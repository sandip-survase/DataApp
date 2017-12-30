package placeme.octopusites.com.placeme;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.support.v4.app.NotificationManagerCompat.IMPORTANCE_HIGH;
import static android.support.v4.app.NotificationManagerCompat.IMPORTANCE_MAX;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String MyPREFERENCES = "MyPrefs";
    private static final String TAG = "MyFirebaseMsgService";
    static int count = 0;  // notification id ...increase this count when new type of notifiation arrives
    static int countfornotiff = 0;
    static int countforplace = 0;
    static int countformessagesenders = 0;
    static String stro = "", stro2 = "";
    static String notiffbigtext = "", notiffbigtext2 = "";
    String chatBigText = null;
    String chatSubText = null;

    static ArrayList<String> companynameslist = new ArrayList<>();
    static ArrayList<String> packagelists = new ArrayList<>();
    static ArrayList<String> postlists = new ArrayList<>();
    static ArrayList<String> ldrlists = new ArrayList<>();
    static ArrayList<String> vacantlist = new ArrayList<>();
    static ArrayList<String> messages = new ArrayList<>();
    static ArrayList<String> messageSenders = new ArrayList<>();


    static ArrayList<String> notificationtitlelist = new ArrayList<>();
    static ArrayList<String> notificationcontentlist = new ArrayList<>();


    SharedPreferences sharedpreferences;
    String role, pref1, pref2, pref3, pref4, pref5;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "push received");
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null) {

        } else {
            Log.d(TAG, "remoteMessage.getNotification() NULL");
        }

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

        Log.d(TAG, "onMessageReceived: getFrom" + remoteMessage.getFrom());
        Log.d(TAG, "onMessageReceived: getMessageId" + remoteMessage.getMessageId());
        Log.d(TAG, "onMessageReceived: getSentTime" + remoteMessage.getSentTime());
        Log.d(TAG, "onMessageReceived: getTo" + remoteMessage.getTo());
        Log.d(TAG, "onMessageReceived: getFrom" + remoteMessage.getFrom());
        try {
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            JSONObject json = new JSONObject(remoteMessage.getData().toString());
            JSONObject data = json.getJSONObject("data");

            if (remoteMessage.getData().size() > 0) {
                Log.d(TAG, "payload:present");

                String Notificationtags = data.getString("Notificationtags");


                if (Notificationtags.equals("notificationFromhr")) {
                    String sender = data.getString("sender");
                    String receiver = data.getString("receiver");
                    String title = data.getString("title");
                    String ids = data.getString("ids");
                    String notification = data.getString("notification");
                    String uploadtime = data.getString("uploadtime");
                    String lastmodified = data.getString("lastmodified");
                    String file1 = data.getString("file1");
                    String file2 = data.getString("file2");
                    String file3 = data.getString("file3");
                    String file4 = data.getString("file4");
                    String file5 = data.getString("file5");

                    String senderdec = AES4all.Decrypt(sender, MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));
//                    String  lastmodifiedDEC=AES4all.Decrypt(lastmodified,MySharedPreferencesManager.getDigest1(this),MySharedPreferencesManager.getDigest2(this));


                    Intent intent = new Intent(this, ViewNotification.class);
                    intent.putExtra("receiver", receiver);
                    intent.putExtra("uploadedby", sender);
                    intent.putExtra("id", ids);
                    intent.putExtra("title", title);
                    intent.putExtra("notification", notification);
                    intent.putExtra("uploadtime", uploadtime);
                    intent.putExtra("lastmodified", lastmodified);
                    intent.putExtra("file1", file1);
                    intent.putExtra("file2", file2);
                    intent.putExtra("file3", file3);
                    intent.putExtra("file4", file4);
                    intent.putExtra("file5", file5);
                    String Bigmsg = " \n" + notification;

                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                     int countforsame = 0;
                    count = 1;

                    if (countfornotiff == 0) {
                        notiffbigtext =
                                title + "\t" + notification + "";

                        notificationtitlelist.add(title);
                        notificationcontentlist.add(notification);

                    } else {
                        notificationtitlelist.add(title);
                        notificationcontentlist.add(notification);


                        notiffbigtext2 += "" + title + "\t" + notification + "\n";

                        notiffbigtext = notificationtitlelist.get(0) + "\t" + notificationcontentlist.get(0) +
                                "\n" + notiffbigtext2;

                        senderdec = countfornotiff + "\tNotifications From PLACEME.";
                        title = "NOTIFICATIONS";

                    }


                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(this)
                                    .setLargeIcon(icon)
                                    .setSmallIcon(R.drawable.bar)
                                    .setContentTitle(title)
                                    .setContentText(notification)
                                    .setAutoCancel(true)
                                    .setSound(defaultSoundUri)
                                    .setNumber(++countfornotiff)
                                    .setStyle(new NotificationCompat.BigTextStyle()
                                            .bigText(Bigmsg))
                                    .setSubText(senderdec)
                                    .setStyle(new NotificationCompat.BigTextStyle()
                                            .bigText(notiffbigtext))
                                    .setTicker("notifications from PLACEME")
//                                    .setContent(remoteViews)
                                    .setPriority(IMPORTANCE_HIGH)
                                    .setContentIntent(pendingIntent);
                    Ringtone r = RingtoneManager.getRingtone(this, defaultSoundUri);
                    r.play();
                    Log.d(TAG, "count" + count);


                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(count, builder.build());
//                    countfornotiff++;


                } else if (Notificationtags.equals("PlacemetsforAll")) {
                    // message or chat
                    Log.d(TAG, "Placemnt from hr recieved");


                    if (pref1.equals("no")) {
                        showFlag = 0;
                    } else {
                        showFlag = 1;
                    }

                    String sender = data.getString("sender");
//                    String ids = data.getString("ids");
                    String Companyname = data.getString("Companyname");
                    String cpackage = data.getString("package");
                    String post = data.getString("post");
                    String forwhichcourse = data.getString("forwhichcourse");
                    String forwhichstreams = data.getString("forwhichstreams");
                    String vacancies = data.getString("vacancies");
                    String lastdateofregistration = data.getString("lastdateofregistration");
                    String dateofarrival = data.getString("dateofarrival");
                    String bond = data.getString("bond");
                    String noofapti = data.getString("noofapti");
                    String noofgd = data.getString("noofgd");
                    String noofhri = data.getString("noofhri");
                    String nooftechtest = data.getString("nooftechtest");
                    String stdx = data.getString("stdx");
                    String stdxiiordiploma = data.getString("stdxiiordiploma");
                    String ug = data.getString("ug");
                    String pg = data.getString("pg");
                    String lastmodified = data.getString("lastmodified");
                    String uploadedby = data.getString("uploadedby");
                    String passingyear = data.getString("passingyear");
                    String experience = data.getString("experience");

                    String senderdec = AES4all.Decrypt(sender, MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));


                    Intent intent = new Intent(this, ViewPlacement.class);
//                    intent.putExtra("fname", name1.get(0));
//                    intent.putExtra("lname", name1.get(1));
//                    intent.putExtra("uploadedby", name1.get(2));
//                    intent.putExtra("signature", name1.get(3));
//                    intent.putExtra("sender", receiver);
//                    intent.putExtra("receiver", sender);
//                    intent.putExtra("sender_uid", receiver_uid);
//                    intent.putExtra("receiver_uid", sender_uid);
//                    ArrayList<String> companynameslist = new ArrayList<>();
//                    ArrayList<String> packagelists = new ArrayList<>();
//                    ArrayList<String> postlists = new ArrayList<>();
//                    ArrayList<String>  ldrlists = new ArrayList<>();
                    count = 2;

//                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    if (countforplace == 0) {
                        stro =
                                post + " (" + cpackage + " LPA)" +
                                        "\nVacancies: " + vacancies +
                                        "\nDate of Arrival: " + dateofarrival;

                        companynameslist.add(Companyname);
                        packagelists.add(cpackage);
                        postlists.add(post);
                        ldrlists.add(lastdateofregistration);
                        vacantlist.add(vacancies);
                        lastdateofregistration = "Last Date Of reg. " + lastdateofregistration;


                    } else {
                        companynameslist.add(Companyname);
                        packagelists.add(cpackage);
                        postlists.add(post);
                        ldrlists.add(lastdateofregistration);
                        vacantlist.add(vacancies);


                        stro2 += "" + Companyname + " (" + cpackage + " LPA) \n";

                        lastdateofregistration = countforplace + "\tPlacements From PLACEME.";
                        Companyname = "PLACEMENTS";

                        stro = companynameslist.get(0) + " (" + packagelists.get(0) + " LPA)" +
                                "\n" + stro2;

                    }


                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(this)
                                    .setLargeIcon(icon)
                                    .setSmallIcon(R.drawable.bar)
                                    .setContentTitle(Companyname)
                                    .setContentText(post + " (" + cpackage + " LPA)")
                                    .setAutoCancel(true)
                                    .setSound(defaultSoundUri)
                                    .setNumber(++countforplace)
                                    .setStyle(new NotificationCompat.BigTextStyle()
                                            .bigText(stro))
                                    .setSubText(lastdateofregistration)
                                    .setTicker("Placement from PLACEME")
