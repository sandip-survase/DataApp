package placeme.octopusites.com.placeme;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.bumptech.glide.signature.StringSignature;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.modal.CompanyDetailsModal;
import placeme.octopusites.com.placeme.modal.ModalHrIntro;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;
import static placeme.octopusites.com.placeme.HrCompanyDetails.HRlog;

public class HRProfileFragment extends Fragment {

    CircleImageView myprofileimg;
    ProgressBar updateProgress;
    SwipeRefreshLayout swipe_refresh_layout;


    TextView myprofilename, myprofilrole, myprofiledu, myprofilloc, myprofilemail, myprofilepercenttxt;
    TextView editprofiletxt, eduboxtxt, expboxtxt, accomplishmentsboxtxt, instemailtxt, contactboxtxt, instcontactemail, acc4txttxt, instwebtxt;
    TextView myprofilecource, instteletxt, insttelephone, instwebsite, acc2txt, acc2txttxt, acc4txt, acc5txt, acc6txt, acc7txt, acc5txttxt, acc6txttxt, acc7txttxt;
    TextView exp1txt, myprofileexpfromto, myprofileexp1name, myprofileexp2name, exp2txt, myprofileexpfromto2, myprofileexp3name, exp3txt, myprofileexpfromto3, emailtxt, myprofileclgname, nametxt, mobiletxt, contactpersonalemail, contactaddr, contactprofesionalemail, myprofiledomain1, myprofileduration1, myprofiledomain2, myprofileduration2, myprofiledomain3, myprofileduration3, careerobjtxttxt, strengthstxt, weaknessestxt, locationpreferences, contactaddr1, contactmobile, contactemail, myprofilepreview;
    HashMap<String, Integer> hashMap;
    ImageView introedit, eduedit, expedit, accomplishmentsedit, careeredit, contactedit, exp2, exp3;
    final static CharSequence[] items = {"View Profile Picture", "Update Profile Picture", "Delete Profile Picture"};
    RelativeLayout editprofilerl, exptab2, exptab3;
    String username = "", resultofop,dataobject="",companydataobject="";
    //

