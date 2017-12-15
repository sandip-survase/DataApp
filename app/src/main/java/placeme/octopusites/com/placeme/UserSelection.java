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

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.fromString;


public class UserSelection extends AppCompatActivity {

    //serverside
    private static String url_SaveShortlisted = "http://192.168.100.100:8080/CreateNotificationTemp/SaveShortListedUsers";
    private static String url_Saveplaced = "http://192.168.100.100:8080/CreateNotificationTemp/SavePlacedUsers";

    private static String url_SaveRegistereduserStatus = "http://192.168.100.100:8080/CreateNotificationTemp/SaveRegistereduserStatus";



    Boolean editregistered=false,editedshortlist=false,editedPlaced=false;






    String paramsList;

    JSONParser jParser = new JSONParser();
    String digest1, digest2;
    JSONObject json;

    Boolean registerd = true, shortlisted = false, placed = false;
    boolean isStarted = false;
    Vibrator myVib;
    int selectedCount = 0;
    View[] selectedViews;
    int selectedPositions[];
    int registerdCount = 0;
    RelativeLayout fabminirl;
    TextView fabCountText;
    Menu menu;
    int RegisteredFabCount = 0, ShortlistedFabCount = 0;
    //shortlisting
    ArrayList<String> selectedIds = new ArrayList<>();
    FloatingActionButton fab, fabmini;
    int deleteflag = 0;

    ArrayList<String> notificationdeleteArraylist = new ArrayList<>();
    String CompanId;
    ArrayList<RecyclerItemUsers> registerdListfromserver;
    ArrayList<RecyclerItemUsers> placedListfromserver;
    ArrayList<RecyclerItemUsers> ShortlistedListfromserver;
    BottomNavigationView navigation;
    private RecyclerView recycler_view_Registered, recycler_view_ShortListed, recycler_view_Placed;

    private ArrayList<RecyclerItemUsers> itemListShortlitedToset = new ArrayList<>();
    private ArrayList<RecyclerItemUsers> itemListShortlitedTosendtoserver = new ArrayList<>();

    private ArrayList<RecyclerItemUsers> itemListPlacedToset = new ArrayList<>();
    //1ST RecyclerView
    private ArrayList<RecyclerItemUsers> itemListRegisterd = new ArrayList<>();
    private RecyclerItemUsersAdapter mAdapterRegisterd;
    //2nd RecyclerView`
    private ArrayList<RecyclerItemUsers> itemListShortlited = new ArrayList<>();
    private RecyclerItemUsersAdapter mAdapterShortlited;
    //2nd RecyclerView
    private ArrayList<RecyclerItemUsers> itemListPlaced = new ArrayList<>();
    private RecyclerItemUsersAdapter mAdapterplaced;
    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            MenuItem itemM = menu.findItem(R.id.action_save);

