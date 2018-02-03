package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerView;
import com.appnext.base.Appnext;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class AchievementsProfileTabFragment extends Fragment {

    String username;

    View knownlang,certifications,courses,skills,honors,patents,publications;
    private AdView mAdView;
    BannerView bannerView, bannerView2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Appnext.init(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_edit_profile_accomplishments, container, false);

//        MobileAds.initialize(getActivity(), Z.APP_ID);
//        mAdView = rootView.findViewById(R.id.ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        bannerView = rootView.findViewById(R.id.banner);
        bannerView2 = rootView.findViewById(R.id.banner2);
        bannerView.loadAd(new BannerAdRequest());
        bannerView2.loadAd(new BannerAdRequest());

        TextView knownlangtxt=(TextView)rootView.findViewById(R.id.knownlangtxt);
        TextView certitxt=(TextView)rootView.findViewById(R.id.certitxt);
        TextView coursetxt=(TextView)rootView.findViewById(R.id.coursetxt);
        TextView skillstxt=(TextView)rootView.findViewById(R.id.skillstxt);
        TextView honortxt=(TextView)rootView.findViewById(R.id.honortxt);
        TextView patenttxt=(TextView)rootView.findViewById(R.id.patenttxt);
        TextView publicationtxt=(TextView)rootView.findViewById(R.id.publicationtxt);


        knownlangtxt.setTypeface(Z.getBold(getActivity()));
        certitxt.setTypeface(Z.getBold(getActivity()));
        coursetxt.setTypeface(Z.getBold(getActivity()));
        skillstxt.setTypeface(Z.getBold(getActivity()));
        honortxt.setTypeface(Z.getBold(getActivity()));
        patenttxt.setTypeface(Z.getBold(getActivity()));
        publicationtxt.setTypeface(Z.getBold(getActivity()));

        knownlang=(View)rootView.findViewById(R.id.studentBlock);
        skills=(View)rootView.findViewById(R.id.alumniBlock);
        certifications=(View)rootView.findViewById(R.id.certibutton);
        courses=(View)rootView.findViewById(R.id.coursebutton);
        honors=(View)rootView.findViewById(R.id.adminBlock);
        patents=(View)rootView.findViewById(R.id.patentbutton);
        publications=(View)rootView.findViewById(R.id.publicationbutton);

        knownlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileKnownLang.class).putExtra("username",username),0);
            }
        });

        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileSkills.class).putExtra("username",username),0);
            }
        });
        certifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileCertifications.class).putExtra("username",username),0);
            }
        });
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileCourses.class).putExtra("username",username),0);
            }
        });
        honors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileAchievements.class).putExtra("username",username),0);
            }
        });
        patents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfilePatents.class).putExtra("username",username),0);
            }
        });
        publications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfilePublications.class).putExtra("username",username),0);

            }
        });





//        achievementsedittext=(EditText)rootView.findViewById(R.id.achievements);
//        save=(Button)rootView.findViewById(R.id.saveachievements);
//        achievementsprogress=(ProgressBar)rootView.findViewById(R.id.achievementsprogress);
//
//
//        SaveProfileInfo s=new SaveProfileInfo();
//        username=s.getUsername();
//        achievements=s.getAchievements();
//
//        achievementsedittext.setText(achievements);
//
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                save.setVisibility(View.GONE);
//                achievementsprogress.setVisibility(View.VISIBLE);
//
//                achievements=achievementsedittext.getText().toString();
//
//                new MyAsyncTask().execute();
//            }
//        });


        return rootView;
    }
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);

        // HW layer support only exists on API 11+
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

                    // ...other AnimationListener methods go here...
                });
            }
        }

        return animation;
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        bannerView.destroy();
        bannerView2.destroy();
        super.onDestroy();
    }
}