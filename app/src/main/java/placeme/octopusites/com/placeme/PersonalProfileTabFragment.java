package placeme.octopusites.com.placeme;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import placeme.octopusites.com.placeme.modal.MyProfilePersonal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;


public class PersonalProfileTabFragment extends Fragment {

    EditText fnameedittext, mnameedittext, snameedittext, nameastenedittext, caddrline1, caddrline2, caddrline3, paddrline1, paddrline2, paddrline3, emailedittext, phoneedittext, profileaemail, mothernameedittext, dobedittext, mobileedittext, alternatemobileedittext, mothertongueedittext, hobbiesedittext, casteedittext, prnedittext, languagesknownedittext;
    RadioButton radioButtonMale, radioButtonFemale, radioButtonHandicappedNo, radioButtonHandicappedYes, radioButtonSportsNo, radioButtonSportsState, radioButtonSportsNational, radioButtonSportsInternational, radioButtonDefenceNo, radioButtonDefence, radioButtonExserviceman;
    Spinner bloodgrpspinner, categoryspinner, religionspinner;
    String fname = "", mname = "", sname = "", nameasten = "", alternateemail = "", mothername = "", dob = "", gender = "", mobile = "", phone = "", alternatemobile = "", mothertongue = "", hobbies = "", bloodgroup = "", category = "", religion = "", caste = "", prn = "", handicapped = "", sports = "", defenceex = "";
    String lang1 = "", lang2 = "", lang3 = "", lang4 = "", lang5 = "", lang6 = "", lang7 = "", lang8 = "", lang9 = "", lang10 = "", addrline1c = "", addrline2c = "", addrline3c = "", addrline1p = "", addrline2p = "", addrline3p = "";
    String encfname, encmname, encsname, encnameasten, encalternateemail, encmothername, encdob, encgender, encmobile, encphone, encalternatemobile, encmothertongue, enchobbies, encbloodgroup, enccategory, encreligion, enccaste, encprn, enchandicapped, encsports, encdefenceex;
    String encaddrline1c, encaddrline2c, encaddrline3c, encaddrline1p, encaddrline2p, encaddrline3p;
    //    Button save;
    RadioGroup radioGroupGender, radioGroupHandicapped, radioGroupSports, radioGroupDefenceex;
    JSONParser jParser = new JSONParser();

    JSONObject json;
    String resultofop = "",encobj="";
    ProgressBar personalprogress;
    ImageView editknownlang;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username, plainusername, Myrole,role;
    String digest1, digest2;
    int edittedFlag = 0;
    StudentData s = new StudentData();
    String[] bloodgroups, categories, religions;
    List<String> bloodgrouplist, categorylist, religionlist;
    ArrayAdapter<String> categoryArrayAdapter;
    ArrayAdapter<String> religionArrayAdapter;
    ArrayAdapter<String> bloodgrpArrayAdapter;

    int errorflag = 0;

    CheckBox CheckBoxPSC;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_edit_profile_personal, container, false);

        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        username=MySharedPreferencesManager.getUsername(getActivity());
        role=MySharedPreferencesManager.getRole(getActivity());
        Myrole = role;


        CheckBoxPSC = (CheckBox) rootView.findViewById(R.id.CheckBoxPSC);
        fnameedittext = (EditText) rootView.findViewById(R.id.fname);
        mnameedittext = (EditText) rootView.findViewById(R.id.mname);
        snameedittext = (EditText) rootView.findViewById(R.id.sname);
        nameastenedittext = (EditText) rootView.findViewById(R.id.nameas10);
        emailedittext = (EditText) rootView.findViewById(R.id.profileemail);
        profileaemail = (EditText) rootView.findViewById(R.id.profileaemail);
        caddrline1 = (EditText) rootView.findViewById(R.id.caddrline1);
        caddrline2 = (EditText) rootView.findViewById(R.id.caddrline2);
        caddrline3 = (EditText) rootView.findViewById(R.id.caddrline3);
        paddrline1 = (EditText) rootView.findViewById(R.id.paddrline1);
        paddrline2 = (EditText) rootView.findViewById(R.id.paddrline2);
        paddrline3 = (EditText) rootView.findViewById(R.id.paddrline3);
        phoneedittext = (EditText) rootView.findViewById(R.id.phone);
        mothernameedittext = (EditText) rootView.findViewById(R.id.mothername);
        dobedittext = (EditText) rootView.findViewById(R.id.dob);
        dobedittext.setFocusable(false);
        mobileedittext = (EditText) rootView.findViewById(R.id.mobile);
        alternatemobileedittext = (EditText) rootView.findViewById(R.id.amobile);
        mothertongueedittext = (EditText) rootView.findViewById(R.id.mothertongue);
        hobbiesedittext = (EditText) rootView.findViewById(R.id.hobbies);
        casteedittext = (EditText) rootView.findViewById(R.id.cast);
        prnedittext = (EditText) rootView.findViewById(R.id.prn);
        languagesknownedittext = (EditText) rootView.findViewById(R.id.knownlang);

