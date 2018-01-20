package placeme.octopusites.com.placeme;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ConnectionQuality;
import com.androidnetworking.interfaces.ConnectionQualityChangeListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;


public class Z {
    //**************************  advertisement **************************

    public static final String BANNER_UNIT_ID = "ca-app-pub-4561960316771699/4063759942";
    public static final String VIDEO_UNIT_ID = "ca-app-pub-4561960316771699/5264504434";
    public static final String APP_ID = "ca-app-pub-4561960316771699~6596755648";

    //**************************  final **************************
    public static final String VPS_IP = "162.213.199.3";   // for authority
    public static final String IP = "http://162.213.199.3/";
    public static final String IP_TOMCAT = "http://162.213.199.3:8080/";
    public static final String IP_local_Glassfish = "http://162.213.199.3:8090/";
//    public static final String IP_secured_sunny = "http://104.237.4.236:26080/";
//    public static final String I = "http://104.237.4.236:26080/";
public static final String IP_8086 = "http://162.213.199.3:8086/";



    //********************************  final ************************************
    public static final String IP_secured_sunny_8090 =  "http://162.213.199.3:8090/";
    public static final String IP_secured_sunny = "https://placeme.co.in/";
    public static final String FAIL_TO_PROCESS = "Fail to process your request!\nPlease try again";
    public static final String FAIL_TO_UPLOAD_IMAGE = "Fail to upload image!\nPlease try again";
    public static String CountOfUsersUnderAdmin = "0";
    //======================================= login =====================================
    public static final String url_Welcome = IP + "AESTest/Welcome";
    public static final String url_login = IP + "AESTest/Auth";
    public static final String url_getdigest = IP + "AESTest/GetDigest";
    public static final String url_CheckInternet = IP + "AESTest/CheckInternet";
    public static final String url_SaveAndGenrateInstituteCode = IP + "AESTest/SaveAndGenrateInstituteCode";
    public static final String url_SaveAndGenrateCompanyCode = IP + "AESTest/SaveAndGenrateCompanyCode";
    //======================================= login =====================================

    //-----------------------------------------sunny---------------------------------------------------------------
    public static final String url_IsPlacemeVerified = IP + "AESTest/IsPlacemeVerified";
    public static final String GetFLName = IP_secured_sunny + "CreateNotificationTemp/GetFLname";

    // -------------------------------------------MainActivity(student)-----------------------------------------------
public static final String url_getnotificationsmetadata = IP_secured_sunny + "CreateNotificationTemp/GetNotificationsMetaData";
    public static final String url_getnotificationsreadstatus = IP_secured_sunny + "CreateNotificationTemp/GetReadStatusOfNotifications";
    public static final String url_getnotifications = IP_secured_sunny + "CreateNotificationTemp/GetNotifications";
    public static final String url_changenotificationsreadstatus = IP + "CreateNotificationTemp/ChangeNotificationReadStatus";

    public static final String url_getplacementsmetadata = IP_secured_sunny + "CreateNotificationTemp/GetPlacementsMetaData";
    public static final String url_getplacementsreadstatus = IP + "CreateNotificationTemp/GetReadStatusOfPlacements";
    public static final String url_changeplacementsreadstatus = IP + "CreateNotificationTemp/ChangePlacementReadStatus";
    public static final String url_getplacements = IP_secured_sunny + "CreateNotificationTemp/GetPlacements";
    //                           --------------------alumniActivity(alumni)----------------------------------------------
    public static final String url_GetNotificationsAlumniAlumniMetaData = IP_secured_sunny + "CreateNotificationTemp/GetNotificationsAlumniMetaData";
    public static final String url_GetReadStatusOfNotificationsAlumni = IP_secured_sunny + "CreateNotificationTemp/GetReadStatusOfNotificationsAlumni";
    public static final String url_GetNotificationsAlumni = IP_secured_sunny + "CreateNotificationTemp/GetNotificationsAlumni";

