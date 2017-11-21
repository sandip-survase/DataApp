package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CreateNewPassword extends AppCompatActivity {

    EditText newpassedittetx,newpassaedittext;
    String encUsername,role,newpass,newpassa,encpassword,resultofop="";
    JSONObject json;
    JSONParser jParser = new JSONParser();
    private static String url_changepass = "http://192.168.100.100/AESTest/CreatePass";
    ProgressBar progressBar;
    Button changepassbutton;
    String digest1,digest2;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    public static final String Password = "passKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_password);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Create New Password");
        ab.setDisplayHomeAsUpEnabled(true);

        Digest d=new Digest();
        digest1=d.getDigest1();
        digest2=d.getDigest2();


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        encUsername=sharedpreferences.getString(Username,null);
        role=sharedpreferences.getString("role",null);

        newpassedittetx=(EditText)findViewById(R.id.new_password);
        newpassaedittext=(EditText)findViewById(R.id.new_password_again);

        TextView createpasstxt=(TextView)findViewById(R.id.createpasstxt);
        TextView passsenstxt=(TextView)findViewById(R.id.passsenstxt);

        progressBar=(ProgressBar)findViewById(R.id.changepassprogress);
        changepassbutton=(Button)findViewById(R.id.change_password_button);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/button.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/cabinsemibold.ttf");
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(),  "fonts/maven.ttf");

        changepassbutton.setTypeface(custom_font);
        createpasstxt.setTypeface(custom_font3);
        passsenstxt.setTypeface(custom_font4);

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
            json = jParser.makeHttpRequest(url_changepass, "GET", params);
            try {
                resultofop = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if(resultofop.equals("success")) {
                Toast.makeText(CreateNewPassword.this, "Successfully Updated..!", Toast.LENGTH_SHORT).show();
                ProfileRole r=new ProfileRole();
                r.setUsername(encUsername);
                r.setRole(role);

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Username, encUsername);
                editor.putString(Password, encpassword);
                editor.commit();

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