    String fname = "", lname = "", country = "", state = "", city = "", designation = "", phone = "";
    int found_intro_box = 0, found_contact_details = 0, found_skills = 0, found_honors = 0, found_patents = 0, found_publications = 0;
    String email2 = "", addressline1 = "", addressline2 = "", addressline3 = "", telephone = "", mobile2 = "";
    String skill1 = "", skill2 = "", skill3 = "", skill4 = "", skill5 = "", skill6 = "", skill7 = "", skill8 = "", skill9 = "", skill10 = "", skill11 = "", skill12 = "", skill13 = "", skill14 = "", skill15 = "", skill16 = "", skill17 = "", skill18 = "", skill19 = "", skill20 = "";
    String sproficiency1 = "", sproficiency2 = "", sproficiency3 = "", sproficiency4 = "", sproficiency5 = "", sproficiency6 = "", sproficiency7 = "", sproficiency8 = "", sproficiency9 = "", sproficiency10 = "", sproficiency11 = "", sproficiency12 = "", sproficiency13 = "", sproficiency14 = "", sproficiency15 = "", sproficiency16 = "", sproficiency17 = "", sproficiency18 = "", sproficiency19 = "", sproficiency20 = "";
    String htitle1 = "", hissuer1 = "", hdescription1 = "", htitle2 = "", hissuer2 = "", hdescription2 = "", htitle3 = "", hissuer3 = "", hdescription3 = "", htitle4 = "", hissuer4 = "", hdescription4 = "", htitle5 = "", hissuer5 = "", hdescription5 = "", htitle6 = "", hissuer6 = "", hdescription6 = "", htitle7 = "", hissuer7 = "", hdescription7 = "", htitle8 = "", hissuer8 = "", hdescription8 = "", htitle9 = "", hissuer9 = "", hdescription9 = "", htitle10 = "", hissuer10 = "", hdescription10 = "", yearofhonor1 = "", yearofhonor2 = "", yearofhonor3 = "", yearofhonor4 = "", yearofhonor5 = "", yearofhonor6 = "", yearofhonor7 = "", yearofhonor8 = "", yearofhonor9 = "", yearofhonor10 = "";
    String ptitle1 = "", pappno1 = "", pinventor1 = "", pissue1 = "", pfiling1 = "", purl1 = "", pdescription1 = "", ptitle2 = "", pappno2 = "", pinventor2 = "", pissue2 = "", pfiling2 = "", purl2 = "", pdescription2 = "", ptitle3 = "", pappno3 = "", pinventor3 = "", pissue3 = "", pfiling3 = "", purl3 = "", pdescription3 = "", ptitle4 = "", pappno4 = "", pinventor4 = "", pissue4 = "", pfiling4 = "", purl4 = "", pdescription4 = "", ptitle5 = "", pappno5 = "", pinventor5 = "", pissue5 = "", pfiling5 = "", purl5 = "", pdescription5 = "", ptitle6 = "", pappno6 = "", pinventor6 = "", pissue6 = "", pfiling6 = "", purl6 = "", pdescription6 = "", ptitle7 = "", pappno7 = "", pinventor7 = "", pissue7 = "", pfiling7 = "", purl7 = "", pdescription7 = "", ptitle8 = "", pappno8 = "", pinventor8 = "", pissue8 = "", pfiling8 = "", purl8 = "", pdescription8 = "", ptitle9 = "", pappno9 = "", pinventor9 = "", pissue9 = "", pfiling9 = "", purl9 = "", pdescription9 = "", ptitle10 = "", pappno10 = "", pinventor10 = "", pissue10 = "", pfiling10 = "", purl10 = "", pdescription10 = "", pselectedcountry1 = "", pselectedcountry2 = "", pselectedcountry3 = "", pselectedcountry4 = "", pselectedcountry5 = "", pselectedcountry6 = "", pselectedcountry7 = "", pselectedcountry8 = "", pselectedcountry9 = "", pselectedcountry10 = "", issuedorpending1 = "", issuedorpending2 = "", issuedorpending3 = "", issuedorpending4 = "", issuedorpending5 = "", issuedorpending6 = "", issuedorpending7 = "", issuedorpending8 = "", issuedorpending9 = "", issuedorpending10 = "";
    String pubtitle1 = "", publication1 = "", author1 = "", puburl1 = "", pubdescription1 = "", pubtitle2 = "", publication2 = "", author2 = "", puburl2 = "", pubdescription2 = "", pubtitle3 = "", publication3 = "", author3 = "", puburl3 = "", pubdescription3 = "", pubtitle4 = "", publication4 = "", author4 = "", puburl4 = "", pubdescription4 = "", pubtitle5 = "", publication5 = "", author5 = "", puburl5 = "", pubdescription5 = "", pubtitle6 = "", publication6 = "", author6 = "", puburl6 = "", pubdescription6 = "", pubtitle7 = "", publication7 = "", author7 = "", puburl7 = "", pubdescription7 = "", pubtitle8 = "", publication8 = "", author8 = "", puburl8 = "", pubdescription8 = "", pubtitle9 = "", publication9 = "", author9 = "", puburl9 = "", pubdescription9 = "", pubtitle10 = "", publication10 = "", author10 = "", puburl10 = "", pubdescription10 = "", publicationdate1 = "", publicationdate2 = "", publicationdate3 = "", publicationdate4 = "", publicationdate5 = "", publicationdate6 = "", publicationdate7 = "", publicationdate8 = "", publicationdate9 = "", publicationdate10 = "";
    String lang1 = "", proficiency1 = "", lang2 = "", proficiency2 = "", lang3 = "", proficiency3 = "", lang4 = "", proficiency4 = "", lang5 = "", proficiency5 = "", lang6 = "", proficiency6 = "", lang7 = "", proficiency7 = "", lang8 = "", proficiency8 = "", lang9 = "", proficiency9 = "", lang10 = "", proficiency10 = "";
    //
    String languages[], codes[];
    JSONParser jParser = new JSONParser();
    JSONParser jParserlang = new JSONParser();
    JSONObject json, jsonlang;
    String digest1, digest2, plainusername = "";
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    int found_box1 = 0, found_lang = 0, found_exp = 0;
//    private static String load_last_updated = "http://192.168.100.10/AESTest/GetLastUpdated";
//    private static String load_HR_data = "http://192.168.100.10/AESTest/GetHrData";
//    private static String url_getlanguages = "http://192.168.100.10/AESTest/GetLanguages";
//    private static String remove_profile = "http://192.168.100.10/AESTest/RemoveImage";
//    private static String load_student_image = "http://192.168.100.10/AESTest/GetImage";
    int count;
    public final static int COMPANY_DEATAILS_CHANGE_REQUEST = 10;
    int percentProfile = 0;
    String CompanyName = "", CompanyEmail = "", CompanyWeb = "", Companyphone = "", CompanyAltPhone = "", CompanyCIIN = "", CompanyNature = "", Companyaddl1 = "", Companyaddl2 = "", Companyaddl3 = "";
    String usernamestr = "", CompanyNamestr = "", CompanyEmailstr = "", CompanyWebstr = "", Companyphonestr = "", CompanyAltPhonestr = "", CompanyCIINstr = "", CompanyNaturestr = "", Companyaddl1str = "", Companyaddl2str = "", Companyaddl3str = "";
    //    String lang1, lang2, lang3, lang4, lang5, lang6, lang7, lang8, lang9, lang10;
    String langstr1, langstr2, langstr3, langstr4, langstr5, langstr6, langstr7, langstr8, langstr9, langstr10;
    String profecc1, profecc2, profecc3, profecc4, profecc5, profecc6, profecc7, profecc8, profecc9, profecc10;
    String profeccstr1, profeccstr2, profeccstr3, profeccstr4, profeccstr5, profeccstr6, profeccstr7, profeccstr8, profeccstr9, profeccstr10;
    String posts1 = "", posts2 = "", posts3 = "", posts4 = "", posts5 = "", posts6 = "", posts7 = "", posts8 = "", posts9 = "", posts10 = "";
    String inst1s1 = "", inst1s2 = "", inst1s3 = "", inst1s4 = "", inst1s5 = "", inst1s6 = "", inst1s7 = "", inst1s8 = "", inst1s9 = "", inst1s10 = "";
    String fromdates1 = "", todates1 = "", fromdates2 = "", todates2 = "", fromdates3 = "", todates3 = "", fromdates4 = "", todates4 = "", fromdates5 = "", todates5 = "", fromdates6 = "", todates6 = "", fromdates7 = "", todates7 = "", fromdates8 = "", todates8 = "", fromdates9 = "", todates9 = "", fromdates10 = "", todates10 = "";
    boolean hrinfobox1 = false, hrinfobox2 = false, hrinfobox3 = false;

