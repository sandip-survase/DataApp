package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import placeme.octopusites.com.placeme.modal.PgSem;
import placeme.octopusites.com.placeme.modal.PgYear;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.MyProfileAlumniFragment.alumniLog;

public class MyProfilePg extends AppCompatActivity {
    RelativeLayout layouttoshow, layouttohide, pgsemstreamspinner, pgyearstreamspinner;
    RadioGroup radioGroupPg;
    RadioButton radioButtonPgSem, radioButtonPgYear;
    String pattern = "sem";
    EditText yearofpassingpgsem, yearofpassingpgyear, othersemcoursesem;
    EditText pmarkssem1, poutofsem1, ppercentsem1, pmarkssem2, poutofsem2, ppercentsem2, pmarkssem3, poutofsem3, ppercentsem3, pmarkssem4, poutofsem4, ppercentsem4, pmarkssem5, poutofsem5, ppercentsem5, pmarkssem6, poutofsem6, ppercentsem6, pgsemaggregate, othersemcourse, othersemstream, othersemuniversity, schoolnamepgsem;
    TextInputLayout pmarkssem1input, poutofsem1input, ppercentsem1input, pmarksusem2input, poutofsem2input, ppercentsem2input, pmarksusem3input, poutofsem3input, ppercentsem3input, pmarksusem4input, poutofsem4input, ppercentsem4input, pmarksusem5input, poutofsem5input, ppercentsem5input, pmarksusem6input, poutofsem6input, ppercentsem6input, pgsemaggregateinput, othersemcourseinput, othersemstreaminput, othersemuniversityinput, schoolnamepgseminput, yearofpassingpgseminput, pmarksuyear1input, poutofyear1input, ppercentyear1input, pmarksuyear2input, poutofyear2input, ppercentyear2input, pmarksuyear3input, poutofyear3input, ppercentyear3input, pgyearaggregateinput, otheryearcourseinput, otheryearstreaminput, otheryearuniversityinput, schoolnamepgyearinput, yearofpassingpgyearinput;
    Spinner pgsemcourse, pgsemstream, pgsemuniversity;
    EditText pmarksyear1, poutofyear1, ppercentyear1, pmarksyear2, poutofyear2, ppercentyear2, pmarksyear3, poutofyear3, ppercentyear3, pgyearaggregate, otheryearcourse, otheryearstream, otheryearuniversity, schoolnamepgyear;
    Spinner pgyearcourse, pgyearstream, pgyearuniversity;
    String markssem1, outofsem1, percentsem1, markssem2, outofsem2, percentsem2, markssem3, outofsem3, percentsem3, markssem4, outofsem4, percentsem4, markssem5, outofsem5, percentsem5, markssem6, outofsem6, percentsem6, schoolnamepgsemester, aggregatepgsem, monthandyearofpassingpgsem, otherspecifiedcoursepgsem, otherspecifiedstreampgsem = "", otherspecifieduniversitypgsem;
    String selectedCoursepgsem = "", selectedStreampgsem = "", selectedUniversitypgsem = "";
    String encmarkssem1, encoutofsem1, encpercentsem1, encmarkssem2, encoutofsem2, encpercentsem2, encmarkssem3, encoutofsem3, encpercentsem3, encmarkssem4, encoutofsem4, encpercentsem4, encmarkssem5, encoutofsem5, encpercentsem5, encmarkssem6, encoutofsem6, encpercentsem6, encaggregatepgsem, encschoolnamepgsem, encmonthandyearofpassingpgsem, encselectedcoursepgsem, encselectedstreampgsem, encselecteduniversitypgsem;
    String marksyear1, outofyear1, percentyear1, marksyear2, outofyear2, percentyear2, marksyear3, outofyear3, percentyear3, aggregatepgyear, schoolnamepgyears, monthandyearofpassingpgyear, otherspecifiedcoursepgyear, otherspecifiedstreampgyear, otherspecifieduniversitypgyear;
    String selectedCoursepgyear = "", selectedStreampgyear = "", selectedUniversitypgyear = "";
    String encmarksyear1, encoutofyear1, encpercentyear1, encmarksyear2, encoutofyear2, encpercentyear2, encmarksyear3, encoutofyear3, encpercentyear3, encaggregatepgyear, encschoolnamepgyear, encmonthandyearofpassingpgyear, encselectedcoursepgyear, encselectedstreampgyear, encselecteduniversitypgyear;
    int coursecount = 0, streamcount = 0, universitycount = 0;

    ArrayList<String> tosettoStreamslist = new ArrayList<>();
    String username, role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    ArrayAdapter<String> dataAdapter2;
    ArrayAdapter<String> streamlistadapter;
    String[] CourseListWithIds, CourseList;
    String[] StramsListWithIds, StramsList, tempStramsList;

    List<String> courseslist = new ArrayList<String>();
    List<String> streamlist = new ArrayList<String>();
    List<String> universitieslist = new ArrayList<String>();
    String[] courses, streams, universities;
    StudentData s = new StudentData();
    String oldCoursesem = "", oldStreamsem = "", oldUniversitysem = "";
    String oldCourseyear = "", oldStreamyear = "", oldUniversityyear = "";
    int edittedFlag = 0, checksemstream = 0, checkyearstream = 0, isCourseSetsem = 0, isStreamSetsem = 0, isCourseSetyear = 0, isStreamSetyear = 0, isUniversitySetsem = 0, isUniversitySetyear = 0;