    public static final String url_ChangeNotificationReadStatus = IP + "CreateNotificationTemp/ChangeNotificationReadStatus";
    public static final String url_GetPlacementsAlumniAlumniMetaData = IP_secured_sunny + "CreateNotificationTemp/GetPlacementsAlumniMetaData";
    public static final String url_GetReadStatusOfPlacementsAlumni = IP + "CreateNotificationTemp/GetReadStatusOfPlacementsAlumni";
    public static final String url_GetPlacementsAlumni = IP + "CreateNotificationTemp/GetPlacementsAlumni";
    //                                --------------------adminActivity-----------------
//placement urls
    public static final String url_GetPlacementsAdminAdminMetaData = IP_secured_sunny + "CreateNotificationTemp/GetPlacementsAdminMetaData";
    public static final String url_GetReadStatusOfPlacementsForAdmin = IP_secured_sunny + "CreateNotificationTemp/GetReadStatusOfPlacementsForAdmin";
    public static final String url_GetPlacementsAdmin = IP_secured_sunny + "CreateNotificationTemp/GetPlacementsAdmin";
    public static final String url_ChangePlacementReadStatusAdmin = IP_secured_sunny + "CreateNotificationTemp/ChangePlacementReadStatusAdmin";
    //notiffurl
    public static final String url_GetNotificationsAdminAdminMetaData = IP_secured_sunny + "CreateNotificationTemp/GetNotificationsAdminMetaData";
    public static final String url_GetReadStatusOfNotificationsAdmin = IP_secured_sunny + "CreateNotificationTemp/GetReadStatusOfNotificationsAdmin";
    public static final String url_GetNotificationsAdmin = IP_secured_sunny + "CreateNotificationTemp/GetNotificationsAdmin";
    public static final String url_ChangeNotificationReadStatusAdmin = IP_secured_sunny + "CreateNotificationTemp/ChangeNotificationReadStatusAdmin";
    //    -------------------------------EditNotification-----------------------------------
    public static final String url_GetNotificationsByAdminMetaData = IP_secured_sunny + "CreateNotificationTemp/GetNotificationsByAdminMetaData";
    public static final String url_GetReadStatusOfNotificationsByAdmin = IP_secured_sunny + "CreateNotificationTemp/GetReadStatusOfNotificationsByAdmin";
    public static final String url_GetNotificationsSentByAdmin = IP_secured_sunny + "CreateNotificationTemp/GetNotificationsSentByAdmin";
    public static final String url_DeleteNotification = IP_secured_sunny + "CreateNotificationTemp/DeleteNotification";
    //    -------------------------------Editplacements -----------------------------------
    public static final String url_GetPlacementSentByAdminByAdminMetaData = IP_secured_sunny + "CreateNotificationTemp/GetPlacementsByAdminMetaData";
    public static final String url_GetReadStatusOfPlacementsByAFdmin = IP + "CreateNotificationTemp/GetReadStatusOfPlacementsByAdmin";
    public static final String url_GetPlacementSentByAdmin = IP_secured_sunny + "CreateNotificationTemp/GetPlacementSentByAdmin";
    public static final String url_Delete_Placements = IP_secured_sunny + "CreateNotificationTemp/DeletePlacement";
    //    -------------------------------hractivity -----------------------------------
    public static final String GetPlacementsCreatedByHrMetadata = IP_secured_sunny + "CreateNotificationTemp/GetPlacementsCreatedByHrMetadata";
    public static final String url_GetPlacementsCreatedByHr = IP_secured_sunny + "CreateNotificationTemp/GetPlacementsCreatedByHr";

    public static final String url_GetNotificationsHrMetadata = IP + "CreateNotificationTemp/GetNotificationsHrMetadata";
    public static final String url_GetNotificationsHr = IP + "CreateNotificationTemp/GetNotificationsHr";


    //    -------------------------------EditPlacementHr -----------------------------------
    public static final String url_GetPlacementSentByHr = IP + "CreateNotificationTemp/GetPlacementSentByHr";
    //    -------------------------------EditPlacementMainHr -----------------------------------
    public static final String url_ModifyPlacementHr = IP + "CreateNotificationTemp/ModifyPlacementHr";
    //    -------------------------------CreateNotification -----------------------------------
    public static final String url_UploadAttach1 = IP + "CreateNotificationTemp/UploadAttach1";
    public static final String url_ModifyNotification =IP + "CreateNotificationTemp/ModifyNotification";
    public static final String url_GetForWhomeNotification = IP + "CreateNotificationTemp/GetForWhomeNotification";    //files variables
    public static final String url_SavefileOnServer = IP_secured_sunny + "CreateNotificationTemp/SavefileOnServer";

