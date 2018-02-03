package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerView;
import com.appnext.base.Appnext;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.MyProfileCareerObjModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfileCareerObj extends AppCompatActivity {


    String username,role;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    int edittedFlag=0,currentsetFlag=1;
    RadioGroup radioGroupCareerobj;
    TextView careerobjtxt;
    RadioButton radioButtonObj1,radioButtonObj2,radioButtonObj3,radioButtonObj4,radioButtonObj5,radioButtonObj6;
    TextInputEditText otherobj;
    TextInputLayout otherobjinput;
    String careerobj="To secure a promising position in the company of repute that offers both a challenging assignments and ample opportunity for growth in terms of due recognition and added responsibilities.";
    String enccareerobj,encobj="";
    int otherFlag=0;
    StudentData s=new StudentData();
    private AdView mAdView;
    BannerView bannerView, bannerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Appnext.init(this);
        setContentView(R.layout.activity_my_profile_career_obj);


//        MobileAds.initialize(this, Z.APP_ID);
//        mAdView = findViewById(R.id.ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        bannerView = findViewById(R.id.banner);
        bannerView2 = findViewById(R.id.banner2);
        bannerView.loadAd(new BannerAdRequest());
        bannerView2.loadAd(new BannerAdRequest());

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Career Objective");
        ab.setDisplayHomeAsUpEnabled(true);

        otherobj=(TextInputEditText)findViewById(R.id.otherobj);
        careerobjtxt=(TextView)findViewById(R.id.careerobjtxt);

        radioGroupCareerobj=(RadioGroup)findViewById(R.id.radioGroupCareerobj);
        otherobjinput= (TextInputLayout) findViewById(R.id.otherobjinput);

        radioButtonObj1=(RadioButton)findViewById(R.id.radioButtonObj1);
        radioButtonObj2=(RadioButton)findViewById(R.id.radioButtonObj2);
        radioButtonObj3=(RadioButton)findViewById(R.id.radioButtonObj3);
        radioButtonObj4=(RadioButton)findViewById(R.id.radioButtonObj4);
        radioButtonObj5=(RadioButton)findViewById(R.id.radioButtonObj5);
        radioButtonObj6=(RadioButton)findViewById(R.id.radioButtonObj6);

        radioButtonObj1.setTypeface(Z.getBold(this));
        radioButtonObj2.setTypeface(Z.getBold(this));
        radioButtonObj3.setTypeface(Z.getBold(this));
        radioButtonObj4.setTypeface(Z.getBold(this));
        radioButtonObj5.setTypeface(Z.getBold(this));
        radioButtonObj6.setTypeface(Z.getBold(this));

        careerobjtxt.setTypeface(Z.getBold(this));
        otherobjinput.setTypeface(Z.getLight(this));
        otherobj.setTypeface(Z.getBold(this));


        radioGroupCareerobj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(i) {
                    case R.id.radioButtonObj1:
                        otherFlag=0;
                        otherobj.setVisibility(View.GONE);
                        otherobj.setText("");
                        otherobj.setFocusable(false);
                        otherobj.setFocusableInTouchMode(false);
                        otherobj.setClickable(false);
                        careerobj="To secure a promising position in the company of repute that offers both a challenging assignments and ample opportunity for growth in terms of due recognition and added responsibilities.";

                        radioButtonObj1.setTextColor(getResources().getColor(R.color.sky_blue_color));

                        radioButtonObj2.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj3.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj4.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj5.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj6.setTextColor(getResources().getColor(R.color.dark_color));


                        if(currentsetFlag!=1)
                            edittedFlag=1;
                        break;
                    case R.id.radioButtonObj2:
                        otherFlag=0;
                        otherobj.setText("");
                        otherobj.setVisibility(View.GONE);
                        otherobj.setFocusable(false);
                        otherobj.setFocusableInTouchMode(false);
                        otherobj.setClickable(false);
                        careerobj="To work with the company, belonging to professionally managed group, that is offering enough opportunities for career advancement and professional growth of an individual.";

                        radioButtonObj2.setTextColor(getResources().getColor(R.color.sky_blue_color));

                        radioButtonObj1.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj3.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj4.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj5.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj6.setTextColor(getResources().getColor(R.color.dark_color));


                        if(currentsetFlag!=2)
                            edittedFlag=1;
                        break;
                    case R.id.radioButtonObj3:
                        otherFlag=0;
                        otherobj.setText("");
                        otherobj.setVisibility(View.GONE);
                        otherobj.setFocusable(false);
                        otherobj.setFocusableInTouchMode(false);
                        otherobj.setClickable(false);
                        careerobj="To work in a stimulating work culture and environment where I am allowed to freely apply the management principles learned to the best interest of the company and gain rich experience thereby enhancing my knowledge.";

                        radioButtonObj3.setTextColor(getResources().getColor(R.color.sky_blue_color));

                        radioButtonObj1.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj2.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj4.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj5.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj6.setTextColor(getResources().getColor(R.color.dark_color));

                        if(currentsetFlag!=3)
                            edittedFlag=1;
                        break;
                    case R.id.radioButtonObj4:
                        otherFlag=0;
                        otherobj.setText("");
                        otherobj.setVisibility(View.GONE);
                        otherobj.setFocusable(false);
                        otherobj.setFocusableInTouchMode(false);
                        otherobj.setClickable(false);
                        careerobj="To achieve success in competitive environment of the corporate world and making a mark by my hard work, passion for the job and strong will to fulfill management expectations following ethical values.";

                        radioButtonObj4.setTextColor(getResources().getColor(R.color.sky_blue_color));

                        radioButtonObj1.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj2.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj3.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj5.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj6.setTextColor(getResources().getColor(R.color.dark_color));

                        if(currentsetFlag!=4)
                            edittedFlag=1;
                        break;
                    case R.id.radioButtonObj5:
                        otherFlag=0;
                        otherobj.setText("");
                        otherobj.setVisibility(View.GONE);
                        otherobj.setFocusable(false);
                        otherobj.setFocusableInTouchMode(false);
                        otherobj.setClickable(false);
                        careerobj="To represent myself as one of the important members of the team and driving my team towards fulfillment of company's goals and objectives and make long lasting impression of my team on senior management.";

                        radioButtonObj5.setTextColor(getResources().getColor(R.color.sky_blue_color));

                        radioButtonObj1.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj2.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj3.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj4.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj6.setTextColor(getResources().getColor(R.color.dark_color));
                        if(currentsetFlag!=5)
                            edittedFlag=1;
                        break;
                    case R.id.radioButtonObj6:
                        otherFlag=1;
                        otherobj.setFocusable(true);
                        otherobj.setVisibility(View.VISIBLE);
                        otherobj.setFocusableInTouchMode(true);
                        otherobj.setClickable(true);

                        radioButtonObj6.setTextColor(getResources().getColor(R.color.sky_blue_color));

                        radioButtonObj1.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj2.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj3.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj4.setTextColor(getResources().getColor(R.color.dark_color));
                        radioButtonObj5.setTextColor(getResources().getColor(R.color.dark_color));

                        if(currentsetFlag!=6)
                            edittedFlag=1;
                        break;
                }
            }
        });

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);



        TextView careerobjtxt=(TextView)findViewById(R.id.careerobjtxt);
        careerobjtxt.setTypeface(Z.getBold(this));



        careerobj=s.getCareerobj();
        if(careerobj!=null) {
            if (careerobj.length() > 5) {
                if (careerobj.equals("To secure a promising position in the company of repute that offers both a challenging assignments and ample opportunity for growth in terms of due recognition and added responsibilities."))
                {
                    radioButtonObj1.setChecked(true);
                    currentsetFlag=1;
                }
                else if (careerobj.equals("To work with the company, belonging to professionally managed group, that is offering enough opportunities for career advancement and professional growth of an individual."))
                {
                    radioButtonObj2.setChecked(true);
                    currentsetFlag=2;
                }
                else if (careerobj.equals("To work in a stimulating work culture and environment where I am allowed to freely apply the management principles learned to the best interest of the company and gain rich experience thereby enhancing my knowledge."))
                {
                    radioButtonObj3.setChecked(true);
                    currentsetFlag=3;
                }
                else if (careerobj.equals("To achieve success in competitive environment of the corporate world and making a mark by my hard work, passion for the job and strong will to fulfill management expectations following ethical values."))
                {
                    radioButtonObj4.setChecked(true);
                    currentsetFlag=4;
                }
                else if (careerobj.equals("To represent myself as one of the important members of the team and driving my team towards fulfillment of company's goals and objectives and make long lasting impression of my team on senior management."))
                {
                    radioButtonObj5.setChecked(true);
                    currentsetFlag=5;
                }
                else {
                    radioButtonObj6.setChecked(true);
                    otherobj.setText(careerobj);
                    currentsetFlag=6;
                }
            }
        }
        else
            careerobj="To secure a promising position in the company of repute that offers both a challenging assignments and ample opportunity for growth in terms of due recognition and added responsibilities.";

        edittedFlag=0;
    }
    void validateandSave()
    {
        int errorflag=0;
        if(otherFlag==1)
        {
            careerobj=otherobj.getText().toString();
            if(careerobj.length()<5)
            {
                errorflag=1;
                otherobjinput.setError("Kindly enter your career objective");
            }

        }
        if(errorflag==0)
        {
            try {

                MyProfileCareerObjModal obj2 = new MyProfileCareerObjModal(careerobj);;

                encobj =OtoString(obj2,MySharedPreferencesManager.getDigest1(MyProfileCareerObj.this),MySharedPreferencesManager.getDigest2(MyProfileCareerObj.this));

                new SaveCareerObj2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } catch (Exception e) {
            }
        }

    }
    class SaveCareerObj2 extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("c",encobj));

            json = jParser.makeHttpRequest(Z.url_savecareerobj, "GET", params);
            try {
                r = json.getString("info");

            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(MyProfileCareerObj.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();

                if(role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                s.setCareerobj(careerobj);


                MyProfileCareerObj.super.onBackPressed();
            }
            else
                Toast.makeText(MyProfileCareerObj.this, "Try again !", Toast.LENGTH_SHORT).show();


        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                validateandSave();
                break;

            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);


    }
    @Override
    public void onBackPressed() {

        if(edittedFlag==1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfileCareerObj.super.onBackPressed();
                                }
                            })

                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileCareerObj.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileCareerObj.this));

                }
            });

            alertDialog.show();
        }
        else
            MyProfileCareerObj.super.onBackPressed();
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
