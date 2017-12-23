package placeme.octopusites.com.placeme;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TAG", "Firebase token: ");

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(final String token) {

        MySharedPreferencesManager.save(getApplicationContext(),"firebaseToken", token);
        new SharedPrefUtil(getApplicationContext()).saveString("firebaseToken", token);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseDatabase.getInstance()
                    .getReference()
                    .child("users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("firebaseToken")
                    .setValue(token);
        }
    }
}