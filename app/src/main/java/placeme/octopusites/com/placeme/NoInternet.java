package placeme.octopusites.com.placeme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class NoInternet extends AppCompatActivity {

    Button refreshButton;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    TextView msg, msg2;
    Context mainContext;
    Boolean internetStatus = false;
    Boolean fromSplashScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        refreshButton = (Button) findViewById(R.id.refreshButton);
        msg = (TextView) findViewById(R.id.msg);
        msg2 = (TextView) findViewById(R.id.msg2);
        msg.setTypeface(Z.getBold(this));
        msg2.setTypeface(Z.getLight(this));
        refreshButton.setTypeface(Z.getBold(this));

        fromSplashScreen = getIntent().getBooleanExtra("splash", false);
//
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mainContext = context;

                Log.d("TAG", "onReceive: ======== ");
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    check();
                }
            }

        };
//
        registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                check();

            }
        });
    }


    public void check() {

        Log.d("TAG", "check: called");

        try {

            new Echo().execute().get(5, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    private class Echo extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String echo_number = "" + new Random().nextInt();
            Log.d("TAG", "echo send " + echo_number);

            params.add(new BasicNameValuePair("e", echo_number));
            JSONParser jParser = new JSONParser();
            JSONObject json = jParser.makeHttpRequest("http://104.237.4.236:8086/AESTest/CheckInternet", "GET", params);

            if (json != null) {
                try {
                    String info = json.getString("info");
                    Log.d("TAG", "echo received " + info);

                    if (info.equals(echo_number)) {
                        return true;
                    } else
                        return false;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {

            if (aVoid) {
                Toast.makeText(NoInternet.this, "system online", Toast.LENGTH_SHORT).show();
                if (fromSplashScreen == true && mainContext != null) {
                    Toast.makeText(mainContext, "from splash", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mainContext, SplashScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);       // cleat stack histry new fresh call
                    LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
                    startActivity(intent);
                    finish();
                } else {
                    NoInternet.super.onBackPressed();
                }
            } else
                Toast.makeText(NoInternet.this, "No internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);

    }

    //
//
//    public void check() {
//        if (isOnline()) {
////            super.onBackPressed();
////            Toast.makeText(this, "lets go..!", Toast.LENGTH_SHORT).show();
//            if(fromSplashScreen==true && mainContext!=null){
//                Toast.makeText(mainContext, "from splash", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(mainContext,SplashScreen.class));
//                finish();
//            }else {
//                Toast.makeText(mainContext, "back", Toast.LENGTH_SHORT).show();
//                super.onBackPressed();
//            }
//        }
//    }
//
//    private boolean isOnline() {
//        try {
//            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo netInfo = cm.getActiveNetworkInfo();
//            //should check null because in airplane mode it will be null
//            return (netInfo != null && netInfo.isConnected());
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mRegistrationBroadcastReceiver);

        super.onPause();
    }
}
