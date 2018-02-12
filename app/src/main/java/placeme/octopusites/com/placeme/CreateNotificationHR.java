package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CreateNotificationHR extends AppCompatActivity {
    private static final String LINE_FEED = "\r\n";
    RelativeLayout file1;
    RelativeLayout attchrl1, attchrl2, attchrl3, attchrl4, attchrl5;
    TextView t1, t2, t3, t4, t5, t6;
    ProgressBar prg1, prg2, prg3, prg4, prg5;
    String sstitle = "", ssnotification = "", ssfile1 = "", ssfile2 = "", ssfile3 = "", ssfile4 = "", ssfile5 = "", ssuploadedby = "", ssuploadtime = "", sslastmodified = "";
    String stitle = "", snotiffication = "";
    String forwhom = "", tempselection = "", tempforwhom = "";
    TextView createnotitxt, createnotinotitxt, attachmentstxt;
    TextInputLayout titleinput, notificationinput;
    int errorflag = 0;
    EditText title, notification;
    String encTitle = "", encNotiffication = "", encforwhom = "", encUsername = "";
    JSONObject json;
    JSONParser jParser = new JSONParser();
    //file work
    int filecounter = 0;
    int filesame = 0;
    String filePath = "";
    long lenght;
    String filename = "";
    String directory = "";
    ArrayList<String> a1 = new ArrayList<>();
    String srole = "", instname = "", digest1 = "", digest2;
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    LinkedHashMap<String, String> map2 = new LinkedHashMap<>();
    String encfilenameparam1, encfilenameparam2, encfilenameparam3, encfilenameparam4, encfilenameparam5;
    String filenameparam1 = "", filenameparam2 = "", filenameparam3 = "", filenameparam4 = "", filenameparam5 = "";
    String username = "";
    String name = "", vallue = "", name2 = "", value2 = "";
    int progress, progress2, progress3, progress4, progress5;
    int data_for_progressbar, data_for_progressbar1, data_for_progressbar2, data_for_progressbar3, data_for_progressbar4;
    List<String> response = new ArrayList<String>();
    Drawable compleatesprogress;
    ScrollView scrollview;
    int edittedFlag = 0, containsall = 0;
    //connection work
    private HttpURLConnection httpConn;
    private OutputStream outputStream;
    private String charset = "UTF-8";
    private String boundary;
    private PrintWriter writer;
    private String TAG = "CreateNotificationHR";
    private String encRole = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification_hr);
        scrollview = ((ScrollView) findViewById(R.id.schroll1));


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Create Notification");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        Window window = CreateNotificationHR.this.getWindow();

        AndroidNetworking.initialize(getApplicationContext(), OkHttpUtil.getClient());


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

        a1 = new ArrayList<>();
        prg1 = (ProgressBar) findViewById(R.id.PROGRESS_BAR);
        prg2 = (ProgressBar) findViewById(R.id.PROGRESS_BAR2);
        prg3 = (ProgressBar) findViewById(R.id.PROGRESS_BAR3);
        prg4 = (ProgressBar) findViewById(R.id.PROGRESS_BAR4);
        prg5 = (ProgressBar) findViewById(R.id.PROGRESS_BAR5);

        srole = MySharedPreferencesManager.getRole(this);
        encUsername = MySharedPreferencesManager.getUsername(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        titleinput = (TextInputLayout) findViewById(R.id.titleinput);
        notificationinput = (TextInputLayout) findViewById(R.id.notificationinput);
        title = (EditText) findViewById(R.id.title);
        notification = (EditText) findViewById(R.id.notification);

        titleinput.setTypeface(Z.getLight(this));
        notificationinput.setTypeface(Z.getLight(this));
        title.setTypeface(Z.getBold(this));
        notification.setTypeface(Z.getBold(this));


        tempforwhom = getIntent().getStringExtra("forwhom");
        tempselection = getIntent().getStringExtra("selection");
        Log.d("Tag", "emailids: " + tempforwhom);
        Log.d("Tag", "selection: " + tempselection);

        if (tempselection != null) {
            if (tempselection.equals("registered")) {
                createnotinotitxt.setText("Your notification will be sent  to selected Registerd users.");
            } else if (tempselection.equals("shortlisted")) {
                createnotinotitxt.setText("Your notification will be sent  to selected shortlisted users.");
            } else if (tempselection.equals("placed")) {
                createnotinotitxt.setText("Your notification will be sent  to All Placed shortlisted users.");
            }
        }

        if (tempforwhom != null) {


        }

        srole = MySharedPreferencesManager.getRole(this);
        instname = MySharedPreferencesManager.getInstitute(this);
        encUsername = MySharedPreferencesManager.getUsername(this);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleinput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        notification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                notificationinput.setError(null);
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (filecounter < 5) {
                    filesame = 0;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.setType("*/*");
                    startActivityForResult(i, 9);
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


                stitle = title.getText().toString();
                snotiffication = notification.getText().toString();
                encUsername = MySharedPreferencesManager.getUsername(this);

                if (stitle.length() <= 2) {
                    errorflag = 1;
                    title.setError("invalid title");
                } else if (snotiffication.length() <= 2) {
                    errorflag = 1;
                    title.setError("invalid notification");
                }


                if (errorflag == 0) {
                    try {
                        encTitle = Z.Encrypt(stitle, CreateNotificationHR.this);
                        encNotiffication = Z.Encrypt(snotiffication, CreateNotificationHR.this);
                        encforwhom = Z.Encrypt(tempforwhom, CreateNotificationHR.this);
                        encRole = Z.Encrypt(srole, CreateNotificationHR.this);


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
        if (edittedFlag == 1) {

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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
                }
            });

            alertDialog.show();

        } else {
            CreateNotificationHR.super.onBackPressed();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // new
        if (requestCode == 9 && resultCode == RESULT_OK) {

            Uri uri = data.getData();
            String[] fileInfoArray = Z.getMyFilePath(CreateNotificationHR.this, uri);

            filePath = fileInfoArray[0];
            filename = fileInfoArray[1];

            File file = new File(filePath);

            if (lenght > 16777216) {
                Toast.makeText(CreateNotificationHR.this, "File Exceeds the Size Limit(16MB)", Toast.LENGTH_LONG).show();
                filesame = 1;
            }

            try {
                for (int i = 0; i < a1.size(); i++) {
                    if (a1.get(i).equals(filename)) {
                        filesame = 1;
                    }
                }
            } catch (Exception e) {
            }

            if (filesame != 1) {

                a1.add(filename);
                map.put(filename, filePath);
                map2.put(filename, lenght + "");
                filecounter++;
                refresh();
                if (filecounter == 1) {
//                    new ShowProgress1().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    ShowProgress1(filename, filePath);
                }
                if (filecounter == 2) {
                    ShowProgress2(filename, filePath);
                }
                if (filecounter == 3) {
                    ShowProgress3(filename, filePath);
                }
                if (filecounter == 4) {
                    ShowProgress4(filename, filePath);

                }
                if (filecounter == 5) {
                    ShowProgress5(filename, filePath);

                }

            } else {
                Toast.makeText(CreateNotificationHR.this, "File is already uploaded", Toast.LENGTH_LONG).show();
            }

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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(CreateNotificationHR.this));
            }
        });
        alertDialog.show();
    }

    public void ShowProgress1(final String filename, String filePath) {

        final File file1 = new File(filePath);

        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            username = Z.Decrypt(encUsername, CreateNotificationHR.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AndroidNetworking.upload(Z.url_SavefileOnServer)
                .setPriority(Priority.LOW)
                .addQueryParameter("u", username)
                .addQueryParameter("f", filename)
                .setOkHttpClient(OkHttpUtil.getClient())
                .addMultipartFile("uf", file1)
                .setTag(this)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        Log.d(TAG, "bytesUploaded : " + bytesUploaded + " totalBytes : " + totalBytes);
                        int precent = (int) ((bytesUploaded * 100) / totalBytes);
                        Log.d(TAG, "precent :" + precent);
                        prg1.setProgress(precent);

                        Log.d(TAG, "setUploadProgressListener isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Image upload Completed");
                        Log.d(TAG, "onResponse object : " + response.toString());
                        if (file1 != null)
                            file1.delete();
                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received ANError from server
                            // error.getErrorCode() - the ANError code from server
                            // error.getErrorBody() - the ANError body from server
                            // error.getErrorDetail() - just a ANError detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Toast.makeText(CreateNotificationHR.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }

    public void ShowProgress2(final String filename, String filePath) {

        final File file1 = new File(filePath);

        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            username = Z.Decrypt(encUsername, CreateNotificationHR.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AndroidNetworking.upload(Z.url_SavefileOnServer)
                .setPriority(Priority.LOW)
                .addQueryParameter("u", username)
                .addQueryParameter("f", filename)
                .setOkHttpClient(OkHttpUtil.getClient())
                .addMultipartFile("uf", file1)
                .setTag(this)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        Log.d(TAG, "bytesUploaded : " + bytesUploaded + " totalBytes : " + totalBytes);
                        int precent = (int) ((bytesUploaded * 100) / totalBytes);
                        Log.d(TAG, "precent :" + precent);
                        prg1.setProgress(precent);

                        Log.d(TAG, "setUploadProgressListener isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Image upload Completed");
                        Log.d(TAG, "onResponse object : " + response.toString());
                        if (file1 != null)
                            file1.delete();
                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received ANError from server
                            // error.getErrorCode() - the ANError code from server
                            // error.getErrorBody() - the ANError body from server
                            // error.getErrorDetail() - just a ANError detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Toast.makeText(CreateNotificationHR.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }

    public void ShowProgress3(final String filename, String filePath) {

        final File file1 = new File(filePath);

        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            username = Z.Decrypt(encUsername, CreateNotificationHR.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AndroidNetworking.upload(Z.url_SavefileOnServer)
                .setPriority(Priority.LOW)
                .addQueryParameter("u", username)
                .addQueryParameter("f", filename)
                .setOkHttpClient(OkHttpUtil.getClient())
                .addMultipartFile("uf", file1)
                .setTag(this)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        Log.d(TAG, "bytesUploaded : " + bytesUploaded + " totalBytes : " + totalBytes);
                        int precent = (int) ((bytesUploaded * 100) / totalBytes);
                        Log.d(TAG, "precent :" + precent);
                        prg1.setProgress(precent);

                        Log.d(TAG, "setUploadProgressListener isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Image upload Completed");
                        Log.d(TAG, "onResponse object : " + response.toString());
                        if (file1 != null)
                            file1.delete();
                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received ANError from server
                            // error.getErrorCode() - the ANError code from server
                            // error.getErrorBody() - the ANError body from server
                            // error.getErrorDetail() - just a ANError detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Toast.makeText(CreateNotificationHR.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }

    public void ShowProgress4(final String filename, String filePath) {

        final File file1 = new File(filePath);

        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            username = Z.Decrypt(encUsername, CreateNotificationHR.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AndroidNetworking.upload(Z.url_SavefileOnServer)
                .setPriority(Priority.LOW)
                .addQueryParameter("u", username)
                .addQueryParameter("f", filename)
                .setOkHttpClient(OkHttpUtil.getClient())
                .addMultipartFile("uf", file1)
                .setTag(this)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        Log.d(TAG, "bytesUploaded : " + bytesUploaded + " totalBytes : " + totalBytes);
                        int precent = (int) ((bytesUploaded * 100) / totalBytes);
                        Log.d(TAG, "precent :" + precent);
                        prg1.setProgress(precent);

                        Log.d(TAG, "setUploadProgressListener isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Image upload Completed");
                        Log.d(TAG, "onResponse object : " + response.toString());
                        if (file1 != null)
                            file1.delete();
                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received ANError from server
                            // error.getErrorCode() - the ANError code from server
                            // error.getErrorBody() - the ANError body from server
                            // error.getErrorDetail() - just a ANError detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Toast.makeText(CreateNotificationHR.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }

    public void ShowProgress5(final String filename, String filePath) {

        final File file1 = new File(filePath);

        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            username = Z.Decrypt(encUsername, CreateNotificationHR.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AndroidNetworking.upload(Z.url_SavefileOnServer)
                .setPriority(Priority.LOW)
                .addQueryParameter("u", username)
                .addQueryParameter("f", filename)
                .setOkHttpClient(OkHttpUtil.getClient())
                .addMultipartFile("uf", file1)
                .setTag(this)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        Log.d(TAG, "bytesUploaded : " + bytesUploaded + " totalBytes : " + totalBytes);
                        int precent = (int) ((bytesUploaded * 100) / totalBytes);
                        Log.d(TAG, "precent :" + precent);
                        prg1.setProgress(precent);

                        Log.d(TAG, "setUploadProgressListener isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Image upload Completed");
                        Log.d(TAG, "onResponse object : " + response.toString());
                        if (file1 != null)
                            file1.delete();
                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received ANError from server
                            // error.getErrorCode() - the ANError code from server
                            // error.getErrorBody() - the ANError body from server
                            // error.getErrorDetail() - just a ANError detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Toast.makeText(CreateNotificationHR.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }

    class SaveData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... param) {


            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("r", encRole));   //1
            params.add(new BasicNameValuePair("f", encTitle));       //2
            params.add(new BasicNameValuePair("l", encNotiffication));   //3
            params.add(new BasicNameValuePair("c", encforwhom));     //4

            Log.d("Params", "encUsername:0 " + encUsername);
            Log.d("Params", "encTitle:2 " + encTitle);
            Log.d("Params", "encNotiffication: 3" + encNotiffication);
            Log.d("Params", "encforwhom: 4" + encforwhom);


            if (filenameparam1.length() != 0 || filenameparam1 != "") {

                params.add(new BasicNameValuePair("f1", filenameparam1));  //5
                Log.d("Params", "filenameparam1: 5" + filenameparam1);

            } else {
                params.add(new BasicNameValuePair("f1", "BLANK"));
                Log.d("Params", "BLANK: 5");

            }

            if (filenameparam2.length() != 0 || filenameparam2 != "") {
                params.add(new BasicNameValuePair("f2", filenameparam2));     //6
                Log.d("Params", "filenameparam2: 6" + filenameparam2);

            } else {
                params.add(new BasicNameValuePair("f2", "BLANK"));
                Log.d("Params", "BLANK: 6");


            }
            if (filenameparam3.length() != 0 || filenameparam3 != "") {
                params.add(new BasicNameValuePair("f3", filenameparam3));     //7
                Log.d("Params", "filenameparam3: 7" + filenameparam3);

            } else {
                params.add(new BasicNameValuePair("f3", "BLANK"));
                Log.d("Params", "BLANK: 7");


            }
            if (filenameparam4.length() != 0 || filenameparam4 != "") {
                params.add(new BasicNameValuePair("f4", filenameparam4));     //8
                Log.d("Params", "filenameparam4: 8" + filenameparam4);

            } else {
                params.add(new BasicNameValuePair("f4", "BLANK"));
                Log.d("Params", "BLANK: 8");


            }
            if (filenameparam5.length() != 0 || filenameparam5 != "") {
                params.add(new BasicNameValuePair("f5", filenameparam5));     //9
                Log.d("Params", "filenameparam5: 9" + filenameparam5);

            } else {
                params.add(new BasicNameValuePair("f5", "BLANK"));
                Log.d("Params", "BLANK: 9");

            }


            json = jParser.makeHttpRequest(Z.url_CreateNotificationHrToEach, "GET", params);
            try {
                r = json.getString("info");
                Log.d(TAG, "doInBackground: Response " + r);

            } catch (Exception e) {
                Log.d("crash============", "" + e.getMessage());
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {
                Toast.makeText(CreateNotificationHR.this, "Notification  Sent Successfully!", Toast.LENGTH_SHORT).show();

//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result", result);
//                if(edittedFlag==1){
//                    setResult(111);
//                }
                CreateNotificationHR.super.onBackPressed();
            } else {
                Toast.makeText(CreateNotificationHR.this, result, Toast.LENGTH_SHORT).show();

            }
        }
    }


}
