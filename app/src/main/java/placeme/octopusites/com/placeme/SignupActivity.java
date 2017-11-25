package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings.Secure;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.crypto.spec.IvParameterSpec;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class SignupActivity extends AppCompatActivity {

    String digest1,digest2,digest3;
    List<String> categories = new ArrayList<String>();
    Spinner rolespinner;
    EditText usernameedittext,phoneedittext,passwordedittext,confirmpasswordedittext;
    String username="",phone="",password="",role="",resultofop="",hash="";
    Button signup;
    ProgressBar signupprogress;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    TextView legald;
    private static  String android_id,device_id;
    private static String url_getdigest = "http://192.168.100.100/PlaceMe/GetDigest";
    private static String url_signup= "http://192.168.100.100/PlaceMe/SignUp";
    String string;
    String usernameenc,passwordenc,phoneenc;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    public static final String Password = "passKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        try {
            android_id = Secure.getString(getApplication().getContentResolver(), Secure.ANDROID_ID);
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            device_id = telephonyManager.getDeviceId();
        }catch (Exception e){}


        new GetDigest().execute();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



        ScrollView scrollView=(ScrollView)findViewById(R.id.signupscroll);
        disableNavigationViewScrollbars(scrollView);


        rolespinner=(Spinner)findViewById(R.id.role);

        categories.add("- Select Role -");
        categories.add("Student/Under Graduate/Post Graduate");
        categories.add("Alumni/Current Working Employee/Ex-Employee");
        categories.add("Training and Placement Officer/Co-ordinator");
        categories.add("Recruiter/HR Manager");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories)
        {
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {

                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/abz.ttf");
                tv.setTypeface(custom_font3);
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.parseColor("#eeeeee"));
                }
                return view;
            }
        };;
        rolespinner.setAdapter(dataAdapter);

        rolespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){

                    if(position==1)
                        role="student";
                    else if(position==2)
                        role="alumni";
                    else if(position==3)
                        role="admin";
                    else if(position==4)
                        role="hr";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        usernameedittext=(EditText)findViewById(R.id.email);
        phoneedittext=(EditText)findViewById(R.id.phone);
        passwordedittext=(EditText)findViewById(R.id.pass);
        confirmpasswordedittext=(EditText)findViewById(R.id.cpass);
        signup=(Button)findViewById(R.id.signup);
        signupprogress=(ProgressBar)findViewById(R.id.signuprogress);
        legald=(TextView)findViewById(R.id.legal);

        String termsandconditions="Terms and Conditions";
        String privacypolicy="Privacy Policy";

        legald.setText("By Clicking Sign Up, you agree to our "+termsandconditions+" and that you have read our "+privacypolicy);

        Pattern termsAndConditionsMatcher = Pattern.compile(termsandconditions);
        Linkify.addLinks(legald, termsAndConditionsMatcher, "terms:");

        Pattern privacyPolicyMatcher = Pattern.compile(privacypolicy);
        Linkify.addLinks(legald, privacyPolicyMatcher, "privacy:");

        legald.setMovementMethod(LinkMovementMethod.getInstance());
        legald.setLinkTextColor(Color.parseColor("#c59a6d"));

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/button.ttf");
        signup.setTypeface(custom_font);

        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/abz.ttf");
        usernameedittext.setTypeface(custom_font3);
        phoneedittext.setTypeface(custom_font3);
        passwordedittext.setTypeface(custom_font3);
        confirmpasswordedittext.setTypeface(custom_font3);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=usernameedittext.getText().toString();
                phone=phoneedittext.getText().toString();
                password=passwordedittext.getText().toString();
                String apassword=confirmpasswordedittext.getText().toString();




                int flag1=0,flag2=0,flag3=0,flag4=0,flag5=0;
                if(username.equals("")||!username.contains("@"))
                {
                    flag1=1;
                    usernameedittext.setError("Invalid Email");
                }
                if(password.length()<4)
                {
                    flag2=1;
                    passwordedittext.setError("Too Short");
                }
                if(!password.equals(apassword))
                {
                    flag3=1;
                    Toast.makeText(SignupActivity.this,"Passwords did'nt match..!",Toast.LENGTH_SHORT).show();
                }
                if(role==null)
                {
                    flag4=1;
                    Toast.makeText(SignupActivity.this,"Select Role..!",Toast.LENGTH_SHORT).show();
                }
                if(!(phone.length()==10))
                {
                    flag5=1;
                    phoneedittext.setError("Invalid Phone Number");
                }
                if(flag1==0&&flag2==0&&flag3==0&&flag4==0&&flag5==0)
                {
                    signup.setVisibility(View.GONE);
                    signupprogress.setVisibility(View.VISIBLE);


                    try {


                        byte[] demoKeyBytes=SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes=SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] usernameBytes = username.getBytes("UTF-8");
                        byte[] passwordBytes = password.getBytes("UTF-8");
                        byte[] phoneBytes = phone.getBytes("UTF-8");

                        byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                        usernameenc=new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));

                        byte[] passwordEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, passwordBytes);
                        passwordenc=new String(SimpleBase64Encoder.encode(passwordEncryptedBytes));

                        byte[] phoneEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, phoneBytes);
                        phoneenc=new String(SimpleBase64Encoder.encode(phoneEncryptedBytes));




                        String MyPREFERENCES = "MyPrefs" ;
                        SharedPreferences sharedpreferences;
                        String Username = "nameKey";
                        String Password = "passKey";
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();


                        editor.putString(Username, usernameenc);
                        editor.putString(Password, passwordenc);
                        editor.putString("role", role);
                        editor.commit();


                        new MyAsyncTask1().execute();

                    }catch (Exception e){

                        Toast.makeText(SignupActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }


               }
            }
        });

    }

    private void disableNavigationViewScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }

    class MyAsyncTask1 extends AsyncTask<String, String, String> {

        String op[]=new String[8];

        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("ue",usernameenc));   //0
            params.add(new BasicNameValuePair("pe",passwordenc));    //1
            params.add(new BasicNameValuePair("phe",phoneenc));      //2
            params.add(new BasicNameValuePair("r",role));           //3
            params.add(new BasicNameValuePair("id",android_id));    //4
            params.add(new BasicNameValuePair("t", new SharedPrefUtil(getApplicationContext()).getString("firebaseToken"))); //5

            json = jParser.makeHttpRequest(url_signup, "GET", params);
            try {

                resultofop = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if(resultofop.equals("success")) {
                Toast.makeText(SignupActivity.this,"Enter OTP sent on your Email..!", Toast.LENGTH_LONG).show();

                MySharedPreferencesManager.save(SignupActivity.this,"role",role);
                MySharedPreferencesManager.save(SignupActivity.this,"nameKey",usernameenc);

//                ProfileRole r=new ProfileRole();
//                r.setUsername(usernameenc);
//                r.setRole(role);

                startActivity(new Intent(SignupActivity.this,OTPActivity.class).putExtra("encusername",usernameenc).putExtra("password",passwordenc).putExtra("hash",hash));
                finish();
            }
            else if(resultofop.equals("notactivated")) {
                //send otp verify,if ok activate
                MySharedPreferencesManager.save(SignupActivity.this,"role",role);
                MySharedPreferencesManager.save(SignupActivity.this,"nameKey",usernameenc);

//                ProfileRole r=new ProfileRole();
//                r.setUsername(usernameenc);
//                r.setRole(role);
                Toast.makeText(SignupActivity.this,"You are already registered but not verified.Enter OTP sent on your Email..!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SignupActivity.this,OTPActivity.class).putExtra("encusername",usernameenc).putExtra("password",passwordenc).putExtra("hash",hash));
                finish();
            }
            else if(resultofop.equals("already")) {
                //send otp verify,if ok activate
                Toast.makeText(SignupActivity.this,"You are already a registered user.Please login.", Toast.LENGTH_LONG).show();

                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                finish();
            }
            else
                Toast.makeText(SignupActivity.this,resultofop, Toast.LENGTH_SHORT).show();


            signup.setVisibility(View.VISIBLE);
            signupprogress.setVisibility(View.GONE);
        }
    }
    class GetDigest extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("aid", android_id));
            params.add(new BasicNameValuePair("did", device_id));

            json = jParser.makeHttpRequest(url_getdigest, "GET", params);
            try {
                digest1 = json.getString("digest1");
                digest2 = json.getString("digest2");
                digest3 = json.getString("digest3");

//                Digest d=new Digest();
//                d.setDigest1(digest1);
//                d.setDigest2(digest2);

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("digest1", digest1);
                editor.putString("digest2", digest2);
                editor.putString("digest3", digest3);
                editor.commit();

            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {



        }
    }



}
