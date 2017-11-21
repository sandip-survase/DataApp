package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ViewProfileImage extends AppCompatActivity {
    ImageView profile_image;
    private static String load_student_image = "http://192.168.100.10/AESTest/GetImage";
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_image);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Profile Image");
        ab.setDisplayHomeAsUpEnabled(true);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);

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
                .authority("192.168.100.100")
                .path("AESTest/GetImage")
                .appendQueryParameter("u", username)
                .build();

        Glide.with(this)
                .load(uri)
                .crossFade()
                .signature(new StringSignature(System.currentTimeMillis() + ""))
                .into(profile_image);

    }


//    public class GetProfileImage extends AsyncTask<String, Void, Bitmap> {
//        @Override
//        protected Bitmap doInBackground(String... urls) {
//            Bitmap map = null;
//            map = downloadImage(load_student_image);
//            return map;
//        }
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            profile_image.setImageBitmap(result);
//        }
//        private Bitmap downloadImage(String url) {
//            Uri uri = new Uri.Builder()
//                    .scheme("http")
//                    .authority("192.168.100.100")
//                    .path("AESTest/GetImage")
//                    .appendQueryParameter("u", username)
//                    .build();
//
//            url=uri.toString();
//
//            Bitmap bitmap = null;
//            InputStream stream = null;
//            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//            bmOptions.inSampleSize = 1;
//
//            try {
//                stream = getHttpConnection(url);
//                bitmap = BitmapFactory.
//                        decodeStream(stream, null, bmOptions);
//                stream.close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            return bitmap;
//        }
//
//        // Makes HttpURLConnection and returns InputStream
//        private InputStream getHttpConnection(String urlString)
//                throws IOException {
//            InputStream stream = null;
//            URL url = new URL(urlString);
//            URLConnection connection = url.openConnection();
//
//            try {
//                HttpURLConnection httpConnection = (HttpURLConnection) connection;
//                httpConnection.setRequestMethod("GET");
//                httpConnection.connect();
//
//                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    stream = httpConnection.getInputStream();
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            return stream;
//        }
//
//    }


}
