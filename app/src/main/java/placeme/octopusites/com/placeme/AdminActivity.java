package placeme.octopusites.com.placeme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ConnectionQuality;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.androidnetworking.interfaces.ConnectionQualityChangeListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.soundcloud.android.crop.Crop;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;
import okhttp3.OkHttpClient;
import placeme.octopusites.com.placeme.modal.RecyclerItemAdapterPlacement;
import placeme.octopusites.com.placeme.modal.RecyclerItemEdit;
import placeme.octopusites.com.placeme.modal.RecyclerItemEditNotificationAdapter;
import placeme.octopusites.com.placeme.modal.RecyclerItemPlacement;
import placeme.octopusites.com.placeme.modal.RecyclerTouchListener;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;
import static placeme.octopusites.com.placeme.LoginActivity.md5;

public class AdminActivity extends AppCompatActivity implements ImagePickerCallback {

    File Imgfile;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final int ADMIN_DATA_CHANGE_RESULT_CODE = 111;
    public static final int ADMIN_CREATE_DATA_CHANGE_RESULT_CODE = 999;
    public static final String url_GetPlacementsCreatedByHr = "http://192.168.100.30:8080/CreateNotificationTemp/NotificationlistTest";
    String TAG = "DeepikaPadukone";
    CircleImageView profile;
    boolean doubleBackToExitPressedOnce = false;
    JSONParser jParser = new JSONParser();
    String digest1, digest2;
    JSONObject json;
    FrameLayout mainfragment;
    FrameLayout crop_layout;
    ImagePicker imagePicker;
    String filepath = "", filename = "";
    String directory;
    List<String> response;
    SwipeRefreshLayout tswipe_refresh_layout;
    int crop_flag = 0;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";
    int notificationorplacementflag = 1;
    int navMenuFlag = 0, oldNavMenuFlag = 0;
    int count = 0, id[], pcount = 0;
    String heading[], notification[];
    Handler handler = new Handler();
    RelativeLayout createnotificationrl, editnotificationrl;
    int searchNotificationFlag = 0, searchPlacementFlag = 0;
    ArrayList<RecyclerItemEdit> tempListNotification;
    int unreadcountNotification = 0;
    RelativeLayout notificationcountrl;
    TextView notificationcounttxt;
    int notificationplacementflag = 0;
    int total_no_of_notifications;
    int firstVisibleItemNotification, visibleItemCountNotification, totalItemCountNotification;
    int[] called_pages_notification;
    boolean isFirstRunNotification = true, isLastPageLoadedNotification = false;
    int lastPageFlagNotification = 0;
    int notificationcount = 0;
    int readstatuscountNotification = 0;
    int notificationpages = 0;
    String buffrrcontent;
    String notificationids[], notificationtitles[], notificationnotifications[], notificationfilename1[], notificationfilename2[], notificationfilename3[], notificationfilename4[], notificationfilename5[], notificationuploadtime[], notificationlastmodified[], notificationuploadedby[], notificationuploadedbyplain[];
    String[] uniqueUploadersNotification;
    String[] uniqueUploadersEncNotification;
    String lastupdatedNotification[];
    String notificationreadstatus[];
    int selectedMenuFlag = 1;
    ArrayList<RecyclerItemPlacement> tempListPlacement;
    int unreadcountPlacement = 0;
    int placementcount = 0;
    int readstatuscountPlacement = 0;
    RelativeLayout placementcountrl, messagecountrl;
    TextView placementcounttxt, messagecount;
    int firstVisibleItemPlacement, visibleItemCountPlacement, totalItemCountPlacement;
    int total_no_of_placements;
    int placementpages = 0;
    String placementids[], placementcompanyname[], placementcpackage[], placementpost[], placementforwhichcourse[], placementforwhichstream[], placementvacancies[], placementlastdateofregistration[], placementdateofarrival[], placementbond[], placementnoofapti[], placementnooftechtest[], placementnoofgd[], placementnoofti[], placementnoofhri[], placementstdx[], placementstdxiiordiploma[], placementug[], placementpg[], placementuploadtime[], placementlastmodified[], placementuploadedby[], placementuploadedbyplain[], placementnoofallowedliveatkt[], placementnoofalloweddeadatkt[];
    boolean isFirstRunPlacement = true, isLastPageLoadedPlacement = false;
    int lastPageFlagPlacement = 0;
    int[] called_pages_placement;
    String placementreadstatus[];
    String[] uniqueUploadersPlacement;
    String[] uniqueUploadersEncPlacement;
    String lastupdatedPlacement[];
    TextView createPlacementOrNotification, editPlacementOrNotification;
    PlacementEditData settag = new PlacementEditData();
    //notification
    String reciever_username[], reciever_uid[];
    String unread_count[];
    int unreadMessageCount = 0;
    String sender_uid;

    ArrayList<RecyclerItemEdit> itemlistfromserver = new ArrayList<>();
    ArrayList<RecyclerItemPlacement> placementListfromserver = new ArrayList<>();
    private ImageView resultView;
    private int chooserType;
    private String mediaPath;
    private String finalPath;
    private String plainusername, username = "", fname = "", mname = "", sname = "";
    private String thumbPath;
    private MaterialSearchView searchView;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private int previousTotalNotification = 0; // The total number of items in the dataset after the last load
    private boolean loadingNotification = true; // True if we are still waiting for the last set of data to load.
    private int visibleThresholdNotification = 0; // The minimum amount of items to have below your current scroll position before loading more.
    private int page_to_call_notification = 1;
    private int current_page_notification = 1;
    private boolean loadingPlacement = true; // True if we are still waiting for the last set of data to load.
    private int previousTotalPlacement = 0; // The total number of items in the dataset after the last load
    private int visibleThresholdPlacement = 0; // The minimum amount of items to have below your current scroll position before loading more.
    private int page_to_call_placement = 1;
    private int current_page_placement = 1;
    private TextView toolbar_title;
    Toolbar toolbar;
    TextView bluePanelTv;


    RelativeLayout admincontrolsrl;
    private RecyclerView recyclerViewNotification, recyclerViewPlacement;

