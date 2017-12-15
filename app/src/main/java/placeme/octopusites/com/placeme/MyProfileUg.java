package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
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

import placeme.octopusites.com.placeme.modal.MyProfileUgModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class MyProfileUg extends AppCompatActivity {

    EditText umarkssem1,uoutofsem1,upercentsem1,umarkssem2,uoutofsem2,upercentsem2,umarkssem3,uoutofsem3,upercentsem3,umarkssem4,uoutofsem4,upercentsem4,umarkssem5,uoutofsem5,upercentsem5,umarkssem6,uoutofsem6,upercentsem6,umarkssem7,uoutofsem7,upercentsem7,umarkssem8,uoutofsem8,upercentsem8,uaggregate;
    Spinner ucourse,ustream,uuniversity;
    EditText othercourse,otherstream,otheruniversity,schoolnameu,yearofpassingu;
    String markssem1,outofsem1,percentsem1,markssem2,outofsem2,percentsem2,markssem3,outofsem3,percentsem3,markssem4,outofsem4,percentsem4,markssem5,outofsem5,percentsem5,markssem6,outofsem6,percentsem6,markssem7,outofsem7,percentsem7,markssem8,outofsem8,percentsem8,aggregate,schoolname,monthandyearofpassing,otherspecifiedcourse,otherspecifiedstream,otherspecifieduniversity;
    String selectedCourse="",selectedStream="",selectedUniversity="";
    String encmarkssem1,encoutofsem1,encpercentsem1,encmarkssem2,encoutofsem2,encpercentsem2,encmarkssem3,encoutofsem3,encpercentsem3,encmarkssem4,encoutofsem4,encpercentsem4,encmarkssem5,encoutofsem5,encpercentsem5,encmarkssem6,encoutofsem6,encpercentsem6,encmarkssem7,encoutofsem7,encpercentsem7,encmarkssem8,encoutofsem8,encpercentsem8,encaggregate,encschoolname,encmonthandyearofpassing;
    String encselectedcourse,encselectedstream,encselecteduniversity;
    String username,role;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;

    int coursecount=0,streamcount=0,universitycount=0;
    List<String> courseslist = new ArrayList<String>();
    List<String> streamlist = new ArrayList<String>();
    List<String> universitieslist = new ArrayList<String>();
    String[] courses,streams,universities;
    StudentData s=new StudentData();
    String oldCourse="",oldStream="",oldUniversity="",encobj="";
    int edittedFlag=0,isCourseSet=0,isStreamSet=0;

    public static String url_savedata_ug= "http://192.168.100.30:8080/CreateNotificationTemp/SaveUg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_ug);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Educational Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        umarkssem1=(EditText)findViewById(R.id.umarkssem1);
        uoutofsem1=(EditText)findViewById(R.id.uoutofsem1);
        upercentsem1=(EditText)findViewById(R.id.upercentsem1);
        umarkssem2=(EditText)findViewById(R.id.umarkssem2);
        uoutofsem2=(EditText)findViewById(R.id.uoutofsem2);
        upercentsem2=(EditText)findViewById(R.id.upercentsem2);
        umarkssem3=(EditText)findViewById(R.id.umarkssem3);
        uoutofsem3=(EditText)findViewById(R.id.uoutofsem3);
        upercentsem3=(EditText)findViewById(R.id.upercentsem3);
        umarkssem4=(EditText)findViewById(R.id.umarkssem4);
        uoutofsem4=(EditText)findViewById(R.id.uoutofsem4);
        upercentsem4=(EditText)findViewById(R.id.upercentsem4);
        umarkssem5=(EditText)findViewById(R.id.umarkssem5);
        uoutofsem5=(EditText)findViewById(R.id.uoutofsem5);
        upercentsem5=(EditText)findViewById(R.id.upercentsem5);
        umarkssem6=(EditText)findViewById(R.id.umarkssem6);
        uoutofsem6=(EditText)findViewById(R.id.uoutofsem6);
        upercentsem6=(EditText)findViewById(R.id.upercentsem6);
        umarkssem7=(EditText)findViewById(R.id.umarkssem7);
        uoutofsem7=(EditText)findViewById(R.id.uoutofsem7);
        upercentsem7=(EditText)findViewById(R.id.upercentsem7);
        umarkssem8=(EditText)findViewById(R.id.umarkssem8);
        uoutofsem8=(EditText)findViewById(R.id.uoutofsem8);
        upercentsem8=(EditText)findViewById(R.id.upercentsem8);
        uaggregate=(EditText)findViewById(R.id.uaggregate);
        schoolnameu=(EditText)findViewById(R.id.schoolnameu);
        yearofpassingu=(EditText)findViewById(R.id.yearofpassingu);

        othercourse=(EditText)findViewById(R.id.othercourse);
        otherstream=(EditText)findViewById(R.id.otherstream);
        otheruniversity=(EditText)findViewById(R.id.otheruniversity);

        umarkssem1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                umarkssem1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {



//                try {
//
//                    String s1=umarkssem1.getText().toString();
//                    String s2=uoutofsem1.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem1.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}

                uoutofsem1.setError(null);

                try {
                    double percentage = 0;
                    String s1 = umarkssem1.getText().toString();
                    String s2 = uoutofsem1.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem1.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem1.setError("Invalid Out Of Marks");
                            upercentsem1.setText("");
                        }
                    }
                    else
                    {
                        upercentsem1.setText("");
                    }
                } catch (Exception e) {
                }


            }
        });
        uoutofsem1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uoutofsem1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=umarkssem1.getText().toString();
