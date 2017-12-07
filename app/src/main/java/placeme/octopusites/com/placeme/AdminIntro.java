package placeme.octopusites.com.placeme;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
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

import placeme.octopusites.com.placeme.modal.AdminInstituteModal;
import placeme.octopusites.com.placeme.modal.AdminIntroModal;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

import static placeme.octopusites.com.placeme.AES4all.fromString;
import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class AdminIntro extends AppCompatActivity {

    int errorflagfirstname=0;
    String digest1,digest2;
    AutoCompleteTextView citystaecountry;
    ArrayList<String> listAll=new ArrayList<String>();
    EditText fname,lname,role,email,inst;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    private String username="";
    TextInputLayout fnameTextInputLayout, lnameTextInputLayout, roleinputlayout,instinputlayout, emailinputlayout, citystaecountryinputlayout;


    private static String url_getcountries = "http://192.168.100.100/AESTest/GetCountries";
    private static String url_getstates= "http://192.168.100.100/AESTest/GetStates";
    private static String url_getcities= "http://192.168.100.100/AESTest/GetCities";




    int countrycount=0,statecount=0,citycount=0;
    String firstname="",lastname="",instname="",CityStateCountry="";
    String oldCountry="",oldState="",oldCity="";
    String countries[],states[],cities[];
    Spinner country,state,city;
    List<String> countrieslist = new ArrayList<String>();
    List<String> stateslist = new ArrayList<String>();
    List<String> citieslist = new ArrayList<String>();
    String selectedCountry="",selectedState="",selectedCity="";
    String encUsername,encRole,encFname,encLname,encCountry,encState,encCity,encInst;

    //ssss
    AdminData a = new AdminData();
    int edittedFlag=0,isCountrySet=0,isStateSet=0,isCitySet=0;

    AdminIntroModal obj;
    String strobj="",encobj="";

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_intro);

        digest1=MySharedPreferencesManager.getDigest1(this);
        digest2=MySharedPreferencesManager.getDigest2(this);


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Personal Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        TextView loctxt=(TextView)findViewById(R.id.loctxt);
        loctxt.setTypeface(MyConstants.getBold(this));
//        country=(Spinner)findViewById(R.id.country);
//        state=(Spinner)findViewById(R.id.state);
//        city=(Spinner)findViewById(R.id.city);

