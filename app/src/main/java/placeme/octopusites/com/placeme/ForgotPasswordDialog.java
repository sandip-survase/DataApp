package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
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

public class ForgotPasswordDialog extends AppCompatActivity {

    EditText forgotedittext;
    Button reset;
    JSONParser jParser = new JSONParser();
    TextView forgottxt,forgotetxt;
    JSONObject json;
    String enteredemailorphone,encemailorphone;
    ProgressBar forgotprogress;
    private static String url_forgotpassword= "http://192.168.100.100/AESTest/ForgotPassword";
    String resultofop="",encUsername;
    String digest1,digest2;
    private static  String android_id;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_dialog);



        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        android_id = Settings.Secure.getString(getApplication().getContentResolver(), Settings.Secure.ANDROID_ID);


        forgottxt=(TextView)findViewById(R.id.forgottxt);
        forgotetxt=(TextView)findViewById(R.id.forgotetxt);
        forgotedittext=(EditText)findViewById(R.id.forgotedittext);
        reset=(Button)findViewById(R.id.submitforgot);
        forgotprogress=(ProgressBar)findViewById(R.id.forgotprogress);

        forgottxt.setTypeface(MyConstants.getBold(this));
        forgotetxt.setTypeface(MyConstants.getLight(this));
        forgotedittext.setTypeface(MyConstants.getBold(this));
        reset.setTypeface(MyConstants.getBold(this));




        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean flag=false;


                enteredemailorphone=forgotedittext.getText().toString();
                if(enteredemailorphone.length()<5 && !enteredemailorphone.contains("@")){
                    forgotedittext.setError("Kindly enter your email address");
                    flag=true;
                }
                    if(flag==false) {


                        try {
                            byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                            byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                            String sPadding = "ISO10126Padding";

                            byte[] emailorphoneBytes = enteredemailorphone.getBytes("UTF-8");

                            byte[] emailorphoneEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, emailorphoneBytes);
                            encemailorphone = new String(SimpleBase64Encoder.encode(emailorphoneEncryptedBytes));
                            forgotprogress.setVisibility(View.VISIBLE);
                            reset.setVisibility(View.GONE);

                        } catch (Exception e) {
                        }

                        new ForgotPassword().execute();
                    }
            }
        });
    }
    class ForgotPassword extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ud", encemailorphone));
            params.add(new BasicNameValuePair("aid", android_id));
            json = jParser.makeHttpRequest(url_forgotpassword, "GET", params);
            try {
                resultofop = json.getString("info");
                encUsername=json.getString("info2");
                role=json.getString("role");

            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if(resultofop.equals("success"))
            {
                MySharedPreferencesManager.save(ForgotPasswordDialog.this,MyConstants.USERNAME_KEY, encUsername);
                MySharedPreferencesManager.save(ForgotPasswordDialog.this,"role", role);

                Toast.makeText(ForgotPasswordDialog.this,"OTP to reset your account has been sent to your Email.!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ForgotPasswordDialog.this,OTP2Activity.class));
                finish();
            }


            reset.setVisibility(View.VISIBLE);
            forgotprogress.setVisibility(View.GONE);

        }
    }
//    @Override
//    public void onBackPressed() {
//
//        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//        homeIntent.addCategory( Intent.CATEGORY_HOME );
//        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(homeIntent);
//    }
}