//                    String s2=uoutofsem1.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem1.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                uoutofsem1.setError(null);

                try {
                    double percentage = 0;
                    String s1 = umarkssem1.getText().toString();
                    String s2 = uoutofsem1.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem1.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem1.setError("Invalid Out Of Marks");
                            upercentsem1.setText("");
                        }
                    }
                    else
                    {
                        upercentsem1.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        umarkssem2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                umarkssem2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {


//
//                try {
//
//                    String s1=umarkssem2.getText().toString();
//                    String s2=uoutofsem2.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem2.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                uoutofsem2.setError(null);

                try {
                    double percentage = 0;
                    String s1 = umarkssem2.getText().toString();
                    String s2 = uoutofsem2.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem2.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem2.setError("Invalid Out Of Marks");
                            upercentsem2.setText("");
                        }
                    }
                    else
                    {
                        upercentsem2.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        uoutofsem2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uoutofsem2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=umarkssem2.getText().toString();
//                    String s2=uoutofsem2.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem2.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}




                uoutofsem2.setError(null);

                try {
                    double percentage = 0;
                    String s1 = umarkssem2.getText().toString();
                    String s2 = uoutofsem2.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem2.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem2.setError("Invalid Out Of Marks");
                            upercentsem2.setText("");
                        }
                    }
                    else
                    {
                        upercentsem2.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        umarkssem3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                umarkssem3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {



//                try {
//
//                    String s1=umarkssem3.getText().toString();
//                    String s2=uoutofsem3.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem3.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}



                uoutofsem3.setError(null);

                try {
                    double percentage = 0;
                    String s1 = umarkssem3.getText().toString();
                    String s2 = uoutofsem3.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem3.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem3.setError("Invalid Out Of Marks");
                            upercentsem3.setText("");
                        }
                    }
                    else
                    {
                        upercentsem3.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        uoutofsem3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uoutofsem3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=umarkssem3.getText().toString();
//                    String s2=uoutofsem3.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem3.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                uoutofsem3.setError(null);

                try {
                    double percentage = 0;
                    String s1 = umarkssem3.getText().toString();
                    String s2 = uoutofsem3.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem3.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem3.setError("Invalid Out Of Marks");
                            upercentsem3.setText("");
                        }
                    }
                    else
                    {
                        upercentsem3.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        umarkssem4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                umarkssem4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {



//                try {
//
//                    String s1=umarkssem4.getText().toString();
//                    String s2=uoutofsem4.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem4.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                uoutofsem4.setError(null);

                try {
                    double percentage = 0;
                    String s1 = umarkssem4.getText().toString();
                    String s2 = uoutofsem4.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem4.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem4.setError("Invalid Out Of Marks");
                            upercentsem4.setText("");
                        }
                    }
                    else
                    {
                        upercentsem4.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        uoutofsem4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uoutofsem4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=umarkssem4.getText().toString();
//                    String s2=uoutofsem4.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem4.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}
                uoutofsem4.setError(null);
                try {
                    double percentage = 0;
                    String s1 = umarkssem4.getText().toString();
                    String s2 = uoutofsem4.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem4.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem4.setError("Invalid Out Of Marks");
                            upercentsem4.setText("");
                        }
                    }
                    else
                    {
                        upercentsem4.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        umarkssem5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                umarkssem5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

//
//
//                try {
//
//                    String s1=umarkssem5.getText().toString();
//                    String s2=uoutofsem5.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem5.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}
                uoutofsem5.setError(null);
                try {
                    double percentage = 0;
                    String s1 = umarkssem5.getText().toString();
                    String s2 = uoutofsem5.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem5.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem5.setError("Invalid Out Of Marks");
                            upercentsem5.setText("");
                        }
                    }
                    else
                    {
                        upercentsem5.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        uoutofsem5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uoutofsem5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=umarkssem5.getText().toString();
//                    String s2=uoutofsem5.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem5.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                uoutofsem5.setError(null);
                try {
                    double percentage = 0;
                    String s1 = umarkssem5.getText().toString();
                    String s2 = uoutofsem5.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem5.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem5.setError("Invalid Out Of Marks");
                            upercentsem5.setText("");
                        }
                    }
                    else
                    {
                        upercentsem5.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        umarkssem6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                umarkssem6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=umarkssem6.getText().toString();
//                    String s2=uoutofsem6.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem6.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                uoutofsem6.setError(null);
                try {
                    double percentage = 0;
                    String s1 = umarkssem6.getText().toString();
                    String s2 = uoutofsem6.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem6.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem6.setError("Invalid Out Of Marks");
                            upercentsem6.setText("");
                        }
                    }
                    else
                    {
                        upercentsem6.setText("");
                    }
                } catch (Exception e) {
                }
            }
        });
        uoutofsem6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uoutofsem6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=umarkssem6.getText().toString();
