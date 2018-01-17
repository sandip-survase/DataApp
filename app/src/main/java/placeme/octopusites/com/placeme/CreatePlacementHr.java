package placeme.octopusites.com.placeme;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mabbas007.tagsedittext.TagsEditText;

import static placeme.octopusites.com.placeme.AES4all.Encrypt;


public class CreatePlacementHr extends AppCompatActivity {

    //ui work
    private Toolbar toolbar;
    private TagsEditText batchesTags,expTag,instTag;
    ArrayAdapter<String> dataAdapter;
    ArrayAdapter<String> dataAdapterexp;
    ArrayAdapter<String> dataAdapterinst;
    String[] Institute;

    ArrayList<String> TagCreateList = new ArrayList<>();
    ArrayList<String> TagCreateList2 = new ArrayList<>();
    ArrayList<String> TagCreateList3 = new ArrayList<>();
    List<String> institutelist = new ArrayList<String>();

    int Institutecount=0;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;
    int errorflag = 0;
    String forwhom = "",encforwhom="";
    String paramcompanyname = "", cpackage = "", post = "", selected = "", vacancies = "", lastdateofrr = "", dateofarrival = "", bond = "", apti = "", techtest = "",
            groupdisc = "", techinterview = "", Hrinterview = "", xcriteria = "", xiicriteria = "", ugcriteria = "", pgcriteria = "";

    JSONObject json;
    JSONParser jParser = new JSONParser();
    String encUsername = "",encRole="";
    String  sbatchesTags="",sexptaTags="",sinsttaTags="";
    TextView createnotinotitxt;
    ImageView addIcon;
    View dummy6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_placement_hr);


        createnotinotitxt=(TextView)findViewById(R.id.createnotinotitxt);
        dummy6 = (View)findViewById(R.id.dummy6);
        
        createnotinotitxt.setTypeface(Z.getLight(this));
