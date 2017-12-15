package placeme.octopusites.com.placeme;

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
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.TextInputEditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

import placeme.octopusites.com.placeme.modal.Honors;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfileAchievements extends AppCompatActivity {

    int honorcount = 0,editachieve=0;
    View addmorehonor;
    String username, role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    //    private static String url_savehonors= "http://192.168.100.10/AESTest/SaveHonors";
    TextInputEditText title1, issuer1, description1, title2, issuer2, description2, title3, issuer3, description3, title4, issuer4, description4, title5, issuer5, description5, title6, issuer6, description6, title7, issuer7, description7, title8, issuer8, description8, title9, issuer9, description9, title10, issuer10, description10;
    String stitle1 = "", sissuer1 = "", sdescription1 = "", stitle2 = "", sissuer2 = "", sdescription2 = "", stitle3 = "", sissuer3 = "", sdescription3 = "", stitle4 = "", sissuer4 = "", sdescription4 = "", stitle5 = "", sissuer5 = "", sdescription5 = "", stitle6 = "", sissuer6 = "", sdescription6 = "", stitle7 = "", sissuer7 = "", sdescription7 = "", stitle8 = "", sissuer8 = "", sdescription8 = "", stitle9 = "", sissuer9 = "", sdescription9 = "", stitle10 = "", sissuer10 = "", sdescription10 = "", syearofhonor1 = "", syearofhonor2 = "", syearofhonor3 = "", syearofhonor4 = "", syearofhonor5 = "", syearofhonor6 = "", syearofhonor7 = "", syearofhonor8 = "", syearofhonor9 = "", syearofhonor10 = "";
    TextInputLayout titleinput1, issuerinput1, decrinput1, titleinput2, issuerinput2, decrinput2, titleinput3, issuerinput3, decrinput3, titleinput4, issuerinput4, decrinput4, titleinput5, issuerinput5, decrinput5, titleinput6, issuerinput6, decrinput6, titleinput7, issuerinput7, decrinput7, titleinput8, issuerinput8, decrinput8, titleinput9, issuerinput9, decrinput9, titleinput10, issuerinput10, decrinput10, yearhonor1, yearhonor2, yearhonor3, yearhonor4, yearhonor5, yearhonor6, yearhonor7, yearhonor8, yearhonor9, yearhonor10;
    TextInputEditText yearofhonor1, yearofhonor2, yearofhonor3, yearofhonor4, yearofhonor5, yearofhonor6, yearofhonor7, yearofhonor8, yearofhonor9, yearofhonor10;
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview, trash6selectionview, trash7selectionview, trash8selectionview, trash9selectionview, trash10selectionview;
    int edittedFlag = 0;

    int d = 0;
    StudentData s = new StudentData();
    ArrayList<Honors> honorsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_achievements);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Honors and Awards Info");
        ab.setDisplayHomeAsUpEnabled(true);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username = MySharedPreferencesManager.getUsername(this);
        role = MySharedPreferencesManager.getRole(this);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

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


        title1 = (TextInputEditText) findViewById(R.id.title1);
        issuer1 = (TextInputEditText) findViewById(R.id.issuer1);
        description1 = (TextInputEditText) findViewById(R.id.description1);

        title2 = (TextInputEditText) findViewById(R.id.title2);
        issuer2 = (TextInputEditText) findViewById(R.id.issuer2);
        description2 = (TextInputEditText) findViewById(R.id.description2);
        title3 = (TextInputEditText) findViewById(R.id.title3);
        issuer3 = (TextInputEditText) findViewById(R.id.issuer3);
        description3 = (TextInputEditText) findViewById(R.id.description3);
        title4 = (TextInputEditText) findViewById(R.id.title4);
        issuer4 = (TextInputEditText) findViewById(R.id.issuer4);
        description4 = (TextInputEditText) findViewById(R.id.description4);
        title5 = (TextInputEditText) findViewById(R.id.title5);
        issuer5 = (TextInputEditText) findViewById(R.id.issuer5);
        description5 = (TextInputEditText) findViewById(R.id.description5);
        title6 = (TextInputEditText) findViewById(R.id.title6);
        issuer6 = (TextInputEditText) findViewById(R.id.issuer6);
        description6 = (TextInputEditText) findViewById(R.id.description6);
        title7 = (TextInputEditText) findViewById(R.id.title7);
        issuer7 = (TextInputEditText) findViewById(R.id.issuer7);
        description7 = (TextInputEditText) findViewById(R.id.description7);
        title8 = (TextInputEditText) findViewById(R.id.title8);
        issuer8 = (TextInputEditText) findViewById(R.id.issuer8);
        description8 = (TextInputEditText) findViewById(R.id.description8);
        title9 = (TextInputEditText) findViewById(R.id.title9);
        issuer9 = (TextInputEditText) findViewById(R.id.issuer9);
        description9 = (TextInputEditText) findViewById(R.id.description9);
        title10 = (TextInputEditText) findViewById(R.id.title10);
        issuer10 = (TextInputEditText) findViewById(R.id.issuer10);
        description10 = (TextInputEditText) findViewById(R.id.description10);
        yearofhonor1 = (TextInputEditText) findViewById(R.id.yearofhonor1);
        yearofhonor2 = (TextInputEditText) findViewById(R.id.yearofhonor2);
        yearofhonor3 = (TextInputEditText) findViewById(R.id.yearofhonor3);
        yearofhonor4 = (TextInputEditText) findViewById(R.id.yearofhonor4);
        yearofhonor5 = (TextInputEditText) findViewById(R.id.yearofhonor5);
        yearofhonor6 = (TextInputEditText) findViewById(R.id.yearofhonor6);
        yearofhonor7 = (TextInputEditText) findViewById(R.id.yearofhonor7);
        yearofhonor8 = (TextInputEditText) findViewById(R.id.yearofhonor8);
        yearofhonor9 = (TextInputEditText) findViewById(R.id.yearofhonor9);
        yearofhonor10 = (TextInputEditText) findViewById(R.id.yearofhonor10);

        titleinput1 = (TextInputLayout) findViewById(R.id.titleinput1);
        issuerinput1 = (TextInputLayout) findViewById(R.id.issuerinput1);
        decrinput1 = (TextInputLayout) findViewById(R.id.decrinput1);

        titleinput2 = (TextInputLayout) findViewById(R.id.titleinput2);
        issuerinput2 = (TextInputLayout) findViewById(R.id.issuerinput2);
        decrinput2 = (TextInputLayout) findViewById(R.id.decrinput2);

        titleinput3 = (TextInputLayout) findViewById(R.id.titleinput3);
        issuerinput3 = (TextInputLayout) findViewById(R.id.issuerinput3);
        decrinput3 = (TextInputLayout) findViewById(R.id.decrinput3);

        titleinput4 = (TextInputLayout) findViewById(R.id.titleinput4);
        issuerinput4 = (TextInputLayout) findViewById(R.id.issuerinput4);
        decrinput4 = (TextInputLayout) findViewById(R.id.decrinput4);

        titleinput5 = (TextInputLayout) findViewById(R.id.titleinput5);
        issuerinput5 = (TextInputLayout) findViewById(R.id.issuerinput5);
        decrinput5 = (TextInputLayout) findViewById(R.id.decrinput5);

        titleinput6 = (TextInputLayout) findViewById(R.id.titleinput6);
        issuerinput6 = (TextInputLayout) findViewById(R.id.issuerinput6);
        decrinput6 = (TextInputLayout) findViewById(R.id.decrinput6);

        titleinput7 = (TextInputLayout) findViewById(R.id.titleinput7);
        issuerinput7 = (TextInputLayout) findViewById(R.id.issuerinput7);
        decrinput7 = (TextInputLayout) findViewById(R.id.decrinput7);

        titleinput8 = (TextInputLayout) findViewById(R.id.titleinput8);
        issuerinput8 = (TextInputLayout) findViewById(R.id.issuerinput8);
        decrinput8 = (TextInputLayout) findViewById(R.id.decrinput8);

        titleinput9 = (TextInputLayout) findViewById(R.id.titleinput9);
        issuerinput9 = (TextInputLayout) findViewById(R.id.issuerinput9);
        decrinput9 = (TextInputLayout) findViewById(R.id.decrinput9);

        titleinput10 = (TextInputLayout) findViewById(R.id.titleinput10);
        issuerinput10 = (TextInputLayout) findViewById(R.id.issuerinput10);
        decrinput10 = (TextInputLayout) findViewById(R.id.decrinput10);

        yearhonor1 = (TextInputLayout) findViewById(R.id.yearhonor1);
        yearhonor2 = (TextInputLayout) findViewById(R.id.yearhonor2);
        yearhonor3 = (TextInputLayout) findViewById(R.id.yearhonor3);
        yearhonor4 = (TextInputLayout) findViewById(R.id.yearhonor4);
        yearhonor5 = (TextInputLayout) findViewById(R.id.yearhonor5);
        yearhonor6 = (TextInputLayout) findViewById(R.id.yearhonor6);
        yearhonor7 = (TextInputLayout) findViewById(R.id.yearhonor7);
        yearhonor8 = (TextInputLayout) findViewById(R.id.yearhonor8);
        yearhonor9 = (TextInputLayout) findViewById(R.id.yearhonor9);
        yearhonor10 = (TextInputLayout) findViewById(R.id.yearhonor10);

        title1.setTypeface(MyConstants.getBold(this));
        issuer1.setTypeface(MyConstants.getBold(this));
        description1.setTypeface(MyConstants.getBold(this));
        title2.setTypeface(MyConstants.getBold(this));
        issuer2.setTypeface(MyConstants.getBold(this));
        description2.setTypeface(MyConstants.getBold(this));
        title3.setTypeface(MyConstants.getBold(this));
        issuer3.setTypeface(MyConstants.getBold(this));
        description3.setTypeface(MyConstants.getBold(this));
        title4.setTypeface(MyConstants.getBold(this));
        issuer4.setTypeface(MyConstants.getBold(this));
        description4.setTypeface(MyConstants.getBold(this));
        title5.setTypeface(MyConstants.getBold(this));
        issuer5.setTypeface(MyConstants.getBold(this));
        description5.setTypeface(MyConstants.getBold(this));
        title6.setTypeface(MyConstants.getBold(this));
        issuer6.setTypeface(MyConstants.getBold(this));
        description6.setTypeface(MyConstants.getBold(this));
        title7.setTypeface(MyConstants.getBold(this));

        issuer7.setTypeface(MyConstants.getBold(this));
        description7.setTypeface(MyConstants.getBold(this));
        title8.setTypeface(MyConstants.getBold(this));
        issuer8.setTypeface(MyConstants.getBold(this));
        description8.setTypeface(MyConstants.getBold(this));
        title9.setTypeface(MyConstants.getBold(this));
        issuer9.setTypeface(MyConstants.getBold(this));
        description9.setTypeface(MyConstants.getBold(this));
        title10.setTypeface(MyConstants.getBold(this));
        issuer10.setTypeface(MyConstants.getBold(this));
        description10.setTypeface(MyConstants.getBold(this));
        yearofhonor1.setTypeface(MyConstants.getBold(this));
        yearofhonor2.setTypeface(MyConstants.getBold(this));
        yearofhonor3.setTypeface(MyConstants.getBold(this));
        yearofhonor4.setTypeface(MyConstants.getBold(this));
        yearofhonor5.setTypeface(MyConstants.getBold(this));
        yearofhonor6.setTypeface(MyConstants.getBold(this));
        yearofhonor7.setTypeface(MyConstants.getBold(this));
        yearofhonor8.setTypeface(MyConstants.getBold(this));
        yearofhonor9.setTypeface(MyConstants.getBold(this));
        yearofhonor10.setTypeface(MyConstants.getBold(this));







        titleinput1.setTypeface(MyConstants.getLight(this));
        issuerinput1.setTypeface(MyConstants.getLight(this));
        decrinput1.setTypeface(MyConstants.getLight(this));
        yearhonor1.setTypeface(MyConstants.getLight(this));

        titleinput2.setTypeface(MyConstants.getLight(this));
        issuerinput2.setTypeface(MyConstants.getLight(this));
        decrinput2.setTypeface(MyConstants.getLight(this));
        yearhonor2.setTypeface(MyConstants.getLight(this));

        titleinput3.setTypeface(MyConstants.getLight(this));
        issuerinput3.setTypeface(MyConstants.getLight(this));
        decrinput3.setTypeface(MyConstants.getLight(this));
        yearhonor3.setTypeface(MyConstants.getLight(this));

        titleinput4.setTypeface(MyConstants.getLight(this));
        issuerinput4.setTypeface(MyConstants.getLight(this));
        decrinput4.setTypeface(MyConstants.getLight(this));
        yearhonor4.setTypeface(MyConstants.getLight(this));

        titleinput5.setTypeface(MyConstants.getLight(this));
        issuerinput5.setTypeface(MyConstants.getLight(this));
        decrinput5.setTypeface(MyConstants.getLight(this));
        yearhonor5.setTypeface(MyConstants.getLight(this));

        titleinput6.setTypeface(MyConstants.getLight(this));
        issuerinput6.setTypeface(MyConstants.getLight(this));
        decrinput6.setTypeface(MyConstants.getLight(this));
        yearhonor6.setTypeface(MyConstants.getLight(this));

        titleinput7.setTypeface(MyConstants.getLight(this));
        issuerinput7.setTypeface(MyConstants.getLight(this));
        decrinput7.setTypeface(MyConstants.getLight(this));
        yearhonor7.setTypeface(MyConstants.getLight(this));

        titleinput8.setTypeface(MyConstants.getLight(this));
        issuerinput8.setTypeface(MyConstants.getLight(this));
        decrinput8.setTypeface(MyConstants.getLight(this));
        yearhonor8.setTypeface(MyConstants.getLight(this));

        titleinput9.setTypeface(MyConstants.getLight(this));
        issuerinput9.setTypeface(MyConstants.getLight(this));
        decrinput9.setTypeface(MyConstants.getLight(this));
        yearhonor9.setTypeface(MyConstants.getLight(this));

        titleinput10.setTypeface(MyConstants.getLight(this));
        issuerinput10.setTypeface(MyConstants.getLight(this));
        decrinput10.setTypeface(MyConstants.getLight(this));
        yearhonor10.setTypeface(MyConstants.getLight(this));



        title1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                titleinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issuer1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                issuerinput1.setError(null);
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
                edittedFlag = 1;
                decrinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofhonor1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearhonor1.setError(null);
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
                edittedFlag = 1;
                titleinput2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issuer2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                issuerinput2.setError(null);
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
                edittedFlag = 1;
                decrinput2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofhonor2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearhonor2.setError(null);
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
                edittedFlag = 1;
                titleinput3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issuer3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                issuerinput3.setError(null);
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
                edittedFlag = 1;
                decrinput3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofhonor3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearhonor3.setError(null);
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
                edittedFlag = 1;
                titleinput4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issuer4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                issuerinput4.setError(null);
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
                edittedFlag = 1;
                decrinput4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofhonor4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearhonor4.setError(null);
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
                edittedFlag = 1;
                titleinput5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issuer5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                issuerinput5.setError(null);
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
                edittedFlag = 1;
                decrinput5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofhonor5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearhonor5.setError(null);
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
                edittedFlag = 1;
                titleinput6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issuer6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                issuerinput6.setError(null);
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
                edittedFlag = 1;
                decrinput6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofhonor6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearhonor6.setError(null);
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
                edittedFlag = 1;
                titleinput7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issuer7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                issuerinput7.setError(null);
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
                edittedFlag = 1;
                decrinput7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofhonor7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearhonor7.setError(null);
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
                edittedFlag = 1;
                titleinput8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issuer8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                issuerinput8.setError(null);
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
                edittedFlag = 1;
                decrinput8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofhonor8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearhonor8.setError(null);
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
                edittedFlag = 1;
                titleinput9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issuer9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                issuerinput9.setError(null);
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
                edittedFlag = 1;
                decrinput9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofhonor9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearhonor9.setError(null);
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
                edittedFlag = 1;
                titleinput10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        issuer10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                issuerinput10.setError(null);
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
                edittedFlag = 1;
                decrinput10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yearofhonor10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                yearhonor10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        yearofhonor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(yearofhonor1);
            }
        });
        yearofhonor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(yearofhonor2);
            }
        });

        yearofhonor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(yearofhonor3);
            }
        });

        yearofhonor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(yearofhonor4);
            }
        });

        yearofhonor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(yearofhonor5);
            }
        });

        yearofhonor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(yearofhonor6);
            }
        });

        yearofhonor7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(yearofhonor7);
            }
        });

        yearofhonor8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(yearofhonor8);
            }
        });

        yearofhonor9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(yearofhonor9);
            }
        });

        yearofhonor10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(yearofhonor10);
            }
        });

        TextView achievementstxt = (TextView) findViewById(R.id.achievementstxt);
        achievementstxt.setTypeface(MyConstants.getBold(this));

        addmorehonor = (View) findViewById(R.id.addmorehonor);
        addmorehonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editachieve=0;

                if (honorcount == 0) {
                    if (title1.getText().toString() != null && issuer1.getText().toString() != null && yearofhonor1.getText().toString() != null) {
                        if (!title1.getText().toString().equals("") && !issuer1.getText().toString().equals("")  && !yearofhonor1.getText().toString().equals("")) {
                            View view1 = (View) findViewById(R.id.honorline1);
                            view1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl2);
                            relativeLayout.setVisibility(View.VISIBLE);

                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor2);
                            textInputLayout.setVisibility(View.VISIBLE);
                            honorcount++;
                        } else {
                            Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();


                } else if (honorcount == 1) {
                    if (title2.getText().toString() != null && issuer2.getText().toString() != null  && yearofhonor2.getText().toString() != null) {
                        if (!title2.getText().toString().equals("") && !issuer2.getText().toString().equals("") && !yearofhonor2.getText().toString().equals("")) {
                            View view1 = (View) findViewById(R.id.honorline2);
                            view1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl3);
                            relativeLayout.setVisibility(View.VISIBLE);

                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor3);
                            textInputLayout.setVisibility(View.VISIBLE);
                            honorcount++;
                        } else {
                            Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();


                } else if (honorcount == 2) {
                    if (title3.getText().toString() != null && issuer3.getText().toString() != null &&  yearofhonor3.getText().toString() != null) {
                        if (!title3.getText().toString().equals("") && !issuer3.getText().toString().equals("") && !yearofhonor3.getText().toString().equals("")) {

                            View view1 = (View) findViewById(R.id.honorline3);
                            view1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl4);
                            relativeLayout.setVisibility(View.VISIBLE);

                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor4);
                            textInputLayout.setVisibility(View.VISIBLE);
                            honorcount++;
                        } else {
                            Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();


                } else if (honorcount == 3) {

                    if (title4.getText().toString() != null && issuer4.getText().toString() != null &&  yearofhonor4.getText().toString() != null) {
                        if (!title4.getText().toString().equals("") && !issuer4.getText().toString().equals("")  && !yearofhonor4.getText().toString().equals("")) {
                            View view1 = (View) findViewById(R.id.honorline4);
                            view1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl5);
                            relativeLayout.setVisibility(View.VISIBLE);

                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor5);
                            textInputLayout.setVisibility(View.VISIBLE);
                            honorcount++;
                        } else {
                            Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();


                } else if (honorcount == 4) {
                    if (title5.getText().toString() != null && issuer5.getText().toString() != null  && yearofhonor5.getText().toString() != null) {
                        if (!title5.getText().toString().equals("") && !issuer5.getText().toString().equals("") && !yearofhonor5.getText().toString().equals("")) {
                            View view1 = (View) findViewById(R.id.honorline5);
                            view1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl6);
                            relativeLayout.setVisibility(View.VISIBLE);

                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor6);
                            textInputLayout.setVisibility(View.VISIBLE);
                            honorcount++;
                        } else {
                            Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();


                } else if (honorcount == 5) {
                    if (title6.getText().toString() != null && issuer6.getText().toString() != null  && yearofhonor6.getText().toString() != null) {
                        if (!title6.getText().toString().equals("") && !issuer6.getText().toString().equals("")  && !yearofhonor6.getText().toString().equals("")) {

                            View view1 = (View) findViewById(R.id.honorline6);
                            view1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl7);
                            relativeLayout.setVisibility(View.VISIBLE);

                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor7);
                            textInputLayout.setVisibility(View.VISIBLE);
                            honorcount++;
                        } else {
                            Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();


                } else if (honorcount == 6) {
                    if (title7.getText().toString() != null && issuer7.getText().toString() != null  && yearofhonor7.getText().toString() != null) {
                        if (!title7.getText().toString().equals("") && !issuer7.getText().toString().equals("")  && !yearofhonor7.getText().toString().equals("")) {

                            View view1 = (View) findViewById(R.id.honorline7);
                            view1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl8);
                            relativeLayout.setVisibility(View.VISIBLE);

                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor8);
                            textInputLayout.setVisibility(View.VISIBLE);
                            honorcount++;
                        } else {
                            Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();


                } else if (honorcount == 7) {
                    if (title8.getText().toString() != null && issuer8.getText().toString() != null  && yearofhonor8.getText().toString() != null) {
                        if (!title8.getText().toString().equals("") && !issuer8.getText().toString().equals("")  && !yearofhonor8.getText().toString().equals("")) {

                            View view1 = (View) findViewById(R.id.honorline8);
                            view1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl9);
                            relativeLayout.setVisibility(View.VISIBLE);

                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor9);
                            textInputLayout.setVisibility(View.VISIBLE);
                            honorcount++;
                        } else {
                            Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();

                } else if (honorcount == 8) {
                    if (title9.getText().toString() != null && issuer9.getText().toString() != null  && yearofhonor9.getText().toString() != null) {
                        if (!title9.getText().toString().equals("") && !issuer9.getText().toString().equals("")  && !yearofhonor9.getText().toString().equals("")) {

                            View view1 = (View) findViewById(R.id.honorline9);
                            view1.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl10);
                            relativeLayout.setVisibility(View.VISIBLE);

                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor10);
                            textInputLayout.setVisibility(View.VISIBLE);
                            honorcount++;


                            TextView t = (TextView) findViewById(R.id.addmorehonortxt);
                            ImageView i = (ImageView) findViewById(R.id.addmorehonorimg);
                            addmorehonor.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfileAchievements.this, "Please fill the empty honors", Toast.LENGTH_SHORT).show();


                }
            }
        });

        ScrollView myprofileintroscrollview = (ScrollView) findViewById(R.id.myprofilehonorsandawards);
        disableScrollbars(myprofileintroscrollview);

        stitle1 = s.getHtitle1();
        sissuer1 = s.getHissuer1();
        sdescription1 = s.getHdescription1();
        syearofhonor1 = s.getYearofhonor1();
        stitle2 = s.getHtitle2();
        sissuer2 = s.getHissuer2();
        sdescription2 = s.getHdescription2();
        syearofhonor2 = s.getYearofhonor2();
        stitle3 = s.getHtitle3();
        sissuer3 = s.getHissuer3();
        sdescription3 = s.getHdescription3();
        syearofhonor3 = s.getYearofhonor3();
        stitle4 = s.getHtitle4();
        sissuer4 = s.getHissuer4();
        sdescription4 = s.getHdescription4();
        syearofhonor4 = s.getYearofhonor4();
        stitle5 = s.getHtitle5();
        sissuer5 = s.getHissuer5();
        sdescription5 = s.getHdescription5();
        syearofhonor5 = s.getYearofhonor5();
        stitle6 = s.getHtitle6();
        sissuer6 = s.getHissuer6();
        sdescription6 = s.getHdescription6();
        syearofhonor6 = s.getYearofhonor6();
        stitle7 = s.getHtitle7();
        sissuer7 = s.getHissuer7();
        sdescription7 = s.getHdescription7();
        syearofhonor7 = s.getYearofhonor7();
        stitle8 = s.getHtitle8();
        sissuer8 = s.getHissuer8();
        sdescription8 = s.getHdescription8();
        syearofhonor8 = s.getYearofhonor8();
        stitle9 = s.getHtitle9();
        sissuer9 = s.getHissuer9();
        sdescription9 = s.getHdescription9();
        syearofhonor9 = s.getYearofhonor9();
        stitle10 = s.getHtitle10();
        sissuer10 = s.getHissuer10();
        sdescription10 = s.getHdescription10();
        syearofhonor10 = s.getYearofhonor10();

        if (stitle1 != null) {
            if (stitle1.length() > 2) {
                title1.setText(stitle1);
                issuer1.setText(sissuer1);
                description1.setText(sdescription1);
                yearofhonor1.setText(syearofhonor1);


            }
        }
        if (stitle2 != null) {
            if (stitle2.length() > 2) {
                title2.setText(stitle2);
                issuer2.setText(sissuer2);
                description2.setText(sdescription2);
                yearofhonor2.setText(syearofhonor2);

                View view1 = (View) findViewById(R.id.honorline1);
                view1.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl2);
                relativeLayout.setVisibility(View.VISIBLE);

                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor2);
                textInputLayout.setVisibility(View.VISIBLE);
                honorcount++;
            }
        }
        if (stitle3 != null) {
            if (stitle3.length() > 2) {
                title3.setText(stitle3);
                issuer3.setText(sissuer3);
                description3.setText(sdescription3);
                yearofhonor3.setText(syearofhonor3);

                View view1 = (View) findViewById(R.id.honorline2);
                view1.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl3);
                relativeLayout.setVisibility(View.VISIBLE);

                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor3);
                textInputLayout.setVisibility(View.VISIBLE);
                honorcount++;
            }
        }
        if (stitle4 != null) {
            if (stitle4.length() > 2) {
                title4.setText(stitle4);
                issuer4.setText(sissuer4);
                description4.setText(sdescription4);
                yearofhonor4.setText(syearofhonor4);

                View view1 = (View) findViewById(R.id.honorline3);
                view1.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl4);
                relativeLayout.setVisibility(View.VISIBLE);

                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor4);
                textInputLayout.setVisibility(View.VISIBLE);
                honorcount++;
            }
        }
        if (stitle5 != null) {
            if (stitle5.length() > 2) {
                title5.setText(stitle5);
                issuer5.setText(sissuer5);
                description5.setText(sdescription5);
                yearofhonor5.setText(syearofhonor5);

                View view1 = (View) findViewById(R.id.honorline4);
                view1.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl5);
                relativeLayout.setVisibility(View.VISIBLE);

                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor5);
                textInputLayout.setVisibility(View.VISIBLE);
                honorcount++;
            }
        }
        if (stitle6 != null) {
            if (stitle6.length() > 2) {
                title6.setText(stitle6);
                issuer6.setText(sissuer6);
                description6.setText(sdescription6);
                yearofhonor6.setText(syearofhonor6);

                View view1 = (View) findViewById(R.id.honorline5);
                view1.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl6);
                relativeLayout.setVisibility(View.VISIBLE);

                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor6);
                textInputLayout.setVisibility(View.VISIBLE);
                honorcount++;

            }
        }
        if (stitle7 != null) {
            if (stitle7.length() > 2) {
                title7.setText(stitle7);
                issuer7.setText(sissuer7);
                description7.setText(sdescription7);
                yearofhonor7.setText(syearofhonor7);

                View view1 = (View) findViewById(R.id.honorline6);
                view1.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl7);
                relativeLayout.setVisibility(View.VISIBLE);

                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor7);
                textInputLayout.setVisibility(View.VISIBLE);
                honorcount++;
            }
        }
        if (stitle8 != null) {
            if (stitle8.length() > 2) {
                title8.setText(stitle8);
                issuer8.setText(sissuer8);
                description8.setText(sdescription8);
                yearofhonor8.setText(syearofhonor8);

                View view1 = (View) findViewById(R.id.honorline7);
                view1.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl8);
                relativeLayout.setVisibility(View.VISIBLE);

                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor8);
                textInputLayout.setVisibility(View.VISIBLE);
                honorcount++;
            }
        }
        if (stitle9 != null) {
            if (stitle9.length() > 2) {
                title9.setText(stitle9);
                issuer9.setText(sissuer9);
                description9.setText(sdescription9);
                yearofhonor9.setText(syearofhonor9);

                View view1 = (View) findViewById(R.id.honorline8);
                view1.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl9);
                relativeLayout.setVisibility(View.VISIBLE);

                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor9);
                textInputLayout.setVisibility(View.VISIBLE);
                honorcount++;
            }
        }
        if (stitle10 != null) {
            if (stitle10.length() > 2) {
                title10.setText(stitle10);
                issuer10.setText(sissuer10);
                description10.setText(sdescription10);
                yearofhonor10.setText(syearofhonor10);

                View view1 = (View) findViewById(R.id.honorline9);
                view1.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl10);
                relativeLayout.setVisibility(View.VISIBLE);

                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor10);
                textInputLayout.setVisibility(View.VISIBLE);
                honorcount++;

                TextView t = (TextView) findViewById(R.id.addmorehonortxt);
                ImageView i = (ImageView) findViewById(R.id.addmorehonorimg);
                addmorehonor.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                i.setVisibility(View.GONE);
            }
        }

        edittedFlag = 0;
    }

    void showDeletDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this Honor/Award ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag = 1;
                                deleteHonor();
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
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(MyConstants.getBold(MyProfileAchievements.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(MyConstants.getBold(MyProfileAchievements.this));

            }
        });

        alertDialog.show();

    }

    void deleteHonor() {
        View v = (View) findViewById(R.id.honorline9);
        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View) findViewById(R.id.honorline9);
            v1.setVisibility(View.GONE);

            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl10);
            relativeLayout.setVisibility(View.GONE);

            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor10);
            textInputLayout.setVisibility(View.GONE);

            honorcount--;

            TextView t = (TextView) findViewById(R.id.addmorehonortxt);
            ImageView i = (ImageView) findViewById(R.id.addmorehonorimg);
            addmorehonor.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);

        } else {
            v = (View) findViewById(R.id.honorline8);
            if (v.getVisibility() == View.VISIBLE) {

                View v1 = (View) findViewById(R.id.honorline8);
                v1.setVisibility(View.GONE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl9);
                relativeLayout.setVisibility(View.GONE);

                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor9);
                textInputLayout.setVisibility(View.GONE);

                honorcount--;

            } else {
                v = (View) findViewById(R.id.honorline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View) findViewById(R.id.honorline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl8);
                    relativeLayout.setVisibility(View.GONE);

                    TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor8);
                    textInputLayout.setVisibility(View.GONE);

                    honorcount--;

                } else {
                    v = (View) findViewById(R.id.honorline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View) findViewById(R.id.honorline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl7);
                        relativeLayout.setVisibility(View.GONE);

                        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor7);
                        textInputLayout.setVisibility(View.GONE);

                        honorcount--;

                    } else {
                        v = (View) findViewById(R.id.honorline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) findViewById(R.id.honorline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl6);
                            relativeLayout.setVisibility(View.GONE);

                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor6);
                            textInputLayout.setVisibility(View.GONE);

                            honorcount--;

                        } else {
                            v = (View) findViewById(R.id.honorline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) findViewById(R.id.honorline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl5);
                                relativeLayout.setVisibility(View.GONE);

                                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor5);
                                textInputLayout.setVisibility(View.GONE);

                                honorcount--;

                            } else {
                                v = (View) findViewById(R.id.honorline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) findViewById(R.id.honorline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl4);
                                    relativeLayout.setVisibility(View.GONE);

                                    TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor4);
                                    textInputLayout.setVisibility(View.GONE);

                                    honorcount--;

                                } else {
                                    v = (View) findViewById(R.id.honorline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) findViewById(R.id.honorline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl3);
                                        relativeLayout.setVisibility(View.GONE);

                                        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor3);
                                        textInputLayout.setVisibility(View.GONE);

                                        honorcount--;

                                    } else {
                                        v = (View) findViewById(R.id.honorline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1 = (View) findViewById(R.id.honorline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.honorrl2);
                                            relativeLayout.setVisibility(View.GONE);

                                            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.yearhonor2);
                                            textInputLayout.setVisibility(View.GONE);

                                            honorcount--;

                                        }
