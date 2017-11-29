package placeme.octopusites.com.placeme;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//import cat.ereza.customactivityoncrash.config.CaocConfig;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.LoginActivity.md5;

public class MainActivity extends AppCompatActivity implements ImagePickerCallback
{

    final public static int  STUDENT_DATA_CHANGE_RESULT_CODE =333;
    private int previousTotalNotification = 0; // The total number of items in the dataset after the last load
    private boolean loadingNotification = true; // True if we are still waiting for the last set of data to load.
    private int visibleThresholdNotification = 0; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItemNotification, visibleItemCountNotification, totalItemCountNotification;
    private int page_to_call_notification = 1;
    private int current_page_notification = 1;
    int total_no_of_notifications;
    int[] called_pages_notification;
    boolean isFirstRunNotification=true,isLastPageLoadedNotification=false;
    int lastPageFlagNotification=0;
    int unreadcountNotification=0;
    int notificationcount=0;
    int readstatuscountNotification=0;
    RelativeLayout notificationcountrl;
    TextView notificationcounttxt;
    String lastupdatedNotification[];
    String[] uniqueUploadersNotification;
    String[] uniqueUploadersEncNotification;
    String notificationids[],notificationtitles[],notificationnotifications[],notificationfilename1[],notificationfilename2[],notificationfilename3[],notificationfilename4[],notificationfilename5[],notificationuploadtime[],notificationlastmodified[],notificationuploadedby[],notificationuploadedbyplain[];
    String notificationreadstatus[];
    int notificationpages=0;
    private int previousTotalPlacement = 0; // The total number of items in the dataset after the last load
    private boolean loadingPlacement = true; // True if we are still waiting for the last set of data to load.
    private int visibleThresholdPlacement = 0; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItemPlacement, visibleItemCountPlacement, totalItemCountPlacement;
    private int page_to_call_placement = 1;
    private int current_page_placement = 1;
    int total_no_of_placements;
    int[] called_pages_placement;
    boolean isFirstRunPlacement=true,isLastPageLoadedPlacement=false;
    int lastPageFlagPlacement=0;
    int unreadcountPlacement=0;
    int placementcount=0;
    int readstatuscountPlacement=0;
    RelativeLayout placementcountrl,messagecountrl;
    TextView placementcounttxt,messagecount;
    String lastupdatedPlacement[];
    String[] uniqueUploadersPlacement;
    String[] uniqueUploadersEncPlacement;
    String placementids[],placementcompanyname[],placementcpackage[],placementpost[],placementforwhichcourse[],placementforwhichstream[],placementvacancies[],placementlastdateofregistration[],placementdateofarrival[],placementbond[],placementnoofapti[],placementnooftechtest[],placementnoofgd[],placementnoofti[],placementnoofhri[],placementstdx[],placementstdxiiordiploma[],placementug[],placementpg[],placementuploadtime[],placementlastmodified[],placementuploadedby[],placementuploadedbyplain[],placementnoofallowedliveatkt[],placementnoofalloweddeadatkt[];
    String placementreadstatus[];
    int placementpages=0;
    Menu menu;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    private String plainusername,username="",fname="",mname="",sname="";
    CircleImageView profile;
    boolean doubleBackToExitPressedOnce = false;
    int selectedMenuFlag=1;
    private List<RecyclerItem> itemListNotification = new ArrayList<>();
    private List<RecyclerItemPlacement> itemListPlacement = new ArrayList<>();
    List<RecyclerItem> tempListNotification;
    List<RecyclerItemPlacement> tempListPlacement;
    private RecyclerItemAdapter mAdapterNotification;
    int searchNotificationFlag=0,searchPlacementFlag=0;
    private RecyclerItemAdapterPlacement mAdapterPlacement;
    private RecyclerView recyclerViewNotification,recyclerViewPlacement;
    JSONParser jParser = new JSONParser();
    String digest1,digest2;
    JSONObject json;
    FrameLayout mainfragment;
    private MaterialSearchView searchView;
    String[] blanksuggestionList={""};
    String[] suggestionList1={"A","AA","AAA","AAAA","AAAAA","AAAAAA","AAAAAAA"};
    private static String load_student_image = "http://192.168.100.100/AESTest/GetImage";
    private static String upload_profile = "http://192.168.100.100/AESTest/UploadProfile";

    private static String url_getnotificationsmetadata= "http://192.168.100.30/CreateNotificationTemp/GetNotificationsMetaData";
    private static String url_getnotificationsreadstatus= "http://192.168.100.30/CreateNotificationTemp/GetReadStatusOfNotifications";
    private static String url_getnotifications= "http://192.168.100.30/CreateNotificationTemp/GetNotifications";
    private static String url_changenotificationsreadstatus= "http://192.168.100.30/CreateNotificationTemp/ChangeNotificationReadStatus";

    private static String url_getplacementsmetadata= "http://192.168.100.30/CreateNotificationTemp/GetPlacementsMetaData";
    private static String url_changeplacementsreadstatus= "http://192.168.100.30/CreateNotificationTemp/ChangePlacementReadStatus";
    private static String url_getplacements= "http://192.168.100.30/CreateNotificationTemp/GetPlacements";
    private static String url_getplacementsreadstatus= "http://192.168.100.30/CreateNotificationTemp/GetReadStatusOfPlacements";

    private static String url_getlastupdated= "http://192.168.100.100/AESTest/GetLastUpdated";
    int navMenuFlag=0,oldNavMenuFlag=0;
    FrameLayout crop_layout;
    private ImageView resultView;
    ImagePicker imagePicker;
    private int chooserType;
    private String mediaPath;
    private String finalPath;
    String filepath="",filename="";
    private String thumbPath;
    String directory;
    List<String> response;
    public static final String Username = "nameKey";
    public static final String Password = "passKey";
    SwipeRefreshLayout tswipe_refresh_layout;
    int crop_flag=0;
    String reciever_username[],reciever_uid[];
    String unread_count[];
    private static String url_get_chatrooms = "http://192.168.100.100/PlaceMe/GetChatRooms";
    private static String url_getmessagesreadstatus= "http://192.168.100.100/PlaceMe/GetReadStatusOfMessages";
    int count;
    int unreadMessageCount=0;
    String sender_uid;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";

    Toolbar toolbar;
    ViewPager mViewPager;
    TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.tab_news,
            R.drawable.tab_videos,
            R.drawable.tab_resume,
            R.drawable.tab_question,
            R.drawable.tab_ebooks,
            R.drawable.tab_question_sets,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("MYTAG", "mainactivity called: ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mViewPager = (ViewPager) findViewById(R.id.blogcontainer);
        tabLayout = (TabLayout) findViewById(R.id.blogtabs);
        tabLayout.setupWithViewPager(mViewPager);

        MySharedPreferencesManager.save(MainActivity.this,"intro","yes");

        setupViewPager(mViewPager);
        setupTabIcons();
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.colorAccent);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.white);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

