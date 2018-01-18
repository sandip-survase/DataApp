package placeme.octopusites.com.placeme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.KnownLangs;
import placeme.octopusites.com.placeme.modal.Modelmyprofileintro;
import placeme.octopusites.com.placeme.modal.MyProfileCareerObjModal;
import placeme.octopusites.com.placeme.modal.MyProfileDiplomaModal;
import placeme.octopusites.com.placeme.modal.MyProfilePersonal;
import placeme.octopusites.com.placeme.modal.MyProfileStrengthsModal;
import placeme.octopusites.com.placeme.modal.MyProfileTenthModal;
import placeme.octopusites.com.placeme.modal.MyProfileTwelthModal;
import placeme.octopusites.com.placeme.modal.MyProfileUgModal;
import placeme.octopusites.com.placeme.modal.MyProfileWeaknessesModal;
import placeme.octopusites.com.placeme.modal.Projects;
import placeme.octopusites.com.placeme.modal.Skills;

import static placeme.octopusites.com.placeme.AES4all.fromString;
import static placeme.octopusites.com.placeme.MainActivity.photo;


public class ViewPlacement extends AppCompatActivity {

    JSONObject json;
    JSONParser jParser = new JSONParser();
    String username, resultofop;
    String proj1 = "", domain1 = "", team1 = "", duration1 = "", skill1 = "", strength1 = "", weak1;
    String email2 = "", addressline1 = "", phone = "", lang1 = "", addressline2 = "", addressline3 = "", telephone = "", mobile2 = "";
    String nameasten = "", mothername = "", dob = "", gender = "", mothertongue = "", hobbies = "", bloodgroup = "", category = "", religion = "", caste = "", prn = "", paddrline1 = "", paddrline2 = "", paddrline3 = "", handicapped = "", sports = "", defenceex = "";
    String id, companyname, cpackage, post, forwhichcourse, forwhichstream, vacancies, lastdateofregistration, dateofarrival, bond, noofapti, nooftechtest, noofgd, noofti, noofhri, stdx, stdxiiordiploma, ug, pg, uploadtime, lastmodified, uploadedby, noofallowedliveatkt, noofalloweddeadatkt, studenttenthmarks, studenttwelthordiplomamarks, studentugmarks, studentpgmarks;
    Button registerbutton;
    ProgressBar progressBar;
    int found_box1 = 0, found_tenth = 0, found_twelth = 0, found_diploma = 0, found_ug = 0, found_pgsem = 0, found_pgyear = 0, found_projects = 0, found_lang = 0, found_certificates = 0;
    int found_courses = 0, found_skills = 0, found_honors = 0, found_patents = 0, found_publications = 0, found_careerobj = 0, found_strengths = 0, found_weaknesses = 0, found_locationpreferences = 0;
    int found_contact_details = 0, found_personal = 0, found_photo = 0;
    String role;
    String fname = "", lname = "";
    String sPadding = "ISO10126Padding";
    StudentData studentData = new StudentData();
    StudentData s = new StudentData();
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_placement);


        username = MySharedPreferencesManager.getUsername(this);

        role = MySharedPreferencesManager.getRole(this);

        toolbar = (Toolbar) findViewById(R.id.placementtoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Placement");

        viewPager = (ViewPager) findViewById(R.id.placementviewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.placementtabs);
        tabLayout.setupWithViewPager(viewPager);


        String uploadedby_enc = getIntent().getStringExtra("uploadedby");
        uploadedby = uploadedby_enc;
        registerbutton = (Button) findViewById(R.id.registerforplacementbutton);
        registerbutton.setTypeface(Z.getBold(this));
        progressBar = (ProgressBar) findViewById(R.id.registerforplacementprogress);

        id = getIntent().getStringExtra("id");
        companyname = getIntent().getStringExtra("companyname");
        cpackage = getIntent().getStringExtra("package");
        post = getIntent().getStringExtra("post");
        forwhichcourse = getIntent().getStringExtra("forwhichcourse");
        forwhichstream = getIntent().getStringExtra("forwhichstream");
        vacancies = getIntent().getStringExtra("vacancies");
        lastdateofregistration = getIntent().getStringExtra("lastdateofregistration");
        dateofarrival = getIntent().getStringExtra("dateofarrival");
        bond = getIntent().getStringExtra("bond");
        noofapti = getIntent().getStringExtra("noofapti");
        nooftechtest = getIntent().getStringExtra("nooftechtest");
        noofgd = getIntent().getStringExtra("noofgd");
        noofti = getIntent().getStringExtra("noofti");
        noofhri = getIntent().getStringExtra("noofhri");
        stdx = getIntent().getStringExtra("stdx");
        stdxiiordiploma = getIntent().getStringExtra("stdxiiordiploma");
        ug = getIntent().getStringExtra("ug");
        pg = getIntent().getStringExtra("pg");
        uploadtime = getIntent().getStringExtra("uploadtime");
        lastmodified = getIntent().getStringExtra("lastmodified");
        noofallowedliveatkt = getIntent().getStringExtra("noofallowedliveatkt");
        noofalloweddeadatkt = getIntent().getStringExtra("noofalloweddeadatkt");


        SavePlacementInfoForFragment save = new SavePlacementInfoForFragment();
        save.setId(id);
        save.setCompanyname(companyname);
        save.setPackage(cpackage);
        save.setPost(post);
        save.setForwhichcourse(forwhichcourse);
        save.setForwhichstream(forwhichstream);
        save.setVacancies(vacancies);
        save.setLastdateofregistration(lastdateofregistration);
        save.setDateofarrival(dateofarrival);
        save.setBond(bond);
        save.setNoofapti(noofapti);
        save.setNooftechtest(nooftechtest);
        save.setNoofgd(noofgd);
        save.setNoofti(noofti);
        save.setNoofhri(noofhri);
        save.setStdx(stdx);
        save.setStdxiiordiploma(stdxiiordiploma);
        save.setUg(ug);
        save.setPg(pg);
        save.setNoofallowedliveatkt(noofallowedliveatkt);
        save.setNoofalloweddeadatkt(noofalloweddeadatkt);
        save.setUploadtime(uploadtime);
        save.setLastmodified(lastmodified);
        save.setUploadedby(uploadedby);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // download resume in database
                registerbutton.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                studentmarks();
                validatedata();
            }
        });

