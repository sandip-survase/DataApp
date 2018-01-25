package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import mabbas007.tagsedittext.TagsEditText;
import placeme.octopusites.com.placeme.modal.RecyclerItemPlacement;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;


public class EditPlacementMain extends AppCompatActivity {

    boolean showPop = false;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;
    CheckBox CheckBoxstudent, CheckBoxsAlumni;
    private TagsEditText batchesTagsedittext;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    String Forwhomefromdb = "";

    RelativeLayout allumiselector;
    int forstudflag = 0, forallumflag = 0;
    int edittedFlag = 0, containsall = 0;
    final List<String> yearList = new ArrayList<String>();

    ArrayAdapter<String> dataAdapter;
    ArrayList<String> TagCreateList = new ArrayList<>();
    String encUsername = "", encforwhom = "", encRole = "";
    int errorflag = 0;
    String forwhom = "";
    String username = "", srole = "", instname = "";
    String digest1, digest2;

    String role;
    String sid, scompanyname, spackage, spost, sforwhichcourse, sforwhichstream, svacancies, slastdateofregistration, sdateofarrival, sbond, snoofapti;
    String snooftechtest, snoofgd, snoofti, snoofhri, sstdx, sstdxiiordiploma, sug, spg, suploadtime, slastmodified, suploadedby, sforwhom = "", snoofallowedliveatkt, snoofalloweddeadatkt;
    //
    boolean newCreatedPlacements=false;


//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_placement_main);

        toolbar = (Toolbar) findViewById(R.id.placementtoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Placement");

        role = MySharedPreferencesManager.getRole(this);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        role = MySharedPreferencesManager.getRole(this);
        allumiselector = (RelativeLayout) findViewById(R.id.allumiselector);
        batchesTagsedittext = (TagsEditText) findViewById(R.id.batchesTags);

        viewPager = (ViewPager) findViewById(R.id.placementviewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.placementtabs);
        tabLayout.setupWithViewPager(viewPager);


        TextView createnotinotitxt = (TextView) findViewById(R.id.createnotinotitxt);

        createnotinotitxt.setTypeface(Z.getBold(this));
        if (role.equals("hr")) {
            createnotinotitxt.setVisibility(View.GONE);
            CheckBox CheckBoxstudent = (CheckBox) findViewById(R.id.CheckBoxstudent);
            CheckBox CheckBoxsAlumni = (CheckBox) findViewById(R.id.CheckBoxsAlumni);
            CheckBoxstudent.setVisibility(View.GONE);
            CheckBoxsAlumni.setVisibility(View.GONE);

        }
        allumiselector = (RelativeLayout) findViewById(R.id.allumiselector);


        batchesTagsedittext = (TagsEditText) findViewById(R.id.batchesTags);
        batchesTagsedittext.setFocusable(false);
        batchesTagsedittext.setHint("Enter the Batches");
        RecyclerItemPlacement obj = (RecyclerItemPlacement) getIntent().getParcelableExtra("object");


        srole = MySharedPreferencesManager.getRole(this);
        instname = MySharedPreferencesManager.getInstitute(this);
        encUsername = MySharedPreferencesManager.getUsername(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        sid = getIntent().getStringExtra("id");
//        Log.d("TAG", "id: "+obj.getId());
//        scompanyname=getIntent().getStringExtra("companyname");
//        spackage=getIntent().getStringExtra("package");
//        spost=getIntent().getStringExtra("post");
//        sforwhichcourse=getIntent().getStringExtra("forwhichcourse");
//        sforwhichstream=getIntent().getStringExtra("forwhichstream");
//        svacancies=getIntent().getStringExtra("vacancies");
//        slastdateofregistration=getIntent().getStringExtra("lastdateofregistration");
//        sdateofarrival=getIntent().getStringExtra("dateofarrival");
//        sbond=getIntent().getStringExtra("bond");
//        snoofapti=getIntent().getStringExtra("noofapti");
//        snooftechtest=getIntent().getStringExtra("nooftechtest");
//        snoofgd =getIntent().getStringExtra("noofgd");
//        snoofti=getIntent().getStringExtra("noofti");
//        snoofhri=getIntent().getStringExtra("noofhri");
//        sstdx=getIntent().getStringExtra("stdx");
//        sstdxiiordiploma=getIntent().getStringExtra("stdxiiordiploma");
//        sug=getIntent().getStringExtra("ugc");
//        spg=getIntent().getStringExtra("pgc");
//        suploadtime=getIntent().getStringExtra("uploadtime");
//        suploadedby=getIntent().getStringExtra("uploadedby");
//        sforwhom=getIntent().getStringExtra("forwhome");
//        slastmodified=getIntent().getStringExtra("lastmodified");
//        snoofallowedliveatkt=getIntent().getStringExtra("noofallowedliveatkt");
//        snoofalloweddeadatkt=getIntent().getStringExtra("noofalloweddeadatkt");
//
//        Log.d("onCreate", "placementsid: "+sid);
//        Log.d("onCreate", "scompanyname: "+scompanyname);
//        Log.d("onCreate", "spackage: "+spackage);
//        Log.d("onCreate", "spost: "+spost);
//        Log.d("onCreate", "sforwhom: "+sforwhom);
//        Log.d("onCreate", "slastdateofregistration: "+slastdateofregistration);
        CheckBoxstudent = (CheckBox) findViewById(R.id.CheckBoxstudent);
        CheckBoxsAlumni = (CheckBox) findViewById(R.id.CheckBoxsAlumni);
        new GetForwhome().execute();


        CheckBoxstudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    forstudflag = 1;
                    edittedFlag = 1;
                } else {
                    forstudflag = 0;
                    edittedFlag = 1;
                }
            }
        });
        CheckBoxsAlumni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showPop = false;
                    allumiselector.setVisibility(View.VISIBLE);
