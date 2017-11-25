package placeme.octopusites.com.placeme;


import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.modal.AlumniIntroModal;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;
import static placeme.octopusites.com.placeme.JSONParser.json;

public class MyProfileAlumniFragment extends Fragment {
    ImageView insti4 ,exp2, exp3;
    CircleImageView myprofileimg;
    ImageView introedit,eduedit,projectsedit,accomplishmentsedit,expedit,careeredit,contactedit;


    String fname = "", lname = "", country = "", state = "", city = "", dataobject = "";
    String marks="",outof="",percentage="",schoolname="",board="",yearofpassing="";
    String marks12="",outof12="",percentage12="",schoolname12="",board12="",stream12="",yearofpassing12="",markssem1diploma="",outofsem1diploma="",percentagesem1diploma="",markssem2diploma="",outofsem2diploma="",percentagesem2diploma="",markssem3diploma="",outofsem3diploma="",percentagesem3diploma="",markssem4diploma="",outofsem4diploma="",percentagesem4diploma="",markssem5diploma="",outofsem5diploma="",percentagesem5diploma="",markssem6diploma="",outofsem6diploma="",percentagesem6diploma="",aggregatediploma="",coursediploma="",streamdiploma="",universitydiploma="",collegenamediploma="",yearofpassingdiploma="";
    String markssem1ug="",outofsem1ug="",percentagesem1ug="",markssem2ug="",outofsem2ug="",percentagesem2ug="",markssem3ug="",outofsem3ug="",percentagesem3ug="",markssem4ug="",outofsem4ug="",percentagesem4ug="",markssem5ug="",outofsem5ug="",percentagesem5ug="",markssem6ug="",outofsem6ug="",percentagesem6ug="",markssem7ug="",outofsem7ug="",percentagesem7ug="",markssem8ug="",outofsem8ug="",percentagesem8ug="",aggregateug="",courseug="",streamug="",universityug="",collegenameug="",yearofpassingug="";
    String markssem1pgsem="",outofsem1pgsem="",percentagesem1pgsem="",markssem2pgsem="",outofsem2pgsem="",percentagesem2pgsem="",markssem3pgsem="",outofsem3pgsem="",percentagesem3pgsem="",markssem4pgsem="",outofsem4pgsem="",percentagesem4pgsem="",markssem5pgsem="",outofsem5pgsem="",percentagesem5pgsem="",markssem6pgsem="",outofsem6pgsem="",percentagesem6pgsem="",aggregatepgsem="",coursepgsem="",streampgsem="",universitypgsem="",collegenamepgsem="",yearofpassingpgsem="";
    String marksyear1pgyear="",outofyear1pgyear="",percentageyear1pgyear="",marksyear2pgyear="",outofyear2pgyear="",percentageyear2pgyear="",marksyear3pgyear="",outofyear3pgyear="",percentageyear3pgyear="",aggregatepgyear="",coursepgyear="",streampgyear="",universitypgyear="",collegenamepgyear="",yearofpassingpgyear="";

    String nameasten="",mothername="",dob="",gender="",mothertongue="",hobbies="",bloodgroup="",category="",religion="",caste="",prn="",paddrline1="",paddrline2="",paddrline3="",handicapped="",sports="",defenceex="";
    String posts1 = "", posts2 = "", posts3 = "", posts4 = "", posts5 = "", posts6 = "", posts7 = "", posts8 = "", posts9 = "", posts10 = "";
    String inst1s1 = "", inst1s2 = "", inst1s3 = "", inst1s4 = "", inst1s5 = "", inst1s6 = "", inst1s7 = "", inst1s8 = "", inst1s9 = "", inst1s10 = "";
    String fromdates1 = "", todates1 = "", fromdates2 = "", todates2 = "", fromdates3 = "", todates3 = "", fromdates4 = "", todates4 = "", fromdates5 = "", todates5 = "", fromdates6 = "", todates6 = "", fromdates7 = "", todates7 = "", fromdates8 = "", todates8 = "", fromdates9 = "", todates9 = "", fromdates10 = "", todates10 = "";
    String username="",resultofop="";
    final static CharSequence[] items = {"View Profile Picture", "Update Profile Picture","Delete Profile Picture"};
    private String signature = "";
    String digest1, digest2, plainusername = "";
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public String role = "";
    public static  final String alumniLog = "alumniLog";
    private String mname="";


    boolean hrinfobox1 = false, hrinfobox2 = false, hrinfobox3 = false;
    int found_exp = 0,found_personal=0;;
    int found_box1=0,found_tenth=0,found_twelth=0,found_diploma=0,found_ug=0,found_pgsem=0,found_pgyear=0;
    int found_intro_box = 0,found_tenth_box = 0;
    int percentProfile = 0;

    TextView myprofilename,myprofilrole,myprofiledu,myprofilloc,myprofilemail,myprofilepercenttxt,editprofiletxt,eduboxtxt,projboxtxt,accomplishmentsboxtxt,careerboxtxt,contactboxtxt,myprofilecource,myprofilecource2,myprofilecource3;
    TextView myprofilecource4,myprofileproj1,myprofileproj2,myprofileproj3,acc1txt,acc2txt,acc3txt,acc4txt,acc5txt,acc6txt,acc7txt,careerobjtxt,strengthtxt,weaktxt,locpretxt,nametxt,mobiletxt,emailtxt,myprofileclgname,myprofileclgyearofpassing,myprofileclgname2,myprofileclgyearofpassing2,myprofileclgname3,myprofileclgname4,myprofileclgyearofpassing3,myprofileclgyearofpassing4,myprofiledomain1,myprofileduration1,myprofiledomain2,myprofileduration2,myprofiledomain3,myprofileduration3,careerobjtxttxt,strengthstxt,weaknessestxt,locationpreferences,contactaddr1,contactmobile,contactemail,myprofilepreview,acc1txttxt,acc2txttxt,acc3txttxt,acc4txttxt,acc5txttxt,acc6txttxt,acc7txttxt;
    TextView exp1txt, myprofileexpfromto, myprofileexp1name, myprofileexp2name, exp2txt, myprofileexpfromto2, myprofileexp3name, exp3txt, myprofileexpfromto3;

    JSONParser jParser = new JSONParser();
    JSONParser jParserlang = new JSONParser();
    JSONObject json, jsonlang;
    RelativeLayout edutab4;
    RelativeLayout editprofilerl, exptab2, exptab3;
    View rootView;
    SwipeRefreshLayout swipe_refresh_layout;
    private SharedPreferences sharedpreferences;
    private ProgressBar updateProgress;

    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding;


    //    AlumniData alumniData = new AlumniData();
    StudentData s = new StudentData();
    HrData hrData = new HrData();
    HashMap<String, Integer> hashMap;

