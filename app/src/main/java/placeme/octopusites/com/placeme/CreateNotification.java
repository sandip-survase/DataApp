package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import mabbas007.tagsedittext.TagsEditText;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;

public class CreateNotification extends AppCompatActivity implements TagsEditText.TagsEditListener {


    final List<String> yearList = new ArrayList<String>();
    //hiding ui
    TextView createnotitxt, createnotinotitxt, lastmodifiedtxt;
    RelativeLayout file1;
    TextInputEditText title, notiffication;
    CheckBox stud, allum;
    String stitle = "", snotiffication = "";
    String username = "", srole = "", plainusername = "", forwhom = "", encRole = "";
    String encUsername, encTitle, encNotiffication, encforwhom;
    String encfilenameparam1, encfilenameparam2, encfilenameparam3, encfilenameparam4, encfilenameparam5;
    String instname = "";
    AdminData a = new AdminData();
    int errorflag = 0;
    int forstudflag = 0, forallumflag = 0;
    TextInputLayout batches, titleinput, notificationinput;
    RelativeLayout yearspinner;
    RelativeLayout attchrl1, attchrl2, attchrl3, attchrl4, attchrl5;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    boolean showPop = false;
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
    String name = "";
    List<String> response = new ArrayList<String>();

    ProgressBar prg1, prg2, prg3, prg4, prg5;
    ScrollView scrollview;
    String filenameparam1 = "", filenameparam2 = "", filenameparam3 = "", filenameparam4 = "", filenameparam5 = "";
    String FLAG = "";
    String ssnotificationid = "", sstitle = "", ssnotification = "", ssfile1 = "", ssfile2 = "", ssfile3 = "", ssfile4 = "", ssfile5 = "", ssuploadedby = "", ssuploadtime = "", sslastmodified = "";
    HashMap<String, String> map3 = new HashMap();
    String File1index = "", File2index = "", File3index = "", File4index = "", File5index = "";
    String Forwhomefromdb = "";
    ArrayAdapter<String> dataAdapter;
    ArrayList<String> TagCreateList = new ArrayList<>();
    int edittedFlag = 0, containsall = 0;
    String digest1, digest2;
    boolean fileinprogress1 = false, fileinprogress2 = false, fileinprogress3 = false, fileinprogress4 = false, fileinprogress5 = false;