//                                        else if(honorcount==0)
                                        else {
                                            title1.setText("");
                                            issuer1.setText("");
                                            description1.setText("");
                                            yearofhonor1.setText("");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (d == 10) {
            title10.setText("");
            issuer10.setText("");
            description10.setText("");
            yearofhonor10.setText("");
        } else if (d == 9) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            sdescription10 = description10.getText().toString();
            syearofhonor10 = yearofhonor10.getText().toString();


            stitle9 = stitle10;
            sissuer9 = sissuer10;
            sdescription9 = sdescription10;
            syearofhonor9 = syearofhonor10;

            title10.setText("");
            issuer10.setText("");
            description10.setText("");
            yearofhonor10.setText("");

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            description9.setText(sdescription9);
            yearofhonor9.setText(syearofhonor9);


        } else if (d == 8) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            sdescription10 = description10.getText().toString();
            syearofhonor10 = yearofhonor10.getText().toString();
            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            sdescription9 = description9.getText().toString();
            syearofhonor9 = yearofhonor9.getText().toString();

            stitle8 = stitle9;
            sissuer8 = sissuer9;
            sdescription8 = sdescription9;
            syearofhonor8 = syearofhonor9;

            title9.setText("");
            issuer9.setText("");
            description9.setText("");
            yearofhonor9.setText("");

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            description8.setText(sdescription8);
            yearofhonor8.setText(syearofhonor8);


            stitle9 = stitle10;
            sissuer9 = sissuer10;
            sdescription9 = sdescription10;
            syearofhonor9 = syearofhonor10;

            title10.setText("");
            issuer10.setText("");
            description10.setText("");
            yearofhonor10.setText("");

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            description9.setText(sdescription9);
            yearofhonor9.setText(syearofhonor9);


        } else if (d == 7) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            sdescription10 = description10.getText().toString();
            syearofhonor10 = yearofhonor10.getText().toString();
            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            sdescription9 = description9.getText().toString();
            syearofhonor9 = yearofhonor9.getText().toString();
            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            sdescription8 = description8.getText().toString();
            syearofhonor8 = yearofhonor8.getText().toString();


            stitle7 = stitle8;
            sissuer7 = sissuer8;
            sdescription7 = sdescription8;
            syearofhonor7 = syearofhonor8;

            title8.setText("");
            issuer8.setText("");
            description8.setText("");
            yearofhonor8.setText("");

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            description7.setText(sdescription7);
            yearofhonor7.setText(syearofhonor7);


            stitle8 = stitle9;
            sissuer8 = sissuer9;
            sdescription8 = sdescription9;
            syearofhonor8 = syearofhonor9;

            title9.setText("");
            issuer9.setText("");
            description9.setText("");
            yearofhonor9.setText("");

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            description8.setText(sdescription8);
            yearofhonor8.setText(syearofhonor8);


            stitle9 = stitle10;
            sissuer9 = sissuer10;
            sdescription9 = sdescription10;
            syearofhonor9 = syearofhonor10;

            title10.setText("");
            issuer10.setText("");
            description10.setText("");
            yearofhonor10.setText("");

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            description9.setText(sdescription9);
            yearofhonor9.setText(syearofhonor9);


        } else if (d == 6) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            sdescription10 = description10.getText().toString();
            syearofhonor10 = yearofhonor10.getText().toString();
            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            sdescription9 = description9.getText().toString();
            syearofhonor9 = yearofhonor9.getText().toString();
            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            sdescription8 = description8.getText().toString();
            syearofhonor8 = yearofhonor8.getText().toString();
            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            sdescription7 = description7.getText().toString();
            syearofhonor7 = yearofhonor7.getText().toString();


            stitle6 = stitle7;
            sissuer6 = sissuer7;
            sdescription6 = sdescription7;
            syearofhonor6 = syearofhonor7;

            title7.setText("");
            issuer7.setText("");
            description7.setText("");
            yearofhonor7.setText("");

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            description6.setText(sdescription6);
            yearofhonor6.setText(syearofhonor6);


            stitle7 = stitle8;
            sissuer7 = sissuer8;
            sdescription7 = sdescription8;
            syearofhonor7 = syearofhonor8;

            title8.setText("");
            issuer8.setText("");
            description8.setText("");
            yearofhonor8.setText("");

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            description7.setText(sdescription7);
            yearofhonor7.setText(syearofhonor7);


            stitle8 = stitle9;
            sissuer8 = sissuer9;
            sdescription8 = sdescription9;
            syearofhonor8 = syearofhonor9;

            title9.setText("");
            issuer9.setText("");
            description9.setText("");
            yearofhonor9.setText("");

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            description8.setText(sdescription8);
            yearofhonor8.setText(syearofhonor8);


            stitle9 = stitle10;
            sissuer9 = sissuer10;
            sdescription9 = sdescription10;
            syearofhonor9 = syearofhonor10;

            title10.setText("");
            issuer10.setText("");
            description10.setText("");
            yearofhonor10.setText("");

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            description9.setText(sdescription9);
            yearofhonor9.setText(syearofhonor9);


        } else if (d == 5) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            sdescription10 = description10.getText().toString();
            syearofhonor10 = yearofhonor10.getText().toString();
            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            sdescription9 = description9.getText().toString();
            syearofhonor9 = yearofhonor9.getText().toString();
            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            sdescription8 = description8.getText().toString();
            syearofhonor8 = yearofhonor8.getText().toString();
            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            sdescription7 = description7.getText().toString();
            syearofhonor7 = yearofhonor7.getText().toString();
            stitle6 = title6.getText().toString();
            sissuer6 = issuer6.getText().toString();
            sdescription6 = description6.getText().toString();
            syearofhonor6 = yearofhonor6.getText().toString();


            stitle5 = stitle6;
            sissuer5 = sissuer6;
            sdescription5 = sdescription6;
            syearofhonor5 = syearofhonor6;

            title6.setText("");
            issuer6.setText("");
            description6.setText("");
            yearofhonor6.setText("");

            title5.setText(stitle5);
            issuer5.setText(sissuer5);
            description5.setText(sdescription5);
            yearofhonor5.setText(syearofhonor5);


            stitle6 = stitle7;
            sissuer6 = sissuer7;
            sdescription6 = sdescription7;
            syearofhonor6 = syearofhonor7;

            title7.setText("");
            issuer7.setText("");
            description7.setText("");
            yearofhonor7.setText("");

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            description6.setText(sdescription6);
            yearofhonor6.setText(syearofhonor6);


            stitle7 = stitle8;
            sissuer7 = sissuer8;
            sdescription7 = sdescription8;
            syearofhonor7 = syearofhonor8;

            title8.setText("");
            issuer8.setText("");
            description8.setText("");
            yearofhonor8.setText("");

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            description7.setText(sdescription7);
            yearofhonor7.setText(syearofhonor7);


            stitle8 = stitle9;
            sissuer8 = sissuer9;
            sdescription8 = sdescription9;
            syearofhonor8 = syearofhonor9;

            title9.setText("");
            issuer9.setText("");
            description9.setText("");
            yearofhonor9.setText("");

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            description8.setText(sdescription8);
            yearofhonor8.setText(syearofhonor8);


            stitle9 = stitle10;
            sissuer9 = sissuer10;
            sdescription9 = sdescription10;
            syearofhonor9 = syearofhonor10;

            title10.setText("");
            issuer10.setText("");
            description10.setText("");
            yearofhonor10.setText("");

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            description9.setText(sdescription9);
            yearofhonor9.setText(syearofhonor9);


        } else if (d == 4) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            sdescription10 = description10.getText().toString();
            syearofhonor10 = yearofhonor10.getText().toString();
            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            sdescription9 = description9.getText().toString();
            syearofhonor9 = yearofhonor9.getText().toString();
            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            sdescription8 = description8.getText().toString();
            syearofhonor8 = yearofhonor8.getText().toString();
            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            sdescription7 = description7.getText().toString();
            syearofhonor7 = yearofhonor7.getText().toString();
            stitle6 = title6.getText().toString();
            sissuer6 = issuer6.getText().toString();
            sdescription6 = description6.getText().toString();
            syearofhonor6 = yearofhonor6.getText().toString();
            stitle5 = title5.getText().toString();
            sissuer5 = issuer5.getText().toString();
            sdescription5 = description5.getText().toString();
            syearofhonor5 = yearofhonor5.getText().toString();


            stitle4 = stitle5;
            sissuer4 = sissuer5;
            sdescription4 = sdescription5;
            syearofhonor4 = syearofhonor5;

            title5.setText("");
            issuer5.setText("");
            description5.setText("");
            yearofhonor5.setText("");

            title4.setText(stitle4);
            issuer4.setText(sissuer4);
            description4.setText(sdescription4);
            yearofhonor4.setText(syearofhonor4);


            stitle5 = stitle6;
            sissuer5 = sissuer6;
            sdescription5 = sdescription6;
            syearofhonor5 = syearofhonor6;

            title6.setText("");
            issuer6.setText("");
            description6.setText("");
            yearofhonor6.setText("");

            title5.setText(stitle5);
            issuer5.setText(sissuer5);
            description5.setText(sdescription5);
            yearofhonor5.setText(syearofhonor5);

            stitle6 = stitle7;
            sissuer6 = sissuer7;
            sdescription6 = sdescription7;
            syearofhonor6 = syearofhonor7;

            title7.setText("");
            issuer7.setText("");
            description7.setText("");
            yearofhonor7.setText("");

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            description6.setText(sdescription6);
            yearofhonor6.setText(syearofhonor6);


            stitle7 = stitle8;
            sissuer7 = sissuer8;
            sdescription7 = sdescription8;
            syearofhonor7 = syearofhonor8;

            title8.setText("");
            issuer8.setText("");
            description8.setText("");
            yearofhonor8.setText("");

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            description7.setText(sdescription7);
            yearofhonor7.setText(syearofhonor7);


            stitle8 = stitle9;
            sissuer8 = sissuer9;
            sdescription8 = sdescription9;
            syearofhonor8 = syearofhonor9;

            title9.setText("");
            issuer9.setText("");
            description9.setText("");
            yearofhonor9.setText("");

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            description8.setText(sdescription8);
            yearofhonor8.setText(syearofhonor8);


            stitle9 = stitle10;
            sissuer9 = sissuer10;
            sdescription9 = sdescription10;
            syearofhonor9 = syearofhonor10;

            title10.setText("");
            issuer10.setText("");
            description10.setText("");
            yearofhonor10.setText("");

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            description9.setText(sdescription9);
            yearofhonor9.setText(syearofhonor9);


        } else if (d == 3) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            sdescription10 = description10.getText().toString();
            syearofhonor10 = yearofhonor10.getText().toString();
            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            sdescription9 = description9.getText().toString();
            syearofhonor9 = yearofhonor9.getText().toString();
            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            sdescription8 = description8.getText().toString();
            syearofhonor8 = yearofhonor8.getText().toString();
            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            sdescription7 = description7.getText().toString();
            syearofhonor7 = yearofhonor7.getText().toString();
            stitle6 = title6.getText().toString();
            sissuer6 = issuer6.getText().toString();
            sdescription6 = description6.getText().toString();
            syearofhonor6 = yearofhonor6.getText().toString();
            stitle5 = title5.getText().toString();
            sissuer5 = issuer5.getText().toString();
            sdescription5 = description5.getText().toString();
            syearofhonor5 = yearofhonor5.getText().toString();
            stitle4 = title4.getText().toString();
            sissuer4 = issuer4.getText().toString();
            sdescription4 = description4.getText().toString();
            syearofhonor4 = yearofhonor4.getText().toString();


            stitle3 = stitle4;
            sissuer3 = sissuer4;
            sdescription3 = sdescription4;
            syearofhonor3 = syearofhonor4;

            title4.setText("");
            issuer4.setText("");
            description4.setText("");
            yearofhonor4.setText("");

            title3.setText(stitle3);
            issuer3.setText(sissuer3);
            description3.setText(sdescription3);
            yearofhonor3.setText(syearofhonor3);


            stitle4 = stitle5;
            sissuer4 = sissuer5;
            sdescription4 = sdescription5;
            syearofhonor4 = syearofhonor5;

            title5.setText("");
            issuer5.setText("");
            description5.setText("");
            yearofhonor5.setText("");

            title4.setText(stitle4);
            issuer4.setText(sissuer4);
            description4.setText(sdescription4);
            yearofhonor4.setText(syearofhonor4);


            stitle5 = stitle6;
            sissuer5 = sissuer6;
            sdescription5 = sdescription6;
            syearofhonor5 = syearofhonor6;

            title6.setText("");
            issuer6.setText("");
            description6.setText("");
            yearofhonor6.setText("");

            title5.setText(stitle5);
            issuer5.setText(sissuer5);
            description5.setText(sdescription5);
            yearofhonor5.setText(syearofhonor5);

            stitle6 = stitle7;
            sissuer6 = sissuer7;
            sdescription6 = sdescription7;
            syearofhonor6 = syearofhonor7;

            title7.setText("");
            issuer7.setText("");
            description7.setText("");
            yearofhonor7.setText("");

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            description6.setText(sdescription6);
            yearofhonor6.setText(syearofhonor6);
            stitle7 = stitle8;
            sissuer7 = sissuer8;
            sdescription7 = sdescription8;
            syearofhonor7 = syearofhonor8;

            title8.setText("");
            issuer8.setText("");
            description8.setText("");
            yearofhonor8.setText("");

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            description7.setText(sdescription7);
            yearofhonor7.setText(syearofhonor7);


            stitle8 = stitle9;
            sissuer8 = sissuer9;
            sdescription8 = sdescription9;
            syearofhonor8 = syearofhonor9;

            title9.setText("");
            issuer9.setText("");
            description9.setText("");
            yearofhonor9.setText("");

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            description8.setText(sdescription8);
            yearofhonor8.setText(syearofhonor8);


            stitle9 = stitle10;
            sissuer9 = sissuer10;
            sdescription9 = sdescription10;
            syearofhonor9 = syearofhonor10;

            title10.setText("");
            issuer10.setText("");
            description10.setText("");
            yearofhonor10.setText("");

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            description9.setText(sdescription9);
            yearofhonor9.setText(syearofhonor9);


        } else if (d == 2) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            sdescription10 = description10.getText().toString();
            syearofhonor10 = yearofhonor10.getText().toString();
            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            sdescription9 = description9.getText().toString();
            syearofhonor9 = yearofhonor9.getText().toString();
            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            sdescription8 = description8.getText().toString();
            syearofhonor8 = yearofhonor8.getText().toString();
            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            sdescription7 = description7.getText().toString();
            syearofhonor7 = yearofhonor7.getText().toString();
            stitle6 = title6.getText().toString();
            sissuer6 = issuer6.getText().toString();
            sdescription6 = description6.getText().toString();
            syearofhonor6 = yearofhonor6.getText().toString();
            stitle5 = title5.getText().toString();
            sissuer5 = issuer5.getText().toString();
            sdescription5 = description5.getText().toString();
            syearofhonor5 = yearofhonor5.getText().toString();
            stitle4 = title4.getText().toString();
            sissuer4 = issuer4.getText().toString();
            sdescription4 = description4.getText().toString();
            syearofhonor4 = yearofhonor4.getText().toString();
            stitle3 = title3.getText().toString();
            sissuer3 = issuer3.getText().toString();
            sdescription3 = description3.getText().toString();
            syearofhonor3 = yearofhonor3.getText().toString();


            stitle2 = stitle3;
            sissuer2 = sissuer3;
            sdescription2 = sdescription3;
            syearofhonor2 = syearofhonor3;

            title3.setText("");
            issuer3.setText("");
            description3.setText("");
            yearofhonor3.setText("");

            title2.setText(stitle2);
            issuer2.setText(sissuer2);
            description2.setText(sdescription2);
            yearofhonor2.setText(syearofhonor2);


            stitle3 = stitle4;
            sissuer3 = sissuer4;
            sdescription3 = sdescription4;
            syearofhonor3 = syearofhonor4;

            title4.setText("");
            issuer4.setText("");
            description4.setText("");
            yearofhonor4.setText("");

            title3.setText(stitle3);
            issuer3.setText(sissuer3);
            description3.setText(sdescription3);
            yearofhonor3.setText(syearofhonor3);


            stitle4 = stitle5;
            sissuer4 = sissuer5;
            sdescription4 = sdescription5;
            syearofhonor4 = syearofhonor5;

            title5.setText("");
            issuer5.setText("");
            description5.setText("");
            yearofhonor5.setText("");

            title4.setText(stitle4);
            issuer4.setText(sissuer4);
            description4.setText(sdescription4);
            yearofhonor4.setText(syearofhonor4);


            stitle5 = stitle6;
            sissuer5 = sissuer6;
            sdescription5 = sdescription6;
            syearofhonor5 = syearofhonor6;

            title6.setText("");
            issuer6.setText("");
            description6.setText("");
            yearofhonor6.setText("");

            title5.setText(stitle5);
            issuer5.setText(sissuer5);
            description5.setText(sdescription5);
            yearofhonor5.setText(syearofhonor5);


            stitle6 = stitle7;
            sissuer6 = sissuer7;
            sdescription6 = sdescription7;
            syearofhonor6 = syearofhonor7;

            title7.setText("");
            issuer7.setText("");
            description7.setText("");
            yearofhonor7.setText("");

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            description6.setText(sdescription6);
            yearofhonor6.setText(syearofhonor6);


            stitle7 = stitle8;
            sissuer7 = sissuer8;
            sdescription7 = sdescription8;
            syearofhonor7 = syearofhonor8;

            title8.setText("");
            issuer8.setText("");
            description8.setText("");
            yearofhonor8.setText("");

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            description7.setText(sdescription7);
            yearofhonor7.setText(syearofhonor7);


            stitle8 = stitle9;
            sissuer8 = sissuer9;
            sdescription8 = sdescription9;
            syearofhonor8 = syearofhonor9;

            title9.setText("");
            issuer9.setText("");
            description9.setText("");
            yearofhonor9.setText("");

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            description8.setText(sdescription8);
            yearofhonor8.setText(syearofhonor8);


            stitle9 = stitle10;
            sissuer9 = sissuer10;
            sdescription9 = sdescription10;
            syearofhonor9 = syearofhonor10;

            title10.setText("");
            issuer10.setText("");
            description10.setText("");
            yearofhonor10.setText("");

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            description9.setText(sdescription9);
            yearofhonor9.setText(syearofhonor9);


        } else if (d == 1) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            sdescription10 = description10.getText().toString();
            syearofhonor10 = yearofhonor10.getText().toString();
            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            sdescription9 = description9.getText().toString();
            syearofhonor9 = yearofhonor9.getText().toString();
            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            sdescription8 = description8.getText().toString();
            syearofhonor8 = yearofhonor8.getText().toString();
            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            sdescription7 = description7.getText().toString();
            syearofhonor7 = yearofhonor7.getText().toString();
            stitle6 = title6.getText().toString();
            sissuer6 = issuer6.getText().toString();
            sdescription6 = description6.getText().toString();
            syearofhonor6 = yearofhonor6.getText().toString();
            stitle5 = title5.getText().toString();
            sissuer5 = issuer5.getText().toString();
            sdescription5 = description5.getText().toString();
            syearofhonor5 = yearofhonor5.getText().toString();
            stitle4 = title4.getText().toString();
            sissuer4 = issuer4.getText().toString();
            sdescription4 = description4.getText().toString();
            syearofhonor4 = yearofhonor4.getText().toString();
            stitle3 = title3.getText().toString();
            sissuer3 = issuer3.getText().toString();
            sdescription3 = description3.getText().toString();
            syearofhonor3 = yearofhonor3.getText().toString();
            stitle2 = title2.getText().toString();
            sissuer2 = issuer2.getText().toString();
            sdescription2 = description2.getText().toString();
            syearofhonor2 = yearofhonor2.getText().toString();


            stitle1 = stitle2;
            sissuer1 = sissuer2;
            sdescription1 = sdescription2;
            syearofhonor1 = syearofhonor2;

            title2.setText("");
            issuer2.setText("");
            description2.setText("");
            yearofhonor2.setText("");

            title1.setText(stitle1);
            issuer1.setText(sissuer1);
            description1.setText(sdescription1);
            yearofhonor1.setText(syearofhonor1);

            stitle2 = stitle3;
            sissuer2 = sissuer3;
            sdescription2 = sdescription3;
            syearofhonor2 = syearofhonor3;

            title3.setText("");
            issuer3.setText("");
            description3.setText("");
            yearofhonor3.setText("");

            title2.setText(stitle2);
            issuer2.setText(sissuer2);
            description2.setText(sdescription2);
            yearofhonor2.setText(syearofhonor2);


            stitle3 = stitle4;
            sissuer3 = sissuer4;
            sdescription3 = sdescription4;
            syearofhonor3 = syearofhonor4;

            title4.setText("");
            issuer4.setText("");
            description4.setText("");
            yearofhonor4.setText("");

            title3.setText(stitle3);
            issuer3.setText(sissuer3);
            description3.setText(sdescription3);
            yearofhonor3.setText(syearofhonor3);


            stitle4 = stitle5;
            sissuer4 = sissuer5;
            sdescription4 = sdescription5;
            syearofhonor4 = syearofhonor5;

            title5.setText("");
            issuer5.setText("");
            description5.setText("");
            yearofhonor5.setText("");

            title4.setText(stitle4);
            issuer4.setText(sissuer4);
            description4.setText(sdescription4);
            yearofhonor4.setText(syearofhonor4);


            stitle5 = stitle6;
            sissuer5 = sissuer6;
            sdescription5 = sdescription6;
            syearofhonor5 = syearofhonor6;

            title6.setText("");
            issuer6.setText("");
            description6.setText("");
            yearofhonor6.setText("");

            title5.setText(stitle5);
            issuer5.setText(sissuer5);
            description5.setText(sdescription5);
            yearofhonor5.setText(syearofhonor5);


            stitle6 = stitle7;
            sissuer6 = sissuer7;
            sdescription6 = sdescription7;
            syearofhonor6 = syearofhonor7;

            title7.setText("");
            issuer7.setText("");
            description7.setText("");
            yearofhonor7.setText("");

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            description6.setText(sdescription6);
            yearofhonor6.setText(syearofhonor6);


            stitle7 = stitle8;
            sissuer7 = sissuer8;
            sdescription7 = sdescription8;
            syearofhonor7 = syearofhonor8;

            title8.setText("");
            issuer8.setText("");
            description8.setText("");
            yearofhonor8.setText("");

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            description7.setText(sdescription7);
            yearofhonor7.setText(syearofhonor7);


            stitle8 = stitle9;
            sissuer8 = sissuer9;
            sdescription8 = sdescription9;
            syearofhonor8 = syearofhonor9;

            title9.setText("");
            issuer9.setText("");
            description9.setText("");
            yearofhonor9.setText("");

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            description8.setText(sdescription8);
            yearofhonor8.setText(syearofhonor8);


            stitle9 = stitle10;
            sissuer9 = sissuer10;
            sdescription9 = sdescription10;
            syearofhonor9 = syearofhonor10;

            title10.setText("");
            issuer10.setText("");
            description10.setText("");
            yearofhonor10.setText("");

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            description9.setText(sdescription9);
            yearofhonor9.setText(syearofhonor9);


            if(stitle1.equals("") && sissuer1.equals("") && sdescription1.equals("") && syearofhonor1.equals("") ){
                Log.d("TAG", "deleteLang: lang 1");
                editachieve =1;
            }

            if(editachieve==1){
                Log.d("TAG", "deleteLang: editcertfi - "+editachieve);
                encachieve();
            }


        }
    }

    void showDateDialog(final TextInputEditText id) {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfileAchievements.this);
        LayoutInflater inflater = MyProfileAchievements.this.getLayoutInflater();
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

