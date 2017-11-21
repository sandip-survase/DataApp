package placeme.octopusites.com.placeme;

/**
 * Created by Lincoln on 15/01/16.
 */
public class User {
    private String uid, email, firebaseToken;

    public User(){

    }

    public User(String uid, String email, String firebaseToken){
        this.uid = uid;
        this.email = email;
        this.firebaseToken = firebaseToken;
    }
    public String getEmail() {
        return email;
    }
    public String getUid()
    {
        return uid;
    }
    public String getFirebaseToken()
    {
        return firebaseToken;
    }

    
}
