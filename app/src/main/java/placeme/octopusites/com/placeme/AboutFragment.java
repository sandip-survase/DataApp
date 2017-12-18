package placeme.octopusites.com.placeme;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AboutFragment extends Fragment {



    public AboutFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

//        Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/cabinsemibold.ttf");
//        Typeface custom_font4 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/maven.ttf");

        TextView aboutplacemetxt=(TextView)rootView.findViewById(R.id.aboutplacemetxt);
        TextView versiontxt=(TextView)rootView.findViewById(R.id.versiontxt);
        TextView copyrighttxt=(TextView)rootView.findViewById(R.id.copyrighttxt);
        TextView allrightstxt=(TextView)rootView.findViewById(R.id.allrightstxt);
        TextView licensetxt=(TextView)rootView.findViewById(R.id.licensetxt);

        aboutplacemetxt.setTypeface(Z.getRighteous(getActivity()));
        versiontxt.setTypeface(Z.getLight(getActivity()));
        copyrighttxt.setTypeface(Z.getBold(getActivity()));
        allrightstxt.setTypeface(Z.getLight(getActivity()));
        licensetxt.setTypeface(Z.getBold(getActivity()));

        licensetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),LicenseActivity.class));
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
    public void onAttach(final Activity activity) {

        super.onAttach(activity);

        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(final Menu menu) {

        super.onPrepareOptionsMenu(menu);

        menu.clear();
    }
}
