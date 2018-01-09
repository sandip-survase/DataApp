package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class LoginActivity extends AppCompatActivity {

    EditText usernameedittext, passwordedittext;
    String username, usernameenc, password;
    Button login, signup;
    ProgressBar loginprogress;
    TextView forgotpassword;
    private String EmailCred = "";
    JSONParser jParser = new JSONParser();
    JSONObject json;
    String resultofop = "";
    String country = "", city = "", isp = "", countryCode = "", query = "";
    String digest1, digest2;
    TextInputLayout usernameTextInputLayout, passwordTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        usernameedittext = (EditText) findViewById(R.id.email);
        passwordedittext = (EditText) findViewById(R.id.password);
        usernameTextInputLayout = (TextInputLayout) findViewById(R.id.usernameTextInputLayout);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.passwordTextInputLayout);

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
        passwordedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        usernameedittext.setTypeface(Z.getBold(this));
        passwordedittext.setTypeface(Z.getBold(this));
        usernameTextInputLayout.setTypeface(Z.getLight(this));
        passwordTextInputLayout.setTypeface(Z.getLight(this));

        login = (Button) findViewById(R.id.login);
        login.setTypeface(Z.getBold(this));
        loginprogress = (ProgressBar) findViewById(R.id.loginprogress);
        forgotpassword = (TextView) findViewById(R.id.forgot);
        forgotpassword.setTypeface(Z.getBold(this));
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordDialog.class));
            }
        });
        signup = (Button) findViewById(R.id.signup);
        signup.setTypeface(Z.getBold(this));

        TextView newp = (TextView) findViewById(R.id.newp);
        newp.setTypeface(Z.getBold(this));

        String otp = MySharedPreferencesManager.getData(LoginActivity.this, "otp");
        String otp2 = MySharedPreferencesManager.getData(LoginActivity.this, "otp2");

        if (otp != null) {
            if (otp.equals("yes")) {
                startActivity(new Intent(getApplicationContext(), OTPActivity.class));

            }
        }
        if (otp2 != null) {
            if (otp2.equals("yes")) {
                startActivity(new Intent(getApplicationContext(), OTP2Activity.class));

            }
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                username = usernameedittext.getText().toString();
                password = passwordedittext.getText().toString();
                int flag1 = 0, flag2 = 0;

                if (username.equals("")) {
                    flag1 = 1;
                    usernameTextInputLayout.setError("Kindly provide your email address");
                }
                if (!username.contains("@")) {
                    flag1 = 1;
                    usernameTextInputLayout.setError("Kindly provide valid email address");
                }
                if (password.equals("")) {
                    flag2 = 1;
                    passwordTextInputLayout.setError("Kindly provide your password");
                }
                if (password.length() < 4) {
                    flag2 = 1;
                    passwordTextInputLayout.setError("Kindly provide valid password");
                }
                if (flag1 == 0 && flag2 == 0) {

                    login.setVisibility(View.GONE);
                    loginprogress.setVisibility(View.VISIBLE);

                    try {
                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] usernameBytes = username.getBytes("UTF-8");
                        byte[] passwordBytes = password.getBytes("UTF-8");

                        byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                        usernameenc = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));

                        byte[] passwordEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, passwordBytes);
                        String passwordenc = new String(SimpleBase64Encoder.encode(passwordEncryptedBytes));

                        //kun
                        byte[] demo1EncryptedBytes1 = SimpleBase64Encoder.decode(usernameenc);
                        byte[] demo1DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes1);
                        String plainusername = new String(demo1DecryptedBytes1);

                        MySharedPreferencesManager.save(LoginActivity.this, Z.USERNAME_KEY, usernameenc);
                        MySharedPreferencesManager.save(LoginActivity.this, Z.PASSWORD_KEY, passwordenc);

                        attemptLogin(usernameenc, passwordenc);
                    } catch (Exception e) {
                    }

                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Welcome.class));
            }
        });

        String newUserCheck = getIntent().getStringExtra("showOTP");
        if (newUserCheck != null && newUserCheck.equals("yes")) {
            startActivity(new Intent(LoginActivity.this, OTPActivity.class));
        } else if (MySharedPreferencesManager.getUsername(this) != null && MySharedPreferencesManager.getPassword(this) != null)
            attemptLogin(MySharedPreferencesManager.getUsername(this), MySharedPreferencesManager.getPassword(this));
    }

    void attemptLogin(String u, String p) {
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
            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", mEmail));
                params.add(new BasicNameValuePair("p", mPassword));
                json = jParser.makeHttpRequest(Z.url_login, "GET", params);
                String s = null;
                s = json.getString("info");
                Log.d("TAG", "loginActivity login task : " + s);
                resultofop = s;


                if (s.equals("student")) {

                    EmailCred = mEmail;
                    MySharedPreferencesManager.save(LoginActivity.this, "role", s);

                    return 1;
                } else if (s.equals("admin")) {

                    EmailCred = mEmail;
                    MySharedPreferencesManager.save(LoginActivity.this, "role", s);

                    return 3;
                } else if (s.equals("hr")) {

                    EmailCred = mEmail;
                    MySharedPreferencesManager.save(LoginActivity.this, "role", s);


                    return 4;
                } else if (s.equals("alumni")) {

                    EmailCred = mEmail;
                    MySharedPreferencesManager.save(LoginActivity.this, "role", s);
                    return 5;
                }
                if (s.equals("notactivated")) {

                    String role = json.getString("role");
                    Log.d("TAG", "dead");
                    MySharedPreferencesManager.save(LoginActivity.this, "role", role);
                    Log.d("TAG", "doInBackground: role : " + role);
                    EmailCred = mEmail;

                    return 6;
                }


            } catch (Exception e) {
                Log.d("exp ul", e.getMessage());
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(final Integer success) {

            processOutput(success);

        }

        void processOutput(final int success) {

            Log.d("TAG", "1+1= " + success);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (success == 1) {
                        new SaveSessionDetails().execute();
                        MySharedPreferencesManager.save(LoginActivity.this, "role", "student");
                        MySharedPreferencesManager.save(LoginActivity.this, "nameKey", EmailCred);
//                            ProfileRole r = new ProfileRole();
//                            r.setRole("student");
//                            r.setUsername(EmailCred);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else if (success == 3) {
                        new SaveSessionDetails().execute();
                        MySharedPreferencesManager.save(LoginActivity.this, "role", "admin");
                        MySharedPreferencesManager.save(LoginActivity.this, "nameKey", EmailCred);
//                            ProfileRole r = new ProfileRole();
//                            r.setRole("admin");
//                            r.setUsername(EmailCred);
                        startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                        finish();
                    } else if (success == 4) {
                        new SaveSessionDetails().execute();
                        MySharedPreferencesManager.save(LoginActivity.this, "role", "hr");
                        MySharedPreferencesManager.save(LoginActivity.this, "nameKey", EmailCred);
//                            ProfileRole r = new ProfileRole();
//                            r.setRole("hr");
//                            r.setUsername(EmailCred);
                        startActivity(new Intent(LoginActivity.this, HRActivity.class));
                        finish();
                    } else if (success == 5) {
                        new SaveSessionDetails().execute();
                        MySharedPreferencesManager.save(LoginActivity.this, "role", "alumni");
                        MySharedPreferencesManager.save(LoginActivity.this, "nameKey", EmailCred);

//                            ProfileRole r = new ProfileRole();
//                            r.setRole("alumni");
//                            r.setUsername(EmailCred);
                        startActivity(new Intent(LoginActivity.this, AlumniActivity.class));
                        finish();
                    }
                }
            }).start();

            if (success == 6) {
                Toast.makeText(LoginActivity.this, "You are already registered but not verified. Enter OTP sent on your registered email address", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, OTPActivity.class));
            } else if (resultofop.equals("notpresent")) {
                Toast.makeText(LoginActivity.this, "Incorrect email address. If you are a new user, please Sign Up.", Toast.LENGTH_LONG).show();
                passwordedittext.setText("");
            } else if (resultofop.equals("fail")) {
                Toast.makeText(LoginActivity.this, "Incorrect Password !", Toast.LENGTH_LONG).show();
                passwordedittext.setText("");
            } else {
                passwordedittext.setText("");
            }

            login.setVisibility(View.VISIBLE);
            loginprogress.setVisibility(View.GONE);
        }
    }

    class SaveSessionDetails extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String encUsername = null;
            try {
                encUsername = Z.Encrypt(username, LoginActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String r = null;
            String platform = "Android (" + getDeviceName() + ")";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));           //0
            params.add(new BasicNameValuePair("m", platform));      //1
            json = jParser.makeHttpRequest(Z.url_savesessiondetails, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
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
