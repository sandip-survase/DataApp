package placeme.octopusites.com.placeme;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class PlacementTabAdmin2 extends Fragment {


    TextView aptiview,techtestview,gdview,techinterviewview,hrinterviewview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tabadmin2, container, false);



        aptiview=(TextView)rootView.findViewById(R.id.companyaptiview);
        techtestview=(TextView)rootView.findViewById(R.id.companytechtestview);
        gdview=(TextView)rootView.findViewById(R.id.companygdview);
        techinterviewview=(TextView)rootView.findViewById(R.id.companytechinterviewview);
        hrinterviewview=(TextView)rootView.findViewById(R.id.companyhrinterviewview);



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
}
