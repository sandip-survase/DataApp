package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

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

    Modelmyprofileintro obj;
    ArrayList<String> listAll = new ArrayList<String>();
    String firstname = "", lastname = "", CityStateCountry = "";
    String selectedCountry = "", selectedState = "", selectedCity = "";
    String encUsername, encRole, encobj;
    int edittedFlag = 0;
    StudentData s = new StudentData();

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_intro);

//        MobileAds.initialize(this, Z.APP_ID);
//        mAdView = findViewById(R.id.ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        buildCityStateCountryList();

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Personal Info");
        ab.setDisplayHomeAsUpEnabled(true);


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        TextView loctxt = (TextView) findViewById(R.id.loctxt);
        loctxt.setTypeface(Z.getBold(this));
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

        fname.setTypeface(Z.getBold(this));
        lname.setTypeface(Z.getBold(this));
        role.setTypeface(Z.getBold(this));
        email.setTypeface(Z.getBold(this));
        citystaecountry.setTypeface(Z.getBold(this));

        fnameTextInputLayout.setTypeface(Z.getLight(this));
        lnameTextInputLayout.setTypeface(Z.getLight(this));
        roleinputlayout.setTypeface(Z.getLight(this));
        emailinputlayout.setTypeface(Z.getLight(this));
        citystaecountryinputlayout.setTypeface(Z.getLight(this));


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
            Log.d("cricket", "MPI DG odi match lost - " + e.getMessage());
            e.printStackTrace();
        }
        role.setText(rolestr.substring(0, 1).toUpperCase() + rolestr.substring(1));
        email.setText(plainUsername);
        encUsername = username;

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
        Log.d("cricket", "MPI OC odi match");

    }

    private void buildCityStateCountryList() {

        new Thread(new Runnable() {
            public void run() {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

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
                    Log.d("cricket", "MPI  BCSCL match lost - " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();

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
            Log.d("cricket", "MPI GJ odi match lost - " + ex.getMessage());
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
                Log.d("cricket", "MPI V&S odi match");
                Modelmyprofileintro obj2 = new Modelmyprofileintro(firstname, lastname, selectedCity, selectedState, selectedCountry);
                encobj = OtoString(obj2, digest1, digest2);
                new SaveData().execute(encobj);

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("cricket", "MPI V&S odi match lost - " + e.getMessage());
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:

                if (edittedFlag == 1) {
                    validateandSave();
                }
                else {
                    onBackPressed();
                }
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
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileIntro.this));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileIntro.this));
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
            params.add(new BasicNameValuePair("ci", encobj));       //1

            Log.d("cricket", "MPI DIB odi match");
            json = jParser.makeHttpRequest(Z.url_SaveIntro, "GET", params);
            try {
                r = json.getString("info");
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("cricket", "MPI DIB odi match lost - " + e.getMessage());

            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("cricket", "MPI OPE odi match");
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
                Toast.makeText(MyProfileIntro.this, "Try again !", Toast.LENGTH_SHORT).show();


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
        super.onDestroy();
    }
}
