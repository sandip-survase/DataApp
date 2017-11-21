package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.AdminContactDetailsModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;

public class MyProfileContact extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    private static String url_savedata = "http://192.168.100.30:8080/ProfileObjects/SaveAdminContact";
    SharedPreferences sharedpreferences;
    String username;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    int edittedFlag = 0;
    StudentData s = new StudentData();
    EditText fname, lname, email, email2, addressline1, addressline2, addressline3, phone, mobile, mobile2;
    String sfname = "", slname = "", semail2 = "", saddressline1 = "", saddressline2 = "", saddressline3 = "", sphone = "", smobile = "", smobile2 = "";
    String encfname, enclname, encemail2, encaddressline1, encaddressline2, encaddressline3, encphone, encmobile, encmobile2;
    String plainusername = "";
    String strobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_contact);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Contact Details");
        ab.setDisplayHomeAsUpEnabled(true);

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        email2 = (EditText) findViewById(R.id.email2);
        addressline1 = (EditText) findViewById(R.id.addressline1);
        addressline2 = (EditText) findViewById(R.id.addressline2);
        addressline3 = (EditText) findViewById(R.id.addressline3);
        phone = (EditText) findViewById(R.id.phone);
        mobile = (EditText) findViewById(R.id.mobile);
        mobile2 = (EditText) findViewById(R.id.mobile2);

        email.setFocusable(false);
        email.setFocusableInTouchMode(false);
        email.setClickable(false);

        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                fname.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                lname.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                email.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                email2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addressline1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                addressline1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addressline2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                addressline2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addressline3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                addressline3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                phone.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                mobile.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mobile2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                mobile2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        TextView addresstxt = (TextView) findViewById(R.id.addresstxt);
        TextView contactnotxt = (TextView) findViewById(R.id.contactnotxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/arba.ttf");
        addresstxt.setTypeface(custom_font1);
        contactnotxt.setTypeface(custom_font1);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = MySharedPreferencesManager.getUsername(this);
        String role = sharedpreferences.getString("role", null);

        ProfileRole r = new ProfileRole();
        r.setUsername(username);
        r.setRole(role);

        Digest d = new Digest();
        digest1 = d.getDigest1();
        digest2 = d.getDigest2();

        if (digest1 == null || digest2 == null) {
            digest1 = sharedpreferences.getString("digest1", null);
            digest2 = sharedpreferences.getString("digest2", null);
            d.setDigest1(digest1);
            d.setDigest2(digest2);
        }

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";


        try {
            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(username);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);
            email.setText(plainusername);
        } catch (Exception e) {
        }


        sfname = s.getFname();
        slname = s.getLname();
        smobile = s.getPhone();
        semail2 = s.getEmail2();
        saddressline1 = s.getAddressline1();
        saddressline2 = s.getAddressline2();
        saddressline3 = s.getAddressline3();
        sphone = s.getTelephone();
        smobile2 = s.getMobile2();

        if (sfname != null) {
            if (sfname.length() > 1) {
                fname.setText(sfname);
            }
        }
        if (slname != null) {
            if (slname.length() > 1) {
                lname.setText(slname);
            }
        }
        if (smobile != null) {
            if (smobile.length() > 3) {
                mobile.setText(smobile);
            }
        }
        if (semail2 != null) {
            if (semail2.length() > 3) {
                email2.setText(semail2);
            }
        }
        if (saddressline1 != null) {
            if (saddressline1.length() > 3) {
                addressline1.setText(saddressline1);
            }
        }
        if (saddressline2 != null) {
            if (saddressline2.length() > 3) {
                addressline2.setText(saddressline2);
            }
        }
        if (saddressline3 != null) {
            if (saddressline3.length() > 3) {
                addressline3.setText(saddressline3);
            }
        }
        if (sphone != null) {
            if (sphone.length() > 3) {
                phone.setText(sphone);
            }
        }
        if (smobile2 != null) {
            if (smobile2.length() > 3) {
                mobile2.setText(smobile2);
            }
        }
        edittedFlag = 0;
    }

    void validateandSave() {


        sfname = fname.getText().toString();
        slname = lname.getText().toString();
        semail2 = email2.getText().toString();
        saddressline1 = addressline1.getText().toString();
        saddressline2 = addressline2.getText().toString();
        saddressline3 = addressline3.getText().toString();
        sphone = phone.getText().toString();
        smobile = mobile.getText().toString();
        smobile2 = mobile2.getText().toString();

        int errorflag = 0;

        if (sfname.length() < 2) {
            errorflag = 1;
            fname.setError("Invalid Name");
        } else {
            errorflag = 0;
            if (smobile.length() < 10 || smobile.length() > 10) {
                errorflag = 1;
                mobile.setError("Mobile Number should have 10 digits");
            } else {
                if (plainusername.equals(semail2)) {
                    errorflag = 1;
                    email2.setError("Priamry and Alternate Email cannot be same");
                }
            }
        }

        if (errorflag == 0) {
            try {
                AdminContactDetailsModal obj = new AdminContactDetailsModal(sfname, slname, plainusername, semail2, saddressline1, saddressline2, saddressline3, sphone, smobile, smobile2);
                try {
                    strobj = OtoString(obj, digest1, digest2);
                    Log.d("encstrobj", "strobj: " + strobj);

                } catch (Exception e) {
                    Log.d("TAG", "validateandSave: - " + e.getMessage());
                }
                new SaveDetails().execute();

            } catch (Exception e) {
                Toast.makeText(MyProfileContact.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                validateandSave();
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

    @Override
    public void onBackPressed() {

        if (edittedFlag != 0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfileContact.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
                }
            });

            alertDialog.show();
        } else
            MyProfileContact.super.onBackPressed();
    }

    class SaveDetails extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("obj", strobj));               //1

            json = jParser.makeHttpRequest(url_savedata, "GET", params);
            try {

                r = json.getString("info");
                Log.d("reverse check", ": info" + json.getString("info"));

                Log.d("reverse check", ": fnameToreplace" + json.getString("fnameToreplace"));
                Log.d("reverse check", ":lnametoreplace" + json.getString("lnametoreplace"));
                Log.d("reverse check", ": entry" + json.getString("entry"));
                Log.d("reverse check", ": info1" + json.getString("info1"));
                Log.d("reverse check", ": introobj" + json.getString("introobj"));
                Log.d("reverse check", ": updatedobj" + json.getString("updatedobj"));
                Log.d("reverse check", ": updatedobj" + json.getString("updatedobj"));
                Log.d("reverse check", ": info" + json.getString("info"));


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

//            if(result.equals("success"))
//            {
//                Toast.makeText(MyProfileContact.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();
//
//                if(edittedFlag!=0){
//                    setResult(111);
//                }
//                MyProfileContact.super.onBackPressed();
//            }
//            else
//                Toast.makeText(MyProfileContact.this,result,Toast.LENGTH_SHORT).show();

        }
    }
}
