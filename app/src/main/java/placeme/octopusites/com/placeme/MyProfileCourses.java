package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import placeme.octopusites.com.placeme.modal.Courses;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfileCourses extends AppCompatActivity {

    int coursecount = 0;
    View addmorecourse;

    String username, role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    EditText fromdate1, todate1, fromdate2, todate2, fromdate3, todate3, fromdate4, todate4, fromdate5, todate5, fromdate6, todate6, fromdate7, todate7, fromdate8, todate8, fromdate9, todate9, fromdate10, todate10;
    EditText name1, inst1, name2, inst2, name3, inst3, name4, inst4, name5, inst5, name6, inst6, name7, inst7, name8, inst8, name9, inst9, name10, inst10;
    TextInputLayout nameinput1, instinput1, fromdateinput1, todateinput1, nameinput2, instinput2, fromdateinput2, todateinput2, nameinput3, instinput3, fromdateinput3, todateinput3, nameinput4, instinput4, fromdateinput4, todateinput4, nameinput5, instinput5, fromdateinput5, todateinput5, nameinput6, instinput6, fromdateinput6, todateinput6, nameinput7, instinput7, fromdateinput7, todateinput7, nameinput8, instinput8, fromdateinput8, todateinput8, nameinput9, instinput9, fromdateinput9, todateinput9, nameinput10, instinput10, fromdateinput10, todateinput10;


    String sname1 = "", sinst1 = "", sname2 = "", sinst2 = "", sname3 = "", sinst3 = "", sname4 = "", sinst4 = "", sname5 = "", sinst5 = "", sname6 = "", sinst6 = "", sname7 = "", sinst7 = "", sname8 = "", sinst8 = "", sname9 = "", sinst9 = "", sname10 = "", sinst10 = "", sfromdate1 = "", stodate1 = "", sfromdate2 = "", stodate2 = "", sfromdate3 = "", stodate3 = "", sfromdate4 = "", stodate4 = "", sfromdate5 = "", stodate5 = "", sfromdate6 = "", stodate6 = "", sfromdate7 = "", stodate7 = "", sfromdate8 = "", stodate8 = "", sfromdate9 = "", stodate9 = "", sfromdate10 = "", stodate10 = "";
    String encname1, encinst1, encname2, encinst2, encname3, encinst3, encname4, encinst4, encname5, encinst5, encname6, encinst6, encname7, encinst7, encname8, encinst8, encname9, encinst9, encname10, encinst10, encfromdate1, enctodate1, encfromdate2, enctodate2, encfromdate3, enctodate3, encfromdate4, enctodate4, encfromdate5, enctodate5, encfromdate6, enctodate6, encfromdate7, enctodate7, encfromdate8, enctodate8, encfromdate9, enctodate9, encfromdate10, enctodate10;
    ;
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview, trash6selectionview, trash7selectionview, trash8selectionview, trash9selectionview, trash10selectionview;
    int edittedFlag = 0;
    ;
    int d = 0;
    StudentData s = new StudentData();

    ArrayList<Courses> coursesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_courses);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username = MySharedPreferencesManager.getUsername(this);
        role = MySharedPreferencesManager.getRole(this);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Course Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        trash1selectionview = (View) findViewById(R.id.trash1selectionview);
        trash2selectionview = (View) findViewById(R.id.trash2selectionview);
        trash3selectionview = (View) findViewById(R.id.trash3selectionview);
        trash4selectionview = (View) findViewById(R.id.trash4selectionview);
        trash5selectionview = (View) findViewById(R.id.trash5selectionview);
        trash6selectionview = (View) findViewById(R.id.trash6selectionview);
        trash7selectionview = (View) findViewById(R.id.trash7selectionview);
        trash8selectionview = (View) findViewById(R.id.trash8selectionview);
        trash9selectionview = (View) findViewById(R.id.trash9selectionview);
        trash10selectionview = (View) findViewById(R.id.trash10selectionview);

        name1 = (EditText) findViewById(R.id.name1);
        inst1 = (EditText) findViewById(R.id.inst1);
        name2 = (EditText) findViewById(R.id.name2);
        inst2 = (EditText) findViewById(R.id.inst2);
        name3 = (EditText) findViewById(R.id.name3);
        inst3 = (EditText) findViewById(R.id.inst3);
        name4 = (EditText) findViewById(R.id.name4);
        inst4 = (EditText) findViewById(R.id.inst4);
        name5 = (EditText) findViewById(R.id.name5);
        inst5 = (EditText) findViewById(R.id.inst5);
        name6 = (EditText) findViewById(R.id.name6);
        inst6 = (EditText) findViewById(R.id.inst6);
        name7 = (EditText) findViewById(R.id.name7);
        inst7 = (EditText) findViewById(R.id.inst7);
        name8 = (EditText) findViewById(R.id.name8);
        inst8 = (EditText) findViewById(R.id.inst8);
        name9 = (EditText) findViewById(R.id.name9);
        inst9 = (EditText) findViewById(R.id.inst9);
        name10 = (EditText) findViewById(R.id.name10);
        inst10 = (EditText) findViewById(R.id.inst10);

        fromdate1 = (EditText) findViewById(R.id.fromdate1);
        todate1 = (EditText) findViewById(R.id.todate1);
        fromdate2 = (EditText) findViewById(R.id.fromdate2);
        todate2 = (EditText) findViewById(R.id.todate2);
        fromdate3 = (EditText) findViewById(R.id.fromdate3);
        todate3 = (EditText) findViewById(R.id.todate3);
        fromdate4 = (EditText) findViewById(R.id.fromdate4);
        todate4 = (EditText) findViewById(R.id.todate4);
        fromdate5 = (EditText) findViewById(R.id.fromdate5);
        todate5 = (EditText) findViewById(R.id.todate5);
        fromdate6 = (EditText) findViewById(R.id.fromdate6);
        todate6 = (EditText) findViewById(R.id.todate6);
        fromdate7 = (EditText) findViewById(R.id.fromdate7);
        todate7 = (EditText) findViewById(R.id.todate7);
        fromdate8 = (EditText) findViewById(R.id.fromdate8);
        todate8 = (EditText) findViewById(R.id.todate8);
        fromdate9 = (EditText) findViewById(R.id.fromdate9);
        todate9 = (EditText) findViewById(R.id.todate9);
        fromdate10 = (EditText) findViewById(R.id.fromdate10);
        todate10 = (EditText) findViewById(R.id.todate10);


        nameinput1 = (TextInputLayout) findViewById(R.id.nameinput1);
        instinput1 = (TextInputLayout) findViewById(R.id.instinput1);
        fromdateinput1 = (TextInputLayout) findViewById(R.id.fromdateinput1);
        todateinput1 = (TextInputLayout) findViewById(R.id.todateinput1);

        nameinput2 = (TextInputLayout) findViewById(R.id.nameinput2);
        instinput2 = (TextInputLayout) findViewById(R.id.instinput2);
        fromdateinput2 = (TextInputLayout) findViewById(R.id.fromdateinput2);
        todateinput2 = (TextInputLayout) findViewById(R.id.todateinput2);
        nameinput3 = (TextInputLayout) findViewById(R.id.nameinput3);
        instinput3 = (TextInputLayout) findViewById(R.id.instinput3);
        fromdateinput3 = (TextInputLayout) findViewById(R.id.fromdateinput3);
        todateinput3 = (TextInputLayout) findViewById(R.id.todateinput3);
        nameinput4 = (TextInputLayout) findViewById(R.id.nameinput4);
        instinput4 = (TextInputLayout) findViewById(R.id.instinput4);
        fromdateinput4 = (TextInputLayout) findViewById(R.id.fromdateinput4);
        todateinput4 = (TextInputLayout) findViewById(R.id.todateinput4);
        nameinput5 = (TextInputLayout) findViewById(R.id.nameinput5);
        instinput5 = (TextInputLayout) findViewById(R.id.instinput5);
        fromdateinput5 = (TextInputLayout) findViewById(R.id.fromdateinput5);
        todateinput5 = (TextInputLayout) findViewById(R.id.todateinput5);
        nameinput6 = (TextInputLayout) findViewById(R.id.nameinput6);
        instinput6 = (TextInputLayout) findViewById(R.id.instinput6);
        fromdateinput6 = (TextInputLayout) findViewById(R.id.fromdateinput6);
        todateinput6 = (TextInputLayout) findViewById(R.id.todateinput6);
        nameinput7 = (TextInputLayout) findViewById(R.id.nameinput7);
        instinput7 = (TextInputLayout) findViewById(R.id.instinput7);
        fromdateinput7 = (TextInputLayout) findViewById(R.id.fromdateinput7);
        todateinput7 = (TextInputLayout) findViewById(R.id.todateinput7);
        nameinput8 = (TextInputLayout) findViewById(R.id.nameinput8);
        instinput8 = (TextInputLayout) findViewById(R.id.instinput8);
        fromdateinput8 = (TextInputLayout) findViewById(R.id.fromdateinput8);
        todateinput8 = (TextInputLayout) findViewById(R.id.todateinput8);
        nameinput9 = (TextInputLayout) findViewById(R.id.nameinput9);
        instinput9 = (TextInputLayout) findViewById(R.id.instinput9);
        fromdateinput9 = (TextInputLayout) findViewById(R.id.fromdateinput9);
        todateinput9 = (TextInputLayout) findViewById(R.id.todateinput9);
        nameinput10 = (TextInputLayout) findViewById(R.id.nameinput10);
        instinput10 = (TextInputLayout) findViewById(R.id.instinput10);
        fromdateinput10 = (TextInputLayout) findViewById(R.id.fromdateinput10);
        todateinput10 = (TextInputLayout) findViewById(R.id.todateinput10);


        fromdate1.setTypeface(MyConstants.getBold(this));
        todate1.setTypeface(MyConstants.getBold(this));
        fromdate2.setTypeface(MyConstants.getBold(this));
        todate2.setTypeface(MyConstants.getBold(this));
        fromdate3.setTypeface(MyConstants.getBold(this));
        todate3.setTypeface(MyConstants.getBold(this));
        fromdate4.setTypeface(MyConstants.getBold(this));
        todate4.setTypeface(MyConstants.getBold(this));
        fromdate5.setTypeface(MyConstants.getBold(this));
        todate5.setTypeface(MyConstants.getBold(this));
        fromdate6.setTypeface(MyConstants.getBold(this));
        todate6.setTypeface(MyConstants.getBold(this));
        fromdate7.setTypeface(MyConstants.getBold(this));
        todate7.setTypeface(MyConstants.getBold(this));
        fromdate8.setTypeface(MyConstants.getBold(this));
        todate8.setTypeface(MyConstants.getBold(this));
        fromdate9.setTypeface(MyConstants.getBold(this));
        todate9.setTypeface(MyConstants.getBold(this));
        fromdate10.setTypeface(MyConstants.getBold(this));
        todate10.setTypeface(MyConstants.getBold(this));
        name1.setTypeface(MyConstants.getBold(this));
        inst1.setTypeface(MyConstants.getBold(this));
        name2.setTypeface(MyConstants.getBold(this));
        inst2.setTypeface(MyConstants.getBold(this));
        name3.setTypeface(MyConstants.getBold(this));
        inst3.setTypeface(MyConstants.getBold(this));
        name4.setTypeface(MyConstants.getBold(this));
        inst4.setTypeface(MyConstants.getBold(this));
        name5.setTypeface(MyConstants.getBold(this));
        inst5.setTypeface(MyConstants.getBold(this));
        name6.setTypeface(MyConstants.getBold(this));
        inst6.setTypeface(MyConstants.getBold(this));
        name7.setTypeface(MyConstants.getBold(this));
        inst7.setTypeface(MyConstants.getBold(this));
        name8.setTypeface(MyConstants.getBold(this));
        inst8.setTypeface(MyConstants.getBold(this));
        name9.setTypeface(MyConstants.getBold(this));
        inst9.setTypeface(MyConstants.getBold(this));
        name10.setTypeface(MyConstants.getBold(this));
        inst10.setTypeface(MyConstants.getBold(this));


        nameinput1.setTypeface(MyConstants.getLight(this));
        instinput1.setTypeface(MyConstants.getLight(this));
        fromdateinput1.setTypeface(MyConstants.getLight(this));
        todateinput1.setTypeface(MyConstants.getLight(this));
        nameinput2.setTypeface(MyConstants.getLight(this));
        instinput2.setTypeface(MyConstants.getLight(this));
        fromdateinput2.setTypeface(MyConstants.getLight(this));
        todateinput2.setTypeface(MyConstants.getLight(this));
        nameinput3.setTypeface(MyConstants.getLight(this));
        instinput3.setTypeface(MyConstants.getLight(this));
        fromdateinput3.setTypeface(MyConstants.getLight(this));
        todateinput3.setTypeface(MyConstants.getLight(this));
        nameinput4.setTypeface(MyConstants.getLight(this));
        instinput4.setTypeface(MyConstants.getLight(this));
        fromdateinput4.setTypeface(MyConstants.getLight(this));
        todateinput4.setTypeface(MyConstants.getLight(this));
        nameinput5.setTypeface(MyConstants.getLight(this));
        instinput5.setTypeface(MyConstants.getLight(this));
        fromdateinput5.setTypeface(MyConstants.getLight(this));
        todateinput5.setTypeface(MyConstants.getLight(this));
        nameinput6.setTypeface(MyConstants.getLight(this));
        instinput6.setTypeface(MyConstants.getLight(this));
        fromdateinput6.setTypeface(MyConstants.getLight(this));
        todateinput6.setTypeface(MyConstants.getLight(this));
        nameinput7.setTypeface(MyConstants.getLight(this));
        instinput7.setTypeface(MyConstants.getLight(this));
        fromdateinput7.setTypeface(MyConstants.getLight(this));
        todateinput7.setTypeface(MyConstants.getLight(this));
        nameinput8.setTypeface(MyConstants.getLight(this));
        instinput8.setTypeface(MyConstants.getLight(this));
        fromdateinput8.setTypeface(MyConstants.getLight(this));
        todateinput8.setTypeface(MyConstants.getLight(this));
        nameinput9.setTypeface(MyConstants.getLight(this));
        instinput9.setTypeface(MyConstants.getLight(this));
        fromdateinput9.setTypeface(MyConstants.getLight(this));
        todateinput9.setTypeface(MyConstants.getLight(this));
        nameinput10.setTypeface(MyConstants.getLight(this));
        instinput10.setTypeface(MyConstants.getLight(this));
        fromdateinput10.setTypeface(MyConstants.getLight(this));
        todateinput10.setTypeface(MyConstants.getLight(this));


        TextView coursetxt = (TextView) findViewById(R.id.coursetxt);
        coursetxt.setTypeface(MyConstants.getBold(this));
        TextView addmorecoursetxt = (TextView) findViewById(R.id.addmorecoursetxt);
        addmorecoursetxt.setTypeface(MyConstants.getBold(this));

        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 1;
                showDeletDialog();
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


        name1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                nameinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fromdate1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                fromdateinput1.setError(null);
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
                edittedFlag = 1;
                todateinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                nameinput2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instinput2.setError(null);
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
                edittedFlag = 1;
                fromdateinput2.setError(null);
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
                edittedFlag = 1;
                todateinput2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                nameinput3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instinput3.setError(null);
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
                edittedFlag = 1;
                fromdateinput3.setError(null);
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
                edittedFlag = 1;
                todateinput3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                nameinput4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instinput4.setError(null);
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
                edittedFlag = 1;
                fromdateinput4.setError(null);
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
                edittedFlag = 1;
                todateinput4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                nameinput5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instinput5.setError(null);
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
                edittedFlag = 1;
                fromdateinput5.setError(null);
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
                edittedFlag = 1;
                todateinput5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                nameinput6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instinput6.setError(null);
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
                edittedFlag = 1;
                fromdateinput6.setError(null);
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
                edittedFlag = 1;
                todateinput6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                nameinput7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instinput7.setError(null);
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
                edittedFlag = 1;
                fromdateinput7.setError(null);
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
                edittedFlag = 1;
                todateinput7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                nameinput8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instinput8.setError(null);
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
                edittedFlag = 1;
                fromdateinput8.setError(null);
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
                edittedFlag = 1;
                todateinput8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                nameinput9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instinput9.setError(null);
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
                edittedFlag = 1;
                fromdateinput9.setError(null);
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
                edittedFlag = 1;
                todateinput9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                nameinput10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inst10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                instinput10.setError(null);
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
                edittedFlag = 1;
                fromdateinput10.setError(null);
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
                edittedFlag = 1;
                todateinput10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fromdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdateinput1.setError(null);
                // 1- id   2- isFromDateSelected 3-fromYear   4- month in str

                String toDate = todate1.getText().toString();
                showDateDialog(fromdate1, false, 0, "", toDate, fromdateinput1);

            }
        });
        todate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todateinput1.setError(null);
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
                Log.d("TAG", "onClick: id before " + todateinput1);
                showDateDialog(todate1, isFromDateSelected, fromYear, fromMonth, "", todateinput1);
            }
        });


        fromdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdateinput2.setError(null);
                String toDate = todate2.getText().toString();
                showDateDialog(fromdate2, false, 0, "", toDate, fromdateinput2);

            }
        });
        todate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todateinput2.setError(null);
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
                showDateDialog(todate2, isFromDateSelected, fromYear, fromMonth, "", todateinput2);
            }
        });


        fromdate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdateinput3.setError(null);
                String toDate = todate3.getText().toString();
                showDateDialog(fromdate3, false, 0, "", toDate, fromdateinput3);

            }
        });
        todate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todateinput3.setError(null);
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
                showDateDialog(todate3, isFromDateSelected, fromYear, fromMonth, "", todateinput3);
            }
        });


        fromdate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdateinput4.setError(null);
                String toDate = todate4.getText().toString();
                showDateDialog(fromdate4, false, 0, "", toDate, fromdateinput4);
            }
        });
        todate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todateinput4.setError(null);
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
                showDateDialog(todate4, isFromDateSelected, fromYear, fromMonth, "", todateinput4);
            }
        });


        fromdate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdateinput5.setError(null);
                String toDate = todate5.getText().toString();
                showDateDialog(fromdate5, false, 0, "", toDate, fromdateinput5);

            }
        });
        todate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                todateinput5.setError(null);
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
                showDateDialog(todate5, isFromDateSelected, fromYear, fromMonth, "", todateinput5);
            }
        });


        fromdate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdateinput6.setError(null);
                String toDate = todate6.getText().toString();
                showDateDialog(fromdate6, false, 0, "", toDate, fromdateinput6);

            }
        });
        todate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                todateinput6.setError(null);
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
                showDateDialog(todate6, isFromDateSelected, fromYear, fromMonth, "", todateinput6);
            }
        });


        fromdate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdateinput7.setError(null);
                String toDate = todate7.getText().toString();
                showDateDialog(fromdate7, false, 0, "", toDate, fromdateinput7);
            }
        });
        todate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todateinput7.setError(null);
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
                showDateDialog(todate7, isFromDateSelected, fromYear, fromMonth, "", todateinput7);
            }
        });


        fromdate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdateinput8.setError(null);
                String toDate = todate8.getText().toString();
                showDateDialog(fromdate8, false, 0, "", toDate, fromdateinput8);
            }
        });
        todate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todateinput8.setError(null);
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
                showDateDialog(todate8, isFromDateSelected, fromYear, fromMonth, "", todateinput8);
            }
        });


        fromdate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdateinput9.setError(null);
                String toDate = todate9.getText().toString();
                showDateDialog(fromdate9, false, 0, "", toDate, fromdateinput9);
            }
        });
        todate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todateinput9.setError(null);
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
                showDateDialog(todate9, isFromDateSelected, fromYear, fromMonth, "", todateinput9);
            }
        });


        fromdate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdateinput10.setError(null);
                String toDate = todate10.getText().toString();
                showDateDialog(fromdate10, false, 0, "", toDate, fromdateinput10);

            }
        });
        todate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todateinput10.setError(null);
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
                showDateDialog(todate10, isFromDateSelected, fromYear, fromMonth, "", todateinput10);
            }
        });


        addmorecourse = (View) findViewById(R.id.addmorecourse);
        addmorecourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (coursecount == 0) {
                    if (name1.getText().toString() != null && inst1.getText().toString() != null && fromdate1.getText().toString() != null && todate1.getText().toString() != null) {
                        if (!name1.getText().toString().equals("") && !inst1.getText().toString().equals("") && !fromdate1.getText().toString().equals("") && !todate1.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.courseline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl2);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            coursecount++;
                        } else
                            Toast.makeText(MyProfileCourses.this, "Please fill the first Course", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileCourses.this, "Please fill the first Course", Toast.LENGTH_SHORT).show();

                } else if (coursecount == 1) {
                    if (name2.getText().toString() != null && inst2.getText().toString() != null && fromdate2.getText().toString() != null && todate2.getText().toString() != null) {
                        if (!name2.getText().toString().equals("") && !inst2.getText().toString().equals("") && !fromdate2.getText().toString().equals("") && !todate2.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.courseline2);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl3);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            coursecount++;
                        } else
                            Toast.makeText(MyProfileCourses.this, "Please fill the Second Course", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileCourses.this, "Please fill the Second Course", Toast.LENGTH_SHORT).show();


                } else if (coursecount == 2) {
                    if (name3.getText().toString() != null && inst3.getText().toString() != null && fromdate3.getText().toString() != null && todate3.getText().toString() != null) {
                        if (!name3.getText().toString().equals("") && !inst3.getText().toString().equals("") && !fromdate3.getText().toString().equals("") && !todate3.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.courseline3);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl4);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            coursecount++;
                        } else
                            Toast.makeText(MyProfileCourses.this, "Please fill the Third Course", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileCourses.this, "Please fill the Third Course", Toast.LENGTH_SHORT).show();


                } else if (coursecount == 3) {
                    if (name4.getText().toString() != null && inst4.getText().toString() != null && fromdate4.getText().toString() != null && todate4.getText().toString() != null) {
                        if (!name4.getText().toString().equals("") && !inst4.getText().toString().equals("") && !fromdate4.getText().toString().equals("") && !todate4.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.courseline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl5);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            coursecount++;
                        } else
                            Toast.makeText(MyProfileCourses.this, "Please fill the Fourth Course", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileCourses.this, "Please fill the Fourth Course", Toast.LENGTH_SHORT).show();


                } else if (coursecount == 4) {
                    if (name5.getText().toString() != null && inst5.getText().toString() != null && fromdate5.getText().toString() != null && todate5.getText().toString() != null) {
                        if (!name5.getText().toString().equals("") && !inst5.getText().toString().equals("") && !fromdate5.getText().toString().equals("") && !todate5.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.courseline5);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl6);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            coursecount++;
                        } else
                            Toast.makeText(MyProfileCourses.this, "Please fill the Fifth Course", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileCourses.this, "Please fill the Fifth Course", Toast.LENGTH_SHORT).show();

                } else if (coursecount == 5) {
                    if (name6.getText().toString() != null && inst6.getText().toString() != null && fromdate6.getText().toString() != null && todate6.getText().toString() != null) {
                        if (!name6.getText().toString().equals("") && !inst6.getText().toString().equals("") && !fromdate6.getText().toString().equals("") && !todate6.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.courseline6);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl7);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            coursecount++;
                        } else
                            Toast.makeText(MyProfileCourses.this, "Please fill the Sixth Course", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileCourses.this, "Please fill the Sixth Course", Toast.LENGTH_SHORT).show();


                } else if (coursecount == 6) {
                    if (name7.getText().toString() != null && inst7.getText().toString() != null && fromdate7.getText().toString() != null && todate7.getText().toString() != null) {
                        if (!name7.getText().toString().equals("") && !inst7.getText().toString().equals("") && !fromdate7.getText().toString().equals("") && !todate7.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.courseline7);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl8);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            coursecount++;
                        } else
                            Toast.makeText(MyProfileCourses.this, "Please fill the Seventh Course", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileCourses.this, "Please fill the Seventh Course", Toast.LENGTH_SHORT).show();


                } else if (coursecount == 7) {
                    if (name8.getText().toString() != null && inst8.getText().toString() != null && fromdate8.getText().toString() != null && todate8.getText().toString() != null) {
                        if (!name8.getText().toString().equals("") && !inst8.getText().toString().equals("") && !fromdate8.getText().toString().equals("") && !todate8.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.courseline8);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl9);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            coursecount++;
                        } else
                            Toast.makeText(MyProfileCourses.this, "Please fill the Eighth Course", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileCourses.this, "Please fill the Eighth Course", Toast.LENGTH_SHORT).show();


                } else if (coursecount == 8) {
                    if (name9.getText().toString() != null && inst9.getText().toString() != null && fromdate9.getText().toString() != null && todate9.getText().toString() != null) {
                        if (!name9.getText().toString().equals("") && !inst9.getText().toString().equals("") && !fromdate9.getText().toString().equals("") && !todate9.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.courseline9);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl10);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            coursecount++;
                            TextView t = (TextView) findViewById(R.id.addmorecoursetxt);
                            ImageView i = (ImageView) findViewById(R.id.addmorecourseimg);
                            addmorecourse.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);
                        } else
                            Toast.makeText(MyProfileCourses.this, "Please fill the Nineth Course", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileCourses.this, "Please fill the Nineth Course", Toast.LENGTH_SHORT).show();


                }

            }
        });

        ScrollView myprofileintroscrollview = (ScrollView) findViewById(R.id.myprofilecourses);
        disableScrollbars(myprofileintroscrollview);


        sname1 = s.getCourse1();
        sinst1 = s.getInst1();
        sfromdate1 = s.getFromdate1();
        stodate1 = s.getTodate1();
        sname2 = s.getCourse2();
        sinst2 = s.getInst2();
        sfromdate2 = s.getFromdate2();
        stodate2 = s.getTodate2();
        sname3 = s.getCourse3();
        sinst3 = s.getInst3();
        sfromdate3 = s.getFromdate3();
        stodate3 = s.getTodate3();
        sname4 = s.getCourse4();
        sinst4 = s.getInst4();
        sfromdate4 = s.getFromdate4();
        stodate4 = s.getTodate4();
        sname5 = s.getCourse5();
        sinst5 = s.getInst5();
        sfromdate5 = s.getFromdate5();
        stodate5 = s.getTodate5();
        sname6 = s.getCourse6();
        sinst6 = s.getInst6();
        sfromdate6 = s.getFromdate6();
        stodate6 = s.getTodate6();
        sname7 = s.getCourse7();
        sinst7 = s.getInst7();
        sfromdate7 = s.getFromdate7();
        stodate7 = s.getTodate7();
        sname8 = s.getCourse8();
        sinst8 = s.getInst8();
        sfromdate8 = s.getFromdate8();
        stodate8 = s.getTodate8();
        sname9 = s.getCourse9();
        sinst9 = s.getInst9();
        sfromdate9 = s.getFromdate9();
        stodate9 = s.getTodate9();
        sname10 = s.getCourse10();
        sinst10 = s.getInst10();
        sfromdate10 = s.getFromdate10();
        stodate10 = s.getTodate10();

        if (sname1 != null) {
            if (sname1.length() > 2) {
                name1.setText(sname1);
                inst1.setText(sinst1);
                fromdate1.setText(sfromdate1);
                todate1.setText(stodate1);
            }
        }
        if (sname2 != null) {
            if (sname2.length() > 2) {
                name2.setText(sname2);
                inst2.setText(sinst2);
                fromdate2.setText(sfromdate2);
                todate2.setText(stodate2);
                View v = (View) findViewById(R.id.courseline1);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl2);
                relativeLayout1.setVisibility(View.VISIBLE);
                coursecount++;
            }
        }
        if (sname3 != null) {
            if (sname3.length() > 2) {
                name3.setText(sname3);
                inst3.setText(sinst3);
                fromdate3.setText(sfromdate3);
                todate3.setText(stodate3);
                View v = (View) findViewById(R.id.courseline2);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl3);
                relativeLayout1.setVisibility(View.VISIBLE);
                coursecount++;
            }
        }
        if (sname4 != null) {
            if (sname4.length() > 2) {
                name4.setText(sname4);
                inst4.setText(sinst4);
                fromdate4.setText(sfromdate4);
                todate4.setText(stodate4);
                View v = (View) findViewById(R.id.courseline3);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl4);
                relativeLayout1.setVisibility(View.VISIBLE);
                coursecount++;
            }
        }
        if (sname5 != null) {
            if (sname5.length() > 2) {
                name5.setText(sname5);
                inst5.setText(sinst5);
                fromdate5.setText(sfromdate5);
                todate5.setText(stodate5);
                View v = (View) findViewById(R.id.courseline4);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl5);
                relativeLayout1.setVisibility(View.VISIBLE);
                coursecount++;
            }
        }
        if (sname6 != null) {
            if (sname6.length() > 2) {
                name6.setText(sname6);
                inst6.setText(sinst6);
                fromdate6.setText(sfromdate6);
                todate6.setText(stodate6);
                View v = (View) findViewById(R.id.courseline5);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl6);
                relativeLayout1.setVisibility(View.VISIBLE);
                coursecount++;
            }
        }
        if (sname7 != null) {
            if (sname7.length() > 2) {
                name7.setText(sname7);
                inst7.setText(sinst7);
                fromdate7.setText(sfromdate7);
                todate7.setText(stodate7);
                View v = (View) findViewById(R.id.courseline6);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl7);
                relativeLayout1.setVisibility(View.VISIBLE);
                coursecount++;
            }
        }
        if (sname8 != null) {
            if (sname8.length() > 2) {
                name8.setText(sname8);
                inst8.setText(sinst8);
                fromdate8.setText(sfromdate8);
                todate8.setText(stodate8);
                View v = (View) findViewById(R.id.courseline7);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl8);
                relativeLayout1.setVisibility(View.VISIBLE);
                coursecount++;
            }
        }
        if (sname9 != null) {
            if (sname9.length() > 2) {
                name9.setText(sname9);
                inst9.setText(sinst9);
                fromdate9.setText(sfromdate9);
                todate9.setText(stodate9);
                View v = (View) findViewById(R.id.courseline8);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl9);
                relativeLayout1.setVisibility(View.VISIBLE);
                coursecount++;
            }
        }
        if (sname10 != null) {
            if (sname10.length() > 2) {
                name10.setText(sname10);
                inst10.setText(sinst10);
                fromdate10.setText(sfromdate10);
                todate10.setText(stodate10);
                View v = (View) findViewById(R.id.courseline9);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.courserl10);
                relativeLayout1.setVisibility(View.VISIBLE);
                coursecount++;
                TextView t = (TextView) findViewById(R.id.addmorecoursetxt);
                ImageView i = (ImageView) findViewById(R.id.addmorecourseimg);
                addmorecourse.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                i.setVisibility(View.GONE);
            }
        }
        edittedFlag = 0;
    }

    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }

    void showDeletDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this Course ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag = 1;
                                deleteCourse();
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

    void deleteCourse() {
        View v = (View) findViewById(R.id.courseline9);
        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View) findViewById(R.id.courseline9);
            v1.setVisibility(View.GONE);

            RelativeLayout courserl = (RelativeLayout) findViewById(R.id.courserl10);
            courserl.setVisibility(View.GONE);

            coursecount--;

            TextView t = (TextView) findViewById(R.id.addmorecoursetxt);
            ImageView i = (ImageView) findViewById(R.id.addmorecourseimg);
            addmorecourse.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        } else {
            v = (View) findViewById(R.id.courseline8);
            if (v.getVisibility() == View.VISIBLE) {


                View v1 = (View) findViewById(R.id.courseline8);
                v1.setVisibility(View.GONE);

                RelativeLayout courserl = (RelativeLayout) findViewById(R.id.courserl9);
                courserl.setVisibility(View.GONE);

                coursecount--;

            } else {
                v = (View) findViewById(R.id.courseline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View) findViewById(R.id.courseline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout courserl = (RelativeLayout) findViewById(R.id.courserl8);
                    courserl.setVisibility(View.GONE);

                    coursecount--;
                } else {
                    v = (View) findViewById(R.id.courseline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View) findViewById(R.id.courseline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout courserl = (RelativeLayout) findViewById(R.id.courserl7);
                        courserl.setVisibility(View.GONE);

                        coursecount--;

                    } else {
                        v = (View) findViewById(R.id.courseline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) findViewById(R.id.courseline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout courserl = (RelativeLayout) findViewById(R.id.courserl6);
                            courserl.setVisibility(View.GONE);

                            coursecount--;


                        } else {
                            v = (View) findViewById(R.id.courseline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) findViewById(R.id.courseline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout courserl = (RelativeLayout) findViewById(R.id.courserl5);
                                courserl.setVisibility(View.GONE);

                                coursecount--;
                            } else {
                                v = (View) findViewById(R.id.courseline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) findViewById(R.id.courseline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout courserl = (RelativeLayout) findViewById(R.id.courserl4);
                                    courserl.setVisibility(View.GONE);

                                    coursecount--;

                                } else {
                                    v = (View) findViewById(R.id.courseline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) findViewById(R.id.courseline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout courserl = (RelativeLayout) findViewById(R.id.courserl3);
                                        courserl.setVisibility(View.GONE);

                                        coursecount--;

                                    } else {
                                        v = (View) findViewById(R.id.courseline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1 = (View) findViewById(R.id.courseline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout courserl = (RelativeLayout) findViewById(R.id.courserl2);
                                            courserl.setVisibility(View.GONE);

                                            coursecount--;


                                        }
//                                  if(coursecount==0)
                                        else {
                                            name1.setText("");
                                            inst1.setText("");
                                            fromdate1.setText("");
                                            todate1.setText("");
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
            name10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");

        } else if (d == 9) {
            sname10 = name10.getText().toString();
            sinst10 = inst10.getText().toString();
            sfromdate10 = fromdate10.getText().toString();
            stodate10 = todate10.getText().toString();

            if (sname10.length() > 2) {
                sname9 = sname10;
                sinst9 = sinst10;
                sfromdate9 = sfromdate10;
                stodate9 = stodate10;

                name10.setText("");
                inst10.setText("");
                fromdate10.setText("");
                todate10.setText("");

                name9.setText(sname9);
                inst9.setText(sinst9);
                fromdate9.setText(sfromdate9);
                todate9.setText(stodate9);

            }

        } else if (d == 8) {
            sname10 = name10.getText().toString();
            sinst10 = inst10.getText().toString();
            sfromdate10 = fromdate10.getText().toString();
            stodate10 = todate10.getText().toString();
            sname9 = name9.getText().toString();
            sinst9 = inst9.getText().toString();
            sfromdate9 = fromdate9.getText().toString();
            stodate9 = todate9.getText().toString();

            if (sname9.length() > 2) {
                sname8 = sname9;
                sinst8 = sinst9;
                sfromdate8 = sfromdate9;
                stodate8 = stodate9;

                name9.setText("");
                inst9.setText("");
                fromdate9.setText("");
                todate9.setText("");

                name8.setText(sname8);
                inst8.setText(sinst8);
                fromdate8.setText(sfromdate8);
                todate8.setText(stodate8);

            }
            if (sname10.length() > 2) {
                sname9 = sname10;
                sinst9 = sinst10;
                sfromdate9 = sfromdate10;
                stodate9 = stodate10;

                name10.setText("");
                inst10.setText("");
                fromdate10.setText("");
                todate10.setText("");

                name9.setText(sname9);
                inst9.setText(sinst9);
                fromdate9.setText(sfromdate9);
                todate9.setText(stodate9);

            }
        } else if (d == 7) {
            sname10 = name10.getText().toString();
            sinst10 = inst10.getText().toString();
            sfromdate10 = fromdate10.getText().toString();
            stodate10 = todate10.getText().toString();
            sname9 = name9.getText().toString();
            sinst9 = inst9.getText().toString();
            sfromdate9 = fromdate9.getText().toString();
            stodate9 = todate9.getText().toString();
            sname8 = name8.getText().toString();
            sinst8 = inst8.getText().toString();
            sfromdate8 = fromdate8.getText().toString();
            stodate8 = todate8.getText().toString();

            if (sname8.length() > 2) {
                sname7 = sname8;
                sinst7 = sinst8;
                sfromdate7 = sfromdate8;
                stodate7 = stodate8;

                name8.setText("");
                inst8.setText("");
                fromdate8.setText("");
                todate8.setText("");

                name7.setText(sname7);
                inst7.setText(sinst7);
                fromdate7.setText(sfromdate7);
                todate7.setText(stodate7);

            }
            if (sname9.length() > 2) {
                sname8 = sname9;
                sinst8 = sinst9;
                sfromdate8 = sfromdate9;
                stodate8 = stodate9;

                name9.setText("");
                inst9.setText("");
                fromdate9.setText("");
                todate9.setText("");

                name8.setText(sname8);
                inst8.setText(sinst8);
                fromdate8.setText(sfromdate8);
                todate8.setText(stodate8);

            }
            if (sname10.length() > 2) {
                sname9 = sname10;
                sinst9 = sinst10;
                sfromdate9 = sfromdate10;
                stodate9 = stodate10;

                name10.setText("");
                inst10.setText("");
                fromdate10.setText("");
                todate10.setText("");

                name9.setText(sname9);
                inst9.setText(sinst9);
                fromdate9.setText(sfromdate9);
                todate9.setText(stodate9);

            }
        } else if (d == 6) {
            sname10 = name10.getText().toString();
            sinst10 = inst10.getText().toString();
            sfromdate10 = fromdate10.getText().toString();
            stodate10 = todate10.getText().toString();
            sname9 = name9.getText().toString();
            sinst9 = inst9.getText().toString();
            sfromdate9 = fromdate9.getText().toString();
            stodate9 = todate9.getText().toString();
            sname8 = name8.getText().toString();
            sinst8 = inst8.getText().toString();
            sfromdate8 = fromdate8.getText().toString();
            stodate8 = todate8.getText().toString();
            sname7 = name7.getText().toString();
            sinst7 = inst7.getText().toString();
            sfromdate7 = fromdate7.getText().toString();
            stodate7 = todate7.getText().toString();

            if (sname7.length() > 2) {
                sname6 = sname7;
                sinst6 = sinst7;
                sfromdate6 = sfromdate7;
                stodate6 = stodate7;

                name7.setText("");
                inst7.setText("");
                fromdate7.setText("");
                todate7.setText("");

                name6.setText(sname6);
                inst6.setText(sinst6);
                fromdate6.setText(sfromdate6);
                todate6.setText(stodate6);

            }
            if (sname8.length() > 2) {
                sname7 = sname8;
                sinst7 = sinst8;
                sfromdate7 = sfromdate8;
                stodate7 = stodate8;

                name8.setText("");
                inst8.setText("");
                fromdate8.setText("");
                todate8.setText("");

                name7.setText(sname7);
                inst7.setText(sinst7);
                fromdate7.setText(sfromdate7);
                todate7.setText(stodate7);

            }
            if (sname9.length() > 2) {
                sname8 = sname9;
                sinst8 = sinst9;
                sfromdate8 = sfromdate9;
                stodate8 = stodate9;

                name9.setText("");
                inst9.setText("");
                fromdate9.setText("");
                todate9.setText("");

                name8.setText(sname8);
                inst8.setText(sinst8);
                fromdate8.setText(sfromdate8);
                todate8.setText(stodate8);

            }
            if (sname10.length() > 2) {
                sname9 = sname10;
                sinst9 = sinst10;
                sfromdate9 = sfromdate10;
                stodate9 = stodate10;

                name10.setText("");
                inst10.setText("");
                fromdate10.setText("");
                todate10.setText("");

                name9.setText(sname9);
                inst9.setText(sinst9);
                fromdate9.setText(sfromdate9);
                todate9.setText(stodate9);

            }
        } else if (d == 5) {
            sname10 = name10.getText().toString();
            sinst10 = inst10.getText().toString();
            sfromdate10 = fromdate10.getText().toString();
            stodate10 = todate10.getText().toString();
            sname9 = name9.getText().toString();
            sinst9 = inst9.getText().toString();
            sfromdate9 = fromdate9.getText().toString();
            stodate9 = todate9.getText().toString();
            sname8 = name8.getText().toString();
            sinst8 = inst8.getText().toString();
            sfromdate8 = fromdate8.getText().toString();
            stodate8 = todate8.getText().toString();
            sname7 = name7.getText().toString();
            sinst7 = inst7.getText().toString();
            sfromdate7 = fromdate7.getText().toString();
            stodate7 = todate7.getText().toString();
            sname6 = name6.getText().toString();
            sinst6 = inst6.getText().toString();
            sfromdate6 = fromdate6.getText().toString();
            stodate6 = todate6.getText().toString();

            if (sname6.length() > 2) {
                sname5 = sname6;
                sinst5 = sinst6;
                sfromdate5 = sfromdate6;
                stodate5 = stodate6;

                name6.setText("");
                inst6.setText("");
                fromdate6.setText("");
                todate6.setText("");

                name5.setText(sname5);
                inst5.setText(sinst5);
                fromdate5.setText(sfromdate5);
                todate5.setText(stodate5);

            }
            if (sname7.length() > 2) {
                sname6 = sname7;
                sinst6 = sinst7;
                sfromdate6 = sfromdate7;
                stodate6 = stodate7;

                name7.setText("");
                inst7.setText("");
                fromdate7.setText("");
                todate7.setText("");

                name6.setText(sname6);
                inst6.setText(sinst6);
                fromdate6.setText(sfromdate6);
                todate6.setText(stodate6);

            }
            if (sname8.length() > 2) {
                sname7 = sname8;
                sinst7 = sinst8;
                sfromdate7 = sfromdate8;
                stodate7 = stodate8;

                name8.setText("");
                inst8.setText("");
                fromdate8.setText("");
                todate8.setText("");

                name7.setText(sname7);
                inst7.setText(sinst7);
                fromdate7.setText(sfromdate7);
                todate7.setText(stodate7);

            }
            if (sname9.length() > 2) {
                sname8 = sname9;
                sinst8 = sinst9;
                sfromdate8 = sfromdate9;
                stodate8 = stodate9;

                name9.setText("");
                inst9.setText("");
                fromdate9.setText("");
                todate9.setText("");

                name8.setText(sname8);
                inst8.setText(sinst8);
                fromdate8.setText(sfromdate8);
                todate8.setText(stodate8);

            }
            if (sname10.length() > 2) {
                sname9 = sname10;
                sinst9 = sinst10;
                sfromdate9 = sfromdate10;
                stodate9 = stodate10;

                name10.setText("");
                inst10.setText("");
                fromdate10.setText("");
                todate10.setText("");

                name9.setText(sname9);
                inst9.setText(sinst9);
                fromdate9.setText(sfromdate9);
                todate9.setText(stodate9);

            }
        } else if (d == 4) {
            sname10 = name10.getText().toString();
            sinst10 = inst10.getText().toString();
            sfromdate10 = fromdate10.getText().toString();
            stodate10 = todate10.getText().toString();
            sname9 = name9.getText().toString();
            sinst9 = inst9.getText().toString();
            sfromdate9 = fromdate9.getText().toString();
            stodate9 = todate9.getText().toString();
            sname8 = name8.getText().toString();
            sinst8 = inst8.getText().toString();
            sfromdate8 = fromdate8.getText().toString();
            stodate8 = todate8.getText().toString();
            sname7 = name7.getText().toString();
            sinst7 = inst7.getText().toString();
            sfromdate7 = fromdate7.getText().toString();
            stodate7 = todate7.getText().toString();
            sname6 = name6.getText().toString();
            sinst6 = inst6.getText().toString();
            sfromdate6 = fromdate6.getText().toString();
            stodate6 = todate6.getText().toString();
            sname5 = name5.getText().toString();
            sinst5 = inst5.getText().toString();
            sfromdate5 = fromdate5.getText().toString();
            stodate5 = todate5.getText().toString();

            if (sname5.length() > 2) {
                sname4 = sname5;
                sinst4 = sinst5;
                sfromdate4 = sfromdate5;
                stodate4 = stodate5;

                name5.setText("");
                inst5.setText("");
                fromdate5.setText("");
                todate5.setText("");

                name4.setText(sname4);
                inst4.setText(sinst4);
                fromdate4.setText(sfromdate4);
                todate4.setText(stodate4);

            }

            if (sname6.length() > 2) {
                sname5 = sname6;
                sinst5 = sinst6;
                sfromdate5 = sfromdate6;
                stodate5 = stodate6;

                name6.setText("");
                inst6.setText("");
                fromdate6.setText("");
                todate6.setText("");

                name5.setText(sname5);
                inst5.setText(sinst5);
                fromdate5.setText(sfromdate5);
                todate5.setText(stodate5);

            }
            if (sname7.length() > 2) {
                sname6 = sname7;
                sinst6 = sinst7;
                sfromdate6 = sfromdate7;
                stodate6 = stodate7;

                name7.setText("");
                inst7.setText("");
                fromdate7.setText("");
                todate7.setText("");

                name6.setText(sname6);
                inst6.setText(sinst6);
                fromdate6.setText(sfromdate6);
                todate6.setText(stodate6);

            }
            if (sname8.length() > 2) {
                sname7 = sname8;
                sinst7 = sinst8;
                sfromdate7 = sfromdate8;
                stodate7 = stodate8;

                name8.setText("");
                inst8.setText("");
                fromdate8.setText("");
                todate8.setText("");

                name7.setText(sname7);
                inst7.setText(sinst7);
                fromdate7.setText(sfromdate7);
                todate7.setText(stodate7);

            }
            if (sname9.length() > 2) {
                sname8 = sname9;
                sinst8 = sinst9;
                sfromdate8 = sfromdate9;
                stodate8 = stodate9;

                name9.setText("");
                inst9.setText("");
                fromdate9.setText("");
                todate9.setText("");

                name8.setText(sname8);
                inst8.setText(sinst8);
                fromdate8.setText(sfromdate8);
                todate8.setText(stodate8);

            }
            if (sname10.length() > 2) {
                sname9 = sname10;
                sinst9 = sinst10;
                sfromdate9 = sfromdate10;
                stodate9 = stodate10;

                name10.setText("");
                inst10.setText("");
                fromdate10.setText("");
                todate10.setText("");

                name9.setText(sname9);
                inst9.setText(sinst9);
                fromdate9.setText(sfromdate9);
                todate9.setText(stodate9);

            }
        } else if (d == 3) {
            sname10 = name10.getText().toString();
            sinst10 = inst10.getText().toString();
            sfromdate10 = fromdate10.getText().toString();
            stodate10 = todate10.getText().toString();
            sname9 = name9.getText().toString();
            sinst9 = inst9.getText().toString();
            sfromdate9 = fromdate9.getText().toString();
            stodate9 = todate9.getText().toString();
            sname8 = name8.getText().toString();
            sinst8 = inst8.getText().toString();
            sfromdate8 = fromdate8.getText().toString();
            stodate8 = todate8.getText().toString();
            sname7 = name7.getText().toString();
            sinst7 = inst7.getText().toString();
            sfromdate7 = fromdate7.getText().toString();
            stodate7 = todate7.getText().toString();
            sname6 = name6.getText().toString();
            sinst6 = inst6.getText().toString();
            sfromdate6 = fromdate6.getText().toString();
            stodate6 = todate6.getText().toString();
            sname5 = name5.getText().toString();
            sinst5 = inst5.getText().toString();
            sfromdate5 = fromdate5.getText().toString();
            stodate5 = todate5.getText().toString();
            sname4 = name4.getText().toString();
            sinst4 = inst4.getText().toString();
            sfromdate4 = fromdate4.getText().toString();
            stodate4 = todate4.getText().toString();

            if (sname4.length() > 2) {
                sname3 = sname4;
                sinst3 = sinst4;
                sfromdate3 = sfromdate4;
                stodate3 = stodate4;

                name4.setText("");
                inst4.setText("");
                fromdate4.setText("");
                todate4.setText("");

                name3.setText(sname3);
                inst3.setText(sinst3);
                fromdate3.setText(sfromdate3);
                todate3.setText(stodate3);

            }
            if (sname5.length() > 2) {
                sname4 = sname5;
                sinst4 = sinst5;
                sfromdate4 = sfromdate5;
                stodate4 = stodate5;

                name5.setText("");
                inst5.setText("");
                fromdate5.setText("");
                todate5.setText("");

                name4.setText(sname4);
                inst4.setText(sinst4);
                fromdate4.setText(sfromdate4);
                todate4.setText(stodate4);

            }

            if (sname6.length() > 2) {
                sname5 = sname6;
                sinst5 = sinst6;
                sfromdate5 = sfromdate6;
                stodate5 = stodate6;

                name6.setText("");
                inst6.setText("");
                fromdate6.setText("");
                todate6.setText("");

                name5.setText(sname5);
                inst5.setText(sinst5);
                fromdate5.setText(sfromdate5);
                todate5.setText(stodate5);

            }
            if (sname7.length() > 2) {
                sname6 = sname7;
                sinst6 = sinst7;
                sfromdate6 = sfromdate7;
                stodate6 = stodate7;

                name7.setText("");
                inst7.setText("");
                fromdate7.setText("");
                todate7.setText("");

                name6.setText(sname6);
                inst6.setText(sinst6);
                fromdate6.setText(sfromdate6);
                todate6.setText(stodate6);

            }
            if (sname8.length() > 2) {
                sname7 = sname8;
                sinst7 = sinst8;
                sfromdate7 = sfromdate8;
                stodate7 = stodate8;

                name8.setText("");
                inst8.setText("");
                fromdate8.setText("");
                todate8.setText("");

                name7.setText(sname7);
                inst7.setText(sinst7);
                fromdate7.setText(sfromdate7);
                todate7.setText(stodate7);

            }
            if (sname9.length() > 2) {
                sname8 = sname9;
                sinst8 = sinst9;
                sfromdate8 = sfromdate9;
                stodate8 = stodate9;

                name9.setText("");
                inst9.setText("");
                fromdate9.setText("");
                todate9.setText("");

                name8.setText(sname8);
                inst8.setText(sinst8);
                fromdate8.setText(sfromdate8);
                todate8.setText(stodate8);

            }
            if (sname10.length() > 2) {
                sname9 = sname10;
                sinst9 = sinst10;
                sfromdate9 = sfromdate10;
                stodate9 = stodate10;

                name10.setText("");
                inst10.setText("");
                fromdate10.setText("");
                todate10.setText("");

                name9.setText(sname9);
                inst9.setText(sinst9);
                fromdate9.setText(sfromdate9);
                todate9.setText(stodate9);

            }
        } else if (d == 2) {
            sname10 = name10.getText().toString();
            sinst10 = inst10.getText().toString();
            sfromdate10 = fromdate10.getText().toString();
            stodate10 = todate10.getText().toString();
            sname9 = name9.getText().toString();
            sinst9 = inst9.getText().toString();
            sfromdate9 = fromdate9.getText().toString();
            stodate9 = todate9.getText().toString();
            sname8 = name8.getText().toString();
            sinst8 = inst8.getText().toString();
            sfromdate8 = fromdate8.getText().toString();
            stodate8 = todate8.getText().toString();
            sname7 = name7.getText().toString();
            sinst7 = inst7.getText().toString();
            sfromdate7 = fromdate7.getText().toString();
            stodate7 = todate7.getText().toString();
            sname6 = name6.getText().toString();
            sinst6 = inst6.getText().toString();
            sfromdate6 = fromdate6.getText().toString();
            stodate6 = todate6.getText().toString();
            sname5 = name5.getText().toString();
            sinst5 = inst5.getText().toString();
            sfromdate5 = fromdate5.getText().toString();
            stodate5 = todate5.getText().toString();
            sname4 = name4.getText().toString();
            sinst4 = inst4.getText().toString();
            sfromdate4 = fromdate4.getText().toString();
            stodate4 = todate4.getText().toString();
            sname3 = name3.getText().toString();
            sinst3 = inst3.getText().toString();
            sfromdate3 = fromdate3.getText().toString();
            stodate3 = todate3.getText().toString();

            if (sname3.length() > 2) {
                sname2 = sname3;
                sinst2 = sinst3;
                sfromdate2 = sfromdate3;
                stodate2 = stodate3;

                name3.setText("");
                inst3.setText("");
                fromdate3.setText("");
                todate3.setText("");

                name2.setText(sname2);
                inst2.setText(sinst2);
                fromdate2.setText(sfromdate2);
                todate2.setText(stodate2);

            }
            if (sname4.length() > 2) {
                sname3 = sname4;
                sinst3 = sinst4;
                sfromdate3 = sfromdate4;
                stodate3 = stodate4;

                name4.setText("");
                inst4.setText("");
                fromdate4.setText("");
                todate4.setText("");

                name3.setText(sname3);
                inst3.setText(sinst3);
                fromdate3.setText(sfromdate3);
                todate3.setText(stodate3);

            }
            if (sname5.length() > 2) {
                sname4 = sname5;
                sinst4 = sinst5;
                sfromdate4 = sfromdate5;
                stodate4 = stodate5;

                name5.setText("");
                inst5.setText("");
                fromdate5.setText("");
                todate5.setText("");

                name4.setText(sname4);
                inst4.setText(sinst4);
                fromdate4.setText(sfromdate4);
                todate4.setText(stodate4);

            }

            if (sname6.length() > 2) {
                sname5 = sname6;
                sinst5 = sinst6;
                sfromdate5 = sfromdate6;
                stodate5 = stodate6;

                name6.setText("");
                inst6.setText("");
                fromdate6.setText("");
                todate6.setText("");

                name5.setText(sname5);
                inst5.setText(sinst5);
                fromdate5.setText(sfromdate5);
                todate5.setText(stodate5);

            }
            if (sname7.length() > 2) {
                sname6 = sname7;
                sinst6 = sinst7;
                sfromdate6 = sfromdate7;
                stodate6 = stodate7;

                name7.setText("");
                inst7.setText("");
                fromdate7.setText("");
                todate7.setText("");

                name6.setText(sname6);
                inst6.setText(sinst6);
                fromdate6.setText(sfromdate6);
                todate6.setText(stodate6);

            }
            if (sname8.length() > 2) {
                sname7 = sname8;
                sinst7 = sinst8;
                sfromdate7 = sfromdate8;
                stodate7 = stodate8;

                name8.setText("");
                inst8.setText("");
                fromdate8.setText("");
                todate8.setText("");

                name7.setText(sname7);
                inst7.setText(sinst7);
                fromdate7.setText(sfromdate7);
                todate7.setText(stodate7);

            }
            if (sname9.length() > 2) {
                sname8 = sname9;
                sinst8 = sinst9;
                sfromdate8 = sfromdate9;
                stodate8 = stodate9;

                name9.setText("");
                inst9.setText("");
                fromdate9.setText("");
                todate9.setText("");

                name8.setText(sname8);
                inst8.setText(sinst8);
                fromdate8.setText(sfromdate8);
                todate8.setText(stodate8);

            }
            if (sname10.length() > 2) {
                sname9 = sname10;
                sinst9 = sinst10;
                sfromdate9 = sfromdate10;
                stodate9 = stodate10;

                name10.setText("");
                inst10.setText("");
                fromdate10.setText("");
                todate10.setText("");

                name9.setText(sname9);
                inst9.setText(sinst9);
                fromdate9.setText(sfromdate9);
                todate9.setText(stodate9);

            }
        } else if (d == 1) {
            sname10 = name10.getText().toString();
            sinst10 = inst10.getText().toString();
            sfromdate10 = fromdate10.getText().toString();
            stodate10 = todate10.getText().toString();
            sname9 = name9.getText().toString();
            sinst9 = inst9.getText().toString();
            sfromdate9 = fromdate9.getText().toString();
            stodate9 = todate9.getText().toString();
            sname8 = name8.getText().toString();
            sinst8 = inst8.getText().toString();
            sfromdate8 = fromdate8.getText().toString();
            stodate8 = todate8.getText().toString();
            sname7 = name7.getText().toString();
            sinst7 = inst7.getText().toString();
            sfromdate7 = fromdate7.getText().toString();
            stodate7 = todate7.getText().toString();
            sname6 = name6.getText().toString();
            sinst6 = inst6.getText().toString();
            sfromdate6 = fromdate6.getText().toString();
            stodate6 = todate6.getText().toString();
            sname5 = name5.getText().toString();
            sinst5 = inst5.getText().toString();
            sfromdate5 = fromdate5.getText().toString();
            stodate5 = todate5.getText().toString();
            sname4 = name4.getText().toString();
            sinst4 = inst4.getText().toString();
            sfromdate4 = fromdate4.getText().toString();
            stodate4 = todate4.getText().toString();
            sname3 = name3.getText().toString();
            sinst3 = inst3.getText().toString();
            sfromdate3 = fromdate3.getText().toString();
            stodate3 = todate3.getText().toString();
            sname2 = name2.getText().toString();
            sinst2 = inst2.getText().toString();
            sfromdate2 = fromdate2.getText().toString();
            stodate2 = todate2.getText().toString();

            if (sname2.length() > 1) {
                sname1 = sname2;
                sinst1 = sinst2;
                sfromdate1 = sfromdate2;
                stodate1 = stodate2;

                name2.setText("");
                inst2.setText("");
                fromdate2.setText("");
                todate2.setText("");

                name1.setText(sname1);
                inst1.setText(sinst1);
                fromdate1.setText(sfromdate1);
                todate1.setText(stodate1);

            }
            if (sname3.length() > 2) {
                sname2 = sname3;
                sinst2 = sinst3;
                sfromdate2 = sfromdate3;
                stodate2 = stodate3;

                name3.setText("");
                inst3.setText("");
                fromdate3.setText("");
                todate3.setText("");

                name2.setText(sname2);
                inst2.setText(sinst2);
                fromdate2.setText(sfromdate2);
                todate2.setText(stodate2);

            }
            if (sname4.length() > 2) {
                sname3 = sname4;
                sinst3 = sinst4;
                sfromdate3 = sfromdate4;
                stodate3 = stodate4;

                name4.setText("");
                inst4.setText("");
                fromdate4.setText("");
                todate4.setText("");

                name3.setText(sname3);
                inst3.setText(sinst3);
                fromdate3.setText(sfromdate3);
                todate3.setText(stodate3);

            }
            if (sname5.length() > 2) {
                sname4 = sname5;
                sinst4 = sinst5;
                sfromdate4 = sfromdate5;
                stodate4 = stodate5;

                name5.setText("");
                inst5.setText("");
                fromdate5.setText("");
                todate5.setText("");

                name4.setText(sname4);
                inst4.setText(sinst4);
                fromdate4.setText(sfromdate4);
                todate4.setText(stodate4);

            }

            if (sname6.length() > 2) {
                sname5 = sname6;
                sinst5 = sinst6;
                sfromdate5 = sfromdate6;
                stodate5 = stodate6;

                name6.setText("");
                inst6.setText("");
                fromdate6.setText("");
                todate6.setText("");

                name5.setText(sname5);
                inst5.setText(sinst5);
                fromdate5.setText(sfromdate5);
                todate5.setText(stodate5);

            }
            if (sname7.length() > 2) {
                sname6 = sname7;
                sinst6 = sinst7;
                sfromdate6 = sfromdate7;
                stodate6 = stodate7;

                name7.setText("");
                inst7.setText("");
                fromdate7.setText("");
                todate7.setText("");

                name6.setText(sname6);
                inst6.setText(sinst6);
                fromdate6.setText(sfromdate6);
                todate6.setText(stodate6);

            }
            if (sname8.length() > 2) {
                sname7 = sname8;
                sinst7 = sinst8;
                sfromdate7 = sfromdate8;
                stodate7 = stodate8;

                name8.setText("");
                inst8.setText("");
                fromdate8.setText("");
                todate8.setText("");

                name7.setText(sname7);
                inst7.setText(sinst7);
                fromdate7.setText(sfromdate7);
                todate7.setText(stodate7);

            }
            if (sname9.length() > 2) {
                sname8 = sname9;
                sinst8 = sinst9;
                sfromdate8 = sfromdate9;
                stodate8 = stodate9;

                name9.setText("");
                inst9.setText("");
                fromdate9.setText("");
                todate9.setText("");

                name8.setText(sname8);
                inst8.setText(sinst8);
                fromdate8.setText(sfromdate8);
                todate8.setText(stodate8);

            }
            if (sname10.length() > 2) {
                sname9 = sname10;
                sinst9 = sinst10;
                sfromdate9 = sfromdate10;
                stodate9 = stodate10;

                name10.setText("");
                inst10.setText("");
                fromdate10.setText("");
                todate10.setText("");

                name9.setText(sname9);
                inst9.setText(sinst9);
                fromdate9.setText(sfromdate9);
                todate9.setText(stodate9);

            }
        }

    }
//

    // my date validation


    void showDateDialog(final EditText id, boolean isFromDateSelected, final int fromYear, final String fromMonth, final String todate, final TextInputLayout textInputLayout) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfileCourses.this);
        LayoutInflater inflater = MyProfileCourses.this.getLayoutInflater();
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

        monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfileCourses.this));
        monthView.setWheelData(monthList);
        yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfileCourses.this));
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

                setMonthYear(id, selectedMonth, selectedYear, isvalid, textInputLayout);
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
        int w = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());
        int h = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 215, getResources().getDisplayMetrics());
        alertDialog.getWindow().setLayout(w, h);


    }

    void setMonthYear(EditText id, String selectedMonth, String selectedYear, boolean isValid, TextInputLayout textInputLayout) {
        id.setError(null);
        Log.d("TAG", "onClick: id after " + textInputLayout);
        if (isValid == true) {
            id.setText(selectedMonth + ", " + selectedYear);
        } else {
//            textInputLayout.setError("Choose valid date 123");
            nameinput1.setError("errrrrrororor");
            todateinput1.setError("errrrrrororor");
            Toast.makeText(this, "Invalid date " + textInputLayout, Toast.LENGTH_LONG).show();
            id.setText("");
        }
    }


    //

    void validateandSave() {
        instinput1.setError(null);
        fromdateinput1.setError(null);
        todateinput1.setError(null);
        nameinput2.setError(null);
        instinput2.setError(null);
        fromdateinput2.setError(null);
        todateinput2.setError(null);
        nameinput3.setError(null);
        instinput3.setError(null);
        fromdateinput3.setError(null);
        todateinput3.setError(null);
        nameinput4.setError(null);
        instinput4.setError(null);
        fromdateinput4.setError(null);
        todateinput4.setError(null);
        nameinput5.setError(null);
        instinput5.setError(null);
        fromdateinput5.setError(null);
        todateinput5.setError(null);
        nameinput6.setError(null);
        instinput6.setError(null);
        fromdateinput6.setError(null);
        todateinput6.setError(null);
        nameinput7.setError(null);
        instinput7.setError(null);
        fromdateinput7.setError(null);
        todateinput7.setError(null);
        nameinput8.setError(null);
        instinput8.setError(null);
        fromdateinput8.setError(null);
        todateinput8.setError(null);
        nameinput9.setError(null);
        instinput9.setError(null);
        fromdateinput9.setError(null);
        todateinput9.setError(null);
        nameinput10.setError(null);
        instinput10.setError(null);
        fromdateinput10.setError(null);
        todateinput10.setError(null);

        sname1 = name1.getText().toString();
        sinst1 = inst1.getText().toString();
        sfromdate1 = fromdate1.getText().toString();
        stodate1 = todate1.getText().toString();
        sname2 = name2.getText().toString();
        sinst2 = inst2.getText().toString();
        sfromdate2 = fromdate2.getText().toString();
        stodate2 = todate2.getText().toString();
        sname3 = name3.getText().toString();
        sinst3 = inst3.getText().toString();
        sfromdate3 = fromdate3.getText().toString();
        stodate3 = todate3.getText().toString();
        sname4 = name4.getText().toString();
        sinst4 = inst4.getText().toString();
        sfromdate4 = fromdate4.getText().toString();
        stodate4 = todate4.getText().toString();
        sname5 = name5.getText().toString();
        sinst5 = inst5.getText().toString();
        sfromdate5 = fromdate5.getText().toString();
        stodate5 = todate5.getText().toString();
        sname6 = name6.getText().toString();
        sinst6 = inst6.getText().toString();
        sfromdate6 = fromdate6.getText().toString();
        stodate6 = todate6.getText().toString();
        sname7 = name7.getText().toString();
        sinst7 = inst7.getText().toString();
        sfromdate7 = fromdate7.getText().toString();
        stodate7 = todate7.getText().toString();
        sname8 = name8.getText().toString();
        sinst8 = inst8.getText().toString();
        sfromdate8 = fromdate8.getText().toString();
        stodate8 = todate8.getText().toString();
        sname9 = name9.getText().toString();
        sinst9 = inst9.getText().toString();
        sfromdate9 = fromdate9.getText().toString();
        stodate9 = todate9.getText().toString();
        sname10 = name10.getText().toString();
        sinst10 = inst10.getText().toString();
        sfromdate10 = fromdate10.getText().toString();
        stodate10 = todate10.getText().toString();

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag = 0;

        if (coursecount == 0) {
            if (sname1.length() < 3) {
                errorflag = 1;
                nameinput1.setError("Invalid Course Name");
            } else {
                errorflag = 0;
                if (sinst1.length() < 3) {
                    errorflag = 1;
                    instinput1.setError("Invalid Institute Name");
                } else {
                    errorflag = 0;
                    if (sfromdate1.length() < 3) {
                        errorflag = 1;
                        fromdateinput1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (stodate1.length() < 3) {
                            errorflag = 1;
                            todateinput1.setError("Invalid Date");
                        }
                    }
                }
            }
        } else if (coursecount == 1) {
            if (sname1.length() < 3) {
                errorflag = 1;
                nameinput1.setError("Invalid Course Name");
            } else {
                errorflag = 0;
                if (sinst1.length() < 3) {
                    errorflag = 1;
                    instinput1.setError("Invalid Institute Name");
                } else {
                    errorflag = 0;
                    if (sfromdate1.length() < 3) {
                        errorflag = 1;
                        fromdateinput1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (stodate1.length() < 3) {
                            errorflag = 1;
                            todateinput1.setError("Invalid Date");
                        } else {
                            errorflag = 0;
                            if (sname2.length() < 3) {
                                errorflag = 1;
                                nameinput2.setError("Invalid Course Name");
                            } else {
                                errorflag = 0;
                                if (sinst2.length() < 3) {
                                    errorflag = 1;
                                    instinput2.setError("Invalid Institute Name");
                                } else {
                                    errorflag = 0;
                                    if (sfromdate2.length() < 3) {
                                        errorflag = 1;
                                        fromdateinput2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stodate2.length() < 3) {
                                            errorflag = 1;
                                            todateinput2.setError("Invalid Date");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (coursecount == 2) {
            if (sname1.length() < 3) {
                errorflag = 1;
                nameinput1.setError("Invalid Course Name");
            } else {
                errorflag = 0;
                if (sinst1.length() < 3) {
                    errorflag = 1;
                    instinput1.setError("Invalid Institute Name");
                } else {
                    errorflag = 0;
                    if (sfromdate1.length() < 3) {
                        errorflag = 1;
                        fromdateinput1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (stodate1.length() < 3) {
                            errorflag = 1;
                            todateinput1.setError("Invalid Date");
                        } else {
                            errorflag = 0;
                            if (sname2.length() < 3) {
                                errorflag = 1;
                                nameinput2.setError("Invalid Course Name");
                            } else {
                                errorflag = 0;
                                if (sinst2.length() < 3) {
                                    errorflag = 1;
                                    instinput2.setError("Invalid Institute Name");
                                } else {
                                    errorflag = 0;
                                    if (sfromdate2.length() < 3) {
                                        errorflag = 1;
                                        fromdateinput2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stodate2.length() < 3) {
                                            errorflag = 1;
                                            todateinput2.setError("Invalid Date");
                                        } else {
                                            errorflag = 0;
                                            if (sname3.length() < 3) {
                                                errorflag = 1;
                                                nameinput3.setError("Invalid Course Name");
                                            } else {
                                                errorflag = 0;
                                                if (sinst3.length() < 3) {
                                                    errorflag = 1;
                                                    instinput3.setError("Invalid Institute Name");
                                                } else {
                                                    errorflag = 0;
                                                    if (sfromdate3.length() < 3) {
                                                        errorflag = 1;
                                                        fromdateinput3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (stodate3.length() < 3) {
                                                            errorflag = 1;
                                                            todateinput3.setError("Invalid Date");
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
            }
        } else if (coursecount == 3) {
            if (sname1.length() < 3) {
                errorflag = 1;
                nameinput1.setError("Invalid Course Name");
            } else {
                errorflag = 0;
                if (sinst1.length() < 3) {
                    errorflag = 1;
                    instinput1.setError("Invalid Institute Name");
                } else {
                    errorflag = 0;
                    if (sfromdate1.length() < 3) {
                        errorflag = 1;
                        fromdateinput1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (stodate1.length() < 3) {
                            errorflag = 1;
                            todateinput1.setError("Invalid Date");
                        } else {
                            errorflag = 0;
                            if (sname2.length() < 3) {
                                errorflag = 1;
                                nameinput2.setError("Invalid Course Name");
                            } else {
                                errorflag = 0;
                                if (sinst2.length() < 3) {
                                    errorflag = 1;
                                    instinput2.setError("Invalid Institute Name");
                                } else {
                                    errorflag = 0;
                                    if (sfromdate2.length() < 3) {
                                        errorflag = 1;
                                        fromdateinput2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stodate2.length() < 3) {
                                            errorflag = 1;
                                            todateinput2.setError("Invalid Date");
                                        } else {
                                            errorflag = 0;
                                            if (sname3.length() < 3) {
                                                errorflag = 1;
                                                nameinput3.setError("Invalid Course Name");
                                            } else {
                                                errorflag = 0;
                                                if (sinst3.length() < 3) {
                                                    errorflag = 1;
                                                    instinput3.setError("Invalid Institute Name");
                                                } else {
                                                    errorflag = 0;
                                                    if (sfromdate3.length() < 3) {
                                                        errorflag = 1;
                                                        fromdateinput3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (stodate3.length() < 3) {
                                                            errorflag = 1;
                                                            todateinput3.setError("Invalid Date");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sname4.length() < 3) {
                                                                errorflag = 1;
                                                                nameinput4.setError("Invalid Course Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sinst4.length() < 3) {
                                                                    errorflag = 1;
                                                                    instinput4.setError("Invalid Institute Name");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sfromdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        fromdateinput4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (stodate4.length() < 3) {
                                                                            errorflag = 1;
                                                                            todateinput4.setError("Invalid Date");
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
                            }
                        }
                    }
                }
            }
        } else if (coursecount == 4) {
            if (sname1.length() < 3) {
                errorflag = 1;
                nameinput1.setError("Invalid Course Name");
            } else {
                errorflag = 0;
                if (sinst1.length() < 3) {
                    errorflag = 1;
                    instinput1.setError("Invalid Institute Name");
                } else {
                    errorflag = 0;
                    if (sfromdate1.length() < 3) {
                        errorflag = 1;
                        fromdateinput1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (stodate1.length() < 3) {
                            errorflag = 1;
                            todateinput1.setError("Invalid Date");
                        } else {
                            errorflag = 0;
                            if (sname2.length() < 3) {
                                errorflag = 1;
                                nameinput2.setError("Invalid Course Name");
                            } else {
                                errorflag = 0;
                                if (sinst2.length() < 3) {
                                    errorflag = 1;
                                    instinput2.setError("Invalid Institute Name");
                                } else {
                                    errorflag = 0;
                                    if (sfromdate2.length() < 3) {
                                        errorflag = 1;
                                        fromdateinput2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stodate2.length() < 3) {
                                            errorflag = 1;
                                            todateinput2.setError("Invalid Date");
                                        } else {
                                            errorflag = 0;
                                            if (sname3.length() < 3) {
                                                errorflag = 1;
                                                nameinput3.setError("Invalid Course Name");
                                            } else {
                                                errorflag = 0;
                                                if (sinst3.length() < 3) {
                                                    errorflag = 1;
                                                    instinput3.setError("Invalid Institute Name");
                                                } else {
                                                    errorflag = 0;
                                                    if (sfromdate3.length() < 3) {
                                                        errorflag = 1;
                                                        fromdateinput3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (stodate3.length() < 3) {
                                                            errorflag = 1;
                                                            todateinput3.setError("Invalid Date");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sname4.length() < 3) {
                                                                errorflag = 1;
                                                                nameinput4.setError("Invalid Course Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sinst4.length() < 3) {
                                                                    errorflag = 1;
                                                                    instinput4.setError("Invalid Institute Name");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sfromdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        fromdateinput4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (stodate4.length() < 3) {
                                                                            errorflag = 1;
                                                                            todateinput4.setError("Invalid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (sname5.length() < 3) {
                                                                                errorflag = 1;
                                                                                nameinput5.setError("Invalid Course Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    instinput5.setError("Invalid Institute Name");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (stodate5.length() < 3) {
                                                                                            errorflag = 1;
                                                                                            todateinput5.setError("Invalid Date");
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
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (coursecount == 5) {
            if (sname1.length() < 3) {
                errorflag = 1;
                nameinput1.setError("Invalid Course Name");
            } else {
                errorflag = 0;
                if (sinst1.length() < 3) {
                    errorflag = 1;
                    instinput1.setError("Invalid Institute Name");
                } else {
                    errorflag = 0;
                    if (sfromdate1.length() < 3) {
                        errorflag = 1;
                        fromdateinput1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (stodate1.length() < 3) {
                            errorflag = 1;
                            todateinput1.setError("Invalid Date");
                        } else {
                            errorflag = 0;
                            if (sname2.length() < 3) {
                                errorflag = 1;
                                nameinput2.setError("Invalid Course Name");
                            } else {
                                errorflag = 0;
                                if (sinst2.length() < 3) {
                                    errorflag = 1;
                                    instinput2.setError("Invalid Institute Name");
                                } else {
                                    errorflag = 0;
                                    if (sfromdate2.length() < 3) {
                                        errorflag = 1;
                                        fromdateinput2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stodate2.length() < 3) {
                                            errorflag = 1;
                                            todateinput2.setError("Invalid Date");
                                        } else {
                                            errorflag = 0;
                                            if (sname3.length() < 3) {
                                                errorflag = 1;
                                                nameinput3.setError("Invalid Course Name");
                                            } else {
                                                errorflag = 0;
                                                if (sinst3.length() < 3) {
                                                    errorflag = 1;
                                                    instinput3.setError("Invalid Institute Name");
                                                } else {
                                                    errorflag = 0;
                                                    if (sfromdate3.length() < 3) {
                                                        errorflag = 1;
                                                        fromdateinput3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (stodate3.length() < 3) {
                                                            errorflag = 1;
                                                            todateinput3.setError("Invalid Date");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sname4.length() < 3) {
                                                                errorflag = 1;
                                                                nameinput4.setError("Invalid Course Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sinst4.length() < 3) {
                                                                    errorflag = 1;
                                                                    instinput4.setError("Invalid Institute Name");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sfromdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        fromdateinput4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (stodate4.length() < 3) {
                                                                            errorflag = 1;
                                                                            todateinput4.setError("Invalid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (sname5.length() < 3) {
                                                                                errorflag = 1;
                                                                                nameinput5.setError("Invalid Course Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    instinput5.setError("Invalid Institute Name");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (stodate5.length() < 3) {
                                                                                            errorflag = 1;
                                                                                            todateinput5.setError("Invalid Date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (sname6.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                nameinput6.setError("Invalid Course Name");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sinst6.length() < 3) {
                                                                                                    errorflag = 1;
                                                                                                    instinput6.setError("Invalid Institute Name");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sfromdate6.length() < 3) {
                                                                                                        errorflag = 1;
                                                                                                        fromdateinput6.setError("Invalid Date");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (stodate6.length() < 3) {
                                                                                                            errorflag = 1;
                                                                                                            todateinput6.setError("Invalid Date");
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
                }
            }
        } else if (coursecount == 6) {
            if (sname1.length() < 3) {
                errorflag = 1;
                nameinput1.setError("Invalid Course Name");
            } else {
                errorflag = 0;
                if (sinst1.length() < 3) {
                    errorflag = 1;
                    instinput1.setError("Invalid Institute Name");
                } else {
                    errorflag = 0;
                    if (sfromdate1.length() < 3) {
                        errorflag = 1;
                        fromdateinput1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (stodate1.length() < 3) {
                            errorflag = 1;
                            todateinput1.setError("Invalid Date");
                        } else {
                            errorflag = 0;
                            if (sname2.length() < 3) {
                                errorflag = 1;
                                nameinput2.setError("Invalid Course Name");
                            } else {
                                errorflag = 0;
                                if (sinst2.length() < 3) {
                                    errorflag = 1;
                                    instinput2.setError("Invalid Institute Name");
                                } else {
                                    errorflag = 0;
                                    if (sfromdate2.length() < 3) {
                                        errorflag = 1;
                                        fromdateinput2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stodate2.length() < 3) {
                                            errorflag = 1;
                                            todateinput2.setError("Invalid Date");
                                        } else {
                                            errorflag = 0;
                                            if (sname3.length() < 3) {
                                                errorflag = 1;
                                                nameinput3.setError("Invalid Course Name");
                                            } else {
                                                errorflag = 0;
                                                if (sinst3.length() < 3) {
                                                    errorflag = 1;
                                                    instinput3.setError("Invalid Institute Name");
                                                } else {
                                                    errorflag = 0;
                                                    if (sfromdate3.length() < 3) {
                                                        errorflag = 1;
                                                        fromdateinput3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (stodate3.length() < 3) {
                                                            errorflag = 1;
                                                            todateinput3.setError("Invalid Date");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sname4.length() < 3) {
                                                                errorflag = 1;
                                                                nameinput4.setError("Invalid Course Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sinst4.length() < 3) {
                                                                    errorflag = 1;
                                                                    instinput4.setError("Invalid Institute Name");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sfromdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        fromdateinput4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (stodate4.length() < 3) {
                                                                            errorflag = 1;
                                                                            todateinput4.setError("Invalid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (sname5.length() < 3) {
                                                                                errorflag = 1;
                                                                                nameinput5.setError("Invalid Course Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    instinput5.setError("Invalid Institute Name");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (stodate5.length() < 3) {
                                                                                            errorflag = 1;
                                                                                            todateinput5.setError("Invalid Date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (sname6.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                nameinput6.setError("Invalid Course Name");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sinst6.length() < 3) {
                                                                                                    errorflag = 1;
                                                                                                    instinput6.setError("Invalid Institute Name");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sfromdate6.length() < 3) {
                                                                                                        errorflag = 1;
                                                                                                        fromdateinput6.setError("Invalid Date");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (stodate6.length() < 3) {
                                                                                                            errorflag = 1;
                                                                                                            todateinput6.setError("Invalid Date");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (sname7.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                nameinput7.setError("Invalid Course Name");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sinst7.length() < 3) {
                                                                                                                    errorflag = 1;
                                                                                                                    instinput7.setError("Invalid Institute Name");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sfromdate7.length() < 3) {
                                                                                                                        errorflag = 1;
                                                                                                                        fromdateinput7.setError("Invalid Date");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (stodate7.length() < 3) {
                                                                                                                            errorflag = 1;
                                                                                                                            todateinput7.setError("Invalid Date");
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
                                }
                            }
                        }
                    }
                }
            }
        } else if (coursecount == 7) {
            if (sname1.length() < 3) {
                errorflag = 1;
                nameinput1.setError("Invalid Course Name");
            } else {
                errorflag = 0;
                if (sinst1.length() < 3) {
                    errorflag = 1;
                    instinput1.setError("Invalid Institute Name");
                } else {
                    errorflag = 0;
                    if (sfromdate1.length() < 3) {
                        errorflag = 1;
                        fromdateinput1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (stodate1.length() < 3) {
                            errorflag = 1;
                            todateinput1.setError("Invalid Date");
                        } else {
                            errorflag = 0;
                            if (sname2.length() < 3) {
                                errorflag = 1;
                                nameinput2.setError("Invalid Course Name");
                            } else {
                                errorflag = 0;
                                if (sinst2.length() < 3) {
                                    errorflag = 1;
                                    instinput2.setError("Invalid Institute Name");
                                } else {
                                    errorflag = 0;
                                    if (sfromdate2.length() < 3) {
                                        errorflag = 1;
                                        fromdateinput2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stodate2.length() < 3) {
                                            errorflag = 1;
                                            todateinput2.setError("Invalid Date");
                                        } else {
                                            errorflag = 0;
                                            if (sname3.length() < 3) {
                                                errorflag = 1;
                                                nameinput3.setError("Invalid Course Name");
                                            } else {
                                                errorflag = 0;
                                                if (sinst3.length() < 3) {
                                                    errorflag = 1;
                                                    instinput3.setError("Invalid Institute Name");
                                                } else {
                                                    errorflag = 0;
                                                    if (sfromdate3.length() < 3) {
                                                        errorflag = 1;
                                                        fromdateinput3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (stodate3.length() < 3) {
                                                            errorflag = 1;
                                                            todateinput3.setError("Invalid Date");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sname4.length() < 3) {
                                                                errorflag = 1;
                                                                nameinput4.setError("Invalid Course Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sinst4.length() < 3) {
                                                                    errorflag = 1;
                                                                    instinput4.setError("Invalid Institute Name");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sfromdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        fromdateinput4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (stodate4.length() < 3) {
                                                                            errorflag = 1;
                                                                            todateinput4.setError("Invalid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (sname5.length() < 3) {
                                                                                errorflag = 1;
                                                                                nameinput5.setError("Invalid Course Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    instinput5.setError("Invalid Institute Name");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (stodate5.length() < 3) {
                                                                                            errorflag = 1;
                                                                                            todateinput5.setError("Invalid Date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (sname6.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                nameinput6.setError("Invalid Course Name");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sinst6.length() < 3) {
                                                                                                    errorflag = 1;
                                                                                                    instinput6.setError("Invalid Institute Name");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sfromdate6.length() < 3) {
                                                                                                        errorflag = 1;
                                                                                                        fromdateinput6.setError("Invalid Date");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (stodate6.length() < 3) {
                                                                                                            errorflag = 1;
                                                                                                            todateinput6.setError("Invalid Date");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (sname7.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                nameinput7.setError("Invalid Course Name");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sinst7.length() < 3) {
                                                                                                                    errorflag = 1;
                                                                                                                    instinput7.setError("Invalid Institute Name");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sfromdate7.length() < 3) {
                                                                                                                        errorflag = 1;
                                                                                                                        fromdateinput7.setError("Invalid Date");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (stodate7.length() < 3) {
                                                                                                                            errorflag = 1;
                                                                                                                            todateinput7.setError("Invalid Date");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (sname8.length() < 3) {
                                                                                                                                errorflag = 1;
                                                                                                                                nameinput8.setError("Invalid Course Name");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sinst8.length() < 3) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    instinput8.setError("Invalid Institute Name");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (sfromdate8.length() < 3) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        fromdateinput8.setError("Invalid Date");
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (stodate8.length() < 3) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            todateinput8.setError("Invalid Date");
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
        } else if (coursecount == 8) {
            if (sname1.length() < 3) {
                errorflag = 1;
                nameinput1.setError("Invalid Course Name");
            } else {
                errorflag = 0;
                if (sinst1.length() < 3) {
                    errorflag = 1;
                    instinput1.setError("Invalid Institute Name");
                } else {
                    errorflag = 0;
                    if (sfromdate1.length() < 3) {
                        errorflag = 1;
                        fromdateinput1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (stodate1.length() < 3) {
                            errorflag = 1;
                            todateinput1.setError("Invalid Date");
                        } else {
                            errorflag = 0;
                            if (sname2.length() < 3) {
                                errorflag = 1;
                                nameinput2.setError("Invalid Course Name");
                            } else {
                                errorflag = 0;
                                if (sinst2.length() < 3) {
                                    errorflag = 1;
                                    instinput2.setError("Invalid Institute Name");
                                } else {
                                    errorflag = 0;
                                    if (sfromdate2.length() < 3) {
                                        errorflag = 1;
                                        fromdateinput2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stodate2.length() < 3) {
                                            errorflag = 1;
                                            todateinput2.setError("Invalid Date");
                                        } else {
                                            errorflag = 0;
                                            if (sname3.length() < 3) {
                                                errorflag = 1;
                                                nameinput3.setError("Invalid Course Name");
                                            } else {
                                                errorflag = 0;
                                                if (sinst3.length() < 3) {
                                                    errorflag = 1;
                                                    instinput3.setError("Invalid Institute Name");
                                                } else {
                                                    errorflag = 0;
                                                    if (sfromdate3.length() < 3) {
                                                        errorflag = 1;
                                                        fromdateinput3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (stodate3.length() < 3) {
                                                            errorflag = 1;
                                                            todateinput3.setError("Invalid Date");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sname4.length() < 3) {
                                                                errorflag = 1;
                                                                nameinput4.setError("Invalid Course Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sinst4.length() < 3) {
                                                                    errorflag = 1;
                                                                    instinput4.setError("Invalid Institute Name");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sfromdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        fromdateinput4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (stodate4.length() < 3) {
                                                                            errorflag = 1;
                                                                            todateinput4.setError("Invalid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (sname5.length() < 3) {
                                                                                errorflag = 1;
                                                                                nameinput5.setError("Invalid Course Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    instinput5.setError("Invalid Institute Name");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (stodate5.length() < 3) {
                                                                                            errorflag = 1;
                                                                                            todateinput5.setError("Invalid Date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (sname6.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                nameinput6.setError("Invalid Course Name");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sinst6.length() < 3) {
                                                                                                    errorflag = 1;
                                                                                                    instinput6.setError("Invalid Institute Name");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sfromdate6.length() < 3) {
                                                                                                        errorflag = 1;
                                                                                                        fromdateinput6.setError("Invalid Date");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (stodate6.length() < 3) {
                                                                                                            errorflag = 1;
                                                                                                            todateinput6.setError("Invalid Date");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (sname7.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                nameinput7.setError("Invalid Course Name");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sinst7.length() < 3) {
                                                                                                                    errorflag = 1;
                                                                                                                    instinput7.setError("Invalid Institute Name");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sfromdate7.length() < 3) {
                                                                                                                        errorflag = 1;
                                                                                                                        fromdateinput7.setError("Invalid Date");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (stodate7.length() < 3) {
                                                                                                                            errorflag = 1;
                                                                                                                            todateinput7.setError("Invalid Date");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (sname8.length() < 3) {
                                                                                                                                errorflag = 1;
                                                                                                                                nameinput8.setError("Invalid Course Name");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sinst8.length() < 3) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    instinput8.setError("Invalid Institute Name");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (sfromdate8.length() < 3) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        fromdateinput8.setError("Invalid Date");
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (stodate8.length() < 3) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            todateinput8.setError("Invalid Date");
                                                                                                                                        } else {
                                                                                                                                            errorflag = 0;
                                                                                                                                            if (sname9.length() < 3) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                nameinput9.setError("Invalid Course Name");
                                                                                                                                            } else {
                                                                                                                                                errorflag = 0;
                                                                                                                                                if (sinst9.length() < 3) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    instinput9.setError("Invalid Institute Name");
                                                                                                                                                } else {
                                                                                                                                                    errorflag = 0;
                                                                                                                                                    if (sfromdate9.length() < 3) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        fromdateinput9.setError("Invalid Date");
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (stodate9.length() < 3) {
                                                                                                                                                            errorflag = 1;
                                                                                                                                                            todateinput9.setError("Invalid Date");
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
                    }
                }
            }
        } else if (coursecount == 9) {
            if (sname1.length() < 3) {
                errorflag = 1;
                nameinput1.setError("Invalid Course Name");
            } else {
                errorflag = 0;
                if (sinst1.length() < 3) {
                    errorflag = 1;
                    instinput1.setError("Invalid Institute Name");
                } else {
                    errorflag = 0;
                    if (sfromdate1.length() < 3) {
                        errorflag = 1;
                        fromdateinput1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (stodate1.length() < 3) {
                            errorflag = 1;
                            todateinput1.setError("Invalid Date");
                        } else {
                            errorflag = 0;
                            if (sname2.length() < 3) {
                                errorflag = 1;
                                nameinput2.setError("Invalid Course Name");
                            } else {
                                errorflag = 0;
                                if (sinst2.length() < 3) {
                                    errorflag = 1;
                                    instinput2.setError("Invalid Institute Name");
                                } else {
                                    errorflag = 0;
                                    if (sfromdate2.length() < 3) {
                                        errorflag = 1;
                                        fromdateinput2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stodate2.length() < 3) {
                                            errorflag = 1;
                                            todateinput2.setError("Invalid Date");
                                        } else {
                                            errorflag = 0;
                                            if (sname3.length() < 3) {
                                                errorflag = 1;
                                                nameinput3.setError("Invalid Course Name");
                                            } else {
                                                errorflag = 0;
                                                if (sinst3.length() < 3) {
                                                    errorflag = 1;
                                                    instinput3.setError("Invalid Institute Name");
                                                } else {
                                                    errorflag = 0;
                                                    if (sfromdate3.length() < 3) {
                                                        errorflag = 1;
                                                        fromdateinput3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (stodate3.length() < 3) {
                                                            errorflag = 1;
                                                            todateinput3.setError("Invalid Date");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sname4.length() < 3) {
                                                                errorflag = 1;
                                                                nameinput4.setError("Invalid Course Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sinst4.length() < 3) {
                                                                    errorflag = 1;
                                                                    instinput4.setError("Invalid Institute Name");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sfromdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        fromdateinput4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (stodate4.length() < 3) {
                                                                            errorflag = 1;
                                                                            todateinput4.setError("Invalid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (sname5.length() < 3) {
                                                                                errorflag = 1;
                                                                                nameinput5.setError("Invalid Course Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    instinput5.setError("Invalid Institute Name");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (stodate5.length() < 3) {
                                                                                            errorflag = 1;
                                                                                            todateinput5.setError("Invalid Date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (sname6.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                nameinput6.setError("Invalid Course Name");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sinst6.length() < 3) {
                                                                                                    errorflag = 1;
                                                                                                    instinput6.setError("Invalid Institute Name");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sfromdate6.length() < 3) {
                                                                                                        errorflag = 1;
                                                                                                        fromdateinput6.setError("Invalid Date");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (stodate6.length() < 3) {
                                                                                                            errorflag = 1;
                                                                                                            todateinput6.setError("Invalid Date");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (sname7.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                nameinput7.setError("Invalid Course Name");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sinst7.length() < 3) {
                                                                                                                    errorflag = 1;
                                                                                                                    instinput7.setError("Invalid Institute Name");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sfromdate7.length() < 3) {
                                                                                                                        errorflag = 1;
                                                                                                                        fromdateinput7.setError("Invalid Date");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (stodate7.length() < 3) {
                                                                                                                            errorflag = 1;
                                                                                                                            todateinput7.setError("Invalid Date");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (sname8.length() < 3) {
                                                                                                                                errorflag = 1;
                                                                                                                                nameinput8.setError("Invalid Course Name");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sinst8.length() < 3) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    instinput8.setError("Invalid Institute Name");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (sfromdate8.length() < 3) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        fromdateinput8.setError("Invalid Date");
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (stodate8.length() < 3) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            todateinput8.setError("Invalid Date");
                                                                                                                                        } else {
                                                                                                                                            errorflag = 0;
                                                                                                                                            if (sname9.length() < 3) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                nameinput9.setError("Invalid Course Name");
                                                                                                                                            } else {
                                                                                                                                                errorflag = 0;
                                                                                                                                                if (sinst9.length() < 3) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    instinput9.setError("Invalid Institute Name");
                                                                                                                                                } else {
                                                                                                                                                    errorflag = 0;
                                                                                                                                                    if (sfromdate9.length() < 3) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        fromdateinput9.setError("Invalid Date");
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (stodate9.length() < 3) {
                                                                                                                                                            errorflag = 1;
                                                                                                                                                            todateinput9.setError("Invalid Date");
                                                                                                                                                        } else {
                                                                                                                                                            errorflag = 0;
                                                                                                                                                            if (sname10.length() < 3) {
                                                                                                                                                                errorflag = 1;
                                                                                                                                                                nameinput10.setError("Invalid Course Name");
                                                                                                                                                            } else {
                                                                                                                                                                errorflag = 0;
                                                                                                                                                                if (sinst10.length() < 3) {
                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                    instinput10.setError("Invalid Institute Name");
                                                                                                                                                                } else {
                                                                                                                                                                    errorflag = 0;
                                                                                                                                                                    if (sfromdate10.length() < 3) {
                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                        fromdateinput10.setError("Invalid Date");
                                                                                                                                                                    } else {
                                                                                                                                                                        errorflag = 0;
                                                                                                                                                                        if (stodate10.length() < 3) {
                                                                                                                                                                            errorflag = 1;
                                                                                                                                                                            todateinput10.setError("Invalid Date");
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
                Courses obj1 = new Courses(sname1, sinst1, sfromdate1, stodate1);
                Courses obj2 = new Courses(sname2, sinst2, sfromdate2, stodate2);
                Courses obj3 = new Courses(sname3, sinst3, sfromdate3, stodate3);
                Courses obj4 = new Courses(sname4, sinst4, sfromdate4, stodate4);
                Courses obj5 = new Courses(sname5, sinst5, sfromdate5, stodate5);
                Courses obj6 = new Courses(sname6, sinst6, sfromdate6, stodate6);
                Courses obj7 = new Courses(sname7, sinst7, sfromdate7, stodate7);
                Courses obj8 = new Courses(sname8, sinst8, sfromdate8, stodate8);
                Courses obj9 = new Courses(sname9, sinst9, sfromdate9, stodate9);
                Courses obj10 = new Courses(sname10, sinst10, sfromdate10, stodate10);

                coursesList.add(obj1);
                coursesList.add(obj2);
                coursesList.add(obj3);
                coursesList.add(obj4);
                coursesList.add(obj5);
                coursesList.add(obj6);
                coursesList.add(obj7);
                coursesList.add(obj8);
                coursesList.add(obj9);
                coursesList.add(obj10);

                String encObjString = OtoString(coursesList, MySharedPreferencesManager.getDigest1(MyProfileCourses.this), MySharedPreferencesManager.getDigest2(MyProfileCourses.this));


                new SaveCourses().execute(encObjString);
            } catch (Exception e) {
                Toast.makeText(MyProfileCourses.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
                                    MyProfileCourses.super.onBackPressed();
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
            MyProfileCourses.super.onBackPressed();

    }

    class SaveCourses extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("d", param[0]));       //0

            json = jParser.makeHttpRequest(MyConstants.url_savecourses, "GET", params);
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
                Toast.makeText(MyProfileCourses.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();


                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                s.setCourse1(sname1);
                s.setInst1(sinst1);
                s.setFromdate1(sfromdate1);
                s.setTodate1(stodate1);
                s.setCourse2(sname2);
                s.setInst2(sinst2);
                s.setFromdate2(sfromdate2);
                s.setTodate2(stodate2);
                s.setCourse3(sname3);
                s.setInst3(sinst3);
                s.setFromdate3(sfromdate3);
                s.setTodate3(stodate3);
                s.setCourse4(sname4);
                s.setInst4(sinst4);
                s.setFromdate4(sfromdate4);
                s.setTodate4(stodate4);
                s.setCourse5(sname5);
                s.setInst5(sinst5);
                s.setFromdate5(sfromdate5);
                s.setTodate5(stodate5);
                s.setCourse6(sname6);
                s.setInst6(sinst6);
                s.setFromdate6(sfromdate6);
                s.setTodate6(stodate6);
                s.setCourse7(sname7);
                s.setInst7(sinst7);
                s.setFromdate7(sfromdate7);
                s.setTodate7(stodate7);
                s.setCourse8(sname8);
                s.setInst8(sinst8);
                s.setFromdate8(sfromdate8);
                s.setTodate8(stodate8);
                s.setCourse9(sname9);
                s.setInst9(sinst9);
                s.setFromdate9(sfromdate9);
                s.setTodate9(stodate9);
                s.setCourse10(sname10);
                s.setInst10(sinst10);
                s.setFromdate10(sfromdate10);
                s.setTodate10(stodate10);


                MyProfileCourses.super.onBackPressed();
            } else
                Toast.makeText(MyProfileCourses.this, result, Toast.LENGTH_SHORT).show();

        }
    }

}