    //    kunal
    String email2 = "", addressline1 = "", addressline2 = "", addressline3 = "", telephone = "", mobile2 = "",phone="";
    String proj1="",domain1="",team1="",duration1="",proj2="",domain2="",team2="",duration2="",proj3="",domain3="",team3="",duration3="",proj4="",domain4="",team4="",duration4="",proj5="",domain5="",team5="",duration5="",proj6="",domain6="",team6="",duration6="",proj7="",domain7="",team7="",duration7="",proj8="",domain8="",team8="",duration8="",proj9="",domain9="",team9="",duration9="",proj10="",domain10="",team10="",duration10="";
    String strength1="",strength2="",strength3="",strength4="",strength5="",strength6="",strength7="",strength8="",strength9="",strength10="";
    String weak1="",weak2="",weak3="",weak4="",weak5="",weak6="",weak7="",weak8="",weak9="",weak10="";
    String location1="",location2="",location3="",location4="",location5="",careerobj="";;
    String lang1="",proficiency1="",lang2="",proficiency2="",lang3="",proficiency3="",lang4="",proficiency4="",lang5="",proficiency5="",lang6="",proficiency6="",lang7="",proficiency7="",lang8="",proficiency8="",lang9="",proficiency9="",lang10="",proficiency10="";
    String title1="",issuer1="",license1="",title2="",issuer2="",license2="",title3="",issuer3="",license3="",title4="",issuer4="",license4="",title5="",issuer5="",license5="",title6="",issuer6="",license6="",title7="",issuer7="",license7="",title8="",issuer8="",license8="",title9="",issuer9="",license9="",title10="",issuer10="",license10="",startdate1certificate="",enddate1certificate="",startdate2certificate="",enddate2certificate="",startdate3certificate="",enddate3certificate="",startdate4certificate="",enddate4certificate="",startdate5certificate="",enddate5certificate="",startdate6certificate="",enddate6certificate="",startdate7certificate="",enddate7certificate="",startdate8certificate="",enddate8certificate="",startdate9certificate="",enddate9certificate="",startdate10certificate="",enddate10certificate="",willexpire1certificate="",willexpire2certificate="",willexpire3certificate="",willexpire4certificate="",willexpire5certificate="",willexpire6certificate="",willexpire7certificate="",willexpire8certificate="",willexpire9certificate="",willexpire10certificate="";;
    String skill1="",skill2="",skill3="",skill4="",skill5="",skill6="",skill7="",skill8="",skill9="",skill10="",skill11="",skill12="",skill13="",skill14="",skill15="",skill16="",skill17="",skill18="",skill19="",skill20="",sproficiency1="",sproficiency2="",sproficiency3="",sproficiency4="",sproficiency5="",sproficiency6="",sproficiency7="",sproficiency8="",sproficiency9="",sproficiency10="",sproficiency11="",sproficiency12="",sproficiency13="",sproficiency14="",sproficiency15="",sproficiency16="",sproficiency17="",sproficiency18="",sproficiency19="",sproficiency20="";
    String htitle1="",hissuer1="",hdescription1="",htitle2="",hissuer2="",hdescription2="",htitle3="",hissuer3="",hdescription3="",htitle4="",hissuer4="",hdescription4="",htitle5="",hissuer5="",hdescription5="",htitle6="",hissuer6="",hdescription6="",htitle7="",hissuer7="",hdescription7="",htitle8="",hissuer8="",hdescription8="",htitle9="",hissuer9="",hdescription9="",htitle10="",hissuer10="",hdescription10="",yearofhonor1="",yearofhonor2="",yearofhonor3="",yearofhonor4="",yearofhonor5="",yearofhonor6="",yearofhonor7="",yearofhonor8="",yearofhonor9="",yearofhonor10="";
    String ptitle1="",pappno1="",pinventor1="",pissue1="",pfiling1="",purl1="",pdescription1="",ptitle2="",pappno2="",pinventor2="",pissue2="",pfiling2="",purl2="",pdescription2="",ptitle3="",pappno3="",pinventor3="",pissue3="",pfiling3="",purl3="",pdescription3="",ptitle4="",pappno4="",pinventor4="",pissue4="",pfiling4="",purl4="",pdescription4="",ptitle5="",pappno5="",pinventor5="",pissue5="",pfiling5="",purl5="",pdescription5="",ptitle6="",pappno6="",pinventor6="",pissue6="",pfiling6="",purl6="",pdescription6="",ptitle7="",pappno7="",pinventor7="",pissue7="",pfiling7="",purl7="",pdescription7="",ptitle8="",pappno8="",pinventor8="",pissue8="",pfiling8="",purl8="",pdescription8="",ptitle9="",pappno9="",pinventor9="",pissue9="",pfiling9="",purl9="",pdescription9="",ptitle10="",pappno10="",pinventor10="",pissue10="",pfiling10="",purl10="",pdescription10="",pselectedcountry1="",pselectedcountry2="",pselectedcountry3="",pselectedcountry4="",pselectedcountry5="",pselectedcountry6="",pselectedcountry7="",pselectedcountry8="",pselectedcountry9="",pselectedcountry10="",issuedorpending1="",issuedorpending2="",issuedorpending3="",issuedorpending4="",issuedorpending5="",issuedorpending6="",issuedorpending7="",issuedorpending8="",issuedorpending9="",issuedorpending10="";
    String pubtitle1="",publication1="",author1="",puburl1="",pubdescription1="",pubtitle2="",publication2="",author2="",puburl2="",pubdescription2="",pubtitle3="",publication3="",author3="",puburl3="",pubdescription3="",pubtitle4="",publication4="",author4="",puburl4="",pubdescription4="",pubtitle5="",publication5="",author5="",puburl5="",pubdescription5="",pubtitle6="",publication6="",author6="",puburl6="",pubdescription6="",pubtitle7="",publication7="",author7="",puburl7="",pubdescription7="",pubtitle8="",publication8="",author8="",puburl8="",pubdescription8="",pubtitle9="",publication9="",author9="",puburl9="",pubdescription9="",pubtitle10="",publication10="",author10="",puburl10="",pubdescription10="",publicationdate1="",publicationdate2="",publicationdate3="",publicationdate4="",publicationdate5="",publicationdate6="",publicationdate7="",publicationdate8="",publicationdate9="",publicationdate10="";
    String course1="",inst1="",fromdate1="",todate1="",course2="",inst2="",fromdate2="",todate2="",course3="",inst3="",fromdate3="",todate3="",course4="",inst4="",fromdate4="",todate4="",course5="",inst5="",fromdate5="",todate5="",course6="",inst6="",fromdate6="",todate6="",course7="",inst7="",fromdate7="",todate7="",course8="",inst8="",fromdate8="",todate8="",course9="",inst9="",fromdate9="",todate9="",course10="",inst10="",fromdate10="",todate10="";

    int found_contact_details = 0,found_projects=0;
    int found_certificates=0,found_courses=0,found_skills=0,found_honors=0,found_patents=0,found_publications=0,found_lang=0,found_careerobj=0,found_strengths=0,found_weaknesses=0,found_locationpreferences=0;

