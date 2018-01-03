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
import android.util.DisplayMetrics;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import placeme.octopusites.com.placeme.modal.MyProfileDiplomaModal;
import placeme.octopusites.com.placeme.modal.MyProfileTwelthModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfileTwelthOrDiploma extends AppCompatActivity {

    EditText marks12, outof12, percent12, schoolname12, otherboard, otherstream12;
    EditText dmarkssem1, doutofsem1, dpercentsem1, dmarkssem2, doutofsem2, dpercentsem2, dmarkssem3, doutofsem3, dpercentsem3, dmarkssem4, doutofsem4, dpercentsem4, dmarkssem5, doutofsem5, dpercentsem5, dmarkssem6, doutofsem6, dpercentsem6, daggregate, schoolnamed, othercourse, otherboardd;
    TextInputLayout marks12input, outof12input, schoolname12input, otherstream12input, otherboardinput, yearofpassing12inpute, marksdsem1input, doutofsem1input, dpercentsem1input, marksdsem2input, doutofsem2input, dpercentsem2inpute, marksdsem3input, doutofsem3input, dpercentsem3input, marksdsem4input, doutofsem4inpute, dpercentsem4inpute, marksdsem5input, doutofsem5input, dpercentsem5input, marksdsem6input, doutofsem6input, dpercentsem6input, daggregateinput, othercourseinput, otherboarddinput, schoolnamedinput, yearofpassingdinput;
    TextInputLayout percent12input;
    String selectedBoard12 = "", selectedBoarddiploma = "", selectedStream12 = "", monthandyearofpassing12 = "", monthandyearofpassingdiploma = "";
    Spinner stream12, board12;
    Spinner dcourse, duniversity;
    String username, role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;

    int edittedFlag = 0;
    String marksobtained = "", outofmarks = "", percentage = "", schoolnametwelth = "", schoolnamediploma = "", otherspecifiedboard = "", otherspecifiedstream = "", otherspecifiedcourse = "";
    String markssem1 = "", outofsem1 = "", percentsem1 = "", markssem2 = "", outofsem2 = "", percentsem2 = "", markssem3 = "", outofsem3 = "", percentsem3 = "", markssem4 = "", outofsem4 = "", percentsem4 = "", markssem5 = "", outofsem5 = "", percentsem5 = "", markssem6 = "", outofsem6 = "", percentsem6 = "", aggregate = "";
    RelativeLayout layouttoshow, layouttohide;
    RadioGroup radioGrouptwelthdiploma;
    RadioButton radioButtonTwelth, radioButtonDiploma;
    String pattern = "twelth";
    EditText yearofpassing12, yearofpassingd;

    int coursecount = 0;
    String[] courses;
    String selectedCourse = "";
    StudentData s = new StudentData();
    String oldStream12 = "", oldBoard12 = "", oldBoarddiploma = "", oldCourse = "";

    String selectedboardBytes1 = null;
    String selectedstreamBytes1 = null;
    String selectedcourseBytes1 = null;
    String selecteddiplomaboardBytes1 =null;

    String strobj, strobj2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_twelth_or_diploma);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Educational Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        username = MySharedPreferencesManager.getUsername(this);
        role = MySharedPreferencesManager.getRole(this);

        dmarkssem1 = (EditText) findViewById(R.id.dmarkssem1);
        doutofsem1 = (EditText) findViewById(R.id.doutofsem1);
        dpercentsem1 = (EditText) findViewById(R.id.dpercentsem1);
        dmarkssem2 = (EditText) findViewById(R.id.dmarkssem2);
        doutofsem2 = (EditText) findViewById(R.id.doutofsem2);
        dpercentsem2 = (EditText) findViewById(R.id.dpercentsem2);
        dmarkssem3 = (EditText) findViewById(R.id.dmarkssem3);
        doutofsem3 = (EditText) findViewById(R.id.doutofsem3);
        dpercentsem3 = (EditText) findViewById(R.id.dpercentsem3);
        dmarkssem4 = (EditText) findViewById(R.id.dmarkssem4);
        doutofsem4 = (EditText) findViewById(R.id.doutofsem4);
        dpercentsem4 = (EditText) findViewById(R.id.dpercentsem4);
        dmarkssem5 = (EditText) findViewById(R.id.dmarkssem5);
        doutofsem5 = (EditText) findViewById(R.id.doutofsem5);
        dpercentsem5 = (EditText) findViewById(R.id.dpercentsem5);
        dmarkssem6 = (EditText) findViewById(R.id.dmarkssem6);
        doutofsem6 = (EditText) findViewById(R.id.doutofsem6);
        dpercentsem6 = (EditText) findViewById(R.id.dpercentsem6);
        daggregate = (EditText) findViewById(R.id.daggregate);
        schoolnamed = (EditText) findViewById(R.id.schoolnamed);
        dcourse = (Spinner) findViewById(R.id.dcourse);
        duniversity = (Spinner) findViewById(R.id.duniversity);
        othercourse = (EditText) findViewById(R.id.othercourse);
        otherboardd = (EditText) findViewById(R.id.otherboardd);
        yearofpassingd = (EditText) findViewById(R.id.yearofpassingd);

        marks12 = (EditText) findViewById(R.id.marks12);
        outof12 = (EditText) findViewById(R.id.outof12);
        percent12 = (EditText) findViewById(R.id.percent12);
        schoolname12 = (EditText) findViewById(R.id.schoolname12);
        yearofpassing12 = (EditText) findViewById(R.id.yearofpassing12);
        otherboard = (EditText) findViewById(R.id.otherboard);
        otherstream12 = (EditText) findViewById(R.id.otherstream12);
        stream12 = (Spinner) findViewById(R.id.stream12);
        board12 = (Spinner) findViewById(R.id.board12);

        marks12input = (TextInputLayout) findViewById(R.id.marks12input);
        outof12input = (TextInputLayout) findViewById(R.id.outof12input);
        schoolname12input = (TextInputLayout) findViewById(R.id.schoolname12input);
        otherstream12input = (TextInputLayout) findViewById(R.id.otherstream12input);
        otherboardinput = (TextInputLayout) findViewById(R.id.otherboardinput);
        yearofpassing12inpute = (TextInputLayout) findViewById(R.id.yearofpassing12inpute);
        marksdsem1input = (TextInputLayout) findViewById(R.id.marksdsem1input);
        doutofsem1input = (TextInputLayout) findViewById(R.id.doutofsem1input);
        dpercentsem1input = (TextInputLayout) findViewById(R.id.dpercentsem1input);
        marksdsem2input = (TextInputLayout) findViewById(R.id.marksdsem2input);
        doutofsem2input = (TextInputLayout) findViewById(R.id.doutofsem2input);
        dpercentsem2inpute = (TextInputLayout) findViewById(R.id.dpercentsem2inpute);
        marksdsem3input = (TextInputLayout) findViewById(R.id.marksdsem3input);
        doutofsem3input = (TextInputLayout) findViewById(R.id.doutofsem3input);
        dpercentsem3input = (TextInputLayout) findViewById(R.id.dpercentsem3input);
        marksdsem4input = (TextInputLayout) findViewById(R.id.marksdsem4input);
        doutofsem4inpute = (TextInputLayout) findViewById(R.id.doutofsem4inpute);
        dpercentsem4inpute = (TextInputLayout) findViewById(R.id.dpercentsem4inpute);
        marksdsem5input = (TextInputLayout) findViewById(R.id.marksdsem5input);
        doutofsem5input = (TextInputLayout) findViewById(R.id.doutofsem5input);
        dpercentsem5input = (TextInputLayout) findViewById(R.id.dpercentsem5input);
        marksdsem6input = (TextInputLayout) findViewById(R.id.marksdsem6input);
        doutofsem6input = (TextInputLayout) findViewById(R.id.doutofsem6input);
        dpercentsem6input = (TextInputLayout) findViewById(R.id.dpercentsem6input);
        daggregateinput = (TextInputLayout) findViewById(R.id.daggregateinput);
        othercourseinput = (TextInputLayout) findViewById(R.id.othercourseinput);
        otherboarddinput = (TextInputLayout) findViewById(R.id.otherboarddinput);
        schoolnamedinput = (TextInputLayout) findViewById(R.id.schoolnamedinput);
        yearofpassingdinput = (TextInputLayout) findViewById(R.id.yearofpassingdinput);
        percent12input = (TextInputLayout) findViewById(R.id.percent12input);

        marks12.setTypeface(Z.getBold(this));
        outof12.setTypeface(Z.getBold(this));
        percent12.setTypeface(Z.getBold(this));
        schoolname12.setTypeface(Z.getBold(this));
        otherboard.setTypeface(Z.getBold(this));
        otherstream12.setTypeface(Z.getBold(this));
        dmarkssem1.setTypeface(Z.getBold(this));
        doutofsem1.setTypeface(Z.getBold(this));
        dpercentsem1.setTypeface(Z.getBold(this));
        dmarkssem2.setTypeface(Z.getBold(this));
        doutofsem2.setTypeface(Z.getBold(this));
        dpercentsem2.setTypeface(Z.getBold(this));
        dmarkssem3.setTypeface(Z.getBold(this));
        doutofsem3.setTypeface(Z.getBold(this));
        dpercentsem3.setTypeface(Z.getBold(this));
        dmarkssem4.setTypeface(Z.getBold(this));
        doutofsem4.setTypeface(Z.getBold(this));
        dpercentsem4.setTypeface(Z.getBold(this));
        dmarkssem5.setTypeface(Z.getBold(this));
        doutofsem5.setTypeface(Z.getBold(this));
        dpercentsem5.setTypeface(Z.getBold(this));
        dmarkssem6.setTypeface(Z.getBold(this));
        doutofsem6.setTypeface(Z.getBold(this));
        dpercentsem6.setTypeface(Z.getBold(this));
        daggregate.setTypeface(Z.getBold(this));
        schoolnamed.setTypeface(Z.getBold(this));
        othercourse.setTypeface(Z.getBold(this));
        otherboardd.setTypeface(Z.getBold(this));
        yearofpassing12.setTypeface(Z.getBold(this));
        yearofpassingd.setTypeface(Z.getBold(this));

        marks12input.setTypeface(Z.getLight(this));
        outof12input.setTypeface(Z.getLight(this));
        schoolname12input.setTypeface(Z.getLight(this));
        otherstream12input.setTypeface(Z.getLight(this));
        otherboardinput.setTypeface(Z.getLight(this));
        yearofpassing12inpute.setTypeface(Z.getLight(this));
        marksdsem1input.setTypeface(Z.getLight(this));
        doutofsem1input.setTypeface(Z.getLight(this));
        dpercentsem1input.setTypeface(Z.getLight(this));
        marksdsem2input.setTypeface(Z.getLight(this));
        doutofsem2input.setTypeface(Z.getLight(this));
        dpercentsem2inpute.setTypeface(Z.getLight(this));
        marksdsem3input.setTypeface(Z.getLight(this));
        doutofsem3input.setTypeface(Z.getLight(this));
        dpercentsem3input.setTypeface(Z.getLight(this));
        marksdsem4input.setTypeface(Z.getLight(this));
        doutofsem4inpute.setTypeface(Z.getLight(this));
        dpercentsem4inpute.setTypeface(Z.getLight(this));
        marksdsem5input.setTypeface(Z.getLight(this));
        doutofsem5input.setTypeface(Z.getLight(this));
        dpercentsem5input.setTypeface(Z.getLight(this));
        marksdsem6input.setTypeface(Z.getLight(this));
        doutofsem6input.setTypeface(Z.getLight(this));
        dpercentsem6input.setTypeface(Z.getLight(this));
        daggregateinput.setTypeface(Z.getLight(this));
        othercourseinput.setTypeface(Z.getLight(this));
        otherboarddinput.setTypeface(Z.getLight(this));
        schoolnamedinput.setTypeface(Z.getLight(this));
        yearofpassingdinput.setTypeface(Z.getLight(this));
        percent12input.setTypeface(Z.getLight(this));


        coursecount = getResources().getStringArray(R.array.dcourses).length;
        courses = new String[coursecount];
        courses = getResources().getStringArray(R.array.dcourses);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.spinner_item, courses) {
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
            tv.setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));
            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView tv = (TextView) view;
            tv.setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));

            if (position == 0) {
                // Set the hint text color gray
                tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
            } else {
                tv.setTextColor(getResources().getColor(R.color.dark_color));
            }
            return view;
        }
    };

        dcourse.setAdapter(adapter4);

        dmarkssem1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                marksdsem1input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                doutofsem1input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem1.getText().toString();
                    String s2 = doutofsem1.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem1.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem1input.setError("Kindly enter valid Out Of Marks");
                            dpercentsem1.setText("");
                        }
                    } else {
                        dpercentsem1.setText("");
                    }
                } catch (Exception e) {
                }
            }
        });

        doutofsem1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                doutofsem1input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
                doutofsem1input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem1.getText().toString();
                    String s2 = doutofsem1.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem1.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem1input.setError("Kindly enter valid Out Of Marks");
                            dpercentsem1.setText("");
                        }
                    } else {
                        dpercentsem1.setText("");
                    }
                } catch (Exception e) {
                }
            }


        });
        dmarkssem2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                marksdsem2input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                doutofsem2input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem2.getText().toString();
                    String s2 = doutofsem2.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem2.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem2input.setError("Kindly enter valid Out Of Marks");
                            dpercentsem2.setText("");
                        }
                    } else {
                        dpercentsem2.setText("");
                    }
                } catch (Exception e) {
                }


            }
        });
        doutofsem2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                doutofsem2input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//
                doutofsem2input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem2.getText().toString();
                    String s2 = doutofsem2.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem2.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem2input.setError("Kindly enter valid Out Of Marks");
                            dpercentsem2.setText("");
                        }
                    } else {
                        dpercentsem2.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        dmarkssem3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                marksdsem3input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {


                doutofsem3input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem3.getText().toString();
                    String s2 = doutofsem3.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem3.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem3input.setError("Kindly enter valid Out Of Marks");
                            dpercentsem3.setText("");
                        }
                    } else {
                        dpercentsem3.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        doutofsem3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                doutofsem3input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//
                doutofsem3input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem3.getText().toString();
                    String s2 = doutofsem3.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem3.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem3input.setError("Kindly enter valid Out Of Marks");
                            dpercentsem3.setText("");
                        }
                    } else {
                        dpercentsem3.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        dmarkssem4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                marksdsem4input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                doutofsem4inpute.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem4.getText().toString();
                    String s2 = outofsem4;


                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem4.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem4inpute.setError("Kindly enter valid Out Of Marks");
                            dpercentsem4.setText("");
                        }
                    } else {
                        dpercentsem4.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        doutofsem4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                doutofsem4inpute.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//

                doutofsem4inpute.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem4.getText().toString();
                    String s2 = doutofsem4.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem4.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem4inpute.setError("Kindly enter valid out of marks");
                            dpercentsem4.setText("");
                        }
                    } else {
                        dpercentsem4.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        dmarkssem5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                marksdsem5input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                doutofsem5input.setError(null);
                try {
                    double percentage = 0;
                    String s1 = dmarkssem5.getText().toString();
                    String s2 = doutofsem5.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem5.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem5input.setError("Kindly enter valid Out Of Marks");
                            dpercentsem5.setText("");
                        }
                    } else {
                        dpercentsem5.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        doutofsem5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                doutofsem5input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//
                doutofsem5input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem5.getText().toString();
                    String s2 = doutofsem5.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem5.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem5input.setError("Kindly enter valid Out Of Marks");
                            dpercentsem5.setText("");
                        }
                    } else {
                        dpercentsem5.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        dmarkssem6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                marksdsem6input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {


                doutofsem6input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem6.getText().toString();
                    String s2 = doutofsem6.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem6.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem6input.setError("Kindly enter valid Out Of Marks");
                            dpercentsem6.setText("");
                        }
                    } else {
                        dpercentsem6.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        doutofsem6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                doutofsem6input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//
                doutofsem6input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = dmarkssem6.getText().toString();
                    String s2 = doutofsem6.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {
                                dpercentsem6.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            doutofsem6input.setError("Kindly enter valid Out Of Marks");
                            dpercentsem6.setText("");
                        }
                    } else {
                        dpercentsem6.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        daggregate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                daggregateinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        marks12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                marks12input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {


                outof12input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = marks12.getText().toString();
                    String s2 = outof12.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                percent12.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            outof12input.setError("Kindly enter valid Out Of Marks");
                            percent12.setText("");
                        }
                    } else {
                        percent12.setText("");
                    }
                } catch (Exception e) {
                }


            }
        });
        outof12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                outof12input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

