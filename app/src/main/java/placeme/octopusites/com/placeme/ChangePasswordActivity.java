package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.Z.md5;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputEditText currentedittext,newpassedittetx,newpassaedittext;
    String username,currentpass,newpass,newpassa,resultofop="";
    String enccurrentpass,encnewpass;
    JSONObject json;
    JSONParser jParser = new JSONParser();

    ProgressBar progressBar;
    Button changepassbutton;
    String digest1,digest2;
    TextInputLayout currentinput,newinput,newainput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Change Password");
        ab.setDisplayHomeAsUpEnabled(true);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        String role=MySharedPreferencesManager.getRole(this);

        currentedittext=(TextInputEditText)findViewById(R.id.current_password);
        newpassedittetx=(TextInputEditText)findViewById(R.id.new_password);
        newpassaedittext=(TextInputEditText)findViewById(R.id.new_password_again);

        currentedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentedittext.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        newpassedittetx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newpassedittetx.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        newpassaedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newpassaedittext.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextView forgotpassword=(TextView)findViewById(R.id.forgot);
        forgotpassword.setTypeface(Z.getBold(this));
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangePasswordActivity.this,ForgotPasswordDialog.class));
            }
        });

        TextView createpasstxt=(TextView)findViewById(R.id.createpasstxt);
        TextView passsenstxt=(TextView)findViewById(R.id.passsenstxt);
        currentinput=(TextInputLayout)findViewById(R.id.currentinput);
        newinput=(TextInputLayout)findViewById(R.id.newinput);
        newainput=(TextInputLayout)findViewById(R.id.newainput);
        TextInputEditText current_password=(TextInputEditText)findViewById(R.id.current_password);
        TextInputEditText new_password=(TextInputEditText)findViewById(R.id.new_password);
        TextInputEditText new_password_again=(TextInputEditText)findViewById(R.id.new_password_again);
        progressBar=(ProgressBar)findViewById(R.id.changepassprogress);
        changepassbutton=(Button)findViewById(R.id.change_password_button);

        changepassbutton.setTypeface(Z.getBold(this));
        createpasstxt.setTypeface(Z.getBold(this));
        passsenstxt.setTypeface(Z.getLight(this));
        currentinput.setTypeface(Z.getLight(this));
        newinput.setTypeface(Z.getLight(this));
        newainput.setTypeface(Z.getLight(this));
        current_password.setTypeface(Z.getBold(this));
        new_password.setTypeface(Z.getBold(this));
        new_password_again.setTypeface(Z.getBold(this));


        changepassbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    currentinput.setError(null);
                    newinput.setError(null);
                    newainput.setError(null);

                    currentpass=currentedittext.getText().toString();
                    newpass=newpassedittetx.getText().toString();
                    newpassa=newpassaedittext.getText().toString();
                    int flag1=0,flag2=0,flag3=0;
                    if(currentpass.length()<6)
                    {
                        flag1=1;
                        currentinput.setError("Kindly enter valid password");
                    }
                    else if(newpass.length()<6)
                    {
                        flag2=1;
                        newinput.setError("New password must be of at least 6 characters");
                    }
                    else if(newpassa.length()<6)
                    {
                        flag3=1;
                        newainput.setError("New password must be of at least 6 characters");
                    }
                    if(flag1==0&&flag2==0&&flag3==0)
                    {
                        if(newpass.equals(newpassa))
                        {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                            progressBar.setVisibility(View.VISIBLE);
                            changepassbutton.setVisibility(View.GONE);

                            try
                            {

                                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                                String sPadding = "ISO10126Padding";

                                byte[] currentpassBytes = currentpass.getBytes("UTF-8");
                                byte[] newpassBytes = newpass.getBytes("UTF-8");

                                byte[] currentpassEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, currentpassBytes);
                                enccurrentpass=new String(SimpleBase64Encoder.encode(currentpassEncryptedBytes));

                                byte[] newpassEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, newpassBytes);
                                encnewpass=new String(SimpleBase64Encoder.encode(newpassEncryptedBytes));

                                new ChangePassTask().execute();
                            }catch (Exception e){Toast.makeText(ChangePasswordActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();}


                        }
                        else
                        {
                            Toast.makeText(ChangePasswordActivity.this,"Passwords didn't match !",Toast.LENGTH_LONG).show();
                        }
                    }

                }catch (Exception e){
                    Toast.makeText(ChangePasswordActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
    class ChangePassTask extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            params.add(new BasicNameValuePair("p", enccurrentpass));
            params.add(new BasicNameValuePair("a", encnewpass));

            json = jParser.makeHttpRequest(Z.url_changepass, "GET", params);
            Log.d("TAG", "ChangePassTask: " + json);
            try {
                resultofop = json.getString("info");
                json.put("chge pass json : ",json);

            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if(resultofop.equals("success")) {

                String hashnew = null, hashOld = null, firebaseUsername = null;
                try {
                    String data = Z.Decrypt(encnewpass, ChangePasswordActivity.this);
                    hashnew = md5(data + MySharedPreferencesManager.getDigest3(ChangePasswordActivity.this));
                    firebaseUsername = Z.Decrypt(MySharedPreferencesManager.getUsername(ChangePasswordActivity.this), ChangePasswordActivity.this);
                    String passOld = Z.Decrypt(MySharedPreferencesManager.getPassword(ChangePasswordActivity.this), ChangePasswordActivity.this);
                    hashOld = md5(passOld + MySharedPreferencesManager.getDigest3(ChangePasswordActivity.this));
//                    Log.d("kkk", "firebaseUsernameOld: pass "+firebaseUsername);
//                    Log.d("kkk", "onPostExecute: pass old "+passOld);
//                    Log.d("kkk", "onPostExecute: pass new "+data);


                    // firebase change password
                    final String hash = hashnew;
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                Log.d("TAG", "onPostExecute: firebaseUsernameOld "+firebaseUsername);
//                Log.d("TAG", "onPostExecute: hashOld "+hashOld);
                    AuthCredential credential = EmailAuthProvider.getCredential(firebaseUsername, hashOld);
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        user.updatePassword(hash).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d("kkk", "FIFA updated");
                                                } else {
                                                    Log.d("kkk", "Error FIFA not updated");
                                                }
                                            }
                                        });
                                    } else {
                                        Log.d("kkk", "Error auth failed");
                                    }
                                }
                            });

                    //
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("TAg", "fire log: " + e.getMessage());
                }



                MySharedPreferencesManager.save(ChangePasswordActivity.this,"passKey", encnewpass);
                Toast.makeText(ChangePasswordActivity.this, "Successfully Updated..!", Toast.LENGTH_SHORT).show();
                ChangePasswordActivity.super.onBackPressed();
            }
            else if(resultofop.equals("wrong"))
                Toast.makeText(ChangePasswordActivity.this,"Wrong Current Password..!",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(ChangePasswordActivity.this,"Failed..!",Toast.LENGTH_SHORT).show();


            currentedittext.setText("");
            newpassedittetx.setText("");
            newpassaedittext.setText("");
            changepassbutton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }


}
