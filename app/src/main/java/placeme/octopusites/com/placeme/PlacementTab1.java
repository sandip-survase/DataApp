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


public class PlacementTab1 extends Fragment {

    String companyname,cpackage,lastdateofreg,post,vacancies,bond,dateofarrival;

    TextView companynameview,cpackageview,lastdateofregview,postview,vacanciesview,bondview,dateofarrivalview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab1, container, false);

        SavePlacementInfoForFragment save=new SavePlacementInfoForFragment();
        companyname=save.getCompanyname();
        cpackage=save.getPackage();
        lastdateofreg=save.getLastdateofregistration();
        post=save.getPost();
        vacancies=save.getVacancies();
        bond=save.getBond();
        dateofarrival=save.getDateofarrival();


        companynameview=(TextView)rootView.findViewById(R.id.companynameview);
        lastdateofregview=(TextView)rootView.findViewById(R.id.companylastdateofregview);
        postview=(TextView)rootView.findViewById(R.id.companypostview);
        vacanciesview=(TextView)rootView.findViewById(R.id.companyvacanciesview);
        bondview=(TextView)rootView.findViewById(R.id.companybondview);
        dateofarrivalview=(TextView)rootView.findViewById(R.id.companydateofarrview);
        cpackageview=(TextView)rootView.findViewById(R.id.companypackageview);

        companynameview.setText(companyname);
        lastdateofregview.setText(lastdateofreg);
        postview.setText(post);
        vacanciesview.setText(vacancies);
        bondview.setText(bond);
        dateofarrivalview.setText(dateofarrival);
        cpackageview.setText(cpackage);

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