//        FirebaseMessaging.getInstance().subscribeToTopic("ALL");
//        FirebaseMessaging.getInstance().subscribeToTopic("REGISTERED");
//        FirebaseMessaging.getInstance().subscribeToTopic("STUDENT");
//        FirebaseMessaging.getInstance().subscribeToTopic("ALUMNI");
//        FirebaseMessaging.getInstance().subscribeToTopic("ADMIN");
//        FirebaseMessaging.getInstance().subscribeToTopic("HR");


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals("pushNotification")) {

                    String sender = intent.getStringExtra("sender");
                    for(int i=0;i<reciever_username.length;i++)
                    {
                        if(reciever_username[i].equals(sender)) {
                            unread_count[i] = Integer.parseInt(unread_count[i]) + 1+"";
                            unreadMessageCount++;
                        }
                    }
                    messagecountrl.setVisibility(View.VISIBLE);
                    messagecount.setText(unreadMessageCount+"");
                    if(unreadMessageCount==0)
                    {
                        messagecountrl.setVisibility(View.GONE);
                    }
                }
            }
        };


        imagePicker = new ImagePicker(this);
        imagePicker.setImagePickerCallback(this);

        imagePicker.shouldGenerateMetadata(false); // Default is true
        imagePicker.shouldGenerateThumbnails(false); // Default is true


        crop_layout=(FrameLayout)findViewById(R.id.crop_layout);
        resultView = (ImageView) findViewById(R.id.result_image);


        sharedpreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);
        String pass=sharedpreferences.getString(Password,null);
        String role=sharedpreferences.getString("role",null);



        ProfileRole r=new ProfileRole();
        r.setUsername(username);
        r.setRole(role);

        Digest d=new Digest();
        digest1=d.getDigest1();
        digest2=d.getDigest2();

        if(digest1==null||digest2==null) {
            digest1 = sharedpreferences.getString("digest1", null);
            digest2 = sharedpreferences.getString("digest2", null);
            d.setDigest1(digest1);
            d.setDigest2(digest2);
        }

        try
        {

            demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            demoIVBytes = SimpleBase64Encoder.decode(digest2);
            sPadding = "ISO10126Padding";

            byte[] demo1EncryptedBytes1=SimpleBase64Encoder.decode(username);
            byte[] demo1DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes1);
            plainusername=new String(demo1DecryptedBytes1);
            r.setPlainusername(plainusername);

            byte[] demo2EncryptedBytes1=SimpleBase64Encoder.decode(pass);
            byte[] demo2DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo2EncryptedBytes1);
            String data=new String(demo2DecryptedBytes1);
            String hash=md5(data + sharedpreferences.getString("digest3",null));

            loginFirebase(plainusername,hash);

        }catch (Exception e){Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();}

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("otp", "no");
        editor.commit();

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

                if(selectedMenuFlag==0)
                {

                }
                else if(selectedMenuFlag==1)
                {
                    searchNotificationFlag=1;
                    filterNotifications(newText);
                }
                else if(selectedMenuFlag==2)
                {
                    searchPlacementFlag=1;
                    filterPlacements(newText);
                }
                else if(selectedMenuFlag==3)
                {

                }
                else if(selectedMenuFlag==4)
                {

                }
                else if(selectedMenuFlag==5)
                {

                }
                else if(selectedMenuFlag==6)
                {

                }

                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {


                if(selectedMenuFlag==1)
                {
                    searchView.setSuggestions(blanksuggestionList);
                    searchView.setSuggestions(null);
                }
                else if(selectedMenuFlag==2)
                {
                    searchView.setSuggestions(blanksuggestionList);
                    searchView.setSuggestions(null);
                }


                final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
                upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                searchView.setBackIcon(upArrow);
            }
            @Override
            public void onSearchViewClosed() {
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        {
            //scrolling toolbar gone problem solve blog
            AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                if(oldNavMenuFlag!=navMenuFlag) {
                    if (navMenuFlag == 1) {
                        selectedMenuFlag=0;

                        //scroll
                        params.setScrollFlags(0);

                        crop_layout.setVisibility(View.GONE);
                        MyProfileFragment fragment = new MyProfileFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);

                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("My Profile");

//                    int i=4/0;



                    } else if (navMenuFlag == 2) {

                        params.setScrollFlags(0);

                        selectedMenuFlag=1;
                        crop_layout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("Notifications");
                        mainfragment.setVisibility(View.GONE);
                        tswipe_refresh_layout.setVisibility(View.VISIBLE);

                        getNotifications();
                        recyclerViewNotification.setVisibility(View.VISIBLE);
                        recyclerViewPlacement.setVisibility(View.GONE);
                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);

                    } else if (navMenuFlag == 3) {

                        selectedMenuFlag=2;
                        params.setScrollFlags(0);

                        crop_layout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("Placements");
                        mainfragment.setVisibility(View.GONE);
                        tswipe_refresh_layout.setVisibility(View.VISIBLE);

                        getPlacements();
                        recyclerViewNotification.setVisibility(View.GONE);
                        recyclerViewPlacement.setVisibility(View.VISIBLE);
                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);

                    } else if (navMenuFlag == 4) {

                        selectedMenuFlag=3;
                        params.setScrollFlags(0);

                        crop_layout.setVisibility(View.GONE);
                        MessagesFragment fragment = new MessagesFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();

                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("Messages");
                        tswipe_refresh_layout.setVisibility(View.GONE);
                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);

                    } else if (navMenuFlag == 5) {

                        selectedMenuFlag=4;
                        params.setScrollFlags(0);

                        crop_layout.setVisibility(View.GONE);
                        SettingsFragment fragment = new SettingsFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("Settings");
                        tswipe_refresh_layout.setVisibility(View.GONE);
                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                    } else if (navMenuFlag == 6) {

                        selectedMenuFlag=5;
                        params.setScrollFlags(5);           // enable scrolling

//                        Toast.makeText(MainActivity.this, ""+params.getScrollFlags(), Toast.LENGTH_SHORT).show();
                        crop_layout.setVisibility(View.GONE);
                        //mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("Blog");
                        tswipe_refresh_layout.setVisibility(View.GONE);

                        mainfragment.setVisibility(View.GONE);
                        mViewPager.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.VISIBLE);

                    } else if (navMenuFlag == 7) {

                        selectedMenuFlag=6;
                        params.setScrollFlags(0);

                        crop_layout.setVisibility(View.GONE);
                        AboutFragment fragment = new AboutFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("About");
                        tswipe_refresh_layout.setVisibility(View.GONE);
                        mViewPager.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                    }
                }

            }


            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                oldNavMenuFlag=navMenuFlag;

            }
        };


        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final View hView =  navigationView.getHeaderView(0);

        profile = (CircleImageView)hView.findViewById(R.id.profile_image);
        new GetProfileImage().execute();
        final ImageView profilei=(ImageView)hView.findViewById(R.id.profile);
        final ImageView notificationi=(ImageView)hView.findViewById(R.id.notification);
        final ImageView placementi=(ImageView)hView.findViewById(R.id.placement);
        final ImageView proi=(ImageView)hView.findViewById(R.id.pro);
        final ImageView settingsi=(ImageView)hView.findViewById(R.id.settings);
        final ImageView newsi=(ImageView)hView.findViewById(R.id.blog);
        final ImageView chati=(ImageView)hView.findViewById(R.id.chat);

        notificationcounttxt=(TextView)hView.findViewById(R.id.notificationcount);
        notificationcountrl=(RelativeLayout)hView.findViewById(R.id.notificationcountrl);

        placementcounttxt=(TextView)hView.findViewById(R.id.placementcount);
        placementcountrl=(RelativeLayout)hView.findViewById(R.id.placementcountrl);

        messagecount=(TextView)hView.findViewById(R.id.messagecount);
        messagecountrl=(RelativeLayout)hView.findViewById(R.id.messagecountrl);

        View v1=(View)hView.findViewById(R.id.prifileselectionview);
        View v2=(View)hView.findViewById(R.id.notificationselectionview);
        View v3=(View)hView.findViewById(R.id.placementselectionview);
        View v4=(View)hView.findViewById(R.id.proselectionview);
        View v5=(View)hView.findViewById(R.id.settingselectionview);
        View v6=(View)hView.findViewById(R.id.blogselectionview);
        View v7=(View)hView.findViewById(R.id.abtselectionview);
        View v8=(View)hView.findViewById(R.id.chatselectionview);

        mainfragment=(FrameLayout)findViewById(R.id.mainfragment);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                navMenuFlag=1;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.profile_icon_selected);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTextColor(Color.parseColor("#4169e1"));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.chat);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.paper);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTextColor(Color.parseColor("#ffffff"));


                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTextColor(Color.parseColor("#ffffff"));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);



            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag=2;


                tswipe_refresh_layout.setVisibility(View.VISIBLE);

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_selected);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTextColor(Color.parseColor("#4169e1"));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.chat);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.paper);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTextColor(Color.parseColor("#ffffff"));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTextColor(Color.parseColor("#ffffff"));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag=3;

                tswipe_refresh_layout.setVisibility(View.VISIBLE);

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_selected);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTextColor(Color.parseColor("#4169e1"));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.chat);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.paper);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTextColor(Color.parseColor("#ffffff"));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTextColor(Color.parseColor("#ffffff"));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });
        v8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag=4;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.chat_selected);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTextColor(Color.parseColor("#4169e1"));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.paper);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTextColor(Color.parseColor("#ffffff"));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTextColor(Color.parseColor("#ffffff"));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crop_flag=1;
                startActivity(new Intent(MainActivity.this,ProSplashScreen.class));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });
        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag=5;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.chat);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.paper);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_selected);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTextColor(Color.parseColor("#4169e1"));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTextColor(Color.parseColor("#ffffff"));


                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTextColor(Color.parseColor("#ffffff"));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });
        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag=6;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.chat);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.paper);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_selected);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTextColor(Color.parseColor("#4169e1"));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTextColor(Color.parseColor("#ffffff"));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);




            }
        });
        v7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag=7;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1=(TextView)hView.findViewById(R.id.profiletxt);
                pt1.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2=(TextView)hView.findViewById(R.id.notificationtxt);
                pt2.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3=(TextView)hView.findViewById(R.id.placementtxt);
                pt3.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable8 = getResources().getDrawable(R.drawable.chat);
                chati.setImageDrawable(myDrawable8);

                TextView pt8=(TextView)hView.findViewById(R.id.chattxt);
                pt8.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable4 = getResources().getDrawable(R.drawable.paper);
                proi.setImageDrawable(myDrawable4);

                TextView pt4=(TextView)hView.findViewById(R.id.protxt);
                pt4.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5=(TextView)hView.findViewById(R.id.settingstxt);
                pt5.setTextColor(Color.parseColor("#ffffff"));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6=(TextView)hView.findViewById(R.id.blogtxt);
                pt6.setTextColor(Color.parseColor("#ffffff"));

                TextView pt7=(TextView)hView.findViewById(R.id.abttxt);
                pt7.setTextColor(Color.parseColor("#4169e1"));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });


        Drawable myDrawable = getResources().getDrawable(R.drawable.notification_selected);
        notificationi.setImageDrawable(myDrawable);

        TextView pt=(TextView)hView.findViewById(R.id.notificationtxt);
        pt.setTextColor(Color.parseColor("#4169e1"));

        recyclerViewNotification = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapterNotification = new RecyclerItemAdapter(itemListNotification);
        recyclerViewNotification.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManagerNotification = new LinearLayoutManager(this);
        recyclerViewNotification.setLayoutManager(linearLayoutManagerNotification);
        recyclerViewNotification.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNotification.setAdapter(mAdapterNotification);

        recyclerViewPlacement = (RecyclerView) findViewById(R.id.recycler_view_placement);
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

                RecyclerItem item=null;
                if(searchNotificationFlag==1)
                    item = tempListNotification.get(position);
                else
                    item = itemListNotification.get(position);
                if(!item.getisRead())
                {
                    item.setisRead(true);
                    unreadcountNotification--;
                    notificationcountrl.setVisibility(View.VISIBLE);
                    notificationcounttxt.setText(unreadcountNotification+"");
                    if(unreadcountNotification==0)
                    {
                        notificationcountrl.setVisibility(View.GONE);
                    }

                }

                mAdapterNotification.notifyDataSetChanged();

                changeReadStatusNotification(item.getId());

                crop_flag=1;
                Intent i1 = new Intent(MainActivity.this, ViewNotification.class);
                i1.putExtra("id",item.getId());
                i1.putExtra("title",item.getTitle());
                i1.putExtra("notification",item.getNotification());
                i1.putExtra("file1",item.getFilename1());
                i1.putExtra("file2",item.getFilename2());
                i1.putExtra("file3",item.getFilename3());
                i1.putExtra("file4",item.getFilename4());
                i1.putExtra("file5",item.getFilename5());
                i1.putExtra("uploadedby",item.getUploadedby());
                i1.putExtra("uploadtime",item.getUploadtime());
                i1.putExtra("lastmodified",item.getLastmodified());

                startActivity(i1);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        recyclerViewNotification.setOnScrollListener(new EndlessRecyclerOnScrollListenerNotification(linearLayoutManagerNotification) {
            @Override
            public void onLoadMore(int current_page) {

                if(total_no_of_notifications>20) {
                    simulateLoadingNotification();
                }

            }
        });

        recyclerViewPlacement.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewPlacement, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                RecyclerItemPlacement item=null;
                if(searchPlacementFlag==1)
                    item = tempListPlacement.get(position);
                else
                    item = itemListPlacement.get(position);


                if(!item.getisRead())
                {
                    item.setisRead(true);
                    unreadcountPlacement--;
                    placementcountrl.setVisibility(View.VISIBLE);
                    placementcounttxt.setText(unreadcountPlacement+"");
                    if(unreadcountPlacement==0)
                    {
                        placementcountrl.setVisibility(View.GONE);
                    }

                }

                mAdapterPlacement.notifyDataSetChanged();

                changeReadStatusPlacement(item.getId());

                crop_flag=1;

                Intent i1 = new Intent(MainActivity.this, ViewPlacement.class);
                i1.putExtra("id",item.getId());
                i1.putExtra("companyname",item.getCompanyname());
                i1.putExtra("package",item.getCpackage());
                i1.putExtra("post",item.getPost());
                i1.putExtra("forwhichcourse",item.getForwhichcourse());
                i1.putExtra("forwhichstream",item.getForwhichstream());
                i1.putExtra("vacancies",item.getVacancies());
                i1.putExtra("lastdateofregistration",item.getLastdateofregistration());
                i1.putExtra("dateofarrival",item.getDateofarrival());
                i1.putExtra("bond",item.getBond());
                i1.putExtra("noofapti",item.getNoofapti());
                i1.putExtra("nooftechtest",item.getNooftechtest());
                i1.putExtra("noofgd",item.getNoofgd());
                i1.putExtra("noofti",item.getNoofti());
                i1.putExtra("noofhri",item.getNoofhri());
                i1.putExtra("stdx",item.getStdx());
                i1.putExtra("stdxiiordiploma",item.getStdxiiordiploma());
                i1.putExtra("ug",item.getUg());
                i1.putExtra("pg",item.getPg());
                i1.putExtra("uploadtime",item.getUploadtime());
                i1.putExtra("lastmodified",item.getLastmodified());
                i1.putExtra("uploadedby",item.getUploadedby());
                i1.putExtra("noofallowedliveatkt",item.getNoofallowedliveatkt());
                i1.putExtra("noofalloweddeadatkt",item.getNoofalloweddeadatkt());
                startActivity(i1);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerViewPlacement.setOnScrollListener(new EndlessRecyclerOnScrollListenerPlacement(linearLayoutManagerPlacement) {
            @Override
            public void onLoadMore(int current_page) {

                if(total_no_of_placements>20) {
                    simulateLoadingPlacement();
                }

            }
        });

        disableNavigationViewScrollbars(navigationView);


        tswipe_refresh_layout=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                if(selectedMenuFlag==1) {
                    getNotifications();
                }
                else if(selectedMenuFlag==2) {
                    getPlacements();
                }

            }
        });

