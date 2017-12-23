package placeme.octopusites.com.placeme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;


public class Z {
    public static final String VPS_IP = "104.237.4.236";   // for authority
    public static final String IP = "http://104.237.4.236/";

    public static final String IP_8081 = "http://104.237.4.236:8081/";

    public static final String IP_1234= "http://104.237.4.236:1234/";

    public static final String IP_8086 = "http://104.237.4.236:8086/";

    public static final String Local_IP_Raju = "http://192.168.100.100:8080/";

    public static final String FAIL_TO_PROCESS="Fail to process your request!\nPlease try again";
//;sid
    //-----------------------------------------sunny---------------------------------------------------------------
// -------------------------------------------MainActivity(student)-----------------------------------------------
    public static final String url_getnotificationsmetadata = IP + "CreateNotificationTemp/GetNotificationsMetaData";
    public static final String url_getnotificationsreadstatus = IP + "CreateNotificationTemp/GetReadStatusOfNotifications";
    public static final String url_getnotifications = IP + "CreateNotificationTemp/GetNotifications";
    public static final String url_changenotificationsreadstatus = IP + "CreateNotificationTemp/ChangeNotificationReadStatus";

    public static final String url_getplacementsmetadata = IP + "CreateNotificationTemp/GetPlacementsMetaData";
    public static final String url_getplacementsreadstatus = IP + "CreateNotificationTemp/GetReadStatusOfPlacements";
    public static final String url_changeplacementsreadstatus = IP + "CreateNotificationTemp/ChangePlacementReadStatus";
    public static final String url_getplacements = IP + "CreateNotificationTemp/GetPlacements";
    //                           --------------------alumniActivity(alumni)----------------------------------------------
    public static final String url_GetNotificationsAlumniAlumniMetaData = IP + "CreateNotificationTemp/GetNotificationsAlumniMetaData";
    public static final String url_GetReadStatusOfNotificationsAlumni = IP + "CreateNotificationTemp/GetReadStatusOfNotificationsAlumni";
    public static final String url_GetNotificationsAlumni = IP + "CreateNotificationTemp/GetNotificationsAlumni";
    public static final String url_ChangeNotificationReadStatus = IP + "CreateNotificationTemp/ChangeNotificationReadStatus";
    public static final String url_GetPlacementsAlumniAlumniMetaData = IP + "CreateNotificationTemp/GetPlacementsAlumniMetaData";
    public static final String url_GetReadStatusOfPlacementsAlumni = IP + "CreateNotificationTemp/GetReadStatusOfPlacementsAlumni";
    public static final String url_GetPlacementsAlumni = IP + "CreateNotificationTemp/GetPlacementsAlumni";
    public static final String url_ChangePlacementReadStatus = IP + "CreateNotificationTemp/ChangePlacementReadStatus";
    //                                --------------------adminActivity-----------------
//placement urls
    public static final String url_GetPlacementsAdminAdminMetaData = IP_8086 + "CreateNotificationTemp/GetPlacementsAdminMetaData";
    public static final String url_GetReadStatusOfPlacementsForAdmin = IP_8086 + "CreateNotificationTemp/GetReadStatusOfPlacementsForAdmin";
    public static final String url_GetPlacementsAdmin = IP_8086 + "CreateNotificationTemp/GetPlacementsAdmin";
    public static final String url_ChangePlacementReadStatusAdmin = IP_8086 + "CreateNotificationTemp/ChangePlacementReadStatusAdmin";
    //notiffurl
    public static final String url_GetNotificationsAdminAdminMetaData = IP_8086 + "CreateNotificationTemp/GetNotificationsAdminMetaData";
    public static final String url_GetReadStatusOfNotificationsAdmin = IP_8086 + "CreateNotificationTemp/GetReadStatusOfNotificationsAdmin";
    public static final String url_GetNotificationsAdmin = IP_8086 + "CreateNotificationTemp/GetNotificationsAdmin";
    public static final String url_ChangeNotificationReadStatusAdmin = IP_8086 + "CreateNotificationTemp/ChangeNotificationReadStatusAdmin";
    //    -------------------------------EditNotification-----------------------------------
    public static final String url_GetNotificationsByAdminMetaData = IP_8086 + "CreateNotificationTemp/GetNotificationsByAdminMetaData";
    public static final String url_GetReadStatusOfNotificationsByAdmin = IP_8086 + "CreateNotificationTemp/GetReadStatusOfNotificationsByAdmin";
    public static final String url_GetNotificationsSentByAdmin = IP_8086 + "CreateNotificationTemp/GetNotificationsSentByAdmin";
    public static final String url_DeleteNotification = IP_8086 + "CreateNotificationTemp/DeleteNotification";
    //    -------------------------------Editplacements -----------------------------------
    public static final String url_GetPlacementSentByAdminByAdminMetaData = IP_8086 + "CreateNotificationTemp/GetPlacementsByAdminMetaData";
    public static final String url_GetReadStatusOfPlacementsByAdmin = IP_8086 + "CreateNotificationTemp/GetReadStatusOfPlacementsByAdmin";
    public static final String url_GetPlacementSentByAdmin = IP_8086 + "CreateNotificationTemp/GetPlacementSentByAdmin";
    public static final String url_Delete_Placements = IP_8086 + "CreateNotificationTemp/DeletePlacement";
    //    -------------------------------hractivity -----------------------------------
    public static final String url_GetPlacementsCreatedByHr = IP + "CreateNotificationTemp/GetPlacementsCreatedByHr";
    //    -------------------------------EditPlacementHr -----------------------------------
    public static final String url_GetPlacementSentByHr = IP + "CreateNotificationTemp/GetPlacementSentByHr";
    //    -------------------------------EditPlacementMainHr -----------------------------------
    public static final String url_ModifyPlacementHr = IP + "CreateNotificationTemp/ModifyPlacementHr";
    //    -------------------------------CreateNotification -----------------------------------
    public static final String url_UploadAttach1 = IP + "CreateNotificationTemp/UploadAttach1";
    public static final String url_ModifyNotification = IP + "CreateNotificationTemp/ModifyNotification";
    public static final String url_GetForWhomeNotification = IP + "CreateNotificationTemp/GetForWhomeNotification";    //files variables
    public static final String url_SavefileOnServer = IP + "CreateNotificationTemp/SavefileOnServer";


