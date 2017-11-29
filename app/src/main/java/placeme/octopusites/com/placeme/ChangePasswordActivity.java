package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.R.id.cpass;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText currentedittext,newpassedittetx,newpassaedittext;
    String username,currentpass,newpass,newpassa,resultofop="";
    String enccurrentpass,encnewpass;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    private static String url_changepass = "http://192.168.100.100/PlaceMe/ChangePass";
    ProgressBar progressBar;
    Button changepassbutton;
    String digest1,digest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Change Password");
        ab.setDisplayHomeAsUpEnabled(true);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        String role=MySharedPreferencesManager.getRole(this);




        currentedittext=(EditText)findViewById(R.id.current_password);
        newpassedittetx=(EditText)findViewById(R.id.new_password);
        newpassaedittext=(EditText)findViewById(R.id.new_password_again);

        currentedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentedittext.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        newpassedittetx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newpassedittetx.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        newpassaedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newpassaedittext.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextView forgotpassword=(TextView)findViewById(R.id.forgot);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangePasswordActivity.this,ForgotPasswordDialog.class));
            }
        });

        TextView createpasstxt=(TextView)findViewById(R.id.createpasstxt);
        TextView passsenstxt=(TextView)findViewById(R.id.passsenstxt);

        progressBar=(ProgressBar)findViewById(R.id.changepassprogress);
        changepassbutton=(Button)findViewById(R.id.change_password_button);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/button.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/hint.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/cabinsemibold.ttf");
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(),  "fonts/maven.ttf");
        forgotpassword.setTypeface(custom_font2);
        changepassbutton.setTypeface(custom_font);
        createpasstxt.setTypeface(custom_font3);
        passsenstxt.setTypeface(custom_font4);
        changepassbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    currentedittext.setError(null);
                    newpassedittetx.setError(null);
                    newpassaedittext.setError(null);

                    currentpass=currentedittext.getText().toString();
                    newpass=newpassedittetx.getText().toString();
                    newpassa=newpassaedittext.getText().toString();
                    int flag1=0,flag2=0,flag3=0;
                    if(currentpass.length()<3)
                    {
                        flag1=1;
                        currentedittext.setError("Invalid Password");
                    }
                    if(newpass.length()<6)
                    {
                        flag2=1;
                        newpassedittetx.setError("Minimum 6 Characters");
                    }
                    if(newpassa.length()<6)
                    {
                        flag3=1;
                        newpassaedittext.setError("Minimum 6 Characters");
                    }
                    if(flag1==0&&flag2==0&&flag3==0)
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

                                byte[] currentpassBytes = currentpass.getBytes("UTF-8");
                                byte[] newpassBytes = newpass.getBytes("UTF-8");

                                byte[] currentpassEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, currentpassBytes);
                                enccurrentpass=new String(SimpleBase64Encoder.encode(currentpassEncryptedBytes));

                                byte[] newpassEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, newpassBytes);
                                encnewpass=new String(SimpleBase64Encoder.encode(newpassEncryptedBytes));

                                new ChangePassTask().execute();
                            }catch (Exception e){Toast.makeText(ChangePasswordActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();}


                        }
                        else
                        {
                            Toast.makeText(ChangePasswordActivity.this,"Passwords didn't match",Toast.LENGTH_LONG).show();
                        }
                    }

                }catch (Exception e){
                    Toast.makeText(ChangePasswordActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

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
    class ChangePassTask extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            params.add(new BasicNameValuePair("p", enccurrentpass));
            params.add(new BasicNameValuePair("a", encnewpass));

            json = jParser.makeHttpRequest(url_changepass, "GET", params);

            Log.d("TAG", "change password json "+json);
            try {
                resultofop = json.getString("info");
                json.put("chge pass json : ",json);


            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if(resultofop.equals("success")) {

                MySharedPreferencesManager.save(ChangePasswordActivity.this,"passKey", encnewpass);

                Toast.makeText(ChangePasswordActivity.this, "Successfully Updated..!", Toast.LENGTH_SHORT).show();
                ChangePasswordActivity.super.onBackPressed();
            }
            else if(resultofop.equals("wrong"))
                Toast.makeText(ChangePasswordActivity.this,"Wrong Current Password..!",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(ChangePasswordActivity.this,"Failed..!",Toast.LENGTH_SHORT).show();




            currentedittext.setText("");
            newpassedittetx.setText("");
            newpassaedittext.setText("");
            changepassbutton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

}
