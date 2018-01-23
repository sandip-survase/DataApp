package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.PlacedDebarInfo;
import placeme.octopusites.com.placeme.modal.RecyclerItemUsersAdmin;
import placeme.octopusites.com.placeme.modal.RecyclerItemUsersAdminAdapter;
import placeme.octopusites.com.placeme.modal.RecyclerTouchListener;
import placeme.octopusites.com.placeme.modal.UserDetails;

import static placeme.octopusites.com.placeme.AES4all.fromString;

public class ShowUsers extends AppCompatActivity {

    int Verify = 0, NotVerify = 1, Activated = 2, NotActivated = 3, Placed = 4, NotPlaced = 5, Debar = 6, NotDebar = 7, Student = 8, Alumni = 9;
    boolean[] filter = {false, false, false, false, false, false, false, false, false, false};
    TextView title;
    private MaterialSearchView searchView;
    private List<RecyclerItemUsersAdmin> itemList = new ArrayList<>();
    List<RecyclerItemUsersAdmin> tempListUsers;
    private RecyclerView recyclerView;
    private RecyclerItemUsersAdminAdapter mAdapter;
    String registerUsersName[], encUsersName[], userType[], fnames[], lnames[], isactivated[], signature[];
    String isplaced[], isDebar[], companyName[], verify[];
    JSONParser jParser = new JSONParser();
    JSONObject json, json2;
    String username;
    int count = 0;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding;
    int searchFlag = 0;
    FloatingActionButton fab, fabFilter;
    SwipeRefreshLayout swipeRefreshLayout;

    int i = 10;

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
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        username = MySharedPreferencesManager.getUsername(ShowUsers.this);
        demoKeyBytes = SimpleBase64Encoder.decode(MySharedPreferencesManager.getDigest1(ShowUsers.this));
        demoIVBytes = SimpleBase64Encoder.decode(MySharedPreferencesManager.getDigest2(ShowUsers.this));
        sPadding = "ISO10126Padding";
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabFilter = (FloatingActionButton) findViewById(R.id.fabFilter);


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filterUsers(newText);
                searchFlag = 1;

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
                startActivityForResult(new Intent(ShowUsers.this, AddUsersActivity.class), 0);
            }
        });

        fabFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFilterDialog();
            }
        });


