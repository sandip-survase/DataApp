package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import mabbas007.tagsedittext.TagsEditText;


import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class CreateNotification extends AppCompatActivity implements TagsEditText.TagsEditListener {


    private static final String LINE_FEED = "\r\n";
    private static String url_savedata = "http://192.168.100.30/CreateNotificationTemp/UploadAttach1";
    private static String url_saveUpdated = "http://192.168.100.30/CreateNotificationTemp/ModifyNotification";
    private static String url_getforwhome = "http://192.168.100.30/CreateNotificationTemp/GetForWhomeNotification";
    //files variables
    private static String savefoleonserverURL = "http://192.168.100.30/CreateNotificationTemp/SavefileOnServer";
    //hiding ui
    TextView createnotitxt, createnotinotitxt, lastmodifiedtxt;
    ImageView trashnotification;
    RelativeLayout file1;
    EditText title, notiffication;
    CheckBox stud, allum;
    String stitle = "", snotiffication = "";
    String username = "", srole = "", plainusername = "", forwhom = "", encRole;
    String encUsername, encTitle, encNotiffication, encforwhom;
    String encfilenameparam1, encfilenameparam2, encfilenameparam3, encfilenameparam4, encfilenameparam5;
    String instname = "";
    AdminData a = new AdminData();
    int errorflag = 0;
    int attachmentcount = 0;
    int attach1 = 0, attach2 = 0, attach3 = 0, attach4 = 0, attach5 = 0;
    int forstudflag = 0, forallumflag = 0;
    TextInputLayout batches;
    RelativeLayout yearspinner;
    RelativeLayout attchrl1, attchrl2, attchrl3, attchrl4, attchrl5;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    //attachment work
    int filecounter = 0;
    int filesame = 0;
    String filePath = "";
    long lenght;
    String filename = "";
    String directory = "";
    ArrayList<String> a1;
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    LinkedHashMap<String, String> map2 = new LinkedHashMap<>();
    TextView t1, t2, t3, t4, t5, t6;
    String name = "", vallue = "", name2 = "", value2 = "";
    List<String> response = new ArrayList<String>();
    int progress, progress2, progress3, progress4, progress5;
    int data_for_progressbar, data_for_progressbar1, data_for_progressbar2, data_for_progressbar3, data_for_progressbar4;
    ProgressBar prg1, prg2, prg3, prg4, prg5;
    int completefile = 0;
    ScrollView scrollview;
    String filenameparam1 = "", filenameparam2 = "", filenameparam3 = "", filenameparam4 = "", filenameparam5 = "";
    String FLAG = "";
    String ssnotificationid = "", sstitle = "", ssnotification = "", ssfile1 = "", ssfile2 = "", ssfile3 = "", ssfile4 = "", ssfile5 = "", ssuploadedby = "", ssuploadtime = "", sslastmodified = "";
    HashMap<String, String> map3 = new HashMap();
    String File1index = "", File2index = "", File3index = "", File4index = "", File5index = "";
    String Forwhomefromdb = "";
    Drawable compleatesprogress;
    private String charset = "UTF-8";
    private String boundary;
    private HttpURLConnection httpConn;
    private OutputStream outputStream;
    private PrintWriter writer;
    private TagsEditText batchesTags;
    ArrayAdapter<String> dataAdapter;
    ArrayList<String> TagCreateList=new ArrayList<>();
    int edittedFlag=0,containsall=0;
    String digest1="",digest2="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);

        scrollview = ((ScrollView) findViewById(R.id.schroll1));



        TextView createnotitxt = (TextView) findViewById(R.id.createnotitxt);
        TextView createnotinotitxt = (TextView) findViewById(R.id.createnotinotitxt);
        TextView lastmodifiedtxt = (TextView) findViewById(R.id.lastmodifiedtxt);
        trashnotification = (ImageView) findViewById(R.id.trashnotification);
        TextView choosetxt = (TextView) findViewById(R.id.choosetxt);
        TextView attachmentstxt = (TextView) findViewById(R.id.attachmentstxt);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Create Notification");

        FLAG = getIntent().getStringExtra("flag");
        if (FLAG.equals("EditNotification")) {
            ab.setTitle("Edit Notification");
            createnotitxt.setText("Modify/Delete Notification");
            createnotinotitxt.setText("Your changes will be broadcasted only to the students and/or alumnis of your institute.");
            lastmodifiedtxt.setVisibility(View.VISIBLE);
            trashnotification.setVisibility(View.VISIBLE);

        } else if (FLAG.equals("fromAdminActivity")) {
            ab.setTitle("Create Notification");
            createnotitxt.setText("Create Notification");
            createnotinotitxt.setText("Your notification will be broadcasted only to the students and/or alumnis of your institute.");
            trashnotification.setVisibility(View.GONE);
            lastmodifiedtxt.setVisibility(View.GONE);

        }
        attchrl1 = (RelativeLayout) findViewById(R.id.file1);
        attchrl2 = (RelativeLayout) findViewById(R.id.file2);
        attchrl3 = (RelativeLayout) findViewById(R.id.file3);
        attchrl4 = (RelativeLayout) findViewById(R.id.file4);
        attchrl5 = (RelativeLayout) findViewById(R.id.file5);
        t1 = (TextView) findViewById(R.id.filename);
        t2 = (TextView) findViewById(R.id.filename2);
        t3 = (TextView) findViewById(R.id.filename3);
        t4 = (TextView) findViewById(R.id.filename4);
        t5 = (TextView) findViewById(R.id.filename5);
        final Context context = this;




        a1 = new ArrayList<>();
        prg1 = (ProgressBar) findViewById(R.id.PROGRESS_BAR);
        prg2 = (ProgressBar) findViewById(R.id.PROGRESS_BAR2);
        prg3 = (ProgressBar) findViewById(R.id.PROGRESS_BAR3);
        prg4 = (ProgressBar) findViewById(R.id.PROGRESS_BAR4);
        prg5 = (ProgressBar) findViewById(R.id.PROGRESS_BAR5);

        yearspinner = (RelativeLayout) findViewById(R.id.yearspinner);
        yearspinner.setVisibility(View.INVISIBLE);
        batches = (TextInputLayout) findViewById(R.id.batchesinput);
        batches.setVisibility(View.INVISIBLE);


        srole=MySharedPreferencesManager.getRole(this);
        instname=MySharedPreferencesManager.getInstitute(this);
        encUsername=MySharedPreferencesManager.getUsername(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        Log.d("Shardpreff", "encUsername: " + encUsername);
        Log.d("Shardpreff", "onCreate: " + instname);
        Log.d("Shardpreff", "SROLR: " + srole);



        Digest d = new Digest();
        digest1 = d.getDigest1();
        digest2 = d.getDigest2();
        if (digest1 == null || digest2 == null) {

            d.setDigest1(digest1);
            d.setDigest2(digest2);
        }


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);




        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/cabinsemibold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/maven.ttf");

        createnotitxt.setTypeface(custom_font);
        createnotinotitxt.setTypeface(custom_font2);
        choosetxt.setTypeface(custom_font2);
        attachmentstxt.setTypeface(custom_font2);

        title = (EditText) findViewById(R.id.title);
        notiffication = (EditText) findViewById(R.id.notification);
        stud = (CheckBox) findViewById(R.id.CheckBoxstudent);
        allum = (CheckBox) findViewById(R.id.CheckBoxsAlumni);



        //tags
        batchesTags = (TagsEditText) findViewById(R.id.tagsEditText);
        batchesTags.setHint("Enter the Batches");
        batchesTags.setTagsListener(this);
        batchesTags.setTagsWithSpacesEnabled(true);
//        mTagsEditText.setAdapter(new ArrayAdapter<>(this,
//                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.fruits)));
        batchesTags.setThreshold(1);
        batchesTags.setTagsTextColor(R.color.blackOlive);
        batchesTags.setFocusable(false);

        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,  getResources().getStringArray(R.array.fruits))
        {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/abz.ttf");
                tv.setTypeface(custom_font3);

                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
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
                if(batchesTags.getText().toString().contains("ALL")){
                    //dont popullate
                    Toast.makeText(CreateNotification.this,"Notification will be sent to All batches", Toast.LENGTH_SHORT).show();
                }else {
                    ArrayAdapter<String>  dataAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item,  getResources().getStringArray(R.array.fruits))
                    {
                        @Override
                        public View getDropDownView(int position, View convertView,
                                                    ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);
                            TextView tv = (TextView) view;
                            Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/abz.ttf");
                            tv.setTypeface(custom_font3);

                            if(position == 0){
                                // Set the hint text color gray
                                tv.setTextColor(Color.GRAY);
                            }
                            else {
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
                String temp=getResources().getStringArray(R.array.fruits)[position];
                temp=temp.trim();

                if(temp.contains("ALL")){
                    TagCreateList.clear();
                    TagCreateList.add("ALL");
                    String[] TagCreateArray = new String[TagCreateList.size()];
                    TagCreateArray = TagCreateList.toArray(TagCreateArray);
                    batchesTags.setText("");
                    batchesTags.setTags(TagCreateArray);
                }
                if(TagCreateList.contains(temp));{
                    int occurance=   TagCreateList.indexOf(temp);
                    int Lastelement = TagCreateList.size()-1;
                    Log.d("occurance", "onItemClick:"+occurance);
                    Log.d("Lastelement", "onItemClick:"+Lastelement);

                    if(occurance!=TagCreateList.size()-1){
                        Log.d("deletethis", "onItemClick:");
                        TagCreateList.remove(TagCreateList.size()-1);
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
                Log.d("setTagsListener", "onEditingFinished: "+containsall);

                String temp="" ;
                temp= batchesTags.getText().toString();
                Log.d("tag", "onTagsChanged: "+temp);
                if(temp.equals("")){
                    batchesTags.dismissDropDown();
                    yearspinner.setVisibility(View.GONE);
                }


            }

            @Override
            public void onEditingFinished() {


            }
        });





        stud.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        allum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yearspinner.setVisibility(View.VISIBLE);
                    batches.setVisibility(View.VISIBLE);
                    forallumflag = 1;
                    yearspinner.setVisibility(View.VISIBLE);
                    batches.setVisibility(View.VISIBLE);
                    batchesTags.setVisibility(View.VISIBLE);
                    edittedFlag = 1;
                } else {
                    forallumflag = 0;
                    yearspinner.setVisibility(View.INVISIBLE);
                    batches.setVisibility(View.INVISIBLE);
                    batchesTags.setVisibility(View.GONE);
                    batchesTags.setText("");
                    edittedFlag = 1;
                }
            }
        });


        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        notiffication.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                notiffication.setError(null);
                edittedFlag = 1;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (filecounter < 5) {
                    filesame = 0;
                    new MaterialFilePicker().
                            withActivity(CreateNotification.this)
                            .withRequestCode(1)
                            .withFilter(Pattern.compile(".*\\.*$")) // Filtering files and directories by file name using regexp
                            .withFilterDirectories(false) // Set directories filterable (false by default)
                            .withHiddenFiles(true) // Show hidden files and folders
                            .start();
                } else {
                    Toast.makeText(CreateNotification.this, "FileLimit 5 ", Toast.LENGTH_LONG).show();
                }


            }
        });


        attchrl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog1();

            }

        });
        attchrl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog2();

            }
        });
        attchrl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog3();


            }
        });
        attchrl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog4();


            }
        });
        attchrl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog5();
            }
        });