    //    -------------------------------CreatePlacement -----------------------------------
    public static final String url_CreatePlacements = IP + "CreateNotificationTemp/CreatePlacements";
    //    -------------------------------CreatePlacementHr -----------------------------------
    public static final String url_CreatePlacementsHr = IP + "CreateNotificationTemp/CreatePlacementsHr";
    //    -------------------------------CreateNotificationHR -----------------------------------
    public static final String url_CreateNotificationHrToEach = IP + "CreateNotificationTemp/CreateNotificationHrToEach";
    //    -------------------------------EditPlacementMain -----------------------------------
    public static final String url_GetForWhomePlacements = IP + "CreateNotificationTemp/GetForWhomePlacements";
    public static final String url_ModifyPlacement = IP + "CreateNotificationTemp/ModifyPlacement";
    //    -------------------------------ViewPlacement -----------------------------------
    public static final String url_RegisterForPlacement = IP + "AESTest/RegisterForPlacement";
    public static final String url_GetStudentMarksInfo = IP + "AESTest/GetStudentMarksInfo";
    public static final String url_SaveResume = IP + "GenerateResumeWithJODConverter3/SaveResume";
    //    -------------------------------EditPlacementMain -----------------------------------
    public static final String url_SaveShortListedUsers = IP + "CreateNotificationTemp/SaveShortListedUsers";
    public static final String url_SavePlacedUsers = IP + "CreateNotificationTemp/SavePlacedUsers";
    public static final String url_SaveRegistereduserStatus = IP + "CreateNotificationTemp/SaveRegistereduserStatus";
    public static final int USER_DATA_CHANGE_RESULT_CODE = 888;
    public static final String USERNAME_KEY = "nameKey";
    public static final String PASSWORD_KEY = "passKey";
    public static final String users_under_your_supervision = " Users under your supervision. Click to view the list.";
    public static final String url_create_firebase = IP + "Firbase/RegisterFirebaseUser";
    public static final String url_UpdateFirebaseToken = IP + "PlaceMe/UpdateFirebaseToken";
    //    ----------------------------------/sunny---------------------------------------------------------------