    private SharedPreferences sharedpreferences;
    HrData hrData = new HrData();

    StudentData s = new StudentData();
    private String signature = "";

    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding;
    public String role = "";

    public HRProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hr_profile, container, false);


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

        myprofileimg = (CircleImageView) rootView.findViewById(R.id.myprofileimg);
        myprofilename = (TextView) rootView.findViewById(R.id.myprofilename);
        myprofilrole = (TextView) rootView.findViewById(R.id.myprofilrole);
        myprofiledu = (TextView) rootView.findViewById(R.id.myprofiledu);
        myprofilloc = (TextView) rootView.findViewById(R.id.myprofilloc);
        myprofilemail = (TextView) rootView.findViewById(R.id.myprofilemail);

        myprofilepercenttxt = (TextView) rootView.findViewById(R.id.myprofilepercenttxt);
        editprofiletxt = (TextView) rootView.findViewById(R.id.editprofiletxt);

        eduboxtxt = (TextView) rootView.findViewById(R.id.eduboxtxt);
        accomplishmentsboxtxt = (TextView) rootView.findViewById(R.id.accomplishmentsboxtxt);
        expboxtxt = (TextView) rootView.findViewById(R.id.expboxtxt);
        contactboxtxt = (TextView) rootView.findViewById(R.id.contactboxtxt);

        updateProgress = (ProgressBar) rootView.findViewById(R.id.updateProgressmain);


        swipe_refresh_layout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        SwipeRefreshLayout tswipe_refresh_layout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setVisibility(View.GONE);

        myprofilecource = (TextView) rootView.findViewById(R.id.myprofilecource);
        instemailtxt = (TextView) rootView.findViewById(R.id.instemailtxt);
        instwebtxt = (TextView) rootView.findViewById(R.id.instwebtxt);
        instteletxt = (TextView) rootView.findViewById(R.id.instteletxt);

        myprofileclgname = (TextView) rootView.findViewById(R.id.myprofileclgname);
        instcontactemail = (TextView) rootView.findViewById(R.id.instcontactemail);
        instwebsite = (TextView) rootView.findViewById(R.id.instwebsite);
        insttelephone = (TextView) rootView.findViewById(R.id.insttelephone);


        acc2txt = (TextView) rootView.findViewById(R.id.acc2txt);
        acc4txt = (TextView) rootView.findViewById(R.id.acc4txt);
        acc5txt = (TextView) rootView.findViewById(R.id.acc5txt);
        acc6txt = (TextView) rootView.findViewById(R.id.acc6txt);
        acc7txt = (TextView) rootView.findViewById(R.id.acc7txt);


        acc2txttxt = (TextView) rootView.findViewById(R.id.acc2txttxt);
        acc4txttxt = (TextView) rootView.findViewById(R.id.acc4txttxt);
        acc5txttxt = (TextView) rootView.findViewById(R.id.acc5txttxt);
        acc6txttxt = (TextView) rootView.findViewById(R.id.acc6txttxt);
        acc7txttxt = (TextView) rootView.findViewById(R.id.acc7txttxt);

        exp1txt = (TextView) rootView.findViewById(R.id.exp1txt);
        myprofileexp1name = (TextView) rootView.findViewById(R.id.myprofileexp1name);
        myprofileexpfromto = (TextView) rootView.findViewById(R.id.myprofileexpfromto);

        exp2txt = (TextView) rootView.findViewById(R.id.exp2txt);
        myprofileexp2name = (TextView) rootView.findViewById(R.id.myprofileexp2name);
        myprofileexpfromto2 = (TextView) rootView.findViewById(R.id.myprofileexpfromto1);

        exp3txt = (TextView) rootView.findViewById(R.id.exp3txt);
        myprofileexp3name = (TextView) rootView.findViewById(R.id.myprofileexp3name);
        myprofileexpfromto3 = (TextView) rootView.findViewById(R.id.myprofileexpfromto2);


        nametxt = (TextView) rootView.findViewById(R.id.nametxt);
        emailtxt = (TextView) rootView.findViewById(R.id.emailtxt);
        mobiletxt = (TextView) rootView.findViewById(R.id.mobiletxt);

        contactaddr = (TextView) rootView.findViewById(R.id.contactaddr);
        contactprofesionalemail = (TextView) rootView.findViewById(R.id.contactprofesionalemail);
        contactpersonalemail = (TextView) rootView.findViewById(R.id.contactpersonalemail);
        contactmobile = (TextView) rootView.findViewById(R.id.contactmobile);


        contactaddr1 = (TextView) rootView.findViewById(R.id.contactaddr);

        contactmobile = (TextView) rootView.findViewById(R.id.contactmobile);

        myprofilepreview = (TextView) rootView.findViewById(R.id.myprofilepreview);


        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/arba.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ubuntu.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/arimo.ttf");
        Typeface custom_font4 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/meriitalic.ttf");
        Typeface custom_font5 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/righteous.ttf");
        Typeface custom_font6 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/rockitbold.ttf");
        Typeface custom_font7 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/portano.ttf");
        Typeface custom_font8 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/montbold.ttf");

        myprofilepreview.setTypeface(custom_font8);
        myprofilename.setTypeface(custom_font1);
        myprofilrole.setTypeface(custom_font2);
        myprofiledu.setTypeface(custom_font3);
        myprofilloc.setTypeface(custom_font3);
        myprofilemail.setTypeface(custom_font3);
        myprofilepercenttxt.setTypeface(custom_font4);
        editprofiletxt.setTypeface(custom_font5);
        eduboxtxt.setTypeface(custom_font1);
        expboxtxt.setTypeface(custom_font1);
        accomplishmentsboxtxt.setTypeface(custom_font1);
        contactboxtxt.setTypeface(custom_font1);

        myprofilecource.setTypeface(custom_font6);
        instemailtxt.setTypeface(custom_font6);
        instwebtxt.setTypeface(custom_font6);
        instteletxt.setTypeface(custom_font6);

        acc2txt.setTypeface(custom_font6);
        acc4txt.setTypeface(custom_font6);
        acc5txt.setTypeface(custom_font6);
        acc6txt.setTypeface(custom_font6);
        acc7txt.setTypeface(custom_font6);

        myprofileclgname.setTypeface(custom_font7);
        instcontactemail.setTypeface(custom_font7);
        instwebsite.setTypeface(custom_font7);
        insttelephone.setTypeface(custom_font7);
        acc2txttxt.setTypeface(custom_font7);
        acc4txttxt.setTypeface(custom_font7);
        acc5txttxt.setTypeface(custom_font7);
        acc6txttxt.setTypeface(custom_font7);
        acc7txttxt.setTypeface(custom_font7);

        exp1txt.setTypeface(custom_font6);
        myprofileexp1name.setTypeface(custom_font7);
        myprofileexpfromto.setTypeface(custom_font7);

        exp2txt.setTypeface(custom_font6);
        myprofileexp2name.setTypeface(custom_font7);
        myprofileexpfromto2.setTypeface(custom_font7);

        exp3txt.setTypeface(custom_font6);
        myprofileexp3name.setTypeface(custom_font7);
        myprofileexpfromto3.setTypeface(custom_font7);

        nametxt.setTypeface(custom_font6);
        emailtxt.setTypeface(custom_font6);
        mobiletxt.setTypeface(custom_font6);

        contactaddr.setTypeface(custom_font7);
        contactprofesionalemail.setTypeface(custom_font7);
        contactpersonalemail.setTypeface(custom_font7);
        contactmobile.setTypeface(custom_font7);


        introedit = (ImageView) rootView.findViewById(R.id.introedit);
        eduedit = (ImageView) rootView.findViewById(R.id.eduedit);
        accomplishmentsedit = (ImageView) rootView.findViewById(R.id.accomplishmentsedit);
        expedit = (ImageView) rootView.findViewById(R.id.expedit);
        contactedit = (ImageView) rootView.findViewById(R.id.contactedit);

        editprofilerl = (RelativeLayout) rootView.findViewById(R.id.editprofilerl);
        exptab2 = (RelativeLayout) rootView.findViewById(R.id.exptab2);
        exptab3 = (RelativeLayout) rootView.findViewById(R.id.exptab3);
        exp2 = (ImageView) rootView.findViewById(R.id.exp2);
        exp3 = (ImageView) rootView.findViewById(R.id.exp3);