//                    batches.setVisibility(View.VISIBLE);
                    forallumflag = 1;
//                    yearspinner.setVisibility(View.VISIBLE);
//                    batches.setVisibility(View.VISIBLE);
                    batchesTagsedittext.setVisibility(View.VISIBLE);
                    edittedFlag = 1;
                } else {
                    forallumflag = 0;
                    batchesTagsedittext.dismissDropDown();
                    allumiselector.setVisibility(View.GONE);
                    batchesTagsedittext.setText("");
                    edittedFlag = 1;
                }
            }
        });

        yearList.add("ALL");
        Calendar currentCalendar = Calendar.getInstance();
        for (int i = currentCalendar.get(Calendar.YEAR) - 1; i >= 2000; i--)
            yearList.add("" + i);

//        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.fruits)) {
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, yearList) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/abz.ttf");
                tv.setTypeface(custom_font3);

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.parseColor("#eeeeee"));
                }
                return view;
            }
        };


        batchesTagsedittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                batchesTagsedittext.setAdapter(dataAdapter);
                batchesTagsedittext.setThreshold(1);

                if (showPop == false) {
                    if (batchesTagsedittext.getText().toString().contains("ALL")) {
                        //dont popullate
                        Toast.makeText(EditPlacementMain.this, "Notification will be sent to All batches", Toast.LENGTH_SHORT).show();
                    } else {
//                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, getResources().getStringArray(R.array.fruits)) {
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, yearList) {
                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/abz.ttf");
                                tv.setTypeface(custom_font3);

                                if (position == 0) {
                                    // Set the hint text color gray
                                    tv.setTextColor(Color.GRAY);
                                } else {
                                    tv.setTextColor(Color.parseColor("#eeeeee"));
                                }
                                return view;
                            }
                        };
                        batchesTagsedittext.setAdapter(dataAdapter);
                        batchesTagsedittext.showDropDown();
                    }
                }
            }
        });

        batchesTagsedittext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                String temp = getResources().getStringArray(R.array.fruits)[position];
                String temp = yearList.get(position);
                temp = temp.trim();

                if (temp.contains("ALL")) {
                    TagCreateList.clear();
                    TagCreateList.add("ALL");
                    String[] TagCreateArray = new String[TagCreateList.size()];
                    TagCreateArray = TagCreateList.toArray(TagCreateArray);
                    batchesTagsedittext.setText("");
                    batchesTagsedittext.setTags(TagCreateArray);
                }
                if (TagCreateList.contains(temp)) ;
                {
                    int occurance = TagCreateList.indexOf(temp);
                    int Lastelement = TagCreateList.size() - 1;
                    Log.d("occurance", "onItemClick:" + occurance);
                    Log.d("Lastelement", "onItemClick:" + Lastelement);

                    if (occurance != TagCreateList.size() - 1) {

                        Log.d("deletethis", "onItemClick:");
                        TagCreateList.remove(TagCreateList.size() - 1);
                        String[] TagCreateArray = new String[TagCreateList.size()];
                        TagCreateArray = TagCreateList.toArray(TagCreateArray);
                        batchesTagsedittext.setTags(TagCreateArray);



                        Toast.makeText(EditPlacementMain.this, "Batch " + temp + " is already present", Toast.LENGTH_SHORT).show();


                    }

                }
            }

        });

        batchesTagsedittext.setTagsListener(new TagsEditText.TagsEditListener() {
            @Override
            public void onTagsChanged(Collection<String> collection) {
                TagCreateList.clear();
                List<String> newList = new ArrayList<String>(batchesTagsedittext.getTags());
                TagCreateList.addAll(newList);
                Log.d("setTagsListener", "onEditingFinished: " + containsall);
                String temp = "";
                temp = batchesTagsedittext.getText().toString();
                Log.d("tag", "onTagsChanged: " + temp);
                if (temp.equals("")) {
                    batchesTagsedittext.dismissDropDown();
                    allumiselector.setVisibility(View.GONE);
                    CheckBoxsAlumni.setChecked(false);
                    showPop = true;
                }

                Log.d("TAG", "onTagsChanged: showPop " + showPop);
            }

            @Override
            public void onEditingFinished() {


            }
        });

        PlacementCreateTab1 PlaceTab1 = (PlacementCreateTab1) adapter.getItem(0);
        PlacementCreateTab2 PlaceTab2 = (PlacementCreateTab2) adapter.getItem(1);
        PlacementCreateTab3 PlaceTab3 = (PlacementCreateTab3) adapter.getItem(2);
        viewPager.setOffscreenPageLimit(3);

