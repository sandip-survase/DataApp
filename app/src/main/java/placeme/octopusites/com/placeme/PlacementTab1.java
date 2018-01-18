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


public class PlacementTab1 extends Fragment {

    String companyname, cpackage, lastdateofreg, post, vacancies, bond, dateofarrival, uploadedby;
    TextView companynameview, cpackageview, lastdateofregview, postview, vacanciesview, bondview, dateofarrivalview;
    TextView companynametxt, posttxt, packagetxt, vacancytxt, lastdateofregtxt, bondtxt, dateofarrtxt, uploadedtxt;
    private String TAG = "tab1";
    String Uploader_FLname = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab1, container, false);

        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        OkHttpUtil.getClient();
//        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.initialize(getActivity(), OkHttpUtil.getClient());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        AndroidNetworking.setBitmapDecodeOptions(options);
        AndroidNetworking.enableLogging();
        AndroidNetworking.setConnectionQualityChangeListener(new ConnectionQualityChangeListener() {
            @Override
            public void onChange(ConnectionQuality currentConnectionQuality, int currentBandwidth) {
                Log.d(TAG, "onChange: currentConnectionQuality : " + currentConnectionQuality + " currentBandwidth : " + currentBandwidth);
            }
        });
        SavePlacementInfoForFragment save = new SavePlacementInfoForFragment();
        companyname = save.getCompanyname();
        cpackage = save.getPackage();
        lastdateofreg = save.getLastdateofregistration();
        post = save.getPost();
        vacancies = save.getVacancies();
        bond = save.getBond();
        dateofarrival = save.getDateofarrival();
        uploadedby = save.getUploadedby();


        companynametxt = (TextView) rootView.findViewById(R.id.companynametxt);
        posttxt = (TextView) rootView.findViewById(R.id.posttxt);
        packagetxt = (TextView) rootView.findViewById(R.id.packagetxt);
        vacancytxt = (TextView) rootView.findViewById(R.id.vacancytxt);
        lastdateofregtxt = (TextView) rootView.findViewById(R.id.lastdateofregtxt);
        bondtxt = (TextView) rootView.findViewById(R.id.bondtxt);
        dateofarrtxt = (TextView) rootView.findViewById(R.id.dateofarrtxt);
        uploadedtxt = (TextView) rootView.findViewById(R.id.uploadedtxt);


        companynameview = (TextView) rootView.findViewById(R.id.companynameview);
        lastdateofregview = (TextView) rootView.findViewById(R.id.companylastdateofregview);
        postview = (TextView) rootView.findViewById(R.id.companypostview);
        vacanciesview = (TextView) rootView.findViewById(R.id.companyvacanciesview);
        bondview = (TextView) rootView.findViewById(R.id.companybondview);
        dateofarrivalview = (TextView) rootView.findViewById(R.id.companydateofarrview);
        cpackageview = (TextView) rootView.findViewById(R.id.companypackageview);

        companynameview.setText(companyname);
        lastdateofregview.setText(lastdateofreg);
        postview.setText(post);
        vacanciesview.setText(vacancies);
        bondview.setText(bond);
        dateofarrivalview.setText(dateofarrival);
        cpackageview.setText(cpackage);

        companynametxt.setTypeface(Z.getLight(getActivity()));
        posttxt.setTypeface(Z.getLight(getActivity()));
        packagetxt.setTypeface(Z.getLight(getActivity()));
        vacancytxt.setTypeface(Z.getLight(getActivity()));
        lastdateofregtxt.setTypeface(Z.getLight(getActivity()));
        bondtxt.setTypeface(Z.getLight(getActivity()));
        dateofarrtxt.setTypeface(Z.getLight(getActivity()));


        companynameview.setTypeface(Z.getBold(getActivity()));
        lastdateofregview.setTypeface(Z.getBold(getActivity()));
        postview.setTypeface(Z.getBold(getActivity()));
        vacanciesview.setTypeface(Z.getBold(getActivity()));
        bondview.setTypeface(Z.getBold(getActivity()));
        dateofarrivalview.setTypeface(Z.getBold(getActivity()));
        cpackageview.setTypeface(Z.getBold(getActivity()));
        RefreshNotificationCount();

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

    private void RefreshNotificationCount() {
//        AndroidNetworking.get(Z.url_GetNotificationsAdminAdminMetaData)
        AndroidNetworking.post(Z.GetFLName)
                .setTag(this)
                .addQueryParameter("u", uploadedby)
                .setPriority(Priority.MEDIUM)
                .setOkHttpClient(OkHttpUtil.getClient())
                .getResponseOnlyFromNetwork()
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Log.d(TAG, "onResponse object : " + response.toString());
                        try {
                            if (response.getString("info").equals("success")){
                            Uploader_FLname = response.getString("flname");
                                uploadedtxt.setText(Uploader_FLname);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received ANError from server
                            // error.getErrorCode() - the ANError code from server
                            // error.getErrorBody() - the ANError body from server
                            // error.getErrorDetail() - just a ANError detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }

}
