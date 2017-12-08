package placeme.octopusites.com.placeme;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import java.util.List;

import placeme.octopusites.com.placeme.modal.Experiences;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.HrCompanyDetailsTabFragment.HRlog;


/**
 * A simple {@link Fragment} subclass.
 */
public class HrExperiencesTabFragment extends Fragment {


    int d = 0, expcount = 0;
    int edittedFlag = 0;
    View addmoreexp;
    ProgressBar   personalprogress1;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    String digest1, digest2;
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview, trash6selectionview, trash7selectionview, trash8selectionview, trash9selectionview, trash10selectionview;
    EditText fromdate1, todate1, fromdate2, todate2, fromdate3, todate3, fromdate4, todate4, fromdate5, todate5, fromdate6, todate6, fromdate7, todate7, fromdate8, todate8, fromdate9, todate9, fromdate10, todate10;
    EditText post1, post2, post3, post4, post5, post6, post7, post8, post9, post10;
    EditText inst11, inst12, inst13, inst14, inst15, inst16, inst17, inst18, inst19, inst110;
    TextView addmoreexptxt,work1,work2,work3,work4,work5,work6,work7,work8,work9,work10;
    String posts1 = "", posts2 = "", posts3 = "", posts4 = "", posts5 = "", posts6 = "", posts7 = "", posts8 = "", posts9 = "", posts10 = "";
    String inst1s1 = "", inst1s2 = "", inst1s3 = "", inst1s4 = "", inst1s5 = "", inst1s6 = "", inst1s7 = "", inst1s8 = "", inst1s9 = "", inst1s10 = "";
    String fromdates1 = "", todates1 = "", fromdates2 = "", todates2 = "", fromdates3 = "", todates3 = "", fromdates4 = "", todates4 = "", fromdates5 = "", todates5 = "", fromdates6 = "", todates6 = "", fromdates7 = "", todates7 = "", fromdates8 = "", todates8 = "", fromdates9 = "", todates9 = "", fromdates10 = "", todates10 = "";
    String encpost1 = "", encpost2 = "", encpost3 = "", encpost4 = "", encpost5 = "", encpost6 = "", encpost7 = "", encpost8 = "", encpost9 = "", encpost10 = "";
    String encinst1 = "", encinst2 = "", encinst3 = "", encinst4 = "", encinst5 = "", encinst6 = "", encinst7 = "", encinst8 = "", encinst9 = "", encinst10 = "";
    String encfromdate1 = "", encfromdate2 = "", encfromdate3 = "", encfromdate4 = "", encfromdate5 = "", encfromdate6 = "", encfromdate7 = "", encfromdate8 = "", encfromdate9 = "", encfromdate10 = "";
    String enctodate1 = "", enctodate2 = "", enctodate3 = "", enctodate4 = "", enctodate5 = "", enctodate6 = "", enctodate7 = "", enctodate8 = "", enctodate9 = "", enctodate10 = "";
//    Button savepersonal;
    String status = "false",encObjString="";
    AdminData a = new AdminData();
    SwitchCompat switch1, switch2, switch3, switch4, switch5, switch6, switch7, switch8, switch9, switch10;
    boolean blnswitch1, blnswitch2, blnswitch3, blnswitch4, blnswitch5, blnswitch6, blnswitch7, blnswitch8, blnswitch9, blnswitch10;
    private String username = "";
//    private final String url_saveHrExperience = "http://192.168.100.10/AESTest/SaveHrExperiences";
    int errorflag = 0;
    byte[] demoKeyBytes, demoIVBytes;
    String sPadding;
    String role;
    HrData hr = new HrData();
    ArrayList<Experiences> experiencesList=new ArrayList<>();
    View rootView;
    TextInputLayout postinput1,instinput1,fromdateinput1,todateinput1,postinput2,instinput2,fromdateinput2,todateinput2,postinput3,instinput3,fromdateinput3,todateinput3,postinput4,instinput4,fromdateinput4,todateinput4,postinput5,instinput5,fromdateinput5,todateinput5;
    TextInputLayout postinput6,instinput6,fromdateinput6,todateinput6,postinput7,instinput7,fromdateinput7,todateinput7,postinput8,instinput8,fromdateinput8,todateinput8,postinput9,instinput9,fromdateinput9,todateinput9,postinput10,instinput10,fromdateinput10,todateinput10;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_hr_experiences_tab, container, false);

        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        username=MySharedPreferencesManager.getUsername(getActivity());
        role=MySharedPreferencesManager.getRole(getActivity());

        demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        demoIVBytes = SimpleBase64Encoder.decode(digest2);
        sPadding = "ISO10126Padding";