//        save=(Button)rootView.findViewById(R.id.savepersonal);
        personalprogress = (ProgressBar) rootView.findViewById(R.id.personalprogress);

        bloodgrpspinner = (Spinner) rootView.findViewById(R.id.bloodgrp);
        categoryspinner = (Spinner) rootView.findViewById(R.id.category);
        religionspinner = (Spinner) rootView.findViewById(R.id.religion);


        radioGroupGender = (RadioGroup) rootView.findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton) rootView.findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton) rootView.findViewById(R.id.radioButtonFemale);

        radioGroupHandicapped = (RadioGroup) rootView.findViewById(R.id.radioGroupHandicapped);
        radioButtonHandicappedNo = (RadioButton) rootView.findViewById(R.id.radioButtonHandicappedNo);
        radioButtonHandicappedYes = (RadioButton) rootView.findViewById(R.id.radioButtonHandicappedYes);

        radioGroupSports = (RadioGroup) rootView.findViewById(R.id.radioGroupSports);
        radioButtonSportsNo = (RadioButton) rootView.findViewById(R.id.radioButtonSportsNo);
        radioButtonSportsState = (RadioButton) rootView.findViewById(R.id.radioButtonSportsState);
        radioButtonSportsNational = (RadioButton) rootView.findViewById(R.id.radioButtonSportsNational);
        radioButtonSportsInternational = (RadioButton) rootView.findViewById(R.id.radioButtonSportsInternational);

        radioGroupDefenceex = (RadioGroup) rootView.findViewById(R.id.radioGroupDefenceex);
        radioButtonDefenceNo = (RadioButton) rootView.findViewById(R.id.radioButtonDefenceNo);
        radioButtonDefence = (RadioButton) rootView.findViewById(R.id.radioButtonDefence);
        radioButtonExserviceman = (RadioButton) rootView.findViewById(R.id.radioButtonExserviceman);

        radioGroupHandicapped.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                edittedFlag = 1;
            }
        });

        radioGroupSports.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                edittedFlag = 1;
            }
        });

        radioGroupDefenceex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                edittedFlag = 1;
            }
        });

        fnameedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fnameedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mnameedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mnameedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        snameedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                snameedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nameastenedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameastenedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        profileaemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                profileaemail.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mothernameedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mothernameedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dobedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dobedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        phoneedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mobileedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mobileedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        alternatemobileedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alternatemobileedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mothertongueedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mothertongueedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        hobbiesedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hobbiesedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        prnedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                prnedittext.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        caddrline1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                caddrline1.setError(null);
                String Editvalue ="";
                Editvalue = caddrline1.getText().toString();
                Log.d("TAG", "onTextChanged: Editvalue- "+Editvalue);
                Log.d("TAG", "onTextChanged: addrline1c -"+addrline1c);
                if(addrline1c!=null) {
                    if (!addrline1c.equals(Editvalue)) {

                        edittedFlag = 1;
                        CheckBoxPSC.setChecked(false);
                    }
                }
                else{
                    addrline1c = "";
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        caddrline2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                caddrline2.setError(null);

                String Editvalue = caddrline2.getText().toString();
                if(addrline2c!=null) {
                    if (!addrline2c.equals(Editvalue)) {
                        edittedFlag = 1;
                        CheckBoxPSC.setChecked(false);
                    }
                }
                else{
                    addrline2c = "";
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        caddrline3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                caddrline3.setError(null);
                String Editvalue = caddrline3.getText().toString();
                if(addrline3c!=null) {
                    if (!addrline3c.equals(Editvalue)) {
                        edittedFlag = 1;
                        CheckBoxPSC.setChecked(false);
                    }
                }
                else{
                    addrline3c = "";
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        paddrline1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                paddrline1.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        paddrline2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                paddrline2.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        paddrline3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                paddrline3.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        editknownlang = (ImageView) rootView.findViewById(R.id.editknownlang);

        editknownlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MyProfileKnownLang.class));
            }
        });


        bloodgroups = new String[]{
                "- Select Bloodgroup -", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        };
        categories = new String[]{
                "- Select Category -", "OPEN", "OBC", "SC/ST", "NT"
        };
        religions = new String[]{
                "- Select Religion -", "Hinduism", "Buddhism", "Christianity", "Islam", "Jainism", "Sikhism", "Zoroastrianism"
        };
        bloodgrouplist = new ArrayList<>(Arrays.asList(bloodgroups));
        categorylist = new ArrayList<>(Arrays.asList(categories));
        religionlist = new ArrayList<>(Arrays.asList(religions));

        // Initializing an ArrayAdapter

        categoryArrayAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item, categorylist) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.parseColor("#eeeeee"));
                }
                return view;
            }
        };

        religionArrayAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item, religionlist) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.parseColor("#eeeeee"));
                }
                return view;
            }
        };


        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        categoryspinner.setAdapter(categoryArrayAdapter);

        religionArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        religionspinner.setAdapter(religionArrayAdapter);

        bloodgrpArrayAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item, bloodgrouplist) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.parseColor("#eeeeee"));
                }
                return view;
            }
        };


        bloodgrpArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        bloodgrpspinner.setAdapter(bloodgrpArrayAdapter);

        bloodgrpspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {

                    if (position == 1)
                        bloodgroup = "A+";
                    else if (position == 2)
                        bloodgroup = "A-";
                    else if (position == 3)
                        bloodgroup = "B+";
                    else if (position == 4)
                        bloodgroup = "B-";
                    else if (position == 5)
                        bloodgroup = "AB+";
                    else if (position == 6)
                        bloodgroup = "AB-";
                    else if (position == 7)
                        bloodgroup = "O+";
                    else if (position == 8)
                        bloodgroup = "O-";


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        categoryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {

                    if (position == 1)
                        category = "open";
                    else if (position == 2)
                        category = "obc";
                    else if (position == 3)
                        category = "sc/st";
                    else if (position == 4)
                        category = "nt";


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        religionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {

                    if (position == 1)
                        religion = "Hinduism";
                    else if (position == 2)
                        religion = "Buddhism";
                    else if (position == 3)
                        religion = "Christianity";
                    else if (position == 4)
                        religion = "Islam";
                    else if (position == 5)
                        religion = "Jainism";
                    else if (position == 6)
                        religion = "Sikhism";
                    else if (position == 7)
                        religion = "Zoroastrianism";


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CheckBoxPSC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    addrline1p=addrline1c;
//                    addrline2p=addrline2c;
//                    addrline3p=addrline3c;

                    addrline1p = caddrline1.getText().toString();
                    addrline2p = caddrline2.getText().toString();
                    addrline3p = caddrline3.getText().toString();


                    paddrline1.setText(addrline1p);
                    paddrline2.setText(addrline2p);
                    paddrline3.setText(addrline3p);

                    paddrline1.setFocusable(false);
                    paddrline1.setFocusableInTouchMode(false);
                    paddrline1.setClickable(false);

                    paddrline2.setFocusable(false);
                    paddrline2.setFocusableInTouchMode(false);
                    paddrline2.setClickable(false);

                    paddrline3.setFocusable(false);
                    paddrline3.setFocusableInTouchMode(false);
                    paddrline3.setClickable(false);
                } else {

                    paddrline1.setText("");
                    paddrline2.setText("");
                    paddrline3.setText("");

                    paddrline1.setFocusable(true);
                    paddrline1.setFocusableInTouchMode(true);
                    paddrline1.setClickable(true);

                    paddrline2.setFocusable(true);
                    paddrline2.setFocusableInTouchMode(true);
                    paddrline2.setClickable(true);

                    paddrline3.setFocusable(true);
                    paddrline3.setFocusableInTouchMode(true);
                    paddrline3.setClickable(true);
                }
            }
        });


        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";


        try {
            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(username);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);
            emailedittext.setText(plainusername);
        } catch (Exception e) {
        }

        fname = s.getFname();
        mname = s.getMname();
        sname = s.getLname();
        nameasten = s.getNameasten();
        alternateemail = s.getEmail2();
        mothername = s.getMothername();
        dob = s.getDob();
        gender = s.getGender();

        phone = s.getTelephone();
        mobile = s.getPhone();

        alternatemobile = s.getMobile2();
        mothertongue = s.getMothertongue();
        hobbies = s.getHobbies();
        bloodgroup = s.getBloodgroup();
        category = s.getCategory();
        religion = s.getReligion();
        caste = s.getCaste();
        prn = s.getPrn();
        lang1 = s.getLang1();
        lang2 = s.getLang2();
        lang3 = s.getLang3();
        lang4 = s.getLang4();
        lang5 = s.getLang5();
        lang6 = s.getLang6();
        lang7 = s.getLang7();
        lang8 = s.getLang8();
        lang9 = s.getLang9();
        lang10 = s.getLang10();

        addrline1c = s.getAddressline1();

        addrline2c = s.getAddressline2();
        addrline3c = s.getAddressline3();
        addrline1p = s.getPaddrline1();
        addrline2p = s.getPaddrline2();
        addrline3p = s.getPaddrline3();
        handicapped = s.getHandicapped();
        sports = s.getSports();
        defenceex = s.getDefenceex();


        if (fname != null) {
            if (!fname.equals(""))
                fnameedittext.setText(fname);

        }
        if (mname != null) {
//            Toast.makeText(getActivity(), "mname"+mname, Toast.LENGTH_SHORT).show();
            if (!mname.equals(""))
                mnameedittext.setText(mname);

        }
        if (sname != null) {
            if (!sname.equals(""))
                snameedittext.setText(sname);

        }
        if (nameasten != null) {
            if (!nameasten.equals(""))
                nameastenedittext.setText(nameasten);

        }
        if (alternateemail != null) {
            if (!alternateemail.equals(""))
                profileaemail.setText(alternateemail);
        }
        if (mothername != null) {
            if (!mothername.equals(""))
                mothernameedittext.setText(mothername);

        }
        if (dob != null) {
            if (!dob.equals("")) {
                DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                String outputDateStrlastdateofreg = "";
                try {
                    Date date = inputFormat.parse(dob);
                    outputDateStrlastdateofreg = outputFormat.format(date);
                    dob = outputDateStrlastdateofreg;
                    dobedittext.setText(dob);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Enter Date of Birth Correctly..!", Toast.LENGTH_SHORT).show();
                    dobedittext.setError("Invalid Date");
                }
            }


        }
        if (gender != null) {
            if (gender.equalsIgnoreCase("male")) {
                radioButtonMale.setChecked(true);
            } else if (gender.equalsIgnoreCase("female")) {
                radioButtonFemale.setChecked(true);
            }
        }
        if (phone != null) {
            if (!phone.equals(""))
                phoneedittext.setText(phone);
        }
        if (mobile != null) {
            if (!mobile.equals(""))
                mobileedittext.setText(mobile);
        }
        if (alternatemobile != null) {
            if (!alternatemobile.equals(""))
                alternatemobileedittext.setText(alternatemobile);
        }
        if (mothertongue != null) {
            if (!mothertongue.equals(""))
                mothertongueedittext.setText(mothertongue);

        }
        if (hobbies != null) {
            if (!hobbies.equals(""))
                hobbiesedittext.setText(hobbies);

        }

        if (bloodgroup != null) {
            if (!bloodgroup.equals("") && !bloodgroup.equals("- Select Bloodgroup -"))
                bloodgrpspinner.setSelection(bloodgrpArrayAdapter.getPosition(bloodgroup));
        }
        if (category != null) {
            if (!category.equals("") && !category.equals("- Select Category -"))
                categoryspinner.setSelection(categoryArrayAdapter.getPosition(category));
        }
        if (religion != null) {
            if (!religion.equals("") && !religion.equals("- Select Religion -"))
                religionspinner.setSelection(religionArrayAdapter.getPosition(religion));
        }

        if (caste != null) {
            if (!caste.equals(""))
                casteedittext.setText(caste);

        }
        if (prn != null) {
            if (!prn.equals(""))
                prnedittext.setText(prn);

        }


        if (lang1 != null) {
            if (!lang1.equals("- Select Language -"))
                languagesknownedittext.setText(lang1);
        }
        if (lang1 != null && lang2 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2);
        }
        if (lang1 != null && lang2 != null && lang3 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null && lang8 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -") && !lang8.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7 + ", " + lang8);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null && lang8 != null && lang9 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -") && !lang8.equals("- Select Language -") && !lang9.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7 + ", " + lang8 + ", " + lang9);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null && lang8 != null && lang9 != null && lang10 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -") && !lang8.equals("- Select Language -") && !lang9.equals("- Select Language -") && !lang10.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7 + ", " + lang8 + ", " + lang9 + ", " + lang10);
        }
        if (addrline1c != null) {
            if (!addrline1c.equals(""))
                caddrline1.setText(addrline1c);
        }
        if (addrline2c != null) {
            if (!addrline2c.equals(""))
                caddrline2.setText(addrline2c);
        }
        if (addrline3c != null) {
            if (!addrline3c.equals(""))
                caddrline3.setText(addrline3c);
        }
        if (addrline1c != null && addrline1p != null && addrline2c != null && addrline2p != null && addrline3c != null && addrline3p != null) {
            if (addrline1c.equals(addrline1p) && addrline2c.equals(addrline2p) && addrline3c.equals(addrline3p)) {
                CheckBoxPSC.setChecked(true);
                paddrline1.setText(addrline1p);
                paddrline2.setText(addrline2p);
                paddrline3.setText(addrline3p);

                paddrline1.setFocusable(false);
                paddrline1.setFocusableInTouchMode(false);
                paddrline1.setClickable(false);

                paddrline2.setFocusable(false);
                paddrline2.setFocusableInTouchMode(false);
                paddrline2.setClickable(false);

                paddrline3.setFocusable(false);
                paddrline3.setFocusableInTouchMode(false);
                paddrline3.setClickable(false);

            }
        }
        if (handicapped != null) {
            if (handicapped.equals("notapplicable"))
                radioButtonHandicappedNo.setChecked(true);
            else if (handicapped.equals("yes"))
                radioButtonHandicappedYes.setChecked(true);
        }