    //    -------------------------------CreatePlacement -----------------------------------
    public static final String url_CreatePlacements =   IP + "CreateNotificationTemp/CreatePlacements";
    //    -------------------------------CreatePlacementHr -----------------------------------
    public static final String url_CreatePlacementsHr = IP + "CreateNotificationTemp/CreatePlacementsHr";
    //    -------------------------------CreateNotificationHR -----------------------------------
    public static final String url_CreateNotificationHrToEach = IP + "CreateNotificationTemp/CreateNotificationHrToEach";
    //    -------------------------------EditPlacementMain -----------------------------------
    public static final String url_GetForWhomePlacements = IP + "CreateNotificationTemp/GetForWhomePlacements";
    public static final String url_ModifyPlacement = IP + "CreateNotificationTemp/ModifyPlacement";


    //    ---------------------------------------ViewPlacement -----------------------------------
    public static final String url_RegisterForPlacement = IP + "CreateNotificationTemp/RegisterForPlacementss";
    public static final String url_GetStudentMarksInfo = IP_secured_sunny + "CreateNotificationTemp/GetStudentMarksInfo";
    public static final String url_SaveResume = IP + "GenerateResumeWithJODConverter3/SaveResume";
    //----------------------------------------EditPlacementMain -----------------------------------
    public static final String url_SaveShortListedUsers = IP + "CreateNotificationTemp/SaveShortListedUsers";
    public static final String url_SavePlacedUsers = IP + "CreateNotificationTemp/SavePlacedUsers";
    public static final String url_SaveRegistereduserStatus = IP_secured_sunny + "CreateNotificationTemp/SaveRegistereduserStatus";

    public static final int USER_DATA_CHANGE_RESULT_CODE = 888;
    public static final String USERNAME_KEY = "nameKey";
    public static final String PASSWORD_KEY = "passKey";
    public static final String users_under_your_supervision = " Users under your supervision. Click to view the list.";
    public static final String url_create_firebase = IP + "Firebase/RegisterFirebaseUser";
    public static final String url_UpdateFirebaseToken = IP + "Firebase/UpdateFirebaseToken";
    //    ----------------------------------/sunny---------------------------------------------------------------

    public static final String url_ChangeUsernameFireBase = IP_TOMCAT + "Firebase/ChangeUsername";
    public static final String url_get_chatrooms = IP + "Firebase/GetChatRooms";
    public static final String url_getmessagesreadstatus = IP + "Firebase/GetReadStatusOfMessages";
    public static final String url_changepass = IP + "Firebase/ChangePass";

    //#############################   Firebase   ######################################
    public static final String load_news = IP + "Firebase/GetNews";
    public static final String load_videos = IP + "Firebase/GetVideos";
    public static final String url_savechat = IP + "Firebase/SaveChat";
    public static final String url_loadchat = IP + "Firebase/LoadChat";
    public static final String url_SendPushNotification = IP + "Firebase/SendPushNotification";
    public static final String url_markread = IP + "Firebase/MarkRead";
    public static final String url_GetLastPushedMessage = IP + "Firebase/GetLastPushedMessage";
    public static final String url_ChangeMessageReadStatus = IP + "Firebase/ChangeMessageReadStatus";
    public static final String url_ForgotPasswordChange = IP_TOMCAT + "Firebase/ForgotPasswordChange";
    public static final String url_GenrateCustomToken = IP_TOMCAT + "Firebase/GenrateCustomToken";

    // Messages -----------------------------------------


    public static final String load_resume_ids = IP + "AESTest/GetResumeIds";
    public static final String url_editemail = IP + "AESTest/EditEmail";

    //**************************************   AESTest   *****************************************
    public static final String url_VerifyOTPEditEmail = IP + "AESTest/VerifyOTPEditEmail";
    public static final String url_CreatePass = IP + "AESTest/CreatePass";
    public static final String url_ForgotPassword = IP + "AESTest/ForgotPassword";
    public static final String url_SaveLogFile = IP + "ProfileObjects/SaveLogFile";