//        for(int i=1975;i<=2017;i++)
//            yearList.add(""+i);

        Calendar currentCalendar = Calendar.getInstance();
        for (int i = 1975; i <= currentCalendar.get(Calendar.YEAR); i++)
            yearList.add("" + i);


        monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfileAchievements.this));
        monthView.setWheelData(monthList);
        yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfileAchievements.this));
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

                // date pick valication -- change setMonth method with last para
                int isInvalidDate = 0;
                Calendar currentDatecalendar = Calendar.getInstance();
                int selectedYearInterger = Integer.parseInt(selectedYear);
                if (selectedYearInterger >= currentDatecalendar.get(Calendar.YEAR) && monthPosition > currentDatecalendar.get(Calendar.MONTH)) {
                    isInvalidDate = 1;
                }
                setMonthYear(id, selectedMonth, selectedYear, isInvalidDate);
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

    void setMonthYear(TextInputEditText id, String selectedMonth, String selectedYear, int isInvalidDate) {
        if (isInvalidDate == 1) {
            id.setText("");
            id.setError("Kindly select valid Date");
            Toast.makeText(this, "Kindly select valid Date", Toast.LENGTH_SHORT).show();
        } else
            id.setText(selectedMonth + ", " + selectedYear);
    }

    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }

    void validateandSave() {
        titleinput1.setError(null);
        issuerinput1.setError(null);
        decrinput1.setError(null);
        yearhonor1.setError(null);
        titleinput2.setError(null);
        issuerinput2.setError(null);
        decrinput2.setError(null);
        yearhonor2.setError(null);
        titleinput3.setError(null);
        issuerinput3.setError(null);
        decrinput3.setError(null);
        yearhonor3.setError(null);
        titleinput4.setError(null);
        issuerinput4.setError(null);
        decrinput4.setError(null);
        yearhonor4.setError(null);
        titleinput5.setError(null);
        issuerinput5.setError(null);
        decrinput5.setError(null);
        yearhonor5.setError(null);
        titleinput6.setError(null);
        issuerinput6.setError(null);
        decrinput6.setError(null);
        yearhonor6.setError(null);
        titleinput7.setError(null);
        issuerinput7.setError(null);
        decrinput7.setError(null);
        yearhonor7.setError(null);
        titleinput8.setError(null);
        issuerinput8.setError(null);
        decrinput8.setError(null);
        yearhonor8.setError(null);
        titleinput9.setError(null);
        issuerinput9.setError(null);
        decrinput9.setError(null);
        yearhonor9.setError(null);
        titleinput10.setError(null);
        issuerinput10.setError(null);
        decrinput10.setError(null);
        yearhonor10.setError(null);

        stitle1 = title1.getText().toString();
        sissuer1 = issuer1.getText().toString();
        sdescription1 = description1.getText().toString();
        syearofhonor1 = yearofhonor1.getText().toString();
        stitle2 = title2.getText().toString();
        sissuer2 = issuer2.getText().toString();
        sdescription2 = description2.getText().toString();
        syearofhonor2 = yearofhonor2.getText().toString();
        stitle3 = title3.getText().toString();
        sissuer3 = issuer3.getText().toString();
        sdescription3 = description3.getText().toString();
        syearofhonor3 = yearofhonor3.getText().toString();
        stitle4 = title4.getText().toString();
        sissuer4 = issuer4.getText().toString();
        sdescription4 = description4.getText().toString();
        syearofhonor4 = yearofhonor4.getText().toString();
        stitle5 = title5.getText().toString();
        sissuer5 = issuer5.getText().toString();
        sdescription5 = description5.getText().toString();
        syearofhonor5 = yearofhonor5.getText().toString();
        stitle6 = title6.getText().toString();
        sissuer6 = issuer6.getText().toString();
        sdescription6 = description6.getText().toString();
        syearofhonor6 = yearofhonor6.getText().toString();
        stitle7 = title7.getText().toString();
        sissuer7 = issuer7.getText().toString();
        sdescription7 = description7.getText().toString();
        syearofhonor7 = yearofhonor7.getText().toString();
        stitle8 = title8.getText().toString();
        sissuer8 = issuer8.getText().toString();
        sdescription8 = description8.getText().toString();
        syearofhonor8 = yearofhonor8.getText().toString();
        stitle9 = title9.getText().toString();
        sissuer9 = issuer9.getText().toString();
        sdescription9 = description9.getText().toString();
        syearofhonor9 = yearofhonor9.getText().toString();
        stitle10 = title10.getText().toString();
        sissuer10 = issuer10.getText().toString();
        sdescription10 = description10.getText().toString();
        syearofhonor10 = yearofhonor10.getText().toString();

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag = 0;

        if(editachieve==1){
            encachieve();
        }
        else {

            if (honorcount == 0) {
                if (stitle1.length() < 3) {
                    errorflag = 1;
                    titleinput1.setError("Kindly enter valid title");
                } else {
                    errorflag = 0;
                    if (sissuer1.length() < 3) {
                        errorflag = 1;
                        issuerinput1.setError("Kindly enter valid Issuer");
                    } else {
                        errorflag = 0;
                        if (syearofhonor1.length() < 3) {
                            errorflag = 1;
                            yearhonor1.setError("Kindly select valid Date");
                        }
                    }
                }
            } else if (honorcount == 1) {
                if (stitle1.length() < 3) {
                    errorflag = 1;
                    titleinput1.setError("Kindly enter valid title");
                } else {
                    errorflag = 0;
                    if (sissuer1.length() < 3) {
                        errorflag = 1;
                        issuerinput1.setError("Kindly enter valid Issuer");
                    } else {
                        errorflag = 0;
                        if (syearofhonor1.length() < 3) {
                            errorflag = 1;
                            yearhonor1.setError("Kindly select valid Date");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 2;
                                    issuerinput2.setError("Kindly enter valid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (syearofhonor2.length() < 3) {
                                        errorflag = 2;
                                        yearhonor2.setError("Kindly select valid Date");
                                    }
                                }
                            }

                        }
                    }
                }
            } else if (honorcount == 2) {
                if (stitle1.length() < 3) {
                    errorflag = 1;
                    titleinput1.setError("Kindly enter valid title");
                } else {
                    errorflag = 0;
                    if (sissuer1.length() < 3) {
                        errorflag = 1;
                        issuerinput1.setError("Kindly enter valid Issuer");
                    } else {
                        errorflag = 0;
                        if (syearofhonor1.length() < 3) {
                            errorflag = 1;
                            yearhonor1.setError("Kindly select valid Date");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 2;
                                    issuerinput2.setError("Kindly enter valid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (syearofhonor2.length() < 3) {
                                        errorflag = 2;
                                        yearhonor2.setError("Kindly select valid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stitle3.length() < 3) {
                                            errorflag = 3;
                                            titleinput3.setError("Kindly enter valid title");
                                        } else {
                                            errorflag = 0;
                                            if (sissuer3.length() < 3) {
                                                errorflag = 3;
                                                issuerinput3.setError("Kindly enter valid Issuer");
                                            } else {
                                                errorflag = 0;
                                                if (syearofhonor3.length() < 3) {
                                                    errorflag = 3;
                                                    yearhonor3.setError("Kindly select valid Date");
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else if (honorcount == 3) {
                if (stitle1.length() < 3) {
                    errorflag = 1;
                    titleinput1.setError("Kindly enter valid title");
                } else {
                    errorflag = 0;
                    if (sissuer1.length() < 3) {
                        errorflag = 1;
                        issuerinput1.setError("Kindly enter valid Issuer");
                    } else {
                        errorflag = 0;
                        if (syearofhonor1.length() < 3) {
                            errorflag = 1;
                            yearhonor1.setError("Kindly select valid Date");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 2;
                                    issuerinput2.setError("Kindly enter valid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (syearofhonor2.length() < 3) {
                                        errorflag = 2;
                                        yearhonor2.setError("Kindly select valid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stitle3.length() < 3) {
                                            errorflag = 3;
                                            titleinput3.setError("Kindly enter valid title");
                                        } else {
                                            errorflag = 0;
                                            if (sissuer3.length() < 3) {
                                                errorflag = 3;
                                                issuerinput3.setError("Kindly enter valid Issuer");
                                            } else {
                                                errorflag = 0;
                                                if (syearofhonor3.length() < 3) {
                                                    errorflag = 3;
                                                    yearhonor3.setError("Kindly select valid Date");
                                                } else {
                                                    errorflag = 0;
                                                    if (stitle4.length() < 3) {
                                                        errorflag = 4;
                                                        titleinput4.setError("Kindly enter valid title");
                                                    } else {
                                                        errorflag = 0;
                                                        if (sissuer4.length() < 3) {
                                                            errorflag = 4;
                                                            issuerinput4.setError("Kindly enter valid Issuer");
                                                        } else {
                                                            errorflag = 0;
                                                            if (syearofhonor4.length() < 3) {
                                                                errorflag = 4;
                                                                yearhonor4.setError("Kindly select valid Date");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else if (honorcount == 4) {
                if (stitle1.length() < 3) {
                    errorflag = 1;
                    titleinput1.setError("Kindly enter valid title");
                } else {
                    errorflag = 0;
                    if (sissuer1.length() < 3) {
                        errorflag = 1;
                        issuerinput1.setError("Kindly enter valid Issuer");
                    } else {
                        errorflag = 0;
                        if (syearofhonor1.length() < 3) {
                            errorflag = 1;
                            yearhonor1.setError("Kindly select valid Date");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 2;
                                    issuerinput2.setError("Kindly enter valid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (syearofhonor2.length() < 3) {
                                        errorflag = 2;
                                        yearhonor2.setError("Kindly select valid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stitle3.length() < 3) {
                                            errorflag = 3;
                                            titleinput3.setError("Kindly enter valid title");
                                        } else {
                                            errorflag = 0;
                                            if (sissuer3.length() < 3) {
                                                errorflag = 3;
                                                issuerinput3.setError("Kindly enter valid Issuer");
                                            } else {
                                                errorflag = 0;
                                                if (syearofhonor3.length() < 3) {
                                                    errorflag = 3;
                                                    yearhonor3.setError("Kindly select valid Date");
                                                } else {
                                                    errorflag = 0;
                                                    if (stitle4.length() < 3) {
                                                        errorflag = 4;
                                                        titleinput4.setError("Kindly enter valid title");
                                                    } else {
                                                        errorflag = 0;
                                                        if (sissuer4.length() < 3) {
                                                            errorflag = 4;
                                                            issuerinput4.setError("Kindly enter valid Issuer");
                                                        } else {
                                                            errorflag = 0;
                                                            if (syearofhonor4.length() < 3) {
                                                                errorflag = 4;
                                                                yearhonor4.setError("Kindly select valid Date");
                                                            } else {
                                                                errorflag = 0;
                                                                if (stitle5.length() < 3) {
                                                                    errorflag = 5;
                                                                    titleinput5.setError("Kindly enter valid title");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sissuer5.length() < 3) {
                                                                        errorflag = 5;
                                                                        issuerinput5.setError("Kindly enter valid Issuer");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (syearofhonor5.length() < 3) {
                                                                            errorflag = 5;
                                                                            yearhonor5.setError("Kindly select valid Date");
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else if (honorcount == 5) {
                if (stitle1.length() < 3) {
                    errorflag = 1;
                    titleinput1.setError("Kindly enter valid title");
                } else {
                    errorflag = 0;
                    if (sissuer1.length() < 3) {
                        errorflag = 1;
                        issuerinput1.setError("Kindly enter valid Issuer");
                    } else {
                        errorflag = 0;
                        if (syearofhonor1.length() < 3) {
                            errorflag = 1;
                            yearhonor1.setError("Kindly select valid Date");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 2;
                                    issuerinput2.setError("Kindly enter valid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (syearofhonor2.length() < 3) {
                                        errorflag = 2;
                                        yearhonor2.setError("Kindly select valid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stitle3.length() < 3) {
                                            errorflag = 3;
                                            titleinput3.setError("Kindly enter valid title");
                                        } else {
                                            errorflag = 0;
                                            if (sissuer3.length() < 3) {
                                                errorflag = 3;
                                                issuerinput3.setError("Kindly enter valid Issuer");
                                            } else {
                                                errorflag = 0;
                                                if (syearofhonor3.length() < 3) {
                                                    errorflag = 3;
                                                    yearhonor3.setError("Kindly select valid Date");
                                                } else {
                                                    errorflag = 0;
                                                    if (stitle4.length() < 3) {
                                                        errorflag = 4;
                                                        titleinput4.setError("Kindly enter valid title");
                                                    } else {
                                                        errorflag = 0;
                                                        if (sissuer4.length() < 3) {
                                                            errorflag = 4;
                                                            issuerinput4.setError("Kindly enter valid Issuer");
                                                        } else {
                                                            errorflag = 0;
                                                            if (syearofhonor4.length() < 3) {
                                                                errorflag = 4;
                                                                yearhonor4.setError("Kindly select valid Date");
                                                            } else {
                                                                errorflag = 0;
                                                                if (stitle5.length() < 3) {
                                                                    errorflag = 5;
                                                                    titleinput5.setError("Kindly enter valid title");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sissuer5.length() < 3) {
                                                                        errorflag = 5;
                                                                        issuerinput5.setError("Kindly enter valid Issuer");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (syearofhonor5.length() < 3) {
                                                                            errorflag = 5;
                                                                            yearhonor5.setError("Kindly select valid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle6.length() < 3) {
                                                                                errorflag = 6;
                                                                                titleinput6.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer6.length() < 3) {
                                                                                    errorflag = 6;
                                                                                    issuerinput6.setError("Kindly enter valid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (syearofhonor6.length() < 3) {
                                                                                        errorflag = 6;
                                                                                        yearhonor6.setError("Kindly select valid Date");
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else if (honorcount == 6) {
                if (stitle1.length() < 3) {
                    errorflag = 1;
                    titleinput1.setError("Kindly enter valid title");
                } else {
                    errorflag = 0;
                    if (sissuer1.length() < 3) {
                        errorflag = 1;
                        issuerinput1.setError("Kindly enter valid Issuer");
                    } else {
                        errorflag = 0;
                        if (syearofhonor1.length() < 3) {
                            errorflag = 1;
                            yearhonor1.setError("Kindly select valid Date");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 2;
                                    issuerinput2.setError("Kindly enter valid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (syearofhonor2.length() < 3) {
                                        errorflag = 2;
                                        yearhonor2.setError("Kindly select valid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stitle3.length() < 3) {
                                            errorflag = 3;
                                            titleinput3.setError("Kindly enter valid title");
                                        } else {
                                            errorflag = 0;
                                            if (sissuer3.length() < 3) {
                                                errorflag = 3;
                                                issuerinput3.setError("Kindly enter valid Issuer");
                                            } else {
                                                errorflag = 0;
                                                if (syearofhonor3.length() < 3) {
                                                    errorflag = 3;
                                                    yearhonor3.setError("Kindly select valid Date");
                                                } else {
                                                    errorflag = 0;
                                                    if (stitle4.length() < 3) {
                                                        errorflag = 4;
                                                        titleinput4.setError("Kindly enter valid title");
                                                    } else {
                                                        errorflag = 0;
                                                        if (sissuer4.length() < 3) {
                                                            errorflag = 4;
                                                            issuerinput4.setError("Kindly enter valid Issuer");
                                                        } else {
                                                            errorflag = 0;
                                                            if (syearofhonor4.length() < 3) {
                                                                errorflag = 4;
                                                                yearhonor4.setError("Kindly select valid Date");
                                                            } else {
                                                                errorflag = 0;
                                                                if (stitle5.length() < 3) {
                                                                    errorflag = 5;
                                                                    titleinput5.setError("Kindly enter valid title");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sissuer5.length() < 3) {
                                                                        errorflag = 5;
                                                                        issuerinput5.setError("Kindly enter valid Issuer");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (syearofhonor5.length() < 3) {
                                                                            errorflag = 5;
                                                                            yearhonor5.setError("Kindly select valid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle6.length() < 3) {
                                                                                errorflag = 6;
                                                                                titleinput6.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer6.length() < 3) {
                                                                                    errorflag = 6;
                                                                                    issuerinput6.setError("Kindly enter valid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (syearofhonor6.length() < 3) {
                                                                                        errorflag = 6;
                                                                                        yearhonor6.setError("Kindly select valid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (stitle7.length() < 3) {
                                                                                            errorflag = 7;
                                                                                            titleinput7.setError("Kindly enter valid title");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (sissuer7.length() < 3) {
                                                                                                errorflag = 7;
                                                                                                issuerinput7.setError("Kindly enter valid Issuer");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (syearofhonor7.length() < 3) {
                                                                                                    errorflag = 7;
                                                                                                    yearhonor7.setError("Kindly select valid Date");
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else if (honorcount == 7) {
                if (stitle1.length() < 3) {
                    errorflag = 1;
                    titleinput1.setError("Kindly enter valid title");
                } else {
                    errorflag = 0;
                    if (sissuer1.length() < 3) {
                        errorflag = 1;
                        issuerinput1.setError("Kindly enter valid Issuer");
                    } else {
                        errorflag = 0;
                        if (syearofhonor1.length() < 3) {
                            errorflag = 1;
                            yearhonor1.setError("Kindly select valid Date");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 2;
                                    issuerinput2.setError("Kindly enter valid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (syearofhonor2.length() < 3) {
                                        errorflag = 2;
                                        yearhonor2.setError("Kindly select valid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stitle3.length() < 3) {
                                            errorflag = 3;
                                            titleinput3.setError("Kindly enter valid title");
                                        } else {
                                            errorflag = 0;
                                            if (sissuer3.length() < 3) {
                                                errorflag = 3;
                                                issuerinput3.setError("Kindly enter valid Issuer");
                                            } else {
                                                errorflag = 0;
                                                if (syearofhonor3.length() < 3) {
                                                    errorflag = 3;
                                                    yearhonor3.setError("Kindly select valid Date");
                                                } else {
                                                    errorflag = 0;
                                                    if (stitle4.length() < 3) {
                                                        errorflag = 4;
                                                        titleinput4.setError("Kindly enter valid title");
                                                    } else {
                                                        errorflag = 0;
                                                        if (sissuer4.length() < 3) {
                                                            errorflag = 4;
                                                            issuerinput4.setError("Kindly enter valid Issuer");
                                                        } else {
                                                            errorflag = 0;
                                                            if (syearofhonor4.length() < 3) {
                                                                errorflag = 4;
                                                                yearhonor4.setError("Kindly select valid Date");
                                                            } else {
                                                                errorflag = 0;
                                                                if (stitle5.length() < 3) {
                                                                    errorflag = 5;
                                                                    titleinput5.setError("Kindly enter valid title");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sissuer5.length() < 3) {
                                                                        errorflag = 5;
                                                                        issuerinput5.setError("Kindly enter valid Issuer");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (syearofhonor5.length() < 3) {
                                                                            errorflag = 5;
                                                                            yearhonor5.setError("Kindly select valid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle6.length() < 3) {
                                                                                errorflag = 6;
                                                                                titleinput6.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer6.length() < 3) {
                                                                                    errorflag = 6;
                                                                                    issuerinput6.setError("Kindly enter valid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (syearofhonor6.length() < 3) {
                                                                                        errorflag = 6;
                                                                                        yearhonor6.setError("Kindly select valid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (stitle7.length() < 3) {
                                                                                            errorflag = 7;
                                                                                            titleinput7.setError("Kindly enter valid title");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (sissuer7.length() < 3) {
                                                                                                errorflag = 7;
                                                                                                issuerinput7.setError("Kindly enter valid Issuer");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (syearofhonor7.length() < 3) {
                                                                                                    errorflag = 7;
                                                                                                    yearhonor7.setError("Kindly select valid Date");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (stitle8.length() < 3) {
                                                                                                        errorflag = 8;
                                                                                                        titleinput8.setError("Kindly enter valid title");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sissuer8.length() < 3) {
                                                                                                            errorflag = 8;
                                                                                                            issuerinput8.setError("Kindly enter valid Issuer");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (syearofhonor8.length() < 3) {
                                                                                                                errorflag = 8;
                                                                                                                yearhonor8.setError("Kindly select valid Date");
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else if (honorcount == 8) {
                if (stitle1.length() < 3) {
                    errorflag = 1;
                    titleinput1.setError("Kindly enter valid title");
                } else {
                    errorflag = 0;
                    if (sissuer1.length() < 3) {
                        errorflag = 1;
                        issuerinput1.setError("Kindly enter valid Issuer");
                    } else {
                        errorflag = 0;
                        if (syearofhonor1.length() < 3) {
                            errorflag = 1;
                            yearhonor1.setError("Kindly select valid Date");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 2;
                                    issuerinput2.setError("Kindly enter valid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (syearofhonor2.length() < 3) {
                                        errorflag = 2;
                                        yearhonor2.setError("Kindly select valid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stitle3.length() < 3) {
                                            errorflag = 3;
                                            titleinput3.setError("Kindly enter valid title");
                                        } else {
                                            errorflag = 0;
                                            if (sissuer3.length() < 3) {
                                                errorflag = 3;
                                                issuerinput3.setError("Kindly enter valid Issuer");
                                            } else {
                                                errorflag = 0;
                                                if (syearofhonor3.length() < 3) {
                                                    errorflag = 3;
                                                    yearhonor3.setError("Kindly select valid Date");
                                                } else {
                                                    errorflag = 0;
                                                    if (stitle4.length() < 3) {
                                                        errorflag = 4;
                                                        titleinput4.setError("Kindly enter valid title");
                                                    } else {
                                                        errorflag = 0;
                                                        if (sissuer4.length() < 3) {
                                                            errorflag = 4;
                                                            issuerinput4.setError("Kindly enter valid Issuer");
                                                        } else {
                                                            errorflag = 0;
                                                            if (syearofhonor4.length() < 3) {
                                                                errorflag = 4;
                                                                yearhonor4.setError("Kindly select valid Date");
                                                            } else {
                                                                errorflag = 0;
                                                                if (stitle5.length() < 3) {
                                                                    errorflag = 5;
                                                                    titleinput5.setError("Kindly enter valid title");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sissuer5.length() < 3) {
                                                                        errorflag = 5;
                                                                        issuerinput5.setError("Kindly enter valid Issuer");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (syearofhonor5.length() < 3) {
                                                                            errorflag = 5;
                                                                            yearhonor5.setError("Kindly select valid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle6.length() < 3) {
                                                                                errorflag = 6;
                                                                                titleinput6.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer6.length() < 3) {
                                                                                    errorflag = 6;
                                                                                    issuerinput6.setError("Kindly enter valid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (syearofhonor6.length() < 3) {
                                                                                        errorflag = 6;
                                                                                        yearhonor6.setError("Kindly select valid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (stitle7.length() < 3) {
                                                                                            errorflag = 7;
                                                                                            titleinput7.setError("Kindly enter valid title");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (sissuer7.length() < 3) {
                                                                                                errorflag = 7;
                                                                                                issuerinput7.setError("Kindly enter valid Issuer");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (syearofhonor7.length() < 3) {
                                                                                                    errorflag = 7;
                                                                                                    yearhonor7.setError("Kindly select valid Date");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (stitle8.length() < 3) {
                                                                                                        errorflag = 8;
                                                                                                        titleinput8.setError("Kindly enter valid title");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sissuer8.length() < 3) {
                                                                                                            errorflag = 8;
                                                                                                            issuerinput8.setError("Kindly enter valid Issuer");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (syearofhonor8.length() < 3) {
                                                                                                                errorflag = 8;
                                                                                                                yearhonor8.setError("Kindly select valid Date");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (stitle9.length() < 3) {
                                                                                                                    errorflag = 9;
                                                                                                                    titleinput9.setError("Kindly enter valid title");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sissuer9.length() < 3) {
                                                                                                                        errorflag = 9;
                                                                                                                        issuerinput9.setError("Kindly enter valid Issuer");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (syearofhonor9.length() < 3) {
                                                                                                                            errorflag = 9;
                                                                                                                            yearhonor9.setError("Kindly select valid Date");
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else if (honorcount == 9) {
                if (stitle1.length() < 3) {
                    errorflag = 1;
                    titleinput1.setError("Kindly enter valid title");
                } else {
                    errorflag = 0;
                    if (sissuer1.length() < 3) {
                        errorflag = 1;
                        issuerinput1.setError("Kindly enter valid Issuer");
                    } else {
                        errorflag = 0;
                        if (syearofhonor1.length() < 3) {
                            errorflag = 1;
                            yearhonor1.setError("Kindly select valid Date");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 2;
                                    issuerinput2.setError("Kindly enter valid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (syearofhonor2.length() < 3) {
                                        errorflag = 2;
                                        yearhonor2.setError("Kindly select valid Date");
                                    } else {
                                        errorflag = 0;
                                        if (stitle3.length() < 3) {
                                            errorflag = 3;
                                            titleinput3.setError("Kindly enter valid title");
                                        } else {
                                            errorflag = 0;
                                            if (sissuer3.length() < 3) {
                                                errorflag = 3;
                                                issuerinput3.setError("Kindly enter valid Issuer");
                                            } else {
                                                errorflag = 0;
                                                if (syearofhonor3.length() < 3) {
                                                    errorflag = 3;
                                                    yearhonor3.setError("Kindly select valid Date");
                                                } else {
                                                    errorflag = 0;
                                                    if (stitle4.length() < 3) {
                                                        errorflag = 4;
                                                        titleinput4.setError("Kindly enter valid title");
                                                    } else {
                                                        errorflag = 0;
                                                        if (sissuer4.length() < 3) {
                                                            errorflag = 4;
                                                            issuerinput4.setError("Kindly enter valid Issuer");
                                                        } else {
                                                            errorflag = 0;
                                                            if (syearofhonor4.length() < 3) {
                                                                errorflag = 4;
                                                                yearhonor4.setError("Kindly select valid Date");
                                                            } else {
                                                                errorflag = 0;
                                                                if (stitle5.length() < 3) {
                                                                    errorflag = 5;
                                                                    titleinput5.setError("Kindly enter valid title");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sissuer5.length() < 3) {
                                                                        errorflag = 5;
                                                                        issuerinput5.setError("Kindly enter valid Issuer");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (syearofhonor5.length() < 3) {
                                                                            errorflag = 5;
                                                                            yearhonor5.setError("Kindly select valid Date");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle6.length() < 3) {
                                                                                errorflag = 6;
                                                                                titleinput6.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer6.length() < 3) {
                                                                                    errorflag = 6;
                                                                                    issuerinput6.setError("Kindly enter valid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (syearofhonor6.length() < 3) {
                                                                                        errorflag = 6;
                                                                                        yearhonor6.setError("Kindly select valid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (stitle7.length() < 3) {
                                                                                            errorflag = 7;
                                                                                            titleinput7.setError("Kindly enter valid title");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (sissuer7.length() < 3) {
                                                                                                errorflag = 7;
                                                                                                issuerinput7.setError("Kindly enter valid Issuer");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (syearofhonor7.length() < 3) {
                                                                                                    errorflag = 7;
                                                                                                    yearhonor7.setError("Kindly select valid Date");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (stitle8.length() < 3) {
                                                                                                        errorflag = 8;
                                                                                                        titleinput8.setError("Kindly enter valid title");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (sissuer8.length() < 3) {
                                                                                                            errorflag = 8;
                                                                                                            issuerinput8.setError("Kindly enter valid Issuer");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (syearofhonor8.length() < 3) {
                                                                                                                errorflag = 8;
                                                                                                                yearhonor8.setError("Kindly select valid Date");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (stitle9.length() < 3) {
                                                                                                                    errorflag = 9;
                                                                                                                    titleinput9.setError("Kindly enter valid title");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sissuer9.length() < 3) {
                                                                                                                        errorflag = 9;
                                                                                                                        issuerinput9.setError("Kindly enter valid Issuer");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (syearofhonor9.length() < 3) {
                                                                                                                            errorflag = 9;
                                                                                                                            yearhonor9.setError("Kindly select valid Date");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (stitle10.length() < 3) {
                                                                                                                                errorflag = 10;
                                                                                                                                titleinput10.setError("Kindly enter valid title");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sissuer10.length() < 3) {
                                                                                                                                    errorflag = 10;
                                                                                                                                    issuerinput10.setError("Kindly enter valid Issuer");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (syearofhonor10.length() < 3) {
                                                                                                                                        errorflag = 10;
                                                                                                                                        yearhonor10.setError("Kindly select valid Date");
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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

            encachieve();
        }
    }

    public void encachieve()
    {
        try {

            Honors obj1 = new Honors(stitle1, sissuer1, sdescription1, syearofhonor1);
            Honors obj2 = new Honors(stitle2, sissuer2, sdescription2, syearofhonor2);
            Honors obj3 = new Honors(stitle3, sissuer3, sdescription3, syearofhonor3);
            Honors obj4 = new Honors(stitle4, sissuer4, sdescription4, syearofhonor4);
            Honors obj5 = new Honors(stitle5, sissuer5, sdescription5, syearofhonor5);
            Honors obj6 = new Honors(stitle6, sissuer6, sdescription6, syearofhonor6);
            Honors obj7 = new Honors(stitle7, sissuer7, sdescription7, syearofhonor7);
            Honors obj8 = new Honors(stitle8, sissuer8, sdescription8, syearofhonor8);
            Honors obj9 = new Honors(stitle9, sissuer9, sdescription9, syearofhonor9);
            Honors obj10 = new Honors(stitle10, sissuer10, sdescription10, syearofhonor10);

            honorsList.add(obj1);
            honorsList.add(obj2);
            honorsList.add(obj3);
            honorsList.add(obj4);
            honorsList.add(obj5);
            honorsList.add(obj6);
            honorsList.add(obj7);
            honorsList.add(obj8);
            honorsList.add(obj9);
            honorsList.add(obj10);

            String encObjString = OtoString(honorsList, MySharedPreferencesManager.getDigest1(MyProfileAchievements.this), MySharedPreferencesManager.getDigest2(MyProfileAchievements.this));

            new SaveHonors().execute(encObjString);

        } catch (Exception e) {
            Toast.makeText(MyProfileAchievements.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
                                    MyProfileAchievements.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(MyConstants.getBold(MyProfileAchievements.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(MyConstants.getBold(MyProfileAchievements.this));

                }
            });

            alertDialog.show();
        } else
            MyProfileAchievements.super.onBackPressed();
    }

    class SaveHonors extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("d", param[0]));       //0

            json = jParser.makeHttpRequest(MyConstants.url_savehonors, "GET", params);
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
                Toast.makeText(MyProfileAchievements.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();

                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("hr"))
                    setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("admin"))
                    setResult(AdminActivity.ADMIN_DATA_CHANGE_RESULT_CODE);


                s.setHtitle1(stitle1);
                s.setHissuer1(sissuer1);
                s.setHdescription1(sdescription1);
                s.setYearofhonor1(syearofhonor1);
                s.setHtitle2(stitle2);
                s.setHissuer2(sissuer2);
                s.setHdescription2(sdescription2);
                s.setYearofhonor2(syearofhonor2);
                s.setHtitle3(stitle3);
                s.setHissuer3(sissuer3);
                s.setHdescription3(sdescription3);
                s.setYearofhonor3(syearofhonor3);
                s.setHtitle4(stitle4);
                s.setHissuer4(sissuer4);
                s.setHdescription4(sdescription4);
                s.setYearofhonor4(syearofhonor4);
                s.setHtitle5(stitle5);
                s.setHissuer5(sissuer5);
                s.setHdescription5(sdescription5);
                s.setYearofhonor5(syearofhonor5);
                s.setHtitle6(stitle6);
                s.setHissuer6(sissuer6);
                s.setHdescription6(sdescription6);
                s.setYearofhonor6(syearofhonor6);
                s.setHtitle7(stitle7);
                s.setHissuer7(sissuer7);
                s.setHdescription7(sdescription7);
                s.setYearofhonor7(syearofhonor7);
                s.setHtitle8(stitle8);
                s.setHissuer8(sissuer8);
                s.setHdescription8(sdescription8);
                s.setYearofhonor8(syearofhonor8);
                s.setHtitle9(stitle9);
                s.setHissuer9(sissuer9);
                s.setHdescription9(sdescription9);
                s.setYearofhonor9(syearofhonor9);
                s.setHtitle10(stitle10);
                s.setHissuer10(sissuer10);
                s.setHdescription10(sdescription10);
                s.setYearofhonor10(syearofhonor10);

                MyProfileAchievements.super.onBackPressed();
            } else
                Toast.makeText(MyProfileAchievements.this, result, Toast.LENGTH_SHORT).show();

        }
    }
}
