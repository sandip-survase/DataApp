package placeme.octopusites.com.placeme;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.modal.AdminIntroModal;
import placeme.octopusites.com.placeme.modal.Certificates;
import placeme.octopusites.com.placeme.modal.Courses;
import placeme.octopusites.com.placeme.modal.Honors;
import placeme.octopusites.com.placeme.modal.KnownLangs;
import placeme.octopusites.com.placeme.modal.Modelmyprofileintro;
import placeme.octopusites.com.placeme.modal.MyProfileCareerObjModal;
import placeme.octopusites.com.placeme.modal.MyProfileDiplomaModal;
import placeme.octopusites.com.placeme.modal.MyProfileLocationModal;
import placeme.octopusites.com.placeme.modal.MyProfilePersonal;
import placeme.octopusites.com.placeme.modal.MyProfileStrengthsModal;
import placeme.octopusites.com.placeme.modal.MyProfileTenthModal;
import placeme.octopusites.com.placeme.modal.MyProfileTwelthModal;
import placeme.octopusites.com.placeme.modal.MyProfileUgModal;
import placeme.octopusites.com.placeme.modal.MyProfileWeaknessesModal;
import placeme.octopusites.com.placeme.modal.Patents;
import placeme.octopusites.com.placeme.modal.PgSem;
import placeme.octopusites.com.placeme.modal.PgYear;
import placeme.octopusites.com.placeme.modal.Projects;
import placeme.octopusites.com.placeme.modal.Publications;
import placeme.octopusites.com.placeme.modal.Skills;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;

public class MyProfileFragment extends Fragment {
    CircleImageView myprofileimg;
    TextView myprofilename, myprofilrole, myprofiledu, myprofilloc, myprofilemail, myprofilepercenttxt, editprofiletxt, eduboxtxt, projboxtxt, accomplishmentsboxtxt, careerboxtxt, contactboxtxt, myprofilecource, myprofilecource2, myprofilecource3, myprofilecource4, myprofileproj1, myprofileproj2, myprofileproj3, acc1txt, acc2txt, acc3txt, acc4txt, acc5txt, acc6txt, acc7txt, careerobjtxt, strengthtxt, weaktxt, locpretxt, nametxt, mobiletxt, emailtxt, myprofileclgname, myprofileclgyearofpassing, myprofileclgname2, myprofileclgyearofpassing2, myprofileclgname3, myprofileclgname4, myprofileclgyearofpassing3, myprofileclgyearofpassing4, myprofiledomain1, myprofileduration1, myprofiledomain2, myprofileduration2, myprofiledomain3, myprofileduration3, careerobjtxttxt, strengthstxt, weaknessestxt, locationpreferences, contactaddr1, contactmobile, contactemail, myprofilepreview, acc1txttxt, acc2txttxt, acc3txttxt, acc4txttxt, acc5txttxt, acc6txttxt, acc7txttxt;
    TextView trytxt, protxt, freetxt;
    ImageView introedit, eduedit, projectsedit, accomplishmentsedit, careeredit, contactedit;
    final static CharSequence[] items = {"View Profile Picture", "Update Profile Picture", "Delete Profile Picture"};
    RelativeLayout editprofilerl;
    String username = "", resultofop,dataobject="",careerdataobject="",strengthdataobject="",weaknessesdataobject="",locationpreferencesdataobject="",tenthdataobject="",ugdataobject="";
    String fname = "", mname = "", lname = "", country = "", state = "", city = "", role = "", plainusername = "", phone = "";
    String marks10 = "", outof10 = "", percentage10 = "", schoolname10 = "", board10 = "", yearofpassing10 = "", marks12 = "", outof12 = "", percentage12 = "", schoolname12 = "", board12 = "", stream12 = "", yearofpassing12 = "", markssem1diploma = "", outofsem1diploma = "", percentagesem1diploma = "", markssem2diploma = "", outofsem2diploma = "", percentagesem2diploma = "", markssem3diploma = "", outofsem3diploma = "", percentagesem3diploma = "", markssem4diploma = "", outofsem4diploma = "", percentagesem4diploma = "", markssem5diploma = "", outofsem5diploma = "", percentagesem5diploma = "", markssem6diploma = "", outofsem6diploma = "", percentagesem6diploma = "", aggregatediploma = "", coursediploma = "", streamdiploma = "", universitydiploma = "", collegenamediploma = "", yearofpassingdiploma = "",personaldataobject="";
    String markssem1ug = "", outofsem1ug = "", percentagesem1ug = "", markssem2ug = "", outofsem2ug = "", percentagesem2ug = "", markssem3ug = "", outofsem3ug = "", percentagesem3ug = "", markssem4ug = "", outofsem4ug = "", percentagesem4ug = "", markssem5ug = "", outofsem5ug = "", percentagesem5ug = "", markssem6ug = "", outofsem6ug = "", percentagesem6ug = "", markssem7ug = "", outofsem7ug = "", percentagesem7ug = "", markssem8ug = "", outofsem8ug = "", percentagesem8ug = "", aggregateug = "", courseug = "", streamug = "", universityug = "", collegenameug = "", yearofpassingug = "";
    String markssem1pgsem = "", outofsem1pgsem = "", percentagesem1pgsem = "", markssem2pgsem = "", outofsem2pgsem = "", percentagesem2pgsem = "", markssem3pgsem = "", outofsem3pgsem = "", percentagesem3pgsem = "", markssem4pgsem = "", outofsem4pgsem = "", percentagesem4pgsem = "", markssem5pgsem = "", outofsem5pgsem = "", percentagesem5pgsem = "", markssem6pgsem = "", outofsem6pgsem = "", percentagesem6pgsem = "", aggregatepgsem = "", coursepgsem = "", streampgsem = "", universitypgsem = "", collegenamepgsem = "", yearofpassingpgsem = "";
    String marksyear1pgyear = "", outofyear1pgyear = "", percentageyear1pgyear = "", marksyear2pgyear = "", outofyear2pgyear = "", percentageyear2pgyear = "", marksyear3pgyear = "", outofyear3pgyear = "", percentageyear3pgyear = "", aggregatepgyear = "", coursepgyear = "", streampgyear = "", universitypgyear = "", collegenamepgyear = "", yearofpassingpgyear = "";
    String proj1 = "", domain1 = "", team1 = "", duration1 = "", proj2 = "", domain2 = "", team2 = "", duration2 = "", proj3 = "", domain3 = "", team3 = "", duration3 = "", proj4 = "", domain4 = "", team4 = "", duration4 = "", proj5 = "", domain5 = "", team5 = "", duration5 = "", proj6 = "", domain6 = "", team6 = "", duration6 = "", proj7 = "", domain7 = "", team7 = "", duration7 = "", proj8 = "", domain8 = "", team8 = "", duration8 = "", proj9 = "", domain9 = "", team9 = "", duration9 = "", proj10 = "", domain10 = "", team10 = "", duration10 = "";
    String course1 = "", inst1 = "", fromdate1 = "", todate1 = "", course2 = "", inst2 = "", fromdate2 = "", todate2 = "", course3 = "", inst3 = "", fromdate3 = "", todate3 = "", course4 = "", inst4 = "", fromdate4 = "", todate4 = "", course5 = "", inst5 = "", fromdate5 = "", todate5 = "", course6 = "", inst6 = "", fromdate6 = "", todate6 = "", course7 = "", inst7 = "", fromdate7 = "", todate7 = "", course8 = "", inst8 = "", fromdate8 = "", todate8 = "", course9 = "", inst9 = "", fromdate9 = "", todate9 = "", course10 = "", inst10 = "", fromdate10 = "", todate10 = "";
    String lang1 = "", proficiency1 = "", lang2 = "", proficiency2 = "", lang3 = "", proficiency3 = "", lang4 = "", proficiency4 = "", lang5 = "", proficiency5 = "", lang6 = "", proficiency6 = "", lang7 = "", proficiency7 = "", lang8 = "", proficiency8 = "", lang9 = "", proficiency9 = "", lang10 = "", proficiency10 = "";
    String title1 = "", issuer1 = "", license1 = "", title2 = "", issuer2 = "", license2 = "", title3 = "", issuer3 = "", license3 = "", title4 = "", issuer4 = "", license4 = "", title5 = "", issuer5 = "", license5 = "", title6 = "", issuer6 = "", license6 = "", title7 = "", issuer7 = "", license7 = "", title8 = "", issuer8 = "", license8 = "", title9 = "", issuer9 = "", license9 = "", title10 = "", issuer10 = "", license10 = "", startdate1certificate = "", enddate1certificate = "", startdate2certificate = "", enddate2certificate = "", startdate3certificate = "", enddate3certificate = "", startdate4certificate = "", enddate4certificate = "", startdate5certificate = "", enddate5certificate = "", startdate6certificate = "", enddate6certificate = "", startdate7certificate = "", enddate7certificate = "", startdate8certificate = "", enddate8certificate = "", startdate9certificate = "", enddate9certificate = "", startdate10certificate = "", enddate10certificate = "", willexpire1certificate = "", willexpire2certificate = "", willexpire3certificate = "", willexpire4certificate = "", willexpire5certificate = "", willexpire6certificate = "", willexpire7certificate = "", willexpire8certificate = "", willexpire9certificate = "", willexpire10certificate = "";
    ;
    String skill1 = "", skill2 = "", skill3 = "", skill4 = "", skill5 = "", skill6 = "", skill7 = "", skill8 = "", skill9 = "", skill10 = "", skill11 = "", skill12 = "", skill13 = "", skill14 = "", skill15 = "", skill16 = "", skill17 = "", skill18 = "", skill19 = "", skill20 = "", sproficiency1 = "", sproficiency2 = "", sproficiency3 = "", sproficiency4 = "", sproficiency5 = "", sproficiency6 = "", sproficiency7 = "", sproficiency8 = "", sproficiency9 = "", sproficiency10 = "", sproficiency11 = "", sproficiency12 = "", sproficiency13 = "", sproficiency14 = "", sproficiency15 = "", sproficiency16 = "", sproficiency17 = "", sproficiency18 = "", sproficiency19 = "", sproficiency20 = "";
    String htitle1 = "", hissuer1 = "", hdescription1 = "", htitle2 = "", hissuer2 = "", hdescription2 = "", htitle3 = "", hissuer3 = "", hdescription3 = "", htitle4 = "", hissuer4 = "", hdescription4 = "", htitle5 = "", hissuer5 = "", hdescription5 = "", htitle6 = "", hissuer6 = "", hdescription6 = "", htitle7 = "", hissuer7 = "", hdescription7 = "", htitle8 = "", hissuer8 = "", hdescription8 = "", htitle9 = "", hissuer9 = "", hdescription9 = "", htitle10 = "", hissuer10 = "", hdescription10 = "", yearofhonor1 = "", yearofhonor2 = "", yearofhonor3 = "", yearofhonor4 = "", yearofhonor5 = "", yearofhonor6 = "", yearofhonor7 = "", yearofhonor8 = "", yearofhonor9 = "", yearofhonor10 = "";
    String ptitle1 = "", pappno1 = "", pinventor1 = "", pissue1 = "", pfiling1 = "", purl1 = "", pdescription1 = "", ptitle2 = "", pappno2 = "", pinventor2 = "", pissue2 = "", pfiling2 = "", purl2 = "", pdescription2 = "", ptitle3 = "", pappno3 = "", pinventor3 = "", pissue3 = "", pfiling3 = "", purl3 = "", pdescription3 = "", ptitle4 = "", pappno4 = "", pinventor4 = "", pissue4 = "", pfiling4 = "", purl4 = "", pdescription4 = "", ptitle5 = "", pappno5 = "", pinventor5 = "", pissue5 = "", pfiling5 = "", purl5 = "", pdescription5 = "", ptitle6 = "", pappno6 = "", pinventor6 = "", pissue6 = "", pfiling6 = "", purl6 = "", pdescription6 = "", ptitle7 = "", pappno7 = "", pinventor7 = "", pissue7 = "", pfiling7 = "", purl7 = "", pdescription7 = "", ptitle8 = "", pappno8 = "", pinventor8 = "", pissue8 = "", pfiling8 = "", purl8 = "", pdescription8 = "", ptitle9 = "", pappno9 = "", pinventor9 = "", pissue9 = "", pfiling9 = "", purl9 = "", pdescription9 = "", ptitle10 = "", pappno10 = "", pinventor10 = "", pissue10 = "", pfiling10 = "", purl10 = "", pdescription10 = "", pselectedcountry1 = "", pselectedcountry2 = "", pselectedcountry3 = "", pselectedcountry4 = "", pselectedcountry5 = "", pselectedcountry6 = "", pselectedcountry7 = "", pselectedcountry8 = "", pselectedcountry9 = "", pselectedcountry10 = "", issuedorpending1 = "", issuedorpending2 = "", issuedorpending3 = "", issuedorpending4 = "", issuedorpending5 = "", issuedorpending6 = "", issuedorpending7 = "", issuedorpending8 = "", issuedorpending9 = "", issuedorpending10 = "";
    String pubtitle1 = "", publication1 = "", author1 = "", puburl1 = "", pubdescription1 = "", pubtitle2 = "", publication2 = "", author2 = "", puburl2 = "", pubdescription2 = "", pubtitle3 = "", publication3 = "", author3 = "", puburl3 = "", pubdescription3 = "", pubtitle4 = "", publication4 = "", author4 = "", puburl4 = "", pubdescription4 = "", pubtitle5 = "", publication5 = "", author5 = "", puburl5 = "", pubdescription5 = "", pubtitle6 = "", publication6 = "", author6 = "", puburl6 = "", pubdescription6 = "", pubtitle7 = "", publication7 = "", author7 = "", puburl7 = "", pubdescription7 = "", pubtitle8 = "", publication8 = "", author8 = "", puburl8 = "", pubdescription8 = "", pubtitle9 = "", publication9 = "", author9 = "", puburl9 = "", pubdescription9 = "", pubtitle10 = "", publication10 = "", author10 = "", puburl10 = "", pubdescription10 = "", publicationdate1 = "", publicationdate2 = "", publicationdate3 = "", publicationdate4 = "", publicationdate5 = "", publicationdate6 = "", publicationdate7 = "", publicationdate8 = "", publicationdate9 = "", publicationdate10 = "";
    String strength1 = "", strength2 = "", strength3 = "", strength4 = "", strength5 = "", strength6 = "", strength7 = "", strength8 = "", strength9 = "", strength10 = "";
    String weak1 = "", weak2 = "", weak3 = "", weak4 = "", weak5 = "", weak6 = "", weak7 = "", weak8 = "", weak9 = "", weak10 = "";
    String location1 = "", location2 = "", location3 = "", location4 = "", location5 = "";
    String careerobj = "", twelthdataobject = "",diplomadataobject="";
    String email2 = "", addressline1 = "", addressline2 = "", addressline3 = "", telephone = "", mobile2 = "";
    String nameasten = "", mothername = "", dob = "", gender = "", mothertongue = "", hobbies = "", bloodgroup = "", category = "", religion = "", caste = "", prn = "", paddrline1 = "", paddrline2 = "", paddrline3 = "", handicapped = "", sports = "", defenceex = "";
    ProgressBar updateProgress;
    SwipeRefreshLayout swipe_refresh_layout;
    int found_box1 = 0, found_tenth = 0, found_twelth = 0, found_diploma = 0, found_ug = 0, found_pgsem = 0, found_pgyear = 0, found_projects = 0, found_lang = 0, found_certificates = 0;
    int found_courses = 0, found_skills = 0, found_honors = 0, found_patents = 0, found_publications = 0, found_careerobj = 0, found_strengths = 0, found_weaknesses = 0, found_locationpreferences = 0;
    int found_contact_details = 0, found_personal = 0;
    JSONParser jParser = new JSONParser();
    JSONObject json;

    String digest1, digest2;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    View rootView;
    StudentData studentData = new StudentData();
    int percentProfile = 0;
    ProgressBar profileprogress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        Digest d = new Digest();
        digest1 = d.getDigest1();
        digest2 = d.getDigest2();

        profileprogress = (ProgressBar) rootView.findViewById(R.id.profileprogress);
        updateProgress = (ProgressBar) rootView.findViewById(R.id.updateProgress);
        swipe_refresh_layout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        swipe_refresh_layout.setRefreshing(true);
        SwipeRefreshLayout tswipe_refresh_layout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setVisibility(View.GONE);
        myprofileimg = (CircleImageView) rootView.findViewById(R.id.myprofileimg);
        myprofilename = (TextView) rootView.findViewById(R.id.myprofilename);
        myprofilrole = (TextView) rootView.findViewById(R.id.myprofilrole);
        myprofiledu = (TextView) rootView.findViewById(R.id.myprofiledu);
        myprofilloc = (TextView) rootView.findViewById(R.id.myprofilloc);
        myprofilemail = (TextView) rootView.findViewById(R.id.myprofilemail);
        myprofilepercenttxt = (TextView) rootView.findViewById(R.id.myprofilepercenttxt);
        editprofiletxt = (TextView) rootView.findViewById(R.id.editprofiletxt);
        eduboxtxt = (TextView) rootView.findViewById(R.id.eduboxtxt);
        projboxtxt = (TextView) rootView.findViewById(R.id.projboxtxt);
        accomplishmentsboxtxt = (TextView) rootView.findViewById(R.id.accomplishmentsboxtxt);
        careerboxtxt = (TextView) rootView.findViewById(R.id.careerboxtxt);
        contactboxtxt = (TextView) rootView.findViewById(R.id.contactboxtxt);
        myprofilecource = (TextView) rootView.findViewById(R.id.myprofilecource);
        myprofilecource2 = (TextView) rootView.findViewById(R.id.myprofilecource2);
        myprofilecource3 = (TextView) rootView.findViewById(R.id.myprofilecource3);
        myprofilecource4 = (TextView) rootView.findViewById(R.id.myprofilecource4);
        myprofileproj1 = (TextView) rootView.findViewById(R.id.myprofileproj1);
        myprofileproj2 = (TextView) rootView.findViewById(R.id.myprofileproj2);
        myprofileproj3 = (TextView) rootView.findViewById(R.id.myprofileproj3);
        acc1txt = (TextView) rootView.findViewById(R.id.acc1txt);
        acc2txt = (TextView) rootView.findViewById(R.id.acc2txt);
        acc3txt = (TextView) rootView.findViewById(R.id.acc3txt);
        acc4txt = (TextView) rootView.findViewById(R.id.acc4txt);
        acc5txt = (TextView) rootView.findViewById(R.id.acc5txt);
        acc6txt = (TextView) rootView.findViewById(R.id.acc6txt);
        acc7txt = (TextView) rootView.findViewById(R.id.acc7txt);
        acc1txttxt = (TextView) rootView.findViewById(R.id.acc1txttxt);
        acc2txttxt = (TextView) rootView.findViewById(R.id.acc2txttxt);
        acc3txttxt = (TextView) rootView.findViewById(R.id.acc3txttxt);
        acc4txttxt = (TextView) rootView.findViewById(R.id.acc4txttxt);
        acc5txttxt = (TextView) rootView.findViewById(R.id.acc5txttxt);
        acc6txttxt = (TextView) rootView.findViewById(R.id.acc6txttxt);
        acc7txttxt = (TextView) rootView.findViewById(R.id.acc7txttxt);
        careerobjtxt = (TextView) rootView.findViewById(R.id.careerobjtxt);
        strengthtxt = (TextView) rootView.findViewById(R.id.strengthtxt);
        weaktxt = (TextView) rootView.findViewById(R.id.weaktxt);
        locpretxt = (TextView) rootView.findViewById(R.id.locpretxt);
        nametxt = (TextView) rootView.findViewById(R.id.nametxt);
        mobiletxt = (TextView) rootView.findViewById(R.id.mobiletxt);
        emailtxt = (TextView) rootView.findViewById(R.id.emailtxt);
        myprofileclgname = (TextView) rootView.findViewById(R.id.myprofileclgname);
        myprofileclgyearofpassing = (TextView) rootView.findViewById(R.id.myprofileclgyearofpassing);
        myprofileclgname2 = (TextView) rootView.findViewById(R.id.myprofileclgname2);
        myprofileclgyearofpassing2 = (TextView) rootView.findViewById(R.id.myprofileclgyearofpassing2);
        myprofileclgname3 = (TextView) rootView.findViewById(R.id.myprofileclgname3);
        myprofileclgyearofpassing3 = (TextView) rootView.findViewById(R.id.myprofileclgyearofpassing3);
        myprofileclgname4 = (TextView) rootView.findViewById(R.id.myprofileclgname4);
        myprofileclgyearofpassing4 = (TextView) rootView.findViewById(R.id.myprofileclgyearofpassing4);
        myprofiledomain1 = (TextView) rootView.findViewById(R.id.myprofiledomain1);
        myprofileduration1 = (TextView) rootView.findViewById(R.id.myprofileduration1);
        myprofiledomain2 = (TextView) rootView.findViewById(R.id.myprofiledomain2);
        myprofileduration2 = (TextView) rootView.findViewById(R.id.myprofileduration2);
        myprofiledomain3 = (TextView) rootView.findViewById(R.id.myprofiledomain3);
        myprofileduration3 = (TextView) rootView.findViewById(R.id.myprofileduration3);
        careerobjtxttxt = (TextView) rootView.findViewById(R.id.careerobj);
        strengthstxt = (TextView) rootView.findViewById(R.id.strengths);
        weaknessestxt = (TextView) rootView.findViewById(R.id.weaknesses);
        locationpreferences = (TextView) rootView.findViewById(R.id.locationpreferences);
        contactaddr1 = (TextView) rootView.findViewById(R.id.contactaddr);

        contactmobile = (TextView) rootView.findViewById(R.id.contactmobile);
        contactemail = (TextView) rootView.findViewById(R.id.contactemail);
        myprofilepreview = (TextView) rootView.findViewById(R.id.myprofilepreview);


