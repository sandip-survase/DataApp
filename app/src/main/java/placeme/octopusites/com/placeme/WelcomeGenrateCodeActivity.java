package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class WelcomeGenrateCodeActivity extends AppCompatActivity {


    String CODE, COUNTRY;
    private CustomViewPager viewPager;
    private MyAdapter myViewPagerAdapter;
    private Button btnNext;
    ProgressBar nextProgress;
    private TextView[] dots;
    int currentPosition = 0, lastPosition = 0;
    private LinearLayout dotsLayout;
    String ROLE;
    public int pos;
    Boolean errorFlagInstitute = false, errorFlagCompany = false;
    String digest1, digest2;
    String CompanyType = "",resultofop;
    private int path;
    JSONParser jsonParser = new JSONParser();
    JSONObject json;
    View WelComeCompanyView, WelComeInstituteView, WelComeShowCodeView;
    String encUsername, encFirstName, encLastName, encPassword, encAdminPhone, encrole, encProfessionalEmail, enccountry;
    TextInputEditText instituteName, instituteAddress, instituteEmail, institutewebsite, institutephone, instituteAlternatephone, university, regNumber;
    TextInputLayout instituteNameTextInputLayout,countryinputlayout,addressTextInputLayout,instituteEmailTextInputLayout,websiteTextInputLayout,phoneTextInputLayout,alternatePhoneTextInputLayout,univercityTextInputLayout,regNumTextInputLayout;
    String sInstituteName, sInstituteAddress, sInstituteEmail, sInstitutewebsite, sInstitutephone, sInstituteAlternatephone = "", sUniversity, sRegNumber;
    String encInstituteName, encInstituteAddress, encInstituteEmail, encInstituteInstitutewebsite, encInstitutephone, encInstituteAlternatephone = "", encUniversity, encRegNumber;
    TextInputEditText companyName, companyAddress, companyEmail, companyWebsite, companyPhone, companyAlternatephone, CIN, otherNature;
    String sCompanyName, sCompanyAddress, sCompanyEmail, sCompanyWebsite, sCompanyPhone, sCompanyAlternatephone = "", sCIN, nature, sOtherNature = "";
    String encCompanyName, encCompanyAddress, encCompanyEmail, encCompanyWebsite, encCompanyPhone, encCompanyAlternatephone = "", encCIN, encOtherNature;
    String[] Nature = {"-Select Company Nature-", "Partnership", "Proprietory", "LLP (Limited Liability)", "Private Limited", "Public Limited", "Inc", "Other"};
    Spinner companyNature;
    ArrayAdapter<String> dataAdapter;
    List<String> countrieslist = new ArrayList<String>();
    AutoCompleteTextView countryAutoBox,countryAutoBoxCompany;
    String countries[];
    int countrycount;
    private static  String android_id,device_id;
    TextView helloMsgcode, genratedCode,headerMsgcode;
    ImageView ucodeicon;
    TextInputLayout companyNameTextInputLayout,companycountryinputlayout,companyaddressTextInputLayout,companyemailTextInputLayout,companywebsiteTextInputLayout,companyphoneTextInputLayout,companyalternatePhoneTextInputLayout,companyCINTextInputLayout,ocompanytherNatureTextInputLayout;

    public void setWelComeShowCodeView(View v) {
        WelComeShowCodeView = v;
        helloMsgcode = (TextView) WelComeShowCodeView.findViewById(R.id.helloMsgcode);
        genratedCode = (TextView) WelComeShowCodeView.findViewById(R.id.genratedCode);
        headerMsgcode = (TextView) WelComeShowCodeView.findViewById(R.id.headerMsgcode);

        ucodeicon=(ImageView) WelComeShowCodeView.findViewById(R.id.ucodeicon);

        helloMsgcode.setTypeface(Z.getBold(this));
        headerMsgcode.setTypeface(Z.getBold(this));
        genratedCode.setTypeface(Z.getRighteous(this));




    }

    public void setWelComeInstituteView(View v) {
        WelComeInstituteView = v;
        instituteName = (TextInputEditText) WelComeInstituteView.findViewById(R.id.instituteName);
        instituteAddress = (TextInputEditText) WelComeInstituteView.findViewById(R.id.instituteAddress);
        instituteEmail = (TextInputEditText) WelComeInstituteView.findViewById(R.id.instituteEmail);
        institutewebsite = (TextInputEditText) WelComeInstituteView.findViewById(R.id.institutewebsite);
        institutephone = (TextInputEditText) WelComeInstituteView.findViewById(R.id.institutephone);
        instituteAlternatephone = (TextInputEditText) WelComeInstituteView.findViewById(R.id.instituteAlternatephone);
        university = (TextInputEditText) WelComeInstituteView.findViewById(R.id.university);
        regNumber = (TextInputEditText) WelComeInstituteView.findViewById(R.id.regNumber);
        countryAutoBox = (AutoCompleteTextView) WelComeInstituteView.findViewById(R.id.countryAutoBox);

        instituteName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                instituteNameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        instituteAddress.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addressTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        instituteEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                instituteEmailTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        institutewebsite.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                websiteTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        institutephone.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        instituteAlternatephone.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alternatePhoneTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        university.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                univercityTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        regNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                regNumTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        countrycount = getResources().getStringArray(R.array.countries_array).length;
        countries = new String[countrycount];
        countries = getResources().getStringArray(R.array.countries_array);

        countrieslist.clear();
        for (int i = 0; i < countrycount; i++) {
            countrieslist.add(countries[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countrieslist);
        countryAutoBox.setAdapter(adapter);


        TextView welcometextviewcontext1=(TextView)WelComeInstituteView.findViewById(R.id.welcometextviewcontext1);
        TextView welcometextviewcontext2=(TextView)WelComeInstituteView.findViewById(R.id.welcometextviewcontext2);

        welcometextviewcontext2.setText("\"Education is the most powerful weapon which you can use to change the world.\" - Nelson Mandela");
        welcometextviewcontext1.setTypeface(Z.getBold(this));
        welcometextviewcontext2.setTypeface(Z.getBoldItalic(this));

        instituteNameTextInputLayout=(TextInputLayout)WelComeInstituteView.findViewById(R.id.instituteNameTextInputLayout);
        countryinputlayout=(TextInputLayout)WelComeInstituteView.findViewById(R.id.countryinputlayout);
        addressTextInputLayout=(TextInputLayout)WelComeInstituteView.findViewById(R.id.addressTextInputLayout);
        instituteEmailTextInputLayout=(TextInputLayout)WelComeInstituteView.findViewById(R.id.instituteEmailTextInputLayout);
        websiteTextInputLayout=(TextInputLayout)WelComeInstituteView.findViewById(R.id.websiteTextInputLayout);
        phoneTextInputLayout=(TextInputLayout)WelComeInstituteView.findViewById(R.id.phoneTextInputLayout);
        alternatePhoneTextInputLayout=(TextInputLayout)WelComeInstituteView.findViewById(R.id.alternatePhoneTextInputLayout);
        univercityTextInputLayout=(TextInputLayout)WelComeInstituteView.findViewById(R.id.univercityTextInputLayout);
        regNumTextInputLayout=(TextInputLayout)WelComeInstituteView.findViewById(R.id.regNumTextInputLayout);

        instituteName.setTypeface(Z.getBold(this));
        countryAutoBox.setTypeface(Z.getBold(this));
        instituteAddress.setTypeface(Z.getBold(this));
        instituteEmail.setTypeface(Z.getBold(this));
        institutewebsite.setTypeface(Z.getBold(this));
        institutephone.setTypeface(Z.getBold(this));
        instituteAlternatephone.setTypeface(Z.getBold(this));
        university.setTypeface(Z.getBold(this));
        regNumber.setTypeface(Z.getBold(this));
        instituteNameTextInputLayout.setTypeface(Z.getLight(this));
        countryinputlayout.setTypeface(Z.getLight(this));
        addressTextInputLayout.setTypeface(Z.getLight(this));
        instituteEmailTextInputLayout.setTypeface(Z.getLight(this));
        websiteTextInputLayout.setTypeface(Z.getLight(this));
        phoneTextInputLayout.setTypeface(Z.getLight(this));
        alternatePhoneTextInputLayout.setTypeface(Z.getLight(this));
        univercityTextInputLayout.setTypeface(Z.getLight(this));
        regNumTextInputLayout.setTypeface(Z.getLight(this));

        ImageView enterinstdetailsimage=(ImageView)WelComeInstituteView.findViewById(R.id.enterinstdetailsimage);
        slideinleft1(welcometextviewcontext1);
        slideinleft2(welcometextviewcontext2);
        fade(enterinstdetailsimage);
        fadeandmove(instituteNameTextInputLayout);
        fadeandmove(countryinputlayout);
        fadeandmove(addressTextInputLayout);
        fadeandmove(instituteEmailTextInputLayout);
        fadeandmove(websiteTextInputLayout);
        fadeandmove(phoneTextInputLayout);
        fadeandmove(alternatePhoneTextInputLayout);
        fadeandmove(univercityTextInputLayout);
        fadeandmove(regNumTextInputLayout);



    }


    public void setWelComeCompanyView(View v) {     // --------------   WelComeCompanyView

        WelComeCompanyView = v;
        companyName = (TextInputEditText) WelComeCompanyView.findViewById(R.id.companyName);
        companyAddress = (TextInputEditText) WelComeCompanyView.findViewById(R.id.companyAddress);
        companyEmail = (TextInputEditText) WelComeCompanyView.findViewById(R.id.companyEmail);
        companyWebsite = (TextInputEditText) WelComeCompanyView.findViewById(R.id.companyWebsite);
        companyPhone = (TextInputEditText) WelComeCompanyView.findViewById(R.id.companyPhone);
        companyAlternatephone = (TextInputEditText) WelComeCompanyView.findViewById(R.id.companyAlternatephone);
        CIN = (TextInputEditText) WelComeCompanyView.findViewById(R.id.CIN);
        otherNature = (TextInputEditText) WelComeCompanyView.findViewById(R.id.otherNature);

        companyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyNameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        companyAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyaddressTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        companyEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyemailTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        companyWebsite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companywebsiteTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        companyPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyphoneTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        companyAlternatephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyalternatePhoneTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CIN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyCINTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otherNature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ocompanytherNatureTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                ((TextView) v).setTypeface(Z.getBold(WelcomeGenrateCodeActivity.this));

                return v;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
//                    Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/abz.ttf");
                tv.setTypeface(Z.getBold(WelcomeGenrateCodeActivity.this));

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.parseColor("#00bcd4"));
                } else {
                    tv.setTextColor(Color.parseColor("#03353e"));
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

                if (CompanyType.equals("Other")) {
                    ocompanytherNatureTextInputLayout.setVisibility(View.VISIBLE);
                } else
                    ocompanytherNatureTextInputLayout.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        TextView welcometextviewcontext1=(TextView)WelComeCompanyView.findViewById(R.id.welcometextviewcontext1);
        TextView welcometextviewcontext2=(TextView)WelComeCompanyView.findViewById(R.id.welcometextviewcontext2);

        welcometextviewcontext2.setText("\"The mechanics of industry is easy. The real engine is the peeople: Their motivation and direction.\" - Ken Gilbert");
        welcometextviewcontext1.setTypeface(Z.getBold(this));
        welcometextviewcontext2.setTypeface(Z.getBoldItalic(this));

        companyNameTextInputLayout=(TextInputLayout)WelComeCompanyView.findViewById(R.id.companyNameTextInputLayout);
        companycountryinputlayout=(TextInputLayout)WelComeCompanyView.findViewById(R.id.countryinputlayout);
        companyaddressTextInputLayout=(TextInputLayout)WelComeCompanyView.findViewById(R.id.addressTextInputLayout);
        companyemailTextInputLayout=(TextInputLayout)WelComeCompanyView.findViewById(R.id.emailTextInputLayout);
        companywebsiteTextInputLayout=(TextInputLayout)WelComeCompanyView.findViewById(R.id.websiteTextInputLayout);
        companyphoneTextInputLayout=(TextInputLayout)WelComeCompanyView.findViewById(R.id.phoneTextInputLayout);
        companyalternatePhoneTextInputLayout=(TextInputLayout)WelComeCompanyView.findViewById(R.id.alternatePhoneTextInputLayout);
        companyCINTextInputLayout=(TextInputLayout)WelComeCompanyView.findViewById(R.id.CINTextInputLayout);
        ocompanytherNatureTextInputLayout=(TextInputLayout)WelComeCompanyView.findViewById(R.id.otherNatureTextInputLayout);

        companyName.setTypeface(Z.getBold(this));
        countryAutoBoxCompany.setTypeface(Z.getBold(this));
        companyAddress.setTypeface(Z.getBold(this));
        companyEmail.setTypeface(Z.getBold(this));
        companyWebsite.setTypeface(Z.getBold(this));
        companyPhone.setTypeface(Z.getBold(this));
        companyAlternatephone.setTypeface(Z.getBold(this));
        CIN.setTypeface(Z.getBold(this));
        otherNature.setTypeface(Z.getBold(this));
        companyNameTextInputLayout.setTypeface(Z.getLight(this));
        companycountryinputlayout.setTypeface(Z.getLight(this));
        companyaddressTextInputLayout.setTypeface(Z.getLight(this));
        companyemailTextInputLayout.setTypeface(Z.getLight(this));
        companywebsiteTextInputLayout.setTypeface(Z.getLight(this));
        companyphoneTextInputLayout.setTypeface(Z.getLight(this));
        companyalternatePhoneTextInputLayout.setTypeface(Z.getLight(this));
        companyCINTextInputLayout.setTypeface(Z.getLight(this));
        ocompanytherNatureTextInputLayout.setTypeface(Z.getLight(this));

        ImageView enterindustrydetailsscreen=(ImageView)WelComeCompanyView.findViewById(R.id.enterindustrydetailsscreen);
        slideinleft1(welcometextviewcontext1);
        slideinleft2(welcometextviewcontext2);
        fade(enterindustrydetailsscreen);
        fadeandmove(companyNameTextInputLayout);
        fadeandmove(companycountryinputlayout);
        fadeandmove(companyaddressTextInputLayout);
        fadeandmove(companyemailTextInputLayout);
        fadeandmove(companywebsiteTextInputLayout);
        fadeandmove(companyphoneTextInputLayout);
        fadeandmove(companyalternatePhoneTextInputLayout);
        fadeandmove(companyCINTextInputLayout);
        fadeandmove(ocompanytherNatureTextInputLayout);

    }
    public void fade(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadein);
        view.startAnimation(animation1);
    }
    public void fadeandmove(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeinmove);
        view.startAnimation(animation1);
    }  //test
    public void slideinleft1(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slideinleft1);
        view.startAnimation(animation1);
    }
    public void slideinleft2(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slideinleft2);
        view.startAnimation(animation1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_genrate_code);
        Log.d("TAG", "onCreate:WelcomeGenrateCodeActivity called");

        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPager.setPagingEnabled(false);

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setTypeface(Z.getBold(this));
        nextProgress = (ProgressBar) findViewById(R.id.nextProgress);


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
                if(currentPosition==1)
                {
                    slideinleft1(helloMsgcode);
                    slideinleft2(headerMsgcode);
                    fade(genratedCode);
                    fade(ucodeicon);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        if (ROLE != null && ROLE.equals("admin")) {

            myViewPagerAdapter = new MyAdapter(getSupportFragmentManager());
            myViewPagerAdapter.addFrag(new WelcomeInstituteDetailsFragment());
            myViewPagerAdapter.addFrag(new WelcomeShowGeneratedCodeFragment());
            viewPager.setAdapter(myViewPagerAdapter);
            viewPager.setCurrentItem(0);
            addBottomDots(0, 2);

        } else if (ROLE != null && ROLE.equals("hr")) {            // OR

            myViewPagerAdapter = new MyAdapter(getSupportFragmentManager());
            myViewPagerAdapter.addFrag(new WelcomeCompanyDetailsFragment());
            myViewPagerAdapter.addFrag(new WelcomeShowGeneratedCodeFragment());
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
                        errorFlagInstitute = false;
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
                            instituteNameTextInputLayout.setError("Enter valid Institute name");
                        } else if (COUNTRY.length() < 1) {
                            errorFlagInstitute = true;
                            countryinputlayout.setError("select country");
                        } else if (sInstituteAddress.length() < 5) {
                            errorFlagInstitute = true;
                            addressTextInputLayout.setError("Enter valid address");
                        } else if (!sInstituteEmail.contains("@")) {
                            errorFlagInstitute = true;
                            instituteEmailTextInputLayout.setError("Invalid Email");
                        } else if (!sInstitutewebsite.contains(".")) {
                            errorFlagInstitute = true;
                            websiteTextInputLayout.setError("Enter valid Website url");
                        } else if (sInstitutephone.length() < 8) {
                            errorFlagInstitute = true;
                            phoneTextInputLayout.setError("Invalid phone number");
                        }else if(instituteAlternatephone.length()>0 && instituteAlternatephone.length()<8){
                            errorFlagInstitute = true;
                            alternatePhoneTextInputLayout.setError("Invalid phone number");
                        } else if (sUniversity.length() < 2) {
                            errorFlagInstitute = true;
                            univercityTextInputLayout.setError("Enter Valid University Name");
                        } else if (sRegNumber.length() < 2) {
                            errorFlagInstitute = true;
                            regNumTextInputLayout.setError("Invalid Registration Number");
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
                                countryinputlayout.setError("please select country from suggestions");
                            }
                        }
                        if (errorFlagInstitute == false) {
//                            Toast.makeText(WelcomeGenrateCodeActivity.this, "valid", Toast.LENGTH_SHORT).show();
                            new SaveInstititeData().execute();

                        }
                    } else if (currentPosition == 1) {
                        startActivity(new Intent(WelcomeGenrateCodeActivity.this, AdminActivity.class));
                        finish();
                    }


                } else if (ROLE != null && ROLE.equals("hr")) {            // OR  Hr

                    if (currentPosition == 0) {
                        errorFlagCompany=false;
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
                            companyNameTextInputLayout.setError("Kindly provide valid company name");
                        }else if (COUNTRY.length() < 1) {
                            errorFlagCompany = true;
                            companycountryinputlayout.setError("Kindly provide country name");
                        } else if (sCompanyAddress.length() < 2 ) {
                            errorFlagCompany = true;
                            companyaddressTextInputLayout.setError("Kindly provide valid address");
                        } else if (!sCompanyEmail.contains("@")) {
                            errorFlagCompany = true;
                            companyemailTextInputLayout.setError("Kindly provide valid email");
                        } else if (!sCompanyWebsite.contains(".")) {
                            errorFlagCompany = true;
                            companywebsiteTextInputLayout.setError("Kindly provide valid website");
                        } else if (sCompanyPhone.length() < 8) {
                            errorFlagCompany = true;
                            companyphoneTextInputLayout.setError("Kindly provide correct phone number");
                        }else if(companyAlternatephone.length()>0 && companyAlternatephone.length()<8){
                            errorFlagCompany = true;
                            companyalternatePhoneTextInputLayout.setError("Kindly provide correct phone number");
                        } else if (sCIN.length() < 3) {
                            errorFlagCompany = true;
                            companyCINTextInputLayout.setError("KIndly provide valid CIN (Company Identification Number)");
                        } else  if (CompanyType== null) {
                            errorFlagCompany = true;
                        }else  if (CompanyType != null && CompanyType.equals("-Select Company Nature-")) {
                            errorFlagCompany = true;
                            Toast.makeText(WelcomeGenrateCodeActivity.this, "select company nature", Toast.LENGTH_SHORT).show();
                        } if (CompanyType != null && !CompanyType.equals("")) {
                            if (CompanyType.equals("Other")) {
                                if (sOtherNature.length() < 2) {
                                    errorFlagCompany = true;
                                    ocompanytherNatureTextInputLayout.setError("Kindly provide valid company nature");
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
                                companycountryinputlayout.setError("Please select country from suggestions");
                            }
                        }
                        if (errorFlagCompany == false) {
//                            Toast.makeText(WelcomeGenrateCodeActivity.this, "valid", Toast.LENGTH_SHORT).show();
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

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,5);


        int colorsActive = getResources().getColor(R.color.array_dot_active);
        int colorsInactive = getResources().getColor(R.color.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setTypeface(Z.getFA(this));
            dots[i].setLayoutParams(params);
            dots[i].setText(getString(R.string.dot_unselected));
            dots[i].setTextSize(8);
            dots[i].setTextColor(colorsInactive);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[currentPage].setTextColor(colorsActive);
            dots[currentPage].setText(getString(R.string.dot_selected));
            dots[currentPage].setTextSize(10);
        }
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
    @Override
    public void onBackPressed() {
        // do nothing
    }
    public class MyAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();

        MyAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void clear() {
            mFragmentList.clear();
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = mFragmentList.get(position);
            return fragment;
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        void deletePage(int position) {
            if (canDelete()) {

                mFragmentList.remove(position);
                notifyDataSetChanged();
            }
        }

        boolean canDelete() {
            return mFragmentList.size() > 0;
        }

        // This is called when notifyDataSetChanged() is called
        @Override
        public int getItemPosition(Object object) {
            // refresh all fragments when data set changed
            return PagerAdapter.POSITION_NONE;
        }
    }
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
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
                encProfessionalEmail = MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this, "proEmail");

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

            json = jsonParser.makeHttpRequest(Z.url_SaveAndGenrateInstituteCode, "GET", params);
            try {
                r = json.getString("info");
                Log.d("TAG", "SaveInstititeData json : "+json);
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
//                Toast.makeText(WelcomeGenrateCodeActivity.this, CODE, Toast.LENGTH_SHORT).show();

                new CreateFirebaseUser(encUsername,encPassword).execute();

                Log.d("TAG", "admin code ===============================   " + CODE);
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"nameKey",encUsername);
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"passKey",encPassword);
                viewPager.setCurrentItem(1);
                addBottomDots(1, 2);
                try {
                    helloMsgcode.setText("Hello "+Decrypt(MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this,"fname"),digest1,digest2)+", your account has been successfully created under Training and Placement Officer / Coordinator.");
                    genratedCode.setText(CODE);
                    headerMsgcode.setText("This is your Institute Code provided by PlaceMe..!!");
                }
                catch (Exception e){
                    Log.d("TAG", "onPostExecute: exp in setting msg code : "+e.getMessage());
                }
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
                encProfessionalEmail = MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this, "proEmail");

                Log.d("TAG", "shared encUsername:          " + encUsername);
                Log.d("TAG", "shared encFirstName:         " + encPassword);
                Log.d("TAG", "shared encLastName:          " + encFirstName);
                Log.d("TAG", "shared encPassword:          " + encLastName);
                Log.d("TAG", "shared encAdminPhone:        " + encAdminPhone);
                Log.d("TAG", "shared proEmail:              " + encProfessionalEmail);
                Log.d("TAG", "shared role              " + ROLE);


            } catch (Exception e) {
                Log.d("TAG", "doInBackground: exp " + e.getMessage());
            }


            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("u", encUsername));                      // 0
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
            params.add(new BasicNameValuePair("cin", encCIN));                      //      11
            params.add(new BasicNameValuePair("nature", encOtherNature));             //   12
            params.add(new BasicNameValuePair("proEmail", encProfessionalEmail));             //13
            params.add(new BasicNameValuePair("country", enccountry));                       //14
            params.add(new BasicNameValuePair("id",android_id));                              //15



            json = jsonParser.makeHttpRequest(Z.url_SaveAndGenrateCompanyCode, "GET", params);
            Log.d("TAG", "SaveCompanyData json : "+json);
            try {
                r = json.getString("info");
                if(r.equals("success")){
                    CODE=json.getString("ucode");
                }
            } catch (Exception e) {
                Log.d("TAG", "doInBackground: comp asynk"+e.getMessage());
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result!=null && result.equals("success")) {

                new CreateFirebaseUser(encUsername,encPassword).execute();

//                Toast.makeText(WelcomeGenrateCodeActivity.this, CODE, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "hr comp code ===============================   " + CODE);
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"nameKey",encUsername);
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"passKey",encPassword);
                viewPager.setCurrentItem(1);
                addBottomDots(1, 2);
                try {
                    helloMsgcode.setText("Hello "+Decrypt(MySharedPreferencesManager.getData(WelcomeGenrateCodeActivity.this,"fname"),digest1,digest2)+", your account has been successfully created under HR Manager / Recruiter.");
                    genratedCode.setText(CODE);
                    headerMsgcode.setText("This is your Company Code provided by PlaceMe..!!");
                }catch (Exception e){}

                // back press next will move to base activity
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"intro","yes");
                MySharedPreferencesManager.save(WelcomeGenrateCodeActivity.this,"activatedCode","no");

            }
        }
    }

    class CreateFirebaseUser extends AsyncTask<String, String, String> {

        String u, p;

        CreateFirebaseUser(String u, String p) {
            this.u = u;
            this.p = p;
            Log.d("TAG", "CreateFirebaseUser input : "+u+"   "+p);
        }

        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", u));
            params.add(new BasicNameValuePair("p", p));
            params.add(new BasicNameValuePair("t", new SharedPrefUtil(getApplicationContext()).getString("firebaseToken")));
            json = jsonParser.makeHttpRequest(Z.url_create_firebase, "GET", params);
            Log.d("TAG", "CreateFirebaseUser json : "+json);
            try {
                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d("TAG", "CreateFirebaseUser onPostExecute: "+resultofop);

            String plainusername = null;
            String plainPassword = null;

            try {
                plainusername = AES4all.Decrypt(encUsername,digest1,digest2);
                plainPassword = AES4all.Decrypt(encPassword,digest1,digest2);

            } catch (Exception e) {
                e.printStackTrace();
            }
            String hash=md5(plainPassword + MySharedPreferencesManager.getDigest3(WelcomeGenrateCodeActivity.this));

            loginFirebase(plainusername, hash);
            Toast.makeText(WelcomeGenrateCodeActivity.this, "fire "+resultofop, Toast.LENGTH_LONG).show();
        }
    }



    void loginFirebase(String username, String hash) {

        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username, hash)
                .addOnCompleteListener(WelcomeGenrateCodeActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            Toast.makeText(WelcomeGenrateCodeActivity.this, "Successfully logged in to Firebase", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(WelcomeGenrateCodeActivity.this, "Failed to login to Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public static String md5(String input) {

        String md5 = null;

        if (null == input) return null;

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }


}
