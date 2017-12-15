package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class LoginActivity extends AppCompatActivity {

    EditText usernameedittext, passwordedittext;
    String username, usernameenc, password;
    Button login, signup;
    ProgressBar loginprogress;
    TextView forgotpassword;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    public static final String Password = "passKey";
    private String EmailCred = "";
    JSONParser jParser = new JSONParser();

    JSONObject json;
    String resultofop = "";
    private static String url_login = "http://192.168.100.100/AESTest/Auth";
    private static String url_savesessiondetails = "http://192.168.100.100/PlaceMe/SaveSessionDetails";
    //private static String url_sessiondetails = "http://ip-api.com/json";
    String country = "", regionName = "", city = "", isp = "", countryCode = "", query = "";
    String enccountry, encregionName, enccity, encisp, enccountryCode, encquery;
    String digest1, digest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        usernameedittext = (EditText) findViewById(R.id.email);
        passwordedittext = (EditText) findViewById(R.id.password);
        passwordedittext.setTypeface(Typeface.DEFAULT);
        login = (Button) findViewById(R.id.login);
        loginprogress = (ProgressBar) findViewById(R.id.loginprogress);
        forgotpassword = (TextView) findViewById(R.id.forgot);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordDialog.class));
            }
        });
        signup = (Button) findViewById(R.id.signup);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/button.ttf");
        signup.setTypeface(custom_font);
        login.setTypeface(custom_font);

        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/hint.ttf");
        forgotpassword.setTypeface(custom_font2);

        Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/abz.ttf");
        usernameedittext.setTypeface(custom_font3);
        passwordedittext.setTypeface(custom_font3);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String otp = sharedpreferences.getString("otp", null);

        if (otp != null) {
            if (otp.equals("yes")) {
                startActivity(new Intent(getApplicationContext(), OTPActivity.class));
            }
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                username = usernameedittext.getText().toString();
                password = passwordedittext.getText().toString();


                int flag1 = 0, flag2 = 0;

                if (username.equals("")) {
                    flag1 = 1;
                    usernameedittext.setError("Username cannot be blank");
                }
                if (!username.contains("@")) {
                    flag1 = 1;
                    usernameedittext.setError("Invalid Username");
                }
                if (password.equals("")) {
                    flag2 = 1;
                    passwordedittext.setError("Password cannot be blank");
                }
                if (password.length() < 4) {
                    flag2 = 1;
                    passwordedittext.setError("Invalid Password");
                }
                if (flag1 == 0 && flag2 == 0) {

                    login.setVisibility(View.GONE);
                    loginprogress.setVisibility(View.VISIBLE);

                    try {
                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] usernameBytes = username.getBytes("UTF-8");
                        byte[] passwordBytes = password.getBytes("UTF-8");

                        byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                        usernameenc = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));

                        byte[] passwordEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, passwordBytes);
                        String passwordenc = new String(SimpleBase64Encoder.encode(passwordEncryptedBytes));

                        //kun
                        byte[] demo1EncryptedBytes1 = SimpleBase64Encoder.decode(usernameenc);
                        byte[] demo1DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes1);
                        String plainusername = new String(demo1DecryptedBytes1);
                        Log.d("login", "onClick: plain " + plainusername);
                        //
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(Username, usernameenc);
                        editor.putString(Password, passwordenc);

                        editor.commit();

                        attemptLogin(usernameenc, passwordenc);
                    } catch (Exception e) {
                    }

                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                startActivity(new Intent(LoginActivity.this, Welcome.class));
            }
        });

        String newUserCheck = getIntent().getStringExtra("showOTP");
        if (newUserCheck!=null && newUserCheck.equals("yes")) {
            startActivity(new Intent(LoginActivity.this, OTPActivity.class));
        }
    }

    void attemptLogin(String u, String p) {
        UserLoginTask userLoginTask = new UserLoginTask(u, p);
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
                if (isNetworkAvailable()) {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("u", mEmail));
                    params.add(new BasicNameValuePair("p", mPassword));
//                    params.add(new BasicNameValuePair("t", new SharedPrefUtil(getApplicationContext()).getString("firebaseToken")));
                    json = jParser.makeHttpRequest(url_login, "GET", params);
                    Log.d("TAG", "loginActivity json : "+json);
                    String s = null;

                    s = json.getString("info");


                    resultofop = s;
                    Log.d("Msg", json.getString("info"));

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("role", s);
                    editor.commit();

                    if (s.equals("student")) {

                        EmailCred = mEmail;


                        return 1;
                    } else if (s.equals("admin")) {

                        EmailCred = mEmail;


                        return 3;
                    } else if (s.equals("hr")) {

                        EmailCred = mEmail;


                        return 4;
                    } else if (s.equals("alumni")) {

                        EmailCred = mEmail;
                        return 5;
                    }
                    if (s.equals("notactivated")) {

                        String role = json.getString("role");
                        Log.d("Msg role ", role);
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        editor = sharedpreferences.edit();

                        editor.putString("role", role);
                        editor.commit();


                        EmailCred = mEmail;

                        return 6;
                    }

                } else
                    return 2;

            } catch (Exception e) {
                Log.d("exp", e.getMessage());
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(final Integer success) {

            processOutput(success);

        }

        void processOutput(final int success) {
            if (success == 2) {

                passwordedittext.setText("");
                Toast.makeText(LoginActivity.this, "No Internet Connection..!", Toast.LENGTH_LONG).show();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (success == 1) {
                            new SaveSessionDetails().execute();
                            MySharedPreferencesManager.save(LoginActivity.this,"role","student");
                            MySharedPreferencesManager.save(LoginActivity.this,"nameKey",EmailCred);
//                            ProfileRole r = new ProfileRole();
//                            r.setRole("student");
//                            r.setUsername(EmailCred);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else if (success == 3) {
                            new SaveSessionDetails().execute();
                            MySharedPreferencesManager.save(LoginActivity.this,"role","admin");
                            MySharedPreferencesManager.save(LoginActivity.this,"nameKey",EmailCred);
//                            ProfileRole r = new ProfileRole();
//                            r.setRole("admin");
//                            r.setUsername(EmailCred);
                            startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                            finish();
                        } else if (success == 4) {
                            new SaveSessionDetails().execute();
                            MySharedPreferencesManager.save(LoginActivity.this,"role","hr");
                            MySharedPreferencesManager.save(LoginActivity.this,"nameKey",EmailCred);
//                            ProfileRole r = new ProfileRole();
//                            r.setRole("hr");
//                            r.setUsername(EmailCred);
                            startActivity(new Intent(LoginActivity.this, HRActivity.class));
                            finish();
                        } else if (success == 5) {
                            new SaveSessionDetails().execute();
                            MySharedPreferencesManager.save(LoginActivity.this,"role","alumni");
                            MySharedPreferencesManager.save(LoginActivity.this,"nameKey",EmailCred);

//                            ProfileRole r = new ProfileRole();
//                            r.setRole("alumni");
//                            r.setUsername(EmailCred);
                            startActivity(new Intent(LoginActivity.this, AlumniActivity.class));
                            finish();
                        }
                    }
                }).start();
            }
            if (success == 6) {
                Toast.makeText(LoginActivity.this, "You are already registered but not verified.Enter OTP sent on Email", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, OTPActivity.class));
            } else if (resultofop.equals("notpresent")) {
                Toast.makeText(LoginActivity.this, "Incorrect Username. If you are a new user, please Sign Up.", Toast.LENGTH_LONG).show();
                passwordedittext.setText("");
            } else if (resultofop.equals("fail")) {
                Toast.makeText(LoginActivity.this, "Incorrect Password..!", Toast.LENGTH_LONG).show();
                passwordedittext.setText("");
            } else {
                passwordedittext.setText("");
            }

            login.setVisibility(View.VISIBLE);
            loginprogress.setVisibility(View.GONE);
        }
    }

    private boolean isNetworkAvailable() throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            HttpURLConnection localHttpURLConnection = (HttpURLConnection) new URL("http://192.168.100.100/").openConnection();
            localHttpURLConnection.setConnectTimeout(1000);
            localHttpURLConnection.connect();
            return true;
        }
        return false;
    }

    class SaveSessionDetails extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));    //0
            params.add(new BasicNameValuePair("m", getDeviceName()));      //1
            json = jParser.makeHttpRequest(url_savesessiondetails, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public static String md5(String input) {

        String md5 = null;

        if (null == input) return null;

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }

}
