package placeme.octopusites.com.placeme;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;

public class LastSession extends AppCompatActivity {

    TextView countrytxt,platformtxt,iptxt,ipiptxt,ipiplasttxt,countrylasttxt,platformlasttxt,iplasttxt,lastaccessedtxt,lastaccessedtxttxt,activetxt,lasttxt,detaildetailtxt,detaildetaillasttxt;
    String scountry,sregion,scity,sregionlast,scitylast,splatform,sip,scountrylast,splatformlast,siplast,slastaccessed;
    JSONObject json;
    String username;
    JSONParser jParser = new JSONParser();
    private static String url_getsession= "http://192.168.100.100/AESTest/GetSessionDetails";

    int found_current=0,found_last=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_session);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Login Sessions");
        ab.setDisplayHomeAsUpEnabled(true);

//        digest1 = MySharedPreferencesManager.getDigest1(this);
//        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
//        String role=MySharedPreferencesManager.getRole(this);

        countrytxt=(TextView)findViewById(R.id.countrytxt);
        platformtxt=(TextView)findViewById(R.id.platformtxt);
        iptxt=(TextView)findViewById(R.id.iptxt);
        ipiptxt=(TextView)findViewById(R.id.ipiptxt);
        ipiplasttxt=(TextView)findViewById(R.id.ipiplasttxt);
        countrylasttxt=(TextView)findViewById(R.id.countrylasttxt);
        platformlasttxt=(TextView)findViewById(R.id.platformlasttxt);
        iplasttxt=(TextView)findViewById(R.id.iplasttxt);
        lastaccessedtxttxt=(TextView)findViewById(R.id.lastaccessedtxttxt);
        lastaccessedtxt=(TextView)findViewById(R.id.lastaccessedtxt);
        activetxt=(TextView)findViewById(R.id.activetxt);
        lasttxt=(TextView)findViewById(R.id.lasttxt);
        detaildetailtxt=(TextView)findViewById(R.id.detaildetailtxt);
        detaildetaillasttxt=(TextView)findViewById(R.id.detaildetaillasttxt);

        activetxt.setTypeface(MyConstants.getBold(this));
        lasttxt.setTypeface(MyConstants.getBold(this));
        detaildetailtxt.setTypeface(MyConstants.getLight(this));
        detaildetaillasttxt.setTypeface(MyConstants.getLight(this));
        countrytxt.setTypeface(MyConstants.getBold(this));
        platformtxt.setTypeface(MyConstants.getBold(this));
        countrylasttxt.setTypeface(MyConstants.getBold(this));
        platformlasttxt.setTypeface(MyConstants.getBold(this));
        iptxt.setTypeface(MyConstants.getBold(this));
        iplasttxt.setTypeface(MyConstants.getBold(this));
        ipiptxt.setTypeface(MyConstants.getLight(this));
        ipiplasttxt.setTypeface(MyConstants.getLight(this));
        lastaccessedtxttxt.setTypeface(MyConstants.getLight(this));
        lastaccessedtxt.setTypeface(MyConstants.getBold(this));






        new GetSessionDetails().execute();






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
    class GetSessionDetails extends AsyncTask<String, String, String> {


        String r="";
        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(url_getsession, "GET", params);
            try {
                r = json.getString("current");
                if(r.equals("found"))
                {
                    found_current=1;
                    scity= json.getString("city");
                    sregion= json.getString("region");
                    scountry= json.getString("country");
                    splatform = json.getString("platform");
                    sip = json.getString("ip");

                }
                r = json.getString("last");
                if(r.equals("found")) {
                    found_last=1;
                    scitylast= json.getString("lastcity");
                    sregionlast= json.getString("lastregion");
                    scountrylast= json.getString("lastcountry");
                    splatformlast = json.getString("lastplatform");
                    siplast = json.getString("lastip");
                    slastaccessed = json.getString("lastaccesstime");
                }

                }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(found_current==1)
            {
                countrytxt.setText(scity+", "+sregion+", "+scountry);
                platformtxt.setText("Place Me on "+splatform);
                iptxt.setText(sip);
            }
            if(found_last==1)
            {
                countrylasttxt.setText(scitylast+", "+sregionlast+", "+scountrylast);
                platformlasttxt.setText("Place Me on "+splatformlast);
                iplasttxt.setText(siplast);

                DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                DateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                DateFormat sdf2 = new SimpleDateFormat("hh:mm a");

                try {
                    Date date0 = sdf.parse(slastaccessed);
                    String date1 = sdf1.format(date0);
                    String time1 = sdf2.format(date0);
                    lastaccessedtxt.setText(date1+" "+time1);

                } catch (Exception e) {
                }


            }
        }
    }
}