    public static final String url_resendotp = IP + "AESTest/ResendOTP";
    public static final String url_verifyotp = IP + "AESTest/VerifyOTP";
    public static final String url_GetMyResumeIds = IP + "AESTest/GetMyResumeIds";
    public static final String url_savepreferences = IP + "AESTest/SavePreferences";
    public static final String url_report = IP + "AESTest/ReportBug";
    public static final String download_resume_template = IP + "AESTest/DownloadResumeTemplate";
    public static final String url_getsession = IP_TOMCAT + "AESTest/GetSessionDetails";
    public static final String url_savesessiondetails = IP_TOMCAT + "AESTest/SaveSessionDetails";
    public static final String load_resume_pages = IP + "AESTest/GetAvailableResumePages";
    public static final String load_resume_page = IP + "AESTest/GetResumePage";
    public static final String load_HR_data = IP + "ProfileObjects/GetHrData";
    public static final String url_load_alumni_data = IP + "ProfileObjects/GetAlumniData";
    public static final String load_student_data = IP + "ProfileObjects/GetStudentData";
    public static final String load_Admin_data = IP + "ProfileObjects/GetAdminData";
    public static final String url_SaveIntro = IP + "ProfileObjects/SaveIntro";
    public static final String url_SaveHrIntro = IP + "ProfileObjects/SaveHrIntro";
    public static final String url_getinstitute = IP + "ProfileObjects/GetInstitute";
    public static final String url_IssubAdmin = IP + "AESTest/IsSubAdmin";

//    -----------------------------------ProfileObjects--------------------------------------------

    //    --------------------------------------Load fragment data---------------------------------
    public static final String url_SaveAdminIntro = IP + "ProfileObjects/SaveAdminIntro";
    public static final String url_saveprojects = IP + "ProfileObjects/SaveProjects";
    public static final String url_SaveTenth = IP + "ProfileObjects/SaveTenth";
    public static final String url_savedata_twelth = IP + "ProfileObjects/SaveTwelth";

    //    --------------------------------------Intro-------------------------------------
    public static final String url_savedata_diploma = IP + "ProfileObjects/SaveDiploma";
    public static final String url_savedata_ug = IP + "ProfileObjects/SaveUg";
    public static final String url_savedata_pg_sem = IP + "ProfileObjects/SavePgSem";

    //    --------------------------------------project-------------------------------------
    public static final String url_savedata_pg_year = IP + "ProfileObjects/SavePgYear";

    //    -----------------------------------Education details--------------------------
    public static final String url_savelanguages = IP + "ProfileObjects/SaveLanguages";
    public static final String url_savecertifications = IP + "ProfileObjects/SaveCertificates";
    public static final String url_savecourses = IP + "ProfileObjects/SaveCourses";
    public static final String url_saveskills = IP + "ProfileObjects/SaveSkills";
    public static final String url_savehonors = IP + "ProfileObjects/SaveHonors";
    public static final String url_savepatents = IP + "ProfileObjects/SavePatents";

    //    ----------------------------------------Accomplishments-----------------------------
    public static final String url_savepublications = IP + "ProfileObjects/SavePublications";
    public static final String url_savecareerobj = IP + "ProfileObjects/SaveCareerObj";
    public static final String url_savestrengths = IP + "ProfileObjects/SaveStrengths";
    public static final String url_saveweaknesses = IP + "ProfileObjects/SaveWeaknesses";
    public static final String url_savelocationpreferences = IP + "ProfileObjects/SaveLocationPreferences";
    public static final String url_SaveStdalmContact = IP + "ProfileObjects/SaveStdAlmContact";
    public static final String url_SaveAdminContact = IP + "ProfileObjects/SaveAdminContact";

    //    -----------------------------------------MyProfileCareerDetails---------------------------------
    public static final String url_SaveHrContact = IP + "ProfileObjects/SaveHrContact";
    public static final String url_SaveExperiences = IP + "ProfileObjects/SaveExperiences";
    public static final String savepersonalinfo = IP + "ProfileObjects/SavePersonalInfo";
    public static final String url_SaveAdminPersonal = IP + "ProfileObjects/SaveAdminPersonal";

    //    --------------------------------------------MyProfileContact---------------------------------
    public static final String url_SaveHrCompany = IP + "ProfileObjects/SaveHrCompany";
    public static final String url_SaveAdminInstituteData = IP + "ProfileObjects/SaveAdminInstituteData";
    public static final String url_getcourses = IP + "AESTest/GetDiplomaCourses";

    //   --------------------------------------------Experience---------------------------------

    //  --------------------------------------------Tabfragment---------------------------------

    //    ------------------------------------------Institute details---------------------------------

    //    ------------------------------------------Education details---------------------------------

