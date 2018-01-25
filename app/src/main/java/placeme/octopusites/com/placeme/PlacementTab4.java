package placeme.octopusites.com.placeme;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ConnectionQuality;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.androidnetworking.interfaces.ConnectionQualityChangeListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;


public class PlacementTab4 extends Fragment {

    String Courses,TAG="PlacementTab4";
    TextView CoursesNdStrams,mainheading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_placement_tab4, container, false);

        SavePlacementInfoForFragment save = new SavePlacementInfoForFragment();
        Courses = save.getForwhichcourse();

        CoursesNdStrams = (TextView) rootView.findViewById(R.id.CoursesNdStrams);
        mainheading = (TextView) rootView.findViewById(R.id.mainheading);


        mainheading.setTypeface(Z.getLight(getActivity()));

        CoursesNdStrams.setTypeface(Z.getBold(getActivity()));



        if (Courses != null) {
            CoursesNdStrams.setText(Courses);
            Log.d(TAG, "Courses: "+Courses);




        }else{
            CoursesNdStrams.setText("N/A");
        }


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