            switch (item.getItemId()) {
                case R.id.navigation_home:
//
                    itemM.setVisible(true);
                    registerd = true;
                    placed = false;
                    shortlisted = false;
                    recycler_view_Registered.setVisibility(View.VISIBLE);
                    recycler_view_ShortListed.setVisibility(View.GONE);
                    recycler_view_Placed.setVisibility(View.GONE);
                    //call to shortlistServlet
                    Log.d("TAG", "shortlistmethod..itemListShortlitedToset.size:" + itemListShortlitedToset.size());

                    if (RegisteredFabCount != 0) {
                        fabminirl.setVisibility(View.VISIBLE);
                        fabCountText.setText("" + RegisteredFabCount);
                    } else {
                        fabminirl.setVisibility(View.GONE);
                        fabCountText.setText("");
                    }


                    return true;
//                    }
                case R.id.navigation_dashboard:
//
                        itemM.setVisible(true);
                        placed = false;
                        registerd = false;
                        shortlisted = true;
                        recycler_view_Registered.setVisibility(View.GONE);
                        recycler_view_ShortListed.setVisibility(View.VISIBLE);
                        recycler_view_Placed.setVisibility(View.GONE);


                        mAdapterShortlited = new RecyclerItemUsersAdapter(itemListShortlited, UserSelection.this, 2);
                        recycler_view_ShortListed.setAdapter(mAdapterShortlited);
                        mAdapterShortlited.notifyDataSetChanged();
                        if (ShortlistedFabCount != 0) {
                            fabminirl.setVisibility(View.VISIBLE);
                            fabCountText.setText("" + ShortlistedFabCount);
                        } else {
                            fabminirl.setVisibility(View.GONE);
                            fabCountText.setText("");
                        }
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

                    mAdapterplaced = new RecyclerItemUsersAdapter(itemListPlaced, UserSelection.this, 3);
                    recycler_view_Placed.setAdapter(mAdapterplaced);

                    mAdapterplaced.notifyDataSetChanged();

                    fabminirl.setVisibility(View.GONE);
                    fabCountText.setText("");
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
        if (PlacementCompanyname != null && PlacementCompanyname.length() != 0) {
            ab.setTitle(PlacementCompanyname);
        } else {
            ab.setTitle("Company Name");
        }
        CompanId = "" + getIntent().getIntExtra("id", 0);
        Log.d("shd", "onCreate: " + CompanId);

        int tempfabcount = 0;
        Log.d("", "tempfabcount1: " + tempfabcount);

        try {


            registerdListfromserver = (ArrayList<RecyclerItemUsers>) fromString(getIntent().getStringExtra("sRegisteredItemlistTemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));
            ShortlistedListfromserver = (ArrayList<RecyclerItemUsers>) fromString(getIntent().getStringExtra("sShortlistedListsfromservertemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));
            placedListfromserver = (ArrayList<RecyclerItemUsers>) fromString(getIntent().getStringExtra("splacedItemlistTemp"), MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));

            for (int k = 0; k < registerdListfromserver.size(); k++) {
                Log.d("checking", "selected status:::" + k + ":::" + registerdListfromserver.get(k).isSelected());
            }

        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
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


//        1st recyclerView
        mAdapterRegisterd = new RecyclerItemUsersAdapter(itemListRegisterd, UserSelection.this, 1);
        recycler_view_Registered.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_view_Registered.setLayoutManager(mLayoutManager);
        recycler_view_Registered.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recycler_view_Registered.setItemAnimator(new DefaultItemAnimator());
        recycler_view_Registered.setAdapter(mAdapterRegisterd);


        //2nd recyclerView
        mAdapterShortlited = new RecyclerItemUsersAdapter(itemListShortlited, UserSelection.this, 2);
        recycler_view_ShortListed.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(UserSelection.this);
        recycler_view_ShortListed.setLayoutManager(mLayoutManager2);
        recycler_view_ShortListed.addItemDecoration(new DividerItemDecoration(UserSelection.this, LinearLayoutManager.VERTICAL));
        recycler_view_ShortListed.setItemAnimator(new DefaultItemAnimator());
        recycler_view_ShortListed.setAdapter(mAdapterShortlited);


        //3rd recyclerView
        mAdapterplaced = new RecyclerItemUsersAdapter(itemListPlaced, UserSelection.this, 3);
        recycler_view_Placed.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(UserSelection.this);
        recycler_view_Placed.setLayoutManager(mLayoutManager3);
        recycler_view_Placed.addItemDecoration(new DividerItemDecoration(UserSelection.this, LinearLayoutManager.VERTICAL));
        recycler_view_Placed.setItemAnimator(new DefaultItemAnimator());
        recycler_view_Placed.setAdapter(mAdapterplaced);

        recycler_view_Registered.setVisibility(View.VISIBLE);
        recycler_view_ShortListed.setVisibility(View.GONE);
        recycler_view_Placed.setVisibility(View.GONE);

        addUsersdatatoAdapter();
        addShortlisedUserToAdapter();
        addplacedUsersdatatoAdapter();




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerd) {
                    StringBuilder listString =new StringBuilder();
                    for (int k = 0; k < itemListShortlitedToset.size(); k++) {
                        listString.append(itemListShortlitedToset.get(k).getEmail()+",");

                    }

                     Intent i1 = new Intent(UserSelection.this, CreateNotificationHR.class);
                    i1.putExtra("selection","registerd");
                    i1.putExtra("emailids",""+listString);
                    startActivity(i1);

                      Log.d("Tag","listString"+ listString);
                    Toast.makeText(UserSelection.this, "Send notification to:-" + listString + "-:user", Toast.LENGTH_SHORT).show();
                } else if (shortlisted) {
                    StringBuilder listString2 =new StringBuilder();
                    for (int k = 0; k < itemListPlacedToset.size(); k++) {
                        listString2.append(itemListPlacedToset.get(k).getEmail()+",");

                    }
                    Intent i1 = new Intent(UserSelection.this, CreateNotificationHR.class);
                    i1.putExtra("selection","shortlisted");
                    i1.putExtra("id",""+listString2);
                    startActivity(i1);
                    Toast.makeText(UserSelection.this, "Send notification to:-" + listString2 + "-:user", Toast.LENGTH_SHORT).show();
                } else if (placed) {

//                    StringBuilder listString3 =new StringBuilder();
//                    for (int k = 0; k < itemListPlacedToset.size(); k++) {
//                        listString3.append(itemListPlacedToset.get(k).getEmail()+",");
//
//                    }

                    Intent i1 = new Intent(UserSelection.this, CreateNotificationHR.class);
                    i1.putExtra("selection","placed");
                    i1.putExtra("id","ALL");
                    startActivity(i1);

                    Toast.makeText(UserSelection.this, "Send notification to:- All-:user", Toast.LENGTH_SHORT).show();

                }



            }
        });

        Log.d("finaltestUsers", "============from server================: ");
        Log.d("finaltestUsers", "registerdListfromserver: " + registerdListfromserver.size());
        Log.d("finaltestUsers", "ShortlistedListfromserver: " + ShortlistedListfromserver.size());
        Log.d("finaltestUsers", "itemListplacedListfromserver: " + placedListfromserver.size());
        Log.d("finaltestUsers", "=======================================: ");

            RecyclerItemUsers item = new RecyclerItemUsers(100,"","",false);
        for (int k = 0; k < registerdListfromserver.size(); k++) {


            if (registerdListfromserver.get(k).isSelected()) {
                RegisteredFabCount++;
//                if (!itemListShortlitedToset.contains(registerdListfromserver.get(k))) {
//                    itemListShortlitedToset.add(registerdListfromserver.get(k));
//
////                    tempfabcount++;
//                    RegisteredFabCount++;
//                }
            }

        }





        for (int j = 0; j < ShortlistedListfromserver.size(); j++) {


            if (ShortlistedListfromserver.get(j).isSelected()) {
                ShortlistedFabCount++;
//                if (!itemListPlacedToset.contains(ShortlistedListfromserver.get(j))) {
//                    itemListPlacedToset.add(ShortlistedListfromserver.get(j));
//                    ShortlistedFabCount++;
//                }
            }

        }
        Log.d("finaltestUsers", "==============fab counts=================: ");

        Log.d("finaltestUsers", "RegisteredFabCount: " + RegisteredFabCount);
        Log.d("finaltestUsers", "ShortlistedFabCount: " + ShortlistedFabCount);
        Log.d("finaltestUsers", "=========================================: ");



        fabminirl.setVisibility(View.VISIBLE);
        fabCountText.setText("" + RegisteredFabCount);


    }

