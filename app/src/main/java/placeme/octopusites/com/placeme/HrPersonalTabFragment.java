package placeme.octopusites.com.placeme;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;


/**
 * A simple {@link Fragment} subclass.
 */
public class HrPersonalTabFragment extends Fragment {


    private EditText fname, lname, role, email, designation;
    private Spinner country, state, city;
    AutoCompleteTextView citystaecountry;
    ArrayList<String> listAll=new ArrayList<String>();
//    Button savePersonal;
//    ProgressBar personalprogress;

    private String plainusername = "";
    private String digest1, digest2;
    public static final String USERNAME = "nameKey";
    public static final String MyPREFERENCES = "MyPrefs";

    JSONObject json;
    JSONParser jParser = new JSONParser();

    int countrycount = 0, statecount = 0, citycount = 0;
    int persnolerrorflag = 0,edittedFlagp=0, isCountrySet = 0, isStateSet = 0, isCitySet = 0;

    String oldCountry = "", oldState = "", oldCity = "";
    String selectedCountry = "", selectedState = "", selectedCity = "";
    String firstname = "", lastname = "", designationValue = "",CityStateCountry="";
    String userName, roleValue;
    String encUsername, encRole, encFname, encLname, encCountry, encState, encCity, encdesignation;


    String countries[], states[], cities[];

    List<String> countrieslist = new ArrayList<String>();
    List<String> stateslist = new ArrayList<String>();
    List<String> citieslist = new ArrayList<String>();


    private static String url_getcountries = "http://192.168.100.100/AESTest/GetCountries";
    private static String url_getstates = "http://192.168.100.100/AESTest/GetStates";
    private static String url_getcities = "http://192.168.100.100/AESTest/GetCities";
//    private static String url_savedata = "http://192.168.100.10/AESTest/SaveHrIntro1";

    HrData hr = new HrData();
    private SharedPreferences sharedPreferences;


    public HrPersonalTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_hr_personal_tab, container, false);


        TextView loctxt = (TextView) rootView.findViewById(R.id.loctxt);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/arba.ttf");
        loctxt.setTypeface(custom_font1);


//        ActionBar ab = getSupportActionBar();
//        ab.setTitle("Edit Personal Info");
//        ab.setDisplayHomeAsUpEnabled(true);
//
//        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
//        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);
//
//        Window window = HrIntro.this.getWindow();
//
//        int sdklevel = Integer.valueOf(android.os.Build.VERSION.SDK);
//        if (sdklevel >= 21) {
//
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(HrIntro.this.getResources().getColor(R.color.background));
//        }
//        TextView loctxt = (TextView) findViewById(R.id.loctxt);
//        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/arba.ttf");
//        loctxt.setTypeface(custom_font1);

        //-----------------

        Digest d = new Digest();
        digest1 = d.getDigest1();
        digest2 = d.getDigest2();

        fname = (EditText) rootView.findViewById(R.id.fname);
        lname = (EditText) rootView.findViewById(R.id.sname);
        role = (EditText) rootView.findViewById(R.id.role);
        email = (EditText) rootView.findViewById(R.id.email);
        designation = (EditText) rootView.findViewById(R.id.inst);
//        savePersonal = (Button) rootView.findViewById(R.id.savepersonal);
//        personalprogress= (ProgressBar) rootView.findViewById(R.id.personalprogress);
        citystaecountry=(AutoCompleteTextView)rootView.findViewById(R.id.citystaecountry);

//        ScrollView myprofileintroscrollview = (ScrollView) rootView.findViewById(R.id.myprofileintroscrollview);
//        disableScrollbars(myprofileintroscrollview);

        sharedPreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        userName = sharedPreferences.getString(USERNAME, null);
        encUsername = userName;
        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        try {
            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(userName);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);
            email.setText(plainusername);
        } catch (Exception e) {
        }


        roleValue = sharedPreferences.getString("role", null);
        role.setText(roleValue.toUpperCase());


//        country = (Spinner) rootView.findViewById(R.id.country);
//        state = (Spinner) rootView.findViewById(R.id.state);
//        city = (Spinner) rootView.findViewById(R.id.city);

