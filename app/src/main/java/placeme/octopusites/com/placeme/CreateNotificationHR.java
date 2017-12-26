package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.Encrypt;

public class CreateNotificationHR extends AppCompatActivity {
    RelativeLayout file1;

    RelativeLayout attchrl1, attchrl2, attchrl3, attchrl4, attchrl5;
    TextView t1, t2, t3, t4, t5, t6;
    ProgressBar prg1, prg2, prg3, prg4, prg5;
    String sstitle = "", ssnotification = "", ssfile1 = "", ssfile2 = "", ssfile3 = "", ssfile4 = "", ssfile5 = "", ssuploadedby = "", ssuploadtime = "", sslastmodified = "";
    String stitle = "", snotiffication = "";
    String forwhom = "", tempselection = "", tempforwhom = "";

    TextView createnotitxt, createnotinotitxt, attachmentstxt;
    TextInputLayout titleinput,notificationinput;

    int errorflag = 0;

    EditText title, notification;
    String encTitle = "", encNotiffication = "", encforwhom = "",encUsername="";
    JSONObject json;
    JSONParser jParser = new JSONParser();

    //file work
    int filecounter = 0;
    int filesame = 0;
    String filePath = "";
    long lenght;
    String filename = "";
    String directory = "";
    ArrayList<String>  a1 = new ArrayList<>();
    String srole = "", instname = "", digest1 = "", digest2;


    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    LinkedHashMap<String, String> map2 = new LinkedHashMap<>();
    String encfilenameparam1, encfilenameparam2, encfilenameparam3, encfilenameparam4, encfilenameparam5;
    String filenameparam1 = "", filenameparam2 = "", filenameparam3 = "", filenameparam4 = "", filenameparam5 = "";


    //connection work
    private HttpURLConnection httpConn;
    private OutputStream outputStream;
    String username="";
    private String charset = "UTF-8";
    private String boundary;
    private PrintWriter writer;


