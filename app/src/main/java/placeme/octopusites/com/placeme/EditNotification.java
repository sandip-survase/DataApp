package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
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

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import de.hdodenhof.circleimageview.CircleImageView;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;

public class EditNotification extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";

    Toolbar toolbar;
    TextView selectedtxt;
    int selectedCount = 0;
    int deleteflag = 0;
    int selectedPositions[];
    boolean isStarted = false;
    Vibrator myVib;
    View[] selectedViews;
    String role;
    SharedPreferences sharedpreferences;
    CircleImageView profile;
    JSONParser jParser = new JSONParser();
    String digest1, digest2;
    JSONObject json;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";
    int[] called_pages_notification;
    boolean isFirstRunNotification = true, isLastPageLoadedNotification = false;
    int lastPageFlagNotification = 0;
    int notificationpages = 0;
    int total_no_of_notifications;
    int unreadcountNotification = 0;
    int notificationcount = 0;
    int readstatuscountNotification = 0;
    String notificationids[], notificationtitles[], notificationnotifications[], notificationfilename1[], notificationfilename2[], notificationfilename3[], notificationfilename4[], notificationfilename5[], notificationuploadtime[], notificationlastmodified[], notificationuploadedby[], notificationuploadedbyplain[];
    String[] uniqueUploadersNotification;
    String[] uniqueUploadersEncNotification;
    String lastupdatedNotification[];
    String notificationreadstatus[];
    int firstVisibleItemNotification, visibleItemCountNotification, totalItemCountNotification;
    //notification deletion
    ArrayList<String> notificationdeleteArraylist = new ArrayList<>();
    private List<RecyclerItemAdminEdit> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerItemAdminEditAdapter mAdapter;
    private MaterialSearchView searchView;
    //edit part
    private int previousTotalNotification = 0; // The total number of items in the dataset after the last load
    private boolean loadingNotification = true; // True if we are still waiting for the last set of data to load.
    private int visibleThresholdNotification = 0; // The minimum amount of items to have below your current scroll position before loading more.
    private int page_to_call_notification = 1;
    private int current_page_notification = 1;
    private String plainusername, username = "", fname = "", mname = "", sname = "";
    private RecyclerView recyclerViewNotification, recyclerViewPlacement;
    private List<RecyclerItem> itemListNotification = new ArrayList<>();
    private RecyclerItemAdapter mAdapterNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notification);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Edit Notification</font>"));

        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);


        username = MySharedPreferencesManager.getUsername(this);

        role = MySharedPreferencesManager.getRole(this);


        try {
            demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            demoIVBytes = SimpleBase64Encoder.decode(digest2);
            sPadding = "ISO10126Padding";

            byte[] demo1EncryptedBytes1 = SimpleBase64Encoder.decode(username);

            byte[] demo1DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes1);

            String plainusername = new String(demo1DecryptedBytes1);


        } catch (Exception e) {
        }

        recyclerViewNotification = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapterNotification = new RecyclerItemAdapter(itemListNotification);
        recyclerViewNotification.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerNotification = new LinearLayoutManager(this);
        recyclerViewNotification.setLayoutManager(linearLayoutManagerNotification);
        recyclerViewNotification.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNotification.setAdapter(mAdapterNotification);


        recyclerViewNotification.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewNotification, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                RecyclerItem item = itemListNotification.get(position);


                if (deleteflag == 1) {
                    if (selectedPositions[position] == 0) {
                        view.setBackgroundColor(Color.parseColor("#000000"));
                        selectedCount++;
                        setActionBarTitle(selectedCount);
                        selectedPositions[position] = 1;
                        selectedViews[position] = view;
                        Log.d("selectfordel", "onClick: " + item.getId());
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
                } else if (role.equals("hr")) {
                    startActivity(new Intent(EditNotification.this, EditNotificationHrMain.class));

                } else if (role.equals("admin")) {

                    Intent i1 = new Intent(EditNotification.this, CreateNotification.class);
                    i1.putExtra("flag", "EditNotification");
                    i1.putExtra("id", item.getId());
                    i1.putExtra("title", item.getTitle());
                    i1.putExtra("notification", item.getNotification());
                    i1.putExtra("file1", item.getFilename1());
                    i1.putExtra("file2", item.getFilename2());
                    i1.putExtra("file3", item.getFilename3());
                    i1.putExtra("file4", item.getFilename4());
                    i1.putExtra("file5", item.getFilename5());
                    i1.putExtra("uploadedby", item.getUploadedby());
                    i1.putExtra("uploadtime", item.getUploadtime());
                    i1.putExtra("lastmodified", item.getLastmodified());
                    Log.d("Check2", "id: " + item.getId());
                    Log.d("Check2", "notiff: " + item.getNotification());
                    Log.d("Check2", "title: " + item.getTitle());
                    Log.d("Check2", "file1: " + item.getFilename1());
                    Log.d("Check2", "lastmodified: " + item.getLastmodified());
                    Log.d("Check2", "file5: " + item.getFilename5());
                    startActivity(i1);


                    //startActivity(new Intent(EditNotification.this,EditNotificationMain.class));
//
//                    Intent i1 = new Intent(EditNotification.this, EditNotificationMain.class);
//                    i1.putExtra("flag","EditNotification");
//                    i1.putExtra("id",item.getId());
//
//                    i1.putExtra("title",item.getTitle());
//                    i1.putExtra("notification",item.getNotification());
//                    i1.putExtra("file1",item.getFilename1());
//                    i1.putExtra("file2",item.getFilename2());
//                    i1.putExtra("file3",item.getFilename3());
//                    i1.putExtra("file4",item.getFilename4());
//                    i1.putExtra("file5",item.getFilename5());
//                    i1.putExtra("uploadedby",item.getUploadedby());
//                    i1.putExtra("uploadtime",item.getUploadtime());
//                    i1.putExtra("lastmodified",item.getLastmodified());
//                    Log.d("Check2" ,"id: "+item.getId());
//                    Log.d("Check2" ,"notiff: "+item.getNotification());
//                    Log.d("Check2" ,"title: "+item.getTitle());
//                    Log.d("Check2" ,"file1: "+item.getFilename1());
//                    Log.d("Check2" ,"lastmodified: "+item.getLastmodified());
//                    Log.d("Check2" ,"file5: "+item.getFilename5());
//                    startActivity(i1);

                }
            }
