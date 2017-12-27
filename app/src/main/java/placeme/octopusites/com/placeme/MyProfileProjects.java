package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.Projects;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfileProjects extends AppCompatActivity implements TextWatcher {

    int projectscount=0,editproj=0;;
    View addmoreproject;
    String username,role;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    EditText proj1,domain1,team1,duration1,proj2,domain2,team2,duration2,proj3,domain3,team3,duration3,proj4,domain4,team4,duration4,proj5,domain5,team5,duration5,proj6,domain6,team6,duration6,proj7,domain7,team7,duration7,proj8,domain8,team8,duration8,proj9,domain9,team9,duration9,proj10,domain10,team10,duration10;
    TextInputLayout projinput1, domaininput1, teaminput1, durationinput1, projinput2, domaininput2, teaminput2, durationinput2, projinput3, domaininput3, teaminput3, durationinput3, projinput4, domaininput4, teaminput4, durationinput4, projinput5, domaininput5, teaminput5, durationinput5, projinput6, domaininput6, teaminput6, durationinput6, projinput7, domaininput7, teaminput7, durationinput7, projinput8, domaininput8, teaminput8, durationinput8, projinput9, domaininput9, teaminput9, durationinput9, projinput10, domaininput10, teaminput10, durationinput10;
    String sproj1="",sdomain1="",steam1="",sduration1="",sproj2="",sdomain2="",steam2="",sduration2="",sproj3="",sdomain3="",steam3="",sduration3="",sproj4="",sdomain4="",steam4="",sduration4="",sproj5="",sdomain5="",steam5="",sduration5="",sproj6="",sdomain6="",steam6="",sduration6="",sproj7="",sdomain7="",steam7="",sduration7="",sproj8="",sdomain8="",steam8="",sduration8="",sproj9="",sdomain9="",steam9="",sduration9="",sproj10="",sdomain10="",steam10="",sduration10="";
    String encproj1,encdomain1,encteam1,encduration1,encproj2,encdomain2,encteam2,encduration2,encproj3,encdomain3,encteam3,encduration3,encproj4,encdomain4,encteam4,encduration4,encproj5,encdomain5,encteam5,encduration5,encproj6,encdomain6,encteam6,encduration6,encproj7,encdomain7,encteam7,encduration7,encproj8,encdomain8,encteam8,encduration8,encproj9,encdomain9,encteam9,encduration9,encproj10,encdomain10,encteam10,encduration10;
    View trash1selectionview,trash2selectionview,trash3selectionview,trash4selectionview,trash5selectionview,trash6selectionview,trash7selectionview,trash8selectionview,trash9selectionview,trash10selectionview;
    TextView addmoreprojecttxt;
    int edittedFlag=0;;
    int d=0;
    StudentData s=new StudentData();

    ArrayList<Projects> projectsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_projects);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Projects Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        TextView addmoreprojecttxt=(TextView)findViewById(R.id.addmoreprojecttxt);
        addmoreprojecttxt.setTypeface(Z.getBold(this));


        TextView projtxt=(TextView)findViewById(R.id.projtxt);
        projtxt.setTypeface(Z.getBold(this));

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

        proj1=(EditText)findViewById(R.id.proj1);
        domain1=(EditText)findViewById(R.id.domain1);
        team1=(EditText)findViewById(R.id.team1);
        duration1=(EditText)findViewById(R.id.duration1);
        proj2=(EditText)findViewById(R.id.proj2);
        domain2=(EditText)findViewById(R.id.domain2);
        team2=(EditText)findViewById(R.id.team2);
        duration2=(EditText)findViewById(R.id.duration2);
        proj3=(EditText)findViewById(R.id.proj3);
        domain3=(EditText)findViewById(R.id.domain3);
        team3=(EditText)findViewById(R.id.team3);
        duration3=(EditText)findViewById(R.id.duration3);
        proj4=(EditText)findViewById(R.id.proj4);
        domain4=(EditText)findViewById(R.id.domain4);
        team4=(EditText)findViewById(R.id.team4);
        duration4=(EditText)findViewById(R.id.duration4);
        proj5=(EditText)findViewById(R.id.proj5);
        domain5=(EditText)findViewById(R.id.domain5);
        team5=(EditText)findViewById(R.id.team5);
        duration5=(EditText)findViewById(R.id.duration5);
        proj6=(EditText)findViewById(R.id.proj6);
        domain6=(EditText)findViewById(R.id.domain6);
        team6=(EditText)findViewById(R.id.team6);
        duration6=(EditText)findViewById(R.id.duration6);
        proj7=(EditText)findViewById(R.id.proj7);
        domain7=(EditText)findViewById(R.id.domain7);
        team7=(EditText)findViewById(R.id.team7);
        duration7=(EditText)findViewById(R.id.duration7);
        proj8=(EditText)findViewById(R.id.proj8);
        domain8=(EditText)findViewById(R.id.domain8);
        team8=(EditText)findViewById(R.id.team8);
        duration8=(EditText)findViewById(R.id.duration8);
        proj9=(EditText)findViewById(R.id.proj9);
        domain9=(EditText)findViewById(R.id.domain9);
        team9=(EditText)findViewById(R.id.team9);
        duration9=(EditText)findViewById(R.id.duration9);
        proj10=(EditText)findViewById(R.id.proj10);
        domain10=(EditText)findViewById(R.id.domain10);
        team10=(EditText)findViewById(R.id.team10);
        duration10=(EditText)findViewById(R.id.duration10);


        projinput1 = (TextInputLayout) findViewById(R.id.proj1input);
        domaininput1 = (TextInputLayout) findViewById(R.id.domain1input);
        teaminput1 = (TextInputLayout) findViewById(R.id.team1input);
        durationinput1 = (TextInputLayout) findViewById(R.id.duration1input);

        projinput2 = (TextInputLayout) findViewById(R.id.proj2input);
        domaininput2 = (TextInputLayout) findViewById(R.id.domain2input);
        teaminput2 = (TextInputLayout) findViewById(R.id.team2input);
        durationinput2 = (TextInputLayout) findViewById(R.id.duration2input);

        projinput3 = (TextInputLayout) findViewById(R.id.proj3input);
        domaininput3 = (TextInputLayout) findViewById(R.id.domain3input);
        teaminput3 = (TextInputLayout) findViewById(R.id.team3input);
        durationinput3 = (TextInputLayout) findViewById(R.id.duration3input);

        projinput4 = (TextInputLayout) findViewById(R.id.proj4input);
        domaininput4 = (TextInputLayout) findViewById(R.id.domain4input);
        teaminput4 = (TextInputLayout) findViewById(R.id.team4input);
        durationinput4 = (TextInputLayout) findViewById(R.id.duration4input);

        projinput5 = (TextInputLayout) findViewById(R.id.proj5input);
        domaininput5 = (TextInputLayout) findViewById(R.id.domain5input);
        teaminput5 = (TextInputLayout) findViewById(R.id.team5input);
        durationinput5 = (TextInputLayout) findViewById(R.id.duration5input);

        projinput6 = (TextInputLayout)findViewById(R.id.proj6input);
        domaininput6 = (TextInputLayout) findViewById(R.id.domain6input);
        teaminput6 = (TextInputLayout) findViewById(R.id.team6input);
        durationinput6 = (TextInputLayout) findViewById(R.id.duration6input);

        projinput7 = (TextInputLayout) findViewById(R.id.proj7input);
        domaininput7 = (TextInputLayout) findViewById(R.id.domain7input);
        teaminput7 = (TextInputLayout) findViewById(R.id.team7input);
        durationinput7 = (TextInputLayout) findViewById(R.id.duration7input);


        projinput8 = (TextInputLayout) findViewById(R.id.proj8input);
        domaininput8 = (TextInputLayout) findViewById(R.id.domain8input);
        teaminput8 = (TextInputLayout) findViewById(R.id.team8input);
        durationinput8 = (TextInputLayout) findViewById(R.id.duration8input);

        projinput9 = (TextInputLayout) findViewById(R.id.proj9input);
        domaininput9 = (TextInputLayout) findViewById(R.id.domain9input);
        teaminput9 = (TextInputLayout) findViewById(R.id.team9input);
        durationinput9 = (TextInputLayout) findViewById(R.id.duration9input);

        projinput10 = (TextInputLayout) findViewById(R.id.proj10input);
        domaininput10 = (TextInputLayout) findViewById(R.id.domain10input);
        teaminput10 = (TextInputLayout) findViewById(R.id.team10input);
        durationinput10 = (TextInputLayout) findViewById(R.id.duration10input);



        proj1.setTypeface(Z.getBold(this));
        domain1.setTypeface(Z.getBold(this));
        team1.setTypeface(Z.getBold(this));
        duration1.setTypeface(Z.getBold(this));
        proj2.setTypeface(Z.getBold(this));
        domain2.setTypeface(Z.getBold(this));
        team2.setTypeface(Z.getBold(this));
        duration2.setTypeface(Z.getBold(this));
        proj3.setTypeface(Z.getBold(this));
        domain3.setTypeface(Z.getBold(this));
        team3.setTypeface(Z.getBold(this));
        duration3.setTypeface(Z.getBold(this));
        proj4.setTypeface(Z.getBold(this));
        domain4.setTypeface(Z.getBold(this));
        team4.setTypeface(Z.getBold(this));
        duration4.setTypeface(Z.getBold(this));
        proj5.setTypeface(Z.getBold(this));
        domain5.setTypeface(Z.getBold(this));
        team5.setTypeface(Z.getBold(this));
        duration5.setTypeface(Z.getBold(this));
        proj6.setTypeface(Z.getBold(this));
        domain6.setTypeface(Z.getBold(this));
        team6.setTypeface(Z.getBold(this));
        duration6.setTypeface(Z.getBold(this));
        proj7.setTypeface(Z.getBold(this));
        domain7.setTypeface(Z.getBold(this));
        team7.setTypeface(Z.getBold(this));
        duration7.setTypeface(Z.getBold(this));
        proj8.setTypeface(Z.getBold(this));
        domain8.setTypeface(Z.getBold(this));
        team8.setTypeface(Z.getBold(this));
        duration8.setTypeface(Z.getBold(this));
        proj9.setTypeface(Z.getBold(this));
        domain9.setTypeface(Z.getBold(this));
        team9.setTypeface(Z.getBold(this));
        duration9.setTypeface(Z.getBold(this));
        proj10.setTypeface(Z.getBold(this));
        domain10.setTypeface(Z.getBold(this));
        team10.setTypeface(Z.getBold(this));
        duration10.setTypeface(Z.getBold(this));


        projinput1.setTypeface(Z.getLight(this));
        domaininput1.setTypeface(Z.getLight(this));
        teaminput1.setTypeface(Z.getLight(this));
        durationinput1.setTypeface(Z.getLight(this));
        projinput2.setTypeface(Z.getLight(this));
        domaininput2.setTypeface(Z.getLight(this));
        teaminput2.setTypeface(Z.getLight(this));
        durationinput2.setTypeface(Z.getLight(this));
        projinput3.setTypeface(Z.getLight(this));
        domaininput3.setTypeface(Z.getLight(this));
        teaminput3.setTypeface(Z.getLight(this));
        durationinput3.setTypeface(Z.getLight(this));

        projinput4.setTypeface(Z.getLight(this));
        domaininput4.setTypeface(Z.getLight(this));
        teaminput4.setTypeface(Z.getLight(this));
        durationinput4.setTypeface(Z.getLight(this));
        projinput5.setTypeface(Z.getLight(this));
        domaininput5.setTypeface(Z.getLight(this));
        teaminput5.setTypeface(Z.getLight(this));
        durationinput5.setTypeface(Z.getLight(this));
        projinput6.setTypeface(Z.getLight(this));
        domaininput6.setTypeface(Z.getLight(this));
        teaminput6.setTypeface(Z.getLight(this));
        durationinput6.setTypeface(Z.getLight(this));
        projinput7.setTypeface(Z.getLight(this));
        domaininput7.setTypeface(Z.getLight(this));
        teaminput7.setTypeface(Z.getLight(this));
        durationinput7.setTypeface(Z.getLight(this));
        projinput8.setTypeface(Z.getLight(this));
        domaininput8.setTypeface(Z.getLight(this));
        teaminput8.setTypeface(Z.getLight(this));
        durationinput8.setTypeface(Z.getLight(this));
        projinput9.setTypeface(Z.getLight(this));
        domaininput9.setTypeface(Z.getLight(this));
        teaminput9.setTypeface(Z.getLight(this));
        durationinput9.setTypeface(Z.getLight(this));
        projinput10.setTypeface(Z.getLight(this));
        domaininput10.setTypeface(Z.getLight(this));

        teaminput10.setTypeface(Z.getLight(this));
        durationinput10.setTypeface(Z.getLight(this));




        proj1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                projinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        domain1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                domaininput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                teaminput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
                durationinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput2 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput2.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput3.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput3 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput3 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput4 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput4 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput4 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput4.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput5 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput5 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput5.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput5 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput6 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput6 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput6 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput6 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput7 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput7.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput7 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput8.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput8 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput8  .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput9 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput9 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput9 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput9  .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        proj10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                projinput10.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        domain10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                domaininput10 .setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        team10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaminput10.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        duration10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                durationinput10.setError(null);
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addmoreproject=(View)findViewById(R.id.addmoreproject);
        addmoreproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editproj=0;
                if(projectscount==0)
                {
                    if(proj1.getText().toString()!=null && domain1.getText().toString()!=null && team1.getText().toString()!=null && duration1.getText().toString()!=null) {
                        if (!proj1.getText().toString().equals("") && !domain1.getText().toString().equals("") && !team1.getText().toString().equals("") && !duration1.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.projectline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl2);
                            projectrl.setVisibility(View.VISIBLE);

                            projectscount++;
                        } else
                            Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();

                }
                else  if(projectscount==1)
                {

                    if(proj2.getText().toString()!=null && domain2.getText().toString()!=null && team2.getText().toString()!=null && duration2.getText().toString()!=null) {
                        if (!proj2.getText().toString().equals("") && !domain2.getText().toString().equals("") && !team2.getText().toString().equals("") && !duration2.getText().toString().equals("")) {

                    View v=(View)findViewById(R.id.projectline2);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl3);
                    projectrl.setVisibility(View.VISIBLE);
                    projectscount++;

                        } else
                            Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();


                }
                else  if(projectscount==2)
                {

                    if(proj3.getText().toString()!=null && domain3.getText().toString()!=null && team3.getText().toString()!=null && duration3.getText().toString()!=null) {
                        if (!proj3.getText().toString().equals("") && !domain3.getText().toString().equals("") && !team3.getText().toString().equals("") && !duration3.getText().toString().equals("")) {

                    View v=(View)findViewById(R.id.projectline3);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl4);
                    projectrl.setVisibility(View.VISIBLE);
                    projectscount++;

                        } else
                            Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();


                }
                else  if(projectscount==3)
                {

                    if(proj4.getText().toString()!=null && domain4.getText().toString()!=null && team4.getText().toString()!=null && duration4.getText().toString()!=null) {
                        if (!proj4.getText().toString().equals("") && !domain4.getText().toString().equals("") && !team4.getText().toString().equals("") && !duration4.getText().toString().equals("")) {

                    View v=(View)findViewById(R.id.projectline4);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl5);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                        } else
                            Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();


                }
                else  if(projectscount==4)
                {

                    if(proj5.getText().toString()!=null &&domain5.getText().toString()!=null && team5.getText().toString()!=null && duration5.getText().toString()!=null) {
                        if (!proj5.getText().toString().equals("") && !domain5.getText().toString().equals("") && !team5.getText().toString().equals("") && !duration5.getText().toString().equals("")) {

                    View v=(View)findViewById(R.id.projectline5);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl6);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                        } else
                            Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();


                }
                else  if(projectscount==5)
                {
                    if(proj6.getText().toString()!=null && domain6.getText().toString()!=null && team6.getText().toString()!=null && duration6.getText().toString()!=null) {
                        if (!proj6.getText().toString().equals("") && !domain6.getText().toString().equals("") && !team6.getText().toString().equals("") && !duration6.getText().toString().equals("")) {

                    View v=(View)findViewById(R.id.projectline6);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl7);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                        } else
                            Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();






                }
                else  if(projectscount==6)
                {
                    if(proj7.getText().toString()!=null && domain7.getText().toString()!=null && team7.getText().toString()!=null && duration7.getText().toString()!=null) {
                        if (!proj7.getText().toString().equals("") && !domain7.getText().toString().equals("") && !team7.getText().toString().equals("") && !duration7.getText().toString().equals("")) {

                    View v=(View)findViewById(R.id.projectline7);
                    v.setVisibility(View.VISIBLE);
                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl8);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                        } else
                            Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();

                }
                else  if(projectscount==7)
                {
                    if(proj8.getText().toString()!=null && domain8.getText().toString()!=null && team8.getText().toString()!=null && duration8.getText().toString()!=null) {
                        if (!proj8.getText().toString().equals("") && !domain8.getText().toString().equals("") && !team8.getText().toString().equals("") && !duration8.getText().toString().equals("")) {

                    View v=(View)findViewById(R.id.projectline8);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl9);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                        } else
                            Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();




                }
                else  if(projectscount==8)
                {

                    if(proj9.getText().toString()!=null && domain9.getText().toString()!=null && team9.getText().toString()!=null && duration9.getText().toString()!=null) {
                        if (!proj9.getText().toString().equals("") && !domain9.getText().toString().equals("") && !team9.getText().toString().equals("") && !duration9.getText().toString().equals("")) {

                    View v=(View)findViewById(R.id.projectline9);
                    v.setVisibility(View.VISIBLE);

                    RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl10);
                    projectrl.setVisibility(View.VISIBLE);

                    projectscount++;

                    TextView t=(TextView)findViewById(R.id.addmoreprojecttxt);
                    ImageView i=(ImageView)findViewById(R.id.addmoreprojectimg);
                    addmoreproject.setVisibility(View.GONE);
                    t.setVisibility(View.GONE);
                    i.setVisibility(View.GONE);

                        } else
                            Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileProjects.this, "Please fill the empty project details", Toast.LENGTH_SHORT).show();

                }

            }
        });
        ScrollView myprofileintroscrollview=(ScrollView)findViewById(R.id.myprofileprojects);
        disableScrollbars(myprofileintroscrollview);



        sproj1=s.getProj1();
        sdomain1=s.getDomain1();
        steam1=s.getTeam1();
        sduration1=s.getDuration1();
        sproj2=s.getProj2();
        sdomain2=s.getDomain2();
        steam2=s.getTeam2();
        sduration2=s.getDuration2();
        sproj3=s.getProj3();
        sdomain3=s.getDomain3();
        steam3=s.getTeam3();
        sduration3=s.getDuration3();
        sproj4=s.getProj4();
        sdomain4=s.getDomain4();
        steam4=s.getTeam4();
        sduration4=s.getDuration4();
        sproj5=s.getProj5();
        sdomain5=s.getDomain5();
        steam5=s.getTeam5();
        sduration5=s.getDuration5();
        sproj6=s.getProj6();
        sdomain6=s.getDomain6();
        steam6=s.getTeam6();
        sduration6=s.getDuration6();
        sproj7=s.getProj7();
        sdomain7=s.getDomain7();
        steam7=s.getTeam7();
        sduration7=s.getDuration7();
        sproj8=s.getProj8();
        sdomain8=s.getDomain8();
        steam8=s.getTeam8();
        sduration8=s.getDuration8();
        sproj9=s.getProj9();
        sdomain9=s.getDomain9();
        steam9=s.getTeam9();
        sduration9=s.getDuration9();
        sproj10=s.getProj10();
        sdomain10=s.getDomain10();
        steam10=s.getTeam10();
        sduration10=s.getDuration10();



        if(sproj1!=null) {
            if (sproj1.length() > 2) {
                proj1.setText(sproj1);
                domain1.setText(sdomain1);
                team1.setText(steam1);
                duration1.setText(sduration1);
            }
        }
        if(sproj2!=null) {
            if (sproj2.length() > 2) {
                View v = (View) findViewById(R.id.projectline1);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl2);
                projectrl.setVisibility(View.VISIBLE);

                proj2.setText(sproj2);
                domain2.setText(sdomain2);
                team2.setText(steam2);
                duration2.setText(sduration2);
                projectscount++;
            }
        }
        if(sproj3!=null) {
            if (sproj3.length() > 2) {
                View v = (View) findViewById(R.id.projectline2);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl3);
                projectrl.setVisibility(View.VISIBLE);

                proj3.setText(sproj3);
                domain3.setText(sdomain3);
                team3.setText(steam3);
                duration3.setText(sduration3);
                projectscount++;
            }
        }
        if(sproj4!=null) {
            if (sproj4.length() > 2) {
                View v = (View) findViewById(R.id.projectline3);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl4);
                projectrl.setVisibility(View.VISIBLE);

                proj4.setText(sproj4);
                domain4.setText(sdomain4);
                team4.setText(steam4);
                duration4.setText(sduration4);
                projectscount++;
            }
        }
        if(sproj5!=null) {
            if (sproj5.length() > 2) {
                View v = (View) findViewById(R.id.projectline4);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl5);
                projectrl.setVisibility(View.VISIBLE);
                proj5.setText(sproj5);
                domain5.setText(sdomain5);
                team5.setText(steam5);
                duration5.setText(sduration5);
                projectscount++;
            }
        }
        if(sproj6!=null) {
            if (sproj6.length() > 2) {
                View v = (View) findViewById(R.id.projectline5);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl6);
                projectrl.setVisibility(View.VISIBLE);

                proj6.setText(sproj6);
                domain6.setText(sdomain6);
                team6.setText(steam6);
                duration6.setText(sduration6);
                projectscount++;
            }
        }
        if(sproj7!=null) {
            if (sproj7.length() > 2) {
                View v = (View) findViewById(R.id.projectline6);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl7);
                projectrl.setVisibility(View.VISIBLE);

                proj7.setText(sproj7);
                domain7.setText(sdomain7);
                team7.setText(steam7);
                duration7.setText(sduration7);
                projectscount++;
            }
        }
        if(sproj8!=null) {
            if (sproj8.length() > 2) {
                View v = (View) findViewById(R.id.projectline7);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl8);
                projectrl.setVisibility(View.VISIBLE);

                proj8.setText(sproj8);
                domain8.setText(sdomain8);
                team8.setText(steam8);
                duration8.setText(sduration8);
                projectscount++;
            }
        }
        if(sproj9!=null) {
            if (sproj9.length() > 2) {
                View v = (View) findViewById(R.id.projectline8);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl9);
                projectrl.setVisibility(View.VISIBLE);

                proj9.setText(sproj9);
                domain9.setText(sdomain9);
                team9.setText(steam9);
                duration9.setText(sduration9);
                projectscount++;
            }
        }
        if(sproj10!=null) {
            if (sproj10.length() > 2) {
                View v = (View) findViewById(R.id.projectline9);
                v.setVisibility(View.VISIBLE);
                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl10);
                projectrl.setVisibility(View.VISIBLE);

                proj10.setText(sproj10);
                domain10.setText(sdomain10);
                team10.setText(steam10);
                duration10.setText(sduration10);
                projectscount++;

                TextView t = (TextView) findViewById(R.id.addmoreprojecttxt);
                ImageView i = (ImageView) findViewById(R.id.addmoreprojectimg);
                addmoreproject.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                i.setVisibility(View.GONE);
            }
        }

        edittedFlag=0;
    }
    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }
    void validateandSave()
    {

        projinput1.setError(null);
        domaininput1.setError(null);
        teaminput1.setError(null);
        durationinput1.setError(null);

        projinput2.setError(null);
        domaininput2.setError(null);
        teaminput2.setError(null);
        durationinput2.setError(null);

        projinput3.setError(null);
        domaininput3.setError(null);
        teaminput3.setError(null);
        durationinput3.setError(null);

        projinput4.setError(null);
        domaininput4.setError(null);
        teaminput4.setError(null);
        durationinput4.setError(null);

        projinput5.setError(null);
        domaininput5.setError(null);
        teaminput5.setError(null);
        durationinput5.setError(null);

        projinput6.setError(null);
        domaininput6.setError(null);
        teaminput6.setError(null);
        durationinput6.setError(null);

        projinput7.setError(null);
        domaininput7.setError(null);
        teaminput7.setError(null);
        durationinput7.setError(null);

        projinput8.setError(null);
        domaininput8.setError(null);
        teaminput8.setError(null);
        durationinput8.setError(null);

        projinput9.setError(null);
        domaininput9.setError(null);
        teaminput9.setError(null);
        durationinput9.setError(null);

        projinput10.setError(null);
        domaininput10.setError(null);
        teaminput10.setError(null);
        durationinput10.setError(null);



        sproj1=proj1.getText().toString();
        sdomain1=domain1.getText().toString();
        steam1=team1.getText().toString();
        sduration1=duration1.getText().toString();
        sproj2=proj2.getText().toString();
        sdomain2=domain2.getText().toString();
        steam2=team2.getText().toString();
        sduration2=duration2.getText().toString();
        sproj3=proj3.getText().toString();
        sdomain3=domain3.getText().toString();
        steam3=team3.getText().toString();
        sduration3=duration3.getText().toString();
        sproj4=proj4.getText().toString();
        sdomain4=domain4.getText().toString();
        steam4=team4.getText().toString();
        sduration4=duration4.getText().toString();
        sproj5=proj5.getText().toString();
        sdomain5=domain5.getText().toString();
        steam5=team5.getText().toString();
        sduration5=duration5.getText().toString();
        sproj6=proj6.getText().toString();
        sdomain6=domain6.getText().toString();
        steam6=team6.getText().toString();
        sduration6=duration6.getText().toString();
        sproj7=proj7.getText().toString();
        sdomain7=domain7.getText().toString();
        steam7=team7.getText().toString();
        sduration7=duration7.getText().toString();
        sproj8=proj8.getText().toString();
        sdomain8=domain8.getText().toString();
        steam8=team8.getText().toString();
        sduration8=duration8.getText().toString();
        sproj9=proj9.getText().toString();
        sdomain9=domain9.getText().toString();
        steam9=team9.getText().toString();
        sduration9=duration9.getText().toString();
        sproj10=proj10.getText().toString();
        sdomain10=domain10.getText().toString();
        steam10=team10.getText().toString();
        sduration10=duration10.getText().toString();

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag=0;

        if (projectscount == 0) {
            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");
            }

        } else if (projectscount == 1) {


            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");

            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            }
        } else if (projectscount == 2) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");

            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            }
        } else if (projectscount == 3) {


            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");

            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            }
        } else if (projectscount == 4) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");

            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            }
        } else if (projectscount == 5) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");

            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            } else if (sproj6.length() < 3) {
                errorflag = 1;
                projinput6.setError("Kindly enter valid project name");
            } else if (sdomain6.length() < 3) {
                errorflag = 1;
                domaininput6.setError("Kindly enter valid domain");
            } else if (steam6.length() < 1) {
                errorflag = 1;
                teaminput6.setError("Kindly enter valid team size");
            } else if (sduration6.length() < 1) {
                errorflag = 1;
                durationinput6.setError("Kindly enter valid duration in months");
            }

        } else if (projectscount == 6) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");
            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            } else if (sproj6.length() < 3) {
                errorflag = 1;
                projinput6.setError("Kindly enter valid project name");
            } else if (sdomain6.length() < 3) {
                errorflag = 1;
                domaininput6.setError("Kindly enter valid domain");
            } else if (steam6.length() < 1) {
                errorflag = 1;
                teaminput6.setError("Kindly enter valid team size");
            } else if (sduration6.length() < 1) {
                errorflag = 1;
                durationinput6.setError("Kindly enter valid duration in months");
            } else if (sproj7.length() < 3) {
                errorflag = 1;
                projinput7.setError("Kindly enter valid project name");
            } else if (sdomain7.length() < 3) {
                errorflag = 1;
                domaininput7.setError("Kindly enter valid domain");
            } else if (steam7.length() < 1) {
                errorflag = 1;
                teaminput7.setError("Kindly enter valid team size");
            } else if (sduration7.length() < 1) {
                errorflag = 1;
                durationinput7.setError("Kindly enter valid duration in months");
            }

        } else if (projectscount == 7) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");
            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            } else if (sproj6.length() < 3) {
                errorflag = 1;
                projinput6.setError("Kindly enter valid project name");
            } else if (sdomain6.length() < 3) {
                errorflag = 1;
                domaininput6.setError("Kindly enter valid domain");
            } else if (steam6.length() < 1) {
                errorflag = 1;
                teaminput6.setError("Kindly enter valid team size");
            } else if (sduration6.length() < 1) {
                errorflag = 1;
                durationinput6.setError("Kindly enter valid duration in months");
            } else if (sproj7.length() < 3) {
                errorflag = 1;
                projinput7.setError("Kindly enter valid project name");
            } else if (sdomain7.length() < 3) {
                errorflag = 1;
                domaininput7.setError("Kindly enter valid domain");
            } else if (steam7.length() < 1) {
                errorflag = 1;
                teaminput7.setError("Kindly enter valid team size");
            } else if (sduration7.length() < 1) {
                errorflag = 1;
                durationinput7.setError("Kindly enter valid duration in months");
            } else if (sproj8.length() < 3) {
                errorflag = 1;
                projinput8.setError("Kindly enter valid project name");
            } else if (sdomain8.length() < 3) {
                errorflag = 1;
                domaininput8.setError("Kindly enter valid domain");
            } else if (steam8.length() < 1) {
                errorflag = 1;
                teaminput8.setError("Kindly enter valid team size");
            } else if (sduration8.length() < 1) {
                errorflag = 1;
                durationinput8.setError("Kindly enter valid duration in months");
            }
