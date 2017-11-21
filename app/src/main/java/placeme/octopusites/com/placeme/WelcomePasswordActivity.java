package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class WelcomePasswordActivity extends AppCompatActivity {

    String encUsersName,passwordstr,encPassword;
    EditText passwordedittext;
    String digest1, digest2;
    Button welcomePasswordNext;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    String resultofop="";
    private String EmailCred="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_password);

        passwordedittext= (EditText) findViewById(R.id.password);
        welcomePasswordNext= (Button) findViewById(R.id.welcomePasswordNext);
        encUsersName=MySharedPreferencesManager.getUsername(WelcomePasswordActivity.this);
        digest1 = MySharedPreferencesManager.getDigest1(WelcomePasswordActivity.this);
        digest2 = MySharedPreferencesManager.getDigest2(WelcomePasswordActivity.this);
        Log.d("TAG", "MySharedPreferencesManager encUsersName: "+encUsersName);

        welcomePasswordNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                passwordstr=passwordedittext.getText().toString();
                if(passwordstr.length()<5){
                    passwordedittext.setError("Incorrect password");
                }
                else {

                    try {

                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] passwordBytes = passwordstr.getBytes("UTF-8");

                        byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, passwordBytes);
                        encPassword = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));

                        MySharedPreferencesManager.save(WelcomePasswordActivity.this,"passKey",encPassword);

                        attemptLogin(encUsersName,encPassword);

                    } catch (Exception e) {
                    }
                }
            }
        });
    }//onc

    void attemptLogin(String u, String p)
    {
        UserLoginTask userLoginTask=new UserLoginTask(u,p);
        userLoginTask.execute();
    }



    public class UserLoginTask extends AsyncTask<Void, Void, Integer> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Integer doInBackground(Void... args) {
            try {
                if(isNetworkAvailable())
                {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("u",mEmail));
                    params.add(new BasicNameValuePair("p",mPassword));
                    params.add(new BasicNameValuePair("t", new SharedPrefUtil(getApplicationContext()).getString("firebaseToken")));
                    json = jParser.makeHttpRequest(MyConstants.url_login, "GET", params);
                    String s=null;

                    s= json.getString("info");


                    resultofop=s;
                    Log.d("Msg", json.getString("info"));

//                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                    editor.putString("role", s);
//                    editor.commit();

                    MySharedPreferencesManager.save(WelcomePasswordActivity.this,"role",s);

                    if(s.equals("student")){

                        EmailCred=mEmail;


                        return 1;
                    }
                    else if(s.equals("admin")){

                        EmailCred=mEmail;


                        return 3;
                    }
                    else if(s.equals("hr")){

                        EmailCred=mEmail;


                        return 4;
                    }
                    else if(s.equals("alumni")){

                        EmailCred=mEmail;
                        return 5;
                    }
                    if(s.equals("notactivated")){

                        String role=json.getString("role");
//                        MySharedPreferencesManager.save(WelcomePasswordActivity.this,"role",role);
//
//                        ProfileRole r=new ProfileRole();
//                        r.setRole(role);

                        MySharedPreferencesManager.save(WelcomePasswordActivity.this,"role",role);
                        EmailCred=mEmail;

                        return 6;
                    }

                }
                else
                    return 2;

            }  catch (Exception e) {
                Log.d("exp", e.getMessage());
                e.printStackTrace();
            }
            return 0;
        }
        @Override
        protected void onPostExecute(final Integer success) {

            processOutput(success);

        }
        void processOutput(final int success)
        {
            if (success==2) {

                passwordedittext.setText("");
                Toast.makeText(WelcomePasswordActivity.this,"No Internet Connection..!", Toast.LENGTH_LONG).show();
            }
            else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (success==1) {
                            new SaveSessionDetails().execute();
                            ProfileRole r=new ProfileRole();
                            r.setRole("student");
                            r.setUsername(EmailCred);
                            startActivity(new Intent(WelcomePasswordActivity.this,MainActivity.class));
                            finish();
                        }
                        else if (success==3) {
                            new SaveSessionDetails().execute();
                            ProfileRole r=new ProfileRole();
                            r.setRole("admin");
                            r.setUsername(EmailCred);
                            startActivity(new Intent(WelcomePasswordActivity.this,AdminActivity.class));
                            finish();
                        }
                        else if (success==4) {
                            new SaveSessionDetails().execute();
                            ProfileRole r=new ProfileRole();
                            r.setRole("hr");
                            r.setUsername(EmailCred);
                            startActivity(new Intent(WelcomePasswordActivity.this,HRActivity.class));
                            finish();
                        }
                        else if (success==5) {
                            new SaveSessionDetails().execute();
                            ProfileRole r=new ProfileRole();
                            r.setRole("alumni");
                            r.setUsername(EmailCred);
                            startActivity(new Intent(WelcomePasswordActivity.this,AlumniActivity.class));
                            finish();
                        }
                    }
                }).start();
            }
            if (success==6) {
                Toast.makeText(WelcomePasswordActivity.this,"You are already registered but not verified.Enter OTP sent on Email", Toast.LENGTH_LONG).show();
                startActivity(new Intent(WelcomePasswordActivity.this, OTPActivity.class));
            }
            else if(resultofop.equals("notpresent"))
            {
                Toast.makeText(WelcomePasswordActivity.this,"Incorrect Username. If you are a new user, please Sign Up.", Toast.LENGTH_LONG).show();
                passwordedittext.setText("");
            }
            else if(resultofop.equals("fail"))
            {
                Toast.makeText(WelcomePasswordActivity.this,"Incorrect Password..!", Toast.LENGTH_LONG).show();
                passwordedittext.setText("");
            }
            else
            {
                passwordedittext.setText("");

            }

            welcomePasswordNext.setVisibility(View.VISIBLE);
//            loginprogress.setVisibility(View.GONE);
        }
    }
    private boolean isNetworkAvailable() throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected())
        {
            HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL("http://192.168.100.100/").openConnection();
            localHttpURLConnection.setConnectTimeout(1000);
            localHttpURLConnection.connect();
            return true;
        }
        return false;
    }

    class SaveSessionDetails extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",encUsersName));    //0
            params.add(new BasicNameValuePair("m",getDeviceName()));      //1
            json = jParser.makeHttpRequest(MyConstants.url_savesessiondetails, "GET", params);
            try {
                r = json.getString("info");

            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }


    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

}
