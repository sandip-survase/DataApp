package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.MyProfileAlumniFragment.alumniLog;

public class MyProfilePg extends AppCompatActivity {
    RelativeLayout layouttoshow,layouttohide;
    RadioGroup radioGroupPg;
    RadioButton radioButtonPgSem,radioButtonPgYear;
    String pattern="sem";
    EditText yearofpassingpgsem,yearofpassingpgyear;
    EditText pmarkssem1,poutofsem1,ppercentsem1,pmarkssem2,poutofsem2,ppercentsem2,pmarkssem3,poutofsem3,ppercentsem3,pmarkssem4,poutofsem4,ppercentsem4,pmarkssem5,poutofsem5,ppercentsem5,pmarkssem6,poutofsem6,ppercentsem6,pgsemaggregate,othersemcourse,othersemstream,othersemuniversity,schoolnamepgsem;
    Spinner pgsemcourse,pgsemstream,pgsemuniversity;
    EditText pmarksyear1,poutofyear1,ppercentyear1,pmarksyear2,poutofyear2,ppercentyear2,pmarksyear3,poutofyear3,ppercentyear3,pgyearaggregate,otheryearcourse,otheryearstream,otheryearuniversity,schoolnamepgyear;
    Spinner pgyearcourse,pgyearstream,pgyearuniversity;
    String markssem1,outofsem1,percentsem1,markssem2,outofsem2,percentsem2,markssem3,outofsem3,percentsem3,markssem4,outofsem4,percentsem4,markssem5,outofsem5,percentsem5,markssem6,outofsem6,percentsem6,schoolnamepgsemester,aggregatepgsem,monthandyearofpassingpgsem,otherspecifiedcoursepgsem,otherspecifiedstreampgsem="",otherspecifieduniversitypgsem;
    String selectedCoursepgsem="",selectedStreampgsem="",selectedUniversitypgsem="";
    String encmarkssem1,encoutofsem1,encpercentsem1,encmarkssem2,encoutofsem2,encpercentsem2,encmarkssem3,encoutofsem3,encpercentsem3,encmarkssem4,encoutofsem4,encpercentsem4,encmarkssem5,encoutofsem5,encpercentsem5,encmarkssem6,encoutofsem6,encpercentsem6,encaggregatepgsem,encschoolnamepgsem,encmonthandyearofpassingpgsem,encselectedcoursepgsem,encselectedstreampgsem,encselecteduniversitypgsem;
    String marksyear1,outofyear1,percentyear1,marksyear2,outofyear2,percentyear2,marksyear3,outofyear3,percentyear3,aggregatepgyear,schoolnamepgyears,monthandyearofpassingpgyear,otherspecifiedcoursepgyear,otherspecifiedstreampgyear,otherspecifieduniversitypgyear;
    String selectedCoursepgyear="",selectedStreampgyear="",selectedUniversitypgyear="";
    String encmarksyear1,encoutofyear1,encpercentyear1,encmarksyear2,encoutofyear2,encpercentyear2,encmarksyear3,encoutofyear3,encpercentyear3,encaggregatepgyear,encschoolnamepgyear,encmonthandyearofpassingpgyear,encselectedcoursepgyear,encselectedstreampgyear,encselecteduniversitypgyear;
    int coursecount=0,streamcount=0,universitycount=0;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;



    List<String> courseslist = new ArrayList<String>();
    List<String> streamlist = new ArrayList<String>();
    List<String> universitieslist = new ArrayList<String>();
    String[] courses,streams,universities;
    StudentData s=new StudentData();
    String oldCoursesem="",oldStreamsem="",oldUniversitysem="";
    String oldCourseyear="",oldStreamyear="",oldUniversityyear="";
    int edittedFlag=0,isCourseSetsem=0,isStreamSetsem=0,isCourseSetyear=0,isStreamSetyear=0,isUniversitySetsem=0,isUniversitySetyear=0;

    String courseSem=null,streamSem=null,universitySem=null;
    String courseYear=null,streamYear=null,universityYear=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_pg);

        sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);
        String role=sharedpreferences.getString("role",null);

        ProfileRole r=new ProfileRole();
        r.setUsername(username);
        r.setRole(role);

        Digest d=new Digest();
        digest1=d.getDigest1();
        digest2=d.getDigest2();

        if(digest1==null||digest2==null) {
            digest1 = sharedpreferences.getString("digest1", null);
            digest2 = sharedpreferences.getString("digest2", null);
            d.setDigest1(digest1);
            d.setDigest2(digest2);
        }

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Educational Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        pmarksyear1=(EditText)findViewById(R.id.pmarksyear1);
        poutofyear1=(EditText)findViewById(R.id.poutofyear1);
        ppercentyear1=(EditText)findViewById(R.id.ppercentyear1);
        pmarksyear2=(EditText)findViewById(R.id.pmarksyear2);
        poutofyear2=(EditText)findViewById(R.id.poutofyear2);
        ppercentyear2=(EditText)findViewById(R.id.ppercentyear2);
        pmarksyear3=(EditText)findViewById(R.id.pmarksyear3);
        poutofyear3=(EditText)findViewById(R.id.poutofyear3);
        ppercentyear3=(EditText)findViewById(R.id.ppercentyear3);
        pgyearaggregate=(EditText)findViewById(R.id.pgyearaggregate);
        pgyearcourse=(Spinner)findViewById(R.id.pgyearcourse);
        otheryearcourse=(EditText)findViewById(R.id.otheryearcourse);
        pgyearstream=(Spinner)findViewById(R.id.pgyearstream);
        otheryearstream=(EditText)findViewById(R.id.otheryearstream);
        pgyearuniversity=(Spinner)findViewById(R.id.pgyearuniversity);
        otheryearuniversity=(EditText)findViewById(R.id.otheryearuniversity);
        schoolnamepgyear=(EditText)findViewById(R.id.schoolnamepgyear);
        yearofpassingpgyear=(EditText)findViewById(R.id.yearofpassingpgyear);

        pmarkssem1=(EditText)findViewById(R.id.pmarkssem1);
        poutofsem1=(EditText)findViewById(R.id.poutofsem1);
        ppercentsem1=(EditText)findViewById(R.id.ppercentsem1);
        pmarkssem2=(EditText)findViewById(R.id.pmarkssem2);
        poutofsem2=(EditText)findViewById(R.id.poutofsem2);
        ppercentsem2=(EditText)findViewById(R.id.ppercentsem2);
        pmarkssem3=(EditText)findViewById(R.id.pmarkssem3);
        poutofsem3=(EditText)findViewById(R.id.poutofsem3);
        ppercentsem3=(EditText)findViewById(R.id.ppercentsem3);
        pmarkssem4=(EditText)findViewById(R.id.pmarkssem4);
        poutofsem4=(EditText)findViewById(R.id.poutofsem4);
        ppercentsem4=(EditText)findViewById(R.id.ppercentsem4);
        pmarkssem5=(EditText)findViewById(R.id.pmarkssem5);
        poutofsem5=(EditText)findViewById(R.id.poutofsem5);
        ppercentsem5=(EditText)findViewById(R.id.ppercentsem5);
        pmarkssem6=(EditText)findViewById(R.id.pmarkssem6);
        poutofsem6=(EditText)findViewById(R.id.poutofsem6);
        ppercentsem6=(EditText)findViewById(R.id.ppercentsem6);
        pgsemaggregate=(EditText)findViewById(R.id.pgsemaggregate);
        pgsemcourse=(Spinner)findViewById(R.id.pgsemcourse);
        othersemcourse=(EditText)findViewById(R.id.othersemcourse);
        pgsemstream=(Spinner)findViewById(R.id.pgsemstream);
        othersemstream=(EditText)findViewById(R.id.othersemstream);
        pgsemuniversity=(Spinner)findViewById(R.id.pgsemuniversity);
        othersemuniversity=(EditText)findViewById(R.id.othersemuniversity);
        schoolnamepgsem=(EditText)findViewById(R.id.schoolnamepgsem);
        yearofpassingpgsem=(EditText)findViewById(R.id.yearofpassingpgsem);

        new GetCourses().execute();
        new GetUniversities().execute();

        pmarkssem1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pmarkssem1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

