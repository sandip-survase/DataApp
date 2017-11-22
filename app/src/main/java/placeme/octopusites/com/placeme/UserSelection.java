package placeme.octopusites.com.placeme;

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
import android.util.Base64;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;


public class UserSelection extends AppCompatActivity  {

    //serverside
    private static String url_SaveShortlisted= "http://192.168.100.30/CreateNotificationTemp/saveShortlistList";
    String paramsList;

    JSONParser jParser = new JSONParser();
    String digest1,digest2;
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
    boolean refreshflagRegisterd = false,refreshflagShortlist= false;
    ArrayList<String> notificationdeleteArraylist = new ArrayList<>();
    private RecyclerView recycler_view_Registered, recycler_view_ShortListed, recycler_view_Placed;




    private ArrayList<RecyclerItemUsers> itemListShortlitedToset = new ArrayList<>();
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
//                    mTextMessage.setText(R.string.title_home);
                    registerd = true;
                    placed = false;
                    shortlisted = false;
                    recycler_view_Registered.setVisibility(View.VISIBLE);
                    recycler_view_ShortListed.setVisibility(View.GONE);
                    recycler_view_Placed.setVisibility(View.GONE);
                    itemM.setVisible(true);
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
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    placed = false;
                    registerd = false;
                    shortlisted = true;
                    recycler_view_Registered.setVisibility(View.GONE);
                    recycler_view_ShortListed.setVisibility(View.VISIBLE);
                    recycler_view_Placed.setVisibility(View.GONE);
                    itemM.setVisible(true);


                    mAdapterShortlited = new RecyclerItemUsersAdapter(itemListShortlited, UserSelection.this, 2);
                    recycler_view_ShortListed.setAdapter(mAdapterShortlited);
                    addShortlisedUserToAdapter();

                    if (ShortlistedFabCount != 0) {
                        fabminirl.setVisibility(View.VISIBLE);
                        fabCountText.setText("" + ShortlistedFabCount);
                    } else {
                        fabminirl.setVisibility(View.GONE);
                        fabCountText.setText("");
                    }
                    return true;
                case R.id.navigation_notifications:

                    placed = true;
                    registerd = false;
                    shortlisted = false;
                    itemM.setVisible(false);
                    recycler_view_Registered.setVisibility(View.GONE);
                    recycler_view_ShortListed.setVisibility(View.GONE);
                    recycler_view_Placed.setVisibility(View.VISIBLE);

                    mAdapterplaced = new RecyclerItemUsersAdapter(itemListPlaced, UserSelection.this, 3);
                    recycler_view_Placed.setAdapter(mAdapterplaced);
                    addplacedUsersdatatoAdapter();
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


        ab.setDisplayHomeAsUpEnabled(true);
        Window window = UserSelection.this.getWindow();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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

        addUsersdatatoAdapter();
        refreshflagRegisterd = true;


