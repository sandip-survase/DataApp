package placeme.octopusites.com.placeme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ViewTemplateActivity extends AppCompatActivity {

    ImageView main;
    View closeselectionview;
    int pageCount=0;
    int id=0;
    int resumePages[];
    int count=0,setFlag=0;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    String resultofop="",username;

    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_template);

        id=getIntent().getIntExtra("key",0);

//        Window window = ViewTemplateActivity.this.getWindow();
//        int sdklevel=Integer.valueOf(android.os.Build.VERSION.SDK);
//        if(sdklevel>=21) {
//
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(ViewTemplateActivity.this.getResources().getColor(R.color.background));
//        }

        main=(ImageView)findViewById(R.id.main);

        main.setOnTouchListener(new OnSwipeTouchListener(ViewTemplateActivity.this) {

            public void onSwipeLeft() {

                int i=0;
                for(i=0;i<count;i++) {
                    if (setFlag == resumePages[i])
                        break;
                }
                if(i<count-1)
                {
                    i=i+1;
                    setFlag=resumePages[i];
                    progressbar.setVisibility(View.VISIBLE);
                    new GetResumePage().execute();
                }
            }
            public void onSwipeRight() {
                int i=0;
                for(i=0;i<count;i++) {
                    if (setFlag == resumePages[i])
                        break;
                }
                if(i>0)
                {
                    i=i-1;
                    setFlag=resumePages[i];
                    progressbar.setVisibility(View.VISIBLE);
                    new GetResumePage().execute();
                }
            }


        });
        closeselectionview=(View)findViewById(R.id.closeselectionview);
        closeselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        progressbar=(ProgressBar)findViewById(R.id.progressbar);
        Log.d("TAG", "oncreate: resume page -");
        new GetResumePages().execute();


    }
    void setPage()
    {
        setFlag=resumePages[0];
        new GetResumePage().execute();
    }
    private class GetResumePage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            Log.d("TAG", "doInBackground: getresume psge");
            map = downloadImage(Z.load_resume_page+"?i="+id+"&p="+setFlag);
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {

            main.setImageBitmap(result);
            progressbar.setVisibility(View.GONE);

        }
        private Bitmap downloadImage(String url) {
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

        // Makes HttpURLConnection and returns InputStream
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


    }
    class GetResumePages extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            Log.d("TAG", "doInBackground: resume page -");
            Log.d("TAG", "doInBackground: id - " + id);
            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("i",id+""));

            json = jParser.makeHttpRequest(Z.load_resume_pages, "GET", params);
            Log.d("TAG", "doInBackground: json - " + json);
            try {
                String s = json.getString("count");
                count=Integer.parseInt(s);
                Log.d("TAG", "doInBackground: resume page count -" + count);
                resumePages=new int[count];
                for(int i=0;i<count;i++)
                    resumePages[i]=Integer.parseInt(json.getString("page"+i));

            } catch (Exception e) {
                Log.d("TAG", "doInBackground: exception - " + e.getMessage());
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            setPage();
        }
    }
}
