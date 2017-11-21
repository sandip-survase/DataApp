package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Octopus ITES on 2017-10-31.
 */

public class MySharedPreferencesManager {

    public static final String MyPREFERENCES = "MyPrefs";

    private MySharedPreferencesManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    // role , digest1 , digest2 , nameKey(Username) , plainUsername , password
    public static String getRole(Context context) {
        return getSharedPreferences(context).getString("role", null);
    }

    public static String getDigest1(Context context) {
        return getSharedPreferences(context).getString("digest1", null);
    }

    public static String getDigest2(Context context) {
        return getSharedPreferences(context).getString("digest2", null);
    }

    public static String getDigest3(Context context) {
        return getSharedPreferences(context).getString("digest3", null);
    }

    public static String getUsername(Context context) {
        return getSharedPreferences(context).getString("nameKey", null);
    }

    public static String getPlainUsername(Context context) {
        return getSharedPreferences(context).getString("plainUser", null);
    }

    public static String getPassword(Context context) {
        return getSharedPreferences(context).getString("passKey", null);
    }

    public static void save(Context context,String key,String value) {

         getSharedPreferences(context).edit().putString(key,value).commit();
    }

    public static String getData(Context context,String key) {
        return getSharedPreferences(context).getString(key, null);
    }

    public static String getInstitute(Context context) {
        return getSharedPreferences(context).getString("institute", null);
    }



}