//getters


        FLAG = getIntent().getStringExtra("flag");
        ssnotificationid = getIntent().getStringExtra("id");
        sstitle = getIntent().getStringExtra("title");
        ssnotification = getIntent().getStringExtra("notification");
        ssfile1 = getIntent().getStringExtra("file1");
        ssfile2 = getIntent().getStringExtra("file2");
        ssfile3 = getIntent().getStringExtra("file3");
        ssfile4 = getIntent().getStringExtra("file4");
        ssfile5 = getIntent().getStringExtra("file5");
        ssuploadedby = getIntent().getStringExtra("uploadedby");
        ssuploadtime = getIntent().getStringExtra("uploadtime");
        sslastmodified = getIntent().getStringExtra("lastmodified");
        //CALLTOFORWHOME

        try {
            Log.d("*****", "FLAG: " + FLAG);
            Log.d("*****", "id: " + ssnotificationid);


            Log.d("*****", "title: " + sstitle);
            Log.d("*****", "notification: " + ssnotification);
            Log.d("*****", "file1: " + ssfile1);
            Log.d("*****", "file2: " + ssfile2);
            Log.d("*****", "file3: " + ssfile3);
            Log.d("*****", "file4: " + ssfile4);
            Log.d("*****", "file5: " + ssfile5);
            Log.d("*****", "uploadedby: " + ssuploadedby);
            Log.d("*****", "uploadtime: " + ssuploadtime);
            Log.d("*****", "lastmodified: s" + sslastmodified);
            if (sslastmodified.length() != 0) {
                lastmodifiedtxt.setText("Last modified :" + sslastmodified);
            }
            if (sstitle.length() != 0) {
                title.setText(sstitle);
            }
            if (ssnotification.length() != 0) {
                notiffication.setText(ssnotification);
            }

            //call
            new GetForwhome().execute();
            if (ssfile1 != null) {
                if (!ssfile1.equals("null")) {
                    a1.add(ssfile1);
                    map3.put(ssfile1, "filename1");          //COLUMNNAMES this vallue is directly used in backend
                    filecounter++;
                    refresh();
                    batchesTags.dismissDropDown();

                } else {
                    ssfile1 = "";
                }

            }
            if (ssfile2 != null) {
                if (!ssfile2.equals("null")) {
                    a1.add(ssfile2);
                    map3.put(ssfile2, "filename2");

//                map2.put(filename,lenght+"");
                    filecounter++;
                    refresh();
                    batchesTags.dismissDropDown();

                } else {
                    ssfile2 = "";
                }
            }

            if (ssfile3 != null) {
                if (!ssfile3.equals("null")) {
                    a1.add(ssfile3);
                    map3.put(ssfile3, "filename3");
//                map2.put(filename,lenght+"");
                    filecounter++;
                    refresh();
                    batchesTags.dismissDropDown();

                } else {
                    ssfile3 = "";
                }
            }
            if (ssfile4 != null) {
                if (!ssfile4.equals("null")) {
                    a1.add(ssfile4);
                    map3.put(ssfile4, "filename4");
//                map2.put(filename,lenght+"");
                    filecounter++;
                    refresh();
                    batchesTags.dismissDropDown();

                } else {
                    ssfile4 = "";
                }
            }
            if (ssfile5 != null) {
                if (!ssfile5.equals("null")) {
                    a1.add(ssfile5);
                    map3.put(ssfile5, "filename5");
//                map2.put(filename,lenght+"");
                    filecounter++;
                    refresh();
                    batchesTags.dismissDropDown();

                } else {
                    ssfile5 = "";
                }
            }

            Log.d("*****", "id2222: " + ssnotificationid);
            Log.d("*****", "title: " + stitle);
            Log.d("*****", "notification: " + ssnotification);
            Log.d("*****", "file1: " + ssfile1);
            if (ssfile2 == null)
                Log.d("*****", "file2: " + "nusta null" + ssfile2);
            else
                Log.d("*****", "file2: " + "textual null" + ssfile2);

            Log.d("*****", "file3: " + ssfile3);
            Log.d("*****", "file4: " + ssfile4);
            Log.d("*****", "file5: " + ssfile5);
            Log.d("*****", "uploadedby: " + ssuploadedby);
            Log.d("*****", "uploadtime: " + ssuploadtime);
            Log.d("*****", "lastmodified: s" + sslastmodified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:


                stitle = title.getText().toString();
                snotiffication = notiffication.getText().toString();
                errorflag = 0;


                ArrayList<String> batchesArraylist2 = new ArrayList<>();
                String sBatches = batchesTags.getText().toString();
                Log.d("check", "onOptionsItemSelected: " + sBatches);
                String batchesArray[] = sBatches.split(" ");
                for (int i = 0; i < batchesArray.length; i++) {
                    batchesArraylist2.add(batchesArray[i]);
                }
                String sunny = "";
//                       sunny=  mTagsEditText.getText().toString();
                sunny = android.text.TextUtils.join(",", batchesArraylist2);
                Log.d("sunny", "afterTextChanged3: " + sunny);


                if (forstudflag == 1) {
                    //notification for Student
                    forwhom = instname + "(ADMIN,STUDENT";                  //for testing  purpose ADMIN IS sTUDENT
                    if (forallumflag == 1) {
                        //for Stud + alumni
                        forwhom = forwhom + "," + sunny + ")";
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
                        forwhom = instname + "(" + sunny + ")";
                        Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                    } else {
                        //NOTIFICATION FOR NONE
                        forwhom = forwhom + "(NONE)";
                        Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                    }
                }


                if (stitle.length() < 2) {
                    title.setError("Incorrect Title ");
                    errorflag = 1;
                } else if (snotiffication.length() < 2) {
                    notiffication.setError("Incorrect Notiffication ");
                    errorflag = 1;
                } else if (instname == null) {
                    Toast.makeText(this, "Please Fill Institute Name in Your Profile in Order To Create Notification", Toast.LENGTH_LONG).show();
                    errorflag = 1;

                } else
                    try {
                        if (errorflag == 0) {
                            byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                            byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                            String sPadding = "ISO10126Padding";

                            byte[] roleBytes = srole.getBytes("UTF-8");
                            byte[] stitleBytes = stitle.getBytes("UTF-8");
                            byte[] snotifficationBytes = snotiffication.getBytes("UTF-8");
                            byte[] forwhomyBytes = forwhom.getBytes("UTF-8");

                            byte[] filenameparam1Bytes = filenameparam1.getBytes("UTF-8");
                            byte[] filenameparam2Bytes = filenameparam2.getBytes("UTF-8");
                            byte[] filenameparam3Bytes = filenameparam3.getBytes("UTF-8");
                            byte[] filenameparam4Bytes = filenameparam4.getBytes("UTF-8");
                            byte[] filenameparam5Bytes = filenameparam5.getBytes("UTF-8");

                            byte[] roleEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, roleBytes);
                            encRole = new String(SimpleBase64Encoder.encode(roleEncryptedBytes));
                            byte[] titleEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, stitleBytes);
                            encTitle = new String(SimpleBase64Encoder.encode(titleEncryptedBytes));
                            byte[] notifficationEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, snotifficationBytes);
                            encNotiffication = new String(SimpleBase64Encoder.encode(notifficationEncryptedBytes));
                            byte[] forwhomyEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, forwhomyBytes);
                            encforwhom = new String(SimpleBase64Encoder.encode(forwhomyEncryptedBytes));

                            byte[] filenameparam1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, filenameparam1Bytes);
                            encfilenameparam1 = new String(SimpleBase64Encoder.encode(filenameparam1EncryptedBytes));
                            byte[] filenameparam2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, filenameparam2Bytes);
                            encfilenameparam2 = new String(SimpleBase64Encoder.encode(filenameparam2EncryptedBytes));
                            byte[] filenameparam3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, filenameparam3Bytes);
                            encfilenameparam3 = new String(SimpleBase64Encoder.encode(filenameparam3EncryptedBytes));
                            byte[] filenameparam4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, filenameparam4Bytes);
                            encfilenameparam4 = new String(SimpleBase64Encoder.encode(filenameparam4EncryptedBytes));
                            byte[] filenameparam5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, filenameparam5Bytes);
                            encfilenameparam5 = new String(SimpleBase64Encoder.encode(filenameparam5EncryptedBytes));

                            File1index = map3.get(filenameparam1);
                            File2index = map3.get(filenameparam2);
                            File3index = map3.get(filenameparam3);
                            File4index = map3.get(filenameparam4);
                            File5index = map3.get(filenameparam5);

                            if (forwhom.length() < 2) {
                                Toast.makeText(CreateNotification.this, "select Student or Admin", Toast.LENGTH_SHORT).show();
                                errorflag = 1;
                            } else if (FLAG.equals("EditNotification")) {
                                Toast.makeText(this, "flag  :" + FLAG, Toast.LENGTH_SHORT).show();
                                new Modify().execute();
                            } else if (FLAG.equals("fromAdminActivity")) {
                                Log.d("Tag", "here: ");
                                new SaveData().execute();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
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

    void cancelDialog1() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
//               .setMessage("Filename:"+filename+"          Size:"+lenght/1024+"MB")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {

                                    String f1 = t1.getText().toString();
                                    a1.remove(f1);
                                    filenameparam1 = "";
                                    filenameparam1 = "";
                                    ssfile1 = "";
                                    filecounter--;
                                    refresh();

                                } catch (Exception e) {
                                    Toast.makeText(CreateNotification.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//        alertDialogBuilder .setView(R.layout.custom_dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
//            String n = a1.get(0);
            String n = t1.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);
            if (FLAG.equals("fromeditactivity")) {
                sizeondialogshow1.setText("");

            } else {
//                String s = map2.get(t1.getText().toString());
                String finalsize;
                String s = map2.get(a1.get(0));
                long size = Long.parseLong(s);
                int unit = 1000;
                if (size < unit) {
                    finalsize = size + " Bytes";
                    sizeondialogshow1.setText(finalsize);
                } else {
                    int exp = (int) (Math.log(size) / Math.log(unit));
                    String pre = ("kMGTPE").charAt(exp - 1) + ("");
                    finalsize = String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
                    sizeondialogshow1.setText(finalsize);
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    void cancelDialog2() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
//               .setMessage("Filename:"+filename+"          Size:"+lenght/1024+"MB")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    String f2 = t2.getText().toString();
                                    a1.remove(f2);
                                    filenameparam2 = "";
                                    ssfile2 = "";

                                    filecounter--;
                                    refresh();

                                } catch (Exception e) {
                                    Toast.makeText(CreateNotification.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//        alertDialogBuilder .setView(R.layout.custom_dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
//            String n = a1.get(1);
            String n = t2.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);
            if (FLAG.equals("fromeditactivity")) {
                sizeondialogshow1.setText("");

            } else {
//          String s = map2.get(t1.getText().toString());
                String s = map2.get(a1.get(1));
                long size = Long.parseLong(s);
                String finalsize;
                int unit = 1000;
                if (size < unit) {
                    finalsize = size + " Bytes";
                    sizeondialogshow1.setText(finalsize);
                } else {
                    int exp = (int) (Math.log(size) / Math.log(unit));
                    String pre = ("kMGTPE").charAt(exp - 1) + ("");
                    finalsize = String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
                    sizeondialogshow1.setText(finalsize);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    void cancelDialog3() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
//               .setMessage("Filename:"+filename+"          Size:"+lenght/1024+"MB")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    String f3 = t3.getText().toString();

                                    a1.remove(f3);
                                    filenameparam3 = "";
                                    ssfile3 = "";
                                    filecounter--;
                                    refresh();
                                } catch (Exception e) {
                                    Toast.makeText(CreateNotification.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//        alertDialogBuilder .setView(R.layout.custom_dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
//            String n = a1.get(1);
            String n = t3.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);
            if (FLAG.equals("fromeditactivity")) {
                sizeondialogshow1.setText("");

            } else {


//          String s = map2.get(t1.getText().toString());
                String s = map2.get(a1.get(2));
                long size = Long.parseLong(s);
                String finalsize;
                int unit = 1000;
                if (size < unit) {
                    finalsize = size + " Bytes";
                    sizeondialogshow1.setText(finalsize);
                } else {
                    int exp = (int) (Math.log(size) / Math.log(unit));
                    String pre = ("kMGTPE").charAt(exp - 1) + ("");
                    finalsize = String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
                    sizeondialogshow1.setText(finalsize);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    void cancelDialog4() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
//               .setMessage("Filename:"+filename+"          Size:"+lenght/1024+"MB")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    String f4 = t4.getText().toString();
                                    a1.remove(f4);
                                    filecounter--;
                                    ssfile4 = "";

                                    filenameparam4 = "";
                                    refresh();

                                } catch (Exception e) {
                                    Toast.makeText(CreateNotification.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//        alertDialogBuilder .setView(R.layout.custom_dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
//            String n = a1.get(1);
            String n = t4.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);

            if (FLAG.equals("fromeditactivity")) {
                sizeondialogshow1.setText("");

            } else {


//          String s = map2.get(t1.getText().toString());
                String s = map2.get(a1.get(3));
                long size = Long.parseLong(s);
                String finalsize;
                int unit = 1000;
                if (size < unit) {
                    finalsize = size + " Bytes";
                    sizeondialogshow1.setText(finalsize);
                } else {
                    int exp = (int) (Math.log(size) / Math.log(unit));
                    String pre = ("kMGTPE").charAt(exp - 1) + ("");
                    finalsize = String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
                    sizeondialogshow1.setText(finalsize);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    void cancelDialog5() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
//               .setMessage("Filename:"+filename+"          Size:"+lenght/1024+"MB")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String f5 = t5.getText().toString();
                                try {
                                    a1.remove(f5);
                                    filenameparam5 = "";
                                    ssfile5 = "";

                                    filecounter--;
                                    refresh();
                                } catch (Exception e) {
                                    Toast.makeText(CreateNotification.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//        alertDialogBuilder .setView(R.layout.custom_dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
//            String n = a1.get(1);
            String n = t5.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);
            if (FLAG.equals("fromeditactivity")) {
                sizeondialogshow1.setText("");

            } else {


//          String s = map2.get(t1.getText().toString());
                String s = map2.get(t5.getText().toString());
                long size = Long.parseLong(s);
                String finalsize;
                int unit = 1000;
                if (size < unit) {
                    finalsize = size + " Bytes";
                    sizeondialogshow1.setText(finalsize);
                } else {
                    int exp = (int) (Math.log(size) / Math.log(unit));
                    String pre = ("kMGTPE").charAt(exp - 1) + ("");
                    finalsize = String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
                    sizeondialogshow1.setText(finalsize);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onBackPressed() {
        if (edittedFlag == 1) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    CreateNotification.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
                }
            });

            alertDialog.show();

        } else {
            CreateNotification.super.onBackPressed();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {


            filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            File f = new File(filePath);
            filePath = f.getAbsolutePath();
            // Do anything with file
            lenght = f.length();
//            Toast.makeText(MainActivity.this, "File lenght:" + lenght, Toast.LENGTH_LONG).show();

            if (lenght > 16777216) {
                Toast.makeText(CreateNotification.this, "File Exceeds the Size Limit(16MB)", Toast.LENGTH_LONG).show();
                filesame = 1;
            }
            filename = "";
            int index = filePath.lastIndexOf("/");
            directory = "";
            for (int i = 0; i < index; i++)
                directory += filePath.charAt(i);

            for (int i = index + 1; i < filePath.length(); i++)
                filename += filePath.charAt(i);
            try {
                for (int i = 0; i < a1.size(); i++) {
                    if (a1.get(i).equals(filename)) {
                        filesame = 1;
                    }
                }
            } catch (Exception e) {
            }


            if (filesame != 1) {
                a1.add(filename);
                map.put(filename, filePath);
                map2.put(filename, lenght + "");
                filecounter++;
                refresh();
                batchesTags.dismissDropDown();
                if (filecounter == 1) {
                    new ShowProgress().execute();

                }

                if (filecounter == 2) {
                    new ShowProgress2().execute();

                }
                if (filecounter == 3) {
                    new ShowProgress3().execute();

                }
                if (filecounter == 4) {
                    new ShowProgress4().execute();

                }
                if (filecounter == 5) {
                    new ShowProgress5().execute();

                }

            } else {
                Toast.makeText(CreateNotification.this, "File name is same", Toast.LENGTH_LONG).show();
            }
        }
    }

    void refresh() {

        try {

            try {
                if (a1.get(0) != null) {
                    Log.d("***", "a1 " + a1.get(0));
                    attchrl1.setVisibility(View.VISIBLE);
                    t1.setText(a1.get(0));
                    filenameparam1 = (a1.get(0));
                    filenameparam2 = "";
                    filenameparam3 = "";
                    filenameparam4 = "";
                    filenameparam5 = "";

                    attchrl1.requestFocus();

//                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                    mTagsEditText.dismissDropDown();


                }
            } catch (Exception e) {
                attchrl1.setVisibility(View.GONE);
                t1.setText("");
            }
            try {
                if (a1.get(1) != null) {
                    Log.d("***", "a2 " + a1.get(1));
                    attchrl2.setVisibility(View.VISIBLE);
                    t2.setText(a1.get(1));
                    filenameparam2 = (a1.get(1));
                    filenameparam3 = "";
                    filenameparam4 = "";
                    filenameparam5 = "";

                    attchrl2.requestFocus();

//                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                    mTagsEditText.ropDown();


                }
            } catch (Exception e) {
//                Toast.makeText(CreateNotification.this,  "2"+e.getMessage(), Toast.LENGTH_LONG).show();
                attchrl2.setVisibility(View.GONE);
                t2.setText("");
            }
            try {
                if (a1.get(2) != null) {
                    Log.d("***", "a3 " + a1.get(2));
                    attchrl3.setVisibility(View.VISIBLE);
                    t3.setText(a1.get(2));
                    filenameparam3 = (a1.get(2));
                    filenameparam4 = "";
                    filenameparam5 = "";
                    attchrl3.requestFocus();

//                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                    mTagsEditText.dismissDropDown();


                }
            } catch (Exception e) {
                t3.setText("");
                attchrl3.setVisibility(View.GONE);
            }
            try {
                if (a1.get(3) != null) {

                    Log.d("***", "a4 " + a1.get(3));
                    attchrl4.setVisibility(View.VISIBLE);
                    t4.setText(a1.get(3));
                    filenameparam4 = (a1.get(3));
                    filenameparam5 = "";

                    attchrl4.requestFocus();
//                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                    mTagsEditText.dismissDropDown();


                }
            } catch (Exception e) {
                attchrl4.setVisibility(View.GONE);
                t4.setText("");
            }
            try {

                if (a1.get(4) != null) {
                    attchrl5.setVisibility(View.VISIBLE);
                    Log.d("***", "a5 " + a1.get(4));
                    t5.setText(a1.get(4));
                    filenameparam5 = (a1.get(4));
                    attchrl5.requestFocus();

//                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                    mTagsEditText.dismissDropDown();


                }
            } catch (Exception e) {
                attchrl5.setVisibility(View.GONE);
                t5.setText("");
            }


        } catch (Exception e) {
            Toast.makeText(CreateNotification.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            batchesTags.showDropDown();
        }
    }

    @Override
    public void onTagsChanged(Collection<String> tags) {

    }

    @Override
    public void onEditingFinished() {
//        Log.d(TAG,"OnEditing finished");
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(mTagsEditText.getWindowToken(), 0);
//        //mTagsEditText.clearFocus();
    }

    class SaveData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("r", encRole));   //1
            params.add(new BasicNameValuePair("f", encTitle));       //2
            params.add(new BasicNameValuePair("l", encNotiffication));       //3
            params.add(new BasicNameValuePair("c", encforwhom));     //4
            Log.d("Params", "encUsername:0 "+encUsername);
            Log.d("Params", "encRole: 1"+encRole);
            Log.d("Params", "encTitle:2 "+encTitle);
            Log.d("Params", "encNotiffication: 3"+encNotiffication);
            Log.d("Params", "encforwhom: 4"+encforwhom);



            if (filenameparam1.length() != 0 || filenameparam1 != "") {

                params.add(new BasicNameValuePair("f1", filenameparam1));  //5
                Log.d("Params", "filenameparam1: 5"+filenameparam1);

            } else {
                params.add(new BasicNameValuePair("f1", "BLANK"));
                Log.d("Params", "BLANK: 5");

            }

            if (filenameparam2.length() != 0 || filenameparam2 != "") {
                params.add(new BasicNameValuePair("f2", filenameparam2));     //6
                Log.d("Params", "filenameparam2: 6"+filenameparam2);

            } else {
                params.add(new BasicNameValuePair("f2", "BLANK"));
                Log.d("Params", "BLANK: 6");


            }
            if (filenameparam3.length() != 0 || filenameparam3 != "") {
                params.add(new BasicNameValuePair("f3", filenameparam3));     //7
                Log.d("Params", "filenameparam3: 7"+filenameparam3);

            } else {
                params.add(new BasicNameValuePair("f3", "BLANK"));
                Log.d("Params", "BLANK: 7");


            }
            if (filenameparam4.length() != 0 || filenameparam4 != "") {
                params.add(new BasicNameValuePair("f4", filenameparam4));     //8
                Log.d("Params", "filenameparam4: 8"+filenameparam4);

            } else {
                params.add(new BasicNameValuePair("f4", "BLANK"));
                Log.d("Params", "BLANK: 8");


            }
            if (filenameparam5.length() != 0 || filenameparam5 != "") {
                params.add(new BasicNameValuePair("f5", filenameparam5));     //9
                Log.d("Params", "filenameparam5: 9"+filenameparam5);

            } else {
                params.add(new BasicNameValuePair("f5", "BLANK"));
                Log.d("Params", "BLANK: 9");

            }

            json = jParser.makeHttpRequest(url_savedata, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(CreateNotification.this, result, Toast.LENGTH_SHORT).show();
            CreateNotification.super.onBackPressed();
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

    class Modify extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... param) {
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            Log.d("im here", "doInBackground: ");

            params.add(new BasicNameValuePair("id", ssnotificationid));       //0
            params.add(new BasicNameValuePair("u", encUsername));    //1
            params.add(new BasicNameValuePair("r", encRole));      //2
            params.add(new BasicNameValuePair("tt", encTitle));       //3
            params.add(new BasicNameValuePair("nt", encNotiffication));       //4
            params.add(new BasicNameValuePair("fw", encforwhom));     //5


            if (filenameparam1.length() != 0 || filenameparam1 != "") {
                params.add(new BasicNameValuePair("f1", encfilenameparam1));  //6
            } else {
                params.add(new BasicNameValuePair("f1", "BLANK"));
            }
            if (File1index == null) {
                //param updated
                params.add(new BasicNameValuePair("S1", "UPDATED"));
            } else {
                // param fileindex
                params.add(new BasicNameValuePair("S1", File1index));                //7

            }

            if (filenameparam2.length() != 0 || filenameparam2 != "") {
                params.add(new BasicNameValuePair("f2", encfilenameparam2));  //8
            } else {
                params.add(new BasicNameValuePair("f2", "BLANK"));
            }
            if (File2index == null) {
                //param updated
                params.add(new BasicNameValuePair("S2", "UPDATED"));

            } else {
                // param fileindex
                params.add(new BasicNameValuePair("S2", File2index));                //9

            }

            if (filenameparam3.length() != 0 || filenameparam3 != "") {
                params.add(new BasicNameValuePair("f3", encfilenameparam3));  //10
            } else {
                params.add(new BasicNameValuePair("f3", "BLANK"));
            }
            if (File3index == null) {
                //param updated
                params.add(new BasicNameValuePair("S3", "UPDATED"));

            } else {
                // param fileindex
                params.add(new BasicNameValuePair("S3", File3index));                //11

            }


            if (filenameparam4.length() != 0 || filenameparam4 != "") {
                params.add(new BasicNameValuePair("f4", encfilenameparam3));  //12
            } else {
                params.add(new BasicNameValuePair("f4", "BLANK"));
            }


            if (File4index == null) {
                //param updated
                params.add(new BasicNameValuePair("S4", "UPDATED"));

            } else {
                // param fileindex
                params.add(new BasicNameValuePair("S4", File4index));                //13

            }


            if (filenameparam5.length() != 0 || filenameparam5 != "") {
                params.add(new BasicNameValuePair("f5", encfilenameparam5));  //14
            } else {
                params.add(new BasicNameValuePair("f5", "BLANK"));
            }
            if (File5index == null) {
                //param updated
                params.add(new BasicNameValuePair("S5", "UPDATED"));

            } else {
                // param fileindex
                params.add(new BasicNameValuePair("S5", File5index));                //15

            }


            json = jParser.makeHttpRequest(url_saveUpdated, "GET", params);
            try {
                r = json.getString("info");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(CreateNotification.this, "ugh" + result, Toast.LENGTH_SHORT).show();
//            AdminActivity.getNotifications();
            CreateNotification.super.onBackPressed();
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

    class ShowProgress extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

            File atach1 = new File(filePath);
            lenght = atach1.length();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            File atach1 = new File(filePath);
            lenght = atach1.length();
            username = "sunny.gh.gm@gmail.com";
//            username = encUsername;
            try {
//            MultipartUtility multipart = new MultipartUtility(upload_Attach_temp, "UTF-8");
                // creates a unique boundary based on time stamp
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(savefoleonserverURL);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);    // indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);
                //formfieldpart//     multipart.addFormField("u", username);
                name = "u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if (filename != "") {
                    name2 = "f";
                    value2 = filename;
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                            .append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(
                            LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append(value2).append(LINE_FEED);
                    writer.flush();
                    //multipart part// multipart.addFilePart("uf", sourceFile);

                    String fieldName = "uf", uploadFile = "";
                    //        String fileName = uploadFile.getName();
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append(
                            "Content-Disposition: form-data; name=\"" + fieldName
                                    + "\"; filename=\"" + filename + "\"")
                            .append(LINE_FEED);
                    writer.append(
                            "Content-Type: "
                                    + URLConnection.guessContentTypeFromName(filename))
                            .append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();
                    FileInputStream inputStream = new FileInputStream(atach1);
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
//                  long totalSize= 15710566;
                    long totalSize = lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        //progres
                        Log.d("bytes", "" + bytesRead);
                        progress += bytesRead;
                        data_for_progressbar = (int) ((progress * 100) / totalSize);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
///finishPart
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

// checks server's status code first
                    int status = httpConn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                httpConn.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            response.add(line);
                        }
                        reader.close();
                        httpConn.disconnect();

                    } else {
                        throw new IOException("Server returned non-OK status: " + status);
                    }

                } else
//        multipart.addFormField("f", "");
                    name2 = "f";
                value2 = "";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(value2).append(LINE_FEED);
                writer.flush();
                ///finishPart
                writer.append(LINE_FEED).flush();
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();

//            response = multipart.finish();

// checks server's status code first
                int status = httpConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            httpConn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.add(line);
                    }
                    reader.close();
                    httpConn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " + status);
                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.d("response;", response.get(0));
                if (response.contains(" \"file\": \"created\"")) {
                    Toast.makeText(CreateNotification.this, response.get(0), Toast.LENGTH_LONG).show();
//                        prg1.setIndeterminateDrawable(compleatesprogress);

                    prg1.setProgressDrawable(compleatesprogress);
//                        pr/g1.


                } else
                    Toast.makeText(CreateNotification.this, response.get(0), Toast.LENGTH_LONG).show();


            } catch (Exception e) {
                Toast.makeText(CreateNotification.this, e.getMessage(), Toast.LENGTH_LONG).show();

            }

        }

        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
            Log.d("LAst", "progressOnUpdate1:" + data_for_progressbar + "");
//

            prg1.setProgress(data_for_progressbar);


        }

    }

    class ShowProgress2 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

            File atach1 = new File(filePath);
            lenght = atach1.length();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            File atach1 = new File(filePath);
            lenght = atach1.length();
            username = "sunny.gh.gm@gmail.com";
//            username = encUsername;
            try {
//            MultipartUtility multipart = new MultipartUtility(upload_Attach_temp, "UTF-8");
                // creates a unique boundary based on time stamp
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(savefoleonserverURL);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);    // indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);
                //formfieldpart//     multipart.addFormField("u", username);
                name = "u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if (filename != "") {
                    name2 = "f";
                    value2 = filename;
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                            .append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(
                            LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append(value2).append(LINE_FEED);
                    writer.flush();
                    //multipart part// multipart.addFilePart("uf", sourceFile);


                    String fieldName = "uf", uploadFile = "";
                    //        String fileName = uploadFile.getName();
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append(
                            "Content-Disposition: form-data; name=\"" + fieldName
                                    + "\"; filename=\"" + filename + "\"")
                            .append(LINE_FEED);
                    writer.append(
                            "Content-Type: "
                                    + URLConnection.guessContentTypeFromName(filename))
                            .append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();
                    FileInputStream inputStream = new FileInputStream(atach1);
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
//                  long totalSize= 15710566;
                    long totalSize = lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        //progres
                        Log.d("bytes", "" + bytesRead);
                        progress2 += bytesRead;
                        data_for_progressbar1 = (int) ((progress2 * 100) / totalSize);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
///finishPart
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

// checks server's status code first
                    int status = httpConn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                httpConn.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            response.add(line);
                        }
                        reader.close();
                        httpConn.disconnect();

                    } else {
                        throw new IOException("Server returned non-OK status: " + status);
                    }

                } else
//        multipart.addFormField("f", "");
                    name2 = "f";
                value2 = "";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(value2).append(LINE_FEED);
                writer.flush();
                ///finishPart
                writer.append(LINE_FEED).flush();
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();

//            response = multipart.finish();

// checks server's status code first
                int status = httpConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            httpConn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.add(line);
                    }
                    reader.close();
                    httpConn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " + status);
                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress2) {
            super.onProgressUpdate(progress2);
            Log.d("LAst", "progressOnUpdate2:" + data_for_progressbar1 + "");
//

            prg2.setProgress(data_for_progressbar1);


        }

    }

    class ShowProgress3 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

            File atach1 = new File(filePath);
            lenght = atach1.length();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            File atach1 = new File(filePath);
            lenght = atach1.length();
            username = "sunny.gh.gm@gmail.com";
