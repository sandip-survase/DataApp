package placeme.octopusites.com.placeme;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.ArrayList;

import placeme.octopusites.com.placeme.modal.MainListModal;
import placeme.octopusites.com.placeme.modal.RecyclerItem;
import placeme.octopusites.com.placeme.modal.SubListAdapter1;
import placeme.octopusites.com.placeme.modal.SubListAdapter2;
import placeme.octopusites.com.placeme.modal.SubListAdapter3;
import placeme.octopusites.com.placeme.modal.SubListModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.fromString;
import static placeme.octopusites.com.placeme.Z.IP;
import static placeme.octopusites.com.placeme.Z.url_UpdatePlacementStudentList;

public class UserSelection2 extends AppCompatActivity {

    public boolean Usereditedflag = false;
    public RecyclerItem checkboxs;
    int selectedTab = 1;
    int menuFlag1 = 0, menuFlag2 = 0, menuFlag3 = 0;
    String TAG = "TAG";
    MenuItem select_all, deselect_all, submit;
    FloatingActionButton fab, fabmini;
    TextView filterCount;
    RelativeLayout filterCountLayout;
    String CompanId;

    Toolbar toolbar;
    BottomNavigationView navigation;
    String PlacementCompanyname = "";
    private TextView toolbar_title;
    private ProgressDialog dialog;
    private RecyclerView recycler_view_Registered, recycler_view_ShortListed, recycler_view_Placed;
    private ArrayList<MainListModal> MainList = new ArrayList<>();
    public static ArrayList<SubListModal> subList1 = new ArrayList<>();
    public static ArrayList<SubListModal> subList2 = new ArrayList<>();
    public static ArrayList<SubListModal> subList3 = new ArrayList<>();
    private SubListAdapter1 mSubListAdapter1;
    private SubListAdapter2 mSubListAdapter2;
    private SubListAdapter3 mSubListAdapter3;

    SwipeRefreshLayout tswipe_refresh_layout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    recycler_view_Registered.setVisibility(View.VISIBLE);
                    recycler_view_ShortListed.setVisibility(View.GONE);
                    recycler_view_Placed.setVisibility(View.GONE);
                    selectedTab = 1;
                    showCountOfShortlistedUsers();
                    submit.setVisible(true);

                    if (menuFlag1 == 0) {
                        select_all.setVisible(true);
                        deselect_all.setVisible(false);

                    } else {

                        select_all.setVisible(false);
                        deselect_all.setVisible(true);
                    }
                    return true;
                case R.id.navigation_dashboard:
                    recycler_view_Registered.setVisibility(View.GONE);
                    recycler_view_ShortListed.setVisibility(View.VISIBLE);
                    recycler_view_Placed.setVisibility(View.GONE);
                    selectedTab = 2;
                    showCountOfPlacedUsers();
                    submit.setVisible(true);

                    if (menuFlag2 == 0) {
                        select_all.setVisible(true);
                        deselect_all.setVisible(false);

                    } else {

                        select_all.setVisible(false);
                        deselect_all.setVisible(true);
                    }
                    return true;
                case R.id.navigation_notifications:
                    filterCountLayout.setVisibility(View.GONE);
                    filterCount.setText("");
                    select_all.setVisible(false);
                    deselect_all.setVisible(false);
                    submit.setVisible(false);
                    recycler_view_Registered.setVisibility(View.GONE);
                    recycler_view_ShortListed.setVisibility(View.GONE);
                    recycler_view_Placed.setVisibility(View.VISIBLE);
                    selectedTab = 3;
                    return true;
            }
            return false;


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection2);

