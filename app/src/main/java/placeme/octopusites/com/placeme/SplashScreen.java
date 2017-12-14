package placeme.octopusites.com.placeme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//import cat.ereza.customactivityoncrash.config.CaocConfig;

//kunal khedkar 2

public class SplashScreen extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Password = "passKey";
    public static final String Intro = "intro";
    private static String url_login = "http://192.168.100.100/PlaceMe/Auth";

    private static String url_savesessiondetails = "http://192.168.100.100/PlaceMe/SaveSessionDetails";
    SharedPreferences sharedpreferences;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    String resultofop = "";
    String digest1, digest2, digest3;
    String country = "", regionName = "", city = "", isp = "", countryCode = "", query = "";
    String enccountry, encregionName, enccity, encisp, enccountryCode, encquery;
    String username;
    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";
    private String EmailCred = "";
    private String android_id, device_id;

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

    protected void onCreate(Bundle paramBundle) {

        super.onCreate(paramBundle);
        setContentView(R.layout.activity_splashscreen);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        TextView poweredbyid=(TextView)findViewById(R.id.poweredbyid);
        TextView companynamesplash=(TextView)findViewById(R.id.companynamesplash);

        poweredbyid.setTypeface(MyConstants.getLight(this));
        companynamesplash.setTypeface(MyConstants.getBold(this));

// my code
//        CaocConfig.Builder.create()
//                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
////                .showErrorDetails(false) //default: true
////                .showRestartButton(false) //default: true
//                .trackActivities(true) //default: false
////                .minTimeBetweenCrashesMs(2000) //default: 3000
//                .errorDrawable(R.mipmap.ic_launcher) //default: bug image
//                .restartActivity(SplashScreen.class) //default: null (your app's launch activity)
//                .errorActivity(MyCustomErrorActivity.class) //default: null (default error activity)
//                .apply();

        // my code end

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    android_id = Secure.getString(getApplication().getContentResolver(), Secure.ANDROID_ID);
                    TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    device_id = telephonyManager.getDeviceId();
                } catch (Exception e) {
                }

                new GetDigest().execute();



                String i = sharedpreferences.getString(Intro, null);
                String u = sharedpreferences.getString(Username, null);
                username = sharedpreferences.getString(Username, null);
                String p = sharedpreferences.getString(Password, null);
                String otp = sharedpreferences.getString("otp", null);

                String activatedCode = MySharedPreferencesManager.getData(SplashScreen.this, "activatedCode");

                if (activatedCode != null && activatedCode.equals("yes")) {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(), WelcomeGenrateCodeActivity.class));
                                finish();
                            }
                        }, 2000);

                }
                else if (otp != null) {
                    if (otp.equals("yes")) {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            }
                        }, 2000);
                    } else {

                        if (i != null) {
                            if (p != null && u != null) {
                                attemptLogin(u, p);
                            } else {
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        finish();
                                    }
                                }, 2000);
                            }
                        } else {
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(getApplicationContext(), Welcome.class));
                                    finish();
                                }
                            }, 2000);
                        }

                    }
                } else {

                    if (i != null) {
                        if (p != null && u != null) {
                            attemptLogin(u, p);
                        } else {
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    finish();
                                }
                            }, 2000);
                        }
                    } else {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(), Welcome.class));
                                finish();
                            }
                        }, 2000);
                    }

                }
            }
        }).start();


    }

    void attemptLogin(String u, String p) {
        UserLoginTask userLoginTask = new UserLoginTask(u, p);
        userLoginTask.execute();
    }

    void processOutput(final int success) {
        if (success == 2) {
            Toast.makeText(SplashScreen.this, "No Internet Connection..!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (success == 1) {

                        new SaveSessionDetails().execute();

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                MySharedPreferencesManager.save(SplashScreen.this,"role","student");
                                MySharedPreferencesManager.save(SplashScreen.this,"nameKey",EmailCred);

//                                ProfileRole r = new ProfileRole();
//                                r.setRole("student");
//                                r.setUsername(EmailCred);
                                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                finish();
                            }
                        }, 1000);

                    } else if (success == 3) {
                        new SaveSessionDetails().execute();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                MySharedPreferencesManager.save(SplashScreen.this,"role","admin");
                                MySharedPreferencesManager.save(SplashScreen.this,"nameKey",EmailCred);

//                                ProfileRole r = new ProfileRole();
//                                r.setRole("admin");
//                                r.setUsername(EmailCred);
                                startActivity(new Intent(SplashScreen.this, AdminActivity.class));
                                finish();
                            }
                        }, 1000);

                    } else if (success == 4) {
                        new SaveSessionDetails().execute();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                MySharedPreferencesManager.save(SplashScreen.this,"role","hr");
                                MySharedPreferencesManager.save(SplashScreen.this,"nameKey",EmailCred);

//                                ProfileRole r = new ProfileRole();
//                                r.setRole("hr");
//                                r.setUsername(EmailCred);
                                startActivity(new Intent(SplashScreen.this, HRActivity.class));
                                finish();
                            }
                        }, 1000);
                    } else if (success == 5) {
                        new SaveSessionDetails().execute();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                MySharedPreferencesManager.save(SplashScreen.this,"role","alumni");
                                MySharedPreferencesManager.save(SplashScreen.this,"nameKey",EmailCred);

//                                ProfileRole r = new ProfileRole();
//                                r.setRole("alumni");
//                                r.setUsername(EmailCred);

                                startActivity(new Intent(SplashScreen.this, AlumniActivity.class));
                                finish();
                            }
                        }, 1000);
                    } else if (success == 6) {

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                                finish();
                            }
                        }, 1000);
                    } else {


                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            }
                        }, 1000);
                    }
                }
            }).start();
        }
    }

    private boolean isNetworkAvailable() throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            HttpURLConnection localHttpURLConnection = (HttpURLConnection) new URL("http://192.168.100.100/").openConnection();
            localHttpURLConnection.setConnectTimeout(1000);
            localHttpURLConnection.connect();
            return true;
        }
        return false;
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Integer> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Integer doInBackground(Void... args) {
            try {
                if (isNetworkAvailable()) {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("u", mEmail));
                    params.add(new BasicNameValuePair("p", mPassword));
                    params.add(new BasicNameValuePair("t", new SharedPrefUtil(getApplicationContext()).getString("firebaseToken")));
                    json = jParser.makeHttpRequest(url_login, "GET", params);
                    String s = null;

                    s = json.getString("info");
                    resultofop = s;
                    Log.d("Msg", json.getString("info"));


                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("role", s);
                    editor.putString("pref1", "yes");
                    editor.putString("pref2", "yes");
                    editor.putString("pref3", "yes");
                    editor.putString("pref4", "yes");
                    editor.putString("pref5", "yes");
                    editor.commit();

                    if (s.equals("notactivated")) {

                        EmailCred = mEmail;

                        return 6;
                    }
                    if (s.equals("student")) {

                        EmailCred = mEmail;

                        return 1;
                    } else if (s.equals("admin")) {

                        EmailCred = mEmail;


                        return 3;
                    } else if (s.equals("hr")) {

                        EmailCred = mEmail;


                        return 4;
                    } else if (s.equals("alumni")) {

                        EmailCred = mEmail;
                        return 5;
                    }
                } else
                    return 2;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(final Integer success) {

            processOutput(success);
        }
    }

    class SaveSessionDetails extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));    //0
            params.add(new BasicNameValuePair("m", getDeviceName()));      //1
            json = jParser.makeHttpRequest(url_savesessiondetails, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }

    class GetDigest extends AsyncTask<String, String, String> {

        String info = null;
        protected String doInBackground(String... param) {

            String username = MySharedPreferencesManager.getUsername(SplashScreen.this);

            Log.d("***", "doInBackground: user "+username);
            Log.d("***", "doInBackground: aid "+android_id);
            Log.d("***", "doInBackground: did "+device_id);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("aid", android_id));
            params.add(new BasicNameValuePair("did", device_id));
            params.add(new BasicNameValuePair("u", username));

            json = jParser.makeHttpRequest(MyConstants.url_getdigest, "GET", params);
            Log.d("  ***", "doInBackground: json -"+json);
            try {
                info = json.getString("info");


                if (info != null && info.equals("success")) {

                    digest1 = json.getString("digest1");
                    digest2 = json.getString("digest2");
                    digest3 = json.getString("digest3");

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("digest1", digest1);
                    editor.putString("digest2", digest2);
                    editor.putString("digest3", digest3);
                    editor.commit();
                }

            } catch (Exception e) {
                Log.d("TAG", "doInBackground: exception in splashsreen"+e.getMessage());
                e.printStackTrace();
            }
            return info;
        }

        @Override
        protected void onPostExecute(String result) {
            if (info != null && !info.equals("success")) {
                Toast.makeText(SplashScreen.this, info, Toast.LENGTH_SHORT).show();
                new UpdateFirebaseToken().execute();
                //TODO remove comment from servlet checking aid,did is not null from getDigest
            }
        }
    }

    class UpdateFirebaseToken extends AsyncTask<String, String, String> {

        // TODO move UpdateFirebaseToken code to all base activity
        // TODO update AID,DID
        JSONObject json;
        JSONParser jParser = new JSONParser();
        String resultofop = null;

        protected String doInBackground(String... param) {
            try {

                String encUsername = MySharedPreferencesManager.getUsername(getApplicationContext());
//                String token = MySharedPreferencesManager.getData(SplashScreen.this, "firebaseToken");
                String token = new SharedPrefUtil(getApplicationContext()).getString("firebaseToken");
                Log.d("TAG", "splashScreen token\n" + token);

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", encUsername));       //0
                params.add(new BasicNameValuePair("t", ""));             //1
                json = jParser.makeHttpRequest(MyConstants.url_UpdateFirebaseToken, "GET", params);


                resultofop = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            if (resultofop.equals("success")) {
                Log.d("TAG_FIRE_IDService", "Successfully Updated token..!");
            }
        }
    }
}