//        new GetCountries().execute();

        selectedCountry = hr.getCountry();
        selectedState = hr.getState();
        selectedCity = hr.getCity();
        firstname = hr.getFname();
        lastname = hr.getLname();
        designationValue = hr.getDesignation();

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

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,listAll);
        citystaecountry.setAdapter(adapter);

        citystaecountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagp=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (firstname != null) {

            if (firstname.length() > 1)
                fname.setText(firstname);
        }

        if (lastname != null) {
            if (lastname.length() > 1)
                lname.setText(lastname);
        }
        if (designationValue != null) {
            if (designationValue.length() > 1)
                designation.setText(designationValue);
        }

        if(selectedCountry!=null && selectedState!=null && selectedCity!=null)
        {
            CityStateCountry = selectedCity+" , "+selectedState+" , "+selectedCountry;
            citystaecountry.setText(CityStateCountry);
        }
        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fname.setError(null);
                edittedFlagp = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lname.setError(null);
                edittedFlagp = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        designation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                designation.setError(null);
                edittedFlagp = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        savePersonal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                personalprogress.setVisibility(View.VISIBLE);
////                savePersonal.setVisibility(View.GONE);
//                validateandSave();
//            }
//        });

        edittedFlagp=0;

        return rootView;
    }//------

    public String getJson()
    {
        String json=null;
        try
        {
            InputStream is = getActivity().getAssets().open("citystatecountrydb/array.json");
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
    void validateandSave() {
        int errorflagCountry = 0, errorflagState = 0, errorflagCity = 0, errorflagfirstname = 0, errorflaglastname = 0, errorflagdesignation = 0;

        if(validate())
        {
            save();
        }
    }
    public boolean validate()
    {
        persnolerrorflag=0;

        firstname = fname.getText().toString();
        lastname = lname.getText().toString();
        designationValue = designation.getText().toString();
        CityStateCountry = citystaecountry.getText().toString();
        if(CityStateCountry.length()<2)
        {
            citystaecountry.setError("Incorrect City Name");
            persnolerrorflag=1;
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

        if (firstname.length() < 2) {
            fname.setError("Incorrect First Name");
            persnolerrorflag = 1;
            Log.d("hrlog", "validate: fname"+persnolerrorflag);
        } else if (lastname.length() < 2) {
            lname.setError("Incorrect Last Name");
            persnolerrorflag = 1;
            Log.d("hrlog", "validate: lname"+persnolerrorflag);
        } else if (designation.length() < 2) {
            designation.setError("Incorrect designation");
            persnolerrorflag = 1;
            Log.d("hrlog", "validate: desiname"+persnolerrorflag);
        }

        if(persnolerrorflag == 0)
        {
            Log.d("hrlog", "validate: if "+persnolerrorflag);
            return true;

        }
        else{
            Log.d("hrlog", "validate: if else "+persnolerrorflag);
            return false;
        }




// else {
//            //fname.setError(null);
//            if (selectedCountry.contains("Select")) {
//                Toast.makeText(getActivity(), "Select Country", Toast.LENGTH_SHORT).show();
//                persnolerrorflag = 1;
//            } else {
//                if (selectedState.contains("Select")) {
//                    Toast.makeText(getActivity(), "Select State", Toast.LENGTH_SHORT).show();
//                    persnolerrorflag = 1;
//                } else if (selectedCity.contains("Select")) {
//                    Toast.makeText(getActivity(), "Select City", Toast.LENGTH_SHORT).show();
//                    persnolerrorflag = 1;
//                }
//            }

//        }

    }

    public void save()
    {
        try {
            byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
            String sPadding = "ISO10126Padding";

            byte[] roleBytes = role.getText().toString().getBytes("UTF-8");
            byte[] fnameBytes = firstname.getBytes("UTF-8");
            byte[] lnameBytes = lastname.getBytes("UTF-8");
            byte[] designationBytes = designationValue.getBytes("UTF-8");
            byte[] countryBytes = selectedCountry.getBytes("UTF-8");
            byte[] stateBytes = selectedState.getBytes("UTF-8");
            byte[] cityBytes = selectedCity.getBytes("UTF-8");


            byte[] roleEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, roleBytes);
            encRole = new String(SimpleBase64Encoder.encode(roleEncryptedBytes));

            byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fnameBytes);
            encFname = new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));

            byte[] lnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, lnameBytes);
            encLname = new String(SimpleBase64Encoder.encode(lnameEncryptedBytes));

            byte[] designationEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, designationBytes);
            encdesignation = new String(SimpleBase64Encoder.encode(designationEncryptedBytes));

            byte[] countryEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, countryBytes);
            encCountry = new String(SimpleBase64Encoder.encode(countryEncryptedBytes));

            byte[] stateEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, stateBytes);
            encState = new String(SimpleBase64Encoder.encode(stateEncryptedBytes));

            byte[] cityEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, cityBytes);
            encCity = new String(SimpleBase64Encoder.encode(cityEncryptedBytes));

            new SaveData().execute();

        } catch (Exception e) {

        }

    }
    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("f", encFname));       //1
            params.add(new BasicNameValuePair("l", encLname));       //2
            params.add(new BasicNameValuePair("c", encCountry));     //3
            params.add(new BasicNameValuePair("s", encState));       //4
            params.add(new BasicNameValuePair("ci", encCity));       //5
            params.add(new BasicNameValuePair("d", encdesignation)); //6

            json = jParser.makeHttpRequest(MyConstants.url_savedata_SaveHrIntro1, "GET", params);
            try {
                r = json.getString("info");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {
                edittedFlagp=0;
                Toast.makeText(getActivity(), "Successfully Saved..!", Toast.LENGTH_SHORT).show();
//                this.super.onBackPressed();
            }

//            personalprogress.setVisibility(View.GONE);
//            savePersonal.setVisibility(View.VISIBLE);

            getActivity().setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
        }
    }





