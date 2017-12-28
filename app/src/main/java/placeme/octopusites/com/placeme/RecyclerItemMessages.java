package placeme.octopusites.com.placeme;

/**
 * Created by Lincoln on 15/01/16.
 */
public class RecyclerItemMessages {
    private String uploadedby, signature, fname, lname, lastmessage, time, username, sender_uid ,uid, token,unreadcount;

    public RecyclerItemMessages(String uploadedby,String username, String signature, String fname, String lname, String lastmessage, String time,String sender_uid,String uid,String token,String unreadcount) {
        this.uploadedby = uploadedby;
        this.signature = signature;
        this.fname = fname;
        this.lname = lname;
        this.lastmessage = lastmessage;
        this.time = time;
        this.username = username;
        this.uid = uid;
        this.sender_uid = sender_uid;
        this.token = token;
        this.unreadcount = unreadcount;
    }

    public String getUploadedby() {
        return uploadedby;
    }
    public void setUploadedby(String uploadedby) {
        this.uploadedby = uploadedby;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String id) {
        this.fname = fname;
    }
    public String getLname() {  return lname;    }
    public void setLname(String id) {   this.lname = lname;    }
    public String getLastmessage() { return lastmessage;    }
    public void setLastmessage(String id) { this.lastmessage = lastmessage;   }
    public String getTime() {  return time;   }
    public void setTime(String id) { this.time = time;   }
    public String getUsername() {  return username;   }
    public void setUsername(String id) { this.username = username;   }
    public String getUid() {  return uid;   }
    public void setUid(String id) { this.uid = uid;   }
    public String getSender_uid(){return sender_uid;}
    public void setSender_uid(String id){this.sender_uid = sender_uid;}
    public String getToken() {  return token;   }
    public void setToken(String id) { this.token = token;   }
    public String getUnreadcount() {  return unreadcount;   }
    public void setUnreadcount(String unreadcount) { this.unreadcount = unreadcount;   }
}