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

public class PlacementTab2 extends Fragment {

    String apti,techtest,gd,techinterview,hrinterview;
    TextView aptiview,techtestview,gdview,techinterviewview,hrinterviewview;
    TextView aptitxt,companygdtxt,companytechtesttxt,companytechinterviewtxt,companyhrinterviewtxt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab2, container, false);

        SavePlacementInfoForFragment save=new SavePlacementInfoForFragment();
        apti=save.getNoofapti();
        techtest=save.getNooftechtest();
        gd=save.getNoofgd();
        techinterview=save.getNoofti();
        hrinterview=save.getNoofhri();

        aptitxt=(TextView)rootView.findViewById(R.id.aptitxt);
        companygdtxt=(TextView)rootView.findViewById(R.id.companygdtxt);
        companytechtesttxt=(TextView)rootView.findViewById(R.id.companytechtesttxt);
        companytechinterviewtxt=(TextView)rootView.findViewById(R.id.companytechinterviewtxt);
        companyhrinterviewtxt=(TextView)rootView.findViewById(R.id.companyhrinterviewtxt);

        aptiview=(TextView)rootView.findViewById(R.id.companyaptiview);
        techtestview=(TextView)rootView.findViewById(R.id.companytechtestview);
        gdview=(TextView)rootView.findViewById(R.id.companygdview);
        techinterviewview=(TextView)rootView.findViewById(R.id.companytechinterviewview);
        hrinterviewview=(TextView)rootView.findViewById(R.id.companyhrinterviewview);

        aptiview.setText(apti);
        techtestview.setText(techtest);
        gdview.setText(gd);
        techinterviewview.setText(techinterview);
        hrinterviewview.setText(hrinterview);

        aptitxt.setTypeface(Z.getLight(getActivity()));
        companygdtxt.setTypeface(Z.getLight(getActivity()));
        companytechtesttxt.setTypeface(Z.getLight(getActivity()));
        companytechinterviewtxt.setTypeface(Z.getLight(getActivity()));
        companyhrinterviewtxt.setTypeface(Z.getLight(getActivity()));

        aptiview.setTypeface(Z.getBold(getActivity()));
        techtestview.setTypeface(Z.getBold(getActivity()));
        gdview.setTypeface(Z.getBold(getActivity()));
        techinterviewview.setTypeface(Z.getBold(getActivity()));
        hrinterviewview.setTypeface(Z.getBold(getActivity()));

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