        fab.setOnClickListener(new View.OnClickListener() {
            String listString = "";

            @Override
            public void onClick(View v) {
                if (registerd) {
                    for (String s : selectedIds) {
                        listString += s + "\t";
                    }
                    Toast.makeText(UserSelection.this, "Send notification to:-" + listString + "-:user", Toast.LENGTH_SHORT).show();
                } else if (shortlisted) {
                    Toast.makeText(UserSelection.this, "Send notification to:-" + listString + "-:user", Toast.LENGTH_SHORT).show();
                } else if (placed) {
                    Toast.makeText(UserSelection.this, "Send notification to:-" + listString + "-:user", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    void addUsersdatatoAdapter() {

        if (!refreshflagRegisterd) {
            //get it from server
            registerdCount = 10;            //servercount
            for (int i = 0; i < registerdCount; i++) {
                RecyclerItemUsers item = new RecyclerItemUsers(i, "Abc " + i, "abc" + i + "@gmail.com ", false);
                itemListRegisterd.add(item);
            }

            mAdapterRegisterd.notifyDataSetChanged();
        }
    }

    void addShortlisedUserToAdapter() {

//        for (int i = 0; i < 10; i++) {
//            RecyclerItemUsers item = new RecyclerItemUsers(i, "Shortlisted....! " + i, "abc" + i + "@gmail.com ",false);
//
//            itemListShortlited.add(item);
//        }
        if (!refreshflagShortlist) {
            mAdapterShortlited.notifyDataSetChanged();
        }
    }

    void addplacedUsersdatatoAdapter() {

//        for (int i = 0; i < 10; i++) {
//            RecyclerItemUsers item = new RecyclerItemUsers(i, "Placed.....!!!! " + i, "abc" + i + "@gmail.com ", false);
//            itemListPlaced.add(item);
//        }

        mAdapterplaced.notifyDataSetChanged();


    }

    public void goBack() {
        onBackPressed();
    }

    public void showCount(RecyclerItemUsers object) {

        if (object.isSelected) {
            RecyclerItemUsers item2 = object;
            if (!itemListShortlitedToset.contains(item2)) {
                item2.setSelected(false);
                itemListShortlitedToset.add(item2);
                RegisteredFabCount++;
            }
        } else {
            for (int i = 0; i < itemListShortlitedToset.size(); i++) {
                if (object.getId() == itemListShortlitedToset.get(i).getId())
                    itemListShortlitedToset.remove(itemListShortlitedToset.get(i));
            }
            RegisteredFabCount--;
        }
        if (RegisteredFabCount != 0) {
            fabminirl.setVisibility(View.VISIBLE);
            fabCountText.setText("" + RegisteredFabCount);
        } else {
            fabminirl.setVisibility(View.GONE);
            fabCountText.setText("");
        }


    }

    public void showCount2(RecyclerItemUsers object) {

        refreshflagShortlist=true;
        if (object.isSelected) {
            RecyclerItemUsers item3 = object;
            if (!itemListPlacedToset.contains(item3)) {
                item3.setSelected(false);
                itemListPlacedToset.add(item3);
                ShortlistedFabCount++;
            }
        } else {
            for (int i = 0; i < itemListPlacedToset.size(); i++) {
                if (object.getId() == itemListPlacedToset.get(i).getId())
                    itemListPlacedToset.remove(itemListPlacedToset.get(i));

            }
            ShortlistedFabCount--;
        }

        if (ShortlistedFabCount != 0) {
            fabminirl.setVisibility(View.VISIBLE);
            fabCountText.setText("" + ShortlistedFabCount);
        } else {
            fabminirl.setVisibility(View.GONE);
            fabCountText.setText("");
        }


        for (int i = 0; i < itemListPlacedToset.size(); i++) {
            if (object.isSelected) {
                Log.d("TAG", "showCount2: ");
            }

        }


    }

    public void showResume(int id) {
        Toast.makeText(this, "Show Resume Of usr" + id, Toast.LENGTH_SHORT).show();
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

    public void shortlist(){
        saveShortlisted();
//        new SaveShortlisted().execute();

        itemListShortlited.addAll(itemListShortlitedToset);


//        itemListShortlited = new ArrayList<RecyclerItemUsers>(itemListShortlitedToset);//
//        recycler_view_ShortListed.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(UserSelection.this);
//        recycler_view_ShortListed.setLayoutManager(mLayoutManager2);
//        recycler_view_ShortListed.addItemDecoration(new DividerItemDecoration(UserSelection.this, LinearLayoutManager.VERTICAL));
//        recycler_view_ShortListed.setItemAnimator(new DefaultItemAnimator());
    }

    public void placeS() {
        getShortlisted();

        itemListPlaced.addAll(itemListPlacedToset);

        recycler_view_Placed.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(UserSelection.this);
        recycler_view_Placed.setLayoutManager(mLayoutManager3);
        recycler_view_Placed.addItemDecoration(new DividerItemDecoration(UserSelection.this, LinearLayoutManager.VERTICAL));
        recycler_view_Placed.setItemAnimator(new DefaultItemAnimator());
        refreshflagShortlist=false;



    }

//    class SaveShortlisted extends AsyncTask<String, String, String> {
//
//
//        protected String doInBackground(String... param) {
//
//
//
//
//
//
//            String r=null;
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//
//            params.add(new BasicNameValuePair("u",   ));    //0
//
//
//            json = jParser.makeHttpRequest(url_SaveShortlisted, "GET", params);
//            try {
//                r = json.getString("info");
//
//
//            }catch (Exception e){e.printStackTrace();}
//            return r;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            if(result.equals("success"))
//            {
//
//
//            }
//            else {
//
//            }
//        }
//    }

    public void saveShortlisted(){

        try{
            paramsList = toString(itemListShortlitedToset);

            byte[] demoKeyBytes = SimpleBase64Encoder.decode("I09jdG9wdXMxMkl0ZXMjJQ==");
            byte[] demoIVBytes = SimpleBase64Encoder.decode("I1BsYWNlMTJNZSMlJSopXg==");
            String sPadding = "ISO10126Padding";
            byte[] fnameBytes = paramsList.getBytes("UTF-8");
            byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fnameBytes);
            String  paramsList2 = new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));
            Log.d("TAG", "paramsList2Encrypted: "+paramsList2);

            byte[] demo1EncryptedBytes=SimpleBase64Encoder.decode(paramsList2);
            byte[] demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
            Log.d("TAG", "paramsListDecrycrypted: "+new String(demo1DecryptedBytes));





        }catch (Exception e ){
            Toast.makeText(this,"saveShortlisted"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getShortlisted(){

        try{
//            private ArrayList<RecyclerItemUsers> itemListRegisterd = new ArrayList<>();

            ArrayList<RecyclerItemUsers> chatList2=(ArrayList<RecyclerItemUsers>)fromString(paramsList);
            for(int i=0;i<chatList2.size();i++)
            {
                Log.d("paramsList2", "paramsList2: "+chatList2.get(i).getName());
                System.out.println(chatList2.get(i).getName());
            }

        }catch (Exception e ){
            Toast.makeText(this,"getShortlisted"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    private static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();


        return  new String(SimpleBase64Encoder.encode(baos.toByteArray()));

    }


    private static Object fromString( String s ) throws IOException ,
            ClassNotFoundException {

        byte[] data = SimpleBase64Encoder.decode(s);
//        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }



}