//                    changeReadStatusNotification(item.getId());

//                crop_flag=1;


            @Override
            public void onLongClick(View view, int position) {
                RecyclerItem item = itemListNotification.get(position);

                if (deleteflag == 0) {
//                    int retval = itemListNotification.size();
//                    selectedPositions=new int[retval];
//                    selectedViews=new View[retval];

                    Log.d("POsition", "onLongClick: " + position);
                    Log.d("id", "onLongClick: " + item.getId());

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
        recyclerViewNotification.setOnScrollListener(new EndlessRecyclerOnScrollListenerNotification(linearLayoutManagerNotification) {
            @Override
            public void onLoadMore(int current_page) {

                if (total_no_of_notifications > 20) {
                    simulateLoadingNotification();
                }

            }
        });


        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(EditNotification.this, query, Toast.LENGTH_LONG).show();
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



        TextView editnotitxt = (TextView) findViewById(R.id.editnotitxt);
        TextView editnotinotitxt = (TextView) findViewById(R.id.editnotinotitxt);
        editnotitxt.setTypeface(Z.getBold(this));
        editnotinotitxt.setTypeface(Z.getLight(this));
        deletenotitxt.setTypeface(Z.getBold(this));


//        addPlacementdatatoAdapter();
//        getNotifications();
    }

    void hideSearchMenu() {
        EditNotification.this.invalidateOptionsMenu();
        isStarted = true;
        Menu m = null;
        super.onPrepareOptionsMenu(m);
    }

    void showSearchMenu() {
        notificationdeleteArraylist.clear();
        EditNotification.this.invalidateOptionsMenu();
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


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(EditNotification.this.getResources().getColor(R.color.colorPrimary)));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Edit Notification</font>"));

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


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(EditNotification.this.getResources().getColor(R.color.transperent)));

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


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditNotification.this);
                    alertDialogBuilder
                            .setMessage("Are you sure you want to delete selected items ?")
                            .setCancelable(false)
                            .setPositiveButton("Delete",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            new DeleteNotifications().execute();
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
                    Toast.makeText(EditNotification.this, "Select First", Toast.LENGTH_SHORT).show();
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
            for (int i = 0; i < mAdapterNotification.getItemCount(); i++) {
                if (selectedViews[i] != null) {
                    selectedViews[i].setBackgroundColor(Color.TRANSPARENT);
                }
                selectedPositions[i] = 0;
            }
            selectedCount = 0;

        } else
            super.onBackPressed();
    }


    //populate notiff
    void getNotifications() {
        previousTotalNotification = 0;
        loadingNotification = true;
        page_to_call_notification = 1;
        isFirstRunNotification = true;
        isLastPageLoadedNotification = false;
        lastPageFlagNotification = 0;
//        new GetNotificationsReadStatus().execute();

    }

    void addNotificationdatatoAdapter() {

        if (isFirstRunNotification) {
            itemListNotification.clear();
            mAdapterNotification.notifyDataSetChanged();
            isFirstRunNotification = false;

        }

        if (!isLastPageLoadedNotification) {

            for (int i = 0; i < notificationcount; i++) {

                String headingtoshow = "", notificationtoshow = "";
                int largeheadingflag = 0, largenotificationflag = 0;

                if (notificationtitles[i].length() > 25) {
                    for (int j = 0; j < 20; j++)
                        headingtoshow += notificationtitles[i].charAt(j);
                    largeheadingflag = 1;
                    headingtoshow += "...";
                }
                if (notificationnotifications[i].length() > 25) {
                    for (int j = 0; j < 25; j++)
                        notificationtoshow += notificationnotifications[i].charAt(j);
                    largenotificationflag = 1;
                    notificationtoshow += "...";
                }
                DateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");
                String outputDate = "";
                try {
                    Date date = inputFormat.parse(notificationuploadtime[i]);
                    outputDate = outputFormat.format(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                RecyclerItem item = null;
//                            Toast.makeText(this, "read status count Notification:-"+readstatuscountNotification, Toast.LENGTH_LONG).show();
//                            Toast.makeText(this, "notification read status here:-"+notificationreadstatus, Toast.LENGTH_LONG).show();

                for (int j = 0; j < readstatuscountNotification; j++) {
                    String idnstatus = notificationreadstatus[j];
                    Log.d("TAGAdmin", "idnstatus: " + idnstatus);
                    String sid = "";
                    if (idnstatus.contains("U")) {

                        for (int k = 0; k < idnstatus.length() - 1; k++) {
                            sid += idnstatus.charAt(k);
                            Log.d("TAGAdmin", "sid: " + sid);
                        }
                        if (sid.equals(notificationids[i])) {
                            for (int k = 0; k < uniqueUploadersNotification.length; k++) {

                                if (notificationuploadedbyplain[i].equals(uniqueUploadersNotification[k])) {
                                    if (lastupdatedNotification[k] == null) {

                                        if (notificationfilename1[i].equals("null")) {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], EditNotification.this, "placeme");

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], EditNotification.this, "placeme");

                                            itemListNotification.add(item);
                                        }
                                    } else {
                                        if (notificationfilename1[i].equals("null")) {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);

                                            itemListNotification.add(item);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (idnstatus.contains("R")) {

                        for (int k = 0; k < idnstatus.length() - 1; k++) {
                            sid += idnstatus.charAt(k);
                        }
                        if (sid.equals(notificationids[i])) {
                            for (int k = 0; k < uniqueUploadersNotification.length; k++) {

                                if (notificationuploadedbyplain[i].equals(uniqueUploadersNotification[k])) {


                                    if (lastupdatedNotification[k] == null) {

                                        if (notificationfilename1[i].equals("null")) {

                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], EditNotification.this, "cplaceme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], EditNotification.this, "placeme");

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], EditNotification.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], EditNotification.this, "placeme");

                                            itemListNotification.add(item);
                                        }
                                    } else {
                                        if (notificationfilename1[i].equals("null")) {

                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], EditNotification.this, lastupdatedNotification[k]);

                                            itemListNotification.add(item);
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }

        }

        if (lastPageFlagNotification == 1)
            isLastPageLoadedNotification = true;

        mAdapterNotification.notifyDataSetChanged();
    }

    private void simulateLoadingNotification() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... param) {
                try {


                    if (page_to_call_notification < notificationpages)
                        page_to_call_notification++;


                    if (page_to_call_notification != notificationpages) {
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("u", username));       //0
                        params.add(new BasicNameValuePair("p", page_to_call_notification + ""));
                        json = jParser.makeHttpRequest(Z.url_GetNotificationsSentByAdmin, "GET", params);

                        notificationcount = Integer.parseInt(json.getString("count"));

                        notificationids = new String[notificationcount];
                        notificationtitles = new String[notificationcount];
                        notificationnotifications = new String[notificationcount];
                        notificationfilename1 = new String[notificationcount];
                        notificationfilename2 = new String[notificationcount];
                        notificationfilename3 = new String[notificationcount];
                        notificationfilename4 = new String[notificationcount];
                        notificationfilename5 = new String[notificationcount];
                        notificationuploadtime = new String[notificationcount];
                        notificationlastmodified = new String[notificationcount];
                        notificationuploadedby = new String[notificationcount];
                        notificationuploadedbyplain = new String[notificationcount];
                        for (int i = 0; i < notificationcount; i++) {
                            notificationids[i] = json.getString("id" + i);
                            notificationtitles[i] = json.getString("title" + i);
                            notificationnotifications[i] = json.getString("notification" + i);
                            notificationfilename1[i] = json.getString("filename1" + i);
                            notificationfilename2[i] = json.getString("filename2" + i);
                            notificationfilename3[i] = json.getString("filename3" + i);
                            notificationfilename4[i] = json.getString("filename4" + i);
                            notificationfilename5[i] = json.getString("filename5" + i);
                            notificationuploadtime[i] = json.getString("uploadtime" + i);
                            notificationlastmodified[i] = json.getString("lastmodified" + i);
                            notificationuploadedby[i] = json.getString("uploadedby" + i);

                        }
                    } else {
                        if (!isLastPageLoadedNotification) {

                            lastPageFlagNotification = 1;

                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("u", username));       //0
                            params.add(new BasicNameValuePair("p", page_to_call_notification + ""));
                            json = jParser.makeHttpRequest(Z.url_GetNotificationsSentByAdmin, "GET", params);

                            notificationcount = Integer.parseInt(json.getString("count"));

                            notificationids = new String[notificationcount];
                            notificationtitles = new String[notificationcount];
                            notificationnotifications = new String[notificationcount];
                            notificationfilename1 = new String[notificationcount];
                            notificationfilename2 = new String[notificationcount];
                            notificationfilename3 = new String[notificationcount];
                            notificationfilename4 = new String[notificationcount];
                            notificationfilename5 = new String[notificationcount];
                            notificationuploadtime = new String[notificationcount];
                            notificationlastmodified = new String[notificationcount];
                            notificationuploadedby = new String[notificationcount];
                            notificationuploadedbyplain = new String[notificationcount];
                            for (int i = 0; i < notificationcount; i++) {
                                notificationids[i] = json.getString("id" + i);
                                notificationtitles[i] = json.getString("title" + i);
                                notificationnotifications[i] = json.getString("notification" + i);
                                notificationfilename1[i] = json.getString("filename1" + i);
                                notificationfilename2[i] = json.getString("filename2" + i);
                                notificationfilename3[i] = json.getString("filename3" + i);
                                notificationfilename4[i] = json.getString("filename4" + i);
                                notificationfilename5[i] = json.getString("filename5" + i);
                                notificationuploadtime[i] = json.getString("uploadtime" + i);
                                notificationlastmodified[i] = json.getString("lastmodified" + i);
                                notificationuploadedby[i] = json.getString("uploadedby" + i);

                            }
                        }

                    }

                } catch (Exception e) {

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {

                if (!isLastPageLoadedNotification) {

                    for (int i = 0; i < notificationcount; i++)
                        try {

                            if (notificationtitles[i] != null) {
                                byte[] notificationtitlesEncryptedBytes = SimpleBase64Encoder.decode(notificationtitles[i]);
                                byte[] notificationtitlesDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationtitlesEncryptedBytes);
                                notificationtitles[i] = new String(notificationtitlesDecryptedBytes);
                            }
                            if (notificationnotifications[i] != null) {
                                byte[] notificationnotificationsEncryptedBytes = SimpleBase64Encoder.decode(notificationnotifications[i]);
                                byte[] notificationnotificationsDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationnotificationsEncryptedBytes);
                                notificationnotifications[i] = new String(notificationnotificationsDecryptedBytes);
                            }
                            if (notificationfilename1[i] != null) {
                                if (!notificationfilename1[i].equals("null")) {
                                    byte[] notificationfilename1EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename1[i]);
                                    byte[] notificationfilename1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename1EncryptedBytes);
                                    notificationfilename1[i] = new String(notificationfilename1DecryptedBytes);
                                }
                            }
                            if (notificationfilename2[i] != null) {
                                if (!notificationfilename2[i].equals("null")) {
                                    byte[] notificationfilename2EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename2[i]);
                                    byte[] notificationfilename2DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename2EncryptedBytes);
                                    notificationfilename2[i] = new String(notificationfilename2DecryptedBytes);
                                }
                            }
                            if (notificationfilename3[i] != null) {
                                if (!notificationfilename3[i].equals("null")) {
                                    byte[] notificationfilename3EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename3[i]);
                                    byte[] notificationfilename3DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename3EncryptedBytes);
                                    notificationfilename3[i] = new String(notificationfilename3DecryptedBytes);
                                }
                            }
                            if (notificationfilename4[i] != null) {
                                if (!notificationfilename4[i].equals("null")) {
                                    byte[] notificationfilename4EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename4[i]);
                                    byte[] notificationfilename4DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename4EncryptedBytes);
                                    notificationfilename4[i] = new String(notificationfilename4DecryptedBytes);
                                }
                            }
                            if (notificationfilename5[i] != null) {
                                if (!notificationfilename5[i].equals("null")) {
                                    byte[] notificationfilename5EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename5[i]);
                                    byte[] notificationfilename5DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename5EncryptedBytes);
                                    notificationfilename5[i] = new String(notificationfilename5DecryptedBytes);
                                }
                            }
                            if (notificationuploadtime[i] != null) {
                                byte[] notificationuploadtimeEncryptedBytes = SimpleBase64Encoder.decode(notificationuploadtime[i]);
                                byte[] notificationuploadtimeDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationuploadtimeEncryptedBytes);
                                notificationuploadtime[i] = new String(notificationuploadtimeDecryptedBytes);
                            }
                            if (notificationlastmodified[i] != null) {
                                byte[] notificationlastmodifiedEncryptedBytes = SimpleBase64Encoder.decode(notificationlastmodified[i]);
                                byte[] notificationlastmodifiedDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationlastmodifiedEncryptedBytes);
                                notificationlastmodified[i] = new String(notificationlastmodifiedDecryptedBytes);
                            }
                            if (notificationuploadedby[i] != null) {
                                byte[] notificationuploadedbyEncryptedBytes = SimpleBase64Encoder.decode(notificationuploadedby[i]);
                                byte[] notificationuploadedbyDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationuploadedbyEncryptedBytes);
                                notificationuploadedbyplain[i] = new String(notificationuploadedbyDecryptedBytes);
                            }


                        } catch (Exception e) {
                            //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                }


                new GetLastUpdatedNotification().execute();
            }
        }.execute();
    }

    class GetNotificationsReadStatus extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {

                json = jParser.makeHttpRequest(Z.url_GetNotificationsByAdminMetaData, "GET", params);
                notificationpages = Integer.parseInt(json.getString("pages"));
                called_pages_notification = new int[notificationpages];
                total_no_of_notifications = Integer.parseInt(json.getString("count"));
                unreadcountNotification = Integer.parseInt(json.getString("unreadcount"));

                json = jParser.makeHttpRequest(Z.url_GetReadStatusOfNotificationsByAdmin, "GET", params);

                readstatuscountNotification = Integer.parseInt(json.getString("count"));
                Log.d("TAGAdmin", "readstatuscountNotification: " + readstatuscountNotification);

                notificationreadstatus = new String[readstatuscountNotification];
                for (int i = 0; i < readstatuscountNotification; i++) {
                    notificationreadstatus[i] = json.getString("s" + i);
                    Log.d("TAGAdmin", "getReadStatus " + notificationreadstatus[i]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
//              Toast.makeText(AdminActivity.this, "total_no_of_notifications"+total_no_of_notifications, Toast.LENGTH_LONG).show();
//              Toast.makeText(AdminActivity.this, "total pagess  "+notificationpages, Toast.LENGTH_LONG).show();
//
//              Toast.makeText(AdminActivity.this, "UNRead count:-"+unreadcountNotification, Toast.LENGTH_LONG).show();
//
//              Toast.makeText(AdminActivity.this, "Read count:-"+readstatuscountNotification, Toast.LENGTH_LONG).show();
//              Toast.makeText(AdminActivity.this, "notification read status 0 :-"+notificationreadstatus[0], Toast.LENGTH_LONG).show();
//              Toast.makeText(AdminActivity.this, "notification read status 1:-"+notificationreadstatus[1], Toast.LENGTH_LONG).show();
//              Toast.makeText(AdminActivity.this, "notification read status 2 :-"+notificationreadstatus[2], Toast.LENGTH_LONG).show();
                Log.d("GetReadStatus", ":total_no_of_notifications::" + total_no_of_notifications);
                Log.d("GetReadStatus", ":notificationpages::" + notificationpages);
                Log.d("GetReadStatus", ": readcountNotification::" + unreadcountNotification);


            } catch (Exception e) {
                Toast.makeText(EditNotification.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }



            new GetNotifications().execute();

        }
    }

    class GetNotifications extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("p", page_to_call_notification + ""));
            json = jParser.makeHttpRequest(Z.url_GetNotificationsSentByAdmin, "GET", params);
            try {
                notificationcount = Integer.parseInt(json.getString("count"));

                notificationids = new String[notificationcount];
                notificationtitles = new String[notificationcount];
                notificationnotifications = new String[notificationcount];
                notificationfilename1 = new String[notificationcount];
                notificationfilename2 = new String[notificationcount];
                notificationfilename3 = new String[notificationcount];
                notificationfilename4 = new String[notificationcount];
                notificationfilename5 = new String[notificationcount];
                notificationuploadtime = new String[notificationcount];
                notificationlastmodified = new String[notificationcount];
                notificationuploadedby = new String[notificationcount];
                notificationuploadedbyplain = new String[notificationcount];
                for (int i = 0; i < notificationcount; i++) {
                    notificationids[i] = json.getString("id" + i);
                    notificationtitles[i] = json.getString("title" + i);
                    notificationnotifications[i] = json.getString("notification" + i);
                    notificationfilename1[i] = json.getString("filename1" + i);
                    notificationfilename2[i] = json.getString("filename2" + i);
                    notificationfilename3[i] = json.getString("filename3" + i);
                    notificationfilename4[i] = json.getString("filename4" + i);
                    notificationfilename5[i] = json.getString("filename5" + i);
                    notificationuploadtime[i] = json.getString("uploadtime" + i);
                    notificationlastmodified[i] = json.getString("lastmodified" + i);
                    notificationuploadedby[i] = json.getString("uploadedby" + i);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {


            for (int i = 0; i < notificationcount; i++)
                try {


                    if (notificationtitles[i] != null) {
                        byte[] notificationtitlesEncryptedBytes = SimpleBase64Encoder.decode(notificationtitles[i]);
                        byte[] notificationtitlesDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationtitlesEncryptedBytes);
                        notificationtitles[i] = new String(notificationtitlesDecryptedBytes);
//                        Toast.makeText(AdminActivity.this, "title"+notificationtitles[i], Toast.LENGTH_SHORT).show();
                        Log.d("notificationdecripted", "notificationtitles" + "[" + i + "] " + notificationtitles[i]);

                    }
                    if (notificationnotifications[i] != null) {
                        byte[] notificationnotificationsEncryptedBytes = SimpleBase64Encoder.decode(notificationnotifications[i]);
                        byte[] notificationnotificationsDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationnotificationsEncryptedBytes);
                        notificationnotifications[i] = new String(notificationnotificationsDecryptedBytes);
//                        Toast.makeText(AdminActivity.this, "notification"+notificationnotifications[i], Toast.LENGTH_SHORT).show();

                    }
                    if (notificationfilename1[i] != null) {
                        if (!notificationfilename1[i].equals("null")) {
                            byte[] notificationfilename1EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename1[i]);
                            byte[] notificationfilename1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename1EncryptedBytes);
                            notificationfilename1[i] = new String(notificationfilename1DecryptedBytes);
                        }
                    }
                    if (notificationfilename2[i] != null) {
                        if (!notificationfilename2[i].equals("null")) {
                            byte[] notificationfilename2EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename2[i]);
                            byte[] notificationfilename2DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename2EncryptedBytes);
                            notificationfilename2[i] = new String(notificationfilename2DecryptedBytes);
                        }
                    }
                    if (notificationfilename3[i] != null) {
                        if (!notificationfilename3[i].equals("null")) {
                            byte[] notificationfilename3EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename3[i]);
                            byte[] notificationfilename3DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename3EncryptedBytes);
                            notificationfilename3[i] = new String(notificationfilename3DecryptedBytes);
                        }
                    }
                    if (notificationfilename4[i] != null) {
                        if (!notificationfilename4[i].equals("null")) {
                            byte[] notificationfilename4EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename4[i]);
                            byte[] notificationfilename4DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename4EncryptedBytes);
                            notificationfilename4[i] = new String(notificationfilename4DecryptedBytes);
                        }
                    }
                    if (notificationfilename5[i] != null) {
                        if (!notificationfilename5[i].equals("null")) {
                            byte[] notificationfilename5EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename5[i]);
                            byte[] notificationfilename5DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename5EncryptedBytes);
                            notificationfilename5[i] = new String(notificationfilename5DecryptedBytes);
                        }
                    }
                    if (notificationuploadtime[i] != null) {
                        byte[] notificationuploadtimeEncryptedBytes = SimpleBase64Encoder.decode(notificationuploadtime[i]);
                        byte[] notificationuploadtimeDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationuploadtimeEncryptedBytes);
                        notificationuploadtime[i] = new String(notificationuploadtimeDecryptedBytes);
                    }
                    if (notificationlastmodified[i] != null) {
                        byte[] notificationlastmodifiedEncryptedBytes = SimpleBase64Encoder.decode(notificationlastmodified[i]);
                        byte[] notificationlastmodifiedDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationlastmodifiedEncryptedBytes);
                        notificationlastmodified[i] = new String(notificationlastmodifiedDecryptedBytes);
                    }
                    if (notificationuploadedby[i] != null) {
//                        byte[] notificationuploadedbyEncryptedBytes = SimpleBase64Encoder.decode(notificationuploadedby[i]);
//                        byte[] notificationuploadedbyDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationuploadedbyEncryptedBytes);
                        notificationuploadedbyplain[i] = Decrypt(notificationuploadedby[i],MySharedPreferencesManager.getDigest1(EditNotification.this),MySharedPreferencesManager.getDigest2(EditNotification.this));
                        Log.d("notificationuploadedby", "notificationuploadedby" + "[" + i + "] " +  notificationuploadedbyplain[i] );

                    }


                } catch (Exception e) {
                    //Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }


            new GetLastUpdatedNotification().execute();
//            addNotificationdatatoAdapter();


        }
    }

    class GetLastUpdatedNotification extends AsyncTask<String, String, String> {

        String s = "";

        protected String doInBackground(String... param) {
            String r = null;


            Set<String> uniqKeys = new TreeSet<String>();
            uniqKeys.addAll(Arrays.asList(notificationuploadedbyplain));


            uniqueUploadersNotification = uniqKeys.toArray(new String[uniqKeys.size()]);
            uniqueUploadersEncNotification = new String[uniqueUploadersNotification.length];
            lastupdatedNotification = new String[uniqueUploadersNotification.length];
            for (int j = 0; j < uniqueUploadersNotification.length; j++) {
                for (int i = 0; i < notificationcount; i++) {

                    if (notificationuploadedbyplain[i].equals(uniqueUploadersNotification[j])) {
                        uniqueUploadersEncNotification[j] = notificationuploadedby[i];
                    }
                }
            }
            for (int i = 0; i < uniqueUploadersNotification.length; i++) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", uniqueUploadersEncNotification[i]));       //0
                json = jParser.makeHttpRequest(Z.url_getlastupdated, "GET", params);
                try {
                    s = json.getString("lastupdated");
                    if (s.equals("noupdate")) {
//                         Toast.makeText(AdminActivity.this,notificationuploadedbyplain[i]+"\n"+s , Toast.LENGTH_SHORT).show();
                    } else {
                        lastupdatedNotification[i] = s;
//                         Toast.makeText(AdminActivity.this,notificationuploadedbyplain[i]+"\n"+s , Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                }
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
//            Toast.makeText(AdminActivity.this,"uniqueUploadersNotification:- \n"+uniqueUploadersNotification[0] , Toast.LENGTH_LONG).show();
//
//            Toast.makeText(AdminActivity.this,notificationuploadedbyplain[0]+"\n"+notificationuploadedby[0] , Toast.LENGTH_LONG).show();


//            for(int i=0;i<lastupdated.length;i++)
//            {
//                if(lastupdated[i]==null) {
//                 //   Toast.makeText(MainActivity.this, uniqueUploaders[i] + "\n nulla it is", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(AdminActivity.this, uniqueUploaders[i] + "\n" + lastupdated[i], Toast.LENGTH_SHORT).show();
//
//
//                }
//            }
            if (s.equals("noupdate")) {
                Toast.makeText(EditNotification.this, "no update", Toast.LENGTH_SHORT).show();
            } else {
//                lastupdatedNotification [i]=s;
            }

            if (!isLastPageLoadedNotification) {
            }
            addNotificationdatatoAdapter();

        }

    }

    class DeleteNotifications extends AsyncTask<String, String, String> {


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


            json = jParser.makeHttpRequest(Z.url_DeleteNotification, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                Toast.makeText(EditNotification.this, result, Toast.LENGTH_LONG).show();
                Log.d("Tag", "onPostExecute: " + result);
                goBack();
                getNotifications();

            } catch (Exception e) {
                Log.d("Tag", "onPostExecute: " + result);
                Log.d("Tag", "onPostExecute: " + e.getMessage());
                Toast.makeText(EditNotification.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    public abstract class EndlessRecyclerOnScrollListenerNotification extends RecyclerView.OnScrollListener {


        private LinearLayoutManager mLinearLayoutManager;

        public EndlessRecyclerOnScrollListenerNotification(LinearLayoutManager linearLayoutManager) {
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            visibleItemCountNotification = recyclerView.getChildCount();
            totalItemCountNotification = mLinearLayoutManager.getItemCount();
            firstVisibleItemNotification = mLinearLayoutManager.findFirstVisibleItemPosition();

            if (loadingNotification) {
                if (totalItemCountNotification > previousTotalNotification) {
                    loadingNotification = false;
                    previousTotalNotification = totalItemCountNotification;
                }
            }
            if (!loadingNotification && (totalItemCountNotification - visibleItemCountNotification)
                    <= (firstVisibleItemNotification + visibleThresholdNotification)) {
                // End has been reached

                // Do something
                current_page_notification++;

                onLoadMore(current_page_notification);

                loadingNotification = true;
            }
        }

        public abstract void onLoadMore(int current_page);
    }

}

