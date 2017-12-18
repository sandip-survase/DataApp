package placeme.octopusites.com.placeme;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.signature.ObjectKey;

public class ViewProfileImage extends AppCompatActivity {
    ImageView profile_image;

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_image);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Profile Image");
        ab.setDisplayHomeAsUpEnabled(true);


        username=MySharedPreferencesManager.getUsername(this);

        profile_image=(ImageView)findViewById(R.id.profile_image);
//        new GetProfileImage().execute();
        downloadImage();
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
    private void downloadImage() {

        String t = String.valueOf(System.currentTimeMillis());

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority(Z.VPS_IP)
                .path("AESTest/GetImage")
                .appendQueryParameter("u", username)
                .build();

        GlideApp.with(this)
                .load(uri)
                .signature(new ObjectKey(System.currentTimeMillis() + ""))
                .into(profile_image);


    }

}
