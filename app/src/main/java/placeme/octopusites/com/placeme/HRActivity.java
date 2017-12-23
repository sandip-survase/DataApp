package placeme.octopusites.com.placeme;

import android.content.Intent;
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
import android.support.design.widget.NavigationView;
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

import com.bumptech.glide.signature.ObjectKey;
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
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.fromString;
import static placeme.octopusites.com.placeme.LoginActivity.md5;

public class HRActivity extends AppCompatActivity implements ImagePickerCallback {

    public static final int HR_DATA_CHANGE_RESULT_CODE = 444;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Password = "passKey";
    public static final String Intro = "intro";
    CircleImageView profile;
    boolean doubleBackToExitPressedOnce = false;
    int notificationorplacementflag = 0;
    int count = 0, id[], pcount = 0;
    String heading[], notification[];
    JSONParser jParser = new JSONParser();
    JSONObject json;
    FrameLayout mainfragment;
    //  our coding here
    Handler handler = new Handler();
    RelativeLayout createnotificationrl, editnotificationrl;
    int notificationplacementflag = 0;
    int navMenuFlag = 0, oldNavMenuFlag = 0;
    int selectedMenuFlag = 1;
    ImagePicker imagePicker;
    FrameLayout crop_layout;
    int crop_flag = 0;
    String digest1, digest2;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";
    String filepath = "", filename = "";
    String directory;
    List<String> response;

