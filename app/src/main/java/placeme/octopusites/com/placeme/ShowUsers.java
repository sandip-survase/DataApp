package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;

public class ShowUsers extends AppCompatActivity {

    private MaterialSearchView searchView;
    private List<RecyclerItemUsersAdmin> itemList = new ArrayList<>();
    List<RecyclerItemUsersAdmin> tempListUsers;
    private RecyclerView recyclerView;
    private RecyclerItemUsersAdminAdapter mAdapter;
    String registerUsersName[],encUsersName[],userType[],fnames[],lnames[],isactivated[],signature[];
    JSONParser jParser = new JSONParser();
    JSONObject json,json2;
    String username;
    int count=0;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding;
    int searchFlag=0;
    FloatingActionButton fab;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("News Feed");
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Users</font>"));

        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        username=MySharedPreferencesManager.getUsername(ShowUsers.this);
        Log.d("TAG", "onCreate: userName "+username);
        demoKeyBytes = SimpleBase64Encoder.decode(MySharedPreferencesManager.getDigest1(ShowUsers.this));
        demoIVBytes = SimpleBase64Encoder.decode(MySharedPreferencesManager.getDigest2(ShowUsers.this));
        sPadding = "ISO10126Padding";


        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        fab= (FloatingActionButton) findViewById(R.id.fab);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filterUsers(newText);
                searchFlag=1;

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
                getSupportActionBar().setHomeAsUpIndicator(upArrow);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new RecyclerItemUsersAdminAdapter(itemList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new CustomScrollListener());


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ShowUsers.this, AddUsersActivity.class),0);
            }
        });

        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });



//        RelativeLayout createnotificationrl = (RelativeLayout) findViewById(R.id.createnotificationrl);
//        createnotificationrl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ShowUsers.this, AddUsersActivity.class));
//            }
//        });
//        RelativeLayout editnotificationrl = (RelativeLayout) findViewById(R.id.editnotificationrl);
//        editnotificationrl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ShowUsers.this, EditUserActivity.class));
//            }
//        });
//        addUsersdatatoAdapter();

        refreshContent();


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                RecyclerItemUsersAdmin recyclerItemUsersAdmin=null;
                if(searchFlag==1)
                    recyclerItemUsersAdmin = tempListUsers.get(position);
                else
                    recyclerItemUsersAdmin = itemList.get(position);


                String studentUsername=recyclerItemUsersAdmin.getEmail();
                String isactivated=recyclerItemUsersAdmin.getIsactivated();
                String role=recyclerItemUsersAdmin.getRole();


                Intent intent=new Intent(ShowUsers.this, AdminSingleUserViewDialog.class);
                intent.putExtra("studentUsername",studentUsername);
                intent.putExtra("role",role);
                intent.putExtra("isactivated",isactivated);

                startActivityForResult(intent, 0);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }//onc

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResult: ------------------------------------ " + resultCode);
        if(resultCode==MyConstants.USER_DATA_CHANGE_RESULT_CODE){
            refreshContent();
        }
    }

    public void refreshContent(){
        swipeRefreshLayout.setRefreshing(true);
        new GetRegisteredUsersUnderAdmin().execute();

    }

    void filterUsers(String text)
    {
        tempListUsers = new ArrayList();
        for(RecyclerItemUsersAdmin d: itemList){

            if(containsIgnoreCase(d.getEmail(),text)){
                tempListUsers.add(d);
            }
        }
        mAdapter.updateList(tempListUsers,text);
    }
    public static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }
    class GetRegisteredUsersUnderAdmin extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            Log.d("TAG", "GetRegisteredUsersUnderAdmin: input username  " + username);

            json = jParser.makeHttpRequest(MyConstants.url_GetRegisteredUsersUnderAdmin, "GET", params);
            Log.d("TAG", "GetRegisteredUsersUnderAdmin: json  " + json);


            try {
                String info = json.getString("info");
                Log.d("TAG", "doInBackground: info "+info);
                if (info.equals("success")) {
                    count = Integer.parseInt(json.getString("count"));
                    Log.d("TAG", "count -----------------------------   "+count);

                    registerUsersName = new String[count];
                    userType=new String[count];
                    fnames=new String[count];
                    lnames=new String[count];
                    isactivated=new String[count];
                    encUsersName=new String[count];
                    signature=new String[count];

                    byte[]  demo1DecryptedBytes = null;

                    for (int i = 0; i < count; i++) {

                        try {

                            registerUsersName[i] = json.getString("user" + i);
                            userType[i] = json.getString("role" + i);
                            fnames[i] = json.getString("fname" + i);
                            lnames[i] = json.getString("lname" + i);
                            isactivated[i] = json.getString("isactivated" + i);

                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("u",registerUsersName[i]));       //0
                            json2 = jParser.makeHttpRequest(MyConstants.url_getlastupdated, "GET", params);

                            String s = json2.getString("lastupdated");
                            if(s.equals("noupdate"))
                            {
                                // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+s , Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                signature [i]=s;
                                // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+s , Toast.LENGTH_SHORT).show();
                            }

                            if (!registerUsersName[i].equals("null")) {
                                byte[] demo1EncryptedBytes = SimpleBase64Encoder.decode(registerUsersName[i]);
                                demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
                                encUsersName[i]=registerUsersName[i];
                                registerUsersName[i]=new String(demo1DecryptedBytes);


                            }

                            if (!fnames[i].equals("null")) {
                                byte[] demo1EncryptedBytes = SimpleBase64Encoder.decode(fnames[i]);
                                demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
                                fnames[i]=new String(demo1DecryptedBytes);

                            }

                            if (!lnames[i].equals("null")) {
                                byte[] demo1EncryptedBytes = SimpleBase64Encoder.decode(lnames[i]);
                                demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
                                lnames[i]=new String(demo1DecryptedBytes);

                            }



                        }catch (Exception e){
                            Log.d("TAG", "doInBackground: exp "+e.getMessage());
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "doInBackground: exp "+e.getMessage());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            new GetLastUpdatedNotification().execute();


        }
    }

    class GetLastUpdatedNotification extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r=null;


            for(int i=0;i<encUsersName.length;i++) {
                // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+notificationuploadedby[i] , Toast.LENGTH_SHORT).show();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u",encUsersName [i]));       //0
                json = jParser.makeHttpRequest(MyConstants.url_getlastupdated, "GET", params);
                try {
                    String s = json.getString("lastupdated");
                    Log.d("TAG", "signature "+s);

                    if(s.equals("noupdate"))
                    {
                        // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+s , Toast.LENGTH_SHORT).show();
                        signature[i]="placeme";
                    }
                    else
                    {
                        signature[i]=s;
                        // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+s , Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){}
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            addUsersdatatoAdapter(count);
            swipeRefreshLayout.setRefreshing(false);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

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

    void addUsersdatatoAdapter(int count) {
        String role,username,fullname="Name",active,strisactivated,encusername,strsignature;

        itemList.clear();

        for (int i = 0; i < count; i++) {

            username=registerUsersName[i];
            encusername=encUsersName[i];
            role=userType[i];

            active=isactivated[i];
            strsignature=signature[i];

            Log.d("TAG", "addUsersdatatoAdapter: fname array = "+fnames[i]);
            if(fnames[i]!=null && lnames[i]!=null) {
                if (!fnames[i].equals("") && !lnames[i].equals(""))
                    fullname = fnames[i] + " " + lnames[i];
                else
                    fullname="Name";
            }

            if(active.equals("yes"))
                strisactivated="Activated";
            else
                strisactivated="Not Activated";

//            Log.d("TAG", "addUsersdatatoAdapter: count "+count);
//            Log.d("TAG", "addUsersdatatoAdapter: name "+fullname);
//            Log.d("TAG", "addUsersdatatoAdapter: username "+username);
//            Log.d("TAG", "addUsersdatatoAdapter: role "+role);
//            Log.d("TAG", "addUsersdatatoAdapter: Activated "+strisactivated);

//            RecyclerItemUsersAdmin item = new RecyclerItemUsersAdmin(i, "Abc " + i, "abc" + i + "@gmail.com ", "Student", "Placed in Cognizant");
            RecyclerItemUsersAdmin item = new RecyclerItemUsersAdmin(encusername,fullname,username,role,strisactivated,strsignature);

            itemList.add(item);
        }

        mAdapter.notifyDataSetChanged();
    }

    public class CustomScrollListener extends RecyclerView.OnScrollListener {
        public CustomScrollListener() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:
                    Animation fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
                    fab.startAnimation(fab_open);
                    break;
                case RecyclerView.SCROLL_STATE_DRAGGING:

                    Animation fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
                    fab.startAnimation(fab_close);

                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    break;

            }

        }
    }

}