//        savepersonal = (Button)rootView.findViewById(R.id.savepersonal);
//        personalprogress1 = (ProgressBar)rootView.findViewById(R.id.expprogress);

        trash1selectionview = (View)rootView. findViewById(R.id.trashexp1);
        trash2selectionview = (View)rootView. findViewById(R.id.trashexp2);
        trash3selectionview = (View)rootView.findViewById(R.id.trashexp3);
        trash4selectionview = (View)rootView. findViewById(R.id.trashexp4);
        trash5selectionview = (View)rootView. findViewById(R.id.trashexp5);
        trash6selectionview = (View)rootView. findViewById(R.id.trashexp6);
        trash7selectionview = (View)rootView. findViewById(R.id.trashexp7);
        trash8selectionview = (View)rootView. findViewById(R.id.trashexp8);
        trash9selectionview = (View)rootView. findViewById(R.id.trashexp9);
        trash10selectionview = (View)rootView. findViewById(R.id.trashexp10);

        addmoreexptxt=(TextView)rootView.findViewById(R.id.addmoreexptxt);
        work1=(TextView)rootView.findViewById(R.id.work1);
        work2=(TextView)rootView.findViewById(R.id.work2);
        work3=(TextView)rootView.findViewById(R.id.work3);
        work4=(TextView)rootView.findViewById(R.id.work4);
        work5=(TextView)rootView.findViewById(R.id.work5);
        work6=(TextView)rootView.findViewById(R.id.work6);
        work7=(TextView)rootView.findViewById(R.id.work7);
        work8=(TextView)rootView.findViewById(R.id.work8);
        work9=(TextView)rootView.findViewById(R.id.work9);
        work10=(TextView)rootView.findViewById(R.id.work10);

        addmoreexptxt.setTypeface(MyConstants.getBold(getActivity()));
        work1.setTypeface(MyConstants.getBold(getActivity()));
        work2.setTypeface(MyConstants.getBold(getActivity()));
        work3.setTypeface(MyConstants.getBold(getActivity()));
        work4.setTypeface(MyConstants.getBold(getActivity()));
        work5.setTypeface(MyConstants.getBold(getActivity()));
        work6.setTypeface(MyConstants.getBold(getActivity()));
        work7.setTypeface(MyConstants.getBold(getActivity()));
        work8.setTypeface(MyConstants.getBold(getActivity()));
        work9.setTypeface(MyConstants.getBold(getActivity()));
        work10.setTypeface(MyConstants.getBold(getActivity()));

        postinput1=(TextInputLayout)rootView.findViewById(R.id.postinput1);
        postinput2=(TextInputLayout)rootView.findViewById(R.id.postinput2);
        postinput3=(TextInputLayout)rootView.findViewById(R.id.postinput3);
        postinput4=(TextInputLayout)rootView.findViewById(R.id.postinput4);
        postinput5=(TextInputLayout)rootView.findViewById(R.id.postinput5);
        postinput6=(TextInputLayout)rootView.findViewById(R.id.postinput6);
        postinput7=(TextInputLayout)rootView.findViewById(R.id.postinput7);
        postinput8=(TextInputLayout)rootView.findViewById(R.id.postinput8);
        postinput9=(TextInputLayout)rootView.findViewById(R.id.postinput9);
        postinput10=(TextInputLayout)rootView.findViewById(R.id.postinput10);

        instinput1=(TextInputLayout)rootView.findViewById(R.id.instinput1);
        instinput2=(TextInputLayout)rootView.findViewById(R.id.instinput2);
        instinput3=(TextInputLayout)rootView.findViewById(R.id.instinput3);
        instinput4=(TextInputLayout)rootView.findViewById(R.id.instinput4);
        instinput5=(TextInputLayout)rootView.findViewById(R.id.instinput5);
        instinput6=(TextInputLayout)rootView.findViewById(R.id.instinput6);
        instinput7=(TextInputLayout)rootView.findViewById(R.id.instinput7);
        instinput8=(TextInputLayout)rootView.findViewById(R.id.instinput8);
        instinput9=(TextInputLayout)rootView.findViewById(R.id.instinput9);
        instinput10=(TextInputLayout)rootView.findViewById(R.id.instinput10);

        fromdateinput1=(TextInputLayout)rootView.findViewById(R.id.fromdateinput1);
        fromdateinput2=(TextInputLayout)rootView.findViewById(R.id.fromdateinput2);
        fromdateinput3=(TextInputLayout)rootView.findViewById(R.id.fromdateinput3);
        fromdateinput4=(TextInputLayout)rootView.findViewById(R.id.fromdateinput4);
        fromdateinput5=(TextInputLayout)rootView.findViewById(R.id.fromdateinput5);
        fromdateinput6=(TextInputLayout)rootView.findViewById(R.id.fromdateinput6);
        fromdateinput7=(TextInputLayout)rootView.findViewById(R.id.fromdateinput7);
        fromdateinput8=(TextInputLayout)rootView.findViewById(R.id.fromdateinput8);
        fromdateinput9=(TextInputLayout)rootView.findViewById(R.id.fromdateinput9);
        fromdateinput10=(TextInputLayout)rootView.findViewById(R.id.fromdateinput10);

        todateinput1=(TextInputLayout)rootView.findViewById(R.id.todateinput1);
        todateinput2=(TextInputLayout)rootView.findViewById(R.id.todateinput2);
        todateinput3=(TextInputLayout)rootView.findViewById(R.id.todateinput3);
        todateinput4=(TextInputLayout)rootView.findViewById(R.id.todateinput4);
        todateinput5=(TextInputLayout)rootView.findViewById(R.id.todateinput5);
        todateinput6=(TextInputLayout)rootView.findViewById(R.id.todateinput6);
        todateinput7=(TextInputLayout)rootView.findViewById(R.id.todateinput7);
        todateinput8=(TextInputLayout)rootView.findViewById(R.id.todateinput8);
        todateinput9=(TextInputLayout)rootView.findViewById(R.id.todateinput9);
        todateinput10=(TextInputLayout)rootView.findViewById(R.id.todateinput10);

        todateinput1.setTypeface(MyConstants.getLight(getActivity()));
        todateinput2.setTypeface(MyConstants.getLight(getActivity()));
        todateinput3.setTypeface(MyConstants.getLight(getActivity()));
        todateinput4.setTypeface(MyConstants.getLight(getActivity()));
        todateinput5.setTypeface(MyConstants.getLight(getActivity()));
        todateinput6.setTypeface(MyConstants.getLight(getActivity()));
        todateinput7.setTypeface(MyConstants.getLight(getActivity()));
        todateinput8.setTypeface(MyConstants.getLight(getActivity()));
        todateinput9.setTypeface(MyConstants.getLight(getActivity()));
        todateinput10.setTypeface(MyConstants.getLight(getActivity()));

        postinput1.setTypeface(MyConstants.getLight(getActivity()));
        postinput2.setTypeface(MyConstants.getLight(getActivity()));
        postinput3.setTypeface(MyConstants.getLight(getActivity()));
        postinput4.setTypeface(MyConstants.getLight(getActivity()));
        postinput5.setTypeface(MyConstants.getLight(getActivity()));
        postinput6.setTypeface(MyConstants.getLight(getActivity()));
        postinput7.setTypeface(MyConstants.getLight(getActivity()));
        postinput8.setTypeface(MyConstants.getLight(getActivity()));
        postinput9.setTypeface(MyConstants.getLight(getActivity()));
        postinput10.setTypeface(MyConstants.getLight(getActivity()));

        instinput1.setTypeface(MyConstants.getLight(getActivity()));
        instinput2.setTypeface(MyConstants.getLight(getActivity()));
        instinput3.setTypeface(MyConstants.getLight(getActivity()));
        instinput4.setTypeface(MyConstants.getLight(getActivity()));
        instinput5.setTypeface(MyConstants.getLight(getActivity()));
        instinput6.setTypeface(MyConstants.getLight(getActivity()));
        instinput7.setTypeface(MyConstants.getLight(getActivity()));
        instinput8.setTypeface(MyConstants.getLight(getActivity()));
        instinput9.setTypeface(MyConstants.getLight(getActivity()));
        instinput10.setTypeface(MyConstants.getLight(getActivity()));

        fromdateinput1.setTypeface(MyConstants.getLight(getActivity()));
        fromdateinput2.setTypeface(MyConstants.getLight(getActivity()));
        fromdateinput3.setTypeface(MyConstants.getLight(getActivity()));
        fromdateinput4.setTypeface(MyConstants.getLight(getActivity()));
        fromdateinput5.setTypeface(MyConstants.getLight(getActivity()));
        fromdateinput6.setTypeface(MyConstants.getLight(getActivity()));
        fromdateinput7.setTypeface(MyConstants.getLight(getActivity()));
        fromdateinput8.setTypeface(MyConstants.getLight(getActivity()));
        fromdateinput9.setTypeface(MyConstants.getLight(getActivity()));
        fromdateinput10.setTypeface(MyConstants.getLight(getActivity()));

        //Edittext
        post1 = (EditText)rootView. findViewById(R.id.post1);
        post2 = (EditText)rootView. findViewById(R.id.post2);
        post3 = (EditText)rootView. findViewById(R.id.post3);
        post4 = (EditText)rootView. findViewById(R.id.post4);
        post5 = (EditText)rootView. findViewById(R.id.post5);
        post6 = (EditText)rootView. findViewById(R.id.post6);
        post7 = (EditText)rootView. findViewById(R.id.post7);
        post8 = (EditText)rootView. findViewById(R.id.post8);
        post9 = (EditText)rootView. findViewById(R.id.post9);
        post10 = (EditText)rootView. findViewById(R.id.post10);

        inst11 = (EditText)rootView. findViewById(R.id.inst1);
        inst12 = (EditText)rootView. findViewById(R.id.inst2);
        inst13 = (EditText)rootView. findViewById(R.id.inst3);
        inst14 = (EditText)rootView. findViewById(R.id.inst4);
        inst15 = (EditText)rootView. findViewById(R.id.inst5);
        inst16 = (EditText)rootView. findViewById(R.id.inst6);
        inst17 = (EditText)rootView. findViewById(R.id.inst7);
        inst18 = (EditText)rootView. findViewById(R.id.inst8);
        inst19 = (EditText)rootView. findViewById(R.id.inst9);
        inst110 = (EditText)rootView. findViewById(R.id.inst10);

        post1.setTypeface(MyConstants.getBold(getActivity()));
        post2.setTypeface(MyConstants.getBold(getActivity()));
        post3.setTypeface(MyConstants.getBold(getActivity()));
        post4.setTypeface(MyConstants.getBold(getActivity()));
        post5.setTypeface(MyConstants.getBold(getActivity()));
        post6.setTypeface(MyConstants.getBold(getActivity()));
        post7.setTypeface(MyConstants.getBold(getActivity()));
        post8.setTypeface(MyConstants.getBold(getActivity()));
        post9.setTypeface(MyConstants.getBold(getActivity()));
        post10.setTypeface(MyConstants.getBold(getActivity()));

        inst11.setTypeface(MyConstants.getBold(getActivity()));
        inst12.setTypeface(MyConstants.getBold(getActivity()));
        inst13.setTypeface(MyConstants.getBold(getActivity()));
        inst14.setTypeface(MyConstants.getBold(getActivity()));
        inst15.setTypeface(MyConstants.getBold(getActivity()));
        inst16.setTypeface(MyConstants.getBold(getActivity()));
        inst17.setTypeface(MyConstants.getBold(getActivity()));
        inst18.setTypeface(MyConstants.getBold(getActivity()));
        inst19.setTypeface(MyConstants.getBold(getActivity()));
        inst110.setTypeface(MyConstants.getBold(getActivity()));


        switch1 = (SwitchCompat)rootView. findViewById(R.id.switch1);
        switch2 = (SwitchCompat)rootView. findViewById(R.id.switch2);
        switch3 = (SwitchCompat)rootView. findViewById(R.id.switch3);
        switch4 = (SwitchCompat)rootView. findViewById(R.id.switch4);
        switch5 = (SwitchCompat)rootView. findViewById(R.id.switch5);
        switch6 = (SwitchCompat)rootView. findViewById(R.id.switch6);
        switch7 = (SwitchCompat)rootView. findViewById(R.id.switch7);
        switch8 = (SwitchCompat)rootView. findViewById(R.id.switch8);
        switch9 = (SwitchCompat)rootView. findViewById(R.id.switch9);
        switch10 = (SwitchCompat)rootView. findViewById(R.id.switch10);

        switch1.setChecked(false);

        fromdate1=(EditText)rootView.findViewById(R.id.fromdate1);
        todate1=(EditText)rootView.findViewById(R.id.todate1);
        fromdate2=(EditText)rootView.findViewById(R.id.fromdate2);
        todate2=(EditText)rootView.findViewById(R.id.todate2);
        fromdate3=(EditText)rootView.findViewById(R.id.fromdate3);
        todate3=(EditText)rootView.findViewById(R.id.todate3);
        fromdate4=(EditText)rootView.findViewById(R.id.fromdate4);
        todate4=(EditText)rootView.findViewById(R.id.todate4);
        fromdate5=(EditText)rootView.findViewById(R.id.fromdate5);
        todate5=(EditText)rootView.findViewById(R.id.todate5);
        fromdate6=(EditText)rootView.findViewById(R.id.fromdate6);
        todate6=(EditText)rootView.findViewById(R.id.todate6);
        fromdate7=(EditText)rootView.findViewById(R.id.fromdate7);
        todate7=(EditText)rootView.findViewById(R.id.todate7);
        fromdate8=(EditText)rootView.findViewById(R.id.fromdate8);
        todate8=(EditText)rootView.findViewById(R.id.todate8);
        fromdate9=(EditText)rootView.findViewById(R.id.fromdate9);
        todate9=(EditText)rootView.findViewById(R.id.todate9);
        fromdate10=(EditText)rootView.findViewById(R.id.fromdate10);
        todate10=(EditText)rootView.findViewById(R.id.todate10);


        fromdate1.setTypeface(MyConstants.getBold(getActivity()));
        todate1.setTypeface(MyConstants.getBold(getActivity()));
        fromdate2.setTypeface(MyConstants.getBold(getActivity()));
        todate2.setTypeface(MyConstants.getBold(getActivity()));
        fromdate3.setTypeface(MyConstants.getBold(getActivity()));
        todate3.setTypeface(MyConstants.getBold(getActivity()));
        fromdate4.setTypeface(MyConstants.getBold(getActivity()));
        todate4.setTypeface(MyConstants.getBold(getActivity()));
        fromdate5.setTypeface(MyConstants.getBold(getActivity()));
        todate5.setTypeface(MyConstants.getBold(getActivity()));
        fromdate6.setTypeface(MyConstants.getBold(getActivity()));
        todate6.setTypeface(MyConstants.getBold(getActivity()));
        fromdate7.setTypeface(MyConstants.getBold(getActivity()));
        todate7.setTypeface(MyConstants.getBold(getActivity()));
        fromdate8.setTypeface(MyConstants.getBold(getActivity()));
        todate8.setTypeface(MyConstants.getBold(getActivity()));
        fromdate9.setTypeface(MyConstants.getBold(getActivity()));
        todate9.setTypeface(MyConstants.getBold(getActivity()));
        fromdate10.setTypeface(MyConstants.getBold(getActivity()));
        todate10.setTypeface(MyConstants.getBold(getActivity()));

        TextView exptxt=(TextView)rootView.findViewById(R.id.exptxt);
        exptxt.setTypeface(MyConstants.getBold(getActivity()));

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
                    View v = (View)rootView. findViewById(R.id.todaterl1);
                    todate1.setText("");
                    v.setVisibility(View.INVISIBLE);

                    Log.d(HRlog, "blnswitch staus check- " + blnswitch1);
                } else {
                    //status="false";
                    blnswitch1 = false;
                    View v = (View)rootView.findViewById(R.id.todaterl1);
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
                    View v = (View)rootView.findViewById(R.id.todaterl2);
                    todate2.setText("");
                    v.setVisibility(View.INVISIBLE);
                } else {
                    //status="false";
                    blnswitch2 = false;
                    View v = (View)rootView. findViewById(R.id.todaterl2);
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
                    View v = (View)rootView.findViewById(R.id.todaterl3);
                    todate3.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View)rootView. findViewById(R.id.todaterl3);
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
                    View v = (View)rootView. findViewById(R.id.todaterl4);
                    todate4.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View)rootView.findViewById(R.id.todaterl4);
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
                    View v = (View)rootView.findViewById(R.id.todaterl5);
                    todate5.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View)rootView.findViewById(R.id.todaterl5);
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
                    View v = (View)rootView.findViewById(R.id.todaterl6);
                    todate6.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View)rootView.findViewById(R.id.todaterl6);
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
                    View v = (View)rootView.findViewById(R.id.todaterl7);
                    todate7.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View)rootView.findViewById(R.id.todaterl7);
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
                    View v = (View)rootView.findViewById(R.id.todaterl8);
                    todate8.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View)rootView.findViewById(R.id.todaterl8);
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
                    View v = (View)rootView.findViewById(R.id.todaterl9);
                    todate9.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View)rootView.findViewById(R.id.todaterl9);
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
                    View v = (View)rootView.findViewById(R.id.todaterl10);
                    todate10.setText("");
                    v.setVisibility(View.INVISIBLE);

                } else {
                    //status="false";
                    View v = (View)rootView.findViewById(R.id.todaterl10);
                    v.setVisibility(View.VISIBLE);
                    blnswitch10 = false;
                }

            }
        });
        //
        post1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                postinput1.setError(null);
                edittedFlag = 1;
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
                postinput2.setError(null);
                edittedFlag = 1;
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
                postinput3.setError(null);
                edittedFlag = 1;
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
                postinput4.setError(null);
                edittedFlag = 1;
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
                postinput5.setError(null);
                edittedFlag = 1;
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
                postinput6.setError(null);
                edittedFlag = 1;
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
                postinput7.setError(null);
                edittedFlag = 1;
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
                postinput8.setError(null);
                edittedFlag = 1;
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
                postinput9.setError(null);
                edittedFlag = 1;
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
                postinput10.setError(null);
                edittedFlag = 1;
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
                instinput1.setError(null);
                edittedFlag = 1;
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
                instinput2.setError(null);
                edittedFlag = 1;
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
                instinput3.setError(null);
                edittedFlag = 1;
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
                instinput4.setError(null);
                edittedFlag = 1;
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
                instinput5.setError(null);
                edittedFlag = 1;
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
                instinput6.setError(null);
                edittedFlag = 1;
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
                instinput7.setError(null);
                edittedFlag = 1;
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
                instinput8.setError(null);
                edittedFlag = 1;
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
                instinput9.setError(null);
                edittedFlag = 1;
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
                instinput10.setError(null);
                edittedFlag = 1;
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
                edittedFlag = 1;
                fromdateinput1.setError(null);
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

        posts1 = a.getPost1e();
        posts2 = a.getPost2e();
        posts3 = a.getPost3e();
        posts4 = a.getPost4e();
        posts5 = a.getPost5e();
        posts6 = a.getPost6e();
        posts7 = a.getPost7e();
        posts8 = a.getPost8e();
        posts9 = a.getPost9e();
        posts10 = a.getPost10e();

        inst1s1 = a.getInst1e();
        inst1s2 = a.getInst2e();
        inst1s3 = a.getInst3e();
        inst1s4 = a.getInst4e();
        inst1s5 = a.getInst5e();
        inst1s6 = a.getInst6e();
        inst1s7 = a.getInst7e();
        inst1s8 = a.getInst8e();
        inst1s9 = a.getInst9e();
        inst1s10 = a.getInst10e();

        fromdates1 = a.getFromdate1e();
        fromdates2 = a.getFromdate2e();
        fromdates3 = a.getFromdate3e();
        fromdates4 = a.getFromdate4e();
        fromdates5 = a.getFromdate5e();
        fromdates6 = a.getFromdate6e();
        fromdates7 = a.getFromdate7e();
        fromdates8 = a.getFromdate8e();
        fromdates9 = a.getFromdate9e();
        fromdates10 = a.getFromdate10e();

        todates1 = a.getTodate1e();
        todates2 = a.getTodate2e();
        todates3 = a.getTodate3e();
        todates4 = a.getTodate4e();
        todates5 = a.getTodate5e();
        todates6 = a.getTodate6e();
        todates7 = a.getTodate7e();
        todates8 = a.getTodate8e();
        todates9 = a.getTodate9e();
        todates10 = a.getTodate10e();


        if(posts1==null)
           posts1="";
        if(inst1s1==null)
            inst1s1="";
        if(fromdates1==null)
            fromdates1="";
        if(todates1==null)
            todates1="";

        if(posts2==null)
            posts2="";
        if(inst1s2==null)
            inst1s2="";
        if(fromdates2==null)
            fromdates2="";
        if(todates2==null)
            todates2="";

        if(posts3==null)
            posts3="";
        if(inst1s3==null)
            inst1s3="";
        if(fromdates3==null)
            fromdates3="";
        if(todates3==null)
            todates3="";

        if(posts4==null)
            posts4="";
        if(inst1s4==null)
            inst1s4="";
        if(fromdates4==null)
            fromdates4="";
        if(todates4==null)
            todates4="";

        if(posts5==null)
            posts5="";
        if(inst1s5==null)
            inst1s5="";
        if(fromdates5==null)
            fromdates5="";
        if(todates5==null)
            todates5="";

        if(posts6==null)
            posts6="";
        if(inst1s6==null)
            inst1s6="";
        if(fromdates6==null)
            fromdates6="";
        if(todates6==null)
            todates6="";

        if(posts7==null)
            posts7="";
        if(inst1s7==null)
            inst1s7="";
        if(fromdates7==null)
            fromdates7="";
        if(todates7==null)
            todates7="";

        if(posts8==null)
            posts8="";
        if(inst1s8==null)
            inst1s8="";
        if(fromdates8==null)
            fromdates8="";
        if(todates8==null)
            todates8="";

        if(posts9==null)
            posts9="";
        if(inst1s9==null)
            inst1s9="";
        if(fromdates9==null)
            fromdates9="";
        if(todates9==null)
            todates9="";

        if(posts10==null)
            posts10="";
        if(inst1s10==null)
            inst1s10="";
        if(fromdates10==null)
            fromdates10="";
        if(todates10==null)
            todates10="";



