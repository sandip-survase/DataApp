package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.MyProfileFragment.instcode;

public class CreateMultipleAdmin extends AppCompatActivity {
    TextInputEditText email;
    TextInputLayout adduserinput;
    String username = "", userEmail, plainUsername = "";
    private String digest1, digest2;
    String encadminUsername;
    String  encuserEmail;

    String encUsername = "";
    String plainFilename = "";
    int edittedFlag = 0;


    JSONObject json;
    JSONParser jParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_multiple_admin);

        email = (TextInputEditText) findViewById(R.id.email);
        adduserinput = (TextInputLayout) findViewById(R.id.adduserinput);

        adduserinput.setTypeface(Z.getLight(this));
        email.setTypeface(Z.getBold(this));

        encadminUsername = MySharedPreferencesManager.getUsername(CreateMultipleAdmin.this);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Create New Admin");
        ab.setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        email.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adduserinput.setError(null);
                 edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:
                if(Z.isAdminHrVerified(CreateMultipleAdmin.this)) {
                    validate();
                }
                else
                    Toast.makeText(CreateMultipleAdmin.this, "Your account is still not verified. Please wait while we are verifying your account as TPO & you will get a notification after successful Verification ", Toast.LENGTH_LONG).show();

                break;

            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);


        return super.onCreateOptionsMenu(menu);

    }

    private void validate() {

            userEmail = email.getText().toString();


        try {
            Log.d("TAG", "doInBackground: encadminUsername "+Z.Decrypt(encadminUsername,CreateMultipleAdmin.this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("TAG", "doInBackground: encuserEmail "+userEmail);
            if (userEmail != null && !userEmail.equals("")) {

                if (userEmail.contains("@")  && userEmail.contains(".edu")) {

                    try {
                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] userEmailBytes = userEmail.getBytes("UTF-8");

                        byte[] userEmailEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, userEmailBytes);
                        encuserEmail = new String(SimpleBase64Encoder.encode(userEmailEncryptedBytes));

                        new CreateAdmin().execute();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("TAG", "baby:" + e.getMessage());
                    }
                } else
                    adduserinput.setError("Kindly enter valid email address (must contain .edu)");
            } else
                adduserinput.setError("Kindly enter valid email address (must contain .edu)");
    }

    class CreateAdmin extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            Log.d("TAG", "doInBackground: encadminUsername "+encadminUsername);
            Log.d("TAG", "doInBackground: encuserEmail "+encuserEmail);
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encadminUsername));    //0
            params.add(new BasicNameValuePair("ue", encuserEmail));//1
//            params.add(new BasicNameValuePair("uc", instcode));//1

            json = jParser.makeHttpRequest(Z.url_createSingleAdmin, "GET", params);
            Log.d("TAG", "doInBackground: json - "+json);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("TAG", "onPostExecute: result " + result);

            if (result.equals("success")) {
                edittedFlag = 0;
                Toast.makeText(CreateMultipleAdmin.this, "Admin successfully Created!", Toast.LENGTH_SHORT).show();
                setResult(Z.USER_DATA_CHANGE_RESULT_CODE);

            } else if (result.equals("userExist")) {
                edittedFlag = 0;
                Toast.makeText(CreateMultipleAdmin.this, "Admin already exist on Placeme", Toast.LENGTH_LONG).show();
            } else if (result.equals("Missing domain")) {
                Toast.makeText(CreateMultipleAdmin.this, "Kindly check email Address", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CreateMultipleAdmin.this, "Fail to create user", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    public void onBackPressed() {

        if (edittedFlag == 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    CreateMultipleAdmin.super.onBackPressed();
                                }
                            })

                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(CreateMultipleAdmin.this));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(CreateMultipleAdmin.this));
                }
            });

            alertDialog.show();
        } else
            CreateMultipleAdmin.super.onBackPressed();
    }


}
