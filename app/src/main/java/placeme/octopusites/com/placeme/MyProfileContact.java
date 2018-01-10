package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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


    String username,role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    int edittedFlag = 0;
    StudentData s = new StudentData();
    TextInputEditText fname, lname, email, email2, addressline1, addressline2, addressline3, phone, mobile, mobile2;
    String sfname = "", slname = "", semail2 = "", saddressline1 = "", saddressline2 = "", saddressline3 = "", sphone = "", smobile = "", smobile2 = "";
    String plainusername = "";
    String strobj="";

    TextInputLayout fnameTextInputLayout, lnameTextInputLayout,emai2input,addressline2input,addressline1input,addressline3input,phoneinput,mobileinput,mobile2input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_contact);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Contact Details");
        ab.setDisplayHomeAsUpEnabled(true);

        fname = (TextInputEditText) findViewById(R.id.fname);
        lname = (TextInputEditText) findViewById(R.id.lname);
        email = (TextInputEditText) findViewById(R.id.email);
        email2 = (TextInputEditText) findViewById(R.id.email2);
        addressline1 = (TextInputEditText) findViewById(R.id.addressline1);
        addressline2 = (TextInputEditText) findViewById(R.id.addressline2);
        addressline3 = (TextInputEditText) findViewById(R.id.addressline3);
        phone = (TextInputEditText) findViewById(R.id.phone);
        mobile = (TextInputEditText) findViewById(R.id.mobile);
        mobile2 = (TextInputEditText) findViewById(R.id.mobile2);


        fnameTextInputLayout =  (TextInputLayout) findViewById(R.id.fnameTextInputLayout);
        lnameTextInputLayout=  (TextInputLayout) findViewById(R.id.lnameTextInputLayout);
        emai2input=  (TextInputLayout) findViewById(R.id.emai2input);
        addressline2input=  (TextInputLayout) findViewById(R.id.addressline2input);
        addressline1input=  (TextInputLayout) findViewById(R.id.addressline1input);
        addressline3input=  (TextInputLayout) findViewById(R.id.addressline3input);
        phoneinput=  (TextInputLayout) findViewById(R.id.phoneinput);
        mobileinput=  (TextInputLayout) findViewById(R.id.mobileinput);
        mobile2input=  (TextInputLayout) findViewById(R.id.mobile2input);


        fnameTextInputLayout.setTypeface(Z.getLight(this));
        lnameTextInputLayout.setTypeface(Z.getLight(this));
        emai2input.setTypeface(Z.getLight(this));
        addressline2input.setTypeface(Z.getLight(this));
        addressline1input.setTypeface(Z.getLight(this));
        addressline3input.setTypeface(Z.getLight(this));
        phoneinput.setTypeface(Z.getLight(this));
        mobileinput.setTypeface(Z.getLight(this));
        mobile2input.setTypeface(Z.getLight(this));


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
                fnameTextInputLayout.setError(null);
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
                lnameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//
        email2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                emai2input .setError(null);
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
                addressline1input.setError(null);
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
                addressline2input.setError(null);
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
                addressline3input.setError(null);
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
                phoneinput.setError(null);
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
                mobileinput.setError(null);
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
                mobile2input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

//
        TextView addresstxt = (TextView) findViewById(R.id.addresstxt);
        TextView contactnotxt = (TextView) findViewById(R.id.contactnotxt);
