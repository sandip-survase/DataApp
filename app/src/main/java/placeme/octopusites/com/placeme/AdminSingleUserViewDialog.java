package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class AdminSingleUserViewDialog extends AppCompatActivity {

    int flag = 0;
    CheckBox checkboxplaced, checkboxnotplaced, CheckBoxdebar, CheckBoxsnotdebar, checkboxstudent, checkboxalumni;
    EditText email, companyname;
    TextInputLayout emailinput,companynameinput;
    String PLACED = "", DEBAR = "", ROLE = "";
    String username,strcompanyname,isactivated;
    String encUsername,encCompanyname,encPlaced,encdebar,encadminUsername;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    private byte[] demo1DecryptedBytes;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding;
    ImageView trash2ImageView;
    Boolean changeFlag=false;
    TextView ass2txt,passpasstxt,placementstatus2txt,debarstatus2txt,rolestatus2txt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_single_user_view_dialog);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit User");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        checkboxplaced = (CheckBox) findViewById(R.id.checkboxplaced);
        checkboxnotplaced = (CheckBox) findViewById(R.id.checkboxnotplaced);
        CheckBoxdebar = (CheckBox) findViewById(R.id.CheckBoxdebar);
        CheckBoxsnotdebar = (CheckBox) findViewById(R.id.CheckBoxsnotdebar);
        checkboxstudent = (CheckBox) findViewById(R.id.checkboxstudent);
        checkboxalumni = (CheckBox) findViewById(R.id.checkboxalumni);
        trash2ImageView= (ImageView) findViewById(R.id.trash2);
        //default

        checkboxplaced.setTypeface(MyConstants.getBold(this));
        checkboxnotplaced.setTypeface(MyConstants.getBold(this));
        CheckBoxdebar.setTypeface(MyConstants.getBold(this));
        CheckBoxsnotdebar.setTypeface(MyConstants.getBold(this));
        checkboxstudent.setTypeface(MyConstants.getBold(this));
        checkboxalumni.setTypeface(MyConstants.getBold(this));



        ass2txt=(TextView)findViewById(R.id.ass2txt);
        passpasstxt=(TextView)findViewById(R.id.passpasstxt);
        placementstatus2txt=(TextView)findViewById(R.id.placementstatus2txt);
        debarstatus2txt=(TextView)findViewById(R.id.debarstatus2txt);
        rolestatus2txt=(TextView)findViewById(R.id.rolestatus2txt);

        ass2txt.setTypeface(MyConstants.getBold(this));
        passpasstxt.setTypeface(MyConstants.getLight(this));
        placementstatus2txt.setTypeface(MyConstants.getLight(this));
        debarstatus2txt.setTypeface(MyConstants.getLight(this));
        rolestatus2txt.setTypeface(MyConstants.getLight(this));


        emailinput=(TextInputLayout)findViewById(R.id.emailinput);
        companynameinput=(TextInputLayout)findViewById(R.id.companynameinput);
        email = (EditText) findViewById(R.id.email);
        companyname = (EditText) findViewById(R.id.companyname);

        emailinput.setTypeface(MyConstants.getLight(this));
        companynameinput.setTypeface(MyConstants.getLight(this));

        email.setTypeface(MyConstants.getBold(this));
        companyname.setTypeface(MyConstants.getBold(this));

        demoKeyBytes = SimpleBase64Encoder.decode(MySharedPreferencesManager.getDigest1(AdminSingleUserViewDialog.this));
        demoIVBytes = SimpleBase64Encoder.decode(MySharedPreferencesManager.getDigest2(AdminSingleUserViewDialog.this));
        sPadding = "ISO10126Padding";
        encadminUsername=MySharedPreferencesManager.getUsername(AdminSingleUserViewDialog.this);

        username=getIntent().getStringExtra("studentUsername");
        ROLE=getIntent().getStringExtra("role");
        isactivated=getIntent().getStringExtra("isactivated");
        email.setText(username);
        if(ROLE.equals("student"))
            checkboxstudent.setChecked(true);
        else
            checkboxalumni.setChecked(true);

        View trash2 = (View) findViewById(R.id.trash2selectionview);

        if(isactivated.equals("Activated")){
            trash2.setVisibility(View.GONE);
            trash2ImageView.setVisibility(View.GONE);
            email.setFocusable(false);
        }

        trash2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AdminSingleUserViewDialog.this);

                alertDialogBuilder
                        .setMessage("Are you sure you want to delete this account, by clicking yes this user will be deactivated ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                            new DeleteNonActiveUser().execute();
                                        AdminSingleUserViewDialog.super.onBackPressed();
                                    }
                                })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
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
            }
        });




        checkboxplaced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkboxnotplaced.setChecked(false);
                    companyname.setError(null);
                    PLACED = "yes";
                    companyname.setVisibility(View.VISIBLE);
                }
                changeFlag=true;
            }
        });

        checkboxnotplaced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkboxplaced.setChecked(false);
                    companyname.setError(null);
                    PLACED = "no";
                    companyname.setVisibility(View.GONE);
                }
                changeFlag=true;
            }
        });
        checkboxnotplaced.setChecked(true);
        CheckBoxsnotdebar.setChecked(true);
        checkboxstudent.setChecked(true);

        CheckBoxdebar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CheckBoxsnotdebar.setChecked(false);
                    DEBAR = "yes";
                }
                changeFlag=true;
            }
        });

        CheckBoxsnotdebar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CheckBoxdebar.setChecked(false);
                    DEBAR = "no";
                }
                changeFlag=true;
            }
        });

        checkboxstudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkboxalumni.setChecked(false);
                    ROLE = "student";
                }
                changeFlag=true;
            }
        });

        checkboxalumni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkboxstudent.setChecked(false);
                    ROLE = "alumni";
                }
                changeFlag=true;
            }
        });

        new GetRegisteredUsersUnderAdmin().execute();

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isactivated.equals("Activated")){
                    Toast.makeText(AdminSingleUserViewDialog.this,"You can not edit username of Activated user", Toast.LENGTH_SHORT).show();
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeFlag=true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        changeFlag=false;

    }//onc



    void showmenu() {
        AdminSingleUserViewDialog.this.invalidateOptionsMenu();
        Menu m = null;
        flag = 1;
        super.onCreateOptionsMenu(m);
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

        if (flag == 1)
            getMenuInflater().inflate(R.menu.admin_edit, menu);

        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);


    }


    class GetRegisteredUsersUnderAdmin extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {



            try {

                byte[] usernameBytes = username.getBytes("UTF-8");


                byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                encUsername=new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", encUsername));
                Log.d("TAG", "doInBackground: pass username "+username);

                json = jParser.makeHttpRequest(MyConstants.url_GetPlacedDebarInfo, "GET", params);

                String info = json.getString("info");
                Log.d("TAG", "doInBackground: GetRegisteredUsersUnderAdmin info "+info);
                if (info.equals("success")) {

                    String isplaced=json.getString("isplaced");
                    strcompanyname=json.getString("companyname");
                    String isdebar=json.getString("isdebar");

                    Log.d("TAG", "doInBackground: isplaced "+isplaced);
                    Log.d("TAG", "doInBackground: isdebar "+isdebar);

                    if (!isplaced.equals("null")) {
                        byte[] demo1EncryptedBytes = SimpleBase64Encoder.decode(isplaced);
                        demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
                        isplaced = new String(demo1DecryptedBytes);
                        PLACED=isplaced;

                    }

                    if (!strcompanyname.equals("null")) {
                        byte[] demo1EncryptedBytes = SimpleBase64Encoder.decode(strcompanyname);
                        demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
                        strcompanyname = new String(demo1DecryptedBytes);


                    }

                    if (!isdebar.equals("null")) {
                        byte[] demo1EncryptedBytes = SimpleBase64Encoder.decode(isdebar);
                        demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
                        isdebar = new String(demo1DecryptedBytes);
                        DEBAR=isdebar;

                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "doInBackground: exp "+e.getMessage());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

//            Log.d("TAG", "onPostExecute: PLACED "+PLACED);
//            Log.d("TAG", "onPostExecute: DEBAR "+DEBAR);
//            Log.d("TAG", "onPostExecute: strcompanyname "+strcompanyname);
                if(PLACED.equals("yes"))
                    checkboxplaced.setChecked(true);

                if(DEBAR.equals("yes"))
                    CheckBoxdebar.setChecked(true);

            if(strcompanyname!=null && !strcompanyname.equals(""))
                companyname.setText(strcompanyname);

        }
    }

    void validateandSave()
    {

            int errorflag=0;

            username = email.getText().toString();
            strcompanyname = companyname.getText().toString();



            if(!username.contains("@"))
            {
                email.setError("Incorrect Username");
                errorflag=1;
            }
            if(checkboxplaced.isChecked()) {
                if(strcompanyname.length()<1) {
                    errorflag=1;
                    companynameinput.setError("Kindly enter valid company name");
                }
            }

            if (errorflag == 0 ) {
                try {

                    byte[] usernameBytes = username.getBytes("UTF-8");
                    byte[] companynameBytes = strcompanyname.getBytes("UTF-8");

                    byte[] placedBytes = PLACED.getBytes("UTF-8");
                    byte[] debarBytes = DEBAR.getBytes("UTF-8");


                    byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                    encUsername = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));

                    byte[] companynameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, companynameBytes);
                    encCompanyname = new String(SimpleBase64Encoder.encode(companynameEncryptedBytes));

                    byte[] placedBytesEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, placedBytes);
                    encPlaced = new String(SimpleBase64Encoder.encode(placedBytesEncryptedBytes));

                    byte[] debarEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, debarBytes);
                    encdebar = new String(SimpleBase64Encoder.encode(debarEncryptedBytes));


                    new SaveData().execute();

                } catch (Exception e) {

                }
            }
        }


    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",encUsername));    //0
            params.add(new BasicNameValuePair("p",encPlaced));       //1
            params.add(new BasicNameValuePair("c",encCompanyname));       //2
            params.add(new BasicNameValuePair("d",encdebar));     //3


            json = jParser.makeHttpRequest(MyConstants.url_SavePlacedDebarInfo, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(AdminSingleUserViewDialog.this,"Successfully updated..!",Toast.LENGTH_SHORT).show();

//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result", result);

                AdminSingleUserViewDialog.super.onBackPressed();
            }
            else {
                Toast.makeText(AdminSingleUserViewDialog.this,result,Toast.LENGTH_SHORT).show();

            }
        }
    }


    class DeleteNonActiveUser extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encadminUsername));    //0
            params.add(new BasicNameValuePair("ue", encUsername));//1
            Log.d("TAG", "doInBackground: delete admin "+encadminUsername);
            Log.d("TAG", "doInBackground: delete admin "+encUsername);
            json = jParser.makeHttpRequest(MyConstants.url_DeleteNonActiveUser, "GET", params);
            try {
                r = json.getString("info");
                Log.d("TAG", "DeleteNonActiveUser: info "+r);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(AdminSingleUserViewDialog.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return r;
        }
        @Override
        protected void onPostExecute(String result) {
            if(result.equals("success"))
                Toast.makeText(AdminSingleUserViewDialog.this, "Successfully deleted..!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {

        if(changeFlag) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AdminSingleUserViewDialog.super.onBackPressed();
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
        }
        else
        AdminSingleUserViewDialog.super.onBackPressed();
    }
}
