package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.soundcloud.android.crop.Crop;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;

public class WelcomeIntroThroughAdminFromLoginActivity extends AppCompatActivity implements ImagePickerCallback {

    File Imgfile;
    int crop_flag = 0;
    JSONParser jParser = new JSONParser();
    private String finalPath;
    EditText fnameEditText, lnameEditText, mobileEditText;
    CircleImageView profilePicture;
    TextView adminInfo;
    List<String> response;
    ProgressBar updateProgress;
    FrameLayout crop_layout;
    ImagePicker imagePicker;
    private ImageView resultView;
    TextInputLayout usernameTextInputLayout, fnameTextInputLayout, lnameTextInputLayout, mobileTextInputLayout;
    private String filepath, filename, directory;
    private String encUsersName, encPassword;
    Button start;
    private String fname, lname, mobile;
    String adminInstitute, adminfname, adminlname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_intro_through_admin_from_login);

        fnameEditText = (EditText) findViewById(R.id.fname);
        lnameEditText = (EditText) findViewById(R.id.lname);
        mobileEditText = (EditText) findViewById(R.id.mobile);
        profilePicture = (CircleImageView) findViewById(R.id.profilePic);
        updateProgress = (ProgressBar) findViewById(R.id.updateProgress);
        ImageButton iv_camera = (ImageButton) findViewById(R.id.iv_camera);
        start = findViewById(R.id.start);

        fnameEditText.setTypeface(Z.getBold(this));
        lnameEditText.setTypeface(Z.getBold(this));
        mobileEditText.setTypeface(Z.getBold(this));

        TextView getProfilePictureMsg = (TextView) findViewById(R.id.getProfilePictureMsg);
        adminInfo = (TextView) findViewById(R.id.adminInfo);
        TextView welcometextviewcontext3 = (TextView) findViewById(R.id.welcometextviewcontext3);

        getProfilePictureMsg.setTypeface(Z.getBold(this));
        adminInfo.setTypeface(Z.getBoldItalic(this));

        fnameTextInputLayout = (TextInputLayout) findViewById(R.id.fnameTextInputLayout);
        lnameTextInputLayout = (TextInputLayout) findViewById(R.id.lnameTextInputLayout);
        mobileTextInputLayout = (TextInputLayout) findViewById(R.id.mobileTextInputLayout);
        resultView = (ImageView) findViewById(R.id.result_image);
        crop_layout = (FrameLayout) findViewById(R.id.crop_layout);

        encUsersName = MySharedPreferencesManager.getUsername(WelcomeIntroThroughAdminFromLoginActivity.this);
        encPassword = MySharedPreferencesManager.getPassword(WelcomeIntroThroughAdminFromLoginActivity.this);


        adminfname = getIntent().getStringExtra("fname");
        adminlname = getIntent().getStringExtra("lname");
        adminInstitute = getIntent().getStringExtra("inst");

        if (adminfname != null && adminlname != null && adminInstitute != null) {
            String MSG = "Your account has already been created by " + adminfname + " " + adminlname + " from " + adminInstitute;
            adminInfo.setText(MSG);
        }

        try {
            Log.d("TAG", "onCreate: " + Z.Decrypt(encUsersName, WelcomeIntroThroughAdminFromLoginActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Z.slideinleft1(this, getProfilePictureMsg);
        Z.slideinleft2(this, adminInfo);
        Z.fade(this, welcometextviewcontext3);
        Z.fade(this, profilePicture);
        Z.fade(this, iv_camera);
        Z.fadeandmovedown(this, fnameTextInputLayout);
        Z.fadeandmovedown(this, lnameTextInputLayout);
        Z.fadeandmovedown(this, mobileTextInputLayout);

        fnameTextInputLayout.setTypeface(Z.getLight(this));
        lnameTextInputLayout.setTypeface(Z.getLight(this));
        mobileTextInputLayout.setTypeface(Z.getLight(this));

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCropImage();
            }
        });
        fnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fnameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lnameTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mobileEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mobileTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        resultView = (ImageView) findViewById(R.id.result_image);
        imagePicker = new ImagePicker(this);
        imagePicker.setImagePickerCallback(WelcomeIntroThroughAdminFromLoginActivity.this);
        imagePicker.shouldGenerateMetadata(false); // Default is true
        imagePicker.shouldGenerateThumbnails(false); // Default is true


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean errorFlagThroughAdminIntro = false;
                fname = fnameEditText.getText().toString().trim();
                lname = lnameEditText.getText().toString().trim();
                mobile = mobileEditText.getText().toString().trim();

                if (fname.length() < 2) {
                    errorFlagThroughAdminIntro = true;
                    fnameTextInputLayout.setError("Kindly provide your first name");
                } else if (lname.length() < 2) {
                    lnameTextInputLayout.setError("Kindly provide your last name");
                    errorFlagThroughAdminIntro = true;
                } else if (mobile.length() != 10) {
                    mobileTextInputLayout.setError("Kindly provide your correct mobile number");
                    errorFlagThroughAdminIntro = true;
                }


                if (errorFlagThroughAdminIntro == false) {
                    new SaveDataUserCreatedThroughAdmin().execute();
                }

            }
        });


    }

    class SaveDataUserCreatedThroughAdmin extends AsyncTask<String, String, Boolean> {

        String result = null, encfname, enclname, encmobile;

        protected Boolean doInBackground(String... param) {

            try {
                encfname = Z.Encrypt(fname, WelcomeIntroThroughAdminFromLoginActivity.this);
                enclname = Z.Encrypt(lname, WelcomeIntroThroughAdminFromLoginActivity.this);
                encmobile = Z.Encrypt(mobile, WelcomeIntroThroughAdminFromLoginActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }


            Log.d("TAG", "doInBackground: encUsersName " + encUsersName);
            Log.d("TAG", "doInBackground: encfname " + encfname);
            Log.d("TAG", "doInBackground: enclname " + enclname);
            Log.d("TAG", "doInBackground: encmobile " + encmobile);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsersName));       //0
            params.add(new BasicNameValuePair("f", encfname));           //1
            params.add(new BasicNameValuePair("l", enclname));           //2
            params.add(new BasicNameValuePair("m", encmobile));          //3
            JSONObject json = jParser.makeHttpRequest(Z.url_SaveStudentFnameLnameMobile, "GET", params);
            if (json != null) {
                try {
                    result = json.getString("info");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                return false;

            return true;
        }

        @Override
        protected void onPostExecute(Boolean notNull) {

            if (notNull) {
                if (result.equals("success")) {
                    MySharedPreferencesManager.save(WelcomeIntroThroughAdminFromLoginActivity.this, "role", "student");
                    MySharedPreferencesManager.save(WelcomeIntroThroughAdminFromLoginActivity.this, "nameKey", encUsersName);

                    CreateFirebaseUser(encUsersName, encPassword);
                    try {
                        loginFirebase(Z.Decrypt(encUsersName, WelcomeIntroThroughAdminFromLoginActivity.this), Z.md5(Z.Decrypt(encPassword, WelcomeIntroThroughAdminFromLoginActivity.this) + MySharedPreferencesManager.getDigest3(WelcomeIntroThroughAdminFromLoginActivity.this)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    startActivity(new Intent(WelcomeIntroThroughAdminFromLoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    finish();

                }
            } else
                Toast.makeText(WelcomeIntroThroughAdminFromLoginActivity.this, Z.FAIL_TO_PROCESS, Toast.LENGTH_SHORT).show();
        }
    }

    void CreateFirebaseUser(final String u, final String p) {
        String u1 = null, p1 = null;
        try {
            u1 = Z.Decrypt(u, WelcomeIntroThroughAdminFromLoginActivity.this);
            p1 = Z.Decrypt(p, WelcomeIntroThroughAdminFromLoginActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String u2 = u1;
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(u1, Z.md5(p1 + MySharedPreferencesManager.getDigest3(WelcomeIntroThroughAdminFromLoginActivity.this)))
                .addOnCompleteListener(WelcomeIntroThroughAdminFromLoginActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = user.getUid();
                            MySharedPreferencesManager.save(WelcomeIntroThroughAdminFromLoginActivity.this, "uid", uid);
                            Log.d("TAG", "firebase user created in otp activity with email: " + u2 + "\nuid: " + uid);


                        } else {
                            Log.d("TAG", "firebase user creation failed in otp activity:");

                        }
                    }
                });


    }

    void loginFirebase(String username, String hash) {

        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username, hash)
                .addOnCompleteListener(WelcomeIntroThroughAdminFromLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            Toast.makeText(WelcomeIntroThroughAdminFromLoginActivity.this, "Successfully logged in to Firebase from otp activity", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(WelcomeIntroThroughAdminFromLoginActivity.this, "Failed to login to Firebase from otp activity", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        if (requestCode == Picker.PICK_IMAGE_DEVICE) {

            try {

                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback((ImagePickerCallback) this);
                }
                imagePicker.submit(result);
                crop_layout.setVisibility(View.VISIBLE);

                crop_flag = 1;
                beginCrop(result.getData());

            } catch (Exception e) {
                e.printStackTrace();
                crop_layout.setVisibility(View.GONE);
            }
        } else if (resultCode == RESULT_CANCELED) {
            crop_layout.setVisibility(View.GONE);


            crop_flag = 0;
        } else if (requestCode == Crop.REQUEST_CROP) {
            // Toast.makeText(this, "cropped", Toast.LENGTH_SHORT).show();
            handleCrop(resultCode, result);
        }

    }


    public void requestCropImage() {
        resultView.setImageDrawable(null);
        MySharedPreferencesManager.save(WelcomeIntroThroughAdminFromLoginActivity.this, "crop", "yes");
        chooseImage();
    }

    private void chooseImage() {
        imagePicker.pickImage();
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            File f = new File(getCacheDir(), "cropped");
            filepath = f.getAbsolutePath();

            filename = "";
            int index = filepath.lastIndexOf("/");
            directory = "";
            for (int i = 0; i < index; i++)
                directory += filepath.charAt(i);

            for (int i = index + 1; i < filepath.length(); i++)
                filename += filepath.charAt(i);

            crop_layout.setVisibility(View.GONE);
//            new UploadProfile().execute();
            new CompressTask().execute();

        } else if (resultCode == Crop.RESULT_ERROR) {
            crop_layout.setVisibility(View.GONE);

            Toast.makeText(this, "Try Again..!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onImagesChosen(List<ChosenImage> list) {
        final ChosenImage file = list.get(0);

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (file != null) {

                    finalPath = file.getOriginalPath().toString();
                }
            }
        });
    }

    public void onError(String s) {
        crop_layout.setVisibility(View.GONE);
        // tswap       tswipe_refresh_layout.setVisibility(View.GONE);
        Toast.makeText(WelcomeIntroThroughAdminFromLoginActivity.this, "Try again !", Toast.LENGTH_SHORT).show();

    }

    class CompressTask extends AsyncTask<String, String, Boolean> {
        protected Boolean doInBackground(String... param) {
            File sourceFile = new File(filepath);

            Luban.compress(WelcomeIntroThroughAdminFromLoginActivity.this, sourceFile)
                    .setMaxSize(256)                // limit the final image size（unit：Kb）
                    .putGear(Luban.CUSTOM_GEAR)
                    .launch(new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(File file) {

                            if (file.exists()) {
                                String filepath = file.getAbsolutePath();
                                String filename = "";
                                int index = filepath.lastIndexOf("/");
                                directory = "";
                                for (int i = 0; i < index; i++)
                                    directory += filepath.charAt(i);
                                for (int i = index + 1; i < filepath.length(); i++)
                                    filename += filepath.charAt(i);
                                Imgfile = file;

                                new UploadProfile().execute();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("TAG", "karina exp:" + e.getMessage());
                        }
                    });
            return true;
        }
    }

    class UploadProfile extends AsyncTask<String, String, Boolean> {

        @Override
        protected void onPreExecute() {
            updateProgress.setVisibility(View.VISIBLE);
        }

        protected Boolean doInBackground(String... param) {

            if (Imgfile != null) {

                MultipartUtility multipart = null;
                try {
                    multipart = new MultipartUtility(Z.upload_profile, "UTF-8");
                    multipart.addFormField("u", encUsersName);
                    if (filename != "") {
                        multipart.addFormField("f", filename);
                        multipart.addFilePart("uf", Imgfile);
                    } else
                        multipart.addFormField("f", "null");
                    response = multipart.finish();

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("TAG", "fb exp:" + e.getMessage());

                }

            } else
                return false;

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            crop_layout.setVisibility(View.GONE);
            updateProgress.setVisibility(View.GONE);
            if (result) {

                if (response != null && response.get(0).contains("success")) {
                    MySharedPreferencesManager.save(WelcomeIntroThroughAdminFromLoginActivity.this, "crop", "no");
                    requestProfileImage();
                    refreshContent();
                    Toast.makeText(WelcomeIntroThroughAdminFromLoginActivity.this, "Photo uploaded successfully !", Toast.LENGTH_SHORT).show();
                    DeleteRecursive(new File(directory));
                } else if (response != null && response.get(0).contains("null")) {
                    requestProfileImage();
                    Toast.makeText(WelcomeIntroThroughAdminFromLoginActivity.this, "Upload failed, please try again !", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(WelcomeIntroThroughAdminFromLoginActivity.this, Z.FAIL_TO_UPLOAD_IMAGE, Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(WelcomeIntroThroughAdminFromLoginActivity.this, Z.FAIL_TO_UPLOAD_IMAGE, Toast.LENGTH_SHORT).show();
        }

        void DeleteRecursive(File fileOrDirectory) {

            if (fileOrDirectory.isDirectory())
                for (File child : fileOrDirectory.listFiles())
                    DeleteRecursive(child);

            fileOrDirectory.delete();

        }

    }

    public void refreshContent() {

        downloadImage();
    }

    public void requestProfileImage() {
        downloadImage();
    }

    private void downloadImage() {
        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority(Z.VPS_IP)
                .path("AESTest/GetImage")
                .appendQueryParameter("u", encUsersName)
                .build();

        Glide.with(this)
                .load(uri)
                .signature(new StringSignature(System.currentTimeMillis() + ""))
                .into(profilePicture);

    }

}