//
        fname.setTypeface(Z.getBold(this));
        lname.setTypeface(Z.getBold(this));

        email.setTypeface(Z.getBold(this));
        email2.setTypeface(Z.getBold(this));
        addressline1.setTypeface(Z.getBold(this));
        addressline2.setTypeface(Z.getBold(this));
        addressline3.setTypeface(Z.getBold(this));
        phone.setTypeface(Z.getBold(this));
        mobile.setTypeface(Z.getBold(this));
        mobile2.setTypeface(Z.getBold(this));
        addresstxt.setTypeface(Z.getBold(this));
        contactnotxt.setTypeface(Z.getBold(this));

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
        else
            sfname="";

        if (slname != null) {
            if (slname.length() > 1) {
                lname.setText(slname);
            }
        }
        else
            slname="";

        if (smobile != null) {
            if (smobile.length() > 3) {
                mobile.setText(smobile);
            }
        }
        else
            smobile="";

        if (semail2 != null) {
            if (semail2.length() > 3) {
                email2.setText(semail2);
            }
        }
        else
            semail2="";


        if (saddressline1 != null) {
            if (saddressline1.length() > 2) {
                addressline1.setText(saddressline1);
            }
        }
        else
            saddressline1="";



        if (saddressline2 != null) {
            if (saddressline2.length() > 2) {
                addressline2.setText(saddressline2);
            }
        }
        else
            saddressline2="";

        if (saddressline3 != null) {
            if (saddressline3.length() > 2) {
                addressline3.setText(saddressline3);
            }
        }
        else
            saddressline3="";



        if (sphone != null) {
            if (sphone.length() > 9) {
                phone.setText(sphone);
            }
        }
        else
            sphone="";

        if (smobile2 != null) {
            if (smobile2.length() > 3) {
                mobile2.setText(smobile2);
            }
        }
        else
            smobile2="";

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
            fnameTextInputLayout.setError("Kindly enter valid First name");
        } else {
            errorflag = 0;

            fnameTextInputLayout.setError(null);
            if (slname.length() < 1) {
                lnameTextInputLayout.setError("Kindly enter valid last name");
                errorflag = 1;
            }
            else {
                errorflag = 0;
                lnameTextInputLayout.setError(null);
//
                if(saddressline1.length()<2){
                    errorflag = 1;
                    addressline1input.setError("Kindly enter valid Address");
                }
                else {
                    errorflag = 0;
                    addressline1input.setError(null);

                    if (saddressline2.length() < 2) {
                        errorflag = 1;
                        addressline2input.setError("Kindly enter valid Address");

                    } else {
                        errorflag = 0;
                        addressline3input.setError(null);
                        if (saddressline3.length() < 2) {
                            errorflag = 1;
                            addressline3input.setError("Kindly enter valid Address");

                        } else {
                            errorflag = 0;
                            addressline3input.setError(null);
                            if (smobile.length() < 10) {
                                errorflag = 1;
                                mobileinput.setError("Mobile Number should have 10 digits");
                            } else {
                                errorflag = 0;
                                mobileinput.setError(null);

                            }
                        }
                    }
//                    }
                }
            }
        }

        if (errorflag == 0) {
            try {
                AdminContactDetailsModal obj = new AdminContactDetailsModal(sfname, slname, plainusername, semail2, saddressline1, saddressline2, saddressline3, sphone, smobile, smobile2);

                strobj = OtoString(obj, digest1, digest2);
                new SaveDetails().execute();

            } catch (Exception e) {
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:
                if (edittedFlag == 1) {
                    validateandSave();
                }
                else {
                    onBackPressed();
                }
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileContact.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileContact.this));

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

            if (role.equals("hr"))
                json = jParser.makeHttpRequest(Z.url_SaveHrContact, "GET", params);

            else if (role.equals("admin"))
                json = jParser.makeHttpRequest(Z.url_SaveAdminContact, "GET", params);

            else
                json = jParser.makeHttpRequest(Z.url_SaveStdalmContact, "GET", params);

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
                Toast.makeText(MyProfileContact.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();

                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("hr"))
                    setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
                else  {
                    setResult(AdminActivity.ADMIN_DATA_CHANGE_RESULT_CODE);
                }
                MyProfileContact.super.onBackPressed();

            } else
            Toast.makeText(MyProfileContact.this, "Try again !", Toast.LENGTH_SHORT).show();


        }
    }
}
