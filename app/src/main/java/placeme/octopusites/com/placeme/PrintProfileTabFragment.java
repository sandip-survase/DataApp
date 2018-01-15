package placeme.octopusites.com.placeme;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.RecyclerTouchListener;

import static android.content.Context.DOWNLOAD_SERVICE;
import static placeme.octopusites.com.placeme.MainActivity.photo;

public class PrintProfileTabFragment extends Fragment {

    private RecyclerView recyclerView;
    private PrintProfileAdapter adapter;
    private List<ResumeTemplateItem> itemList;

    JSONParser jParser = new JSONParser();
    JSONObject json;
    int count=0;
    int template = 2;
    int resumeIds[];
    String resumeNames[];
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String fname = "", lname = "", studenttenthmarks, studenttwelthordiplomamarks, studentugmarks, studentpgmarks;
    String username,resultofop="",role;
    RadioGroup radioGroupFormat;
    RadioButton radioButtonWord,radioButtonPdf;
    String format="pdf";
    Button downloadresume;
    View getmoreselectionview;
    TextView getmore,selectformattxt;
    ProgressBar resumeprogress;
    int found_photo=0,found_box1=0,found_tenth=0,found_twelth=0,found_diploma=0,found_ug=0,found_pgsem=0,found_pgyear=0,found_projects=0,found_lang=0,found_certificates=0;
    int found_courses=0,found_skills=0,found_honors=0,found_patents=0,found_publications=0,found_careerobj=0,found_strengths=0,found_weaknesses=0,found_locationpreferences=0;
    int found_contact_details=0,found_personal=0;
    int downloadClickFlag = 0;
    int selectedResumeTemplate=0;
    private RewardedVideoAd mRewardedVideoAd;
    boolean isRewarded = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_profile_printprofile, container, false);

        MobileAds.initialize(getActivity(), Z.APP_ID);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                if (!isRewarded)
                    Toast.makeText(getActivity(), "Please watch the complete video to download your resume.", Toast.LENGTH_SHORT).show();
                downloadresume.setVisibility(View.VISIBLE);
                resumeprogress.setVisibility(View.GONE);
                loadRewardedVideoAd();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                if (downloadClickFlag == 1)
                    startDownload();
                isRewarded = true;
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {


            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {


            }
        });
        loadRewardedVideoAd();
        getmore=(TextView)rootView.findViewById(R.id.getmore);
        selectformattxt=(TextView)rootView.findViewById(R.id.selectformattxt);

        getmore.setTypeface(Z.getBold(getActivity()));
        selectformattxt.setTypeface(Z.getLight(getActivity()));
        sharedpreferences=getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);
        role = MySharedPreferencesManager.getRole(getActivity());

        MySharedPreferencesManager.save(getActivity(),"template",template+"");

        radioGroupFormat=(RadioGroup)rootView.findViewById(R.id.radioGroupFormat);
        radioButtonWord=(RadioButton)rootView.findViewById(R.id.radioButtonWord);
        radioButtonPdf=(RadioButton)rootView.findViewById(R.id.radioButtonPdf);

        radioButtonWord.setTypeface(Z.getBold(getActivity()));
        radioButtonPdf.setTypeface(Z.getBold(getActivity()));

        radioGroupFormat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonPdf:
                        format="pdf";
                        break;
                    case R.id.radioButtonWord:
                        format="word";