    void addUsersdatatoAdapter() {

        itemListRegisterd.addAll(registerdListfromserver);

        mAdapterRegisterd.notifyDataSetChanged();

    }

    void addShortlisedUserToAdapter() {


        itemListShortlited.addAll(ShortlistedListfromserver);

        mAdapterShortlited.notifyDataSetChanged();

    }

    void addplacedUsersdatatoAdapter() {

//        for (int i = 0; i < 10; i++) {
//            RecyclerItemUsers item = new RecyclerItemUsers(i, "Placed.....!!!! " + i, "abc" + i + "@gmail.com ", false);
//            itemListPlaced.add(item);
//        }
        itemListPlaced.addAll(placedListfromserver);
        mAdapterplaced.notifyDataSetChanged();


    }

    public void goBack() {
        onBackPressed();
    }

    public void showCount(RecyclerItemUsers object) {
        editregistered=true;

        RecyclerItemUsers item2 = object;
        if (object.isSelected) {

            if (!itemListShortlitedToset.contains(item2)) {
                itemListShortlitedToset.add(item2);
                item2.setSelected(false);
                Log.d("contains", "::" + item2.getEmail() + "::added");
                RegisteredFabCount++;
            } else {
                itemListShortlitedToset.remove(item2);
                RegisteredFabCount--;
                Log.d("contains", "::" + item2.getEmail() + "::removed");
            }
        } else {
            if (!itemListShortlitedToset.contains(item2)) {
                itemListShortlitedToset.add(item2);
                item2.setSelected(false);
                Log.d("contains", "::" + item2.getEmail() + "added");
                RegisteredFabCount++;
            } else {
                itemListShortlitedToset.remove(item2);
                RegisteredFabCount--;
                Log.d("contains", "::" + item2.getEmail() + "removed");
            }

        }

        if (RegisteredFabCount != 0) {
            fabminirl.setVisibility(View.VISIBLE);
            fabCountText.setText("" + RegisteredFabCount);
        } else {
            fabminirl.setVisibility(View.GONE);
            fabCountText.setText("");
        }
        Log.d("contains", "=====itemListShortlitedToset size===========: " + itemListShortlitedToset.size());

    }