    public static final String load_last_updated = IP + "AESTest/GetLastUpdated";
    public static final String url_remove_profile = IP + "AESTest/RemoveImage";
    public static final String url = IP + "HandleMobileRequests/getimg.jsp?username=";
    public static final String load_student_image = IP + "AESTest/GetImage";
    public static final String upload_profile = IP + "AESTest/UploadProfile";
    public static final String remove_profile = IP + "AESTest/RemoveImage";
    public static final String url_createSingleUser_admin = IP + "AESTest/CreateSingleUser";
    public static final String url_createSingleAdmin = IP + "AESTest/CreateSingleAdmin";

    public static final String url_createMultipleUser_admin = IP + "AESTest/CreateMultipleUser";
    public static final String url_delete_file = IP + "AESTest/DeleteFile";
    public static final String url_uploadSingleFile = IP + "AESTest/UploadSingleFile";
    public static final String url_GetRegisteredUsersUnderAdmin = IP + "AESTest/GetRegisteredUsersUnderAdmin";
    public static final String url_getlastupdated = IP + "AESTest/GetLastUpdated";
    public static final String url_SavePlacedDebarInfo = IP + "AESTest/SavePlacedDebarInfo";
    public static final String url_GetPlacedDebarInfo = IP + "AESTest/GetPlacedDebarInfo";
    public static final String url_DeleteNonActiveUser = IP + "AESTest/DeleteNonActiveUser";
    public static final String url_SaveWelcomeIntroData = IP + "AESTest/SaveNewUserWelcomeIntroData";
    public static final String url_SaveStudentFnameLnameMobile = IP + "AESTest/SaveStudentFnameLnameMobile";
    public static final String url_SendActivationCode = IP + "AESTest/SendActivationCode";
    public static final String url_checkUcode = IP + "AESTest/checkUcode";
    public static final String url_ClearOTP = IP + "AESTest/ClearOTP";
    public static final String url_AddStudentUnderAdmin = IP + "AESTest/AddStudentUnderAdmin";
    public static final String url_GetCountOfUsersUnderAdmin = IP + "AESTest/GetCountOfUsersUnderAdmin";

    private static final String IP_100 = "http://192.168.100.100/";
    private static final String IP_10 = "http://192.168.100.10/";
    private static final String IP_20 = "http://192.168.100.20/";
    private static final String IP_30 = "http://192.168.100.30/";

//    --------------------------------- function --------------------------------------------------------------

    private static Typeface FA = null, Bold = null, Light = null, Italic = null, BoldItalic = null, Righteous = null;
    private static Animation fadeAnimation = null, fadeandmovedownAnimation = null, slideoutleft2Animation = null, fadeoutAnimation = null, slideinleft1Animation = null, slideinleft2Animation = null, scaledownAnimation = null, bottomupbox1Animation = null, bottomupbox2Animation = null, bottomupbox3Animation = null, bottomupbox4Animation = null;
    public static String digest1 = null, digest2 = null;

    public static Typeface getFA(Context context) {
        if (FA != null)
            return FA;
        else
            FA = Typeface.createFromAsset(context.getAssets(), "fonts/fa.ttf");
        return FA;
    }

    public static Typeface getBold(Context context) {
        if (Bold != null)
            return Bold;
        else
            Bold = Typeface.createFromAsset(context.getAssets(), "fonts/nunitobold.ttf");
        return Bold;
    }

    public static Typeface getLight(Context context) {
        if (Light != null)
            return Light;
        else
            Light = Typeface.createFromAsset(context.getAssets(), "fonts/nunitolight.ttf");
        return Light;

    }

    public static Typeface getItalic(Context context) {
        if (Italic != null)
            return Italic;
        else
            Italic = Typeface.createFromAsset(context.getAssets(), "fonts/nunitoitalic.ttf");
        return Italic;

    }

    public static Typeface getBoldItalic(Context context) {
        if (BoldItalic != null)
            return BoldItalic;
        else
            BoldItalic = Typeface.createFromAsset(context.getAssets(), "fonts/nunitobolditalic.ttf");
        return BoldItalic;

    }

    public static Typeface getRighteous(Context context) {
        if (Righteous != null)
            return Righteous;
        else
            Righteous = Typeface.createFromAsset(context.getAssets(), "fonts/righteous.ttf");
        return Righteous;

    }

    public static void fade(Activity activity, View view) {

        if (fadeAnimation != null)
            view.startAnimation(fadeAnimation);
        else {
            fadeAnimation = AnimationUtils.loadAnimation(activity, R.anim.fadein);
            view.startAnimation(fadeAnimation);
        }

    }

