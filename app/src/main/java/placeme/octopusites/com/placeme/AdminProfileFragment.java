package placeme.octopusites.com.placeme;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.StringSignature;

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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.modal.AdminContactDetailsModal;
import placeme.octopusites.com.placeme.modal.AdminInstituteModal;
import placeme.octopusites.com.placeme.modal.AdminIntroModal;
import placeme.octopusites.com.placeme.modal.AdminPersonal;
import placeme.octopusites.com.placeme.modal.Experiences;
import placeme.octopusites.com.placeme.modal.Honors;
import placeme.octopusites.com.placeme.modal.KnownLangs;
import placeme.octopusites.com.placeme.modal.Patents;
import placeme.octopusites.com.placeme.modal.Publications;
import placeme.octopusites.com.placeme.modal.Skills;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;
import static placeme.octopusites.com.placeme.AdminActivity.IsSubadmin;

public class AdminProfileFragment extends Fragment {

    final static CharSequence[] items = {"Update Profile Picture", "Delete Profile Picture"};
    CircleImageView myprofileimg;
    ImageButton iv_camera;
    TextView myprofilename, myprofilrole, myprofiledu, myprofilloc, myprofilemail, myprofilepercenttxt, noexptxt;
    RelativeLayout editprofilerl;
    RelativeLayout box1, exptab1, exptab2, exptab3, noexptab;
    View box2;
    String experiencesataobject = "";
    int val1 = 0, val2 = 0;
    TextView instcodetxt,editprofiletxt, eduboxtxt, expboxtxt, accomplishmentsboxtxt, instemailtxt, contactboxtxt, instcontactemail, acc4txttxt, instwebtxt, extraexpcount;
    TextView myprofilecource, instteletxt, insttelephone, instwebsite, caddinst, instcontactaddr, acc2txt, acc2txttxt, acc4txt, acc5txt, acc6txt, acc7txt, acc5txttxt, acc6txttxt, acc7txttxt;
    TextView exp1txt, myprofileexpfromto, myprofileexp1name, exp2txt, myprofileexpfromto2, myprofileexp2name, exp3txt, myprofileexpfromto3, myprofileexp3name, emailtxt, myprofileclgname, nametxt, mobiletxt, contactpersonalemail, contactaddr, contactprofesionalemail, myprofiledomain1, myprofileduration1, myprofiledomain2, myprofileduration2, myprofiledomain3, myprofileduration3, careerobjtxttxt, strengthstxt, weaknessestxt, locationpreferences, contactaddr1, contactmobile, contactemail, myprofilepreview;
    ImageView introedit, eduedit, expedit, accomplishmentsedit, careeredit, contactedit;
    ImageView diamond1, diamond2, diamond3;
    ImageView exp1, exp2, exp3;
    AdminData a = new AdminData();
    StudentData studentData = new StudentData();
    ProgressBar updateProgress;
    SwipeRefreshLayout swipe_refresh_layout;
    ProgressBar profileprogress;

    JSONParser jParser = new JSONParser();
    JSONObject json;
    String digest1, digest2;
    String username = "", resultofop;
    String ParentAdmin;
    int found_lang = 0, found_AdminIntro = 0, found_institute = 0, found_box2 = 0, found_skills = 0, found_honors = 0, found_patents = 0, found_publications = 0;
    int found_contact_details = 0, found_personal = 0, found_exp = 0, found_admin_personal = 0;
    int intfromdat1 = 0, inttodate1 = 0, intfromdate2 = 0, inttodate2 = 0;

    String fname = "", mname = "", lname = "", country = "", state = "", city = "", role = "", plainusername = "", phone = "", inst = "";
    String instname = "", instemail = "", instweb = "", instphone = "", instaltrphone = "", universityname = "", instreg = "", instcaddrline1 = "", instcaddrline2 = "", instcaddrline3 = "";
    String lang1 = "", proficiency1 = "", lang2 = "", proficiency2 = "", lang3 = "", proficiency3 = "", lang4 = "", proficiency4 = "", lang5 = "", proficiency5 = "", lang6 = "", proficiency6 = "", lang7 = "", proficiency7 = "", lang8 = "", proficiency8 = "", lang9 = "", proficiency9 = "", lang10 = "", proficiency10 = "";
    String skill1 = "", skill2 = "", skill3 = "", skill4 = "", skill5 = "", skill6 = "", skill7 = "", skill8 = "", skill9 = "", skill10 = "", skill11 = "", skill12 = "", skill13 = "", skill14 = "", skill15 = "", skill16 = "", skill17 = "", skill18 = "", skill19 = "", skill20 = "", sproficiency1 = "", sproficiency2 = "", sproficiency3 = "", sproficiency4 = "", sproficiency5 = "", sproficiency6 = "", sproficiency7 = "", sproficiency8 = "", sproficiency9 = "", sproficiency10 = "", sproficiency11 = "", sproficiency12 = "", sproficiency13 = "", sproficiency14 = "", sproficiency15 = "", sproficiency16 = "", sproficiency17 = "", sproficiency18 = "", sproficiency19 = "", sproficiency20 = "";
    String htitle1 = "", hissuer1 = "", hdescription1 = "", htitle2 = "", hissuer2 = "", hdescription2 = "", htitle3 = "", hissuer3 = "", hdescription3 = "", htitle4 = "", hissuer4 = "", hdescription4 = "", htitle5 = "", hissuer5 = "", hdescription5 = "", htitle6 = "", hissuer6 = "", hdescription6 = "", htitle7 = "", hissuer7 = "", hdescription7 = "", htitle8 = "", hissuer8 = "", hdescription8 = "", htitle9 = "", hissuer9 = "", hdescription9 = "", htitle10 = "", hissuer10 = "", hdescription10 = "", yearofhonor1 = "", yearofhonor2 = "", yearofhonor3 = "", yearofhonor4 = "", yearofhonor5 = "", yearofhonor6 = "", yearofhonor7 = "", yearofhonor8 = "", yearofhonor9 = "", yearofhonor10 = "";
    String ptitle1 = "", pappno1 = "", pinventor1 = "", pissue1 = "", pfiling1 = "", purl1 = "", pdescription1 = "", ptitle2 = "", pappno2 = "", pinventor2 = "", pissue2 = "", pfiling2 = "", purl2 = "", pdescription2 = "", ptitle3 = "", pappno3 = "", pinventor3 = "", pissue3 = "", pfiling3 = "", purl3 = "", pdescription3 = "", ptitle4 = "", pappno4 = "", pinventor4 = "", pissue4 = "", pfiling4 = "", purl4 = "", pdescription4 = "", ptitle5 = "", pappno5 = "", pinventor5 = "", pissue5 = "", pfiling5 = "", purl5 = "", pdescription5 = "", ptitle6 = "", pappno6 = "", pinventor6 = "", pissue6 = "", pfiling6 = "", purl6 = "", pdescription6 = "", ptitle7 = "", pappno7 = "", pinventor7 = "", pissue7 = "", pfiling7 = "", purl7 = "", pdescription7 = "", ptitle8 = "", pappno8 = "", pinventor8 = "", pissue8 = "", pfiling8 = "", purl8 = "", pdescription8 = "", ptitle9 = "", pappno9 = "", pinventor9 = "", pissue9 = "", pfiling9 = "", purl9 = "", pdescription9 = "", ptitle10 = "", pappno10 = "", pinventor10 = "", pissue10 = "", pfiling10 = "", purl10 = "", pdescription10 = "", pselectedcountry1 = "", pselectedcountry2 = "", pselectedcountry3 = "", pselectedcountry4 = "", pselectedcountry5 = "", pselectedcountry6 = "", pselectedcountry7 = "", pselectedcountry8 = "", pselectedcountry9 = "", pselectedcountry10 = "", issuedorpending1 = "", issuedorpending2 = "", issuedorpending3 = "", issuedorpending4 = "", issuedorpending5 = "", issuedorpending6 = "", issuedorpending7 = "", issuedorpending8 = "", issuedorpending9 = "", issuedorpending10 = "";
    String pubtitle1 = "", publication1 = "", author1 = "", puburl1 = "", pubdescription1 = "", pubtitle2 = "", publication2 = "", author2 = "", puburl2 = "", pubdescription2 = "", pubtitle3 = "", publication3 = "", author3 = "", puburl3 = "", pubdescription3 = "", pubtitle4 = "", publication4 = "", author4 = "", puburl4 = "", pubdescription4 = "", pubtitle5 = "", publication5 = "", author5 = "", puburl5 = "", pubdescription5 = "", pubtitle6 = "", publication6 = "", author6 = "", puburl6 = "", pubdescription6 = "", pubtitle7 = "", publication7 = "", author7 = "", puburl7 = "", pubdescription7 = "", pubtitle8 = "", publication8 = "", author8 = "", puburl8 = "", pubdescription8 = "", pubtitle9 = "", publication9 = "", author9 = "", puburl9 = "", pubdescription9 = "", pubtitle10 = "", publication10 = "", author10 = "", puburl10 = "", pubdescription10 = "", publicationdate1 = "", publicationdate2 = "", publicationdate3 = "", publicationdate4 = "", publicationdate5 = "", publicationdate6 = "", publicationdate7 = "", publicationdate8 = "", publicationdate9 = "", publicationdate10 = "";
    String email2 = "", addressline1 = "", addressline2 = "", addressline3 = "", telephone = "", mobile = "", mobile2 = "";
    String post1 = "", inst1 = "", post2 = "", inst2 = "", post3 = "", inst3 = "", post4 = "", inst4 = "", post5 = "", inst5 = "", post6 = "", inst6 = "", post7 = "", inst7 = "", post8 = "", inst8 = "", post9 = "", inst9 = "", post10 = "", inst10 = "";
    String fromdate1 = "", todate1 = "", fromdate2 = "", todate2 = "", fromdate3 = "", todate3 = "", fromdate4 = "", todate4 = "", fromdate5 = "", todate5 = "", fromdate6 = "", todate6 = "", fromdate7 = "", todate7 = "", fromdate8 = "", todate8 = "", fromdate9 = "", todate9 = "", fromdate10 = "", todate10 = "";
    String dob = "", gender = "", paddrline1 = "", paddrline2 = "", paddrline3 = "", personaldataobject = "", ucode = "";
    boolean hrinfobox1 = false, hrinfobox2 = false, hrinfobox3 = false;
    int lang_count = 0, skills_count = 0, patent_count = 0, public_count = 0, honor_count = 0, exps_count = 0;

