package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class WelcomeActivity extends AppCompatActivity {

    JSONParser jParser = new JSONParser();
    JSONObject json;
    EditText username;
    String encUsersName, plainUsername, usertype, isactivated;
    Button welcomeNext;
    String digest1, digest2,status;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Username = "nameKey";
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);

        username = (EditText) findViewById(R.id.username);
        welcomeNext = (Button) findViewById(R.id.welcomeNext);

        digest1 = MySharedPreferencesManager.getDigest1(WelcomeActivity.this);
        digest2 = MySharedPreferencesManager.getDigest2(WelcomeActivity.this);

        welcomeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plainUsername = username.getText().toString().trim();
                new validateUser().execute();
            }
        });

    }
    class validateUser extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {
            String s = null;

            try {

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";


                byte[] usernameBytes = plainUsername.getBytes("UTF-8");

                byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                encUsersName = new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));
                Log.d("TAG", "enc: "+encUsersName);

                MySharedPreferencesManager.save(WelcomeActivity.this,"nameKey",encUsersName);


            } catch (Exception e) {
                Log.d("TAG", "doInBackground: 1 "+e.getMessage());
            }

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsersName));       //0
            json = jParser.makeHttpRequest(MyConstants.url_Welcome, "GET", params);
            try {

                s = json.getString("info");

                Log.d("TAG2", "json: "+json);

            } catch (Exception e) {
                Log.d("TAG", "doInBackground: 2 "+e.getMessage());
            }

            return s;
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d("TAG", "onPostExecute: "+result);

            if (result.equals("found")) {

                startActivity(new Intent(WelcomeActivity.this, WelcomePasswordActivity.class));
            } else if (result.equals("notfound")) {
                startActivity(new Intent(WelcomeActivity.this, WelcomeProfileActivity.class));
            }

        }

    }


}
