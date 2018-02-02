package placeme.octopusites.com.placeme;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import org.apache.http.NameValuePair;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ViewResume extends AppCompatActivity {

    InputStream input = null;
    PDFView pdfView;
    ImageView download, shortlist;
    String username = "";
    CheckBox checkboxshortlist;
    String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resume);
        setFinishOnTouchOutside(false);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        download = (ImageView) findViewById(R.id.download);
        checkboxshortlist = (CheckBox) findViewById(R.id.checkboxs);


        try {
        username = getIntent().getStringExtra("username");
        username = Z.Encrypt(username,ViewResume.this);
        Log.d("accesed", "onCreate: " + username);

        } catch (Exception e) {
            Log.d("TAG", "onCreate: " + e.getMessage());
        }

//        final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "SkySciQPresentationEnglish.pdf");
//        Log.d("TAG", "onCreate: "+file);

        new ViewPDF().execute();


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadPDF();
            }
        });

        checkboxshortlist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //addin arraylist
                    Toast.makeText(ViewResume.this, "Candidate shortlisted !", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ViewResume.this, "Candidate Removed !", Toast.LENGTH_SHORT).show();

                }


            }

        });


    }

    void DownloadPDF() {

        try {
        Uri uri = null;
            uri = new Uri.Builder()
                    .scheme("http")
                    .authority(Z.VPS_IP)
                    .path("CreateNotificationTemp/PDFDownload2")
                    .appendQueryParameter("u", username )
                    .build();


//        ********
        File myDirectory = new File(Environment.getExternalStorageDirectory(), "Place Me");
        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        }

        String storagePath = Environment.getExternalStorageDirectory().getPath() + "/Place Me/";

        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir("/Place Me", name + "_resume.pdf");

        Long referese = dm.enqueue(request);


//        *******


//        DownloadManager localDownloadManager = (DownloadManager) this.getSystemService(DOWNLOAD_SERVICE);
//        DownloadManager.Request localRequest = new DownloadManager.Request(uri);
//        localRequest.setNotificationVisibility(1);
//        localDownloadManager.enqueue(localRequest);

        Toast.makeText(this, "Resume Downloaded !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ViewPDF extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            try {

                String username1 = Z.Decrypt(username,ViewResume.this);
                 username1 = Z.Encrypt(username,ViewResume.this);
                input = new URL(Z.IP+"CreateNotificationTemp/PDFView?u="+Z.Encrypt("kunal@student2.com",ViewResume.this)).openStream();

            } catch (Exception ex) {
                Log.d("TAG", "exp: " + ex.getMessage());
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("TAG", "onPostExecute: ---------view pdf------");
            pdfView.fromStream(input).load();

        }
    }

}
