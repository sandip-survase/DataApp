package placeme.octopusites.com.placeme;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import static placeme.octopusites.com.placeme.AES4all.fromString;


public class PersonalProfileTabFragment extends Fragment  {
    Activity mActivity;

    EditText fnameedittext, mnameedittext, snameedittext, nameastenedittext, caddrline1, caddrline2, caddrline3, paddrline1, paddrline2, paddrline3, emailedittext, phoneedittext, profileaemail, mothernameedittext, dobedittext, mobileedittext, alternatemobileedittext, mothertongueedittext, hobbiesedittext, casteedittext, prnedittext, languagesknownedittext;
    RadioButton radioButtonMale, radioButtonFemale, radioButtonHandicappedNo, radioButtonHandicappedYes, radioButtonSportsNo, radioButtonSportsState, radioButtonSportsNational, radioButtonSportsInternational, radioButtonDefenceNo, radioButtonDefence, radioButtonExserviceman;
    Spinner bloodgrpspinner, categoryspinner, religionspinner;
    String fname = "", mname = "", sname = "", nameasten = "", alternateemail = "", mothername = "", dob = "", gender = "", mobile = "", phone = "", alternatemobile = "", mothertongue = "", hobbies = "", bloodgroup = "", category = "", religion = "", caste = "", prn = "", handicapped = "", sports = "", defenceex = "";
    String lang1 = "", lang2 = "", lang3 = "", lang4 = "", lang5 = "", lang6 = "", lang7 = "", lang8 = "", lang9 = "", lang10 = "", addrline1c = "", addrline2c = "", addrline3c = "", addrline1p = "", addrline2p = "", addrline3p = "";
    RadioGroup radioGroupGender, radioGroupHandicapped, radioGroupSports, radioGroupDefenceex;
    JSONParser jParser = new JSONParser();

    JSONObject json;
    String resultofop = "", encobj = "";
    ProgressBar personalprogress;
    ImageView editknownlang;

    String username, plainusername, Myrole, role;
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
    TextInputLayout fnameinput, mnameinput, snameinput, nameas10input, profileemailinput, profileaemailinput, mothernameinput, dobinput, phoneinput, mobileinput, amobileinput, mothertongueinput, hobbiesinput, castinput, prninput, knownlanginput, caddrline1input, caddrline2input, caddrline3input, paddrline1input, paddrline2input, paddrline3input;
    TextView gendertxt, paddrtxt, handicappedtxt, sportstxt, defencetxt, caddrtxt;

