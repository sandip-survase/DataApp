package placeme.octopusites.com.placeme;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.Skills;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfileSkills extends AppCompatActivity {


    int skillcount = 0;
    View addmoreskill;
    View addmoreskillView;
    String username,role;
    String digest1, digest2;
    int editeskills=0;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    StudentData s = new StudentData();
    EditText skill1, skill2, skill3, skill4, skill5, skill6, skill7, skill8, skill9, skill10, skill11, skill12, skill13, skill14, skill15, skill16, skill17, skill18, skill19, skill20;
    TextInputLayout skillinput1,skillinput2,skillinput3,skillinput4,skillinput5,skillinput6,skillinput7,skillinput8,skillinput9,skillinput10,skillinput11,skillinput12,skillinput13,skillinput14,skillinput15,skillinput16,skillinput17,skillinput18,skillinput19,skillinput20;
    String sskill1 = "", sskill2 = "", sskill3 = "", sskill4 = "", sskill5 = "", sskill6 = "", sskill7 = "", sskill8 = "", sskill9 = "", sskill10 = "", sskill11 = "", sskill12 = "", sskill13 = "", sskill14 = "", sskill15 = "", sskill16 = "", sskill17 = "", sskill18 = "", sskill19 = "", sskill20 = "";
    String encskill1, encskill2, encskill3, encskill4, encskill5, encskill6, encskill7, encskill8, encskill9, encskill10, encskill11, encskill12, encskill13, encskill14, encskill15, encskill16, encskill17, encskill18, encskill19, encskill20;
    Spinner proficiency1, proficiency2, proficiency3, proficiency4, proficiency5, proficiency6, proficiency7, proficiency8, proficiency9, proficiency10, proficiency11, proficiency12, proficiency13, proficiency14, proficiency15, proficiency16, proficiency17, proficiency18, proficiency19, proficiency20;
    String sproficiency1 = "", sproficiency2 = "", sproficiency3 = "", sproficiency4 = "", sproficiency5 = "", sproficiency6 = "", sproficiency7 = "", sproficiency8 = "", sproficiency9 = "", sproficiency10 = "", sproficiency11 = "", sproficiency12 = "", sproficiency13 = "", sproficiency14 = "", sproficiency15 = "", sproficiency16 = "", sproficiency17 = "", sproficiency18 = "", sproficiency19 = "", sproficiency20 = "";
    String encproficiency1, encproficiency2, encproficiency3, encproficiency4, encproficiency5, encproficiency6, encproficiency7, encproficiency8, encproficiency9, encproficiency10, encproficiency11, encproficiency12, encproficiency13, encproficiency14, encproficiency15, encproficiency16, encproficiency17, encproficiency18, encproficiency19, encproficiency20;
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview, trash6selectionview, trash7selectionview, trash8selectionview, trash9selectionview, trash10selectionview, trash11selectionview, trash12selectionview, trash13selectionview, trash14selectionview, trash15selectionview, trash16selectionview, trash17selectionview, trash18selectionview, trash19selectionview, trash20selectionview;
    List<String> level = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;
    int edittedFlag = 0, proficiencyEdittedFlog = 0;
    int d = 0;

    ArrayList<Skills> skillsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_skills);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Skills");
        ab.setDisplayHomeAsUpEnabled(true);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        TextView skilltxt = (TextView) findViewById(R.id.skilltxt);
        skilltxt.setTypeface(Z.getBold(this));

        trash1selectionview = (View) findViewById(R.id.trash1selectionview);
        trash2selectionview = (View) findViewById(R.id.trash2selectionview);
        trash3selectionview = (View) findViewById(R.id.trash3selectionview);
        trash4selectionview = (View) findViewById(R.id.trash4selectionview);
        trash5selectionview = (View) findViewById(R.id.trash5selectionview);
        trash6selectionview = (View) findViewById(R.id.trash6selectionview);
        trash7selectionview = (View) findViewById(R.id.trash7selectionview);
        trash8selectionview = (View) findViewById(R.id.trash8selectionview);
        trash9selectionview = (View) findViewById(R.id.trash9selectionview);
        trash10selectionview = (View) findViewById(R.id.trash10selectionview);
        trash11selectionview = (View) findViewById(R.id.trash11selectionview);
        trash12selectionview = (View) findViewById(R.id.trash12selectionview);
        trash13selectionview = (View) findViewById(R.id.trash13selectionview);
        trash14selectionview = (View) findViewById(R.id.trash14selectionview);
        trash15selectionview = (View) findViewById(R.id.trash15selectionview);
        trash16selectionview = (View) findViewById(R.id.trash16selectionview);
        trash17selectionview = (View) findViewById(R.id.trash17selectionview);
        trash18selectionview = (View) findViewById(R.id.trash18selectionview);
        trash19selectionview = (View) findViewById(R.id.trash19selectionview);
        trash20selectionview = (View) findViewById(R.id.trash20selectionview);

        skillinput1= (TextInputLayout) findViewById(R.id.skillinput1);
        skillinput2= (TextInputLayout) findViewById(R.id.skillinput2);
        skillinput3= (TextInputLayout) findViewById(R.id.skillinput3);
        skillinput4= (TextInputLayout) findViewById(R.id.skillinput4);
        skillinput5= (TextInputLayout) findViewById(R.id.skillinput5);
        skillinput6= (TextInputLayout) findViewById(R.id.skillinput6);
        skillinput7= (TextInputLayout) findViewById(R.id.skillinput7);
        skillinput8= (TextInputLayout) findViewById(R.id.skillinput8);
        skillinput9= (TextInputLayout) findViewById(R.id.skillinput9);
        skillinput10= (TextInputLayout) findViewById(R.id.skillinput10);
        skillinput11= (TextInputLayout) findViewById(R.id.skillinput11);
        skillinput12= (TextInputLayout) findViewById(R.id.skillinput12);
        skillinput13= (TextInputLayout) findViewById(R.id.skillinput13);
        skillinput14= (TextInputLayout) findViewById(R.id.skillinput14);
        skillinput15= (TextInputLayout) findViewById(R.id.skillinput15);
        skillinput16= (TextInputLayout) findViewById(R.id.skillinput16);
        skillinput17= (TextInputLayout) findViewById(R.id.skillinput17);
        skillinput18= (TextInputLayout) findViewById(R.id.skillinput18);
        skillinput19= (TextInputLayout) findViewById(R.id.skillinput19);
        skillinput20= (TextInputLayout) findViewById(R.id.skillinput20);

        addmoreskillView= (View) findViewById(R.id.addmoreskill);
        skill1 = (EditText) findViewById(R.id.skill1);
        skill2 = (EditText) findViewById(R.id.skill2);
        skill3 = (EditText) findViewById(R.id.skill3);
        skill4 = (EditText) findViewById(R.id.skill4);
        skill5 = (EditText) findViewById(R.id.skill5);
        skill6 = (EditText) findViewById(R.id.skill6);
        skill7 = (EditText) findViewById(R.id.skill7);
        skill8 = (EditText) findViewById(R.id.skill8);
        skill9 = (EditText) findViewById(R.id.skill9);
        skill10 = (EditText) findViewById(R.id.skill10);
        skill11 = (EditText) findViewById(R.id.skill11);
        skill12 = (EditText) findViewById(R.id.skill12);
        skill13 = (EditText) findViewById(R.id.skill13);
        skill14 = (EditText) findViewById(R.id.skill14);
        skill15 = (EditText) findViewById(R.id.skill15);
        skill16 = (EditText) findViewById(R.id.skill16);
        skill17 = (EditText) findViewById(R.id.skill17);
        skill18 = (EditText) findViewById(R.id.skill18);
        skill19 = (EditText) findViewById(R.id.skill19);
        skill20 = (EditText) findViewById(R.id.skill20);

        proficiency1 = (Spinner) findViewById(R.id.proficiency1);
        proficiency2 = (Spinner) findViewById(R.id.proficiency2);
        proficiency3 = (Spinner) findViewById(R.id.proficiency3);
        proficiency4 = (Spinner) findViewById(R.id.proficiency4);
        proficiency5 = (Spinner) findViewById(R.id.proficiency5);
        proficiency6 = (Spinner) findViewById(R.id.proficiency6);
        proficiency7 = (Spinner) findViewById(R.id.proficiency7);
        proficiency8 = (Spinner) findViewById(R.id.proficiency8);
        proficiency9 = (Spinner) findViewById(R.id.proficiency9);
        proficiency10 = (Spinner) findViewById(R.id.proficiency10);
        proficiency11 = (Spinner) findViewById(R.id.proficiency11);
        proficiency12 = (Spinner) findViewById(R.id.proficiency12);
        proficiency13 = (Spinner) findViewById(R.id.proficiency13);
        proficiency14 = (Spinner) findViewById(R.id.proficiency14);
        proficiency15 = (Spinner) findViewById(R.id.proficiency15);
        proficiency16 = (Spinner) findViewById(R.id.proficiency16);
        proficiency17 = (Spinner) findViewById(R.id.proficiency17);
        proficiency18 = (Spinner) findViewById(R.id.proficiency18);
        proficiency19 = (Spinner) findViewById(R.id.proficiency19);
        proficiency20 = (Spinner) findViewById(R.id.proficiency20);


        skillinput1.setTypeface(Z.getLight(this));
        skillinput2.setTypeface(Z.getLight(this));
        skillinput3.setTypeface(Z.getLight(this));
        skillinput4.setTypeface(Z.getLight(this));
        skillinput5.setTypeface(Z.getLight(this));
        skillinput6.setTypeface(Z.getLight(this));
        skillinput7.setTypeface(Z.getLight(this));
        skillinput8.setTypeface(Z.getLight(this));
        skillinput9.setTypeface(Z.getLight(this));
        skillinput10.setTypeface(Z.getLight(this));
        skillinput11.setTypeface(Z.getLight(this));
        skillinput12.setTypeface(Z.getLight(this));
        skillinput13.setTypeface(Z.getLight(this));
        skillinput14.setTypeface(Z.getLight(this));
        skillinput15.setTypeface(Z.getLight(this));
        skillinput16.setTypeface(Z.getLight(this));
        skillinput17.setTypeface(Z.getLight(this));
        skillinput18.setTypeface(Z.getLight(this));
        skillinput19.setTypeface(Z.getLight(this));
        skillinput20.setTypeface(Z.getLight(this));

        skill1.setTypeface(Z.getBold(this));
        skill2.setTypeface(Z.getBold(this));
        skill3.setTypeface(Z.getBold(this));
        skill4.setTypeface(Z.getBold(this));
        skill5.setTypeface(Z.getBold(this));
        skill6.setTypeface(Z.getBold(this));
        skill7.setTypeface(Z.getBold(this));
        skill8.setTypeface(Z.getBold(this));
        skill9.setTypeface(Z.getBold(this));
        skill10.setTypeface(Z.getBold(this));
        skill11.setTypeface(Z.getBold(this));
        skill12.setTypeface(Z.getBold(this));
        skill13.setTypeface(Z.getBold(this));
        skill14.setTypeface(Z.getBold(this));
        skill15.setTypeface(Z.getBold(this));
        skill16.setTypeface(Z.getBold(this));
        skill17.setTypeface(Z.getBold(this));
        skill18.setTypeface(Z.getBold(this));
        skill19.setTypeface(Z.getBold(this));
        skill20.setTypeface(Z.getBold(this));

        final TextView addmoreskilltxt = (TextView) findViewById(R.id.addmoreskilltxt);
        addmoreskilltxt.setTypeface(Z.getBold(this));

        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 1;
                showDeletDialog();
            }
        });
        trash2selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 2;
                showDeletDialog();

            }
        });
        trash3selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 3;
                showDeletDialog();
            }
        });
        trash4selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 4;
                showDeletDialog();
            }
        });
        trash5selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 5;
                showDeletDialog();
            }
        });
        trash6selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 6;
                showDeletDialog();
            }
        });
        trash7selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 7;
                showDeletDialog();
            }
        });
        trash8selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 8;
                showDeletDialog();
            }
        });
        trash9selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 9;
                showDeletDialog();
            }
        });
        trash10selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 10;
                showDeletDialog();
            }
        });
        trash11selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 11;
                showDeletDialog();
            }
        });
        trash12selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 12;
                showDeletDialog();

            }
        });
        trash13selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 13;
                showDeletDialog();
            }
        });
        trash14selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 14;
                showDeletDialog();
            }
        });
        trash15selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 15;
                showDeletDialog();
            }
        });
        trash16selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 16;
                showDeletDialog();
            }
        });
        trash17selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 17;
                showDeletDialog();
            }
        });
        trash18selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 18;
                showDeletDialog();
            }
        });
        trash19selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 19;
                showDeletDialog();
            }
        });
        trash20selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 20;
                showDeletDialog();
            }
        });



        addmoreskill = (View) findViewById(R.id.addmoreskill);
        addmoreskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editeskills=0;
                Log.d("TAG", "onClick: skill count - "+skillcount);
                if (skillcount == 0) {

                    if(skill1.getText().toString()!=null)
                    {
                        if(!skill1.getText().toString().equals("")&&!proficiency1.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl2);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner2);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();


                } else if (skillcount == 1) {

                    if(skill2.getText().toString()!=null)
                    {
                        if(!skill2.getText().toString().equals("")&&!proficiency2.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline2);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl3);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner3);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();

                }
                else if (skillcount == 2) {


                    if(skill3.getText().toString()!=null)
                    {
                        if(!skill3.getText().toString().equals("")&&!proficiency3.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline3);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl4);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner4);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;

                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();


                } else if (skillcount == 3) {

                    if(skill4.getText().toString()!=null)
                    {
                        if(!skill4.getText().toString().equals("")&&!proficiency4.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl5);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner5);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();

                }
                else if (skillcount == 4) {
                    if(skill5.getText().toString()!=null)
                    {
                        if(!skill5.getText().toString().equals("")&&!proficiency5.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline5);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl6);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner6);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();



                } else if (skillcount == 5) {

                    if(skill6.getText().toString()!=null)
                    {
                        if(!skill6.getText().toString().equals("")&&!proficiency6.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline6);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl7);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner7);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();


                } else if (skillcount == 6) {
                    if(skill7.getText().toString()!=null)
                    {
                        if(!skill7.getText().toString().equals("")&&!proficiency7.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline7);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl8);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner8);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();


                } else if (skillcount == 7) {

                    if(skill8.getText().toString()!=null)
                    {
                        if(!skill8.getText().toString().equals("")&&!proficiency8.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline8);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl9);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner9);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();





                } else if (skillcount == 8) {

                    if(skill9.getText().toString()!=null)
                    {
                        if(!skill9.getText().toString().equals("")&&!proficiency9.getSelectedItem().toString().equals("- Proficiency -"))
                        {

                            View v = (View) findViewById(R.id.skillline9);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl10);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner10);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();





                } else if (skillcount == 9) {


                    if(skill10.getText().toString()!=null)
                    {
                        if(!skill10.getText().toString().equals("")&&!proficiency10.getSelectedItem().toString().equals("- Proficiency -"))
                        {

                            View v = (View) findViewById(R.id.skillline10);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl11);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner11);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();



                } else if (skillcount == 10) {

                    if(skill11.getText().toString()!=null)
                    {
                        if(!skill11.getText().toString().equals("")&&!proficiency11.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline11);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl12);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner12);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;

                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();




                } else if (skillcount == 11) {

                    if(skill12.getText().toString()!=null)
                    {
                        if(!skill12.getText().toString().equals("")&&!proficiency12.getSelectedItem().toString().equals("- Proficiency -"))
                        {

                            View v = (View) findViewById(R.id.skillline12);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl13);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner13);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();




                } else if (skillcount == 12) {

                    if(skill13.getText().toString()!=null)
                    {
                        if(!skill13.getText().toString().equals("")&&!proficiency13.getSelectedItem().toString().equals("- Proficiency -"))
                        {

                            View v = (View) findViewById(R.id.skillline13);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl14);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner14);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();




                } else if (skillcount == 13) {
                    if(skill14.getText().toString()!=null)
                    {
                        if(!skill14.getText().toString().equals("")&&!proficiency14.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline14);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl15);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner15);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;

                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();





                } else if (skillcount == 14) {

                    if(skill15.getText().toString()!=null)
                    {
                        if(!skill15.getText().toString().equals("")&&!proficiency15.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            View v = (View) findViewById(R.id.skillline15);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl16);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner16);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();




                } else if (skillcount == 15) {
                    if(skill16.getText().toString()!=null)
                    {
                        if(!skill16.getText().toString().equals("")&&!proficiency16.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            Log.d("TAG", "onClick: skill 17");
                            View v = (View) findViewById(R.id.skillline16);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl17);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner17);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;

                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();



                } else if (skillcount == 16) {

                    if(skill17.getText().toString()!=null)
                    {
                        if(!skill17.getText().toString().equals("")&&!proficiency17.getSelectedItem().toString().equals("- Proficiency -"))
                        {
                            Log.d("TAG", "onClick: skill 18");
                            View v = (View) findViewById(R.id.skillline17);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl18);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner18);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();



                } else if (skillcount == 17) {
                    if(skill18.getText().toString()!=null)
                    {
                        if(!skill18.getText().toString().equals("")&&!proficiency18.getSelectedItem().toString().equals("- Proficiency -"))
                        {

                            Log.d("TAG", "onClick: skill 19");
                            View v = (View) findViewById(R.id.skillline18);
                            v.setVisibility(View.VISIBLE);
                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl19);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner19);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                }
                else if (skillcount == 18) {

                    if(skill19.getText().toString()!=null)
                    {
                        if(!skill19.getText().toString().equals("")&&!proficiency19.getSelectedItem().toString().equals("- Proficiency -"))
                        {

                            Log.d("TAG", "onClick: skill 20");
                            View v = (View) findViewById(R.id.skillline19);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl20);
                            relativeLayout1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner20);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            skillcount++;

                            TextView t = (TextView) findViewById(R.id.addmoreskilltxt);
                            ImageView i = (ImageView) findViewById(R.id.addmoreskillimg);
                            addmoreskill.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);
                            addmoreskillView.setVisibility(View.GONE);
                        }
                        else
                        {
                            Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfileSkills.this, "Please fill the empty skill", Toast.LENGTH_SHORT).show();


                }

            }
        });

        ScrollView myprofileintroscrollview = (ScrollView) findViewById(R.id.myprofileskills);
        disableScrollbars(myprofileintroscrollview);


        level.add("- Proficiency -");
        level.add("Beginner");
        level.add("Intermediate");
        level.add("Expert");


        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, level) {
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
                tv.setTypeface(Z.getBold(MyProfileSkills.this));
                tv.setTextColor(getResources().getColor(R.color.dark_color));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(MyProfileSkills.this));
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.dark_color));
                }
                return view;
            }
        };
        
        proficiency1.setAdapter(dataAdapter);
        proficiency2.setAdapter(dataAdapter);
        proficiency3.setAdapter(dataAdapter);
        proficiency4.setAdapter(dataAdapter);
        proficiency5.setAdapter(dataAdapter);
        proficiency6.setAdapter(dataAdapter);
        proficiency7.setAdapter(dataAdapter);
        proficiency8.setAdapter(dataAdapter);
        proficiency9.setAdapter(dataAdapter);
        proficiency10.setAdapter(dataAdapter);
        proficiency11.setAdapter(dataAdapter);
        proficiency12.setAdapter(dataAdapter);
        proficiency13.setAdapter(dataAdapter);
        proficiency14.setAdapter(dataAdapter);
        proficiency15.setAdapter(dataAdapter);
        proficiency16.setAdapter(dataAdapter);
        proficiency17.setAdapter(dataAdapter);
        proficiency18.setAdapter(dataAdapter);
        proficiency19.setAdapter(dataAdapter);
        proficiency20.setAdapter(dataAdapter);

        sskill1 = s.getSkill1();
        sproficiency1 = s.getSproficiency1();
        sskill2 = s.getSkill2();
        sproficiency2 = s.getSproficiency2();
        sskill3 = s.getSkill3();
        sproficiency3 = s.getSproficiency3();
        sskill4 = s.getSkill4();
        sproficiency4 = s.getSproficiency4();
        sskill5 = s.getSkill5();
        sproficiency5 = s.getSproficiency5();
        sskill6 = s.getSkill6();
        sproficiency6 = s.getSproficiency6();
        sskill7 = s.getSkill7();
        sproficiency7 = s.getSproficiency7();
        sskill8 = s.getSkill8();
        sproficiency8 = s.getSproficiency8();
        sskill9 = s.getSkill9();
        sproficiency9 = s.getSproficiency9();
        sskill10 = s.getSkill10();
        sproficiency10 = s.getSproficiency10();
        sskill11 = s.getSkill11();
        sproficiency11 = s.getSproficiency11();
        sskill12 = s.getSkill12();
        sproficiency12 = s.getSproficiency12();
        sskill13 = s.getSkill13();
        sproficiency13 = s.getSproficiency13();
        sskill14 = s.getSkill14();
        sproficiency14 = s.getSproficiency14();
        sskill15 = s.getSkill15();
        sproficiency15 = s.getSproficiency15();
        sskill16 = s.getSkill16();
        sproficiency16 = s.getSproficiency16();
        sskill17 = s.getSkill17();
        sproficiency17 = s.getSproficiency17();
        sskill18 = s.getSkill18();
        sproficiency18 = s.getSproficiency18();
        sskill19 = s.getSkill19();
        sproficiency19 = s.getSproficiency19();
        sskill20 = s.getSkill20();
        sproficiency20 = s.getSproficiency20();

        skill1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput1.setError(null);
                edittedFlag = 1;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput2.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput3.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput4.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput5.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput6.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput7.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput8.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput9.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput10.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput11.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput12.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill13.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput13.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill14.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput14.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill15.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput15.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill16.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput16.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill17.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput17.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill18.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput18.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill19.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput19.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skill20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillinput20.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