    public void showCount2(RecyclerItemUsers object) {


        RecyclerItemUsers item3 = object;
        if (object.isSelected) {
            if (!itemListPlacedToset.contains(item3)) {
                itemListPlacedToset.add(item3);
                item3.setSelected(false);
                Log.d("contains", "::" + item3.getEmail() + "::added");
                ShortlistedFabCount++;
            } else {
                itemListPlacedToset.remove(item3);
                ShortlistedFabCount--;
                Log.d("contains", "::" + item3.getEmail() + "::removed");
            }
        } else {
            if (!itemListPlacedToset.contains(item3)) {
                itemListPlacedToset.add(item3);
                item3.setSelected(false);
                Log.d("contains", "::" + item3.getEmail() + "added");
                ShortlistedFabCount++;
            } else {
                itemListPlacedToset.remove(item3);
                ShortlistedFabCount--;
                Log.d("contains", "::" + item3.getEmail() + "removed");
            }

        }

        if (ShortlistedFabCount != 0) {
            fabminirl.setVisibility(View.VISIBLE);
            fabCountText.setText("" + ShortlistedFabCount);
        } else {
            fabminirl.setVisibility(View.GONE);
            fabCountText.setText("");
        }
        Log.d("contains", "=====itemListShortlitedToset size===========: " + itemListPlacedToset.size());


    }

    public void showResume(String username) {

        Toast.makeText(this, "Show Resume Of usr" + username, Toast.LENGTH_SHORT).show();

        Intent i1 = new Intent(UserSelection.this, ViewResume.class);
        i1.putExtra("username", username);
        startActivity(i1);


        Toast.makeText(this, "after", Toast.LENGTH_SHORT).show();



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

        //from server



        //from same activity
        if (itemListShortlited.size() == 0) {
            itemListShortlited.addAll(itemListShortlitedToset);

        } else {


            for (int j = 0; j < itemListShortlitedToset.size(); j++) {

                if (itemListShortlited.contains(itemListShortlitedToset.get(j))) {
                    //already
                    Log.d("contains", "already present: " + itemListShortlitedToset.get(j).getEmail());
                } else {
                    Log.d("contains", "not present: " + itemListShortlitedToset.get(j).getEmail());
                    itemListShortlited.add(itemListShortlitedToset.get(j));


                }
            }

        }
        new SaveShortlisted().execute();


        Log.d("itemlistsize", "========temp size ==============: " + itemListShortlitedToset.size());
        Log.d("itemlistsize", "========final size=============: " + itemListShortlited.size());

        for (int i = 0; i < itemListShortlitedToset.size(); i++) {
            Log.d("itemlistsize", "======status temp==========: " + itemListShortlitedToset.get(i).isSelected());

        }
        for (int i = 0; i < itemListShortlited.size(); i++) {
            Log.d("itemlistsize", "======status final==========: " + itemListShortlited.get(i).isSelected());

        }
        navigation.setSelectedItemId(R.id.navigation_dashboard);


    }

