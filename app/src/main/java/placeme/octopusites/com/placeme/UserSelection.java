package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
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
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.RecyclerItemUsers;
import placeme.octopusites.com.placeme.modal.RecyclerItemUsersAdapter;
import placeme.octopusites.com.placeme.modal.RecyclerItemUsersAdapter2;
import placeme.octopusites.com.placeme.modal.RecyclerItemUsersAdapter3;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.fromString;


public class UserSelection extends AppCompatActivity {
    Boolean editregistered = false, editedshortlist = false, editedPlaced = false;
    JSONParser jParser = new JSONParser();
    String digest1, digest2;
    JSONObject json;
    Boolean registerd = true, shortlisted = false, placed = false;
    Vibrator myVib;
    RelativeLayout fabminirl;
    TextView fabCountText;
    Menu menu;
    int RegisteredFabCount = 0, ShortlistedFabCount = 0, placedFabCount = 0;
    //shortlisting
    FloatingActionButton fab, fabmini;
    String CompanId;
    BottomNavigationView navigation;

    //FromServerArraylists
    ArrayList<RecyclerItemUsers> MasterListfromserver;
    //
//    ArrayList<RecyclerItemUsers> registerdListfromserver;
//    ArrayList<RecyclerItemUsers> placedListfromserver;
//    ArrayList<RecyclerItemUsers> ShortlistedListfromserver;
    private String TAG = "UserSelection";

    private RecyclerView recycler_view_Registered, recycler_view_ShortListed, recycler_view_Placed;
    //1ST RecyclerView
    private ArrayList<RecyclerItemUsers> itemListRegisterd = new ArrayList<>();
    private RecyclerItemUsersAdapter mAdapterRegisterd;
    //2nd RecyclerView`
    private ArrayList<RecyclerItemUsers> itemListShortlited = new ArrayList<>();
    private RecyclerItemUsersAdapter2 mAdapterShortlited;
    //2nd RecyclerView
    private ArrayList<RecyclerItemUsers> itemListPlaced = new ArrayList<>();
    private RecyclerItemUsersAdapter3 mAdapterplaced;
    //aRRAYLISTS TOSET
    private ArrayList<RecyclerItemUsers> itemListPlacedToset = new ArrayList<>();

    private ArrayList<RecyclerItemUsers> itemListShortlitedToset = new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            MenuItem itemM = menu.findItem(R.id.action_save);

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    itemM.setVisible(true);
                    registerd = true;
                    placed = false;
                    shortlisted = false;
                    recycler_view_Registered.setVisibility(View.VISIBLE);
                    recycler_view_ShortListed.setVisibility(View.GONE);
                    recycler_view_Placed.setVisibility(View.GONE);

                    itemListRegisterd.clear();
                    RegisteredFabCount = 0;
                    refreshContents();
                    if (RegisteredFabCount != 0) {
                        fabminirl.setVisibility(View.VISIBLE);
                        fabCountText.setText("" + RegisteredFabCount);
                    } else {
                        fabminirl.setVisibility(View.GONE);
                        fabCountText.setText("");
                    }

                    mAdapterRegisterd = new RecyclerItemUsersAdapter(itemListRegisterd, UserSelection.this, 1);
                    recycler_view_Registered.setAdapter(mAdapterRegisterd);
                    mAdapterRegisterd.notifyDataSetChanged();
                    return true;
//                    }
                case R.id.navigation_dashboard:

                    itemM.setVisible(true);
                    placed = false;
                    registerd = false;
                    shortlisted = true;
                    recycler_view_Registered.setVisibility(View.GONE);
                    recycler_view_ShortListed.setVisibility(View.VISIBLE);
                    recycler_view_Placed.setVisibility(View.GONE);

                    itemListShortlited.clear();
                    ShortlistedFabCount = 0;
                    refreshContents();
                    if (ShortlistedFabCount != 0) {
                        fabminirl.setVisibility(View.VISIBLE);
                        fabCountText.setText("" + ShortlistedFabCount);
                    } else {
                        fabminirl.setVisibility(View.GONE);
                        fabCountText.setText("");
                    }
                    mAdapterShortlited = new RecyclerItemUsersAdapter2(itemListShortlited, UserSelection.this, 2);
                    recycler_view_ShortListed.setAdapter(mAdapterShortlited);
                    mAdapterShortlited.notifyDataSetChanged();

//                    }
                    return true;
                case R.id.navigation_notifications:
                    itemM.setVisible(false);
                    placed = true;
                    registerd = false;
                    shortlisted = false;
                    recycler_view_Registered.setVisibility(View.GONE);
                    recycler_view_ShortListed.setVisibility(View.GONE);
                    recycler_view_Placed.setVisibility(View.VISIBLE);
                    if (placedFabCount != 0) {
                        fabminirl.setVisibility(View.VISIBLE);
                        fabCountText.setText("" + placedFabCount);
                    } else {
                        fabminirl.setVisibility(View.GONE);
                        fabCountText.setText("");
                    }
                    itemListPlaced.clear();
                    refreshContents();
                    mAdapterplaced = new RecyclerItemUsersAdapter3(itemListPlaced, UserSelection.this, 3);
                    recycler_view_Placed.setAdapter(mAdapterplaced);
                    mAdapterplaced.notifyDataSetChanged();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

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



