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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ScrollView;
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

import placeme.octopusites.com.placeme.modal.ModalHrIntro;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.ProfileRole.plainusername;

public class HrIntro extends AppCompatActivity {


    public static final String USERNAME = "nameKey";
//    private Spinner country, state, city;
    public static final String MyPREFERENCES = "MyPrefs";
    String strobj = "";
    String encobj = "";
    AutoCompleteTextView citystaecountry;
    ArrayList<String> listAll = new ArrayList<String>();
    JSONObject json;
    JSONParser jParser = new JSONParser();
    int edittedFlag = 0, isCountrySet = 0, isStateSet = 0, isCitySet = 0;
    String selectedCountry = "", selectedState = "", selectedCity = "", CityStateCountry = "";
    String firstname = "", lastname = "", designationValue = "";
    String userName, roleValue;
    String encUsername, encRole, encFname, encLname, encCountry, encState, encCity, encdesignation;
    HrData hr = new HrData();
    private EditText fname, lname, role, email, designation;
    private String digest1, digest2;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_intro);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Personal Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        TextView loctxt = (TextView) findViewById(R.id.loctxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/arba.ttf");
        loctxt.setTypeface(custom_font1);

        //-----------------

        Digest d = new Digest();
        digest1 = d.getDigest1();
        digest2 = d.getDigest2();

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.sname);
        role = (EditText) findViewById(R.id.role);
        email = (EditText) findViewById(R.id.email);
        designation = (EditText) findViewById(R.id.inst);


        ScrollView myprofileintroscrollview = (ScrollView) findViewById(R.id.myprofileintroscrollview);
        disableScrollbars(myprofileintroscrollview);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
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


        citystaecountry = (AutoCompleteTextView) findViewById(R.id.citystaecountry);


        selectedCountry = hr.getCountry();
        selectedState = hr.getState();
        selectedCity = hr.getCity();

        firstname = hr.getFname();
        lastname = hr.getLname();
        designationValue = hr.getDesignation();


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
        if (selectedCountry != null && selectedState != null && selectedCity != null) {
            CityStateCountry = selectedCity + " , " + selectedState + " , " + selectedCountry;
            citystaecountry.setText(CityStateCountry);
        }

        try {
            JSONObject jsonObject = new JSONObject(getJson());
            JSONArray array = jsonObject.getJSONArray("array");
            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);
                String city = object.getString("city");
                String state = object.getString("state");
                String country = object.getString("country");


                listAll.add(city + " , " + state + " , " + country);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listAll);
        citystaecountry.setAdapter(adapter);

        citystaecountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                fname.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                lname.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        designation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                designation.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }// oncreate

    public String getJson() {
        String json = null;
        try {
            InputStream is = getAssets().open("citystatecountrydb/array.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return json;
        }
        return json;
    }

    void validateandSave() {
        int errorflagCountry = 0, errorflagState = 0, errorflagCity = 0, errorflagfirstname = 0, errorflaglastname = 0, errorflagdesignation = 0;

        firstname = fname.getText().toString();
        lastname = lname.getText().toString();
        designationValue = designation.getText().toString();
        CityStateCountry = citystaecountry.getText().toString();


        if (CityStateCountry.length() < 2) {
            citystaecountry.setError("Incorrect City Name");
            errorflagfirstname = 1;
        } else {
            citystaecountry.setError(null);

            String[] parts = CityStateCountry.split(" , ");
            if (parts.length == 3) {
                selectedCity = parts[0];
                selectedState = parts[1];
                selectedCountry = parts[2];
            }
        }


        if (firstname.length() < 2) {
            fname.setError("Incorrect First Name");
            errorflagfirstname = 1;
        } else if (lastname.length() < 2) {
            lname.setError("Incorrect Last Name");
            errorflaglastname = 1;
        } else if (designation.length() < 2) {
            designation.setError("Incorrect designation");
            errorflagdesignation = 1;
        } else {
            //fname.setError(null);
            if (selectedCountry.contains("Select")) {
                Toast.makeText(HrIntro.this, "Select Country", Toast.LENGTH_SHORT).show();
                errorflagCountry = 1;
            } else {
                if (selectedState.contains("Select")) {
                    Toast.makeText(HrIntro.this, "Select State", Toast.LENGTH_SHORT).show();
                    errorflagState = 1;
                } else if (selectedCity.contains("Select")) {
                    Toast.makeText(HrIntro.this, "Select City", Toast.LENGTH_SHORT).show();
                    errorflagCity = 1;
                }
            }

        }

        if (errorflagfirstname == 0 && errorflagCountry == 0 && errorflagState == 0 && errorflagCity == 0 && errorflaglastname == 0 && errorflagdesignation == 0) {

            try {

                ModalHrIntro obj2 = new ModalHrIntro(firstname, lastname, designationValue, selectedCity, selectedState, selectedCountry);
                encobj = OtoString(obj2, MySharedPreferencesManager.getDigest1(HrIntro.this), MySharedPreferencesManager.getDigest2(HrIntro.this));
                Log.d("TAG", "validateandSave: encobj - " + encobj);
                new SaveData().execute();

            } catch (Exception e) {
                Log.d("TAG", "validateandSave: Exception -  " + e.getMessage());
            }
        }


    }

    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
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
                break;

            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {


        if (edittedFlag == 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    HrIntro.super.onBackPressed();
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
        } else
            HrIntro.super.onBackPressed();
    }

    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));
            params.add(new BasicNameValuePair("d", encobj));       //1

            json = jParser.makeHttpRequest(MyConstants.url_savedata_SaveHrIntro, "GET", params);
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
                Toast.makeText(HrIntro.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();
                setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);

                hr.setFname(firstname);
                hr.setLname(lastname);
                hr.setCountry(selectedCountry);
                hr.setState(selectedState);
                hr.setCity(selectedCity);

                HrIntro.super.onBackPressed();
            }
        }
    }

}

