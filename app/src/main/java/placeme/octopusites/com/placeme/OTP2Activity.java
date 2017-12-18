package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
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
    AppCompatEditText otpedittext;
    TextInputLayout otplayout;
    Button verify;
    String enteredOTP,encOTP;
    String encUsername,encpassword;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    String resultofop="";
    TextView resendotp,entertxt,otptxt;
    ProgressBar otpprogress;

    String digest1,digest2;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp2);

        otplayout=(TextInputLayout)findViewById(R.id.otplayout);
        entertxt=(TextView)findViewById(R.id.entertxt);
        resendotp=(TextView)findViewById(R.id.resend);
        otptxt=(TextView)findViewById(R.id.otptxt);
        otpedittext=(AppCompatEditText)findViewById(R.id.otp);
        verify=(Button)findViewById(R.id.submitotp);

        entertxt.setTypeface(Z.getBold(this));
        otptxt.setTypeface(Z.getLight(this));
        resendotp.setTypeface(Z.getBold(this));
        otplayout.setTypeface(Z.getLight(this));
        otpedittext.setTypeface(Z.getBold(this));
        verify.setTypeface(Z.getBold(this));

        otpprogress=(ProgressBar)findViewById(R.id.otpprogress);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        encUsername=MySharedPreferencesManager.getUsername(this);




        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendotp.setVisibility(View.GONE);

                new ResendOTP().execute();
            }
        });




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

            json = jParser.makeHttpRequest(Z.url_verifyotp, "GET", params);
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

            json = jParser.makeHttpRequest(Z.url_resendotp, "GET", params);
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
