package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.Z.md5;

public class OTPActivity extends AppCompatActivity {

    TextInputEditText otpedittext;
    TextView entertxt, otptxt, resend;
    Button verify;
    String enteredOTP, encOTP;
    String encUsername, encpassword;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    boolean activationMessageflag = false;
    String resultofop = "";
    TextView resendotp;
    ProgressBar otpprogress;

    String digest1, digest2;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";
    String plainusername, hash;
    ImageView otpClose;
    TextInputLayout otplayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        setFinishOnTouchOutside(false);

        otplayout = (TextInputLayout) findViewById(R.id.otplayout);
        otpedittext = (TextInputEditText) findViewById(R.id.otp);
        otpClose = (ImageView) findViewById(R.id.otpClose);
        verify = (Button) findViewById(R.id.submitotp);

        otpprogress = (ProgressBar) findViewById(R.id.otpprogress);

        otpClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OTPActivity.this);

                alertDialogBuilder
                        .setMessage("Your sign up data will get lost. Do you want to continue?")
                        .setCancelable(false)
                        .setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                })

                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
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
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(OTPActivity.this));
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(OTPActivity.this));
                    }
                });

                alertDialog.show();

            }
        });

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);


        entertxt = (TextView) findViewById(R.id.entertxt);
        otptxt = (TextView) findViewById(R.id.otptxt);
        resend = (TextView) findViewById(R.id.resend);

        String activationMessage = MySharedPreferencesManager.getData(OTPActivity.this, "activationMessage");

        if (activationMessage != null && activationMessage.equals("yes")) {
            activationMessageflag = true;
            entertxt.setText("Enter Activation Code");
            otplayout.setHint("Activation Code");
            otpedittext.setHint("Activation Code");
            otptxt.setText("Enter Activation code sent on your professional email..!");
            resend.setText("Resend Code");
        }
        Log.d("TAG", "papoose" + activationMessageflag);

        resendotp = (TextView) findViewById(R.id.resend);
        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendotp.setVisibility(View.GONE);
                Toast.makeText(OTPActivity.this, "" + MySharedPreferencesManager.getRole(OTPActivity.this), Toast.LENGTH_SHORT).show();
                new ResendOTP().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

        encUsername = MySharedPreferencesManager.getUsername(this);
        encpassword = MySharedPreferencesManager.getPassword(this);

        try {
            demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            demoIVBytes = SimpleBase64Encoder.decode(digest2);
            sPadding = "ISO10126Padding";

            byte[] demo1EncryptedBytes1 = SimpleBase64Encoder.decode(encUsername);
            byte[] demo1DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes1);
            plainusername = new String(demo1DecryptedBytes1);

            byte[] demo2EncryptedBytes1 = SimpleBase64Encoder.decode(encpassword);
            byte[] demo2DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo2EncryptedBytes1);
            String data = new String(demo2DecryptedBytes1);
            hash = md5(data + MySharedPreferencesManager.getDigest3(OTPActivity.this));

        } catch (Exception e) {
        }

        MySharedPreferencesManager.save(this, "otp", "yes");

        entertxt.setTypeface(Z.getBold(this));
        otptxt.setTypeface(Z.getLight(this));
        resendotp.setTypeface(Z.getBold(this));
        otplayout.setTypeface(Z.getLight(this));
        otpedittext.setTypeface(Z.getBold(this));
        verify.setTypeface(Z.getBold(this));


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enteredOTP = otpedittext.getText().toString();
                if (enteredOTP.length() < 6)
                    otplayout.setError("Kindly enter valid OTP");
                else {
                    verify.setVisibility(View.GONE);
                    otpprogress.setVisibility(View.VISIBLE);

                    try {
                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] otpBytes = enteredOTP.getBytes("UTF-8");

                        byte[] otpEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, otpBytes);
                        encOTP = new String(SimpleBase64Encoder.encode(otpEncryptedBytes));

                    } catch (Exception e) {
                    }

                    new VerifyOTP().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        });


        otpedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otplayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    class VerifyOTP extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String encUsernameVerify = encUsername;

            if (activationMessageflag == true) {
                encUsernameVerify = MySharedPreferencesManager.getData(OTPActivity.this, "proEmail");
            }
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ud", encUsernameVerify));
            params.add(new BasicNameValuePair("eo", encOTP));
            json = jParser.makeHttpRequest(Z.url_verifyotp, "GET", params);

            try {
                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "wife tp " + e.getMessage());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            new ClearOTPTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            if (resultofop.equals("success") && activationMessageflag == false) {

                    Log.d("TAG", "por bala" + activationMessageflag);

                MySharedPreferencesManager.save(OTPActivity.this, Z.USERNAME_KEY, encUsername);
                MySharedPreferencesManager.save(OTPActivity.this, Z.PASSWORD_KEY, encpassword);
                MySharedPreferencesManager.save(OTPActivity.this, "otp", "no");

                    String role = MySharedPreferencesManager.getRole(OTPActivity.this);
                    String u = MySharedPreferencesManager.getUsername(OTPActivity.this);
                    String p = MySharedPreferencesManager.getPassword(OTPActivity.this);

//                    Toast.makeText(OTPActivity.this, "Successfully Registered..!", Toast.LENGTH_LONG).show();

                    if (role.equals("student")) {
                        new CreateFirebaseUser(u, p).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        new AddStudentUnderAdmin().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        startActivity(new Intent(OTPActivity.this, MainActivity.class));
                        finish();
                    } else if (role.equals("admin")) {
                        startActivity(new Intent(OTPActivity.this, AdminActivity.class));
                        finish();
                    } else if (role.equals("alumni")) {
                        new CreateFirebaseUser(u, p).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        new AddStudentUnderAdmin().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        startActivity(new Intent(OTPActivity.this, AlumniActivity.class));
                        finish();
                    } else if (role.equals("hr")) {
                        startActivity(new Intent(OTPActivity.this, HRActivity.class));
                        finish();
                    }

//              }    else if (resultofop.equals("fail"))
//                    Toast.makeText(OTPActivity.this, "Incorrect OTP..!", Toast.LENGTH_LONG).show();
//                else if (resultofop.equals("expired"))
//                    Toast.makeText(OTPActivity.this, "OTP expired.Please request OTP again.", Toast.LENGTH_LONG).show();
//                else if (resultofop.equals("already")) {
//
//                }
//                else
//                    Toast.makeText(OTPActivity.this, resultofop, Toast.LENGTH_LONG).show();
            } else if (resultofop.equals("success") && activationMessageflag == true) {
                Log.d("TAG", "por bala" + activationMessageflag);
                String role = MySharedPreferencesManager.getRole(OTPActivity.this);
                String u = MySharedPreferencesManager.getUsername(OTPActivity.this);
                String p = MySharedPreferencesManager.getPassword(OTPActivity.this);

                new CreateFirebaseUser(u, p).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                startActivity(new Intent(OTPActivity.this, WelcomeGenrateCodeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            } else if (resultofop.equals("fail")) {
                otplayout.setError("Incorrect OTP");
//                Toast.makeText(OTPActivity.this, "Incorrect OTP..!", Toast.LENGTH_LONG).show();
            } else if (resultofop.equals("expired"))
                otplayout.setError("OTP expired.Please request OTP again.");
//                Toast.makeText(OTPActivity.this, "OTP expired.Please request OTP again.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(OTPActivity.this, "Try again", Toast.LENGTH_SHORT).show();


            verify.setVisibility(View.VISIBLE);
            otpprogress.setVisibility(View.GONE);

        }

    }

    void loginFirebase(String username, String hash) {

        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username, hash);