        trytxt = (TextView) rootView.findViewById(R.id.trytxt);
        protxt = (TextView) rootView.findViewById(R.id.protxt);
        freetxt = (TextView) rootView.findViewById(R.id.freetxt);

        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/arba.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ubuntu.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/arimo.ttf");
        Typeface custom_font4 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/meriitalic.ttf");
        Typeface custom_font5 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/righteous.ttf");
        Typeface custom_font6 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/rockitbold.ttf");
        Typeface custom_font7 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/portano.ttf");
        Typeface custom_font8 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/montbold.ttf");
        Typeface custom_font10 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/hint.ttf");
        Typeface custom_font11 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/hamm.ttf");
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
        myprofileclgname4.setTypeface(custom_font7);
        myprofileclgyearofpassing3.setTypeface(custom_font7);
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


        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetStudentData().execute();
                ((MainActivity) getActivity()).requestProfileImage();
            }
        });


        introedit = (ImageView) rootView.findViewById(R.id.introedit);
        eduedit = (ImageView) rootView.findViewById(R.id.eduedit);
        projectsedit = (ImageView) rootView.findViewById(R.id.projectsedit);
        accomplishmentsedit = (ImageView) rootView.findViewById(R.id.accomplishmentsedit);
        careeredit = (ImageView) rootView.findViewById(R.id.careeredit);
        contactedit = (ImageView) rootView.findViewById(R.id.contactedit);
        introedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileIntro.class), 0);
                Log.d("MYTAG", "MyProfileIntro called: ");
            }
        });
        eduedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileEdu.class), 0);
            }
        });
        projectsedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileProjects.class), 0);
            }
        });
        accomplishmentsedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileAccomplishments.class), 0);
            }
        });
        careeredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileCareerDetails.class), 0);
            }
        });
        contactedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileContact.class), 0);
            }
        });

        myprofileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        editprofilerl = (RelativeLayout) rootView.findViewById(R.id.editprofilerl);
        editprofilerl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivityForResult(new Intent(getActivity(), EditProfile.class), 0);
            }
        });

        View proselectionview = (View) rootView.findViewById(R.id.proselectionview);
        proselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProSplashScreen.class));

            }
        });

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = sharedpreferences.getString(Username, null);
        role = sharedpreferences.getString("role", null);

        Log.d("in mainfragment", "update user set isactivated=\"no\" where usernamed=\"60/onJpfYmsVdoDTjizGCg7kCu7DzogOMAfO06U4hIc=\"': " + role);

        digest1 = sharedpreferences.getString("digest1", null);
        digest2 = sharedpreferences.getString("digest2", null);

        myprofilrole.setText(role);

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";


        try {
            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(username);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);
            myprofilemail.setText(plainusername);
            contactemail.setText(plainusername);
        } catch (Exception e) {
            Log.d("TAG", "onCreateView: " + e.getMessage());
        }

        refreshContent();

        return rootView;
    }

    public void refreshContent() {
        new GetStudentData().execute();
        ((MainActivity) getActivity()).requestProfileImage();
        updateProgress.setVisibility(View.VISIBLE);

    }

    public void showUpdateProgress() {
        updateProgress.setVisibility(View.VISIBLE);
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
            Log.d("TAG", "getUserCountry: " + e.getMessage());
        }
        return null;
    }

    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Action").setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    startActivity(new Intent(getContext(), ViewProfileImage.class));
                } else if (which == 1) {

                    sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("digest1", digest1);
                    editor.putString("digest2", digest2);
                    editor.putString("plain", plainusername);
                    editor.commit();
                    dialog.cancel();
                    ((MainActivity) getActivity()).requestCropImage();
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

    private class GetStudentData extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            Log.d("TAG", "doInBackground: in GetStudentData");

            try {
                percentProfile = 0;
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));
                json = jParser.makeHttpRequest(MyConstants.load_student_data, "GET", params);

                String s = "";

                resultofop = json.getString("info");
                Log.d("TAG", "info " + resultofop);

                if (resultofop.equals("found")) {

                    phone = json.getString("phone");

                    s = json.getString("intro");
                    if (s.equals("found")) {
                        found_box1 = 1;
                        Log.d("TAG", "found_box1===:-" + found_box1);
                        dataobject = json.getString("introObj");

                        Modelmyprofileintro obj2 = (Modelmyprofileintro) fromString(dataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        fname = obj2.getFirstname();
                        lname = obj2.getLastname();
                        country = obj2.getSelectedCountry();
                        state = obj2.getSelectedState();
                        city = obj2.getSelectedCity();
//
                        studentData.setFname(fname);
                        studentData.setMname(mname);
                        studentData.setLname(lname);
                        studentData.setCountry(country);
                        studentData.setState(state);
                        studentData.setCity(city);

                        Log.d("TAG", "dataobject===: " + dataobject);
                    }

                    s = json.getString("career");
                    if (s.equals("found")) {
                        found_careerobj = 1;

                        careerdataobject = json.getString("careerobj");
                        MyProfileCareerObjModal obj2 = (MyProfileCareerObjModal) fromString(careerdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        careerobj = obj2.careerobj;
                        studentData.setCareerobj(careerobj);


                    }
                    s = json.getString("strengths");
                    if (s.equals("found")) {
                        found_strengths = 1;


                        strengthdataobject = json.getString("strengthsobj");

                        MyProfileStrengthsModal obj2 = (MyProfileStrengthsModal) fromString(strengthdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        strength1 = obj2.sstrength1;
                        strength2 = obj2.sstrength2;
                        strength3 = obj2.sstrength3;
                        strength4 = obj2.sstrength4;
                        strength5 = obj2.sstrength5;
                        strength6 = obj2.sstrength6;
                        strength7 = obj2.sstrength7;
                        strength8 = obj2.sstrength8;
                        strength9 = obj2.sstrength9;
                        strength10 = obj2.sstrength10;

                        studentData.setStrength1(strength1);
                        studentData.setStrength2(strength2);
                        studentData.setStrength3(strength3);
                        studentData.setStrength4(strength4);
                        studentData.setStrength5(strength5);
                        studentData.setStrength6(strength6);
                        studentData.setStrength7(strength7);
                        studentData.setStrength8(strength8);
                        studentData.setStrength9(strength9);
                        studentData.setStrength10(strength10);


                    }
                    s = json.getString("weaknesses");
                    if (s.equals("found")) {
                        found_weaknesses = 1;


                        weaknessesdataobject = json.getString("weaknessesobj");

                        MyProfileWeaknessesModal obj2 = (MyProfileWeaknessesModal) fromString(weaknessesdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));


                        weak1 = obj2.sweak1;
                        weak2 = obj2.sweak2;
                        weak3 = obj2.sweak3;
                        weak4 = obj2.sweak4;
                        weak5 = obj2.sweak5;
                        weak6 = obj2.sweak6;
                        weak7 = obj2.sweak7;
                        weak8 = obj2.sweak8;
                        weak9 = obj2.sweak9;
                        weak10 = obj2.sweak10;


                        studentData.setWeak1(weak1);
                        studentData.setWeak2(weak2);
                        studentData.setWeak3(weak3);
                        studentData.setWeak4(weak4);
                        studentData.setWeak5(weak5);
                        studentData.setWeak6(weak6);
                        studentData.setWeak7(weak7);
                        studentData.setWeak8(weak8);
                        studentData.setWeak9(weak9);
                        studentData.setWeak10(weak10);


                    }

                    s = json.getString("locationpreferences");
                    if (s.equals("found")) {
                        found_locationpreferences = 1;

                        locationpreferencesdataobject = json.getString("locationpreferencesobj");

                        MyProfileLocationModal obj2 = (MyProfileLocationModal) fromString(locationpreferencesdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                        location1 = obj2.slocation1;
                        location2 = obj2.slocation2;
                        location3 = obj2.slocation3;
                        location4 = obj2.slocation4;
                        location5 = obj2.slocation5;

                        studentData.setLocation1(location1);
                        studentData.setLocation2(location2);
                        studentData.setLocation3(location3);
                        studentData.setLocation4(location4);
                        studentData.setLocation5(location5);


                    }

                    s = json.getString("tenth");
                    if (s.equals("found")) {
                        found_tenth = 1;

                        tenthdataobject = json.getString("tenthobj");

                        MyProfileTenthModal obj2 = (MyProfileTenthModal) fromString(tenthdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        yearofpassing10= obj2.monthandyearofpassing;
                        board10 =obj2.selectedBoard;
                        schoolname10 =obj2.schoolname;

                        studentData.setYearofpassing10(yearofpassing10);
                        studentData.setBoard10(board10);
                        studentData.setSchoolname10(schoolname10);
                        studentData.setMarks10(obj2.marksobtained);
                        studentData.setOutof10(obj2.outofmarks);
                        studentData.setPercentage10(obj2.percentage);
                        studentData.setSchoolname10(obj2.schoolname);
                        studentData.setYearofpassing10(obj2.monthandyearofpassing);
                        studentData.setBoard10(obj2.selectedBoard);


                    }

                    s = json.getString("ug");
                    if (s.equals("found")) {
                        found_ug = 1;
                        Log.d("TAG", "found_ug===:-" + found_ug);

                        ugdataobject = json.getString("ugobj");

                        MyProfileUgModal obj2 = (MyProfileUgModal) fromString(ugdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        yearofpassingug = obj2.monthandyearofpassing;
                        collegenameug = obj2.schoolname;
                        universityug = obj2.selectedUniversity;
                        courseug = obj2.selectedCourse;
                        streamug = obj2.selectedStream;

                        studentData.setYearofpassingug(yearofpassingpgyear);
                        studentData.setCollegenameug(collegenameug);
                        studentData.setUniversityug(universityug);

                        studentData.setMarkssem1ug(obj2.markssem1);
                        studentData.setOutofsem1ug(obj2.outofsem1);
                        studentData.setPercentagesem1ug(obj2.percentsem1);
                        studentData.setMarkssem2ug(obj2.markssem2);
                        studentData.setOutofsem2ug(obj2.outofsem2);
                        studentData.setPercentagesem2ug(obj2.percentsem2);
                        studentData.setMarkssem3ug(obj2.markssem3);
                        studentData.setOutofsem3ug(obj2.outofsem3);
                        studentData.setPercentagesem3ug(obj2.percentsem3);
                        studentData.setMarkssem4ug(obj2.markssem4);
                        studentData.setOutofsem4ug(obj2.outofsem4);
                        studentData.setPercentagesem4ug(obj2.percentsem4);
                        studentData.setMarkssem5ug(obj2.markssem5);
                        studentData.setOutofsem5ug(obj2.outofsem5);
                        studentData.setPercentagesem5ug(obj2.percentsem5);
                        studentData.setMarkssem6ug(obj2.markssem6);
                        studentData.setOutofsem6ug(obj2.outofsem6);
                        studentData.setPercentagesem6ug(obj2.percentsem6);
                        studentData.setMarkssem7ug(obj2.markssem7);
                        studentData.setOutofsem7ug(obj2.outofsem7);
                        studentData.setPercentagesem7ug(obj2.percentsem7);
                        studentData.setMarkssem8ug(obj2.markssem8);
                        studentData.setOutofsem8ug(obj2.outofsem8);
                        studentData.setPercentagesem8ug(obj2.percentsem8);
                        studentData.setAggregateug(obj2.aggregate);
                        studentData.setCollegenameug(obj2.schoolname);
                        studentData.setYearofpassingug(obj2.monthandyearofpassing);

                        studentData.setCourseug(obj2.selectedCourse);
                        studentData.setStreamug(obj2.selectedStream);

                        Log.d("TAG", "ugdataobject===: " + ugdataobject);
                    }
                    s = json.getString("twelth");
                    if (s.equals("found")) {
                        found_twelth = 1;
                        Log.d("TAG", "found_twelth===:-" + found_twelth);

                        twelthdataobject = json.getString("twelthobj");

                        MyProfileTwelthModal obj2 = (MyProfileTwelthModal) fromString(twelthdataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        yearofpassing12 = obj2.monthandyearofpassing12;
                        schoolname12 = obj2.schoolnametwelth;
                        board12 = obj2.selectedboardBytes1;

                        studentData.setMarks12(obj2.marksobtained);
                        studentData.setOutof12(obj2.outofmarks);
                        studentData.setPercentage12(obj2.percentage);
                        studentData.setSchoolname12(obj2.schoolnametwelth);
                        studentData.setStream12(obj2.selectedstreamBytes1);
                        studentData.setBoard12(obj2.selectedboardBytes1);
                        studentData.setYearofpassing12(obj2.monthandyearofpassing12);

                        Log.d("TAG", "twelthdataobject===: " + twelthdataobject);
                    }

                    s = json.getString("diploma");
                    if (s.equals("found")) {
                        found_diploma = 1;
                        Log.d("TAG", "found_diploma===:-" + found_diploma);

                        diplomadataobject = json.getString("diplomaobj");

                        MyProfileDiplomaModal obj2 = (MyProfileDiplomaModal) fromString(diplomadataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        yearofpassingdiploma = obj2.monthandyearofpassingdiploma;
                        collegenamediploma = obj2.schoolnamediploma;
                        universitydiploma = obj2.selectedboardBytes1;
                        coursediploma = obj2.selectedcourseBytes1;


                        studentData.setMarkssem1diploma(obj2.markssem1);
                        studentData.setOutofsem1diploma(obj2.outofsem1);
                        studentData.setPercentagesem1diploma(obj2.percentsem1);
                        studentData.setMarkssem2diploma(obj2.markssem2);
                        studentData.setOutofsem2diploma(obj2.outofsem2);
                        studentData.setPercentagesem2diploma(obj2.percentsem2);
                        studentData.setMarkssem3diploma(obj2.markssem3);
                        studentData.setOutofsem3diploma(obj2.outofsem3);
                        studentData.setPercentagesem3diploma(obj2.percentsem3);
                        studentData.setMarkssem4diploma(obj2.markssem4);
                        studentData.setOutofsem4diploma(obj2.outofsem4);
                        studentData.setPercentagesem4diploma(obj2.percentsem4);
                        studentData.setMarkssem5diploma(obj2.markssem5);
                        studentData.setOutofsem5diploma(obj2.outofsem5);
                        studentData.setPercentagesem5diploma(obj2.percentsem5);
                        studentData.setMarkssem6diploma(obj2.markssem6);
                        studentData.setOutofsem6diploma(obj2.outofsem6);
                        studentData.setPercentagesem6diploma(obj2.percentsem6);
                        studentData.setAggregatediploma(obj2.aggregate);
                        studentData.setUniversitydiploma(obj2.selectedboardBytes1);
                        studentData.setCollegenamediploma(obj2.schoolnamediploma);
                        studentData.setYearofpassingdiploma(obj2.monthandyearofpassingdiploma);
                        studentData.setCoursediploma(obj2.selectedcourseBytes1);


                        Log.d("TAG", "diplomadataobject===: " + diplomadataobject);
                    }
                    s = json.getString("pgsem");
                    if (s.equals("found")) {
                        found_pgsem=1;

                        PgSem obj=(PgSem) fromString(json.getString("pgsemdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        markssem1pgsem=obj.getMarkssem1();
                        outofsem1pgsem=obj.getOutofsem1();
                        percentagesem1pgsem=obj.getPercentsem1();
                        markssem2pgsem=obj.getMarkssem2();
                        outofsem2pgsem=obj.getOutofsem2();
                        percentagesem2pgsem=obj.getPercentsem2();
                        markssem3pgsem=obj.getMarkssem3();
                        outofsem3pgsem=obj.getOutofsem3();
                        percentagesem3pgsem=obj.getPercentsem3();
                        markssem4pgsem=obj.getMarkssem4();
                        outofsem4pgsem=obj.getOutofsem4();
                        percentagesem4pgsem=obj.getPercentsem4();
                        markssem5pgsem=obj.getMarkssem5();
                        outofsem5pgsem=obj.getOutofsem5();
                        percentagesem5pgsem=obj.getPercentsem5();
                        markssem6pgsem=obj.getMarkssem6();
                        outofsem6pgsem=obj.getOutofsem6();
                        percentagesem6pgsem=obj.getPercentsem6();
                        aggregatepgsem=obj.getAggregatepgsem();
                        coursepgsem=obj.getSelectedCoursepgsem();
                        streampgsem=obj.getSelectedStreampgsem();
                        universitypgsem=obj.getSelectedUniversitypgsem();
                        collegenamepgsem=obj.getSchoolnamepgsemester();
                        yearofpassingpgsem=obj.getMonthandyearofpassingpgsem();

                        studentData.setMarkssem1pgsem(markssem1pgsem);
                        studentData.setOutofsem1pgsem(outofsem1pgsem);
                        studentData.setPercentagesem1pgsem(percentagesem1pgsem);
                        studentData.setMarkssem2pgsem(markssem2pgsem);
                        studentData.setOutofsem2pgsem(outofsem2pgsem);
                        studentData.setPercentagesem2pgsem(percentagesem2pgsem);
                        studentData.setMarkssem3pgsem(markssem3pgsem);
                        studentData.setOutofsem3pgsem(outofsem3pgsem);
                        studentData.setPercentagesem3pgsem(percentagesem3pgsem);
                        studentData.setMarkssem4pgsem(markssem4pgsem);
                        studentData.setOutofsem4pgsem(outofsem4pgsem);
                        studentData.setPercentagesem4pgsem(percentagesem4pgsem);
                        studentData.setMarkssem5pgsem(markssem5pgsem);
                        studentData.setOutofsem5pgsem(outofsem5pgsem);
                        studentData.setPercentagesem5pgsem(percentagesem5pgsem);
                        studentData.setMarkssem6pgsem(markssem6pgsem);
                        studentData.setOutofsem6pgsem(outofsem6pgsem);
                        studentData.setPercentagesem6pgsem(percentagesem6pgsem);
                        studentData.setAggregatepgsem(aggregatepgsem);
                        studentData.setCoursepgsem(coursepgsem);
                        studentData.setStreampgsem(streampgsem);
                        studentData.setUniversitypgsem(universitypgsem);
                        studentData.setCollegenamepgsem(collegenamepgsem);
                        studentData.setYearofpassingpgsem(yearofpassingpgsem);


                    }
                    s = json.getString("pgyear");
                    if (s.equals("found")) {
                        found_pgyear=1;

                        PgYear obj=(PgYear) fromString(json.getString("pgyeardata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        marksyear1pgyear=obj.getMarksyear1();
                        outofyear1pgyear=obj.getOutofyear1();
                        percentageyear1pgyear=obj.getPercentyear1();
                        marksyear2pgyear=obj.getMarksyear2();
                        outofyear2pgyear=obj.getOutofyear2();
                        percentageyear2pgyear=obj.getPercentyear2();
                        marksyear3pgyear=obj.getMarksyear3();
                        outofyear3pgyear=obj.getOutofyear3();
                        percentageyear3pgyear=obj.getPercentyear3();
                        aggregatepgyear=obj.getAggregatepgyear();
                        coursepgyear=obj.getSelectedCoursepgyear();
                        streampgyear=obj.getSelectedStreampgyear();
                        universitypgyear=obj.getSelectedUniversitypgyear();
                        collegenamepgyear=obj.getSchoolnamepgyears();
                        yearofpassingpgyear=obj.getMonthandyearofpassingpgyear();

                        studentData.setMarksyear1pgyear(marksyear1pgyear);
                        studentData.setOutofyear1pgyear(outofyear1pgyear);
                        studentData.setPercentageyear1pgyear(percentageyear1pgyear);
                        studentData.setMarksyear2pgyear(marksyear2pgyear);
                        studentData.setOutofyear2pgyear(outofyear2pgyear);
                        studentData.setPercentageyear2pgyear(percentageyear2pgyear);
                        studentData.setMarksyear3pgyear(marksyear3pgyear);
                        studentData.setOutofyear3pgyear(outofyear3pgyear);
                        studentData.setPercentageyear3pgyear(percentageyear3pgyear);
                        studentData.setAggregatepgyear(aggregatepgyear);
                        studentData.setCoursepgyear(coursepgyear);
                        studentData.setStreampgyear(streampgyear);
                        studentData.setUniversitypgyear(universitypgyear);
                        studentData.setCollegenamepgyear(collegenamepgyear);
                        studentData.setYearofpassingpgyear(yearofpassingpgyear);
                    }


                    s=json.getString("projects");


                    if(s.equals("found")) {
                        found_projects=1;

                        ArrayList<Projects> projectsList=(ArrayList<Projects>)fromString(json.getString("projectsdata"),MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

                        Projects obj1=projectsList.get(0);
                        Projects obj2=projectsList.get(1);
                        Projects obj3=projectsList.get(2);
                        Projects obj4=projectsList.get(3);
                        Projects obj5=projectsList.get(4);
                        Projects obj6=projectsList.get(5);
                        Projects obj7=projectsList.get(6);
                        Projects obj8=projectsList.get(7);
                        Projects obj9=projectsList.get(8);
                        Projects obj10=projectsList.get(9);



                        proj1=obj1.getProj1();
                        domain1=obj1.getDomain1();
                        team1=obj1.getTeam1();
                        duration1=obj1.getDuration1();

                        proj2=obj2.getProj1();
                        domain2=obj2.getDomain1();
                        team2=obj2.getTeam1();
                        duration2=obj2.getDuration1();

                        proj3=obj3.getProj1();
                        domain3=obj3.getDomain1();
                        team3=obj3.getTeam1();
                        duration3=obj3.getDuration1();

                        proj4=obj4.getProj1();
                        domain4=obj4.getDomain1();
                        team4=obj4.getTeam1();
                        duration4=obj4.getDuration1();

                        proj5=obj5.getProj1();
                        domain5=obj5.getDomain1();
                        team5=obj5.getTeam1();
                        duration5=obj5.getDuration1();

                        proj6=obj6.getProj1();
                        domain6=obj6.getDomain1();
                        team6=obj6.getTeam1();
                        duration6=obj6.getDuration1();

                        proj7=obj7.getProj1();
                        domain7=obj7.getDomain1();
                        team7=obj7.getTeam1();
                        duration7=obj7.getDuration1();

                        proj8=obj8.getProj1();
                        domain8=obj8.getDomain1();
                        team8=obj8.getTeam1();
                        duration8=obj8.getDuration1();

                        proj9=obj9.getProj1();
                        domain9=obj9.getDomain1();
                        team9=obj9.getTeam1();
                        duration9=obj9.getDuration1();

                        proj10=obj10.getProj1();
                        domain10=obj10.getDomain1();
                        team10=obj10.getTeam1();
                        duration10=obj10.getDuration1();

                        studentData.setProj1(proj1);
                        studentData.setDomain1(domain1);
                        studentData.setTeam1(team1);
                        studentData.setDuration1(duration1);
                        studentData.setProj2(proj2);
                        studentData.setDomain2(domain2);
                        studentData.setTeam2(team2);
                        studentData.setDuration2(duration2);
                        studentData.setProj3(proj3);
                        studentData.setDomain3(domain3);
                        studentData.setTeam3(team3);
                        studentData.setDuration3(duration3);
                        studentData.setProj4(proj4);
                        studentData.setDomain4(domain4);
                        studentData.setTeam4(team4);
                        studentData.setDuration4(duration4);
                        studentData.setProj5(proj5);
                        studentData.setDomain5(domain5);
                        studentData.setTeam5(team5);
                        studentData.setDuration5(duration5);
                        studentData.setProj6(proj6);
                        studentData.setDomain6(domain6);
                        studentData.setTeam6(team6);
                        studentData.setDuration6(duration6);
                        studentData.setProj7(proj7);
                        studentData.setDomain7(domain7);
                        studentData.setTeam7(team7);
                        studentData.setDuration7(duration7);
                        studentData.setProj8(proj8);
                        studentData.setDomain8(domain8);
                        studentData.setTeam8(team8);
                        studentData.setDuration8(duration8);
                        studentData.setProj9(proj9);
                        studentData.setDomain9(domain9);
                        studentData.setTeam9(team9);
                        studentData.setDuration9(duration9);
                        studentData.setProj10(proj10);
                        studentData.setDomain10(domain10);
                        studentData.setTeam10(team10);
                        studentData.setDuration10(duration10);


                    }
                    s=json.getString("knownlang");
                    Log.d("TAG", "knownlang: "+s);
                    if(s.equals("found")) {
                        found_lang=1;

                        ArrayList<KnownLangs> knownLangsList=(ArrayList<KnownLangs>)fromString(json.getString("knownlangdata"),MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

                        KnownLangs obj1=knownLangsList.get(0);
                        KnownLangs obj2=knownLangsList.get(1);
                        KnownLangs obj3=knownLangsList.get(2);
                        KnownLangs obj4=knownLangsList.get(3);
                        KnownLangs obj5=knownLangsList.get(4);
                        KnownLangs obj6=knownLangsList.get(5);
                        KnownLangs obj7=knownLangsList.get(6);
                        KnownLangs obj8=knownLangsList.get(7);
                        KnownLangs obj9=knownLangsList.get(8);
                        KnownLangs obj10=knownLangsList.get(9);

                        lang1=obj1.getKnownlang();
                        proficiency1=obj1.getProficiency();
                        lang2=obj2.getKnownlang();
                        proficiency2=obj2.getProficiency();
                        lang3=obj3.getKnownlang();
                        proficiency3=obj3.getProficiency();
                        lang4=obj4.getKnownlang();
                        proficiency4=obj4.getProficiency();
                        lang5=obj5.getKnownlang();
                        proficiency5=obj5.getProficiency();
                        lang6=obj6.getKnownlang();
                        proficiency6=obj6.getProficiency();
                        lang7=obj7.getKnownlang();
                        proficiency7=obj7.getProficiency();
                        lang8=obj8.getKnownlang();
                        proficiency8=obj8.getProficiency();
                        lang9=obj9.getKnownlang();
                        proficiency9=obj9.getProficiency();
                        lang10=obj10.getKnownlang();
                        proficiency10=obj10.getProficiency();

                        studentData.setLang1(lang1);
                        studentData.setProficiency1(proficiency1);
                        studentData.setLang2(lang2);
                        studentData.setProficiency2(proficiency2);
                        studentData.setLang3(lang3);
                        studentData.setProficiency3(proficiency3);
                        studentData.setLang4(lang4);
                        studentData.setProficiency4(proficiency4);
                        studentData.setLang5(lang5);
                        studentData.setProficiency5(proficiency5);
                        studentData.setLang6(lang6);
                        studentData.setProficiency6(proficiency6);
                        studentData.setLang7(lang7);
                        studentData.setProficiency7(proficiency7);
                        studentData.setLang8(lang8);
                        studentData.setProficiency8(proficiency8);
                        studentData.setLang9(lang9);
                        studentData.setProficiency9(proficiency9);
                        studentData.setLang10(lang10);
                        studentData.setProficiency10(proficiency10);


                    }
                    s=json.getString("certificates");
                    if(s.equals("found")) {
                        found_certificates=1;

                        ArrayList<Certificates> certificatesList=(ArrayList<Certificates>)fromString(json.getString("certificatesdata"),MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

                        Certificates obj1=certificatesList.get(0);
                        Certificates obj2=certificatesList.get(1);
                        Certificates obj3=certificatesList.get(2);
                        Certificates obj4=certificatesList.get(3);
                        Certificates obj5=certificatesList.get(4);
                        Certificates obj6=certificatesList.get(5);
                        Certificates obj7=certificatesList.get(6);
                        Certificates obj8=certificatesList.get(7);
                        Certificates obj9=certificatesList.get(8);
                        Certificates obj10=certificatesList.get(9);

                        title1=obj1.getTitle();
                        issuer1=obj1.getIssuer();
                        license1=obj1.getLicense();
                        startdate1certificate=obj1.getStartdate();
                        enddate1certificate=obj1.getEnddate();
                        willexpire1certificate=obj1.getWillexpire();

                        title2=obj2.getTitle();
                        issuer2=obj2.getIssuer();
                        license2=obj2.getLicense();
                        startdate2certificate=obj2.getStartdate();
                        enddate2certificate=obj2.getEnddate();
                        willexpire2certificate=obj2.getWillexpire();

                        title3=obj3.getTitle();
                        issuer3=obj3.getIssuer();
                        license3=obj3.getLicense();
                        startdate3certificate=obj3.getStartdate();
                        enddate3certificate=obj3.getEnddate();
                        willexpire3certificate=obj3.getWillexpire();

                        title4=obj4.getTitle();
                        issuer4=obj4.getIssuer();
                        license4=obj4.getLicense();
                        startdate4certificate=obj4.getStartdate();
                        enddate4certificate=obj4.getEnddate();
                        willexpire4certificate=obj4.getWillexpire();

                        title5=obj5.getTitle();
                        issuer5=obj5.getIssuer();
                        license5=obj5.getLicense();
                        startdate5certificate=obj5.getStartdate();
                        enddate5certificate=obj5.getEnddate();
                        willexpire5certificate=obj5.getWillexpire();

                        title6=obj6.getTitle();
                        issuer6=obj6.getIssuer();
                        license6=obj6.getLicense();
                        startdate6certificate=obj6.getStartdate();
                        enddate6certificate=obj6.getEnddate();
                        willexpire6certificate=obj6.getWillexpire();

                        title7=obj7.getTitle();
                        issuer7=obj7.getIssuer();
                        license7=obj7.getLicense();
                        startdate7certificate=obj7.getStartdate();
                        enddate7certificate=obj7.getEnddate();
                        willexpire7certificate=obj7.getWillexpire();

                        title8=obj8.getTitle();
                        issuer8=obj8.getIssuer();
                        license8=obj8.getLicense();
                        startdate8certificate=obj8.getStartdate();
                        enddate8certificate=obj8.getEnddate();
                        willexpire8certificate=obj8.getWillexpire();

                        title9=obj9.getTitle();
                        issuer9=obj9.getIssuer();
                        license9=obj9.getLicense();
                        startdate9certificate=obj9.getStartdate();
                        enddate9certificate=obj9.getEnddate();
                        willexpire9certificate=obj9.getWillexpire();

                        title10=obj10.getTitle();
                        issuer10=obj10.getIssuer();
                        license10=obj10.getLicense();
                        startdate10certificate=obj10.getStartdate();
                        enddate10certificate=obj10.getEnddate();
                        willexpire10certificate=obj10.getWillexpire();

                        studentData.setTitle1(title1);
                        studentData.setIssuer1(issuer1);
                        studentData.setLicense1(license1);
                        studentData.setStartdate1certificate(startdate1certificate);
                        studentData.setEnddate1certificate(enddate1certificate);
                        studentData.setWillexpire1certificate(willexpire1certificate);
                        studentData.setTitle2(title2);
                        studentData.setIssuer2(issuer2);
                        studentData.setLicense2(license2);
                        studentData.setStartdate2certificate(startdate2certificate);
                        studentData.setEnddate2certificate(enddate2certificate);
                        studentData.setWillexpire2certificate(willexpire2certificate);
                        studentData.setTitle3(title3);
                        studentData.setIssuer3(issuer3);
                        studentData.setLicense3(license3);
                        studentData.setStartdate3certificate(startdate3certificate);
                        studentData.setEnddate3certificate(enddate3certificate);
                        studentData.setWillexpire3certificate(willexpire3certificate);
                        studentData.setTitle4(title4);
                        studentData.setIssuer4(issuer4);
                        studentData.setLicense4(license4);
                        studentData.setStartdate4certificate(startdate4certificate);
                        studentData.setEnddate4certificate(enddate4certificate);
                        studentData.setWillexpire4certificate(willexpire4certificate);
                        studentData.setTitle5(title5);
                        studentData.setIssuer5(issuer5);
                        studentData.setLicense5(license5);
                        studentData.setStartdate5certificate(startdate5certificate);
                        studentData.setEnddate5certificate(enddate5certificate);
                        studentData.setWillexpire5certificate(willexpire5certificate);
                        studentData.setTitle6(title6);
                        studentData.setIssuer6(issuer6);
                        studentData.setLicense6(license6);
                        studentData.setStartdate6certificate(startdate6certificate);
                        studentData.setEnddate6certificate(enddate6certificate);
                        studentData.setWillexpire6certificate(willexpire6certificate);
                        studentData.setTitle7(title7);
                        studentData.setIssuer7(issuer7);
                        studentData.setLicense7(license7);
                        studentData.setStartdate7certificate(startdate7certificate);
                        studentData.setEnddate7certificate(enddate7certificate);
                        studentData.setWillexpire7certificate(willexpire7certificate);
                        studentData.setTitle8(title8);
                        studentData.setIssuer8(issuer8);
                        studentData.setLicense8(license8);
                        studentData.setStartdate8certificate(startdate8certificate);
                        studentData.setEnddate8certificate(enddate8certificate);
                        studentData.setWillexpire8certificate(willexpire8certificate);
                        studentData.setTitle9(title9);
                        studentData.setIssuer9(issuer9);
                        studentData.setLicense9(license9);
                        studentData.setStartdate9certificate(startdate9certificate);
                        studentData.setEnddate9certificate(enddate9certificate);
                        studentData.setWillexpire9certificate(willexpire9certificate);
                        studentData.setTitle10(title10);
                        studentData.setIssuer10(issuer10);
                        studentData.setLicense10(license10);
                        studentData.setStartdate10certificate(startdate10certificate);
                        studentData.setEnddate10certificate(enddate10certificate);
                        studentData.setWillexpire10certificate(willexpire10certificate);

                    }
                    s=json.getString("courses");
                    if(s.equals("found")) {
                        found_courses=1;

                        ArrayList<Courses> coursesList=(ArrayList<Courses>)fromString(json.getString("coursesdata"),MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

                        Courses obj1=coursesList.get(0);
                        Courses obj2=coursesList.get(1);
                        Courses obj3=coursesList.get(2);
                        Courses obj4=coursesList.get(3);
                        Courses obj5=coursesList.get(4);
                        Courses obj6=coursesList.get(5);
                        Courses obj7=coursesList.get(6);
                        Courses obj8=coursesList.get(7);
                        Courses obj9=coursesList.get(8);
                        Courses obj10=coursesList.get(9);

                        course1=obj1.getName();
                        inst1=obj1.getInst();
                        fromdate1=obj1.getFromdate();
                        todate1=obj1.getTodate();

                        course2=obj2.getName();
                        inst2=obj2.getInst();
                        fromdate2=obj2.getFromdate();
                        todate2=obj2.getTodate();

                        course3=obj3.getName();
                        inst3=obj3.getInst();
                        fromdate3=obj3.getFromdate();
                        todate3=obj3.getTodate();

                        course4=obj4.getName();
                        inst4=obj4.getInst();
                        fromdate4=obj4.getFromdate();
                        todate4=obj4.getTodate();

                        course5=obj5.getName();
                        inst5=obj5.getInst();
                        fromdate5=obj5.getFromdate();
                        todate5=obj5.getTodate();

                        course6=obj6.getName();
                        inst6=obj6.getInst();
                        fromdate6=obj6.getFromdate();
                        todate6=obj6.getTodate();

                        course7=obj7.getName();
                        inst7=obj7.getInst();
                        fromdate7=obj7.getFromdate();
                        todate7=obj7.getTodate();

                        course8=obj8.getName();
                        inst8=obj8.getInst();
                        fromdate8=obj8.getFromdate();
                        todate8=obj8.getTodate();

                        course9=obj9.getName();
                        inst9=obj9.getInst();
                        fromdate9=obj9.getFromdate();
                        todate9=obj9.getTodate();

                        course10=obj10.getName();
                        inst10=obj10.getInst();
                        fromdate10=obj10.getFromdate();
                        todate10=obj10.getTodate();

                        studentData.setCourse1(course1);
                        studentData.setInst1(inst1);
                        studentData.setFromdate1(fromdate1);
                        studentData.setTodate1(todate1);
                        studentData.setCourse2(course2);
                        studentData.setInst2(inst2);
                        studentData.setFromdate2(fromdate2);
                        studentData.setTodate2(todate2);
                        studentData.setCourse3(course3);
                        studentData.setInst3(inst3);
                        studentData.setFromdate3(fromdate3);
                        studentData.setTodate3(todate3);
                        studentData.setCourse4(course4);
                        studentData.setInst4(inst4);
                        studentData.setFromdate4(fromdate4);
                        studentData.setTodate4(todate4);
                        studentData.setCourse5(course5);
                        studentData.setInst5(inst5);
                        studentData.setFromdate5(fromdate5);
                        studentData.setTodate5(todate5);
                        studentData.setCourse6(course6);
                        studentData.setInst6(inst6);
                        studentData.setFromdate6(fromdate6);
                        studentData.setTodate6(todate6);
                        studentData.setCourse7(course7);
                        studentData.setInst7(inst7);
                        studentData.setFromdate7(fromdate7);
                        studentData.setTodate7(todate7);
                        studentData.setCourse8(course8);
                        studentData.setInst8(inst8);
                        studentData.setFromdate8(fromdate8);
                        studentData.setTodate8(todate8);
                        studentData.setCourse9(course9);
                        studentData.setInst9(inst9);
                        studentData.setFromdate9(fromdate9);
                        studentData.setTodate9(todate9);
                        studentData.setCourse10(course10);
                        studentData.setInst10(inst10);
                        studentData.setFromdate10(fromdate10);
                        studentData.setTodate10(todate10);

                    }
                    s=json.getString("skills");
                    if(s.equals("found")) {
                        found_skills=1;

                        ArrayList<Skills> skillsList=(ArrayList<Skills>)fromString(json.getString("skillsdata"),MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

                        Skills obj1=skillsList.get(0);
                        Skills obj2=skillsList.get(1);
                        Skills obj3=skillsList.get(2);
                        Skills obj4=skillsList.get(3);
                        Skills obj5=skillsList.get(4);
                        Skills obj6=skillsList.get(5);
                        Skills obj7=skillsList.get(6);
                        Skills obj8=skillsList.get(7);
                        Skills obj9=skillsList.get(8);
                        Skills obj10=skillsList.get(9);
                        Skills obj11=skillsList.get(10);
                        Skills obj12=skillsList.get(11);
                        Skills obj13=skillsList.get(12);
                        Skills obj14=skillsList.get(13);
                        Skills obj15=skillsList.get(14);
                        Skills obj16=skillsList.get(15);
                        Skills obj17=skillsList.get(16);
                        Skills obj18=skillsList.get(17);
                        Skills obj19=skillsList.get(18);
                        Skills obj20=skillsList.get(19);

                        skill1=obj1.getSkill();
                        sproficiency1=obj1.getProficiency();

                        skill2=obj2.getSkill();
                        sproficiency2=obj2.getProficiency();

                        skill3=obj3.getSkill();
                        sproficiency3=obj3.getProficiency();

                        skill4=obj4.getSkill();
                        sproficiency4=obj4.getProficiency();

                        skill5=obj5.getSkill();
                        sproficiency5=obj5.getProficiency();

                        skill6=obj6.getSkill();
                        sproficiency6=obj6.getProficiency();

                        skill7=obj7.getSkill();
                        sproficiency7=obj7.getProficiency();

                        skill8=obj8.getSkill();
                        sproficiency8=obj8.getProficiency();

                        skill9=obj9.getSkill();
                        sproficiency9=obj9.getProficiency();

                        skill10=obj10.getSkill();
                        sproficiency10=obj10.getProficiency();

                        skill11=obj11.getSkill();
                        sproficiency11=obj11.getProficiency();

                        skill12=obj12.getSkill();
                        sproficiency12=obj12.getProficiency();

                        skill13=obj13.getSkill();
                        sproficiency13=obj13.getProficiency();

                        skill14=obj14.getSkill();
                        sproficiency14=obj14.getProficiency();

                        skill15=obj15.getSkill();
                        sproficiency15=obj15.getProficiency();

                        skill16=obj16.getSkill();
                        sproficiency16=obj16.getProficiency();

                        skill17=obj17.getSkill();
                        sproficiency17=obj17.getProficiency();

                        skill18=obj18.getSkill();
                        sproficiency18=obj18.getProficiency();

                        skill19=obj19.getSkill();
                        sproficiency19=obj19.getProficiency();

                        skill20=obj20.getSkill();
                        sproficiency20=obj20.getProficiency();

                        studentData.setSkill1(skill1);
                        studentData.setSproficiency1(sproficiency1);
                        studentData.setSkill2(skill2);
                        studentData.setSproficiency2(sproficiency2);
                        studentData.setSkill3(skill3);
                        studentData.setSproficiency3(sproficiency3);
                        studentData.setSkill4(skill4);
                        studentData.setSproficiency4(sproficiency4);
                        studentData.setSkill5(skill5);
                        studentData.setSproficiency5(sproficiency5);
                        studentData.setSkill6(skill6);
                        studentData.setSproficiency6(sproficiency6);
                        studentData.setSkill7(skill7);
                        studentData.setSproficiency7(sproficiency7);
                        studentData.setSkill8(skill8);
                        studentData.setSproficiency8(sproficiency8);
                        studentData.setSkill9(skill9);
                        studentData.setSproficiency9(sproficiency9);
                        studentData.setSkill10(skill10);
                        studentData.setSproficiency10(sproficiency10);
                        studentData.setSkill11(skill11);
                        studentData.setSproficiency11(sproficiency11);
                        studentData.setSkill12(skill12);
                        studentData.setSproficiency12(sproficiency12);
                        studentData.setSkill13(skill13);
                        studentData.setSproficiency13(sproficiency13);
                        studentData.setSkill14(skill14);
                        studentData.setSproficiency14(sproficiency14);
                        studentData.setSkill15(skill15);
                        studentData.setSproficiency15(sproficiency15);
                        studentData.setSkill16(skill16);
                        studentData.setSproficiency16(sproficiency16);
                        studentData.setSkill17(skill17);
                        studentData.setSproficiency17(sproficiency17);
                        studentData.setSkill18(skill18);
                        studentData.setSproficiency18(sproficiency18);
                        studentData.setSkill19(skill19);
                        studentData.setSproficiency19(sproficiency19);
                        studentData.setSkill20(skill20);
                        studentData.setSproficiency20(sproficiency20);


                    }
                    s=json.getString("honors");
                    if(s.equals("found")) {
                        found_honors=1;

                        ArrayList<Honors> honorsList=(ArrayList<Honors>)fromString(json.getString("honorsdata"),MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

                        Honors obj1=honorsList.get(0);
                        Honors obj2=honorsList.get(1);
                        Honors obj3=honorsList.get(2);
                        Honors obj4=honorsList.get(3);
                        Honors obj5=honorsList.get(4);
                        Honors obj6=honorsList.get(5);
                        Honors obj7=honorsList.get(6);
                        Honors obj8=honorsList.get(7);
                        Honors obj9=honorsList.get(8);
                        Honors obj10=honorsList.get(9);

                        htitle1=obj1.getTitle();
                        hissuer1=obj1.getIssuer();
                        hdescription1=obj1.getDescription();
                        yearofhonor1=obj1.getYearofhonor();

                        htitle2=obj2.getTitle();
                        hissuer2=obj2.getIssuer();
                        hdescription2=obj2.getDescription();
                        yearofhonor2=obj2.getYearofhonor();

                        htitle3=obj3.getTitle();
                        hissuer3=obj3.getIssuer();
                        hdescription3=obj3.getDescription();
                        yearofhonor3=obj3.getYearofhonor();

                        htitle4=obj4.getTitle();
                        hissuer4=obj4.getIssuer();
                        hdescription4=obj4.getDescription();
                        yearofhonor4=obj4.getYearofhonor();

                        htitle5=obj5.getTitle();
                        hissuer5=obj5.getIssuer();
                        hdescription5=obj5.getDescription();
                        yearofhonor5=obj5.getYearofhonor();

                        htitle6=obj6.getTitle();
                        hissuer6=obj6.getIssuer();
                        hdescription6=obj6.getDescription();
                        yearofhonor6=obj6.getYearofhonor();

                        htitle7=obj7.getTitle();
                        hissuer7=obj7.getIssuer();
                        hdescription7=obj7.getDescription();
                        yearofhonor7=obj7.getYearofhonor();

                        htitle8=obj8.getTitle();
                        hissuer8=obj8.getIssuer();
                        hdescription8=obj8.getDescription();
                        yearofhonor8=obj8.getYearofhonor();

                        htitle9=obj9.getTitle();
                        hissuer9=obj9.getIssuer();
                        hdescription9=obj9.getDescription();
                        yearofhonor9=obj9.getYearofhonor();

                        htitle10=obj10.getTitle();
                        hissuer10=obj10.getIssuer();
                        hdescription10=obj10.getDescription();
                        yearofhonor10=obj10.getYearofhonor();

                        studentData.setHtitle1(htitle1);
                        studentData.setHissuer1(hissuer1);
                        studentData.setHdescription1(hdescription1);
                        studentData.setYearofhonor1(yearofhonor1);
                        studentData.setHtitle2(htitle2);
                        studentData.setHissuer2(hissuer2);
                        studentData.setHdescription2(hdescription2);
                        studentData.setYearofhonor2(yearofhonor2);
                        studentData.setHtitle3(htitle3);
                        studentData.setHissuer3(hissuer3);
                        studentData.setHdescription3(hdescription3);
                        studentData.setYearofhonor3(yearofhonor3);
                        studentData.setHtitle4(htitle4);
                        studentData.setHissuer4(hissuer4);
                        studentData.setHdescription4(hdescription4);
                        studentData.setYearofhonor4(yearofhonor4);
                        studentData.setHtitle5(htitle5);
                        studentData.setHissuer5(hissuer5);
                        studentData.setHdescription5(hdescription5);
                        studentData.setYearofhonor5(yearofhonor5);
                        studentData.setHtitle6(htitle6);
                        studentData.setHissuer6(hissuer6);
                        studentData.setHdescription6(hdescription6);
                        studentData.setYearofhonor6(yearofhonor6);
                        studentData.setHtitle7(htitle7);
                        studentData.setHissuer7(hissuer7);
                        studentData.setHdescription7(hdescription7);
                        studentData.setYearofhonor7(yearofhonor7);
                        studentData.setHtitle8(htitle8);
                        studentData.setHissuer8(hissuer8);
                        studentData.setHdescription8(hdescription8);
                        studentData.setYearofhonor8(yearofhonor8);
                        studentData.setHtitle9(htitle9);
                        studentData.setHissuer9(hissuer9);
                        studentData.setHdescription9(hdescription9);
                        studentData.setYearofhonor9(yearofhonor9);
                        studentData.setHtitle10(htitle10);
                        studentData.setHissuer10(hissuer10);
                        studentData.setHdescription10(hdescription10);
                        studentData.setYearofhonor10(yearofhonor10);


                    }

                    s=json.getString("patents");
                    if(s.equals("found")) {
                        found_patents=1;

                        ArrayList<Patents> patentsList=(ArrayList<Patents>)fromString(json.getString("patentsdata"),MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

                        Patents obj1=patentsList.get(0);
                        Patents obj2=patentsList.get(1);
                        Patents obj3=patentsList.get(2);
                        Patents obj4=patentsList.get(3);
                        Patents obj5=patentsList.get(4);
                        Patents obj6=patentsList.get(5);
                        Patents obj7=patentsList.get(6);
                        Patents obj8=patentsList.get(7);
                        Patents obj9=patentsList.get(8);
                        Patents obj10=patentsList.get(9);

                        ptitle1= obj1.getTitle();
                        pappno1= obj1.getAppno();
                        pselectedcountry1= obj1.getPatoffice();
                        pinventor1= obj1.getInventor();
                        issuedorpending1= obj1.getIssuedorpending();
                        pissue1= obj1.getIssue();
                        pfiling1= obj1.getFiling();
                        purl1= obj1.getUrl();
                        pdescription1= obj1.getDescription();

                        ptitle2= obj2.getTitle();
                        pappno2= obj2.getAppno();
                        pselectedcountry2= obj2.getPatoffice();
                        pinventor2= obj2.getInventor();
                        issuedorpending2= obj2.getIssuedorpending();
                        pissue2= obj2.getIssue();
                        pfiling2= obj2.getFiling();
                        purl2= obj2.getUrl();
                        pdescription2= obj2.getDescription();

                        ptitle3= obj3.getTitle();
                        pappno3= obj3.getAppno();
                        pselectedcountry3= obj3.getPatoffice();
                        pinventor3= obj3.getInventor();
                        issuedorpending3= obj3.getIssuedorpending();
                        pissue3= obj3.getIssue();
                        pfiling3= obj3.getFiling();
                        purl3= obj3.getUrl();
                        pdescription3= obj3.getDescription();

                        ptitle4= obj4.getTitle();
                        pappno4= obj4.getAppno();
                        pselectedcountry4= obj4.getPatoffice();
                        pinventor4= obj4.getInventor();
                        issuedorpending4= obj4.getIssuedorpending();
                        pissue4= obj4.getIssue();
                        pfiling4= obj4.getFiling();
                        purl4= obj4.getUrl();
                        pdescription4= obj4.getDescription();

                        ptitle5= obj5.getTitle();
                        pappno5= obj5.getAppno();
                        pselectedcountry5= obj5.getPatoffice();
                        pinventor5= obj5.getInventor();
                        issuedorpending5= obj5.getIssuedorpending();
                        pissue5= obj5.getIssue();
                        pfiling5= obj5.getFiling();
                        purl5= obj5.getUrl();
                        pdescription5= obj5.getDescription();

                        ptitle6= obj6.getTitle();
                        pappno6= obj6.getAppno();
                        pselectedcountry6= obj6.getPatoffice();
                        pinventor6= obj6.getInventor();
                        issuedorpending6= obj6.getIssuedorpending();
                        pissue6= obj6.getIssue();
                        pfiling6= obj6.getFiling();
                        purl6= obj6.getUrl();
                        pdescription6= obj6.getDescription();

                        ptitle7= obj7.getTitle();
                        pappno7= obj7.getAppno();
                        pselectedcountry7= obj7.getPatoffice();
                        pinventor7= obj7.getInventor();
                        issuedorpending7= obj7.getIssuedorpending();
                        pissue7= obj7.getIssue();
                        pfiling7= obj7.getFiling();
                        purl7= obj7.getUrl();
                        pdescription7= obj7.getDescription();

                        ptitle8= obj8.getTitle();
                        pappno8= obj8.getAppno();
                        pselectedcountry8= obj8.getPatoffice();
                        pinventor8= obj8.getInventor();
                        issuedorpending8= obj8.getIssuedorpending();
                        pissue8= obj8.getIssue();
                        pfiling8= obj8.getFiling();
                        purl8= obj8.getUrl();
                        pdescription8= obj8.getDescription();

                        ptitle9= obj9.getTitle();
                        pappno9= obj9.getAppno();
                        pselectedcountry9= obj9.getPatoffice();
                        pinventor9= obj9.getInventor();
                        issuedorpending9= obj9.getIssuedorpending();
                        pissue9= obj9.getIssue();
                        pfiling9= obj9.getFiling();
                        purl9= obj9.getUrl();
                        pdescription9= obj9.getDescription();

                        ptitle10= obj10.getTitle();
                        pappno10= obj10.getAppno();
                        pselectedcountry10= obj10.getPatoffice();
                        pinventor10= obj10.getInventor();
                        issuedorpending10= obj10.getIssuedorpending();
                        pissue10= obj10.getIssue();
                        pfiling10= obj10.getFiling();
                        purl10= obj10.getUrl();
                        pdescription10= obj10.getDescription();

                        studentData.setPtitle1(ptitle1);
                        studentData.setPappno1(pappno1);
                        studentData.setPinventor1(pinventor1);
                        studentData.setPissue1(pissue1);
                        studentData.setPfiling1(pfiling1);
                        studentData.setPurl1(purl1);
                        studentData.setPdescription1(pdescription1);
                        studentData.setPselectedcountry1(pselectedcountry1);
                        studentData.setIssuedorpending1(issuedorpending1);
                        studentData.setPtitle2(ptitle2);
                        studentData.setPappno2(pappno2);
                        studentData.setPinventor2(pinventor2);
                        studentData.setPissue2(pissue2);
                        studentData.setPfiling2(pfiling2);
                        studentData.setPurl2(purl2);
                        studentData.setPdescription2(pdescription2);
                        studentData.setPselectedcountry2(pselectedcountry2);
                        studentData.setIssuedorpending2(issuedorpending2);
                        studentData.setPtitle3(ptitle3);
                        studentData.setPappno3(pappno3);
                        studentData.setPinventor3(pinventor3);
                        studentData.setPissue3(pissue3);
                        studentData.setPfiling3(pfiling3);
                        studentData.setPurl3(purl3);
                        studentData.setPdescription3(pdescription3);
                        studentData.setPselectedcountry3(pselectedcountry3);
                        studentData.setIssuedorpending3(issuedorpending3);
                        studentData.setPtitle4(ptitle4);
                        studentData.setPappno4(pappno4);
                        studentData.setPinventor4(pinventor4);
                        studentData.setPissue4(pissue4);
                        studentData.setPfiling4(pfiling4);
                        studentData.setPurl4(purl4);
                        studentData.setPdescription4(pdescription4);
                        studentData.setPselectedcountry4(pselectedcountry4);
                        studentData.setIssuedorpending4(issuedorpending4);
                        studentData.setPtitle5(ptitle5);
                        studentData.setPappno5(pappno5);
                        studentData.setPinventor5(pinventor5);
                        studentData.setPissue5(pissue5);
                        studentData.setPfiling5(pfiling5);
                        studentData.setPurl5(purl5);
                        studentData.setPdescription5(pdescription5);
                        studentData.setPselectedcountry5(pselectedcountry5);
                        studentData.setIssuedorpending5(issuedorpending5);
                        studentData.setPtitle6(ptitle6);
                        studentData.setPappno6(pappno6);
                        studentData.setPinventor6(pinventor6);
                        studentData.setPissue6(pissue6);
                        studentData.setPfiling6(pfiling6);
                        studentData.setPurl6(purl6);
                        studentData.setPdescription6(pdescription6);
                        studentData.setPselectedcountry6(pselectedcountry6);
                        studentData.setIssuedorpending6(issuedorpending6);
                        studentData.setPtitle7(ptitle7);
                        studentData.setPappno7(pappno7);
                        studentData.setPinventor7(pinventor7);
                        studentData.setPissue7(pissue7);
                        studentData.setPfiling7(pfiling7);
                        studentData.setPurl7(purl7);
                        studentData.setPdescription7(pdescription7);
                        studentData.setPselectedcountry7(pselectedcountry7);
                        studentData.setIssuedorpending7(issuedorpending7);
                        studentData.setPtitle8(ptitle8);
                        studentData.setPappno8(pappno8);
                        studentData.setPinventor8(pinventor8);
                        studentData.setPissue8(pissue8);
                        studentData.setPfiling8(pfiling8);
                        studentData.setPurl8(purl8);
                        studentData.setPdescription8(pdescription8);
                        studentData.setPselectedcountry8(pselectedcountry8);
                        studentData.setIssuedorpending8(issuedorpending8);
                        studentData.setPtitle9(ptitle9);
                        studentData.setPappno9(pappno9);
                        studentData.setPinventor9(pinventor9);
                        studentData.setPissue9(pissue9);
                        studentData.setPfiling9(pfiling9);
                        studentData.setPurl9(purl9);
                        studentData.setPdescription9(pdescription9);
                        studentData.setPselectedcountry9(pselectedcountry9);
                        studentData.setIssuedorpending9(issuedorpending9);
                        studentData.setPtitle10(ptitle10);
                        studentData.setPappno10(pappno10);
                        studentData.setPinventor10(pinventor10);
                        studentData.setPissue10(pissue10);
                        studentData.setPfiling10(pfiling10);
                        studentData.setPurl10(purl10);
                        studentData.setPdescription10(pdescription10);
                        studentData.setPselectedcountry10(pselectedcountry10);
                        studentData.setIssuedorpending10(issuedorpending10);

                    }
                    s=json.getString("publications");
                    if(s.equals("found")) {
                        found_publications=1;

                        ArrayList<Publications> publicationsList=(ArrayList<Publications>)fromString(json.getString("publicationsdata"),MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

                        Publications obj1=publicationsList.get(0);
                        Publications obj2=publicationsList.get(1);
                        Publications obj3=publicationsList.get(2);
                        Publications obj4=publicationsList.get(3);
                        Publications obj5=publicationsList.get(4);
                        Publications obj6=publicationsList.get(5);
                        Publications obj7=publicationsList.get(6);
                        Publications obj8=publicationsList.get(7);
                        Publications obj9=publicationsList.get(8);
                        Publications obj10=publicationsList.get(9);

                        pubtitle1 = obj1.getTitle();
                        publication1 = obj1.getPublication();
                        author1 = obj1.getAuthor();
                        publicationdate1 = obj1.getPublicationdate();
                        puburl1 = obj1.getUrl();
                        pubdescription1= obj1.getDescription();

                        pubtitle2 = obj2.getTitle();
                        publication2 = obj2.getPublication();
                        author2 = obj2.getAuthor();
                        publicationdate2 = obj2.getPublicationdate();
                        puburl2 = obj2.getUrl();
                        pubdescription2= obj2.getDescription();

                        pubtitle3 = obj3.getTitle();
                        publication3 = obj3.getPublication();
                        author3 = obj3.getAuthor();
                        publicationdate3 = obj3.getPublicationdate();
                        puburl3 = obj3.getUrl();
                        pubdescription3= obj3.getDescription();

                        pubtitle4 = obj4.getTitle();
                        publication4 = obj4.getPublication();
                        author4 = obj4.getAuthor();
                        publicationdate4 = obj4.getPublicationdate();
                        puburl4 = obj4.getUrl();
                        pubdescription4= obj4.getDescription();

                        pubtitle5 = obj5.getTitle();
                        publication5 = obj5.getPublication();
                        author5 = obj5.getAuthor();
                        publicationdate5 = obj5.getPublicationdate();
                        puburl5 = obj5.getUrl();
                        pubdescription5= obj5.getDescription();

                        pubtitle6 = obj6.getTitle();
                        publication6 = obj6.getPublication();
                        author6 = obj6.getAuthor();
                        publicationdate6 = obj6.getPublicationdate();
                        puburl6 = obj6.getUrl();
                        pubdescription6= obj6.getDescription();

                        pubtitle7 = obj7.getTitle();
                        publication7 = obj7.getPublication();
                        author7 = obj7.getAuthor();
                        publicationdate7 = obj7.getPublicationdate();
                        puburl7 = obj7.getUrl();
                        pubdescription7= obj7.getDescription();

                        pubtitle8 = obj8.getTitle();
                        publication8 = obj8.getPublication();
                        author8 = obj8.getAuthor();
                        publicationdate8 = obj8.getPublicationdate();
                        puburl8 = obj8.getUrl();
                        pubdescription8= obj8.getDescription();

                        pubtitle9 = obj9.getTitle();
                        publication9 = obj9.getPublication();
                        author9 = obj9.getAuthor();
                        publicationdate9 = obj9.getPublicationdate();
                        puburl9 = obj9.getUrl();
                        pubdescription9= obj9.getDescription();

                        pubtitle10 = obj10.getTitle();
                        publication10 = obj10.getPublication();
                        author10 = obj10.getAuthor();
                        publicationdate10 = obj10.getPublicationdate();
                        puburl10 = obj10.getUrl();
                        pubdescription10= obj10.getDescription();


                    }
                    s = json.getString("personal");
                    if (s.equals("found")) {
                        found_personal = 1;
                        Log.d("TAG", "found_personal===:-" + found_personal);

                        personaldataobject = json.getString("personalobj");



                        MyProfilePersonal obj2 = (MyProfilePersonal) fromString(personaldataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        fname = obj2.fname;
                        mname = obj2.mname;
                        lname = obj2.sname;
                        nameasten = obj2.nameasten;
                        email2 = obj2.alternateemail;
                        mothername = obj2.mothername;
                        dob = obj2.dob;
                        gender = obj2.gender;
                        telephone = obj2.phone;
                        phone = obj2.mobile;
                        mobile2 = obj2.alternatemobile;
                        mothertongue = obj2.mothertongue;
                        hobbies = obj2.hobbies;
                        bloodgroup = obj2.bloodgroup;
                        category = obj2.category;
                        religion = obj2.religion;
                        caste = obj2.caste;
                        prn = obj2.prn;

                        addressline1 = obj2.addrline1c;
                        addressline2 = obj2.addrline2c;
                        addressline3 = obj2.addrline3c;

                        paddrline1 = obj2.addrline1p;
                        paddrline2 = obj2.addrline2p;
                        paddrline3 = obj2.addrline3p;
                        handicapped = obj2.handicapped;
                        sports = obj2.sports;
                        defenceex = obj2.defenceex;

                        studentData.setFname(fname);
                        studentData.setMname(mname);
                        studentData.setLname(lname);
                        studentData.setNameasten(nameasten);
                        studentData.setEmail2(email2);
                        studentData.setMothername(mothername);
                        studentData.setDob(dob);
                        studentData.setGender(gender);

                        studentData.setTelephone(telephone);
                        studentData.setPhone(phone);

                        studentData.setMobile2(mobile2);
                        studentData.setMothertongue(mothertongue);
                        studentData.setHobbies(hobbies);
                        studentData.setBloodgroup(bloodgroup);
                        studentData.setCategory(category);
                        studentData.setReligion(religion);
                        studentData.setCaste(caste);
                        studentData.setPrn(prn);
                        studentData.setLang1(lang1);
                        studentData.setLang2(lang2);
                        studentData.setLang3(lang3);
                        studentData.setLang4(lang4);
                        studentData.setLang5(lang5);
                        studentData.setLang6(lang6);
                        studentData.setLang7(lang7);
                        studentData.setLang8(lang8);
                        studentData.setLang9(lang9);
                        studentData.setLang10(lang10);

                        studentData.setAddressline1(addressline1);

                        studentData.setAddressline2(addressline2);
                        studentData.setAddressline3(addressline3);
                        studentData.setPaddrline1(paddrline1);
                        studentData.setPaddrline2(paddrline2);
                        studentData.setPaddrline3(paddrline3);
                        studentData.setHandicapped(handicapped);
                        studentData.setSports(sports);
                        studentData.setDefenceex(defenceex);

                    }




                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "doInBackground: " + e.getMessage());
            }

            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {

            myprofileimg.setImageBitmap(result);
            swipe_refresh_layout.setVisibility(View.VISIBLE);
            swipe_refresh_layout.setRefreshing(false);
            updateProgress.setVisibility(View.GONE);

            Log.d("TAG", "in onPostExecute: ()- ");
            try {

                downloadImage();

                populateData();

            } catch (Exception e) {
                Toast.makeText(getActivity(), "exp " + e.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("TAG", "onPostExecute: exp = " + e.getMessage());
            }


//        @Override
//        protected Bitmap doInBackground(String... urls) {
//            Bitmap map = null;
//
//
//            try {
//                percentProfile=0;
//
//                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("u",username));
//                json = jParser.makeHttpRequest(MyConstants.load_student_data, "GET", params);
//
//
//                resultofop=json.getString("info");
//                if(resultofop.equals("found"))
//                {
//                    phone=json.getString("phone");
//
//                    String s=json.getString("box1");

//                    if(s.equals("found"))
//                    {
//                        found_box1=1;
//                        fname=json.getString("fname");
//                        mname=json.getString("mname");
//                        lname=json.getString("lname");
//                        country=json.getString("country");
//                        state=json.getString("state");
//                        city=json.getString("city");
//                    }
//                    s=json.getString("tenth");
//                    if(s.equals("found"))
//                    {
//                        found_tenth=1;
//                        marks10=json.getString("marks10");
//                        outof10=json.getString("outof10");
//                        percentage10=json.getString("percentage10");
//                        schoolname10=json.getString("schoolname10");
//                        board10=json.getString("board10");
//                        yearofpassing10=json.getString("yearofpassing10");
//                    }
//                    s=json.getString("twelth");
//                    if(s.equals("found"))
//                    {
//                        found_twelth=1;
//                        marks12=json.getString("marks12");
//                        outof12=json.getString("outof12");
//                        percentage12=json.getString("percentage12");
//                        schoolname12=json.getString("schoolname12");
//                        board12=json.getString("board12");
//                        stream12=json.getString("stream12");
//                        yearofpassing12=json.getString("yearofpassing12");
//                    }
//                    s=json.getString("diploma");
//                    if(s.equals("found"))
//                    {
//                        found_diploma=1;
//                        markssem1diploma=json.getString("markssem1diploma");
//                        outofsem1diploma=json.getString("outofsem1diploma");
//                        percentagesem1diploma=json.getString("percentagesem1diploma");
//                        markssem2diploma=json.getString("markssem2diploma");
//                        outofsem2diploma=json.getString("outofsem2diploma");
//                        percentagesem2diploma=json.getString("percentagesem2diploma");
//                        markssem3diploma=json.getString("markssem3diploma");
//                        outofsem3diploma=json.getString("outofsem3diploma");
//                        percentagesem3diploma=json.getString("percentagesem3diploma");
//                        markssem4diploma=json.getString("markssem4diploma");
//                        outofsem4diploma=json.getString("outofsem4diploma");
//                        percentagesem4diploma=json.getString("percentagesem4diploma");
//                        markssem5diploma=json.getString("markssem5diploma");
//                        outofsem5diploma=json.getString("outofsem5diploma");
//                        percentagesem5diploma=json.getString("percentagesem5diploma");
//                        markssem6diploma=json.getString("markssem6diploma");
//                        outofsem6diploma=json.getString("outofsem6diploma");
//                        percentagesem6diploma=json.getString("percentagesem6diploma");
//                        aggregatediploma=json.getString("aggregatediploma");
//                        coursediploma=json.getString("coursediploma");
//                        streamdiploma=json.getString("streamdiploma");
//                        universitydiploma=json.getString("universitydiploma");
//                        collegenamediploma=json.getString("collegenamediploma");
//                        yearofpassingdiploma=json.getString("yearofpassingdiploma");
//                    }
//                    s=json.getString("ug");
//                    if(s.equals("found"))
//                    {
//                        found_ug=1;
//                        markssem1ug=json.getString("markssem1ug");
//                        outofsem1ug=json.getString("outofsem1ug");
//                        percentagesem1ug=json.getString("percentagesem1ug");
//                        markssem2ug=json.getString("markssem2ug");
//                        outofsem2ug=json.getString("outofsem2ug");
//                        percentagesem2ug=json.getString("percentagesem2ug");
//                        markssem3ug=json.getString("markssem3ug");
//                        outofsem3ug=json.getString("outofsem3ug");
//                        percentagesem3ug=json.getString("percentagesem3ug");
//                        markssem4ug=json.getString("markssem4ug");
//                        outofsem4ug=json.getString("outofsem4ug");
//                        percentagesem4ug=json.getString("percentagesem4ug");
//                        markssem5ug=json.getString("markssem5ug");
//                        outofsem5ug=json.getString("outofsem5ug");
//                        percentagesem5ug=json.getString("percentagesem5ug");
//                        markssem6ug=json.getString("markssem6ug");
//                        outofsem6ug=json.getString("outofsem6ug");
//                        percentagesem6ug=json.getString("percentagesem6ug");
//                        markssem7ug=json.getString("markssem7ug");
//                        outofsem7ug=json.getString("outofsem7ug");
//                        percentagesem7ug=json.getString("percentagesem7ug");
//                        markssem8ug=json.getString("markssem8ug");
//                        outofsem8ug=json.getString("outofsem8ug");
//                        percentagesem8ug=json.getString("percentagesem8ug");
//                        aggregateug=json.getString("aggregateug");
//                        courseug=json.getString("courseug");
//                        streamug=json.getString("streamug");
//                        universityug=json.getString("universityug");
//                        collegenameug=json.getString("collegenameug");
//                        yearofpassingug=json.getString("yearofpassingug");
//                    }
//                    s=json.getString("pgsem");
//                    if(s.equals("found"))
//                    {
//                        found_pgsem=1;
//                        markssem1pgsem=json.getString("markssem1pgsem");
//                        outofsem1pgsem=json.getString("outofsem1pgsem");
//                        percentagesem1pgsem=json.getString("percentagesem1pgsem");
//                        markssem2pgsem=json.getString("markssem2pgsem");
//                        outofsem2pgsem=json.getString("outofsem2pgsem");
//                        percentagesem2pgsem=json.getString("percentagesem2pgsem");
//                        markssem3pgsem=json.getString("markssem3pgsem");
//                        outofsem3pgsem=json.getString("outofsem3pgsem");
//                        percentagesem3pgsem=json.getString("percentagesem3pgsem");
//                        markssem4pgsem=json.getString("markssem4pgsem");
//                        outofsem4pgsem=json.getString("outofsem4pgsem");
//                        percentagesem4pgsem=json.getString("percentagesem4pgsem");
//                        markssem5pgsem=json.getString("markssem5pgsem");
//                        outofsem5pgsem=json.getString("outofsem5pgsem");
//                        percentagesem5pgsem=json.getString("percentagesem5pgsem");
//                        markssem6pgsem=json.getString("markssem6pgsem");
//                        outofsem6pgsem=json.getString("outofsem6pgsem");
//                        percentagesem6pgsem=json.getString("percentagesem6pgsem");
//                        aggregatepgsem=json.getString("aggregatepgsem");
//                        coursepgsem=json.getString("coursepgsem");
//                        streampgsem=json.getString("streampgsem");
//                        universitypgsem=json.getString("universitypgsem");
//                        collegenamepgsem=json.getString("collegenamepgsem");
//                        yearofpassingpgsem=json.getString("yearofpassingpgsem");
//                    }
//                    s=json.getString("pgyear");
//                    if(s.equals("found"))
//                    {
//                        found_pgyear=1;
//                        marksyear1pgyear=json.getString("marksyear1pgyear");
//                        outofyear1pgyear=json.getString("outofyear1pgyear");
//                        percentageyear1pgyear=json.getString("percentageyear1pgyear");
//                        marksyear2pgyear=json.getString("marksyear2pgyear");
//                        outofyear2pgyear=json.getString("outofyear2pgyear");
//                        percentageyear2pgyear=json.getString("percentageyear2pgyear");
//                        marksyear3pgyear=json.getString("marksyear3pgyear");
//                        outofyear3pgyear=json.getString("outofyear3pgyear");
//                        percentageyear3pgyear=json.getString("percentageyear3pgyear");
//                        aggregatepgyear=json.getString("aggregatepgyear");
//                        coursepgyear=json.getString("coursepgyear");
//                        streampgyear=json.getString("streampgyear");
//                        universitypgyear=json.getString("universitypgyear");
//                        collegenamepgyear=json.getString("collegenamepgyear");
//                        yearofpassingpgyear=json.getString("yearofpassingpgyear");
//                    }
//                    s=json.getString("projects");
//                    if(s.equals("found")) {
//                        found_projects=1;
//                        proj1 = json.getString("name1");
//                        domain1 = json.getString("domain1");
//                        team1 = json.getString("teamsize1");
//                        duration1 = json.getString("duration1");
//                        proj2 = json.getString("name2");
//                        domain2 = json.getString("domain2");
//                        team2 = json.getString("teamsize2");
//                        duration2 = json.getString("duration2");
//                        proj3 = json.getString("name3");
//                        domain3 = json.getString("domain3");
//                        team3 = json.getString("teamsize3");
//                        duration3 = json.getString("duration3");
//                        proj4 = json.getString("name4");
//                        domain4 = json.getString("domain4");
//                        team4 = json.getString("teamsize4");
//                        duration4 = json.getString("duration4");
//                        proj5 = json.getString("name5");
//                        domain5 = json.getString("domain5");
//                        team5 = json.getString("teamsize5");
//                        duration5 = json.getString("duration5");
//                        proj6 = json.getString("name6");
//                        domain6 = json.getString("domain6");
//                        team6 = json.getString("teamsize6");
//                        duration6 = json.getString("duration6");
//                        proj7 = json.getString("name7");
//                        domain7 = json.getString("domain7");
//                        team7 = json.getString("teamsize7");
//                        duration7 = json.getString("duration7");
//                        proj8 = json.getString("name8");
//                        domain8 = json.getString("domain8");
//                        team8 = json.getString("teamsize8");
//                        duration8 = json.getString("duration8");
//                        proj9 = json.getString("name9");
//                        domain9 = json.getString("domain9");
//                        team9 = json.getString("teamsize9");
//                        duration9 = json.getString("duration9");
//                        proj10 = json.getString("name10");
//                        domain10 = json.getString("domain10");
//                        team10 = json.getString("teamsize10");
//                        duration10 = json.getString("duration10");
//                    }
//                    s=json.getString("knownlang");
//                    if(s.equals("found")) {
//                        found_lang=1;
//                        lang1 = json.getString("lang1");
//                        proficiency1 = json.getString("proficiency1");
//                        lang2 = json.getString("lang2");
//                        proficiency2 = json.getString("proficiency2");
//                        lang3 = json.getString("lang3");
//                        proficiency3 = json.getString("proficiency3");
//                        lang4 = json.getString("lang4");
//                        proficiency4 = json.getString("proficiency4");
//                        lang5 = json.getString("lang5");
//                        proficiency5 = json.getString("proficiency5");
//                        lang6 = json.getString("lang6");
//                        proficiency6 = json.getString("proficiency6");
//                        lang7 = json.getString("lang7");
//                        proficiency7 = json.getString("proficiency7");
//                        lang8 = json.getString("lang8");
//                        proficiency8 = json.getString("proficiency8");
//                        lang9 = json.getString("lang9");
//                        proficiency9 = json.getString("proficiency9");
//                        lang10 = json.getString("lang10");
//                        proficiency10 = json.getString("proficiency10");
//
//                    }
//                    s=json.getString("certificates");
//                    if(s.equals("found")) {
//                        found_certificates=1;
//                        title1=json.getString("title1certi");
//                        issuer1=json.getString("issuer1certi");
//                        license1=json.getString("license1certi");
//                        startdate1certificate=json.getString("startdate1certi");
//                        enddate1certificate=json.getString("enddate1certi");
//                        willexpire1certificate=json.getString("willexpire1certi");
//
//                        title2=json.getString("title2certi");
//                        issuer2=json.getString("issuer2certi");
//                        license2=json.getString("license2certi");
//                        startdate2certificate=json.getString("startdate2certi");
//                        enddate2certificate=json.getString("enddate2certi");
//                        willexpire2certificate=json.getString("willexpire2certi");
//
//                        title3=json.getString("title3certi");
//                        issuer3=json.getString("issuer3certi");
//                        license3=json.getString("license3certi");
//                        startdate3certificate=json.getString("startdate3certi");
//                        enddate3certificate=json.getString("enddate3certi");
//                        willexpire3certificate=json.getString("willexpire3certi");
//
//                        title4=json.getString("title4certi");
//                        issuer4=json.getString("issuer4certi");
//                        license4=json.getString("license4certi");
//                        startdate4certificate=json.getString("startdate4certi");
//                        enddate4certificate=json.getString("enddate4certi");
//                        willexpire4certificate=json.getString("willexpire4certi");
//
//                        title5=json.getString("title5certi");
//                        issuer5=json.getString("issuer5certi");
//                        license5=json.getString("license5certi");
//                        startdate5certificate=json.getString("startdate5certi");
//                        enddate5certificate=json.getString("enddate5certi");
//                        willexpire5certificate=json.getString("willexpire5certi");
//
//                        title6=json.getString("title6certi");
//                        issuer6=json.getString("issuer6certi");
//                        license6=json.getString("license6certi");
//                        startdate6certificate=json.getString("startdate6certi");
//                        enddate6certificate=json.getString("enddate6certi");
//                        willexpire6certificate=json.getString("willexpire6certi");
//
//                        title7=json.getString("title7certi");
//                        issuer7=json.getString("issuer7certi");
//                        license7=json.getString("license7certi");
//                        startdate7certificate=json.getString("startdate7certi");
//                        enddate7certificate=json.getString("enddate7certi");
//                        willexpire7certificate=json.getString("willexpire7certi");
//
//                        title8=json.getString("title8certi");
//                        issuer8=json.getString("issuer8certi");
//                        license8=json.getString("license8certi");
//                        startdate8certificate=json.getString("startdate8certi");
//                        enddate8certificate=json.getString("enddate8certi");
//                        willexpire8certificate=json.getString("willexpire8certi");
//
//                        title9=json.getString("title9certi");
//                        issuer9=json.getString("issuer9certi");
//                        license9=json.getString("license9certi");
//                        startdate9certificate=json.getString("startdate9certi");
//                        enddate9certificate=json.getString("enddate9certi");
//                        willexpire9certificate=json.getString("willexpire9certi");
//
//                        title10=json.getString("title10certi");
//                        issuer10=json.getString("issuer10certi");
//                        license10=json.getString("license10certi");
//                        startdate10certificate=json.getString("startdate10certi");
//                        enddate10certificate=json.getString("enddate10certi");
//                        willexpire10certificate=json.getString("willexpire10certi");
//                    }
//                    s=json.getString("courses");
//                    if(s.equals("found")) {
//                        found_courses=1;
//                        course1 = json.getString("coursename1");
//                        inst1 = json.getString("inst1");
//                        fromdate1 = json.getString("fromdate1");
//                        todate1 = json.getString("todate1");
//                        course2 = json.getString("coursename2");
//                        inst2 = json.getString("inst2");
//                        fromdate2 = json.getString("fromdate2");
//                        todate2 = json.getString("todate2");
//                        course3 = json.getString("coursename3");
//                        inst3 = json.getString("inst3");
//                        fromdate3 = json.getString("fromdate3");
//                        todate3 = json.getString("todate3");
//                        course4 = json.getString("coursename4");
//                        inst4 = json.getString("inst4");
//                        fromdate4 = json.getString("fromdate4");
//                        todate4 = json.getString("todate4");
//                        course5 = json.getString("coursename5");
//                        inst5 = json.getString("inst5");
//                        fromdate5 = json.getString("fromdate5");
//                        todate5 = json.getString("todate5");
//                        course6 = json.getString("coursename6");
//                        inst6 = json.getString("inst6");
//                        fromdate6 = json.getString("fromdate6");
//                        todate6 = json.getString("todate6");
//                        course7 = json.getString("coursename7");
//                        inst7 = json.getString("inst7");
//                        fromdate7 = json.getString("fromdate7");
//                        todate7 = json.getString("todate7");
//                        course8 = json.getString("coursename8");
//                        inst8 = json.getString("inst8");
//                        fromdate8 = json.getString("fromdate8");
//                        todate8 = json.getString("todate8");
//                        course9 = json.getString("coursename9");
//                        inst9 = json.getString("inst9");
//                        fromdate9 = json.getString("fromdate9");
//                        todate9 = json.getString("todate9");
//                        course10 = json.getString("coursename10");
//                        inst10 = json.getString("inst10");
//                        fromdate10 = json.getString("fromdate10");
//                        todate10 = json.getString("todate10");
//
//                    }
//                    s=json.getString("skills");
//                    if(s.equals("found")) {
//                        found_skills=1;
//                        skill1 = json.getString("skill1");
//                        sproficiency1= json.getString("sproficiency1");
//                        skill2 = json.getString("skill2");
//                        sproficiency2= json.getString("sproficiency2");
//                        skill3 = json.getString("skill3");
//                        sproficiency3= json.getString("sproficiency3");
//                        skill4 = json.getString("skill4");
//                        sproficiency4= json.getString("sproficiency4");
//                        skill5 = json.getString("skill5");
//                        sproficiency5= json.getString("sproficiency5");
//                        skill6 = json.getString("skill6");
//                        sproficiency6= json.getString("sproficiency6");
//                        skill7 = json.getString("skill7");
//                        sproficiency7= json.getString("sproficiency7");
//                        skill8 = json.getString("skill8");
//                        sproficiency8= json.getString("sproficiency8");
//                        skill9 = json.getString("skill9");
//                        sproficiency9= json.getString("sproficiency9");
//                        skill10 = json.getString("skill10");
//                        sproficiency10= json.getString("sproficiency10");
//                        skill11 = json.getString("skill11");
//                        sproficiency11= json.getString("sproficiency11");
//                        skill12 = json.getString("skill12");
//                        sproficiency12= json.getString("sproficiency12");
//                        skill13 = json.getString("skill13");
//                        sproficiency13= json.getString("sproficiency13");
//                        skill14 = json.getString("skill14");
//                        sproficiency14= json.getString("sproficiency14");
//                        skill15 = json.getString("skill15");
//                        sproficiency15= json.getString("sproficiency15");
//                        skill16 = json.getString("skill16");
//                        sproficiency16= json.getString("sproficiency16");
//                        skill17 = json.getString("skill17");
//                        sproficiency17= json.getString("sproficiency17");
//                        skill18 = json.getString("skill18");
//                        sproficiency18= json.getString("sproficiency18");
//                        skill19 = json.getString("skill19");
//                        sproficiency19= json.getString("sproficiency19");
//                        skill20 = json.getString("skill20");
//                        sproficiency20= json.getString("sproficiency20");
//                    }
//                    s=json.getString("honors");
//                    if(s.equals("found")) {
//                        found_honors=1;
//                        htitle1 = json.getString("title1honor");
//                        hissuer1= json.getString("issuer1honor");
//                        hdescription1= json.getString("description1honor");
//                        yearofhonor1= json.getString("yearofhonor1");
//                        htitle2 = json.getString("title2honor");
//                        hissuer2= json.getString("issuer2honor");
//                        hdescription2= json.getString("description2honor");
//                        yearofhonor2= json.getString("yearofhonor2");
//                        htitle3 = json.getString("title3honor");
//                        hissuer3= json.getString("issuer3honor");
//                        hdescription3= json.getString("description3honor");
//                        yearofhonor3= json.getString("yearofhonor3");
//                        htitle4 = json.getString("title4honor");
//                        hissuer4= json.getString("issuer4honor");
//                        hdescription4= json.getString("description4honor");
//                        yearofhonor4= json.getString("yearofhonor4");
//                        htitle5 = json.getString("title5honor");
//                        hissuer5= json.getString("issuer5honor");
//                        hdescription5= json.getString("description5honor");
//                        yearofhonor5= json.getString("yearofhonor5");
//                        htitle6 = json.getString("title6honor");
//                        hissuer6= json.getString("issuer6honor");
//                        hdescription6= json.getString("description6honor");
//                        yearofhonor6= json.getString("yearofhonor6");
//                        htitle7 = json.getString("title7honor");
//                        hissuer7= json.getString("issuer7honor");
//                        hdescription7= json.getString("description7honor");
//                        yearofhonor7= json.getString("yearofhonor7");
//                        htitle8 = json.getString("title8honor");
//                        hissuer8= json.getString("issuer8honor");
//                        hdescription8= json.getString("description8honor");
//                        yearofhonor8= json.getString("yearofhonor8");
//                        htitle9 = json.getString("title9honor");
//                        hissuer9= json.getString("issuer9honor");
//                        hdescription9= json.getString("description9honor");
//                        yearofhonor9= json.getString("yearofhonor9");
//                        htitle10 = json.getString("title10honor");
//                        hissuer10= json.getString("issuer10honor");
//                        hdescription10= json.getString("description10honor");
//                        yearofhonor10= json.getString("yearofhonor10");
//                    }
//
//                    s=json.getString("patents");
//                    if(s.equals("found")) {
//                        found_patents=1;
//
//                        ptitle1 = json.getString("title1patent");
//                        pappno1 = json.getString("appno1patent");
//                        pselectedcountry1 = json.getString("selectedcountry1patent");
//                        pinventor1= json.getString("inventor1patent");
//                        issuedorpending1= json.getString("issuedorpending1patent");
//                        pissue1= json.getString("issue1patent");
//                        pfiling1= json.getString("filing1patent");
//                        purl1= json.getString("url1patent");
//                        pdescription1= json.getString("description1patent");
//
//                        ptitle2 = json.getString("title2patent");
//                        pappno2 = json.getString("appno2patent");
//                        pselectedcountry2 = json.getString("selectedcountry2patent");
//                        pinventor2= json.getString("inventor2patent");
//                        issuedorpending2= json.getString("issuedorpending2patent");
//                        pissue2= json.getString("issue2patent");
//                        pfiling2= json.getString("filing2patent");
//                        purl2= json.getString("url2patent");
//                        pdescription2= json.getString("description2patent");
//                        ptitle3 = json.getString("title3patent");
//                        pappno3 = json.getString("appno3patent");
//                        pselectedcountry3 = json.getString("selectedcountry3patent");
//                        pinventor3= json.getString("inventor3patent");
//                        issuedorpending3= json.getString("issuedorpending3patent");
//                        pissue3= json.getString("issue3patent");
//                        pfiling3= json.getString("filing3patent");
//                        purl3= json.getString("url3patent");
//                        pdescription3= json.getString("description3patent");
//                        ptitle4 = json.getString("title4patent");
//                        pappno4 = json.getString("appno4patent");
//                        pselectedcountry4 = json.getString("selectedcountry4patent");
//                        pinventor4= json.getString("inventor4patent");
//                        issuedorpending4= json.getString("issuedorpending4patent");
//                        pissue4= json.getString("issue4patent");
//                        pfiling4= json.getString("filing4patent");
//                        purl4= json.getString("url4patent");
//                        pdescription4= json.getString("description4patent");
//                        ptitle5 = json.getString("title5patent");
//                        pappno5 = json.getString("appno5patent");
//                        pselectedcountry5 = json.getString("selectedcountry5patent");
//                        pinventor5= json.getString("inventor5patent");
//                        issuedorpending5= json.getString("issuedorpending5patent");
//                        pissue5= json.getString("issue5patent");
//                        pfiling5= json.getString("filing5patent");
//                        purl5= json.getString("url5patent");
//                        pdescription5= json.getString("description5patent");
//                        ptitle6 = json.getString("title6patent");
//                        pappno6 = json.getString("appno6patent");
//                        pselectedcountry6 = json.getString("selectedcountry6patent");
//                        pinventor6= json.getString("inventor6patent");
//                        issuedorpending6= json.getString("issuedorpending6patent");
//                        pissue6= json.getString("issue6patent");
//                        pfiling6= json.getString("filing6patent");
//                        purl6= json.getString("url6patent");
//                        pdescription6= json.getString("description6patent");
//                        ptitle7 = json.getString("title7patent");
//                        pappno7 = json.getString("appno7patent");
//                        pselectedcountry7 = json.getString("selectedcountry7patent");
//                        pinventor7= json.getString("inventor7patent");
//                        issuedorpending7= json.getString("issuedorpending7patent");
//                        pissue7= json.getString("issue7patent");
//                        pfiling7= json.getString("filing7patent");
//                        purl7= json.getString("url7patent");
//                        pdescription7= json.getString("description7patent");
//                        ptitle8 = json.getString("title8patent");
//                        pappno8 = json.getString("appno8patent");
//                        pselectedcountry8 = json.getString("selectedcountry8patent");
//                        pinventor8= json.getString("inventor8patent");
//                        issuedorpending8= json.getString("issuedorpending8patent");
//                        pissue8= json.getString("issue8patent");
//                        pfiling8= json.getString("filing8patent");
//                        purl8= json.getString("url8patent");
//                        pdescription8= json.getString("description8patent");
//                        ptitle9 = json.getString("title9patent");
//                        pappno9 = json.getString("appno9patent");
//                        pselectedcountry9 = json.getString("selectedcountry9patent");
//                        pinventor9= json.getString("inventor9patent");
//                        issuedorpending9= json.getString("issuedorpending9patent");
//                        pissue9= json.getString("issue9patent");
//                        pfiling9= json.getString("filing9patent");
//                        purl9= json.getString("url9patent");
//                        pdescription9= json.getString("description9patent");
//                        ptitle10 = json.getString("title10patent");
//                        pappno10 = json.getString("appno10patent");
//                        pselectedcountry10 = json.getString("selectedcountry10patent");
//                        pinventor10= json.getString("inventor10patent");
//                        issuedorpending10= json.getString("issuedorpending10patent");
//                        pissue10= json.getString("issue10patent");
//                        pfiling10= json.getString("filing10patent");
//                        purl10= json.getString("url10patent");
//                        pdescription10= json.getString("description10patent");
//
//                    }
//                    s=json.getString("publications");
//                    if(s.equals("found")) {
//                        found_publications=1;
//                        pubtitle1 = json.getString("title1publication");
//                        publication1 = json.getString("publication1");
//                        author1 = json.getString("author1");
//                        publicationdate1 = json.getString("publicationdate1");
//                        puburl1 = json.getString("url1publication");
//                        pubdescription1= json.getString("description1publication");
//                        pubtitle2 = json.getString("title2publication");
//                        publication2 = json.getString("publication2");
//                        author2 = json.getString("author2");
//                        publicationdate2 = json.getString("publicationdate2");
//                        puburl2 = json.getString("url2publication");
//                        pubdescription2= json.getString("description2publication");
//                        pubtitle3 = json.getString("title3publication");
//                        publication3 = json.getString("publication3");
//                        author3 = json.getString("author3");
//                        publicationdate3 = json.getString("publicationdate3");
//                        puburl3 = json.getString("url3publication");
//                        pubdescription3= json.getString("description3publication");
//                        pubtitle4 = json.getString("title4publication");
//                        publication4 = json.getString("publication4");
//                        author4 = json.getString("author4");
//                        publicationdate4 = json.getString("publicationdate4");
//                        puburl4 = json.getString("url4publication");
//                        pubdescription4= json.getString("description4publication");
//                        pubtitle5 = json.getString("title5publication");
//                        publication5 = json.getString("publication5");
//                        author5 = json.getString("author5");
//                        publicationdate5 = json.getString("publicationdate5");
//                        puburl5 = json.getString("url5publication");
//                        pubdescription5= json.getString("description5publication");
//                        pubtitle6 = json.getString("title6publication");
//                        publication6 = json.getString("publication6");
//                        author6 = json.getString("author6");
//                        publicationdate6 = json.getString("publicationdate6");
//                        puburl6 = json.getString("url6publication");
//                        pubdescription6= json.getString("description6publication");
//                        pubtitle7 = json.getString("title7publication");
//                        publication7 = json.getString("publication7");
//                        author7 = json.getString("author7");
//                        publicationdate7 = json.getString("publicationdate7");
//                        puburl7 = json.getString("url7publication");
//                        pubdescription7= json.getString("description7publication");
//                        pubtitle8 = json.getString("title8publication");
//                        publication8 = json.getString("publication8");
//                        author8 = json.getString("author8");
//                        publicationdate8 = json.getString("publicationdate8");
//                        puburl8 = json.getString("url8publication");
//                        pubdescription8= json.getString("description8publication");
//                        pubtitle9 = json.getString("title9publication");
//                        publication9 = json.getString("publication9");
//                        author9 = json.getString("author9");
//                        publicationdate9 = json.getString("publicationdate9");
//                        puburl9 = json.getString("url9publication");
//                        pubdescription9= json.getString("description9publication");
//                        pubtitle10 = json.getString("title10publication");
//                        publication10 = json.getString("publication10");
//                        author10 = json.getString("author10");
//                        publicationdate10 = json.getString("publicationdate10");
//                        puburl10 = json.getString("url10publication");
//                        pubdescription10= json.getString("description10publication");
//                    }
//                    s=json.getString("careerobj");
//                    if(s.equals("found")) {
//                        found_careerobj=1;
//                        careerobj = json.getString("careerobjective");
//                    }
//                    s=json.getString("strengths");
//                    if(s.equals("found")) {
//                        found_strengths=1;
//                        strength1 = json.getString("strength1");
//                        strength2 = json.getString("strength2");
//                        strength3 = json.getString("strength3");
//                        strength4 = json.getString("strength4");
//                        strength5 = json.getString("strength5");
//                        strength6 = json.getString("strength6");
//                        strength7 = json.getString("strength7");
//                        strength8 = json.getString("strength8");
//                        strength9 = json.getString("strength9");
//                        strength10 = json.getString("strength10");
//                    }
//                    s=json.getString("weaknesses");
//                    if(s.equals("found")) {
//                        found_weaknesses=1;
//                        weak1 = json.getString("weak1");
//                        weak2 = json.getString("weak2");
//                        weak3 = json.getString("weak3");
//                        weak4 = json.getString("weak4");
//                        weak5 = json.getString("weak5");
//                        weak6 = json.getString("weak6");
//                        weak7 = json.getString("weak7");
//                        weak8 = json.getString("weak8");
//                        weak9 = json.getString("weak9");
//                        weak10 = json.getString("weak10");
//                    }
//                    s=json.getString("locationpreferences");
//                    if(s.equals("found")) {
//                        found_locationpreferences=1;
//                        location1 = json.getString("location1");
//                        location2 = json.getString("location2");
//                        location3 = json.getString("location3");
//                        location4 = json.getString("location4");
//                        location5 = json.getString("location5");
//                    }
//                    s=json.getString("contact_details");
//                    if(s.equals("found")) {
//                        found_contact_details=1;
//                        email2 = json.getString("alternateemail");
//                        addressline1 = json.getString("addrline1");
//                        addressline2 = json.getString("addrline2");
//                        addressline3 = json.getString("addrline3");
//                        telephone = json.getString("telephone");
//                        mobile2 = json.getString("alternatemobile");
//                    }
//                    s=json.getString("personal");
//                    if(s.equals("found")) {
//                        found_personal= 1;
//                        nameasten = json.getString("nameasten");
//                        mothername = json.getString("mothername");
//                        dob = json.getString("dob");
//                        gender = json.getString("gender");
//                        mothertongue = json.getString("mothertongue");
//                        hobbies = json.getString("hobbies");
//                        bloodgroup = json.getString("bloodgroup");
//                        category = json.getString("category");
//                        religion = json.getString("religion");
//                        caste = json.getString("caste");
//                        prn = json.getString("prn");
//                        paddrline1 = json.getString("paddrline1");
//                        paddrline2 = json.getString("paddrline2");
//                        paddrline3 = json.getString("paddrline3");
//                        handicapped = json.getString("handicapped");
//                        sports = json.getString("sports");
//                        defenceex = json.getString("defenceex");
//                    }
//
//
//                    }
//
//
//            }catch (Exception e){e.printStackTrace();
//                Log.d("TAG", "doInBackground: "+e.getMessage());
//            }
//
//            return map;
//        }
//
//        // Sets the Bitmap returned by doInBackground
//        @Override
//        protected void onPostExecute(Bitmap result)
//        {
//
//           // Toast.makeText(getActivity(),domain1+team1+duration1,Toast.LENGTH_LONG).show();
//
//            myprofileimg.setImageBitmap(result);
//            swipe_refresh_layout.setVisibility(View.VISIBLE);
//            swipe_refresh_layout.setRefreshing(false);
//            updateProgress.setVisibility(View.GONE);
//            byte[] mnameDecryptedBytes=null,lnameDecryptedBytes=null,fnameDecryptedBytes=null,demo1DecryptedBytes=null;
//            byte[] countryDecryptedBytes=null,stateDecryptedBytes=null,cityDecryptedBytes=null;
//            byte[] marks10DecryptedBytes,outof10DecryptedBytes,percentage10DecryptedBytes,schoolname10DecryptedBytes,board10DecryptedBytes,yearofpassing10DecryptedBytes,marks12DecryptedBytes,outof12DecryptedBytes,percentage12DecryptedBytes,schoolname12DecryptedBytes,board12DecryptedBytes,stream12DecryptedBytes,yearofpassing12DecryptedBytes,markssem1diplomaDecryptedBytes,outofsem1diplomaDecryptedBytes,percentagesem1diplomaDecryptedBytes,markssem2diplomaDecryptedBytes,outofsem2diplomaDecryptedBytes,percentagesem2diplomaDecryptedBytes,markssem3diplomaDecryptedBytes,outofsem3diplomaDecryptedBytes,percentagesem3diplomaDecryptedBytes,markssem4diplomaDecryptedBytes,outofsem4diplomaDecryptedBytes,percentagesem4diplomaDecryptedBytes,markssem5diplomaDecryptedBytes,outofsem5diplomaDecryptedBytes,percentagesem5diplomaDecryptedBytes,markssem6diplomaDecryptedBytes,outofsem6diplomaDecryptedBytes,percentagesem6diplomaDecryptedBytes,aggregatediplomaDecryptedBytes,coursediplomaDecryptedBytes,streamdiplomaDecryptedBytes,universitydiplomaDecryptedBytes,collegenamediplomaDecryptedBytes,yearofpassingdiplomaDecryptedBytes;
//            byte[] markssem1ugDecryptedBytes,outofsem1ugDecryptedBytes,percentagesem1ugDecryptedBytes,markssem2ugDecryptedBytes,outofsem2ugDecryptedBytes,percentagesem2ugDecryptedBytes,markssem3ugDecryptedBytes,outofsem3ugDecryptedBytes,percentagesem3ugDecryptedBytes,markssem4ugDecryptedBytes,outofsem4ugDecryptedBytes,percentagesem4ugDecryptedBytes,markssem5ugDecryptedBytes,outofsem5ugDecryptedBytes,percentagesem5ugDecryptedBytes,markssem6ugDecryptedBytes,outofsem6ugDecryptedBytes,percentagesem6ugDecryptedBytes,markssem7ugDecryptedBytes,outofsem7ugDecryptedBytes,percentagesem7ugDecryptedBytes,markssem8ugDecryptedBytes,outofsem8ugDecryptedBytes,percentagesem8ugDecryptedBytes,aggregateugDecryptedBytes,courseugDecryptedBytes,streamugDecryptedBytes,universityugDecryptedBytes,collegenameugDecryptedBytes,yearofpassingugDecryptedBytes;
//            byte[] markssem1pgsemDecryptedBytes,outofsem1pgsemDecryptedBytes,percentagesem1pgsemDecryptedBytes,markssem2pgsemDecryptedBytes,outofsem2pgsemDecryptedBytes,percentagesem2pgsemDecryptedBytes,markssem3pgsemDecryptedBytes,outofsem3pgsemDecryptedBytes,percentagesem3pgsemDecryptedBytes,markssem4pgsemDecryptedBytes,outofsem4pgsemDecryptedBytes,percentagesem4pgsemDecryptedBytes,markssem5pgsemDecryptedBytes,outofsem5pgsemDecryptedBytes,percentagesem5pgsemDecryptedBytes,markssem6pgsemDecryptedBytes,outofsem6pgsemDecryptedBytes,percentagesem6pgsemDecryptedBytes,aggregatepgsemDecryptedBytes,coursepgsemDecryptedBytes,streampgsemDecryptedBytes,universitypgsemDecryptedBytes,collegenamepgsemDecryptedBytes,yearofpassingpgsemDecryptedBytes;
//            byte[] marksyear1pgyearDecryptedBytes,outofyear1pgyearDecryptedBytes,percentageyear1pgyearDecryptedBytes,marksyear2pgyearDecryptedBytes,outofyear2pgyearDecryptedBytes,percentageyear2pgyearDecryptedBytes,marksyear3pgyearDecryptedBytes,outofyear3pgyearDecryptedBytes,percentageyear3pgyearDecryptedBytes,marksyear4pgyearDecryptedBytes,outofyear4pgyearDecryptedBytes,percentageyear4pgyearDecryptedBytes,marksyear5pgyearDecryptedBytes,outofyear5pgyearDecryptedBytes,percentageyear5pgyearDecryptedBytes,marksyear6pgyearDecryptedBytes,outofyear6pgyearDecryptedBytes,percentageyear6pgyearDecryptedBytes,aggregatepgyearDecryptedBytes,coursepgyearDecryptedBytes,streampgyearDecryptedBytes,universitypgyearDecryptedBytes,collegenamepgyearDecryptedBytes,yearofpassingpgyearDecryptedBytes;
//            byte[] nameastenDecryptedBytes,mothernameDecryptedBytes,dobDecryptedBytes,genderDecryptedBytes,mothertongueDecryptedBytes,hobbiesDecryptedBytes,bloodgroupDecryptedBytes,categoryDecryptedBytes,religionDecryptedBytes,casteDecryptedBytes,prnDecryptedBytes,paddrline1DecryptedBytes,paddrline2DecryptedBytes,paddrline3DecryptedBytes,handicappedDecryptedBytes,sportsDecryptedBytes,defenceexDecryptedBytes;
//            try {
//                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
//                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
//                String sPadding = "ISO10126Padding";
//
//                if(found_box1==1) {
//                    if (!phone.equals("null")) {
//                        byte[] demo1EncryptedBytes = SimpleBase64Encoder.decode(phone);
//                        demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
//                        s.setPhone(new String(demo1DecryptedBytes));
//                        phone = "+" + GetCountryZipCode() + " " + new String(demo1DecryptedBytes);
//                        contactmobile.setText(phone);
//                    }
//                    if (!fname.equals("null")) {
//                        byte[] fnameEncryptedBytes = SimpleBase64Encoder.decode(fname);
//                        fnameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, fnameEncryptedBytes);
//                        fname = new String(fnameDecryptedBytes);
//                        s.setFname(fname);
//                    }
//                    if (!mname.equals("null")) {
//                        byte[] mnameEncryptedBytes = SimpleBase64Encoder.decode(mname);
//                        mnameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, mnameEncryptedBytes);
//                        mname = new String(mnameDecryptedBytes);
//                        s.setMname(mname);
//                    }
//                    if (!lname.equals("null")) {
//                        byte[] lnameEncryptedBytes = SimpleBase64Encoder.decode(lname);
//                        lnameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, lnameEncryptedBytes);
//                        lname = new String(lnameDecryptedBytes);
//                        s.setLname(lname);
//                    }
//                    if (!country.equals("null")) {
//                        byte[] countryEncryptedBytes = SimpleBase64Encoder.decode(country);
//                        countryDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, countryEncryptedBytes);
//                        country = new String(countryDecryptedBytes);
//                        s.setCountry(country);
//                    }
//                    if (!state.equals("null")) {
//                        byte[] stateEncryptedBytes = SimpleBase64Encoder.decode(state);
//                        stateDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, stateEncryptedBytes);
//                        state = new String(stateDecryptedBytes);
//                        s.setState(state);
//                    }
//                    if (!city.equals("null")) {
//                        byte[] cityEncryptedBytes = SimpleBase64Encoder.decode(city);
//                        cityDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, cityEncryptedBytes);
//                        city = new String(cityDecryptedBytes);
//                        s.setCity(city);
//                    }
//                    Log.d("TAG", "onPostExecute:  intro done");
//                }
//                if(found_tenth==1) {
//                    if (!marks10.equals("null")) {
//                        byte[] marks10EncryptedBytes = SimpleBase64Encoder.decode(marks10);
//                        marks10DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, marks10EncryptedBytes);
//                        marks10 = new String(marks10DecryptedBytes);
//                        s.setMarks10(marks10);
//                    }
//                    if (!outof10.equals("null")) {
//                        byte[] outof10EncryptedBytes = SimpleBase64Encoder.decode(outof10);
//                        outof10DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outof10EncryptedBytes);
//                        outof10 = new String(outof10DecryptedBytes);
//                        s.setOutof10(outof10);
//                    }
//                    if (!percentage10.equals("null")) {
//                        byte[] percentage10EncryptedBytes = SimpleBase64Encoder.decode(percentage10);
//                        percentage10DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentage10EncryptedBytes);
//                        percentage10 = new String(percentage10DecryptedBytes);
//                        s.setPercentage10(percentage10);
//                    }
//                    if (!schoolname10.equals("null")) {
//                        byte[] schoolname10EncryptedBytes = SimpleBase64Encoder.decode(schoolname10);
//                        schoolname10DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, schoolname10EncryptedBytes);
//                        schoolname10 = new String(schoolname10DecryptedBytes);
//                        s.setSchoolname10(schoolname10);
//                    }
//                    if (!board10.equals("null")) {
//                        byte[] board10EncryptedBytes = SimpleBase64Encoder.decode(board10);
//                        board10DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, board10EncryptedBytes);
//                        board10 = new String(board10DecryptedBytes);
//                        s.setBoard10(board10);
//                    }
//                    if (!yearofpassing10.equals("null")) {
//                        byte[] yearofpassing10EncryptedBytes = SimpleBase64Encoder.decode(yearofpassing10);
//                        yearofpassing10DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, yearofpassing10EncryptedBytes);
//                        yearofpassing10 = new String(yearofpassing10DecryptedBytes);
//                        s.setYearofpassing10(yearofpassing10);
//                    }
//                    Log.d("TAG", "onPostExecute:  tenth  done");
//                }
//                if(found_twelth==1) {
//
////                    Log.d("TAG", "twelth enc data="+"\n"+marks12+"\n"+outof12+"\n"+percentage12+"\n"+schoolname12+"\n"+board12+"\n"+stream12+"\n"+yearofpassing12);
//                    if (!marks12.equals("null")) {
//                        byte[] marks12EncryptedBytes = SimpleBase64Encoder.decode(marks12);
//                        marks12DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, marks12EncryptedBytes);
//                        marks12 = new String(marks12DecryptedBytes);
//                        s.setMarks12(marks12);
//                    }
//                    if (!outof12.equals("null")) {
//                        byte[] outof12EncryptedBytes = SimpleBase64Encoder.decode(outof12);
//                        outof12DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outof12EncryptedBytes);
//                        outof12 = new String(outof12DecryptedBytes);
//                        s.setOutof12(outof12);
//                    }
//                    if (!percentage12.equals("null")) {
//                        byte[] percentage12EncryptedBytes = SimpleBase64Encoder.decode(percentage12);
//                        percentage12DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentage12EncryptedBytes);
//                        percentage12 = new String(percentage12DecryptedBytes);
//                        s.setPercentage12(percentage12);
//                    }
//                    if (!schoolname12.equals("null")) {
//                        byte[] schoolname12EncryptedBytes = SimpleBase64Encoder.decode(schoolname12);
//                        schoolname12DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, schoolname12EncryptedBytes);
//                        schoolname12 = new String(schoolname12DecryptedBytes);
//                        s.setSchoolname12(schoolname12);
//                    }
//                    if (!board12.equals("null")) {
//                        byte[] board12EncryptedBytes = SimpleBase64Encoder.decode(board12);
//                        board12DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, board12EncryptedBytes);
//                        board12 = new String(board12DecryptedBytes);
//                        s.setBoard12(board12);
//                    }
//                    if (!stream12.equals("null")) {
//                        byte[] stream12EncryptedBytes = SimpleBase64Encoder.decode(stream12);
//                        stream12DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, stream12EncryptedBytes);
//                        stream12 = new String(stream12DecryptedBytes);
//                        s.setStream12(stream12);
//                    }
//                    if (!yearofpassing12.equals("null")) {
//                        byte[] yearofpassing12EncryptedBytes = SimpleBase64Encoder.decode(yearofpassing12);
//                        yearofpassing12DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, yearofpassing12EncryptedBytes);
//                        yearofpassing12 = new String(yearofpassing12DecryptedBytes);
//                        s.setYearofpassing12(yearofpassing12);
//                    }
//                    Log.d("TAG", "onPostExecute:  twelth done");
//                }
//                if(found_diploma==1) {
//
////                    Log.d("TAG", "onPostExecute: "+markssem1diploma+"\n"+outofsem1diploma+"\n"+percentagesem1diploma+"\n"+markssem2diploma+"\n"+outofsem2diploma+"\n"+percentagesem2diploma+"\n"+markssem3diploma+"\n"+outofsem3diploma+"\n"+percentagesem3diploma+"\n"+markssem4diploma+"\n"+outofsem4diploma+"\n"+percentagesem4diploma+"\n"+markssem5diploma+"\n"+outofsem5diploma+"\n"+percentagesem5diploma+"\n"+markssem6diploma+"\n"+outofsem6diploma+"\n"+percentagesem6diploma+""+aggregatediploma+"\n"+coursediploma+"\n"+streamdiploma+"\n"+universitydiploma+"\n"+collegenamediploma+"\n"+yearofpassingdiploma);
//
//                    if (!markssem1diploma.equals("null")) {
//                        byte[] markssem1diplomaEncryptedBytes = SimpleBase64Encoder.decode(markssem1diploma);
//                        markssem1diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem1diplomaEncryptedBytes);
//                        markssem1diploma = new String(markssem1diplomaDecryptedBytes);
//                        s.setMarkssem1diploma(markssem1diploma);
//                    }
//                    if (!outofsem1diploma.equals("null")) {
//                        byte[] outofsem1diplomaEncryptedBytes = SimpleBase64Encoder.decode(outofsem1diploma);
//                        outofsem1diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem1diplomaEncryptedBytes);
//                        outofsem1diploma = new String(outofsem1diplomaDecryptedBytes);
//                        s.setOutofsem1diploma(outofsem1diploma);
//                    }
//                    if (!percentagesem1diploma.equals("null")) {
//                        byte[] percentagesem1diplomaEncryptedBytes = SimpleBase64Encoder.decode(percentagesem1diploma);
//                        percentagesem1diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem1diplomaEncryptedBytes);
//                        percentagesem1diploma = new String(percentagesem1diplomaDecryptedBytes);
//                        s.setPercentagesem1diploma(percentagesem1diploma);
//                    }
//                    if (!markssem2diploma.equals("null")) {
//                        byte[] markssem2diplomaEncryptedBytes = SimpleBase64Encoder.decode(markssem2diploma);
//                        markssem2diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem2diplomaEncryptedBytes);
//                        markssem2diploma = new String(markssem2diplomaDecryptedBytes);
//                        s.setMarkssem2diploma(markssem2diploma);
//                    }
//                    if (!outofsem2diploma.equals("null")) {
//                        byte[] outofsem2diplomaEncryptedBytes = SimpleBase64Encoder.decode(outofsem2diploma);
//                        outofsem2diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem2diplomaEncryptedBytes);
//                        outofsem2diploma = new String(outofsem2diplomaDecryptedBytes);
//                        s.setOutofsem2diploma(outofsem2diploma);
//                    }
//                    if (!percentagesem2diploma.equals("null")) {
//                        byte[] percentagesem2diplomaEncryptedBytes = SimpleBase64Encoder.decode(percentagesem2diploma);
//                        percentagesem2diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem2diplomaEncryptedBytes);
//                        percentagesem2diploma = new String(percentagesem2diplomaDecryptedBytes);
//                        s.setPercentagesem2diploma(percentagesem2diploma);
//                    }
//                    if (!markssem3diploma.equals("null")) {
//                        byte[] markssem3diplomaEncryptedBytes = SimpleBase64Encoder.decode(markssem3diploma);
//                        markssem3diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem3diplomaEncryptedBytes);
//                        markssem3diploma = new String(markssem3diplomaDecryptedBytes);
//                        s.setMarkssem3diploma(markssem3diploma);
//                    }
//                    if (!outofsem3diploma.equals("null")) {
//                        byte[] outofsem3diplomaEncryptedBytes = SimpleBase64Encoder.decode(outofsem3diploma);
//                        outofsem3diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem3diplomaEncryptedBytes);
//                        outofsem3diploma = new String(outofsem3diplomaDecryptedBytes);
//                        s.setOutofsem3diploma(outofsem3diploma);
//                    }
//                    if (!percentagesem3diploma.equals("null")) {
//                        byte[] percentagesem3diplomaEncryptedBytes = SimpleBase64Encoder.decode(percentagesem3diploma);
//                        percentagesem3diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem3diplomaEncryptedBytes);
//                        percentagesem3diploma = new String(percentagesem3diplomaDecryptedBytes);
//                        s.setPercentagesem3diploma(percentagesem3diploma);
//                    }
//                    if (!markssem4diploma.equals("null")) {
//                        byte[] markssem4diplomaEncryptedBytes = SimpleBase64Encoder.decode(markssem4diploma);
//                        markssem4diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem4diplomaEncryptedBytes);
//                        markssem4diploma = new String(markssem4diplomaDecryptedBytes);
//                        s.setMarkssem4diploma(markssem4diploma);
//                    }
//                    if (!outofsem4diploma.equals("null")) {
//                        byte[] outofsem4diplomaEncryptedBytes = SimpleBase64Encoder.decode(outofsem4diploma);
//                        outofsem4diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem4diplomaEncryptedBytes);
//                        outofsem4diploma = new String(outofsem4diplomaDecryptedBytes);
//                        s.setOutofsem4diploma(outofsem4diploma);
//                    }
//                    if (!percentagesem4diploma.equals("null")) {
//                        byte[] percentagesem4diplomaEncryptedBytes = SimpleBase64Encoder.decode(percentagesem4diploma);
//                        percentagesem4diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem4diplomaEncryptedBytes);
//                        percentagesem4diploma = new String(percentagesem4diplomaDecryptedBytes);
//                        s.setPercentagesem4diploma(percentagesem4diploma);
//                    }
//                    if (!markssem5diploma.equals("null")) {
//                        byte[] markssem5diplomaEncryptedBytes = SimpleBase64Encoder.decode(markssem5diploma);
//                        markssem5diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem5diplomaEncryptedBytes);
//                        markssem5diploma = new String(markssem5diplomaDecryptedBytes);
//                        s.setMarkssem5diploma(markssem5diploma);
//                    }
//                    if (!outofsem5diploma.equals("null")) {
//                        byte[] outofsem5diplomaEncryptedBytes = SimpleBase64Encoder.decode(outofsem5diploma);
//                        outofsem5diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem5diplomaEncryptedBytes);
//                        outofsem5diploma = new String(outofsem5diplomaDecryptedBytes);
//                        s.setOutofsem5diploma(outofsem5diploma);
//                    }
//                    if (!percentagesem5diploma.equals("null")) {
//                        byte[] percentagesem5diplomaEncryptedBytes = SimpleBase64Encoder.decode(percentagesem5diploma);
//                        percentagesem5diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem5diplomaEncryptedBytes);
//                        percentagesem5diploma = new String(percentagesem5diplomaDecryptedBytes);
//                        s.setPercentagesem5diploma(percentagesem5diploma);
//                    }
//                    if (!markssem6diploma.equals("null")) {
//                        byte[] markssem6diplomaEncryptedBytes = SimpleBase64Encoder.decode(markssem6diploma);
//                        markssem6diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem6diplomaEncryptedBytes);
//                        markssem6diploma = new String(markssem6diplomaDecryptedBytes);
//                        s.setMarkssem6diploma(markssem6diploma);
//                    }
//                    if (!outofsem6diploma.equals("null")) {
//                        byte[] outofsem6diplomaEncryptedBytes = SimpleBase64Encoder.decode(outofsem6diploma);
//                        outofsem6diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem6diplomaEncryptedBytes);
//                        outofsem6diploma = new String(outofsem6diplomaDecryptedBytes);
//                        s.setOutofsem6diploma(outofsem6diploma);
//                    }
//                    if (!percentagesem6diploma.equals("null")) {
//                        byte[] percentagesem6diplomaEncryptedBytes = SimpleBase64Encoder.decode(percentagesem6diploma);
//                        percentagesem6diplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem6diplomaEncryptedBytes);
//                        percentagesem6diploma = new String(percentagesem6diplomaDecryptedBytes);
//                        s.setPercentagesem6diploma(percentagesem6diploma);
//                    }
//                    if (!aggregatediploma.equals("null")) {
//                        byte[] aggregatediplomaEncryptedBytes = SimpleBase64Encoder.decode(aggregatediploma);
//                        aggregatediplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, aggregatediplomaEncryptedBytes);
//                        aggregatediploma = new String(aggregatediplomaDecryptedBytes);
//                        s.setAggregatediploma(aggregatediploma);
//                    }
//                    if (!coursediploma.equals("null")) {
//                        byte[] coursediplomaEncryptedBytes = SimpleBase64Encoder.decode(coursediploma);
//                        coursediplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, coursediplomaEncryptedBytes);
//                        coursediploma = new String(coursediplomaDecryptedBytes);
//                        s.setCoursediploma(coursediploma);
//                    }
//                    if (!streamdiploma.equals("null")) {
//                        byte[] streamdiplomaEncryptedBytes = SimpleBase64Encoder.decode(streamdiploma);
//                        streamdiplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, streamdiplomaEncryptedBytes);
//                        streamdiploma = new String(streamdiplomaDecryptedBytes);
//                        s.setStreamdiploma(streamdiploma);
//                    }
//                    if (!universitydiploma.equals("null")) {
//                        byte[] universitydiplomaEncryptedBytes = SimpleBase64Encoder.decode(universitydiploma);
//                        universitydiplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, universitydiplomaEncryptedBytes);
//                        universitydiploma = new String(universitydiplomaDecryptedBytes);
//                        s.setUniversitydiploma(universitydiploma);
//                    }
//                    if (!collegenamediploma.equals("null")) {
//                        byte[] collegenamediplomaEncryptedBytes = SimpleBase64Encoder.decode(collegenamediploma);
//                        collegenamediplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, collegenamediplomaEncryptedBytes);
//                        collegenamediploma = new String(collegenamediplomaDecryptedBytes);
//                        s.setCollegenamediploma(collegenamediploma);
//                    }
//                    if (!yearofpassingdiploma.equals("null")) {
//                        byte[] yearofpassingdiplomaEncryptedBytes = SimpleBase64Encoder.decode(yearofpassingdiploma);
//                        yearofpassingdiplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, yearofpassingdiplomaEncryptedBytes);
//                        yearofpassingdiploma = new String(yearofpassingdiplomaDecryptedBytes);
//                        s.setYearofpassingdiploma(yearofpassingdiploma);
//
//                    }
//                    Log.d("TAG", "onPostExecute:  diploma done");
//                }
//                if(found_ug==1) {
//                    if (!markssem1ug.equals("null")) {
//                        byte[] markssem1ugEncryptedBytes = SimpleBase64Encoder.decode(markssem1ug);
//                        markssem1ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem1ugEncryptedBytes);
//                        markssem1ug = new String(markssem1ugDecryptedBytes);
//                        s.setMarkssem1ug(markssem1ug);
//                    }
//                    if (!outofsem1ug.equals("null")) {
//                        byte[] outofsem1ugEncryptedBytes = SimpleBase64Encoder.decode(outofsem1ug);
//                        outofsem1ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem1ugEncryptedBytes);
//                        outofsem1ug = new String(outofsem1ugDecryptedBytes);
//                        s.setOutofsem1ug(outofsem1ug);
//                    }
//                    if (!percentagesem1ug.equals("null")) {
//                        byte[] percentagesem1ugEncryptedBytes = SimpleBase64Encoder.decode(percentagesem1ug);
//                        percentagesem1ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem1ugEncryptedBytes);
//                        percentagesem1ug = new String(percentagesem1ugDecryptedBytes);
//                        s.setPercentagesem1ug(percentagesem1ug);
//                    }
//                    if (!markssem2ug.equals("null")) {
//                        byte[] markssem2ugEncryptedBytes = SimpleBase64Encoder.decode(markssem2ug);
//                        markssem2ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem2ugEncryptedBytes);
//                        markssem2ug = new String(markssem2ugDecryptedBytes);
//                        s.setMarkssem2ug(markssem2ug);
//                    }
//                    if (!outofsem2ug.equals("null")) {
//                        byte[] outofsem2ugEncryptedBytes = SimpleBase64Encoder.decode(outofsem2ug);
//                        outofsem2ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem2ugEncryptedBytes);
//                        outofsem2ug = new String(outofsem2ugDecryptedBytes);
//                        s.setOutofsem2ug(outofsem2ug);
//                    }
//                    if (!percentagesem2ug.equals("null")) {
//                        byte[] percentagesem2ugEncryptedBytes = SimpleBase64Encoder.decode(percentagesem2ug);
//                        percentagesem2ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem2ugEncryptedBytes);
//                        percentagesem2ug = new String(percentagesem2ugDecryptedBytes);
//                        s.setPercentagesem2ug(percentagesem2ug);
//                    }
//                    if (!markssem3ug.equals("null")) {
//                        byte[] markssem3ugEncryptedBytes = SimpleBase64Encoder.decode(markssem3ug);
//                        markssem3ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem3ugEncryptedBytes);
//                        markssem3ug = new String(markssem3ugDecryptedBytes);
//                        s.setMarkssem3ug(markssem3ug);
//                    }
//                    if (!outofsem3ug.equals("null")) {
//                        byte[] outofsem3ugEncryptedBytes = SimpleBase64Encoder.decode(outofsem3ug);
//                        outofsem3ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem3ugEncryptedBytes);
//                        outofsem3ug = new String(outofsem3ugDecryptedBytes);
//                        s.setOutofsem3ug(outofsem3ug);
//                    }
//                    if (!percentagesem3ug.equals("null")) {
//                        byte[] percentagesem3ugEncryptedBytes = SimpleBase64Encoder.decode(percentagesem3ug);
//                        percentagesem3ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem3ugEncryptedBytes);
//                        percentagesem3ug = new String(percentagesem3ugDecryptedBytes);
//                        s.setPercentagesem3ug(percentagesem3ug);
//                    }
//                    if (!markssem4ug.equals("null")) {
//                        byte[] markssem4ugEncryptedBytes = SimpleBase64Encoder.decode(markssem4ug);
//                        markssem4ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem4ugEncryptedBytes);
//                        markssem4ug = new String(markssem4ugDecryptedBytes);
//                        s.setMarkssem4ug(markssem4ug);
//                    }
//                    if (!outofsem4ug.equals("null")) {
//                        byte[] outofsem4ugEncryptedBytes = SimpleBase64Encoder.decode(outofsem4ug);
//                        outofsem4ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem4ugEncryptedBytes);
//                        outofsem4ug = new String(outofsem4ugDecryptedBytes);
//                        s.setOutofsem4ug(outofsem4ug);
//                    }
//                    if (!percentagesem4ug.equals("null")) {
//                        byte[] percentagesem4ugEncryptedBytes = SimpleBase64Encoder.decode(percentagesem4ug);
//                        percentagesem4ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem4ugEncryptedBytes);
//                        percentagesem4ug = new String(percentagesem4ugDecryptedBytes);
//                        s.setPercentagesem4ug(percentagesem4ug);
//                    }
//                    if (!markssem5ug.equals("null")) {
//                        byte[] markssem5ugEncryptedBytes = SimpleBase64Encoder.decode(markssem5ug);
//                        markssem5ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem5ugEncryptedBytes);
//                        markssem5ug = new String(markssem5ugDecryptedBytes);
//                        s.setMarkssem5ug(markssem5ug);
//                    }
//                    if (!outofsem5ug.equals("null")) {
//                        byte[] outofsem5ugEncryptedBytes = SimpleBase64Encoder.decode(outofsem5ug);
//                        outofsem5ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem5ugEncryptedBytes);
//                        outofsem5ug = new String(outofsem5ugDecryptedBytes);
//                        s.setOutofsem5ug(outofsem5ug);
//                    }
//                    if (!percentagesem5ug.equals("null")) {
//                        byte[] percentagesem5ugEncryptedBytes = SimpleBase64Encoder.decode(percentagesem5ug);
//                        percentagesem5ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem5ugEncryptedBytes);
//                        percentagesem5ug = new String(percentagesem5ugDecryptedBytes);
//                        s.setPercentagesem5ug(percentagesem5ug);
//                    }
//                    if (!markssem6ug.equals("null")) {
//                        byte[] markssem6ugEncryptedBytes = SimpleBase64Encoder.decode(markssem6ug);
//                        markssem6ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem6ugEncryptedBytes);
//                        markssem6ug = new String(markssem6ugDecryptedBytes);
//                        s.setMarkssem6ug(markssem6ug);
//                    }
//                    if (!outofsem6ug.equals("null")) {
//                        byte[] outofsem6ugEncryptedBytes = SimpleBase64Encoder.decode(outofsem6ug);
//                        outofsem6ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem6ugEncryptedBytes);
//                        outofsem6ug = new String(outofsem6ugDecryptedBytes);
//                        s.setOutofsem6ug(outofsem6ug);
//                    }
//                    if (!percentagesem6ug.equals("null")) {
//                        byte[] percentagesem6ugEncryptedBytes = SimpleBase64Encoder.decode(percentagesem6ug);
//                        percentagesem6ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem6ugEncryptedBytes);
//                        percentagesem6ug = new String(percentagesem6ugDecryptedBytes);
//                        s.setPercentagesem6ug(percentagesem6ug);
//                    }
//                    if (!markssem7ug.equals("null")) {
//                        byte[] markssem7ugEncryptedBytes = SimpleBase64Encoder.decode(markssem7ug);
//                        markssem7ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem7ugEncryptedBytes);
//                        markssem7ug = new String(markssem7ugDecryptedBytes);
//                        s.setMarkssem7ug(markssem7ug);
//                    }
//                    if (!outofsem7ug.equals("null")) {
//                        byte[] outofsem7ugEncryptedBytes = SimpleBase64Encoder.decode(outofsem7ug);
//                        outofsem7ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem7ugEncryptedBytes);
//                        outofsem7ug = new String(outofsem7ugDecryptedBytes);
//                        s.setOutofsem7ug(outofsem7ug);
//                    }
//                    if (!percentagesem7ug.equals("null")) {
//                        byte[] percentagesem7ugEncryptedBytes = SimpleBase64Encoder.decode(percentagesem7ug);
//                        percentagesem7ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem7ugEncryptedBytes);
//                        percentagesem7ug = new String(percentagesem7ugDecryptedBytes);
//                        s.setPercentagesem7ug(percentagesem7ug);
//                    }
//                    if (!markssem8ug.equals("null")) {
//                        byte[] markssem8ugEncryptedBytes = SimpleBase64Encoder.decode(markssem8ug);
//                        markssem8ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem8ugEncryptedBytes);
//                        markssem8ug = new String(markssem8ugDecryptedBytes);
//                        s.setMarkssem8ug(markssem8ug);
//                    }
//                    if (!outofsem8ug.equals("null")) {
//                        byte[] outofsem8ugEncryptedBytes = SimpleBase64Encoder.decode(outofsem8ug);
//                        outofsem8ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem8ugEncryptedBytes);
//                        outofsem8ug = new String(outofsem8ugDecryptedBytes);
//                        s.setOutofsem8ug(outofsem8ug);
//                    }
//                    if (!percentagesem8ug.equals("null")) {
//                        byte[] percentagesem8ugEncryptedBytes = SimpleBase64Encoder.decode(percentagesem8ug);
//                        percentagesem8ugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem8ugEncryptedBytes);
//                        percentagesem8ug = new String(percentagesem8ugDecryptedBytes);
//                        s.setPercentagesem8ug(percentagesem8ug);
//                    }
//                    if (!aggregateug.equals("null")) {
//                        byte[] aggregateugEncryptedBytes = SimpleBase64Encoder.decode(aggregateug);
//                        aggregateugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, aggregateugEncryptedBytes);
//                        aggregateug = new String(aggregateugDecryptedBytes);
//                        s.setAggregateug(aggregateug);
//                    }
//                    if (!courseug.equals("null")) {
//                        byte[] courseugEncryptedBytes = SimpleBase64Encoder.decode(courseug);
//                        courseugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, courseugEncryptedBytes);
//                        courseug = new String(courseugDecryptedBytes);
//                        s.setCourseug(courseug);
//                    }
//                    if (!streamug.equals("null")) {
//                        byte[] streamugEncryptedBytes = SimpleBase64Encoder.decode(streamug);
//                        streamugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, streamugEncryptedBytes);
//                        streamug = new String(streamugDecryptedBytes);
//                        s.setStreamug(streamug);
//                    }
//                    if (!universityug.equals("null")) {
//                        byte[] universityugEncryptedBytes = SimpleBase64Encoder.decode(universityug);
//                        universityugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, universityugEncryptedBytes);
//                        universityug = new String(universityugDecryptedBytes);
//                        s.setUniversityug(universityug);
//                    }
//                    if (!collegenameug.equals("null")) {
//                        byte[] collegenameugEncryptedBytes = SimpleBase64Encoder.decode(collegenameug);
//                        collegenameugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, collegenameugEncryptedBytes);
//                        collegenameug = new String(collegenameugDecryptedBytes);
//                        s.setCollegenameug(collegenameug);
//                    }
//                    if (!yearofpassingug.equals("null")) {
//                        byte[] yearofpassingugEncryptedBytes = SimpleBase64Encoder.decode(yearofpassingug);
//                        yearofpassingugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, yearofpassingugEncryptedBytes);
//                        yearofpassingug = new String(yearofpassingugDecryptedBytes);
//                        s.setYearofpassingug(yearofpassingug);
//                    }
//                    Log.d("TAG", "onPostExecute: ug done");
//                }
//                if(found_pgsem==1) {
//                    if (!markssem1pgsem.equals("null")) {
//                        byte[] markssem1pgsemEncryptedBytes = SimpleBase64Encoder.decode(markssem1pgsem);
//                        markssem1pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem1pgsemEncryptedBytes);
//                        markssem1pgsem = new String(markssem1pgsemDecryptedBytes);

//                    }
//                    if (!outofsem1pgsem.equals("null")) {
//                        byte[] outofsem1pgsemEncryptedBytes = SimpleBase64Encoder.decode(outofsem1pgsem);
//                        outofsem1pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem1pgsemEncryptedBytes);
//                        outofsem1pgsem = new String(outofsem1pgsemDecryptedBytes);

//                    }
//                    if (!percentagesem1pgsem.equals("null")) {
//                        byte[] percentagesem1pgsemEncryptedBytes = SimpleBase64Encoder.decode(percentagesem1pgsem);
//                        percentagesem1pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem1pgsemEncryptedBytes);
//                        percentagesem1pgsem = new String(percentagesem1pgsemDecryptedBytes);

//                    }
//                    if (!markssem2pgsem.equals("null")) {
//                        byte[] markssem2pgsemEncryptedBytes = SimpleBase64Encoder.decode(markssem2pgsem);
//                        markssem2pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem2pgsemEncryptedBytes);
//                        markssem2pgsem = new String(markssem2pgsemDecryptedBytes);
//                        s.setMarkssem2pgsem(markssem2pgsem);
//                    }
//                    if (!outofsem2pgsem.equals("null")) {
//                        byte[] outofsem2pgsemEncryptedBytes = SimpleBase64Encoder.decode(outofsem2pgsem);
//                        outofsem2pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem2pgsemEncryptedBytes);
//                        outofsem2pgsem = new String(outofsem2pgsemDecryptedBytes);
//                        s.setOutofsem2pgsem(outofsem2pgsem);
//                    }
//                    if (!percentagesem2pgsem.equals("null")) {
//                        byte[] percentagesem2pgsemEncryptedBytes = SimpleBase64Encoder.decode(percentagesem2pgsem);
//                        percentagesem2pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem2pgsemEncryptedBytes);
//                        percentagesem2pgsem = new String(percentagesem2pgsemDecryptedBytes);
//                        s.setPercentagesem2pgsem(percentagesem2pgsem);
//                    }
//                    if (!markssem3pgsem.equals("null")) {
//                        byte[] markssem3pgsemEncryptedBytes = SimpleBase64Encoder.decode(markssem3pgsem);
//                        markssem3pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem3pgsemEncryptedBytes);
//                        markssem3pgsem = new String(markssem3pgsemDecryptedBytes);
//                        s.setMarkssem3pgsem(markssem3pgsem);
//                    }
//                    if (!outofsem3pgsem.equals("null")) {
//                        byte[] outofsem3pgsemEncryptedBytes = SimpleBase64Encoder.decode(outofsem3pgsem);
//                        outofsem3pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem3pgsemEncryptedBytes);
//                        outofsem3pgsem = new String(outofsem3pgsemDecryptedBytes);
//                        s.setOutofsem3pgsem(outofsem3pgsem);
//                    }
//                    if (!percentagesem3pgsem.equals("null")) {
//                        byte[] percentagesem3pgsemEncryptedBytes = SimpleBase64Encoder.decode(percentagesem3pgsem);
//                        percentagesem3pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem3pgsemEncryptedBytes);
//                        percentagesem3pgsem = new String(percentagesem3pgsemDecryptedBytes);
//                        s.setPercentagesem3pgsem(percentagesem3pgsem);
//                    }
//                    if (!markssem4pgsem.equals("null")) {
//                        byte[] markssem4pgsemEncryptedBytes = SimpleBase64Encoder.decode(markssem4pgsem);
//                        markssem4pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem4pgsemEncryptedBytes);
//                        markssem4pgsem = new String(markssem4pgsemDecryptedBytes);
//                        s.setMarkssem4pgsem(markssem4pgsem);
//                    }
//                    if (!outofsem4pgsem.equals("null")) {
//                        byte[] outofsem4pgsemEncryptedBytes = SimpleBase64Encoder.decode(outofsem4pgsem);
//                        outofsem4pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem4pgsemEncryptedBytes);
//                        outofsem4pgsem = new String(outofsem4pgsemDecryptedBytes);
//                        s.setOutofsem4pgsem(outofsem4pgsem);
//                    }
//                    if (!percentagesem4pgsem.equals("null")) {
//                        byte[] percentagesem4pgsemEncryptedBytes = SimpleBase64Encoder.decode(percentagesem4pgsem);
//                        percentagesem4pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem4pgsemEncryptedBytes);
//                        percentagesem4pgsem = new String(percentagesem4pgsemDecryptedBytes);
//                        s.setPercentagesem4pgsem(percentagesem4pgsem);
//                    }
//                    if (!markssem5pgsem.equals("null")) {
//                        byte[] markssem5pgsemEncryptedBytes = SimpleBase64Encoder.decode(markssem5pgsem);
//                        markssem5pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem5pgsemEncryptedBytes);
//                        markssem5pgsem = new String(markssem5pgsemDecryptedBytes);
//                        s.setMarkssem5pgsem(markssem5pgsem);
//                    }
//                    if (!outofsem5pgsem.equals("null")) {
//                        byte[] outofsem5pgsemEncryptedBytes = SimpleBase64Encoder.decode(outofsem5pgsem);
//                        outofsem5pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem5pgsemEncryptedBytes);
//                        outofsem5pgsem = new String(outofsem5pgsemDecryptedBytes);
//                        s.setOutofsem5pgsem(outofsem5pgsem);
//                    }
//                    if (!percentagesem5pgsem.equals("null")) {
//                        byte[] percentagesem5pgsemEncryptedBytes = SimpleBase64Encoder.decode(percentagesem5pgsem);
//                        percentagesem5pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem5pgsemEncryptedBytes);
//                        percentagesem5pgsem = new String(percentagesem5pgsemDecryptedBytes);
//                        s.setPercentagesem5pgsem(percentagesem5pgsem);
//                    }
//                    if (!markssem6pgsem.equals("null")) {
//                        byte[] markssem6pgsemEncryptedBytes = SimpleBase64Encoder.decode(markssem6pgsem);
//                        markssem6pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, markssem6pgsemEncryptedBytes);
//                        markssem6pgsem = new String(markssem6pgsemDecryptedBytes);
//                        s.setMarkssem6pgsem(markssem6pgsem);
//                    }
//                    if (!outofsem6pgsem.equals("null")) {
//                        byte[] outofsem6pgsemEncryptedBytes = SimpleBase64Encoder.decode(outofsem6pgsem);
//                        outofsem6pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem6pgsemEncryptedBytes);
//                        outofsem6pgsem = new String(outofsem6pgsemDecryptedBytes);
//                        s.setOutofsem6pgsem(outofsem6pgsem);
//                    }
//                    if (!percentagesem6pgsem.equals("null")) {
//                        byte[] percentagesem6pgsemEncryptedBytes = SimpleBase64Encoder.decode(percentagesem6pgsem);
//                        percentagesem6pgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentagesem6pgsemEncryptedBytes);
//                        percentagesem6pgsem = new String(percentagesem6pgsemDecryptedBytes);
//                        s.setPercentagesem6pgsem(percentagesem6pgsem);
//                    }
//                    if (!aggregatepgsem.equals("null")) {
//                        byte[] aggregatepgsemEncryptedBytes = SimpleBase64Encoder.decode(aggregatepgsem);
//                        aggregatepgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, aggregatepgsemEncryptedBytes);
//                        aggregatepgsem = new String(aggregatepgsemDecryptedBytes);

//                    }
//                    if (!coursepgsem.equals("null")) {
//                        byte[] coursepgsemEncryptedBytes = SimpleBase64Encoder.decode(coursepgsem);
//                        coursepgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, coursepgsemEncryptedBytes);
//                        coursepgsem = new String(coursepgsemDecryptedBytes);

//                    }
//                    if (!streampgsem.equals("null")) {
//                        byte[] streampgsemEncryptedBytes = SimpleBase64Encoder.decode(streampgsem);
//                        streampgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, streampgsemEncryptedBytes);
//                        streampgsem = new String(streampgsemDecryptedBytes);

//                    }
//                    if (!universitypgsem.equals("null")) {
//                        byte[] universitypgsemEncryptedBytes = SimpleBase64Encoder.decode(universitypgsem);
//                        universitypgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, universitypgsemEncryptedBytes);
//                        universitypgsem = new String(universitypgsemDecryptedBytes);

//                    }
//                    if (!collegenamepgsem.equals("null")) {
//                        byte[] collegenamepgsemEncryptedBytes = SimpleBase64Encoder.decode(collegenamepgsem);
//                        collegenamepgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, collegenamepgsemEncryptedBytes);
//                        collegenamepgsem = new String(collegenamepgsemDecryptedBytes);

//                    }
//                    if (!yearofpassingpgsem.equals("null")) {
//                        byte[] yearofpassingpgsemEncryptedBytes = SimpleBase64Encoder.decode(yearofpassingpgsem);
//                        yearofpassingpgsemDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, yearofpassingpgsemEncryptedBytes);
//                        yearofpassingpgsem = new String(yearofpassingpgsemDecryptedBytes);

//                    }
//                }
//                if(found_pgyear==1) {
//                    if (!marksyear1pgyear.equals("null")) {
//                        byte[] marksyear1pgyearEncryptedBytes = SimpleBase64Encoder.decode(marksyear1pgyear);
//                        marksyear1pgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, marksyear1pgyearEncryptedBytes);
//                        marksyear1pgyear = new String(marksyear1pgyearDecryptedBytes);

//                    }
//                    if (!outofyear1pgyear.equals("null")) {
//                        byte[] outofyear1pgyearEncryptedBytes = SimpleBase64Encoder.decode(outofyear1pgyear);
//                        outofyear1pgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofyear1pgyearEncryptedBytes);
//                        outofyear1pgyear = new String(outofyear1pgyearDecryptedBytes);

//                    }
//                    if (!percentageyear1pgyear.equals("null")) {
//                        byte[] percentageyear1pgyearEncryptedBytes = SimpleBase64Encoder.decode(percentageyear1pgyear);
//                        percentageyear1pgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentageyear1pgyearEncryptedBytes);
//                        percentageyear1pgyear = new String(percentageyear1pgyearDecryptedBytes);

//                    }
//                    if (!marksyear2pgyear.equals("null")) {
//                        byte[] marksyear2pgyearEncryptedBytes = SimpleBase64Encoder.decode(marksyear2pgyear);
//                        marksyear2pgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, marksyear2pgyearEncryptedBytes);
//                        marksyear2pgyear = new String(marksyear2pgyearDecryptedBytes);
//                        s.setMarksyear2pgyear(marksyear2pgyear);
//                    }
//                    if (!outofyear2pgyear.equals("null")) {
//                        byte[] outofyear2pgyearEncryptedBytes = SimpleBase64Encoder.decode(outofyear2pgyear);
//                        outofyear2pgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofyear2pgyearEncryptedBytes);
//                        outofyear2pgyear = new String(outofyear2pgyearDecryptedBytes);
//                        s.setOutofyear2pgyear(outofyear2pgyear);
//                    }
//                    if (!percentageyear2pgyear.equals("null")) {
//                        byte[] percentageyear2pgyearEncryptedBytes = SimpleBase64Encoder.decode(percentageyear2pgyear);
//                        percentageyear2pgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentageyear2pgyearEncryptedBytes);
//                        percentageyear2pgyear = new String(percentageyear2pgyearDecryptedBytes);
//                        s.setPercentageyear2pgyear(percentageyear2pgyear);
//                    }
//                    if (!marksyear3pgyear.equals("null")) {
//                        byte[] marksyear3pgyearEncryptedBytes = SimpleBase64Encoder.decode(marksyear3pgyear);
//                        marksyear3pgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, marksyear3pgyearEncryptedBytes);
//                        marksyear3pgyear = new String(marksyear3pgyearDecryptedBytes);
//                        s.setMarksyear3pgyear(marksyear3pgyear);
//                    }
//                    if (!outofyear3pgyear.equals("null")) {
//                        byte[] outofyear3pgyearEncryptedBytes = SimpleBase64Encoder.decode(outofyear3pgyear);
//                        outofyear3pgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, outofyear3pgyearEncryptedBytes);
//                        outofyear3pgyear = new String(outofyear3pgyearDecryptedBytes);
//                        s.setOutofyear3pgyear(outofyear3pgyear);
//                    }
//                    if (!percentageyear3pgyear.equals("null")) {
//                        byte[] percentageyear3pgyearEncryptedBytes = SimpleBase64Encoder.decode(percentageyear3pgyear);
//                        percentageyear3pgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, percentageyear3pgyearEncryptedBytes);
//                        percentageyear3pgyear = new String(percentageyear3pgyearDecryptedBytes);
//                        s.setPercentageyear3pgyear(percentageyear3pgyear);
//                    }
//                    if (!aggregatepgyear.equals("null")) {
//                        byte[] aggregatepgyearEncryptedBytes = SimpleBase64Encoder.decode(aggregatepgyear);
//                        aggregatepgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, aggregatepgyearEncryptedBytes);
//                        aggregatepgyear = new String(aggregatepgyearDecryptedBytes);

//                    }
//                    if (!coursepgyear.equals("null")) {
//                        byte[] coursepgyearEncryptedBytes = SimpleBase64Encoder.decode(coursepgyear);
//                        coursepgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, coursepgyearEncryptedBytes);
//                        coursepgyear = new String(coursepgyearDecryptedBytes);

//                    }
//                    if (!streampgyear.equals("null")) {
//                        byte[] streampgyearEncryptedBytes = SimpleBase64Encoder.decode(streampgyear);
//                        streampgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, streampgyearEncryptedBytes);
//                        streampgyear = new String(streampgyearDecryptedBytes);

//                    }
//                    if (!universitypgyear.equals("null")) {
//                        byte[] universitypgyearEncryptedBytes = SimpleBase64Encoder.decode(universitypgyear);
//                        universitypgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, universitypgyearEncryptedBytes);
//                        universitypgyear = new String(universitypgyearDecryptedBytes);

//                    }
//                    if (!collegenamepgyear.equals("null")) {
//                        byte[] collegenamepgyearEncryptedBytes = SimpleBase64Encoder.decode(collegenamepgyear);
//                        collegenamepgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, collegenamepgyearEncryptedBytes);
//                        collegenamepgyear = new String(collegenamepgyearDecryptedBytes);

//                    }
//                    if (!yearofpassingpgyear.equals("null")) {
//                        byte[] yearofpassingpgyearEncryptedBytes = SimpleBase64Encoder.decode(yearofpassingpgyear);
//                        yearofpassingpgyearDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, yearofpassingpgyearEncryptedBytes);
//                        yearofpassingpgyear = new String(yearofpassingpgyearDecryptedBytes);

//                    }
//                }
//
//                task1();
//                task2();
//                task3();
//
//
//                if(found_personal==1)
//                {
//                    if (!nameasten.equals("null")) {
//                        byte[] nameastenEncryptedBytes = SimpleBase64Encoder.decode(nameasten);
//                        nameastenDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, nameastenEncryptedBytes);
//                        nameasten = new String(nameastenDecryptedBytes);
//                        s.setNameasten(nameasten);
//                    }
//                    if (!mothername.equals("null")) {
//                        byte[] mothernameEncryptedBytes = SimpleBase64Encoder.decode(mothername);
//                        mothernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, mothernameEncryptedBytes);
//                        mothername = new String(mothernameDecryptedBytes);
//                        s.setMothername(mothername);
//                    }
//                    if (!dob.equals("null")) {
//                        byte[] dobEncryptedBytes = SimpleBase64Encoder.decode(dob);
//                        dobDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, dobEncryptedBytes);
//                        dob = new String(dobDecryptedBytes);
//                        s.setDob(dob);
//                    }
//                    if (!gender.equals("null")) {
//                        byte[] genderEncryptedBytes = SimpleBase64Encoder.decode(gender);
//                        genderDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, genderEncryptedBytes);
//                        gender = new String(genderDecryptedBytes);
//                        s.setGender(gender);
//                    }
//                    if (!mothertongue.equals("null")) {
//                        byte[] mothertongueEncryptedBytes = SimpleBase64Encoder.decode(mothertongue);
//                        mothertongueDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, mothertongueEncryptedBytes);
//                        mothertongue = new String(mothertongueDecryptedBytes);
//                        s.setMothertongue(mothertongue);
//                    }
//                    if (!hobbies.equals("null")) {
//                        byte[] hobbiesEncryptedBytes = SimpleBase64Encoder.decode(hobbies);
//                        hobbiesDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, hobbiesEncryptedBytes);
//                        hobbies = new String(hobbiesDecryptedBytes);
//                        s.setHobbies(hobbies);
//                    }
//                    if (!bloodgroup.equals("null")) {
//                        byte[] bloodgroupEncryptedBytes = SimpleBase64Encoder.decode(bloodgroup);
//                        bloodgroupDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, bloodgroupEncryptedBytes);
//                        bloodgroup = new String(bloodgroupDecryptedBytes);
//                        s.setBloodgroup(bloodgroup);
//                    }
//                    if (!category.equals("null")) {
//                        byte[] categoryEncryptedBytes = SimpleBase64Encoder.decode(category);
//                        categoryDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, categoryEncryptedBytes);
//                        category = new String(categoryDecryptedBytes);
//                        s.setCategory(category);
//                    }
//                    if (!religion.equals("null")) {
//                        byte[] religionEncryptedBytes = SimpleBase64Encoder.decode(religion);
//                        religionDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, religionEncryptedBytes);
//                        religion = new String(religionDecryptedBytes);
//                        s.setReligion(religion);
//                    }
//                    if (!caste.equals("null")) {
//                        byte[] casteEncryptedBytes = SimpleBase64Encoder.decode(caste);
//                        casteDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, casteEncryptedBytes);
//                        caste = new String(casteDecryptedBytes);
//                        s.setCaste(caste);
//                    }
//                    if (!prn.equals("null")) {
//                        byte[] prnEncryptedBytes = SimpleBase64Encoder.decode(prn);
//                        prnDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, prnEncryptedBytes);
//                        prn = new String(prnDecryptedBytes);
//                        s.setPrn(prn);
//                    }
//                    if (!paddrline1.equals("null")) {
//                        byte[] paddrline1EncryptedBytes = SimpleBase64Encoder.decode(paddrline1);
//                        paddrline1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, paddrline1EncryptedBytes);
//                        paddrline1 = new String(paddrline1DecryptedBytes);
//                        s.setPaddrline1(paddrline1);
//                    }
//                    if (!paddrline2.equals("null")) {
//                        byte[] paddrline2EncryptedBytes = SimpleBase64Encoder.decode(paddrline2);
//                        paddrline2DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, paddrline2EncryptedBytes);
//                        paddrline2 = new String(paddrline2DecryptedBytes);
//                        s.setPaddrline2(paddrline2);
//                    }
//                    if (!paddrline3.equals("null")) {
//                        byte[] paddrline3EncryptedBytes = SimpleBase64Encoder.decode(paddrline3);
//                        paddrline3DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, paddrline3EncryptedBytes);
//                        paddrline3 = new String(paddrline3DecryptedBytes);
//                        s.setPaddrline3(paddrline3);
//                    }
//                    if (!handicapped.equals("null")) {
//                        byte[] handicappedEncryptedBytes = SimpleBase64Encoder.decode(handicapped);
//                        handicappedDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, handicappedEncryptedBytes);
//                        handicapped = new String(handicappedDecryptedBytes);
//                        s.setHandicapped(handicapped);
//                    }
//                    if (!sports.equals("null")) {
//                        byte[] sportsEncryptedBytes = SimpleBase64Encoder.decode(sports);
//                        sportsDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, sportsEncryptedBytes);
//                        sports = new String(sportsDecryptedBytes);
//                        s.setSports(sports);
//                    }
//                    if (!defenceex.equals("null")) {
//                        byte[] defenceexEncryptedBytes = SimpleBase64Encoder.decode(defenceex);
//                        defenceexDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, defenceexEncryptedBytes);
//                        defenceex = new String(defenceexDecryptedBytes);
//                        s.setDefenceex(defenceex);
//                    }
//                    Log.d("TAG", "onPostExecute:  personal done");
//                }
//
//                downloadImage();
//                populateData();
//
//            }catch (Exception e)
//            {
//                Toast.makeText(getActivity(), "exp "+e.getMessage(), Toast.LENGTH_LONG).show();
//                Log.d("TAG", "onPostExecute: exp = "+e.getMessage());
//            }
        }

        //
//        // Creates Bitmap from InputStream and returns it
////        private Bitmap downloadImage(String url) {
////            Uri uri = new Uri.Builder()
////                    .scheme("http")
////                    .authority("192.168.100.100")
////                    .path("AESTest/GetImage")
////                    .appendQueryParameter("u", username)
////                    .build();
////
////            url=uri.toString();
////
////            Bitmap bitmap = null;
////            InputStream stream = null;
////            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
////            bmOptions.inSampleSize = 1;
////
////            try {
////                stream = getHttpConnection(url);
////                bitmap = BitmapFactory.
////                        decodeStream(stream, null, bmOptions);
////                stream.close();
////            } catch (IOException e1) {
////                e1.printStackTrace();
////            }
////            return bitmap;
////        }
//
//        // Makes HttpURLConnection and returns InputStream
//        private InputStream getHttpConnection(String urlString)
//                throws IOException {
//            InputStream stream = null;
//            URL url = new URL(urlString);
//            URLConnection connection = url.openConnection();
//
//            try {
//                HttpURLConnection httpConnection = (HttpURLConnection) connection;
//                httpConnection.setRequestMethod("GET");
//                httpConnection.connect();
//
//                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    stream = httpConnection.getInputStream();
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                Log.d("TAG", "getHttpConnection: "+ ex.getMessage());
//            }
//            return stream;
//        }
    }

        void populateData() {
            Log.d("TAG", "populateData: welcome to populate");
            if (!addressline1.equals("")) {
                contactaddr1.setText(addressline1 + " " + addressline2 + " " + addressline3);
                percentProfile++;
            }
            if (found_box1 == 1) {
                if (!fname.equals("") && !lname.equals("")) {
                    myprofilename.setText(fname + " " + lname);
                    nametxt.setText(fname + " " + lname);
                    percentProfile++;
                }

                if (!fname.equals("") && lname.equals("")) {
                    myprofilename.setText(fname);
                    percentProfile++;
                }
                if (!country.equals("") && !state.equals("") && !city.equals("")) {
                    myprofilloc.setText(city + ", " + state + ", " + country);
                    percentProfile++;
                }
            }
            if (found_tenth == 1) {
                if (!board10.equals("")) {

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

                    for (int i = 0; i < yearofpassing10.length() - 6; i++) {
                        month += yearofpassing10.charAt(i);
                    }
                    for (int i = 5; i < yearofpassing10.length(); i++) {
                        years += yearofpassing10.charAt(i);
                    }

                    int currentYear = Integer.parseInt(currentYears);
                    int year = Integer.parseInt(years);

                    if (currentYear > year)
                        myprofilecource3.setText("Attended Std. X in " + board10 + "  at");
                    else if (currentYear == year) {
                        if (map.get(currentMonth) > map.get(month)) {
                            myprofilecource3.setText("Attended Std. X in " + board10 + "  at");
                        } else {
                            myprofilecource3.setText("Attending Std. X in " + board10 + "  at");
                            myprofiledu.setText("Std. X  (" + board10 + ")");
                        }
                    } else {
                        myprofilecource3.setText("Attending Std. X in " + board10 + "  at");
                        myprofiledu.setText("Std. X  (" + board10 + ")");
                    }

                    if (!schoolname10.equals(""))
                        myprofileclgname3.setText(schoolname10);
                    if (!yearofpassing10.equals(""))
                        myprofileclgyearofpassing3.setText(yearofpassing10);
                    percentProfile++;
                }
            }
            if (found_twelth == 1) {
                Log.d("TAG", "populateData: welcome to twelth");

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

                    if (currentYear > year)
                        myprofilecource2.setText("Attended Std. XII in " + board12 + "  at");
                    else if (currentYear == year) {
                        if (map.get(currentMonth) > map.get(month)) {
                            myprofilecource2.setText("Attended Std. XII in " + board12 + "  at");
                        } else {
                            myprofilecource2.setText("Attending Std. XII in " + board12 + "  at");
                            myprofiledu.setText("Std. XII (" + board12 + ")");
                        }
                    } else {
                        myprofilecource2.setText("Attending Std. XII in " + board12 + "  at");
                        myprofiledu.setText("Std. XII (" + board12 + ")");
                    }

                    if (!schoolname12.equals(""))
                        myprofileclgname2.setText(schoolname12);
                    if (!yearofpassing12.equals(""))
                        myprofileclgyearofpassing2.setText(yearofpassing12);
                    percentProfile++;
                }
            }
            if (found_diploma == 1) {
                Log.d("TAG", "populateData: welcome to diploma");
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
                            myprofiledu.setText("Diploma (" + coursediploma + ")");
                        }
                    } else {
                        myprofilecource2.setText("Attending Diploma in " + coursediploma + "  at");
                        myprofiledu.setText("Diploma (" + coursediploma + ")");
                    }

                    if (!collegenamediploma.equals(""))
                        myprofileclgname2.setText(collegenamediploma);
                    if (!yearofpassingdiploma.equals(""))
                        myprofileclgyearofpassing2.setText(yearofpassingdiploma);
                }
                percentProfile++;
            }
            if (found_careerobj == 1) {
                if (!careerobj.equals(""))
                    careerobjtxttxt.setText(careerobj);
                percentProfile++;
            }
            if (found_strengths == 1) {
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
            if (found_weaknesses == 1) {
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
            if (found_locationpreferences == 1) {
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
            if (found_lang == 1) {
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
            if (found_certificates == 1) {
                if (!title1.equals(""))
                    acc2txttxt.setText(title1);
                if (!title1.equals("") && !title2.equals(""))
                    acc2txttxt.setText(title1 + ", " + title2);
                if (!title1.equals("") && !title2.equals("") && !title3.equals(""))
                    acc2txttxt.setText(title1 + ", " + title2 + ", " + title3);
                if (!title1.equals("") && !title2.equals("") && !title3.equals("") && !title4.equals(""))
                    acc2txttxt.setText(title1 + ", " + title2 + ", " + title3 + " ...");
            }
            if (found_courses == 1) {
                if (!course1.equals(""))
                    acc3txttxt.setText(course1);
                if (!course1.equals("") && !course2.equals(""))
                    acc3txttxt.setText(course1 + ", " + course2);
                if (!course1.equals("") && !course2.equals("") && !course3.equals(""))
                    acc3txttxt.setText(course1 + ", " + course2 + ", " + course3);
                if (!course1.equals("") && !course2.equals("") && !course3.equals("") && !course4.equals(""))
                    acc3txttxt.setText(course1 + ", " + course2 + ", " + course3 + " ...");
            }
            if (found_skills == 1) {
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
            if (found_honors == 1) {
                if (!htitle1.equals(""))
                    acc5txttxt.setText(htitle1);
                if (!htitle1.equals("") && !htitle2.equals(""))
                    acc5txttxt.setText(htitle1 + ", " + htitle2);
                if (!htitle1.equals("") && !htitle2.equals("") && !htitle3.equals(""))
                    acc5txttxt.setText(htitle1 + ", " + htitle2 + ", " + htitle3);
                if (!htitle1.equals("") && !htitle2.equals("") && !htitle3.equals("") && !htitle4.equals(""))
                    acc5txttxt.setText(htitle1 + ", " + htitle2 + ", " + htitle3 + " ...");
            }
            if (found_patents == 1) {
                if (!ptitle1.equals(""))
                    acc6txttxt.setText(ptitle1);
                if (!ptitle1.equals("") && !ptitle2.equals(""))
                    acc6txttxt.setText(ptitle1 + ", " + ptitle2);
                if (!ptitle1.equals("") && !ptitle2.equals("") && !ptitle3.equals(""))
                    acc6txttxt.setText(ptitle1 + ", " + ptitle2 + ", " + ptitle3);
                if (!ptitle1.equals("") && !ptitle2.equals("") && !ptitle3.equals("") && !ptitle4.equals(""))
                    acc6txttxt.setText(ptitle1 + ", " + ptitle2 + ", " + ptitle3 + " ...");
            }
            if (found_publications == 1) {
                if (!pubtitle1.equals(""))
                    acc7txttxt.setText(pubtitle1);
                if (!pubtitle1.equals("") && !pubtitle2.equals(""))
                    acc7txttxt.setText(pubtitle1 + ", " + pubtitle2);
                if (!pubtitle1.equals("") && !pubtitle2.equals("") && !pubtitle3.equals(""))
                    acc7txttxt.setText(pubtitle1 + ", " + pubtitle2 + ", " + pubtitle3);
                if (!pubtitle1.equals("") && !pubtitle2.equals("") && !pubtitle3.equals("") && !pubtitle4.equals(""))
                    acc7txttxt.setText(pubtitle1 + ", " + pubtitle2 + ", " + pubtitle3 + " ...");
            }

            if (found_projects == 1) {
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
            if (found_ug == 1) {
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
                    } else if (currentYear == year) {
                        if (map.get(currentMonth) > map.get(month)) {
                            myprofilecource.setText("Attended " + courseug + "  in " + streamug + " at");
                        } else {
                            myprofilecource.setText("Attending " + courseug + "  in " + streamug + " at");
                            myprofiledu.setText(courseug + " (" + streamug + ")");
                        }

                    } else {
                        myprofilecource.setText("Attending " + courseug + "  in " + streamug + " at");
                        myprofiledu.setText(courseug + " (" + streamug + ")");
                    }

                    myprofileclgname.setText(collegenameug);
                    myprofileclgyearofpassing.setText(yearofpassingug);


                    ImageView insti4 = (ImageView) rootView.findViewById(R.id.insti4);
                    RelativeLayout edutab4 = (RelativeLayout) rootView.findViewById(R.id.edutab4);
                    insti4.setVisibility(View.GONE);
                    edutab4.setVisibility(View.GONE);
                }
            }
            if (found_pgsem == 1) {
                Log.d("TAG", "populateData: universitypgsem"+universitypgsem);
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

                    ImageView insti4 = (ImageView) rootView.findViewById(R.id.insti4);
                    RelativeLayout edutab4 = (RelativeLayout) rootView.findViewById(R.id.edutab4);
                    insti4.setVisibility(View.VISIBLE);
                    edutab4.setVisibility(View.VISIBLE);
                }
            }

            if (found_pgyear == 1) {
                Log.d("TAG", "populateData: universitypgyear"+universitypgyear);
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

                    ImageView insti4 = (ImageView) rootView.findViewById(R.id.insti4);
                    RelativeLayout edutab4 = (RelativeLayout) rootView.findViewById(R.id.edutab4);
                    insti4.setVisibility(View.VISIBLE);
                    edutab4.setVisibility(View.VISIBLE);
                }
            }

            if (myprofiledu.getText().toString().equals("Current Education"))
                myprofiledu.setVisibility(View.GONE);
            else
                myprofiledu.setVisibility(View.VISIBLE);


            float R = (1000 - 0) / (15 - 0);
            float y = (percentProfile - 0) * R + 0;
            int val = Math.round(y);

            ObjectAnimator progressAnimator = ObjectAnimator.ofInt(profileprogress, "progress", 0, val);
            progressAnimator.setDuration(700);
            progressAnimator.setInterpolator(new LinearInterpolator());
            progressAnimator.start();
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

        class DeleteProfile extends AsyncTask<String, String, String> {


            protected String doInBackground(String... param) {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));
                json = jParser.makeHttpRequest(MyConstants.remove_profile, "GET", params);

                try {

                    resultofop = json.getString("info");

                } catch (Exception ex) {
                    Log.d("TAG", "DeleteProfile " + ex.getMessage());
                }

                return resultofop;
            }

            @Override
            protected void onPostExecute(String result) {

                if (resultofop.equals("success")) {
                    Toast.makeText(getActivity(), "Profile Picture removed..!", Toast.LENGTH_LONG).show();
                    refreshContent();
                    ((MainActivity) getActivity()).requestProfileImage();
                } else if (resultofop.equals("fail"))
                    Toast.makeText(getActivity(), "Failed..!", Toast.LENGTH_LONG).show();

                else if (resultofop.equals("notfound"))
                    Toast.makeText(getActivity(), "No Profile Picture..!", Toast.LENGTH_LONG).show();


            }
        }

        @Override
        public void onAttach(final Activity activity) {

            super.onAttach(activity);

            setHasOptionsMenu(true);
        }

        @Override
        public void onPrepareOptionsMenu(final Menu menu) {

            super.onPrepareOptionsMenu(menu);

            menu.clear();
        }


        private void downloadImage() {

            String t = String.valueOf(System.currentTimeMillis());

            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority("192.168.100.100")
                    .path("AESTest/GetImage")
                    .appendQueryParameter("u", username)
                    .build();

            Glide.with(this)
                    .load(uri)
                    .crossFade()
                    .signature(new StringSignature(System.currentTimeMillis() + ""))
                    .into(myprofileimg);

        }


 }