//        tswipe_refresh_layout.setRefreshing(true);
//        new GetUnreadCountOfNotificationAndPlacement().execute();
////        new GetUnreadMessagesCount().execute();


        getNotifications();

    }

    void filterNotifications(String text)
    {
        tempListNotification = new ArrayList();
        for(RecyclerItem d: itemListNotification){

            if(containsIgnoreCase(d.getTitle(),text)){
                tempListNotification.add(d);
            }
        }
        mAdapterNotification.updateList(tempListNotification,text);
    }
    void filterPlacements(String text)
    {
        tempListPlacement = new ArrayList();
        for(RecyclerItemPlacement d: itemListPlacement){

            if(containsIgnoreCase(d.getCompanyname(),text)){
                tempListPlacement.add(d);
            }
        }
        mAdapterPlacement.updateList(tempListPlacement,text);
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new NewsFeedFragment(), "News Feed");
        adapter.addFrag(new VideosFragment(), "Videos");
        adapter.addFrag(new ResumeTemplatesFragment(), "Resume Templates");
        adapter.addFrag(new NoDataAvailableFragment(), "Interview Questions");
        adapter.addFrag(new NoDataAvailableFragment(), "Ebooks");
        adapter.addFrag(new NoDataAvailableFragment(), "Question Sets");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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
    void loginFirebase(String username,String hash)
    {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username,hash)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Successfully logged in to Firebase from mainactivity", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(MainActivity.this, "Failed to login to Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void updateUnreadMessageCount(int readCount)
    {
        unreadMessageCount-=readCount;
        messagecountrl.setVisibility(View.VISIBLE);
        messagecount.setText(unreadMessageCount+"");
        if(unreadMessageCount<=0)
        {
            messagecountrl.setVisibility(View.GONE);
        }
    }