    private ArrayList<RecyclerItemEdit> itemListNotificationNew = new ArrayList<>();
    private RecyclerItemEditNotificationAdapter mAdapterNotificationEdit;
    private ArrayList<RecyclerItemPlacement> itemListPlacementnew = new ArrayList<>();
    private RecyclerItemAdapterPlacement mAdapterPlacement;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //first change

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ShouldAnimateProfile.AdminActivity = AdminActivity.this;
        ShouldAnimateProfile.isInside = true;
        Z.CountOfUsersUnderAdmin = "0";

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(Z.getRighteous(AdminActivity.this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar_title.setText("Notifications");

        admincontrolsrl = (RelativeLayout) findViewById(R.id.admincontrolsrl);

        String encUsername = MySharedPreferencesManager.getUsername(this);
        //sss
        imagePicker = new ImagePicker(this);
        imagePicker.setImagePickerCallback(this);
        imagePicker.shouldGenerateMetadata(false); // Default is true
        imagePicker.shouldGenerateThumbnails(false); // Default is true


        crop_layout = (FrameLayout) findViewById(R.id.crop_layout);
        resultView = (ImageView) findViewById(R.id.result_image);
        tswipe_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);


        username = MySharedPreferencesManager.getUsername(this);
        String pass = MySharedPreferencesManager.getPassword(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        String role = MySharedPreferencesManager.getRole(this);


        //

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();

        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        OkHttpUtil.getClient();
//        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.initialize(getApplicationContext(), OkHttpUtil.getClient());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        AndroidNetworking.setBitmapDecodeOptions(options);
        AndroidNetworking.enableLogging();
        AndroidNetworking.setConnectionQualityChangeListener(new ConnectionQualityChangeListener() {
            @Override
            public void onChange(ConnectionQuality currentConnectionQuality, int currentBandwidth) {
                Log.d(TAG, "onChange: currentConnectionQuality : " + currentConnectionQuality + " currentBandwidth : " + currentBandwidth);
            }
        });


        MySharedPreferencesManager.save(AdminActivity.this, "intro", "yes");
        MySharedPreferencesManager.save(AdminActivity.this, "otp", "no");
        MySharedPreferencesManager.save(AdminActivity.this, "activationMessage", "no");
        MySharedPreferencesManager.save(AdminActivity.this, "activatedCode", "no");

        new isVerified().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        try {
            String plainusername = Z.Decrypt(username,AdminActivity.this);
            String data = new String(Z.Decrypt(pass,AdminActivity.this));
            String hash = md5(data + MySharedPreferencesManager.getDigest3(this));
            new LoginFirebaseTask().execute(plainusername, hash);
        } catch (Exception e) {
        }

        bluePanelTv = (TextView) findViewById(R.id.bluePanelTv);
        bluePanelTv.setTypeface(Z.getBold(this));
        refreshUserCount();

        recyclerViewNotification = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewPlacement = (RecyclerView) findViewById(R.id.recycler_view_placement);

        if (notificationorplacementflag == 1) {
            recyclerViewNotification.setVisibility(View.VISIBLE);
            recyclerViewPlacement.setVisibility(View.GONE);

        } else if (notificationorplacementflag == 2) {
            recyclerViewNotification.setVisibility(View.GONE);
            recyclerViewPlacement.setVisibility(View.VISIBLE);
        }

        createnotificationrl = (RelativeLayout) findViewById(R.id.createnotificationrl);
        editnotificationrl = (RelativeLayout) findViewById(R.id.editnotificationrl);

        createnotificationrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notificationorplacementflag == 1) {
                    if (Z.isAdminHrVerified(AdminActivity.this)) {
                        Intent i1 = new Intent(AdminActivity.this, CreateNotification.class);
                        i1.putExtra("flag", "fromAdminActivity");
                        startActivityForResult(i1, 0);

//                        startActivityForResult(new Intent(getActivity(), MyProfileKnownLang.class).putExtra("username", username), 0);
//                        for result
                    } else {
                        Toast.makeText(AdminActivity.this, "Your account is still not verified. Please wait while we are verifying your account as TPO & you will get a notification after successful Verification ", Toast.LENGTH_LONG).show();
                    }


                } else if (notificationorplacementflag == 2) {

                    if (Z.isAdminHrVerified(AdminActivity.this)) {
                        String Tag = "adminActivity";
                        settag.setActivityFromtag(Tag);

                        startActivityForResult(new Intent(AdminActivity.this, CreatePlacement.class),0);

                    } else {
                        Toast.makeText(AdminActivity.this, "Your account is still not verified. Please wait while we are verifying your account as TPO & you will get a notification after successful Verification ", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });
        editnotificationrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notificationorplacementflag == 1) {
                    if (Z.isAdminHrVerified(AdminActivity.this)) {
                        startActivity(new Intent(AdminActivity.this, EditNotification.class));

                    } else {
                        Toast.makeText(AdminActivity.this, "Your account is still not verified. Please wait while we are verifying your account as TPO & you will get a notification after successful Verification ", Toast.LENGTH_LONG).show();
                    }


                } else if (notificationorplacementflag == 2) {
                    if (Z.isAdminHrVerified(AdminActivity.this)) {
                        startActivity(new Intent(AdminActivity.this, EditPlacement.class));

                    } else {
                        Toast.makeText(AdminActivity.this, "Your account is still not verified. Please wait while we are verifying your account as TPO & you will get a notification after successful Verification ", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        createPlacementOrNotification = (TextView) findViewById(R.id.createnotificationtxt);
        editPlacementOrNotification = (TextView) findViewById(R.id.editnotificationtxt);
        createPlacementOrNotification.setTypeface(Z.getBold(this));
        editPlacementOrNotification.setTypeface(Z.getBold(this));

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (selectedMenuFlag == 0) {

                } else if (selectedMenuFlag == 1) {
                    searchNotificationFlag = 1;
                    filterNotifications(newText);
                } else if (selectedMenuFlag == 2) {
                    searchPlacementFlag = 1;
                    filterPlacements(newText);
                } else if (selectedMenuFlag == 3) {

                } else if (selectedMenuFlag == 4) {

                } else if (selectedMenuFlag == 5) {

                } else if (selectedMenuFlag == 6) {

                }

                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                admincontrolsrl.setVisibility(View.GONE);
                final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
                upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                searchView.setBackIcon(upArrow);
            }

            @Override
            public void onSearchViewClosed() {

                admincontrolsrl.setVisibility(View.VISIBLE);

            }
        });

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals("pushreceived")) {

                    Log.d("TAG", "push broadcast received: ");
                    new GetUnreadMessagesCount().execute();
                    RefreshNotificationCount();
                    RefreshPlacementCount();
                    MessagesFragment fragment = (MessagesFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
                    if (fragment != null)
                        fragment.addMessages();
                }
            }
        };
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AdminActions.class));
            }
        });

        RelativeLayout bottompanel = (RelativeLayout) findViewById(R.id.bottompanel);
        bottompanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, ShowUsers.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                if (oldNavMenuFlag != navMenuFlag) {
                    if (navMenuFlag == 1) {
                        crop_layout.setVisibility(View.GONE);
                        AdminProfileFragment fragment = new AdminProfileFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("My Profile");

                        admincontrolsrl.setVisibility(View.GONE);

                    } else if (navMenuFlag == 2) {

                        notificationorplacementflag = 1;

                        crop_layout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Notifications");

                        mainfragment.setVisibility(View.GONE);
                        tswipe_refresh_layout.setVisibility(View.VISIBLE);
                        tswipe_refresh_layout.setRefreshing(true);
                        createPlacementOrNotification.setText("Create Notification");
                        editPlacementOrNotification.setText("Edit Notification");
                        recyclerViewNotification.setVisibility(View.VISIBLE);
                        recyclerViewPlacement.setVisibility(View.GONE);
//                      createPlacementOrNotification=(TextView)findViewById(R.id.createnotificationtxt) ;
//                      editPlacementOrNotification
                        selectedMenuFlag = 1;
                        getNotifications2();
//                      recyclerViewNotification.setVisibility(View.VISIBLE);
//                      recyclerViewPlacement.setVisibility(View.GONE);

                        admincontrolsrl.setVisibility(View.VISIBLE);
                    } else if (navMenuFlag == 3) {

                        notificationorplacementflag = 2;

                        crop_layout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Placements");
                        mainfragment.setVisibility(View.GONE);
                        createPlacementOrNotification.setText("Create Placements");
                        editPlacementOrNotification.setText("Edit Placements");
                        tswipe_refresh_layout.setVisibility(View.VISIBLE);

                        recyclerViewNotification.setVisibility(View.GONE);
                        recyclerViewPlacement.setVisibility(View.VISIBLE);

                        selectedMenuFlag = 2;

                        getPlacements2();

                        admincontrolsrl.setVisibility(View.VISIBLE);

                    } else if (navMenuFlag == 4) {
                        crop_layout.setVisibility(View.GONE);
                        MessagesFragment fragment = new MessagesFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();

                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Messages");
                        tswipe_refresh_layout.setVisibility(View.GONE);

                        admincontrolsrl.setVisibility(View.GONE);

                    } else if (navMenuFlag == 5) {
                        crop_layout.setVisibility(View.GONE);
                        SettingsFragment fragment = new SettingsFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Settings");
                        tswipe_refresh_layout.setVisibility(View.GONE);

                        admincontrolsrl.setVisibility(View.GONE);
                    } else if (navMenuFlag == 6) {
                        crop_layout.setVisibility(View.GONE);
                        NoDataAvailableFragment fragment = new NoDataAvailableFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Blog");
                        tswipe_refresh_layout.setVisibility(View.GONE);

                        admincontrolsrl.setVisibility(View.GONE);
                    } else if (navMenuFlag == 7) {
                        crop_layout.setVisibility(View.GONE);
                        AboutFragment fragment = new AboutFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("About");
                        tswipe_refresh_layout.setVisibility(View.GONE);

                        admincontrolsrl.setVisibility(View.GONE);
                    }
                }

            }


            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                oldNavMenuFlag = navMenuFlag;

            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final View hView = navigationView.getHeaderView(0);
        profile = (CircleImageView) hView.findViewById(R.id.profile_image);
        new Getsingnature().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        final ImageView profilei = (ImageView) hView.findViewById(R.id.profile);
        final ImageView notificationi = (ImageView) hView.findViewById(R.id.notification);
        final ImageView placementi = (ImageView) hView.findViewById(R.id.placement);
        final ImageView settingsi = (ImageView) hView.findViewById(R.id.settings);
        final ImageView newsi = (ImageView) hView.findViewById(R.id.blog);
        final ImageView chati = (ImageView) hView.findViewById(R.id.chat);