//        ui work
        toolbar = (Toolbar) findViewById(R.id.placementtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Placement");


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        addIcon = (ImageView)findViewById(R.id.addIcon);

        batchesTags = (TagsEditText) findViewById(R.id.batchesTags);
        batchesTags.setFocusable(false);

        expTag = (TagsEditText) findViewById(R.id.expTag);
        expTag.setFocusable(false);

        instTag = (TagsEditText) findViewById(R.id.instTag);
        instTag.setFocusable(false);

        viewPager = (ViewPager) findViewById(R.id.placementviewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.placementtabs);
        tabLayout.setupWithViewPager(viewPager);



        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.fruits)) {
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

        dataAdapterexp = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.ExpYears)) {
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


        batchesTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                batchesTags.setAdapter(dataAdapter);
                batchesTags.setThreshold(1);
                if (batchesTags.getText().toString().contains("ALL")) {
                    //dont popullate
                    Toast.makeText(CreatePlacementHr.this, "Notification will be sent to All batches", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, getResources().getStringArray(R.array.fruits)) {
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


                    batchesTags.setAdapter(dataAdapter);
                    batchesTags.showDropDown();
                }
            }
        });

        batchesTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String temp = getResources().getStringArray(R.array.fruits)[position];
                temp = temp.trim();

                if (temp.contains("ALL")) {
                    TagCreateList.clear();
                    TagCreateList.add("ALL");
                    String[] TagCreateArray = new String[TagCreateList.size()];
                    TagCreateArray = TagCreateList.toArray(TagCreateArray);
                    batchesTags.setText("");
                    batchesTags.setTags(TagCreateArray);
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
                        batchesTags.setTags(TagCreateArray);
                    }

                }
            }

        });

        batchesTags.setTagsListener(new TagsEditText.TagsEditListener() {
            @Override
            public void onTagsChanged(Collection<String> collection) {
                TagCreateList.clear();
                List<String> newList = new ArrayList<String>(batchesTags.getTags());
                TagCreateList.addAll(newList);

                String temp = "";
                temp = batchesTags.getText().toString();
                Log.d("tag", "onTagsChanged: " + temp);
                if (temp.equals("")) {
                    batchesTags.dismissDropDown();
                }


            }

            @Override
            public void onEditingFinished() {


            }
        });

        expTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: expTag");
                expTag.setAdapter(dataAdapterexp);
                expTag.setThreshold(1);
                if (expTag.getText().toString().length()>1) {
                    //dont popullate
//                    Toast.makeText(CreatePlacementHr.this, "Notification will be sent to All batches", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, getResources().getStringArray(R.array.ExpYears)) {
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


                    expTag.setAdapter(dataAdapter);
                    expTag.showDropDown();
                }

            }
        });
        expTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("TAG", "onItemClick: expTag");
                String temp = getResources().getStringArray(R.array.ExpYears)[position];
                temp = temp.trim();

                if (temp.contains("ALL")) {
                    TagCreateList2.clear();
                    TagCreateList2.add("ALL");
                    String[] TagCreateArray = new String[TagCreateList2.size()];
                    TagCreateArray = TagCreateList2.toArray(TagCreateArray);
                    expTag.setText("");
                    expTag.setTags(TagCreateArray);
                }
                if (TagCreateList2.contains(temp)) ;
                {
                    int occurance = TagCreateList2.indexOf(temp);
                    int Lastelement = TagCreateList2.size() - 1;
                    Log.d("occurance", "onItemClick:" + occurance);
                    Log.d("Lastelement", "onItemClick:" + Lastelement);

                    if (occurance != TagCreateList2.size() - 1) {
                        Log.d("deletethis", "onItemClick:");
                        TagCreateList2.remove(TagCreateList2.size() - 1);
                        String[] TagCreateArray = new String[TagCreateList2.size()];
                        TagCreateArray = TagCreateList2.toArray(TagCreateArray);
                        expTag.setTags(TagCreateArray);
                    }

                }
            }

        });


        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: insttag");
                instTag.setAdapter(dataAdapterinst);
                instTag.setThreshold(1);
                if (instTag.getText().toString().contains("ALL")) {
                    //dont popullate
                    Toast.makeText(CreatePlacementHr.this, "Notification will be sent to All batches", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, institutelist) {
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

                    instTag.setAdapter(dataAdapter);
                    instTag.showDropDown();
                }


            }
        });



        instTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: insttag");
                instTag.setAdapter(dataAdapterinst);
                instTag.setThreshold(1);
                if (instTag.getText().toString().contains("ALL")) {
                    //dont popullate
                    Toast.makeText(CreatePlacementHr.this, "Notification will be sent to All batches", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, institutelist) {
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

                    instTag.setAdapter(dataAdapter);
                    instTag.showDropDown();
                }


            }
        });

        instTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                String temp = institutelist.get(position);
                Log.d("TAG", "onItemClick: insttag temp"+temp);

                temp = temp.trim();

                if (temp.contains("ALL")) {
                    TagCreateList3.clear();
                    TagCreateList3.add("ALL");
                    String[] TagCreateArray = new String[TagCreateList3.size()];
                    TagCreateArray = TagCreateList3.toArray(TagCreateArray);
                    instTag.setText("");
                    instTag.setTags(TagCreateArray);
                }
                if (TagCreateList3.contains(temp)) ;
                {
                    Log.d("occurance", "onItemClick: temp" + temp);
                    int occurance = TagCreateList3.indexOf(temp);
                    int Lastelement = TagCreateList3.size() - 1;
                    Log.d("occurance", "onItemClick:" + occurance);
                    Log.d("Lastelement", "onItemClick:" + Lastelement);

                    if (occurance != TagCreateList3.size() - 1) {
                        Log.d("deletethis", "onItemClick:");
                        TagCreateList3.remove(TagCreateList3.size() - 1);
                        //foer loop

                        String[] TagCreateArray = new String[TagCreateList3.size()];
                        TagCreateArray = TagCreateList3.toArray(TagCreateArray);

                        Log.d("occurance", "TagCreateArray: " + TagCreateArray[0]);

//                        String arr[] =  temp.split(",");
//                        Log.d("occurance", "onItemClick: arr[0]" + arr[0]);
//                        Log.d("occurance", "onItemClick: arr[1]" + arr[1]);
//                        temp =arr[0];
                        instTag.setTags(TagCreateArray);


                    }

                }
                dummy6.requestFocus();
            }


        });

        instTag.setTagsListener(new TagsEditText.TagsEditListener() {
            @Override
            public void onTagsChanged(Collection<String> collection) {
                TagCreateList3.clear();
                List<String> newList = new ArrayList<String>(instTag.getTags());
                TagCreateList3.addAll(newList);

                String temp = "";
                temp = instTag.getText().toString();
                Log.d("tag", "onTagsChanged: onItemClick" + temp);
                if (temp.equals("")) {
                    instTag.dismissDropDown();
                }
            }

            @Override
            public void onEditingFinished() {


            }
        });


        new GetInstitute().execute();

    }

    class GetInstitute extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();

            json = jParser.makeHttpRequest(Z.url_getinstitute, "GET", params);
            try {
                String s = json.getString("count");
                Institutecount=Integer.parseInt(s);
                Institute=new String[Institutecount];
                for(int i=0;i<Institutecount;i++)
                {

                    Institute[i]=json.getString("institute"+i);
                }

            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            institutelist.clear();
            institutelist.add("ALL");
//            institutelist.add("- Select University -");
            for(int i=0;i<Institutecount;i++)
            {
                institutelist.add(Institute[i]);
            }
            populateInstitute();
        }
    }


    void populateInstitute() {
        ArrayAdapter<String> dataAdapterinst = new ArrayAdapter<String>(this, R.layout.spinner_item_long, institutelist) {
            @Override
            public boolean isEnabled(int position) {

                if (position == 0) {

                    return false;
                } else {
                    return true;
                }
            }

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
        ;
        instTag.setAdapter(dataAdapterinst);
    }


    private void setupViewPager(ViewPager viewPager) {

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlacementCreateTab1(), "Job Description");
        adapter.addFragment(new PlacementCreateTab2(), "Selection Process");
        adapter.addFragment(new PlacementCreateTab3(), "Selection Criteria");
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
        getMenuInflater().inflate(R.menu.admin_create, menu);
        return super.onCreateOptionsMenu(menu);
    }

    void validate() {
        try {

             sbatchesTags = batchesTags.getText().toString();
             sexptaTags = expTag.getText().toString();
            sinsttaTags = instTag.getText().toString();
            Log.d("gettingtabData", "sbatchesTags: "+sbatchesTags);
            Log.d("gettingtabData", "sexptaTags: "+sexptaTags);
            Log.d("gettingtabData", "sinsttaTags: "+sinsttaTags);


            if(sbatchesTags.length()<=2){
                batchesTags.setError("enter passing Year ");
            }else if(sexptaTags.length()<=2){

                expTag.setError("enter passing Year ");
            }
            else if(sinsttaTags.length()<=2){

                instTag.setError("select institute name ");
            }
            else{

                forwhom="PLACEME(ALL)";
                Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

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

                            Toast.makeText(this, "Tab1 & Tab2 & tab3 OK", Toast.LENGTH_SHORT).show();
                            //call ENCRYPT ND SAVE save method
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


    void encrypt() {
        try {
            PlacementCreateTab1 PlaceTab1 = (PlacementCreateTab1) adapter.getItem(0);
            PlacementCreateTab2 PlaceTab2 = (PlacementCreateTab2) adapter.getItem(1);
            PlacementCreateTab3 PlaceTab3 = (PlacementCreateTab3) adapter.getItem(2);

            Log.d("gettingtabData", "encforwhom: " + encforwhom);

//            String encparamcompanyname="";
//            byte[] paramcompanynameBytes = srole.getBytes("UTF-8");
//            byte[] paramcompanynameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, paramcompanynameBytes);
//            encparamcompanyname=new String(SimpleBase64Encoder.encode(paramcompanynameEncryptedBytes));

            paramcompanyname = PlaceTab1.companyname.getText().toString();
            cpackage = PlaceTab1.cpackage.getText().toString();
            post = PlaceTab1.post.getText().toString();
            selected = PlaceTab1.selected.getText().toString();
            vacancies = PlaceTab1.vacancies.getText().toString();
            lastdateofrr = PlaceTab1.lastdateofrr.getText().toString();
            dateofarrival = PlaceTab1.dateofarrival.getText().toString();
            bond = PlaceTab1.bond.getText().toString();

            //appendin Experiences and


            Log.d("gettingtabData", "encrypt: " + paramcompanyname);
            Log.d("gettingtabData", "cpackage: " + cpackage);
            Log.d("gettingtabData", "post: " + post);
            Log.d("gettingtabData", "selected: " + selected);
            Log.d("gettingtabData", "vacancies: " + vacancies);
            Log.d("gettingtabData", "lastdateofrr: " + lastdateofrr);
            Log.d("gettingtabData", "dateofarrival: " + dateofarrival);
            Log.d("gettingtabData", "bond: " + bond);

            apti = PlaceTab2.apti.getText().toString();
            techtest = PlaceTab2.techtest.getText().toString();
            groupdisc = PlaceTab2.groupdisc.getText().toString();
            techinterview = PlaceTab2.techinterview.getText().toString();
            Hrinterview = PlaceTab2.Hrinterview.getText().toString();

            Log.d("gettingtabData", "apti: " + apti);
            Log.d("gettingtabData", "techtest: " + techtest);
            Log.d("gettingtabData", "groupdisc: " + groupdisc);
            Log.d("gettingtabData", "techinterview: " + techinterview);
            Log.d("gettingtabData", "Hrinterview: " + Hrinterview);

//
            xcriteria = PlaceTab3.xcriteria.getText().toString();
            xiicriteria = PlaceTab3.xiicriteria.getText().toString();
            ugcriteria = PlaceTab3.ugcriteria.getText().toString();
            pgcriteria = PlaceTab3.pgcriteria.getText().toString();

            Log.d("gettingtabData", "xcriteria: " + xcriteria);
            Log.d("gettingtabData", "xcritexiicriteriaria: " + xiicriteria);
            Log.d("gettingtabData", "ugcriteria: " + ugcriteria);
            Log.d("gettingtabData", "pgcriteria: " + pgcriteria);

            encRole = Encrypt(MySharedPreferencesManager.getRole(this), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));
            encUsername =MySharedPreferencesManager.getUsername(this);

            encforwhom = Encrypt(forwhom, MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));

            new save().execute();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    class save extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... param) {
            String encinst="";

            try {
                encinst = Z.Encrypt(sinsttaTags,CreatePlacementHr.this);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("a", encRole));   //1
            params.add(new BasicNameValuePair("b", encforwhom));       //2
            params.add(new BasicNameValuePair("c", paramcompanyname));       //3
            params.add(new BasicNameValuePair("d", cpackage));       //4
            params.add(new BasicNameValuePair("e", post));     //5
            params.add(new BasicNameValuePair("f", selected));     //6
            params.add(new BasicNameValuePair("g", vacancies));     //7
            params.add(new BasicNameValuePair("h", lastdateofrr));     //8
            params.add(new BasicNameValuePair("i", dateofarrival));     //9
            params.add(new BasicNameValuePair("j", bond));     //10
            params.add(new BasicNameValuePair("k", apti));     //11
            params.add(new BasicNameValuePair("l", techtest));     //12
            params.add(new BasicNameValuePair("m", groupdisc));     //13
            params.add(new BasicNameValuePair("n", techinterview));     //14
            params.add(new BasicNameValuePair("o", Hrinterview));     //15
            params.add(new BasicNameValuePair("p", xcriteria));     //16
            params.add(new BasicNameValuePair("q", xiicriteria));     //17
            params.add(new BasicNameValuePair("r", ugcriteria));     //18
            params.add(new BasicNameValuePair("s", pgcriteria));     //19
            params.add(new BasicNameValuePair("t", sbatchesTags));     //20
            params.add(new BasicNameValuePair("v", sexptaTags));     //21
            params.add(new BasicNameValuePair("w",encinst));     //22

            json = jParser.makeHttpRequest(Z.url_CreatePlacementsHr, "GET", params);
            try {
                r = json.getString("info1");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }




        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(CreatePlacementHr.this, result, Toast.LENGTH_SHORT).show();

//            CreatePlacementHr.super.onBackPressed();

//            if(result.equals("success"))
//            {
//                Toast.makeText(CreateNotification.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();
//
////                Intent returnIntent = new Intent();
////                returnIntent.putExtra("result", result);
////                if(edittedFlag==1){
////                    setResult(111);
////                }
//                CreateNotification.super.onBackPressed();
//            }
//            else {
//                Toast.makeText(CreateNotification.this,result,Toast.LENGTH_SHORT).show();
//
//            }
        }
    }

}
