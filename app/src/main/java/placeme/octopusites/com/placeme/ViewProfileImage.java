package placeme.octopusites.com.placeme;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.signature.ObjectKey;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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


        username = MySharedPreferencesManager.getUsername(this);

        profile_image = (ImageView) findViewById(R.id.profile_image);
//        new GetProfileImage().execute();
        downloadImage();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    private void downloadImage() {

        new Getsingnature().execute();
    }

    class Getsingnature extends AsyncTask<String, String, String> {
        String signature = "";
        protected String doInBackground(String... param) {
            JSONParser jParser = new JSONParser();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));
            JSONObject json = jParser.makeHttpRequest(Z.load_last_updated, "GET", params);
            Log.d("TAG", "doInBackground: Getsingnature json " + json);
            try {
                signature = json.getString("lastupdated");
            } catch (Exception ex) {
            }
            return signature;
        }

        @Override
        protected void onPostExecute(String result) {


            Log.d("TAG", "downloadImage signature : " + signature);
            Log.d("TAG", "downloadImage: GetImage username " + username);
            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority(Z.VPS_IP)
                    .path("AESTest/GetImage")
                    .appendQueryParameter("u", username)
                    .build();

            GlideApp.with(ViewProfileImage.this)
                    .load(uri)
                    .signature(new ObjectKey(signature))
                    .into(profile_image);
        }
    }

}