    String name = "", vallue = "", name2 = "", value2 = "";
    private static final String LINE_FEED = "\r\n";
    int progress, progress2, progress3, progress4, progress5;
    int data_for_progressbar, data_for_progressbar1, data_for_progressbar2, data_for_progressbar3, data_for_progressbar4;
    List<String> response = new ArrayList<String>();
    Drawable compleatesprogress;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification_hr);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Create Notification");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        Window window = CreateNotificationHR.this.getWindow();


        createnotitxt = (TextView) findViewById(R.id.createnotitxt);
        createnotinotitxt = (TextView) findViewById(R.id.createnotinotitxt);
        attachmentstxt = (TextView) findViewById(R.id.attachmentstxt);

        createnotitxt.setTypeface(Z.getBold(this));
        createnotinotitxt.setTypeface(Z.getLight(this));
        attachmentstxt.setTypeface(Z.getLight(this));


        attchrl1 = (RelativeLayout) findViewById(R.id.file1);
        attchrl2 = (RelativeLayout) findViewById(R.id.file2);
        attchrl3 = (RelativeLayout) findViewById(R.id.file3);
        attchrl4 = (RelativeLayout) findViewById(R.id.file4);
        attchrl5 = (RelativeLayout) findViewById(R.id.file5);
        t1 = (TextView) findViewById(R.id.filename);
        t2 = (TextView) findViewById(R.id.filename2);
        t3 = (TextView) findViewById(R.id.filename3);
        t4 = (TextView) findViewById(R.id.filename4);
        t5 = (TextView) findViewById(R.id.filename5);
        final Context context = this;

        prg1 = (ProgressBar) findViewById(R.id.PROGRESS_BAR);
        prg2 = (ProgressBar) findViewById(R.id.PROGRESS_BAR2);
        prg3 = (ProgressBar) findViewById(R.id.PROGRESS_BAR3);
        prg4 = (ProgressBar) findViewById(R.id.PROGRESS_BAR4);
        prg5 = (ProgressBar) findViewById(R.id.PROGRESS_BAR5);

        titleinput=(TextInputLayout)findViewById(R.id.titleinput);
        notificationinput=(TextInputLayout)findViewById(R.id.notificationinput);
        title = (EditText) findViewById(R.id.title);
        notification = (EditText) findViewById(R.id.notification);
        titleinput.setTypeface(Z.getLight(this));
        notificationinput.setTypeface(Z.getLight(this));
        title.setTypeface(Z.getBold(this));
        notification.setTypeface(Z.getBold(this));


        tempforwhom = getIntent().getStringExtra("emailids");
        tempselection = getIntent().getStringExtra("selection");
        Log.d("Tag", "emailids: " + tempforwhom);
        Log.d("Tag", "selection: " + tempselection);

        if (tempselection != null) {
            if (tempselection.equals("registerd")) {
                createnotinotitxt.setText("Your notification will be sent  to selected Registerd users.");
            } else if (tempselection.equals("shortlisted")) {
                createnotinotitxt.setText("Your notification will be sent  to selected shortlisted users.");
            } else if (tempselection.equals("placed")) {
                createnotinotitxt.setText("Your notification will be sent  to Placed shortlisted users.");
            }
        }

        srole=MySharedPreferencesManager.getRole(this);
        instname=MySharedPreferencesManager.getInstitute(this);
        encUsername=MySharedPreferencesManager.getUsername(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (filecounter < 5) {
                    filesame = 0;


                    new MaterialFilePicker().
                            withActivity(CreateNotificationHR.this)
                            .withRequestCode(1)
                            .withFilter(Pattern.compile(".*\\.*$")) // Filtering files and directories by file name using regexp
                            .withFilterDirectories(false) // Set directories filterable (false by default)
                            .withHiddenFiles(true) // Show hidden files and folders
                            .start();
                } else {
                    Toast.makeText(CreateNotificationHR.this, "FileLimit 5 ", Toast.LENGTH_LONG).show();
                }


            }
        });


        attchrl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog1();

            }

        });
        attchrl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog2();

            }
        });
        attchrl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog3();


            }
        });
        attchrl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog4();


            }
        });
        attchrl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog5();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                Toast.makeText(getBaseContext(), "clciked", Toast.LENGTH_SHORT).show();
                errorflag = 0;

                stitle = title.getText().toString();
                snotiffication = notification.getText().toString();
                encUsername=MySharedPreferencesManager.getUsername(this);

                if (stitle.length() <= 2) {
                    errorflag = 1;
                    title.setError("invalid title");
                } else if (snotiffication.length() <= 2) {
                    errorflag = 1;
                    title.setError("invalid notification");
                }


                if (errorflag == 0) {
                    try {
//

                        encTitle = Encrypt(stitle, MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));
                        encNotiffication = Encrypt(snotiffication, MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));
                        encforwhom = Encrypt(tempforwhom, MySharedPreferencesManager.getDigest1(this), MySharedPreferencesManager.getDigest2(this));


                new SaveData().execute();

                    } catch (Exception e) {
                        Log.d("TAG", "validateandSave: - " + e.getMessage());

                    }


                }


                break;

            case android.R.id.home:

                onBackPressed();

                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_create, menu);
        return super.onCreateOptionsMenu(menu);


    }














    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to discard changes ?")
                .setCancelable(false)
                .setPositiveButton("Discard",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                CreateNotificationHR.super.onBackPressed();
                            }
                        })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
            }
        });

        alertDialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {


            filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            Log.d("filetest", "filePath: "+filePath);
            File f = new File(filePath);
            filePath = f.getAbsolutePath();

            Log.d("filetest", "filePath ABSOLUTE: "+filePath);
            // Do anything with file
            lenght = f.length();
            Log.d("filetest", " lenght: "+lenght);

//            Toast.makeText(MainActivity.this, "File lenght:" + lenght, Toast.LENGTH_LONG).show();

            if (lenght > 16777216) {
                Toast.makeText(CreateNotificationHR.this, "File Exceeds the Size Limit(16MB)", Toast.LENGTH_LONG).show();
                filesame = 1;
            }
            filename = "";
            int index = filePath.lastIndexOf("/");
            directory = "";
            for (int i = 0; i < index; i++)
                directory += filePath.charAt(i);

            for (int i = index + 1; i < filePath.length(); i++)
                filename += filePath.charAt(i);

            Log.d("filetest", " filename: "+filename);

            if(a1.contains(filename)){
                filesame = 1;
                Log.d("filetest", "filename:"+filename+"=====already present" );

            }

//            try {
//                for (int i = 0; i < a1.size(); i++) {
//                    if (a1.get(i).equals(filename)) {
//                        filesame = 1;
//                    }
//                }
//            } catch (Exception e) {
//            }


            if (filesame != 1) {
                a1.add(filename);
                map.put(filename, filePath);
                map2.put(filename, lenght + "");
                filecounter++;
                refresh();

//                batchesTags.dismissDropDown();

                if (filecounter == 1) {
                    new ShowProgress().execute();

                }

                if (filecounter == 2) {
                    new ShowProgress2().execute();

                }
                if (filecounter == 3) {
                    new ShowProgress3().execute();

                }
                if (filecounter == 4) {
                    new ShowProgress4().execute();

                }
                if (filecounter == 5) {
                    new ShowProgress5().execute();

                }

            } else {
                Log.d("filetest", "filename:"+filename+"=====already present" );

                Toast.makeText(CreateNotificationHR.this, "File name is same", Toast.LENGTH_LONG).show();
            }
        }
    }





    class SaveData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("r", "Role:hr"));   //1
            params.add(new BasicNameValuePair("f", encTitle));       //2
            params.add(new BasicNameValuePair("l", encNotiffication));   //3
            params.add(new BasicNameValuePair("c", encforwhom));     //4

            Log.d("Params", "encUsername:0 "+encUsername);
            Log.d("Params", "encTitle:2 "+encTitle);
            Log.d("Params", "encNotiffication: 3"+encNotiffication);
            Log.d("Params", "encforwhom: 4"+encforwhom);



            if (filenameparam1.length() != 0 || filenameparam1 != "") {

                params.add(new BasicNameValuePair("f1", filenameparam1));  //5
                Log.d("Params", "filenameparam1: 5"+filenameparam1);

            } else {
                params.add(new BasicNameValuePair("f1", "BLANK"));
                Log.d("Params", "BLANK: 5");

            }

            if (filenameparam2.length() != 0 || filenameparam2 != "") {
                params.add(new BasicNameValuePair("f2", filenameparam2));     //6
                Log.d("Params", "filenameparam2: 6"+filenameparam2);

            } else {
                params.add(new BasicNameValuePair("f2", "BLANK"));
                Log.d("Params", "BLANK: 6");


            }
            if (filenameparam3.length() != 0 || filenameparam3 != "") {
                params.add(new BasicNameValuePair("f3", filenameparam3));     //7
                Log.d("Params", "filenameparam3: 7"+filenameparam3);

            } else {
                params.add(new BasicNameValuePair("f3", "BLANK"));
                Log.d("Params", "BLANK: 7");


            }
            if (filenameparam4.length() != 0 || filenameparam4 != "") {
                params.add(new BasicNameValuePair("f4", filenameparam4));     //8
                Log.d("Params", "filenameparam4: 8"+filenameparam4);

            } else {
                params.add(new BasicNameValuePair("f4", "BLANK"));
                Log.d("Params", "BLANK: 8");


            }
            if (filenameparam5.length() != 0 || filenameparam5 != "") {
                params.add(new BasicNameValuePair("f5", filenameparam5));     //9
                Log.d("Params", "filenameparam5: 9"+filenameparam5);

            } else {
                params.add(new BasicNameValuePair("f5", "BLANK"));
                Log.d("Params", "BLANK: 9");

            }


            json = jParser.makeHttpRequest(Z.url_CreateNotificationHrToEach, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                Log.d("crash============",""+ e.getMessage());
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(CreateNotificationHR.this, result, Toast.LENGTH_SHORT).show();
//            CreateNotificationHR.super.onBackPressed();
//            if(result.equals("success"))
//            {
//                Toast.makeText(CreateNotification.this,"Successfully Saved..!",Toast.LENGTH_SHORT).show();
//
////                Intent returnIntent = new Intent();
////                returnIntent.putExtra("result", result);
////                if(edittedFlag==1){
////                    setResult(111);
////                }
//                CreateNotification.super.onBackPressed();
//            }
//            else {
//                Toast.makeText(CreateNotification.this,result,Toast.LENGTH_SHORT).show();
//
//            }
        }
    }

    void refresh() {

        try {

            try {
                if (a1.get(0) != null) {
                    Log.d("***", "a1 " + a1.get(0));
                    attchrl1.setVisibility(View.VISIBLE);
                    t1.setText(a1.get(0));
                    filenameparam1 = (a1.get(0));
                    filenameparam2 = "";
                    filenameparam3 = "";
                    filenameparam4 = "";
                    filenameparam5 = "";

                    attchrl1.requestFocus();

//                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                    mTagsEditText.dismissDropDown();


                }
            } catch (Exception e) {
                attchrl1.setVisibility(View.GONE);
                t1.setText("");
            }
            try {
                if (a1.get(1) != null) {
                    Log.d("***", "a2 " + a1.get(1));
                    attchrl2.setVisibility(View.VISIBLE);
                    t2.setText(a1.get(1));
                    filenameparam2 = (a1.get(1));
                    filenameparam3 = "";
                    filenameparam4 = "";
                    filenameparam5 = "";

                    attchrl2.requestFocus();

//                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                    mTagsEditText.ropDown();


                }
            } catch (Exception e) {
//                Toast.makeText(CreateNotification.this,  "2"+e.getMessage(), Toast.LENGTH_LONG).show();
                attchrl2.setVisibility(View.GONE);
                t2.setText("");
            }
            try {
                if (a1.get(2) != null) {
                    Log.d("***", "a3 " + a1.get(2));
                    attchrl3.setVisibility(View.VISIBLE);
                    t3.setText(a1.get(2));
                    filenameparam3 = (a1.get(2));
                    filenameparam4 = "";
                    filenameparam5 = "";
                    attchrl3.requestFocus();

//                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                    mTagsEditText.dismissDropDown();


                }
            } catch (Exception e) {
                t3.setText("");
                attchrl3.setVisibility(View.GONE);
            }
            try {
                if (a1.get(3) != null) {

                    Log.d("***", "a4 " + a1.get(3));
                    attchrl4.setVisibility(View.VISIBLE);
                    t4.setText(a1.get(3));
                    filenameparam4 = (a1.get(3));
                    filenameparam5 = "";

                    attchrl4.requestFocus();
//                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                    mTagsEditText.dismissDropDown();


                }
            } catch (Exception e) {
                attchrl4.setVisibility(View.GONE);
                t4.setText("");
            }
            try {

                if (a1.get(4) != null) {
                    attchrl5.setVisibility(View.VISIBLE);
                    Log.d("***", "a5 " + a1.get(4));
                    t5.setText(a1.get(4));
                    filenameparam5 = (a1.get(4));
                    attchrl5.requestFocus();

//                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                    mTagsEditText.dismissDropDown();


                }
            } catch (Exception e) {
                attchrl5.setVisibility(View.GONE);
                t5.setText("");
            }


        } catch (Exception e) {
            Toast.makeText(CreateNotificationHR.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    class ShowProgress extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

            File atach1 = new File(filePath);
            lenght = atach1.length();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            File atach1 = new File(filePath);
            lenght = atach1.length();
            try {
//   username = "sunny.gh.gm@gmail.com";
//            username =Decrypt(MySharedPreferencesManager.getUsername(getBaseContext()),MySharedPreferencesManager.getDigest1(getBaseContext()),MySharedPreferencesManager.getDigest2(getBaseContext()) );
                username =Decrypt(encUsername,digest1,digest2 );

                Log.d("filetest", "username fro sendinfg server"+username );

//            MultipartUtility multipart = new MultipartUtility(upload_Attach_temp, "UTF-8");
                // creates a unique boundary based on time stamp
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(Z.url_SavefileOnServer);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);    // indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);
                //formfieldpart//     multipart.addFormField("u", username);
                name = "u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if (filename != "") {
                    name2 = "f";
                    value2 = filename;
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                            .append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(
                            LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append(value2).append(LINE_FEED);
                    writer.flush();
                    //multipart part// multipart.addFilePart("uf", sourceFile);

                    String fieldName = "uf", uploadFile = "";
                    //        String fileName = uploadFile.getName();
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append(
                            "Content-Disposition: form-data; name=\"" + fieldName
                                    + "\"; filename=\"" + filename + "\"")
                            .append(LINE_FEED);
                    writer.append(
                            "Content-Type: "
                                    + URLConnection.guessContentTypeFromName(filename))
                            .append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();
                    FileInputStream inputStream = new FileInputStream(atach1);
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
//                  long totalSize= 15710566;
                    long totalSize = lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        //progres
                        Log.d("bytes", "" + bytesRead);
                        progress += bytesRead;
                        data_for_progressbar = (int) ((progress * 100) / totalSize);
                        Log.d("filetest", "data_for_progressbar: "+data_for_progressbar);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
///finishPart
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

// checks server's status code first
                    int status = httpConn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                httpConn.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            response.add(line);
                        }
                        reader.close();
                        httpConn.disconnect();

                    } else {
                        throw new IOException("Server returned non-OK status: " + status);
                    }

                } else
//        multipart.addFormField("f", "");
                    name2 = "f";
                value2 = "";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(value2).append(LINE_FEED);
                writer.flush();
                ///finishPart
                writer.append(LINE_FEED).flush();
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();

//            response = multipart.finish();

// checks server's status code first
                int status = httpConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            httpConn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.add(line);
                    }
                    reader.close();
                    httpConn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " + status);
                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.d("response;", response.get(0));
                if (response.contains(" \"file\": \"created\"")) {
                    Toast.makeText(CreateNotificationHR.this, response.get(0), Toast.LENGTH_LONG).show();
//                        prg1.setIndeterminateDrawable(compleatesprogress);

//                    prg1.setProgressDrawable(compleatesprogress);
//                        pr/g1.


                } else
                    Toast.makeText(CreateNotificationHR.this, response.get(0), Toast.LENGTH_LONG).show();


            } catch (Exception e)  {
                Toast.makeText(CreateNotificationHR.this, e.getMessage(), Toast.LENGTH_LONG).show();

            }

        }

        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
            Log.d("LAst", "progressOnUpdate1:" + data_for_progressbar + "");
