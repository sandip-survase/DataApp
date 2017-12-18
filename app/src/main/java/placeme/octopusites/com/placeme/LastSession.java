package placeme.octopusites.com.placeme;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LastSession extends AppCompatActivity {

    TextView countrytxt,platformtxt,iptxt,ipiptxt,ipiplasttxt,countrylasttxt,platformlasttxt,iplasttxt,lastaccessedtxt,lastaccessedtxttxt,activetxt,lasttxt,detaildetailtxt,detaildetaillasttxt;
    String scountry,sregion,scity,sregionlast,scitylast,splatform,sip,scountrylast,splatformlast,siplast,slastaccessed;
    JSONObject json;
    String username;
    JSONParser jParser = new JSONParser();

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

        activetxt.setTypeface(Z.getBold(this));
        lasttxt.setTypeface(Z.getBold(this));
        detaildetailtxt.setTypeface(Z.getLight(this));
        detaildetaillasttxt.setTypeface(Z.getLight(this));
        countrytxt.setTypeface(Z.getBold(this));
        platformtxt.setTypeface(Z.getBold(this));
        countrylasttxt.setTypeface(Z.getBold(this));
        platformlasttxt.setTypeface(Z.getBold(this));
        iptxt.setTypeface(Z.getBold(this));
        iplasttxt.setTypeface(Z.getBold(this));
        ipiptxt.setTypeface(Z.getLight(this));
        ipiplasttxt.setTypeface(Z.getLight(this));
        lastaccessedtxttxt.setTypeface(Z.getLight(this));
        lastaccessedtxt.setTypeface(Z.getBold(this));






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
            json = jParser.makeHttpRequest(Z.url_getsession, "GET", params);
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
