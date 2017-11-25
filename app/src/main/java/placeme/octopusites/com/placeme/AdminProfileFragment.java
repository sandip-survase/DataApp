package placeme.octopusites.com.placeme;


import android.animation.ObjectAnimator;
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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
import placeme.octopusites.com.placeme.modal.AdminContactDetailsModal;
import placeme.octopusites.com.placeme.modal.AdminInstituteModal;
import placeme.octopusites.com.placeme.modal.AdminIntroModal;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;
import static placeme.octopusites.com.placeme.HrCompanyDetails.HRlog;

public class AdminProfileFragment extends Fragment {

    CircleImageView myprofileimg;
    TextView myprofilename, myprofilrole, myprofiledu, myprofilloc, myprofilemail, myprofilepercenttxt;
    final static CharSequence[] items = {"View Profile Picture", "Update Profile Picture", "Delete Profile Picture"};
    RelativeLayout editprofilerl;
    RelativeLayout exptab2,exptab3;


    int val1=0,val2=0;

    TextView editprofiletxt, eduboxtxt, expboxtxt, accomplishmentsboxtxt, instemailtxt, contactboxtxt, instcontactemail, acc4txttxt, instwebtxt;
    TextView myprofilecource, instteletxt, insttelephone, instwebsite, acc2txt, acc2txttxt, acc4txt, acc5txt, acc6txt, acc7txt, acc5txttxt, acc6txttxt, acc7txttxt;
    TextView exp1txt, myprofileexpfromto, myprofileexp1name,exp2txt, myprofileexpfromto2, myprofileexp2name,exp3txt, myprofileexpfromto3, myprofileexp3name, emailtxt, myprofileclgname, nametxt, mobiletxt, contactpersonalemail, contactaddr, contactprofesionalemail, myprofiledomain1, myprofileduration1, myprofiledomain2, myprofileduration2, myprofiledomain3, myprofileduration3, careerobjtxttxt, strengthstxt, weaknessestxt, locationpreferences, contactaddr1, contactmobile, contactemail, myprofilepreview;
    ImageView introedit, eduedit, expedit, accomplishmentsedit, careeredit, contactedit;
    ImageView diamond1,diamond2,diamond3;
    ImageView exp2,exp3;

    //sssss
    AdminData a = new AdminData();
    StudentData s=new StudentData();
    ProgressBar mainloadingbar,updateProgress;
    SwipeRefreshLayout swipe_refresh_layout;
    ProgressBar profileprogress;
    JSONParser jParser = new JSONParser();
    JSONObject json;
//    private static String load_Admin_data = "http://192.168.100.100/AESTest/GetAdminData";
        private static String load_Admin_data = "http://192.168.100.30:8080/ProfileObjects/GetAdminData";

    private static String load_student_image = "http://192.168.100.100/AESTest/GetImage";
    private static String remove_profile = "http://192.168.100.100/AESTest/RemoveImage";

    String digest1,digest2;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username = "", resultofop;

    int found_lang=0, found_AdminIntro = 0,found_institute=0,found_box2=0,found_skills=0,found_honors=0,found_patents=0,found_publications=0;
    int found_contact_details=0,found_personal=0,found_experiences=0,found_admin_personal=0;
    int intfromdat1=0,inttodate1=0,intfromdate2=0,inttodate2=0;


    String fname = "", mname = "", lname = "", country = "", state = "", city = "", role = "", plainusername = "", phone = "",inst="";
    String instname="",instemail="",instweb="",instphone="",instaltrphone="",universityname="",instreg="";
    String lang1="",proficiency1="",lang2="",proficiency2="",lang3="",proficiency3="",lang4="",proficiency4="",lang5="",proficiency5="",lang6="",proficiency6="",lang7="",proficiency7="",lang8="",proficiency8="",lang9="",proficiency9="",lang10="",proficiency10="";
    String skill1="",skill2="",skill3="",skill4="",skill5="",skill6="",skill7="",skill8="",skill9="",skill10="",skill11="",skill12="",skill13="",skill14="",skill15="",skill16="",skill17="",skill18="",skill19="",skill20="",sproficiency1="",sproficiency2="",sproficiency3="",sproficiency4="",sproficiency5="",sproficiency6="",sproficiency7="",sproficiency8="",sproficiency9="",sproficiency10="",sproficiency11="",sproficiency12="",sproficiency13="",sproficiency14="",sproficiency15="",sproficiency16="",sproficiency17="",sproficiency18="",sproficiency19="",sproficiency20="";
    String htitle1="",hissuer1="",hdescription1="",htitle2="",hissuer2="",hdescription2="",htitle3="",hissuer3="",hdescription3="",htitle4="",hissuer4="",hdescription4="",htitle5="",hissuer5="",hdescription5="",htitle6="",hissuer6="",hdescription6="",htitle7="",hissuer7="",hdescription7="",htitle8="",hissuer8="",hdescription8="",htitle9="",hissuer9="",hdescription9="",htitle10="",hissuer10="",hdescription10="",yearofhonor1="",yearofhonor2="",yearofhonor3="",yearofhonor4="",yearofhonor5="",yearofhonor6="",yearofhonor7="",yearofhonor8="",yearofhonor9="",yearofhonor10="";
    String ptitle1="",pappno1="",pinventor1="",pissue1="",pfiling1="",purl1="",pdescription1="",ptitle2="",pappno2="",pinventor2="",pissue2="",pfiling2="",purl2="",pdescription2="",ptitle3="",pappno3="",pinventor3="",pissue3="",pfiling3="",purl3="",pdescription3="",ptitle4="",pappno4="",pinventor4="",pissue4="",pfiling4="",purl4="",pdescription4="",ptitle5="",pappno5="",pinventor5="",pissue5="",pfiling5="",purl5="",pdescription5="",ptitle6="",pappno6="",pinventor6="",pissue6="",pfiling6="",purl6="",pdescription6="",ptitle7="",pappno7="",pinventor7="",pissue7="",pfiling7="",purl7="",pdescription7="",ptitle8="",pappno8="",pinventor8="",pissue8="",pfiling8="",purl8="",pdescription8="",ptitle9="",pappno9="",pinventor9="",pissue9="",pfiling9="",purl9="",pdescription9="",ptitle10="",pappno10="",pinventor10="",pissue10="",pfiling10="",purl10="",pdescription10="",pselectedcountry1="",pselectedcountry2="",pselectedcountry3="",pselectedcountry4="",pselectedcountry5="",pselectedcountry6="",pselectedcountry7="",pselectedcountry8="",pselectedcountry9="",pselectedcountry10="",issuedorpending1="",issuedorpending2="",issuedorpending3="",issuedorpending4="",issuedorpending5="",issuedorpending6="",issuedorpending7="",issuedorpending8="",issuedorpending9="",issuedorpending10="";
    String pubtitle1="",publication1="",author1="",puburl1="",pubdescription1="",pubtitle2="",publication2="",author2="",puburl2="",pubdescription2="",pubtitle3="",publication3="",author3="",puburl3="",pubdescription3="",pubtitle4="",publication4="",author4="",puburl4="",pubdescription4="",pubtitle5="",publication5="",author5="",puburl5="",pubdescription5="",pubtitle6="",publication6="",author6="",puburl6="",pubdescription6="",pubtitle7="",publication7="",author7="",puburl7="",pubdescription7="",pubtitle8="",publication8="",author8="",puburl8="",pubdescription8="",pubtitle9="",publication9="",author9="",puburl9="",pubdescription9="",pubtitle10="",publication10="",author10="",puburl10="",pubdescription10="",publicationdate1="",publicationdate2="",publicationdate3="",publicationdate4="",publicationdate5="",publicationdate6="",publicationdate7="",publicationdate8="",publicationdate9="",publicationdate10="";
    String  email2="",addressline1 = "", addressline2 = "", addressline3 = "", telephone = "",mobile="", mobile2 = "";
    String  post1="",inst1="",post2="",inst2="",post3="",inst3="",post4="",inst4="",post5="",inst5="",post6="",inst6="",post7="",inst7="",post8="",inst8="",post9="",inst9="",post10="",inst10="";
    String fromdate1="",todate1="",fromdate2="",todate2="",fromdate3="",todate3="",fromdate4="",todate4="",fromdate5="",todate5="",fromdate6="",todate6="",fromdate7="",todate7="",fromdate8="",todate8="",fromdate9="",todate9="",fromdate10="",todate10="";
    String dob="",gender="",paddrline1="",paddrline2="",paddrline3="";
    boolean hrinfobox1 = false, hrinfobox2 = false, hrinfobox3 = false;

    String ucode="";


    int percentProfile=0;
    View rootView;