//        eduedit=(ImageView)rootView.findViewById(R.id.eduedit);
//        projectsedit=(ImageView)rootView.findViewById(R.id.projectsedit);
//        accomplishmentsedit=(ImageView)rootView.findViewById(R.id.accomplishmentsedit);
//        careeredit=(ImageView)rootView.findViewById(R.id.careeredit);
//        contactedit=(ImageView)rootView.findViewById(R.id.contactedit);


        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = sharedpreferences.getString(Username, "username not found");

        digest1 = sharedpreferences.getString("digest1", null);
        digest2 = sharedpreferences.getString("digest2", null);
        role = sharedpreferences.getString("role", "role not found");
        Digest d = new Digest();
        digest1 = d.getDigest1();
        digest2 = d.getDigest2();

        demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        demoIVBytes = SimpleBase64Encoder.decode(digest2);
        sPadding = "ISO10126Padding";

        try {
//            byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
//            byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
//            String sPadding = "ISO10126Padding";


            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(username);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);
//            Log.d(HRlog,"username -"+plainusername);
//            Log.d(HRlog,"role -"+ role);

            myprofilemail.setText(plainusername);
            contactpersonalemail.setText(plainusername);
            myprofilrole.setText(role.toUpperCase());
//            Toast.makeText(getActivity(),plainusername+ "  \n"+role, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
//            Toast.makeText(getActivity(), "excetion - "+e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d(HRlog, "exception " + e.getMessage());
        }


        introedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), HrIntro.class), 0);
            }
        });
        eduedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), HrCompanyDetails.class), 0);
                //
            }
        });
        accomplishmentsedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), AdminAccomplishments.class), 0);
            }
        });
        expedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(getActivity(), HR_Experiences.class), 0);
            }
        });
        contactedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), HrContactDetails.class), 0);
            }
        });