//

            prg1.setProgress(data_for_progressbar);


        }

    }
    class ShowProgress2 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

            File atach1 = new File(filePath);
            lenght = atach1.length();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
            File atach1 = new File(filePath);
            lenght = atach1.length();
//            username = "sunny.gh.gm@gmail.com";
            username =Decrypt(encUsername,digest1,digest2 );


//            MultipartUtility multipart = new MultipartUtility(upload_Attach_temp, "UTF-8");
                // creates a unique boundary based on time stamp
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(Z.url_SavefileOnServer);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);    // indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);
                //formfieldpart//     multipart.addFormField("u", username);
                name = "u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if (filename != "") {
                    name2 = "f";
                    value2 = filename;
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                            .append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(
                            LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append(value2).append(LINE_FEED);
                    writer.flush();
                    //multipart part// multipart.addFilePart("uf", sourceFile);


                    String fieldName = "uf", uploadFile = "";
                    //        String fileName = uploadFile.getName();
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append(
                            "Content-Disposition: form-data; name=\"" + fieldName
                                    + "\"; filename=\"" + filename + "\"")
                            .append(LINE_FEED);
                    writer.append(
                            "Content-Type: "
                                    + URLConnection.guessContentTypeFromName(filename))
                            .append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();
                    FileInputStream inputStream = new FileInputStream(atach1);
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
//                  long totalSize= 15710566;
                    long totalSize = lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        //progres
                        Log.d("bytes", "" + bytesRead);
                        progress2 += bytesRead;
                        data_for_progressbar1 = (int) ((progress2 * 100) / totalSize);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
///finishPart
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

// checks server's status code first
                    int status = httpConn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                httpConn.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            response.add(line);
                        }
                        reader.close();
                        httpConn.disconnect();

                    } else {
                        throw new IOException("Server returned non-OK status: " + status);
                    }

                } else
