package placeme.octopusites.com.placeme;

import android.app.DatePickerDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import placeme.octopusites.com.placeme.modal.AdminPersonal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;

public class AdminPersonalTabFragment extends Fragment {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    String digest1, digest2, encobj = "";
    EditText role, inst, fnameedittext, mnameedittext, snameedittext, nameastenedittext, caddrline1, caddrline2, caddrline3, paddrline1, paddrline2, paddrline3, emailedittext, phoneedittext, profileaemail, mothernameedittext, dobedittext, mobileedittext, alternatemobileedittext, mothertongueedittext, hobbiesedittext, casteedittext, prnedittext, languagesknownedittext;
    RadioButton radioButtonMale, radioButtonFemale, radioButtonHandicappedNo, radioButtonHandicappedYes, radioButtonSportsNo, radioButtonSportsState, radioButtonSportsNational, radioButtonSportsInternational, radioButtonDefenceNo, radioButtonDefence, radioButtonExserviceman;
    Spinner bloodgrpspinner, categoryspinner, religionspinner;
    String fname = "", mname = "", sname = "", nameasten = "", semail = "", alternateemail = "", srole = "", sinst = "", mothername = "", dob = "", gender = "", mobile = "", phone = "", alternatemobile = "", mothertongue = "", hobbies = "", bloodgroup = "", category = "", religion = "", caste = "", prn = "", handicapped = "", sports = "", defenceex = "";
    String lang1 = "", lang2 = "", lang3 = "", lang4 = "", lang5 = "", lang6 = "", lang7 = "", lang8 = "", lang9 = "", lang10 = "", addrline1c = "", addrline2c = "", addrline3c = "", addrline1p = "", addrline2p = "", addrline3p = "";
    String encfname, encmname, encsname, encnameasten, encalternateemail, encmothername, encdob, encgender, encmobile, encphone, encalternatemobile, encmothertongue, enchobbies, encbloodgroup, enccategory, encreligion, enccaste, encprn, enchandicapped, encsports, encdefenceex;
    String encaddrline1c, encaddrline2c, encaddrline3c, encaddrline1p, encaddrline2p, encaddrline3p;
    CheckBox CheckBoxPSC;
    int errorflag1 = 0;
    RadioGroup radioGroupGender;
    JSONObject json;
    JSONParser jParser = new JSONParser();


    private static String url_savedata = "http://192.168.100.100/AESTest/SaveAdminPersonal";



    int countrycount = 0, statecount = 0, citycount = 0;
    String firstname = "", lastname = "", instname = "";
    String oldCountry = "", oldState = "", oldCity = "";
    String countries[], states[], cities[];
    Spinner country, state, city;
    List<String> countrieslist = new ArrayList<String>();
    List<String> stateslist = new ArrayList<String>();
    List<String> citieslist = new ArrayList<String>();
    String selectedCountry = "", selectedState = "", selectedCity = "";
    String encUsername, encRole, encemail, encFname, encLname, encCountry, encState, encCity, encInst;
    //    Button save;
    String username, plainusername;
    //    ProgressBar personalprogress;
    View rootView;


    //ssss
    AdminData a = new AdminData();
    StudentData s = new StudentData();
    int edittedFlag = 0, isCountrySet = 0, isStateSet = 0, isCitySet = 0;
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            dobedittext.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1)
                    + "/" + String.valueOf(year));
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_edit_profile_admin_personal, container, false);

        fnameedittext = (EditText) rootView.findViewById(R.id.fname);
        mnameedittext = (EditText) rootView.findViewById(R.id.mname);
        snameedittext = (EditText) rootView.findViewById(R.id.sname);
        role = (EditText) rootView.findViewById(R.id.role);
        emailedittext = (EditText) rootView.findViewById(R.id.email);
        emailedittext.setFocusable(false);
        profileaemail = (EditText) rootView.findViewById(R.id.profileaemail);
        inst = (EditText) rootView.findViewById(R.id.inst);
        dobedittext = (EditText) rootView.findViewById(R.id.dob);
        dobedittext.setFocusable(false);
        radioGroupGender = (RadioGroup) rootView.findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton) rootView.findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton) rootView.findViewById(R.id.radioButtonFemale);
        caddrline1 = (EditText) rootView.findViewById(R.id.caddrline1);
        caddrline2 = (EditText) rootView.findViewById(R.id.caddrline2);
        caddrline3 = (EditText) rootView.findViewById(R.id.caddrline3);
        CheckBoxPSC = (CheckBox) rootView.findViewById(R.id.CheckBoxPSC);
        paddrline1 = (EditText) rootView.findViewById(R.id.paddrline1);
        paddrline2 = (EditText) rootView.findViewById(R.id.paddrline2);
        paddrline3 = (EditText) rootView.findViewById(R.id.paddrline3);