//        ActionBar ab = getSupportActionBar();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(Z.getRighteous(UserSelection2.this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        PlacementCompanyname = getIntent().getStringExtra("companyname");

        //setting title
        if (PlacementCompanyname != null && PlacementCompanyname.length() != 0) {
//            ab.setTitle(PlacementCompanyname);
            Log.d(TAG, "Company Name: " + PlacementCompanyname);
            toolbar_title.setText(PlacementCompanyname);
        } else {
//            ab.setTitle("Company Name");
            toolbar_title.setText("Company Name");
        }

        //getting companyId
        CompanId = "" + getIntent().getIntExtra("id", 0);
        Log.d(TAG, "CompanId " + CompanId);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        tswipe_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);


        filterCount = findViewById(R.id.filterCount);
        filterCountLayout = findViewById(R.id.filterCountLayout);
        fab = (FloatingActionButton) findViewById(R.id.fabSendNotification);
        dialog = new ProgressDialog(this);


        recycler_view_Registered = findViewById(R.id.recycler_view_Registered);
        mSubListAdapter1 = new SubListAdapter1(subList1, UserSelection2.this);
        recycler_view_Registered.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_view_Registered.setLayoutManager(mLayoutManager);
        recycler_view_Registered.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recycler_view_Registered.setItemAnimator(new DefaultItemAnimator());
        recycler_view_Registered.setAdapter(mSubListAdapter1);

        recycler_view_ShortListed = findViewById(R.id.recycler_view_ShortListed);
        mSubListAdapter2 = new SubListAdapter2(subList2, UserSelection2.this);
        recycler_view_ShortListed.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        recycler_view_ShortListed.setLayoutManager(mLayoutManager2);
        recycler_view_ShortListed.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recycler_view_ShortListed.setItemAnimator(new DefaultItemAnimator());
        recycler_view_ShortListed.setAdapter(mSubListAdapter2);

        recycler_view_Placed = findViewById(R.id.recycler_view_Placed);
        mSubListAdapter3 = new SubListAdapter3(subList3, UserSelection2.this);
        recycler_view_Placed.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(this);
        recycler_view_Placed.setLayoutManager(mLayoutManager3);
        recycler_view_Placed.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recycler_view_Placed.setItemAnimator(new DefaultItemAnimator());
        recycler_view_Placed.setAdapter(mSubListAdapter3);

        recycler_view_Registered.setVisibility(View.VISIBLE);

        try {
            MainList = (ArrayList<MainListModal>) fromString(getIntent().getStringExtra("sRegisteredItemlistTemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));

//          registerdListfromserver = (ArrayList<RecyclerItemUsers>) fromString(getIntent().getStringExtra("sRegisteredItemlistTemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));
//          ShortlistedListfromserver = (ArrayList<RecyclerItemUsers>) fromString(getIntent().getStringExtra("sShortlistedListsfromservertemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));
//          placedListfromserver = (ArrayList<RecyclerItemUsers>) fromString(getIntent().getStringExtra("splacedItemlistTemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));

        } catch (Exception e) {
//
            Toast.makeText(this, "here" + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("", "e.getMessage(): " + e.getMessage());
        }

        buildListsAsPerMainList();


        mSubListAdapter1.notifyDataSetChanged();
        mSubListAdapter2.notifyDataSetChanged();
        mSubListAdapter3.notifyDataSetChanged();

        showCountOfShortlistedUsers();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedTab == 1) {
                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < subList1.size(); i++) {
                        if (subList1.get(i).isSelected())
                            sb.append(subList1.get(i).getEmail() + ",");
                    }
//                    Toast.makeText(UserSelection2.this, "send Notification to selected Registered users: " + sb.toString(), Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(UserSelection2.this, CreateNotificationHR.class);
                    i1.putExtra("selection", "registered");
                    i1.putExtra("forwhom", "" + sb.toString());
                    startActivity(i1);
                } else if (selectedTab == 2) {
                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < subList2.size(); i++) {
                        if (subList2.get(i).isSelected())
                            sb.append(subList2.get(i).getEmail() + ",");
                    }
//                    Toast.makeText(UserSelection2.this, "send Notification to selected Shortlisted users" + sb.toString(), Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(UserSelection2.this, CreateNotificationHR.class);
                    i1.putExtra("selection", "shortlisted");
                    i1.putExtra("forwhom", "" + sb.toString());
                    startActivity(i1);
                } else if (selectedTab == 3) {
                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < subList3.size(); i++) {
                        sb.append(subList3.get(i).getEmail() + ",");
                    }
