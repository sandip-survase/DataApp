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
import android.widget.ProgressBar;
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
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        Log.d("TAG", "onCreate: ");

        refreshButton = (Button) findViewById(R.id.refreshButton);
        msg = (TextView) findViewById(R.id.msg);
        msg2 = (TextView) findViewById(R.id.msg2);
        msg.setTypeface(Z.getBold(this));
        msg2.setTypeface(Z.getLight(this));
        refreshButton.setTypeface(Z.getBold(this));
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        fromSplashScreen = getIntent().getBooleanExtra("splash", false);
//
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mainContext = context;

                Log.d("TAG", "mom called");
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    check();
                }
            }

        };
        registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar.setVisibility(View.VISIBLE);
                refreshButton.setVisibility(View.GONE);
                TryAgain();

            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        Log.d("TAG", "onResume: ");
//    }

    public void check() {
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
            Log.d("TAG", "kill process s: " + echo_number);

            params.add(new BasicNameValuePair("e", echo_number));
            JSONParser jParser = new JSONParser();
            JSONObject json = jParser.makeHttpRequest(Z.url_CheckInternet, "GET", params);

            if (json != null) {
                try {
                    String info = json.getString("info");
                    Log.d("TAG", "kill process r:" + info);

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
                if (fromSplashScreen == true && mainContext != null) {
                    Log.d("TAG", "towords om");
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


    public void TryAgain() {

        try {

            new TryAgainTask().execute().get(5, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(NoInternet.this, "No internet", Toast.LENGTH_SHORT).show();
            progressbar.setVisibility(View.GONE);
            refreshButton.setVisibility(View.VISIBLE);
        }

    }


    private class TryAgainTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String echo_number = "" + new Random().nextInt();
            Log.d("TAG", "kill process s:" + echo_number);

            params.add(new BasicNameValuePair("e", echo_number));
            JSONParser jParser = new JSONParser();
            JSONObject json = jParser.makeHttpRequest(Z.url_CheckInternet, "GET", params);

            if (json != null) {
                try {
                    String info = json.getString("info");
                    Log.d("TAG", "kill process r:" + info);

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
            Log.d("TAG", "TryAgain : " + aVoid);
            progressbar.setVisibility(View.GONE);
            refreshButton.setVisibility(View.VISIBLE);
            if (aVoid) {
                if (fromSplashScreen == true && mainContext != null) {
                    Log.d("TAG", "towords om");
                    Intent intent = new Intent(mainContext, SplashScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);       // cleat stack histry new fresh call
                    LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
                    startActivity(intent);
                    finish();

                } else {
                    NoInternet.super.onBackPressed();
                }
            } else {
                Toast.makeText(NoInternet.this, "No internet", Toast.LENGTH_SHORT).show();
                //TODO handle multiple toast
            }
        }
    }

    @Override
    public void onBackPressed() {

        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);


    }


//    @Override
//    public void onPause() {
//        Log.d("TAG", "onPause: ");
//        super.onPause();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
        Log.d("TAG", "onDestroy: ");
    }
}