//
        notificationcounttxt = (TextView) hView.findViewById(R.id.notificationcount);
        notificationcountrl = (RelativeLayout) hView.findViewById(R.id.notificationcountrl);
        placementcounttxt = (TextView) hView.findViewById(R.id.placementcount);
        placementcountrl = (RelativeLayout) hView.findViewById(R.id.placementcountrl);
        messagecount = (TextView) hView.findViewById(R.id.messagecount);
        messagecountrl = (RelativeLayout) hView.findViewById(R.id.messagecountrl);

        View v1 = hView.findViewById(R.id.prifileselectionview);
        View v2 = hView.findViewById(R.id.notificationselectionview);
        View v3 = hView.findViewById(R.id.placementselectionview);
        View v5 = hView.findViewById(R.id.settingselectionview);
        View v6 = hView.findViewById(R.id.blogselectionview);
        View v7 = hView.findViewById(R.id.abtselectionview);
        View v8 = hView.findViewById(R.id.chatselectionview);

        mainfragment = (FrameLayout) findViewById(R.id.mainfragment);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navMenuFlag = 1;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon_selected);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getBold(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));


                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(AdminActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 2;

                tswipe_refresh_layout.setVisibility(View.VISIBLE);

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon_selected);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getBold(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(AdminActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 3;

                tswipe_refresh_layout.setVisibility(View.VISIBLE);

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon_selected);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getBold(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(AdminActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });
        v8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 4;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon_selected);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getBold(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(AdminActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 5;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon_selected);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getBold(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(AdminActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });
        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 6;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon_selected);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getBold(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.sky_blue_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(AdminActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });
        v7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 7;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
                pt8.setTypeface(Z.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(AdminActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.sky_blue_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });

        Drawable myDrawable = getResources().getDrawable(R.drawable.notification_icon_selected);
        notificationi.setImageDrawable(myDrawable);

        TextView pt = (TextView) hView.findViewById(R.id.notificationtxt);
        pt.setTypeface(Z.getBold(AdminActivity.this));
        pt.setTextColor(getResources().getColor(R.color.sky_blue_color));

        TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
        pt1.setTypeface(Z.getLight(AdminActivity.this));
        pt1.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
        pt3.setTypeface(Z.getLight(AdminActivity.this));
        pt3.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt8 = (TextView) hView.findViewById(R.id.chattxt);
        pt8.setTypeface(Z.getLight(AdminActivity.this));
        pt8.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
        pt5.setTypeface(Z.getLight(AdminActivity.this));
        pt5.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
        pt6.setTypeface(Z.getLight(AdminActivity.this));
        pt6.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
        pt7.setTypeface(Z.getLight(AdminActivity.this));
        pt7.setTextColor(getResources().getColor(R.color.while_color));


//
//        mAdapterNotification = new RecyclerItemAdapter(itemListNotification);
//        recyclerViewNotification.setHasFixedSize(true);
//        final LinearLayoutManager linearLayoutManagerNotification = new LinearLayoutManager(this);
//        recyclerViewNotification.setLayoutManager(linearLayoutManagerNotification);
//        recyclerViewNotification.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerViewNotification.setItemAnimator(new DefaultItemAnimator());
//        recyclerViewNotification.setAdapter(mAdapterNotification);


        //placements

        mAdapterPlacement = new RecyclerItemAdapterPlacement(itemListPlacementnew, AdminActivity.this);
        recyclerViewPlacement.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerPlacement = new LinearLayoutManager(this);
        recyclerViewPlacement.setLayoutManager(linearLayoutManagerPlacement);
        recyclerViewPlacement.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewPlacement.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlacement.setAdapter(mAdapterPlacement);


        mAdapterNotificationEdit = new RecyclerItemEditNotificationAdapter(itemListNotificationNew, AdminActivity.this);
        recyclerViewNotification.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerNotification = new LinearLayoutManager(this);
        recyclerViewNotification.setLayoutManager(linearLayoutManagerNotification);
        recyclerViewNotification.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNotification.setAdapter(mAdapterNotificationEdit);


