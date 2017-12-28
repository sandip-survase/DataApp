package placeme.octopusites.com.placeme;


import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.ModalHrIntro;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;


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

    private String plainusername = "",encobj="";
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
    TextInputLayout fnameTextInputLayout, lnameTextInputLayout, roleinputlayout,designinputlayout, emailinputlayout, citystaecountryinputlayout;

    String countries[], states[], cities[];

    List<String> countrieslist = new ArrayList<String>();
    List<String> stateslist = new ArrayList<String>();
    List<String> citieslist = new ArrayList<String>();

    HrData hr = new HrData();

    public HrPersonalTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_hr_personal_tab, container, false);


        TextView loctxt = (TextView) rootView.findViewById(R.id.loctxt);
        loctxt.setTypeface(Z.getBold(getActivity()));

        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());

        fname = (EditText) rootView.findViewById(R.id.fname);
        lname = (EditText) rootView.findViewById(R.id.sname);
        role = (EditText) rootView.findViewById(R.id.role);
        email = (EditText) rootView.findViewById(R.id.email);
        designation = (EditText) rootView.findViewById(R.id.inst);
        citystaecountry = (AutoCompleteTextView) rootView.findViewById(R.id.citystaecountry);

        fnameTextInputLayout = (TextInputLayout)rootView. findViewById(R.id.fnameTextInputLayout);
        lnameTextInputLayout = (TextInputLayout)rootView. findViewById(R.id.lnameTextInputLayout);
        roleinputlayout = (TextInputLayout)rootView. findViewById(R.id.roleinputlayout);
        emailinputlayout = (TextInputLayout)rootView. findViewById(R.id.emailinputlayout);
        designinputlayout = (TextInputLayout) rootView.findViewById(R.id.instinputlayout);
        citystaecountryinputlayout = (TextInputLayout)rootView. findViewById(R.id.citystaecountryinputlayout);

        fname.setTypeface(Z.getBold(getActivity()));
        lname.setTypeface(Z.getBold(getActivity()));
        role.setTypeface(Z.getBold(getActivity()));
        email.setTypeface(Z.getBold(getActivity()));
        designation.setTypeface(Z.getBold(getActivity()));
        citystaecountry.setTypeface(Z.getBold(getActivity()));

        fnameTextInputLayout.setTypeface(Z.getLight(getActivity()));
        lnameTextInputLayout.setTypeface(Z.getLight(getActivity()));
        roleinputlayout.setTypeface(Z.getLight(getActivity()));
        designinputlayout.setTypeface(Z.getLight(getActivity()));
        emailinputlayout.setTypeface(Z.getLight(getActivity()));
        citystaecountryinputlayout.setTypeface(Z.getLight(getActivity()));

        citystaecountry=(AutoCompleteTextView)rootView.findViewById(R.id.citystaecountry);
        userName = MySharedPreferencesManager.getUsername(getActivity());
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


        roleValue = MySharedPreferencesManager.getRole(getActivity());
        role.setText(roleValue.toUpperCase());
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
            if(!selectedCountry.equals("") && !selectedState.equals("") && !selectedCity.equals("")) {
                CityStateCountry = selectedCity + " , " + selectedState + " , " + selectedCountry;
                citystaecountry.setText(CityStateCountry);
            }
            else
                citystaecountry.setText("");
        }


        citystaecountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagp = 1;
                citystaecountryinputlayout.setError("");
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
                fnameTextInputLayout.setError(null);
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

                lnameTextInputLayout.setError(null);
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
                designinputlayout.setError(null);
                edittedFlagp = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

        if (firstname.length() < 2) {
            fnameTextInputLayout.setError("Kindly enter valid first name");
            persnolerrorflag = 1;
        } else if (lastname.length() < 2) {
            lnameTextInputLayout.setError("Kindly enter valid last name");
            persnolerrorflag = 1;
        } else if (designation.length() < 2) {
            designinputlayout.setError("Kindly enter valid designation");
            persnolerrorflag = 1;
        } else {
            if (CityStateCountry.length() < 2) {
                citystaecountryinputlayout.setError("Please select your city");
                persnolerrorflag = 1;
            } else {
                citystaecountry.setError(null);

                String[] parts = CityStateCountry.split(" , ");
                if (parts.length == 3) {
                    selectedCity = parts[0];
                    selectedState = parts[1];
                    selectedCountry = parts[2];
                }
            }

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


    }

    public void save()
    {
        try {
            ModalHrIntro obj2 = new ModalHrIntro(firstname, lastname, designationValue, selectedCity, selectedState, selectedCountry);
            encobj = OtoString(obj2, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
            Log.d("TAG", "validateandSave: encobj - " + encobj);
            new SaveData().execute();

        } catch (Exception e) {

        }

    }
    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));
            params.add(new BasicNameValuePair("d", encobj));       //1

            json = jParser.makeHttpRequest(Z.url_SaveHrIntro, "GET", params);
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
            }

            getActivity().setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
        }
    }


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