    public void placeS() {

        if (itemListPlaced.size() == 0) {
            itemListPlaced.addAll(itemListPlacedToset);

        } else {


            for (int j = 0; j < itemListPlacedToset.size(); j++) {

                if (itemListPlaced.contains(itemListPlacedToset.get(j))) {
                    //already
                    Log.d("contains", "already present: " + itemListPlacedToset.get(j).getEmail());
                } else {
                    Log.d("contains", "not present: " + itemListPlacedToset.get(j).getEmail());
                    itemListPlaced.add(itemListPlacedToset.get(j));


                }
            }

        }


        Log.d("itemlistsize", "========temp size ==============: " + itemListPlacedToset.size());
        Log.d("itemlistsize", "========final size=============: " + itemListPlaced.size());

        for (int i = 0; i < itemListPlacedToset.size(); i++) {
            Log.d("itemlistsize", "======status temp==========: " + itemListPlacedToset.get(i).isSelected());

        }
        for (int i = 0; i < itemListPlaced.size(); i++) {
            Log.d("itemlistsize", "======status final==========: " + itemListPlaced.get(i).isSelected());

        }


        new SavePlaced().execute();

//        itemListPlaced.addAll(itemListPlacedToset);


        navigation.setSelectedItemId(R.id.navigation_notifications);


    }


    public void getShortlisted() {

        try {
//            private ArrayList<RecyclerItemUsers> itemListRegisterd = new ArrayList<>();

//            ArrayList<RecyclerItemUsers> chatList2=(ArrayList<RecyclerItemUsers>)fromString(paramsList);
//            for(int i=0;i<chatList2.size();i++)
//            {
//                Log.d("paramsList2", "paramsList2: "+chatList2.get(i).getName());
//                System.out.println(chatList2.get(i).getName());
//            }

        } catch (Exception e) {
            Toast.makeText(this, "getShortlisted" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    class SaveShortlisted extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            try {

                String shortlistusersToSend = OtoString(itemListShortlitedToset, MySharedPreferencesManager.getDigest1(UserSelection.this), MySharedPreferencesManager.getDigest2(UserSelection.this));
                params.add(new BasicNameValuePair("u", CompanId));                    //0
                params.add(new BasicNameValuePair("lobj", shortlistusersToSend));    //1
                Log.d("TAG", "doInBackground: " + CompanId);
            } catch (Exception e) {

            }

            json = jParser.makeHttpRequest(url_SaveShortlisted, "GET", params);
            try {

                r = json.getString("info");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            itemListShortlitedToset.clear();
            if (result.equals("success")) {
                Toast.makeText(UserSelection.this, "Successfully Shortlisted", Toast.LENGTH_SHORT).show();

            } else {

//                Toast.makeText(UserSelection.this, "ooops something went Wrong...!!", Toast.LENGTH_SHORT).show();
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

            json = jParser.makeHttpRequest(url_Saveplaced, "GET", params);
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




    public void fromserver(){
        if (itemListShortlited.size() == 0) {









            itemListShortlited.addAll(itemListShortlitedToset);

        } else {


            for (int j = 0; j < itemListShortlitedToset.size(); j++) {

                if (itemListShortlited.contains(itemListShortlitedToset.get(j))) {
                    //already
                    Log.d("contains", "already present: " + itemListShortlitedToset.get(j).getEmail());
                } else {
                    Log.d("contains", "not present: " + itemListShortlitedToset.get(j).getEmail());
                    itemListShortlited.add(itemListShortlitedToset.get(j));


                }
            }

        }
        new SaveShortlisted().execute();

    }

}
