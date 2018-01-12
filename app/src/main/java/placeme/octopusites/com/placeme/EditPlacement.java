package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.modal.RecyclerItemAdapterPlacement;
import placeme.octopusites.com.placeme.modal.RecyclerItemPlacement;
import placeme.octopusites.com.placeme.modal.RecyclerTouchListener;

import static placeme.octopusites.com.placeme.AES4all.fromString;


public class EditPlacement extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    Toolbar toolbar;
    List<RecyclerItemPlacement> tempListPlacement;
    TextView selectedtxt;
    int selectedCount = 0;
    int deleteflag = 0;
    int selectedPositions[];
    boolean isStarted = false;
    Vibrator myVib;
    View[] selectedViews;

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

    private RecyclerItemAdapterPlacement mAdapterPlacement;
    private RecyclerView recyclerViewPlacement;
    private ArrayList<RecyclerItemPlacement> itemListPlacement = new ArrayList<>();
    private ArrayList<RecyclerItemPlacement> placementListfromserver = new ArrayList<>();


    int firstVisibleItemPlacement, visibleItemCountPlacement, totalItemCountPlacement;

    SwipeRefreshLayout tswipe_refresh_layout;
    boolean newCreatedPlacements=false;
    private String TAG="EditPlacemets";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_placement);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Edit Placement</font>"));

        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        Z.NetworkConnectoin(EditPlacement.this);

        //init
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username = MySharedPreferencesManager.getUsername(this);
        String role = MySharedPreferencesManager.getRole(this);

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

        mAdapterPlacement = new RecyclerItemAdapterPlacement(itemListPlacement, EditPlacement.this);
        recyclerViewPlacement.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerPlacement = new LinearLayoutManager(this);
        recyclerViewPlacement.setLayoutManager(linearLayoutManagerPlacement);
        recyclerViewPlacement.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewPlacement.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlacement.setAdapter(mAdapterPlacement);

        String Tag = "EditPlacement";
        set1.setActivityFromtag(Tag);
        recyclerViewPlacement.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewPlacement, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                RecyclerItemPlacement item = itemListPlacement.get(position);

                if (deleteflag == 1) {

                    if (!notificationdeleteArraylist.contains(item.getId())) {
                        notificationdeleteArraylist.add(item.getId());
                        view.setBackgroundColor(Color.parseColor("#eeeeee"));
                        selectedCount++;
                        setActionBarTitle(selectedCount);
                        selectedPositions[position] = 1;
                        selectedViews[position] = view;
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
                    startActivityForResult(i1,99);
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                RecyclerItemPlacement item = itemListPlacement.get(position);
                if (deleteflag == 0) {

                    if (!notificationdeleteArraylist.contains(item.getId())) {
                        notificationdeleteArraylist.add(item.getId());
                    }

                    myVib.vibrate(40);
                    hideSearchMenu();
                    deleteflag = 1;
                    setDeleteActionbar();
                    view.setBackgroundColor(Color.parseColor("#eeeeee"));
                    selectedCount++;
                    setActionBarTitle(selectedCount);
                }

            }
        }));

        recyclerViewPlacement.setOnScrollListener(new EndlessRecyclerOnScrollListenerPlacement(linearLayoutManagerPlacement) {
            @Override
            public void onLoadMore(int current_page) {

                if (total_no_of_placements > 20) {

                    simulateLoadingPlacement();
                }

            }
        });


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
                upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
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


        TextView editnotitxt = (TextView) findViewById(R.id.editnotitxt);
        TextView editnotinotitxt = (TextView) findViewById(R.id.editnotinotitxt);
        editnotitxt.setTypeface(Z.getBold(this));
        editnotinotitxt.setTypeface(Z.getLight(this));
        deletenotitxt.setTypeface(Z.getBold(this));


        //seting data to adapter
        tswipe_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setRefreshing(true);
        getPlacements();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tswipe_refresh_layout.setRefreshing(false);
                if (mAdapterPlacement.getItemCount() == 0) {
                    Toast.makeText(EditPlacement.this, "Couldn't Process Your request.please try again", Toast.LENGTH_SHORT).show();
                }
            }
        }, 5000);


        tswipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPlacements();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tswipe_refresh_layout.setRefreshing(false);
                        if (mAdapterPlacement.getItemCount() == 0) {
                            Toast.makeText(EditPlacement.this, "Couldn't Process Your request.please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 5000);
            }
        });

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
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
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
        actionBar.setBackgroundDrawable(new ColorDrawable(EditPlacement.this.getResources().getColor(R.color.transparent)));

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

                                             Deleteplacements();

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
            tswipe_refresh_layout.setRefreshing(true);

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

            getPlacements();


        } else {
            if(newCreatedPlacements){
                setResult(AdminActivity.ADMIN_CREATE_DATA_CHANGE_RESULT_CODE);
                super.onBackPressed();

            }else{
                setResult(299);
            super.onBackPressed();
            }


        }
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


