package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import placeme.octopusites.com.placeme.modal.KnownLangs;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;


public class MyProfileKnownLang extends AppCompatActivity {

    int langcount=0;
    View addmorelang;
    JSONObject json;
    JSONParser jParser = new JSONParser();
    //    private static String url_getlanguages = "http://192.168.100.10/AESTest/GetLanguages";
//    private static String url_savelanguages= "http://192.168.100.10/AESTest/SaveLanguages";
    String languages[];//,codes[];
    List<String> languageslist = new ArrayList<String>();
    List<String> level = new ArrayList<String>();
    StudentData s=new StudentData();

    String username,role;
    String digest1,digest2;
    Spinner knownlang1,proficiency1,knownlang2,proficiency2,knownlang3,proficiency3,knownlang4,proficiency4,knownlang5,proficiency5,knownlang6,proficiency6,knownlang7,proficiency7,knownlang8,proficiency8,knownlang9,proficiency9,knownlang10,proficiency10;
    String sknownlang1="",sproficiency1="",sknownlang2="",sproficiency2="",sknownlang3="",sproficiency3="",sknownlang4="",sproficiency4="",sknownlang5="",sproficiency5="",sknownlang6="",sproficiency6="",sknownlang7="",sproficiency7="",sknownlang8="",sproficiency8="",sknownlang9="",sproficiency9="",sknownlang10="",sproficiency10="";;
    String encknownlang1="",encproficiency1="",encknownlang2="",encproficiency2="",encknownlang3="",encproficiency3="",encknownlang4="",encproficiency4="",encknownlang5="",encproficiency5="",encknownlang6="",encproficiency6="",encknownlang7="",encproficiency7="",encknownlang8="",encproficiency8="",encknownlang9="",encproficiency9="",encknownlang10="",encproficiency10="";
    View trash1selectionview,trash2selectionview,trash3selectionview,trash4selectionview,trash5selectionview,trash6selectionview,trash7selectionview,trash8selectionview,trash9selectionview,trash10selectionview;
    int count;
    int d=0,p=0;
    ArrayAdapter<String> dataAdapter,dataAdapter2;
    private int proficiencyEdittedFlog=0;
    private int langEdittedFlog=0;

    ArrayList<KnownLangs> knownLangsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_known_lang);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Known Languages");
        ab.setDisplayHomeAsUpEnabled(true);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);


        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        TextView knowntxt=(TextView)findViewById(R.id.knowntxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        knowntxt.setTypeface(custom_font1);

        trash1selectionview=(View)findViewById(R.id.trash1selectionview);
        trash2selectionview=(View)findViewById(R.id.trash2selectionview);
        trash3selectionview=(View)findViewById(R.id.trash3selectionview);
        trash4selectionview=(View)findViewById(R.id.trash4selectionview);
        trash5selectionview=(View)findViewById(R.id.trash5selectionview);
        trash6selectionview=(View)findViewById(R.id.trash6selectionview);
        trash7selectionview=(View)findViewById(R.id.trash7selectionview);
        trash8selectionview=(View)findViewById(R.id.trash8selectionview);
        trash9selectionview=(View)findViewById(R.id.trash9selectionview);
        trash10selectionview=(View)findViewById(R.id.trash10selectionview);

        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=1;
                showDeletDialog();
            }
        });
        trash2selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=2;
                showDeletDialog();

            }
        });
        trash3selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=3;
                showDeletDialog();
            }
        });
        trash4selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=4;
                showDeletDialog();
            }
        });
        trash5selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=5;
                showDeletDialog();
            }
        });
        trash6selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=6;
                showDeletDialog();
            }
        });
        trash7selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=7;
                showDeletDialog();
            }
        });
        trash8selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=8;
                showDeletDialog();
            }
        });
        trash9selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=9;
                showDeletDialog();
            }
        });
        trash10selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=10;
                showDeletDialog();
            }
        });

        addmorelang=(View)findViewById(R.id.addmorelang);
        addmorelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(langcount==0)
                {
                    if(!knownlang1.getSelectedItem().toString().equals("- Select Language -")&&!proficiency1.getSelectedItem().toString().equals("- Proficiency -")) {
                        View v = (View) findViewById(R.id.line1);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.langrl2);
                        relativeLayout1.setVisibility(View.VISIBLE);


                        langcount++;
                    }
                    else
                    {
                        Toast.makeText(MyProfileKnownLang.this, "Please fill the First Language", Toast.LENGTH_SHORT).show();
                    }
                }
                else  if(langcount==1)
                {
                    if(!knownlang2.getSelectedItem().toString().equals("- Select Language -")&&!proficiency2.getSelectedItem().toString().equals("- Proficiency -")) {
                        View v=(View)findViewById(R.id.line2);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.langrl3);
                        relativeLayout1.setVisibility(View.VISIBLE);

                        langcount++;
                    }
                    else
                    {
                        Toast.makeText(MyProfileKnownLang.this, "Please fill the Second Language", Toast.LENGTH_SHORT).show();
                    }


                }
                else  if(langcount==2)
                {
                    if(!knownlang3.getSelectedItem().toString().equals("- Select Language -")&&!proficiency3.getSelectedItem().toString().equals("- Proficiency -")) {
                        View v=(View)findViewById(R.id.line3);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.langrl4);
                        relativeLayout1.setVisibility(View.VISIBLE);

                        langcount++;
                    }
                    else
                    {
                        Toast.makeText(MyProfileKnownLang.this, "Please fill the Third Language", Toast.LENGTH_SHORT).show();
                    }

                }
                else  if(langcount==3)
                {
                    if(!knownlang4.getSelectedItem().toString().equals("- Select Language -")&&!proficiency4.getSelectedItem().toString().equals("- Proficiency -")) {
                        View v=(View)findViewById(R.id.line4);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.langrl5);
                        relativeLayout1.setVisibility(View.VISIBLE);

                        langcount++;

                    }
                    else
                    {
                        Toast.makeText(MyProfileKnownLang.this, "Please fill the Fourth Language", Toast.LENGTH_SHORT).show();
                    }


                }
                else  if(langcount==4)
                {
                    if(!knownlang5.getSelectedItem().toString().equals("- Select Language -")&&!proficiency5.getSelectedItem().toString().equals("- Proficiency -")) {

                        View v=(View)findViewById(R.id.line5);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.langrl6);
                        relativeLayout1.setVisibility(View.VISIBLE);

                        langcount++;
                    }
                    else
                    {
                        Toast.makeText(MyProfileKnownLang.this, "Please fill the Fifth Language", Toast.LENGTH_SHORT).show();
                    }



                }
                else  if(langcount==5)
                {
                    if(!knownlang6.getSelectedItem().toString().equals("- Select Language -")&&!proficiency6.getSelectedItem().toString().equals("- Proficiency -")) {

                        View v=(View)findViewById(R.id.line6);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.langrl7);
                        relativeLayout1.setVisibility(View.VISIBLE);

                        langcount++;
                    }
                    else
                    {
                        Toast.makeText(MyProfileKnownLang.this, "Please fill the Sixth Language", Toast.LENGTH_SHORT).show();
                    }




                }
                else  if(langcount==6)
                {
                    if(!knownlang7.getSelectedItem().toString().equals("- Select Language -")&&!proficiency7.getSelectedItem().toString().equals("- Proficiency -")) {

                        View v=(View)findViewById(R.id.line7);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.langrl8);
                        relativeLayout1.setVisibility(View.VISIBLE);

                        langcount++;
                    }
                    else
                    {
                        Toast.makeText(MyProfileKnownLang.this, "Please fill the Seventh Language", Toast.LENGTH_SHORT).show();
                    }



                }
                else  if(langcount==7)
                {
                    if(!knownlang8.getSelectedItem().toString().equals("- Select Language -")&&!proficiency8.getSelectedItem().toString().equals("- Proficiency -")) {
                        View v=(View)findViewById(R.id.line8);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.langrl9);
                        relativeLayout1.setVisibility(View.VISIBLE);

                        langcount++;
                    }
                    else
                    {
                        Toast.makeText(MyProfileKnownLang.this, "Please fill the Eighth Language", Toast.LENGTH_SHORT).show();
                    }



                }
                else  if(langcount==8)
                {
                    if(!knownlang9.getSelectedItem().toString().equals("- Select Language -")&&!proficiency9.getSelectedItem().toString().equals("- Proficiency -")) {
                        View v=(View)findViewById(R.id.line9);
                        v.setVisibility(View.VISIBLE);

                        RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.langrl10);
                        relativeLayout1.setVisibility(View.VISIBLE);

                        TextView t=(TextView)findViewById(R.id.addmorelangtxt);
                        ImageView i=(ImageView)findViewById(R.id.addmorelangimg);
                        addmorelang.setVisibility(View.GONE);
                        t.setVisibility(View.GONE);
                        i.setVisibility(View.GONE);

                        langcount++;
                    }
                    else
                    {
                        Toast.makeText(MyProfileKnownLang.this, "Please fill the Ninth Language", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });
        ScrollView myprofileintroscrollview=(ScrollView)findViewById(R.id.myprofileknownlang);
        disableScrollbars(myprofileintroscrollview);

        knownlang1=(Spinner)findViewById(R.id.knownlang1);
        proficiency1=(Spinner)findViewById(R.id.proficiency1);
        knownlang2=(Spinner)findViewById(R.id.knownlang2);
        proficiency2=(Spinner)findViewById(R.id.proficiency2);
        knownlang3=(Spinner)findViewById(R.id.knownlang3);
        proficiency3=(Spinner)findViewById(R.id.proficiency3);
        knownlang4=(Spinner)findViewById(R.id.knownlang4);
        proficiency4=(Spinner)findViewById(R.id.proficiency4);
        knownlang5=(Spinner)findViewById(R.id.knownlang5);
        proficiency5=(Spinner)findViewById(R.id.proficiency5);
        knownlang6=(Spinner)findViewById(R.id.knownlang6);
        proficiency6=(Spinner)findViewById(R.id.proficiency6);
        knownlang7=(Spinner)findViewById(R.id.knownlang7);
        proficiency7=(Spinner)findViewById(R.id.proficiency7);
        knownlang8=(Spinner)findViewById(R.id.knownlang8);
        proficiency8=(Spinner)findViewById(R.id.proficiency8);
        knownlang9=(Spinner)findViewById(R.id.knownlang9);
        proficiency9=(Spinner)findViewById(R.id.proficiency9);
        knownlang10=(Spinner)findViewById(R.id.knownlang10);
        proficiency10=(Spinner)findViewById(R.id.proficiency10);

