package placeme.octopusites.com.placeme;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

//import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
//import cat.ereza.customactivityoncrash.config.CaocConfig;


public class MyCustomErrorActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    private String plainusername, username = "";
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Password = "passKey";

    JSONParser jParser = new JSONParser();
    JSONObject json;
    String error = "";
    CaocConfig config;
    private static String url_save_error = "http://192.168.100.30/ProfileObjects/Save_Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_error);



        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = sharedpreferences.getString(Username, null);
        String pass = sharedpreferences.getString(Password, null);
        String role = sharedpreferences.getString("role", null);
//            error+=role;

        error = CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent());


        error+=getlogcat();


        config = CustomActivityOnCrash.getConfigFromIntent(getIntent());
        Button restartButton = (Button) findViewById(R.id.restart_button);

        if (config.isShowRestartButton() && config.getRestartActivityClass() != null) {
            restartButton.setText("Restart app");
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SaveError().execute();
//                    report();
                    Log.d("errorreport", "onClick: if click lisnner1");
                    CustomActivityOnCrash.restartApplication(MyCustomErrorActivity.this, config);
                }
            });
        } else {
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    new SaveError().execute();
                    Log.d("errorreport", "onClick: in else click lisnner2");
                    CustomActivityOnCrash.closeApplication(MyCustomErrorActivity.this, config);
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        report();
        MyCustomErrorActivity.super.onBackPressed();
    }

    public void report() {

        Log.d("errorreport", "report: welcome to report");
        new SaveError().execute();

    }
        class SaveError extends AsyncTask<String, String, String> {

            protected String doInBackground(String... param) {

                String r =null;

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));  //0

                params.add(new BasicNameValuePair("e", error));     //1

                json = jParser.makeHttpRequest(url_save_error, "GET", params);
                try {
                    r = json.getString("info");
                    Log.d("errorreport", "doInBackground: -" + json);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return r;
            }

            @Override
            protected void onPostExecute(String result) {
                Log.d("errorreport", "onPostExecute: result" + result);

                if (result.equals("success")) {
                    Toast.makeText(MyCustomErrorActivity.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();
//                MyCustomErrorActivity.super.onBackPressed();
                } else {
                    Toast.makeText(MyCustomErrorActivity.this, result, Toast.LENGTH_SHORT).show();
                }

                CustomActivityOnCrash.restartApplication(MyCustomErrorActivity.this, config);
            }
        }

        public String getlogcat()
        {
            String logcat="";
            try {
                Process process = Runtime.getRuntime().exec("logcat -d ");
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                StringBuilder log=new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    if(line.contains("TAG"))
                        log.append(line+"\n");
                }

               logcat+=log.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return logcat;
        }
}
