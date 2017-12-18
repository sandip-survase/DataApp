package placeme.octopusites.com.placeme;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ViewResume extends AppCompatActivity {

    InputStream input = null;
    PDFView pdfView;
    ImageView download,shortlist;
    String username="";
    CheckBox checkboxshortlist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resume);
        setFinishOnTouchOutside(false);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        download= (ImageView) findViewById(R.id.download);
        checkboxshortlist = (CheckBox)findViewById(R.id.checkboxs);


        username = getIntent().getStringExtra("username");
        Log.d("accesed", "onCreate: "+username);


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
                    Toast.makeText(ViewResume.this, "Shortlisted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ViewResume.this, "removed", Toast.LENGTH_SHORT).show();

                }


            }

        });










    }

    class ViewPDF extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            try {

                input = new URL("http://192.168.100.30:8080/CreateNotificationTemp/PDFView?u="+username).openStream();

            } catch (Exception ex) {
                Log.d("TAG", "exp: " + ex.getMessage());
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            pdfView.fromStream(input).load();

        }
    }




    void DownloadPDF() {

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("192.168.100.30")
                .path("CreateNotificationTemp/PDFDownload2")
                .appendQueryParameter("u",username)
                .build();




        DownloadManager localDownloadManager = (DownloadManager) this.getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request localRequest = new DownloadManager.Request(uri);
        localRequest.setNotificationVisibility(1);
        localDownloadManager.enqueue(localRequest);

        Toast.makeText(this, "pdf downloaded", Toast.LENGTH_SHORT).show();
    }

}
