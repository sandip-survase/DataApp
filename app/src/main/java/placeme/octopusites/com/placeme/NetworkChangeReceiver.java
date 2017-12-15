package placeme.octopusites.com.placeme;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.List;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private static boolean firstConnect = true;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("TAG", "--------------------------- onReceive: ---------------------- "+firstConnect);
        try {
            if (isAppOnForeground(context)) {

                if (firstConnect) {     //onReceive call two times

                    if (isOnline(context)) {

                        Log.d("TAG", "Online Connect Intenet ");

                    } else {

                        Log.d("TAG", "Connectivity Failure !!! ");

                        Intent i = new Intent(context, NoInternet.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        context.startActivity(i);

                    }
                    firstConnect=false;
                }
                else
                    firstConnect=true;
            }else{
                Log.d("TAG", "App is NOT on Foreground ");
            }


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }


//    public boolean isRunning(Context ctx) {
//        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
//
//        for (ActivityManager.RunningTaskInfo task : tasks) {
//            if (ctx.getPackageName().equalsIgnoreCase(task.baseActivity.getPackageName()))
//                return true;
//        }
//
//        return false;
//    }


}