//        eduedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),MyProfileEdu.class).putExtra("username",username));
//            }
//        });
//        projectsedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),MyProfileProjects.class).putExtra("username",username));
//            }
//        });
//        accomplishmentsedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),MyProfileAccomplishments.class).putExtra("username",username));
//            }
//        });
//        careeredit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),MyProfileCareerDetails.class).putExtra("username",username));
//            }
//        });
//        contactedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),MyProfileContact.class).putExtra("username",username));
//            }
//        });
//
//
        myprofileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new FireMissilesDialogFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "missiles");
            }
        });


        editprofilerl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivityForResult(new Intent(getActivity(), EditProfileHr.class), 0);
            }
        });

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new GetHRData().execute();
                //  ((MainActivity)getActivity()).requestProfileImage();
            }
        });


        myprofileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

//        new GetHRData().execute();
        refreshContent();

        return rootView;
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


    public void refreshContent() {
        new GetHRData().execute();
//        ((HRActivity)getActivity()).requestProfileImage();
//        updateProgress.setVisibility(View.VISIBLE);
//        updateProgress.setVisibility(View.GONE);

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
                    ((HRActivity) getActivity()).requestCropImage();

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

        Log.d(HRlog, "exp count " + exp_count);

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

    public static class FireMissilesDialogFragment extends DialogFragment {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose Action").setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    if (which == 0) {
                        startActivity(new Intent(getContext(), ViewProfileImage.class));
                    } else if (which == 1) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
                    } else if (which == 2) {

                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
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

    public void showUpdateProgress() {
        updateProgress.setVisibility(View.VISIBLE);
    }
// **********************************************************
    private class GetHRData extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;


            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));

                json = jParser.makeHttpRequest(MyConstants.load_HR_data, "GET", params);

                String s="";

                Log.d("TAG", "doInBackground: before dataobject");

                resultofop=json.getString("info");

                Log.d("TAG", "doInBackground: resultofop - "+resultofop);

                if(resultofop.equals("found"))
                {
                    phone=json.getString("phone");

                    s=json.getString("intro");

                    Log.d("TAG", "doInBackground: intro "+s);

                    if(s.equals("found"))
                    {
                        found_intro_box=1;
                        Log.d("TAG", "dataobject===================found_intro_box: "+found_intro_box);

                        dataobject=json.getString("introObj");

                        Log.d("TAG", "dataobject===================: "+dataobject);

                    }



                    s=json.getString("companyBox");

                    Log.d("TAG", "doInBackground: intro "+s);
                    if(s.equals("found"))
                    {
                        found_box1=1;
                        Log.d("TAG", "dataobject===================found_box1: "+found_box1);

                        companydataobject=json.getString("companyBoxObj");

                        Log.d("TAG", "dataobject===================: "+dataobject);

                    }

                }