//        multipart.addFormField("f", "");
                    name2 = "f";
                value2 = "";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(value2).append(LINE_FEED);
                writer.flush();
                ///finishPart
                writer.append(LINE_FEED).flush();
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();

//            response = multipart.finish();

// checks server's status code first
                int status = httpConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            httpConn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.add(line);
                    }
                    reader.close();
                    httpConn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " + status);
                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress2) {
            super.onProgressUpdate(progress2);
            Log.d("LAst", "progressOnUpdate2:" + data_for_progressbar1 + "");
//

            prg2.setProgress(data_for_progressbar1);


        }

    }

    class ShowProgress3 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

            File atach1 = new File(filePath);
            lenght = atach1.length();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                File atach1 = new File(filePath);
                lenght = atach1.length();
//            username = "sunny.gh.gm@gmail.com";
                username =Decrypt(encUsername,digest1,digest2 );
//            MultipartUtility multipart = new MultipartUtility(upload_Attach_temp, "UTF-8");
                // creates a unique boundary based on time stamp
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(Z.url_SavefileOnServer);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);    // indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);
                //formfieldpart//     multipart.addFormField("u", username);
                name = "u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if (filename != "") {
                    name2 = "f";
                    value2 = filename;
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                            .append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(
                            LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append(value2).append(LINE_FEED);
                    writer.flush();
                    //multipart part// multipart.addFilePart("uf", sourceFile);

                    String fieldName = "uf", uploadFile = "";
                    //        String fileName = uploadFile.getName();
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append(
                            "Content-Disposition: form-data; name=\"" + fieldName
                                    + "\"; filename=\"" + filename + "\"")
                            .append(LINE_FEED);
                    writer.append(
                            "Content-Type: "
                                    + URLConnection.guessContentTypeFromName(filename))
                            .append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();
                    FileInputStream inputStream = new FileInputStream(atach1);
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
//                  long totalSize= 15710566;
                    long totalSize = lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        //progres
                        Log.d("bytes", "" + bytesRead);
                        progress3 += bytesRead;
                        data_for_progressbar2 = (int) ((progress3 * 100) / totalSize);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
///finishPart
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

// checks server's status code first
                    int status = httpConn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                httpConn.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            response.add(line);
                        }
                        reader.close();
                        httpConn.disconnect();

                    } else {
                        throw new IOException("Server returned non-OK status: " + status);
                    }

                } else
