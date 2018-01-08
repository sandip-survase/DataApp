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
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import placeme.octopusites.com.placeme.modal.Experiences;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class AdminExperiences extends AppCompatActivity {
    int expcount=0,editexp=0;
    View addmoreexp;
    TextView t,work1,work2,work3,work4,work5,work6,work7,work8,work9,work10;
    ImageView i;
    View trash1selectionview,trash2selectionview,trash3selectionview,trash4selectionview,trash5selectionview,trash6selectionview,trash7selectionview,trash8selectionview,trash9selectionview,trash10selectionview;
    int del=0;
    int errorflag=0,edittedFlag=0;
    int workinghereflag1=0,workinghereflag2=0,workinghereflag3=0,workinghereflag4=0,workinghereflag5=0,workinghereflag6=0,workinghereflag7=0,workinghereflag8=0,workinghereflag9=0,workinghereflag10=0;
    TextInputLayout todateinput1,todateinput2,todateinput3,todateinput4,todateinput5,todateinput6,todateinput7,todateinput8,todateinput9,todateinput10;
    TextInputLayout fromdateinput1,fromdateinput2,fromdateinput3,fromdateinput4,fromdateinput5,fromdateinput6,fromdateinput7,fromdateinput8,fromdateinput9,fromdateinput10;
    TextInputLayout postinput1,instinput1,postinput2,instinput2,postinput3,instinput3,postinput4,instinput4,postinput5,instinput5,postinput6,instinput6,postinput7,instinput7,postinput8,instinput8,postinput9,instinput9,postinput10,instinput10;
    SwitchCompat expsw1,expsw2,expsw3,expsw4,expsw5,expsw6,expsw7,expsw8,expsw9,expsw10;

    String username,role;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    TextInputEditText post1,inst1,post2,inst2,post3,inst3,post4,inst4,post5,inst5,post6,inst6,post7,inst7,post8,inst8,post9,inst9,post10,inst10;
    TextInputEditText fromdate1,todate1,fromdate2,todate2,fromdate3,todate3,fromdate4,todate4,fromdate5,todate5,fromdate6,todate6,fromdate7,todate7,fromdate8,todate8,fromdate9,todate9,fromdate10,todate10;

    String  spost1="",sinst1="",spost2="",sinst2="",spost3="",sinst3="",spost4="",sinst4="",spost5="",sinst5="", spost6="",sinst6="",spost7="",sinst7="",spost8="",sinst8="",spost9="",sinst9="",spost10="",sinst10="";
    String  sfromdate1="",stodate1="",sfromdate2="",stodate2="",sfromdate3="",stodate3="",sfromdate4="",stodate4="",sfromdate5="",stodate5="",sfromdate6="",stodate6="",sfromdate7="",stodate7="",sfromdate8="",stodate8="",sfromdate9="",stodate9="",sfromdate10="",stodate10="";

    AdminData a = new AdminData();


    int todateflag1=0,todateflag2=0,todateflag3=0,todateflag4=0,todateflag5=0,todateflag6=0,todateflag7=0,todateflag8=0,todateflag9=0,todateflag10=0;

    Hashtable<String, Integer> source = new Hashtable<String, Integer>();
    HashMap<String, Integer> map = new HashMap(source);

    ArrayList<Experiences> experiencesList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_experiences);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Experiences");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        postinput1=(TextInputLayout)findViewById(R.id.postinput1);
        postinput2=(TextInputLayout)findViewById(R.id.postinput2);
        postinput3=(TextInputLayout)findViewById(R.id.postinput3);
        postinput4=(TextInputLayout)findViewById(R.id.postinput4);
        postinput5=(TextInputLayout)findViewById(R.id.postinput5);
        postinput6=(TextInputLayout)findViewById(R.id.postinput6);
        postinput7=(TextInputLayout)findViewById(R.id.postinput7);
        postinput8=(TextInputLayout)findViewById(R.id.postinput8);
        postinput9=(TextInputLayout)findViewById(R.id.postinput9);
        postinput10=(TextInputLayout)findViewById(R.id.postinput10);

        instinput1=(TextInputLayout)findViewById(R.id.instinput1);
        instinput2=(TextInputLayout)findViewById(R.id.instinput2);
        instinput3=(TextInputLayout)findViewById(R.id.instinput3);
        instinput4=(TextInputLayout)findViewById(R.id.instinput4);
        instinput5=(TextInputLayout)findViewById(R.id.instinput5);
        instinput6=(TextInputLayout)findViewById(R.id.instinput6);
        instinput7=(TextInputLayout)findViewById(R.id.instinput7);
        instinput8=(TextInputLayout)findViewById(R.id.instinput8);
        instinput9=(TextInputLayout)findViewById(R.id.instinput9);
        instinput10=(TextInputLayout)findViewById(R.id.instinput10);

        postinput1.setTypeface(Z.getLight(this));
        postinput2.setTypeface(Z.getLight(this));
        postinput3.setTypeface(Z.getLight(this));
        postinput4.setTypeface(Z.getLight(this));
        postinput5.setTypeface(Z.getLight(this));
        postinput6.setTypeface(Z.getLight(this));
        postinput7.setTypeface(Z.getLight(this));
        postinput8.setTypeface(Z.getLight(this));
        postinput9.setTypeface(Z.getLight(this));
        postinput10.setTypeface(Z.getLight(this));
        instinput1.setTypeface(Z.getLight(this));
        instinput2.setTypeface(Z.getLight(this));
        instinput3.setTypeface(Z.getLight(this));
        instinput4.setTypeface(Z.getLight(this));
        instinput5.setTypeface(Z.getLight(this));
        instinput6.setTypeface(Z.getLight(this));
        instinput7.setTypeface(Z.getLight(this));
        instinput8.setTypeface(Z.getLight(this));
        instinput9.setTypeface(Z.getLight(this));
        instinput10.setTypeface(Z.getLight(this));

        fromdate1=(TextInputEditText)findViewById(R.id.fromdate1);
        todate1=(TextInputEditText)findViewById(R.id.todate1);
        fromdate2=(TextInputEditText)findViewById(R.id.fromdate2);
        todate2=(TextInputEditText)findViewById(R.id.todate2);
        fromdate3=(TextInputEditText)findViewById(R.id.fromdate3);
        todate3=(TextInputEditText)findViewById(R.id.todate3);
        fromdate4=(TextInputEditText)findViewById(R.id.fromdate4);
        todate4=(TextInputEditText)findViewById(R.id.todate4);
        fromdate5=(TextInputEditText)findViewById(R.id.fromdate5);
        todate5=(TextInputEditText)findViewById(R.id.todate5);
        fromdate6=(TextInputEditText)findViewById(R.id.fromdate6);
        todate6=(TextInputEditText)findViewById(R.id.todate6);
        fromdate7=(TextInputEditText)findViewById(R.id.fromdate7);
        todate7=(TextInputEditText)findViewById(R.id.todate7);
        fromdate8=(TextInputEditText)findViewById(R.id.fromdate8);
        todate8=(TextInputEditText)findViewById(R.id.todate8);
        fromdate9=(TextInputEditText)findViewById(R.id.fromdate9);
        todate9=(TextInputEditText)findViewById(R.id.todate9);
        fromdate10=(TextInputEditText)findViewById(R.id.fromdate10);
        todate10=(TextInputEditText)findViewById(R.id.todate10);

        fromdate1.setTypeface(Z.getBold(this));
        todate1.setTypeface(Z.getBold(this));
        fromdate2.setTypeface(Z.getBold(this));
        todate2.setTypeface(Z.getBold(this));
        fromdate3.setTypeface(Z.getBold(this));
        todate3.setTypeface(Z.getBold(this));
        fromdate4.setTypeface(Z.getBold(this));
        todate4.setTypeface(Z.getBold(this));
        fromdate5.setTypeface(Z.getBold(this));
        todate5.setTypeface(Z.getBold(this));
        fromdate6.setTypeface(Z.getBold(this));
        todate6.setTypeface(Z.getBold(this));
        fromdate7.setTypeface(Z.getBold(this));
        todate7.setTypeface(Z.getBold(this));
        fromdate8.setTypeface(Z.getBold(this));
        todate8.setTypeface(Z.getBold(this));
        fromdate9.setTypeface(Z.getBold(this));
        todate9.setTypeface(Z.getBold(this));
        fromdate10.setTypeface(Z.getBold(this));
        todate10.setTypeface(Z.getBold(this));

        fromdateinput1 =(TextInputLayout)findViewById(R.id.fromdateinput1);
        fromdateinput2 =(TextInputLayout)findViewById(R.id.fromdateinput2);
        fromdateinput3 =(TextInputLayout)findViewById(R.id.fromdateinput3);
        fromdateinput4 =(TextInputLayout)findViewById(R.id.fromdateinput4);
        fromdateinput5 =(TextInputLayout)findViewById(R.id.fromdateinput5);
        fromdateinput6 =(TextInputLayout)findViewById(R.id.fromdateinput6);
        fromdateinput7 =(TextInputLayout)findViewById(R.id.fromdateinput7);
        fromdateinput8 =(TextInputLayout)findViewById(R.id.fromdateinput8);
        fromdateinput9 =(TextInputLayout)findViewById(R.id.fromdateinput9);
        fromdateinput10=(TextInputLayout)findViewById(R.id.fromdateinput10);

        fromdateinput1.setTypeface(Z.getLight(this));
        fromdateinput2.setTypeface(Z.getLight(this));
        fromdateinput3.setTypeface(Z.getLight(this));
        fromdateinput4.setTypeface(Z.getLight(this));
        fromdateinput5.setTypeface(Z.getLight(this));
        fromdateinput6.setTypeface(Z.getLight(this));
        fromdateinput7.setTypeface(Z.getLight(this));
        fromdateinput8.setTypeface(Z.getLight(this));
        fromdateinput9.setTypeface(Z.getLight(this));
        fromdateinput10.setTypeface(Z.getLight(this));

        todateinput1 =(TextInputLayout)findViewById(R.id.todateinput1);
        todateinput2 =(TextInputLayout)findViewById(R.id.todateinput2);
        todateinput3 =(TextInputLayout)findViewById(R.id.todateinput3);
        todateinput4 =(TextInputLayout)findViewById(R.id.todateinput4);
        todateinput5 =(TextInputLayout)findViewById(R.id.todateinput5);
        todateinput6 =(TextInputLayout)findViewById(R.id.todateinput6);
        todateinput7 =(TextInputLayout)findViewById(R.id.todateinput7);
        todateinput8 =(TextInputLayout)findViewById(R.id.todateinput8);
        todateinput9 =(TextInputLayout)findViewById(R.id.todateinput9);
        todateinput10=(TextInputLayout)findViewById(R.id.todateinput10);

        todateinput1.setTypeface(Z.getLight(this));
        todateinput2.setTypeface(Z.getLight(this));
        todateinput3.setTypeface(Z.getLight(this));
        todateinput4.setTypeface(Z.getLight(this));
        todateinput5.setTypeface(Z.getLight(this));
        todateinput6.setTypeface(Z.getLight(this));
        todateinput7.setTypeface(Z.getLight(this));
        todateinput8.setTypeface(Z.getLight(this));
        todateinput9.setTypeface(Z.getLight(this));
        todateinput10.setTypeface(Z.getLight(this));

        TextView exptxt=(TextView)findViewById(R.id.exptxt);
        exptxt.setTypeface(Z.getBold(this));

        fromdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(fromdate1,fromdateinput1);
            }
        });
        todate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(todate1,todateinput1);
            }
        });
        fromdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(fromdate2,fromdateinput2);
            }
        });
        todate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(todate2,todateinput2);
            }
        });
        fromdate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(fromdate3,fromdateinput3);
            }
        });
        todate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(todate3,todateinput3);
            }
        });
        fromdate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(fromdate4,fromdateinput4);
            }
        });
        todate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(todate4,todateinput4);
            }
        });
        fromdate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(fromdate5,fromdateinput5);
            }
        });
        todate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(todate5,todateinput5);
            }
        });
        fromdate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(fromdate6,fromdateinput6);
            }
        });
        todate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(todate6,todateinput6);
            }
        });
        fromdate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(fromdate7,fromdateinput7);
            }
        });
        todate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(todate7,todateinput7);
            }
        });
        fromdate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(fromdate8,fromdateinput8);
            }
        });
        todate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(todate8,todateinput8);
            }
        });
        fromdate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(fromdate9,fromdateinput9);
            }
        });
        todate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(todate9,todateinput9);
            }
        });
        fromdate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(fromdate10,fromdateinput10);
            }
        });
        todate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(todate10,todateinput10);
            }
        });

        post1=(TextInputEditText)findViewById(R.id.post1);

        inst1 = (TextInputEditText)findViewById(R.id.inst1);
        post2=(TextInputEditText)findViewById(R.id.post2);
        inst2 = (TextInputEditText)findViewById(R.id.inst2);
        post3=(TextInputEditText)findViewById(R.id.post3);
        inst3 = (TextInputEditText)findViewById(R.id.inst3);
        post4=(TextInputEditText)findViewById(R.id.post4);
        inst4 = (TextInputEditText)findViewById(R.id.inst4);
        post5=(TextInputEditText)findViewById(R.id.post5);
        inst5 = (TextInputEditText)findViewById(R.id.inst5);
        post6=(TextInputEditText)findViewById(R.id.post6);
        inst6 = (TextInputEditText)findViewById(R.id.inst6);
        post7=(TextInputEditText)findViewById(R.id.post7);
        inst7 = (TextInputEditText)findViewById(R.id.inst7);
        post8=(TextInputEditText)findViewById(R.id.post8);
        inst8 = (TextInputEditText)findViewById(R.id.inst8);
        post9=(TextInputEditText)findViewById(R.id.post9);
        inst9 = (TextInputEditText)findViewById(R.id.inst9);
        post10=(TextInputEditText)findViewById(R.id.post10);
        inst10 = (TextInputEditText)findViewById(R.id.inst10);

        expsw1=(SwitchCompat)findViewById(R.id.switch1);
        expsw2=(SwitchCompat)findViewById(R.id.switch2);
        expsw3=(SwitchCompat)findViewById(R.id.switch3);
        expsw4=(SwitchCompat)findViewById(R.id.switch4);
        expsw5=(SwitchCompat)findViewById(R.id.switch5);
        expsw6=(SwitchCompat)findViewById(R.id.switch6);
        expsw7=(SwitchCompat)findViewById(R.id.switch7);
        expsw8=(SwitchCompat)findViewById(R.id.switch8);
        expsw9=(SwitchCompat)findViewById(R.id.switch9);
        expsw10=(SwitchCompat)findViewById(R.id.switch10);

        post1.setTypeface(Z.getBold(this));
        inst1.setTypeface(Z.getBold(this));
        post2.setTypeface(Z.getBold(this));
        inst2.setTypeface(Z.getBold(this));
        post3.setTypeface(Z.getBold(this));
        inst3.setTypeface(Z.getBold(this));
        post4.setTypeface(Z.getBold(this));
        inst4.setTypeface(Z.getBold(this));
        post5.setTypeface(Z.getBold(this));
        inst5.setTypeface(Z.getBold(this));
        post6.setTypeface(Z.getBold(this));
        inst6.setTypeface(Z.getBold(this));
        post7.setTypeface(Z.getBold(this));
        inst7.setTypeface(Z.getBold(this));
        post8.setTypeface(Z.getBold(this));
        inst8.setTypeface(Z.getBold(this));
        post9.setTypeface(Z.getBold(this));
        inst9.setTypeface(Z.getBold(this));
        post10.setTypeface(Z.getBold(this));
        inst10.setTypeface(Z.getBold(this));


        work1=(TextView)findViewById(R.id.work1);
        work2=(TextView)findViewById(R.id.work2);
        work3=(TextView)findViewById(R.id.work3);
        work4=(TextView)findViewById(R.id.work4);
        work5=(TextView)findViewById(R.id.work5);
        work6=(TextView)findViewById(R.id.work6);
        work7=(TextView)findViewById(R.id.work7);
        work8=(TextView)findViewById(R.id.work8);
        work9=(TextView)findViewById(R.id.work9);
        work10=(TextView)findViewById(R.id.work10);

        work1.setTypeface(Z.getLight(this));
        work2.setTypeface(Z.getLight(this));
        work3.setTypeface(Z.getLight(this));
        work4.setTypeface(Z.getLight(this));
        work5.setTypeface(Z.getLight(this));
        work6.setTypeface(Z.getLight(this));
        work7.setTypeface(Z.getLight(this));
        work8.setTypeface(Z.getLight(this));
        work9.setTypeface(Z.getLight(this));
        work10.setTypeface(Z.getLight(this));


        t=(TextView)findViewById(R.id.addmoreexptxt);
        t.setTypeface(Z.getBold(this));
        i=(ImageView)findViewById(R.id.addmoreexpimg);
        addmoreexp=(View)findViewById(R.id.addmoreexp);