//
//
//                try {
//
//                    String s1=pmarkssem1.getText().toString();
//                    String s2=poutofsem1.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem1.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}

                poutofsem1.setError(null);

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
                            poutofsem1.setError("Invalid Out Of Marks");
                            ppercentsem1.setText("");
                        }
                    }
                    else
                    {
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
                poutofsem1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarkssem1.getText().toString();
//                    String s2=poutofsem1.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem1.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}

                poutofsem1.setError(null);

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
                            poutofsem1.setError("Invalid Out Of Marks");
                            ppercentsem1.setText("");
                        }
                    }
                    else
                    {
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
                pmarkssem2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

//                try {
//
//                    String s1=pmarkssem2.getText().toString();
//                    String s2=poutofsem2.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem2.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofsem2.setError(null);

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
                            poutofsem2.setError("Invalid Out Of Marks");
                            ppercentsem2.setText("");
                        }
                    }
                    else
                    {
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
                poutofsem2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarkssem2.getText().toString();
//                    String s2=poutofsem2.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem2.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofsem2.setError(null);

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
                            poutofsem2.setError("Invalid Out Of Marks");
                            ppercentsem2.setText("");
                        }
                    }
                    else
                    {
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
                pmarkssem3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarkssem3.getText().toString();
//                    String s2=poutofsem3.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem3.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}

                poutofsem3.setError(null);

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
                            poutofsem3.setError("Invalid Out Of Marks");
                            ppercentsem3.setText("");
                        }
                    }
                    else
                    {
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
                poutofsem3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarkssem3.getText().toString();
//                    String s2=poutofsem3.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem3.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofsem3.setError(null);

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
                            poutofsem3.setError("Invalid Out Of Marks");
                            ppercentsem3.setText("");
                        }
                    }
                    else
                    {
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
                pmarkssem4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarkssem4.getText().toString();
//                    String s2=poutofsem4.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem4.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofsem4.setError(null);

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
                            poutofsem4.setError("Invalid Out Of Marks");
                            ppercentsem4.setText("");
                        }
                    }
                    else
                    {
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
                poutofsem4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarkssem4.getText().toString();
//                    String s2=poutofsem4.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem4.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofsem4.setError(null);

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
                            poutofsem4.setError("Invalid Out Of Marks");
                            ppercentsem4.setText("");
                        }
                    }
                    else
                    {
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
                pmarkssem5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarkssem5.getText().toString();
//                    String s2=poutofsem5.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem5.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofsem5.setError(null);

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
                            poutofsem5.setError("Invalid Out Of Marks");
                            ppercentsem5.setText("");
                        }
                    }
                    else
                    {
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
                poutofsem5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarkssem5.getText().toString();
//                    String s2=poutofsem5.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem5.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofsem5.setError(null);

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
                            poutofsem5.setError("Invalid Out Of Marks");
                            ppercentsem5.setText("");
                        }
                    }
                    else
                    {
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
                pmarkssem6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarkssem6.getText().toString();
//                    String s2=poutofsem6.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem6.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofsem6.setError(null);

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
                            poutofsem6.setError("Invalid Out Of Marks");
                            ppercentsem6.setText("");
                        }
                    }
                    else
                    {
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
                poutofsem6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarkssem6.getText().toString();
//                    String s2=poutofsem6.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentsem6.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofsem6.setError(null);

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
                            poutofsem6.setError("Invalid Out Of Marks");
                            ppercentsem6.setText("");
                        }
                    }
                    else
                    {
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
                pgsemaggregate.setError(null);
                edittedFlag=1;
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
                schoolnamepgsem.setError(null);
                edittedFlag=1;
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
                yearofpassingpgsem.setError(null);
                edittedFlag=1;
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
                pmarksyear1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarksyear1.getText().toString();
//                    String s2=poutofyear1.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentyear1.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofyear1.setError(null);

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
                            poutofyear1.setError("Invalid Out Of Marks");
                            ppercentyear1.setText("");
                        }
                    }
                    else
                    {
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
                poutofyear1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarksyear1.getText().toString();
//                    String s2=poutofyear1.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentyear1.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofyear1.setError(null);

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
                            poutofyear1.setError("Invalid Out Of Marks");
                            ppercentyear1.setText("");
                        }
                    }
                    else
                    {
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
                pmarksyear2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarksyear2.getText().toString();
//                    String s2=poutofyear2.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentyear2.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofyear2.setError(null);

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
                            poutofyear2.setError("Invalid Out Of Marks");
                            ppercentyear2.setText("");
                        }
                    }
                    else
                    {
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
                poutofyear2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarksyear2.getText().toString();
//                    String s2=poutofyear2.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentyear2.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofyear2.setError(null);

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
                            poutofyear2.setError("Invalid Out Of Marks");
                            ppercentyear2.setText("");
                        }
                    }
                    else
                    {
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
                pmarksyear3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarksyear3.getText().toString();
//                    String s2=poutofyear3.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentyear3.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofyear3.setError(null);

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
                            poutofyear3.setError("Invalid Out Of Marks");
                            ppercentyear3.setText("");
                        }
                    }
                    else
                    {
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
                poutofyear3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=pmarksyear3.getText().toString();
//                    String s2=poutofyear3.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            ppercentyear3.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                poutofyear3.setError(null);

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
                            poutofyear3.setError("Invalid Out Of Marks");
                            ppercentsem3.setText("");
                        }
                    }
                    else
                    {
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
                pgyearaggregate.setError(null);
                edittedFlag=1;
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
                schoolnamepgyear.setError(null);
                edittedFlag=1;
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
                yearofpassingpgyear.setError(null);
                edittedFlag=1;
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
                View dialog = inflater.inflate(R.layout.monthyeardialog,null);
                dialogBuilder.setView(dialog);



                final WheelView monthView,yearView;

                final List<String> monthList= new ArrayList<String>();
                final List<String> yearList= new ArrayList<String>();

                monthView= (WheelView)dialog.findViewById(R.id.monthwheel);
                yearView= (WheelView)dialog.findViewById(R.id.yearwheel);

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

                Calendar cur=Calendar.getInstance();
                for(int i=1975;i<=cur.get(Calendar.YEAR);i++)
                    yearList.add(""+i);


                monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePg.this));
                monthView.setWheelData(monthList);
                yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePg.this));
                yearView.setWheelData(yearList);



                View setselectionview=(View)dialog.findViewById(R.id.setselectionview);
                View cancelselectionview=(View)dialog.findViewById(R.id.cancelselectionview);
                final AlertDialog alertDialog = dialogBuilder.create();



                setselectionview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int monthPosition=monthView.getCurrentPosition();
                        int yearPosition=yearView.getCurrentPosition();

                        String selectedMonth=monthList.get(monthPosition);
                        String selectedYear=yearList.get(yearPosition);

                        setMonthYear("sem",selectedMonth,selectedYear);

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

                int w= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());
                int h= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 215, getResources().getDisplayMetrics());
                alertDialog.getWindow().setLayout(w, h);
            }
        });
        yearofpassingpgyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfilePg.this);
                LayoutInflater inflater = MyProfilePg.this.getLayoutInflater();
                View dialog = inflater.inflate(R.layout.monthyeardialog,null);
                dialogBuilder.setView(dialog);



                final WheelView monthView,yearView;

                final List<String> monthList= new ArrayList<String>();
                final List<String> yearList= new ArrayList<String>();

                monthView= (WheelView)dialog.findViewById(R.id.monthwheel);
                yearView= (WheelView)dialog.findViewById(R.id.yearwheel);

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

                Calendar cur=Calendar.getInstance();
                for(int i=1975;i<=cur.get(Calendar.YEAR);i++)
                    yearList.add(""+i);



                monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePg.this));
                monthView.setWheelData(monthList);
                yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePg.this));
                yearView.setWheelData(yearList);



                View setselectionview=(View)dialog.findViewById(R.id.setselectionview);
                View cancelselectionview=(View)dialog.findViewById(R.id.cancelselectionview);
                final AlertDialog alertDialog = dialogBuilder.create();



                setselectionview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int monthPosition=monthView.getCurrentPosition();
                        int yearPosition=yearView.getCurrentPosition();

                        String selectedMonth=monthList.get(monthPosition);
                        String selectedYear=yearList.get(yearPosition);

                        setMonthYear("year",selectedMonth,selectedYear);

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

                int w= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());
                int h= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 215, getResources().getDisplayMetrics());
                alertDialog.getWindow().setLayout(w, h);
            }
        });


        TextView pgtxt=(TextView)findViewById(R.id.pgtxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        pgtxt.setTypeface(custom_font1);

        radioGroupPg=(RadioGroup)findViewById(R.id.radioGroupPg);
        radioButtonPgSem=(RadioButton)findViewById(R.id.radioButtonPgSem);
        radioButtonPgYear=(RadioButton)findViewById(R.id.radioButtonPgYear);

        radioGroupPg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonPgSem:

                        layouttoshow=(RelativeLayout) findViewById(R.id.pgsemform);
                        layouttohide=(RelativeLayout)findViewById(R.id.pgyearform);
                        layouttoshow.setVisibility(View.VISIBLE);
                        layouttohide.setVisibility(View.GONE);
                        pattern="sem";
                        break;
                    case R.id.radioButtonPgYear:
                        layouttoshow=(RelativeLayout)findViewById(R.id.pgyearform);
                        layouttohide=(RelativeLayout)findViewById(R.id.pgsemform);
                        layouttoshow.setVisibility(View.VISIBLE);
                        layouttohide.setVisibility(View.GONE);
                        pattern="year";
                        break;

                }
            }
        });
        ScrollView myprofileintroscrollview=(ScrollView)findViewById(R.id.myprofilepg);
        disableScrollbars(myprofileintroscrollview);


        markssem1=s.getMarkssem1pgsem();
        outofsem1=s.getOutofsem1pgsem();
        percentsem1=s.getPercentagesem1pgsem();
        markssem2=s.getMarkssem2pgsem();
        outofsem2=s.getOutofsem2pgsem();
        percentsem2=s.getPercentagesem2pgsem();
        markssem3=s.getMarkssem3pgsem();
        outofsem3=s.getOutofsem3pgsem();
        percentsem3=s.getPercentagesem3pgsem();
        markssem4=s.getMarkssem4pgsem();
        outofsem4=s.getOutofsem4pgsem();
        percentsem4=s.getPercentagesem4pgsem();
        markssem5=s.getMarkssem5pgsem();
        outofsem5=s.getOutofsem5pgsem();
        percentsem5=s.getPercentagesem5pgsem();
        markssem6=s.getMarkssem6pgsem();
        outofsem6=s.getOutofsem6pgsem();
        percentsem6=s.getPercentagesem6pgsem();
        aggregatepgsem=s.getAggregatepgsem();
        selectedCoursepgsem=s.getCoursepgsem();
        if(s.getStreampgsem()!=null)
            selectedStreampgsem=s.getStreampgsem();
        selectedUniversitypgsem=s.getUniversitypgsem();
        oldUniversitysem=selectedUniversitypgsem;
        schoolnamepgsemester=s.getCollegenamepgsem();
        monthandyearofpassingpgsem=s.getYearofpassingpgsem();



        marksyear1=s.getMarksyear1pgyear();
        outofyear1=s.getOutofyear1pgyear();
        percentyear1=s.getPercentageyear1pgyear();
        marksyear2=s.getMarksyear2pgyear();
        outofyear2=s.getOutofyear2pgyear();
        percentyear2=s.getPercentageyear2pgyear();
        marksyear3=s.getMarksyear3pgyear();
        outofyear3=s.getOutofyear3pgyear();
        percentyear3=s.getPercentageyear3pgyear();
        aggregatepgyear=s.getAggregatepgyear();
        selectedCoursepgyear=s.getCoursepgyear();
        selectedStreampgyear=s.getStreampgyear();
        selectedUniversitypgyear=s.getUniversitypgyear();
        oldUniversityyear=selectedUniversitypgyear;
        schoolnamepgyears=s.getCollegenamepgyear();
        monthandyearofpassingpgyear=s.getYearofpassingpgyear();

        if(markssem1!=null && !markssem1.equals("")) {
            pmarkssem1.setText(markssem1);
            pattern="sem";
            radioButtonPgSem.setChecked(true);
        }
        if(outofsem1!=null)
            poutofsem1.setText(outofsem1);
        if(percentsem1!=null)
            ppercentsem1.setText(percentsem1);
        if(markssem2!=null)
            pmarkssem2.setText(markssem2);
        if(outofsem2!=null)
            poutofsem2.setText(outofsem2);
        if(percentsem2!=null)
            ppercentsem2.setText(percentsem2);
        if(markssem3!=null)
            pmarkssem3.setText(markssem3);
        if(outofsem3!=null)
            poutofsem3.setText(outofsem3);
        if(percentsem3!=null)
            ppercentsem3.setText(percentsem3);
        if(markssem4!=null)
            pmarkssem4.setText(markssem4);
        if(outofsem4!=null)
            poutofsem4.setText(outofsem4);
        if(percentsem4!=null)
            ppercentsem4.setText(percentsem4);
        if(markssem5!=null)
            pmarkssem5.setText(markssem5);
        if(outofsem5!=null)
            poutofsem5.setText(outofsem5);
        if(percentsem5!=null)
            ppercentsem5.setText(percentsem5);
        if(markssem6!=null)
            pmarkssem6.setText(markssem6);
        if(outofsem6!=null)
            poutofsem6.setText(outofsem6);
        if(percentsem6!=null)
            ppercentsem6.setText(percentsem6);
        if(aggregatepgsem!=null)
            pgsemaggregate.setText(aggregatepgsem);
        if(schoolnamepgsemester!=null)
            schoolnamepgsem.setText(schoolnamepgsemester);
        if(monthandyearofpassingpgsem!=null)
            yearofpassingpgsem.setText(monthandyearofpassingpgsem);


        if(marksyear1!=null && !marksyear1.equals("")) {
            pmarksyear1.setText(marksyear1);
            pattern="year";
            radioButtonPgYear.setChecked(true);
        }
        if(outofyear1!=null)
            poutofyear1.setText(outofyear1);
        if(percentyear1!=null)
            ppercentyear1.setText(percentyear1);
        if(marksyear2!=null)
            pmarksyear2.setText(marksyear2);
        if(outofyear2!=null)
            poutofyear2.setText(outofyear2);
        if(percentyear2!=null)
            ppercentyear2.setText(percentyear2);
        if(marksyear3!=null)
            pmarksyear3.setText(marksyear3);
        if(outofyear3!=null)
            poutofyear3.setText(outofyear3);
        if(percentyear3!=null)
            ppercentyear3.setText(percentyear3);
        if(aggregatepgyear!=null)
            pgyearaggregate.setText(aggregatepgyear);
        if(schoolnamepgyears!=null)
            schoolnamepgyear.setText(schoolnamepgyears);
        if(monthandyearofpassingpgyear!=null)
            yearofpassingpgyear.setText(monthandyearofpassingpgyear);

        edittedFlag=0;

    }
    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }
    class GetCourses extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();

            json = jParser.makeHttpRequest(MyConstants.url_getpgcourses, "GET", params);
            try {
                String s = json.getString("count");
                coursecount=Integer.parseInt(s);
                courses=new String[coursecount];
                for(int i=0;i<coursecount;i++)
                {
                    courses[i]=json.getString("course"+i);
                }
            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            courseslist.clear();
            courseslist.add("- Select Course -");
            for(int i=0;i<coursecount;i++)
            {
                courseslist.add(courses[i]);
            }
            populateCourses();
            courseslist.add("Other");
        }
    }

    void populateCourses()
    {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_long, courseslist)
        {
            @Override
            public boolean isEnabled(int position){

                if(position == 0)
                {

                    return false;
                }
                else
                {
                    return true;
                }
            }
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
        };;
        pgsemcourse.setAdapter(dataAdapter);
        pgyearcourse.setAdapter(dataAdapter);
        pgsemcourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCoursepgsem = (String) parent.getItemAtPosition(position);
                TextInputLayout otherboardinput=(TextInputLayout)findViewById(R.id.othersemcourseinput);
                if(selectedCoursepgsem.equals("Other")) {

                    otherboardinput.setVisibility(View.VISIBLE);
                }
                else {
                    new GetStreamsSem().execute();
                    otherboardinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        pgyearcourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCoursepgyear= (String) parent.getItemAtPosition(position);
                TextInputLayout otheryearcourseinput=(TextInputLayout)findViewById(R.id.otheryearcourseinput);
                if(selectedCoursepgyear.equals("Other")) {

                    otheryearcourseinput.setVisibility(View.VISIBLE);
                }
                else {
                    new GetStreamsYear().execute();
                    otheryearcourseinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(isCourseSetsem==0) {
            isCourseSetsem=1;
            if(s.getCoursepgsem()!=null) {
                pgsemcourse.setSelection(dataAdapter.getPosition(s.getCoursepgsem()));
                selectedCoursepgsem = s.getCoursepgsem();
                oldCoursesem = s.getCoursepgsem();
            }
            else
                oldCoursesem = "- Select Course -";
        }
        if(isCourseSetyear==0) {
            isCourseSetyear=1;
            if(s.getCoursepgyear()!=null) {
                pgyearcourse.setSelection(dataAdapter.getPosition(s.getCoursepgyear()));
                selectedCoursepgyear = s.getCoursepgyear();
                oldCourseyear = s.getCoursepgyear();
            }
            else
                oldCourseyear ="- Select Course -" ;
        }


    }

    class GetStreamsSem extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("c",selectedCoursepgsem));
            json = jParser.makeHttpRequest(MyConstants.url_getpgstreams, "GET", params);
            try {
                String s = json.getString("count");
                streamcount=Integer.parseInt(s);
                streams=new String[streamcount];
                for(int i=0;i<streamcount;i++)
                {
                    streams[i]=json.getString("stream"+i);
                }
            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            streamlist.clear();
            streamlist.add("- Select Stream/Specialization -");
            if(streamcount==0)
            {
                RelativeLayout pgsemstreamspinner=(RelativeLayout)findViewById(R.id.pgsemstreamspinner);
                pgsemstreamspinner.setVisibility(View.GONE);
            }
            else{
                RelativeLayout pgsemstreamspinner=(RelativeLayout)findViewById(R.id.pgsemstreamspinner);
                pgsemstreamspinner.setVisibility(View.VISIBLE);
                for (int i = 0; i < streamcount; i++) {
                    streamlist.add(streams[i]);
                }
                populateStreamsSem();
                streamlist.add("Other");
            }
        }
    }
    void populateStreamsSem()
    {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_long, streamlist)
        {
            @Override
            public boolean isEnabled(int position){

                if(position == 0)
                {

                    return false;
                }
                else
                {
                    return true;
                }
            }
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
        };;

        pgsemstream.setAdapter(dataAdapter);

        pgsemstream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStreampgsem= (String) parent.getItemAtPosition(position);
                Log.d(alumniLog, "onItemSelected: "+selectedStreampgsem);

                Toast.makeText(MyProfilePg.this, ""+selectedStreampgsem, Toast.LENGTH_SHORT).show();

                TextInputLayout othersemstreaminput=(TextInputLayout)findViewById(R.id.othersemstreaminput);
                if(selectedStreampgsem.equals("Other")) {

                    othersemstreaminput.setVisibility(View.VISIBLE);
                }
                else {

                    othersemstreaminput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(isStreamSetsem==0) {
            isStreamSetsem=1;
            if(s.getStreampgsem()!=null) {
                pgsemstream.setSelection(dataAdapter.getPosition(s.getStreampgsem()));
                selectedStreampgsem = s.getStreampgsem();
                oldStreamsem = s.getStreampgsem();
            }
            else
                oldStreamsem = "- Select Stream/Specialization -";
        }
    }
    class GetStreamsYear extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("c",selectedCoursepgyear));
            json = jParser.makeHttpRequest(MyConstants.url_getpgstreams, "GET", params);
            try {
                String s = json.getString("count");
                streamcount=Integer.parseInt(s);
                streams=new String[streamcount];
                for(int i=0;i<streamcount;i++)
                {
                    streams[i]=json.getString("stream"+i);
                }
            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            streamlist.clear();
            streamlist.add("- Select Stream/Specialization -");
            if(streamcount==0)
            {
                RelativeLayout pgyearstreamspinner=(RelativeLayout)findViewById(R.id.pgyearstreamspinner);
                pgyearstreamspinner.setVisibility(View.GONE);
            }
            else{
                RelativeLayout pgyearstreamspinner=(RelativeLayout)findViewById(R.id.pgyearstreamspinner);
                pgyearstreamspinner.setVisibility(View.VISIBLE);
                for (int i = 0; i < streamcount; i++) {
                    streamlist.add(streams[i]);
                }
                populateStreamsYear();
                streamlist.add("Other");
            }
        }
    }
    void populateStreamsYear()
    {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_long, streamlist)
        {
            @Override
            public boolean isEnabled(int position){

                if(position == 0)
                {

                    return false;
                }
                else
                {
                    return true;
                }
            }
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
        };;
        pgyearstream.setAdapter(dataAdapter);

        pgyearstream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStreampgyear= (String) parent.getItemAtPosition(position);
                TextInputLayout othersemstreaminput=(TextInputLayout)findViewById(R.id.otheryearstreaminput);
                if(selectedStreampgyear.equals("Other")) {

                    othersemstreaminput.setVisibility(View.VISIBLE);
                }
                else {

                    othersemstreaminput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(isStreamSetyear==0) {
            isStreamSetyear=1;
            if(s.getStreampgyear()!=null) {
                pgyearstream.setSelection(dataAdapter.getPosition(s.getStreampgyear()));
                selectedStreampgyear = s.getStreampgyear();
                oldStreamyear = s.getStreampgyear();
            }
            else
                oldStreamyear = "- Select Stream/Specialization -";
        }


    }
    class GetUniversities extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();

            json = jParser.makeHttpRequest(MyConstants.url_getpguniversities, "GET", params);
            try {
                String s = json.getString("count");
                universitycount=Integer.parseInt(s);
                universities=new String[universitycount];
                for(int i=0;i<universitycount;i++)
                {
                    universities[i]=json.getString("university"+i);
                }

            }catch (Exception e){e.printStackTrace();}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            universitieslist.clear();
            universitieslist.add("- Select University -");
            for(int i=0;i<universitycount;i++)
            {
                universitieslist.add(universities[i]);
            }
            populateUniversities();
            universitieslist.add("Other");
        }
    }
    void populateUniversities()
    {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_long, universitieslist)
        {
            @Override
            public boolean isEnabled(int position){

                if(position == 0)
                {

                    return false;
                }
                else
                {
                    return true;
                }
            }
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
        };;
        pgsemuniversity.setAdapter(dataAdapter);
        pgyearuniversity.setAdapter(dataAdapter);

        pgsemuniversity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUniversitypgsem= (String) parent.getItemAtPosition(position);
                TextInputLayout otheruniversityinput=(TextInputLayout)findViewById(R.id.othersemuniversityinput);
                if(selectedUniversitypgsem.equals("Other")) {

                    otheruniversityinput.setVisibility(View.VISIBLE);

                }
                else {
                    otheruniversityinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
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
                selectedUniversitypgyear= (String) parent.getItemAtPosition(position);
                TextInputLayout otheryearuniversityinput=(TextInputLayout)findViewById(R.id.otheryearuniversityinput);
                if(selectedUniversitypgyear.equals("Other")) {

                    otheryearuniversityinput.setVisibility(View.VISIBLE);

                }
                else {
                    otheryearuniversityinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(isUniversitySetsem==0) {
            isUniversitySetsem=1;
            if(s.getUniversitypgsem()!=null) {
                pgsemuniversity.setSelection(dataAdapter.getPosition(s.getUniversitypgsem()));
                selectedUniversitypgsem = s.getUniversitypgsem();
                oldUniversitysem = s.getUniversitypgsem();
            }
            else
                oldUniversitysem="- Select University -";
        }
        if(isUniversitySetyear==0) {
            isUniversitySetyear=1;
            if(s.getUniversitypgyear()!=null) {
                pgyearuniversity.setSelection(dataAdapter.getPosition(s.getUniversitypgyear()));
                selectedUniversitypgyear = s.getUniversitypgyear();
                oldUniversityyear = s.getUniversitypgyear();
            }
            else
                oldUniversityyear="- Select University -";
        }


    }



    void setMonthYear(String tod,String selectedMonth,String selectedYear)
    {
        if(tod.equals("sem"))
            yearofpassingpgsem.setText(selectedMonth+", "+selectedYear);
        else
            yearofpassingpgyear.setText(selectedMonth+", "+selectedYear);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                validateandSave();
                break;

            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    void validateandSave()
    {

        if(pattern.equals("sem"))
        {
            setBlankYear();

            pmarkssem1.setError(null);
            poutofsem1.setError(null);
            ppercentsem1.setError(null);
            pmarkssem2.setError(null);
            poutofsem2.setError(null);
            ppercentsem2.setError(null);
            pmarkssem3.setError(null);
            poutofsem3.setError(null);
            ppercentsem3.setError(null);
            pmarkssem4.setError(null);
            poutofsem4.setError(null);
            ppercentsem4.setError(null);
            pmarkssem5.setError(null);
            poutofsem5.setError(null);
            ppercentsem5.setError(null);
            pmarkssem6.setError(null);
            poutofsem6.setError(null);
            ppercentsem6.setError(null);
            pgsemaggregate.setError(null);
            schoolnamepgsem.setError(null);
            yearofpassingpgsem.setError(null);

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

            int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0;
            if (markssem1.length() < 1) {
                errorflag1 = 1;
                pmarkssem1.setError("Incorrect Marks");
            } else {
                errorflag1 = 0;
                if (outofsem1.length() < 1) {
                    errorflag1 = 1;
                    pmarkssem1.setError(null);
                    poutofsem1.setError("Incorrect Marks");
                } else {
                    errorflag1 = 0;
                    if (percentsem1.length() < 1) {
                        errorflag1 = 1;
                        pmarkssem1.setError(null);
                        poutofsem1.setError(null);
                        ppercentsem1.setError("Incorrect Percentage");
                    } else {
                        errorflag1 = 0;
                        if (markssem2.length() < 1) {
                            errorflag1 = 1;
                            pmarkssem2.setError("Incorrect Marks");
                        } else {
                            errorflag1 = 0;
                            if (outofsem2.length() < 1) {
                                errorflag1 = 1;
                                pmarkssem2.setError(null);
                                poutofsem2.setError("Incorrect Marks");
                            } else {
                                errorflag1 = 0;
                                if (percentsem2.length() < 1) {
                                    errorflag1 = 1;
                                    pmarkssem2.setError(null);
                                    poutofsem2.setError(null);
                                    ppercentsem2.setError("Incorrect Percentage");
                                } else {
                                    errorflag1 = 0;
                                    if (markssem3.length() < 1) {
                                        errorflag1 = 1;
                                        pmarkssem3.setError("Incorrect Marks");
                                    } else {
                                        errorflag1 = 0;
                                        if (outofsem3.length() < 1) {
                                            errorflag1 = 1;
                                            pmarkssem3.setError(null);
                                            poutofsem3.setError("Incorrect Marks");
                                        } else {
                                            errorflag1 = 0;
                                            if (percentsem3.length() < 1) {
                                                errorflag1 = 1;
                                                pmarkssem3.setError(null);
                                                poutofsem3.setError(null);
                                                ppercentsem3.setError("Incorrect Percentage");
                                            } else {
                                                errorflag1 = 0;
                                                if (markssem4.length() < 1) {
                                                    errorflag1 = 1;
                                                    pmarkssem4.setError("Incorrect Marks");
                                                } else {
                                                    errorflag1 = 0;
                                                    if (outofsem4.length() < 1) {
                                                        errorflag1 = 1;
                                                        pmarkssem4.setError(null);
                                                        poutofsem4.setError("Incorrect Marks");
                                                    } else {
                                                        errorflag1 = 0;
                                                        if (percentsem4.length() < 1) {
                                                            errorflag1 = 1;
                                                            pmarkssem4.setError(null);
                                                            poutofsem4.setError(null);
                                                            ppercentsem4.setError("Incorrect Percentage");
                                                        } else {
                                                            errorflag1 = 0;
                                                            if (markssem5.length() < 1) {
                                                                errorflag1 = 1;
                                                                pmarkssem5.setError("Incorrect Marks");
                                                            } else {
                                                                errorflag1 = 0;
                                                                if (outofsem5.length() < 1) {
                                                                    errorflag1 = 1;
                                                                    pmarkssem5.setError(null);
                                                                    poutofsem5.setError("Incorrect Marks");
                                                                } else {
                                                                    errorflag1 = 0;
                                                                    if (percentsem5.length() < 1) {
                                                                        errorflag1 = 1;
                                                                        pmarkssem5.setError(null);
                                                                        poutofsem5.setError(null);
                                                                        ppercentsem5.setError("Incorrect Percentage");
                                                                    } else {
                                                                        errorflag1 = 0;
                                                                        if (markssem6.length() < 1) {
                                                                            errorflag1 = 1;
                                                                            pmarkssem6.setError("Incorrect Marks");
                                                                        } else {
                                                                            errorflag1 = 0;
                                                                            if (outofsem6.length() < 1) {
                                                                                errorflag1 = 1;
                                                                                pmarkssem6.setError(null);
                                                                                poutofsem6.setError("Incorrect Marks");
                                                                            } else {
                                                                                errorflag1 = 0;
                                                                                if (percentsem6.length() < 1) {
                                                                                    errorflag1 = 1;
                                                                                    pmarkssem6.setError(null);
                                                                                    poutofsem6.setError(null);
                                                                                    ppercentsem6.setError("Incorrect Percentage");
                                                                                } {
                                                                                    errorflag1 = 0;
                                                                                    float aggg=0;
                                                                                    try{
                                                                                        aggg=Float.parseFloat(aggregatepgsem);}catch (NumberFormatException e){errorflag1 = 1; pgsemaggregate.setError("Incorrect Aggregate");}
                                                                                    if (aggg<0||aggg>100) {
                                                                                        errorflag1 = 1;
                                                                                        pmarkssem6.setError(null);
                                                                                        poutofsem6.setError(null);
                                                                                        poutofsem6.setError(null);
                                                                                        pgsemaggregate.setError("Incorrect Aggregate");
                                                                                    } else {
                                                                                        errorflag1 = 0;
                                                                                        if (selectedCoursepgsem.equals("- Select Course -")) {
                                                                                            errorflag1 = 1;
                                                                                            pgsemaggregate.setError(null);
                                                                                            Toast.makeText(MyProfilePg.this, "Select Course", Toast.LENGTH_LONG).show();
                                                                                        } else {
                                                                                            if (selectedCoursepgsem.equals("Other")) {
                                                                                                otherspecifiedcoursepgsem = othersemcourse.getText().toString();
                                                                                                if (otherspecifiedcoursepgsem.length() < 3) {
                                                                                                    errorflag2 = 1;
                                                                                                    pgsemaggregate.setError(null);
                                                                                                    othersemcourse.setError("Invalid Course");
                                                                                                }
                                                                                            }


                                                                                            if (selectedUniversitypgsem.equals("- Select University -")) {
                                                                                                errorflag3 = 1;
                                                                                                pgsemaggregate.setError(null);
                                                                                                othersemcourse.setError(null);
                                                                                                Toast.makeText(MyProfilePg.this, "Select University", Toast.LENGTH_LONG).show();
                                                                                            }
                                                                                            else {
                                                                                                if(schoolnamepgsemester.length()<3)
                                                                                                {
                                                                                                    errorflag4=1;
                                                                                                    schoolnamepgsem.setError("Invalid College Name");
                                                                                                }
                                                                                                if (selectedUniversitypgsem.equals("Other")) {
                                                                                                    otherspecifieduniversitypgsem = othersemuniversity.getText().toString();

                                                                                                    if (otherspecifieduniversitypgsem.length() < 3) {
                                                                                                        errorflag4 = 1;

                                                                                                        othersemuniversity.setError("Invalid University");
                                                                                                    }
                                                                                                }

                                                                                                if (monthandyearofpassingpgsem.length() < 9 || monthandyearofpassingpgsem.length() > 9) {
                                                                                                    errorflag5 = 1;
                                                                                                    othersemuniversity.setError(null);
                                                                                                    yearofpassingpgsem.setError("Invalid Month,Year");
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
            if(errorflag1==0&&errorflag2==0&&errorflag3==0&&errorflag4==0&&errorflag5==0) {

                try {



                    if(selectedCoursepgsem.equals("Other"))
                        courseSem=otherspecifiedcoursepgsem;
                    else
                        courseSem=selectedCoursepgsem;

                    if(selectedStreampgsem.equals("Other"))
                        streamSem=otherspecifiedstreampgsem;
                    else
                        streamSem=selectedStreampgsem;

                    if(selectedUniversitypgsem.equals("Other"))
                        universitySem=otherspecifieduniversitypgsem;
                    else
                        universitySem=selectedUniversitypgsem;

                    PgSem objSem=new PgSem(markssem1,outofsem1,percentsem1,markssem2,outofsem2,percentsem2,markssem3,outofsem3,percentsem3,markssem4,outofsem4,percentsem4,markssem5,outofsem5,percentsem5,markssem6,outofsem6,percentsem6,schoolnamepgsemester,aggregatepgsem,monthandyearofpassingpgsem,courseSem,streamSem,universitySem);
                    String encObjStringSem=OtoString(objSem,MySharedPreferencesManager.getDigest1(MyProfilePg.this),MySharedPreferencesManager.getDigest2(MyProfilePg.this));
                    PgYear objYear=new PgYear(marksyear1,outofyear1,percentyear1,marksyear2,outofyear2,percentyear2,marksyear3,outofyear3,percentyear3,aggregatepgyear,schoolnamepgyears,monthandyearofpassingpgyear,courseYear,streamYear,universityYear);
                    String encObjStringYear=OtoString(objYear,MySharedPreferencesManager.getDigest1(MyProfilePg.this),MySharedPreferencesManager.getDigest2(MyProfilePg.this));

                    new SaveDataPgSem().execute(encObjStringSem);
                    new SaveDataPgYear().execute(encObjStringYear);

                } catch (Exception e) {
                    Toast.makeText(MyProfilePg.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    Log.d(alumniLog,"exception -"+e.getMessage());
                }
            }
        }
        else if(pattern.equals("year")) {

            setBlankSem();

            pmarksyear1.setError(null);
            poutofyear1.setError(null);
            ppercentyear1.setError(null);
            pmarksyear2.setError(null);
            poutofyear2.setError(null);
            ppercentyear2.setError(null);
            pmarksyear3.setError(null);
            poutofyear3.setError(null);
            ppercentyear3.setError(null);
            pgyearaggregate.setError(null);
            schoolnamepgyear.setError(null);
            yearofpassingpgyear.setError(null);

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
                pmarksyear1.setError("Incorrect Marks");
            } else {
                errorflag1 = 0;
                if (outofyear1.length() < 1) {
                    errorflag1 = 1;
                    pmarksyear1.setError(null);
                    poutofyear1.setError("Incorrect Marks");
                } else {
                    errorflag1 = 0;
                    if (percentyear1.length() < 1) {
                        errorflag1 = 1;
                        pmarksyear1.setError(null);
                        poutofyear1.setError(null);
                        ppercentyear1.setError("Incorrect Percentage");
                    } else {
                        errorflag1 = 0;
                        if (marksyear2.length() < 1) {
                            errorflag1 = 1;
                            pmarksyear2.setError("Incorrect Marks");
                        } else {
                            errorflag1 = 0;
                            if (outofyear2.length() < 1) {
                                errorflag1 = 1;
                                pmarksyear2.setError(null);
                                poutofyear2.setError("Incorrect Marks");
                            } else {
                                errorflag1 = 0;
                                if (percentyear2.length() < 1) {
                                    errorflag1 = 1;
                                    pmarksyear2.setError(null);
                                    poutofyear2.setError(null);
                                    ppercentyear2.setError("Incorrect Percentage");
                                } else {
                                    errorflag1 = 0;
                                    if (marksyear3.length() < 1) {
                                        errorflag1 = 1;
                                        pmarksyear3.setError("Incorrect Marks");
                                    } else {
                                        errorflag1 = 0;
                                        if (outofyear3.length() < 1) {
                                            errorflag1 = 1;
                                            pmarksyear3.setError(null);
                                            poutofyear3.setError("Incorrect Marks");
                                        } else {
                                            errorflag1 = 0;
                                            if (percentyear3.length() < 1) {
                                                errorflag1 = 1;
                                                pmarksyear3.setError(null);
                                                poutofyear3.setError(null);
                                                ppercentyear3.setError("Incorrect Percentage");
                                            } else {
                                                errorflag1 = 0;
                                                float aggg = 0;
                                                try {
                                                    aggg = Float.parseFloat(aggregatepgyear);
                                                } catch (NumberFormatException e) {
                                                    errorflag1 = 1;
                                                    pgyearaggregate.setError("Incorrect Aggregate");
                                                }
                                                if (aggg < 0 || aggg > 100) {
                                                    errorflag1 = 1;
                                                    pmarksyear3.setError(null);
                                                    poutofyear3.setError(null);
                                                    poutofyear3.setError(null);
                                                    pgyearaggregate.setError("Incorrect Aggregate");
                                                } else {
                                                    errorflag1 = 0;
                                                    if (selectedCoursepgyear.equals("- Select Course -")) {
                                                        errorflag1 = 1;
                                                        pgyearaggregate.setError(null);
                                                        Toast.makeText(MyProfilePg.this, "Select Course", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        if (selectedCoursepgyear.equals("Other")) {
                                                            otherspecifiedcoursepgyear = otheryearcourse.getText().toString();
                                                            if (otherspecifiedcoursepgyear.length() < 3) {
                                                                errorflag2 = 1;
                                                                pgyearaggregate.setError(null);
                                                                otheryearcourse.setError("Invalid Course");
                                                            }
                                                        }


                                                        if (selectedUniversitypgyear.equals("- Select University -")) {
                                                            errorflag3 = 1;
                                                            pgyearaggregate.setError(null);
                                                            otheryearcourse.setError(null);
                                                            Toast.makeText(MyProfilePg.this, "Select University", Toast.LENGTH_LONG).show();
                                                        } else {
                                                            if (schoolnamepgyears.length() < 3) {
                                                                errorflag4 = 1;
                                                                schoolnamepgyear.setError("Invalid College Name");
                                                            }
                                                            if (selectedUniversitypgyear.equals("Other")) {
                                                                otherspecifieduniversitypgyear = otheryearuniversity.getText().toString();

                                                                if (otherspecifieduniversitypgyear.length() < 3) {
                                                                    errorflag4 = 1;

                                                                    otheryearuniversity.setError("Invalid University");
                                                                }
                                                            }

                                                            if (monthandyearofpassingpgyear.length() < 9 || monthandyearofpassingpgyear.length() > 9) {
                                                                errorflag5 = 1;
                                                                otheryearuniversity.setError(null);
                                                                yearofpassingpgyear.setError("Invalid Month,Year");
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
            if(errorflag1==0&&errorflag2==0&&errorflag3==0&&errorflag4==0&&errorflag5==0) {

                try {




                    if(selectedCoursepgyear.equals("Other"))
                        courseYear=otherspecifiedcoursepgyear;
                    else
                        courseYear=selectedCoursepgyear;

                    if(selectedStreampgyear.equals("Other"))
                        streamYear=otherspecifiedstreampgyear;
                    else
                        streamYear=selectedStreampgyear;
                    if(selectedUniversitypgyear.equals("Other"))
                        universityYear=otherspecifieduniversitypgyear;
                    else
                        universityYear=selectedUniversitypgyear;


                    PgSem objSem=new PgSem(markssem1,outofsem1,percentsem1,markssem2,outofsem2,percentsem2,markssem3,outofsem3,percentsem3,markssem4,outofsem4,percentsem4,markssem5,outofsem5,percentsem5,markssem6,outofsem6,percentsem6,schoolnamepgsemester,aggregatepgsem,monthandyearofpassingpgsem,courseSem,streamSem,universitySem);
                    String encObjStringSem=OtoString(objSem,MySharedPreferencesManager.getDigest1(MyProfilePg.this),MySharedPreferencesManager.getDigest2(MyProfilePg.this));
                    PgYear objYear=new PgYear(marksyear1,outofyear1,percentyear1,marksyear2,outofyear2,percentyear2,marksyear3,outofyear3,percentyear3,aggregatepgyear,schoolnamepgyears,monthandyearofpassingpgyear,courseYear,streamYear,universityYear);
                    String encObjStringYear=OtoString(objYear,MySharedPreferencesManager.getDigest1(MyProfilePg.this),MySharedPreferencesManager.getDigest2(MyProfilePg.this));

                    new SaveDataPgYear().execute(encObjStringYear);
                    new SaveDataPgSem().execute(encObjStringSem);

                }catch (Exception e){}
            }
        }


    }
    class SaveDataPgSem extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));    //0
            params.add(new BasicNameValuePair("d",param[0]));    //1

            json = jParser.makeHttpRequest(MyConstants.url_savedata_pg_sem, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(MyProfilePg.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();

                setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);

                s.setMarkssem1pgsem(markssem1);
                s.setOutofsem1pgsem(outofsem1);
                s.setPercentagesem1pgsem(percentsem1);
                s.setMarkssem2pgsem(markssem2);
                s.setOutofsem2pgsem(outofsem2);
                s.setPercentagesem2pgsem(percentsem2);
                s.setMarkssem3pgsem(markssem3);
                s.setOutofsem3pgsem(outofsem3);
                s.setPercentagesem3pgsem(percentsem3);
                s.setMarkssem4pgsem(markssem4);
                s.setOutofsem4pgsem(outofsem4);
                s.setPercentagesem4pgsem(percentsem4);
                s.setMarkssem5pgsem(markssem5);
                s.setOutofsem5pgsem(outofsem5);
                s.setPercentagesem5pgsem(percentsem5);
                s.setMarkssem6pgsem(markssem6);
                s.setOutofsem6pgsem(outofsem6);
                s.setPercentagesem6pgsem(percentsem6);
                s.setAggregatepgsem(aggregatepgsem);
                s.setCoursepgsem(selectedCoursepgsem);
                s.setStreampgsem(selectedStreampgsem);
                s.setUniversitypgsem(selectedUniversitypgsem);
                s.setCollegenamepgsem(schoolnamepgsemester);
                s.setYearofpassingpgsem(monthandyearofpassingpgsem);
                s.setMarksyear1pgyear(marksyear1);
                s.setOutofyear1pgyear(outofyear1);
                s.setPercentageyear1pgyear(percentyear1);
                s.setMarksyear2pgyear(marksyear2);
                s.setOutofyear2pgyear(outofyear2);
                s.setPercentageyear2pgyear(percentyear2);
                s.setMarksyear3pgyear(marksyear3);
                s.setOutofyear3pgyear(outofyear3);
                s.setPercentageyear3pgyear(percentyear3);
                s.setAggregatepgyear(aggregatepgyear);
                s.setCoursepgyear(selectedCoursepgyear);
                s.setStreampgyear(selectedStreampgyear);
                s.setUniversitypgyear(selectedUniversitypgyear);
                s.setCollegenamepgyear(schoolnamepgyears);
                s.setYearofpassingpgyear(monthandyearofpassingpgyear);

                MyProfilePg.super.onBackPressed();
            }

        }
    }
    class SaveDataPgYear extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));    //0
            params.add(new BasicNameValuePair("d",param[0]));    //0

            json = jParser.makeHttpRequest(MyConstants.url_savedata_pg_year, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(MyProfilePg.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();

                ProfileRole r=new ProfileRole();
                String role=r.getRole();
                if(role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                  setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                MyProfilePg.super.onBackPressed();
            }
            Toast.makeText(MyProfilePg.this,result,Toast.LENGTH_SHORT).show();

        }
    }

    public void setBlankSem(){

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


        markssem1="";
        outofsem1="";
        percentsem1="";
        markssem2="";
        outofsem2="";
        percentsem2="";
        markssem3="";
        outofsem3="";
        percentsem3="";
        markssem4="";
        outofsem4="";
        percentsem4="";
        markssem5="";
        outofsem5="";
        percentsem5="";
        markssem6="";
        outofsem6="";
        percentsem6="";
        aggregatepgsem="";
        selectedCoursepgsem="";
            selectedStreampgsem="";
        selectedUniversitypgsem="";
        schoolnamepgsemester="";
        monthandyearofpassingpgsem="";


        try {
            byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
            String sPadding = "ISO10126Padding";


            byte[] markssem1Bytes = markssem1.getBytes("UTF-8");
            byte[] outofsem1Bytes = outofsem1.getBytes("UTF-8");
            byte[] percentsem1Bytes = percentsem1.getBytes("UTF-8");
            byte[] markssem2Bytes = markssem2.getBytes("UTF-8");
            byte[] outofsem2Bytes = outofsem2.getBytes("UTF-8");
            byte[] percentsem2Bytes = percentsem2.getBytes("UTF-8");
            byte[] markssem3Bytes = markssem3.getBytes("UTF-8");
            byte[] outofsem3Bytes = outofsem3.getBytes("UTF-8");
            byte[] percentsem3Bytes = percentsem3.getBytes("UTF-8");
            byte[] markssem4Bytes = markssem4.getBytes("UTF-8");
            byte[] outofsem4Bytes = outofsem4.getBytes("UTF-8");
            byte[] percentsem4Bytes = percentsem4.getBytes("UTF-8");
            byte[] markssem5Bytes = markssem5.getBytes("UTF-8");
            byte[] outofsem5Bytes = outofsem5.getBytes("UTF-8");
            byte[] percentsem5Bytes = percentsem5.getBytes("UTF-8");
            byte[] markssem6Bytes = markssem6.getBytes("UTF-8");
            byte[] outofsem6Bytes = outofsem6.getBytes("UTF-8");
            byte[] percentsem6Bytes = percentsem6.getBytes("UTF-8");
            byte[] aggregateBytes = aggregatepgsem.getBytes("UTF-8");
            byte[] schoolnameBytes = schoolnamepgsemester.getBytes("UTF-8");
            byte[] monthandyearofpassingBytes = monthandyearofpassingpgsem.getBytes("UTF-8");

            byte[] selectedcourseBytes=null,selectedstreamBytes=null,selecteduniversityBytes=null;
            if(selectedCoursepgsem.equals("Other"))
                selectedcourseBytes=otherspecifiedcoursepgsem.getBytes("UTF-8");
            else
                selectedcourseBytes=selectedCoursepgsem.getBytes("UTF-8");
            Log.d(alumniLog,"selectedcourseBytes "+selectedCoursepgsem);
            Log.d(alumniLog,"selectedstreamBytes "+selectedStreampgsem);


            if(selectedStreampgsem.equals("Other")){
                Log.d(alumniLog, "validateandSave: before"+otherspecifiedstreampgsem);
                selectedstreamBytes=otherspecifiedstreampgsem.getBytes("UTF-8");
                Log.d(alumniLog, "validateandSave: after"+otherspecifiedstreampgsem);
            }

            else
                selectedstreamBytes=selectedStreampgsem.getBytes("UTF-8");
            Log.d(alumniLog,"selectedstreamBytes "+selectedStreampgsem);


            if(selectedUniversitypgsem.equals("Other"))
                selecteduniversityBytes=otherspecifieduniversitypgsem.getBytes("UTF-8");
            else
                selecteduniversityBytes=selectedUniversitypgsem.getBytes("UTF-8");
            Log.d(alumniLog,"u r in after utf");

            byte[] markssem1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem1Bytes);
            encmarkssem1=new String(SimpleBase64Encoder.encode(markssem1EncryptedBytes));
            byte[] markssem2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem2Bytes);
            encmarkssem2=new String(SimpleBase64Encoder.encode(markssem2EncryptedBytes));
            byte[] markssem3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem3Bytes);
            encmarkssem3=new String(SimpleBase64Encoder.encode(markssem3EncryptedBytes));
            byte[] markssem4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem4Bytes);
            encmarkssem4=new String(SimpleBase64Encoder.encode(markssem4EncryptedBytes));
            byte[] markssem5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem5Bytes);
            encmarkssem5=new String(SimpleBase64Encoder.encode(markssem5EncryptedBytes));
            byte[] markssem6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem6Bytes);
            encmarkssem6=new String(SimpleBase64Encoder.encode(markssem6EncryptedBytes));
            byte[] outofsem1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem1Bytes);
            encoutofsem1=new String(SimpleBase64Encoder.encode(outofsem1EncryptedBytes));
            byte[] outofsem2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem2Bytes);
            encoutofsem2=new String(SimpleBase64Encoder.encode(outofsem2EncryptedBytes));
            byte[] outofsem3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem3Bytes);
            encoutofsem3=new String(SimpleBase64Encoder.encode(outofsem3EncryptedBytes));
            byte[] outofsem4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem4Bytes);
            encoutofsem4=new String(SimpleBase64Encoder.encode(outofsem4EncryptedBytes));
            byte[] outofsem5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem5Bytes);
            encoutofsem5=new String(SimpleBase64Encoder.encode(outofsem5EncryptedBytes));
            byte[] outofsem6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem6Bytes);
            encoutofsem6=new String(SimpleBase64Encoder.encode(outofsem6EncryptedBytes));
            byte[] percentsem1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem1Bytes);
            encpercentsem1=new String(SimpleBase64Encoder.encode(percentsem1EncryptedBytes));
            byte[] percentsem2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem2Bytes);
            encpercentsem2=new String(SimpleBase64Encoder.encode(percentsem2EncryptedBytes));
            byte[] percentsem3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem3Bytes);
            encpercentsem3=new String(SimpleBase64Encoder.encode(percentsem3EncryptedBytes));
            byte[] percentsem4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem4Bytes);
            encpercentsem4=new String(SimpleBase64Encoder.encode(percentsem4EncryptedBytes));
            byte[] percentsem5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem5Bytes);
            encpercentsem5=new String(SimpleBase64Encoder.encode(percentsem5EncryptedBytes));
            byte[] percentsem6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem6Bytes);
            encpercentsem6=new String(SimpleBase64Encoder.encode(percentsem6EncryptedBytes));
            byte[] aggregateEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, aggregateBytes);
            encaggregatepgsem=new String(SimpleBase64Encoder.encode(aggregateEncryptedBytes));
            byte[] schoolnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, schoolnameBytes);
            encschoolnamepgsem=new String(SimpleBase64Encoder.encode(schoolnameEncryptedBytes));
            byte[] monthandyearofpassingEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, monthandyearofpassingBytes);
            encmonthandyearofpassingpgsem=new String(SimpleBase64Encoder.encode(monthandyearofpassingEncryptedBytes));
            byte[] selectedcourseEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, selectedcourseBytes);
            encselectedcoursepgsem=new String(SimpleBase64Encoder.encode(selectedcourseEncryptedBytes));
            byte[] selectedstreamEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, selectedstreamBytes);
            encselectedstreampgsem=new String(SimpleBase64Encoder.encode(selectedstreamEncryptedBytes));
            byte[] selecteduniversityEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, selecteduniversityBytes);
            encselecteduniversitypgsem=new String(SimpleBase64Encoder.encode(selecteduniversityEncryptedBytes));

        } catch (Exception e) {
            Toast.makeText(MyProfilePg.this,e.getMessage(),Toast.LENGTH_LONG).show();
            Log.d(alumniLog,"exception -"+e.getMessage());
        }

    }

    public void setBlankYear(){

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

        marksyear1="";
        outofyear1="";
        percentyear1="";
        marksyear2="";
        outofyear2="";
        percentyear2="";
        marksyear3="";
        outofyear3="";
        percentyear3="";
        aggregatepgyear="";
        selectedCoursepgyear="";
        selectedStreampgyear="";
        selectedUniversitypgyear="";
        oldUniversityyear="";
        schoolnamepgyears="";
        monthandyearofpassingpgyear="";


        try {
            byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
            String sPadding = "ISO10126Padding";

            byte[] marksyear1Bytes = marksyear1.getBytes("UTF-8");
            byte[] outofyear1Bytes = outofyear1.getBytes("UTF-8");
            byte[] percentyear1Bytes = percentyear1.getBytes("UTF-8");
            byte[] marksyear2Bytes = marksyear2.getBytes("UTF-8");
            byte[] outofyear2Bytes = outofyear2.getBytes("UTF-8");
            byte[] percentyear2Bytes = percentyear2.getBytes("UTF-8");
            byte[] marksyear3Bytes = marksyear3.getBytes("UTF-8");
            byte[] outofyear3Bytes = outofyear3.getBytes("UTF-8");
            byte[] percentyear3Bytes = percentyear3.getBytes("UTF-8");

            byte[] aggregateBytes = aggregatepgyear.getBytes("UTF-8");
            byte[] schoolnameBytes = schoolnamepgyears.getBytes("UTF-8");
            byte[] monthandyearofpassingBytes = monthandyearofpassingpgyear.getBytes("UTF-8");

            byte[] selectedcourseBytes=null,selectedstreamBytes=null,selecteduniversityBytes=null;
            if(selectedCoursepgyear.equals("Other"))
                selectedcourseBytes=otherspecifiedcoursepgyear.getBytes("UTF-8");
            else
                selectedcourseBytes=selectedCoursepgyear.getBytes("UTF-8");
            if(selectedStreampgyear.equals("Other"))
                selectedstreamBytes=otherspecifiedstreampgyear.getBytes("UTF-8");
            else
                selectedstreamBytes=selectedStreampgyear.getBytes("UTF-8");
            if(selectedUniversitypgyear.equals("Other"))
                selecteduniversityBytes=otherspecifieduniversitypgyear.getBytes("UTF-8");
            else
                selecteduniversityBytes=selectedUniversitypgyear.getBytes("UTF-8");

            byte[] marksyear1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, marksyear1Bytes);
            encmarksyear1=new String(SimpleBase64Encoder.encode(marksyear1EncryptedBytes));
            byte[] marksyear2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, marksyear2Bytes);
            encmarksyear2=new String(SimpleBase64Encoder.encode(marksyear2EncryptedBytes));
            byte[] marksyear3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, marksyear3Bytes);
            encmarksyear3=new String(SimpleBase64Encoder.encode(marksyear3EncryptedBytes));
            byte[] outofyear1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofyear1Bytes);
            encoutofyear1=new String(SimpleBase64Encoder.encode(outofyear1EncryptedBytes));
            byte[] outofyear2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofyear2Bytes);
            encoutofyear2=new String(SimpleBase64Encoder.encode(outofyear2EncryptedBytes));
            byte[] outofyear3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofyear3Bytes);
            encoutofyear3=new String(SimpleBase64Encoder.encode(outofyear3EncryptedBytes));
            byte[] percentyear1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentyear1Bytes);
            encpercentyear1=new String(SimpleBase64Encoder.encode(percentyear1EncryptedBytes));
            byte[] percentyear2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentyear2Bytes);
            encpercentyear2=new String(SimpleBase64Encoder.encode(percentyear2EncryptedBytes));
            byte[] percentyear3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentyear3Bytes);
            encpercentyear3=new String(SimpleBase64Encoder.encode(percentyear3EncryptedBytes));
            byte[] aggregateEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, aggregateBytes);
            encaggregatepgyear=new String(SimpleBase64Encoder.encode(aggregateEncryptedBytes));
            byte[] schoolnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, schoolnameBytes);
            encschoolnamepgyear=new String(SimpleBase64Encoder.encode(schoolnameEncryptedBytes));
            byte[] monthandyearofpassingEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, monthandyearofpassingBytes);
            encmonthandyearofpassingpgyear=new String(SimpleBase64Encoder.encode(monthandyearofpassingEncryptedBytes));
            byte[] selectedcourseEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, selectedcourseBytes);
            encselectedcoursepgyear=new String(SimpleBase64Encoder.encode(selectedcourseEncryptedBytes));
            byte[] selectedstreamEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, selectedstreamBytes);
            encselectedstreampgyear=new String(SimpleBase64Encoder.encode(selectedstreamEncryptedBytes));
            byte[] selecteduniversityEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, selecteduniversityBytes);
            encselecteduniversitypgyear=new String(SimpleBase64Encoder.encode(selecteduniversityEncryptedBytes));

        }catch (Exception e){
            Log.d("TAG", "setBlankYear: "+e.getMessage());
        }

    }


    @Override
    public void onBackPressed() {

        //if(edittedFlag==1||!oldCoursesem.equals(selectedCoursepgsem)||!oldStreamsem.equals(selectedStreampgsem)||!oldUniversitysem.equals(selectedUniversitypgsem)||!oldCourseyear.equals(selectedCoursepgyear)||!oldStreamyear.equals(selectedStreampgyear)||!oldUniversityyear.equals(selectedUniversitypgyear)) {
        if(edittedFlag==1)
        {
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
                }
            });

            alertDialog.show();
        }else
            MyProfilePg.super.onBackPressed();
    }
}