//
        } else if (projectscount == 8) {

            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");
            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            } else if (sproj6.length() < 3) {
                errorflag = 1;
                projinput6.setError("Kindly enter valid project name");
            } else if (sdomain6.length() < 3) {
                errorflag = 1;
                domaininput6.setError("Kindly enter valid domain");
            } else if (steam6.length() < 1) {
                errorflag = 1;
                teaminput6.setError("Kindly enter valid team size");
            } else if (sduration6.length() < 1) {
                errorflag = 1;
                durationinput6.setError("Kindly enter valid duration in months");
            } else if (sproj7.length() < 3) {
                errorflag = 1;
                projinput7.setError("Kindly enter valid project name");
            } else if (sdomain7.length() < 3) {
                errorflag = 1;
                domaininput7.setError("Kindly enter valid domain");
            } else if (steam7.length() < 1) {
                errorflag = 1;
                teaminput7.setError("Kindly enter valid team size");
            } else if (sduration7.length() < 1) {
                errorflag = 1;
                durationinput7.setError("Kindly enter valid duration in months");
            } else if (sproj8.length() < 3) {
                errorflag = 1;
                projinput8.setError("Kindly enter valid project name");
            } else if (sdomain8.length() < 3) {
                errorflag = 1;
                domaininput8.setError("Kindly enter valid domain");
            } else if (steam8.length() < 1) {
                errorflag = 1;
                teaminput8.setError("Kindly enter valid team size");
            } else if (sduration8.length() < 1) {
                errorflag = 1;
                durationinput8.setError("Kindly enter valid duration in months");
            } else if (sproj9.length() < 3) {
                errorflag = 1;
                projinput9.setError("Kindly enter valid project name");
            } else if (sdomain9.length() < 3) {
                errorflag = 1;
                domaininput9.setError("Kindly enter valid domain");
            } else if (steam9.length() < 1) {
                errorflag = 1;
                teaminput9.setError("Kindly enter valid team size");
            } else if (sduration9.length() < 1) {
                errorflag = 1;
                durationinput9.setError("Kindly enter valid duration in months");
            }

        } else if (projectscount == 9) {
            if (sproj1.length() < 3) {
                errorflag = 1;
                projinput1.setError("Kindly enter valid project name");
            } else if (sdomain1.length() < 3) {
                errorflag = 1;
                domaininput1.setError("Kindly enter valid domain");
            } else if (steam1.length() < 1) {
                errorflag = 1;
                teaminput1.setError("Kindly enter valid team size");
            } else if (sduration1.length() < 1) {
                errorflag = 1;
                durationinput1.setError("Kindly enter valid duration in months");
            } else if (sproj2.length() < 3) {
                errorflag = 1;
                projinput2.setError("Kindly enter valid project name");
            } else if (sdomain2.length() < 3) {
                errorflag = 1;
                domaininput2.setError("Kindly enter valid domain");
            } else if (steam2.length() < 1) {
                errorflag = 1;
                teaminput2.setError("Kindly enter valid team size");
            } else if (sduration2.length() < 1) {
                errorflag = 1;
                durationinput2.setError("Kindly enter valid duration in months");
            } else if (sproj3.length() < 3) {
                errorflag = 1;
                projinput3.setError("Kindly enter valid project name");
            } else if (sdomain3.length() < 3) {
                errorflag = 1;
                domaininput3.setError("Kindly enter valid domain");
            } else if (steam3.length() < 1) {
                errorflag = 1;
                teaminput3.setError("Kindly enter valid team size");
            } else if (sduration3.length() < 1) {
                errorflag = 1;
                durationinput3.setError("Kindly enter valid duration in months");
            } else if (sproj4.length() < 3) {
                errorflag = 1;
                projinput4.setError("Kindly enter valid project name");
            } else if (sdomain4.length() < 3) {
                errorflag = 1;
                domaininput4.setError("Kindly enter valid domain");
            } else if (steam4.length() < 1) {
                errorflag = 1;
                teaminput4.setError("Kindly enter valid team size");
            } else if (sduration4.length() < 1) {
                errorflag = 1;
                durationinput4.setError("Kindly enter valid duration in months");
            } else if (sproj5.length() < 3) {
                errorflag = 1;
                projinput5.setError("Kindly enter valid project name");
            } else if (sdomain5.length() < 3) {
                errorflag = 1;
                domaininput5.setError("Kindly enter valid domain");
            } else if (steam5.length() < 1) {
                errorflag = 1;
                teaminput5.setError("Kindly enter valid team size");
            } else if (sduration5.length() < 1) {
                errorflag = 1;
                durationinput5.setError("Kindly enter valid duration in months");             //
            } else if (sproj6.length() < 3) {
                errorflag = 1;
                projinput6.setError("Kindly enter valid project name");
            } else if (sdomain6.length() < 3) {
                errorflag = 1;
                domaininput6.setError("Kindly enter valid domain");
            } else if (steam6.length() < 1) {
                errorflag = 1;
                teaminput6.setError("Kindly enter valid team size");
            } else if (sduration6.length() < 1) {
                errorflag = 1;
                durationinput6.setError("Kindly enter valid duration in months");
            } else if (sproj7.length() < 3) {
                errorflag = 1;
                projinput7.setError("Kindly enter valid project name");
            } else if (sdomain7.length() < 3) {
                errorflag = 1;
                domaininput7.setError("Kindly enter valid domain");
            } else if (steam7.length() < 1) {
                errorflag = 1;
                teaminput7.setError("Kindly enter valid team size");
            } else if (sduration7.length() < 1) {
                errorflag = 1;
                durationinput7.setError("Kindly enter valid duration in months");
            } else if (sproj8.length() < 3) {
                errorflag = 1;
                projinput8.setError("Kindly enter valid project name");
            } else if (sdomain8.length() < 3) {
                errorflag = 1;
                domaininput8.setError("Kindly enter valid domain");
            } else if (steam8.length() < 1) {
                errorflag = 1;
                teaminput8.setError("Kindly enter valid team size");
            } else if (sduration8.length() < 1) {
                errorflag = 1;
                durationinput8.setError("Kindly enter valid duration in months");
            } else if (sproj9.length() < 3) {
                errorflag = 1;
                projinput9.setError("Kindly enter valid project name");
            } else if (sdomain9.length() < 3) {
                errorflag = 1;
                domaininput9.setError("Kindly enter valid domain");
            } else if (steam9.length() < 1) {
                errorflag = 1;
                teaminput9.setError("Kindly enter valid team size");
            } else if (sduration9.length() < 1) {
                errorflag = 1;
                durationinput9.setError("Kindly enter valid duration in months");
            } else if (sproj10.length() < 3) {
                errorflag = 1;
                projinput10.setError("Kindly enter valid project name");
            } else if (sdomain10.length() < 3) {
                errorflag = 1;
                domaininput10.setError("Kindly enter valid domain");
            } else if (steam10.length() < 1) {
                errorflag = 1;
                teaminput10.setError("Kindly enter valid team size");
            } else if (sduration10.length() < 1) {
                errorflag = 1;
                durationinput10.setError("Kindly enter valid duration in months");
            }
        }

        if(errorflag==0)
        {
           save();
        }
    }

    public void save()
    {
        try
        {

            Projects obj1=new Projects(sproj1,sdomain1,steam1,sduration1);
            Projects obj2=new Projects(sproj2,sdomain2,steam2,sduration2);
            Projects obj3=new Projects(sproj3,sdomain3,steam3,sduration3);
            Projects obj4=new Projects(sproj4,sdomain4,steam4,sduration4);
            Projects obj5=new Projects(sproj5,sdomain5,steam5,sduration5);
            Projects obj6=new Projects(sproj6,sdomain6,steam6,sduration6);
            Projects obj7=new Projects(sproj7,sdomain7,steam7,sduration7);
            Projects obj8=new Projects(sproj8,sdomain8,steam8,sduration8);
            Projects obj9=new Projects(sproj9,sdomain9,steam9,sduration9);
            Projects obj10=new Projects(sproj10,sdomain10,steam10,sduration10);

            Log.d("TAG", "objects created");

            projectsList.add(obj1);
            projectsList.add(obj2);
            projectsList.add(obj3);
            projectsList.add(obj4);
            projectsList.add(obj5);
            projectsList.add(obj6);
            projectsList.add(obj7);
            projectsList.add(obj8);
            projectsList.add(obj9);
            projectsList.add(obj10);

            Log.d("TAG", "objects added in arraylist");

            String encObjString=OtoString(projectsList,MySharedPreferencesManager.getDigest1(MyProfileProjects.this),MySharedPreferencesManager.getDigest2(MyProfileProjects.this));

            new SaveProjects().execute(encObjString);
        }catch (Exception e){Toast.makeText(MyProfileProjects.this,"error:-"+e.getMessage(),Toast.LENGTH_LONG).show();}

    }

    class SaveProjects extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0

            Log.d("TAG", "encrypted objec string2: "+param[0]);
            params.add(new BasicNameValuePair("d",param[0]));       //0

            json = jParser.makeHttpRequest(Z.url_saveprojects, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {

                Toast.makeText(MyProfileProjects.this, "Successfully saved", Toast.LENGTH_SHORT).show();

                if(role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                edittedFlag=0;

                s.setProj1(sproj1);
                s.setDomain1(sdomain1);
                s.setTeam1(steam1);
                s.setDuration1(sduration1);



                MyProfileProjects.super.onBackPressed();


            }
        }
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
                                edittedFlag=1;
                                deleteProject();
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
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileProjects.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileProjects.this));
            }
        });

        alertDialog.show();

    }
    void deleteProject()
    {
        View v = (View) findViewById(R.id.projectline9);
        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View) findViewById(R.id.projectline9);
            v1.setVisibility(View.GONE);

            RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl10);
            projectrl.setVisibility(View.GONE);

            projectscount--;

            TextView t = (TextView) findViewById(R.id.addmoreprojecttxt);
            ImageView i = (ImageView) findViewById(R.id.addmoreprojectimg);
            addmoreproject.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        }
        else
        {
            v = (View) findViewById(R.id.projectline8);
            if (v.getVisibility() == View.VISIBLE) {


                View v1 = (View) findViewById(R.id.projectline8);
                v1.setVisibility(View.GONE);

                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl9);
                projectrl.setVisibility(View.GONE);

                projectscount--;

            }
            else
            {
                v = (View) findViewById(R.id.projectline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View) findViewById(R.id.projectline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl8);
                    projectrl.setVisibility(View.GONE);

                    projectscount--;
                }
                else
                {
                    v = (View) findViewById(R.id.projectline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View) findViewById(R.id.projectline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl7);
                        projectrl.setVisibility(View.GONE);

                        projectscount--;

                    }
                    else
                    {
                        v = (View) findViewById(R.id.projectline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) findViewById(R.id.projectline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl6);
                            projectrl.setVisibility(View.GONE);

                            projectscount--;


                        }
                        else
                        {
                            v = (View) findViewById(R.id.projectline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) findViewById(R.id.projectline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl5);
                                projectrl.setVisibility(View.GONE);

                                projectscount--;
                            }
                            else
                            {
                                v = (View) findViewById(R.id.projectline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) findViewById(R.id.projectline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl4);
                                    projectrl.setVisibility(View.GONE);

                                    projectscount--;

                                }
                                else
                                {
                                    v = (View) findViewById(R.id.projectline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) findViewById(R.id.projectline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout projectrl = (RelativeLayout) findViewById(R.id.projectrl3);
                                        projectrl.setVisibility(View.GONE);

                                        projectscount--;

                                    }
                                    else
                                    {
                                        v = (View) findViewById(R.id.projectline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1=(View)findViewById(R.id.projectline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout projectrl=(RelativeLayout)findViewById(R.id.projectrl2);
                                            projectrl.setVisibility(View.GONE);

                                            projectscount--;

//                                            Log.d("alumni",""+projectscount);
                                        }
//                                        if(projectscount==0)
                                        else
                                        {
                                            proj1.setText("");
                                            domain1.setText("");
                                            team1.setText("");
                                            duration1.setText("");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        Toast.makeText(MyProfileProjects.this,"delete "+d,Toast.LENGTH_LONG).show();
        if(d==10)
        {
            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");
        }
        else if(d==9)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==8)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();


            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==7)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();


            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==6)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();
            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();


            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==5)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();
            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();
            sproj6=proj6.getText().toString();
            sdomain6=domain6.getText().toString();
            steam6=team6.getText().toString();
            sduration6=duration6.getText().toString();



            sproj5=sproj6;
            sdomain5=sdomain6;
            steam5=steam6;
            sduration5=sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==4)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();
            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();
            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();
            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();
            sproj6=proj6.getText().toString();
            sdomain6=domain6.getText().toString();
            steam6=team6.getText().toString();
            sduration6=duration6.getText().toString();
            sproj5=proj5.getText().toString();
            sdomain5=domain5.getText().toString();
            steam5=team5.getText().toString();
            sduration5=duration5.getText().toString();


            sproj4=sproj5;
            sdomain4=sdomain5;
            steam4=steam5;
            sduration4=sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5=sproj6;
            sdomain5=sdomain6;
            steam5=steam6;
            sduration5=sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==3)
        {
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();

            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();

            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();

            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();

            sproj6=proj6.getText().toString();
            sdomain6=domain6.getText().toString();
            steam6=team6.getText().toString();
            sduration6=duration6.getText().toString();

            sproj5=proj5.getText().toString();
            sdomain5=domain5.getText().toString();
            steam5=team5.getText().toString();
            sduration5=duration5.getText().toString();

            sproj4=proj4.getText().toString();
            sdomain4=domain4.getText().toString();
            steam4=team4.getText().toString();
            sduration4=duration4.getText().toString();


            sproj3=sproj4;
            sdomain3=sdomain4;
            steam3=steam4;
            sduration3=sduration4;

            proj4.setText("");
            domain4.setText("");
            team4.setText("");
            duration4.setText("");

            proj3.setText(sproj3);
            domain3.setText(sdomain3);
            team3.setText(steam3);
            duration3.setText(sduration3);



            sproj4=sproj5;
            sdomain4=sdomain5;
            steam4=steam5;
            sduration4=sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5=sproj6;
            sdomain5=sdomain6;
            steam5=steam6;
            sduration5=sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==2)
        {
            Log.d("alumni", "deleteProject: delete proj 2");

            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();

            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();

            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();

            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();

            sproj6=proj6.getText().toString();
            sdomain6=domain6.getText().toString();
            steam6=team6.getText().toString();
            sduration6=duration6.getText().toString();

            sproj5=proj5.getText().toString();
            sdomain5=domain5.getText().toString();
            steam5=team5.getText().toString();
            sduration5=duration5.getText().toString();

            sproj4=proj4.getText().toString();
            sdomain4=domain4.getText().toString();
            steam4=team4.getText().toString();
            sduration4=duration4.getText().toString();

            sproj3=proj3.getText().toString();
            sdomain3=domain3.getText().toString();
            steam3=team3.getText().toString();
            sduration3=duration3.getText().toString();


            sproj2=sproj3;
            sdomain2=sdomain3;
            steam2=steam3;
            sduration2=sduration3;

            proj3.setText("");
            domain3.setText("");
            team3.setText("");
            duration3.setText("");

            proj2.setText(sproj2);
            domain2.setText(sdomain2);
            team2.setText(steam2);
            duration2.setText(sduration2);

            sproj3=sproj4;
            sdomain3=sdomain4;
            steam3=steam4;
            sduration3=sduration4;

            proj4.setText("");
            domain4.setText("");
            team4.setText("");
            duration4.setText("");

            proj3.setText(sproj3);
            domain3.setText(sdomain3);
            team3.setText(steam3);
            duration3.setText(sduration3);

            sproj4=sproj5;
            sdomain4=sdomain5;
            steam4=steam5;
            sduration4=sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);

            sproj5=sproj6;
            sdomain5=sdomain6;
            steam5=steam6;
            sduration5=sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

        }
        else if(d==1)
        {
            Log.d("alumni", "deleteProject: delete proj 1");
            sproj10=proj10.getText().toString();
            sdomain10=domain10.getText().toString();
            steam10=team10.getText().toString();
            sduration10=duration10.getText().toString();

            sproj9=proj9.getText().toString();
            sdomain9=domain9.getText().toString();
            steam9=team9.getText().toString();
            sduration9=duration9.getText().toString();

            sproj8=proj8.getText().toString();
            sdomain8=domain8.getText().toString();
            steam8=team8.getText().toString();
            sduration8=duration8.getText().toString();

            sproj7=proj7.getText().toString();
            sdomain7=domain7.getText().toString();
            steam7=team7.getText().toString();
            sduration7=duration7.getText().toString();

            sproj6=proj6.getText().toString();
            sdomain6=domain6.getText().toString();
            steam6=team6.getText().toString();
            sduration6=duration6.getText().toString();

            sproj5=proj5.getText().toString();
            sdomain5=domain5.getText().toString();
            steam5=team5.getText().toString();
            sduration5=duration5.getText().toString();

            sproj4=proj4.getText().toString();
            sdomain4=domain4.getText().toString();
            steam4=team4.getText().toString();
            sduration4=duration4.getText().toString();

            sproj3=proj3.getText().toString();
            sdomain3=domain3.getText().toString();
            steam3=team3.getText().toString();
            sduration3=duration3.getText().toString();

            sproj2=proj2.getText().toString();
            sdomain2=domain2.getText().toString();
            steam2=team2.getText().toString();
            sduration2=duration2.getText().toString();

            sproj1=sproj2;
            sdomain1=sdomain2;
            steam1=steam2;
            sduration1=sduration2;

            proj2.setText("");
            domain2.setText("");
            team2.setText("");
            duration2.setText("");

            proj1.setText(sproj1);
            domain1.setText(sdomain1);
            team1.setText(steam1);
            duration1.setText(sduration1);

            sproj2=sproj3;
            sdomain2=sdomain3;
            steam2=steam3;
            sduration2=sduration3;

            proj3.setText("");
            domain3.setText("");
            team3.setText("");
            duration3.setText("");

            proj2.setText(sproj2);
            domain2.setText(sdomain2);
            team2.setText(steam2);
            duration2.setText(sduration2);

            sproj3=sproj4;
            sdomain3=sdomain4;
            steam3=steam4;
            sduration3=sduration4;

            proj4.setText("");
            domain4.setText("");
            team4.setText("");
            duration4.setText("");

            proj3.setText(sproj3);
            domain3.setText(sdomain3);
            team3.setText(steam3);
            duration3.setText(sduration3);

            sproj4=sproj5;
            sdomain4=sdomain5;
            steam4=steam5;
            sduration4=sduration5;

            proj5.setText("");
            domain5.setText("");
            team5.setText("");
            duration5.setText("");

            proj4.setText(sproj4);
            domain4.setText(sdomain4);
            team4.setText(steam4);
            duration4.setText(sduration4);
            sproj5=sproj6;
            sdomain5=sdomain6;
            steam5=steam6;
            sduration5=sduration6;

            proj6.setText("");
            domain6.setText("");
            team6.setText("");
            duration6.setText("");

            proj5.setText(sproj5);
            domain5.setText(sdomain5);
            team5.setText(steam5);
            duration5.setText(sduration5);

            sproj6=sproj7;
            sdomain6=sdomain7;
            steam6=steam7;
            sduration6=sduration7;

            proj7.setText("");
            domain7.setText("");
            team7.setText("");
            duration7.setText("");

            proj6.setText(sproj6);
            domain6.setText(sdomain6);
            team6.setText(steam6);
            duration6.setText(sduration6);

            sproj7=sproj8;
            sdomain7=sdomain8;
            steam7=steam8;
            sduration7=sduration8;

            proj8.setText("");
            domain8.setText("");
            team8.setText("");
            duration8.setText("");

            proj7.setText(sproj7);
            domain7.setText(sdomain7);
            team7.setText(steam7);
            duration7.setText(sduration7);

            sproj8=sproj9;
            sdomain8=sdomain9;
            steam8=steam9;
            sduration8=sduration9;

            proj9.setText("");
            domain9.setText("");
            team9.setText("");
            duration9.setText("");

            proj8.setText(sproj8);
            domain8.setText(sdomain8);
            team8.setText(steam8);
            duration8.setText(sduration8);

            sproj9=sproj10;
            sdomain9=sdomain10;
            steam9=steam10;
            sduration9=sduration10;

            proj10.setText("");
            domain10.setText("");
            team10.setText("");
            duration10.setText("");

            proj9.setText(sproj9);
            domain9.setText(sdomain9);
            team9.setText(steam9);
            duration9.setText(sduration9);

            sproj1 = proj1.getText().toString();
            sdomain1 = domain1.getText().toString();
            steam1 = team1.getText().toString();
            sduration1 = duration1.getText().toString();

            if(sproj1.equals("") && sdomain1.equals("") && steam1.equals("") && sduration1.equals("")){
                Log.d("TAG", "deleteLang: strength1 1");
                editproj =1;
            }

            if(editproj==1){
                Log.d("TAG", "deleteLang: strength1 - "+editproj);
                save();
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
        if(edittedFlag==1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfileProjects.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileProjects.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileProjects.this));

                }
            });

            alertDialog.show();
        }else
            MyProfileProjects.super.onBackPressed();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        edittedFlag=1;
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
