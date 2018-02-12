package placeme.octopusites.com.placeme;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.RecyclerTouchListener;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;
import static placeme.octopusites.com.placeme.MainActivity.containsIgnoreCase;

public class MessagesFragment extends Fragment {
    private List<RecyclerItemMessages> itemList = new ArrayList<>();
    private List<RecyclerItemMessages> tempList = new ArrayList<>();
    private List<RecyclerItemMessages> tempListForSearch;
    private RecyclerView recyclerView;
    private RecyclerItemMessagesAdapter mAdapter;
    int selectedCount=0;
    int selectedPositions[];
    int index=0;
    String usernameenc,role,plainusername;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Username = "nameKey";
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    String messagesreadstatus[];
    int count;
    String sender_uid;
    SwipeRefreshLayout swipeRefreshLayout;
    String reciever_username[],reciever_signature[],reciever_fname[],reciever_lname[],reciever_lastmessage[],reciever_time[],reciever_token[],reciever_uid[];
    String unread_count[];
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    int searchFlag=0;
    ArrayList<Integer> indexes_to_skip = new ArrayList<>();
    public MessagesFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("pushreceived")) {

                    String message = intent.getStringExtra("message");
                    String time = intent.getStringExtra("time");
                    String sender = intent.getStringExtra("sender");

                    DatabaseHelper helper = new DatabaseHelper(getContext());

                        for(int i=0;i<reciever_username.length;i++)
                        {
                            if(reciever_username[i].equals(sender)) {
                                Toast.makeText(getActivity(), ""+unread_count[i], Toast.LENGTH_SHORT).show();
                                unread_count[i] = Integer.parseInt(unread_count[i]) + 1+"";
                                helper.updateUnreadCout(sender,unread_count[i]);

                            }
                        }
                    if(helper.updateChatRoom(sender,message,time)) {

                        refreshFromDB();
                        Toast.makeText(getActivity(), message + "\n" + time + "\n" + sender, Toast.LENGTH_LONG).show();
                    }


                }
            }
        };


        usernameenc=MySharedPreferencesManager.getUsername(getActivity());
        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        role = MySharedPreferencesManager.getRole(getActivity());

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addMessages();
            }
        });


        try {
            plainusername = Z.Decrypt(usernameenc, getActivity());
        } catch (Exception e) {
        }

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);

        mAdapter = new RecyclerItemMessagesAdapter(itemList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        addMessages();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                RecyclerItemMessages item = itemList.get(position);

                if (role.equals("student")) {
                    if (unread_count != null) {
                        ((MainActivity) getActivity()).updateUnreadMessageCount(Integer.parseInt(unread_count[position]));
                        Log.d("TAG", "unread messages count: " + Integer.parseInt(unread_count[position]));
                    }
                }
                if (role.equals("admin")) {
                    if (unread_count != null) {
                        ((AdminActivity) getActivity()).updateUnreadMessageCount(Integer.parseInt(unread_count[position]));
                        Log.d("TAG", "unread messages count: " + Integer.parseInt(unread_count[position]));
                    }
                }

                DatabaseHelper helper = new DatabaseHelper(getContext());
                if (unread_count != null)
                    unread_count[position] = "0";

                if (unread_count != null)
                    if(helper.updateUnreadCout(item.getUsername(),unread_count[position])) {

                        refreshFromDB();
                    }
                startActivity(new Intent(getActivity(), MessageActivity.class).putExtra("uploadedby", item.getUploadedby()).putExtra("signature", item.getSignature()).putExtra("fname", item.getFname()).putExtra("lname", item.getLname()).putExtra("sender", plainusername).putExtra("receiver", item.getUsername()).putExtra("sender_uid", item.getSender_uid()).putExtra("receiver_uid", item.getUid()));

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }
    public void filterChats(String text)
    {
        tempListForSearch= new ArrayList();
        for(RecyclerItemMessages d: itemList){

            if(containsIgnoreCase(d.getFname(),text)||containsIgnoreCase(d.getLname(),text)){
                tempListForSearch.add(d);
            }
        }
        mAdapter.updateList(tempListForSearch,text);
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
                });
            }
        }

        return animation;
    }
    void refreshFromDB()
    {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        tempList.clear();
        tempList=helper.loadChatRooms();
        itemList.clear();
        itemList.addAll(tempList);
        mAdapter.notifyDataSetChanged();
    }
    public void addMessages()
    {


        DatabaseHelper helper = new DatabaseHelper(getContext());
        tempList.clear();
        tempList=helper.loadChatRooms();
        itemList.clear();
        itemList.addAll(tempList);
        mAdapter.notifyDataSetChanged();


        Log.d("TAG", "calling getmessages: ");
        new GetMessages().execute();

    }


    class GetMessages extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", usernameenc));
            Log.d("TAG", "sending: " + usernameenc);
            json = jParser.makeHttpRequest(Z.url_get_chatrooms, "GET", params);
            Log.d("TAG", "get chatroom json: " + json);
            Bitmap map = null;
            try {

                count = Integer.parseInt(json.getString("count"));
                sender_uid = json.getString("uid");

                reciever_username=new String[count];
                reciever_signature=new String[count];
                reciever_fname=new String[count];
                reciever_lname=new String[count];
                reciever_lastmessage=new String[count];
                reciever_time=new String[count];
                reciever_token=new String[count];
                reciever_uid=new String[count];
                unread_count=new String[count];

                for(int i=0;i<count;i++)
                {
                    unread_count[i]="0";
                    reciever_username[i]=json.getString("username"+i);
                    reciever_fname[i]=json.getString("fname"+i);
                    reciever_lname[i]=json.getString("lname"+i);
                    reciever_lastmessage[i]=json.getString("lastmessage"+i);
                    reciever_time[i]=json.getString("lasttime"+i);
                    try {
                        reciever_signature[i] = json.getString("signature" + i);
                    } catch (Exception e) {
                        reciever_signature[i] = "PlaceMe";
                    }
                    try {
                        reciever_token[i] = json.getString("token" + i);
                        reciever_uid[i] = json.getString("uid" + i);
                    } catch (Exception e) {
                        indexes_to_skip.add(i);
                    }
                    Log.d("TAG", "reciever_username[i]: "+reciever_username[i]);

                }

            } catch (Exception ex) {

                Log.e("TAG", "error: "+ex.getMessage());
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if (count > 0) {

                for (int i = 0; i < count; i++) {

                    if (!indexes_to_skip.contains(i)) {
                        String tempusername = null;
                        try {
                            byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                            byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                            String sPadding = "ISO10126Padding";

                            byte[] usernameBytes = reciever_username[i].getBytes("UTF-8");

                            byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                            tempusername = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));


                        } catch (Exception e) {

                        }

                        if (reciever_uid != null & reciever_username != null) {
                            new GetMessagesReadStatus(usernameenc, tempusername, sender_uid, reciever_uid[i], i).executeOnExecutor(THREAD_POOL_EXECUTOR);
                            new GetProfileImageAndSaveToPref(reciever_username[i], tempusername).executeOnExecutor(THREAD_POOL_EXECUTOR);
                        }
                    }
                }

            }

        }
    }
    public class GetProfileImageAndSaveToPref extends AsyncTask<String, Void, Bitmap> {

        String uname,enc;

        GetProfileImageAndSaveToPref(String uname,String enc) {
            this.uname=uname;
            this.enc=enc;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            Bitmap map = downloadImage(enc);

            return map;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                result.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                MySharedPreferencesManager.save(getActivity(),uname,encodedImage);
                Log.d("TAG", "image saved in preferences: "+uname);

        }
    }
    private Bitmap downloadImage(String uname) {

        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority(Z.VPS_IP)
                .path("AESTest/GetImageThumbnail")
                .appendQueryParameter("u", uname)
                .build();

        String url=uri.toString();

        Bitmap bitmap = null;
        InputStream stream = null;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;

        try {
            stream = getHttpConnection(url);
            bitmap = BitmapFactory.
                    decodeStream(stream, null, bmOptions);

            stream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;
    }

    private InputStream getHttpConnection(String urlString)
            throws IOException {
        InputStream stream = null;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        try {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stream;
    }
    class GetMessagesReadStatus extends AsyncTask<String, String, String> {

        String sender, reciever, senderuid, recieveruid;
        int index;

        GetMessagesReadStatus(String sender, String reciever, String senderuid, String recieveruid, int index) {
            this.sender = sender;
            this.reciever = reciever;
            this.senderuid = senderuid;
            this.recieveruid = recieveruid;
            this.index = index;
        }

        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("s", sender));       //0
            params.add(new BasicNameValuePair("r", reciever));     //1
            params.add(new BasicNameValuePair("su", senderuid));    //2
            params.add(new BasicNameValuePair("ru", recieveruid));  //3

            try {

                json = jParser.makeHttpRequest(Z.url_getmessagesreadstatus, "GET", params);
                unread_count[index] = json.getString("unreadcount");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (index == count - 1) {
                DatabaseHelper helper = new DatabaseHelper(getContext());
                helper.clearChatRooms();

                for (int i = 0; i < count; i++) {

                    //encrypt username (temp work)
                    String tempusername = null;
                    try {
                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] usernameBytes = reciever_username[i].getBytes("UTF-8");

                        byte[] usernameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, usernameBytes);
                        tempusername = new String(SimpleBase64Encoder.encode(usernameEncryptedBytes));
                    } catch (Exception e) {
                    }

                    helper.createChatRoom(tempusername, reciever_username[i], reciever_signature[i], reciever_fname[i], reciever_lname[i], reciever_lastmessage[i], reciever_time[i], sender_uid, reciever_uid[i], reciever_token[i], unread_count[i]);

                }
                tempList.clear();
                tempList = helper.loadChatRooms();
                itemList.clear();
                itemList.addAll(tempList);

                mAdapter.notifyDataSetChanged();

            }
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mRegistrationBroadcastReceiver,new IntentFilter("pushNotification"));

    }
    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
    @Override
    public void onAttach(final Activity activity) {

        super.onAttach(activity);

        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(final Menu menu) {

        super.onPrepareOptionsMenu(menu);

        if(role.equals("student"))
            menu.clear();
    }
}
