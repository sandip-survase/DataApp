package placeme.octopusites.com.placeme;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
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
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.signature.ObjectKey;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.soundcloud.android.crop.Crop;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.OTPActivity.md5;

public class Welcome extends AppCompatActivity implements ImagePickerCallback {


    public static final String Intro = "intro";
    String encUsersName, plainUsername, usertype, isactivated, passwordstr, encPassword, encfname, enclname, encmobile, encinstOrEmail, encrole, encProMail;
    String resultofop = "";
    String SELECTED_ROLE;
    private String EmailCred = "";
    String digest1, digest2, status;
    String fname, lname, mobile;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    int currentPosition = 0, lastPosition = 0;
    int path = 0;
    int crop_flag = 0;
    String filepath = "", filename = "";
    private String finalPath;
    String directory;
    private ViewPager viewPager;
    private MyAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private Button btnNext,btnPrev;
    ProgressBar nextProgress;
    Boolean errorFlagIntro = false, genrateCodeFlag = false;
    View WelcomeEmailView, WelcomePasswordView, WelComeIntroView, WelcomeRoleView, WelcomeCreatePasswordView, WelComeIntroThroughAdminView;
    EditText passwordedittext;
    TextInputEditText usernameedittext;
    TextInputLayout usernameTextInputLayout;
    ImageView enteremailimage;
    EditText fnameEditText, lnameEditText, mobileEditText, instOrEmail;
    CircleImageView profilePicture;
    View studentBlock, alumniBlock, adminBlock, hrBlock;
    EditText enterPassword, confirmPassword;
    private ImageView resultView;
    ImagePicker imagePicker;
    FrameLayout crop_layout;
    List<String> response;
    boolean throughAdminFlag = false, errorFlagThroughAdminIntro = false;
    private String instOrEmailstr;
    private String confrimpass;
    TextView adminInfo;
    boolean instoremailerror = false;
    private static String android_id, device_id;
    String adminInstitute, adminfname, adminlname;
    Typeface fa,bold,light;
    public void setWelComeEmailView(View v) {
        WelcomeEmailView = v;
        usernameedittext = (TextInputEditText) WelcomeEmailView.findViewById(R.id.welcomeusername);
        usernameTextInputLayout = (TextInputLayout) WelcomeEmailView.findViewById(R.id.usernameTextInputLayout);
        TextView welcometextviewcontext1=(TextView)WelcomeEmailView.findViewById(R.id.welcometextviewcontext1);
        TextView welcometextviewcontext2=(TextView)WelcomeEmailView.findViewById(R.id.welcometextviewcontext2);
        enteremailimage=(ImageView) WelcomeEmailView.findViewById(R.id.enteremailimage);
        usernameTextInputLayout.setTypeface(light);
        usernameedittext.setTypeface(bold);
        welcometextviewcontext1.setTypeface(bold);
        welcometextviewcontext2.setTypeface(light);
        fade(enteremailimage);
        fadeandmove(usernameTextInputLayout);
        slideinleft1(welcometextviewcontext1);
        slideinleft2(welcometextviewcontext2);
        usernameedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usernameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    public void fade(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade);
        view.startAnimation(animation1);
    }
    public void fadeandmove(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeinmove);
        view.startAnimation(animation1);
    }
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

    public void setWelComePasswordView(View v) {
        WelcomePasswordView = v;
        passwordedittext = (EditText) WelcomePasswordView.findViewById(R.id.welcomepassword);
    }

    public void setWelComeCreatePasswordView(View v) {

        WelcomeCreatePasswordView = v;
        enterPassword = (EditText) WelcomeCreatePasswordView.findViewById(R.id.enterPassword);
        confirmPassword = (EditText) WelcomeCreatePasswordView.findViewById(R.id.confirmPassword);


    }

    public void setWelComeIntroView(View v) {
        WelComeIntroView = v;

        fnameEditText = (EditText) WelComeIntroView.findViewById(R.id.fname);
        lnameEditText = (EditText) WelComeIntroView.findViewById(R.id.lname);
        mobileEditText = (EditText) WelComeIntroView.findViewById(R.id.mobile);
        profilePicture = (CircleImageView) WelComeIntroView.findViewById(R.id.profilePicture);

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCropImage();
            }
        });
    }

    public void setWelComeIntroThroughAdminView(View v) {
        WelComeIntroThroughAdminView = v;

        fnameEditText = (EditText) WelComeIntroThroughAdminView.findViewById(R.id.fname);
        lnameEditText = (EditText) WelComeIntroThroughAdminView.findViewById(R.id.lname);
        mobileEditText = (EditText) WelComeIntroThroughAdminView.findViewById(R.id.mobile);
        adminInfo = (TextView) WelComeIntroThroughAdminView.findViewById(R.id.adminInfo);
        profilePicture = (CircleImageView) WelComeIntroThroughAdminView.findViewById(R.id.profilePicture);

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCropImage();
            }
        });
    }

    public void setWelComeRoleView(View v) {

        WelcomeRoleView = v;
        studentBlock = WelcomeRoleView.findViewById(R.id.studentBlock);
        alumniBlock = WelcomeRoleView.findViewById(R.id.alumniBlock);
        adminBlock = WelcomeRoleView.findViewById(R.id.adminBlock);
        hrBlock = WelcomeRoleView.findViewById(R.id.hrBlock);
        instOrEmail = (EditText) WelcomeRoleView.findViewById(R.id.instOrEmail);

        studentBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alumniBlock.setBackgroundResource(R.color.colorPrimary);
                adminBlock.setBackgroundResource(R.color.colorPrimary);
                hrBlock.setBackgroundResource(R.color.colorPrimary);
                studentBlock.setBackgroundResource(R.color.timestamp);
                SELECTED_ROLE = "student";
                instOrEmail.setHint("Institute code");
                // max length 8
                InputFilter[] fArray = new InputFilter[1];
                fArray[0] = new InputFilter.LengthFilter(8);
                instOrEmail.setFilters(fArray);

            }
        });

        alumniBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminBlock.setBackgroundResource(R.color.colorPrimary);
                studentBlock.setBackgroundResource(R.color.colorPrimary);
                hrBlock.setBackgroundResource(R.color.colorPrimary);
                alumniBlock.setBackgroundResource(R.color.timestamp);
                SELECTED_ROLE = "alumni";
                instOrEmail.setHint("Institute code");
                // max length 8
                InputFilter[] fArray = new InputFilter[1];
                fArray[0] = new InputFilter.LengthFilter(8);
                instOrEmail.setFilters(fArray);
            }
        });

        adminBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alumniBlock.setBackgroundResource(R.color.colorPrimary);
                studentBlock.setBackgroundResource(R.color.colorPrimary);
                hrBlock.setBackgroundResource(R.color.colorPrimary);
                adminBlock.setBackgroundResource(R.color.timestamp);
                SELECTED_ROLE = "admin";
                instOrEmail.setHint("professional Email");
                // max length 100
                InputFilter[] fArray = new InputFilter[1];
                fArray[0] = new InputFilter.LengthFilter(100);
                instOrEmail.setFilters(fArray);

            }
        });

        hrBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alumniBlock.setBackgroundResource(R.color.colorPrimary);
                adminBlock.setBackgroundResource(R.color.colorPrimary);
                studentBlock.setBackgroundResource(R.color.colorPrimary);
                hrBlock.setBackgroundResource(R.color.timestamp);
                SELECTED_ROLE = "hr";
                instOrEmail.setHint("professional Email");
                // max length 100
                InputFilter[] fArray = new InputFilter[1];
                fArray[0] = new InputFilter.LengthFilter(100);
                instOrEmail.setFilters(fArray);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        fa = Typeface.createFromAsset(this.getAssets(),  "fonts/fa.ttf");
        bold = Typeface.createFromAsset(this.getAssets(),  "fonts/nunitobold.ttf");
        light = Typeface.createFromAsset(this.getAssets(),  "fonts/nunitolight.ttf");

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        resultView = (ImageView) findViewById(R.id.result_image);
        nextProgress = (ProgressBar) findViewById(R.id.nextProgress);
        crop_layout = (FrameLayout) findViewById(R.id.crop_layout);
        btnNext.setTypeface(bold);
        btnPrev.setTypeface(bold);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        // layouts of all welcome sliders
        // add few more layouts if you want


        // adding bottom dots

        // making notification bar transparent
        changeStatusBarColor();