//        posts1 = hr.getPosts1();
//        posts2 = hr.getPosts2();
//        posts3 = hr.getPosts3();
//        posts4 = hr.getPosts4();
//        posts5 = hr.getPosts5();
//        posts6 = hr.getPosts6();
//        posts7 = hr.getPosts7();
//        posts8 = hr.getPosts8();
//        posts9 = hr.getPosts9();
//        posts10 = hr.getPosts10();
//
//        inst1s1 = hr.getInst1s1();
//        inst1s2 = hr.getInst1s2();
//
//        inst1s3 = hr.getInst1s3();
//        inst1s4 = hr.getInst1s4();
//        inst1s5 = hr.getInst1s5();
//        inst1s6 = hr.getInst1s6();
//
//        inst1s7 = hr.getInst1s7();
//        inst1s8 = hr.getInst1s8();
//        inst1s9 = hr.getInst1s9();
//        inst1s10 = hr.getInst1s10();
//
//        fromdates1 = hr.getFromdates1();
//        fromdates2 = hr.getFromdates2();
//        fromdates3 = hr.getFromdates3();
//        fromdates4 = hr.getFromdates4();
//        fromdates5 = hr.getFromdates5();
//        fromdates6 = hr.getFromdates6();
//
//
//        fromdates7 = hr.getFromdates7();
//        fromdates8 = hr.getFromdates8();
//        fromdates9 = hr.getFromdates9();
//        fromdates10 = hr.getFromdates10();
//
//        todates1 = hr.getTodates1();
//        todates2 = hr.getTodates2();
//        todates3 = hr.getTodates3();
//        todates4 = hr.getTodates4();
//        todates5 = hr.getTodates5();
//        todates6 = hr.getTodates6();
//        todates7 = hr.getTodates7();
//        todates8 = hr.getTodates8();
//        todates9 = hr.getTodates9();
//        todates10 = hr.getTodates10();

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
        if (!posts2.equals("")) {
            post2.setText(posts2);
            inst12.setText(inst1s2);
            fromdate2.setText(fromdates2);
            if (!todates2.equals(""))
                todate2.setText(todates2);
            else {
                switch2.setChecked(true);
            }

            View v = (View)rootView.findViewById(R.id.expline1);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout)rootView.findViewById(R.id.exprl2);
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
            View v = (View)rootView.findViewById(R.id.expline2);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout)rootView.findViewById(R.id.exprl3);
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
            View v = (View)rootView.findViewById(R.id.expline3);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout)rootView.findViewById(R.id.exprl4);
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
            View v = (View)rootView.findViewById(R.id.expline4);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout)rootView.findViewById(R.id.exprl5);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }

        if (!posts6.equals("")) {
            post6.setText(posts6);
            inst16.setText(inst1s6);
            fromdate6.setText(fromdates6);
            if (!todates6.equals(""))
                todate6.setText(todates6);
            else {
                switch6.setChecked(true);
            }
            View v = (View)rootView.findViewById(R.id.expline5);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout)rootView.findViewById(R.id.exprl6);
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
            View v = (View)rootView.findViewById(R.id.expline6);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout)rootView.findViewById(R.id.exprl7);
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
            View v = (View)rootView.findViewById(R.id.expline7);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout)rootView.findViewById(R.id.exprl8);
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
            View v = (View)rootView.findViewById(R.id.expline8);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout)rootView.findViewById(R.id.exprl9);
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
            View v = (View)rootView.findViewById(R.id.expline9);
            v.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout1 = (RelativeLayout)rootView.findViewById(R.id.exprl10);
            relativeLayout1.setVisibility(View.VISIBLE);
            expcount++;
        }
        addmoreexp=(View)rootView.findViewById(R.id.addmoreexp);
        addmoreexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (expcount == 0) {

                    if(post1.getText().toString()!=null && inst11.getText().toString()!=null && fromdate1.getText().toString()!=null )
                    {
                        if(!post1.getText().toString().equals("") && !inst11.getText().toString().equals("") && !fromdate1.getText().toString().equals("") )
                        {
                            if(!switch1.isChecked() && todate1.getText().toString().equals("") ) {
                                Toast.makeText(getActivity(), "Please fill the first Experience", Toast.LENGTH_SHORT).show();
                            }




                            if (!switch1.isChecked()) {

                                if (todate1.getText().toString() != null) {

                                    if (!todate1.getText().toString().equals("")) {

                                        View v = (View)rootView. findViewById(R.id.expline1);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl2);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View)rootView. findViewById(R.id.expline1);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl2);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                            Toast.makeText(getActivity(), "Please fill the first Experience", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the first Experience", Toast.LENGTH_SHORT).show();

                }
                else if (expcount == 1) {
                    if(post2.getText().toString()!=null && inst12.getText().toString()!=null && fromdate2.getText().toString()!=null ) {
                        if (!post2.getText().toString().equals("") && !inst12.getText().toString().equals("") && !fromdate2.getText().toString().equals("")) {

                            if (!switch2.isChecked() && todate2.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Please fill the Second Experience", Toast.LENGTH_SHORT).show();
                            }

                            if (!switch2.isChecked()) {

                                if (todate2.getText().toString() != null) {

                                    if (!todate2.getText().toString().equals("")) {

                                        View v = (View)rootView. findViewById(R.id.expline2);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl3);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View)rootView. findViewById(R.id.expline2);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl3);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }

                        else
                        {
                            Toast.makeText(getActivity(), "Please fill the Second Experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the Second Experience", Toast.LENGTH_SHORT).show();



                }


                else if (expcount == 2) {

                    if(post3.getText().toString()!=null && inst13.getText().toString()!=null && fromdate3.getText().toString()!=null )
                    {
                        if(!post3.getText().toString().equals("") && !inst13.getText().toString().equals("") && !fromdate3.getText().toString().equals("") )
                        {
                            if (!switch3.isChecked() && todate3.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Please fill the Third Experience", Toast.LENGTH_SHORT).show();
                            }

                            if (!switch3.isChecked()) {
                                if (todate3.getText().toString() != null) {
                                    if (!todate3.getText().toString().equals("")) {
                                        View v = (View)rootView. findViewById(R.id.expline3);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl4);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;

                                    }
                                }
                            } else {

                                View v = (View)rootView. findViewById(R.id.expline3);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl4);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                            Toast.makeText(getActivity(), "Please fill the Third Experience", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the Third Experience", Toast.LENGTH_SHORT).show();


                }
                else if (expcount == 3) {
                    if(post4.getText().toString()!=null && inst14.getText().toString()!=null && fromdate4.getText().toString()!=null )
                    {
                        if(!post4.getText().toString().equals("") && !inst14.getText().toString().equals("") && !fromdate4.getText().toString().equals("") )
                        {
                            if (!switch4.isChecked() && todate4.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Please fill the Fourth Experience", Toast.LENGTH_SHORT).show();
                            }


                            if (!switch4.isChecked()) {

                                if (todate4.getText().toString() != null) {

                                    if (!todate4.getText().toString().equals("")) {

                                        View v = (View)rootView. findViewById(R.id.expline4);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl5);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View)rootView. findViewById(R.id.expline4);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl5);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Please fill the Fourth Experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the Fourth Experience", Toast.LENGTH_SHORT).show();



                }
                else if (expcount == 4) {
                    if(post5.getText().toString()!=null && inst15.getText().toString()!=null && fromdate5.getText().toString()!=null )
                    {
                        if(!post5.getText().toString().equals("") && !inst15.getText().toString().equals("") && !fromdate5.getText().toString().equals("") )
                        {
                            if (!switch5.isChecked() && todate5.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Please fill the Fifth Experience", Toast.LENGTH_SHORT).show();
                            }

                            if (!switch5.isChecked()) {

                                if (todate5.getText().toString() != null) {

                                    if (!todate5.getText().toString().equals("")) {
                                        View v = (View)rootView. findViewById(R.id.expline5);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl6);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View)rootView. findViewById(R.id.expline5);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl6);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Please fill the Fifth Experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the Fifth Experience", Toast.LENGTH_SHORT).show();




                }

                else if (expcount == 5) {

                    if(post6.getText().toString()!=null && inst16.getText().toString()!=null && fromdate6.getText().toString()!=null )
                    {
                        if(!post6.getText().toString().equals("") && !inst16.getText().toString().equals("") && !fromdate6.getText().toString().equals("") )
                        {
                            if (!switch6.isChecked() && todate6.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Please fill the Sixth Experience", Toast.LENGTH_SHORT).show();
                            }



                            if (!switch6.isChecked()) {

                                if (todate6.getText().toString() != null) {

                                    if (!todate6.getText().toString().equals("")) {
                                        View v = (View)rootView. findViewById(R.id.expline6);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl7);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View)rootView. findViewById(R.id.expline6);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl7);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Please fill the Sixth Experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the Sixth Experience", Toast.LENGTH_SHORT).show();



                }
                else if (expcount == 6) {

                    if(post7.getText().toString()!=null && inst17.getText().toString()!=null && fromdate7.getText().toString()!=null )
                    {
                        if(!post7.getText().toString().equals("") && !inst17.getText().toString().equals("") && !fromdate7.getText().toString().equals("") )
                        {
                            if (!switch7.isChecked() && todate7.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Please fill the Seventh Experience", Toast.LENGTH_SHORT).show();
                            }



                            if (!switch7.isChecked()) {

                                if (todate7.getText().toString() != null) {

                                    if (!todate7.getText().toString().equals("")) {
                                        View v = (View)rootView. findViewById(R.id.expline7);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl8);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View)rootView. findViewById(R.id.expline7);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl8);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Please fill the Seventh Experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the Seventh Experience", Toast.LENGTH_SHORT).show();


                } else if (expcount == 7) {

                    if(post8.getText().toString()!=null && inst18.getText().toString()!=null && fromdate8.getText().toString()!=null )
                    {
                        if(!post8.getText().toString().equals("") && !inst18.getText().toString().equals("") && !fromdate8.getText().toString().equals("") )
                        {
                            if (!switch8.isChecked() && todate8.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Please fill the Eighth Experience", Toast.LENGTH_SHORT).show();
                            }



                            if (!switch8.isChecked()) {

                                if (todate8.getText().toString() != null) {

                                    if (!todate8.getText().toString().equals("")) {
                                        View v = (View)rootView. findViewById(R.id.expline8);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl9);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                    }
                                }
                            } else {
                                View v = (View)rootView. findViewById(R.id.expline8);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl9);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Please fill the Eighth Experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the Eighth Experience", Toast.LENGTH_SHORT).show();


                }
                else if (expcount == 8) {
                    if(post9.getText().toString()!=null && inst19.getText().toString()!=null && fromdate9.getText().toString()!=null )
                    {
                        if(!post9.getText().toString().equals("") && !inst19.getText().toString().equals("") && !fromdate9.getText().toString().equals("") )
                        {
                            if (!switch9.isChecked() && todate9.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Please fill the Nineth Experience", Toast.LENGTH_SHORT).show();
                            }

                            if (!switch9.isChecked()) {

                                if (todate9.getText().toString() != null) {

                                    if (!todate9.getText().toString().equals("")) {
                                        View v = (View)rootView. findViewById(R.id.expline9);
                                        v.setVisibility(View.VISIBLE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl10);
                                        relativeLayout1.setVisibility(View.VISIBLE);
                                        expcount++;
                                        TextView t = (TextView)rootView. findViewById(R.id.addmoreexptxt);
                                        ImageView i = (ImageView)rootView. findViewById(R.id.addmoreexpimg);

                                        View line10 = (View) rootView.findViewById(R.id.expline10);
                                        line10.setVisibility(View.GONE);
                                        addmoreexp.setVisibility(View.GONE);

                                        t.setVisibility(View.GONE);
                                        i.setVisibility(View.GONE);
                                    }
                                }
                            } else {
                                View v = (View)rootView. findViewById(R.id.expline9);
                                v.setVisibility(View.VISIBLE);

                                RelativeLayout relativeLayout1 = (RelativeLayout)rootView. findViewById(R.id.exprl10);
                                relativeLayout1.setVisibility(View.VISIBLE);
                                expcount++;
                                TextView t = (TextView)rootView. findViewById(R.id.addmoreexptxt);
                                ImageView i = (ImageView)rootView. findViewById(R.id.addmoreexpimg);

                                View line10 = (View)rootView. findViewById(R.id.expline10);
                                line10.setVisibility(View.GONE);
                                addmoreexp.setVisibility(View.GONE);

                                t.setVisibility(View.GONE);
                                i.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Please fill the Nineth Experience", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill the Nineth Experience", Toast.LENGTH_SHORT).show();


                }

            }
        });

//        savepersonal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                savepersonal.setVisibility(View.GONE);
//                personalprogress1.setVisibility(View.VISIBLE);
//                validateAndSaveData();
//            }
//        });
        edittedFlag=0;
        return rootView;
    }
    void showDateDialog(final EditText id, boolean isFromDateSelected, final int fromYear, final String fromMonth, final String todate) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
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

        monthView.setWheelAdapter(new ArrayWheelAdapter(getActivity()));
        monthView.setWheelData(monthList);
        yearView.setWheelAdapter(new ArrayWheelAdapter(getActivity()));
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
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
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
    void setMonthYear(EditText id, String selectedMonth, String selectedYear, boolean isValid) {
        id.setError(null);
        if (isValid == true) {
            id.setText(selectedMonth + ", " + selectedYear);
        } else {
            id.setError("Kindly choose valid date");
            Toast.makeText(getActivity(), "Kindly enter valid date", Toast.LENGTH_SHORT).show();
            id.setText("");
        }
    }
    void showDeletDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder
                .setMessage("Do you want to delete this project ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag = 1;
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
        View v = (View)rootView.findViewById(R.id.expline9);
        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View)rootView.findViewById(R.id.expline9);
            v1.setVisibility(View.GONE);

            RelativeLayout langrl = (RelativeLayout)rootView.findViewById(R.id.exprl10);
            langrl.setVisibility(View.GONE);

            expcount--;

            TextView t = (TextView)rootView.findViewById(R.id.addmoreexptxt);
            ImageView i = (ImageView)rootView.findViewById(R.id.addmoreexpimg);
            addmoreexp.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        } else {
            v = (View)rootView.findViewById(R.id.expline8);
            if (v.getVisibility() == View.VISIBLE) {


                View v1 = (View)rootView.findViewById(R.id.expline8);
                v1.setVisibility(View.GONE);

                RelativeLayout langrl = (RelativeLayout)rootView.findViewById(R.id.exprl9);
                langrl.setVisibility(View.GONE);

                expcount--;

            } else {
                v = (View)rootView.findViewById(R.id.expline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View)rootView.findViewById(R.id.expline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout langrl = (RelativeLayout)rootView.findViewById(R.id.exprl8);
                    langrl.setVisibility(View.GONE);

                    expcount--;
                } else {
                    v = (View)rootView.findViewById(R.id.expline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View)rootView.findViewById(R.id.expline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout langrl = (RelativeLayout)rootView.findViewById(R.id.exprl7);
                        langrl.setVisibility(View.GONE);

                        expcount--;

                    } else {
                        v = (View)rootView.findViewById(R.id.expline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View)rootView.findViewById(R.id.expline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout langrl = (RelativeLayout)rootView.findViewById(R.id.exprl6);
                            langrl.setVisibility(View.GONE);

                            expcount--;


                        } else {
                            v = (View)rootView.findViewById(R.id.expline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View)rootView.findViewById(R.id.expline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout langrl = (RelativeLayout)rootView.findViewById(R.id.exprl5);
                                langrl.setVisibility(View.GONE);

                                expcount--;
                            } else {
                                v = (View)rootView.findViewById(R.id.expline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View)rootView.findViewById(R.id.expline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout langrl = (RelativeLayout)rootView.findViewById(R.id.exprl4);
                                    langrl.setVisibility(View.GONE);

                                    expcount--;

                                } else {
                                    v = (View)rootView.findViewById(R.id.expline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View)rootView.findViewById(R.id.expline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout langrl = (RelativeLayout)rootView.findViewById(R.id.exprl3);
                                        langrl.setVisibility(View.GONE);

                                        expcount--;

                                    } else {
                                        v = (View)rootView.findViewById(R.id.expline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1 = (View) rootView.findViewById(R.id.expline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout langrl = (RelativeLayout)rootView.findViewById(R.id.exprl2);
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
            postinput1.setError("Kindly enter valid position");
        } else if (posts1.length() < 2) {
            errorflag = 1;
            postinput1.setError("Kindly enter valid position");
        } else if (inst1s1.equals("")) {
            errorflag = 1;
            instinput1.setError("Kindly enter valid institute");
        } else if (inst1s1.length() < 2) {
            errorflag = 1;
            instinput1.setError("Kindly enter valid institute");
        } else if (fromdates1.equals("")) {
            errorflag = 1;
            fromdateinput1.setError("Enter From date");
        } else if (blnswitch1 == false) {
            if (todates1.equals("")) {
                errorflag = 1;
                todateinput1.setError("Enter To date");
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
            postinput2.setError("Kindly enter valid position");
        } else if (posts2.length() < 2) {
            errorflag = 1;
            postinput2.setError("Kindly enter valid position");
        } else if (inst1s2.equals("")) {
            errorflag = 1;
            instinput2.setError("Kindly enter valid institute");
        } else if (inst1s2.length() < 2) {
            errorflag = 1;
            instinput2.setError("Kindly enter valid institute");
        } else if (fromdates2.equals("")) {
            errorflag = 1;
            fromdateinput2.setError("Enter From date");
        } else if (blnswitch2 == false) {
            if (todates2.equals("")) {
                errorflag = 1;
                todateinput2.setError("Enter To date");
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
            postinput3.setError("Kindly enter valid position");
        } else if (posts3.length() < 2) {
            errorflag = 1;
            postinput3.setError("Kindly enter valid position");
        } else if (inst1s3.equals("")) {
            errorflag = 1;
            instinput3.setError("Kindly enter valid institute");
        } else if (inst1s3.length() < 2) {
            errorflag = 1;
            instinput3.setError("Kindly enter valid institute");
        } else if (fromdates3.equals("")) {
            errorflag = 1;
            fromdateinput3.setError("Enter From date");
        } else if (blnswitch3 == false) {
            if (todates3.equals("")) {
                errorflag = 1;
                todateinput3.setError("Enter To date");
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
            postinput4.setError("Kindly enter valid position");
        } else if (posts4.length() < 2) {
            errorflag = 1;
            postinput4.setError("Kindly enter valid position");
        } else if (inst1s4.equals("")) {
            errorflag = 1;
            instinput4.setError("Kindly enter valid institute");
        } else if (inst1s4.length() < 2) {
            errorflag = 1;
            instinput4.setError("Kindly enter valid institute");
        } else if (fromdates4.equals("")) {
            errorflag = 1;
            fromdateinput4.setError("Enter From date");
        } else if (blnswitch4 == false) {
            if (todates4.equals("")) {
                errorflag = 1;
                todateinput4.setError("Enter To date");
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
            postinput5.setError("Kindly enter valid position");
        } else if (posts5.length() < 2) {
            errorflag = 1;
            postinput5.setError("Kindly enter valid position");
        } else if (inst1s5.equals("")) {
            errorflag = 1;
            instinput5.setError("Kindly enter valid institute");
        } else if (inst1s5.length() < 2) {
            errorflag = 1;
            instinput5.setError("Kindly enter valid institute");
        } else if (fromdates5.equals("")) {
            errorflag = 1;
            fromdateinput5.setError("Enter From date");
        } else if (blnswitch5 == false) {
            if (todates5.equals("")) {
                errorflag = 1;
                todateinput5.setError("Enter To date");
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
            postinput6.setError("Kindly enter valid position");
        } else if (posts6.length() < 2) {
            errorflag = 1;
            postinput6.setError("Kindly enter valid position");
        } else if (inst1s6.equals("")) {
            errorflag = 1;
            instinput6.setError("Kindly enter valid institute");
        } else if (inst1s6.length() < 2) {
            errorflag = 1;
            instinput6.setError("Kindly enter valid institute");
        } else if (fromdates6.equals("")) {
            errorflag = 1;
            fromdateinput6.setError("Enter From date");
        } else if (blnswitch6 == false) {
            if (todates6.equals("")) {
                errorflag = 1;
                todateinput6.setError("Enter To date");
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
            postinput7.setError("Kindly enter valid position");
        } else if (posts7.length() < 2) {
            errorflag = 1;
            postinput7.setError("Kindly enter valid position");
        } else if (inst1s7.equals("")) {
            errorflag = 1;
            instinput7.setError("Kindly enter valid institute");
        } else if (inst1s7.length() < 2) {
            errorflag = 1;
            instinput7.setError("Kindly enter valid institute");
        } else if (fromdates7.equals("")) {
            errorflag = 1;
            fromdateinput7.setError("Enter From date");
        } else if (blnswitch7 == false) {
            if (todates7.equals("")) {
                errorflag = 1;
                todateinput7.setError("Enter To date");
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
            postinput8.setError("Kindly enter valid position");
        } else if (posts8.length() < 2) {
            errorflag = 1;
            postinput8.setError("Kindly enter valid position");
        } else if (inst1s8.equals("")) {
            errorflag = 1;
            instinput8.setError("Kindly enter valid institute");
        } else if (inst1s8.length() < 2) {
            errorflag = 1;
            instinput8.setError("Kindly enter valid institute");
        } else if (fromdates8.equals("")) {
            errorflag = 1;
            fromdateinput8.setError("Enter From date");
        } else if (blnswitch8 == false) {
            if (todates8.equals("")) {
                errorflag = 1;
                todateinput8.setError("Enter To date");
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
            postinput9.setError("Kindly enter valid position");
        } else if (posts9.length() < 2) {
            errorflag = 1;
            postinput9.setError("Kindly enter valid position");
        } else if (inst1s9.equals("")) {
            errorflag = 1;
            instinput9.setError("Kindly enter valid institute");
        } else if (inst1s9.length() < 2) {
            errorflag = 1;
            instinput9.setError("Kindly enter valid institute");
        } else if (fromdates9.equals("")) {
            errorflag = 1;
            fromdateinput9.setError("Enter From date");
        } else if (blnswitch9 == false) {
            if (todates9.equals("")) {
                errorflag = 1;
                todateinput9.setError("Enter To date");
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
            postinput10.setError("Kindly enter valid position");
        } else if (posts10.length() < 2) {
            errorflag = 1;
            postinput10.setError("Kindly enter valid position");
        } else if (inst1s10.equals("")) {
            errorflag = 1;
            instinput10.setError("Kindly enter valid institute");
        } else if (inst1s10.length() < 2) {
            errorflag = 1;
            instinput10.setError("Kindly enter valid institute");
        } else if (fromdates10.equals("")) {
            errorflag = 1;
            fromdateinput10.setError("Enter From date");
        } else if (blnswitch10 == false) {
            if (todates10.equals("")) {
                errorflag = 1;
                todateinput10.setError("Enter To date");
            }
        }
        if (errorflag == 1) {
            return false;
        }
        return true;
    }


    public void save() {

        try {


            Experiences obj1=new Experiences(posts1,inst1s1,fromdates1,todates1);
            Experiences obj2=new Experiences(posts2,inst1s2,fromdates2,todates2);
            Experiences obj3=new Experiences(posts3,inst1s3,fromdates3,todates3);
            Experiences obj4=new Experiences(posts4,inst1s4,fromdates4,todates4);
            Experiences obj5=new Experiences(posts5,inst1s5,fromdates5,todates5);
            Experiences obj6=new Experiences(posts6,inst1s6,fromdates6,todates6);
            Experiences obj7=new Experiences(posts7,inst1s7,fromdates7,todates7);
            Experiences obj8=new Experiences(posts8,inst1s8,fromdates8,todates8);
            Experiences obj9=new Experiences(posts9,inst1s9,fromdates9,todates9);
            Experiences obj10=new Experiences(posts10,inst1s10,fromdates10,todates10);


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

             encObjString=OtoString(experiencesList,MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));


            new saveHrExperienceTask().execute();

        } catch (Exception e) {
            Log.d(HRlog," "+e.getMessage());
        }
//        new saveHrExperienceTask().execute();
    }
    public boolean validate()
    {
        errorflag=0;

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
            return true;
        }
        else{
            return false;
        }
    }

    class saveHrExperienceTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("u",username));      //0
            params.add(new BasicNameValuePair("d",encObjString));      //1
            json = jParser.makeHttpRequest(MyConstants.url_SaveExperiences, "GET", params);

            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("success")) {

                Toast.makeText(getActivity(),"Successfully Saved !",Toast.LENGTH_SHORT).show();

                if (role.equals("alumni"))
                    getActivity().setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("hr"))
                    getActivity().setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);

                edittedFlag=0;
            }
        }
    }

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);

        // HW layer support only exists on API 11+
        if (Build.VERSION.SDK_INT >= 11) {
            if (animation == null && nextAnim != 0) {
                animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
            }

            if (animation != null) {
                getView().setLayerType(View.LAYER_TYPE_HARDWARE, null);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    public void onAnimationEnd(Animation animation) {
                        getView().setLayerType(View.LAYER_TYPE_NONE, null);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                    // ...other AnimationListener methods go here...
                });
            }
        }

        return animation;
    }


}
