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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import placeme.octopusites.com.placeme.modal.MyProfileStrengthsModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class MyProfileStrengths extends AppCompatActivity {

    int strengthcount=0;
    View addmorestrength;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    //    private static String url_savestrengths= "http://192.168.100.100/AESTest/SaveStrengths";
    View trash1selectionview,trash2selectionview,trash3selectionview,trash4selectionview,trash5selectionview,trash6selectionview,trash7selectionview,trash8selectionview,trash9selectionview,trash10selectionview;
    int edittedFlag=0;;
    int d=0;
    StudentData s=new StudentData();
    EditText strength1,strength2,strength3,strength4,strength5,strength6,strength7,strength8,strength9,strength10;
    String sstrength1,sstrength2,sstrength3,sstrength4,sstrength5,sstrength6,sstrength7,sstrength8,sstrength9,sstrength10,encobj="";
    String encstrength1,encstrength2,encstrength3,encstrength4,encstrength5,encstrength6,encstrength7,encstrength8,encstrength9,encstrength10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_strengths);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Strengths");
        ab.setDisplayHomeAsUpEnabled(true);

        trash1selectionview=(View)findViewById(R.id.trash1selectionview);
        trash2selectionview=(View)findViewById(R.id.trash2selectionview);
        trash3selectionview=(View)findViewById(R.id.trash3selectionview);
        trash4selectionview=(View)findViewById(R.id.trash4selectionview);
        trash5selectionview=(View)findViewById(R.id.trash5selectionview);
        trash6selectionview=(View)findViewById(R.id.trash6selectionview);
        trash7selectionview=(View)findViewById(R.id.trash7selectionview);
        trash8selectionview=(View)findViewById(R.id.trash8selectionview);
        trash9selectionview=(View)findViewById(R.id.trash9selectionview);
        trash10selectionview=(View)findViewById(R.id.trash10selectionview);

        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=1;
                showDeletDialog();
            }
        });
        trash2selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=2;
                showDeletDialog();

            }
        });
        trash3selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=3;
                showDeletDialog();
            }
        });
        trash4selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=4;
                showDeletDialog();
            }
        });
        trash5selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=5;
                showDeletDialog();
            }
        });
        trash6selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=6;
                showDeletDialog();
            }
        });
        trash7selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=7;
                showDeletDialog();
            }
        });
        trash8selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=8;
                showDeletDialog();
            }
        });
        trash9selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=9;
                showDeletDialog();
            }
        });
        trash10selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=10;
                showDeletDialog();
            }
        });

        strength1=(EditText)findViewById(R.id.strength1);
        strength2=(EditText)findViewById(R.id.strength2);
        strength3=(EditText)findViewById(R.id.strength3);
        strength4=(EditText)findViewById(R.id.strength4);
        strength5=(EditText)findViewById(R.id.strength5);
        strength6=(EditText)findViewById(R.id.strength6);
        strength7=(EditText)findViewById(R.id.strength7);
        strength8=(EditText)findViewById(R.id.strength8);
        strength9=(EditText)findViewById(R.id.strength9);
        strength10=(EditText)findViewById(R.id.strength10);

        strength1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                strength1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        strength2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                strength2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        strength3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                strength3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        strength4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                strength4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        strength5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                strength5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        strength6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                strength6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        strength7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                strength7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        strength8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                strength8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        strength9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                strength9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        strength10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                strength10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        TextView strengthtxt=(TextView)findViewById(R.id.strengthtxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        strengthtxt.setTypeface(custom_font1);

        addmorestrength=(View)findViewById(R.id.addmorestrength);

        addmorestrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(strengthcount==0)
                {
                    if(strength1.getText().toString()!=null ){
                        if(!strength1.getText().toString().equals("")){
                            View v=(View)findViewById(R.id.strengthline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl2);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            strengthcount++;
                        }else
                            Toast.makeText(MyProfileStrengths.this, "Please enter first strength", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileStrengths.this, "Please enter first strength", Toast.LENGTH_SHORT).show();
                }
                else if(strengthcount==1)
                {
                    if(strength2.getText().toString()!=null ){
                        if(!strength2.getText().toString().equals("")){
                            View v=(View)findViewById(R.id.strengthline2);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl3);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            strengthcount++;
                        }
                        else
                            Toast.makeText(MyProfileStrengths.this, "Please enter Second strength", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MyProfileStrengths.this, "Please enter second strength", Toast.LENGTH_SHORT).show();

                }
                else if(strengthcount==2)
                {
                    if(strength3.getText().toString()!=null ) {
                        if (!strength3.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.strengthline3);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl4);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            strengthcount++;
                        }else
                            Toast.makeText(MyProfileStrengths.this, "Please enter third strength", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MyProfileStrengths.this, "Please enter third strength", Toast.LENGTH_SHORT).show();

                }
                else  if(strengthcount==3)
                {
                    if(strength4.getText().toString()!=null ) {
                        if (!strength4.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.strengthline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl5);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            strengthcount++;
                        }else
                            Toast.makeText(MyProfileStrengths.this, "Please enter fourth strength", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MyProfileStrengths.this, "Please enter fourth strength", Toast.LENGTH_SHORT).show();
                }
                else if(strengthcount==4)
                {
                    if(strength5.getText().toString()!=null ) {
                        if (!strength5.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.strengthline5);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl6);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            strengthcount++;
                        }else
                            Toast.makeText(MyProfileStrengths.this, "Please enter fifth strength", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MyProfileStrengths.this, "Please enter fifth strength", Toast.LENGTH_SHORT).show();
                }
                else if(strengthcount==5)
                {
                    if(strength6.getText().toString()!=null ) {
                        if (!strength6.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.strengthline6);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl7);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            strengthcount++;
                        }else
                            Toast.makeText(MyProfileStrengths.this, "Please enter sixth strength", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MyProfileStrengths.this, "Please enter sixth strength", Toast.LENGTH_SHORT).show();
                }
                else if(strengthcount==6)
                {
                    if(strength7.getText().toString()!=null ) {
                        if (!strength7.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.strengthline7);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl8);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            strengthcount++;
                        }else
                            Toast.makeText(MyProfileStrengths.this, "Please enter seventh strength", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MyProfileStrengths.this, "Please enter seventh strength", Toast.LENGTH_SHORT).show();
                }
                else if(strengthcount==7)
                {
                    if(strength8.getText().toString()!=null ) {
                        if (!strength8.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.strengthline8);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl9);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            strengthcount++;
                        }else
                            Toast.makeText(MyProfileStrengths.this, "Please enter eighth strength", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MyProfileStrengths.this, "Please enter eighth strength", Toast.LENGTH_SHORT).show();

                }
                else if(strengthcount==8)
                {
                    if(strength9.getText().toString()!=null ) {
                        if (!strength9.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.strengthline9);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl10);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            strengthcount++;
                            TextView t = (TextView) findViewById(R.id.addmorestrengthtxt);
                            ImageView i = (ImageView) findViewById(R.id.addmorestrengthimg);
                            addmorestrength.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);
                        }else
                            Toast.makeText(MyProfileStrengths.this, "Please enter nineth strength", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MyProfileStrengths.this, "Please enter nineth strength", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sharedpreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);
        String role=sharedpreferences.getString("role",null);

        ProfileRole r=new ProfileRole();
        r.setUsername(username);
        r.setRole(role);

        Digest d=new Digest();
        digest1=d.getDigest1();
        digest2=d.getDigest2();

        if(digest1==null||digest2==null) {
            digest1 = sharedpreferences.getString("digest1", null);
            digest2 = sharedpreferences.getString("digest2", null);
            d.setDigest1(digest1);
            d.setDigest2(digest2);
        }

        sstrength1=s.getStrength1();
        sstrength2=s.getStrength2();
        sstrength3=s.getStrength3();
        sstrength4=s.getStrength4();
        sstrength5=s.getStrength5();
        sstrength6=s.getStrength6();
        sstrength7=s.getStrength7();
        sstrength8=s.getStrength8();
        sstrength9=s.getStrength9();
        sstrength10=s.getStrength10();

        if(sstrength1!=null) {
            if (sstrength1.length() > 2) {
                strength1.setText(sstrength1);
            }
        }
        if(sstrength2!=null) {
            if (sstrength2.length() > 2) {
                strength2.setText(sstrength2);

                View v = (View) findViewById(R.id.strengthline1);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl2);
                relativeLayout1.setVisibility(View.VISIBLE);
                strengthcount++;
            }
        }
        if(sstrength3!=null) {
            if (sstrength3.length() > 2) {
                strength3.setText(sstrength3);

                View v = (View) findViewById(R.id.strengthline2);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl3);
                relativeLayout1.setVisibility(View.VISIBLE);
                strengthcount++;
            }
        }
        if(sstrength4!=null) {
            if (sstrength4.length() > 2) {
                strength4.setText(sstrength4);

                View v = (View) findViewById(R.id.strengthline3);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl4);
                relativeLayout1.setVisibility(View.VISIBLE);
                strengthcount++;
            }
        }
        if(sstrength5!=null) {
            if (sstrength5.length() > 2) {
                strength5.setText(sstrength5);

                View v = (View) findViewById(R.id.strengthline4);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl5);
                relativeLayout1.setVisibility(View.VISIBLE);
                strengthcount++;
            }
        }
        if(sstrength6!=null) {
            if (sstrength6.length() > 2) {
                strength6.setText(sstrength6);

                View v = (View) findViewById(R.id.strengthline5);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl6);
                relativeLayout1.setVisibility(View.VISIBLE);
                strengthcount++;
            }
        }
        if(sstrength7!=null) {
            if (sstrength7.length() > 2) {
                strength7.setText(sstrength7);

                View v = (View) findViewById(R.id.strengthline6);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl7);
                relativeLayout1.setVisibility(View.VISIBLE);
                strengthcount++;
            }
        }
        if(sstrength8!=null) {
            if (sstrength8.length() > 2) {
                strength8.setText(sstrength8);

                View v = (View) findViewById(R.id.strengthline7);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl8);
                relativeLayout1.setVisibility(View.VISIBLE);
                strengthcount++;
            }
        }
        if(sstrength9!=null) {
            if (sstrength9.length() > 2) {
                strength9.setText(sstrength9);

                View v = (View) findViewById(R.id.strengthline8);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl9);
                relativeLayout1.setVisibility(View.VISIBLE);
                strengthcount++;
            }
        }
        if(sstrength10!=null) {
            if (sstrength10.length() > 2) {
                strength10.setText(sstrength10);

                View v = (View) findViewById(R.id.strengthline9);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.strengthrl10);
                relativeLayout1.setVisibility(View.VISIBLE);
                strengthcount++;

                TextView t = (TextView) findViewById(R.id.addmorestrengthtxt);
                ImageView i = (ImageView) findViewById(R.id.addmorestrengthimg);
                addmorestrength.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                i.setVisibility(View.GONE);
            }
        }


        edittedFlag=0;
    }
    void showDeletDialog()
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this patent ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag=1;
                                deleteStrength();
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
    void deleteStrength()
    {
        View v = (View) findViewById(R.id.strengthline9);
        if (v.getVisibility() == View.VISIBLE) {
            View v1=(View)findViewById(R.id.strengthline9);
            v1.setVisibility(View.GONE);

            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl10);
            relativeLayout1.setVisibility(View.GONE);
            strengthcount--;

            TextView t=(TextView)findViewById(R.id.addmorestrengthtxt);
            ImageView i=(ImageView)findViewById(R.id.addmorestrengthimg);
            addmorestrength.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);


        }
        else
        {
            v = (View) findViewById(R.id.strengthline8);
            if (v.getVisibility() == View.VISIBLE) {
                View v1=(View)findViewById(R.id.strengthline8);
                v1.setVisibility(View.GONE);

                RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl9);
                relativeLayout1.setVisibility(View.GONE);
                strengthcount--;

            }
            else
            {
                v = (View) findViewById(R.id.strengthline7);
                if (v.getVisibility() == View.VISIBLE) {
                    View v1=(View)findViewById(R.id.strengthline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl8);
                    relativeLayout1.setVisibility(View.GONE);
                    strengthcount--;

                }
                else
                {
                    v = (View) findViewById(R.id.strengthline6);
                    if (v.getVisibility() == View.VISIBLE) {
                        View v1=(View)findViewById(R.id.strengthline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl7);
                        relativeLayout1.setVisibility(View.GONE);
                        strengthcount--;

                    }
                    else
                    {
                        v = (View) findViewById(R.id.strengthline5);
                        if (v.getVisibility() == View.VISIBLE) {
                            View v1=(View)findViewById(R.id.strengthline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl6);
                            relativeLayout1.setVisibility(View.GONE);
                            strengthcount--;

                        }
                        else
                        {
                            v = (View) findViewById(R.id.strengthline4);
                            if (v.getVisibility() == View.VISIBLE) {
                                View v1=(View)findViewById(R.id.strengthline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl5);
                                relativeLayout1.setVisibility(View.GONE);
                                strengthcount--;

                            }
                            else
                            {
                                v = (View) findViewById(R.id.strengthline3);
                                if (v.getVisibility() == View.VISIBLE) {
                                    View v1=(View)findViewById(R.id.strengthline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl4);
                                    relativeLayout1.setVisibility(View.GONE);
                                    strengthcount--;

                                }
                                else
                                {
                                    v = (View) findViewById(R.id.strengthline2);
                                    if (v.getVisibility() == View.VISIBLE) {
                                        View v1=(View)findViewById(R.id.strengthline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl3);
                                        relativeLayout1.setVisibility(View.GONE);
                                        strengthcount--;

                                    }
                                    else
                                    {
                                        v = (View) findViewById(R.id.strengthline1);
                                        if (v.getVisibility() == View.VISIBLE) {
                                            View v1=(View)findViewById(R.id.strengthline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.strengthrl2);
                                            relativeLayout1.setVisibility(View.GONE);
                                            strengthcount--;

                                        }
                                        else
                                        {
                                            strength1.setText("");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(d==10)
        {
            strength10.setText("");
        }
        else if(d==9)
        {
            sstrength10=strength10.getText().toString();

            if(sstrength10.length()>2) {
                sstrength9 = sstrength10;
                strength9.setText(sstrength9);
                strength10.setText("");
            }
        }
        else if(d==8)
        {
            sstrength10=strength10.getText().toString();
            sstrength9=strength9.getText().toString();

            if(sstrength9.length()>2) {
                sstrength8 = sstrength9;
                strength8.setText(sstrength8);
                strength9.setText("");
            }
            if(sstrength10.length()>2) {
                sstrength9 = sstrength10;
                strength9.setText(sstrength9);
                strength10.setText("");
            }
        }
        else if(d==7)
        {
            sstrength10=strength10.getText().toString();
            sstrength9=strength9.getText().toString();
            sstrength8=strength8.getText().toString();

            if(sstrength8.length()>2) {
                sstrength7 = sstrength8;
                strength7.setText(sstrength7);
                strength8.setText("");
            }
            if(sstrength9.length()>2) {
                sstrength8 = sstrength9;
                strength8.setText(sstrength8);
                strength9.setText("");
            }
            if(sstrength10.length()>2) {
                sstrength9 = sstrength10;
                strength9.setText(sstrength9);
                strength10.setText("");
            }
        }
        else if(d==6)
        {
            sstrength10=strength10.getText().toString();
            sstrength9=strength9.getText().toString();
            sstrength8=strength8.getText().toString();
            sstrength7=strength7.getText().toString();

            if(sstrength7.length()>2) {
                sstrength6 = sstrength7;
                strength6.setText(sstrength6);
                strength7.setText("");
            }
            if(sstrength8.length()>2) {
                sstrength7 = sstrength8;
                strength7.setText(sstrength7);
                strength8.setText("");
            }
            if(sstrength9.length()>2) {
                sstrength8 = sstrength9;
                strength8.setText(sstrength8);
                strength9.setText("");
            }
            if(sstrength10.length()>2) {
                sstrength9 = sstrength10;
                strength9.setText(sstrength9);
                strength10.setText("");
            }
        }
        else if(d==5)
        {
            sstrength10=strength10.getText().toString();
            sstrength9=strength9.getText().toString();
            sstrength8=strength8.getText().toString();
            sstrength7=strength7.getText().toString();
            sstrength6=strength6.getText().toString();

            if(sstrength6.length()>2) {
                sstrength5 = sstrength6;
                strength5.setText(sstrength5);
                strength6.setText("");
            }
            if(sstrength7.length()>2) {
                sstrength6 = sstrength7;
                strength6.setText(sstrength6);
                strength7.setText("");
            }
            if(sstrength8.length()>2) {
                sstrength7 = sstrength8;
                strength7.setText(sstrength7);
                strength8.setText("");
            }
            if(sstrength9.length()>2) {
                sstrength8 = sstrength9;
                strength8.setText(sstrength8);
                strength9.setText("");
            }
            if(sstrength10.length()>2) {
                sstrength9 = sstrength10;
                strength9.setText(sstrength9);
                strength10.setText("");
            }
        }
        else if(d==4)
        {
            sstrength10=strength10.getText().toString();
            sstrength9=strength9.getText().toString();
            sstrength8=strength8.getText().toString();
            sstrength7=strength7.getText().toString();
            sstrength6=strength6.getText().toString();
            sstrength5=strength5.getText().toString();

            if(sstrength5.length()>2) {
                sstrength4 = sstrength5;
                strength4.setText(sstrength4);
                strength5.setText("");
            }
            if(sstrength6.length()>2) {
                sstrength5 = sstrength6;
                strength5.setText(sstrength5);
                strength6.setText("");
            }
            if(sstrength7.length()>2) {
                sstrength6 = sstrength7;
                strength6.setText(sstrength6);
                strength7.setText("");
            }
            if(sstrength8.length()>2) {
                sstrength7 = sstrength8;
                strength7.setText(sstrength7);
                strength8.setText("");
            }
            if(sstrength9.length()>2) {
                sstrength8 = sstrength9;
                strength8.setText(sstrength8);
                strength9.setText("");
            }
            if(sstrength10.length()>2) {
                sstrength9 = sstrength10;
                strength9.setText(sstrength9);
                strength10.setText("");
            }
        }
        else if(d==3)
        {
            sstrength10=strength10.getText().toString();
            sstrength9=strength9.getText().toString();
            sstrength8=strength8.getText().toString();
            sstrength7=strength7.getText().toString();
            sstrength6=strength6.getText().toString();
            sstrength5=strength5.getText().toString();
            sstrength4=strength4.getText().toString();

            if(sstrength4.length()>2) {
                sstrength3 = sstrength4;
                strength3.setText(sstrength3);
                strength4.setText("");
            }
            if(sstrength5.length()>2) {
                sstrength4 = sstrength5;
                strength4.setText(sstrength4);
                strength5.setText("");
            }
            if(sstrength6.length()>2) {
                sstrength5 = sstrength6;
                strength5.setText(sstrength5);
                strength6.setText("");
            }
            if(sstrength7.length()>2) {
                sstrength6 = sstrength7;
                strength6.setText(sstrength6);
                strength7.setText("");
            }
            if(sstrength8.length()>2) {
                sstrength7 = sstrength8;
                strength7.setText(sstrength7);
                strength8.setText("");
            }
            if(sstrength9.length()>2) {
                sstrength8 = sstrength9;
                strength8.setText(sstrength8);
                strength9.setText("");
            }
            if(sstrength10.length()>2) {
                sstrength9 = sstrength10;
                strength9.setText(sstrength9);
                strength10.setText("");
            }
        }
        else if(d==2)
        {
            sstrength10=strength10.getText().toString();
            sstrength9=strength9.getText().toString();
            sstrength8=strength8.getText().toString();
            sstrength7=strength7.getText().toString();
            sstrength6=strength6.getText().toString();
            sstrength5=strength5.getText().toString();
            sstrength4=strength4.getText().toString();
            sstrength3=strength3.getText().toString();

            if(sstrength3.length()>2) {
                sstrength2 = sstrength3;
                strength2.setText(sstrength2);
                strength3.setText("");
            }
            if(sstrength4.length()>2) {
                sstrength3 = sstrength4;
                strength3.setText(sstrength3);
                strength4.setText("");
            }
            if(sstrength5.length()>2) {
                sstrength4 = sstrength5;
                strength4.setText(sstrength4);
                strength5.setText("");
            }
            if(sstrength6.length()>2) {
                sstrength5 = sstrength6;
                strength5.setText(sstrength5);
                strength6.setText("");
            }
            if(sstrength7.length()>2) {
                sstrength6 = sstrength7;
                strength6.setText(sstrength6);
                strength7.setText("");
            }
            if(sstrength8.length()>2) {
                sstrength7 = sstrength8;
                strength7.setText(sstrength7);
                strength8.setText("");
            }
            if(sstrength9.length()>2) {
                sstrength8 = sstrength9;
                strength8.setText(sstrength8);
                strength9.setText("");
            }
            if(sstrength10.length()>2) {
                sstrength9 = sstrength10;
                strength9.setText(sstrength9);
                strength10.setText("");
            }
        }
        else if(d==1)
        {
            sstrength10=strength10.getText().toString();
            sstrength9=strength9.getText().toString();
            sstrength8=strength8.getText().toString();
            sstrength7=strength7.getText().toString();
            sstrength6=strength6.getText().toString();
            sstrength5=strength5.getText().toString();
            sstrength4=strength4.getText().toString();
            sstrength3=strength3.getText().toString();
            sstrength2=strength2.getText().toString();

            if(sstrength2.length()>1) {
                sstrength1 = sstrength2;
                strength1.setText(sstrength1);
                strength2.setText("");
            }
            if(sstrength3.length()>2) {
                sstrength2 = sstrength3;
                strength2.setText(sstrength2);
                strength3.setText("");
            }
            if(sstrength4.length()>2) {
                sstrength3 = sstrength4;
                strength3.setText(sstrength3);
                strength4.setText("");
            }
            if(sstrength5.length()>2) {
                sstrength4 = sstrength5;
                strength4.setText(sstrength4);
                strength5.setText("");
            }
            if(sstrength6.length()>2) {
                sstrength5 = sstrength6;
                strength5.setText(sstrength5);
                strength6.setText("");
            }
            if(sstrength7.length()>2) {
                sstrength6 = sstrength7;
                strength6.setText(sstrength6);
                strength7.setText("");
            }
            if(sstrength8.length()>2) {
                sstrength7 = sstrength8;
                strength7.setText(sstrength7);
                strength8.setText("");
            }
            if(sstrength9.length()>2) {
                sstrength8 = sstrength9;
                strength8.setText(sstrength8);
                strength9.setText("");
            }
            if(sstrength10.length()>2) {
                sstrength9 = sstrength10;
                strength9.setText(sstrength9);
                strength10.setText("");
            }
        }
    }
    void validateandSave()
    {
        strength1.setError(null);
        strength2.setError(null);
        strength3.setError(null);
        strength4.setError(null);
        strength5.setError(null);
        strength6.setError(null);
        strength7.setError(null);
        strength8.setError(null);
        strength9.setError(null);
        strength10.setError(null);

        sstrength1=strength1.getText().toString();
        sstrength2=strength2.getText().toString();
        sstrength3=strength3.getText().toString();
        sstrength4=strength4.getText().toString();
        sstrength5=strength5.getText().toString();
        sstrength6=strength6.getText().toString();
        sstrength7=strength7.getText().toString();
        sstrength8=strength8.getText().toString();
        sstrength9=strength9.getText().toString();
        sstrength10=strength10.getText().toString();

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag=0;

        if(strengthcount==0)
        {
            if(sstrength1.length()<2)
            {
                errorflag=1;
                strength1.setError("Invalid Strength");
            }
        }
        else if(strengthcount==1)
        {
            if(sstrength1.length()<2)
            {
                errorflag=1;
                strength1.setError("Invalid Strength");
            }
            else
            {
                errorflag=0;
                if(sstrength2.length()<2)
                {
                    errorflag=1;
                    strength2.setError("Invalid Strength");
                }
            }
        }
        else if(strengthcount==2)
        {
            if(sstrength1.length()<2)
            {
                errorflag=1;
                strength1.setError("Invalid Strength");
            }
            else
            {
                errorflag=0;
                if(sstrength2.length()<2)
                {
                    errorflag=1;
                    strength2.setError("Invalid Strength");
                }
                else
                {
                    errorflag=0;
                    if(sstrength3.length()<2)
                    {
                        errorflag=1;
                        strength3.setError("Invalid Strength");
                    }
                }
            }
        }
        else if(strengthcount==3)
        {
            if(sstrength1.length()<2)
            {
                errorflag=1;
                strength1.setError("Invalid Strength");
            }
            else
            {
                errorflag=0;
                if(sstrength2.length()<2)
                {
                    errorflag=1;
                    strength2.setError("Invalid Strength");
                }
                else
                {
                    errorflag=0;
                    if(sstrength3.length()<2)
                    {
                        errorflag=1;
                        strength3.setError("Invalid Strength");
                    }
                    else
                    {
                        errorflag=0;
                        if(sstrength4.length()<2)
                        {
                            errorflag=1;
                            strength4.setError("Invalid Strength");
                        }
                    }
                }
            }
        }
        else if(strengthcount==4)
        {
            if(sstrength1.length()<2)
            {
                errorflag=1;
                strength1.setError("Invalid Strength");
            }
            else
            {
                errorflag=0;
                if(sstrength2.length()<2)
                {
                    errorflag=1;
                    strength2.setError("Invalid Strength");
                }
                else
                {
                    errorflag=0;
                    if(sstrength3.length()<2)
                    {
                        errorflag=1;
                        strength3.setError("Invalid Strength");
                    }
                    else
                    {
                        errorflag=0;
                        if(sstrength4.length()<2)
                        {
                            errorflag=1;
                            strength4.setError("Invalid Strength");
                        }
                        else
                        {
                            errorflag=0;
                            if(sstrength5.length()<2)
                            {
                                errorflag=1;
                                strength5.setError("Invalid Strength");
                            }
                        }
                    }
                }
            }
        }
        else if(strengthcount==5)
        {
            if(sstrength1.length()<2)
            {
                errorflag=1;
                strength1.setError("Invalid Strength");
            }
            else
            {
                errorflag=0;
                if(sstrength2.length()<2)
                {
                    errorflag=1;
                    strength2.setError("Invalid Strength");
                }
                else
                {
                    errorflag=0;
                    if(sstrength3.length()<2)
                    {
                        errorflag=1;
                        strength3.setError("Invalid Strength");
                    }
                    else
                    {
                        errorflag=0;
                        if(sstrength4.length()<2)
                        {
                            errorflag=1;
                            strength4.setError("Invalid Strength");
                        }
                        else
                        {
                            errorflag=0;
                            if(sstrength5.length()<2)
                            {
                                errorflag=1;
                                strength5.setError("Invalid Strength");
                            }
                            else
                            {
                                errorflag=0;
                                if(sstrength6.length()<2)
                                {
                                    errorflag=1;
                                    strength6.setError("Invalid Strength");
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(strengthcount==6)
        {
            if(sstrength1.length()<2)
            {
                errorflag=1;
                strength1.setError("Invalid Strength");
            }
            else
            {
                errorflag=0;
                if(sstrength2.length()<2)
                {
                    errorflag=1;
                    strength2.setError("Invalid Strength");
                }
                else
                {
                    errorflag=0;
                    if(sstrength3.length()<2)
                    {
                        errorflag=1;
                        strength3.setError("Invalid Strength");
                    }
                    else
                    {
                        errorflag=0;
                        if(sstrength4.length()<2)
                        {
                            errorflag=1;
                            strength4.setError("Invalid Strength");
                        }
                        else
                        {
                            errorflag=0;
                            if(sstrength5.length()<2)
                            {
                                errorflag=1;
                                strength5.setError("Invalid Strength");
                            }
                            else
                            {
                                errorflag=0;
                                if(sstrength6.length()<2)
                                {
                                    errorflag=1;
                                    strength6.setError("Invalid Strength");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sstrength7.length()<2)
                                    {
                                        errorflag=1;
                                        strength7.setError("Invalid Strength");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(strengthcount==7)
        {
            if(sstrength1.length()<2)
            {
                errorflag=1;
                strength1.setError("Invalid Strength");
            }
            else
            {
                errorflag=0;
                if(sstrength2.length()<2)
                {
                    errorflag=1;
                    strength2.setError("Invalid Strength");
                }
                else
                {
                    errorflag=0;
                    if(sstrength3.length()<2)
                    {
                        errorflag=1;
                        strength3.setError("Invalid Strength");
                    }
                    else
                    {
                        errorflag=0;
                        if(sstrength4.length()<2)
                        {
                            errorflag=1;
                            strength4.setError("Invalid Strength");
                        }
                        else
                        {
                            errorflag=0;
                            if(sstrength5.length()<2)
                            {
                                errorflag=1;
                                strength5.setError("Invalid Strength");
                            }
                            else
                            {
                                errorflag=0;
                                if(sstrength6.length()<2)
                                {
                                    errorflag=1;
                                    strength6.setError("Invalid Strength");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sstrength7.length()<2)
                                    {
                                        errorflag=1;
                                        strength7.setError("Invalid Strength");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(sstrength8.length()<2)
                                        {
                                            errorflag=1;
                                            strength8.setError("Invalid Strength");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(strengthcount==8)
        {
            if(sstrength1.length()<2)
            {
                errorflag=1;
                strength1.setError("Invalid Strength");
            }
            else
            {
                errorflag=0;
                if(sstrength2.length()<2)
                {
                    errorflag=1;
                    strength2.setError("Invalid Strength");
                }
                else
                {
                    errorflag=0;
                    if(sstrength3.length()<2)
                    {
                        errorflag=1;
                        strength3.setError("Invalid Strength");
                    }
                    else
                    {
                        errorflag=0;
                        if(sstrength4.length()<2)
                        {
                            errorflag=1;
                            strength4.setError("Invalid Strength");
                        }
                        else
                        {
                            errorflag=0;
                            if(sstrength5.length()<2)
                            {
                                errorflag=1;
                                strength5.setError("Invalid Strength");
                            }
                            else
                            {
                                errorflag=0;
                                if(sstrength6.length()<2)
                                {
                                    errorflag=1;
                                    strength6.setError("Invalid Strength");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sstrength7.length()<2)
                                    {
                                        errorflag=1;
                                        strength7.setError("Invalid Strength");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(sstrength8.length()<2)
                                        {
                                            errorflag=1;
                                            strength8.setError("Invalid Strength");
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sstrength9.length()<2)
                                            {
                                                errorflag=1;
                                                strength9.setError("Invalid Strength");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(strengthcount==9)
        {
            if(sstrength1.length()<2)
            {
                errorflag=1;
                strength1.setError("Invalid Strength");
            }
            else
            {
                errorflag=0;
                if(sstrength2.length()<2)
                {
                    errorflag=1;
                    strength2.setError("Invalid Strength");
                }
                else
                {
                    errorflag=0;
                    if(sstrength3.length()<2)
                    {
                        errorflag=1;
                        strength3.setError("Invalid Strength");
                    }
                    else
                    {
                        errorflag=0;
                        if(sstrength4.length()<2)
                        {
                            errorflag=1;
                            strength4.setError("Invalid Strength");
                        }
                        else
                        {
                            errorflag=0;
                            if(sstrength5.length()<2)
                            {
                                errorflag=1;
                                strength5.setError("Invalid Strength");
                            }
                            else
                            {
                                errorflag=0;
                                if(sstrength6.length()<2)
                                {
                                    errorflag=1;
                                    strength6.setError("Invalid Strength");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sstrength7.length()<2)
                                    {
                                        errorflag=1;
                                        strength7.setError("Invalid Strength");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(sstrength8.length()<2)
                                        {
                                            errorflag=1;
                                            strength8.setError("Invalid Strength");
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sstrength9.length()<2)
                                            {
                                                errorflag=1;
                                                strength9.setError("Invalid Strength");
                                            }
                                            else
                                            {
                                                errorflag=0;
                                                if(sstrength10.length()<2)
                                                {
                                                    errorflag=1;
                                                    strength10.setError("Invalid Strength");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(errorflag==0) {
            try
            {


                MyProfileStrengthsModal obj2 = new MyProfileStrengthsModal(sstrength1,sstrength2,sstrength3,sstrength4,sstrength5,sstrength6,sstrength7,sstrength8,sstrength9,sstrength10);


                encobj =OtoString(obj2,MySharedPreferencesManager.getDigest1(MyProfileStrengths.this),MySharedPreferencesManager.getDigest2(MyProfileStrengths.this));


                Log.d("TAG", "validateandSave: encobj - "+encobj);


                new SaveStrengths().execute();

            }catch (Exception e){Toast.makeText(MyProfileStrengths.this,e.getMessage(),Toast.LENGTH_LONG).show();}
        }
    }
    class SaveStrengths extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("t1",encobj));  //1

            json = jParser.makeHttpRequest(MyConstants.url_savestrengths, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(MyProfileStrengths.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();

                ProfileRole r=new ProfileRole();
                String role=r.getRole();
                if(role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);


                s.setStrength1(sstrength1);
                s.setStrength2(sstrength2);
                s.setStrength3(sstrength3);
                s.setStrength4(sstrength4);
                s.setStrength5(sstrength5);
                s.setStrength6(sstrength6);
                s.setStrength7(sstrength7);
                s.setStrength8(sstrength8);
                s.setStrength9(sstrength9);
                s.setStrength10(sstrength10);

                MyProfileStrengths.super.onBackPressed();
            }
            else
                Toast.makeText(MyProfileStrengths.this,result,Toast.LENGTH_SHORT).show();

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
        if(edittedFlag!=0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfileStrengths.super.onBackPressed();
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
            MyProfileStrengths.super.onBackPressed();
    }
}
