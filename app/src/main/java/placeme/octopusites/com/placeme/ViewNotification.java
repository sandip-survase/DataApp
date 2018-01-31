package placeme.octopusites.com.placeme;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ViewNotification extends AppCompatActivity {

    int id;
    String heading,notification,filename;
    TextView notificationheadingview,notificationnotificationview,attachmentstxt;
    RelativeLayout attachmentrl1,attachmentrl2,attachmentrl3,attachmentrl4,attachmentrl5;
    ImageView attachment1img,attachment2img,attachment3img,attachment4img,attachment5img;
    TextView filename1,filename2,filename3,filename4,filename5;
    Button download;
    String username;
    TextView uploadedbytxt,lastmodifiedtxt;


    byte[] demoKeyBytes;
    byte[] demoIVBytes;
    String sPadding = "ISO10126Padding";

    JSONParser jParser = new JSONParser();
    String digest1,digest2;
    JSONObject json;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);

//        MobileAds.initialize(this, Z.APP_ID);
//        mAdView = findViewById(R.id.ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Notification");
        ab.setDisplayHomeAsUpEnabled(true);

        username=MySharedPreferencesManager.getUsername(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        attachmentstxt=(TextView)findViewById(R.id.attachmentstxt);

        attachmentrl1=(RelativeLayout)findViewById(R.id.attachmentrl1);
        attachmentrl2=(RelativeLayout)findViewById(R.id.attachmentrl2);
        attachmentrl3=(RelativeLayout)findViewById(R.id.attachmentrl3);
        attachmentrl4=(RelativeLayout)findViewById(R.id.attachmentrl4);
        attachmentrl5=(RelativeLayout)findViewById(R.id.attachmentrl5);

        attachment1img=(ImageView)findViewById(R.id.attachment1img);
        attachment2img=(ImageView)findViewById(R.id.attachment2img);
        attachment3img=(ImageView)findViewById(R.id.attachment3img);
        attachment4img=(ImageView)findViewById(R.id.attachment4img);
        attachment5img=(ImageView)findViewById(R.id.attachment5img);

        filename1=(TextView)findViewById(R.id.filename1);
        filename2=(TextView)findViewById(R.id.filename2);
        filename3=(TextView)findViewById(R.id.filename3);
        filename4=(TextView)findViewById(R.id.filename4);
        filename5=(TextView)findViewById(R.id.filename5);

        uploadedbytxt=(TextView)findViewById(R.id.uploadedbytxt);
        lastmodifiedtxt=(TextView)findViewById(R.id.lastmodifiedtxt);
        uploadedbytxt.setTypeface(Z.getLight(this));
        lastmodifiedtxt.setTypeface(Z.getLight(this));
        attachmentstxt.setTypeface(Z.getLight(this));

        String uploadedby="";

        String uploadedby_enc=getIntent().getStringExtra("uploadedby");
        Log.d("gettingdata", "uploadedby_enc"+uploadedby_enc);


        try
        {
            MyFirebaseMessagingService.countfornotiff = 0;
            MyFirebaseMessagingService.notiffbigtext = "";
            MyFirebaseMessagingService.notiffbigtext2 = "";
            MyFirebaseMessagingService.companynameslist.clear();
            MyFirebaseMessagingService.packagelists.clear();
            MyFirebaseMessagingService.postlists.clear();
            MyFirebaseMessagingService.ldrlists.clear();
            MyFirebaseMessagingService.vacantlist.clear();
            Log.d("serviceFields", "notificationtitlelist:" + MyFirebaseMessagingService.notificationcontentlist.size());
            MyFirebaseMessagingService.notificationtitlelist.clear();
            MyFirebaseMessagingService.notificationcontentlist.clear();
            Log.d("serviceFields", "notificationtitlelist2:" + MyFirebaseMessagingService.notificationcontentlist.size());




//            uploadedby=uploadedby_enc;
            Log.d("gettingdata", "uploadedby"+uploadedby);

        String File1= Z.Decrypt(getIntent().getStringExtra("file1"),ViewNotification.this);
            String File2= Z.Decrypt(getIntent().getStringExtra("file2"),ViewNotification.this);
            String File3= Z.Decrypt(getIntent().getStringExtra("file3"),ViewNotification.this);
            String File4= Z.Decrypt(getIntent().getStringExtra("file4"),ViewNotification.this);
            String File5= Z.Decrypt(getIntent().getStringExtra("file5"),ViewNotification.this);


        }catch (Exception e){}



        uploadedbytxt.setText("Uploaded by "+uploadedby_enc);
        lastmodifiedtxt.setText("Last modified on "+getIntent().getStringExtra("lastmodified"));
        attachmentrl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downloadAttachment(getIntent().getStringExtra("id"),getIntent().getStringExtra("file1"));

            }
        });
        attachmentrl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadAttachment(getIntent().getStringExtra("id"),getIntent().getStringExtra("file2"));
            }
        });
        attachmentrl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadAttachment(getIntent().getStringExtra("id"),getIntent().getStringExtra("file3"));
            }
        });
        attachmentrl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadAttachment(getIntent().getStringExtra("id"),getIntent().getStringExtra("file4"));
            }
        });
        attachmentrl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadAttachment(getIntent().getStringExtra("id"),getIntent().getStringExtra("file5"));
            }
        });



        Log.d("gettingdata", "id"+getIntent().getStringExtra("id"));

        Log.d("gettingdata", "titile"+getIntent().getStringExtra("title"));
        Log.d("gettingdata", "titile"+getIntent().getStringExtra("notification"));
        Log.d("gettingdata", "file1"+getIntent().getStringExtra("file1"));
        Log.d("gettingdata", "file2"+getIntent().getStringExtra("file2"));
        Log.d("gettingdata", "file3"+getIntent().getStringExtra("file3"));
        Log.d("gettingdata", "file4"+getIntent().getStringExtra("file4"));
        Log.d("gettingdata", "file5"+getIntent().getStringExtra("file5"));




        notificationheadingview=(TextView)findViewById(R.id.notificationheadingview);
        notificationheadingview.setTypeface(Z.getBold(this));
        notificationnotificationview=(TextView)findViewById(R.id.notificationnotificationview);
        notificationnotificationview.setTypeface(Z.getLight(this));

        notificationheadingview.setText(getIntent().getStringExtra("title"));
        notificationnotificationview.setText(getIntent().getStringExtra("notification"));




        if(getIntent().getStringExtra("file1")!=null)
        {
            String filename=getIntent().getStringExtra("file1");
            if(!filename.equals("null")) {
                attachmentstxt.setVisibility(View.VISIBLE);
                attachmentrl1.setVisibility(View.VISIBLE);
                filename1.setText(filename);

                int index1=filename.lastIndexOf(".");
                String extension="";
                for(int i=index1+1;i<filename.length();i++)
                    extension+=filename.charAt(i);

                Drawable myDrawable=getDrawable(extension);
                attachment1img.setImageDrawable(myDrawable);

            }
        }
        if(getIntent().getStringExtra("file2")!=null)
        {
            String filename=getIntent().getStringExtra("file2");

            if(!filename.equals("null")) {
                attachmentstxt.setVisibility(View.VISIBLE);
                attachmentrl2.setVisibility(View.VISIBLE);
                filename2.setText(filename);

                int index1=filename.lastIndexOf(".");
                String extension="";
                for(int i=index1+1;i<filename.length();i++)
                    extension+=filename.charAt(i);

                Drawable myDrawable=getDrawable(extension);
                attachment2img.setImageDrawable(myDrawable);
            }
        }
        if(getIntent().getStringExtra("file3")!=null)
        {
            String filename=getIntent().getStringExtra("file3");
            if(!filename.equals("null")) {
                attachmentstxt.setVisibility(View.VISIBLE);
                attachmentrl3.setVisibility(View.VISIBLE);
                filename3.setText(filename);

                int index1=filename.lastIndexOf(".");
                String extension="";
                for(int i=index1+1;i<filename.length();i++)
                    extension+=filename.charAt(i);

                Drawable myDrawable=getDrawable(extension);
                attachment3img.setImageDrawable(myDrawable);
            }
        }

        if(getIntent().getStringExtra("file4")!=null)
        {
            String filename=getIntent().getStringExtra("file4");
            if(!filename.equals("null")) {
                attachmentstxt.setVisibility(View.VISIBLE);
                attachmentrl4.setVisibility(View.VISIBLE);
                filename4.setText(filename);

                int index1=filename.lastIndexOf(".");
                String extension="";
                for(int i=index1+1;i<filename.length();i++)
                    extension+=filename.charAt(i);

                Drawable myDrawable=getDrawable(extension);
                attachment4img.setImageDrawable(myDrawable);
            }
        }

        if(getIntent().getStringExtra("file5")!=null)
        {
            String filename=getIntent().getStringExtra("file5");
            if(!filename.equals("null")) {
                attachmentstxt.setVisibility(View.VISIBLE);
                attachmentrl5.setVisibility(View.VISIBLE);
                filename5.setText(filename);

                int index1=filename.lastIndexOf(".");
                String extension="";
                for(int i=index1+1;i<filename.length();i++)
                    extension+=filename.charAt(i);

                Drawable myDrawable=getDrawable(extension);
                attachment5img.setImageDrawable(myDrawable);
            }
        }

        changeReadStatusNotification(getIntent().getStringExtra("id"));

    }

    void downloadAttachment(String id,String filename)
    {
        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority(Z.VPS_IP)
                .path("CreateNotificationTemp/DownloadAttachmentFiles")
                .appendQueryParameter("u", username)
                .appendQueryParameter("id", id)
                .appendQueryParameter("f", filename)
                .build();

//        DownloadManager localDownloadManager = (DownloadManager)ViewNotification.this.getSystemService(DOWNLOAD_SERVICE);
//        DownloadManager.Request localRequest = new DownloadManager.Request(uri);
//        localRequest.setNotificationVisibility(1);
//        localDownloadManager.enqueue(localRequest);

//        ********
        File myDirectory = new File(Environment.getExternalStorageDirectory(), "Place Me");
        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        }

        String storagePath = Environment.getExternalStorageDirectory().getPath() + "/Place Me/";

        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir("/Place Me", filename);

        Long referese = dm.enqueue(request);


