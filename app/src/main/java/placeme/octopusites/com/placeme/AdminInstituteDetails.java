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

import placeme.octopusites.com.placeme.modal.AdminInstituteModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;


public class AdminInstituteDetails extends AppCompatActivity {

    //sss
    TextInputEditText iname, iemail, iweb, iphone, ialtphone, uniname, caddrline1, caddrline2, caddrline3, ireg;
    String instname = "", instemail = "", instweb = "", instphone = "", instaltrphone = "", universityname = "", instreg = "", instcaddrline1 = "", instcaddrline2 = "", instcaddrline3 = "";
    String encUsername, enciname, encinstemail, encinstweb, encinstphone, encinstaltrphone, encuniversityname, encCinstreg;
    TextInputLayout instnameinput, instemailinput, instwebinput, instphoneinput, instphoneainput, instuniversityinput, caddrline1input, caddrline2input, caddrline3input, instreginput;
    int edittedFlag = 0;
    TextView loctxt;
    String digest1, digest2;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    AdminData a = new AdminData();
    //
    AdminInstituteModal obj;
    String strobj = "", encobj;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_institute_details);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username = MySharedPreferencesManager.getUsername(this);
        String role = MySharedPreferencesManager.getRole(this);
        encUsername = username;

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Institute Details");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        instnameinput = (TextInputLayout) findViewById(R.id.instnameinput);
        instemailinput = (TextInputLayout) findViewById(R.id.instemailinput);
        instwebinput = (TextInputLayout) findViewById(R.id.instwebinput);
        instphoneinput = (TextInputLayout) findViewById(R.id.instphoneinput);
        instphoneainput = (TextInputLayout) findViewById(R.id.instphoneainput);
        instuniversityinput = (TextInputLayout) findViewById(R.id.instuniversityinput);
        instreginput = (TextInputLayout) findViewById(R.id.instreginput);

        caddrline1input = (TextInputLayout) findViewById(R.id.caddrline1input);
        caddrline2input = (TextInputLayout) findViewById(R.id.caddrline2input);
        caddrline3input = (TextInputLayout) findViewById(R.id.caddrline3input);

        iname = (TextInputEditText) findViewById(R.id.instname);
        iemail = (TextInputEditText) findViewById(R.id.instemail);
        iweb = (TextInputEditText) findViewById(R.id.instweb);
        iphone = (TextInputEditText) findViewById(R.id.instphone);
        ialtphone = (TextInputEditText) findViewById(R.id.instphonea);
        uniname = (TextInputEditText) findViewById(R.id.instuniversity);
        ireg = (TextInputEditText) findViewById(R.id.instreg);
        loctxt = (TextView) findViewById(R.id.loctxt);


        caddrline1 = (TextInputEditText) findViewById(R.id.caddrline1);
        caddrline2 = (TextInputEditText) findViewById(R.id.caddrline2);
        caddrline3 = (TextInputEditText) findViewById(R.id.caddrline3);


        instnameinput.setTypeface(Z.getLight(this));
        instemailinput.setTypeface(Z.getLight(this));
        instwebinput.setTypeface(Z.getLight(this));
        instphoneinput.setTypeface(Z.getLight(this));
        instphoneainput.setTypeface(Z.getLight(this));
        instuniversityinput.setTypeface(Z.getLight(this));
        instreginput.setTypeface(Z.getLight(this));
        caddrline1input.setTypeface(Z.getLight(this));
        caddrline2input.setTypeface(Z.getLight(this));
        caddrline3input.setTypeface(Z.getLight(this));

        iname.setTypeface(Z.getBold(this));
        iemail.setTypeface(Z.getBold(this));
        iweb.setTypeface(Z.getBold(this));
        iphone.setTypeface(Z.getBold(this));
        ialtphone.setTypeface(Z.getBold(this));
        uniname.setTypeface(Z.getBold(this));
        ireg.setTypeface(Z.getBold(this));
        caddrline1.setTypeface(Z.getBold(this));
        caddrline2.setTypeface(Z.getBold(this));
        caddrline3.setTypeface(Z.getBold(this));
        loctxt.setTypeface(Z.getBold(this));

        instname = a.getInstitute();
        instemail = a.getInstemail();
        instweb = a.getInstweb();
        instphone = a.getInstphone();
        instaltrphone = a.getInstaltrphone();
        universityname = a.getUnivname();
        instreg = a.getInstregno();

        instcaddrline1 = a.getInstcaddrline1();
        instcaddrline2 = a.getInstcaddrline2();
        instcaddrline3 = a.getInstcaddrline3();


        if (instname != null)
            iname.setText(instname);
        if (instemail != null)
            iemail.setText(instemail);
        if (instweb != null)
            iweb.setText(instweb);
        if (instphone != null)
            iphone.setText(instphone);
        if (instaltrphone != null)
            ialtphone.setText(instaltrphone);
        if (universityname != null)
            uniname.setText(universityname);
        if (instreg != null)
            ireg.setText(instreg);
        if (instreg != null)
            ireg.setText(instreg);

        if (instcaddrline1 != null)
            caddrline1.setText(instcaddrline1);

        if (instcaddrline2 != null)
            caddrline2.setText(instcaddrline2);

        if (instcaddrline3 != null)
            caddrline3.setText(instcaddrline3);

        iname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instnameinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instemailinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iweb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instwebinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instphoneinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ialtphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instphoneainput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        uniname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instuniversityinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ireg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instreginput.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        caddrline1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                caddrline1input.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        caddrline2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                caddrline2input.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        caddrline3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                caddrline3input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    void validateandSave() {


        int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0, errorflag6 = 0, errorflag7 = 0;

        instname = iname.getText().toString();
        instemail = iemail.getText().toString();
        instweb = iweb.getText().toString();
        instphone = iphone.getText().toString();
        instaltrphone = ialtphone.getText().toString();
        universityname = uniname.getText().toString();
        instreg = ireg.getText().toString();

        instcaddrline1 = caddrline1.getText().toString();
        instcaddrline2 = caddrline2.getText().toString();
        instcaddrline3 = caddrline3.getText().toString();


        if (instname.length() < 2) {
            instnameinput.setError("Kindly enter valid institute name");

            errorflag7 = 1;

        } else {
            if (!instemail.contains("@") || (!instemail.contains(".edu"))) {
                instemailinput.setError("Kindly enter valid email address (must contain .edu)");
                errorflag1 = 1;
            } else {
                if (instweb.length() < 3 || !instweb.contains(".")) {
                    instwebinput.setError("Kindly enter valid website URL");
                    errorflag2 = 1;
                } else {
                    if (instphone.length() < 10) {
                        instphoneinput.setError("Kindly enter valid phone number");
                        errorflag3 = 1;
                    } else {
                        if (universityname.length() < 2) {
                            instuniversityinput.setError("Kindly enter valid university name");
                            errorflag5 = 1;
                        } else {
                            if (instreg.length() < 2) {
                                instreginput.setError("Kindly enter valid registration number");
                                errorflag6 = 1;
                            } else {
                                if (instcaddrline1.length() < 2){
                                    caddrline1input.setError("Kindly enter valid address");
                                    errorflag6 = 1;
                                }
                                else {
                                    if (instcaddrline2.length() < 2) {
                                        caddrline2input.setError("Kindly enter valid address");
                                        errorflag6 = 1;
                                    }
                                    else {
                                        if (instcaddrline3.length() < 2) {
                                            caddrline3input.setError("Kindly enter valid address");
                                            errorflag6 = 1;
                                        }
                                    }
                                }
                            }

                        }

                    }

                }
            }
        }
        if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 && errorflag5 == 0 && errorflag6 == 0 && errorflag7 == 0) {
            try {
//

                /////******************object work*******************////////
                //one field is to be stored in adminintro
                try {
                    Log.d("TAG", "validateandSave: instreg - "+instreg);
                    obj = new AdminInstituteModal(instname, instemail, instweb, instphone, instaltrphone, universityname, instreg,instcaddrline1,instcaddrline2,instcaddrline3);
//
                    digest1 = MySharedPreferencesManager.getDigest1(this);
                    digest2 = MySharedPreferencesManager.getDigest2(this);

                    strobj = OtoString(obj, digest1, digest2);
                    Log.d("TAG", "validateandSave: - " + strobj);
                } catch (Exception e) {
                    Log.d("TAG", "validateandSave: - " + e.getMessage());
                }
                new SaveData().execute();

            } catch (Exception e) {

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
        if (edittedFlag == 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AdminInstituteDetails.super.onBackPressed();
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
            AdminInstituteDetails.super.onBackPressed();

    }


    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("obj", strobj));    //1


            json = jParser.makeHttpRequest(Z.url_SaveAdminInstituteData, "GET", params);
            try {
//                Log.d("Reversecheck", "doInBackground: "+json.getString("dataobj"));
                r = json.getString("info");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {
                Toast.makeText(AdminInstituteDetails.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();
                a.setFname(instname);
                a.setInstemail(instemail);
                a.setInstweb(instweb);
                a.setInstphone(instphone);
                a.setInstaltrphone(instaltrphone);
                a.setUnivname(universityname);
                a.setInstregno(instreg);
                MySharedPreferencesManager.save(AdminInstituteDetails.this, "instname", instname);

                if (edittedFlag == 1) {
                    setResult(111);
                }
                AdminInstituteDetails.super.onBackPressed();
            } else {
                Toast.makeText(AdminInstituteDetails.this, "not saved" + result, Toast.LENGTH_SHORT).show();

            }
        }
    }

}