//                                    .setContent(remoteViews)
                                    .setPriority(IMPORTANCE_MAX)
                                    .setGroupSummary(true)
                                    .setGroup("Group message")
                                    .setContentIntent(pendingIntent);
                    Ringtone r = RingtoneManager.getRingtone(this, defaultSoundUri);
                    r.play();
                    Log.d(TAG, "count" + count);


                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(count, builder.build());
//                    countforplace++;

                }
                else if (Notificationtags.equals("chat")) {
                    count=3;
                    Log.d(TAG, "chat received: ");
                    String sender = data.getString("sender");
                    String receiver = data.getString("receiver");
                    String message = data.getString("message");
                    String timestamp = data.getString("timestamp");
                    String sender_uid = data.getString("sender_uid");
                    String receiver_uid = data.getString("receiver_uid");

                    if (!messageSenders.contains(sender)) {
                        messageSenders.add(sender);
                        Log.d(TAG, "added: " + sender + " to list");
                    }
                    Log.d(TAG, "senders list size: " + messageSenders.size());
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
                    intent.putExtra("splash", "MessageActivity");
                    intent.putExtra("fname", name1.get(0));
                    intent.putExtra("lname", name1.get(1));
                    intent.putExtra("uploadedby", name1.get(2));
                    intent.putExtra("signature", name1.get(3));
                    intent.putExtra("sender", receiver);
                    intent.putExtra("receiver", sender);
                    intent.putExtra("sender_uid", receiver_uid);
                    intent.putExtra("receiver_uid", sender_uid);

                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
//                    Bitmap icon2 = BitmapFactory.decodeResource(getResources(),R.drawable.logo);
                    String previouslyEncodedImage = MySharedPreferencesManager.getData(this, sender);
                    if (!previouslyEncodedImage.equalsIgnoreCase("")) {
                        byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
                        icon = BitmapFactory.decodeByteArray(b, 0, b.length);

                    }
                    if (messages.size() == 1) {
                        messages.add(extractedMessage);
                        chatSubText = "1 chat from " + messageSenders.size() + " conversation";
                        chatBigText = messages.get(0);
                    } else {
                        messages.add(extractedMessage);
                        chatBigText = messages.get(0) + "\n" + messages.get(1);
                        chatSubText = "2 chats from " + messageSenders.size() + " conversation";
                        switch (messages.size()) {
                            case 3:
                                chatBigText = messages.get(0) + "\n" + messages.get(1) + "\n" + messages.get(2);
                                chatSubText = "3 chats from " + messageSenders.size() + " conversation";
                                break;
                            case 4:
                                chatBigText = messages.get(0) + "\n" + messages.get(1) + "\n" + messages.get(2) + "\n" + messages.get(3);
                                chatSubText = "4 chats from " + messageSenders.size() + " conversation";
                                break;
                            default:
                                chatBigText = messages.get(0) + "\n" + messages.get(1) + "\n" + messages.get(2) + "\n" + messages.get(3) + "\n...........";
                                chatSubText = messages.size() + " chats from " + messageSenders.size() + " conversation";
                                break;
                        }

                    }
                    NotificationCompat.Builder builder = null;
//                    if(messageSenders.size()==1) {
//                        builder= new NotificationCompat.Builder(this)
//                                .setLargeIcon(icon2)
//                                .setColor(Color.parseColor("#03353E"))
//                                .setContentTitle(name1.get(0) + " " + name1.get(1))
//                                .setContentText(extractedMessage)
//                                .setAutoCancel(true)
//                                .setSound(defaultSoundUri)
//                                .setNumber(++countformessage)
//                                .setStyle(new NotificationCompat.BigTextStyle()
//                                        .bigText(chatBigText))
//                                .setSubText(chatSubText)
//                                .setPriority(IMPORTANCE_HIGH)
//                                .setContentIntent(pendingIntent);
//                    }
//                    else
//                    {
                    builder = new NotificationCompat.Builder(this)
                            .setLargeIcon(icon)
                            .setSmallIcon(R.drawable.bar)
                            .setColor(Color.parseColor("#03353E"))
                            .setContentTitle(name1.get(0) + " " + name1.get(1))
                            .setContentText(extractedMessage)
                            .setAutoCancel(true)
                            .setSound(defaultSoundUri)
                            .setNumber(messages.size())
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText(chatBigText))
                            .setSubText(chatSubText)
                            .setPriority(IMPORTANCE_HIGH)
                            .setContentIntent(pendingIntent);
//                    }
                    if (helper.isChatRoomActive(sender_uid, receiver_uid)) {
                        Ringtone r = RingtoneManager.getRingtone(this, defaultSoundUri);
                        r.play();
                        Log.d(TAG, "chat room already active: ");
                        messages.clear();
                        messageSenders.clear();

                    } else {
                        Log.d(TAG, "chat room not active: ");

                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(0, builder.build());

                        Intent pushNotification = new Intent("pushNotificationChat");
                        LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                    }

                }

            } else {
                Log.e(TAG, "payload not present..");

            }
        } catch (Exception e) {
            Log.e(TAG, "Exception:Occurred " + e.getMessage());
            e.printStackTrace();
        }

    }

}





