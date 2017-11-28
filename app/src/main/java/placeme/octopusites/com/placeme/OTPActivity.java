package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class OTPActivity extends AppCompatActivity {

    EditText otpedittext;
    TextView entertxt, otptxt, resend;
    Button verify;
    String enteredOTP, encOTP;
    String encUsername, encpassword;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    boolean activationMessageflag = false;
    String resultofop = "";
    TextView resendotp;
    ProgressBar otpprogress;
    private static String url_verifyotp = "http://192.168.100.100/AESTest/VerifyOTP";
    private static String url_resendotp = "http://192.168.100.100/AESTest/ResendOTP";

    String digest1, digest2;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";
    String plainusername, hash;
    ImageView otpClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        setFinishOnTouchOutside(false);

        otpedittext = (EditText) findViewById(R.id.otp);
        otpClose = (ImageView) findViewById(R.id.otpClose);
        verify = (Button) findViewById(R.id.submitotp);

        otpprogress = (ProgressBar) findViewById(R.id.otpprogress);

        otpClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);


        entertxt = (TextView) findViewById(R.id.entertxt);
        otptxt = (TextView) findViewById(R.id.otptxt);
        resend = (TextView) findViewById(R.id.resend);

        String activationMessage = MySharedPreferencesManager.getData(OTPActivity.this, "activationMessage");
        Log.d("TAG", "onCreate : activationMessage " + activationMessage);
        if (activationMessage != null && activationMessage.equals("yes")) {
            activationMessageflag = true;
            entertxt.setText("Enter Activation Code");
            otpedittext.setHint("Activation Code");
            otptxt.setText("Enter Activation code sent on your professional email..!");
            resend.setText("Resend");
        }
        Log.d("TAG", "onCreate : activationMessageflag " + activationMessageflag);


        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/button.ttf");
        verify.setTypeface(custom_font);

        Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/abz.ttf");
        otpedittext.setTypeface(custom_font3);

        resendotp = (TextView) findViewById(R.id.resend);
        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendotp.setVisibility(View.GONE);

                new ResendOTP().execute();
            }
        });

        encUsername=MySharedPreferencesManager.getUsername(this);
        encpassword=MySharedPreferencesManager.getPassword(this);

        try {
            demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            demoIVBytes = SimpleBase64Encoder.decode(digest2);
            sPadding = "ISO10126Padding";

            byte[] demo1EncryptedBytes1 = SimpleBase64Encoder.decode(encUsername);
            byte[] demo1DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes1);
            plainusername = new String(demo1DecryptedBytes1);

            byte[] demo2EncryptedBytes1 = SimpleBase64Encoder.decode(encpassword);
            byte[] demo2DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo2EncryptedBytes1);
            String data = new String(demo2DecryptedBytes1);
            hash = md5(data + MySharedPreferencesManager.getDigest3(OTPActivity.this));

        } catch (Exception e) {
        }

        MySharedPreferencesManager.save(this,"otp", "yes");


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify.setVisibility(View.GONE);
                otpprogress.setVisibility(View.VISIBLE);
                enteredOTP = otpedittext.getText().toString();

                try {
                    byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                    byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                    String sPadding = "ISO10126Padding";

                    byte[] otpBytes = enteredOTP.getBytes("UTF-8");

                    byte[] otpEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, otpBytes);
                    encOTP = new String(SimpleBase64Encoder.encode(otpEncryptedBytes));

                } catch (Exception e) {
                }

                new VerifyOTP().execute();
            }
        });
    }

    class VerifyOTP extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String encUsernameVerify = encUsername;

            if (activationMessageflag == true) {
                encUsernameVerify = MySharedPreferencesManager.getData(OTPActivity.this, "proEmail");
            }
            Log.d("TAG", "doInBackground: proEmail : " + encUsernameVerify);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ud", encUsernameVerify));
            params.add(new BasicNameValuePair("eo", encOTP));
            Log.d("TAG", "otp input encuser " + encUsernameVerify);
            Log.d("TAG", "otp input encotp " + encOTP);

            json = jParser.makeHttpRequest(url_verifyotp, "GET", params);
            Log.d("TAG", "doInBackground: verfy OTP sevlet call");
            Log.d("TAG", "otp verify json : " + json);
            try {
                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "doInBackground: exp " + e.getMessage());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if (resultofop.equals("success")) {
                if (activationMessageflag == false) {
                    Log.d("TAG", "onPostExecute: activation 1 flag" + activationMessageflag);
                    //create new firebase user

                    MySharedPreferencesManager.save(OTPActivity.this,MyConstants.USERNAME_KEY,encUsername);
                    MySharedPreferencesManager.save(OTPActivity.this,MyConstants.PASSWORD_KEY,encpassword);
                    MySharedPreferencesManager.save(OTPActivity.this,"otp", "no");

                    String role = MySharedPreferencesManager.getRole(OTPActivity.this);
                    String u = MySharedPreferencesManager.getUsername(OTPActivity.this);
                    String p = MySharedPreferencesManager.getPassword(OTPActivity.this);

                    new CreateFirebaseUser(u, p).execute();

                    Toast.makeText(OTPActivity.this, "Successfully Registered..!", Toast.LENGTH_LONG).show();


                    if (role.equals("student")) {

                        startActivity(new Intent(OTPActivity.this, MainActivity.class));
                        finish();
                    } else if (role.equals("admin")) {


                        startActivity(new Intent(OTPActivity.this, AdminActivity.class));
                        finish();
                    } else if (role.equals("alumni")) {


                        startActivity(new Intent(OTPActivity.this, AlumniActivity.class));
                        finish();
                    } else if (role.equals("hr")) {


                        startActivity(new Intent(OTPActivity.this, HRActivity.class));
                        finish();
                    }
                } else if (resultofop.equals("fail"))
                    Toast.makeText(OTPActivity.this, "Incorrect OTP..!", Toast.LENGTH_LONG).show();
                else if (resultofop.equals("expired"))
                    Toast.makeText(OTPActivity.this, "OTP expired.Please request OTP again.", Toast.LENGTH_LONG).show();
                else if (resultofop.equals("already")) {

                } else
                    Toast.makeText(OTPActivity.this, resultofop, Toast.LENGTH_LONG).show();
            }
            if (resultofop.equals("success") && activationMessageflag == true) {
                Log.d("TAG", "onPostExecute: activation 2 flag" + activationMessageflag);
                String role = MySharedPreferencesManager.getRole(OTPActivity.this);
                Log.d("TAG", "OTP onPostExecute: sahrd role ^^^^ "+role);

                String u = MySharedPreferencesManager.getUsername(OTPActivity.this);
                String p = MySharedPreferencesManager.getPassword(OTPActivity.this);

                new CreateFirebaseUser(u, p).execute();

                startActivity(new Intent(OTPActivity.this, WelcomeGenrateCodeActivity.class));
                finish();
            } else if (resultofop.equals("fail") && activationMessageflag == true) {
                Toast.makeText(OTPActivity.this, "Incorrect OTP..!", Toast.LENGTH_LONG).show();
            } else if (resultofop.equals("expired") && activationMessageflag == true)
                Toast.makeText(OTPActivity.this, "OTP expired.Please request OTP again.", Toast.LENGTH_LONG).show();


            verify.setVisibility(View.VISIBLE);
            otpprogress.setVisibility(View.GONE);

            // clearing expired otp
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                json = jParser.makeHttpRequest(url_verifyotp, "GET", params);
            } catch (Exception e) {
            }

        }

    }

    void loginFirebase(String username, String hash) {

        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username, hash)
                .addOnCompleteListener(OTPActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            Toast.makeText(OTPActivity.this, "Successfully logged in to Firebase", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(OTPActivity.this, "Failed to login to Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    class ResendOTP extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String encfname = MySharedPreferencesManager.getData(OTPActivity.this, "fname");
            String enclname = MySharedPreferencesManager.getData(OTPActivity.this, "lname");

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ud", encUsername));
            params.add(new BasicNameValuePair("f", encfname));
            params.add(new BasicNameValuePair("l", enclname));
            Log.d("TAG", "doInBackground: resend otp usrename " + encUsername);

            json = jParser.makeHttpRequest(url_resendotp, "GET", params);
            try {
                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if (resultofop.equals("success")) {
                Toast.makeText(OTPActivity.this, "New OTP has been sent to your Email.!", Toast.LENGTH_LONG).show();
            }

            resendotp.setVisibility(View.VISIBLE);

        }
    }

    class CreateFirebaseUser extends AsyncTask<String, String, String> {

        String u, p;

        CreateFirebaseUser(String u, String p) {
            this.u = u;
            this.p = p;
        }

        protected String doInBackground(String... param) {


            String fname=MySharedPreferencesManager.getData(OTPActivity.this,"fname");
            String lname=MySharedPreferencesManager.getData(OTPActivity.this,"lname");
            String encfullName=fname+" "+lname;
            String encPhone=MySharedPreferencesManager.getData(OTPActivity.this,"phone");

            if(encfullName==null)
                encfullName="Name_Not_Found_From_shared";
            if(encPhone==null)
                encPhone="Phone_Not_Found_From_shared";

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", u));
            params.add(new BasicNameValuePair("p", p));
            params.add(new BasicNameValuePair("t", new SharedPrefUtil(getApplicationContext()).getString("firebaseToken"))); //5
            json = jParser.makeHttpRequest(MyConstants.url_create_firebase, "GET", params);
            Log.d("TAG", "Firebase: "+json);
            try {
                resultofop = json.getString("info");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {

            loginFirebase(plainusername, hash);
            Toast.makeText(OTPActivity.this, resultofop, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onBackPressed() {

        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
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
