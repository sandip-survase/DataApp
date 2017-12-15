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


public class PlacementCreateTab2 extends Fragment {
    EditText apti,techtest,groupdisc,techinterview,Hrinterview;
    String sapti="",stechtest="",sgroupdisc="",stechinterview="",sHrinterview="";

    int Erroflag=0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.create_placement_tab2, container, false);

        apti=(EditText)rootView.findViewById(R.id.apti);
        techtest=(EditText)rootView.findViewById(R.id.techtest);
        groupdisc=(EditText)rootView.findViewById(R.id.gd);
        techinterview=(EditText)rootView.findViewById(R.id.techinterview);
        Hrinterview=(EditText)rootView.findViewById(R.id.hrinterview);

        PlacementEditData getdata=new  PlacementEditData();
        String activitytag = getdata.getActivityFromtag();
        Log.d("activitytag", "onCreateView: "+activitytag);
        if (activitytag.contains("EditPlacement")) {
            sapti = getdata.getNoofapti();
            stechtest = getdata.getNooftechtest();
            sgroupdisc = getdata.getNoofgd();
            stechinterview = getdata.getNoofti();
            sHrinterview = getdata.getNoofhri();

            if (sapti.length() > 0) {
                apti.setText(sapti);
            }
            if (stechtest.length() > 0) {
                techtest.setText(stechtest);
            }
            if (sgroupdisc.length() > 0) {
                groupdisc.setText(sgroupdisc);
            }
            if (stechinterview.length() > 0) {
                techinterview.setText(stechinterview);
            }
            if (sHrinterview.length() > 0) {
                Hrinterview.setText(sHrinterview);
            }

        }else if(activitytag.contains("HrActivityEdit")){
            sapti = getdata.getNoofapti();
            stechtest = getdata.getNooftechtest();
            sgroupdisc = getdata.getNoofgd();
            stechinterview = getdata.getNoofti();
            sHrinterview = getdata.getNoofhri();

            if (sapti.length() > 0) {
                apti.setText(sapti);
            }
            if (stechtest.length() > 0) {
                techtest.setText(stechtest);
            }
            if (sgroupdisc.length() > 0) {
                groupdisc.setText(sgroupdisc);
            }
            if (stechinterview.length() > 0) {
                techinterview.setText(stechinterview);
            }
            if (sHrinterview.length() > 0) {
                Hrinterview.setText(sHrinterview);
            }
        }







        return rootView;
    }


    public Boolean validate(){


        sapti= apti.getText().toString();
        stechtest= techtest.getText().toString();
        sgroupdisc= groupdisc.getText().toString();
        stechinterview= techinterview.getText().toString();
        sHrinterview= Hrinterview.getText().toString();


        Erroflag=0;

        if(sapti.length()<1){
            apti.setError("Enter Valid no of Aptitude tests");
            Erroflag=1;

        } else if(stechtest.length()<1){
            techtest.setError("Enter Valid no of technical tests");
            Erroflag=1;

        }else if(sgroupdisc.length()<1){
            groupdisc.setError("Enter Valid no of GD Rounds");
            Erroflag=1;

        } else if(stechinterview.length()<1){
            techinterview.setError("Enter Valid no of technical interviews");
            Erroflag=1;

        }
        else if(sHrinterview.length()<1){
            Hrinterview.setError("Enter Valid no of HR interviews ");
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