//        save = (Button) rootView.findViewById(R.id.savepersonal);
//        personalprogress = (ProgressBar) rootView.findViewById(R.id.personalprogress);



        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        username=MySharedPreferencesManager.getUsername(getActivity());
        srole=MySharedPreferencesManager.getRole(getActivity());
        encUsername =username;
        role.setText(srole.toUpperCase());

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
        //getters

        fname = a.getFname();
        mname = a.getMname();
        sname = a.getLname();
        sinst = a.getInstitute();

        alternateemail = s.getEmail2();
        dob = s.getDob();
        gender = s.getGender();
        addrline1c = s.getAddressline1();
        addrline2c = s.getAddressline2();
        addrline3c = s.getAddressline3();
        addrline1p = s.getPaddrline1();
        addrline2p = s.getPaddrline2();
        addrline3p = s.getPaddrline3();

        if (fname != null) {
            if (!fname.equals(""))
                fnameedittext.setText(fname);
        }
        if (mname != null) {
            if (!mname.equals(""))
                mnameedittext.setText(mname);
        }
        if (sname != null) {
            if (!sname.equals(""))
                snameedittext.setText(sname);
        }
        if (alternateemail != null) {
            if (!alternateemail.equals(""))
                profileaemail.setText(alternateemail);
        }
        if (sinst != null) {
            if (!sinst.equals(""))
                inst.setText(sinst);
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
//                    dobedittext.setError("Invalid Date");
                }
            }


        }
//        Toast.makeText(getActivity(), "gender"+gender, Toast.LENGTH_LONG).show();

        if (gender != null) {
            if (gender.equalsIgnoreCase("male")) {
                radioButtonMale.setChecked(true);
            } else if (gender.equalsIgnoreCase("female")) {
                radioButtonFemale.setChecked(true);
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
        }

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
        inst.addTextChangedListener(new TextWatcher() {
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
                dob = dobedittext.getText().toString();
//                Toast.makeText(getContext(),"dob"+dob, Toast.LENGTH_LONG).show();

                Calendar calendar = Calendar.getInstance();
                DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                String currntDate = inputFormat.format(calendar.getTime());
//                Toast.makeText(getContext(),"currntDate:"+currntDate, Toast.LENGTH_LONG).show();


                Date dt2 = null;
                Date dt3 = null;

                try {
                    dt2 = inputFormat.parse(dob);
                    dt3 = inputFormat.parse(currntDate);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "parsing error", Toast.LENGTH_SHORT).show();
                }
                DateTime dt11 = new DateTime(dt2);
                DateTime dt22 = new DateTime(dt3);

                int old = Years.yearsBetween(dt11, dt22).getYears();
//                    Toast.makeText(getContext(),"age years"+old, Toast.LENGTH_LONG).show();
                if (old < 18) {
                    dobedittext.setError("Invalid Date");
//                        Toast.makeText(getContext(),"U Must Be 18 Years old,in Order use Place me...", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Please Enter Valid Date Of Birth", Toast.LENGTH_SHORT).show();

                } else {
                    dobedittext.setError(null);

                }


            }
        });


        caddrline1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                caddrline1.setError(null);
                String Editvalue = caddrline1.getText().toString();
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
                caddrline2.setError(null);
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
                caddrline3.setError(null);
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
        CheckBoxPSC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

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
        edittedFlag = 0;