    String courseSem = null, streamSem = null, universitySem = null;
    String courseYear = null, streamYear = null, universityYear = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_pg);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username = MySharedPreferencesManager.getUsername(this);
        role = MySharedPreferencesManager.getRole(this);


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Educational Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        pmarksyear1 = (EditText) findViewById(R.id.pmarksyear1);
        poutofyear1 = (EditText) findViewById(R.id.poutofyear1);
        ppercentyear1 = (EditText) findViewById(R.id.ppercentyear1);
        pmarksyear2 = (EditText) findViewById(R.id.pmarksyear2);
        poutofyear2 = (EditText) findViewById(R.id.poutofyear2);
        ppercentyear2 = (EditText) findViewById(R.id.ppercentyear2);
        pmarksyear3 = (EditText) findViewById(R.id.pmarksyear3);
        poutofyear3 = (EditText) findViewById(R.id.poutofyear3);
        ppercentyear3 = (EditText) findViewById(R.id.ppercentyear3);
        pgyearaggregate = (EditText) findViewById(R.id.pgyearaggregate);
        pgyearcourse = (Spinner) findViewById(R.id.pgyearcourse);
        otheryearcourse = (EditText) findViewById(R.id.otheryearcourse);
        pgyearstream = (Spinner) findViewById(R.id.pgyearstream);
        otheryearstream = (EditText) findViewById(R.id.otheryearstream);
        pgyearuniversity = (Spinner) findViewById(R.id.pgyearuniversity);
        otheryearuniversity = (EditText) findViewById(R.id.otheryearuniversity);
        schoolnamepgyear = (EditText) findViewById(R.id.schoolnamepgyear);
        yearofpassingpgyear = (EditText) findViewById(R.id.yearofpassingpgyear);

        pmarkssem1 = (EditText) findViewById(R.id.pmarkssem1);
        poutofsem1 = (EditText) findViewById(R.id.poutofsem1);
        ppercentsem1 = (EditText) findViewById(R.id.ppercentsem1);
        pmarkssem2 = (EditText) findViewById(R.id.pmarkssem2);
        poutofsem2 = (EditText) findViewById(R.id.poutofsem2);
        ppercentsem2 = (EditText) findViewById(R.id.ppercentsem2);
        pmarkssem3 = (EditText) findViewById(R.id.pmarkssem3);
        poutofsem3 = (EditText) findViewById(R.id.poutofsem3);
        ppercentsem3 = (EditText) findViewById(R.id.ppercentsem3);
        pmarkssem4 = (EditText) findViewById(R.id.pmarkssem4);
        poutofsem4 = (EditText) findViewById(R.id.poutofsem4);
        ppercentsem4 = (EditText) findViewById(R.id.ppercentsem4);
        pmarkssem5 = (EditText) findViewById(R.id.pmarkssem5);
        poutofsem5 = (EditText) findViewById(R.id.poutofsem5);
        ppercentsem5 = (EditText) findViewById(R.id.ppercentsem5);
        pmarkssem6 = (EditText) findViewById(R.id.pmarkssem6);
        poutofsem6 = (EditText) findViewById(R.id.poutofsem6);
        ppercentsem6 = (EditText) findViewById(R.id.ppercentsem6);
        pgsemaggregate = (EditText) findViewById(R.id.pgsemaggregate);
        pgsemcourse = (Spinner) findViewById(R.id.pgsemcourse);
        othersemcourse = (EditText) findViewById(R.id.othersemcourse);
        pgsemstream = (Spinner) findViewById(R.id.pgsemstream);
        othersemstream = (EditText) findViewById(R.id.othersemstream);
        pgsemuniversity = (Spinner) findViewById(R.id.pgsemuniversity);
        othersemuniversity = (EditText) findViewById(R.id.othersemuniversity);
        schoolnamepgsem = (EditText) findViewById(R.id.schoolnamepgsem);
        yearofpassingpgsem = (EditText) findViewById(R.id.yearofpassingpgsem);

        othersemcourse = (EditText) findViewById(R.id.othersemcourse);
        Log.d("TAG", "lagech: " + othersemcourse.getText().toString());

        pmarkssem1input = (TextInputLayout) findViewById(R.id.pmarkssem1input);
        ppercentsem1input = (TextInputLayout) findViewById(R.id.ppercentsem1input);
        pmarksusem2input = (TextInputLayout) findViewById(R.id.pmarksusem2input);
        poutofsem2input = (TextInputLayout) findViewById(R.id.poutofsem2input);
        ppercentsem2input = (TextInputLayout) findViewById(R.id.ppercentsem2input);
        pmarksusem3input = (TextInputLayout) findViewById(R.id.pmarksusem3input);
        poutofsem3input = (TextInputLayout) findViewById(R.id.poutofsem3input);
        ppercentsem3input = (TextInputLayout) findViewById(R.id.ppercentsem3input);
        pmarksusem4input = (TextInputLayout) findViewById(R.id.pmarksusem4input);
        poutofsem4input = (TextInputLayout) findViewById(R.id.poutofsem4input);
        ppercentsem4input = (TextInputLayout) findViewById(R.id.ppercentsem4input);
        pmarksusem5input = (TextInputLayout) findViewById(R.id.pmarksusem5input);
        poutofsem5input = (TextInputLayout) findViewById(R.id.poutofsem5input);
        ppercentsem5input = (TextInputLayout) findViewById(R.id.ppercentsem5input);
        pmarksusem6input = (TextInputLayout) findViewById(R.id.pmarksusem6input);
        poutofsem6input = (TextInputLayout) findViewById(R.id.poutofsem6input);
        ppercentsem6input = (TextInputLayout) findViewById(R.id.ppercentsem6input);
        pgsemaggregateinput = (TextInputLayout) findViewById(R.id.pgsemaggregateinput);
        othersemcourseinput = (TextInputLayout) findViewById(R.id.othersemcourseinput);
        othersemstreaminput = (TextInputLayout) findViewById(R.id.othersemstreaminput);
        othersemuniversityinput = (TextInputLayout) findViewById(R.id.othersemuniversityinput);
        schoolnamepgseminput = (TextInputLayout) findViewById(R.id.schoolnamepgseminput);
        yearofpassingpgseminput = (TextInputLayout) findViewById(R.id.yearofpassingpgseminput);
        pmarksuyear1input = (TextInputLayout) findViewById(R.id.pmarksuyear1input);
        poutofyear1input = (TextInputLayout) findViewById(R.id.poutofyear1input);
        ppercentyear1input = (TextInputLayout) findViewById(R.id.ppercentyear1input);
        pmarksuyear2input = (TextInputLayout) findViewById(R.id.pmarksuyear2input);
        poutofyear2input = (TextInputLayout) findViewById(R.id.poutofyear2input);
        ppercentyear2input = (TextInputLayout) findViewById(R.id.ppercentyear2input);
        pmarksuyear3input = (TextInputLayout) findViewById(R.id.pmarksuyear3input);
        poutofyear3input = (TextInputLayout) findViewById(R.id.poutofyear3input);
        ppercentyear3input = (TextInputLayout) findViewById(R.id.ppercentyear3input);
        pgyearaggregateinput = (TextInputLayout) findViewById(R.id.pgyearaggregateinput);
        otheryearcourseinput = (TextInputLayout) findViewById(R.id.otheryearcourseinput);
        otheryearstreaminput = (TextInputLayout) findViewById(R.id.otheryearstreaminput);
        otheryearuniversityinput = (TextInputLayout) findViewById(R.id.otheryearuniversityinput);
        schoolnamepgyearinput = (TextInputLayout) findViewById(R.id.schoolnamepgyearinput);
        yearofpassingpgyearinput = (TextInputLayout) findViewById(R.id.yearofpassingpgyearinput);
        poutofsem1input = (TextInputLayout) findViewById(R.id.poutofsem1input);

        pgsemstreamspinner = (RelativeLayout) findViewById(R.id.pgsemstreamspinner);
        pgyearstreamspinner = (RelativeLayout) findViewById(R.id.pgyearstreamspinner);

        pgsemstreamspinner.setVisibility(View.GONE);
        pgyearstreamspinner.setVisibility(View.GONE);

        pmarkssem1.setTypeface(Z.getBold(this));
        poutofsem1.setTypeface(Z.getBold(this));
        ppercentsem1.setTypeface(Z.getBold(this));
        pmarkssem2.setTypeface(Z.getBold(this));
        poutofsem2.setTypeface(Z.getBold(this));
        ppercentsem2.setTypeface(Z.getBold(this));
        pmarkssem3.setTypeface(Z.getBold(this));
        poutofsem3.setTypeface(Z.getBold(this));
        ppercentsem3.setTypeface(Z.getBold(this));
        pmarkssem4.setTypeface(Z.getBold(this));
        poutofsem4.setTypeface(Z.getBold(this));
        ppercentsem4.setTypeface(Z.getBold(this));
        pmarkssem5.setTypeface(Z.getBold(this));
        poutofsem5.setTypeface(Z.getBold(this));
        ppercentsem5.setTypeface(Z.getBold(this));
        pmarkssem6.setTypeface(Z.getBold(this));
        poutofsem6.setTypeface(Z.getBold(this));
        ppercentsem6.setTypeface(Z.getBold(this));
        pgsemaggregate.setTypeface(Z.getBold(this));
        othersemcourse.setTypeface(Z.getBold(this));
        othersemstream.setTypeface(Z.getBold(this));
        othersemuniversity.setTypeface(Z.getBold(this));
        schoolnamepgsem.setTypeface(Z.getBold(this));
        yearofpassingpgsem.setTypeface(Z.getBold(this));
        yearofpassingpgyear.setTypeface(Z.getBold(this));

        pmarksyear1.setTypeface(Z.getBold(this));
        poutofyear1.setTypeface(Z.getBold(this));
        ppercentyear1.setTypeface(Z.getBold(this));
        pmarksyear2.setTypeface(Z.getBold(this));
        poutofyear2.setTypeface(Z.getBold(this));
        ppercentyear2.setTypeface(Z.getBold(this));
        pmarksyear3.setTypeface(Z.getBold(this));
        poutofyear3.setTypeface(Z.getBold(this));
        ppercentyear3.setTypeface(Z.getBold(this));
        pgyearaggregate.setTypeface(Z.getBold(this));
        otheryearcourse.setTypeface(Z.getBold(this));
        otheryearstream.setTypeface(Z.getBold(this));
        otheryearuniversity.setTypeface(Z.getBold(this));
        schoolnamepgyear.setTypeface(Z.getBold(this));


        pmarkssem1input.setTypeface(Z.getLight(this));
        ppercentsem1input.setTypeface(Z.getLight(this));
        pmarksusem2input.setTypeface(Z.getLight(this));
        poutofsem2input.setTypeface(Z.getLight(this));
        ppercentsem2input.setTypeface(Z.getLight(this));
        pmarksusem3input.setTypeface(Z.getLight(this));
        poutofsem3input.setTypeface(Z.getLight(this));
        ppercentsem3input.setTypeface(Z.getLight(this));
        pmarksusem4input.setTypeface(Z.getLight(this));
        poutofsem4input.setTypeface(Z.getLight(this));
        ppercentsem4input.setTypeface(Z.getLight(this));
        pmarksusem5input.setTypeface(Z.getLight(this));
        poutofsem5input.setTypeface(Z.getLight(this));
        ppercentsem5input.setTypeface(Z.getLight(this));
        pmarksusem6input.setTypeface(Z.getLight(this));
        poutofsem6input.setTypeface(Z.getLight(this));
        ppercentsem6input.setTypeface(Z.getLight(this));
        pgsemaggregateinput.setTypeface(Z.getLight(this));
        othersemcourseinput.setTypeface(Z.getLight(this));
        othersemstreaminput.setTypeface(Z.getLight(this));
        othersemuniversityinput.setTypeface(Z.getLight(this));
        schoolnamepgseminput.setTypeface(Z.getLight(this));
        yearofpassingpgseminput.setTypeface(Z.getLight(this));

        pmarksuyear1input.setTypeface(Z.getLight(this));
        poutofyear1input.setTypeface(Z.getLight(this));
        ppercentyear1input.setTypeface(Z.getLight(this));
        pmarksuyear2input.setTypeface(Z.getLight(this));
        poutofyear2input.setTypeface(Z.getLight(this));
        ppercentyear2input.setTypeface(Z.getLight(this));
        pmarksuyear3input.setTypeface(Z.getLight(this));
        poutofyear3input.setTypeface(Z.getLight(this));
        ppercentyear3input.setTypeface(Z.getLight(this));
        pgyearaggregateinput.setTypeface(Z.getLight(this));
        otheryearcourseinput.setTypeface(Z.getLight(this));
        otheryearstreaminput.setTypeface(Z.getLight(this));
        otheryearuniversityinput.setTypeface(Z.getLight(this));
        schoolnamepgyearinput.setTypeface(Z.getLight(this));
        yearofpassingpgyearinput.setTypeface(Z.getLight(this));
        poutofsem1input.setTypeface(Z.getLight(this));

        TextView pgtxt = (TextView) findViewById(R.id.pgtxt);
        pgtxt.setTypeface(Z.getBold(this));

        CourseListWithIds = getResources().getStringArray(R.array.pgcourses);
        CourseList = new String[CourseListWithIds.length];

        Log.d("TAG", "Course without ID: " + CourseListWithIds[2]);

        for (int i = 0; i < CourseListWithIds.length; i++) {
            Log.d("TAG", "Course without ID: " + CourseList[i]);
            String temp[] = CourseListWithIds[i].split(",");
            CourseList[i] = temp[1];

        }

        StramsListWithIds = getResources().getStringArray(R.array.pgstreams);

        StramsList = new String[StramsListWithIds.length];
        for (int i = 0; i < StramsListWithIds.length; i++) {
            String temp[] = StramsListWithIds[i].split(",");
            StramsList[i] = temp[1];
            Log.d("TAG", "streams without ID: " + StramsList[i]);

        }

        pgsemcourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCoursepgsem = (String) parent.getItemAtPosition(position);
                Log.d(alumniLog, "onItemSelected: " + selectedCoursepgsem);

                String toCompare = selectedCoursepgsem;

                int index = 0;
                for (int i = 0; i < CourseList.length; i++) {
                    if (CourseList[i].equals(toCompare))
                        index = i;
                }
                toCompare = CourseListWithIds[index];
                Log.d("TAG", "toCompare : " + toCompare);
                tosettoStreamslist.clear();

                setStreamAdapter(toCompare);