//            username = encUsername;
            try {
//            MultipartUtility multipart = new MultipartUtility(upload_Attach_temp, "UTF-8");
                // creates a unique boundary based on time stamp
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(savefoleonserverURL);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);    // indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);
                //formfieldpart//     multipart.addFormField("u", username);
                name = "u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if (filename != "") {
                    name2 = "f";
                    value2 = filename;
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                            .append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(
                            LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append(value2).append(LINE_FEED);
                    writer.flush();
                    //multipart part// multipart.addFilePart("uf", sourceFile);

                    String fieldName = "uf", uploadFile = "";
                    //        String fileName = uploadFile.getName();
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append(
                            "Content-Disposition: form-data; name=\"" + fieldName
                                    + "\"; filename=\"" + filename + "\"")
                            .append(LINE_FEED);
                    writer.append(
                            "Content-Type: "
                                    + URLConnection.guessContentTypeFromName(filename))
                            .append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();
                    FileInputStream inputStream = new FileInputStream(atach1);
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
//                  long totalSize= 15710566;
                    long totalSize = lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        //progres
                        Log.d("bytes", "" + bytesRead);
                        progress3 += bytesRead;
                        data_for_progressbar2 = (int) ((progress3 * 100) / totalSize);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
///finishPart
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

// checks server's status code first
                    int status = httpConn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                httpConn.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            response.add(line);
                        }
                        reader.close();
                        httpConn.disconnect();

                    } else {
                        throw new IOException("Server returned non-OK status: " + status);
                    }

                } else
