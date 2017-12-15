package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;


public class ViewPlacement extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    JSONObject json;
    JSONParser jParser = new JSONParser();
    private static String url_registerforplacement = "http://192.168.100.100/AESTest/RegisterForPlacement";
    private static String url_getstudentmarksinfo = "http://192.168.100.100/AESTest/GetStudentMarksInfo";
    String username,resultofop;
    String id,companyname,cpackage,post,forwhichcourse,forwhichstream,vacancies,lastdateofregistration,dateofarrival,bond,noofapti,nooftechtest,noofgd,noofti,noofhri,stdx,stdxiiordiploma,ug,pg,uploadtime,lastmodified,uploadedby,noofallowedliveatkt,noofalloweddeadatkt,studenttenthmarks,studenttwelthordiplomamarks,studentugmarks,studentpgmarks;
    Button registerbutton;
    ProgressBar progressBar;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String digest1,digest2;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_placement);

        toolbar = (Toolbar) findViewById(R.id.placementtoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Placement");

        viewPager = (ViewPager) findViewById(R.id.placementviewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.placementtabs);
        tabLayout.setupWithViewPager(viewPager);

        sharedpreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);
        digest1=sharedpreferences.getString("digest1",null);
        digest2=sharedpreferences.getString("digest2",null);

        String uploadedby_enc=getIntent().getStringExtra("uploadedby");
        try
        {
            demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            demoIVBytes = SimpleBase64Encoder.decode(digest2);
            sPadding = "ISO10126Padding";

            byte[] demo1EncryptedBytes1=SimpleBase64Encoder.decode(uploadedby_enc);

            byte[] demo1DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes1);

            uploadedby=new String(demo1DecryptedBytes1);


        }catch (Exception e){}

        registerbutton=(Button)findViewById(R.id.registerforplacementbutton);
        progressBar=(ProgressBar)findViewById(R.id.registerforplacementprogress);


        id=getIntent().getStringExtra("id");
        companyname=getIntent().getStringExtra("companyname");
        cpackage=getIntent().getStringExtra("package");
        post=getIntent().getStringExtra("post");
        forwhichcourse=getIntent().getStringExtra("forwhichcourse");
        forwhichstream=getIntent().getStringExtra("forwhichstream");
        vacancies=getIntent().getStringExtra("vacancies");
        lastdateofregistration=getIntent().getStringExtra("lastdateofregistration");
        dateofarrival=getIntent().getStringExtra("dateofarrival");
        bond=getIntent().getStringExtra("bond");
        noofapti=getIntent().getStringExtra("noofapti");
        nooftechtest=getIntent().getStringExtra("nooftechtest");
        noofgd=getIntent().getStringExtra("noofgd");
        noofti=getIntent().getStringExtra("noofti");
        noofhri=getIntent().getStringExtra("noofhri");
        stdx=getIntent().getStringExtra("stdx");
        stdxiiordiploma=getIntent().getStringExtra("stdxiiordiploma");
        ug=getIntent().getStringExtra("ug");
        pg=getIntent().getStringExtra("pg");
        uploadtime=getIntent().getStringExtra("uploadtime");
        lastmodified=getIntent().getStringExtra("lastmodified");
        noofallowedliveatkt=getIntent().getStringExtra("noofallowedliveatkt");
        noofalloweddeadatkt=getIntent().getStringExtra("noofalloweddeadatkt");



        SavePlacementInfoForFragment save=new SavePlacementInfoForFragment();
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


        new getStudentMarksInfo().execute();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlacementTab1(), "Job Description");
        adapter.addFragment(new PlacementTab2(), "Selection Process");
        adapter.addFragment(new PlacementTab3(), "Selection Criteria");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter
    {
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
            try
            {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));
                params.add(new BasicNameValuePair("id", id));

                //get selected templte from preferences and send it as a parameter

                json = jParser.makeHttpRequest(url_registerforplacement, "GET", params);
                String s = null;
                resultofop = json.getString("info");

            }catch (Exception e){e.printStackTrace();}

            return 0;
        }
        @Override
        protected void onPostExecute(Integer result) {

            if(resultofop.equals("success"))
                Toast.makeText(ViewPlacement.this,"Successfully Registered", Toast.LENGTH_LONG).show();
            else if(resultofop.equals("already"))
                Toast.makeText(ViewPlacement.this,"Already Registered", Toast.LENGTH_LONG).show();
            else if(resultofop.equals("date"))
                Toast.makeText(ViewPlacement.this,"Registrations are closed..!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ViewPlacement.this, "Failed..!", Toast.LENGTH_LONG).show();

            registerbutton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

        }

    }
    private class getStudentMarksInfo extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... urls) {
            try
            {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));

                json = jParser.makeHttpRequest(url_getstudentmarksinfo, "GET", params);

                String s = json.getString("tenth");
                if(s.equals("found"))
                    studenttenthmarks = json.getString("tenthPercentage");
                s = json.getString("twelth");
                if(s.equals("found"))
                    studenttwelthordiplomamarks= json.getString("twelthPercentage");
                s = json.getString("diploma");
                if(s.equals("found"))
                    studenttwelthordiplomamarks=json.getString("diplomaPercentage");
                s = json.getString("ug");
                if(s.equals("found"))
                    studentugmarks=json.getString("ugPercentage");
                s = json.getString("pgsem");
                if(s.equals("found"))
                    studentpgmarks=json.getString("pgsemPercentage");
                s = json.getString("pgyear");
                if(s.equals("found"))
                    studentpgmarks=json.getString("pgsemPercentage");

            }catch (Exception e){e.printStackTrace();}

        return 0;
        }
        @Override
        protected void onPostExecute(Integer result) {
        try
            {
                demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                demoIVBytes = SimpleBase64Encoder.decode(digest2);
                sPadding = "ISO10126Padding";

                if(studenttenthmarks!=null) {
                    byte[] studenttenthmarksEncryptedBytes = SimpleBase64Encoder.decode(studenttenthmarks);
                    byte[] studenttenthmarksDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, studenttenthmarksEncryptedBytes);
                    studenttenthmarks = new String(studenttenthmarksDecryptedBytes);
                }
                else
                    studenttenthmarks="0";
                if(studenttwelthordiplomamarks!=null)
                {
                    byte[] studenttwelthordiplomamarksEncryptedBytes = SimpleBase64Encoder.decode(studenttwelthordiplomamarks);
                    byte[] studenttwelthordiplomamarksDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, studenttwelthordiplomamarksEncryptedBytes);
                    studenttwelthordiplomamarks = new String(studenttwelthordiplomamarksDecryptedBytes);
                }
                else
                    studenttwelthordiplomamarks="0";
                if(studentugmarks!=null) {
                    byte[] studentugmarksEncryptedBytes = SimpleBase64Encoder.decode(studentugmarks);
                    byte[] studentugmarksDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, studentugmarksEncryptedBytes);
                    studentugmarks = new String(studentugmarksDecryptedBytes);
                }
                else
                    studentugmarks="0";
                if(studentpgmarks!=null)
                {
                    byte[] studentpgmarksEncryptedBytes = SimpleBase64Encoder.decode(studentpgmarks);
                    byte[] studentpgmarksDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, studentpgmarksEncryptedBytes);
                    studentpgmarks = new String(studentpgmarksDecryptedBytes);
                }
                else
                    studentpgmarks="0";

                SavePlacementInfoForFragment save=new SavePlacementInfoForFragment();
                save.setStudenttenthmarks(studenttenthmarks);
                save.setStudenttwelthordiplomamarks(studenttwelthordiplomamarks);
                save.setStudentugmarks(studentugmarks);
                save.setStudentpgmarks(studentpgmarks);

                Float c10,s10,c12,s12,cu,su;
                c10= Float.parseFloat(stdx);
                c12= Float.parseFloat(stdxiiordiploma);
                cu= Float.parseFloat(ug);

                s10= Float.parseFloat(studenttenthmarks);
                s12= Float.parseFloat(studenttwelthordiplomamarks);
                su= Float.parseFloat(studentugmarks);

                int tenthflag=0,twelthordiplomaflag=0,ugflag=0;
                if(s10>=c10)
                {
                    tenthflag=1;
                }
                if(s12>=c12)
                {
                    twelthordiplomaflag=1;
                }
                if(su>=cu)
                {
                    ugflag=1;
                }
                LinearLayout registerbuttonlayout=(LinearLayout)findViewById(R.id.registerbuttonlayout);
                if(tenthflag==1&&twelthordiplomaflag==1&&ugflag==1)
                    registerbuttonlayout.setVisibility(View.VISIBLE);
                else
                    registerbuttonlayout.setVisibility(View.GONE);
                registerbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        registerbutton.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);

                        registerforPlacementTask task = new registerforPlacementTask();
                        task.execute();

                    }
                });

            }catch (Exception e){}

        }
    }
}