    public static final String url_ChangeUsernameFireBase = IP + "Firebase/ChangeUsername";
    public static final String url_get_chatrooms = Local_IP_Raju + "Firebase/GetChatRooms";
    public static final String url_getmessagesreadstatus = Local_IP_Raju + "Firebase/GetReadStatusOfMessages";
    public static final String url_changepass = IP + "Firebase/ChangePass";

    //#############################   Firebase   ######################################
    public static final String load_news = IP_1234 + "Firebase/GetNews";
    public static final String load_videos = IP_1234 + "Firebase/GetVideos";
    public static final String url_savechat = Local_IP_Raju + "Firebase/SaveChat";
    public static final String url_loadchat = Local_IP_Raju + "Firebase/LoadChat";
    public static final String url_SendPushNotification = Local_IP_Raju + "Firebase/SendPushNotification";
    public static final String url_markread = Local_IP_Raju + "Firebase/MarkRead";
    public static final String url_GetLastPushedMessage = Local_IP_Raju + "Firebase/GetLastPushedMessage";
    public static final String url_ChangeMessageReadStatus = Local_IP_Raju + "Firebase/ChangeMessageReadStatus";

    // Messages -----------------------------------------
    public static final String url_Welcome = IP + "AESTest/Welcome";
    public static final String url_login = IP + "AESTest/Auth";
    public static final String url_CheckInternet = IP + "AESTest/CheckInternet";
    public static final String url_getdigest = IP + "AESTest/GetDigest";
    public static final String url_getsession = IP + "AESTest/GetSessionDetails";
    public static final String load_resume_ids = IP + "AESTest/GetResumeIds";
    public static final String url_editemail = IP_8086 + "AESTest/EditEmail";