//        fabFilter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final CharSequence[] items = {" Easy ", " Medium ", " Hard ", " Very Hard "," Easy ", " Medium ", " Hard ", " Very Hard "};
//                final ArrayList seletedItems = new ArrayList();
//
//                AlertDialog dialog = new AlertDialog.Builder(ShowUsers.this)
//                        .setTitle("Select The filters")
//
//                        .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
//                                if (isChecked) {
//                                    // If the user checked the item, add it to the selected items
//                                    seletedItems.add(indexSelected);
//                                } else if (seletedItems.contains(indexSelected)) {
//                                    // Else, if the item is already in the array, remove it
//                                    seletedItems.remove(Integer.valueOf(indexSelected));
//                                }
//                            }
//                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int id) {
//                                //  Your code when user clicked on OK
//                                //  You can write the code  to save the selected item here
//                            }
//                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int id) {
//                                //  Your code when user clicked on Cancel
//                            }
//                        }).create();
//                dialog.show();
//
//            }
//        });


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
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

                RecyclerItemUsersAdmin recyclerItemUsersAdmin = null;
                if (searchFlag == 1)
                    recyclerItemUsersAdmin = tempListUsers.get(position);
                else
                    recyclerItemUsersAdmin = itemList.get(position);


                String studentUsername = recyclerItemUsersAdmin.getEmail();
                String isactivated = recyclerItemUsersAdmin.getIsactivated();
                String role = recyclerItemUsersAdmin.getRole();
                String isVerify = recyclerItemUsersAdmin.getVerifys();
                String isPlaced = recyclerItemUsersAdmin.getIsplaceds();
                String isDebar = recyclerItemUsersAdmin.getIsDebars();
                String companyName = recyclerItemUsersAdmin.getCompanyNames();

                Intent intent = new Intent(ShowUsers.this, AdminSingleUserViewDialog.class);
                intent.putExtra("studentUsername", studentUsername);
                intent.putExtra("role", role);
                intent.putExtra("isactivated", isactivated);
                intent.putExtra("isVerify", isVerify);
                intent.putExtra("isPlaced", isPlaced);
                intent.putExtra("isDebar", isDebar);
                intent.putExtra("companyName", companyName);

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
        if (resultCode == Z.USER_DATA_CHANGE_RESULT_CODE) {
            refreshContent();


        }
    }

    class GetCountOfUsersUnderAdmin extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            String r = null;
            String username = MySharedPreferencesManager.getUsername(ShowUsers.this);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            json = jParser.makeHttpRequest(Z.url_GetCountOfUsersUnderAdmin, "GET", params);
            try {
                r = json.getString("count");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Z.CountOfUsersUnderAdmin = result;
                Log.d("TAG", "onPostExecute: count " + result);
            }
        }
    }

    public void refreshContent() {
        swipeRefreshLayout.setRefreshing(true);
        new GetRegisteredUsersUnderAdmin().execute();
        new GetCountOfUsersUnderAdmin().execute();

    }

    void filterUsers(String text) {
        tempListUsers = new ArrayList();
        for (RecyclerItemUsersAdmin d : itemList) {

            if (containsIgnoreCase(d.getEmail(), text)) {
                tempListUsers.add(d);
            }
        }
        mAdapter.updateList(tempListUsers, text);
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) return false;

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
            Log.d("TAG", "doInBackground: inside asynk " + username);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(Z.url_GetRegisteredUsersUnderAdmin, "GET", params);
            Log.d("TAG", "GetRegisteredUsersUnderAdmin : json " + json);

            try {
                String info = json.getString("info");
                if (info.equals("success")) {
                    count = Integer.parseInt(json.getString("count"));
                    Log.d("TAG", "count -----------------------------   " + count);

                    registerUsersName = new String[count];
                    userType = new String[count];
                    fnames = new String[count];
                    lnames = new String[count];
                    isactivated = new String[count];
                    encUsersName = new String[count];
                    signature = new String[count];

                    //
                    isplaced = new String[count];
                    isDebar = new String[count];
                    companyName = new String[count];
                    verify = new String[count];

                    String o = json.getString("object");
                    ArrayList<UserDetails> userDetails = (ArrayList<UserDetails>) fromString(o, MySharedPreferencesManager.getDigest1(ShowUsers.this), MySharedPreferencesManager.getDigest2(ShowUsers.this));

                    String p = json.getString("object2");
                    ArrayList<PlacedDebarInfo> placedDebarInfo = (ArrayList<PlacedDebarInfo>) fromString(p, MySharedPreferencesManager.getDigest1(ShowUsers.this), MySharedPreferencesManager.getDigest2(ShowUsers.this));

                    Log.d("TAG", "--------------- size " + userDetails.size());

                    for (int i = 0; i < count; i++) {

                        registerUsersName[i] = userDetails.get(i).getUser();
                        userType[i] = userDetails.get(i).getRole();
                        fnames[i] = userDetails.get(i).getFname();
                        lnames[i] = userDetails.get(i).getLname();
                        isactivated[i] = userDetails.get(i).getIsactivated();
                        encUsersName[i] = Z.Encrypt(registerUsersName[i], ShowUsers.this);

                        isplaced[i] = placedDebarInfo.get(i).getIsplaced();
                        isDebar[i] = placedDebarInfo.get(i).getIsdebar();
                        companyName[i] = placedDebarInfo.get(i).getCompanyname();
                        verify[i] = placedDebarInfo.get(i).getVerify();


                        Log.d("KUN", ": user " + registerUsersName[i]);
                        Log.d("KUN", ": isplaced " + isplaced[i]);
                        Log.d("KUN", ": isDebar " + isDebar[i]);
                        Log.d("KUN", ": companyName " + companyName[i]);
                        Log.d("KUN", ": isplaced " + isplaced[i]);
                        Log.d("KUN", "----------------------------------------------------- ");


                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "get money exp:" + e.getMessage());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            addUsersdatatoAdapter(count);
            swipeRefreshLayout.setRefreshing(false);
//            new GetLastUpdatedNotification().execute();
        }
    }

    class GetLastUpdatedNotification extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r = null;


            for (int i = 0; i < encUsersName.length; i++) {
                // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+notificationuploadedby[i] , Toast.LENGTH_SHORT).show();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", encUsersName[i]));       //0
                json = jParser.makeHttpRequest(Z.url_getlastupdated, "GET", params);
                try {
                    String s = json.getString("lastupdated");
                    Log.d("TAG", "signature " + s);

                    if (s.equals("noupdate")) {
                        // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+s , Toast.LENGTH_SHORT).show();
                        signature[i] = "placeme";
                    } else {
                        signature[i] = s;
                        // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+s , Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                }
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {


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

    // add spinner in action bar  do it later

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.spinner_and_search, menu);
//
//        MenuItem item = menu.findItem(R.id.spinner);
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        searchView.setMenuItem(item);
//
//
//        spinner.setAdapter(adapter);
//        return true;
//    }

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
        String role, username, fullname = "Name", active, strisactivated, encusername, strsignature;
        String isplaceds, isDebars, companyNames, verifys;

        itemList.clear();
        for (int i = 0; i < count; i++) {
            boolean flag = false;

            if (filter[0] == true && !isVerify(i)) {
                flag = true;
            }
            if (filter[1] == true && !isNotVerify(i)) {
                flag = true;
            }


            if (flag == false) {
                isplaceds = isplaced[i];
                isDebars = isDebar[i];
                companyNames = companyName[i];
                verifys = verify[i];


                username = registerUsersName[i];
                encusername = encUsersName[i];
                role = userType[i];

                active = isactivated[i];
                strsignature = signature[i];

                if (fnames[i] != null && lnames[i] != null) {
                    if (!fnames[i].equals("") && !lnames[i].equals(""))
                        fullname = fnames[i] + " " + lnames[i];
                    else
                        fullname = "Name";
                }

                if (active.equals("yes"))
                    strisactivated = "Activated";
                else
                    strisactivated = "Not Activated";

                RecyclerItemUsersAdmin item = new RecyclerItemUsersAdmin(encusername, fullname, username, role, strisactivated, strsignature, isplaceds, isDebars, companyNames, verifys);
                itemList.add(item);
            }
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


    public void showFilterDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.show_user_filter_layout, null);
        dialogBuilder.setView(dialogView);


//        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);
        title = (TextView) dialogView.findViewById(R.id.title);
        title.setTypeface(Z.getBold(ShowUsers.this));
        title.setTextColor(Color.parseColor("#03353e"));


        final CheckBox checkboxVerify = (CheckBox) dialogView.findViewById(R.id.checkboxVerify);
        final CheckBox checkboxNotVerify = (CheckBox) dialogView.findViewById(R.id.checkboxNotVerify);
        final CheckBox checkboxActivated = (CheckBox) dialogView.findViewById(R.id.checkboxActivated);
        final CheckBox checkboxNotActivated = (CheckBox) dialogView.findViewById(R.id.checkboxNotActivated);
        final CheckBox checkboxPlaced = (CheckBox) dialogView.findViewById(R.id.checkboxPlaced);
        final CheckBox checkboxNotPlaced = (CheckBox) dialogView.findViewById(R.id.checkboxNotPlaced);
        final CheckBox CheckBoxDebar = (CheckBox) dialogView.findViewById(R.id.CheckBoxDebar);
        final CheckBox CheckBoxsNotDebar = (CheckBox) dialogView.findViewById(R.id.CheckBoxsNotDebar);
        final CheckBox checkboxStudent = (CheckBox) dialogView.findViewById(R.id.checkboxStudent);
        final CheckBox checkboxAlumni = (CheckBox) dialogView.findViewById(R.id.checkboxAlumni);


        checkboxVerify.setChecked(filter[0]);
        checkboxNotVerify.setChecked(filter[1]);
        checkboxActivated.setChecked(filter[2]);
        checkboxNotActivated.setChecked(filter[3]);
        checkboxPlaced.setChecked(filter[4]);
        checkboxNotPlaced.setChecked(filter[5]);
        CheckBoxDebar.setChecked(filter[6]);
        CheckBoxsNotDebar.setChecked(filter[7]);
        checkboxStudent.setChecked(filter[8]);
        checkboxAlumni.setChecked(filter[9]);


        checkboxVerify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filter[Verify] = true;
                    checkboxNotVerify.setChecked(false);
                    filter[NotVerify] = false;
                } else {
                    filter[Verify] = false;
                }
            }
        });

        checkboxNotVerify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filter[NotVerify] = true;
                    checkboxVerify.setChecked(false);
                    filter[Verify] = false;
                } else
                    filter[NotVerify] = false;
            }
        });
        checkboxActivated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filter[Activated] = true;
                    checkboxNotActivated.setChecked(false);
                    filter[NotActivated] = false;
                } else
                    filter[Activated] = false;
            }
        });
        checkboxNotActivated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filter[NotActivated] = true;
                    checkboxActivated.setChecked(false);
                    filter[Activated] = false;
                } else
                    filter[NotActivated] = false;
            }
        });
        checkboxPlaced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filter[Placed] = true;
                    checkboxNotPlaced.setChecked(false);
                    filter[NotPlaced] = false;
                } else
                    filter[Placed] = false;
            }
        });
        checkboxNotPlaced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filter[NotPlaced] = true;
                    checkboxPlaced.setChecked(false);
                    filter[Placed] = false;
                } else
                    filter[NotPlaced] = false;
            }
        });
        CheckBoxDebar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filter[Debar] = true;
                    CheckBoxsNotDebar.setChecked(false);
                    filter[NotDebar] = false;
                } else
                    filter[Debar] = false;
            }
        });
        CheckBoxsNotDebar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filter[NotDebar] = true;
                    CheckBoxDebar.setChecked(false);
                    filter[Debar] = false;
                } else
                    filter[NotDebar] = false;
            }
        });
        checkboxStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filter[Student] = true;
                    checkboxAlumni.setChecked(false);
                    filter[Alumni] = false;
                } else
                    filter[Student] = false;
            }
        });
        checkboxAlumni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filter[Alumni] = true;
                    checkboxStudent.setChecked(false);
                    filter[Student] = false;
                } else
                    filter[Alumni] = false;
            }
        });


        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                Log.d("KUN", "onClick: " + filter[0] + "  " + filter[1] + " " + filter[2] + "  " + filter[3] + "  " + filter[4] + "  " + filter[5] + "  " + filter[6] + "  " + filter[7] + "  " + filter[8] + "  " + filter[9]);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


    boolean isVerify(int i) {
        if (verify[i].equals("yes"))
            return true;
        return false;
    }

    boolean isNotVerify(int i) {
        if (verify[i].equals("no"))
            return true;
        return false;
    }
}