//
                percent12input.setError(null);

                try {
                    double percentage = 0;
                    String s1 = marks12.getText().toString();
                    String s2 = outof12.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100)
                                percent12.setText("" + (new DecimalFormat("##.##").format(percentage)));

                        } else {
                            outof12input.setError("Kindly enter valid Out Of Marks");
                            percent12.setText("");
                        }
                    } else {
                        percent12.setText("");
                    }
                } catch (Exception e) {
                }


            }
        });

        otherboardd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                otherboarddinput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otherboard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                otherboardinput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otherstream12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                otherstream12input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        schoolname12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                schoolname12input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        schoolnamed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                schoolnamedinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofpassing12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearofpassing12inpute.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        othercourse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                othercourseinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        String[] dboards = new String[]{
                "- Select Board -", "State Board", "Other"
        };
        final List<String> dboardslist = new ArrayList<>(Arrays.asList(dboards));

        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item, dboardslist) {
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
                tv.setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.dark_color));
                }
                return view;
            }
        };

        duniversity.setAdapter(dataAdapter3);

        duniversity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBoarddiploma = (String) parent.getItemAtPosition(position);
                TextInputLayout otherboardinput = (TextInputLayout) findViewById(R.id.otherboarddinput);
                if (selectedBoarddiploma.equals("Other")) {

                    otherboardinput.setVisibility(View.VISIBLE);
                    otherboardd.setVisibility(View.VISIBLE);

                } else {

                    otherboardinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] boards = new String[]{
                "- Select Board -", "State Board", "CBSE", "ICSE", "National Open School", "Other"
        };
        final List<String> boardslist = new ArrayList<>(Arrays.asList(boards));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, boardslist) {
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
                tv.setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.dark_color));
                }
                return view;
            }
        };
        ;
        board12.setAdapter(dataAdapter);

        board12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBoard12 = (String) parent.getItemAtPosition(position);
                TextInputLayout otherboardinput = (TextInputLayout) findViewById(R.id.otherboardinput);
                if (selectedBoard12.equals("Other")) {
                    otherboard.setVisibility(View.VISIBLE);
                    otherboardinput.setVisibility(View.VISIBLE);
                } else {
                    otherboard.setVisibility(View.GONE);
                    otherboardinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] streams = new String[]{
                "- Select Stream -", "Science", "Commerce", "Arts", "Other"
        };

        final List<String> streamslist = new ArrayList<>(Arrays.asList(streams));

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, streamslist) {
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
                tv.setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.dark_color));
                }
                return view;
            }
        };
        ;
        stream12.setAdapter(dataAdapter2);

        stream12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStream12 = (String) parent.getItemAtPosition(position);
                TextInputLayout otherstreaminput = (TextInputLayout) findViewById(R.id.otherstream12input);
                if (selectedStream12.equals("Other")) {

                    otherstreaminput.setVisibility(View.VISIBLE);
                } else {

                    otherstreaminput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dcourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (String) parent.getItemAtPosition(position);
                if (selectedCourse.equals("Other")) {
                    othercourseinput.setVisibility(View.VISIBLE);
                } else {

                    othercourseinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearofpassing12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                yearofpassing12inpute.setError(null);

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfileTwelthOrDiploma.this);
                LayoutInflater inflater = MyProfileTwelthOrDiploma.this.getLayoutInflater();
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
                for (int i = 1975; i <= cur.get(Calendar.YEAR); i++)
                    yearList.add("" + i);


                monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfileTwelthOrDiploma.this));
                monthView.setWheelData(monthList);
                yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfileTwelthOrDiploma.this));
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

                        setMonthYear("twelth", selectedMonth, selectedYear);

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

                DisplayMetrics displaymetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);


                int w = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());
                int h = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 215, getResources().getDisplayMetrics());
                alertDialog.getWindow().setLayout(w, h);
            }
        });
        yearofpassingd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                yearofpassingdinput.setError(null);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfileTwelthOrDiploma.this);
                LayoutInflater inflater = MyProfileTwelthOrDiploma.this.getLayoutInflater();
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
                for (int i = 1975; i <= cur.get(Calendar.YEAR); i++)
                    yearList.add("" + i);


                monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfileTwelthOrDiploma.this));
                monthView.setWheelData(monthList);
                yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfileTwelthOrDiploma.this));
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

                        setMonthYear("diploma", selectedMonth, selectedYear);

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


        radioGrouptwelthdiploma = (RadioGroup) findViewById(R.id.radioGrouptwelthdiploma);
        radioButtonTwelth = (RadioButton) findViewById(R.id.radioButtonTwelth);
        radioButtonDiploma = (RadioButton) findViewById(R.id.radioButtonDiploma);

        radioButtonTwelth.setTypeface(Z.getBold(this));
        radioButtonDiploma.setTypeface(Z.getBold(this));

        TextView twelthtxt = (TextView) findViewById(R.id.twelthtxt);
        twelthtxt.setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));

        radioGrouptwelthdiploma.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButtonTwelth:

                        layouttoshow = (RelativeLayout) findViewById(R.id.twelthform);
                        layouttohide = (RelativeLayout) findViewById(R.id.diplomaform);
                        layouttoshow.setVisibility(View.VISIBLE);
                        layouttohide.setVisibility(View.GONE);
                        pattern = "twelth";

                        break;
                    case R.id.radioButtonDiploma:
                        layouttoshow = (RelativeLayout) findViewById(R.id.diplomaform);
                        layouttohide = (RelativeLayout) findViewById(R.id.twelthform);
                        layouttoshow.setVisibility(View.VISIBLE);
                        layouttohide.setVisibility(View.GONE);
                        pattern = "diploma";
                        break;

                }
            }
        });
        ScrollView myprofileintroscrollview = (ScrollView) findViewById(R.id.myprofiletwelthordiplomascrollview);
        disableScrollbars(myprofileintroscrollview);

        marksobtained = s.getMarks12();
        outofmarks = s.getOutof12();
        percentage = s.getPercentage12();
        schoolnametwelth = s.getSchoolname12();
        selectedStream12 = s.getStream12();
        oldStream12 = selectedStream12;
        selectedBoard12 = s.getBoard12();
        oldBoard12 = selectedBoard12;
        monthandyearofpassing12 = s.getYearofpassing12();


        if (marksobtained != null && !marksobtained.equals("")) {
            marks12.setText(marksobtained);
            pattern = "twelth";
            radioButtonTwelth.setChecked(true);
        }
        if (outofmarks != null)
            outof12.setText(outofmarks);

        if (percentage != null)
            percent12.setText(percentage);

        if (schoolnametwelth != null)
            schoolname12.setText(schoolnametwelth);

        if (monthandyearofpassing12 != null)
            yearofpassing12.setText(monthandyearofpassing12);




        if (selectedBoard12 != null) {
            if(!selectedBoard12.equals("")) {

                int foundboard = 0;
                for (int i = 1; i < boards.length - 1; i++)
                    if (selectedBoard12.equals(boards[i])) {
                        foundboard = 1;
                        break;
                    }
                if (foundboard == 1)
                    board12.setSelection(dataAdapter.getPosition(selectedBoard12));
                else {

                    board12.setSelection(dataAdapter.getPosition("Other"));
                    otherboard.setVisibility(View.VISIBLE);
                    otherboard.setText(selectedBoard12);
                }
            }
            else
            {
                selectedBoard12 = "-Select Board - ";
            }
        } else
            selectedBoard12 = "-Select Board - ";


        if (selectedStream12 != null) {
            if (!selectedStream12.equals("")) {

                int foundstream = 0;
                for (int i = 1; i < streams.length - 1; i++)
                    if (selectedStream12.equals(streams[i])) {
                        foundstream = 1;
                        break;
                    }
                if (foundstream == 1)
                    stream12.setSelection(dataAdapter2.getPosition(selectedStream12));
                else {
                    stream12.setSelection(dataAdapter2.getPosition("Other"));
                    otherstream12.setVisibility(View.VISIBLE);
                    otherstream12.setText(selectedStream12);
                }
            }
            else {
                selectedBoard12 = "- Select Stream -";
            }

        } else
            selectedStream12 = "- Select Stream -";

        if (s.getMarkssem1diploma() != null)
            markssem1 = s.getMarkssem1diploma();
        if (s.getOutofsem1diploma() != null)
            outofsem1 = s.getOutofsem1diploma();
        if (s.getPercentagesem1diploma() != null)
            percentsem1 = s.getPercentagesem1diploma();
        if (s.getMarkssem2diploma() != null)
            markssem2 = s.getMarkssem2diploma();
        if (s.getOutofsem2diploma() != null)
            outofsem2 = s.getOutofsem2diploma();
        if (s.getPercentagesem2diploma() != null)
            percentsem2 = s.getPercentagesem2diploma();
        if (s.getMarkssem3diploma() != null)
            markssem3 = s.getMarkssem3diploma();
        if (s.getOutofsem3diploma() != null)
            outofsem3 = s.getOutofsem3diploma();
        if (s.getPercentagesem3diploma() != null)
            percentsem3 = s.getPercentagesem3diploma();
        if (s.getMarkssem4diploma() != null)
            markssem4 = s.getMarkssem4diploma();
        if (s.getOutofsem4diploma() != null)
            outofsem4 = s.getOutofsem4diploma();
        if (s.getPercentagesem4diploma() != null)
            percentsem4 = s.getPercentagesem4diploma();
        if (s.getMarkssem5diploma() != null)
            markssem5 = s.getMarkssem5diploma();
        if (s.getOutofsem5diploma() != null)
            outofsem5 = s.getOutofsem5diploma();
        if (s.getPercentagesem5diploma() != null)
            percentsem5 = s.getPercentagesem5diploma();
        if (s.getMarkssem6diploma() != null)
            markssem6 = s.getMarkssem6diploma();
        if (s.getOutofsem6diploma() != null)
            outofsem6 = s.getOutofsem6diploma();
        if (s.getPercentagesem6diploma() != null)
            percentsem6 = s.getPercentagesem6diploma();
        if (s.getAggregatediploma() != null)
            aggregate = s.getAggregatediploma();

        if (s.getUniversitydiploma() != null)
            selectedBoarddiploma = s.getUniversitydiploma();


        if (s.getCollegenamediploma() != null)
            schoolnamediploma = s.getCollegenamediploma();
        if (s.getYearofpassingdiploma() != null)
            monthandyearofpassingdiploma = s.getYearofpassingdiploma();

        if (s.getCoursediploma() != null)
            selectedCourse = s.getCoursediploma();

        oldBoarddiploma = selectedBoarddiploma;
        oldCourse = selectedCourse;

        if (markssem1 != null && !markssem1.equals("")) {
            dmarkssem1.setText(markssem1);
            pattern = "diploma";
            radioButtonDiploma.setChecked(true);
        }

        if (outofsem1 != null)
            doutofsem1.setText(outofsem1);
        if (percentsem1 != null)
            dpercentsem1.setText(percentsem1);
        if (markssem2 != null)
            dmarkssem2.setText(markssem2);
        if (outofsem2 != null)
            doutofsem2.setText(outofsem2);
        if (percentsem2 != null)
            dpercentsem2.setText(percentsem2);
        if (markssem3 != null)
            dmarkssem3.setText(markssem3);
        if (outofsem3 != null)
            doutofsem3.setText(outofsem3);
        if (percentsem3 != null)
            dpercentsem3.setText(percentsem3);
        if (markssem4 != null)
            dmarkssem4.setText(markssem4);
        if (outofsem4 != null) {
            doutofsem4.setText(outofsem4);
        }
        if (percentsem4 != null) {
            dpercentsem4.setText(percentsem4);

        }


        if (markssem5 != null)
            dmarkssem5.setText(markssem5);

        if (outofsem5 != null)
            doutofsem5.setText(outofsem5);


        if (percentsem5 != null)
            dpercentsem5.setText(percentsem5);

        if (markssem6 != null)
            dmarkssem6.setText(markssem6);

        if (outofsem6 != null)
            doutofsem6.setText(outofsem6);


        if (percentsem6 != null)
            dpercentsem6.setText(percentsem6);

        if (aggregate != null)
            daggregate.setText(aggregate);

        if (schoolnamediploma != null)
            schoolnamed.setText(schoolnamediploma);

        if (monthandyearofpassingdiploma != null)
            yearofpassingd.setText(monthandyearofpassingdiploma);




        if (selectedBoarddiploma != null) {
            int foundboard = 0;
            for (int i = 1; i < dboards.length - 1; i++)
                if (selectedBoarddiploma.equals(dboards[i])) {
                    foundboard = 1;
                    break;
                }
            if (foundboard == 1)
                duniversity.setSelection(dataAdapter3.getPosition(selectedBoarddiploma));
            else {

                if (selectedBoarddiploma.equals("")) {
                    duniversity.setSelection(dataAdapter3.getPosition("- Select Board -"));
                    otherboardd.setVisibility(View.GONE);
                } else {
                    duniversity.setSelection(dataAdapter3.getPosition("Other"));
                    otherboardd.setVisibility(View.VISIBLE);
                    otherboardd.setText(selectedBoarddiploma);
                }
            }

        } else
            selectedBoarddiploma = "- Select Board - ";


