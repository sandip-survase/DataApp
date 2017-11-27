package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.AdminContactDetailsModal;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class AlumniContactDetails extends AppCompatActivity {


    EditText fname, lname, email, email2, addressline1, addressline2, addressline3, phone, mobile, mobile2;

    String username;
    String digest1, digest2;

    String hrfname = "", hrlname = "", hremail2 = "", hraddressline1 = "", hraddressline2 = "", hraddressline3 = "", hrphone = "", hrmobile = "", hrmobile2 = "";
    String encfname,enclname,encemail2,encaddressline1,encaddressline2,encaddressline3,encphone,encmobile,encmobile2;
    String plainusername="",role="";
    String strobj;

    int edittedFlag=0;

    JSONParser jParser = new JSONParser();
    JSONObject json;

//    HrData hrData = new HrData();
//    AlumniData alumniData=new AlumniData();

    StudentData s=new StudentData();
    private static String url_savedata= "http://192.168.100.30:8080/ProfileObjects/SaveAdminContact";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_contact_details);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Contact Details");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);



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


        TextView addresstxt = (TextView) findViewById(R.id.addresstxt);
        TextView contactnotxt = (TextView) findViewById(R.id.contactnotxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/arba.ttf");
        addresstxt.setTypeface(custom_font1);
        contactnotxt.setTypeface(custom_font1);


        digest1 =MySharedPreferencesManager.getDigest1(this);
        digest2 =MySharedPreferencesManager.getDigest2(this);
        username =MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);


        try {
            plainusername=Decrypt(username,digest1,digest2);
            email.setText(plainusername);

        } catch (Exception e) {
        }


        hrfname = s.getFname();
        hrlname = s.getLname();
        hrmobile = s.getPhone();
        hremail2 = s.getEmail2();
        hraddressline1 = s.getAddressline1();
        hraddressline2 = s.getAddressline2();
        hraddressline3 = s.getAddressline3();
        hrphone = s.getTelephone();
        hrmobile2 = s.getMobile2();

        if(hrfname!=null) {
            if (hrfname.length() > 1) {
                fname.setText(hrfname);
            }
        }
        if(hrlname!=null) {
            if (hrlname.length() > 1) {
                lname.setText(hrlname);
            }
        }
        if(hrmobile!=null) {   // main number
            if (hrmobile.length() > 3) {
                mobile.setText(hrmobile);
            }
        }
        if(hremail2!=null) {
            if (hremail2.length() > 3) {

                email2.setText(hremail2);
            }
        }
        if(hraddressline1!=null) {
            if (hraddressline1.length() > 1) {
                addressline1.setText(hraddressline1);
            }
        }
        if(hraddressline2!=null) {
            if (hraddressline2.length() > 1) {
                addressline2.setText(hraddressline2);
            }
        }
        if(hraddressline3!=null) {
            if (hraddressline3.length() > 1) {
                addressline3.setText(hraddressline3);
            }
        }
        if(hrphone!=null) {
            if (hrphone.length() > 3) {
                phone.setText(hrphone);
            }
        }
        if(hrmobile2!=null) {
            if (hrmobile2.length() > 3) {
                mobile2.setText(hrmobile2);
            }
        }

//
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


    }


    void validateandSave() {

        hrfname = fname.getText().toString();
        hrlname = lname.getText().toString();
        hremail2 = email2.getText().toString();
        hraddressline1 = addressline1.getText().toString();
        hraddressline2 = addressline2.getText().toString();
        hraddressline3 = addressline3.getText().toString();
        hrphone = phone.getText().toString();
        hrmobile = mobile.getText().toString();
        hrmobile2 = mobile2.getText().toString();

        int errorflag = 0;

        if (hrfname.length() < 2) {
            errorflag = 1;
            fname.setError("Invalid Name");
        } else {
            if (plainusername.equals(hremail2)) {
                errorflag = 1;
                email2.setError("Priamry and Alternate Email cannot be same");
            } else {
                if (!email2.getText().toString().contains("@")) {
                    errorflag = 1;
                    email2.setError("Incorrect Email");
                } else {
                    if (hraddressline1.length() < 1) {
                        errorflag = 1;
                        addressline1.setError("Enter valid address");
                    } else {
                        if (hraddressline2.length() < 1) {
                            errorflag = 1;
                            addressline2.setError("Enter valid address");
                        } else {
                            if (hraddressline3.length() < 1) {
                                errorflag = 1;
                                addressline3.setError("Enter valid address");
                            } else {
                                if (hrphone.length() < 6) {
                                    phone.setError("Incorrect phone number ");
                                    errorflag = 1;
                                } else {
                                    if (hrmobile.length() < 10) {
                                        errorflag = 1;
                                        mobile.setError("Mobile Number should have 10 digits");
                                    } else {
                                        if (hrmobile2.length() < 10) {
                                            mobile2.setError("Incorrect phone number ");
                                            errorflag = 1;
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }

        }
        if (errorflag == 0) {
            try {
                AdminContactDetailsModal obj = new AdminContactDetailsModal(hrfname, hrlname, plainusername, hremail2, hraddressline1, hraddressline2, hraddressline3, hrphone, hrmobile, hrmobile2);
                try {
                    strobj = OtoString(obj, digest1, digest2);
                    Log.d("encstrobj", "strobj: " + strobj);

                } catch (Exception e) {
                    Log.d("TAG", "validateandSave: - " + e.getMessage());
                }
                new SaveDetails().execute();
            } catch (Exception e) {
                Toast.makeText(AlumniContactDetails.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }
    class SaveDetails extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("obj",strobj));               //1

            json = jParser.makeHttpRequest(url_savedata, "GET", params);
            try {
                r = json.getString("info");

                Log.d("reverse check", ": fnameToreplace"+json.getString("fnameToreplace"));
                Log.d("reverse check", ":lnametoreplace"+json.getString("lnametoreplace"));
                Log.d("reverse check", ": entry"+json.getString("entry"));
                Log.d("reverse check", ": info1"+json.getString("info1"));
                Log.d("reverse check", ": introobj"+json.getString("introobj"));
                Log.d("reverse check", ": updatedobj"+json.getString("updatedobj"));
                Log.d("reverse check", ": updatedobj"+json.getString("updatedobj"));
                Log.d("reverse check", ": info"+json.getString("info"));







            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(AlumniContactDetails.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();

                if(edittedFlag!=0){
                    setResult(111);
                }

                AlumniContactDetails.super.onBackPressed();
            }
            else
                Toast.makeText(AlumniContactDetails.this,result,Toast.LENGTH_SHORT).show();

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
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public void onBackPressed() {
        if(edittedFlag==1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AlumniContactDetails.super.onBackPressed();
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
        }else
            AlumniContactDetails.super.onBackPressed();
    }
}
