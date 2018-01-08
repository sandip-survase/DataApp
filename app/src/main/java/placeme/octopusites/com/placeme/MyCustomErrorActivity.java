package placeme.octopusites.com.placeme;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

import static placeme.octopusites.com.placeme.AES4all.OtoString;


public class MyCustomErrorActivity extends AppCompatActivity {


    private String plainusername, username = "";
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Password = "passKey";

    JSONParser jParser = new JSONParser();
    JSONObject json;
    String error = "";
    CaocConfig config;
    String abd="";
    TextView ohsnapmsg,ohsnapmsg2;
    String uname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_error);

        username = MySharedPreferencesManager.getUsername(this);

        error=getlogcat();
        String stack = CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent());

        abd = stack+error ;
        Log.d("TAG", "onCreate: username -"+username);
        Log.d("TAG", "onCreate: abd - "+abd);
        new SaveError().execute();
        Log.d("TAG", "onCreate: after ask task - ");

        config = CustomActivityOnCrash.getConfigFromIntent(getIntent());

        ohsnapmsg=(TextView)findViewById(R.id.ohsnapmsg);
        ohsnapmsg2=(TextView)findViewById(R.id.ohsnapmsg2);
        ohsnapmsg.setTypeface(Z.getBold(this));
        ohsnapmsg2.setTypeface(Z.getLight(this));
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

    class SaveError extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String encstring = "";
            List<String> response = new ArrayList<String>();
//            *********
            try {
                uname = Z.Decrypt(username, MyCustomErrorActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String filename = "Logcat.txt";
            generateNoteOnSD(MyCustomErrorActivity.this, filename, abd);

            File atach1 = new File(Environment.getExternalStorageDirectory(), "/Place Me/" + uname + "/" + filename);


            if (atach1 != null) {

                MultipartUtility multipart = null;
                try {
                    multipart = new MultipartUtility(Z.url_SaveLogFile, "UTF-8");
                    Log.d("TAG", "UploadProfile1 : input  username " + username);
                    multipart.addFormField("u", username);

                    if (filename != "") {
                        multipart.addFormField("f", filename);
                        multipart.addFilePart("uf", atach1);
                        Log.d("TAG", "onSuccess: f name- " + filename);
                    } else
                        multipart.addFormField("f", "null");
                    response = multipart.finish();
                    atach1.delete();

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("TAG", "exp : " + e.getMessage());

                }

            } else {
                Log.d("TAG", "file null");

            }

//**************
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

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
                if (line.contains("TAG") || line.contains("cricket"))
                    log.append(line+"\n");
            }

            logcat+=log.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return logcat;
    }

    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Place Me/" + uname);
//            File root = new File(Environment.getExternalStorageDirectory(), "/Place Me/Logcat");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
//            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