    //
    ArrayList<ArrayList<RecyclerItemUsers>> registeredallListsfromserver = new ArrayList<>();
    ArrayList<ArrayList<RecyclerItemUsers>> ShortlistedListsfromserver = new ArrayList<>();
    ArrayList<ArrayList<RecyclerItemUsers>> placedallListsfromserver = new ArrayList<>();
    int placemntscount;
    Toolbar toolbar;
    SwipeRefreshLayout tswipe_refresh_layout;
    TextView createnotificationtxt, editnotificationtxt;
    private String username = "";
    private List<RecyclerItem> itemList = new ArrayList<>();
    private RecyclerItemAdapter mAdapter;
    private MaterialSearchView searchView;
    //  our coding here
    private ImageView resultView;
    private String finalPath;
    private String plainusername;
    private RecyclerView recyclerView, recyclerViewPlacemetsHr;
    private List<RecyclerItemHrPlacement> itemList2 = new ArrayList<>();
    private RecyclerItemHrPlacementAdapter mAdapter2;
    private TextView toolbar_title;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("MYTAG", "hractivityactivity called: ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(Z.getRighteous(HRActivity.this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar_title.setText("Notifications");

//        username=getIntent().getStringExtra("username");
        username = MySharedPreferencesManager.getUsername(this);
        String pass = MySharedPreferencesManager.getPassword(HRActivity.this);
        String role = MySharedPreferencesManager.getRole(HRActivity.this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        try {
            plainusername = AES4all.Decrypt(username, digest1, digest2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        username = getIntent().getStringExtra("username");

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);


        createnotificationtxt = (TextView) findViewById(R.id.createnotificationtxt);
        editnotificationtxt = (TextView) findViewById(R.id.editnotificationtxt);


        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        MySharedPreferencesManager.save(HRActivity.this, "intro", "yes");
        MySharedPreferencesManager.save(HRActivity.this, "otp", "no");
        MySharedPreferencesManager.save(HRActivity.this, "activationMessage", "no");
        MySharedPreferencesManager.save(HRActivity.this, "activatedCode", "no");


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(HRActivity.this, query, Toast.LENGTH_LONG).show();

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

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                if (oldNavMenuFlag != navMenuFlag) {
                    if (navMenuFlag == 1) {
                        crop_layout.setVisibility(View.GONE);
                        HRProfileFragment fragment = new HRProfileFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();
                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("My Profile");

                        recyclerView.setVisibility(View.GONE);
                        recyclerViewPlacemetsHr.setVisibility(View.GONE);


                    } else if (navMenuFlag == 2) {

                        notificationorplacementflag = 1;

                        notificationorplacementflag = 1;
                        crop_layout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Notifications");
                        getSupportActionBar().setTitle("Notifications");

                        createnotificationtxt.setText("Create Notification");
                        editnotificationtxt.setText("Edit Notification");

                        mainfragment.setVisibility(View.GONE);
//                        tswipe_refresh_layout.setVisibility(View.VISIBLE);

//                            getNotifications();
                        mainfragment.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerViewPlacemetsHr.setVisibility(View.GONE);
                    } else if (navMenuFlag == 3) {

                        notificationorplacementflag = 2;

                        crop_layout.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("");
                        toolbar_title.setText("Placements");
                        mainfragment.setVisibility(View.GONE);
//                        tswipe_refresh_layout.setVisibility(View.VISIBLE);

                        addTempPlacements();
                        getSupportActionBar().setTitle("Placements");

                        createnotificationtxt.setText("Create Placements");
                        editnotificationtxt.setText("Edit Placements");

                        mainfragment.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        recyclerViewPlacemetsHr.setVisibility(View.VISIBLE);

                        RelativeLayout rl = (RelativeLayout) findViewById(R.id.admincontrolsrl);
                        rl.setVisibility(View.VISIBLE);
//                        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//                        fab.setVisibility(View.VISIBLE);

//                        initializerecyclerViewPlacements();
                        getplacementbyhr();
                    } else if (navMenuFlag == 4) {
                        crop_layout.setVisibility(View.GONE);
                        MessagesFragment fragment = new MessagesFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment, fragment);
                        fragmentTransaction.commit();

                        mainfragment.setVisibility(View.VISIBLE);
                        getSupportActionBar().setTitle("Messages");
//                        tswipe_refresh_layout.setVisibility(View.GONE);

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
//                        tswipe_refresh_layout.setVisibility(View.GONE);
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
//                        tswipe_refresh_layout.setVisibility(View.GONE);
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
//                        tswipe_refresh_layout.setVisibility(View.GONE);
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

//        new GetProfileImage().execute();

        final ImageView profilei = (ImageView) hView.findViewById(R.id.profile);
        final ImageView notificationi = (ImageView) hView.findViewById(R.id.notification);
        final ImageView placementi = (ImageView) hView.findViewById(R.id.placement);
        final ImageView proi = (ImageView) hView.findViewById(R.id.pro);
        final ImageView settingsi = (ImageView) hView.findViewById(R.id.settings);
        final ImageView newsi = (ImageView) hView.findViewById(R.id.blog);
        final ImageView chati = (ImageView) hView.findViewById(R.id.chat);
        downloadImage();

//        notificationcounttxt=(TextView) hView.findViewById(R.id.notificationcount);
//        notificationcountrl=(RelativeLayout) hView.findViewById(R.id.notificationcountrl);
//        final ImageView chati=(ImageView)hView.findViewById(R.id.chat);

//        notificationcounttxt=(TextView)hView.findViewById(R.id.notificationcount);
//        notificationcountrl=(RelativeLayout)hView.findViewById(R.id.notificationcountrl);
//
//        placementcounttxt=(TextView)hView.findViewById(R.id.placementcount);
//        placementcountrl=(RelativeLayout)hView.findViewById(R.id.placementcountrl);
//
//        messagecount=(TextView)hView.findViewById(R.id.messagecount);
//        messagecountrl=(RelativeLayout)hView.findViewById(R.id.messagecountrl);

        View v1 = (View) hView.findViewById(R.id.prifileselectionview);
        View v2 = (View) hView.findViewById(R.id.notificationselectionview);
        View v3 = (View) hView.findViewById(R.id.placementselectionview);
//        View v4 = (View) hView.findViewById(R.id.proselectionview);
        View v5 = (View) hView.findViewById(R.id.settingselectionview);
        View v6 = (View) hView.findViewById(R.id.blogselectionview);
        View v7 = (View) hView.findViewById(R.id.abtselectionview);
//        View v8 = (View) hView.findViewById(R.id.chatselectionview);

        mainfragment = (FrameLayout) findViewById(R.id.mainfragment);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                navMenuFlag = 1;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon_selected);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getBold(HRActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(HRActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(HRActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));


                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(HRActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(HRActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));


                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(HRActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 2;


//                tswipe_refresh_layout.setVisibility(View.VISIBLE);

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(HRActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon_selected);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getBold(HRActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(HRActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));


                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(HRActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(HRActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(HRActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


                mainfragment.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerViewPlacemetsHr.setVisibility(View.GONE);
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.admincontrolsrl);
                rl.setVisibility(View.VISIBLE);
//                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//                fab.setVisibility(View.VISIBLE);

            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 3;

//                tswipe_refresh_layout.setVisibility(View.VISIBLE);

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(HRActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(HRActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon_selected);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getBold(HRActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(HRActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(HRActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(HRActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.while_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                mainfragment.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                recyclerViewPlacemetsHr.setVisibility(View.VISIBLE);
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.admincontrolsrl);
                rl.setVisibility(View.VISIBLE);
//                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//                fab.setVisibility(View.VISIBLE);


            }
        });

        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navMenuFlag = 5;

                Drawable myDrawable1 = getResources().getDrawable(R.drawable.my_profile_icon);
                profilei.setImageDrawable(myDrawable1);

                TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
                pt1.setTypeface(Z.getLight(HRActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(HRActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(HRActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon_selected);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getBold(HRActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.sky_blue_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(HRActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(HRActivity.this));
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
                pt1.setTypeface(Z.getLight(HRActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(HRActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(HRActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(HRActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon_selected);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getBold(HRActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.sky_blue_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(HRActivity.this));
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
                pt1.setTypeface(Z.getLight(HRActivity.this));
                pt1.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable2 = getResources().getDrawable(R.drawable.notification_icon);
                notificationi.setImageDrawable(myDrawable2);

                TextView pt2 = (TextView) hView.findViewById(R.id.notificationtxt);
                pt2.setTypeface(Z.getLight(HRActivity.this));
                pt2.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable3 = getResources().getDrawable(R.drawable.placement_icon);
                placementi.setImageDrawable(myDrawable3);

                TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
                pt3.setTypeface(Z.getLight(HRActivity.this));
                pt3.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable5 = getResources().getDrawable(R.drawable.settings_icon);
                settingsi.setImageDrawable(myDrawable5);

                TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
                pt5.setTypeface(Z.getLight(HRActivity.this));
                pt5.setTextColor(getResources().getColor(R.color.while_color));

                Drawable myDrawable6 = getResources().getDrawable(R.drawable.blog_icon);
                newsi.setImageDrawable(myDrawable6);

                TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
                pt6.setTypeface(Z.getLight(HRActivity.this));
                pt6.setTextColor(getResources().getColor(R.color.while_color));

                TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
                pt7.setTypeface(Z.getLight(HRActivity.this));
                pt7.setTextColor(getResources().getColor(R.color.sky_blue_color));

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


            }
        });

        Drawable myDrawable = getResources().getDrawable(R.drawable.notification_icon_selected);
        notificationi.setImageDrawable(myDrawable);

        TextView pt = (TextView) hView.findViewById(R.id.notificationtxt);
        pt.setTypeface(Z.getBold(HRActivity.this));
        pt.setTextColor(getResources().getColor(R.color.sky_blue_color));

        TextView pt1 = (TextView) hView.findViewById(R.id.profiletxt);
        pt1.setTypeface(Z.getLight(HRActivity.this));
        pt1.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt3 = (TextView) hView.findViewById(R.id.placementtxt);
        pt3.setTypeface(Z.getLight(HRActivity.this));
        pt3.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt5 = (TextView) hView.findViewById(R.id.settingstxt);
        pt5.setTypeface(Z.getLight(HRActivity.this));
        pt5.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt6 = (TextView) hView.findViewById(R.id.blogtxt);
        pt6.setTypeface(Z.getLight(HRActivity.this));
        pt6.setTextColor(getResources().getColor(R.color.while_color));

        TextView pt7 = (TextView) hView.findViewById(R.id.abttxt);
        pt7.setTypeface(Z.getLight(HRActivity.this));
        pt7.setTextColor(getResources().getColor(R.color.while_color));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewPlacemetsHr = (RecyclerView) findViewById(R.id.recyclerViewPlacemetsHr);

        mAdapter = new RecyclerItemAdapter(itemList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        mAdapter2 = new RecyclerItemHrPlacementAdapter(itemList2);
        recyclerViewPlacemetsHr.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        recyclerViewPlacemetsHr.setLayoutManager(mLayoutManager2);
        recyclerViewPlacemetsHr.addItemDecoration(new DividerItemDecorationHrPlacements(this, LinearLayoutManager.VERTICAL));
        recyclerViewPlacemetsHr.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlacemetsHr.setAdapter(mAdapter2);


        //temp work remove after
        addNotificationdatatoAdapter();


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                RecyclerItem movie = itemList.get(position);

                if (notificationorplacementflag == 0) {

                    startActivity(new Intent(HRActivity.this, ViewNotificationAdmin.class).putExtra("username", username));
                } else if (notificationorplacementflag == 1) {
                    startActivity(new Intent(HRActivity.this, ViewPlacementAdmin.class).putExtra("username", username));
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        recyclerViewPlacemetsHr.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewPlacemetsHr, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {
                    RecyclerItemHrPlacement item = itemList2.get(position);


                    ArrayList<RecyclerItemUsers> RegisteredItemlistTemp = registeredallListsfromserver.get(position);
                    ArrayList<RecyclerItemUsers> ShortlistedListsfromservertemp = ShortlistedListsfromserver.get(position);
                    ArrayList<RecyclerItemUsers> placedItemlistTemp = placedallListsfromserver.get(position);


                    String sRegisteredItemlistTemp = OtoString(RegisteredItemlistTemp, digest1, digest2);
                    String sShortlistedListsfromservertemp = OtoString(ShortlistedListsfromservertemp, digest1, digest2);
                    String splacedItemlistTemp = OtoString(placedItemlistTemp, digest1, digest2);


                    Log.d("TAG", "position  " + position);
                    Intent i1 = new Intent(HRActivity.this, UserSelection.class);
                    i1.putExtra("id", item.getId());
                    Log.d("Tag", "id: " + item.getId());
                    i1.putExtra("sRegisteredItemlistTemp", sRegisteredItemlistTemp);
                    i1.putExtra("sShortlistedListsfromservertemp", sShortlistedListsfromservertemp);
                    i1.putExtra("splacedItemlistTemp", splacedItemlistTemp);
                    i1.putExtra("companyname", item.getCompanyname());
                    i1.putExtra("lastmodifiedtime", item.getLastmodifiedtime());
                    i1.putExtra("registerednumber", item.getRegisterednumber());
                    i1.putExtra("placednumber", item.getPlacednumber());
                    i1.putExtra("lastdateofreg", item.getLastdateofreg());
                    startActivity(i1);
                } catch (Exception e) {
                    Toast.makeText(HRActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        disableNavigationViewScrollbars(navigationView);

        createnotificationrl = (RelativeLayout) findViewById(R.id.createnotificationrl);
        editnotificationrl = (RelativeLayout) findViewById(R.id.editnotificationrl);

        createnotificationrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notificationorplacementflag == 1) {
                    //CreateNotification
                    Intent i1 = new Intent(HRActivity.this, CreateNotificationHR.class);
                    i1.putExtra("flag", "HrActivity");
                    startActivity(i1);

//                    startActivity(new Intent(AdminActivity.this,CreateNotification.class));


                } else if (notificationorplacementflag == 2) {
                    //CreatePlacement
                    String Tag = "HrActivity";
                    PlacementEditData settag = new PlacementEditData();
                    settag.setActivityFromtag(Tag);
                    startActivity(new Intent(HRActivity.this, CreatePlacementHr.class));

                }
            }
        });

        editnotificationrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notificationorplacementflag == 1) {
                    //EditNotification
                    String Tag = "HrActivity";
                    PlacementEditData settag = new PlacementEditData();
                    settag.setActivityFromtag(Tag);
                    startActivity(new Intent(HRActivity.this, EditNotification.class));
                } else if (notificationorplacementflag == 2) {
                    //EditPlacement

                    String Tag = "HrActivityEdit";
                    PlacementEditData settag = new PlacementEditData();
                    settag.setActivityFromtag(Tag);
                    startActivity(new Intent(HRActivity.this, EditPlacementHr.class));

                }

            }
        });

        try {

            demoKeyBytes = SimpleBase64Encoder.decode(digest1);
            demoIVBytes = SimpleBase64Encoder.decode(digest2);
            sPadding = "ISO10126Padding";

            byte[] demo1EncryptedBytes1 = SimpleBase64Encoder.decode(username);
            byte[] demo1DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes1);
            plainusername = new String(demo1DecryptedBytes1);
//            r.setPlainusername(plainusername);

            byte[] demo2EncryptedBytes1 = SimpleBase64Encoder.decode(pass);
            byte[] demo2DecryptedBytes1 = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo2EncryptedBytes1);
            String data = new String(demo2DecryptedBytes1);
            String hash = md5(data + MySharedPreferencesManager.getDigest3(HRActivity.this));

//           loginFirebase(plainusername,hash);
            new LoginFirebaseTask().execute(plainusername, hash);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        crop_layout = (FrameLayout) findViewById(R.id.crop_layout);


        resultView = (ImageView) findViewById(R.id.result_image);
        imagePicker = new ImagePicker(this);
        imagePicker.setImagePickerCallback(this);

        imagePicker.shouldGenerateMetadata(false); // Default is true
        imagePicker.shouldGenerateThumbnails(false); // Default is true
        requestProfileImage();  //  update thumbanail first time activity Load


        tswipe_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        tswipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getplacementbyhr();

//                if (selectedMenuFlag == 1)
//                    getNotifications();
//                else if (selectedMenuFlag == 2)
//                    getPlacements();


            }
        });

        tswipe_refresh_layout.setRefreshing(true);

        new UpdateFirebaseToken().execute();
    }

    void setserverlisttoadapter(List<RecyclerItemHrPlacement> itemlist) {

        itemList2.clear();
        itemList2.addAll(itemlist);
//        mAdapter2 = new RecyclerItemHrPlacementAdapter(itemList2);
//        recyclerViewPlacemetsHr.setAdapter(mAdapter2);
        mAdapter2.notifyDataSetChanged();


    }

    void getplacementbyhr() {
//        initializerecyclerViewPlacements();
        new Getplacementbyhr().execute();
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

        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    void addNotificationdatatoAdapter() {
//        for(int i=0;i<10;i++)
//        {
//            RecyclerItem item = new RecyclerItem(i,"Heading "+i, "Notification "+i,"21/07/2016", "yes",HRActivity.this);
//            itemList.add(item);
//        }
//        for(int i=0;i<2;i++)
//        {
//            RecyclerItem item = new RecyclerItem(i,"Heading "+i, "Notification "+i,"21/07/2016", "no",HRActivity.this);
//            itemList.add(item);
//        }
//        mAdapter.notifyDataSetChanged();
    }

    void addPlacementdatatoAdapter() {
//        for(int i=0;i<5;i++)
//        {
//            RecyclerItem item = new RecyclerItem(i,"Company "+i, "3.2","21/07/2016", "Trainee");
//            itemList.add(item);
//        }
//        mAdapter.notifyDataSetChanged();
    }

    // our coding here
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        if (resultCode == HR_DATA_CHANGE_RESULT_CODE) {

            HRProfileFragment fragment = (HRProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
            fragment.refreshContent();

        }
        if (requestCode == Picker.PICK_IMAGE_DEVICE) {

            try {

                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback(this);
                }
                imagePicker.submit(result);
                crop_layout.setVisibility(View.VISIBLE);
                //            tswipe_refresh_layout.setVisibility(View.GONE);
                mainfragment.setVisibility(View.GONE);
                crop_flag = 1;
                beginCrop(result.getData());
                // Toast.makeText(this, "crop initiated", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                crop_layout.setVisibility(View.GONE);
                //            tswipe_refresh_layout.setVisibility(View.GONE);
                mainfragment.setVisibility(View.VISIBLE);
            }
        } else if (resultCode == RESULT_CANCELED) {
            crop_layout.setVisibility(View.GONE);
            //        tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            crop_flag = 0;
        } else if (requestCode == Crop.REQUEST_CROP) {
            // Toast.makeText(this, "cropped", Toast.LENGTH_SHORT).show();
            handleCrop(resultCode, result);
        }

//    if(requestCode==HRProfileFragment.COMPANY_DEATAILS_CHANGE_REQUEST)
//    {
//
//    }
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
//            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            HRProfileFragment fragment = (HRProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
            fragment.showUpdateProgress();
            new UploadProfile().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (resultCode == Crop.RESULT_ERROR) {
            crop_layout.setVisibility(View.GONE);
//            tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Try Again..!", Toast.LENGTH_SHORT).show();

        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    public void requestCropImage() {
        resultView.setImageDrawable(null);
        MySharedPreferencesManager.save(HRActivity.this, "crop", "yes");

        chooseImage();


    }

    private void chooseImage() {

        imagePicker.pickImage();
    }

    @Override
    public void onError(String s) {
        crop_layout.setVisibility(View.GONE);
        // tswap       tswipe_refresh_layout.setVisibility(View.GONE);
        mainfragment.setVisibility(View.VISIBLE);
        Toast.makeText(HRActivity.this, s, Toast.LENGTH_SHORT).show();

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

    private void downloadImage() {

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

            GlideApp.with(HRActivity.this)
                    .load(uri)
                    .signature(new ObjectKey(signature))
                    .into(profile);
        }
    }

    public void requestProfileImage() {
        // Toast.makeText(this, "thumbnail Method()", Toast.LENGTH_SHORT).show();
//        new GetProfileImage().execute();
        downloadImage();

    }

    void addTempPlacements() {

        Log.d("tag", "addTempPlacements:Accessed ");
        for (int i = 0; i < 10; i++) {
            RecyclerItemHrPlacement item2 = new RecyclerItemHrPlacement(i, "Cognizant", "17-FEB-2017 5:20 PM", "201 Candidates Registered", "57 Candidates Placed", "20-FEB-2017");
            itemList2.add(item2);


        }
    }

    // thumbanail

    class UploadProfile extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            try {
                username=MySharedPreferencesManager.getUsername(HRActivity.this);
                File sourceFile = new File(filepath);
                Log.d("***", "doInBackground: input username "+username);
                MultipartUtility multipart = new MultipartUtility(Z.upload_profile, "UTF-8");
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
            //           tswipe_refresh_layout.setVisibility(View.GONE);
            mainfragment.setVisibility(View.VISIBLE);

            if (response != null && response.get(0).contains("success")) {

                MySharedPreferencesManager.save(HRActivity.this, "crop", "no");
                requestProfileImage();
                HRProfileFragment fragment = (HRProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
                fragment.downloadImage();
                Toast.makeText(HRActivity.this, "Successfully Updated..!", Toast.LENGTH_SHORT).show();
                DeleteRecursive(new File(directory));
            } else if (response != null && response.get(0).contains("null")) {
                requestProfileImage();
                MyProfileFragment fragment = (MyProfileFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
                fragment.refreshContent();
                Toast.makeText(HRActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(HRActivity.this, Z.FAIL_TO_PROCESS, Toast.LENGTH_SHORT).show();

        }

        void DeleteRecursive(File fileOrDirectory) {

            if (fileOrDirectory.isDirectory())
                for (File child : fileOrDirectory.listFiles())
                    DeleteRecursive(child);

            fileOrDirectory.delete();

        }

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

    class LoginFirebaseTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            String user = param[0];
            String hash = param[1];
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(user, hash)
                    .addOnCompleteListener(HRActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                MySharedPreferencesManager.save(HRActivity.this, "fireLoginStatus", "Successfully logged in to Firebase");
                            } else {
                                MySharedPreferencesManager.save(HRActivity.this, "fireLoginStatus", "Failed to login to Firebase");
                            }
                        }
                    });
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            String status = MySharedPreferencesManager.getData(HRActivity.this, "fireLoginStatus");
            Toast.makeText(HRActivity.this, status, Toast.LENGTH_SHORT).show();
            // remove value from shared
            MySharedPreferencesManager.removeKey(HRActivity.this, "fireLoginStatus");
        }
    }


    class Getplacementbyhr extends AsyncTask<String, String, String> {

        private static final String TAG = "Getplacementbyhr";
        ArrayList<RecyclerItemHrPlacement> itemlistfromserver = new ArrayList<>();
        String username1 = MySharedPreferencesManager.getUsername(getBaseContext());

        protected String doInBackground(String... param) {
            Log.d(TAG, "doInBackground:username1 " + username1);

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("u", "JPmAnGBeJZXNGflV0GTTdQm/vhd32vqwKhPHylZ77kw="));       //0
            params.add(new BasicNameValuePair("u", username1));       //0


//            params.add(new BasicNameValuePair("p", page_to_call_notification + ""));
            digest1 = MySharedPreferencesManager.getDigest1(getBaseContext());
            digest2 = MySharedPreferencesManager.getDigest2(getBaseContext());
            Log.d("itemlistfromserver", "digest1: " + digest1);
            Log.d("itemlistfromserver", "digest2: " + digest2);


            json = jParser.makeHttpRequest(Z.url_GetPlacementsCreatedByHr, "GET", params);
            try {

                placemntscount = Integer.parseInt(json.getString("count"));
                Log.d("itemlistfromserver", "doInBackground: " + placemntscount);

                if (placemntscount != 0) {
                    Log.d("json1", "jsonparamsList " + json.getString("jsonparamsList"));
                    Log.d("json1", "jsonRegisteredAllLists " + json.getString("jsonRegisteredAllLists"));
                    Log.d("json1", "jsonshortlistedallallLists" + json.getString("jsonshortlistedallallLists"));
                    Log.d("json1", "jsonparamsplacedlistedallLists" + json.getString("jsonparamsplacedlistedallLists"));

                    itemlistfromserver = (ArrayList<RecyclerItemHrPlacement>) fromString(json.getString("jsonparamsList"), digest1, digest2);
                    Log.d("itemlistfromserver", "reg=======================" + itemlistfromserver.get(0).getRegisterednumber());


                    registeredallListsfromserver = (ArrayList<ArrayList<RecyclerItemUsers>>) fromString(json.getString("jsonRegisteredAllLists"), digest1, digest2);
                    ShortlistedListsfromserver = (ArrayList<ArrayList<RecyclerItemUsers>>) fromString(json.getString("jsonshortlistedallallLists"), digest1, digest2);
                    placedallListsfromserver = (ArrayList<ArrayList<RecyclerItemUsers>>) fromString(json.getString("jsonparamsplacedlistedallLists"), digest1, digest2);
                    Log.d("itemlistfromserver", "itemlistfromserver size: " + itemlistfromserver.size());
                    Log.d("itemlistfromserver", "registeredallListsfromserver: " + registeredallListsfromserver.size());
                    Log.d("itemlistfromserver", "ShortlistedListsfromserver: " + ShortlistedListsfromserver.size());
                    Log.d("itemlistfromserver", "placedallListsfromserver: " + placedallListsfromserver.size());

                    Log.d("check", "----------------ci=ontentx of list--------------------------: " + registeredallListsfromserver.get(0).size());


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

            setserverlisttoadapter(itemlistfromserver);

        }


    }
    class UpdateFirebaseToken extends AsyncTask<String, String, String> {

        // TODO move UpdateFirebaseToken code to all base activity
        // TODO update AID,DID
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

}