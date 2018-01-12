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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class EducationTabFragment extends Fragment {

    View tenth,twelth,ug,pg;
    String username;
    private AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_edit_profile_education, container, false);

        MobileAds.initialize(getActivity(), Z.APP_ID);
        mAdView = rootView.findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView tenthtxt=(TextView)rootView.findViewById(R.id.tenthtxt);
        TextView twelthtxt=(TextView)rootView.findViewById(R.id.twelthtxt);
        TextView ugtxt=(TextView)rootView.findViewById(R.id.ugtxt);
        TextView pgtxt=(TextView)rootView.findViewById(R.id.pgtxt);


        tenthtxt.setTypeface(Z.getBold(getActivity()));
        twelthtxt.setTypeface(Z.getBold(getActivity()));
        ugtxt.setTypeface(Z.getBold(getActivity()));
        pgtxt.setTypeface(Z.getBold(getActivity()));


        tenth=(View)rootView.findViewById(R.id.tenthbutton);
        twelth=(View)rootView.findViewById(R.id.twelfthbutton);
        ug=(View)rootView.findViewById(R.id.ugbutton);
        pg=(View)rootView.findViewById(R.id.pgbutton);

        tenth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileTenth.class).putExtra("username",username),0);
            }
        });


        twelth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileTwelthOrDiploma.class).putExtra("username",username),0);
            }
        });

        ug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileUg.class).putExtra("username",username),0);
            }
        });

        pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfilePg.class).putExtra("username",username),0);
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