//            PlaceTab1.datasetters(obj);               //sending Object
//        PlaceTab1.datasetters(obj);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:
                validate();
                break;

            case android.R.id.home:

                onBackPressed();

                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_edit, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public void onBackPressed() {
        PlacementCreateTab1 PlaceTab1 = (PlacementCreateTab1) adapter.getItem(0);
        PlacementCreateTab2 PlaceTab2 = (PlacementCreateTab2) adapter.getItem(1);
        PlacementCreateTab3 PlaceTab3 = (PlacementCreateTab3) adapter.getItem(2);

        boolean tab1 = PlaceTab1.isTabEditted();
        boolean tab2 = PlaceTab2.isTabEditted();
        boolean tab3 = PlaceTab3.isTabEditted();

        if (edittedFlag == 1 || tab1 || tab2 || tab3) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    setResult(299);
                                    EditPlacementMain.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(EditPlacementMain.this));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(EditPlacementMain.this));
                }
            });

            alertDialog.show();
        } else{
            if(newCreatedPlacements){
                setResult(AdminActivity.ADMIN_CREATE_DATA_CHANGE_RESULT_CODE);
            }else{
                setResult(299);
            }
            super.onBackPressed();
        }

    }

    private void setupViewPager(ViewPager viewPager) {

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlacementCreateTab1(), "Job Description");
        adapter.addFragment(new PlacementCreateTab2(), "Selection Process");
        adapter.addFragment(new PlacementCreateTab3(), "Selection Criteria");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
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


    class GetForwhome extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", sid)); //0

            json = jParser.makeHttpRequest(Z.url_GetForWhomePlacements, "GET", params);
            try {
                Forwhomefromdb = json.getString("forwhom");
                Log.d("Forwhomefromdb", "onPostExecute: " + Forwhomefromdb);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            try {

                if (!Forwhomefromdb.equals("")) {
                   Forwhomefromdb = Z.Decrypt(Forwhomefromdb, EditPlacementMain.this);
                    Log.d("Forwhomefromdb", "onPostExecute: " + Forwhomefromdb);
                }

                String tempu = Z.Decrypt(encUsername,EditPlacementMain.this);
                Log.d("Forwhomefromdb", "tempu: " + tempu);


                if (Forwhomefromdb.contains(tempu)) {
                    Forwhomefromdb = Forwhomefromdb.replace(tempu, "");
                }
                Log.d("Forwhomefromdb", "after: " + Forwhomefromdb);

                if (Forwhomefromdb.contains("STUDENT")) {
                    CheckBoxstudent.setChecked(true);
                }
                if (Forwhomefromdb.contains("ALL")) {
                    CheckBoxsAlumni.setChecked(true);
                    batchesTagsedittext.setText("ALL");
                } else {


                }


                int index1 = Forwhomefromdb.indexOf("(");
                int index2 = Forwhomefromdb.indexOf(")");
                String whomsYears = "";
                for (int i = index1 + 1; i < index2; i++) {
                    whomsYears += Forwhomefromdb.charAt(i);
                }

                //k
                String str = whomsYears;
                Log.d("kun", "onPostExecute: str " + str);
                str = str.replaceAll("[^-?0-9]+", " ");
                Log.d("kun", "onPostExecute: " + Arrays.asList(str.trim().split(" ")));
                Log.d("kun", str);
                String batchyears[] = str.split(" ");

                Log.d("kun", "after : " + str);

                if (str.length() >= 2) {
                    CheckBoxsAlumni.setChecked(true);
                    batchesTagsedittext.setTags(batchyears);
                    showPop = true;

                }
                //k


//                String testStr="STUDENT";
                String testStr = "STUDENT";
                Log.d("TAG1", "before: " + whomsYears);
                whomsYears = whomsYears.replace(Z.Decrypt(encUsername, EditPlacementMain.this), "");
                Log.d("TAG1", "after1: " + whomsYears);
                whomsYears = whomsYears.replace("STUDENT,", "");
                Log.d("TAG1", "after2: " + whomsYears);
                whomsYears = whomsYears.replace("STUDENT", "");
                whomsYears = whomsYears.replace(",STUDENT", "");

                Log.d("TAG1", "after4: " + whomsYears);
                whomsYears = whomsYears.replace("ALL", "");


            } catch (Exception e) {

                Log.d("whomsYears e:", e.getMessage());
                Toast.makeText(EditPlacementMain.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            edittedFlag = 0;
        }
    }

    void validate() {
        try {
            errorflag = 0;

            String selectedBatchesForWhome = android.text.TextUtils.join(",", TagCreateList);
            Log.d("Forwhome", "validate: " + selectedBatchesForWhome);

//        if (batchesTags.length() < 2){
////            a.setInstitute(instname);
//            Toast.makeText(CreatePlacement.this, "Select Student Or Alumni to send This Placement ", Toast.LENGTH_SHORT).show();
//            errorflag = 1;
//        }

            if (forstudflag == 0 && forallumflag == 0) {
                Toast.makeText(EditPlacementMain.this, "Select Student Or Alumni to send This Placement ", Toast.LENGTH_SHORT).show();
                errorflag = 1;
            } else {


                if (forstudflag == 1) {
                    //notification for Student
                    forwhom = MySharedPreferencesManager.getData(EditPlacementMain.this,"ucode") + "(" + Decrypt(encUsername, digest1, digest2) + ",STUDENT";                  //for testing  purpose ADMIN IS sTUDENT
                    if (forallumflag == 1) {
                        //for Stud + alumni
                        forwhom = forwhom + "," + selectedBatchesForWhome + ")";
                        Log.d("forwhomeStringAppend", "onCreate: " + forwhom);
                    } else {
                        //only for Stud
                        forwhom = forwhom + ")";
                        Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                    }
                } else {
                    //notification not for Student
                    if (forallumflag == 1) {
                        //for ALLUMNI
                        forwhom = MySharedPreferencesManager.getData(EditPlacementMain.this,"ucode") + "(" + Decrypt(encUsername, digest1, digest2) + " ," + selectedBatchesForWhome + ")";
                        Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                    } else {
                        //NOTIFICATION FOR NONE
                        forwhom = forwhom + "(NONE)";
                        Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                    }
                }

                PlacementCreateTab1 PlaceTab1 = (PlacementCreateTab1) adapter.getItem(0);
                PlacementCreateTab2 PlaceTab2 = (PlacementCreateTab2) adapter.getItem(1);
                PlacementCreateTab3 PlaceTab3 = (PlacementCreateTab3) adapter.getItem(2);
                viewPager.setOffscreenPageLimit(3);

                Boolean PlaceTab1_success = true;
                Boolean PlaceTab2_success = true;
                Boolean PlaceTab3_success = true;

                PlaceTab1_success = PlaceTab1.Validate();


                if (!PlaceTab1_success) {
                    viewPager.setCurrentItem(0);
                    PlaceTab1.Validate();
//            personalflag = 1;
                } else {
                    PlaceTab2_success = PlaceTab2.validate();
                    if (!PlaceTab2_success) {
                        viewPager.setCurrentItem(1);
                        PlaceTab2.validate();
                    } else {
                        PlaceTab3_success = PlaceTab3.validate();

                        if (!PlaceTab3_success) {
                            viewPager.setCurrentItem(3);
                            PlaceTab3.validate();
                        } else {

                            encrypt();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    class save extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... param) {


            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("a", encRole));   //1
            params.add(new BasicNameValuePair("b", encforwhom));       //2
            params.add(new BasicNameValuePair("c", scompanyname));       //3
            params.add(new BasicNameValuePair("d", spackage));       //4
            params.add(new BasicNameValuePair("e", spost));     //5
            params.add(new BasicNameValuePair("f", sforwhichcourse));     //6
            params.add(new BasicNameValuePair("g", svacancies));     //7
            params.add(new BasicNameValuePair("h", slastdateofregistration));     //8
            params.add(new BasicNameValuePair("i", sdateofarrival));     //9
            params.add(new BasicNameValuePair("j", sbond));     //10
            params.add(new BasicNameValuePair("k", snoofapti));     //11
            params.add(new BasicNameValuePair("l", snooftechtest));     //12
            params.add(new BasicNameValuePair("m", snoofgd));     //13
            params.add(new BasicNameValuePair("n", snoofti));     //14
            params.add(new BasicNameValuePair("o", snoofhri));     //15
            params.add(new BasicNameValuePair("p", sstdx));     //16
            params.add(new BasicNameValuePair("q", sstdxiiordiploma));     //17
            params.add(new BasicNameValuePair("r", sug));     //18
            params.add(new BasicNameValuePair("s", spg));     //19
            params.add(new BasicNameValuePair("id", sid));    //20


            json = jParser.makeHttpRequest(Z.url_ModifyPlacement, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(EditPlacementMain.this,result,Toast.LENGTH_SHORT).show();
            setResult(AdminActivity.ADMIN_CREATE_DATA_CHANGE_RESULT_CODE);
            newCreatedPlacements=true;

            EditPlacementMain.super.onBackPressed();


        }
    }

    void encrypt() {
        try {
            PlacementCreateTab1 PlaceTab1 = (PlacementCreateTab1) adapter.getItem(0);
            PlacementCreateTab2 PlaceTab2 = (PlacementCreateTab2) adapter.getItem(1);
            PlacementCreateTab3 PlaceTab3 = (PlacementCreateTab3) adapter.getItem(2);

//             String temppppp= PlaceTab1.courses.getText().toString();

            byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
            String sPadding = "ISO10126Padding";

            byte[] roleBytes = srole.getBytes("UTF-8");
            byte[] forwhomyBytes = forwhom.getBytes("UTF-8");
            byte[] roleEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, roleBytes);
            encRole = new String(SimpleBase64Encoder.encode(roleEncryptedBytes));

            byte[] forwhomyEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, forwhomyBytes);
            encforwhom = new String(SimpleBase64Encoder.encode(forwhomyEncryptedBytes));
            Log.d("gettingtabData", "encRole: " + encRole);
            Log.d("gettingtabData", "encforwhom: " + encforwhom);


            String encparamcompanyname = "";


            sid = getIntent().getStringExtra("id");

            scompanyname = PlaceTab1.companyname.getText().toString();
            byte[] paramcompanynameBytes = srole.getBytes("UTF-8");
            byte[] paramcompanynameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, paramcompanynameBytes);
            encparamcompanyname = new String(SimpleBase64Encoder.encode(paramcompanynameEncryptedBytes));

            spackage = PlaceTab1.cpackage.getText().toString();
            spackage = spackage.replace("LPA", "");
            spost = PlaceTab1.post.getText().toString();
            sforwhichcourse = PlaceTab1.selected.getText().toString();
            svacancies = PlaceTab1.vacancies.getText().toString();
            slastdateofregistration = PlaceTab1.lastdateofrr.getText().toString();
            sdateofarrival = PlaceTab1.dateofarrival.getText().toString();
            sbond = PlaceTab1.bond.getText().toString();

            Log.d("gettingtabData", "ids: " + sid);
            Log.d("gettingtabData", "encUsername: " + encUsername);
            Log.d("gettingtabData", "company: " + scompanyname);
            Log.d("gettingtabData", "cpackage: " + spackage);
            Log.d("gettingtabData", "post: " + spost);
            Log.d("gettingtabData", "selected: " + sforwhichcourse);
            Log.d("gettingtabData", "vacancies: " + svacancies);
            Log.d("gettingtabData", "lastdateofrr: " + slastdateofregistration);
            Log.d("gettingtabData", "dateofarrival: " + sdateofarrival);
            Log.d("gettingtabData", "bond: " + sbond);

            snoofapti = PlaceTab2.apti.getText().toString();
            snooftechtest = PlaceTab2.techtest.getText().toString();
            snoofgd = PlaceTab2.groupdisc.getText().toString();
            snoofti = PlaceTab2.techinterview.getText().toString();
            snoofhri = PlaceTab2.Hrinterview.getText().toString();
            Log.d("gettingtabData", "apti: " + snoofapti);
            Log.d("gettingtabData", "techtest: " + snooftechtest);
            Log.d("gettingtabData", "groupdisc: " + snoofgd);
            Log.d("gettingtabData", "techinterview: " + snoofti);
            Log.d("gettingtabData", "Hrinterview: " + snoofhri);

//
            sstdx = PlaceTab3.xcriteria.getText().toString();
            sstdxiiordiploma = PlaceTab3.xiicriteria.getText().toString();
            sug = PlaceTab3.ugcriteria.getText().toString();
            spg = PlaceTab3.pgcriteria.getText().toString();

            Log.d("gettingtabData", "xcriteria: " + sstdx);
            Log.d("gettingtabData", "xcritexiicriteriaria: " + sstdxiiordiploma);
            Log.d("gettingtabData", "ugcriteria: " + sug);
            Log.d("gettingtabData", "pgcriteria: " + spg);


            new save().execute();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

}