//***************

        if (selectedCourse != null) {

            if(!selectedCourse.equals("")) {
                int foundcourse = 0;
                for (int i = 1; i < courses.length - 1; i++)
                    if (selectedCourse.equals(courses[i])) {
                        foundcourse = 1;
                        break;
                    }


                if (foundcourse == 1)
                    dcourse.setSelection(adapter4.getPosition(selectedCourse));
                else {
                    if (selectedCourse.equals("")) {
                        dcourse.setSelection(adapter4.getPosition("- Select Course -"));
                        othercourse.setVisibility(View.GONE);
                    } else {

                        dcourse.setSelection(adapter4.getPosition("Other"));
                        othercourse.setVisibility(View.VISIBLE);
                        othercourseinput.setVisibility(View.VISIBLE);
                        othercourse.setText(selectedCourse);
                    }
                }
            }

        } else
            selectedBoarddiploma = "- Select Course - ";


        edittedFlag = 0;

        Log.d("cricket", "MPTOD OC odi match");

    }


    void setMonthYear(String tod, String selectedMonth, String selectedYear) {
        if (tod.equals("twelth"))
            yearofpassing12.setText(selectedMonth + ", " + selectedYear);
        else
            yearofpassingd.setText(selectedMonth + ", " + selectedYear);

    }

    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }

    void validateandSave() {

        if (pattern.equals("twelth")) {
            setBlankDelpoma();

            int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0, errorflag6 = 0, errorflag7 = 0, errorflag8 = 0, errorflag9 = 0;

            marksobtained = marks12.getText().toString();
            outofmarks = outof12.getText().toString();
            percentage = percent12.getText().toString();
            schoolnametwelth = schoolname12.getText().toString();
            monthandyearofpassing12 = yearofpassing12.getText().toString();

            if (marksobtained.length() < 1) {
                errorflag1 = 1;
                marks12input.setError("Kindly enter valid marks");
            } else {
                if (outofmarks.length() < 1) {
                    errorflag2 = 1;

                    outof12input.setError("Kindly enter valid marks");
                } else {
                    if (percentage.length() < 1) {
                        errorflag3 = 1;
                        percent12input.setError("Incorrect Percentage");
                    } else {
                        if (schoolnametwelth.length() < 3) {
                            errorflag4 = 1;
                            schoolname12input.setError("Kindly enter valid college name");
                        } else {
                            if (selectedStream12 == null || selectedStream12.equals("- Select Stream -")) {
                                errorflag5 = 1;
                                Toast.makeText(MyProfileTwelthOrDiploma.this, "Select Stream", Toast.LENGTH_LONG).show();
                            } else {
                                if (selectedStream12.equals("Other")) {
                                    otherspecifiedstream = otherstream12.getText().toString();
                                    if (otherspecifiedstream.length() < 3) {
                                        errorflag6 = 1;
                                        otherstream12input.setError("Kindly enter valid Stream");
                                    }
                                }

                                if (selectedBoard12 == null || selectedBoard12.equals("- Select Board -")) {
                                    errorflag7 = 1;
                                    marks12.setError(null);
                                    outof12.setError(null);
                                    percent12.setError(null);
                                    schoolname12.setError(null);
                                    Toast.makeText(MyProfileTwelthOrDiploma.this, "Select Board", Toast.LENGTH_LONG).show();
                                } else {
                                    if (selectedBoard12.equals("Other")) {
                                        otherspecifiedboard = otherboard.getText().toString();
                                        if (otherspecifiedboard.length() < 3) {
                                            errorflag8 = 1;
                                            otherboardinput.setError("Kindly enter valid Board");
                                        }
                                    }
                                    if (monthandyearofpassing12.length() < 9 || monthandyearofpassing12.length() > 9) {
                                        errorflag9 = 1;
                                        yearofpassing12inpute.setError("Kindly select valid Month,Year");
                                    }
                                }

                            }
                        }
                    }
                }
            }

            if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 && errorflag5 == 0 && errorflag6 == 0 && errorflag7 == 0 && errorflag8 == 0 && errorflag9 == 0) {
                try {
                    Log.d("cricket", "MPTOD V&ST odi match");

                    if (selectedStream12.equals("Other"))
                        selectedstreamBytes1 = otherspecifiedstream;
                    else
                        selectedstreamBytes1 = selectedStream12;

                    if (selectedBoard12.equals("Other"))
                        selectedboardBytes1 = otherspecifiedboard;
                    else
                        selectedboardBytes1 = selectedBoard12;

                    new SaveDataTwelth().execute();

                    new SaveDataDiploma().execute();

                } catch (Exception e) {
                    Log.d("cricket", "MPTOD V&ST odi match lost - " + e.getMessage());
                }
            }


        } else if (pattern.equals("diploma")) {

            setBlankTwelth();


            markssem1 = dmarkssem1.getText().toString();
            outofsem1 = doutofsem1.getText().toString();
            percentsem1 = dpercentsem1.getText().toString();
            markssem2 = dmarkssem2.getText().toString();
            outofsem2 = doutofsem2.getText().toString();
            percentsem2 = dpercentsem2.getText().toString();
            markssem3 = dmarkssem3.getText().toString();
            outofsem3 = doutofsem3.getText().toString();
            percentsem3 = dpercentsem3.getText().toString();
            markssem4 = dmarkssem4.getText().toString();
            outofsem4 = doutofsem4.getText().toString();
            percentsem4 = dpercentsem4.getText().toString();
            markssem5 = dmarkssem5.getText().toString();
            outofsem5 = doutofsem5.getText().toString();
            percentsem5 = dpercentsem5.getText().toString();
            markssem6 = dmarkssem6.getText().toString();
            outofsem6 = doutofsem6.getText().toString();
            percentsem6 = dpercentsem6.getText().toString();
            aggregate = daggregate.getText().toString();
            schoolnamediploma = schoolnamed.getText().toString();
            monthandyearofpassingdiploma = yearofpassingd.getText().toString();

            int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0;

            if (markssem1.length() < 1) {
                errorflag1 = 1;
                marksdsem1input.setError("Kindly enter valid marks");
            } else {
                errorflag1 = 0;
                if (outofsem1.length() < 1) {
                    errorflag1 = 1;
                    doutofsem1input.setError("Kindly enter valid marks");
                } else {
                    errorflag1 = 0;
                    if (percentsem1.length() < 1) {
                        errorflag1 = 1;
                        doutofsem1input.setError("Incorrect Percentage");
                    } else {
                        errorflag1 = 0;
                        if (markssem2.length() < 1) {
                            errorflag1 = 1;
                            marksdsem2input.setError("Kindly enter valid marks");
                        } else {
                            errorflag1 = 0;
                            if (outofsem2.length() < 1) {
                                errorflag1 = 1;
                                doutofsem2input.setError("Kindly enter valid marks");
                            } else {
                                errorflag1 = 0;
                                if (percentsem2.length() < 1) {
                                    errorflag1 = 1;
                                    doutofsem2input.setError("Incorrect Percentage");
                                } else {
                                    errorflag1 = 0;
                                    if (markssem3.length() < 1) {
                                        errorflag1 = 1;
                                        marksdsem3input.setError("Kindly enter valid marks");
                                    } else {
                                        errorflag1 = 0;
                                        if (outofsem3.length() < 1) {
                                            errorflag1 = 1;
                                            doutofsem3input.setError("Kindly enter valid marks");
                                        } else {
                                            errorflag1 = 0;
                                            if (percentsem3.length() < 1) {
                                                errorflag1 = 1;
                                                doutofsem3input.setError("Incorrect Percentage");
                                            } else {
                                                errorflag1 = 0;
                                                if (markssem4.length() < 1) {
                                                    errorflag1 = 1;
                                                    marksdsem4input.setError("Kindly enter valid marks");
                                                } else {
                                                    errorflag1 = 0;
                                                    if (outofsem4.length() < 1) {
                                                        errorflag1 = 1;
                                                        doutofsem4inpute.setError("Kindly enter valid marks");
                                                    } else {
                                                        errorflag1 = 0;
                                                        if (percentsem4.length() < 1) {
                                                            errorflag1 = 1;
                                                            doutofsem4inpute.setError("Incorrect Percentage");
                                                        } else {
                                                            errorflag1 = 0;
                                                            if (markssem5.length() < 1) {
                                                                errorflag1 = 1;
                                                                marksdsem5input.setError("Kindly enter valid marks");
                                                            } else {
                                                                errorflag1 = 0;
                                                                if (outofsem5.length() < 1) {
                                                                    errorflag1 = 1;
                                                                    doutofsem5input.setError("Kindly enter valid marks");
                                                                } else {
                                                                    errorflag1 = 0;
                                                                    if (percentsem5.length() < 1) {
                                                                        errorflag1 = 1;
                                                                        doutofsem5input.setError("Incorrect Percentage");
                                                                    } else {
                                                                        errorflag1 = 0;
                                                                        if (markssem6.length() < 1) {
                                                                            errorflag1 = 1;
                                                                            marksdsem6input.setError("Kindly enter valid marks");
                                                                        } else {
                                                                            errorflag1 = 0;
                                                                            if (outofsem6.length() < 1) {
                                                                                errorflag1 = 1;
                                                                                doutofsem6input.setError("Kindly enter valid marks");
                                                                            } else {
                                                                                errorflag1 = 0;
                                                                                if (percentsem6.length() < 1) {
                                                                                    errorflag1 = 1;
                                                                                    doutofsem6input.setError("Incorrect Percentage");
                                                                                } else {
                                                                                    errorflag1 = 0;
                                                                                    float aggg = 0;
                                                                                    if (!aggregate.equals(""))
                                                                                        aggg = Float.parseFloat(aggregate);

                                                                                    if (aggg <= 0 || aggg >= 100) {
                                                                                        errorflag1 = 1;
                                                                                        daggregateinput.setError("Kindly enter valid Aggregate");
                                                                                    }
                                                                                    else {
                                                                                        errorflag1 = 0;
                                                                                        if (selectedCourse == null || selectedCourse.equals("- Select Course -")) {
                                                                                            errorflag1 = 1;
                                                                                            daggregateinput.setError(null);
                                                                                            Toast.makeText(MyProfileTwelthOrDiploma.this, "Select Course", Toast.LENGTH_LONG).show();
                                                                                        } else {
                                                                                            if (selectedCourse.equals("Other")) {
                                                                                                otherspecifiedcourse = othercourse.getText().toString();
                                                                                                if (otherspecifiedcourse.length() < 3) {
                                                                                                    errorflag2 = 1;
                                                                                                    othercourseinput.setError("Kindly enter valid Course");
                                                                                                }
                                                                                            }

                                                                                            if (selectedBoarddiploma.equals("- Select Board -")) {
                                                                                                errorflag3 = 1;
                                                                                                Toast.makeText(MyProfileTwelthOrDiploma.this, "Select Board", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                if (schoolnamediploma.length() < 3) {
                                                                                                    errorflag4 = 1;
                                                                                                    schoolnamedinput.setError("Kindly enter valid College Name");
                                                                                                } else if (selectedBoarddiploma.equals("Other")) {

                                                                                                    otherspecifiedboard = otherboardd.getText().toString();
                                                                                                    if (otherspecifiedboard.length() < 3) {
                                                                                                        errorflag4 = 1;

                                                                                                        otherboarddinput.setError("Kindly enter valid board");
                                                                                                    } else if (monthandyearofpassingdiploma.length() < 9 || monthandyearofpassingdiploma.length() > 9) {
                                                                                                        errorflag5 = 1;
                                                                                                        yearofpassingdinput.setError("Kindly select valid Month,Year");
                                                                                                    }
                                                                                                } else if (monthandyearofpassingdiploma.length() < 9 || monthandyearofpassingdiploma.length() > 9) {
                                                                                                    errorflag5 = 1;
                                                                                                    yearofpassingdinput.setError("Kindly select valid Month,Year");
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
                    Log.d("cricket", "MPTOD V&SD odi match");

                    if (selectedCourse.equals("Other"))
                        selectedcourseBytes1 = otherspecifiedcourse;
                    else
                        selectedcourseBytes1 = selectedCourse;

                    if (selectedBoarddiploma.equals("Other"))
                        selecteddiplomaboardBytes1 = otherspecifiedboard;
                    else
                        selecteddiplomaboardBytes1 = selectedBoarddiploma;

                    new SaveDataDiploma().execute();
                    new SaveDataTwelth().execute();

                } catch (Exception e) {

                    Log.d("cricket", "MPTOD V&SD odi match lost - " + e.getMessage());
                }
            }
        }
    }

    public void setBlankTwelth() {

        marksobtained = "";
        outofmarks = "";
        percentage = "";
        schoolnametwelth = "";
        selectedStream12 = "";
        oldStream12 = selectedStream12;
        selectedboardBytes1="";
        selectedBoard12 = "";
        oldBoard12 = "";
        monthandyearofpassing12 = "";

        marks12.setText("");
        outof12.setText("");
        percent12.setText("");
        schoolname12.setText("");
        yearofpassing12.setText("");

        board12.setSelection(0);
        stream12.setSelection(0);

        s.setMarks12("");
        s.setOutof12("");
        s.setPercentage12("");
        s.setSchoolname12("");
        s.setStream12("");
        s.setBoard12("");
        s.setYearofpassing12("");


    }

    public void setBlankDelpoma() {

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
        aggregate = "";
        schoolnamediploma = "";
        selectedBoarddiploma = "";
        selectedCourse = "";


        dmarkssem1.setText("");
        doutofsem1.setText("");
        dpercentsem1.setText("");
        dmarkssem2.setText("");
        doutofsem2.setText("");
        dpercentsem2.setText("");
        dmarkssem3.setText("");
        doutofsem3.setText("");
        dpercentsem3.setText("");
        dmarkssem4.setText("");
        doutofsem4.setText("");
        dpercentsem4.setText("");
        dmarkssem5.setText("");
        doutofsem5.setText("");
        dpercentsem5.setText("");
        dmarkssem6.setText("");
        doutofsem6.setText("");
        dpercentsem6.setText("");
        daggregate.setText("");
        schoolnamed.setText("");
        yearofpassingd.setText("");

        dcourse.setSelection(0);
        duniversity.setSelection(0);

        s.setMarkssem1diploma("");
        s.setOutofsem1diploma("");
        s.setPercentagesem1diploma("");
        s.setMarkssem2diploma("");
        s.setOutofsem2diploma("");
        s.setPercentagesem2diploma("");
        s.setMarkssem3diploma("");
        s.setOutofsem3diploma("");
        s.setPercentagesem3diploma("");
        s.setMarkssem4diploma("");
        s.setOutofsem4diploma("");
        s.setPercentagesem4diploma("");
        s.setMarkssem5diploma("");
        s.setOutofsem5diploma("");
        s.setPercentagesem5diploma("");
        s.setMarkssem6diploma("");
        s.setOutofsem6diploma("");
        s.setPercentagesem6diploma("");
        s.setAggregatediploma("");
        s.setUniversitydiploma("");
        s.setCollegenamediploma("");
        s.setYearofpassingdiploma("");
        s.setCoursediploma("");
        dcourse.setSelection(0);
        try {

            if (selectedCourse.equals("Other"))
                selectedcourseBytes1 = otherspecifiedcourse;
            else
                selectedcourseBytes1 = selectedCourse;

            if (selectedBoarddiploma.equals("Other"))
                selectedboardBytes1 = otherspecifiedboard;
            else
                selectedboardBytes1 = selectedBoarddiploma;


        } catch (Exception e) {
        }

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
                                    MyProfileTwelthOrDiploma.super.onBackPressed();
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

                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileTwelthOrDiploma.this));

                }
            });

            alertDialog.show();
        } else
            MyProfileTwelthOrDiploma.super.onBackPressed();
    }


    class SaveDataTwelth extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {


            MyProfileTwelthModal obj = new MyProfileTwelthModal(marksobtained, outofmarks, percentage, schoolnametwelth, selectedstreamBytes1, selectedboardBytes1, monthandyearofpassing12);

            try {
                digest1 = MySharedPreferencesManager.getDigest1(MyProfileTwelthOrDiploma.this);
                digest2 = MySharedPreferencesManager.getDigest2(MyProfileTwelthOrDiploma.this);

                strobj = OtoString(obj, digest1, digest2);

            } catch (Exception e) {
            }

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));    //0
            params.add(new BasicNameValuePair("obj", strobj));        //1

            Log.d("cricket", "MPTOD DIBT odi match");
            json = jParser.makeHttpRequest(Z.url_savedata_twelth, "GET", params);
            try {
                r = json.getString("info");


            } catch (Exception e) {
                e.printStackTrace();
                Log.d("cricket", "MPTOD DIBT odi match lost - " + e.getMessage());
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("cricket", "MPTOD OPET odi match");
            if (result.equals("success")) {
                if (!marksobtained.equals(""))
                    Toast.makeText(MyProfileTwelthOrDiploma.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();

                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                // fill student obj with update value
                s.setMarks12(marksobtained);
                s.setOutof12(outofmarks);
                s.setPercentage12(percentage);
                s.setSchoolname12(schoolnametwelth);
                s.setStream12(selectedStream12);
                s.setBoard12(selectedBoard12);
                s.setYearofpassing12(monthandyearofpassing12);

                MyProfileTwelthOrDiploma.super.onBackPressed();
            }
        }
    }

    class SaveDataDiploma extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            MyProfileDiplomaModal obj2 = new MyProfileDiplomaModal(markssem1, outofsem1, percentsem1, markssem2, outofsem2, percentsem2, markssem3, outofsem3, percentsem3, markssem4, outofsem4, percentsem4, markssem5, outofsem5, percentsem5, markssem6, outofsem6, percentsem6, aggregate, selectedcourseBytes1, selecteddiplomaboardBytes1, schoolnamediploma, monthandyearofpassingdiploma);

            try {
                strobj2 = OtoString(obj2, MySharedPreferencesManager.getDigest1(MyProfileTwelthOrDiploma.this), MySharedPreferencesManager.getDigest2(MyProfileTwelthOrDiploma.this));

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("cricket", "MPTOD DIBD odi match lost - " + e.getMessage());

            }

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));    //0
            params.add(new BasicNameValuePair("obj1", strobj2));        //1
            Log.d("cricket", "MPTOD DIBD odi match");
            json = jParser.makeHttpRequest(Z.url_savedata_diploma, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("cricket", "MPTOD DIBD odi match lost - " + e.getMessage());
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("cricket", "MPTOD OPED odi match");
            if (result.equals("success")) {


                String role = MySharedPreferencesManager.getRole(MyProfileTwelthOrDiploma.this);
                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                if (!markssem1.equals(""))
                    Toast.makeText(MyProfileTwelthOrDiploma.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();


                s.setMarkssem1diploma(markssem1);
                s.setOutofsem1diploma(outofsem1);
                s.setPercentagesem1diploma(percentsem1);
                s.setMarkssem2diploma(markssem2);
                s.setOutofsem2diploma(outofsem2);
                s.setPercentagesem2diploma(percentsem2);
                s.setMarkssem3diploma(markssem3);
                s.setOutofsem3diploma(outofsem3);
                s.setPercentagesem3diploma(percentsem3);
                s.setMarkssem4diploma(markssem4);
                s.setOutofsem4diploma(outofsem4);

                s.setPercentagesem4diploma(percentsem4);
                s.setMarkssem5diploma(markssem5);
                s.setOutofsem5diploma(outofsem5);
                s.setPercentagesem5diploma(percentsem5);
                s.setMarkssem6diploma(markssem6);
                s.setOutofsem6diploma(outofsem6);
                s.setPercentagesem6diploma(percentsem6);
                s.setAggregatediploma(aggregate);
                s.setUniversitydiploma(selectedBoarddiploma);
                s.setCollegenamediploma(schoolnamediploma);
                s.setYearofpassingdiploma(monthandyearofpassingdiploma);
                s.setCoursediploma(selectedcourseBytes1);
                s.setUniversitydiploma(selecteddiplomaboardBytes1);

            } else
                Toast.makeText(MyProfileTwelthOrDiploma.this, "Try again !", Toast.LENGTH_SHORT).show();
        }
    }
}