//                        radioButtonPdf.setChecked(true);
//                        Toast.makeText(getActivity(),"Word option is not available right now, but will be available soon.",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);

        itemList = new ArrayList<>();
        adapter = new PrintProfileAdapter(username,itemList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                selectedResumeTemplate=position;


                int temptemplate = resumeIds[selectedResumeTemplate];

                if (temptemplate == 1) {
                    template = 2;
                } else if (temptemplate == 2) {
                    template = 1;
                } else
                    template = temptemplate;

                MySharedPreferencesManager.save(getActivity(),"template",template+"");

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        downloadresume=(Button)rootView.findViewById(R.id.downloadresume);
        resumeprogress=(ProgressBar)rootView.findViewById(R.id.resumeprogress);
        getmoreselectionview=(View) rootView.findViewById(R.id.getmoreselectionview);

        getmoreselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),GetMoreResumeTemplates.class),0);
            }
        });



        downloadresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validatedata();


            }
        });

        refreshContent();
        File myDirectory = new File(Environment.getExternalStorageDirectory(), "Place Me");
        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        }


        return rootView;
    }
    public void refreshContent()
    {
        new GetResumeIds().execute();
    }

    class GetResumeIds extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            itemList.clear();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));
            json = jParser.makeHttpRequest(Z.url_GetMyResumeIds, "GET", params);
            try {
                String s = json.getString("count");
                count=Integer.parseInt(s);
                resumeIds=new int[count];
                resumeNames=new String[count];

                for(int i=0;i<count;i++) {
                    resumeIds[i] = Integer.parseInt(json.getString("id" + i));
                    resumeNames[i] = json.getString("name" + i);
                }


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {


            ResumeTemplateItem item=null;
            for(int i=0;i<count;i++)
            {
                if(i==0)
                    item=new ResumeTemplateItem(resumeIds[i], Z.IP+"AESTest/GetResumePage?a="+resumeIds[i]+"&b=1",resumeNames[i],"checked");
                else
                    item=new ResumeTemplateItem(resumeIds[i], Z.IP+"AESTest/GetResumePage?a="+resumeIds[i]+"&b=1",resumeNames[i],"unchecked");
                itemList.add(item);
            }
            adapter.notifyDataSetChanged();
        }
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);

        if (Build.VERSION.SDK_INT >= 11) {
            if (animation == null && nextAnim != 0) {
                animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
            }

            if (animation != null) {
                getView().setLayerType(View.LAYER_TYPE_HARDWARE, null);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    public void onAnimationEnd(Animation animation) {
                        getView().setLayerType(View.LAYER_TYPE_NONE, null);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                });
            }
        }

        return animation;
    }

    public void validatedata() {
        StudentData s = new StudentData();
        fname = s.getFname();
        lname = s.getLname();

        String careerobj = s.getCareerobj();

        String dob = s.getDob();
        String mobile = s.getPhone();
        String hobbies = s.getHobbies();

        String addrline1c = s.getAddressline1();
        String addrline2c = s.getAddressline2();
        String addrline3c = s.getAddressline3();

        String lang1 = s.getLang1();
        String proj = s.getProj1();
        String strength1 = s.getStrength1();
        String weak1 = s.getWeak1();
        String skill1 = s.getSkill1();
        studenttenthmarks = s.getPercentage10();
        studenttwelthordiplomamarks = s.getPercentage12();
        studentugmarks = s.getAggregateug();


        if (fname != null && lname != null) {

            if (!fname.equals("") && !lname.equals(""))
                found_box1 = 1;
            else
                found_box1 = 0;
        }


        if (studenttenthmarks != null) {

            if (!studenttenthmarks.equals("")) {
                found_tenth = 1;
            } else
                found_tenth = 0;
        }

        if (studenttwelthordiplomamarks != null) {
            if (!studenttwelthordiplomamarks.equals("")) {
                found_twelth = 1;
            } else {
                found_twelth = 0;
                studenttwelthordiplomamarks = s.getAggregatediploma();
                found_diploma = 1;

            }
        }

        if (studentugmarks != null) {

            if (!studentugmarks.equals("")) {
                found_ug = 1;
            } else
                found_ug = 0;
        }

        if (dob != null && mobile != null && hobbies != null && addrline1c != null && addrline2c != null && addrline3c != null) {
            if (!dob.equals("") && !mobile.equals("") && !hobbies.equals("") && !addrline1c.equals("") && !addrline2c.equals("") && !addrline3c.equals("")) {
                found_personal = 1;
            } else
                found_personal = 0;
        }
        if (lang1 != null) {
            if (!lang1.equals("") && !lang1.equals("- Select Language -"))
                found_lang = 1;
            else
                found_lang = 0;
        }
        if (proj != null) {
            if (!proj.equals(""))
                found_projects = 1;
            else
                found_projects = 0;
        }
        if (strength1 != null) {
            if (!strength1.equals(""))
                found_strengths = 1;
            else
                found_strengths = 0;
        }
        if (weak1 != null) {
            if (!weak1.equals(""))
                found_weaknesses = 1;
            else
                found_weaknesses = 0;
        }

        if (skill1 != null) {

            if (!skill1.equals(""))
                found_skills = 1;
            else
                found_skills = 0;
        }

        if (careerobj != null) {
            if (!careerobj.equals(""))
                found_careerobj = 1;
            else
                found_careerobj = 0;
        }


        Log.d("TAG", "validatedata: ShouldAnimateProfile.photo - "+ShouldAnimateProfile.photo);

        if(ShouldAnimateProfile.photo.equals("noupdate")){

            downloadresume.setVisibility(View.VISIBLE);
            resumeprogress.setVisibility(View.GONE);

            Snackbar.make(downloadresume.getRootView(), "Please upload photo", Snackbar.LENGTH_LONG)
                    .setAction("OPEN", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(role.equals("student")) {
                                startActivity(new Intent(getActivity(), MainActivity.class).putExtra("status", 1));
                                getActivity().finish();
                            }else {
                                startActivity(new Intent(getActivity(), AlumniActivity.class).putExtra("status", 1));
                                getActivity().finish();
                            }
                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                    .setDuration(10000)
                    .show();
        }else{

            found_photo =1;
            if(found_box1==0){
                downloadresume.setVisibility(View.VISIBLE);
                resumeprogress.setVisibility(View.GONE);
                Snackbar.make(downloadresume.getRootView(), "Please fill personal details", Snackbar.LENGTH_LONG)
                        .setAction("OPEN", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(getActivity(), MyProfileIntro.class));
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                        .setDuration(10000)
                        .show();
            }else{
                if(found_tenth==0){
                    downloadresume.setVisibility(View.VISIBLE);
                    resumeprogress.setVisibility(View.GONE);
                    Snackbar.make(downloadresume.getRootView(), "Please fill Std. X details", Snackbar.LENGTH_LONG)
                            .setAction("OPEN", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(getActivity(), MyProfileTenth.class));
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                            .setDuration(10000)
                            .show();

                } else{
                    if(found_twelth==0 && found_diploma==0){
                        downloadresume.setVisibility(View.VISIBLE);
                        resumeprogress.setVisibility(View.GONE);
                        Snackbar.make(downloadresume.getRootView(), "Please fill Std. XII/Diplom details", Snackbar.LENGTH_LONG)
                                .setAction("OPEN", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(getActivity(), MyProfileTwelthOrDiploma.class));
                                    }
                                })
                                .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                .setDuration(10000)
                                .show();

                    }else {
                        if (found_ug == 0) {
                            downloadresume.setVisibility(View.VISIBLE);
                            resumeprogress.setVisibility(View.GONE);
                            Snackbar.make(downloadresume.getRootView(), "Please fill Std. Ug details", Snackbar.LENGTH_LONG)
                                    .setAction("OPEN", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(getActivity(), MyProfileUg.class));
                                        }
                                    })
                                    .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                    .setDuration(10000)
                                    .show();

                        } else {
                            if (found_projects == 0) {
                                downloadresume.setVisibility(View.VISIBLE);
                                resumeprogress.setVisibility(View.GONE);
                                Snackbar.make(downloadresume.getRootView(), "Please fill Project details", Snackbar.LENGTH_LONG)
                                        .setAction("OPEN", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                startActivity(new Intent(getActivity(), MyProfileProjects.class));
                                            }
                                        })
                                        .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                        .setDuration(10000)
                                        .show();

                            } else {
                                if (found_lang == 0) {
                                    downloadresume.setVisibility(View.VISIBLE);
                                    resumeprogress.setVisibility(View.GONE);
                                    Snackbar.make(downloadresume.getRootView(), "Please fill Known languages details", Snackbar.LENGTH_LONG)
                                            .setAction("OPEN", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    startActivity(new Intent(getActivity(), MyProfileKnownLang.class));
                                                }
                                            })
                                            .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                            .setDuration(10000)
                                            .show();

                                } else {
                                    if (found_skills == 0) {
                                        downloadresume.setVisibility(View.VISIBLE);
                                        resumeprogress.setVisibility(View.GONE);
                                        Snackbar.make(downloadresume.getRootView(), "Please fill Skill details", Snackbar.LENGTH_LONG)
                                                .setAction("OPEN", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        startActivity(new Intent(getActivity(), MyProfileSkills.class));
                                                    }
                                                })
                                                .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                                .setDuration(10000)
                                                .show();

                                    } else {
                                        if (found_careerobj == 0) {
                                            downloadresume.setVisibility(View.VISIBLE);
                                            resumeprogress.setVisibility(View.GONE);
                                            Snackbar.make(downloadresume.getRootView(), "Please fill Career details", Snackbar.LENGTH_LONG)
                                                    .setAction("OPEN", new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            startActivity(new Intent(getActivity(), MyProfileCareerObj.class));
                                                        }
                                                    })
                                                    .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                                    .setDuration(10000)
                                                    .show();

                                        } else {
                                            if (found_strengths == 0) {
                                                downloadresume.setVisibility(View.VISIBLE);
                                                resumeprogress.setVisibility(View.GONE);
                                                Snackbar.make(downloadresume.getRootView(), "Please fill Strength details", Snackbar.LENGTH_LONG)
                                                        .setAction("OPEN", new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                startActivity(new Intent(getActivity(), MyProfileStrengths.class));
                                                            }
                                                        })
                                                        .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                                        .setDuration(10000)
                                                        .show();

                                            } else {
                                                if (found_weaknesses == 0) {
                                                    downloadresume.setVisibility(View.VISIBLE);
                                                    resumeprogress.setVisibility(View.GONE);
                                                    Snackbar.make(downloadresume.getRootView(), "Please fill Weaknesses details", Snackbar.LENGTH_LONG)
                                                            .setAction("OPEN", new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    startActivity(new Intent(getActivity(), MyProfileWeaknesses.class));
                                                                }
                                                            })
                                                            .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                                            .setDuration(10000)
                                                            .show();

                                                } else {
                                                    if (found_personal == 0) {
                                                        downloadresume.setVisibility(View.VISIBLE);
                                                        resumeprogress.setVisibility(View.GONE);
                                                        Snackbar.make(downloadresume.getRootView(), "Please fill Personal details details", Snackbar.LENGTH_LONG)
                                                                .setAction("OPEN", new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View view) {
                                                                        String role = MySharedPreferencesManager.getRole(getActivity());
                                                                        if (role.equals("student")) {
                                                                            startActivity(new Intent(getActivity(), EditProfile.class));
                                                                        } else
                                                                            startActivity(new Intent(getActivity(), EditProfileAlumni.class));
                                                                    }
                                                                })
                                                                .setActionTextColor(getResources().getColor(R.color.sky_blue_color))
                                                                .setDuration(10000)
                                                                .show();
                                                    }
                                                }
                                            }


                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        if (found_photo == 1 && found_box1 == 1 && found_tenth == 1 && (found_diploma == 1 || found_twelth == 1) && found_ug == 1 && found_projects == 1 && found_lang == 1 && found_skills == 1 && found_careerobj == 1 && found_strengths == 1 && found_weaknesses == 1 && found_personal == 1) {

            downloadClickFlag = 1;
            if (mRewardedVideoAd.isLoaded()) {
                downloadresume.setVisibility(View.GONE);
                resumeprogress.setVisibility(View.VISIBLE);
                mRewardedVideoAd.show();
            } else
                Toast.makeText(getActivity(), "Please wait while we prepare your resume...", Toast.LENGTH_SHORT).show();

        }
    }

    void startDownload() {

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority(Z.VPS_IP)
                .path("GenerateResumeWithJODConverter3/DownloadResume")
                .appendQueryParameter("username", username)
                .appendQueryParameter("format", format)
                .appendQueryParameter("template", template + "")
                .build();


//****************
        String storagePath = Environment.getExternalStorageDirectory().getPath() + "/Place Me/";

        DownloadManager dm = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        if (format.equals("pdf")) {
            request.setDestinationInExternalPublicDir("/Place Me", "resume.pdf");
        } else {
            request.setDestinationInExternalPublicDir("/Place Me", "resume.docx");
        }
        Long referese = dm.enqueue(request);


//                ************
        downloadresume.setVisibility(View.VISIBLE);
        resumeprogress.setVisibility(View.GONE);

        Toast.makeText(getContext(), "Downloading Started..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        mRewardedVideoAd.pause(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        mRewardedVideoAd.resume(getActivity());
        IsNewTemplateDownloaded obj = new IsNewTemplateDownloaded();
        if (obj.getIsDownloaded()) {
//            Toast.makeText(getActivity(), "refreshed", Toast.LENGTH_SHORT).show();
            refreshContent();
            obj.setIsDownloaded(false);
        }
    }

    private void loadRewardedVideoAd() {
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd(Z.VIDEO_UNIT_ID, new AdRequest.Builder().build());
        }
    }

}