//                    String s2=uoutofsem6.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem6.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}

                uoutofsem6.setError(null);
                try {
                    double percentage = 0;
                    String s1 = umarkssem6.getText().toString();
                    String s2 = uoutofsem6.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem6.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem6.setError("Invalid Out Of Marks");
                            upercentsem6.setText("");
                        }
                    }
                    else
                    {
                        upercentsem6.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        umarkssem7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                umarkssem7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {



//                try {
//
//                    String s1=umarkssem7.getText().toString();
//                    String s2=uoutofsem7.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem7.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                uoutofsem7.setError(null);
                try {
                    double percentage = 0;
                    String s1 = umarkssem7.getText().toString();
                    String s2 = uoutofsem7.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem7.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem7.setError("Invalid Out Of Marks");
                            upercentsem7.setText("");
                        }
                    }
                    else
                    {
                        upercentsem7.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        uoutofsem7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uoutofsem7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=umarkssem7.getText().toString();
//                    String s2=uoutofsem7.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem7.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                uoutofsem7.setError(null);
                try {
                    double percentage = 0;
                    String s1 = umarkssem7.getText().toString();
                    String s2 = uoutofsem7.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem7.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem7.setError("Invalid Out Of Marks");
                            upercentsem7.setText("");
                        }
                    }
                    else
                    {
                        upercentsem7.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });

        umarkssem8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                umarkssem8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {



//                try {
//
//                    String s1=umarkssem8.getText().toString();
//                    String s2=uoutofsem8.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem8.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                uoutofsem8.setError(null);
                try {
                    double percentage = 0;
                    String s1 = umarkssem8.getText().toString();
                    String s2 = uoutofsem8.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem8.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem8.setError("Invalid Out Of Marks");
                            upercentsem8.setText("");
                        }
                    }
                    else
                    {
                        upercentsem8.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        uoutofsem8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uoutofsem8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
//
//                    String s1=umarkssem8.getText().toString();
//                    String s2=uoutofsem8.getText().toString();
//                    if(!s1.equals("")&&!s2.equals("")) {
//                        double n1 = Double.parseDouble(s1);
//                        double n2 = Double.parseDouble(s2);
//
//                        double percentage = (n1 * 100/ n2);
//
//                        if(percentage>=0&&percentage<=100)
//                            upercentsem8.setText("" +(new DecimalFormat("##.##").format(percentage)));
//                    }
//                }catch (Exception e){}


                uoutofsem8.setError(null);
                try {
                    double percentage = 0;
                    String s1 = umarkssem8.getText().toString();
                    String s2 = uoutofsem8.getText().toString();
                    if (!s1.equals("") && !s2.equals("")) {
                        double n1 = Double.parseDouble(s1);
                        double n2 = Double.parseDouble(s2);

                        if (n1 <= n2) {
                            percentage = (n1 * 100 / n2);

                            if (percentage >= 0 && percentage <= 100) {

                                upercentsem8.setText("" + (new DecimalFormat("##.##").format(percentage)));
                            }
                        } else {
                            uoutofsem8.setError("Invalid Out Of Marks");
                            upercentsem8.setText("");
                        }
                    }
                    else
                    {
                        upercentsem8.setText("");
                    }
                } catch (Exception e) {
                }

            }
        });
        schoolnameu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                schoolnameu.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        uaggregate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uaggregate.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofpassingu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                yearofpassingu.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ucourse=(Spinner)findViewById(R.id.ucourse);
        ustream=(Spinner)findViewById(R.id.ustream);
        uuniversity=(Spinner)findViewById(R.id.uuniversity);

        new GetCourses().execute();
        new GetUniversities().execute();

        yearofpassingu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfileUg.this);
                LayoutInflater inflater = MyProfileUg.this.getLayoutInflater();
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
                for(int i=1975;i<=cur.get(Calendar.YEAR)+5;i++)
                    yearList.add(""+i);



                monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfileUg.this));
                monthView.setWheelData(monthList);
                yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfileUg.this));
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

                        setMonthYear(selectedMonth,selectedYear);

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



        TextView ugtxt=(TextView)findViewById(R.id.ugtxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        ugtxt.setTypeface(custom_font1);

        ScrollView myprofileug=(ScrollView)findViewById(R.id.myprofileug);
        disableScrollbars(myprofileug);


        markssem1=s.getMarkssem1ug();
        outofsem1=s.getOutofsem1ug();
        percentsem1=s.getPercentagesem1ug();
        markssem2=s.getMarkssem2ug();
        outofsem2=s.getOutofsem2ug();
        percentsem2=s.getPercentagesem2ug();
        markssem3=s.getMarkssem3ug();
        outofsem3=s.getOutofsem3ug();
        percentsem3=s.getPercentagesem3ug();
        markssem4=s.getMarkssem4ug();
        outofsem4=s.getOutofsem4ug();
        percentsem4=s.getPercentagesem4ug();
        markssem5=s.getMarkssem5ug();
        outofsem5=s.getOutofsem5ug();
        percentsem5=s.getPercentagesem5ug();
        markssem6=s.getMarkssem6ug();
        outofsem6=s.getOutofsem6ug();
        percentsem6=s.getPercentagesem6ug();
        markssem7=s.getMarkssem7ug();
        outofsem7=s.getOutofsem7ug();
        percentsem7=s.getPercentagesem7ug();
        markssem8=s.getMarkssem8ug();
        outofsem8=s.getOutofsem8ug();
        percentsem8=s.getPercentagesem8ug();
        aggregate=s.getAggregateug();
        schoolname=s.getCollegenameug();
        monthandyearofpassing=s.getYearofpassingug();

        // Toast.makeText(MyProfileUg.this,s.getUniversityug()+s.getCollegenameug(),Toast.LENGTH_LONG).show();
        if(markssem1!=null)
            umarkssem1.setText(markssem1);
        if(outofsem1!=null)
            uoutofsem1.setText(outofsem1);
        if(percentsem1!=null)
            upercentsem1.setText(percentsem1);
        if(markssem2!=null)
            umarkssem2.setText(markssem2);
        if(outofsem2!=null)
            uoutofsem2.setText(outofsem2);
        if(percentsem2!=null)
            upercentsem2.setText(percentsem2);
        if(markssem3!=null)
            umarkssem3.setText(markssem3);
        if(outofsem3!=null)
            uoutofsem3.setText(outofsem3);
        if(percentsem3!=null)
            upercentsem3.setText(percentsem3);
        if(markssem4!=null)
            umarkssem4.setText(markssem4);
        if(outofsem4!=null)
            uoutofsem4.setText(outofsem4);
        if(percentsem4!=null)
            upercentsem4.setText(percentsem4);
        if(markssem5!=null)
            umarkssem5.setText(markssem5);
        if(outofsem5!=null)
            uoutofsem5.setText(outofsem5);
        if(percentsem5!=null)
            upercentsem5.setText(percentsem5);
        if(markssem6!=null)
            umarkssem6.setText(markssem6);
        if(outofsem6!=null)
            uoutofsem6.setText(outofsem6);
        if(percentsem6!=null)
            upercentsem6.setText(percentsem6);
        if(markssem7!=null)
            umarkssem7.setText(markssem7);
        if(outofsem7!=null)
            uoutofsem7.setText(outofsem7);
        if(percentsem7!=null)
            upercentsem7.setText(percentsem7);
        if(markssem8!=null)
            umarkssem8.setText(markssem8);
        if(outofsem8!=null)
            uoutofsem8.setText(outofsem8);
        if(percentsem8!=null)
            upercentsem8.setText(percentsem8);
        if(aggregate!=null)
            uaggregate.setText(aggregate);
        if(schoolname!=null)
            schoolnameu.setText(schoolname);
        if(monthandyearofpassing!=null)
            yearofpassingu.setText(monthandyearofpassing);

        edittedFlag=0;


    }
    class GetCourses extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();

            json = jParser.makeHttpRequest(MyConstants.url_getugcourses, "GET", params);
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
    class GetStreams extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("c",selectedCourse));
            json = jParser.makeHttpRequest(MyConstants.url_getstreams, "GET", params);
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
                RelativeLayout ustreamspinner=(RelativeLayout)findViewById(R.id.ustreamspinner);
                ustreamspinner.setVisibility(View.GONE);
            }
            else{
                RelativeLayout ustreamspinner=(RelativeLayout)findViewById(R.id.ustreamspinner);
                ustreamspinner.setVisibility(View.VISIBLE);
                for (int i = 0; i < streamcount; i++) {
                    streamlist.add(streams[i]);
                }
                populateStreams();
                streamlist.add("Other");
            }
        }
    }

    class GetUniversities extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();

            json = jParser.makeHttpRequest(MyConstants.url_getuniversities, "GET", params);
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
        ucourse.setAdapter(dataAdapter);

        ucourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (String) parent.getItemAtPosition(position);
                TextInputLayout otherboardinput=(TextInputLayout)findViewById(R.id.othercourseinput);
                if(selectedCourse.equals("Other")) {

                    otherboardinput.setVisibility(View.VISIBLE);
                }
                else {
                    new GetStreams().execute();
                    otherboardinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(isCourseSet==0) {
            isCourseSet=1;
            if(s.getCourseug()!=null) {
                ucourse.setSelection(dataAdapter.getPosition(s.getCourseug()));
                selectedCourse = s.getCourseug();
                oldCourse = s.getCourseug();
            }
            else
                oldCourse = "- Select Course -";
        }


    }
    void populateStreams()
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
        ustream.setAdapter(dataAdapter);

        ustream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStream= (String) parent.getItemAtPosition(position);
                TextInputLayout otherboardinput=(TextInputLayout)findViewById(R.id.otherstreaminput);
                if(selectedStream.equals("Other")) {

                    otherboardinput.setVisibility(View.VISIBLE);
                }
                else {

                    otherboardinput.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(isStreamSet==0) {
            isStreamSet=1;
            if(s.getStreamug()!=null) {
                ustream.setSelection(dataAdapter.getPosition(s.getStreamug()));
                selectedStream = s.getStreamug();
                oldStream = s.getStreamug();
            }
            else
                oldStream = "- Select Stream/Specialization -" ;
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
        uuniversity.setAdapter(dataAdapter);

        uuniversity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUniversity= (String) parent.getItemAtPosition(position);
                TextInputLayout otheruniversityinput=(TextInputLayout)findViewById(R.id.otheruniversityinput);
                if(selectedUniversity.equals("Other")) {

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
        selectedUniversity=s.getUniversityug();
        if(selectedUniversity!=null)
        {
            uuniversity.setSelection(dataAdapter.getPosition(selectedUniversity));
            oldUniversity=selectedUniversity;
        }
        else
            oldUniversity="- Select University -";
    }
    void setMonthYear(String selectedMonth,String selectedYear)
    {
        yearofpassingu.setText(selectedMonth+", "+selectedYear);
    }
    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

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
        umarkssem1.setError(null);
        uoutofsem1.setError(null);
        upercentsem1.setError(null);
        umarkssem2.setError(null);
        uoutofsem2.setError(null);
        upercentsem2.setError(null);
        umarkssem3.setError(null);
        uoutofsem3.setError(null);
        upercentsem3.setError(null);
        umarkssem4.setError(null);
        uoutofsem4.setError(null);
        upercentsem4.setError(null);
        umarkssem5.setError(null);
        uoutofsem5.setError(null);
        upercentsem5.setError(null);
        umarkssem6.setError(null);
        uoutofsem6.setError(null);
        upercentsem6.setError(null);
        umarkssem7.setError(null);
        uoutofsem7.setError(null);
        upercentsem7.setError(null);
        umarkssem8.setError(null);
        uoutofsem8.setError(null);
        upercentsem8.setError(null);
        uaggregate.setError(null);
        othercourse.setError(null);
        otherstream.setError(null);
        otheruniversity.setError(null);
        schoolnameu.setError(null);
        yearofpassingu.setError(null);


        markssem1 = umarkssem1.getText().toString();
        outofsem1 = uoutofsem1.getText().toString();
        percentsem1 = upercentsem1.getText().toString();
        markssem2 = umarkssem2.getText().toString();
        outofsem2 = uoutofsem2.getText().toString();
        percentsem2 = upercentsem2.getText().toString();
        markssem3 = umarkssem3.getText().toString();
        outofsem3 = uoutofsem3.getText().toString();
        percentsem3 = upercentsem3.getText().toString();
        markssem4 = umarkssem4.getText().toString();
        outofsem4 = uoutofsem4.getText().toString();
        percentsem4 = upercentsem4.getText().toString();
        markssem5 = umarkssem5.getText().toString();
        outofsem5 = uoutofsem5.getText().toString();
        percentsem5 = upercentsem5.getText().toString();
        markssem6 = umarkssem6.getText().toString();
        outofsem6 = uoutofsem6.getText().toString();
        percentsem6 = upercentsem6.getText().toString();
        markssem7 = umarkssem7.getText().toString();
        outofsem7 = uoutofsem7.getText().toString();
        percentsem7 = upercentsem7.getText().toString();
        markssem8 = umarkssem8.getText().toString();
        outofsem8 = uoutofsem8.getText().toString();
        percentsem8 = upercentsem8.getText().toString();
        aggregate = uaggregate.getText().toString();
        schoolname = schoolnameu.getText().toString();
        monthandyearofpassing = yearofpassingu.getText().toString();

        int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0;
        if (markssem1.length() < 1) {
            errorflag1 = 1;
            umarkssem1.setError("Incorrect Marks");
        } else {
            errorflag1 = 0;
            if (outofsem1.length() < 1) {
                errorflag1 = 1;
                umarkssem1.setError(null);
                uoutofsem1.setError("Incorrect Marks");
            } else {
                errorflag1 = 0;
                if (percentsem1.length() < 1) {
                    errorflag1 = 1;
                    umarkssem1.setError(null);
                    uoutofsem1.setError(null);
                    upercentsem1.setError("Incorrect Percentage");
                } else {
                    errorflag1 = 0;
                    if (markssem2.length() < 1) {
                        errorflag1 = 1;
                        umarkssem2.setError("Incorrect Marks");
                    } else {
                        errorflag1 = 0;
                        if (outofsem2.length() < 1) {
                            errorflag1 = 1;
                            umarkssem2.setError(null);
                            uoutofsem2.setError("Incorrect Marks");
                        } else {
                            errorflag1 = 0;
                            if (percentsem2.length() < 1) {
                                errorflag1 = 1;
                                umarkssem2.setError(null);
                                uoutofsem2.setError(null);
                                upercentsem2.setError("Incorrect Percentage");
                            } else {
                                errorflag1 = 0;
                                if (markssem3.length() < 1) {
                                    errorflag1 = 1;
                                    umarkssem3.setError("Incorrect Marks");
                                } else {
                                    errorflag1 = 0;
                                    if (outofsem3.length() < 1) {
                                        errorflag1 = 1;
                                        umarkssem3.setError(null);
                                        uoutofsem3.setError("Incorrect Marks");
                                    } else {
                                        errorflag1 = 0;
                                        if (percentsem3.length() < 1) {
                                            errorflag1 = 1;
                                            umarkssem3.setError(null);
                                            uoutofsem3.setError(null);
                                            upercentsem3.setError("Incorrect Percentage");
                                        } else {
                                            errorflag1 = 0;
                                            if (markssem4.length() < 1) {
                                                errorflag1 = 1;
                                                umarkssem4.setError("Incorrect Marks");
                                            } else {
                                                errorflag1 = 0;
                                                if (outofsem4.length() < 1) {
                                                    errorflag1 = 1;
                                                    umarkssem4.setError(null);
                                                    uoutofsem4.setError("Incorrect Marks");
                                                } else {
                                                    errorflag1 = 0;
                                                    if (percentsem4.length() < 1) {
                                                        errorflag1 = 1;
                                                        umarkssem4.setError(null);
                                                        uoutofsem4.setError(null);
                                                        upercentsem4.setError("Incorrect Percentage");
                                                    } else {
                                                        errorflag1 = 0;
                                                        if (markssem5.length() < 1) {
                                                            errorflag1 = 1;
                                                            umarkssem5.setError("Incorrect Marks");
                                                        } else {
                                                            errorflag1 = 0;
                                                            if (outofsem5.length() < 1) {
                                                                errorflag1 = 1;
                                                                umarkssem5.setError(null);
                                                                uoutofsem5.setError("Incorrect Marks");
                                                            } else {
                                                                errorflag1 = 0;
                                                                if (percentsem5.length() < 1) {
                                                                    errorflag1 = 1;
                                                                    umarkssem5.setError(null);
                                                                    uoutofsem5.setError(null);
                                                                    upercentsem5.setError("Incorrect Percentage");
                                                                } else {
                                                                    errorflag1 = 0;
                                                                    if (markssem6.length() < 1) {
                                                                        errorflag1 = 1;
                                                                        umarkssem6.setError("Incorrect Marks");
                                                                    } else {
                                                                        errorflag1 = 0;
                                                                        if (outofsem6.length() < 1) {
                                                                            errorflag1 = 1;
                                                                            umarkssem6.setError(null);
                                                                            uoutofsem6.setError("Incorrect Marks");
                                                                        } else {
                                                                            errorflag1 = 0;
                                                                            if (percentsem6.length() < 1) {
                                                                                errorflag1 = 1;
                                                                                umarkssem6.setError(null);
                                                                                uoutofsem6.setError(null);
                                                                                upercentsem6.setError("Incorrect Percentage");
                                                                            } else {
                                                                                errorflag1 = 0;
                                                                                if (markssem7.length() < 1) {
                                                                                    errorflag1 = 1;
                                                                                    umarkssem7.setError("Incorrect Marks");
                                                                                } else {
                                                                                    errorflag1 = 0;
                                                                                    if (outofsem7.length() < 1) {
                                                                                        errorflag1 = 1;
                                                                                        umarkssem7.setError(null);
                                                                                        uoutofsem7.setError("Incorrect Marks");
                                                                                    } else {
                                                                                        errorflag1 = 0;
                                                                                        if (percentsem7.length() < 1) {
                                                                                            errorflag1 = 1;
                                                                                            umarkssem7.setError(null);
                                                                                            uoutofsem7.setError(null);
                                                                                            upercentsem7.setError("Incorrect Percentage");
                                                                                        } else {
                                                                                            errorflag1 = 0;
                                                                                            if (markssem8.length() < 1) {
                                                                                                errorflag1 = 1;
                                                                                                umarkssem8.setError("Incorrect Marks");
                                                                                            } else {
                                                                                                errorflag1 = 0;
                                                                                                if (outofsem8.length() < 1) {
                                                                                                    errorflag1 = 1;
                                                                                                    umarkssem8.setError(null);
                                                                                                    uoutofsem8.setError("Incorrect Marks");
                                                                                                } else {
                                                                                                    errorflag1 = 0;
                                                                                                    if (percentsem8.length() < 1) {
                                                                                                        errorflag1 = 1;
                                                                                                        umarkssem8.setError(null);
                                                                                                        uoutofsem8.setError(null);
                                                                                                        upercentsem8.setError("Incorrect Percentage");
                                                                                                    } else {
                                                                                                        errorflag1 = 0;
                                                                                                        float aggg=0;
                                                                                                        try{
                                                                                                            aggg=Float.parseFloat(aggregate);}catch (NumberFormatException e){errorflag1 = 1; uaggregate.setError("Incorrect Aggregate");}
                                                                                                        if (aggg<0||aggg>100) {
                                                                                                            errorflag1 = 1;
                                                                                                            umarkssem6.setError(null);
                                                                                                            uoutofsem6.setError(null);
                                                                                                            uoutofsem6.setError(null);
                                                                                                            uaggregate.setError("Incorrect Aggregate");
                                                                                                        } else {
                                                                                                            errorflag1 = 0;
                                                                                                            if (selectedCourse.equals("- Select Course -")) {
                                                                                                                errorflag1 = 1;
                                                                                                                uaggregate.setError(null);
                                                                                                                Toast.makeText(MyProfileUg.this, "Select Course", Toast.LENGTH_LONG).show();
                                                                                                            } else {
                                                                                                                if (selectedCourse.equals("Other")) {
                                                                                                                    otherspecifiedcourse = othercourse.getText().toString();
                                                                                                                    if (otherspecifiedcourse.length() < 3) {
                                                                                                                        errorflag2 = 1;
                                                                                                                        uaggregate.setError(null);
                                                                                                                        othercourse.setError("Invalid Course");
                                                                                                                    }
                                                                                                                }


                                                                                                                if (selectedUniversity.equals("- Select University -")) {
                                                                                                                    errorflag3 = 1;
                                                                                                                    uaggregate.setError(null);
                                                                                                                    othercourse.setError(null);
                                                                                                                    Toast.makeText(MyProfileUg.this, "Select University", Toast.LENGTH_LONG).show();
                                                                                                                }
                                                                                                                else {
                                                                                                                    if(schoolname.length()<3)
                                                                                                                    {
                                                                                                                        errorflag4=1;
                                                                                                                        schoolnameu.setError("Invalid College Name");
                                                                                                                    }
                                                                                                                    if (selectedUniversity.equals("Other")) {
                                                                                                                        otherspecifieduniversity = otheruniversity.getText().toString();

                                                                                                                        if (otherspecifieduniversity.length() < 3) {
                                                                                                                            errorflag4 = 1;

                                                                                                                            otheruniversity.setError("Invalid University");
                                                                                                                        }
                                                                                                                    }

                                                                                                                    if (monthandyearofpassing.length() < 9 || monthandyearofpassing.length() > 9) {
                                                                                                                        errorflag5 = 1;
                                                                                                                        otheruniversity.setError(null);
                                                                                                                        yearofpassingu.setError("Invalid Month,Year");
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
                        }
                    }
                }
            }
        }
        if(errorflag1==0&&errorflag2==0&&errorflag3==0&&errorflag4==0&&errorflag5==0)
        {
            try {
                MyProfileUgModal obj2 = new MyProfileUgModal(markssem1,outofsem1,percentsem1,markssem2,outofsem2,percentsem2,markssem3,outofsem3,percentsem3,markssem4,outofsem4,percentsem4,markssem5,outofsem5,percentsem5,markssem6,outofsem6, percentsem6,markssem7,outofsem7,percentsem7,markssem8,outofsem8,percentsem8,aggregate,schoolname,monthandyearofpassing
                        ,selectedCourse,selectedStream,selectedUniversity);

                encobj =OtoString(obj2,MySharedPreferencesManager.getDigest1(MyProfileUg.this),MySharedPreferencesManager.getDigest2(MyProfileUg.this));
                Log.d("TAG", "validateandSave: encobj - "+encobj);

//                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
//                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
//                String sPadding = "ISO10126Padding";
//
//                byte[] markssem1Bytes = markssem1.getBytes("UTF-8");
//                byte[] outofsem1Bytes = outofsem1.getBytes("UTF-8");
//                byte[] percentsem1Bytes = percentsem1.getBytes("UTF-8");
//                byte[] markssem2Bytes = markssem2.getBytes("UTF-8");
//                byte[] outofsem2Bytes = outofsem2.getBytes("UTF-8");
//                byte[] percentsem2Bytes = percentsem2.getBytes("UTF-8");
//                byte[] markssem3Bytes = markssem3.getBytes("UTF-8");
//                byte[] outofsem3Bytes = outofsem3.getBytes("UTF-8");
//                byte[] percentsem3Bytes = percentsem3.getBytes("UTF-8");
//                byte[] markssem4Bytes = markssem4.getBytes("UTF-8");
//                byte[] outofsem4Bytes = outofsem4.getBytes("UTF-8");
//                byte[] percentsem4Bytes = percentsem4.getBytes("UTF-8");
//                byte[] markssem5Bytes = markssem5.getBytes("UTF-8");
//                byte[] outofsem5Bytes = outofsem5.getBytes("UTF-8");
//                byte[] percentsem5Bytes = percentsem5.getBytes("UTF-8");
//                byte[] markssem6Bytes = markssem6.getBytes("UTF-8");
//                byte[] outofsem6Bytes = outofsem6.getBytes("UTF-8");
//                byte[] percentsem6Bytes = percentsem6.getBytes("UTF-8");
//                byte[] markssem7Bytes = markssem7.getBytes("UTF-8");
//                byte[] outofsem7Bytes = outofsem7.getBytes("UTF-8");
//                byte[] percentsem7Bytes = percentsem7.getBytes("UTF-8");
//                byte[] markssem8Bytes = markssem8.getBytes("UTF-8");
//                byte[] outofsem8Bytes = outofsem8.getBytes("UTF-8");
//                byte[] percentsem8Bytes = percentsem8.getBytes("UTF-8");
//                byte[] aggregateBytes = aggregate.getBytes("UTF-8");
//                byte[] schoolnameBytes = schoolname.getBytes("UTF-8");
//                byte[] monthandyearofpassingBytes = monthandyearofpassing.getBytes("UTF-8");
//                byte[] selectedcourseBytes=null,selectedstreamBytes=null,selecteduniversityBytes=null;
//                if(selectedCourse.equals("Other"))
//                    selectedcourseBytes=otherspecifiedcourse.getBytes("UTF-8");
//                else
//                    selectedcourseBytes=selectedCourse.getBytes("UTF-8");
//                if(selectedStream.equals("Other"))
//                    selectedstreamBytes=otherspecifiedstream.getBytes("UTF-8");
//                else
//                    selectedstreamBytes=selectedStream.getBytes("UTF-8");
//                if(selectedUniversity.equals("Other"))
//                    selecteduniversityBytes=otherspecifieduniversity.getBytes("UTF-8");
//                else
//                    selecteduniversityBytes=selectedUniversity.getBytes("UTF-8");
//
//                byte[] markssem1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem1Bytes);
//                encmarkssem1=new String(SimpleBase64Encoder.encode(markssem1EncryptedBytes));
//                byte[] markssem2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem2Bytes);
//                encmarkssem2=new String(SimpleBase64Encoder.encode(markssem2EncryptedBytes));
//                byte[] markssem3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem3Bytes);
//                encmarkssem3=new String(SimpleBase64Encoder.encode(markssem3EncryptedBytes));
//                byte[] markssem4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem4Bytes);
//                encmarkssem4=new String(SimpleBase64Encoder.encode(markssem4EncryptedBytes));
//                byte[] markssem5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem5Bytes);
//                encmarkssem5=new String(SimpleBase64Encoder.encode(markssem5EncryptedBytes));
//                byte[] markssem6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem6Bytes);
//                encmarkssem6=new String(SimpleBase64Encoder.encode(markssem6EncryptedBytes));
//                byte[] markssem7EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem7Bytes);
//                encmarkssem7=new String(SimpleBase64Encoder.encode(markssem7EncryptedBytes));
//                byte[] markssem8EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, markssem8Bytes);
//                encmarkssem8=new String(SimpleBase64Encoder.encode(markssem8EncryptedBytes));
//                byte[] outofsem1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem1Bytes);
//                encoutofsem1=new String(SimpleBase64Encoder.encode(outofsem1EncryptedBytes));
//                byte[] outofsem2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem2Bytes);
//                encoutofsem2=new String(SimpleBase64Encoder.encode(outofsem2EncryptedBytes));
//                byte[] outofsem3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem3Bytes);
//                encoutofsem3=new String(SimpleBase64Encoder.encode(outofsem3EncryptedBytes));
//                byte[] outofsem4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem4Bytes);
//                encoutofsem4=new String(SimpleBase64Encoder.encode(outofsem4EncryptedBytes));
//                byte[] outofsem5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem5Bytes);
//                encoutofsem5=new String(SimpleBase64Encoder.encode(outofsem5EncryptedBytes));
//                byte[] outofsem6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem6Bytes);
//                encoutofsem6=new String(SimpleBase64Encoder.encode(outofsem6EncryptedBytes));
//                byte[] outofsem7EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem7Bytes);
//                encoutofsem7=new String(SimpleBase64Encoder.encode(outofsem7EncryptedBytes));
//                byte[] outofsem8EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, outofsem8Bytes);
//                encoutofsem8=new String(SimpleBase64Encoder.encode(outofsem8EncryptedBytes));
//                byte[] percentsem1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem1Bytes);
//                encpercentsem1=new String(SimpleBase64Encoder.encode(percentsem1EncryptedBytes));
//                byte[] percentsem2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem2Bytes);
//                encpercentsem2=new String(SimpleBase64Encoder.encode(percentsem2EncryptedBytes));
//                byte[] percentsem3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem3Bytes);
//                encpercentsem3=new String(SimpleBase64Encoder.encode(percentsem3EncryptedBytes));
//                byte[] percentsem4EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem4Bytes);
//                encpercentsem4=new String(SimpleBase64Encoder.encode(percentsem4EncryptedBytes));
//                byte[] percentsem5EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem5Bytes);
//                encpercentsem5=new String(SimpleBase64Encoder.encode(percentsem5EncryptedBytes));
//                byte[] percentsem6EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem6Bytes);
//                encpercentsem6=new String(SimpleBase64Encoder.encode(percentsem6EncryptedBytes));
//                byte[] percentsem7EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem7Bytes);
//                encpercentsem7=new String(SimpleBase64Encoder.encode(percentsem7EncryptedBytes));
//                byte[] percentsem8EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, percentsem8Bytes);
//                encpercentsem8=new String(SimpleBase64Encoder.encode(percentsem8EncryptedBytes));
//                byte[] aggregateEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, aggregateBytes);
//                encaggregate=new String(SimpleBase64Encoder.encode(aggregateEncryptedBytes));
//                byte[] schoolnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, schoolnameBytes);
//                encschoolname=new String(SimpleBase64Encoder.encode(schoolnameEncryptedBytes));
//                byte[] monthandyearofpassingEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, monthandyearofpassingBytes);
//                encmonthandyearofpassing=new String(SimpleBase64Encoder.encode(monthandyearofpassingEncryptedBytes));
//                byte[] selectedcourseEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, selectedcourseBytes);
//                encselectedcourse=new String(SimpleBase64Encoder.encode(selectedcourseEncryptedBytes));
//                byte[] selectedstreamEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, selectedstreamBytes);
//                encselectedstream=new String(SimpleBase64Encoder.encode(selectedstreamEncryptedBytes));
//                byte[] selecteduniversityEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, selecteduniversityBytes);
//                encselecteduniversity=new String(SimpleBase64Encoder.encode(selecteduniversityEncryptedBytes));

                new SaveDataUg().execute();

            }catch (Exception e){

            }
        }


    }
    class SaveDataUg extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));    //0
            params.add(new BasicNameValuePair("m1",encobj));        //1
//            params.add(new BasicNameValuePair("o1",encoutofsem1));       //2
//            params.add(new BasicNameValuePair("p1",encpercentsem1));     //3
//            params.add(new BasicNameValuePair("m2",encmarkssem2));        //4
//            params.add(new BasicNameValuePair("o2",encoutofsem2));       //5
//            params.add(new BasicNameValuePair("p2",encpercentsem2));    //6
//            params.add(new BasicNameValuePair("m3",encmarkssem3));        //7
//            params.add(new BasicNameValuePair("o3",encoutofsem3));       //8
//            params.add(new BasicNameValuePair("p3",encpercentsem3));    //9
//            params.add(new BasicNameValuePair("m4",encmarkssem4));        //10
//            params.add(new BasicNameValuePair("o4",encoutofsem4));       //11
//            params.add(new BasicNameValuePair("p4",encpercentsem4));    //12
//            params.add(new BasicNameValuePair("m5",encmarkssem5));        //13
//            params.add(new BasicNameValuePair("o5",encoutofsem5));       //14
//            params.add(new BasicNameValuePair("p5",encpercentsem5));    //15
//            params.add(new BasicNameValuePair("m6",encmarkssem6));        //16
//            params.add(new BasicNameValuePair("o6",encoutofsem6));       //17
//            params.add(new BasicNameValuePair("p6",encpercentsem6));    //18
//            params.add(new BasicNameValuePair("m7",encmarkssem7));        //19
//            params.add(new BasicNameValuePair("o7",encoutofsem7));       //20
//            params.add(new BasicNameValuePair("p7",encpercentsem7));    //21
//            params.add(new BasicNameValuePair("m8",encmarkssem8));        //22
//            params.add(new BasicNameValuePair("o8",encoutofsem8));       //23
//            params.add(new BasicNameValuePair("p8",encpercentsem8));    //24
//            params.add(new BasicNameValuePair("a",encaggregate));        //25
//            params.add(new BasicNameValuePair("c",encselectedcourse));  //26
//            params.add(new BasicNameValuePair("s1",encselectedstream)); //27
//            params.add(new BasicNameValuePair("u1",encselecteduniversity));       //28
//            params.add(new BasicNameValuePair("s",encschoolname));     //29
//            params.add(new BasicNameValuePair("y",encmonthandyearofpassing));       //30
            json = jParser.makeHttpRequest(url_savedata_ug, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(MyProfileUg.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();


                if(role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                s.setMarkssem1ug(markssem1);
                s.setOutofsem1ug(outofsem1);
                s.setPercentagesem1ug(percentsem1);
                s.setMarkssem2ug(markssem2);
                s.setOutofsem2ug(outofsem2);
                s.setPercentagesem2ug(percentsem2);
                s.setMarkssem3ug(markssem3);
                s.setOutofsem3ug(outofsem3);
                s.setPercentagesem3ug(percentsem3);
                s.setMarkssem4ug(markssem4);
                s.setOutofsem4ug(outofsem4);
                s.setPercentagesem4ug(percentsem4);
                s.setMarkssem5ug(markssem5);
                s.setOutofsem5ug(outofsem5);
                s.setPercentagesem5ug(percentsem5);
                s.setMarkssem6ug(markssem6);
                s.setOutofsem6ug(outofsem6);
                s.setPercentagesem6ug(percentsem6);
                s.setMarkssem7ug(markssem7);
                s.setOutofsem7ug(outofsem7);
                s.setPercentagesem7ug(percentsem7);
                s.setMarkssem8ug(markssem8);
                s.setOutofsem8ug(outofsem8);
                s.setPercentagesem8ug(percentsem8);
                s.setAggregateug(aggregate);
                s.setCollegenameug(schoolname);
                s.setYearofpassingug(monthandyearofpassing);

                MyProfileUg.super.onBackPressed();
            }

        }
    }
    @Override
    public void onBackPressed() {

        if(!oldCourse.equals(selectedCourse)||!oldStream.equals(selectedStream)||!oldUniversity.equals(selectedUniversity)||edittedFlag==1)
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfileUg.super.onBackPressed();
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
        }
        else
            MyProfileUg.super.onBackPressed();
    }
}