    //**************************************   AESTest   *****************************************
    public static final String url_VerifyOTPEditEmail = IP_8086 + "AESTest/VerifyOTPEditEmail";
    public static final String url_CreatePass = IP + "AESTest/CreatePass";
    public static final String url_ForgotPassword = IP + "AESTest/ForgotPassword";
    public static final String url_save_bug = IP + "ProfileObjects/Save_Bug";
    public static final String url_resendotp = IP + "AESTest/ResendOTP";
    public static final String url_verifyotp = IP + "AESTest/VerifyOTP";
    public static final String url_GetMyResumeIds = IP + "AESTest/GetMyResumeIds";
    public static final String url_savepreferences = IP + "AESTest/SavePreferences";
    public static final String url_report = IP + "AESTest/ReportBug";
    public static final String download_resume_template = IP + "AESTest/DownloadResumeTemplate";
    public static final String url_savesessiondetails = IP + "AESTest/SaveSessionDetails";
    public static final String load_resume_pages = IP + "AESTest/GetAvailableResumePages";
    public static final String load_resume_page = IP + "AESTest/GetResumePage";
    public static final String load_HR_data = IP + "ProfileObjects/GetHrData";
    public static final String url_load_alumni_data = IP + "ProfileObjects/GetAlumniData";
    public static final String load_student_data = IP + "ProfileObjects/GetStudentData";
    public static final String load_Admin_data = IP + "ProfileObjects/GetAdminData";
    public static final String url_SaveIntro = IP + "ProfileObjects/SaveIntro";
    public static final String url_SaveHrIntro = IP + "ProfileObjects/SaveHrIntro";


//    -----------------------------------ProfileObjects---------------------------------------------------------------------

//    --------------------------------------Load fragment data-------------------------------------
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
    public static final String url_getugcourses = IP + "AESTest/GetUGCourses";

//  --------------------------------------------Tabfragment---------------------------------
    public static final String url_getstreams = IP + "AESTest/GetUGStreams";
    public static final String url_getuniversities = IP + "AESTest/GetUGUniversities";

//    ------------------------------------------company details---------------------------------
    public static final String url_getpgcourses = IP + "AESTest/GetPGCourses";

//    ------------------------------------------Institute details---------------------------------
    public static final String url_getpgstreams = IP + "AESTest/GetPGStreams";

//    ------------------------------------------Education details---------------------------------
    public static final String url_getpguniversities = IP + "AESTest/GetPGUniversities";
    public static final String load_last_updated = IP + "AESTest/GetLastUpdated";
    public static final String url_remove_profile = IP + "AESTest/RemoveImage";
    public static final String url_saveHrExperience = IP + "AESTest/SaveHrExperiences";
    public static final String url = IP + "HandleMobileRequests/getimg.jsp?username=";
    public static final String load_student_image = IP + "AESTest/GetImage";
    public static final String upload_profile = IP + "AESTest/UploadProfile";
    public static final String remove_profile = IP + "AESTest/RemoveImage";
    public static final String url_createSingleUser_admin = IP + "AESTest/CreateSingleUser";
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
    //    public static final String url = IP+"HandleMobileRequests/getimg.jsp?username=";
    public static final String url_SendActivationCode = IP + "AESTest/SendActivationCode";
    public static final String url_SaveAndGenrateInstituteCode = IP + "AESTest/SaveAndGenrateInstituteCode";
    public static final String url_SaveAndGenrateCompanyCode = IP + "AESTest/SaveAndGenrateCompanyCode";
    public static final String url_checkUcode = IP + "AESTest/checkUcode";
    public static final String url_ClearOTP = IP + "AESTest/ClearOTP";
    public static final String url_AddStudentUnderAdmin = IP + "AESTest/AddStudentUnderAdmin";
    public static final String url_GetCountOfUsersUnderAdmin = IP + "AESTest/GetCountOfUsersUnderAdmin";
    private static final String IP_100 = "http://192.168.100.100/";
    private static final String IP_10 = "http://192.168.100.10/";
    private static final String IP_20 = "http://192.168.100.20/";
    private static final String IP_30 = "http://192.168.100.30/";
    private static Typeface FA = null, Bold = null, Light = null, Italic = null, BoldItalic = null, Righteous = null;
    private static Animation fadeAnimation = null, fadeandmovedownAnimation = null, slideoutleft2Animation = null, fadeoutAnimation = null, slideinleft1Animation = null, slideinleft2Animation = null, scaledownAnimation = null, bottomupbox1Animation = null, bottomupbox2Animation = null, bottomupbox3Animation = null, bottomupbox4Animation = null;
    public static String digest1=null,digest2=null;
//    --------------------------------- function --------------------------------------------------------------

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
            fadeAnimation =AnimationUtils.loadAnimation(activity,R.anim.fadein);
            view.startAnimation(fadeAnimation);
        }

    }

    public static void fadeandmovedown(Activity activity, View view) {

        if (fadeandmovedownAnimation != null)
            view.startAnimation(fadeandmovedownAnimation);
        else {
            fadeandmovedownAnimation =AnimationUtils.loadAnimation(activity,R.anim.fadeinmove);
            view.startAnimation(fadeandmovedownAnimation);
        }
    }

    public static void slideoutleft2(Activity activity, View view) {

        if (slideoutleft2Animation != null)
            view.startAnimation(slideoutleft2Animation);
        else {
            slideoutleft2Animation =AnimationUtils.loadAnimation(activity,R.anim.slideoutleft2);
            view.startAnimation(slideoutleft2Animation);
        }

    }

    public static void fadeout(Activity activity, View view) {

        if (fadeoutAnimation != null)
            view.startAnimation(fadeoutAnimation);
        else {
            fadeoutAnimation =AnimationUtils.loadAnimation(activity,R.anim.fadeout);
            view.startAnimation(fadeoutAnimation);
        }

    }

    public static void slideinleft1(Activity activity, View view) {

        if (slideinleft1Animation != null)
            view.startAnimation(slideinleft1Animation);
        else {
            slideinleft1Animation =AnimationUtils.loadAnimation(activity,R.anim.slideinleft1);
            view.startAnimation(slideinleft1Animation);
        }

    }

    public static void slideinleft2(Activity activity, View view) {

        if (slideinleft2Animation != null)
            view.startAnimation(slideinleft2Animation);
        else {
            slideinleft2Animation =AnimationUtils.loadAnimation(activity,R.anim.slideinleft2);
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
            scaledownAnimation =AnimationUtils.loadAnimation(activity,R.anim.scaledown);
            view.startAnimation(scaledownAnimation);
        }

    }

    public static void bottomupbox1(Activity activity, View view) {

        if (bottomupbox1Animation != null)
            view.startAnimation(bottomupbox1Animation);
        else {
            bottomupbox1Animation =AnimationUtils.loadAnimation(activity,R.anim.bottom_up_box1);
            view.startAnimation(bottomupbox1Animation);
        }

    }

    public static void bottomupbox2(Activity activity, View view) {

        if (bottomupbox2Animation != null)
            view.startAnimation(bottomupbox2Animation);
        else {
            bottomupbox2Animation =AnimationUtils.loadAnimation(activity,R.anim.bottom_up_box2);
            view.startAnimation(bottomupbox2Animation);
        }

    }

    public static void bottomupbox3(Activity activity, View view) {

        if (bottomupbox3Animation != null)
            view.startAnimation(bottomupbox3Animation);
        else {
            bottomupbox3Animation =AnimationUtils.loadAnimation(activity,R.anim.bottom_up_box3);
            view.startAnimation(bottomupbox3Animation);
        }

    }

    public static void bottomupbox4(Activity activity, View view) {
        if (bottomupbox4Animation != null)
            view.startAnimation(bottomupbox4Animation);
        else {
            bottomupbox4Animation =AnimationUtils.loadAnimation(activity,R.anim.bottom_up_box4);
            view.startAnimation(bottomupbox4Animation);
        }

    }
    public static String getDigest1(Context context)
    {
        if(digest1!=null)
            return digest1;
        else
        {
            digest1=MySharedPreferencesManager.getDigest1(context);
        }
        return digest1;
    }
    public static String getDigest2(Context context)
    {
        if(digest2!=null)
            return digest2;
        else
        {
            digest2=MySharedPreferencesManager.getDigest2(context);
        }
        return digest2;
    }
    public static String Encrypt(String string,Context context) throws Exception
    {
        byte[] demoKeyBytes=SimpleBase64Encoder.decode(getDigest1(context));
        byte[] demoIVBytes=SimpleBase64Encoder.decode(getDigest2(context));
        String sPadding = "ISO10126Padding";

        byte[] objBytes = string.getBytes("UTF-8");
        byte[] objEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, objBytes);

        return new String(SimpleBase64Encoder.encode(objEncryptedBytes));
    }
    public static String Decrypt(String string,Context context) throws Exception
    {
        byte[] demoKeyBytes=SimpleBase64Encoder.decode(getDigest1(context));
        byte[] demoIVBytes=SimpleBase64Encoder.decode(getDigest2(context));
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

}