    public MyProfileAlumniFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_profile_alumni, container, false);


        myprofileimg= (CircleImageView)rootView.findViewById(R.id.myprofileimg);
        myprofilename=(TextView)rootView.findViewById(R.id.myprofilename);
        myprofilrole=(TextView)rootView.findViewById(R.id.myprofilrole);
        myprofiledu=(TextView)rootView.findViewById(R.id.myprofiledu);
        myprofilloc =(TextView)rootView.findViewById(R.id.myprofilloc);
        myprofilemail=(TextView)rootView.findViewById(R.id.myprofilemail);
        myprofilepercenttxt=(TextView)rootView.findViewById(R.id.myprofilepercenttxt);
        editprofiletxt=(TextView)rootView.findViewById(R.id.editprofiletxt);
        eduboxtxt=(TextView)rootView.findViewById(R.id.eduboxtxt);
        projboxtxt=(TextView)rootView.findViewById(R.id.projboxtxt);
        accomplishmentsboxtxt=(TextView)rootView.findViewById(R.id.accomplishmentsboxtxt);
        careerboxtxt=(TextView)rootView.findViewById(R.id.careerboxtxt);
        contactboxtxt=(TextView)rootView.findViewById(R.id.contactboxtxt);
        myprofilecource =(TextView)rootView.findViewById(R.id.myprofilecource);
        myprofilecource2=(TextView)rootView.findViewById(R.id.myprofilecource2);
        myprofilecource3 =(TextView)rootView.findViewById(R.id.myprofilecource3);
        myprofileproj1=(TextView)rootView.findViewById(R.id.myprofileproj1);
        myprofileproj2=(TextView)rootView.findViewById(R.id.myprofileproj2);
        myprofileproj3=(TextView)rootView.findViewById(R.id.myprofileproj3);
        myprofilecource4 =(TextView)rootView.findViewById(R.id.myprofilecource4);
        acc1txt=(TextView)rootView.findViewById(R.id.acc1txt);
        acc2txt=(TextView)rootView.findViewById(R.id.acc2txt);
        acc3txt=(TextView)rootView.findViewById(R.id.acc3txt);
        acc4txt=(TextView)rootView.findViewById(R.id.acc4txt);
        acc5txt=(TextView)rootView.findViewById(R.id.acc5txt);
        acc6txt=(TextView)rootView.findViewById(R.id.acc6txt);
        acc7txt=(TextView)rootView.findViewById(R.id.acc7txt);
        acc1txttxt=(TextView)rootView.findViewById(R.id.acc1txttxt);
        acc2txttxt=(TextView)rootView.findViewById(R.id.acc2txttxt);
        acc3txttxt=(TextView)rootView.findViewById(R.id.acc3txttxt);
        acc4txttxt=(TextView)rootView.findViewById(R.id.acc4txttxt);
        acc5txttxt=(TextView)rootView.findViewById(R.id.acc5txttxt);
        acc6txttxt=(TextView)rootView.findViewById(R.id.acc6txttxt);
        acc7txttxt=(TextView)rootView.findViewById(R.id.acc7txttxt);
        careerobjtxt=(TextView)rootView.findViewById(R.id.careerobjtxt);
        strengthtxt=(TextView)rootView.findViewById(R.id.strengthtxt);
        weaktxt=(TextView)rootView.findViewById(R.id.weaktxt);
        locpretxt=(TextView)rootView.findViewById(R.id.locpretxt);
        nametxt=(TextView)rootView.findViewById(R.id.nametxt);
        mobiletxt=(TextView)rootView.findViewById(R.id.mobiletxt);
        emailtxt=(TextView)rootView.findViewById(R.id.emailtxt);
        myprofileclgname=(TextView)rootView.findViewById(R.id.myprofileclgname);
        myprofileclgyearofpassing=(TextView)rootView.findViewById(R.id.myprofileclgyearofpassing);
        myprofileclgname2=(TextView)rootView.findViewById(R.id.myprofileclgname2);
        myprofileclgyearofpassing2=(TextView)rootView.findViewById(R.id.myprofileclgyearofpassing2);
        myprofileclgname3=(TextView)rootView.findViewById(R.id.myprofileclgname3);
        myprofileclgyearofpassing3=(TextView)rootView.findViewById(R.id.myprofileclgyearofpassing3);
        myprofiledomain1=(TextView)rootView.findViewById(R.id.myprofiledomain1);
        myprofileduration1=(TextView)rootView.findViewById(R.id.myprofileduration1);
        myprofiledomain2=(TextView)rootView.findViewById(R.id.myprofiledomain2);
        myprofileduration2=(TextView)rootView.findViewById(R.id.myprofileduration2);
        myprofiledomain3=(TextView)rootView.findViewById(R.id.myprofiledomain3);
        myprofileduration3=(TextView)rootView.findViewById(R.id.myprofileduration3);
        careerobjtxttxt=(TextView)rootView.findViewById(R.id.careerobj);
        strengthstxt=(TextView)rootView.findViewById(R.id.strengths);
        weaknessestxt=(TextView)rootView.findViewById(R.id.weaknesses);
        locationpreferences=(TextView)rootView.findViewById(R.id.locationpreferences);
        contactaddr1=(TextView)rootView.findViewById(R.id.contactaddr);

        contactmobile=(TextView)rootView.findViewById(R.id.contactmobile);
        contactemail=(TextView)rootView.findViewById(R.id.contactemail);
        myprofilepreview=(TextView)rootView.findViewById(R.id.myprofilepreview);

        insti4 = (ImageView) rootView.findViewById(R.id.insti4);
        edutab4 = (RelativeLayout) rootView.findViewById(R.id.edutab4);

        exptab2 = (RelativeLayout) rootView.findViewById(R.id.exptab2);
        exptab3 = (RelativeLayout) rootView.findViewById(R.id.exptab3);
        exp2 = (ImageView) rootView.findViewById(R.id.exp2);
        exp3 = (ImageView) rootView.findViewById(R.id.exp3);

        TextView expboxtxt=(TextView)rootView.findViewById(R.id.expboxtxt);

        TextView trytxt,protxt,freetxt;

        trytxt=(TextView)rootView.findViewById(R.id.trytxt);
        protxt=(TextView)rootView.findViewById(R.id.protxt);
        freetxt=(TextView)rootView.findViewById(R.id.freetxt);


        swipe_refresh_layout=(SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh_layout);
        SwipeRefreshLayout tswipe_refresh_layout=(SwipeRefreshLayout)getActivity().findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setVisibility(View.GONE);



