package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;

public class EditPlacementHr extends AppCompatActivity {



    Toolbar toolbar;
    Vibrator myVib;
    String placementids[], placementcompanyname[], placementcpackage[], placementpost[], placementforwhichcourse[], placementforwhichstream[], placementvacancies[], placementlastdateofregistration[], placementdateofarrival[], placementbond[], placementnoofapti[], placementnooftechtest[], placementnoofgd[], placementnoofti[], placementnoofhri[], placementstdx[], placementstdxiiordiploma[], placementug[], placementpg[], placementuploadtime[], placementlastmodified[], placementuploadedby[], placementuploadedbyplain[], placemenforwhome[], placemenforwhomeplain[], placementnoofallowedliveatkt[], placementnoofalloweddeadatkt[], passingYear[], experience[];
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    int placemntscount = 0;
    boolean isFirstRunPlacement = true, isLastPageLoadedPlacement = false;
    int lastPageFlagPlacement = 0;
    int placementcount = 0;
    String placementreadstatus[];
    String[] uniqueUploadersPlacement;
    String[] uniqueUploadersEncPlacement;
    String lastupdatedPlacement[];
    int selectedPositions[];
    View[] selectedViews;
    PlacementEditData set1 = new PlacementEditData();
    private String plainusername, username = "", fname = "", mname = "", sname = "";
    private MaterialSearchView searchView;
    private int page_to_call_placement = 1;
    private List<RecyclerItemPlacement> itemListPlacement = new ArrayList<>();
    private RecyclerItemAdapterPlacement mAdapterPlacement;
    private RecyclerView recyclerViewPlacement;
    private int previousTotalPlacement = 0; // The total number of items in the dataset after the last load
    private boolean loadingPlacement = true; // True if we are still waiting for the last set of data to load.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_placement_hr);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Edit Placements</font>"));

        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);


        //init
        username = MySharedPreferencesManager.getUsername(this);
        String role = MySharedPreferencesManager.getRole(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);




        try {

            String plainusername = Decrypt(username, digest1, digest2);

        } catch (Exception e) {
        }

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);


        recyclerViewPlacement = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapterPlacement = new RecyclerItemAdapterPlacement(itemListPlacement);
        recyclerViewPlacement.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerPlacement = new LinearLayoutManager(this);
        recyclerViewPlacement.setLayoutManager(linearLayoutManagerPlacement);
        recyclerViewPlacement.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewPlacement.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlacement.setAdapter(mAdapterPlacement);


        recyclerViewPlacement.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewPlacement, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                RecyclerItemPlacement item = itemListPlacement.get(position);


                Intent i1 = new Intent(EditPlacementHr.this, EditPlacementMainHr.class);
                i1.putExtra("flag", "fromeditactivity");

//                    String st1=""+set1.getId();
//
                i1.putExtra("id", item.getId());
                i1.putExtra("passingyear", item.getPassingyear());
                i1.putExtra("experiences", item.getExperiences());


                set1.setId(item.getId());
                set1.setCompanyname(item.getCompanyname());
                set1.setCpackage(item.getCpackage());
                set1.setPost(item.getPost());
                set1.setForwhichcourse(item.getForwhichcourse());
                set1.setForwhichstream(item.getForwhichstream());
                set1.setVacancies(item.getVacancies());
                set1.setLastdateofregistration(item.getLastdateofregistration());
                set1.setDateofarrival(item.getDateofarrival());
                set1.setBond(item.getBond());
                set1.setSelectedcourceforplacement(item.getForwhichcourse());
                set1.setNoofapti(item.getNoofapti());
                set1.setNooftechtest(item.getNooftechtest());
                set1.setNoofgd(item.getNoofgd());
                set1.setNoofti(item.getNoofti());
                set1.setNoofhri(item.getNoofhri());
                set1.setStdx(item.getStdx());
                set1.setStdxiiordiploma(item.getStdxiiordiploma());
                set1.setUploadtime(item.getUploadtime());
                set1.setUg(item.getUg());
                set1.setPg(item.getPg());
                set1.setLastmodified(item.getLastmodified());
                set1.setUploadedby(item.getUploadedby());//
                Log.d("passing", "getPassingyear: " + item.getPassingyear());
                Log.d("passing", "getExperiences: " + item.getExperiences());
