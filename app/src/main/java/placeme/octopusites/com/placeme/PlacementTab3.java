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

    String tenth, twelthordiploma, ug, liveatkt, deadatkt;
    TextView tenthview, twelthordiplomaview, ugview, studenttenthview, studenttwelthordiplomaview, studentugview;
    TextView company10txt, student10txt, companyugtxt, studentugtxt, company12txt, student12txt;
    String studenttenthmarks, studentugmarks, studenttwelthordiplomamarks;
    ImageView tentheligibility, twelthordiplomaeligibility, ugeligibility;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab3, container, false);

        SavePlacementInfoForFragment save = new SavePlacementInfoForFragment();
        tenth = save.getStdx();
        twelthordiploma = save.getStdxiiordiploma();
        ug = save.getUg();
        liveatkt = save.getNoofallowedliveatkt();
        deadatkt = save.getNoofalloweddeadatkt();
        studenttenthmarks = save.getStudenttenthmarks();
        studenttwelthordiplomamarks = save.getStudenttwelthordiplomamarks();
        studentugmarks = save.getStudentugmarks();

        company10txt = (TextView) rootView.findViewById(R.id.company10txt);
        student10txt = (TextView) rootView.findViewById(R.id.student10txt);
        companyugtxt = (TextView) rootView.findViewById(R.id.companyugtxt);
        studentugtxt = (TextView) rootView.findViewById(R.id.studentugtxt);
        company12txt = (TextView) rootView.findViewById(R.id.company12txt);
        student12txt = (TextView) rootView.findViewById(R.id.student12txt);

        company10txt.setTypeface(Z.getLight(getActivity()));
        student10txt.setTypeface(Z.getLight(getActivity()));
        companyugtxt.setTypeface(Z.getLight(getActivity()));
        studentugtxt.setTypeface(Z.getLight(getActivity()));
        company12txt.setTypeface(Z.getLight(getActivity()));
        student12txt.setTypeface(Z.getLight(getActivity()));

        tenthview = (TextView) rootView.findViewById(R.id.companytenthmarksview);
        twelthordiplomaview = (TextView) rootView.findViewById(R.id.companytwelthordiplomamarksview);
        ugview = (TextView) rootView.findViewById(R.id.companyugmarksview);
        studenttenthview = (TextView) rootView.findViewById(R.id.studenttenthmarksview);
        studenttwelthordiplomaview = (TextView) rootView.findViewById(R.id.studenttwelthordiplomamarksview);
        studentugview = (TextView) rootView.findViewById(R.id.studentugmarksview);
        tentheligibility = (ImageView) rootView.findViewById(R.id.eligibilitytenth);
        twelthordiplomaeligibility = (ImageView) rootView.findViewById(R.id.eligibilitytwelthordiploma);
        ugeligibility = (ImageView) rootView.findViewById(R.id.eligibilityug);

        tenthview.setTypeface(Z.getBold(getActivity()));
        twelthordiplomaview.setTypeface(Z.getBold(getActivity()));
        ugview.setTypeface(Z.getBold(getActivity()));
        studenttenthview.setTypeface(Z.getBold(getActivity()));
        studenttwelthordiplomaview.setTypeface(Z.getBold(getActivity()));
        studentugview.setTypeface(Z.getBold(getActivity()));

        tenthview.setText(tenth);
        twelthordiplomaview.setText(twelthordiploma);
        ugview.setText(ug);


        if (MySharedPreferencesManager.getRole(getActivity()).equals("student") || MySharedPreferencesManager.getRole(getActivity()).equals("alumni")) {

            if (studenttenthmarks != null && studenttwelthordiplomamarks != null && studentugmarks != null) {

                if (studenttenthmarks.equals("0.00")) {
                    studenttenthview.setText("N/A");

                } else {
                    studenttenthview.setText(studenttenthmarks);

                }

                if (studenttwelthordiplomamarks.equals("0.00")) {
                    studenttwelthordiplomaview.setText("N/A");

                } else {
                    studenttwelthordiplomaview.setText(studenttwelthordiplomamarks);

                }


                if (studentugmarks.equals("0.00")) {
                    studentugview.setText("N/A");

                } else {

                    studentugview.setText(studentugmarks);
                }


                Float c10, s10, c12, s12, cu, su;
                c10 = Float.parseFloat(tenth);
                c12 = Float.parseFloat(twelthordiploma);
                cu = Float.parseFloat(ug);

                s10 = Float.parseFloat(studenttenthmarks);
                s12 = Float.parseFloat(studenttwelthordiplomamarks);
                su = Float.parseFloat(studentugmarks);

                if (s10 >= c10) {
                    Drawable myDrawable = getResources().getDrawable(R.drawable.eligible_icon);
                    tentheligibility.setImageDrawable(myDrawable);
                    TextView student10txt = (TextView) rootView.findViewById(R.id.student10txt);
                    TextView studenttenthmarksview = (TextView) rootView.findViewById(R.id.studenttenthmarksview);
                    student10txt.setTextColor(getResources().getColor(R.color.sky_blue_color));
                    studenttenthmarksview.setTextColor(getResources().getColor(R.color.sky_blue_color));

                } else {
                    Drawable myDrawable = getResources().getDrawable(R.drawable.not_eligible_icon);
                    tentheligibility.setImageDrawable(myDrawable);
                    TextView student10txt = (TextView) rootView.findViewById(R.id.student10txt);
                    TextView studenttenthmarksview = (TextView) rootView.findViewById(R.id.studenttenthmarksview);
                    student10txt.setTextColor(getResources().getColor(R.color.dark_color));
                    studenttenthmarksview.setTextColor(getResources().getColor(R.color.dark_color));
                }
                if (s12 >= c12) {
                    Drawable myDrawable = getResources().getDrawable(R.drawable.eligible_icon);
                    twelthordiplomaeligibility.setImageDrawable(myDrawable);
                    TextView student10txt = (TextView) rootView.findViewById(R.id.student12txt);
                    TextView studenttenthmarksview = (TextView) rootView.findViewById(R.id.studenttwelthordiplomamarksview);
                    student10txt.setTextColor(getResources().getColor(R.color.sky_blue_color));
                    studenttenthmarksview.setTextColor(getResources().getColor(R.color.sky_blue_color));

                } else {
                    Drawable myDrawable = getResources().getDrawable(R.drawable.not_eligible_icon);
                    twelthordiplomaeligibility.setImageDrawable(myDrawable);
                    TextView student10txt = (TextView) rootView.findViewById(R.id.student12txt);
                    TextView studenttenthmarksview = (TextView) rootView.findViewById(R.id.studenttwelthordiplomamarksview);
                    student10txt.setTextColor(getResources().getColor(R.color.dark_color));
                    studenttenthmarksview.setTextColor(getResources().getColor(R.color.dark_color));
                }
                if (su >= cu) {
                    Drawable myDrawable = getResources().getDrawable(R.drawable.eligible_icon);
                    ugeligibility.setImageDrawable(myDrawable);
                    TextView student10txt = (TextView) rootView.findViewById(R.id.studentugtxt);
                    TextView studenttenthmarksview = (TextView) rootView.findViewById(R.id.studentugmarksview);
                    student10txt.setTextColor(getResources().getColor(R.color.sky_blue_color));
                    studenttenthmarksview.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    Drawable myDrawable = getResources().getDrawable(R.drawable.not_eligible_icon);
                    ugeligibility.setImageDrawable(myDrawable);
                    TextView student10txt = (TextView) rootView.findViewById(R.id.studentugtxt);
                    TextView studenttenthmarksview = (TextView) rootView.findViewById(R.id.studentugmarksview);
                    student10txt.setTextColor(getResources().getColor(R.color.dark_color));
                    studenttenthmarksview.setTextColor(getResources().getColor(R.color.dark_color));
                }
            } else {

                tentheligibility.setVisibility(View.GONE);
                TextView studenttenthmarksview = (TextView) rootView.findViewById(R.id.studenttenthmarksview);
                studenttenthmarksview.setVisibility(View.GONE);
                twelthordiplomaeligibility.setVisibility(View.GONE);
                TextView studenttenthmarksview2 = (TextView) rootView.findViewById(R.id.studenttwelthordiplomamarksview);
                studenttenthmarksview2.setVisibility(View.GONE);
                ugeligibility.setVisibility(View.GONE);
                TextView studenttenthmarksview3 = (TextView) rootView.findViewById(R.id.studentugmarksview);
                studenttenthmarksview3.setVisibility(View.GONE);
                TextView studenttenthmarksview4 = (TextView) rootView.findViewById(R.id.student10txt);
                studenttenthmarksview4.setVisibility(View.GONE);
                TextView studenttenthmarksview5 = (TextView) rootView.findViewById(R.id.student12txt);
                studenttenthmarksview5.setVisibility(View.GONE);
                TextView studenttenthmarksview6 = (TextView) rootView.findViewById(R.id.studentugtxt);
                studenttenthmarksview6.setVisibility(View.GONE);


            }
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