//        sports
        if (sports != null) {

//            Toast.makeText(getActivity(), "sports "+sports, Toast.LENGTH_SHORT).show();
            if (sports.equals("NA")) {
                sports = "notapplicable";
                radioButtonSportsNo.setChecked(true);
            } else if (sports.equals("State")) {
                sports = "state";
                radioButtonSportsState.setChecked(true);
            } else if (sports.equals("National")) {
                sports = "national";
                radioButtonSportsNational.setChecked(true);
            } else if (sports.equals("International")) {
                sports = "international";
                radioButtonSportsInternational.setChecked(true);
            }
        }
        if (defenceex != null) {
            if (defenceex.equals("notapplicable"))
                radioButtonDefenceNo.setChecked(true);
            else if (defenceex.equals("defence"))
                radioButtonDefence.setChecked(true);
            else if (defenceex.equals("exserviceman"))
                radioButtonExserviceman.setChecked(true);
        }


        edittedFlag = 0;
        return rootView;

    }

    private void refreshKnowLang() {

        lang1 = s.getLang1();
        lang2 = s.getLang2();
        lang3 = s.getLang3();
        lang4 = s.getLang4();
        lang5 = s.getLang5();
        lang6 = s.getLang6();
        lang7 = s.getLang7();
        lang8 = s.getLang8();
        lang9 = s.getLang9();
        lang10 = s.getLang10();

        if (lang1 != null) {
            if (!lang1.equals("- Select Language -"))
                languagesknownedittext.setText(lang1);
        }
        if (lang1 != null && lang2 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2);
        }
        if (lang1 != null && lang2 != null && lang3 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null && lang8 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -") && !lang8.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7 + ", " + lang8);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null && lang8 != null && lang9 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -") && !lang8.equals("- Select Language -") && !lang9.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7 + ", " + lang8 + ", " + lang9);
        }
        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null && lang8 != null && lang9 != null && lang10 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -") && !lang8.equals("- Select Language -") && !lang9.equals("- Select Language -") && !lang10.equals("- Select Language -"))
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7 + ", " + lang8 + ", " + lang9 + ", " + lang10);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (fnameedittext != null) {
            refreshKnowLang();
//            edittedFlag = 0;
        }
    }


    public Boolean validate() {

        errorflag = 0;

        fname = fnameedittext.getText().toString();
        mname = mnameedittext.getText().toString();
        sname = snameedittext.getText().toString();

        nameasten = nameastenedittext.getText().toString();
        alternateemail = profileaemail.getText().toString();

        mothername = mothernameedittext.getText().toString();
        dob = dobedittext.getText().toString();
        phone = phoneedittext.getText().toString();
        mobile = mobileedittext.getText().toString();
        alternatemobile = alternatemobileedittext.getText().toString();

        mothertongue = mothertongueedittext.getText().toString();
        hobbies = hobbiesedittext.getText().toString();
        caste = casteedittext.getText().toString();
        prn = prnedittext.getText().toString();

        addrline1c = caddrline1.getText().toString();
        addrline2c = caddrline2.getText().toString();
        addrline3c = caddrline3.getText().toString();
        addrline1p = paddrline1.getText().toString();
        addrline2p = paddrline2.getText().toString();
        addrline3p = paddrline3.getText().toString();


        bloodgroup = bloodgrpspinner.getSelectedItem().toString();
        if (bloodgroup.equals("- Select Bloodgroup -")) {
            errorflag = 1;
//                    Toast.makeText(getActivity(), "Select Bloodgroup", Toast.LENGTH_LONG).show();
        }
        category = categoryspinner.getSelectedItem().toString();
        religion = religionspinner.getSelectedItem().toString();

        int selectedId;
        RadioButton tempradiobutton;
        try {
            selectedId = radioGroupGender.getCheckedRadioButtonId();
            tempradiobutton = (RadioButton) rootView.findViewById(selectedId);
            gender = tempradiobutton.getText().toString().trim();
        } catch (Exception e) {
            errorflag = 1;
            Toast.makeText(getActivity(), "Select Gender", Toast.LENGTH_LONG).show();
        }

        selectedId = radioGroupHandicapped.getCheckedRadioButtonId();
        tempradiobutton = (RadioButton) rootView.findViewById(selectedId);
        handicapped = tempradiobutton.getText().toString().trim();
//        if (handicapped.equals("Handicapped(NO)"))
        if (handicapped.equals("NO"))
            handicapped = "notapplicable";
//        else if (handicapped.equals("Handicapped(YES)"))
        else if (handicapped.equals("YES"))
            handicapped = "yes";

        selectedId = radioGroupSports.getCheckedRadioButtonId();
        tempradiobutton = (RadioButton) rootView.findViewById(selectedId);
        sports = tempradiobutton.getText().toString().trim();

        if (sports.equals("Sports(NA)"))
            sports = "notapplicable";
        else if (sports.equals("Sports(State)")) {
            Log.d("TAG", "validate: MATCH");
            sports = "state";
        } else if (sports.equals("Sports(National)"))
            sports = "national";
        else if (sports.equals("Sports(International)"))
            sports = "international";

//        if (sports.equals("NA"))
//            sports = "notapplicable";
//        else if (sports.equals("State")) {
//            sports = "state";
//        } else if (sports.equals("National"))
//            sports = "national";
//        else if (sports.equals("International"))
//            sports = "international";


        selectedId = radioGroupDefenceex.getCheckedRadioButtonId();
        tempradiobutton = (RadioButton) rootView.findViewById(selectedId);
        defenceex = tempradiobutton.getText().toString().trim();
//        if (defenceex.equals("Defence(NA)"))
        if (defenceex.equals("NA"))
            defenceex = "notapplicable";
        else if (defenceex.equals("Defence"))
            defenceex = "defence";
        else if (defenceex.equals("Ex-Serviceman"))
            defenceex = "exserviceman";


        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        String outputDateStrlastdateofreg = "";
        try {
            Date date = inputFormat.parse(dob);
            outputDateStrlastdateofreg = outputFormat.format(date);
            dob = outputDateStrlastdateofreg;
        } catch (Exception e) {
            Toast.makeText(getContext(), "Enter Date of Birth Correctly..!", Toast.LENGTH_SHORT).show();
            dobedittext.setError("Invalid Date");
        }

        if (fname.length() < 2) {
            errorflag = 1;
            fnameedittext.setError("Invalid Name");
        } else {
            errorflag = 0;
            if (mname.length() < 2) {
                errorflag = 1;
                mnameedittext.setError("Invalid Name");
            } else {
                errorflag = 0;
                if (nameasten.length() < 2) {
                    errorflag = 1;
                    nameastenedittext.setError("Invalid Name");
                } else {
                    errorflag = 0;
                    if (sname.length() < 2) {
                        errorflag = 1;
                        snameedittext.setError("Invalid Name");
                    } else {
                        errorflag = 0;


                        if (alternateemail.length() < 2 || !alternateemail.contains("@")) {
                            errorflag = 1;
                            profileaemail.setError("Invalid Email");
                        } else {
                            errorflag = 0;
                            if (mothername.length() < 2) {
                                errorflag = 1;
                                mothernameedittext.setError("Invalid Name");
                            } else {
                                errorflag = 0;
                                if (dob.length() < 2) {
                                    errorflag = 1;
                                    dobedittext.setError("Invalid Name");
                                } else {
                                    errorflag = 0;
                                    if (mobile.length() < 10 || mobile.length() > 10) {
                                        errorflag = 1;
                                        mobileedittext.setError("Mobile Number Should Have 10 Digits");
                                    } else {
                                        errorflag = 0;
                                        if (mothertongue.length() < 2) {
                                            errorflag = 1;
                                            mothertongueedittext.setError("Invalid Mothertongue");
                                        } else if (hobbies.length() < 2) {
                                            errorflag = 1;
                                            hobbiesedittext.setError("Invalid hobbies");
                                        } else if (caste.length() < 2) {
                                            errorflag = 1;
                                            casteedittext.setError("Invalid caste");
                                        } else if (prn.length() < 2) {
                                            errorflag = 1;
                                            prnedittext.setError("Invalid PRN");
                                        } else if (addrline1c.length() < 2) {
                                            errorflag = 1;
                                            caddrline1.setError("Invalid addrline1");
                                        } else if (addrline2c.length() < 2) {
                                            errorflag = 1;
                                            caddrline2.setError("Invalid addrline2");
                                        } else if (addrline3c.length() < 2) {
                                            errorflag = 1;
                                            caddrline3.setError("Invalid addrline3");
                                        } else if (addrline1p.length() < 2) {
                                            errorflag = 1;
                                            paddrline1.setError("Invalid addrline1");
                                            paddrline1.requestFocus();
                                        } else if (addrline2p.length() < 2) {
                                            errorflag = 1;
                                            paddrline2.setError("Invalid addrline2");
                                            paddrline2.requestFocus();
                                        } else if (addrline3p.length() < 2) {
                                            errorflag = 1;
                                            paddrline3.setError("Invalid addrline3");
                                            paddrline3.requestFocus();
                                        }


                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        if (errorflag == 0)
            return true;
        else
            return false;
    }

    public void save() {

        try {

            Log.d("TAG", "save: fname - "+fname);
            Log.d("TAG", "save: mname - "+mname);
            Log.d("TAG", "save: sname - "+sname);
            Log.d("TAG", "save: digest1 - "+digest1);
            Log.d("TAG", "save: digest2 - "+digest2);

            MyProfilePersonal obj=new MyProfilePersonal(fname,mname,sname,nameasten,alternateemail,mothername,dob,gender,phone,mobile,alternatemobile,mothertongue,hobbies,bloodgroup,category,religion,caste,prn,addrline1c,addrline2c,addrline3c,addrline1p,addrline2p,addrline3p,handicapped,sports,defenceex);
            encobj =OtoString(obj,digest1,digest2);


//             encobj =OtoString(obj,MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

            MyProfilePersonal obj2 = (MyProfilePersonal) fromString(encobj,digest1,digest2);

            Log.d("TAG", "save: "+obj2.fname +" "+obj2.sname);

            new MyAsyncTask().execute(encobj);

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dobedittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);

        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            dobedittext.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1)
                    + "/" + String.valueOf(year));
        }
    };

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

    class MyAsyncTask extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));        //0
            params.add(new BasicNameValuePair("d", encobj));        //1


            if (Myrole.equals("student")) {
                Log.d("student", "onCreateView: " + Myrole);
                json = jParser.makeHttpRequest(MyConstants.savepersonalinfo, "GET", params);
            }
            if (Myrole.equals("alumni")) {
                json = jParser.makeHttpRequest(MyConstants.savepersonalinfoAlumni, "GET", params);
            }


            try {
                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if (resultofop.equals("success")) {

                if (role.equals("student"))
                    getActivity().setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    getActivity().setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

//                Toast.makeText(getContext(), "Successfully Updated..!", Toast.LENGTH_SHORT).show();
                edittedFlag = 0;
            } else {
                Toast.makeText(getContext(), "try again", Toast.LENGTH_SHORT).show();
            }

//            save.setVisibility(View.VISIBLE);
            personalprogress.setVisibility(View.GONE);
        }
    }

}