    public static void fadeImage(Activity activity, View view) {

        if (fadeAnimation != null)
            view.startAnimation(fadeAnimation);
        else {
            fadeAnimation = AnimationUtils.loadAnimation(activity, R.anim.fadeinimage);
            view.startAnimation(fadeAnimation);
        }

    }


    public static void fadeandmovedown(Activity activity, View view) {

        if (fadeandmovedownAnimation != null)
            view.startAnimation(fadeandmovedownAnimation);
        else {
            fadeandmovedownAnimation = AnimationUtils.loadAnimation(activity, R.anim.fadeinmove);
            view.startAnimation(fadeandmovedownAnimation);
        }
    }

    public static void slideoutleft2(Activity activity, View view) {

        if (slideoutleft2Animation != null)
            view.startAnimation(slideoutleft2Animation);
        else {
            slideoutleft2Animation = AnimationUtils.loadAnimation(activity, R.anim.slideoutleft2);
            view.startAnimation(slideoutleft2Animation);
        }

    }

    public static void fadeout(Activity activity, View view) {

        if (fadeoutAnimation != null)
            view.startAnimation(fadeoutAnimation);
        else {
            fadeoutAnimation = AnimationUtils.loadAnimation(activity, R.anim.fadeout);
            view.startAnimation(fadeoutAnimation);
        }

    }

    public static void slideinleft1(Activity activity, View view) {

        if (slideinleft1Animation != null)
            view.startAnimation(slideinleft1Animation);
        else {
            slideinleft1Animation = AnimationUtils.loadAnimation(activity, R.anim.slideinleft1);
            view.startAnimation(slideinleft1Animation);
        }

    }

    public static void slideinleft2(Activity activity, View view) {

        if (slideinleft2Animation != null)
            view.startAnimation(slideinleft2Animation);
        else {
            slideinleft2Animation = AnimationUtils.loadAnimation(activity, R.anim.slideinleft2);
            view.startAnimation(slideinleft2Animation);
        }

    }

