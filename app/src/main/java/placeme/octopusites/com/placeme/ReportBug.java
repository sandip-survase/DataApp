package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class ReportBug extends AppCompatActivity {

    String username;
    Button reportbug_button;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    private static String url_report= "http://192.168.100.100/AESTest/ReportBug";
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String digest1,digest2;
    EditText title,comments;
    ProgressBar reportprogress;
    String stitle="",scomments="";
    String enctitle,enccomments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bug);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Report Bug");
        ab.setDisplayHomeAsUpEnabled(true);


        sharedpreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);
        String role=sharedpreferences.getString("role",null);

        ProfileRole r=new ProfileRole();
        r.setUsername(username);
        r.setRole(role);

        Digest d=new Digest();
        digest1=d.getDigest1();
        digest2=d.getDigest2();

        if(digest1==null||digest2==null) {
            digest1 = sharedpreferences.getString("digest1", null);
            digest2 = sharedpreferences.getString("digest2", null);
            d.setDigest1(digest1);
            d.setDigest2(digest2);
        }

        TextView createreporttxt=(TextView)findViewById(R.id.createreporttxt);
        TextView reportsenstxt=(TextView)findViewById(R.id.reportsenstxt);

        reportbug_button=(Button)findViewById(R.id.reportbug_button);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/cabinsemibold.ttf");
        createreporttxt.setTypeface(custom_font);
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/button.ttf");
        reportbug_button.setTypeface(custom_font3);
        Typeface custom_fon2 = Typeface.createFromAsset(getAssets(),  "fonts/maven.ttf");
        reportsenstxt.setTypeface(custom_fon2);

        title=(EditText)findViewById(R.id.title);
        comments=(EditText)findViewById(R.id.comments);
        reportprogress=(ProgressBar)findViewById(R.id.reportprogress);

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        comments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                comments.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        reportbug_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title.setError(null);
                comments.setError(null);

                stitle=title.getText().toString();
                scomments=comments.getText().toString();
                int errorflag=0;
                if(title.length()<3)
                {
                    errorflag=1;
                    title.setError("Invalid Title");
                }

                if(errorflag==0)
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    reportprogress.setVisibility(View.VISIBLE);
                    reportbug_button.setVisibility(View.GONE);

                    try
                    {

                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] currentpassBytes = stitle.getBytes("UTF-8");
                        byte[] newpassBytes = scomments.getBytes("UTF-8");

                        byte[] currentpassEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, currentpassBytes);
                        enctitle=new String(SimpleBase64Encoder.encode(currentpassEncryptedBytes));

                        byte[] newpassEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, newpassBytes);
                        enccomments=new String(SimpleBase64Encoder.encode(newpassEncryptedBytes));

                        new ReportBugTask().execute();
                    }catch (Exception e){
                        Toast.makeText(ReportBug.this,e.getMessage(),Toast.LENGTH_LONG).show();}
                }

            }
        });

    }

    class ReportBugTask extends AsyncTask<String, String, String> {


        String r="";
        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            params.add(new BasicNameValuePair("t", enctitle));
            params.add(new BasicNameValuePair("c", enccomments));
            json = jParser.makeHttpRequest(url_report, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success")) {
                Toast.makeText(ReportBug.this, "Successfully Reported..!", Toast.LENGTH_SHORT).show();
                ReportBug.super.onBackPressed();
            }
            else
                Toast.makeText(ReportBug.this,"Failed..!",Toast.LENGTH_SHORT).show();

            title.setText("");
            comments.setText("");
            reportbug_button.setVisibility(View.VISIBLE);
            reportprogress.setVisibility(View.GONE);
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
}
