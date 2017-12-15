package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import de.hdodenhof.circleimageview.CircleImageView;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;


public class EditPlacement extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    private static String url_getplacementsmetadata = "http://192.168.100.100:8080/CreateNotificationTemp/GetPlacementsByAdminMetaData";
    private static String url_getplacementsreadstatus = "http://192.168.100.100:8080/CreateNotificationTemp/GetReadStatusOfPlacementsByAdmin";
    private static String url_getplacements = "http://192.168.100.100:8080/CreateNotificationTemp/GetPlacementSentByAdmin";
    private static String url_changeplacementsreadstatus = "http://192.168.100.100:8080/CreateNotificationTemp/ChangePlacementReadStatusAdmin";
    private static String url_Delete_Placements = "http://192.168.100.100:8080/CreateNotificationTemp/DeletePlacement";
    private static String url_getlastupdated = "http://192.168.100.30:8080/AESTest/GetLastUpdatedAdmin";
    Toolbar toolbar;
    List<RecyclerItemPlacement> tempListPlacement;
    TextView selectedtxt;
    int selectedCount = 0;
    int deleteflag = 0;
    int selectedPositions[];
    boolean isStarted = false;
    Vibrator myVib;
    View[] selectedViews;

    SharedPreferences sharedpreferences;
    CircleImageView profile;

    JSONParser jParser = new JSONParser();
    JSONObject json;
    String digest1, digest2;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";
    int total_no_of_placements;
    boolean isFirstRunPlacement = true, isLastPageLoadedPlacement = false;
    int lastPageFlagPlacement = 0;
    int placementpages = 0;
    String placementids[], placementcompanyname[], placementcpackage[], placementpost[], placementforwhichcourse[], placementforwhichstream[], placementvacancies[], placementlastdateofregistration[], placementdateofarrival[], placementbond[], placementnoofapti[], placementnooftechtest[], placementnoofgd[], placementnoofti[], placementnoofhri[], placementstdx[], placementstdxiiordiploma[], placementug[], placementpg[], placementuploadtime[], placementlastmodified[], placementuploadedby[], placementuploadedbyplain[], placemenforwhome[], placemenforwhomeplain[], placementnoofallowedliveatkt[], placementnoofalloweddeadatkt[];
    int[] called_pages_placement;
    int unreadcountPlacement = 0;
    int placementcount = 0;
    int readstatuscountPlacement = 0;
    String placementreadstatus[];
    String[] uniqueUploadersPlacement;
    String[] uniqueUploadersEncPlacement;
    String lastupdatedPlacement[];
    ArrayList<String> notificationdeleteArraylist = new ArrayList<>();
    PlacementEditData set1 = new PlacementEditData();
    //    private List<RecyclerItemPlacement> itemList = new ArrayList<>();
//    private RecyclerView recyclerView;
    private MaterialSearchView searchView;
    //placmeents adapter data.
    private int previousTotalPlacement = 0; // The total number of items in the dataset after the last load
    private int visibleThresholdPlacement = 0; // The minimum amount of items to have below your current scroll position before loading more.
    private int page_to_call_placement = 1;
    private int current_page_placement = 1;
    private boolean loadingPlacement = true; // True if we are still waiting for the last set of data to load.
    private String plainusername, username = "", fname = "", mname = "", sname = "";
    private List<RecyclerItemPlacement> itemListPlacement = new ArrayList<>();
    private RecyclerItemAdapterPlacement mAdapterPlacement;
    private RecyclerView recyclerViewPlacement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_placement);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Edit Placement</font>"));

        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        //init

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        String role=MySharedPreferencesManager.getRole(this);

