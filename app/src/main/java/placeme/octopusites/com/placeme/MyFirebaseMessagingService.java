package placeme.octopusites.com.placeme;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Base64;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    String role, pref1, pref2, pref3, pref4, pref5;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        int showFlag = 0;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        role = sharedpreferences.getString("role", null);

        pref1 = sharedpreferences.getString("pref1", null);
        pref2 = sharedpreferences.getString("pref2", null);
        pref3 = sharedpreferences.getString("pref3", null);
        pref4 = sharedpreferences.getString("pref4", null);
        pref5 = sharedpreferences.getString("pref5", null);

        if (remoteMessage == null)
            return;


        if (remoteMessage.getData().size() > 0) {

            if (pref1.equals("no")) {
                showFlag = 0;
            } else {
                showFlag = 1;
            }

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                JSONObject data = json.getJSONObject("data");
                String sender = data.getString("sender");
                String receiver = data.getString("receiver");
                String message = data.getString("message");
                String sender_uid = data.getString("sender_uid");
                String receiver_uid = data.getString("receiver_uid");

                StringBuffer sb = new StringBuffer("");
                int index1 = message.indexOf("pLACEmeMsGTime");
                for (int j = 0; j < index1; j++) {
                    sb.append(message.charAt(j));
                }
                String extractedMessage = sb.toString();
                sb = new StringBuffer("");
                index1 += 14;
                for (int j = index1; j < message.length(); j++) {
                    sb.append(message.charAt(j));
                }
                String extractedTime = sb.toString();

                ArrayList<String> name1 = new ArrayList<>();
                DatabaseHelper helper = new DatabaseHelper(this);
                name1 = helper.getName(sender);

                Intent intent = new Intent(this, MessageActivity.class);
                intent.putExtra("fname", name1.get(0));
                intent.putExtra("lname", name1.get(1));
                intent.putExtra("uploadedby", name1.get(2));
                intent.putExtra("signature", name1.get(3));
                intent.putExtra("sender", receiver);
                intent.putExtra("receiver", sender);
                intent.putExtra("sender_uid", receiver_uid);
                intent.putExtra("receiver_uid", sender_uid);


                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.logo);

                String previouslyEncodedImage = MySharedPreferencesManager.getData(this,sender);
                if( !previouslyEncodedImage.equalsIgnoreCase("") ){
                    byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
                    icon = BitmapFactory.decodeByteArray(b, 0, b.length);

                }

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.bar)
                                .setColor(Color.parseColor("#03353E"))
                                .setLargeIcon(icon)
                                .setContentTitle(name1.get(0))
                                .setContentText(extractedMessage)
                                .setAutoCancel(true)
                                .setSound(defaultSoundUri)
                                .setContentIntent(pendingIntent);


                if (helper.isChatRoomActive(sender_uid, receiver_uid)) {
                    Ringtone r = RingtoneManager.getRingtone(this, defaultSoundUri);
                    r.play();
                } else {

                    if (role.equals("student")) {
                        if (showFlag == 1) {
                            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            manager.notify(0, builder.build());

                            Intent pushNotification = new Intent("pushNotification");
                            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
                        }
                    }
                    else if (role.equals("admin")) {
                        if (showFlag == 1) {
                            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            manager.notify(0, builder.build());

                            Intent pushNotification = new Intent("pushNotification");
                            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
                        }
                    }
                }


            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }

    }

}