//        multipart.addFormField("f", "");
                    name2 = "f";
                value2 = "";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(value2).append(LINE_FEED);
                writer.flush();
                ///finishPart
                writer.append(LINE_FEED).flush();
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();

//            response = multipart.finish();

// checks server's status code first
                int status = httpConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            httpConn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.add(line);
                    }
                    reader.close();
                    httpConn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " + status);
                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress3) {
            super.onProgressUpdate(progress3);
            Log.d("LAst", "progressOnUpdate3:" + data_for_progressbar2 + "");
//

            prg3.setProgress(data_for_progressbar2);


        }

    }

    class ShowProgress4 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

            File atach1 = new File(filePath);
            lenght = atach1.length();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            File atach1 = new File(filePath);
            lenght = atach1.length();
            username = "sunny.gh.gm@gmail.com";
//            username = encUsername;
            try {
//            MultipartUtility multipart = new MultipartUtility(upload_Attach_temp, "UTF-8");
                // creates a unique boundary based on time stamp
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(savefoleonserverURL);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);    // indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);
                //formfieldpart//     multipart.addFormField("u", username);
                name = "u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if (filename != "") {
                    name2 = "f";
                    value2 = filename;
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                            .append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(
                            LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append(value2).append(LINE_FEED);
                    writer.flush();
                    //multipart part// multipart.addFilePart("uf", sourceFile);

                    String fieldName = "uf", uploadFile = "";
                    //        String fileName = uploadFile.getName();
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append(
                            "Content-Disposition: form-data; name=\"" + fieldName
                                    + "\"; filename=\"" + filename + "\"")
                            .append(LINE_FEED);
                    writer.append(
                            "Content-Type: "
                                    + URLConnection.guessContentTypeFromName(filename))
                            .append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();
                    FileInputStream inputStream = new FileInputStream(atach1);
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
//                  long totalSize= 15710566;
                    long totalSize = lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        //progres
                        Log.d("bytes", "" + bytesRead);
                        progress4 += bytesRead;
                        data_for_progressbar3 = (int) ((progress4 * 100) / totalSize);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
///finishPart
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

// checks server's status code first
                    int status = httpConn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                httpConn.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            response.add(line);
                        }
                        reader.close();
                        httpConn.disconnect();

                    } else {
                        throw new IOException("Server returned non-OK status: " + status);
                    }

                } else