// auto fill edit
        if (sskill1 != null) {
            if (sskill1.length()>=1) {
                skill1.setText(sskill1);
                proficiency1.setSelection(dataAdapter.getPosition(sproficiency1));
            }
        }
        if (sskill2 != null) {
            if (sskill2.length() >=1) {
                skill2.setText(sskill2);
                proficiency2.setSelection(dataAdapter.getPosition(sproficiency2));
                View v = (View) findViewById(R.id.skillline1);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl2);
                relativeLayout1.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner2);
                relativeLayout2.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill3 != null) {
            if (sskill3.length() >=1) {
                skill3.setText(sskill3);
                proficiency3.setSelection(dataAdapter.getPosition(sproficiency3));
                View v = (View) findViewById(R.id.skillline2);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.skillrl3);
                relativeLayout2.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout3 = (RelativeLayout) findViewById(R.id.proficiencyspinner3);
                relativeLayout3.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill4 != null) {
            if (sskill4.length() >= 1) {
                skill4.setText(sskill4);
                proficiency4.setSelection(dataAdapter.getPosition(sproficiency4));

                View v = (View) findViewById(R.id.skillline3);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout3 = (RelativeLayout) findViewById(R.id.skillrl4);
                relativeLayout3.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout4 = (RelativeLayout) findViewById(R.id.proficiencyspinner4);
                relativeLayout4.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill5 != null) {
            if (sskill5.length() >= 1) {
                skill5.setText(sskill5);
                proficiency5.setSelection(dataAdapter.getPosition(sproficiency5));

                View v = (View) findViewById(R.id.skillline4);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout4 = (RelativeLayout) findViewById(R.id.skillrl5);
                relativeLayout4.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout5 = (RelativeLayout) findViewById(R.id.proficiencyspinner5);
                relativeLayout5.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill6 != null) {
            if (sskill6.length() >= 1) {
                skill6.setText(sskill6);
                proficiency6.setSelection(dataAdapter.getPosition(sproficiency6));
                View v = (View) findViewById(R.id.skillline5);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout5 = (RelativeLayout) findViewById(R.id.skillrl6);
                relativeLayout5.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout6 = (RelativeLayout) findViewById(R.id.proficiencyspinner6);
                relativeLayout6.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill7 != null) {
            if (sskill7.length() >= 1) {
                skill7.setText(sskill7);
                proficiency7.setSelection(dataAdapter.getPosition(sproficiency7));
                View v = (View) findViewById(R.id.skillline6);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout6 = (RelativeLayout) findViewById(R.id.skillrl7);
                relativeLayout6.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout7 = (RelativeLayout) findViewById(R.id.proficiencyspinner7);
                relativeLayout7.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill8 != null) {
            if (sskill8.length() >= 1) {
                skill8.setText(sskill8);
                proficiency8.setSelection(dataAdapter.getPosition(sproficiency8));
                View v = (View) findViewById(R.id.skillline7);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout7 = (RelativeLayout) findViewById(R.id.skillrl8);
                relativeLayout7.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout8 = (RelativeLayout) findViewById(R.id.proficiencyspinner8);
                relativeLayout8.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill9 != null) {
            if (sskill9.length() >= 1) {
                skill9.setText(sskill9);
                proficiency9.setSelection(dataAdapter.getPosition(sproficiency9));
                View v = (View) findViewById(R.id.skillline8);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout8 = (RelativeLayout) findViewById(R.id.skillrl9);
                relativeLayout8.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout9 = (RelativeLayout) findViewById(R.id.proficiencyspinner9);
                relativeLayout9.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill10 != null) {
            if (sskill10.length() >= 1) {
                skill10.setText(sskill10);
                proficiency10.setSelection(dataAdapter.getPosition(sproficiency10));
                View v = (View) findViewById(R.id.skillline9);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout9 = (RelativeLayout) findViewById(R.id.skillrl10);
                relativeLayout9.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout10 = (RelativeLayout) findViewById(R.id.proficiencyspinner10);
                relativeLayout10.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill11 != null) {
            if (sskill11.length() >= 1) {
                skill11.setText(sskill11);
                proficiency11.setSelection(dataAdapter.getPosition(sproficiency11));
                View v = (View) findViewById(R.id.skillline10);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout10 = (RelativeLayout) findViewById(R.id.skillrl11);
                relativeLayout10.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout11 = (RelativeLayout) findViewById(R.id.proficiencyspinner11);
                relativeLayout11.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill12 != null) {
            if (sskill12.length() >= 1) {
                skill12.setText(sskill12);
                proficiency12.setSelection(dataAdapter.getPosition(sproficiency12));
                View v = (View) findViewById(R.id.skillline11);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout11 = (RelativeLayout) findViewById(R.id.skillrl12);
                relativeLayout11.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout12 = (RelativeLayout) findViewById(R.id.proficiencyspinner12);
                relativeLayout12.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill13 != null) {
            if (sskill13.length() >= 1) {
                skill13.setText(sskill13);
                proficiency13.setSelection(dataAdapter.getPosition(sproficiency13));
                View v = (View) findViewById(R.id.skillline12);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout12 = (RelativeLayout) findViewById(R.id.skillrl13);
                relativeLayout12.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout13 = (RelativeLayout) findViewById(R.id.proficiencyspinner13);
                relativeLayout13.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill14 != null) {
            if (sskill14.length() >= 1) {
                skill14.setText(sskill14);
                proficiency14.setSelection(dataAdapter.getPosition(sproficiency14));
                View v = (View) findViewById(R.id.skillline13);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout13 = (RelativeLayout) findViewById(R.id.skillrl14);
                relativeLayout13.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout14 = (RelativeLayout) findViewById(R.id.proficiencyspinner14);
                relativeLayout14.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill15 != null) {
            if (sskill15.length() >= 1) {
                skill15.setText(sskill15);
                proficiency15.setSelection(dataAdapter.getPosition(sproficiency15));
                View v = (View) findViewById(R.id.skillline14);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout14 = (RelativeLayout) findViewById(R.id.skillrl15);
                relativeLayout14.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout15 = (RelativeLayout) findViewById(R.id.proficiencyspinner15);
                relativeLayout15.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill16 != null) {
            if (sskill16.length() >= 1) {
                skill16.setText(sskill16);
                proficiency16.setSelection(dataAdapter.getPosition(sproficiency16));
                View v = (View) findViewById(R.id.skillline15);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout15 = (RelativeLayout) findViewById(R.id.skillrl16);
                relativeLayout15.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout16 = (RelativeLayout) findViewById(R.id.proficiencyspinner16);
                relativeLayout16.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill17 != null) {
            if (sskill17.length() >= 1) {
                skill17.setText(sskill17);
                proficiency17.setSelection(dataAdapter.getPosition(sproficiency17));
                View v = (View) findViewById(R.id.skillline16);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout16 = (RelativeLayout) findViewById(R.id.skillrl17);
                relativeLayout16.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout17 = (RelativeLayout) findViewById(R.id.proficiencyspinner17);
                relativeLayout17.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill18 != null) {
            if (sskill18.length() >= 1) {
                skill18.setText(sskill18);
                proficiency18.setSelection(dataAdapter.getPosition(sproficiency18));
                View v = (View) findViewById(R.id.skillline17);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout17 = (RelativeLayout) findViewById(R.id.skillrl18);
                relativeLayout17.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout18 = (RelativeLayout) findViewById(R.id.proficiencyspinner18);
                relativeLayout18.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill19 != null) {
            if (sskill19.length() >= 1) {
                skill19.setText(sskill19);
                proficiency19.setSelection(dataAdapter.getPosition(sproficiency19));
                View v = (View) findViewById(R.id.skillline18);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout18 = (RelativeLayout) findViewById(R.id.skillrl19);
                relativeLayout18.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout19 = (RelativeLayout) findViewById(R.id.proficiencyspinner19);
                relativeLayout19.setVisibility(View.VISIBLE);
                skillcount++;

            }
        }
        if (sskill20 != null) {
            if (sskill20.length() >= 1) {
                skill20.setText(sskill20);
                proficiency20.setSelection(dataAdapter.getPosition(sproficiency20));
                View v = (View) findViewById(R.id.skillline19);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout19 = (RelativeLayout) findViewById(R.id.skillrl20);
                relativeLayout19.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout20 = (RelativeLayout) findViewById(R.id.proficiencyspinner20);
                relativeLayout20.setVisibility(View.VISIBLE);
                skillcount++;

                TextView t = (TextView) findViewById(R.id.addmoreskilltxt);
                ImageView i = (ImageView) findViewById(R.id.addmoreskillimg);

                addmoreskill.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                i.setVisibility(View.GONE);

            }
        }

        edittedFlag = 0;

// spinner listener
        proficiencyEdittedFlog = 0;


        proficiency1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency1 = (String) parent.getItemAtPosition(position);

                Log.d("TAG", "onItemSelected: " + sproficiency1);

                if (s.getSproficiency1()!=null && !s.getSproficiency1().equals(sproficiency1)) {
                    proficiencyEdittedFlog = 1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency2 = (String) parent.getItemAtPosition(position);

                if (s.getSproficiency2()!=null && !s.getSproficiency2().equals(sproficiency2)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency3 = (String) parent.getItemAtPosition(position);

                if (s.getSproficiency3()!=null && !s.getSproficiency3().equals(sproficiency3)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency4 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency4()!=null && !s.getSproficiency4().equals(sproficiency4)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency5 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency5()!=null && !s.getSproficiency5().equals(sproficiency5)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency6 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency6()!=null && !s.getSproficiency6().equals(sproficiency6)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency7 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency7()!=null && !s.getSproficiency7().equals(sproficiency7)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency8 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency8()!=null && !s.getSproficiency8().equals(sproficiency8)) {
                    proficiencyEdittedFlog = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency9 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency9()!=null && !s.getSproficiency9().equals(sproficiency9)) {
                    proficiencyEdittedFlog = 1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency10 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency10()!=null && !s.getSproficiency10().equals(sproficiency10)) {
                    proficiencyEdittedFlog = 1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency11 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency11()!=null && !s.getSproficiency11().equals(sproficiency11)) {
                    proficiencyEdittedFlog = 1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency12 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency12()!=null && !s.getSproficiency12().equals(sproficiency12)) {
                    proficiencyEdittedFlog = 1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency13 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency13()!=null && !s.getSproficiency13().equals(sproficiency13)) {
                    proficiencyEdittedFlog = 1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency14 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency14()!=null && !s.getSproficiency14().equals(sproficiency14)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency15 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency15()!=null && !s.getSproficiency15().equals(sproficiency15)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency16.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency16 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency16()!=null && !s.getSproficiency16().equals(sproficiency16)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency17 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency17()!=null && !s.getSproficiency17().equals(sproficiency17)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency18 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency18()!=null && !s.getSproficiency18().equals(sproficiency18)) {
                    proficiencyEdittedFlog = 1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency19.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency19 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency19()!=null && !s.getSproficiency19().equals(sproficiency19)) {
                    proficiencyEdittedFlog = 1;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        proficiency20.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency20 = (String) parent.getItemAtPosition(position);
                if (s.getSproficiency20()!=null && !s.getSproficiency20().equals(sproficiency20)) {
                    proficiencyEdittedFlog = 1;
                    addmoreskillView.setVisibility(View.GONE);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // endOncrete



    void showDeletDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this Skill ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag = 1;
                                deleteSkill();
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
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
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileSkills.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileSkills.this));

            }
        });

        alertDialog.show();

    }

    void deleteSkill() {
        View v = (View) findViewById(R.id.skillline19);

        if (v.getVisibility() == View.VISIBLE) {
            Log.d("TAG", "deleteSkill: skillline19");

            View v1 = (View) findViewById(R.id.skillline19);
            v1.setVisibility(View.GONE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl20);
            relativeLayout1.setVisibility(View.GONE);

            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner20);
            relativeLayout2.setVisibility(View.GONE);
            skillcount--;

            TextView t = (TextView) findViewById(R.id.addmoreskilltxt);
            ImageView i = (ImageView) findViewById(R.id.addmoreskillimg);
            addmoreskill.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);

        } else {

            v = (View) findViewById(R.id.skillline18);
            if (v.getVisibility() == View.VISIBLE) {

                Log.d("TAG", "deleteSkill: skillline18");

                View v1 = (View) findViewById(R.id.skillline18);
                v1.setVisibility(View.GONE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl19);
                relativeLayout1.setVisibility(View.GONE);

                RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner19);
                relativeLayout2.setVisibility(View.GONE);

                skillcount--;

            } else {
                v = (View) findViewById(R.id.skillline17);
                if (v.getVisibility() == View.VISIBLE) {

                    Log.d("TAG", "deleteSkill: skillline17");

                    View v1 = (View) findViewById(R.id.skillline17);
                    v1.setVisibility(View.GONE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl18);
                    relativeLayout1.setVisibility(View.GONE);

                    RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner18);
                    relativeLayout2.setVisibility(View.GONE);

                    skillcount--;

                } else {
                    v = (View) findViewById(R.id.skillline16);
                    if (v.getVisibility() == View.VISIBLE) {

                        Log.d("TAG", "deleteSkill: skillline16");

                        View v1 = (View) findViewById(R.id.skillline16);
                        v1.setVisibility(View.GONE);

                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl17);
                        relativeLayout1.setVisibility(View.GONE);

                        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner17);
                        relativeLayout2.setVisibility(View.GONE);

                        skillcount--;

                    } else {
                        v = (View) findViewById(R.id.skillline15);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) findViewById(R.id.skillline15);
                            v1.setVisibility(View.GONE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl16);
                            relativeLayout1.setVisibility(View.GONE);

                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner16);
                            relativeLayout2.setVisibility(View.GONE);

                            skillcount--;

                        } else {
                            v = (View) findViewById(R.id.skillline14);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) findViewById(R.id.skillline14);
                                v1.setVisibility(View.GONE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl15);
                                relativeLayout1.setVisibility(View.GONE);

                                RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner15);
                                relativeLayout2.setVisibility(View.GONE);

                                skillcount--;

                            } else {
                                v = (View) findViewById(R.id.skillline13);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) findViewById(R.id.skillline13);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl14);
                                    relativeLayout1.setVisibility(View.GONE);

                                    RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner14);
                                    relativeLayout2.setVisibility(View.GONE);

                                    skillcount--;

                                } else {
                                    v = (View) findViewById(R.id.skillline12);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) findViewById(R.id.skillline12);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl13);
                                        relativeLayout1.setVisibility(View.GONE);

                                        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner13);
                                        relativeLayout2.setVisibility(View.GONE);

                                        skillcount--;

                                    } else {
                                        v = (View) findViewById(R.id.skillline11);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1 = (View) findViewById(R.id.skillline11);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl12);
                                            relativeLayout1.setVisibility(View.GONE);

                                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner12);
                                            relativeLayout2.setVisibility(View.GONE);

                                            skillcount--;

                                        } else {
                                            v = (View) findViewById(R.id.skillline10);
                                            if (v.getVisibility() == View.VISIBLE) {

                                                View v1 = (View) findViewById(R.id.skillline10);
                                                v1.setVisibility(View.GONE);

                                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl11);
                                                relativeLayout1.setVisibility(View.GONE);

                                                RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner11);
                                                relativeLayout2.setVisibility(View.GONE);

                                                skillcount--;

                                            } else {
                                                v = (View) findViewById(R.id.skillline9);
                                                if (v.getVisibility() == View.VISIBLE) {

                                                    View v1 = (View) findViewById(R.id.skillline9);
                                                    v1.setVisibility(View.GONE);

                                                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl10);
                                                    relativeLayout1.setVisibility(View.GONE);

                                                    RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner10);
                                                    relativeLayout2.setVisibility(View.GONE);

                                                    skillcount--;

                                                } else {
                                                    v = (View) findViewById(R.id.skillline8);
                                                    if (v.getVisibility() == View.VISIBLE) {

                                                        View v1 = (View) findViewById(R.id.skillline8);
                                                        v1.setVisibility(View.GONE);

                                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl9);
                                                        relativeLayout1.setVisibility(View.GONE);

                                                        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner9);
                                                        relativeLayout2.setVisibility(View.GONE);

                                                        skillcount--;

                                                    } else {
                                                        v = (View) findViewById(R.id.skillline7);
                                                        if (v.getVisibility() == View.VISIBLE) {

                                                            View v1 = (View) findViewById(R.id.skillline7);
                                                            v1.setVisibility(View.GONE);

                                                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl8);
                                                            relativeLayout1.setVisibility(View.GONE);

                                                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner8);
                                                            relativeLayout2.setVisibility(View.GONE);

                                                            skillcount--;

                                                        } else {
                                                            v = (View) findViewById(R.id.skillline6);
                                                            if (v.getVisibility() == View.VISIBLE) {

                                                                View v1 = (View) findViewById(R.id.skillline6);
                                                                v1.setVisibility(View.GONE);

                                                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl7);
                                                                relativeLayout1.setVisibility(View.GONE);

                                                                RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner7);
                                                                relativeLayout2.setVisibility(View.GONE);

                                                                skillcount--;

                                                            } else {
                                                                v = (View) findViewById(R.id.skillline5);
                                                                if (v.getVisibility() == View.VISIBLE) {

                                                                    View v1 = (View) findViewById(R.id.skillline5);
                                                                    v1.setVisibility(View.GONE);

                                                                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl6);
                                                                    relativeLayout1.setVisibility(View.GONE);

                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner6);
                                                                    relativeLayout2.setVisibility(View.GONE);

                                                                    skillcount--;

                                                                } else {
                                                                    v = (View) findViewById(R.id.skillline4);
                                                                    if (v.getVisibility() == View.VISIBLE) {

                                                                        View v1 = (View) findViewById(R.id.skillline4);
                                                                        v1.setVisibility(View.GONE);

                                                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl5);
                                                                        relativeLayout1.setVisibility(View.GONE);

                                                                        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner5);
                                                                        relativeLayout2.setVisibility(View.GONE);

                                                                        skillcount--;

                                                                    } else {
                                                                        v = (View) findViewById(R.id.skillline3);
                                                                        if (v.getVisibility() == View.VISIBLE) {

                                                                            View v1 = (View) findViewById(R.id.skillline3);
                                                                            v1.setVisibility(View.GONE);

                                                                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl4);
                                                                            relativeLayout1.setVisibility(View.GONE);

                                                                            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner4);
                                                                            relativeLayout2.setVisibility(View.GONE);

                                                                            skillcount--;

                                                                        } else {
                                                                            v = (View) findViewById(R.id.skillline2);
                                                                            if (v.getVisibility() == View.VISIBLE) {

                                                                                View v1 = (View) findViewById(R.id.skillline2);
                                                                                v1.setVisibility(View.GONE);

                                                                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl3);
                                                                                relativeLayout1.setVisibility(View.GONE);

                                                                                RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner3);
                                                                                relativeLayout2.setVisibility(View.GONE);

                                                                                skillcount--;

                                                                            } else {
                                                                                v = (View) findViewById(R.id.skillline1);
                                                                                if (v.getVisibility() == View.VISIBLE) {

                                                                                    View v1 = (View) findViewById(R.id.skillline1);
                                                                                    v1.setVisibility(View.GONE);

                                                                                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.skillrl2);
                                                                                    relativeLayout1.setVisibility(View.GONE);

                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.proficiencyspinner2);
                                                                                    relativeLayout2.setVisibility(View.GONE);

                                                                                    skillcount--;

                                                                                } else {
                                                                                    skill1.setText("");
                                                                                    proficiency1.setSelection(0);
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
        if (d == 20) {
            skill20.setText("");
            proficiency20.setSelection(0);

        } else if (d == 19) {

            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));


        } else if (d == 18) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();


            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));


            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 17) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();


            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));


            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));


            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 16) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();


            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));


            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));


            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 15) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();


            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));


            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 14) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();


            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 13) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();


            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 12) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();


            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 11) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));
            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 10) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();
            sskill11 = skill11.getText().toString();
            sproficiency11 = proficiency11.getSelectedItem().toString();


            sskill10 = sskill11;
            sproficiency10 = sproficiency11;

            skill11.setText("");
            proficiency11.setSelection(0);

            skill10.setText(sskill10);
            proficiency10.setSelection(dataAdapter.getPosition(sproficiency11));

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));

            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 9) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();
            sskill11 = skill11.getText().toString();
            sproficiency11 = proficiency11.getSelectedItem().toString();
            sskill10 = skill10.getText().toString();
            sproficiency10 = proficiency10.getSelectedItem().toString();


            sskill9 = sskill10;
            sproficiency9 = sproficiency10;

            skill10.setText("");
            proficiency10.setSelection(0);

            skill9.setText(sskill9);
            proficiency9.setSelection(dataAdapter.getPosition(sproficiency10));

            sskill10 = sskill11;
            sproficiency10 = sproficiency11;

            skill11.setText("");
            proficiency11.setSelection(0);

            skill10.setText(sskill10);
            proficiency10.setSelection(dataAdapter.getPosition(sproficiency11));

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));

            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));
            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 8) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();
            sskill11 = skill11.getText().toString();
            sproficiency11 = proficiency11.getSelectedItem().toString();
            sskill10 = skill10.getText().toString();
            sproficiency10 = proficiency10.getSelectedItem().toString();
            sskill9 = skill9.getText().toString();
            sproficiency9 = proficiency9.getSelectedItem().toString();


            sskill8 = sskill9;
            sproficiency8 = sproficiency9;

            skill9.setText("");
            proficiency9.setSelection(0);

            skill8.setText(sskill8);
            proficiency8.setSelection(dataAdapter.getPosition(sproficiency9));

            sskill9 = sskill10;
            sproficiency9 = sproficiency10;

            skill10.setText("");
            proficiency10.setSelection(0);

            skill9.setText(sskill9);
            proficiency9.setSelection(dataAdapter.getPosition(sproficiency10));

            sskill10 = sskill11;
            sproficiency10 = sproficiency11;

            skill11.setText("");
            proficiency11.setSelection(0);

            skill10.setText(sskill10);
            proficiency10.setSelection(dataAdapter.getPosition(sproficiency11));

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));

            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));
            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 7) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();
            sskill11 = skill11.getText().toString();
            sproficiency11 = proficiency11.getSelectedItem().toString();
            sskill10 = skill10.getText().toString();
            sproficiency10 = proficiency10.getSelectedItem().toString();
            sskill9 = skill9.getText().toString();
            sproficiency9 = proficiency9.getSelectedItem().toString();
            sskill8 = skill8.getText().toString();
            sproficiency8 = proficiency8.getSelectedItem().toString();


            sskill7 = sskill8;
            sproficiency7 = sproficiency8;

            skill8.setText("");
            proficiency8.setSelection(0);

            skill7.setText(sskill7);
            proficiency7.setSelection(dataAdapter.getPosition(sproficiency8));

            sskill8 = sskill9;
            sproficiency8 = sproficiency9;

            skill9.setText("");
            proficiency9.setSelection(0);

            skill8.setText(sskill8);
            proficiency8.setSelection(dataAdapter.getPosition(sproficiency9));

            sskill9 = sskill10;
            sproficiency9 = sproficiency10;

            skill10.setText("");
            proficiency10.setSelection(0);

            skill9.setText(sskill9);
            proficiency9.setSelection(dataAdapter.getPosition(sproficiency10));
            sskill10 = sskill11;
            sproficiency10 = sproficiency11;

            skill11.setText("");
            proficiency11.setSelection(0);

            skill10.setText(sskill10);
            proficiency10.setSelection(dataAdapter.getPosition(sproficiency11));

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));

            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));
            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 6) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();
            sskill11 = skill11.getText().toString();
            sproficiency11 = proficiency11.getSelectedItem().toString();
            sskill10 = skill10.getText().toString();
            sproficiency10 = proficiency10.getSelectedItem().toString();
            sskill9 = skill9.getText().toString();
            sproficiency9 = proficiency9.getSelectedItem().toString();
            sskill8 = skill8.getText().toString();
            sproficiency8 = proficiency8.getSelectedItem().toString();
            sskill7 = skill7.getText().toString();
            sproficiency7 = proficiency7.getSelectedItem().toString();


            sskill6 = sskill7;
            sproficiency6 = sproficiency7;

            skill7.setText("");
            proficiency7.setSelection(0);

            skill6.setText(sskill6);
            proficiency6.setSelection(dataAdapter.getPosition(sproficiency7));

            sskill7 = sskill8;
            sproficiency7 = sproficiency8;

            skill8.setText("");
            proficiency8.setSelection(0);

            skill7.setText(sskill7);
            proficiency7.setSelection(dataAdapter.getPosition(sproficiency8));

            sskill8 = sskill9;
            sproficiency8 = sproficiency9;

            skill9.setText("");
            proficiency9.setSelection(0);

            skill8.setText(sskill8);
            proficiency8.setSelection(dataAdapter.getPosition(sproficiency9));

            sskill9 = sskill10;
            sproficiency9 = sproficiency10;

            skill10.setText("");
            proficiency10.setSelection(0);

            skill9.setText(sskill9);
            proficiency9.setSelection(dataAdapter.getPosition(sproficiency10));

            sskill10 = sskill11;
            sproficiency10 = sproficiency11;

            skill11.setText("");
            proficiency11.setSelection(0);

            skill10.setText(sskill10);
            proficiency10.setSelection(dataAdapter.getPosition(sproficiency11));

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));

            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 5) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();
            sskill11 = skill11.getText().toString();
            sproficiency11 = proficiency11.getSelectedItem().toString();
            sskill10 = skill10.getText().toString();
            sproficiency10 = proficiency10.getSelectedItem().toString();
            sskill9 = skill9.getText().toString();
            sproficiency9 = proficiency9.getSelectedItem().toString();
            sskill8 = skill8.getText().toString();
            sproficiency8 = proficiency8.getSelectedItem().toString();
            sskill7 = skill7.getText().toString();
            sproficiency7 = proficiency7.getSelectedItem().toString();
            sskill6 = skill6.getText().toString();
            sproficiency6 = proficiency6.getSelectedItem().toString();


            sskill5 = sskill6;
            sproficiency5 = sproficiency6;

            skill6.setText("");
            proficiency6.setSelection(0);

            skill5.setText(sskill5);
            proficiency5.setSelection(dataAdapter.getPosition(sproficiency6));

            sskill6 = sskill7;
            sproficiency6 = sproficiency7;

            skill7.setText("");
            proficiency7.setSelection(0);

            skill6.setText(sskill6);
            proficiency6.setSelection(dataAdapter.getPosition(sproficiency7));

            sskill7 = sskill8;
            sproficiency7 = sproficiency8;

            skill8.setText("");
            proficiency8.setSelection(0);

            skill7.setText(sskill7);
            proficiency7.setSelection(dataAdapter.getPosition(sproficiency8));

            sskill8 = sskill9;
            sproficiency8 = sproficiency9;

            skill9.setText("");
            proficiency9.setSelection(0);

            skill8.setText(sskill8);
            proficiency8.setSelection(dataAdapter.getPosition(sproficiency9));

            sskill9 = sskill10;
            sproficiency9 = sproficiency10;

            skill10.setText("");
            proficiency10.setSelection(0);

            skill9.setText(sskill9);
            proficiency9.setSelection(dataAdapter.getPosition(sproficiency10));

            sskill10 = sskill11;
            sproficiency10 = sproficiency11;

            skill11.setText("");
            proficiency11.setSelection(0);

            skill10.setText(sskill10);
            proficiency10.setSelection(dataAdapter.getPosition(sproficiency11));

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));

            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 4) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();
            sskill11 = skill11.getText().toString();
            sproficiency11 = proficiency11.getSelectedItem().toString();
            sskill10 = skill10.getText().toString();
            sproficiency10 = proficiency10.getSelectedItem().toString();
            sskill9 = skill9.getText().toString();
            sproficiency9 = proficiency9.getSelectedItem().toString();
            sskill8 = skill8.getText().toString();
            sproficiency8 = proficiency8.getSelectedItem().toString();
            sskill7 = skill7.getText().toString();
            sproficiency7 = proficiency7.getSelectedItem().toString();
            sskill6 = skill6.getText().toString();
            sproficiency6 = proficiency6.getSelectedItem().toString();
            sskill5 = skill5.getText().toString();
            sproficiency5 = proficiency5.getSelectedItem().toString();


            sskill4 = sskill5;
            sproficiency4 = sproficiency5;

            skill5.setText("");
            proficiency5.setSelection(0);

            skill4.setText(sskill4);
            proficiency4.setSelection(dataAdapter.getPosition(sproficiency5));

            sskill5 = sskill6;
            sproficiency5 = sproficiency6;

            skill6.setText("");
            proficiency6.setSelection(0);

            skill5.setText(sskill5);
            proficiency5.setSelection(dataAdapter.getPosition(sproficiency6));

            sskill6 = sskill7;
            sproficiency6 = sproficiency7;

            skill7.setText("");
            proficiency7.setSelection(0);

            skill6.setText(sskill6);
            proficiency6.setSelection(dataAdapter.getPosition(sproficiency7));

            sskill7 = sskill8;
            sproficiency7 = sproficiency8;

            skill8.setText("");
            proficiency8.setSelection(0);

            skill7.setText(sskill7);
            proficiency7.setSelection(dataAdapter.getPosition(sproficiency8));

            sskill8 = sskill9;
            sproficiency8 = sproficiency9;

            skill9.setText("");
            proficiency9.setSelection(0);

            skill8.setText(sskill8);
            proficiency8.setSelection(dataAdapter.getPosition(sproficiency9));

            sskill9 = sskill10;
            sproficiency9 = sproficiency10;

            skill10.setText("");
            proficiency10.setSelection(0);

            skill9.setText(sskill9);
            proficiency9.setSelection(dataAdapter.getPosition(sproficiency10));

            sskill10 = sskill11;
            sproficiency10 = sproficiency11;

            skill11.setText("");
            proficiency11.setSelection(0);

            skill10.setText(sskill10);
            proficiency10.setSelection(dataAdapter.getPosition(sproficiency11));

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));

            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        } else if (d == 3) {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();
            sskill11 = skill11.getText().toString();
            sproficiency11 = proficiency11.getSelectedItem().toString();
            sskill10 = skill10.getText().toString();
            sproficiency10 = proficiency10.getSelectedItem().toString();
            sskill9 = skill9.getText().toString();
            sproficiency9 = proficiency9.getSelectedItem().toString();
            sskill8 = skill8.getText().toString();
            sproficiency8 = proficiency8.getSelectedItem().toString();
            sskill7 = skill7.getText().toString();
            sproficiency7 = proficiency7.getSelectedItem().toString();
            sskill6 = skill6.getText().toString();
            sproficiency6 = proficiency6.getSelectedItem().toString();
            sskill5 = skill5.getText().toString();
            sproficiency5 = proficiency5.getSelectedItem().toString();
            sskill4 = skill4.getText().toString();
            sproficiency4 = proficiency4.getSelectedItem().toString();

            sskill3 = sskill4;
            sproficiency3 = sproficiency4;

            skill4.setText("");
            proficiency4.setSelection(0);

            skill3.setText(sskill3);
            proficiency3.setSelection(dataAdapter.getPosition(sproficiency4));

            sskill4 = sskill5;
            sproficiency4 = sproficiency5;

            skill5.setText("");
            proficiency5.setSelection(0);

            skill4.setText(sskill4);
            proficiency4.setSelection(dataAdapter.getPosition(sproficiency5));


            sskill5 = sskill6;
            sproficiency5 = sproficiency6;

            skill6.setText("");
            proficiency6.setSelection(0);

            skill5.setText(sskill5);
            proficiency5.setSelection(dataAdapter.getPosition(sproficiency6));

            sskill6 = sskill7;
            sproficiency6 = sproficiency7;

            skill7.setText("");
            proficiency7.setSelection(0);

            skill6.setText(sskill6);
            proficiency6.setSelection(dataAdapter.getPosition(sproficiency7));

            sskill7 = sskill8;
            sproficiency7 = sproficiency8;

            skill8.setText("");
            proficiency8.setSelection(0);

            skill7.setText(sskill7);
            proficiency7.setSelection(dataAdapter.getPosition(sproficiency8));

            sskill8 = sskill9;
            sproficiency8 = sproficiency9;

            skill9.setText("");
            proficiency9.setSelection(0);

            skill8.setText(sskill8);
            proficiency8.setSelection(dataAdapter.getPosition(sproficiency9));

            sskill9 = sskill10;
            sproficiency9 = sproficiency10;

            skill10.setText("");
            proficiency10.setSelection(0);

            skill9.setText(sskill9);
            proficiency9.setSelection(dataAdapter.getPosition(sproficiency10));

            sskill10 = sskill11;
            sproficiency10 = sproficiency11;

            skill11.setText("");
            proficiency11.setSelection(0);

            skill10.setText(sskill10);
            proficiency10.setSelection(dataAdapter.getPosition(sproficiency11));

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));

            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        }
        else if(d==2)

        {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();
            sskill11 = skill11.getText().toString();
            sproficiency11 = proficiency11.getSelectedItem().toString();
            sskill10 = skill10.getText().toString();
            sproficiency10 = proficiency10.getSelectedItem().toString();
            sskill9 = skill9.getText().toString();
            sproficiency9 = proficiency9.getSelectedItem().toString();
            sskill8 = skill8.getText().toString();
            sproficiency8 = proficiency8.getSelectedItem().toString();
            sskill7 = skill7.getText().toString();
            sproficiency7 = proficiency7.getSelectedItem().toString();
            sskill6 = skill6.getText().toString();
            sproficiency6 = proficiency6.getSelectedItem().toString();
            sskill5 = skill5.getText().toString();
            sproficiency5 = proficiency5.getSelectedItem().toString();
            sskill4 = skill4.getText().toString();
            sproficiency4 = proficiency4.getSelectedItem().toString();
            sskill3 = skill3.getText().toString();
            sproficiency3 = proficiency3.getSelectedItem().toString();


            sskill2 = sskill3;
            sproficiency2 = sproficiency3;

            skill3.setText("");
            proficiency3.setSelection(0);

            skill2.setText(sskill2);
            proficiency2.setSelection(dataAdapter.getPosition(sproficiency3));

            sskill3 = sskill4;
            sproficiency3 = sproficiency4;

            skill4.setText("");
            proficiency4.setSelection(0);

            skill3.setText(sskill3);
            proficiency3.setSelection(dataAdapter.getPosition(sproficiency4));

            sskill4 = sskill5;
            sproficiency4 = sproficiency5;

            skill5.setText("");
            proficiency5.setSelection(0);

            skill4.setText(sskill4);
            proficiency4.setSelection(dataAdapter.getPosition(sproficiency5));

            sskill5 = sskill6;
            sproficiency5 = sproficiency6;

            skill6.setText("");
            proficiency6.setSelection(0);

            skill5.setText(sskill5);
            proficiency5.setSelection(dataAdapter.getPosition(sproficiency6));

            sskill6 = sskill7;
            sproficiency6 = sproficiency7;

            skill7.setText("");
            proficiency7.setSelection(0);

            skill6.setText(sskill6);
            proficiency6.setSelection(dataAdapter.getPosition(sproficiency7));

            sskill7 = sskill8;
            sproficiency7 = sproficiency8;

            skill8.setText("");
            proficiency8.setSelection(0);

            skill7.setText(sskill7);
            proficiency7.setSelection(dataAdapter.getPosition(sproficiency8));

            sskill8 = sskill9;
            sproficiency8 = sproficiency9;

            skill9.setText("");
            proficiency9.setSelection(0);

            skill8.setText(sskill8);
            proficiency8.setSelection(dataAdapter.getPosition(sproficiency9));

            sskill9 = sskill10;
            sproficiency9 = sproficiency10;

            skill10.setText("");
            proficiency10.setSelection(0);

            skill9.setText(sskill9);
            proficiency9.setSelection(dataAdapter.getPosition(sproficiency10));

            sskill10 = sskill11;
            sproficiency10 = sproficiency11;

            skill11.setText("");
            proficiency11.setSelection(0);

            skill10.setText(sskill10);
            proficiency10.setSelection(dataAdapter.getPosition(sproficiency11));

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));

            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));

        }


        else if(d==1)

        {
            sskill20 = skill20.getText().toString();
            sproficiency20 = proficiency20.getSelectedItem().toString();
            sskill19 = skill19.getText().toString();
            sproficiency19 = proficiency19.getSelectedItem().toString();
            sskill18 = skill18.getText().toString();
            sproficiency18 = proficiency18.getSelectedItem().toString();
            sskill17 = skill17.getText().toString();
            sproficiency17 = proficiency17.getSelectedItem().toString();
            sskill16 = skill16.getText().toString();
            sproficiency16 = proficiency16.getSelectedItem().toString();
            sskill15 = skill15.getText().toString();
            sproficiency15 = proficiency15.getSelectedItem().toString();
            sskill14 = skill14.getText().toString();
            sproficiency14 = proficiency14.getSelectedItem().toString();
            sskill13 = skill13.getText().toString();
            sproficiency13 = proficiency13.getSelectedItem().toString();
            sskill12 = skill12.getText().toString();
            sproficiency12 = proficiency12.getSelectedItem().toString();
            sskill11 = skill11.getText().toString();
            sproficiency11 = proficiency11.getSelectedItem().toString();
            sskill10 = skill10.getText().toString();
            sproficiency10 = proficiency10.getSelectedItem().toString();
            sskill9 = skill9.getText().toString();
            sproficiency9 = proficiency9.getSelectedItem().toString();
            sskill8 = skill8.getText().toString();
            sproficiency8 = proficiency8.getSelectedItem().toString();
            sskill7 = skill7.getText().toString();
            sproficiency7 = proficiency7.getSelectedItem().toString();
            sskill6 = skill6.getText().toString();
            sproficiency6 = proficiency6.getSelectedItem().toString();
            sskill5 = skill5.getText().toString();
            sproficiency5 = proficiency5.getSelectedItem().toString();
            sskill4 = skill4.getText().toString();
            sproficiency4 = proficiency4.getSelectedItem().toString();
            sskill3 = skill3.getText().toString();
            sproficiency3 = proficiency3.getSelectedItem().toString();
            sskill2 = skill2.getText().toString();
            sproficiency2 = proficiency2.getSelectedItem().toString();


            sskill1 = sskill2;
            sproficiency1 = sproficiency2;

            skill2.setText("");
            proficiency2.setSelection(0);

            skill1.setText(sskill1);
            proficiency1.setSelection(dataAdapter.getPosition(sproficiency2));

            sskill2 = sskill3;
            sproficiency2 = sproficiency3;

            skill3.setText("");
            proficiency3.setSelection(0);

            skill2.setText(sskill2);
            proficiency2.setSelection(dataAdapter.getPosition(sproficiency3));

            sskill3 = sskill4;
            sproficiency3 = sproficiency4;

            skill4.setText("");
            proficiency4.setSelection(0);

            skill3.setText(sskill3);
            proficiency3.setSelection(dataAdapter.getPosition(sproficiency4));

            sskill4 = sskill5;
            sproficiency4 = sproficiency5;

            skill5.setText("");
            proficiency5.setSelection(0);

            skill4.setText(sskill4);
            proficiency4.setSelection(dataAdapter.getPosition(sproficiency5));

            sskill5 = sskill6;
            sproficiency5 = sproficiency6;

            skill6.setText("");
            proficiency6.setSelection(0);

            skill5.setText(sskill5);
            proficiency5.setSelection(dataAdapter.getPosition(sproficiency6));

            sskill6 = sskill7;
            sproficiency6 = sproficiency7;

            skill7.setText("");
            proficiency7.setSelection(0);

            skill6.setText(sskill6);
            proficiency6.setSelection(dataAdapter.getPosition(sproficiency7));

            sskill7 = sskill8;
            sproficiency7 = sproficiency8;

            skill8.setText("");
            proficiency8.setSelection(0);

            skill7.setText(sskill7);
            proficiency7.setSelection(dataAdapter.getPosition(sproficiency8));

            sskill8 = sskill9;
            sproficiency8 = sproficiency9;

            skill9.setText("");
            proficiency9.setSelection(0);

            skill8.setText(sskill8);
            proficiency8.setSelection(dataAdapter.getPosition(sproficiency9));

            sskill9 = sskill10;
            sproficiency9 = sproficiency10;

            skill10.setText("");
            proficiency10.setSelection(0);

            skill9.setText(sskill9);
            proficiency9.setSelection(dataAdapter.getPosition(sproficiency10));

            sskill10 = sskill11;
            sproficiency10 = sproficiency11;

            skill11.setText("");
            proficiency11.setSelection(0);

            skill10.setText(sskill10);
            proficiency10.setSelection(dataAdapter.getPosition(sproficiency11));

            sskill11 = sskill12;
            sproficiency11 = sproficiency12;

            skill12.setText("");
            proficiency12.setSelection(0);

            skill11.setText(sskill11);
            proficiency11.setSelection(dataAdapter.getPosition(sproficiency12));

            sskill12 = sskill13;
            sproficiency12 = sproficiency13;

            skill13.setText("");
            proficiency13.setSelection(0);

            skill12.setText(sskill12);
            proficiency12.setSelection(dataAdapter.getPosition(sproficiency13));

            sskill13 = sskill14;
            sproficiency13 = sproficiency14;

            skill14.setText("");
            proficiency14.setSelection(0);

            skill13.setText(sskill13);
            proficiency13.setSelection(dataAdapter.getPosition(sproficiency14));

            sskill14 = sskill15;
            sproficiency14 = sproficiency15;

            skill15.setText("");
            proficiency15.setSelection(0);

            skill14.setText(sskill14);
            proficiency14.setSelection(dataAdapter.getPosition(sproficiency15));

            sskill15 = sskill16;
            sproficiency15 = sproficiency16;

            skill16.setText("");
            proficiency16.setSelection(0);

            skill15.setText(sskill15);
            proficiency15.setSelection(dataAdapter.getPosition(sproficiency16));

            sskill16 = sskill17;
            sproficiency16 = sproficiency17;

            skill17.setText("");
            proficiency17.setSelection(0);

            skill16.setText(sskill16);
            proficiency16.setSelection(dataAdapter.getPosition(sproficiency17));

            sskill17 = sskill18;
            sproficiency17 = sproficiency18;

            skill18.setText("");
            proficiency18.setSelection(0);

            skill17.setText(sskill17);
            proficiency17.setSelection(dataAdapter.getPosition(sproficiency18));

            sskill18 = sskill19;
            sproficiency18 = sproficiency19;

            skill19.setText("");
            proficiency19.setSelection(0);

            skill18.setText(sskill18);
            proficiency18.setSelection(dataAdapter.getPosition(sproficiency19));

            sskill19 = sskill20;
            sproficiency19 = sproficiency20;

            skill20.setText("");
            proficiency20.setSelection(0);

            skill19.setText(sskill19);
            proficiency19.setSelection(dataAdapter.getPosition(sproficiency20));


//            **************

            sproficiency1=proficiency1.getSelectedItem().toString();
            if(sskill1.equals("")  )
            {
                Log.d("TAG", "deleteLang: lang 1");
                editeskills =1;
            }

            if(editeskills==1){
                Log.d("TAG", "deleteLang: editlang - "+editeskills);
                encskills();
            }


//          *********************




        }

    }

    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }

    void validateandSave() {
//        skill1.setError(null);
//        skill2.setError(null);
//        skill3.setError(null);
//        skill4.setError(null);
//        skill5.setError(null);
//        skill6.setError(null);
//        skill7.setError(null);
//        skill8.setError(null);
//        skill9.setError(null);
//        skill10.setError(null);
//        skill11.setError(null);
//        skill12.setError(null);
//        skill13.setError(null);
//        skill14.setError(null);
//        skill15.setError(null);
//        skill16.setError(null);
//        skill17.setError(null);
//        skill18.setError(null);
//        skill19.setError(null);
//        skill20.setError(null);

        sskill1 = skill1.getText().toString();
        sskill2 = skill2.getText().toString();
        sskill3 = skill3.getText().toString();
        sskill4 = skill4.getText().toString();
        sskill5 = skill5.getText().toString();
        sskill6 = skill6.getText().toString();
        sskill7 = skill7.getText().toString();
        sskill8 = skill8.getText().toString();
        sskill9 = skill9.getText().toString();
        sskill10 = skill10.getText().toString();
        sskill11 = skill11.getText().toString();
        sskill12 = skill12.getText().toString();
        sskill13 = skill13.getText().toString();
        sskill14 = skill14.getText().toString();
        sskill15 = skill15.getText().toString();
        sskill16 = skill16.getText().toString();
        sskill17 = skill17.getText().toString();
        sskill18 = skill18.getText().toString();
        sskill19 = skill19.getText().toString();
        sskill20 = skill20.getText().toString();
// getting data from Spinner
        sproficiency1 = proficiency1.getSelectedItem().toString();
        sproficiency2 = proficiency2.getSelectedItem().toString();
        sproficiency3 = proficiency3.getSelectedItem().toString();
        sproficiency4 = proficiency4.getSelectedItem().toString();
        sproficiency5 = proficiency5.getSelectedItem().toString();
        sproficiency6 = proficiency6.getSelectedItem().toString();
        sproficiency7 = proficiency7.getSelectedItem().toString();
        sproficiency8 = proficiency8.getSelectedItem().toString();
        sproficiency9 = proficiency9.getSelectedItem().toString();
        sproficiency10 = proficiency10.getSelectedItem().toString();
        sproficiency11 = proficiency11.getSelectedItem().toString();
        sproficiency12 = proficiency12.getSelectedItem().toString();
        sproficiency13 = proficiency13.getSelectedItem().toString();
        sproficiency14 = proficiency14.getSelectedItem().toString();
        sproficiency15 = proficiency15.getSelectedItem().toString();
        sproficiency16 = proficiency16.getSelectedItem().toString();
        sproficiency17 = proficiency17.getSelectedItem().toString();
        sproficiency18 = proficiency18.getSelectedItem().toString();
        sproficiency19 = proficiency19.getSelectedItem().toString();
        sproficiency20 = proficiency20.getSelectedItem().toString();

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag = 0;

        if(editeskills==1){
            encskills();
        }
        else {

            if (skillcount == 0) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skillinput1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    }
                }
            } else if (skillcount == 1) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skillinput1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            } else if (skillcount == 2) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skillinput1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (skillcount == 3) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skillinput1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (skillcount == 4) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skillinput1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 5) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 6) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 7) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 8) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 9) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 10) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sskill11.length() < 1) {
                                                                                                    errorflag = 1;
                                                                                                    skillinput11.setError("Invalid Skill");

                                                                                                } else {
                                                                                                    if (sproficiency11.equals("- Proficiency -")) {
                                                                                                        errorflag = 1;
                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 11) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sskill11.length() < 1) {
                                                                                                    errorflag = 1;
                                                                                                    skillinput11.setError("Invalid Skill");

                                                                                                } else {
                                                                                                    if (sproficiency11.equals("- Proficiency -")) {
                                                                                                        errorflag = 1;
                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sskill12.length() < 1) {
                                                                                                            errorflag = 1;
                                                                                                            skillinput12.setError("Invalid Skill");

                                                                                                        } else {
                                                                                                            if (sproficiency12.equals("- Proficiency -")) {
                                                                                                                errorflag = 1;
                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 12) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sskill11.length() < 1) {
                                                                                                    errorflag = 1;
                                                                                                    skillinput11.setError("Invalid Skill");

                                                                                                } else {
                                                                                                    if (sproficiency11.equals("- Proficiency -")) {
                                                                                                        errorflag = 1;
                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sskill12.length() < 1) {
                                                                                                            errorflag = 1;
                                                                                                            skillinput12.setError("Invalid Skill");

                                                                                                        } else {
                                                                                                            if (sproficiency12.equals("- Proficiency -")) {
                                                                                                                errorflag = 1;
                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sskill13.length() < 1) {
                                                                                                                    errorflag = 1;
                                                                                                                    skillinput13.setError("Invalid Skill");

                                                                                                                } else {
                                                                                                                    if (sproficiency13.equals("- Proficiency -")) {
                                                                                                                        errorflag = 1;
                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 13) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sskill11.length() < 1) {
                                                                                                    errorflag = 1;
                                                                                                    skillinput11.setError("Invalid Skill");

                                                                                                } else {
                                                                                                    if (sproficiency11.equals("- Proficiency -")) {
                                                                                                        errorflag = 1;
                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sskill12.length() < 1) {
                                                                                                            errorflag = 1;
                                                                                                            skillinput12.setError("Invalid Skill");

                                                                                                        } else {
                                                                                                            if (sproficiency12.equals("- Proficiency -")) {
                                                                                                                errorflag = 1;
                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sskill13.length() < 1) {
                                                                                                                    errorflag = 1;
                                                                                                                    skillinput13.setError("Invalid Skill");

                                                                                                                } else {
                                                                                                                    if (sproficiency13.equals("- Proficiency -")) {
                                                                                                                        errorflag = 1;
                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sskill14.length() < 1) {
                                                                                                                            errorflag = 1;
                                                                                                                            skillinput14.setError("Invalid Skill");

                                                                                                                        } else {
                                                                                                                            if (sproficiency14.equals("- Proficiency -")) {
                                                                                                                                errorflag = 1;
                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 14) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sskill11.length() < 1) {
                                                                                                    errorflag = 1;
                                                                                                    skillinput11.setError("Invalid Skill");

                                                                                                } else {
                                                                                                    if (sproficiency11.equals("- Proficiency -")) {
                                                                                                        errorflag = 1;
                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sskill12.length() < 1) {
                                                                                                            errorflag = 1;
                                                                                                            skillinput12.setError("Invalid Skill");

                                                                                                        } else {
                                                                                                            if (sproficiency12.equals("- Proficiency -")) {
                                                                                                                errorflag = 1;
                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sskill13.length() < 1) {
                                                                                                                    errorflag = 1;
                                                                                                                    skillinput13.setError("Invalid Skill");

                                                                                                                } else {
                                                                                                                    if (sproficiency13.equals("- Proficiency -")) {
                                                                                                                        errorflag = 1;
                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sskill14.length() < 1) {
                                                                                                                            errorflag = 1;
                                                                                                                            skillinput14.setError("Invalid Skill");

                                                                                                                        } else {
                                                                                                                            if (sproficiency14.equals("- Proficiency -")) {
                                                                                                                                errorflag = 1;
                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sskill15.length() < 1) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    skillinput15.setError("Invalid Skill");

                                                                                                                                } else {
                                                                                                                                    if (sproficiency15.equals("- Proficiency -")) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
                    }
                }
            } else if (skillcount == 15) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sskill11.length() < 1) {
                                                                                                    errorflag = 1;
                                                                                                    skillinput11.setError("Invalid Skill");

                                                                                                } else {
                                                                                                    if (sproficiency11.equals("- Proficiency -")) {
                                                                                                        errorflag = 1;
                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sskill12.length() < 1) {
                                                                                                            errorflag = 1;
                                                                                                            skillinput12.setError("Invalid Skill");

                                                                                                        } else {
                                                                                                            if (sproficiency12.equals("- Proficiency -")) {
                                                                                                                errorflag = 1;
                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sskill13.length() < 1) {
                                                                                                                    errorflag = 1;
                                                                                                                    skillinput13.setError("Invalid Skill");

                                                                                                                } else {
                                                                                                                    if (sproficiency13.equals("- Proficiency -")) {
                                                                                                                        errorflag = 1;
                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sskill14.length() < 1) {
                                                                                                                            errorflag = 1;
                                                                                                                            skillinput14.setError("Invalid Skill");

                                                                                                                        } else {
                                                                                                                            if (sproficiency14.equals("- Proficiency -")) {
                                                                                                                                errorflag = 1;
                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sskill15.length() < 1) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    skillinput15.setError("Invalid Skill");

                                                                                                                                } else {
                                                                                                                                    if (sproficiency15.equals("- Proficiency -")) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (sskill16.length() < 1) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            skillinput16.setError("Invalid Skill");

                                                                                                                                        } else {
                                                                                                                                            if (sproficiency16.equals("- Proficiency -")) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
                            }
                        }
                    }
                }
            } else if (skillcount == 16) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sskill11.length() < 1) {
                                                                                                    errorflag = 1;
                                                                                                    skillinput11.setError("Invalid Skill");

                                                                                                } else {
                                                                                                    if (sproficiency11.equals("- Proficiency -")) {
                                                                                                        errorflag = 1;
                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sskill12.length() < 1) {
                                                                                                            errorflag = 1;
                                                                                                            skillinput12.setError("Invalid Skill");

                                                                                                        } else {
                                                                                                            if (sproficiency12.equals("- Proficiency -")) {
                                                                                                                errorflag = 1;
                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sskill13.length() < 1) {
                                                                                                                    errorflag = 1;
                                                                                                                    skillinput13.setError("Invalid Skill");

                                                                                                                } else {
                                                                                                                    if (sproficiency13.equals("- Proficiency -")) {
                                                                                                                        errorflag = 1;
                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sskill14.length() < 1) {
                                                                                                                            errorflag = 1;
                                                                                                                            skillinput14.setError("Invalid Skill");

                                                                                                                        } else {
                                                                                                                            if (sproficiency14.equals("- Proficiency -")) {
                                                                                                                                errorflag = 1;
                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sskill15.length() < 1) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    skillinput15.setError("Invalid Skill");

                                                                                                                                } else {
                                                                                                                                    if (sproficiency15.equals("- Proficiency -")) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (sskill16.length() < 1) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            skillinput16.setError("Invalid Skill");

                                                                                                                                        } else {
                                                                                                                                            if (sproficiency16.equals("- Proficiency -")) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                            } else {
                                                                                                                                                if (sskill17.length() < 1) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    skillinput17.setError("Invalid Skill");

                                                                                                                                                } else {
                                                                                                                                                    if (sproficiency17.equals("- Proficiency -")) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (skillcount == 17) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sskill11.length() < 1) {
                                                                                                    errorflag = 1;
                                                                                                    skillinput11.setError("Invalid Skill");

                                                                                                } else {
                                                                                                    if (sproficiency11.equals("- Proficiency -")) {
                                                                                                        errorflag = 1;
                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sskill12.length() < 1) {
                                                                                                            errorflag = 1;
                                                                                                            skillinput12.setError("Invalid Skill");

                                                                                                        } else {
                                                                                                            if (sproficiency12.equals("- Proficiency -")) {
                                                                                                                errorflag = 1;
                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sskill13.length() < 1) {
                                                                                                                    errorflag = 1;
                                                                                                                    skillinput13.setError("Invalid Skill");

                                                                                                                } else {
                                                                                                                    if (sproficiency13.equals("- Proficiency -")) {
                                                                                                                        errorflag = 1;
                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sskill14.length() < 1) {
                                                                                                                            errorflag = 1;
                                                                                                                            skillinput14.setError("Invalid Skill");

                                                                                                                        } else {
                                                                                                                            if (sproficiency14.equals("- Proficiency -")) {
                                                                                                                                errorflag = 1;
                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sskill15.length() < 1) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    skillinput15.setError("Invalid Skill");

                                                                                                                                } else {
                                                                                                                                    if (sproficiency15.equals("- Proficiency -")) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (sskill16.length() < 1) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            skillinput16.setError("Invalid Skill");

                                                                                                                                        } else {
                                                                                                                                            if (sproficiency16.equals("- Proficiency -")) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                            } else {
                                                                                                                                                if (sskill17.length() < 1) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    skillinput17.setError("Invalid Skill");

                                                                                                                                                } else {
                                                                                                                                                    if (sproficiency17.equals("- Proficiency -")) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (sskill18.length() < 1) {
                                                                                                                                                            errorflag = 1;
                                                                                                                                                            skillinput18.setError("Invalid Skill");

                                                                                                                                                        } else {
                                                                                                                                                            if (sproficiency18.equals("- Proficiency -")) {
                                                                                                                                                                errorflag = 1;
                                                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (skillcount == 18) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sskill11.length() < 1) {
                                                                                                    errorflag = 1;
                                                                                                    skillinput11.setError("Invalid Skill");

                                                                                                } else {
                                                                                                    if (sproficiency11.equals("- Proficiency -")) {
                                                                                                        errorflag = 1;
                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sskill12.length() < 1) {
                                                                                                            errorflag = 1;
                                                                                                            skillinput12.setError("Invalid Skill");

                                                                                                        } else {
                                                                                                            if (sproficiency12.equals("- Proficiency -")) {
                                                                                                                errorflag = 1;
                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sskill13.length() < 1) {
                                                                                                                    errorflag = 1;
                                                                                                                    skillinput13.setError("Invalid Skill");

                                                                                                                } else {
                                                                                                                    if (sproficiency13.equals("- Proficiency -")) {
                                                                                                                        errorflag = 1;
                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sskill14.length() < 1) {
                                                                                                                            errorflag = 1;
                                                                                                                            skillinput14.setError("Invalid Skill");

                                                                                                                        } else {
                                                                                                                            if (sproficiency14.equals("- Proficiency -")) {
                                                                                                                                errorflag = 1;
                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sskill15.length() < 1) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    skillinput15.setError("Invalid Skill");

                                                                                                                                } else {
                                                                                                                                    if (sproficiency15.equals("- Proficiency -")) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (sskill16.length() < 1) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            skillinput16.setError("Invalid Skill");

                                                                                                                                        } else {
                                                                                                                                            if (sproficiency16.equals("- Proficiency -")) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                            } else {
                                                                                                                                                if (sskill17.length() < 1) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    skillinput17.setError("Invalid Skill");

                                                                                                                                                } else {
                                                                                                                                                    if (sproficiency17.equals("- Proficiency -")) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (sskill18.length() < 1) {
                                                                                                                                                            errorflag = 1;
                                                                                                                                                            skillinput18.setError("Invalid Skill");

                                                                                                                                                        } else {
                                                                                                                                                            if (sproficiency18.equals("- Proficiency -")) {
                                                                                                                                                                errorflag = 1;
                                                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                                            } else {
                                                                                                                                                                errorflag = 0;
                                                                                                                                                                if (sskill19.length() < 1) {
                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                    skillinput19.setError("Invalid Skill");

                                                                                                                                                                } else {
                                                                                                                                                                    if (sproficiency19.equals("- Proficiency -")) {
                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
            } else if (skillcount == 19) {
                if (sskill1.length() < 1) {
                    errorflag = 1;
                    skill1.setError("Invalid Skill");

                } else {
                    if (sproficiency1.equals("- Proficiency -")) {
                        errorflag = 1;
                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                    } else {
                        errorflag = 0;
                        if (sskill2.length() < 1) {
                            errorflag = 1;
                            skillinput2.setError("Invalid Skill");

                        } else {
                            if (sproficiency2.equals("- Proficiency -")) {
                                errorflag = 1;
                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                            } else {
                                errorflag = 0;
                                if (sskill3.length() < 1) {
                                    errorflag = 1;
                                    skillinput3.setError("Invalid Skill");

                                } else {
                                    if (sproficiency3.equals("- Proficiency -")) {
                                        errorflag = 1;
                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                    } else {
                                        errorflag = 0;
                                        if (sskill4.length() < 1) {
                                            errorflag = 1;
                                            skillinput4.setError("Invalid Skill");

                                        } else {
                                            if (sproficiency4.equals("- Proficiency -")) {
                                                errorflag = 1;
                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (sskill5.length() < 1) {
                                                    errorflag = 1;
                                                    skillinput5.setError("Invalid Skill");

                                                } else {
                                                    if (sproficiency5.equals("- Proficiency -")) {
                                                        errorflag = 1;
                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        errorflag = 0;
                                                        if (sskill6.length() < 1) {
                                                            errorflag = 1;
                                                            skillinput6.setError("Invalid Skill");

                                                        } else {
                                                            if (sproficiency6.equals("- Proficiency -")) {
                                                                errorflag = 1;
                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                errorflag = 0;
                                                                if (sskill7.length() < 1) {
                                                                    errorflag = 1;
                                                                    skillinput7.setError("Invalid Skill");

                                                                } else {
                                                                    if (sproficiency7.equals("- Proficiency -")) {
                                                                        errorflag = 1;
                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (sskill8.length() < 1) {
                                                                            errorflag = 1;
                                                                            skillinput8.setError("Invalid Skill");

                                                                        } else {
                                                                            if (sproficiency8.equals("- Proficiency -")) {
                                                                                errorflag = 1;
                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sskill9.length() < 1) {
                                                                                    errorflag = 1;
                                                                                    skillinput9.setError("Invalid Skill");

                                                                                } else {
                                                                                    if (sproficiency9.equals("- Proficiency -")) {
                                                                                        errorflag = 1;
                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (sskill10.length() < 1) {
                                                                                            errorflag = 1;
                                                                                            skillinput10.setError("Invalid Skill");

                                                                                        } else {
                                                                                            if (sproficiency10.equals("- Proficiency -")) {
                                                                                                errorflag = 1;
                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sskill11.length() < 1) {
                                                                                                    errorflag = 1;
                                                                                                    skillinput11.setError("Invalid Skill");

                                                                                                } else {
                                                                                                    if (sproficiency11.equals("- Proficiency -")) {
                                                                                                        errorflag = 1;
                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sskill12.length() < 1) {
                                                                                                            errorflag = 1;
                                                                                                            skillinput12.setError("Invalid Skill");

                                                                                                        } else {
                                                                                                            if (sproficiency12.equals("- Proficiency -")) {
                                                                                                                errorflag = 1;
                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sskill13.length() < 1) {
                                                                                                                    errorflag = 1;
                                                                                                                    skillinput13.setError("Invalid Skill");

                                                                                                                } else {
                                                                                                                    if (sproficiency13.equals("- Proficiency -")) {
                                                                                                                        errorflag = 1;
                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (sskill14.length() < 1) {
                                                                                                                            errorflag = 1;
                                                                                                                            skillinput14.setError("Invalid Skill");

                                                                                                                        } else {
                                                                                                                            if (sproficiency14.equals("- Proficiency -")) {
                                                                                                                                errorflag = 1;
                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sskill15.length() < 1) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    skillinput15.setError("Invalid Skill");

                                                                                                                                } else {
                                                                                                                                    if (sproficiency15.equals("- Proficiency -")) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (sskill16.length() < 1) {
                                                                                                                                            errorflag = 1;
                                                                                                                                            skillinput16.setError("Invalid Skill");

                                                                                                                                        } else {
                                                                                                                                            if (sproficiency16.equals("- Proficiency -")) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                            } else {
                                                                                                                                                if (sskill17.length() < 1) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    skillinput17.setError("Invalid Skill");

                                                                                                                                                } else {
                                                                                                                                                    if (sproficiency17.equals("- Proficiency -")) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (sskill18.length() < 1) {
                                                                                                                                                            errorflag = 1;
                                                                                                                                                            skillinput18.setError("Invalid Skill");

                                                                                                                                                        } else {
                                                                                                                                                            if (sproficiency18.equals("- Proficiency -")) {
                                                                                                                                                                errorflag = 1;
                                                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                                            } else {
                                                                                                                                                                errorflag = 0;
                                                                                                                                                                if (sskill19.length() < 1) {
                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                    skillinput19.setError("Invalid Skill");

                                                                                                                                                                } else {
                                                                                                                                                                    if (sproficiency19.equals("- Proficiency -")) {
                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                        Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
                                                                                                                                                                    } else {
                                                                                                                                                                        errorflag = 0;
                                                                                                                                                                        if (sskill20.length() < 1) {
                                                                                                                                                                            errorflag = 1;
                                                                                                                                                                            skillinput20.setError("Invalid Skill");

                                                                                                                                                                        } else {
                                                                                                                                                                            if (sproficiency20.equals("- Proficiency -")) {
                                                                                                                                                                                errorflag = 1;
                                                                                                                                                                                Toast.makeText(MyProfileSkills.this, "Please Select Proficiency", Toast.LENGTH_LONG).show();
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
        if (errorflag == 0) {
            encskills();
        }

    }

    public void encskills(){

        try {

            Skills obj1=new Skills(sskill1,sproficiency1);
            Skills obj2=new Skills(sskill2,sproficiency2);
            Skills obj3=new Skills(sskill3,sproficiency3);
            Skills obj4=new Skills(sskill4,sproficiency4);
            Skills obj5=new Skills(sskill5,sproficiency5);
            Skills obj6=new Skills(sskill6,sproficiency6);
            Skills obj7=new Skills(sskill7,sproficiency7);
            Skills obj8=new Skills(sskill8,sproficiency8);
            Skills obj9=new Skills(sskill9,sproficiency9);
            Skills obj10=new Skills(sskill10,sproficiency10);
            Skills obj11=new Skills(sskill11,sproficiency11);
            Skills obj12=new Skills(sskill12,sproficiency12);
            Skills obj13=new Skills(sskill13,sproficiency13);
            Skills obj14=new Skills(sskill14,sproficiency14);
            Skills obj15=new Skills(sskill15,sproficiency15);
            Skills obj16=new Skills(sskill16,sproficiency16);
            Skills obj17=new Skills(sskill17,sproficiency17);
            Skills obj18=new Skills(sskill18,sproficiency18);
            Skills obj19=new Skills(sskill19,sproficiency19);
            Skills obj20=new Skills(sskill20,sproficiency20);

            skillsList.add(obj1);
            skillsList.add(obj2);
            skillsList.add(obj3);
            skillsList.add(obj4);
            skillsList.add(obj5);
            skillsList.add(obj6);
            skillsList.add(obj7);
            skillsList.add(obj8);
            skillsList.add(obj9);
            skillsList.add(obj10);
            skillsList.add(obj11);
            skillsList.add(obj12);
            skillsList.add(obj13);
            skillsList.add(obj14);
            skillsList.add(obj15);
            skillsList.add(obj16);
            skillsList.add(obj17);
            skillsList.add(obj18);
            skillsList.add(obj19);
            skillsList.add(obj20);

            String encObjString=OtoString(skillsList,MySharedPreferencesManager.getDigest1(MyProfileSkills.this),MySharedPreferencesManager.getDigest2(MyProfileSkills.this));


            new SaveSkills().execute(encObjString);
        } catch (Exception e) {
            Toast.makeText(MyProfileSkills.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    class SaveSkills extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));          //0
            params.add(new BasicNameValuePair("d", param[0]));          //0


            json = jParser.makeHttpRequest(Z.url_saveskills, "GET", params);
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
                Toast.makeText(MyProfileSkills.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();

                if(role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("hr"))
                    setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("admin"))
                    setResult(AdminActivity.ADMIN_DATA_CHANGE_RESULT_CODE);


                s.setSkill1(sskill1);
                s.setSproficiency1(sproficiency1);
                s.setSkill2(sskill2);
                s.setSproficiency2(sproficiency2);
                s.setSkill3(sskill3);
                s.setSproficiency3(sproficiency3);
                s.setSkill4(sskill4);
                s.setSproficiency4(sproficiency4);
                s.setSkill5(sskill5);
                s.setSproficiency5(sproficiency5);
                s.setSkill6(sskill6);
                s.setSproficiency6(sproficiency6);
                s.setSkill7(sskill7);
                s.setSproficiency7(sproficiency7);
                s.setSkill8(sskill8);
                s.setSproficiency8(sproficiency8);
                s.setSkill9(sskill9);
                s.setSproficiency9(sproficiency9);
                s.setSkill10(sskill10);
                s.setSproficiency10(sproficiency10);
                s.setSkill11(sskill11);
                s.setSproficiency11(sproficiency11);
                s.setSkill12(sskill12);
                s.setSproficiency12(sproficiency12);
                s.setSkill13(sskill13);
                s.setSkill14(sskill14);
                s.setSproficiency14(sproficiency14);
                s.setSkill15(sskill15);
                s.setSproficiency15(sproficiency15);
                s.setSkill16(sskill16);
                s.setSproficiency16(sproficiency16);
                s.setSkill17(sskill17);
                s.setSproficiency17(sproficiency17);
                s.setSkill18(sskill18);
                s.setSproficiency18(sproficiency18);
                s.setSkill19(sskill19);
                s.setSproficiency19(sproficiency19);
                s.setSkill20(sskill20);
                s.setSproficiency20(sproficiency20);
                MyProfileSkills.super.onBackPressed();
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

        Log.d("TAG", "proficiencyEdittedFlog   " + proficiencyEdittedFlog + "\n edittedFlag  " + edittedFlag);

        if (edittedFlag == 1 || proficiencyEdittedFlog == 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfileSkills.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileSkills.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileSkills.this));

                }
            });

            alertDialog.show();
        } else
            MyProfileSkills.super.onBackPressed();
    }
}