    int percentProfile = 0;
    View rootView;
    RelativeLayout knownLanguagesRelativeLayout, SkillsRelativeLayout, HonorsRelativeLayout, PatentsRelativeLayout, PublicationsRelativeLayout;
    HashMap<String, Integer> hashMap;


    public void bottomupbox2(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.bottom_up_box2);
        view.startAnimation(animation1);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                profileprogress.setVisibility(View.VISIBLE);
                View box2section = rootView.findViewById(R.id.box2section);
                box2section.setVisibility(View.VISIBLE);
                editprofiletxt.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_admin_profile, container, false);

        box1 = (RelativeLayout) rootView.findViewById(R.id.box1);
        box2 = rootView.findViewById(R.id.box2);

        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        username = MySharedPreferencesManager.getUsername(getActivity());

//        ParentAdmin = MySharedPreferencesManager.getParent(getActivity());

        role = MySharedPreferencesManager.getRole(getActivity());

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

        profileprogress = (ProgressBar) rootView.findViewById(R.id.profileprogress);
        updateProgress = (ProgressBar) rootView.findViewById(R.id.updateProgress);

        swipe_refresh_layout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        SwipeRefreshLayout tswipe_refresh_layout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setVisibility(View.GONE);

        myprofileimg = (CircleImageView) rootView.findViewById(R.id.myprofileimg);
        iv_camera = (ImageButton) rootView.findViewById(R.id.iv_camera);
        myprofilename = (TextView) rootView.findViewById(R.id.myprofilename);
        myprofilrole = (TextView) rootView.findViewById(R.id.myprofilrole);
        myprofiledu = (TextView) rootView.findViewById(R.id.myprofiledu);
        myprofilloc = (TextView) rootView.findViewById(R.id.myprofilloc);
        myprofilemail = (TextView) rootView.findViewById(R.id.myprofilemail);
        instcodetxt=(TextView) rootView.findViewById(R.id.instcodetxt);
        extraexpcount = (TextView) rootView.findViewById(R.id.extraexpcount);

        ImageView insteditpencil = (ImageView) rootView.findViewById(R.id.insteditpencil);
        myprofilepercenttxt = (TextView) rootView.findViewById(R.id.myprofilepercenttxt);
        editprofiletxt = (TextView) rootView.findViewById(R.id.editprofiletxt);

        myprofilepercenttxt = (TextView) rootView.findViewById(R.id.myprofilepercenttxt);
        editprofiletxt = (TextView) rootView.findViewById(R.id.editprofiletxt);
        eduboxtxt = (TextView) rootView.findViewById(R.id.eduboxtxt);
        accomplishmentsboxtxt = (TextView) rootView.findViewById(R.id.accomplishmentsboxtxt);
        contactboxtxt = (TextView) rootView.findViewById(R.id.contactboxtxt);


        noexptab = (RelativeLayout) rootView.findViewById(R.id.noexptab);

        exptab1 = (RelativeLayout) rootView.findViewById(R.id.exptab1);
        exp1 = (ImageView) rootView.findViewById(R.id.exp1);

        exptab2 = (RelativeLayout) rootView.findViewById(R.id.exptab2);
        exp2 = (ImageView) rootView.findViewById(R.id.exp2);

        exptab3 = (RelativeLayout) rootView.findViewById(R.id.exptab3);
        exp3 = (ImageView) rootView.findViewById(R.id.exp3);

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

        caddinst = (TextView) rootView.findViewById(R.id.caddinst);
        instcontactaddr = (TextView) rootView.findViewById(R.id.instcontactaddr);

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

        noexptxt = (TextView) rootView.findViewById(R.id.noexptxt);

        noexptxt.setTypeface(Z.getBold(getActivity()));

        myprofilepreview.setTypeface(Z.getBold(getActivity()));
        myprofilename.setTypeface(Z.getBold(getActivity()));
        myprofilrole.setTypeface(Z.getBold(getActivity()));
        instcodetxt.setTypeface(Z.getLight(getActivity()));
        instcodetxt.setText("Institute Code");
        myprofiledu.setTypeface(Z.getBold(getActivity()));
        myprofilloc.setTypeface(Z.getLight(getActivity()));
        myprofilemail.setTypeface(Z.getLight(getActivity()));
        myprofilepercenttxt.setTypeface(Z.getItalic(getActivity()));
        editprofiletxt.setTypeface(Z.getBold(getActivity()));
        eduboxtxt.setTypeface(Z.getBold(getActivity()));
        expboxtxt.setTypeface(Z.getBold(getActivity()));
        accomplishmentsboxtxt.setTypeface(Z.getBold(getActivity()));
        contactboxtxt.setTypeface(Z.getBold(getActivity()));

        extraexpcount.setTypeface(Z.getLight(getActivity()));
        myprofilecource.setTypeface(Z.getLight(getActivity()));
        instemailtxt.setTypeface(Z.getLight(getActivity()));
        instwebtxt.setTypeface(Z.getLight(getActivity()));
        instteletxt.setTypeface(Z.getLight(getActivity()));
        caddinst.setTypeface(Z.getLight(getActivity()));

        acc2txt.setTypeface(Z.getLight(getActivity()));
        acc4txt.setTypeface(Z.getLight(getActivity()));
        acc5txt.setTypeface(Z.getLight(getActivity()));
        acc6txt.setTypeface(Z.getLight(getActivity()));
        acc7txt.setTypeface(Z.getLight(getActivity()));