//    class GetUnreadMessagesCount extends AsyncTask<String, String, String> {
//
//
//        protected String doInBackground(String... param) {
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("u", username));
//            json = jParser.makeHttpRequest(url_get_chatrooms, "GET", params);
//
//            try {
//
//                count = Integer.parseInt(json.getString("count"));
//                sender_uid = json.getString("uid");
//
//                reciever_username=new String[count];
//                reciever_uid=new String[count];
//                unread_count=new String[count];
//
//                for(int i=0;i<count;i++)
//                {
//                    unread_count[i]="0";
//                    reciever_username[i]=json.getString("username"+i);
//                    reciever_uid[i]=json.getString("uid"+i);
//                }
//
//            } catch (Exception ex) {
//
//            }
//
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            if (count > 0) {
//
//                for (int i = 0; i < count; i++) {
//
//                    String tempusername = null;
//                    try {
//                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
//                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
//                        String sPadding = "ISO10126Padding";
//
//                        byte[] usernameBytes = reciever_username[i].getBytes("UTF-8");
//
//                        byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
//                        tempusername = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));
//
//
//
//
//                    } catch (Exception e) {
//                    }
//
//                    new GetMessagesReadStatus(username,tempusername,sender_uid, reciever_uid[i],i).execute();
//                }
//
//            }
//
//
//        }
//    }
//    class GetMessagesReadStatus extends AsyncTask<String, String, String> {
//
//        String sender, reciever, senderuid, recieveruid;
//        int index;
//
//        GetMessagesReadStatus(String sender, String reciever, String senderuid, String recieveruid, int index) {
//            this.sender = sender;
//            this.reciever = reciever;
//            this.senderuid = senderuid;
//            this.recieveruid = recieveruid;
//            this.index = index;
//        }
//
//        protected String doInBackground(String... param) {
//
//            String r = null;
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("s", sender));       //0
//            params.add(new BasicNameValuePair("r", reciever));     //1
//            params.add(new BasicNameValuePair("su", senderuid));    //2
//            params.add(new BasicNameValuePair("ru", recieveruid));  //3
//
//            try {
//
//                json = jParser.makeHttpRequest(url_getmessagesreadstatus, "GET", params);
//                unread_count[index] = json.getString("unreadcount");
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return r;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            if (index == count - 1) {
//
//                for (int i = 0; i < count; i++) {
//
//                    unreadMessageCount+=Integer.parseInt(unread_count[i]);
//
//                }
//                messagecountrl.setVisibility(View.VISIBLE);
//                messagecount.setText(unreadMessageCount+"");
//                if(unreadMessageCount==0)
//                {
//                    messagecountrl.setVisibility(View.GONE);
//                }
//            }
//        }
//    }
    class GetUnreadCountOfNotificationAndPlacement extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {

                json = jParser.makeHttpRequest(url_getnotificationsmetadata, "GET", params);
                unreadcountNotification = Integer.parseInt(json.getString("unreadcount"));
                json = jParser.makeHttpRequest(url_getplacementsmetadata, "GET", params);
                unreadcountPlacement = Integer.parseInt(json.getString("unreadcount"));


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            placementcountrl.setVisibility(View.VISIBLE);
            placementcounttxt.setText(unreadcountPlacement+"");
            if(unreadcountPlacement==0)
            {
                placementcountrl.setVisibility(View.GONE);
            }
            notificationcountrl.setVisibility(View.VISIBLE);
            notificationcounttxt.setText(unreadcountNotification+"");
            if(unreadcountNotification==0)
            {
                notificationcountrl.setVisibility(View.GONE);
            }

            getNotifications();

        }
    }

    void getNotifications()
    {
        tswipe_refresh_layout.setRefreshing(true);
        previousTotalNotification = 0;
        loadingNotification = true;
        page_to_call_notification=1;
        isFirstRunNotification=true;
        isLastPageLoadedNotification=false;
        lastPageFlagNotification=0;
        new GetNotificationsReadStatus().execute();
    }
    void getPlacements()
    {
        tswipe_refresh_layout.setRefreshing(true);
        previousTotalPlacement = 0;
        loadingPlacement = true;
        page_to_call_placement=1;
        isFirstRunPlacement=true;
        isLastPageLoadedPlacement=false;
        lastPageFlagPlacement=0;
        new GetPlacementsReadStatus().execute();
    }
    private void simulateLoadingNotification() {
        new AsyncTask<Void, Void, Void>() {
            @Override protected void onPreExecute() {
                tswipe_refresh_layout.setRefreshing(true);
            }

            @Override protected Void doInBackground(Void... param) {
                try {


                    if(page_to_call_notification<notificationpages)
                        page_to_call_notification++;



                    if(page_to_call_notification!=notificationpages) {
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
                    else
                    {
                        if(!isLastPageLoadedNotification)
                        {

                            lastPageFlagNotification=1;

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

            @Override protected void onPostExecute(Void param) {


                if(!isLastPageLoadedNotification) {

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
    private void simulateLoadingPlacement() {
        new AsyncTask<Void, Void, Void>() {
            @Override protected void onPreExecute() {
                tswipe_refresh_layout.setRefreshing(true);
            }

            @Override protected Void doInBackground(Void... param) {

                try {
                    if (page_to_call_placement < placementpages)
                        page_to_call_placement++;

                    if (page_to_call_placement != placementpages) {

                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("u", username));
                        params.add(new BasicNameValuePair("p", page_to_call_placement + ""));
                        json = jParser.makeHttpRequest(url_getplacements, "GET", params);

                        placementcount = Integer.parseInt(json.getString("count"));
                        placementids=new String[placementcount];
                        placementcompanyname=new String[placementcount];
                        placementcpackage=new String[placementcount];
                        placementpost=new String[placementcount];
                        placementforwhichcourse=new String[placementcount];
                        placementforwhichstream=new String[placementcount];
                        placementvacancies=new String[placementcount];
                        placementlastdateofregistration=new String[placementcount];
                        placementdateofarrival=new String[placementcount];
                        placementbond=new String[placementcount];
                        placementnoofapti=new String[placementcount];
                        placementnooftechtest=new String[placementcount];
                        placementnoofgd=new String[placementcount];
                        placementnoofti=new String[placementcount];
                        placementnoofhri=new String[placementcount];
                        placementstdx=new String[placementcount];
                        placementstdxiiordiploma=new String[placementcount];
                        placementug=new String[placementcount];
                        placementpg=new String[placementcount];
                        placementuploadtime=new String[placementcount];
                        placementlastmodified=new String[placementcount];
                        placementuploadedby=new String[placementcount];
                        placementuploadedbyplain=new String[placementcount];
                        placementnoofallowedliveatkt=new String[placementcount];
                        placementnoofalloweddeadatkt=new String[placementcount];

                        for(int i=0;i<placementcount;i++) {

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
                    else
                    {
                        if(!isLastPageLoadedPlacement) {

                            lastPageFlagPlacement = 1;

                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("u", username));
                            params.add(new BasicNameValuePair("p", page_to_call_placement + ""));
                            json = jParser.makeHttpRequest(url_getplacements, "GET", params);

                            placementcount = Integer.parseInt(json.getString("count"));

                            placementids=new String[placementcount];
                            placementcompanyname=new String[placementcount];
                            placementcpackage=new String[placementcount];
                            placementpost=new String[placementcount];
                            placementforwhichcourse=new String[placementcount];
                            placementforwhichstream=new String[placementcount];
                            placementvacancies=new String[placementcount];
                            placementlastdateofregistration=new String[placementcount];
                            placementdateofarrival=new String[placementcount];
                            placementbond=new String[placementcount];
                            placementnoofapti=new String[placementcount];
                            placementnooftechtest=new String[placementcount];
                            placementnoofgd=new String[placementcount];
                            placementnoofti=new String[placementcount];
                            placementnoofhri=new String[placementcount];
                            placementstdx=new String[placementcount];
                            placementstdxiiordiploma=new String[placementcount];
                            placementug=new String[placementcount];
                            placementpg=new String[placementcount];
                            placementuploadtime=new String[placementcount];
                            placementlastmodified=new String[placementcount];
                            placementuploadedby=new String[placementcount];
                            placementuploadedbyplain=new String[placementcount];
                            placementnoofallowedliveatkt=new String[placementcount];
                            placementnoofalloweddeadatkt=new String[placementcount];

                            for(int i=0;i<placementcount;i++) {

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
                }catch (Exception e){}
                return null;
            }

            @Override protected void onPostExecute(Void param) {


                tswipe_refresh_layout.setVisibility(View.VISIBLE);
                tswipe_refresh_layout.setRefreshing(false);
                if(!isLastPageLoadedPlacement)
                    for(int i=0;i<placementcount;i++)
                        try
                        {
                            if(placementcompanyname[i]!=null)
                            {
                                byte[] placementcompanynameEncryptedBytes=SimpleBase64Encoder.decode(placementcompanyname[i]);
                                byte[] placementcompanynameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementcompanynameEncryptedBytes);
                                placementcompanyname[i]=new String(placementcompanynameDecryptedBytes);

                            }
                            if(placementcpackage[i]!=null)
                            {
                                byte[] placementcpackageEncryptedBytes=SimpleBase64Encoder.decode(placementcpackage[i]);
                                byte[] placementcpackageDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementcpackageEncryptedBytes);
                                placementcpackage[i]=new String(placementcpackageDecryptedBytes);
                            }
                            if(placementpost[i]!=null)
                            {
                                byte[] placementpostEncryptedBytes=SimpleBase64Encoder.decode(placementpost[i]);
                                byte[] placementpostDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementpostEncryptedBytes);
                                placementpost[i]=new String(placementpostDecryptedBytes);
                            }
                            if(placementforwhichcourse[i]!=null)
                            {
                                byte[] placementforwhichcourseEncryptedBytes=SimpleBase64Encoder.decode(placementforwhichcourse[i]);
                                byte[] placementforwhichcourseDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementforwhichcourseEncryptedBytes);
                                placementforwhichcourse[i]=new String(placementforwhichcourseDecryptedBytes);
                            }
                            if(placementforwhichstream[i]!=null)
                            {
                                byte[] placementforwhichstreamEncryptedBytes=SimpleBase64Encoder.decode(placementforwhichstream[i]);
                                byte[] placementforwhichstreamDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementforwhichstreamEncryptedBytes);
                                placementforwhichstream[i]=new String(placementforwhichstreamDecryptedBytes);
                            }
                            if(placementvacancies[i]!=null)
                            {
                                byte[] placementvacanciesEncryptedBytes=SimpleBase64Encoder.decode(placementvacancies[i]);
                                byte[] placementvacanciesDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementvacanciesEncryptedBytes);
                                placementvacancies[i]=new String(placementvacanciesDecryptedBytes);
                            }
                            if(placementlastdateofregistration[i]!=null)
                            {
                                byte[] placementlastdateofregistrationEncryptedBytes=SimpleBase64Encoder.decode(placementlastdateofregistration[i]);
                                byte[] placementlastdateofregistrationDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementlastdateofregistrationEncryptedBytes);
                                placementlastdateofregistration[i]=new String(placementlastdateofregistrationDecryptedBytes);
                            }
                            if(placementdateofarrival[i]!=null)
                            {
                                byte[] placementdateofarrivalEncryptedBytes=SimpleBase64Encoder.decode(placementdateofarrival[i]);
                                byte[] placementdateofarrivalDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementdateofarrivalEncryptedBytes);
                                placementdateofarrival[i]=new String(placementdateofarrivalDecryptedBytes);
                            }
                            if(placementbond[i]!=null)
                            {
                                byte[] placementbondEncryptedBytes=SimpleBase64Encoder.decode(placementbond[i]);
                                byte[] placementbondDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementbondEncryptedBytes);
                                placementbond[i]=new String(placementbondDecryptedBytes);
                            }
                            if(placementnoofapti[i]!=null)
                            {
                                byte[] placementnoofaptiEncryptedBytes=SimpleBase64Encoder.decode(placementnoofapti[i]);
                                byte[] placementnoofaptiDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofaptiEncryptedBytes);
                                placementnoofapti[i]=new String(placementnoofaptiDecryptedBytes);
                            }
                            if(placementnooftechtest[i]!=null)
                            {
                                byte[] placementnooftechtestEncryptedBytes=SimpleBase64Encoder.decode(placementnooftechtest[i]);
                                byte[] placementnooftechtestDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnooftechtestEncryptedBytes);
                                placementnooftechtest[i]=new String(placementnooftechtestDecryptedBytes);
                            }
                            if(placementnoofgd[i]!=null)
                            {
                                byte[] placementnoofgdEncryptedBytes=SimpleBase64Encoder.decode(placementnoofgd[i]);
                                byte[] placementnoofgdDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofgdEncryptedBytes);
                                placementnoofgd[i]=new String(placementnoofgdDecryptedBytes);
                            }
                            if(placementnoofti[i]!=null)
                            {
                                byte[] placementnooftiEncryptedBytes=SimpleBase64Encoder.decode(placementnoofti[i]);
                                byte[] placementnooftiDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnooftiEncryptedBytes);
                                placementnoofti[i]=new String(placementnooftiDecryptedBytes);
                            }
                            if(placementnoofhri[i]!=null)
                            {
                                byte[] placementnoofhriEncryptedBytes=SimpleBase64Encoder.decode(placementnoofhri[i]);
                                byte[] placementnoofhriDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofhriEncryptedBytes);
                                placementnoofhri[i]=new String(placementnoofhriDecryptedBytes);
                            }
                            if(placementstdx[i]!=null)
                            {
                                byte[] placementstdxEncryptedBytes=SimpleBase64Encoder.decode(placementstdx[i]);
                                byte[] placementstdxDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementstdxEncryptedBytes);
                                placementstdx[i]=new String(placementstdxDecryptedBytes);
                            }
                            if(placementstdxiiordiploma[i]!=null)
                            {
                                byte[] placementstdxiiordiplomaEncryptedBytes=SimpleBase64Encoder.decode(placementstdxiiordiploma[i]);
                                byte[] placementstdxiiordiplomaDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementstdxiiordiplomaEncryptedBytes);
                                placementstdxiiordiploma[i]=new String(placementstdxiiordiplomaDecryptedBytes);
                            }
                            if(placementug[i]!=null)
                            {
                                byte[] placementugEncryptedBytes=SimpleBase64Encoder.decode(placementug[i]);
                                byte[] placementugDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementugEncryptedBytes);
                                placementug[i]=new String(placementugDecryptedBytes);
                            }
                            if(placementpg[i]!=null)
                            {
                                byte[] placementpgEncryptedBytes=SimpleBase64Encoder.decode(placementpg[i]);
                                byte[] placementpgDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementpgEncryptedBytes);
                                placementpg[i]=new String(placementpgDecryptedBytes);
                            }
                            if(placementuploadtime[i]!=null)
                            {
                                byte[] placementuploadtimeEncryptedBytes=SimpleBase64Encoder.decode(placementuploadtime[i]);
                                byte[] placementuploadtimeDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementuploadtimeEncryptedBytes);
                                placementuploadtime[i]=new String(placementuploadtimeDecryptedBytes);
                            }
                            if(placementlastmodified[i]!=null)
                            {
                                byte[] placementlastmodifiedEncryptedBytes=SimpleBase64Encoder.decode(placementlastmodified[i]);
                                byte[] placementlastmodifiedDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementlastmodifiedEncryptedBytes);
                                placementlastmodified[i]=new String(placementlastmodifiedDecryptedBytes);
                            }
                            if(placementuploadedby[i]!=null)
                            {
                                byte[] placementuploadedbyEncryptedBytes=SimpleBase64Encoder.decode(placementuploadedby[i]);
                                byte[] placementuploadedbyDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementuploadedbyEncryptedBytes);
                                placementuploadedbyplain[i]=new String(placementuploadedbyDecryptedBytes);
                            }
                            if(placementnoofallowedliveatkt[i]!=null)
                            {
                                byte[] placementnoofallowedliveatktEncryptedBytes=SimpleBase64Encoder.decode(placementnoofallowedliveatkt[i]);
                                byte[] placementnoofallowedliveatktDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofallowedliveatktEncryptedBytes);
                                placementnoofallowedliveatkt[i]=new String(placementnoofallowedliveatktDecryptedBytes);
                            }
                            if(placementnoofalloweddeadatkt[i]!=null)
                            {
                                byte[] placementnoofalloweddeadatktEncryptedBytes=SimpleBase64Encoder.decode(placementnoofalloweddeadatkt[i]);
                                byte[] placementnoofalloweddeadatktDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofalloweddeadatktEncryptedBytes);
                                placementnoofalloweddeadatkt[i]=new String(placementnoofalloweddeadatktDecryptedBytes);
                            }

                        }catch (Exception e){
                            //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                new GetLastUpdatedPlacement().execute();
            }
        }.execute();
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
    class GetNotificationsReadStatus extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0

            try {

                json = jParser.makeHttpRequest(url_getnotificationsmetadata, "GET", params);
                notificationpages = Integer.parseInt(json.getString("pages"));
                called_pages_notification=new int[notificationpages];
                total_no_of_notifications = Integer.parseInt(json.getString("count"));
                unreadcountNotification = Integer.parseInt(json.getString("unreadcount"));

                json = jParser.makeHttpRequest(url_getnotificationsreadstatus, "GET", params);

                readstatuscountNotification  = Integer.parseInt(json.getString("count"));
                notificationreadstatus=new String[readstatuscountNotification ];
                for(int i=0;i<readstatuscountNotification ;i++)
                {
                    notificationreadstatus[i]=json.getString("s"+i);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            notificationcountrl.setVisibility(View.VISIBLE);
            notificationcounttxt.setText(unreadcountNotification+"");
            if(unreadcountNotification==0)
            {
                notificationcountrl.setVisibility(View.GONE);
            }
            new GetNotifications().execute();

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
                called_pages_placement=new int[placementpages];
                total_no_of_placements = Integer.parseInt(json.getString("count"));
                unreadcountPlacement = Integer.parseInt(json.getString("unreadcount"));
                json = jParser.makeHttpRequest(url_getplacementsreadstatus, "GET", params);
                readstatuscountPlacement = Integer.parseInt(json.getString("count"));
                placementreadstatus=new String[readstatuscountPlacement];
                for(int i=0;i<readstatuscountPlacement;i++)
                {
                    placementreadstatus[i]=json.getString("s"+i);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            placementcountrl.setVisibility(View.VISIBLE);
            placementcounttxt.setText(unreadcountPlacement+"");
            if(unreadcountPlacement==0)
            {
                placementcountrl.setVisibility(View.GONE);
            }

            new GetPlacements().execute();

        }
    }
    class GetNotifications extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("p",page_to_call_notification+""));
            json = jParser.makeHttpRequest(url_getnotifications, "GET", params);
            try {
                notificationcount = Integer.parseInt(json.getString("count"));

                notificationids=new String[notificationcount];
                notificationtitles=new String[notificationcount];
                notificationnotifications=new String[notificationcount];
                notificationfilename1=new String[notificationcount];
                notificationfilename2=new String[notificationcount];
                notificationfilename3=new String[notificationcount];
                notificationfilename4=new String[notificationcount];
                notificationfilename5=new String[notificationcount];
                notificationuploadtime=new String[notificationcount];
                notificationlastmodified=new String[notificationcount];
                notificationuploadedby=new String[notificationcount];
                notificationuploadedbyplain=new String[notificationcount];
                for(int i=0;i<notificationcount;i++)
                {
                    notificationids[i]=json.getString("id"+i);
                    notificationtitles[i]=json.getString("title"+i);
                    notificationnotifications[i]=json.getString("notification"+i);
                    notificationfilename1[i]=json.getString("filename1"+i);
                    notificationfilename2[i]=json.getString("filename2"+i);
                    notificationfilename3[i]=json.getString("filename3"+i);
                    notificationfilename4[i]=json.getString("filename4"+i);
                    notificationfilename5[i]=json.getString("filename5"+i);
                    notificationuploadtime[i]=json.getString("uploadtime"+i);
                    notificationlastmodified[i]=json.getString("lastmodified"+i);
                    notificationuploadedby[i]=json.getString("uploadedby"+i);

                }



            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            tswipe_refresh_layout.setVisibility(View.VISIBLE);
            tswipe_refresh_layout.setRefreshing(false);


            for(int i=0;i<notificationcount;i++)
                try
                {

                    if(notificationtitles[i]!=null)
                    {
                        byte[] notificationtitlesEncryptedBytes=SimpleBase64Encoder.decode(notificationtitles[i]);
                        byte[] notificationtitlesDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationtitlesEncryptedBytes);
                        notificationtitles[i]=new String(notificationtitlesDecryptedBytes);
                    }
                    if(notificationnotifications[i]!=null)
                    {
                        byte[] notificationnotificationsEncryptedBytes=SimpleBase64Encoder.decode(notificationnotifications[i]);
                        byte[] notificationnotificationsDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationnotificationsEncryptedBytes);
                        notificationnotifications[i]=new String(notificationnotificationsDecryptedBytes);
                    }
                    if(notificationfilename1[i]!=null)
                    {
                        if(!notificationfilename1[i].equals("null")) {
                            byte[] notificationfilename1EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename1[i]);
                            byte[] notificationfilename1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename1EncryptedBytes);
                            notificationfilename1[i] = new String(notificationfilename1DecryptedBytes);
                        }
                    }
                    if(notificationfilename2[i]!=null)
                    {
                        if(!notificationfilename2[i].equals("null")) {
                            byte[] notificationfilename2EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename2[i]);
                            byte[] notificationfilename2DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename2EncryptedBytes);
                            notificationfilename2[i] = new String(notificationfilename2DecryptedBytes);
                        }
                    }
                    if(notificationfilename3[i]!=null)
                    {
                        if(!notificationfilename3[i].equals("null")) {
                            byte[] notificationfilename3EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename3[i]);
                            byte[] notificationfilename3DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename3EncryptedBytes);
                            notificationfilename3[i] = new String(notificationfilename3DecryptedBytes);
                        }
                    }
                    if(notificationfilename4[i]!=null)
                    {
                        if(!notificationfilename4[i].equals("null")) {
                            byte[] notificationfilename4EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename4[i]);
                            byte[] notificationfilename4DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename4EncryptedBytes);
                            notificationfilename4[i] = new String(notificationfilename4DecryptedBytes);
                        }
                    }
                    if(notificationfilename5[i]!=null)
                    {
                        if(!notificationfilename5[i].equals("null")) {
                            byte[] notificationfilename5EncryptedBytes = SimpleBase64Encoder.decode(notificationfilename5[i]);
                            byte[] notificationfilename5DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationfilename5EncryptedBytes);
                            notificationfilename5[i] = new String(notificationfilename5DecryptedBytes);
                        }
                    }
                    if(notificationuploadtime[i]!=null)
                    {
                        byte[] notificationuploadtimeEncryptedBytes=SimpleBase64Encoder.decode(notificationuploadtime[i]);
                        byte[] notificationuploadtimeDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationuploadtimeEncryptedBytes);
                        notificationuploadtime[i]=new String(notificationuploadtimeDecryptedBytes);
                    }
                    if(notificationlastmodified[i]!=null)
                    {
                        byte[] notificationlastmodifiedEncryptedBytes=SimpleBase64Encoder.decode(notificationlastmodified[i]);
                        byte[] notificationlastmodifiedDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationlastmodifiedEncryptedBytes);
                        notificationlastmodified[i]=new String(notificationlastmodifiedDecryptedBytes);
                    }
                    if(notificationuploadedby[i]!=null)
                    {
                        byte[] notificationuploadedbyEncryptedBytes=SimpleBase64Encoder.decode(notificationuploadedby[i]);
                        byte[] notificationuploadedbyDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, notificationuploadedbyEncryptedBytes);
                        notificationuploadedbyplain[i]=new String(notificationuploadedbyDecryptedBytes);
                    }



                }catch (Exception e){
                    //Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }



            new GetLastUpdatedNotification().execute();

        }
    }
    class GetPlacements extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r=null;

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("p",page_to_call_placement+""));
            json = jParser.makeHttpRequest(url_getplacements, "GET", params);
            try
            {
                placementcount = Integer.parseInt(json.getString("count"));

                placementids=new String[placementcount];
                placementcompanyname=new String[placementcount];
                placementcpackage=new String[placementcount];
                placementpost=new String[placementcount];
                placementforwhichcourse=new String[placementcount];
                placementforwhichstream=new String[placementcount];
                placementvacancies=new String[placementcount];
                placementlastdateofregistration=new String[placementcount];
                placementdateofarrival=new String[placementcount];
                placementbond=new String[placementcount];
                placementnoofapti=new String[placementcount];
                placementnooftechtest=new String[placementcount];
                placementnoofgd=new String[placementcount];
                placementnoofti=new String[placementcount];
                placementnoofhri=new String[placementcount];
                placementstdx=new String[placementcount];
                placementstdxiiordiploma=new String[placementcount];
                placementug=new String[placementcount];
                placementpg=new String[placementcount];
                placementuploadtime=new String[placementcount];
                placementlastmodified=new String[placementcount];
                placementuploadedby=new String[placementcount];
                placementuploadedbyplain=new String[placementcount];
                placementnoofallowedliveatkt=new String[placementcount];
                placementnoofalloweddeadatkt=new String[placementcount];

                for(int i=0;i<placementcount;i++) {

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


            }catch (Exception e){}

            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            tswipe_refresh_layout.setVisibility(View.VISIBLE);
            tswipe_refresh_layout.setRefreshing(false);

            for(int i=0;i<placementcount;i++)
                try
                {
//                    if(placementcompanyname[i]!=null)
//                    {
//                        byte[] placementcompanynameEncryptedBytes=SimpleBase64Encoder.decode(placementcompanyname[i]);
//                        byte[] placementcompanynameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementcompanynameEncryptedBytes);
//                        placementcompanyname[i]=new String(placementcompanynameDecryptedBytes);
////                        Log.d("TAG", "onPostExecute: placementcompanyname"+placementcompanyname[i]);
//                    }
//                    if(placementcpackage[i]!=null)
//                    {
//                        byte[] placementcpackageEncryptedBytes=SimpleBase64Encoder.decode(placementcpackage[i]);
//                        byte[] placementcpackageDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementcpackageEncryptedBytes);
//                        placementcpackage[i]=new String(placementcpackageDecryptedBytes);
//                    }
//                    if(placementpost[i]!=null)
//                    {
//                        byte[] placementpostEncryptedBytes=SimpleBase64Encoder.decode(placementpost[i]);
//                        byte[] placementpostDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementpostEncryptedBytes);
//                        placementpost[i]=new String(placementpostDecryptedBytes);
//                    }
//                    if(placementforwhichcourse[i]!=null)
//                    {
//                        byte[] placementforwhichcourseEncryptedBytes=SimpleBase64Encoder.decode(placementforwhichcourse[i]);
//                        byte[] placementforwhichcourseDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementforwhichcourseEncryptedBytes);
//                        placementforwhichcourse[i]=new String(placementforwhichcourseDecryptedBytes);
//                    }
//                    if(placementforwhichstream[i]!=null)
//                    {
//                        byte[] placementforwhichstreamEncryptedBytes=SimpleBase64Encoder.decode(placementforwhichstream[i]);
//                        byte[] placementforwhichstreamDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementforwhichstreamEncryptedBytes);
//                        placementforwhichstream[i]=new String(placementforwhichstreamDecryptedBytes);
//                    }
//                    if(placementvacancies[i]!=null)
//                    {
//                        byte[] placementvacanciesEncryptedBytes=SimpleBase64Encoder.decode(placementvacancies[i]);
//                        byte[] placementvacanciesDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementvacanciesEncryptedBytes);
//                        placementvacancies[i]=new String(placementvacanciesDecryptedBytes);
//                    }
//                    if(placementlastdateofregistration[i]!=null)
//                    {
//                        byte[] placementlastdateofregistrationEncryptedBytes=SimpleBase64Encoder.decode(placementlastdateofregistration[i]);
//                        byte[] placementlastdateofregistrationDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementlastdateofregistrationEncryptedBytes);
//                        placementlastdateofregistration[i]=new String(placementlastdateofregistrationDecryptedBytes);
//                    }
//                    if(placementdateofarrival[i]!=null)
//                    {
//                        byte[] placementdateofarrivalEncryptedBytes=SimpleBase64Encoder.decode(placementdateofarrival[i]);
//                        byte[] placementdateofarrivalDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementdateofarrivalEncryptedBytes);
//                        placementdateofarrival[i]=new String(placementdateofarrivalDecryptedBytes);
//                    }
//                    if(placementbond[i]!=null)
//                    {
//                        byte[] placementbondEncryptedBytes=SimpleBase64Encoder.decode(placementbond[i]);
//                        byte[] placementbondDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementbondEncryptedBytes);
//                        placementbond[i]=new String(placementbondDecryptedBytes);
//                    }
//                    if(placementnoofapti[i]!=null)
//                    {
//                        byte[] placementnoofaptiEncryptedBytes=SimpleBase64Encoder.decode(placementnoofapti[i]);
//                        byte[] placementnoofaptiDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofaptiEncryptedBytes);
//                        placementnoofapti[i]=new String(placementnoofaptiDecryptedBytes);
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
                    if(placementuploadedby[i]!=null)
                    {
                        byte[] placementuploadedbyEncryptedBytes=SimpleBase64Encoder.decode(placementuploadedby[i]);
                        byte[] placementuploadedbyDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementuploadedbyEncryptedBytes);
                        placementuploadedbyplain[i]=new String(placementuploadedbyDecryptedBytes);
                    }
//                    if(placementnoofallowedliveatkt[i]!=null)
//                    {
//                        byte[] placementnoofallowedliveatktEncryptedBytes=SimpleBase64Encoder.decode(placementnoofallowedliveatkt[i]);
//                        byte[] placementnoofallowedliveatktDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofallowedliveatktEncryptedBytes);
//                        placementnoofallowedliveatkt[i]=new String(placementnoofallowedliveatktDecryptedBytes);
//                    }
//                    if(placementnoofalloweddeadatkt[i]!=null)
//                    {
//                        byte[] placementnoofalloweddeadatktEncryptedBytes=SimpleBase64Encoder.decode(placementnoofalloweddeadatkt[i]);
//                        byte[] placementnoofalloweddeadatktDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, placementnoofalloweddeadatktEncryptedBytes);
//                        placementnoofalloweddeadatkt[i]=new String(placementnoofalloweddeadatktDecryptedBytes);
//                    }

                }catch (Exception e){
                    //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            new GetLastUpdatedPlacement().execute();
        }
    }
    class GetLastUpdatedNotification extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r=null;


            Set<String> uniqKeys = new TreeSet<String>();
            uniqKeys.addAll(Arrays.asList(notificationuploadedbyplain));

            uniqueUploadersNotification  = uniqKeys.toArray(new String[uniqKeys.size()]);
            uniqueUploadersEncNotification =new String[uniqueUploadersNotification .length];
            lastupdatedNotification =new String[uniqueUploadersNotification .length];
            for(int j=0;j<uniqueUploadersNotification .length;j++)
            {
                for(int i=0;i<notificationcount;i++) {

                    if (notificationuploadedbyplain[i].equals(uniqueUploadersNotification [j]))
                    {
                        uniqueUploadersEncNotification [j]=notificationuploadedby[i];
                    }
                }
            }
            for(int i=0;i<uniqueUploadersNotification .length;i++) {
                // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+notificationuploadedby[i] , Toast.LENGTH_SHORT).show();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u",uniqueUploadersEncNotification [i]));       //0
                json = jParser.makeHttpRequest(url_getlastupdated, "GET", params);
                try {
                    String s = json.getString("lastupdated");
                    if(s.equals("noupdate"))
                    {
                        // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+s , Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        lastupdatedNotification [i]=s;
                        // Toast.makeText(MainActivity.this,notificationuploadedbyplain[i]+"\n"+s , Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){}
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
//            for(int i=0;i<lastupdated.length;i++)
//            {
//                if(lastupdated[i]==null) {
//                 //   Toast.makeText(MainActivity.this, uniqueUploaders[i] + "\n nulla it is", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(MainActivity.this, uniqueUploaders[i] + "\n" + lastupdated[i], Toast.LENGTH_SHORT).show();
//
//
//                }
//            }

            if(!isLastPageLoadedNotification)
                addNotificationdatatoAdapter();

        }

    }
    class GetLastUpdatedPlacement extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r=null;

            Set<String> uniqKeys = new TreeSet<String>();
            uniqKeys.addAll(Arrays.asList(placementuploadedbyplain));

            uniqueUploadersPlacement= uniqKeys.toArray(new String[uniqKeys.size()]);
            uniqueUploadersEncPlacement=new String[uniqueUploadersPlacement.length];
            lastupdatedPlacement=new String[uniqueUploadersPlacement.length];

            for(int j=0;j<uniqueUploadersPlacement.length;j++)
            {
                for(int i=0;i<placementcount;i++) {

                    if (placementuploadedbyplain[i].equals(uniqueUploadersPlacement[j]))
                    {
                        uniqueUploadersEncPlacement[j]=placementuploadedby[i];
                    }
                }
            }

            for(int i=0;i<uniqueUploadersPlacement.length;i++) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u",uniqueUploadersEncPlacement[i]));       //0
                json = jParser.makeHttpRequest(url_getlastupdated, "GET", params);
                try {
                    String s = json.getString("lastupdated");
                    if(s.equals("noupdate"))
                    {
                    }
                    else
                    {
                        lastupdatedPlacement[i]=s;

                    }

                }catch (Exception e){}
            }


            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(!isLastPageLoadedPlacement)
                addPlacementdatatoAdapter();

        }

    }

    void addNotificationdatatoAdapter() {

        if (isFirstRunNotification) {
            itemListNotification.clear();
            mAdapterNotification.notifyDataSetChanged();
            isFirstRunNotification = false;
        }

        if(!isLastPageLoadedNotification)
        {

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
                for (int j = 0; j < readstatuscountNotification ; j++) {
                    String idnstatus = notificationreadstatus[j];
                    String sid = "";
                    if (idnstatus.contains("U")) {

                        for (int k = 0; k < idnstatus.length() - 1; k++) {
                            sid += idnstatus.charAt(k);
                        }
                        if (sid.equals(notificationids[i])) {
                            for (int k = 0; k < uniqueUploadersNotification .length; k++) {

                                if (notificationuploadedbyplain[i].equals(uniqueUploadersNotification [k])) {
                                    if (lastupdatedNotification [k] == null) {

                                        if (notificationfilename1[i].equals("null")) {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i],notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i],outputDate,notificationlastmodified[i], false, false, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, false, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i] ,notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, false, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, false, notificationuploadedby[i], MainActivity.this, "placeme");

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, false, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, false, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, false, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, false, notificationuploadedby[i], MainActivity.this, "placeme");

                                            itemListNotification.add(item);
                                        }
                                    } else {
                                        if (notificationfilename1[i].equals("null")) {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, false, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, false, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, false, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, false, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, false, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, false, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, false, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, false, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);

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
                            for (int k = 0; k < uniqueUploadersNotification .length; k++) {

                                if (notificationuploadedbyplain[i].equals(uniqueUploadersNotification [k])) {


                                    if (lastupdatedNotification [k] == null) {

                                        if (notificationfilename1[i].equals("null")) {

                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, true, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, true, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, true, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, true, notificationuploadedby[i], MainActivity.this, "placeme");

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, true, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, true, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, true, notificationuploadedby[i], MainActivity.this, "placeme");
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, true, notificationuploadedby[i], MainActivity.this, "placeme");

                                            itemListNotification.add(item);
                                        }
                                    } else {
                                        if (notificationfilename1[i].equals("null")) {

                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, true, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, true, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, true, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], false, true, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);

                                            itemListNotification.add(item);
                                        } else {
                                            if (largeheadingflag == 1 && largenotificationflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, true, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else if (largenotificationflag == 1 && largeheadingflag == 0)
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationtoshow,notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, true, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else if (largenotificationflag == 0 && largeheadingflag == 1)
                                                item = new RecyclerItem(notificationids[i], headingtoshow,notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, true, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);
                                            else
                                                item = new RecyclerItem(notificationids[i], notificationtitles[i],notificationtitles[i], notificationnotifications[i],notificationnotifications[i],notificationfilename1[i],notificationfilename2[i],notificationfilename3[i],notificationfilename4[i],notificationfilename5[i], outputDate,notificationlastmodified[i], true, true, notificationuploadedby[i], MainActivity.this, lastupdatedNotification [k]);

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


        if(lastPageFlagNotification==1)
            isLastPageLoadedNotification=true;

        mAdapterNotification.notifyDataSetChanged();

    }

    void addPlacementdatatoAdapter()
    {
        if (isFirstRunPlacement) {
            itemListPlacement.clear();
            mAdapterPlacement.notifyDataSetChanged();
            isFirstRunPlacement = false;
        }
        selectedMenuFlag=2;
        if(!isLastPageLoadedPlacement)
        {
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
                                            item = new RecyclerItemPlacement(placementids[i], companynametoshow, placementcpackage[i]+" LPA",placementpost[i],placementforwhichcourse[i],placementforwhichstream[i],placementvacancies[i],placementlastdateofregistration[i], placementdateofarrival[i],placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i],placementstdx[i],placementstdxiiordiploma[i],placementug[i],placementpg[i],placementuploadtime[i],placementlastmodified[i],placementuploadedby[i],false,MainActivity.this,"placeme",placementnoofallowedliveatkt[i],placementnoofalloweddeadatkt[i]);
                                        else
                                            item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i]+" LPA",placementpost[i],placementforwhichcourse[i],placementforwhichstream[i],placementvacancies[i],placementlastdateofregistration[i], placementdateofarrival[i],placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i],placementstdx[i],placementstdxiiordiploma[i],placementug[i],placementpg[i],placementuploadtime[i],placementlastmodified[i],placementuploadedby[i],false,MainActivity.this,"placeme",placementnoofallowedliveatkt[i],placementnoofalloweddeadatkt[i]);
                                        itemListPlacement.add(item);
                                    }
                                    else
                                    {
                                        if (largecompanynameflag == 1)
                                            item = new RecyclerItemPlacement(placementids[i], companynametoshow, placementcpackage[i]+" LPA",placementpost[i],placementforwhichcourse[i],placementforwhichstream[i],placementvacancies[i],placementlastdateofregistration[i], placementdateofarrival[i],placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i],placementstdx[i],placementstdxiiordiploma[i],placementug[i],placementpg[i],placementuploadtime[i],placementlastmodified[i],placementuploadedby[i],false,MainActivity.this,lastupdatedPlacement[k],placementnoofallowedliveatkt[i],placementnoofalloweddeadatkt[i]);
                                        else
                                            item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i]+" LPA",placementpost[i],placementforwhichcourse[i],placementforwhichstream[i],placementvacancies[i],placementlastdateofregistration[i], placementdateofarrival[i],placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i],placementstdx[i],placementstdxiiordiploma[i],placementug[i],placementpg[i],placementuploadtime[i],placementlastmodified[i],placementuploadedby[i],false,MainActivity.this,lastupdatedPlacement[k],placementnoofallowedliveatkt[i],placementnoofalloweddeadatkt[i]);
                                        itemListPlacement.add(item);
                                    }
                                }

                            }
                        }
                    }
                    else if (idnstatus.contains("R")) {
                        for (int k = 0; k < idnstatus.length() - 1; k++) {
                            sid += idnstatus.charAt(k);
                        }
                        if (sid.equals(placementids[i])) {
                            for (int k = 0; k < uniqueUploadersPlacement.length; k++) {
                                if (placementuploadedbyplain[i].equals(uniqueUploadersPlacement[k])) {
                                    if (lastupdatedPlacement[k] == null) {
                                        if (largecompanynameflag == 1)
                                            item = new RecyclerItemPlacement(placementids[i], companynametoshow, placementcpackage[i]+" LPA",placementpost[i],placementforwhichcourse[i],placementforwhichstream[i],placementvacancies[i],placementlastdateofregistration[i], placementdateofarrival[i],placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i],placementstdx[i],placementstdxiiordiploma[i],placementug[i],placementpg[i],placementuploadtime[i],placementlastmodified[i],placementuploadedby[i],true,MainActivity.this,"placeme",placementnoofallowedliveatkt[i],placementnoofalloweddeadatkt[i]);
                                        else
                                            item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i]+" LPA",placementpost[i],placementforwhichcourse[i],placementforwhichstream[i],placementvacancies[i],placementlastdateofregistration[i], placementdateofarrival[i],placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i],placementstdx[i],placementstdxiiordiploma[i],placementug[i],placementpg[i],placementuploadtime[i],placementlastmodified[i],placementuploadedby[i],true,MainActivity.this,"placeme",placementnoofallowedliveatkt[i],placementnoofalloweddeadatkt[i]);
                                        itemListPlacement.add(item);
                                    }
                                    else
                                    {
                                        if (largecompanynameflag == 1)
                                            item = new RecyclerItemPlacement(placementids[i], companynametoshow, placementcpackage[i]+" LPA",placementpost[i],placementforwhichcourse[i],placementforwhichstream[i],placementvacancies[i],placementlastdateofregistration[i], placementdateofarrival[i],placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i],placementstdx[i],placementstdxiiordiploma[i],placementug[i],placementpg[i],placementuploadtime[i],placementlastmodified[i],placementuploadedby[i],true,MainActivity.this,lastupdatedPlacement[k],placementnoofallowedliveatkt[i],placementnoofalloweddeadatkt[i]);
                                        else
                                            item = new RecyclerItemPlacement(placementids[i], placementcompanyname[i], placementcpackage[i]+" LPA",placementpost[i],placementforwhichcourse[i],placementforwhichstream[i],placementvacancies[i],placementlastdateofregistration[i], placementdateofarrival[i],placementbond[i], placementnoofapti[i], placementnooftechtest[i], placementnoofgd[i], placementnoofti[i], placementnoofhri[i],placementstdx[i],placementstdxiiordiploma[i],placementug[i],placementpg[i],placementuploadtime[i],placementlastmodified[i],placementuploadedby[i],true,MainActivity.this,lastupdatedPlacement[k],placementnoofallowedliveatkt[i],placementnoofalloweddeadatkt[i]);
                                        itemListPlacement.add(item);
                                    }
                                }

                            }
                        }

                    }

                }

            }
        }

        if(lastPageFlagPlacement==1)
            isLastPageLoadedPlacement=true;
        mAdapterPlacement.notifyDataSetChanged();

    }

    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
    }
    void changeReadStatusNotification(String id)
    {
        new ChangeReadStatusNotification().execute(id);

    }
    void changeReadStatusPlacement(String id)
    {
        new ChangeReadStatusPlacement().execute(id);

    }

    @Override
    public void onImagesChosen(List<ChosenImage> list) {
        final ChosenImage file=list.get(0);

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
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

    }

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
        } else {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce=false;
                    }
                }, 2000);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void requestProfileImage()
    {
        new GetProfileImage().execute();
    }

    public void requestCropImage()
    {
        resultView.setImageDrawable(null);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("digest1", digest1);
        editor.putString("digest2", digest2);
        editor.putString("plain", plainusername);
        editor.putString("crop", "yes");

        editor.commit();
        chooseImage();


    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("digest1", digest1);
        editor.putString("digest2", digest2);
        editor.putString("plain", plainusername);
        editor.commit();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        sharedpreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        plainusername=sharedpreferences.getString("plain",null);
        digest1=sharedpreferences.getString("digest1",null);
        digest2=sharedpreferences.getString("digest2",null);
        username=sharedpreferences.getString(Username,null);
        String role=sharedpreferences.getString("role",null);

        ProfileRole r=new ProfileRole();
        r.setUsername(username);
        r.setPlainusername(plainusername);
        r.setRole(role);

        Digest d=new Digest();
        d.setDigest1(digest1);
        d.setDigest2(digest2);
        if(requestCode== Picker.PICK_IMAGE_DEVICE) {

            try {

                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback(this);
                }
                imagePicker.submit(result);
                crop_layout.setVisibility(View.VISIBLE);
                tswipe_refresh_layout.setVisibility(View.GONE);
                mainfragment.setVisibility(View.GONE);
                crop_flag=1;
                beginCrop(result.getData());
                // Toast.makeText(this, "crop initiated", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                crop_layout.setVisibility(View.GONE);
                tswipe_refresh_layout.setVisibility(View.GONE);
                mainfragment.setVisibility(View.VISIBLE);
            }
        }
        else if(resultCode==RESULT_CANCELED)
        {
            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            crop_flag=0;
        }
        else if (requestCode == Crop.REQUEST_CROP) {
            // Toast.makeText(this, "cropped", Toast.LENGTH_SHORT).show();
            handleCrop(resultCode, result);
        }

        if(resultCode==STUDENT_DATA_CHANGE_RESULT_CODE)
        {
            MyProfileFragment fragment = (MyProfileFragment)getSupportFragmentManager().findFragmentById(R.id.mainfragment);
            fragment.refreshContent();
        }
    }


    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            File f=new File(getCacheDir(), "cropped");
            filepath=f.getAbsolutePath();

            filename="";
            int index=filepath.lastIndexOf("/");
            directory="";
            for(int i=0;i<index;i++)
                directory+=filepath.charAt(i);

            for(int i=index+1;i<filepath.length();i++)
                filename+=filepath.charAt(i);

            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            MyProfileFragment fragment = (MyProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
            fragment.showUpdateProgress();
            new UploadProfile().execute();

        } else if (resultCode == Crop.RESULT_ERROR) {
            crop_layout.setVisibility(View.GONE);
            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Try Again..!", Toast.LENGTH_SHORT).show();

        }
    }

    private void chooseImage() {

        imagePicker.pickImage();
    }



    public class GetProfileImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;

            return map;
        }
        @Override
        protected void onPostExecute(Bitmap result) {

            downloadImage();
        }



    }
    private void downloadImage() {

        String t = String.valueOf(System.currentTimeMillis());

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("192.168.100.100")
                .path("AESTest/GetImage")
                .appendQueryParameter("u", username)
                .build();

        Glide.with(this)
                .load(uri)
                .crossFade()
                .signature(new StringSignature(System.currentTimeMillis() + ""))
                .into(profile);

    }
    class UploadProfile extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            try {


                File sourceFile = new File(filepath);



                MultipartUtility multipart = new MultipartUtility(upload_profile, "UTF-8");

                multipart.addFormField("u", username);

                if(filename!="") {
                    multipart.addFormField("f", filename);
                    multipart.addFilePart("uf", sourceFile);
                }
                else
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

            if(response.get(0).contains("success")) {
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("crop", "no");
                editor.commit();
                Toast.makeText(MainActivity.this, "Successfully Updated..!", Toast.LENGTH_SHORT).show();
                requestProfileImage();
                MyProfileFragment fragment = (MyProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
                fragment.refreshContent();
                DeleteRecursive(new File(directory));
            }
            else if(response.get(0).contains("null"))
            {
                requestProfileImage();
                MyProfileFragment fragment = (MyProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
                fragment.refreshContent();
                Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }

        }
        void DeleteRecursive(File fileOrDirectory) {

            if (fileOrDirectory.isDirectory())
                for (File child : fileOrDirectory.listFiles())
                    DeleteRecursive(child);

            fileOrDirectory.delete();

        }

    }


    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mRegistrationBroadcastReceiver,new IntentFilter("pushNotification"));

    }
    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


}
