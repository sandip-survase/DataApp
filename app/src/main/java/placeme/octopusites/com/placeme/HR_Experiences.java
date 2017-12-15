package placeme.octopusites.com.placeme;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.support.design.widget.TextInputEditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.HrCompanyDetails.HRlog;
import static placeme.octopusites.com.placeme.JSONParser.json;

public class HR_Experiences extends AppCompatActivity {

    int d = 0, expcount = 0;
    int edittedFlagexp = 0;
    int edittedFlag=0;

    View addmoreexp;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    String digest1, digest2;
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview, trash6selectionview, trash7selectionview, trash8selectionview, trash9selectionview, trash10selectionview;
    TextInputEditText fromdate1, todate1, fromdate2, todate2, fromdate3, todate3, fromdate4, todate4, fromdate5, todate5, fromdate6, todate6, fromdate7, todate7, fromdate8, todate8, fromdate9, todate9, fromdate10, todate10;
    TextInputEditText post1, post2, post3, post4, post5, post6, post7, post8, post9, post10;
    TextInputEditText inst11, inst12, inst13, inst14, inst15, inst16, inst17, inst18, inst19, inst110;
    String posts1 = "", posts2 = "", posts3 = "", posts4 = "", posts5 = "", posts6 = "", posts7 = "", posts8 = "", posts9 = "", posts10 = "";
    String inst1s1 = "", inst1s2 = "", inst1s3 = "", inst1s4 = "", inst1s5 = "", inst1s6 = "", inst1s7 = "", inst1s8 = "", inst1s9 = "", inst1s10 = "";
    String fromdates1 = "", todates1 = "", fromdates2 = "", todates2 = "", fromdates3 = "", todates3 = "", fromdates4 = "", todates4 = "", fromdates5 = "", todates5 = "", fromdates6 = "", todates6 = "", fromdates7 = "", todates7 = "", fromdates8 = "", todates8 = "", fromdates9 = "", todates9 = "", fromdates10 = "", todates10 = "";
    String encpost1 = "", encpost2 = "", encpost3 = "", encpost4 = "", encpost5 = "", encpost6 = "", encpost7 = "", encpost8 = "", encpost9 = "", encpost10 = "";
    String encinst1 = "", encinst2 = "", encinst3 = "", encinst4 = "", encinst5 = "", encinst6 = "", encinst7 = "", encinst8 = "", encinst9 = "", encinst10 = "";
    String encfromdate1 = "", encfromdate2 = "", encfromdate3 = "", encfromdate4 = "", encfromdate5 = "", encfromdate6 = "", encfromdate7 = "", encfromdate8 = "", encfromdate9 = "", encfromdate10 = "";
    String enctodate1 = "", enctodate2 = "", enctodate3 = "", enctodate4 = "", enctodate5 = "", enctodate6 = "", enctodate7 = "", enctodate8 = "", enctodate9 = "", enctodate10 = "";

    String status = "false";
    SwitchCompat switch1, switch2, switch3, switch4, switch5, switch6, switch7, switch8, switch9, switch10;
    boolean blnswitch1, blnswitch2, blnswitch3, blnswitch4, blnswitch5, blnswitch6, blnswitch7, blnswitch8, blnswitch9, blnswitch10;
    private String username = "";
    //    private final String url_saveHrExperience = "http://192.168.100.10/AESTest/SaveAlumniExperience";
    int errorflag = 0;
    byte[] demoKeyBytes, demoIVBytes;
    String sPadding,role;
    HrData hr = new HrData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_experiences);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Experiences");
        ab.setDisplayHomeAsUpEnabled(true);


        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);

        demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        demoIVBytes = SimpleBase64Encoder.decode(digest2);
        sPadding = "ISO10126Padding";

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        //my code
        trash1selectionview = (View) findViewById(R.id.trashexp1);
        trash2selectionview = (View) findViewById(R.id.trashexp2);
        trash3selectionview = (View) findViewById(R.id.trashexp3);
        trash4selectionview = (View) findViewById(R.id.trashexp4);
        trash5selectionview = (View) findViewById(R.id.trashexp5);
        trash6selectionview = (View) findViewById(R.id.trashexp6);
        trash7selectionview = (View) findViewById(R.id.trashexp7);
        trash8selectionview = (View) findViewById(R.id.trashexp8);
        trash9selectionview = (View) findViewById(R.id.trashexp9);
        trash10selectionview = (View) findViewById(R.id.trashexp10);


        //TextInputEditText
        post1 = (TextInputEditText) findViewById(R.id.post1);
        post2 = (TextInputEditText) findViewById(R.id.post2);
        post3 = (TextInputEditText) findViewById(R.id.post3);
        post4 = (TextInputEditText) findViewById(R.id.post4);
        post5 = (TextInputEditText) findViewById(R.id.post5);
        post6 = (TextInputEditText) findViewById(R.id.post6);
        post7 = (TextInputEditText) findViewById(R.id.post7);
        post8 = (TextInputEditText) findViewById(R.id.post8);
        post9 = (TextInputEditText) findViewById(R.id.post9);
        post10 = (TextInputEditText) findViewById(R.id.post10);

        inst11 = (TextInputEditText) findViewById(R.id.inst1);
        inst12 = (TextInputEditText) findViewById(R.id.inst2);
        inst13 = (TextInputEditText) findViewById(R.id.inst3);
        inst14 = (TextInputEditText) findViewById(R.id.inst4);
        inst15 = (TextInputEditText) findViewById(R.id.inst5);
        inst16 = (TextInputEditText) findViewById(R.id.inst6);
        inst17 = (TextInputEditText) findViewById(R.id.inst7);
        inst18 = (TextInputEditText) findViewById(R.id.inst8);
        inst19 = (TextInputEditText) findViewById(R.id.inst9);
        inst110 = (TextInputEditText) findViewById(R.id.inst10);

        switch1 = (SwitchCompat) findViewById(R.id.switch1);
        switch2 = (SwitchCompat) findViewById(R.id.switch2);
        switch3 = (SwitchCompat) findViewById(R.id.switch3);
        switch4 = (SwitchCompat) findViewById(R.id.switch4);
        switch5 = (SwitchCompat) findViewById(R.id.switch5);
        switch6 = (SwitchCompat) findViewById(R.id.switch6);
        switch7 = (SwitchCompat) findViewById(R.id.switch7);
        switch8 = (SwitchCompat) findViewById(R.id.switch8);
        switch9 = (SwitchCompat) findViewById(R.id.switch9);
        switch10 = (SwitchCompat) findViewById(R.id.switch10);

        switch1.setChecked(false);