//        multipart.addFormField("f", "");
                    name2 = "f";
                value2 = "";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(value2).append(LINE_FEED);
                writer.flush();
                ///finishPart
                writer.append(LINE_FEED).flush();
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();

//            response = multipart.finish();

// checks server's status code first
                int status = httpConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            httpConn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.add(line);
                    }
                    reader.close();
                    httpConn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " + status);
                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress3) {
            super.onProgressUpdate(progress3);
            Log.d("LAst", "progressOnUpdate3:" + data_for_progressbar2 + "");
//

            prg3.setProgress(data_for_progressbar2);


        }

    }

    class ShowProgress4 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

            File atach1 = new File(filePath);
            lenght = atach1.length();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                File atach1 = new File(filePath);
                lenght = atach1.length();
//            username = "sunny.gh.gm@gmail.com";
                username =Decrypt(encUsername,digest1,digest2 );
//            MultipartUtility multipart = new MultipartUtility(upload_Attach_temp, "UTF-8");
                // creates a unique boundary based on time stamp
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(Z.url_SavefileOnServer);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);    // indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);
                //formfieldpart//     multipart.addFormField("u", username);
                name = "u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if (filename != "") {
                    name2 = "f";
                    value2 = filename;
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                            .append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(
                            LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append(value2).append(LINE_FEED);
                    writer.flush();
                    //multipart part// multipart.addFilePart("uf", sourceFile);

                    String fieldName = "uf", uploadFile = "";
                    //        String fileName = uploadFile.getName();
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append(
                            "Content-Disposition: form-data; name=\"" + fieldName
                                    + "\"; filename=\"" + filename + "\"")
                            .append(LINE_FEED);
                    writer.append(
                            "Content-Type: "
                                    + URLConnection.guessContentTypeFromName(filename))
                            .append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();
                    FileInputStream inputStream = new FileInputStream(atach1);
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
//                  long totalSize= 15710566;
                    long totalSize = lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        //progres
                        Log.d("bytes", "" + bytesRead);
                        progress4 += bytesRead;
                        data_for_progressbar3 = (int) ((progress4 * 100) / totalSize);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
///finishPart
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

// checks server's status code first
                    int status = httpConn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                httpConn.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            response.add(line);
                        }
                        reader.close();
                        httpConn.disconnect();

                    } else {
                        throw new IOException("Server returned non-OK status: " + status);
                    }

                } else