//        exp

        exp1txt = (TextView) rootView.findViewById(R.id.exp1txt);
        myprofileexp1name = (TextView) rootView.findViewById(R.id.myprofileexp1name);
        myprofileexpfromto = (TextView) rootView.findViewById(R.id.myprofileexpfromto);

        exp2txt = (TextView) rootView.findViewById(R.id.exp2txt);
        myprofileexp2name = (TextView) rootView.findViewById(R.id.myprofileexp2name);
        myprofileexpfromto2 = (TextView) rootView.findViewById(R.id.myprofileexpfromto1);

        exp3txt = (TextView) rootView.findViewById(R.id.exp3txt);
        myprofileexp3name = (TextView) rootView.findViewById(R.id.myprofileexp3name);
        myprofileexpfromto3 = (TextView) rootView.findViewById(R.id.myprofileexpfromto2);

        myprofileclgname4 = (TextView)rootView.findViewById(R.id.myprofileclgname4);
        myprofileclgyearofpassing4 = (TextView)rootView.findViewById(R.id.myprofileclgyearofpassing4);



        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/arba.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/ubuntu.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/arimo.ttf");
        Typeface custom_font4 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/meriitalic.ttf");
        Typeface custom_font5 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/righteous.ttf");
        Typeface custom_font6 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/rockitbold.ttf");
        Typeface custom_font7 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/portano.ttf");
        Typeface custom_font8 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/montbold.ttf");
        Typeface custom_font10 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/hint.ttf");
        Typeface custom_font11 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/hamm.ttf");

        exp1txt.setTypeface(custom_font6);
        myprofileexp1name.setTypeface(custom_font7);
        myprofileexpfromto.setTypeface(custom_font7);

        exp2txt.setTypeface(custom_font6);
        myprofileexp2name.setTypeface(custom_font7);
        myprofileexpfromto2.setTypeface(custom_font7);

        exp3txt.setTypeface(custom_font6);
        myprofileexp3name.setTypeface(custom_font7);
        myprofileexpfromto3.setTypeface(custom_font7);



        myprofilename.setTypeface(custom_font1);
        myprofilrole.setTypeface(custom_font2);
        myprofiledu.setTypeface(custom_font3);
        myprofilloc.setTypeface(custom_font3);
        myprofilemail.setTypeface(custom_font3);
        myprofilepercenttxt.setTypeface(custom_font4);
        editprofiletxt.setTypeface(custom_font5);
        eduboxtxt.setTypeface(custom_font1);
        projboxtxt.setTypeface(custom_font1);
        accomplishmentsboxtxt.setTypeface(custom_font1);
        careerboxtxt.setTypeface(custom_font1);
        contactboxtxt.setTypeface(custom_font1);
        expboxtxt.setTypeface(custom_font1);
        myprofilecource.setTypeface(custom_font6);
        myprofilecource2.setTypeface(custom_font6);
        myprofilecource3.setTypeface(custom_font6);
        myprofilecource4.setTypeface(custom_font6);
        myprofileproj1.setTypeface(custom_font6);
        myprofileproj2.setTypeface(custom_font6);
        myprofileproj3.setTypeface(custom_font6);

        acc1txt.setTypeface(custom_font6);
        acc2txt.setTypeface(custom_font6);
        acc3txt.setTypeface(custom_font6);
        acc4txt.setTypeface(custom_font6);
        acc5txt.setTypeface(custom_font6);
        acc6txt.setTypeface(custom_font6);
        acc7txt.setTypeface(custom_font6);
        careerobjtxt.setTypeface(custom_font6);
        strengthtxt.setTypeface(custom_font6);
        weaktxt.setTypeface(custom_font6);
        locpretxt.setTypeface(custom_font6);
        nametxt.setTypeface(custom_font6);
        mobiletxt.setTypeface(custom_font6);
        emailtxt.setTypeface(custom_font6);


        myprofileclgname.setTypeface(custom_font7);
        myprofileclgyearofpassing.setTypeface(custom_font7);
        myprofileclgname2.setTypeface(custom_font7);
        myprofileclgyearofpassing2.setTypeface(custom_font7);
        myprofileclgname3.setTypeface(custom_font7);
        myprofileclgyearofpassing3.setTypeface(custom_font7);
        myprofileclgname4.setTypeface(custom_font7);
        myprofileclgyearofpassing4.setTypeface(custom_font7);

        myprofiledomain1.setTypeface(custom_font7);
        myprofileduration1.setTypeface(custom_font7);
        myprofiledomain2.setTypeface(custom_font7);
        myprofileduration2.setTypeface(custom_font7);
        myprofiledomain3.setTypeface(custom_font7);
        myprofileduration3.setTypeface(custom_font7);
        careerobjtxttxt.setTypeface(custom_font7);
        strengthstxt.setTypeface(custom_font7);
        weaknessestxt.setTypeface(custom_font7);
        locationpreferences.setTypeface(custom_font7);
        contactaddr1.setTypeface(custom_font7);
        acc1txttxt.setTypeface(custom_font7);
        acc2txttxt.setTypeface(custom_font7);
        acc3txttxt.setTypeface(custom_font7);
        acc4txttxt.setTypeface(custom_font7);
        acc5txttxt.setTypeface(custom_font7);
        acc6txttxt.setTypeface(custom_font7);
        acc7txttxt.setTypeface(custom_font7);

        contactmobile.setTypeface(custom_font7);
        contactemail.setTypeface(custom_font7);
        myprofilepreview.setTypeface(custom_font8);
        protxt.setTypeface(custom_font10);
        trytxt.setTypeface(custom_font11);
        freetxt.setTypeface(custom_font11);

        introedit=(ImageView)rootView.findViewById(R.id.introedit);
        eduedit=(ImageView)rootView.findViewById(R.id.eduedit);
        projectsedit=(ImageView)rootView.findViewById(R.id.projectsedit);
        accomplishmentsedit=(ImageView)rootView.findViewById(R.id.accomplishmentsedit);
        expedit = (ImageView)rootView.findViewById(R.id.expedit);
        careeredit=(ImageView)rootView.findViewById(R.id.careeredit);
        contactedit=(ImageView)rootView.findViewById(R.id.contactedit);
        updateProgress = (ProgressBar) rootView.findViewById(R.id.updateProgressmain);



        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = sharedpreferences.getString(Username, null);

        digest1 = sharedpreferences.getString("digest1", null);
        digest2 = sharedpreferences.getString("digest2", null);
        role = sharedpreferences.getString("role", null);


        demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        demoIVBytes = SimpleBase64Encoder.decode(digest2);
        sPadding = "ISO10126Padding";
        try {
//


            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(username);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);

            myprofilemail.setText(plainusername);
            myprofilrole.setText(role.toUpperCase());
            contactemail.setText(plainusername);

        } catch (Exception e) {
        }

        introedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),AlumniIntro.class),0);
            }
        });
        eduedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileEdu.class),0);
            }
        });
        projectsedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),AlumniProfileProjects.class),0);
            }
        });
        accomplishmentsedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileAccomplishments.class),0);
            }
        });


        expedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),HR_Experiences.class),0);
            }
        });


        careeredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileCareerDetails.class),0);
            }
        });
        contactedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),AlumniContactDetails.class),0);
            }
        });


        myprofileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        editprofilerl=(RelativeLayout)rootView.findViewById(R.id.editprofilerl);
        editprofilerl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivityForResult(new Intent(getActivity(),EditProfileAlumni.class),0);
            }
        });

        View proselectionview=(View)rootView.findViewById(R.id.proselectionview);
        proselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ProSplashScreen.class));

            }
        });

        hashMap = new HashMap<>();
        hashMap.put("Jan", 1);
        hashMap.put("Feb", 2);
        hashMap.put("Mar", 3);
        hashMap.put("Apr", 4);
        hashMap.put("May", 5);
        hashMap.put("Jun", 6);
        hashMap.put("Jul", 7);
        hashMap.put("Aug", 8);
        hashMap.put("Sep", 9);
        hashMap.put("Oct", 10);
        hashMap.put("Nov", 11);
        hashMap.put("Dec", 12);


        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetAlumniData().execute();
                ((AlumniActivity)getActivity()).requestProfileImage();
            }
        });



        refreshContent();

        return rootView;
    }
    private class GetAlumniData extends AsyncTask<String, Void,Bitmap> {


        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            try {


                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));

                json = jParser.makeHttpRequest(MyConstants.url_load_alumni_data, "GET", params);


                String s1 = "";
                String s = "";

                resultofop = json.getString("info");
                Log.d("TAG", "info " + resultofop);
                if (resultofop.equals("found")) {
                    phone = json.getString("phone");
                    s = json.getString("intro");
                    if (s.equals("found")) {
                        found_intro_box = 1;
                        Log.d("TAG", "found_intro_box===:-" + found_intro_box);
                        dataobject = json.getString("introObj");
                        Log.d("TAG", "dataobject===: " + dataobject);

                    }

                }



            } catch (Exception e) {

                e.printStackTrace();
//                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(alumniLog, "exc msg "+e.getMessage());


            }
            return map;

        }

        protected void onPostExecute(Bitmap result) {


            try {
                demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";
                decrept_intro();
//                decrept_tenth();
//                decrept_twelthOrDiploma();
//                decrept_ug();
//                decrept_pg();
//                decrypt_projects();
//                decrypt_knownlangs();
//                decrypt_certificates();
//                decrypt_courses();
//                decrypt_skills();
//                decrypt_honors();
//                decrypt_patents();
//                decrypt_publication();
//                decrept_exp();
//                decrypt_career_details();
//                decrypt_contcat_details();
//                decrept_personol();
            }
            catch (Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            populateData();

            downloadImage();

            swipe_refresh_layout.setRefreshing(false);


        }
    }

    public void decrept_intro() {
        try {
            if (found_intro_box == 1) {



                Log.d("TAG", "onPostExecute: found_box1 -" + found_box1);

                AlumniIntroModal obj2 = (AlumniIntroModal) fromString(dataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                fname = obj2.firstname;
                lname = obj2.lastname;
                country = obj2.selectedCountry;
                state = obj2.selectedState;
                city = obj2.selectedCity;
//                    }
                Log.d("TAG", "onPostExecute: " + fname);
                Log.d("TAG", "onPostExecute: " + lname);
                Log.d("TAG", "onPostExecute: " + country);
                Log.d("TAG", "onPostExecute: " + state);
                Log.d("TAG", "onPostExecute: " + city);


                s.setFname(fname);
                s.setMname(mname);
                s.setLname(lname);
                s.setCountry(country);
                s.setState(state);
                s.setCity(city);

                Log.d("TAG", "onPostExecute:  intro done");


            }
        } catch (Exception ep) {

        }

    }

    public void setVisibilityExpbox() {
        int exp_count = 0;

        if (!fromdates1.equals(""))
            exp_count++;
        if (!fromdates2.equals(""))
            exp_count++;
        if (!fromdates3.equals(""))
            exp_count++;
        if (!fromdates4.equals(""))
            exp_count++;
        if (!fromdates5.equals(""))
            exp_count++;
        if (!fromdates6.equals(""))
            exp_count++;
        if (!fromdates7.equals(""))
            exp_count++;
        if (!fromdates8.equals(""))
            exp_count++;
        if (!fromdates9.equals(""))
            exp_count++;
        if (!fromdates10.equals(""))
            exp_count++;

//        Log.d(HRlog, "exp count " + exp_count);

        exptab2.setVisibility(View.VISIBLE);
        exp2.setVisibility(View.VISIBLE);

        exptab3.setVisibility(View.VISIBLE);
        exp3.setVisibility(View.VISIBLE);

        if (exp_count == 1 || exp_count == 0) {

            exptab2.setVisibility(View.GONE);
            exp2.setVisibility(View.GONE);

            exptab3.setVisibility(View.GONE);
            exp3.setVisibility(View.GONE);
        }
        if (exp_count == 2) {
            exptab3.setVisibility(View.GONE);
            exp3.setVisibility(View.GONE);
        }
    }


    void populateData() {
        setVisibilityExpbox();

        percentProfile = 0;

        if (found_intro_box == 1) {

            percentProfile++;
            if (!fname.equals("") && !lname.equals("")) {
                String firstLetterCapFirstname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
                String firstLetterCapLastname = lname.substring(0, 1).toUpperCase() + lname.substring(1);
                myprofilename.setText(firstLetterCapFirstname + " " + firstLetterCapLastname);
                nametxt.setText(fname + " " + lname);

            }
            if (!fname.equals("") && lname.equals("")) {
                myprofilename.setText(fname);

            }
            if (!country.equals("") && !state.equals("") && !city.equals("")) {
                myprofilloc.setText(city + ", " + state + ", " + country);

            }

        }

        if (found_tenth_box == 1) {
            percentProfile++;
            if (!schoolname.equals("")) {
                myprofileclgname3.setText(schoolname);
            }
            if (!yearofpassing.equals("")) {
                myprofileclgyearofpassing3.setText(yearofpassing);
            }

        }
        if(found_twelth==1) {
            percentProfile++;

            if (!board12.equals("")) {
                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);

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

                Date date = new Date();

                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");

                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);

                String month = "", years = "";

                for (int i = 0; i < yearofpassing12.length() - 6; i++) {
                    month += yearofpassing12.charAt(i);
                }
                for (int i = 5; i < yearofpassing12.length(); i++) {
                    years += yearofpassing12.charAt(i);
                }

                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);

                if(currentYear>year)
                    myprofilecource2.setText("Attended Std. XII in " + board12 + "  at");
                else if (currentYear == year) {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource2.setText("Attended Std. XII in " + board12 + "  at");
                    } else {
                        myprofilecource2.setText("Attending Std. XII in " + board12 + "  at");
                        myprofiledu.setText("Std. XII ("+board12+")");
                    }
                } else {
                    myprofilecource2.setText("Attending Std. XII in " + board12 + "  at");
                    myprofiledu.setText("Std. XII ("+board12+")");
                }

                if (!schoolname12.equals(""))
                    myprofileclgname2.setText(schoolname12);
                if (!yearofpassing12.equals(""))
                    myprofileclgyearofpassing2.setText(yearofpassing12);

            }
        }
        if(found_diploma==1) {
            percentProfile++;
            if (!universitydiploma.equals("")) {

                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);

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

                Date date = new Date();

                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");

                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);

                String month = "", years = "";

                for (int i = 0; i < yearofpassingdiploma.length() - 6; i++) {
                    month += yearofpassingdiploma.charAt(i);
                }
                for (int i = 5; i < yearofpassingdiploma.length(); i++) {
                    years += yearofpassingdiploma.charAt(i);
                }

                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);
                if (currentYear > year)
                    myprofilecource2.setText("Attended Diploma in " + coursediploma + "  at");
                else if (currentYear == year) {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource2.setText("Attended Diploma in " + coursediploma + "  at");
                    } else {
                        myprofilecource2.setText("Attending Diploma in " + coursediploma + "  at");
                        myprofiledu.setText("Diploma ("+coursediploma+")");
                    }
                } else {
                    myprofilecource2.setText("Attending Diploma in " + coursediploma + "  at");
                    myprofiledu.setText("Diploma ("+coursediploma+")");
                }

                if (!collegenamediploma.equals(""))
                    myprofileclgname2.setText(collegenamediploma);
                if (!yearofpassingdiploma.equals(""))
                    myprofileclgyearofpassing2.setText(yearofpassingdiploma);
            }
        }
        if(found_ug==1) {
            if (!universityug.equals("")) {
                percentProfile++;
                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);

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

                Date date = new Date();

                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");

                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);

                String month = "", years = "";

                for (int i = 0; i < yearofpassingug.length() - 6; i++) {
                    month += yearofpassingug.charAt(i);
                }
                for (int i = 5; i < yearofpassingug.length(); i++) {
                    years += yearofpassingug.charAt(i);
                }

                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);

                if (currentYear > year) {
                    myprofilecource.setText("Attended " + courseug + "  in " + streamug + " at");
                }
                else if(currentYear==year)
                {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource.setText("Attended " + courseug + "  in " + streamug + " at");
                    } else {
                        myprofilecource.setText("Attending " + courseug + "  in " + streamug + " at");
                        myprofiledu.setText(courseug+" ("+streamug+")");
                    }

                }
                else {
                    myprofilecource.setText("Attending " + courseug + "  in " + streamug + " at");
                    myprofiledu.setText(courseug+" ("+streamug+")");
                }

                myprofileclgname.setText(collegenameug);
                myprofileclgyearofpassing.setText(yearofpassingug);

                insti4.setVisibility(View.GONE);
                edutab4.setVisibility(View.GONE);
            }
        }

        if(found_pgsem==1) {
            percentProfile++;
            if (!universitypgsem.equals("")) {

                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);

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

                Date date = new Date();

                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");

                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);

                String month = "", years = "";

                for (int i = 0; i < yearofpassingpgsem.length() - 6; i++) {
                    month += yearofpassingpgsem.charAt(i);
                }
                for (int i = 5; i < yearofpassingpgsem.length(); i++) {
                    years += yearofpassingpgsem.charAt(i);
                }
                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);


                if (currentYear > year)
                    myprofilecource4.setText("Attended " + coursepgsem + " at");

                else if (currentYear == year) {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource4.setText("Attended " + coursepgsem + " at");
                    } else {
                        myprofilecource4.setText("Attending " + coursepgsem + " at");
                        myprofiledu.setText(coursepgsem);
                    }
                } else {
                    myprofilecource4.setText("Attending " + coursepgsem + " at");
                    myprofiledu.setText(coursepgsem);
                }

                myprofileclgname4.setText(collegenamepgsem);
                myprofileclgyearofpassing4.setText(yearofpassingpgsem);


                insti4.setVisibility(View.VISIBLE);
                edutab4.setVisibility(View.VISIBLE);
            }
        }
        if(found_pgyear==1) {
            percentProfile++;
            if (!universitypgyear.equals("")) {

                Hashtable<String, Integer> source = new Hashtable<String, Integer>();
                HashMap<String, Integer> map = new HashMap(source);

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

                Date date = new Date();

                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");

                String currentMonth = sdfm.format(date);
                String currentYears = sdfy.format(date);

                String month = "", years = "";

                for (int i = 0; i < yearofpassingpgyear.length() - 6; i++) {
                    month += yearofpassingpgyear.charAt(i);
                }
                for (int i = 5; i < yearofpassingpgyear.length(); i++) {
                    years += yearofpassingpgyear.charAt(i);
                }
                int currentYear = Integer.parseInt(currentYears);
                int year = Integer.parseInt(years);

                if (currentYear > year)
                    myprofilecource4.setText("Attended " + coursepgyear + " at");
                else if (currentYear == year) {
                    if (map.get(currentMonth) > map.get(month)) {
                        myprofilecource4.setText("Attended " + coursepgyear + " at");
                    } else {
                        myprofilecource4.setText("Attending " + coursepgyear + " at");
                        myprofiledu.setText(coursepgyear);
                    }
                } else {
                    myprofilecource4.setText("Attending " + coursepgsem + " at");
                    myprofiledu.setText(coursepgyear);
                }

                myprofileclgname4.setText(collegenamepgyear);
                myprofileclgyearofpassing4.setText(yearofpassingpgyear);


                insti4.setVisibility(View.VISIBLE);
                edutab4.setVisibility(View.VISIBLE);
            }
        }
