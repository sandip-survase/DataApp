package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class CreateNewPassword extends AppCompatActivity {

    AppCompatEditText newpassedittetx,newpassaedittext;
    TextInputLayout newinput,newainput;
    String encUsername,role,newpass,newpassa,encpassword,resultofop="";
    JSONObject json;
    JSONParser jParser = new JSONParser();
    ProgressBar progressBar;
    Button changepassbutton;
    String digest1,digest2;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_password);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Create New Password");
        ab.setDisplayHomeAsUpEnabled(true);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        encUsername=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);

        newinput=(TextInputLayout)findViewById(R.id.newinput);
        newainput=(TextInputLayout)findViewById(R.id.newainput);
        newpassedittetx=(AppCompatEditText)findViewById(R.id.new_password);
        newpassaedittext=(AppCompatEditText)findViewById(R.id.new_password_again);

        TextView createpasstxt=(TextView)findViewById(R.id.createpasstxt);
        TextView passsenstxt=(TextView)findViewById(R.id.passsenstxt);
        progressBar=(ProgressBar)findViewById(R.id.changepassprogress);
        changepassbutton=(Button)findViewById(R.id.change_password_button);

        createpasstxt.setTypeface(MyConstants.getBold(this));
        passsenstxt.setTypeface(MyConstants.getLight(this));
        newinput.setTypeface(MyConstants.getLight(this));
        newainput.setTypeface(MyConstants.getLight(this));
        newpassedittetx.setTypeface(MyConstants.getBold(this));
        newpassaedittext.setTypeface(MyConstants.getBold(this));
        changepassbutton.setTypeface(MyConstants.getBold(this));




        changepassbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    newpass=newpassedittetx.getText().toString();
                    newpassa=newpassaedittext.getText().toString();
                    int flag2=0,flag3=0;
                    if(newpass.length()<6)
                    {
                        flag2=1;
                        newpassedittetx.setError("Too short");
                    }
                    if(newpassa.length()<6)
                    {
                        flag3=1;
                        newpassaedittext.setError("Too short");
                    }
                    if(flag2==0&&flag3==0)
                    {
                        if(newpass.equals(newpassa))
                        {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                            progressBar.setVisibility(View.VISIBLE);
                            changepassbutton.setVisibility(View.GONE);

                            try
                            {

                                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                                String sPadding = "ISO10126Padding";

                                byte[] otpBytes = newpass.getBytes("UTF-8");

                                byte[] otpEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, otpBytes);
                                encpassword=new String(SimpleBase64Encoder.encode(otpEncryptedBytes));

                            }catch (Exception e)
                            {

                            }
                            new ChangePassTask().execute();
                        }
                        else
                        {
                            Toast.makeText(CreateNewPassword.this,"Passwords didn't match",Toast.LENGTH_LONG).show();
                        }
                    }

                }catch (Exception e){
                    Toast.makeText(CreateNewPassword.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    class ChangePassTask extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));
            params.add(new BasicNameValuePair("p", encpassword));
            json = jParser.makeHttpRequest(MyConstants.url_CreatePass, "GET", params);
            try {
                resultofop = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if(resultofop.equals("success")) {
                Toast.makeText(CreateNewPassword.this, "Successfully Updated..!", Toast.LENGTH_SHORT).show();

                MySharedPreferencesManager.save(CreateNewPassword.this,"nameKey",encUsername);
                MySharedPreferencesManager.save(CreateNewPassword.this,"passKey",encpassword);

                if(role.equals("student"))
                {
                    startActivity(new Intent(CreateNewPassword.this, MainActivity.class));
                    finish();

                }
                else if(role.equals("admin")) {
                    startActivity(new Intent(CreateNewPassword.this, AdminActivity.class));
                    finish();
                }
            }
            changepassbutton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