//        multipart.addFormField("f", "");
                    name2 = "f";
                value2 = "";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(value2).append(LINE_FEED);
                writer.flush();
                ///finishPart
                writer.append(LINE_FEED).flush();
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();

//            response = multipart.finish();

// checks server's status code first
                int status = httpConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            httpConn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.add(line);
                    }
                    reader.close();
                    httpConn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " + status);
                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress4) {
            super.onProgressUpdate(progress4);
            Log.d("LAst", "progressOnUpdate4:" + data_for_progressbar3 + "");
            prg4.setProgress(data_for_progressbar3);

        }

    }

    class ShowProgress5 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

            File atach1 = new File(filePath);
            lenght = atach1.length();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            File atach1 = new File(filePath);
            lenght = atach1.length();
            username = "sunny.gh.gm@gmail.com";
//            username = encUsername;
            try {
//            MultipartUtility multipart = new MultipartUtility(upload_Attach_temp, "UTF-8");
                // creates a unique boundary based on time stamp
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(savefoleonserverURL);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);    // indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);
                //formfieldpart//     multipart.addFormField("u", username);
                name = "u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if (filename != "") {
                    name2 = "f";
                    value2 = filename;
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                            .append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(
                            LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append(value2).append(LINE_FEED);
                    writer.flush();
                    //multipart part// multipart.addFilePart("uf", sourceFile);

                    String fieldName = "uf", uploadFile = "";
                    //        String fileName = uploadFile.getName();
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append(
                            "Content-Disposition: form-data; name=\"" + fieldName
                                    + "\"; filename=\"" + filename + "\"")
                            .append(LINE_FEED);
                    writer.append(
                            "Content-Type: "
                                    + URLConnection.guessContentTypeFromName(filename))
                            .append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();
                    FileInputStream inputStream = new FileInputStream(atach1);
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
//                  long totalSize= 15710566;
                    long totalSize = lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        //progres
                        Log.d("bytes", "" + bytesRead);
                        progress5 += bytesRead;
                        data_for_progressbar4 = (int) ((progress5 * 100) / totalSize);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
///finishPart
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

// checks server's status code first
                    int status = httpConn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                httpConn.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            response.add(line);
                        }
                        reader.close();
                        httpConn.disconnect();
                    } else {
                        throw new IOException("Server returned non-OK status: " + status);
                    }

                } else