//    class GetCountries extends AsyncTask<String, String, String> {
//
//
//        protected String doInBackground(String... param) {
//
//
////            List<NameValuePair> params = new ArrayList<NameValuePair>();
////            json = jParser.makeHttpRequest(url_getcountries, "GET", params);
////            try {
////                String s = json.getString("count");
////                countrycount=Integer.parseInt(s);
////                countries=new String[countrycount];
////                for(int i=0;i<countrycount;i++)
////                {
////                    countries[i]=json.getString("country"+i);
////                }
////
////            }catch (Exception e){e.printStackTrace();}
//
//            countrycount = getResources().getStringArray(R.array.countries_array).length;
//            countries = new String[countrycount];
//            countries = getResources().getStringArray(R.array.countries_array);
//
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            countrieslist.clear();
//            countrieslist.add("- Select Country -");
//            for (int i = 0; i < countrycount; i++) {
//                countrieslist.add(countries[i]);
//            }
//            populateCountries();
//        }
//    }
//
//    class GetStates extends AsyncTask<String, String, String> {
//
//
//        protected String doInBackground(String... param) {
//
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("country", selectedCountry));
//            json = jParser.makeHttpRequest(url_getstates, "GET", params);
//            try {
//                String s = json.getString("count");
//                statecount = Integer.parseInt(s);
//                states = new String[statecount];
//                for (int i = 0; i < statecount; i++) {
//                    states[i] = json.getString("state" + i);
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            stateslist.clear();
//            stateslist.add("- Select State -");
//            for (int i = 0; i < statecount; i++) {
//                stateslist.add(states[i]);
//            }
//            populateStates();
//        }
//    }
//
//    class GetCities extends AsyncTask<String, String, String> {
//
//
//        protected String doInBackground(String... param) {
//
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("state", selectedState));
//            json = jParser.makeHttpRequest(url_getcities, "GET", params);
//            try {
//                String s = json.getString("count");
//                citycount = Integer.parseInt(s);
//                cities = new String[citycount];
//                for (int i = 0; i < citycount; i++) {
//                    cities[i] = json.getString("city" + i);
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            citieslist.clear();
//            citieslist.add("- Select City -");
//            for (int i = 0; i < citycount; i++) {
//                citieslist.add(cities[i]);
//            }
//            populateCities();
//        }
//
//    }
//
//    private void disableScrollbars(ScrollView scrollView) {
//        if (scrollView != null) {
//
//            scrollView.setVerticalScrollBarEnabled(false);
//
//        }
//    }
//
//
//    void populateCountries() {
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, countrieslist) {
//            @Override
//            public boolean isEnabled(int position) {
//
//                if (position == 0) {
//
//                    return false;
//                } else {
//                    return true;
//                }
//            }
//
//            @Override
//            public View getDropDownView(int position, View convertView,
//                                        ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/abz.ttf");
//                tv.setTypeface(custom_font3);
//
//                if (position == 0) {
//                    // Set the hint text color gray
//                    tv.setTextColor(Color.GRAY);
//                } else {
//                    tv.setTextColor(Color.parseColor("#eeeeee"));
//                }
//                return view;
//            }
//        };
//        ;
//        country.setAdapter(dataAdapter);
//
//        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedCountry = (String) parent.getItemAtPosition(position);
//
//                new GetStates().execute();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        //getter method
//        if (isCountrySet == 0) {
//            isCountrySet = 1;
//            if (hr.getCountry() != null) {
//                country.setSelection(dataAdapter.getPosition(hr.getCountry()));
//                selectedCountry = hr.getCountry();
//                oldCountry = hr.getCountry();
//            } else
//                oldCountry = "- Select Country -";
//        }
//
//
//    }
//
//    void populateStates() {
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, stateslist) {
//            @Override
//            public boolean isEnabled(int position) {
//
//                if (position == 0) {
//
//                    return false;
//                } else {
//                    return true;
//                }
//            }
//
//            @Override
//            public View getDropDownView(int position, View convertView,
//                                        ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/abz.ttf");
//                tv.setTypeface(custom_font3);
//
//                if (position == 0) {
//                    // Set the hint text color gray
//                    tv.setTextColor(Color.GRAY);
//                } else {
//                    tv.setTextColor(Color.parseColor("#eeeeee"));
//                }
//                return view;
//            }
//        };
//        ;
//        state.setAdapter(dataAdapter);
//
//        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedState = (String) parent.getItemAtPosition(position);
//
//                new GetCities().execute();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        if (isStateSet == 0) {
//            isStateSet = 1;
//            if (hr.getState() != null) {
//                state.setSelection(dataAdapter.getPosition(hr.getState()));
//                selectedState = hr.getState();
//                oldState = hr.getState();
//            } else
//                oldState = "- Select State -";
//        }
//    }
//
//    void populateCities() {
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, citieslist) {
//            @Override
//            public boolean isEnabled(int position) {
//
//                if (position == 0) {
//
//                    return false;
//                } else {
//                    return true;
//                }
//            }
//
//            @Override
//            public View getDropDownView(int position, View convertView,
//                                        ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/abz.ttf");
//                tv.setTypeface(custom_font3);
//
//                if (position == 0) {
//                    // Set the hint text color gray
//                    tv.setTextColor(Color.GRAY);
//                } else {
//                    tv.setTextColor(Color.parseColor("#eeeeee"));
//                }
//                return view;
//            }
//        };
//
//        city.setAdapter(dataAdapter);
//
//        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedCity = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        if (isCitySet == 0) {
//            isCitySet = 1;
//            if (hr.getCity() != null) {
//                city.setSelection(dataAdapter.getPosition(hr.getCity()));
//                selectedCity = hr.getCity();
//                oldCity = hr.getCity();
//            } else
//                oldCity = "- Select City -";
//        }
//    }
//

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);

        // HW layer support only exists on API 11+
        if (Build.VERSION.SDK_INT >= 11) {
            if (animation == null && nextAnim != 0) {
                animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
            }

            if (animation != null) {
                getView().setLayerType(View.LAYER_TYPE_HARDWARE, null);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    public void onAnimationEnd(Animation animation) {
                        getView().setLayerType(View.LAYER_TYPE_NONE, null);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                    // ...other AnimationListener methods go here...
                });
            }
        }

        return animation;
    }
}