//************

        if(role.equals("alumni") ||role.equals("student") ){

        String fnametocheck = s.getFname();

        if (fnametocheck == null) {

            GetStudentData getStudentData = new GetStudentData(ViewPlacement.this);
            getStudentData.execute();
            new Getsingnature().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else {
            studentmarks();
            new Getsingnature().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        }
        }else{
            LinearLayout registerbuttonlayout = (LinearLayout) findViewById(R.id.registerbuttonlayout);
            registerbuttonlayout.setVisibility(View.GONE);
        }



//        **********notification crash solution


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlacementTab1(), "Job Description");
        adapter.addFragment(new PlacementTab2(), "Selection Process");
        adapter.addFragment(new PlacementTab3(), "Selection Criteria");
        viewPager.setAdapter(adapter);
    }

    //    student data************************************************************************************
    public void studentmarks() {
        Log.d("TAG", "studentmarks: 183 -");

        fname = s.getFname();
        lname = s.getLname();

        String careerobj = s.getCareerobj();

        String dob = s.getDob();
        String mobile = s.getPhone();
        String hobbies = s.getHobbies();
        String addrline1c = s.getAddressline1();
        String addrline2c = s.getAddressline2();
        String addrline3c = s.getAddressline3();

        String lang1 = s.getLang1();
        String proj = s.getProj1();
        String strength1 = s.getStrength1();
        String weak1 = s.getWeak1();
        String skill1 = s.getSkill1();

        studenttenthmarks = s.getPercentage10();
        studenttwelthordiplomamarks = s.getPercentage12();
        studentugmarks = s.getAggregateug();


        if (fname != null && lname != null) {

            if (!fname.equals("") && !lname.equals(""))
                found_box1 = 1;
            else
                found_box1 = 0;
        }


        if (studenttenthmarks != null) {

            if (!studenttenthmarks.equals("")) {
                found_tenth = 1;
            } else
                found_tenth = 0;
        }

        if (studenttwelthordiplomamarks != null) {
            if (!studenttwelthordiplomamarks.equals("")) {
                found_twelth = 1;
            } else {
                found_twelth = 0;
                studenttwelthordiplomamarks = s.getAggregatediploma();
                found_diploma = 1;

            }
        }

        if (studentugmarks != null) {

            if (!studentugmarks.equals("")) {
                found_ug = 1;
            } else
                found_ug = 0;
        }


        if (dob != null && mobile != null && hobbies != null && addrline1c != null && addrline2c != null && addrline3c != null) {
            if (!dob.equals("") && !mobile.equals("") && !hobbies.equals("") && !addrline1c.equals("") && !addrline2c.equals("") && !addrline3c.equals("")) {
                found_personal = 1;
            } else
                found_personal = 0;
        }

        if (lang1 != null) {

            if (!lang1.equals("") && !lang1.equals("- Select Language -"))
                found_lang = 1;
            else
                found_lang = 0;
        }

        if (proj != null) {
            if (!proj.equals(""))
                found_projects = 1;
            else
                found_projects = 0;
        }

        if (strength1 != null) {
            if (!strength1.equals(""))
                found_strengths = 1;
            else
                found_strengths = 0;
        }
        if (weak1 != null) {
            if (!weak1.equals(""))
                found_weaknesses = 1;
            else
                found_weaknesses = 0;
        }

        if (careerobj != null) {
            if (!careerobj.equals(""))
                found_careerobj = 1;
            else
                found_careerobj = 0;
        }
        if (skill1 != null) {
            if (!skill1.equals(""))
                found_skills = 1;
            else
                found_skills = 0;
        }


//            **********************

        SavePlacementInfoForFragment save = new SavePlacementInfoForFragment();
        Float c10 = 0.0f, s10 = 0.0f, c12 = 0.0f, s12 = 0.0f, cu = 0.0f, su = 0.0f;
        c10 = Float.parseFloat(stdx);
        c12 = Float.parseFloat(stdxiiordiploma);
        cu = Float.parseFloat(ug);
        int tenthflag = 0, twelthordiplomaflag = 0, ugflag = 0;


        if (found_tenth == 0) {
//10 th not filled
            save.setStudenttenthmarks("0.00");

        } else {

            save.setStudenttenthmarks(studenttenthmarks);
            s10 = Float.parseFloat(studenttenthmarks);
            if (s10 >= c10) {
                tenthflag = 1;
            }


        }

        if (found_twelth == 0 && found_diploma == 0) {
//                        please fill twelth or diploma information
            save.setStudenttwelthordiplomamarks("0.00");

        } else {
            save.setStudenttwelthordiplomamarks(studenttwelthordiplomamarks);
            s12 = Float.parseFloat(studenttwelthordiplomamarks);
            if (s12 >= c12) {
                twelthordiplomaflag = 1;
            }
        }

        if (found_ug == 0) {
            save.setStudentugmarks("0.00");

//udgnot found
        } else {

            save.setStudentugmarks(studentugmarks);
            su = Float.parseFloat(studentugmarks);

            if (su >= cu) {
                ugflag = 1;
            }


        }


        if (found_pgsem == 0 && found_pgyear == 0) {
//                        please fill twelth or diploma information
            save.setStudentpgmarks("0.00");

        } else {
            save.setStudentpgmarks(studentpgmarks);
            s12 = Float.parseFloat(studenttwelthordiplomamarks);
            if (s12 >= c12) {
                twelthordiplomaflag = 1;
            }
        }


        LinearLayout registerbuttonlayout = (LinearLayout) findViewById(R.id.registerbuttonlayout);
        if (tenthflag == 1 && twelthordiplomaflag == 1 && ugflag == 1) {
            registerbuttonlayout.setVisibility(View.VISIBLE);
            registerbutton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);


        } else {
            registerbuttonlayout.setVisibility(View.GONE);

        }
    }

    //*************************************************
    public void validatedata() {

        Log.d("TAG", "validatedata: ShouldAnimateProfile.photo - "+ShouldAnimateProfile.photo);
        if (ShouldAnimateProfile.photo.equals("noupdate")) {

            registerbutton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            Snackbar.make(registerbutton.getRootView(), "Please upload photo", Snackbar.LENGTH_LONG)
                    .setAction("OPEN", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(role.equals("student")) {
                                startActivity(new Intent(ViewPlacement.this, MainActivity.class).putExtra("status", 1));
                                finish();
                            }
                            else {
                                startActivity(new Intent(ViewPlacement.this, AlumniActivity.class).putExtra("status", 1));
                                finish();
                            }


                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                    .setDuration(10000)
                    .show();
        } else {

            found_photo = 1;
            if (found_box1 == 0) {
//                    please fill intro information
                registerbutton.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Snackbar.make(registerbutton.getRootView(), "Please fill personal details", Snackbar.LENGTH_LONG)
                        .setAction("OPEN", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(ViewPlacement.this, MyProfileIntro.class));
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                        .setDuration(10000)
                        .show();
//            Toast.makeText(ViewPlacement.this, " Please fill personal details before applying", Toast.LENGTH_SHORT).show();
            } else {
                if (found_tenth == 0) {
//                        please fill tenth information
                    registerbutton.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Snackbar.make(registerbutton.getRootView(), "Please fill Std. X details", Snackbar.LENGTH_LONG)
                            .setAction("OPEN", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(ViewPlacement.this, MyProfileTenth.class));
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                            .setDuration(10000)
                            .show();
//                Toast.makeText(ViewPlacement.this, " Please fill Std. X details before applying", Toast.LENGTH_SHORT).show();

                } else {
                    if (found_twelth == 0 && found_diploma == 0) {
//                        please fill twelth or diploma information
                        registerbutton.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        Snackbar.make(registerbutton.getRootView(), "Please fill Std. XII/Diplom details", Snackbar.LENGTH_LONG)
                                .setAction("OPEN", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(ViewPlacement.this, MyProfileTwelthOrDiploma.class));
                                    }
                                })
                                .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                .setDuration(10000)
                                .show();
//                    Toast.makeText(ViewPlacement.this, "Please fill Std. XII/Diploma details before applying", Toast.LENGTH_SHORT).show();

                    } else {
                        if (found_ug == 0) {
//                        please fill ug information
                            Snackbar.make(registerbutton.getRootView(), "Please fill Std. Ug details", Snackbar.LENGTH_LONG)
                                    .setAction("OPEN", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(ViewPlacement.this, MyProfileUg.class));
                                        }
                                    })
                                    .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                    .setDuration(10000)
                                    .show();
//                        Toast.makeText(ViewPlacement.this, " Please fill your Ug details before applying", Toast.LENGTH_SHORT).show();

                        } else {
                            if (found_projects == 0) {
//                        please fill project information
                                registerbutton.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                                Snackbar.make(registerbutton.getRootView(), "Please fill Project details", Snackbar.LENGTH_LONG)
                                        .setAction("OPEN", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                startActivity(new Intent(ViewPlacement.this, MyProfileProjects.class));
                                            }
                                        })
                                        .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                        .setDuration(10000)
                                        .show();
