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
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class CareerobjProfileTabFragment extends Fragment {


    View careerobjbutton,strengthbutton,weakbutton,locbutton;
    String username;
    private AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_edit_profile_careerdetails, container, false);

        MobileAds.initialize(getActivity(), Z.APP_ID);
        mAdView = rootView.findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        TextView careerobjtxt=(TextView)rootView.findViewById(R.id.careerobjtxt);
        TextView strengthtxt=(TextView)rootView.findViewById(R.id.strengthtxt);
        TextView weaktxt=(TextView)rootView.findViewById(R.id.weaktxt);
        TextView locationtxt=(TextView)rootView.findViewById(R.id.locationtxt);

        careerobjtxt.setTypeface(Z.getBold(getActivity()));
        strengthtxt.setTypeface(Z.getBold(getActivity()));
        weaktxt.setTypeface(Z.getBold(getActivity()));
        locationtxt.setTypeface(Z.getBold(getActivity()));

        careerobjbutton=(View)rootView.findViewById(R.id.careerobjbutton);
        strengthbutton=(View)rootView.findViewById(R.id.strengthbutton);
        weakbutton=(View)rootView.findViewById(R.id.weakbutton);
        locbutton=(View)rootView.findViewById(R.id.locbutton);

        careerobjbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileCareerObj.class).putExtra("username",username),0);
            }
        });
        strengthbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileStrengths.class).putExtra("username",username),0);

            }
        });
        weakbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileWeaknesses.class).putExtra("username",username),0);
            }
        });
        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileLocationPreferences.class).putExtra("username",username),0);
            }
        });



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
        super.onDestroy();
    }
}