//        ArrayList<RecyclerItemEdit> itemlistfromserver = new ArrayList<>();

        recyclerViewNotification.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewNotification, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {


                    RecyclerItemEdit itemN = null;
                    if (searchNotificationFlag == 1)
                        itemN = tempListNotification.get(position);
                    else
                        itemN = itemListNotificationNew.get(position);
                    if (!itemN.isIsread()) {
                        itemN.setIsread(true);
                        unreadcountNotification--;
                        notificationcountrl.setVisibility(View.VISIBLE);
                        notificationcounttxt.setText(unreadcountNotification + "");
                        if (unreadcountNotification == 0) {
                            notificationcountrl.setVisibility(View.GONE);
                        }

                    }


                    mAdapterNotificationEdit.notifyDataSetChanged();
                    changeReadStatusNotification(itemN.getId());

                    crop_flag = 1;
                    Intent i1 = new Intent(AdminActivity.this, ViewNotification.class);

                    i1.putExtra("id", itemN.getId());
                    i1.putExtra("title", itemN.getTitle());
                    i1.putExtra("notification", itemN.getNotification());


                    if (itemN.getFilename1() != null) {
                        i1.putExtra("file1", Z.Decrypt(itemN.getFilename1(), AdminActivity.this));
                    } else {
                        i1.putExtra("file1", itemN.getFilename1());
                    }

                    if (itemN.getFilename2() != null) {
                        i1.putExtra("file2", Z.Decrypt(itemN.getFilename2(), AdminActivity.this));
                    } else {
                        i1.putExtra("file2", itemN.getFilename2());
                    }
                    if (itemN.getFilename3() != null) {
                        i1.putExtra("file3", Z.Decrypt(itemN.getFilename3(), AdminActivity.this));
                    } else {
                        i1.putExtra("file3", itemN.getFilename3());
                    }
                    if (itemN.getFilename4() != null) {
                        i1.putExtra("file4", Z.Decrypt(itemN.getFilename4(), AdminActivity.this));
                    } else {
                        i1.putExtra("file4", itemN.getFilename4());
                    }

                    if (itemN.getFilename5() != null) {
                        i1.putExtra("file5", Z.Decrypt(itemN.getFilename5(), AdminActivity.this));
                    } else {
                        i1.putExtra("file5", itemN.getFilename5());
                    }

                    i1.putExtra("uploadedby", itemN.getUploadedby());
                    i1.putExtra("uploadtime", itemN.getUploadtime());
                    i1.putExtra("lastmodified", itemN.getLastmodified());

                    Log.d("addOnItemTouch", "id: " + itemN.getId());
                    Log.d("addOnItemTouch", "title" + itemN.getTitle());
                    Log.d("addOnItemTouch", "notification" + itemN.getNotification());
                    Log.d("addOnItemTouch", "file1" + itemN.getFilename1());
                    Log.d("addOnItemTouch", "file2" + itemN.getFilename2());
                    Log.d("addOnItemTouch", "file3" + itemN.getFilename3());
                    Log.d("addOnItemTouch", "file4" + itemN.getFilename4());
                    Log.d("addOnItemTouch", "file5" + itemN.getFilename5());
                    Log.d("addOnItemTouch", "uploadedby" + itemN.getUploadedby());
                    Log.d("addOnItemTouch", "uploadtime" + itemN.getUploadtime());
                    Log.d("addOnItemTouch", "uploadedby" + itemN.getUploadedby());
                    Log.d("addOnItemTouch", "lastmodified" + itemN.getLastmodified());
                    startActivity(i1);


                } catch (Exception e) {

                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        recyclerViewNotification.setOnScrollListener(new EndlessRecyclerOnScrollListenerNotification(linearLayoutManagerNotification) {
            @Override
            public void onLoadMore(int current_page) {
                Log.d("TAG", "HERE BEFORE");
                if (total_no_of_notifications > 20) {
                    Log.d("TAG", "HERE INSIDE");

                    simulateLoadingNotification();
                }

            }
        });
// placement work

        recyclerViewPlacement.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewPlacement, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                RecyclerItemPlacement item = null;
                if (searchPlacementFlag == 1)
                    item = tempListPlacement.get(position);
                else
                    item = itemListPlacementnew.get(position);


                if (!item.isIsread()) {
                    item.setIsread(true);
                    unreadcountPlacement--;
                    placementcountrl.setVisibility(View.VISIBLE);
                    placementcounttxt.setText(unreadcountPlacement + "");
                    if (unreadcountPlacement == 0) {
                        placementcountrl.setVisibility(View.GONE);
                    }

                }

                mAdapterPlacement.notifyDataSetChanged();

                changeReadStatusPlacement(item.getId());

                crop_flag = 1;

                Intent i1 = new Intent(AdminActivity.this, ViewPlacement.class);

                i1.putExtra("ActivityFrom", "AdminActivity");
                i1.putExtra("id", item.getId());
                i1.putExtra("companyname", item.getCompanyname());
                i1.putExtra("package", item.getCpackage());
                i1.putExtra("post", item.getPost());
                i1.putExtra("forwhichcourse", item.getForwhichcourse());
                i1.putExtra("forwhichstream", item.getForwhichstream());
                i1.putExtra("vacancies", item.getVacancies());
                i1.putExtra("lastdateofregistration", item.getLastdateofregistration());
                i1.putExtra("dateofarrival", item.getDateofarrival());
                i1.putExtra("bond", item.getBond());
                i1.putExtra("noofapti", item.getNoofapti());
                i1.putExtra("nooftechtest", item.getNooftechtest());
                i1.putExtra("noofgd", item.getNoofgd());
                i1.putExtra("noofti", item.getNoofti());
                i1.putExtra("noofhri", item.getNoofhri());
                i1.putExtra("stdx", item.getStdx());
                i1.putExtra("stdxiiordiploma", item.getStdxiiordiploma());
                i1.putExtra("ug", item.getUg());
                i1.putExtra("pg", item.getPg());
                i1.putExtra("uploadtime", item.getUploadtime());
                i1.putExtra("lastmodified", item.getLastmodified());
                i1.putExtra("uploadedby", item.getUploadedby());
                i1.putExtra("noofallowedliveatkt", item.getNoofalloweddeadatkt());
                startActivity(i1);
            }

            @Override
            public void onLongClick(View view, int position) {

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

//
        disableNavigationViewScrollbars(navigationView);

        tswipe_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (selectedMenuFlag == 1) {
//                    getNotifications();
                    getNotifications2();
                } else if (selectedMenuFlag == 2) {
                    getPlacements2();
                }

            }
        });

        Log.d(TAG, "onCreate: " + getIntent().getStringExtra("push"));

        if (getIntent().getStringExtra("push") != null) {
//for clearing firebase service static fields
            Log.d("OnCreate", "trying to get String data");
            MyFirebaseMessagingService.countfornotiff = 0;
            MyFirebaseMessagingService.notiffbigtext = "";
            MyFirebaseMessagingService.notiffbigtext2 = "";
            MyFirebaseMessagingService.companynameslist.clear();
            MyFirebaseMessagingService.packagelists.clear();
            MyFirebaseMessagingService.postlists.clear();
            MyFirebaseMessagingService.ldrlists.clear();
            MyFirebaseMessagingService.vacantlist.clear();
            Log.d("serviceFields", "notificationtitlelist:" + MyFirebaseMessagingService.notificationcontentlist.size());
            MyFirebaseMessagingService.notificationtitlelist.clear();
            MyFirebaseMessagingService.notificationcontentlist.clear();
            Log.d("serviceFields", "notificationtitlelist2:" + MyFirebaseMessagingService.notificationcontentlist.size());

        }


        tswipe_refresh_layout.setVisibility(View.VISIBLE);
        tswipe_refresh_layout.setRefreshing(true);
        getNotifications2();
        RefreshPlacementCount();
        new UpdateFirebaseToken().execute();
        new GetUnreadMessagesCount().execute();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            MySharedPreferencesManager.save(AdminActivity.this, "uid", user.getUid());
            new CreateFirebaseUser(username, pass, user.getUid()).execute();
        }

    }

    class CreateFirebaseUser extends AsyncTask<String, String, String> {

        String u, p, d;
        String resultofop;

        CreateFirebaseUser(String u, String p, String d) {
            this.u = u;
            this.p = p;
            this.d = d;
        }

        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", u));
            params.add(new BasicNameValuePair("p", p));
            params.add(new BasicNameValuePair("t", new SharedPrefUtil(getApplicationContext()).getString("firebaseToken"))); //5
            params.add(new BasicNameValuePair("d", d));
            json = jParser.makeHttpRequest(Z.url_create_firebase, "GET", params);

            Log.d("TAG", "create firebase json: " + json);
            try {
                resultofop = json.getString("info");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultofop;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }

    class GetUnreadMessagesCount extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            json = jParser.makeHttpRequest(Z.url_get_chatrooms, "GET", params);

            try {

                count = Integer.parseInt(json.getString("count"));
                sender_uid = json.getString("uid");

                reciever_username = new String[count];
                reciever_uid = new String[count];
                unread_count = new String[count];

                for (int i = 0; i < count; i++) {
                    unread_count[i] = "0";
                    reciever_username[i] = json.getString("username" + i);
                    reciever_uid[i] = json.getString("uid" + i);
                }

            } catch (Exception ex) {

            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if (count > 0) {

                for (int i = 0; i < count; i++) {

                    String tempusername = null;
                    try {
                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] usernameBytes = reciever_username[i].getBytes("UTF-8");

                        byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                        tempusername = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));


                    } catch (Exception e) {
                    }

                    if (reciever_uid != null)
                        new GetMessagesReadStatus(username, tempusername, sender_uid, reciever_uid[i], i).execute();
                }

            }


        }
    }

    class GetMessagesReadStatus extends AsyncTask<String, String, String> {

        String sender, reciever, senderuid, recieveruid;
        int index;

        GetMessagesReadStatus(String sender, String reciever, String senderuid, String recieveruid, int index) {
            this.sender = sender;
            this.reciever = reciever;
            this.senderuid = senderuid;
            this.recieveruid = recieveruid;
            this.index = index;
        }

        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("s", sender));       //0
            params.add(new BasicNameValuePair("r", reciever));     //1
            params.add(new BasicNameValuePair("su", senderuid));    //2
            params.add(new BasicNameValuePair("ru", recieveruid));  //3

            try {

                json = jParser.makeHttpRequest(Z.url_getmessagesreadstatus, "GET", params);
                unread_count[index] = json.getString("unreadcount");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            unreadMessageCount = 0;
            if (index == count - 1) {

                if (unread_count != null)
                    for (int i = 0; i < count; i++) {
                        try {
                            unreadMessageCount += Integer.parseInt(unread_count[i]);
                        } catch (Exception e) {
                        }
                    }
                messagecountrl.setVisibility(View.VISIBLE);
                messagecount.setText(unreadMessageCount + "");
                if (unreadMessageCount == 0) {
                    messagecountrl.setVisibility(View.GONE);
                }
            }
        }
    }

    void filterNotifications(String text) {
        tempListNotification = new ArrayList();
        for (RecyclerItemEdit d : itemListNotificationNew) {

            if (containsIgnoreCase(d.getTitle(), text)) {
                tempListNotification.add(d);
            }
        }
        mAdapterNotificationEdit.updateList(tempListNotification, text);
    }

    void changeReadStatusNotification(String id) {
        new ChangeReadStatusNotification().execute(id);

    }


    @Override
    public void onImagesChosen(List<ChosenImage> list) {
        final ChosenImage file = list.get(0);

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (file != null) {

                    finalPath = file.getOriginalPath().toString();
                }
            }
        });
    }

    @Override
    public void onError(String s) {
        crop_layout.setVisibility(View.GONE);
        tswipe_refresh_layout.setVisibility(View.GONE);
        mainfragment.setVisibility(View.VISIBLE);
        Toast.makeText(AdminActivity.this, "Try Again !", Toast.LENGTH_SHORT).show();

    }

    public void requestProfileImage() {

        new Getsingnature().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class Getsingnature extends AsyncTask<String, String, String> {
        String signature = "";

        protected String doInBackground(String... param) {
            JSONParser jParser = new JSONParser();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            JSONObject json = jParser.makeHttpRequest(Z.load_last_updated, "GET", params);
            Log.d("TAG", "doInBackground: Getsingnature json " + json);
            try {
                signature = json.getString("lastupdated");
            } catch (Exception ex) {
            }
            return signature;
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d("TAG", "downloadImage signature : " + signature);
            Log.d("TAG", "downloadImage: GetImage username " + username);
            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority(Z.VPS_IP)
                    .path("AESTest/GetImage")
                    .appendQueryParameter("u", username)
                    .build();
            try {

                Glide.with(ShouldAnimateProfile.AdminActivity)
                        .load(uri)
                        .crossFade()
                        .signature(new StringSignature(signature))
                        .into(profile);

            } catch (Exception e) {
                Log.d("TAG", "onPostExecute: glide exception - " + e.getMessage());
            }

        }
    }

    public void requestCropImage() {
        resultView.setImageDrawable(null);

        MySharedPreferencesManager.save(AdminActivity.this, "crop", "yes");
        chooseImage();

    }

    private void chooseImage() {

        imagePicker.pickImage();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        Log.d(TAG, "onActivityResult: called "+resultCode);

        if (resultCode == ADMIN_DATA_CHANGE_RESULT_CODE) {
            AdminProfileFragment fragment = (AdminProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
            fragment.refreshContent();
        } else if (requestCode == Picker.PICK_IMAGE_DEVICE) {

            try {
                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback(this);
                }
                imagePicker.submit(result);
                crop_layout.setVisibility(View.VISIBLE);
                tswipe_refresh_layout.setVisibility(View.GONE);
                mainfragment.setVisibility(View.GONE);
                crop_flag = 1;
                beginCrop(result.getData());
                // Toast.makeText(this, "crop initiated", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                crop_layout.setVisibility(View.GONE);
                tswipe_refresh_layout.setVisibility(View.GONE);
                tswipe_refresh_layout.setVisibility(View.GONE);
                mainfragment.setVisibility(View.VISIBLE);
//                 Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();

            }
        } else if (resultCode == RESULT_CANCELED) {
            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            crop_flag = 0;
        } else if (requestCode == Crop.REQUEST_CROP) {

            // Toast.makeText(this, "cropped", Toast.LENGTH_SHORT).show();
            handleCrop(resultCode, result);
        }

        else if (resultCode == ADMIN_CREATE_DATA_CHANGE_RESULT_CODE) {

            tswipe_refresh_layout.setVisibility(View.VISIBLE);
            Log.d(TAG, "onActivityResult: called notification");
//             Toast.makeText(this, "NOTIFICATION", Toast.LENGTH_SHORT).show();
             getNotifications2();
                getPlacements2();

//            handleCrop(resultCode, result);
        }

    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            File f = new File(getCacheDir(), "cropped");
            filepath = f.getAbsolutePath();

            filename = "";
            int index = filepath.lastIndexOf("/");
            directory = "";
            for (int i = 0; i < index; i++)
                directory += filepath.charAt(i);

            for (int i = index + 1; i < filepath.length(); i++)
                filename += filepath.charAt(i);

            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            AdminProfileFragment fragment = (AdminProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
            fragment.showUpdateProgress();

            new CompressTask().execute();
//            new UploadProfile().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (resultCode == Crop.RESULT_ERROR) {
            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Try Again..!", Toast.LENGTH_SHORT).show();

        }
    }

    public void setCropFlag(int v) {
        crop_flag = v;
    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    @Override
    public void onBackPressed() {

        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                //startService(new Intent(getBaseContext(), MyService.class).putExtra("username",username).putExtra("usertype","student"));
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
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

        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter("pushNotificationChat"));
        setUserCount();
        Log.d("TAG", "onResume: ");

    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    void changeReadStatusPlacement(String id) {
        new ChangeReadStatusPlacement().execute(id);

    }


    void filterPlacements(String text) {
        tempListPlacement = new ArrayList();
        for (RecyclerItemPlacement d : itemListPlacementnew) {

            if (containsIgnoreCase(d.getCompanyname(), text)) {
                tempListPlacement.add(d);
            }
        }
        mAdapterPlacement.updateList(tempListPlacement, text);
    }

    void getNotifications2() {
        Log.d(TAG, "doing foreplay");
        previousTotalNotification = 0;
        loadingNotification = true;
        page_to_call_notification = 1;
        isFirstRunNotification = true;
        isLastPageLoadedNotification = false;
        lastPageFlagNotification = 0;
        GetNotificationsReadStatus();
    }

    void getPlacements2() {
        Log.d(TAG, "Doing foreplay with ranveer");
        previousTotalPlacement = 0;
        loadingPlacement = true;
        page_to_call_placement = 1;
        isFirstRunPlacement = true;
        isLastPageLoadedPlacement = false;
        lastPageFlagPlacement = 0;
        GetPlacementsReadStatus();
    }

    void setserverlisttoadapter(ArrayList<RecyclerItemEdit> itemlist) {

        itemListNotificationNew.addAll(itemlist);
        Log.d(TAG, "New MovieList To release:" + itemListNotificationNew.size());
        if (lastPageFlagNotification == 1)
            isLastPageLoadedNotification = true;


        recyclerViewNotification.getRecycledViewPool().clear();
        mAdapterNotificationEdit.notifyDataSetChanged();

        tswipe_refresh_layout.setVisibility(View.VISIBLE);
        tswipe_refresh_layout.setRefreshing(false);
        Log.d(TAG, "After release collection " + mAdapterNotificationEdit.getItemCount());


    }

    public void refreshUserCount() {

        new GetCountOfUsersUnderAdmin().execute();
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();

//        if (navMenuFlag == 2) {
//
//            getNotifications();
//        } else if (navMenuFlag == 3) {
//            getPlacements();
//        }

//    }

    public void setUserCount() {
        bluePanelTv.setText(Z.CountOfUsersUnderAdmin + Z.users_under_your_supervision);
        Log.d("TAG", "setUserCount: " + Z.CountOfUsersUnderAdmin);
    }


    class ChangeReadStatusNotification extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("id", param[0]));       //0
            json = jParser.makeHttpRequest(Z.url_changenotificationsreadstatus, "GET", params);
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

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

    public class GetProfileImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            map = downloadImage(Z.load_student_image);
            return map;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            profile.setImageBitmap(result);
        }

        private Bitmap downloadImage(String url) {
            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority(Z.VPS_IP)
                    .path("AESTest/GetImage")
                    .appendQueryParameter("u", username)
                    .build();

            url = uri.toString();

            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }

    }

    class CompressTask extends AsyncTask<String, String, Boolean> {
        protected Boolean doInBackground(String... param) {
            File sourceFile = new File(filepath);
            try {
                Log.d("TAG", "Before compress :   " + sourceFile.length() / 1024 + " kb");
            } catch (Exception e) {
            }
            Luban.compress(AdminActivity.this, sourceFile)
                    .setMaxSize(256)                // limit the final image size（unit：Kb）
                    .putGear(Luban.CUSTOM_GEAR)
                    .launch(new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(File file) {
                            try {
                                Log.d("TAG", "After compress :   " + file.length() / 1024 + " kb");
                            } catch (Exception e) {
                            }
                            if (file.exists()) {
                                String filepath = file.getAbsolutePath();
                                String filename = "";
                                int index = filepath.lastIndexOf("/");
                                directory = "";
                                for (int i = 0; i < index; i++)
                                    directory += filepath.charAt(i);
                                for (int i = index + 1; i < filepath.length(); i++)
                                    filename += filepath.charAt(i);

                                Log.d("TAG", "before : f name- " + filename);
                                Imgfile = file;

                                new UploadProfile().execute();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("TAG", "onError: " + e.getMessage());
                        }
                    });
            return true;
        }
    }

    class UploadProfile extends AsyncTask<String, String, Boolean> {


        protected Boolean doInBackground(String... param) {
            if (Imgfile != null) {
                try {
                    File sourceFile = new File(filepath);

                    MultipartUtility multipart = new MultipartUtility(Z.upload_profile, "UTF-8");
                    Log.d("TAG", "doInBackground: input username " + username);
                    multipart.addFormField("u", username);

                    if (filename != "") {
                        multipart.addFormField("f", filename);
                        multipart.addFilePart("uf", Imgfile);
                    } else
                        multipart.addFormField("f", "null");
                    response = multipart.finish();


                } catch (Exception ex) {

                }
            } else
                return false;

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            AdminProfileFragment fragment = (AdminProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
            if (result) {
                if (response != null && response.get(0).contains("success")) {
                    MySharedPreferencesManager.save(AdminActivity.this, "crop", "no");
                    Toast.makeText(AdminActivity.this, "Successfully Updated..!", Toast.LENGTH_SHORT).show();
                    requestProfileImage();

                    fragment.downloadImage();
                    DeleteRecursive(new File(directory));
                } else if (response != null && response.get(0).contains("null")) {
                    requestProfileImage();
                    fragment.refreshContent();
                    Toast.makeText(AdminActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminActivity.this, Z.FAIL_TO_UPLOAD_IMAGE, Toast.LENGTH_SHORT).show();
                    fragment.hideUpdateProgress();
                }
            } else {
                Toast.makeText(AdminActivity.this, Z.FAIL_TO_UPLOAD_IMAGE, Toast.LENGTH_SHORT).show();
                fragment.hideUpdateProgress();
            }
        }

        void DeleteRecursive(File fileOrDirectory) {

            if (fileOrDirectory.isDirectory())
                for (File child : fileOrDirectory.listFiles())
                    DeleteRecursive(child);

            fileOrDirectory.delete();

        }

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


    class ChangeReadStatusPlacement extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("id", param[0]));       //0

            json = jParser.makeHttpRequest(Z.url_ChangePlacementReadStatus, "GET", params);
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }

    class LoginFirebaseTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            String user = param[0];
            String hash = param[1];
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(user, hash)
                    .addOnCompleteListener(AdminActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("TAG", "successfully logged in to firebase");
                                MySharedPreferencesManager.save(AdminActivity.this, "fireLoginStatus", "Successfully logged in to Firebase");
                            } else {
                                Log.d("TAG", "failed to login to firebase");
                                MySharedPreferencesManager.save(AdminActivity.this, "fireLoginStatus", "Failed to login to Firebase");
                            }
                        }
                    });
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            String status = MySharedPreferencesManager.getData(AdminActivity.this, "fireLoginStatus");
//            Toast.makeText(AdminActivity.this, status, Toast.LENGTH_SHORT).show();
            // remove value from shared
            MySharedPreferencesManager.removeKey(AdminActivity.this, "fireLoginStatus");
        }
    }

    class isVerified extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            String username = MySharedPreferencesManager.getUsername(AdminActivity.this);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            JSONObject json = jParser.makeHttpRequest(Z.url_IsPlacemeVerified, "GET", params);
            try {
                String result = json.getString("info");
                Log.d("TAG", "isVerified json : " + json);
                Log.d("TAG", "isVerified save: " + Z.Decrypt(result, AdminActivity.this));
                if (result != null) {
                    MySharedPreferencesManager.save(AdminActivity.this, "placemeverify", result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class GetCountOfUsersUnderAdmin extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            String r = null;
            String username = MySharedPreferencesManager.getUsername(AdminActivity.this);
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

            if (result == null) {
                bluePanelTv.setText(Z.users_under_your_supervision);
            } else if (result.equals("0")) {
                bluePanelTv.setText("0" + Z.users_under_your_supervision);
            } else {
                Z.CountOfUsersUnderAdmin = result;
                bluePanelTv.setText(Z.CountOfUsersUnderAdmin + Z.users_under_your_supervision);
            }
        }
    }


    private void setplacementListtoadapter(ArrayList<RecyclerItemPlacement> itemList2) {

        itemListPlacementnew.addAll(itemList2);
        Log.d(TAG, "New MovieList with ranveer  To release:" + itemListPlacementnew.size());

        if (lastPageFlagPlacement == 1)
            isLastPageLoadedPlacement = true;

        recyclerViewPlacement.getRecycledViewPool().clear();

        mAdapterPlacement.notifyDataSetChanged();
        tswipe_refresh_layout.setVisibility(View.VISIBLE);
        tswipe_refresh_layout.setRefreshing(false);

        Log.d(TAG, "After release  with ranveer collection " + mAdapterPlacement.getItemCount());


    }

    class UpdateFirebaseToken extends AsyncTask<String, String, String> {

        JSONObject json;
        JSONParser jParser = new JSONParser();
        String resultofop = null;

        protected String doInBackground(String... param) {
            try {

                String encUsername = MySharedPreferencesManager.getUsername(getApplicationContext());
                String token = new SharedPrefUtil(getApplicationContext()).getString("firebaseToken");
                Log.d("TAG", "splashScreen token\n" + token);

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", encUsername));       //0
                params.add(new BasicNameValuePair("t", token));             //1
                json = jParser.makeHttpRequest(Z.url_UpdateFirebaseToken, "GET", params);
                Log.d("TAG", "token json admin: " + json);

                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
//            if (resultofop.equals("success")) {
//                Log.d("TAG_FIRE_IDService", "Successfully Updated token..!");
//            }
        }
    }

    public void updateUnreadMessageCount(int readCount) {
        unreadMessageCount -= readCount;
        messagecountrl.setVisibility(View.VISIBLE);
        messagecount.setText(unreadMessageCount + "");
        if (unreadMessageCount <= 0) {
            messagecountrl.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if(  selectedMenuFlag ==1){
            tswipe_refresh_layout.setVisibility(View.VISIBLE);
            getNotifications2();

        }else if(selectedMenuFlag ==2 ){
            tswipe_refresh_layout.setVisibility(View.VISIBLE);
            getPlacements2();
        }
    }


    private void GetNotificationsReadStatus() {
//        AndroidNetworking.get(Z.url_GetNotificationsAdminAdminMetaData)
        AndroidNetworking.post(Z.url_GetNotificationsAdminAdminMetaData)
                .setTag(this)
                .addQueryParameter("u", username)
                .setPriority(Priority.HIGH)
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
                            notificationpages = Integer.parseInt(response.getString("pages"));
                            called_pages_notification = new int[notificationpages];
                            total_no_of_notifications = Integer.parseInt(response.getString("count"));
                            unreadcountNotification = Integer.parseInt(response.getString("unreadcount"));
                            Log.d(TAG, "projects :" + notificationpages);
                            Log.d(TAG, "total Movies:" + total_no_of_notifications);
                            Log.d(TAG, "Upcoming Movies to release:" + unreadcountNotification);


                            notificationcountrl.setVisibility(View.VISIBLE);
                            notificationcounttxt.setText(unreadcountNotification + "");
                            if (unreadcountNotification == 0) {
                                notificationcountrl.setVisibility(View.GONE);
                            }
                            GetNotifications2();
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


    private void GetNotifications2() {
        Log.d(TAG, "getCurrentConnectionQuality : " + AndroidNetworking.getCurrentConnectionQuality() + " currentBandwidth : " + AndroidNetworking.getCurrentBandwidth());
        AndroidNetworking.post(Z.url_GetNotificationsAdmin)
                .setTag(this)
                .addQueryParameter("u", username)
                .addQueryParameter("p", page_to_call_notification + "")

                .setPriority(Priority.HIGH)
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

                            itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(response.getString("jsonparamsList"), MySharedPreferencesManager.getDigest1(AdminActivity.this), MySharedPreferencesManager.getDigest2(AdminActivity.this));
                            Log.d(TAG, " Movies from Hollywood" + itemlistfromserver.size());
                            Log.d(TAG, " Hollywood movie trailer 1" + itemlistfromserver.get(0).getNotification());
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

    private void GetPlacementsReadStatus() {
//        AndroidNetworking.get(Z.url_GetNotificationsAdminAdminMetaData)
        AndroidNetworking.post(Z.url_GetPlacementsAdminAdminMetaData)
                .setTag(this)
                .addQueryParameter("u", username)
                .setPriority(Priority.HIGH)
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
                            placementpages = Integer.parseInt(response.getString("pages"));
                            called_pages_placement = new int[placementpages];
                            total_no_of_placements = Integer.parseInt(response.getString("count"));
                            unreadcountPlacement = Integer.parseInt(response.getString("unreadcount"));
                            Log.d(TAG, "with Ranveer projects :" + placementpages);
                            Log.d(TAG, "with Ranveer total Movies:" + total_no_of_placements);
                            Log.d(TAG, "with Ranveer Movies to release:" + unreadcountPlacement);

                            placementcountrl.setVisibility(View.VISIBLE);
                            placementcounttxt.setText(unreadcountPlacement + "");
                            if (unreadcountPlacement == 0) {
                                placementcountrl.setVisibility(View.GONE);
                            }
                            GetPlacements2();

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

    private void GetPlacements2() {
        Log.d(TAG, "getCurrentConnectionQuality : " + AndroidNetworking.getCurrentConnectionQuality() + " currentBandwidth : " + AndroidNetworking.getCurrentBandwidth());

        AndroidNetworking.post(Z.url_GetPlacementsAdmin)
                .setTag(this)
                .addQueryParameter("u", username)
                .addQueryParameter("p", page_to_call_placement + "")

                .setPriority(Priority.HIGH)
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

                            try {
                                placementListfromserver = (ArrayList<RecyclerItemPlacement>) fromString(response.getString("placementlistfromserver"), MySharedPreferencesManager.getDigest1(AdminActivity.this), MySharedPreferencesManager.getDigest2(AdminActivity.this));
                                Log.d(TAG, "with ranveer Movies from Hollywood" + placementListfromserver.size());
                                Log.d(TAG, "with ranveer Hollywood movie trailer 1" + placementListfromserver.get(0).getCompanyname());

                            } catch (Exception e) {
                            }
                            itemListPlacementnew.clear();
                            setplacementListtoadapter(placementListfromserver);
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
    }


    private void simulateLoadingNotification() {
        tswipe_refresh_layout.setRefreshing(true);

        Log.d(TAG, "Movies to release from reports:" + page_to_call_notification);
        Log.d(TAG, "projects :" + notificationpages);
        if (page_to_call_notification < notificationpages)
            page_to_call_notification++;

        if (page_to_call_notification != notificationpages) {

            AndroidNetworking.post(Z.url_GetNotificationsAdmin)
                    .setTag(this)
                    .addQueryParameter("u", username)
                    .addQueryParameter("p", page_to_call_notification + "")

                    .setPriority(Priority.HIGH)
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

                                itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(response.getString("jsonparamsList"), MySharedPreferencesManager.getDigest1(AdminActivity.this), MySharedPreferencesManager.getDigest2(AdminActivity.this));
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

                AndroidNetworking.post(Z.url_GetNotificationsAdmin)
                        .setTag(this)
                        .addQueryParameter("u", username)
                        .addQueryParameter("p", page_to_call_notification + "")

                        .setPriority(Priority.HIGH)
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

                                    itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(response.getString("jsonparamsList"), MySharedPreferencesManager.getDigest1(AdminActivity.this), MySharedPreferencesManager.getDigest2(AdminActivity.this));
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
            }else{
                tswipe_refresh_layout.setRefreshing(false);

            }

        }


    }

    private void RefreshNotificationCount() {
//        AndroidNetworking.get(Z.url_GetNotificationsAdminAdminMetaData)
        AndroidNetworking.post(Z.url_GetNotificationsAdminAdminMetaData)
                .setTag(this)
                .addQueryParameter("u", username)
                .setPriority(Priority.HIGH)
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
                            notificationpages = Integer.parseInt(response.getString("pages"));
                            called_pages_notification = new int[notificationpages];
                            total_no_of_notifications = Integer.parseInt(response.getString("count"));
                            unreadcountNotification = Integer.parseInt(response.getString("unreadcount"));
                            Log.d(TAG, "projects :" + notificationpages);
                            Log.d(TAG, "total Movies:" + total_no_of_notifications);
                            Log.d(TAG, "Upcoming Movies to release:" + unreadcountNotification);


                            notificationcountrl.setVisibility(View.VISIBLE);
                            notificationcounttxt.setText(unreadcountNotification + "");
                            if (unreadcountNotification == 0) {
                                notificationcountrl.setVisibility(View.GONE);
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

    private void RefreshPlacementCount() {
//        AndroidNetworking.get(Z.url_GetNotificationsAdminAdminMetaData)
        AndroidNetworking.post(Z.url_GetPlacementsAdminAdminMetaData)
                .setTag(this)
                .addQueryParameter("u", username)
                .setPriority(Priority.HIGH)
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
                            placementpages = Integer.parseInt(response.getString("pages"));
                            called_pages_placement = new int[placementpages];
                            total_no_of_placements = Integer.parseInt(response.getString("count"));
                            unreadcountPlacement = Integer.parseInt(response.getString("unreadcount"));
                            Log.d(TAG, "with Ranveer projects :" + placementpages);
                            Log.d(TAG, "with Ranveer total Movies:" + total_no_of_placements);
                            Log.d(TAG, "with Ranveer Movies to release:" + unreadcountPlacement);

                            placementcountrl.setVisibility(View.VISIBLE);
                            placementcounttxt.setText(unreadcountPlacement + "");
                            if (unreadcountPlacement == 0) {
                                placementcountrl.setVisibility(View.GONE);
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
    private void simulateLoadingPlacement() {
        tswipe_refresh_layout.setRefreshing(true);

        Log.d(TAG, "Movies to release with ranveer from reports:" + page_to_call_placement);
        Log.d(TAG, "projects with ranveer :" + placementpages);
        if (page_to_call_placement < placementpages)
            page_to_call_placement++;

        if (page_to_call_placement != placementpages) {

            AndroidNetworking.post(Z.url_GetPlacementsAdmin)
                    .setTag(this)
                    .addQueryParameter("u", username)
                    .addQueryParameter("p", page_to_call_placement + "")
                    .setPriority(Priority.HIGH)
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

                                itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(response.getString("jsonparamsList"), MySharedPreferencesManager.getDigest1(AdminActivity.this), MySharedPreferencesManager.getDigest2(AdminActivity.this));
                                Log.d(TAG, " Movies from Hollywood" + itemlistfromserver.size());
                                Log.d(TAG, " Hollywood movie trailer 1" + itemlistfromserver.get(0).getNotification());

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
            if (!isLastPageLoadedPlacement) {
                lastPageFlagPlacement = 1;

                AndroidNetworking.post(Z.url_GetPlacementsAdmin)
                        .setTag(this)
                        .addQueryParameter("u", username)
                        .addQueryParameter("p", page_to_call_placement + "")
                        .setPriority(Priority.HIGH)
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

                                    itemlistfromserver = (ArrayList<RecyclerItemEdit>) fromString(response.getString("jsonparamsList"), MySharedPreferencesManager.getDigest1(AdminActivity.this), MySharedPreferencesManager.getDigest2(AdminActivity.this));
                                    Log.d(TAG, " Movies from Hollywood" + itemlistfromserver.size());
                                    Log.d(TAG, " Hollywood movie trailer 1" + itemlistfromserver.get(0).getNotification());

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
                                    Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                                    Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                                    Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                                } else {
                                    // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                    Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                                }
                            }
                        });
            }else{
                tswipe_refresh_layout.setRefreshing(false);

            }

        }


    }


}