//getters
        try {


            spost1 = a.getPost1e();
            sinst1 = a.getInst1e();
            sfromdate1 = a.getFromdate1e();
            stodate1 = a.getTodate1e();
            spost2 = a.getPost2e();
            sinst2 = a.getInst2e();
            sfromdate2 = a.getFromdate2e();
            stodate2 = a.getTodate2e();
            spost3 = a.getPost3e();
            sinst3 = a.getInst3e();
            sfromdate3 = a.getFromdate3e();
            stodate3 = a.getTodate3e();
            spost4 = a.getPost4e();
            sinst4 = a.getInst4e();
            sfromdate4 = a.getFromdate4e();
            stodate4 = a.getTodate4e();
            spost5 = a.getPost5e();
            sinst5 = a.getInst5e();
            sfromdate5 = a.getFromdate5e();
            stodate5 = a.getTodate5e();
            spost6 = a.getPost6e();
            sinst6 = a.getInst6e();
            sfromdate6 = a.getFromdate6e();
            stodate6 = a.getTodate6e();
            spost7 = a.getPost7e();
            sinst7 = a.getInst7e();
            sfromdate7 = a.getFromdate7e();
            stodate7 = a.getTodate7e();
            spost8 = a.getPost8e();
            sinst8 = a.getInst8e();
            sfromdate8 = a.getFromdate8e();
            stodate8 = a.getTodate8e();
            spost9 = a.getPost9e();
            sinst9 = a.getInst9e();
            sfromdate9 = a.getFromdate9e();
            stodate9 = a.getTodate9e();
            spost10 = a.getPost10e();
            sinst10 = a.getInst10e();
            sfromdate10 = a.getFromdate10e();
            stodate10 = a.getTodate10e();

            if (spost1 != null) {
                if (spost1.length() > 1) {

                    post1.setText(spost1);
                    inst1.setText(sinst1);
                    fromdate1.setText(sfromdate1);

                    if (stodate1.length() > 0) {
                        todate1.setText(stodate1);
                    } else {
                        expsw1.setChecked(true);
                        todate1.setText("");
                        workinghereflag1 = 1;
                        todateinput1.setVisibility(View.INVISIBLE);
                    }

                }


            }
            if (spost2 != null) {
                if (spost2.length() > 1) {
                    post2.setText(spost2);
                    inst2.setText(sinst2);
                    fromdate2.setText(sfromdate2);
                    if (stodate2.length() > 0) {

                        todate2.setText(stodate2);

                    } else {
                        expsw2.setChecked(true);
                        todate2.setText("");
                        todateinput2.setVisibility(View.INVISIBLE);
                        workinghereflag2 = 1;

                    }


                    View v = (View) findViewById(R.id.expline1);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl2);
                    relativeLayout1.setVisibility(View.VISIBLE);

                    expcount++;
                }
            }
            if (spost3 != null) {

                if (spost3.length() > 1) {
                    post3.setText(spost3);
                    inst3.setText(sinst3);
                    fromdate3.setText(sfromdate3);
                    if (stodate3.length() > 0) {

                        todate3.setText(stodate3);

                    } else {
                        expsw3.setChecked(true);
                        todate3.setText("");
                        todateinput3.setVisibility(View.INVISIBLE);
                        workinghereflag3 = 1;

                    }


                    View v = (View) findViewById(R.id.expline2);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl3);
                    relativeLayout1.setVisibility(View.VISIBLE);

                    expcount++;
                }

            }
            if (spost4 != null) {
                if (spost4.length() > 1) {

                    post4.setText(spost4);
                    inst4.setText(sinst4);
                    fromdate4.setText(sfromdate4);
                    if (stodate4.length() > 0) {

                        todate4.setText(stodate4);

                    } else {
                        expsw4.setChecked(true);
                        todate4.setText("");
                        todateinput4.setVisibility(View.INVISIBLE);
                        workinghereflag4 = 1;

                    }


                    View v = (View) findViewById(R.id.expline3);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl4);
                    relativeLayout1.setVisibility(View.VISIBLE);

                    expcount++;

                }
            }
            if (spost5 != null) {
                if (spost5.length() > 1) {

                    post5.setText(spost5);
                    inst5.setText(sinst5);
                    fromdate5.setText(sfromdate5);
                    if (stodate5.length() > 0) {

                        todate5.setText(stodate5);

                    } else {
                        expsw5.setChecked(true);
                        todate5.setText("");
                        todateinput5.setVisibility(View.INVISIBLE);
                        workinghereflag5 = 1;

                    }


                    View v = (View) findViewById(R.id.expline4);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl5);
                    relativeLayout1.setVisibility(View.VISIBLE);

                    expcount++;

                }
            }
            if (spost6 != null) {
                if (spost6.length() > 1) {

                    post6.setText(spost6);
                    inst6.setText(sinst6);
                    fromdate6.setText(sfromdate6);
                    if (stodate6.length() > 0) {

                        todate6.setText(stodate6);

                    } else {
                        expsw6.setChecked(true);
                        todate6.setText("");
                        todateinput6.setVisibility(View.INVISIBLE);
                        workinghereflag6 = 1;

                    }


                    View v = (View) findViewById(R.id.expline5);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl6);
                    relativeLayout1.setVisibility(View.VISIBLE);

                    expcount++;

                }
            }
            if (spost7 != null) {
                if (spost7.length() > 1) {

                    post7.setText(spost7);
                    inst7.setText(sinst7);
                    fromdate7.setText(sfromdate7);
                    if (stodate7.length() > 0) {

                        todate7.setText(stodate7);

                    } else {
                        expsw7.setChecked(true);
                        todate7.setText("");
                        todateinput7.setVisibility(View.INVISIBLE);
                        workinghereflag7 = 1;

                    }


                    View v = (View) findViewById(R.id.expline6);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl7);
                    relativeLayout1.setVisibility(View.VISIBLE);

                    expcount++;

                }
            }

            if (spost8 != null) {
                if (spost8.length() > 1) {

                    post8.setText(spost8);
                    inst8.setText(sinst8);
                    fromdate8.setText(sfromdate8);
                    if (stodate8.length() > 0) {

                        todate8.setText(stodate8);

                    } else {
                        expsw8.setChecked(true);
                        todate8.setText("");
                        todateinput8.setVisibility(View.INVISIBLE);
                        workinghereflag8 = 1;

                    }


                    View v = (View) findViewById(R.id.expline7);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl8);
                    relativeLayout1.setVisibility(View.VISIBLE);

                    expcount++;

                }
            }
            if (spost9 != null) {
                if (spost9.length() > 1) {

                    post9.setText(spost9);
                    inst9.setText(sinst9);
                    fromdate9.setText(sfromdate9);
                    if (stodate9.length() > 0) {

                        todate9.setText(stodate9);

                    } else {
                        expsw9.setChecked(true);
                        todate9.setText("");
                        todateinput9.setVisibility(View.INVISIBLE);
                        workinghereflag8 = 1;

                    }


                    View v = (View) findViewById(R.id.expline8);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl9);
                    relativeLayout1.setVisibility(View.VISIBLE);


                    expcount++;

                }
            }
            if (spost10 != null) {
                if (spost10.length() > 1) {

                    post10.setText(spost10);
                    inst10.setText(sinst10);
                    fromdate10.setText(sfromdate10);
                    if (stodate10.length() > 0) {

                        todate10.setText(stodate10);

                    } else {
                        expsw10.setChecked(true);
                        todate10.setText("");
                        todateinput10.setVisibility(View.INVISIBLE);
                        workinghereflag10 = 1;

                    }

                    View v = (View) findViewById(R.id.expline9);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl10);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    expcount++;
//
                    addmoreexp.setVisibility(View.GONE);
                    t.setVisibility(View.GONE);
                    i.setVisibility(View.GONE);



                }
            }
        } catch (Exception e) {

        }

        map.put("Jan", 1);
        map.put("Feb", 2);
        map.put("Mar", 3);
        map.put("Apr", 4);
        map.put("May", 5);
        map.put("Jun", 6);
        map.put("Jul", 7);
        map.put("Aug", 8);
        map.put("Sep", 9);
        map.put("Oct", 10);
        map.put("Nov", 11);
        map.put("Dec", 12);

        post1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                postinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        post2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                postinput2.setError(null);
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
                edittedFlag=1;
                postinput3.setError(null);
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
                edittedFlag=1;
                postinput4.setError(null);
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
                edittedFlag=1;
                postinput5.setError(null);
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
                edittedFlag=1;
                postinput6.setError(null);
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
                edittedFlag=1;
                postinput7.setError(null);
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
                edittedFlag=1;
                postinput8.setError(null);
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
                edittedFlag=1;
                postinput9.setError(null);
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
                edittedFlag=1;
                postinput10.setError(null);
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
                edittedFlag=1;
                instinput1.setError(null);

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
                edittedFlag=1;
                instinput2.setError(null);

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
                edittedFlag=1;
                instinput3.setError(null);

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
                edittedFlag=1;
                instinput4.setError(null);

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
                edittedFlag=1;
                instinput5.setError(null);

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
                edittedFlag=1;
                instinput6.setError(null);
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
                edittedFlag=1;
                instinput7.setError(null);

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
                edittedFlag=1;
                instinput8.setError(null);

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
                edittedFlag=1;
                instinput9.setError(null);

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
                edittedFlag=1;
                instinput10.setError(null);

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
                edittedFlag=1;
                fromdateinput1.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {


                    sfromdate1 = fromdate1.getText().toString();
                    stodate1 = todate1.getText().toString();
                    if (!stodate1.equals("" ) && !sfromdate1.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate1.length() - 6; i++) {
                            fmonth += sfromdate1.charAt(i);
                        }
                        for (int i = 5; i < sfromdate1.length(); i++) {
                            fyears += sfromdate1.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate1.length() - 6; i++) {
                            tmonth += stodate1.charAt(i);
                        }
                        for (int i = 5; i < stodate1.length(); i++) {
                            tyears += stodate1.charAt(i);
                        }


                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);

                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            fromdate1.setText("");
                            fromdateinput1.setError("Kindly enter valid date");
                        } else {
                            //allowed
                            todateflag1 = 0;
                            fromdateinput1.setError(null);
                            todateinput1.setError(null);

                        }
                    }
                } catch(Exception e){
                }

            }
        });
//fffff
        fromdate2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fromdateinput2.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate2 = fromdate2.getText().toString();
                    stodate2 = todate2.getText().toString();
                    if (!stodate2.equals("" ) && !sfromdate2.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate2.length() - 6; i++) {
                            fmonth += sfromdate2.charAt(i);
                        }
                        for (int i = 5; i < sfromdate2.length(); i++) {
                            fyears += sfromdate2.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate2.length() - 6; i++) {
                            tmonth += stodate2.charAt(i);
                        }
                        for (int i = 5; i < stodate2.length(); i++) {
                            tyears += stodate2.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);

                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            fromdate2.setText("");

                            fromdateinput2.setError("Kindly enter valid date");
                        } else {
                            //allowed
                            todateflag1 = 0;
                            fromdateinput2.setError(null);
                            todateinput2.setError(null);

                        }
                    }
                } catch(Exception e){
                }

            }
        });

        fromdate3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fromdateinput3.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate3 = fromdate3.getText().toString();
                    stodate3 = todate3.getText().toString();
                    if (!stodate3.equals("" ) && !sfromdate3.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate3.length() - 6; i++) {
                            fmonth += sfromdate3.charAt(i);
                        }
                        for (int i = 5; i < sfromdate3.length(); i++) {
                            fyears += sfromdate3.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate3.length() - 6; i++) {
                            tmonth += stodate3.charAt(i);
                        }
                        for (int i = 5; i < stodate3.length(); i++) {
                            tyears += stodate3.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);

                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            fromdate3.setText("");
                            fromdateinput3.setError("Kindly enter valid date");
                        } else {
                            //allowed
                            todateflag1 = 0;
                            fromdateinput3.setError(null);
                            todateinput3.setError(null);

                        }
                    }
                } catch(Exception e){
                }


            }
        });

        fromdate4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fromdateinput4.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate4 = fromdate4.getText().toString();
                    stodate4 = todate4.getText().toString();
                    if (!stodate4.equals("" ) && !sfromdate4.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate4.length() - 6; i++) {
                            fmonth += sfromdate4.charAt(i);
                        }
                        for (int i = 5; i < sfromdate4.length(); i++) {
                            fyears += sfromdate4.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate4.length() - 6; i++) {
                            tmonth += stodate4.charAt(i);
                        }
                        for (int i = 5; i < stodate4.length(); i++) {
                            tyears += stodate4.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);

                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            fromdate4.setText("");
                            fromdateinput4.setError("Kindly enter valid date");
                        } else {
                            //allowed
                            todateflag1 = 0;
                            fromdateinput4.setError(null);
                            todateinput4.setError(null);

                        }
                    }
                } catch(Exception e){
                }

            }
        });

        fromdate5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fromdateinput5.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate5 = fromdate5.getText().toString();
                    stodate5 = todate5.getText().toString();
                    if (!stodate5.equals("" ) && !sfromdate5.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate5.length() - 6; i++) {
                            fmonth += sfromdate5.charAt(i);
                        }
                        for (int i = 5; i < sfromdate5.length(); i++) {
                            fyears += sfromdate5.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate5.length() - 6; i++) {
                            tmonth += stodate5.charAt(i);
                        }
                        for (int i = 5; i < stodate5.length(); i++) {
                            tyears += stodate5.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);

                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            fromdate5.setText("");
                            fromdateinput5.setError("Kindly enter valid date");
                        } else {
                            //allowed
                            todateflag1 = 0;
                            fromdateinput5.setError(null);
                            todateinput5.setError(null);

                        }
                    }
                } catch(Exception e){
                }

            }
        });

        fromdate6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fromdateinput6.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate6 = fromdate6.getText().toString();
                    stodate6 = todate6.getText().toString();
                    if (!stodate6.equals("" ) && !sfromdate6.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate6.length() - 6; i++) {
                            fmonth += sfromdate6.charAt(i);
                        }
                        for (int i = 5; i < sfromdate6.length(); i++) {
                            fyears += sfromdate6.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate6.length() - 6; i++) {
                            tmonth += stodate6.charAt(i);
                        }
                        for (int i = 5; i < stodate6.length(); i++) {
                            tyears += stodate6.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);

                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            fromdate6.setText("");
                            fromdateinput6.setError("Kindly enter valid date");
                        } else {
                            //allowed
                            todateflag1 = 0;
                            fromdateinput6.setError(null);
                            todateinput6.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });

        fromdate7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fromdateinput7.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate7 = fromdate7.getText().toString();
                    stodate7 = todate7.getText().toString();
                    if (!stodate7.equals("" ) && !sfromdate7.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate7.length() - 6; i++) {
                            fmonth += sfromdate7.charAt(i);
                        }
                        for (int i = 5; i < sfromdate7.length(); i++) {
                            fyears += sfromdate7.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate7.length() - 6; i++) {
                            tmonth += stodate7.charAt(i);
                        }
                        for (int i = 5; i < stodate7.length(); i++) {
                            tyears += stodate7.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);

                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            fromdate7.setText("");
                            fromdateinput7.setError("Kindly enter valid date");
                        } else {
                            //allowed
                            todateflag1 = 0;
                            fromdateinput7.setError(null);
                            todateinput7.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });
        fromdate8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fromdateinput8.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate8 = fromdate8.getText().toString();
                    stodate8 = todate8.getText().toString();
                    if (!stodate8.equals("" ) && !sfromdate8.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate8.length() - 6; i++) {
                            fmonth += sfromdate8.charAt(i);
                        }
                        for (int i = 5; i < sfromdate8.length(); i++) {
                            fyears += sfromdate8.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate8.length() - 6; i++) {
                            tmonth += stodate8.charAt(i);
                        }
                        for (int i = 5; i < stodate8.length(); i++) {
                            tyears += stodate8.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);

                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            fromdate8.setText("");
                            fromdateinput8.setError("Kindly enter valid date");
                        } else {
                            //allowed
                            todateflag1 = 0;
                            fromdateinput8.setError(null);
                            todateinput8.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });

        fromdate9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fromdateinput9.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate9 = fromdate9.getText().toString();
                    stodate9 = todate9.getText().toString();
                    if (!stodate9.equals("" ) && !sfromdate9.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate9.length() - 6; i++) {
                            fmonth += sfromdate9.charAt(i);
                        }
                        for (int i = 5; i < sfromdate9.length(); i++) {
                            fyears += sfromdate9.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate9.length() - 6; i++) {
                            tmonth += stodate9.charAt(i);
                        }
                        for (int i = 5; i < stodate9.length(); i++) {
                            tyears += stodate9.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);

                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            fromdate9.setText("");
                            fromdateinput9.setError("Kindly enter valid date");
                        } else {
                            //allowed
                            todateflag1 = 0;
                            fromdateinput9.setError(null);
                            todateinput9.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });


        fromdate10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                fromdateinput10.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate10 = fromdate10.getText().toString();
                    stodate10 = todate10.getText().toString();
                    if (!stodate10.equals("" ) && !sfromdate10.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate10.length() - 6; i++) {
                            fmonth += sfromdate10.charAt(i);
                        }
                        for (int i = 5; i < sfromdate10.length(); i++) {
                            fyears += sfromdate10.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate10.length() - 6; i++) {
                            tmonth += stodate10.charAt(i);
                        }
                        for (int i = 5; i < stodate10.length(); i++) {
                            tyears += stodate10.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);

                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
//                        Toast.makeText(AdminExperiences.this,""+fm+" "+fy+"  TO  "+tm+" "+ty, Toast.LENGTH_LONG).show();
//                        Toast.makeText(AdminExperiences.this,""+exp_in_months2, Toast.LENGTH_LONG).show();


                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            fromdate10.setText("");
                            fromdateinput10.setError("Kindly enter valid date");
                        } else {
                            //allowed
                            todateflag1 = 0;
                            fromdateinput10.setError(null);
                            todateinput10.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });


        todate1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                todateinput1.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    sfromdate1 = fromdate1.getText().toString();
                    stodate1 = todate1.getText().toString();
                    if (!sfromdate1.equals("") &&  !stodate1.equals("")) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate1.length() - 6; i++) {
                            fmonth += sfromdate1.charAt(i);
                        }
                        for (int i = 5; i < sfromdate1.length(); i++) {
                            fyears += sfromdate1.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate1.length() - 6; i++) {
                            tmonth += stodate1.charAt(i);
                        }
                        for (int i = 5; i < stodate1.length(); i++) {
                            tyears += stodate1.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);



                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            todate1.setText("");
                            todateinput1.setError("Kindly enter valid date");

                        } else {
                            //allowed
                            todateflag1 = 0;
                            todateinput1.setError(null);
                            fromdateinput1.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });

        todate2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                todateinput2.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    sfromdate2 = fromdate2.getText().toString();
                    stodate2 = todate2.getText().toString();
                    if (!stodate2.equals("" ) && !sfromdate2.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate2.length() - 6; i++) {
                            fmonth += sfromdate2.charAt(i);
                        }
                        for (int i = 5; i < sfromdate2.length(); i++) {
                            fyears += sfromdate2.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate2.length() - 6; i++) {
                            tmonth += stodate2.charAt(i);
                        }
                        for (int i = 5; i < stodate2.length(); i++) {
                            tyears += stodate2.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);
//                        Toast.makeText(AdminExperiences.this,""+fm+" "+fy+"  TO  "+tm+" "+ty, Toast.LENGTH_LONG).show();


                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
