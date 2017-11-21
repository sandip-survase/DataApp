package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.MyProfileCareerObjModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class MyProfileCareerObj extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    //    private static String url_savecareerobj= "http://192.168.100.100/AESTest/SaveCareerObj";
    int edittedFlag=0,currentsetFlag=1;
    RadioGroup radioGroupCareerobj;
    RadioButton radioButtonObj1,radioButtonObj2,radioButtonObj3,radioButtonObj4,radioButtonObj5,radioButtonObj6;
    EditText otherobj;
    String careerobj="To secure a promising position in the company of repute that offers both a challenging assignments and ample opportunity for growth in terms of due recognition and added responsibilities.";
    String enccareerobj,encobj="";
    int otherFlag=0;
    StudentData s=new StudentData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_career_obj);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Career Objective");
        ab.setDisplayHomeAsUpEnabled(true);

        otherobj=(EditText)findViewById(R.id.otherobj);

        radioGroupCareerobj=(RadioGroup)findViewById(R.id.radioGroupCareerobj);

        radioButtonObj1=(RadioButton)findViewById(R.id.radioButtonObj1);
        radioButtonObj2=(RadioButton)findViewById(R.id.radioButtonObj2);
        radioButtonObj3=(RadioButton)findViewById(R.id.radioButtonObj3);
        radioButtonObj4=(RadioButton)findViewById(R.id.radioButtonObj4);
        radioButtonObj5=(RadioButton)findViewById(R.id.radioButtonObj5);
        radioButtonObj6=(RadioButton)findViewById(R.id.radioButtonObj6);

        radioGroupCareerobj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonObj1:
                        otherFlag=0;
                        otherobj.setText("");
                        otherobj.setFocusable(false);
                        otherobj.setFocusableInTouchMode(false);
                        otherobj.setClickable(false);
                        careerobj="To secure a promising position in the company of repute that offers both a challenging assignments and ample opportunity for growth in terms of due recognition and added responsibilities.";
                        if(currentsetFlag!=1)
                            edittedFlag=1;
                        break;
                    case R.id.radioButtonObj2:
                        otherFlag=0;
                        otherobj.setText("");
                        otherobj.setFocusable(false);
                        otherobj.setFocusableInTouchMode(false);
                        otherobj.setClickable(false);
                        careerobj="To work with the company, belonging to professionally managed group, that is offering enough opportunities for career advancement and professional growth of an individual.";
                        if(currentsetFlag!=2)
                            edittedFlag=1;
                        break;
                    case R.id.radioButtonObj3:
                        otherFlag=0;
                        otherobj.setText("");
                        otherobj.setFocusable(false);
                        otherobj.setFocusableInTouchMode(false);
                        otherobj.setClickable(false);
                        careerobj="To work in a stimulating work culture and environment where I am allowed to freely apply the management principles learned to the best interest of the company and gain rich experience thereby enhancing my knowledge.";
                        if(currentsetFlag!=3)
                            edittedFlag=1;
                        break;
                    case R.id.radioButtonObj4:
                        otherFlag=0;
                        otherobj.setText("");
                        otherobj.setFocusable(false);
                        otherobj.setFocusableInTouchMode(false);
                        otherobj.setClickable(false);
                        careerobj="To achieve success in competitive environment of the corporate world and making a mark by my hard work, passion for the job and strong will to fulfill management expectations following ethical values.";
                        if(currentsetFlag!=4)
                            edittedFlag=1;
                        break;
                    case R.id.radioButtonObj5:
                        otherFlag=0;
                        otherobj.setText("");
                        otherobj.setFocusable(false);
                        otherobj.setFocusableInTouchMode(false);
                        otherobj.setClickable(false);
                        careerobj="To represent myself as one of the important members of the team and driving my team towards fulfillment of company's goals and objectives and make long lasting impression of my team on senior management.";
                        if(currentsetFlag!=5)
                            edittedFlag=1;
                        break;
                    case R.id.radioButtonObj6:
                        otherFlag=1;
                        otherobj.setFocusable(true);
                        otherobj.setFocusableInTouchMode(true);
                        otherobj.setClickable(true);
                        if(currentsetFlag!=6)
                            edittedFlag=1;
                        break;
                }
            }
        });

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);



        TextView careerobjtxt=(TextView)findViewById(R.id.careerobjtxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        careerobjtxt.setTypeface(custom_font1);

        sharedpreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);
        String role=sharedpreferences.getString("role",null);

        ProfileRole r=new ProfileRole();
        r.setUsername(username);
        r.setRole(role);

        Digest d=new Digest();
        digest1=d.getDigest1();
        digest2=d.getDigest2();

        if(digest1==null||digest2==null) {
            digest1 = sharedpreferences.getString("digest1", null);
            digest2 = sharedpreferences.getString("digest2", null);
            d.setDigest1(digest1);
            d.setDigest2(digest2);
        }


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
                otherobj.setError("Invalid Objective");
            }

        }
        if(errorflag==0)
        {
            try {

                MyProfileCareerObjModal obj2 = new MyProfileCareerObjModal(careerobj);;

                encobj =OtoString(obj2,MySharedPreferencesManager.getDigest1(MyProfileCareerObj.this),MySharedPreferencesManager.getDigest2(MyProfileCareerObj.this));
                Log.d("TAG", "validateandSave: encobj - "+encobj);


                new SaveCareerObj().execute();

            }catch (Exception e){Toast.makeText(MyProfileCareerObj.this,e.getMessage(),Toast.LENGTH_LONG).show();}
        }

    }
    class SaveCareerObj extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("c",encobj));
            //1
            json = jParser.makeHttpRequest(MyConstants.url_savecareerobj, "GET", params);
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

                ProfileRole r=new ProfileRole();
                String role=r.getRole();
                if(role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                s.setCareerobj(careerobj);


                MyProfileCareerObj.super.onBackPressed();
            }
            else
                Toast.makeText(MyProfileCareerObj.this,result,Toast.LENGTH_SHORT).show();

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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
                }
            });

            alertDialog.show();
        }
        else
            MyProfileCareerObj.super.onBackPressed();
    }
}