//        multipart.addFormField("f", "");
                    name2 = "f";
                value2 = "";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(value2).append(LINE_FEED);
                writer.flush();
                ///finishPart
                writer.append(LINE_FEED).flush();
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();

//            response = multipart.finish();

// checks server's status code first
                int status = httpConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            httpConn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.add(line);
                    }
                    reader.close();
                    httpConn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " + status);
                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress5) {
            super.onProgressUpdate(progress5);
            Log.d("LAst", "progressOnUpdate4:" + data_for_progressbar4 + "");
            prg5.setProgress(data_for_progressbar4);


        }

    }

    class GetForwhome extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", ssnotificationid)); //0

            json = jParser.makeHttpRequest(url_getforwhome, "GET", params);
            try {
                Forwhomefromdb = json.getString("forwhom");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            try {

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";
                if (!Forwhomefromdb.equals("")) {
                    byte[] ForwhomefromdbEncryptedBytes = SimpleBase64Encoder.decode(Forwhomefromdb);
                    byte[] ForwhomefromdbDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, ForwhomefromdbEncryptedBytes);
                    Forwhomefromdb = new String(ForwhomefromdbDecryptedBytes);
                    Log.d("Forwhomefromdb", "onPostExecute: " + Forwhomefromdb);
                }

                if (Forwhomefromdb.contains("ALL")) {
                    allum.setChecked(true);
                    batchesTags.setText("ALL");
                }
                if (Forwhomefromdb.contains("ADMIN"))          //CHANGE IT TO sTUDENT
                {
                    stud.setChecked(true);
                }

                int index1 = Forwhomefromdb.indexOf("(");
                int index2 = Forwhomefromdb.indexOf(")");
                String whomsYears = "";
                for (int i = index1 + 1; i < index2; i++) {
                    whomsYears += Forwhomefromdb.charAt(i);
                }
//                String testStr="STUDENT";
                Log.d("TAG1", "before: " + whomsYears);
                whomsYears = whomsYears.replace("ADMIN,", "");
                Log.d("TAG1", "after1: " + whomsYears);
                whomsYears = whomsYears.replace("STUDENT,", "");
                Log.d("TAG1", "after2: " + whomsYears);
                whomsYears = whomsYears.replace("ADMIN", "");
                Log.d("TAG1", "after3: " + whomsYears);
                whomsYears = whomsYears.replace("STUDENT", "");
                Log.d("TAG1", "after4: " + whomsYears);
                whomsYears = whomsYears.replace("ALL", "");

                if (whomsYears.length() >= 2) {
                    allum.setChecked(true);
                    Log.d("whomsYears3:", whomsYears);
                    whomsYears = whomsYears.replace(",", " ");
                    String batchyears[] = whomsYears.split(" ");
                    batchesTags.setTags(batchyears);

                }

            } catch (Exception e) {

                Log.d("whomsYears e:", e.getMessage());
                Toast.makeText(CreateNotification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}