    public static void scale1(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.scaleup1);
        view.startAnimation(animation1);
    }

    public static void scale2(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.scaleup2);
        view.startAnimation(animation1);
    }

    public static void scale3(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.scaleup3);
        view.startAnimation(animation1);
    }

    public static void scale4(Activity activity, View view) {
        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.scaleup4);
        view.startAnimation(animation1);
    }

    public static void scaledown(Activity activity, View view) {

        if (scaledownAnimation != null)
            view.startAnimation(scaledownAnimation);
        else {
            scaledownAnimation = AnimationUtils.loadAnimation(activity, R.anim.scaledown);
            view.startAnimation(scaledownAnimation);
        }

    }

    public static void bottomupbox1(Activity activity, View view) {

        if (bottomupbox1Animation != null)
            view.startAnimation(bottomupbox1Animation);
        else {
            bottomupbox1Animation = AnimationUtils.loadAnimation(activity, R.anim.bottom_up_box1);
            view.startAnimation(bottomupbox1Animation);
        }

    }

    public static void bottomupbox2(Activity activity, View view) {

        if (bottomupbox2Animation != null)
            view.startAnimation(bottomupbox2Animation);
        else {
            bottomupbox2Animation = AnimationUtils.loadAnimation(activity, R.anim.bottom_up_box2);
            view.startAnimation(bottomupbox2Animation);
        }

    }

    public static void bottomupbox3(Activity activity, View view) {

        if (bottomupbox3Animation != null)
            view.startAnimation(bottomupbox3Animation);
        else {
            bottomupbox3Animation = AnimationUtils.loadAnimation(activity, R.anim.bottom_up_box3);
            view.startAnimation(bottomupbox3Animation);
        }

    }

    public static void bottomupbox4(Activity activity, View view) {
        if (bottomupbox4Animation != null)
            view.startAnimation(bottomupbox4Animation);
        else {
            bottomupbox4Animation = AnimationUtils.loadAnimation(activity, R.anim.bottom_up_box4);
            view.startAnimation(bottomupbox4Animation);
        }

    }

    public static String getDigest1(Context context) {
        if (digest1 != null)
            return digest1;
        else {
            digest1 = MySharedPreferencesManager.getDigest1(context);
        }
        return digest1;
    }

    public static String getDigest2(Context context) {
        if (digest2 != null)
            return digest2;
        else {
            digest2 = MySharedPreferencesManager.getDigest2(context);
        }
        return digest2;
    }

    public static String Encrypt(String string, Context context) throws Exception {
        byte[] demoKeyBytes = SimpleBase64Encoder.decode(getDigest1(context));
        byte[] demoIVBytes = SimpleBase64Encoder.decode(getDigest2(context));
        String sPadding = "ISO10126Padding";

        byte[] objBytes = string.getBytes("UTF-8");
        byte[] objEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, objBytes);

        return new String(SimpleBase64Encoder.encode(objEncryptedBytes));
    }

    public static String Decrypt(String string, Context context) throws Exception {
        byte[] demoKeyBytes = SimpleBase64Encoder.decode(getDigest1(context));
        byte[] demoIVBytes = SimpleBase64Encoder.decode(getDigest2(context));
        String sPadding = "ISO10126Padding";

        byte[] EncryptedBytes = SimpleBase64Encoder.decode(string);
        byte[] DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, EncryptedBytes);
        return new String(DecryptedBytes);
    }

    public static String md5(String input) {

        String md5 = null;

        if (null == input) return null;

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }

    public static boolean isAdminHrVerified(Context context) {
        String isVerify = MySharedPreferencesManager.getData(context, "placemeverify");
        Log.d("TAG", "shared isVerified: " + isVerify);
        if (isVerify != null) {
            try {
                if (Z.Decrypt(isVerify, context).equals("yes")) {
                    Log.d("TAG", "check isVerified: " + Z.Decrypt(isVerify, context));
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean isSubAdmin(Context context) {
        String isSubAdmin = MySharedPreferencesManager.getData(context, "IsSubadmin");
        Log.d("TAG", "shared isSubAdmin: " + isSubAdmin);
        if (isSubAdmin != null && isSubAdmin.equals("true")) {
            return true;
        }
        return false;
    }









    public static void NetworkConnectoin(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();

        try {
            OkHttpUtil.init(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        OkHttpUtil.getClient();

//        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.initialize(context, OkHttpUtil.getClient());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        AndroidNetworking.setBitmapDecodeOptions(options);
        AndroidNetworking.enableLogging();
        AndroidNetworking.setConnectionQualityChangeListener(new ConnectionQualityChangeListener() {
            @Override
            public void onChange(ConnectionQuality currentConnectionQuality, int currentBandwidth) {
//                Log.d(TAG, "onChange: currentConnectionQuality : " + currentConnectionQuality + " currentBandwidth : " + currentBandwidth);
            }
        });


    }


    public static String[] getMyFilePath(Context context, Uri uri) {

        File file = null;
        String[] array = new String[2];


// get name with extension
        String uriString = uri.toString();
        File myFile = new File(uriString);
        String path = myFile.getAbsolutePath();
        String displayName = null;
        if (uriString.startsWith("content://")) {
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        } else if (uriString.startsWith("file://")) {
            displayName = myFile.getName();
        }
//
        String scheme = uri.getScheme();
        if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
            try {
                InputStream fileInputStream = context.getApplicationContext().getContentResolver().openInputStream(uri);
                Log.d("TAG", "getFilePath:" + fileInputStream.available());
                // TODO check file size then create file

                if (fileInputStream.available() < 16777216) {
                    try {
//                        String k=Environment.getExternalStorageDirectory().getPath() + "/Place Me/";
                        File myDirectory = new File(Environment.getExternalStorageDirectory(), "/Place Me/tmp");
                        if (!myDirectory.exists()) {
                            myDirectory.mkdirs();
                        }
                        file = new File(Environment.getExternalStorageDirectory().toString() + "/Place Me/tmp", displayName);
                        OutputStream output = new FileOutputStream(file);
                        try {
                            byte[] buffer = new byte[4 * 1024]; // or other buffer size
                            int read;
                            while ((read = fileInputStream.read(buffer)) != -1) {
                                output.write(buffer, 0, read);
                            }
                            output.flush();
                        } finally {
                            output.close();
                        }
                    } finally {
                        fileInputStream.close();
                    }

                } else {
                    array[0] = "max";  // File Exceeds the Size Limit
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (scheme.equals(ContentResolver.SCHEME_FILE)) {
            String path1 = uri.getPath();
            try {
                file = new File(path1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (file != null) {
            array[0] = file.getPath();
            array[1] = displayName;
        }
        Log.d("TAG", "displayName: " + array[1]);
        Log.d("TAG", "getPath: " + array[0]);

        return array;
    }


}