//            kunal

        if (found_contact_details == 1) {
            percentProfile++;
            if (!addressline1.equals("") && !addressline2.equals("") && !addressline3.equals("")) {
                contactaddr1.setText(addressline1 + ", " + addressline2 + ", " + addressline3);
                percentProfile++;
            }
            // setting email and phone from intro box
        }

        // projects

        if(found_projects==1) {
            percentProfile++;
            if (!proj1.equals("")) {
                myprofileproj1.setText(proj1);
                myprofiledomain1.setText(domain1);
                myprofileduration1.setText(duration1 + " Months");

                ImageView gear2 = (ImageView) rootView.findViewById(R.id.gear2);
                RelativeLayout projtab2 = (RelativeLayout) rootView.findViewById(R.id.projtab2);
                ImageView gear3 = (ImageView) rootView.findViewById(R.id.gear3);
                RelativeLayout projtab3 = (RelativeLayout) rootView.findViewById(R.id.projtab3);

                gear2.setVisibility(View.GONE);
                projtab2.setVisibility(View.GONE);
                gear3.setVisibility(View.GONE);
                projtab3.setVisibility(View.GONE);
            }
            if (!proj2.equals("")) {
                myprofileproj2.setText(proj2);
                myprofiledomain2.setText(domain2);
                myprofileduration2.setText(duration2 + " Months");

                ImageView gear2 = (ImageView) rootView.findViewById(R.id.gear2);
                RelativeLayout projtab2 = (RelativeLayout) rootView.findViewById(R.id.projtab2);
                ImageView gear3 = (ImageView) rootView.findViewById(R.id.gear3);
                RelativeLayout projtab3 = (RelativeLayout) rootView.findViewById(R.id.projtab3);

                gear2.setVisibility(View.VISIBLE);
                projtab2.setVisibility(View.VISIBLE);
                gear3.setVisibility(View.GONE);
                projtab3.setVisibility(View.GONE);
            }
            if (!proj3.equals("")) {
                myprofileproj3.setText(proj3);
                myprofiledomain3.setText(domain3);
                myprofileduration3.setText(duration3 + " Months");

                ImageView gear2 = (ImageView) rootView.findViewById(R.id.gear2);
                RelativeLayout projtab2 = (RelativeLayout) rootView.findViewById(R.id.projtab2);
                ImageView gear3 = (ImageView) rootView.findViewById(R.id.gear3);
                RelativeLayout projtab3 = (RelativeLayout) rootView.findViewById(R.id.projtab3);

                gear2.setVisibility(View.VISIBLE);
                projtab2.setVisibility(View.VISIBLE);
                gear3.setVisibility(View.VISIBLE);
                projtab3.setVisibility(View.VISIBLE);
            }
        }

        // career details

        if(found_careerobj==1) {
            if (!careerobj.equals(""))
                careerobjtxttxt.setText(careerobj);
            percentProfile++;
        }
        if(found_strengths==1) {
            if (!strength1.equals(""))
                strengthstxt.setText(strength1);
            if (!strength1.equals("") && !strength2.equals(""))
                strengthstxt.setText(strength1 + ", " + strength2);
            if (!strength1.equals("") && !strength2.equals("") && !strength3.equals(""))
                strengthstxt.setText(strength1 + ", " + strength2 + ", " + strength3);
            if (!strength1.equals("") && !strength2.equals("") && !strength3.equals("") && !strength4.equals(""))
                strengthstxt.setText(strength1 + ", " + strength2 + ", " + strength3 + " ...");
            percentProfile++;
        }
        if(found_weaknesses==1) {
            if (!weak1.equals(""))
                weaknessestxt.setText(weak1);
            if (!weak1.equals("") && !weak2.equals(""))
                weaknessestxt.setText(weak1 + ", " + weak2);
            if (!weak1.equals("") && !weak2.equals("") && !weak3.equals(""))
                weaknessestxt.setText(weak1 + ", " + weak2 + ", " + weak3);
            if (!weak1.equals("") && !weak2.equals("") && !weak3.equals("") && !weak4.equals(""))
                weaknessestxt.setText(weak1 + ", " + weak2 + ", " + weak3 + " ...");
            percentProfile++;
        }
        if(found_locationpreferences==1) {
            if (!location1.equals(""))
                locationpreferences.setText(location1);
            if (!location1.equals("") && !location2.equals(""))
                locationpreferences.setText(location1 + ", " + location2);
            if (!location1.equals("") && !location2.equals("") && !location3.equals(""))
                locationpreferences.setText(location1 + ", " + location2 + ", " + location3);
            if (!location1.equals("") && !location2.equals("") && !location3.equals("") && !location4.equals(""))
                locationpreferences.setText(location1 + ", " + location2 + ", " + location3 + " ...");
            percentProfile++;
        }
        if(found_lang==1) {
            if (!lang1.equals("") && !lang1.equals("- Select Language -"))
                acc1txttxt.setText(lang1);
            if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -"))
                acc1txttxt.setText(lang1 + ", " + lang2);
            if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -") && !lang3.equals("") && !lang3.equals("- Select Language -"))
                acc1txttxt.setText(lang1 + ", " + lang2 + ", " + lang3);
            if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -") && !lang3.equals("") && !lang3.equals("- Select Language -") && !lang4.equals("") && !lang4.equals("- Select Language -"))
                acc1txttxt.setText(lang1 + ", " + lang2 + ", " + lang3 + " ...");
            percentProfile++;
        }

        if(found_certificates==1) {
            if (!title1.equals(""))
                acc2txttxt.setText(title1);
            if (!title1.equals("") && !title2.equals(""))
                acc2txttxt.setText(title1 + ", " + title2);
            if (!title1.equals("") && !title2.equals("") && !title3.equals(""))
                acc2txttxt.setText(title1 + ", " + title2 + ", " + title3);
            if (!title1.equals("") && !title2.equals("") && !title3.equals("") && !title4.equals(""))
                acc2txttxt.setText(title1 + ", " + title2 + ", " + title3 + " ...");
            percentProfile++;
        }
        if(found_courses==1) {
            if (!course1.equals(""))
                acc3txttxt.setText(course1);
            if (!course1.equals("") && !course2.equals(""))
                acc3txttxt.setText(course1 + ", " + course2);
            if (!course1.equals("") && !course2.equals("") && !course3.equals(""))
                acc3txttxt.setText(course1 + ", " + course2 + ", " + course3);
            if (!course1.equals("") && !course2.equals("") && !course3.equals("") && !course4.equals(""))
                acc3txttxt.setText(course1 + ", " + course2 + ", " + course3 + " ...");
            percentProfile++;
        }
        if(found_skills==1) {
            if (!skill1.equals(""))
                acc4txttxt.setText(skill1);
            if (!skill1.equals("") && !skill2.equals(""))
                acc4txttxt.setText(skill1 + ", " + skill2);
            if (!skill1.equals("") && !skill2.equals("") && !skill3.equals(""))
                acc4txttxt.setText(skill1 + ", " + skill2 + ", " + skill3);
            if (!skill1.equals("") && !skill2.equals("") && !skill3.equals("") && !skill4.equals(""))
                acc4txttxt.setText(skill1 + ", " + skill2 + ", " + skill3 + " ...");

            percentProfile++;
        }
        if(found_honors==1) {
            if (!htitle1.equals(""))
                acc5txttxt.setText(htitle1);
            if (!htitle1.equals("") && !htitle2.equals(""))
                acc5txttxt.setText(htitle1 + ", " + htitle2);
            if (!htitle1.equals("") && !htitle2.equals("") && !htitle3.equals(""))
                acc5txttxt.setText(htitle1 + ", " + htitle2 + ", " + htitle3);
            if (!htitle1.equals("") && !htitle2.equals("") && !htitle3.equals("") && !htitle4.equals(""))
                acc5txttxt.setText(htitle1 + ", " + htitle2 + ", " + htitle3 + " ...");
            percentProfile++;
        }
        if(found_patents==1) {
            if (!ptitle1.equals(""))
                acc6txttxt.setText(ptitle1);
            if (!ptitle1.equals("") && !ptitle2.equals(""))
                acc6txttxt.setText(ptitle1 + ", " + ptitle2);
            if (!ptitle1.equals("") && !ptitle2.equals("") && !ptitle3.equals(""))
                acc6txttxt.setText(ptitle1 + ", " + ptitle2 + ", " + ptitle3);
            if (!ptitle1.equals("") && !ptitle2.equals("") && !ptitle3.equals("") && !ptitle4.equals(""))
                acc6txttxt.setText(ptitle1 + ", " + ptitle2 + ", " + ptitle3 + " ...");
            percentProfile++;
        }
        if(found_publications==1) {
            if (!pubtitle1.equals(""))
                acc7txttxt.setText(pubtitle1);
            if (!pubtitle1.equals("") && !pubtitle2.equals(""))
                acc7txttxt.setText(pubtitle1 + ", " + pubtitle2);
            if (!pubtitle1.equals("") && !pubtitle2.equals("") && !pubtitle3.equals(""))
                acc7txttxt.setText(pubtitle1 + ", " + pubtitle2 + ", " + pubtitle3);
            if (!pubtitle1.equals("") && !pubtitle2.equals("") && !pubtitle3.equals("") && !pubtitle4.equals(""))
                acc7txttxt.setText(pubtitle1 + ", " + pubtitle2 + ", " + pubtitle3 + " ...");
            percentProfile++;
        }
        populateHrInfo();




        if (hrinfobox1 == true)
            percentProfile++;

        if (hrinfobox2 == true)
            percentProfile++;

        if (hrinfobox3 == true)
            percentProfile++;


        float R = (1000 - 0) / (24 - 0);
        float y = (percentProfile - 0) * R + 0;
        int val = Math.round(y);

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(updateProgress, "progress", 0, val);
        progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();


    }

    public int alltoDatestoInt(String mydate) {
        int m = 0, t = 0;

        String[] splited;
        splited = mydate.split(", ");
        if (splited.length == 2) {
            int mixdate;
            String fromMonths = "";
            fromMonths = splited[0];
            m = hashMap.get(fromMonths);
            t = Integer.parseInt(splited[1]);
            mixdate = t * 10 + m;
            return mixdate;
        }

        return 0;
    }

    public int[] expYearMonth(String fromdate, String todate) {

        int fromYear = 0, frommonth = 0, toyear = 0, tomonth = 0;
        String[] splited;
        splited = fromdate.split(", ");
        if (splited.length == 2) {
            String fromMonths = "";
            fromMonths = splited[0];
            frommonth = hashMap.get(fromMonths);
            fromYear = Integer.parseInt(splited[1]);
        }
        if (!todate.equals("")) {

            splited = todate.split(", ");
            if (splited.length == 2) {
                String mstr = "";
                mstr = splited[0];
                tomonth = hashMap.get(mstr);
                toyear = Integer.parseInt(splited[1]);
            }

        } else {
            Date date = new Date();
            SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
            SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");

            String currentMonth = sdfm.format(date);
            String currentYears = sdfy.format(date);
            tomonth = hashMap.get(currentMonth);
            toyear = Integer.parseInt(currentYears);

        }
        DateTime date2 = new DateTime().withDate(toyear, tomonth, 1);
        DateTime date1 = new DateTime().withDate(fromYear, frommonth, 1);
        int exp_in_months2 = Months.monthsBetween(date1, date2).getMonths();
        int year = exp_in_months2 / 12;
        int months = exp_in_months2 % 12;
        int expMY[] = {year, months};

        return expMY;
    }

    public void populateHrInfo() {
//        TreeMap<Integer,Integer> continuseWork=new TreeMap<>(Collections.reverseOrder());
        TreeMap<Integer, Integer> continuseWork = new TreeMap<>();
        TreeMap<Integer, Integer> workDoneExp = new TreeMap<>(Collections.reverseOrder());
        Log.d(alumniLog, "populateHrInfo: ");
//        boolean hrinfobox1 = false, hrinfobox2 = false, hrinfobox3 = false;

        hrinfobox1 = false;
        hrinfobox2 = false;
        hrinfobox3 = false;

        int fulltodate1 = 0, fulltodate2 = 0, fulltodate3 = 0, fulltodate4 = 0, fulltodate5 = 0, fulltodate6 = 0, fulltodate7 = 0, fulltodate8 = 0, fulltodate9 = 0, fulltodate10 = 0;

        String MONTH = "";
        String YEAR = "";

        // continus working then we hav to fromdate not todate
        if (todates1.equals("") && !fromdates1.equals("")) {
            fulltodate1 = alltoDatestoInt(fromdates1);
            continuseWork.put(fulltodate1, 1);
        }

        if (todates2.equals("") && !fromdates2.equals("")) {
            fulltodate2 = alltoDatestoInt(fromdates2);
            continuseWork.put(fulltodate2, 2);
        }

        if (todates3.equals("") && !fromdates3.equals("")) {
            fulltodate3 = alltoDatestoInt(fromdates3);
            continuseWork.put(fulltodate3, 3);
        }

        if (todates4.equals("") && !fromdates4.equals("")) {
            fulltodate4 = alltoDatestoInt(fromdates4);
            continuseWork.put(fulltodate4, 4);
        }

        if (todates5.equals("") && !fromdates5.equals("")) {
            fulltodate5 = alltoDatestoInt(fromdates5);
            continuseWork.put(fulltodate5, 5);
        }

        if (todates6.equals("") && !fromdates6.equals("")) {
            fulltodate6 = alltoDatestoInt(todates6);
            continuseWork.put(fulltodate6, 6);
        }

        if (todates7.equals("") && !fromdates7.equals("")) {
            fulltodate7 = alltoDatestoInt(fromdates7);
            continuseWork.put(fulltodate7, 7);
        }

        if (todates8.equals("") && !fromdates8.equals("")) {
            fulltodate8 = alltoDatestoInt(fromdates8);
            continuseWork.put(fulltodate8, 8);
        }

        if (todates9.equals("") && !fromdates9.equals("")) {
            fulltodate9 = alltoDatestoInt(fromdates9);
            continuseWork.put(fulltodate9, 9);
        }

        if (todates10.equals("") && !fromdates10.equals("")) {
            fulltodate10 = alltoDatestoInt(fromdates10);
            continuseWork.put(fulltodate10, 10);
        }

        //for continuous working
        for (Map.Entry<Integer, Integer> entry : continuseWork.entrySet()) {
            int fullDatekey = entry.getKey();
            int expItemIndex = entry.getValue();          // post+index  all set

            if (expItemIndex == 1) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts1);
                    myprofileexp1name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
//                    if(YM[1]==0)
//                    myprofileexpfromto.setText("Currently Working | " + YM[0] + " year");
//                    else if(YM[0]==0)
//                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " month");
//                    else
//                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " year - "+YM[1] + " month");
//                    hrinfobox1=true;

                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";


                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;

                } else if (hrinfobox2 == false) {

                    exp2txt.setText(posts1);
                    myprofileexp2name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts1);
                    myprofileexp3name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 2) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts2);
                    myprofileexp1name.setText(inst1s2);
                    int[] YM = expYearMonth(fromdates2, todates2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;

                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts2);
                    myprofileexp2name.setText(inst1s2);
                    int[] YM = expYearMonth(fromdates2, todates2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts3);
                    myprofileexp3name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }

            }
            if (expItemIndex == 3) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts3);
                    myprofileexp1name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts3);
                    myprofileexp2name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts3);
                    myprofileexp3name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 4) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts4);
                    myprofileexp1name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts4);
                    myprofileexp2name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts4);
                    myprofileexp3name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 5) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts5);
                    myprofileexp1name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts5);
                    myprofileexp2name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts5);
                    myprofileexp3name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 6) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts6);
                    myprofileexp1name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts6);
                    myprofileexp2name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts6);
                    myprofileexp3name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 7) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts7);
                    myprofileexp1name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts7);
                    myprofileexp2name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts7);
                    myprofileexp3name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 8) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts8);
                    myprofileexp1name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts8);
                    myprofileexp2name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts8);
                    myprofileexp3name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 9) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts9);
                    myprofileexp1name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts9);
                    myprofileexp2name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts9);
                    myprofileexp3name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 10) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts10);
                    myprofileexp1name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts10);
                    myprofileexp2name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts10);
                    myprofileexp3name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText("Currently Working | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText("Currently Working | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }


        }


        // from-to exp ----------------------------

        if (!todates1.equals("") && !fromdates1.equals("")) {
            fulltodate1 = alltoDatestoInt(todates1);
            workDoneExp.put(fulltodate1, 1);
        }
        if (!todates2.equals("") && !fromdates2.equals("")) {
            fulltodate2 = alltoDatestoInt(todates2);
            workDoneExp.put(fulltodate2, 2);
        }
        if (!todates3.equals("") && !fromdates3.equals("")) {
            fulltodate3 = alltoDatestoInt(todates3);
            workDoneExp.put(fulltodate3, 3);
        }
        if (!todates4.equals("") && !fromdates4.equals("")) {
            fulltodate4 = alltoDatestoInt(todates4);
            workDoneExp.put(fulltodate4, 4);
        }
        if (!todates5.equals("") && !fromdates5.equals("")) {
            fulltodate5 = alltoDatestoInt(todates5);
            workDoneExp.put(fulltodate5, 5);
        }
        if (!todates6.equals("") && !fromdates6.equals("")) {
            fulltodate6 = alltoDatestoInt(todates6);
            workDoneExp.put(fulltodate6, 6);
        }
        if (!todates7.equals("") && !fromdates7.equals("")) {
            fulltodate7 = alltoDatestoInt(todates7);
            workDoneExp.put(fulltodate7, 7);
        }
        if (!todates8.equals("") && !fromdates8.equals("")) {
            fulltodate8 = alltoDatestoInt(todates8);
            workDoneExp.put(fulltodate8, 8);
        }
        if (!todates9.equals("") && !fromdates9.equals("")) {
            fulltodate9 = alltoDatestoInt(todates9);
            workDoneExp.put(fulltodate9, 9);
        }
        if (!todates10.equals("") && !fromdates10.equals("")) {
            fulltodate10 = alltoDatestoInt(todates10);
            workDoneExp.put(fulltodate10, 10);
        }

        for (Map.Entry<Integer, Integer> entry : workDoneExp.entrySet()) {
            int expItemIndex = entry.getValue();          // post+index  all set

            if (expItemIndex == 1) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts1);
                    myprofileexp1name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates1 + " - " + todates1 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts1);
                    myprofileexp2name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates1 + " - " + todates1 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts1);
                    myprofileexp3name.setText(inst1s1);
                    int[] YM = expYearMonth(fromdates1, todates1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates1 + " - " + todates1 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates1 + " - " + todates1 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 2) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts2);
                    myprofileexp1name.setText(inst1s2);
                    int[] YM = expYearMonth(fromdates2, todates2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates2 + " - " + todates2 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;

                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts2);
                    myprofileexp2name.setText(inst1s2);
                    int[] YM = expYearMonth(fromdates2, todates2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates2 + " - " + todates2 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts2);
                    myprofileexp3name.setText(inst1s2);
                    int[] YM = expYearMonth(fromdates2, todates2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates2 + " - " + todates2 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates2 + " - " + todates2 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }

            }
            if (expItemIndex == 3) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts3);
                    myprofileexp1name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates3 + " - " + todates3 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts3);
                    myprofileexp2name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates3 + " - " + todates3 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts3);
                    myprofileexp3name.setText(inst1s3);
                    int[] YM = expYearMonth(fromdates3, todates3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates3 + " - " + todates3 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates3 + " - " + todates3 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 4) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts4);
                    myprofileexp1name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates4 + " - " + todates4 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts4);
                    myprofileexp2name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates4 + " - " + todates4 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts4);
                    myprofileexp3name.setText(inst1s4);
                    int[] YM = expYearMonth(fromdates4, todates4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates4 + " - " + todates4 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates4 + " - " + todates4 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 5) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts5);
                    myprofileexp1name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates5 + " - " + todates5 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts5);
                    myprofileexp2name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates5 + " - " + todates5 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts5);
                    myprofileexp3name.setText(inst1s5);
                    int[] YM = expYearMonth(fromdates5, todates5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates5 + " - " + todates5 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates5 + " - " + todates5 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 6) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts6);
                    myprofileexp1name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates6 + " - " + todates6 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts6);
                    myprofileexp2name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates6 + " - " + todates6 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts6);
                    myprofileexp3name.setText(inst1s6);
                    int[] YM = expYearMonth(fromdates6, todates6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates6 + " - " + todates6 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates6 + " - " + todates6 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 7) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts7);
                    myprofileexp1name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates7 + " - " + todates7 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts7);
                    myprofileexp2name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates7 + " - " + todates7 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts7);
                    myprofileexp3name.setText(inst1s7);
                    int[] YM = expYearMonth(fromdates7, todates7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates7 + " - " + todates7 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates7 + " - " + todates7 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 8) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts8);
                    myprofileexp1name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates8 + " - " + todates8 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts8);
                    myprofileexp2name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates8 + " - " + todates8 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts8);
                    myprofileexp3name.setText(inst1s8);
                    int[] YM = expYearMonth(fromdates8, todates8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates8 + " - " + todates8 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates8 + " - " + todates8 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 9) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts9);
                    myprofileexp1name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates9 + " - " + todates9 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts9);
                    myprofileexp2name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates9 + " - " + todates9 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts9);
                    myprofileexp3name.setText(inst1s9);
                    int[] YM = expYearMonth(fromdates9, todates9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates9 + " - " + todates9 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates9 + " - " + todates9 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 10) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(posts10);
                    myprofileexp1name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdates10 + " - " + todates10 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(posts10);
                    myprofileexp2name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdates10 + " - " + todates10 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(posts10);
                    myprofileexp3name.setText(inst1s10);
                    int[] YM = expYearMonth(fromdates10, todates10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdates10 + " - " + todates10 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdates10 + " - " + todates10 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
        }
    }








    public String GetCountryZipCode() {
        String CountryID = "";
        String CountryZipCode = "";
        CountryID = getUserCountry(getContext());
        String[] rl = this.getResources().getStringArray(R.array.CountryCodes);
        for (int i = 0; i < rl.length; i++) {
            String[] g = rl[i].split(",");
            if (g[1].trim().equals(CountryID.trim())) {
                CountryZipCode = g[0];
                break;
            }
        }
        return CountryZipCode;
    }
    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toUpperCase(Locale.US);
            } else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toUpperCase(Locale.US);
                }
            }
        } catch (Exception e) {
        }
        return null;
    }


    public void showUpdateProgress() {
        updateProgress.setVisibility(View.VISIBLE);
    }

    public void refreshContent() {

        new GetAlumniData().execute();


    }
    public static class FireMissilesDialogFragment extends DialogFragment {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose Action").setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    if (which == 0) {
                        startActivity(new Intent(getContext(),ViewProfileImage.class));
                    } else if(which==1){
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
                    }
                    else if(which==2){

                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:

                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        dialog.cancel();
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();

                    }
                }
            });
            return builder.create();
        }
    }
    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Action").setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    startActivity(new Intent(getContext(), ViewProfileImage.class));
                } else if (which == 1) {

                    dialog.cancel();
                    ((AlumniActivity) getActivity()).requestCropImage();
                } else if (which == 2) {

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    new DeleteProfile().execute();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialog.cancel();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();

                }
            }
        });
        builder.show();
    }
    class DeleteProfile extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(MyConstants.url_remove_profile, "GET", params);

            try {

                resultofop = json.getString("info");

            } catch (Exception ex) {

            }

            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {

            if (resultofop.equals("success")) {
                Toast.makeText(getActivity(), "Profile Picture removed..!", Toast.LENGTH_LONG).show();
                refreshContent();
                ((AlumniActivity) getActivity()).requestProfileImage();
            } else if (resultofop.equals("fail"))
                Toast.makeText(getActivity(), "Failed..!", Toast.LENGTH_LONG).show();

            else if (resultofop.equals("notfound"))
                Toast.makeText(getActivity(), "No Profile Picture..!", Toast.LENGTH_LONG).show();


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


                });
            }
        }

        return animation;
    }

    class Getsingnature extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(MyConstants.load_last_updated, "GET", params);
            try {

                signature = json.getString("lastupdated");


            } catch (Exception ex) {

            }
            return signature;
        }

        @Override
        protected void onPostExecute(String result) {


        }
    }
    private void downloadImage() {


//        new Getsingnature().execute();
//        String t = String.valueOf(System.currentTimeMillis());
        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("192.168.100.100")
                .path("AESTest/GetImage")
                .appendQueryParameter("u", username)
                .build();

        GlideApp.with(getContext())
                .load(uri)
                .signature(new ObjectKey(System.currentTimeMillis() + ""))
                .into(myprofileimg);


    }
}