//        *******


    }


    Drawable getDrawable(String extension)
    {
        Drawable myDrawable1=null;

        if(extension.equalsIgnoreCase("jpg")||extension.equalsIgnoreCase("jpeg")||extension.equalsIgnoreCase("png")||extension.equalsIgnoreCase("gif")||extension.equalsIgnoreCase("bmp")||extension.equalsIgnoreCase("tiff"))
            myDrawable1 = getResources().getDrawable(R.drawable.image);
        else if(extension.equalsIgnoreCase("pdf"))
            myDrawable1 = getResources().getDrawable(R.drawable.pdf);
        else if(extension.equalsIgnoreCase("doc")||extension.equalsIgnoreCase("docx")||extension.equalsIgnoreCase("docm")||extension.equalsIgnoreCase("dot")||extension.equalsIgnoreCase("dotx")||extension.equalsIgnoreCase("dotm")||extension.equalsIgnoreCase("odt")||extension.equalsIgnoreCase("rtf"))
            myDrawable1 = getResources().getDrawable(R.drawable.word);
        else if(extension.equalsIgnoreCase("xlsx")||extension.equalsIgnoreCase("xls")||extension.equalsIgnoreCase("xlsm")||extension.equalsIgnoreCase("xlsb")||extension.equalsIgnoreCase("xltx")||extension.equalsIgnoreCase("xltm")||extension.equalsIgnoreCase("xlt"))
            myDrawable1 = getResources().getDrawable(R.drawable.excel);
        else if(extension.equalsIgnoreCase("txt"))
            myDrawable1 = getResources().getDrawable(R.drawable.text);
        else if(extension.equalsIgnoreCase("pptx")||extension.equalsIgnoreCase("ppt")||extension.equalsIgnoreCase("pptm"))
            myDrawable1 = getResources().getDrawable(R.drawable.powerpoint);
        else if(extension.equalsIgnoreCase("zip")||extension.equalsIgnoreCase("rar")||extension.equalsIgnoreCase("tar")||extension.equalsIgnoreCase("7z")||extension.equalsIgnoreCase("jar"))
            myDrawable1 = getResources().getDrawable(R.drawable.archive);
        else if(extension.equalsIgnoreCase("java")||extension.equalsIgnoreCase("html")||extension.equalsIgnoreCase("py")||extension.equalsIgnoreCase("c")||extension.equalsIgnoreCase("c++"))
            myDrawable1 = getResources().getDrawable(R.drawable.code);
        else
            myDrawable1 = getResources().getDrawable(R.drawable.unknownfile);

        return myDrawable1;
    }
    class ChangeReadStatusNotification extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("id", param[0]));       //0
            json = jParser.makeHttpRequest(Z.url_changenotificationsreadstatus, "GET", params);
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

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
    void changeReadStatusNotification(String id)
    {
        new ChangeReadStatusNotification().execute(id);

    }

    @Override
    public void onBackPressed() {

        //change as per entry

        if(getIntent().getStringExtra("push")!=null){
            Log.d("TAG", "role"+MySharedPreferencesManager.getRole(this));
            if(MySharedPreferencesManager.getRole(getBaseContext()).equals("student")){
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                finish();

            } else if(MySharedPreferencesManager.getRole(getBaseContext()).equals("alumni")){
                startActivity(new Intent(getBaseContext(),AlumniActivity.class));
                finish();

            }
            else if(MySharedPreferencesManager.getRole(getBaseContext()).equals("admin")){
                startActivity(new Intent(getBaseContext(),AdminActivity.class));
                finish();

            }
            else if(MySharedPreferencesManager.getRole(getBaseContext()).equals("hr")){
                startActivity(new Intent(getBaseContext(),HRActivity.class));
                finish();

            }
        }else{
        super.onBackPressed();

        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

}

