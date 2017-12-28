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


public class PlacementTabAdmin3 extends Fragment {


    TextView tenthview,twelthordiplomaview,ugview,studenttenthview,studenttwelthordiplomaview,studentugview;
    TextView aptitxt,ugtxt,diplomatxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tabadmin3, container, false);


        tenthview=(TextView)rootView.findViewById(R.id.companytenthmarksview);
        twelthordiplomaview=(TextView)rootView.findViewById(R.id.companytwelthordiplomamarksview);
        ugview=(TextView)rootView.findViewById(R.id.companyugmarksview);
        studenttenthview=(TextView)rootView.findViewById(R.id.studenttenthmarksview);
        studenttwelthordiplomaview=(TextView)rootView.findViewById(R.id.studenttwelthordiplomamarksview);
        studentugview=(TextView)rootView.findViewById(R.id.studentugmarksview);

        aptitxt=(TextView)rootView.findViewById(R.id.aptitxt);
        ugtxt=(TextView)rootView.findViewById(R.id.ugtxt);
        diplomatxt=(TextView)rootView.findViewById(R.id.diplomatxt);

        aptitxt.setTypeface(Z.getLight(getActivity()));
        ugtxt.setTypeface(Z.getLight(getActivity()));
        diplomatxt.setTypeface(Z.getLight(getActivity()));

        tenthview.setTypeface(Z.getBold(getActivity()));
        twelthordiplomaview.setTypeface(Z.getBold(getActivity()));
        ugview.setTypeface(Z.getBold(getActivity()));
        studenttenthview.setTypeface(Z.getBold(getActivity()));
        studenttwelthordiplomaview.setTypeface(Z.getBold(getActivity()));
        studentugview.setTypeface(Z.getBold(getActivity()));





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