//        setupViewPagerNewUser(viewPager);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


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

                if(currentPosition==0)
                    btnPrev.setVisibility(View.GONE);
                else
                    btnPrev.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        myViewPagerAdapter = new MyAdapter(getSupportFragmentManager());
        myViewPagerAdapter.addFrag(new WelcomeEmailFragment());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setCurrentItem(0);
        addBottomDots(0, 2);



        try {
            android_id = Settings.Secure.getString(getApplication().getContentResolver(), Settings.Secure.ANDROID_ID);
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            device_id = telephonyManager.getDeviceId();
        } catch (Exception e) {
        }

        Log.d("TAG", "onCreate: **************** aid : " + android_id);
        //--------------------------------------------------- NEXT BUTTON ---------------------------------------------//

//       path 1. existing user
//       path 2. new user
//       path 3. user through admin

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TAG", "onClick: currentposition -------------------------------- " + currentPosition);
                Log.d("TAG", "onClick: path ------------------- " + path);

                if (currentPosition == 0) {
                    plainUsername = usernameedittext.getText().toString().trim();

                    boolean usernameflag = false;
                    if (plainUsername.equals("")) {
                        usernameflag = true;
                        usernameTextInputLayout.setError("Field Can Not Be Empty");
                    } else if (plainUsername.length() < 5) {
                        usernameflag = true;
                        usernameTextInputLayout.setError("Enter Valid Email Address");
                    } else if (!plainUsername.contains("@")) {
                        usernameflag = true;
                        usernameTextInputLayout.setError("Enter Valid Email Address");
                    }
                    if (usernameflag == false) {
                        new ValidateUser().execute();
                    }

                } else {

                    if (path == 1)     // existing user
                    {
                        if (currentPosition == 1) {     // added this check 28-11
                            passwordstr = passwordedittext.getText().toString();

                            if (passwordstr.equals("")) {
                                passwordedittext.setError("Field can not be empty");
                            } else if (passwordstr.length() < 6) {
                                passwordedittext.setError("Incorrect Password");
                            } else {
                                try {
                                    byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                                    byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                                    String sPadding = "ISO10126Padding";

                                    byte[] passwordBytes = passwordstr.getBytes("UTF-8");
                                    byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, passwordBytes);
                                    encPassword = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));

                                    MySharedPreferencesManager.save(Welcome.this, "passKey", encPassword);

                                    nextProgress.setVisibility(View.VISIBLE);
                                    btnNext.setVisibility(View.GONE);
                                    attemptLogin(encUsersName, encPassword);

                                } catch (Exception e) {
                                    Log.d("TAG", "onClick: pass exp " + e.getMessage());
                                }
                            }
                        }
                    } else if (path == 2)        //user not found i.e new user
                    {
                        if (currentPosition == 1) {
                            mobileEditText.setError(null);
                            fnameEditText.setError(null);
                            lnameEditText.setError(null);

                            errorFlagIntro = false;
                            fname = fnameEditText.getText().toString().trim();
                            lname = lnameEditText.getText().toString().trim();
                            mobile = mobileEditText.getText().toString().trim();

                            if (fname.length() < 2) {
                                errorFlagIntro = true;
                                fnameEditText.setError("Enter minimum 2 characters");
                            } else if (lname.length() < 2) {
                                lnameEditText.setError("Enter minimum 2 characters");
                                errorFlagIntro = true;
                            } else if (mobile.length() != 10) {
                                mobileEditText.setError("Incorrect mobile number");
                                errorFlagIntro = true;
                            }

                            if (!errorFlagIntro) {

                                try {


                                    byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                                    byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                                    String sPadding = "ISO10126Padding";

                                    byte[] fnameBytes = fname.getBytes("UTF-8");
                                    byte[] lnameBytes = lname.getBytes("UTF-8");
                                    byte[] mobileBytes = mobile.getBytes("UTF-8");

                                    byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fnameBytes);
                                    encfname = new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));
                                    byte[] lnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, lnameBytes);
                                    enclname = new String(SimpleBase64Encoder.encode(lnameEncryptedBytes));
                                    byte[] mobileEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, mobileBytes);
                                    encmobile = new String(SimpleBase64Encoder.encode(mobileEncryptedBytes));

                                    viewPager.setCurrentItem(2);
                                    addBottomDots(2, 4);


                                } catch (Exception e) {
                                    Log.d("TAG", "onClick: EXp " + e.getMessage());
                                }
                            }
                        } else if (currentPosition == 2) {    //role

                            instOrEmailstr = instOrEmail.getText().toString();
                            if (SELECTED_ROLE != null) {
                                if (SELECTED_ROLE.equals("student") || SELECTED_ROLE.equals("alumni")) {
                                    instOrEmailstr = instOrEmailstr.toUpperCase();
                                }
                            }
                            instOrEmail.setError(null);

                            if (SELECTED_ROLE != null) {
                                if (SELECTED_ROLE.equals("student") || SELECTED_ROLE.equals("alumni")) {
                                    if (instOrEmailstr.length() != 8) {
                                        instoremailerror = true;
                                        instOrEmail.setError("Incorrect Institute code");
                                    }
                                }
                                if (SELECTED_ROLE.equals("admin")) {
                                    genrateCodeFlag = true;
                                    // for testing validation in comment  *****************************************

                                    if (!instOrEmailstr.contains("@")) {
                                        instoremailerror = true;
                                        instOrEmail.setError("Incorrect Email");
                                    }
//                                    else if (!instOrEmailstr.contains(".edu")) {
//                                        instoremaileror = true;
//                                        instOrEmail.setError("Incorrect professional Email");
//                                    }

                                }
                                if (SELECTED_ROLE.equals("hr")) {
                                    genrateCodeFlag = true;
                                    String email = instOrEmail.getText().toString().trim();
                                    if (!email.contains("@")) {
                                        instoremailerror = true;
                                        instOrEmail.setError("Incorrect Email");
                                    }
//                                    else if (email.contains("gmail") || email.contains("yahoo") || email.contains("ymail") || email.contains("rediffmail") || email.contains("outlook") || email.contains("hotmail")) {
//                                        instoremaileror = true;
//                                        instOrEmail.setError("Incorrect professional Email");
//                                    }
                                }

                                if (!instoremailerror) {
                                    if (SELECTED_ROLE.equals("student") || SELECTED_ROLE.equals("alumni")) {

                                        new checkUcode().execute(instOrEmailstr);

                                    } else {
                                        viewPager.setCurrentItem(3);
                                        addBottomDots(3, 4);
                                    }
                                }

                            } else
                                Toast.makeText(Welcome.this, "select role", Toast.LENGTH_SHORT).show();

                        }
                        if (currentPosition == 3) {    //passwoord

                            confirmPassword.setError(null);

                            boolean errorflag = false;
                            String enterpass = enterPassword.getText().toString();
                            confrimpass = confirmPassword.getText().toString();



                            if (!enterpass.equals(confrimpass)) {
                                errorflag = true;
                                confirmPassword.setError("confirm password not matched");
                            } else if (enterpass.length() < 6 || confrimpass.length() < 6) {
                                errorflag = true;
                                confirmPassword.setError("Passwords must be at least 6 characters long");
                            }
                            if (errorflag == false) {
                                try {
                                    byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                                    byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                                    String sPadding = "ISO10126Padding";
                                    byte[] passwordBytes = confrimpass.getBytes("UTF-8");
                                    byte[] passwordEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, passwordBytes);
                                    encPassword = new String(SimpleBase64Encoder.encode(passwordEncryptedBytes));
                                } catch (Exception e) {
                                    Log.d("TAG", "onClick: EXp " + e.getMessage());
                                }
//                                Toast.makeText(Welcome.this, "start", Toast.LENGTH_SHORT).show();
                                if (genrateCodeFlag == true) {
                                    //save pref  password instcode
                                    //call a servlet that will send activation code
                                    //call otp activty
                                    //verify otp (activation code)
                                    //call activity like welcome

                                    MySharedPreferencesManager.save(Welcome.this, "passKey", encPassword);
                                    MySharedPreferencesManager.save(Welcome.this, "role", SELECTED_ROLE);
                                    MySharedPreferencesManager.save(Welcome.this, "nameKey", encUsersName);

                                    MySharedPreferencesManager.save(Welcome.this, "fname", encfname);
                                    MySharedPreferencesManager.save(Welcome.this, "lname", enclname);
                                    MySharedPreferencesManager.save(Welcome.this, "phone", encmobile);

                                    Log.d("TAG", "save to shared encPassword " + encPassword);
                                    Log.d("TAG", "save to shared SELECTED_ROLE " + SELECTED_ROLE);
                                    Log.d("TAG", "save to shared encUsersName " + encUsersName);
                                    Log.d("TAG", "save to shared fname " + encfname);
                                    Log.d("TAG", "save to shared lname " + enclname);
                                    Log.d("TAG", "save to shared phone " + encmobile);

                                    new SendActivationCode().execute();
                                } else {
                                    nextProgress.setVisibility(View.INVISIBLE);
                                    new SaveData().execute();
                                }
                            }
                        }
                    } else if (path == 3) {

                        errorFlagThroughAdminIntro = false;
                        fname = fnameEditText.getText().toString().trim();
                        lname = lnameEditText.getText().toString().trim();
                        mobile = mobileEditText.getText().toString().trim();

                        if (fname.length() < 2) {
                            errorFlagThroughAdminIntro = true;
                            fnameEditText.setError("Enter minimum 2 characters");
                        } else if (lname.length() < 2) {
                            lnameEditText.setError("Enter minimum 2 characters");
                            errorFlagThroughAdminIntro = true;
                        } else if (mobile.length() != 10) {
                            mobileEditText.setError("Incorrect mobile number");
                            errorFlagThroughAdminIntro = true;
                        }


                        if (errorFlagThroughAdminIntro == false) {
                            Toast.makeText(Welcome.this, "path 3", Toast.LENGTH_SHORT).show();
                            new SaveDataUserCreatedThroughAdmin().execute();
                        }
                    }

                }


            }

        });

        resultView = (ImageView)

                findViewById(R.id.result_image);

        imagePicker = new

                ImagePicker(this);
        imagePicker.setImagePickerCallback(Welcome.this);

        imagePicker.shouldGenerateMetadata(false); // Default is true
        imagePicker.shouldGenerateThumbnails(false); // Default is true


    }//onc


    class SaveData extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {


            try {

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";

                Log.d("TAG", "doInBackground: plain role ========================  " + SELECTED_ROLE);
                Log.d("TAG", "doInBackground: fname " + fname);
                Log.d("TAG", "doInBackground: lname " + lname);
                Log.d("TAG", "doInBackground: mobile " + mobile);
                Log.d("TAG", "doInBackground: passwordstr " + passwordstr);
                Log.d("TAG", "doInBackground: instOrEmailstr " + instOrEmailstr);
                Log.d("TAG", "doInBackground: plainUsername " + plainUsername);
                Log.d("TAG", "doInBackground: plainUsername " + plainUsername);


                byte[] fnameBytes = fname.getBytes("UTF-8");
                byte[] lnameBytes = lname.getBytes("UTF-8");
                byte[] mobileBytes = mobile.getBytes("UTF-8");
                byte[] passwordBytes = confrimpass.getBytes("UTF-8");
                byte[] instOrEmailstrBytes = instOrEmailstr.getBytes("UTF-8");
                byte[] usernameBytes = plainUsername.getBytes("UTF-8");
                byte[] roleBytes = SELECTED_ROLE.getBytes("UTF-8");

                Log.d("TAG", "doInBackground: Aftrer Bytes role ========================  " + SELECTED_ROLE);

                byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                encUsersName = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));
                byte[] passwordEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, passwordBytes);
                encPassword = new String(SimpleBase64Encoder.encode(passwordEncryptedBytes));
                byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fnameBytes);
                encfname = new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));
                byte[] lnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, lnameBytes);
                enclname = new String(SimpleBase64Encoder.encode(lnameEncryptedBytes));
                byte[] mobileEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, mobileBytes);
                encmobile = new String(SimpleBase64Encoder.encode(mobileEncryptedBytes));
                byte[] instOrEmailstrEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, instOrEmailstrBytes);
                encinstOrEmail = new String(SimpleBase64Encoder.encode(instOrEmailstrEncryptedBytes));

                byte[] roleEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, roleBytes);
                encrole = new String(SimpleBase64Encoder.encode(roleEncryptedBytes));


            } catch (Exception e) {
                Log.d("TAG", "doInBackground: exp " + e.getMessage());
            }

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsersName));       // 0
            params.add(new BasicNameValuePair("r", encrole));            // 1
            params.add(new BasicNameValuePair("f", encfname));           // 2
            params.add(new BasicNameValuePair("l", enclname));           // 3
            params.add(new BasicNameValuePair("m", encmobile));          // 4
            params.add(new BasicNameValuePair("ie", encinstOrEmail));    // 5
            params.add(new BasicNameValuePair("p", encPassword));        // 6
            params.add(new BasicNameValuePair("id", android_id));        // 7

            Log.d("TAG", "doInBackground: enc role ========================  " + encrole);


            json = jParser.makeHttpRequest(MyConstants.url_SaveWelcomeIntroData, "GET", params);
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
                Toast.makeText(Welcome.this, "Successfully Register Account..!", Toast.LENGTH_SHORT).show();

                Log.d("TAG", "onPostExecute: SaveData role " + SELECTED_ROLE);

                MySharedPreferencesManager.save(Welcome.this, "role", SELECTED_ROLE);       //0
                MySharedPreferencesManager.save(Welcome.this, "nameKey", encUsersName);     //1
                MySharedPreferencesManager.save(Welcome.this, "passKey", encPassword);      //2
                MySharedPreferencesManager.save(Welcome.this, "fname", encfname);           //3
                MySharedPreferencesManager.save(Welcome.this, "lname", enclname);            //4
                nextProgress.setVisibility(View.GONE);
                Intent loginintent = new Intent(Welcome.this, LoginActivity.class);
                loginintent.putExtra("showOTP", "yes");
                startActivity(loginintent);
            }
        }
    }

    class checkUcode extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            String inputUcode = param[0];
            Log.d("TAG", "checkUcode: " + inputUcode);
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", inputUcode));       //0

            json = jParser.makeHttpRequest(MyConstants.url_checkUcode, "GET", params);
            Log.d("TAG", "checkUcode result: " + json);
            try {
                r = json.getString("info");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result != null && result.equals("found")) {
                viewPager.setCurrentItem(3);
                addBottomDots(3, 4);
            } else {
                Toast.makeText(Welcome.this, "Invalid Institute Code\nplease contact your TPO", Toast.LENGTH_LONG).show();
                instOrEmail.setError("Invalid Institute Code\nplease contact your TPO");
            }
        }
    }

    class SaveDataUserCreatedThroughAdmin extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsersName));       //0
            params.add(new BasicNameValuePair("f", encfname));           //1
            params.add(new BasicNameValuePair("l", enclname));           //2
            params.add(new BasicNameValuePair("m", encmobile));          //3


            Log.d("TAG", "doInBackground: enc role ========================  " + encrole);


            json = jParser.makeHttpRequest(MyConstants.url_SaveStudentFnameLnameMobile, "GET", params);
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
                MySharedPreferencesManager.save(Welcome.this, "role", "student");
                MySharedPreferencesManager.save(Welcome.this, "nameKey", encUsersName);

                new CreateFirebaseUser(encUsersName, encPassword).execute();

                startActivity(new Intent(Welcome.this, MainActivity.class));
