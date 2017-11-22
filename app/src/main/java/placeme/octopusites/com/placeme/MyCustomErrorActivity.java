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
    String abd="";
    private static String url_save_error = "http://192.168.100.10:8080/ProfileObjects/Save_Error";
    private static String url_save_bug = "http://192.168.100.10:8080/ProfileObjects/Save_Bug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_error);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = sharedpreferences.getString(Username, null);
        error=getlogcat();
        abd=error+CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent());
        new ask().execute();

        config = CustomActivityOnCrash.getConfigFromIntent(getIntent());

        Button restartButton = (Button) findViewById(R.id.restart_button);

        if (config.isShowRestartButton() && config.getRestartActivityClass() != null) {
            restartButton.setText("Restart app");
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomActivityOnCrash.restartApplication(MyCustomErrorActivity.this, config);
                }
            });
        } else {
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomActivityOnCrash.closeApplication(MyCustomErrorActivity.this, config);
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        MyCustomErrorActivity.super.onBackPressed();
    }

    class ask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            String  r ="";
//
            String str =abd;
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("u", username));  //0
            params.add(new BasicNameValuePair("k", str));     //1
            json = jParser.makeHttpRequest(url_save_bug, "GET", params);
            Log.d("TAG", "json - "+json);
            try {
                r = json.getString("info");
                Log.d("errorreport", "doInBackground: errorreport  r: -" + r);
            } catch (Exception e) {
                Log.d("errorreport", "doInBackground: errorreport  Exception: -" + e.getMessage());
                e.printStackTrace();
            }

            return  r;

        }
        protected void onPostExecute(String result) {

            if (result.equals("success")) {
                Toast.makeText(MyCustomErrorActivity.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MyCustomErrorActivity.this, result, Toast.LENGTH_SHORT).show();
            }

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
