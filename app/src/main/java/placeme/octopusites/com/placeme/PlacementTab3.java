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


public class PlacementTab3 extends Fragment {

    String tenth,twelthordiploma,ug,liveatkt,deadatkt;
    TextView tenthview,twelthordiplomaview,ugview,studenttenthview,studenttwelthordiplomaview,studentugview;
    String studenttenthmarks,studentugmarks,studenttwelthordiplomamarks;
    ImageView tentheligibility,twelthordiplomaeligibility,ugeligibility;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab3, container, false);

        SavePlacementInfoForFragment save=new SavePlacementInfoForFragment();
        tenth=save.getStdx();
        twelthordiploma=save.getStdxiiordiploma();
        ug=save.getUg();
        liveatkt=save.getNoofallowedliveatkt();
        deadatkt=save.getNoofalloweddeadatkt();
        studenttenthmarks=save.getStudenttenthmarks();
        studenttwelthordiplomamarks=save.getStudenttwelthordiplomamarks();
        studentugmarks=save.getStudentugmarks();

        tenthview=(TextView)rootView.findViewById(R.id.companytenthmarksview);
        twelthordiplomaview=(TextView)rootView.findViewById(R.id.companytwelthordiplomamarksview);
        ugview=(TextView)rootView.findViewById(R.id.companyugmarksview);
        studenttenthview=(TextView)rootView.findViewById(R.id.studenttenthmarksview);
        studenttwelthordiplomaview=(TextView)rootView.findViewById(R.id.studenttwelthordiplomamarksview);
        studentugview=(TextView)rootView.findViewById(R.id.studentugmarksview);
        tentheligibility=(ImageView) rootView.findViewById(R.id.eligibilitytenth);
        twelthordiplomaeligibility=(ImageView) rootView.findViewById(R.id.eligibilitytwelthordiploma);
        ugeligibility=(ImageView) rootView.findViewById(R.id.eligibilityug);

        tenthview.setText(tenth);
        twelthordiplomaview.setText(twelthordiploma);
        ugview.setText(ug);
        studenttenthview.setText(studenttenthmarks);
        studenttwelthordiplomaview.setText(studenttwelthordiplomamarks);
        studentugview.setText(studentugmarks);
//
        Float c10,s10,c12,s12,cu,su;
        c10= Float.parseFloat(tenth);
        c12= Float.parseFloat(twelthordiploma);
        cu= Float.parseFloat(ug);

        s10= Float.parseFloat(studenttenthmarks);
        s12= Float.parseFloat(studenttwelthordiplomamarks);
        su= Float.parseFloat(studentugmarks);


        if(s10>=c10)
        {
            Drawable myDrawable = getResources().getDrawable(R.drawable.check);
            tentheligibility.setImageDrawable(myDrawable);
            TextView student10txt=(TextView)rootView.findViewById(R.id.student10txt);
            TextView studenttenthmarksview=(TextView)rootView.findViewById(R.id.studenttenthmarksview);
            student10txt.setTextColor(Color.GREEN);
            studenttenthmarksview.setTextColor(Color.GREEN);

        }
        else
        {
            Drawable myDrawable = getResources().getDrawable(R.drawable.incorrect);
            tentheligibility.setImageDrawable(myDrawable);
            TextView student10txt=(TextView)rootView.findViewById(R.id.student10txt);
            TextView studenttenthmarksview=(TextView)rootView.findViewById(R.id.studenttenthmarksview);
            student10txt.setTextColor(Color.RED);
            studenttenthmarksview.setTextColor(Color.RED);
        }
        if(s12>=c12)
        {
            Drawable myDrawable = getResources().getDrawable(R.drawable.check);
            twelthordiplomaeligibility.setImageDrawable(myDrawable);
            TextView student10txt=(TextView)rootView.findViewById(R.id.student12txt);
            TextView studenttenthmarksview=(TextView)rootView.findViewById(R.id.studenttwelthordiplomamarksview);
            student10txt.setTextColor(Color.GREEN);
            studenttenthmarksview.setTextColor(Color.GREEN);

        }
        else
        {
            Drawable myDrawable = getResources().getDrawable(R.drawable.incorrect);
            twelthordiplomaeligibility.setImageDrawable(myDrawable);
            TextView student10txt=(TextView)rootView.findViewById(R.id.student12txt);
            TextView studenttenthmarksview=(TextView)rootView.findViewById(R.id.studenttwelthordiplomamarksview);
            student10txt.setTextColor(Color.RED);
            studenttenthmarksview.setTextColor(Color.RED);
        }
        if(su>=cu)
        {
            Drawable myDrawable = getResources().getDrawable(R.drawable.check);
            ugeligibility.setImageDrawable(myDrawable);
            TextView student10txt=(TextView)rootView.findViewById(R.id.studentugtxt);
            TextView studenttenthmarksview=(TextView)rootView.findViewById(R.id.studentugmarksview);
            student10txt.setTextColor(Color.GREEN);
            studenttenthmarksview.setTextColor(Color.GREEN);
        }
        else
        {
            Drawable myDrawable = getResources().getDrawable(R.drawable.incorrect);
            ugeligibility.setImageDrawable(myDrawable);
            TextView student10txt=(TextView)rootView.findViewById(R.id.studentugtxt);
            TextView studenttenthmarksview=(TextView)rootView.findViewById(R.id.studentugmarksview);
            student10txt.setTextColor(Color.RED);
            studenttenthmarksview.setTextColor(Color.RED);
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