//                            Toast.makeText(ViewPlacement.this, " Please fill Project details before applying", Toast.LENGTH_SHORT).show();

                            } else {
                                if (found_lang == 0) {
//                        please fill language information
                                    registerbutton.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                    Snackbar.make(registerbutton.getRootView(), "Please fill Known languages details", Snackbar.LENGTH_LONG)
                                            .setAction("OPEN", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    startActivity(new Intent(ViewPlacement.this, MyProfileKnownLang.class));
                                                }
                                            })
                                            .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                            .setDuration(10000)
                                            .show();
//                                Toast.makeText(ViewPlacement.this, " Please fill Known languages details before applying", Toast.LENGTH_SHORT).show();

                                } else {
                                    if (found_skills == 0) {
//                        please fill skill information
                                        registerbutton.setVisibility(View.VISIBLE);
                                        progressBar.setVisibility(View.GONE);
                                        Snackbar.make(registerbutton.getRootView(), "Please fill Skill details", Snackbar.LENGTH_LONG)
                                                .setAction("OPEN", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        startActivity(new Intent(ViewPlacement.this, MyProfileSkills.class));
                                                    }
                                                })
                                                .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                                .setDuration(10000)
                                                .show();

//                                    Toast.makeText(ViewPlacement.this, " Please fill Skill details before applying", Toast.LENGTH_SHORT).show();

                                    } else {
                                        if (found_careerobj == 0) {
//                        please fill career objective information
                                            registerbutton.setVisibility(View.VISIBLE);
                                            progressBar.setVisibility(View.GONE);
                                            Snackbar.make(registerbutton.getRootView(), "Please fill Career details", Snackbar.LENGTH_LONG)
                                                    .setAction("OPEN", new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            startActivity(new Intent(ViewPlacement.this, MyProfileCareerObj.class));
                                                        }
                                                    })
                                                    .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                                    .setDuration(10000)
                                                    .show();