//                    Toast.makeText(UserSelection2.this, "send Notification to All Placed users" + sb.toString(), Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(UserSelection2.this, CreateNotificationHR.class);
                    i1.putExtra("selection", "placed");
                    i1.putExtra("forwhom", "" + sb.toString());
                    startActivity(i1);
                }
            }
        });





//        tswipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        tswipe_refresh_layout.setRefreshing(false);
//                        if (  selectedTab == 1) {
//                            if (mSubListAdapter1.getItemCount() == 0)
//                                Toast.makeText(UserSelection2.this, "Couldn't Process Your request.Kindly Try Again", Toast.LENGTH_SHORT).show();
//                        } else if ( selectedTab == 2) {
//                            if (mSubListAdapter2.getItemCount() == 0)
//                                Toast.makeText(UserSelection2.this, "Couldn't Process Your request.Kindly Try Again", Toast.LENGTH_SHORT).show();
//                        }else if ( selectedTab == 1) {
//                            if (mSubListAdapter3.getItemCount() == 0)
//                                Toast.makeText(UserSelection2.this, "Couldn't Process Your request.Kindly Try Again", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }, 10000);
//
//
//            }
//    });







}

    public void showCountOfPlacedUsers() {
        filterCountLayout.setVisibility(View.VISIBLE);

        int count = 0;
        for (int i = 0; i < subList2.size(); i++) {
            if (subList2.get(i).isSelected())
                count++;
        }
        filterCount.setText("" + count);
    }

    public boolean wasShortlistedInMainList(String username) {
        boolean isSuccess = false;

        for (int i = 0; i < MainList.size(); i++) {

            if (MainList.get(i).getEmail().equals(username)) {
                if (MainList.get(i).isShortListed())
                    isSuccess = true;
            }
        }

        return isSuccess;
    }

    public boolean wasPlacedInMainList(String username) {
        boolean isSuccess = false;

        for (int i = 0; i < MainList.size(); i++) {

            if (MainList.get(i).getEmail().equals(username)) {
                if (MainList.get(i).isPlaced())
                    isSuccess = true;
            }
        }

        return isSuccess;
    }

    private void buildListsAsPerMainList() {
        subList1.clear();

        for (int i = 0; i < MainList.size(); i++) {

            SubListModal obj1 = null;
            if (MainList.get(i).isShortListed())
                obj1 = new SubListModal(MainList.get(i).getName(), MainList.get(i).getEmail(), true);
            else
                obj1 = new SubListModal(MainList.get(i).getName(), MainList.get(i).getEmail(), false);

            if (obj1 != null)
                subList1.add(obj1);

            SubListModal obj2 = null;
            if (MainList.get(i).isPlaced()) {
                obj2 = new SubListModal(MainList.get(i).getName(), MainList.get(i).getEmail(), true);
                if (!isInList3(MainList.get(i).getEmail()))
                    subList3.add(0, obj2);
            } else if (MainList.get(i).isShortListed() && !MainList.get(i).isPlaced())
                obj2 = new SubListModal(MainList.get(i).getName(), MainList.get(i).getEmail(), false);

            if (obj2 != null) {
                if (!isInList2(MainList.get(i).getEmail()))
                    subList2.add(0, obj2);
            }
        }


        mSubListAdapter1.notifyDataSetChanged();
        mSubListAdapter2.notifyDataSetChanged();
        mSubListAdapter3.notifyDataSetChanged();
    }

    private boolean isInList3(String username) {
        boolean found = false;

        for (int i = 0; i < subList3.size(); i++) {
            if (subList3.get(i).getEmail().equals(username)) {
                found = true;
                break;
            }
        }

        return found;
    }

    private boolean isInList2(String username) {
        boolean found = false;

        for (int i = 0; i < subList2.size(); i++) {
            if (subList2.get(i).getEmail().equals(username)) {
                found = true;
                break;
            }
        }

        return found;
    }

    public void showCountOfShortlistedUsers() {
        filterCountLayout.setVisibility(View.VISIBLE);

        int count = 0;
        for (int i = 0; i < subList1.size(); i++) {
            if (subList1.get(i).isSelected())
                count++;
        }
        filterCount.setText("" + count);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.submit, menu);

        submit = menu.findItem(R.id.action_save);
        select_all = menu.findItem(R.id.action_selectall);
        deselect_all = menu.findItem(R.id.action_deselectall);
        deselect_all.setVisible(false);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:

                if (selectedTab == 1) {
                    updateMainListFromTab1();
                }
                if (selectedTab == 2) {
                    updateMainListFromTab2();
                }

                break;
            case R.id.action_selectall:
                select_all.setVisible(false);
                deselect_all.setVisible(true);
                SelectAll(selectedTab);
                break;
            case R.id.action_deselectall:
                select_all.setVisible(true);
                deselect_all.setVisible(false);
                DeSelectAll(selectedTab);
                break;

        }

        return (super.onOptionsItemSelected(item));
    }

    private void DeSelectAll(int index) {

        if (index == 1) {
            for (int i = 0; i < subList1.size(); i++) {
                for (int j = 0; j < MainList.size(); j++) {
                    if (subList1.get(i).getEmail().equals(MainList.get(j).getEmail())) {
                        if (!MainList.get(j).isShortListed())
                            subList1.get(i).setSelected(false);
                    }
                }
            }
            mSubListAdapter1.notifyDataSetChanged();

            menuFlag1 = 0;
            showCountOfShortlistedUsers();


        } else if (index == 2) {
            for (int i = 0; i < subList2.size(); i++) {
                for (int j = 0; j < MainList.size(); j++) {
                    if (subList2.get(i).getEmail().equals(MainList.get(j).getEmail())) {
                        if (!MainList.get(j).isShortListed())
                            subList2.get(i).setSelected(false);
                    }
                }
            }
            mSubListAdapter2.notifyDataSetChanged();

            menuFlag2 = 0;
            showCountOfPlacedUsers();
        }


    }

    private void SelectAll(int index) {

        if (index == 1) {
            for (int i = 0; i < subList1.size(); i++) {
                subList1.get(i).setSelected(true);
                mSubListAdapter1.notifyDataSetChanged();
            }
            showCountOfShortlistedUsers();
            menuFlag1 = 1;
        } else if (index == 2) {
            for (int i = 0; i < subList2.size(); i++) {
                subList2.get(i).setSelected(true);
                mSubListAdapter2.notifyDataSetChanged();

            }
            showCountOfPlacedUsers();
            menuFlag2 = 1;
        }

    }

    private int getIndexFromMainList(String username) {
        int index = -1;

        for (int i = 0; i < MainList.size(); i++) {
            if (MainList.get(i).getEmail().equals(username)) {
                index = i;
                break;
            }
        }
        return index;
    }


    private void updateMainListFromTab1() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Are you sure You want to shortlist the selected candidates? Once shortlisted, you cannot change the status of the candidates.\n A notification will be sent to selected shortlisted candidates.")
                .setCancelable(false)
                .setPositiveButton("Submit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Methodcall();
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(UserSelection2.this));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(UserSelection2.this));
            }
        });

        alertDialog.show();


    }

    private void Methodcall() {

        for (int i = 0; i < subList1.size(); i++) {
            if (subList1.get(i).isSelected()) {
                int indexInMainList = getIndexFromMainList(subList1.get(i).getEmail());
                Log.d(TAG, "index in mainlist: " + indexInMainList);
                MainList.get(indexInMainList).setShortListed(true);
            }
        }
//savecall
        String ObjectString = "";
        try {
            Log.d(TAG, "updateMainListFromTab1: " + MainList.size());

            ObjectString = OtoString(MainList, MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));

            Log.d(TAG, "ObjectString: " + ObjectString);
        } catch (Exception e) {
            Log.d(TAG, "Error: " + e.getMessage());
            e.printStackTrace();
        }

