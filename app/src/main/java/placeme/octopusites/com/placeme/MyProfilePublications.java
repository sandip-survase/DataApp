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
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import placeme.octopusites.com.placeme.modal.Publications;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfilePublications extends AppCompatActivity {

    int publicationcount = 0;
    View addmorepublication;
    String username, role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    //    private static String url_savepublications= "http://192.168.100.10/AESTest/SavePublications";
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview, trash6selectionview, trash7selectionview, trash8selectionview, trash9selectionview, trash10selectionview;
    int edittedFlag = 0;
    ;
    int d = 0;
    int errorflag = 0;
    StudentData s = new StudentData();
    EditText title1, publication1, author1, url1, description1, title2, publication2, author2, url2, description2, title3, publication3, author3, url3, description3, title4, publication4, author4, url4, description4, title5, publication5, author5, url5, description5, title6, publication6, author6, url6, description6, title7, publication7, author7, url7, description7, title8, publication8, author8, url8, description8, title9, publication9, author9, url9, description9, title10, publication10, author10, url10, description10;
    EditText publicationdate1, publicationdate2, publicationdate3, publicationdate4, publicationdate5, publicationdate6, publicationdate7, publicationdate8, publicationdate9, publicationdate10;
    TextInputLayout titleinput1, publicationinput1, authorsinput1, publicationdateinput1, titleinput2, publicationinput2, authorsinput2, publicationdateinput2, titleinput3, publicationinput3, authorsinput3, publicationdateinput3, titleinput4, publicationinput4, authorsinput4, publicationdateinput4, titleinput5, publicationinput5, authorsinput5, publicationdateinput5, titleinput6, publicationinput6, authorsinput6, publicationdateinput6, titleinput7, publicationinput7, authorsinput7, publicationdateinput7, titleinput8, publicationinput8, authorsinput8, publicationdateinput8, titleinput9, publicationinput9, authorsinput9, publicationdateinput9, titleinput10, publicationinput10, authorsinput10, publicationdateinput10;
    TextInputLayout urlinput1, descriptioninput1, urlinput2, descriptioninput2, urlinput3, descriptioninput3, urlinput4, descriptioninput4, urlinput5, descriptioninput5, urlinput6, descriptioninput6, urlinput7, descriptioninput7, urlinput8, descriptioninput8, urlinput9, descriptioninput9, urlinput10, descriptioninput10;
    String stitle1 = "", spublication1 = "", sauthor1 = "", surl1 = "", sdescription1 = "", stitle2 = "", spublication2 = "", sauthor2 = "", surl2 = "", sdescription2 = "", stitle3 = "", spublication3 = "", sauthor3 = "", surl3 = "", sdescription3 = "", stitle4 = "", spublication4 = "", sauthor4 = "", surl4 = "", sdescription4 = "", stitle5 = "", spublication5 = "", sauthor5 = "", surl5 = "", sdescription5 = "", stitle6 = "", spublication6 = "", sauthor6 = "", surl6 = "", sdescription6 = "", stitle7 = "", spublication7 = "", sauthor7 = "", surl7 = "", sdescription7 = "", stitle8 = "", spublication8 = "", sauthor8 = "", surl8 = "", sdescription8 = "", stitle9 = "", spublication9 = "", sauthor9 = "", surl9 = "", sdescription9 = "", stitle10 = "", spublication10 = "", sauthor10 = "", surl10 = "", sdescription10 = "";

    String enctitle1, encpublication1, encauthor1, encurl1, encdescription1, enctitle2, encpublication2, encauthor2, encurl2, encdescription2, enctitle3, encpublication3, encauthor3, encurl3, encdescription3, enctitle4, encpublication4, encauthor4, encurl4, encdescription4, enctitle5, encpublication5, encauthor5, encurl5, encdescription5, enctitle6, encpublication6, encauthor6, encurl6, encdescription6, enctitle7, encpublication7, encauthor7, encurl7, encdescription7, enctitle8, encpublication8, encauthor8, encurl8, encdescription8, enctitle9, encpublication9, encauthor9, encurl9, encdescription9, enctitle10, encpublication10, encauthor10, encurl10, encdescription10;

    String spublicationdate1 = "", spublicationdate2 = "", spublicationdate3 = "", spublicationdate4 = "", spublicationdate5 = "", spublicationdate6 = "", spublicationdate7 = "", spublicationdate8 = "", spublicationdate9 = "", spublicationdate10 = "";

    String encpublicationdate1, encpublicationdate2, encpublicationdate3, encpublicationdate4, encpublicationdate5, encpublicationdate6, encpublicationdate7, encpublicationdate8, encpublicationdate9, encpublicationdate10;


    ArrayList<Publications> publicationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_publications);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username = MySharedPreferencesManager.getUsername(this);
        role = MySharedPreferencesManager.getRole(this);


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Publication Info");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        title1 = (EditText) findViewById(R.id.title1);
        publication1 = (EditText) findViewById(R.id.publication1);
        author1 = (EditText) findViewById(R.id.author1);
        url1 = (EditText) findViewById(R.id.url1);
        description1 = (EditText) findViewById(R.id.description1);
        title2 = (EditText) findViewById(R.id.title2);
        publication2 = (EditText) findViewById(R.id.publication2);
        author2 = (EditText) findViewById(R.id.author2);
        url2 = (EditText) findViewById(R.id.url2);
        description2 = (EditText) findViewById(R.id.description2);
        title3 = (EditText) findViewById(R.id.title3);
        publication3 = (EditText) findViewById(R.id.publication3);
        author3 = (EditText) findViewById(R.id.author3);
        url3 = (EditText) findViewById(R.id.url3);
        description3 = (EditText) findViewById(R.id.description3);
        title4 = (EditText) findViewById(R.id.title4);
        publication4 = (EditText) findViewById(R.id.publication4);
        author4 = (EditText) findViewById(R.id.author4);
        url4 = (EditText) findViewById(R.id.url4);
        description4 = (EditText) findViewById(R.id.description4);
        title5 = (EditText) findViewById(R.id.title5);
        publication5 = (EditText) findViewById(R.id.publication5);
        author5 = (EditText) findViewById(R.id.author5);
        url5 = (EditText) findViewById(R.id.url5);
        description5 = (EditText) findViewById(R.id.description5);
        title6 = (EditText) findViewById(R.id.title6);
        publication6 = (EditText) findViewById(R.id.publication6);
        author6 = (EditText) findViewById(R.id.author6);
        url6 = (EditText) findViewById(R.id.url6);
        description6 = (EditText) findViewById(R.id.description6);
        title7 = (EditText) findViewById(R.id.title7);
        publication7 = (EditText) findViewById(R.id.publication7);
        author7 = (EditText) findViewById(R.id.author7);
        url7 = (EditText) findViewById(R.id.url7);
        description7 = (EditText) findViewById(R.id.description7);
        title8 = (EditText) findViewById(R.id.title8);
        publication8 = (EditText) findViewById(R.id.publication8);
        author8 = (EditText) findViewById(R.id.author8);
        url8 = (EditText) findViewById(R.id.url8);
        description8 = (EditText) findViewById(R.id.description8);
        title9 = (EditText) findViewById(R.id.title9);
        publication9 = (EditText) findViewById(R.id.publication9);
        author9 = (EditText) findViewById(R.id.author9);
        url9 = (EditText) findViewById(R.id.url9);
        description9 = (EditText) findViewById(R.id.description9);
        title10 = (EditText) findViewById(R.id.title10);
        publication10 = (EditText) findViewById(R.id.publication10);
        author10 = (EditText) findViewById(R.id.author10);
        url10 = (EditText) findViewById(R.id.url10);
        description10 = (EditText) findViewById(R.id.description10);
        publicationdate1 = (EditText) findViewById(R.id.publicationdate1);
        publicationdate2 = (EditText) findViewById(R.id.publicationdate2);
        publicationdate3 = (EditText) findViewById(R.id.publicationdate3);
        publicationdate4 = (EditText) findViewById(R.id.publicationdate4);
        publicationdate5 = (EditText) findViewById(R.id.publicationdate5);
        publicationdate6 = (EditText) findViewById(R.id.publicationdate6);
        publicationdate7 = (EditText) findViewById(R.id.publicationdate7);
        publicationdate8 = (EditText) findViewById(R.id.publicationdate8);
        publicationdate9 = (EditText) findViewById(R.id.publicationdate9);
        publicationdate10 = (EditText) findViewById(R.id.publicationdate10);


                 titleinput1 = (TextInputLayout) findViewById(R.id.titleinput1);
                 publicationinput1= (TextInputLayout) findViewById(R.id.publicationinput1);
                 authorsinput1= (TextInputLayout) findViewById(R.id.authorsinput1);
                 publicationdateinput1= (TextInputLayout) findViewById(R.id.publicationdateinput1);

                 titleinput2= (TextInputLayout) findViewById(R.id.titleinput2);
                 publicationinput2= (TextInputLayout) findViewById(R.id.publicationinput2);
                 authorsinput2= (TextInputLayout) findViewById(R.id.authorsinput2);
                 publicationdateinput2= (TextInputLayout) findViewById(R.id.publicationdateinput2);

                 titleinput3= (TextInputLayout) findViewById(R.id.titleinput3);
                 publicationinput3= (TextInputLayout) findViewById(R.id.publicationinput3);
                 authorsinput3= (TextInputLayout) findViewById(R.id.authorsinput3);
                 publicationdateinput3= (TextInputLayout) findViewById(R.id.publicationdateinput3);

                 titleinput4= (TextInputLayout) findViewById(R.id.titleinput4);
                 publicationinput4= (TextInputLayout) findViewById(R.id.publicationinput4);
                 authorsinput4= (TextInputLayout) findViewById(R.id.authorsinput4);
                 publicationdateinput4= (TextInputLayout) findViewById(R.id.publicationdateinput4);

                 titleinput5= (TextInputLayout) findViewById(R.id.titleinput5);
                 publicationinput5= (TextInputLayout) findViewById(R.id.publicationinput5);
                 authorsinput5= (TextInputLayout) findViewById(R.id.authorsinput5);
                 publicationdateinput5= (TextInputLayout) findViewById(R.id.publicationdateinput5);

                 titleinput6= (TextInputLayout) findViewById(R.id.titleinput6);
                 publicationinput6= (TextInputLayout) findViewById(R.id.publicationinput6);
                 authorsinput6= (TextInputLayout) findViewById(R.id.authorsinput6);
                 publicationdateinput6= (TextInputLayout) findViewById(R.id.publicationdateinput6);

                 titleinput7= (TextInputLayout) findViewById(R.id.titleinput7);
                 publicationinput7= (TextInputLayout) findViewById(R.id.publicationinput7);
                 authorsinput7= (TextInputLayout) findViewById(R.id.authorsinput7);
                 publicationdateinput7= (TextInputLayout) findViewById(R.id.publicationdateinput7);

                 titleinput8= (TextInputLayout) findViewById(R.id.titleinput8);
                 publicationinput8= (TextInputLayout) findViewById(R.id.publicationinput8);
                 authorsinput8= (TextInputLayout) findViewById(R.id.authorsinput8);
                 publicationdateinput8= (TextInputLayout) findViewById(R.id.publicationdateinput8);

                 titleinput9= (TextInputLayout) findViewById(R.id.titleinput9);
                 publicationinput9= (TextInputLayout) findViewById(R.id.publicationinput9);
                 authorsinput9= (TextInputLayout) findViewById(R.id.authorsinput9);
                 publicationdateinput9= (TextInputLayout) findViewById(R.id.publicationdateinput9);

                 titleinput10= (TextInputLayout) findViewById(R.id.titleinput10);
                 publicationinput10= (TextInputLayout) findViewById(R.id.publicationinput10);
                 authorsinput10= (TextInputLayout) findViewById(R.id.authorsinput10);
                 publicationdateinput10= (TextInputLayout) findViewById(R.id.publicationdateinput10);

                 urlinput1= (TextInputLayout) findViewById(R.id.urlinput1);
                 descriptioninput1= (TextInputLayout) findViewById(R.id.descriptioninput1);
                 urlinput2= (TextInputLayout) findViewById(R.id.urlinput2);
                 descriptioninput2= (TextInputLayout) findViewById(R.id.descriptioninput2);
                 urlinput3= (TextInputLayout) findViewById(R.id.urlinput3);
                 descriptioninput3= (TextInputLayout) findViewById(R.id.descriptioninput3);
                 urlinput4= (TextInputLayout) findViewById(R.id.urlinput4);
                 descriptioninput4= (TextInputLayout) findViewById(R.id.descriptioninput4);
                 urlinput5= (TextInputLayout) findViewById(R.id.urlinput5);
                 descriptioninput5= (TextInputLayout) findViewById(R.id.descriptioninput5);
                 urlinput6= (TextInputLayout) findViewById(R.id.urlinput6);
                 descriptioninput6= (TextInputLayout) findViewById(R.id.descriptioninput6);
                 urlinput7= (TextInputLayout) findViewById(R.id.urlinput7);
                 descriptioninput7= (TextInputLayout) findViewById(R.id.descriptioninput7);
                 urlinput8= (TextInputLayout) findViewById(R.id.urlinput8);
                 descriptioninput8= (TextInputLayout) findViewById(R.id.descriptioninput8);
                 urlinput9= (TextInputLayout) findViewById(R.id.urlinput9);
                 descriptioninput9= (TextInputLayout) findViewById(R.id.descriptioninput9);
                 urlinput10= (TextInputLayout) findViewById(R.id.urlinput10);
                 descriptioninput10= (TextInputLayout) findViewById(R.id.descriptioninput10);



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

        publication1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        author1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                authorsinput1.setError(null);
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
                edittedFlag = 1;
                urlinput1.setError(null);
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
                descriptioninput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicationdate1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationdateinput1.setError(null);
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
        publication2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationinput2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        author2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                authorsinput2.setError(null);
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
                edittedFlag = 1;
                urlinput2.setError(null);
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
               descriptioninput2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicationdate2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationdateinput2.setError(null);
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
        publication3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationinput3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        author3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                authorsinput3.setError(null);
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
                edittedFlag = 1;
                urlinput3.setError(null);
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
               descriptioninput3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicationdate3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationdateinput3.setError(null);
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
        publication4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationinput4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        author4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                authorsinput4.setError(null);
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
                edittedFlag = 1;
                urlinput4.setError(null);
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
               descriptioninput4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicationdate4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationdateinput4.setError(null);
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
        publication5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationinput5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        author5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                authorsinput5.setError(null);
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
                edittedFlag = 1;
                urlinput5.setError(null);
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
               descriptioninput5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicationdate5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationdateinput5.setError(null);
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
        publication6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationinput6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        author6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                authorsinput6.setError(null);
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
                edittedFlag = 1;
                urlinput6.setError(null);
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
               descriptioninput6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicationdate6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationdateinput6.setError(null);
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
        publication7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationinput7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        author7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                authorsinput7.setError(null);
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
                edittedFlag = 1;
                urlinput7.setError(null);
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
               descriptioninput7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicationdate7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationdateinput7.setError(null);
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
        publication8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationinput8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        author8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                authorsinput8.setError(null);
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
                edittedFlag = 1;
                urlinput8.setError(null);
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
               descriptioninput8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicationdate8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationdateinput8.setError(null);
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
        publication9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationinput9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        author9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                authorsinput9.setError(null);
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
                edittedFlag = 1;
                urlinput9.setError(null);
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
               descriptioninput9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicationdate9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationdateinput9.setError(null);
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
        publication10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationinput10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        author10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                authorsinput10.setError(null);
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
                edittedFlag = 1;
                urlinput10.setError(null);
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
               descriptioninput10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicationdate10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                publicationdateinput10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        publicationdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(publicationdate1);
            }
        });
        publicationdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(publicationdate2);
            }
        });
        publicationdate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(publicationdate3);
            }
        });
        publicationdate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(publicationdate4);
            }
        });
        publicationdate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(publicationdate5);
            }
        });
        publicationdate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(publicationdate6);
            }
        });
        publicationdate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(publicationdate7);
            }
        });
        publicationdate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(publicationdate8);
            }
        });
        publicationdate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(publicationdate9);
            }
        });
        publicationdate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(publicationdate10);
            }
        });

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


        TextView publicationtxt = (TextView) findViewById(R.id.publicationtxt);
        TextView addmorepublicationtxt = (TextView) findViewById(R.id.addmorepublicationtxt);

        addmorepublicationtxt.setTypeface(MyConstants.getBold(this));
        publicationtxt.setTypeface(MyConstants.getBold(this));


        title1.setTypeface(MyConstants.getBold(this));
        publication1.setTypeface(MyConstants.getBold(this));
        author1.setTypeface(MyConstants.getBold(this));
        url1.setTypeface(MyConstants.getBold(this));
        description1.setTypeface(MyConstants.getBold(this));

        title2.setTypeface(MyConstants.getBold(this));
        publication2.setTypeface(MyConstants.getBold(this));
        author2.setTypeface(MyConstants.getBold(this));
        url2.setTypeface(MyConstants.getBold(this));
        description2.setTypeface(MyConstants.getBold(this));

        title3.setTypeface(MyConstants.getBold(this));
        publication3.setTypeface(MyConstants.getBold(this));
        author3.setTypeface(MyConstants.getBold(this));
        url3.setTypeface(MyConstants.getBold(this));
        description3.setTypeface(MyConstants.getBold(this));

        title4.setTypeface(MyConstants.getBold(this));
        publication4.setTypeface(MyConstants.getBold(this));
        author4.setTypeface(MyConstants.getBold(this));
        url4.setTypeface(MyConstants.getBold(this));
        description4.setTypeface(MyConstants.getBold(this));

        title5.setTypeface(MyConstants.getBold(this));
        publication5.setTypeface(MyConstants.getBold(this));
        author5.setTypeface(MyConstants.getBold(this));
        url5.setTypeface(MyConstants.getBold(this));
        description5.setTypeface(MyConstants.getBold(this));

        title6.setTypeface(MyConstants.getBold(this));
        publication6.setTypeface(MyConstants.getBold(this));
        author6.setTypeface(MyConstants.getBold(this));
        url6.setTypeface(MyConstants.getBold(this));
        description6.setTypeface(MyConstants.getBold(this));

        title7.setTypeface(MyConstants.getBold(this));
        publication7.setTypeface(MyConstants.getBold(this));
        author7.setTypeface(MyConstants.getBold(this));
        url7.setTypeface(MyConstants.getBold(this));
        description7.setTypeface(MyConstants.getBold(this));

        title8.setTypeface(MyConstants.getBold(this));
        publication8.setTypeface(MyConstants.getBold(this));
        author8.setTypeface(MyConstants.getBold(this));
        url8.setTypeface(MyConstants.getBold(this));
        description8.setTypeface(MyConstants.getBold(this));

        title9.setTypeface(MyConstants.getBold(this));
        publication9.setTypeface(MyConstants.getBold(this));
        author9.setTypeface(MyConstants.getBold(this));
        url9.setTypeface(MyConstants.getBold(this));
        description9.setTypeface(MyConstants.getBold(this));

        title10.setTypeface(MyConstants.getBold(this));
        publication10.setTypeface(MyConstants.getBold(this));
        author10.setTypeface(MyConstants.getBold(this));
        url10.setTypeface(MyConstants.getBold(this));
        description10.setTypeface(MyConstants.getBold(this));

        titleinput1.setTypeface(MyConstants.getLight(this));
        publicationinput1.setTypeface(MyConstants.getLight(this));
        authorsinput1.setTypeface(MyConstants.getLight(this));
        publicationdateinput1.setTypeface(MyConstants.getLight(this));
        titleinput2.setTypeface(MyConstants.getLight(this));
        publicationinput2.setTypeface(MyConstants.getLight(this));
        authorsinput2.setTypeface(MyConstants.getLight(this));
        publicationdateinput2.setTypeface(MyConstants.getLight(this));
        titleinput3.setTypeface(MyConstants.getLight(this));
        publicationinput3.setTypeface(MyConstants.getLight(this));
        authorsinput3.setTypeface(MyConstants.getLight(this));
        publicationdateinput3.setTypeface(MyConstants.getLight(this));
        titleinput4.setTypeface(MyConstants.getLight(this));
        publicationinput4.setTypeface(MyConstants.getLight(this));
        authorsinput4.setTypeface(MyConstants.getLight(this));
        publicationdateinput4.setTypeface(MyConstants.getLight(this));
        titleinput5.setTypeface(MyConstants.getLight(this));
        publicationinput5.setTypeface(MyConstants.getLight(this));
        authorsinput5.setTypeface(MyConstants.getLight(this));
        publicationdateinput5.setTypeface(MyConstants.getLight(this));
        titleinput6.setTypeface(MyConstants.getLight(this));
        publicationinput6.setTypeface(MyConstants.getLight(this));
        authorsinput6.setTypeface(MyConstants.getLight(this));
        publicationdateinput6.setTypeface(MyConstants.getLight(this));
        titleinput7.setTypeface(MyConstants.getLight(this));
        publicationinput7.setTypeface(MyConstants.getLight(this));
        authorsinput7.setTypeface(MyConstants.getLight(this));
        publicationdateinput7.setTypeface(MyConstants.getLight(this));
        titleinput8.setTypeface(MyConstants.getLight(this));
        publicationinput8.setTypeface(MyConstants.getLight(this));
        authorsinput8.setTypeface(MyConstants.getLight(this));
        publicationdateinput8.setTypeface(MyConstants.getLight(this));
        titleinput9.setTypeface(MyConstants.getLight(this));
        publicationinput9.setTypeface(MyConstants.getLight(this));
        authorsinput9.setTypeface(MyConstants.getLight(this));
        publicationdateinput9.setTypeface(MyConstants.getLight(this));
        titleinput10.setTypeface(MyConstants.getLight(this));
        publicationinput10.setTypeface(MyConstants.getLight(this));
        authorsinput10.setTypeface(MyConstants.getLight(this));
        publicationdateinput10.setTypeface(MyConstants.getLight(this));


        addmorepublication = (View) findViewById(R.id.addmorepublication);
        addmorepublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (publicationcount == 0) {
                    if (title1.getText().toString() != null && publication1.getText().toString() != null && author1.getText().toString() != null && publicationdate1.getText().toString() != null) {
                        if (!title1.getText().toString().equals("") && !publication1.getText().toString().equals("") && !author1.getText().toString().equals("")  && !publicationdate1.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.publicationline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl2);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            publicationcount++;
                        } else {
                            Toast.makeText(MyProfilePublications.this, "Please fill the first publication", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfilePublications.this, "Please fill the first publication", Toast.LENGTH_SHORT).show();


                } else if (publicationcount == 1) {
                    if (title2.getText().toString() != null && publication2.getText().toString() != null && author2.getText().toString() != null  && publicationdate2.getText().toString() != null) {
                        if (!title2.getText().toString().equals("") && !publication2.getText().toString().equals("") && !author2.getText().toString().equals("")  && !publicationdate2.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.publicationline2);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl3);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            publicationcount++;
                        } else {
                            Toast.makeText(MyProfilePublications.this, "Please fill the second publication", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfilePublications.this, "Please fill the second publication", Toast.LENGTH_SHORT).show();


                } else if (publicationcount == 2) {
                    if (title3.getText().toString() != null && publication3.getText().toString() != null && author3.getText().toString() != null  && publicationdate3.getText().toString() != null) {
                        if (!title3.getText().toString().equals("") && !publication3.getText().toString().equals("") && !author3.getText().toString().equals("") && !publicationdate3.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.publicationline3);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl4);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            publicationcount++;
                        } else {
                            Toast.makeText(MyProfilePublications.this, "Please fill the third publication", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfilePublications.this, "Please fill the third publication", Toast.LENGTH_SHORT).show();


                } else if (publicationcount == 3) {
                    if (title4.getText().toString() != null && publication4.getText().toString() != null && author4.getText().toString() != null  && publicationdate4.getText().toString() != null) {
                        if (!title4.getText().toString().equals("") && !publication4.getText().toString().equals("") && !author4.getText().toString().equals("")  && !publicationdate4.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.publicationline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl5);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            publicationcount++;
                        } else {
                            Toast.makeText(MyProfilePublications.this, "Please fill the fourth publication", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfilePublications.this, "Please fill the fourth publication", Toast.LENGTH_SHORT).show();


                } else if (publicationcount == 4) {
                    if (title5.getText().toString() != null && publication5.getText().toString() != null && author5.getText().toString() != null &&  publicationdate5.getText().toString() != null) {
                        if (!title5.getText().toString().equals("") && !publication5.getText().toString().equals("") && !author5.getText().toString().equals("") && !publicationdate5.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.publicationline5);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl6);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            publicationcount++;
                        } else {
                            Toast.makeText(MyProfilePublications.this, "Please fill the fifth publication", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfilePublications.this, "Please fill the fifth publication", Toast.LENGTH_SHORT).show();


                } else if (publicationcount == 5) {
                    if (title6.getText().toString() != null && publication6.getText().toString() != null && author6.getText().toString() != null && publicationdate6.getText().toString() != null) {
                        if (!title6.getText().toString().equals("") && !publication6.getText().toString().equals("") && !author6.getText().toString().equals("") && !publicationdate6.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.publicationline6);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl7);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            publicationcount++;
                        } else {
                            Toast.makeText(MyProfilePublications.this, "Please fill the sixth publication", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfilePublications.this, "Please fill the sixth publication", Toast.LENGTH_SHORT).show();


                } else if (publicationcount == 6) {
                    if (title7.getText().toString() != null && publication7.getText().toString() != null && author7.getText().toString() != null && publicationdate7.getText().toString() != null) {
                        if (!title7.getText().toString().equals("") && !publication7.getText().toString().equals("") && !author7.getText().toString().equals("")  && !publicationdate7.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.publicationline7);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl8);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            publicationcount++;
                        } else {
                            Toast.makeText(MyProfilePublications.this, "Please fill the seventh publication", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfilePublications.this, "Please fill the seventh publication", Toast.LENGTH_SHORT).show();


                } else if (publicationcount == 7) {
                    if (title8.getText().toString() != null && publication8.getText().toString() != null && author8.getText().toString() != null && publicationdate8.getText().toString() != null) {
                        if (!title8.getText().toString().equals("") && !publication8.getText().toString().equals("") && !author8.getText().toString().equals("") && !publicationdate8.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.publicationline8);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl9);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            publicationcount++;
                        } else {
                            Toast.makeText(MyProfilePublications.this, "Please fill the eighth publication", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfilePublications.this, "Please fill the eighth publication", Toast.LENGTH_SHORT).show();


                } else if (publicationcount == 8) {
                    if (title9.getText().toString() != null && publication9.getText().toString() != null && author9.getText().toString() != null  && publicationdate9.getText().toString() != null) {
                        if (!title9.getText().toString().equals("") && !publication9.getText().toString().equals("") && !author9.getText().toString().equals("") && !publicationdate9.getText().toString().equals("")) {


                            View v = (View) findViewById(R.id.publicationline9);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl10);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            publicationcount++;


                            TextView t = (TextView) findViewById(R.id.addmorepublicationtxt);
                            ImageView i = (ImageView) findViewById(R.id.addmorepublicationimg);
                            addmorepublication.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(MyProfilePublications.this, "Please fill the nineth publication", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(MyProfilePublications.this, "Please fill the nineth publication", Toast.LENGTH_SHORT).show();


                }

            }
        });

        ScrollView myprofileintroscrollview = (ScrollView) findViewById(R.id.myprofilepublications);
        disableScrollbars(myprofileintroscrollview);

        stitle1 = s.getPubtitle1();
        spublication1 = s.getPublication1();
        sauthor1 = s.getAuthor1();
        spublicationdate1 = s.getPublicationdate1();
        surl1 = s.getPuburl1();
        sdescription1 = s.getPubdescription1();
        stitle2 = s.getPubtitle2();
        spublication2 = s.getPublication2();
        sauthor2 = s.getAuthor2();
        spublicationdate2 = s.getPublicationdate2();
        surl2 = s.getPuburl2();
        Log.d("TAG", "onCreate: surl2  -"+surl2);
        sdescription2 = s.getPubdescription2();
        stitle3 = s.getPubtitle3();
        spublication3 = s.getPublication3();
        sauthor3 = s.getAuthor3();
        spublicationdate3 = s.getPublicationdate3();
        surl3 = s.getPuburl3();
        sdescription3 = s.getPubdescription3();
        stitle4 = s.getPubtitle4();
        spublication4 = s.getPublication4();
        sauthor4 = s.getAuthor4();
        spublicationdate4 = s.getPublicationdate4();
        surl4 = s.getPuburl4();
        sdescription4 = s.getPubdescription4();
        stitle5 = s.getPubtitle5();
        spublication5 = s.getPublication5();
        sauthor5 = s.getAuthor5();
        spublicationdate5 = s.getPublicationdate5();
        surl5 = s.getPuburl5();
        sdescription5 = s.getPubdescription5();
        stitle6 = s.getPubtitle6();
        spublication6 = s.getPublication6();
        sauthor6 = s.getAuthor6();
        spublicationdate6 = s.getPublicationdate6();
        surl6 = s.getPuburl6();
        sdescription6 = s.getPubdescription6();
        stitle7 = s.getPubtitle7();
        spublication7 = s.getPublication7();
        sauthor7 = s.getAuthor7();
        spublicationdate7 = s.getPublicationdate7();
        surl7 = s.getPuburl7();
        sdescription7 = s.getPubdescription7();
        stitle8 = s.getPubtitle8();
        spublication8 = s.getPublication8();
        sauthor8 = s.getAuthor8();
        spublicationdate8 = s.getPublicationdate8();
        surl8 = s.getPuburl8();
        sdescription8 = s.getPubdescription8();
        stitle9 = s.getPubtitle9();
        spublication9 = s.getPublication9();
        sauthor9 = s.getAuthor9();
        spublicationdate9 = s.getPublicationdate9();
        surl9 = s.getPuburl9();
        sdescription9 = s.getPubdescription9();
        stitle10 = s.getPubtitle10();
        spublication10 = s.getPublication10();
        sauthor10 = s.getAuthor10();
        spublicationdate10 = s.getPublicationdate10();
        surl10 = s.getPuburl10();
        sdescription10 = s.getPubdescription10();

        if (stitle1 != null) {
            if (stitle1.length() > 2) {
                title1.setText(stitle1);
                publication1.setText(spublication1);
                author1.setText(sauthor1);
                publicationdate1.setText(spublicationdate1);
                url1.setText(surl1);
                description1.setText(sdescription1);
            }
        }
        if (stitle2 != null) {
            if (stitle2.length() > 2) {
                title2.setText(stitle2);
                publication2.setText(spublication2);
                author2.setText(sauthor2);
                publicationdate2.setText(spublicationdate2);
                url2.setText(surl2);
                description2.setText(sdescription2);

                View v = (View) findViewById(R.id.publicationline1);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl2);
                relativeLayout1.setVisibility(View.VISIBLE);
                publicationcount++;

            }
        }
        if (stitle3 != null) {
            if (stitle3.length() > 2) {
                title3.setText(stitle3);
                publication3.setText(spublication3);
                author3.setText(sauthor3);
                publicationdate3.setText(spublicationdate3);
                url3.setText(surl3);
                description3.setText(sdescription3);

                View v = (View) findViewById(R.id.publicationline2);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl3);
                relativeLayout1.setVisibility(View.VISIBLE);
                publicationcount++;

            }
        }
        if (stitle4 != null) {
            if (stitle4.length() > 2) {

                title4.setText(stitle4);
                publication4.setText(spublication4);
                author4.setText(sauthor4);
                publicationdate4.setText(spublicationdate4);
                url4.setText(surl4);
                description4.setText(sdescription4);

                View v = (View) findViewById(R.id.publicationline3);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl4);
                relativeLayout1.setVisibility(View.VISIBLE);
                publicationcount++;

            }
        }
        if (stitle5 != null) {
            if (stitle5.length() > 2) {
                title5.setText(stitle5);
                publication5.setText(spublication5);
                author5.setText(sauthor5);
                publicationdate5.setText(spublicationdate5);
                url5.setText(surl5);
                description5.setText(sdescription5);

                View v = (View) findViewById(R.id.publicationline4);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl5);
                relativeLayout1.setVisibility(View.VISIBLE);
                publicationcount++;

            }
        }
        if (stitle6 != null) {
            if (stitle6.length() > 2) {
                title6.setText(stitle6);
                publication6.setText(spublication6);
                author6.setText(sauthor6);
                publicationdate6.setText(spublicationdate6);
                url6.setText(surl6);
                description6.setText(sdescription6);

                View v = (View) findViewById(R.id.publicationline5);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl6);
                relativeLayout1.setVisibility(View.VISIBLE);
                publicationcount++;

            }
        }
        if (stitle7 != null) {
            if (stitle7.length() > 2) {
                title7.setText(stitle7);
                publication7.setText(spublication7);
                author7.setText(sauthor7);
                publicationdate7.setText(spublicationdate7);
                url7.setText(surl7);
                description7.setText(sdescription7);

                View v = (View) findViewById(R.id.publicationline6);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl7);
                relativeLayout1.setVisibility(View.VISIBLE);
                publicationcount++;

            }
        }
        if (stitle8 != null) {
            if (stitle8.length() > 2) {
                title8.setText(stitle8);
                publication8.setText(spublication8);
                author8.setText(sauthor8);
                publicationdate8.setText(spublicationdate8);
                url8.setText(surl8);
                description8.setText(sdescription8);

                View v = (View) findViewById(R.id.publicationline7);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl8);
                relativeLayout1.setVisibility(View.VISIBLE);
                publicationcount++;

            }
        }
        if (stitle9 != null) {
            if (stitle9.length() > 2) {
                title9.setText(stitle9);
                publication9.setText(spublication9);
                author9.setText(sauthor9);
                publicationdate9.setText(spublicationdate9);
                url9.setText(surl9);
                description9.setText(sdescription9);

                View v = (View) findViewById(R.id.publicationline8);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl9);
                relativeLayout1.setVisibility(View.VISIBLE);
                publicationcount++;

            }
        }
        if (stitle10 != null) {
            if (stitle10.length() > 2) {
                title10.setText(stitle10);
                publication10.setText(spublication10);
                author10.setText(sauthor10);
                publicationdate10.setText(spublicationdate10);
                url10.setText(surl10);
                description10.setText(sdescription10);

                View v = (View) findViewById(R.id.publicationline9);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl10);
                relativeLayout1.setVisibility(View.VISIBLE);
                publicationcount++;

                TextView t = (TextView) findViewById(R.id.addmorepublicationtxt);
                ImageView i = (ImageView) findViewById(R.id.addmorepublicationimg);
                addmorepublication.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                i.setVisibility(View.GONE);


            }
        }
        edittedFlag = 0;
    }

    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }

    void showDeletDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this publication ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag = 1;
                                deletePublication();
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
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(MyConstants.getBold(MyProfilePublications.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(MyConstants.getBold(MyProfilePublications.this));
            }
        });

        alertDialog.show();

    }

    void deletePublication() {
        View v = (View) findViewById(R.id.publicationline9);
        if (v.getVisibility() == View.VISIBLE) {

            View v1 = (View) findViewById(R.id.publicationline9);
            v.setVisibility(View.GONE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl10);
            relativeLayout1.setVisibility(View.GONE);
            publicationcount--;

            TextView t = (TextView) findViewById(R.id.addmorepublicationtxt);
            ImageView i = (ImageView) findViewById(R.id.addmorepublicationimg);
            addmorepublication.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        } else {
            v = (View) findViewById(R.id.publicationline8);
            if (v.getVisibility() == View.VISIBLE) {

                View v1 = (View) findViewById(R.id.publicationline8);
                v.setVisibility(View.GONE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl9);
                relativeLayout1.setVisibility(View.GONE);
                publicationcount--;

            } else {
                v = (View) findViewById(R.id.publicationline7);
                if (v.getVisibility() == View.VISIBLE) {

                    View v1 = (View) findViewById(R.id.publicationline7);
                    v.setVisibility(View.GONE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl8);
                    relativeLayout1.setVisibility(View.GONE);
                    publicationcount--;

                } else {
                    v = (View) findViewById(R.id.publicationline6);
                    if (v.getVisibility() == View.VISIBLE) {

                        View v1 = (View) findViewById(R.id.publicationline6);
                        v.setVisibility(View.GONE);

                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl7);
                        relativeLayout1.setVisibility(View.GONE);
                        publicationcount--;

                    } else {
                        v = (View) findViewById(R.id.publicationline5);
                        if (v.getVisibility() == View.VISIBLE) {

                            View v1 = (View) findViewById(R.id.publicationline5);
                            v.setVisibility(View.GONE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl6);
                            relativeLayout1.setVisibility(View.GONE);
                            publicationcount--;

                        } else {
                            v = (View) findViewById(R.id.publicationline4);
                            if (v.getVisibility() == View.VISIBLE) {

                                View v1 = (View) findViewById(R.id.publicationline4);
                                v.setVisibility(View.GONE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl5);
                                relativeLayout1.setVisibility(View.GONE);
                                publicationcount--;

                            } else {
                                v = (View) findViewById(R.id.publicationline3);
                                if (v.getVisibility() == View.VISIBLE) {

                                    View v1 = (View) findViewById(R.id.publicationline3);
                                    v.setVisibility(View.GONE);

                                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl4);
                                    relativeLayout1.setVisibility(View.GONE);
                                    publicationcount--;

                                } else {
                                    v = (View) findViewById(R.id.publicationline2);
                                    if (v.getVisibility() == View.VISIBLE) {

                                        View v1 = (View) findViewById(R.id.publicationline2);
                                        v.setVisibility(View.GONE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl3);
                                        relativeLayout1.setVisibility(View.GONE);
                                        publicationcount--;

                                    } else {
                                        v = (View) findViewById(R.id.publicationline1);
                                        if (v.getVisibility() == View.VISIBLE) {

                                            View v1 = (View) findViewById(R.id.publicationline1);
                                            v.setVisibility(View.GONE);

                                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.publicationrl2);
                                            relativeLayout1.setVisibility(View.GONE);
                                            publicationcount--;

                                        } else {
                                            title1.setText("");
                                            publication1.setText("");
                                            author1.setText("");
                                            publicationdate1.setText("");
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
        if (d == 10) {
            title10.setText("");
            publication10.setText("");
            author10.setText("");
            publicationdate10.setText("");
            url10.setText("");
            description10.setText("");
        } else if (d == 9) {
            stitle10 = title10.getText().toString();
            spublication10 = publication10.getText().toString();
            sauthor10 = author10.getText().toString();
            spublicationdate10 = publicationdate10.getText().toString();
            surl10 = url10.getText().toString();
            sdescription10 = description10.getText().toString();


            stitle9 = stitle10;
            spublication9 = spublication10;
            sauthor9 = sauthor10;
            spublicationdate9 = spublicationdate10;
            surl9 = surl10;
            sdescription9 = sdescription10;

            title9.setText(stitle9);
            publication9.setText(spublication9);
            author9.setText(sauthor9);
            publicationdate9.setText(spublicationdate9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            title10.setText("");
            publication10.setText("");
            author10.setText("");
            publicationdate10.setText("");
            url10.setText("");
            description10.setText("");


        } else if (d == 8) {
            stitle10 = title10.getText().toString();
            spublication10 = publication10.getText().toString();
            sauthor10 = author10.getText().toString();
            spublicationdate10 = publicationdate10.getText().toString();
            surl10 = url10.getText().toString();
            sdescription10 = description10.getText().toString();
            stitle9 = title9.getText().toString();
            spublication9 = publication9.getText().toString();
            sauthor9 = author9.getText().toString();
            spublicationdate9 = publicationdate9.getText().toString();
            surl9 = url9.getText().toString();
            sdescription9 = description9.getText().toString();


            stitle8 = stitle9;
            spublication8 = spublication9;
            sauthor8 = sauthor9;
            spublicationdate8 = spublicationdate9;
            surl8 = surl9;
            sdescription8 = sdescription9;

            title8.setText(stitle8);
            publication8.setText(spublication8);
            author8.setText(sauthor8);
            publicationdate8.setText(spublicationdate8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            title9.setText("");
            publication9.setText("");
            author9.setText("");
            publicationdate9.setText("");
            url9.setText("");
            description9.setText("");

            stitle9 = stitle10;
            spublication9 = spublication10;
            sauthor9 = sauthor10;
            spublicationdate9 = spublicationdate10;
            surl9 = surl10;
            sdescription9 = sdescription10;

            title9.setText(stitle9);
            publication9.setText(spublication9);
            author9.setText(sauthor9);
            publicationdate9.setText(spublicationdate9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            title10.setText("");
            publication10.setText("");
            author10.setText("");
            publicationdate10.setText("");
            url10.setText("");
            description10.setText("");

        } else if (d == 7) {
            stitle10 = title10.getText().toString();
            spublication10 = publication10.getText().toString();
            sauthor10 = author10.getText().toString();
            spublicationdate10 = publicationdate10.getText().toString();
            surl10 = url10.getText().toString();
            sdescription10 = description10.getText().toString();
            stitle9 = title9.getText().toString();
            spublication9 = publication9.getText().toString();
            sauthor9 = author9.getText().toString();
            spublicationdate9 = publicationdate9.getText().toString();
            surl9 = url9.getText().toString();
            sdescription9 = description9.getText().toString();
            stitle8 = title8.getText().toString();
            spublication8 = publication8.getText().toString();
            sauthor8 = author8.getText().toString();
            spublicationdate8 = publicationdate8.getText().toString();
            surl8 = url8.getText().toString();
            sdescription8 = description8.getText().toString();


            stitle7 = stitle8;
            spublication7 = spublication8;
            sauthor7 = sauthor8;
            spublicationdate7 = spublicationdate8;
            surl7 = surl8;
            sdescription7 = sdescription8;

            title7.setText(stitle7);
            publication7.setText(spublication7);
            author7.setText(sauthor7);
            publicationdate7.setText(spublicationdate7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            title8.setText("");
            publication8.setText("");
            author8.setText("");
            publicationdate8.setText("");
            url8.setText("");
            description8.setText("");

            stitle8 = stitle9;
            spublication8 = spublication9;
            sauthor8 = sauthor9;
            spublicationdate8 = spublicationdate9;
            surl8 = surl9;
            sdescription8 = sdescription9;

            title8.setText(stitle8);
            publication8.setText(spublication8);
            author8.setText(sauthor8);
            publicationdate8.setText(spublicationdate8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            title9.setText("");
            publication9.setText("");
            author9.setText("");
            publicationdate9.setText("");
            url9.setText("");
            description9.setText("");

            stitle9 = stitle10;
            spublication9 = spublication10;
            sauthor9 = sauthor10;
            spublicationdate9 = spublicationdate10;
            surl9 = surl10;
            sdescription9 = sdescription10;

            title9.setText(stitle9);
            publication9.setText(spublication9);
            author9.setText(sauthor9);
            publicationdate9.setText(spublicationdate9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            title10.setText("");
            publication10.setText("");
            author10.setText("");
            publicationdate10.setText("");
            url10.setText("");
            description10.setText("");

        } else if (d == 6) {
            stitle10 = title10.getText().toString();
            spublication10 = publication10.getText().toString();
            sauthor10 = author10.getText().toString();
            spublicationdate10 = publicationdate10.getText().toString();
            surl10 = url10.getText().toString();
            sdescription10 = description10.getText().toString();
            stitle9 = title9.getText().toString();
            spublication9 = publication9.getText().toString();
            sauthor9 = author9.getText().toString();
            spublicationdate9 = publicationdate9.getText().toString();
            surl9 = url9.getText().toString();
            sdescription9 = description9.getText().toString();
            stitle8 = title8.getText().toString();
            spublication8 = publication8.getText().toString();
            sauthor8 = author8.getText().toString();
            spublicationdate8 = publicationdate8.getText().toString();
            surl8 = url8.getText().toString();
            sdescription8 = description8.getText().toString();
            stitle7 = title7.getText().toString();
            spublication7 = publication7.getText().toString();
            sauthor7 = author7.getText().toString();
            spublicationdate7 = publicationdate7.getText().toString();
            surl7 = url7.getText().toString();
            sdescription7 = description7.getText().toString();


            stitle6 = stitle7;
            spublication6 = spublication7;
            sauthor6 = sauthor7;
            spublicationdate6 = spublicationdate7;
            surl6 = surl7;
            sdescription6 = sdescription7;

            title6.setText(stitle6);
            publication6.setText(spublication6);
            author6.setText(sauthor6);
            publicationdate6.setText(spublicationdate6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            title7.setText("");
            publication7.setText("");
            author7.setText("");
            publicationdate7.setText("");
            url7.setText("");
            description7.setText("");

            stitle7 = stitle8;
            spublication7 = spublication8;
            sauthor7 = sauthor8;
            spublicationdate7 = spublicationdate8;
            surl7 = surl8;
            sdescription7 = sdescription8;

            title7.setText(stitle7);
            publication7.setText(spublication7);
            author7.setText(sauthor7);
            publicationdate7.setText(spublicationdate7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            title8.setText("");
            publication8.setText("");
            author8.setText("");
            publicationdate8.setText("");
            url8.setText("");
            description8.setText("");

            stitle8 = stitle9;
            spublication8 = spublication9;
            sauthor8 = sauthor9;
            spublicationdate8 = spublicationdate9;
            surl8 = surl9;
            sdescription8 = sdescription9;

            title8.setText(stitle8);
            publication8.setText(spublication8);
            author8.setText(sauthor8);
            publicationdate8.setText(spublicationdate8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            title9.setText("");
            publication9.setText("");
            author9.setText("");
            publicationdate9.setText("");
            url9.setText("");
            description9.setText("");

            stitle9 = stitle10;
            spublication9 = spublication10;
            sauthor9 = sauthor10;
            spublicationdate9 = spublicationdate10;
            surl9 = surl10;
            sdescription9 = sdescription10;

            title9.setText(stitle9);
            publication9.setText(spublication9);
            author9.setText(sauthor9);
            publicationdate9.setText(spublicationdate9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            title10.setText("");
            publication10.setText("");
            author10.setText("");
            publicationdate10.setText("");
            url10.setText("");
            description10.setText("");

        } else if (d == 5) {
            stitle10 = title10.getText().toString();
            spublication10 = publication10.getText().toString();
            sauthor10 = author10.getText().toString();
            spublicationdate10 = publicationdate10.getText().toString();
            surl10 = url10.getText().toString();
            sdescription10 = description10.getText().toString();
            stitle9 = title9.getText().toString();
            spublication9 = publication9.getText().toString();
            sauthor9 = author9.getText().toString();
            spublicationdate9 = publicationdate9.getText().toString();
            surl9 = url9.getText().toString();
            sdescription9 = description9.getText().toString();
            stitle8 = title8.getText().toString();
            spublication8 = publication8.getText().toString();
            sauthor8 = author8.getText().toString();
            spublicationdate8 = publicationdate8.getText().toString();
            surl8 = url8.getText().toString();
            sdescription8 = description8.getText().toString();
            stitle7 = title7.getText().toString();
            spublication7 = publication7.getText().toString();
            sauthor7 = author7.getText().toString();
            spublicationdate7 = publicationdate7.getText().toString();
            surl7 = url7.getText().toString();
            sdescription7 = description7.getText().toString();
            stitle6 = title6.getText().toString();
            spublication6 = publication6.getText().toString();
            sauthor6 = author6.getText().toString();
            spublicationdate6 = publicationdate6.getText().toString();
            surl6 = url6.getText().toString();
            sdescription6 = description6.getText().toString();


            stitle5 = stitle6;
            spublication5 = spublication6;
            sauthor5 = sauthor6;
            spublicationdate5 = spublicationdate6;
            surl5 = surl6;
            sdescription5 = sdescription6;

            title5.setText(stitle5);
            publication5.setText(spublication5);
            author5.setText(sauthor5);
            publicationdate5.setText(spublicationdate5);
            url5.setText(surl5);
            description5.setText(sdescription5);

            title6.setText("");
            publication6.setText("");
            author6.setText("");
            publicationdate6.setText("");
            url6.setText("");
            description6.setText("");

            stitle6 = stitle7;
            spublication6 = spublication7;
            sauthor6 = sauthor7;
            spublicationdate6 = spublicationdate7;
            surl6 = surl7;
            sdescription6 = sdescription7;

            title6.setText(stitle6);
            publication6.setText(spublication6);
            author6.setText(sauthor6);
            publicationdate6.setText(spublicationdate6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            title7.setText("");
            publication7.setText("");
            author7.setText("");
            publicationdate7.setText("");
            url7.setText("");
            description7.setText("");

            stitle7 = stitle8;
            spublication7 = spublication8;
            sauthor7 = sauthor8;
            spublicationdate7 = spublicationdate8;
            surl7 = surl8;
            sdescription7 = sdescription8;

            title7.setText(stitle7);
            publication7.setText(spublication7);
            author7.setText(sauthor7);
            publicationdate7.setText(spublicationdate7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            title8.setText("");
            publication8.setText("");
            author8.setText("");
            publicationdate8.setText("");
            url8.setText("");
            description8.setText("");

            stitle8 = stitle9;
            spublication8 = spublication9;
            sauthor8 = sauthor9;
            spublicationdate8 = spublicationdate9;
            surl8 = surl9;
            sdescription8 = sdescription9;

            title8.setText(stitle8);
            publication8.setText(spublication8);
            author8.setText(sauthor8);
            publicationdate8.setText(spublicationdate8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            title9.setText("");
            publication9.setText("");
            author9.setText("");
            publicationdate9.setText("");
            url9.setText("");
            description9.setText("");

            stitle9 = stitle10;
            spublication9 = spublication10;
            sauthor9 = sauthor10;
            spublicationdate9 = spublicationdate10;
            surl9 = surl10;
            sdescription9 = sdescription10;

            title9.setText(stitle9);
            publication9.setText(spublication9);
            author9.setText(sauthor9);
            publicationdate9.setText(spublicationdate9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            title10.setText("");
            publication10.setText("");
            author10.setText("");
            publicationdate10.setText("");
            url10.setText("");
            description10.setText("");

        } else if (d == 4) {
            stitle10 = title10.getText().toString();
            spublication10 = publication10.getText().toString();
            sauthor10 = author10.getText().toString();
            spublicationdate10 = publicationdate10.getText().toString();
            surl10 = url10.getText().toString();
            sdescription10 = description10.getText().toString();
            stitle9 = title9.getText().toString();
            spublication9 = publication9.getText().toString();
            sauthor9 = author9.getText().toString();
            spublicationdate9 = publicationdate9.getText().toString();
            surl9 = url9.getText().toString();
            sdescription9 = description9.getText().toString();
            stitle8 = title8.getText().toString();
            spublication8 = publication8.getText().toString();
            sauthor8 = author8.getText().toString();
            spublicationdate8 = publicationdate8.getText().toString();
            surl8 = url8.getText().toString();
            sdescription8 = description8.getText().toString();
            stitle7 = title7.getText().toString();
            spublication7 = publication7.getText().toString();
            sauthor7 = author7.getText().toString();
            spublicationdate7 = publicationdate7.getText().toString();
            surl7 = url7.getText().toString();
            sdescription7 = description7.getText().toString();
            stitle6 = title6.getText().toString();
            spublication6 = publication6.getText().toString();
            sauthor6 = author6.getText().toString();
            spublicationdate6 = publicationdate6.getText().toString();
            surl6 = url6.getText().toString();
            sdescription6 = description6.getText().toString();
            stitle5 = title5.getText().toString();
            spublication5 = publication5.getText().toString();
            sauthor5 = author5.getText().toString();
            spublicationdate5 = publicationdate5.getText().toString();
            surl5 = url5.getText().toString();
            sdescription5 = description5.getText().toString();


            stitle4 = stitle5;
            spublication4 = spublication5;
            sauthor4 = sauthor5;
            spublicationdate4 = spublicationdate5;
            surl4 = surl5;
            sdescription4 = sdescription5;

            title4.setText(stitle4);
            publication4.setText(spublication4);
            author4.setText(sauthor4);
            publicationdate4.setText(spublicationdate4);
            url4.setText(surl4);
            description4.setText(sdescription4);

            title5.setText("");
            publication5.setText("");
            author5.setText("");
            publicationdate5.setText("");
            url5.setText("");
            description5.setText("");

            stitle5 = stitle6;
            spublication5 = spublication6;
            sauthor5 = sauthor6;
            spublicationdate5 = spublicationdate6;
            surl5 = surl6;
            sdescription5 = sdescription6;

            title5.setText(stitle5);
            publication5.setText(spublication5);
            author5.setText(sauthor5);
            publicationdate5.setText(spublicationdate5);
            url5.setText(surl5);
            description5.setText(sdescription5);

            title6.setText("");
            publication6.setText("");
            author6.setText("");
            publicationdate6.setText("");
            url6.setText("");
            description6.setText("");

            stitle6 = stitle7;
            spublication6 = spublication7;
            sauthor6 = sauthor7;
            spublicationdate6 = spublicationdate7;
            surl6 = surl7;
            sdescription6 = sdescription7;

            title6.setText(stitle6);
            publication6.setText(spublication6);
            author6.setText(sauthor6);
            publicationdate6.setText(spublicationdate6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            title7.setText("");
            publication7.setText("");
            author7.setText("");
            publicationdate7.setText("");
            url7.setText("");
            description7.setText("");

            stitle7 = stitle8;
            spublication7 = spublication8;
            sauthor7 = sauthor8;
            spublicationdate7 = spublicationdate8;
            surl7 = surl8;
            sdescription7 = sdescription8;

            title7.setText(stitle7);
            publication7.setText(spublication7);
            author7.setText(sauthor7);
            publicationdate7.setText(spublicationdate7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            title8.setText("");
            publication8.setText("");
            author8.setText("");
            publicationdate8.setText("");
            url8.setText("");
            description8.setText("");

            stitle8 = stitle9;
            spublication8 = spublication9;
            sauthor8 = sauthor9;
            spublicationdate8 = spublicationdate9;
            surl8 = surl9;
            sdescription8 = sdescription9;

            title8.setText(stitle8);
            publication8.setText(spublication8);
            author8.setText(sauthor8);
            publicationdate8.setText(spublicationdate8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            title9.setText("");
            publication9.setText("");
            author9.setText("");
            publicationdate9.setText("");
            url9.setText("");
            description9.setText("");

            stitle9 = stitle10;
            spublication9 = spublication10;
            sauthor9 = sauthor10;
            spublicationdate9 = spublicationdate10;
            surl9 = surl10;
            sdescription9 = sdescription10;

            title9.setText(stitle9);
            publication9.setText(spublication9);
            author9.setText(sauthor9);
            publicationdate9.setText(spublicationdate9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            title10.setText("");
            publication10.setText("");
            author10.setText("");
            publicationdate10.setText("");
            url10.setText("");
            description10.setText("");

        } else if (d == 3) {
            stitle10 = title10.getText().toString();
            spublication10 = publication10.getText().toString();
            sauthor10 = author10.getText().toString();
            spublicationdate10 = publicationdate10.getText().toString();
            surl10 = url10.getText().toString();
            sdescription10 = description10.getText().toString();
            stitle9 = title9.getText().toString();
            spublication9 = publication9.getText().toString();
            sauthor9 = author9.getText().toString();
            spublicationdate9 = publicationdate9.getText().toString();
            surl9 = url9.getText().toString();
            sdescription9 = description9.getText().toString();
            stitle8 = title8.getText().toString();
            spublication8 = publication8.getText().toString();
            sauthor8 = author8.getText().toString();
            spublicationdate8 = publicationdate8.getText().toString();
            surl8 = url8.getText().toString();
            sdescription8 = description8.getText().toString();
            stitle7 = title7.getText().toString();
            spublication7 = publication7.getText().toString();
            sauthor7 = author7.getText().toString();
            spublicationdate7 = publicationdate7.getText().toString();
            surl7 = url7.getText().toString();
            sdescription7 = description7.getText().toString();
            stitle6 = title6.getText().toString();
            spublication6 = publication6.getText().toString();
            sauthor6 = author6.getText().toString();
            spublicationdate6 = publicationdate6.getText().toString();
            surl6 = url6.getText().toString();
            sdescription6 = description6.getText().toString();
            stitle5 = title5.getText().toString();
            spublication5 = publication5.getText().toString();
            sauthor5 = author5.getText().toString();
            spublicationdate5 = publicationdate5.getText().toString();
            surl5 = url5.getText().toString();
            sdescription5 = description5.getText().toString();
            stitle4 = title4.getText().toString();
            spublication4 = publication4.getText().toString();
            sauthor4 = author4.getText().toString();
            spublicationdate4 = publicationdate4.getText().toString();
            surl4 = url4.getText().toString();
            sdescription4 = description4.getText().toString();


            stitle3 = stitle4;
            spublication3 = spublication4;
            sauthor3 = sauthor4;
            spublicationdate3 = spublicationdate4;
            surl3 = surl4;
            sdescription3 = sdescription4;

            title3.setText(stitle3);
            publication3.setText(spublication3);
            author3.setText(sauthor3);
            publicationdate3.setText(spublicationdate3);
            url3.setText(surl3);
            description3.setText(sdescription3);

            title4.setText("");
            publication4.setText("");
            author4.setText("");
            publicationdate4.setText("");
            url4.setText("");
            description4.setText("");

            stitle4 = stitle5;
            spublication4 = spublication5;
            sauthor4 = sauthor5;
            spublicationdate4 = spublicationdate5;
            surl4 = surl5;
            sdescription4 = sdescription5;

            title4.setText(stitle4);
            publication4.setText(spublication4);
            author4.setText(sauthor4);
            publicationdate4.setText(spublicationdate4);
            url4.setText(surl4);
            description4.setText(sdescription4);

            title5.setText("");
            publication5.setText("");
            author5.setText("");
            publicationdate5.setText("");
            url5.setText("");
            description5.setText("");

            stitle5 = stitle6;
            spublication5 = spublication6;
            sauthor5 = sauthor6;
            spublicationdate5 = spublicationdate6;
            surl5 = surl6;
            sdescription5 = sdescription6;

            title5.setText(stitle5);
            publication5.setText(spublication5);
            author5.setText(sauthor5);
            publicationdate5.setText(spublicationdate5);
            url5.setText(surl5);
            description5.setText(sdescription5);

            title6.setText("");
            publication6.setText("");
            author6.setText("");
            publicationdate6.setText("");
            url6.setText("");
            description6.setText("");

            stitle6 = stitle7;
            spublication6 = spublication7;
            sauthor6 = sauthor7;
            spublicationdate6 = spublicationdate7;
            surl6 = surl7;
            sdescription6 = sdescription7;

            title6.setText(stitle6);
            publication6.setText(spublication6);
            author6.setText(sauthor6);
            publicationdate6.setText(spublicationdate6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            title7.setText("");
            publication7.setText("");
            author7.setText("");
            publicationdate7.setText("");
            url7.setText("");
            description7.setText("");

            stitle7 = stitle8;
            spublication7 = spublication8;
            sauthor7 = sauthor8;
            spublicationdate7 = spublicationdate8;
            surl7 = surl8;
            sdescription7 = sdescription8;

            title7.setText(stitle7);
            publication7.setText(spublication7);
            author7.setText(sauthor7);
            publicationdate7.setText(spublicationdate7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            title8.setText("");
            publication8.setText("");
            author8.setText("");
            publicationdate8.setText("");
            url8.setText("");
            description8.setText("");

            stitle8 = stitle9;
            spublication8 = spublication9;
            sauthor8 = sauthor9;
            spublicationdate8 = spublicationdate9;
            surl8 = surl9;
            sdescription8 = sdescription9;

            title8.setText(stitle8);
            publication8.setText(spublication8);
            author8.setText(sauthor8);
            publicationdate8.setText(spublicationdate8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            title9.setText("");
            publication9.setText("");
            author9.setText("");
            publicationdate9.setText("");
            url9.setText("");
            description9.setText("");

            stitle9 = stitle10;
            spublication9 = spublication10;
            sauthor9 = sauthor10;
            spublicationdate9 = spublicationdate10;
            surl9 = surl10;
            sdescription9 = sdescription10;

            title9.setText(stitle9);
            publication9.setText(spublication9);
            author9.setText(sauthor9);
            publicationdate9.setText(spublicationdate9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            title10.setText("");
            publication10.setText("");
            author10.setText("");
            publicationdate10.setText("");
            url10.setText("");
            description10.setText("");

        } else if (d == 2) {
            stitle10 = title10.getText().toString();
            spublication10 = publication10.getText().toString();
            sauthor10 = author10.getText().toString();
            spublicationdate10 = publicationdate10.getText().toString();
            surl10 = url10.getText().toString();
            sdescription10 = description10.getText().toString();
            stitle9 = title9.getText().toString();
            spublication9 = publication9.getText().toString();
            sauthor9 = author9.getText().toString();
            spublicationdate9 = publicationdate9.getText().toString();
            surl9 = url9.getText().toString();
            sdescription9 = description9.getText().toString();
            stitle8 = title8.getText().toString();
            spublication8 = publication8.getText().toString();
            sauthor8 = author8.getText().toString();
            spublicationdate8 = publicationdate8.getText().toString();
            surl8 = url8.getText().toString();
            sdescription8 = description8.getText().toString();
            stitle7 = title7.getText().toString();
            spublication7 = publication7.getText().toString();
            sauthor7 = author7.getText().toString();
            spublicationdate7 = publicationdate7.getText().toString();
            surl7 = url7.getText().toString();
            sdescription7 = description7.getText().toString();
            stitle6 = title6.getText().toString();
            spublication6 = publication6.getText().toString();
            sauthor6 = author6.getText().toString();
            spublicationdate6 = publicationdate6.getText().toString();
            surl6 = url6.getText().toString();
            sdescription6 = description6.getText().toString();
            stitle5 = title5.getText().toString();
            spublication5 = publication5.getText().toString();
            sauthor5 = author5.getText().toString();
            spublicationdate5 = publicationdate5.getText().toString();
            surl5 = url5.getText().toString();
            sdescription5 = description5.getText().toString();
            stitle4 = title4.getText().toString();
            spublication4 = publication4.getText().toString();
            sauthor4 = author4.getText().toString();
            spublicationdate4 = publicationdate4.getText().toString();
            surl4 = url4.getText().toString();
            sdescription4 = description4.getText().toString();
            stitle3 = title3.getText().toString();
            spublication3 = publication3.getText().toString();
            sauthor3 = author3.getText().toString();
            spublicationdate3 = publicationdate3.getText().toString();
            surl3 = url3.getText().toString();
            sdescription3 = description3.getText().toString();


            stitle2 = stitle3;
            spublication2 = spublication3;
            sauthor2 = sauthor3;
            spublicationdate2 = spublicationdate3;
            surl2 = surl3;
            sdescription2 = sdescription3;

            title2.setText(stitle2);
            publication2.setText(spublication2);
            author2.setText(sauthor2);
            publicationdate2.setText(spublicationdate2);
            url2.setText(surl2);
            description2.setText(sdescription2);

            title3.setText("");
            publication3.setText("");
            author3.setText("");
            publicationdate3.setText("");
            url3.setText("");
            description3.setText("");

            stitle3 = stitle4;
            spublication3 = spublication4;
            sauthor3 = sauthor4;
            spublicationdate3 = spublicationdate4;
            surl3 = surl4;
            sdescription3 = sdescription4;

            title3.setText(stitle3);
            publication3.setText(spublication3);
            author3.setText(sauthor3);
            publicationdate3.setText(spublicationdate3);
            url3.setText(surl3);
            description3.setText(sdescription3);

            title4.setText("");
            publication4.setText("");
            author4.setText("");
            publicationdate4.setText("");
            url4.setText("");
            description4.setText("");

            stitle4 = stitle5;
            spublication4 = spublication5;
            sauthor4 = sauthor5;
            spublicationdate4 = spublicationdate5;
            surl4 = surl5;
            sdescription4 = sdescription5;

            title4.setText(stitle4);
            publication4.setText(spublication4);
            author4.setText(sauthor4);
            publicationdate4.setText(spublicationdate4);
            url4.setText(surl4);
            description4.setText(sdescription4);

            title5.setText("");
            publication5.setText("");
            author5.setText("");
            publicationdate5.setText("");
            url5.setText("");
            description5.setText("");

            stitle5 = stitle6;
            spublication5 = spublication6;
            sauthor5 = sauthor6;
            spublicationdate5 = spublicationdate6;
            surl5 = surl6;
            sdescription5 = sdescription6;

            title5.setText(stitle5);
            publication5.setText(spublication5);
            author5.setText(sauthor5);
            publicationdate5.setText(spublicationdate5);
            url5.setText(surl5);
            description5.setText(sdescription5);

            title6.setText("");
            publication6.setText("");
            author6.setText("");
            publicationdate6.setText("");
            url6.setText("");
            description6.setText("");

            stitle6 = stitle7;
            spublication6 = spublication7;
            sauthor6 = sauthor7;
            spublicationdate6 = spublicationdate7;
            surl6 = surl7;
            sdescription6 = sdescription7;

            title6.setText(stitle6);
            publication6.setText(spublication6);
            author6.setText(sauthor6);
            publicationdate6.setText(spublicationdate6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            title7.setText("");
            publication7.setText("");
            author7.setText("");
            publicationdate7.setText("");
            url7.setText("");
            description7.setText("");

            stitle7 = stitle8;
            spublication7 = spublication8;
            sauthor7 = sauthor8;
            spublicationdate7 = spublicationdate8;
            surl7 = surl8;
            sdescription7 = sdescription8;

            title7.setText(stitle7);
            publication7.setText(spublication7);
            author7.setText(sauthor7);
            publicationdate7.setText(spublicationdate7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            title8.setText("");
            publication8.setText("");
            author8.setText("");
            publicationdate8.setText("");
            url8.setText("");
            description8.setText("");

            stitle8 = stitle9;
            spublication8 = spublication9;
            sauthor8 = sauthor9;
            spublicationdate8 = spublicationdate9;
            surl8 = surl9;
            sdescription8 = sdescription9;

            title8.setText(stitle8);
            publication8.setText(spublication8);
            author8.setText(sauthor8);
            publicationdate8.setText(spublicationdate8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            title9.setText("");
            publication9.setText("");
            author9.setText("");
            publicationdate9.setText("");
            url9.setText("");
            description9.setText("");

            stitle9 = stitle10;
            spublication9 = spublication10;
            sauthor9 = sauthor10;
            spublicationdate9 = spublicationdate10;
            surl9 = surl10;
            sdescription9 = sdescription10;

            title9.setText(stitle9);
            publication9.setText(spublication9);
            author9.setText(sauthor9);
            publicationdate9.setText(spublicationdate9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            title10.setText("");
            publication10.setText("");
            author10.setText("");
            publicationdate10.setText("");
            url10.setText("");
            description10.setText("");

        } else if (d == 1) {
            stitle10 = title10.getText().toString();
            spublication10 = publication10.getText().toString();
            sauthor10 = author10.getText().toString();
            spublicationdate10 = publicationdate10.getText().toString();
            surl10 = url10.getText().toString();
            sdescription10 = description10.getText().toString();
            stitle9 = title9.getText().toString();
            spublication9 = publication9.getText().toString();
            sauthor9 = author9.getText().toString();
            spublicationdate9 = publicationdate9.getText().toString();
            surl9 = url9.getText().toString();
            sdescription9 = description9.getText().toString();
            stitle8 = title8.getText().toString();
            spublication8 = publication8.getText().toString();
            sauthor8 = author8.getText().toString();
            spublicationdate8 = publicationdate8.getText().toString();
            surl8 = url8.getText().toString();
            sdescription8 = description8.getText().toString();
            stitle7 = title7.getText().toString();
            spublication7 = publication7.getText().toString();
            sauthor7 = author7.getText().toString();
            spublicationdate7 = publicationdate7.getText().toString();
            surl7 = url7.getText().toString();
            sdescription7 = description7.getText().toString();
            stitle6 = title6.getText().toString();
            spublication6 = publication6.getText().toString();
            sauthor6 = author6.getText().toString();
            spublicationdate6 = publicationdate6.getText().toString();
            surl6 = url6.getText().toString();
            sdescription6 = description6.getText().toString();
            stitle5 = title5.getText().toString();
            spublication5 = publication5.getText().toString();
            sauthor5 = author5.getText().toString();
            spublicationdate5 = publicationdate5.getText().toString();
            surl5 = url5.getText().toString();
            sdescription5 = description5.getText().toString();
            stitle4 = title4.getText().toString();
            spublication4 = publication4.getText().toString();
            sauthor4 = author4.getText().toString();
            spublicationdate4 = publicationdate4.getText().toString();
            surl4 = url4.getText().toString();
            sdescription4 = description4.getText().toString();
            stitle3 = title3.getText().toString();
            spublication3 = publication3.getText().toString();
            sauthor3 = author3.getText().toString();
            spublicationdate3 = publicationdate3.getText().toString();
            surl3 = url3.getText().toString();
            sdescription3 = description3.getText().toString();
            stitle2 = title2.getText().toString();
            spublication2 = publication2.getText().toString();
            sauthor2 = author2.getText().toString();
            spublicationdate2 = publicationdate2.getText().toString();
            surl2 = url2.getText().toString();
            sdescription2 = description2.getText().toString();


            stitle1 = stitle2;
            spublication1 = spublication2;
            sauthor1 = sauthor2;
            spublicationdate1 = spublicationdate2;
            surl1 = surl2;
            sdescription1 = sdescription2;

            title1.setText(stitle1);
            publication1.setText(spublication1);
            author1.setText(sauthor1);
            publicationdate1.setText(spublicationdate1);
            url1.setText(surl1);
            description1.setText(sdescription1);

            title2.setText("");
            publication2.setText("");
            author2.setText("");
            publicationdate2.setText("");
            url2.setText("");
            description2.setText("");

            stitle2 = stitle3;
            spublication2 = spublication3;
            sauthor2 = sauthor3;
            spublicationdate2 = spublicationdate3;
            surl2 = surl3;
            sdescription2 = sdescription3;

            title2.setText(stitle2);
            publication2.setText(spublication2);
            author2.setText(sauthor2);
            publicationdate2.setText(spublicationdate2);
            url2.setText(surl2);
            description2.setText(sdescription2);

            title3.setText("");
            publication3.setText("");
            author3.setText("");
            publicationdate3.setText("");
            url3.setText("");
            description3.setText("");

            stitle3 = stitle4;
            spublication3 = spublication4;
            sauthor3 = sauthor4;
            spublicationdate3 = spublicationdate4;
            surl3 = surl4;
            sdescription3 = sdescription4;

            title3.setText(stitle3);
            publication3.setText(spublication3);
            author3.setText(sauthor3);
            publicationdate3.setText(spublicationdate3);
            url3.setText(surl3);
            description3.setText(sdescription3);

            title4.setText("");
            publication4.setText("");
            author4.setText("");
            publicationdate4.setText("");
            url4.setText("");
            description4.setText("");

            stitle4 = stitle5;
            spublication4 = spublication5;
            sauthor4 = sauthor5;
            spublicationdate4 = spublicationdate5;
            surl4 = surl5;
            sdescription4 = sdescription5;

            title4.setText(stitle4);
            publication4.setText(spublication4);
            author4.setText(sauthor4);
            publicationdate4.setText(spublicationdate4);
            url4.setText(surl4);
            description4.setText(sdescription4);

            title5.setText("");
            publication5.setText("");
            author5.setText("");
            publicationdate5.setText("");
            url5.setText("");
            description5.setText("");

            stitle5 = stitle6;
            spublication5 = spublication6;
            sauthor5 = sauthor6;
            spublicationdate5 = spublicationdate6;
            surl5 = surl6;
            sdescription5 = sdescription6;

            title5.setText(stitle5);
            publication5.setText(spublication5);
            author5.setText(sauthor5);
            publicationdate5.setText(spublicationdate5);
            url5.setText(surl5);
            description5.setText(sdescription5);

            title6.setText("");
            publication6.setText("");
            author6.setText("");
            publicationdate6.setText("");
            url6.setText("");
            description6.setText("");

            stitle6 = stitle7;
            spublication6 = spublication7;
            sauthor6 = sauthor7;
            spublicationdate6 = spublicationdate7;
            surl6 = surl7;
            sdescription6 = sdescription7;

            title6.setText(stitle6);
            publication6.setText(spublication6);
            author6.setText(sauthor6);
            publicationdate6.setText(spublicationdate6);
            url6.setText(surl6);
            description6.setText(sdescription6);

            title7.setText("");
            publication7.setText("");
            author7.setText("");
            publicationdate7.setText("");
            url7.setText("");
            description7.setText("");

            stitle7 = stitle8;
            spublication7 = spublication8;
            sauthor7 = sauthor8;
            spublicationdate7 = spublicationdate8;
            surl7 = surl8;
            sdescription7 = sdescription8;

            title7.setText(stitle7);
            publication7.setText(spublication7);
            author7.setText(sauthor7);
            publicationdate7.setText(spublicationdate7);
            url7.setText(surl7);
            description7.setText(sdescription7);

            title8.setText("");
            publication8.setText("");
            author8.setText("");
            publicationdate8.setText("");
            url8.setText("");
            description8.setText("");

            stitle8 = stitle9;
            spublication8 = spublication9;
            sauthor8 = sauthor9;
            spublicationdate8 = spublicationdate9;
            surl8 = surl9;
            sdescription8 = sdescription9;

            title8.setText(stitle8);
            publication8.setText(spublication8);
            author8.setText(sauthor8);
            publicationdate8.setText(spublicationdate8);
            url8.setText(surl8);
            description8.setText(sdescription8);

            title9.setText("");
            publication9.setText("");
            author9.setText("");
            publicationdate9.setText("");
            url9.setText("");
            description9.setText("");

            stitle9 = stitle10;
            spublication9 = spublication10;
            sauthor9 = sauthor10;
            spublicationdate9 = spublicationdate10;
            surl9 = surl10;
            sdescription9 = sdescription10;

            title9.setText(stitle9);
            publication9.setText(spublication9);
            author9.setText(sauthor9);
            publicationdate9.setText(spublicationdate9);
            url9.setText(surl9);
            description9.setText(sdescription9);

            title10.setText("");
            publication10.setText("");
            author10.setText("");
            publicationdate10.setText("");
            url10.setText("");
            description10.setText("");

        }
    }

    void validateandSave() {
        titleinput1.setError(null);
        publicationinput1.setError(null);
        authorsinput1.setError(null);
        publicationdateinput1.setError(null);
        urlinput1.setError(null);
        descriptioninput1.setError(null);

        titleinput2.setError(null);
        publicationinput2.setError(null);
        authorsinput2.setError(null);
        publicationdateinput2.setError(null);
        urlinput2.setError(null);
        descriptioninput2.setError(null);
        titleinput3.setError(null);
        publicationinput3.setError(null);
        authorsinput3.setError(null);
        publicationdateinput3.setError(null);
        urlinput3.setError(null);
        descriptioninput3.setError(null);
        titleinput4.setError(null);
        publicationinput4.setError(null);
        authorsinput4.setError(null);
        publicationdateinput4.setError(null);
        urlinput4.setError(null);
        descriptioninput4.setError(null);
        titleinput5.setError(null);
        publicationinput5.setError(null);
        authorsinput5.setError(null);
        publicationdateinput5.setError(null);
        urlinput5.setError(null);
        descriptioninput5.setError(null);
        titleinput6.setError(null);
        publicationinput6.setError(null);
        authorsinput6.setError(null);
        publicationdateinput6.setError(null);
        urlinput6.setError(null);
        descriptioninput6.setError(null);
        titleinput7.setError(null);
        publicationinput7.setError(null);
        authorsinput7.setError(null);
        publicationdateinput7.setError(null);
        urlinput7.setError(null);
        descriptioninput7.setError(null);
        titleinput8.setError(null);
        publicationinput8.setError(null);
        authorsinput8.setError(null);
        publicationdateinput8.setError(null);
        urlinput8.setError(null);
        descriptioninput8.setError(null);
        titleinput9.setError(null);
        publicationinput9.setError(null);
        authorsinput9.setError(null);
        publicationdateinput9.setError(null);
        urlinput9.setError(null);
        descriptioninput9.setError(null);
        titleinput10.setError(null);
        publicationinput10.setError(null);
        authorsinput10.setError(null);
        publicationdateinput10.setError(null);
        urlinput10.setError(null);
        descriptioninput10.setError(null);

        stitle1 = title1.getText().toString();
        spublication1 = publication1.getText().toString();
        sauthor1 = author1.getText().toString();
        spublicationdate1 = publicationdate1.getText().toString();
        surl1 = url1.getText().toString();
        sdescription1 = description1.getText().toString();
        stitle2 = title2.getText().toString();
        spublication2 = publication2.getText().toString();
        sauthor2 = author2.getText().toString();
        spublicationdate2 = publicationdate2.getText().toString();
        surl2 = url2.getText().toString();
        sdescription2 = description2.getText().toString();
        stitle3 = title3.getText().toString();
        spublication3 = publication3.getText().toString();
        sauthor3 = author3.getText().toString();
        spublicationdate3 = publicationdate3.getText().toString();
        surl3 = url3.getText().toString();
        sdescription3 = description3.getText().toString();
        stitle4 = title4.getText().toString();
        spublication4 = publication4.getText().toString();
        sauthor4 = author4.getText().toString();
        spublicationdate4 = publicationdate4.getText().toString();
        surl4 = url4.getText().toString();
        sdescription4 = description4.getText().toString();
        stitle5 = title5.getText().toString();
        spublication5 = publication5.getText().toString();
        sauthor5 = author5.getText().toString();
        spublicationdate5 = publicationdate5.getText().toString();
        surl5 = url5.getText().toString();
        sdescription5 = description5.getText().toString();
        stitle6 = title6.getText().toString();
        spublication6 = publication6.getText().toString();
        sauthor6 = author6.getText().toString();
        spublicationdate6 = publicationdate6.getText().toString();
        surl6 = url6.getText().toString();
        sdescription6 = description6.getText().toString();
        stitle7 = title7.getText().toString();
        spublication7 = publication7.getText().toString();
        sauthor7 = author7.getText().toString();
        spublicationdate7 = publicationdate7.getText().toString();
        surl7 = url7.getText().toString();
        sdescription7 = description7.getText().toString();
        stitle8 = title8.getText().toString();
        spublication8 = publication8.getText().toString();
        sauthor8 = author8.getText().toString();
        spublicationdate8 = publicationdate8.getText().toString();
        surl8 = url8.getText().toString();
        sdescription8 = description8.getText().toString();
        stitle9 = title9.getText().toString();
        spublication9 = publication9.getText().toString();
        sauthor9 = author9.getText().toString();
        spublicationdate9 = publicationdate9.getText().toString();
        surl9 = url9.getText().toString();
        sdescription9 = description9.getText().toString();
        stitle10 = title10.getText().toString();
        spublication10 = publication10.getText().toString();
        sauthor10 = author10.getText().toString();
        spublicationdate10 = publicationdate10.getText().toString();
        surl10 = url10.getText().toString();
        sdescription10 = description10.getText().toString();

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";


        if (publicationcount == 0) {

            if (stitle1.length() < 2) {
                errorflag = 1;
                titleinput1.setError("Kindly enter valid title");
            } else {
                errorflag = 0;
                if (spublication1.length() < 2) {
                    errorflag = 1;
                    publicationinput1.setError("Kindly enter valid publication");
                } else {
                    errorflag = 0;
                    if (sauthor1.length() < 2) {
                        errorflag = 1;
                        authorsinput1.setError("Kindly enter valid author name");
                    } else {
                        errorflag = 0;
                        if (spublicationdate1.length() < 2) {
                            errorflag = 1;
                            publicationdateinput1.setError("Kindly select valid month,year");
                        }
                    }
                }
            }
        } else if (publicationcount == 1) {
            if (stitle1.length() < 2) {
                errorflag = 1;
                titleinput1.setError("Kindly enter valid title");
            } else {
                errorflag = 0;
                if (spublication1.length() < 2) {
                    errorflag = 1;
                    publicationinput1.setError("Kindly enter valid publication");
                } else {
                    errorflag = 0;
                    if (sauthor1.length() < 2) {
                        errorflag = 1;
                        authorsinput1.setError("Kindly enter valid author name");
                    } else {
                        errorflag = 0;
                        if (spublicationdate1.length() < 2) {
                            errorflag = 1;
                            publicationdateinput1.setError("Kindly select valid month,year");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 2) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (spublication2.length() < 2) {
                                    errorflag = 2;
                                    publicationinput2.setError("Kindly enter valid publication");
                                } else {
                                    errorflag = 0;
                                    if (sauthor2.length() < 2) {
                                        errorflag = 2;
                                        authorsinput2.setError("Kindly enter valid author name");
                                    } else {
                                        errorflag = 0;
                                        if (spublicationdate2.length() < 2) {
                                            errorflag = 2;
                                            publicationdateinput2.setError("Kindly select valid month,year");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (publicationcount == 2) {
            if (stitle1.length() < 2) {
                errorflag = 1;
                titleinput1.setError("Kindly enter valid title");
            } else {
                errorflag = 0;
                if (spublication1.length() < 2) {
                    errorflag = 1;
                    publicationinput1.setError("Kindly enter valid publication");
                } else {
                    errorflag = 0;
                    if (sauthor1.length() < 2) {
                        errorflag = 1;
                        authorsinput1.setError("Kindly enter valid Author Name");
                    } else {
                        errorflag = 0;
                        if (spublicationdate1.length() < 2) {
                            errorflag = 1;
                            publicationdateinput1.setError("Kindly select valid month,year");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 2) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (spublication2.length() < 2) {
                                    errorflag = 2;
                                    publicationinput2.setError("Kindly enter valid publication");
                                } else {
                                    errorflag = 0;
                                    if (sauthor2.length() < 2) {
                                        errorflag = 2;
                                        authorsinput2.setError("Kindly enter valid author name");
                                    } else {
                                        errorflag = 0;
                                        if (spublicationdate2.length() < 2) {
                                            errorflag = 2;
                                            publicationdateinput2.setError("Kindly select valid month,year");
                                        } else {
                                            errorflag = 0;
                                            if (stitle3.length() < 2) {
                                                errorflag = 3;
                                                titleinput3.setError("Kindly enter valid title");
                                            } else {
                                                errorflag = 0;
                                                if (spublication3.length() < 2) {
                                                    errorflag = 3;
                                                    publicationinput3.setError("Kindly enter valid publication");
                                                } else {
                                                    errorflag = 0;
                                                    if (sauthor3.length() < 2) {
                                                        errorflag = 3;
                                                        authorsinput3.setError("Kindly enter valid author name");
                                                    } else {
                                                        errorflag = 0;
                                                        if (spublicationdate3.length() < 2) {
                                                            errorflag = 3;
                                                            publicationdateinput3.setError("Kindly select valid month,year");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (publicationcount == 3) {
            if (stitle1.length() < 2) {
                errorflag = 1;
                titleinput1.setError("Kindly enter valid title");
            } else {
                errorflag = 0;
                if (spublication1.length() < 2) {
                    errorflag = 1;
                    publicationinput1.setError("Kindly enter valid publication");
                } else {
                    errorflag = 0;
                    if (sauthor1.length() < 2) {
                        errorflag = 1;
                        authorsinput1.setError("Kindly enter valid author name");
                    } else {
                        errorflag = 0;
                        if (spublicationdate1.length() < 2) {
                            errorflag = 1;
                            publicationdateinput1.setError("Kindly select valid month,year");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 2) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (spublication2.length() < 2) {
                                    errorflag = 2;
                                    publicationinput2.setError("Kindly enter valid publication");
                                } else {
                                    errorflag = 0;
                                    if (sauthor2.length() < 2) {
                                        errorflag = 2;
                                        authorsinput2.setError("Kindly enter valid author name");
                                    } else {
                                        errorflag = 0;
                                        if (spublicationdate2.length() < 2) {
                                            errorflag = 2;
                                            publicationdateinput2.setError("Kindly select valid month,year");
                                        } else {
                                            errorflag = 0;
                                            if (stitle3.length() < 2) {
                                                errorflag = 3;
                                                titleinput3.setError("Kindly enter valid title");
                                            } else {
                                                errorflag = 0;
                                                if (spublication3.length() < 2) {
                                                    errorflag = 3;
                                                    publicationinput3.setError("Kindly enter valid publication");
                                                } else {
                                                    errorflag = 0;
                                                    if (sauthor3.length() < 2) {
                                                        errorflag = 3;
                                                        authorsinput3.setError("Kindly enter valid author name");
                                                    } else {
                                                        errorflag = 0;
                                                        if (spublicationdate3.length() < 2) {
                                                            errorflag = 3;
                                                            publicationdateinput3.setError("Kindly select valid month,year");
                                                        } else {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 2) {
                                                                errorflag = 4;
                                                                titleinput4.setError("Kindly enter valid title");
                                                            } else {
                                                                errorflag = 0;
                                                                if (spublication4.length() < 2) {
                                                                    errorflag = 4;
                                                                    publicationinput4.setError("Kindly enter valid publication");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sauthor4.length() < 2) {
                                                                        errorflag = 4;
                                                                        authorsinput4.setError("Kindly enter valid author name");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (spublicationdate4.length() < 2) {
                                                                            errorflag = 4;
                                                                            publicationdateinput4.setError("Kindly select valid month,year");
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (publicationcount == 4) {
            if (stitle1.length() < 2) {
                errorflag = 1;
                titleinput1.setError("Kindly enter valid title");
            } else {
                errorflag = 0;
                if (spublication1.length() < 2) {
                    errorflag = 1;
                    publicationinput1.setError("Kindly enter valid publication");
                } else {
                    errorflag = 0;
                    if (sauthor1.length() < 2) {
                        errorflag = 1;
                        authorsinput1.setError("Kindly enter valid author name");
                    } else {
                        errorflag = 0;
                        if (spublicationdate1.length() < 2) {
                            errorflag = 1;
                            publicationdateinput1.setError("Kindly select valid month,year");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 2) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (spublication2.length() < 2) {
                                    errorflag = 2;
                                    publicationinput2.setError("Kindly enter valid publication");
                                } else {
                                    errorflag = 0;
                                    if (sauthor2.length() < 2) {
                                        errorflag = 2;
                                        authorsinput2.setError("Kindly enter valid author name");
                                    } else {
                                        errorflag = 0;
                                        if (spublicationdate2.length() < 2) {
                                            errorflag = 2;
                                            publicationdateinput2.setError("Kindly select valid month,year");
                                        } else {
                                            errorflag = 0;
                                            if (stitle3.length() < 2) {
                                                errorflag = 3;
                                                titleinput3.setError("Kindly enter valid title");
                                            } else {
                                                errorflag = 0;
                                                if (spublication3.length() < 2) {
                                                    errorflag = 3;
                                                    publicationinput3.setError("Kindly enter valid publication");
                                                } else {
                                                    errorflag = 0;
                                                    if (sauthor3.length() < 2) {
                                                        errorflag = 3;
                                                        authorsinput3.setError("Kindly enter valid author name");
                                                    } else {
                                                        errorflag = 0;
                                                        if (spublicationdate3.length() < 2) {
                                                            errorflag = 3;
                                                            publicationdateinput3.setError("Kindly select valid month,year");
                                                        } else {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 2) {
                                                                errorflag = 4;
                                                                titleinput4.setError("Kindly enter valid title");
                                                            } else {
                                                                errorflag = 0;
                                                                if (spublication4.length() < 2) {
                                                                    errorflag = 4;
                                                                    publicationinput4.setError("Kindly enter valid publication");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sauthor4.length() < 2) {
                                                                        errorflag = 4;
                                                                        authorsinput4.setError("Kindly enter valid author name");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (spublicationdate4.length() < 2) {
                                                                            errorflag = 4;
                                                                            publicationdateinput4.setError("Kindly select valid month,year");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 2) {
                                                                                errorflag = 5;
                                                                                titleinput5.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (spublication5.length() < 2) {
                                                                                    errorflag = 5;
                                                                                    publicationinput5.setError("Kindly enter valid publication");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sauthor5.length() < 2) {
                                                                                        errorflag = 5;
                                                                                        authorsinput5.setError("Kindly enter valid author name");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (spublicationdate5.length() < 2) {
                                                                                            errorflag = 5;
                                                                                            publicationdateinput5.setError("Kindly select valid month,year");
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (publicationcount == 5) {
            if (stitle1.length() < 2) {
                errorflag = 1;
                titleinput1.setError("Kindly enter valid title");
            } else {
                errorflag = 0;
                if (spublication1.length() < 2) {
                    errorflag = 1;
                    publicationinput1.setError("Kindly enter valid publication");
                } else {
                    errorflag = 0;
                    if (sauthor1.length() < 2) {
                        errorflag = 1;
                        authorsinput1.setError("Kindly enter valid author name");
                    } else {
                        errorflag = 0;
                        if (spublicationdate1.length() < 2) {
                            errorflag = 1;
                            publicationdateinput1.setError("Kindly select valid month,year");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 2) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (spublication2.length() < 2) {
                                    errorflag = 2;
                                    publicationinput2.setError("Kindly enter valid publication");
                                } else {
                                    errorflag = 0;
                                    if (sauthor2.length() < 2) {
                                        errorflag = 2;
                                        authorsinput2.setError("Kindly enter valid author name");
                                    } else {
                                        errorflag = 0;
                                        if (spublicationdate2.length() < 2) {
                                            errorflag = 2;
                                            publicationdateinput2.setError("Kindly select valid month,year");
                                        } else {
                                            errorflag = 0;
                                            if (stitle3.length() < 2) {
                                                errorflag = 3;
                                                titleinput3.setError("Kindly enter valid title");
                                            } else {
                                                errorflag = 0;
                                                if (spublication3.length() < 2) {
                                                    errorflag = 3;
                                                    publicationinput3.setError("Kindly enter valid publication");
                                                } else {
                                                    errorflag = 0;
                                                    if (sauthor3.length() < 2) {
                                                        errorflag = 3;
                                                        authorsinput3.setError("Kindly enter valid author name");
                                                    } else {
                                                        errorflag = 0;
                                                        if (spublicationdate3.length() < 2) {
                                                            errorflag = 3;
                                                            publicationdateinput3.setError("Kindly select valid month,year");
                                                        } else {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 2) {
                                                                errorflag = 4;
                                                                titleinput4.setError("Kindly enter valid title");
                                                            } else {
                                                                errorflag = 0;
                                                                if (spublication4.length() < 2) {
                                                                    errorflag = 4;
                                                                    publicationinput4.setError("Kindly enter valid publication");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sauthor4.length() < 2) {
                                                                        errorflag = 4;
                                                                        authorsinput4.setError("Kindly enter valid author name");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (spublicationdate4.length() < 2) {
                                                                            errorflag = 4;
                                                                            publicationdateinput4.setError("Kindly select valid month,year");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 2) {
                                                                                errorflag = 5;
                                                                                titleinput5.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (spublication5.length() < 2) {
                                                                                    errorflag = 5;
                                                                                    publicationinput5.setError("Kindly enter valid publication");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sauthor5.length() < 2) {
                                                                                        errorflag = 5;
                                                                                        authorsinput5.setError("Kindly enter valid author name");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (spublicationdate5.length() < 2) {
                                                                                            errorflag = 5;
                                                                                            publicationdateinput5.setError("Kindly select valid month,year");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (stitle6.length() < 2) {
                                                                                                errorflag = 6;
                                                                                                titleinput6.setError("Kindly enter valid title");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (spublication6.length() < 2) {
                                                                                                    errorflag = 6;
                                                                                                    publicationinput6.setError("Kindly enter valid publication");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sauthor6.length() < 2) {
                                                                                                        errorflag = 6;
                                                                                                        authorsinput6.setError("Kindly enter valid author name");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (spublicationdate6.length() < 2) {
                                                                                                            errorflag = 6;
                                                                                                            publicationdateinput6.setError("Kindly select valid month,year");
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (publicationcount == 6) {
            if (stitle1.length() < 2) {
                errorflag = 1;
                titleinput1.setError("Kindly enter valid title");
            } else {
                errorflag = 0;
                if (spublication1.length() < 2) {
                    errorflag = 1;
                    publicationinput1.setError("Kindly enter valid publication");
                } else {
                    errorflag = 0;
                    if (sauthor1.length() < 2) {
                        errorflag = 1;
                        authorsinput1.setError("Kindly enter valid author name");
                    } else {
                        errorflag = 0;
                        if (spublicationdate1.length() < 2) {
                            errorflag = 1;
                            publicationdateinput1.setError("Kindly select valid month,year");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 2) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (spublication2.length() < 2) {
                                    errorflag = 2;
                                    publicationinput2.setError("Kindly enter valid publication");
                                } else {
                                    errorflag = 0;
                                    if (sauthor2.length() < 2) {
                                        errorflag = 2;
                                        authorsinput2.setError("Kindly enter valid author name");
                                    } else {
                                        errorflag = 0;
                                        if (spublicationdate2.length() < 2) {
                                            errorflag = 2;
                                            publicationdateinput2.setError("Kindly select valid month,year");
                                        } else {
                                            errorflag = 0;
                                            if (stitle3.length() < 2) {
                                                errorflag = 3;
                                                titleinput3.setError("Kindly enter valid title");
                                            } else {
                                                errorflag = 0;
                                                if (spublication3.length() < 2) {
                                                    errorflag = 3;
                                                    publicationinput3.setError("Kindly enter valid publication");
                                                } else {
                                                    errorflag = 0;
                                                    if (sauthor3.length() < 2) {
                                                        errorflag = 3;
                                                        authorsinput3.setError("Kindly enter valid author name");
                                                    } else {
                                                        errorflag = 0;
                                                        if (spublicationdate3.length() < 2) {
                                                            errorflag = 3;
                                                            publicationdateinput3.setError("Kindly select valid month,year");
                                                        } else {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 2) {
                                                                errorflag = 4;
                                                                titleinput4.setError("Kindly enter valid title");
                                                            } else {
                                                                errorflag = 0;
                                                                if (spublication4.length() < 2) {
                                                                    errorflag = 4;
                                                                    publicationinput4.setError("Kindly enter valid publication");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sauthor4.length() < 2) {
                                                                        errorflag = 4;
                                                                        authorsinput4.setError("Kindly enter valid author name");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (spublicationdate4.length() < 2) {
                                                                            errorflag = 4;
                                                                            publicationdateinput4.setError("Kindly select valid month,year");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 2) {
                                                                                errorflag = 5;
                                                                                titleinput5.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (spublication5.length() < 2) {
                                                                                    errorflag = 5;
                                                                                    publicationinput5.setError("Kindly enter valid publication");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sauthor5.length() < 2) {
                                                                                        errorflag = 5;
                                                                                        authorsinput5.setError("Kindly enter valid author name");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (spublicationdate5.length() < 2) {
                                                                                            errorflag = 5;
                                                                                            publicationdateinput5.setError("Kindly select valid month,year");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (stitle6.length() < 2) {
                                                                                                errorflag = 6;
                                                                                                titleinput6.setError("Kindly enter valid title");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (spublication6.length() < 2) {
                                                                                                    errorflag = 6;
                                                                                                    publicationinput6.setError("Kindly enter valid publication");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sauthor6.length() < 2) {
                                                                                                        errorflag = 6;
                                                                                                        authorsinput6.setError("Kindly enter valid author name");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (spublicationdate6.length() < 2) {
                                                                                                            errorflag = 6;
                                                                                                            publicationdateinput6.setError("Kindly select valid month,year");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (stitle7.length() < 2) {
                                                                                                                errorflag = 7;
                                                                                                                titleinput7.setError("Kindly enter valid title");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (spublication7.length() < 2) {
                                                                                                                    errorflag = 7;
                                                                                                                    publicationinput7.setError("Kindly enter valid publication");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sauthor7.length() < 2) {
                                                                                                                        errorflag = 7;
                                                                                                                        authorsinput7.setError("Kindly enter valid author name");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (spublicationdate7.length() < 2) {
                                                                                                                            errorflag = 7;
                                                                                                                            publicationdateinput7.setError("Kindly select valid month,year");
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (publicationcount == 7) {
            if (stitle1.length() < 2) {
                errorflag = 1;
                titleinput1.setError("Kindly enter valid title");
            } else {
                errorflag = 0;
                if (spublication1.length() < 2) {
                    errorflag = 1;
                    publicationinput1.setError("Kindly enter valid publication");
                } else {
                    errorflag = 0;
                    if (sauthor1.length() < 2) {
                        errorflag = 1;
                        authorsinput1.setError("Kindly enter valid author name");
                    } else {
                        errorflag = 0;
                        if (spublicationdate1.length() < 2) {
                            errorflag = 1;
                            publicationdateinput1.setError("Kindly select valid month,year");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 2) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (spublication2.length() < 2) {
                                    errorflag = 2;
                                    publicationinput2.setError("Kindly enter valid publication");
                                } else {
                                    errorflag = 0;
                                    if (sauthor2.length() < 2) {
                                        errorflag = 2;
                                        authorsinput2.setError("Kindly enter valid author name");
                                    } else {
                                        errorflag = 0;
                                        if (spublicationdate2.length() < 2) {
                                            errorflag = 2;
                                            publicationdateinput2.setError("Kindly select valid month,year");
                                        } else {
                                            errorflag = 0;
                                            if (stitle3.length() < 2) {
                                                errorflag = 3;
                                                titleinput3.setError("Kindly enter valid title");
                                            } else {
                                                errorflag = 0;
                                                if (spublication3.length() < 2) {
                                                    errorflag = 3;
                                                    publicationinput3.setError("Kindly enter valid publication");
                                                } else {
                                                    errorflag = 0;
                                                    if (sauthor3.length() < 2) {
                                                        errorflag = 3;
                                                        authorsinput3.setError("Kindly enter valid author name");
                                                    } else {
                                                        errorflag = 0;
                                                        if (spublicationdate3.length() < 2) {
                                                            errorflag = 3;
                                                            publicationdateinput3.setError("Kindly select valid month,year");
                                                        } else {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 2) {
                                                                errorflag = 4;
                                                                titleinput4.setError("Kindly enter valid title");
                                                            } else {
                                                                errorflag = 0;
                                                                if (spublication4.length() < 2) {
                                                                    errorflag = 4;
                                                                    publicationinput4.setError("Kindly enter valid publication");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sauthor4.length() < 2) {
                                                                        errorflag = 4;
                                                                        authorsinput4.setError("Kindly enter valid author name");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (spublicationdate4.length() < 2) {
                                                                            errorflag = 4;
                                                                            publicationdateinput4.setError("Kindly select valid month,year");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 2) {
                                                                                errorflag = 5;
                                                                                titleinput5.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (spublication5.length() < 2) {
                                                                                    errorflag = 5;
                                                                                    publicationinput5.setError("Kindly enter valid publication");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sauthor5.length() < 2) {
                                                                                        errorflag = 5;
                                                                                        authorsinput5.setError("Kindly enter valid author name");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (spublicationdate5.length() < 2) {
                                                                                            errorflag = 5;
                                                                                            publicationdateinput5.setError("Kindly select valid month,year");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (stitle6.length() < 2) {
                                                                                                errorflag = 6;
                                                                                                titleinput6.setError("Kindly enter valid title");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (spublication6.length() < 2) {
                                                                                                    errorflag = 6;
                                                                                                    publicationinput6.setError("Kindly enter valid publication");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sauthor6.length() < 2) {
                                                                                                        errorflag = 6;
                                                                                                        authorsinput6.setError("Kindly enter valid author name");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (spublicationdate6.length() < 2) {
                                                                                                            errorflag = 6;
                                                                                                            publicationdateinput6.setError("Kindly select valid month,year");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (stitle7.length() < 2) {
                                                                                                                errorflag = 7;
                                                                                                                titleinput7.setError("Kindly enter valid title");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (spublication7.length() < 2) {
                                                                                                                    errorflag = 7;
                                                                                                                    publicationinput7.setError("Kindly enter valid publication");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sauthor7.length() < 2) {
                                                                                                                        errorflag = 7;
                                                                                                                        authorsinput7.setError("Kindly enter valid author name");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (spublicationdate7.length() < 2) {
                                                                                                                            errorflag = 7;
                                                                                                                            publicationdateinput7.setError("Kindly select valid month,year");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (stitle8.length() < 2) {
                                                                                                                                errorflag = 8;
                                                                                                                                titleinput8.setError("Kindly enter valid title");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (spublication8.length() < 2) {
                                                                                                                                    errorflag = 8;
                                                                                                                                    publicationinput8.setError("Kindly enter valid publication");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (sauthor8.length() < 2) {
                                                                                                                                        errorflag = 8;
                                                                                                                                        authorsinput8.setError("Kindly enter valid author name");
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (spublicationdate8.length() < 2) {
                                                                                                                                            errorflag = 8;
                                                                                                                                            publicationdateinput8.setError("Kindly select valid month,year");
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (publicationcount == 8) {
            if (stitle1.length() < 2) {
                errorflag = 1;
                titleinput1.setError("Kindly enter valid title");
            } else {
                errorflag = 0;
                if (spublication1.length() < 2) {
                    errorflag = 1;
                    publicationinput1.setError("Kindly enter valid publication");
                } else {
                    errorflag = 0;
                    if (sauthor1.length() < 2) {
                        errorflag = 1;
                        authorsinput1.setError("Kindly enter valid author name");
                    } else {
                        errorflag = 0;
                        if (spublicationdate1.length() < 2) {
                            errorflag = 1;
                            publicationdateinput1.setError("Kindly select valid month,year");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 2) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (spublication2.length() < 2) {
                                    errorflag = 2;
                                    publicationinput2.setError("Kindly enter valid publication");
                                } else {
                                    errorflag = 0;
                                    if (sauthor2.length() < 2) {
                                        errorflag = 2;
                                        authorsinput2.setError("Kindly enter valid author name");
                                    } else {
                                        errorflag = 0;
                                        if (spublicationdate2.length() < 2) {
                                            errorflag = 2;
                                            publicationdateinput2.setError("Kindly select valid month,year");
                                        } else {
                                            errorflag = 0;
                                            if (stitle3.length() < 2) {
                                                errorflag = 3;
                                                titleinput3.setError("Kindly enter valid title");
                                            } else {
                                                errorflag = 0;
                                                if (spublication3.length() < 2) {
                                                    errorflag = 3;
                                                    publicationinput3.setError("Kindly enter valid publication");
                                                } else {
                                                    errorflag = 0;
                                                    if (sauthor3.length() < 2) {
                                                        errorflag = 3;
                                                        authorsinput3.setError("Kindly enter valid author name");
                                                    } else {
                                                        errorflag = 0;
                                                        if (spublicationdate3.length() < 2) {
                                                            errorflag = 3;
                                                            publicationdateinput3.setError("Kindly select valid month,year");
                                                        } else {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 2) {
                                                                errorflag = 4;
                                                                titleinput4.setError("Kindly enter valid title");
                                                            } else {
                                                                errorflag = 0;
                                                                if (spublication4.length() < 2) {
                                                                    errorflag = 4;
                                                                    publicationinput4.setError("Kindly enter valid publication");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sauthor4.length() < 2) {
                                                                        errorflag = 4;
                                                                        authorsinput4.setError("Kindly enter valid author name");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (spublicationdate4.length() < 2) {
                                                                            errorflag = 4;
                                                                            publicationdateinput4.setError("Kindly select valid month,year");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 2) {
                                                                                errorflag = 5;
                                                                                titleinput5.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (spublication5.length() < 2) {
                                                                                    errorflag = 5;
                                                                                    publicationinput5.setError("Kindly enter valid publication");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sauthor5.length() < 2) {
                                                                                        errorflag = 5;
                                                                                        authorsinput5.setError("Kindly enter valid author name");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (spublicationdate5.length() < 2) {
                                                                                            errorflag = 5;
                                                                                            publicationdateinput5.setError("Kindly select valid month,year");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (stitle6.length() < 2) {
                                                                                                errorflag = 6;
                                                                                                titleinput6.setError("Kindly enter valid title");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (spublication6.length() < 2) {
                                                                                                    errorflag = 6;
                                                                                                    publicationinput6.setError("Kindly enter valid publication");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sauthor6.length() < 2) {
                                                                                                        errorflag = 6;
                                                                                                        authorsinput6.setError("Kindly enter valid author name");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (spublicationdate6.length() < 2) {
                                                                                                            errorflag = 6;
                                                                                                            publicationdateinput6.setError("Kindly select valid month,year");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (stitle7.length() < 2) {
                                                                                                                errorflag = 7;
                                                                                                                titleinput7.setError("Kindly enter valid title");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (spublication7.length() < 2) {
                                                                                                                    errorflag = 7;
                                                                                                                    publicationinput7.setError("Kindly enter valid publication");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sauthor7.length() < 2) {
                                                                                                                        errorflag = 7;
                                                                                                                        authorsinput7.setError("Kindly enter valid author name");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (spublicationdate7.length() < 2) {
                                                                                                                            errorflag = 7;
                                                                                                                            publicationdateinput7.setError("Kindly select valid month,year");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (stitle8.length() < 2) {
                                                                                                                                errorflag = 8;
                                                                                                                                titleinput8.setError("Kindly enter valid title");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (spublication8.length() < 2) {
                                                                                                                                    errorflag = 8;
                                                                                                                                    publicationinput8.setError("Kindly enter valid publication");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (sauthor8.length() < 2) {
                                                                                                                                        errorflag = 8;
                                                                                                                                        authorsinput8.setError("Kindly enter valid author name");
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (spublicationdate8.length() < 2) {
                                                                                                                                            errorflag = 8;
                                                                                                                                            publicationdateinput8.setError("Kindly select valid month,year");
                                                                                                                                        } else {
                                                                                                                                            errorflag = 0;
                                                                                                                                            if (stitle9.length() < 2) {
                                                                                                                                                errorflag = 9;
                                                                                                                                                titleinput9.setError("Kindly enter valid title");
                                                                                                                                            } else {
                                                                                                                                                errorflag = 0;
                                                                                                                                                if (spublication9.length() < 2) {
                                                                                                                                                    errorflag = 9;
                                                                                                                                                    publicationinput9.setError("Kindly enter valid publication");
                                                                                                                                                } else {
                                                                                                                                                    errorflag = 0;
                                                                                                                                                    if (sauthor9.length() < 2) {
                                                                                                                                                        errorflag = 9;
                                                                                                                                                        authorsinput9.setError("Kindly enter valid author name");
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (spublicationdate9.length() < 2) {
                                                                                                                                                            errorflag = 9;
                                                                                                                                                            publicationdateinput9.setError("Kindly select valid month,year");
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (publicationcount == 9) {
            if (stitle1.length() < 2) {
                errorflag = 1;
                titleinput1.setError("Kindly enter valid title");
            } else {
                errorflag = 0;
                if (spublication1.length() < 2) {
                    errorflag = 1;
                    publicationinput1.setError("Kindly enter valid publication");
                } else {
                    errorflag = 0;
                    if (sauthor1.length() < 2) {
                        errorflag = 1;
                        authorsinput1.setError("Kindly enter valid author name");
                    } else {
                        errorflag = 0;
                        if (spublicationdate1.length() < 2) {
                            errorflag = 1;
                            publicationdateinput1.setError("Kindly select valid month,year");
                        } else {
                            errorflag = 0;
                            if (stitle2.length() < 2) {
                                errorflag = 2;
                                titleinput2.setError("Kindly enter valid title");
                            } else {
                                errorflag = 0;
                                if (spublication2.length() < 2) {
                                    errorflag = 2;
                                    publicationinput2.setError("Kindly enter valid publication");
                                } else {
                                    errorflag = 0;
                                    if (sauthor2.length() < 2) {
                                        errorflag = 2;
                                        authorsinput2.setError("Kindly enter valid author name");
                                    } else {
                                        errorflag = 0;
                                        if (spublicationdate2.length() < 2) {
                                            errorflag = 2;
                                            publicationdateinput2.setError("Kindly select valid month,year");
                                        } else {
                                            errorflag = 0;
                                            if (stitle3.length() < 2) {
                                                errorflag = 3;
                                                titleinput3.setError("Kindly enter valid title");
                                            } else {
                                                errorflag = 0;
                                                if (spublication3.length() < 2) {
                                                    errorflag = 3;
                                                    publicationinput3.setError("Kindly enter valid publication");
                                                } else {
                                                    errorflag = 0;
                                                    if (sauthor3.length() < 2) {
                                                        errorflag = 3;
                                                        authorsinput3.setError("Kindly enter valid author name");
                                                    } else {
                                                        errorflag = 0;
                                                        if (spublicationdate3.length() < 2) {
                                                            errorflag = 3;
                                                            publicationdateinput3.setError("Kindly select valid month,year");
                                                        } else {
                                                            errorflag = 0;
                                                            if (stitle4.length() < 2) {
                                                                errorflag = 4;
                                                                titleinput4.setError("Kindly enter valid title");
                                                            } else {
                                                                errorflag = 0;
                                                                if (spublication4.length() < 2) {
                                                                    errorflag = 4;
                                                                    publicationinput4.setError("Kindly enter valid publication");
                                                                } else {
                                                                    errorflag = 0;
                                                                    if (sauthor4.length() < 2) {
                                                                        errorflag = 4;
                                                                        authorsinput4.setError("Kindly enter valid author name");
                                                                    } else {
                                                                        errorflag = 0;
                                                                        if (spublicationdate4.length() < 2) {
                                                                            errorflag = 4;
                                                                            publicationdateinput4.setError("Kindly select valid month,year");
                                                                        } else {
                                                                            errorflag = 0;
                                                                            if (stitle5.length() < 2) {
                                                                                errorflag = 5;
                                                                                titleinput5.setError("Kindly enter valid title");
                                                                            } else {
                                                                                errorflag = 0;
                                                                                if (spublication5.length() < 2) {
                                                                                    errorflag = 5;
                                                                                    publicationinput5.setError("Kindly enter valid publication");
                                                                                } else {
                                                                                    errorflag = 0;
                                                                                    if (sauthor5.length() < 2) {
                                                                                        errorflag = 5;
                                                                                        authorsinput5.setError("Kindly enter valid author name");
                                                                                    } else {
                                                                                        errorflag = 0;
                                                                                        if (spublicationdate5.length() < 2) {
                                                                                            errorflag = 5;
                                                                                            publicationdateinput5.setError("Kindly select valid month,year");
                                                                                        } else {
                                                                                            errorflag = 0;
                                                                                            if (stitle6.length() < 2) {
                                                                                                errorflag = 6;
                                                                                                titleinput6.setError("Kindly enter valid title");
                                                                                            } else {
                                                                                                errorflag = 0;
                                                                                                if (spublication6.length() < 2) {
                                                                                                    errorflag = 6;
                                                                                                    publicationinput6.setError("Kindly enter valid publication");
                                                                                                } else {
                                                                                                    errorflag = 0;
                                                                                                    if (sauthor6.length() < 2) {
                                                                                                        errorflag = 6;
                                                                                                        authorsinput6.setError("Kindly enter valid author name");
                                                                                                    } else {
                                                                                                        errorflag = 0;
                                                                                                        if (spublicationdate6.length() < 2) {
                                                                                                            errorflag = 6;
                                                                                                            publicationdateinput6.setError("Kindly select valid month,year");
                                                                                                        } else {
                                                                                                            errorflag = 0;
                                                                                                            if (stitle7.length() < 2) {
                                                                                                                errorflag = 7;
                                                                                                                titleinput7.setError("Kindly enter valid title");
                                                                                                            } else {
                                                                                                                errorflag = 0;
                                                                                                                if (spublication7.length() < 2) {
                                                                                                                    errorflag = 7;
                                                                                                                    publicationinput7.setError("Kindly enter valid publication");
                                                                                                                } else {
                                                                                                                    errorflag = 0;
                                                                                                                    if (sauthor7.length() < 2) {
                                                                                                                        errorflag = 7;
                                                                                                                        authorsinput7.setError("Kindly enter valid author name");
                                                                                                                    } else {
                                                                                                                        errorflag = 0;
                                                                                                                        if (spublicationdate7.length() < 2) {
                                                                                                                            errorflag = 7;
                                                                                                                            publicationdateinput7.setError("Kindly select valid month,year");
                                                                                                                        } else {
                                                                                                                            errorflag = 0;
                                                                                                                            if (stitle8.length() < 2) {
                                                                                                                                errorflag = 8;
                                                                                                                                titleinput8.setError("Kindly enter valid title");
                                                                                                                            } else {
                                                                                                                                errorflag = 0;
                                                                                                                                if (spublication8.length() < 2) {
                                                                                                                                    errorflag = 8;
                                                                                                                                    publicationinput8.setError("Kindly enter valid publication");
                                                                                                                                } else {
                                                                                                                                    errorflag = 0;
                                                                                                                                    if (sauthor8.length() < 2) {
                                                                                                                                        errorflag = 8;
                                                                                                                                        authorsinput8.setError("Kindly enter valid author name");
                                                                                                                                    } else {
                                                                                                                                        errorflag = 0;
                                                                                                                                        if (spublicationdate8.length() < 2) {
                                                                                                                                            errorflag = 8;
                                                                                                                                            publicationdateinput8.setError("Kindly select valid month,year");
                                                                                                                                        } else {
                                                                                                                                            errorflag = 0;
                                                                                                                                            if (stitle9.length() < 2) {
                                                                                                                                                errorflag = 9;
                                                                                                                                                titleinput9.setError("Kindly enter valid title");
                                                                                                                                            } else {
                                                                                                                                                errorflag = 0;
                                                                                                                                                if (spublication9.length() < 2) {
                                                                                                                                                    errorflag = 9;
                                                                                                                                                    publicationinput9.setError("Kindly enter valid publication");
                                                                                                                                                } else {
                                                                                                                                                    errorflag = 0;
                                                                                                                                                    if (sauthor9.length() < 2) {
                                                                                                                                                        errorflag = 9;
                                                                                                                                                        authorsinput9.setError("Kindly enter valid author name");
                                                                                                                                                    } else {
                                                                                                                                                        errorflag = 0;
                                                                                                                                                        if (spublicationdate9.length() < 2) {
                                                                                                                                                            errorflag = 9;
                                                                                                                                                            publicationdateinput9.setError("Kindly select valid month,year");
                                                                                                                                                        } else {
                                                                                                                                                            errorflag = 0;
                                                                                                                                                            if (stitle10.length() < 2) {
                                                                                                                                                                errorflag = 10;
                                                                                                                                                                titleinput10.setError("Kindly enter valid title");
                                                                                                                                                            } else {
                                                                                                                                                                errorflag = 0;
                                                                                                                                                                if (spublication10.length() < 2) {
                                                                                                                                                                    errorflag = 10;
                                                                                                                                                                    publicationinput10.setError("Kindly enter valid publication");
                                                                                                                                                                } else {
                                                                                                                                                                    errorflag = 0;
                                                                                                                                                                    if (sauthor10.length() < 2) {
                                                                                                                                                                        errorflag = 10;
                                                                                                                                                                        authorsinput10.setError("Kindly enter valid author name");
                                                                                                                                                                    } else {
                                                                                                                                                                        errorflag = 0;
                                                                                                                                                                        if (spublicationdate10.length() < 2) {
                                                                                                                                                                            errorflag = 10;
                                                                                                                                                                            publicationdateinput10.setError("Kindly select valid month,year");
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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

                Publications obj1 = new Publications(stitle1, spublication1, sauthor1, spublicationdate1, surl1, sdescription1);
                Publications obj2 = new Publications(stitle2, spublication2, sauthor2, spublicationdate2, surl2, sdescription2);
                Publications obj3 = new Publications(stitle3, spublication3, sauthor3, spublicationdate3, surl3, sdescription3);
                Publications obj4 = new Publications(stitle4, spublication4, sauthor4, spublicationdate4, surl4, sdescription4);
                Publications obj5 = new Publications(stitle5, spublication5, sauthor5, spublicationdate5, surl5, sdescription5);
                Publications obj6 = new Publications(stitle6, spublication6, sauthor6, spublicationdate6, surl6, sdescription6);
                Publications obj7 = new Publications(stitle7, spublication7, sauthor7, spublicationdate7, surl7, sdescription7);
                Publications obj8 = new Publications(stitle8, spublication8, sauthor8, spublicationdate8, surl8, sdescription8);
                Publications obj9 = new Publications(stitle9, spublication9, sauthor9, spublicationdate9, surl9, sdescription9);
                Publications obj10 = new Publications(stitle10, spublication10, sauthor10, spublicationdate10, surl10, sdescription10);

                publicationsList.add(obj1);
                publicationsList.add(obj2);
                publicationsList.add(obj3);
                publicationsList.add(obj4);
                publicationsList.add(obj5);
                publicationsList.add(obj6);
                publicationsList.add(obj7);
                publicationsList.add(obj8);
                publicationsList.add(obj9);
                publicationsList.add(obj10);

                String encObjString = OtoString(publicationsList, MySharedPreferencesManager.getDigest1(MyProfilePublications.this), MySharedPreferencesManager.getDigest2(MyProfilePublications.this));

                new SavePublications().execute(encObjString);

            } catch (Exception e) {
                Toast.makeText(MyProfilePublications.this, e.getMessage(), Toast.LENGTH_LONG).show();
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

        if (edittedFlag != 0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfilePublications.super.onBackPressed();
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
            MyProfilePublications.super.onBackPressed();
    }

    void showDateDialog(final EditText id) {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyProfilePublications.this);
        LayoutInflater inflater = MyProfilePublications.this.getLayoutInflater();
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
//
        Calendar currentCalendar = Calendar.getInstance();
        for (int i = 1975; i <= currentCalendar.get(Calendar.YEAR); i++)
            yearList.add("" + i);


        monthView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePublications.this));
        monthView.setWheelData(monthList);
        yearView.setWheelAdapter(new ArrayWheelAdapter(MyProfilePublications.this));
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
                if (selectedYearInterger > currentDatecalendar.get(Calendar.YEAR) || monthPosition > currentDatecalendar.get(Calendar.MONTH)) {
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

    void setMonthYear(EditText id, String selectedMonth, String selectedYear, int isInvalidDate) {
        if (isInvalidDate == 1) {
            id.setText("");
            id.setError("Kindly select valid month,year");
            Toast.makeText(this, "Kindly select valid month,year", Toast.LENGTH_SHORT).show();
        } else
            id.setText(selectedMonth + ", " + selectedYear);
    }

    class SavePublications extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("d", param[0]));       //0

            json = jParser.makeHttpRequest(MyConstants.url_savepublications, "GET", params);
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
                Toast.makeText(MyProfilePublications.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();

                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("hr"))
                    setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("admin"))
                    setResult(AdminActivity.ADMIN_DATA_CHANGE_RESULT_CODE);


                s.setPubtitle1(stitle1);
                s.setPublication1(spublication1);
                s.setAuthor1(sauthor1);
                s.setPublicationdate1(spublicationdate1);
                s.setPuburl1(surl1);
                s.setPubdescription1(sdescription1);
                s.setPubtitle2(stitle2);
                s.setPublication2(spublication2);
                s.setAuthor2(sauthor2);
                s.setPublicationdate2(spublicationdate2);
                s.setPuburl2(surl2);
                s.setPubdescription2(sdescription2);
                s.setPubtitle3(stitle3);
                s.setPublication3(spublication3);
                s.setAuthor3(sauthor3);
                s.setPublicationdate3(spublicationdate3);
                s.setPuburl3(surl3);
                s.setPubdescription3(sdescription3);
                s.setPubtitle4(stitle4);
                s.setPublication4(spublication4);
                s.setAuthor4(sauthor4);
                s.setPublicationdate4(spublicationdate4);
                s.setPuburl4(surl4);
                s.setPubdescription4(sdescription4);
                s.setPubtitle5(stitle5);
                s.setPublication5(spublication5);
                s.setAuthor5(sauthor5);
                s.setPublicationdate5(spublicationdate5);
                s.setPuburl5(surl5);
                s.setPubdescription5(sdescription5);
                s.setPubtitle6(stitle6);
                s.setPublication6(spublication6);
                s.setAuthor6(sauthor6);
                s.setPublicationdate6(spublicationdate6);
                s.setPuburl6(surl6);
                s.setPubdescription6(sdescription6);
                s.setPubtitle7(stitle7);
                s.setPublication7(spublication7);
                s.setAuthor7(sauthor7);
                s.setPublicationdate7(spublicationdate7);
                s.setPuburl7(surl7);
                s.setPubdescription7(sdescription7);
                s.setPubtitle8(stitle8);
                s.setPublication8(spublication8);
                s.setAuthor8(sauthor8);
                s.setPublicationdate8(spublicationdate8);
                s.setPuburl8(surl8);
                s.setPubdescription8(sdescription8);
                s.setPubtitle9(stitle9);
                s.setPublication9(spublication9);
                s.setAuthor9(sauthor9);
                s.setPublicationdate9(spublicationdate9);
                s.setPuburl9(surl9);
                s.setPubdescription9(sdescription9);
                s.setPubtitle10(stitle10);
                s.setPublication10(spublication10);
                s.setAuthor10(sauthor10);
                s.setPublicationdate10(spublicationdate10);
                s.setPuburl10(surl10);
                s.setPubdescription10(sdescription10);


                MyProfilePublications.super.onBackPressed();
            } else
                Toast.makeText(MyProfilePublications.this, result, Toast.LENGTH_SHORT).show();

        }
    }

}