//                    set1.setForwhom(item.getForwhom());

                set1.setNoofallowedliveatkt(item.getNoofallowedliveatkt());
                set1.setNoofalloweddeadatkt(item.getNoofalloweddeadatkt());


                Log.d("Check2", "id: " + item.getId());
                Log.d("Check2", "Companyname: " + item.getCompanyname());
                Log.d("Check2", "Stdxiiordiploma: " + item.getStdxiiordiploma());
                Log.d("Check2", "Uploadedby: " + item.getUploadedby());
//                    Log.d("Check2" ,"forwhome: "+  item.getForwhom());

                Log.d("Check2", "ug: " + item.getUg());
                startActivity(i1);


            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));


        getPlacements();
    }

    void getPlacements() {
        previousTotalPlacement = 0;
        loadingPlacement = true;
        page_to_call_placement = 1;
        isFirstRunPlacement = true;
        isLastPageLoadedPlacement = false;
        lastPageFlagPlacement = 0;
        Log.d("PlacmentTesting", "previousTotalPlacement: " + previousTotalPlacement);
        Log.d("PlacmentTesting", "page_to_call_placement: " + page_to_call_placement);
        Log.d("PlacmentTesting", "lastPageFlagPlacement: " + lastPageFlagPlacement);
        new GetPlacements().execute();
    }

    void addPlacementdatatoAdapter() {
        if (isFirstRunPlacement) {
            itemListPlacement.clear();
            mAdapterPlacement.notifyDataSetChanged();
            isFirstRunPlacement = false;
        }
        if (!isLastPageLoadedPlacement) {
            for (int i = 0; i < placementcount; i++) {

                RecyclerItemPlacement item = null;


                for (int k = 0; k < uniqueUploadersPlacement.length; k++) {
                    if (placementuploadedbyplain[i].equals(uniqueUploadersPlacement[k])) {

                        if (lastupdatedPlacement[k] == null) {

//                                    public RecyclerItemPlacement(String id, String companyname, String cpackage, String post, String forwhichcourse,                                   String forwhichstream,     String vacancies,   String lastdateofregistration,     String dateofarrival,    String bond,        String noofapti,       String nooftechtest,       String noofgd,      String noofti,      String noofhri,      String stdx,   String stdxiiordiploma,      String ug,      String pg,     String uploadtime,       String lastmodified, String uploadedby, String noofallowedliveatkt, String noofalloweddeadatkt, String signature, String experiences, String passingyear, Context context, boolean isRead) {

                            item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i], "placeme", experience[i], passingYear[i], EditPlacementHr.this, true);

                            itemListPlacement.add(item);
                        } else {
                            item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i], lastupdatedPlacement[k], experience[i], passingYear[i], EditPlacementHr.this, true);
                            itemListPlacement.add(item);
                        }


                    }

                }
            }
        }

        selectedPositions = new int[placementcount];
        selectedViews = new View[placementcount];

        if (lastPageFlagPlacement == 1)
            isLastPageLoadedPlacement = true;
        mAdapterPlacement.notifyDataSetChanged();

    }

    class GetPlacements extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r = null;

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("p", page_to_call_placement + ""));  //1


            json = jParser.makeHttpRequest(Z.url_GetPlacementSentByHr, "GET", params);
            try {
                placementcount = Integer.parseInt(json.getString("count"));

                placementids = new String[placementcount];
                placementcompanyname = new String[placementcount];
                placementcpackage = new String[placementcount];
                placementpost = new String[placementcount];
                placementforwhichcourse = new String[placementcount];
                placementforwhichstream = new String[placementcount];
                placementvacancies = new String[placementcount];
                placementlastdateofregistration = new String[placementcount];
                placementdateofarrival = new String[placementcount];
                placementbond = new String[placementcount];
                placementnoofapti = new String[placementcount];
                placementnooftechtest = new String[placementcount];
                placementnoofgd = new String[placementcount];
                placementnoofti = new String[placementcount];
                placementnoofhri = new String[placementcount];
                placementstdx = new String[placementcount];
                placementstdxiiordiploma = new String[placementcount];
                placementug = new String[placementcount];
                placementpg = new String[placementcount];
                placementuploadtime = new String[placementcount];
                placementlastmodified = new String[placementcount];
                placementuploadedby = new String[placementcount];
                placementuploadedbyplain = new String[placementcount];
                placementnoofallowedliveatkt = new String[placementcount];
                placementnoofalloweddeadatkt = new String[placementcount];

                passingYear = new String[placementcount];
                experience = new String[placementcount];


                for (int i = 0; i < placementcount; i++) {

                    placementids[i] = json.getString("id" + i);
                    placementcompanyname[i] = json.getString("companyname" + i);
                    placementcpackage[i] = json.getString("package" + i);
                    placementpost[i] = json.getString("post" + i);
                    placementforwhichcourse[i] = json.getString("forwhichcourse" + i);
                    placementforwhichstream[i] = json.getString("forwhichstream" + i);
                    placementvacancies[i] = json.getString("vacancies" + i);
                    placementlastdateofregistration[i] = json.getString("lastdateofregistration" + i);
                    placementdateofarrival[i] = json.getString("dateofarrival" + i);
                    placementbond[i] = json.getString("bond" + i);
                    placementnoofapti[i] = json.getString("noofapti" + i);
                    placementnooftechtest[i] = json.getString("nooftechtest" + i);
                    placementnoofgd[i] = json.getString("noofgd" + i);
                    placementnoofti[i] = json.getString("noofti" + i);
                    placementnoofhri[i] = json.getString("noofhri" + i);
                    placementstdx[i] = json.getString("stdx" + i);
                    placementstdxiiordiploma[i] = json.getString("stdxiiordiploma" + i);
                    placementug[i] = json.getString("ug" + i);
                    placementpg[i] = json.getString("pg" + i);
                    placementuploadtime[i] = json.getString("uploadtime" + i);
                    placementlastmodified[i] = json.getString("lastmodified" + i);
                    placementuploadedby[i] = json.getString("uploadedby" + i);
                    placementnoofallowedliveatkt[i] = json.getString("noofallowedliveatkt" + i);
                    placementnoofalloweddeadatkt[i] = json.getString("noofalloweddeadatkt" + i);
//
//                    passingYear[i] = json.getString("passingyear" + i);
//                    experience[i] = json.getString("experience" + i);


                    Log.d("PlacmentTesting", "passingyear: id" + json.getString("id" + i));
//                    Log.d("PlacmentTesting", "passingyear:comp name " + json.getString("companyname" + i));
//                    Log.d("PlacmentTesting", "passingyear: package" + json.getString("package" + i));
//                    Log.d("PlacmentTesting", "passingyear:post " + json.getString("post" + i));
//                    Log.d("PlacmentTesting", "passingyear: forwhichcourse" + json.getString("forwhichcourse" + i));
//                    Log.d("PlacmentTesting", "passingyear: lastdateofregistration" + json.getString("lastdateofregistration" + i));
//                    Log.d("PlacmentTesting", "passingyear: dateofarrival" + json.getString("dateofarrival" + i));
//                    Log.d("PlacmentTesting", "passingyear: bond" + json.getString("bond" + i));
//                    Log.d("PlacmentTesting", "passingyear: noofapti" + json.getString("noofapti" + i));
//                    Log.d("PlacmentTesting", "passingyear: noofgd" + json.getString("noofgd" + i));
//                    Log.d("PlacmentTesting", "passingyear: noofti" + json.getString("noofti" + i));
//                    Log.d("PlacmentTesting", "passingyear: stdx" + json.getString("stdx" + i));
//                    Log.d("PlacmentTesting", "passingyear: stdxiiordiploma" + json.getString("stdxiiordiploma" + i));
//                    Log.d("PlacmentTesting", "passingyear: ug" + json.getString("ug" + i));
//                    Log.d("PlacmentTesting", "passingyear: pg" + json.getString("pg" + i));
//                    Log.d("PlacmentTesting", "passingyear: lastmodified" + json.getString("lastmodified" + i));
//                    Log.d("PlacmentTesting", "passingyear: noofallowedliveatkt" + json.getString("noofallowedliveatkt" + i));
//                    Log.d("PlacmentTesting", "passingyear: noofalloweddeadatkt" + json.getString("noofalloweddeadatkt" + i));
//                    Log.d("PlacmentTesting", "passingyear: experiences" + json.getString("experiences" + i));
//                    Log.d("PlacmentTesting", "passingyear: passingyear" + json.getString("passingyear" + i));

                }


            } catch (Exception e) {
            }

            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            for (int i = 0; i < placementcount; i++)
                try {
                    Log.d("PlacmentTesting", "count: " + placementcount);
//                    if(placementcompanyname[i]!=null)
//                    {
//                        byte[] placementcompanynameEncryptedBytes=SimpleBase64Encoder.decode(placementcompanyname[i]);
//                        byte[] placementcompanynameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementcompanynameEncryptedBytes);
//                        placementcompanyname[i]=new String(placementcompanynameDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementcompanyname"+"["+i+"] "+placementcompanyname[i]);
//
//                    }
//                    if(placementcpackage[i]!=null)
//                    {
//                        byte[] placementcpackageEncryptedBytes=SimpleBase64Encoder.decode(placementcpackage[i]);
//                        byte[] placementcpackageDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementcpackageEncryptedBytes);
//                        placementcpackage[i]=new String(placementcpackageDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementcpackage"+"["+i+"] "+placementcpackage[i]);
//
//                    }
//                    if(placementpost[i]!=null)
//                    {
//                        byte[] placementpostEncryptedBytes=SimpleBase64Encoder.decode(placementpost[i]);
//                        byte[] placementpostDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementpostEncryptedBytes);
//                        placementpost[i]=new String(placementpostDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementpost"+"["+i+"] "+placementpost[i]);
//
//                    }
//                    if(placementforwhichcourse[i]!=null)
//                    {
//                        byte[] placementforwhichcourseEncryptedBytes=SimpleBase64Encoder.decode(placementforwhichcourse[i]);
//                        byte[] placementforwhichcourseDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementforwhichcourseEncryptedBytes);
//                        placementforwhichcourse[i]=new String(placementforwhichcourseDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementforwhichcourse"+"["+i+"] "+placementforwhichcourse[i]);
//
//                    }
//                    if(placementforwhichstream[i]!=null)
//                    {
//                        byte[] placementforwhichstreamEncryptedBytes=SimpleBase64Encoder.decode(placementforwhichstream[i]);
//                        byte[] placementforwhichstreamDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementforwhichstreamEncryptedBytes);
//                        placementforwhichstream[i]=new String(placementforwhichstreamDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementforwhichstream"+"["+i+"] "+placementforwhichstream[i]);
//
//                    }
//                    if(placementvacancies[i]!=null)
//                    {
//                        byte[] placementvacanciesEncryptedBytes=SimpleBase64Encoder.decode(placementvacancies[i]);
//                        byte[] placementvacanciesDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementvacanciesEncryptedBytes);
//                        placementvacancies[i]=new String(placementvacanciesDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementvacancies"+"["+i+"] "+placementvacancies[i]);
//
//                    }
//                    if(placementlastdateofregistration[i]!=null)
//                    {
//                        byte[] placementlastdateofregistrationEncryptedBytes=SimpleBase64Encoder.decode(placementlastdateofregistration[i]);
//                        byte[] placementlastdateofregistrationDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementlastdateofregistrationEncryptedBytes);
//                        placementlastdateofregistration[i]=new String(placementlastdateofregistrationDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementlastdateofregistration"+"["+i+"] "+placementlastdateofregistration[i]);
//
//                    }
//                    if(placementdateofarrival[i]!=null)
//                    {
//                        byte[] placementdateofarrivalEncryptedBytes=SimpleBase64Encoder.decode(placementdateofarrival[i]);
//                        byte[] placementdateofarrivalDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementdateofarrivalEncryptedBytes);
//                        placementdateofarrival[i]=new String(placementdateofarrivalDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementdateofarrival"+"["+i+"] "+placementdateofarrival[i]);
//
//                    }
//                    if(placementbond[i]!=null)
//                    {
//                        byte[] placementbondEncryptedBytes=SimpleBase64Encoder.decode(placementbond[i]);
//                        byte[] placementbondDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementbondEncryptedBytes);
//                        placementbond[i]=new String(placementbondDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementbond"+"["+i+"] "+placementbond[i]);
//
//                    }
//                    if(placementnoofapti[i]!=null)
//                    {
//                        byte[] placementnoofaptiEncryptedBytes=SimpleBase64Encoder.decode(placementnoofapti[i]);
//                        byte[] placementnoofaptiDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofaptiEncryptedBytes);
//                        placementnoofapti[i]=new String(placementnoofaptiDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementnoofapti"+"["+i+"] "+placementnoofapti[i]);
//
//                    }
//                    if(placementnooftechtest[i]!=null)
//                    {
//                        byte[] placementnooftechtestEncryptedBytes=SimpleBase64Encoder.decode(placementnooftechtest[i]);
//                        byte[] placementnooftechtestDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnooftechtestEncryptedBytes);
//                        placementnooftechtest[i]=new String(placementnooftechtestDecryptedBytes);
//                    }
//                    if(placementnoofgd[i]!=null)
//                    {
//                        byte[] placementnoofgdEncryptedBytes=SimpleBase64Encoder.decode(placementnoofgd[i]);
//                        byte[] placementnoofgdDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofgdEncryptedBytes);
//                        placementnoofgd[i]=new String(placementnoofgdDecryptedBytes);
//                    }
//                    if(placementnoofti[i]!=null)
//                    {
//                        byte[] placementnooftiEncryptedBytes=SimpleBase64Encoder.decode(placementnoofti[i]);
//                        byte[] placementnooftiDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnooftiEncryptedBytes);
//                        placementnoofti[i]=new String(placementnooftiDecryptedBytes);
//                    }
//                    if(placementnoofhri[i]!=null)
//                    {
//                        byte[] placementnoofhriEncryptedBytes=SimpleBase64Encoder.decode(placementnoofhri[i]);
//                        byte[] placementnoofhriDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofhriEncryptedBytes);
//                        placementnoofhri[i]=new String(placementnoofhriDecryptedBytes);
//                    }
//                    if(placementstdx[i]!=null)
//                    {
//                        byte[] placementstdxEncryptedBytes=SimpleBase64Encoder.decode(placementstdx[i]);
//                        byte[] placementstdxDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementstdxEncryptedBytes);
//                        placementstdx[i]=new String(placementstdxDecryptedBytes);
//                    }
//                    if(placementstdxiiordiploma[i]!=null)
//                    {
//                        byte[] placementstdxiiordiplomaEncryptedBytes=SimpleBase64Encoder.decode(placementstdxiiordiploma[i]);
//                        byte[] placementstdxiiordiplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementstdxiiordiplomaEncryptedBytes);
//                        placementstdxiiordiploma[i]=new String(placementstdxiiordiplomaDecryptedBytes);
//                    }
//                    if(placementug[i]!=null)
//                    {
//                        byte[] placementugEncryptedBytes=SimpleBase64Encoder.decode(placementug[i]);
//                        byte[] placementugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementugEncryptedBytes);
//                        placementug[i]=new String(placementugDecryptedBytes);
//                    }
//                    if(placementpg[i]!=null)
//                    {
//                        byte[] placementpgEncryptedBytes=SimpleBase64Encoder.decode(placementpg[i]);
//                        byte[] placementpgDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementpgEncryptedBytes);
//                        placementpg[i]=new String(placementpgDecryptedBytes);
//                    }
//                    if(placementuploadtime[i]!=null)
//                    {
//                        byte[] placementuploadtimeEncryptedBytes=SimpleBase64Encoder.decode(placementuploadtime[i]);
//                        byte[] placementuploadtimeDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementuploadtimeEncryptedBytes);
//                        placementuploadtime[i]=new String(placementuploadtimeDecryptedBytes);
//                    }
//                    if(placementlastmodified[i]!=null)
//                    {
//                        byte[] placementlastmodifiedEncryptedBytes=SimpleBase64Encoder.decode(placementlastmodified[i]);
//                        byte[] placementlastmodifiedDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementlastmodifiedEncryptedBytes);
//                        placementlastmodified[i]=new String(placementlastmodifiedDecryptedBytes);
//                    }
                    if (placementuploadedby[i] != null) {

                        placementuploadedbyplain[i] = Decrypt(placementuploadedby[i], digest1, digest2);

                        Log.d("PlacmentTesting", "placementuploadedbyplain" + "[" + i + "] " + placementuploadedbyplain[i]);

                    }
//                    if(placemenforwhome[i]!=null)
//                    {
////                        byte[] placemenforwhomeEncryptedBytes=SimpleBase64Encoder.decode(placemenforwhome[i]);
////                        byte[]placemenforwhomeDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placemenforwhomeEncryptedBytes);
////                        placemenforwhomeplain[i]=new String(placemenforwhomeDecryptedBytes);
//
//                        placemenforwhomeplain[i]=Decrypt(placemenforwhome[i],digest1,digest2);
//                        Log.d("placemenforwhome1", "placemenforwhome"+"["+i+"] "+ placemenforwhomeplain[i]);
//
//                    }
//                    if(placementnoofallowedliveatkt[i]!=null)
//                    {
//                        byte[] placementnoofallowedliveatktEncryptedBytes=SimpleBase64Encoder.decode(placementnoofallowedliveatkt[i]);
//                        byte[] placementnoofallowedliveatktDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofallowedliveatktEncryptedBytes);
//                        placementnoofallowedliveatkt[i]=new String(placementnoofallowedliveatktDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementnoofallowedliveatkt"+"["+i+"] "+placementnoofallowedliveatkt[i]);
//
//                    }
//                    if(placementnoofalloweddeadatkt[i]!=null)
//                    {
//                        byte[] placementnoofalloweddeadatktEncryptedBytes=SimpleBase64Encoder.decode(placementnoofalloweddeadatkt[i]);
//                        byte[] placementnoofalloweddeadatktDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofalloweddeadatktEncryptedBytes);
//                        placementnoofalloweddeadatkt[i]=new String(placementnoofalloweddeadatktDecryptedBytes);
//                    }

                } catch (Exception e) {
                    //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            new GetLastUpdatedPlacement().execute();
        }
    }

    class GetLastUpdatedPlacement extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r = null;
