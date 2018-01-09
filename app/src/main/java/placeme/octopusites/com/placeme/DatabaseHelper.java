package placeme.octopusites.com.placeme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    final static String DATABASE_NAME="chat.db";
    final static int DATABASE_VERSION = 1;

    String tablecreate1="create table chat_rooms(enc_username text,plain_username text,signature text,fname text,lname text,last_message text,last_message_time text,sender_uid text,uid text,token text,unreadcount text);";
    String tablecreate2="create table chat(sender text,receiver text,senderUid text,receiverUid text,message text,timestamp text,messagetime text);";
    String tablecreate3="create table current_chat_room(chat_room text);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(tablecreate1);
        db.execSQL(tablecreate2);
        db.execSQL(tablecreate3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS chat_rooms");
        db.execSQL("DROP TABLE IF EXISTS chat");
        db.execSQL("DROP TABLE IF EXISTS current_chat_room");
        onCreate(db);
    }

    public ArrayList<String> getName(String plain_username)
    {
        ArrayList<String> name=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "select fname,lname,enc_username,signature from chat_rooms where plain_username='"+plain_username+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        int c=cursor.getCount();
        if(c==0)
        {

            return name;
        }
        else
        {
            if(cursor.moveToFirst()) {
                name.add(cursor.getString(0));
                name.add(cursor.getString(1));
                name.add(cursor.getString(2));
                name.add(cursor.getString(3));
            }

            return name;
        }

    }
    public boolean setCurrentChatRoom(String senderUID,String recieverUID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "select * from current_chat_room";
        Cursor cursor = db.rawQuery(selectQuery, null);
        int c=cursor.getCount();
        if(c==0)
        {
            ContentValues values = new ContentValues();
            values.put("chat_room", senderUID + "_" + recieverUID);
            db.insert("current_chat_room", null, values);

            return true;
        }
        else
        {
            db.delete("current_chat_room",null,null);
            ContentValues values = new ContentValues();
            values.put("chat_room", senderUID + "_" + recieverUID);
            db.insert("current_chat_room", null, values);

            return true;
        }

    }
    public boolean clearCurrentChatRoom()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("current_chat_room",null,null);

        return true;
    }
    public boolean isChatRoomActive(String senderUID,String recieverUID)
    {
        boolean flag=false;

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "select * from current_chat_room";
        Cursor cursor = db.rawQuery(selectQuery, null);
        int c=cursor.getCount();
        if(c==0)
        {
            flag=false;
        }
        else
        {
            if(cursor.moveToFirst()) {
                String chat_room = cursor.getString(0);
                if (chat_room.contains(senderUID) && chat_room.contains(recieverUID))
                    flag = true;
            }
        }

        return flag;
    }

    public boolean isChatInDB(String senderUid, String receiverUid, String timestamp) {
        boolean isChatInDB = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "select * from chat where senderUid='" + senderUid + "' and receiverUid='" + receiverUid + "' and timestamp='" + timestamp + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        int c = cursor.getCount();
        if (c > 0)
            isChatInDB = true;
        return isChatInDB;
    }
    public boolean updateChatRoom(String plain_username,String last_message,String last_message_time)
    {
        boolean isSuccess=false;
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "select plain_username from chat_rooms where plain_username='"+plain_username+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        int c=cursor.getCount();
        if(c>0) {
            ContentValues values = new ContentValues();
            values.put("last_message", last_message);
            values.put("last_message_time", last_message_time);
            db.update("chat_rooms", values,"plain_username='"+plain_username+"'",null);
            isSuccess=true;
        }


        return isSuccess;
    }
    public int createChatRoom(String enc_username,String plain_username, String signature , String fname, String lname, String last_message, String last_message_time,String sender_uid ,String uid,String token,String unreadcount) {

        int isSuccess=0;

        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "select plain_username from chat_rooms where plain_username='"+plain_username+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        int c=cursor.getCount();
        if(c==0) {
            ContentValues values = new ContentValues();
            values.put("enc_username", enc_username);
            values.put("plain_username", plain_username);
            values.put("signature", signature);
            values.put("fname", fname);
            values.put("lname", lname);
            values.put("last_message", last_message);
            values.put("last_message_time", last_message_time);
            values.put("sender_uid", sender_uid);
            values.put("uid", uid);
            values.put("token", token);
            values.put("unreadcount", unreadcount);
            db.insert("chat_rooms", null, values);
            isSuccess=1;
        }
        else
        {
            ContentValues values = new ContentValues();
            values.put("enc_username", enc_username);
            values.put("plain_username", plain_username);
            values.put("signature", signature);
            values.put("fname", fname);
            values.put("lname", lname);
            values.put("last_message", last_message);
            values.put("last_message_time", last_message_time);
            values.put("sender_uid", sender_uid);
            values.put("uid", uid);
            values.put("token", token);
            values.put("unreadcount", unreadcount);
            db.update("chat_rooms", values,"plain_username='"+plain_username+"'",null);
            isSuccess=2;
        }


        return isSuccess;
    }

    public ArrayList<RecyclerItemMessages> loadChatRooms()
    {

        ArrayList<RecyclerItemMessages> chat_rooms = new ArrayList<RecyclerItemMessages>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "select * from chat_rooms";
        Cursor cursor = db.rawQuery(selectQuery, null);

        int c=cursor.getCount();
        if(c>0)
        {
            if(cursor.moveToFirst())
            {
                do{

                    RecyclerItemMessages user=new RecyclerItemMessages(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));
                    chat_rooms.add(user);

                }
                while(cursor.moveToNext());
            }
        }

        return chat_rooms;

    }
    void clearChatRooms()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("chat_rooms",null,null);

    }
    public boolean createChat(Chat chat)
    {
        boolean isSuccess=false;

        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "select * from chat where timestamp='"+chat.timestamp+"' and message='"+chat.message+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        int c=cursor.getCount();
        if(c==0) {
            ContentValues values = new ContentValues();

            values.put("sender", chat.sender);
            values.put("receiver", chat.receiver);
            values.put("senderUid", chat.senderUid);
            values.put("receiverUid", chat.receiverUid);
            values.put("message", chat.message);
            values.put("timestamp", chat.timestamp);

            db.insert("chat", null, values);
            Log.d("TAG", "sender: "+chat.sender+" receiver"+chat.receiver);
            isSuccess=true;
        }

        return isSuccess;
    }

    public ArrayList<Chat> loadChat(String sender,String receiver)
    {

        Log.d("TAG", "loadchat called with sender "+sender+" receiver "+receiver);
        ArrayList<Chat> chats = new ArrayList<Chat>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "select * from chat where (sender='"+sender+"' or  receiver='"+sender+"') and (sender='"+receiver+"' or  receiver='"+receiver+"')";
        Cursor cursor = db.rawQuery(selectQuery, null);

        int c=cursor.getCount();
        if(c>0)
        {
            if(cursor.moveToFirst())
            {
                do{

                    Chat chat=new Chat(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),Long.parseLong(cursor.getString(5)));
                    chats.add(chat);
                    Log.d("TAG", "chat found in db: "+chat.message);

                }
                while(cursor.moveToNext());
            }
        }

        return chats;

    }
    public boolean updateUnreadCout(String plain_username,String unreadcount)
    {
        boolean isSuccess=false;

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "select plain_username from chat_rooms where plain_username='"+plain_username+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        int c=cursor.getCount();
        if(c>0)
        {
            ContentValues values = new ContentValues();
            values.put("unreadcount", unreadcount);
            db.update("chat_rooms", values,"plain_username='"+plain_username+"'",null);
            isSuccess=true;
        }

        return isSuccess;
    }





}
