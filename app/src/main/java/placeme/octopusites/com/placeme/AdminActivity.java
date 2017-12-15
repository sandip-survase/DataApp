package placeme.octopusites.com.placeme;

import android.content.BroadcastReceiver;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.soundcloud.android.crop.Crop;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
import static placeme.octopusites.com.placeme.LoginActivity.md5;

public class AdminActivity extends AppCompatActivity implements ImagePickerCallback {


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final int ADMIN_DATA_CHANGE_RESULT_CODE =111;

    private static String url = "http://192.168.100.100/AESTest/GetImage";
    private static String upload_profile = "http://192.168.100.100/AESTest/UploadProfile";
    private static String load_student_image = "http://192.168.100.100/AESTest/GetImage";

    //placement urls
    private static String url_getplacementsmetadata = "http://192.168.100.100:8080/CreateNotificationTemp/GetPlacementsAdminMetaData";
    private static String url_getplacementsreadstatus = "http://192.168.100.100:8080/CreateNotificationTemp/GetReadStatusOfPlacementsForAdmin";
    private static String url_getplacements = "http://192.168.100.100:8080/CreateNotificationTemp/GetPlacementsAdmin";
    private static String url_changeplacementsreadstatus = "http://192.168.100.100:8080/CreateNotificationTemp/ChangePlacementReadStatusAdmin";


    //notiffurl
    private static String url_getnotificationsmetadata = "http://192.168.100.100:8080/CreateNotificationTemp/GetNotificationsAdminMetaData";
    private static String url_getnotificationsreadstatus = "http://192.168.100.100:8080/CreateNotificationTemp/GetReadStatusOfNotificationsAdmin";
    private static String url_getnotifications = "http://192.168.100.100:8080/CreateNotificationTemp/GetNotificationsAdmin";
    private static String url_changenotificationsreadstatus = "http://192.168.100.100:8080/CreateNotificationTemp/ChangeNotificationReadStatusAdmin";

    public static String url_getlastupdated = "http://192.168.100.30/CreateNotificationTemp/GetLastUpdatedAdmin";


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
    List<RecyclerItem> tempListNotification;
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
    String[] blanksuggestionList = {""};
    List<RecyclerItemPlacement> tempListPlacement;
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
    private ImageView resultView;
    private int chooserType;
    private String mediaPath;
    private String finalPath;
    private String plainusername, username = "", fname = "", mname = "", sname = "";
    private String thumbPath;
    private MaterialSearchView searchView;
    //notification
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private List<RecyclerItem> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerItemAdapter mAdapter;
    private RecyclerView recyclerViewNotification, recyclerViewPlacement;
    private RecyclerItemAdapter mAdapterNotification;
    private List<RecyclerItem> itemListNotification = new ArrayList<>();
    private int previousTotalNotification = 0; // The total number of items in the dataset after the last load
    private boolean loadingNotification = true; // True if we are still waiting for the last set of data to load.
    private int visibleThresholdNotification = 0; // The minimum amount of items to have below your current scroll position before loading more.
    private int page_to_call_notification = 1;
    private int current_page_notification = 1;
    //placements workdeclair
    private RecyclerItemAdapterPlacement mAdapterPlacement;
    private List<RecyclerItemPlacement> itemListPlacement = new ArrayList<>();
    private boolean loadingPlacement = true; // True if we are still waiting for the last set of data to load.
    private int previousTotalPlacement = 0; // The total number of items in the dataset after the last load
    private int visibleThresholdPlacement = 0; // The minimum amount of items to have below your current scroll position before loading more.
    private int page_to_call_placement = 1;
    private int current_page_placement = 1;
    private TextView toolbar_title;
    Toolbar toolbar;
    TextView bluePanelTv;


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

        Log.d("MYTAG", "adminactivity called: ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title=(TextView)toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(MyConstants.getRighteous(AdminActivity.this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar_title.setText("Notifications");



        String encUsername=MySharedPreferencesManager.getUsername(this);
        Log.d("Shardpreff", "encUsername=======================: " + encUsername);
        //sss
        imagePicker = new ImagePicker(this);
        imagePicker.setImagePickerCallback(this);
        imagePicker.shouldGenerateMetadata(false); // Default is true
        imagePicker.shouldGenerateThumbnails(false); // Default is true


        crop_layout = (FrameLayout) findViewById(R.id.crop_layout);
        resultView = (ImageView) findViewById(R.id.result_image);
        tswipe_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);



        username = MySharedPreferencesManager.getUsername(this);
        String pass=MySharedPreferencesManager.getPassword(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        String role = MySharedPreferencesManager.getRole(this);


        MySharedPreferencesManager.save(AdminActivity.this,"intro","yes");
        MySharedPreferencesManager.save(AdminActivity.this,"otp","no");
        MySharedPreferencesManager.save(AdminActivity.this,"activationMessage","no");
        MySharedPreferencesManager.save(AdminActivity.this,"activatedCode","no");

        try {
            demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            demoIVBytes = SimpleBase64Encoder.decode(digest2);
            sPadding = "ISO10126Padding";

            String plainusername=Decrypt(username,digest1,digest2);

            byte[] demo2EncryptedBytes1=SimpleBase64Encoder.decode(pass);
            byte[] demo2DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo2EncryptedBytes1);
            String data=new String(demo2DecryptedBytes1);
            String hash=md5(data + MySharedPreferencesManager.getDigest3(this));

            new LoginFirebaseTask().execute(plainusername,hash);


        } catch (Exception e) {
        }

        bluePanelTv = (TextView) findViewById(R.id.bluePanelTv);
        refreshUserCount();

        recyclerViewNotification = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewPlacement = (RecyclerView) findViewById(R.id.recycler_view_placement);
//        if(notificationorplacementflag==1) {
//            recyclerViewNotification.setVisibility(View.VISIBLE);
//            recyclerViewPlacement.setVisibility(View.GONE);
//
//           }
//        else   if(notificationorplacementflag==2) {
//            recyclerViewNotification.setVisibility(View.GONE);
//            recyclerViewPlacement.setVisibility(View.VISIBLE);
//        }

        createnotificationrl = (RelativeLayout) findViewById(R.id.createnotificationrl);
        editnotificationrl = (RelativeLayout) findViewById(R.id.editnotificationrl);

        createnotificationrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notificationorplacementflag == 1) {
                    Intent i1 = new Intent(AdminActivity.this, CreateNotification.class);
                    i1.putExtra("flag", "fromAdminActivity");
                    startActivity(i1);

//                    startActivity(new Intent(AdminActivity.this,CreateNotification.class));


                } else if (notificationorplacementflag == 2) {
                    String Tag = "adminActivity";
                    settag.setActivityFromtag(Tag);
                    startActivity(new Intent(AdminActivity.this, CreatePlacement.class));

                }
            }
        });
        editnotificationrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notificationorplacementflag == 1) {
                    startActivity(new Intent(AdminActivity.this, EditNotification.class));
                } else if (notificationorplacementflag == 2) {

                    startActivity(new Intent(AdminActivity.this, EditPlacement.class));

                }
            }
        });
        createPlacementOrNotification = (TextView) findViewById(R.id.createnotificationtxt);
        editPlacementOrNotification = (TextView) findViewById(R.id.editnotificationtxt);


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

                RelativeLayout admincontrolsrl = (RelativeLayout) findViewById(R.id.admincontrolsrl);
                admincontrolsrl.setVisibility(View.GONE);

                if (selectedMenuFlag == 1) {
                    searchView.setSuggestions(blanksuggestionList);
                    searchView.setSuggestions(null);
                } else if (selectedMenuFlag == 2) {
                    searchView.setSuggestions(blanksuggestionList);
                    searchView.setSuggestions(null);
                }


                final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
                upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                searchView.setBackIcon(upArrow);


            }

            @Override
            public void onSearchViewClosed() {

                RelativeLayout admincontrolsrl = (RelativeLayout) findViewById(R.id.admincontrolsrl);
                admincontrolsrl.setVisibility(View.VISIBLE);

            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AdminActions.class));
            }
        });

        RelativeLayout bottompanel=(RelativeLayout)findViewById(R.id.bottompanel);
        bottompanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this,ShowUsers.class));
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

                    } else if (navMenuFlag == 2) {

                        notificationorplacementflag = 1;

                        crop_layout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Notifications");

                        mainfragment.setVisibility(View.GONE);
                        tswipe_refresh_layout.setVisibility(View.VISIBLE);
                        createPlacementOrNotification.setText("Create Notification");
                        editPlacementOrNotification.setText("Edit Notification");
                        recyclerViewNotification.setVisibility(View.VISIBLE);
                        recyclerViewPlacement.setVisibility(View.GONE);
//                      createPlacementOrNotification=(TextView)findViewById(R.id.createnotificationtxt) ;
//                      editPlacementOrNotification
                        getNotifications();
//                      recyclerViewNotification.setVisibility(View.VISIBLE);
//                      recyclerViewPlacement.setVisibility(View.GONE);

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
                        getPlacements();
//                     recyclerViewNotification.setVisibility(View.GONE);
//                     recyclerViewPlacement.setVisibility(View.VISIBLE);

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
                    } else if (navMenuFlag == 6) {
                        crop_layout.setVisibility(View.GONE);
                        NewsFeedFragment fragment = new NewsFeedFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Blog");
                        tswipe_refresh_layout.setVisibility(View.GONE);
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
        new GetProfileImage().execute();
        final ImageView profilei = (ImageView) hView.findViewById(R.id.profile);
        final ImageView notificationi = (ImageView) hView.findViewById(R.id.notification);
        final ImageView placementi = (ImageView) hView.findViewById(R.id.placement);
        final ImageView proi = (ImageView) hView.findViewById(R.id.pro);
        final ImageView settingsi = (ImageView) hView.findViewById(R.id.settings);
        final ImageView newsi = (ImageView) hView.findViewById(R.id.blog);
        final ImageView chati = (ImageView) hView.findViewById(R.id.chat);
//
        notificationcounttxt = (TextView) hView.findViewById(R.id.notificationcount);
        notificationcountrl = (RelativeLayout) hView.findViewById(R.id.notificationcountrl);
        placementcounttxt = (TextView) hView.findViewById(R.id.placementcount);
        placementcountrl = (RelativeLayout) hView.findViewById(R.id.placementcountrl);

        View v1 = (View) hView.findViewById(R.id.prifileselectionview);
        View v2 = (View) hView.findViewById(R.id.notificationselectionview);
        View v3 = (View) hView.findViewById(R.id.placementselectionview);
        View v4 = (View) hView.findViewById(R.id.proselectionview);
        View v5 = (View) hView.findViewById(R.id.settingselectionview);
        View v6 = (View) hView.findViewById(R.id.blogselectionview);
        View v7 = (View) hView.findViewById(R.id.abtselectionview);
        View v8 = (View) hView.findViewById(R.id.chatselectionview);

        mainfragment = (FrameLayout) findViewById(R.id.mainfragment);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navMenuFlag = 1;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon_selected);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(MyConstants.getBold(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.pro_icon);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt4.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));


                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTypeface(MyConstants.getLight(AdminActivity.this));
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

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon_selected);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(MyConstants.getBold(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.pro_icon);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt4.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTypeface(MyConstants.getLight(AdminActivity.this));
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

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon_selected);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(MyConstants.getBold(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.pro_icon);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt4.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTypeface(MyConstants.getLight(AdminActivity.this));
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

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon_selected);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTypeface(MyConstants.getBold(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.pro_icon);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt4.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crop_flag = 1;
                startActivity(new Intent(AdminActivity.this, ProSplashScreen.class));

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

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.pro_icon);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt4.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon_selected);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(MyConstants.getBold(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTypeface(MyConstants.getLight(AdminActivity.this));
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

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.pro_icon);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt4.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon_selected);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(MyConstants.getBold(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.sky_blue_color));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTypeface(MyConstants.getLight(AdminActivity.this));
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

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.messages_icon);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt8.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.pro_icon);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt4.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTypeface(MyConstants.getLight(AdminActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.sky_blue_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });

        Drawable myDrawable = getResources().getDrawable(R.drawable.notification_icon_selected);
        notificationi.setImageDrawable(myDrawable);

        TextView pt=(TextView)hView.findViewById(R.id.notificationtxt);
        pt.setTypeface(MyConstants.getBold(AdminActivity.this));
        pt.setTextColor(getResources().getColor(R.color.sky_blue_color));

        TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
        pt1.setTypeface(MyConstants.getLight(AdminActivity.this));
        pt1.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
        pt3.setTypeface(MyConstants.getLight(AdminActivity.this));
        pt3.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
        pt8.setTypeface(MyConstants.getLight(AdminActivity.this));
        pt8.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt4=(TextView)hView.findViewById(R.id.protxt);
        pt4.setTypeface(MyConstants.getLight(AdminActivity.this));
        pt4.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
        pt5.setTypeface(MyConstants.getLight(AdminActivity.this));
        pt5.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
        pt6.setTypeface(MyConstants.getLight(AdminActivity.this));
        pt6.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
        pt7.setTypeface(MyConstants.getLight(AdminActivity.this));
        pt7.setTextColor(getResources().getColor(R.color.while_color));


        mAdapterNotification = new RecyclerItemAdapter(itemListNotification);
        recyclerViewNotification.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerNotification = new LinearLayoutManager(this);
        recyclerViewNotification.setLayoutManager(linearLayoutManagerNotification);
        recyclerViewNotification.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNotification.setAdapter(mAdapterNotification);

        //placements

        mAdapterPlacement = new RecyclerItemAdapterPlacement(itemListPlacement);
        recyclerViewPlacement.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerPlacement = new LinearLayoutManager(this);
        recyclerViewPlacement.setLayoutManager(linearLayoutManagerPlacement);
        recyclerViewPlacement.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewPlacement.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlacement.setAdapter(mAdapterPlacement);


        recyclerViewNotification.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewNotification, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                RecyclerItem itemN = null;
                if (searchNotificationFlag == 1)
                    itemN = tempListNotification.get(position);
                else
                    itemN = itemListNotification.get(position);
                if (!itemN.getisRead()) {
                    itemN.setisRead(true);
                    unreadcountNotification--;
                    notificationcountrl.setVisibility(View.VISIBLE);
                    notificationcounttxt.setText(unreadcountNotification + "");
                    if (unreadcountNotification == 0) {
                        notificationcountrl.setVisibility(View.GONE);
                    }

                }

                mAdapterNotification.notifyDataSetChanged();

                changeReadStatusNotification(itemN.getId());

                crop_flag = 1;
                Intent i1 = new Intent(AdminActivity.this, ViewNotification.class);

                i1.putExtra("id", itemN.getId());
                i1.putExtra("title", itemN.getTitle());
                i1.putExtra("notification", itemN.getNotification());
                i1.putExtra("file1", itemN.getFilename1());
                i1.putExtra("file2", itemN.getFilename2());
                i1.putExtra("file3", itemN.getFilename3());
                i1.putExtra("file4", itemN.getFilename4());
                i1.putExtra("file5", itemN.getFilename5());
                i1.putExtra("uploadedby", itemN.getUploadedby());
                i1.putExtra("uploadtime", itemN.getUploadtime());
                i1.putExtra("lastmodified", itemN.getLastmodified());

                startActivity(i1);

            }

            @Override
            public void onLongClick(View view, int position) {

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
// placement work

        recyclerViewPlacement.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewPlacement, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                RecyclerItemPlacement item = null;
                if (searchPlacementFlag == 1)
                    item = tempListPlacement.get(position);
                else
                    item = itemListPlacement.get(position);


                if (!item.getisRead()) {
                    item.setisRead(true);
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
                i1.putExtra("noofallowedliveatkt", item.getNoofallowedliveatkt());
                i1.putExtra("noofalloweddeadatkt", item.getNoofalloweddeadatkt());
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


                if (selectedMenuFlag == 1)
                    getNotifications();
                else if (selectedMenuFlag == 2)
                    getPlacements();


            }
        });

        tswipe_refresh_layout.setRefreshing(true);

        new GetUnreadCountOfNotificationAndPlacement().execute();