//
        instcontactaddr.setTypeface(Z.getBold(getActivity()));
        myprofileclgname.setTypeface(Z.getBold(getActivity()));
        instcontactemail.setTypeface(Z.getBold(getActivity()));
        instwebsite.setTypeface(Z.getBold(getActivity()));
        insttelephone.setTypeface(Z.getBold(getActivity()));
        acc2txttxt.setTypeface(Z.getBold(getActivity()));
        acc4txttxt.setTypeface(Z.getBold(getActivity()));
        acc5txttxt.setTypeface(Z.getBold(getActivity()));
        acc6txttxt.setTypeface(Z.getBold(getActivity()));
        acc7txttxt.setTypeface(Z.getBold(getActivity()));

        exp1txt.setTypeface(Z.getBold(getActivity()));
        myprofileexp1name.setTypeface(Z.getLight(getActivity()));
        myprofileexpfromto.setTypeface(Z.getLight(getActivity()));

        exp2txt.setTypeface(Z.getBold(getActivity()));
        myprofileexp2name.setTypeface(Z.getLight(getActivity()));
        myprofileexpfromto2.setTypeface(Z.getLight(getActivity()));

        exp3txt.setTypeface(Z.getBold(getActivity()));
        myprofileexp3name.setTypeface(Z.getLight(getActivity()));
        myprofileexpfromto3.setTypeface(Z.getLight(getActivity()));

        nametxt.setTypeface(Z.getBold(getActivity()));
        emailtxt.setTypeface(Z.getLight(getActivity()));
        mobiletxt.setTypeface(Z.getLight(getActivity()));
        contactaddr.setTypeface(Z.getLight(getActivity()));
        contactprofesionalemail.setTypeface(Z.getBold(getActivity()));
        contactpersonalemail.setTypeface(Z.getBold(getActivity()));
        contactmobile.setTypeface(Z.getBold(getActivity()));

        introedit = (ImageView) rootView.findViewById(R.id.introedit);
        eduedit = (ImageView) rootView.findViewById(R.id.eduedit);
        accomplishmentsedit = (ImageView) rootView.findViewById(R.id.accomplishmentsedit);
        expedit = (ImageView) rootView.findViewById(R.id.expedit);
        contactedit = (ImageView) rootView.findViewById(R.id.contactedit);

        knownLanguagesRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab2);
        SkillsRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab4);
        HonorsRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab5);
        PatentsRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab6);
        PublicationsRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.acctab7);


        knownLanguagesRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileKnownLang.class).putExtra("username", username), 0);
            }
        });

        SkillsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileSkills.class).putExtra("username", username), 0);
            }
        });

        HonorsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfileAchievements.class).putExtra("username", username), 0);
            }
        });


        PatentsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfilePatents.class).putExtra("username", username), 0);
            }
        });


        PublicationsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), MyProfilePublications.class).putExtra("username", username), 0);
            }
        });


        if (!ShouldAnimateProfile.shouldAnimate) {
            Z.bottomupbox1(getActivity(), box1);
            bottomupbox2(getActivity(), box2);
            Z.bottomupbox4(getActivity(), eduboxtxt);
            Z.fade(getActivity(), myprofileimg);
            Z.fade(getActivity(), iv_camera);
            Z.bottomupbox3(getActivity(), eduboxtxt);
            Z.bottomupbox3(getActivity(), insteditpencil);
            Z.fadeandmovedown(getActivity(), myprofilepreview);
            ShouldAnimateProfile.shouldAnimate = true;
        } else {
            profileprogress.setVisibility(View.VISIBLE);
            View box2section = rootView.findViewById(R.id.box2section);
            box2section.setVisibility(View.VISIBLE);
            editprofiletxt.setVisibility(View.VISIBLE);
        }

        introedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), AdminIntro.class), 0);
            }
        });


        eduedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivityForResult(new Intent(getActivity(), AdminInstituteDetails.class), 0);

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
                startActivityForResult(new Intent(getActivity(), AdminExperiences.class), 0);
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
                startActivity(new Intent(getContext(), ViewProfileImage.class));
            }
        });
        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        editprofilerl = (RelativeLayout) rootView.findViewById(R.id.editprofilerl);
        editprofilerl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivityForResult(new Intent(getActivity(), EditProfileAdmin.class), 0);
            }
        });

        swipe_refresh_layout.setRefreshing(true);
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetAdminData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                ((AdminActivity) getActivity()).requestProfileImage();
            }
        });

        myprofilrole.setText("TPO");

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
            Log.d("cricket", "Match 1 lost : " + e.getMessage());
            e.printStackTrace();

        }

        refreshContent();
        Log.d("cricket", "virender sehwag opening to bat");
        return rootView;
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


    public void refreshContent() {
        new GetAdminData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        ((AdminActivity) getActivity()).requestProfileImage();
        updateProgress.setVisibility(View.VISIBLE);


    }

    public void showUpdateProgress() {
        updateProgress.setVisibility(View.VISIBLE);
    }

    public void hideUpdateProgress() {
        updateProgress.setVisibility(View.VISIBLE);
    }



    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Action").setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    try {

                        dialog.cancel();
                        ((AdminActivity) getActivity()).requestCropImage();
                    } catch (Exception e) {
                    }

                } else if (which == 1) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    new DeleteProfile().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

    void populateData() {
        setVisibilityExpbox();

        if (ucode != null) {
            if (!ucode.equals(""))
                Log.d("cricket", "sachin tendulkar massive inning");
                myprofilepreview.setText(ucode);
        }

        if (found_AdminIntro == 1) {
            Log.d("cricket", "virat kohli massive inning");
            if (!fname.equals("") && !lname.equals("")) {
                myprofilename.setText(fname + " " + lname);
                nametxt.setText(fname + " " + lname);
                percentProfile++;
            }
            if (!country.equals("") && !state.equals("") && !city.equals("")) {
                myprofilloc.setText(city + ", " + state + ", " + country);
            }
            if (!inst.equals("")) {
                myprofiledu.setText(inst);
            }
        }
        if (found_institute == 1) {
            Log.d("cricket", "ishan kishan massive inning");
            if (!instname.equals("")) {
                myprofileclgname.setText(instname);
                myprofiledu.setText(instname);
                percentProfile++;
            }

            if (!instemail.equals("")) {
                instcontactemail.setText(instemail);
            }

            if (!instweb.equals("")) {
                instwebsite.setText(instweb);
            }

            if (!instphone.equals("")) {
                insttelephone.setText(instphone);
            }

            if (!instcaddrline1.equals("")) {
                instcontactaddr.setText(instcaddrline1 + " " + instcaddrline2 + " " + instcaddrline3);
            }

        }
        if (found_contact_details == 1) {
            Log.d("cricket", " Dinesh kartik massive inning");
            if (!addressline1.equals("")) {
                contactaddr1.setText(addressline1 + " " + addressline2 + " " + addressline3);
            }
            if (!email2.equals("")) {
                contactprofesionalemail.setText(email2);
            }
            if (!plainusername.equals("")) {
                contactpersonalemail.setText(plainusername);
            }
            if (!mobile.equals("")) {
                contactmobile.setText(mobile);
            }
            percentProfile++;

        }
        if (found_lang == 1) {
            if (!lang1.equals("- Select Language -")) {
                Log.d("cricket", " ajinkya rahane massive inning");
                if (!lang1.equals("") && !lang1.equals("- Select Language -"))
                    acc2txttxt.setText(lang1);
                if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -"))
                    acc2txttxt.setText(lang1 + ", " + lang2);
                if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -") && !lang3.equals("") && !lang3.equals("- Select Language -"))
                    acc2txttxt.setText(lang1 + ", " + lang2 + ", " + lang3);
                if (!lang1.equals("") && !lang1.equals("- Select Language -") && !lang2.equals("") && !lang2.equals("- Select Language -") && !lang3.equals("") && !lang3.equals("- Select Language -") && !lang4.equals("") && !lang4.equals("- Select Language -"))
                    acc2txttxt.setText(lang1 + ", " + lang2 + ", " + lang3 + " and " + lang_count + " more");
                percentProfile++;
            } else {
                acc2txttxt.setText("No known languages filled.");
            }
        }

        if (found_skills == 1) {
            if (!skill1.equals("")) {
                Log.d("cricket", " Ab divillers  massive inning");
                if (!skill1.equals(""))
                    acc4txttxt.setText(skill1);
                if (!skill1.equals("") && !skill2.equals(""))
                    acc4txttxt.setText(skill1 + ", " + skill2);
                if (!skill1.equals("") && !skill2.equals("") && !skill3.equals(""))
                    acc4txttxt.setText(skill1 + ", " + skill2 + ", " + skill3);
                if (!skill1.equals("") && !skill2.equals("") && !skill3.equals("") && !skill4.equals(""))
                    acc4txttxt.setText(skill1 + ", " + skill2 + ", " + skill3 + " and " + skills_count + " more");
                percentProfile++;
            } else {
                acc4txttxt.setText("No skills filled.");
            }
        }

        if (found_honors == 1) {
            if (!htitle1.equals("")) {
                Log.d("cricket", " hardik pandya massive inning");
                if (!htitle1.equals(""))
                    acc5txttxt.setText(htitle1);
                if (!htitle1.equals("") && !htitle2.equals(""))
                    acc5txttxt.setText(htitle1 + ", " + htitle2);
                if (!htitle1.equals("") && !htitle2.equals("") && !htitle3.equals(""))
                    acc5txttxt.setText(htitle1 + ", " + htitle2 + ", " + htitle3);
                if (!htitle1.equals("") && !htitle2.equals("") && !htitle3.equals("") && !htitle4.equals(""))
                    acc5txttxt.setText(htitle1 + ", " + htitle2 + ", " + htitle3 + " and " + honor_count + " more");
                percentProfile++;

            } else {
                acc5txttxt.setText("No awards filled.");
            }

        }
        if (found_patents == 1) {
            if (!ptitle1.equals("")) {
                Log.d("cricket", " shreyash ayyar  massive inning");
                if (!ptitle1.equals(""))
                    acc6txttxt.setText(ptitle1);
                if (!ptitle1.equals("") && !ptitle2.equals(""))
                    acc6txttxt.setText(ptitle1 + ", " + ptitle2);
                if (!ptitle1.equals("") && !ptitle2.equals("") && !ptitle3.equals(""))
                    acc6txttxt.setText(ptitle1 + ", " + ptitle2 + ", " + ptitle3);
                if (!ptitle1.equals("") && !ptitle2.equals("") && !ptitle3.equals("") && !ptitle4.equals(""))
                    acc6txttxt.setText(ptitle1 + ", " + ptitle2 + ", " + ptitle3 + " and " + patent_count + " more");
                percentProfile++;

            } else {
                acc6txttxt.setText("No patents filled.");
            }

        }
        if (found_publications == 1) {
            if (!pubtitle1.equals("")) {
                Log.d("cricket", " ambati rayadu  massive inning");
                if (!pubtitle1.equals(""))
                    acc7txttxt.setText(pubtitle1);
                if (!pubtitle1.equals("") && !pubtitle2.equals(""))
                    acc7txttxt.setText(pubtitle1 + ", " + pubtitle2);
                if (!pubtitle1.equals("") && !pubtitle2.equals("") && !pubtitle3.equals(""))
                    acc7txttxt.setText(pubtitle1 + ", " + pubtitle2 + ", " + pubtitle3);
                if (!pubtitle1.equals("") && !pubtitle2.equals("") && !pubtitle3.equals("") && !pubtitle4.equals(""))
                    acc7txttxt.setText(pubtitle1 + ", " + pubtitle2 + ", " + pubtitle3 + " and " + public_count + " more");
                percentProfile++;

            } else {
                acc7txttxt.setText("No publications filled.");
            }

        }

        populateExperiencesInfo();

        if (hrinfobox1 == true)
            percentProfile++;

    }

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

        if (exp_count > 3) {
            extraexpcount.setVisibility(View.VISIBLE);
            extraexpcount.setText("and " + exps_count + " more");
        } else
            extraexpcount.setVisibility(View.GONE);

        if (!fromdate1.equals("")) {
            exptab1.setVisibility(View.VISIBLE);
            exp1.setVisibility(View.VISIBLE);
            noexptab.setVisibility(View.GONE);

            exptab2.setVisibility(View.GONE);
            exp2.setVisibility(View.GONE);
            exptab3.setVisibility(View.GONE);
            exp3.setVisibility(View.GONE);

        } else {
            exptab1.setVisibility(View.GONE);
            exp1.setVisibility(View.GONE);
            noexptab.setVisibility(View.VISIBLE);

            exptab2.setVisibility(View.GONE);
            exp2.setVisibility(View.GONE);

            exptab3.setVisibility(View.GONE);
            exp3.setVisibility(View.GONE);

        }

        if (!fromdate2.equals("")) {
            exptab2.setVisibility(View.VISIBLE);
            exp2.setVisibility(View.VISIBLE);
            noexptab.setVisibility(View.GONE);

            exptab3.setVisibility(View.GONE);
            exp3.setVisibility(View.GONE);
        }

        if (!fromdate3.equals("")) {

            exptab3.setVisibility(View.VISIBLE);
            exp3.setVisibility(View.VISIBLE);
            noexptab.setVisibility(View.GONE);

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
        TreeMap<Integer, Integer> continuseWork = new TreeMap<>();
        TreeMap<Integer, Integer> workDoneExp = new TreeMap<>(Collections.reverseOrder());

        hrinfobox1 = false;
        hrinfobox2 = false;
        hrinfobox3 = false;

        int fulltodate1 = 0, fulltodate2 = 0, fulltodate3 = 0, fulltodate4 = 0, fulltodate5 = 0, fulltodate6 = 0, fulltodate7 = 0, fulltodate8 = 0, fulltodate9 = 0, fulltodate10 = 0;

        String MONTH = "";
        String YEAR = "";

        if (todate1.equals("") && !fromdate1.equals("")) {
            Log.d("cricket", " Ravidra jadeja massive inning");
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

        if (!todate1.equals("") && !fromdate1.equals("")) {
            Log.d("cricket", " Ravidra jadeja massive inning");
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

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);
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

    public void downloadImage() {

        new Getsingnature().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class Getsingnature extends AsyncTask<String, String, String> {
        String signature = "";

        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(Z.load_last_updated, "GET", params);
            try {
                signature = json.getString("lastupdated");
            } catch (Exception ex) {
            }
            return signature;
        }

        @Override
        protected void onPostExecute(String result) {

            Uri uri = new Uri.Builder()
                    .scheme("https")
                    .authority(Z.VPS_IP)
                    .path("AESTest/GetImage")
                    .appendQueryParameter("u", username)
                    .build();
try {

            Glide.with(ShouldAnimateProfile.AdminActivity)
                    .load(uri)
                    .crossFade()
                    .signature(new StringSignature(signature))
                    .listener(new RequestListener<Uri, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                            updateProgress.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            updateProgress.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(myprofileimg);

        }
            catch (Exception e){
            Log.d("TAG", "onPostExecute: glide exception - "+e.getMessage());
        }

        }
    }

    private class GetAdminData extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;

            try {
                percentProfile = 0;
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));
                json = jParser.makeHttpRequest(Z.load_Admin_data, "GET", params);
                resultofop = json.getString("info");
                if (resultofop.equals("found")) {
                    Log.d("cricket", "sachin tendulkar opening to bat");
                    ucode = json.getString("ucode");
                    String s = json.getString("AdminIntro");

                    if (s.equals("found")) {
                        found_AdminIntro = 1;
                        String AdminIntroObj = json.getString("AdminIntroObj");
                        AdminIntroModal obj1 = (AdminIntroModal) fromString(AdminIntroObj, digest1, digest2);

                        fname = obj1.getFname();
                        mname = obj1.getMname();
                        lname = obj1.getLname();
                        country = obj1.getCountry();
                        state = obj1.getState();
                        city = obj1.getCity();
                        inst = obj1.getInstitute();

                        a.setPhone(phone);
                        a.setFname(fname);
                        a.setMname(mname);
                        a.setLname(lname);
                        a.setInstitute(inst);
                        a.setCountry(country);
                        a.setState(state);
                        a.setCity(city);
                    }
                    s = json.getString("AdminInstitute");
                    if (s.equals("found")) {
                        Log.d("cricket", "ishan kishan opening to bat");
                        found_institute = 1;
                        String AdminInstituteobj = json.getString("AdminInstituteobj");
                        AdminInstituteModal obj2 = (AdminInstituteModal) fromString(AdminInstituteobj, digest1, digest2);

                        instname = obj2.getInstname();
                        instemail = obj2.getInstemail();
                        instweb = obj2.getInstweb();
                        instphone = obj2.getInstphone();
                        instaltrphone = obj2.getInstaltrphone();
                        universityname = obj2.getUnivname();
                        instreg = obj2.getInstregno();

                        instcaddrline1 = obj2.getInstcaddrline1();
                        instcaddrline2 = obj2.getInstcaddrline2();
                        instcaddrline3 = obj2.getInstcaddrline3();
                        a.setInstitute(instname);
                        a.setInstemail(instemail);
                        a.setInstweb(instweb);
                        a.setInstphone(instphone);
                        a.setInstaltrphone(instaltrphone);
                        a.setUnivname(universityname);
                        a.setInstregno(instreg);
                        a.setInstcaddrline1(instcaddrline1);
                        a.setInstcaddrline2(instcaddrline2);
                        a.setInstcaddrline3(instcaddrline3);
                    }

                    s = json.getString("knownlang");
                    if (s.equals("found")) {
                        found_lang = 1;
                        Log.d("cricket", " ajinkya rahane coming to bat");
                        ArrayList<KnownLangs> knownLangsList = (ArrayList<KnownLangs>) fromString(json.getString("knownlangdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        KnownLangs obj1 = knownLangsList.get(0);
                        KnownLangs obj2 = knownLangsList.get(1);
                        KnownLangs obj3 = knownLangsList.get(2);
                        KnownLangs obj4 = knownLangsList.get(3);
                        KnownLangs obj5 = knownLangsList.get(4);
                        KnownLangs obj6 = knownLangsList.get(5);
                        KnownLangs obj7 = knownLangsList.get(6);
                        KnownLangs obj8 = knownLangsList.get(7);
                        KnownLangs obj9 = knownLangsList.get(8);
                        KnownLangs obj10 = knownLangsList.get(9);

                        lang1 = obj1.getKnownlang();
                        proficiency1 = obj1.getProficiency();
                        lang2 = obj2.getKnownlang();
                        proficiency2 = obj2.getProficiency();
                        lang3 = obj3.getKnownlang();
                        proficiency3 = obj3.getProficiency();
                        lang4 = obj4.getKnownlang();
                        proficiency4 = obj4.getProficiency();
                        lang5 = obj5.getKnownlang();
                        proficiency5 = obj5.getProficiency();
                        lang6 = obj6.getKnownlang();
                        proficiency6 = obj6.getProficiency();
                        lang7 = obj7.getKnownlang();
                        proficiency7 = obj7.getProficiency();
                        lang8 = obj8.getKnownlang();
                        proficiency8 = obj8.getProficiency();
                        lang9 = obj9.getKnownlang();
                        proficiency9 = obj9.getProficiency();
                        lang10 = obj10.getKnownlang();
                        proficiency10 = obj10.getProficiency();

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


                        if (!lang4.equals("") && !lang4.equals("- Select Language -"))
                            lang_count = 1;
                        if (!lang5.equals("") && !lang5.equals("- Select Language -"))
                            lang_count = 2;
                        if (!lang6.equals("") && !lang6.equals("- Select Language -"))
                            lang_count = 3;
                        if (!lang7.equals("") && !lang7.equals("- Select Language -"))
                            lang_count = 4;
                        if (!lang8.equals("") && !lang8.equals("- Select Language -"))
                            lang_count = 5;
                        if (!lang9.equals("") && !lang9.equals("- Select Language -"))
                            lang_count = 6;
                        if (!lang10.equals("") && !lang10.equals("- Select Language -"))
                            lang_count = 7;

                    }
                    s = json.getString("skills");
                    if (s.equals("found")) {
                        found_skills = 1;
                        Log.d("cricket", " Ab divillers  coming to bat");
                        ArrayList<Skills> skillsList = (ArrayList<Skills>) fromString(json.getString("skillsdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Skills obj1 = skillsList.get(0);
                        Skills obj2 = skillsList.get(1);
                        Skills obj3 = skillsList.get(2);
                        Skills obj4 = skillsList.get(3);
                        Skills obj5 = skillsList.get(4);
                        Skills obj6 = skillsList.get(5);
                        Skills obj7 = skillsList.get(6);
                        Skills obj8 = skillsList.get(7);
                        Skills obj9 = skillsList.get(8);
                        Skills obj10 = skillsList.get(9);
                        Skills obj11 = skillsList.get(10);
                        Skills obj12 = skillsList.get(11);
                        Skills obj13 = skillsList.get(12);
                        Skills obj14 = skillsList.get(13);
                        Skills obj15 = skillsList.get(14);
                        Skills obj16 = skillsList.get(15);
                        Skills obj17 = skillsList.get(16);
                        Skills obj18 = skillsList.get(17);
                        Skills obj19 = skillsList.get(18);
                        Skills obj20 = skillsList.get(19);

                        skill1 = obj1.getSkill();
                        sproficiency1 = obj1.getProficiency();

                        skill2 = obj2.getSkill();
                        sproficiency2 = obj2.getProficiency();

                        skill3 = obj3.getSkill();
                        sproficiency3 = obj3.getProficiency();

                        skill4 = obj4.getSkill();
                        sproficiency4 = obj4.getProficiency();

                        skill5 = obj5.getSkill();
                        sproficiency5 = obj5.getProficiency();

                        skill6 = obj6.getSkill();
                        sproficiency6 = obj6.getProficiency();

                        skill7 = obj7.getSkill();
                        sproficiency7 = obj7.getProficiency();

                        skill8 = obj8.getSkill();
                        sproficiency8 = obj8.getProficiency();

                        skill9 = obj9.getSkill();
                        sproficiency9 = obj9.getProficiency();

                        skill10 = obj10.getSkill();
                        sproficiency10 = obj10.getProficiency();

                        skill11 = obj11.getSkill();
                        sproficiency11 = obj11.getProficiency();

                        skill12 = obj12.getSkill();
                        sproficiency12 = obj12.getProficiency();

                        skill13 = obj13.getSkill();
                        sproficiency13 = obj13.getProficiency();

                        skill14 = obj14.getSkill();
                        sproficiency14 = obj14.getProficiency();

                        skill15 = obj15.getSkill();
                        sproficiency15 = obj15.getProficiency();

                        skill16 = obj16.getSkill();
                        sproficiency16 = obj16.getProficiency();

                        skill17 = obj17.getSkill();
                        sproficiency17 = obj17.getProficiency();

                        skill18 = obj18.getSkill();
                        sproficiency18 = obj18.getProficiency();

                        skill19 = obj19.getSkill();
                        sproficiency19 = obj19.getProficiency();

                        skill20 = obj20.getSkill();
                        sproficiency20 = obj20.getProficiency();

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

                        if (!skill4.equals(""))
                            skills_count = 1;

                        if (!skill5.equals(""))
                            skills_count = 2;

                        if (!skill6.equals(""))
                            skills_count = 3;

                        if (!skill7.equals(""))
                            skills_count = 4;

                        if (!skill8.equals(""))
                            skills_count = 5;

                        if (!skill9.equals(""))
                            skills_count = 6;

                        if (!skill10.equals(""))
                            skills_count = 7;

                        if (!skill11.equals(""))
                            skills_count = 8;

                        if (!skill12.equals(""))
                            skills_count = 9;

                        if (!skill13.equals(""))
                            skills_count = 10;

                        if (!skill14.equals(""))
                            skills_count = 11;

                        if (!skill15.equals(""))
                            skills_count = 12;

                        if (!skill16.equals(""))
                            skills_count = 13;

                        if (!skill17.equals(""))
                            skills_count = 14;

                        if (!skill18.equals(""))
                            skills_count = 15;

                        if (!skill19.equals(""))
                            skills_count = 16;

                        if (!skill20.equals(""))
                            skills_count = 17;
                    }
                    s = json.getString("honors");
                    if (s.equals("found")) {
                        found_honors = 1;
                        Log.d("cricket", " hardik pandya coming to bat");
                        ArrayList<Honors> honorsList = (ArrayList<Honors>) fromString(json.getString("honorsdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Honors obj1 = honorsList.get(0);
                        Honors obj2 = honorsList.get(1);
                        Honors obj3 = honorsList.get(2);
                        Honors obj4 = honorsList.get(3);
                        Honors obj5 = honorsList.get(4);
                        Honors obj6 = honorsList.get(5);
                        Honors obj7 = honorsList.get(6);
                        Honors obj8 = honorsList.get(7);
                        Honors obj9 = honorsList.get(8);
                        Honors obj10 = honorsList.get(9);

                        htitle1 = obj1.getTitle();
                        hissuer1 = obj1.getIssuer();
                        hdescription1 = obj1.getDescription();
                        yearofhonor1 = obj1.getYearofhonor();

                        htitle2 = obj2.getTitle();
                        hissuer2 = obj2.getIssuer();
                        hdescription2 = obj2.getDescription();
                        yearofhonor2 = obj2.getYearofhonor();

                        htitle3 = obj3.getTitle();
                        hissuer3 = obj3.getIssuer();
                        hdescription3 = obj3.getDescription();
                        yearofhonor3 = obj3.getYearofhonor();

                        htitle4 = obj4.getTitle();
                        hissuer4 = obj4.getIssuer();
                        hdescription4 = obj4.getDescription();
                        yearofhonor4 = obj4.getYearofhonor();

                        htitle5 = obj5.getTitle();
                        hissuer5 = obj5.getIssuer();
                        hdescription5 = obj5.getDescription();
                        yearofhonor5 = obj5.getYearofhonor();

                        htitle6 = obj6.getTitle();
                        hissuer6 = obj6.getIssuer();
                        hdescription6 = obj6.getDescription();
                        yearofhonor6 = obj6.getYearofhonor();

                        htitle7 = obj7.getTitle();
                        hissuer7 = obj7.getIssuer();
                        hdescription7 = obj7.getDescription();
                        yearofhonor7 = obj7.getYearofhonor();

                        htitle8 = obj8.getTitle();
                        hissuer8 = obj8.getIssuer();
                        hdescription8 = obj8.getDescription();
                        yearofhonor8 = obj8.getYearofhonor();

                        htitle9 = obj9.getTitle();
                        hissuer9 = obj9.getIssuer();
                        hdescription9 = obj9.getDescription();
                        yearofhonor9 = obj9.getYearofhonor();

                        htitle10 = obj10.getTitle();
                        hissuer10 = obj10.getIssuer();
                        hdescription10 = obj10.getDescription();
                        yearofhonor10 = obj10.getYearofhonor();

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

                        if (!htitle4.equals(""))
                            honor_count = 1;
                        if (!htitle5.equals(""))
                            honor_count = 2;
                        if (!htitle6.equals(""))
                            honor_count = 3;
                        if (!htitle7.equals(""))
                            honor_count = 4;
                        if (!htitle8.equals(""))
                            honor_count = 5;
                        if (!htitle9.equals(""))
                            honor_count = 6;
                        if (!htitle10.equals(""))
                            honor_count = 7;
                    }

                    s = json.getString("patents");
                    if (s.equals("found")) {
                        found_patents = 1;
                        Log.d("cricket", " shreyash ayyar  coming to bat");
                        ArrayList<Patents> patentsList = (ArrayList<Patents>) fromString(json.getString("patentsdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Patents obj1 = patentsList.get(0);
                        Patents obj2 = patentsList.get(1);
                        Patents obj3 = patentsList.get(2);
                        Patents obj4 = patentsList.get(3);
                        Patents obj5 = patentsList.get(4);
                        Patents obj6 = patentsList.get(5);
                        Patents obj7 = patentsList.get(6);
                        Patents obj8 = patentsList.get(7);
                        Patents obj9 = patentsList.get(8);
                        Patents obj10 = patentsList.get(9);

                        ptitle1 = obj1.getTitle();
                        pappno1 = obj1.getAppno();
                        pselectedcountry1 = obj1.getPatoffice();
                        pinventor1 = obj1.getInventor();
                        issuedorpending1 = obj1.getIssuedorpending();
                        pissue1 = obj1.getIssue();
                        pfiling1 = obj1.getFiling();
                        purl1 = obj1.getUrl();
                        pdescription1 = obj1.getDescription();

                        ptitle2 = obj2.getTitle();
                        pappno2 = obj2.getAppno();
                        pselectedcountry2 = obj2.getPatoffice();
                        pinventor2 = obj2.getInventor();
                        issuedorpending2 = obj2.getIssuedorpending();
                        pissue2 = obj2.getIssue();
                        pfiling2 = obj2.getFiling();
                        purl2 = obj2.getUrl();
                        pdescription2 = obj2.getDescription();

                        ptitle3 = obj3.getTitle();
                        pappno3 = obj3.getAppno();
                        pselectedcountry3 = obj3.getPatoffice();
                        pinventor3 = obj3.getInventor();
                        issuedorpending3 = obj3.getIssuedorpending();
                        pissue3 = obj3.getIssue();
                        pfiling3 = obj3.getFiling();
                        purl3 = obj3.getUrl();
                        pdescription3 = obj3.getDescription();

                        ptitle4 = obj4.getTitle();
                        pappno4 = obj4.getAppno();
                        pselectedcountry4 = obj4.getPatoffice();
                        pinventor4 = obj4.getInventor();
                        issuedorpending4 = obj4.getIssuedorpending();
                        pissue4 = obj4.getIssue();
                        pfiling4 = obj4.getFiling();
                        purl4 = obj4.getUrl();
                        pdescription4 = obj4.getDescription();

                        ptitle5 = obj5.getTitle();
                        pappno5 = obj5.getAppno();
                        pselectedcountry5 = obj5.getPatoffice();
                        pinventor5 = obj5.getInventor();
                        issuedorpending5 = obj5.getIssuedorpending();
                        pissue5 = obj5.getIssue();
                        pfiling5 = obj5.getFiling();
                        purl5 = obj5.getUrl();
                        pdescription5 = obj5.getDescription();

                        ptitle6 = obj6.getTitle();
                        pappno6 = obj6.getAppno();
                        pselectedcountry6 = obj6.getPatoffice();
                        pinventor6 = obj6.getInventor();
                        issuedorpending6 = obj6.getIssuedorpending();
                        pissue6 = obj6.getIssue();
                        pfiling6 = obj6.getFiling();
                        purl6 = obj6.getUrl();
                        pdescription6 = obj6.getDescription();

                        ptitle7 = obj7.getTitle();
                        pappno7 = obj7.getAppno();
                        pselectedcountry7 = obj7.getPatoffice();
                        pinventor7 = obj7.getInventor();
                        issuedorpending7 = obj7.getIssuedorpending();
                        pissue7 = obj7.getIssue();
                        pfiling7 = obj7.getFiling();
                        purl7 = obj7.getUrl();
                        pdescription7 = obj7.getDescription();

                        ptitle8 = obj8.getTitle();
                        pappno8 = obj8.getAppno();
                        pselectedcountry8 = obj8.getPatoffice();
                        pinventor8 = obj8.getInventor();
                        issuedorpending8 = obj8.getIssuedorpending();
                        pissue8 = obj8.getIssue();
                        pfiling8 = obj8.getFiling();
                        purl8 = obj8.getUrl();
                        pdescription8 = obj8.getDescription();

                        ptitle9 = obj9.getTitle();
                        pappno9 = obj9.getAppno();
                        pselectedcountry9 = obj9.getPatoffice();
                        pinventor9 = obj9.getInventor();
                        issuedorpending9 = obj9.getIssuedorpending();
                        pissue9 = obj9.getIssue();
                        pfiling9 = obj9.getFiling();
                        purl9 = obj9.getUrl();
                        pdescription9 = obj9.getDescription();

                        ptitle10 = obj10.getTitle();
                        pappno10 = obj10.getAppno();
                        pselectedcountry10 = obj10.getPatoffice();
                        pinventor10 = obj10.getInventor();
                        issuedorpending10 = obj10.getIssuedorpending();
                        pissue10 = obj10.getIssue();
                        pfiling10 = obj10.getFiling();
                        purl10 = obj10.getUrl();
                        pdescription10 = obj10.getDescription();

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

                        if (!ptitle4.equals(""))
                            patent_count = 1;
                        if (!ptitle5.equals(""))
                            patent_count = 2;
                        if (!ptitle6.equals(""))
                            patent_count = 3;
                        if (!ptitle7.equals(""))
                            patent_count = 4;
                        if (!ptitle8.equals(""))
                            patent_count = 5;
                        if (!ptitle9.equals(""))
                            patent_count = 6;
                        if (!ptitle10.equals(""))
                            patent_count = 7;
                    }

                    s = json.getString("publications");
                    if (s.equals("found")) {
                        found_publications = 1;
                        Log.d("cricket", " ambati rayadu  coming to bat");
                        ArrayList<Publications> publicationsList = (ArrayList<Publications>) fromString(json.getString("publicationsdata"), MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Publications obj1 = publicationsList.get(0);
                        Publications obj2 = publicationsList.get(1);
                        Publications obj3 = publicationsList.get(2);
                        Publications obj4 = publicationsList.get(3);
                        Publications obj5 = publicationsList.get(4);
                        Publications obj6 = publicationsList.get(5);
                        Publications obj7 = publicationsList.get(6);
                        Publications obj8 = publicationsList.get(7);
                        Publications obj9 = publicationsList.get(8);
                        Publications obj10 = publicationsList.get(9);

                        pubtitle1 = obj1.getTitle();
                        publication1 = obj1.getPublication();
                        author1 = obj1.getAuthor();
                        publicationdate1 = obj1.getPublicationdate();
                        puburl1 = obj1.getUrl();
                        pubdescription1 = obj1.getDescription();

                        pubtitle2 = obj2.getTitle();
                        publication2 = obj2.getPublication();
                        author2 = obj2.getAuthor();
                        publicationdate2 = obj2.getPublicationdate();
                        puburl2 = obj2.getUrl();
                        pubdescription2 = obj2.getDescription();

                        pubtitle3 = obj3.getTitle();
                        publication3 = obj3.getPublication();
                        author3 = obj3.getAuthor();
                        publicationdate3 = obj3.getPublicationdate();
                        puburl3 = obj3.getUrl();
                        pubdescription3 = obj3.getDescription();

                        pubtitle4 = obj4.getTitle();
                        publication4 = obj4.getPublication();
                        author4 = obj4.getAuthor();
                        publicationdate4 = obj4.getPublicationdate();
                        puburl4 = obj4.getUrl();
                        pubdescription4 = obj4.getDescription();

                        pubtitle5 = obj5.getTitle();
                        publication5 = obj5.getPublication();
                        author5 = obj5.getAuthor();
                        publicationdate5 = obj5.getPublicationdate();
                        puburl5 = obj5.getUrl();
                        pubdescription5 = obj5.getDescription();

                        pubtitle6 = obj6.getTitle();
                        publication6 = obj6.getPublication();
                        author6 = obj6.getAuthor();
                        publicationdate6 = obj6.getPublicationdate();
                        puburl6 = obj6.getUrl();
                        pubdescription6 = obj6.getDescription();

                        pubtitle7 = obj7.getTitle();
                        publication7 = obj7.getPublication();
                        author7 = obj7.getAuthor();
                        publicationdate7 = obj7.getPublicationdate();
                        puburl7 = obj7.getUrl();
                        pubdescription7 = obj7.getDescription();

                        pubtitle8 = obj8.getTitle();
                        publication8 = obj8.getPublication();
                        author8 = obj8.getAuthor();
                        publicationdate8 = obj8.getPublicationdate();
                        puburl8 = obj8.getUrl();
                        pubdescription8 = obj8.getDescription();

                        pubtitle9 = obj9.getTitle();
                        publication9 = obj9.getPublication();
                        author9 = obj9.getAuthor();
                        publicationdate9 = obj9.getPublicationdate();
                        puburl9 = obj9.getUrl();
                        pubdescription9 = obj9.getDescription();

                        pubtitle10 = obj10.getTitle();
                        publication10 = obj10.getPublication();
                        author10 = obj10.getAuthor();
                        publicationdate10 = obj10.getPublicationdate();
                        puburl10 = obj10.getUrl();
                        pubdescription10 = obj10.getDescription();

                        studentData.setPubtitle1(pubtitle1);
                        studentData.setPublication1(publication1);
                        studentData.setAuthor1(author1);
                        studentData.setPublicationdate1(publicationdate1);
                        studentData.setPuburl1(puburl1);
                        studentData.setPubdescription1(pubdescription1);
                        studentData.setPubtitle2(pubtitle2);
                        studentData.setPublication2(publication2);
                        studentData.setAuthor2(author2);
                        studentData.setPublicationdate2(publicationdate2);
                        studentData.setPubdescription2(pubdescription2);
                        studentData.setPubtitle3(pubtitle3);
                        studentData.setPublication3(publication3);
                        studentData.setAuthor3(author3);
                        studentData.setPublicationdate3(publicationdate3);
                        studentData.setPuburl3(puburl3);
                        studentData.setPubdescription3(pubdescription3);
                        studentData.setPubtitle4(pubtitle4);
                        studentData.setPublication4(publication4);
                        studentData.setAuthor4(author4);
                        studentData.setPublicationdate4(publicationdate4);
                        studentData.setPuburl4(puburl4);
                        studentData.setPubdescription4(pubdescription4);
                        studentData.setPubtitle5(pubtitle5);
                        studentData.setPublication5(publication5);
                        studentData.setAuthor5(author5);
                        studentData.setPublicationdate5(publicationdate5);
                        studentData.setPuburl5(puburl5);
                        studentData.setPubdescription5(pubdescription5);
                        studentData.setPubtitle6(pubtitle6);
                        studentData.setPublication6(publication6);
                        studentData.setAuthor6(author6);
                        studentData.setPublicationdate6(publicationdate6);
                        studentData.setPuburl6(puburl6);
                        studentData.setPubdescription6(pubdescription6);
                        studentData.setPubtitle7(pubtitle7);
                        studentData.setPublication7(publication7);
                        studentData.setAuthor7(author7);
                        studentData.setPublicationdate7(publicationdate7);
                        studentData.setPuburl7(puburl7);
                        studentData.setPubdescription7(pubdescription7);
                        studentData.setPubtitle8(pubtitle8);
                        studentData.setPublication8(publication8);
                        studentData.setAuthor8(author8);
                        studentData.setPublicationdate8(publicationdate8);
                        studentData.setPuburl8(puburl8);
                        studentData.setPubdescription8(pubdescription8);
                        studentData.setPubtitle9(pubtitle9);
                        studentData.setPublication9(publication9);
                        studentData.setAuthor9(author9);
                        studentData.setPublicationdate9(publicationdate9);
                        studentData.setPuburl9(puburl9);
                        studentData.setPubdescription9(pubdescription9);
                        studentData.setPubtitle10(pubtitle10);
                        studentData.setPublication10(publication10);
                        studentData.setAuthor10(author10);
                        studentData.setPublicationdate10(publicationdate10);
                        studentData.setPuburl10(puburl10);
                        studentData.setPubdescription10(pubdescription10);

                        if (!pubtitle4.equals(""))
                            public_count = 1;
                        if (!pubtitle5.equals(""))
                            public_count = 2;
                        if (!pubtitle6.equals(""))
                            public_count = 3;
                        if (!pubtitle7.equals(""))
                            public_count = 4;
                        if (!pubtitle8.equals(""))
                            public_count = 5;
                        if (!pubtitle9.equals(""))
                            public_count = 6;
                        if (!pubtitle10.equals(""))
                            public_count = 7;
                    }

                    s = json.getString("experiences");
                    if (s.equals("found")) {
                        found_exp = 1;
                        Log.d("cricket", " Ravidra jadeja coming to bat");
                        experiencesataobject = json.getString("experiencesdata");

                        ArrayList<Experiences> ExperiencesList = (ArrayList<Experiences>) fromString(experiencesataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        Experiences obj1 = ExperiencesList.get(0);
                        Experiences obj2 = ExperiencesList.get(1);
                        Experiences obj3 = ExperiencesList.get(2);
                        Experiences obj4 = ExperiencesList.get(3);
                        Experiences obj5 = ExperiencesList.get(4);
                        Experiences obj6 = ExperiencesList.get(5);
                        Experiences obj7 = ExperiencesList.get(6);
                        Experiences obj8 = ExperiencesList.get(7);
                        Experiences obj9 = ExperiencesList.get(8);
                        Experiences obj10 = ExperiencesList.get(9);

                        post1 = obj1.getPost();
                        inst1 = obj1.getInst();
                        fromdate1 = obj1.getFromdate();
                        todate1 = obj1.getTodate();

                        post2 = obj2.getPost();
                        inst2 = obj2.getInst();
                        fromdate2 = obj2.getFromdate();
                        todate2 = obj2.getTodate();

                        post3 = obj3.getPost();
                        inst3 = obj3.getInst();
                        fromdate3 = obj3.getFromdate();
                        todate3 = obj3.getTodate();

                        post4 = obj4.getPost();
                        inst4 = obj4.getInst();
                        fromdate4 = obj4.getFromdate();
                        todate4 = obj4.getTodate();

                        post5 = obj5.getPost();
                        inst5 = obj5.getInst();
                        fromdate5 = obj5.getFromdate();
                        todate5 = obj5.getTodate();

                        post6 = obj6.getPost();
                        inst6 = obj6.getInst();
                        fromdate6 = obj6.getFromdate();
                        todate6 = obj6.getTodate();

                        post7 = obj7.getPost();
                        inst7 = obj7.getInst();
                        fromdate7 = obj7.getFromdate();
                        todate7 = obj7.getTodate();

                        post8 = obj8.getPost();
                        inst8 = obj8.getInst();
                        fromdate8 = obj8.getFromdate();
                        todate8 = obj8.getTodate();

                        post9 = obj9.getPost();
                        inst9 = obj9.getInst();
                        fromdate9 = obj9.getFromdate();
                        todate9 = obj9.getTodate();

                        post10 = obj10.getPost();
                        inst10 = obj10.getInst();
                        fromdate10 = obj10.getFromdate();
                        todate10 = obj10.getTodate();

                        a.setPost1e(post1);
                        a.setInst1e(inst1);
                        a.setFromdate1e(fromdate1);
                        a.setTodate1e(todate1);

                        a.setPost2e(post2);
                        a.setInst2e(inst2);
                        a.setFromdate2e(fromdate2);
                        a.setTodate2e(todate2);

                        a.setPost3e(post3);
                        a.setInst3e(inst3);
                        a.setFromdate3e(fromdate3);
                        a.setTodate3e(todate3);

                        a.setPost4e(post4);
                        a.setInst4e(inst4);
                        a.setFromdate4e(fromdate4);
                        a.setTodate4e(todate4);

                        a.setPost5e(post5);
                        a.setInst5e(inst5);
                        a.setFromdate5e(fromdate5);
                        a.setTodate5e(todate5);

                        a.setPost6e(post6);
                        a.setInst6e(inst6);
                        a.setFromdate6e(fromdate6);
                        a.setTodate6e(todate6);

                        a.setPost7e(post7);
                        a.setInst7e(inst7);
                        a.setFromdate7e(fromdate7);
                        a.setTodate7e(todate7);

                        a.setPost8e(post8);
                        a.setInst8e(inst8);
                        a.setFromdate8e(fromdate8);
                        a.setTodate8e(todate8);

                        a.setPost9e(post9);
                        a.setInst9e(inst9);
                        a.setFromdate9e(fromdate9);
                        a.setTodate9e(todate9);

                        a.setPost10e(post10);
                        a.setInst10e(inst10);
                        a.setFromdate10e(fromdate10);
                        a.setTodate10e(todate10);

                        if (!post4.equals(""))
                            exps_count = 1;

                        if (!post5.equals(""))
                            exps_count = 2;

                        if (!post6.equals(""))
                            exps_count = 3;

                        if (!post7.equals(""))
                            exps_count = 4;

                        if (!post8.equals(""))
                            exps_count = 5;

                        if (!post9.equals(""))
                            exps_count = 6;

                        if (!post10.equals(""))
                            exps_count = 7;

                    }
                    s = json.getString("personal");
                    if (s.equals("found")) {
                        found_personal = 1;
                        Log.d("cricket", " manish pandey  coming to bat");
                        personaldataobject = json.getString("personalobj");
                        AdminPersonal obj2 = (AdminPersonal) fromString(personaldataobject, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));

                        fname = obj2.fname;
                        mname = obj2.mname;
                        lname = obj2.sname;
                        inst = obj2.sinst;
                        email2 = obj2.alternateemail;
                        dob = obj2.dob;
                        gender = obj2.gender;
                        addressline1 = obj2.addrline1c;
                        addressline2 = obj2.addrline2c;
                        addressline3 = obj2.addrline3c;
                        paddrline1 = obj2.addrline1p;
                        paddrline2 = obj2.addrline2p;
                        paddrline3 = obj2.addrline3p;

                        a.setFname(fname);
                        a.setMname(mname);
                        a.setLname(lname);
                        a.setInstitute(inst);

                        studentData.setFname(fname);
                        studentData.setLname(lname);

                        studentData.setEmail2(email2);
                        studentData.setDob(dob);
                        studentData.setGender(gender);
                        studentData.setAddressline1(addressline1);
                        studentData.setAddressline2(addressline2);
                        studentData.setAddressline3(addressline3);
                        studentData.setPaddrline1(paddrline1);
                        studentData.setPaddrline2(paddrline2);
                        studentData.setPaddrline3(paddrline3);

                    }

                    s = json.getString("contact_details");
                    if (s.equals("found")) {
                        found_contact_details = 1;
                        Log.d("cricket", " Dinesh kartik coming to bat");
                        String contactdetailsobj = json.getString("contact_detailsdata");

                        AdminContactDetailsModal obj3 = (AdminContactDetailsModal) fromString(contactdetailsobj, digest1, digest2);

                        fname = obj3.getFname();
                        lname = obj3.getLname();
                        email2 = obj3.getEmail2();
                        addressline1 = obj3.getAddressline1();
                        addressline2 = obj3.getAddressline2();
                        addressline3 = obj3.getAddressline3();
                        telephone = obj3.getPhone();
                        mobile = obj3.getMobile();
                        mobile2 = obj3.getMobile2();

                        studentData.setFname(fname);
                        studentData.setLname(lname);
                        studentData.setEmail2(email2);
                        studentData.setTelephone(telephone);
                        studentData.setPhone(mobile);
                        studentData.setMobile2(mobile2);
                        studentData.setAddressline1(addressline1);
                        studentData.setAddressline2(addressline2);
                        studentData.setAddressline3(addressline3);
                    }
                }

            } catch (Exception e) {
                Log.d("cricket", "Match 2 lost : " + e.getMessage());
                e.printStackTrace();
            }
            return map;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            swipe_refresh_layout.setRefreshing(false);
            updateProgress.setVisibility(View.GONE);
            swipe_refresh_layout.setVisibility(View.VISIBLE);
            try {
                populateData();
                myprofileimg.setImageBitmap(result);
                float R = (1000 - 0) / (9 - 0);
                float y = (percentProfile - 0) * R + 0;
                val1 = Math.round(y);

                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(profileprogress, "progress", 0, val1);
                progressAnimator.setDuration(1000);
                progressAnimator.setInterpolator(new LinearInterpolator());
                progressAnimator.start();
                downloadImage();

            } catch (Exception e) {
                Log.d("cricket", "Match 3 lost : " + e.getMessage());
                e.printStackTrace();

            }

        }

    }

    class DeleteProfile extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(Z.remove_profile, "GET", params);

            try {

                resultofop = json.getString("info");

            } catch (Exception ex) {
                Log.d("cricket", "Match 4 lost " + ex.getMessage());
                ex.printStackTrace();
            }

            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {

            if (resultofop.equals("success")) {
                Toast.makeText(getActivity(), "Profile Picture removed..!", Toast.LENGTH_LONG).show();
                refreshContent();
                ((AdminActivity) getActivity()).requestProfileImage();
            } else if (resultofop.equals("fail"))
                Toast.makeText(getActivity(), "Failed..!", Toast.LENGTH_LONG).show();

            else if (resultofop.equals("notfound"))
                Toast.makeText(getActivity(), "No Profile Picture..!", Toast.LENGTH_LONG).show();

        }
    }

}