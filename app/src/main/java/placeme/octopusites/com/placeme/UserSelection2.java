package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import placeme.octopusites.com.placeme.modal.MainListModal;
import placeme.octopusites.com.placeme.modal.RecyclerItem;
import placeme.octopusites.com.placeme.modal.SubListAdapter1;
import placeme.octopusites.com.placeme.modal.SubListAdapter2;
import placeme.octopusites.com.placeme.modal.SubListAdapter3;
import placeme.octopusites.com.placeme.modal.SubListModal;

public class UserSelection2 extends AppCompatActivity {

    public boolean Usereditedflag = false;
    public RecyclerItem checkboxs;
    int selectedTab = 1;
    int menuFlag1 = 0, menuFlag2 = 0, menuFlag3 = 0;
    String TAG = "TAG";
    MenuItem select_all, deselect_all;
    FloatingActionButton fab, fabmini;
    TextView filterCount;
    RelativeLayout filterCountLayout;
    String CompanId;


    private RecyclerView recycler_view_Registered, recycler_view_ShortListed, recycler_view_Placed;
    private ArrayList<MainListModal> MainList = new ArrayList<>();
    private ArrayList<SubListModal> subList1 = new ArrayList<>();
    private ArrayList<SubListModal> subList2 = new ArrayList<>();
    private ArrayList<SubListModal> subList3 = new ArrayList<>();
    private SubListAdapter1 mSubListAdapter1;
    private SubListAdapter2 mSubListAdapter2;
    private SubListAdapter3 mSubListAdapter3;
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
        ActionBar ab = getSupportActionBar();
        String PlacementCompanyname = "";
        PlacementCompanyname = getIntent().getStringExtra("companyname");

        //setting title
        if (PlacementCompanyname != null && PlacementCompanyname.length() != 0) {
            ab.setTitle(PlacementCompanyname);
            Log.d(TAG, "Company Name: " + PlacementCompanyname);
        } else {
            ab.setTitle("Company Name");
        }

        //getting companyId
        CompanId = "" + getIntent().getIntExtra("id", 0);
        Log.d(TAG, "CompanId " + CompanId);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        filterCount = findViewById(R.id.filterCount);
        filterCountLayout = findViewById(R.id.filterCountLayout);
        fab = (FloatingActionButton) findViewById(R.id.fabSendNotification);


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

        for (int i = 0; i < 5; i++) {
            MainListModal obj = new MainListModal("Raju" + i, "abc@abc.com" + i, false, false);
            MainList.add(obj);
        }

        MainListModal obj = new MainListModal("kunal 2", "kunal@student3.com" , false, false);
        MainList.add(obj);
         obj = new MainListModal("kunal 3", "kunal@student2.com" , false, false);
        MainList.add(obj);

        MainList.get(3).setShortListed(true);
        MainList.get(3).setPlaced(true);

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

                    Toast.makeText(UserSelection2.this, "send Notification to selected Registered users: " + sb.toString(), Toast.LENGTH_SHORT).show();

                } else if (selectedTab == 2) {

                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < subList2.size(); i++) {
                        if (subList2.get(i).isSelected())
                            sb.append(subList2.get(i).getEmail() + ",");
                    }
                    Toast.makeText(UserSelection2.this, "send Notification to selected Shortlisted users" + sb.toString(), Toast.LENGTH_SHORT).show();
                } else if (selectedTab == 3) {

                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < subList3.size(); i++) {
                        sb.append(subList3.get(i).getEmail() + ",");
                    }
                    Toast.makeText(UserSelection2.this, "send Notification toAll Placed users"+ sb.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


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

        for (int i = 0; i < subList1.size(); i++) {
            if (subList1.get(i).isSelected()) {
                int indexInMainList = getIndexFromMainList(subList1.get(i).getEmail());
                Log.d(TAG, "index in mainlist: " + indexInMainList);
                MainList.get(indexInMainList).setShortListed(true);
            }
        }
        buildListsAsPerMainList();
//savecall
//        UpdatePlacementStudentList();


            }
    private void updateMainListFromTab2() {

        for (int i = 0; i < subList2.size(); i++) {

            if (subList2.get(i).isSelected()) {
                int indexInMainList = getIndexFromMainList(subList2.get(i).getEmail());
                Log.d(TAG, "index in mainlist: " + indexInMainList);
                MainList.get(indexInMainList).setPlaced(true);
            }

        }

        buildListsAsPerMainList();
        //savecall


    }

    public void showResume(String username) {
        Intent i1 = new Intent(UserSelection2.this, ViewResume.class);
        i1.putExtra("username", username);
        startActivity(i1);

    }

//    private void UpdatePlacementStudentList() {
////        AndroidNetworking.get(Z.url_GetNotificationsAdminAdminMetaData)
//        AndroidNetworking.post(Z.UpdatePlacementStudentList)
//                .setTag(this)
//                .addQueryParameter("cid", CompanId)
//                .setPriority(Priority.MEDIUM)
//                .setOkHttpClient(OkHttpUtil.getClient())
//                .getResponseOnlyFromNetwork()
//                .build()
//                .setAnalyticsListener(new AnalyticsListener() {
//                    @Override
//                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
//                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
//                        Log.d(TAG, " bytesSent : " + bytesSent);
//                        Log.d(TAG, " bytesReceived : " + bytesReceived);
//                        Log.d(TAG, " isFromCache : " + isFromCache);
//                    }
//                })
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//
//                        Log.d(TAG, "onResponse object : " + response.toString());
//
//
//                    }
//
//                    @Override
//                    public void onError(ANError error) {
//                        if (error.getErrorCode() != 0) {
//                            // received ANError from server
//                            // error.getErrorCode() - the ANError code from server
//                            // error.getErrorBody() - the ANError body from server
//                            // error.getErrorDetail() - just a ANError detail
//                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
//                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
//                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
//
//                        } else {
//                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
//                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
//
//                        }
//                    }
//                });
//    }

}