    HashMap<String, Integer> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_profile, container, false);

        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        username =MySharedPreferencesManager.getUsername(getActivity());
        role =MySharedPreferencesManager.getRole(getActivity());

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

        profileprogress=(ProgressBar)rootView.findViewById(R.id.profileprogress);
        mainloadingbar=(ProgressBar)rootView.findViewById(R.id.mainloadingbar);
        updateProgress=(ProgressBar)rootView.findViewById(R.id.updateProgress);

        swipe_refresh_layout=(SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh_layout);
        SwipeRefreshLayout tswipe_refresh_layout=(SwipeRefreshLayout)getActivity().findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setVisibility(View.GONE);

        myprofileimg = (CircleImageView) rootView.findViewById(R.id.myprofileimg);
        myprofilename = (TextView) rootView.findViewById(R.id.myprofilename);
        myprofilrole = (TextView) rootView.findViewById(R.id.myprofilrole);
        myprofiledu = (TextView) rootView.findViewById(R.id.myprofiledu);
        myprofilloc = (TextView) rootView.findViewById(R.id.myprofilloc);
        myprofilemail = (TextView) rootView.findViewById(R.id.myprofilemail);

        myprofilepercenttxt=(TextView)rootView.findViewById(R.id.myprofilepercenttxt);
        editprofiletxt=(TextView)rootView.findViewById(R.id.editprofiletxt);

        myprofilepercenttxt=(TextView)rootView.findViewById(R.id.myprofilepercenttxt);
        editprofiletxt=(TextView)rootView.findViewById(R.id.editprofiletxt);
        eduboxtxt=(TextView)rootView.findViewById(R.id.eduboxtxt);
        accomplishmentsboxtxt=(TextView)rootView.findViewById(R.id.accomplishmentsboxtxt);
        contactboxtxt=(TextView)rootView.findViewById(R.id.contactboxtxt);

        exptab2=(RelativeLayout) rootView.findViewById(R.id.exptab2);
        exp2=(ImageView) rootView.findViewById(R.id.exp2);
        exptab3=(RelativeLayout) rootView.findViewById(R.id.exptab3);
        exp3=(ImageView) rootView.findViewById(R.id.exp3);


        eduboxtxt = (TextView) rootView.findViewById(R.id.eduboxtxt);
        accomplishmentsboxtxt = (TextView) rootView.findViewById(R.id.accomplishmentsboxtxt);
        expboxtxt = (TextView) rootView.findViewById(R.id.expboxtxt);
        contactboxtxt = (TextView) rootView.findViewById(R.id.contactboxtxt);
//
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
        myprofileexpfromto2 = (TextView) rootView.findViewById(R.id.myprofileexpfromto2);

        exp3txt = (TextView) rootView.findViewById(R.id.exp3txt);
        myprofileexp3name = (TextView) rootView.findViewById(R.id.myprofileexp3name);
        myprofileexpfromto3 = (TextView) rootView.findViewById(R.id.myprofileexpfromto3);

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

        TextView trytxt, protxt, freetxt;
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

        protxt.setTypeface(custom_font10);
        trytxt.setTypeface(custom_font11);
        freetxt.setTypeface(custom_font11);

        introedit = (ImageView) rootView.findViewById(R.id.introedit);
        eduedit = (ImageView) rootView.findViewById(R.id.eduedit);
        accomplishmentsedit = (ImageView) rootView.findViewById(R.id.accomplishmentsedit);
        expedit = (ImageView) rootView.findViewById(R.id.expedit);
        contactedit = (ImageView) rootView.findViewById(R.id.contactedit);
        introedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), AdminIntro.class),0);
            }
        });
        eduedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), AdminInstituteDetails.class),0);
            }
        });
        accomplishmentsedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), AdminAccomplishments.class),0);
            }
        });
        expedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), AdminExperiences.class),0);
            }
        });
        contactedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), AdminContactDetails.class),0);
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


                startActivityForResult(new Intent(getActivity(), EditProfileAdmin.class),0);
            }
        });

        View proselectionview = (View) rootView.findViewById(R.id.proselectionview);
        proselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProSplashScreen.class));

            }
        });

        swipe_refresh_layout.setRefreshing(true);
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetAdminData().execute();
                ((AdminActivity)getActivity()).requestProfileImage();
            }
        });

//        sharedpreferences=getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        myprofilrole.setText(role.toUpperCase());

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        try {
            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(username);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);
            myprofilemail.setText(plainusername);
            contactemail.setText(plainusername);
        }catch (Exception e){}

        refreshContent();
        return rootView;
    }

    public void refreshContent()
    {
        new GetAdminData().execute();
        ((AdminActivity)getActivity()).requestProfileImage();
        updateProgress.setVisibility(View.VISIBLE);

    }
    public void showUpdateProgress()
    {
        updateProgress.setVisibility(View.VISIBLE);
    }

    public String GetCountryZipCode(){
        String CountryID="";
        String CountryZipCode="";
        CountryID=getUserCountry(getContext());
        String[] rl=this.getResources().getStringArray(R.array.CountryCodes);
        for(int i=0;i<rl.length;i++){
            String[] g=rl[i].split(",");
            if(g[1].trim().equals(CountryID.trim())){
                CountryZipCode=g[0];
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
            }
            else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toUpperCase(Locale.US);
                }
            }
        }
        catch (Exception e) { }
        return null;
    }
    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Action").setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    startActivity(new Intent(getContext(), ViewProfileImage.class));
                } else if (which == 1) {
                    try {
                        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("digest1", digest1);
                        editor.putString("digest2", digest2);
                        editor.putString("plain", plainusername);
                        editor.commit();
                        dialog.cancel();
                        ((AdminActivity) getActivity()).requestCropImage();
                    }catch (Exception e){Toast.makeText(getActivity()," error" + e.getMessage(),Toast.LENGTH_LONG).show();}


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

    private class GetImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            map = downloadImage(load_student_image);
            percentProfile++;
            return   map;
        }


        @Override
        protected void onPostExecute(Bitmap result) {

            myprofileimg.setImageBitmap(result);
            //show progress
            float R = (1000 - 0) / (16 - 0);
            float y = (percentProfile - 0) * R + 0;
            val2=Math.round(y);

            ObjectAnimator progressAnimator = ObjectAnimator.ofInt(profileprogress, "progress", val1, val2);
            progressAnimator.setDuration(1000);
            progressAnimator.setInterpolator(new LinearInterpolator());
            progressAnimator.start();

        }
        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority("192.168.100.100")
                    .path("AESTest/GetImage")
                    .appendQueryParameter("u", username)
                    .build();

            url=uri.toString();

            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
    //ssss
    private class GetAdminData extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;