//                Toast.makeText(MyProfilePg.this, ""+selectedStreampgsem, Toast.LENGTH_SHORT).show();

                TextInputLayout othersemcourseinput = (TextInputLayout) findViewById(R.id.othersemcourseinput);

                if (selectedCoursepgsem.equals("Other")) {
                    othersemcourseinput.setVisibility(View.VISIBLE);
                } else {

                    othersemcourseinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.spinner_item, CourseList) {
            @Override
            public boolean isEnabled(int position) {

                if (position == 0) {

                    return false;
                } else {
                    return true;
                }
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(MyProfilePg.this));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(MyProfilePg.this));

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.dark_color));
                }
                return view;
            }
        };


        pgsemcourse.setAdapter(adapter4);
        pgyearcourse.setAdapter(adapter4);


        universitycount = getResources().getStringArray(R.array.pguniversity).length;
        universities = new String[universitycount];
        universities = getResources().getStringArray(R.array.pguniversity);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, universities) {
            @Override
            public boolean isEnabled(int position) {

                if (position == 0) {

                    return false;
                } else {
                    return true;
                }
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(MyProfilePg.this));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(MyProfilePg.this));

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.dark_color));
                }
                return view;
            }
        };


        pgsemuniversity.setAdapter(adapter2);
        pgyearuniversity.setAdapter(adapter2);

        pgsemuniversity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUniversitypgsem = (String) parent.getItemAtPosition(position);

                TextInputLayout otheruniversityinput = (TextInputLayout) findViewById(R.id.othersemuniversityinput);
                if (selectedUniversitypgsem.equals("Other")) {

                    otheruniversityinput.setVisibility(View.VISIBLE);

                } else {
                    otheruniversityinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pgyearuniversity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUniversitypgyear = (String) parent.getItemAtPosition(position);
                TextInputLayout otheryearuniversityinput = (TextInputLayout) findViewById(R.id.otheryearuniversityinput);
                if (selectedUniversitypgyear.equals("Other")) {

                    otheryearuniversityinput.setVisibility(View.VISIBLE);

                } else {
                    otheryearuniversityinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//


        pgyearcourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCoursepgyear = (String) parent.getItemAtPosition(position);
                TextInputLayout otheryearcourseinput = (TextInputLayout) findViewById(R.id.otheryearcourseinput);


                String toCompare = selectedCoursepgyear;

                int index = 0;
                for (int i = 0; i < CourseList.length; i++) {
                    if (CourseList[i].equals(toCompare))
                        index = i;

                }

                toCompare = CourseListWithIds[index];
                Log.d("TAG", "toCompare : " + toCompare);
                tosettoStreamslist.clear();

                setStreamyearAdapter(toCompare);
                if (selectedCoursepgyear.equals("Other")) {

                    otheryearcourseinput.setVisibility(View.VISIBLE);
                } else {
//                    new GetStreamsYear().execute();
                    otheryearcourseinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        pgsemstream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStreampgsem = (String) parent.getItemAtPosition(position);
                Log.d("TAG", " setOnItemSelectedListener selectedStreampgyear - " + selectedStreampgsem);
                TextInputLayout othersemstreaminput = (TextInputLayout) findViewById(R.id.othersemstreaminput);

                if (selectedStreampgsem.equals("Other")) {
                    Log.d("TAG", " selectedStreampgsem Other - " + selectedStreampgsem);
                    othersemstreaminput.setVisibility(View.VISIBLE);
                } else {

                    othersemstreaminput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pgyearstream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStreampgyear = (String) parent.getItemAtPosition(position);
                Log.d("TAG", " setOnItemSelectedListener selectedStreampgyear - " + selectedStreampgyear);
                TextInputLayout othersemstreaminput = (TextInputLayout) findViewById(R.id.otheryearstreaminput);
                if (selectedStreampgyear.equals("Other")) {
                    Log.d("TAG", " setOnItemSelectedListener Other - " + selectedStreampgyear);
                    othersemstreaminput.setVisibility(View.VISIBLE);
                } else {

                    othersemstreaminput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        pmarkssem1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pmarkssem1input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

//

                poutofsem1input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem1.getText().toString();
                    String s2 = poutofsem1.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem1.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem1input.setError("Kindly enter valid out of marks.");
                            ppercentsem1.setText("");
                        }
                    } else {
                        ppercentsem1.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        poutofsem1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                poutofsem1input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                poutofsem1input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem1.getText().toString();
                    String s2 = poutofsem1.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem1.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem1input.setError("Kindly enter valid out of marks.");
                            ppercentsem1.setText("");
                        }
                    } else {
                        ppercentsem1.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        pmarkssem2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pmarksusem2input.setError(null);

                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                poutofsem2input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem2.getText().toString();
                    String s2 = poutofsem2.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem2.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem2input.setError("Kindly enter valid out of marks.");
                            ppercentsem2.setText("");
                        }
                    } else {
                        ppercentsem2.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        poutofsem2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                poutofsem2input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                poutofsem2input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem2.getText().toString();
                    String s2 = poutofsem2.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem2.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem2input.setError("Kindly enter valid out of marks.");
                            ppercentsem2.setText("");
                        }
                    } else {
                        ppercentsem2.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        pmarkssem3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pmarksusem3input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                poutofsem3input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem3.getText().toString();
                    String s2 = poutofsem3.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem3.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem3input.setError("Kindly enter valid out of marks.");
                            ppercentsem3.setText("");
                        }
                    } else {
                        ppercentsem3.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        poutofsem3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                poutofsem3input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//
                poutofsem3input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem3.getText().toString();
                    String s2 = poutofsem3.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem3.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem3input.setError("Kindly enter valid out of marks.");
                            ppercentsem3.setText("");
                        }
                    } else {
                        ppercentsem3.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        pmarkssem4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pmarksusem4input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                poutofsem4input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem4.getText().toString();
                    String s2 = poutofsem4.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem4.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem4input.setError("Kindly enter valid out of marks.");
                            ppercentsem4.setText("");
                        }
                    } else {
                        ppercentsem4.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        poutofsem4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                poutofsem4input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//

                poutofsem4input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem4.getText().toString();
                    String s2 = poutofsem4.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem4.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem4input.setError("Kindly enter valid out of marks.");
                            ppercentsem4.setText("");
                        }
                    } else {
                        ppercentsem4.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        pmarkssem5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pmarksusem5input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//

                poutofsem5input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem5.getText().toString();
                    String s2 = poutofsem5.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem5.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem5input.setError("Kindly enter valid out of marks.");
                            ppercentsem5.setText("");
                        }
                    } else {
                        ppercentsem5.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        poutofsem5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                poutofsem5input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//
                poutofsem5input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem5.getText().toString();
                    String s2 = poutofsem5.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem5.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem5input.setError("Kindly enter valid out of marks.");
                            ppercentsem5.setText("");
                        }
                    } else {
                        ppercentsem5.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        pmarkssem6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pmarksusem6input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                poutofsem6input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem6.getText().toString();
                    String s2 = poutofsem6.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem6.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem6input.setError("Kindly enter valid out of marks.");
                            ppercentsem6.setText("");
                        }
                    } else {
                        ppercentsem6.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        poutofsem6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                poutofsem6input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//
                poutofsem6input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarkssem6.getText().toString();
                    String s2 = poutofsem6.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentsem6.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofsem6input.setError("Kindly enter valid out of marks.");
                            ppercentsem6.setText("");
                        }
                    } else {
                        ppercentsem6.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        pgsemaggregate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pgsemaggregateinput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        schoolnamepgsem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                schoolnamepgseminput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofpassingpgsem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                yearofpassingpgseminput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        pmarksyear1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pmarksuyear1input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                poutofyear1input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarksyear1.getText().toString();
                    String s2 = poutofyear1.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentyear1.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofyear1input.setError("Kindly enter valid out of marks.");
                            ppercentyear1.setText("");
                        }
                    } else {
                        ppercentyear1.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        poutofyear1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                poutofyear1input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
                poutofyear1input.setError(null);
                try {
                    double percentage = 0;
                    String s1 = pmarksyear1.getText().toString();
                    String s2 = poutofyear1.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentyear1.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofyear1input.setError("Kindly enter valid out of marks.");
                            ppercentyear1.setText("");
                        }
                    } else {
                        ppercentyear1.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        pmarksyear2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pmarksuyear2input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {


                poutofyear2input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarksyear2.getText().toString();
                    String s2 = poutofyear2.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentyear2.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofyear2input.setError("Kindly enter valid out of marks.");
                            ppercentyear2.setText("");
                        }
                    } else {
                        ppercentyear2.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        poutofyear2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                poutofyear2input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//

                poutofyear2input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarksyear2.getText().toString();
                    String s2 = poutofyear2.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentyear2.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofyear2input.setError("Kindly enter valid out of marks.");
                            ppercentyear2.setText("");
                        }
                    } else {
                        ppercentyear2.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        pmarksyear3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pmarksuyear3input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//

                poutofyear3input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarksyear3.getText().toString();
                    String s2 = poutofyear3.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentyear3.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofyear3input.setError("Kindly enter valid out of marks.");
                            ppercentyear3.setText("");
                        }
                    } else {
                        ppercentyear3.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        poutofyear3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                poutofyear3input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//

                poutofyear3input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = pmarksyear3.getText().toString();
                    String s2 = poutofyear3.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                ppercentyear3.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            poutofyear3input.setError("Kindly enter valid out of marks.");
                            ppercentsem3.setText("");
                        }
                    } else {
                        ppercentyear3.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });

        pgyearaggregate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pgyearaggregateinput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        schoolnamepgyear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                schoolnamepgyearinput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        yearofpassingpgyear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                yearofpassingpgyearinput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        yearofpassingpgsem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfilePg.this);
                LayoutInflater inflater = MyProfilePg.this.getLayoutInflater();
                View dialog = inflater.inflate(R.layout.monthyeardialog, null);
                dialogBuilder.setView(dialog);


                final WheelView monthView, yearView;

                final List<String> monthList = new ArrayList<String>();
                final List<String> yearList = new ArrayList<String>();

                monthView = (WheelView) dialog.findViewById(R.id.monthwheel);
                yearView = (WheelView) dialog.findViewById(R.id.yearwheel);

                monthList.add("Jan");
                monthList.add("Feb");
                monthList.add("Mar");
                monthList.add("Apr");
                monthList.add("May");
                monthList.add("Jun");
                monthList.add("Jul");
                monthList.add("Aug");
                monthList.add("Sep");
                monthList.add("Oct");
                monthList.add("Nov");
                monthList.add("Dec");

                Calendar cur = Calendar.getInstance();
                for (int i = 1975; i <= cur.get(Calendar.YEAR) + 2; i++)
                    yearList.add("" + i);


                monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePg.this));
                monthView.setWheelData(monthList);
                yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePg.this));
                yearView.setWheelData(yearList);


                View setselectionview = (View) dialog.findViewById(R.id.setselectionview);
                View cancelselectionview = (View) dialog.findViewById(R.id.cancelselectionview);
                final AlertDialog alertDialog = dialogBuilder.create();


                setselectionview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int monthPosition = monthView.getCurrentPosition();
                        int yearPosition = yearView.getCurrentPosition();

                        String selectedMonth = monthList.get(monthPosition);
                        String selectedYear = yearList.get(yearPosition);

                        setMonthYear("sem", selectedMonth, selectedYear);

                        alertDialog.cancel();
                    }
                });


                cancelselectionview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.cancel();
                    }
                });

                alertDialog.show();

                int w = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());
                int h = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 215, getResources().getDisplayMetrics());
                alertDialog.getWindow().setLayout(w, h);
            }
        });

        yearofpassingpgyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfilePg.this);
                LayoutInflater inflater = MyProfilePg.this.getLayoutInflater();
                View dialog = inflater.inflate(R.layout.monthyeardialog, null);
                dialogBuilder.setView(dialog);

                final WheelView monthView, yearView;

                final List<String> monthList = new ArrayList<String>();
                final List<String> yearList = new ArrayList<String>();

                monthView = (WheelView) dialog.findViewById(R.id.monthwheel);
                yearView = (WheelView) dialog.findViewById(R.id.yearwheel);

                monthList.add("Jan");
                monthList.add("Feb");
                monthList.add("Mar");
                monthList.add("Apr");
                monthList.add("May");
                monthList.add("Jun");
                monthList.add("Jul");
                monthList.add("Aug");
                monthList.add("Sep");
                monthList.add("Oct");
                monthList.add("Nov");
                monthList.add("Dec");

                Calendar cur = Calendar.getInstance();
                for (int i = 1975; i <= cur.get(Calendar.YEAR) + 2; i++)
                    yearList.add("" + i);


                monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePg.this));
                monthView.setWheelData(monthList);
                yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePg.this));
                yearView.setWheelData(yearList);


                View setselectionview = (View) dialog.findViewById(R.id.setselectionview);
                View cancelselectionview = (View) dialog.findViewById(R.id.cancelselectionview);
                final AlertDialog alertDialog = dialogBuilder.create();


                setselectionview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int monthPosition = monthView.getCurrentPosition();
                        int yearPosition = yearView.getCurrentPosition();

                        String selectedMonth = monthList.get(monthPosition);
                        String selectedYear = yearList.get(yearPosition);

                        setMonthYear("year", selectedMonth, selectedYear);

                        alertDialog.cancel();
                    }
                });

                cancelselectionview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.cancel();
                    }
                });

                alertDialog.show();

                int w = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());
                int h = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 215, getResources().getDisplayMetrics());
                alertDialog.getWindow().setLayout(w, h);
            }
        });

        radioGroupPg = (RadioGroup) findViewById(R.id.radioGroupPg);
        radioButtonPgSem = (RadioButton) findViewById(R.id.radioButtonPgSem);
        radioButtonPgYear = (RadioButton) findViewById(R.id.radioButtonPgYear);


        radioButtonPgSem.setTypeface(Z.getBold(this));
        radioButtonPgYear.setTypeface(Z.getBold(this));


        radioGroupPg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButtonPgSem:

                        layouttoshow = (RelativeLayout) findViewById(R.id.pgsemform);
                        layouttohide = (RelativeLayout) findViewById(R.id.pgyearform);
                        layouttoshow.setVisibility(View.VISIBLE);
                        layouttohide.setVisibility(View.GONE);
                        pattern = "sem";
                        break;
                    case R.id.radioButtonPgYear:
                        layouttoshow = (RelativeLayout) findViewById(R.id.pgyearform);
                        layouttohide = (RelativeLayout) findViewById(R.id.pgsemform);
                        layouttoshow.setVisibility(View.VISIBLE);
                        layouttohide.setVisibility(View.GONE);
                        pattern = "year";
                        break;

                }
            }
        });

        ScrollView myprofileintroscrollview = (ScrollView) findViewById(R.id.myprofilepg);
        disableScrollbars(myprofileintroscrollview);


        markssem1 = s.getMarkssem1pgsem();
        outofsem1 = s.getOutofsem1pgsem();
        percentsem1 = s.getPercentagesem1pgsem();
        markssem2 = s.getMarkssem2pgsem();
        outofsem2 = s.getOutofsem2pgsem();
        percentsem2 = s.getPercentagesem2pgsem();
        markssem3 = s.getMarkssem3pgsem();
        outofsem3 = s.getOutofsem3pgsem();
        percentsem3 = s.getPercentagesem3pgsem();
        markssem4 = s.getMarkssem4pgsem();
        outofsem4 = s.getOutofsem4pgsem();
        percentsem4 = s.getPercentagesem4pgsem();
        markssem5 = s.getMarkssem5pgsem();
        outofsem5 = s.getOutofsem5pgsem();
        percentsem5 = s.getPercentagesem5pgsem();
        markssem6 = s.getMarkssem6pgsem();
        outofsem6 = s.getOutofsem6pgsem();
        percentsem6 = s.getPercentagesem6pgsem();
        aggregatepgsem = s.getAggregatepgsem();
        selectedCoursepgsem = s.getCoursepgsem();
        if (s.getStreampgsem() != null)
            selectedStreampgsem = s.getStreampgsem();

        selectedUniversitypgsem = s.getUniversitypgsem();
        oldUniversitysem = selectedUniversitypgsem;
        schoolnamepgsemester = s.getCollegenamepgsem();
        monthandyearofpassingpgsem = s.getYearofpassingpgsem();

        marksyear1 = s.getMarksyear1pgyear();
        outofyear1 = s.getOutofyear1pgyear();
        percentyear1 = s.getPercentageyear1pgyear();
        marksyear2 = s.getMarksyear2pgyear();
        outofyear2 = s.getOutofyear2pgyear();
        percentyear2 = s.getPercentageyear2pgyear();
        marksyear3 = s.getMarksyear3pgyear();
        outofyear3 = s.getOutofyear3pgyear();
        percentyear3 = s.getPercentageyear3pgyear();
        aggregatepgyear = s.getAggregatepgyear();
        selectedCoursepgyear = s.getCoursepgyear();
        if (selectedCoursepgyear == null) {
            selectedCoursepgyear = "";
        }

        selectedStreampgyear = s.getStreampgyear();


        if (selectedStreampgyear == null) {
            selectedStreampgyear = "";
        }


        selectedUniversitypgyear = s.getUniversitypgyear();