//        new GetLanguages().execute();


        level.add("- Proficiency -");
        level.add("Elementary");
        level.add("Intermediate");
        level.add("Professional");
        level.add("Native");


        dataAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item_long, level)
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
        };


        String[] langArray=getResources().getStringArray(R.array.languages_array);
        languageslist.clear();
        languageslist.add("- Select Language -");
        for(String lang:langArray){
            languageslist.add(lang);
        }

        populateLanguages();


//        textChangeListerners


        knownlang1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sknownlang1 = (String) parent.getItemAtPosition(position);

                if (s.getLang1() != null && !s.getLang1().equals(sknownlang1)) {
                    langEdittedFlog = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        knownlang2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sknownlang2 = (String) parent.getItemAtPosition(position);

                if (s.getLang2() != null && !s.getLang2().equals(sknownlang2)) {
                    langEdittedFlog = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        knownlang3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sknownlang3 = (String) parent.getItemAtPosition(position);

                if (s.getLang3() != null && !s.getLang3().equals(sknownlang3)) {
                    langEdittedFlog = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        knownlang4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sknownlang4 = (String) parent.getItemAtPosition(position);

                if (s.getLang4() != null && !s.getLang4().equals(sknownlang4)) {
                    langEdittedFlog = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        knownlang5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sknownlang5 = (String) parent.getItemAtPosition(position);

                if (s.getLang5() != null && !s.getLang5().equals(sknownlang5)) {
                    langEdittedFlog = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        knownlang6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sknownlang6 = (String) parent.getItemAtPosition(position);

                if (s.getLang6() != null && !s.getLang6().equals(sknownlang6)) {
                    langEdittedFlog = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        knownlang7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sknownlang7 = (String) parent.getItemAtPosition(position);

                if (s.getLang7() != null && !s.getLang7().equals(sknownlang7)) {
                    langEdittedFlog = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        knownlang8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sknownlang8 = (String) parent.getItemAtPosition(position);

                if (s.getLang8() != null && !s.getLang8().equals(sknownlang8)) {
                    langEdittedFlog = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        knownlang9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sknownlang9 = (String) parent.getItemAtPosition(position);

                if (s.getLang9() != null && !s.getLang9().equals(sknownlang9)) {
                    langEdittedFlog = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        knownlang10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sknownlang10 = (String) parent.getItemAtPosition(position);

                if (s.getLang10() != null && !s.getLang10().equals(sknownlang10)) {
                    langEdittedFlog = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//proficiency

        proficiency1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sproficiency1 = (String) parent.getItemAtPosition(position);

                String Sobj= s.getProficiency1();

                if (Sobj!=null && !Sobj.equals(sproficiency1)) {
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

                String Sobj= s.getProficiency2();

                if (Sobj!=null && !Sobj.equals(sproficiency2)) {
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

                String Sobj= s.getProficiency3();

                if (Sobj!=null && !Sobj.equals(sproficiency3)) {
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

                String Sobj= s.getProficiency4();

                if (Sobj!=null && !Sobj.equals(sproficiency4)) {
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

                String Sobj= s.getProficiency5();

                if (Sobj!=null && !Sobj.equals(sproficiency5)) {
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

                String Sobj= s.getProficiency6();

                if (Sobj!=null && !Sobj.equals(sproficiency6)) {
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

                String Sobj= s.getProficiency7();

                if (Sobj!=null && !Sobj.equals(sproficiency7)) {
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

                String Sobj= s.getProficiency8();

                if (Sobj!=null && !Sobj.equals(sproficiency8)) {
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
                String Sobj= s.getProficiency9();

                if (Sobj!=null && !Sobj.equals(sproficiency9)) {
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

                String Sobj= s.getProficiency10();

                if (Sobj!=null && !Sobj.equals(sproficiency10)) {
                    proficiencyEdittedFlog = 1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    void showDeletDialog()
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this project ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteLang();
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
            }
        });

        alertDialog.show();

    }
    void deleteLang()
    {
        View v = (View) findViewById(R.id.line9);
        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View) findViewById(R.id.line9);
            v1.setVisibility(View.GONE);

            RelativeLayout langrl = (RelativeLayout) findViewById(R.id.langrl10);
            langrl.setVisibility(View.GONE);

            langcount--;

            TextView t = (TextView) findViewById(R.id.addmorelangtxt);
            ImageView i = (ImageView) findViewById(R.id.addmorelangimg);
            addmorelang.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        }
        else
        {
            v = (View) findViewById(R.id.line8);
            if (v.getVisibility() == View.VISIBLE) {


                View v1 = (View) findViewById(R.id.line8);
                v1.setVisibility(View.GONE);

                RelativeLayout langrl = (RelativeLayout) findViewById(R.id.langrl9);
                langrl.setVisibility(View.GONE);

                langcount--;

            }
            else
            {
                v = (View) findViewById(R.id.line7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View) findViewById(R.id.line7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout langrl = (RelativeLayout) findViewById(R.id.langrl8);
                    langrl.setVisibility(View.GONE);

                    langcount--;
                }
                else
                {
                    v = (View) findViewById(R.id.line6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View) findViewById(R.id.line6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout langrl = (RelativeLayout) findViewById(R.id.langrl7);
                        langrl.setVisibility(View.GONE);

                        langcount--;

                    }
                    else
                    {
                        v = (View) findViewById(R.id.line5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) findViewById(R.id.line5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout langrl = (RelativeLayout) findViewById(R.id.langrl6);
                            langrl.setVisibility(View.GONE);

                            langcount--;


                        }
                        else
                        {
                            v = (View) findViewById(R.id.line4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) findViewById(R.id.line4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout langrl = (RelativeLayout) findViewById(R.id.langrl5);
                                langrl.setVisibility(View.GONE);

                                langcount--;
                            }
                            else
                            {
                                v = (View) findViewById(R.id.line3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) findViewById(R.id.line3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout langrl = (RelativeLayout) findViewById(R.id.langrl4);
                                    langrl.setVisibility(View.GONE);

                                    langcount--;

                                }
                                else
                                {
                                    v = (View) findViewById(R.id.line2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) findViewById(R.id.line2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout langrl = (RelativeLayout) findViewById(R.id.langrl3);
                                        langrl.setVisibility(View.GONE);

                                        langcount--;

                                    }
                                    else
                                    {
                                        v = (View) findViewById(R.id.line1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1=(View)findViewById(R.id.line1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout langrl=(RelativeLayout)findViewById(R.id.langrl2);
                                            langrl.setVisibility(View.GONE);

                                            langcount--;


                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(d==10)
        {
            knownlang10.setSelection(0);
            proficiency10.setSelection(0);
        }
        else if(d==9)
        {
            sknownlang10=knownlang10.getSelectedItem().toString();
            sproficiency10=proficiency10.getSelectedItem().toString();

            sknownlang9=sknownlang10;
            sproficiency9=sproficiency10;

            knownlang10.setSelection(0);
            proficiency10.setSelection(0);

            knownlang9.setSelection(dataAdapter.getPosition(sknownlang10));
            proficiency9.setSelection(dataAdapter2.getPosition(sproficiency10));


        }
        else if(d==8)
        {
            sknownlang10=knownlang10.getSelectedItem().toString();
            sproficiency10=proficiency10.getSelectedItem().toString();
            sknownlang9=knownlang9.getSelectedItem().toString();
            sproficiency9=proficiency9.getSelectedItem().toString();


            sknownlang8=sknownlang9;
            sproficiency8=sproficiency9;

            knownlang9.setSelection(0);
            proficiency9.setSelection(0);

            knownlang8.setSelection(dataAdapter.getPosition(sknownlang9));
            proficiency8.setSelection(dataAdapter2.getPosition(sproficiency9));



            sknownlang9=sknownlang10;
            sproficiency9=sproficiency10;

            knownlang10.setSelection(0);
            proficiency10.setSelection(0);

            knownlang9.setSelection(dataAdapter.getPosition(sknownlang10));
            proficiency9.setSelection(dataAdapter2.getPosition(sproficiency10));


        }
        else if(d==7)
        {
            sknownlang10=knownlang10.getSelectedItem().toString();
            sproficiency10=proficiency10.getSelectedItem().toString();
            sknownlang9=knownlang9.getSelectedItem().toString();
            sproficiency9=proficiency9.getSelectedItem().toString();
            sknownlang8=knownlang8.getSelectedItem().toString();
            sproficiency8=proficiency8.getSelectedItem().toString();

            sknownlang7=sknownlang8;
            sproficiency7=sproficiency8;

            knownlang8.setSelection(0);
            proficiency8.setSelection(0);

            knownlang7.setSelection(dataAdapter.getPosition(sknownlang8));
            proficiency7.setSelection(dataAdapter2.getPosition(sproficiency8));

            sknownlang8=sknownlang9;
            sproficiency8=sproficiency9;

            knownlang9.setSelection(0);
            proficiency9.setSelection(0);

            knownlang8.setSelection(dataAdapter.getPosition(sknownlang9));
            proficiency8.setSelection(dataAdapter2.getPosition(sproficiency9));

            sknownlang9=sknownlang10;
            sproficiency9=sproficiency10;

            knownlang10.setSelection(0);
            proficiency10.setSelection(0);

            knownlang9.setSelection(dataAdapter.getPosition(sknownlang10));
            proficiency9.setSelection(dataAdapter2.getPosition(sproficiency10));


        }
        else if(d==6)
        {
            sknownlang10=knownlang10.getSelectedItem().toString();
            sproficiency10=proficiency10.getSelectedItem().toString();
            sknownlang9=knownlang9.getSelectedItem().toString();
            sproficiency9=proficiency9.getSelectedItem().toString();
            sknownlang8=knownlang8.getSelectedItem().toString();
            sproficiency8=proficiency8.getSelectedItem().toString();
            sknownlang7=knownlang7.getSelectedItem().toString();
            sproficiency7=proficiency7.getSelectedItem().toString();

            sknownlang6=sknownlang7;
            sproficiency6=sproficiency7;

            knownlang7.setSelection(0);
            proficiency7.setSelection(0);

            knownlang6.setSelection(dataAdapter.getPosition(sknownlang7));
            proficiency6.setSelection(dataAdapter2.getPosition(sproficiency7));


            sknownlang7=sknownlang8;
            sproficiency7=sproficiency8;

            knownlang8.setSelection(0);
            proficiency8.setSelection(0);

            knownlang7.setSelection(dataAdapter.getPosition(sknownlang8));
            proficiency7.setSelection(dataAdapter2.getPosition(sproficiency8));


            sknownlang8=sknownlang9;
            sproficiency8=sproficiency9;

            knownlang9.setSelection(0);
            proficiency9.setSelection(0);

            knownlang8.setSelection(dataAdapter.getPosition(sknownlang9));
            proficiency8.setSelection(dataAdapter2.getPosition(sproficiency9));


            sknownlang9=sknownlang10;
            sproficiency9=sproficiency10;

            knownlang10.setSelection(0);
            proficiency10.setSelection(0);

            knownlang9.setSelection(dataAdapter.getPosition(sknownlang10));
            proficiency9.setSelection(dataAdapter2.getPosition(sproficiency10));


        }
        else if(d==5)
        {
            sknownlang10=knownlang10.getSelectedItem().toString();
            sproficiency10=proficiency10.getSelectedItem().toString();
            sknownlang9=knownlang9.getSelectedItem().toString();
            sproficiency9=proficiency9.getSelectedItem().toString();
            sknownlang8=knownlang8.getSelectedItem().toString();
            sproficiency8=proficiency8.getSelectedItem().toString();
            sknownlang7=knownlang7.getSelectedItem().toString();
            sproficiency7=proficiency7.getSelectedItem().toString();
            sknownlang6=knownlang6.getSelectedItem().toString();
            sproficiency6=proficiency6.getSelectedItem().toString();


            sknownlang5=sknownlang6;
            sproficiency5=sproficiency6;

            knownlang6.setSelection(0);
            proficiency6.setSelection(0);

            knownlang5.setSelection(dataAdapter.getPosition(sknownlang6));
            proficiency5.setSelection(dataAdapter2.getPosition(sproficiency6));

            sknownlang6=sknownlang7;
            sproficiency6=sproficiency7;

            knownlang7.setSelection(0);
            proficiency7.setSelection(0);

            knownlang6.setSelection(dataAdapter.getPosition(sknownlang7));
            proficiency6.setSelection(dataAdapter2.getPosition(sproficiency7));


            sknownlang7=sknownlang8;
            sproficiency7=sproficiency8;

            knownlang8.setSelection(0);
            proficiency8.setSelection(0);

            knownlang7.setSelection(dataAdapter.getPosition(sknownlang8));
            proficiency7.setSelection(dataAdapter2.getPosition(sproficiency8));


            sknownlang8=sknownlang9;
            sproficiency8=sproficiency9;

            knownlang9.setSelection(0);
            proficiency9.setSelection(0);

            knownlang8.setSelection(dataAdapter.getPosition(sknownlang9));
            proficiency8.setSelection(dataAdapter2.getPosition(sproficiency9));


            sknownlang9=sknownlang10;
            sproficiency9=sproficiency10;

            knownlang10.setSelection(0);
            proficiency10.setSelection(0);

            knownlang9.setSelection(dataAdapter.getPosition(sknownlang10));
            proficiency9.setSelection(dataAdapter2.getPosition(sproficiency10));


        }
        else if(d==4)
        {
            sknownlang10=knownlang10.getSelectedItem().toString();
            sproficiency10=proficiency10.getSelectedItem().toString();
            sknownlang9=knownlang9.getSelectedItem().toString();
            sproficiency9=proficiency9.getSelectedItem().toString();
            sknownlang8=knownlang8.getSelectedItem().toString();
            sproficiency8=proficiency8.getSelectedItem().toString();
            sknownlang7=knownlang7.getSelectedItem().toString();
            sproficiency7=proficiency7.getSelectedItem().toString();
            sknownlang6=knownlang6.getSelectedItem().toString();
            sproficiency6=proficiency6.getSelectedItem().toString();
            sknownlang5=knownlang5.getSelectedItem().toString();
            sproficiency5=proficiency5.getSelectedItem().toString();


            sknownlang4=sknownlang5;
            sproficiency4=sproficiency5;

            knownlang5.setSelection(0);
            proficiency5.setSelection(0);

            knownlang4.setSelection(dataAdapter.getPosition(sknownlang5));
            proficiency4.setSelection(dataAdapter2.getPosition(sproficiency5));


            sknownlang5=sknownlang6;
            sproficiency5=sproficiency6;

            knownlang6.setSelection(0);
            proficiency6.setSelection(0);

            knownlang5.setSelection(dataAdapter.getPosition(sknownlang6));
            proficiency5.setSelection(dataAdapter2.getPosition(sproficiency6));


            sknownlang6=sknownlang7;
            sproficiency6=sproficiency7;

            knownlang7.setSelection(0);
            proficiency7.setSelection(0);

            knownlang6.setSelection(dataAdapter.getPosition(sknownlang7));
            proficiency6.setSelection(dataAdapter2.getPosition(sproficiency7));


            sknownlang7=sknownlang8;
            sproficiency7=sproficiency8;

            knownlang8.setSelection(0);
            proficiency8.setSelection(0);

            knownlang7.setSelection(dataAdapter.getPosition(sknownlang8));
            proficiency7.setSelection(dataAdapter2.getPosition(sproficiency8));


            sknownlang8=sknownlang9;
            sproficiency8=sproficiency9;

            knownlang9.setSelection(0);
            proficiency9.setSelection(0);

            knownlang8.setSelection(dataAdapter.getPosition(sknownlang9));
            proficiency8.setSelection(dataAdapter2.getPosition(sproficiency9));


            sknownlang9=sknownlang10;
            sproficiency9=sproficiency10;

            knownlang10.setSelection(0);
            proficiency10.setSelection(0);

            knownlang9.setSelection(dataAdapter.getPosition(sknownlang10));
            proficiency9.setSelection(dataAdapter2.getPosition(sproficiency10));


        }
        else if(d==3)
        {
            sknownlang10=knownlang10.getSelectedItem().toString();
            sproficiency10=proficiency10.getSelectedItem().toString();
            sknownlang9=knownlang9.getSelectedItem().toString();
            sproficiency9=proficiency9.getSelectedItem().toString();
            sknownlang8=knownlang8.getSelectedItem().toString();
            sproficiency8=proficiency8.getSelectedItem().toString();
            sknownlang7=knownlang7.getSelectedItem().toString();
            sproficiency7=proficiency7.getSelectedItem().toString();
            sknownlang6=knownlang6.getSelectedItem().toString();
            sproficiency6=proficiency6.getSelectedItem().toString();
            sknownlang5=knownlang5.getSelectedItem().toString();
            sproficiency5=proficiency5.getSelectedItem().toString();
            sknownlang4=knownlang4.getSelectedItem().toString();
            sproficiency4=proficiency4.getSelectedItem().toString();


            sknownlang3=sknownlang4;
            sproficiency3=sproficiency4;

            knownlang4.setSelection(0);
            proficiency4.setSelection(0);

            knownlang3.setSelection(dataAdapter.getPosition(sknownlang4));
            proficiency3.setSelection(dataAdapter2.getPosition(sproficiency4));


            sknownlang4=sknownlang5;
            sproficiency4=sproficiency5;

            knownlang5.setSelection(0);
            proficiency5.setSelection(0);

            knownlang4.setSelection(dataAdapter.getPosition(sknownlang5));
            proficiency4.setSelection(dataAdapter2.getPosition(sproficiency5));


            sknownlang5=sknownlang6;
            sproficiency5=sproficiency6;

            knownlang6.setSelection(0);
            proficiency6.setSelection(0);

            knownlang5.setSelection(dataAdapter.getPosition(sknownlang6));
            proficiency5.setSelection(dataAdapter2.getPosition(sproficiency6));


            sknownlang6=sknownlang7;
            sproficiency6=sproficiency7;

            knownlang7.setSelection(0);
            proficiency7.setSelection(0);

            knownlang6.setSelection(dataAdapter.getPosition(sknownlang7));
            proficiency6.setSelection(dataAdapter2.getPosition(sproficiency7));


            sknownlang7=sknownlang8;
            sproficiency7=sproficiency8;

            knownlang8.setSelection(0);
            proficiency8.setSelection(0);

            knownlang7.setSelection(dataAdapter.getPosition(sknownlang8));
            proficiency7.setSelection(dataAdapter2.getPosition(sproficiency8));


            sknownlang8=sknownlang9;
            sproficiency8=sproficiency9;

            knownlang9.setSelection(0);
            proficiency9.setSelection(0);

            knownlang8.setSelection(dataAdapter.getPosition(sknownlang9));
            proficiency8.setSelection(dataAdapter2.getPosition(sproficiency9));


            sknownlang9=sknownlang10;
            sproficiency9=sproficiency10;

            knownlang10.setSelection(0);
            proficiency10.setSelection(0);

            knownlang9.setSelection(dataAdapter.getPosition(sknownlang10));
            proficiency9.setSelection(dataAdapter2.getPosition(sproficiency10));


        }
        else if(d==2)
        {
            sknownlang10=knownlang10.getSelectedItem().toString();
            sproficiency10=proficiency10.getSelectedItem().toString();
            sknownlang9=knownlang9.getSelectedItem().toString();
            sproficiency9=proficiency9.getSelectedItem().toString();
            sknownlang8=knownlang8.getSelectedItem().toString();
            sproficiency8=proficiency8.getSelectedItem().toString();
            sknownlang7=knownlang7.getSelectedItem().toString();
            sproficiency7=proficiency7.getSelectedItem().toString();
            sknownlang6=knownlang6.getSelectedItem().toString();
            sproficiency6=proficiency6.getSelectedItem().toString();
            sknownlang5=knownlang5.getSelectedItem().toString();
            sproficiency5=proficiency5.getSelectedItem().toString();
            sknownlang4=knownlang4.getSelectedItem().toString();
            sproficiency4=proficiency4.getSelectedItem().toString();
            sknownlang3=knownlang3.getSelectedItem().toString();
            sproficiency3=proficiency3.getSelectedItem().toString();


            sknownlang2=sknownlang3;
            sproficiency2=sproficiency3;

            knownlang3.setSelection(0);
            proficiency3.setSelection(0);

            knownlang2.setSelection(dataAdapter.getPosition(sknownlang3));
            proficiency2.setSelection(dataAdapter2.getPosition(sproficiency3));

            sknownlang3=sknownlang4;
            sproficiency3=sproficiency4;

            knownlang4.setSelection(0);
            proficiency4.setSelection(0);

            knownlang3.setSelection(dataAdapter.getPosition(sknownlang4));
            proficiency3.setSelection(dataAdapter2.getPosition(sproficiency4));


            sknownlang4=sknownlang5;
            sproficiency4=sproficiency5;

            knownlang5.setSelection(0);
            proficiency5.setSelection(0);

            knownlang4.setSelection(dataAdapter.getPosition(sknownlang5));
            proficiency4.setSelection(dataAdapter2.getPosition(sproficiency5));


            sknownlang5=sknownlang6;
            sproficiency5=sproficiency6;

            knownlang6.setSelection(0);
            proficiency6.setSelection(0);

            knownlang5.setSelection(dataAdapter.getPosition(sknownlang6));
            proficiency5.setSelection(dataAdapter2.getPosition(sproficiency6));


            sknownlang6=sknownlang7;
            sproficiency6=sproficiency7;

            knownlang7.setSelection(0);
            proficiency7.setSelection(0);

            knownlang6.setSelection(dataAdapter.getPosition(sknownlang7));
            proficiency6.setSelection(dataAdapter2.getPosition(sproficiency7));


            sknownlang7=sknownlang8;
            sproficiency7=sproficiency8;

            knownlang8.setSelection(0);
            proficiency8.setSelection(0);

            knownlang7.setSelection(dataAdapter.getPosition(sknownlang8));
            proficiency7.setSelection(dataAdapter2.getPosition(sproficiency8));


            sknownlang8=sknownlang9;
            sproficiency8=sproficiency9;

            knownlang9.setSelection(0);
            proficiency9.setSelection(0);

            knownlang8.setSelection(dataAdapter.getPosition(sknownlang9));
            proficiency8.setSelection(dataAdapter2.getPosition(sproficiency9));


            sknownlang9=sknownlang10;
            sproficiency9=sproficiency10;

            knownlang10.setSelection(0);
            proficiency10.setSelection(0);

            knownlang9.setSelection(dataAdapter.getPosition(sknownlang10));
            proficiency9.setSelection(dataAdapter2.getPosition(sproficiency10));


        }
        else if(d==1)
        {
            sknownlang10=knownlang10.getSelectedItem().toString();
            sproficiency10=proficiency10.getSelectedItem().toString();
            sknownlang9=knownlang9.getSelectedItem().toString();
            sproficiency9=proficiency9.getSelectedItem().toString();
            sknownlang8=knownlang8.getSelectedItem().toString();
            sproficiency8=proficiency8.getSelectedItem().toString();
            sknownlang7=knownlang7.getSelectedItem().toString();
            sproficiency7=proficiency7.getSelectedItem().toString();
            sknownlang6=knownlang6.getSelectedItem().toString();
            sproficiency6=proficiency6.getSelectedItem().toString();
            sknownlang5=knownlang5.getSelectedItem().toString();
            sproficiency5=proficiency5.getSelectedItem().toString();
            sknownlang4=knownlang4.getSelectedItem().toString();
            sproficiency4=proficiency4.getSelectedItem().toString();
            sknownlang3=knownlang3.getSelectedItem().toString();
            sproficiency3=proficiency3.getSelectedItem().toString();
            sknownlang2=knownlang2.getSelectedItem().toString();
            sproficiency2=proficiency2.getSelectedItem().toString();



            sknownlang1=sknownlang2;
            sproficiency1=sproficiency2;

            knownlang2.setSelection(0);
            proficiency2.setSelection(0);

            knownlang1.setSelection(dataAdapter.getPosition(sknownlang2));
            proficiency1.setSelection(dataAdapter2.getPosition(sproficiency2));


            sknownlang2=sknownlang3;
            sproficiency2=sproficiency3;

            knownlang3.setSelection(0);
            proficiency3.setSelection(0);

            knownlang2.setSelection(dataAdapter.getPosition(sknownlang3));
            proficiency2.setSelection(dataAdapter2.getPosition(sproficiency3));


            sknownlang3=sknownlang4;
            sproficiency3=sproficiency4;

            knownlang4.setSelection(0);
            proficiency4.setSelection(0);

            knownlang3.setSelection(dataAdapter.getPosition(sknownlang4));
            proficiency3.setSelection(dataAdapter2.getPosition(sproficiency4));


            sknownlang4=sknownlang5;
            sproficiency4=sproficiency5;

            knownlang5.setSelection(0);
            proficiency5.setSelection(0);

            knownlang4.setSelection(dataAdapter.getPosition(sknownlang5));
            proficiency4.setSelection(dataAdapter2.getPosition(sproficiency5));


            sknownlang5=sknownlang6;
            sproficiency5=sproficiency6;

            knownlang6.setSelection(0);
            proficiency6.setSelection(0);

            knownlang5.setSelection(dataAdapter.getPosition(sknownlang6));
            proficiency5.setSelection(dataAdapter2.getPosition(sproficiency6));


            sknownlang6=sknownlang7;
            sproficiency6=sproficiency7;

            knownlang7.setSelection(0);
            proficiency7.setSelection(0);

            knownlang6.setSelection(dataAdapter.getPosition(sknownlang7));
            proficiency6.setSelection(dataAdapter2.getPosition(sproficiency7));


            sknownlang7=sknownlang8;
            sproficiency7=sproficiency8;

            knownlang8.setSelection(0);
            proficiency8.setSelection(0);

            knownlang7.setSelection(dataAdapter.getPosition(sknownlang8));
            proficiency7.setSelection(dataAdapter2.getPosition(sproficiency8));


            sknownlang8=sknownlang9;
            sproficiency8=sproficiency9;

            knownlang9.setSelection(0);
            proficiency9.setSelection(0);

            knownlang8.setSelection(dataAdapter.getPosition(sknownlang9));
            proficiency8.setSelection(dataAdapter2.getPosition(sproficiency9));

            sknownlang9=sknownlang10;
            sproficiency9=sproficiency10;

            knownlang10.setSelection(0);
            proficiency10.setSelection(0);

            knownlang9.setSelection(dataAdapter.getPosition(sknownlang10));
            proficiency9.setSelection(dataAdapter2.getPosition(sproficiency10));


        }
    }
    class GetLanguages extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//
//            json = jParser.makeHttpRequest(MyConstants.url_getlanguages, "GET", params);
//            try {
//                String s = json.getString("count");
//                count=Integer.parseInt(s);
//                languages=new String[count];
////                codes=new String[count];
//                for(int i=0;i<count;i++)
//                {
//                    languages[i]=json.getString("language"+i);
////                    codes[i]=json.getString("code"+i);
//                }
//            }catch (Exception e){e.printStackTrace();}


            //
            String[] langArray=getResources().getStringArray(R.array.languages_array);
            languageslist.clear();
            languageslist.add("- Select Language -");
            for(String lang:langArray){
                languageslist.add(lang);
            }

            populateLanguages();

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

//            languageslist.clear();
//            languageslist.add("- Select Language -");
//            for(int i=0;i<count;i++)
//            {
//                languageslist.add(languages[i]);
//            }
//            populateLanguages();
        }
    }
    void populateLanguages()
    {
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_long, languageslist)
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
        };

        knownlang1.setAdapter(dataAdapter);
        knownlang2.setAdapter(dataAdapter);
        knownlang3.setAdapter(dataAdapter);
        knownlang4.setAdapter(dataAdapter);
        knownlang5.setAdapter(dataAdapter);
        knownlang6.setAdapter(dataAdapter);
        knownlang7.setAdapter(dataAdapter);
        knownlang8.setAdapter(dataAdapter);
        knownlang9.setAdapter(dataAdapter);
        knownlang10.setAdapter(dataAdapter);

        proficiency1.setAdapter(dataAdapter2);
        proficiency2.setAdapter(dataAdapter2);
        proficiency3.setAdapter(dataAdapter2);
        proficiency4.setAdapter(dataAdapter2);
        proficiency5.setAdapter(dataAdapter2);
        proficiency6.setAdapter(dataAdapter2);
        proficiency7.setAdapter(dataAdapter2);
        proficiency8.setAdapter(dataAdapter2);
        proficiency9.setAdapter(dataAdapter2);
        proficiency10.setAdapter(dataAdapter2);

        sknownlang1=s.getLang1();
        sproficiency1=s.getProficiency1();
        sknownlang2=s.getLang2();
        sproficiency2=s.getProficiency2();
        sknownlang3=s.getLang3();
        sproficiency3=s.getProficiency3();
        sknownlang4=s.getLang4();
        sproficiency4=s.getProficiency4();
        sknownlang5=s.getLang5();
        sproficiency5=s.getProficiency5();
        sknownlang6=s.getLang6();
        sproficiency6=s.getProficiency6();
        sknownlang7=s.getLang7();
        sproficiency7=s.getProficiency7();
        sknownlang8=s.getLang8();
        sproficiency8=s.getProficiency8();
        sknownlang9=s.getLang9();
        sproficiency9=s.getProficiency9();
        sknownlang10=s.getLang10();
        sproficiency10=s.getProficiency10();




//        knownlang1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sknownlang1 = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        knownlang2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sknownlang2 = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        knownlang3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sknownlang3 = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        knownlang4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sknownlang4 = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        knownlang5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sknownlang5 = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        knownlang6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sknownlang6 = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        knownlang7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sknownlang7 = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        knownlang8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sknownlang8 = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        knownlang9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sknownlang9 = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        knownlang10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sknownlang10 = (String) parent.getItemAtPosition(position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });




        if(sknownlang1!=null) {
            if (!sknownlang1.equals("- Select Language -"))
                knownlang1.setSelection(dataAdapter.getPosition(sknownlang1));
        }
        if(sproficiency1!=null) {
            if (!sproficiency1.equals("Proficiency"))
                proficiency1.setSelection(dataAdapter2.getPosition(sproficiency1));
        }
        if(sknownlang2!=null) {
            if (!sknownlang2.equals("- Select Language -")) {
                knownlang2.setSelection(dataAdapter.getPosition(sknownlang2));
                View v = (View) findViewById(R.id.line1);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.langrl2);
                relativeLayout1.setVisibility(View.VISIBLE);

                langcount++;
            }
        }
        if(sproficiency2!=null) {
            if (!sproficiency2.equals("Proficiency"))
                proficiency2.setSelection(dataAdapter2.getPosition(sproficiency2));
        }
        if(sknownlang3!=null) {
            if (!sknownlang3.equals("- Select Language -")) {
                knownlang3.setSelection(dataAdapter.getPosition(sknownlang3));
                View v = (View) findViewById(R.id.line2);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.langrl3);
                relativeLayout1.setVisibility(View.VISIBLE);


                langcount++;
            }
        }
        if(sproficiency3!=null) {
            if (!sproficiency3.equals("Proficiency"))
                proficiency3.setSelection(dataAdapter2.getPosition(sproficiency3));
        }
        if(sknownlang4!=null) {
            if (!sknownlang4.equals("- Select Language -")) {
                knownlang4.setSelection(dataAdapter.getPosition(sknownlang4));
                View v = (View) findViewById(R.id.line3);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.langrl4);
                relativeLayout1.setVisibility(View.VISIBLE);


                langcount++;
            }
        }
        if(sproficiency4!=null) {
            if (!sproficiency4.equals("Proficiency"))
                proficiency4.setSelection(dataAdapter2.getPosition(sproficiency4));
        }
        if(sknownlang5!=null) {
            if (!sknownlang5.equals("- Select Language -")) {
                knownlang5.setSelection(dataAdapter.getPosition(sknownlang5));
                View v = (View) findViewById(R.id.line4);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.langrl5);
                relativeLayout1.setVisibility(View.VISIBLE);


                langcount++;
            }
        }
        if(sproficiency5!=null) {
            if (!sproficiency5.equals("Proficiency"))
                proficiency5.setSelection(dataAdapter2.getPosition(sproficiency5));
        }
        if(sknownlang6!=null) {
            if (!sknownlang6.equals("- Select Language -")) {
                knownlang6.setSelection(dataAdapter.getPosition(sknownlang6));
                View v = (View) findViewById(R.id.line5);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.langrl6);
                relativeLayout1.setVisibility(View.VISIBLE);


                langcount++;
            }
        }
        if(sproficiency6!=null) {
            if (!sproficiency6.equals("Proficiency"))
                proficiency6.setSelection(dataAdapter2.getPosition(sproficiency6));
        }
        if(sknownlang7!=null) {
            if (!sknownlang7.equals("- Select Language -")) {
                knownlang7.setSelection(dataAdapter.getPosition(sknownlang7));
                View v = (View) findViewById(R.id.line6);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.langrl7);
                relativeLayout1.setVisibility(View.VISIBLE);


                langcount++;
            }
        }
        if(sproficiency7!=null) {
            if (!sproficiency7.equals("Proficiency"))
                proficiency7.setSelection(dataAdapter2.getPosition(sproficiency7));
        }
        if(sknownlang8!=null) {
            if (!sknownlang8.equals("- Select Language -")) {
                knownlang8.setSelection(dataAdapter.getPosition(sknownlang8));
                View v = (View) findViewById(R.id.line7);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.langrl8);
                relativeLayout1.setVisibility(View.VISIBLE);


                langcount++;
            }
        }
        if(sproficiency8!=null) {
            if (!sproficiency8.equals("Proficiency"))
                proficiency8.setSelection(dataAdapter2.getPosition(sproficiency8));
        }
        if(sknownlang9!=null) {
            if (!sknownlang9.equals("- Select Language -")) {
                knownlang9.setSelection(dataAdapter.getPosition(sknownlang9));
                View v = (View) findViewById(R.id.line8);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.langrl9);
                relativeLayout1.setVisibility(View.VISIBLE);


                langcount++;
            }
        }
        if(sproficiency9!=null) {
            if (!sproficiency9.equals("Proficiency"))
                proficiency9.setSelection(dataAdapter2.getPosition(sproficiency9));
        }
        if(sknownlang10!=null) {
            if (!sknownlang10.equals("- Select Language -")) {
                knownlang10.setSelection(dataAdapter.getPosition(sknownlang10));
                View v = (View) findViewById(R.id.line9);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.langrl10);
                relativeLayout1.setVisibility(View.VISIBLE);


                langcount++;
            }
        }
        if(sproficiency10!=null) {
            if (!sproficiency10.equals("Proficiency"))
                proficiency10.setSelection(dataAdapter2.getPosition(sproficiency10));
        }

    }

    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }
    void validateandSave()
    {
        sknownlang1=knownlang1.getSelectedItem().toString();
        sproficiency1=proficiency1.getSelectedItem().toString();
        sknownlang2=knownlang2.getSelectedItem().toString();
        sproficiency2=proficiency2.getSelectedItem().toString();
        sknownlang3=knownlang3.getSelectedItem().toString();
        sproficiency3=proficiency3.getSelectedItem().toString();
        sknownlang4=knownlang4.getSelectedItem().toString();
        sproficiency4=proficiency4.getSelectedItem().toString();
        sknownlang5=knownlang5.getSelectedItem().toString();
        sproficiency5=proficiency5.getSelectedItem().toString();
        sknownlang6=knownlang6.getSelectedItem().toString();
        sproficiency6=proficiency6.getSelectedItem().toString();
        sknownlang7=knownlang7.getSelectedItem().toString();
        sproficiency7=proficiency7.getSelectedItem().toString();
        sknownlang8=knownlang8.getSelectedItem().toString();
        sproficiency8=proficiency8.getSelectedItem().toString();
        sknownlang9=knownlang9.getSelectedItem().toString();
        sproficiency9=proficiency9.getSelectedItem().toString();
        sknownlang10=knownlang10.getSelectedItem().toString();
        sproficiency10=proficiency10.getSelectedItem().toString();

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag=0;
        if(langcount==0)
        {
            if(sknownlang1.equals("- Select Language -"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
            }
            else
            {
                if(sproficiency1.equals("Proficiency"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                }
            }
        }
        else if(langcount==1)
        {
            if(sknownlang1.equals("- Select Language -"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
            }
            else if(sproficiency1.equals("Proficiency"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
            }
            else
            {
                errorflag=0;
                if(sknownlang2.equals("- Select Language -"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                }
                else if(sproficiency2.equals("Proficiency"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                }
            }
        }
        else if(langcount==2)
        {
            if(sknownlang1.equals("- Select Language -"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
            }
            else if(sproficiency1.equals("Proficiency"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
            }
            else
            {
                errorflag=0;
                if(sknownlang2.equals("- Select Language -"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                }
                else if(sproficiency2.equals("Proficiency"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                }
                else
                {
                    errorflag=0;
                    if(sknownlang3.equals("- Select Language -"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                    }
                    else if(sproficiency3.equals("Proficiency"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        else if(langcount==3)
        {
            if(sknownlang1.equals("- Select Language -"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
            }
            else if(sproficiency1.equals("Proficiency"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
            }
            else
            {
                errorflag=0;
                if(sknownlang2.equals("- Select Language -"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                }
                else if(sproficiency2.equals("Proficiency"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                }
                else
                {
                    errorflag=0;
                    if(sknownlang3.equals("- Select Language -"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                    }
                    else if(sproficiency3.equals("Proficiency"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        errorflag=0;
                        if(sknownlang4.equals("- Select Language -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                        }
                        else if(sproficiency4.equals("Proficiency"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        }
        else if(langcount==4)
        {
            if(sknownlang1.equals("- Select Language -"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
            }
            else if(sproficiency1.equals("Proficiency"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
            }
            else
            {
                errorflag=0;
                if(sknownlang2.equals("- Select Language -"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                }
                else if(sproficiency2.equals("Proficiency"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                }
                else
                {
                    errorflag=0;
                    if(sknownlang3.equals("- Select Language -"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                    }
                    else if(sproficiency3.equals("Proficiency"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        errorflag=0;
                        if(sknownlang4.equals("- Select Language -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                        }
                        else if(sproficiency4.equals("Proficiency"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sknownlang5.equals("- Select Language -"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                            }
                            else if(sproficiency5.equals("Proficiency"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        }
        else if(langcount==5)
        {
            if(sknownlang1.equals("- Select Language -"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
            }
            else if(sproficiency1.equals("Proficiency"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
            }
            else
            {
                errorflag=0;
                if(sknownlang2.equals("- Select Language -"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                }
                else if(sproficiency2.equals("Proficiency"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                }
                else
                {
                    errorflag=0;
                    if(sknownlang3.equals("- Select Language -"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                    }
                    else if(sproficiency3.equals("Proficiency"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        errorflag=0;
                        if(sknownlang4.equals("- Select Language -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                        }
                        else if(sproficiency4.equals("Proficiency"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sknownlang5.equals("- Select Language -"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                            }
                            else if(sproficiency5.equals("Proficiency"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                errorflag=0;
                                if(sknownlang6.equals("- Select Language -"))
                                {
                                    errorflag=1;
                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                }
                                else if(sproficiency6.equals("Proficiency"))
                                {
                                    errorflag=1;
                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(langcount==6)
        {
            if(sknownlang1.equals("- Select Language -"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
            }
            else if(sproficiency1.equals("Proficiency"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
            }
            else
            {
                errorflag=0;
                if(sknownlang2.equals("- Select Language -"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                }
                else if(sproficiency2.equals("Proficiency"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                }
                else
                {
                    errorflag=0;
                    if(sknownlang3.equals("- Select Language -"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                    }
                    else if(sproficiency3.equals("Proficiency"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        errorflag=0;
                        if(sknownlang4.equals("- Select Language -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                        }
                        else if(sproficiency4.equals("Proficiency"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sknownlang5.equals("- Select Language -"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                            }
                            else if(sproficiency5.equals("Proficiency"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                errorflag=0;
                                if(sknownlang6.equals("- Select Language -"))
                                {
                                    errorflag=1;
                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                }
                                else if(sproficiency6.equals("Proficiency"))
                                {
                                    errorflag=1;
                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sknownlang7.equals("- Select Language -"))
                                    {
                                        errorflag=1;
                                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                    }
                                    else if(sproficiency7.equals("Proficiency"))
                                    {
                                        errorflag=1;
                                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(langcount==7)
        {
            if(sknownlang1.equals("- Select Language -"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
            }
            else if(sproficiency1.equals("Proficiency"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
            }
            else
            {
                errorflag=0;
                if(sknownlang2.equals("- Select Language -"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                }
                else if(sproficiency2.equals("Proficiency"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                }
                else
                {
                    errorflag=0;
                    if(sknownlang3.equals("- Select Language -"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                    }
                    else if(sproficiency3.equals("Proficiency"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        errorflag=0;
                        if(sknownlang4.equals("- Select Language -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                        }
                        else if(sproficiency4.equals("Proficiency"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sknownlang5.equals("- Select Language -"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                            }
                            else if(sproficiency5.equals("Proficiency"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                errorflag=0;
                                if(sknownlang6.equals("- Select Language -"))
                                {
                                    errorflag=1;
                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                }
                                else if(sproficiency6.equals("Proficiency"))
                                {
                                    errorflag=1;
                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sknownlang7.equals("- Select Language -"))
                                    {
                                        errorflag=1;
                                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                    }
                                    else if(sproficiency7.equals("Proficiency"))
                                    {
                                        errorflag=1;
                                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(sknownlang8.equals("- Select Language -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                        }
                                        else if(sproficiency8.equals("Proficiency"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(langcount==8)
        {
            if(sknownlang1.equals("- Select Language -"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
            }
            else if(sproficiency1.equals("Proficiency"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
            }
            else
            {
                errorflag=0;
                if(sknownlang2.equals("- Select Language -"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                }
                else if(sproficiency2.equals("Proficiency"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                }
                else
                {
                    errorflag=0;
                    if(sknownlang3.equals("- Select Language -"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                    }
                    else if(sproficiency3.equals("Proficiency"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        errorflag=0;
                        if(sknownlang4.equals("- Select Language -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                        }
                        else if(sproficiency4.equals("Proficiency"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sknownlang5.equals("- Select Language -"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                            }
                            else if(sproficiency5.equals("Proficiency"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                errorflag=0;
                                if(sknownlang6.equals("- Select Language -"))
                                {
                                    errorflag=1;
                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                }
                                else if(sproficiency6.equals("Proficiency"))
                                {
                                    errorflag=1;
                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sknownlang7.equals("- Select Language -"))
                                    {
                                        errorflag=1;
                                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                    }
                                    else if(sproficiency7.equals("Proficiency"))
                                    {
                                        errorflag=1;
                                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(sknownlang8.equals("- Select Language -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                        }
                                        else if(sproficiency8.equals("Proficiency"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sknownlang9.equals("- Select Language -"))
                                            {
                                                errorflag=1;
                                                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                            }
                                            else if(sproficiency9.equals("Proficiency"))
                                            {
                                                errorflag=1;
                                                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(langcount==9)
        {
            if(sknownlang1.equals("- Select Language -"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
            }
            else if(sproficiency1.equals("Proficiency"))
            {
                errorflag=1;
                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
            }
            else
            {
                errorflag=0;
                if(sknownlang2.equals("- Select Language -"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                }
                else if(sproficiency2.equals("Proficiency"))
                {
                    errorflag=1;
                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                }
                else
                {
                    errorflag=0;
                    if(sknownlang3.equals("- Select Language -"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                    }
                    else if(sproficiency3.equals("Proficiency"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        errorflag=0;
                        if(sknownlang4.equals("- Select Language -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                        }
                        else if(sproficiency4.equals("Proficiency"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sknownlang5.equals("- Select Language -"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                            }
                            else if(sproficiency5.equals("Proficiency"))
                            {
                                errorflag=1;
                                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                errorflag=0;
                                if(sknownlang6.equals("- Select Language -"))
                                {
                                    errorflag=1;
                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                }
                                else if(sproficiency6.equals("Proficiency"))
                                {
                                    errorflag=1;
                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sknownlang7.equals("- Select Language -"))
                                    {
                                        errorflag=1;
                                        Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                    }
                                    else if(sproficiency7.equals("Proficiency"))
                                    {
                                        errorflag=1;
                                        Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(sknownlang8.equals("- Select Language -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                        }
                                        else if(sproficiency8.equals("Proficiency"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sknownlang9.equals("- Select Language -"))
                                            {
                                                errorflag=1;
                                                Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                            }
                                            else if(sproficiency9.equals("Proficiency"))
                                            {
                                                errorflag=1;
                                                Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                            }
                                            else
                                            {
                                                errorflag=0;
                                                if(sknownlang10.equals("- Select Language -"))
                                                {
                                                    errorflag=1;
                                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Language",Toast.LENGTH_LONG).show();
                                                }
                                                else if(sproficiency10.equals("Proficiency"))
                                                {
                                                    errorflag=1;
                                                    Toast.makeText(MyProfileKnownLang.this,"Please Select Proficiency",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(errorflag==0)
        {
            try
            {
                KnownLangs obj1=new KnownLangs(sknownlang1,sproficiency1);
                KnownLangs obj2=new KnownLangs(sknownlang2,sproficiency2);
                KnownLangs obj3=new KnownLangs(sknownlang3,sproficiency3);
                KnownLangs obj4=new KnownLangs(sknownlang4,sproficiency4);
                KnownLangs obj5=new KnownLangs(sknownlang5,sproficiency5);
                KnownLangs obj6=new KnownLangs(sknownlang6,sproficiency6);
                KnownLangs obj7=new KnownLangs(sknownlang7,sproficiency7);
                KnownLangs obj8=new KnownLangs(sknownlang8,sproficiency8);
                KnownLangs obj9=new KnownLangs(sknownlang9,sproficiency9);
                KnownLangs obj10=new KnownLangs(sknownlang10,sproficiency10);

                knownLangsList.add(obj1);
                knownLangsList.add(obj2);
                knownLangsList.add(obj3);
                knownLangsList.add(obj4);
                knownLangsList.add(obj5);
                knownLangsList.add(obj6);
                knownLangsList.add(obj7);
                knownLangsList.add(obj8);
                knownLangsList.add(obj9);
                knownLangsList.add(obj10);

                String encObjString=OtoString(knownLangsList,MySharedPreferencesManager.getDigest1(MyProfileKnownLang.this),MySharedPreferencesManager.getDigest2(MyProfileKnownLang.this));

                new SaveLanguages().execute(encObjString);


            }catch (Exception e){Toast.makeText(MyProfileKnownLang.this,e.getMessage(),Toast.LENGTH_LONG).show();}
        }

    }
    class SaveLanguages extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("d",param[0]));       //0


            json = jParser.makeHttpRequest(MyConstants.url_savelanguages, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(MyProfileKnownLang.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();



                if(role.equals("student"))
                   setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                  setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("hr"))
                    setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);

                s.setLang1(sknownlang1);
                s.setProficiency1(sproficiency1);
                s.setLang2(sknownlang2);
                s.setProficiency2(sproficiency2);
                s.setLang3(sknownlang3);
                s.setProficiency3(sproficiency3);
                s.setLang4(sknownlang4);
                s.setProficiency4(sproficiency4);
                s.setLang5(sknownlang5);
                s.setProficiency5(sproficiency5);
                s.setLang6(sknownlang6);
                s.setProficiency6(sproficiency6);
                s.setLang7(sknownlang7);
                s.setProficiency7(sproficiency7);
                s.setLang8(sknownlang8);
                s.setProficiency8(sproficiency8);
                s.setLang9(sknownlang9);
                s.setProficiency9(sproficiency9);
                s.setLang10(sknownlang10);
                s.setProficiency10(sproficiency10);

                MyProfileKnownLang.super.onBackPressed();
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

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);


    }
    @Override
    public void onBackPressed() {

        if( proficiencyEdittedFlog==1 || langEdittedFlog==1 ) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfileKnownLang.super.onBackPressed();
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
            MyProfileKnownLang.super.onBackPressed();
    }
}