//        new GetUnreadMessagesCount().execute();


    }

    void filterNotifications(String text) {
        tempListNotification = new ArrayList();
        for (RecyclerItem d : itemListNotification) {

            if (containsIgnoreCase(d.getTitle(), text)) {
                tempListNotification.add(d);
            }
        }
        mAdapterNotification.updateList(tempListNotification, text);
    }

    public void getNotifications() {
        tswipe_refresh_layout.setRefreshing(true);
        previousTotalNotification = 0;
        loadingNotification = true;
        page_to_call_notification = 1;
        isFirstRunNotification = true;
        isLastPageLoadedNotification = false;
        lastPageFlagNotification = 0;
        new GetNotificationsReadStatus().execute();
        Log.d("Ad.getNotifications", ":total_no_of_notifications::" + total_no_of_notifications);
        Log.d("Ad.getNotifications", ":notificationpages::" + notificationpages);
        Log.d("Ad.getNotifications", ": readcountNotification::" + unreadcountNotification);
    }

    void changeReadStatusNotification(String id) {
        new ChangeReadStatusNotification().execute(id);

    }

    private void simulateLoadingNotification() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                tswipe_refresh_layout.setRefreshing(true);
            }

            @Override
            protected Void doInBackground(Void... param) {
                try {


                    if (page_to_call_notification < notificationpages)
                        page_to_call_notification++;


                    if (page_to_call_notification != notificationpages) {
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("u", username));       //0
                        params.add(new BasicNameValuePair("p", page_to_call_notification + ""));
                        json = jParser.makeHttpRequest(url_getnotifications, "GET", params);

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
                            json = jParser.makeHttpRequest(url_getnotifications, "GET", params);

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


                tswipe_refresh_layout.setRefreshing(false);
                new GetLastUpdatedNotification().execute();
            }
        }.execute();
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
        Toast.makeText(AdminActivity.this, s, Toast.LENGTH_SHORT).show();

    }

    public void requestProfileImage() {
        new GetProfileImage().execute();
    }

    public void requestCropImage() {
        resultView.setImageDrawable(null);

        MySharedPreferencesManager.save(AdminActivity.this,"crop", "yes");
        chooseImage();

    }

    private void chooseImage() {


        imagePicker.pickImage();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        if (resultCode == ADMIN_DATA_CHANGE_RESULT_CODE) {
            Log.d("TAG", "onActivityResult: result code " + ADMIN_DATA_CHANGE_RESULT_CODE);

            username = MySharedPreferencesManager.getUsername(AdminActivity.this);
            try {
                plainusername = Decrypt(username, digest1, digest2);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (resultCode == 111) {
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
            new UploadProfile().execute();

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
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], AdminActivity.this, "placeme");

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], AdminActivity.this, "placeme");

                                            itemListNotification.add(item);
                                        }
                                    } else {
                                        if (notificationfilename1[i].equals("null")) {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, false, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, false, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);

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
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], AdminActivity.this, "cplaceme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], AdminActivity.this, "placeme");

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], AdminActivity.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], AdminActivity.this, "placeme");

                                            itemListNotification.add(item);
                                        }
                                    } else {
                                        if (notificationfilename1[i].equals("null")) {

                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], false, true, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationtoshow, notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow, notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i], notificationtitles[i], notificationnotifications[i], notificationnotifications[i], notificationfilename1[i], notificationfilename2[i], notificationfilename3[i], notificationfilename4[i], notificationfilename5[i], outputDate, notificationlastmodified[i], true, true, notificationuploadedby[i], AdminActivity.this, lastupdatedNotification[k]);

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

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter("pushNotification"));
        refreshUserCount();

    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    void getPlacements() {
        tswipe_refresh_layout.setRefreshing(true);
        previousTotalPlacement = 0;
        loadingPlacement = true;
        page_to_call_placement = 1;
        isFirstRunPlacement = true;
        isLastPageLoadedPlacement = false;
        lastPageFlagPlacement = 0;
        Log.d("PlacmentTesting", "previousTotalPlacement: " + previousTotalPlacement);
        Log.d("PlacmentTesting", "page_to_call_placement: " + page_to_call_placement);
        Log.d("PlacmentTesting", "lastPageFlagPlacement: " + lastPageFlagPlacement);
        new GetPlacementsReadStatus().execute();
    }