//        multipart.addFormField("f", "");
                    name2 = "f";
                value2 = "";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(value2).append(LINE_FEED);
                writer.flush();
                ///finishPart
                writer.append(LINE_FEED).flush();
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();

//            response = multipart.finish();

// checks server's status code first
                int status = httpConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            httpConn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.add(line);
                    }
                    reader.close();
                    httpConn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " + status);
                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress4) {
            super.onProgressUpdate(progress4);
            Log.d("LAst", "progressOnUpdate4:" + data_for_progressbar3 + "");
            prg4.setProgress(data_for_progressbar3);

        }

    }

    class ShowProgress5 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

            File atach1 = new File(filePath);
            lenght = atach1.length();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                File atach1 = new File(filePath);
                lenght = atach1.length();
//            username = "sunny.gh.gm@gmail.com";
                username =Decrypt(encUsername,digest1,digest2 );
//            MultipartUtility multipart = new MultipartUtility(upload_Attach_temp, "UTF-8");
                // creates a unique boundary based on time stamp
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(Z.url_SavefileOnServer);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);    // indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);
                //formfieldpart//     multipart.addFormField("u", username);
                name = "u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if (filename != "") {
                    name2 = "f";
                    value2 = filename;
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                            .append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(
                            LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append(value2).append(LINE_FEED);
                    writer.flush();
                    //multipart part// multipart.addFilePart("uf", sourceFile);

                    String fieldName = "uf", uploadFile = "";
                    //        String fileName = uploadFile.getName();
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append(
                            "Content-Disposition: form-data; name=\"" + fieldName
                                    + "\"; filename=\"" + filename + "\"")
                            .append(LINE_FEED);
                    writer.append(
                            "Content-Type: "
                                    + URLConnection.guessContentTypeFromName(filename))
                            .append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();
                    FileInputStream inputStream = new FileInputStream(atach1);
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
//                  long totalSize= 15710566;
                    long totalSize = lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        //progres
                        Log.d("bytes", "" + bytesRead);
                        progress5 += bytesRead;
                        data_for_progressbar4 = (int) ((progress5 * 100) / totalSize);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
///finishPart
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

// checks server's status code first
                    int status = httpConn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                httpConn.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            response.add(line);
                        }
                        reader.close();
                        httpConn.disconnect();
                    } else {
                        throw new IOException("Server returned non-OK status: " + status);
                    }

                } else