//                Intent loginintent = new Intent(Welcome.this, LoginActivity.class);
//                loginintent.putExtra("newUser", "yes");
//                startActivity(loginintent);


            }
        }
    }

    class CreateFirebaseUser extends AsyncTask<String, String, String> {

        String u, p;

        CreateFirebaseUser(String u, String p) {
            this.u = u;
            this.p = p;
        }

        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", u));
            params.add(new BasicNameValuePair("p", p));
            params.add(new BasicNameValuePair("t", new SharedPrefUtil(getApplicationContext()).getString("firebaseToken"))); //5

            json = jParser.makeHttpRequest(MyConstants.url_create_firebase, "GET", params);
            try {
                resultofop = json.getString("info");
                Log.d("TAG", " CreateFirebaseUser json : " + json);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {


            String salt = MySharedPreferencesManager.getDigest3(Welcome.this);
            Log.d("TAG", "plain salt " + salt);

            String hash = md5(passwordstr + salt);
            loginFirebase(plainUsername, hash);
            Toast.makeText(Welcome.this, resultofop, Toast.LENGTH_LONG).show();

        }

        void loginFirebase(String username, String hash) {
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(username, hash)
                    .addOnCompleteListener(Welcome.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {
                                Toast.makeText(Welcome.this, "Successfully logged in to Firebase", Toast.LENGTH_SHORT).show();
                                Log.d("TAG", "Successfully logged in to Firebase ===================  ");


                            } else {
                                Toast.makeText(Welcome.this, "Failed to login to Firebase", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
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
            dots[i].setTypeface(fa);
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

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            if (path == 1) {
                addBottomDots(position, 2);

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

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void onError(String s) {
        crop_layout.setVisibility(View.GONE);
        // tswap       tswipe_refresh_layout.setVisibility(View.GONE);
        Toast.makeText(Welcome.this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onImagesChosen(List<ChosenImage> list) {
        final ChosenImage file = list.get(0);

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (file != null) {

                    finalPath = file.getOriginalPath().toString();
                }
            }
        });
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


    class SendActivationCode extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {
            String s = null;

            try {

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";


                byte[] proEmailBytes = instOrEmailstr.getBytes("UTF-8");
                byte[] proEmailEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, proEmailBytes);
                encProMail = new String(SimpleBase64Encoder.encode(proEmailEncryptedBytes));


            } catch (Exception e) {
                Log.d("TAG", "doInBackground: 1 " + e.getMessage());
            }

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encProMail));              //0
            params.add(new BasicNameValuePair("f", encfname));                  //1
            params.add(new BasicNameValuePair("l", enclname));                  //2

            json = jParser.makeHttpRequest(MyConstants.url_SendActivationCode, "GET", params);
            try {
                s = json.getString("info");
                Log.d("TAG2", "json: " + json);

            } catch (Exception e) {
                Log.d("TAG", "doInBackground: 2 " + e.getMessage());
            }

            return s;
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d("TAG", result);
            if (result.equals("success")) {
                Toast.makeText(Welcome.this, "send activation code", Toast.LENGTH_SHORT).show();
                MySharedPreferencesManager.save(Welcome.this, "activationMessage", "yes");
                MySharedPreferencesManager.save(Welcome.this, "proEmail", encProMail);
                startActivity(new Intent(Welcome.this, OTPActivity.class));

            } else if (result.equals("exist")) {
                Toast.makeText(Welcome.this, "Account already exist on PlaceMe", Toast.LENGTH_SHORT).show();
            } else {
                if (instOrEmail != null) {
                    instOrEmail.setError("Incorrect Professional Email");
                } else
                    Toast.makeText(Welcome.this, "Incorrect Professional Email", Toast.LENGTH_SHORT).show();

                String enterpass = enterPassword.getText().toString();
                confrimpass = confirmPassword.getText().toString();

                if (enterPassword != null && confirmPassword != null) {
                    enterPassword.setText("");
                    confirmPassword.setText("");
                }

                onBackPressed();


            }

        }
    }

    class ValidateUser extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {
            String s = null;

            try {

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";

                Log.d("TAG", "input ValidateUser plain---" + plainUsername);
                byte[] usernameBytes = plainUsername.getBytes("UTF-8");
                byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                encUsersName = new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));
                Log.d("TAG", "input ValidateUser enc: " + encUsersName);

                MySharedPreferencesManager.save(Welcome.this, "nameKey", encUsersName);

            } catch (Exception e) {
                Log.d("TAG", "ValidateUser doInBackground exp " + e.getMessage());
            }

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsersName));       //0

            json = jParser.makeHttpRequest(MyConstants.url_Welcome, "GET", params);
            try {

                s = json.getString("info");

                Log.d("TAG", "ValidateUser json: " + json);

            } catch (Exception e) {
                Log.d("TAG", "doInBackground 2 json  exp :  " + e.getMessage());
            }

            return s;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("found")) {

                path = 1;
//                setupViewPagerExistingActivatedUser(viewPager);

                if (myViewPagerAdapter.getCount() > 1) {


                    myViewPagerAdapter.clear();
                    myViewPagerAdapter.addFrag(new WelcomeEmailFragment());
                    myViewPagerAdapter.addFrag(new WelcomePasswordFragment());
                    myViewPagerAdapter.notifyDataSetChanged();
                    viewPager.setCurrentItem(1);
                    addBottomDots(1, 2);

                } else {

                    myViewPagerAdapter.addFrag(new WelcomePasswordFragment());
                    myViewPagerAdapter.notifyDataSetChanged();
                    Log.d("TAG", "onPostExecute: pasword frag added");
                    viewPager.setCurrentItem(1);
                    addBottomDots(1, 2);
                }

            } else if (result.equals("notfound")) {

                path = 2;
                if (myViewPagerAdapter.getCount() > 1) {
                    //clear the adapter first and then add all the fragments
                    myViewPagerAdapter.clear();
                    myViewPagerAdapter.addFrag(new WelcomeEmailFragment());
                    myViewPagerAdapter.addFrag(new WelcomeIntroFragment());
                    myViewPagerAdapter.addFrag(new WelcomeRoleFragment());
                    myViewPagerAdapter.addFrag(new WelcomeCreatePasswordFragment());
                    myViewPagerAdapter.notifyDataSetChanged();
                    viewPager.setCurrentItem(1);
                    addBottomDots(1, 4);


                } else {
                    myViewPagerAdapter.addFrag(new WelcomeIntroFragment());
                    myViewPagerAdapter.addFrag(new WelcomeRoleFragment());
                    myViewPagerAdapter.addFrag(new WelcomeCreatePasswordFragment());
                    myViewPagerAdapter.notifyDataSetChanged();
                    viewPager.setCurrentItem(1);
                    addBottomDots(1, 4);
                }

            }
        }
    }

    void attemptLogin(String u, String p) {

        Log.d("TAG", "login using:  " + u + "/n" + p);

        UserLoginTask userLoginTask = new UserLoginTask(u, p);
        userLoginTask.execute();


    }

    public class UserLoginTask extends AsyncTask<Void, Void, Integer> {

        private final String mEmail;
        private final String mPassword;


        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Integer doInBackground(Void... args) {

            int returnCode = 0;

            try {
                if (isNetworkAvailable()) {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("u", mEmail));
                    params.add(new BasicNameValuePair("p", mPassword));
                    Log.d("TAG", "UserLoginTask user input : " + mEmail + " \t pass : " + mPassword);
//                params.add(new BasicNameValuePair("t", new SharedPrefUtil(Welcome.this.getApplicationContext()).getString("firebaseToken")));
                    json = jParser.makeHttpRequest(MyConstants.url_login, "GET", params);
                    String s = null;

                    s = json.getString("info");

                    Log.d("TAG", "UserLoginTask json: " + json);
                    Log.d("TAG", "result   " + s);

                    resultofop = s;

//                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                    editor.putString("role", s);
//                    editor.commit();

                    Log.d("TAG", "UserLoginTask : role ---------------------------------- " + s);
                    if (!s.equals("notactivated"))
                        MySharedPreferencesManager.save(Welcome.this, "role", s);
                    else {
                        MySharedPreferencesManager.save(Welcome.this, "role", json.getString("role"));
                    }

                    if (s.equals("student")) {

                        EmailCred = mEmail;
                        returnCode = 1;
                        return 1;
                    } else if (s.equals("admin")) {

                        EmailCred = mEmail;
                        returnCode = 3;
                        return 3;
                    } else if (s.equals("hr")) {

                        EmailCred = mEmail;
                        returnCode = 4;
                        return 4;
                    } else if (s.equals("alumni")) {

                        EmailCred = mEmail;
                        returnCode = 5;
                        return 5;
                    }
                    if (s.equals("notactivated")) {             // throughAdmin
                        Log.d("TAG", "------------------------------------------ user notactivated ");

                        String throughAdmin = json.getString("throughAdmin");
                        Log.d("TAG, ", "------------------------------------------  user throughAdmin = " + throughAdmin);
                        Log.d("TAG", "json" + json);

                        if (throughAdmin != null && throughAdmin.equals("yes")) {
                            throughAdminFlag = true;

                            adminInstitute = json.getString("adminInst");
                            adminfname = json.getString("adminfname");
                            adminlname = json.getString("adminlname");
                            try {
                                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                                String sPadding = "ISO10126Padding";

                                byte[] adminInstituteEncryptedBytes = SimpleBase64Encoder.decode(adminInstitute);
                                byte[] adminInstituteDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, adminInstituteEncryptedBytes);
                                adminInstitute = new String(adminInstituteDecryptedBytes);

                                byte[] fnameEncryptedBytes = SimpleBase64Encoder.decode(adminfname);
                                byte[] fnameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, fnameEncryptedBytes);
                                adminfname = new String(fnameDecryptedBytes);

                                byte[] lnameEncryptedBytes = SimpleBase64Encoder.decode(adminlname);
                                byte[] lnameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, lnameEncryptedBytes);
                                adminlname = new String(lnameDecryptedBytes);

//                                Log.d("TAG", "doInBackground: plain inst "+adminInstitute);
//                                Log.d("TAG", "doInBackground: plain fname "+adminfname);
//                                Log.d("TAG", "doInBackground: plain lname "+adminlname);
                                returnCode = 7;
                                return 7;
                            } catch (Exception e) {
                                Log.d("TAG", "doInBackground: exp " + e.getMessage());
                            }


                        } else {
                            Log.d("TAG", "doInBackground: else");
                            String role = json.getString("role");
                            if (!s.equals("notactivated"))
                                MySharedPreferencesManager.save(Welcome.this, "role", s);
                            else {
                                MySharedPreferencesManager.save(Welcome.this, "role", json.getString("role"));
                            }
                            EmailCred = mEmail;
                            returnCode = 6;
                            return 6;
                        }


                    }

                } else {
                    returnCode = 2;
                    return 2;
                }

            } catch (Exception e) {
                Log.d("exp", e.getMessage());
                e.printStackTrace();
            }
            return returnCode;
        }

        @Override
        protected void onPostExecute(final Integer success) {

            processOutput(success);

        }

        void processOutput(final int success) {
            if (success == 2) {

                Toast.makeText(Welcome.this, "No Internet Connection..!", Toast.LENGTH_LONG).show();
            } else if (success == 7) { // through admin
                path = 3;

                String MSG = adminfname + " " + adminlname + " from " + adminInstitute;

                if (myViewPagerAdapter.getCount() > 2) {
                    //clear the adapter first and then add all the fragments
                    myViewPagerAdapter.clear();
                    myViewPagerAdapter.addFrag(new WelcomeEmailFragment());
                    myViewPagerAdapter.addFrag(new WelcomePasswordFragment());
                    myViewPagerAdapter.addFrag(new WelcomeIntroThroughAdminFragment());
                    myViewPagerAdapter.notifyDataSetChanged();
                    viewPager.setCurrentItem(2);
                    addBottomDots(2, 3);

                } else {
                    myViewPagerAdapter.addFrag(new WelcomeIntroThroughAdminFragment());
                    myViewPagerAdapter.notifyDataSetChanged();
                    viewPager.setCurrentItem(2);
                    addBottomDots(2, 3);
                }
                adminInfo.setText(MSG);


            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (success == 1) {
                            new SaveSessionDetails().execute();
//                            ProfileRole r = new ProfileRole();
//                            r.setRole("student");
//                            r.setUsername(EmailCred);
                            MySharedPreferencesManager.save(Welcome.this, "nameKey", EmailCred);
                            MySharedPreferencesManager.save(Welcome.this, "role", "student");
                            Log.d("TAG", "launching mainactivity..");
                            startActivity(new Intent(Welcome.this, MainActivity.class));
                            finish();
                        } else if (success == 3) {
                            new SaveSessionDetails().execute();
                            MySharedPreferencesManager.save(Welcome.this, "role", "admin");
                            MySharedPreferencesManager.save(Welcome.this, "nameKey", EmailCred);
//                            ProfileRole r = new ProfileRole();
//                            r.setRole("admin");
//                            r.setUsername(EmailCred);
                            startActivity(new Intent(Welcome.this, AdminActivity.class));
                            finish();
                        } else if (success == 4) {
                            new SaveSessionDetails().execute();
                            MySharedPreferencesManager.save(Welcome.this, "role", "hr");
                            MySharedPreferencesManager.save(Welcome.this, "nameKey", EmailCred);

//                            ProfileRole r = new ProfileRole();
//                            r.setRole("hr");
//                            r.setUsername(EmailCred);
                            startActivity(new Intent(Welcome.this, HRActivity.class));
                            finish();
                        } else if (success == 5) {
                            new SaveSessionDetails().execute();
                            MySharedPreferencesManager.save(Welcome.this, "role", "alumni");
                            MySharedPreferencesManager.save(Welcome.this, "nameKey", EmailCred);

//                            ProfileRole r = new ProfileRole();
//                            r.setRole("alumni");
//                            r.setUsername(EmailCred);
                            startActivity(new Intent(Welcome.this, AlumniActivity.class));
                            finish();
                        }
                    }
                }).start();
            }

            if (success == 6) {
                Toast.makeText(Welcome.this, "You are already registered but not verified.Enter OTP sent on Email", Toast.LENGTH_LONG).show();
                Intent loginintent = new Intent(Welcome.this, LoginActivity.class);
                loginintent.putExtra("showOTP", "yes");
                startActivity(loginintent);
                finish();
            } else if (resultofop.equals("notpresent")) {
                Toast.makeText(Welcome.this, "Incorrect Username. If you are a new user, please Sign Up.", Toast.LENGTH_LONG).show();

            } else if (resultofop.equals("fail")) {
                Toast.makeText(Welcome.this, "Incorrect Password..!", Toast.LENGTH_LONG).show();
                WelcomePasswordFragment fragment = (WelcomePasswordFragment) myViewPagerAdapter.getItem(1);
                passwordedittext.setText("");
            } else {
                WelcomePasswordFragment fragment = (WelcomePasswordFragment) myViewPagerAdapter.getItem(1);
                passwordedittext.setText("");

            }

            nextProgress.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);
        }
    }

    private boolean isNetworkAvailable() throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            HttpURLConnection localHttpURLConnection = (HttpURLConnection) new URL("http://192.168.100.100/").openConnection();
            localHttpURLConnection.setConnectTimeout(1000);
            localHttpURLConnection.connect();
            return true;
        }
        return false;
    }

    class SaveSessionDetails extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsersName));    //0
            params.add(new BasicNameValuePair("m", getDeviceName()));      //1
            json = jParser.makeHttpRequest(MyConstants.url_savesessiondetails, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }


    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }
    //prfile image ***

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        Log.d("TAG", "onActivityResult: hr activity " + resultCode);

        try {

        } catch (Exception e) {
        }