//                                        Toast.makeText(ViewPlacement.this, " Please fill Career details before applying", Toast.LENGTH_SHORT).show();

                                        } else {
                                            if (found_strengths == 0) {
//                        please fill strength information
                                                registerbutton.setVisibility(View.VISIBLE);
                                                progressBar.setVisibility(View.GONE);
                                                Snackbar.make(registerbutton.getRootView(), "Please fill Strength details", Snackbar.LENGTH_LONG)
                                                        .setAction("OPEN", new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                startActivity(new Intent(ViewPlacement.this, MyProfileStrengths.class));
                                                            }
                                                        })
                                                        .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                                        .setDuration(10000)
                                                        .show();
//                                            Toast.makeText(ViewPlacement.this, " Please fill Strength details before applying", Toast.LENGTH_SHORT).show();

                                            } else {
                                                if (found_weaknesses == 0) {
//                        please fill weaknesses information
                                                    registerbutton.setVisibility(View.VISIBLE);
                                                    progressBar.setVisibility(View.GONE);
                                                    Snackbar.make(registerbutton.getRootView(), "Please fill Weaknesses details", Snackbar.LENGTH_LONG)
                                                            .setAction("OPEN", new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    startActivity(new Intent(ViewPlacement.this, MyProfileWeaknesses.class));
                                                                }
                                                            })
                                                            .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                                            .setDuration(10000)
                                                            .show();
