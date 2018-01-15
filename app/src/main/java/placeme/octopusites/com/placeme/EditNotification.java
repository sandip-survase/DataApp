package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.widget.SwipeRefreshLayout;
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
import placeme.octopusites.com.placeme.modal.RecyclerItemEdit;
import placeme.octopusites.com.placeme.modal.RecyclerItemEditNotificationAdapter;
import placeme.octopusites.com.placeme.modal.RecyclerTouchListener;

import static placeme.octopusites.com.placeme.AES4all.fromString;

public class EditNotification extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";

    String TAG = "EditNotificationtag";

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
    ArrayList<String> notificationdeleteArraylist = new ArrayList<>();
    SwipeRefreshLayout tswipe_refresh_layout;
    ArrayList<RecyclerItemEdit> itemlistfromserver = new ArrayList<>();
    //notification deletion
    private MaterialSearchView searchView;
    //edit part
    private int previousTotalNotification = 0; // The total number of items in the dataset after the last load
    private boolean loadingNotification = true; // True if we are still waiting for the last set of data to load.
    private int visibleThresholdNotification = 0; // The minimum amount of items to have below your current scroll position before loading more.
    private int page_to_call_notification = 1;
    private int current_page_notification = 1;
    private String plainusername, username = "", fname = "", mname = "", sname = "";
    private List<RecyclerItemEdit> itemListNotification = new ArrayList<>();
    private RecyclerView recyclerViewNotification;
    private ArrayList<RecyclerItemEdit> itemListNotificationNew = new ArrayList<>();
    private RecyclerItemEditNotificationAdapter mAdapterNotificationEdit;
    boolean newCreatedNotification = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notification);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Edit Notification</font>"));

        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        Z.NetworkConnectoin(EditNotification.this);

        username = MySharedPreferencesManager.getUsername(this);

        role = MySharedPreferencesManager.getRole(this);

        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerViewNotification = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapterNotificationEdit = new RecyclerItemEditNotificationAdapter(itemListNotificationNew, EditNotification.this);
        recyclerViewNotification.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerNotification = new LinearLayoutManager(this);
        recyclerViewNotification.setLayoutManager(linearLayoutManagerNotification);
        recyclerViewNotification.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNotification.setAdapter(mAdapterNotificationEdit);


        recyclerViewNotification.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewNotification, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                    RecyclerItemEdit item = itemListNotificationNew.get(position);


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


                    } else if (role.equals("hr")) {
                        startActivity(new Intent(EditNotification.this, EditNotificationHrMain.class));

                    } else if (role.equals("admin")) {

                        Intent i1 = new Intent(EditNotification.this, CreateNotification.class);
                        i1.putExtra("flag", "EditNotification");
                        i1.putExtra("id", item.getId());
                        i1.putExtra("title", item.getTitle());
                        i1.putExtra("notification", item.getNotification());


                        if (item.getFilename1() != null) {
                            i1.putExtra("file1", Z.Decrypt(item.getFilename1(), EditNotification.this));
                        } else {
                            i1.putExtra("file1", item.getFilename1());
                        }

                        if (item.getFilename2() != null) {
                            i1.putExtra("file2", Z.Decrypt(item.getFilename2(), EditNotification.this));
                            i1.putExtra("file2", Z.Decrypt(item.getFilename2(), EditNotification.this));
                        } else {
                            i1.putExtra("file2", item.getFilename2());
                        }
                        if (item.getFilename3() != null) {
                            i1.putExtra("file3", Z.Decrypt(item.getFilename3(), EditNotification.this));
                        } else {
                            i1.putExtra("file3", item.getFilename3());
                        }
                        if (item.getFilename4() != null) {
                            i1.putExtra("file4", Z.Decrypt(item.getFilename4(), EditNotification.this));
                        } else {
                            i1.putExtra("file4", item.getFilename4());
                        }

                        if (item.getFilename5() != null) {
                            i1.putExtra("file5", Z.Decrypt(item.getFilename5(), EditNotification.this));
                        } else {
                            i1.putExtra("file5", item.getFilename5());
                        }

                        i1.putExtra("uploadedby", item.getUploadedby());
                        i1.putExtra("uploadtime", item.getUploadtime());
                        i1.putExtra("lastmodified", item.getLastmodified());
                        Log.d("Check2", "id: " + item.getId());
                        Log.d("Check2", "notiff: " + item.getNotification());
                        Log.d("Check2", "title: " + item.getTitle());
                        Log.d("Check2", "file1: " + item.getFilename1());
                        Log.d("Check2", "lastmodified: " + item.getLastmodified());
                        Log.d("Check2", "file5: " + item.getFilename5());
                        startActivityForResult(i1, 99);



                    }

                } catch (Exception e) {
                    Log.d("Exception", " " + e.getMessage());
                }
            }


            @Override
            public void onLongClick(View view, int position) {
                RecyclerItemEdit item = itemListNotificationNew.get(position);
                Log.d("POsition", "onLongClick: " + position);
                Log.d("id", "onLongClick: " + item.getId());

                if (deleteflag == 0) {
//                    int retval = itemListNotification.size();
//                    selectedPositions=new int[retval];
//                    selectedViews=new View[retval];


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
        recyclerViewNotification.setOnScrollListener(new EndlessRecyclerOnScrollListenerNotification(linearLayoutManagerNotification) {
            @Override
            public void onLoadMore(int current_page) {

                if (total_no_of_notifications > 20) {
                    Log.d("here", "onLoadMore: ");
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
        tswipe_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);


        TextView editnotitxt = (TextView) findViewById(R.id.editnotitxt);
        TextView editnotinotitxt = (TextView) findViewById(R.id.editnotinotitxt);
        editnotitxt.setTypeface(Z.getBold(this));
        editnotinotitxt.setTypeface(Z.getLight(this));
        deletenotitxt.setTypeface(Z.getBold(this));

        tswipe_refresh_layout.setVisibility(View.VISIBLE);
        tswipe_refresh_layout.setRefreshing(true);
        getNotifications2();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tswipe_refresh_layout.setRefreshing(false);
                if (mAdapterNotificationEdit.getItemCount() == 0) {
                    Toast.makeText(EditNotification.this, "Couldn't Process Your request.Kindly Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        }, 10000);

        tswipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNotifications2();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tswipe_refresh_layout.setRefreshing(false);
                        if (mAdapterNotificationEdit.getItemCount() == 0) {
                            Toast.makeText(EditNotification.this, "Couldn't Process Your request.please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 10000);
            }
        });


        tswipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNotifications2();
            }
        });


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


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(EditNotification.this.getResources().getColor(R.color.transparent)));

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

                                            DeleteNotifications();
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
            tswipe_refresh_layout.setRefreshing(true);
//            recyclerViewNotification.setVisibility(View.GONE);
//           recyclerViewNotification.setVisibility(View.VISIBLE);

            for (int i = 0; i < mAdapterNotificationEdit.getItemCount(); i++) {
                if (selectedViews[i] != null) {
                    selectedViews[i].setBackgroundColor(Color.TRANSPARENT);
                }
                selectedPositions[i] = 0;
            }

            setNormalActionbar();
            deleteflag = 0;
            showSearchMenu();
            selectedCount = 0;
            getNotifications2();

        } else if (newCreatedNotification) {
            setResult(AdminActivity.ADMIN_CREATE_DATA_CHANGE_RESULT_CODE);
            super.onBackPressed();

        } else {
            setResult(299);
        super.onBackPressed();
        }



    }


    void getNotifications2() {
        previousTotalNotification = 0;
        loadingNotification = true;
        page_to_call_notification = 1;
        isFirstRunNotification = true;
        isLastPageLoadedNotification = false;
        lastPageFlagNotification = 0;
//        new GetNotificationsByadminMetadata().execute();
        GetNotificationsByadminMetadata();

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


    private void GetplacementbyAdmin() {
        Log.d("TAG", "getCurrentConnectionQuality : " + AndroidNetworking.getCurrentConnectionQuality() + " currentBandwidth : " + AndroidNetworking.getCurrentBandwidth());

        AndroidNetworking.post(Z.url_GetNotificationsSentByAdmin)
                .setTag(this)
                .addQueryParameter("u", username)
                .addQueryParameter("p", page_to_call_notification + "")
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
                        ArrayList<RecyclerItemEdit> itemlistfromserver = new ArrayList<>();
                        Log.d("TAG", "onResponse object2 : " + response.toString());
                        try {
                            Log.d("json1", "jsonparamsList " + response.getString("jsonparamsList"));
                            itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(response.getString("jsonparamsList"), "I09jdG9wdXMxMkl0ZXMjJQ==", "I1BsYWNlMTJNZSMlJSopXg==");
                            Log.d("itemlistfromserver", "reg=======================" + itemlistfromserver.size());

                            itemListNotificationNew.clear();
                            setserverlisttoadapter(itemlistfromserver);
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
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }


    void setserverlisttoadapter(ArrayList<RecyclerItemEdit> itemlist) {


        if (lastPageFlagNotification == 1)
            isLastPageLoadedNotification = true;

        itemListNotificationNew.addAll(itemlist);
        recyclerViewNotification.getRecycledViewPool().clear();
        mAdapterNotificationEdit.notifyDataSetChanged();

        selectedPositions = new int[total_no_of_notifications];
        selectedViews = new View[total_no_of_notifications];
//

        tswipe_refresh_layout.setVisibility(View.VISIBLE);
        tswipe_refresh_layout.setRefreshing(false);


    }

    class GetNotificationsByadminMetadata extends AsyncTask<String, String, String> {


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

                Log.d("FinaltestN", "notificationpages: " + notificationpages);
                Log.d("FinaltestN", "total_no_of_notifications: " + total_no_of_notifications);
                Log.d("FinaltestN", "unreadcountNotification: " + unreadcountNotification);

//
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            try {


//                new GetplacementbyAdmin().execute();


            } catch (Exception e) {
                Toast.makeText(EditNotification.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }


        }
    }


    private void GetNotificationsByadminMetadata() {
        Log.d("TAG", "getCurrentConnectionQuality : " + AndroidNetworking.getCurrentConnectionQuality() + " currentBandwidth : " + AndroidNetworking.getCurrentBandwidth());
        AndroidNetworking.post(Z.url_GetNotificationsByAdminMetaData)
                .setTag(this)
                .addQueryParameter("u", username)
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
                            notificationpages = Integer.parseInt(response.getString("pages"));
                            called_pages_notification = new int[notificationpages];
                            total_no_of_notifications = Integer.parseInt(response.getString("count"));
                            unreadcountNotification = Integer.parseInt(response.getString("unreadcount"));

                            Log.d("FinaltestN", "notificationpages: " + notificationpages);
                            Log.d("FinaltestN", "total_no_of_notifications: " + total_no_of_notifications);
                            Log.d("FinaltestN", "unreadcountNotification: " + unreadcountNotification);

                            GetplacementbyAdmin();
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
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }


    private void simulateLoadingNotification() {
        tswipe_refresh_layout.setRefreshing(true);


        if (page_to_call_notification < notificationpages)
            page_to_call_notification++;
        Log.d(TAG, "Movies to release from reports:" + page_to_call_notification);
        Log.d(TAG, "projects :" + notificationpages);
        if (page_to_call_notification < notificationpages)
            page_to_call_notification++;

        if (page_to_call_notification != notificationpages) {

            AndroidNetworking.post(Z.url_GetNotificationsSentByAdmin)
                    .setTag(this)
                    .addQueryParameter("u", username)
                    .addQueryParameter("p", page_to_call_notification + "")

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
                            Log.d(TAG, "onResponse object2 : " + response.toString());
                            try {
                                itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(response.getString("jsonparamsList"), MySharedPreferencesManager.getDigest1(EditNotification.this), MySharedPreferencesManager.getDigest2(EditNotification.this));
                                Log.d(TAG, " Movies from Hollywood" + itemlistfromserver.size());
                                Log.d(TAG, " Hollywood movie trailer 1" + itemlistfromserver.get(0).getNotification());
                                if (!isLastPageLoadedNotification) {
                                    setserverlisttoadapter(itemlistfromserver);
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
                                Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                                Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                                Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            } else {
                                // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            }
                        }
                    });
        } else {
            if (!isLastPageLoadedNotification) {
                lastPageFlagNotification = 1;

                AndroidNetworking.post(Z.url_GetNotificationsSentByAdmin)
                        .setTag(this)
                        .addQueryParameter("u", username)
                        .addQueryParameter("p", page_to_call_notification + "")

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
                                Log.d(TAG, "onResponse object2 : " + response.toString());
                                try {

                                    itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(response.getString("jsonparamsList"), MySharedPreferencesManager.getDigest1(EditNotification.this), MySharedPreferencesManager.getDigest2(EditNotification.this));
                                    Log.d(TAG, " Movies from Hollywood" + itemlistfromserver.size());
                                    Log.d(TAG, " Hollywood movie trailer 1" + itemlistfromserver.get(0).getNotification());
                                    if (!isLastPageLoadedNotification) {

                                        setserverlisttoadapter(itemlistfromserver);
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
                                    Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                                    Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                                    Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                                } else {
                                    // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                    Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                                }
                            }
                        });
            } else {
                tswipe_refresh_layout.setRefreshing(false);

            }

        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AdminActivity.ADMIN_CREATE_DATA_CHANGE_RESULT_CODE) {
            newCreatedNotification = true;
            getNotifications2();
        }

    }


    private void DeleteNotifications() {

        String deletidsArray[] = notificationdeleteArraylist.toArray(new String[notificationdeleteArraylist.size()]);
        String deletids = Arrays.toString(deletidsArray);
        deletids = deletids.trim();


        Log.d(TAG, ":username " + username);
        Log.d(TAG, ":deletids " + deletids);

        AndroidNetworking.post(Z.url_DeleteNotification)
                .setTag(this)
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
                                    Toast.makeText(EditNotification.this, "Notifications deleted successfully", Toast.LENGTH_LONG).show();
                                    newCreatedNotification=true;
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