    private TagsEditText batchesTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);

        scrollview = ((ScrollView) findViewById(R.id.schroll1));

        titleinput = (TextInputLayout) findViewById(R.id.titleinput);
        notificationinput = (TextInputLayout) findViewById(R.id.notificationinput);
        titleinput.setTypeface(Z.getLight(this));
        notificationinput.setTypeface(Z.getLight(this));


        createnotitxt = (TextView) findViewById(R.id.createnotitxt);
        createnotinotitxt = (TextView) findViewById(R.id.createnotinotitxt);
        lastmodifiedtxt = (TextView) findViewById(R.id.lastmodifiedtxt);
        createnotitxt.setTypeface(Z.getBold(this));
        createnotinotitxt.setTypeface(Z.getLight(this));
        lastmodifiedtxt.setTypeface(Z.getLight(this));


        TextView choosetxt = (TextView) findViewById(R.id.choosetxt);
        choosetxt.setTypeface(Z.getBold(this));
        TextView attachmentstxt = (TextView) findViewById(R.id.attachmentstxt);
        attachmentstxt.setTypeface(Z.getLight(this));

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Create Notification");

        FLAG = getIntent().getStringExtra("flag");
        if (FLAG.equals("EditNotification")) {
            ab.setTitle("Edit Notification");
            createnotitxt.setText("Modify/Delete Notification");
            createnotinotitxt.setText("Your changes will be broadcasted only to the students and/or alumnis of your institute.");


        } else if (FLAG.equals("fromAdminActivity")) {
            ab.setTitle("Create Notification");
            createnotitxt.setText("Create Notification");
            createnotinotitxt.setText("Your notification will be broadcasted only to the students and/or alumnis of your institute.");

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


        srole = MySharedPreferencesManager.getRole(this);
        instname = MySharedPreferencesManager.getInstitute(this);
        encUsername = MySharedPreferencesManager.getUsername(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        Log.d("Shardpreff", "encUsername: " + encUsername);
        Log.d("Shardpreff", "onCreate: " + instname);
        Log.d("Shardpreff", "SROLR: " + srole);


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        title = (TextInputEditText) findViewById(R.id.title);
        notiffication = (TextInputEditText) findViewById(R.id.notification);
        title.setTypeface(Z.getBold(this));
        notiffication.setTypeface(Z.getBold(this));

        stud = (CheckBox) findViewById(R.id.CheckBoxstudent);
        allum = (CheckBox) findViewById(R.id.CheckBoxsAlumni);
        stud.setTypeface(Z.getBold(this));
        allum.setTypeface(Z.getBold(this));


        //tags
        batchesTags = (TagsEditText) findViewById(R.id.tagsEditText);
        batchesTags.setHint("Enter the Batches");
        batchesTags.setTagsListener(this);
        batchesTags.setTagsWithSpacesEnabled(true);
        batchesTags.setThreshold(1);
        batchesTags.setFocusable(false);

        yearList.add("ALL");
        Calendar currentCalendar = Calendar.getInstance();
        for (int i = currentCalendar.get(Calendar.YEAR) - 1; i >= 2000; i--)
            yearList.add("" + i);


        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, yearList) {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getLight(CreateNotification.this));
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

                if (showPop == false) {
                    if (batchesTags.getText().toString().contains("ALL")) {
                        //dont popullate
                        Toast.makeText(CreateNotification.this, "Notification will be sent to All batches", Toast.LENGTH_SHORT).show();
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


                        batchesTags.setAdapter(dataAdapter);
                        batchesTags.showDropDown();
                    }
                }
            }
        });

        batchesTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                        Toast.makeText(CreateNotification.this, "Batch " + temp + " is already present", Toast.LENGTH_SHORT).show();
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
                Log.d("setTagsListener", "onEditingFinished: " + containsall);

                String temp = "";
                temp = batchesTags.getText().toString().trim();
                Log.d("tag", "onTagsChanged: " + temp + " len " + temp.length());
                if (temp.equals("")) {
                    batchesTags.dismissDropDown();
                    yearspinner.setVisibility(View.GONE);
                    allum.setChecked(false);
                    showPop = true;
                }

            }

            @Override
            public void onEditingFinished() {

            }
        });


        stud.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edittedFlag = 1;

                if (isChecked) {
                    forstudflag = 1;
                } else {
                    forstudflag = 0;
                }
            }
        });

        allum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edittedFlag = 1;
                if (isChecked) {
                    showPop = false;
                    yearspinner.setVisibility(View.VISIBLE);
                    batches.setVisibility(View.VISIBLE);
                    forallumflag = 1;
                    yearspinner.setVisibility(View.VISIBLE);
                    batches.setVisibility(View.VISIBLE);
                    batchesTags.setVisibility(View.VISIBLE);

                } else {

                    forallumflag = 0;
                    yearspinner.setVisibility(View.INVISIBLE);
                    batches.setVisibility(View.INVISIBLE);
                    batchesTags.setVisibility(View.GONE);
                    batchesTags.setText("");

                }
            }
        });


        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleinput.setError(null);
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
                notificationinput.setError(null);
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

        try {

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

                try {

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
                    sunny = android.text.TextUtils.join(",", batchesArraylist2);
                    Log.d("sunny", "afterTextChanged3: " + sunny);


                    if (forstudflag == 1) {
                        //notification for Student
                        forwhom = instname + "( " + Decrypt(encUsername, digest1, digest2) + " ,STUDENT";                  //for testing  purpose ADMIN IS sTUDENT
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
                            forwhom = instname + "( " + Decrypt(encUsername, digest1, digest2) + " ," + sunny + ")";
                            Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                        } else {
                            //NOTIFICATION FOR NONE
                            forwhom = forwhom + "";
                            Log.d("forwhomeStringAppend", "onCreate: " + forwhom);

                        }
                    }

                    if (stitle.length() < 2) {
                        titleinput.setError("Kindly enter notification title ");
                        errorflag = 1;
                    } else if (snotiffication.length() < 2) {
                        notificationinput.setError("Kindly enter some message");
                        errorflag = 1;
                    } else if (forwhom.length() < 2) {
                        Toast.makeText(CreateNotification.this, "select Student or Admin", Toast.LENGTH_SHORT).show();
                        errorflag = 1;
                    } else if (errorflag == 0) {

                        if (!fileinprogress1 && !fileinprogress2 && !fileinprogress3 && !fileinprogress4 && !fileinprogress5) {

//                            Toast.makeText(this, "files ok", Toast.LENGTH_SHORT).show();
                            encRole = Z.Encrypt(srole, CreateNotification.this);
                            encTitle = Z.Encrypt(stitle, CreateNotification.this);
                            encNotiffication = Z.Encrypt(snotiffication, CreateNotification.this);
                            encforwhom = Z.Encrypt(forwhom, CreateNotification.this);
                            encfilenameparam1 = Z.Encrypt(filenameparam1, CreateNotification.this);
                            encfilenameparam2 = Z.Encrypt(filenameparam2, CreateNotification.this);
                            encfilenameparam3 = Z.Encrypt(filenameparam3, CreateNotification.this);
                            encfilenameparam4 = Z.Encrypt(filenameparam4, CreateNotification.this);
                            encfilenameparam5 = Z.Encrypt(filenameparam5, CreateNotification.this);
                            File1index = map3.get(filenameparam1);
                            File2index = map3.get(filenameparam2);
                            File3index = map3.get(filenameparam3);
                            File4index = map3.get(filenameparam4);
                            File5index = map3.get(filenameparam5);

                            if (FLAG.equals("EditNotification")) {
                                new Modify().execute();
                            } else if (FLAG.equals("fromAdminActivity")) {
                                Log.d("Tag", "here: ");
                                new SaveData().execute();
                            }


                        } else {
                            Toast.makeText(this, "file upload is in progress", Toast.LENGTH_SHORT).show();
                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
                                    fileinprogress1=false;
                                    refresh();

                                } catch (Exception e) {

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
                sizeondialogshow1.setVisibility(View.GONE);
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
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                                    fileinprogress2=false;
                                    refresh();

                                } catch (Exception e) {

                                }


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
            String n = t2.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);
            if (FLAG.equals("fromeditactivity")) {
                sizeondialogshow1.setText("");
                sizeondialogshow1.setVisibility(View.GONE);

            } else {
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
                                    fileinprogress3=false;

                                    refresh();
                                } catch (Exception e) {

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
                sizeondialogshow1.setVisibility(View.GONE);

            } else {

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
                                    fileinprogress4=false;
                                    refresh();

                                } catch (Exception e) {

                                }


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
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
                sizeondialogshow1.setVisibility(View.GONE);

            } else {


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
                                    fileinprogress5=false;
                                    refresh();
                                } catch (Exception e) {

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
                sizeondialogshow1.setVisibility(View.GONE);

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
                int THREAD_POOL_EXECUTOR2 = 5;

                a1.add(filename);
                map.put(filename, filePath);
                map2.put(filename, lenght + "");
                filecounter++;
                refresh();
                batchesTags.dismissDropDown();
                if (filecounter == 1) {
                    new ShowProgress1().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
                if (filecounter == 2) {
                    new ShowProgress2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
                if (filecounter == 3) {
                    new ShowProgress3().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
                if (filecounter == 4) {
                    new ShowProgress4().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                }
                if (filecounter == 5) {
                    new ShowProgress5().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                }

            } else {
                Toast.makeText(CreateNotification.this, "File is already uploaded", Toast.LENGTH_LONG).show();
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
          }
            } catch (Exception e) {
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
         }
            } catch (Exception e) {
                attchrl5.setVisibility(View.GONE);
                t5.setText("");
            }
     } catch (Exception e) {
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
            Log.d("Params", "encUsername:0 " + encUsername);
            Log.d("Params", "encRole: 1" + encRole);
            Log.d("Params", "encTitle:2 " + encTitle);
            Log.d("Params", "encNotiffication: 3" + encNotiffication);
            Log.d("Params", "encforwhom: 4" + encforwhom);


            if (filenameparam1.length() != 0 || filenameparam1 != "") {

                params.add(new BasicNameValuePair("f1", filenameparam1));  //5
                Log.d("Params", "filenameparam1: 5" + filenameparam1);

            } else {
                params.add(new BasicNameValuePair("f1", "BLANK"));
                Log.d("Params", "BLANK: 5");

            }

            if (filenameparam2.length() != 0 || filenameparam2 != "") {
                params.add(new BasicNameValuePair("f2", filenameparam2));     //6
                Log.d("Params", "filenameparam2: 6" + filenameparam2);

            } else {
                params.add(new BasicNameValuePair("f2", "BLANK"));
                Log.d("Params", "BLANK: 6");


            }
            if (filenameparam3.length() != 0 || filenameparam3 != "") {
                params.add(new BasicNameValuePair("f3", filenameparam3));     //7
                Log.d("Params", "filenameparam3: 7" + filenameparam3);

            } else {
                params.add(new BasicNameValuePair("f3", "BLANK"));
                Log.d("Params", "BLANK: 7");


            }
            if (filenameparam4.length() != 0 || filenameparam4 != "") {
                params.add(new BasicNameValuePair("f4", filenameparam4));     //8
                Log.d("Params", "filenameparam4: 8" + filenameparam4);

            } else {
                params.add(new BasicNameValuePair("f4", "BLANK"));
                Log.d("Params", "BLANK: 8");


            }
            if (filenameparam5.length() != 0 || filenameparam5 != "") {
                params.add(new BasicNameValuePair("f5", filenameparam5));     //9
                Log.d("Params", "filenameparam5: 9" + filenameparam5);

            } else {
                params.add(new BasicNameValuePair("f5", "BLANK"));
                Log.d("Params", "BLANK: 9");

            }

            json = jParser.makeHttpRequest(Z.url_UploadAttach1, "GET", params);
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
                params.add(new BasicNameValuePair("f4", encfilenameparam4));  //12
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


            json = jParser.makeHttpRequest(Z.url_ModifyNotification, "GET", params);
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

        }
    }

   class ShowProgress1 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            prg1.setIndeterminate(true);
            try {
                File atach1 = new File(filePath);
                lenght = atach1.length();
                username = Z.Decrypt(encUsername, CreateNotification.this);
//            username = encUsername;

                if (atach1 != null) {

                    MultipartUtility multipart = null;
                    try {
                        prg1.setIndeterminate(true);
                        fileinprogress1 = true;

                        multipart = new MultipartUtility(Z.url_SavefileOnServer, "UTF-8");
                        Log.d("TAG", "UploadProfile1 : input  username " + username);
                        multipart.addFormField("u", username);
                        if (filename != "") {
                            multipart.addFormField("f", filename);
                            multipart.addFilePart("uf", atach1);
                            Log.d("TAG", "onSuccess: f name- " + filename);
                        } else
                            multipart.addFormField("f", "null");
                        response = multipart.finish();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("TAG", "exp : " + e.getMessage());

                    }

                } else {
                    Log.d("TAG", "file null");

                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("response", ":onPostExecute response1 :" + response);

            if (response != null && response.get(0).contains("created")) {
                Log.d("filestatus1", "created");
                prg1.setIndeterminate(false);
                prg1.setProgress(100);
                fileinprogress1 = false;


            } else if (response != null && response.get(0).contains("null")) {
                Log.d("filestatus1", "null");
                fileinprogress1 = false;


            } else {
                Log.d("filestatus1", "file not created");
                fileinprogress1 = false;


            }

        }
    }

    class ShowProgress2 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            prg2.setIndeterminate(true);
            try {
                File atach1 = new File(filePath);
                lenght = atach1.length();
                username = Z.Decrypt(encUsername, CreateNotification.this);
//            username = encUsername;

                if (atach1 != null) {

                    MultipartUtility multipart = null;
                    try {
                        prg2.setIndeterminate(true);
                        fileinprogress2 = true;


                        multipart = new MultipartUtility(Z.url_SavefileOnServer, "UTF-8");
                        Log.d("TAG", "UploadProfile2 : input  username " + username);
                        multipart.addFormField("u", username);
                        if (filename != "") {
                            multipart.addFormField("f", filename);
                            multipart.addFilePart("uf", atach1);
                            Log.d("TAG", "onSuccess: f name- " + filename);
                        } else
                            multipart.addFormField("f", "null");
                        response = multipart.finish();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("TAG", "exp : " + e.getMessage());

                    }

                } else {
                    Log.d("TAG", "file null");

                }


            } catch (Exception ex) {

            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("response", ":onPostExecute response2 :" + response);


            if (response != null && response.get(0).contains("created")) {
                Log.d("filestatus2", "created");
                prg2.setIndeterminate(false);
                prg2.setProgress(100);
                fileinprogress2 = false;


            } else if (response != null && response.get(0).contains("null")) {
                Log.d("filestatus2", "null");
                fileinprogress2 = false;


            } else {
                Log.d("filestatus2", "file not created");
                fileinprogress2 = false;


            }

        }
    }

    class ShowProgress3 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                File atach1 = new File(filePath);
                lenght = atach1.length();
                username = Z.Decrypt(encUsername, CreateNotification.this);