//make submit button Visible and get updated list from server

        dialog.setMessage("please wait...");
        dialog.setIndeterminate(false);
        dialog.show();
//        submit.setVisible(false);
        UpdatePlacementStudentList(ObjectString, "one");

    }

    private void updateMainListFromTab2() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage("Are you sure You want to place the selected candidates? Once Placed, you cannot change the status of the candidates.\n A notification will be sent to  Placed candidates. ")
                .setCancelable(false)
                .setPositiveButton("Submit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Methodcall2();
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(UserSelection2.this));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(UserSelection2.this));
            }
        });
        alertDialog.show();
        for (int i = 0; i < subList2.size(); i++) {
            if (subList2.get(i).isSelected()) {
                int indexInMainList = getIndexFromMainList(subList2.get(i).getEmail());
                Log.d(TAG, "index in mainlist: " + indexInMainList);
                MainList.get(indexInMainList).setPlaced(true);
            }
        }
    }

    private void Methodcall2() {
        String ObjectString = "";
        try {
            Log.d(TAG, "updateMainListFromTab1: " + MainList.size());

            ObjectString = OtoString(MainList, MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));

            Log.d(TAG, "ObjectString: " + ObjectString);
        } catch (Exception e) {
            Log.d(TAG, "Error: " + e.getMessage());
            e.printStackTrace();
        }

        dialog.setMessage("please wait...");
        dialog.setIndeterminate(false);
        dialog.show();
        UpdatePlacementStudentList(ObjectString, "two");
    }

    public void showResume(String username, String tab,boolean checkstastus ) {

        Intent i1 = new Intent(UserSelection2.this, ViewResume.class);
        i1.putExtra("username", username);

        if (tab.equals("one")) {
            i1.putExtra("from", tab);
            i1.putExtra("checkstastus",checkstastus);
            startActivityForResult(i1, 999);
        } else if (tab.equals("two")) {
            i1.putExtra("from", tab);
            i1.putExtra("checkstastus",checkstastus);
            startActivityForResult(i1, 999);
        } else if (tab.equals("three")) {
            i1.putExtra("from", tab);
            i1.putExtra("checkstastus",checkstastus);
            startActivityForResult(i1, 999);

        }
    }

    private void UpdatePlacementStudentList(String ObjectString, String Tab) {
        AndroidNetworking.post(url_UpdatePlacementStudentList)
                .setTag(this)
                .addQueryParameter("cid", CompanId)
                .addQueryParameter("cname", PlacementCompanyname)
                .addQueryParameter("StrObj", ObjectString)
                .addQueryParameter("tab", Tab)
                .setPriority(Priority.HIGH)
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
                        MainList.clear();
                        try {
                            MainList = (ArrayList<MainListModal>) fromString(response.getString("objstr"), MySharedPreferencesManager.getDigest1(UserSelection2.this), MySharedPreferencesManager.getDigest2(UserSelection2.this));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        buildListsAsPerMainList();

                        if (dialog.isShowing()) {
                            dialog.dismiss();

                            if (selectedTab == 1) {
                                navigation.setSelectedItemId(R.id.navigation_dashboard);
                            } else if (selectedTab == 2) {
                                navigation.setSelectedItemId(R.id.navigation_notifications);
                            }


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
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "resultCode: " + resultCode);
        if (resultCode == 29) {
            mSubListAdapter1.notifyDataSetChanged();
//            Log.d(TAG, "changedStatus change Status");
//            String usernametemp = data.getStringExtra("username");
//            String from  = data.getStringExtra("from");
//            Log.d(TAG, "usernametemp: "+usernametemp);
//            Log.d(TAG, "from: "+from);


        }
}
}
