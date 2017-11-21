package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class WelcomeIntroActivity extends AppCompatActivity {

    JSONParser jParser = new JSONParser();
    JSONObject json;
    EditText fnameEditText,lnameEditText,mobileEditText;
    Button nextButton;
    Boolean errorFlag=false;
    String fname,lname,mobile;
    String encUsersName,encfname,enclname,encmobile;
    String digest1, digest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_intro);

        fnameEditText= (EditText) findViewById(R.id.fname);
        lnameEditText= (EditText) findViewById(R.id.lname);
        mobileEditText= (EditText) findViewById(R.id.mobile);
        nextButton= (Button) findViewById(R.id.nextButton);

        encUsersName=MySharedPreferencesManager.getUsername(WelcomeIntroActivity.this);
        digest1 = MySharedPreferencesManager.getDigest1(WelcomeIntroActivity.this);
        digest2 = MySharedPreferencesManager.getDigest2(WelcomeIntroActivity.this);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname=fnameEditText.getText().toString().trim();
                lname=lnameEditText.getText().toString().trim();
                mobile=mobileEditText.getText().toString().trim();

                if(fname.length()<1){
                    errorFlag=true;
                    fnameEditText.setError("Enter minimum 2 characters");
                }else if(fname.length()<1){
                    lnameEditText.setError("Enter minimum 2 characters");
                    errorFlag=true;
                }else if(mobile.length()!=10){
                    mobileEditText.setError("Incorrect mobile number");
                }


                if(!errorFlag){

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

                        MySharedPreferencesManager.save(WelcomeIntroActivity.this,"fname",encfname);
                        MySharedPreferencesManager.save(WelcomeIntroActivity.this,"lname",enclname);
                        MySharedPreferencesManager.save(WelcomeIntroActivity.this,"mobile",encmobile);



                    } catch (Exception e) {
                    }
                }

            }
        });

    }

}