//            username = encUsername;

                if (atach1 != null) {

                    MultipartUtility multipart = null;
                    try {
                        prg3.setIndeterminate(true);
                        fileinprogress3 = true;


                        multipart = new MultipartUtility(Z.url_SavefileOnServer, "UTF-8");
                        Log.d("TAG", "UploadProfile3 : input  username " + username);
                        multipart.addFormField("u", username);
                        if (filename != "") {
                            multipart.addFormField("f", filename);
                            multipart.addFilePart("uf", atach1);
                            Log.d("TAG", "onSuccess: f name- " + filename);
                        } else
                            multipart.addFormField("f", "null");
                        response = multipart.finish();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("TAG", "exp : " + e.getMessage());

                    }

                } else {
                    Log.d("TAG", "file null");

                }


            } catch (Exception ex) {

            }


            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("response", ":onPostExecute response3 :" + response);

            if (response != null && response.get(0).contains("created")) {
                Log.d("filestatus3", "created");
                prg3.setIndeterminate(false);
                prg3.setProgress(100);
                fileinprogress3 = false;


            } else if (response != null && response.get(0).contains("null")) {
                Log.d("filestatus3", "null");
                fileinprogress3 = false;


            } else {
                Log.d("filestatus3", "file not created");
                fileinprogress3 = false;


            }

        }
    }

    class ShowProgress4 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {


            try {
                File atach1 = new File(filePath);
                lenght = atach1.length();
                username = Z.Decrypt(encUsername, CreateNotification.this);
//            username = encUsername;

                if (atach1 != null) {

                    MultipartUtility multipart = null;
                    try {
                        prg4.setIndeterminate(true);
                        fileinprogress4 = true;


                        multipart = new MultipartUtility(Z.url_SavefileOnServer, "UTF-8");
                        Log.d("TAG", "UploadProfile4 : input  username " + username);
                        multipart.addFormField("u", username);
                        if (filename != "") {
                            multipart.addFormField("f", filename);
                            multipart.addFilePart("uf", atach1);
                            Log.d("TAG", "onSuccess: f name- " + filename);
                        } else
                            multipart.addFormField("f", "null");
                        response = multipart.finish();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("TAG", "exp : " + e.getMessage());

                    }

                } else {
                    Log.d("TAG", "file null");

                }


            } catch (Exception ex) {


            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            if (response != null && response.get(0).contains("created")) {
                Log.d("filestatus4", "created");
                prg4.setIndeterminate(false);
                prg4.setProgress(100);
                fileinprogress4 = false;


            } else if (response != null && response.get(0).contains("null")) {
                Log.d("filestatus4", "null");
                fileinprogress4 = false;


            } else {
                Log.d("filestatus4", "file not created");
                fileinprogress4 = false;


            }


        }
    }

    class ShowProgress5 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                File atach1 = new File(filePath);
                lenght = atach1.length();
                username = Z.Decrypt(encUsername, CreateNotification.this);

                if (atach1 != null) {

                    MultipartUtility multipart = null;
                    try {
                        prg5.setIndeterminate(true);
                        fileinprogress5 = true;


                        multipart = new MultipartUtility(Z.url_SavefileOnServer, "UTF-8");
                        Log.d("TAG", "UploadProfile5 : input  username " + username);
                        multipart.addFormField("u", username);
                        if (filename != "") {
                            multipart.addFormField("f", filename);
                            multipart.addFilePart("uf", atach1);
                            Log.d("TAG", "onSuccess: f name- " + filename);
                        } else
                            multipart.addFormField("f", "null");
                        response = multipart.finish();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("TAG", "exp : " + e.getMessage());

                    }

                } else {
                    Log.d("TAG", "file null");

                }


            } catch (Exception ex) {


            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {

            if (response != null && response.get(0).contains("created")) {
                Log.d("filestatus5", "created");
                prg5.setIndeterminate(false);
                prg5.setProgress(100);
                fileinprogress5 = false;


            } else if (response != null && response.get(0).contains("null")) {
                Log.d("filestatus5", "null");
                fileinprogress5 = false;


            } else {
                Log.d("filestatus5", "file not created");
                fileinprogress5 = false;




            }

        }
    }

    class GetForwhome extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", ssnotificationid)); //0

            json = jParser.makeHttpRequest(Z.url_GetForWhomeNotification, "GET", params);
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
                    Forwhomefromdb = Z.Decrypt(Forwhomefromdb, CreateNotification.this);
                    Log.d("Forwhomefromdb", "onPostExecute: " + Forwhomefromdb);
                }

                String tempu = Decrypt(encUsername, digest1, digest2);
                Log.d("Forwhomefromdb", "tempu: " + tempu);


                if (Forwhomefromdb.contains(tempu)) {

                    Forwhomefromdb = Forwhomefromdb.replace(tempu, "");

                }
                Log.d("Forwhomefromdb", "after: " + Forwhomefromdb);

                if (Forwhomefromdb.contains("STUDENT")) {
                    stud.setChecked(true);
                }
                if (Forwhomefromdb.contains("ALL")) {
                    allum.setChecked(true);
                    batchesTags.setText("ALL");
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
                    allum.setChecked(true);
                    batchesTags.setTags(batchyears);
                    showPop = true;

                }
                //k


//                String testStr="STUDENT";
                String testStr = "STUDENT";
                Log.d("TAG1", "before: " + whomsYears);
                whomsYears = whomsYears.replace(Z.Decrypt(encUsername, CreateNotification.this), "");
                Log.d("TAG1", "after1: " + whomsYears);
                whomsYears = whomsYears.replace("STUDENT,", "");
                Log.d("TAG1", "after2: " + whomsYears);
                whomsYears = whomsYears.replace("STUDENT", "");
                whomsYears = whomsYears.replace(",STUDENT", "");

                Log.d("TAG1", "after4: " + whomsYears);
                whomsYears = whomsYears.replace("ALL", "");


            } catch (Exception e) {

                Log.d("whomsYears e:", e.getMessage());
                Toast.makeText(CreateNotification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            edittedFlag = 0;
        }
    }


}