//    @Override
//    protected void onRestart() {
//
//        super.onRestart();
//        if(crop_flag==0)
//        {
//            AdminProfileFragment fragment = (AdminProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
////            fragment.refreshContent();
//        }
//    }

    void addPlacementdatatoAdapter() {
        if (isFirstRunPlacement) {
            itemListPlacement.clear();
            mAdapterPlacement.notifyDataSetChanged();
            isFirstRunPlacement = false;
        }
        selectedMenuFlag = 2;
        if (!isLastPageLoadedPlacement) {
            for (int i = 0; i < placementcount; i++) {

                String companynametoshow = "";
                int largecompanynameflag = 0;

                if (placementcompanyname[i].length() > 25) {
                    for (int j = 0; j < 20; j++)
                        companynametoshow += placementcompanyname[i].charAt(j);
                    largecompanynameflag = 1;
                    companynametoshow += "...";
                }

                RecyclerItemPlacement item = null;
                for (int j = 0; j < readstatuscountPlacement; j++) {
                    String idnstatus = placementreadstatus[j];
                    String sid = "";
                    if (idnstatus.contains("U")) {

                        for (int k = 0; k < idnstatus.length() - 1; k++) {
                            sid += idnstatus.charAt(k);
                        }
                        if (sid.equals(placementids[i])) {
                            for (int k = 0; k < uniqueUploadersPlacement.length; k++) {
                                if (placementuploadedbyplain[i].equals(uniqueUploadersPlacement[k])) {
                                    if (lastupdatedPlacement[k] == null) {
                                        if (largecompanynameflag == 1)
                                            item = new RecyclerItemPlacement(placementids[i], companynametoshow, placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], false, AdminActivity.this, "placeme", placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                                        else
                                            item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], false, AdminActivity.this, "placeme", placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                                        itemListPlacement.add(item);
                                    } else {
                                        if (largecompanynameflag == 1)
                                            item = new RecyclerItemPlacement(placementids[i], companynametoshow, placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], false, AdminActivity.this, lastupdatedPlacement[k], placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                                        else
                                            item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], false, AdminActivity.this, lastupdatedPlacement[k], placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                                        itemListPlacement.add(item);
                                    }
                                }

                            }
                        }
                    } else if (idnstatus.contains("R")) {
                        for (int k = 0; k < idnstatus.length() - 1; k++) {
                            sid += idnstatus.charAt(k);
                        }
                        if (sid.equals(placementids[i])) {
                            for (int k = 0; k < uniqueUploadersPlacement.length; k++) {
                                if (placementuploadedbyplain[i].equals(uniqueUploadersPlacement[k])) {
                                    if (lastupdatedPlacement[k] == null) {
                                        if (largecompanynameflag == 1)
                                            item = new RecyclerItemPlacement(placementids[i], companynametoshow, placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], true, AdminActivity.this, "placeme", placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                                        else
                                            item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], true, AdminActivity.this, "placeme", placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                                        itemListPlacement.add(item);
                                    } else {
                                        if (largecompanynameflag == 1)
                                            item = new RecyclerItemPlacement(placementids[i], companynametoshow, placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], true, AdminActivity.this, lastupdatedPlacement[k], placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                                        else
                                            item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i] + " LPA", placementpost[i], placementforwhichcourse[i], placementforwhichstream[i], placementvacancies[i], placementlastdateofregistration[i], placementdateofarrival[i], placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i], placementstdx[i], placementstdxiiordiploma[i], placementug[i], placementpg[i], placementuploadtime[i], placementlastmodified[i], placementuploadedby[i], true, AdminActivity.this, lastupdatedPlacement[k], placementnoofallowedliveatkt[i], placementnoofalloweddeadatkt[i]);
                                        itemListPlacement.add(item);
                                    }
                                }

                            }
                        }

                    }

                }

            }
        }

        if (lastPageFlagPlacement == 1)
            isLastPageLoadedPlacement = true;
        mAdapterPlacement.notifyDataSetChanged();

    }

    void changeReadStatusPlacement(String id) {
        new ChangeReadStatusPlacement().execute(id);

    }

    private void simulateLoadingPlacement() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                tswipe_refresh_layout.setRefreshing(true);
            }

            @Override
            protected Void doInBackground(Void... param) {

                try {
                    if (page_to_call_placement < placementpages)
                        page_to_call_placement++;

                    if (page_to_call_placement != placementpages) {

                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("u", username));
                        params.add(new BasicNameValuePair("p", page_to_call_placement + ""));
                        json = jParser.makeHttpRequest(url_getplacements, "GET", params);

                        placementcount = Integer.parseInt(json.getString("count"));
                        placementids = new String[placementcount];
                        placementcompanyname = new String[placementcount];
                        placementcpackage = new String[placementcount];
                        placementpost = new String[placementcount];
                        placementforwhichcourse = new String[placementcount];
                        placementforwhichstream = new String[placementcount];
                        placementvacancies = new String[placementcount];
                        placementlastdateofregistration = new String[placementcount];
                        placementdateofarrival = new String[placementcount];
                        placementbond = new String[placementcount];
                        placementnoofapti = new String[placementcount];
                        placementnooftechtest = new String[placementcount];
                        placementnoofgd = new String[placementcount];
                        placementnoofti = new String[placementcount];
                        placementnoofhri = new String[placementcount];
                        placementstdx = new String[placementcount];
                        placementstdxiiordiploma = new String[placementcount];
                        placementug = new String[placementcount];
                        placementpg = new String[placementcount];
                        placementuploadtime = new String[placementcount];
                        placementlastmodified = new String[placementcount];
                        placementuploadedby = new String[placementcount];
                        placementuploadedbyplain = new String[placementcount];
                        placementnoofallowedliveatkt = new String[placementcount];
                        placementnoofalloweddeadatkt = new String[placementcount];

                        for (int i = 0; i < placementcount; i++) {

                            placementids[i] = json.getString("id" + i);
                            placementcompanyname[i] = json.getString("companyname" + i);
                            placementcpackage[i] = json.getString("package" + i);
                            placementpost[i] = json.getString("post" + i);
                            placementforwhichcourse[i] = json.getString("forwhichcourse" + i);
                            placementforwhichstream[i] = json.getString("forwhichstream" + i);
                            placementvacancies[i] = json.getString("vacancies" + i);
                            placementlastdateofregistration[i] = json.getString("lastdateofregistration" + i);
                            placementdateofarrival[i] = json.getString("dateofarrival" + i);
                            placementbond[i] = json.getString("bond" + i);
                            placementnoofapti[i] = json.getString("noofapti" + i);
                            placementnooftechtest[i] = json.getString("nooftechtest" + i);
                            placementnoofgd[i] = json.getString("noofgd" + i);
                            placementnoofti[i] = json.getString("noofti" + i);
                            placementnoofhri[i] = json.getString("noofhri" + i);
                            placementstdx[i] = json.getString("stdx" + i);
                            placementstdxiiordiploma[i] = json.getString("stdxiiordiploma" + i);
                            placementug[i] = json.getString("ug" + i);
                            placementpg[i] = json.getString("pg" + i);
                            placementuploadtime[i] = json.getString("uploadtime" + i);
                            placementlastmodified[i] = json.getString("lastmodified" + i);
                            placementuploadedby[i] = json.getString("uploadedby" + i);
                            placementnoofallowedliveatkt[i] = json.getString("noofallowedliveatkt" + i);
                            placementnoofalloweddeadatkt[i] = json.getString("noofalloweddeadatkt" + i);
                        }

                    } else {
                        if (!isLastPageLoadedPlacement) {

                            lastPageFlagPlacement = 1;

                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("u", username));
                            params.add(new BasicNameValuePair("p", page_to_call_placement + ""));
                            json = jParser.makeHttpRequest(url_getplacements, "GET", params);

                            placementcount = Integer.parseInt(json.getString("count"));

                            placementids = new String[placementcount];
                            placementcompanyname = new String[placementcount];
                            placementcpackage = new String[placementcount];
                            placementpost = new String[placementcount];
                            placementforwhichcourse = new String[placementcount];
                            placementforwhichstream = new String[placementcount];
                            placementvacancies = new String[placementcount];
                            placementlastdateofregistration = new String[placementcount];
                            placementdateofarrival = new String[placementcount];
                            placementbond = new String[placementcount];
                            placementnoofapti = new String[placementcount];
                            placementnooftechtest = new String[placementcount];
                            placementnoofgd = new String[placementcount];
                            placementnoofti = new String[placementcount];
                            placementnoofhri = new String[placementcount];
                            placementstdx = new String[placementcount];
                            placementstdxiiordiploma = new String[placementcount];
                            placementug = new String[placementcount];
                            placementpg = new String[placementcount];
                            placementuploadtime = new String[placementcount];
                            placementlastmodified = new String[placementcount];
                            placementuploadedby = new String[placementcount];
                            placementuploadedbyplain = new String[placementcount];
                            placementnoofallowedliveatkt = new String[placementcount];
                            placementnoofalloweddeadatkt = new String[placementcount];

                            for (int i = 0; i < placementcount; i++) {

                                placementids[i] = json.getString("id" + i);
                                placementcompanyname[i] = json.getString("companyname" + i);
                                placementcpackage[i] = json.getString("package" + i);
                                placementpost[i] = json.getString("post" + i);
                                placementforwhichcourse[i] = json.getString("forwhichcourse" + i);
                                placementforwhichstream[i] = json.getString("forwhichstream" + i);
                                placementvacancies[i] = json.getString("vacancies" + i);
                                placementlastdateofregistration[i] = json.getString("lastdateofregistration" + i);
                                placementdateofarrival[i] = json.getString("dateofarrival" + i);
                                placementbond[i] = json.getString("bond" + i);
                                placementnoofapti[i] = json.getString("noofapti" + i);
                                placementnooftechtest[i] = json.getString("nooftechtest" + i);
                                placementnoofgd[i] = json.getString("noofgd" + i);
                                placementnoofti[i] = json.getString("noofti" + i);
                                placementnoofhri[i] = json.getString("noofhri" + i);
                                placementstdx[i] = json.getString("stdx" + i);
                                placementstdxiiordiploma[i] = json.getString("stdxiiordiploma" + i);
                                placementug[i] = json.getString("ug" + i);
                                placementpg[i] = json.getString("pg" + i);
                                placementuploadtime[i] = json.getString("uploadtime" + i);
                                placementlastmodified[i] = json.getString("lastmodified" + i);
                                placementuploadedby[i] = json.getString("uploadedby" + i);
                                placementnoofallowedliveatkt[i] = json.getString("noofallowedliveatkt" + i);
                                placementnoofalloweddeadatkt[i] = json.getString("noofalloweddeadatkt" + i);
                            }
                        }
                    }
                } catch (Exception e) {
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {


                tswipe_refresh_layout.setVisibility(View.VISIBLE);
                tswipe_refresh_layout.setRefreshing(false);
                if (!isLastPageLoadedPlacement)
                    for (int i = 0; i < placementcount; i++)
                        try {
//                            if(placementcompanyname[i]!=null)
//                            {
//                                byte[] placementcompanynameEncryptedBytes=SimpleBase64Encoder.decode(placementcompanyname[i]);
//                                byte[] placementcompanynameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementcompanynameEncryptedBytes);
//                                placementcompanyname[i]=new String(placementcompanynameDecryptedBytes);
//                            }
//                            if(placementcpackage[i]!=null)
//                            {
//                                byte[] placementcpackageEncryptedBytes=SimpleBase64Encoder.decode(placementcpackage[i]);
//                                byte[] placementcpackageDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementcpackageEncryptedBytes);
//                                placementcpackage[i]=new String(placementcpackageDecryptedBytes);
//                            }
//                            if(placementpost[i]!=null)
//                            {
//                                byte[] placementpostEncryptedBytes=SimpleBase64Encoder.decode(placementpost[i]);
//                                byte[] placementpostDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementpostEncryptedBytes);
//                                placementpost[i]=new String(placementpostDecryptedBytes);
//                            }
//                            if(placementforwhichcourse[i]!=null)
//                            {
//                                byte[] placementforwhichcourseEncryptedBytes=SimpleBase64Encoder.decode(placementforwhichcourse[i]);
//                                byte[] placementforwhichcourseDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementforwhichcourseEncryptedBytes);
//                                placementforwhichcourse[i]=new String(placementforwhichcourseDecryptedBytes);
//                            }
//                            if(placementforwhichstream[i]!=null)
//                            {
//                                byte[] placementforwhichstreamEncryptedBytes=SimpleBase64Encoder.decode(placementforwhichstream[i]);
//                                byte[] placementforwhichstreamDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementforwhichstreamEncryptedBytes);
//                                placementforwhichstream[i]=new String(placementforwhichstreamDecryptedBytes);
//                            }
//                            if(placementvacancies[i]!=null)
//                            {
//                                byte[] placementvacanciesEncryptedBytes=SimpleBase64Encoder.decode(placementvacancies[i]);
//                                byte[] placementvacanciesDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementvacanciesEncryptedBytes);
//                                placementvacancies[i]=new String(placementvacanciesDecryptedBytes);
//                            }
//                            if(placementlastdateofregistration[i]!=null)
//                            {
//                                byte[] placementlastdateofregistrationEncryptedBytes=SimpleBase64Encoder.decode(placementlastdateofregistration[i]);
//                                byte[] placementlastdateofregistrationDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementlastdateofregistrationEncryptedBytes);
//                                placementlastdateofregistration[i]=new String(placementlastdateofregistrationDecryptedBytes);
//                            }
//                            if(placementdateofarrival[i]!=null)
//                            {
//                                byte[] placementdateofarrivalEncryptedBytes=SimpleBase64Encoder.decode(placementdateofarrival[i]);
//                                byte[] placementdateofarrivalDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementdateofarrivalEncryptedBytes);
//                                placementdateofarrival[i]=new String(placementdateofarrivalDecryptedBytes);
//                            }
//                            if(placementbond[i]!=null)
//                            {
//                                byte[] placementbondEncryptedBytes=SimpleBase64Encoder.decode(placementbond[i]);
//                                byte[] placementbondDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementbondEncryptedBytes);
//                                placementbond[i]=new String(placementbondDecryptedBytes);
//                            }
//                            if(placementnoofapti[i]!=null)
//                            {
//                                byte[] placementnoofaptiEncryptedBytes=SimpleBase64Encoder.decode(placementnoofapti[i]);
//                                byte[] placementnoofaptiDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofaptiEncryptedBytes);
//                                placementnoofapti[i]=new String(placementnoofaptiDecryptedBytes);
//                            }
//                            if(placementnooftechtest[i]!=null)
//                            {
//                                byte[] placementnooftechtestEncryptedBytes=SimpleBase64Encoder.decode(placementnooftechtest[i]);
//                                byte[] placementnooftechtestDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnooftechtestEncryptedBytes);
//                                placementnooftechtest[i]=new String(placementnooftechtestDecryptedBytes);
//                            }
//                            if(placementnoofgd[i]!=null)
//                            {
//                                byte[] placementnoofgdEncryptedBytes=SimpleBase64Encoder.decode(placementnoofgd[i]);
//                                byte[] placementnoofgdDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofgdEncryptedBytes);
//                                placementnoofgd[i]=new String(placementnoofgdDecryptedBytes);
//                            }
//                            if(placementnoofti[i]!=null)
//                            {
//                                byte[] placementnooftiEncryptedBytes=SimpleBase64Encoder.decode(placementnoofti[i]);
//                                byte[] placementnooftiDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnooftiEncryptedBytes);
//                                placementnoofti[i]=new String(placementnooftiDecryptedBytes);
//                            }
//                            if(placementnoofhri[i]!=null)
//                            {
//                                byte[] placementnoofhriEncryptedBytes=SimpleBase64Encoder.decode(placementnoofhri[i]);
//                                byte[] placementnoofhriDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofhriEncryptedBytes);
//                                placementnoofhri[i]=new String(placementnoofhriDecryptedBytes);
//                            }
//                            if(placementstdx[i]!=null)
//                            {
//                                byte[] placementstdxEncryptedBytes=SimpleBase64Encoder.decode(placementstdx[i]);
//                                byte[] placementstdxDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementstdxEncryptedBytes);
//                                placementstdx[i]=new String(placementstdxDecryptedBytes);
//                            }
//                            if(placementstdxiiordiploma[i]!=null)
//                            {
//                                byte[] placementstdxiiordiplomaEncryptedBytes=SimpleBase64Encoder.decode(placementstdxiiordiploma[i]);
//                                byte[] placementstdxiiordiplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementstdxiiordiplomaEncryptedBytes);
//                                placementstdxiiordiploma[i]=new String(placementstdxiiordiplomaDecryptedBytes);
//                            }
//                            if(placementug[i]!=null)
//                            {
//                                byte[] placementugEncryptedBytes=SimpleBase64Encoder.decode(placementug[i]);
//                                byte[] placementugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementugEncryptedBytes);
//                                placementug[i]=new String(placementugDecryptedBytes);
//                            }
//                            if(placementpg[i]!=null)
//                            {
//                                byte[] placementpgEncryptedBytes=SimpleBase64Encoder.decode(placementpg[i]);
//                                byte[] placementpgDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementpgEncryptedBytes);
//                                placementpg[i]=new String(placementpgDecryptedBytes);
//                            }
//                            if(placementuploadtime[i]!=null)
//                            {
//                                byte[] placementuploadtimeEncryptedBytes=SimpleBase64Encoder.decode(placementuploadtime[i]);
//                                byte[] placementuploadtimeDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementuploadtimeEncryptedBytes);
//                                placementuploadtime[i]=new String(placementuploadtimeDecryptedBytes);
//                            }
//                            if(placementlastmodified[i]!=null)
//                            {
//                                byte[] placementlastmodifiedEncryptedBytes=SimpleBase64Encoder.decode(placementlastmodified[i]);
//                                byte[] placementlastmodifiedDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementlastmodifiedEncryptedBytes);
//                                placementlastmodified[i]=new String(placementlastmodifiedDecryptedBytes);
//                            }

                            if (placementuploadedby[i] != null) {
                                byte[] placementuploadedbyEncryptedBytes = SimpleBase64Encoder.decode(placementuploadedby[i]);
                                byte[] placementuploadedbyDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementuploadedbyEncryptedBytes);
                                placementuploadedbyplain[i] = new String(placementuploadedbyDecryptedBytes);
                            }

//                            if(placementnoofallowedliveatkt[i]!=null)
//                            {
//                                byte[] placementnoofallowedliveatktEncryptedBytes=SimpleBase64Encoder.decode(placementnoofallowedliveatkt[i]);
//                                byte[] placementnoofallowedliveatktDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofallowedliveatktEncryptedBytes);
//                                placementnoofallowedliveatkt[i]=new String(placementnoofallowedliveatktDecryptedBytes);
//                            }
//                            if(placementnoofalloweddeadatkt[i]!=null)
//                            {
//                                byte[] placementnoofalloweddeadatktEncryptedBytes=SimpleBase64Encoder.decode(placementnoofalloweddeadatkt[i]);
//                                byte[] placementnoofalloweddeadatktDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofalloweddeadatktEncryptedBytes);
//                                placementnoofalloweddeadatkt[i]=new String(placementnoofalloweddeadatktDecryptedBytes);
//                            }

                        } catch (Exception e) {
                            //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                new GetLastUpdatedPlacement().execute();
            }
        }.execute();
    }

    void filterPlacements(String text) {
        tempListPlacement = new ArrayList();
        for (RecyclerItemPlacement d : itemListPlacement) {

            if (containsIgnoreCase(d.getCompanyname(), text)) {
                tempListPlacement.add(d);
            }
        }
        mAdapterPlacement.updateList(tempListPlacement, text);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (navMenuFlag == 2) {

            getNotifications();
        } else if (navMenuFlag == 3) {
            getPlacements();
        }

    }

    class GetUnreadCountOfNotificationAndPlacement extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {


                json = jParser.makeHttpRequest(url_getnotificationsmetadata, "GET", params);
                unreadcountNotification = Integer.parseInt(json.getString("unreadcount"));


//                json = jParser.makeHttpRequest(url_getplacementsmetadata, "GET", params);
//                unreadcountPlacement = Integer.parseInt(json.getString("unreadcount"));


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
//            placementcountrl.setVisibility(View.VISIBLE);
//            placementcounttxt.setText(unreadcountPlacement+"");
//            if(unreadcountPlacement==0)
//            {
//                placementcountrl.setVisibility(View.GONE);
//            }


            notificationcountrl.setVisibility(View.VISIBLE);
            notificationcounttxt.setText(unreadcountNotification + "");
            if (unreadcountNotification == 0) {
                Toast.makeText(AdminActivity.this, "no notification", Toast.LENGTH_SHORT).show();
                notificationcountrl.setVisibility(View.GONE);
            }

            getNotifications();

        }
    }

    class GetNotificationsReadStatus extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {

                json = jParser.makeHttpRequest(url_getnotificationsmetadata, "GET", params);
                notificationpages = Integer.parseInt(json.getString("pages"));
                called_pages_notification = new int[notificationpages];
                total_no_of_notifications = Integer.parseInt(json.getString("count"));
                unreadcountNotification = Integer.parseInt(json.getString("unreadcount"));

                json = jParser.makeHttpRequest(url_getnotificationsreadstatus, "GET", params);

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
                Toast.makeText(AdminActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }


            notificationcountrl.setVisibility(View.VISIBLE);
            notificationcounttxt.setText(unreadcountNotification + "");
            if (unreadcountNotification == 0) {
                notificationcountrl.setVisibility(View.GONE);
            }
            new GetNotifications().execute();

        }
    }

