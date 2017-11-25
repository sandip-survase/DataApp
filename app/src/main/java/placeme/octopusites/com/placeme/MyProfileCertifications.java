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
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
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

import placeme.octopusites.com.placeme.modal.Certificates;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class MyProfileCertifications extends AppCompatActivity {

    int certicount = 0;
    View addmorecerti;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username,role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;

    boolean blnswitch1, blnswitch2, blnswitch3, blnswitch4, blnswitch5, blnswitch6, blnswitch7, blnswitch8, blnswitch9, blnswitch10;
    EditText title1, issuer1, license1, title2, issuer2, license2, title3, issuer3, license3, title4, issuer4, license4, title5, issuer5, license5, title6, issuer6, license6, title7, issuer7, license7, title8, issuer8, license8, title9, issuer9, license9, title10, issuer10, license10;
    EditText startdate1, enddate1, startdate2, enddate2, startdate3, enddate3, startdate4, enddate4, startdate5, enddate5, startdate6, enddate6, startdate7, enddate7, startdate8, enddate8, startdate9, enddate9, startdate10, enddate10;
    String encstartdate1, encenddate1, encstartdate2, encenddate2, encstartdate3, encenddate3, encstartdate4, encenddate4, encstartdate5, encenddate5, encstartdate6, encenddate6, encstartdate7, encenddate7, encstartdate8, encenddate8, encstartdate9, encenddate9, encstartdate10, encenddate10;
    String stitle1 = "", sissuer1 = "", slicense1 = "", sstartdate1 = "", senddate1 = "", stitle2 = "", sissuer2 = "", slicense2 = "", sstartdate2 = "", senddate2 = "", stitle3 = "", sissuer3 = "", slicense3 = "", sstartdate3 = "", senddate3 = "", stitle4 = "", sissuer4 = "", slicense4 = "", sstartdate4 = "", senddate4 = "", stitle5 = "", sissuer5 = "", slicense5 = "", sstartdate5 = "", senddate5 = "", stitle6 = "", sissuer6 = "", slicense6 = "", sstartdate6 = "", senddate6 = "", stitle7 = "", sissuer7 = "", slicense7 = "", sstartdate7 = "", senddate7 = "", stitle8 = "", sissuer8 = "", slicense8 = "", sstartdate8 = "", senddate8 = "", stitle9 = "", sissuer9 = "", slicense9 = "", sstartdate9 = "", senddate9 = "", stitle10 = "", sissuer10 = "", slicense10 = "", sstartdate10 = "", senddate10 = "";
    String willexpire1 = "yes", willexpire2 = "yes", willexpire3 = "yes", willexpire4 = "yes", willexpire5 = "yes", willexpire6 = "yes", willexpire7 = "yes", willexpire8 = "yes", willexpire9 = "yes", willexpire10 = "yes";
    String encwillexpire1, encwillexpire2, encwillexpire3, encwillexpire4, encwillexpire5, encwillexpire6, encwillexpire7, encwillexpire8, encwillexpire9, encwillexpire10;
    String enctitle1, encissuer1, enclicense1, enctitle2, encissuer2, enclicense2, enctitle3, encissuer3, enclicense3, enctitle4, encissuer4, enclicense4, enctitle5, encissuer5, enclicense5, enctitle6, encissuer6, enclicense6, enctitle7, encissuer7, enclicense7, enctitle8, encissuer8, enclicense8, enctitle9, encissuer9, enclicense9, enctitle10, encissuer10, enclicense10;
    SwitchCompat switch1, switch2, switch3, switch4, switch5, switch6, switch7, switch8, switch9, switch10;
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview, trash6selectionview, trash7selectionview, trash8selectionview, trash9selectionview, trash10selectionview;
    int edittedFlag = 0;
    ;
    int d = 0;
    StudentData s = new StudentData();

    ArrayList<Certificates> certificatesList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_certifications);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Certification Info");
        ab.setDisplayHomeAsUpEnabled(true);

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

        title1 = (EditText) findViewById(R.id.title1);
        issuer1 = (EditText) findViewById(R.id.issuer1);
        license1 = (EditText) findViewById(R.id.license1);
        title2 = (EditText) findViewById(R.id.title2);
        issuer2 = (EditText) findViewById(R.id.issuer2);
        license2 = (EditText) findViewById(R.id.license2);
        title3 = (EditText) findViewById(R.id.title3);
        issuer3 = (EditText) findViewById(R.id.issuer3);
        license3 = (EditText) findViewById(R.id.license3);
        title4 = (EditText) findViewById(R.id.title4);
        issuer4 = (EditText) findViewById(R.id.issuer4);
        license4 = (EditText) findViewById(R.id.license4);
        title5 = (EditText) findViewById(R.id.title5);
        issuer5 = (EditText) findViewById(R.id.issuer5);
        license5 = (EditText) findViewById(R.id.license5);
        title6 = (EditText) findViewById(R.id.title6);
        issuer6 = (EditText) findViewById(R.id.issuer6);
        license6 = (EditText) findViewById(R.id.license6);
        title7 = (EditText) findViewById(R.id.title7);
        issuer7 = (EditText) findViewById(R.id.issuer7);
        license7 = (EditText) findViewById(R.id.license7);
        title8 = (EditText) findViewById(R.id.title8);
        issuer8 = (EditText) findViewById(R.id.issuer8);
        license8 = (EditText) findViewById(R.id.license8);
        title9 = (EditText) findViewById(R.id.title9);
        issuer9 = (EditText) findViewById(R.id.issuer9);
        license9 = (EditText) findViewById(R.id.license9);
        title10 = (EditText) findViewById(R.id.title10);
        issuer10 = (EditText) findViewById(R.id.issuer10);
        license10 = (EditText) findViewById(R.id.license10);

        title1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                title1.setError(null);
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
                issuer1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        license1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                license1.setError(null);
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
                title2.setError(null);
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
                issuer2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        license2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                license2.setError(null);
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
                title3.setError(null);
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
                issuer3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        license3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                license3.setError(null);
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
                title4.setError(null);
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
                issuer4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        license4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                license4.setError(null);
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
                title5.setError(null);
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
                issuer5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        license5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                license5.setError(null);
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
                title6.setError(null);
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
                issuer6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        license6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                license6.setError(null);
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
                title7.setError(null);
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
                issuer7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        license7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                license7.setError(null);
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
                title8.setError(null);
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
                issuer8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        license8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                license8.setError(null);
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
                title9.setError(null);
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
                issuer9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        license9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                license9.setError(null);
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
                title10.setError(null);
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
                issuer10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        license10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                license10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        switch1 = (SwitchCompat) findViewById(R.id.switch1);
        switch2 = (SwitchCompat) findViewById(R.id.switch2);
        switch3 = (SwitchCompat) findViewById(R.id.switch3);
        switch4 = (SwitchCompat) findViewById(R.id.switch4);
        switch5 = (SwitchCompat) findViewById(R.id.switch5);
        switch6 = (SwitchCompat) findViewById(R.id.switch6);
        switch7 = (SwitchCompat) findViewById(R.id.switch7);
        switch8 = (SwitchCompat) findViewById(R.id.switch8);
        switch9 = (SwitchCompat) findViewById(R.id.switch9);
        switch10 = (SwitchCompat) findViewById(R.id.switch10);


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                edittedFlag = 1;
                if (isChecked) {
                    enddate1.setVisibility(View.GONE);
                    enddate1.setText("");
                } else
                    enddate1.setVisibility(View.VISIBLE);

            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                edittedFlag = 1;
                if (isChecked) {
                    enddate2.setVisibility(View.GONE);
                } else
                    enddate2.setVisibility(View.VISIBLE);

            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                edittedFlag = 1;
                if (isChecked) {
                    enddate3.setVisibility(View.GONE);
                } else
                    enddate3.setVisibility(View.VISIBLE);

            }
        });
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                edittedFlag = 1;
                if (isChecked) {
                    enddate4.setVisibility(View.GONE);
                } else
                    enddate4.setVisibility(View.VISIBLE);

            }
        });
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                edittedFlag = 1;
                if (isChecked) {
                    enddate5.setVisibility(View.GONE);
                } else
                    enddate5.setVisibility(View.VISIBLE);

            }
        });
        switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                edittedFlag = 1;
                if (isChecked) {
                    enddate6.setVisibility(View.GONE);
                } else
                    enddate6.setVisibility(View.VISIBLE);

            }
        });
        switch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                edittedFlag = 1;
                if (isChecked) {
                    enddate7.setVisibility(View.GONE);
                } else
                    enddate7.setVisibility(View.VISIBLE);

            }
        });
        switch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                edittedFlag = 1;
                if (isChecked) {
                    enddate8.setVisibility(View.GONE);
                } else
                    enddate8.setVisibility(View.VISIBLE);

            }
        });
        switch9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                edittedFlag = 1;
                if (isChecked) {
                    enddate9.setVisibility(View.GONE);
                } else
                    enddate9.setVisibility(View.VISIBLE);

            }
        });
        switch10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                edittedFlag = 1;
                if (isChecked) {
                    enddate10.setVisibility(View.GONE);
                } else
                    enddate10.setVisibility(View.VISIBLE);

            }
        });


        startdate1 = (EditText) findViewById(R.id.startdate1);
        enddate1 = (EditText) findViewById(R.id.enddate1);
        startdate2 = (EditText) findViewById(R.id.startdate2);
        enddate2 = (EditText) findViewById(R.id.enddate2);
        startdate3 = (EditText) findViewById(R.id.startdate3);
        enddate3 = (EditText) findViewById(R.id.enddate3);
        startdate4 = (EditText) findViewById(R.id.startdate4);
        enddate4 = (EditText) findViewById(R.id.enddate4);
        startdate5 = (EditText) findViewById(R.id.startdate5);
        enddate5 = (EditText) findViewById(R.id.enddate5);
        startdate6 = (EditText) findViewById(R.id.startdate6);
        enddate6 = (EditText) findViewById(R.id.enddate6);
        startdate7 = (EditText) findViewById(R.id.startdate7);
        enddate7 = (EditText) findViewById(R.id.enddate7);
        startdate8 = (EditText) findViewById(R.id.startdate8);
        enddate8 = (EditText) findViewById(R.id.enddate8);
        startdate9 = (EditText) findViewById(R.id.startdate9);
        enddate9 = (EditText) findViewById(R.id.enddate9);
        startdate10 = (EditText) findViewById(R.id.startdate10);
        enddate10 = (EditText) findViewById(R.id.enddate10);

        startdate1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                startdate1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enddate1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                enddate1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        startdate2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                startdate2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enddate2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                enddate2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        startdate3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                startdate3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enddate3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                enddate3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        startdate4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                startdate4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enddate4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                enddate4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        startdate5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                startdate5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enddate5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                enddate5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        startdate6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                startdate6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enddate6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                enddate6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        startdate7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                startdate7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enddate7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                enddate7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        startdate8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                startdate8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enddate8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                enddate8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        startdate9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                startdate9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enddate9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                enddate9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        startdate10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                startdate10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enddate10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                enddate10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        startdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // 1- id   2- isFromDateSelected 3-fromYear   4- month in str

                String toDate = enddate1.getText().toString();
                showDateDialog(startdate1, false, 0, "", toDate);


            }
        });
        enddate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!startdate1.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = startdate1.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(enddate1, isFromDateSelected, fromYear, fromMonth, "");
            }
        });

        startdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = enddate2.getText().toString();
                showDateDialog(startdate2, false, 0, "", toDate);
            }
        });
        enddate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!startdate2.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = startdate2.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(enddate2, isFromDateSelected, fromYear, fromMonth, "");
            }
        });


        startdate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = enddate3.getText().toString();
                showDateDialog(startdate3, false, 0, "", toDate);
            }
        });
        enddate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!startdate3.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = startdate3.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(enddate3, isFromDateSelected, fromYear, fromMonth, "");
            }
        });


        startdate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = enddate4.getText().toString();
                showDateDialog(startdate4, false, 0, "", toDate);

            }
        });
        enddate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!startdate4.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = startdate4.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(enddate4, isFromDateSelected, fromYear, fromMonth, "");
            }
        });


        startdate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = enddate5.getText().toString();
                showDateDialog(startdate5, false, 0, "", toDate);
            }
        });
        enddate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!startdate5.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = startdate5.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(enddate5, isFromDateSelected, fromYear, fromMonth, "");
            }
        });


        startdate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = enddate6.getText().toString();
                showDateDialog(startdate6, false, 0, "", toDate);

            }
        });
        enddate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!startdate6.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = startdate6.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(enddate6, isFromDateSelected, fromYear, fromMonth, "");
            }
        });


        startdate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = enddate7.getText().toString();
                showDateDialog(startdate7, false, 0, "", toDate);

            }
        });
        enddate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!startdate7.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = startdate7.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(enddate7, isFromDateSelected, fromYear, fromMonth, "");
            }
        });


        startdate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = enddate8.getText().toString();
                showDateDialog(startdate8, false, 0, "", toDate);

            }
        });
        enddate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!startdate8.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = startdate8.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(enddate8, isFromDateSelected, fromYear, fromMonth, "");
            }
        });


        startdate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDate = enddate9.getText().toString();
                showDateDialog(startdate9, false, 0, "", toDate);
            }
        });
        enddate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!startdate9.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = startdate9.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(enddate9, isFromDateSelected, fromYear, fromMonth, "");
            }
        });


        startdate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDate = enddate10.getText().toString();
                showDateDialog(startdate10, false, 0, "", toDate);
            }
        });
        enddate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromYear = 0;
                String fromMonth = "";
                boolean isFromDateSelected = false;

                if (!startdate10.getText().toString().equals("")) {
                    isFromDateSelected = true;
                    String[] splited = startdate10.getText().toString().split(", ");
                    if (splited.length == 2) {
                        fromMonth = splited[0];
                        fromYear = Integer.parseInt(splited[1]);
                    }
                } else {
                    isFromDateSelected = false;
                    fromYear = 0;
                    fromMonth = "";
                }
                showDateDialog(enddate10, isFromDateSelected, fromYear, fromMonth, "");
            }
        });



        TextView certitxt = (TextView) findViewById(R.id.certitxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/arba.ttf");
        certitxt.setTypeface(custom_font1);

        addmorecerti = (View) findViewById(R.id.addmorecerti);
        addmorecerti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (certicount == 0) {
                    if(title1.getText().toString()!=null && issuer1.getText().toString()!=null && license1.getText().toString()!=null && startdate1.getText().toString()!=null && enddate1.getText().toString()!=null)
                    {
                        if(!title1.getText().toString().equals("") && !issuer1.getText().toString().equals("") && !license1.getText().toString().equals("") && !startdate1.getText().toString().equals("") && !enddate1.getText().toString().equals(""))
                        {

                            View v = (View) findViewById(R.id.certiline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl2);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            certicount++;
                        }
                        else
                            Toast.makeText(MyProfileCertifications.this, "Please fill the first Certificate", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileCertifications.this, "Please fill the first Certificate", Toast.LENGTH_SHORT).show();



                } else if (certicount == 1) {
                    if(title2.getText().toString()!=null && issuer2.getText().toString()!=null && license2.getText().toString()!=null && startdate2.getText().toString()!=null && enddate2.getText().toString()!=null)
                    {
                        if(!title2.getText().toString().equals("") && !issuer2.getText().toString().equals("") && !license2.getText().toString().equals("") && !startdate2.getText().toString().equals("") && !enddate2.getText().toString().equals(""))
                        {

                            View v = (View) findViewById(R.id.certiline2);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl3);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            certicount++;
                        }
                        else
                            Toast.makeText(MyProfileCertifications.this, "Please fill the Second Certificate", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileCertifications.this, "Please fill the Second Certificate", Toast.LENGTH_SHORT).show();


                } else if (certicount == 2) {
                    if(title3.getText().toString()!=null && issuer3.getText().toString()!=null && license3.getText().toString()!=null && startdate3.getText().toString()!=null && enddate3.getText().toString()!=null)
                    {
                        if(!title3.getText().toString().equals("") && !issuer3.getText().toString().equals("") && !license3.getText().toString().equals("") && !startdate3.getText().toString().equals("") && !enddate3.getText().toString().equals(""))
                        {
                            View v = (View) findViewById(R.id.certiline3);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl4);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            certicount++;
                        }
                        else
                            Toast.makeText(MyProfileCertifications.this, "Please fill the Third Certificate", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileCertifications.this, "Please fill the Third Certificate", Toast.LENGTH_SHORT).show();


                } else if (certicount == 3) {
                    if(title4.getText().toString()!=null && issuer4.getText().toString()!=null && license4.getText().toString()!=null && startdate4.getText().toString()!=null && enddate4.getText().toString()!=null)
                    {
                        if(!title4.getText().toString().equals("") && !issuer4.getText().toString().equals("") && !license4.getText().toString().equals("") && !startdate4.getText().toString().equals("") && !enddate4.getText().toString().equals(""))
                        {
                            View v = (View) findViewById(R.id.certiline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl5);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            certicount++;
                        }
                        else
                            Toast.makeText(MyProfileCertifications.this, "Please fill the Fourth Certificate", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileCertifications.this, "Please fill the Fourth Certificate", Toast.LENGTH_SHORT).show();


                } else if (certicount == 4) {
                    if(title5.getText().toString()!=null && issuer5.getText().toString()!=null && license5.getText().toString()!=null && startdate5.getText().toString()!=null && enddate5.getText().toString()!=null)
                    {
                        if(!title5.getText().toString().equals("") && !issuer5.getText().toString().equals("") && !license5.getText().toString().equals("") && !startdate5.getText().toString().equals("") && !enddate5.getText().toString().equals(""))
                        {
                            View v = (View) findViewById(R.id.certiline5);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl6);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            certicount++;
                        }
                        else
                            Toast.makeText(MyProfileCertifications.this, "Please fill the Fifth Certificate", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileCertifications.this, "Please fill the Fifth Certificate", Toast.LENGTH_SHORT).show();


                } else if (certicount == 5) {

                    if(title6.getText().toString()!=null && issuer6.getText().toString()!=null && license6.getText().toString()!=null && startdate6.getText().toString()!=null && enddate6.getText().toString()!=null)
                    {
                        if(!title6.getText().toString().equals("") && !issuer6.getText().toString().equals("") && !license6.getText().toString().equals("") && !startdate6.getText().toString().equals("") && !enddate6.getText().toString().equals(""))
                        {
                            View v = (View) findViewById(R.id.certiline6);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl7);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            certicount++;
                        }
                        else
                            Toast.makeText(MyProfileCertifications.this, "Please fill the Sixth Certificate", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileCertifications.this, "Please fill the Sixth Certificate", Toast.LENGTH_SHORT).show();


                } else if (certicount == 6) {
                    if(title7.getText().toString()!=null && issuer7.getText().toString()!=null && license7.getText().toString()!=null && startdate7.getText().toString()!=null && enddate7.getText().toString()!=null)
                    {
                        if(!title7.getText().toString().equals("") && !issuer7.getText().toString().equals("") && !license7.getText().toString().equals("") && !startdate7.getText().toString().equals("") && !enddate7.getText().toString().equals(""))
                        {
                            View v = (View) findViewById(R.id.certiline7);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl8);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            certicount++;
                        }
                        else
                            Toast.makeText(MyProfileCertifications.this, "Please fill the Seventh Certificate", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileCertifications.this, "Please fill the Seventh Certificate", Toast.LENGTH_SHORT).show();


                } else if (certicount == 7) {
                    if(title8.getText().toString()!=null && issuer8.getText().toString()!=null && license8.getText().toString()!=null && startdate8.getText().toString()!=null && enddate8.getText().toString()!=null)
                    {
                        if(!title8.getText().toString().equals("") && !issuer8.getText().toString().equals("") && !license8.getText().toString().equals("") && !startdate8.getText().toString().equals("") && !enddate8.getText().toString().equals(""))
                        {
                            View v = (View) findViewById(R.id.certiline8);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl9);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            certicount++;
                        }
                        else
                            Toast.makeText(MyProfileCertifications.this, "Please fill the Eighth Certificate", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileCertifications.this, "Please fill the Eighth Certificate", Toast.LENGTH_SHORT).show();


                } else if (certicount == 8) {
                    if(title9.getText().toString()!=null && issuer9.getText().toString()!=null && license9.getText().toString()!=null && startdate9.getText().toString()!=null && enddate9.getText().toString()!=null)
                    {
                        if(!title9.getText().toString().equals("") && !issuer9.getText().toString().equals("") && !license9.getText().toString().equals("") && !startdate9.getText().toString().equals("") && !enddate9.getText().toString().equals(""))
                        {
                            View v = (View) findViewById(R.id.certiline9);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl10);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            certicount++;
                            TextView t = (TextView) findViewById(R.id.addmorecertitxt);
                            ImageView i = (ImageView) findViewById(R.id.addmorecertiimg);
                            addmorecerti.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);
                        }
                        else
                            Toast.makeText(MyProfileCertifications.this, "Please fill the Nineth Certificate", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MyProfileCertifications.this, "Please fill the Nineth Certificate", Toast.LENGTH_SHORT).show();


                }

            }
        });

        ScrollView myprofileintroscrollview = (ScrollView) findViewById(R.id.myprofilecertification);
        disableScrollbars(myprofileintroscrollview);



        stitle1 = s.getTitle1();
        sissuer1 = s.getIssuer1();
        slicense1 = s.getLicense1();
        sstartdate1 = s.getStartdate1certificate();
        senddate1 = s.getEnddate1certificate();

        // willexpire1,2,3,4.... is set to NULL  from object getter so added null check to willexpie1,2,3,4...

        if (s.getWillexpire1certificate() != null)
            willexpire1 = s.getWillexpire1certificate();

        stitle2 = s.getTitle2();
        sissuer2 = s.getIssuer2();
        slicense2 = s.getLicense2();
        sstartdate2 = s.getStartdate2certificate();
        senddate2 = s.getEnddate2certificate();
        if (s.getWillexpire2certificate() != null)
            willexpire2 = s.getWillexpire2certificate();

        stitle3 = s.getTitle3();
        sissuer3 = s.getIssuer3();
        slicense3 = s.getLicense3();
        sstartdate3 = s.getStartdate3certificate();
        senddate3 = s.getEnddate3certificate();
        if (s.getWillexpire3certificate() != null)
            willexpire3 = s.getWillexpire3certificate();

        stitle4 = s.getTitle4();
        sissuer4 = s.getIssuer4();
        slicense4 = s.getLicense4();
        sstartdate4 = s.getStartdate4certificate();
        senddate4 = s.getEnddate4certificate();
        if (s.getWillexpire4certificate() != null)
            willexpire4 = s.getWillexpire4certificate();

        stitle5 = s.getTitle5();
        sissuer5 = s.getIssuer5();
        slicense5 = s.getLicense5();
        sstartdate5 = s.getStartdate5certificate();
        senddate5 = s.getEnddate5certificate();
        if (s.getWillexpire5certificate() != null)
            willexpire5 = s.getWillexpire5certificate();

        stitle6 = s.getTitle6();
        sissuer6 = s.getIssuer6();
        slicense6 = s.getLicense6();
        sstartdate6 = s.getStartdate6certificate();
        senddate6 = s.getEnddate6certificate();
        if (s.getWillexpire6certificate() != null)
            willexpire6 = s.getWillexpire6certificate();

        stitle7 = s.getTitle7();
        sissuer7 = s.getIssuer7();
        slicense7 = s.getLicense7();
        sstartdate7 = s.getStartdate7certificate();
        senddate7 = s.getEnddate7certificate();
        if (s.getWillexpire7certificate() != null)
            willexpire7 = s.getWillexpire7certificate();

        stitle8 = s.getTitle8();
        sissuer8 = s.getIssuer8();
        slicense8 = s.getLicense8();
        sstartdate8 = s.getStartdate8certificate();
        senddate8 = s.getEnddate8certificate();
        if (s.getWillexpire8certificate() != null)
            willexpire8 = s.getWillexpire8certificate();

        stitle9 = s.getTitle9();
        sissuer9 = s.getIssuer9();
        slicense9 = s.getLicense9();
        sstartdate9 = s.getStartdate9certificate();
        senddate9 = s.getEnddate9certificate();
        if (s.getWillexpire9certificate() != null)
            willexpire9 = s.getWillexpire9certificate();

        stitle10 = s.getTitle10();
        sissuer10 = s.getIssuer10();
        slicense10 = s.getLicense10();
        sstartdate10 = s.getStartdate10certificate();
        senddate10 = s.getEnddate10certificate();
        if (s.getWillexpire10certificate() != null)
            willexpire10 = s.getWillexpire10certificate();

        if (stitle1 != null) {
            if (stitle1.length() > 2) {
                title1.setText(stitle1);
                issuer1.setText(sissuer1);
                license1.setText(slicense1);
                startdate1.setText(sstartdate1);
                enddate1.setText(senddate1);
                if (willexpire1.equals("no")) {
                    switch1.setChecked(true);
                    enddate1.setVisibility(View.GONE);
                }
            }
        }
        if (stitle2 != null) {
            if (stitle2.length() > 2) {
                title2.setText(stitle2);
                issuer2.setText(sissuer2);
                license2.setText(slicense2);
                startdate2.setText(sstartdate2);
                enddate2.setText(senddate2);
                if (willexpire2.equals("no")) {
                    switch2.setChecked(true);
                    enddate2.setVisibility(View.GONE);
                }
                View v = (View) findViewById(R.id.certiline1);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl2);
                relativeLayout1.setVisibility(View.VISIBLE);
                certicount++;
            }
        }
        if (stitle3 != null) {
            if (stitle3.length() > 2) {
                title3.setText(stitle3);
                issuer3.setText(sissuer3);
                license3.setText(slicense3);
                startdate3.setText(sstartdate3);
                enddate3.setText(senddate3);
                if (willexpire3.equals("no")) {
                    switch3.setChecked(true);
                    enddate3.setVisibility(View.GONE);
                }
                View v = (View) findViewById(R.id.certiline2);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl3);
                relativeLayout1.setVisibility(View.VISIBLE);
                certicount++;
            }
        }
        if (stitle4 != null) {
            if (stitle4.length() > 2) {
                title4.setText(stitle4);
                issuer4.setText(sissuer4);
                license4.setText(slicense4);
                startdate4.setText(sstartdate4);
                enddate4.setText(senddate4);
                if (willexpire4.equals("no")) {
                    switch4.setChecked(true);
                    enddate4.setVisibility(View.GONE);
                }
                View v = (View) findViewById(R.id.certiline3);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl4);
                relativeLayout1.setVisibility(View.VISIBLE);
                certicount++;
            }
        }
        if (stitle5 != null) {
            if (stitle5.length() > 2) {
                title5.setText(stitle5);
                issuer5.setText(sissuer5);
                license5.setText(slicense5);
                startdate5.setText(sstartdate5);
                enddate5.setText(senddate5);
                if (willexpire5.equals("no")) {
                    switch5.setChecked(true);
                    enddate5.setVisibility(View.GONE);
                }
                View v = (View) findViewById(R.id.certiline4);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl5);
                relativeLayout1.setVisibility(View.VISIBLE);
                certicount++;
            }
        }
        if (stitle6 != null) {
            if (stitle6.length() > 2) {
                title6.setText(stitle6);
                issuer6.setText(sissuer6);
                license6.setText(slicense6);
                startdate6.setText(sstartdate6);
                enddate6.setText(senddate6);
                if (willexpire6.equals("no")) {
                    switch6.setChecked(true);
                    enddate6.setVisibility(View.GONE);
                }
                View v = (View) findViewById(R.id.certiline5);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl6);
                relativeLayout1.setVisibility(View.VISIBLE);
                certicount++;
            }
        }
        if (stitle7 != null) {
            if (stitle7.length() > 2) {
                title7.setText(stitle7);
                issuer7.setText(sissuer7);
                license7.setText(slicense7);
                startdate7.setText(sstartdate7);
                enddate7.setText(senddate7);
                if (willexpire7.equals("no")) {
                    switch7.setChecked(true);
                    enddate7.setVisibility(View.GONE);
                }
                View v = (View) findViewById(R.id.certiline6);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl7);
                relativeLayout1.setVisibility(View.VISIBLE);
                certicount++;
            }
        }
        if (stitle8 != null) {
            if (stitle8.length() > 2) {
                title8.setText(stitle8);
                issuer8.setText(sissuer8);
                license8.setText(slicense8);
                startdate8.setText(sstartdate8);
                enddate8.setText(senddate8);
                if (willexpire8.equals("no")) {
                    switch8.setChecked(true);
                    enddate8.setVisibility(View.GONE);
                }
                View v = (View) findViewById(R.id.certiline7);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl8);
                relativeLayout1.setVisibility(View.VISIBLE);
                certicount++;
            }
        }
        if (stitle9 != null) {
            if (stitle9.length() > 2) {
                title9.setText(stitle9);
                issuer9.setText(sissuer9);
                license9.setText(slicense9);
                startdate9.setText(sstartdate9);
                enddate9.setText(senddate9);
                if (willexpire9.equals("no")) {
                    switch9.setChecked(true);
                    enddate9.setVisibility(View.GONE);
                }
                View v = (View) findViewById(R.id.certiline8);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl9);
                relativeLayout1.setVisibility(View.VISIBLE);
                certicount++;
            }
        }
        if (stitle10 != null) {
            if (stitle10.length() > 2) {
                title10.setText(stitle10);
                issuer10.setText(sissuer10);
                license10.setText(slicense10);
                startdate10.setText(sstartdate10);
                enddate10.setText(senddate10);
                if (willexpire10.equals("no")) {
                    switch10.setChecked(true);
                    enddate10.setVisibility(View.GONE);
                }
                View v = (View) findViewById(R.id.certiline9);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.certirl10);
                relativeLayout1.setVisibility(View.VISIBLE);
                certicount++;
            }
        }

        edittedFlag = 0;

    }

    void validateandSave() {
        title1.setError(null);
        issuer1.setError(null);
        license1.setError(null);
        startdate1.setError(null);
        enddate1.setError(null);
        title2.setError(null);
        issuer2.setError(null);
        license2.setError(null);
        startdate2.setError(null);
        enddate2.setError(null);
        title3.setError(null);
        issuer3.setError(null);
        license3.setError(null);
        startdate3.setError(null);
        enddate3.setError(null);
        title4.setError(null);
        issuer4.setError(null);
        license4.setError(null);
        startdate4.setError(null);
        enddate4.setError(null);
        title5.setError(null);
        issuer5.setError(null);
        license5.setError(null);
        startdate5.setError(null);
        enddate5.setError(null);
        title6.setError(null);
        issuer6.setError(null);
        license6.setError(null);
        startdate6.setError(null);
        enddate6.setError(null);
        title7.setError(null);
        issuer7.setError(null);
        license7.setError(null);
        startdate7.setError(null);
        enddate7.setError(null);
        title8.setError(null);
        issuer8.setError(null);
        license8.setError(null);
        startdate8.setError(null);
        enddate8.setError(null);
        title9.setError(null);
        issuer9.setError(null);
        license9.setError(null);
        startdate9.setError(null);
        enddate9.setError(null);
        title10.setError(null);
        issuer10.setError(null);
        license10.setError(null);
        startdate10.setError(null);
        enddate10.setError(null);

        stitle1 = title1.getText().toString();
        sissuer1 = issuer1.getText().toString();
        slicense1 = license1.getText().toString();
        sstartdate1 = startdate1.getText().toString();
        senddate1 = enddate1.getText().toString();
        stitle2 = title2.getText().toString();
        sissuer2 = issuer2.getText().toString();
        slicense2 = license2.getText().toString();
        sstartdate2 = startdate2.getText().toString();
        senddate2 = enddate2.getText().toString();
        stitle3 = title3.getText().toString();
        sissuer3 = issuer3.getText().toString();
        slicense3 = license3.getText().toString();
        sstartdate3 = startdate3.getText().toString();
        senddate3 = enddate3.getText().toString();
        stitle4 = title4.getText().toString();
        sissuer4 = issuer4.getText().toString();
        slicense4 = license4.getText().toString();
        sstartdate4 = startdate4.getText().toString();
        senddate4 = enddate4.getText().toString();
        stitle5 = title5.getText().toString();
        sissuer5 = issuer5.getText().toString();
        slicense5 = license5.getText().toString();
        sstartdate5 = startdate5.getText().toString();
        senddate5 = enddate5.getText().toString();
        stitle6 = title6.getText().toString();
        sissuer6 = issuer6.getText().toString();
        slicense6 = license6.getText().toString();
        sstartdate6 = startdate6.getText().toString();
        senddate6 = enddate6.getText().toString();
        stitle7 = title7.getText().toString();
        sissuer7 = issuer7.getText().toString();
        slicense7 = license7.getText().toString();
        sstartdate7 = startdate7.getText().toString();
        senddate7 = enddate7.getText().toString();
        stitle8 = title8.getText().toString();
        sissuer8 = issuer8.getText().toString();
        slicense8 = license8.getText().toString();
        sstartdate8 = startdate8.getText().toString();
        senddate8 = enddate8.getText().toString();
        stitle9 = title9.getText().toString();
        sissuer9 = issuer9.getText().toString();
        slicense9 = license9.getText().toString();
        sstartdate9 = startdate9.getText().toString();
        senddate9 = enddate9.getText().toString();
        stitle10 = title10.getText().toString();
        sissuer10 = issuer10.getText().toString();
        slicense10 = license10.getText().toString();
        sstartdate10 = startdate10.getText().toString();
        senddate10 = enddate10.getText().toString();


        if (switch1.isChecked())
            willexpire1 = "no";
        if (switch2.isChecked())
            willexpire2 = "no";
        if (switch3.isChecked())
            willexpire3 = "no";
        if (switch4.isChecked())
            willexpire4 = "no";
        if (switch5.isChecked())
            willexpire5 = "no";
        if (switch6.isChecked())
            willexpire6 = "no";
        if (switch7.isChecked())
            willexpire7 = "no";
        if (switch8.isChecked())
            willexpire8 = "no";
        if (switch9.isChecked())
            willexpire9 = "no";
        if (switch10.isChecked())
            willexpire10 = "no";

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag = 0;

        if (certicount == 0) {
            if (stitle1.length() < 3) {
                errorflag = 1;
                title1.setError("Invalid Certificate Name");
            } else {
                errorflag = 0;
                if (sissuer1.length() < 3) {
                    errorflag = 1;
                    issuer1.setError("Invalid Issuer");
                } else {
                    errorflag = 0;
                    if (sstartdate1.length() < 1) {
                        errorflag = 1;
                        startdate1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (willexpire1.equals("yes")) {
                            if (senddate1.length() < 1) {
                                errorflag = 1;
                                enddate1.setError("Invalid Date");
                            }
                        }

                    }


                }
            }
        } else if (certicount == 1) {
            if (stitle1.length() < 3) {
                errorflag = 1;
                title1.setError("Invalid Certificate Name");
            } else {
                errorflag = 0;
                if (sissuer1.length() < 3) {
                    errorflag = 1;
                    issuer1.setError("Invalid Issuer");
                } else {
                    errorflag = 0;
                    if (sstartdate1.length() < 1) {
                        errorflag = 1;
                        startdate1.setError("Invalid Date");
                    } else {
                        if (willexpire1.equals("yes")) {
                            errorflag = 0;
                            if (senddate1.length() < 1) {
                                errorflag = 1;
                                enddate1.setError("Invalid Date");
                            }
                        }


                        {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 1;
                                title2.setError("Invalid Certificate Name");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 1;
                                    issuer2.setError("Invalid Issuer");
                                } else {

                                    errorflag = 0;
                                    if (sstartdate2.length() < 2) {
                                        errorflag = 1;
                                        startdate2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (willexpire2.equals("yes")) {
                                            if (senddate2.length() < 2) {
                                                errorflag = 1;
                                                enddate2.setError("Invalid Date");
                                            }
                                        }

                                    }


                                }
                            }


                        }

                    }
                }
            }
        }
        if (certicount == 2) {
            if (stitle1.length() < 3) {
                errorflag = 1;
                title1.setError("Invalid Certificate Name");
            } else {
                errorflag = 0;
                if (sissuer1.length() < 3) {
                    errorflag = 1;
                    issuer1.setError("Invalid Issuer");
                } else {

                    errorflag = 0;
                    if (sstartdate1.length() < 1) {
                        errorflag = 1;
                        startdate1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (willexpire1.equals("yes")) {
                            if (senddate1.length() < 1) {
                                errorflag = 1;
                                enddate1.setError("Invalid Date");
                            }
                        }

                        {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 1;
                                title2.setError("Invalid Certificate Name");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 1;
                                    issuer2.setError("Invalid Issuer");
                                } else {

                                    errorflag = 0;
                                    if (sstartdate2.length() < 2) {
                                        errorflag = 1;
                                        startdate2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (willexpire2.equals("yes")) {
                                            if (senddate2.length() < 2) {
                                                errorflag = 1;
                                                enddate2.setError("Invalid Date");
                                            }
                                        }


                                        {
                                            errorflag = 0;
                                            if (stitle3.length() < 3) {
                                                errorflag = 1;
                                                title3.setError("Invalid Certificate Name");
                                            } else {
                                                errorflag = 0;
                                                if (sissuer3.length() < 3) {
                                                    errorflag = 1;
                                                    issuer3.setError("Invalid Issuer");
                                                } else {

                                                    errorflag = 0;
                                                    if (sstartdate3.length() < 3) {
                                                        errorflag = 1;
                                                        startdate3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (willexpire3.equals("yes")) {
                                                            if (senddate3.length() < 3) {
                                                                errorflag = 1;
                                                                enddate3.setError("Invalid Date");
                                                            }
                                                        }

                                                    }

                                                }
                                            }
                                        }
                                    }
                                }

                            }

                        }

                    }
                }
            }
        }
        if (certicount == 3) {
            if (stitle1.length() < 3) {
                errorflag = 1;
                title1.setError("Invalid Certificate Name");
            } else {
                errorflag = 0;
                if (sissuer1.length() < 3) {
                    errorflag = 1;
                    issuer1.setError("Invalid Issuer");
                } else {

                    errorflag = 0;
                    if (sstartdate1.length() < 1) {
                        errorflag = 1;
                        startdate1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (willexpire1.equals("yes")) {
                            if (senddate1.length() < 1) {
                                errorflag = 1;
                                enddate1.setError("Invalid Date");
                            }
                        }

                        {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 1;
                                title2.setError("Invalid Certificate Name");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 1;
                                    issuer2.setError("Invalid Issuer");
                                } else {

                                    errorflag = 0;
                                    if (sstartdate2.length() < 2) {
                                        errorflag = 1;
                                        startdate2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (willexpire2.equals("yes")) {
                                            if (senddate2.length() < 2) {
                                                errorflag = 1;
                                                enddate2.setError("Invalid Date");
                                            }
                                        }

                                        {
                                            errorflag = 0;
                                            if (stitle3.length() < 3) {
                                                errorflag = 1;
                                                title3.setError("Invalid Certificate Name");
                                            } else {
                                                errorflag = 0;
                                                if (sissuer3.length() < 3) {
                                                    errorflag = 1;
                                                    issuer3.setError("Invalid Issuer");
                                                } else {
                                                    errorflag = 0;
                                                    if (sstartdate3.length() < 3) {
                                                        errorflag = 1;
                                                        startdate3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (willexpire3.equals("yes")) {
                                                            if (senddate3.length() < 3) {
                                                                errorflag = 1;
                                                                enddate3.setError("Invalid Date");
                                                            }
                                                        }

                                                        {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 3) {
                                                                errorflag = 1;
                                                                title4.setError("Invalid Certificate Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sissuer4.length() < 3) {
                                                                    errorflag = 1;
                                                                    issuer4.setError("Invalid Issuer");
                                                                } else {

                                                                    errorflag = 0;
                                                                    if (sstartdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        startdate4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (willexpire4.equals("yes")) {
                                                                            if (senddate4.length() < 3) {
                                                                                errorflag = 1;
                                                                                enddate4.setError("Invalid Date");
                                                                            }
                                                                        }

                                                                    }

                                                                }

                                                            }
                                                        }
                                                    }

                                                }

                                            }

                                        }
                                    }
                                }

                            }

                        }

                    }
                }
            }
        }
        if (certicount == 4) {

            if (stitle1.length() < 3) {
                errorflag = 1;
                title1.setError("Invalid Certificate Name");
            } else {
                errorflag = 0;
                if (sissuer1.length() < 3) {
                    errorflag = 1;
                    issuer1.setError("Invalid Issuer");
                } else {

                    errorflag = 0;
                    if (sstartdate1.length() < 1) {
                        errorflag = 1;
                        startdate1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (willexpire1.equals("yes")) {
                            if (senddate1.length() < 1) {
                                errorflag = 1;
                                enddate1.setError("Invalid Date");
                            }
                        }

                        {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 1;
                                title2.setError("Invalid Certificate Name");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 1;
                                    issuer2.setError("Invalid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (sstartdate2.length() < 2) {
                                        errorflag = 1;
                                        startdate2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (willexpire2.equals("yes")) {
                                            if (senddate2.length() < 2) {
                                                errorflag = 1;
                                                enddate2.setError("Invalid Date");
                                            }
                                        }

                                        {
                                            errorflag = 0;
                                            if (stitle3.length() < 3) {
                                                errorflag = 1;
                                                title3.setError("Invalid Certificate Name");
                                            } else {
                                                errorflag = 0;
                                                if (sissuer3.length() < 3) {
                                                    errorflag = 1;
                                                    issuer3.setError("Invalid Issuer");
                                                } else {
                                                    errorflag = 0;
                                                    if (sstartdate3.length() < 3) {
                                                        errorflag = 1;
                                                        startdate3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (willexpire3.equals("yes")) {
                                                            if (senddate3.length() < 3) {
                                                                errorflag = 1;
                                                                enddate3.setError("Invalid Date");
                                                            }
                                                        }

                                                        {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 3) {
                                                                errorflag = 1;
                                                                title4.setError("Invalid Certificate Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sissuer4.length() < 3) {
                                                                    errorflag = 1;
                                                                    issuer4.setError("Invalid Issuer");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sstartdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        startdate4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (willexpire4.equals("yes")) {
                                                                            if (senddate4.length() < 3) {
                                                                                errorflag = 1;
                                                                                enddate4.setError("Invalid Date");
                                                                            }
                                                                        }

                                                                        {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 3) {
                                                                                errorflag = 1;
                                                                                title5.setError("Invalid Certificate Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    issuer5.setError("Invalid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sstartdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        startdate5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (willexpire5.equals("yes")) {
                                                                                            if (senddate5.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                enddate5.setError("Invalid Date");
                                                                                            }
                                                                                        }

                                                                                    }


                                                                                }
                                                                            }
                                                                        }

                                                                    }

                                                                }

                                                            }
                                                        }
                                                    }

                                                }

                                            }

                                        }
                                    }
                                }

                            }

                        }

                    }
                }
            }
        }
        if (certicount == 5) {
            if (stitle1.length() < 3) {
                errorflag = 1;
                title1.setError("Invalid Certificate Name");
            } else {
                errorflag = 0;
                if (sissuer1.length() < 3) {
                    errorflag = 1;
                    issuer1.setError("Invalid Issuer");
                } else {

                    errorflag = 0;
                    if (sstartdate1.length() < 1) {
                        errorflag = 1;
                        startdate1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (willexpire1.equals("yes")) {
                            if (senddate1.length() < 1) {
                                errorflag = 1;
                                enddate1.setError("Invalid Date");
                            }
                        }

                        {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 1;
                                title2.setError("Invalid Certificate Name");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 1;
                                    issuer2.setError("Invalid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (sstartdate2.length() < 2) {
                                        errorflag = 1;
                                        startdate2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (willexpire2.equals("yes")) {
                                            if (senddate2.length() < 2) {
                                                errorflag = 1;
                                                enddate2.setError("Invalid Date");
                                            }
                                        }

                                        {
                                            errorflag = 0;
                                            if (stitle3.length() < 3) {
                                                errorflag = 1;
                                                title3.setError("Invalid Certificate Name");
                                            } else {
                                                errorflag = 0;
                                                if (sissuer3.length() < 3) {
                                                    errorflag = 1;
                                                    issuer3.setError("Invalid Issuer");
                                                } else {
                                                    errorflag = 0;
                                                    if (sstartdate3.length() < 3) {
                                                        errorflag = 1;
                                                        startdate3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (willexpire3.equals("yes")) {
                                                            if (senddate3.length() < 3) {
                                                                errorflag = 1;
                                                                enddate3.setError("Invalid Date");
                                                            }
                                                        }

                                                        {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 3) {
                                                                errorflag = 1;
                                                                title4.setError("Invalid Certificate Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sissuer4.length() < 3) {
                                                                    errorflag = 1;
                                                                    issuer4.setError("Invalid Issuer");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sstartdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        startdate4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (willexpire4.equals("yes")) {
                                                                            if (senddate4.length() < 3) {
                                                                                errorflag = 1;
                                                                                enddate4.setError("Invalid Date");
                                                                            }
                                                                        }

                                                                        {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 3) {
                                                                                errorflag = 1;
                                                                                title5.setError("Invalid Certificate Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    issuer5.setError("Invalid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sstartdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        startdate5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (willexpire5.equals("yes")) {
                                                                                            if (senddate5.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                enddate5.setError("Invalid Date");
                                                                                            }
                                                                                        }

                                                                                        {
                                                                                            errorflag = 0;
                                                                                            if (stitle6.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                title6.setError("Invalid Certificate Name");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sissuer6.length() < 3) {
                                                                                                    errorflag = 1;
                                                                                                    issuer6.setError("Invalid Issuer");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sstartdate6.length() < 3) {
                                                                                                        errorflag = 1;
                                                                                                        startdate6.setError("Invalid Date");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (willexpire6.equals("yes")) {
                                                                                                            if (senddate6.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                enddate6.setError("Invalid Date");
                                                                                                            }
                                                                                                        }

                                                                                                    }
                                                                                                }
                                                                                            }

                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        }

                                                                    }

                                                                }

                                                            }
                                                        }
                                                    }

                                                }

                                            }

                                        }
                                    }
                                }

                            }

                        }

                    }
                }
            }
        }
        if (certicount == 6) {
            if (stitle1.length() < 3) {
                errorflag = 1;
                title1.setError("Invalid Certificate Name");
            } else {
                errorflag = 0;
                if (sissuer1.length() < 3) {
                    errorflag = 1;
                    issuer1.setError("Invalid Issuer");
                } else {

                    errorflag = 0;
                    if (sstartdate1.length() < 1) {
                        errorflag = 1;
                        startdate1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (willexpire1.equals("yes")) {
                            if (senddate1.length() < 1) {
                                errorflag = 1;
                                enddate1.setError("Invalid Date");
                            }
                        }

                        {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 1;
                                title2.setError("Invalid Certificate Name");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 1;
                                    issuer2.setError("Invalid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (sstartdate2.length() < 2) {
                                        errorflag = 1;
                                        startdate2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (willexpire2.equals("yes")) {
                                            if (senddate2.length() < 2) {
                                                errorflag = 1;
                                                enddate2.setError("Invalid Date");
                                            }
                                        }

                                        {
                                            errorflag = 0;
                                            if (stitle3.length() < 3) {
                                                errorflag = 1;
                                                title3.setError("Invalid Certificate Name");
                                            } else {
                                                errorflag = 0;
                                                if (sissuer3.length() < 3) {
                                                    errorflag = 1;
                                                    issuer3.setError("Invalid Issuer");
                                                } else {
                                                    errorflag = 0;
                                                    if (sstartdate3.length() < 3) {
                                                        errorflag = 1;
                                                        startdate3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (willexpire3.equals("yes")) {
                                                            if (senddate3.length() < 3) {
                                                                errorflag = 1;
                                                                enddate3.setError("Invalid Date");
                                                            }
                                                        }

                                                        {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 3) {
                                                                errorflag = 1;
                                                                title4.setError("Invalid Certificate Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sissuer4.length() < 3) {
                                                                    errorflag = 1;
                                                                    issuer4.setError("Invalid Issuer");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sstartdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        startdate4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (willexpire4.equals("yes")) {
                                                                            if (senddate4.length() < 3) {
                                                                                errorflag = 1;
                                                                                enddate4.setError("Invalid Date");
                                                                            }
                                                                        }

                                                                        {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 3) {
                                                                                errorflag = 1;
                                                                                title5.setError("Invalid Certificate Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    issuer5.setError("Invalid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sstartdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        startdate5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (willexpire5.equals("yes")) {
                                                                                            if (senddate5.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                enddate5.setError("Invalid Date");
                                                                                            }
                                                                                        }
                                                                                        {
                                                                                            errorflag = 0;
                                                                                            if (stitle6.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                title6.setError("Invalid Certificate Name");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sissuer6.length() < 3) {
                                                                                                    errorflag = 1;
                                                                                                    issuer6.setError("Invalid Issuer");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sstartdate6.length() < 3) {
                                                                                                        errorflag = 1;
                                                                                                        startdate6.setError("Invalid Date");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (willexpire6.equals("yes")) {
                                                                                                            if (senddate6.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                enddate6.setError("Invalid Date");
                                                                                                            }
                                                                                                        }

                                                                                                        {
                                                                                                            errorflag = 0;
                                                                                                            if (stitle7.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                title7.setError("Invalid Certificate Name");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sissuer7.length() < 3) {
                                                                                                                    errorflag = 1;
                                                                                                                    issuer7.setError("Invalid Issuer");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sstartdate7.length() < 3) {
                                                                                                                        errorflag = 1;
                                                                                                                        startdate7.setError("Invalid Date");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (willexpire7.equals("yes")) {
                                                                                                                            if (senddate7.length() < 3) {
                                                                                                                                errorflag = 1;
                                                                                                                                enddate7.setError("Invalid Date");
                                                                                                                            }
                                                                                                                        }

                                                                                                                    }
                                                                                                                }
                                                                                                            }

                                                                                                        }

                                                                                                    }
                                                                                                }
                                                                                            }

                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        }

                                                                    }

                                                                }

                                                            }
                                                        }
                                                    }

                                                }

                                            }

                                        }
                                    }
                                }

                            }

                        }

                    }
                }
            }
        }
        if (certicount == 7) {
            if (stitle1.length() < 3) {
                errorflag = 1;
                title1.setError("Invalid Certificate Name");
            } else {
                errorflag = 0;
                if (sissuer1.length() < 3) {
                    errorflag = 1;
                    issuer1.setError("Invalid Issuer");
                } else {

                    errorflag = 0;
                    if (sstartdate1.length() < 1) {
                        errorflag = 1;
                        startdate1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (willexpire1.equals("yes")) {
                            if (senddate1.length() < 1) {
                                errorflag = 1;
                                enddate1.setError("Invalid Date");
                            }
                        }

                        {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 1;
                                title2.setError("Invalid Certificate Name");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 1;
                                    issuer2.setError("Invalid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (sstartdate2.length() < 2) {
                                        errorflag = 1;
                                        startdate2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (willexpire2.equals("yes")) {
                                            if (senddate2.length() < 2) {
                                                errorflag = 1;
                                                enddate2.setError("Invalid Date");
                                            }
                                        }
                                        {
                                            errorflag = 0;
                                            if (stitle3.length() < 3) {
                                                errorflag = 1;
                                                title3.setError("Invalid Certificate Name");
                                            } else {
                                                errorflag = 0;
                                                if (sissuer3.length() < 3) {
                                                    errorflag = 1;
                                                    issuer3.setError("Invalid Issuer");
                                                } else {
                                                    errorflag = 0;
                                                    if (sstartdate3.length() < 3) {
                                                        errorflag = 1;
                                                        startdate3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (willexpire3.equals("yes")) {
                                                            if (senddate3.length() < 3) {
                                                                errorflag = 1;
                                                                enddate3.setError("Invalid Date");
                                                            }
                                                        }
                                                        {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 3) {
                                                                errorflag = 1;
                                                                title4.setError("Invalid Certificate Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sissuer4.length() < 3) {
                                                                    errorflag = 1;
                                                                    issuer4.setError("Invalid Issuer");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sstartdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        startdate4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (willexpire4.equals("yes")) {
                                                                            if (senddate4.length() < 3) {
                                                                                errorflag = 1;
                                                                                enddate4.setError("Invalid Date");
                                                                            }
                                                                        }
                                                                        {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 3) {
                                                                                errorflag = 1;
                                                                                title5.setError("Invalid Certificate Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    issuer5.setError("Invalid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sstartdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        startdate5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (willexpire5.equals("yes")) {
                                                                                            if (senddate5.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                enddate5.setError("Invalid Date");
                                                                                            }
                                                                                        }
                                                                                        {
                                                                                            errorflag = 0;
                                                                                            if (stitle6.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                title6.setError("Invalid Certificate Name");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sissuer6.length() < 3) {
                                                                                                    errorflag = 1;
                                                                                                    issuer6.setError("Invalid Issuer");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sstartdate6.length() < 3) {
                                                                                                        errorflag = 1;
                                                                                                        startdate6.setError("Invalid Date");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (willexpire6.equals("yes")) {
                                                                                                            if (senddate6.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                enddate6.setError("Invalid Date");
                                                                                                            }
                                                                                                        }
                                                                                                        {
                                                                                                            errorflag = 0;
                                                                                                            if (stitle7.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                title7.setError("Invalid Certificate Name");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sissuer7.length() < 3) {
                                                                                                                    errorflag = 1;
                                                                                                                    issuer7.setError("Invalid Issuer");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sstartdate7.length() < 3) {
                                                                                                                        errorflag = 1;
                                                                                                                        startdate7.setError("Invalid Date");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (willexpire7.equals("yes")) {
                                                                                                                            if (senddate7.length() < 3) {
                                                                                                                                errorflag = 1;
                                                                                                                                enddate7.setError("Invalid Date");
                                                                                                                            }
                                                                                                                        }
                                                                                                                        {
                                                                                                                            errorflag = 0;
                                                                                                                            if (stitle8.length() < 3) {
                                                                                                                                errorflag = 1;
                                                                                                                                title8.setError("Invalid Certificate Name");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sissuer8.length() < 3) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    issuer8.setError("Invalid Issuer");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (sstartdate8.length() < 3) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        startdate8.setError("Invalid Date");
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (willexpire8.equals("yes")) {
                                                                                                                                            if (senddate8.length() < 3) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                enddate8.setError("Invalid Date");
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }

                                                                                                                        }

                                                                                                                    }
                                                                                                                }
                                                                                                            }

                                                                                                        }

                                                                                                    }
                                                                                                }
                                                                                            }

                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        }

                                                                    }

                                                                }

                                                            }
                                                        }
                                                    }

                                                }

                                            }

                                        }
                                    }
                                }

                            }

                        }

                    }
                }
            }
        }
        if (certicount == 8) {
            if (stitle1.length() < 3) {
                errorflag = 1;
                title1.setError("Invalid Certificate Name");
            } else {
                errorflag = 0;
                if (sissuer1.length() < 3) {
                    errorflag = 1;
                    issuer1.setError("Invalid Issuer");
                } else {

                    errorflag = 0;
                    if (sstartdate1.length() < 1) {
                        errorflag = 1;
                        startdate1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (willexpire1.equals("yes")) {
                            if (senddate1.length() < 1) {
                                errorflag = 1;
                                enddate1.setError("Invalid Date");
                            }
                        }
                        {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 1;
                                title2.setError("Invalid Certificate Name");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 1;
                                    issuer2.setError("Invalid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (sstartdate2.length() < 2) {
                                        errorflag = 1;
                                        startdate2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (willexpire2.equals("yes")) {
                                            if (senddate2.length() < 2) {
                                                errorflag = 1;
                                                enddate2.setError("Invalid Date");
                                            }
                                        }
                                        {
                                            errorflag = 0;
                                            if (stitle3.length() < 3) {
                                                errorflag = 1;
                                                title3.setError("Invalid Certificate Name");
                                            } else {
                                                errorflag = 0;
                                                if (sissuer3.length() < 3) {
                                                    errorflag = 1;
                                                    issuer3.setError("Invalid Issuer");
                                                } else {
                                                    errorflag = 0;
                                                    if (sstartdate3.length() < 3) {
                                                        errorflag = 1;
                                                        startdate3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (willexpire3.equals("yes")) {
                                                            if (senddate3.length() < 3) {
                                                                errorflag = 1;
                                                                enddate3.setError("Invalid Date");
                                                            }
                                                        }
                                                        {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 3) {
                                                                errorflag = 1;
                                                                title4.setError("Invalid Certificate Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sissuer4.length() < 3) {
                                                                    errorflag = 1;
                                                                    issuer4.setError("Invalid Issuer");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sstartdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        startdate4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (willexpire4.equals("yes")) {
                                                                            if (senddate4.length() < 3) {
                                                                                errorflag = 1;
                                                                                enddate4.setError("Invalid Date");
                                                                            }
                                                                        }
                                                                        {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 3) {
                                                                                errorflag = 1;
                                                                                title5.setError("Invalid Certificate Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    issuer5.setError("Invalid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sstartdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        startdate5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (willexpire5.equals("yes")) {
                                                                                            if (senddate5.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                enddate5.setError("Invalid Date");
                                                                                            }
                                                                                        }
                                                                                        {
                                                                                            errorflag = 0;
                                                                                            if (stitle6.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                title6.setError("Invalid Certificate Name");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sissuer6.length() < 3) {
                                                                                                    errorflag = 1;
                                                                                                    issuer6.setError("Invalid Issuer");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sstartdate6.length() < 3) {
                                                                                                        errorflag = 1;
                                                                                                        startdate6.setError("Invalid Date");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (willexpire6.equals("yes")) {
                                                                                                            if (senddate6.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                enddate6.setError("Invalid Date");
                                                                                                            }
                                                                                                        }
                                                                                                        {
                                                                                                            errorflag = 0;
                                                                                                            if (stitle7.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                title7.setError("Invalid Certificate Name");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sissuer7.length() < 3) {
                                                                                                                    errorflag = 1;
                                                                                                                    issuer7.setError("Invalid Issuer");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sstartdate7.length() < 3) {
                                                                                                                        errorflag = 1;
                                                                                                                        startdate7.setError("Invalid Date");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (willexpire7.equals("yes")) {
                                                                                                                            if (senddate7.length() < 3) {
                                                                                                                                errorflag = 1;
                                                                                                                                enddate7.setError("Invalid Date");
                                                                                                                            }
                                                                                                                        }
                                                                                                                        {
                                                                                                                            errorflag = 0;
                                                                                                                            if (stitle8.length() < 3) {
                                                                                                                                errorflag = 1;
                                                                                                                                title8.setError("Invalid Certificate Name");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sissuer8.length() < 3) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    issuer8.setError("Invalid Issuer");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (sstartdate8.length() < 3) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        startdate8.setError("Invalid Date");
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (willexpire8.equals("yes")) {
                                                                                                                                            if (senddate8.length() < 3) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                enddate8.setError("Invalid Date");
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        {
                                                                                                                                            errorflag = 0;
                                                                                                                                            if (stitle9.length() < 3) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                title9.setError("Invalid Certificate Name");
                                                                                                                                            } else {
                                                                                                                                                errorflag = 0;
                                                                                                                                                if (sissuer9.length() < 3) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    issuer9.setError("Invalid Issuer");
                                                                                                                                                } else {
                                                                                                                                                    errorflag = 0;
                                                                                                                                                    if (sstartdate9.length() < 3) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        startdate9.setError("Invalid Date");
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (willexpire9.equals("yes")) {
                                                                                                                                                            if (senddate9.length() < 3) {
                                                                                                                                                                errorflag = 1;
                                                                                                                                                                enddate9.setError("Invalid Date");
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }

                                                                                                                                        }

                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }

                                                                                                                        }

                                                                                                                    }
                                                                                                                }
                                                                                                            }

                                                                                                        }

                                                                                                    }
                                                                                                }
                                                                                            }

                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        }

                                                                    }

                                                                }

                                                            }
                                                        }
                                                    }

                                                }

                                            }

                                        }
                                    }
                                }

                            }

                        }

                    }
                }
            }
        }
        if (certicount == 9) {
            if (stitle1.length() < 3) {
                errorflag = 1;
                title1.setError("Invalid Certificate Name");
            } else {
                errorflag = 0;
                if (sissuer1.length() < 3) {
                    errorflag = 1;
                    issuer1.setError("Invalid Issuer");
                } else {

                    errorflag = 0;
                    if (sstartdate1.length() < 1) {
                        errorflag = 1;
                        startdate1.setError("Invalid Date");
                    } else {
                        errorflag = 0;
                        if (willexpire1.equals("yes")) {
                            if (senddate1.length() < 1) {
                                errorflag = 1;
                                enddate1.setError("Invalid Date");
                            }
                        }
                        {
                            errorflag = 0;
                            if (stitle2.length() < 3) {
                                errorflag = 1;
                                title2.setError("Invalid Certificate Name");
                            } else {
                                errorflag = 0;
                                if (sissuer2.length() < 3) {
                                    errorflag = 1;
                                    issuer2.setError("Invalid Issuer");
                                } else {
                                    errorflag = 0;
                                    if (sstartdate2.length() < 2) {
                                        errorflag = 1;
                                        startdate2.setError("Invalid Date");
                                    } else {
                                        errorflag = 0;
                                        if (willexpire2.equals("yes")) {
                                            if (senddate2.length() < 2) {
                                                errorflag = 1;
                                                enddate2.setError("Invalid Date");
                                            }
                                        }
                                        {
                                            errorflag = 0;
                                            if (stitle3.length() < 3) {
                                                errorflag = 1;
                                                title3.setError("Invalid Certificate Name");
                                            } else {
                                                errorflag = 0;
                                                if (sissuer3.length() < 3) {
                                                    errorflag = 1;
                                                    issuer3.setError("Invalid Issuer");
                                                } else {
                                                    errorflag = 0;
                                                    if (sstartdate3.length() < 3) {
                                                        errorflag = 1;
                                                        startdate3.setError("Invalid Date");
                                                    } else {
                                                        errorflag = 0;
                                                        if (willexpire3.equals("yes")) {
                                                            if (senddate3.length() < 3) {
                                                                errorflag = 1;
                                                                enddate3.setError("Invalid Date");
                                                            }
                                                        }
                                                        {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 3) {
                                                                errorflag = 1;
                                                                title4.setError("Invalid Certificate Name");
                                                            } else {
                                                                errorflag = 0;
                                                                if (sissuer4.length() < 3) {
                                                                    errorflag = 1;
                                                                    issuer4.setError("Invalid Issuer");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sstartdate4.length() < 3) {
                                                                        errorflag = 1;
                                                                        startdate4.setError("Invalid Date");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (willexpire4.equals("yes")) {
                                                                            if (senddate4.length() < 3) {
                                                                                errorflag = 1;
                                                                                enddate4.setError("Invalid Date");
                                                                            }
                                                                        }
                                                                        {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 3) {
                                                                                errorflag = 1;
                                                                                title5.setError("Invalid Certificate Name");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (sissuer5.length() < 3) {
                                                                                    errorflag = 1;
                                                                                    issuer5.setError("Invalid Issuer");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sstartdate5.length() < 3) {
                                                                                        errorflag = 1;
                                                                                        startdate5.setError("Invalid Date");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (willexpire5.equals("yes")) {
                                                                                            if (senddate5.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                enddate5.setError("Invalid Date");
                                                                                            }
                                                                                        }
                                                                                        {
                                                                                            errorflag = 0;
                                                                                            if (stitle6.length() < 3) {
                                                                                                errorflag = 1;
                                                                                                title6.setError("Invalid Certificate Name");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (sissuer6.length() < 3) {
                                                                                                    errorflag = 1;
                                                                                                    issuer6.setError("Invalid Issuer");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sstartdate6.length() < 3) {
                                                                                                        errorflag = 1;
                                                                                                        startdate6.setError("Invalid Date");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (willexpire6.equals("yes")) {
                                                                                                            if (senddate6.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                enddate6.setError("Invalid Date");
                                                                                                            }
                                                                                                        }

                                                                                                        {
                                                                                                            errorflag = 0;
                                                                                                            if (stitle7.length() < 3) {
                                                                                                                errorflag = 1;
                                                                                                                title7.setError("Invalid Certificate Name");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (sissuer7.length() < 3) {
                                                                                                                    errorflag = 1;
                                                                                                                    issuer7.setError("Invalid Issuer");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sstartdate7.length() < 3) {
                                                                                                                        errorflag = 1;
                                                                                                                        startdate7.setError("Invalid Date");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (willexpire7.equals("yes")) {
                                                                                                                            if (senddate7.length() < 3) {
                                                                                                                                errorflag = 1;
                                                                                                                                enddate7.setError("Invalid Date");
                                                                                                                            }
                                                                                                                        }
                                                                                                                        {
                                                                                                                            errorflag = 0;
                                                                                                                            if (stitle8.length() < 3) {
                                                                                                                                errorflag = 1;
                                                                                                                                title8.setError("Invalid Certificate Name");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (sissuer8.length() < 3) {
                                                                                                                                    errorflag = 1;
                                                                                                                                    issuer8.setError("Invalid Issuer");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (sstartdate8.length() < 3) {
                                                                                                                                        errorflag = 1;
                                                                                                                                        startdate8.setError("Invalid Date");
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (willexpire8.equals("yes")) {
                                                                                                                                            if (senddate8.length() < 3) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                enddate8.setError("Invalid Date");
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        {
                                                                                                                                            errorflag = 0;
                                                                                                                                            if (stitle9.length() < 3) {
                                                                                                                                                errorflag = 1;
                                                                                                                                                title9.setError("Invalid Certificate Name");
                                                                                                                                            } else {
                                                                                                                                                errorflag = 0;
                                                                                                                                                if (sissuer9.length() < 3) {
                                                                                                                                                    errorflag = 1;
                                                                                                                                                    issuer9.setError("Invalid Issuer");
                                                                                                                                                } else {
                                                                                                                                                    errorflag = 0;
                                                                                                                                                    if (sstartdate9.length() < 3) {
                                                                                                                                                        errorflag = 1;
                                                                                                                                                        startdate9.setError("Invalid Date");
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (willexpire9.equals("yes")) {
                                                                                                                                                            if (senddate9.length() < 3) {
                                                                                                                                                                errorflag = 1;
                                                                                                                                                                enddate9.setError("Invalid Date");
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                        {
                                                                                                                                                            errorflag = 0;
                                                                                                                                                            if (stitle10.length() < 3) {
                                                                                                                                                                errorflag = 1;
                                                                                                                                                                title10.setError("Invalid Certificate Name");
                                                                                                                                                            } else {
                                                                                                                                                                errorflag = 0;
                                                                                                                                                                if (sissuer10.length() < 3) {
                                                                                                                                                                    errorflag = 1;
                                                                                                                                                                    issuer10.setError("Invalid Issuer");
                                                                                                                                                                } else {
                                                                                                                                                                    errorflag = 0;
                                                                                                                                                                    if (sstartdate10.length() < 3) {
                                                                                                                                                                        errorflag = 1;
                                                                                                                                                                        startdate10.setError("Invalid Date");
                                                                                                                                                                    } else {
                                                                                                                                                                        errorflag = 0;
                                                                                                                                                                        if (willexpire10.equals("yes")) {
                                                                                                                                                                            if (senddate10.length() < 3) {
                                                                                                                                                                                errorflag = 1;
                                                                                                                                                                                enddate10.setError("Invalid Date");
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }

                                                                                                                                                        }

                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }

                                                                                                                                        }

                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }

                                                                                                                        }

                                                                                                                    }
                                                                                                                }
                                                                                                            }

                                                                                                        }

                                                                                                    }
                                                                                                }
                                                                                            }

                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        }

                                                                    }

                                                                }

                                                            }
                                                        }
                                                    }

                                                }

                                            }

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
            try {

                Certificates obj1=new Certificates(stitle1,sissuer1,slicense1,sstartdate1,senddate1,willexpire1);
                Certificates obj2=new Certificates(stitle2,sissuer2,slicense2,sstartdate2,senddate2,willexpire2);
                Certificates obj3=new Certificates(stitle3,sissuer3,slicense3,sstartdate3,senddate3,willexpire3);
                Certificates obj4=new Certificates(stitle4,sissuer4,slicense4,sstartdate4,senddate4,willexpire4);
                Certificates obj5=new Certificates(stitle5,sissuer5,slicense5,sstartdate5,senddate5,willexpire5);
                Certificates obj6=new Certificates(stitle6,sissuer6,slicense6,sstartdate6,senddate6,willexpire6);
                Certificates obj7=new Certificates(stitle7,sissuer7,slicense7,sstartdate7,senddate7,willexpire7);
                Certificates obj8=new Certificates(stitle8,sissuer8,slicense8,sstartdate8,senddate8,willexpire8);
                Certificates obj9=new Certificates(stitle9,sissuer9,slicense9,sstartdate9,senddate9,willexpire9);
                Certificates obj10=new Certificates(stitle10,sissuer10,slicense10,sstartdate10,senddate10,willexpire10);


                certificatesList.add(obj1);
                certificatesList.add(obj2);
                certificatesList.add(obj3);
                certificatesList.add(obj4);
                certificatesList.add(obj5);
                certificatesList.add(obj6);
                certificatesList.add(obj7);
                certificatesList.add(obj8);
                certificatesList.add(obj9);
                certificatesList.add(obj10);

                String encObjString=OtoString(certificatesList,MySharedPreferencesManager.getDigest1(MyProfileCertifications.this),MySharedPreferencesManager.getDigest2(MyProfileCertifications.this));

                new SaveCertificates().execute(encObjString);

            } catch (Exception e) {
                Log.d("TAG", "validateandSave:  " + e.getMessage());
            }

        }

    }

    class SaveCertificates extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("d", param[0]));       //0


            json = jParser.makeHttpRequest(MyConstants.url_savecertifications, "GET", params);
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
                Toast.makeText(MyProfileCertifications.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();

                if(role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if(role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

                s.setTitle1(stitle1);
                s.setIssuer1(sissuer1);
                s.setLicense1(slicense1);
                s.setStartdate1certificate(sstartdate1);
                s.setEnddate1certificate(senddate1);
                s.setWillexpire1certificate(willexpire1);
                s.setTitle2(stitle2);
                s.setIssuer2(sissuer2);
                s.setLicense2(slicense2);
                s.setStartdate2certificate(sstartdate2);
                s.setEnddate2certificate(senddate2);
                s.setWillexpire2certificate(willexpire2);
                s.setTitle3(stitle3);
                s.setIssuer3(sissuer3);
                s.setLicense3(slicense3);
                s.setStartdate3certificate(sstartdate3);
                s.setEnddate3certificate(senddate3);
                s.setWillexpire3certificate(willexpire3);
                s.setTitle4(stitle4);
                s.setLicense4(slicense4);
                s.setStartdate4certificate(sstartdate4);
                s.setEnddate4certificate(senddate4);
                s.setWillexpire4certificate(willexpire4);
                s.setTitle5(stitle5);
                s.setIssuer5(sissuer5);
                s.setLicense5(slicense5);
                s.setStartdate5certificate(sstartdate5);
                s.setEnddate5certificate(senddate5);
                s.setWillexpire5certificate(willexpire5);
                s.setTitle6(stitle6);
                s.setIssuer6(sissuer6);
                s.setLicense6(slicense6);
                s.setStartdate6certificate(sstartdate6);
                s.setEnddate6certificate(senddate6);
                s.setWillexpire6certificate(willexpire6);
                s.setTitle7(stitle7);
                s.setIssuer7(sissuer7);
                s.setLicense7(slicense7);
                s.setStartdate7certificate(sstartdate7);
                s.setEnddate7certificate(senddate7);
                s.setWillexpire7certificate(willexpire7);
                s.setTitle8(stitle8);
                s.setIssuer8(sissuer8);
                s.setLicense8(slicense8);
                s.setStartdate8certificate(sstartdate8);
                s.setEnddate8certificate(senddate8);
                s.setWillexpire8certificate(willexpire8);
                s.setTitle9(stitle9);
                s.setIssuer9(sissuer9);
                s.setLicense9(slicense9);
                s.setStartdate9certificate(sstartdate9);
                s.setEnddate9certificate(senddate9);
                s.setWillexpire9certificate(willexpire9);
                s.setTitle10(stitle10);
                s.setIssuer10(sissuer10);
                s.setLicense10(slicense10);
                s.setStartdate10certificate(sstartdate10);
                s.setEnddate10certificate(senddate10);
                s.setWillexpire10certificate(willexpire10);

                MyProfileCertifications.super.onBackPressed();
            } else
                Toast.makeText(MyProfileCertifications.this, result, Toast.LENGTH_SHORT).show();

        }
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

                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    void showDeletDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this Certificate ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag = 1;
                                deleteCertificate();
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

    void deleteCertificate() {
        View v = (View) findViewById(R.id.certiline9);
        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View) findViewById(R.id.certiline9);
            v1.setVisibility(View.GONE);

            RelativeLayout certirl = (RelativeLayout) findViewById(R.id.certirl10);
            certirl.setVisibility(View.GONE);

            certicount--;

            TextView t = (TextView) findViewById(R.id.addmorecertitxt);
            ImageView i = (ImageView) findViewById(R.id.addmorecertiimg);
            addmorecerti.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        } else {
            v = (View) findViewById(R.id.certiline8);
            if (v.getVisibility() == View.VISIBLE) {


                View v1 = (View) findViewById(R.id.certiline8);
                v1.setVisibility(View.GONE);

                RelativeLayout certirl = (RelativeLayout) findViewById(R.id.certirl9);
                certirl.setVisibility(View.GONE);

                certicount--;

            } else {
                v = (View) findViewById(R.id.certiline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View) findViewById(R.id.certiline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout certirl = (RelativeLayout) findViewById(R.id.certirl8);
                    certirl.setVisibility(View.GONE);

                    certicount--;
                } else {
                    v = (View) findViewById(R.id.certiline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View) findViewById(R.id.certiline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout certirl = (RelativeLayout) findViewById(R.id.certirl7);
                        certirl.setVisibility(View.GONE);

                        certicount--;

                    } else {
                        v = (View) findViewById(R.id.certiline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) findViewById(R.id.certiline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout certirl = (RelativeLayout) findViewById(R.id.certirl6);
                            certirl.setVisibility(View.GONE);

                            certicount--;


                        } else {
                            v = (View) findViewById(R.id.certiline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) findViewById(R.id.certiline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout certirl = (RelativeLayout) findViewById(R.id.certirl5);
                                certirl.setVisibility(View.GONE);

                                certicount--;
                            } else {
                                v = (View) findViewById(R.id.certiline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) findViewById(R.id.certiline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout certirl = (RelativeLayout) findViewById(R.id.certirl4);
                                    certirl.setVisibility(View.GONE);

                                    certicount--;

                                } else {
                                    v = (View) findViewById(R.id.certiline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) findViewById(R.id.certiline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout certirl = (RelativeLayout) findViewById(R.id.certirl3);
                                        certirl.setVisibility(View.GONE);

                                        certicount--;

                                    } else {
                                        v = (View) findViewById(R.id.certiline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1 = (View) findViewById(R.id.certiline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout certirl = (RelativeLayout) findViewById(R.id.certirl2);
                                            certirl.setVisibility(View.GONE);

                                            certicount--;


                                        }
//                                        if (certicount == 0) {
                                        else{
                                            title1.setText("");
                                            issuer1.setText("");
                                            license1.setText("");
                                            enddate1.setText("");
                                            startdate1.setText("");
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
            license10.setText("");
            startdate10.setText("");
            enddate10.setText("");

            switch10.setChecked(false);
        } else if (d == 9) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            slicense10 = license10.getText().toString();
            sstartdate10 = startdate10.getText().toString();
            senddate10 = enddate10.getText().toString();
            blnswitch10 = switch10.isChecked();


            stitle9 = stitle10;
            sissuer9 = sissuer10;
            slicense9 = slicense10;
            sstartdate9 = sstartdate10;
            senddate9 = senddate10;

            title10.setText("");
            issuer10.setText("");
            license10.setText("");
            startdate10.setText("");
            enddate10.setText("");
            switch10.setChecked(false);

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            license9.setText(slicense9);
            startdate9.setText(sstartdate9);
            enddate9.setText(senddate9);
            switch9.setChecked(blnswitch10);

        } else if (d == 8) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            slicense10 = license10.getText().toString();
            sstartdate10 = startdate10.getText().toString();
            senddate10 = enddate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            slicense9 = license9.getText().toString();
            sstartdate9 = startdate9.getText().toString();
            senddate9 = enddate9.getText().toString();
            blnswitch9 = switch9.isChecked();


            stitle8 = stitle9;
            sissuer8 = sissuer9;
            slicense8 = slicense9;
            sstartdate8 = sstartdate9;
            senddate8 = senddate9;

            title9.setText("");
            issuer9.setText("");
            license9.setText("");
            startdate9.setText("");
            enddate9.setText("");
            switch9.setChecked(false);

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            license8.setText(slicense8);
            startdate8.setText(sstartdate8);
            enddate8.setText(senddate8);
            switch8.setChecked(blnswitch9);

            stitle9 = stitle10;
            sissuer9 = sissuer10;
            slicense9 = slicense10;
            sstartdate9 = sstartdate10;
            senddate9 = senddate10;

            title10.setText("");
            issuer10.setText("");
            license10.setText("");
            startdate10.setText("");
            enddate10.setText("");
            switch10.setChecked(false);

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            license9.setText(slicense9);
            startdate9.setText(sstartdate9);
            enddate9.setText(senddate9);
            switch9.setChecked(blnswitch10);

        } else if (d == 7) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            slicense10 = license10.getText().toString();
            sstartdate10 = startdate10.getText().toString();
            senddate10 = enddate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            slicense9 = license9.getText().toString();
            sstartdate9 = startdate9.getText().toString();
            senddate9 = enddate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            slicense8 = license8.getText().toString();
            sstartdate8 = startdate8.getText().toString();
            senddate8 = enddate8.getText().toString();
            blnswitch8 = switch8.isChecked();


            stitle7 = stitle8;
            sissuer7 = sissuer8;
            slicense7 = slicense8;
            sstartdate7 = sstartdate8;
            senddate7 = senddate8;

            title8.setText("");
            issuer8.setText("");
            license8.setText("");
            startdate8.setText("");
            enddate8.setText("");
            switch8.setChecked(false);                            // 8 false

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            license7.setText(slicense7);
            startdate7.setText(sstartdate7);
            enddate7.setText(senddate7);
            switch7.setChecked(blnswitch8);                             // 7=8

            stitle8 = stitle9;
            sissuer8 = sissuer9;
            slicense8 = slicense9;
            sstartdate8 = sstartdate9;
            senddate8 = senddate9;

            title9.setText("");
            issuer9.setText("");
            license9.setText("");
            startdate9.setText("");
            enddate9.setText("");// 9 false
            switch9.setChecked(false);

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            license8.setText(slicense8);
            startdate8.setText(sstartdate8);
            enddate8.setText(senddate8);        // 8=9

            stitle9 = stitle10;
            sissuer9 = sissuer10;
            slicense9 = slicense10;
            sstartdate9 = sstartdate10;
            senddate9 = senddate10;

            title10.setText("");
            issuer10.setText("");
            license10.setText("");
            startdate10.setText("");
            enddate10.setText("");              // 10 false
            switch10.setChecked(false);
            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            license9.setText(slicense9);
            startdate9.setText(sstartdate9);
            enddate9.setText(senddate9);// 9=10
            switch9.setChecked(blnswitch10);

        } else if (d == 6) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            slicense10 = license10.getText().toString();
            sstartdate10 = startdate10.getText().toString();
            senddate10 = enddate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            slicense9 = license9.getText().toString();
            sstartdate9 = startdate9.getText().toString();
            senddate9 = enddate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            slicense8 = license8.getText().toString();
            sstartdate8 = startdate8.getText().toString();
            senddate8 = enddate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            slicense7 = license7.getText().toString();
            sstartdate7 = startdate7.getText().toString();
            senddate7 = enddate7.getText().toString();
            blnswitch7 = switch7.isChecked();


            stitle6 = stitle7;
            sissuer6 = sissuer7;
            slicense6 = slicense7;
            sstartdate6 = sstartdate7;
            senddate6 = senddate7;

            title7.setText("");
            issuer7.setText("");
            license7.setText("");
            startdate7.setText("");
            enddate7.setText("");
            switch7.setChecked(false);

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            license6.setText(slicense6);
            startdate6.setText(sstartdate6);
            enddate6.setText(senddate6);
            switch6.setChecked(blnswitch7);

            stitle7 = stitle8;
            sissuer7 = sissuer8;
            slicense7 = slicense8;
            sstartdate7 = sstartdate8;
            senddate7 = senddate8;

            title8.setText("");
            issuer8.setText("");
            license8.setText("");
            startdate8.setText("");
            enddate8.setText("");
            switch8.setChecked(false);

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            license7.setText(slicense7);
            startdate7.setText(sstartdate7);
            enddate7.setText(senddate7);
            switch7.setChecked(blnswitch8);

            stitle8 = stitle9;
            sissuer8 = sissuer9;
            slicense8 = slicense9;
            sstartdate8 = sstartdate9;
            senddate8 = senddate9;

            title9.setText("");
            issuer9.setText("");
            license9.setText("");
            startdate9.setText("");
            enddate9.setText("");
            switch9.setChecked(false);

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            license8.setText(slicense8);
            startdate8.setText(sstartdate8);
            enddate8.setText(senddate8);
            switch8.setChecked(blnswitch9);

            stitle9 = stitle10;
            sissuer9 = sissuer10;
            slicense9 = slicense10;
            sstartdate9 = sstartdate10;
            senddate9 = senddate10;

            title10.setText("");
            issuer10.setText("");
            license10.setText("");
            startdate10.setText("");
            enddate10.setText("");
            switch10.setChecked(false);

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            license9.setText(slicense9);
            startdate9.setText(sstartdate9);
            enddate9.setText(senddate9);
            switch9.setChecked(blnswitch10);

        } else if (d == 5) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            slicense10 = license10.getText().toString();
            sstartdate10 = startdate10.getText().toString();
            senddate10 = enddate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            slicense9 = license9.getText().toString();
            sstartdate9 = startdate9.getText().toString();
            senddate9 = enddate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            slicense8 = license8.getText().toString();
            sstartdate8 = startdate8.getText().toString();
            senddate8 = enddate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            slicense7 = license7.getText().toString();
            sstartdate7 = startdate7.getText().toString();
            senddate7 = enddate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            stitle6 = title6.getText().toString();
            sissuer6 = issuer6.getText().toString();
            slicense6 = license6.getText().toString();
            sstartdate6 = startdate6.getText().toString();
            senddate6 = enddate6.getText().toString();
            blnswitch6 = switch6.isChecked();



            stitle5 = stitle6;
            sissuer5 = sissuer6;
            slicense5 = slicense6;
            sstartdate5 = sstartdate6;
            senddate5 = senddate6;

            title6.setText("");
            issuer6.setText("");
            license6.setText("");
            startdate6.setText("");
            enddate6.setText("");
            switch6.setChecked(false);

            title5.setText(stitle5);
            issuer5.setText(sissuer5);
            license5.setText(slicense5);
            startdate5.setText(sstartdate5);
            enddate5.setText(senddate5);
            switch5.setChecked(blnswitch6);

            stitle6 = stitle7;
            sissuer6 = sissuer7;
            slicense6 = slicense7;
            sstartdate6 = sstartdate7;
            senddate6 = senddate7;

            title7.setText("");
            issuer7.setText("");
            license7.setText("");
            startdate7.setText("");
            enddate7.setText("");
            switch7.setChecked(false);

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            license6.setText(slicense6);
            startdate6.setText(sstartdate6);
            enddate6.setText(senddate6);
            switch6.setChecked(blnswitch7);

            stitle7 = stitle8;
            sissuer7 = sissuer8;
            slicense7 = slicense8;
            sstartdate7 = sstartdate8;
            senddate7 = senddate8;

            title8.setText("");
            issuer8.setText("");
            license8.setText("");
            startdate8.setText("");
            enddate8.setText("");
            switch8.setChecked(false);

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            license7.setText(slicense7);
            startdate7.setText(sstartdate7);
            enddate7.setText(senddate7);
            switch7.setChecked(blnswitch8);

            stitle8 = stitle9;
            sissuer8 = sissuer9;
            slicense8 = slicense9;
            sstartdate8 = sstartdate9;
            senddate8 = senddate9;

            title9.setText("");
            issuer9.setText("");
            license9.setText("");
            startdate9.setText("");
            enddate9.setText("");
            switch9.setChecked(false);

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            license8.setText(slicense8);
            startdate8.setText(sstartdate8);
            enddate8.setText(senddate8);
            switch8.setChecked(blnswitch9);

            stitle9 = stitle10;
            sissuer9 = sissuer10;
            slicense9 = slicense10;
            sstartdate9 = sstartdate10;
            senddate9 = senddate10;


            title10.setText("");
            issuer10.setText("");
            license10.setText("");
            startdate10.setText("");
            enddate10.setText("");
            switch10.setChecked(false);

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            license9.setText(slicense9);
            startdate9.setText(sstartdate9);
            enddate9.setText(senddate9);
            switch9.setChecked(blnswitch10);

        } else if (d == 4) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            slicense10 = license10.getText().toString();
            sstartdate10 = startdate10.getText().toString();
            senddate10 = enddate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            slicense9 = license9.getText().toString();
            sstartdate9 = startdate9.getText().toString();
            senddate9 = enddate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            slicense8 = license8.getText().toString();
            sstartdate8 = startdate8.getText().toString();
            senddate8 = enddate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            slicense7 = license7.getText().toString();
            sstartdate7 = startdate7.getText().toString();
            senddate7 = enddate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            stitle6 = title6.getText().toString();
            sissuer6 = issuer6.getText().toString();
            slicense6 = license6.getText().toString();
            sstartdate6 = startdate6.getText().toString();
            senddate6 = enddate6.getText().toString();
            blnswitch6 = switch6.isChecked();

            stitle5 = title5.getText().toString();
            sissuer5 = issuer5.getText().toString();
            slicense5 = license5.getText().toString();
            sstartdate5 = startdate5.getText().toString();
            senddate5 = enddate5.getText().toString();
            blnswitch5 = switch5.isChecked();


            stitle4 = stitle5;
            sissuer4 = sissuer5;
            slicense4 = slicense5;
            sstartdate4 = sstartdate5;
            senddate4 = senddate5;

            title5.setText("");
            issuer5.setText("");
            license5.setText("");
            startdate5.setText("");
            enddate5.setText("");
            switch5.setChecked(false);

            title4.setText(stitle4);
            issuer4.setText(sissuer4);
            license4.setText(slicense4);
            startdate4.setText(sstartdate4);
            enddate4.setText(senddate4);
            switch4.setChecked(blnswitch5);

            stitle5 = stitle6;
            sissuer5 = sissuer6;
            slicense5 = slicense6;
            sstartdate5 = sstartdate6;
            senddate5 = senddate6;

            title6.setText("");
            issuer6.setText("");
            license6.setText("");
            startdate6.setText("");
            enddate6.setText("");
            switch6.setChecked(false);

            title5.setText(stitle5);
            issuer5.setText(sissuer5);
            license5.setText(slicense5);
            startdate5.setText(sstartdate5);
            enddate5.setText(senddate5);
            switch5.setChecked(blnswitch6);

            stitle6 = stitle7;
            sissuer6 = sissuer7;
            slicense6 = slicense7;
            sstartdate6 = sstartdate7;
            senddate6 = senddate7;

            title7.setText("");
            issuer7.setText("");
            license7.setText("");
            startdate7.setText("");
            enddate7.setText("");
            switch7.setChecked(false);

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            license6.setText(slicense6);
            startdate6.setText(sstartdate6);
            enddate6.setText(senddate6);
            switch6.setChecked(blnswitch6);


            stitle7 = stitle8;
            sissuer7 = sissuer8;
            slicense7 = slicense8;
            sstartdate7 = sstartdate8;
            senddate7 = senddate8;

            title8.setText("");
            issuer8.setText("");
            license8.setText("");
            startdate8.setText("");
            enddate8.setText("");
            switch8.setChecked(blnswitch7);

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            license7.setText(slicense7);
            startdate7.setText(sstartdate7);
            enddate7.setText(senddate7);

            stitle8 = stitle9;
            sissuer8 = sissuer9;
            slicense8 = slicense9;
            sstartdate8 = sstartdate9;
            senddate8 = senddate9;

            title9.setText("");
            issuer9.setText("");
            license9.setText("");
            startdate9.setText("");
            enddate9.setText("");


            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            license8.setText(slicense8);
            startdate8.setText(sstartdate8);
            enddate8.setText(senddate8);
            switch8.setChecked(blnswitch9);

            stitle9 = stitle10;
            sissuer9 = sissuer10;
            slicense9 = slicense10;
            sstartdate9 = sstartdate10;
            senddate9 = senddate10;


            title10.setText("");
            issuer10.setText("");
            license10.setText("");
            startdate10.setText("");
            enddate10.setText("");
            switch10.setChecked(false);

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            license9.setText(slicense9);
            startdate9.setText(sstartdate9);
            enddate9.setText(senddate9);
            switch9.setChecked(blnswitch10);

        } else if (d == 3) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            slicense10 = license10.getText().toString();
            sstartdate10 = startdate10.getText().toString();
            senddate10 = enddate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            slicense9 = license9.getText().toString();
            sstartdate9 = startdate9.getText().toString();
            senddate9 = enddate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            slicense8 = license8.getText().toString();
            sstartdate8 = startdate8.getText().toString();
            senddate8 = enddate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            slicense7 = license7.getText().toString();
            sstartdate7 = startdate7.getText().toString();
            senddate7 = enddate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            stitle6 = title6.getText().toString();
            sissuer6 = issuer6.getText().toString();
            slicense6 = license6.getText().toString();
            sstartdate6 = startdate6.getText().toString();
            senddate6 = enddate6.getText().toString();
            blnswitch6 = switch6.isChecked();

            stitle5 = title5.getText().toString();
            sissuer5 = issuer5.getText().toString();
            slicense5 = license5.getText().toString();
            sstartdate5 = startdate5.getText().toString();
            senddate5 = enddate5.getText().toString();
            blnswitch5 = switch5.isChecked();

            stitle4 = title4.getText().toString();
            sissuer4 = issuer4.getText().toString();
            slicense4 = license4.getText().toString();
            sstartdate4 = startdate4.getText().toString();
            senddate4 = enddate4.getText().toString();
            blnswitch4 = switch4.isChecked();

            stitle3 = stitle4;
            sissuer3 = sissuer4;
            slicense3 = slicense4;
            sstartdate3 = sstartdate4;
            senddate3 = senddate4;

            title4.setText("");
            issuer4.setText("");
            license4.setText("");
            startdate4.setText("");
            enddate4.setText("");
            switch4.setChecked(false);

            title3.setText(stitle3);
            issuer3.setText(sissuer3);
            license3.setText(slicense3);
            startdate3.setText(sstartdate3);
            enddate3.setText(senddate3);
            switch3.setChecked(blnswitch4);

            stitle4 = stitle5;
            sissuer4 = sissuer5;
            slicense4 = slicense5;
            sstartdate4 = sstartdate5;
            senddate4 = senddate5;

            title5.setText("");
            issuer5.setText("");
            license5.setText("");
            startdate5.setText("");
            enddate5.setText("");
            switch5.setChecked(false);

            title4.setText(stitle4);
            issuer4.setText(sissuer4);
            license4.setText(slicense4);
            startdate4.setText(sstartdate4);
            enddate4.setText(senddate4);
            switch4.setChecked(blnswitch5);


            stitle5 = stitle6;
            sissuer5 = sissuer6;
            slicense5 = slicense6;
            sstartdate5 = sstartdate6;
            senddate5 = senddate6;

            title6.setText("");
            issuer6.setText("");
            license6.setText("");
            startdate6.setText("");
            enddate6.setText("");
            switch6.setChecked(false);

            title5.setText(stitle5);
            issuer5.setText(sissuer5);
            license5.setText(slicense5);
            startdate5.setText(sstartdate5);
            enddate5.setText(senddate5);
            switch5.setChecked(blnswitch6);

            stitle6 = stitle7;
            sissuer6 = sissuer7;
            slicense6 = slicense7;
            sstartdate6 = sstartdate7;
            senddate6 = senddate7;

            title7.setText("");
            issuer7.setText("");
            license7.setText("");
            startdate7.setText("");
            enddate7.setText("");
            switch7.setChecked(false);

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            license6.setText(slicense6);
            startdate6.setText(sstartdate6);
            enddate6.setText(senddate6);
            switch6.setChecked(blnswitch7);

            stitle7 = stitle8;
            sissuer7 = sissuer8;
            slicense7 = slicense8;
            sstartdate7 = sstartdate8;
            senddate7 = senddate8;

            title8.setText("");
            issuer8.setText("");
            license8.setText("");
            startdate8.setText("");
            enddate8.setText("");
            switch8.setChecked(false);

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            license7.setText(slicense7);
            startdate7.setText(sstartdate7);
            enddate7.setText(senddate7);
            switch7.setChecked(blnswitch8);

            stitle8 = stitle9;
            sissuer8 = sissuer9;
            slicense8 = slicense9;
            sstartdate8 = sstartdate9;
            senddate8 = senddate9;

            title9.setText("");
            issuer9.setText("");
            license9.setText("");
            startdate9.setText("");
            enddate9.setText("");
            switch9.setChecked(false);

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            license8.setText(slicense8);
            startdate8.setText(sstartdate8);
            enddate8.setText(senddate8);
            switch8.setChecked(blnswitch9);

            stitle9 = stitle10;
            sissuer9 = sissuer10;
            slicense9 = slicense10;
            sstartdate9 = sstartdate10;
            senddate9 = senddate10;


            title10.setText("");
            issuer10.setText("");
            license10.setText("");
            startdate10.setText("");
            enddate10.setText("");
            switch10.setChecked(false);

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            license9.setText(slicense9);
            startdate9.setText(sstartdate9);
            enddate9.setText(senddate9);
            switch9.setChecked(blnswitch10);

        }
        else if (d == 2) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            slicense10 = license10.getText().toString();
            sstartdate10 = startdate10.getText().toString();
            senddate10 = enddate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            slicense9 = license9.getText().toString();
            sstartdate9 = startdate9.getText().toString();
            senddate9 = enddate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            slicense8 = license8.getText().toString();
            sstartdate8 = startdate8.getText().toString();
            senddate8 = enddate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            slicense7 = license7.getText().toString();
            sstartdate7 = startdate7.getText().toString();
            senddate7 = enddate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            stitle6 = title6.getText().toString();
            sissuer6 = issuer6.getText().toString();
            slicense6 = license6.getText().toString();
            sstartdate6 = startdate6.getText().toString();
            senddate6 = enddate6.getText().toString();
            blnswitch6 = switch6.isChecked();

            stitle5 = title5.getText().toString();
            sissuer5 = issuer5.getText().toString();
            slicense5 = license5.getText().toString();
            sstartdate5 = startdate5.getText().toString();
            senddate5 = enddate5.getText().toString();
            blnswitch5 = switch5.isChecked();

            stitle4 = title4.getText().toString();
            sissuer4 = issuer4.getText().toString();
            slicense4 = license4.getText().toString();
            sstartdate4 = startdate4.getText().toString();
            senddate4 = enddate4.getText().toString();
            blnswitch4 = switch4.isChecked();

            stitle3 = title3.getText().toString();
            sissuer3 = issuer3.getText().toString();
            slicense3 = license3.getText().toString();
            sstartdate3 = startdate3.getText().toString();
            senddate3 = enddate3.getText().toString();
            blnswitch3 = switch3.isChecked();


            stitle2 = stitle3;
            sissuer2 = sissuer3;
            slicense2 = slicense3;
            sstartdate2 = sstartdate3;
            senddate2 = senddate3;

            title3.setText("");
            issuer3.setText("");
            license3.setText("");
            startdate3.setText("");
            enddate3.setText("");
            switch3.setChecked(false);

            title2.setText(stitle2);
            issuer2.setText(sissuer2);
            license2.setText(slicense2);
            startdate2.setText(sstartdate2);
            enddate2.setText(senddate2);
            switch2.setChecked(blnswitch3);

            stitle3 = stitle4;
            sissuer3 = sissuer4;
            slicense3 = slicense4;
            sstartdate3 = sstartdate4;
            senddate3 = senddate4;

            title4.setText("");
            issuer4.setText("");
            license4.setText("");
            startdate4.setText("");
            enddate4.setText("");
            switch4.setChecked(false);

            title3.setText(stitle3);
            issuer3.setText(sissuer3);
            license3.setText(slicense3);
            startdate3.setText(sstartdate3);
            enddate3.setText(senddate3);
            switch3.setChecked(blnswitch4);

            stitle4 = stitle5;
            sissuer4 = sissuer5;
            slicense4 = slicense5;
            sstartdate4 = sstartdate5;
            senddate4 = senddate5;

            title5.setText("");
            issuer5.setText("");
            license5.setText("");
            startdate5.setText("");
            enddate5.setText("");
            switch5.setChecked(false);

            title4.setText(stitle4);
            issuer4.setText(sissuer4);
            license4.setText(slicense4);
            startdate4.setText(sstartdate4);
            enddate4.setText(senddate4);
            switch4.setChecked(blnswitch5);

            stitle5 = stitle6;
            sissuer5 = sissuer6;
            slicense5 = slicense6;
            sstartdate5 = sstartdate6;
            senddate5 = senddate6;

            title6.setText("");
            issuer6.setText("");
            license6.setText("");
            startdate6.setText("");
            enddate6.setText("");
            switch6.setChecked(false);

            title5.setText(stitle5);
            issuer5.setText(sissuer5);
            license5.setText(slicense5);
            startdate5.setText(sstartdate5);
            enddate5.setText(senddate5);
            switch5.setChecked(blnswitch6);

            stitle6 = stitle7;
            sissuer6 = sissuer7;
            slicense6 = slicense7;
            sstartdate6 = sstartdate7;
            senddate6 = senddate7;

            title7.setText("");
            issuer7.setText("");
            license7.setText("");
            startdate7.setText("");
            enddate7.setText("");
            switch7.setChecked(false);

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            license6.setText(slicense6);
            startdate6.setText(sstartdate6);
            enddate6.setText(senddate6);
            switch6.setChecked(blnswitch7);

            stitle7 = stitle8;
            sissuer7 = sissuer8;
            slicense7 = slicense8;
            sstartdate7 = sstartdate8;
            senddate7 = senddate8;

            title8.setText("");
            issuer8.setText("");
            license8.setText("");
            startdate8.setText("");
            enddate8.setText("");
            switch8.setChecked(blnswitch8);

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            license7.setText(slicense7);
            startdate7.setText(sstartdate7);
            enddate7.setText(senddate7);
            switch7.setChecked(blnswitch8);

            stitle8 = stitle9;
            sissuer8 = sissuer9;
            slicense8 = slicense9;
            sstartdate8 = sstartdate9;
            senddate8 = senddate9;

            title9.setText("");
            issuer9.setText("");
            license9.setText("");
            startdate9.setText("");
            enddate9.setText("");
            switch9.setChecked(false);

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            license8.setText(slicense8);
            startdate8.setText(sstartdate8);
            enddate8.setText(senddate8);
            switch8.setChecked(blnswitch9);

            stitle9 = stitle10;
            sissuer9 = sissuer10;
            slicense9 = slicense10;
            sstartdate9 = sstartdate10;
            senddate9 = senddate10;


            title10.setText("");
            issuer10.setText("");
            license10.setText("");
            startdate10.setText("");
            enddate10.setText("");
            switch10.setChecked(false);

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            license9.setText(slicense9);
            startdate9.setText(sstartdate9);
            enddate9.setText(senddate9);
            switch9.setChecked(blnswitch10);
        }
        else if (d == 1) {
            stitle10 = title10.getText().toString();
            sissuer10 = issuer10.getText().toString();
            slicense10 = license10.getText().toString();
            sstartdate10 = startdate10.getText().toString();
            senddate10 = enddate10.getText().toString();
            blnswitch10 = switch10.isChecked();

            stitle9 = title9.getText().toString();
            sissuer9 = issuer9.getText().toString();
            slicense9 = license9.getText().toString();
            sstartdate9 = startdate9.getText().toString();
            senddate9 = enddate9.getText().toString();
            blnswitch9 = switch9.isChecked();

            stitle8 = title8.getText().toString();
            sissuer8 = issuer8.getText().toString();
            slicense8 = license8.getText().toString();
            sstartdate8 = startdate8.getText().toString();
            senddate8 = enddate8.getText().toString();
            blnswitch8 = switch8.isChecked();

            stitle7 = title7.getText().toString();
            sissuer7 = issuer7.getText().toString();
            slicense7 = license7.getText().toString();
            sstartdate7 = startdate7.getText().toString();
            senddate7 = enddate7.getText().toString();
            blnswitch7 = switch7.isChecked();

            stitle6 = title6.getText().toString();
            sissuer6 = issuer6.getText().toString();
            slicense6 = license6.getText().toString();
            sstartdate6 = startdate6.getText().toString();
            senddate6 = enddate6.getText().toString();
            blnswitch6 = switch6.isChecked();

            stitle5 = title5.getText().toString();
            sissuer5 = issuer5.getText().toString();
            slicense5 = license5.getText().toString();
            sstartdate5 = startdate5.getText().toString();
            senddate5 = enddate5.getText().toString();
            blnswitch5 = switch5.isChecked();

            stitle4 = title4.getText().toString();
            sissuer4 = issuer4.getText().toString();
            slicense4 = license4.getText().toString();
            sstartdate4 = startdate4.getText().toString();
            senddate4 = enddate4.getText().toString();
            blnswitch4 = switch4.isChecked();

            stitle3 = title3.getText().toString();
            sissuer3 = issuer3.getText().toString();
            slicense3 = license3.getText().toString();
            sstartdate3 = startdate3.getText().toString();
            senddate3 = enddate3.getText().toString();
            blnswitch3 = switch3.isChecked();

            stitle2 = title2.getText().toString();
            sissuer2 = issuer2.getText().toString();
            slicense2 = license2.getText().toString();
            sstartdate2 = startdate2.getText().toString();
            senddate2 = enddate2.getText().toString();
            blnswitch2 = switch2.isChecked();


            stitle1 = stitle2;
            sissuer1 = sissuer2;
            slicense1 = slicense2;
            sstartdate1 = sstartdate2;
            senddate1 = senddate2;

            title2.setText("");
            issuer2.setText("");
            license2.setText("");
            startdate2.setText("");
            enddate2.setText("");
            switch2.setChecked(false);

            title1.setText(stitle1);
            issuer1.setText(sissuer1);
            license1.setText(slicense1);
            startdate1.setText(sstartdate1);
            enddate1.setText(senddate1);
            switch2.setChecked(blnswitch1);

            stitle2 = stitle3;
            sissuer2 = sissuer3;
            slicense2 = slicense3;
            sstartdate2 = sstartdate3;
            senddate2 = senddate3;

            title3.setText("");
            issuer3.setText("");
            license3.setText("");
            startdate3.setText("");
            enddate3.setText("");
            switch3.setChecked(false);

            title2.setText(stitle2);
            issuer2.setText(sissuer2);
            license2.setText(slicense2);
            startdate2.setText(sstartdate2);
            enddate2.setText(senddate2);
            switch2.setChecked(blnswitch3);

            stitle3 = stitle4;
            sissuer3 = sissuer4;
            slicense3 = slicense4;
            sstartdate3 = sstartdate4;
            senddate3 = senddate4;

            title4.setText("");
            issuer4.setText("");
            license4.setText("");
            startdate4.setText("");
            enddate4.setText("");
            switch4.setChecked(false);

            title3.setText(stitle3);
            issuer3.setText(sissuer3);
            license3.setText(slicense3);
            startdate3.setText(sstartdate3);
            enddate3.setText(senddate3);
            switch3.setChecked(blnswitch4);

            stitle4 = stitle5;
            sissuer4 = sissuer5;
            slicense4 = slicense5;
            sstartdate4 = sstartdate5;
            senddate4 = senddate5;

            title5.setText("");
            issuer5.setText("");
            license5.setText("");
            startdate5.setText("");
            enddate5.setText("");
            switch5.setChecked(false);

            title4.setText(stitle4);
            issuer4.setText(sissuer4);
            license4.setText(slicense4);
            startdate4.setText(sstartdate4);
            enddate4.setText(senddate4);
            switch4.setChecked(blnswitch5);

            stitle5 = stitle6;
            sissuer5 = sissuer6;
            slicense5 = slicense6;
            sstartdate5 = sstartdate6;
            senddate5 = senddate6;

            title6.setText("");
            issuer6.setText("");
            license6.setText("");
            startdate6.setText("");
            enddate6.setText("");
            switch6.setChecked(false);

            title5.setText(stitle5);
            issuer5.setText(sissuer5);
            license5.setText(slicense5);
            startdate5.setText(sstartdate5);
            enddate5.setText(senddate5);
            switch5.setChecked(blnswitch6);

            stitle6 = stitle7;
            sissuer6 = sissuer7;
            slicense6 = slicense7;
            sstartdate6 = sstartdate7;
            senddate6 = senddate7;

            title7.setText("");
            issuer7.setText("");
            license7.setText("");
            startdate7.setText("");
            enddate7.setText("");
            switch7.setChecked(false);

            title6.setText(stitle6);
            issuer6.setText(sissuer6);
            license6.setText(slicense6);
            startdate6.setText(sstartdate6);
            enddate6.setText(senddate6);
            switch6.setChecked(blnswitch7);

            stitle7 = stitle8;
            sissuer7 = sissuer8;
            slicense7 = slicense8;
            sstartdate7 = sstartdate8;
            senddate7 = senddate8;

            title8.setText("");
            issuer8.setText("");
            license8.setText("");
            startdate8.setText("");
            enddate8.setText("");
            switch8.setChecked(blnswitch8);

            title7.setText(stitle7);
            issuer7.setText(sissuer7);
            license7.setText(slicense7);
            startdate7.setText(sstartdate7);
            enddate7.setText(senddate7);
            switch7.setChecked(blnswitch8);

            stitle8 = stitle9;
            sissuer8 = sissuer9;
            slicense8 = slicense9;
            sstartdate8 = sstartdate9;
            senddate8 = senddate9;

            title9.setText("");
            issuer9.setText("");
            license9.setText("");
            startdate9.setText("");
            enddate9.setText("");
            switch9.setChecked(false);

            title8.setText(stitle8);
            issuer8.setText(sissuer8);
            license8.setText(slicense8);
            startdate8.setText(sstartdate8);
            enddate8.setText(senddate8);
            switch8.setChecked(blnswitch9);

            stitle9 = stitle10;
            sissuer9 = sissuer10;
            slicense9 = slicense10;
            sstartdate9 = sstartdate10;
            senddate9 = senddate10;


            title10.setText("");
            issuer10.setText("");
            license10.setText("");
            startdate10.setText("");
            enddate10.setText("");
            switch10.setChecked(false);

            title9.setText(stitle9);
            issuer9.setText(sissuer9);
            license9.setText(slicense9);
            startdate9.setText(sstartdate9);
            enddate9.setText(senddate9);
            switch9.setChecked(blnswitch10);

        }


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
                                    MyProfileCertifications.super.onBackPressed();
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
        } else
            MyProfileCertifications.super.onBackPressed();
    }


//    void showDateDialog(final EditText id)
//    {
//
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfileCertifications.this);
//        LayoutInflater inflater = MyProfileCertifications.this.getLayoutInflater();
//        View dialog = inflater.inflate(R.layout.monthyeardialog,null);
//        dialogBuilder.setView(dialog);
//
//
//
//        final WheelView monthView,yearView;
//
//        final List<String> monthList= new ArrayList<String>();
//        final List<String> yearList= new ArrayList<String>();
//
//        monthView= (WheelView)dialog.findViewById(R.id.monthwheel);
//        yearView= (WheelView)dialog.findViewById(R.id.yearwheel);
//
//        monthList.add("Jan");
//        monthList.add("Feb");
//        monthList.add("Mar");
//        monthList.add("Apr");
//        monthList.add("May");
//        monthList.add("Jun");
//        monthList.add("Jul");
//        monthList.add("Aug");
//        monthList.add("Sep");
//        monthList.add("Oct");
//        monthList.add("Nov");
//        monthList.add("Dec");
//
//        Calendar currentCal=Calendar.getInstance();
//        for(int i=1975;i<=currentCal.get(Calendar.YEAR);i++)
//            yearList.add(""+i);
//
//
//        monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfileCertifications.this));
//        monthView.setWheelData(monthList);
//        yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfileCertifications.this));
//        yearView.setWheelData(yearList);
//
//
//
//        View setselectionview=(View)dialog.findViewById(R.id.setselectionview);
//        View cancelselectionview=(View)dialog.findViewById(R.id.cancelselectionview);
//
//        final AlertDialog alertDialog = dialogBuilder.create();
//
//
//
//        setselectionview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int monthPosition=monthView.getCurrentPosition();
//                int yearPosition=yearView.getCurrentPosition();
//
//                String selectedMonth=monthList.get(monthPosition);
//                String selectedYear=yearList.get(yearPosition);
//
//                setMonthYear(id,selectedMonth,selectedYear);
//
//                alertDialog.cancel();
//            }
//        });
//
//        cancelselectionview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                alertDialog.cancel();
//            }
//        });
//
//        alertDialog.show();
//
//        int w= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());
//        int h= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 215, getResources().getDisplayMetrics());
//        alertDialog.getWindow().setLayout(w, h);
//
//    }
//    void setMonthYear(EditText id,String selectedMonth,String selectedYear)
//    {
//        id.setText(selectedMonth+", "+selectedYear);
//    }


    void showDateDialog(final EditText id, boolean isFromDateSelected, final int fromYear, final String fromMonth, final String todate) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfileCertifications.this);
        LayoutInflater inflater = MyProfileCertifications.this.getLayoutInflater();
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

        if (isFromDateSelected == true && fromYear != 0 && !fromMonth.equals("")) {        // custum year list if from date is already set
            Calendar calendar = Calendar.getInstance();
            yearList.clear();
            for (int i = fromYear; i <= calendar.get(Calendar.YEAR); i++) {
                yearList.add(i + "");
            }


        } else if (!todate.equals("")) {       // custum year list if todate is already set
            String[] splited = todate.split(", ");
            int toYear = 0;
            if (splited.length == 2) {
                toYear = Integer.parseInt(splited[1]);
                yearList.clear();
                for (int i = 1970; i <= toYear; i++) {
                    yearList.add(i + "");
                }
            }


        } else {                              // normal year list 1970 to current year
//            yearList.clear();
//            yearList.add("2000");
//            yearList.add("2001");
//            yearList.add("2002");
//            yearList.add("2003");
//            yearList.add("2004");
//            yearList.add("2005");
//            yearList.add("2006");
//            yearList.add("2007");
//            yearList.add("2008");
//            yearList.add("2009");
//            yearList.add("2010");
//            yearList.add("2011");
//            yearList.add("2012");
//            yearList.add("2013");
//            yearList.add("2014");
//            yearList.add("2015");
//            yearList.add("2016");

            Calendar calendar = Calendar.getInstance();
            yearList.clear();
            for (int i = 1970; i <= calendar.get(Calendar.YEAR); i++) {
                yearList.add(i + "");
            }


        }

        monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfileCertifications.this));
        monthView.setWheelData(monthList);
        yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfileCertifications.this));
        yearView.setWheelData(yearList);


        TextView set = (TextView) dialog.findViewById(R.id.set);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);

        final AlertDialog alertDialog = dialogBuilder.create();


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int monthPosition = monthView.getCurrentPosition();
                int yearPosition = yearView.getCurrentPosition();
                boolean isvalid = true;

                String selectedMonth = monthList.get(monthPosition);
                String selectedYear = yearList.get(yearPosition);
                Calendar cal = Calendar.getInstance();
                int current_month_pos = cal.get(Calendar.MONTH);
                int current_year = cal.get(Calendar.YEAR);

                if (!fromMonth.equals("")) {        //if first from date set
                    int fromMonthInt = monthList.indexOf(fromMonth);
                    if (selectedYear.equals(String.valueOf(fromYear))) {

                        if (fromMonthInt >= monthPosition) {
                            isvalid = false;
                        }
                    }
                }

                if (!todate.equals("")) {       // if first todate set
                    int toYear = 0;
                    int toMonthInt = 0;
                    String toMonth = "";

                    String[] splited = todate.split(", ");
                    if (splited.length == 2) {
                        toMonth = splited[0];
                        toYear = Integer.parseInt(splited[1]);

                        if (toYear < Integer.parseInt(selectedYear)) {
                            isvalid = false;
                        }
                        if (toYear == Integer.parseInt(selectedYear)) {
                            toMonthInt = monthList.indexOf(toMonth);
                            if (monthPosition >= toMonthInt) {
                                isvalid = false;
                            }
                        }
                    }

                }
                // invalid future date
                if (current_month_pos < monthPosition && current_year == Integer.parseInt(selectedYear)) {
                    isvalid = false;
                }

                setMonthYear(id, selectedMonth, selectedYear, isvalid);
                alertDialog.cancel();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
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

    void setMonthYear(EditText id, String selectedMonth, String selectedYear, boolean isValid) {
        id.setError(null);
        if (isValid == true) {
            id.setText(selectedMonth + ", " + selectedYear);
        } else {
            id.setError("Choose valid date");
            Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
            id.setText("");
        }
    }

}
