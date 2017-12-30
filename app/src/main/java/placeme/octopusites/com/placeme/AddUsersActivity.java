package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.List;
import java.util.regex.Pattern;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class AddUsersActivity extends AppCompatActivity {

    FloatingActionButton fab;

    RadioGroup radioGroupUsers;
    RadioButton radioButtonsinle, radioButtonmulti;
    TextInputEditText email;
    TextInputLayout adduserinput;
    RelativeLayout multiusersrl;
    String param="single";

    JSONObject json;
    JSONParser jParser = new JSONParser();

    //filework
    String username="",userEmail,plainUsername="";
    private String digest1,digest2;

    String encadminUsername;

    int filecounter = 0;
    int filesame = 0;
    String filePath = "";
    long lenght;
    String filename = "",encuserEmail;;
    String directory = "";
    String encUsername="";
    String plainFilename="";


    private String charset = "UTF-8" ;
    private  String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private OutputStream outputStream;
    private PrintWriter writer;
    String name="",vallue="",name2="" ,value2="";
    List<String> response = new ArrayList<String>();
    int progress;

    int data_for_progressbar;
    ProgressBar prg1;
    RelativeLayout attchrl1;
    TextView t1;


    private String encfilename="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);

        email = (TextInputEditText) findViewById(R.id.email);
        adduserinput=(TextInputLayout)findViewById(R.id.adduserinput);
        multiusersrl = (RelativeLayout) findViewById(R.id.multiusersrl);
        radioGroupUsers = (RadioGroup) findViewById(R.id.radioGroupUsers);
        radioButtonsinle = (RadioButton) findViewById(R.id.radioButtonsinle);
        radioButtonmulti = (RadioButton) findViewById(R.id.radioButtonmulti);
        fab= (FloatingActionButton) findViewById(R.id.fab);


        adduserinput.setTypeface(Z.getLight(this));
        email.setTypeface(Z.getBold(this));

        encadminUsername=MySharedPreferencesManager.getUsername(AddUsersActivity.this);

        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        email.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adduserinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        radioGroupUsers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButtonsinle:
                        multiusersrl.setVisibility(View.GONE);
                        email.setVisibility(View.VISIBLE);
                        adduserinput.setVisibility(View.VISIBLE);
                        param="single";
                        break;
                    case R.id.radioButtonmulti:
                        radioButtonsinle.setTextColor(getResources().getColor(R.color.dark_color));
                        multiusersrl.setVisibility(View.VISIBLE);
                        email.setVisibility(View.GONE);
                        adduserinput.setVisibility(View.GONE);
                        param="multi";
                        break;
                }
            }
        });
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Add User(s)");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        TextView createpasstxt = (TextView) findViewById(R.id.createpasstxt);
        TextView passsenstxt = (TextView) findViewById(R.id.passsenstxt);
        TextView passsens1txt = (TextView) findViewById(R.id.passsens1txt);
        TextView note = (TextView) findViewById(R.id.note);
        TextView note1 = (TextView) findViewById(R.id.note1);
        TextView note2 = (TextView) findViewById(R.id.note2);
        attchrl1=(RelativeLayout)findViewById(R.id.file1);
        t1=(TextView)findViewById(R.id.filename) ;

        createpasstxt.setTypeface(Z.getBold(this));
        passsenstxt.setTypeface(Z.getLight(this));
        passsens1txt.setTypeface(Z.getLight(this));
        radioButtonsinle.setTypeface(Z.getBold(this));
        radioButtonmulti.setTypeface(Z.getBold(this));
        note.setTypeface(Z.getLight(this));
        note1.setTypeface(Z.getBold(this));
        note2.setTypeface(Z.getBold(this));


        prg1 = (ProgressBar) findViewById(R.id.PROGRESS_BAR);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialFilePicker().
                        withActivity(AddUsersActivity.this)
                        .withRequestCode(1)
                        .withFilter(Pattern.compile(".*\\.*$")) // Filtering files and directories by file name using regexp
                        .withFilterDirectories(false) // Set directories filterable (false by default)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();

            }
        });
        attchrl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog1();

            }

        });

    }
    void cancelDialog1()
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Do you want to remove this attachment ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {

                                    t1.setText("");
                                    attchrl1.setVisibility(View.GONE);
                                    new deleteFile().execute();
                                    filename="";


                                } catch (Exception e) {

                                }
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(AddUsersActivity.this, ""+filename, Toast.LENGTH_SHORT).show();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:
                validate();
                break;

            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }


    class deleteFile extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            String encUsername=MySharedPreferencesManager.getUsername(AddUsersActivity.this);
            Log.d("TAG", "murder");
            filename=plainFilename;

            try {

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";

                byte[] filenameBytes = filename.getBytes("UTF-8");

                byte[] filenameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, filenameBytes);
                encfilename = new String(SimpleBase64Encoder.encode(filenameEncryptedBytes));

            }catch (Exception e){
                e.printStackTrace();
                Log.d("TAG", "murder exp:" + e.getMessage());
            }

            String result = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("f", encfilename));

            json = jParser.makeHttpRequest(Z.url_delete_file, "GET", params);

            try {
                result = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

    }


    private void validate() {

        if (param.equals("single")) {
            userEmail = email.getText().toString();

            if (userEmail != null && !userEmail.equals("")) {

                if(userEmail.contains("@")) {

                    try {
                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                        String sPadding = "ISO10126Padding";

                        byte[] userEmailBytes = userEmail.getBytes("UTF-8");

                        byte[] userEmailEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, userEmailBytes);
                        encuserEmail = new String(SimpleBase64Encoder.encode(userEmailEncryptedBytes));

                        new CreateUser().execute();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("TAG", "baby:" + e.getMessage());
                    }
                } else
                    adduserinput.setError("Kindly enter valid email address");
            }
            else
                adduserinput.setError("Kindly enter valid email address");
        }
        if(param.equals("multi")){
            Toast.makeText(this, "mul", Toast.LENGTH_SHORT).show();
            if(!filename.equals("")){

                String adminUsername = MySharedPreferencesManager.getUsername(AddUsersActivity.this);
                try {
                    plainUsername=Decrypt(adminUsername,digest1,digest2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(this, "multi selected "+filename+"\n"+adminUsername, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "landa");

                try {
                    byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                    byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                    String sPadding = "ISO10126Padding";

                    byte[] filenameBytes = filename.getBytes("UTF-8");

                    byte[] filenameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, filenameBytes);
                    encfilename = new String(SimpleBase64Encoder.encode(filenameEncryptedBytes));

                    new CreateMultipleUser().execute();

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("TAG", "baby:" + e.getMessage());
                }

            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            File f = new File(filePath);
            filePath = f.getAbsolutePath();
            lenght = f.length();
            if (lenght > 16777216) {
                Toast.makeText(AddUsersActivity.this, "File Exceeds the Size Limit(16MB)", Toast.LENGTH_LONG).show();
                filesame = 1;
            }
            filename = "";
            int index = filePath.lastIndexOf("/");
            directory = "";
            for (int i = 0; i < index; i++)
                directory += filePath.charAt(i);

            for (int i = index + 1; i < filePath.length(); i++)
                filename += filePath.charAt(i);

            String fileName[]=filename.split("\\.");
            if(fileName.length==2) {
                if (fileName[1].equals("xls") || fileName[1].equals("xlsx")) {
                    filesame = 0;
                } else {
                    filesame = 1;
                    Toast.makeText(this, "File format must be .xls or .xlsx", Toast.LENGTH_SHORT).show();
                }

            }else
                Toast.makeText(this, "File format must be .xls or .xlsx", Toast.LENGTH_SHORT).show();

            if (filesame != 1) {
                attchrl1.setVisibility(View.VISIBLE);
                t1.setText(filename);
                plainFilename=filename;

                new ShowProgress().execute();

            }

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
            lenght= atach1.length();
            String username = encadminUsername;

            try {

                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";

                byte[] filenameBytes = filename.getBytes("UTF-8");

                byte[] filenameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, filenameBytes);
                encfilename = new String(SimpleBase64Encoder.encode(filenameEncryptedBytes));

            }catch (Exception e){
                Log.d("TAG", "marksheet exp:" + e.getMessage());
            }
            String filename=encfilename;

            try {
                boundary = "===" + System.currentTimeMillis() + "===";
                URL url = new URL(Z.url_uploadSingleFile);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setUseCaches(false);
                httpConn.setDoOutput(true);	// indicates POST method
                httpConn.setDoInput(true);
                httpConn.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                httpConn.setRequestProperty("User-Agent", "PlaceMe Agent");
                httpConn.setRequestProperty("Test", "Bonjour");
                outputStream = httpConn.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                        true);

                name="u";
                vallue = username;
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                        .append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(
                        LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(vallue).append(LINE_FEED);
                writer.flush();
                if(filename!="") {
                    name2 ="f";
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

                    String  fieldName= "uf",uploadFile="";
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
                    long totalSize= lenght;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        Log.d("bytes",""+bytesRead);
                        progress += bytesRead;
                        data_for_progressbar=(int)((progress*100)/totalSize);
                        publishProgress("lavda");
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.append(LINE_FEED);
                    writer.flush();
                    writer.append(LINE_FEED).flush();
                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

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

                }else
                    name2 ="f";
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
            try{ Log.d("TAG;",response.get(0));
            }
            catch (Exception e){
                Toast.makeText(AddUsersActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

            }

        }
        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
            prg1.setProgress(data_for_progressbar);
        }

    }
    class CreateUser extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encadminUsername));    //0
            params.add(new BasicNameValuePair("ue", encuserEmail));//1
            json = jParser.makeHttpRequest(Z.url_createSingleUser_admin, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("TAG", "onPostExecute: result "+result);

            if (result.equals("success")) {
                Toast.makeText(AddUsersActivity.this, "User successfully Created!", Toast.LENGTH_SHORT).show();
                setResult(Z.USER_DATA_CHANGE_RESULT_CODE);

            } else if (result.equals("userExist")) {
                Toast.makeText(AddUsersActivity.this, "User already exist on Placeme", Toast.LENGTH_LONG).show();
            } else if(result.equals("Missing domain")){
                Toast.makeText(AddUsersActivity.this, "Kindly check email Address", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(AddUsersActivity.this, "Fail to create user", Toast.LENGTH_SHORT).show();
            }
        }
    }


    class CreateMultipleUser extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            String result = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encadminUsername));    //0
            params.add(new BasicNameValuePair("f", encfilename));//1

            json = jParser.makeHttpRequest(Z.url_createMultipleUser_admin, "GET", params);

            try {
                result = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_edit, menu);
        return super.onCreateOptionsMenu(menu);


    }

}