//placements methods and classes

    class ChangeReadStatusNotification extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("id", param[0]));       //0
            json = jParser.makeHttpRequest(url_changenotificationsreadstatus, "GET", params);
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

    class GetNotifications extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("p", page_to_call_notification + ""));
            json = jParser.makeHttpRequest(url_getnotifications, "GET", params);
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


            tswipe_refresh_layout.setVisibility(View.VISIBLE);
            tswipe_refresh_layout.setRefreshing(false);

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
                        byte[] notificationuploadedbyEncryptedBytes = SimpleBase64Encoder.decode(notificationuploadedby[i]);
                        byte[] notificationuploadedbyDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationuploadedbyEncryptedBytes);
                        notificationuploadedbyplain[i] = new String(notificationuploadedbyDecryptedBytes);
                        Log.d("notificationuploadedby", "notificationuploadedby" + "[" + i + "] " + notificationuploadedby[i]);

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
                json = jParser.makeHttpRequest(url_getlastupdated, "GET", params);
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
                Toast.makeText(AdminActivity.this, "no update", Toast.LENGTH_SHORT).show();
            } else {
//                lastupdatedNotification [i]=s;
            }

            if (!isLastPageLoadedNotification) {
            }
            addNotificationdatatoAdapter();

        }

    }

    public class GetProfileImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            map = downloadImage(load_student_image);
            return map;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            profile.setImageBitmap(result);
        }

        private Bitmap downloadImage(String url) {
            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority("192.168.100.100")
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

    class UploadProfile extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            try {


                File sourceFile = new File(filepath);


                MultipartUtility multipart = new MultipartUtility(upload_profile, "UTF-8");

                multipart.addFormField("u", username);

                if (filename != "") {
                    multipart.addFormField("f", filename);
                    multipart.addFilePart("uf", sourceFile);
                } else
                    multipart.addFormField("f", "null");
                response = multipart.finish();


            } catch (Exception ex) {

            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);

            if (response.get(0).contains("success")) {
                MySharedPreferencesManager.save(AdminActivity.this,"crop", "no");
                Toast.makeText(AdminActivity.this, "Successfully Updated..!", Toast.LENGTH_SHORT).show();
                requestProfileImage();
                AdminProfileFragment fragment = (AdminProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
                fragment.refreshContent();
                DeleteRecursive(new File(directory));
            } else if (response.get(0).contains("null")) {
                requestProfileImage();
                AdminProfileFragment fragment = (AdminProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
                fragment.refreshContent();
                Toast.makeText(AdminActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }

        }

        void DeleteRecursive(File fileOrDirectory) {

            if (fileOrDirectory.isDirectory())
                for (File child : fileOrDirectory.listFiles())
                    DeleteRecursive(child);

            fileOrDirectory.delete();

        }

    }

    class GetPlacementsReadStatus extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {
                json = jParser.makeHttpRequest(url_getplacementsmetadata, "GET", params);
                placementpages = Integer.parseInt(json.getString("pages"));
                called_pages_placement = new int[placementpages];
                total_no_of_placements = Integer.parseInt(json.getString("count"));
                unreadcountPlacement = Integer.parseInt(json.getString("unreadcount"));


                json = jParser.makeHttpRequest(url_getplacementsreadstatus, "GET", params);
                readstatuscountPlacement = Integer.parseInt(json.getString("count"));
                placementreadstatus = new String[readstatuscountPlacement];


                for (int i = 0; i < readstatuscountPlacement; i++) {
                    placementreadstatus[i] = json.getString("s" + i);
//                    Log.d("PlacmentTesting", "total_no_of_placements: "+placementreadstatus[i]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            placementcountrl.setVisibility(View.VISIBLE);
            placementcounttxt.setText(unreadcountPlacement + "");
            if (unreadcountPlacement == 0) {
                placementcountrl.setVisibility(View.GONE);
            }
            Log.d("PlacmentTesting", "total_no_of_placements: " + total_no_of_placements);
            Log.d("PlacmentTesting", "unreadcountPlacement: " + unreadcountPlacement);
            for (int i = 0; i < readstatuscountPlacement; i++) {
                Log.d("PlacmentTesting", "placementreadstatus1: " + placementreadstatus[i]);
            }

            new GetPlacements().execute();

        }
    }

    class GetPlacements extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r = null;

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("p", page_to_call_placement + ""));


            json = jParser.makeHttpRequest(url_getplacements, "GET", params);
            try {
                placementcount = Integer.parseInt(json.getString("count"));

                placementids = new String[placementcount];
                placementcompanyname = new String[placementcount];
                placementcpackage = new String[placementcount];
                placementpost = new String[placementcount];
                placementforwhichcourse = new String[placementcount];
                placementforwhichstream = new String[placementcount];
                placementvacancies = new String[placementcount];
                placementlastdateofregistration = new String[placementcount];
                placementdateofarrival = new String[placementcount];
                placementbond = new String[placementcount];
                placementnoofapti = new String[placementcount];
                placementnooftechtest = new String[placementcount];
                placementnoofgd = new String[placementcount];
                placementnoofti = new String[placementcount];
                placementnoofhri = new String[placementcount];
                placementstdx = new String[placementcount];
                placementstdxiiordiploma = new String[placementcount];
                placementug = new String[placementcount];
                placementpg = new String[placementcount];
                placementuploadtime = new String[placementcount];
                placementlastmodified = new String[placementcount];
                placementuploadedby = new String[placementcount];
                placementuploadedbyplain = new String[placementcount];
                placementnoofallowedliveatkt = new String[placementcount];
                placementnoofalloweddeadatkt = new String[placementcount];

                for (int i = 0; i < placementcount; i++) {

                    placementids[i] = json.getString("id" + i);
                    placementcompanyname[i] = json.getString("companyname" + i);
                    placementcpackage[i] = json.getString("package" + i);
                    placementpost[i] = json.getString("post" + i);
                    placementforwhichcourse[i] = json.getString("forwhichcourse" + i);
                    placementforwhichstream[i] = json.getString("forwhichstream" + i);
                    placementvacancies[i] = json.getString("vacancies" + i);
                    placementlastdateofregistration[i] = json.getString("lastdateofregistration" + i);
                    placementdateofarrival[i] = json.getString("dateofarrival" + i);
                    placementbond[i] = json.getString("bond" + i);
                    placementnoofapti[i] = json.getString("noofapti" + i);
                    placementnooftechtest[i] = json.getString("nooftechtest" + i);
                    placementnoofgd[i] = json.getString("noofgd" + i);
                    placementnoofti[i] = json.getString("noofti" + i);
                    placementnoofhri[i] = json.getString("noofhri" + i);
                    placementstdx[i] = json.getString("stdx" + i);
                    placementstdxiiordiploma[i] = json.getString("stdxiiordiploma" + i);
                    placementug[i] = json.getString("ug" + i);
                    placementpg[i] = json.getString("pg" + i);
                    placementuploadtime[i] = json.getString("uploadtime" + i);
                    placementlastmodified[i] = json.getString("lastmodified" + i);
                    placementuploadedby[i] = json.getString("uploadedby" + i);

                    placementnoofallowedliveatkt[i] = json.getString("noofallowedliveatkt" + i);
                    placementnoofalloweddeadatkt[i] = json.getString("noofalloweddeadatkt" + i);
                }


            } catch (Exception e) {
            }

            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            tswipe_refresh_layout.setVisibility(View.VISIBLE);
            tswipe_refresh_layout.setRefreshing(false);

            Log.d("Getplacement", "onPostExecute:  im here");
            for (int i = 0; i < placementcount; i++)
                try {
//                    if(placementcompanyname[i]!=null)
//                    {
//                        byte[] placementcompanynameEncryptedBytes=SimpleBase64Encoder.decode(placementcompanyname[i]);
//                        byte[] placementcompanynameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementcompanynameEncryptedBytes);
//                        placementcompanyname[i]=new String(placementcompanynameDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementcompanyname"+"["+i+"] "+placementcompanyname[i]);
//
//                    }
//                    if(placementcpackage[i]!=null)
//                    {
//                        byte[] placementcpackageEncryptedBytes=SimpleBase64Encoder.decode(placementcpackage[i]);
//                        byte[] placementcpackageDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementcpackageEncryptedBytes);
//                        placementcpackage[i]=new String(placementcpackageDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementcpackage"+"["+i+"] "+placementcpackage[i]);
//
//                    }
//                    if(placementpost[i]!=null)
//                    {
//                        byte[] placementpostEncryptedBytes=SimpleBase64Encoder.decode(placementpost[i]);
//                        byte[] placementpostDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementpostEncryptedBytes);
//                        placementpost[i]=new String(placementpostDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementpost"+"["+i+"] "+placementpost[i]);
//
//                    }
//                    if(placementforwhichcourse[i]!=null)
//                    {
//                        byte[] placementforwhichcourseEncryptedBytes=SimpleBase64Encoder.decode(placementforwhichcourse[i]);
//                        byte[] placementforwhichcourseDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementforwhichcourseEncryptedBytes);
//                        placementforwhichcourse[i]=new String(placementforwhichcourseDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementforwhichcourse"+"["+i+"] "+placementforwhichcourse[i]);
//
//                    }
//                    if(placementforwhichstream[i]!=null)
//                    {
//                        byte[] placementforwhichstreamEncryptedBytes=SimpleBase64Encoder.decode(placementforwhichstream[i]);
//                        byte[] placementforwhichstreamDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementforwhichstreamEncryptedBytes);
//                        placementforwhichstream[i]=new String(placementforwhichstreamDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementforwhichstream"+"["+i+"] "+placementforwhichstream[i]);
//
//                    }
//                    if(placementvacancies[i]!=null)
//                    {
//                        byte[] placementvacanciesEncryptedBytes=SimpleBase64Encoder.decode(placementvacancies[i]);
//                        byte[] placementvacanciesDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementvacanciesEncryptedBytes);
//                        placementvacancies[i]=new String(placementvacanciesDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementvacancies"+"["+i+"] "+placementvacancies[i]);
//
//                    }
//                    if(placementlastdateofregistration[i]!=null)
//                    {
//                        byte[] placementlastdateofregistrationEncryptedBytes=SimpleBase64Encoder.decode(placementlastdateofregistration[i]);
//                        byte[] placementlastdateofregistrationDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementlastdateofregistrationEncryptedBytes);
//                        placementlastdateofregistration[i]=new String(placementlastdateofregistrationDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementlastdateofregistration"+"["+i+"] "+placementlastdateofregistration[i]);
//
//                    }
//                    if(placementdateofarrival[i]!=null)
//                    {
//                        byte[] placementdateofarrivalEncryptedBytes=SimpleBase64Encoder.decode(placementdateofarrival[i]);
//                        byte[] placementdateofarrivalDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementdateofarrivalEncryptedBytes);
//                        placementdateofarrival[i]=new String(placementdateofarrivalDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementdateofarrival"+"["+i+"] "+placementdateofarrival[i]);
//
//                    }
//                    if(placementbond[i]!=null)
//                    {
//                        byte[] placementbondEncryptedBytes=SimpleBase64Encoder.decode(placementbond[i]);
//                        byte[] placementbondDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementbondEncryptedBytes);
//                        placementbond[i]=new String(placementbondDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementbond"+"["+i+"] "+placementbond[i]);
//
//                    }
//                    if(placementnoofapti[i]!=null)
//                    {
//                        byte[] placementnoofaptiEncryptedBytes=SimpleBase64Encoder.decode(placementnoofapti[i]);
//                        byte[] placementnoofaptiDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofaptiEncryptedBytes);
//                        placementnoofapti[i]=new String(placementnoofaptiDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementnoofapti"+"["+i+"] "+placementnoofapti[i]);
//
//                    }
//                    if(placementnooftechtest[i]!=null)
//                    {
//                        byte[] placementnooftechtestEncryptedBytes=SimpleBase64Encoder.decode(placementnooftechtest[i]);
//                        byte[] placementnooftechtestDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnooftechtestEncryptedBytes);
//                        placementnooftechtest[i]=new String(placementnooftechtestDecryptedBytes);
//                    }
//                    if(placementnoofgd[i]!=null)
//                    {
//                        byte[] placementnoofgdEncryptedBytes=SimpleBase64Encoder.decode(placementnoofgd[i]);
//                        byte[] placementnoofgdDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofgdEncryptedBytes);
//                        placementnoofgd[i]=new String(placementnoofgdDecryptedBytes);
//                    }
//                    if(placementnoofti[i]!=null)
//                    {
//                        byte[] placementnooftiEncryptedBytes=SimpleBase64Encoder.decode(placementnoofti[i]);
//                        byte[] placementnooftiDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnooftiEncryptedBytes);
//                        placementnoofti[i]=new String(placementnooftiDecryptedBytes);
//                    }
//                    if(placementnoofhri[i]!=null)
//                    {
//                        byte[] placementnoofhriEncryptedBytes=SimpleBase64Encoder.decode(placementnoofhri[i]);
//                        byte[] placementnoofhriDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofhriEncryptedBytes);
//                        placementnoofhri[i]=new String(placementnoofhriDecryptedBytes);
//                    }
//                    if(placementstdx[i]!=null)
//                    {
//                        byte[] placementstdxEncryptedBytes=SimpleBase64Encoder.decode(placementstdx[i]);
//                        byte[] placementstdxDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementstdxEncryptedBytes);
//                        placementstdx[i]=new String(placementstdxDecryptedBytes);
//                    }
//                    if(placementstdxiiordiploma[i]!=null)
//                    {
//                        byte[] placementstdxiiordiplomaEncryptedBytes=SimpleBase64Encoder.decode(placementstdxiiordiploma[i]);
//                        byte[] placementstdxiiordiplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementstdxiiordiplomaEncryptedBytes);
//                        placementstdxiiordiploma[i]=new String(placementstdxiiordiplomaDecryptedBytes);
//                    }
//                    if(placementug[i]!=null)
//                    {
//                        byte[] placementugEncryptedBytes=SimpleBase64Encoder.decode(placementug[i]);
//                        byte[] placementugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementugEncryptedBytes);
//                        placementug[i]=new String(placementugDecryptedBytes);
//                    }
//                    if(placementpg[i]!=null)
//                    {
//                        byte[] placementpgEncryptedBytes=SimpleBase64Encoder.decode(placementpg[i]);
//                        byte[] placementpgDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementpgEncryptedBytes);
//                        placementpg[i]=new String(placementpgDecryptedBytes);
//                    }
//                    if(placementuploadtime[i]!=null)
//                    {
//                        byte[] placementuploadtimeEncryptedBytes=SimpleBase64Encoder.decode(placementuploadtime[i]);
//                        byte[] placementuploadtimeDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementuploadtimeEncryptedBytes);
//                        placementuploadtime[i]=new String(placementuploadtimeDecryptedBytes);
//                    }
//                    if(placementlastmodified[i]!=null)
//                    {
//                        byte[] placementlastmodifiedEncryptedBytes=SimpleBase64Encoder.decode(placementlastmodified[i]);
//                        byte[] placementlastmodifiedDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementlastmodifiedEncryptedBytes);
//                        placementlastmodified[i]=new String(placementlastmodifiedDecryptedBytes);
//                    }
                    if (placementuploadedby[i] != null) {
                        byte[] placementuploadedbyEncryptedBytes = SimpleBase64Encoder.decode(placementuploadedby[i]);
                        byte[] placementuploadedbyDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementuploadedbyEncryptedBytes);
                        placementuploadedbyplain[i] = new String(placementuploadedbyDecryptedBytes);
                        Log.d("PlacmentTesting", "placementuploadedbyplain" + "[" + i + "] " + placementuploadedbyplain[i]);

                    }
//                    if(placementnoofallowedliveatkt[i]!=null)
//                    {
//                        byte[] placementnoofallowedliveatktEncryptedBytes=SimpleBase64Encoder.decode(placementnoofallowedliveatkt[i]);
//                        byte[] placementnoofallowedliveatktDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofallowedliveatktEncryptedBytes);
//                        placementnoofallowedliveatkt[i]=new String(placementnoofallowedliveatktDecryptedBytes);
//                        Log.d("PlacmentTesting", "placementnoofallowedliveatkt"+"["+i+"] "+placementnoofallowedliveatkt[i]);
//
//                    }
//                    if(placementnoofalloweddeadatkt[i]!=null)
//                    {
//                        byte[] placementnoofalloweddeadatktEncryptedBytes=SimpleBase64Encoder.decode(placementnoofalloweddeadatkt[i]);
//                        byte[] placementnoofalloweddeadatktDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofalloweddeadatktEncryptedBytes);
//                        placementnoofalloweddeadatkt[i]=new String(placementnoofalloweddeadatktDecryptedBytes);
//                    }

                } catch (Exception e) {
                    //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            new GetLastUpdatedPlacement().execute();
        }
    }

    class GetLastUpdatedPlacement extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r = null;

            Set<String> uniqKeys = new TreeSet<String>();
            uniqKeys.addAll(Arrays.asList(placementuploadedbyplain));

            uniqueUploadersPlacement = uniqKeys.toArray(new String[uniqKeys.size()]);
            uniqueUploadersEncPlacement = new String[uniqueUploadersPlacement.length];
            lastupdatedPlacement = new String[uniqueUploadersPlacement.length];

            for (int j = 0; j < uniqueUploadersPlacement.length; j++) {
                for (int i = 0; i < placementcount; i++) {

                    if (placementuploadedbyplain[i].equals(uniqueUploadersPlacement[j])) {
                        uniqueUploadersEncPlacement[j] = placementuploadedby[i];
                    }
                }
            }

            for (int i = 0; i < uniqueUploadersPlacement.length; i++) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", uniqueUploadersEncPlacement[i]));       //0
                json = jParser.makeHttpRequest(url_getlastupdated, "GET", params);
                try {
                    String s = json.getString("lastupdated");
                    if (s.equals("noupdate")) {
                    } else {
                        lastupdatedPlacement[i] = s;

                    }

                } catch (Exception e) {
                }
            }


            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (!isLastPageLoadedPlacement)
                addPlacementdatatoAdapter();

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

            json = jParser.makeHttpRequest(url_changeplacementsreadstatus, "GET", params);
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }

    class LoginFirebaseTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            String user=param[0];
            String hash=param[1];
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(user,hash)
                    .addOnCompleteListener(AdminActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                MySharedPreferencesManager.save(AdminActivity.this,"fireLoginStatus","Successfully logged in to Firebase");
                            } else {
                                MySharedPreferencesManager.save(AdminActivity.this,"fireLoginStatus","Failed to login to Firebase");
                            }
                        }
                    });
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            String status=MySharedPreferencesManager.getData(AdminActivity.this,"fireLoginStatus");
            Toast.makeText(AdminActivity.this, status, Toast.LENGTH_SHORT).show();
            // remove value from shared
            MySharedPreferencesManager.removeKey(AdminActivity.this,"fireLoginStatus");
        }
    }


    public void refreshUserCount() {
        new GetCountOfUsersUnderAdmin().execute();
        Log.d("kun", "refreshUserCount: ");
    }

    class GetCountOfUsersUnderAdmin extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            String r = null;
            String username = MySharedPreferencesManager.getUsername(AdminActivity.this);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            json = jParser.makeHttpRequest(MyConstants.url_GetCountOfUsersUnderAdmin, "GET", params);
            try {
                r = json.getString("count");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("0")) {
                bluePanelTv.setText(MyConstants.users_under_your_supervision);
            } else {
                bluePanelTv.setText(result + MyConstants.users_under_your_supervision);
            }
        }
    }

}