//        boolean status = switch1.isChecked();
//
//        Log.d(HRlog,"status -"+status);
//        Log.d(HRlog,"switch -"+switch1);
//        Log.d(HRlog,"post -"+post1);


        fromdate1 = (TextInputEditText) findViewById(R.id.fromdate1);
        todate1 = (TextInputEditText) findViewById(R.id.todate1);
        fromdate2 = (TextInputEditText) findViewById(R.id.fromdate2);
        todate2 = (TextInputEditText) findViewById(R.id.todate2);
        fromdate3 = (TextInputEditText) findViewById(R.id.fromdate3);
        todate3 = (TextInputEditText) findViewById(R.id.todate3);
        fromdate4 = (TextInputEditText) findViewById(R.id.fromdate4);
        todate4 = (TextInputEditText) findViewById(R.id.todate4);
        fromdate5 = (TextInputEditText) findViewById(R.id.fromdate5);
        todate5 = (TextInputEditText) findViewById(R.id.todate5);
        fromdate6 = (TextInputEditText) findViewById(R.id.fromdate6);
        todate6 = (TextInputEditText) findViewById(R.id.todate6);
        fromdate7 = (TextInputEditText) findViewById(R.id.fromdate7);
        todate7 = (TextInputEditText) findViewById(R.id.todate7);
        fromdate8 = (TextInputEditText) findViewById(R.id.fromdate8);
        todate8 = (TextInputEditText) findViewById(R.id.todate8);
        fromdate9 = (TextInputEditText) findViewById(R.id.fromdate9);
        todate9 = (TextInputEditText) findViewById(R.id.todate9);
        fromdate10 = (TextInputEditText) findViewById(R.id.fromdate10);
        todate10 = (TextInputEditText) findViewById(R.id.todate10);

        TextView exptxt = (TextView) findViewById(R.id.exptxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/arba.ttf");
        exptxt.setTypeface(custom_font1);
        //my code
        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 1;
                showDeletDialog();
                Log.d(HRlog, "u click on 1 tarsh" + trash1selectionview);
            }
        });
        trash2selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 2;
                showDeletDialog();

            }
        });
        trash3selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 3;
                showDeletDialog();
            }
        });
        trash4selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 4;
                showDeletDialog();
            }
        });
        trash5selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 5;
                showDeletDialog();
            }
        });
        trash6selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 6;
                showDeletDialog();
            }
        });
        trash7selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 7;
                showDeletDialog();
            }
        });
        trash8selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 8;
                showDeletDialog();
            }
        });
        trash9selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 9;
                showDeletDialog();
            }
        });
        trash10selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 10;
                showDeletDialog();
            }
        });
        //

        posts1 = hr.getPosts1();
        posts2 = hr.getPosts2();
        posts3 = hr.getPosts3();
        posts4 = hr.getPosts4();
        posts5 = hr.getPosts5();
        posts6 = hr.getPosts6();
        posts7 = hr.getPosts7();
        posts8 = hr.getPosts8();
        posts9 = hr.getPosts9();
        posts10 = hr.getPosts10();

        inst1s1 = hr.getInst1s1();
        inst1s2 = hr.getInst1s2();

        inst1s3 = hr.getInst1s3();
        inst1s4 = hr.getInst1s4();
        inst1s5 = hr.getInst1s5();
        inst1s6 = hr.getInst1s6();

        inst1s7 = hr.getInst1s7();
        inst1s8 = hr.getInst1s8();
        inst1s9 = hr.getInst1s9();
        inst1s10 = hr.getInst1s10();

        fromdates1 = hr.getFromdates1();
        fromdates2 = hr.getFromdates2();
        fromdates3 = hr.getFromdates3();
        fromdates4 = hr.getFromdates4();
        fromdates5 = hr.getFromdates5();
        fromdates6 = hr.getFromdates6();


        fromdates7 = hr.getFromdates7();
        fromdates8 = hr.getFromdates8();
        fromdates9 = hr.getFromdates9();
        fromdates10 = hr.getFromdates10();

        todates1 = hr.getTodates1();
        todates2 = hr.getTodates2();
        todates3 = hr.getTodates3();
        todates4 = hr.getTodates4();
        todates5 = hr.getTodates5();
        todates6 = hr.getTodates6();
        todates7 = hr.getTodates7();
        todates8 = hr.getTodates8();
        todates9 = hr.getTodates9();
        todates10 = hr.getTodates10();









        fromdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // 1- id   2- isFromDateSelected 3-fromYear   4- month in str

                String toDate = todate1.getText().toString();
                showDateDialog(fromdate1, false, 0, "", toDate);


            }
        });
        todate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate1.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate1.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate1, isFromDateSelected, fromYear, fromMonth, "");

            }
        });
        fromdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate2.getText().toString();
                showDateDialog(fromdate2, false, 0, "", toDate);


            }
        });
        todate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate2.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate2.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate2, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate3.getText().toString();
                showDateDialog(fromdate3, false, 0, "", toDate);


            }
        });
        todate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate3.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate3.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate3, isFromDateSelected, fromYear, fromMonth, "");
            }

        });
        fromdate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate4.getText().toString();
                showDateDialog(fromdate4, false, 0, "", toDate);


            }
        });
        todate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate4.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate4.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate4, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate5.getText().toString();
                showDateDialog(fromdate5, false, 0, "", toDate);

            }
        });
        todate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate5.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate5.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate5, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate6.getText().toString();
                showDateDialog(fromdate6, false, 0, "", toDate);

            }
        });
        todate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate6.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate6.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate6, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate7.getText().toString();
                showDateDialog(fromdate7, false, 0, "", toDate);

            }
        });
        todate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate7.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate7.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate7, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate8.getText().toString();
                showDateDialog(fromdate8, false, 0, "", toDate);

            }
        });
        todate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate8.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate8.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate8, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate9.getText().toString();
                showDateDialog(fromdate9, false, 0, "", toDate);

            }
        });
        todate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate9.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate9.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate9, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate10.getText().toString();
                showDateDialog(fromdate10, false, 0, "", toDate);

            }
        });
        todate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate10.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate10.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate10, isFromDateSelected, fromYear, fromMonth, "");
            }
        });


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //status="true"; //edit here
                    blnswitch1 = true;
                    View v = (View) findViewById(R.id.todaterl1);
                    todate1.setText("");
                    v.setVisibility(View.INVISIBLE);

                    Log.d(HRlog, "blnswitch staus check- " + blnswitch1);
                } else {
                    //status="false";
                    blnswitch1 = false;
                    View v = (View) findViewById(R.id.todaterl1);
                    v.setVisibility(View.VISIBLE);
                    Log.d(HRlog, "blnswitch Status -" + blnswitch1);
                }

            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //status="true"; //edit here
                    blnswitch2 = true;
                    View v = (View) findViewById(R.id.todaterl2);
                    todate2.setText("");
                    v.setVisibility(View.INVISIBLE);
                } else {
                    //status="false";
                    blnswitch2 = false;
                    View v = (View) findViewById(R.id.todaterl2);
                    v.setVisibility(View.VISIBLE);
                }

            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //status="true"; //edit here
                    blnswitch3 = true;
                    View v = (View) findViewById(R.id.todaterl3);
                    todate3.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View) findViewById(R.id.todaterl3);
                    v.setVisibility(View.VISIBLE);
                    blnswitch3 = false;
                }

            }
        });
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //status="true"; //edit here
                    blnswitch4 = true;
                    View v = (View) findViewById(R.id.todaterl4);
                    todate4.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View) findViewById(R.id.todaterl4);
                    v.setVisibility(View.VISIBLE);
                    blnswitch4 = false;
                }

            }
        });
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //status="true"; //edit here
                    blnswitch5 = true;
                    View v = (View) findViewById(R.id.todaterl5);
                    todate5.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View) findViewById(R.id.todaterl5);
                    v.setVisibility(View.VISIBLE);
                    blnswitch5 = false;
                }

            }
        });
        switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //status="true"; //edit here
                    blnswitch6 = true;
                    View v = (View) findViewById(R.id.todaterl6);
                    todate6.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View) findViewById(R.id.todaterl6);
                    v.setVisibility(View.VISIBLE);
                    blnswitch6 = false;
                }

            }
        });
        switch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //status="true"; //edit here
                    blnswitch7 = true;
                    View v = (View) findViewById(R.id.todaterl7);
                    todate7.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View) findViewById(R.id.todaterl7);
                    v.setVisibility(View.VISIBLE);
                    blnswitch7 = false;
                }

            }
        });
        switch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //status="true"; //edit here
                    blnswitch8 = true;
                    View v = (View) findViewById(R.id.todaterl8);
                    todate8.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View) findViewById(R.id.todaterl8);
                    v.setVisibility(View.VISIBLE);
                    blnswitch8 = false;
                }

            }
        });
        switch9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //status="true"; //edit here
                    blnswitch9 = true;
                    View v = (View) findViewById(R.id.todaterl9);
                    todate9.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View) findViewById(R.id.todaterl9);
                    v.setVisibility(View.VISIBLE);
                    blnswitch9 = false;
                }

            }
        });
        switch10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //status="true"; //edit here
                    blnswitch10 = true;
                    View v = (View) findViewById(R.id.todaterl10);
                    todate10.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View) findViewById(R.id.todaterl10);
                    v.setVisibility(View.VISIBLE);
                    blnswitch10 = false;
                }

            }
        });
        //

        //fromdate
        fromdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // 1- id   2- isFromDateSelected 3-fromYear   4- month in str

                String toDate = todate1.getText().toString();
                showDateDialog(fromdate1, false, 0, "", toDate);


            }
        });
        todate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate1.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate1.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate1, isFromDateSelected, fromYear, fromMonth, "");

            }
        });
        fromdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate2.getText().toString();
                showDateDialog(fromdate2, false, 0, "", toDate);


            }
        });
        todate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate2.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate2.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate2, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate3.getText().toString();
                showDateDialog(fromdate3, false, 0, "", toDate);


            }
        });
        todate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate3.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate3.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate3, isFromDateSelected, fromYear, fromMonth, "");
            }

        });
        fromdate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate4.getText().toString();
                showDateDialog(fromdate4, false, 0, "", toDate);


            }
        });
        todate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate4.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate4.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate4, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate5.getText().toString();
                showDateDialog(fromdate5, false, 0, "", toDate);

            }
        });
        todate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate5.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate5.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate5, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate6.getText().toString();
                showDateDialog(fromdate6, false, 0, "", toDate);

            }
        });
        todate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate6.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate6.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate6, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate7.getText().toString();
                showDateDialog(fromdate7, false, 0, "", toDate);

            }
        });
        todate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate7.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate7.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate7, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate8.getText().toString();
                showDateDialog(fromdate8, false, 0, "", toDate);

            }
        });
        todate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate8.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate8.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate8, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate9.getText().toString();
                showDateDialog(fromdate9, false, 0, "", toDate);

            }
        });
        todate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate9.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate9.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate9, isFromDateSelected, fromYear, fromMonth, "");
            }
        });
        fromdate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = todate10.getText().toString();
                showDateDialog(fromdate10, false, 0, "", toDate);

            }
        });
        todate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!fromdate10.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = fromdate10.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(todate10, isFromDateSelected, fromYear, fromMonth, "");
            }
        });


        Log.d("alumni", "onCreate: 3 -"+edittedFlagexp);


        // if posts1 is not equal ""  i.e  inst,fromdate,todate has some value from hr object
        if (!posts1.equals("")) {
            post1.setText(posts1);
            inst11.setText(inst1s1);
            fromdate1.setText(fromdates1);
            if (!todates1.equals(""))
                todate1.setText(todates1);
            else {
                switch1.setChecked(true);
            }
        }
        Log.d("alumni", "onCreate: after 3 -"+edittedFlagexp);


        if (!posts2.equals("")) {
            post2.setText(posts2);
            inst12.setText(inst1s2);
            fromdate2.setText(fromdates2);
            if (!todates2.equals(""))
                todate2.setText(todates2);
            else {
                switch2.setChecked(true);
            }

            View v = (View) findViewById(R.id.expline1);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl2);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }

        if (!posts3.equals("")) {
            post3.setText(posts3);
            inst13.setText(inst1s3);
            fromdate3.setText(fromdates3);
            if (!todates3.equals(""))
                todate3.setText(todates3);
            else {
                switch3.setChecked(true);
            }
            View v = (View) findViewById(R.id.expline2);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl3);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }
        if (!posts4.equals("")) {
            post4.setText(posts4);
            inst14.setText(inst1s4);
            fromdate4.setText(fromdates4);
            if (!todates4.equals(""))
                todate4.setText(todates4);
            else {
                switch4.setChecked(true);
            }
            View v = (View) findViewById(R.id.expline3);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl4);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }
        if (!posts5.equals("")) {
            post5.setText(posts5);
            inst15.setText(inst1s5);
            fromdate5.setText(fromdates5);
            if (!todates5.equals(""))
                todate5.setText(todates5);
            else {
                switch5.setChecked(true);
            }
            View v = (View) findViewById(R.id.expline4);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl5);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }
        Log.d("alumni", "onCreate: 2 -"+edittedFlagexp);

        if (!posts6.equals("")) {
            post6.setText(posts6);
            inst16.setText(inst1s6);
            fromdate6.setText(fromdates6);
            if (!todates6.equals(""))
                todate6.setText(todates6);
            else {
                switch6.setChecked(true);
            }
            View v = (View) findViewById(R.id.expline5);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl6);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }
        if (!posts7.equals("")) {
            post7.setText(posts7);
            inst17.setText(inst1s7);
            fromdate7.setText(fromdates7);
            if (!todates7.equals(""))
                todate7.setText(todates7);
            else {
                switch7.setChecked(true);
            }
            View v = (View) findViewById(R.id.expline6);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl7);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }
        if (!posts8.equals("")) {
            post8.setText(posts8);
            inst18.setText(inst1s8);
            fromdate8.setText(fromdates8);
            if (!todates8.equals(""))
                todate8.setText(todates8);
            else {
                switch8.setChecked(true);
            }
            View v = (View) findViewById(R.id.expline7);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl8);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }
        if (!posts9.equals("")) {
            post9.setText(posts9);
            inst19.setText(inst1s9);
            fromdate9.setText(fromdates9);
            if (!todates9.equals(""))
                todate9.setText(todates9);
            else {
                switch9.setChecked(true);
            }
            View v = (View) findViewById(R.id.expline8);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl9);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }
        if (!posts10.equals("")) {
            post10.setText(posts10);
            inst110.setText(inst1s10);
            fromdate10.setText(fromdates10);
            if (!todates10.equals(""))
                todate10.setText(todates10);
            else {
                switch10.setChecked(true);
            }
            View v = (View) findViewById(R.id.expline9);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl10);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }
        addmoreexp = (View) findViewById(R.id.addmoreexp);
        addmoreexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expcount == 0) {
                    View v = (View) findViewById(R.id.expline1);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl2);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    expcount++;
                } else if (expcount == 1) {
                    View v = (View) findViewById(R.id.expline2);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl3);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    expcount++;
                } else if (expcount == 2) {
                    View v = (View) findViewById(R.id.expline3);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl4);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    expcount++;
                } else if (expcount == 3) {
                    View v = (View) findViewById(R.id.expline4);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl5);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    expcount++;
                } else if (expcount == 4) {
                    View v = (View) findViewById(R.id.expline5);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl6);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    expcount++;
                } else if (expcount == 5) {
                    View v = (View) findViewById(R.id.expline6);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl7);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    expcount++;
                } else if (expcount == 6) {
                    View v = (View) findViewById(R.id.expline7);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl8);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    expcount++;
                } else if (expcount == 7) {
                    View v = (View) findViewById(R.id.expline8);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl9);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    expcount++;
                } else if (expcount == 8) {
                    View v = (View) findViewById(R.id.expline9);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl10);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    expcount++;
                    TextView t = (TextView) findViewById(R.id.addmoreexptxt);
                    ImageView i = (ImageView) findViewById(R.id.addmoreexpimg);

                    View line10 = (View) findViewById(R.id.expline10);
                    line10.setVisibility(View.GONE);
                    addmoreexp.setVisibility(View.GONE);

                    t.setVisibility(View.GONE);
                    i.setVisibility(View.GONE);
                }

            }
        });

        post1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post1.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Log.d("alumni", "onCreate: post 1 "+edittedFlagexp);

        post2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post2.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        post3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post3.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        post4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post4.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        post5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post5.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        post6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post6.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        post7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post7.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        post8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post8.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        post9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post9.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        post10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post10.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        inst11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inst11.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inst12.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst13.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inst13.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst14.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inst14.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst15.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inst15.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst16.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inst16.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst17.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inst17.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst18.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inst18.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst19.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inst19.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inst110.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inst110.setError(null);
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        fromdate1
        fromdate1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fromdate2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fromdate3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fromdate4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fromdate5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fromdate6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fromdate7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fromdate8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fromdate9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fromdate10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        todate1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        todate2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        todate3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        todate4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        todate5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        todate6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        todate7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        todate8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        todate9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        todate10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlagexp = 2;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });








        Log.d("alumni", "onCreate: "+edittedFlagexp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                validateAndSaveData();
                break;

            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Log.d("alumni",""+edittedFlagexp);

        if(edittedFlagexp==2) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    HR_Experiences.super.onBackPressed();
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
            HR_Experiences.super.onBackPressed();

    }

    void showDateDialog(final TextInputEditText id, boolean isFromDateSelected, final int fromYear, final String fromMonth, final String todate) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(HR_Experiences.this);
        LayoutInflater inflater = HR_Experiences.this.getLayoutInflater();
        View dialog = inflater.inflate(R.layout.monthyeardialog, null);
        dialogBuilder.setView(dialog);

        final WheelView monthView, yearView;

        final List<String> monthList = new ArrayList<String>();
        final List<String> yearList = new ArrayList<String>();

        monthView = (WheelView) dialog.findViewById(R.id.monthwheel);
        yearView = (WheelView) dialog.findViewById(R.id.yearwheel);

        monthList.add("Jan");
        monthList.add("Feb");
        monthList.add("Mar");
        monthList.add("Apr");
        monthList.add("May");
        monthList.add("Jun");
        monthList.add("Jul");
        monthList.add("Aug");
        monthList.add("Sep");
        monthList.add("Oct");
        monthList.add("Nov");
        monthList.add("Dec");

        if (isFromDateSelected == true && fromYear != 0 && !fromMonth.equals("")) {        // custum year list if from date is already set
            Calendar calendar = Calendar.getInstance();
            yearList.clear();
            for (int i = fromYear; i <= calendar.get(Calendar.YEAR); i++) {
                yearList.add(i + "");
            }


        } else if (!todate.equals("")) {       // custum year list if todate is already set
            String[] splited = todate.split(", ");
            int toYear = 0;
            if (splited.length == 2) {
                toYear = Integer.parseInt(splited[1]);
                yearList.clear();
                for (int i = 1970; i <= toYear; i++) {
                    yearList.add(i + "");
                }
            }


        } else {                              // normal year list 1970 to current year
//            yearList.clear();
//            yearList.add("2000");
//            yearList.add("2001");
//            yearList.add("2002");
//            yearList.add("2003");
//            yearList.add("2004");
//            yearList.add("2005");
//            yearList.add("2006");
//            yearList.add("2007");
//            yearList.add("2008");
//            yearList.add("2009");
//            yearList.add("2010");
//            yearList.add("2011");
//            yearList.add("2012");
//            yearList.add("2013");
//            yearList.add("2014");
//            yearList.add("2015");
//            yearList.add("2016");

            Calendar calendar = Calendar.getInstance();
            yearList.clear();
            for (int i = 1970; i <= calendar.get(Calendar.YEAR); i++) {
                yearList.add(i + "");
            }


        }

        monthView.setWheelAdapter(new ArrayWheelAdapter(HR_Experiences.this));
        monthView.setWheelData(monthList);
        yearView.setWheelAdapter(new ArrayWheelAdapter(HR_Experiences.this));
        yearView.setWheelData(yearList);


        TextView set = (TextView) dialog.findViewById(R.id.set);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);

        final AlertDialog alertDialog = dialogBuilder.create();


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int monthPosition = monthView.getCurrentPosition();
                int yearPosition = yearView.getCurrentPosition();
                boolean isvalid = true;

                String selectedMonth = monthList.get(monthPosition);
                String selectedYear = yearList.get(yearPosition);
                Calendar cal = Calendar.getInstance();
                int current_month_pos = cal.get(Calendar.MONTH);
                int current_year = cal.get(Calendar.YEAR);

                if (!fromMonth.equals("")) {        //if first from date set
                    int fromMonthInt = monthList.indexOf(fromMonth);
                    if (selectedYear.equals(String.valueOf(fromYear))) {

                        if (fromMonthInt >= monthPosition) {
                            isvalid = false;
                        }
                    }
                }

                if (!todate.equals("")) {       // if first todate set
                    int toYear = 0;
                    int toMonthInt = 0;
                    String toMonth = "";

                    String[] splited = todate.split(", ");
                    if (splited.length == 2) {
                        toMonth = splited[0];
                        toYear = Integer.parseInt(splited[1]);

                        if (toYear < Integer.parseInt(selectedYear)) {
                            isvalid = false;
                        }
                        if (toYear == Integer.parseInt(selectedYear)) {
                            toMonthInt = monthList.indexOf(toMonth);
                            if (monthPosition >= toMonthInt) {
                                isvalid = false;
                            }
                        }
                    }

                }
                // invalid future date
                if (current_month_pos < monthPosition && current_year == Integer.parseInt(selectedYear)) {
                    isvalid = false;
                }

                setMonthYear(id, selectedMonth, selectedYear, isvalid);
                alertDialog.cancel();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.cancel();
            }
        });

        alertDialog.show();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        double ratio = (double) height / (double) width;
        double bestDelta = Double.MAX_VALUE;
        int bestI = 0;
        int bestJ = 0;

        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                double newDelta = Math.abs((double) i / (double) j - ratio);
                if (newDelta < bestDelta) {
                    bestDelta = newDelta;
                    bestI = i;
                    bestJ = j;
                }
            }
        }
        if (bestI == 16 && bestJ == 9) {
            alertDialog.getWindow().setLayout(width / 2, height / 3);
        } else if (bestI == 98 && bestJ == 59) {
            alertDialog.getWindow().setLayout(width / 2, height / 3);
        } else if (bestI == 5 && bestJ == 3) {
            int newh = Math.round(height / 2.5f);
            int neww = Math.round(height / 2.3f);
            alertDialog.getWindow().setLayout(neww, newh);
        }

    }

    void setMonthYear(TextInputEditText id, String selectedMonth, String selectedYear, boolean isValid) {
        id.setError(null);
        if (isValid == true) {
            id.setText(selectedMonth + ", " + selectedYear);
        } else {
            id.setError("Choose valid date");
            Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
            id.setText("");
        }
    }

    void showDeletDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this project ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag = 2;
                                deleteExp();
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

    void deleteExp() {
        View v = (View) findViewById(R.id.expline9);
        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View) findViewById(R.id.expline9);
            v1.setVisibility(View.GONE);

            RelativeLayout langrl = (RelativeLayout) findViewById(R.id.exprl10);
            langrl.setVisibility(View.GONE);

            expcount--;

            TextView t = (TextView) findViewById(R.id.addmoreexptxt);
            ImageView i = (ImageView) findViewById(R.id.addmoreexpimg);
            addmoreexp.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        } else {
            v = (View) findViewById(R.id.expline8);
            if (v.getVisibility() == View.VISIBLE) {


                View v1 = (View) findViewById(R.id.expline8);
                v1.setVisibility(View.GONE);

                RelativeLayout langrl = (RelativeLayout) findViewById(R.id.exprl9);
                langrl.setVisibility(View.GONE);

                expcount--;

            } else {
                v = (View) findViewById(R.id.expline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View) findViewById(R.id.expline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout langrl = (RelativeLayout) findViewById(R.id.exprl8);
                    langrl.setVisibility(View.GONE);

                    expcount--;
                } else {
                    v = (View) findViewById(R.id.expline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View) findViewById(R.id.expline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout langrl = (RelativeLayout) findViewById(R.id.exprl7);
                        langrl.setVisibility(View.GONE);

                        expcount--;

                    } else {
                        v = (View) findViewById(R.id.expline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) findViewById(R.id.expline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout langrl = (RelativeLayout) findViewById(R.id.exprl6);
                            langrl.setVisibility(View.GONE);

                            expcount--;


                        } else {
                            v = (View) findViewById(R.id.expline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) findViewById(R.id.expline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout langrl = (RelativeLayout) findViewById(R.id.exprl5);
                                langrl.setVisibility(View.GONE);

                                expcount--;
                            } else {
                                v = (View) findViewById(R.id.expline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) findViewById(R.id.expline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout langrl = (RelativeLayout) findViewById(R.id.exprl4);
                                    langrl.setVisibility(View.GONE);

                                    expcount--;

                                } else {
                                    v = (View) findViewById(R.id.expline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) findViewById(R.id.expline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout langrl = (RelativeLayout) findViewById(R.id.exprl3);
                                        langrl.setVisibility(View.GONE);

                                        expcount--;

                                    } else {
                                        v = (View) findViewById(R.id.expline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1 = (View) findViewById(R.id.expline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout langrl = (RelativeLayout) findViewById(R.id.exprl2);
                                            langrl.setVisibility(View.GONE);

                                            expcount--;


                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        if (d == 10) {
            post10.setText("");
            inst110.setText("");
            fromdate10.setText("");
            todate10.setText("");
            switch10.setChecked(false);

        } else if (d == 9) {
            posts10 = post10.getText().toString();
            inst1s10 = inst110.getText().toString();
            fromdates10 = fromdate10.getText().toString();
            todates10 = todate10.getText().toString();
            blnswitch10 = switch10.isChecked();
            Log.d(HRlog, "bln " + blnswitch10);

            posts9 = posts10;
            inst1s9 = inst1s10;
            fromdates9 = fromdates10;
            todates9 = todates10;
            blnswitch9 = blnswitch10;

            post9.setText(posts10);
            inst19.setText(inst1s10);
            fromdate9.setText(fromdates10);
            todate9.setText(todates10);
            switch9.setChecked(blnswitch10);

            post10.setText("");
            inst110.setText("");
            fromdate10.setText("");
            todate10.setText("");
            switch10.setChecked(false);


            boolean blnswitch101 = switch9.isChecked();
            Log.d(HRlog, "bln swirtch 10 -" + blnswitch10);
            Log.d(HRlog, "after delete - " + blnswitch101);
            // you are here........................................

        } else if (d == 8) {
            posts10 = post10.getText().toString();
            inst1s10 = inst110.getText().toString();
            fromdates10 = fromdate10.getText().toString();
            todates10 = todate10.getText().toString();
            blnswitch10 = switch10.isChecked();


            posts9 = post9.getText().toString();
            inst1s9 = inst19.getText().toString();
            fromdates9 = fromdate9.getText().toString();
            todates9 = todate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            post8.setText(posts9);
            inst18.setText(inst1s9);
            fromdate8.setText(fromdates9);
            todate8.setText(todates9);
            switch8.setChecked(blnswitch9);


            post9.setText("");
            inst19.setText("");
            fromdate9.setText("");
            todate9.setText("");
            switch9.setChecked(false);
            //set value 8

            posts9 = posts10;
            inst1s9 = inst1s10;
            fromdates9 = fromdates10;
            todates9 = todates10;

            post9.setText(posts10);
            inst19.setText(inst1s10);
            fromdate9.setText(fromdates10);
            todate9.setText(todates10);
            switch9.setChecked(blnswitch10);

            post10.setText("");
            inst110.setText("");
            fromdate10.setText("");
            todate10.setText("");
            switch10.setChecked(false);
            //set value 9

            // you are here........................................
        } else if (d == 7) {
            posts10 = post10.getText().toString();
            inst1s10 = inst110.getText().toString();
            fromdates10 = fromdate10.getText().toString();
            todates10 = todate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            posts9 = post9.getText().toString();
            inst1s9 = inst19.getText().toString();
            fromdates9 = fromdate9.getText().toString();
            todates9 = todate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            posts8 = post8.getText().toString();
            inst1s8 = inst18.getText().toString();
            fromdates8 = fromdate8.getText().toString();
            todates8 = todate8.getText().toString();
            blnswitch8 = switch8.isChecked();


            post7.setText(posts8);
            inst17.setText(inst1s8);
            fromdate7.setText(fromdates8);
            todate7.setText(todates8);
            switch7.setChecked(blnswitch8);

            post8.setText("");
            inst18.setText("");
            fromdate8.setText("");
            todate8.setText("");
            switch8.setChecked(false);

            //set value 8
            post8.setText(posts9);
            inst18.setText(inst1s9);
            fromdate8.setText(fromdates9);
            todate8.setText(todates9);
            switch8.setChecked(blnswitch9);

            post9.setText("");
            inst19.setText("");
            fromdate9.setText("");
            todate9.setText("");
            switch9.setChecked(false);


            posts9 = posts10;
            inst1s9 = inst1s10;
            fromdates9 = fromdates10;
            todates9 = todates10;

            post9.setText(posts10);
            inst19.setText(inst1s10);
            fromdate9.setText(fromdates10);
            todate9.setText(todates10);
            switch9.setChecked(blnswitch10);

            post10.setText("");
            inst110.setText("");
            fromdate10.setText("");
            todate10.setText("");
            switch10.setChecked(false);
            //set value 9

            // you are here........................................
        } else if (d == 6) {

            posts7 = post7.getText().toString();
            inst1s7 = inst17.getText().toString();
            fromdates7 = fromdate7.getText().toString();
            todates7 = todate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            posts8 = post8.getText().toString();
            inst1s8 = inst18.getText().toString();
            fromdates8 = fromdate8.getText().toString();
            todates8 = todate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            posts9 = post9.getText().toString();
            inst1s9 = inst19.getText().toString();
            fromdates9 = fromdate9.getText().toString();
            todates9 = todate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            posts10 = post10.getText().toString();
            inst1s10 = inst110.getText().toString();
            fromdates10 = fromdate10.getText().toString();
            todates10 = todate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            post6.setText(posts7);
            inst16.setText(inst1s7);
            fromdate6.setText(fromdates7);
            todate6.setText(todates7);
            switch6.setChecked(blnswitch7);


            post7.setText("");
            inst17.setText("");
            fromdate7.setText("");
            todate7.setText("");
            switch7.setChecked(false);

            post7.setText(posts8);
            inst17.setText(inst1s8);
            fromdate7.setText(fromdates8);
            todate7.setText(todates8);
            switch7.setChecked(blnswitch8);

            post8.setText("");
            inst18.setText("");
            fromdate8.setText("");
            todate8.setText("");
            switch8.setChecked(false);

            post8.setText(posts9);
            inst18.setText(inst1s9);
            fromdate8.setText(fromdates9);
            todate8.setText(todates9);
            switch8.setChecked(blnswitch9);


            //set value 8
            post9.setText("");
            inst19.setText("");
            fromdate9.setText("");
            todate9.setText("");
            switch9.setChecked(false);

            post9.setText(posts10);
            inst19.setText(inst1s10);
            fromdate9.setText(fromdates10);
            todate9.setText(todates10);
            switch9.setChecked(blnswitch10);


            posts9 = posts10;
            inst1s9 = inst1s10;
            fromdates9 = fromdates10;
            todates9 = todates10;

            post10.setText("");
            inst110.setText("");
            fromdate10.setText("");
            todate10.setText("");
            switch10.setChecked(false);
            //set value 9


            // you are here........................................
        } else if (d == 5) {
            posts6 = post6.getText().toString();
            inst1s6 = inst16.getText().toString();
            fromdates6 = fromdate6.getText().toString();
            todates6 = todate6.getText().toString();
            blnswitch6 = switch6.isChecked();

            posts7 = post7.getText().toString();
            inst1s7 = inst17.getText().toString();
            fromdates7 = fromdate7.getText().toString();
            todates7 = todate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            posts8 = post8.getText().toString();
            inst1s8 = inst18.getText().toString();
            fromdates8 = fromdate8.getText().toString();
            todates8 = todate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            posts9 = post9.getText().toString();
            inst1s9 = inst19.getText().toString();
            fromdates9 = fromdate9.getText().toString();
            todates9 = todate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            posts10 = post10.getText().toString();
            inst1s10 = inst110.getText().toString();
            fromdates10 = fromdate10.getText().toString();
            todates10 = todate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            post5.setText(posts6);
            inst15.setText(inst1s6);
            fromdate5.setText(fromdates6);
            todate5.setText(todates6);
            switch5.setChecked(blnswitch6);

            post6.setText("");
            inst16.setText("");
            fromdate6.setText("");
            todate6.setText("");
            switch6.setChecked(false);

            post6.setText(posts7);
            inst16.setText(inst1s7);
            fromdate6.setText(fromdates7);
            todate6.setText(todates7);
            switch6.setChecked(blnswitch7);

            post7.setText("");
            inst17.setText("");
            fromdate7.setText("");
            todate7.setText("");
            switch7.setChecked(false);

            post7.setText(posts8);
            inst17.setText(inst1s8);
            fromdate7.setText(fromdates8);
            todate7.setText(todates8);
            switch7.setChecked(blnswitch8);

            post8.setText("");
            inst18.setText("");
            fromdate8.setText("");
            todate8.setText("");
            switch8.setChecked(false);
            //set value 8
            post8.setText(posts9);
            inst18.setText(inst1s9);
            fromdate8.setText(fromdates9);
            todate8.setText(todates9);
            switch8.setChecked(blnswitch9);

            post9.setText("");
            inst19.setText("");
            fromdate9.setText("");
            todate9.setText("");
            switch9.setChecked(false);

            post9.setText(posts10);
            inst19.setText(inst1s10);
            fromdate9.setText(fromdates10);
            todate9.setText(todates10);
            switch9.setChecked(blnswitch10);

            posts9 = posts10;
            inst1s9 = inst1s10;
            fromdates9 = fromdates10;
            todates9 = todates10;

            post10.setText("");
            inst110.setText("");
            fromdate10.setText("");
            todate10.setText("");
            switch10.setChecked(false);
            //set value 9


            // you are here........................................
        } else if (d == 4) {
            posts5 = post5.getText().toString();
            inst1s5 = inst15.getText().toString();
            fromdates5 = fromdate5.getText().toString();
            todates5 = todate5.getText().toString();
            blnswitch5 = switch5.isChecked();

            posts6 = post6.getText().toString();
            inst1s6 = inst16.getText().toString();
            fromdates6 = fromdate6.getText().toString();
            todates6 = todate6.getText().toString();
            blnswitch6 = switch6.isChecked();

            posts7 = post7.getText().toString();
            inst1s7 = inst17.getText().toString();
            fromdates7 = fromdate7.getText().toString();
            todates7 = todate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            posts8 = post8.getText().toString();
            inst1s8 = inst18.getText().toString();
            fromdates8 = fromdate8.getText().toString();
            todates8 = todate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            posts9 = post9.getText().toString();
            inst1s9 = inst19.getText().toString();
            fromdates9 = fromdate9.getText().toString();
            todates9 = todate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            posts10 = post10.getText().toString();
            inst1s10 = inst110.getText().toString();
            fromdates10 = fromdate10.getText().toString();
            todates10 = todate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            post4.setText(posts5);
            inst14.setText(inst1s5);
            fromdate4.setText(fromdates5);
            todate4.setText(todates5);
            switch4.setChecked(blnswitch5);

            post5.setText("");
            inst15.setText("");
            fromdate5.setText("");
            todate5.setText("");
            switch5.setChecked(false);

            post5.setText(posts6);
            inst15.setText(inst1s6);
            fromdate5.setText(fromdates6);
            todate5.setText(todates6);
            switch5.setChecked(blnswitch6);

            post6.setText("");
            inst16.setText("");
            fromdate6.setText("");
            todate6.setText("");
            switch6.setChecked(false);

            post6.setText(posts7);
            inst16.setText(inst1s7);
            fromdate6.setText(fromdates7);
            todate6.setText(todates7);
            switch6.setChecked(blnswitch7);

            post7.setText("");
            inst17.setText("");
            fromdate7.setText("");
            todate7.setText("");
            switch7.setChecked(false);

            post7.setText(posts8);
            inst17.setText(inst1s8);
            fromdate7.setText(fromdates8);
            todate7.setText(todates8);
            switch7.setChecked(blnswitch8);


            post8.setText("");
            inst18.setText("");
            fromdate8.setText("");
            todate8.setText("");
            switch8.setChecked(false);
            //set value 8
            post8.setText(posts9);
            inst18.setText(inst1s9);
            fromdate8.setText(fromdates9);
            todate8.setText(todates9);
            switch8.setChecked(blnswitch9);

            post9.setText("");
            inst19.setText("");
            fromdate9.setText("");
            todate9.setText("");
            switch9.setChecked(false);

            post9.setText(posts10);
            inst19.setText(inst1s10);
            fromdate9.setText(fromdates10);
            todate9.setText(todates10);
            switch9.setChecked(blnswitch10);

            posts9 = posts10;
            inst1s9 = inst1s10;
            fromdates9 = fromdates10;
            todates9 = todates10;

            post10.setText("");
            inst110.setText("");
            fromdate10.setText("");
            todate10.setText("");
            switch10.setChecked(false);
            //set value 9


            // you are here........................................
        } else if (d == 3) {
            posts4 = post4.getText().toString();
            inst1s4 = inst14.getText().toString();
            fromdates4 = fromdate4.getText().toString();
            todates4 = todate4.getText().toString();
            blnswitch4 = switch4.isChecked();

            posts5 = post5.getText().toString();
            inst1s5 = inst15.getText().toString();
            fromdates5 = fromdate5.getText().toString();
            todates5 = todate5.getText().toString();
            blnswitch5 = switch5.isChecked();

            posts6 = post6.getText().toString();
            inst1s6 = inst16.getText().toString();
            fromdates6 = fromdate6.getText().toString();
            todates6 = todate6.getText().toString();
            blnswitch6 = switch6.isChecked();

            posts7 = post7.getText().toString();
            inst1s7 = inst17.getText().toString();
            fromdates7 = fromdate7.getText().toString();
            todates7 = todate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            posts8 = post8.getText().toString();
            inst1s8 = inst18.getText().toString();
            fromdates8 = fromdate8.getText().toString();
            todates8 = todate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            posts9 = post9.getText().toString();
            inst1s9 = inst19.getText().toString();
            fromdates9 = fromdate9.getText().toString();
            todates9 = todate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            posts10 = post10.getText().toString();
            inst1s10 = inst110.getText().toString();
            fromdates10 = fromdate10.getText().toString();
            todates10 = todate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            post3.setText(posts4);
            inst13.setText(inst1s4);
            fromdate3.setText(fromdates4);
            todate3.setText(todates4);
            switch3.setChecked(blnswitch4);

            post4.setText("");
            inst14.setText("");
            fromdate4.setText("");
            todate4.setText("");
            switch4.setChecked(false);

            post4.setText(posts5);
            inst14.setText(inst1s5);
            fromdate4.setText(fromdates5);
            todate4.setText(todates5);
            switch4.setChecked(blnswitch5);

            post5.setText("");
            inst15.setText("");
            fromdate5.setText("");
            todate5.setText("");
            switch5.setChecked(false);

            post5.setText(posts6);
            inst15.setText(inst1s6);
            fromdate5.setText(fromdates6);
            todate5.setText(todates6);
            switch5.setChecked(blnswitch6);

            post6.setText("");
            inst16.setText("");
            fromdate6.setText("");
            todate6.setText("");
            switch6.setChecked(false);

            post6.setText(posts7);
            inst16.setText(inst1s7);
            fromdate6.setText(fromdates7);
            todate6.setText(todates7);
            switch6.setChecked(blnswitch7);

            post7.setText("");
            inst17.setText("");
            fromdate7.setText("");
            todate7.setText("");
            switch7.setChecked(false);

            post7.setText(posts8);
            inst17.setText(inst1s8);
            fromdate7.setText(fromdates8);
            todate7.setText(todates8);
            switch7.setChecked(blnswitch8);

            post8.setText("");
            inst18.setText("");
            fromdate8.setText("");
            todate8.setText("");
            switch8.setChecked(false);
            //set value 8
            post8.setText(posts9);
            inst18.setText(inst1s9);
            fromdate8.setText(fromdates9);
            todate8.setText(todates9);
            switch8.setChecked(blnswitch9);

            post9.setText("");
            inst19.setText("");
            fromdate9.setText("");
            todate9.setText("");
            switch9.setChecked(false);

            post9.setText(posts10);
            inst19.setText(inst1s10);
            fromdate9.setText(fromdates10);
            todate9.setText(todates10);
            switch9.setChecked(blnswitch10);

            posts9 = posts10;
            inst1s9 = inst1s10;
            fromdates9 = fromdates10;
            todates9 = todates10;

            post10.setText("");
            inst110.setText("");
            fromdate10.setText("");
            todate10.setText("");
            switch10.setChecked(false);
            //set value 9


            // you are here........................................
        } else if (d == 2) {
            posts3 = post3.getText().toString();
            inst1s3 = inst13.getText().toString();
            fromdates3 = fromdate3.getText().toString();
            todates3 = todate3.getText().toString();
            blnswitch3 = switch3.isChecked();

            posts4 = post4.getText().toString();
            inst1s4 = inst14.getText().toString();
            fromdates4 = fromdate4.getText().toString();
            todates4 = todate4.getText().toString();
            blnswitch4 = switch4.isChecked();

            posts5 = post5.getText().toString();
            inst1s5 = inst15.getText().toString();
            fromdates5 = fromdate5.getText().toString();
            todates5 = todate5.getText().toString();
            blnswitch5 = switch5.isChecked();

            posts6 = post6.getText().toString();
            inst1s6 = inst16.getText().toString();
            fromdates6 = fromdate6.getText().toString();
            todates6 = todate6.getText().toString();
            blnswitch6 = switch6.isChecked();

            posts7 = post7.getText().toString();
            inst1s7 = inst17.getText().toString();
            fromdates7 = fromdate7.getText().toString();
            todates7 = todate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            posts8 = post8.getText().toString();
            inst1s8 = inst18.getText().toString();
            fromdates8 = fromdate8.getText().toString();
            todates8 = todate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            posts9 = post9.getText().toString();
            inst1s9 = inst19.getText().toString();
            fromdates9 = fromdate9.getText().toString();
            todates9 = todate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            posts10 = post10.getText().toString();
            inst1s10 = inst110.getText().toString();
            fromdates10 = fromdate10.getText().toString();
            todates10 = todate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            post2.setText(posts3);
            inst12.setText(inst1s3);
            fromdate2.setText(fromdates3);
            todate2.setText(todates3);
            switch2.setChecked(blnswitch3);

            post3.setText("");
            inst13.setText("");
            fromdate3.setText("");
            todate3.setText("");
            switch3.setChecked(false);

            post3.setText(posts4);
            inst13.setText(inst1s4);
            fromdate3.setText(fromdates4);
            todate3.setText(todates4);
            switch3.setChecked(blnswitch4);

            post4.setText("");
            inst14.setText("");
            fromdate4.setText("");
            todate4.setText("");
            switch4.setChecked(false);

            post4.setText(posts5);
            inst14.setText(inst1s5);
            fromdate4.setText(fromdates5);
            todate4.setText(todates5);
            switch4.setChecked(blnswitch5);

            post5.setText("");
            inst15.setText("");
            fromdate5.setText("");
            todate5.setText("");
            switch5.setChecked(false);

            post5.setText(posts6);
            inst15.setText(inst1s6);
            fromdate5.setText(fromdates6);
            todate5.setText(todates6);
            switch5.setChecked(blnswitch6);

            post6.setText("");
            inst16.setText("");
            fromdate6.setText("");
            todate6.setText("");
            switch6.setChecked(false);

            post6.setText(posts7);
            inst16.setText(inst1s7);
            fromdate6.setText(fromdates7);
            todate6.setText(todates7);
            switch6.setChecked(blnswitch7);

            post7.setText("");
            inst17.setText("");
            fromdate7.setText("");
            todate7.setText("");
            switch7.setChecked(false);

            post7.setText(posts8);
            inst17.setText(inst1s8);
            fromdate7.setText(fromdates8);
            todate7.setText(todates8);
            switch7.setChecked(blnswitch8);

            post8.setText("");
            inst18.setText("");
            fromdate8.setText("");
            todate8.setText("");
            switch8.setChecked(false);

            //set value 8
            post8.setText(posts9);
            inst18.setText(inst1s9);
            fromdate8.setText(fromdates9);
            todate8.setText(todates9);
            switch8.setChecked(blnswitch9);

            post9.setText("");
            inst19.setText("");
            fromdate9.setText("");
            todate9.setText("");
            switch9.setChecked(false);

            post9.setText(posts10);
            inst19.setText(inst1s10);
            fromdate9.setText(fromdates10);
            todate9.setText(todates10);
            switch9.setChecked(blnswitch10);

            posts9 = posts10;
            inst1s9 = inst1s10;
            fromdates9 = fromdates10;
            todates9 = todates10;

            post10.setText("");
            inst110.setText("");
            fromdate10.setText("");
            todate10.setText("");
            switch10.setChecked(false);
            //set value 9


            // you are here........................................
        } else if (d == 1) {
            posts2 = post2.getText().toString();
            inst1s2 = inst12.getText().toString();
            fromdates2 = fromdate2.getText().toString();
            todates2 = todate2.getText().toString();
            blnswitch2 = switch2.isChecked();

            posts3 = post3.getText().toString();
            inst1s3 = inst13.getText().toString();
            fromdates3 = fromdate3.getText().toString();
            todates3 = todate3.getText().toString();
            blnswitch3 = switch3.isChecked();

            posts4 = post4.getText().toString();
            inst1s4 = inst14.getText().toString();
            fromdates4 = fromdate4.getText().toString();
            todates4 = todate4.getText().toString();
            blnswitch4 = switch4.isChecked();

            posts5 = post5.getText().toString();
            inst1s5 = inst15.getText().toString();
            fromdates5 = fromdate5.getText().toString();
            todates5 = todate5.getText().toString();
            blnswitch5 = switch5.isChecked();

            posts6 = post6.getText().toString();
            inst1s6 = inst16.getText().toString();
            fromdates6 = fromdate6.getText().toString();
            todates6 = todate6.getText().toString();
            blnswitch6 = switch6.isChecked();

            posts7 = post7.getText().toString();
            inst1s7 = inst17.getText().toString();
            fromdates7 = fromdate7.getText().toString();
            todates7 = todate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            posts8 = post8.getText().toString();
            inst1s8 = inst18.getText().toString();
            fromdates8 = fromdate8.getText().toString();
            todates8 = todate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            posts9 = post9.getText().toString();
            inst1s9 = inst19.getText().toString();
            fromdates9 = fromdate9.getText().toString();
            todates9 = todate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            posts10 = post10.getText().toString();
            inst1s10 = inst110.getText().toString();
            fromdates10 = fromdate10.getText().toString();
            todates10 = todate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            post1.setText(posts2);
            inst11.setText(inst1s2);
            fromdate1.setText(fromdates2);
            todate1.setText(todates2);
            switch1.setChecked(blnswitch2);


            post2.setText("");
            inst12.setText("");
            fromdate2.setText("");
            todate2.setText("");
            switch2.setChecked(false);

            post2.setText(posts3);
            inst12.setText(inst1s3);
            fromdate2.setText(fromdates3);
            todate2.setText(todates3);
            switch2.setChecked(blnswitch3);


            post3.setText("");
            inst13.setText("");
            fromdate3.setText("");
            todate3.setText("");
            switch3.setChecked(false);

            post3.setText(posts4);
            inst13.setText(inst1s4);
            fromdate3.setText(fromdates4);
            todate3.setText(todates4);
            switch3.setChecked(blnswitch4);


            post4.setText("");
            inst14.setText("");
            fromdate4.setText("");
            todate4.setText("");
            switch4.setChecked(false);

            post4.setText(posts5);
            inst14.setText(inst1s5);
            fromdate4.setText(fromdates5);
            todate4.setText(todates5);
            switch4.setChecked(blnswitch5);


            post5.setText("");
            inst15.setText("");
            fromdate5.setText("");
            todate5.setText("");
            switch5.setChecked(false);

            post5.setText(posts6);
            inst15.setText(inst1s6);
            fromdate5.setText(fromdates6);
            todate5.setText(todates6);
            switch5.setChecked(blnswitch6);


            post6.setText("");
            inst16.setText("");
            fromdate6.setText("");
            todate6.setText("");
            switch6.setChecked(false);

            post6.setText(posts7);
            inst16.setText(inst1s7);
            fromdate6.setText(fromdates7);
            todate6.setText(todates7);
            switch6.setChecked(blnswitch7);


            post7.setText("");
            inst17.setText("");
            fromdate7.setText("");
            todate7.setText("");
            switch7.setChecked(false);

            post7.setText(posts8);
            inst17.setText(inst1s8);
            fromdate7.setText(fromdates8);
            todate7.setText(todates8);
            switch7.setChecked(blnswitch8);


            post8.setText("");
            inst18.setText("");
            fromdate8.setText("");
            todate8.setText("");
            switch8.setChecked(false);

            //set value 8
            post8.setText(posts9);
            inst18.setText(inst1s9);
            fromdate8.setText(fromdates9);
            todate8.setText(todates9);
            switch8.setChecked(blnswitch9);

            post9.setText("");
            inst19.setText("");
            fromdate9.setText("");
            todate9.setText("");
            switch9.setChecked(false);

            post9.setText(posts10);
            inst19.setText(inst1s10);
            fromdate9.setText(fromdates10);
            todate9.setText(todates10);
            switch9.setChecked(blnswitch10);

            posts9 = posts10;
            inst1s9 = inst1s10;
            fromdates9 = fromdates10;
            todates9 = todates10;

            post10.setText("");
            inst110.setText("");
            fromdate10.setText("");
            todate10.setText("");
            switch10.setChecked(false);
            //set value 9
            // you are here........................................
        }
    }

    public boolean validate_0() {
        Log.d(HRlog, "validate_0 called");
        errorflag = 0;
        if (posts1.equals("")) {
            errorflag = 1;
            post1.setError("Field can not be Empty");
        } else if (posts1.length() < 2) {
            errorflag = 1;
            post1.setError("Invalid position");
        } else if (inst1s1.equals("")) {
            errorflag = 1;
            inst11.setError("Field can not be Empty");
        } else if (inst1s1.length() < 2) {
            errorflag = 1;
            inst11.setError("Invalid Institute");
        } else if (fromdates1.equals("")) {
            errorflag = 1;
            fromdate1.setError("Enter From date");
        } else if (blnswitch1 == false) {
            if (todates1.equals("")) {
                errorflag = 1;
                todate1.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            Log.d(HRlog, "validate_0 false");
            return false;
        }
        Log.d(HRlog, "validate_0 return true");
        return true;
    }

    public boolean validate_1() {
        errorflag = 0;
        Log.d(HRlog, "validate_1 called");
        errorflag = 0;
        if (posts2.equals("")) {
            errorflag = 1;
            post2.setError("Field can not be Empty");
        } else if (posts2.length() < 2) {
            errorflag = 1;
            post2.setError("Invalid position");
        } else if (inst1s2.equals("")) {
            errorflag = 1;
            inst12.setError("Field can not be Empty");
        } else if (inst1s2.length() < 2) {
            errorflag = 1;
            inst12.setError("Invalid Institute");
        } else if (fromdates2.equals("")) {
            errorflag = 1;
            fromdate2.setError("Enter From date");
        } else if (blnswitch2 == false) {
            if (todates2.equals("")) {
                errorflag = 1;
                todate2.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            Log.d(HRlog, "validate_1 return false");
            return false;
        }
        Log.d(HRlog, "validate_1 true");
        return true;
    }

    public boolean validate_2() {
        errorflag = 0;
        Log.d(HRlog, "validate_2 called");
        errorflag = 0;
        if (posts3.equals("")) {
            errorflag = 1;
            post3.setError("Field can not be Empty");
        } else if (posts3.length() < 2) {
            errorflag = 1;
            post3.setError("Invalid position");
        } else if (inst1s3.equals("")) {
            errorflag = 1;
            inst13.setError("Field can not be Empty");
        } else if (inst1s3.length() < 2) {
            errorflag = 1;
            inst13.setError("Invalid Institute");
        } else if (fromdates3.equals("")) {
            errorflag = 1;
            fromdate3.setError("Enter From date");
        } else if (blnswitch3 == false) {
            if (todates3.equals("")) {
                errorflag = 1;
                todate3.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            Log.d(HRlog, "validate_2 return false");
            return false;
        }
        Log.d(HRlog, "validate_2 return true");
        return true;

    }

    public boolean validate_3() {
        errorflag = 0;
        errorflag = 0;
        if (posts4.equals("")) {
            errorflag = 1;
            post4.setError("Field can not be Empty");
        } else if (posts4.length() < 2) {
            errorflag = 1;
            post4.setError("Invalid position");
        } else if (inst1s4.equals("")) {
            errorflag = 1;
            inst14.setError("Field can not be Empty");
        } else if (inst1s4.length() < 2) {
            errorflag = 1;
            inst14.setError("Invalid Institute");
        } else if (fromdates4.equals("")) {
            errorflag = 1;
            fromdate4.setError("Enter From date");
        } else if (blnswitch4 == false) {
            if (todates4.equals("")) {
                errorflag = 1;
                todate4.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            return false;
        }
        return true;
    }

    public boolean validate_4() {
        errorflag = 0;
        if (posts5.equals("")) {
            errorflag = 1;
            post5.setError("Field can not be Empty");
        } else if (posts5.length() < 2) {
            errorflag = 1;
            post5.setError("Invalid position");
        } else if (inst1s5.equals("")) {
            errorflag = 1;
            inst15.setError("Field can not be Empty");
        } else if (inst1s5.length() < 2) {
            errorflag = 1;
            inst15.setError("Invalid Institute");
        } else if (fromdates5.equals("")) {
            errorflag = 1;
            fromdate5.setError("Enter From date");
        } else if (blnswitch5 == false) {
            if (todates5.equals("")) {
                errorflag = 1;
                todate5.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            return false;
        }
        return true;
    }

    public boolean validate_5() {
        errorflag = 0;
        if (posts6.equals("")) {
            errorflag = 1;
            post6.setError("Field can not be Empty");
        } else if (posts6.length() < 2) {
            errorflag = 1;
            post6.setError("Invalid position");
        } else if (inst1s6.equals("")) {
            errorflag = 1;
            inst16.setError("Field can not be Empty");
        } else if (inst1s6.length() < 2) {
            errorflag = 1;
            inst16.setError("Invalid Institute");
        } else if (fromdates6.equals("")) {
            errorflag = 1;
            fromdate6.setError("Enter From date");
        } else if (blnswitch6 == false) {
            if (todates6.equals("")) {
                errorflag = 1;
                todate6.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            return false;
        }
        return true;
    }

    public boolean validate_6() {
        errorflag = 0;
        if (posts7.equals("")) {
            errorflag = 1;
            post7.setError("Field can not be Empty");
        } else if (posts7.length() < 2) {
            errorflag = 1;
            post7.setError("Invalid position");
        } else if (inst1s7.equals("")) {
            errorflag = 1;
            inst17.setError("Field can not be Empty");
        } else if (inst1s7.length() < 2) {
            errorflag = 1;
            inst17.setError("Invalid Institute");
        } else if (fromdates7.equals("")) {
            errorflag = 1;
            fromdate7.setError("Enter From date");
        } else if (blnswitch7 == false) {
            if (todates7.equals("")) {
                errorflag = 1;
                todate7.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            return false;
        }
        return true;
    }

    public boolean validate_7() {
        errorflag = 0;
        if (posts8.equals("")) {
            errorflag = 1;
            post8.setError("Field can not be Empty");
        } else if (posts8.length() < 2) {
            errorflag = 1;
            post8.setError("Invalid position");
        } else if (inst1s8.equals("")) {
            errorflag = 1;
            inst18.setError("Field can not be Empty");
        } else if (inst1s8.length() < 2) {
            errorflag = 1;
            inst18.setError("Invalid Institute");
        } else if (fromdates8.equals("")) {
            errorflag = 1;
            fromdate8.setError("Enter From date");
        } else if (blnswitch8 == false) {
            if (todates8.equals("")) {
                errorflag = 1;
                todate8.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            return false;
        }
        return true;
    }

    public boolean validate_8() {
        errorflag = 0;
        if (posts9.equals("")) {
            errorflag = 1;
            post9.setError("Field can not be Empty");
        } else if (posts9.length() < 2) {
            errorflag = 1;
            post9.setError("Invalid position");
        } else if (inst1s9.equals("")) {
            errorflag = 1;
            inst19.setError("Field can not be Empty");
        } else if (inst1s9.length() < 2) {
            errorflag = 1;
            inst19.setError("Invalid Institute");
        } else if (fromdates9.equals("")) {
            errorflag = 1;
            fromdate9.setError("Enter From date");
        } else if (blnswitch9 == false) {
            if (todates9.equals("")) {
                errorflag = 1;
                todate9.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            return false;
        }
        return true;
    }

    public boolean validate_9() {
        errorflag = 0;
        if (posts10.equals("")) {
            errorflag = 1;
            post10.setError("Field can not be Empty");
        } else if (posts10.length() < 2) {
            errorflag = 1;
            post10.setError("Invalid position");
        } else if (inst1s10.equals("")) {
            errorflag = 1;
            inst110.setError("Field can not be Empty");
        } else if (inst1s10.length() < 2) {
            errorflag = 1;
            inst110.setError("Invalid Institute");
        } else if (fromdates10.equals("")) {
            errorflag = 1;
            fromdate10.setError("Enter From date");
        } else if (blnswitch10 == false) {
            if (todates10.equals("")) {
                errorflag = 1;
                todate10.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            return false;
        }
        return true;
    }

    public void validateAndSaveData() {

        posts1 = post1.getText().toString();
        inst1s1 = inst11.getText().toString();
        posts2 = post2.getText().toString();
        inst1s2 = inst12.getText().toString();
        posts3 = post3.getText().toString();
        inst1s3 = inst13.getText().toString();
        posts4 = post4.getText().toString();
        inst1s4 = inst14.getText().toString();
        posts5 = post5.getText().toString();
        inst1s5 = inst15.getText().toString();
        posts6 = post6.getText().toString();
        inst1s6 = inst16.getText().toString();
        posts7 = post7.getText().toString();
        inst1s7 = inst17.getText().toString();
        posts8 = post8.getText().toString();
        inst1s8 = inst18.getText().toString();
        posts9 = post9.getText().toString();
        inst1s9 = inst19.getText().toString();
        posts10 = post10.getText().toString();
        inst1s10 = inst110.getText().toString();

        //fromdates1="",todates1=""
        fromdates1 = fromdate1.getText().toString();
        fromdates2 = fromdate2.getText().toString();
        fromdates3 = fromdate3.getText().toString();
        fromdates4 = fromdate4.getText().toString();
        fromdates5 = fromdate5.getText().toString();
        fromdates6 = fromdate6.getText().toString();
        fromdates7 = fromdate7.getText().toString();
        fromdates8 = fromdate8.getText().toString();
        fromdates9 = fromdate9.getText().toString();
        fromdates10 = fromdate10.getText().toString();

        todates1 = todate1.getText().toString();
        todates2 = todate2.getText().toString();
        todates3 = todate3.getText().toString();
        todates4 = todate4.getText().toString();
        todates5 = todate5.getText().toString();
        todates6 = todate6.getText().toString();
        todates7 = todate7.getText().toString();
        todates8 = todate8.getText().toString();
        todates9 = todate9.getText().toString();
        todates10 = todate10.getText().toString();

        blnswitch1 = switch1.isChecked();
        blnswitch2 = switch2.isChecked();
        blnswitch3 = switch3.isChecked();
        blnswitch4 = switch4.isChecked();
        blnswitch5 = switch5.isChecked();
        blnswitch6 = switch6.isChecked();
        blnswitch7 = switch7.isChecked();
        blnswitch8 = switch8.isChecked();
        blnswitch9 = switch9.isChecked();
        blnswitch10 = switch10.isChecked();

        //validate_1 function return true if all filed are OK fro 1 block
        boolean v0 = false, v1 = false, v2 = false, v3 = false, v4 = false, v5 = false, v6 = false, v7 = false, v8 = false, v9 = false;

        if (expcount == 0) {
            v0 = validate_0();
        } else if (expcount == 1) {
            v0 = validate_0();
            if (v0 == true) {
                validate_1();
            }
        } else if (expcount == 2) {
            v0 = validate_0();
            if (v0 == true) {
                v1 = validate_1();
            }
            if (v1 == true) {
                validate_2();
            }
        } else if (expcount == 3) {
            v0 = validate_0();
            if (v0 == true) {
                v1 = validate_1();
            }
            if (v1 == true) {
                v2 = validate_2();
            }
            if (v2 == true) {
                validate_3();
            }
        } else if (expcount == 4) {
            v0 = validate_0();
            if (v0 == true) {
                v1 = validate_1();
            }
            if (v1 == true) {
                v2 = validate_2();
            }
            if (v2 == true) {
                v3 = validate_3();
            }
            if (v3 == true) {
                v4 = validate_4();
            }

        } else if (expcount == 5) {

            v0 = validate_0();
            if (v0 == true) {
                v1 = validate_1();
            }
            if (v1 == true) {
                v2 = validate_2();
            }
            if (v2 == true) {
                v3 = validate_3();
            }
            if (v3 == true) {
                v4 = validate_4();
            }
            if (v4 == true) {
                v5 = validate_5();
            }
        } else if (expcount == 6) {

            v0 = validate_0();
            if (v0 == true) {
                v1 = validate_1();
            }
            if (v1 == true) {
                v2 = validate_2();
            }
            if (v2 == true) {
                v3 = validate_3();
            }
            if (v3 == true) {
                v4 = validate_4();
            }
            if (v4 == true) {
                v5 = validate_5();
            }
            if (v5 == true) {
                v6 = validate_6();
            }
        } else if (expcount == 7) {
            v0 = validate_0();
            if (v0 == true) {
                v1 = validate_1();
            }
            if (v1 == true) {
                v2 = validate_2();
            }
            if (v2 == true) {
                v3 = validate_3();
            }
            if (v3 == true) {
                v4 = validate_4();
            }
            if (v4 == true) {
                v5 = validate_5();
            }
            if (v5 == true) {
                v6 = validate_6();
            }
            if (v6 == true) {
                v7 = validate_7();
            }
        } else if (expcount == 8) {
            v0 = validate_0();
            if (v0 == true) {
                v1 = validate_1();
            }
            if (v1 == true) {
                v2 = validate_2();
            }
            if (v2 == true) {
                v3 = validate_3();
            }
            if (v3 == true) {
                v4 = validate_4();
            }
            if (v4 == true) {
                v5 = validate_5();
            }
            if (v5 == true) {
                v6 = validate_6();
            }
            if (v6 == true) {
                v7 = validate_7();
            }
            if (v7 == true) {
                v8 = validate_8();
            }
        } else if (expcount == 9) {
            v0 = validate_0();
            if (v0 == true) {
                v1 = validate_1();
            }
            if (v1 == true) {
                v2 = validate_2();
            }
            if (v2 == true) {
                v3 = validate_3();
            }
            if (v3 == true) {
                v4 = validate_4();
            }
            if (v4 == true) {
                v5 = validate_5();
            }
            if (v5 == true) {
                v6 = validate_6();
            }
            if (v6 == true) {
                v7 = validate_7();
            }
            if (v7 == true) {
                v8 = validate_8();
            }
            if (v8 == true) {
                validate_9();
            }
        }

        if (errorflag == 0) {
            Encrypt();
            new saveHrExperienceTask().execute();
        }
    }

    public void Encrypt() {

        try {

            byte[] posts1Bytes1 = posts1.getBytes("UTF-8");
            byte[] insts1Bytes1 = inst1s1.getBytes("UTF-8");
            byte[] fromDateBytes1 = fromdates1.getBytes("UTF-8");
            byte[] toDateBytes1 = todates1.getBytes("UTF-8");

            byte[] posts1Bytes2 = posts2.getBytes("UTF-8");
            byte[] insts1Bytes2 = inst1s2.getBytes("UTF-8");
            byte[] fromDateBytes2 = fromdates2.getBytes("UTF-8");
            byte[] toDateBytes2 = todates2.getBytes("UTF-8");

            byte[] posts1Bytes3 = posts3.getBytes("UTF-8");
            byte[] insts1Bytes3 = inst1s3.getBytes("UTF-8");
            byte[] fromDateBytes3 = fromdates3.getBytes("UTF-8");
            byte[] toDateBytes3 = todates3.getBytes("UTF-8");

            byte[] posts1Bytes4 = posts4.getBytes("UTF-8");
            byte[] insts1Bytes4 = inst1s4.getBytes("UTF-8");
            byte[] fromDateBytes4 = fromdates4.getBytes("UTF-8");
            byte[] toDateBytes4 = todates4.getBytes("UTF-8");

            byte[] posts1Bytes5 = posts5.getBytes("UTF-8");
            byte[] insts1Bytes5 = inst1s5.getBytes("UTF-8");
            byte[] fromDateBytes5 = fromdates5.getBytes("UTF-8");
            byte[] toDateBytes5 = todates5.getBytes("UTF-8");

            byte[] posts1Bytes6 = posts6.getBytes("UTF-8");
            byte[] insts1Bytes6 = inst1s6.getBytes("UTF-8");
            byte[] fromDateBytes6 = fromdates6.getBytes("UTF-8");
            byte[] toDateBytes6 = todates6.getBytes("UTF-8");

            byte[] posts1Bytes7 = posts7.getBytes("UTF-8");
            byte[] insts1Bytes7 = inst1s7.getBytes("UTF-8");
            byte[] fromDateBytes7 = fromdates7.getBytes("UTF-8");
            byte[] toDateBytes7 = todates7.getBytes("UTF-8");

            byte[] posts1Bytes8 = posts8.getBytes("UTF-8");
            byte[] insts1Bytes8 = inst1s8.getBytes("UTF-8");
            byte[] fromDateBytes8 = fromdates8.getBytes("UTF-8");
            byte[] toDateBytes8 = todates8.getBytes("UTF-8");

            byte[] posts1Bytes9 = posts9.getBytes("UTF-8");
            byte[] insts1Bytes9 = inst1s9.getBytes("UTF-8");
            byte[] fromDateBytes9 = fromdates9.getBytes("UTF-8");
            byte[] toDateBytes9 = todates9.getBytes("UTF-8");

            byte[] posts1Bytes10 = posts10.getBytes("UTF-8");
            byte[] insts1Bytes10 = inst1s10.getBytes("UTF-8");
            byte[] fromDateBytes10 = fromdates10.getBytes("UTF-8");
            byte[] toDateBytes10 = todates10.getBytes("UTF-8");


            byte[] post1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, posts1Bytes1);
            encpost1 = new String(SimpleBase64Encoder.encode(post1EncryptedBytes));
            byte[] post2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, posts1Bytes2);
            encpost2 = new String(SimpleBase64Encoder.encode(post2EncryptedBytes));
            byte[] post3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, posts1Bytes3);
            encpost3 = new String(SimpleBase64Encoder.encode(post3EncryptedBytes));
            byte[] post4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, posts1Bytes4);
            encpost4 = new String(SimpleBase64Encoder.encode(post4EncryptedBytes));
            byte[] post5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, posts1Bytes5);
            encpost5 = new String(SimpleBase64Encoder.encode(post5EncryptedBytes));
            byte[] post6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, posts1Bytes6);
            encpost6 = new String(SimpleBase64Encoder.encode(post6EncryptedBytes));
            byte[] post7EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, posts1Bytes7);
            encpost7 = new String(SimpleBase64Encoder.encode(post7EncryptedBytes));
            byte[] post8EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, posts1Bytes8);
            encpost8 = new String(SimpleBase64Encoder.encode(post8EncryptedBytes));
            byte[] post9EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, posts1Bytes9);
            encpost9 = new String(SimpleBase64Encoder.encode(post9EncryptedBytes));
            byte[] post10EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, posts1Bytes10);
            encpost10 = new String(SimpleBase64Encoder.encode(post10EncryptedBytes));

            byte[] instEncryptedBytes1 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, insts1Bytes1);
            encinst1 = new String(SimpleBase64Encoder.encode(instEncryptedBytes1));
            byte[] instEncryptedBytes2 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, insts1Bytes2);
            encinst2 = new String(SimpleBase64Encoder.encode(instEncryptedBytes2));
            byte[] instEncryptedBytes3 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, insts1Bytes3);
            encinst3 = new String(SimpleBase64Encoder.encode(instEncryptedBytes3));
            byte[] instEncryptedBytes4 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, insts1Bytes4);
            encinst4 = new String(SimpleBase64Encoder.encode(instEncryptedBytes4));
            byte[] instEncryptedBytes5 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, insts1Bytes5);
            encinst5 = new String(SimpleBase64Encoder.encode(instEncryptedBytes5));
            byte[] instEncryptedBytes6 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, insts1Bytes6);
            encinst6 = new String(SimpleBase64Encoder.encode(instEncryptedBytes6));
            byte[] instEncryptedBytes7 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, insts1Bytes7);
            encinst7 = new String(SimpleBase64Encoder.encode(instEncryptedBytes7));
            byte[] instEncryptedBytes8 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, insts1Bytes8);
            encinst8 = new String(SimpleBase64Encoder.encode(instEncryptedBytes8));
            byte[] instEncryptedBytes9 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, insts1Bytes9);
            encinst9 = new String(SimpleBase64Encoder.encode(instEncryptedBytes9));
            byte[] instEncryptedBytes10 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, insts1Bytes10);
            encinst10 = new String(SimpleBase64Encoder.encode(instEncryptedBytes10));

            byte[] fromDateEncryptedBytes1 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fromDateBytes1);
            encfromdate1 = new String(SimpleBase64Encoder.encode(fromDateEncryptedBytes1));
            byte[] fromDateEncryptedBytes2 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fromDateBytes2);
            encfromdate2 = new String(SimpleBase64Encoder.encode(fromDateEncryptedBytes2));
            byte[] fromDateEncryptedBytes3 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fromDateBytes3);
            encfromdate3 = new String(SimpleBase64Encoder.encode(fromDateEncryptedBytes3));
            byte[] fromDateEncryptedBytes4 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fromDateBytes4);
            encfromdate4 = new String(SimpleBase64Encoder.encode(fromDateEncryptedBytes4));
            byte[] fromDateEncryptedBytes5 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fromDateBytes5);
            encfromdate5 = new String(SimpleBase64Encoder.encode(fromDateEncryptedBytes5));
            byte[] fromDateEncryptedBytes6 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fromDateBytes6);
            encfromdate6 = new String(SimpleBase64Encoder.encode(fromDateEncryptedBytes6));
            byte[] fromDateEncryptedBytes7 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fromDateBytes7);
            encfromdate7 = new String(SimpleBase64Encoder.encode(fromDateEncryptedBytes7));
            byte[] fromDateEncryptedBytes8 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fromDateBytes8);
            encfromdate8 = new String(SimpleBase64Encoder.encode(fromDateEncryptedBytes8));
            byte[] fromDateEncryptedBytes9 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fromDateBytes9);
            encfromdate9 = new String(SimpleBase64Encoder.encode(fromDateEncryptedBytes9));
            byte[] fromDateEncryptedBytes10 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fromDateBytes10);
            encfromdate10 = new String(SimpleBase64Encoder.encode(fromDateEncryptedBytes10));

            byte[] toDateEncryptedBytes1 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, toDateBytes1);
            enctodate1 = new String(SimpleBase64Encoder.encode(toDateEncryptedBytes1));
            byte[] toDateEncryptedBytes2 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, toDateBytes2);
            enctodate2 = new String(SimpleBase64Encoder.encode(toDateEncryptedBytes2));
            byte[] toDateEncryptedBytes3 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, toDateBytes3);
            enctodate3 = new String(SimpleBase64Encoder.encode(toDateEncryptedBytes3));
            byte[] toDateEncryptedBytes4 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, toDateBytes4);
            enctodate4 = new String(SimpleBase64Encoder.encode(toDateEncryptedBytes4));
            byte[] toDateEncryptedBytes5 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, toDateBytes5);
            enctodate5 = new String(SimpleBase64Encoder.encode(toDateEncryptedBytes5));
            byte[] toDateEncryptedBytes6 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, toDateBytes6);
            enctodate6 = new String(SimpleBase64Encoder.encode(toDateEncryptedBytes6));
            byte[] toDateEncryptedBytes7 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, toDateBytes7);
            enctodate7 = new String(SimpleBase64Encoder.encode(toDateEncryptedBytes7));
            byte[] toDateEncryptedBytes8 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, toDateBytes8);
            enctodate8 = new String(SimpleBase64Encoder.encode(toDateEncryptedBytes8));
            byte[] toDateEncryptedBytes9 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, toDateBytes9);
            enctodate9 = new String(SimpleBase64Encoder.encode(toDateEncryptedBytes9));
            byte[] toDateEncryptedBytes10 = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, toDateBytes10);
            enctodate10 = new String(SimpleBase64Encoder.encode(toDateEncryptedBytes10));


        } catch (Exception e) {
            Log.d(HRlog," "+e.getMessage());
        }
    }


    class saveHrExperienceTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("p1", encpost1));
            params.add(new BasicNameValuePair("i1", encinst1));
            params.add(new BasicNameValuePair("f1", encfromdate1));
            params.add(new BasicNameValuePair("t1", enctodate1));

            params.add(new BasicNameValuePair("p2", encpost2));
            params.add(new BasicNameValuePair("i2", encinst2));
            params.add(new BasicNameValuePair("f2", encfromdate2));
            params.add(new BasicNameValuePair("t2", enctodate2));

            params.add(new BasicNameValuePair("p3", encpost3));
            params.add(new BasicNameValuePair("i3", encinst3));
            params.add(new BasicNameValuePair("f3", encfromdate3));
            params.add(new BasicNameValuePair("t3", enctodate3));

            params.add(new BasicNameValuePair("p4", encpost4));
            params.add(new BasicNameValuePair("i4", encinst4));
            params.add(new BasicNameValuePair("f4", encfromdate4));
            params.add(new BasicNameValuePair("t4", enctodate4));

            params.add(new BasicNameValuePair("p5", encpost5));
            params.add(new BasicNameValuePair("i5", encinst5));
            params.add(new BasicNameValuePair("f5", encfromdate5));
            params.add(new BasicNameValuePair("t5", enctodate5));
            //5
            params.add(new BasicNameValuePair("p6", encpost6));
            params.add(new BasicNameValuePair("i6", encinst6));
            params.add(new BasicNameValuePair("f6", encfromdate6));
            params.add(new BasicNameValuePair("t6", enctodate6));

            params.add(new BasicNameValuePair("p7", encpost7));
            params.add(new BasicNameValuePair("i7", encinst7));
            params.add(new BasicNameValuePair("f7", encfromdate7));
            params.add(new BasicNameValuePair("t7", enctodate7));

            params.add(new BasicNameValuePair("p8", encpost8));
            params.add(new BasicNameValuePair("i8", encinst8));
            params.add(new BasicNameValuePair("f8", encfromdate8));
            params.add(new BasicNameValuePair("t8", enctodate8));

            params.add(new BasicNameValuePair("p9", encpost9));
            params.add(new BasicNameValuePair("i9", encinst9));
            params.add(new BasicNameValuePair("f9", encfromdate9));
            params.add(new BasicNameValuePair("t9", enctodate9));

            params.add(new BasicNameValuePair("p10", encpost10));
            params.add(new BasicNameValuePair("i10", encinst10));
            params.add(new BasicNameValuePair("f10", encfromdate10));
            params.add(new BasicNameValuePair("t10", enctodate10));


            json = jParser.makeHttpRequest(MyConstants.url_saveHrExperience, "GET", params);


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
                Toast.makeText(HR_Experiences.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();


//                ProfileRole r=new ProfileRole();
//                String role=r.getRole();
                if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("hr"))
                    setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);


                HR_Experiences.super.onBackPressed();
            }
        }
    }
}