//        new GetCountries().execute();


        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        role=(EditText)findViewById(R.id.role);
        email=(EditText)findViewById(R.id.email);
        inst=(EditText)findViewById(R.id.inst);

        citystaecountry=(AutoCompleteTextView)findViewById(R.id.citystaecountry);

        fnameTextInputLayout = (TextInputLayout) findViewById(R.id.fnameTextInputLayout);
        lnameTextInputLayout = (TextInputLayout) findViewById(R.id.lnameTextInputLayout);
        roleinputlayout = (TextInputLayout) findViewById(R.id.roleinputlayout);
        emailinputlayout = (TextInputLayout) findViewById(R.id.emailinputlayout);
        instinputlayout = (TextInputLayout) findViewById(R.id.instinputlayout);
        citystaecountryinputlayout = (TextInputLayout) findViewById(R.id.citystaecountryinputlayout);

        fname.setTypeface(MyConstants.getBold(this));
        lname.setTypeface(MyConstants.getBold(this));
        role.setTypeface(MyConstants.getBold(this));
        email.setTypeface(MyConstants.getBold(this));
        inst.setTypeface(MyConstants.getBold(this));
        citystaecountry.setTypeface(MyConstants.getBold(this));

        fnameTextInputLayout.setTypeface(MyConstants.getLight(this));
        lnameTextInputLayout.setTypeface(MyConstants.getLight(this));
        roleinputlayout.setTypeface(MyConstants.getLight(this));
        emailinputlayout.setTypeface(MyConstants.getLight(this));
        instinputlayout.setTypeface(MyConstants.getLight(this));
        citystaecountryinputlayout.setTypeface(MyConstants.getLight(this));

        firstname=a.getFname();
        lastname=a.getLname();
        instname = a.getInstitute();
        selectedCountry = a.getCountry();
        selectedState = a.getState();
        selectedCity = a.getCity();

        if(firstname!=null)
            fname.setText(firstname);
        else
            firstname="";

        if(lastname!=null)
            lname.setText(lastname);
        else
            lastname="";

        if (instname != null)
            inst.setText(instname);
        else
            instname="";



        try{
            role.setText(MySharedPreferencesManager.getRole(this));
            email.setText(Decrypt(MySharedPreferencesManager.getUsername(this),digest1,digest2));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        encUsername=MySharedPreferencesManager.getUsername(this);
        if(selectedCountry!=null && selectedState!=null && selectedCity!=null)
        {
            if(!selectedCountry.equals("") && !selectedState.equals("") && !selectedCity.equals("")) {
                CityStateCountry = selectedCity + " , " + selectedState + " , " + selectedCountry;
                citystaecountry.setText(CityStateCountry);
            }
            else
                citystaecountry.setText("City/District");

        }
        else
            citystaecountry.setText("City/District");

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
                citystaecountryinputlayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fnameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lnameTextInputLayout.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


        });
        inst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                instinputlayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        edittedFlag=0;

    }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:

                validateandSave();
                //Toast.makeText(getBaseContext(),"clicked", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                onBackPressed();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
    @Override
    public void onBackPressed() {
        if (!oldCountry.equals(selectedCountry) || !oldState.equals(selectedState) || !oldCity.equals(selectedCity) || edittedFlag == 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AdminIntro.super.onBackPressed();
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
            AdminIntro.super.onBackPressed();

    }
    void validateandSave()
    {

        int errorflag1=0,errorflag2=0,errorflag3=0,errorflag4=0, errorflag5=0;

        firstname = fname.getText().toString();
        lastname = lname.getText().toString();
        instname = inst.getText().toString();
        CityStateCountry = citystaecountry.getText().toString();

        if (firstname.length() < 2) {
            fnameTextInputLayout.setError("Kindly enter valid first name");
            errorflag1 = 1;
        } else {
            fnameTextInputLayout.setError(null);
            if (lastname.length() < 2) {
                lnameTextInputLayout.setError("Kindly enter valid last name");
                errorflag2 = 1;
            } else {
                lnameTextInputLayout.setError(null);
                if (instname.length() < 2) {
                    instinputlayout.setError("Kindly enter valid institute name");
                    errorflag3 = 1;
                } else {
                    instinputlayout.setError(null);

                    if(CityStateCountry.length()<2)
                    {
                        citystaecountryinputlayout.setError("Please select your city");
                        errorflagfirstname=1;
                    }
                    else
                    {
                        citystaecountryinputlayout.setError(null);
                        String[] parts = CityStateCountry.split(" , ");
                        if(parts.length==3) {
                            selectedCity = parts[0];
                            selectedState = parts[1];
                            selectedCountry = parts[2];
                        }
                    }

//                if (selectedCountry.contains("Select")) {
//                    Toast.makeText(AdminIntro.this, "Select Country", Toast.LENGTH_SHORT).show();
//                    errorflag1 = 1;
//                } else {
//                    if (selectedState.contains("Select")) {
//                        Toast.makeText(AdminIntro.this, "Select State", Toast.LENGTH_SHORT).show();
//                        errorflag2 = 1;
//                    } else if (selectedCity.contains("Select")) {
//                        Toast.makeText(AdminIntro.this, "Select City", Toast.LENGTH_SHORT).show();
//                        errorflag3 = 1;
//                    }
//                }

                }
            }

        }


        if (errorflagfirstname == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag1 == 0  ) {
            try {
//
                String mname = a.getMname();
                String phone = a.getPhone();

                Log.d("TAG", "mname: - "+mname);
                Log.d("TAG", "phone: - "+phone);

                obj = new AdminIntroModal(firstname,mname,lastname,selectedCountry,selectedState,selectedCity,phone,instname);
                try{
                    strobj =OtoString(obj,digest1,digest2);
                    Log.d("encstrobj", "strobj: "+strobj);

                }
                catch (Exception e){
                    Log.d("TAG", "validateandSave: - "+e.getMessage());
                }


                new SaveData().execute();

            } catch (Exception e) {
                Log.d("TAG", "validateandSave: - "+e.getMessage());

            }
        }

    }


    class SaveData extends AsyncTask<String, String, String> {





        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",encUsername));    //0
            Log.d("TAG", "encUsername: "+encUsername);
            params.add(new BasicNameValuePair("obj",strobj));       //1
            Log.d("TAG", "strobj: "+encobj);
            json = jParser.makeHttpRequest(MyConstants.url_SaveAdminIntro, "GET", params);
            try {
                r = json.getString("info");
                Log.d("TAG", " result r:-: "+r);

            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                MySharedPreferencesManager.save(AdminIntro.this,"institute",instname);
                Toast.makeText(AdminIntro.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();

                if(edittedFlag==1){
                    setResult(111);
                }
                AdminIntro.super.onBackPressed();
            }
            else {
                Toast.makeText(AdminIntro.this,result,Toast.LENGTH_SHORT).show();
            }
        }
    }



}
