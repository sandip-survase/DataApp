package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
public class EditEmail extends AppCompatActivity {


    String username,plainusername;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;

    RelativeLayout rl1,rl2;
    View editselectionview;
    Button changeemailbutton;
    TextInputEditText newemail,accountpassword,otp;
    String snewemail="",saccountpassword="";
    String encnewemail,encaccountpassword;
    ProgressBar editemailprogress;
    TextInputLayout otpinput,emailinput,passinput;
    int otpflag=0;
    String enteredOTP,encOTP;
    TextView primaryemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_email);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Email");
        ab.setDisplayHomeAsUpEnabled(true);


        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        String role=MySharedPreferencesManager.getRole(this);


        otpinput=(TextInputLayout)findViewById(R.id.otpinput);
        emailinput=(TextInputLayout)findViewById(R.id.emailinput);
        passinput=(TextInputLayout)findViewById(R.id.passinput);
        newemail=(TextInputEditText)findViewById(R.id.newemail);
        accountpassword=(TextInputEditText)findViewById(R.id.accountpassword);
        otp=(TextInputEditText)findViewById(R.id.otp);
        editemailprogress=(ProgressBar)findViewById(R.id.editemailprogress);
        rl1=(RelativeLayout)findViewById(R.id.editemailrl1);
        rl2=(RelativeLayout)findViewById(R.id.editemailrl2);
        editselectionview=(View)findViewById(R.id.editselectionview);
        primaryemail=(TextView)findViewById(R.id.primaryemail);
        editselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl1.setVisibility(View.GONE);
                rl2.setVisibility(View.VISIBLE);
            }
        });

        TextView asstxt=(TextView)findViewById(R.id.asstxt);
        TextView ass2txt=(TextView)findViewById(R.id.ass2txt);
        TextView emailemailtxt=(TextView)findViewById(R.id.emailemailtxt);
        TextView passpasstxt=(TextView)findViewById(R.id.passpasstxt);

        changeemailbutton=(Button)findViewById(R.id.changeemailbutton);

        asstxt.setTypeface(Z.getLight(this));
        primaryemail.setTypeface(Z.getBold(this));
        emailemailtxt.setTypeface(Z.getLight(this));
        ass2txt.setTypeface(Z.getBold(this));
        emailinput.setTypeface(Z.getLight(this));
        newemail.setTypeface(Z.getBold(this));
        passinput.setTypeface(Z.getLight(this));
        accountpassword.setTypeface(Z.getBold(this));
        otpinput.setTypeface(Z.getLight(this));
        otp.setTypeface(Z.getBold(this));
        passpasstxt.setTypeface(Z.getBold(this));

        changeemailbutton.setTypeface(Z.getBold(this));
        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        try {
            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(username);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);
            primaryemail.setText(plainusername);
        }catch (Exception e){}
        changeemailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpflag==1)
                {
                    enteredOTP=otp.getText().toString();
                    try {
                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] otpBytes = enteredOTP.getBytes("UTF-8");

                        byte[] otpEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, otpBytes);
                        encOTP=new String(SimpleBase64Encoder.encode(otpEncryptedBytes));
                        editemailprogress.setVisibility(View.VISIBLE);
                        changeemailbutton.setVisibility(View.GONE);
                        new VerifyOTP().execute();

                    }catch (Exception e){}
                }
                else {
                    snewemail = newemail.getText().toString();
                    saccountpassword = accountpassword.getText().toString();
                    int errotflag = 0;
                    if (snewemail.length() < 5 || !snewemail.contains("@")) {
                        errotflag = 1;
                        emailinput.setError("Kindly enter valid email address");
                    } else {
                        if (saccountpassword.length() < 6) {
                            errotflag = 1;
                            passinput.setError("Kindly enter valid account password");
                        }
                    }
                    if (errotflag == 0) {
                        try {
                            byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                            byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                            String sPadding = "ISO10126Padding";

                            if (!plainusername.equals(snewemail)) {
                                byte[] newemailBytes = snewemail.getBytes("UTF-8");
                                byte[] accountpasswordBytes = saccountpassword.getBytes("UTF-8");

                                byte[] newemailEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, newemailBytes);
                                encnewemail = new String(SimpleBase64Encoder.encode(newemailEncryptedBytes));
                                byte[] accountpasswordEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, accountpasswordBytes);
                                encaccountpassword = new String(SimpleBase64Encoder.encode(accountpasswordEncryptedBytes));
                                editemailprogress.setVisibility(View.VISIBLE);
                                changeemailbutton.setVisibility(View.GONE);
                                new SaveEditedEmail().execute();
                            } else
                                Toast.makeText(EditEmail.this, plainusername + " is your current email address. Kindly enter new email address to which you want to switch your account.", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        });

    }
    class VerifyOTP extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r="";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("e",encnewemail));    //1
            params.add(new BasicNameValuePair("o",encOTP));         //2

            json = jParser.makeHttpRequest(Z.url_VerifyOTPEditEmail, "GET", params);

            Log.d("TAG", "verifyotp json : "+json);
            try {
                r = json.getString("info");

            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            editemailprogress.setVisibility(View.GONE);
            changeemailbutton.setVisibility(View.VISIBLE);

            if (result.equals("success")) {

                new ChangeEmailInFireBaseTask().execute();    // change in placemechats db email

            } else if (result.equals("fail")) {
                otpflag=0;
                Toast.makeText(EditEmail.this, "Your entered OTP is incorrect. Kindly enter valid OTP.", Toast.LENGTH_SHORT).show();
            }
            else if (result.equals("expired")) {
                otpflag=0;
                Toast.makeText(EditEmail.this, "OTP Expired. Click on Resend OTP.", Toast.LENGTH_SHORT).show();
            }


        }

    }

    class ChangeEmailInFireBaseTask extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String resultofop=null;

            Log.d("TAG", "inside ChangeEmailInFireBaseTask: ");


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            params.add(new BasicNameValuePair("nu", encnewemail));
            Log.d("TAG", "ChangeEmailInFireBaseTask input old : "+username);
            Log.d("TAG", "ChangeEmailInFireBaseTask input new : "+encnewemail);

            json = jParser.makeHttpRequest(Z.url_ChangeUsernameFireBase, "GET", params);

            try {
                resultofop = json.getString("info");
                Log.d("TAG", "ChangeEmailInFireBaseTask json : "+json);
            }catch (Exception e){
                e.printStackTrace();
            }

            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result!=null && result.equals("success")){

                MySharedPreferencesManager.save(EditEmail.this, Z.USERNAME_KEY,encnewemail);
                primaryemail.setText(snewemail);
                Toast.makeText(EditEmail.this, "Username successfully changed from "+plainusername+" to " +snewemail , Toast.LENGTH_SHORT).show();
                otpflag=0;
                onBackPressed();
            }

        }
    }
    class SaveEditedEmail extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r="";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("e",encnewemail));             //1
            params.add(new BasicNameValuePair("p",encaccountpassword));             //2
            try {
                Log.d("TAG", "username: "+Z.Decrypt(username, EditEmail.this));
                Log.d("TAG", "new username: "+Z.Decrypt(encnewemail, EditEmail.this));
            } catch (Exception e) {
                e.printStackTrace();
            }
            json = jParser.makeHttpRequest(Z.url_editemail, "GET", params);
            Log.d("TAG", "SaveEditedEmail json : "+json);
            try {
                r = json.getString("info");
            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            editemailprogress.setVisibility(View.GONE);
            changeemailbutton.setVisibility(View.VISIBLE);

            if (result.equals("success")) {
                Toast.makeText(EditEmail.this, "Enter OTP sent on " + snewemail, Toast.LENGTH_LONG).show();
                emailinput.setVisibility(View.GONE);
                passinput.setVisibility(View.GONE);
                otpinput.setVisibility(View.VISIBLE);
                otpflag=1;
                TextView passpasstxt=(TextView)findViewById(R.id.passpasstxt);
                passpasstxt.setText("Enter One Time Password (OTP) sent on "+snewemail);
            } else if (result.equals("wrong")) {
                otpflag=0;
                Toast.makeText(EditEmail.this, "Incorrect account's password", Toast.LENGTH_LONG).show();
            }
            else if (result.equals("notfound")) {
                otpflag=0;
                Toast.makeText(EditEmail.this, "User not found..!", Toast.LENGTH_LONG).show();
            }
            else if (result.equals("already")) {
                otpflag=0;
                Toast.makeText(EditEmail.this, "User having email "+snewemail+" already exists..!", Toast.LENGTH_LONG).show();
            }

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        if(otpflag==1)
        {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        else
            super.onBackPressed();
    }
}
