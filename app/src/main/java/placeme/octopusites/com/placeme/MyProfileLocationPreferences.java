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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.MyProfileLocationModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class MyProfileLocationPreferences extends AppCompatActivity {
    int locationcount=0;
    View addmorelocation;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    //    private static String url_savelocationpreferences= "http://192.168.100.100/AESTest/SaveLocationPreferences";
    View trash1selectionview,trash2selectionview,trash3selectionview,trash4selectionview,trash5selectionview;
    int edittedFlag=0;;
    int d=0;
    StudentData s=new StudentData();
    EditText location1,location2,location3,location4,location5;
    String slocation1,slocation2,slocation3,slocation4,slocation5,encobj="";
    String enclocation1,enclocation2,enclocation3,enclocation4,enclocation5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_location_preferences);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Location Preferences");
        ab.setDisplayHomeAsUpEnabled(true);

        trash1selectionview=(View)findViewById(R.id.trash1selectionview);
        trash2selectionview=(View)findViewById(R.id.trash2selectionview);
        trash3selectionview=(View)findViewById(R.id.trash3selectionview);
        trash4selectionview=(View)findViewById(R.id.trash4selectionview);
        trash5selectionview=(View)findViewById(R.id.trash5selectionview);


        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=1;
                showDeletDialog();
            }
        });
        trash2selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=2;
                showDeletDialog();

            }
        });
        trash3selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=3;
                showDeletDialog();
            }
        });
        trash4selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=4;
                showDeletDialog();
            }
        });
        trash5selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=5;
                showDeletDialog();
            }
        });

        location1=(EditText)findViewById(R.id.location1);
        location2=(EditText)findViewById(R.id.location2);
        location3=(EditText)findViewById(R.id.location3);
        location4=(EditText)findViewById(R.id.location4);
        location5=(EditText)findViewById(R.id.location5);

        location1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                location1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        location2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                location2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        location3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                location3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        location4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                location4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        location5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                location5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        TextView locationtxt=(TextView)findViewById(R.id.locationtxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        locationtxt.setTypeface(custom_font1);

        addmorelocation=(View)findViewById(R.id.addmorelocation);
        addmorelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(locationcount==0)
                {
                    if (location1.getText().toString() != null) {
                        if (!location1.getText().toString().equals("")) {

                            View v=(View)findViewById(R.id.locationline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.locationrl2);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            locationcount++;
                        } else
                            Toast.makeText(MyProfileLocationPreferences.this, "Please Enter First Location", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileLocationPreferences.this, "Please Enter First Location", Toast.LENGTH_SHORT).show();



                }
                else if(locationcount==1)
                {
                    if (location2.getText().toString() != null) {
                        if (!location2.getText().toString().equals("")) {
                            View v=(View)findViewById(R.id.locationline2);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.locationrl3);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            locationcount++;

                        } else
                            Toast.makeText(MyProfileLocationPreferences.this, "Please Enter Second Location", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileLocationPreferences.this, "Please Enter Second Location", Toast.LENGTH_SHORT).show();

                }
                else if(locationcount==2)
                {
                    if (location3.getText().toString() != null) {
                        if (!location3.getText().toString().equals("")) {

                            View v=(View)findViewById(R.id.locationline3);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.locationrl4);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            locationcount++;
                        } else
                            Toast.makeText(MyProfileLocationPreferences.this, "Please Enter Third Location", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileLocationPreferences.this, "Please Enter First Third", Toast.LENGTH_SHORT).show();

                }
                else  if(locationcount==3)
                {

                    if (location4.getText().toString() != null) {
                        if (!location4.getText().toString().equals("")) {

                            View v=(View)findViewById(R.id.locationline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.locationrl5);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            locationcount++;

                            TextView t=(TextView)findViewById(R.id.addmorelocationtxt);
                            ImageView i=(ImageView)findViewById(R.id.addmorelocationimg);
                            addmorelocation.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);
                        } else
                            Toast.makeText(MyProfileLocationPreferences.this, "Please Enter Fifth Location", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileLocationPreferences.this, "Please Enter Fifth Location", Toast.LENGTH_SHORT).show();


                }

            }
        });

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

        slocation1=s.getLocation1();
        slocation2=s.getLocation2();
        slocation3=s.getLocation3();
        slocation4=s.getLocation4();
        slocation5=s.getLocation5();

        if(slocation1!=null) {
            if (slocation1.length() > 2) {
                location1.setText(slocation1);
            }
        }
        if(slocation2!=null) {
            if (slocation2.length() > 2) {
                location2.setText(slocation2);

                View v = (View) findViewById(R.id.locationline1);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl2);
                relativeLayout1.setVisibility(View.VISIBLE);
                locationcount++;
            }
        }
        if(slocation3!=null) {
            if (slocation3.length() > 2) {
                location3.setText(slocation3);

                View v = (View) findViewById(R.id.locationline2);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl3);
                relativeLayout1.setVisibility(View.VISIBLE);
                locationcount++;
            }
        }
        if(slocation4!=null) {
            if (slocation4.length() > 2) {
                location4.setText(slocation4);

                View v = (View) findViewById(R.id.locationline3);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl4);
                relativeLayout1.setVisibility(View.VISIBLE);
                locationcount++;
            }
        }
        if(slocation5!=null) {
            if (slocation5.length() > 2) {
                location5.setText(slocation5);

                View v = (View) findViewById(R.id.locationline4);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl5);
                relativeLayout1.setVisibility(View.VISIBLE);
                locationcount++;
            }
        }


        edittedFlag=0;
    }
    void showDeletDialog()
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this patent ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag=1;
                                deleteLocation();
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
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
    void deleteLocation()
    {
        View v = (View) findViewById(R.id.locationline4);
        if (v.getVisibility() == View.VISIBLE) {
            View v1=(View)findViewById(R.id.locationline4);
            v1.setVisibility(View.GONE);

            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.locationrl5);
            relativeLayout1.setVisibility(View.GONE);
            locationcount--;

            TextView t=(TextView)findViewById(R.id.addmorelocationtxt);
            ImageView i=(ImageView)findViewById(R.id.addmorelocationimg);
            addmorelocation.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);


        }
        else
        {
            v = (View) findViewById(R.id.locationline3);
            if (v.getVisibility() == View.VISIBLE) {
                View v1=(View)findViewById(R.id.locationline3);
                v1.setVisibility(View.GONE);

                RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.locationrl4);
                relativeLayout1.setVisibility(View.GONE);
                locationcount--;

            }
            else
            {
                v = (View) findViewById(R.id.locationline2);
                if (v.getVisibility() == View.VISIBLE) {
                    View v1=(View)findViewById(R.id.locationline2);
                    v1.setVisibility(View.GONE);

                    RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.locationrl3);
                    relativeLayout1.setVisibility(View.GONE);
                    locationcount--;

                }
                else
                {
                    v = (View) findViewById(R.id.locationline1);
                    if (v.getVisibility() == View.VISIBLE) {
                        View v1=(View)findViewById(R.id.locationline1);
                        v1.setVisibility(View.GONE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.locationrl2);
                        relativeLayout1.setVisibility(View.GONE);
                        locationcount--;

                    }
                    else
                    {
                        location1.setText("");
                    }
                }
            }

        }
        if(d==5)
        {
            location5.setText("");
        }
        else if(d==4)
        {
            slocation5=location5.getText().toString();


            slocation4 = slocation5;
            location4.setText(slocation4);
            location5.setText("");

        }
        else if(d==3)
        {
            slocation5=location5.getText().toString();
            slocation4=location4.getText().toString();


            slocation3 = slocation4;
            location3.setText(slocation3);
            location4.setText("");

            slocation4 = slocation5;
            location4.setText(slocation4);
            location5.setText("");
        }
        else if(d==2)
        {
            slocation5=location5.getText().toString();
            slocation4=location4.getText().toString();
            slocation3=location3.getText().toString();


            slocation2 = slocation3;
            location2.setText(slocation2);
            location3.setText("");

            slocation3 = slocation4;
            location3.setText(slocation3);
            location4.setText("");

            slocation4 = slocation5;
            location4.setText(slocation4);
            location5.setText("");

        }
        else if(d==1)
        {
            slocation5=location5.getText().toString();
            slocation4=location4.getText().toString();
            slocation3=location3.getText().toString();
            slocation2=location2.getText().toString();


            slocation1 = slocation2;
            location1.setText(slocation1);
            location2.setText("");

            slocation2 = slocation3;
            location2.setText(slocation2);
            location3.setText("");

            slocation3 = slocation4;
            location3.setText(slocation3);
            location4.setText("");

            slocation4 = slocation5;
            location4.setText(slocation4);
            location5.setText("");
        }
    }
    void validateandSave()
    {
        location1.setError(null);
        location2.setError(null);
        location3.setError(null);
        location4.setError(null);
        location5.setError(null);


        slocation1=location1.getText().toString();
        slocation2=location2.getText().toString();
        slocation3=location3.getText().toString();
        slocation4=location4.getText().toString();
        slocation5=location5.getText().toString();

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag=0;

        if(locationcount==0)
        {
            if(slocation1.length()<2)
            {
                errorflag=1;
                location1.setError("Invalid location");
            }
        }
        else if(locationcount==1)
        {
            if(slocation1.length()<2)
            {
                errorflag=1;
                location1.setError("Invalid location");
            }
            else
            {
                errorflag=0;
                if(slocation2.length()<2)
                {
                    errorflag=1;
                    location2.setError("Invalid location");
                }
            }
        }
        else if(locationcount==2)
        {
            if(slocation1.length()<2)
            {
                errorflag=1;
                location1.setError("Invalid location");
            }
            else
            {
                errorflag=0;
                if(slocation2.length()<2)
                {
                    errorflag=1;
                    location2.setError("Invalid location");
                }
                else
                {
                    errorflag=0;
                    if(slocation3.length()<2)
                    {
                        errorflag=1;
                        location3.setError("Invalid location");
                    }
                }
            }
        }
        else if(locationcount==3)
        {
            if(slocation1.length()<2)
            {
                errorflag=1;
                location1.setError("Invalid location");
            }
            else
            {
                errorflag=0;
                if(slocation2.length()<2)
                {
                    errorflag=1;
                    location2.setError("Invalid location");
                }
                else
                {
                    errorflag=0;
                    if(slocation3.length()<2)
                    {
                        errorflag=1;
                        location3.setError("Invalid location");
                    }
                    else
                    {
                        errorflag=0;
                        if(slocation4.length()<2)
                        {
                            errorflag=1;
                            location4.setError("Invalid location");
                        }
                    }
                }
            }
        }
        else if(locationcount==4)
        {
            if(slocation1.length()<2)
            {
                errorflag=1;
                location1.setError("Invalid location");
            }
            else
            {
                errorflag=0;
                if(slocation2.length()<2)
                {
                    errorflag=1;
                    location2.setError("Invalid location");
                }
                else
                {
                    errorflag=0;
                    if(slocation3.length()<2)
                    {
                        errorflag=1;
                        location3.setError("Invalid location");
                    }
                    else
                    {
                        errorflag=0;
                        if(slocation4.length()<2)
                        {
                            errorflag=1;
                            location4.setError("Invalid location");
                        }
                        else
                        {
                            errorflag=0;
                            if(slocation5.length()<2)
                            {
                                errorflag=1;
                                location5.setError("Invalid location");
                            }
                        }
                    }
                }
            }
        }

        if(errorflag==0) {
            try
            {

                MyProfileLocationModal obj2 = new MyProfileLocationModal(slocation1,slocation2,slocation3,slocation4,slocation5);

                encobj =OtoString(obj2,MySharedPreferencesManager.getDigest1(MyProfileLocationPreferences.this),MySharedPreferencesManager.getDigest2(MyProfileLocationPreferences.this));
                Log.d("TAG", "validateandSave: encobj - "+encobj);

                new SaveLocations().execute();


            }catch (Exception e){Toast.makeText(MyProfileLocationPreferences.this,e.getMessage(),Toast.LENGTH_LONG).show();}
        }
    }
    class SaveLocations extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("l1",encobj));  //1

            json = jParser.makeHttpRequest(MyConstants.url_savelocationpreferences, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(MyProfileLocationPreferences.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();


                ProfileRole r=new ProfileRole();
                String role=r.getRole();
                if(role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                s.setLocation1(slocation1);
                s.setLocation2(slocation2);
                s.setLocation3(slocation3);
                s.setLocation4(slocation4);
                s.setLocation5(slocation5);

                MyProfileLocationPreferences.super.onBackPressed();
            }
            else
                Toast.makeText(MyProfileLocationPreferences.this,result,Toast.LENGTH_SHORT).show();

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
        if(edittedFlag!=0) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfileLocationPreferences.super.onBackPressed();
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
        }else
            MyProfileLocationPreferences.super.onBackPressed();
    }

}
