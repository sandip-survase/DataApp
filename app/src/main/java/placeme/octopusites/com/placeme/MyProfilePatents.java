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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import placeme.octopusites.com.placeme.modal.Patents;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class MyProfilePatents extends AppCompatActivity {

    int patentcount=0;
    View addmorepatent;
    EditText title1,appno1,inventor1,issue1,filing1,url1,description1,title2,appno2,inventor2,issue2,filing2,url2,description2,title3,appno3,inventor3,issue3,filing3,url3,description3,title4,appno4,inventor4,issue4,filing4,url4,description4,title5,appno5,inventor5,issue5,filing5,url5,description5,title6,appno6,inventor6,issue6,filing6,url6,description6,title7,appno7,inventor7,issue7,filing7,url7,description7,title8,appno8,inventor8,issue8,filing8,url8,description8,title9,appno9,inventor9,issue9,filing9,url9,description9,title10,appno10,inventor10,issue10,filing10,url10,description10;
    Spinner patoffice1,patoffice2,patoffice3,patoffice4,patoffice5,patoffice6,patoffice7,patoffice8,patoffice9,patoffice10;
    RadioGroup radioGroupPatent1,radioGroupPatent2,radioGroupPatent3,radioGroupPatent4,radioGroupPatent5,radioGroupPatent6,radioGroupPatent7,radioGroupPatent8,radioGroupPatent9,radioGroupPatent10;
    RadioButton radioButtonIssued1,radioButtonPending1,radioButtonIssued2,radioButtonPending2,radioButtonIssued3,radioButtonPending3,radioButtonIssued4,radioButtonPending4,radioButtonIssued5,radioButtonPending5,radioButtonIssued6,radioButtonPending6,radioButtonIssued7,radioButtonPending7,radioButtonIssued8,radioButtonPending8,radioButtonIssued9,radioButtonPending9,radioButtonIssued10,radioButtonPending10;
    String stitle1="",sappno1="",sinventor1="",sissue1="",sfiling1="",surl1="",sdescription1="",stitle2="",sappno2="",sinventor2="",sissue2="",sfiling2="",surl2="",sdescription2="",stitle3="",sappno3="",sinventor3="",sissue3="",sfiling3="",surl3="",sdescription3="",stitle4="",sappno4="",sinventor4="",sissue4="",sfiling4="",surl4="",sdescription4="",stitle5="",sappno5="",sinventor5="",sissue5="",sfiling5="",surl5="",sdescription5="",stitle6="",sappno6="",sinventor6="",sissue6="",sfiling6="",surl6="",sdescription6="",stitle7="",sappno7="",sinventor7="",sissue7="",sfiling7="",surl7="",sdescription7="",stitle8="",sappno8="",sinventor8="",sissue8="",sfiling8="",surl8="",sdescription8="",stitle9="",sappno9="",sinventor9="",sissue9="",sfiling9="",surl9="",sdescription9="",stitle10="",sappno10="",sinventor10="",sissue10="",sfiling10="",surl10="",sdescription10="";
    String  enctitle1,encappno1,encinventor1,encissue1,encfiling1,encurl1,encdescription1,enctitle2,encappno2,encinventor2,encissue2,encfiling2,encurl2,encdescription2,enctitle3,encappno3,encinventor3,encissue3,encfiling3,encurl3,encdescription3,enctitle4,encappno4,encinventor4,encissue4,encfiling4,encurl4,encdescription4,enctitle5,encappno5,encinventor5,encissue5,encfiling5,encurl5,encdescription5,enctitle6,encappno6,encinventor6,encissue6,encfiling6,encurl6,encdescription6,enctitle7,encappno7,encinventor7,encissue7,encfiling7,encurl7,encdescription7,enctitle8,encappno8,encinventor8,encissue8,encfiling8,encurl8,encdescription8,enctitle9,encappno9,encinventor9,encissue9,encfiling9,encurl9,encdescription9,enctitle10,encappno10,encinventor10,encissue10,encfiling10,encurl10,encdescription10;
    String issuedorpending1="issued",issuedorpending2="issued",issuedorpending3="issued",issuedorpending4="issued",issuedorpending5="issued",issuedorpending6="issued",issuedorpending7="issued",issuedorpending8="issued",issuedorpending9="issued",issuedorpending10="issued";
    String encselectedCountry1,encselectedCountry2,encselectedCountry3,encselectedCountry4,encselectedCountry5,encselectedCountry6,encselectedCountry7,encselectedCountry8,encselectedCountry9,encselectedCountry10;
    String encissuedorpending1,encissuedorpending2,encissuedorpending3,encissuedorpending4,encissuedorpending5,encissuedorpending6,encissuedorpending7,encissuedorpending8,encissuedorpending9,encissuedorpending10;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username,role;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    //    private static String url_savepatents= "http://192.168.100.10/AESTest/SavePatents";
    View trash1selectionview,trash2selectionview,trash3selectionview,trash4selectionview,trash5selectionview,trash6selectionview,trash7selectionview,trash8selectionview,trash9selectionview,trash10selectionview;
    int edittedFlag=0,isInvalidDate=0;
    int d=0;
    StudentData s=new StudentData();
//    private static String url_getcountries = "http://192.168.100.10/AESTest/GetCountries";
    int countrycount=0;
    String countries[];
    List<String> countrieslist = new ArrayList<String>();
    String selectedCountry1="",selectedCountry2="",selectedCountry3="",selectedCountry4="",selectedCountry5="",selectedCountry6="",selectedCountry7="",selectedCountry8="",selectedCountry9="",selectedCountry10="";
    ArrayAdapter<String> dataAdapter;

    ArrayList<Patents> patentsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_patents);


        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Patents Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

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

        title1=(EditText)findViewById(R.id.title1);
        appno1=(EditText)findViewById(R.id.appno1);
        inventor1=(EditText)findViewById(R.id.inventor1);
        url1=(EditText)findViewById(R.id.url1);
        description1=(EditText)findViewById(R.id.description1);
        title2=(EditText)findViewById(R.id.title2);
        appno2=(EditText)findViewById(R.id.appno2);
        inventor2=(EditText)findViewById(R.id.inventor2);
        url2=(EditText)findViewById(R.id.url2);
        description2=(EditText)findViewById(R.id.description2);
        title3=(EditText)findViewById(R.id.title3);
        appno3=(EditText)findViewById(R.id.appno3);
        inventor3=(EditText)findViewById(R.id.inventor3);
        url3=(EditText)findViewById(R.id.url3);
        description3=(EditText)findViewById(R.id.description3);
        title4=(EditText)findViewById(R.id.title4);
        appno4=(EditText)findViewById(R.id.appno4);
        inventor4=(EditText)findViewById(R.id.inventor4);
        url4=(EditText)findViewById(R.id.url4);
        description4=(EditText)findViewById(R.id.description4);
        title5=(EditText)findViewById(R.id.title5);
        appno5=(EditText)findViewById(R.id.appno5);
        inventor5=(EditText)findViewById(R.id.inventor5);
        url5=(EditText)findViewById(R.id.url5);
        description5=(EditText)findViewById(R.id.description5);
        title6=(EditText)findViewById(R.id.title6);
        appno6=(EditText)findViewById(R.id.appno6);
        inventor6=(EditText)findViewById(R.id.inventor6);
        url6=(EditText)findViewById(R.id.url6);
        description6=(EditText)findViewById(R.id.description6);
        title7=(EditText)findViewById(R.id.title7);
        appno7=(EditText)findViewById(R.id.appno7);
        inventor7=(EditText)findViewById(R.id.inventor7);
        url7=(EditText)findViewById(R.id.url7);
        description7=(EditText)findViewById(R.id.description7);
        title8=(EditText)findViewById(R.id.title8);
        appno8=(EditText)findViewById(R.id.appno8);
        inventor8=(EditText)findViewById(R.id.inventor8);
        url8=(EditText)findViewById(R.id.url8);
        description8=(EditText)findViewById(R.id.description8);
        title9=(EditText)findViewById(R.id.title9);
        appno9=(EditText)findViewById(R.id.appno9);
        inventor9=(EditText)findViewById(R.id.inventor9);
        url9=(EditText)findViewById(R.id.url9);
        description9=(EditText)findViewById(R.id.description9);
        title10=(EditText)findViewById(R.id.title10);
        appno10=(EditText)findViewById(R.id.appno10);
        inventor10=(EditText)findViewById(R.id.inventor10);
        url10=(EditText)findViewById(R.id.url10);
        description10=(EditText)findViewById(R.id.description10);

        filing1=(EditText)findViewById(R.id.filing1);
        filing2=(EditText)findViewById(R.id.filing2);
        filing3=(EditText)findViewById(R.id.filing3);
        filing4=(EditText)findViewById(R.id.filing4);
        filing5=(EditText)findViewById(R.id.filing5);
        filing6=(EditText)findViewById(R.id.filing6);
        filing7=(EditText)findViewById(R.id.filing7);
        filing8=(EditText)findViewById(R.id.filing8);
        filing9=(EditText)findViewById(R.id.filing9);
        filing10=(EditText)findViewById(R.id.filing10);

        issue1=(EditText)findViewById(R.id.issue1);
        issue2=(EditText)findViewById(R.id.issue2);
        issue3=(EditText)findViewById(R.id.issue3);
        issue4=(EditText)findViewById(R.id.issue4);
        issue5=(EditText)findViewById(R.id.issue5);
        issue6=(EditText)findViewById(R.id.issue6);
        issue7=(EditText)findViewById(R.id.issue7);
        issue8=(EditText)findViewById(R.id.issue8);
        issue9=(EditText)findViewById(R.id.issue9);
        issue10=(EditText)findViewById(R.id.issue10);

        patoffice1=(Spinner)findViewById(R.id.patoffice1);
        patoffice2=(Spinner)findViewById(R.id.patoffice2);
        patoffice3=(Spinner)findViewById(R.id.patoffice3);
        patoffice4=(Spinner)findViewById(R.id.patoffice4);
        patoffice5=(Spinner)findViewById(R.id.patoffice5);
        patoffice6=(Spinner)findViewById(R.id.patoffice6);
        patoffice7=(Spinner)findViewById(R.id.patoffice7);
        patoffice8=(Spinner)findViewById(R.id.patoffice8);
        patoffice9=(Spinner)findViewById(R.id.patoffice9);
        patoffice10=(Spinner)findViewById(R.id.patoffice10);

        radioGroupPatent1=(RadioGroup)findViewById(R.id.radioGroupPatent1);
        radioGroupPatent2=(RadioGroup)findViewById(R.id.radioGroupPatent2);
        radioGroupPatent3=(RadioGroup)findViewById(R.id.radioGroupPatent3);
        radioGroupPatent4=(RadioGroup)findViewById(R.id.radioGroupPatent4);
        radioGroupPatent5=(RadioGroup)findViewById(R.id.radioGroupPatent5);
        radioGroupPatent6=(RadioGroup)findViewById(R.id.radioGroupPatent6);
        radioGroupPatent7=(RadioGroup)findViewById(R.id.radioGroupPatent7);
        radioGroupPatent8=(RadioGroup)findViewById(R.id.radioGroupPatent8);
        radioGroupPatent9=(RadioGroup)findViewById(R.id.radioGroupPatent9);
        radioGroupPatent10=(RadioGroup)findViewById(R.id.radioGroupPatent10);

        radioButtonIssued1=(RadioButton)findViewById(R.id.radioButtonIssued1) ;
        radioButtonIssued2=(RadioButton)findViewById(R.id.radioButtonIssued2) ;
        radioButtonIssued3=(RadioButton)findViewById(R.id.radioButtonIssued3) ;
        radioButtonIssued4=(RadioButton)findViewById(R.id.radioButtonIssued4) ;
        radioButtonIssued5=(RadioButton)findViewById(R.id.radioButtonIssued5) ;
        radioButtonIssued6=(RadioButton)findViewById(R.id.radioButtonIssued6) ;
        radioButtonIssued7=(RadioButton)findViewById(R.id.radioButtonIssued7) ;
        radioButtonIssued8=(RadioButton)findViewById(R.id.radioButtonIssued8) ;
        radioButtonIssued9=(RadioButton)findViewById(R.id.radioButtonIssued9) ;
        radioButtonIssued10=(RadioButton)findViewById(R.id.radioButtonIssued10) ;

        radioButtonPending1=(RadioButton)findViewById(R.id.radioButtonPending1) ;
        radioButtonPending2=(RadioButton)findViewById(R.id.radioButtonPending2) ;
        radioButtonPending3=(RadioButton)findViewById(R.id.radioButtonPending3) ;
        radioButtonPending4=(RadioButton)findViewById(R.id.radioButtonPending4) ;
        radioButtonPending5=(RadioButton)findViewById(R.id.radioButtonPending5) ;
        radioButtonPending6=(RadioButton)findViewById(R.id.radioButtonPending6) ;
        radioButtonPending7=(RadioButton)findViewById(R.id.radioButtonPending7) ;
        radioButtonPending8=(RadioButton)findViewById(R.id.radioButtonPending8) ;
        radioButtonPending9=(RadioButton)findViewById(R.id.radioButtonPending9) ;
        radioButtonPending10=(RadioButton)findViewById(R.id.radioButtonPending10) ;

        radioGroupPatent1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonIssued1:
                        issuedorpending1="issued";
                        filing1.setVisibility(View.GONE);
                        issue1.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButtonPending1:
                        filing1.setVisibility(View.VISIBLE);
                        issue1.setVisibility(View.GONE);
                        issuedorpending1="pending";
                        break;

                }
            }
        });
        radioGroupPatent2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonIssued2:
                        issuedorpending2="issued";
                        filing2.setVisibility(View.GONE);
                        issue2.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButtonPending2:
                        filing2.setVisibility(View.VISIBLE);
                        issue2.setVisibility(View.GONE);
                        issuedorpending2="pending";
                        break;
                }
            }
        });
        radioGroupPatent3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonIssued3:
                        issuedorpending3="issued";
                        filing3.setVisibility(View.GONE);
                        issue3.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButtonPending3:
                        filing3.setVisibility(View.VISIBLE);
                        issue3.setVisibility(View.GONE);
                        issuedorpending3="pending";
                        break;
                }
            }
        });
        radioGroupPatent4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonIssued4:
                        issuedorpending4="issued";
                        filing4.setVisibility(View.GONE);
                        issue4.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButtonPending4:
                        filing4.setVisibility(View.VISIBLE);
                        issue4.setVisibility(View.GONE);
                        issuedorpending4="pending";
                        break;

                }
            }
        });
        radioGroupPatent5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonIssued5:
                        issuedorpending5="issued";
                        filing5.setVisibility(View.GONE);
                        issue5.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButtonPending5:
                        filing5.setVisibility(View.VISIBLE);
                        issue5.setVisibility(View.GONE);
                        issuedorpending5="pending";
                        break;
                }
            }
        });
        radioGroupPatent6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonIssued6:
                        issuedorpending6="issued";
                        filing6.setVisibility(View.GONE);
                        issue6.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButtonPending6:
                        filing6.setVisibility(View.VISIBLE);
                        issue6.setVisibility(View.GONE);
                        issuedorpending6="pending";
                        break;
                }
            }
        });
        radioGroupPatent7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonIssued7:
                        issuedorpending7="issued";
                        filing7.setVisibility(View.GONE);
                        issue7.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButtonPending7:
                        filing7.setVisibility(View.VISIBLE);
                        issue7.setVisibility(View.GONE);
                        issuedorpending7="pending";
                        break;
                }
            }
        });
        radioGroupPatent8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonIssued8:
                        issuedorpending8="issued";
                        filing8.setVisibility(View.GONE);
                        issue8.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButtonPending8:
                        filing8.setVisibility(View.VISIBLE);
                        issue8.setVisibility(View.GONE);
                        issuedorpending8="pending";
                        break;
                }
            }
        });
        radioGroupPatent9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonIssued9:
                        issuedorpending9="issued";
                        filing9.setVisibility(View.GONE);
                        issue9.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButtonPending9:
                        filing9.setVisibility(View.VISIBLE);
                        issue9.setVisibility(View.GONE);
                        issuedorpending9="pending";
                        break;
                }
            }
        });
        radioGroupPatent10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonIssued10:
                        issuedorpending10="issued";
                        filing10.setVisibility(View.GONE);
                        issue10.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButtonPending10:
                        filing10.setVisibility(View.VISIBLE);
                        issue10.setVisibility(View.GONE);
                        issuedorpending10="pending";
                        break;
                }
            }
        });

        new GetCountries().execute();

        title1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        appno1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appno1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inventor1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventor1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        url1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                url1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        filing1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filing1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issue1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                issue1.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        title2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        appno2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appno2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inventor2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventor2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        url2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                url2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        filing2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filing2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issue2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                issue2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        title3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        appno3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appno3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inventor3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventor3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        url3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                url3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        filing3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filing3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issue3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                issue3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        title4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        appno4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appno4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inventor4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventor4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        url4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                url4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        filing4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filing4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issue4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                issue4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        title5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        appno5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appno5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inventor5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventor5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        url5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                url5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        filing5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filing5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issue5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                issue5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        title6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        appno6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appno6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inventor6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventor6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        url6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                url6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        filing6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filing6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issue6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                issue6.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        title7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        appno7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appno7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inventor7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventor7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        url7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                url7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        filing7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filing7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issue7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                issue7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        title8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        appno8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appno8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inventor8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventor8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        url8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                url8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        filing8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filing8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issue8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                issue8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        title9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title9.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        appno9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appno9.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inventor9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventor9.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        url9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                url9.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description9.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        filing9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filing9.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issue9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                issue9.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        title10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title10.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        appno10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appno10.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inventor10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inventor10.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        url10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                url10.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description10.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        filing10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filing10.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issue10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                issue10.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        filing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(filing1);
            }
        });
        filing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(filing2);
            }
        });
        filing3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(filing3);
            }
        });
        filing4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(filing4);
            }
        });
        filing5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(filing5);
            }
        });
        filing6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(filing6);
            }
        });
        filing7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(filing7);
            }
        });
        filing8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(filing8);
            }
        });
        filing9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(filing9);
            }
        });
        filing10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(filing10);
            }
        });

        issue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(issue1);
            }
        });
        issue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(issue2);
            }
        });
        issue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(issue3);
            }
        });
        issue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(issue4);
            }
        });
        issue5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(issue5);
            }
        });
        issue6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(issue6);
            }
        });
        issue7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(issue7);
            }
        });
        issue8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(issue8);
            }
        });
        issue9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(issue9);
            }
        });
        issue10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(issue10);
            }
        });

        TextView patenttxt=(TextView)findViewById(R.id.patenttxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        patenttxt.setTypeface(custom_font1);

        addmorepatent=(View)findViewById(R.id.addmorepatent);
        addmorepatent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(patentcount==0)
                {
                    if(title1.getText().toString()!=null && appno1.getText().toString()!=null  && inventor1.getText().toString()!=null && url1.getText().toString()!=null && description1.getText().toString()!=null && issue1.getText().toString()!=null )
                    {
                        if(!title1.getText().toString().equals("")&& !appno1.getText().toString().equals("")&& !inventor1.getText().toString().equals("") && !url1.getText().toString().equals("") && !description1.getText().toString().equals("") && !issue1.getText().toString().equals("") && !patoffice1.getSelectedItem().toString().equals("- Select Patent Office -"))
                        {
                            View v=(View)findViewById(R.id.patentline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.patentrl2);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            patentcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfilePatents.this, "Please fill the first Honors", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfilePatents.this, "Please fill the first Honors", Toast.LENGTH_SHORT).show();


                }
                else if(patentcount==1)
                {
                    if(title2.getText().toString()!=null && appno2.getText().toString()!=null  && inventor2.getText().toString()!=null && url2.getText().toString()!=null && description2.getText().toString()!=null && issue2.getText().toString()!=null )
                    {
                        if(!title2.getText().toString().equals("")&& !appno2.getText().toString().equals("")&& !inventor2.getText().toString().equals("") && !url2.getText().toString().equals("") && !description2.getText().toString().equals("") && !issue2.getText().toString().equals("") &&  !patoffice2.getSelectedItem().toString().equals("- Select Patent Office -"))
                        {

                            View v=(View)findViewById(R.id.patentline2);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.patentrl3);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            patentcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfilePatents.this, "Please fill the Second Honors", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfilePatents.this, "Please fill the Second Honors", Toast.LENGTH_SHORT).show();

                }
                else if(patentcount==2)
                {
                    if(title3.getText().toString()!=null && appno3.getText().toString()!=null  && inventor3.getText().toString()!=null && url3.getText().toString()!=null && description3.getText().toString()!=null && issue3.getText().toString()!=null )
                    {
                        if(!title3.getText().toString().equals("")&& !appno3.getText().toString().equals("")&& !inventor3.getText().toString().equals("") && !url3.getText().toString().equals("") && !description3.getText().toString().equals("") && !issue3.getText().toString().equals("")&& !patoffice3.getSelectedItem().toString().equals("- Select Patent Office -"))
                        {

                            View v=(View)findViewById(R.id.patentline3);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.patentrl4);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            patentcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfilePatents.this, "Please fill the Third Honors", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfilePatents.this, "Please fill the Third Honors", Toast.LENGTH_SHORT).show();

                }
                else  if(patentcount==3)
                {
                    if(title4.getText().toString()!=null && appno4.getText().toString()!=null  && inventor4.getText().toString()!=null && url4.getText().toString()!=null && description4.getText().toString()!=null && issue4.getText().toString()!=null )
                    {
                        if(!title4.getText().toString().equals("")&& !appno4.getText().toString().equals("")&& !inventor4.getText().toString().equals("") && !url4.getText().toString().equals("") && !description4.getText().toString().equals("") && !issue4.getText().toString().equals("")&& !patoffice4.getSelectedItem().toString().equals("- Select Patent Office -"))
                        {
                            View v=(View)findViewById(R.id.patentline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.patentrl5);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            patentcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfilePatents.this, "Please fill the Fourth Honors", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfilePatents.this, "Please fill the Fourth Honors", Toast.LENGTH_SHORT).show();


                }
                else if(patentcount==4)
                {
                    if(title5.getText().toString()!=null && appno5.getText().toString()!=null  && inventor5.getText().toString()!=null && url5.getText().toString()!=null && description5.getText().toString()!=null && issue5.getText().toString()!=null )
                    {
                        if(!title5.getText().toString().equals("")&& !appno5.getText().toString().equals("")&& !inventor5.getText().toString().equals("") && !url5.getText().toString().equals("") && !description5.getText().toString().equals("") && !issue5.getText().toString().equals("")&& !patoffice5.getSelectedItem().toString().equals("- Select Patent Office -"))
                        {
                            View v=(View)findViewById(R.id.patentline5);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.patentrl6);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            patentcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfilePatents.this, "Please fill the Fifth Honors", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfilePatents.this, "Please fill the Fifth Honors", Toast.LENGTH_SHORT).show();



                }
                else if(patentcount==5)
                {
                    if(title6.getText().toString()!=null && appno6.getText().toString()!=null  && inventor6.getText().toString()!=null && url6.getText().toString()!=null && description6.getText().toString()!=null && issue6.getText().toString()!=null )
                    {
                        if(!title6.getText().toString().equals("")&& !appno6.getText().toString().equals("")&& !inventor6.getText().toString().equals("") && !url6.getText().toString().equals("") && !description6.getText().toString().equals("") && !issue6.getText().toString().equals("")&& !patoffice6.getSelectedItem().toString().equals("- Select Patent Office -"))
                        {
                            View v=(View)findViewById(R.id.patentline6);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.patentrl7);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            patentcount++;

                        }
                        else
                        {
                            Toast.makeText(MyProfilePatents.this, "Please fill the Sixth Honors", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfilePatents.this, "Please fill the Sixth Honors", Toast.LENGTH_SHORT).show();


                }
                else if(patentcount==6)
                {
                    if(title7.getText().toString()!=null && appno7.getText().toString()!=null  && inventor7.getText().toString()!=null && url7.getText().toString()!=null && description7.getText().toString()!=null && issue7.getText().toString()!=null )
                    {
                        if(!title7.getText().toString().equals("")&& !appno7.getText().toString().equals("")&& !inventor7.getText().toString().equals("") && !url7.getText().toString().equals("") && !description7.getText().toString().equals("") && !issue7.getText().toString().equals("")&& !patoffice7.getSelectedItem().toString().equals("- Select Patent Office -"))
                        {
                            View v=(View)findViewById(R.id.patentline7);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.patentrl8);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            patentcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfilePatents.this, "Please fill the Seventh Honors", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfilePatents.this, "Please fill the Seventh Honors", Toast.LENGTH_SHORT).show();


                }
                else if(patentcount==7)
                {
                    if(title8.getText().toString()!=null && appno8.getText().toString()!=null  && inventor8.getText().toString()!=null && url8.getText().toString()!=null && description8.getText().toString()!=null && issue8.getText().toString()!=null )
                    {
                        if(!title8.getText().toString().equals("")&& !appno8.getText().toString().equals("")&& !inventor8.getText().toString().equals("") && !url8.getText().toString().equals("") && !description8.getText().toString().equals("") && !issue8.getText().toString().equals("")&& !patoffice8.getSelectedItem().toString().equals("- Select Patent Office -"))
                        {
                            View v=(View)findViewById(R.id.patentline8);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.patentrl9);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            patentcount++;
                        }
                        else
                        {
                            Toast.makeText(MyProfilePatents.this, "Please fill the Eighth Honors", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfilePatents.this, "Please fill the Eighth Honors", Toast.LENGTH_SHORT).show();



                }
                else if(patentcount==8)
                {
                    if(title9.getText().toString()!=null && appno9.getText().toString()!=null  && inventor9.getText().toString()!=null && url9.getText().toString()!=null && description9.getText().toString()!=null && issue9.getText().toString()!=null )
                    {
                        if(!title9.getText().toString().equals("")&& !appno9.getText().toString().equals("")&& !inventor9.getText().toString().equals("") && !url9.getText().toString().equals("") && !description9.getText().toString().equals("") && !issue9.getText().toString().equals("") && !patoffice9.getSelectedItem().toString().equals("- Select Patent Office -"))
                        {
                            View v=(View)findViewById(R.id.patentline9);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1=(RelativeLayout)findViewById(R.id.patentrl10);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            patentcount++;




                            TextView t=(TextView)findViewById(R.id.addmorepatenttxt);
                            ImageView i=(ImageView)findViewById(R.id.addmorepatentimg);
                            addmorepatent.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);
                        }
                        else
                        {
                            Toast.makeText(MyProfilePatents.this, "Please fill the Nineth Honors", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(MyProfilePatents.this, "Please fill the Nineth Honors", Toast.LENGTH_SHORT).show();





                }

            }
        });
        ScrollView myprofileintroscrollview=(ScrollView)findViewById(R.id.myprofilepatents);
        disableScrollbars(myprofileintroscrollview);



        stitle1=s.getPtitle1();
        sappno1=s.getPappno1();
        sinventor1=s.getPinventor1();
        sissue1=s.getPissue1();
        sfiling1=s.getPfiling1();
        surl1=s.getPurl1();
        sdescription1=s.getPdescription1();
        selectedCountry1=s.getPselectedcountry1();
        if(s.getIssuedorpending1()!=null)
            issuedorpending1=s.getIssuedorpending1();
        stitle2=s.getPtitle2();
        sappno2=s.getPappno2();
        sinventor2=s.getPinventor2();
        sissue2=s.getPissue2();
        sfiling2=s.getPfiling2();
        surl2=s.getPurl2();
        sdescription2=s.getPdescription2();
        selectedCountry2=s.getPselectedcountry2();
        if(s.getIssuedorpending2()!=null)
            issuedorpending2=s.getIssuedorpending2();
        stitle3=s.getPtitle3();
        sappno3=s.getPappno3();
        sinventor3=s.getPinventor3();
        sissue3=s.getPissue3();
        sfiling3=s.getPfiling3();
        surl3=s.getPurl3();
        sdescription3=s.getPdescription3();
        selectedCountry3=s.getPselectedcountry3();
        if(s.getIssuedorpending3()!=null)
            issuedorpending3=s.getIssuedorpending3();
        stitle4=s.getPtitle4();
        sappno4=s.getPappno4();
        sinventor4=s.getPinventor4();
        sissue4=s.getPissue4();
        sfiling4=s.getPfiling4();
        surl4=s.getPurl4();
        sdescription4=s.getPdescription4();
        selectedCountry4=s.getPselectedcountry4();
        if(s.getIssuedorpending4()!=null)
            issuedorpending4=s.getIssuedorpending4();
        stitle5=s.getPtitle5();
        sappno5=s.getPappno5();
        sinventor5=s.getPinventor5();
        sissue5=s.getPissue5();
        sfiling5=s.getPfiling5();
        surl5=s.getPurl5();
        sdescription5=s.getPdescription5();
        selectedCountry5=s.getPselectedcountry5();
        if(s.getIssuedorpending5()!=null)
            issuedorpending5=s.getIssuedorpending5();
        stitle6=s.getPtitle6();
        sappno6=s.getPappno6();
        sinventor6=s.getPinventor6();
        sissue6=s.getPissue6();
        sfiling6=s.getPfiling6();
        surl6=s.getPurl6();
        sdescription6=s.getPdescription6();
        selectedCountry6=s.getPselectedcountry6();
        if(s.getIssuedorpending6()!=null)
            issuedorpending6=s.getIssuedorpending6();
        stitle7=s.getPtitle7();
        sappno7=s.getPappno7();
        sinventor7=s.getPinventor7();
        sissue7=s.getPissue7();
        sfiling7=s.getPfiling7();
        surl7=s.getPurl7();
        sdescription7=s.getPdescription7();
        selectedCountry7=s.getPselectedcountry7();
        if(s.getIssuedorpending7()!=null)
            issuedorpending7=s.getIssuedorpending7();
        stitle8=s.getPtitle8();
        sappno8=s.getPappno8();
        sinventor8=s.getPinventor8();
        sissue8=s.getPissue8();
        sfiling8=s.getPfiling8();
        surl8=s.getPurl8();
        sdescription8=s.getPdescription8();
        selectedCountry8=s.getPselectedcountry8();
        if(s.getIssuedorpending8()!=null)
            issuedorpending8=s.getIssuedorpending8();
        stitle9=s.getPtitle9();
        sappno9=s.getPappno9();
        sinventor9=s.getPinventor9();
        sissue9=s.getPissue9();
        sfiling9=s.getPfiling9();
        surl9=s.getPurl9();
        sdescription9=s.getPdescription9();
        selectedCountry9=s.getPselectedcountry9();
        if(s.getIssuedorpending9()!=null)
            issuedorpending9=s.getIssuedorpending9();
        stitle10=s.getPtitle10();
        sappno10=s.getPappno10();
        sinventor10=s.getPinventor10();
        sissue10=s.getPissue10();
        sfiling10=s.getPfiling10();
        surl10=s.getPurl10();
        sdescription10=s.getPdescription10();
        selectedCountry10=s.getPselectedcountry10();
        if(s.getIssuedorpending10()!=null)
            issuedorpending10=s.getIssuedorpending10();

        if(stitle1!=null) {
            if (stitle1.length() > 2) {
                title1.setText(stitle1);
                appno1.setText(sappno1);
                inventor1.setText(sinventor1);
                url1.setText(surl1);
                description1.setText(sdescription1);
                if (issuedorpending1.equals("issued")) {
                    radioButtonIssued1.setChecked(true);
                    issue1.setText(sissue1);
                } else {
                    radioButtonPending1.setChecked(true);
                    filing1.setText(sfiling1);
                }
            }
        }
        if(stitle2!=null) {
            if (stitle2.length() > 2) {
                title2.setText(stitle2);
                appno2.setText(sappno2);
                inventor2.setText(sinventor2);
                url2.setText(surl2);
                description2.setText(sdescription2);
                if (issuedorpending2.equals("issued")) {
                    radioButtonIssued2.setChecked(true);
                    issue2.setText(sissue2);
                } else {
                    radioButtonPending2.setChecked(true);
                    filing2.setText(sfiling2);
                }

                View v = (View) findViewById(R.id.patentline1);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.patentrl2);
                relativeLayout1.setVisibility(View.VISIBLE);
                patentcount++;

            }
        }
        if(stitle3!=null) {
            if (stitle3.length() > 2) {
                title3.setText(stitle3);
                appno3.setText(sappno3);
                inventor3.setText(sinventor3);
                url3.setText(surl3);
                description3.setText(sdescription3);
                if (issuedorpending3.equals("issued")) {
                    radioButtonIssued3.setChecked(true);
                    issue3.setText(sissue3);
                } else {
                    radioButtonPending3.setChecked(true);
                    filing3.setText(sfiling3);
                }
                View v = (View) findViewById(R.id.patentline2);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.patentrl3);
                relativeLayout1.setVisibility(View.VISIBLE);
                patentcount++;

            }
        }
        if(stitle4!=null) {
            if (stitle4.length() > 2) {
                title4.setText(stitle4);
                appno4.setText(sappno4);
                inventor4.setText(sinventor4);
                url4.setText(surl4);
                description4.setText(sdescription4);
                if (issuedorpending4.equals("issued")) {
                    radioButtonIssued4.setChecked(true);
                    issue4.setText(sissue4);
                } else {
                    radioButtonPending4.setChecked(true);
                    filing4.setText(sfiling4);
                }
                View v = (View) findViewById(R.id.patentline3);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.patentrl4);
                relativeLayout1.setVisibility(View.VISIBLE);
                patentcount++;

            }
        }
        if(stitle5!=null) {
            if (stitle5.length() > 2) {
                title5.setText(stitle5);
                appno5.setText(sappno5);
                inventor5.setText(sinventor5);
                url5.setText(surl5);
                description5.setText(sdescription5);
                if (issuedorpending5.equals("issued")) {
                    radioButtonIssued5.setChecked(true);
                    issue5.setText(sissue5);
                } else {
                    radioButtonPending5.setChecked(true);
                    filing5.setText(sfiling5);
                }
                View v = (View) findViewById(R.id.patentline4);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.patentrl5);
                relativeLayout1.setVisibility(View.VISIBLE);
                patentcount++;

            }
        }
        if(stitle6!=null) {
            if (stitle6.length() > 2) {
                title6.setText(stitle6);
                appno6.setText(sappno6);
                inventor6.setText(sinventor6);
                url6.setText(surl6);
                description6.setText(sdescription6);
                if (issuedorpending6.equals("issued")) {
                    radioButtonIssued6.setChecked(true);
                    issue6.setText(sissue6);
                } else {
                    radioButtonPending6.setChecked(true);
                    filing6.setText(sfiling6);
                }
                View v = (View) findViewById(R.id.patentline5);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.patentrl6);
                relativeLayout1.setVisibility(View.VISIBLE);
                patentcount++;

            }
        }
        if(stitle7!=null) {
            if (stitle7.length() > 2) {
                title7.setText(stitle7);
                appno7.setText(sappno7);
                inventor7.setText(sinventor7);
                url7.setText(surl7);
                description7.setText(sdescription7);
                if (issuedorpending7.equals("issued")) {
                    radioButtonIssued7.setChecked(true);
                    issue7.setText(sissue7);
                } else {
                    radioButtonPending7.setChecked(true);
                    filing7.setText(sfiling7);
                }
                View v = (View) findViewById(R.id.patentline6);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.patentrl7);
                relativeLayout1.setVisibility(View.VISIBLE);
                patentcount++;

            }
        }
        if(stitle8!=null) {
            if (stitle8.length() > 2) {
                title8.setText(stitle8);
                appno8.setText(sappno8);
                inventor8.setText(sinventor8);
                url8.setText(surl8);
                description8.setText(sdescription8);
                if (issuedorpending8.equals("issued")) {
                    radioButtonIssued8.setChecked(true);
                    issue8.setText(sissue8);
                } else {
                    radioButtonPending8.setChecked(true);
                    filing8.setText(sfiling8);
                }
                View v = (View) findViewById(R.id.patentline7);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.patentrl8);
                relativeLayout1.setVisibility(View.VISIBLE);
                patentcount++;

            }
        }
        if(stitle9!=null) {
            if (stitle9.length() > 2) {
                title9.setText(stitle9);
                appno9.setText(sappno9);
                inventor9.setText(sinventor9);
                url9.setText(surl9);
                description9.setText(sdescription9);
                if (issuedorpending9.equals("issued")) {
                    radioButtonIssued9.setChecked(true);
                    issue9.setText(sissue9);
                } else {
                    radioButtonPending9.setChecked(true);
                    filing9.setText(sfiling9);
                }
                View v = (View) findViewById(R.id.patentline8);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.patentrl9);
                relativeLayout1.setVisibility(View.VISIBLE);
                patentcount++;

            }
        }
        if(stitle10!=null) {
            if (stitle10.length() > 2) {
                title10.setText(stitle10);
                appno10.setText(sappno10);
                inventor10.setText(sinventor10);
                url10.setText(surl10);
                description10.setText(sdescription10);
                if (issuedorpending10.equals("issued")) {
                    radioButtonIssued10.setChecked(true);
                    issue10.setText(sissue10);
                } else {
                    radioButtonPending10.setChecked(true);
                    filing10.setText(sfiling10);
                }
                View v = (View) findViewById(R.id.patentline9);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.patentrl10);
                relativeLayout1.setVisibility(View.VISIBLE);
                patentcount++;

                TextView t = (TextView) findViewById(R.id.addmorepatenttxt);
                ImageView i = (ImageView) findViewById(R.id.addmorepatentimg);
                addmorepatent.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                i.setVisibility(View.GONE);

            }
        }

        edittedFlag=0;
    }
    void showDeletDialog()
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this patent ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag=1;
                                deletePatent();
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
    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }
    void deletePatent()
    {
        View v = (View) findViewById(R.id.patentline9);

        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View) findViewById(R.id.patentline9);
            v1.setVisibility(View.GONE);

            RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.patentrl10);
            relativeLayout.setVisibility(View.GONE);

            patentcount--;

            TextView t=(TextView)findViewById(R.id.addmorepatenttxt);
            ImageView i=(ImageView)findViewById(R.id.addmorepatentimg);
            addmorepatent.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        }
        else
        {
            v = (View) findViewById(R.id.patentline8);
            if (v.getVisibility() == View.VISIBLE) {

                View v1 = (View) findViewById(R.id.patentline8);
                v1.setVisibility(View.GONE);

                RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.patentrl9);
                relativeLayout.setVisibility(View.GONE);

                patentcount--;


            }
            else
            {
                v = (View) findViewById(R.id.patentline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View) findViewById(R.id.patentline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.patentrl8);
                    relativeLayout.setVisibility(View.GONE);

                    patentcount--;

                }
                else
                {
                    v = (View) findViewById(R.id.patentline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View) findViewById(R.id.patentline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.patentrl7);
                        relativeLayout.setVisibility(View.GONE);

                        patentcount--;

                    }
                    else
                    {
                        v = (View) findViewById(R.id.patentline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) findViewById(R.id.patentline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.patentrl6);
                            relativeLayout.setVisibility(View.GONE);

                            patentcount--;

                        }
                        else
                        {
                            v = (View) findViewById(R.id.patentline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) findViewById(R.id.patentline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.patentrl5);
                                relativeLayout.setVisibility(View.GONE);

                                patentcount--;

                            }
                            else
                            {
                                v = (View) findViewById(R.id.patentline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) findViewById(R.id.patentline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.patentrl4);
                                    relativeLayout.setVisibility(View.GONE);

                                    patentcount--;

                                }
                                else
                                {
                                    v = (View) findViewById(R.id.patentline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) findViewById(R.id.patentline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.patentrl3);
                                        relativeLayout.setVisibility(View.GONE);

                                        patentcount--;

                                    }
                                    else
                                    {
                                        v = (View) findViewById(R.id.patentline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1 = (View) findViewById(R.id.patentline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.patentrl2);
                                            relativeLayout.setVisibility(View.GONE);

                                            patentcount--;

                                        }
                                        else
                                        {
                                            title1.setText("");
                                            appno1.setText("");
                                            patoffice1.setSelection(0);
                                            inventor1.setText("");
                                            issue1.setText("");
                                            filing1.setText("");
                                            url1.setText("");
                                            description1.setText("");
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
            title10.setText("");
            appno10.setText("");
            patoffice10.setSelection(0);
            inventor10.setText("");
            issue10.setText("");
            filing10.setText("");
            url10.setText("");
            description10.setText("");
        }
        else if(d==9)
        {
            stitle10=title10.getText().toString();
            sappno10=appno10.getText().toString();
            selectedCountry10=patoffice10.getSelectedItem().toString();
            sinventor10=inventor10.getText().toString();
            sissue10=issue10.getText().toString();
            sfiling10=filing10.getText().toString();
            surl10=url10.getText().toString();
            sdescription10=description10.getText().toString();


            stitle9=stitle10;
            sappno9=sappno10;
            selectedCountry9=selectedCountry10;
            sinventor9=sinventor10;
            sissue9=sissue10;
            sfiling9=sfiling10;
            surl9=surl10;
            sdescription9=sdescription10;

            title9.setText(stitle9);
            appno9.setText(sappno9);
            patoffice9.setSelection(dataAdapter.getPosition(selectedCountry9));
            inventor9.setText(sinventor9);
            issue9.setText(sissue9);
            filing9.setText(sfiling9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            if(issuedorpending10.equals("pending"))
                radioButtonPending9.setChecked(true);
            else
                radioButtonIssued9.setChecked(true);

            title10.setText("");
            appno10.setText("");
            patoffice10.setSelection(0);
            inventor10.setText("");
            issue10.setText("");
            filing10.setText("");
            url10.setText("");
            description10.setText("");
            radioButtonIssued10.setChecked(true);
            radioButtonPending10.setChecked(false);




        }
        else if(d==8)
        {
            stitle10=title10.getText().toString();
            sappno10=appno10.getText().toString();
            selectedCountry10=patoffice10.getSelectedItem().toString();
            sinventor10=inventor10.getText().toString();
            sissue10=issue10.getText().toString();
            sfiling10=filing10.getText().toString();
            surl10=url10.getText().toString();
            sdescription10=description10.getText().toString();

            stitle9=title9.getText().toString();
            sappno9=appno9.getText().toString();
            selectedCountry9=patoffice9.getSelectedItem().toString();
            sinventor9=inventor9.getText().toString();
            sissue9=issue9.getText().toString();
            sfiling9=filing9.getText().toString();
            surl9=url9.getText().toString();
            sdescription9=description9.getText().toString();


            stitle8=stitle9;
            sappno8=sappno9;
            selectedCountry8=selectedCountry9;
            sinventor8=sinventor9;
            sissue8=sissue9;
            sfiling8=sfiling9;
            surl8=surl9;
            sdescription8=sdescription9;

            title8.setText(stitle8);
            appno8.setText(sappno8);
            patoffice8.setSelection(dataAdapter.getPosition(selectedCountry8));
            inventor8.setText(sinventor8);
            issue8.setText(sissue8);
            filing8.setText(sfiling8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            if(issuedorpending9.equals("pending"))
                radioButtonPending8.setChecked(true);
            else
                radioButtonIssued8.setChecked(true);

            title9.setText("");
            appno9.setText("");
            patoffice9.setSelection(0);
            inventor9.setText("");
            issue9.setText("");
            filing9.setText("");
            url9.setText("");
            description9.setText("");
            radioButtonIssued9.setChecked(true);
            radioButtonPending9.setChecked(false);




            stitle9=stitle10;
            sappno9=sappno10;
            selectedCountry9=selectedCountry10;
            sinventor9=sinventor10;
            sissue9=sissue10;
            sfiling9=sfiling10;
            surl9=surl10;
            sdescription9=sdescription10;


            title9.setText(stitle9);
            appno9.setText(sappno9);
            patoffice9.setSelection(dataAdapter.getPosition(selectedCountry9));
            inventor9.setText(sinventor9);
            issue9.setText(sissue9);
            filing9.setText(sfiling9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            if(issuedorpending10.equals("pending"))
                radioButtonPending9.setChecked(true);
            else
                radioButtonIssued9.setChecked(true);

            title10.setText("");
            appno10.setText("");
            patoffice10.setSelection(0);
            inventor10.setText("");
            issue10.setText("");
            filing10.setText("");
            url10.setText("");
            description10.setText("");
            radioButtonIssued10.setChecked(true);
            radioButtonPending10.setChecked(false);



        }
        else if(d==7)
        {
            stitle10=title10.getText().toString();
            sappno10=appno10.getText().toString();
            selectedCountry10=patoffice10.getSelectedItem().toString();
            sinventor10=inventor10.getText().toString();
            sissue10=issue10.getText().toString();
            sfiling10=filing10.getText().toString();
            surl10=url10.getText().toString();
            sdescription10=description10.getText().toString();
            stitle9=title9.getText().toString();
            sappno9=appno9.getText().toString();
            selectedCountry9=patoffice9.getSelectedItem().toString();
            sinventor9=inventor9.getText().toString();
            sissue9=issue9.getText().toString();
            sfiling9=filing9.getText().toString();
            surl9=url9.getText().toString();
            sdescription9=description9.getText().toString();
            stitle8=title8.getText().toString();
            sappno8=appno8.getText().toString();
            selectedCountry8=patoffice8.getSelectedItem().toString();
            sinventor8=inventor8.getText().toString();
            sissue8=issue8.getText().toString();
            sfiling8=filing8.getText().toString();
            surl8=url8.getText().toString();
            sdescription8=description8.getText().toString();

            stitle7=stitle8;
            sappno7=sappno8;
            selectedCountry7=selectedCountry8;
            sinventor7=sinventor8;
            sissue7=sissue8;
            sfiling7=sfiling8;
            surl7=surl8;
            sdescription7=sdescription8;


            title7.setText(stitle7);
            appno7.setText(sappno7);
            patoffice7.setSelection(dataAdapter.getPosition(selectedCountry7));
            inventor7.setText(sinventor7);
            issue7.setText(sissue7);
            filing7.setText(sfiling7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            if(issuedorpending8.equals("pending"))
                radioButtonPending7.setChecked(true);
            else
                radioButtonIssued7.setChecked(true);

            title8.setText("");
            appno8.setText("");
            patoffice8.setSelection(0);
            inventor8.setText("");
            issue8.setText("");
            filing8.setText("");
            url8.setText("");
            description8.setText("");
            radioButtonIssued8.setChecked(true);
            radioButtonPending8.setChecked(false);



            stitle8=stitle9;
            sappno8=sappno9;
            selectedCountry8=selectedCountry9;
            sinventor8=sinventor9;
            sissue8=sissue9;
            sfiling8=sfiling9;
            surl8=surl9;
            sdescription8=sdescription9;

            title8.setText(stitle8);
            appno8.setText(sappno8);
            patoffice8.setSelection(dataAdapter.getPosition(selectedCountry8));
            inventor8.setText(sinventor8);
            issue8.setText(sissue8);
            filing8.setText(sfiling8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            if(issuedorpending9.equals("pending"))
                radioButtonPending8.setChecked(true);
            else
                radioButtonIssued8.setChecked(true);

            title9.setText("");
            appno9.setText("");
            patoffice9.setSelection(0);
            inventor9.setText("");
            issue9.setText("");
            filing9.setText("");
            url9.setText("");
            description9.setText("");
            radioButtonIssued9.setChecked(true);
            radioButtonPending9.setChecked(false);




            stitle9=stitle10;
            sappno9=sappno10;
            selectedCountry9=selectedCountry10;
            sinventor9=sinventor10;
            sissue9=sissue10;
            sfiling9=sfiling10;
            surl9=surl10;
            sdescription9=sdescription10;


            title9.setText(stitle9);
            appno9.setText(sappno9);
            patoffice9.setSelection(dataAdapter.getPosition(selectedCountry9));
            inventor9.setText(sinventor9);
            issue9.setText(sissue9);
            filing9.setText(sfiling9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            if(issuedorpending10.equals("pending"))
                radioButtonPending9.setChecked(true);
            else
                radioButtonIssued9.setChecked(true);

            title10.setText("");
            appno10.setText("");
            patoffice10.setSelection(0);
            inventor10.setText("");
            issue10.setText("");
            filing10.setText("");
            url10.setText("");
            description10.setText("");
            radioButtonIssued10.setChecked(true);
            radioButtonPending10.setChecked(false);



        }
        else if(d==6)
        {
            stitle10=title10.getText().toString();
            sappno10=appno10.getText().toString();
            selectedCountry10=patoffice10.getSelectedItem().toString();
            sinventor10=inventor10.getText().toString();
            sissue10=issue10.getText().toString();
            sfiling10=filing10.getText().toString();
            surl10=url10.getText().toString();
            sdescription10=description10.getText().toString();
            stitle9=title9.getText().toString();
            sappno9=appno9.getText().toString();
            selectedCountry9=patoffice9.getSelectedItem().toString();
            sinventor9=inventor9.getText().toString();
            sissue9=issue9.getText().toString();
            sfiling9=filing9.getText().toString();
            surl9=url9.getText().toString();
            sdescription9=description9.getText().toString();
            stitle8=title8.getText().toString();
            sappno8=appno8.getText().toString();
            selectedCountry8=patoffice8.getSelectedItem().toString();
            sinventor8=inventor8.getText().toString();
            sissue8=issue8.getText().toString();
            sfiling8=filing8.getText().toString();
            surl8=url8.getText().toString();
            sdescription8=description8.getText().toString();
            stitle7=title7.getText().toString();
            sappno7=appno7.getText().toString();
            selectedCountry7=patoffice7.getSelectedItem().toString();
            sinventor7=inventor7.getText().toString();
            sissue7=issue7.getText().toString();
            sfiling7=filing7.getText().toString();
            surl7=url7.getText().toString();
            sdescription7=description7.getText().toString();

            stitle6=stitle7;
            sappno6=sappno7;
            selectedCountry6=selectedCountry7;
            sinventor6=sinventor7;
            sissue6=sissue7;
            sfiling6=sfiling7;
            surl6=surl7;
            sdescription6=sdescription7;


            title6.setText(stitle6);
            appno6.setText(sappno6);
            patoffice6.setSelection(dataAdapter.getPosition(selectedCountry6));
            inventor6.setText(sinventor6);
            issue6.setText(sissue6);
            filing6.setText(sfiling6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            if(issuedorpending7.equals("pending"))
                radioButtonPending6.setChecked(true);
            else
                radioButtonIssued6.setChecked(true);

            title7.setText("");
            appno7.setText("");
            patoffice7.setSelection(0);
            inventor7.setText("");
            issue7.setText("");
            filing7.setText("");
            url7.setText("");
            description7.setText("");
            radioButtonIssued7.setChecked(true);
            radioButtonPending7.setChecked(false);


            stitle7=stitle8;
            sappno7=sappno8;
            selectedCountry7=selectedCountry8;
            sinventor7=sinventor8;
            sissue7=sissue8;
            sfiling7=sfiling8;
            surl7=surl8;
            sdescription7=sdescription8;

            title7.setText(stitle7);
            appno7.setText(sappno7);
            patoffice7.setSelection(dataAdapter.getPosition(selectedCountry7));
            inventor7.setText(sinventor7);
            issue7.setText(sissue7);
            filing7.setText(sfiling7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            if(issuedorpending8.equals("pending"))
                radioButtonPending7.setChecked(true);
            else
                radioButtonIssued7.setChecked(true);

            title8.setText("");
            appno8.setText("");
            patoffice8.setSelection(0);
            inventor8.setText("");
            issue8.setText("");
            filing8.setText("");
            url8.setText("");
            description8.setText("");
            radioButtonIssued8.setChecked(true);
            radioButtonPending8.setChecked(false);




            stitle8=stitle9;
            sappno8=sappno9;
            selectedCountry8=selectedCountry9;
            sinventor8=sinventor9;
            sissue8=sissue9;
            sfiling8=sfiling9;
            surl8=surl9;
            sdescription8=sdescription9;


            title8.setText(stitle8);
            appno8.setText(sappno8);
            patoffice8.setSelection(dataAdapter.getPosition(selectedCountry8));
            inventor8.setText(sinventor8);
            issue8.setText(sissue8);
            filing8.setText(sfiling8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            if(issuedorpending9.equals("pending"))
                radioButtonPending8.setChecked(true);
            else
                radioButtonIssued8.setChecked(true);

            title9.setText("");
            appno9.setText("");
            patoffice9.setSelection(0);
            inventor9.setText("");
            issue9.setText("");
            filing9.setText("");
            url9.setText("");
            description9.setText("");
            radioButtonIssued9.setChecked(true);
            radioButtonPending9.setChecked(false);


            stitle9=stitle10;
            sappno9=sappno10;
            selectedCountry9=selectedCountry10;
            sinventor9=sinventor10;
            sissue9=sissue10;
            sfiling9=sfiling10;
            surl9=surl10;
            sdescription9=sdescription10;


            title9.setText(stitle9);
            appno9.setText(sappno9);
            patoffice9.setSelection(dataAdapter.getPosition(selectedCountry9));
            inventor9.setText(sinventor9);
            issue9.setText(sissue9);
            filing9.setText(sfiling9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            if(issuedorpending10.equals("pending"))
                radioButtonPending9.setChecked(true);
            else
                radioButtonIssued9.setChecked(true);

            title10.setText("");
            appno10.setText("");
            patoffice10.setSelection(0);
            inventor10.setText("");
            issue10.setText("");
            filing10.setText("");
            url10.setText("");
            description10.setText("");
            radioButtonIssued10.setChecked(true);
            radioButtonPending10.setChecked(false);



        }
        else if(d==5)
        {
            stitle10=title10.getText().toString();
            sappno10=appno10.getText().toString();
            selectedCountry10=patoffice10.getSelectedItem().toString();
            sinventor10=inventor10.getText().toString();
            sissue10=issue10.getText().toString();
            sfiling10=filing10.getText().toString();
            surl10=url10.getText().toString();
            sdescription10=description10.getText().toString();
            stitle9=title9.getText().toString();
            sappno9=appno9.getText().toString();
            selectedCountry9=patoffice9.getSelectedItem().toString();
            sinventor9=inventor9.getText().toString();
            sissue9=issue9.getText().toString();
            sfiling9=filing9.getText().toString();
            surl9=url9.getText().toString();
            sdescription9=description9.getText().toString();
            stitle8=title8.getText().toString();
            sappno8=appno8.getText().toString();
            selectedCountry8=patoffice8.getSelectedItem().toString();
            sinventor8=inventor8.getText().toString();
            sissue8=issue8.getText().toString();
            sfiling8=filing8.getText().toString();
            surl8=url8.getText().toString();
            sdescription8=description8.getText().toString();
            stitle7=title7.getText().toString();
            sappno7=appno7.getText().toString();
            selectedCountry7=patoffice7.getSelectedItem().toString();
            sinventor7=inventor7.getText().toString();
            sissue7=issue7.getText().toString();
            sfiling7=filing7.getText().toString();
            surl7=url7.getText().toString();
            sdescription7=description7.getText().toString();
            stitle6=title6.getText().toString();
            sappno6=appno6.getText().toString();
            selectedCountry6=patoffice6.getSelectedItem().toString();
            sinventor6=inventor6.getText().toString();
            sissue6=issue6.getText().toString();
            sfiling6=filing6.getText().toString();
            surl6=url6.getText().toString();
            sdescription6=description6.getText().toString();


            stitle5=stitle6;
            sappno5=sappno6;
            selectedCountry5=selectedCountry6;
            sinventor5=sinventor6;
            sissue5=sissue6;
            sfiling5=sfiling6;
            surl5=surl6;
            sdescription5=sdescription6;


            title5.setText(stitle5);
            appno5.setText(sappno5);
            patoffice5.setSelection(dataAdapter.getPosition(selectedCountry5));
            inventor5.setText(sinventor5);
            issue5.setText(sissue5);
            filing5.setText(sfiling5);
            url5.setText(surl5);
            description5.setText(sdescription5);

            if(issuedorpending6.equals("pending"))
                radioButtonPending5.setChecked(true);
            else
                radioButtonIssued5.setChecked(true);

            title6.setText("");
            appno6.setText("");
            patoffice6.setSelection(0);
            inventor6.setText("");
            issue6.setText("");
            filing6.setText("");
            url6.setText("");
            description6.setText("");
            radioButtonIssued6.setChecked(true);
            radioButtonPending6.setChecked(false);



            stitle6=stitle7;
            sappno6=sappno7;
            selectedCountry6=selectedCountry7;
            sinventor6=sinventor7;
            sissue6=sissue7;
            sfiling6=sfiling7;
            surl6=surl7;
            sdescription6=sdescription7;

            title6.setText(stitle6);
            appno6.setText(sappno6);
            patoffice6.setSelection(dataAdapter.getPosition(selectedCountry6));
            inventor6.setText(sinventor6);
            issue6.setText(sissue6);
            filing6.setText(sfiling6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            if(issuedorpending7.equals("pending"))
                radioButtonPending6.setChecked(true);
            else
                radioButtonIssued6.setChecked(true);

            title7.setText("");
            appno7.setText("");
            patoffice7.setSelection(0);
            inventor7.setText("");
            issue7.setText("");
            filing7.setText("");
            url7.setText("");
            description7.setText("");
            radioButtonIssued7.setChecked(true);
            radioButtonPending7.setChecked(false);



            stitle7=stitle8;
            sappno7=sappno8;
            selectedCountry7=selectedCountry8;
            sinventor7=sinventor8;
            sissue7=sissue8;
            sfiling7=sfiling8;
            surl7=surl8;
            sdescription7=sdescription8;

            title7.setText(stitle7);
            appno7.setText(sappno7);
            patoffice7.setSelection(dataAdapter.getPosition(selectedCountry7));
            inventor7.setText(sinventor7);
            issue7.setText(sissue7);
            filing7.setText(sfiling7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            if(issuedorpending8.equals("pending"))
                radioButtonPending7.setChecked(true);
            else
                radioButtonIssued7.setChecked(true);

            title8.setText("");
            appno8.setText("");
            patoffice8.setSelection(0);
            inventor8.setText("");
            issue8.setText("");
            filing8.setText("");
            url8.setText("");
            description8.setText("");
            radioButtonIssued8.setChecked(true);
            radioButtonPending8.setChecked(false);




            stitle8=stitle9;
            sappno8=sappno9;
            selectedCountry8=selectedCountry9;
            sinventor8=sinventor9;
            sissue8=sissue9;
            sfiling8=sfiling9;
            surl8=surl9;
            sdescription8=sdescription9;

            title8.setText(stitle8);
            appno8.setText(sappno8);
            patoffice8.setSelection(dataAdapter.getPosition(selectedCountry8));
            inventor8.setText(sinventor8);
            issue8.setText(sissue8);
            filing8.setText(sfiling8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            if(issuedorpending9.equals("pending"))
                radioButtonPending8.setChecked(true);
            else
                radioButtonIssued8.setChecked(true);

            title9.setText("");
            appno9.setText("");
            patoffice9.setSelection(0);
            inventor9.setText("");
            issue9.setText("");
            filing9.setText("");
            url9.setText("");
            description9.setText("");
            radioButtonIssued9.setChecked(true);
            radioButtonPending9.setChecked(false);



            stitle9=stitle10;
            sappno9=sappno10;
            selectedCountry9=selectedCountry10;
            sinventor9=sinventor10;
            sissue9=sissue10;
            sfiling9=sfiling10;
            surl9=surl10;
            sdescription9=sdescription10;

            title9.setText(stitle9);
            appno9.setText(sappno9);
            patoffice9.setSelection(dataAdapter.getPosition(selectedCountry9));
            inventor9.setText(sinventor9);
            issue9.setText(sissue9);
            filing9.setText(sfiling9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            if(issuedorpending10.equals("pending"))
                radioButtonPending9.setChecked(true);
            else
                radioButtonIssued9.setChecked(true);

            title10.setText("");
            appno10.setText("");
            patoffice10.setSelection(0);
            inventor10.setText("");
            issue10.setText("");
            filing10.setText("");
            url10.setText("");
            description10.setText("");
            radioButtonIssued10.setChecked(true);
            radioButtonPending10.setChecked(false);




        }
        else if(d==4)
        {
            stitle10=title10.getText().toString();
            sappno10=appno10.getText().toString();
            selectedCountry10=patoffice10.getSelectedItem().toString();
            sinventor10=inventor10.getText().toString();
            sissue10=issue10.getText().toString();
            sfiling10=filing10.getText().toString();
            surl10=url10.getText().toString();
            sdescription10=description10.getText().toString();
            stitle9=title9.getText().toString();
            sappno9=appno9.getText().toString();
            selectedCountry9=patoffice9.getSelectedItem().toString();
            sinventor9=inventor9.getText().toString();
            sissue9=issue9.getText().toString();
            sfiling9=filing9.getText().toString();
            surl9=url9.getText().toString();
            sdescription9=description9.getText().toString();
            stitle8=title8.getText().toString();
            sappno8=appno8.getText().toString();
            selectedCountry8=patoffice8.getSelectedItem().toString();
            sinventor8=inventor8.getText().toString();
            sissue8=issue8.getText().toString();
            sfiling8=filing8.getText().toString();
            surl8=url8.getText().toString();
            sdescription8=description8.getText().toString();
            stitle7=title7.getText().toString();
            sappno7=appno7.getText().toString();
            selectedCountry7=patoffice7.getSelectedItem().toString();
            sinventor7=inventor7.getText().toString();
            sissue7=issue7.getText().toString();
            sfiling7=filing7.getText().toString();
            surl7=url7.getText().toString();
            sdescription7=description7.getText().toString();
            stitle6=title6.getText().toString();
            sappno6=appno6.getText().toString();
            selectedCountry6=patoffice6.getSelectedItem().toString();
            sinventor6=inventor6.getText().toString();
            sissue6=issue6.getText().toString();
            sfiling6=filing6.getText().toString();
            surl6=url6.getText().toString();
            sdescription6=description6.getText().toString();
            stitle5=title5.getText().toString();
            sappno5=appno5.getText().toString();
            selectedCountry5=patoffice5.getSelectedItem().toString();
            sinventor5=inventor5.getText().toString();
            sissue5=issue5.getText().toString();
            sfiling5=filing5.getText().toString();
            surl5=url5.getText().toString();
            sdescription5=description5.getText().toString();

            stitle4=stitle5;
            sappno4=sappno5;
            selectedCountry4=selectedCountry5;
            sinventor4=sinventor5;
            sissue4=sissue5;
            sfiling4=sfiling5;
            surl4=surl5;
            sdescription4=sdescription5;

            title4.setText(stitle4);
            appno4.setText(sappno4);
            patoffice4.setSelection(dataAdapter.getPosition(selectedCountry4));
            inventor4.setText(sinventor4);
            issue4.setText(sissue4);
            filing4.setText(sfiling4);
            url4.setText(surl4);
            description4.setText(sdescription4);

            if(issuedorpending5.equals("pending"))
                radioButtonPending4.setChecked(true);
            else
                radioButtonIssued4.setChecked(true);

            title5.setText("");
            appno5.setText("");
            patoffice5.setSelection(0);
            inventor5.setText("");
            issue5.setText("");
            filing5.setText("");
            url5.setText("");
            description5.setText("");
            radioButtonIssued5.setChecked(true);
            radioButtonPending5.setChecked(false);




            stitle5=stitle6;
            sappno5=sappno6;
            selectedCountry5=selectedCountry6;
            sinventor5=sinventor6;
            sissue5=sissue6;
            sfiling5=sfiling6;
            surl5=surl6;
            sdescription5=sdescription6;


            title5.setText(stitle5);
            appno5.setText(sappno5);
            patoffice5.setSelection(dataAdapter.getPosition(selectedCountry5));
            inventor5.setText(sinventor5);
            issue5.setText(sissue5);
            filing5.setText(sfiling5);
            url5.setText(surl5);
            description5.setText(sdescription5);

            if(issuedorpending6.equals("pending"))
                radioButtonPending5.setChecked(true);
            else
                radioButtonIssued5.setChecked(true);

            title6.setText("");
            appno6.setText("");
            patoffice6.setSelection(0);
            inventor6.setText("");
            issue6.setText("");
            filing6.setText("");
            url6.setText("");
            description6.setText("");
            radioButtonIssued6.setChecked(true);
            radioButtonPending6.setChecked(false);



            stitle6=stitle7;
            sappno6=sappno7;
            selectedCountry6=selectedCountry7;
            sinventor6=sinventor7;
            sissue6=sissue7;
            sfiling6=sfiling7;
            surl6=surl7;
            sdescription6=sdescription7;

            title6.setText(stitle6);
            appno6.setText(sappno6);
            patoffice6.setSelection(dataAdapter.getPosition(selectedCountry6));
            inventor6.setText(sinventor6);
            issue6.setText(sissue6);
            filing6.setText(sfiling6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            if(issuedorpending7.equals("pending"))
                radioButtonPending6.setChecked(true);
            else
                radioButtonIssued6.setChecked(true);

            title7.setText("");
            appno7.setText("");
            patoffice7.setSelection(0);
            inventor7.setText("");
            issue7.setText("");
            filing7.setText("");
            url7.setText("");
            description7.setText("");
            radioButtonIssued7.setChecked(true);
            radioButtonPending7.setChecked(false);




            stitle7=stitle8;
            sappno7=sappno8;
            selectedCountry7=selectedCountry8;
            sinventor7=sinventor8;
            sissue7=sissue8;
            sfiling7=sfiling8;
            surl7=surl8;
            sdescription7=sdescription8;

            title7.setText(stitle7);
            appno7.setText(sappno7);
            patoffice7.setSelection(dataAdapter.getPosition(selectedCountry7));
            inventor7.setText(sinventor7);
            issue7.setText(sissue7);
            filing7.setText(sfiling7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            if(issuedorpending8.equals("pending"))
                radioButtonPending7.setChecked(true);
            else
                radioButtonIssued7.setChecked(true);

            title8.setText("");
            appno8.setText("");
            patoffice8.setSelection(0);
            inventor8.setText("");
            issue8.setText("");
            filing8.setText("");
            url8.setText("");
            description8.setText("");
            radioButtonIssued8.setChecked(true);
            radioButtonPending8.setChecked(false);




            stitle8=stitle9;
            sappno8=sappno9;
            selectedCountry8=selectedCountry9;
            sinventor8=sinventor9;
            sissue8=sissue9;
            sfiling8=sfiling9;
            surl8=surl9;
            sdescription8=sdescription9;

            title8.setText(stitle8);
            appno8.setText(sappno8);
            patoffice8.setSelection(dataAdapter.getPosition(selectedCountry8));
            inventor8.setText(sinventor8);
            issue8.setText(sissue8);
            filing8.setText(sfiling8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            if(issuedorpending9.equals("pending"))
                radioButtonPending8.setChecked(true);
            else
                radioButtonIssued8.setChecked(true);

            title9.setText("");
            appno9.setText("");
            patoffice9.setSelection(0);
            inventor9.setText("");
            issue9.setText("");
            filing9.setText("");
            url9.setText("");
            description9.setText("");
            radioButtonIssued9.setChecked(true);
            radioButtonPending9.setChecked(false);




            stitle9=stitle10;
            sappno9=sappno10;
            selectedCountry9=selectedCountry10;
            sinventor9=sinventor10;
            sissue9=sissue10;
            sfiling9=sfiling10;
            surl9=surl10;
            sdescription9=sdescription10;

            title9.setText(stitle9);
            appno9.setText(sappno9);
            patoffice9.setSelection(dataAdapter.getPosition(selectedCountry9));
            inventor9.setText(sinventor9);
            issue9.setText(sissue9);
            filing9.setText(sfiling9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            if(issuedorpending10.equals("pending"))
                radioButtonPending9.setChecked(true);
            else
                radioButtonIssued9.setChecked(true);

            title10.setText("");
            appno10.setText("");
            patoffice10.setSelection(0);
            inventor10.setText("");
            issue10.setText("");
            filing10.setText("");
            url10.setText("");
            description10.setText("");
            radioButtonIssued10.setChecked(true);
            radioButtonPending10.setChecked(false);




        }
        else if(d==3)
        {
            stitle10=title10.getText().toString();
            sappno10=appno10.getText().toString();
            selectedCountry10=patoffice10.getSelectedItem().toString();
            sinventor10=inventor10.getText().toString();
            sissue10=issue10.getText().toString();
            sfiling10=filing10.getText().toString();
            surl10=url10.getText().toString();
            sdescription10=description10.getText().toString();
            stitle9=title9.getText().toString();
            sappno9=appno9.getText().toString();
            selectedCountry9=patoffice9.getSelectedItem().toString();
            sinventor9=inventor9.getText().toString();
            sissue9=issue9.getText().toString();
            sfiling9=filing9.getText().toString();
            surl9=url9.getText().toString();
            sdescription9=description9.getText().toString();
            stitle8=title8.getText().toString();
            sappno8=appno8.getText().toString();
            selectedCountry8=patoffice8.getSelectedItem().toString();
            sinventor8=inventor8.getText().toString();
            sissue8=issue8.getText().toString();
            sfiling8=filing8.getText().toString();
            surl8=url8.getText().toString();
            sdescription8=description8.getText().toString();
            stitle7=title7.getText().toString();
            sappno7=appno7.getText().toString();
            selectedCountry7=patoffice7.getSelectedItem().toString();
            sinventor7=inventor7.getText().toString();
            sissue7=issue7.getText().toString();
            sfiling7=filing7.getText().toString();
            surl7=url7.getText().toString();
            sdescription7=description7.getText().toString();
            stitle6=title6.getText().toString();
            sappno6=appno6.getText().toString();
            selectedCountry6=patoffice6.getSelectedItem().toString();
            sinventor6=inventor6.getText().toString();
            sissue6=issue6.getText().toString();
            sfiling6=filing6.getText().toString();
            surl6=url6.getText().toString();
            sdescription6=description6.getText().toString();
            stitle5=title5.getText().toString();
            sappno5=appno5.getText().toString();
            selectedCountry5=patoffice5.getSelectedItem().toString();
            sinventor5=inventor5.getText().toString();
            sissue5=issue5.getText().toString();
            sfiling5=filing5.getText().toString();
            surl5=url5.getText().toString();
            sdescription5=description5.getText().toString();
            stitle4=title4.getText().toString();
            sappno4=appno4.getText().toString();
            selectedCountry4=patoffice4.getSelectedItem().toString();
            sinventor4=inventor4.getText().toString();
            sissue4=issue4.getText().toString();
            sfiling4=filing4.getText().toString();
            surl4=url4.getText().toString();
            sdescription4=description4.getText().toString();


            stitle3=stitle4;
            sappno3=sappno4;
            selectedCountry3=selectedCountry4;
            sinventor3=sinventor4;
            sissue3=sissue4;
            sfiling3=sfiling4;
            surl3=surl4;
            sdescription3=sdescription4;

            title3.setText(stitle3);
            appno3.setText(sappno3);
            patoffice3.setSelection(dataAdapter.getPosition(selectedCountry3));
            inventor3.setText(sinventor3);
            issue3.setText(sissue3);
            filing3.setText(sfiling3);
            url3.setText(surl3);
            description3.setText(sdescription3);

            if(issuedorpending4.equals("pending"))
                radioButtonPending3.setChecked(true);
            else
                radioButtonIssued3.setChecked(true);

            title4.setText("");
            appno4.setText("");
            patoffice4.setSelection(0);
            inventor4.setText("");
            issue4.setText("");
            filing4.setText("");
            url4.setText("");
            description4.setText("");
            radioButtonIssued4.setChecked(true);
            radioButtonPending4.setChecked(false);




            stitle4=stitle5;
            sappno4=sappno5;
            selectedCountry4=selectedCountry5;
            sinventor4=sinventor5;
            sissue4=sissue5;
            sfiling4=sfiling5;
            surl4=surl5;
            sdescription4=sdescription5;


            title4.setText(stitle4);
            appno4.setText(sappno4);
            patoffice4.setSelection(dataAdapter.getPosition(selectedCountry4));
            inventor4.setText(sinventor4);
            issue4.setText(sissue4);
            filing4.setText(sfiling4);
            url4.setText(surl4);
            description4.setText(sdescription4);

            if(issuedorpending5.equals("pending"))
                radioButtonPending4.setChecked(true);
            else
                radioButtonIssued4.setChecked(true);

            title5.setText("");
            appno5.setText("");
            patoffice5.setSelection(0);
            inventor5.setText("");
            issue5.setText("");
            filing5.setText("");
            url5.setText("");
            description5.setText("");
            radioButtonIssued5.setChecked(true);
            radioButtonPending5.setChecked(false);



            stitle5=stitle6;
            sappno5=sappno6;
            selectedCountry5=selectedCountry6;
            sinventor5=sinventor6;
            sissue5=sissue6;
            sfiling5=sfiling6;
            surl5=surl6;
            sdescription5=sdescription6;


            title5.setText(stitle5);
            appno5.setText(sappno5);
            patoffice5.setSelection(dataAdapter.getPosition(selectedCountry5));
            inventor5.setText(sinventor5);
            issue5.setText(sissue5);
            filing5.setText(sfiling5);
            url5.setText(surl5);
            description5.setText(sdescription5);

            if(issuedorpending6.equals("pending"))
                radioButtonPending5.setChecked(true);
            else
                radioButtonIssued5.setChecked(true);

            title6.setText("");
            appno6.setText("");
            patoffice6.setSelection(0);
            inventor6.setText("");
            issue6.setText("");
            filing6.setText("");
            url6.setText("");
            description6.setText("");
            radioButtonIssued6.setChecked(true);
            radioButtonPending6.setChecked(false);



            stitle6=stitle7;
            sappno6=sappno7;
            selectedCountry6=selectedCountry7;
            sinventor6=sinventor7;
            sissue6=sissue7;
            sfiling6=sfiling7;
            surl6=surl7;
            sdescription6=sdescription7;

            title6.setText(stitle6);
            appno6.setText(sappno6);
            patoffice6.setSelection(dataAdapter.getPosition(selectedCountry6));
            inventor6.setText(sinventor6);
            issue6.setText(sissue6);
            filing6.setText(sfiling6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            if(issuedorpending7.equals("pending"))
                radioButtonPending6.setChecked(true);
            else
                radioButtonIssued6.setChecked(true);

            title7.setText("");
            appno7.setText("");
            patoffice7.setSelection(0);
            inventor7.setText("");
            issue7.setText("");
            filing7.setText("");
            url7.setText("");
            description7.setText("");
            radioButtonIssued7.setChecked(true);
            radioButtonPending7.setChecked(false);


            stitle7=stitle8;
            sappno7=sappno8;
            selectedCountry7=selectedCountry8;
            sinventor7=sinventor8;
            sissue7=sissue8;
            sfiling7=sfiling8;
            surl7=surl8;
            sdescription7=sdescription8;

            title7.setText(stitle7);
            appno7.setText(sappno7);
            patoffice7.setSelection(dataAdapter.getPosition(selectedCountry7));
            inventor7.setText(sinventor7);
            issue7.setText(sissue7);
            filing7.setText(sfiling7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            if(issuedorpending8.equals("pending"))
                radioButtonPending7.setChecked(true);
            else
                radioButtonIssued7.setChecked(true);

            title8.setText("");
            appno8.setText("");
            patoffice8.setSelection(0);
            inventor8.setText("");
            issue8.setText("");
            filing8.setText("");
            url8.setText("");
            description8.setText("");
            radioButtonIssued8.setChecked(true);
            radioButtonPending8.setChecked(false);




            stitle8=stitle9;
            sappno8=sappno9;
            selectedCountry8=selectedCountry9;
            sinventor8=sinventor9;
            sissue8=sissue9;
            sfiling8=sfiling9;
            surl8=surl9;
            sdescription8=sdescription9;

            title8.setText(stitle8);
            appno8.setText(sappno8);
            patoffice8.setSelection(dataAdapter.getPosition(selectedCountry8));
            inventor8.setText(sinventor8);
            issue8.setText(sissue8);
            filing8.setText(sfiling8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            if(issuedorpending9.equals("pending"))
                radioButtonPending8.setChecked(true);
            else
                radioButtonIssued8.setChecked(true);

            title9.setText("");
            appno9.setText("");
            patoffice9.setSelection(0);
            inventor9.setText("");
            issue9.setText("");
            filing9.setText("");
            url9.setText("");
            description9.setText("");
            radioButtonIssued9.setChecked(true);
            radioButtonPending9.setChecked(false);

            stitle9=stitle10;
            sappno9=sappno10;
            selectedCountry9=selectedCountry10;
            sinventor9=sinventor10;
            sissue9=sissue10;
            sfiling9=sfiling10;
            surl9=surl10;
            sdescription9=sdescription10;

            title9.setText(stitle9);
            appno9.setText(sappno9);
            patoffice9.setSelection(dataAdapter.getPosition(selectedCountry9));
            inventor9.setText(sinventor9);
            issue9.setText(sissue9);
            filing9.setText(sfiling9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            if(issuedorpending10.equals("pending"))
                radioButtonPending9.setChecked(true);
            else
                radioButtonIssued9.setChecked(true);

            title10.setText("");
            appno10.setText("");
            patoffice10.setSelection(0);
            inventor10.setText("");
            issue10.setText("");
            filing10.setText("");
            url10.setText("");
            description10.setText("");
            radioButtonIssued10.setChecked(true);
            radioButtonPending10.setChecked(false);




        }
        else if(d==2)
        {
            stitle10=title10.getText().toString();
            sappno10=appno10.getText().toString();
            selectedCountry10=patoffice10.getSelectedItem().toString();
            sinventor10=inventor10.getText().toString();
            sissue10=issue10.getText().toString();
            sfiling10=filing10.getText().toString();
            surl10=url10.getText().toString();
            sdescription10=description10.getText().toString();
            stitle9=title9.getText().toString();
            sappno9=appno9.getText().toString();
            selectedCountry9=patoffice9.getSelectedItem().toString();
            sinventor9=inventor9.getText().toString();
            sissue9=issue9.getText().toString();
            sfiling9=filing9.getText().toString();
            surl9=url9.getText().toString();
            sdescription9=description9.getText().toString();
            stitle8=title8.getText().toString();
            sappno8=appno8.getText().toString();
            selectedCountry8=patoffice8.getSelectedItem().toString();
            sinventor8=inventor8.getText().toString();
            sissue8=issue8.getText().toString();
            sfiling8=filing8.getText().toString();
            surl8=url8.getText().toString();
            sdescription8=description8.getText().toString();
            stitle7=title7.getText().toString();
            sappno7=appno7.getText().toString();
            selectedCountry7=patoffice7.getSelectedItem().toString();
            sinventor7=inventor7.getText().toString();
            sissue7=issue7.getText().toString();
            sfiling7=filing7.getText().toString();
            surl7=url7.getText().toString();
            sdescription7=description7.getText().toString();
            stitle6=title6.getText().toString();
            sappno6=appno6.getText().toString();
            selectedCountry6=patoffice6.getSelectedItem().toString();
            sinventor6=inventor6.getText().toString();
            sissue6=issue6.getText().toString();
            sfiling6=filing6.getText().toString();
            surl6=url6.getText().toString();
            sdescription6=description6.getText().toString();
            stitle5=title5.getText().toString();
            sappno5=appno5.getText().toString();
            selectedCountry5=patoffice5.getSelectedItem().toString();
            sinventor5=inventor5.getText().toString();
            sissue5=issue5.getText().toString();
            sfiling5=filing5.getText().toString();
            surl5=url5.getText().toString();
            sdescription5=description5.getText().toString();
            stitle4=title4.getText().toString();
            sappno4=appno4.getText().toString();
            selectedCountry4=patoffice4.getSelectedItem().toString();
            sinventor4=inventor4.getText().toString();
            sissue4=issue4.getText().toString();
            sfiling4=filing4.getText().toString();
            surl4=url4.getText().toString();
            sdescription4=description4.getText().toString();
            stitle3=title3.getText().toString();
            sappno3=appno3.getText().toString();
            selectedCountry3=patoffice3.getSelectedItem().toString();
            sinventor3=inventor3.getText().toString();
            sissue3=issue3.getText().toString();
            sfiling3=filing3.getText().toString();
            surl3=url3.getText().toString();
            sdescription3=description3.getText().toString();


            stitle2=stitle3;
            sappno2=sappno3;
            selectedCountry2=selectedCountry3;
            sinventor2=sinventor3;
            sissue2=sissue3;
            sfiling2=sfiling3;
            surl2=surl3;
            sdescription2=sdescription3;


            title2.setText(stitle2);
            appno2.setText(sappno2);
            patoffice2.setSelection(dataAdapter.getPosition(selectedCountry2));
            inventor2.setText(sinventor2);
            issue2.setText(sissue2);
            filing2.setText(sfiling2);
            url2.setText(surl2);
            description2.setText(sdescription2);

            if(issuedorpending3.equals("pending"))
                radioButtonPending2.setChecked(true);
            else
                radioButtonIssued2.setChecked(true);

            title3.setText("");
            appno3.setText("");
            patoffice3.setSelection(0);
            inventor3.setText("");
            issue3.setText("");
            filing3.setText("");
            url3.setText("");
            description3.setText("");
            radioButtonIssued3.setChecked(true);
            radioButtonPending3.setChecked(false);



            stitle3=stitle4;
            sappno3=sappno4;
            selectedCountry3=selectedCountry4;
            sinventor3=sinventor4;
            sissue3=sissue4;
            sfiling3=sfiling4;
            surl3=surl4;
            sdescription3=sdescription4;

            title3.setText(stitle3);
            appno3.setText(sappno3);
            patoffice3.setSelection(dataAdapter.getPosition(selectedCountry3));
            inventor3.setText(sinventor3);
            issue3.setText(sissue3);
            filing3.setText(sfiling3);
            url3.setText(surl3);
            description3.setText(sdescription3);

            if(issuedorpending4.equals("pending"))
                radioButtonPending3.setChecked(true);
            else
                radioButtonIssued3.setChecked(true);

            title4.setText("");
            appno4.setText("");
            patoffice4.setSelection(0);
            inventor4.setText("");
            issue4.setText("");
            filing4.setText("");
            url4.setText("");
            description4.setText("");
            radioButtonIssued4.setChecked(true);
            radioButtonPending4.setChecked(false);

            stitle4=stitle5;
            sappno4=sappno5;
            selectedCountry4=selectedCountry5;
            sinventor4=sinventor5;
            sissue4=sissue5;
            sfiling4=sfiling5;
            surl4=surl5;
            sdescription4=sdescription5;

            title4.setText(stitle4);
            appno4.setText(sappno4);
            patoffice4.setSelection(dataAdapter.getPosition(selectedCountry4));
            inventor4.setText(sinventor4);
            issue4.setText(sissue4);
            filing4.setText(sfiling4);
            url4.setText(surl4);
            description4.setText(sdescription4);

            if(issuedorpending5.equals("pending"))
                radioButtonPending4.setChecked(true);
            else
                radioButtonIssued4.setChecked(true);

            title5.setText("");
            appno5.setText("");
            patoffice5.setSelection(0);
            inventor5.setText("");
            issue5.setText("");
            filing5.setText("");
            url5.setText("");
            description5.setText("");
            radioButtonIssued5.setChecked(true);
            radioButtonPending5.setChecked(false);




            stitle5=stitle6;
            sappno5=sappno6;
            selectedCountry5=selectedCountry6;
            sinventor5=sinventor6;
            sissue5=sissue6;
            sfiling5=sfiling6;
            surl5=surl6;
            sdescription5=sdescription6;


            title5.setText(stitle5);
            appno5.setText(sappno5);
            patoffice5.setSelection(dataAdapter.getPosition(selectedCountry5));
            inventor5.setText(sinventor5);
            issue5.setText(sissue5);
            filing5.setText(sfiling5);
            url5.setText(surl5);
            description5.setText(sdescription5);

            if(issuedorpending6.equals("pending"))
                radioButtonPending5.setChecked(true);
            else
                radioButtonIssued5.setChecked(true);

            title6.setText("");
            appno6.setText("");
            patoffice6.setSelection(0);
            inventor6.setText("");
            issue6.setText("");
            filing6.setText("");
            url6.setText("");
            description6.setText("");
            radioButtonIssued6.setChecked(true);
            radioButtonPending6.setChecked(false);



            stitle6=stitle7;
            sappno6=sappno7;
            selectedCountry6=selectedCountry7;
            sinventor6=sinventor7;
            sissue6=sissue7;
            sfiling6=sfiling7;
            surl6=surl7;
            sdescription6=sdescription7;

            title6.setText(stitle6);
            appno6.setText(sappno6);
            patoffice6.setSelection(dataAdapter.getPosition(selectedCountry6));
            inventor6.setText(sinventor6);
            issue6.setText(sissue6);
            filing6.setText(sfiling6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            if(issuedorpending7.equals("pending"))
                radioButtonPending6.setChecked(true);
            else
                radioButtonIssued6.setChecked(true);

            title7.setText("");
            appno7.setText("");
            patoffice7.setSelection(0);
            inventor7.setText("");
            issue7.setText("");
            filing7.setText("");
            url7.setText("");
            description7.setText("");
            radioButtonIssued7.setChecked(true);
            radioButtonPending7.setChecked(false);




            stitle7=stitle8;
            sappno7=sappno8;
            selectedCountry7=selectedCountry8;
            sinventor7=sinventor8;
            sissue7=sissue8;
            sfiling7=sfiling8;
            surl7=surl8;
            sdescription7=sdescription8;

            title7.setText(stitle7);
            appno7.setText(sappno7);
            patoffice7.setSelection(dataAdapter.getPosition(selectedCountry7));
            inventor7.setText(sinventor7);
            issue7.setText(sissue7);
            filing7.setText(sfiling7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            if(issuedorpending8.equals("pending"))
                radioButtonPending7.setChecked(true);
            else
                radioButtonIssued7.setChecked(true);

            title8.setText("");
            appno8.setText("");
            patoffice8.setSelection(0);
            inventor8.setText("");
            issue8.setText("");
            filing8.setText("");
            url8.setText("");
            description8.setText("");
            radioButtonIssued8.setChecked(true);
            radioButtonPending8.setChecked(false);




            stitle8=stitle9;
            sappno8=sappno9;
            selectedCountry8=selectedCountry9;
            sinventor8=sinventor9;
            sissue8=sissue9;
            sfiling8=sfiling9;
            surl8=surl9;
            sdescription8=sdescription9;

            title8.setText(stitle8);
            appno8.setText(sappno8);
            patoffice8.setSelection(dataAdapter.getPosition(selectedCountry8));
            inventor8.setText(sinventor8);
            issue8.setText(sissue8);
            filing8.setText(sfiling8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            if(issuedorpending9.equals("pending"))
                radioButtonPending8.setChecked(true);
            else
                radioButtonIssued8.setChecked(true);

            title9.setText("");
            appno9.setText("");
            patoffice9.setSelection(0);
            inventor9.setText("");
            issue9.setText("");
            filing9.setText("");
            url9.setText("");
            description9.setText("");
            radioButtonIssued9.setChecked(true);
            radioButtonPending9.setChecked(false);




            stitle9=stitle10;
            sappno9=sappno10;
            selectedCountry9=selectedCountry10;
            sinventor9=sinventor10;
            sissue9=sissue10;
            sfiling9=sfiling10;
            surl9=surl10;
            sdescription9=sdescription10;

            title9.setText(stitle9);
            appno9.setText(sappno9);
            patoffice9.setSelection(dataAdapter.getPosition(selectedCountry9));
            inventor9.setText(sinventor9);
            issue9.setText(sissue9);
            filing9.setText(sfiling9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            if(issuedorpending10.equals("pending"))
                radioButtonPending9.setChecked(true);
            else
                radioButtonIssued9.setChecked(true);

            title10.setText("");
            appno10.setText("");
            patoffice10.setSelection(0);
            inventor10.setText("");
            issue10.setText("");
            filing10.setText("");
            url10.setText("");
            description10.setText("");
            radioButtonIssued10.setChecked(true);
            radioButtonPending10.setChecked(false);




        }
        else if(d==1)
        {
            stitle10=title10.getText().toString();
            sappno10=appno10.getText().toString();
            selectedCountry10=patoffice10.getSelectedItem().toString();
            sinventor10=inventor10.getText().toString();
            sissue10=issue10.getText().toString();
            sfiling10=filing10.getText().toString();
            surl10=url10.getText().toString();
            sdescription10=description10.getText().toString();
            stitle9=title9.getText().toString();
            sappno9=appno9.getText().toString();
            selectedCountry9=patoffice9.getSelectedItem().toString();
            sinventor9=inventor9.getText().toString();
            sissue9=issue9.getText().toString();
            sfiling9=filing9.getText().toString();
            surl9=url9.getText().toString();
            sdescription9=description9.getText().toString();
            stitle8=title8.getText().toString();
            sappno8=appno8.getText().toString();
            selectedCountry8=patoffice8.getSelectedItem().toString();
            sinventor8=inventor8.getText().toString();
            sissue8=issue8.getText().toString();
            sfiling8=filing8.getText().toString();
            surl8=url8.getText().toString();
            sdescription8=description8.getText().toString();
            stitle7=title7.getText().toString();
            sappno7=appno7.getText().toString();
            selectedCountry7=patoffice7.getSelectedItem().toString();
            sinventor7=inventor7.getText().toString();
            sissue7=issue7.getText().toString();
            sfiling7=filing7.getText().toString();
            surl7=url7.getText().toString();
            sdescription7=description7.getText().toString();
            stitle6=title6.getText().toString();
            sappno6=appno6.getText().toString();
            selectedCountry6=patoffice6.getSelectedItem().toString();
            sinventor6=inventor6.getText().toString();
            sissue6=issue6.getText().toString();
            sfiling6=filing6.getText().toString();
            surl6=url6.getText().toString();
            sdescription6=description6.getText().toString();
            stitle5=title5.getText().toString();
            sappno5=appno5.getText().toString();
            selectedCountry5=patoffice5.getSelectedItem().toString();
            sinventor5=inventor5.getText().toString();
            sissue5=issue5.getText().toString();
            sfiling5=filing5.getText().toString();
            surl5=url5.getText().toString();
            sdescription5=description5.getText().toString();
            stitle4=title4.getText().toString();
            sappno4=appno4.getText().toString();
            selectedCountry4=patoffice4.getSelectedItem().toString();
            sinventor4=inventor4.getText().toString();
            sissue4=issue4.getText().toString();
            sfiling4=filing4.getText().toString();
            surl4=url4.getText().toString();
            sdescription4=description4.getText().toString();
            stitle3=title3.getText().toString();
            sappno3=appno3.getText().toString();
            selectedCountry3=patoffice3.getSelectedItem().toString();
            sinventor3=inventor3.getText().toString();
            sissue3=issue3.getText().toString();
            sfiling3=filing3.getText().toString();
            surl3=url3.getText().toString();
            sdescription3=description3.getText().toString();
            stitle2=title2.getText().toString();
            sappno2=appno2.getText().toString();
            selectedCountry2=patoffice2.getSelectedItem().toString();
            sinventor2=inventor2.getText().toString();
            sissue2=issue2.getText().toString();
            sfiling2=filing2.getText().toString();
            surl2=url2.getText().toString();
            sdescription2=description2.getText().toString();


            stitle1=stitle2;
            sappno1=sappno2;
            selectedCountry1=selectedCountry2;
            sinventor1=sinventor2;
            sissue1=sissue2;
            sfiling1=sfiling2;
            surl1=surl2;
            sdescription1=sdescription2;


            title1.setText(stitle1);
            appno1.setText(sappno1);
            patoffice1.setSelection(dataAdapter.getPosition(selectedCountry1));
            inventor1.setText(sinventor1);
            issue1.setText(sissue1);
            filing1.setText(sfiling1);
            url1.setText(surl1);
            description1.setText(sdescription1);

            if(issuedorpending2.equals("pending"))
                radioButtonPending1.setChecked(true);
            else
                radioButtonIssued1.setChecked(true);

            title2.setText("");
            appno2.setText("");
            patoffice2.setSelection(0);
            inventor2.setText("");
            issue2.setText("");
            filing2.setText("");
            url2.setText("");
            description2.setText("");
            radioButtonIssued2.setChecked(true);
            radioButtonPending2.setChecked(false);



            stitle2=stitle3;
            sappno2=sappno3;
            selectedCountry2=selectedCountry3;
            sinventor2=sinventor3;
            sissue2=sissue3;
            sfiling2=sfiling3;
            surl2=surl3;
            sdescription2=sdescription3;

            title2.setText(stitle2);
            appno2.setText(sappno2);
            patoffice2.setSelection(dataAdapter.getPosition(selectedCountry2));
            inventor2.setText(sinventor2);
            issue2.setText(sissue2);
            filing2.setText(sfiling2);
            url2.setText(surl2);
            description2.setText(sdescription2);

            if(issuedorpending3.equals("pending"))
                radioButtonPending2.setChecked(true);
            else
                radioButtonIssued2.setChecked(true);

            title3.setText("");
            appno3.setText("");
            patoffice3.setSelection(0);
            inventor3.setText("");
            issue3.setText("");
            filing3.setText("");
            url3.setText("");
            description3.setText("");
            radioButtonIssued3.setChecked(true);
            radioButtonPending3.setChecked(false);




            stitle3=stitle4;
            sappno3=sappno4;
            selectedCountry3=selectedCountry4;
            sinventor3=sinventor4;
            sissue3=sissue4;
            sfiling3=sfiling4;
            surl3=surl4;
            sdescription3=sdescription4;


            title3.setText(stitle3);
            appno3.setText(sappno3);
            patoffice3.setSelection(dataAdapter.getPosition(selectedCountry3));
            inventor3.setText(sinventor3);
            issue3.setText(sissue3);
            filing3.setText(sfiling3);
            url3.setText(surl3);
            description3.setText(sdescription3);

            if(issuedorpending4.equals("pending"))
                radioButtonPending3.setChecked(true);
            else
                radioButtonIssued3.setChecked(true);

            title4.setText("");
            appno4.setText("");
            patoffice4.setSelection(0);
            inventor4.setText("");
            issue4.setText("");
            filing4.setText("");
            url4.setText("");
            description4.setText("");
            radioButtonIssued4.setChecked(true);
            radioButtonPending4.setChecked(false);



            stitle4=stitle5;
            sappno4=sappno5;
            selectedCountry4=selectedCountry5;
            sinventor4=sinventor5;
            sissue4=sissue5;
            sfiling4=sfiling5;
            surl4=surl5;
            sdescription4=sdescription5;

            title4.setText(stitle4);
            appno4.setText(sappno4);
            patoffice4.setSelection(dataAdapter.getPosition(selectedCountry4));
            inventor4.setText(sinventor4);
            issue4.setText(sissue4);
            filing4.setText(sfiling4);
            url4.setText(surl4);
            description4.setText(sdescription4);

            if(issuedorpending5.equals("pending"))
                radioButtonPending4.setChecked(true);
            else
                radioButtonIssued4.setChecked(true);

            title5.setText("");
            appno5.setText("");
            patoffice5.setSelection(0);
            inventor5.setText("");
            issue5.setText("");
            filing5.setText("");
            url5.setText("");
            description5.setText("");
            radioButtonIssued5.setChecked(true);
            radioButtonPending5.setChecked(false);




            stitle5=stitle6;
            sappno5=sappno6;
            selectedCountry5=selectedCountry6;
            sinventor5=sinventor6;
            sissue5=sissue6;
            sfiling5=sfiling6;
            surl5=surl6;
            sdescription5=sdescription6;

            title5.setText(stitle5);
            appno5.setText(sappno5);
            patoffice5.setSelection(dataAdapter.getPosition(selectedCountry5));
            inventor5.setText(sinventor5);
            issue5.setText(sissue5);
            filing5.setText(sfiling5);
            url5.setText(surl5);
            description5.setText(sdescription5);

            if(issuedorpending6.equals("pending"))
                radioButtonPending5.setChecked(true);
            else
                radioButtonIssued5.setChecked(true);

            title6.setText("");
            appno6.setText("");
            patoffice6.setSelection(0);
            inventor6.setText("");
            issue6.setText("");
            filing6.setText("");
            url6.setText("");
            description6.setText("");
            radioButtonIssued6.setChecked(true);
            radioButtonPending6.setChecked(false);



            stitle6=stitle7;
            sappno6=sappno7;
            selectedCountry6=selectedCountry7;
            sinventor6=sinventor7;
            sissue6=sissue7;
            sfiling6=sfiling7;
            surl6=surl7;
            sdescription6=sdescription7;


            title6.setText(stitle6);
            appno6.setText(sappno6);
            patoffice6.setSelection(dataAdapter.getPosition(selectedCountry6));
            inventor6.setText(sinventor6);
            issue6.setText(sissue6);
            filing6.setText(sfiling6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            if(issuedorpending7.equals("pending"))
                radioButtonPending6.setChecked(true);
            else
                radioButtonIssued6.setChecked(true);

            title7.setText("");
            appno7.setText("");
            patoffice7.setSelection(0);
            inventor7.setText("");
            issue7.setText("");
            filing7.setText("");
            url7.setText("");
            description7.setText("");
            radioButtonIssued7.setChecked(true);
            radioButtonPending7.setChecked(false);



            stitle7=stitle8;
            sappno7=sappno8;
            selectedCountry7=selectedCountry8;
            sinventor7=sinventor8;
            sissue7=sissue8;
            sfiling7=sfiling8;
            surl7=surl8;
            sdescription7=sdescription8;


            title7.setText(stitle7);
            appno7.setText(sappno7);
            patoffice7.setSelection(dataAdapter.getPosition(selectedCountry7));
            inventor7.setText(sinventor7);
            issue7.setText(sissue7);
            filing7.setText(sfiling7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            if(issuedorpending8.equals("pending"))
                radioButtonPending7.setChecked(true);
            else
                radioButtonIssued7.setChecked(true);

            title8.setText("");
            appno8.setText("");
            patoffice8.setSelection(0);
            inventor8.setText("");
            issue8.setText("");
            filing8.setText("");
            url8.setText("");
            description8.setText("");
            radioButtonIssued8.setChecked(true);
            radioButtonPending8.setChecked(false);



            stitle8=stitle9;
            sappno8=sappno9;
            selectedCountry8=selectedCountry9;
            sinventor8=sinventor9;
            sissue8=sissue9;
            sfiling8=sfiling9;
            surl8=surl9;
            sdescription8=sdescription9;

            title8.setText(stitle8);
            appno8.setText(sappno8);
            patoffice8.setSelection(dataAdapter.getPosition(selectedCountry8));
            inventor8.setText(sinventor8);
            issue8.setText(sissue8);
            filing8.setText(sfiling8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            if(issuedorpending9.equals("pending"))
                radioButtonPending8.setChecked(true);
            else
                radioButtonIssued8.setChecked(true);

            title9.setText("");
            appno9.setText("");
            patoffice9.setSelection(0);
            inventor9.setText("");
            issue9.setText("");
            filing9.setText("");
            url9.setText("");
            description9.setText("");
            radioButtonIssued9.setChecked(true);
            radioButtonPending9.setChecked(false);




            stitle9=stitle10;
            sappno9=sappno10;
            selectedCountry9=selectedCountry10;
            sinventor9=sinventor10;
            sissue9=sissue10;
            sfiling9=sfiling10;
            surl9=surl10;
            sdescription9=sdescription10;


            title9.setText(stitle9);
            appno9.setText(sappno9);
            patoffice9.setSelection(dataAdapter.getPosition(selectedCountry9));
            inventor9.setText(sinventor9);
            issue9.setText(sissue9);
            filing9.setText(sfiling9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            if(issuedorpending10.equals("pending"))
                radioButtonPending9.setChecked(true);
            else
                radioButtonIssued9.setChecked(true);

            title10.setText("");
            appno10.setText("");
            patoffice10.setSelection(0);
            inventor10.setText("");
            issue10.setText("");
            filing10.setText("");
            url10.setText("");
            description10.setText("");
            radioButtonIssued10.setChecked(true);
            radioButtonPending10.setChecked(false);



        }
    }

    class GetCountries extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//
//            json = jParser.makeHttpRequest(url_getcountries, "GET", params);
//            try {
//                String s = json.getString("count");
//                countrycount=Integer.parseInt(s);
//                countries=new String[countrycount];
//                for(int i=0;i<countrycount;i++)
//                {
//                    countries[i]=json.getString("country"+i);
//                }
//
//
//
//
//            }catch (Exception e){e.printStackTrace();}

            countrycount=getResources().getStringArray(R.array.countries_array).length;
            countries=new String[countrycount];
            countries=getResources().getStringArray(R.array.countries_array);

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            countrieslist.clear();
            countrieslist.add("- Select Patent Office -");
            for(int i=0;i<countrycount;i++)
            {
                countrieslist.add(countries[i]);
            }
            populateCountries();
        }
    }
    void populateCountries()
    {

        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, countrieslist)
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
        patoffice1.setAdapter(dataAdapter);
        patoffice2.setAdapter(dataAdapter);
        patoffice3.setAdapter(dataAdapter);
        patoffice4.setAdapter(dataAdapter);
        patoffice5.setAdapter(dataAdapter);
        patoffice6.setAdapter(dataAdapter);
        patoffice7.setAdapter(dataAdapter);
        patoffice8.setAdapter(dataAdapter);
        patoffice9.setAdapter(dataAdapter);
        patoffice10.setAdapter(dataAdapter);

        patoffice1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry1 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        patoffice2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry2 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        patoffice3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry3 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        patoffice4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry4 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        patoffice5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry5 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        patoffice6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry6 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        patoffice7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry7 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        patoffice8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry8 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        patoffice9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry9 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        patoffice10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry10 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(selectedCountry1!=null)
            patoffice1.setSelection(dataAdapter.getPosition(selectedCountry1));
        if(selectedCountry2!=null)
            patoffice2.setSelection(dataAdapter.getPosition(selectedCountry2));
        if(selectedCountry3!=null)
            patoffice3.setSelection(dataAdapter.getPosition(selectedCountry3));
        if(selectedCountry4!=null)
            patoffice4.setSelection(dataAdapter.getPosition(selectedCountry4));
        if(selectedCountry5!=null)
            patoffice5.setSelection(dataAdapter.getPosition(selectedCountry5));
        if(selectedCountry6!=null)
            patoffice6.setSelection(dataAdapter.getPosition(selectedCountry6));
        if(selectedCountry7!=null)
            patoffice7.setSelection(dataAdapter.getPosition(selectedCountry7));
        if(selectedCountry8!=null)
            patoffice8.setSelection(dataAdapter.getPosition(selectedCountry8));
        if(selectedCountry9!=null)
            patoffice9.setSelection(dataAdapter.getPosition(selectedCountry9));
        if(selectedCountry10!=null)
            patoffice10.setSelection(dataAdapter.getPosition(selectedCountry10));

    }
    void validateandSave()
    {
        title1.setError(null);
        appno1.setError(null);
        inventor1.setError(null);
        issue1.setError(null);
        filing1.setError(null);
        url1.setError(null);
        description1.setError(null);
        title2.setError(null);
        appno2.setError(null);
        inventor2.setError(null);
        issue2.setError(null);
        filing2.setError(null);
        url2.setError(null);
        description2.setError(null);
        title3.setError(null);
        appno3.setError(null);
        inventor3.setError(null);
        issue3.setError(null);
        filing3.setError(null);
        url3.setError(null);
        description3.setError(null);
        title4.setError(null);
        appno4.setError(null);
        inventor4.setError(null);
        issue4.setError(null);
        filing4.setError(null);
        url4.setError(null);
        description4.setError(null);
        title5.setError(null);
        appno5.setError(null);
        inventor5.setError(null);
        issue5.setError(null);
        filing5.setError(null);
        url5.setError(null);
        description5.setError(null);
        title6.setError(null);
        appno6.setError(null);
        inventor6.setError(null);
        issue6.setError(null);
        filing6.setError(null);
        url6.setError(null);
        description6.setError(null);
        title7.setError(null);
        appno7.setError(null);
        inventor7.setError(null);
        issue7.setError(null);
        filing7.setError(null);
        url7.setError(null);
        description7.setError(null);
        title8.setError(null);
        appno8.setError(null);
        inventor8.setError(null);
        issue8.setError(null);
        filing8.setError(null);
        url8.setError(null);
        description8.setError(null);
        title9.setError(null);
        appno9.setError(null);
        inventor9.setError(null);
        issue9.setError(null);
        filing9.setError(null);
        url9.setError(null);
        description9.setError(null);
        title10.setError(null);
        appno10.setError(null);
        inventor10.setError(null);
        issue10.setError(null);
        filing10.setError(null);
        url10.setError(null);
        description10.setError(null);

        stitle1=title1.getText().toString();
        sappno1=appno1.getText().toString();
        selectedCountry1=patoffice1.getSelectedItem().toString();
        sinventor1=inventor1.getText().toString();
        sfiling1=filing1.getText().toString();
        sissue1=issue1.getText().toString();
        surl1=url1.getText().toString();
        sdescription1=description1.getText().toString();
        stitle2=title2.getText().toString();
        sappno2=appno2.getText().toString();
        selectedCountry2=patoffice2.getSelectedItem().toString();
        sinventor2=inventor2.getText().toString();
        sfiling2=filing2.getText().toString();
        sissue2=issue2.getText().toString();
        surl2=url2.getText().toString();
        sdescription2=description2.getText().toString();
        stitle3=title3.getText().toString();
        sappno3=appno3.getText().toString();
        selectedCountry3=patoffice3.getSelectedItem().toString();
        sinventor3=inventor3.getText().toString();
        sfiling3=filing3.getText().toString();
        sissue3=issue3.getText().toString();
        surl3=url3.getText().toString();
        sdescription3=description3.getText().toString();
        stitle4=title4.getText().toString();
        sappno4=appno4.getText().toString();
        selectedCountry4=patoffice4.getSelectedItem().toString();
        sinventor4=inventor4.getText().toString();
        sfiling4=filing4.getText().toString();
        sissue4=issue4.getText().toString();
        surl4=url4.getText().toString();
        sdescription4=description4.getText().toString();
        stitle5=title5.getText().toString();
        sappno5=appno5.getText().toString();
        selectedCountry5=patoffice5.getSelectedItem().toString();
        sinventor5=inventor5.getText().toString();
        sfiling5=filing5.getText().toString();
        sissue5=issue5.getText().toString();
        surl5=url5.getText().toString();
        sdescription5=description5.getText().toString();
        stitle6=title6.getText().toString();
        sappno6=appno6.getText().toString();
        selectedCountry6=patoffice6.getSelectedItem().toString();
        sinventor6=inventor6.getText().toString();
        sfiling6=filing6.getText().toString();
        sissue6=issue6.getText().toString();
        surl6=url6.getText().toString();
        sdescription6=description6.getText().toString();
        stitle7=title7.getText().toString();
        sappno7=appno7.getText().toString();
        selectedCountry7=patoffice7.getSelectedItem().toString();
        sinventor7=inventor7.getText().toString();
        sfiling7=filing7.getText().toString();
        sissue7=issue7.getText().toString();
        surl7=url7.getText().toString();
        sdescription7=description7.getText().toString();
        stitle8=title8.getText().toString();
        sappno8=appno8.getText().toString();
        selectedCountry8=patoffice8.getSelectedItem().toString();
        sinventor8=inventor8.getText().toString();
        sfiling8=filing8.getText().toString();
        sissue8=issue8.getText().toString();
        surl8=url8.getText().toString();
        sdescription8=description8.getText().toString();
        stitle9=title9.getText().toString();
        sappno9=appno9.getText().toString();
        selectedCountry9=patoffice9.getSelectedItem().toString();
        sinventor9=inventor9.getText().toString();
        sfiling9=filing9.getText().toString();
        sissue9=issue9.getText().toString();
        surl9=url9.getText().toString();
        sdescription9=description9.getText().toString();
        stitle10=title10.getText().toString();
        sappno10=appno10.getText().toString();
        selectedCountry10=patoffice10.getSelectedItem().toString();
        sinventor10=inventor10.getText().toString();
        sfiling10=filing10.getText().toString();
        sissue10=issue10.getText().toString();
        surl10=url10.getText().toString();
        sdescription10=description10.getText().toString();

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag=0;

        if(patentcount==0)
        {
            if(stitle1.length()<2)
            {
                errorflag=1;
                title1.setError("Invalid Patent Name");
            }
            else
            {
                errorflag=0;
                if(sappno1.length()<2)
                {
                    errorflag=1;
                    appno1.setError("Invalid Application Number");
                }
                else
                {
                    errorflag=0;
                    if(selectedCountry1.equals("- Select Patent Office -"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        errorflag=0;
                        if(sinventor1.length()<2)
                        {
                            errorflag=1;
                            inventor1.setError("Invalid Name");
                        }
                        else
                        {
                            errorflag=0;
                            if(issuedorpending1.equals("issued")) {
                                if (sissue1.length() < 2) {
                                    errorflag = 1;
                                    issue1.setError("Invalid Date");
                                }
                            }
                            else
                            {
                                if(sfiling1.length()<2)
                                {
                                    errorflag=1;
                                    filing1.setError("Invalid Date");
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(patentcount==1)
        {
            if(stitle1.length()<2)
            {
                errorflag=1;
                title1.setError("Invalid Patent Name");
            }
            else
            {
                errorflag=0;
                if(sappno1.length()<2)
                {
                    errorflag=1;
                    appno1.setError("Invalid Application Number");
                }
                else
                {
                    errorflag=0;
                    if(selectedCountry1.equals("- Select Patent Office -"))
                    {
                        errorflag=1;
                        Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        errorflag=0;
                        if(sinventor1.length()<2)
                        {
                            errorflag=1;
                            inventor1.setError("Invalid Name");
                        }
                        else
                        {
                            errorflag=0;
                            if(issuedorpending1.equals("issued")) {
                                if (sissue1.length() < 2) {
                                    errorflag = 1;
                                    issue1.setError("Invalid Date");
                                }
                            }
                            else
                            {
                                if(sfiling1.length()<2)
                                {
                                    errorflag=1;
                                    filing1.setError("Invalid Date");
                                }
                            }
                            if(stitle2.length()<2)
                            {
                                errorflag=1;
                                title2.setError("Invalid Patent Name");
                            }
                            else
                            {
                                errorflag=0;
                                if(sappno2.length()<2)
                                {
                                    errorflag=1;
                                    appno2.setError("Invalid Application Number");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(selectedCountry2.equals("- Select Patent Office -"))
                                    {
                                        errorflag=1;
                                        Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(sinventor2.length()<2)
                                        {
                                            errorflag=1;
                                            inventor2.setError("Invalid Name");
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(issuedorpending2.equals("issued")) {
                                                if (sissue2.length() < 2) {
                                                    errorflag = 1;
                                                    issue2.setError("Invalid Date");
                                                }
                                            }
                                            else
                                            {
                                                if(sfiling2.length()<2)
                                                {
                                                    errorflag=1;
                                                    filing2.setError("Invalid Date");
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        else if(patentcount==2)
        {
            {
                if(stitle1.length()<2)
                {
                    errorflag=1;
                    title1.setError("Invalid Patent Name");
                }
                else
                {
                    errorflag=0;
                    if(sappno1.length()<2)
                    {
                        errorflag=1;
                        appno1.setError("Invalid Application Number");
                    }
                    else
                    {
                        errorflag=0;
                        if(selectedCountry1.equals("- Select Patent Office -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sinventor1.length()<2)
                            {
                                errorflag=1;
                                inventor1.setError("Invalid Name");
                            }
                            else
                            {
                                errorflag=0;
                                if(issuedorpending1.equals("issued")) {
                                    if (sissue1.length() < 2) {
                                        errorflag = 1;
                                        issue1.setError("Invalid Date");
                                    }
                                }
                                else
                                {
                                    if(sfiling1.length()<2)
                                    {
                                        errorflag=1;
                                        filing1.setError("Invalid Date");
                                    }
                                }
                                if(stitle2.length()<2)
                                {
                                    errorflag=1;
                                    title2.setError("Invalid Patent Name");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sappno2.length()<2)
                                    {
                                        errorflag=1;
                                        appno2.setError("Invalid Application Number");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(selectedCountry2.equals("- Select Patent Office -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sinventor2.length()<2)
                                            {
                                                errorflag=1;
                                                inventor2.setError("Invalid Name");
                                            }
                                            else
                                            {
                                                errorflag=0;
                                                if(issuedorpending2.equals("issued")) {
                                                    if (sissue2.length() < 2) {
                                                        errorflag = 1;
                                                        issue2.setError("Invalid Date");
                                                    }
                                                }
                                                else
                                                {
                                                    if(sfiling2.length()<2)
                                                    {
                                                        errorflag=1;
                                                        filing2.setError("Invalid Date");
                                                    }
                                                }
                                                if(stitle3.length()<3)
                                                {
                                                    errorflag=1;
                                                    title3.setError("Invalid Patent Name");
                                                }
                                                else
                                                {
                                                    errorflag=0;
                                                    if(sappno3.length()<3)
                                                    {
                                                        errorflag=1;
                                                        appno3.setError("Invalid Application Number");
                                                    }
                                                    else
                                                    {
                                                        errorflag=0;
                                                        if(selectedCountry3.equals("- Select Patent Office -"))
                                                        {
                                                            errorflag=1;
                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                        }
                                                        else
                                                        {
                                                            errorflag=0;
                                                            if(sinventor3.length()<3)
                                                            {
                                                                errorflag=1;
                                                                inventor3.setError("Invalid Name");
                                                            }
                                                            else
                                                            {
                                                                errorflag=0;
                                                                if(issuedorpending3.equals("issued")) {
                                                                    if (sissue3.length() < 3) {
                                                                        errorflag = 1;
                                                                        issue3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(sfiling3.length()<3)
                                                                    {
                                                                        errorflag=1;
                                                                        filing3.setError("Invalid Date");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        else if(patentcount==3)
        {
            {
                if(stitle1.length()<2)
                {
                    errorflag=1;
                    title1.setError("Invalid Patent Name");
                }
                else
                {
                    errorflag=0;
                    if(sappno1.length()<2)
                    {
                        errorflag=1;
                        appno1.setError("Invalid Application Number");
                    }
                    else
                    {
                        errorflag=0;
                        if(selectedCountry1.equals("- Select Patent Office -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sinventor1.length()<2)
                            {
                                errorflag=1;
                                inventor1.setError("Invalid Name");
                            }
                            else
                            {
                                errorflag=0;
                                if(issuedorpending1.equals("issued")) {
                                    if (sissue1.length() < 2) {
                                        errorflag = 1;
                                        issue1.setError("Invalid Date");
                                    }
                                }
                                else
                                {
                                    if(sfiling1.length()<2)
                                    {
                                        errorflag=1;
                                        filing1.setError("Invalid Date");
                                    }
                                }
                                if(stitle2.length()<2)
                                {
                                    errorflag=1;
                                    title2.setError("Invalid Patent Name");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sappno2.length()<2)
                                    {
                                        errorflag=1;
                                        appno2.setError("Invalid Application Number");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(selectedCountry2.equals("- Select Patent Office -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sinventor2.length()<2)
                                            {
                                                errorflag=1;
                                                inventor2.setError("Invalid Name");
                                            }
                                            else
                                            {
                                                errorflag=0;
                                                if(issuedorpending2.equals("issued")) {
                                                    if (sissue2.length() < 2) {
                                                        errorflag = 1;
                                                        issue2.setError("Invalid Date");
                                                    }
                                                }
                                                else
                                                {
                                                    if(sfiling2.length()<2)
                                                    {
                                                        errorflag=1;
                                                        filing2.setError("Invalid Date");
                                                    }
                                                }
                                                if(stitle3.length()<3)
                                                {
                                                    errorflag=1;
                                                    title3.setError("Invalid Patent Name");
                                                }
                                                else
                                                {
                                                    errorflag=0;
                                                    if(sappno3.length()<3)
                                                    {
                                                        errorflag=1;
                                                        appno3.setError("Invalid Application Number");
                                                    }
                                                    else
                                                    {
                                                        errorflag=0;
                                                        if(selectedCountry3.equals("- Select Patent Office -"))
                                                        {
                                                            errorflag=1;
                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                        }
                                                        else
                                                        {
                                                            errorflag=0;
                                                            if(sinventor3.length()<3)
                                                            {
                                                                errorflag=1;
                                                                inventor3.setError("Invalid Name");
                                                            }
                                                            else
                                                            {
                                                                errorflag=0;
                                                                if(issuedorpending3.equals("issued")) {
                                                                    if (sissue3.length() < 3) {
                                                                        errorflag = 1;
                                                                        issue3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(sfiling3.length()<3)
                                                                    {
                                                                        errorflag=1;
                                                                        filing3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                if(stitle4.length()<4)
                                                                {
                                                                    errorflag=1;
                                                                    title4.setError("Invalid Patent Name");
                                                                }
                                                                else
                                                                {
                                                                    errorflag=0;
                                                                    if(sappno4.length()<4)
                                                                    {
                                                                        errorflag=1;
                                                                        appno4.setError("Invalid Application Number");
                                                                    }
                                                                    else
                                                                    {
                                                                        errorflag=0;
                                                                        if(selectedCountry4.equals("- Select Patent Office -"))
                                                                        {
                                                                            errorflag=1;
                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                        }
                                                                        else
                                                                        {
                                                                            errorflag=0;
                                                                            if(sinventor4.length()<4)
                                                                            {
                                                                                errorflag=1;
                                                                                inventor4.setError("Invalid Name");
                                                                            }
                                                                            else
                                                                            {
                                                                                errorflag=0;
                                                                                if(issuedorpending4.equals("issued")) {
                                                                                    if (sissue4.length() < 4) {
                                                                                        errorflag = 1;
                                                                                        issue4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    if(sfiling4.length()<4)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        filing4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        else if(patentcount==4)
        {
            {
                if(stitle1.length()<2)
                {
                    errorflag=1;
                    title1.setError("Invalid Patent Name");
                }
                else
                {
                    errorflag=0;
                    if(sappno1.length()<2)
                    {
                        errorflag=1;
                        appno1.setError("Invalid Application Number");
                    }
                    else
                    {
                        errorflag=0;
                        if(selectedCountry1.equals("- Select Patent Office -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sinventor1.length()<2)
                            {
                                errorflag=1;
                                inventor1.setError("Invalid Name");
                            }
                            else
                            {
                                errorflag=0;
                                if(issuedorpending1.equals("issued")) {
                                    if (sissue1.length() < 2) {
                                        errorflag = 1;
                                        issue1.setError("Invalid Date");
                                    }
                                }
                                else
                                {
                                    if(sfiling1.length()<2)
                                    {
                                        errorflag=1;
                                        filing1.setError("Invalid Date");
                                    }
                                }
                                if(stitle2.length()<2)
                                {
                                    errorflag=1;
                                    title2.setError("Invalid Patent Name");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sappno2.length()<2)
                                    {
                                        errorflag=1;
                                        appno2.setError("Invalid Application Number");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(selectedCountry2.equals("- Select Patent Office -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sinventor2.length()<2)
                                            {
                                                errorflag=1;
                                                inventor2.setError("Invalid Name");
                                            }
                                            else
                                            {
                                                errorflag=0;
                                                if(issuedorpending2.equals("issued")) {
                                                    if (sissue2.length() < 2) {
                                                        errorflag = 1;
                                                        issue2.setError("Invalid Date");
                                                    }
                                                }
                                                else
                                                {
                                                    if(sfiling2.length()<2)
                                                    {
                                                        errorflag=1;
                                                        filing2.setError("Invalid Date");
                                                    }
                                                }
                                                if(stitle3.length()<3)
                                                {
                                                    errorflag=1;
                                                    title3.setError("Invalid Patent Name");
                                                }
                                                else
                                                {
                                                    errorflag=0;
                                                    if(sappno3.length()<3)
                                                    {
                                                        errorflag=1;
                                                        appno3.setError("Invalid Application Number");
                                                    }
                                                    else
                                                    {
                                                        errorflag=0;
                                                        if(selectedCountry3.equals("- Select Patent Office -"))
                                                        {
                                                            errorflag=1;
                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                        }
                                                        else
                                                        {
                                                            errorflag=0;
                                                            if(sinventor3.length()<3)
                                                            {
                                                                errorflag=1;
                                                                inventor3.setError("Invalid Name");
                                                            }
                                                            else
                                                            {
                                                                errorflag=0;
                                                                if(issuedorpending3.equals("issued")) {
                                                                    if (sissue3.length() < 3) {
                                                                        errorflag = 1;
                                                                        issue3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(sfiling3.length()<3)
                                                                    {
                                                                        errorflag=1;
                                                                        filing3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                if(stitle4.length()<4)
                                                                {
                                                                    errorflag=1;
                                                                    title4.setError("Invalid Patent Name");
                                                                }
                                                                else
                                                                {
                                                                    errorflag=0;
                                                                    if(sappno4.length()<4)
                                                                    {
                                                                        errorflag=1;
                                                                        appno4.setError("Invalid Application Number");
                                                                    }
                                                                    else
                                                                    {
                                                                        errorflag=0;
                                                                        if(selectedCountry4.equals("- Select Patent Office -"))
                                                                        {
                                                                            errorflag=1;
                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                        }
                                                                        else
                                                                        {
                                                                            errorflag=0;
                                                                            if(sinventor4.length()<4)
                                                                            {
                                                                                errorflag=1;
                                                                                inventor4.setError("Invalid Name");
                                                                            }
                                                                            else
                                                                            {
                                                                                errorflag=0;
                                                                                if(issuedorpending4.equals("issued")) {
                                                                                    if (sissue4.length() < 4) {
                                                                                        errorflag = 1;
                                                                                        issue4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    if(sfiling4.length()<4)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        filing4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                if(stitle5.length()<2)
                                                                                {
                                                                                    errorflag=1;
                                                                                    title5.setError("Invalid Patent Name");
                                                                                }
                                                                                else
                                                                                {
                                                                                    errorflag=0;
                                                                                    if(sappno5.length()<2)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        appno5.setError("Invalid Application Number");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        errorflag=0;
                                                                                        if(selectedCountry5.equals("- Select Patent Office -"))
                                                                                        {
                                                                                            errorflag=1;
                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            errorflag=0;
                                                                                            if(sinventor5.length()<2)
                                                                                            {
                                                                                                errorflag=1;
                                                                                                inventor5.setError("Invalid Name");
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                errorflag=0;
                                                                                                if(issuedorpending5.equals("issued")) {
                                                                                                    if (sissue5.length() < 5) {
                                                                                                        errorflag = 1;
                                                                                                        issue5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    if(sfiling5.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        filing5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }

                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        else if(patentcount==5)
        {
            {
                if(stitle1.length()<2)
                {
                    errorflag=1;
                    title1.setError("Invalid Patent Name");
                }
                else
                {
                    errorflag=0;
                    if(sappno1.length()<2)
                    {
                        errorflag=1;
                        appno1.setError("Invalid Application Number");
                    }
                    else
                    {
                        errorflag=0;
                        if(selectedCountry1.equals("- Select Patent Office -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sinventor1.length()<2)
                            {
                                errorflag=1;
                                inventor1.setError("Invalid Name");
                            }
                            else
                            {
                                errorflag=0;
                                if(issuedorpending1.equals("issued")) {
                                    if (sissue1.length() < 2) {
                                        errorflag = 1;
                                        issue1.setError("Invalid Date");
                                    }
                                }
                                else
                                {
                                    if(sfiling1.length()<2)
                                    {
                                        errorflag=1;
                                        filing1.setError("Invalid Date");
                                    }
                                }
                                if(stitle2.length()<2)
                                {
                                    errorflag=1;
                                    title2.setError("Invalid Patent Name");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sappno2.length()<2)
                                    {
                                        errorflag=1;
                                        appno2.setError("Invalid Application Number");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(selectedCountry2.equals("- Select Patent Office -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sinventor2.length()<2)
                                            {
                                                errorflag=1;
                                                inventor2.setError("Invalid Name");
                                            }
                                            else
                                            {
                                                errorflag=0;
                                                if(issuedorpending2.equals("issued")) {
                                                    if (sissue2.length() < 2) {
                                                        errorflag = 1;
                                                        issue2.setError("Invalid Date");
                                                    }
                                                }
                                                else
                                                {
                                                    if(sfiling2.length()<2)
                                                    {
                                                        errorflag=1;
                                                        filing2.setError("Invalid Date");
                                                    }
                                                }
                                                if(stitle3.length()<3)
                                                {
                                                    errorflag=1;
                                                    title3.setError("Invalid Patent Name");
                                                }
                                                else
                                                {
                                                    errorflag=0;
                                                    if(sappno3.length()<3)
                                                    {
                                                        errorflag=1;
                                                        appno3.setError("Invalid Application Number");
                                                    }
                                                    else
                                                    {
                                                        errorflag=0;
                                                        if(selectedCountry3.equals("- Select Patent Office -"))
                                                        {
                                                            errorflag=1;
                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                        }
                                                        else
                                                        {
                                                            errorflag=0;
                                                            if(sinventor3.length()<3)
                                                            {
                                                                errorflag=1;
                                                                inventor3.setError("Invalid Name");
                                                            }
                                                            else
                                                            {
                                                                errorflag=0;
                                                                if(issuedorpending3.equals("issued")) {
                                                                    if (sissue3.length() < 3) {
                                                                        errorflag = 1;
                                                                        issue3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(sfiling3.length()<3)
                                                                    {
                                                                        errorflag=1;
                                                                        filing3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                if(stitle4.length()<4)
                                                                {
                                                                    errorflag=1;
                                                                    title4.setError("Invalid Patent Name");
                                                                }
                                                                else
                                                                {
                                                                    errorflag=0;
                                                                    if(sappno4.length()<4)
                                                                    {
                                                                        errorflag=1;
                                                                        appno4.setError("Invalid Application Number");
                                                                    }
                                                                    else
                                                                    {
                                                                        errorflag=0;
                                                                        if(selectedCountry4.equals("- Select Patent Office -"))
                                                                        {
                                                                            errorflag=1;
                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                        }
                                                                        else
                                                                        {
                                                                            errorflag=0;
                                                                            if(sinventor4.length()<4)
                                                                            {
                                                                                errorflag=1;
                                                                                inventor4.setError("Invalid Name");
                                                                            }
                                                                            else
                                                                            {
                                                                                errorflag=0;
                                                                                if(issuedorpending4.equals("issued")) {
                                                                                    if (sissue4.length() < 4) {
                                                                                        errorflag = 1;
                                                                                        issue4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    if(sfiling4.length()<4)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        filing4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                if(stitle5.length()<2)
                                                                                {
                                                                                    errorflag=1;
                                                                                    title5.setError("Invalid Patent Name");
                                                                                }
                                                                                else
                                                                                {
                                                                                    errorflag=0;
                                                                                    if(sappno5.length()<2)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        appno5.setError("Invalid Application Number");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        errorflag=0;
                                                                                        if(selectedCountry5.equals("- Select Patent Office -"))
                                                                                        {
                                                                                            errorflag=1;
                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            errorflag=0;
                                                                                            if(sinventor5.length()<2)
                                                                                            {
                                                                                                errorflag=1;
                                                                                                inventor5.setError("Invalid Name");
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                errorflag=0;
                                                                                                if(issuedorpending5.equals("issued")) {
                                                                                                    if (sissue5.length() < 5) {
                                                                                                        errorflag = 1;
                                                                                                        issue5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    if(sfiling5.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        filing5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                if(stitle6.length()<2)
                                                                                                {
                                                                                                    errorflag=1;
                                                                                                    title6.setError("Invalid Patent Name");
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    errorflag=0;
                                                                                                    if(sappno6.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        appno6.setError("Invalid Application Number");
                                                                                                    }
                                                                                                    else
                                                                                                    {
                                                                                                        errorflag=0;
                                                                                                        if(selectedCountry6.equals("- Select Patent Office -"))
                                                                                                        {
                                                                                                            errorflag=1;
                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                        }
                                                                                                        else
                                                                                                        {
                                                                                                            errorflag=0;
                                                                                                            if(sinventor6.length()<2)
                                                                                                            {
                                                                                                                errorflag=1;
                                                                                                                inventor6.setError("Invalid Name");
                                                                                                            }
                                                                                                            else
                                                                                                            {
                                                                                                                errorflag=0;
                                                                                                                if(issuedorpending6.equals("issued")) {
                                                                                                                    if (sissue6.length() < 6) {
                                                                                                                        errorflag = 1;
                                                                                                                        issue6.setError("Invalid Date");
                                                                                                                    }
                                                                                                                }
                                                                                                                else
                                                                                                                {
                                                                                                                    if(sfiling6.length()<2)
                                                                                                                    {
                                                                                                                        errorflag=1;
                                                                                                                        filing6.setError("Invalid Date");
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }

                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }

                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        else if(patentcount==6)
        {
            {
                if(stitle1.length()<2)
                {
                    errorflag=1;
                    title1.setError("Invalid Patent Name");
                }
                else
                {
                    errorflag=0;
                    if(sappno1.length()<2)
                    {
                        errorflag=1;
                        appno1.setError("Invalid Application Number");
                    }
                    else
                    {
                        errorflag=0;
                        if(selectedCountry1.equals("- Select Patent Office -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sinventor1.length()<2)
                            {
                                errorflag=1;
                                inventor1.setError("Invalid Name");
                            }
                            else
                            {
                                errorflag=0;
                                if(issuedorpending1.equals("issued")) {
                                    if (sissue1.length() < 2) {
                                        errorflag = 1;
                                        issue1.setError("Invalid Date");
                                    }
                                }
                                else
                                {
                                    if(sfiling1.length()<2)
                                    {
                                        errorflag=1;
                                        filing1.setError("Invalid Date");
                                    }
                                }
                                if(stitle2.length()<2)
                                {
                                    errorflag=1;
                                    title2.setError("Invalid Patent Name");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sappno2.length()<2)
                                    {
                                        errorflag=1;
                                        appno2.setError("Invalid Application Number");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(selectedCountry2.equals("- Select Patent Office -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sinventor2.length()<2)
                                            {
                                                errorflag=1;
                                                inventor2.setError("Invalid Name");
                                            }
                                            else
                                            {
                                                errorflag=0;
                                                if(issuedorpending2.equals("issued")) {
                                                    if (sissue2.length() < 2) {
                                                        errorflag = 1;
                                                        issue2.setError("Invalid Date");
                                                    }
                                                }
                                                else
                                                {
                                                    if(sfiling2.length()<2)
                                                    {
                                                        errorflag=1;
                                                        filing2.setError("Invalid Date");
                                                    }
                                                }
                                                if(stitle3.length()<3)
                                                {
                                                    errorflag=1;
                                                    title3.setError("Invalid Patent Name");
                                                }
                                                else
                                                {
                                                    errorflag=0;
                                                    if(sappno3.length()<3)
                                                    {
                                                        errorflag=1;
                                                        appno3.setError("Invalid Application Number");
                                                    }
                                                    else
                                                    {
                                                        errorflag=0;
                                                        if(selectedCountry3.equals("- Select Patent Office -"))
                                                        {
                                                            errorflag=1;
                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                        }
                                                        else
                                                        {
                                                            errorflag=0;
                                                            if(sinventor3.length()<3)
                                                            {
                                                                errorflag=1;
                                                                inventor3.setError("Invalid Name");
                                                            }
                                                            else
                                                            {
                                                                errorflag=0;
                                                                if(issuedorpending3.equals("issued")) {
                                                                    if (sissue3.length() < 3) {
                                                                        errorflag = 1;
                                                                        issue3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(sfiling3.length()<3)
                                                                    {
                                                                        errorflag=1;
                                                                        filing3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                if(stitle4.length()<4)
                                                                {
                                                                    errorflag=1;
                                                                    title4.setError("Invalid Patent Name");
                                                                }
                                                                else
                                                                {
                                                                    errorflag=0;
                                                                    if(sappno4.length()<4)
                                                                    {
                                                                        errorflag=1;
                                                                        appno4.setError("Invalid Application Number");
                                                                    }
                                                                    else
                                                                    {
                                                                        errorflag=0;
                                                                        if(selectedCountry4.equals("- Select Patent Office -"))
                                                                        {
                                                                            errorflag=1;
                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                        }
                                                                        else
                                                                        {
                                                                            errorflag=0;
                                                                            if(sinventor4.length()<4)
                                                                            {
                                                                                errorflag=1;
                                                                                inventor4.setError("Invalid Name");
                                                                            }
                                                                            else
                                                                            {
                                                                                errorflag=0;
                                                                                if(issuedorpending4.equals("issued")) {
                                                                                    if (sissue4.length() < 4) {
                                                                                        errorflag = 1;
                                                                                        issue4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    if(sfiling4.length()<4)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        filing4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                if(stitle5.length()<2)
                                                                                {
                                                                                    errorflag=1;
                                                                                    title5.setError("Invalid Patent Name");
                                                                                }
                                                                                else
                                                                                {
                                                                                    errorflag=0;
                                                                                    if(sappno5.length()<2)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        appno5.setError("Invalid Application Number");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        errorflag=0;
                                                                                        if(selectedCountry5.equals("- Select Patent Office -"))
                                                                                        {
                                                                                            errorflag=1;
                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            errorflag=0;
                                                                                            if(sinventor5.length()<2)
                                                                                            {
                                                                                                errorflag=1;
                                                                                                inventor5.setError("Invalid Name");
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                errorflag=0;
                                                                                                if(issuedorpending5.equals("issued")) {
                                                                                                    if (sissue5.length() < 5) {
                                                                                                        errorflag = 1;
                                                                                                        issue5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    if(sfiling5.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        filing5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                if(stitle6.length()<2)
                                                                                                {
                                                                                                    errorflag=1;
                                                                                                    title6.setError("Invalid Patent Name");
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    errorflag=0;
                                                                                                    if(sappno6.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        appno6.setError("Invalid Application Number");
                                                                                                    }
                                                                                                    else
                                                                                                    {
                                                                                                        errorflag=0;
                                                                                                        if(selectedCountry6.equals("- Select Patent Office -"))
                                                                                                        {
                                                                                                            errorflag=1;
                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                        }
                                                                                                        else
                                                                                                        {
                                                                                                            errorflag=0;
                                                                                                            if(sinventor6.length()<2)
                                                                                                            {
                                                                                                                errorflag=1;
                                                                                                                inventor6.setError("Invalid Name");
                                                                                                            }
                                                                                                            else
                                                                                                            {
                                                                                                                errorflag=0;
                                                                                                                if(issuedorpending6.equals("issued")) {
                                                                                                                    if (sissue6.length() < 6) {
                                                                                                                        errorflag = 1;
                                                                                                                        issue6.setError("Invalid Date");
                                                                                                                    }
                                                                                                                }
                                                                                                                else
                                                                                                                {
                                                                                                                    if(sfiling6.length()<2)
                                                                                                                    {
                                                                                                                        errorflag=1;
                                                                                                                        filing6.setError("Invalid Date");
                                                                                                                    }
                                                                                                                }
                                                                                                                if(stitle7.length()<2)
                                                                                                                {
                                                                                                                    errorflag=1;
                                                                                                                    title7.setError("Invalid Patent Name");
                                                                                                                }
                                                                                                                else
                                                                                                                {
                                                                                                                    errorflag=0;
                                                                                                                    if(sappno7.length()<2)
                                                                                                                    {
                                                                                                                        errorflag=1;
                                                                                                                        appno7.setError("Invalid Application Number");
                                                                                                                    }
                                                                                                                    else
                                                                                                                    {
                                                                                                                        errorflag=0;
                                                                                                                        if(selectedCountry7.equals("- Select Patent Office -"))
                                                                                                                        {
                                                                                                                            errorflag=1;
                                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                                        }
                                                                                                                        else
                                                                                                                        {
                                                                                                                            errorflag=0;
                                                                                                                            if(sinventor7.length()<2)
                                                                                                                            {
                                                                                                                                errorflag=1;
                                                                                                                                inventor7.setError("Invalid Name");
                                                                                                                            }
                                                                                                                            else
                                                                                                                            {
                                                                                                                                errorflag=0;
                                                                                                                                if(issuedorpending7.equals("issued")) {
                                                                                                                                    if (sissue7.length() < 7) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        issue7.setError("Invalid Date");
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                else
                                                                                                                                {
                                                                                                                                    if(sfiling7.length()<2)
                                                                                                                                    {
                                                                                                                                        errorflag=1;
                                                                                                                                        filing7.setError("Invalid Date");
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }

                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }

                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }

                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        else if(patentcount==7)
        {
            {
                if(stitle1.length()<2)
                {
                    errorflag=1;
                    title1.setError("Invalid Patent Name");
                }
                else
                {
                    errorflag=0;
                    if(sappno1.length()<2)
                    {
                        errorflag=1;
                        appno1.setError("Invalid Application Number");
                    }
                    else
                    {
                        errorflag=0;
                        if(selectedCountry1.equals("- Select Patent Office -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sinventor1.length()<2)
                            {
                                errorflag=1;
                                inventor1.setError("Invalid Name");
                            }
                            else
                            {
                                errorflag=0;
                                if(issuedorpending1.equals("issued")) {
                                    if (sissue1.length() < 2) {
                                        errorflag = 1;
                                        issue1.setError("Invalid Date");
                                    }
                                }
                                else
                                {
                                    if(sfiling1.length()<2)
                                    {
                                        errorflag=1;
                                        filing1.setError("Invalid Date");
                                    }
                                }
                                if(stitle2.length()<2)
                                {
                                    errorflag=1;
                                    title2.setError("Invalid Patent Name");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sappno2.length()<2)
                                    {
                                        errorflag=1;
                                        appno2.setError("Invalid Application Number");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(selectedCountry2.equals("- Select Patent Office -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sinventor2.length()<2)
                                            {
                                                errorflag=1;
                                                inventor2.setError("Invalid Name");
                                            }
                                            else
                                            {
                                                errorflag=0;
                                                if(issuedorpending2.equals("issued")) {
                                                    if (sissue2.length() < 2) {
                                                        errorflag = 1;
                                                        issue2.setError("Invalid Date");
                                                    }
                                                }
                                                else
                                                {
                                                    if(sfiling2.length()<2)
                                                    {
                                                        errorflag=1;
                                                        filing2.setError("Invalid Date");
                                                    }
                                                }
                                                if(stitle3.length()<3)
                                                {
                                                    errorflag=1;
                                                    title3.setError("Invalid Patent Name");
                                                }
                                                else
                                                {
                                                    errorflag=0;
                                                    if(sappno3.length()<3)
                                                    {
                                                        errorflag=1;
                                                        appno3.setError("Invalid Application Number");
                                                    }
                                                    else
                                                    {
                                                        errorflag=0;
                                                        if(selectedCountry3.equals("- Select Patent Office -"))
                                                        {
                                                            errorflag=1;
                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                        }
                                                        else
                                                        {
                                                            errorflag=0;
                                                            if(sinventor3.length()<3)
                                                            {
                                                                errorflag=1;
                                                                inventor3.setError("Invalid Name");
                                                            }
                                                            else
                                                            {
                                                                errorflag=0;
                                                                if(issuedorpending3.equals("issued")) {
                                                                    if (sissue3.length() < 3) {
                                                                        errorflag = 1;
                                                                        issue3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(sfiling3.length()<3)
                                                                    {
                                                                        errorflag=1;
                                                                        filing3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                if(stitle4.length()<4)
                                                                {
                                                                    errorflag=1;
                                                                    title4.setError("Invalid Patent Name");
                                                                }
                                                                else
                                                                {
                                                                    errorflag=0;
                                                                    if(sappno4.length()<4)
                                                                    {
                                                                        errorflag=1;
                                                                        appno4.setError("Invalid Application Number");
                                                                    }
                                                                    else
                                                                    {
                                                                        errorflag=0;
                                                                        if(selectedCountry4.equals("- Select Patent Office -"))
                                                                        {
                                                                            errorflag=1;
                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                        }
                                                                        else
                                                                        {
                                                                            errorflag=0;
                                                                            if(sinventor4.length()<4)
                                                                            {
                                                                                errorflag=1;
                                                                                inventor4.setError("Invalid Name");
                                                                            }
                                                                            else
                                                                            {
                                                                                errorflag=0;
                                                                                if(issuedorpending4.equals("issued")) {
                                                                                    if (sissue4.length() < 4) {
                                                                                        errorflag = 1;
                                                                                        issue4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    if(sfiling4.length()<4)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        filing4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                if(stitle5.length()<2)
                                                                                {
                                                                                    errorflag=1;
                                                                                    title5.setError("Invalid Patent Name");
                                                                                }
                                                                                else
                                                                                {
                                                                                    errorflag=0;
                                                                                    if(sappno5.length()<2)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        appno5.setError("Invalid Application Number");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        errorflag=0;
                                                                                        if(selectedCountry5.equals("- Select Patent Office -"))
                                                                                        {
                                                                                            errorflag=1;
                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            errorflag=0;
                                                                                            if(sinventor5.length()<2)
                                                                                            {
                                                                                                errorflag=1;
                                                                                                inventor5.setError("Invalid Name");
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                errorflag=0;
                                                                                                if(issuedorpending5.equals("issued")) {
                                                                                                    if (sissue5.length() < 5) {
                                                                                                        errorflag = 1;
                                                                                                        issue5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    if(sfiling5.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        filing5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                if(stitle6.length()<2)
                                                                                                {
                                                                                                    errorflag=1;
                                                                                                    title6.setError("Invalid Patent Name");
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    errorflag=0;
                                                                                                    if(sappno6.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        appno6.setError("Invalid Application Number");
                                                                                                    }
                                                                                                    else
                                                                                                    {
                                                                                                        errorflag=0;
                                                                                                        if(selectedCountry6.equals("- Select Patent Office -"))
                                                                                                        {
                                                                                                            errorflag=1;
                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                        }
                                                                                                        else
                                                                                                        {
                                                                                                            errorflag=0;
                                                                                                            if(sinventor6.length()<2)
                                                                                                            {
                                                                                                                errorflag=1;
                                                                                                                inventor6.setError("Invalid Name");
                                                                                                            }
                                                                                                            else
                                                                                                            {
                                                                                                                errorflag=0;
                                                                                                                if(issuedorpending6.equals("issued")) {
                                                                                                                    if (sissue6.length() < 6) {
                                                                                                                        errorflag = 1;
                                                                                                                        issue6.setError("Invalid Date");
                                                                                                                    }
                                                                                                                }
                                                                                                                else
                                                                                                                {
                                                                                                                    if(sfiling6.length()<2)
                                                                                                                    {
                                                                                                                        errorflag=1;
                                                                                                                        filing6.setError("Invalid Date");
                                                                                                                    }
                                                                                                                }
                                                                                                                if(stitle7.length()<2)
                                                                                                                {
                                                                                                                    errorflag=1;
                                                                                                                    title7.setError("Invalid Patent Name");
                                                                                                                }
                                                                                                                else
                                                                                                                {
                                                                                                                    errorflag=0;
                                                                                                                    if(sappno7.length()<2)
                                                                                                                    {
                                                                                                                        errorflag=1;
                                                                                                                        appno7.setError("Invalid Application Number");
                                                                                                                    }
                                                                                                                    else
                                                                                                                    {
                                                                                                                        errorflag=0;
                                                                                                                        if(selectedCountry7.equals("- Select Patent Office -"))
                                                                                                                        {
                                                                                                                            errorflag=1;
                                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                                        }
                                                                                                                        else
                                                                                                                        {
                                                                                                                            errorflag=0;
                                                                                                                            if(sinventor7.length()<2)
                                                                                                                            {
                                                                                                                                errorflag=1;
                                                                                                                                inventor7.setError("Invalid Name");
                                                                                                                            }
                                                                                                                            else
                                                                                                                            {
                                                                                                                                errorflag=0;
                                                                                                                                if(issuedorpending7.equals("issued")) {
                                                                                                                                    if (sissue7.length() < 7) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        issue7.setError("Invalid Date");
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                else
                                                                                                                                {
                                                                                                                                    if(sfiling7.length()<2)
                                                                                                                                    {
                                                                                                                                        errorflag=1;
                                                                                                                                        filing7.setError("Invalid Date");
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                if(stitle8.length()<2)
                                                                                                                                {
                                                                                                                                    errorflag=1;
                                                                                                                                    title8.setError("Invalid Patent Name");
                                                                                                                                }
                                                                                                                                else
                                                                                                                                {
                                                                                                                                    errorflag=0;
                                                                                                                                    if(sappno8.length()<2)
                                                                                                                                    {
                                                                                                                                        errorflag=1;
                                                                                                                                        appno8.setError("Invalid Application Number");
                                                                                                                                    }
                                                                                                                                    else
                                                                                                                                    {
                                                                                                                                        errorflag=0;
                                                                                                                                        if(selectedCountry8.equals("- Select Patent Office -"))
                                                                                                                                        {
                                                                                                                                            errorflag=1;
                                                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                                                        }
                                                                                                                                        else
                                                                                                                                        {
                                                                                                                                            errorflag=0;
                                                                                                                                            if(sinventor8.length()<2)
                                                                                                                                            {
                                                                                                                                                errorflag=1;
                                                                                                                                                inventor8.setError("Invalid Name");
                                                                                                                                            }
                                                                                                                                            else
                                                                                                                                            {
                                                                                                                                                errorflag=0;
                                                                                                                                                if(issuedorpending8.equals("issued")) {
                                                                                                                                                    if (sissue8.length() < 8) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        issue8.setError("Invalid Date");
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                else
                                                                                                                                                {
                                                                                                                                                    if(sfiling8.length()<2)
                                                                                                                                                    {
                                                                                                                                                        errorflag=1;
                                                                                                                                                        filing8.setError("Invalid Date");
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }

                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }

                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }

                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }

                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        else if(patentcount==8)
        {
            {
                if(stitle1.length()<2)
                {
                    errorflag=1;
                    title1.setError("Invalid Patent Name");
                }
                else
                {
                    errorflag=0;
                    if(sappno1.length()<2)
                    {
                        errorflag=1;
                        appno1.setError("Invalid Application Number");
                    }
                    else
                    {
                        errorflag=0;
                        if(selectedCountry1.equals("- Select Patent Office -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sinventor1.length()<2)
                            {
                                errorflag=1;
                                inventor1.setError("Invalid Name");
                            }
                            else
                            {
                                errorflag=0;
                                if(issuedorpending1.equals("issued")) {
                                    if (sissue1.length() < 2) {
                                        errorflag = 1;
                                        issue1.setError("Invalid Date");
                                    }
                                }
                                else
                                {
                                    if(sfiling1.length()<2)
                                    {
                                        errorflag=1;
                                        filing1.setError("Invalid Date");
                                    }
                                }
                                if(stitle2.length()<2)
                                {
                                    errorflag=1;
                                    title2.setError("Invalid Patent Name");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sappno2.length()<2)
                                    {
                                        errorflag=1;
                                        appno2.setError("Invalid Application Number");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(selectedCountry2.equals("- Select Patent Office -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sinventor2.length()<2)
                                            {
                                                errorflag=1;
                                                inventor2.setError("Invalid Name");
                                            }
                                            else
                                            {
                                                errorflag=0;
                                                if(issuedorpending2.equals("issued")) {
                                                    if (sissue2.length() < 2) {
                                                        errorflag = 1;
                                                        issue2.setError("Invalid Date");
                                                    }
                                                }
                                                else
                                                {
                                                    if(sfiling2.length()<2)
                                                    {
                                                        errorflag=1;
                                                        filing2.setError("Invalid Date");
                                                    }
                                                }
                                                if(stitle3.length()<3)
                                                {
                                                    errorflag=1;
                                                    title3.setError("Invalid Patent Name");
                                                }
                                                else
                                                {
                                                    errorflag=0;
                                                    if(sappno3.length()<3)
                                                    {
                                                        errorflag=1;
                                                        appno3.setError("Invalid Application Number");
                                                    }
                                                    else
                                                    {
                                                        errorflag=0;
                                                        if(selectedCountry3.equals("- Select Patent Office -"))
                                                        {
                                                            errorflag=1;
                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                        }
                                                        else
                                                        {
                                                            errorflag=0;
                                                            if(sinventor3.length()<3)
                                                            {
                                                                errorflag=1;
                                                                inventor3.setError("Invalid Name");
                                                            }
                                                            else
                                                            {
                                                                errorflag=0;
                                                                if(issuedorpending3.equals("issued")) {
                                                                    if (sissue3.length() < 3) {
                                                                        errorflag = 1;
                                                                        issue3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(sfiling3.length()<3)
                                                                    {
                                                                        errorflag=1;
                                                                        filing3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                if(stitle4.length()<4)
                                                                {
                                                                    errorflag=1;
                                                                    title4.setError("Invalid Patent Name");
                                                                }
                                                                else
                                                                {
                                                                    errorflag=0;
                                                                    if(sappno4.length()<4)
                                                                    {
                                                                        errorflag=1;
                                                                        appno4.setError("Invalid Application Number");
                                                                    }
                                                                    else
                                                                    {
                                                                        errorflag=0;
                                                                        if(selectedCountry4.equals("- Select Patent Office -"))
                                                                        {
                                                                            errorflag=1;
                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                        }
                                                                        else
                                                                        {
                                                                            errorflag=0;
                                                                            if(sinventor4.length()<4)
                                                                            {
                                                                                errorflag=1;
                                                                                inventor4.setError("Invalid Name");
                                                                            }
                                                                            else
                                                                            {
                                                                                errorflag=0;
                                                                                if(issuedorpending4.equals("issued")) {
                                                                                    if (sissue4.length() < 4) {
                                                                                        errorflag = 1;
                                                                                        issue4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    if(sfiling4.length()<4)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        filing4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                if(stitle5.length()<2)
                                                                                {
                                                                                    errorflag=1;
                                                                                    title5.setError("Invalid Patent Name");
                                                                                }
                                                                                else
                                                                                {
                                                                                    errorflag=0;
                                                                                    if(sappno5.length()<2)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        appno5.setError("Invalid Application Number");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        errorflag=0;
                                                                                        if(selectedCountry5.equals("- Select Patent Office -"))
                                                                                        {
                                                                                            errorflag=1;
                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            errorflag=0;
                                                                                            if(sinventor5.length()<2)
                                                                                            {
                                                                                                errorflag=1;
                                                                                                inventor5.setError("Invalid Name");
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                errorflag=0;
                                                                                                if(issuedorpending5.equals("issued")) {
                                                                                                    if (sissue5.length() < 5) {
                                                                                                        errorflag = 1;
                                                                                                        issue5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    if(sfiling5.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        filing5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                if(stitle6.length()<2)
                                                                                                {
                                                                                                    errorflag=1;
                                                                                                    title6.setError("Invalid Patent Name");
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    errorflag=0;
                                                                                                    if(sappno6.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        appno6.setError("Invalid Application Number");
                                                                                                    }
                                                                                                    else
                                                                                                    {
                                                                                                        errorflag=0;
                                                                                                        if(selectedCountry6.equals("- Select Patent Office -"))
                                                                                                        {
                                                                                                            errorflag=1;
                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                        }
                                                                                                        else
                                                                                                        {
                                                                                                            errorflag=0;
                                                                                                            if(sinventor6.length()<2)
                                                                                                            {
                                                                                                                errorflag=1;
                                                                                                                inventor6.setError("Invalid Name");
                                                                                                            }
                                                                                                            else
                                                                                                            {
                                                                                                                errorflag=0;
                                                                                                                if(issuedorpending6.equals("issued")) {
                                                                                                                    if (sissue6.length() < 6) {
                                                                                                                        errorflag = 1;
                                                                                                                        issue6.setError("Invalid Date");
                                                                                                                    }
                                                                                                                }
                                                                                                                else
                                                                                                                {
                                                                                                                    if(sfiling6.length()<2)
                                                                                                                    {
                                                                                                                        errorflag=1;
                                                                                                                        filing6.setError("Invalid Date");
                                                                                                                    }
                                                                                                                }
                                                                                                                if(stitle7.length()<2)
                                                                                                                {
                                                                                                                    errorflag=1;
                                                                                                                    title7.setError("Invalid Patent Name");
                                                                                                                }
                                                                                                                else
                                                                                                                {
                                                                                                                    errorflag=0;
                                                                                                                    if(sappno7.length()<2)
                                                                                                                    {
                                                                                                                        errorflag=1;
                                                                                                                        appno7.setError("Invalid Application Number");
                                                                                                                    }
                                                                                                                    else
                                                                                                                    {
                                                                                                                        errorflag=0;
                                                                                                                        if(selectedCountry7.equals("- Select Patent Office -"))
                                                                                                                        {
                                                                                                                            errorflag=1;
                                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                                        }
                                                                                                                        else
                                                                                                                        {
                                                                                                                            errorflag=0;
                                                                                                                            if(sinventor7.length()<2)
                                                                                                                            {
                                                                                                                                errorflag=1;
                                                                                                                                inventor7.setError("Invalid Name");
                                                                                                                            }
                                                                                                                            else
                                                                                                                            {
                                                                                                                                errorflag=0;
                                                                                                                                if(issuedorpending7.equals("issued")) {
                                                                                                                                    if (sissue7.length() < 7) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        issue7.setError("Invalid Date");
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                else
                                                                                                                                {
                                                                                                                                    if(sfiling7.length()<2)
                                                                                                                                    {
                                                                                                                                        errorflag=1;
                                                                                                                                        filing7.setError("Invalid Date");
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                if(stitle8.length()<2)
                                                                                                                                {
                                                                                                                                    errorflag=1;
                                                                                                                                    title8.setError("Invalid Patent Name");
                                                                                                                                }
                                                                                                                                else
                                                                                                                                {
                                                                                                                                    errorflag=0;
                                                                                                                                    if(sappno8.length()<2)
                                                                                                                                    {
                                                                                                                                        errorflag=1;
                                                                                                                                        appno8.setError("Invalid Application Number");
                                                                                                                                    }
                                                                                                                                    else
                                                                                                                                    {
                                                                                                                                        errorflag=0;
                                                                                                                                        if(selectedCountry8.equals("- Select Patent Office -"))
                                                                                                                                        {
                                                                                                                                            errorflag=1;
                                                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                                                        }
                                                                                                                                        else
                                                                                                                                        {
                                                                                                                                            errorflag=0;
                                                                                                                                            if(sinventor8.length()<2)
                                                                                                                                            {
                                                                                                                                                errorflag=1;
                                                                                                                                                inventor8.setError("Invalid Name");
                                                                                                                                            }
                                                                                                                                            else
                                                                                                                                            {
                                                                                                                                                errorflag=0;
                                                                                                                                                if(issuedorpending8.equals("issued")) {
                                                                                                                                                    if (sissue8.length() < 8) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        issue8.setError("Invalid Date");
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                else
                                                                                                                                                {
                                                                                                                                                    if(sfiling8.length()<2)
                                                                                                                                                    {
                                                                                                                                                        errorflag=1;
                                                                                                                                                        filing8.setError("Invalid Date");
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                if(stitle9.length()<2)
                                                                                                                                                {
                                                                                                                                                    errorflag=1;
                                                                                                                                                    title9.setError("Invalid Patent Name");
                                                                                                                                                }
                                                                                                                                                else
                                                                                                                                                {
                                                                                                                                                    errorflag=0;
                                                                                                                                                    if(sappno9.length()<2)
                                                                                                                                                    {
                                                                                                                                                        errorflag=1;
                                                                                                                                                        appno9.setError("Invalid Application Number");
                                                                                                                                                    }
                                                                                                                                                    else
                                                                                                                                                    {
                                                                                                                                                        errorflag=0;
                                                                                                                                                        if(selectedCountry9.equals("- Select Patent Office -"))
                                                                                                                                                        {
                                                                                                                                                            errorflag=1;
                                                                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                                                                        }
                                                                                                                                                        else
                                                                                                                                                        {
                                                                                                                                                            errorflag=0;
                                                                                                                                                            if(sinventor9.length()<2)
                                                                                                                                                            {
                                                                                                                                                                errorflag=1;
                                                                                                                                                                inventor9.setError("Invalid Name");
                                                                                                                                                            }
                                                                                                                                                            else
                                                                                                                                                            {
                                                                                                                                                                errorflag=0;
                                                                                                                                                                if(issuedorpending9.equals("issued")) {
                                                                                                                                                                    if (sissue9.length() < 9) {
                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                        issue9.setError("Invalid Date");
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                                else
                                                                                                                                                                {
                                                                                                                                                                    if(sfiling9.length()<2)
                                                                                                                                                                    {
                                                                                                                                                                        errorflag=1;
                                                                                                                                                                        filing9.setError("Invalid Date");
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }

                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }

                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }

                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }

                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        else if(patentcount==9)
        {
            {
                if(stitle1.length()<2)
                {
                    errorflag=1;
                    title1.setError("Invalid Patent Name");
                }
                else
                {
                    errorflag=0;
                    if(sappno1.length()<2)
                    {
                        errorflag=1;
                        appno1.setError("Invalid Application Number");
                    }
                    else
                    {
                        errorflag=0;
                        if(selectedCountry1.equals("- Select Patent Office -"))
                        {
                            errorflag=1;
                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            errorflag=0;
                            if(sinventor1.length()<2)
                            {
                                errorflag=1;
                                inventor1.setError("Invalid Name");
                            }
                            else
                            {
                                errorflag=0;
                                if(issuedorpending1.equals("issued")) {
                                    if (sissue1.length() < 2) {
                                        errorflag = 1;
                                        issue1.setError("Invalid Date");
                                    }
                                }
                                else
                                {
                                    if(sfiling1.length()<2)
                                    {
                                        errorflag=1;
                                        filing1.setError("Invalid Date");
                                    }
                                }
                                if(stitle2.length()<2)
                                {
                                    errorflag=1;
                                    title2.setError("Invalid Patent Name");
                                }
                                else
                                {
                                    errorflag=0;
                                    if(sappno2.length()<2)
                                    {
                                        errorflag=1;
                                        appno2.setError("Invalid Application Number");
                                    }
                                    else
                                    {
                                        errorflag=0;
                                        if(selectedCountry2.equals("- Select Patent Office -"))
                                        {
                                            errorflag=1;
                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            errorflag=0;
                                            if(sinventor2.length()<2)
                                            {
                                                errorflag=1;
                                                inventor2.setError("Invalid Name");
                                            }
                                            else
                                            {
                                                errorflag=0;
                                                if(issuedorpending2.equals("issued")) {
                                                    if (sissue2.length() < 2) {
                                                        errorflag = 1;
                                                        issue2.setError("Invalid Date");
                                                    }
                                                }
                                                else
                                                {
                                                    if(sfiling2.length()<2)
                                                    {
                                                        errorflag=1;
                                                        filing2.setError("Invalid Date");
                                                    }
                                                }
                                                if(stitle3.length()<3)
                                                {
                                                    errorflag=1;
                                                    title3.setError("Invalid Patent Name");
                                                }
                                                else
                                                {
                                                    errorflag=0;
                                                    if(sappno3.length()<3)
                                                    {
                                                        errorflag=1;
                                                        appno3.setError("Invalid Application Number");
                                                    }
                                                    else
                                                    {
                                                        errorflag=0;
                                                        if(selectedCountry3.equals("- Select Patent Office -"))
                                                        {
                                                            errorflag=1;
                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                        }
                                                        else
                                                        {
                                                            errorflag=0;
                                                            if(sinventor3.length()<3)
                                                            {
                                                                errorflag=1;
                                                                inventor3.setError("Invalid Name");
                                                            }
                                                            else
                                                            {
                                                                errorflag=0;
                                                                if(issuedorpending3.equals("issued")) {
                                                                    if (sissue3.length() < 3) {
                                                                        errorflag = 1;
                                                                        issue3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(sfiling3.length()<3)
                                                                    {
                                                                        errorflag=1;
                                                                        filing3.setError("Invalid Date");
                                                                    }
                                                                }
                                                                if(stitle4.length()<4)
                                                                {
                                                                    errorflag=1;
                                                                    title4.setError("Invalid Patent Name");
                                                                }
                                                                else
                                                                {
                                                                    errorflag=0;
                                                                    if(sappno4.length()<4)
                                                                    {
                                                                        errorflag=1;
                                                                        appno4.setError("Invalid Application Number");
                                                                    }
                                                                    else
                                                                    {
                                                                        errorflag=0;
                                                                        if(selectedCountry4.equals("- Select Patent Office -"))
                                                                        {
                                                                            errorflag=1;
                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                        }
                                                                        else
                                                                        {
                                                                            errorflag=0;
                                                                            if(sinventor4.length()<4)
                                                                            {
                                                                                errorflag=1;
                                                                                inventor4.setError("Invalid Name");
                                                                            }
                                                                            else
                                                                            {
                                                                                errorflag=0;
                                                                                if(issuedorpending4.equals("issued")) {
                                                                                    if (sissue4.length() < 4) {
                                                                                        errorflag = 1;
                                                                                        issue4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    if(sfiling4.length()<4)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        filing4.setError("Invalid Date");
                                                                                    }
                                                                                }
                                                                                if(stitle5.length()<2)
                                                                                {
                                                                                    errorflag=1;
                                                                                    title5.setError("Invalid Patent Name");
                                                                                }
                                                                                else
                                                                                {
                                                                                    errorflag=0;
                                                                                    if(sappno5.length()<2)
                                                                                    {
                                                                                        errorflag=1;
                                                                                        appno5.setError("Invalid Application Number");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        errorflag=0;
                                                                                        if(selectedCountry5.equals("- Select Patent Office -"))
                                                                                        {
                                                                                            errorflag=1;
                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            errorflag=0;
                                                                                            if(sinventor5.length()<2)
                                                                                            {
                                                                                                errorflag=1;
                                                                                                inventor5.setError("Invalid Name");
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                errorflag=0;
                                                                                                if(issuedorpending5.equals("issued")) {
                                                                                                    if (sissue5.length() < 5) {
                                                                                                        errorflag = 1;
                                                                                                        issue5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    if(sfiling5.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        filing5.setError("Invalid Date");
                                                                                                    }
                                                                                                }
                                                                                                if(stitle6.length()<2)
                                                                                                {
                                                                                                    errorflag=1;
                                                                                                    title6.setError("Invalid Patent Name");
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    errorflag=0;
                                                                                                    if(sappno6.length()<2)
                                                                                                    {
                                                                                                        errorflag=1;
                                                                                                        appno6.setError("Invalid Application Number");
                                                                                                    }
                                                                                                    else
                                                                                                    {
                                                                                                        errorflag=0;
                                                                                                        if(selectedCountry6.equals("- Select Patent Office -"))
                                                                                                        {
                                                                                                            errorflag=1;
                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                        }
                                                                                                        else
                                                                                                        {
                                                                                                            errorflag=0;
                                                                                                            if(sinventor6.length()<2)
                                                                                                            {
                                                                                                                errorflag=1;
                                                                                                                inventor6.setError("Invalid Name");
                                                                                                            }
                                                                                                            else
                                                                                                            {
                                                                                                                errorflag=0;
                                                                                                                if(issuedorpending6.equals("issued")) {
                                                                                                                    if (sissue6.length() < 6) {
                                                                                                                        errorflag = 1;
                                                                                                                        issue6.setError("Invalid Date");
                                                                                                                    }
                                                                                                                }
                                                                                                                else
                                                                                                                {
                                                                                                                    if(sfiling6.length()<2)
                                                                                                                    {
                                                                                                                        errorflag=1;
                                                                                                                        filing6.setError("Invalid Date");
                                                                                                                    }
                                                                                                                }
                                                                                                                if(stitle7.length()<2)
                                                                                                                {
                                                                                                                    errorflag=1;
                                                                                                                    title7.setError("Invalid Patent Name");
                                                                                                                }
                                                                                                                else
                                                                                                                {
                                                                                                                    errorflag=0;
                                                                                                                    if(sappno7.length()<2)
                                                                                                                    {
                                                                                                                        errorflag=1;
                                                                                                                        appno7.setError("Invalid Application Number");
                                                                                                                    }
                                                                                                                    else
                                                                                                                    {
                                                                                                                        errorflag=0;
                                                                                                                        if(selectedCountry7.equals("- Select Patent Office -"))
                                                                                                                        {
                                                                                                                            errorflag=1;
                                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                                        }
                                                                                                                        else
                                                                                                                        {
                                                                                                                            errorflag=0;
                                                                                                                            if(sinventor7.length()<2)
                                                                                                                            {
                                                                                                                                errorflag=1;
                                                                                                                                inventor7.setError("Invalid Name");
                                                                                                                            }
                                                                                                                            else
                                                                                                                            {
                                                                                                                                errorflag=0;
                                                                                                                                if(issuedorpending7.equals("issued")) {
                                                                                                                                    if (sissue7.length() < 7) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        issue7.setError("Invalid Date");
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                else
                                                                                                                                {
                                                                                                                                    if(sfiling7.length()<2)
                                                                                                                                    {
                                                                                                                                        errorflag=1;
                                                                                                                                        filing7.setError("Invalid Date");
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                if(stitle8.length()<2)
                                                                                                                                {
                                                                                                                                    errorflag=1;
                                                                                                                                    title8.setError("Invalid Patent Name");
                                                                                                                                }
                                                                                                                                else
                                                                                                                                {
                                                                                                                                    errorflag=0;
                                                                                                                                    if(sappno8.length()<2)
                                                                                                                                    {
                                                                                                                                        errorflag=1;
                                                                                                                                        appno8.setError("Invalid Application Number");
                                                                                                                                    }
                                                                                                                                    else
                                                                                                                                    {
                                                                                                                                        errorflag=0;
                                                                                                                                        if(selectedCountry8.equals("- Select Patent Office -"))
                                                                                                                                        {
                                                                                                                                            errorflag=1;
                                                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                                                        }
                                                                                                                                        else
                                                                                                                                        {
                                                                                                                                            errorflag=0;
                                                                                                                                            if(sinventor8.length()<2)
                                                                                                                                            {
                                                                                                                                                errorflag=1;
                                                                                                                                                inventor8.setError("Invalid Name");
                                                                                                                                            }
                                                                                                                                            else
                                                                                                                                            {
                                                                                                                                                errorflag=0;
                                                                                                                                                if(issuedorpending8.equals("issued")) {
                                                                                                                                                    if (sissue8.length() < 8) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        issue8.setError("Invalid Date");
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                else
                                                                                                                                                {
                                                                                                                                                    if(sfiling8.length()<2)
                                                                                                                                                    {
                                                                                                                                                        errorflag=1;
                                                                                                                                                        filing8.setError("Invalid Date");
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                if(stitle9.length()<2)
                                                                                                                                                {
                                                                                                                                                    errorflag=1;
                                                                                                                                                    title9.setError("Invalid Patent Name");
                                                                                                                                                }
                                                                                                                                                else
                                                                                                                                                {
                                                                                                                                                    errorflag=0;
                                                                                                                                                    if(sappno9.length()<2)
                                                                                                                                                    {
                                                                                                                                                        errorflag=1;
                                                                                                                                                        appno9.setError("Invalid Application Number");
                                                                                                                                                    }
                                                                                                                                                    else
                                                                                                                                                    {
                                                                                                                                                        errorflag=0;
                                                                                                                                                        if(selectedCountry9.equals("- Select Patent Office -"))
                                                                                                                                                        {
                                                                                                                                                            errorflag=1;
                                                                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                                                                        }
                                                                                                                                                        else
                                                                                                                                                        {
                                                                                                                                                            errorflag=0;
                                                                                                                                                            if(sinventor9.length()<2)
                                                                                                                                                            {
                                                                                                                                                                errorflag=1;
                                                                                                                                                                inventor9.setError("Invalid Name");
                                                                                                                                                            }
                                                                                                                                                            else
                                                                                                                                                            {
                                                                                                                                                                errorflag=0;
                                                                                                                                                                if(issuedorpending9.equals("issued")) {
                                                                                                                                                                    if (sissue9.length() < 9) {
                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                        issue9.setError("Invalid Date");
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                                else
                                                                                                                                                                {
                                                                                                                                                                    if(sfiling9.length()<2)
                                                                                                                                                                    {
                                                                                                                                                                        errorflag=1;
                                                                                                                                                                        filing9.setError("Invalid Date");
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                                if(stitle10.length()<2)
                                                                                                                                                                {
                                                                                                                                                                    errorflag=1;
                                                                                                                                                                    title10.setError("Invalid Patent Name");
                                                                                                                                                                }
                                                                                                                                                                else
                                                                                                                                                                {
                                                                                                                                                                    errorflag=0;
                                                                                                                                                                    if(sappno10.length()<2)
                                                                                                                                                                    {
                                                                                                                                                                        errorflag=1;
                                                                                                                                                                        appno10.setError("Invalid Application Number");
                                                                                                                                                                    }
                                                                                                                                                                    else
                                                                                                                                                                    {
                                                                                                                                                                        errorflag=0;
                                                                                                                                                                        if(selectedCountry10.equals("- Select Patent Office -"))
                                                                                                                                                                        {
                                                                                                                                                                            errorflag=1;
                                                                                                                                                                            Toast.makeText(MyProfilePatents.this,"Select Patent Office",Toast.LENGTH_LONG).show();
                                                                                                                                                                        }
                                                                                                                                                                        else
                                                                                                                                                                        {
                                                                                                                                                                            errorflag=0;
                                                                                                                                                                            if(sinventor10.length()<2)
                                                                                                                                                                            {
                                                                                                                                                                                errorflag=1;
                                                                                                                                                                                inventor10.setError("Invalid Name");
                                                                                                                                                                            }
                                                                                                                                                                            else
                                                                                                                                                                            {
                                                                                                                                                                                errorflag=0;
                                                                                                                                                                                if(issuedorpending10.equals("issued")) {
                                                                                                                                                                                    if (sissue10.length() < 10) {
                                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                                        issue10.setError("Invalid Date");
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                                else
                                                                                                                                                                                {
                                                                                                                                                                                    if(sfiling10.length()<2)
                                                                                                                                                                                    {
                                                                                                                                                                                        errorflag=1;
                                                                                                                                                                                        filing10.setError("Invalid Date");
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }

                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }

                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }

                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }

                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }

        if(errorflag==0) {
            try
            {

                Patents obj1=new Patents(stitle1,sappno1,selectedCountry1,sinventor1,issuedorpending1,sissue1,sfiling1,surl1,sdescription1);
                Patents obj2=new Patents(stitle2,sappno2,selectedCountry2,sinventor2,issuedorpending2,sissue2,sfiling2,surl2,sdescription2);
                Patents obj3=new Patents(stitle3,sappno3,selectedCountry3,sinventor3,issuedorpending3,sissue3,sfiling3,surl3,sdescription3);
                Patents obj4=new Patents(stitle4,sappno4,selectedCountry4,sinventor4,issuedorpending4,sissue4,sfiling4,surl4,sdescription4);
                Patents obj5=new Patents(stitle5,sappno5,selectedCountry5,sinventor5,issuedorpending5,sissue5,sfiling5,surl5,sdescription5);
                Patents obj6=new Patents(stitle6,sappno6,selectedCountry6,sinventor6,issuedorpending6,sissue6,sfiling6,surl6,sdescription6);
                Patents obj7=new Patents(stitle7,sappno7,selectedCountry7,sinventor7,issuedorpending7,sissue7,sfiling7,surl7,sdescription7);
                Patents obj8=new Patents(stitle8,sappno8,selectedCountry8,sinventor8,issuedorpending8,sissue8,sfiling8,surl8,sdescription8);
                Patents obj9=new Patents(stitle9,sappno9,selectedCountry9,sinventor9,issuedorpending9,sissue9,sfiling9,surl9,sdescription9);
                Patents obj10=new Patents(stitle10,sappno10,selectedCountry10,sinventor10,issuedorpending10,sissue10,sfiling10,surl10,sdescription10);

                patentsList.add(obj1);
                patentsList.add(obj2);
                patentsList.add(obj3);
                patentsList.add(obj4);
                patentsList.add(obj5);
                patentsList.add(obj6);
                patentsList.add(obj7);
                patentsList.add(obj8);
                patentsList.add(obj9);
                patentsList.add(obj10);

                String encObjString=OtoString(patentsList,MySharedPreferencesManager.getDigest1(MyProfilePatents.this),MySharedPreferencesManager.getDigest2(MyProfilePatents.this));

                new SavePatents().execute(encObjString);



            }catch (Exception e){Toast.makeText(MyProfilePatents.this,e.getMessage(),Toast.LENGTH_LONG).show();}
        }

    }
    class SavePatents extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("d",param[0]));       //1


            json = jParser.makeHttpRequest(MyConstants.url_savepatents, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
                Toast.makeText(MyProfilePatents.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();


                if(role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("hr"))
                setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);

                s.setPtitle1(stitle1);
                s.setPappno1(sappno1);
                s.setPinventor1(sinventor1);
                s.setPissue1(sissue1);
                s.setPfiling1(sfiling1);
                s.setPurl1(surl1);
                s.setPdescription1(sdescription1);
                s.setPselectedcountry1(selectedCountry1);
                s.setIssuedorpending1(issuedorpending1);
                s.setPtitle2(stitle2);
                s.setPappno2(sappno2);
                s.setPinventor2(sinventor2);
                s.setPissue2(sissue2);
                s.setPfiling2(sfiling2);
                s.setPurl2(surl2);
                s.setPdescription2(sdescription2);
                s.setPselectedcountry2(selectedCountry2);

                s.setIssuedorpending2(issuedorpending2);
                s.setPtitle3(stitle3);
                s.setPappno3(sappno3);
                s.setPinventor3(sinventor3);
                s.setPissue3(sissue3);
                s.setPfiling3(sfiling3);
                s.setPurl3(surl3);
                s.setPdescription3(sdescription3);
                s.setPselectedcountry3(selectedCountry3);
                s.setIssuedorpending3(issuedorpending3);
                s.setPtitle4(stitle4);
                s.setPappno4(sappno4);
                s.setPinventor4(sinventor4);
                s.setPissue4(sissue4);
                s.setPfiling4(sfiling4);
                s.setPurl4(surl4);
                s.setPdescription4(sdescription4);
                s.setPselectedcountry4(selectedCountry4);
                s.setIssuedorpending4(issuedorpending4);
                s.setPtitle5(stitle5);
                s.setPappno5(sappno5);
                s.setPinventor5(sinventor5);
                s.setPissue5(sissue5);
                s.setPfiling5(sfiling5);
                s.setPurl5(surl5);
                s.setPdescription5(sdescription5);
                s.setPselectedcountry5(selectedCountry5);
                s.setIssuedorpending5(issuedorpending5);
                s.setPtitle6(stitle6);
                s.setPappno6(sappno6);
                s.setPinventor6(sinventor6);
                s.setPissue6(sissue6);
                s.setPfiling6(sfiling6);
                s.setPurl6(surl6);
                s.setPdescription6(sdescription6);
                s.setPselectedcountry6(selectedCountry6);
                s.setIssuedorpending6(issuedorpending6);
                s.setPtitle7(stitle7);
                s.setPappno7(sappno7);
                s.setPinventor7(sinventor7);
                s.setPissue7(sissue7);
                s.setPfiling7(sfiling7);
                s.setPurl7(surl7);
                s.setPdescription7(sdescription7);
                s.setPselectedcountry7(selectedCountry7);
                s.setIssuedorpending7(issuedorpending7);
                s.setPtitle8(stitle8);
                s.setPappno8(sappno8);
                s.setPinventor8(sinventor8);
                s.setPissue8(sissue8);
                s.setPfiling8(sfiling8);
                s.setPurl8(surl8);
                s.setPdescription8(sdescription8);
                s.setPselectedcountry8(selectedCountry8);

                s.setIssuedorpending8(issuedorpending8);
                s.setPtitle9(stitle9);
                s.setPappno9(sappno9);
                s.setPinventor9(sinventor9);
                s.setPissue9(sissue9);
                s.setPfiling9(sfiling9);
                s.setPurl9(surl9);
                s.setPdescription9(sdescription9);
                s.setPselectedcountry9(selectedCountry9);
                s.setIssuedorpending9(issuedorpending9);
                s.setPtitle10(stitle10);
                s.setPappno10(sappno10);
                s.setPinventor10(sinventor10);
                s.setPissue10(sissue10);
                s.setPfiling10(sfiling10);
                s.setPurl10(surl10);
                s.setPdescription10(sdescription10);
                s.setPselectedcountry10(selectedCountry10);
                s.setIssuedorpending10(issuedorpending10);

                MyProfilePatents.super.onBackPressed();
            }
            else
                Toast.makeText(MyProfilePatents.this,result,Toast.LENGTH_SHORT).show();

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

        if(edittedFlag==1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfilePatents.super.onBackPressed();
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
            MyProfilePatents.super.onBackPressed();

    }

    void showDateDialog(final EditText id)
    {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfilePatents.this);
        LayoutInflater inflater = MyProfilePatents.this.getLayoutInflater();
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

//        for(int i=1975;i<=2017;i++)
//            yearList.add(""+i);

        Calendar currentCalendar=Calendar.getInstance();
        for(int i=1975;i<=currentCalendar.get(Calendar.YEAR);i++)
            yearList.add(""+i);


        monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePatents.this));
        monthView.setWheelData(monthList);
        yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePatents.this));
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



                // date pick valication -- change setMonth method with last para
                int isInvalidDate=0;
                Calendar currentDatecalendar=Calendar.getInstance();
                int selectedYearInterger=Integer.parseInt(selectedYear);
                if(selectedYearInterger > currentDatecalendar.get(Calendar.YEAR) || monthPosition > currentDatecalendar.get(Calendar.MONTH)){
                    isInvalidDate=1;
                }

                setMonthYear(id,selectedMonth,selectedYear,isInvalidDate);

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
    void setMonthYear(EditText id,String selectedMonth,String selectedYear,int isInvalidDate)
    {
        if(isInvalidDate==1){
            id.setText("");
            id.setError("Invalid Date");
            Toast.makeText(this, "Invalid Date", Toast.LENGTH_SHORT).show();
        }
        else
            id.setText(selectedMonth+", "+selectedYear);
    }

}
