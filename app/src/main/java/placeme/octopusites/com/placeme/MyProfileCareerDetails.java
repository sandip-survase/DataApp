package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerView;
import com.appnext.base.Appnext;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MyProfileCareerDetails extends AppCompatActivity {

    View careerobjbutton,strengthbutton,weakbutton,locbutton;
    String username;
    private AdView mAdView;
    BannerView bannerView, bannerView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Appnext.init(this);
        setContentView(R.layout.activity_my_profile_career_details);

//        MobileAds.initialize(this, Z.APP_ID);
//        mAdView = findViewById(R.id.ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        bannerView = findViewById(R.id.banner);
        bannerView2 = findViewById(R.id.banner2);
        bannerView.loadAd(new BannerAdRequest());
        bannerView2.loadAd(new BannerAdRequest());

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Career Details");
        ab.setDisplayHomeAsUpEnabled(true);

        username=getIntent().getStringExtra("username");

        TextView careerobjtxt=(TextView)findViewById(R.id.careerobjtxt);
        TextView strengthtxt=(TextView)findViewById(R.id.strengthtxt);
        TextView weaktxt=(TextView)findViewById(R.id.weaktxt);
        TextView locationtxt=(TextView)findViewById(R.id.locationtxt);

        careerobjtxt.setTypeface(Z.getBold(this));
        strengthtxt.setTypeface(Z.getBold(this));
        weaktxt.setTypeface(Z.getBold(this));
        locationtxt.setTypeface(Z.getBold(this));

        careerobjbutton=(View)findViewById(R.id.careerobjbutton);
        strengthbutton=(View)findViewById(R.id.strengthbutton);
        weakbutton=(View)findViewById(R.id.weakbutton);
        locbutton=(View)findViewById(R.id.locbutton);

        careerobjbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileCareerDetails.this,MyProfileCareerObj.class).putExtra("username",username),0);
            }
        });
        strengthbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileCareerDetails.this,MyProfileStrengths.class).putExtra("username",username),0);

            }
        });
        weakbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileCareerDetails.this,MyProfileWeaknesses.class).putExtra("username",username),0);
            }
        });
        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileCareerDetails.this,MyProfileLocationPreferences.class).putExtra("username",username),0);
            }
        });

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE)
        {
            setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
        }
        if(resultCode==MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE)
        {
            setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        bannerView.destroy();
        bannerView2.destroy();
        super.onDestroy();
    }
}