//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
        return rootView;

    }

    public Boolean validate() {

        errorflag1 = 0;

        fname = fnameedittext.getText().toString();
        mname = mnameedittext.getText().toString();
        sname = snameedittext.getText().toString();
//        srole = role.getText().toString();
        semail = emailedittext.getText().toString();
        alternateemail = profileaemail.getText().toString();
        sinst = inst.getText().toString();
        dob = dobedittext.getText().toString();

        addrline1c = caddrline1.getText().toString();
        addrline2c = caddrline2.getText().toString();
        addrline3c = caddrline3.getText().toString();
        addrline1p = paddrline1.getText().toString();
        addrline2p = paddrline2.getText().toString();
        addrline3p = paddrline3.getText().toString();

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

        int selectedId;
        RadioButton tempradiobutton;
        try {
            selectedId = radioGroupGender.getCheckedRadioButtonId();
            tempradiobutton = (RadioButton) rootView.findViewById(selectedId);
            gender = tempradiobutton.getText().toString().trim();
        } catch (Exception e) {
            errorflag1 = 1;
            Toast.makeText(getActivity(), "Select Gender", Toast.LENGTH_LONG).show();
        }


        if (fname.length() < 2) {
            fnameedittext.setError("Incorrect First Name");
            errorflag1 = 1;
        } else if (mname.length() < 2) {
            mnameedittext.setError("Incorrect Middle Name");
            errorflag1 = 1;
        } else if (sname.length() < 2) {
            snameedittext.setError("Incorrect Last Name");
            errorflag1 = 1;
        } else if (!alternateemail.contains("@")) {
            profileaemail.setError("Incorrect Email Address");
            errorflag1 = 1;
        } else if (sinst.length() < 2) {
            inst.setError("Incorrect Institute name");
            errorflag1 = 1;
        } else if (dob.length() < 2) {
            Toast.makeText(getContext(), "Select date", Toast.LENGTH_SHORT).show();
            errorflag1 = 1;
        } else if (addrline1c.length() < 2) {
            caddrline1.setError("Incorrect Address");
            errorflag1 = 1;
        } else if (addrline2c.length() < 2) {
            caddrline2.setError("Incorrect Address");
            errorflag1 = 1;
        } else if (addrline3c.length() < 2) {
            caddrline3.setError("Incorrect Address");
            errorflag1 = 1;
        } else if (addrline1p.length() < 2) {
            paddrline1.setError("Incorrect Address");
            errorflag1 = 1;
        } else if (addrline2p.length() < 2) {
            paddrline2.setError("Incorrect Address");
            errorflag1 = 1;
        } else if (addrline3p.length() < 2) {
            paddrline3.setError("Incorrect Address");
            errorflag1 = 1;
        }
        if (errorflag1 == 0)
            return true;

        return false;
    }

    public void save() {

        if (errorflag1 == 0) {
            try {

                Log.d("TAG", "save: fname - "+fname);
                Log.d("TAG", "save: mname - "+mname);
                Log.d("TAG", "save: lname - "+sname);
                Log.d("TAG", "save: alternateemail - "+alternateemail);
                Log.d("TAG", "save: sinst - "+sinst);
                Log.d("TAG", "save: dob - "+dob);
                Log.d("TAG", "save: gender - "+gender);
                Log.d("TAG", "save: addrline1c - "+addrline1c);
                Log.d("TAG", "save: addrline2c - "+addrline2c);
                Log.d("TAG", "save: addrline3c - "+addrline3c);


                AdminPersonal obj = new AdminPersonal(fname, mname, sname, alternateemail, sinst, dob, gender, addrline1c, addrline2c, addrline3c, addrline1p, addrline2p, addrline3p);
                encobj = OtoString(obj, digest1, digest2);
                Log.d("TAG", "save: encobj - "+encobj);
                new SaveData().execute();


            } catch (Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));   //0
            params.add(new BasicNameValuePair("p", encobj));     //1

            json = jParser.makeHttpRequest(MyConstants.url_SaveAdminPersonal, "GET", params);


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
                Toast.makeText(getActivity(), "Successfully Saved..!", Toast.LENGTH_SHORT).show();
                if (edittedFlag == 1) {
                    getActivity().setResult(111);

                }
                edittedFlag = 0;
//                personalprogress.setVisibility(View.GONE);
//                save.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

            }
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