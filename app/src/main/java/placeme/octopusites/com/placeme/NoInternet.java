package placeme.octopusites.com.placeme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NoInternet extends AppCompatActivity {

    Button refreshButton;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    TextView msg,msg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        refreshButton = (Button) findViewById(R.id.refreshButton);
        msg=(TextView)findViewById(R.id.msg);
        msg2=(TextView)findViewById(R.id.msg2);
        msg.setTypeface(MyConstants.getBold(this));
        msg2.setTypeface(MyConstants.getLight(this));
        refreshButton.setTypeface(MyConstants.getBold(this));



        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.d("TAG", "onReceive: ========  inside NoInternet activity");
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    check();
                }
            }
        };

        registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (isOnline()) {
            super.onBackPressed();
            Toast.makeText(this, "lets go..!", Toast.LENGTH_SHORT).show();
        } else {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }


    public void check() {
        if (isOnline()) {
            super.onBackPressed();
            Toast.makeText(this, "lets go..!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mRegistrationBroadcastReceiver);

        super.onPause();
    }
}