//


            } catch (Exception e) {
                e.printStackTrace();
                Log.d(HRlog, "Exception **** " + e.getMessage());
            }
            return map;
        }

        protected void onPostExecute(Bitmap result) {

            swipe_refresh_layout.setRefreshing(false);

            downloadImage();

            try {

                Log.d(HRlog, "onPostExecute: decrepted methods() ");

                decrept_intro();
                decrept_company();

                populateData();

            } catch (Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    public void decrept_company(){
        Log.d("TAG", "decrept_company: in decrypted ()");
        try {

            if (found_box1 == 1) {

                Log.d("TAG", "decrept_company: before fromstring decodede - "+companydataobject);

                CompanyDetailsModal objstr = (CompanyDetailsModal)fromString(companydataobject,MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

                Log.d("TAG", "decrept_company objstr :-"+objstr.ComName+"   "+objstr.ComMail+""+objstr.ComWeb+ "  "+objstr.ComPhone+"   "+objstr.ComAlterPhone+"   "+objstr.ComCIIN+"   "+objstr.ComAdd1+  "   "+objstr.ComAdd2+"   "+objstr.ComAdd3);

                CompanyNamestr = objstr.ComName;
                CompanyEmailstr = objstr.ComMail;
                CompanyWebstr  = objstr.ComWeb;
                Companyphonestr = objstr.ComPhone;
                CompanyAltPhonestr = objstr.ComAlterPhone;
                CompanyCIINstr = objstr.ComCIIN;
                CompanyNaturestr = objstr.CompanyNature;
                Companyaddl1str = objstr.ComAdd1;
                Companyaddl2str = objstr.ComAdd2;
                Companyaddl3str = objstr.ComAdd3;

                hrData.setCompanyName(CompanyNamestr);
                hrData.setCompanyEmail(CompanyEmailstr);
                hrData.setCompanyWeb(CompanyWebstr);
                hrData.setCompanyphone(Companyphonestr);
                hrData.setCompanyAltPhone(CompanyAltPhonestr);
                hrData.setCompanyCIIN(CompanyCIINstr);
                hrData.setCompanyNature(CompanyNaturestr);

                hrData.setCompanyaddl1(Companyaddl1str);
                hrData.setCompanyaddl2(Companyaddl2str);
                hrData.setCompanyaddl3(Companyaddl3str);

                Log.d("TAG", "decrept_company: - "+CompanyNaturestr);


            }
        } catch (Exception ep) {
            Log.d("TAG", "decrept_company: "+ ep.getMessage());
        }


    }
        public void decrept_intro() {

                Log.d("TAG", "decrept_intro: in decrypted ()");
                try {

                    if (found_intro_box == 1) {


                        ArrayList<ModalHrIntro> obj2=(ArrayList<ModalHrIntro>)fromString(dataobject,MySharedPreferencesManager.getDigest1(getActivity()),MySharedPreferencesManager.getDigest2(getActivity()));

                        for(int i=0;i<obj2.size();i++)
                        {
                            Log.d("TAG", "onPostExecute: decrypte data  - "+obj2.get(i).firstname+"   "+obj2.get(i).lastname+"  "+obj2.get(i).designationValue+ "  "+obj2.get(i).selectedCountry+"   "+obj2.get(i).selectedState+"   "+obj2.get(i).selectedCity);

                            fname = obj2.get(i).firstname;
                            lname =obj2.get(i).lastname ;
                            designation = obj2.get(i).designationValue;
                            country = obj2.get(i).selectedCountry ;
                            state = obj2.get(i).selectedState;
                            city = obj2.get(i).selectedCity;
                        }

                        hrData.setFname(fname);
//                    hrdata.setMname(mname);
                        hrData.setLname(lname);
                        hrData.setDesignation(designation);
                        hrData.setCountry(country);
                        hrData.setState(state);
                        hrData.setCity(city);
                    }
                } catch (Exception ep) {

                }

        }


        void populateData() {
            setVisibilityExpbox();

            percentProfile = 0;

            if (found_intro_box == 1) {
                if (!fname.equals("") && !lname.equals("")) {

                    String firstLetterCapFirstname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
                    String firstLetterCapLastname = lname.substring(0, 1).toUpperCase() + lname.substring(1);

                    myprofilename.setText(firstLetterCapFirstname + " " + firstLetterCapLastname);
                    nametxt.setText(fname + " " + lname);
                    percentProfile++;
                }
                if (!fname.equals("") && lname.equals("")) {
                    myprofilename.setText(fname);
                    percentProfile++;
                }
                if (!country.equals("null") && !state.equals("null") && !city.equals("null") && !country.equals("") && !state.equals("") && !city.equals("")) {
                    myprofilloc.setText(city + ", " + state + ", " + country);
                    percentProfile++;
                }
                if (!designation.equals("null") && !designation.equals("")) {
                    Log.d("TAG", "populateData: designation "+designation);
                    myprofiledu.setText(designation);
                    percentProfile++;
                }
            }
            if (found_lang == 1) {
                if (!lang1.equals("") && !lang1.equals("- Select Language -"))
                    acc2txttxt.setText(lang1);
                if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -"))
                    acc2txttxt.setText(lang1 + ", " + lang2);
                if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -") && !lang3.equals("") && !lang3.equals("- Select Language -"))
                    acc2txttxt.setText(lang1 + ", " + lang2 + ", " + lang3);
                if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -") && !lang3.equals("") && !lang3.equals("- Select Language -") && !lang4.equals("") && !lang4.equals("- Select Language -"))
                    acc2txttxt.setText(lang1 + ", " + lang2 + ", " + lang3 + " ...");
                percentProfile++;
            }
            if (found_contact_details == 1) {

                if (!addressline1.equals("") && !addressline2.equals("") && !addressline3.equals("")) {
                    contactaddr.setText(addressline1 + ", " + addressline2 + ", " + addressline3);
                    percentProfile++;
                }
                if (!email2.equals("")) {
                    contactprofesionalemail.setText(email2);
                    percentProfile++;
                }
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
                Log.d("TAG", skill1 + ", " + skill2 + ", " + skill3 + " ...");
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
                percentProfile++;
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
                percentProfile++;
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
                percentProfile++;
            }

            if (found_box1 == 1) {
                if (CompanyNamestr != "") {
                    myprofilecource.setText(CompanyNamestr);
                }
                if (Companyaddl1str != "" && Companyaddl2str != "" && Companyaddl3str != "") {
                    myprofileclgname.setText(Companyaddl1str + ", " + Companyaddl2str + ", " + Companyaddl3str);
                }
                if (CompanyEmailstr != "") {
                    instcontactemail.setText(CompanyEmailstr);
                }
                if (CompanyWebstr != "") {
                    instwebsite.setText(CompanyWebstr);

                }
                if (Companyphonestr != "") {
                    insttelephone.setText(Companyphonestr);
                }
                percentProfile++;

            }


            populateHrInfo();

            if (hrinfobox1 == true)
                percentProfile++;

            if (hrinfobox2 == true)
                percentProfile++;

            if (hrinfobox3 == true)
                percentProfile++;


            float R = (1000 - 0) / (15 - 0);
            float y = (percentProfile - 0) * R + 0;
            int val = Math.round(y);

            ObjectAnimator progressAnimator = ObjectAnimator.ofInt(updateProgress, "progress", 0, val);
            progressAnimator.setDuration(1000);
            progressAnimator.setInterpolator(new LinearInterpolator());
            progressAnimator.start();


            // hr
        }


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

    class DeleteProfile extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(MyConstants.remove_profile, "GET", params);

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
                ((HRActivity) getActivity()).requestProfileImage();
            } else if (resultofop.equals("fail"))
                Toast.makeText(getActivity(), "Failed..!", Toast.LENGTH_LONG).show();

            else if (resultofop.equals("notfound"))
                Toast.makeText(getActivity(), "No Profile Picture..!", Toast.LENGTH_LONG).show();


        }
    }

    // our code here
    class Getsingnature extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(MyConstants.load_last_updated, "GET", params);
            try {

                signature = json.getString("lastupdated");
                Log.d(HRlog, "signature-: " + signature);


            } catch (Exception ex) {

            }
            return signature;
        }

        @Override
        protected void onPostExecute(String result) {


        }
    }

    private void downloadImage() {

        new Getsingnature().execute();
        String t = String.valueOf(System.currentTimeMillis());

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("192.168.100.10")
                .path("AESTest/GetImage")
                .appendQueryParameter("u", username)
                .build();

        Glide.with(getContext())
                .load(uri)
                .crossFade()
                .signature(new StringSignature(System.currentTimeMillis() + ""))
                .into(myprofileimg);

    }

}