//            map = downloadImage(load_student_image);

            try {
                percentProfile = 0;
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));
                json = jParser.makeHttpRequest(load_Admin_data, "GET", params);


                resultofop = json.getString("info");
                if (resultofop.equals("found")) {
                    ucode = json.getString("ucode");
                    phone = json.getString("phoned");


                    String s = json.getString("AdminIntro");
                    Log.d("===JSON===", ""+s);
                    if (s.equals("found")) {
                        found_AdminIntro = 1;
                       String AdminIntroObj = json.getString("AdminIntroObj");
                        AdminIntroModal obj1=(AdminIntroModal)fromString(AdminIntroObj,digest1,digest2);
                        fname =obj1.getFname();
                        mname = obj1.getMname();
                        lname = obj1.getLname();
                        country = obj1.getCountry();
                        state = obj1.getState();
                        city = obj1.getCity();
                        inst = obj1.getInstitute();
                        Log.d("++AdminintroBlock", "+++++++++++++++++++++++");
                        Log.d("++AdminintroBlock", "fname "+fname);
                        Log.d("++AdminintroBlock", "mname "+mname);
                        Log.d("++AdminintroBlock", "lname "+lname);
                        Log.d("++AdminintroBlock", "country "+country);
                        Log.d("++AdminintroBlock", "state "+state);
                        Log.d("++AdminintroBlock", "city "+city);
                        Log.d("++AdminintroBlock", "inst "+inst);
                        if (!phone.equals("null")) {

                            a.setPhone(phone);
                            phone = "+" + GetCountryZipCode() + " " + new String(phone);
                            //setting in populate
//                            contactmobile.setText(phone);
                        }
                        if (!fname.equals("null")) {
                            a.setFname(fname);
                        }
                        if (!mname.equals("null")) {
                            a.setMname(mname);
                        }
                        if (!lname.equals("null")) {

                            a.setLname(lname);
                        }
                        if (!inst.equals("null")) {

                            a.setInstitute(inst);
                        }
                        if (!country.equals("null")) {

                            a.setCountry(country);
                        }
                        if (!state.equals("null")) {

                            a.setState(state);
                        }
                        if (!city.equals("null")) {
                            a.setCity(city);
                        }

                    }
                    s = json.getString("AdminInstitute");
                    if (s.equals("found")) {
                        found_institute = 1;

                        String AdminInstituteobj = json.getString("AdminInstituteobj");
                        AdminInstituteModal obj2=(AdminInstituteModal)fromString(AdminInstituteobj,digest1,digest2);

                        instname =obj2.getInstname();
                        instemail = obj2.getInstemail();
                        instweb = obj2.getInstweb();
                        instphone = obj2.getInstphone();
                        instaltrphone = obj2.getInstaltrphone();
                        universityname = obj2.getUnivname();
                        instreg = obj2.getInstregno();
                        Log.d("--Institute Data--", "+++++++++++++++++ ");
                        Log.d("--Institute Data--", "instname: "+instname);
                        Log.d("--Institute Data--", "instemail: "+instemail);
                        Log.d("--Institute Data--", "instweb: "+instweb);
                        Log.d("--Institute Data--", "instphone: "+instphone);
                        Log.d("--Institute Data--", "instaltrphone: "+instaltrphone);
                        Log.d("--Institute Data--", "universityname: "+universityname);
                        Log.d("--Institute Data--", "instreg: "+instreg);
                        if (!instname.equals("null")) {
                            a.setInstitute(instname);
                        }
                        if (!instemail.equals("null")) {
                            a.setInstemail(instemail);

                        }
                        if (!instweb.equals("null")) {
                            a.setInstweb(instweb);
                        }
                        if (!instphone.equals("null")) {
                            a.setInstphone(instphone);
                        }
                        if (!instaltrphone.equals("null")) {
                            a.setInstaltrphone(instaltrphone);
                        }
                        if (!universityname.equals("null")) {
                            a.setUnivname(universityname);
                        }
                        if (!instreg.equals("null")) {
                            a.setInstregno(instreg);
                        }

                    }

                    s=json.getString("contactdetails");
                    Log.d("--contactdetails Data--", "+++++++++++++++++ ");
                    Log.d("--contactdetails json--", " "+s);
                    if(s.equals("found")) {
                        found_contact_details = 1;
                        String contactdetailsobj = json.getString("contactdetailsobj");
                        AdminContactDetailsModal obj3 = (AdminContactDetailsModal) fromString(contactdetailsobj, digest1, digest2);

                        email2 = obj3.getEmail2();
                        addressline1 = obj3.getAddressline1();
                        addressline2 = obj3.getAddressline2();
                        addressline3 = obj3.getAddressline3();
                        telephone = obj3.getPhone();
                        mobile = obj3.getMobile();
                        mobile2 = obj3.getMobile2();
                        Log.d("--contactdetails --", "email2 " + email2);
                        Log.d("--contactdetails --", "addressline1 " + addressline1);
                        Log.d("--contactdetails --", "addressline2 " + addressline2);

                        Log.d("--contactdetails --", "addressline3 " + addressline3);
                        Log.d("--contactdetails --", "telephone " + telephone);
                        Log.d("--contactdetails --", "mobile " + mobile);
                        Log.d("--contactdetails json--", "mobile2 " + mobile2);

                        if (!mobile.equals("")) {
                            a.setPhone(mobile);
                        }
                        if (!email2.equals("")) {
                            a.setEmail2(email2);
                      }
                        if (!addressline1.equals("")) {
                          a.setAddressline1(addressline1);
                        } if (!addressline2.equals("")) {
                            a.setAddressline2(addressline2);
                        } if (!addressline3.equals("")) {
                            a.setAddressline3(addressline3);
                        }if (!telephone.equals("")) {
                            a.setTelephone(telephone);
                        }if (!mobile2.equals("")) {
                            a.setMobile2(mobile2);
                        }



                    }

//                    s = json.getString("knownlang");
//                    if (s.equals("found")) {
//                        found_lang = 1;
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
//                    s=json.getString("experiences");
//                    if(s.equals("found")) {
//                        found_experiences=1;
////
//                        post1 = json.getString("post1e");
//                        inst1 = json.getString("inst1e");
//                        fromdate1 = json.getString("efromdate1");
//                        todate1 = json.getString("etodate1");
//
//                        post2 = json.getString("post2e");
//                        inst2 = json.getString("inst2e");
//                        fromdate2 = json.getString("efromdate2");
//                        todate2 = json.getString("etodate2");
//
//                        post3 = json.getString("post3e");
//                        inst3 = json.getString("inst3e");
//                        fromdate3 = json.getString("efromdate3");
//                        todate3 = json.getString("etodate3");
//
//                        post4 = json.getString("post4e");
//                        inst4 = json.getString("inst4e");
//                        fromdate4 = json.getString("efromdate4");
//                        todate4 = json.getString("etodate4");
//
//                        post5 = json.getString("post5e");
//                        inst5 = json.getString("inst5e");
//                        fromdate5 = json.getString("efromdate5");
//                        todate5 = json.getString("etodate5");
//
//                        post6 = json.getString("post6e");
//                        inst6 = json.getString("inst6e");
//                        fromdate6 = json.getString("efromdate6");
//                        todate6 = json.getString("etodate6");
//
//                        post7 = json.getString("post7e");
//                        inst7 = json.getString("inst7e");
//                        fromdate7 = json.getString("efromdate7");
//                        todate7 = json.getString("etodate7");
//
//                        post8 = json.getString("post8e");
//                        inst8= json.getString("inst8e");
//                        fromdate8 = json.getString("efromdate8");
//                        todate8 = json.getString("etodate8");
//
//                        post9 = json.getString("post9e");
//                        inst9 = json.getString("inst9e");
//                        fromdate9 = json.getString("efromdate9");
//                        todate9 = json.getString("etodate9");
//
//                        post10 = json.getString("post10e");
//                        inst10 = json.getString("inst10e");
//                        fromdate10 = json.getString("efromdate10");
//                        todate10 = json.getString("etodate10");
//                    }
//

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            swipe_refresh_layout.setRefreshing(false);
            updateProgress.setVisibility(View.GONE);
//
//            myprofileimg.setImageBitmap(result);
            swipe_refresh_layout.setVisibility(View.VISIBLE);
            mainloadingbar.setVisibility(View.GONE);




            try {

                populateData();
                myprofileimg.setImageBitmap(result);
                //show progress
                float R = (1000 - 0) / (16 - 0);
                float y = (percentProfile - 0) * R + 0;
                val1=Math.round(y);

                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(profileprogress, "progress", 0, val1);
                progressAnimator.setDuration(1000);
                progressAnimator.setInterpolator(new LinearInterpolator());
                progressAnimator.start();
                new GetImage().execute();

            } catch (Exception e) {

                Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_LONG).show();

            }

        }

        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
    void populateData() {
        setVisibilityExpbox();

        if (found_AdminIntro == 1) {
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
            if (!inst.equals("")) {
                myprofiledu.setText(inst);
                percentProfile++;
            }
        }
        if (found_institute == 1) {

            if (!instname.equals("")) {
                myprofileclgname.setText(instname);
                myprofiledu.setText(instname);
                percentProfile++;
            }

            if (!instemail.equals("")) {
                instcontactemail.setText(instemail);
//                contactpersonalemail.setText(instemail);
                percentProfile++;
            }

            if (!instweb.equals("")) {
                instwebsite.setText(instweb);
                //  percentProfile++;
            }

            if (!instphone.equals("")) {
                insttelephone.setText(instphone);
                percentProfile++;
            }


        }
        if (found_contact_details == 1) {
            if (!addressline1.equals("")) {
                contactaddr1.setText(addressline1 + " " + addressline2 + " " + addressline3);
                if (!email2.equals("")) {
                    contactprofesionalemail.setText(email2);
                }
                if (!plainusername.equals("")) {
                    contactpersonalemail.setText(plainusername);
                }
                if (!mobile.equals("")) {
                    contactmobile.setText(mobile);
                }
//                contactpersonalemail.setText(plainusername);
                percentProfile++;
            }
        }



//        if (found_lang == 1) {
//            if (!lang1.equals("") && !lang1.equals("- Select Language -"))
//                acc2txttxt.setText(lang1);
//            if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -"))
//                acc2txttxt.setText(lang1 + ", " + lang2);
//            if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -") && !lang3.equals("") && !lang3.equals("- Select Language -"))
//                acc2txttxt.setText(lang1 + ", " + lang2 + ", " + lang3);
//            if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -") && !lang3.equals("") && !lang3.equals("- Select Language -") && !lang4.equals("") && !lang4.equals("- Select Language -"))
//                acc2txttxt.setText(lang1 + ", " + lang2 + ", " + lang3 + " ...");
//            percentProfile++;
//        }
//        if (found_skills == 1) {
//            if (!skill1.equals(""))
//                acc4txttxt.setText(skill1);
//            if (!skill1.equals("") && !skill2.equals(""))
//                acc4txttxt.setText(skill1 + ", " + skill2);
//            if (!skill1.equals("") && !skill2.equals("") && !skill3.equals(""))
//                acc4txttxt.setText(skill1 + ", " + skill2 + ", " + skill3);
//            if (!skill1.equals("") && !skill2.equals("") && !skill3.equals("") && !skill4.equals(""))
//                acc4txttxt.setText(skill1 + ", " + skill2 + ", " + skill3 + " ...");
//            percentProfile++;
//        }
//        if (found_honors == 1) {
//            if (!htitle1.equals(""))
//                acc5txttxt.setText(htitle1);
//            if (!htitle1.equals("") && !htitle2.equals(""))
//                acc5txttxt.setText(htitle1 + ", " + htitle2);
//            if (!htitle1.equals("") && !htitle2.equals("") && !htitle3.equals(""))
//                acc5txttxt.setText(htitle1 + ", " + htitle2 + ", " + htitle3);
//            if (!htitle1.equals("") && !htitle2.equals("") && !htitle3.equals("") && !htitle4.equals(""))
//                acc5txttxt.setText(htitle1 + ", " + htitle2 + ", " + htitle3 + " ...");
//            percentProfile++;
//
//        }
//        if (found_patents == 1) {
//            if (!ptitle1.equals(""))
//                acc6txttxt.setText(ptitle1);
//            if (!ptitle1.equals("") && !ptitle2.equals(""))
//                acc6txttxt.setText(ptitle1 + ", " + ptitle2);
//            if (!ptitle1.equals("") && !ptitle2.equals("") && !ptitle3.equals(""))
//                acc6txttxt.setText(ptitle1 + ", " + ptitle2 + ", " + ptitle3);
//            if (!ptitle1.equals("") && !ptitle2.equals("") && !ptitle3.equals("") && !ptitle4.equals(""))
//                acc6txttxt.setText(ptitle1 + ", " + ptitle2 + ", " + ptitle3 + " ...");
//            percentProfile++;
//
//        }

//            if (found_publications == 1) {
//                if (!pubtitle1.equals(""))
//                    acc7txttxt.setText(pubtitle1);
//                if (!pubtitle1.equals("") && !pubtitle2.equals(""))
//                    acc7txttxt.setText(pubtitle1 + ", " + pubtitle2);
//                if (!pubtitle1.equals("") && !pubtitle2.equals("") && !pubtitle3.equals(""))
//                    acc7txttxt.setText(pubtitle1 + ", " + pubtitle2 + ", " + pubtitle3);
//                if (!pubtitle1.equals("") && !pubtitle2.equals("") && !pubtitle3.equals("") && !pubtitle4.equals(""))
//                    acc7txttxt.setText(pubtitle1 + ", " + pubtitle2 + ", " + pubtitle3 + " ...");
//                percentProfile++;
//
//            }





        populateExperiencesInfo();

        if (hrinfobox1 == true)
            percentProfile++;

        if (hrinfobox2 == true)
            percentProfile++;

        if (hrinfobox3 == true)
            percentProfile++;

    }