//        plainusername=sharedpreferences.getString("plain",null);
//        byte[] skill1EncryptedBytes = SimpleBase64Encoder.decode(skill1);
//        skill1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, skill1EncryptedBytes);
//        skill1 = new String(skill1DecryptedBytes);
//        s.setSkill1(skill1);

//        String role


        if (requestCode == Picker.PICK_IMAGE_DEVICE) {

            try {

                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback((ImagePickerCallback) this);
                }
                imagePicker.submit(result);
                crop_layout.setVisibility(View.VISIBLE);

                crop_flag = 1;
                beginCrop(result.getData());

            } catch (Exception e) {
                crop_layout.setVisibility(View.GONE);


            }
        } else if (resultCode == RESULT_CANCELED) {
            crop_layout.setVisibility(View.GONE);


            crop_flag = 0;
        } else if (requestCode == Crop.REQUEST_CROP) {
            // Toast.makeText(this, "cropped", Toast.LENGTH_SHORT).show();
            handleCrop(resultCode, result);
        }

//    if(requestCode==HRProfileFragment.COMPANY_DEATAILS_CHANGE_REQUEST)
//    {
//
//    }
    }

    public void requestCropImage() {
        resultView.setImageDrawable(null);

        MySharedPreferencesManager.save(Welcome.this, "crop", "yes");

        chooseImage();
    }

    private void chooseImage() {

        imagePicker.pickImage();
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            File f = new File(getCacheDir(), "cropped");
            filepath = f.getAbsolutePath();

            filename = "";
            int index = filepath.lastIndexOf("/");
            directory = "";
            for (int i = 0; i < index; i++)
                directory += filepath.charAt(i);

            for (int i = index + 1; i < filepath.length(); i++)
                filename += filepath.charAt(i);

            crop_layout.setVisibility(View.GONE);
            new UploadProfile().execute();

        } else if (resultCode == Crop.RESULT_ERROR) {
            crop_layout.setVisibility(View.GONE);

            Toast.makeText(this, "Try Again..!", Toast.LENGTH_SHORT).show();

        }
    }

    class UploadProfile extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            try {
                File sourceFile = new File(filepath);
                MultipartUtility multipart = new MultipartUtility(MyConstants.upload_profile, "UTF-8");
                multipart.addFormField("u", encUsersName);
                if (filename != "") {
                    multipart.addFormField("f", filename);
                    multipart.addFilePart("uf", sourceFile);
                } else
                    multipart.addFormField("f", "null");
                response = multipart.finish();


            } catch (Exception ex) {

            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            crop_layout.setVisibility(View.GONE);

            if (response.get(0).contains("success")) {

                MySharedPreferencesManager.save(Welcome.this, "crop", "no");

                Toast.makeText(Welcome.this, "Successfully Updated..!", Toast.LENGTH_SHORT).show();
                requestProfileImage();
                refreshContent();
                DeleteRecursive(new File(directory));
            } else if (response.get(0).contains("null")) {
                requestProfileImage();
                Toast.makeText(Welcome.this, "Try Again", Toast.LENGTH_SHORT).show();
            }

        }

        void DeleteRecursive(File fileOrDirectory) {

            if (fileOrDirectory.isDirectory())
                for (File child : fileOrDirectory.listFiles())
                    DeleteRecursive(child);

            fileOrDirectory.delete();

        }

    }

    public void refreshContent() {
        downloadImage();
    }

    public void requestProfileImage() {
        // Toast.makeText(this, "thumbnail Method()", Toast.LENGTH_SHORT).show();
//        new GetProfileImage().execute();
        downloadImage();

    }

    private void downloadImage() {

        String t = String.valueOf(System.currentTimeMillis());

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("192.168.100.100")
                .path("AESTest/GetImage")
                .appendQueryParameter("u", encUsersName)
                .build();

        GlideApp.with(this)
                .load(uri)
                .signature(new ObjectKey(System.currentTimeMillis() + ""))
                .into(profilePicture);


    }

    @Override
    public void onBackPressed() {


        if (path == 3 && currentPosition == 2)
            path = 1;

        if (currentPosition != 0) {
            currentPosition--;
            viewPager.setCurrentItem(currentPosition);
            //dot coding

            switch (currentPosition) {
                case 0: {
                    if (path == 1) {
                        addBottomDots(0, 2);
                    } else if (path == 2) {
                        addBottomDots(0, 4);
                    }
                    break;
                }
                case 1: {
                    if (path == 1) {
                        addBottomDots(1, 2);
                    } else if (path == 2) {
                        addBottomDots(1, 4);
                    }
                    break;
                }
                case 2: {
                    if (path == 2) {
                        addBottomDots(2, 4);
                    }
                    if (path == 3) {
                        addBottomDots(2, 3);
                    }

                    break;
                }
            }


        } else
            super.onBackPressed();
    }

    //end prfile image

}