try{
    Log.d("catch", "placementuploadedbyplain[i] "+placementuploadedbyplain.length);

    for (int i = 0; i < placementuploadedbyplain.length; i++) {

        Log.d("catch", "placementuploadedbyplain[i] "+placementuploadedbyplain[i]);

    }


            Set<String> uniqKeys = new TreeSet<String>();
            uniqKeys.addAll(Arrays.asList(placementuploadedbyplain));

            uniqueUploadersPlacement = uniqKeys.toArray(new String[uniqKeys.size()]);
            uniqueUploadersEncPlacement = new String[uniqueUploadersPlacement.length];
            lastupdatedPlacement = new String[uniqueUploadersPlacement.length];

            for (int j = 0; j < uniqueUploadersPlacement.length; j++) {
                for (int i = 0; i < placementcount; i++) {

                    if (placementuploadedbyplain[i].equals(uniqueUploadersPlacement[j])) {
                        uniqueUploadersEncPlacement[j] = placementuploadedby[i];
                    }
                }
            }

            for (int i = 0; i < uniqueUploadersPlacement.length; i++) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", uniqueUploadersEncPlacement[i]));       //0
                json = jParser.makeHttpRequest(Z.url_getlastupdated, "GET", params);
                try {
                    String s = json.getString("lastupdated");
                    if (s.equals("noupdate")) {
                    } else {
                        lastupdatedPlacement[i] = s;

                    }

                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
    Log.d("catch", " "+e.getMessage());
    e.printStackTrace();

        }


            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (!isLastPageLoadedPlacement){

                addPlacementdatatoAdapter();
            }

        }

    }


}