//        new GetPlacementsByAdminMetadata().execute();
        GetPlacementsByAdminMetadata();

    }




    class GetPlacements extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r = null;


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("p", page_to_call_placement + ""));

            json = jParser.makeHttpRequest(Z.url_GetPlacementSentByAdmin, "GET", params);
            try {


                Log.d("json1", "placementlistfromserver " + json.getString("placementlistfromserver"));
                placementListfromserver = (ArrayList<RecyclerItemPlacement>) fromString(json.getString("placementlistfromserver"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
                Log.d("itemlistfromserver", "reg=======================" + placementListfromserver.size());
                Log.d("itemlistfromserver", "getNotification1=======================" + placementListfromserver.get(0).getCompanyname());
                Log.d("itemlistfromserver", "getNotification2=======================" + placementListfromserver.get(2).getDateofarrival());


            } catch (Exception e) {
            }

            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d("Getplacement", "onPostExecute:  im here");
            setplacementListtoadapter(placementListfromserver);

        }
    }

    public void GetPlacementsFromServer() {
        Log.d("TAG", "getCurrentConnectionQuality : " + AndroidNetworking.getCurrentConnectionQuality() + " currentBandwidth : " + AndroidNetworking.getCurrentBandwidth());
        AndroidNetworking.post("https://placeme.co.in/CreateNotificationTemp/GetPlacementSentByAdmin")
                .setTag(this)
                .addQueryParameter("u", username)
                .addQueryParameter("p", page_to_call_placement + "")
                .setPriority(Priority.LOW)
                .setOkHttpClient(OkHttpUtil.getClient())
                .getResponseOnlyFromNetwork()
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d("TAG", " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d("TAG", " bytesSent : " + bytesSent);
                        Log.d("TAG", " bytesReceived : " + bytesReceived);
                        Log.d("TAG", " isFromCache : " + isFromCache);
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", "onResponse object2 : " + response.toString());
                        try {


                            Log.d("json1", "placementlistfromserver " + response.getString("placementlistfromserver"));
                            placementListfromserver = (ArrayList<RecyclerItemPlacement>) fromString(response.getString("placementlistfromserver"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
                            Log.d("itemlistfromserver", "reg=======================" + placementListfromserver.size());
                            Log.d("itemlistfromserver", "getNotification1=======================" + placementListfromserver.get(0).getCompanyname());
                            Log.d("itemlistfromserver", "getNotification2=======================" + placementListfromserver.get(2).getDateofarrival());

                            itemListPlacement.clear();
                            setplacementListtoadapter(placementListfromserver);

                        } catch (Exception e) {
                        }


                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received ANError from server
                            // error.getErrorCode() - the ANError code from server
                            // error.getErrorBody() - the ANError body from server
                            // error.getErrorDetail() - just a ANError detail
                            Log.d("TAG", "onError errorCode : " + error.getErrorCode());
                            Log.d("TAG", "onError errorBody : " + error.getErrorBody());
                            Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                            tswipe_refresh_layout.setRefreshing(false);
                            Toast.makeText(EditPlacement.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                            tswipe_refresh_layout.setRefreshing(false);
                            Toast.makeText(EditPlacement.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



    private void setplacementListtoadapter(ArrayList<RecyclerItemPlacement> itemList2) {


        Log.d("tag2", "itemListPlacement size ===========" + itemListPlacement.size());
        tswipe_refresh_layout.setVisibility(View.VISIBLE);
        tswipe_refresh_layout.setRefreshing(false);
        if (lastPageFlagPlacement == 1)
            isLastPageLoadedPlacement = true;

//        mAdapterPlacement = new RecyclerPlacementsAdapter(itemListPlacement,EditPlacement.this);
        itemListPlacement.addAll(itemList2);
        selectedPositions = new int[total_no_of_placements];
        selectedViews = new View[total_no_of_placements];
//
        recyclerViewPlacement.getRecycledViewPool().clear();
        mAdapterPlacement.notifyDataSetChanged();


        Log.d("tag2", "itemcount size ===========" + mAdapterPlacement.getItemCount());


    }

    class GetPlacementsByAdminMetadata extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {
                json = jParser.makeHttpRequest(Z.url_GetPlacementSentByAdminByAdminMetaData, "GET", params);


                placementpages = Integer.parseInt(json.getString("pages"));
                called_pages_placement = new int[placementpages];
                total_no_of_placements = Integer.parseInt(json.getString("count"));
                unreadcountPlacement = Integer.parseInt(json.getString("unreadcount"));

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            new GetPlacements().execute();


        }
    }

    private void GetPlacementsByAdminMetadata() {
        Log.d("TAG", "getCurrentConnectionQuality : " + AndroidNetworking.getCurrentConnectionQuality() + " currentBandwidth : " + AndroidNetworking.getCurrentBandwidth());
        AndroidNetworking.post("https://placeme.co.in/CreateNotificationTemp/GetPlacementsByAdminMetaData")
                .setTag(this)
                .addQueryParameter("u", username)
                .setPriority(Priority.MEDIUM)
                .setOkHttpClient(OkHttpUtil.getClient())
                .getResponseOnlyFromNetwork()
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d("TAG", " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d("TAG", " bytesSent : " + bytesSent);
                        Log.d("TAG", " bytesReceived : " + bytesReceived);
                        Log.d("TAG", " isFromCache : " + isFromCache);
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", "onResponse object2 : " + response.toString());
                        try {
                            placementpages = Integer.parseInt(response.getString("pages"));
                            called_pages_placement = new int[placementpages];
                            total_no_of_placements = Integer.parseInt(response.getString("count"));
                            unreadcountPlacement = Integer.parseInt(response.getString("unreadcount"));
                            GetPlacementsFromServer();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received ANError from server
                            // error.getErrorCode() - the ANError code from server
                            // error.getErrorBody() - the ANError body from server
                            // error.getErrorDetail() - just a ANError detail
                            Log.d("TAG", "onError errorCode : " + error.getErrorCode());
                            Log.d("TAG", "onError errorBody : " + error.getErrorBody());
                            Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                            tswipe_refresh_layout.setRefreshing(false);
                            Toast.makeText(EditPlacement.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                            tswipe_refresh_layout.setRefreshing(false);
                            Toast.makeText(EditPlacement.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }




    public abstract class EndlessRecyclerOnScrollListenerPlacement extends RecyclerView.OnScrollListener {


        private LinearLayoutManager mLinearLayoutManager;

        public EndlessRecyclerOnScrollListenerPlacement(LinearLayoutManager linearLayoutManager) {
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            visibleItemCountPlacement = recyclerView.getChildCount();
            totalItemCountPlacement = mLinearLayoutManager.getItemCount();
            firstVisibleItemPlacement = mLinearLayoutManager.findFirstVisibleItemPosition();

            if (loadingPlacement) {
                if (totalItemCountPlacement > previousTotalPlacement) {
                    loadingPlacement = false;
                    previousTotalPlacement = totalItemCountPlacement;
                }
            }
            if (!loadingPlacement && (totalItemCountPlacement - visibleItemCountPlacement)
                    <= (firstVisibleItemPlacement + visibleThresholdPlacement)) {
                // End has been reached

                // Do something
                current_page_placement++;

                onLoadMore(current_page_placement);

                loadingPlacement = true;
            }
        }

        public abstract void onLoadMore(int current_page);

    }

    private void simulateLoadingPlacement() {
        tswipe_refresh_layout.setRefreshing(true);

        Log.d("TAG", "Movies to release with ranveer from reports:" + page_to_call_placement);
        Log.d("TAG", "projects with ranveer :" + placementpages);
        if (page_to_call_placement < placementpages)
            page_to_call_placement++;

        if (page_to_call_placement != placementpages) {

            AndroidNetworking.post(Z.url_GetPlacementSentByAdmin)
                    .setTag(this)
                    .addQueryParameter("u", username)
                    .addQueryParameter("p", page_to_call_placement + "")
                    .setPriority(Priority.MEDIUM)
                    .setOkHttpClient(OkHttpUtil.getClient())
                    .getResponseOnlyFromNetwork()
                    .build()
                    .setAnalyticsListener(new AnalyticsListener() {
                        @Override
                        public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                            Log.d("TAG", " timeTakenInMillis : " + timeTakenInMillis);
                            Log.d("TAG", " bytesSent : " + bytesSent);
                            Log.d("TAG", " bytesReceived : " + bytesReceived);
                            Log.d("TAG", " isFromCache : " + isFromCache);
                        }
                    })
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("TAG", "onResponse object2 : " + response.toString());
                            try {
                                placementListfromserver = (ArrayList<RecyclerItemPlacement>) fromString(response.getString("placementlistfromserver"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
//                                Log.d("TAG", " Movies from Hollywood" + itemlistfromserver.size());
                                Log.d("itemlistfromserver", "reg=======================" + placementListfromserver.size());
//                            Log.d("itemlistfromserver", "getNotification1=======================" + placementListfromserver.get(0).getCompanyname());
//                            Log.
                                if (!isLastPageLoadedPlacement) {


                                    setplacementListtoadapter(placementListfromserver);
                                }
                                tswipe_refresh_layout.setRefreshing(false);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(ANError error) {
                            if (error.getErrorCode() != 0) {
                                // received ANError from server
                                // error.getErrorCode() - the ANError code from server
                                // error.getErrorBody() - the ANError body from server
                                // error.getErrorDetail() - just a ANError detail
                                Log.d("TAG", "onError errorCode : " + error.getErrorCode());
                                Log.d("TAG", "onError errorBody : " + error.getErrorBody());
                                Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                                tswipe_refresh_layout.setRefreshing(false);
                                Toast.makeText(EditPlacement.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();
                            } else {
                                // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                                tswipe_refresh_layout.setRefreshing(false);
                                Toast.makeText(EditPlacement.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        } else {
            if (!isLastPageLoadedPlacement) {
                lastPageFlagPlacement = 1;

                AndroidNetworking.post(Z.url_GetPlacementSentByAdmin)
                        .setTag(this)
                        .addQueryParameter("u", username)
                        .addQueryParameter("p", page_to_call_placement + "")
                        .setPriority(Priority.MEDIUM)
                        .setOkHttpClient(OkHttpUtil.getClient())
                        .getResponseOnlyFromNetwork()
                        .build()
                        .setAnalyticsListener(new AnalyticsListener() {
                            @Override
                            public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                                Log.d("TAG", " timeTakenInMillis : " + timeTakenInMillis);
                                Log.d("TAG", " bytesSent : " + bytesSent);
                                Log.d("TAG", " bytesReceived : " + bytesReceived);
                                Log.d("TAG", " isFromCache : " + isFromCache);
                            }
                        })
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("TAG", "onResponse object2 : " + response.toString());
                                try {

                            placementListfromserver = (ArrayList<RecyclerItemPlacement>) fromString(response.getString("placementlistfromserver"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
//                            Log.d("TAG", " Movies from Hollywood" + itemlistfromserver.size());
                                    Log.d("itemlistfromserver", "reg=======================" + placementListfromserver.size());
//                            Log.d("itemlistfromserver", "getNotification1=======================" + placementListfromserver.get(0).getCompanyname());
//                            Log.d("itemlistfromserver", "getNotification2=======================" + placementListfromserver.get(2).getDateofarrival());
//
                                    if (!isLastPageLoadedPlacement) {
                                        setplacementListtoadapter(placementListfromserver);
                                    }
                                    tswipe_refresh_layout.setRefreshing(false);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onError(ANError error) {
                                if (error.getErrorCode() != 0) {
                                    // received ANError from server
                                    // error.getErrorCode() - the ANError code from server
                                    // error.getErrorBody() - the ANError body from server
                                    // error.getErrorDetail() - just a ANError detail
                                    Log.d("TAG", "onError errorCode : " + error.getErrorCode());
                                    Log.d("TAG", "onError errorBody : " + error.getErrorBody());
                                    Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                                    tswipe_refresh_layout.setRefreshing(false);
                                    Toast.makeText(EditPlacement.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();
                                } else {
                                    // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                    Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                                    tswipe_refresh_layout.setRefreshing(false);
                                    Toast.makeText(EditPlacement.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }else{
                tswipe_refresh_layout.setRefreshing(false);

            }

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==AdminActivity.ADMIN_CREATE_DATA_CHANGE_RESULT_CODE){
            newCreatedPlacements=true;
            getPlacements();
        }

    }



    private void Deleteplacements() {
        String deletidsArray[] = notificationdeleteArraylist.toArray(new String[notificationdeleteArraylist.size()]);
        String deletids = Arrays.toString(deletidsArray);
        deletids = deletids.trim();



// do it later for refreshing recyclerView

//       String deletidsaftr=deletids.replace("[","");
//        deletidsaftr=deletidsaftr.replace("]","");
//        deletidsaftr=  deletidsaftr.trim();
//        String strids[]=deletidsaftr.split(",");
//
//        for (int i = 0; i < strids.length; i++){
//
//        }

        Log.d(TAG, ":username " + username);
        Log.d(TAG, ":deletids " + deletids);

        AndroidNetworking.post("https://placeme.co.in/CreateNotificationTemp/DeletePlacement")
                .setTag(this)
//                .addPathParameter("port","8080")
                .addQueryParameter("u", username)
                .addQueryParameter("ids", deletids)
                .setPriority(Priority.MEDIUM)
                .setOkHttpClient(OkHttpUtil.getClient())
                .getResponseOnlyFromNetwork()
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse object : " + response.toString());
                        try {
                            String info = response.getString("info");
                            Log.d(TAG, "info: " + info);
                            if (info != null) {
                                if (info.contains("successfully")) {
                                    setResult(AdminActivity.ADMIN_CREATE_DATA_CHANGE_RESULT_CODE);
                                    Toast.makeText(EditPlacement.this, "Placements deleted successfully", Toast.LENGTH_LONG).show();
                                    newCreatedPlacements=true;
                                    goBack();
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received ANError from server
                            // error.getErrorCode() - the ANError code from server
                            // error.getErrorBody() - the ANError body from server
                            // error.getErrorDetail() - just a ANError detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());

                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());

                        }
                    }
                });
    }

}