//                                                Toast.makeText(ViewPlacement.this, " Please fill Weaknesses details before applying", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    if (found_personal == 0) {
//                        please fill personal information
                                                        registerbutton.setVisibility(View.VISIBLE);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(registerbutton.getRootView(), "Please fill Personal details details", Snackbar.LENGTH_LONG)
                                                                .setAction("OPEN", new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View view) {
                                                                        String role = MySharedPreferencesManager.getRole(ViewPlacement.this);
                                                                        if (role.equals("student")) {
                                                                            startActivity(new Intent(ViewPlacement.this, EditProfile.class));
                                                                        } else
                                                                            startActivity(new Intent(ViewPlacement.this, EditProfileAlumni.class));
                                                                    }
                                                                })
                                                                .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                                                .setDuration(10000)
                                                                .show();
//                                                    Toast.makeText(ViewPlacement.this, " Please fill Personal details before applying", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }


                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        if (found_photo == 1 && found_box1 == 1 && found_tenth == 1 && (found_diploma == 1 || found_twelth == 1) && found_ug == 1 && found_projects == 1 && found_lang == 1 && found_skills == 1 && found_careerobj == 1 && found_strengths == 1 && found_weaknesses == 1 && found_personal == 1) {
            new SaveResumedatabase().execute();

        }

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private class registerforPlacementTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... urls) {
            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", id));
                params.add(new BasicNameValuePair("u",username));
                params.add(new BasicNameValuePair("f", fname));
                params.add(new BasicNameValuePair("l", lname));
                params.add(new BasicNameValuePair("lc", companyname));

                json = jParser.makeHttpRequest(Z.url_RegisterForPlacement, "GET", params);
                String s = null;
                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {

            if (resultofop.equals("success"))
                Toast.makeText(ViewPlacement.this, "Successfully Registered", Toast.LENGTH_LONG).show();
            else if (resultofop.equals("already"))
                Toast.makeText(ViewPlacement.this, "Already Registered", Toast.LENGTH_LONG).show();
            else if (resultofop.equals("date"))
                Toast.makeText(ViewPlacement.this, "Registrations are closed..!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ViewPlacement.this, "Failed..!", Toast.LENGTH_LONG).show();

            registerbutton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

        }

    }

    private class SaveResumedatabase extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String r = "";
            String format = "pdf";

            try {

                String template = MySharedPreferencesManager.getData(ViewPlacement.this, "template");
                if (template == null) {
                    int temp = 2;
                    template = temp + "";
                }


                List<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("format", format));
                params.add(new BasicNameValuePair("template", template));

                json = jParser.makeHttpRequest(Z.url_SaveResume, "GET", params);

                r = json.getString("info");


            } catch (Exception e) {
            }
            return r;
        }

        protected void onPostExecute(String result) {
            try {

                if (result.equals("found")) {
                    new registerforPlacementTask().execute();
                } else
                    Toast.makeText(ViewPlacement.this, "Not Register..!", Toast.LENGTH_SHORT).show();

                registerbutton.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            } catch (Exception e) {
            }
        }
    }

    private class GetStudentData extends AsyncTask<String, Void, Bitmap> {
        private ProgressDialog dialog;

        public GetStudentData(ViewPlacement activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("please wait...");
            dialog.show();
        }


        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));
                if(role.equals("alumni")){
                    json = jParser.makeHttpRequest(Z.url_load_alumni_data, "GET", params);
                }
                else {
                    json = jParser.makeHttpRequest(Z.load_student_data, "GET", params);
                }

                //shift this to class
                String studenttenthmarksObj = "", studenttwelthordiplomamarksobj = "", diplomadataobject, ugdataobject = "";


                resultofop = json.getString("info");
                if (resultofop.equals("found")) {
                    String s = json.getString("intro");
                    if (s.equals("found")) {
                        found_box1 = 1;
                        Modelmyprofileintro obj2 = (Modelmyprofileintro) fromString(json.getString("introObj"), MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));
                        fname = obj2.getFirstname();
                        lname = obj2.getLastname();

                        studentData.setFname(fname);
                        studentData.setLname(lname);
                    }
                    s = json.getString("tenth");
                    if (s.equals("found")) {

                        studenttenthmarksObj = json.getString("tenthobj");
                        MyProfileTenthModal obj2 = (MyProfileTenthModal) fromString(studenttenthmarksObj, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));

                        studentData.setPercentage10(obj2.percentage);

                        found_tenth = 1;
                    }

                    s = json.getString("twelth");
                    if (s.equals("found")) {
                        studenttwelthordiplomamarksobj = json.getString("twelthobj");
                        MyProfileTwelthModal obj2 = (MyProfileTwelthModal) fromString(studenttwelthordiplomamarksobj, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));
                        studentData.setPercentage12(obj2.percentage);


                    }

                    s = json.getString("diploma");
                    if (s.equals("found")) {
                        found_diploma = 1;
                        Log.d("TAG", "dataload found_diploma:-" + found_diploma);

                        diplomadataobject = json.getString("diplomaobj");

                        MyProfileDiplomaModal obj2 = (MyProfileDiplomaModal) fromString(diplomadataobject, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));
                        studentData.setAggregatediploma(obj2.aggregate);

                    }

                    s = json.getString("ug");
                    if (s.equals("found")) {
                        ugdataobject = json.getString("ugobj");
                        MyProfileUgModal obj2 = (MyProfileUgModal) fromString(ugdataobject, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));

                        studentData.setAggregateug(obj2.aggregate);
                        found_ug = 1;
                    }


                    s = json.getString("projects");
                    if (s.equals("found")) {
                        found_projects = 1;
                        ArrayList<Projects> projectsList = (ArrayList<Projects>) fromString(json.getString("projectsdata"), MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));

                        Projects obj1 = projectsList.get(0);
                        proj1 = obj1.getProj1();
                        domain1 = obj1.getDomain1();
                        team1 = obj1.getTeam1();
                        duration1 = obj1.getDuration1();

                        studentData.setProj1(proj1);
                        studentData.setDomain1(domain1);
                        studentData.setTeam1(team1);
                        studentData.setDuration1(duration1);


                    }
                    s = json.getString("knownlang");
                    if (s.equals("found")) {
                        found_lang = 1;
                        ArrayList<KnownLangs> knownLangsList = (ArrayList<KnownLangs>) fromString(json.getString("knownlangdata"), MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));

                        KnownLangs obj1 = knownLangsList.get(0);
                        lang1 = obj1.getKnownlang();

                        studentData.setLang1(lang1);

                    }

                    s = json.getString("skills");
                    if (s.equals("found")) {
                        found_skills = 1;
                        ArrayList<Skills> skillsList = (ArrayList<Skills>) fromString(json.getString("skillsdata"), MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));

                        Skills obj1 = skillsList.get(0);
                        skill1 = obj1.getSkill();
                        studentData.setSkill1(skill1);

                    }

                    s = json.getString("career");
                    if (s.equals("found")) {
                        found_careerobj = 1;
                        String careerdataobject = json.getString("careerobj");
                        MyProfileCareerObjModal obj2 = (MyProfileCareerObjModal) fromString(careerdataobject, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));

                        String careerobj = obj2.careerobj;
                        studentData.setCareerobj(careerobj);
                    }

                    s = json.getString("strengths");
                    if (s.equals("found")) {
                        found_strengths = 1;
                        String strengthdataobject = json.getString("strengthsobj");

                        MyProfileStrengthsModal obj2 = (MyProfileStrengthsModal) fromString(strengthdataobject, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));

                        strength1 = obj2.sstrength1;
                        studentData.setStrength1(strength1);
                    }
                    s = json.getString("weaknesses");
                    if (s.equals("found")) {
                        found_weaknesses = 1;
                        String weaknessesdataobject = json.getString("weaknessesobj");

                        MyProfileWeaknessesModal obj2 = (MyProfileWeaknessesModal) fromString(weaknessesdataobject, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));

                        weak1 = obj2.sweak1;
                        studentData.setWeak1(weak1);
                    }

                    s = json.getString("contact_details");
                    if (s.equals("found")) {
                        found_contact_details = 1;
                    }
                    s = json.getString("personal");
                    if (s.equals("found")) {
                        found_personal = 1;
                        String personaldataobject = json.getString("personalobj");

                        MyProfilePersonal obj2 = (MyProfilePersonal) fromString(personaldataobject, MySharedPreferencesManager.getDigest1(ViewPlacement.this), MySharedPreferencesManager.getDigest2(ViewPlacement.this));

                        fname = obj2.fname;
                        lname = obj2.sname;
                        dob = obj2.dob;
                        phone = obj2.mobile;
                        hobbies = obj2.hobbies;
                        addressline1 = obj2.addrline1c;
                        addressline2 = obj2.addrline2c;
                        addressline3 = obj2.addrline3c;

                        Log.d("TAG", "doInBackground: personal - " + fname);
                        Log.d("TAG", "doInBackground: personal - " + lname);


                        studentData.setFname(fname);
                        studentData.setLname(lname);
                        studentData.setDob(dob);
                        studentData.setPhone(phone);
                        studentData.setHobbies(hobbies);
                        studentData.setLang1(lang1);
                        studentData.setAddressline1(addressline1);
                        studentData.setAddressline2(addressline2);
                        studentData.setAddressline3(addressline3);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            if (dialog.isShowing()) {
                dialog.dismiss();
                studentmarks();
            }

        }
    }

    class Getsingnature extends AsyncTask<String, String, String> {
        String signature = "";

        protected String doInBackground(String... param) {
            JSONParser jParser = new JSONParser();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            JSONObject json = jParser.makeHttpRequest(Z.load_last_updated, "GET", params);
            Log.d("TAG", "doInBackground: Getsingnature json " + json);
            try {
                signature = json.getString("lastupdated");
                ShouldAnimateProfile.photo =signature;
                Log.d("TAG", "doInBackground: ShouldAnimateProfile.photo - "+ShouldAnimateProfile.photo);
            } catch (Exception ex) {
            }
            return signature;
        }

    }
}

