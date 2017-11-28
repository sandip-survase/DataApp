package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class WelcomeGenrateCodeActivity extends AppCompatActivity {


    String CODE, COUNTRY;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private Button btnNext;
    ProgressBar nextProgress;
    private ImageView resultView;
    private TextView[] dots;
    FrameLayout mainfragment;
    int currentPosition = 0, lastPosition = 0;
    private LinearLayout dotsLayout;
    String ROLE;
    public int pos;
    Boolean errorFlagInstitute = false, errorFlagCompany = false;
    String digest1, digest2;
    String CompanyType = "";
    private int path;
    JSONParser jsonParser = new JSONParser();
    JSONObject json;
    View WelComeCompanyView, WelComeInstituteView, WelComeShowCodeView;
    String encUsername, encFirstName, encLastName, encPassword, encAdminPhone, encrole, encProfessionalEmail, enccountry;
    EditText instituteName, instituteAddress, instituteEmail, institutewebsite, institutephone, instituteAlternatephone, university, regNumber;
    String sInstituteName, sInstituteAddress, sInstituteEmail, sInstitutewebsite, sInstitutephone, sInstituteAlternatephone = "", sUniversity, sRegNumber;
    String encInstituteName, encInstituteAddress, encInstituteEmail, encInstituteInstitutewebsite, encInstitutephone, encInstituteAlternatephone = "", encUniversity, encRegNumber;
    EditText companyName, companyAddress, companyEmail, companyWebsite, companyPhone, companyAlternatephone, CIN, otherNature;
    String sCompanyName, sCompanyAddress, sCompanyEmail, sCompanyWebsite, sCompanyPhone, sCompanyAlternatephone = "", sCIN, nature, sOtherNature = "";
    String encCompanyName, encCompanyAddress, encCompanyEmail, encCompanyWebsite, encCompanyPhone, encCompanyAlternatephone = "", encCIN, encOtherNature;
    String[] Nature = {"-Select Company Nature-", "Partnership", "Proprietory", "LLP (Limited Liability)", "Private Limited", "Public Limited", "Inc", "other"};
    Spinner companyNature;
    ArrayAdapter<String> dataAdapter;
    List<String> countrieslist = new ArrayList<String>();
    AutoCompleteTextView countryAutoBox,countryAutoBoxCompany;
    String countries[];
    int countrycount;
    private static  String android_id,device_id;
    TextView helloMsgcode, genratedCode,headerMsgcode;


    public void setWelComeShowCodeView(View v) {
        WelComeShowCodeView = v;
        helloMsgcode = (TextView) WelComeShowCodeView.findViewById(R.id.helloMsgcode);
        genratedCode = (TextView) WelComeShowCodeView.findViewById(R.id.genratedCode);
        headerMsgcode = (TextView) WelComeShowCodeView.findViewById(R.id.headerMsgcode);

    }

    public void setWelComeInstituteView(View v) {
        WelComeInstituteView = v;
        instituteName = (EditText) WelComeInstituteView.findViewById(R.id.instituteName);
        instituteAddress = (EditText) WelComeInstituteView.findViewById(R.id.instituteAddress);
        instituteEmail = (EditText) WelComeInstituteView.findViewById(R.id.instituteEmail);
        institutewebsite = (EditText) WelComeInstituteView.findViewById(R.id.institutewebsite);
        institutephone = (EditText) WelComeInstituteView.findViewById(R.id.institutephone);
        instituteAlternatephone = (EditText) WelComeInstituteView.findViewById(R.id.instituteAlternatephone);
        university = (EditText) WelComeInstituteView.findViewById(R.id.university);
        regNumber = (EditText) WelComeInstituteView.findViewById(R.id.regNumber);
        countryAutoBox = (AutoCompleteTextView) WelComeInstituteView.findViewById(R.id.countryAutoBox);

        countrycount = getResources().getStringArray(R.array.countries_array).length;
        countries = new String[countrycount];
        countries = getResources().getStringArray(R.array.countries_array);

        countrieslist.clear();
        for (int i = 0; i < countrycount; i++) {
            countrieslist.add(countries[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countrieslist);
        countryAutoBox.setAdapter(adapter);

        instituteAlternatephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(instituteAlternatephone.length()>0){
                    if(instituteAlternatephone.length()<8 ){
                        errorFlagInstitute=true;
                        instituteAlternatephone.setError("Enter valid phone number");
                    }
                    else
                        errorFlagInstitute=false;
                }else
                    errorFlagInstitute=false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void setWelComeCompanyView(View v) {     // --------------   WelComeCompanyView

        WelComeCompanyView = v;
        companyName = (EditText) WelComeCompanyView.findViewById(R.id.companyName);
        companyAddress = (EditText) WelComeCompanyView.findViewById(R.id.companyAddress);
        companyEmail = (EditText) WelComeCompanyView.findViewById(R.id.companyEmail);
        companyWebsite = (EditText) WelComeCompanyView.findViewById(R.id.companyWebsite);
        companyPhone = (EditText) WelComeCompanyView.findViewById(R.id.companyPhone);
        companyAlternatephone = (EditText) WelComeCompanyView.findViewById(R.id.companyAlternatephone);
        CIN = (EditText) WelComeCompanyView.findViewById(R.id.CIN);
        otherNature = (EditText) WelComeCompanyView.findViewById(R.id.otherNature);

        countryAutoBoxCompany = (AutoCompleteTextView) WelComeCompanyView.findViewById(R.id.countryAutoBoxCompany);

        countrycount = getResources().getStringArray(R.array.countries_array).length;
        countries = new String[countrycount];
        countries = getResources().getStringArray(R.array.countries_array);

        countrieslist.clear();
        for (int i = 0; i < countrycount; i++) {
            countrieslist.add(countries[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countrieslist);
        countryAutoBoxCompany.setAdapter(adapter);


        companyNature = (Spinner) WelComeCompanyView.findViewById(R.id.companyNature);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, Nature) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
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
                Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/abz.ttf");
                tv.setTypeface(custom_font3);

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.parseColor("#eeeeee"));
                }
                return view;
            }
        };

        companyNature.setAdapter(dataAdapter);

        companyNature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //selectedCountry = (String) parent.getItemAtPosition(position);

                // new HrCompanyDetails().GetStates().execute();
                pos = position;
                CompanyType = Nature[position];

                if (CompanyType.equals("other")) {
                    otherNature.setVisibility(View.VISIBLE);
                } else
                    otherNature.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        companyAlternatephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(companyAlternatephone.length()>0){
                    if(companyAlternatephone.length()<8 ){
                        errorFlagCompany=true;
                        companyAlternatephone.setError("Enter valid phone number");
                    }else
                        errorFlagCompany=false;
                }
                else
                errorFlagCompany=false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_genrate_code);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnNext = (Button) findViewById(R.id.btn_next);
        resultView = (ImageView) findViewById(R.id.result_image);
        nextProgress = (ProgressBar) findViewById(R.id.nextProgress);
        mainfragment = (FrameLayout) findViewById(R.id.mainfragment);

        MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"otp","no");
        MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"activatedCode","yes");
        ROLE = MySharedPreferencesManager.getRole(WelcomeGenrateCodeActivity.this);
        Log.d("TAG", "WelcomeGenrateCodeActivity shared role ---------  "+ROLE);

        digest1=MySharedPreferencesManager.getDigest1(WelcomeGenrateCodeActivity.this);
        digest2=MySharedPreferencesManager.getDigest2(WelcomeGenrateCodeActivity.this);

        try {
            android_id = Settings.Secure.getString(getApplication().getContentResolver(), Settings.Secure.ANDROID_ID);
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            device_id = telephonyManager.getDeviceId();
        }catch (Exception e){}

        Log.d("TAG", "onCreate: **************** aid : "+android_id);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                lastPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        if (ROLE != null && ROLE.equals("admin")) {

            myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
            myViewPagerAdapter.addFrag(new WelcomeInstituteDetailsFragment(), "InstituteDetails");
            myViewPagerAdapter.addFrag(new WelcomeShowGeneratedCodeFragment(), "genrateCode");
            viewPager.setAdapter(myViewPagerAdapter);
            viewPager.setCurrentItem(0);
            addBottomDots(0, 2);

        } else if (ROLE != null && ROLE.equals("hr")) {            // OR

            myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
            myViewPagerAdapter.addFrag(new WelcomeCompanyDetailsFragment(), "CompanyDetails");
            myViewPagerAdapter.addFrag(new WelcomeShowGeneratedCodeFragment(), "genrateCode");
            viewPager.setAdapter(myViewPagerAdapter);
            viewPager.setCurrentItem(0);
            addBottomDots(0, 2);

        } else {
            Toast.makeText(this, "Role is not admin or hr", Toast.LENGTH_LONG).show();
        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ROLE != null && ROLE.equals("admin")) {                             //  admin
                    Log.d("TAG", "onClick: curent pos " + currentPosition);

                    if (currentPosition == 0) {
                        sInstituteName = instituteName.getText().toString();
                        sInstituteAddress = instituteAddress.getText().toString();
                        Log.d("TAG", "onClick: sInstituteAddress " + sInstituteAddress);
                        sInstituteEmail = instituteEmail.getText().toString();
                        sInstitutewebsite = institutewebsite.getText().toString();
                        sInstitutephone = institutephone.getText().toString();
                        sInstituteAlternatephone = instituteAlternatephone.getText().toString();
                        sUniversity = university.getText().toString();
                        sRegNumber = regNumber.getText().toString();
                        COUNTRY = countryAutoBox.getText().toString();

                        if (sInstituteName.length() < 2) {
                            errorFlagInstitute = true;
                            instituteName.setError("Enter valid Institute name");
                        } else if (COUNTRY.length() < 1) {
                            errorFlagInstitute = true;
                            countryAutoBox.setError("select country");
                        } else if (sInstituteAddress.length() < 5) {
                            errorFlagInstitute = true;
                            instituteAddress.setError("Enter valid address");
                        } else if (!sInstituteEmail.contains("@")) {
                            errorFlagInstitute = true;
                            instituteEmail.setError("Invalid Email");
                        } else if (!sInstitutewebsite.contains(".")) {
                            errorFlagInstitute = true;
                            institutewebsite.setError("Enter valid Website url");
                        } else if (sInstitutephone.length() < 8) {
                            errorFlagInstitute = true;
                            institutephone.setError("Invalid phone");
                        } else if (sUniversity.length() < 2) {
                            errorFlagInstitute = true;
                            university.setError("Enter Valid University Name");
                        } else if (sRegNumber.length() < 2) {
                            errorFlagInstitute = true;
                            regNumber.setError("Invalid Registration Number");
                        } else if (COUNTRY.length() > 1) {
                            boolean flag = false;
                            for (String compareCountry : countrieslist) {
                                if (compareCountry.equals(COUNTRY)) {
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag == false) {
                                errorFlagInstitute = true;
                                countryAutoBox.setError("please select country from suggestions");
                            }
                        }
                        if (errorFlagInstitute == false) {
                            Toast.makeText(WelcomeGenrateCodeActivity.this, "valid", Toast.LENGTH_SHORT).show();
                            new SaveInstititeData().execute();

                        }
                    } else if (currentPosition == 1) {
                        startActivity(new Intent(WelcomeGenrateCodeActivity.this, AdminActivity.class));
                        finish();
                    }


                } else if (ROLE != null && ROLE.equals("hr")) {            // OR  Hr

                    if (currentPosition == 0) {
                        sCompanyName = companyName.getText().toString();
                        sCompanyAddress = companyAddress.getText().toString();
                        sCompanyEmail = companyEmail.getText().toString();
                        sCompanyWebsite = companyWebsite.getText().toString();
                        sCompanyPhone = companyPhone.getText().toString();
                        sCIN = CIN.getText().toString();
                        sCompanyAlternatephone = companyAlternatephone.getText().toString();
                        sOtherNature = otherNature.getText().toString();
                        nature = CompanyType;
                        Log.d("TAG", "onClick: --------- "+nature);
                        COUNTRY = countryAutoBoxCompany.getText().toString();

                        if (sCompanyName.length() < 2) {
                            errorFlagCompany = true;
                            companyName.setError("Enter valid Company name");
                        }else if (COUNTRY.length() < 1) {
                            errorFlagCompany = true;
                            countryAutoBoxCompany.setError("select country");
                        } else if (sCompanyAddress.length() < 2 ) {
                            errorFlagCompany = true;
                            companyAddress.setError("Enter valid address");
                        } else if (!sCompanyEmail.contains("@")) {
                            errorFlagCompany = true;
                            companyEmail.setError("Enter valid Email");
                        } else if (!sCompanyWebsite.contains(".")) {
                            errorFlagCompany = true;
                            companyWebsite.setError("Enter valid website url");
                        } else if (sCompanyPhone.length() < 8) {
                            errorFlagCompany = true;
                            companyPhone.setError("Invalid phone");
                        } else if (sCIN.length() < 3) {
                            errorFlagCompany = true;
                            CIN.setError("Invalid CIN");
                        } else  if (CompanyType== null) {
                            errorFlagCompany = true;
                        }else  if (CompanyType != null && CompanyType.equals("-Select Company Nature-")) {
                            errorFlagCompany = true;
                            Toast.makeText(WelcomeGenrateCodeActivity.this, "select company nature", Toast.LENGTH_SHORT).show();
                        } if (CompanyType != null && !CompanyType.equals("")) {
                            if (CompanyType.equals("other")) {
                                if (sOtherNature.length() < 2) {
                                    errorFlagCompany = true;
                                    otherNature.setError("Enter valid company nature");
                                } else
                                    nature = sOtherNature;
                            }
                        }if (COUNTRY.length() > 1) {
                            boolean flag = false;
                            for (String compareCountry : countrieslist) {
                                if (compareCountry.equals(COUNTRY)) {
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag == false) {
                                errorFlagCompany = true;
                                countryAutoBoxCompany.setError("please select country from suggestions");
                            }
                        }
                        if (errorFlagCompany == false) {
                            Toast.makeText(WelcomeGenrateCodeActivity.this, "valid", Toast.LENGTH_SHORT).show();
                            new SaveCompanyData().execute();

                        }

                    }else if (currentPosition == 1) {
                        startActivity(new Intent(WelcomeGenrateCodeActivity.this, HRActivity.class));
                        finish();
                    }

                }
            }
        });

    }


    private void addBottomDots(int currentPage, int totalPages) {
        dots = new TextView[totalPages];

        int colorsActive = getResources().getColor(R.color.array_dot_active);
        int colorsInactive = getResources().getColor(R.color.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }


    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            if (path == 1) {
                addBottomDots(position, 2);

                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == 2 - 1) {
                    // last page. make button text to GOT IT
                    btnNext.setText("LOGIN");

                } else {
                    // still pages are left
                    btnNext.setText("NEXT");

                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    public class MyViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public MyViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {


            return mFragmentTitleList.get(position);
        }

    }

    @Override
    public void onBackPressed() {
        // do nothing
    }


    class SaveInstititeData extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {


            try {

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";

                byte[] sInstituteNameBytes = sInstituteName.getBytes("UTF-8");
                byte[] sInstituteAddressBytes = sInstituteAddress.getBytes("UTF-8");
                byte[] sInstituteEmailBytes = sInstituteEmail.getBytes("UTF-8");
                byte[] sInstitutewebsiteBytes = sInstitutewebsite.getBytes("UTF-8");
                byte[] sInstitutephoneBytes = sInstitutephone.getBytes("UTF-8");
                byte[] sInstituteAlternatephoneBytes = sInstituteAlternatephone.getBytes("UTF-8");
                byte[] sUniversityBytes = sUniversity.getBytes("UTF-8");
                byte[] sRegisterNumberBytes = sRegNumber.getBytes("UTF-8");
                byte[] countryBytes = COUNTRY.getBytes("UTF-8");

                byte[] sInstituteNameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, sInstituteNameBytes);
                encInstituteName = new String(SimpleBase64Encoder.encode(sInstituteNameEncryptedBytes));

                byte[] sInstituteAddressEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, sInstituteAddressBytes);
                encInstituteAddress = new String(SimpleBase64Encoder.encode(sInstituteAddressEncryptedBytes));

                byte[] sInstituteEmailEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, sInstituteEmailBytes);
                encInstituteEmail = new String(SimpleBase64Encoder.encode(sInstituteEmailEncryptedBytes));

                byte[] sInstitutewebsiteEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, sInstitutewebsiteBytes);
                encInstituteInstitutewebsite = new String(SimpleBase64Encoder.encode(sInstitutewebsiteEncryptedBytes));

                byte[] sInstitutephoneEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, sInstitutephoneBytes);
                encInstitutephone = new String(SimpleBase64Encoder.encode(sInstitutephoneEncryptedBytes));

                byte[] sInstituteAlternatephoneEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, sInstituteAlternatephoneBytes);
                encInstituteAlternatephone = new String(SimpleBase64Encoder.encode(sInstituteAlternatephoneEncryptedBytes));

                byte[] sUniversityEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, sUniversityBytes);
                encUniversity = new String(SimpleBase64Encoder.encode(sUniversityEncryptedBytes));

                byte[] sRegisterNumberEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, sRegisterNumberBytes);
                encRegNumber = new String(SimpleBase64Encoder.encode(sRegisterNumberEncryptedBytes));

                byte[] countryEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, countryBytes);
                enccountry = new String(SimpleBase64Encoder.encode(countryEncryptedBytes));

                // previous data

                encUsername = MySharedPreferencesManager.getUsername(WelcomeGenrateCodeActivity.this);
                encPassword = MySharedPreferencesManager.getPassword(WelcomeGenrateCodeActivity.this);
                encFirstName = MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this, "fname");
                encLastName = MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this, "lname");
                encAdminPhone = MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this, "phone");
                encrole = MySharedPreferencesManager.getRole(WelcomeGenrateCodeActivity.this);
                encProfessionalEmail = MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this, "ProMail");

                Log.d("TAG", "encUsername:              " + encUsername);
                Log.d("TAG", "encFirstName:             " + encFirstName);
                Log.d("TAG", "encLastName:              " + encLastName);
                Log.d("TAG", "encPassword:              " + encPassword);
                Log.d("TAG", "encAdminPhone:            " + encAdminPhone);
                Log.d("TAG", "encProfessionalEmail:     " + encProfessionalEmail);
                Log.d("TAG", "encInstituteName:         " + encInstituteName);
                Log.d("TAG", "encInstituteAddress:      " + encInstituteAddress);
                Log.d("TAG", "encInstituteEmail:        " + encInstituteEmail);
                Log.d("TAG", "encInstweb:               " + encInstituteInstitutewebsite);
                Log.d("TAG", "encInstitutephone:        " + encInstitutephone);
                Log.d("TAG", "encInstAlterphone:        " + encInstituteAlternatephone);
                Log.d("TAG", "encUniversity:            " + encUniversity);
                Log.d("TAG", "encRegNumber:             " + encRegNumber);
                Log.d("TAG", "enccountry:               " + enccountry);
                Log.d("TAG", "COUNTRY:                  " + COUNTRY);

            } catch (Exception e) {
                Log.d("TAG", "SaveInstititeData doInBackground: exp " + e.getMessage());
            }

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("uname", encUsername));                   // 0
            params.add(new BasicNameValuePair("pass", encPassword));                   // 1
            params.add(new BasicNameValuePair("fnamw", encFirstName));                 // 2
            params.add(new BasicNameValuePair("lname", encLastName));                  // 3
            params.add(new BasicNameValuePair("mobile", encAdminPhone));                 // 4

            params.add(new BasicNameValuePair("instname", encInstituteName));                   //5
            params.add(new BasicNameValuePair("instEmail", encInstituteEmail));                 //6
            params.add(new BasicNameValuePair("instWeb", encInstituteInstitutewebsite));      //7
            params.add(new BasicNameValuePair("instPhone", encInstitutephone));                 //8
            params.add(new BasicNameValuePair("instAlterphone", encInstituteAlternatephone));       //9
            params.add(new BasicNameValuePair("university", encUniversity));                    //10
            params.add(new BasicNameValuePair("regnumber", encRegNumber));                     //11
            params.add(new BasicNameValuePair("address", encInstituteAddress));              //12
            params.add(new BasicNameValuePair("proffmail", encProfessionalEmail));             //13
            params.add(new BasicNameValuePair("country", enccountry));                       //14
            params.add(new BasicNameValuePair("id",android_id));                              //15
            //static

            json = jsonParser.makeHttpRequest(MyConstants.url_SaveAndGenrateInstituteCode, "GET", params);
            try {
                r = json.getString("info");
                Log.d("TAG", "doInBackground: save comp data "+json);
                if (r.equals("success")) {
                    CODE = json.getString("ucode");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("success")) {
                Toast.makeText(WelcomeGenrateCodeActivity.this, CODE, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "admin code ===============================   " + CODE);
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"nameKey",encUsername);
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"passKey",encPassword);
                viewPager.setCurrentItem(1);
                addBottomDots(1, 2);
                helloMsgcode.setText("Hello Admin!");
                genratedCode.setText(CODE);
                headerMsgcode.setText("This is your Institute Code provided by PlaceMe..!!");

                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"intro","yes");
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"activatedCode","no");

            }
        }
    }

    class SaveCompanyData extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {
            try {
                Log.d("TAG", "start comp data");

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";

                Log.d("TAG", "sCompanyName:             " + sCompanyName);
                Log.d("TAG", "sCompanyAddress:          " + sCompanyAddress);
                Log.d("TAG", "sCompanyEmail:            " + sCompanyEmail);
                Log.d("TAG", "sCompanyWebsite:          " + sCompanyWebsite);
                Log.d("TAG", "sCompanyPhone:            " + sCompanyPhone);
                Log.d("TAG", "sCompanyAlterphone:       " + sCompanyAlternatephone);
                Log.d("TAG", "sCIN:                     " + sCIN);
                Log.d("TAG", "nature:                   " + nature);
                Log.d("TAG", "COUNTRY:                  " + COUNTRY);

                byte[] CompanyNameBytes = sCompanyName.getBytes("UTF-8");
                byte[] CompanyAddressBytes = sCompanyAddress.getBytes("UTF-8");
                byte[] CompanyEmailBytes = sCompanyEmail.getBytes("UTF-8");
                byte[] CompanyWebsiteBytes = sCompanyWebsite.getBytes("UTF-8");
                byte[] CompanyPhoneBytes = sCompanyPhone.getBytes("UTF-8");
                byte[] CompanyAlternatePhoneBytes = sCompanyAlternatephone.getBytes("UTF-8");
                byte[] encCINBytes = sCIN.getBytes("UTF-8");
                byte[] NatureBytes = nature.getBytes("UTF-8");
                byte[] countryBytes = COUNTRY.getBytes("UTF-8");

                byte[] sCompanyNameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, CompanyNameBytes);
                encCompanyName = new String(SimpleBase64Encoder.encode(sCompanyNameEncryptedBytes));

                byte[] sCompanyAddressEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, CompanyAddressBytes);
                encCompanyAddress = new String(SimpleBase64Encoder.encode(sCompanyAddressEncryptedBytes));

                byte[] sCompanyEmailEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, CompanyEmailBytes);
                encCompanyEmail = new String(SimpleBase64Encoder.encode(sCompanyEmailEncryptedBytes));

                byte[] sCompanyWebsiteEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, CompanyWebsiteBytes);
                encCompanyWebsite = new String(SimpleBase64Encoder.encode(sCompanyWebsiteEncryptedBytes));

                byte[] sCompanyPhoneEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, CompanyPhoneBytes);
                encCompanyPhone = new String(SimpleBase64Encoder.encode(sCompanyPhoneEncryptedBytes));

                byte[] sCompanyAlternatephoneEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, CompanyAlternatePhoneBytes);
                encCompanyAlternatephone = new String(SimpleBase64Encoder.encode(sCompanyAlternatephoneEncryptedBytes));

                byte[] sCINeEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, encCINBytes);
                encCIN = new String(SimpleBase64Encoder.encode(sCINeEncryptedBytes));

                byte[] sotherNatureEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, NatureBytes);
                encOtherNature = new String(SimpleBase64Encoder.encode(sotherNatureEncryptedBytes));

                byte[] countryEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, countryBytes);
                enccountry = new String(SimpleBase64Encoder.encode(countryEncryptedBytes));

                encUsername = MySharedPreferencesManager.getUsername(WelcomeGenrateCodeActivity.this);
                encPassword = MySharedPreferencesManager.getPassword(WelcomeGenrateCodeActivity.this);
                encFirstName = MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this, "fname");
                encLastName = MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this, "lname");
                encAdminPhone = MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this, "phone");
                encProfessionalEmail = MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this, "ProMail");

                Log.d("TAG", "shared encUsername:          " + encUsername);
                Log.d("TAG", "shared encFirstName:         " + encPassword);
                Log.d("TAG", "shared encLastName:          " + encFirstName);
                Log.d("TAG", "shared encPassword:          " + encLastName);
                Log.d("TAG", "shared encAdminPhone:        " + encAdminPhone);
                Log.d("TAG", "shared encrole:              " + encProfessionalEmail);


            } catch (Exception e) {
                Log.d("TAG", "doInBackground: exp " + e.getMessage());
            }


            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("u", encUsername));                       // 0
            params.add(new BasicNameValuePair("pass", encPassword));                  //  1
            params.add(new BasicNameValuePair("fname", encFirstName));                //  2
            params.add(new BasicNameValuePair("lname", encLastName));                 //  3
            params.add(new BasicNameValuePair("phone", encAdminPhone));               //   4

            params.add(new BasicNameValuePair("compnmae", encCompanyName));           //   5
            params.add(new BasicNameValuePair("adder", encCompanyAddress));          //    6
            params.add(new BasicNameValuePair("e", encCompanyEmail));               //     7
            params.add(new BasicNameValuePair("web", encCompanyWebsite));            //    8
            params.add(new BasicNameValuePair("cp", encCompanyPhone));              //     9
            params.add(new BasicNameValuePair("cap", encCompanyAlternatephone));     //    10
            params.add(new BasicNameValuePair("cin", encCIN));                     //      11
            params.add(new BasicNameValuePair("nature", encOtherNature));             //   12
            params.add(new BasicNameValuePair("proEmail", encProfessionalEmail));             //13
            params.add(new BasicNameValuePair("country", enccountry));                       //14
            params.add(new BasicNameValuePair("id",android_id));                              //15



            json = jsonParser.makeHttpRequest(MyConstants.url_SaveAndGenrateCompanyCode, "GET", params);
            try {
                r = json.getString("info");
                if(r.equals("success")){
                    CODE=json.getString("ucode");
                }
                Log.d("TAG", "doInBackground: "+json);
            } catch (Exception e) {
                Log.d("TAG", "doInBackground: comp asynk"+e.getMessage());
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result!=null && result.equals("success")) {
                Toast.makeText(WelcomeGenrateCodeActivity.this, CODE, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "hr comp code ===============================   " + CODE);
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"nameKey",encUsername);
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"passKey",encPassword);
                viewPager.setCurrentItem(1);
                addBottomDots(1, 2);
                helloMsgcode.setText("Hello Hr!");
                genratedCode.setText(CODE);
                headerMsgcode.setText("This is your Company Code provided by PlaceMe..!!");
                // back press next will move to base activity
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"intro","yes");
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"activatedCode","no");

            }
        }
    }


}