            for (int i = 0; i < 10 ; i++) {
                RecyclerItemUsers obj =new RecyclerItemUsers(0+i,"Sunny"+i,"s@a.com"+i,false,false,false,true,false,false);
                Log.d(TAG, "obj obj.getId() :"+obj.getId());
                Log.d(TAG, "obj obj.getEmail() :"+obj.getEmail());
                Log.d(TAG, "obj obj.getName() :"+obj.getName());
                Log.d(TAG, "obj obj.isSelected :"+obj.isSelected);
                Log.d(TAG, "obj email :"+obj.isSelected2);
                Log.d(TAG, "obj email :"+obj.isSelected3);
                Log.d(TAG, "obj email :"+obj.isregistered);
                Log.d(TAG, "obj email :"+obj.isshortlisted);
                Log.d(TAG, "obj email :"+obj.isPlaced);

                MasterListfromserver.add(obj);
            }
            Log.d(TAG,""+ MasterListfromserver.size());
        try {

//            MasterListfromserver = (ArrayList<RecyclerItemUsers>) fromString(getIntent().getStringExtra("sRegisteredItemlistTemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));

//            registerdListfromserver = (ArrayList<RecyclerItemUsers>) fromString(getIntent().getStringExtra("sRegisteredItemlistTemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));
//            ShortlistedListfromserver = (ArrayList<RecyclerItemUsers>) fromString(getIntent().getStringExtra("sShortlistedListsfromservertemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));
//            placedListfromserver = (ArrayList<RecyclerItemUsers>) fromString(getIntent().getStringExtra("splacedItemlistTemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));




        } catch (Exception e) {
//
            Toast.makeText(this, "here" + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("", "e.getMessage(): " + e.getMessage());
        }


        ab.setDisplayHomeAsUpEnabled(true);
        Window window = UserSelection.this.getWindow();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabmini = (FloatingActionButton) findViewById(R.id.fab2);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        fabminirl = (RelativeLayout) findViewById(R.id.fabminirl);
        fabCountText = (TextView) findViewById(R.id.fabCountText);

        recycler_view_Registered = (RecyclerView) findViewById(R.id.recycler_view_Registered);
        recycler_view_ShortListed = (RecyclerView) findViewById(R.id.recycler_view_ShortListed);
        recycler_view_Placed = (RecyclerView) findViewById(R.id.recycler_view_Placed);

        //Initially
        refreshContents();
        Log.d(TAG, "============from server list sizes================: ");
        Log.d(TAG, "registerdListfromserver: " + itemListRegisterd.size());
        Log.d(TAG, "ShortlistedListfromserver: " + itemListShortlited.size());
        Log.d(TAG, "itemListplacedListfromserver: " + itemListPlaced.size());
        Log.d(TAG, "===================================================: ");

        //1st recyclerView
        mAdapterRegisterd = new RecyclerItemUsersAdapter(itemListRegisterd, UserSelection.this, 1);
        recycler_view_Registered.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_view_Registered.setLayoutManager(mLayoutManager);
        recycler_view_Registered.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recycler_view_Registered.setItemAnimator(new DefaultItemAnimator());
        recycler_view_Registered.setAdapter(mAdapterRegisterd);

        //2nd recyclerView
        mAdapterShortlited = new RecyclerItemUsersAdapter2(itemListShortlited, UserSelection.this, 2);
        recycler_view_ShortListed.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(UserSelection.this);
        recycler_view_ShortListed.setLayoutManager(mLayoutManager2);
        recycler_view_ShortListed.addItemDecoration(new DividerItemDecoration(UserSelection.this, LinearLayoutManager.VERTICAL));
        recycler_view_ShortListed.setItemAnimator(new DefaultItemAnimator());
        recycler_view_ShortListed.setAdapter(mAdapterShortlited);

        //3rd recyclerView
        mAdapterplaced = new RecyclerItemUsersAdapter3(itemListPlaced, UserSelection.this, 3);
        recycler_view_Placed.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(UserSelection.this);
        recycler_view_Placed.setLayoutManager(mLayoutManager3);
        recycler_view_Placed.addItemDecoration(new DividerItemDecoration(UserSelection.this, LinearLayoutManager.VERTICAL));
        recycler_view_Placed.setItemAnimator(new DefaultItemAnimator());

        recycler_view_Placed.setAdapter(mAdapterplaced);
        recycler_view_Registered.setVisibility(View.VISIBLE);
        recycler_view_ShortListed.setVisibility(View.GONE);
        recycler_view_Placed.setVisibility(View.GONE);

//sending notification to selected
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerd) {
                    StringBuilder listString = new StringBuilder();
                    for (int k = 0; k < itemListShortlitedToset.size(); k++) {
                        listString.append(itemListShortlitedToset.get(k).getEmail() + ",");
                    }


                    Log.d("Tag", "listString" + listString);
//                    Toast.makeText(UserSelection.this, "Send notification to:-" + listString + "-:user", Toast.LENGTH_SHORT).show();
                } else if (shortlisted) {
                    StringBuilder listString2 = new StringBuilder();
                    for (int k = 0; k < itemListPlacedToset.size(); k++) {
                        listString2.append(itemListPlacedToset.get(k).getEmail() + ",");

                    }
                    Intent i1 = new Intent(UserSelection.this, CreateNotificationHR.class);
                    i1.putExtra("selection", "shortlisted");
                    i1.putExtra("id", "" + listString2);
                    startActivity(i1);
//                    Toast.makeText(UserSelection.this, "Send notification to:-" + listString2 + "-:user", Toast.LENGTH_SHORT).show();
                } else if (placed) {

//                    StringBuilder listString3 =new StringBuilder();
//                    for (int k = 0; k < itemListPlacedToset.size(); k++) {
//                        listString3.append(itemListPlacedToset.get(k).getEmail()+",");
//
//                    }

                    Intent i1 = new Intent(UserSelection.this, CreateNotificationHR.class);
                    i1.putExtra("selection", "placed");
                    i1.putExtra("id", "ALL");
                    startActivity(i1);

//                    Toast.makeText(UserSelection.this, "Send notification to:- All-:user", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }


    public void showResume(String username) {

//        Toast.makeText(this, "Show Resume Of usr" + username, Toast.LENGTH_SHORT).show();

        Intent i1 = new Intent(UserSelection.this, ViewResume.class);
        i1.putExtra("username", username);
        startActivity(i1);

//        Toast.makeText(this, "after", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.admin_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if (registerd)
                    shortlist();
                else if (shortlisted)
                    placeS();

                break;

            case android.R.id.home:
                onBackPressed();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    public void shortlist() {

        for (int i = 0; i < MasterListfromserver.size(); i++) {
            for (int j = 0; j <itemListRegisterd.size() ; j++) {
                if(itemListRegisterd.get(i).isSelected){
                    MasterListfromserver.get(i).setIsshortlisted(true);
                }
            }
        }
        navigation.setSelectedItemId(R.id.navigation_dashboard);
    }

    public void placeS() {

        for (int i = 0; i < MasterListfromserver.size(); i++) {
            for (int j = 0; j <itemListShortlited.size() ; j++) {
                if(itemListShortlited.get(i).isSelected){
                    MasterListfromserver.get(i).setPlaced(true);
                }
            }
        }
        navigation.setSelectedItemId(R.id.navigation_notifications);
    }

    private void refreshContents() {

        Log.d(TAG, "====================refreshing Contents================================");
        RecyclerItemUsers item;
        RegisteredFabCount=0;
        for (int k = 0; k < MasterListfromserver.size(); k++) {
            if (MasterListfromserver.get(k).isIsregistered()) {
                item = MasterListfromserver.get(k);
                if (MasterListfromserver.get(k).isSelected()) {
                    item.setSelected(true);
                    RegisteredFabCount++;
                } else {
                    item.setSelected(false);
                }
                Log.d(TAG, " 1 adding item email:"+item.getEmail()+" SelectedStatus "+item.isSelected);
                itemListRegisterd.add(item);
            }
        }
        RecyclerItemUsers item2;
        for (int k = 0; k < MasterListfromserver.size(); k++) {
            Log.d(TAG, "shortlisted  status of Listfromserver:" + k + ":::" + MasterListfromserver.get(k).isIsshortlisted());
            if (MasterListfromserver.get(k).isIsshortlisted()) {
                item2 = MasterListfromserver.get(k);
                if (MasterListfromserver.get(k).isSelected2()) {
                    item2.setSelected(true);
                    ShortlistedFabCount++;
                } else {
                    item2.setSelected(false);
                }
                Log.d(TAG, " 2 adding item email:"+item2.getEmail()+" SelectedStatus "+item2.isSelected);
                itemListShortlited.add(item2);
            }
        }
        for (int k = 0; k < MasterListfromserver.size(); k++) {
            Log.d(TAG, "placed Status OF Listfromserver:" + k + ":::" + MasterListfromserver.get(k).isPlaced());
            if (MasterListfromserver.get(k).isPlaced()) {
                itemListPlaced.add(MasterListfromserver.get(k));
            }
        }
        Log.d(TAG, "========================/refreshing Contents====================================");

        Log.d(TAG, "==============fab counts=================: ");
        Log.d(TAG, "RegisteredFabCount: " + RegisteredFabCount);
        Log.d(TAG, "ShortlistedFabCount: " + ShortlistedFabCount);
        Log.d(TAG, "placedFabCount: " + placedFabCount);
        Log.d(TAG, "=========================================: ");
        //getting status

        Log.d(TAG, "==============Status After refreshing=================: ");
        Log.d(TAG, "itemListRegisterd size " + itemListRegisterd.size());
        for (int i = 0; i < itemListRegisterd.size(); i++) {
            Log.d(TAG, "after refreshing Email" + itemListRegisterd.get(i).getEmail() + " status" + itemListRegisterd.get(i).isSelected());
        }
        for (int i = 0; i < itemListShortlited.size(); i++) {
            Log.d(TAG, "after refreshing Email" + itemListShortlited.get(i).getEmail() + " status" + itemListShortlited.get(i).isSelected());
        }
        Log.d(TAG, "==============/Status After refreshing=================: ");
    }


    public void showCountAndSetChecked(RecyclerItemUsers item) {
        editregistered = true;

        RegisteredFabCount = 0;
        for (int i = 0; i < itemListRegisterd.size(); i++) {
            if (itemListRegisterd.get(i).isSelected) {
                RegisteredFabCount++;
            }
        }
        if (RegisteredFabCount != 0) {
            fabminirl.setVisibility(View.VISIBLE);
            fabCountText.setText("" + RegisteredFabCount);
        } else {
            fabminirl.setVisibility(View.GONE);
            fabCountText.setText("");
        }
    }

    public void showCountAndSetChecked2(RecyclerItemUsers item) {
        editregistered = true;
        ShortlistedFabCount = 0;
        for (int i = 0; i < MasterListfromserver.size(); i++) {
            if (MasterListfromserver.get(i).isSelected2) {
                ShortlistedFabCount++;
            }
        }

        if (ShortlistedFabCount != 0) {
            fabminirl.setVisibility(View.VISIBLE);
            fabCountText.setText("" + ShortlistedFabCount);
        } else {
            fabminirl.setVisibility(View.GONE);
            fabCountText.setText("");
        }
    }

    class SaveShortlisted extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            try {
                Log.d(TAG, "ShortlistedListfromserver: " + MasterListfromserver.size());

                String shortlistusersToSend = OtoString(MasterListfromserver, MySharedPreferencesManager.getDigest1(UserSelection.this), MySharedPreferencesManager.getDigest2(UserSelection.this));
                params.add(new BasicNameValuePair("u", CompanId));                    //0
                params.add(new BasicNameValuePair("lobj", shortlistusersToSend));    //1
                Log.d("TAG", "doInBackground: " + CompanId);
            } catch (Exception e) {

            }

            json = jParser.makeHttpRequest(Z.url_SaveShortListedUsers, "GET", params);
            try {

                r = json.getString("info");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            MasterListfromserver.clear();
            if (result.equals("success")) {
                Toast.makeText(UserSelection.this, "Successfully Shortlisted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(UserSelection.this, "ooops something went Wrong...!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class SavePlaced extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            try {
                String itemListPlacedToSend = OtoString(itemListPlacedToset, MySharedPreferencesManager.getDigest1(UserSelection.this), MySharedPreferencesManager.getDigest2(UserSelection.this));
                params.add(new BasicNameValuePair("u", CompanId));                    //0
                params.add(new BasicNameValuePair("lobj", itemListPlacedToSend));    //1
                Log.d("TAG", "doInBackground: " + CompanId);


            } catch (Exception e) {

            }

            json = jParser.makeHttpRequest(Z.url_SavePlacedUsers, "GET", params);
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
                Toast.makeText(UserSelection.this, "Successfully placed", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(UserSelection.this, "ooops something went Wrong...!!", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
