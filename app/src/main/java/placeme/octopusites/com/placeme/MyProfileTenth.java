package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
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

import placeme.octopusites.com.placeme.modal.MyProfileTenthModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;


public class MyProfileTenth extends AppCompatActivity {

    EditText marks10, outof10, percent10, schoolname10, yearofpassing10, otherboard;
    TextView tenthtxt;
    TextInputLayout marks10input, marks10_outOf, schoolname10input, yearofpassing10inpute,otherboardinput,marks10_percentage;
    Spinner board10;

    String username, role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    String selectedBoard = "";
    int edittedFlag = 0;
    String marksobtained = "", outofmarks = "", percentage = "", schoolname = "", monthandyearofpassing = "", otherspecifiedboard = "", encobj = "",selectboard="";
    String oldBoard = "";
    StudentData s = new StudentData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_tenth);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username = MySharedPreferencesManager.getUsername(this);
        role = MySharedPreferencesManager.getRole(this);


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Educational Info");
        ab.setDisplayHomeAsUpEnabled(true);

        yearofpassing10 = (EditText) findViewById(R.id.yearofpassing10);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        TextView tenthtxt = (TextView) findViewById(R.id.tenthtxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/arba.ttf");
        tenthtxt.setTypeface(custom_font1);

        marks10 = (EditText) findViewById(R.id.marks10);
        outof10 = (EditText) findViewById(R.id.outof10);
        percent10 = (EditText) findViewById(R.id.percent10);
        schoolname10 = (EditText) findViewById(R.id.schoolname10);
        board10 = (Spinner) findViewById(R.id.board10);
        yearofpassing10 = (EditText) findViewById(R.id.yearofpassing10);
        otherboard = (EditText) findViewById(R.id.otherboard);
        tenthtxt = (TextView) findViewById(R.id.tenthtxt);

        marks10input = (TextInputLayout) findViewById(R.id.marks10input);
        marks10_outOf = (TextInputLayout) findViewById(R.id.marks10_outOf);
        marks10_percentage = (TextInputLayout) findViewById(R.id.marks10_percentage);
        schoolname10input = (TextInputLayout) findViewById(R.id.schoolname10input);
        yearofpassing10inpute = (TextInputLayout) findViewById(R.id.yearofpassing10inpute);
        otherboardinput= (TextInputLayout) findViewById(R.id.otherboardinput);
        marks10_percentage= (TextInputLayout) findViewById(R.id.marks10_percentage);

        marks10input.setTypeface(Z.getLight(this));
        marks10_outOf.setTypeface(Z.getLight(this));
        marks10_percentage.setTypeface(Z.getLight(this));
        schoolname10input.setTypeface(Z.getLight(this));
        yearofpassing10inpute.setTypeface(Z.getLight(this));
        otherboardinput.setTypeface(Z.getLight(this));
        marks10_percentage.setTypeface(Z.getLight(this));

        marks10.setTypeface(Z.getBold(this));
        outof10.setTypeface(Z.getBold(this));
        percent10.setTypeface(Z.getBold(this));
        schoolname10.setTypeface(Z.getBold(this));
        yearofpassing10.setTypeface(Z.getBold(this));
        otherboard.setTypeface(Z.getBold(this));
        tenthtxt.setTypeface(Z.getBold(this));


        marks10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                marks10input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

                marks10_outOf.setError(null);

                try {
                    double percentage = 0;
                    String s1 = marks10.getText().toString();
                    String s2 = outof10.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage > 0 && percentage <= 100)
                                percent10.setText("" + (new DecimalFormat("##.##").format(percentage)));

                        } else {
                            marks10_outOf.setError("Kindly enter valid Out Of Marks");
                            percent10.setText("");
                        }
                    } else {
                        percent10.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        outof10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                marks10_outOf.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

//                try {
//
//                    String s1 = marks10.getText().toString();
//                    String s2 = outof10.getText().toString();
//                    if (!s1.equals("") && !s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100 / n2);
//
//                        if (percentage >= 0 && percentage <= 100)
//                            percent10.setText("" + (new DecimalFormat("##.##").format(percentage)));
//                    }
//                } catch (Exception e) {
//                }

                marks10_outOf.setError(null);

                try {
                    double percentage = 0;
                    String s1 = marks10.getText().toString();
                    String s2 = outof10.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage > 0 && percentage <= 100)
                                percent10.setText("" + (new DecimalFormat("##.##").format(percentage)));


                        } else {
                            marks10_outOf.setError("Kindly enter valid Out Of Marks");
                            percent10.setText("");
                        }
                    } else {
                        percent10.setText("");
                    }
                } catch (Exception e) {
                }

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
        schoolname10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                schoolname10input.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofpassing10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                yearofpassing10inpute.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

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

                View view= super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(MyProfileTenth.this));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(MyProfileTenth.this));

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
        board10.setAdapter(dataAdapter);

        board10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBoard = (String) parent.getItemAtPosition(position);
                TextInputLayout otherboardinput = (TextInputLayout) findViewById(R.id.otherboardinput);
                if (selectedBoard.equals("Other")) {

                    otherboardinput.setVisibility(View.VISIBLE);
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

        marksobtained = s.getMarks10();
        outofmarks = s.getOutof10();
        percentage = s.getPercentage10();
        schoolname = s.getSchoolname10();
        monthandyearofpassing = s.getYearofpassing10();
        selectedBoard = s.getBoard10();
        oldBoard = selectedBoard;


        if (marksobtained != null)
            marks10.setText(marksobtained);
        if (outofmarks != null)
            outof10.setText(outofmarks);
        if (percentage != null)
            percent10.setText(percentage);
        if (schoolname != null)
            schoolname10.setText(schoolname);
        if (monthandyearofpassing != null)
            yearofpassing10.setText(monthandyearofpassing);
        if (selectedBoard != null) {
            int foundboard = 0;
            for (int i = 1; i < boards.length - 1; i++)
                if (selectedBoard.equals(boards[i])) {
                    foundboard = 1;
                    break;
                }
            if (foundboard == 1)
                board10.setSelection(dataAdapter.getPosition(selectedBoard));
            else {
                board10.setSelection(dataAdapter.getPosition("Other"));
                otherboard.setVisibility(View.VISIBLE);
                otherboard.setText(selectedBoard);
            }
        }

        yearofpassing10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfileTenth.this);
                LayoutInflater inflater = MyProfileTenth.this.getLayoutInflater();
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

                monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfileTenth.this));
                monthView.setWheelData(monthList);
                yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfileTenth.this));
                yearView.setWheelData(yearList);

                View setselectionview = (View) dialog.findViewById(R.id.setselectionview);
                View cancelselectionview = (View) dialog.findViewById(R.id.cancelselectionview);

                final AlertDialog alertDialog = dialogBuilder.create();


                setselectionview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        yearofpassing10inpute.setError(null);
                        int monthPosition = monthView.getCurrentPosition();
                        int yearPosition = yearView.getCurrentPosition();

                        String selectedMonth = monthList.get(monthPosition);
                        String selectedYear = yearList.get(yearPosition);

                        setMonthYear(selectedMonth, selectedYear);

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
        edittedFlag = 0;
        //Toast.makeText(MyProfileTenth.this,edittedFlag+"",Toast.LENGTH_LONG).show();

    }

    void setMonthYear(String selectedMonth, String selectedYear) {
        yearofpassing10.setText(selectedMonth + ", " + selectedYear);
    }

    void validateandSave() {
//        marks10.setError(null);
//        outof10.setError(null);
//        percent10.setError(null);
//        schoolname10.setError(null);
//        otherboard.setError(null);
//        yearofpassing10.setError(null);

        int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0, errorflag6 = 0, errorflag7 = 0;

        marksobtained = marks10.getText().toString();
        outofmarks = outof10.getText().toString();
        percentage = percent10.getText().toString();
        schoolname = schoolname10.getText().toString();
        monthandyearofpassing = yearofpassing10.getText().toString();

        if (marksobtained.length() < 1) {
            errorflag1 = 1;
            marks10input.setError("Kindly enter valid marks");
        } else {
            if (outofmarks.length() < 1) {
                errorflag2 = 1;

                marks10_outOf.setError("Kindly enter valid marks");
            } else {
                if (percentage.length() < 1) {
                    errorflag3 = 1;
                    marks10_percentage.setError("Incorrect Percentage");
                } else {
                    if (schoolname.length() < 3) {
                        errorflag4 = 1;

                        schoolname10input.setError("Kindly enter valid school name");
                    } else {
                        if (selectedBoard == "" || selectedBoard == null || selectedBoard.equals("- Select Board -")) {
                            errorflag5 = 1;
                            Toast.makeText(MyProfileTenth.this, "Select Board", Toast.LENGTH_LONG).show();
                        } else {
                            if (selectedBoard.equals("Other")) {
                                otherspecifiedboard = otherboard.getText().toString();
                                if (otherspecifiedboard.length() < 3) {
                                    errorflag6 = 1;
                                    otherboardinput.setError("Kindly enter valid board");
                                }
                            }
                            if (monthandyearofpassing.length() < 9 || monthandyearofpassing.length() > 9) {
                                errorflag7 = 1;
                                yearofpassing10inpute.setError("Kindly select valid Month,Year");
                            }
                        }
                    }

                }
            }
        }


        if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 && errorflag5 == 0 && errorflag6 == 0 && errorflag7 == 0) {
            try {

                if (selectedBoard.equals("Other")) {
                    selectboard = otherspecifiedboard;
                }
                else {
                    selectboard = selectedBoard;

                }






                Log.d("TAG", "validateandSave: before objstr" + marksobtained + " " + outofmarks + " " + percentage + " " + schoolname + " " + monthandyearofpassing + " " + selectboard);

                MyProfileTenthModal obj2 = new MyProfileTenthModal(marksobtained, outofmarks, percentage, schoolname, monthandyearofpassing, selectboard);

                Log.d("TAG", "validateandSave: " + obj2.marksobtained + " " + obj2.outofmarks + " " + obj2.percentage + " " + obj2.schoolname + " " + obj2.monthandyearofpassing + " " + obj2.selectedBoard);
//                Log.d("TAG", "validateandSave: "+obj2.marksobtained+" "+);

                encobj = OtoString(obj2, MySharedPreferencesManager.getDigest1(MyProfileTenth.this), MySharedPreferencesManager.getDigest2(MyProfileTenth.this));
                Log.d("TAG", "validateandSave: encobj - " + encobj);


                new SaveData().execute();

            } catch (Exception e) {

            }
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
                                    MyProfileTenth.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileTenth.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileTenth.this));
                }
            });

            alertDialog.show();
        } else
            MyProfileTenth.super.onBackPressed();
    }

    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));    //0
            params.add(new BasicNameValuePair("m", encobj));        //1

            json = jParser.makeHttpRequest(Z.url_SaveTenth, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {
                Toast.makeText(MyProfileTenth.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();

                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                s.setMarks10(marksobtained);
                s.setOutof10(outofmarks);
                s.setPercentage10(percentage);
                s.setSchoolname10(schoolname);
                s.setYearofpassing10(monthandyearofpassing);
                s.setBoard10(selectedBoard);
//                oldBoard=selectedBoard;

                MyProfileTenth.super.onBackPressed();
            }
        }
    }

}