//        if(selectedUniversitypgyear==null){
//            selectedUniversitypgyear="- Select University -";
//        }

        oldUniversityyear = selectedUniversitypgyear;
        schoolnamepgyears = s.getCollegenamepgyear();
        monthandyearofpassingpgyear = s.getYearofpassingpgyear();

        setStreamAdapter(selectedCoursepgsem);
        setStreamyearAdapter(selectedCoursepgyear);

        if (markssem1 != null && !markssem1.equals("")) {
            pmarkssem1.setText(markssem1);
            pattern = "sem";
            radioButtonPgSem.setChecked(true);
        }
        if (outofsem1 != null)
            poutofsem1.setText(outofsem1);
        if (percentsem1 != null)
            ppercentsem1.setText(percentsem1);
        if (markssem2 != null)
            pmarkssem2.setText(markssem2);
        if (outofsem2 != null)
            poutofsem2.setText(outofsem2);
        if (percentsem2 != null)
            ppercentsem2.setText(percentsem2);
        if (markssem3 != null)
            pmarkssem3.setText(markssem3);
        if (outofsem3 != null)
            poutofsem3.setText(outofsem3);
        if (percentsem3 != null)
            ppercentsem3.setText(percentsem3);
        if (markssem4 != null)
            pmarkssem4.setText(markssem4);
        if (outofsem4 != null)
            poutofsem4.setText(outofsem4);
        if (percentsem4 != null)
            ppercentsem4.setText(percentsem4);
        if (markssem5 != null)
            pmarkssem5.setText(markssem5);
        if (outofsem5 != null)
            poutofsem5.setText(outofsem5);
        if (percentsem5 != null)
            ppercentsem5.setText(percentsem5);
        if (markssem6 != null)
            pmarkssem6.setText(markssem6);
        if (outofsem6 != null)
            poutofsem6.setText(outofsem6);
        if (percentsem6 != null)
            ppercentsem6.setText(percentsem6);
        if (aggregatepgsem != null)
            pgsemaggregate.setText(aggregatepgsem);
        if (schoolnamepgsemester != null)
            schoolnamepgsem.setText(schoolnamepgsemester);
        if (monthandyearofpassingpgsem != null)
            yearofpassingpgsem.setText(monthandyearofpassingpgsem);


        if (marksyear1 != null && !marksyear1.equals("")) {
            pmarksyear1.setText(marksyear1);
            pattern = "year";
            radioButtonPgYear.setChecked(true);
        }
        if (outofyear1 != null)
            poutofyear1.setText(outofyear1);
        if (percentyear1 != null)
            ppercentyear1.setText(percentyear1);
        if (marksyear2 != null)
            pmarksyear2.setText(marksyear2);
        if (outofyear2 != null)
            poutofyear2.setText(outofyear2);
        if (percentyear2 != null)
            ppercentyear2.setText(percentyear2);
        if (marksyear3 != null)
            pmarksyear3.setText(marksyear3);
        if (outofyear3 != null)
            poutofyear3.setText(outofyear3);
        if (percentyear3 != null)
            ppercentyear3.setText(percentyear3);
        if (aggregatepgyear != null)
            pgyearaggregate.setText(aggregatepgyear);
        if (schoolnamepgyears != null)
            schoolnamepgyear.setText(schoolnamepgyears);
        if (monthandyearofpassingpgyear != null)
            yearofpassingpgyear.setText(monthandyearofpassingpgyear);


        if (selectedCoursepgsem != null) {
            int foundboard = 0;
            for (int i = 1; i < CourseList.length - 1; i++)
                if (selectedCoursepgsem.equals(CourseList[i])) {
                    foundboard = 1;
                    break;
                }
            if (foundboard == 1)
                pgsemcourse.setSelection(adapter4.getPosition(selectedCoursepgsem));
            else {

                if (selectedCoursepgsem.equals("")) {
                    pgsemcourse.setSelection(adapter4.getPosition("- Select Course -"));
                    othersemcourse.setVisibility(View.GONE);
                } else {
                    pgsemcourse.setSelection(adapter4.getPosition("Other"));
                    othersemcourse.setVisibility(View.VISIBLE);
                    othersemcourse.setText(selectedCoursepgsem);
                }
            }

        } else
            selectedCoursepgsem = "- Select Course -";


        if (selectedCoursepgyear != null) {
            int foundboard = 0;
            for (int i = 1; i < CourseList.length - 1; i++)
                if (selectedCoursepgyear.equals(CourseList[i])) {
                    foundboard = 1;
                    break;
                }
            if (foundboard == 1)
                pgyearcourse.setSelection(adapter4.getPosition(selectedCoursepgyear));
            else {

                if (selectedCoursepgyear.equals("")) {
                    pgyearcourse.setSelection(adapter4.getPosition("- Select Course -"));
                    otheryearcourse.setVisibility(View.GONE);
                } else {
                    pgyearcourse.setSelection(adapter4.getPosition("Other"));
                    otheryearcourse.setVisibility(View.VISIBLE);
                    otheryearcourse.setText(selectedCoursepgyear);
                }
            }

        } else
            selectedCoursepgyear = "- Select Course -";


        if (selectedUniversitypgsem != null) {
            if (!selectedUniversitypgsem.equals("")) {

                int foundboard = 0;
                for (int i = 1; i < universities.length - 1; i++)
                    if (selectedUniversitypgsem.equals(universities[i])) {
                        foundboard = 1;
                        Log.d("TAG", "onCreate:  foundboard-" + foundboard);
                        break;
                    }
                Log.d("TAG", "onCreate: selectedBoarddiploma -" + selectedUniversitypgsem);
                if (foundboard == 1)
                    pgsemuniversity.setSelection(adapter2.getPosition(selectedUniversitypgsem));
                else {

                    if (selectedUniversitypgsem.equals("")) {
                        pgsemuniversity.setSelection(adapter2.getPosition("- Select University -"));
                        othersemuniversity.setVisibility(View.GONE);
//                    otherboardd.setText(selectedBoarddiploma);
                    } else {
                        pgsemuniversity.setSelection(adapter2.getPosition("Other"));
                        othersemuniversity.setVisibility(View.VISIBLE);
                        othersemuniversity.setText(selectedUniversitypgsem);
                    }
                }
            } else
                selectedUniversitypgsem = "- Select University -";

        } else
            selectedUniversitypgsem = "- Select University -";


        if (selectedUniversitypgyear != null) {
            int foundboard = 0;
            for (int i = 1; i < universities.length - 1; i++)
                if (selectedUniversitypgyear.equals(universities[i])) {
                    foundboard = 1;
                    Log.d("TAG", "onCreate:  foundboard-" + foundboard);
                    break;
                }
            Log.d("TAG", "onCreate: selectedBoarddiploma -" + selectedUniversitypgyear);
            if (foundboard == 1)
                pgyearuniversity.setSelection(adapter2.getPosition(selectedUniversitypgyear));
            else {

                if (selectedUniversitypgyear.equals("")) {
                    pgyearuniversity.setSelection(adapter2.getPosition("- Select University -"));
                    otheryearuniversity.setVisibility(View.GONE);
//                    otherboardd.setText(selectedBoarddiploma);
                } else {
                    pgyearuniversity.setSelection(adapter2.getPosition("Other"));
                    otheryearuniversity.setVisibility(View.VISIBLE);
                    otheryearuniversity.setText(selectedUniversitypgyear);
                }
            }

        } else
            selectedUniversitypgyear = "- Select University -";


        othersemcourse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                othersemcourseinput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d("TAG", "afterTextChanged: " + othersemcourse.getText().toString());

            }
        });


        edittedFlag = 0;


    }

    //********************************************
    String setStreamAdapter(String a) {
        try {

            String courseArray[] = a.split(",");
            int courseIndex = Integer.parseInt(courseArray[0]);
            Log.d("TAG", "courseIndex: " + courseIndex);

            int index = 0;
            tosettoStreamslist.add("- Select Stream -");
            for (int i = 0; i < StramsListWithIds.length; i++) {
                if (StramsListWithIds[i].contains("" + courseIndex)) {
                    String splitedStream[] = StramsListWithIds[i].split(",");
                    if (splitedStream[0].equals("" + courseIndex)) {

                        tosettoStreamslist.add(splitedStream[1]);
                        splitedStream = null;
                    }
                }
            }

            tempStramsList = tosettoStreamslist.toArray(new String[0]);

            if (tempStramsList != null && tempStramsList.length > 1) {
                dataAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, tempStramsList) {
                    @Override
                    public boolean isEnabled(int position) {

                        if (position == 0) {

                            return false;
                        } else {
                            return true;
                        }
                    }

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        tv.setTypeface(Z.getBold(MyProfilePg.this));
                        return view;
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        tv.setTypeface(Z.getBold(MyProfilePg.this));

                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                        } else {
                            tv.setTextColor(getResources().getColor(R.color.dark_color));
                        }
                        return view;
                    }
                };

                if (tempStramsList.length > 1) {

                    pgsemstream.setAdapter(dataAdapter2);
                    pgsemstreamspinner.setVisibility(View.VISIBLE);
                    checksemstream = 1;

                }


                if (!selectedStreampgsem.equals("- Select Stream -") && !selectedStreampgsem.equals(""))
                    pgsemstream.setSelection(dataAdapter2.getPosition(selectedStreampgsem));

            } else {
                tosettoStreamslist.clear();
                pgsemstreamspinner.setVisibility(View.GONE);
                checksemstream = 0;
            }

        } catch (Exception e) {
        }
        return null;

    }


    String setStreamyearAdapter(String a) {
        try {

            String courseArray[] = a.split(",");
            int courseIndex = Integer.parseInt(courseArray[0]);
            Log.d("TAG", "courseIndex: " + courseIndex);

            int index = 0;
            tosettoStreamslist.add("- Select Stream -");
            for (int i = 0; i < StramsListWithIds.length; i++) {
                if (StramsListWithIds[i].contains("" + courseIndex)) {
                    String splitedStream[] = StramsListWithIds[i].split(",");
                    if (splitedStream[0].equals("" + courseIndex)) {
                        tosettoStreamslist.add(splitedStream[1]);
                        splitedStream = null;
                    }
                }
            }
            tempStramsList = tosettoStreamslist.toArray(new String[0]);
            if (tempStramsList != null && tempStramsList.length > 1) {
                dataAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, tempStramsList) {
                    @Override
                    public boolean isEnabled(int position) {

                        if (position == 0) {

                            return false;
                        } else {
                            return true;
                        }
                    }

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        tv.setTypeface(Z.getBold(MyProfilePg.this));
                        return view;
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        tv.setTypeface(Z.getBold(MyProfilePg.this));

                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                        } else {
                            tv.setTextColor(getResources().getColor(R.color.dark_color));
                        }
                        return view;
                    }
                };
                if (tempStramsList.length > 1) {
                    pgyearstream.setAdapter(dataAdapter2);
                    pgyearstreamspinner.setVisibility(View.VISIBLE);
                    checkyearstream = 1;
                }


                if (!selectedStreampgyear.equals("- Select Stream -") && !selectedStreampgyear.equals(""))
                    pgyearstream.setSelection(dataAdapter2.getPosition(selectedStreampgyear));

            } else {
                tosettoStreamslist.clear();
                pgyearstreamspinner.setVisibility(View.GONE);
                checkyearstream = 0;

            }

        } catch (Exception e) {
        }
        return null;

    }