//        multipart.addFormField("f", "");
                    name2 = "f";
                value2 = "";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name2 + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(value2).append(LINE_FEED);
                writer.flush();
                ///finishPart
                writer.append(LINE_FEED).flush();
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();

//            response = multipart.finish();

// checks server's status code first
                int status = httpConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            httpConn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.add(line);
                    }
                    reader.close();
                    httpConn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " + status);
                }


            } catch (Exception ex) {

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress5) {
            super.onProgressUpdate(progress5);
            Log.d("LAst", "progressOnUpdate4:" + data_for_progressbar4 + "");
            prg5.setProgress(data_for_progressbar4);


        }

    }



    void cancelDialog1() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
//               .setMessage("Filename:"+filename+"          Size:"+lenght/1024+"MB")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {

                                    String f1 = t1.getText().toString();
                                    a1.remove(f1);
                                    filenameparam1 = "";
                                    filenameparam1 = "";
                                    ssfile1 = "";
                                    filecounter--;
                                    refresh();

                                } catch (Exception e) {
                                    Toast.makeText(CreateNotificationHR.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//        alertDialogBuilder .setView(R.layout.custom_dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
//            String n = a1.get(0);
            String n = t1.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);

                String finalsize;
                String s = map2.get(a1.get(0));
                long size = Long.parseLong(s);
                int unit = 1000;
                if (size < unit) {
                    finalsize = size + " Bytes";
                    sizeondialogshow1.setText(finalsize);
                } else {
                    int exp = (int) (Math.log(size) / Math.log(unit));
                    String pre = ("kMGTPE").charAt(exp - 1) + ("");
                    finalsize = String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
                    sizeondialogshow1.setText(finalsize);
                }


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
            }
        });

        alertDialog.show();

    }
    void cancelDialog2() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
