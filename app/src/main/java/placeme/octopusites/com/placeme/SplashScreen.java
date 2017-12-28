package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//import cat.ereza.customactivityoncrash.config.CaocConfig;

//import cat.ereza.customactivityoncrash.config.CaocConfig;


public class SplashScreen extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Password = "passKey";
    public static final String Intro = "intro";


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
        TextView poweredbyid = (TextView) findViewById(R.id.poweredbyid);
        TextView companynamesplash = (TextView) findViewById(R.id.companynamesplash);

        poweredbyid.setTypeface(Z.getLight(this));
        companynamesplash.setTypeface(Z.getBold(this));

        Log.d("TAG", "build ver : " + BuildConfig.VERSION_NAME);

        new GetDigestEcho().execute();


//        CaocConfig.Builder.create().backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT)
//                //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
////                .showErrorDetails(false) //default: true
////                .showRestartButton(false) //default: true
//                .trackActivities(true) //default: false
////                .minTimeBetweenCrashesMs(2000) //default: 3000
////                .errorDrawable(R.mipmap.ic_launcher) //default: bug image
//                .restartActivity(SplashScreen.class) //default: null (your app's launch activity)
//                .errorActivity(MyCustomErrorActivity.class) //default: null (default error activity)
//                .apply();

//        try {
//            if (!Z.CheckInternet()) {
//                Toast.makeText(this, "no Internet", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(this, NoInternet.class);
//                i.putExtra("splash", true);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_NO_HISTORY);
//                startActivity(i);
//
//            } else {
//
//                mainWork();
//
//            }
//        } catch (Exception e) {
//
//        }
    }


    public void mainWork() {

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        new UpdateFirebaseToken().execute();

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

                } else if (otp != null) {
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (success == 1) {

                        new SaveSessionDetails().execute();

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                MySharedPreferencesManager.save(SplashScreen.this, "role", "student");
                                MySharedPreferencesManager.save(SplashScreen.this, "nameKey", EmailCred);

                                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                finish();
                            }
                        }, 1000);

                    } else if (success == 3) {
                        new SaveSessionDetails().execute();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                MySharedPreferencesManager.save(SplashScreen.this, "role", "admin");
                                MySharedPreferencesManager.save(SplashScreen.this, "nameKey", EmailCred);

                                startActivity(new Intent(SplashScreen.this, AdminActivity.class));
                                finish();
                            }
                        }, 1000);

                    } else if (success == 4) {
                        new SaveSessionDetails().execute();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                MySharedPreferencesManager.save(SplashScreen.this, "role", "hr");
                                MySharedPreferencesManager.save(SplashScreen.this, "nameKey", EmailCred);

                                startActivity(new Intent(SplashScreen.this, HRActivity.class));
                                finish();
                            }
                        }, 1000);
                    } else if (success == 5) {
                        new SaveSessionDetails().execute();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                MySharedPreferencesManager.save(SplashScreen.this, "role", "alumni");
                                MySharedPreferencesManager.save(SplashScreen.this, "nameKey", EmailCred);

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

//    private boolean isNetworkAvailable() throws Exception {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
//            HttpURLConnection localHttpURLConnection = (HttpURLConnection) new URL("http://104.237.4.236").openConnection();
//            localHttpURLConnection.setConnectTimeout(1000);
//            localHttpURLConnection.connect();
//            return true;
//        }
//        return false;
//    }

    public class UserLoginTask extends AsyncTask<Void, Void, Integer> {

        private final String mEmail;
        private final String mPassword;
        String s, resultofop;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Integer doInBackground(Void... args) {
            try {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("u", mEmail));
                    params.add(new BasicNameValuePair("p", mPassword));
                    JSONObject json = jParser.makeHttpRequest(Z.url_login, "GET", params);

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
            String platform = "Android (" + getDeviceName() + ")";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));    //0
            params.add(new BasicNameValuePair("m", platform));      //1
            json = jParser.makeHttpRequest(Z.url_savesessiondetails, "GET", params);
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

    class GetDigestEcho extends AsyncTask<String, String, Integer> {

        String info = null, versionName = null, versionType = null;


        protected Integer doInBackground(String... param) {

            String username = MySharedPreferencesManager.getUsername(SplashScreen.this);

            try {
                android_id = Secure.getString(getApplication().getContentResolver(), Secure.ANDROID_ID);
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                device_id = telephonyManager.getDeviceId();
            } catch (Exception e) {
            }

            String echo_number = "" + new Random().nextInt();

            Log.d("TAG", "doInBackground: user " + username);
            Log.d("TAG", "doInBackground: aid " + android_id);
            Log.d("TAG", "doInBackground: did " + device_id);

            Log.d("TAG", "splash echo send " + echo_number);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("aid", android_id));
            params.add(new BasicNameValuePair("did", device_id));
            params.add(new BasicNameValuePair("u", username));
            params.add(new BasicNameValuePair("e", echo_number));

            JSONObject json = jParser.makeHttpRequest(Z.url_getdigest, "GET", params);
            Log.d("TAG", "GetDigest: json -------  GetDigest ------------ " + json);


            try {

                if (json != null) {

                    info = json.getString("info");
                    if (info != null) {

                        String receivedEcho = json.getString("num");

                        if (receivedEcho != null && receivedEcho.equals(echo_number)) {
                            Log.d("TAG", "splash echo recevied : " + receivedEcho);


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

                                versionName = json.getString("versionName");
                                versionType = json.getString("versionType");

                                versionName = Z.Decrypt(versionName, SplashScreen.this);
                                versionType = Z.Decrypt(versionType, SplashScreen.this);



                            } else
                                return 3;
                        } else
                            return 3;

                    } else
                        return 3;

                } else
                    return 2;  // no internet

            } catch (Exception e) {
                Log.d("TAG", "doInBackground: exception in splashScreen" + e.getMessage());
                e.printStackTrace();
                return 3;    // server prob or null or exception
            }

            return 1; // all good
        }

        @Override
        protected void onPostExecute(Integer result) {


            switch (result) {

                case 1: {
                    if (info.equals("success") && versionName != null && versionType != null) {

                        String currentVersion = BuildConfig.VERSION_NAME;

                        if (versionName.equals(currentVersion)) {
                            Log.d("TAG", "+++++++++++++++++  no update");

                            mainWork();

                        } else {
                            if (versionType.equals("force")) {
                                showForceUpdateDialog();
                            } else if (versionType.equals("optional")) {
                                showOptionalUpdateDialog();
                            }
                        }

                    }
                    break;
                }
                case 2: {

                    Intent i = new Intent(SplashScreen.this, NoInternet.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    i.putExtra("splash", true);
                    startActivity(i);

                    break;

                }
                case 3: {
                    showErrorDialog();
                    break;
                }
            }
        }
    }

    private void showErrorDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage("Could not reach to server!")
                .setCancelable(false)
                .setPositiveButton("try again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(SplashScreen.this, SplashScreen.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);       // clear stack histry new fresh call
                                startActivity(intent);
                                finish();

                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(SplashScreen.this));
            }
        });
        alertDialog.show();

    }

    private void showOptionalUpdateDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("New update available!")
                .setMessage("Please, update app to new version.")
                .setCancelable(false)
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String packageName = getPackageName();
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details:id=" + packageName)));
                                finish();

                            }
                        })

                .setNegativeButton("NO,THANKS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        mainWork();

                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(SplashScreen.this));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(SplashScreen.this));
            }
        });

        alertDialog.show();
    }

    private void showForceUpdateDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setTitle("New update available!")
                .setMessage("Please, update app to new version.")
                .setCancelable(false)
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String packageName = getPackageName();
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details:id=" + packageName)));
                                //TODO check playStore link
                                finish();

                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(SplashScreen.this));
            }
        });
        alertDialog.show();
    }

    class UpdateFirebaseToken extends AsyncTask<String, String, String> {


        JSONObject json;
        JSONParser jParser = new JSONParser();
        String resultofop = null;

        protected String doInBackground(String... param) {
            try {

                String encUsername = MySharedPreferencesManager.getUsername(getApplicationContext());
                String token = new SharedPrefUtil(getApplicationContext()).getString("firebaseToken");
                Log.d("TAG", "splashScreen token\n" + token);

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", encUsername));       //0
                params.add(new BasicNameValuePair("t", token));             //1
                json = jParser.makeHttpRequest(Z.url_UpdateFirebaseToken, "GET", params);
                Log.d("TAG", "token json splash: " + json);

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


    private boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

}