    View rootView;
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            dobedittext.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1)
                    + "/" + String.valueOf(year));
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_edit_profile_personal, container, false);

        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        username = MySharedPreferencesManager.getUsername(getActivity());
        role = MySharedPreferencesManager.getRole(getActivity());
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
        editknownlang = (ImageView) rootView.findViewById(R.id.editknownlang);
        gendertxt = (TextView) rootView.findViewById(R.id.gendertxt);
        paddrtxt = (TextView) rootView.findViewById(R.id.paddrtxt);
        handicappedtxt = (TextView) rootView.findViewById(R.id.handicappedtxt);
        sportstxt = (TextView) rootView.findViewById(R.id.sportstxt);
        defencetxt = (TextView) rootView.findViewById(R.id.defencetxt);
        caddrtxt = (TextView) rootView.findViewById(R.id.caddrtxt);

        CheckBoxPSC.setTypeface(Z.getBold(getActivity()));
        radioButtonMale.setTypeface(Z.getBold(getActivity()));
        radioButtonFemale.setTypeface(Z.getBold(getActivity()));
        radioButtonHandicappedNo.setTypeface(Z.getBold(getActivity()));
        radioButtonHandicappedYes.setTypeface(Z.getBold(getActivity()));
        radioButtonSportsNo.setTypeface(Z.getBold(getActivity()));
        radioButtonSportsState.setTypeface(Z.getBold(getActivity()));
        radioButtonSportsNational.setTypeface(Z.getBold(getActivity()));
        radioButtonSportsInternational.setTypeface(Z.getBold(getActivity()));
        radioButtonDefenceNo.setTypeface(Z.getBold(getActivity()));
        radioButtonDefence.setTypeface(Z.getBold(getActivity()));
        radioButtonExserviceman.setTypeface(Z.getBold(getActivity()));

        gendertxt.setTypeface(Z.getLight(getActivity()));
        paddrtxt.setTypeface(Z.getLight(getActivity()));
        handicappedtxt.setTypeface(Z.getLight(getActivity()));
        sportstxt.setTypeface(Z.getLight(getActivity()));
        defencetxt.setTypeface(Z.getLight(getActivity()));
        caddrtxt.setTypeface(Z.getLight(getActivity()));

        fnameinput = (TextInputLayout) rootView.findViewById(R.id.fnameinput);
        mnameinput = (TextInputLayout) rootView.findViewById(R.id.mnameinput);
        snameinput = (TextInputLayout) rootView.findViewById(R.id.snameinput);
        nameas10input = (TextInputLayout) rootView.findViewById(R.id.nameas10input);
        profileemailinput = (TextInputLayout) rootView.findViewById(R.id.profileemailinput);
        profileaemailinput = (TextInputLayout) rootView.findViewById(R.id.profileaemailinput);
        mothernameinput = (TextInputLayout) rootView.findViewById(R.id.mothernameinput);
        dobinput = (TextInputLayout) rootView.findViewById(R.id.dobinput);
        phoneinput = (TextInputLayout) rootView.findViewById(R.id.phoneinput);
        mobileinput = (TextInputLayout) rootView.findViewById(R.id.mobileinput);
        amobileinput = (TextInputLayout) rootView.findViewById(R.id.amobileinput);
        mothertongueinput = (TextInputLayout) rootView.findViewById(R.id.mothertongueinput);
        hobbiesinput = (TextInputLayout) rootView.findViewById(R.id.hobbiesinput);
        castinput = (TextInputLayout) rootView.findViewById(R.id.castinput);
        prninput = (TextInputLayout) rootView.findViewById(R.id.prninput);
        knownlanginput = (TextInputLayout) rootView.findViewById(R.id.knownlanginput);
        caddrline1input = (TextInputLayout) rootView.findViewById(R.id.caddrline1input);
        caddrline2input = (TextInputLayout) rootView.findViewById(R.id.caddrline2input);
        caddrline3input = (TextInputLayout) rootView.findViewById(R.id.caddrline3input);
        paddrline1input = (TextInputLayout) rootView.findViewById(R.id.paddrline1input);
        paddrline2input = (TextInputLayout) rootView.findViewById(R.id.paddrline2input);
        paddrline3input = (TextInputLayout) rootView.findViewById(R.id.paddrline3input);

        fnameinput.setTypeface(Z.getLight(getActivity()));
        mnameinput.setTypeface(Z.getLight(getActivity()));
        snameinput.setTypeface(Z.getLight(getActivity()));
        nameas10input.setTypeface(Z.getLight(getActivity()));
        profileemailinput.setTypeface(Z.getLight(getActivity()));
        profileaemailinput.setTypeface(Z.getLight(getActivity()));
        mothernameinput.setTypeface(Z.getLight(getActivity()));
        dobinput.setTypeface(Z.getLight(getActivity()));
        phoneinput.setTypeface(Z.getLight(getActivity()));
        mobileinput.setTypeface(Z.getLight(getActivity()));
        amobileinput.setTypeface(Z.getLight(getActivity()));
        mothertongueinput.setTypeface(Z.getLight(getActivity()));
        hobbiesinput.setTypeface(Z.getLight(getActivity()));
        castinput.setTypeface(Z.getLight(getActivity()));
        prninput.setTypeface(Z.getLight(getActivity()));
        knownlanginput.setTypeface(Z.getLight(getActivity()));
        caddrline1input.setTypeface(Z.getLight(getActivity()));
        caddrline2input.setTypeface(Z.getLight(getActivity()));
        caddrline3input.setTypeface(Z.getLight(getActivity()));
        paddrline1input.setTypeface(Z.getLight(getActivity()));
        paddrline2input.setTypeface(Z.getLight(getActivity()));
        paddrline3input.setTypeface(Z.getLight(getActivity()));

        fnameedittext.setTypeface(Z.getBold(getActivity()));
        mnameedittext.setTypeface(Z.getBold(getActivity()));
        snameedittext.setTypeface(Z.getBold(getActivity()));
        nameastenedittext.setTypeface(Z.getBold(getActivity()));
        caddrline1.setTypeface(Z.getBold(getActivity()));
        caddrline2.setTypeface(Z.getBold(getActivity()));
        caddrline3.setTypeface(Z.getBold(getActivity()));
        paddrline1.setTypeface(Z.getBold(getActivity()));
        paddrline2.setTypeface(Z.getBold(getActivity()));
        paddrline3.setTypeface(Z.getBold(getActivity()));
        emailedittext.setTypeface(Z.getBold(getActivity()));
        phoneedittext.setTypeface(Z.getBold(getActivity()));
        profileaemail.setTypeface(Z.getBold(getActivity()));
        mothernameedittext.setTypeface(Z.getBold(getActivity()));
        dobedittext.setTypeface(Z.getBold(getActivity()));
        mobileedittext.setTypeface(Z.getBold(getActivity()));
        alternatemobileedittext.setTypeface(Z.getBold(getActivity()));
        mothertongueedittext.setTypeface(Z.getBold(getActivity()));
        hobbiesedittext.setTypeface(Z.getBold(getActivity()));
        casteedittext.setTypeface(Z.getBold(getActivity()));
        prnedittext.setTypeface(Z.getBold(getActivity()));
        languagesknownedittext.setTypeface(Z.getBold(getActivity()));


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
                fnameinput.setError(null);
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
                mnameinput.setError(null);
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
                snameinput.setError(null);
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
                nameas10input.setError(null);
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
                profileaemailinput.setError(null);
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
                mothernameinput.setError(null);
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
                dobinput.setError(null);
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
                phoneinput.setError(null);
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
                mobileinput.setError(null);
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
                amobileinput.setError(null);
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
                mothertongueinput.setError(null);
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
                hobbiesinput.setError(null);
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
                prninput.setError(null);
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
                caddrline1input.setError(null);
                String Editvalue = "";
                Editvalue = caddrline1.getText().toString();

                if (addrline1c != null) {
                    if (!addrline1c.equals(Editvalue)) {

                        edittedFlag = 1;
                        CheckBoxPSC.setChecked(false);
                    }
                } else {
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
                caddrline2input.setError(null);

                String Editvalue = caddrline2.getText().toString();
                if (addrline2c != null) {
                    if (!addrline2c.equals(Editvalue)) {
                        edittedFlag = 1;
                        CheckBoxPSC.setChecked(false);
                    }
                } else {
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
                caddrline3input.setError(null);
                String Editvalue = caddrline3.getText().toString();
                if (addrline3c != null) {
                    if (!addrline3c.equals(Editvalue)) {
                        edittedFlag = 1;
                        CheckBoxPSC.setChecked(false);
                    }
                } else {
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
                paddrline1input.setError(null);
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
                paddrline2input.setError(null);
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
                paddrline3input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


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

                    return false;
                } else {
                    return true;
                }
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view =super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(getActivity()));
                return view;
            }


            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(getActivity()));
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.dark_color));
                }
                return view;
            }
        };

        religionArrayAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item, religionlist) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {

                    return false;
                } else {
                    return true;
                }
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view =super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(getActivity()));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(getActivity()));
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.dark_color));
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

                    return false;
                } else {
                    return true;
                }
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view =super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(getActivity()));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(getActivity()));
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.dark_color));
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
//

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
//
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
//
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
                if(lang1.length()>1)
                languagesknownedittext.setText(lang1);
            else
                languagesknownedittext.setText("");

        }

        if (lang1 != null && lang2 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -"))
                if(lang1.length()>1)
                    languagesknownedittext.setText(lang1 + ", " + lang2);
            else
                languagesknownedittext.setText("");

        }

        if (lang1 != null && lang2 != null && lang3 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -"))
                if(lang1.length()>1)
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3);
            else
                languagesknownedittext.setText("");

        }

        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -"))
                if(lang1.length()>1)
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4);
            else
                languagesknownedittext.setText("");

        }

        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -"))
                if(lang1.length()>1)
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5);
            else
                languagesknownedittext.setText("");

        }

        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -"))
                if(lang1.length()>1)
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6);
            else
                languagesknownedittext.setText("");

        }


        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -"))
                if(lang1.length()>1)
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7);
            else
                languagesknownedittext.setText("");

        }

        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null && lang8 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -") && !lang8.equals("- Select Language -"))
                if(lang1.length()>1)
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7 + ", " + lang8);
            else
                languagesknownedittext.setText("");

        }

        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null && lang8 != null && lang9 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -") && !lang8.equals("- Select Language -") && !lang9.equals("- Select Language -"))
                if(lang1.length()>1)
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7 + ", " + lang8 + ", " + lang9);
            else
                languagesknownedittext.setText("");

        }

        if (lang1 != null && lang2 != null && lang3 != null && lang4 != null && lang5 != null && lang6 != null && lang7 != null && lang8 != null && lang9 != null && lang10 != null) {
            if (!lang1.equals("- Select Language -") && !lang2.equals("- Select Language -") && !lang3.equals("- Select Language -") && !lang4.equals("- Select Language -") && !lang5.equals("- Select Language -") && !lang6.equals("- Select Language -") && !lang7.equals("- Select Language -") && !lang8.equals("- Select Language -") && !lang9.equals("- Select Language -") && !lang10.equals("- Select Language -"))
                if(lang1.length()>1)
                languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7 + ", " + lang8 + ", " + lang9 + ", " + lang10);
            else
            {
                languagesknownedittext.setText("No languages");
            }
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
            else {

                if (addrline1p != null) {
                    if (!addrline1p.equals(""))
                        paddrline1.setText(addrline1p);
                }
                if (addrline2p != null) {
                    if (!addrline2p.equals(""))
                        paddrline2.setText(addrline2p);
                }
                if (addrline3p != null) {
                    if (!addrline3p.equals(""))
                        paddrline3.setText(addrline3p);
                }
            }
        }

        if (handicapped != null) {
            if (handicapped.equals("notapplicable"))
                radioButtonHandicappedNo.setChecked(true);
            else if (handicapped.equals("yes"))
                radioButtonHandicappedYes.setChecked(true);
        }

        if (sports != null) {

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
                if(lang1.length()>1)
                    languagesknownedittext.setText(lang1 + ", " + lang2 + ", " + lang3 + ", " + lang4 + ", " + lang5 + ", " + lang6 + ", " + lang7 + ", " + lang8 + ", " + lang9 + ", " + lang10);
                else
                {
                    languagesknownedittext.setText("No languages");
                }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (fnameedittext != null) {
            refreshKnowLang();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (fnameedittext != null) {
            refreshKnowLang();
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
//            errorflag = 1;
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
//
        }

        selectedId = radioGroupHandicapped.getCheckedRadioButtonId();
        tempradiobutton = (RadioButton) rootView.findViewById(selectedId);
        handicapped = tempradiobutton.getText().toString().trim();
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
            sports = "state";
        } else if (sports.equals("Sports(National)"))
            sports = "national";
        else if (sports.equals("Sports(International)"))
            sports = "international";

//

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
//
        }


        errorflag=0;

        if (fname.length() < 2) {
            errorflag = 1;
            fnameinput.setError("Kindly enter valid name.");
        }
        else if (sname.length() < 2) {
            errorflag = 1;
            snameinput.setError("Kindly enter valid name.");
        }
          else if (alternateemail.length()!=0){
            if(alternateemail.length() < 6 || !alternateemail.contains("@")){
                errorflag = 1;
                profileaemailinput.setError("Kindly enter valid Email");
            }

        } else if (dob.length() < 2) {
            errorflag = 1;
            dobinput.setError("Kindly enter valid date of birth");
        }
        else if (mobile.length() < 10) {
            errorflag = 1;
            mobileinput.setError("Kindly enter valid 10-digit mobile number");
        }
        else if (hobbies.length() < 2) {
            errorflag = 1;
            hobbiesinput.setError("Kindly enter valid hobbies");
        }
         else if (addrline1c.length() < 2) {
            errorflag = 1;
            caddrline1input.setError("Kindly enter valid address ");
        } else if (addrline2c.length() < 2) {
            errorflag = 1;
            caddrline2input.setError("Kindly enter valid address ");
        } else if (addrline3c.length() < 2) {
            errorflag = 1;
            caddrline3input.setError("Kindly enter valid address ");
        }


        if (errorflag == 0)
            return true;
        else
            return false;
    }

    public void save() {

        try {


            MyProfilePersonal obj = new MyProfilePersonal(fname, mname, sname, nameasten, alternateemail, mothername, dob, gender, phone, mobile, alternatemobile, mothertongue, hobbies, bloodgroup, category, religion, caste, prn, addrline1c, addrline2c, addrline3c, addrline1p, addrline2p, addrline3p, handicapped, sports, defenceex);
            encobj = OtoString(obj, digest1, digest2);


            MyProfilePersonal obj2 = (MyProfilePersonal) fromString(encobj, digest1, digest2);


            new MyAsyncTask().execute(encobj);

        } catch (Exception e) {
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
        args.putString("validate", "max");
        date.setArguments(args);

        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
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

    class MyAsyncTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));        //0
            params.add(new BasicNameValuePair("d", encobj));        //1

                json = jParser.makeHttpRequest(Z.savepersonalinfo, "GET", params);

            try {
                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {

                if (role.equals("student")) {
                    Log.d("TAG", "onPostExecute: student");
                    mActivity.setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                } else if (role.equals("alumni")) {

                    mActivity.setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                }

                edittedFlag = 0;
            } else {
                Toast.makeText(getActivity(), "Try again", Toast.LENGTH_SHORT).show();
            }

            personalprogress.setVisibility(View.GONE);

            s.setFname(fname);
            s.setMname(mname);
            s.setLname(sname);
            s.setNameasten(nameasten);
            s.setEmail2(alternateemail);
            s.setMothername(mothername);
            s.setDob(dob);
            s.setGender(gender);
            s.setTelephone(phone);
            s.setPhone(mobile);
            s.setMobile2(alternatemobile);
            s.setMothertongue(mothertongue);
            s.setHobbies(hobbies);
            s.setBloodgroup(bloodgroup);
            s.setCategory(category);
            s.setReligion(religion);
            s.setCaste(caste);
            s.setPrn(prn);


            s.setLang1(lang1);
            s.setLang2(lang2);
            s.setLang3(lang3);
            s.setLang4(lang4);
            s.setLang5(lang5);
            s.setLang6(lang6);
            s.setLang7(lang7);
            s.setLang8(lang8);
            s.setLang9(lang9);
            s.setLang10(lang10);


            s.setAddressline1(addrline1c);
            s.setAddressline2(addrline2c);
            s.setAddressline3(addrline3c);
            s.setPaddrline1(addrline1p);
            s.setPaddrline2(addrline2p);
            s.setPaddrline3(addrline3p);
            s.setHandicapped(handicapped);
            s.setSports(sports);
            s.setDefenceex(defenceex);

        }

    }

}