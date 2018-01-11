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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

import placeme.octopusites.com.placeme.modal.MyProfileLocationModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfileLocationPreferences extends AppCompatActivity {
    int locationcount = 0,editlocation=0;
    View addmorelocation;
    String username, role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    ArrayList<String> listAll = new ArrayList<String>();
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview;
    int edittedFlag = 0;
    ;
    int d = 0;
    StudentData s = new StudentData();
    AutoCompleteTextView location1, location2, location3, location4, location5;
    TextInputLayout locationinput1, locationinput2, locationinput3, locationinput4, locationinput5;
    String slocation1, slocation2, slocation3, slocation4, slocation5, encobj = "";
    String enclocation1, enclocation2, enclocation3, enclocation4, enclocation5;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_location_preferences);


        MobileAds.initialize(this, Z.APP_ID);
        mAdView = findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Location Preferences");
        ab.setDisplayHomeAsUpEnabled(true);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username = MySharedPreferencesManager.getUsername(this);
        role = MySharedPreferencesManager.getRole(this);

        trash1selectionview = (View) findViewById(R.id.trash1selectionview);
        trash2selectionview = (View) findViewById(R.id.trash2selectionview);
        trash3selectionview = (View) findViewById(R.id.trash3selectionview);
        trash4selectionview = (View) findViewById(R.id.trash4selectionview);
        trash5selectionview = (View) findViewById(R.id.trash5selectionview);

        locationinput1 = (TextInputLayout) findViewById(R.id.locationinput1);
        locationinput2 = (TextInputLayout) findViewById(R.id.locationinput2);
        locationinput3 = (TextInputLayout) findViewById(R.id.locationinput3);
        locationinput4 = (TextInputLayout) findViewById(R.id.locationinput4);
        locationinput5 = (TextInputLayout) findViewById(R.id.locationinput5);

        location1 = (AutoCompleteTextView) findViewById(R.id.location1);
        location2 = (AutoCompleteTextView) findViewById(R.id.location2);
        location3 = (AutoCompleteTextView) findViewById(R.id.location3);
        location4 = (AutoCompleteTextView) findViewById(R.id.location4);
        location5 = (AutoCompleteTextView) findViewById(R.id.location5);

        location1.setTypeface(Z.getBold(this));
        location2.setTypeface(Z.getBold(this));
        location3.setTypeface(Z.getBold(this));
        location4.setTypeface(Z.getBold(this));
        location5.setTypeface(Z.getBold(this));

        locationinput1.setTypeface(Z.getLight(this));
        locationinput2.setTypeface(Z.getLight(this));
        locationinput3.setTypeface(Z.getLight(this));
        locationinput4.setTypeface(Z.getLight(this));
        locationinput5.setTypeface(Z.getLight(this));

        TextView addmorelocationtxt = (TextView) findViewById(R.id.addmorelocationtxt);
        addmorelocationtxt.setTypeface(Z.getBold(this));


        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 1;
                showDeletDialog();
            }
        });
        trash2selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 2;
                showDeletDialog();

            }
        });
        trash3selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 3;
                showDeletDialog();
            }
        });
        trash4selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 4;
                showDeletDialog();
            }
        });
        trash5selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 5;
                showDeletDialog();
            }
        });

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
        location1.setAdapter(adapter);

        location2.setAdapter(adapter);
        location3.setAdapter(adapter);
        location4.setAdapter(adapter);
        location5.setAdapter(adapter);




        location1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                locationinput1.setError(null);
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
                edittedFlag = 1;
                locationinput2.setError(null);
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
                edittedFlag = 1;
                locationinput3.setError(null);
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
                edittedFlag = 1;
                locationinput4.setError(null);
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
                edittedFlag = 1;
                locationinput5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        TextView locationtxt = (TextView) findViewById(R.id.locationtxt);
        locationtxt.setTypeface(Z.getBold(this));

        addmorelocation = (View) findViewById(R.id.addmorelocation);
        addmorelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editlocation=0;
                edittedFlag=1;


                if (locationcount == 0) {
                    if (location1.getText().toString() != null) {
                        if (!location1.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.locationline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl2);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            locationcount++;
                        } else
                            Toast.makeText(MyProfileLocationPreferences.this, "Please enter empty location", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileLocationPreferences.this, "Please enter empty location", Toast.LENGTH_SHORT).show();


                } else if (locationcount == 1) {
                    if (location2.getText().toString() != null) {
                        if (!location2.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.locationline2);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl3);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            locationcount++;

                        } else
                            Toast.makeText(MyProfileLocationPreferences.this, "Please enter empty location", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileLocationPreferences.this, "Please enter empty location", Toast.LENGTH_SHORT).show();

                } else if (locationcount == 2) {
                    if (location3.getText().toString() != null) {
                        if (!location3.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.locationline3);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl4);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            locationcount++;
                        } else
                            Toast.makeText(MyProfileLocationPreferences.this, "Please enter empty location", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileLocationPreferences.this, "Please enter empty location", Toast.LENGTH_SHORT).show();

                } else if (locationcount == 3) {

                    if (location4.getText().toString() != null) {
                        if (!location4.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.locationline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl5);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            locationcount++;

                            TextView t = (TextView) findViewById(R.id.addmorelocationtxt);
                            ImageView i = (ImageView) findViewById(R.id.addmorelocationimg);
                            addmorelocation.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);
                        } else
                            Toast.makeText(MyProfileLocationPreferences.this, "Please enter empty location", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileLocationPreferences.this, "Please enter empty location", Toast.LENGTH_SHORT).show();


                }

            }
        });

        slocation1 = s.getLocation1();
        slocation2 = s.getLocation2();
        slocation3 = s.getLocation3();
        slocation4 = s.getLocation4();
        slocation5 = s.getLocation5();

        if (slocation1 != null) {
            if (slocation1.length() > 2) {
                location1.setText(slocation1);
            }
        }
        if (slocation2 != null) {
            if (slocation2.length() > 2) {
                location2.setText(slocation2);

                View v = (View) findViewById(R.id.locationline1);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl2);
                relativeLayout1.setVisibility(View.VISIBLE);
                locationcount++;
            }
        }
        if (slocation3 != null) {
            if (slocation3.length() > 2) {
                location3.setText(slocation3);

                View v = (View) findViewById(R.id.locationline2);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl3);
                relativeLayout1.setVisibility(View.VISIBLE);
                locationcount++;
            }
        }
        if (slocation4 != null) {
            if (slocation4.length() > 2) {
                location4.setText(slocation4);

                View v = (View) findViewById(R.id.locationline3);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl4);
                relativeLayout1.setVisibility(View.VISIBLE);
                locationcount++;
            }
        }
        if (slocation5 != null) {
            if (slocation5.length() > 2) {
                location5.setText(slocation5);

                View v = (View) findViewById(R.id.locationline4);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl5);
                relativeLayout1.setVisibility(View.VISIBLE);
                locationcount++;
            }
        }

        edittedFlag = 0;
    }

    void showDeletDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this location ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag = 1;
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileLocationPreferences.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileLocationPreferences.this));

            }
        });

        alertDialog.show();

    }

    void deleteLocation() {
        View v = (View) findViewById(R.id.locationline4);
        if (v.getVisibility() == View.VISIBLE) {
            View v1 = (View) findViewById(R.id.locationline4);
            v1.setVisibility(View.GONE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl5);
            relativeLayout1.setVisibility(View.GONE);
            locationcount--;

            TextView t = (TextView) findViewById(R.id.addmorelocationtxt);
            ImageView i = (ImageView) findViewById(R.id.addmorelocationimg);
            addmorelocation.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);


        } else {
            v = (View) findViewById(R.id.locationline3);
            if (v.getVisibility() == View.VISIBLE) {
                View v1 = (View) findViewById(R.id.locationline3);
                v1.setVisibility(View.GONE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl4);
                relativeLayout1.setVisibility(View.GONE);
                locationcount--;

            } else {
                v = (View) findViewById(R.id.locationline2);
                if (v.getVisibility() == View.VISIBLE) {
                    View v1 = (View) findViewById(R.id.locationline2);
                    v1.setVisibility(View.GONE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl3);
                    relativeLayout1.setVisibility(View.GONE);
                    locationcount--;

                } else {
                    v = (View) findViewById(R.id.locationline1);
                    if (v.getVisibility() == View.VISIBLE) {
                        View v1 = (View) findViewById(R.id.locationline1);
                        v1.setVisibility(View.GONE);

                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.locationrl2);
                        relativeLayout1.setVisibility(View.GONE);
                        locationcount--;

                    } else {
                        location1.setText("");
                    }
                }
            }

        }
        if (d == 5) {
            location5.setText("");
        } else if (d == 4) {
            slocation5 = location5.getText().toString();


            slocation4 = slocation5;
            location4.setText(slocation4);
            location5.setText("");

        } else if (d == 3) {
            slocation5 = location5.getText().toString();
            slocation4 = location4.getText().toString();


            slocation3 = slocation4;
            location3.setText(slocation3);
            location4.setText("");

            slocation4 = slocation5;
            location4.setText(slocation4);
            location5.setText("");
        } else if (d == 2) {
            slocation5 = location5.getText().toString();
            slocation4 = location4.getText().toString();
            slocation3 = location3.getText().toString();


            slocation2 = slocation3;
            location2.setText(slocation2);
            location3.setText("");

            slocation3 = slocation4;
            location3.setText(slocation3);
            location4.setText("");

            slocation4 = slocation5;
            location4.setText(slocation4);
            location5.setText("");

        } else if (d == 1) {
            slocation5 = location5.getText().toString();
            slocation4 = location4.getText().toString();
            slocation3 = location3.getText().toString();
            slocation2 = location2.getText().toString();


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

            slocation1 = location1.getText().toString();

            if(slocation1.equals("")){
                editlocation=1;
            }

            if(editlocation==1){
                enclocation();
            }
        }
    }

    void validateandSave() {
//

        slocation1 = location1.getText().toString();
        slocation2 = location2.getText().toString();
        slocation3 = location3.getText().toString();
        slocation4 = location4.getText().toString();
        slocation5 = location5.getText().toString();


        int errorflag = 0;

        if(editlocation==1){
            enclocation();
        }
        else {
            if (locationcount == 0) {
                if (slocation1.length() < 2) {
                    errorflag = 1;
                    locationinput1.setError("Kindly enter valid location.");
                }
            } else if (locationcount == 1) {
                if (slocation1.length() < 2) {
                    errorflag = 1;
                    locationinput1.setError("Kindly enter valid location.");
                } else {
                    errorflag = 0;
                    if (slocation2.length() < 2) {
                        errorflag = 1;
                        locationinput2.setError("Kindly enter valid location.");
                    }
                }
            } else if (locationcount == 2) {
                if (slocation1.length() < 2) {
                    errorflag = 1;
                    locationinput1.setError("Kindly enter valid location.");
                } else {
                    errorflag = 0;
                    if (slocation2.length() < 2) {
                        errorflag = 1;
                        locationinput2.setError("Kindly enter valid location.");
                    } else {
                        errorflag = 0;
                        if (slocation3.length() < 2) {
                            errorflag = 1;
                            locationinput3.setError("Kindly enter valid location.");
                        }
                    }
                }
            } else if (locationcount == 3) {
                if (slocation1.length() < 2) {
                    errorflag = 1;
                    locationinput1.setError("Kindly enter valid location.");
                } else {
                    errorflag = 0;
                    if (slocation2.length() < 2) {
                        errorflag = 1;
                        locationinput2.setError("Kindly enter valid location.");
                    } else {
                        errorflag = 0;
                        if (slocation3.length() < 2) {
                            errorflag = 1;
                            locationinput3.setError("Kindly enter valid location.");
                        } else {
                            errorflag = 0;
                            if (slocation4.length() < 2) {
                                errorflag = 1;
                                locationinput4.setError("Kindly enter valid location.");
                            }
                        }
                    }
                }
            } else if (locationcount == 4) {
                if (slocation1.length() < 2) {
                    errorflag = 1;
                    locationinput1.setError("Kindly enter valid location.");
                } else {
                    errorflag = 0;
                    if (slocation2.length() < 2) {
                        errorflag = 1;
                        locationinput2.setError("Kindly enter valid location.");
                    } else {
                        errorflag = 0;
                        if (slocation3.length() < 2) {
                            errorflag = 1;
                            locationinput3.setError("Kindly enter valid location.");
                        } else {
                            errorflag = 0;
                            if (slocation4.length() < 2) {
                                errorflag = 1;
                                locationinput4.setError("Kindly enter valid location.");
                            } else {
                                errorflag = 0;
                                if (slocation5.length() < 2) {
                                    errorflag = 1;
                                    locationinput5.setError("Kindly enter valid location.");
                                }
                            }
                        }
                    }
                }
            }
        }

        if (errorflag == 0) {
            enclocation();
        }
    }

    public void enclocation()
    {
        try {

            MyProfileLocationModal obj2 = new MyProfileLocationModal(slocation1, slocation2, slocation3, slocation4, slocation5);

            encobj = OtoString(obj2, MySharedPreferencesManager.getDigest1(MyProfileLocationPreferences.this), MySharedPreferencesManager.getDigest2(MyProfileLocationPreferences.this));

            new SaveLocations().execute();


        } catch (Exception e) {
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

    @Override
    public void onBackPressed() {
        if (edittedFlag != 0) {

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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileLocationPreferences.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileLocationPreferences.this));
                }
            });

            alertDialog.show();
        } else
            MyProfileLocationPreferences.super.onBackPressed();
    }

    class SaveLocations extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("l1", encobj));  //1

            json = jParser.makeHttpRequest(Z.url_savelocationpreferences, "GET", params);
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
                Toast.makeText(MyProfileLocationPreferences.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();


                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                s.setLocation1(slocation1);
                s.setLocation2(slocation2);
                s.setLocation3(slocation3);
                s.setLocation4(slocation4);
                s.setLocation5(slocation5);

                MyProfileLocationPreferences.super.onBackPressed();
            } else
                Toast.makeText(MyProfileLocationPreferences.this, "Try again !", Toast.LENGTH_SHORT).show();

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