//                        Toast.makeText(AdminExperiences.this,""+exp_in_months2, Toast.LENGTH_LONG).show();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            todate2.setText("");
                            todateinput2.setError("Kindly enter valid date");

                        } else {
                            //allowed
                            todateflag1 = 0;
                            todateinput2.setError(null);
                            fromdateinput2.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }



        });

        todate3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                todateinput3.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    sfromdate3 = fromdate3.getText().toString();
                    stodate3 = todate3.getText().toString();
                    if (!stodate3.equals("" ) && !sfromdate3.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate3.length() - 6; i++) {
                            fmonth += sfromdate3.charAt(i);
                        }
                        for (int i = 5; i < sfromdate3.length(); i++) {
                            fyears += sfromdate3.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate3.length() - 6; i++) {
                            tmonth += stodate3.charAt(i);
                        }
                        for (int i = 5; i < stodate3.length(); i++) {
                            tyears += stodate3.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);


                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            todate3.setText("");
                            todateinput3.setError("Kindly enter valid date");

                        } else {
                            //allowed
                            todateflag1 = 0;
                            todateinput3.setError(null);
                            fromdateinput3.setError(null);

                        }
                    }
                } catch(Exception e){
                }

            }
        });

        todate4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                todateinput4.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {

                    sfromdate4 = fromdate4.getText().toString();
                    stodate4 = todate4.getText().toString();
                    if (!stodate4.equals("" ) && !sfromdate4.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate4.length() - 6; i++) {
                            fmonth += sfromdate4.charAt(i);
                        }
                        for (int i = 5; i < sfromdate4.length(); i++) {
                            fyears += sfromdate4.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate4.length() - 6; i++) {
                            tmonth += stodate4.charAt(i);
                        }
                        for (int i = 5; i < stodate4.length(); i++) {
                            tyears += stodate4.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);



                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            todate4.setText("");
                            todateinput4.setError("Kindly enter valid date");

                        } else {
                            //allowed
                            todateflag1 = 0;
                            todateinput4.setError(null);
                            fromdateinput4.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });

        todate5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                todateinput5.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    sfromdate5 = fromdate5.getText().toString();
                    stodate5 = todate5.getText().toString();
                    if (!stodate5.equals("" ) && !sfromdate5.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate5.length() - 6; i++) {
                            fmonth += sfromdate5.charAt(i);
                        }
                        for (int i = 5; i < sfromdate5.length(); i++) {
                            fyears += sfromdate5.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate5.length() - 6; i++) {
                            tmonth += stodate5.charAt(i);
                        }
                        for (int i = 5; i < stodate5.length(); i++) {
                            tyears += stodate5.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);


                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            todate5.setText("");
                            todateinput5.setError("Kindly enter valid date");

                        } else {
                            //allowed
                            todateflag1 = 0;
                            todateinput5.setError(null);
                            fromdateinput5.setError(null);

                        }
                    }
                } catch(Exception e){
                }

            }
        });

        todate6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                todateinput6.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    sfromdate6 = fromdate6.getText().toString();
                    stodate6 = todate6.getText().toString();
                    if (!stodate6.equals("" ) && !sfromdate6.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate6.length() - 6; i++) {
                            fmonth += sfromdate6.charAt(i);
                        }
                        for (int i = 5; i < sfromdate6.length(); i++) {
                            fyears += sfromdate6.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate6.length() - 6; i++) {
                            tmonth += stodate6.charAt(i);
                        }
                        for (int i = 5; i < stodate6.length(); i++) {
                            tyears += stodate6.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);



                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            todate6.setText("");
                            todateinput6.setError("Kindly enter valid date");

                        } else {
                            //allowed
                            todateflag1 = 0;
                            todateinput6.setError(null);
                            fromdateinput6.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });

        todate7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                todateinput7.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate7 = fromdate7.getText().toString();
                    stodate7 = todate7.getText().toString();
                    if (!stodate7.equals("" ) && !sfromdate7.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate7.length() - 6; i++) {
                            fmonth += sfromdate7.charAt(i);
                        }
                        for (int i = 5; i < sfromdate7.length(); i++) {
                            fyears += sfromdate7.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate7.length() - 6; i++) {
                            tmonth += stodate7.charAt(i);
                        }
                        for (int i = 5; i < stodate7.length(); i++) {
                            tyears += stodate7.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);


                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            todate7.setText("");
                            todateinput7.setError("Kindly enter valid date");

                        } else {
                            //allowed
                            todateflag1 = 0;
                            todateinput7.setError(null);
                            fromdateinput7.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });
        todate8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                todateinput8.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate8 = fromdate8.getText().toString();
                    stodate8 = todate8.getText().toString();
                    if (!stodate8.equals("" ) && !sfromdate8.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate8.length() - 6; i++) {
                            fmonth += sfromdate8.charAt(i);
                        }
                        for (int i = 5; i < sfromdate8.length(); i++) {
                            fyears += sfromdate8.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate8.length() - 6; i++) {
                            tmonth += stodate8.charAt(i);
                        }
                        for (int i = 5; i < stodate8.length(); i++) {
                            tyears += stodate8.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);


                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            todate8.setText("");
                            todateinput8.setError("Kindly enter valid date");

                        } else {
                            //allowed
                            todateflag1 = 0;
                            todateinput8.setError(null);
                            fromdateinput8.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });

        todate9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                todateinput9.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate9 = fromdate9.getText().toString();
                    stodate9 = todate9.getText().toString();
                    if (!stodate9.equals("" ) && !sfromdate9.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate9.length() - 6; i++) {
                            fmonth += sfromdate9.charAt(i);
                        }
                        for (int i = 5; i < sfromdate9.length(); i++) {
                            fyears += sfromdate9.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate9.length() - 6; i++) {
                            tmonth += stodate9.charAt(i);
                        }
                        for (int i = 5; i < stodate9.length(); i++) {
                            tyears += stodate9.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);



                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            todate9.setText("");
                            todateinput9.setError("Kindly enter valid date");

                        } else {
                            //allowed
                            todateflag1 = 0;
                            todateinput9.setError(null);
                            fromdateinput9.setError(null);

                        }
                    }
                } catch(Exception e){
                }
            }
        });

        todate10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                todateinput10.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    sfromdate10 = fromdate10.getText().toString();
                    stodate10 = todate10.getText().toString();
                    if (!stodate10.equals("" ) && !sfromdate10.equals("" )) {

                        String fmonth = "", fyears = "";
                        for (int i = 0; i < sfromdate10.length() - 6; i++) {
                            fmonth += sfromdate10.charAt(i);
                        }
                        for (int i = 5; i < sfromdate10.length(); i++) {
                            fyears += sfromdate10.charAt(i);
                        }
                        String tmonth = "", tyears = "";

                        for (int i = 0; i < stodate10.length() - 6; i++) {
                            tmonth += stodate10.charAt(i);
                        }
                        for (int i = 5; i < stodate10.length(); i++) {
                            tyears += stodate10.charAt(i);
                        }
                        int ty = 0, fy = 0, tm = 0, fm = 0;
                        ty = Integer.parseInt(tyears);
                        fy = Integer.parseInt(fyears);
                        fm = map.get(fmonth);
                        tm = map.get(tmonth);



                        DateTime date2= new DateTime().withDate(fy, fm, 1);
                        DateTime date1 = new DateTime().withDate(ty, tm, 1);

                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();

                        if(exp_in_months2<1){
                            //not allow
                            todateflag1 = 1;
                            todate10.setText("");
                            todateinput10.setError("Kindly enter valid date");

                        } else {
                            //allowed
                            todateflag1 = 0;
                            todateinput10.setError(null);
                            fromdateinput10.setError(null);

                        }

                    }
                } catch(Exception e){
                }
            }
        });

        expsw1.addTextChangedListener(new TextWatcher() {
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
        expsw2.addTextChangedListener(new TextWatcher() {
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
        expsw3.addTextChangedListener(new TextWatcher() {
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
        expsw4.addTextChangedListener(new TextWatcher() {
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
        expsw5.addTextChangedListener(new TextWatcher() {
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
        expsw6.addTextChangedListener(new TextWatcher() {
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
        expsw7.addTextChangedListener(new TextWatcher() {
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
        expsw8.addTextChangedListener(new TextWatcher() {
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
        expsw9.addTextChangedListener(new TextWatcher() {
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
        expsw10.addTextChangedListener(new TextWatcher() {
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
//admore exp
        addmoreexp=(View)findViewById(R.id.addmoreexp);
        addmoreexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editexp=0;
                if (expcount == 0) {

                    if(post1.getText().toString()!=null && inst1.getText().toString()!=null && fromdate1.getText().toString()!=null )
                    {
                        if(!post1.getText().toString().equals("") && !inst1.getText().toString().equals("") && !fromdate1.getText().toString().equals("") )
                        {
                            if(!expsw1.isChecked() && todate1.getText().toString().equals("") ) {
                                Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                            }
                            if (!expsw1.isChecked()) {

                                if (todate1.getText().toString() != null) {

                                    if (!todate1.getText().toString().equals("")) {

                                        View v = (View) findViewById(R.id.expline1);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl2);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View) findViewById(R.id.expline1);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl2);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                            Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();

                }
                else if (expcount == 1) {
                    if(post2.getText().toString()!=null && inst2.getText().toString()!=null && fromdate2.getText().toString()!=null ) {
                        if (!post2.getText().toString().equals("") && !inst2.getText().toString().equals("") && !fromdate2.getText().toString().equals("")) {

                            if (!expsw2.isChecked() && todate2.getText().toString().equals("")) {
                                Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                            }

                            if (!expsw2.isChecked()) {

                                if (todate2.getText().toString() != null) {

                                    if (!todate2.getText().toString().equals("")) {

                                        View v = (View) findViewById(R.id.expline2);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl3);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View) findViewById(R.id.expline2);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl3);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }

                        else
                        {
                            Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();



                }


                else if (expcount == 2) {

                    if(post3.getText().toString()!=null && inst3.getText().toString()!=null && fromdate3.getText().toString()!=null )
                    {
                        if(!post3.getText().toString().equals("") && !inst3.getText().toString().equals("") && !fromdate3.getText().toString().equals("") )
                        {
                            if (!expsw3.isChecked() && todate3.getText().toString().equals("")) {
                                Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                            }

                            if (!expsw3.isChecked()) {
                                if (todate3.getText().toString() != null) {
                                    if (!todate3.getText().toString().equals("")) {
                                        View v = (View) findViewById(R.id.expline3);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl4);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;

                                    }
                                }
                            } else {

                                View v = (View) findViewById(R.id.expline3);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl4);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                            Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();

                }
                else if (expcount == 3) {
                    if(post4.getText().toString()!=null && inst4.getText().toString()!=null && fromdate4.getText().toString()!=null )
                    {
                        if(!post4.getText().toString().equals("") && !inst4.getText().toString().equals("") && !fromdate4.getText().toString().equals("") )
                        {
                            if (!expsw4.isChecked() && todate4.getText().toString().equals("")) {
                                Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                            }

                            if (!expsw4.isChecked()) {

                                if (todate4.getText().toString() != null) {

                                    if (!todate4.getText().toString().equals("")) {

                                        View v = (View) findViewById(R.id.expline4);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl5);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View) findViewById(R.id.expline4);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl5);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }

                        }
                        else
                        {
                            Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();


                }
                else if (expcount == 4) {
                    if(post5.getText().toString()!=null && inst5.getText().toString()!=null && fromdate5.getText().toString()!=null )
                    {
                        if(!post5.getText().toString().equals("") && !inst5.getText().toString().equals("") && !fromdate5.getText().toString().equals("") )
                        {
                            if (!expsw5.isChecked() && todate5.getText().toString().equals("")) {
                                Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                            }

                            if (!expsw5.isChecked()) {

                                if (todate5.getText().toString() != null) {

                                    if (!todate5.getText().toString().equals("")) {
                                        View v = (View) findViewById(R.id.expline5);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl6);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View) findViewById(R.id.expline5);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl6);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                        {
                            Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();

                }

                else if (expcount == 5) {

                    if(post6.getText().toString()!=null && inst6.getText().toString()!=null && fromdate6.getText().toString()!=null )
                    {
                        if(!post6.getText().toString().equals("") && !inst6.getText().toString().equals("") && !fromdate6.getText().toString().equals("") )
                        {
                            if (!expsw6.isChecked() && todate6.getText().toString().equals("")) {
                                Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                            }



                            if (!expsw6.isChecked()) {

                                if (todate6.getText().toString() != null) {

                                    if (!todate6.getText().toString().equals("")) {
                                        View v = (View) findViewById(R.id.expline6);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl7);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View) findViewById(R.id.expline6);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl7);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                        {
                            Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();

                }
                else if (expcount == 6) {

                    if(post7.getText().toString()!=null && inst7.getText().toString()!=null && fromdate7.getText().toString()!=null )
                    {
                        if(!post7.getText().toString().equals("") && !inst7.getText().toString().equals("") && !fromdate7.getText().toString().equals("") )
                        {
                            if (!expsw7.isChecked() && todate7.getText().toString().equals("")) {
                                Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                            }
                            if (!expsw7.isChecked()) {
                                if (todate7.getText().toString() != null) {
                                    if (!todate7.getText().toString().equals("")) {
                                        View v = (View) findViewById(R.id.expline7);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl8);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View) findViewById(R.id.expline7);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl8);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                        {
                            Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();


                } else if (expcount == 7) {

                    if(post8.getText().toString()!=null && inst8.getText().toString()!=null && fromdate8.getText().toString()!=null )
                    {
                        if(!post8.getText().toString().equals("") && !inst8.getText().toString().equals("") && !fromdate8.getText().toString().equals("") )
                        {
                            if (!expsw8.isChecked() && todate8.getText().toString().equals("")) {
                                Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                            }

                            if (!expsw8.isChecked()) {

                                if (todate8.getText().toString() != null) {

                                    if (!todate8.getText().toString().equals("")) {
                                        View v = (View) findViewById(R.id.expline8);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl9);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View) findViewById(R.id.expline8);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.exprl9);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                        {
                            Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();

                }
                else if (expcount == 8) {
                    if(post9.getText().toString()!=null && inst9.getText().toString()!=null && fromdate9.getText().toString()!=null )
                    {
                        if(!post9.getText().toString().equals("") && !inst9.getText().toString().equals("") && !fromdate9.getText().toString().equals("") )
                        {
                            if (!expsw9.isChecked() && todate9.getText().toString().equals("")) {
                                Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                            }

                            if (!expsw9.isChecked()) {

                                if (todate9.getText().toString() != null) {

                                    if (!todate9.getText().toString().equals("")) {
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
                            } else {
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
                        else
                        {
                            Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(AdminExperiences.this, "Please fill the empty experience", Toast.LENGTH_SHORT).show();


                }

            }
        });

        //delete exp
        trash1selectionview=(View)findViewById(R.id.trashexp1);
        trash2selectionview=(View)findViewById(R.id.trashexp2);
        trash3selectionview=(View)findViewById(R.id.trashexp3);
        trash4selectionview=(View)findViewById(R.id.trashexp4);
        trash5selectionview=(View)findViewById(R.id.trashexp5);
        trash6selectionview=(View)findViewById(R.id.trashexp6);
        trash7selectionview=(View)findViewById(R.id.trashexp7);
        trash8selectionview=(View)findViewById(R.id.trashexp8);
        trash9selectionview=(View)findViewById(R.id.trashexp9);
        trash10selectionview=(View)findViewById(R.id.trashexp10);

        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del=1;
                showDeletDialog();

            }
        });
        trash2selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del=2;
                showDeletDialog();

            }
        });
        trash3selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del=3;
                showDeletDialog();
            }
        });
        trash4selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del=4;
                showDeletDialog();
            }
        });
        trash5selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del=5;
                showDeletDialog();
            }
        });
        trash6selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del=6;
                showDeletDialog();
            }
        });
        trash7selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del=7;

                showDeletDialog();
            }
        });
        trash8selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del=8;
                showDeletDialog();
            }
        });
        trash9selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del=9;
                showDeletDialog();
            }
        });
        trash10selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del=10;
                showDeletDialog();
            }
        });



        expsw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    todate1.setText("");
                    todateinput1.setVisibility(View.INVISIBLE);
                    workinghereflag1=1;

                }else{
                    todateinput1.setVisibility(View.VISIBLE);
                    workinghereflag1=0;

                }
            }

        });
        expsw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    todate2.setText("");
                    todateinput2.setVisibility(View.INVISIBLE);
                    workinghereflag2=1;

                }else{
                    todateinput2.setVisibility(View.VISIBLE);
                    workinghereflag2=0;

                }
            }

        });

        expsw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    todate3.setText("");
                    todateinput3.setVisibility(View.INVISIBLE);
                    workinghereflag3=1;

                }else{
                    todateinput3.setVisibility(View.VISIBLE);
                    workinghereflag3=0;

                }
            }

        });
        expsw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    todate4.setText("");
                    todateinput4.setError(null);
                    todateinput4.setVisibility(View.INVISIBLE);
                    workinghereflag4=1;

                }else{
                    todateinput4.setVisibility(View.VISIBLE);
                    workinghereflag4=0;

                }
            }

        });
        expsw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    todate5.setText("");
                    todateinput5.setVisibility(View.INVISIBLE);
                    workinghereflag5=1;

                }else{
                    todateinput5.setVisibility(View.VISIBLE);
                    workinghereflag5=0;

                }
            }

        });

        expsw6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    todate6.setText("");
                    todateinput6.setError(null);
                    todateinput6.setVisibility(View.INVISIBLE);
                    workinghereflag6=1;

                }else{
                    todateinput6.setVisibility(View.VISIBLE);
                    workinghereflag6=0;

                }
            }

        });

        expsw7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    todate7.setText("");
                    todateinput7.setError(null);
                    todateinput7.setVisibility(View.INVISIBLE);
                    workinghereflag7=1;

                }else{
                    todateinput7.setVisibility(View.VISIBLE);
                    workinghereflag7=0;

                }
            }

        });
        expsw8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    todate8.setText("");
                    todateinput8.setError(null);
                    todateinput8.setVisibility(View.INVISIBLE);
                    workinghereflag8=1;

                }else{
                    todateinput8.setVisibility(View.VISIBLE);
                    workinghereflag8=0;

                }
            }

        });
        expsw9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    todate9.setText("");
                    todateinput9.setError(null);
                    todateinput9.setVisibility(View.INVISIBLE);
                    workinghereflag9=1;

                }else{
                    todateinput9.setVisibility(View.VISIBLE);
                    workinghereflag9=0;

                }
            }

        });

        expsw10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    todate10.setText("");
                    todateinput10.setError(null);
                    todateinput10.setVisibility(View.INVISIBLE);
                    workinghereflag10=1;

                }else{
                    todateinput10.setVisibility(View.VISIBLE);
                    workinghereflag10=0;

                }
            }

        });


    }

    //delete
    void deleteExperience() {
        View v = (View)findViewById(R.id.expline9);
        if (v.getVisibility() == View.VISIBLE) {
            View v1=(View)findViewById(R.id.expline9);
            v1.setVisibility(View.GONE);

            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.exprl10);
            relativeLayout1.setVisibility(View.GONE);


            expcount--;

            TextView t=(TextView)findViewById(R.id.addmoreexptxt);
            ImageView i=(ImageView)findViewById(R.id.addmoreexpimg);
            addmoreexp.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        }else
        {
            v = (View) findViewById(R.id.expline8);
            if (v.getVisibility() == View.VISIBLE) {

                View v1=(View)findViewById(R.id.expline8);
                v.setVisibility(View.GONE);

                RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.exprl9);
                relativeLayout1.setVisibility(View.GONE);
                expcount--;

            }
            else  {
                v = (View) findViewById(R.id.expline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1=(View)findViewById(R.id.expline7);
                    v.setVisibility(View.GONE);

                    RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.exprl8);
                    relativeLayout1.setVisibility(View.GONE);
                    expcount--;

                }
                else {
                    v = (View) findViewById(R.id.expline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1=(View)findViewById(R.id.expline6);
                        v.setVisibility(View.GONE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.exprl7);
                        relativeLayout1.setVisibility(View.GONE);
                        expcount--;

                    }
                    else {
                        v = (View) findViewById(R.id.expline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1=(View)findViewById(R.id.expline5);
                            v.setVisibility(View.GONE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.exprl6);
                            relativeLayout1.setVisibility(View.GONE);
                            expcount--;

                        }
                        else {
                            v = (View) findViewById(R.id.expline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1=(View)findViewById(R.id.expline4);
                                v.setVisibility(View.GONE);

                                RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.exprl5);
                                relativeLayout1.setVisibility(View.GONE);
                                expcount--;

                            }
                            else {
                                v = (View) findViewById(R.id.expline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1=(View)findViewById(R.id.expline3);
                                    v.setVisibility(View.GONE);

                                    RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.exprl4);
                                    relativeLayout1.setVisibility(View.GONE);
                                    expcount--;

                                }
                                else {
                                    v = (View) findViewById(R.id.expline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1=(View)findViewById(R.id.expline2);
                                        v.setVisibility(View.GONE);

                                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.exprl3);
                                        relativeLayout1.setVisibility(View.GONE);
                                        expcount--;

                                    }
                                    else {
                                        v = (View) findViewById(R.id.expline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1=(View)findViewById(R.id.expline1);
                                            v.setVisibility(View.GONE);

                                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.exprl2);
                                            relativeLayout1.setVisibility(View.GONE);
                                            expcount--;
                                        }else {
                                            post1.setText("");
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
        if(del==10)
        {
            post10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");
            expsw10.setChecked(false);
        }
        else if(del==9)
        {
            sinst10=inst10.getText().toString();
            spost10=post10.getText().toString();
            sfromdate10=fromdate10.getText().toString();
            stodate10=todate10.getText().toString();

            sinst9=sinst10;
            spost9=spost10;
            sfromdate9=sfromdate10;
            stodate9=stodate10;
            inst9.setText(sinst9);
            post9.setText(spost9);
            fromdate9.setText(sfromdate9);
            if(expsw10.isChecked()==true){
                expsw9.setChecked(true);
                todate9.setText("");
                fromdateinput9.setError(null);
                todateinput9.setError(null);
            }
            else {
                if(expsw9.isChecked()==true) {
                    expsw9.setChecked(false);
                    stodate9=stodate10;
                    todate9.setText(stodate9);

                } else {
                    stodate9=stodate10;
                    todate9.setText(stodate9);
                }
            }
            post10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");
            expsw10.setChecked(false);

        }
        else if(del==8){

            sinst10=inst10.getText().toString();
            spost10=post10.getText().toString();
            sfromdate10=fromdate10.getText().toString();
            stodate10=todate10.getText().toString();
            sinst9=inst9.getText().toString();
            spost9=post9.getText().toString();
            sfromdate9=fromdate9.getText().toString();
            stodate9=todate9.getText().toString();

            sinst8=sinst9;
            spost8=spost9;
            sfromdate8=sfromdate9;
            stodate8=stodate9;

            inst8.setText(sinst8);
            post8.setText(spost8);
            fromdate8.setText(sfromdate8);

            if(expsw9.isChecked()==true){
                expsw8.setChecked(true);
                todate8.setText("");
                fromdateinput8.setError(null);
                todateinput8.setError(null);

            }
            else {
                if(expsw8.isChecked()==true) {
                    expsw8.setChecked(false);
                    stodate8=stodate9;
                    todate8.setText(stodate8);


                } else {
                    stodate8=stodate9;
                    todate8.setText(stodate8);
                }
            }
            post9.setText("");
            inst9.setText("");
            fromdate9.setText("");
            todate9.setText("");
            expsw9.setChecked(false);


            sinst9=sinst10;
            spost9=spost10;
            sfromdate9=sfromdate10;
            stodate9=stodate10;
            inst9.setText(sinst9);
            post9.setText(spost9);
            fromdate9.setText(sfromdate9);
            if(expsw10.isChecked()==true){
                expsw9.setChecked(true);
                todate9.setText("");
                fromdateinput9.setError(null);
                todateinput9.setError(null);
            }
            else {
                if(expsw9.isChecked()==true) {
                    expsw9.setChecked(false);
                    stodate9=stodate10;
                    todate9.setText(stodate9);

                } else {
                    stodate9=stodate10;
                    todate9.setText(stodate9);
                }
            }
            post10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");
            expsw10.setChecked(false);



        }
        else if(del==7){
            sinst10=inst10.getText().toString();
            spost10=post10.getText().toString();
            sfromdate10=fromdate10.getText().toString();
            stodate10=todate10.getText().toString();
            sinst9=inst9.getText().toString();
            spost9=post9.getText().toString();
            sfromdate9=fromdate9.getText().toString();
            stodate9=todate9.getText().toString();

            sinst8=inst8.getText().toString();
            spost8=post8.getText().toString();
            sfromdate8=fromdate8.getText().toString();
            stodate8=todate8.getText().toString();
            sinst7=sinst8;
            spost7=spost8;
            sfromdate7=sfromdate8;
            stodate7=stodate8;
            inst7.setText(sinst7);
            post7.setText(spost7);
            fromdate7.setText(sfromdate7);
//            todate7.setText(stodate7);

            if(expsw8.isChecked()==true){
                expsw7.setChecked(true);
                todate7.setText("");
                fromdateinput7.setError(null);
                todateinput7.setError(null);

            }
            else {
                if(expsw7.isChecked()==true) {
                    expsw7.setChecked(false);
                    stodate7=stodate8;
                    todate7.setText(stodate7);


                } else {
                    stodate7=stodate8;
                    todate7.setText(stodate7);
                }
            }
            post8.setText("");
            inst8.setText("");
            fromdate8.setText("");
            todate8.setText("");
            expsw8.setChecked(false);

            sinst8=sinst9;
            spost8=spost9;
            sfromdate8=sfromdate9;
            stodate8=stodate9;

            inst8.setText(sinst8);
            post8.setText(spost8);
            fromdate8.setText(sfromdate8);

            if(expsw9.isChecked()==true){
                expsw8.setChecked(true);
                todate8.setText("");
                fromdateinput8.setError(null);
                todateinput8.setError(null);

            }
            else {
                if(expsw8.isChecked()==true) {
                    expsw8.setChecked(false);
                    stodate8=stodate9;
                    todate8.setText(stodate8);


                } else {
                    stodate8=stodate9;
                    todate8.setText(stodate8);
                }
            }
            post9.setText("");
            inst9.setText("");
            fromdate9.setText("");
            todate9.setText("");
            expsw9.setChecked(false);


            sinst9=sinst10;
            spost9=spost10;
            sfromdate9=sfromdate10;
            stodate9=stodate10;
            inst9.setText(sinst9);
            post9.setText(spost9);
            fromdate9.setText(sfromdate9);
            if(expsw10.isChecked()==true){
                expsw9.setChecked(true);
                todate9.setText("");
                fromdateinput9.setError(null);
                todateinput9.setError(null);
            }
            else {
                if(expsw9.isChecked()==true) {
                    expsw9.setChecked(false);
                    stodate9=stodate10;
                    todate9.setText(stodate9);

                } else {
                    stodate9=stodate10;
                    todate9.setText(stodate9);
                }
            }
            post10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");
            expsw10.setChecked(false);


        }
        else if(del==6){
            sinst10=inst10.getText().toString();
            spost10=post10.getText().toString();
            sfromdate10=fromdate10.getText().toString();
            stodate10=todate10.getText().toString();
            sinst9=inst9.getText().toString();
            spost9=post9.getText().toString();
            sfromdate9=fromdate9.getText().toString();
            stodate9=todate9.getText().toString();
            sinst8=inst8.getText().toString();
            spost8=post8.getText().toString();
            sfromdate8=fromdate8.getText().toString();
            stodate8=todate8.getText().toString();

            sinst7=inst7.getText().toString();
            spost7=post7.getText().toString();
            sfromdate7=fromdate7.getText().toString();
            stodate7=todate7.getText().toString();


            sinst6=sinst7;
            spost6=spost7;
            sfromdate6=sfromdate7;
            stodate6=stodate7;

            inst6.setText(sinst6);
            post6.setText(spost6);
            fromdate6.setText(sfromdate6);
//            todate6.setText(stodate6);
            if(expsw7.isChecked()==true){
                expsw6.setChecked(true);
                todate6.setText("");
                fromdateinput6.setError(null);
                todateinput6.setError(null);

            }
            else {
                if(expsw6.isChecked()==true) {
                    expsw6.setChecked(false);
                    stodate6=stodate7;
                    todate6.setText(stodate6);


                } else {
                    stodate6=stodate7;
                    todate6.setText(stodate6);
                }
            }
            post7.setText("");
            inst7.setText("");
            fromdate7.setText("");
            todate7.setText("");
            expsw7.setChecked(false);

            sinst7=sinst8;
            spost7=spost8;
            sfromdate7=sfromdate8;
            stodate7=stodate8;
            inst7.setText(sinst7);
            post7.setText(spost7);
            fromdate7.setText(sfromdate7);
//            todate7.setText(stodate7);

            if(expsw8.isChecked()==true){
                expsw7.setChecked(true);
                todate7.setText("");
                fromdateinput7.setError(null);
                todateinput7.setError(null);

            }
            else {
                if(expsw7.isChecked()==true) {
                    expsw7.setChecked(false);
                    stodate7=stodate8;
                    todate7.setText(stodate7);


                } else {
                    stodate7=stodate8;
                    todate7.setText(stodate7);
                }
            }
            post8.setText("");
            inst8.setText("");
            fromdate8.setText("");
            todate8.setText("");
            expsw8.setChecked(false);

            sinst8=sinst9;
            spost8=spost9;
            sfromdate8=sfromdate9;
            stodate8=stodate9;

            inst8.setText(sinst8);
            post8.setText(spost8);
            fromdate8.setText(sfromdate8);

            if(expsw9.isChecked()==true){
                expsw8.setChecked(true);
                todate8.setText("");
                fromdateinput8.setError(null);
                todateinput8.setError(null);

            }
            else {
                if(expsw8.isChecked()==true) {
                    expsw8.setChecked(false);
                    stodate8=stodate9;
                    todate8.setText(stodate8);


                } else {
                    stodate8=stodate9;
                    todate8.setText(stodate8);
                }
            }
            post9.setText("");
            inst9.setText("");
            fromdate9.setText("");
            todate9.setText("");
            expsw9.setChecked(false);


            sinst9=sinst10;
            spost9=spost10;
            sfromdate9=sfromdate10;
            stodate9=stodate10;
            inst9.setText(sinst9);
            post9.setText(spost9);
            fromdate9.setText(sfromdate9);
            if(expsw10.isChecked()==true){
                expsw9.setChecked(true);
                todate9.setText("");
                fromdateinput9.setError(null);
                todateinput9.setError(null);
            }
            else {
                if(expsw9.isChecked()==true) {
                    expsw9.setChecked(false);
                    stodate9=stodate10;
                    todate9.setText(stodate9);

                } else {
                    stodate9=stodate10;
                    todate9.setText(stodate9);
                }
            }
            post10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");
            expsw10.setChecked(false);



        }
        else if(del==5){
            sinst10=inst10.getText().toString();
            spost10=post10.getText().toString();
            sfromdate10=fromdate10.getText().toString();
            stodate10=todate10.getText().toString();
            sinst9=inst9.getText().toString();
            spost9=post9.getText().toString();
            sfromdate9=fromdate9.getText().toString();
            stodate9=todate9.getText().toString();
            sinst8=inst8.getText().toString();
            spost8=post8.getText().toString();
            sfromdate8=fromdate8.getText().toString();
            stodate8=todate8.getText().toString();
            sinst7=inst7.getText().toString();
            spost7=post7.getText().toString();
            sfromdate7=fromdate7.getText().toString();
            stodate7=todate7.getText().toString();

            sinst6=inst6.getText().toString();
            spost6=post6.getText().toString();
            sfromdate6=fromdate6.getText().toString();
            stodate6=todate6.getText().toString();



            sinst5=sinst6;
            spost5=spost6;
            sfromdate5=sfromdate6;
            stodate5=stodate6;

            inst5.setText(sinst5);
            post5.setText(spost5);
            fromdate5.setText(sfromdate5);
//            todate5.setText(stodate5);
            if(expsw6.isChecked()==true){
                expsw5.setChecked(true);
                todate5.setText("");
                fromdateinput5.setError(null);
                todateinput5.setError(null);

            }
            else {
                if(expsw5.isChecked()==true) {
                    expsw5.setChecked(false);
                    stodate5=stodate6;
                    todate5.setText(stodate5);


                } else {
                    stodate5=stodate6;
                    todate5.setText(stodate5);
                }
            }
            post6.setText("");
            inst6.setText("");
            fromdate6.setText("");
            todate6.setText("");
            expsw6.setChecked(false);


            sinst6=sinst7;
            spost6=spost7;
            sfromdate6=sfromdate7;
            stodate6=stodate7;

            inst6.setText(sinst6);
            post6.setText(spost6);
            fromdate6.setText(sfromdate6);
//            todate6.setText(stodate6);
            if(expsw7.isChecked()==true){
                expsw6.setChecked(true);
                todate6.setText("");
                fromdateinput6.setError(null);
                todateinput6.setError(null);

            }
            else {
                if(expsw6.isChecked()==true) {
                    expsw6.setChecked(false);
                    stodate6=stodate7;
                    todate6.setText(stodate6);


                } else {
                    stodate6=stodate7;
                    todate6.setText(stodate6);
                }
            }
            post7.setText("");
            inst7.setText("");
            fromdate7.setText("");
            todate7.setText("");
            expsw7.setChecked(false);

            sinst7=sinst8;
            spost7=spost8;
            sfromdate7=sfromdate8;
            stodate7=stodate8;
            inst7.setText(sinst7);
            post7.setText(spost7);
            fromdate7.setText(sfromdate7);
//            todate7.setText(stodate7);

            if(expsw8.isChecked()==true){
                expsw7.setChecked(true);
                todate7.setText("");
                fromdateinput7.setError(null);
                todateinput7.setError(null);

            }
            else {
                if(expsw7.isChecked()==true) {
                    expsw7.setChecked(false);
                    stodate7=stodate8;
                    todate7.setText(stodate7);


                } else {
                    stodate7=stodate8;
                    todate7.setText(stodate7);
                }
            }
            post8.setText("");
            inst8.setText("");
            fromdate8.setText("");
            todate8.setText("");
            expsw8.setChecked(false);

            sinst8=sinst9;
            spost8=spost9;
            sfromdate8=sfromdate9;
            stodate8=stodate9;

            inst8.setText(sinst8);
            post8.setText(spost8);
            fromdate8.setText(sfromdate8);

            if(expsw9.isChecked()==true){
                expsw8.setChecked(true);
                todate8.setText("");
                fromdateinput8.setError(null);
                todateinput8.setError(null);

            }
            else {
                if(expsw8.isChecked()==true) {
                    expsw8.setChecked(false);
                    stodate8=stodate9;
                    todate8.setText(stodate8);


                } else {
                    stodate8=stodate9;
                    todate8.setText(stodate8);
                }
            }
            post9.setText("");
            inst9.setText("");
            fromdate9.setText("");
            todate9.setText("");
            expsw9.setChecked(false);


            sinst9=sinst10;
            spost9=spost10;
            sfromdate9=sfromdate10;
            stodate9=stodate10;
            inst9.setText(sinst9);
            post9.setText(spost9);
            fromdate9.setText(sfromdate9);
            if(expsw10.isChecked()==true){
                expsw9.setChecked(true);
                todate9.setText("");
                fromdateinput9.setError(null);
                todateinput9.setError(null);
            }
            else {
                if(expsw9.isChecked()==true) {
                    expsw9.setChecked(false);
                    stodate9=stodate10;
                    todate9.setText(stodate9);

                } else {
                    stodate9=stodate10;
                    todate9.setText(stodate9);
                }
            }
            post10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");
            expsw10.setChecked(false);





        }
        else if(del==4){
            sinst10=inst10.getText().toString();
            spost10=post10.getText().toString();
            sfromdate10=fromdate10.getText().toString();
            stodate10=todate10.getText().toString();
            sinst9=inst9.getText().toString();
            spost9=post9.getText().toString();
            sfromdate9=fromdate9.getText().toString();
            stodate9=todate9.getText().toString();
            sinst8=inst8.getText().toString();
            spost8=post8.getText().toString();
            sfromdate8=fromdate8.getText().toString();
            stodate8=todate8.getText().toString();
            sinst7=inst7.getText().toString();
            spost7=post7.getText().toString();
            sfromdate7=fromdate7.getText().toString();
            stodate7=todate7.getText().toString();
            sinst6=inst6.getText().toString();
            spost6=post6.getText().toString();
            sfromdate6=fromdate6.getText().toString();
            stodate6=todate6.getText().toString();
            sinst5=inst5.getText().toString();
            spost5=post5.getText().toString();
            sfromdate5=fromdate5.getText().toString();
            stodate5=todate5.getText().toString();

            sinst4=sinst5;
            spost4=spost5;
            sfromdate4=sfromdate5;
            stodate4=stodate5;
            inst4.setText(sinst4);
            post4.setText(spost4);
            fromdate4.setText(sfromdate4);
            if(expsw5.isChecked()==true){
                expsw4.setChecked(true);
                todate4.setText("");
                fromdateinput4.setError(null);
                todateinput4.setError(null);

            }
            else {
                if(expsw4.isChecked()==true) {
                    expsw4.setChecked(false);
                    stodate4=stodate5;
                    todate4.setText(stodate4);


                } else {
                    stodate4=stodate5;
                    todate4.setText(stodate4);
                }
            }
            post5.setText("");
            inst5.setText("");
            fromdate5.setText("");
            todate5.setText("");
            expsw5.setChecked(false);


            sinst5=sinst6;
            spost5=spost6;
            sfromdate5=sfromdate6;
            stodate5=stodate6;
            inst5.setText(sinst5);
            post5.setText(spost5);
            fromdate5.setText(sfromdate5);

            if(expsw6.isChecked()==true){
                expsw5.setChecked(true);
                todate5.setText("");
                fromdateinput5.setError(null);
                todateinput5.setError(null);
            }
            else {
                if(expsw5.isChecked()==true) {
                    expsw5.setChecked(false);
                    stodate5=stodate6;
                    todate5.setText(stodate5);
                } else {
                    stodate5=stodate6;
                    todate5.setText(stodate5);
                }
            }
            post6.setText("");
            inst6.setText("");
            fromdate6.setText("");
            todate6.setText("");
            expsw6.setChecked(false);

            sinst6=sinst7;
            spost6=spost7;
            sfromdate6=sfromdate7;
            stodate6=stodate7;
            inst6.setText(sinst6);
            post6.setText(spost6);
            fromdate6.setText(sfromdate6);
//            todate6.setText(stodate6);
            if(expsw7.isChecked()==true){
                expsw6.setChecked(true);
                todate6.setText("");
                fromdateinput6.setError(null);
                todateinput6.setError(null);

            }
            else {
                if(expsw6.isChecked()==true) {
                    expsw6.setChecked(false);
                    stodate6=stodate7;
                    todate6.setText(stodate6);


                } else {
                    stodate6=stodate7;
                    todate6.setText(stodate6);
                }
            }
            post7.setText("");
            inst7.setText("");
            fromdate7.setText("");
            todate7.setText("");
            expsw7.setChecked(false);

            sinst7=sinst8;
            spost7=spost8;
            sfromdate7=sfromdate8;
            stodate7=stodate8;
            inst7.setText(sinst7);
            post7.setText(spost7);
            fromdate7.setText(sfromdate7);
//            todate7.setText(stodate7);

            if(expsw8.isChecked()==true){
                expsw7.setChecked(true);
                todate7.setText("");
                fromdateinput7.setError(null);
                todateinput7.setError(null);

            }
            else {
                if(expsw7.isChecked()==true) {
                    expsw7.setChecked(false);
                    stodate7=stodate8;
                    todate7.setText(stodate7);


                } else {
                    stodate7=stodate8;
                    todate7.setText(stodate7);
                }
            }
            post8.setText("");
            inst8.setText("");
            fromdate8.setText("");
            todate8.setText("");
            expsw8.setChecked(false);

            sinst8=sinst9;
            spost8=spost9;
            sfromdate8=sfromdate9;
            stodate8=stodate9;

            inst8.setText(sinst8);
            post8.setText(spost8);
            fromdate8.setText(sfromdate8);

            if(expsw9.isChecked()==true){
                expsw8.setChecked(true);
                todate8.setText("");
                fromdateinput8.setError(null);
                todateinput8.setError(null);

            }
            else {
                if(expsw8.isChecked()==true) {
                    expsw8.setChecked(false);
                    stodate8=stodate9;
                    todate8.setText(stodate8);


                } else {
                    stodate8=stodate9;
                    todate8.setText(stodate8);
                }
            }
            post9.setText("");
            inst9.setText("");
            fromdate9.setText("");
            todate9.setText("");
            expsw9.setChecked(false);


            sinst9=sinst10;
            spost9=spost10;
            sfromdate9=sfromdate10;
            stodate9=stodate10;
            inst9.setText(sinst9);
            post9.setText(spost9);
            fromdate9.setText(sfromdate9);
            if(expsw10.isChecked()==true){
                expsw9.setChecked(true);
                todate9.setText("");
                fromdateinput9.setError(null);
                todateinput9.setError(null);
            }
            else {
                if(expsw9.isChecked()==true) {
                    expsw9.setChecked(false);
                    stodate9=stodate10;
                    todate9.setText(stodate9);

                } else {
                    stodate9=stodate10;
                    todate9.setText(stodate9);
                }
            }
            post10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");
            expsw10.setChecked(false);





        }
        else if(del==3){
            sinst10=inst10.getText().toString();
            spost10=post10.getText().toString();
            sfromdate10=fromdate10.getText().toString();
            stodate10=todate10.getText().toString();
            sinst9=inst9.getText().toString();
            spost9=post9.getText().toString();
            sfromdate9=fromdate9.getText().toString();
            stodate9=todate9.getText().toString();
            sinst8=inst8.getText().toString();
            spost8=post8.getText().toString();
            sfromdate8=fromdate8.getText().toString();
            stodate8=todate8.getText().toString();
            sinst7=inst7.getText().toString();
            spost7=post7.getText().toString();
            sfromdate7=fromdate7.getText().toString();
            stodate7=todate7.getText().toString();
            sinst6=inst6.getText().toString();
            spost6=post6.getText().toString();
            sfromdate6=fromdate6.getText().toString();
            stodate6=todate6.getText().toString();
            sinst5=inst5.getText().toString();
            spost5=post5.getText().toString();
            sfromdate5=fromdate5.getText().toString();
            stodate5=todate5.getText().toString();
            sinst4=inst4.getText().toString();
            spost4=post4.getText().toString();
            sfromdate4=fromdate4.getText().toString();
            stodate4=todate4.getText().toString();


            sinst3=sinst4;
            spost3=spost4;
            sfromdate3=sfromdate4;
            stodate3=stodate4;

            inst3.setText(sinst3);
            post3.setText(spost3);
            fromdate3.setText(sfromdate3);
            if(expsw4.isChecked()==true){
                expsw3.setChecked(true);
                todate3.setText("");
                fromdateinput3.setError(null);
                todateinput3.setError(null);

            }
            else {
                if(expsw3.isChecked()==true) {
                    expsw3.setChecked(false);
                    stodate3=stodate4;
                    todate3.setText(stodate3);


                } else {
                    stodate3=stodate4;
                    todate3.setText(stodate3);
                }
            }
            post4.setText("");
            inst4.setText("");
            fromdate4.setText("");
            todate4.setText("");
            expsw4.setChecked(false);

            sinst4=sinst5;
            spost4=spost5;
            sfromdate4=sfromdate5;
            stodate4=stodate5;
            inst4.setText(sinst4);
            post4.setText(spost4);
            fromdate4.setText(sfromdate4);
            if(expsw5.isChecked()==true){
                expsw4.setChecked(true);
                todate4.setText("");
                fromdateinput4.setError(null);
                todateinput4.setError(null);

            }
            else {
                if(expsw4.isChecked()==true) {
                    expsw4.setChecked(false);
                    stodate4=stodate5;
                    todate4.setText(stodate4);


                } else {
                    stodate4=stodate5;
                    todate4.setText(stodate4);
                }
            }
            post5.setText("");
            inst5.setText("");
            fromdate5.setText("");
            todate5.setText("");
            expsw5.setChecked(false);


            sinst5=sinst6;
            spost5=spost6;
            sfromdate5=sfromdate6;
            stodate5=stodate6;
            inst5.setText(sinst5);
            post5.setText(spost5);
            fromdate5.setText(sfromdate5);

            if(expsw6.isChecked()==true){
                expsw5.setChecked(true);
                todate5.setText("");
                fromdateinput5.setError(null);
                todateinput5.setError(null);
            }
            else {
                if(expsw5.isChecked()==true) {
                    expsw5.setChecked(false);
                    stodate5=stodate6;
                    todate5.setText(stodate5);
                } else {
                    stodate5=stodate6;
                    todate5.setText(stodate5);
                }
            }
            post6.setText("");
            inst6.setText("");
            fromdate6.setText("");
            todate6.setText("");
            expsw6.setChecked(false);

            sinst6=sinst7;
            spost6=spost7;
            sfromdate6=sfromdate7;
            stodate6=stodate7;
            inst6.setText(sinst6);
            post6.setText(spost6);
            fromdate6.setText(sfromdate6);
//            todate6.setText(stodate6);
            if(expsw7.isChecked()==true){
                expsw6.setChecked(true);
                todate6.setText("");
                fromdateinput6.setError(null);
                todateinput6.setError(null);

            }
            else {
                if(expsw6.isChecked()==true) {
                    expsw6.setChecked(false);
                    stodate6=stodate7;
                    todate6.setText(stodate6);


                } else {
                    stodate6=stodate7;
                    todate6.setText(stodate6);
                }
            }
            post7.setText("");
            inst7.setText("");
            fromdate7.setText("");
            todate7.setText("");
            expsw7.setChecked(false);

            sinst7=sinst8;
            spost7=spost8;
            sfromdate7=sfromdate8;
            stodate7=stodate8;
            inst7.setText(sinst7);
            post7.setText(spost7);
            fromdate7.setText(sfromdate7);
//            todate7.setText(stodate7);

            if(expsw8.isChecked()==true){
                expsw7.setChecked(true);
                todate7.setText("");
                fromdateinput7.setError(null);
                todateinput7.setError(null);

            }
            else {
                if(expsw7.isChecked()==true) {
                    expsw7.setChecked(false);
                    stodate7=stodate8;
                    todate7.setText(stodate7);


                } else {
                    stodate7=stodate8;
                    todate7.setText(stodate7);
                }
            }
            post8.setText("");
            inst8.setText("");
            fromdate8.setText("");
            todate8.setText("");
            expsw8.setChecked(false);

            sinst8=sinst9;
            spost8=spost9;
            sfromdate8=sfromdate9;
            stodate8=stodate9;

            inst8.setText(sinst8);
            post8.setText(spost8);
            fromdate8.setText(sfromdate8);

            if(expsw9.isChecked()==true){
                expsw8.setChecked(true);
                todate8.setText("");
                fromdateinput8.setError(null);
                todateinput8.setError(null);

            }
            else {
                if(expsw8.isChecked()==true) {
                    expsw8.setChecked(false);
                    stodate8=stodate9;
                    todate8.setText(stodate8);


                } else {
                    stodate8=stodate9;
                    todate8.setText(stodate8);
                }
            }
            post9.setText("");
            inst9.setText("");
            fromdate9.setText("");
            todate9.setText("");
            expsw9.setChecked(false);


            sinst9=sinst10;
            spost9=spost10;
            sfromdate9=sfromdate10;
            stodate9=stodate10;
            inst9.setText(sinst9);
            post9.setText(spost9);
            fromdate9.setText(sfromdate9);
            if(expsw10.isChecked()==true){
                expsw9.setChecked(true);
                todate9.setText("");
                fromdateinput9.setError(null);
                todateinput9.setError(null);
            }
            else {
                if(expsw9.isChecked()==true) {
                    expsw9.setChecked(false);
                    stodate9=stodate10;
                    todate9.setText(stodate9);

                } else {
                    stodate9=stodate10;
                    todate9.setText(stodate9);
                }
            }
            post10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");
            expsw10.setChecked(false);


        }
        else if(del==2){
            sinst10=inst10.getText().toString();
            spost10=post10.getText().toString();
            sfromdate10=fromdate10.getText().toString();
            stodate10=todate10.getText().toString();
            sinst9=inst9.getText().toString();
            spost9=post9.getText().toString();
            sfromdate9=fromdate9.getText().toString();
            stodate9=todate9.getText().toString();
            sinst8=inst8.getText().toString();
            spost8=post8.getText().toString();
            sfromdate8=fromdate8.getText().toString();
            stodate8=todate8.getText().toString();
            sinst7=inst7.getText().toString();
            spost7=post7.getText().toString();
            sfromdate7=fromdate7.getText().toString();
            stodate7=todate7.getText().toString();
            sinst6=inst6.getText().toString();
            spost6=post6.getText().toString();
            sfromdate6=fromdate6.getText().toString();
            stodate6=todate6.getText().toString();
            sinst5=inst5.getText().toString();
            spost5=post5.getText().toString();
            sfromdate5=fromdate5.getText().toString();
            stodate5=todate5.getText().toString();
            sinst4=inst4.getText().toString();
            spost4=post4.getText().toString();
            sfromdate4=fromdate4.getText().toString();
            stodate4=todate4.getText().toString();
            sinst3=inst3.getText().toString();
            spost3=post3.getText().toString();
            sfromdate3=fromdate3.getText().toString();
            stodate3=todate3.getText().toString();

            sinst2=sinst3;
            spost2=spost3;
            sfromdate2=sfromdate3;
            stodate2=stodate3;

            inst2.setText(sinst2);
            post2.setText(spost2);
            fromdate2.setText(sfromdate2);
            if(expsw3.isChecked()==true){
                expsw2.setChecked(true);
                todate2.setText("");
                fromdateinput2.setError(null);
                todateinput2.setError(null);

            }
            else {
                if(expsw2.isChecked()==true) {
                    expsw2.setChecked(false);
                    stodate2=stodate3;
                    todate2.setText(stodate2);


                } else {
                    stodate2=stodate3;
                    todate2.setText(stodate2);
                }
            }
            post3.setText("");
            inst3.setText("");
            fromdate3.setText("");
            todate3.setText("");
            expsw3.setChecked(false);

            sinst3=sinst4;
            spost3=spost4;
            sfromdate3=sfromdate4;
            stodate3=stodate4;

            inst3.setText(sinst3);
            post3.setText(spost3);
            fromdate3.setText(sfromdate3);
            if(expsw4.isChecked()==true){
                expsw3.setChecked(true);
                todate3.setText("");
                fromdateinput3.setError(null);
                todateinput3.setError(null);

            }
            else {
                if(expsw3.isChecked()==true) {
                    expsw3.setChecked(false);
                    stodate3=stodate4;
                    todate3.setText(stodate3);


                } else {
                    stodate3=stodate4;
                    todate3.setText(stodate3);
                }
            }
            post4.setText("");
            inst4.setText("");
            fromdate4.setText("");
            todate4.setText("");
            expsw4.setChecked(false);

            sinst4=sinst5;
            spost4=spost5;
            sfromdate4=sfromdate5;
            stodate4=stodate5;
            inst4.setText(sinst4);
            post4.setText(spost4);
            fromdate4.setText(sfromdate4);
            if(expsw5.isChecked()==true){
                expsw4.setChecked(true);
                todate4.setText("");
                fromdateinput4.setError(null);
                todateinput4.setError(null);

            }
            else {
                if(expsw4.isChecked()==true) {
                    expsw4.setChecked(false);
                    stodate4=stodate5;
                    todate4.setText(stodate4);


                } else {
                    stodate4=stodate5;
                    todate4.setText(stodate4);
                }
            }
            post5.setText("");
            inst5.setText("");
            fromdate5.setText("");
            todate5.setText("");
            expsw5.setChecked(false);


            sinst5=sinst6;
            spost5=spost6;
            sfromdate5=sfromdate6;
            stodate5=stodate6;
            inst5.setText(sinst5);
            post5.setText(spost5);
            fromdate5.setText(sfromdate5);

            if(expsw6.isChecked()==true){
                expsw5.setChecked(true);
                todate5.setText("");
                fromdateinput5.setError(null);
                todateinput5.setError(null);
            }
            else {
                if(expsw5.isChecked()==true) {
                    expsw5.setChecked(false);
                    stodate5=stodate6;
                    todate5.setText(stodate5);
                } else {
                    stodate5=stodate6;
                    todate5.setText(stodate5);
                }
            }
            post6.setText("");
            inst6.setText("");
            fromdate6.setText("");
            todate6.setText("");
            expsw6.setChecked(false);

            sinst6=sinst7;
            spost6=spost7;
            sfromdate6=sfromdate7;
            stodate6=stodate7;
            inst6.setText(sinst6);
            post6.setText(spost6);
            fromdate6.setText(sfromdate6);
//            todate6.setText(stodate6);
            if(expsw7.isChecked()==true){
                expsw6.setChecked(true);
                todate6.setText("");
                fromdateinput6.setError(null);
                todateinput6.setError(null);

            }
            else {
                if(expsw6.isChecked()==true) {
                    expsw6.setChecked(false);
                    stodate6=stodate7;
                    todate6.setText(stodate6);


                } else {
                    stodate6=stodate7;
                    todate6.setText(stodate6);
                }
            }
            post7.setText("");
            inst7.setText("");
            fromdate7.setText("");
            todate7.setText("");
            expsw7.setChecked(false);

            sinst7=sinst8;
            spost7=spost8;
            sfromdate7=sfromdate8;
            stodate7=stodate8;
            inst7.setText(sinst7);
            post7.setText(spost7);
            fromdate7.setText(sfromdate7);
//            todate7.setText(stodate7);

            if(expsw8.isChecked()==true){
                expsw7.setChecked(true);
                todate7.setText("");
                fromdateinput7.setError(null);
                todateinput7.setError(null);

            }
            else {
                if(expsw7.isChecked()==true) {
                    expsw7.setChecked(false);
                    stodate7=stodate8;
                    todate7.setText(stodate7);


                } else {
                    stodate7=stodate8;
                    todate7.setText(stodate7);
                }
            }
            post8.setText("");
            inst8.setText("");
            fromdate8.setText("");
            todate8.setText("");
            expsw8.setChecked(false);


            sinst8=sinst9;
            spost8=spost9;
            sfromdate8=sfromdate9;
            stodate8=stodate9;

            inst8.setText(sinst8);
            post8.setText(spost8);
            fromdate8.setText(sfromdate8);

            if(expsw9.isChecked()==true){
                expsw8.setChecked(true);
                todate8.setText("");
                fromdateinput8.setError(null);
                todateinput8.setError(null);

            }
            else {
                if(expsw8.isChecked()==true) {
                    expsw8.setChecked(false);
                    stodate8=stodate9;
                    todate8.setText(stodate8);


                } else {
                    stodate8=stodate9;
                    todate8.setText(stodate8);
                }
            }
            post9.setText("");
            inst9.setText("");
            fromdate9.setText("");
            todate9.setText("");
            expsw9.setChecked(false);


            sinst9=sinst10;
            spost9=spost10;
            sfromdate9=sfromdate10;
            stodate9=stodate10;
            inst9.setText(sinst9);
            post9.setText(spost9);
            fromdate9.setText(sfromdate9);
            if(expsw10.isChecked()==true){
                expsw9.setChecked(true);
                todate9.setText("");
                fromdateinput9.setError(null);
                todateinput9.setError(null);
            }
            else {
                if(expsw9.isChecked()==true) {
                    expsw9.setChecked(false);
                    stodate9=stodate10;
                    todate9.setText(stodate9);

                } else {
                    stodate9=stodate10;
                    todate9.setText(stodate9);
                }
            }
            post10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");
            expsw10.setChecked(false);




        }
        else if(del==1){
            sinst10=inst10.getText().toString();
            spost10=post10.getText().toString();
            sfromdate10=fromdate10.getText().toString();
            stodate10=todate10.getText().toString();
            sinst9=inst9.getText().toString();
            spost9=post9.getText().toString();
            sfromdate9=fromdate9.getText().toString();
            stodate9=todate9.getText().toString();
            sinst8=inst8.getText().toString();
            spost8=post8.getText().toString();
            sfromdate8=fromdate8.getText().toString();
            stodate8=todate8.getText().toString();
            sinst7=inst7.getText().toString();
            spost7=post7.getText().toString();
            sfromdate7=fromdate7.getText().toString();
            stodate7=todate7.getText().toString();
            sinst6=inst6.getText().toString();
            spost6=post6.getText().toString();
            sfromdate6=fromdate6.getText().toString();
            stodate6=todate6.getText().toString();
            sinst5=inst5.getText().toString();
            spost5=post5.getText().toString();
            sfromdate5=fromdate5.getText().toString();
            stodate5=todate5.getText().toString();
            sinst4=inst4.getText().toString();
            spost4=post4.getText().toString();
            sfromdate4=fromdate4.getText().toString();
            stodate4=todate4.getText().toString();
            sinst3=inst3.getText().toString();
            spost3=post3.getText().toString();
            sfromdate3=fromdate3.getText().toString();
            stodate3=todate3.getText().toString();

            sinst2=inst2.getText().toString();
            spost2=post2.getText().toString();
            sfromdate2=fromdate2.getText().toString();
            stodate2=todate2.getText().toString();


            sinst1=sinst2;
            spost1=spost2;
            sfromdate1=sfromdate2;
            stodate1=stodate2;

            inst1.setText(sinst1);
            post1.setText(spost1);
            fromdate1.setText(sfromdate1);

            if(expsw2.isChecked()==true){
                expsw1.setChecked(true);
                todate1.setText("");
                fromdateinput1.setError(null);
                todateinput1.setError(null);

            }
            else {
                if(expsw1.isChecked()==true) {
                    expsw1.setChecked(false);
                    stodate1=stodate2;
                    todate1.setText(stodate1);


                } else {
                    stodate1=stodate2;
                    todate1.setText(stodate1);
                }
            }
            post2.setText("");
            inst2.setText("");
            fromdate2.setText("");
            todate2.setText("");
            expsw2.setChecked(false);

            sinst2=sinst3;
            spost2=spost3;
            sfromdate2=sfromdate3;
            stodate2=stodate3;

            inst2.setText(sinst2);
            post2.setText(spost2);
            fromdate2.setText(sfromdate2);
            if(expsw3.isChecked()==true){
                expsw2.setChecked(true);
                todate2.setText("");
                fromdateinput2.setError(null);
                todateinput2.setError(null);

            }
            else {
                if(expsw2.isChecked()==true) {
                    expsw2.setChecked(false);
                    stodate2=stodate3;
                    todate2.setText(stodate2);


                } else {
                    stodate2=stodate3;
                    todate2.setText(stodate2);
                }
            }
            post3.setText("");
            inst3.setText("");
            fromdate3.setText("");
            todate3.setText("");
            expsw3.setChecked(false);

            sinst3=sinst4;
            spost3=spost4;
            sfromdate3=sfromdate4;
            stodate3=stodate4;

            inst3.setText(sinst3);
            post3.setText(spost3);
            fromdate3.setText(sfromdate3);
            if(expsw4.isChecked()==true){
                expsw3.setChecked(true);
                todate3.setText("");
                fromdateinput3.setError(null);
                todateinput3.setError(null);

            }
            else {
                if(expsw3.isChecked()==true) {
                    expsw3.setChecked(false);
                    stodate3=stodate4;
                    todate3.setText(stodate3);


                } else {
                    stodate3=stodate4;
                    todate3.setText(stodate3);
                }
            }
            post4.setText("");
            inst4.setText("");
            fromdate4.setText("");
            todate4.setText("");
            expsw4.setChecked(false);

            sinst4=sinst5;
            spost4=spost5;
            sfromdate4=sfromdate5;
            stodate4=stodate5;
            inst4.setText(sinst4);
            post4.setText(spost4);
            fromdate4.setText(sfromdate4);
            if(expsw5.isChecked()==true){
                expsw4.setChecked(true);
                todate4.setText("");
                fromdateinput4.setError(null);
                todateinput4.setError(null);

            }
            else {
                if(expsw4.isChecked()==true) {
                    expsw4.setChecked(false);
                    stodate4=stodate5;
                    todate4.setText(stodate4);


                } else {
                    stodate4=stodate5;
                    todate4.setText(stodate4);
                }
            }
            post5.setText("");
            inst5.setText("");
            fromdate5.setText("");
            todate5.setText("");
            expsw5.setChecked(false);


            sinst5=sinst6;
            spost5=spost6;
            sfromdate5=sfromdate6;
            stodate5=stodate6;
            inst5.setText(sinst5);
            post5.setText(spost5);
            fromdate5.setText(sfromdate5);

            if(expsw6.isChecked()==true){
                expsw5.setChecked(true);
                todate5.setText("");
                fromdateinput5.setError(null);
                todateinput5.setError(null);
            }
            else {
                if(expsw5.isChecked()==true) {
                    expsw5.setChecked(false);
                    stodate5=stodate6;
                    todate5.setText(stodate5);
                } else {
                    stodate5=stodate6;
                    todate5.setText(stodate5);
                }
            }
            post6.setText("");
            inst6.setText("");
            fromdate6.setText("");
            todate6.setText("");
            expsw6.setChecked(false);

            sinst6=sinst7;
            spost6=spost7;
            sfromdate6=sfromdate7;
            stodate6=stodate7;
            inst6.setText(sinst6);
            post6.setText(spost6);
            fromdate6.setText(sfromdate6);
//            todate6.setText(stodate6);
            if(expsw7.isChecked()==true){
                expsw6.setChecked(true);
                todate6.setText("");
                fromdateinput6.setError(null);
                todateinput6.setError(null);

            }
            else {
                if(expsw6.isChecked()==true) {
                    expsw6.setChecked(false);
                    stodate6=stodate7;
                    todate6.setText(stodate6);


                } else {
                    stodate6=stodate7;
                    todate6.setText(stodate6);
                }
            }
            post7.setText("");
            inst7.setText("");
            fromdate7.setText("");
            todate7.setText("");
            expsw7.setChecked(false);

            sinst7=sinst8;
            spost7=spost8;
            sfromdate7=sfromdate8;
            stodate7=stodate8;
            inst7.setText(sinst7);
            post7.setText(spost7);
            fromdate7.setText(sfromdate7);
//            todate7.setText(stodate7);

            if(expsw8.isChecked()==true){
                expsw7.setChecked(true);
                todate7.setText("");
                fromdateinput7.setError(null);
                todateinput7.setError(null);

            }
            else {
                if(expsw7.isChecked()==true) {
                    expsw7.setChecked(false);
                    stodate7=stodate8;
                    todate7.setText(stodate7);


                } else {
                    stodate7=stodate8;
                    todate7.setText(stodate7);
                }
            }
            post8.setText("");
            inst8.setText("");
            fromdate8.setText("");
            todate8.setText("");
            expsw8.setChecked(false);

            sinst8=sinst9;
            spost8=spost9;
            sfromdate8=sfromdate9;
            stodate8=stodate9;

            inst8.setText(sinst8);
            post8.setText(spost8);
            fromdate8.setText(sfromdate8);

            if(expsw9.isChecked()==true){
                expsw8.setChecked(true);
                todate8.setText("");
                fromdateinput8.setError(null);
                todateinput8.setError(null);

            }
            else {
                if(expsw8.isChecked()==true) {
                    expsw8.setChecked(false);
                    stodate8=stodate9;
                    todate8.setText(stodate8);


                } else {
                    stodate8=stodate9;
                    todate8.setText(stodate8);
                }
            }
            post9.setText("");
            inst9.setText("");
            fromdate9.setText("");
            todate9.setText("");
            expsw9.setChecked(false);


            sinst9=sinst10;
            spost9=spost10;
            sfromdate9=sfromdate10;
            stodate9=stodate10;
            inst9.setText(sinst9);
            post9.setText(spost9);
            fromdate9.setText(sfromdate9);
            if(expsw10.isChecked()==true){
                expsw9.setChecked(true);
                todate9.setText("");
                fromdateinput9.setError(null);
                todateinput9.setError(null);
            }
            else {
                if(expsw9.isChecked()==true) {
                    expsw9.setChecked(false);
                    stodate9=stodate10;
                    todate9.setText(stodate9);

                } else {
                    stodate9=stodate10;
                    todate9.setText(stodate9);
                }
            }
            post10.setText("");
            inst10.setText("");
            fromdate10.setText("");
            todate10.setText("");
            expsw10.setChecked(false);



            if(spost1.equals("") && sinst1.equals("") && sfromdate1.equals("") && stodate1.equals("")){
                editexp =1;
            }

            if(editexp==1){
                save1();
            }

        }

    }

    void showDeletDialog()
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this Experience?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag=1;
                                deleteExperience();
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(AdminExperiences.this));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(AdminExperiences.this));
            }
        });

        alertDialog.show();

    }


    void validate() {
        postinput1.setError(null);
        instinput1.setError(null);
        postinput2.setError(null);
        instinput2.setError(null);
        postinput3.setError(null);
        instinput3.setError(null);
        postinput4.setError(null);
        instinput4.setError(null);
        postinput5.setError(null);
        instinput5.setError(null);
        postinput6.setError(null);
        instinput6.setError(null);
        postinput7.setError(null);
        instinput7.setError(null);
        postinput8.setError(null);
        instinput8.setError(null);
        postinput9.setError(null);
        instinput9.setError(null);
        postinput10.setError(null);
        instinput10.setError(null);

        fromdateinput1.setError(null);
        todateinput1.setError(null);
        fromdateinput2.setError(null);
        todateinput2.setError(null);
        fromdateinput3.setError(null);
        todateinput3.setError(null);
        fromdateinput4.setError(null);
        todateinput4.setError(null);
        fromdateinput5.setError(null);
        todateinput5.setError(null);
        fromdateinput6.setError(null);
        todateinput6.setError(null);
        fromdateinput7.setError(null);
        todateinput7.setError(null);
        fromdateinput8.setError(null);
        todateinput8.setError(null);
        fromdateinput9.setError(null);
        todateinput9.setError(null);
        fromdateinput10.setError(null);
        todateinput10.setError(null);


        spost1 = post1.getText().toString();
        sinst1 = inst1.getText().toString();
        spost2 = post2.getText().toString();
        sinst2 = inst2.getText().toString();
        spost3 = post3.getText().toString();
        sinst3 = inst3.getText().toString();
        spost4 = post4.getText().toString();
        sinst4 = inst4.getText().toString();
        spost5 = post5.getText().toString();
        sinst5 = inst5.getText().toString();
        spost6 = post6.getText().toString();
        sinst6 = inst6.getText().toString();
        spost7 = post7.getText().toString();
        sinst7 = inst7.getText().toString();
        spost8 = post8.getText().toString();
        sinst8 = inst8.getText().toString();
        spost9 = post9.getText().toString();
        sinst9 = inst9.getText().toString();
        spost10 = post10.getText().toString();
        sinst10 = inst10.getText().toString();

        sfromdate1 = fromdate1.getText().toString();
        stodate1 = todate1.getText().toString();
        sfromdate2 = fromdate2.getText().toString();
        stodate2 = todate2.getText().toString();
        sfromdate3 = fromdate3.getText().toString();
        stodate3 = todate3.getText().toString();
        sfromdate4 = fromdate4.getText().toString();
        stodate4 = todate4.getText().toString();
        sfromdate5 = fromdate5.getText().toString();
        stodate5 = todate5.getText().toString();
        sfromdate6 = fromdate6.getText().toString();
        stodate6 = todate6.getText().toString();
        sfromdate7 = fromdate7.getText().toString();
        stodate7 = todate7.getText().toString();
        sfromdate8 = fromdate8.getText().toString();
        stodate8 = todate8.getText().toString();
        sfromdate9 = fromdate9.getText().toString();
        stodate9 = todate9.getText().toString();
        sfromdate10 = fromdate10.getText().toString();
        stodate10 = todate10.getText().toString();


        //validation
        if(editexp==1){
            save1();
        }

        else {
            if (expcount == 0) {
                if (spost1.length() < 2) {
                    errorflag = 1;
                    postinput1.setError("Kindly enter valid post");
                } else {
                    errorflag = 0;
                    if (sinst1.length() < 2) {
                        errorflag = 1;
                        instinput1.setError("Kindly enter valid institute");
                    } else {
                        errorflag = 0;
                        if (sfromdate1.length() < 2) {
                            errorflag = 1;
                            fromdateinput1.setError("Kindly enter valid date ");
                        } else {
                            errorflag = 0;
                            if (workinghereflag1 == 0 && stodate1.length() < 2) {
                                errorflag = 1;
                                todateinput1.setError("Kindly enter valid date");
                            } else {
                                errorflag = 0;
                                if (todateflag1 == 1) {
                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                    errorflag = 1;

                                } else {
                                    errorflag = 0;

//                                Toast.makeText(AdminExperiences.this, "success1", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }

                }
            }
            else if (expcount == 1) {
                if (spost1.length() < 2) {
                    errorflag = 1;
                    postinput1.setError("Kindly enter valid post");
                } else {
                    errorflag = 0;
                    if (sinst1.length() < 2) {
                        errorflag = 1;
                        instinput1.setError("Kindly enter valid institute");
                    } else {
                        errorflag = 0;
                        if (sfromdate1.length() < 2) {
                            errorflag = 1;
                            fromdateinput1.setError("Kindly enter valid date ");
                        } else {
                            errorflag = 0;
                            if (workinghereflag1 == 0 && stodate1.length() < 2) {
                                errorflag = 1;
                                todateinput1.setError("Kindly enter valid date");
                            } else {
                                errorflag = 0;
                                if (todateflag1 == 1) {
                                    errorflag = 1;

//                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                } else {
                                    errorflag = 0;

                                    //2
                                    if (spost2.length() < 2) {
                                        errorflag = 1;
                                        postinput2.setError("Kindly enter valid post");
                                    } else {
                                        errorflag = 0;
                                        if (sinst2.length() < 2) {
                                            errorflag = 1;
                                            instinput2.setError("Kindly enter valid institute");
                                        } else {
                                            errorflag = 0;
                                            if (sfromdate2.length() < 2) {
                                                errorflag = 1;
                                                fromdateinput2.setError("Kindly enter valid date ");
                                            } else {
                                                errorflag = 0;
                                                if (workinghereflag2 == 0 && stodate2.length() < 2) {
                                                    errorflag = 1;
                                                    todateinput2.setError("Kindly enter valid date");
                                                } else {
                                                    errorflag = 0;
                                                    if (todateflag1 == 1) {
                                                        errorflag = 1;

                                                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        errorflag = 0;

                                                        //3

//                                                    Toast.makeText(AdminExperiences.this, "success2", Toast.LENGTH_SHORT).show();
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
            } else if (expcount == 2) {
                if (spost1.length() < 2) {
                    errorflag = 1;
                    postinput1.setError("Kindly enter valid post");
                } else {
                    errorflag = 0;
                    if (sinst1.length() < 2) {
                        errorflag = 1;
                        instinput1.setError("Kindly enter valid institute");
                    } else {
                        errorflag = 0;
                        if (sfromdate1.length() < 2) {
                            errorflag = 1;
                            fromdateinput1.setError("Kindly enter valid date ");
                        } else {
                            errorflag = 0;
                            if (workinghereflag1 == 0 && stodate1.length() < 2) {
                                errorflag = 1;
                                todateinput1.setError("Kindly enter valid date");
                            } else {
                                errorflag = 0;
                                if (todateflag1 == 1) {
                                    errorflag = 1;

                                } else {
                                    errorflag = 0;

                                    //2
                                    if (spost2.length() < 2) {
                                        errorflag = 1;
                                        postinput2.setError("Kindly enter valid post");
                                    } else {
                                        errorflag = 0;
                                        if (sinst2.length() < 2) {
                                            errorflag = 1;
                                            instinput2.setError("Kindly enter valid institute");
                                        } else {
                                            errorflag = 0;
                                            if (sfromdate2.length() < 2) {
                                                errorflag = 1;
                                                fromdateinput2.setError("Kindly enter valid date ");
                                            } else {
                                                errorflag = 0;
                                                if (workinghereflag2 == 0 && stodate2.length() < 2) {
                                                    errorflag = 1;
                                                    todateinput2.setError("Kindly enter valid date");
                                                } else {
                                                    errorflag = 0;
                                                    if (todateflag1 == 1) {
                                                        errorflag = 1;
                                                    } else {
                                                        errorflag = 0;

                                                        //3
                                                        if (spost3.length() < 2) {
                                                            errorflag = 1;
                                                            postinput3.setError("Kindly enter valid post");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sinst3.length() < 2) {
                                                                errorflag = 1;
                                                                instinput3.setError("Kindly enter valid institute");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sfromdate3.length() < 2) {
                                                                    errorflag = 1;
                                                                    fromdateinput3.setError("Kindly enter valid date ");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (workinghereflag3 == 0 && stodate3.length() < 2) {
                                                                        errorflag = 1;
                                                                        todateinput3.setError("Kindly enter valid date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (todateflag1 == 1) {
                                                                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                            errorflag = 1;

                                                                        } else {
                                                                            errorflag = 0;

//                                                                        Toast.makeText(AdminExperiences.this, "success3", Toast.LENGTH_SHORT).show();
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
            else if (expcount == 3) {
                if (spost1.length() < 2) {
                    errorflag = 1;
                    postinput1.setError("Kindly enter valid post");
                } else {
                    errorflag = 0;
                    if (sinst1.length() < 2) {
                        errorflag = 1;
                        instinput1.setError("Kindly enter valid institute");
                    } else {
                        errorflag = 0;
                        if (sfromdate1.length() < 2) {
                            errorflag = 1;
                            fromdateinput1.setError("Kindly enter valid date ");
                        } else {
                            errorflag = 0;
                            if (workinghereflag1 == 0 && stodate1.length() < 2) {
                                errorflag = 1;
                                todateinput1.setError("Kindly enter valid date");
                            } else {
                                errorflag = 0;
                                if (todateflag1 == 1) {
                                    errorflag = 1;

//                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                } else {
                                    errorflag = 0;

                                    //2
                                    if (spost2.length() < 2) {
                                        errorflag = 1;
                                        postinput2.setError("Kindly enter valid post");
                                    } else {
                                        errorflag = 0;
                                        if (sinst2.length() < 2) {
                                            errorflag = 1;
                                            instinput2.setError("Kindly enter valid institute");
                                        } else {
                                            errorflag = 0;
                                            if (sfromdate2.length() < 2) {
                                                errorflag = 1;
                                                fromdateinput2.setError("Kindly enter valid date ");
                                            } else {
                                                errorflag = 0;
                                                if (workinghereflag2 == 0 && stodate2.length() < 2) {
                                                    errorflag = 1;
                                                    todateinput2.setError("Kindly enter valid date");
                                                } else {
                                                    errorflag = 0;
                                                    if (todateflag1 == 1) {
                                                        errorflag = 1;

//                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        errorflag = 0;

                                                        //3
                                                        if (spost3.length() < 2) {
                                                            errorflag = 1;
                                                            postinput3.setError("Kindly enter valid post");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sinst3.length() < 2) {
                                                                errorflag = 1;
                                                                instinput3.setError("Kindly enter valid institute");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sfromdate3.length() < 2) {
                                                                    errorflag = 1;
                                                                    fromdateinput3.setError("Kindly enter valid date ");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (workinghereflag3 == 0 && stodate3.length() < 2) {
                                                                        errorflag = 1;
                                                                        todateinput3.setError("Kindly enter valid date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (todateflag1 == 1) {
//                                                                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                            errorflag = 1;

                                                                        } else {
                                                                            errorflag = 0;
                                                                            //4
                                                                            if (spost4.length() < 2) {
                                                                                errorflag = 1;
                                                                                postinput4.setError("Kindly enter valid post");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst4.length() < 2) {
                                                                                    errorflag = 1;
                                                                                    instinput4.setError("Kindly enter valid institute");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate4.length() < 2) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput4.setError("Kindly enter valid date ");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (workinghereflag4 == 0 && stodate4.length() < 2) {
                                                                                            errorflag = 1;
                                                                                            todateinput4.setError("Kindly enter valid date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (todateflag1 == 1) {
                                                                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                errorflag = 1;

                                                                                            } else {
                                                                                                errorflag = 0;

//                                                                                            Toast.makeText(AdminExperiences.this, "success4", Toast.LENGTH_SHORT).show();
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
            else if (expcount == 4) {
                if (spost1.length() < 2) {
                    errorflag = 1;
                    postinput1.setError("Kindly enter valid post");
                } else {
                    errorflag = 0;
                    if (sinst1.length() < 2) {
                        errorflag = 1;
                        instinput1.setError("Kindly enter valid institute");
                    } else {
                        errorflag = 0;
                        if (sfromdate1.length() < 2) {
                            errorflag = 1;
                            fromdateinput1.setError("Kindly enter valid date ");
                        } else {
                            errorflag = 0;
                            if (workinghereflag1 == 0 && stodate1.length() < 2) {
                                errorflag = 1;
                                todateinput1.setError("Kindly enter valid date");
                            } else {
                                errorflag = 0;
                                if (todateflag1 == 1) {
                                    errorflag = 1;

//                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                } else {
                                    errorflag = 0;

                                    //2
                                    if (spost2.length() < 2) {
                                        errorflag = 1;
                                        postinput2.setError("Kindly enter valid post");
                                    } else {
                                        errorflag = 0;
                                        if (sinst2.length() < 2) {
                                            errorflag = 1;
                                            instinput2.setError("Kindly enter valid institute");
                                        } else {
                                            errorflag = 0;
                                            if (sfromdate2.length() < 2) {
                                                errorflag = 1;
                                                fromdateinput2.setError("Kindly enter valid date ");
                                            } else {
                                                errorflag = 0;
                                                if (workinghereflag2 == 0 && stodate2.length() < 2) {
                                                    errorflag = 1;
                                                    todateinput2.setError("Kindly enter valid date");
                                                } else {
                                                    errorflag = 0;
                                                    if (todateflag1 == 1) {
                                                        errorflag = 1;

//                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        errorflag = 0;

                                                        //3
                                                        if (spost3.length() < 2) {
                                                            errorflag = 1;
                                                            postinput3.setError("Kindly enter valid post");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sinst3.length() < 2) {
                                                                errorflag = 1;
                                                                instinput3.setError("Kindly enter valid institute");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sfromdate3.length() < 2) {
                                                                    errorflag = 1;
                                                                    fromdateinput3.setError("Kindly enter valid date ");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (workinghereflag3 == 0 && stodate3.length() < 2) {
                                                                        errorflag = 1;
                                                                        todateinput3.setError("Kindly enter valid date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (todateflag1 == 1) {
//                                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                            errorflag = 1;

                                                                        } else {
                                                                            errorflag = 0;
                                                                            //4
                                                                            if (spost4.length() < 2) {
                                                                                errorflag = 1;
                                                                                postinput4.setError("Kindly enter valid post");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst4.length() < 2) {
                                                                                    errorflag = 1;
                                                                                    instinput4.setError("Kindly enter valid institute");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate4.length() < 2) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput4.setError("Kindly enter valid date ");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (workinghereflag4 == 0 && stodate4.length() < 2) {
                                                                                            errorflag = 1;
                                                                                            todateinput4.setError("Kindly enter valid date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (todateflag1 == 1) {
//                                                                                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                errorflag = 1;

                                                                                            } else {
                                                                                                //5
                                                                                                errorflag = 0;
                                                                                                if (spost5.length() < 2) {
                                                                                                    errorflag = 1;
                                                                                                    postinput5.setError("Kindly enter valid post");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sinst5.length() < 2) {
                                                                                                        errorflag = 1;
                                                                                                        instinput5.setError("Kindly enter valid institute");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sfromdate5.length() < 2) {
                                                                                                            errorflag = 1;
                                                                                                            fromdateinput5.setError("Kindly enter valid date ");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (workinghereflag5 == 0 && stodate5.length() < 2) {
                                                                                                                errorflag = 1;
                                                                                                                todateinput5.setError("Kindly enter valid date");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (todateflag1 == 1) {
                                                                                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                    errorflag = 1;
                                                                                                                }
                                                                                                                else {

                                                                                                                    errorflag = 0;

//                                                                                                            Toast.makeText(AdminExperiences.this, "success5", Toast.LENGTH_SHORT).show();
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
            else if (expcount == 5) {
                if (spost1.length() < 2) {
                    errorflag = 1;
                    postinput1.setError("Kindly enter valid post");
                } else {
                    errorflag = 0;
                    if (sinst1.length() < 2) {
                        errorflag = 1;
                        instinput1.setError("Kindly enter valid institute");
                    } else {
                        errorflag = 0;
                        if (sfromdate1.length() < 2) {
                            errorflag = 1;
                            fromdateinput1.setError("Kindly enter valid date ");
                        } else {
                            errorflag = 0;
                            if (workinghereflag1 == 0 && stodate1.length() < 2) {
                                errorflag = 1;
                                todateinput1.setError("Kindly enter valid date");
                            } else {
                                errorflag = 0;
                                if (todateflag1 == 1) {
                                    errorflag = 1;

//                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                } else {
                                    errorflag = 0;

                                    //2
                                    if (spost2.length() < 2) {
                                        errorflag = 1;
                                        postinput2.setError("Kindly enter valid post");
                                    } else {
                                        errorflag = 0;
                                        if (sinst2.length() < 2) {
                                            errorflag = 1;
                                            instinput2.setError("Kindly enter valid institute");
                                        } else {
                                            errorflag = 0;
                                            if (sfromdate2.length() < 2) {
                                                errorflag = 1;
                                                fromdateinput2.setError("Kindly enter valid date ");
                                            } else {
                                                errorflag = 0;
                                                if (workinghereflag2 == 0 && stodate2.length() < 2) {
                                                    errorflag = 1;
                                                    todateinput2.setError("Kindly enter valid date");
                                                } else {
                                                    errorflag = 0;
                                                    if (todateflag1 == 1) {
                                                        errorflag = 1;

//                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        errorflag = 0;

                                                        //3
                                                        if (spost3.length() < 2) {
                                                            errorflag = 1;
                                                            postinput3.setError("Kindly enter valid post");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sinst3.length() < 2) {
                                                                errorflag = 1;
                                                                instinput3.setError("Kindly enter valid institute");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sfromdate3.length() < 2) {
                                                                    errorflag = 1;
                                                                    fromdateinput3.setError("Kindly enter valid date ");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (workinghereflag3 == 0 && stodate3.length() < 2) {
                                                                        errorflag = 1;
                                                                        todateinput3.setError("Kindly enter valid date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (todateflag1 == 1) {
//                                                                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                            errorflag = 1;

                                                                        } else {
                                                                            errorflag = 0;
                                                                            //4
                                                                            if (spost4.length() < 2) {
                                                                                errorflag = 1;
                                                                                postinput4.setError("Kindly enter valid post");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst4.length() < 2) {
                                                                                    errorflag = 1;
                                                                                    instinput4.setError("Kindly enter valid institute");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate4.length() < 2) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput4.setError("Kindly enter valid date ");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (workinghereflag4 == 0 && stodate4.length() < 2) {
                                                                                            errorflag = 1;
                                                                                            todateinput4.setError("Kindly enter valid date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (todateflag1 == 1) {
//                                                                                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                errorflag = 1;

                                                                                            } else {
                                                                                                //5
                                                                                                errorflag = 0;
                                                                                                if (spost5.length() < 2) {
                                                                                                    errorflag = 1;
                                                                                                    postinput5.setError("Kindly enter valid post");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sinst5.length() < 2) {
                                                                                                        errorflag = 1;
                                                                                                        instinput5.setError("Kindly enter valid institute");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sfromdate5.length() < 2) {
                                                                                                            errorflag = 1;
                                                                                                            fromdateinput5.setError("Kindly enter valid date ");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (workinghereflag5 == 0 && stodate5.length() < 2) {
                                                                                                                errorflag = 1;
                                                                                                                todateinput5.setError("Kindly enter valid date");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (todateflag1 == 1) {
//                                                                                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                    errorflag = 1;
                                                                                                                } else {
                                                                                                                    //6
                                                                                                                    errorflag = 0;
                                                                                                                    if (spost6.length() < 2) {
                                                                                                                        errorflag = 1;
                                                                                                                        postinput6.setError("Kindly enter valid post");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sinst6.length() < 2) {
                                                                                                                            errorflag = 1;
                                                                                                                            instinput6.setError("Kindly enter valid institute");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (sfromdate6.length() < 2) {
                                                                                                                                errorflag = 1;
                                                                                                                                fromdateinput6.setError("Kindly enter valid date ");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (workinghereflag6 == 0 && stodate6.length() < 2) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    todateinput6.setError("Kindly enter valid date");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (todateflag1 == 1) {
//                                                                                                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                        errorflag = 1;
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;

//                                                                                                                                    Toast.makeText(AdminExperiences.this, "success6", Toast.LENGTH_SHORT).show();
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
            else if (expcount == 6) {
                if (spost1.length() < 2) {
                    errorflag = 1;
                    postinput1.setError("Kindly enter valid post");
                } else {
                    errorflag = 0;
                    if (sinst1.length() < 2) {
                        errorflag = 1;
                        instinput1.setError("Kindly enter valid institute");
                    } else {
                        errorflag = 0;
                        if (sfromdate1.length() < 2) {
                            errorflag = 1;
                            fromdateinput1.setError("Kindly enter valid date ");
                        } else {
                            errorflag = 0;
                            if (workinghereflag1 == 0 && stodate1.length() < 2) {
                                errorflag = 1;
                                todateinput1.setError("Kindly enter valid date");
                            } else {
                                errorflag = 0;
                                if (todateflag1 == 1) {
                                    errorflag = 1;

//                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                } else {
                                    errorflag = 0;

                                    //2
                                    if (spost2.length() < 2) {
                                        errorflag = 1;
                                        postinput2.setError("Kindly enter valid post");
                                    } else {
                                        errorflag = 0;
                                        if (sinst2.length() < 2) {
                                            errorflag = 1;
                                            instinput2.setError("Kindly enter valid institute");
                                        } else {
                                            errorflag = 0;
                                            if (sfromdate2.length() < 2) {
                                                errorflag = 1;
                                                fromdateinput2.setError("Kindly enter valid date ");
                                            } else {
                                                errorflag = 0;
                                                if (workinghereflag2 == 0 && stodate2.length() < 2) {
                                                    errorflag = 1;
                                                    todateinput2.setError("Kindly enter valid date");
                                                } else {
                                                    errorflag = 0;
                                                    if (todateflag1 == 1) {
                                                        errorflag = 1;

//                                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        errorflag = 0;

                                                        //3
                                                        if (spost3.length() < 2) {
                                                            errorflag = 1;
                                                            postinput3.setError("Kindly enter valid post");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sinst3.length() < 2) {
                                                                errorflag = 1;
                                                                instinput3.setError("Kindly enter valid institute");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sfromdate3.length() < 2) {
                                                                    errorflag = 1;
                                                                    fromdateinput3.setError("Kindly enter valid date ");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (workinghereflag3 == 0 && stodate3.length() < 2) {
                                                                        errorflag = 1;
                                                                        todateinput3.setError("Kindly enter valid date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (todateflag1 == 1) {
//                                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                            errorflag = 1;

                                                                        } else {
                                                                            errorflag = 0;
                                                                            //4
                                                                            if (spost4.length() < 2) {
                                                                                errorflag = 1;
                                                                                postinput4.setError("Kindly enter valid post");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst4.length() < 2) {
                                                                                    errorflag = 1;
                                                                                    instinput4.setError("Kindly enter valid institute");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate4.length() < 2) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput4.setError("Kindly enter valid date ");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (workinghereflag4 == 0 && stodate4.length() < 2) {
                                                                                            errorflag = 1;
                                                                                            todateinput4.setError("Kindly enter valid date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (todateflag1 == 1) {
//                                                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                errorflag = 1;

                                                                                            } else {
                                                                                                //5
                                                                                                errorflag = 0;
                                                                                                if (spost5.length() < 2) {
                                                                                                    errorflag = 1;
                                                                                                    postinput5.setError("Kindly enter valid post");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sinst5.length() < 2) {
                                                                                                        errorflag = 1;
                                                                                                        instinput5.setError("Kindly enter valid institute");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sfromdate5.length() < 2) {
                                                                                                            errorflag = 1;
                                                                                                            fromdateinput5.setError("Kindly enter valid date ");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (workinghereflag5 == 0 && stodate5.length() < 2) {
                                                                                                                errorflag = 1;
                                                                                                                todateinput5.setError("Kindly enter valid date");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (todateflag1 == 1) {
//                                                                                                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                    errorflag = 1;
                                                                                                                } else {
                                                                                                                    //6
                                                                                                                    errorflag = 0;
                                                                                                                    if (spost6.length() < 2) {
                                                                                                                        errorflag = 1;
                                                                                                                        postinput6.setError("Kindly enter valid post");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sinst6.length() < 2) {
                                                                                                                            errorflag = 1;
                                                                                                                            instinput6.setError("Kindly enter valid institute");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (sfromdate6.length() < 2) {
                                                                                                                                errorflag = 1;
                                                                                                                                fromdateinput6.setError("Kindly enter valid date ");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (workinghereflag6 == 0 && stodate6.length() < 2) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    todateinput6.setError("Kindly enter valid date");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (todateflag1 == 1) {
//                                                                                                                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                        errorflag = 1;
                                                                                                                                    } else {
                                                                                                                                        //7
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (spost7.length() < 2) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            postinput7.setError("Kindly enter valid post");
                                                                                                                                        } else {
                                                                                                                                            errorflag = 0;
                                                                                                                                            if (sinst7.length() < 2) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                instinput7.setError("Kindly enter valid institute");
                                                                                                                                            } else {
                                                                                                                                                errorflag = 0;
                                                                                                                                                if (sfromdate7.length() < 2) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    fromdateinput7.setError("Kindly enter valid date ");
                                                                                                                                                } else {
                                                                                                                                                    errorflag = 0;
                                                                                                                                                    if (workinghereflag7 == 0 && stodate7.length() < 2) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        todateinput7.setError("Kindly enter valid date");
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (todateflag1 == 1) {
//                                                                                                                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                                            errorflag = 1;

                                                                                                                                                        } else {

                                                                                                                                                            errorflag = 0;

//                                                                                                                                                Toast.makeText(AdminExperiences.this, "success7", Toast.LENGTH_SHORT).show();
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
            else if (expcount == 7) {
                if (spost1.length() < 2) {
                    errorflag = 1;
                    postinput1.setError("Kindly enter valid post");
                } else {
                    errorflag = 0;
                    if (sinst1.length() < 2) {
                        errorflag = 1;
                        instinput1.setError("Kindly enter valid institute");
                    } else {
                        errorflag = 0;
                        if (sfromdate1.length() < 2) {
                            errorflag = 1;
                            fromdateinput1.setError("Kindly enter valid date ");
                        } else {
                            errorflag = 0;
                            if (workinghereflag1 == 0 && stodate1.length() < 2) {
                                errorflag = 1;
                                todateinput1.setError("Kindly enter valid date");
                            } else {
                                errorflag = 0;
                                if (todateflag1 == 1) {
                                    errorflag = 1;

//                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                } else {
                                    errorflag = 0;

                                    //2
                                    if (spost2.length() < 2) {
                                        errorflag = 1;
                                        postinput2.setError("Kindly enter valid post");
                                    } else {
                                        errorflag = 0;
                                        if (sinst2.length() < 2) {
                                            errorflag = 1;
                                            instinput2.setError("Kindly enter valid institute");
                                        } else {
                                            errorflag = 0;
                                            if (sfromdate2.length() < 2) {
                                                errorflag = 1;
                                                fromdateinput2.setError("Kindly enter valid date ");
                                            } else {
                                                errorflag = 0;
                                                if (workinghereflag2 == 0 && stodate2.length() < 2) {
                                                    errorflag = 1;
                                                    todateinput2.setError("Kindly enter valid date");
                                                } else {
                                                    errorflag = 0;
                                                    if (todateflag1 == 1) {
                                                        errorflag = 1;

//                                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        errorflag = 0;

                                                        //3
                                                        if (spost3.length() < 2) {
                                                            errorflag = 1;
                                                            postinput3.setError("Kindly enter valid post");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sinst3.length() < 2) {
                                                                errorflag = 1;
                                                                instinput3.setError("Kindly enter valid institute");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sfromdate3.length() < 2) {
                                                                    errorflag = 1;
                                                                    fromdateinput3.setError("Kindly enter valid date ");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (workinghereflag3 == 0 && stodate3.length() < 2) {
                                                                        errorflag = 1;
                                                                        todateinput3.setError("Kindly enter valid date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (todateflag1 == 1) {
//                                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                            errorflag = 1;

                                                                        } else {
                                                                            errorflag = 0;
                                                                            //4
                                                                            if (spost4.length() < 2) {
                                                                                errorflag = 1;
                                                                                postinput4.setError("Kindly enter valid post");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst4.length() < 2) {
                                                                                    errorflag = 1;
                                                                                    instinput4.setError("Kindly enter valid institute");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate4.length() < 2) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput4.setError("Kindly enter valid date ");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (workinghereflag4 == 0 && stodate4.length() < 2) {
                                                                                            errorflag = 1;
                                                                                            todateinput4.setError("Kindly enter valid date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (todateflag1 == 1) {
//                                                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                errorflag = 1;

                                                                                            } else {
                                                                                                //5
                                                                                                errorflag = 0;
                                                                                                if (spost5.length() < 2) {
                                                                                                    errorflag = 1;
                                                                                                    postinput5.setError("Kindly enter valid post");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sinst5.length() < 2) {
                                                                                                        errorflag = 1;
                                                                                                        instinput5.setError("Kindly enter valid institute");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sfromdate5.length() < 2) {
                                                                                                            errorflag = 1;
                                                                                                            fromdateinput5.setError("Kindly enter valid date ");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (workinghereflag5 == 0 && stodate5.length() < 2) {
                                                                                                                errorflag = 1;
                                                                                                                todateinput5.setError("Kindly enter valid date");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (todateflag1 == 1) {
//                                                                                                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                    errorflag = 1;
                                                                                                                } else {
                                                                                                                    //6
                                                                                                                    errorflag = 0;
                                                                                                                    if (spost6.length() < 2) {
                                                                                                                        errorflag = 1;
                                                                                                                        postinput6.setError("Kindly enter valid post");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sinst6.length() < 2) {
                                                                                                                            errorflag = 1;
                                                                                                                            instinput6.setError("Kindly enter valid institute");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (sfromdate6.length() < 2) {
                                                                                                                                errorflag = 1;
                                                                                                                                fromdateinput6.setError("Kindly enter valid date ");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (workinghereflag6 == 0 && stodate6.length() < 2) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    todateinput6.setError("Kindly enter valid date");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (todateflag1 == 1) {
//                                                                                                                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                        errorflag = 1;
                                                                                                                                    } else {
                                                                                                                                        //7
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (spost7.length() < 2) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            postinput7.setError("Kindly enter valid post");
                                                                                                                                        } else {
                                                                                                                                            errorflag = 0;
                                                                                                                                            if (sinst7.length() < 2) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                instinput7.setError("Kindly enter valid institute");
                                                                                                                                            } else {
                                                                                                                                                errorflag = 0;
                                                                                                                                                if (sfromdate7.length() < 2) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    fromdateinput7.setError("Kindly enter valid date ");
                                                                                                                                                } else {
                                                                                                                                                    errorflag = 0;
                                                                                                                                                    if (workinghereflag7 == 0 && stodate7.length() < 2) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        todateinput7.setError("Kindly enter valid date");
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (todateflag1 == 1) {
//                                                                                                                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                                            errorflag = 1;

                                                                                                                                                        } else {
                                                                                                                                                            //8
                                                                                                                                                            errorflag = 0;
                                                                                                                                                            if (spost8.length() < 2) {
                                                                                                                                                                errorflag = 1;
                                                                                                                                                                postinput8.setError("Kindly enter valid post");
                                                                                                                                                            } else {
                                                                                                                                                                errorflag = 0;
                                                                                                                                                                if (sinst8.length() < 2) {
                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                    instinput8.setError("Kindly enter valid institute");
                                                                                                                                                                } else {
                                                                                                                                                                    errorflag = 0;
                                                                                                                                                                    if (sfromdate8.length() < 2) {
                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                        fromdateinput8.setError("Kindly enter valid date ");
                                                                                                                                                                    } else {
                                                                                                                                                                        errorflag = 0;
                                                                                                                                                                        if (workinghereflag8 == 0 && stodate8.length() < 2) {
                                                                                                                                                                            errorflag = 1;
                                                                                                                                                                            todateinput1.setError("Kindly enter valid date");
                                                                                                                                                                        } else {
                                                                                                                                                                            errorflag = 0;
                                                                                                                                                                            if (todateflag1 == 1) {
//                                                                                                                                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                                                                errorflag = 1;
                                                                                                                                                                            } else {
                                                                                                                                                                                errorflag = 0;

//                                                                                                                                                                    Toast.makeText(AdminExperiences.this, "success8", Toast.LENGTH_SHORT).show();
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
            else if (expcount == 8) {
                if (spost1.length() < 2) {
                    errorflag = 1;
                    postinput1.setError("Kindly enter valid post");
                } else {
                    errorflag = 0;
                    if (sinst1.length() < 2) {
                        errorflag = 1;
                        instinput1.setError("Kindly enter valid institute");
                    } else {
                        errorflag = 0;
                        if (sfromdate1.length() < 2) {
                            errorflag = 1;
                            fromdateinput1.setError("Kindly enter valid date ");
                        } else {
                            errorflag = 0;
                            if (workinghereflag1 == 0 && stodate1.length() < 2) {
                                errorflag = 1;
                                todateinput1.setError("Kindly enter valid date");
                            } else {
                                errorflag = 0;
                                if (todateflag1 == 1) {
                                    errorflag = 1;

//                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                } else {
                                    errorflag = 0;

                                    //2
                                    if (spost2.length() < 2) {
                                        errorflag = 1;
                                        postinput2.setError("Kindly enter valid post");
                                    } else {
                                        errorflag = 0;
                                        if (sinst2.length() < 2) {
                                            errorflag = 1;
                                            instinput2.setError("Kindly enter valid institute");
                                        } else {
                                            errorflag = 0;
                                            if (sfromdate2.length() < 2) {
                                                errorflag = 1;
                                                fromdateinput2.setError("Kindly enter valid date ");
                                            } else {
                                                errorflag = 0;
                                                if (workinghereflag2 == 0 && stodate2.length() < 2) {
                                                    errorflag = 1;
                                                    todateinput2.setError("Kindly enter valid date");
                                                } else {
                                                    errorflag = 0;
                                                    if (todateflag1 == 1) {
                                                        errorflag = 1;

//                                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        errorflag = 0;

                                                        //3
                                                        if (spost3.length() < 2) {
                                                            errorflag = 1;
                                                            postinput3.setError("Kindly enter valid post");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sinst3.length() < 2) {
                                                                errorflag = 1;
                                                                instinput3.setError("Kindly enter valid institute");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sfromdate3.length() < 2) {
                                                                    errorflag = 1;
                                                                    fromdateinput3.setError("Kindly enter valid date ");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (workinghereflag3 == 0 && stodate3.length() < 2) {
                                                                        errorflag = 1;
                                                                        todateinput3.setError("Kindly enter valid date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (todateflag1 == 1) {
//                                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                            errorflag = 1;

                                                                        } else {
                                                                            errorflag = 0;
                                                                            //4
                                                                            if (spost4.length() < 2) {
                                                                                errorflag = 1;
                                                                                postinput4.setError("Kindly enter valid post");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst4.length() < 2) {
                                                                                    errorflag = 1;
                                                                                    instinput4.setError("Kindly enter valid institute");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate4.length() < 2) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput4.setError("Kindly enter valid date ");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (workinghereflag4 == 0 && stodate4.length() < 2) {
                                                                                            errorflag = 1;
                                                                                            todateinput4.setError("Kindly enter valid date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (todateflag1 == 1) {
//                                                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                errorflag = 1;

                                                                                            } else {
                                                                                                //5
                                                                                                errorflag = 0;
                                                                                                if (spost5.length() < 2) {
                                                                                                    errorflag = 1;
                                                                                                    postinput5.setError("Kindly enter valid post");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sinst5.length() < 2) {
                                                                                                        errorflag = 1;
                                                                                                        instinput5.setError("Kindly enter valid institute");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sfromdate5.length() < 2) {
                                                                                                            errorflag = 1;
                                                                                                            fromdateinput5.setError("Kindly enter valid date ");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (workinghereflag5 == 0 && stodate5.length() < 2) {
                                                                                                                errorflag = 1;
                                                                                                                todateinput5.setError("Kindly enter valid date");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (todateflag1 == 1) {
//                                                                                                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                    errorflag = 1;
                                                                                                                } else {
                                                                                                                    //6
                                                                                                                    errorflag = 0;
                                                                                                                    if (spost6.length() < 2) {
                                                                                                                        errorflag = 1;
                                                                                                                        postinput6.setError("Kindly enter valid post");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sinst6.length() < 2) {
                                                                                                                            errorflag = 1;
                                                                                                                            instinput6.setError("Kindly enter valid institute");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (sfromdate6.length() < 2) {
                                                                                                                                errorflag = 1;
                                                                                                                                fromdateinput6.setError("Kindly enter valid date ");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (workinghereflag6 == 0 && stodate6.length() < 2) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    todateinput6.setError("Kindly enter valid date");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (todateflag1 == 1) {
//                                                                                                                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                        errorflag = 1;
                                                                                                                                    } else {
                                                                                                                                        //7
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (spost7.length() < 2) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            postinput7.setError("Kindly enter valid post");
                                                                                                                                        } else {
                                                                                                                                            errorflag = 0;
                                                                                                                                            if (sinst7.length() < 2) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                instinput7.setError("Kindly enter valid institute");
                                                                                                                                            } else {
                                                                                                                                                errorflag = 0;
                                                                                                                                                if (sfromdate7.length() < 2) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    fromdateinput7.setError("Kindly enter valid date ");
                                                                                                                                                } else {
                                                                                                                                                    errorflag = 0;
                                                                                                                                                    if (workinghereflag7 == 0 && stodate7.length() < 2) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        todateinput7.setError("Kindly enter valid date");
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (todateflag1 == 1) {
//                                                                                                                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                                            errorflag = 1;

                                                                                                                                                        } else {
                                                                                                                                                            //8
                                                                                                                                                            errorflag = 0;
                                                                                                                                                            if (spost8.length() < 2) {
                                                                                                                                                                errorflag = 1;
                                                                                                                                                                postinput8.setError("Kindly enter valid post");
                                                                                                                                                            } else {
                                                                                                                                                                errorflag = 0;
                                                                                                                                                                if (sinst8.length() < 2) {
                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                    instinput8.setError("Kindly enter valid institute");
                                                                                                                                                                } else {
                                                                                                                                                                    errorflag = 0;
                                                                                                                                                                    if (sfromdate8.length() < 2) {
                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                        fromdateinput8.setError("Kindly enter valid date ");
                                                                                                                                                                    } else {
                                                                                                                                                                        errorflag = 0;
                                                                                                                                                                        if (workinghereflag8 == 0 && stodate8.length() < 2) {
                                                                                                                                                                            errorflag = 1;
                                                                                                                                                                            todateinput1.setError("Kindly enter valid date");
                                                                                                                                                                        } else {
                                                                                                                                                                            errorflag = 0;
                                                                                                                                                                            if (todateflag1 == 1) {
//                                                                                                                                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                                                                errorflag = 1;
                                                                                                                                                                            } else {
                                                                                                                                                                                //9
                                                                                                                                                                                errorflag = 0;
                                                                                                                                                                                if (spost9.length() < 2) {
                                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                                    postinput9.setError("Kindly enter valid post");
                                                                                                                                                                                } else {
                                                                                                                                                                                    errorflag = 0;
                                                                                                                                                                                    if (sinst9.length() < 2) {
                                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                                        instinput9.setError("Kindly enter valid institute");
                                                                                                                                                                                    } else {
                                                                                                                                                                                        errorflag = 0;
                                                                                                                                                                                        if (sfromdate9.length() < 2) {
                                                                                                                                                                                            errorflag = 1;
                                                                                                                                                                                            fromdateinput9.setError("Kindly enter valid date ");
                                                                                                                                                                                        } else {
                                                                                                                                                                                            errorflag = 0;
                                                                                                                                                                                            if (workinghereflag9 == 0 && stodate9.length() < 2) {
                                                                                                                                                                                                errorflag = 1;
                                                                                                                                                                                                todateinput9.setError("Kindly enter valid date");
                                                                                                                                                                                            } else {
                                                                                                                                                                                                errorflag = 0;
                                                                                                                                                                                                if (todateflag1 == 1) {
//                                                                                                                                                                                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                                                } else {
                                                                                                                                                                                                    errorflag = 0;
//                                                                                                                                                                                        Toast.makeText(AdminExperiences.this, "success9", Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }

                                                                                                                                                                                    }

                                                                                                                                                                                }                                                                                                                                                                }
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
            else if (expcount == 9) {
                if (spost1.length() < 2) {
                    errorflag = 1;
                    postinput1.setError("Kindly enter valid post");
                } else {
                    errorflag = 0;
                    if (sinst1.length() < 2) {
                        errorflag = 1;
                        instinput1.setError("Kindly enter valid institute");
                    } else {
                        errorflag = 0;
                        if (sfromdate1.length() < 2) {
                            errorflag = 1;
                            fromdateinput1.setError("Kindly enter valid date ");
                        } else {
                            errorflag = 0;
                            if (workinghereflag1 == 0 && stodate1.length() < 2) {
                                errorflag = 1;
                                todateinput1.setError("Kindly enter valid date");
                            } else {
                                errorflag = 0;
                                if (todateflag1 == 1) {
                                    errorflag = 1;

//                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                } else {
                                    errorflag = 0;

                                    //2
                                    if (spost2.length() < 2) {
                                        errorflag = 1;
                                        postinput2.setError("Kindly enter valid post");
                                    } else {
                                        errorflag = 0;
                                        if (sinst2.length() < 2) {
                                            errorflag = 1;
                                            instinput2.setError("Kindly enter valid institute");
                                        } else {
                                            errorflag = 0;
                                            if (sfromdate2.length() < 2) {
                                                errorflag = 1;
                                                fromdateinput2.setError("Kindly enter valid date ");
                                            } else {
                                                errorflag = 0;
                                                if (workinghereflag2 == 0 && stodate2.length() < 2) {
                                                    errorflag = 1;
                                                    todateinput2.setError("Kindly enter valid date");
                                                } else {
                                                    errorflag = 0;
                                                    if (todateflag1 == 1) {
                                                        errorflag = 1;

//                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        errorflag = 0;

                                                        //3
                                                        if (spost3.length() < 2) {
                                                            errorflag = 1;
                                                            postinput3.setError("Kindly enter valid post");
                                                        } else {
                                                            errorflag = 0;
                                                            if (sinst3.length() < 2) {
                                                                errorflag = 1;
                                                                instinput3.setError("Kindly enter valid institute");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sfromdate3.length() < 2) {
                                                                    errorflag = 1;
                                                                    fromdateinput3.setError("Kindly enter valid date ");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (workinghereflag3 == 0 && stodate3.length() < 2) {
                                                                        errorflag = 1;
                                                                        todateinput3.setError("Kindly enter valid date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (todateflag1 == 1) {
//                                                                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                            errorflag = 1;

                                                                        } else {
                                                                            errorflag = 0;
                                                                            //4
                                                                            if (spost4.length() < 2) {
                                                                                errorflag = 1;
                                                                                postinput4.setError("Kindly enter valid post");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sinst4.length() < 2) {
                                                                                    errorflag = 1;
                                                                                    instinput4.setError("Kindly enter valid institute");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sfromdate4.length() < 2) {
                                                                                        errorflag = 1;
                                                                                        fromdateinput4.setError("Kindly enter valid date ");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (workinghereflag4 == 0 && stodate4.length() < 2) {
                                                                                            errorflag = 1;
                                                                                            todateinput4.setError("Kindly enter valid date");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (todateflag1 == 1) {
//                                                                                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                errorflag = 1;

                                                                                            } else {
                                                                                                //5
                                                                                                errorflag = 0;
                                                                                                if (spost5.length() < 2) {
                                                                                                    errorflag = 1;
                                                                                                    postinput5.setError("Kindly enter valid post");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sinst5.length() < 2) {
                                                                                                        errorflag = 1;
                                                                                                        instinput5.setError("Kindly enter valid institute");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sfromdate5.length() < 2) {
                                                                                                            errorflag = 1;
                                                                                                            fromdateinput5.setError("Kindly enter valid date ");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (workinghereflag5 == 0 && stodate5.length() < 2) {
                                                                                                                errorflag = 1;
                                                                                                                todateinput5.setError("Kindly enter valid date");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (todateflag1 == 1) {
//                                                                                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                    errorflag = 1;
                                                                                                                } else {
                                                                                                                    //6
                                                                                                                    errorflag = 0;
                                                                                                                    if (spost6.length() < 2) {
                                                                                                                        errorflag = 1;
                                                                                                                        postinput6.setError("Kindly enter valid post");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sinst6.length() < 2) {
                                                                                                                            errorflag = 1;
                                                                                                                            instinput6.setError("Kindly enter valid institute");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (sfromdate6.length() < 2) {
                                                                                                                                errorflag = 1;
                                                                                                                                fromdateinput6.setError("Kindly enter valid date ");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (workinghereflag6 == 0 && stodate6.length() < 2) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    todateinput6.setError("Kindly enter valid date");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (todateflag1 == 1) {
//                                                                                                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                        errorflag = 1;
                                                                                                                                    } else {
                                                                                                                                        //7
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (spost7.length() < 2) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            postinput7.setError("Kindly enter valid post");
                                                                                                                                        } else {
                                                                                                                                            errorflag = 0;
                                                                                                                                            if (sinst7.length() < 2) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                instinput7.setError("Kindly enter valid institute");
                                                                                                                                            } else {
                                                                                                                                                errorflag = 0;
                                                                                                                                                if (sfromdate7.length() < 2) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    fromdateinput7.setError("Kindly enter valid date ");
                                                                                                                                                } else {
                                                                                                                                                    errorflag = 0;
                                                                                                                                                    if (workinghereflag7 == 0 && stodate7.length() < 2) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        todateinput7.setError("Kindly enter valid date");
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (todateflag1 == 1) {
//                                                                                                                                                        Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                                            errorflag = 1;

                                                                                                                                                        } else {
                                                                                                                                                            //8
                                                                                                                                                            errorflag = 0;
                                                                                                                                                            if (spost8.length() < 2) {
                                                                                                                                                                errorflag = 1;
                                                                                                                                                                postinput8.setError("Kindly enter valid post");
                                                                                                                                                            } else {
                                                                                                                                                                errorflag = 0;
                                                                                                                                                                if (sinst8.length() < 2) {
                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                    instinput8.setError("Kindly enter valid institute");
                                                                                                                                                                } else {
                                                                                                                                                                    errorflag = 0;
                                                                                                                                                                    if (sfromdate8.length() < 2) {
                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                        fromdateinput8.setError("Kindly enter valid date ");
                                                                                                                                                                    } else {
                                                                                                                                                                        errorflag = 0;
                                                                                                                                                                        if (workinghereflag8 == 0 && stodate8.length() < 2) {
                                                                                                                                                                            errorflag = 1;
                                                                                                                                                                            todateinput1.setError("Kindly enter valid date");
                                                                                                                                                                        } else {
                                                                                                                                                                            errorflag = 0;
                                                                                                                                                                            if (todateflag1 == 1) {
//                                                                                                                                                                            Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                                                                errorflag = 1;
                                                                                                                                                                            } else {
                                                                                                                                                                                //9
                                                                                                                                                                                errorflag = 0;
                                                                                                                                                                                if (spost9.length() < 2) {
                                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                                    postinput9.setError("Kindly enter valid post");
                                                                                                                                                                                } else {
                                                                                                                                                                                    errorflag = 0;
                                                                                                                                                                                    if (sinst9.length() < 2) {
                                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                                        instinput9.setError("Kindly enter valid institute");
                                                                                                                                                                                    } else {
                                                                                                                                                                                        errorflag = 0;
                                                                                                                                                                                        if (sfromdate9.length() < 2) {
                                                                                                                                                                                            errorflag = 1;
                                                                                                                                                                                            fromdateinput9.setError("Kindly enter valid date ");
                                                                                                                                                                                        } else {
                                                                                                                                                                                            errorflag = 0;
                                                                                                                                                                                            if (workinghereflag9 == 0 && stodate9.length() < 2) {
                                                                                                                                                                                                errorflag = 1;
                                                                                                                                                                                                todateinput9.setError("Kindly enter valid date");
                                                                                                                                                                                            } else {
                                                                                                                                                                                                errorflag = 0;
                                                                                                                                                                                                if (todateflag1 == 1) {
//                                                                                                                                                                                                Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                                                } else {
                                                                                                                                                                                                    //10
                                                                                                                                                                                                    errorflag = 0;
                                                                                                                                                                                                    if (spost10.length() < 2) {
                                                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                                                        postinput10.setError("Kindly enter valid post");
                                                                                                                                                                                                    } else {
                                                                                                                                                                                                        errorflag = 0;
                                                                                                                                                                                                        if (sinst10.length() < 2) {
                                                                                                                                                                                                            errorflag = 1;
                                                                                                                                                                                                            instinput10.setError("Kindly enter valid institute");
                                                                                                                                                                                                        } else {
                                                                                                                                                                                                            errorflag = 0;
                                                                                                                                                                                                            if (sfromdate10.length() < 2) {
                                                                                                                                                                                                                errorflag = 1;
                                                                                                                                                                                                                fromdateinput10.setError("Kindly enter valid date ");
                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                errorflag = 0;
                                                                                                                                                                                                                if (workinghereflag10 == 0 && stodate10.length() < 2) {
                                                                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                                                                    todateinput1.setError("Kindly enter valid date");
                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                    errorflag = 0;
                                                                                                                                                                                                                    if (todateflag1 == 1) {
//                                                                                                                                                                                                                    Toast.makeText(AdminExperiences.this, "To Date must be Greater than From Date", Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                        errorflag = 0;
//                                                                                                                                                                                                                    Toast.makeText(AdminExperiences.this, "success10", Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }

                                                                                                                                                                                                        }

                                                                                                                                                                                                    }
                                                                                                                                                                                                    ;
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
                            }
                        }

                    }
                }
            }
        }
        if(errorflag==0) {
            save1();
        }

    }

    public  void save1() {
        try {


            if(errorflag==0) {


                Experiences obj1=new Experiences(spost1,sinst1,sfromdate1,stodate1);
                Experiences obj2=new Experiences(spost2,sinst2,sfromdate2,stodate2);
                Experiences obj3=new Experiences(spost3,sinst3,sfromdate3,stodate3);
                Experiences obj4=new Experiences(spost4,sinst4,sfromdate4,stodate4);
                Experiences obj5=new Experiences(spost5,sinst5,sfromdate5,stodate5);
                Experiences obj6=new Experiences(spost6,sinst6,sfromdate6,stodate6);
                Experiences obj7=new Experiences(spost7,sinst7,sfromdate7,stodate7);
                Experiences obj8=new Experiences(spost8,sinst8,sfromdate8,stodate8);
                Experiences obj9=new Experiences(spost9,sinst9,sfromdate9,stodate9);
                Experiences obj10=new Experiences(spost10,sinst10,sfromdate10,stodate10);

                experiencesList.add(obj1);
                experiencesList.add(obj2);
                experiencesList.add(obj3);
                experiencesList.add(obj4);
                experiencesList.add(obj5);
                experiencesList.add(obj6);
                experiencesList.add(obj7);
                experiencesList.add(obj8);
                experiencesList.add(obj9);
                experiencesList.add(obj10);

                String encObjString=OtoString(experiencesList,MySharedPreferencesManager.getDigest1(AdminExperiences.this),MySharedPreferencesManager.getDigest2(AdminExperiences.this));


                new SaveExperiences().execute(encObjString);
            }
        } catch (Exception e) {
        }

    }
    class SaveExperiences extends AsyncTask<String,String,String> {
        protected String doInBackground(String... param) {
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("u",username));      //0
            params.add(new BasicNameValuePair("d",param[0]));      //1


            json = jParser.makeHttpRequest(Z.url_SaveExperiences, "GET", params);

            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }


        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(AdminExperiences.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();

                if(role.equals("hr"))
                    setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("admin"))
                    setResult(AdminActivity.ADMIN_DATA_CHANGE_RESULT_CODE);


                AdminExperiences.super.onBackPressed();
            }
            else
                Toast.makeText(AdminExperiences.this, "Try again !", Toast.LENGTH_SHORT).show();

        }
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

                validate();
                break;

            case android.R.id.home:
                onBackPressed();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
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
                                    AdminExperiences.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(AdminExperiences.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(AdminExperiences.this));
                }
            });

            alertDialog.show();
        } else
            AdminExperiences.super.onBackPressed();


    }
    void showDateDialog(final TextInputEditText id, final TextInputLayout tid)
    {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(AdminExperiences.this);
        LayoutInflater inflater = AdminExperiences.this.getLayoutInflater();
        View dialog = inflater.inflate(R.layout.monthyeardialog,null);
        dialogBuilder.setView(dialog);



        final WheelView monthView,yearView;

        final List<String> monthList= new ArrayList<String>();
        final List<String> yearList= new ArrayList<String>();

        monthView= (WheelView)dialog.findViewById(R.id.monthwheel);
        yearView= (WheelView)dialog.findViewById(R.id.yearwheel);

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



        Calendar currentCalendar = Calendar.getInstance();
        for (int i = 1975; i <= currentCalendar.get(Calendar.YEAR); i++)
            yearList.add("" + i);


        monthView.setWheelAdapter(new ArrayWheelAdapter(AdminExperiences.this));
        monthView.setWheelData(monthList);
        yearView.setWheelAdapter(new ArrayWheelAdapter(AdminExperiences.this));
        yearView.setWheelData(yearList);



        View setselectionview=(View)dialog.findViewById(R.id.setselectionview);
        View cancelselectionview=(View)dialog.findViewById(R.id.cancelselectionview);

        final AlertDialog alertDialog = dialogBuilder.create();



        setselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int monthPosition=monthView.getCurrentPosition();
                int yearPosition=yearView.getCurrentPosition();

                String selectedMonth=monthList.get(monthPosition);
                String selectedYear=yearList.get(yearPosition);

                int isInvalidDate = 0;
                Calendar currentDatecalendar = Calendar.getInstance();
                int selectedYearInterger = Integer.parseInt(selectedYear);
                if (selectedYearInterger == currentDatecalendar.get(Calendar.YEAR) && monthPosition > currentDatecalendar.get(Calendar.MONTH)) {
                    isInvalidDate = 1;
                }

                boolean bln = setMonthYear(id, selectedMonth, selectedYear, isInvalidDate);
                if (!bln) {
                    tid.setError("kindly enter valid date");

                    Toast.makeText(AdminExperiences.this, "bln "+bln, Toast.LENGTH_SHORT).show();
                }


//                setMonthYear(id,selectedMonth,selectedYear);

                alertDialog.cancel();
            }
        });


        cancelselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.cancel();
            }
        });

        alertDialog.show();

        int w= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());
        int h= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 215, getResources().getDisplayMetrics());
        alertDialog.getWindow().setLayout(w, h);

    }
    boolean setMonthYear(TextInputEditText id, String selectedMonth, String selectedYear, int isInvalidDate) {
        if (isInvalidDate == 1) {
            id.setText("");
            return false;
        } else {
            id.setText(selectedMonth + ", " + selectedYear);
            return true;
        }
    }

}
