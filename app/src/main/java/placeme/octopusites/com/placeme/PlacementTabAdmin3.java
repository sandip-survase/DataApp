package placeme.octopusites.com.placeme;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class PlacementTabAdmin3 extends Fragment {


    TextView tenthview,twelthordiplomaview,ugview,studenttenthview,studenttwelthordiplomaview,studentugview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tabadmin3, container, false);


        tenthview=(TextView)rootView.findViewById(R.id.companytenthmarksview);
        twelthordiplomaview=(TextView)rootView.findViewById(R.id.companytwelthordiplomamarksview);
        ugview=(TextView)rootView.findViewById(R.id.companyugmarksview);
        studenttenthview=(TextView)rootView.findViewById(R.id.studenttenthmarksview);
        studenttwelthordiplomaview=(TextView)rootView.findViewById(R.id.studenttwelthordiplomamarksview);
        studentugview=(TextView)rootView.findViewById(R.id.studentugmarksview);




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