//        try {
//
//            if (found_experiences==1) {
//
//
//                int sortTo[] = new int[10];
//                int sortDesc[] = new int[10];
//                int index2 = 9;
//                int inc = 999999, inc2 = 0;
//                int f = 0;
//
//                int disp = 0;
//                int disp2 = 0;
//                int disp3 = 0;
//
//                int mindex = 0;
//                int mindex2 = 0;
//                int mindex3 = 0;
//                int exp_in_years = 0, exp_in_months = 0;
//                Date date = new Date();
//
//                SimpleDateFormat sdfm = new SimpleDateFormat("MMM");
//                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
//
//                String currentMonth = sdfm.format(date);
//                String currentYears = sdfy.format(date);
//                String arr1[] = {fromdate1, fromdate2, fromdate3, fromdate4, fromdate5, fromdate6, fromdate7, fromdate8, fromdate9, fromdate10};
//                String arr2[] = {todate1, todate2, todate3, todate4, todate5, todate6, todate7, todate8, todate9, todate10};
//                String arr3[] = {inst1, inst2, inst3, inst4, inst5, inst6, inst7, inst8, inst9, inst10};
//                String arr4[] = {post1, post2, post3, post4, post5, post6, post7, post8, post9, post10};
//
//                TextView companyInst[] = {myprofileexp1name, myprofileexp2name, myprofileexp3name};
//                TextView postings[] = {exp1txt, exp2txt, exp3txt};
//                TextView dates[] = {myprofileexpfromto, myprofileexpfromto2, myprofileexpfromto3};
//
//                HashMap<String, Integer> map = new HashMap();
//                map.put("Jan", 1);
//                map.put("Feb", 2);
//                map.put("Mar", 3);
//                map.put("Apr", 4);
//                map.put("May", 5);
//                map.put("Jun", 6);
//                map.put("Jul", 7);
//                map.put("Aug", 8);
//                map.put("Sep", 9);
//                map.put("Oct", 10);
//                map.put("Nov", 11);
//                map.put("Dec", 12);
//                HashMap<Integer, Integer> map2 = new HashMap();
//                HashMap<Integer, Integer> map3 = new HashMap();
//
//                for (int i = 0; i <= 9; i++) {
//                    if (arr1[i].equals("") && arr2[i].equals("")) {
//                        sortTo[i] = 111;
//                        map2.put(i, sortTo[i]);
//                        map3.put(sortTo[i], i);
//                    } else if (!arr1[i].equals("") && arr2[i].equals("")) {
//
//                        sortTo[i] = inc;
//                        map2.put(i, sortTo[i]);
//                        map3.put(sortTo[i], i);
//                        inc++;
//                    } else if (!arr2[i].equals("") && !arr1[i].equals("")) {
//                        String tmonth = "", tyears = "";
//                        for (int j = 0; j < arr2[i].length() - 6; j++) {
//                            tmonth += arr2[i].charAt(j);
//                        }
//                        for (int j = 5; j < arr2[i].length(); j++) {
//                            tyears += arr2[i].charAt(j);
//
//                        }
//                        int ty = 0, fy = 0, tm = 0, fm = 0;
//                        ty = Integer.parseInt(tyears);
//                        tm = map.get(tmonth);
//                        int concatinatedint = Integer.parseInt(ty + "" + tm + "" + inc2);
//                        inc2++;
//                        sortTo[i] = concatinatedint;
//
//                        map2.put(i, sortTo[i]);
//                        map3.put(sortTo[i], i);
////                                Toast.makeText(getActivity()," hashmap index :"+map3.get(sortTo[i]),Toast.LENGTH_LONG).show();
////                                Toast.makeText(getActivity(),"array content" +sortTo[i],Toast.LENGTH_SHORT).show();
////                               arr3[i].setText(arr4[i]);
////                                arrInst[i].setText(arrSinst[i]);
////                                Toast.makeText(getActivity(),arr4[i],Toast.LENGTH_LONG).show();
//
//                    }
//                }
//
//                Arrays.sort(sortTo);
//                for (int i = 0; i <= 9; i++) {
//                    if (sortTo[i] == 999999) {
//                        f = 1;
//                        disp = sortTo[i];
//                        mindex = map3.get(disp);
////                        Toast.makeText(getActivity(), "index at which " + sortTo[i] + "stored" + mindex, Toast.LENGTH_LONG).show();
////                        Toast.makeText(getActivity(), "content for " + sortTo[i] + "" + arr4[mindex] + "," + arr3[mindex] + "," + arr1[mindex] + "To" + arr2[mindex], Toast.LENGTH_LONG).show();
//                        postings[0].setText(arr4[mindex]);
//                        companyInst[0].setText(arr3[mindex]);
//                        String fmonth = "", fyears = "";
//                        for (int j = 0; j < arr1[mindex].length() - 6; j++) {
//                            fmonth += arr1[mindex].charAt(j);
//                        }
//                        for (int j = 5; j < arr1[mindex].length(); j++) {
//                            fyears += arr1[mindex].charAt(j);
//                        }
//                        int ty = 0, fy = 0, tm = 0, fm = 0;
//                        fy = Integer.parseInt(fyears);
//                        fm = map.get(fmonth);
//                        tm = map.get(currentMonth);
//                        ty = Integer.parseInt(currentYears);
//
//                        DateTime date2= new DateTime().withDate(fy, fm, 1);
//                        DateTime date1 = new DateTime().withDate(ty, tm, 1);
//
//                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
//                        exp_in_months= exp_in_months2%12;
//                        exp_in_years = exp_in_months2/12;
//                        if (fy == ty) {
//                            exp_in_months = tm - fm;
//                            if (tm - fm == 1) {
//                                dates[0].setText("Currenty Working " + " " + "|" + " " + exp_in_months + "Month");
//                            } else
//
//                                dates[0].setText("Currenty Working " + " " + "|" + " " + exp_in_months + "Months");
//                        } else {
//                            if (exp_in_years == 1 && exp_in_months == 1) {
//                                dates[0].setText("Currenty Working " + " " + "|" + " " + exp_in_years + " " + "Year"  + " "+ exp_in_months+ " "  +  "Month");
//                            } else if (exp_in_years == 1 && exp_in_months > 1) {
//                                dates[0].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Year" + exp_in_months + "Months");
//                            }
//                            if (exp_in_years > 1 && exp_in_months == 1) {
//                                dates[0].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Years" + exp_in_months + "Month");
//                            }
//                            if (exp_in_years > 1 && exp_in_months > 1) {
//                                dates[0].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Years" + exp_in_months + "Months");
//                            }
//                        }
//                    } else if (sortTo[i] == 1000000) {
//                        f = 2;
//                        disp = sortTo[i];
//                        mindex = map3.get(disp);
//                        postings[1].setText(arr4[mindex]);
//                        companyInst[1].setText(arr3[mindex]);
//                        //count exp
//                        String fmonth = "", fyears = "";
//                        for (int j = 0; j < arr1[mindex].length() - 6; j++) {
//                            fmonth += arr1[mindex].charAt(j);
//                        }
//                        for (int j = 5; j < arr1[mindex].length(); j++) {
//                            fyears += arr1[mindex].charAt(j);
//                        }
//                        int ty = 0, fy = 0, tm = 0, fm = 0;
//                        fy = Integer.parseInt(fyears);
//                        fm = map.get(fmonth);
//                        tm = map.get(currentMonth);
//                        ty = Integer.parseInt(currentYears);
//
//                        DateTime date2= new DateTime().withDate(fy, fm, 1);
//                        DateTime date1 = new DateTime().withDate(ty, tm, 1);
//
//                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
//                        exp_in_months= exp_in_months2%12;
//                        exp_in_years = exp_in_months2/12;
//
//                        if (fy == ty) {
//                            exp_in_months = tm - fm;
//                            if (tm - fm == 1) {
//                                dates[1].setText("Currenty Working " + " " + "|" + " " + exp_in_months + "Month");
//                            } else
//
//                                dates[1].setText("Currenty Working " + " " + "|" + " " + exp_in_months + "Months");
//                        } else {
//
//                            if (exp_in_years == 1 && exp_in_months == 1) {
//                                dates[1].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Year" + " " + exp_in_months + "Month");
//                            } else if (exp_in_years == 1 && exp_in_months > 1) {
//                                dates[1].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Year" + " " + exp_in_months + "Months");
//                            }
//                            if (exp_in_years > 1 && exp_in_months == 1) {
//                                dates[1].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Years" + " " + exp_in_months + "Month");
//                            }
//                            if (exp_in_years > 1 && exp_in_months > 1) {
//                                dates[1].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Years" + " " + exp_in_months + "Months");
//                            }
//                        }
//
//                    }
//
//                    else if (sortTo[i] == 1000001) {
//
//                        f = 3;
//
//                        disp = sortTo[i];
//
//                        mindex = map3.get(disp);
////                    Toast.makeText(getActivity(), "content for " + sortTo[i] + "" + arr4[mindex] + "," + arr3[mindex] + "," + arr1[mindex] + "To" + arr2[mindex], Toast.LENGTH_LONG).show();
////                        Toast.makeText(getActivity(), "content for " + sortTo[i - 1] + "" + arr4[mindex2] + "," + arr3[mindex2] + "," + arr1[mindex2] + "To" + arr2[mindex2], Toast.LENGTH_LONG).show();
////                        Toast.makeText(getActivity(), "content for " + sortTo[i - 2] + "" + arr4[mindex3] + "," + arr3[mindex3] + "," + arr1[mindex3] + "To" + arr2[mindex3], Toast.LENGTH_LONG).show();
//////
//                        postings[2].setText(arr4[mindex]);
//                        companyInst[2].setText(arr3[mindex]);
//
//                        //count exp
//                        String fmonth = "", fyears = "";
//                        for (int j = 0; j < arr1[mindex].length() - 6; j++) {
//                            fmonth += arr1[mindex].charAt(j);
//                        }
//                        for (int j = 5; j < arr1[mindex].length(); j++) {
//                            fyears += arr1[mindex].charAt(j);
//                        }
//                        int ty = 0, fy = 0, tm = 0, fm = 0;
//                        fy = Integer.parseInt(fyears);
//                        fm = map.get(fmonth);
//                        tm = map.get(currentMonth);
//                        ty = Integer.parseInt(currentYears);
//
//
//                        DateTime date2= new DateTime().withDate(fy, fm, 1);
//                        DateTime date1 = new DateTime().withDate(ty, tm, 1);
//
//                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
//                        exp_in_months= exp_in_months2%12;
//                        exp_in_years = exp_in_months2/12;
//
//                        if (fy == ty) {
//                            exp_in_months = tm - fm;
//                            if (tm - fm == 1) {
//                                dates[2].setText("Currenty Working " + " " + "|" + " " + exp_in_months + "Month");
//                            } else
//
//                                dates[2].setText("Currenty Working " + " " + "|" + " " + exp_in_months + "Months");
//                        } else {
//
//                            if (exp_in_years == 1 && exp_in_months == 1) {
//                                dates[2].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Year" + " " + exp_in_months + "Month");
//                            } else if (exp_in_years == 1 && exp_in_months > 1) {
//                                dates[2].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Year" + " " + exp_in_months + "Months");
//                            }
//                            if (exp_in_years > 1 && exp_in_months == 1) {
//                                dates[2].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Years" + " " + exp_in_months + "Month");
//                            }
//                            if (exp_in_years > 1 && exp_in_months > 1) {
//                                dates[2].setText("Currenty Working " + " " + "|" + " " + exp_in_years + "Years" + " " + exp_in_months + "Months");
//                            }
//                        }
//                    }
//                }
//
/////check non-currently working
//
//                if(f==3){
//                    //dont populate
//                } else if(f==2) {
//                    //populate1
//                    for (int i = 0; i <= 9; i++) {
//                        if (sortTo[i] == 999999) {
//
//                            if (sortTo[i -1]!=111 ) {
//                                disp = sortTo[i-1];
//                                mindex = map3.get(disp);
//                                postings[2].setText(arr4[mindex]);
//
//                                companyInst[2].setText(arr3[mindex]);
//
//                                String fmonth = "", fyears = "";
//                                for (int j = 0; j < arr1[mindex].length() - 6; j++) {
//                                    fmonth +=  arr1[mindex].charAt(j);
//                                }
//                                for (int j = 5; j < arr1[mindex].length(); j++) {
//                                    fyears +=  arr1[mindex].charAt(j);
//                                }
//                                String tmonth = "", tyears = "";
//
//                                for (int j = 0; j < arr2[mindex].length() - 6; j++) {
//                                    tmonth +=  arr2[mindex].charAt(j);
//                                }
//                                for (int j = 5; j < arr2[mindex].length(); j++) {
//                                    tyears +=  arr2[mindex].charAt(j);
//                                }
//                                int ty = 0, fy = 0, tm = 0, fm = 0;
//                                ty = Integer.parseInt(tyears);
//                                fy = Integer.parseInt(fyears);
//                                fm = map.get(fmonth);
//                                tm = map.get(tmonth);
//
//                                DateTime date2= new DateTime().withDate(fy, fm, 1);
//                                DateTime date1 = new DateTime().withDate(ty, tm, 1);
//
//                                int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
//                                exp_in_months= exp_in_months2%12;
//                                exp_in_years = exp_in_months2/12;
//
//                                if (fy == ty) {
//                                    if (exp_in_months== 1) {
//                                        dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Month" );
//                                    } else
//
//                                        dates[2].setText(""+fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Months" );
//                                } else {
//                                    if (exp_in_years == 1 && exp_in_months == 1) {
//                                        dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year" + " " + exp_in_months + "Month" );
//                                    } else if (exp_in_years == 1 && exp_in_months > 1) {
//                                        dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year"+ " " + exp_in_months + "Months" );
//                                    }
//                                    if (exp_in_years > 1 && exp_in_months == 1) {
//                                        dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Month" );
//                                    }
//                                    if (exp_in_years > 1 && exp_in_months > 1) {
//                                        dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Months" );
////                                            dates[2].setText("somethimg" );
//
//                                    }
//                                }
//
//                            } else {
//
//
//                                exptab3.setVisibility(View.GONE);
//                                exp3.setVisibility(View.GONE);
////                                    postings[2].setText("make this invisible");
////                                    companyInst[2].setText("make this invisible");
////                                    dates[2].setText("make this invisible");
//                            }
//                        }
//
//
//                    }
//                } else if (f==1) {
//                    // populate2
//                    for (int i = 0; i <= 9; i++) {
//
//                        if (sortTo[i] == 999999) {
//
//                            if (sortTo[i -1]!=111 ) {
//                                disp = sortTo[i-1];
//                                mindex = map3.get(disp);
//
//                                postings[1].setText(arr4[mindex]);
//                                companyInst[1].setText(arr3[mindex]);
//
//
//
//
//                                String fmonth = "", fyears = "";
//                                for (int j = 0; j < arr1[mindex].length() - 6; j++) {
//                                    fmonth +=  arr1[mindex].charAt(j);
//                                }
//                                for (int j = 5; j < arr1[mindex].length(); j++) {
//                                    fyears +=  arr1[mindex].charAt(j);
//                                }
//                                String tmonth = "", tyears = "";
//
//                                for (int j = 0; j < arr2[mindex].length() - 6; j++) {
//                                    tmonth +=  arr2[mindex].charAt(j);
//                                }
//                                for (int j = 5; j < arr2[mindex].length(); j++) {
//                                    tyears +=  arr2[mindex].charAt(j);
//                                }
//                                int ty = 0, fy = 0, tm = 0, fm = 0;
//                                ty = Integer.parseInt(tyears);
//                                fy = Integer.parseInt(fyears);
//                                fm = map.get(fmonth);
//                                tm = map.get(tmonth);
//                                exp_in_years = 0; exp_in_months = 0;
//                                DateTime date2= new DateTime().withDate(fy, fm, 1);
//                                DateTime date1 = new DateTime().withDate(ty, tm, 1);
//
//                                int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
//                                exp_in_months= exp_in_months2%12;
//                                exp_in_years = exp_in_months2/12;
//
//                                if (fy == ty) {
//                                    if (tm - fm == 1) {
//                                        dates[1].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Month" );
//                                    } else
//
//                                        dates[1].setText(""+fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Months" );
//                                } else {
//                                    if (exp_in_years == 1 && exp_in_months == 1) {
//                                        dates[1].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year" + " " + exp_in_months + "Month" );
//                                    } else if (exp_in_years == 1 && exp_in_months > 1) {
//                                        dates[1].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year"+ " " + exp_in_months + "Months" );
//                                    }
//                                    if (exp_in_years > 1 && exp_in_months == 1) {
//                                        dates[1].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Month" );
//                                    }
//                                    if (exp_in_years > 1 && exp_in_months > 1) {
//                                        dates[1].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Months" );
//                                    }
//                                }
//
//                            } else{
//
//
//                                exptab2.setVisibility(View.GONE);
//                                exp2.setVisibility(View.GONE);
//                                exptab3.setVisibility(View.GONE);
//                                exp3.setVisibility(View.GONE);
////                                   postings[1].setText("make this invisible");
////                                    companyInst[1].setText("make this invisible");
////                                    dates[1].setText("make this invisible");
////                                    postings[2].setText("make this invisible");
////                                    companyInst[2].setText("make this invisible");
////                                    dates[2].setText("make this invisible");
//                            }
//                            if(sortTo[i -2]!=111 ){
//                                disp = sortTo[i-2];
//                                mindex = map3.get(disp);
//
//                                postings[2].setText(arr4[mindex]);
//                                companyInst[2].setText(arr3[mindex]);
//
//                                String fmonth = "", fyears = "";
//                                for (int j = 0; j < arr1[mindex].length() - 6; j++) {
//                                    fmonth +=  arr1[mindex].charAt(j);
//                                }
//                                for (int j = 5; j < arr1[mindex].length(); j++) {
//                                    fyears +=  arr1[mindex].charAt(j);
//                                }
//                                String tmonth = "", tyears = "";
//
//                                for (int j = 0; j < arr2[mindex].length() - 6; j++) {
//                                    tmonth +=  arr2[mindex].charAt(j);
//                                }
//                                for (int j = 5; j < arr2[mindex].length(); j++) {
//                                    tyears +=  arr2[mindex].charAt(j);
//                                }
//                                int ty = 0, fy = 0, tm = 0, fm = 0;
//                                ty = Integer.parseInt(tyears);
//                                fy = Integer.parseInt(fyears);
//                                fm = map.get(fmonth);
//                                tm = map.get(tmonth);
//                                exp_in_years = 0; exp_in_months = 0;
//
//
//                                DateTime date2= new DateTime().withDate(fy, fm, 1);
//                                DateTime date1 = new DateTime().withDate(ty, tm, 1);
//
//                                int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
//                                exp_in_months= exp_in_months2%12;
//                                exp_in_years = exp_in_months2/12;
//
//                                if (fy == ty) {
//                                    if (tm - fm == 1) {
//                                        dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Month" );
//                                    } else
//
//                                        dates[2].setText(""+fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Months" );
//                                } else {
//                                    if (exp_in_years == 1 && exp_in_months == 1) {
//                                        dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year" + " " + exp_in_months + "Month" );
//                                    } else if (exp_in_years == 1 && exp_in_months > 1) {
//                                        dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year"+ " " + exp_in_months + "Months" );
//                                    }
//                                    if (exp_in_years > 1 && exp_in_months == 1) {
//                                        dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Month" );
//                                    }
//                                    if (exp_in_years > 1 && exp_in_months > 1) {
//                                        dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Months" );
//                                    }
//                                }
//                            } else {
////                                    postings[2].setText("make this invisible");
////                                    companyInst[2].setText("make this invisible");
////                                    dates[2].setText("make this invisible");
//
//                                exptab3.setVisibility(View.GONE);
//                                exp3.setVisibility(View.GONE);
//                            }
//                        }
//                    }
//
//                }else if(f==0){
//                    if(sortTo[9]!=111){
//                        disp = sortTo[9];
//                        mindex = map3.get(disp);
//
//                        postings[0].setText(arr4[mindex]);
//                        companyInst[0].setText(arr3[mindex]);
//
//                        String fmonth = "", fyears = "";
//                        for (int j = 0; j < arr1[mindex].length() - 6; j++) {
//                            fmonth +=  arr1[mindex].charAt(j);
//                        }
//                        for (int j = 5; j < arr1[mindex].length(); j++) {
//                            fyears +=  arr1[mindex].charAt(j);
//                        }
//                        String tmonth = "", tyears = "";
//
//                        for (int j = 0; j < arr2[mindex].length() - 6; j++) {
//                            tmonth +=  arr2[mindex].charAt(j);
//                        }
//                        for (int j = 5; j < arr2[mindex].length(); j++) {
//                            tyears +=  arr2[mindex].charAt(j);
//                        }
//
//                        int ty = 0, fy = 0, tm = 0, fm = 0;
//                        ty = Integer.parseInt(tyears);
//                        fy = Integer.parseInt(fyears);
//                        fm = map.get(fmonth);
//                        tm = map.get(tmonth);
//                        exp_in_years = 0; exp_in_months = 0;
//
//                        DateTime date2= new DateTime().withDate(fy, fm, 1);
//                        DateTime date1 = new DateTime().withDate(ty, tm, 1);
//
//                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
//                        exp_in_months= exp_in_months2%12;
//                        exp_in_years = exp_in_months2/12;
//
//                        if (fy == ty) {
//                            if (exp_in_months == 1) {
//                                dates[0].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Month" );
//                            } else
//
//                                dates[0].setText(""+fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Months" );
//                        } else {
//                            if (exp_in_years == 1 && exp_in_months == 1) {
//                                dates[0].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year" + " " + exp_in_months + "Month" );
//                            } else if (exp_in_years == 1 && exp_in_months > 1) {
//                                dates[0].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year"+ " " + exp_in_months + "Months" );
//                            }
//                            if (exp_in_years > 1 && exp_in_months == 1) {
//                                dates[0].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Month" );
//                            }
//                            if (exp_in_years > 1 && exp_in_months > 1) {
//                                dates[0].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Months" );
//                            }
//                        }
//                    }else {
//                        //dont populate
//                    }
//                    if(sortTo[8]!=111){
//
//                        disp2 = sortTo[8];
//                        mindex2 = map3.get(disp2);
//
//                        postings[1].setText(arr4[mindex2]);
//                        companyInst[1].setText(arr3[mindex2]);
//
//                        String fmonth = "", fyears = "";
//                        for (int j = 0; j < arr1[mindex2].length() - 6; j++) {
//                            fmonth +=  arr1[mindex2].charAt(j);
//                        }
//                        for (int j = 5; j < arr1[mindex2].length(); j++) {
//                            fyears +=  arr1[mindex2].charAt(j);
//                        }
//                        String tmonth = "", tyears = "";
//
//                        for (int j = 0; j < arr2[mindex2].length() - 6; j++) {
//                            tmonth +=  arr2[mindex2].charAt(j);
//                        }
//                        for (int j = 5; j < arr2[mindex2].length(); j++) {
//                            tyears +=  arr2[mindex2].charAt(j);
//                        }
//
//                        int ty = 0, fy = 0, tm = 0, fm = 0;
//                        ty = Integer.parseInt(tyears);
//                        fy = Integer.parseInt(fyears);
//                        fm = map.get(fmonth);
//                        tm = map.get(tmonth);
//                        exp_in_years = 0; exp_in_months = 0;
//
//                        DateTime date2= new DateTime().withDate(fy, fm, 1);
//                        DateTime date1 = new DateTime().withDate(ty, tm, 1);
//
//                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
////                            Toast.makeText(getActivity(), "exp in months" +exp_in_months2, Toast.LENGTH_LONG).show();
//                        exp_in_months= exp_in_months2%12;
//                        exp_in_years = exp_in_months2/12;
////                            Toast.makeText(getActivity(), "exp" +exp_in_years+" years"+exp_in_months+"months", Toast.LENGTH_LONG).show();
//
//                        if (fy == ty) {
//                            if (tm - fm == 1) {
//                                dates[1].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Month" );
//                            } else
//
//                                dates[1].setText(""+fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Months" );
//                        } else {
//                            if (exp_in_years == 1 && exp_in_months == 1) {
//                                dates[1].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year" + " " + exp_in_months + "Month" );
//                            } else if (exp_in_years == 1 && exp_in_months > 1) {
//                                dates[1].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year"+ " " + exp_in_months + "Months" );
//                            }
//                            if (exp_in_years > 1 && exp_in_months == 1) {
//                                dates[1].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Month" );
//                            }
//                            if (exp_in_years > 1 && exp_in_months > 1) {
//                                dates[1].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Months" );
//                            }
//                        }
//
//                    } else {
//
////                            postings[1].setText("make this invisible");
////                            companyInst[1].setText("make this invisible");
////                            dates[1].setText("make this invisible");
////                            postings[2].setText("make this invisible");
////                            companyInst[2].setText("make this invisible");
////                            dates[2].setText("make this invisible");
//                        exptab2.setVisibility(View.GONE);
//                        exp2.setVisibility(View.GONE);
//                        exptab3.setVisibility(View.GONE);
//                        exp3.setVisibility(View.GONE);
//                    }
//
//                    if(sortTo[7]!=111){
//
//                        disp3= sortTo[7];
//                        mindex3 = map3.get(disp3);
//
//                        postings[2].setText(arr4[mindex3]);
//                        companyInst[2].setText(arr3[mindex3]);
//                        String fmonth = "", fyears = "";
//                        for (int j = 0; j < arr1[mindex3].length() - 6; j++) {
//                            fmonth +=  arr1[mindex3].charAt(j);
//                        }
//                        for (int j = 5; j < arr1[mindex3].length(); j++) {
//                            fyears +=  arr1[mindex3].charAt(j);
//                        }
//                        String tmonth = "", tyears = "";
//
//                        for (int j = 0; j < arr2[mindex3].length() - 6; j++) {
//                            tmonth +=  arr2[mindex3].charAt(j);
//                        }
//                        for (int j = 5; j < arr2[mindex3].length(); j++) {
//                            tyears +=  arr2[mindex3].charAt(j);
//                        }
//                        int ty = 0, fy = 0, tm = 0, fm = 0;
//                        ty = Integer.parseInt(tyears);
//                        fy = Integer.parseInt(fyears);
//                        fm = map.get(fmonth);
//                        tm = map.get(tmonth);
//                        exp_in_years = 0; exp_in_months = 0;
//
//                        DateTime date2= new DateTime().withDate(fy, fm, 1);
//                        DateTime date1 = new DateTime().withDate(ty, tm, 1);
//
//                        int exp_in_months2=Months.monthsBetween(date2, date1).getMonths();
////                            Toast.makeText(getActivity(), "exp in months" +exp_in_months2, Toast.LENGTH_LONG).show();
//                        exp_in_months= exp_in_months2%12;
//                        exp_in_years = exp_in_months2/12;
////
//                        if (fy == ty) {
//                            if (tm - fm == 1) {
//                                dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Month" );
//                            } else
//
//                                dates[2].setText(""+fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" +" " +exp_in_months + "Months" );
//                        } else {
//                            if (exp_in_years == 1 && exp_in_months == 1) {
//                                dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year" + " " + exp_in_months + "Month" );
//                            } else if (exp_in_years == 1 && exp_in_months > 1) {
//                                dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Year"+ " " + exp_in_months + "Months" );
//                            }
//                            if (exp_in_years > 1 && exp_in_months == 1) {
//                                dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Month" );
//                            }
//                            if (exp_in_years > 1 && exp_in_months > 1) {
//                                dates[2].setText("" +fmonth +" "+fy +" "+"-"+" "+tmonth +" "+ty +" "+"|" + " " + exp_in_years + "Years"+ " " + exp_in_months + "Months" );
//                            }
//                        }
//
//                    } else {
////                            postings[2].setText("make this invisible");
////                            companyInst[2].setText("make this invisible");
////                            dates[2].setText("make this invisible22");
//
//                        exptab3.setVisibility(View.GONE);
//                        exp3.setVisibility(View.GONE);
//
//                    }
//
//
//                }
//                percentProfile++;
//
//            }
//
//        } catch (Exception e){Toast.makeText(getActivity(),"populatExp error" + e.getMessage(),Toast.LENGTH_LONG).show();}
//    }


    public void setVisibilityExpbox() {
        int exp_count = 0;

        if (!fromdate1.equals(""))
            exp_count++;
        if (!fromdate2.equals(""))
            exp_count++;
        if (!fromdate3.equals(""))
            exp_count++;
        if (!fromdate4.equals(""))
            exp_count++;
        if (!fromdate5.equals(""))
            exp_count++;
        if (!fromdate6.equals(""))
            exp_count++;
        if (!fromdate7.equals(""))
            exp_count++;
        if (!fromdate8.equals(""))
            exp_count++;
        if (!fromdate9.equals(""))
            exp_count++;
        if (!fromdate10.equals(""))
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

    public void populateExperiencesInfo() {
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
        if (todate1.equals("") && !fromdate1.equals("")) {
            fulltodate1 = alltoDatestoInt(fromdate1);
            continuseWork.put(fulltodate1, 1);
        }

        if (todate2.equals("") && !fromdate2.equals("")) {
            fulltodate2 = alltoDatestoInt(fromdate2);
            continuseWork.put(fulltodate2, 2);
        }

        if (todate3.equals("") && !fromdate3.equals("")) {
            fulltodate3 = alltoDatestoInt(fromdate3);
            continuseWork.put(fulltodate3, 3);
        }

        if (todate4.equals("") && !fromdate4.equals("")) {
            fulltodate4 = alltoDatestoInt(fromdate4);
            continuseWork.put(fulltodate4, 4);
        }

        if (todate5.equals("") && !fromdate5.equals("")) {
            fulltodate5 = alltoDatestoInt(fromdate5);
            continuseWork.put(fulltodate5, 5);
        }

        if (todate6.equals("") && !fromdate6.equals("")) {
            fulltodate6 = alltoDatestoInt(todate6);
            continuseWork.put(fulltodate6, 6);
        }

        if (todate7.equals("") && !fromdate7.equals("")) {
            fulltodate7 = alltoDatestoInt(fromdate7);
            continuseWork.put(fulltodate7, 7);
        }

        if (todate8.equals("") && !fromdate8.equals("")) {
            fulltodate8 = alltoDatestoInt(fromdate8);
            continuseWork.put(fulltodate8, 8);
        }

        if (todate9.equals("") && !fromdate9.equals("")) {
            fulltodate9 = alltoDatestoInt(fromdate9);
            continuseWork.put(fulltodate9, 9);
        }

        if (todate10.equals("") && !fromdate10.equals("")) {
            fulltodate10 = alltoDatestoInt(fromdate10);
            continuseWork.put(fulltodate10, 10);
        }

        //for continuous working
        for (Map.Entry<Integer, Integer> entry : continuseWork.entrySet()) {
            int fullDatekey = entry.getKey();
            int expItemIndex = entry.getValue();          // post+index  all set

            if (expItemIndex == 1) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post1);
                    myprofileexp1name.setText(inst1);
                    int[] YM = expYearMonth(fromdate1, todate1);
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

                    exp2txt.setText(post1);
                    myprofileexp2name.setText(inst1);
                    int[] YM = expYearMonth(fromdate1, todate1);
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
                    exp3txt.setText(post1);
                    myprofileexp3name.setText(inst1);
                    int[] YM = expYearMonth(fromdate1, todate1);
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
                    exp1txt.setText(post2);
                    myprofileexp1name.setText(inst2);
                    int[] YM = expYearMonth(fromdate2, todate2);
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
                    exp2txt.setText(post2);
                    myprofileexp2name.setText(inst2);
                    int[] YM = expYearMonth(fromdate2, todate2);
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
                    exp3txt.setText(post3);
                    myprofileexp3name.setText(inst3);
                    int[] YM = expYearMonth(fromdate3, todate3);
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
                    exp1txt.setText(post3);
                    myprofileexp1name.setText(inst3);
                    int[] YM = expYearMonth(fromdate3, todate3);
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
                    exp2txt.setText(post3);
                    myprofileexp2name.setText(inst3);
                    int[] YM = expYearMonth(fromdate3, todate3);
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
                    exp3txt.setText(post3);
                    myprofileexp3name.setText(inst3);
                    int[] YM = expYearMonth(fromdate3, todate3);
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
                    exp1txt.setText(post4);
                    myprofileexp1name.setText(inst4);
                    int[] YM = expYearMonth(fromdate4, todate4);
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
                    exp2txt.setText(post4);
                    myprofileexp2name.setText(inst4);
                    int[] YM = expYearMonth(fromdate4, todate4);
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
                    exp3txt.setText(post4);
                    myprofileexp3name.setText(inst4);
                    int[] YM = expYearMonth(fromdate4, todate4);
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
                    exp1txt.setText(post5);
                    myprofileexp1name.setText(inst5);
                    int[] YM = expYearMonth(fromdate5, todate5);
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
                    exp2txt.setText(post5);
                    myprofileexp2name.setText(inst5);
                    int[] YM = expYearMonth(fromdate5, todate5);
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
                    exp3txt.setText(post5);
                    myprofileexp3name.setText(inst5);
                    int[] YM = expYearMonth(fromdate5, todate5);
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
                    exp1txt.setText(post6);
                    myprofileexp1name.setText(inst6);
                    int[] YM = expYearMonth(fromdate6, todate6);
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
                    exp2txt.setText(post6);
                    myprofileexp2name.setText(inst6);
                    int[] YM = expYearMonth(fromdate6, todate6);
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
                    exp3txt.setText(post6);
                    myprofileexp3name.setText(inst6);
                    int[] YM = expYearMonth(fromdate6, todate6);
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
                    exp1txt.setText(post7);
                    myprofileexp1name.setText(inst7);
                    int[] YM = expYearMonth(fromdate7, todate7);
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
                    exp2txt.setText(post7);
                    myprofileexp2name.setText(inst7);
                    int[] YM = expYearMonth(fromdate7, todate7);
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
                    exp3txt.setText(post7);
                    myprofileexp3name.setText(inst7);
                    int[] YM = expYearMonth(fromdate7, todate7);
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
                    exp1txt.setText(post8);
                    myprofileexp1name.setText(inst8);
                    int[] YM = expYearMonth(fromdate8, todate8);
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
                    exp2txt.setText(post8);
                    myprofileexp2name.setText(inst8);
                    int[] YM = expYearMonth(fromdate8, todate8);
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
                    exp3txt.setText(post8);
                    myprofileexp3name.setText(inst8);
                    int[] YM = expYearMonth(fromdate8, todate8);
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
                    exp1txt.setText(post9);
                    myprofileexp1name.setText(inst9);
                    int[] YM = expYearMonth(fromdate9, todate9);
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
                    exp2txt.setText(post9);
                    myprofileexp2name.setText(inst9);
                    int[] YM = expYearMonth(fromdate9, todate9);
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
                    exp3txt.setText(post9);
                    myprofileexp3name.setText(inst9);
                    int[] YM = expYearMonth(fromdate9, todate9);
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
                    exp1txt.setText(post10);
                    myprofileexp1name.setText(inst10);
                    int[] YM = expYearMonth(fromdate10, todate10);
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
                    exp2txt.setText(post10);
                    myprofileexp2name.setText(inst10);
                    int[] YM = expYearMonth(fromdate10, todate10);
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
                    exp3txt.setText(post10);
                    myprofileexp3name.setText(inst10);
                    int[] YM = expYearMonth(fromdate10, todate10);
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

        if (!todate1.equals("") && !fromdate1.equals("")) {
            fulltodate1 = alltoDatestoInt(todate1);
            workDoneExp.put(fulltodate1, 1);
        }
        if (!todate2.equals("") && !fromdate2.equals("")) {
            fulltodate2 = alltoDatestoInt(todate2);
            workDoneExp.put(fulltodate2, 2);
        }
        if (!todate3.equals("") && !fromdate3.equals("")) {
            fulltodate3 = alltoDatestoInt(todate3);
            workDoneExp.put(fulltodate3, 3);
        }
        if (!todate4.equals("") && !fromdate4.equals("")) {
            fulltodate4 = alltoDatestoInt(todate4);
            workDoneExp.put(fulltodate4, 4);
        }
        if (!todate5.equals("") && !fromdate5.equals("")) {
            fulltodate5 = alltoDatestoInt(todate5);
            workDoneExp.put(fulltodate5, 5);
        }
        if (!todate6.equals("") && !fromdate6.equals("")) {
            fulltodate6 = alltoDatestoInt(todate6);
            workDoneExp.put(fulltodate6, 6);
        }
        if (!todate7.equals("") && !fromdate7.equals("")) {
            fulltodate7 = alltoDatestoInt(todate7);
            workDoneExp.put(fulltodate7, 7);
        }
        if (!todate8.equals("") && !fromdate8.equals("")) {
            fulltodate8 = alltoDatestoInt(todate8);
            workDoneExp.put(fulltodate8, 8);
        }
        if (!todate9.equals("") && !fromdate9.equals("")) {
            fulltodate9 = alltoDatestoInt(todate9);
            workDoneExp.put(fulltodate9, 9);
        }
        if (!todate10.equals("") && !fromdate10.equals("")) {
            fulltodate10 = alltoDatestoInt(todate10);
            workDoneExp.put(fulltodate10, 10);
        }

        for (Map.Entry<Integer, Integer> entry : workDoneExp.entrySet()) {
            int expItemIndex = entry.getValue();          // post+index  all set

            if (expItemIndex == 1) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post1);
                    myprofileexp1name.setText(inst1);
                    int[] YM = expYearMonth(fromdate1, todate1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdate1 + " - " + todate1 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdate1 + " - " + todate1 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdate1 + " - " + todate1 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(post1);
                    myprofileexp2name.setText(inst1);
                    int[] YM = expYearMonth(fromdate1, todate1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdate1 + " - " + todate1 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdate1 + " - " + todate1 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdate1 + " - " + todate1 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(post1);
                    myprofileexp3name.setText(inst1);
                    int[] YM = expYearMonth(fromdate1, todate1);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdate1 + " - " + todate1 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdate1 + " - " + todate1 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdate1 + " - " + todate1 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 2) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post2);
                    myprofileexp1name.setText(inst2);
                    int[] YM = expYearMonth(fromdate2, todate2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdate2 + " - " + todate2 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdate2 + " - " + todate2 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdate2 + " - " + todate2 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;

                } else if (hrinfobox2 == false) {
                    exp2txt.setText(post2);
                    myprofileexp2name.setText(inst2);
                    int[] YM = expYearMonth(fromdate2, todate2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdate2 + " - " + todate2 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdate2 + " - " + todate2 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdate2 + " - " + todate2 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(post2);
                    myprofileexp3name.setText(inst2);
                    int[] YM = expYearMonth(fromdate2, todate2);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdate2 + " - " + todate2 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdate2 + " - " + todate2 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdate2 + " - " + todate2 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }

            }
            if (expItemIndex == 3) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post3);
                    myprofileexp1name.setText(inst3);
                    int[] YM = expYearMonth(fromdate3, todate3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdate3 + " - " + todate3 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdate3 + " - " + todate3 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdate3 + " - " + todate3 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(post3);
                    myprofileexp2name.setText(inst3);
                    int[] YM = expYearMonth(fromdate3, todate3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdate3 + " - " + todate3 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdate3 + " - " + todate3 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdate3 + " - " + todate3 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(post3);
                    myprofileexp3name.setText(inst3);
                    int[] YM = expYearMonth(fromdate3, todate3);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdate3 + " - " + todate3 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdate3 + " - " + todate3 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdate3 + " - " + todate3 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 4) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post4);
                    myprofileexp1name.setText(inst4);
                    int[] YM = expYearMonth(fromdate4, todate4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdate4 + " - " + todate4 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdate4 + " - " + todate4 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdate4 + " - " + todate4 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(post4);
                    myprofileexp2name.setText(inst4);
                    int[] YM = expYearMonth(fromdate4, todate4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdate4 + " - " + todate4 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdate4 + " - " + todate4 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdate4 + " - " + todate4 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(post4);
                    myprofileexp3name.setText(inst4);
                    int[] YM = expYearMonth(fromdate4, todate4);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdate4 + " - " + todate4 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdate4 + " - " + todate4 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdate4 + " - " + todate4 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 5) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post5);
                    myprofileexp1name.setText(inst5);
                    int[] YM = expYearMonth(fromdate5, todate5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdate5 + " - " + todate5 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdate5 + " - " + todate5 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdate5 + " - " + todate5 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(post5);
                    myprofileexp2name.setText(inst5);
                    int[] YM = expYearMonth(fromdate5, todate5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdate5 + " - " + todate5 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdate5 + " - " + todate5 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdate5 + " - " + todate5 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(post5);
                    myprofileexp3name.setText(inst5);
                    int[] YM = expYearMonth(fromdate5, todate5);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdate5 + " - " + todate5 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdate5 + " - " + todate5 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdate5 + " - " + todate5 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 6) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post6);
                    myprofileexp1name.setText(inst6);
                    int[] YM = expYearMonth(fromdate6, todate6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdate6 + " - " + todate6 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdate6 + " - " + todate6 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdate6 + " - " + todate6 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(post6);
                    myprofileexp2name.setText(inst6);
                    int[] YM = expYearMonth(fromdate6, todate6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdate6 + " - " + todate6 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdate6 + " - " + todate6 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdate6 + " - " + todate6 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(post6);
                    myprofileexp3name.setText(inst6);
                    int[] YM = expYearMonth(fromdate6, todate6);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdate6 + " - " + todate6 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdate6 + " - " + todate6 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdate6 + " - " + todate6 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 7) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post7);
                    myprofileexp1name.setText(inst7);
                    int[] YM = expYearMonth(fromdate7, todate7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdate7 + " - " + todate7 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdate7 + " - " + todate7 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdate7 + " - " + todate7 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(post7);
                    myprofileexp2name.setText(inst7);
                    int[] YM = expYearMonth(fromdate7, todate7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdate7 + " - " + todate7 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdate7 + " - " + todate7 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdate7 + " - " + todate7 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(post7);
                    myprofileexp3name.setText(inst7);
                    int[] YM = expYearMonth(fromdate7, todate7);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdate7 + " - " + todate7 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdate7 + " - " + todate7 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdate7 + " - " + todate7 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 8) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post8);
                    myprofileexp1name.setText(inst8);
                    int[] YM = expYearMonth(fromdate8, todate8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdate8 + " - " + todate8 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdate8 + " - " + todate8 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdate8 + " - " + todate8 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(post8);
                    myprofileexp2name.setText(inst8);
                    int[] YM = expYearMonth(fromdate8, todate8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdate8 + " - " + todate8 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdate8 + " - " + todate8 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdate8 + " - " + todate8 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(post8);
                    myprofileexp3name.setText(inst8);
                    int[] YM = expYearMonth(fromdate8, todate8);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdate8 + " - " + todate8 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdate8 + " - " + todate8 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdate8 + " - " + todate8 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 9) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post9);
                    myprofileexp1name.setText(inst9);
                    int[] YM = expYearMonth(fromdate9, todate9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdate9 + " - " + todate9 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdate9 + " - " + todate9 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdate9 + " - " + todate9 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(post9);
                    myprofileexp2name.setText(inst9);
                    int[] YM = expYearMonth(fromdate9, todate9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdate9 + " - " + todate9 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdate9 + " - " + todate9 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdate9 + " - " + todate9 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(post9);
                    myprofileexp3name.setText(inst9);
                    int[] YM = expYearMonth(fromdate9, todate9);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdate9 + " - " + todate9 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdate9 + " - " + todate9 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdate9 + " - " + todate9 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
            if (expItemIndex == 10) {
                if (hrinfobox1 == false) {
                    exp1txt.setText(post10);
                    myprofileexp1name.setText(inst10);
                    int[] YM = expYearMonth(fromdate10, todate10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto.setText(fromdate10 + " - " + todate10 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto.setText(fromdate10 + " - " + todate10 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto.setText(fromdate10 + " - " + todate10 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox1 = true;
                } else if (hrinfobox2 == false) {
                    exp2txt.setText(post10);
                    myprofileexp2name.setText(inst10);
                    int[] YM = expYearMonth(fromdate10, todate10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto2.setText(fromdate10 + " - " + todate10 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto2.setText(fromdate10 + " - " + todate10 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto2.setText(fromdate10 + " - " + todate10 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox2 = true;
                } else if (hrinfobox3 == false) {
                    exp3txt.setText(post10);
                    myprofileexp3name.setText(inst10);
                    int[] YM = expYearMonth(fromdate10, todate10);
                    if (YM[0] == 1)
                        YEAR = "year";
                    else
                        YEAR = "years";
                    if (YM[1] == 1)
                        MONTH = "month";
                    else
                        MONTH = "months";
                    if (YM[1] == 0)
                        myprofileexpfromto3.setText(fromdate10 + " - " + todate10 + " | " + YM[0] + " " + YEAR);
                    else if (YM[0] == 0)
                        myprofileexpfromto3.setText(fromdate10 + " - " + todate10 + " | " + YM[1] + " " + MONTH);
                    else
                        myprofileexpfromto3.setText(fromdate10 + " - " + todate10 + " | " + YM[0] + " " + YEAR + " - " + YM[1] + " " + MONTH);
                    hrinfobox3 = true;
                    return;
                }
            }
        }
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        getActivity();
//        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
//            Toast.makeText(getActivity(), "intro edited", Toast.LENGTH_SHORT).show();
//        }
//        Toast.makeText(getActivity(), "request code; "+requestCode + " \n result code:"+resultCode, Toast.LENGTH_SHORT).show();
//
//    }

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
    class DeleteProfile extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(remove_profile, "GET", params);

            try {

                resultofop = json.getString("info");

            } catch (Exception ex) {

            }

            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {

            if(resultofop.equals("success"))
            {
                Toast.makeText(getActivity(),"Profile Picture removed..!",Toast.LENGTH_LONG).show();
                refreshContent();
                ((AdminActivity)getActivity()).requestProfileImage();
            }
            else if(resultofop.equals("fail"))
                Toast.makeText(getActivity(),"Failed..!",Toast.LENGTH_LONG).show();

            else if(resultofop.equals("notfound"))
                Toast.makeText(getActivity(),"No Profile Picture..!",Toast.LENGTH_LONG).show();

        }
    }

}