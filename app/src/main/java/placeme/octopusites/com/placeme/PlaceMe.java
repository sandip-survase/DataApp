package placeme.octopusites.com.placeme;

import android.app.Application;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ConnectionQuality;
import com.androidnetworking.interfaces.ConnectionQualityChangeListener;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by sunny on 1/9/2018.
 */

public class PlaceMe extends Application {

    private static PlaceMe appInstance = null;
    private static final String TAG = "PlaceMe";


    public static PlaceMe getInstance() {
        return appInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;



        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                . writeTimeout(120, TimeUnit.SECONDS)
                .build();

        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        OkHttpUtil.getClient();

//        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.initialize(getApplicationContext(), OkHttpUtil.getClient());
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

    }
}
