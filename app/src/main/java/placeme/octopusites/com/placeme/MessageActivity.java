package placeme.octopusites.com.placeme;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.signature.ObjectKey;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    EditText mETxtMessage;
    ImageView send;
    private List<Chat> chatList = new ArrayList<>();
    private List<Chat> tempList = new ArrayList<>();
    private RecyclerView recyclerView;
    String encusername,signature,fname,lname,username;
    private String senderUID,recieverUID, email, firebaseToken;
    private ChatRecyclerAdapter mAdapter;
    DatabaseHelper helper = new DatabaseHelper(this);


    JSONObject json;
    JSONParser jParser = new JSONParser();
    String resultofop="";
    String chat_room;
    String firstMessage,lastMessage,lastPushedmessage;
    long firstTimestamp,lastTimestamp,lastPushedTimestamp;
    int chatCount=0;
    CircleImageView profile;
    TextView name;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Username = "nameKey";
    String digest1,digest2;

    int tempflag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(MessageActivity.this.getResources().getColor(R.color.colorPrimary)));


        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_actionbar_message, null);

        profile=(CircleImageView)v.findViewById(R.id.profile);
        name=(TextView)v.findViewById(R.id.name);


        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);


        actionBar.setCustomView(v);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);


        fname=getIntent().getStringExtra("fname");
        lname=getIntent().getStringExtra("lname");
        encusername=getIntent().getStringExtra("uploadedby");
        signature=getIntent().getStringExtra("signature");
        username=getIntent().getStringExtra("sender");
        email=getIntent().getStringExtra("receiver");
        senderUID=getIntent().getStringExtra("sender_uid");
        recieverUID=getIntent().getStringExtra("receiver_uid");

        helper.setCurrentChatRoom(senderUID,recieverUID);

        new ChangeMessageReadStatus(MySharedPreferencesManager.getUsername(MessageActivity.this),encusername).execute();

        name.setText(fname+" "+lname);

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority(Z.VPS_IP)
                .path("AESTest/GetImageThumbnail")
                .appendQueryParameter("u", encusername)
                .build();


        GlideApp.with(this)
                .load(uri)
                .signature(new ObjectKey(signature))
                .into(profile);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ChatRecyclerAdapter(chatList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        mETxtMessage = (EditText)findViewById(R.id.message_line);
        send = (ImageView)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendMessage();
                mETxtMessage.setText("");


            }
        });

        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v,
                                       int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(chatList.size()>0)
                                recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                        }
                    }, 100);
                }
            }
        });


        loadChatHistoryFromDB();


    }
    void loadChatHistoryFromDB()
    {
        tempList.clear();
        tempList=helper.loadChat(username,email);
        chatList.clear();
        chatList.addAll(tempList);
        mAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);

        new LoadChatFromServer().execute();


    }
    public void getMessageFromFirebaseUser(String senderUid, String receiverUid) {
        final String room_type_1 = senderUid + "_" + receiverUid;
        final String room_type_2 = receiverUid + "_" + senderUid;

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("chat_rooms").getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               // Toast.makeText(MessageActivity.this, "can now send", Toast.LENGTH_SHORT).show();

                if (dataSnapshot.hasChild(room_type_1)) {

                    chat_room=room_type_1;

                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("chat_rooms")
                            .child(room_type_1).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Chat chat = dataSnapshot.getValue(Chat.class);
                            addChat(chat);

                        }
                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(MessageActivity.this, "Unable to get message: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (dataSnapshot.hasChild(room_type_2)) {

                    chat_room=room_type_2;

                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("chat_rooms")
                            .child(room_type_2).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Chat chat = dataSnapshot.getValue(Chat.class);
                            addChat(chat);
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(MessageActivity.this, "Unable to get message: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    //Toast.makeText(MessageActivity.this, "no such room", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MessageActivity.this, "Unable to get message: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void addChat(Chat chat)
    {

        new SaveChatToServer(chat).execute();

        String message=chat.message;
        StringBuffer sb=new StringBuffer("");
        int index1=message.indexOf("pLACEmeMsGTime");
        for(int j=0;j<index1;j++)
        {
            sb.append(message.charAt(j));
        }
        String extractedMessage=sb.toString();

        Chat chat2=new Chat(chat.sender,chat.receiver,chat.senderUid,chat.receiverUid,extractedMessage,chat.timestamp);
        if(helper.createChat(chat2))
            new CheckLastPushed(chat).execute();
        tempList.clear();
        tempList=helper.loadChat(username,email);
        if(tempList.size()>0) {
            firstMessage = tempList.get(0).message;
            firstTimestamp = tempList.get(0).timestamp;
            lastMessage = tempList.get(tempList.size()-1).message;
            lastTimestamp = tempList.get(tempList.size()-1).timestamp;
        }
        chatList.clear();
        chatList.addAll(tempList);
        mAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);

    }
    void sendMessage()
    {
        String message = mETxtMessage.getText().toString();
        Log.d("TAG", "sending: "+message);
        if(message.length()>0) {
            String sender = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            String senderUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String receiver = email;
            String receiverUid = recieverUID;
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            Calendar calobj = Calendar.getInstance();
            String currenttime = df.format(calobj.getTime());
            message += "pLACEmeMsGTime" + currenttime;
           // Toast.makeText(this, sender + " \n" + senderUID, Toast.LENGTH_LONG).show();
            Chat chat = new Chat(sender, receiver, senderUid, receiverUid, message, System.currentTimeMillis());
            Log.d("TAG", "chat object created: "+sender+" "+receiver+" "+senderUid+" "+receiverUid+" "+message+" "+System.currentTimeMillis());
            sendMessageToFirebaseUser(chat);
        }
    }
    public void sendMessageToFirebaseUser(final Chat chat) {
        final String room_type_1 = chat.senderUid + "_" + chat.receiverUid;
        final String room_type_2 = chat.receiverUid + "_" + chat.senderUid;

        Log.d("TAG", "send to firebase called: ");

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("chat_rooms").getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(room_type_1)) {
                     Log.d("TAG", "sendMessageToFirebaseUser: " + room_type_1 + " exists");
                    databaseReference.child("chat_rooms").child(room_type_1).child(String.valueOf(chat.timestamp)).setValue(chat);
                } else if (dataSnapshot.hasChild(room_type_2)) {
                     Log.d("TAG", "sendMessageToFirebaseUser: " + room_type_2 + " exists");
                    databaseReference.child("chat_rooms").child(room_type_2).child(String.valueOf(chat.timestamp)).setValue(chat);
                } else {
                     Log.d("TAG", "sendMessageToFirebaseUser: success");
                    databaseReference.child("chat_rooms").child(room_type_1).child(String.valueOf(chat.timestamp)).setValue(chat);
                    getMessageFromFirebaseUser(chat.senderUid, chat.receiverUid);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MessageActivity.this, "Unable to send message: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    class SaveChatToServer extends AsyncTask<String, Void, Integer> {

        String sender;
        String receiver;
        String senderUid;
        String receiverUid;
        String message;
        long timestamp;

        SaveChatToServer(Chat chat)
        {
            sender=chat.sender;
            receiver=chat.receiver;
            senderUid=chat.senderUid;
            receiverUid=chat.receiverUid;
            message=chat.message;
            timestamp=chat.timestamp;
        }

        @Override
        protected Integer doInBackground(String... urls) {
            try
            {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("s", Z.Encrypt(sender,MessageActivity.this)));    //0
                params.add(new BasicNameValuePair("r", Z.Encrypt(receiver,MessageActivity.this)));  //1
                params.add(new BasicNameValuePair("su", senderUid));//2
                params.add(new BasicNameValuePair("ru", receiverUid));//3
                params.add(new BasicNameValuePair("m", Z.Encrypt(message,MessageActivity.this)));       //4
                params.add(new BasicNameValuePair("t", String.valueOf(timestamp)));  //5

                if(sender.equals(username)) {
                    tempflag=0;
                    json = jParser.makeHttpRequest(Z.url_savechat, "GET", params);
                    String s = null;
                }
                else
                {
                    tempflag=1;
                    if(helper.isChatRoomActive(senderUID,recieverUID)) {
                        tempflag=2;
                        json = jParser.makeHttpRequest(Z.url_markread, "GET", params);
                    }
                }

            }catch (Exception e){e.printStackTrace();}

            return 0;
        }
        @Override
        protected void onPostExecute(Integer result) {

            if(firstMessage!=null)
            {
                if(firstTimestamp==timestamp)
                {
                   // Toast.makeText(MessageActivity.this, " First Message ", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("chat_rooms").child(chat_room).child(String.valueOf(timestamp)).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError error, DatabaseReference ref) {
                            if (error == null) {
                               // Toast.makeText(MessageActivity.this, "Deleted " + message, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MessageActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }


        }

    }

    class CheckLastPushed extends AsyncTask<String, Void, String> {

        String sender;
        String receiver;
        String senderUid;
        String receiverUid;
        String message;
        long timestamp;

        CheckLastPushed(Chat chat)
        {
            sender=chat.sender;
            receiver=chat.receiver;
            senderUid=chat.senderUid;
            receiverUid=chat.receiverUid;
            message=chat.message;
            timestamp=chat.timestamp;
        }

        @Override
        protected String doInBackground(String... urls) {
            String result=null;
            try
            {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("s", Z.Encrypt(sender,MessageActivity.this)));    //0
                params.add(new BasicNameValuePair("r", Z.Encrypt(receiver,MessageActivity.this)));  //1
                json = jParser.makeHttpRequest(Z.url_GetLastPushedMessage, "GET", params);

                if(json.getString("info").equals("success"))
                {
                    lastPushedmessage=json.getString("lastmessage");
                    lastPushedTimestamp=Long.parseLong(json.getString("timestamp"));
                }



            }catch (Exception e){e.printStackTrace();}

            return result;
        }
        @Override
        protected void onPostExecute(String result) {

            if(lastPushedmessage!=null)
            {
               // Toast.makeText(MessageActivity.this,message+"\n"+lastPushedmessage , Toast.LENGTH_SHORT).show();
                if(lastPushedmessage.equals(lastMessage)&&lastPushedTimestamp==lastTimestamp)
                {
                   // Toast.makeText(MessageActivity.this, "last msg and last pushed same", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(username.equals(sender)) {
                        new SendPushNotification(sender, receiver, message, String.valueOf(timestamp)).execute();
                        //Toast.makeText(MessageActivity.this, "last msg and last pushed not same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                if(username.equals(sender)) {
                    new SendPushNotification(sender, receiver, message, String.valueOf(timestamp)).execute();
                    // Toast.makeText(MessageActivity.this, "last pushed null", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    class ChangeMessageReadStatus extends AsyncTask<String, Void, String> {

        String sender;
        String receiver;


        ChangeMessageReadStatus(String sender,String receiver)
        {
            this.sender=sender;
            this.receiver=receiver;

        }

        @Override
        protected String doInBackground(String... urls) {
            String result=null;
            try
            {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("s", Z.Encrypt(sender,MessageActivity.this)));    //0
                params.add(new BasicNameValuePair("r", Z.Encrypt(receiver,MessageActivity.this)));  //1

                json = jParser.makeHttpRequest(Z.url_ChangeMessageReadStatus, "GET", params);

                result = json.getString("info");


            }catch (Exception e){e.printStackTrace();}

            return result;
        }
        @Override
        protected void onPostExecute(String result) {

           // Toast.makeText(MessageActivity.this,sender+"\n"+receiver, Toast.LENGTH_SHORT).show();

//            if(result.equals("success")) {
//                   Toast.makeText(MessageActivity.this, "push notification sent", Toast.LENGTH_SHORT).show();
//            }
//            else
//                Toast.makeText(MessageActivity.this, result, Toast.LENGTH_SHORT).show();
        }

    }


    class SendPushNotification extends AsyncTask<String, Void, String> {

        String sender;
        String receiver;
        String message;
        String timestamp;

        SendPushNotification(String sender,String receiver,String message,String timestamp)
        {
            this.sender=sender;
            this.receiver=receiver;
            this.message=message;
            this.timestamp=timestamp;
        }

        @Override
        protected String doInBackground(String... urls) {
            String result=null;
            try
            {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("s", Z.Encrypt(sender,MessageActivity.this)));    //0
                params.add(new BasicNameValuePair("r", Z.Encrypt(receiver,MessageActivity.this)));  //1
                params.add(new BasicNameValuePair("m", Z.Encrypt(message,MessageActivity.this)));       //2
                params.add(new BasicNameValuePair("t", timestamp));                                 //3
                json = jParser.makeHttpRequest(Z.url_SendPushNotification, "GET", params);

                result = json.getString("info");


            }catch (Exception e){e.printStackTrace();}

            return result;
        }
        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success")) {
             //   Toast.makeText(MessageActivity.this, "push notification sent", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(MessageActivity.this, result, Toast.LENGTH_SHORT).show();
        }

    }

    class LoadChatFromServer extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... urls) {
            try
            {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("s", senderUID));
                params.add(new BasicNameValuePair("r", recieverUID));
                json = jParser.makeHttpRequest(Z.url_loadchat, "GET", params);
                Log.d("TAG", "load chat json: "+json);
                String s = null;
                resultofop = json.getString("chatroom");
                if(resultofop.equals("chatroom1")||resultofop.equals("chatroom2"))
                {

                    chatCount=Integer.parseInt(json.getString("count"));
                    Log.d("TAG", "chat count from server: "+chatCount);
                    for (int i=0;i<chatCount;i++)
                    {
                        Chat chat=new Chat(Z.Decrypt(json.getString("sender"+i),MessageActivity.this),Z.Decrypt(json.getString("receiver"+i),MessageActivity.this),json.getString("senderUid"+i),json.getString("receiverUid"+i),Z.Decrypt(json.getString("message"+i),MessageActivity.this),Long.parseLong(json.getString("timestamp"+i)));

                        String message=chat.message;
                        StringBuffer sb=new StringBuffer("");
                        int index1=message.indexOf("pLACEmeMsGTime");
                        for(int j=0;j<index1;j++)
                        {
                            sb.append(message.charAt(j));
                        }
                        String extractedMessage=sb.toString();
                        Chat chat2=new Chat(chat.sender,chat.receiver,chat.senderUid,chat.receiverUid,extractedMessage,chat.timestamp);

                        if(helper.createChat(chat2));
                            Log.d("TAG", "chat stored in sqlite"+chat2.message);
                    }

                }


            }catch (Exception e){Log.e("TAG", "exp"+e.getMessage());}

            return 0;
        }
        @Override
        protected void onPostExecute(Integer result) {

            tempList.clear();
            tempList=helper.loadChat(username,email);
            Log.d("TAG", "templist size: "+tempList.size());
            if(tempList.size()>0) {
                firstMessage = tempList.get(0).message;
                firstTimestamp = tempList.get(0).timestamp;
            }
            chatList.clear();
            chatList.addAll(tempList);
            mAdapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);

//            if(chatCount>0)
//                Toast.makeText(MessageActivity.this, "Chat loaded from webserver", Toast.LENGTH_SHORT).show();

            getMessageFromFirebaseUser(recieverUID,senderUID);

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(helper.clearCurrentChatRoom())
        {

        }
        if(isTaskRoot())
            startActivity(new Intent(MessageActivity.this,MainActivity.class));

    }
}

