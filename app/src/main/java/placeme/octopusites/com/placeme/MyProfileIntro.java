package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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

import placeme.octopusites.com.placeme.modal.Modelmyprofileintro;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfileIntro extends AppCompatActivity {

    String digest1, digest2;
    EditText fname, lname, role, email;
    TextInputLayout fnameTextInputLayout, lnameTextInputLayout, roleinputlayout, emailinputlayout, citystaecountryinputlayout;


    AutoCompleteTextView citystaecountry;
    JSONObject json;
    JSONParser jParser = new JSONParser();

    String strobj = "";
    Modelmyprofileintro obj;
    ArrayList<String> listAll = new ArrayList<String>();
    int countrycount = 0, statecount = 0, citycount = 0;
    String firstname = "", lastname = "", CityStateCountry = "";

    String oldCountry = "", oldState = "", oldCity = "";
    String countries[], states[], cities[];
    //    Spinner country,state,city;
    List<String> countrieslist = new ArrayList<String>();
    List<String> stateslist = new ArrayList<String>();
    List<String> citieslist = new ArrayList<String>();
    String selectedCountry = "", selectedState = "", selectedCity = "";
    String encUsername, encRole, encFname, encLname, encCountry, encState, encCity, encobj;
    int edittedFlag = 0, isCountrySet = 0, isStateSet = 0, isCitySet = 0;
    StudentData s = new StudentData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_intro);


        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Personal Info");
        ab.setDisplayHomeAsUpEnabled(true);


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        TextView loctxt = (TextView) findViewById(R.id.loctxt);
        loctxt.setTypeface(MyConstants.getBold(this));
        ScrollView myprofileintroscrollview = (ScrollView) findViewById(R.id.myprofileintroscrollview);
        disableScrollbars(myprofileintroscrollview);

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        role = (EditText) findViewById(R.id.role);
        email = (EditText) findViewById(R.id.email);
        citystaecountry = (AutoCompleteTextView) findViewById(R.id.citystaecountry);

        fnameTextInputLayout = (TextInputLayout) findViewById(R.id.fnameTextInputLayout);
        lnameTextInputLayout = (TextInputLayout) findViewById(R.id.lnameTextInputLayout);
        roleinputlayout = (TextInputLayout) findViewById(R.id.roleinputlayout);
        emailinputlayout = (TextInputLayout) findViewById(R.id.emailinputlayout);
        citystaecountryinputlayout = (TextInputLayout) findViewById(R.id.citystaecountryinputlayout);

        fname.setTypeface(MyConstants.getBold(this));
        lname.setTypeface(MyConstants.getBold(this));
        role.setTypeface(MyConstants.getBold(this));
        email.setTypeface(MyConstants.getBold(this));
        citystaecountry.setTypeface(MyConstants.getBold(this));

        fnameTextInputLayout.setTypeface(MyConstants.getLight(this));
        lnameTextInputLayout.setTypeface(MyConstants.getLight(this));
        roleinputlayout.setTypeface(MyConstants.getLight(this));
        emailinputlayout.setTypeface(MyConstants.getLight(this));
        citystaecountryinputlayout.setTypeface(MyConstants.getLight(this));


        firstname = s.getFname();
        lastname = s.getLname();
        selectedCountry = s.getCountry();
        selectedState = s.getState();
        selectedCity = s.getCity();

        if (firstname != null)
            fname.setText(firstname);
        if (lastname != null)
            lname.setText(lastname);

        if (selectedCountry != null && selectedState != null && selectedCity != null) {
            if(!selectedCountry.equals("") && !selectedState.equals("") && !selectedCity.equals("")) {
                CityStateCountry = selectedCity + " , " + selectedState + " , " + selectedCountry;
                citystaecountry.setText(CityStateCountry);
            }
            else
                citystaecountry.setText("");

        }
        else
            citystaecountry.setText("");


        String plainUsername = null;
        String username = MySharedPreferencesManager.getUsername(this);
        String rolestr = MySharedPreferencesManager.getRole(this);
        try {
            plainUsername = Decrypt(username, digest1, digest2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        role.setText(rolestr.substring(0, 1).toUpperCase() + rolestr.substring(1));
        email.setText(plainUsername);
        encUsername = username;
//        encUsername=MySharedPreferencesManager.getUsername(this);
        Log.d("TAG", "encUsername: " + encUsername);


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
                citystaecountryinputlayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fnameTextInputLayout.setError(null);
                edittedFlag = 1;
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

                lnameTextInputLayout.setError(null);

                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


        });

        edittedFlag = 0;

    }

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


    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }

    void validateandSave() {
        int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0;

        firstname = fname.getText().toString();
        lastname = lname.getText().toString();
        CityStateCountry = citystaecountry.getText().toString();

        if (firstname.length() < 1) {
            fnameTextInputLayout.setError("Kindly enter valid first name");
            errorflag4 = 1;
        } else {
            fnameTextInputLayout.setError(null);
            if (lastname.length() < 1) {
                lnameTextInputLayout.setError("Kindly enter valid last name");
                errorflag4 = 1;
            } else {
                lnameTextInputLayout.setError(null);

                if (CityStateCountry.length() < 1) {
                    citystaecountryinputlayout.setError("Please select your city");
                    errorflag4 = 1;
                } else {
                    citystaecountryinputlayout.setError(null);

                    String[] parts = CityStateCountry.split(" , ");
                    if (parts.length == 3) {
                        selectedCity = parts[0];
                        selectedState = parts[1];
                        selectedCountry = parts[2];
                    }
                }

            }


        }


        if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0) {
            try {

                Modelmyprofileintro obj2 = new Modelmyprofileintro(firstname, lastname, selectedCity, selectedState, selectedCountry);

                encobj = OtoString(obj2, digest1, digest2);
                Log.d("TAG", "validateandSave: encobj - " + encobj);

                new SaveData().execute(encobj);

            } catch (Exception e) {
                Log.d("TAG", "validateandSave: Exception -  " + e.getMessage());
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
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);


        return super.onCreateOptionsMenu(menu);

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
                                    MyProfileIntro.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(MyConstants.getBold(MyProfileIntro.this));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(MyConstants.getBold(MyProfileIntro.this));
                }
            });

            alertDialog.show();
        } else
            MyProfileIntro.super.onBackPressed();
    }

    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));//0
            Log.d("TAG", "encUsername: " + encUsername);
            params.add(new BasicNameValuePair("ci", encobj));       //1
            Log.d("TAG", "encobj: " + encobj);


            json = jParser.makeHttpRequest(MyConstants.url_SaveIntro, "GET", params);
            try {
                r = json.getString("info");
                Log.d("TAG", "doInBackground: r -" + r);
//                Log.d("TAG", "doInBackground: r -"+r);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {
                Toast.makeText(MyProfileIntro.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();


                String role = MySharedPreferencesManager.getRole(MyProfileIntro.this);
                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                MyProfileIntro.super.onBackPressed();

                s.setFname(firstname);
                s.setLname(lastname);
                s.setCountry(selectedCountry);
                s.setState(selectedState);
                s.setCity(selectedCity);

                MyProfileIntro.super.onBackPressed();
            } else
                Toast.makeText(MyProfileIntro.this, result, Toast.LENGTH_SHORT).show();


        }
    }
}
