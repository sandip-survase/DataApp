package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.AdminInstituteModal;
import placeme.octopusites.com.placeme.modal.AdminIntroModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;


public class AdminInstituteDetails extends AppCompatActivity {

    //sss
    EditText iname,iemail,iweb,iphone,ialtphone,uniname,ireg;
    String instname="",instemail="",instweb="",instphone="",instaltrphone="",universityname="",instreg="";
    String encUsername,enciname,encinstemail,encinstweb,encinstphone,encinstaltrphone,encuniversityname,encCinstreg;

    private String username;
    int edittedFlag=0;


    String digest1,digest2;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Username = "nameKey";


    AdminData a =new AdminData();
    //
    AdminInstituteModal obj;
    String strobj="",encobj;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_institute_details);

        //sss
        Digest d=new Digest();
        digest1=d.getDigest1();
        digest2=d.getDigest2();

        sharedpreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);
//        String role=sharedpreferences.getString("role",null);

        if(digest1==null||digest2==null) {
            digest1 = sharedpreferences.getString("digest1", null);
            digest2 = sharedpreferences.getString("digest2", null);
            d.setDigest1(digest1);
            d.setDigest2(digest2);
        }



        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Institute Details");
        ab.setDisplayHomeAsUpEnabled(true);


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        iname=(EditText)findViewById(R.id.instname);
        iemail=(EditText)findViewById(R.id.instemail);
        iweb=(EditText)findViewById(R.id.instweb);
        iphone=(EditText)findViewById(R.id.instphone);
        ialtphone=(EditText)findViewById(R.id.instphonea);
        uniname=(EditText)findViewById(R.id.instuniversity);
        ireg=(EditText)findViewById(R.id.instreg);

        ProfileRole r=new ProfileRole();

        encUsername=MySharedPreferencesManager.getUsername(this);

        instname = a.getInstitute();
        instemail= a.getInstemail();
        instweb= a.getInstweb();
        instphone= a.getInstphone();
        instaltrphone= a.getInstaltrphone();
        universityname= a.getUnivname();
        instreg= a.getInstregno();

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


        iname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    void validateandSave()    {



        int errorflag1=0,errorflag2=0,errorflag3=0,errorflag4=0, errorflag5=0,errorflag6=0,errorflag7=0;

        instname = iname.getText().toString();
        instemail = iemail.getText().toString();
        instweb = iweb.getText().toString();
        instphone = iphone.getText().toString();
        instaltrphone = ialtphone.getText().toString();
        universityname = uniname.getText().toString();
        instreg = ireg.getText().toString();




        if (instname.length() < 2){
            iname.setError("Incorrect Institute Name");

            errorflag7 = 1;

        }else {
            if (!instemail.contains("@")) {
                iemail.setError("Incorrect Email Address");
                errorflag1 = 1;
            } else {
                if (instweb.length() < 3 && !instweb.contains(".")) {
                    iweb.setError("Incorrect Web Address");
                    errorflag2 = 1;
                } else {
                    if (instphone.length() < 6) {
                        iphone.setError("Incorrect phone number ");
                        errorflag3 = 1;
                    } else {
                        if (instaltrphone.length() < 6) {
                            ialtphone.setError("Incorrect phone number ");
                            errorflag4 = 1;
                        } else {
                            if (universityname.length() < 2) {
                                uniname.setError("Incorrect phone number ");
                                errorflag5 = 1;
                            } else {
                                if (instreg.length() < 2) {
                                    ireg.setError("Incorrect Registration number ");
                                    errorflag6 = 1;
                                }
                            }
                        }

                    }


                }
            }
        }
        if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 && errorflag5 == 0&& errorflag6 == 0 && errorflag7==0) {
            try {
//

                /////******************object work*******************////////
                //one field is to be stored in adminintro
                try{
                obj = new AdminInstituteModal(instname,instemail,instweb,instphone,instaltrphone,universityname,instreg);
//

                    digest1=MySharedPreferencesManager.getDigest1(this);
                    digest2=MySharedPreferencesManager.getDigest2(this);

                    strobj =OtoString(obj,digest1,digest2);
                    Log.d("TAG", "validateandSave: - "+strobj);
                }
                catch (Exception e){
                    Log.d("TAG", "validateandSave: - "+e.getMessage());
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

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",encUsername));    //0
            params.add(new BasicNameValuePair("obj",strobj));    //1



            json = jParser.makeHttpRequest(MyConstants.url_SaveAdminInstituteData, "GET", params);
            try {
//                Log.d("Reversecheck", "doInBackground: "+json.getString("dataobj"));
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(AdminInstituteDetails.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();
                a.setFname(instname);
                a.setInstemail(instemail);
                a.setInstweb(instweb);
                a.setInstphone(instphone);
                a.setInstaltrphone(instaltrphone);
                a.setUnivname(universityname);
                a.setInstregno(instreg);
                MySharedPreferencesManager.save (AdminInstituteDetails.this,"instname",instname);

                if(edittedFlag==1){
                    setResult(111);
                }
                AdminInstituteDetails.super.onBackPressed();
            }
            else {
                Toast.makeText(AdminInstituteDetails.this,result,Toast.LENGTH_SHORT).show();

            }
        }
    }







}