//               .setMessage("Filename:"+filename+"          Size:"+lenght/1024+"MB")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    String f2 = t2.getText().toString();
                                    a1.remove(f2);
                                    filenameparam2 = "";
                                    ssfile2 = "";

                                    filecounter--;
                                    refresh();

                                } catch (Exception e) {
                                    Toast.makeText(CreateNotificationHR.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//        alertDialogBuilder .setView(R.layout.custom_dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
//            String n = a1.get(1);
            String n = t2.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);

//          String s = map2.get(t1.getText().toString());
                String s = map2.get(a1.get(1));
                long size = Long.parseLong(s);
                String finalsize;
                int unit = 1000;
                if (size < unit) {
                    finalsize = size + " Bytes";
                    sizeondialogshow1.setText(finalsize);
                } else {
                    int exp = (int) (Math.log(size) / Math.log(unit));
                    String pre = ("kMGTPE").charAt(exp - 1) + ("");
                    finalsize = String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
                    sizeondialogshow1.setText(finalsize);
                }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
            }
        });

        alertDialog.show();

    }

    void cancelDialog3() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
//               .setMessage("Filename:"+filename+"          Size:"+lenght/1024+"MB")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    String f3 = t3.getText().toString();

                                    a1.remove(f3);
                                    filenameparam3 = "";
                                    ssfile3 = "";
                                    filecounter--;
                                    refresh();
                                } catch (Exception e) {
                                    Toast.makeText(CreateNotificationHR.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//        alertDialogBuilder .setView(R.layout.custom_dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
//            String n = a1.get(1);
            String n = t3.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);



//          String s = map2.get(t1.getText().toString());
                String s = map2.get(a1.get(2));
                long size = Long.parseLong(s);
                String finalsize;
                int unit = 1000;
                if (size < unit) {
                    finalsize = size + " Bytes";
                    sizeondialogshow1.setText(finalsize);
                } else {
                    int exp = (int) (Math.log(size) / Math.log(unit));
                    String pre = ("kMGTPE").charAt(exp - 1) + ("");
                    finalsize = String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
                    sizeondialogshow1.setText(finalsize);
                }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
            }
        });
        alertDialog.show();
    }

    void cancelDialog4() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
//               .setMessage("Filename:"+filename+"          Size:"+lenght/1024+"MB")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    String f4 = t4.getText().toString();
                                    a1.remove(f4);
                                    filecounter--;
                                    ssfile4 = "";

                                    filenameparam4 = "";
                                    refresh();

                                } catch (Exception e) {
                                    Toast.makeText(CreateNotificationHR.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//        alertDialogBuilder .setView(R.layout.custom_dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
//            String n = a1.get(1);
            String n = t4.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);



//          String s = map2.get(t1.getText().toString());
                String s = map2.get(a1.get(3));
                long size = Long.parseLong(s);
                String finalsize;
                int unit = 1000;
                if (size < unit) {
                    finalsize = size + " Bytes";
                    sizeondialogshow1.setText(finalsize);
                } else {
                    int exp = (int) (Math.log(size) / Math.log(unit));
                    String pre = ("kMGTPE").charAt(exp - 1) + ("");
                    finalsize = String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
                    sizeondialogshow1.setText(finalsize);
                }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
            }
        });
        alertDialog.show();
    }

    void cancelDialog5() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
//               .setMessage("Filename:"+filename+"          Size:"+lenght/1024+"MB")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String f5 = t5.getText().toString();
                                try {
                                    a1.remove(f5);
                                    filenameparam5 = "";
                                    ssfile5 = "";

                                    filecounter--;
                                    refresh();
                                } catch (Exception e) {
                                    Toast.makeText(CreateNotificationHR.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//        alertDialogBuilder .setView(R.layout.custom_dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(dialogView);
        TextView filenameondialogshow1 = (TextView) dialogView.findViewById(R.id.dialogfilename);
        try {
//            String n = a1.get(1);
            String n = t5.getText().toString();
            filenameondialogshow1.setText(n);
            TextView sizeondialogshow1 = (TextView) dialogView.findViewById(R.id.primaryemail);

//          String s = map2.get(t1.getText().toString());
                String s = map2.get(t5.getText().toString());
                long size = Long.parseLong(s);
                String finalsize;
                int unit = 1000;
                if (size < unit) {
                    finalsize = size + " Bytes";
                    sizeondialogshow1.setText(finalsize);
                } else {
                    int exp = (int) (Math.log(size) / Math.log(unit));
                    String pre = ("kMGTPE").charAt(exp - 1) + ("");
                    finalsize = String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
                    sizeondialogshow1.setText(finalsize);
                }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
            }
        });
        alertDialog.show();
    }


}