//        try
//        {
//            demoKeyBytes = SimpleBase64Encoder.decode(digest1);
//            demoIVBytes = SimpleBase64Encoder.decode(digest2);
//            sPadding = "ISO10126Padding";
//
//            byte[] demo1EncryptedBytes1=SimpleBase64Encoder.decode(username);
//
//            byte[] demo1DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes1);
//
//            String  plainusername=new String(demo1DecryptedBytes1);
//
//            r.setPlainusername(plainusername);
//
//        }catch (Exception e){}

        recyclerViewPlacement = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapterPlacement = new RecyclerItemAdapterPlacement(itemListPlacement);
        recyclerViewPlacement.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerPlacement = new LinearLayoutManager(this);
        recyclerViewPlacement.setLayoutManager(linearLayoutManagerPlacement);
        recyclerViewPlacement.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewPlacement.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlacement.setAdapter(mAdapterPlacement);

        String Tag = "EditPlacementAlumni";
        set1.setActivityFromtag(Tag);
        recyclerViewPlacement.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewPlacement, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                RecyclerItemPlacement item = itemListPlacement.get(position);

                if (deleteflag == 1) {
                    if (selectedPositions[position] == 0) {
                        view.setBackgroundColor(Color.parseColor("#000000"));
                        selectedCount++;
                        setActionBarTitle(selectedCount);
                        selectedPositions[position] = 1;
                        selectedViews[position] = view;
                        if (!notificationdeleteArraylist.contains(item.getId())) {
                            notificationdeleteArraylist.add(item.getId());
                        }


                    } else {

                        notificationdeleteArraylist.remove(item.getId());
                        view.setBackgroundColor(Color.TRANSPARENT);
                        selectedCount--;
                        setActionBarTitle(selectedCount);
                        selectedPositions[position] = 0;
                        selectedViews[position] = null;
                        if (selectedCount == 0)
                            goBack();

                    }
                } else {

                    Intent i1 = new Intent(EditPlacement.this, EditPlacementMain.class);
                    i1.putExtra("flag", "fromeditactivity");

//                    String st1=""+set1.getId();
//
                    i1.putExtra("id", item.getId());
//                    i1.putExtra("companyname",item.getCompanyname());
//                    i1.putExtra("package",item.getCpackage());
//                    i1.putExtra("post",item.getPost());
//                    i1.putExtra("forwhichcourse",item.getForwhichcourse());
//                    i1.putExtra("forwhichstream",item.getForwhichstream());
//                    i1.putExtra("vacancies",item.getVacancies());
//                    i1.putExtra("lastdateofregistration",item.getLastdateofregistration());
//                    i1.putExtra("dateofarrival",item.getDateofarrival());
//                    i1.putExtra("bond",item.getBond());
//                    i1.putExtra("noofapti",item.getNoofapti());
//                    i1.putExtra("nooftechtest",item.getNooftechtest());
//                    i1.putExtra("noofgd",item.getNoofgd());
//                    i1.putExtra("noofti",item.getNoofti());
//                    i1.putExtra("noofhri",item.getNoofhri());
//                    i1.putExtra("stdx",item.getStdx());
//                    i1.putExtra("stdxiiordiploma",item.getStdxiiordiploma());
//                    i1.putExtra("uploadtime",item.getUploadtime());
//                    i1.putExtra("ugc",item.getUg());
//                    i1.putExtra("pgc",item.getPg());
//                    i1.putExtra("lastmodified",item.getLastmodified());
//                    i1.putExtra("uploadedby",item.getUploadedby());
////                    i1.putExtra("forwhome",item.getForwhom());
//                    i1.putExtra("noofallowedliveatkt",item.getNoofallowedliveatkt());
//                    i1.putExtra("noofalloweddeadatkt",item.getNoofalloweddeadatkt());
//                    i1.putExtra("object", item);      //sending object


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
                    set1.setUploadedby(item.getUploadedby());
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

            }

            @Override
            public void onLongClick(View view, int position) {
                if (deleteflag == 0) {
                    RecyclerItemPlacement item = itemListPlacement.get(position);


                    if (!notificationdeleteArraylist.contains(item.getId())) {
                        notificationdeleteArraylist.add(item.getId());
                    }
                    myVib.vibrate(40);
                    hideSearchMenu();
                    deleteflag = 1;
                    setDeleteActionbar();
                    view.setBackgroundColor(Color.parseColor("#000000"));
                    selectedCount++;
                    setActionBarTitle(selectedCount);
                    selectedPositions[position] = 1;
                    selectedViews[position] = view;
                }

            }
        }));
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(EditPlacement.this, query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
                upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                searchView.setBackIcon(upArrow);

            }

            @Override
            public void onSearchViewClosed() {
                final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
                upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                searchView.setBackIcon(upArrow);
            }
        });

        TextView deletenotitxt = (TextView) findViewById(R.id.deletenotitxt);
        deletenotitxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (deleteflag == 0) {
                    hideSearchMenu();
                    deleteflag = 1;
                    setDeleteActionbar();
                }


            }
        });
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/cabinsemibold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/maven.ttf");

        TextView editnotitxt = (TextView) findViewById(R.id.editnotitxt);
        TextView editnotinotitxt = (TextView) findViewById(R.id.editnotinotitxt);
        editnotitxt.setTypeface(custom_font);
        editnotinotitxt.setTypeface(custom_font2);
        deletenotitxt.setTypeface(custom_font);


        //seting data to adapter
        getPlacements();

    }


    void hideSearchMenu() {
        EditPlacement.this.invalidateOptionsMenu();
        isStarted = true;
        Menu m = null;
        super.onPrepareOptionsMenu(m);
    }

    void showSearchMenu() {
        notificationdeleteArraylist.clear();

        EditPlacement.this.invalidateOptionsMenu();
        isStarted = false;
        Menu m = null;
        super.onPrepareOptionsMenu(m);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_search).setVisible(!isStarted);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    void setNormalActionbar() {


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(EditPlacement.this.getResources().getColor(R.color.colorPrimary)));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Edit Placements</font>"));

        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


    }

    public void setActionBarTitle(int count) {
        selectedtxt.setText(count + " Selected");

    }

    public void goBack() {
        onBackPressed();
    }

    void setDeleteActionbar() {


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(EditPlacement.this.getResources().getColor(R.color.transperent)));

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_delete_notifications, null);
        selectedtxt = (TextView) v.findViewById(R.id.selectedtxt);
        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        actionBar.setCustomView(v);

        ImageView trash = (ImageView) v.findViewById(R.id.trashnotifications);
        trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedCount != 0) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditPlacement.this);
                    alertDialogBuilder
                            .setMessage("Are you sure you want to delete selected items ?")
                            .setCancelable(false)
                            .setPositiveButton("Delete",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            new Deleteplacements().execute();

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
                    Toast.makeText(EditPlacement.this, "Select First", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (deleteflag == 1) {
            notificationdeleteArraylist.clear();

            setNormalActionbar();
            deleteflag = 0;
            showSearchMenu();

            for (int i = 0; i < mAdapterPlacement.getItemCount(); i++) {
                if (selectedViews[i] != null) {
                    selectedViews[i].setBackgroundColor(Color.TRANSPARENT);
                }
                selectedPositions[i] = 0;
            }
            selectedCount = 0;

        } else
            super.onBackPressed();
    }

    //adapter methods and classes

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
//        new GetPlacementsReadStatus().execute();

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

                String companynametoshow = "";
                int largecompanynameflag = 0;

                if (placementcompanyname[i].length() > 25) {
                    for (int j = 0; j < 20; j++)
                        companynametoshow += placementcompanyname[i].charAt(j);
                    largecompanynameflag = 1;
                    companynametoshow += "...";
                }

                RecyclerItemPlacement item = null;


                for (int k = 0; k < uniqueUploadersPlacement.length; k++) {
                    if (placementuploadedbyplain[i].equals(uniqueUploadersPlacement[k])) {
                        if (lastupdatedPlacement[k] == null) {
                            if (largecompanynameflag == 1)
                                item = new RecyclerItemPlacement(placementids[i], companynametoshow, placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], true, EditPlacement.this, "placeme", placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                            else
                                item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], true, EditPlacement.this, "placeme", placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                            itemListPlacement.add(item);
                        } else {
                            if (largecompanynameflag == 1)
                                item = new RecyclerItemPlacement(placementids[i], companynametoshow, placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], true, EditPlacement.this, lastupdatedPlacement[k], placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                            else
                                item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], true, EditPlacement.this, lastupdatedPlacement[k], placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        getPlacements();


    }

    class GetPlacementsReadStatus extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {
                json = jParser.makeHttpRequest(url_getplacementsmetadata, "GET", params);
                placementpages = Integer.parseInt(json.getString("pages"));
                called_pages_placement = new int[placementpages];
                total_no_of_placements = Integer.parseInt(json.getString("count"));
                unreadcountPlacement = Integer.parseInt(json.getString("unreadcount"));


                json = jParser.makeHttpRequest(url_getplacementsreadstatus, "GET", params);
                readstatuscountPlacement = Integer.parseInt(json.getString("count"));
                placementreadstatus = new String[readstatuscountPlacement];


                for (int i = 0; i < readstatuscountPlacement; i++) {
                    placementreadstatus[i] = json.getString("s" + i);
//                    Log.d("PlacmentTesting", "total_no_of_placements: "+placementreadstatus[i]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d("PlacmentTesting", "total_no_of_placements: " + total_no_of_placements);
            Log.d("PlacmentTesting", "unreadcountPlacement: " + unreadcountPlacement);
            for (int i = 0; i < readstatuscountPlacement; i++) {
                Log.d("PlacmentTesting", "placementreadstatus1: " + placementreadstatus[i]);
            }

            new GetPlacements().execute();

        }
    }

    class GetPlacements extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r = null;

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("p", page_to_call_placement + ""));


            json = jParser.makeHttpRequest(url_getplacements, "GET", params);
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
                }


            } catch (Exception e) {
            }

            return r;
        }

        @Override
        protected void onPostExecute(String result) {


            Log.d("Getplacement", "onPostExecute:  im here");
            for (int i = 0; i < placementcount; i++)
                try {
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
                        byte[] placementuploadedbyEncryptedBytes = SimpleBase64Encoder.decode(placementuploadedby[i]);
                        byte[] placementuploadedbyDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementuploadedbyEncryptedBytes);
                        placementuploadedbyplain[i] = new String(placementuploadedbyDecryptedBytes);
                        Log.d("PlacmentTesting", "placementuploadedbyplain" + "[" + i + "] " + placementuploadedbyplain[i]);

                    }
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
                json = jParser.makeHttpRequest(url_getlastupdated, "GET", params);
                try {
                    String s = json.getString("lastupdated");
                    if (s.equals("noupdate")) {
                    } else {
                        lastupdatedPlacement[i] = s;

                    }

                } catch (Exception e) {
                }
            }


            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (!isLastPageLoadedPlacement)
                addPlacementdatatoAdapter();

        }

    }

    class Deleteplacements extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String deletidsArray[] = notificationdeleteArraylist.toArray(new String[notificationdeleteArraylist.size()]);
            String deletids = Arrays.toString(deletidsArray);
            deletids = deletids.trim();
            Log.d("username", ":username " + username);
            Log.d("deletids", "onClick: " + deletids);

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("ids", deletids));       //1


            json = jParser.makeHttpRequest(url_Delete_Placements, "GET", params);
            try {
                r = json.getString("info");
                Log.d("TAG", "info" + r);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                Toast.makeText(EditPlacement.this, result, Toast.LENGTH_LONG).show();
                Log.d("Tag", "onPostExecute: " + result);
                goBack();
                getPlacements();

            } catch (Exception e) {
                Log.d("Tag", "onPostExecute: " + result);
                Log.d("Tag", "onPostExecute: " + e.getMessage());
                Toast.makeText(EditPlacement.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }


}