//                .addOnCompleteListener(OTPActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//
//                        if (task.isSuccessful()) {
//                            Toast.makeText(OTPActivity.this, "Successfully logged in to Firebase", Toast.LENGTH_SHORT).show();
//
//
//                        } else {
//                            Toast.makeText(OTPActivity.this, "Failed to login to Firebase", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
    }

    class ResendOTP extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {

            String encUsernameResendOTP = encUsername;

            if (activationMessageflag == true) {
                encUsernameResendOTP = MySharedPreferencesManager.getData(OTPActivity.this, "proEmail");
            }

            String encfname = MySharedPreferencesManager.getData(OTPActivity.this, "fname");
            String enclname = MySharedPreferencesManager.getData(OTPActivity.this, "lname");

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ud", encUsernameResendOTP));
            params.add(new BasicNameValuePair("f", encfname));
            params.add(new BasicNameValuePair("l", enclname));
            json = jParser.makeHttpRequest(Z.url_resendotp, "GET", params);
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
                Toast.makeText(OTPActivity.this, "New OTP has been sent to your Email.!", Toast.LENGTH_LONG).show();
            }

            resendotp.setVisibility(View.VISIBLE);

        }
    }

    class ClearOTPTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            jParser.makeHttpRequest(Z.url_ClearOTP, "GET", params);
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }

    class AddStudentUnderAdmin extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ud", encUsername));

            json = jParser.makeHttpRequest(Z.url_AddStudentUnderAdmin, "GET", params);
            try {
                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }

    class CreateFirebaseUser extends AsyncTask<String, String, String> {

        String u, p;

        CreateFirebaseUser(String u, String p) {
            this.u = u;
            this.p = p;
        }

        protected String doInBackground(String... param) {


            String fname = MySharedPreferencesManager.getData(OTPActivity.this, "fname");
            String lname = MySharedPreferencesManager.getData(OTPActivity.this, "lname");
            String encfullName = fname + " " + lname;
            String encPhone = MySharedPreferencesManager.getData(OTPActivity.this, "phone");

            if (encfullName == null)
                encfullName = "Name_Not_Found_From_shared";
            if (encPhone == null)
                encPhone = "Phone_Not_Found_From_shared";

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", u));
            params.add(new BasicNameValuePair("p", p));
            params.add(new BasicNameValuePair("t", new SharedPrefUtil(getApplicationContext()).getString("firebaseToken"))); //5
            json = jParser.makeHttpRequest(Z.url_create_firebase, "GET", params);
            try {
                resultofop = json.getString("info");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {
            loginFirebase(plainusername, hash);
        }
    }

    @Override
    public void onBackPressed() {

        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }


}
