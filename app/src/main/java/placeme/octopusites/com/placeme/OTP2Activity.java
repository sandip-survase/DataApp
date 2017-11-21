package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class OTP2Activity extends AppCompatActivity {
    EditText otpedittext;
    Button verify;
    String enteredOTP,encOTP;
    String encUsername,encpassword;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    String resultofop="";
    TextView resendotp;
    ProgressBar otpprogress;
    private static String url_verifyotp= "http://192.168.0.100/AESTest/VerifyOTP";
    private static String url_resendotp= "http://192.168.0.100/AESTest/ResendOTP";
    String digest1,digest2;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    public static final String Password = "passKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp2);

        otpedittext=(EditText)findViewById(R.id.otp);
        verify=(Button)findViewById(R.id.submitotp);

        otpprogress=(ProgressBar)findViewById(R.id.otpprogress);

        Digest d=new Digest();
        digest1=d.getDigest1();
        digest2=d.getDigest2();

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/button.ttf");
        verify.setTypeface(custom_font);

        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/abz.ttf");
        otpedittext.setTypeface(custom_font3);

        resendotp=(TextView)findViewById(R.id.resend);
        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendotp.setVisibility(View.GONE);

                new ResendOTP().execute();
            }
        });

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        encUsername=sharedpreferences.getString(Username,null);


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify.setVisibility(View.GONE);
                otpprogress.setVisibility(View.VISIBLE);
                enteredOTP=otpedittext.getText().toString();

                try {
                    byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                    byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                    String sPadding = "ISO10126Padding";

                    byte[] otpBytes = enteredOTP.getBytes("UTF-8");

                    byte[] otpEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, otpBytes);
                    encOTP=new String(SimpleBase64Encoder.encode(otpEncryptedBytes));

                }catch (Exception e){}

                new VerifyOTP().execute();
            }
        });
    }

    class VerifyOTP extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ud", encUsername));
            params.add(new BasicNameValuePair("eo", encOTP));

            json = jParser.makeHttpRequest(url_verifyotp, "GET", params);
            try {
                resultofop = json.getString("info");



            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if(resultofop.equals("success")) {

                startActivity(new Intent(OTP2Activity.this,CreateNewPassword.class));
                finish();
            }
            else if(resultofop.equals("fail"))
                Toast.makeText(OTP2Activity.this,"Incorrect OTP..!",Toast.LENGTH_LONG).show();
            else  if(resultofop.equals("expired"))
                Toast.makeText(OTP2Activity.this,"OTP expired.Please request OTP again.",Toast.LENGTH_LONG).show();



            verify.setVisibility(View.VISIBLE);
            otpprogress.setVisibility(View.GONE);


        }
    }

    class ResendOTP extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ud", encUsername));

            json = jParser.makeHttpRequest(url_resendotp, "GET", params);
            try {
                resultofop = json.getString("info");



            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if(resultofop.equals("success"))
            {
                Toast.makeText(OTP2Activity.this,"New OTP has been sent to your Email.!",Toast.LENGTH_LONG).show();
            }

            resendotp.setVisibility(View.VISIBLE);

        }
    }
    @Override
    public void onBackPressed() {

        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