//    ******************************************

    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }

    void setMonthYear(String tod, String selectedMonth, String selectedYear) {
        if (tod.equals("sem"))
            yearofpassingpgsem.setText(selectedMonth + ", " + selectedYear);
        else
            yearofpassingpgyear.setText(selectedMonth + ", " + selectedYear);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                validateandSave();
                break;

            case android.R.id.home:

                onBackPressed();

                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    void validateandSave() {

        Log.d("TAG", "validateandSave: welcome - ");

        if (pattern.equals("sem")) {
            setBlankYear();
            markssem1 = pmarkssem1.getText().toString();
            outofsem1 = poutofsem1.getText().toString();
            percentsem1 = ppercentsem1.getText().toString();
            markssem2 = pmarkssem2.getText().toString();
            outofsem2 = poutofsem2.getText().toString();
            percentsem2 = ppercentsem2.getText().toString();
            markssem3 = pmarkssem3.getText().toString();
            outofsem3 = poutofsem3.getText().toString();
            percentsem3 = ppercentsem3.getText().toString();
            markssem4 = pmarkssem4.getText().toString();
            outofsem4 = poutofsem4.getText().toString();
            percentsem4 = ppercentsem4.getText().toString();
            markssem5 = pmarkssem5.getText().toString();
            outofsem5 = poutofsem5.getText().toString();
            percentsem5 = ppercentsem5.getText().toString();
            markssem6 = pmarkssem6.getText().toString();
            outofsem6 = poutofsem6.getText().toString();
            percentsem6 = ppercentsem6.getText().toString();
            aggregatepgsem = pgsemaggregate.getText().toString();
            schoolnamepgsemester = schoolnamepgsem.getText().toString();
            monthandyearofpassingpgsem = yearofpassingpgsem.getText().toString();

            Log.d("TAG", "validateandSave: in semester");

            int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0;
            if (markssem1.length() < 1) {
                errorflag1 = 1;
                pmarkssem1input.setError("Kindly enter valid marks");
            } else {
                errorflag1 = 0;
                if (outofsem1.length() < 1) {
                    errorflag1 = 1;
                    poutofsem1input.setError("Kindly enter valid marks");
                } else {
                    errorflag1 = 0;
                    if (percentsem1.length() < 1) {
                        errorflag1 = 1;
                        ppercentsem1input.setError("Incorrect Percentage");
                    } else {
                        errorflag1 = 0;
                        if (markssem2.length() < 1) {
                            errorflag1 = 1;
                            pmarksusem2input.setError("Kindly enter valid marks");
                        } else {
                            errorflag1 = 0;
                            if (outofsem2.length() < 1) {
                                errorflag1 = 1;
                                poutofsem2input.setError("Kindly enter valid marks");
                            } else {
                                errorflag1 = 0;
                                if (percentsem2.length() < 1) {
                                    errorflag1 = 1;
                                    ppercentsem2input.setError("Incorrect Percentage");
                                } else {
                                    errorflag1 = 0;
                                    if (markssem3.length() < 1) {
                                        errorflag1 = 1;
                                        pmarksusem3input.setError("Kindly enter valid marks");
                                    } else {
                                        errorflag1 = 0;
                                        if (outofsem3.length() < 1) {
                                            errorflag1 = 1;
                                            poutofsem3input.setError("Kindly enter valid marks");
                                        } else {
                                            errorflag1 = 0;
                                            if (percentsem3.length() < 1) {
                                                errorflag1 = 1;
                                                ppercentsem3input.setError("Incorrect Percentage");
                                            } else {
                                                errorflag1 = 0;
                                                if (markssem4.length() < 1) {
                                                    errorflag1 = 1;
                                                    pmarksusem4input.setError("Kindly enter valid marks");
                                                } else {
                                                    errorflag1 = 0;
                                                    if (outofsem4.length() < 1) {
                                                        errorflag1 = 1;
                                                        poutofsem4input.setError("Kindly enter valid marks");
                                                    } else {
                                                        errorflag1 = 0;
                                                        if (percentsem4.length() < 1) {
                                                            errorflag1 = 1;
                                                            ppercentsem4input.setError("Incorrect Percentage");
                                                        } else {
                                                            errorflag1 = 0;
                                                            if (markssem5.length() < 1) {
                                                                errorflag1 = 1;
                                                                pmarksusem5input.setError("Kindly enter valid marks");
                                                            } else {
                                                                errorflag1 = 0;
                                                                if (outofsem5.length() < 1) {
                                                                    errorflag1 = 1;
                                                                    poutofsem5input.setError("Kindly enter valid marks");
                                                                } else {
                                                                    errorflag1 = 0;
                                                                    if (percentsem5.length() < 1) {
                                                                        errorflag1 = 1;
                                                                        ppercentsem5input.setError("Incorrect Percentage");
                                                                    } else {
                                                                        errorflag1 = 0;
                                                                        if (markssem6.length() < 1) {
                                                                            errorflag1 = 1;
                                                                            pmarksusem6input.setError("Kindly enter valid marks");
                                                                        } else {
                                                                            errorflag1 = 0;
                                                                            if (outofsem6.length() < 1) {
                                                                                errorflag1 = 1;
                                                                                poutofsem6input.setError("Kindly enter valid marks");
                                                                            } else {
                                                                                errorflag1 = 0;
                                                                                if (percentsem6.length() < 1) {
                                                                                    errorflag1 = 1;
                                                                                    ppercentsem6input.setError("Incorrect Percentage");
                                                                                }
                                                                                {
                                                                                    errorflag1 = 0;
                                                                                    float aggg = 0;
                                                                                    if (!aggregatepgsem.equals(""))
                                                                                        aggg = Float.parseFloat(aggregatepgsem);
                                                                                    if (aggg <= 0 || aggg >= 100) {
                                                                                        errorflag1 = 1;
                                                                                        pgsemaggregateinput.setError("Incorrect Aggregate");
                                                                                    } else {
                                                                                        errorflag1 = 0;
                                                                                        if (selectedCoursepgsem.equals("- Select Course -")) {
                                                                                            errorflag1 = 1;
                                                                                            Toast.makeText(MyProfilePg.this, "Select Course", Toast.LENGTH_LONG).show();
                                                                                        } else {
                                                                                            if (selectedCoursepgsem.equals("Other")) {

                                                                                                Log.d("TAG", "aatala: " + othersemcourse.getText().toString());


                                                                                                otherspecifiedcoursepgsem = othersemcourse.getText().toString();

//                                                                                                Toast.makeText(this, " otherspecifiedcoursepgsem " + otherspecifiedcoursepgsem, Toast.LENGTH_SHORT).show();
                                                                                                if (otherspecifiedcoursepgsem.length() < 3) {
                                                                                                    errorflag2 = 1;
                                                                                                    othersemcourseinput.setError("Kindly enter valid course.");
                                                                                                }
                                                                                            }
                                                                                            if (checksemstream == 1) {
                                                                                                if (selectedStreampgsem.equals("- Select Stream -")) {
                                                                                                    errorflag1 = 1;
                                                                                                    Toast.makeText(MyProfilePg.this, "Select Stream", Toast.LENGTH_LONG).show();
                                                                                                } else {

                                                                                                    if (selectedUniversitypgsem.equals("- Select University -")) {
                                                                                                        errorflag3 = 1;
                                                                                                    } else {
                                                                                                        if (schoolnamepgsemester.length() < 3) {
                                                                                                            errorflag4 = 1;
                                                                                                            schoolnamepgseminput.setError("Kindly enter valid college name");
                                                                                                        } else if (selectedUniversitypgsem.equals("Other")) {
                                                                                                            otherspecifieduniversitypgsem = othersemuniversity.getText().toString();

                                                                                                            if (otherspecifieduniversitypgsem.length() < 3) {
                                                                                                                errorflag4 = 1;

                                                                                                                othersemuniversityinput.setError("Kindly enter valid university");
                                                                                                            }
                                                                                                        } else if (monthandyearofpassingpgsem.length() < 9 || monthandyearofpassingpgsem.length() > 9) {
                                                                                                            errorflag5 = 1;
                                                                                                            yearofpassingpgseminput.setError("Kindly select valid Month,Year");
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (selectedUniversitypgsem.equals("- Select University -")) {
                                                                                                    errorflag3 = 1;
                                                                                                    Toast.makeText(MyProfilePg.this, "Select University", Toast.LENGTH_LONG).show();
                                                                                                } else {
                                                                                                    if (schoolnamepgsemester.length() < 3) {
                                                                                                        errorflag4 = 1;
                                                                                                        schoolnamepgseminput.setError("Kindly enter valid college name");
                                                                                                    } else if (selectedUniversitypgsem.equals("Other")) {
                                                                                                        otherspecifieduniversitypgsem = othersemuniversity.getText().toString();

                                                                                                        if (otherspecifieduniversitypgsem.length() < 3) {
                                                                                                            errorflag4 = 1;

                                                                                                            othersemuniversityinput.setError("Kindly enter valid university");
                                                                                                        }
                                                                                                    } else if (monthandyearofpassingpgsem.length() < 9 || monthandyearofpassingpgsem.length() > 9) {
                                                                                                        errorflag5 = 1;
                                                                                                        yearofpassingpgseminput.setError("Kindly select valid Month,Year");
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
            if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 && errorflag5 == 0) {

                try {
                    if (selectedCoursepgsem.equals("Other"))
                        courseSem = otherspecifiedcoursepgsem;
                    else
                        courseSem = selectedCoursepgsem;

                    if (selectedStreampgsem.equals("Other"))
                        streamSem = otherspecifiedstreampgsem;
                    else
                        streamSem = selectedStreampgsem;

                    if (selectedUniversitypgsem.equals("Other"))
                        universitySem = otherspecifieduniversitypgsem;
                    else
                        universitySem = selectedUniversitypgsem;

                    Log.d("TAG", "validateandSave: setBlankYear courseSem - " + courseSem);
                    Log.d("TAG", "validateandSave: setBlankYear universitySem - " + universitySem);

                    PgSem objSem = new PgSem(markssem1, outofsem1, percentsem1, markssem2, outofsem2, percentsem2, markssem3, outofsem3, percentsem3, markssem4, outofsem4, percentsem4, markssem5, outofsem5, percentsem5, markssem6, outofsem6, percentsem6, schoolnamepgsemester, aggregatepgsem, monthandyearofpassingpgsem, courseSem, selectedStreampgsem, universitySem);
                    String encObjStringSem = OtoString(objSem, MySharedPreferencesManager.getDigest1(MyProfilePg.this), MySharedPreferencesManager.getDigest2(MyProfilePg.this));
                    PgYear objYear = new PgYear(marksyear1, outofyear1, percentyear1, marksyear2, outofyear2, percentyear2, marksyear3, outofyear3, percentyear3, aggregatepgyear, schoolnamepgyears, monthandyearofpassingpgyear, selectedCoursepgyear, selectedStreampgyear, selectedUniversitypgyear);
                    String encObjStringYear = OtoString(objYear, MySharedPreferencesManager.getDigest1(MyProfilePg.this), MySharedPreferencesManager.getDigest2(MyProfilePg.this));

                    new SaveDataPgSem().execute(encObjStringSem);
                    new SaveDataPgYear().execute(encObjStringYear);

                } catch (Exception e) {
                    Toast.makeText(MyProfilePg.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d(alumniLog, "exception -" + e.getMessage());
                }
            }
        } else if (pattern.equals("year")) {

            setBlankSem();
            Log.d("TAG", "validateandSave: after blank sem");
//            pmarksyear1.setError(null);
//            poutofyear1.setError(null);
//            ppercentyear1.setError(null);
//            pmarksyear2.setError(null);
//            poutofyear2.setError(null);
//            ppercentyear2.setError(null);
//            pmarksyear3.setError(null);
//            poutofyear3.setError(null);
//            ppercentyear3.setError(null);
//            pgyearaggregate.setError(null);
//            schoolnamepgyear.setError(null);
//            yearofpassingpgyear.setError(null);

            marksyear1 = pmarksyear1.getText().toString();
            outofyear1 = poutofyear1.getText().toString();
            percentyear1 = ppercentyear1.getText().toString();
            marksyear2 = pmarksyear2.getText().toString();
            outofyear2 = poutofyear2.getText().toString();
            percentyear2 = ppercentyear2.getText().toString();
            marksyear3 = pmarksyear3.getText().toString();
            outofyear3 = poutofyear3.getText().toString();
            percentyear3 = ppercentyear3.getText().toString();
            aggregatepgyear = pgyearaggregate.getText().toString();
            schoolnamepgyears = schoolnamepgyear.getText().toString();
            monthandyearofpassingpgyear = yearofpassingpgyear.getText().toString();

            int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0;
            if (marksyear1.length() < 1) {
                errorflag1 = 1;
                pmarksuyear1input.setError("Kindly enter valid marks");
            } else {
                errorflag1 = 0;
                if (outofyear1.length() < 1) {
                    errorflag1 = 1;
                    poutofyear1input.setError("Kindly enter valid marks");
                } else {
                    errorflag1 = 0;
                    if (percentyear1.length() < 1) {
                        errorflag1 = 1;
                        ppercentyear1input.setError("Incorrect Percentage");
                    } else {
                        errorflag1 = 0;
                        if (marksyear2.length() < 1) {
                            errorflag1 = 1;
                            pmarksusem2input.setError("Kindly enter valid marks");
                        } else {
                            errorflag1 = 0;
                            if (outofyear2.length() < 1) {
                                errorflag1 = 1;
                                poutofyear2input.setError("Kindly enter valid marks");
                            } else {
                                errorflag1 = 0;
                                if (percentyear2.length() < 1) {
                                    errorflag1 = 1;
                                    ppercentyear2input.setError("Incorrect Percentage");
                                } else {
                                    errorflag1 = 0;
                                    if (marksyear3.length() < 1) {
                                        errorflag1 = 1;
                                        pmarksusem3input.setError("Kindly enter valid marks");
                                    } else {
                                        errorflag1 = 0;
                                        if (outofyear3.length() < 1) {
                                            errorflag1 = 1;
                                            poutofyear3input.setError("Kindly enter valid marks");
                                        } else {
                                            errorflag1 = 0;
                                            if (percentyear3.length() < 1) {
                                                errorflag1 = 1;
                                                ppercentyear3input.setError("Incorrect Percentage");
                                            } else {
                                                errorflag1 = 0;
                                                float aggg = 0;

                                                if (!aggregatepgyear.equals(""))
                                                    aggg = Float.parseFloat(aggregatepgyear);
                                                if (aggg <= 0 || aggg >= 100) {
                                                    errorflag1 = 1;
                                                    pgyearaggregateinput.setError("Incorrect Aggregate");
                                                } else {
                                                    errorflag1 = 0;
                                                    if (selectedCoursepgyear.equals("- Select Course -")) {
                                                        errorflag1 = 1;
                                                        Toast.makeText(MyProfilePg.this, "Select Course", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        if (selectedCoursepgyear.equals("Other")) {
                                                            otherspecifiedcoursepgyear = otheryearcourse.getText().toString();
                                                            if (otherspecifiedcoursepgyear.length() < 3) {
                                                                errorflag2 = 1;
                                                                otheryearcourseinput.setError("Kindly enter valid course.");
                                                            }
                                                        }

                                                        if (checkyearstream == 1) {
                                                            if (selectedStreampgyear.equals("- Select Stream -")) {
                                                                errorflag1 = 1;
                                                                Toast.makeText(MyProfilePg.this, "Select Stream", Toast.LENGTH_LONG).show();
                                                            } else {

                                                                if (selectedUniversitypgyear.equals("- Select University -")) {
                                                                    errorflag3 = 1;
                                                                    Toast.makeText(MyProfilePg.this, "Select University", Toast.LENGTH_LONG).show();
                                                                } else {
                                                                    if (schoolnamepgyears.length() < 3) {
                                                                        errorflag4 = 1;
                                                                        schoolnamepgyearinput.setError("Kindly enter valid college name");
                                                                    }
                                                                    if (selectedUniversitypgyear.equals("Other")) {
                                                                        otherspecifieduniversitypgyear = otheryearuniversity.getText().toString();

                                                                        if (otherspecifieduniversitypgyear.length() < 3) {
                                                                            errorflag4 = 1;

                                                                            otheryearuniversityinput.setError("Kindly enter valid university");
                                                                        }
                                                                    }

                                                                    if (monthandyearofpassingpgyear.length() < 9 || monthandyearofpassingpgyear.length() > 9) {
                                                                        errorflag5 = 1;
                                                                        yearofpassingpgyearinput.setError("Kindly select valid Month,Year");
                                                                    }
                                                                }
                                                            }
                                                        } else {

                                                            if (selectedUniversitypgyear.equals("- Select University -")) {
                                                                errorflag3 = 1;
                                                                Toast.makeText(MyProfilePg.this, "Select University", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                if (schoolnamepgyears.length() < 3) {
                                                                    errorflag4 = 1;

                                                                    schoolnamepgyearinput.setError("Kindly enter valid college name");
                                                                }
                                                                if (selectedUniversitypgyear.equals("Other")) {
                                                                    otherspecifieduniversitypgyear = otheryearuniversity.getText().toString();

                                                                    if (otherspecifieduniversitypgyear.length() < 3) {
                                                                        errorflag4 = 1;

                                                                        otheryearuniversityinput.setError("Kindly enter valid university");
                                                                    }
                                                                }

                                                                if (monthandyearofpassingpgyear.length() < 9 || monthandyearofpassingpgyear.length() > 9) {
                                                                    errorflag5 = 1;
                                                                    yearofpassingpgyearinput.setError("Kindly select valid Month,Year");
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
                }
            }
            if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 && errorflag5 == 0) {

                try {

                    if (selectedCoursepgyear.equals("Other"))
                        courseYear = otherspecifiedcoursepgyear;
                    else
                        courseYear = selectedCoursepgyear;

                    if (selectedStreampgyear.equals("Other"))
                        streamYear = otherspecifiedstreampgyear;
                    else
                        streamYear = selectedStreampgyear;

                    if (selectedUniversitypgyear.equals("Other"))
                        universityYear = otherspecifieduniversitypgyear;
                    else
                        universityYear = selectedUniversitypgyear;


                    PgSem objSem = new PgSem(markssem1, outofsem1, percentsem1, markssem2, outofsem2, percentsem2, markssem3, outofsem3, percentsem3, markssem4, outofsem4, percentsem4, markssem5, outofsem5, percentsem5, markssem6, outofsem6, percentsem6, schoolnamepgsemester, aggregatepgsem, monthandyearofpassingpgsem, courseSem, streamSem, universitySem);
                    String encObjStringSem = OtoString(objSem, MySharedPreferencesManager.getDigest1(MyProfilePg.this), MySharedPreferencesManager.getDigest2(MyProfilePg.this));
                    PgYear objYear = new PgYear(marksyear1, outofyear1, percentyear1, marksyear2, outofyear2, percentyear2, marksyear3, outofyear3, percentyear3, aggregatepgyear, schoolnamepgyears, monthandyearofpassingpgyear, courseYear, streamYear, universityYear);
                    String encObjStringYear = OtoString(objYear, MySharedPreferencesManager.getDigest1(MyProfilePg.this), MySharedPreferencesManager.getDigest2(MyProfilePg.this));
                    Log.d("TAG", "validateandSave: encObjStringSem :-" + encObjStringSem);
                    Log.d("TAG", "validateandSave: encObjStringYear :-" + encObjStringYear);

                    new SaveDataPgYear().execute(encObjStringYear);
                    new SaveDataPgSem().execute(encObjStringSem);

                } catch (Exception e) {

                    Log.d("TAG", "validateandSave: exception " + e.getMessage());

                }
            }
        }


    }

    public void setBlankSem() {

        s.setMarkssem1pgsem("");
        s.setOutofsem1pgsem("");
        s.setPercentagesem1pgsem("");
        s.setMarkssem2pgsem("");
        s.setOutofsem2pgsem("");
        s.setPercentagesem2pgsem("");
        s.setMarkssem3pgsem("");
        s.setOutofsem3pgsem("");
        s.setPercentagesem3pgsem("");
        s.setMarkssem4pgsem("");
        s.setOutofsem4pgsem("");
        s.setPercentagesem4pgsem("");
        s.setMarkssem5pgsem("");
        s.setOutofsem5pgsem("");
        s.setPercentagesem5pgsem("");
        s.setMarkssem6pgsem("");
        s.setOutofsem6pgsem("");
        s.setPercentagesem6pgsem("");
        s.setAggregatepgsem("");
        s.setCoursepgsem("");
        s.setStreampgsem("");
        s.setUniversitypgsem("");
        s.setCollegenamepgsem("");
        s.setYearofpassingpgsem("");

        pmarkssem1.setText("");
        poutofsem1.setText("");
        ppercentsem1.setText("");
        pmarkssem2.setText("");
        poutofsem2.setText("");
        ppercentsem2.setText("");
        pmarkssem3.setText("");
        poutofsem3.setText("");
        ppercentsem3.setText("");
        pmarkssem4.setText("");
        poutofsem4.setText("");
        ppercentsem4.setText("");
        pmarkssem5.setText("");
        poutofsem5.setText("");
        ppercentsem5.setText("");
        pmarkssem6.setText("");
        poutofsem6.setText("");
        ppercentsem6.setText("");
        pgsemaggregate.setText("");
        schoolnamepgsem.setText("");
        yearofpassingpgsem.setText("");


        markssem1 = "";
        outofsem1 = "";
        percentsem1 = "";
        markssem2 = "";
        outofsem2 = "";
        percentsem2 = "";
        markssem3 = "";
        outofsem3 = "";
        percentsem3 = "";
        markssem4 = "";
        outofsem4 = "";
        percentsem4 = "";
        markssem5 = "";
        outofsem5 = "";
        percentsem5 = "";
        markssem6 = "";
        outofsem6 = "";
        percentsem6 = "";
        aggregatepgsem = "";

        selectedCoursepgsem = "";
        selectedStreampgsem = "";
        selectedUniversitypgsem = "";

        schoolnamepgsemester = "";
        monthandyearofpassingpgsem = "";

        pgsemcourse.setSelection(0);
        pgsemstream.setSelection(0);

    }

    public void setBlankYear() {

        s.setMarksyear1pgyear("");
        s.setOutofyear1pgyear("");
        s.setPercentageyear1pgyear("");
        s.setMarksyear2pgyear("");
        s.setOutofyear2pgyear("");
        s.setPercentageyear2pgyear("");
        s.setMarksyear3pgyear("");
        s.setOutofyear3pgyear("");
        s.setPercentageyear3pgyear("");
        s.setAggregatepgyear("");
        s.setCoursepgyear("");
        s.setStreampgyear("");
        s.setUniversitypgyear("");
        s.setCollegenamepgyear("");
        s.setYearofpassingpgyear("");

        pmarksyear1.setText("");
        poutofyear1.setText("");
        ppercentyear1.setText("");
        pmarksyear2.setText("");
        poutofyear2.setText("");
        ppercentyear2.setText("");
        pmarksyear3.setText("");
        poutofyear3.setText("");
        ppercentyear3.setText("");
        pgyearaggregate.setText("");
        schoolnamepgyear.setText("");
        yearofpassingpgyear.setText("");

        marksyear1 = "";
        outofyear1 = "";
        percentyear1 = "";
        marksyear2 = "";
        outofyear2 = "";
        percentyear2 = "";
        marksyear3 = "";
        outofyear3 = "";
        percentyear3 = "";
        aggregatepgyear = "";
        selectedCoursepgyear = "";
        selectedStreampgyear = "";
        selectedUniversitypgyear = "";
        oldUniversityyear = "";
        schoolnamepgyears = "";
        monthandyearofpassingpgyear = "";

        pgyearcourse.setSelection(0);
        pgyearstream.setSelection(0);
//        dcourse.setSelection(adapter4.getPosition("- Select Course -"));
    }

    @Override
    public void onBackPressed() {

        //if(edittedFlag==1||!oldCoursesem.equals(selectedCoursepgsem)||!oldStreamsem.equals(selectedStreampgsem)||!oldUniversitysem.equals(selectedUniversitypgsem)||!oldCourseyear.equals(selectedCoursepgyear)||!oldStreamyear.equals(selectedStreampgyear)||!oldUniversityyear.equals(selectedUniversitypgyear)) {
        if (edittedFlag == 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfilePg.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfilePg.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfilePg.this));
                }
            });

            alertDialog.show();
        } else
            MyProfilePg.super.onBackPressed();
    }

    class SaveDataPgSem extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));    //0
            params.add(new BasicNameValuePair("d", param[0]));    //1

            Log.d("TAG", "doInBackground: username =" + username);
            Log.d("TAG", "doInBackground: username =" + param[0]);

            json = jParser.makeHttpRequest(Z.url_savedata_pg_sem, "GET", params);
            Log.d("TAG", "doInBackground: json =" + json);
            try {
                r = json.getString("info");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("TAG", "onPostExecute: SaveDataPgSem result - " + result);
            if (result.equals("success")) {

                if (!markssem1.equals(""))
                    Toast.makeText(MyProfilePg.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();


                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

//                s.setMarkssem1pgsem(markssem1);
//                s.setOutofsem1pgsem(outofsem1);
//                s.setPercentagesem1pgsem(percentsem1);
//                s.setMarkssem2pgsem(markssem2);
//                s.setOutofsem2pgsem(outofsem2);
//                s.setPercentagesem2pgsem(percentsem2);
//                s.setMarkssem3pgsem(markssem3);
//                s.setOutofsem3pgsem(outofsem3);
//                s.setPercentagesem3pgsem(percentsem3);
//                s.setMarkssem4pgsem(markssem4);
//                s.setOutofsem4pgsem(outofsem4);
//                s.setPercentagesem4pgsem(percentsem4);
//                s.setMarkssem5pgsem(markssem5);
//                s.setOutofsem5pgsem(outofsem5);
//                s.setPercentagesem5pgsem(percentsem5);
//                s.setMarkssem6pgsem(markssem6);
//                s.setOutofsem6pgsem(outofsem6);
//                s.setPercentagesem6pgsem(percentsem6);
//                s.setAggregatepgsem(aggregatepgsem);
//                s.setCoursepgsem(selectedCoursepgsem);
//                s.setStreampgsem(selectedStreampgsem);
//                s.setUniversitypgsem(selectedUniversitypgsem);
//                s.setCollegenamepgsem(schoolnamepgsemester);
//                s.setYearofpassingpgsem(monthandyearofpassingpgsem);
//                s.setMarksyear1pgyear(marksyear1);
//                s.setOutofyear1pgyear(outofyear1);
//                s.setPercentageyear1pgyear(percentyear1);
//                s.setMarksyear2pgyear(marksyear2);
//                s.setOutofyear2pgyear(outofyear2);
//                s.setPercentageyear2pgyear(percentyear2);
//                s.setMarksyear3pgyear(marksyear3);
//                s.setOutofyear3pgyear(outofyear3);
//                s.setPercentageyear3pgyear(percentyear3);
//                s.setAggregatepgyear(aggregatepgyear);
//                s.setCoursepgyear(selectedCoursepgyear);
//                s.setStreampgyear(selectedStreampgyear);
//                s.setUniversitypgyear(selectedUniversitypgyear);
//                s.setCollegenamepgyear(schoolnamepgyears);
//                s.setYearofpassingpgyear(monthandyearofpassingpgyear);

            }
//            else
//                Toast.makeText(MyProfilePg.this,"Try again!",Toast.LENGTH_SHORT).show();

//                MyProfilePg.super.onBackPressed();
        }
    }

    class SaveDataPgYear extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));    //0
            params.add(new BasicNameValuePair("d", param[0]));    //0

            Log.d("TAG", "doInBackground: " + param[0]);
            json = jParser.makeHttpRequest(Z.url_savedata_pg_year, "GET", params);
            try {
                r = json.getString("info");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("TAG", "onPostExecute: SaveDataPgYear result - " + result);
            if (result.equals("success")) {
                if (!marksyear1.equals(""))
                    Toast.makeText(MyProfilePg.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();
                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                MyProfilePg.super.onBackPressed();
            }
//             else
//            Toast.makeText(MyProfilePg.this,"Try again!",Toast.LENGTH_SHORT).show();

        }
    }
}
