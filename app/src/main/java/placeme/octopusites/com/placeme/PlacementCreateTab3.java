package placeme.octopusites.com.placeme;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;


public class PlacementCreateTab3 extends Fragment {

    EditText xcriteria ,xiicriteria,ugcriteria,pgcriteria;
    String sxcriteria="" ,sxiicriteria="",sugcriteria="",spgcriteria="",sforwhome="";
    int  Erroflag=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.create_placement_tab3, container, false);

        xcriteria=(EditText)rootView.findViewById(R.id.xcriteria);
        xiicriteria=(EditText)rootView.findViewById(R.id.xiicriteria);
        ugcriteria=(EditText)rootView.findViewById(R.id.ugcriteria);
        pgcriteria=(EditText)rootView.findViewById(R.id.pgcriteria);



        PlacementEditData getdata=new  PlacementEditData();
        String activitytag = getdata.getActivityFromtag();
        Log.d("activitytag", "onCreateView: "+activitytag);
        if (activitytag.contains("EditPlacement")) {
            sxcriteria = getdata.getStdx();
            sxiicriteria = getdata.getStdxiiordiploma();
            sugcriteria = getdata.getUg();
            spgcriteria = getdata.getPg();


            if (sxcriteria.length() > 0) {
                xcriteria.setText(sxcriteria);
            }
            if (sxiicriteria.length() > 0) {
                xiicriteria.setText(sxiicriteria);
            }
            if (sugcriteria.length() > 0) {
                ugcriteria.setText(sugcriteria);
            }
            if (spgcriteria.length() > 0) {
                pgcriteria.setText(spgcriteria);
            }
        }


        return rootView;
    }
    public Boolean validate(){


        sxcriteria= xcriteria.getText().toString();
        sxiicriteria= xiicriteria.getText().toString();
        sugcriteria= ugcriteria.getText().toString();
        spgcriteria= pgcriteria.getText().toString();


        Erroflag=0;

        if(sxcriteria.length()<2){
            xcriteria.setError("Invalid Company Name");
            Erroflag=1;

        } else if(sxiicriteria.length()<2){
            xiicriteria.setError("Enter Valid Package");
            Erroflag=1;

        }else if(sugcriteria.length()<2){
            ugcriteria.setError("Enter Valid Post");
            Erroflag=1;

        } else if(spgcriteria.length()<2){
            pgcriteria.setError("Enter Valid Vacancies");
            Erroflag=1;
        }



        if (Erroflag == 0)
        {
            return true;
        }
        else{
            return false;

        }
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
