package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;




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
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import placeme.octopusites.com.placeme.modal.AlumniIntroModal;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.MyProfileAlumniFragment.alumniLog;


public class AlumniIntro extends AppCompatActivity {
    String digest1,digest2;
    EditText fname,lname,role,email;
    JSONObject json;
    AutoCompleteTextView citystaecountry;
    JSONParser jParser = new JSONParser();
    ArrayList<String> listAll=new ArrayList<String>();

    int countrycount=0,statecount=0,citycount=0;
    String firstname="",lastname="",encobj="";
    String oldCountry="",oldState="",oldCity="",CityStateCountry="";
    String countries[],states[],cities[];
    //        Spinner country,state,city;
    List<String> countrieslist = new ArrayList<String>();
    List<String> stateslist = new ArrayList<String>();
    List<String> citieslist = new ArrayList<String>();
    String selectedCountry="",selectedState="",selectedCity="";
    String encUsername,encRole,encFname,encLname,encCountry,encState,encCity;
    int edittedFlag=0,isCountrySet=0,isStateSet=0,isCitySet=0;
    //    AlumniData alumniDataSet = new AlumniData();
    StudentData s = new StudentData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_intro);


        Digest d=new Digest();
        digest1=d.getDigest1();
        digest2=d.getDigest2();


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Personal Info");
        ab.setDisplayHomeAsUpEnabled(true);


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);



        TextView loctxt=(TextView)findViewById(R.id.loctxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        loctxt.setTypeface(custom_font1);

        ScrollView myprofileintroscrollview=(ScrollView)findViewById(R.id.myprofileintroscrollview);
        disableScrollbars(myprofileintroscrollview);

//            country=(Spinner)findViewById(R.id.country);
//            state=(Spinner)findViewById(R.id.state);
//            city=(Spinner)findViewById(R.id.city);

//            new GetCountries().execute();

        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        role=(EditText)findViewById(R.id.role);
        email=(EditText)findViewById(R.id.email);
        citystaecountry=(AutoCompleteTextView)findViewById(R.id.citystaecountry);

        firstname=s.getFname();
        lastname=s.getLname();
        selectedCountry = s.getCountry();
        selectedState = s.getState();
        selectedCity = s.getCity();


        if(selectedCountry!=null && selectedState!=null && selectedCity!=null)
        {
            CityStateCountry = selectedCity+" , "+selectedState+" , "+selectedCountry;
            citystaecountry.setText(CityStateCountry);
        }


        if (firstname != null) {

            if (firstname.length() > 1)
                fname.setText(firstname);
        }

        if (lastname != null) {
            if (lastname.length() > 1)
                lname.setText(lastname);
        }


        try
        {
            JSONObject jsonObject=new JSONObject(getJson());
            JSONArray array=jsonObject.getJSONArray("array");
            for(int i=0;i<array.length();i++)
            {

                JSONObject object=array.getJSONObject(i);
                String city=object.getString("city");
                String state=object.getString("state");
                String country=object.getString("country");


                listAll.add(city+" , "+state+" , "+country);

            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,listAll);
        citystaecountry.setAdapter(adapter);

        citystaecountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        ProfileRole r=new ProfileRole();
        role.setText(r.getRole().substring(0, 1).toUpperCase()+ r.getRole().substring(1));
        email.setText(r.getPlainusername());
        encUsername=r.getUsername();

        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fname.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


        });
        lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lname.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


        });

//            edittedFlag=0;

    }//oncreate();

    public String getJson()
    {
        String json=null;
        try
        {
            InputStream is = getAssets().open("citystatecountrydb/array.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return json;
        }
        return json;
    }
    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }

    void validateandSave()
    {
        int errorflag1=0,errorflag2=0,errorflag3=0,errorflag4=0;

        firstname=fname.getText().toString();
        lastname=lname.getText().toString();
        CityStateCountry = citystaecountry.getText().toString();


        if(CityStateCountry.length()<2)
        {
            citystaecountry.setError("Incorrect City Name");
            errorflag4=1;
        }
        else
        {
            citystaecountry.setError(null);

            String[] parts = CityStateCountry.split(" , ");
            if(parts.length==3) {
                selectedCity = parts[0];
                selectedState = parts[1];
                selectedCountry = parts[2];
            }
        }

        if(firstname.length()<2)
        {
            fname.setError("Incorrect First Name");
            errorflag4=1;
        }
        else {
            fname.setError(null);
        }

        if(errorflag1==0&&errorflag2==0&&errorflag3==0&&errorflag4==0)
        {
            try
            {
                AlumniIntroModal obj2 = new AlumniIntroModal(firstname, lastname,selectedCity,selectedState,selectedCountry);;

                encobj =OtoString(obj2,MySharedPreferencesManager.getDigest1(AlumniIntro.this),MySharedPreferencesManager.getDigest2(AlumniIntro.this));
                Log.d("TAG", "validateandSave: encobj - "+encobj);




//                byte[] demoKeyBytes=SimpleBase64Encoder.decode(digest1);
//                byte[] demoIVBytes=SimpleBase64Encoder.decode(digest2);
//                String sPadding = "ISO10126Padding";
//
//                byte[] roleBytes = role.getText().toString().getBytes("UTF-8");
//                byte[] fnameBytes = firstname.getBytes("UTF-8");
//                byte[] lnameBytes = lastname.getBytes("UTF-8");
//                byte[] countryBytes = selectedCountry.getBytes("UTF-8");
//                byte[] stateBytes = selectedState.getBytes("UTF-8");
//                byte[] cityBytes = selectedCity.getBytes("UTF-8");
//
//                byte[] roleEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, roleBytes);
//                encRole=new String(SimpleBase64Encoder.encode(roleEncryptedBytes));
//
//                byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fnameBytes);
//                encFname=new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));
//
//                byte[] lnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, lnameBytes);
//                encLname=new String(SimpleBase64Encoder.encode(lnameEncryptedBytes));
//
//                byte[] countryEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, countryBytes);
//                encCountry=new String(SimpleBase64Encoder.encode(countryEncryptedBytes));
//
//                byte[] stateEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, stateBytes);
//                encState=new String(SimpleBase64Encoder.encode(stateEncryptedBytes));
//
//                byte[] cityEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, cityBytes);
//                encCity=new String(SimpleBase64Encoder.encode(cityEncryptedBytes));

                new SaveData().execute();

            }catch (Exception e){

            }
        }

    }

    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",encUsername));//0
            Log.d("TAG", "encUsername: "+encUsername);
            params.add(new BasicNameValuePair("ci",encobj));       //1
            Log.d("TAG", "encobj: "+encobj);


            json = jParser.makeHttpRequest(MyConstants.url_save_intro_data, "GET", params);

            try {

                Log.d("TAG", "doInBackground: welcome to dobackground");
                r = json.getString("info");

                String user  = json.getString("username");
                Log.d("TAG", "doInBackground: "+user);

            }catch (Exception e){e.printStackTrace();}
            return r;

        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(AlumniIntro.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();

                setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                s.setFname(firstname);
                s.setLname(lastname);
                s.setCountry(selectedCountry);
                s.setState(selectedState);
                s.setCity(selectedCity);

                AlumniIntro.super.onBackPressed();
            }
            else
            {
                Toast.makeText(AlumniIntro.this,"try again..!",Toast.LENGTH_SHORT).show();
            }
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


        if(edittedFlag==1)
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AlumniIntro.super.onBackPressed();
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
            AlumniIntro.super.onBackPressed();
    }
}
