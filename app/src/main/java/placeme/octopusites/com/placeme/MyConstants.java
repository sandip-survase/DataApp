package placeme.octopusites.com.placeme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by admin on 9/27/2017.
 */

public class MyConstants {
    public static final String VPS_IP = "104.237.4.236";   // for authority
    public static final String IP = "http://104.237.4.236/";

    private static final String IP_100 = "http://192.168.100.100/";
    private static final String IP_10 = "http://192.168.100.10/";
    private static final String IP_20 = "http://192.168.100.20/";
    private static final String IP_30 = "http://192.168.100.30/";


    //    ----------------------------------sunny---------------------------------------------------------------
//                    --------------------MainActivity(student)-----------------
    public static String url_getnotificationsmetadata = IP + "CreateNotificationTemp/GetNotificationsMetaData";
    public static String url_getnotificationsreadstatus = IP + "CreateNotificationTemp/GetReadStatusOfNotifications";
    public static String url_getnotifications = IP + "CreateNotificationTemp/GetNotifications";
    public static String url_changenotificationsreadstatus = IP + "CreateNotificationTemp/ChangeNotificationReadStatus";
    public static String url_getplacementsmetadata = IP + "CreateNotificationTemp/GetPlacementsMetaData";
    public static String url_getplacementsreadstatus = IP + "CreateNotificationTemp/GetReadStatusOfPlacements";
    public static String url_changeplacementsreadstatus = IP + "CreateNotificationTemp/ChangePlacementReadStatus";
    public static String url_getplacements = IP + "CreateNotificationTemp/GetPlacements";
    //                           --------------------alumniActivity(alumni)-----------------
    public static String url_GetNotificationsAlumniAlumniMetaData = IP + "CreateNotificationTemp/GetNotificationsAlumniMetaData";
    public static String url_GetReadStatusOfNotificationsAlumni = IP + "CreateNotificationTemp/GetReadStatusOfNotificationsAlumni";
    public static String url_GetNotificationsAlumni = IP + "CreateNotificationTemp/GetNotificationsAlumni";
    public static String url_ChangeNotificationReadStatus = IP + "CreateNotificationTemp/ChangeNotificationReadStatus";
    public static String url_GetPlacementsAlumniAlumniMetaData = IP + "CreateNotificationTemp/GetPlacementsAlumniMetaData";
    public static String url_GetReadStatusOfPlacementsAlumni = IP + "CreateNotificationTemp/GetReadStatusOfPlacementsAlumni";
    public static String url_GetPlacementsAlumni = IP + "CreateNotificationTemp/GetPlacementsAlumni";
    public static String url_ChangePlacementReadStatus = IP + "CreateNotificationTemp/ChangePlacementReadStatus";
    //                                --------------------adminActivity)-----------------
//placement urls
    public static String url_GetPlacementsAdminAdminMetaData = IP + "CreateNotificationTemp/GetPlacementsAdminMetaData";
    public static String url_GetReadStatusOfPlacementsForAdmin = IP + "CreateNotificationTemp/GetReadStatusOfPlacementsForAdmin";
    public static String url_GetPlacementsAdmin = IP + "CreateNotificationTemp/GetPlacementsAdmin";
    public static String url_ChangePlacementReadStatusAdmin = IP + "CreateNotificationTemp/ChangePlacementReadStatusAdmin";
    //notiffurl
    public static String url_GetNotificationsAdminAdminMetaData = IP + "CreateNotificationTemp/GetNotificationsAdminMetaData";
    public static String url_GetReadStatusOfNotificationsAdmin = IP + "CreateNotificationTemp/GetReadStatusOfNotificationsAdmin";
    public static String url_GetNotificationsAdmin = IP + "CreateNotificationTemp/GetNotificationsAdmin";
    public static String url_ChangeNotificationReadStatusAdmin = IP + "CreateNotificationTemp/ChangeNotificationReadStatusAdmin";
    //    -------------------------------EditNotification-----------------------------------
    public static String url_GetNotificationsByAdminMetaData = IP + "CreateNotificationTemp/GetNotificationsByAdminMetaData";
    public static String url_GetReadStatusOfNotificationsByAdmin = IP + "CreateNotificationTemp/GetReadStatusOfNotificationsByAdmin";
    public static String url_GetNotificationsSentByAdmin = IP + "CreateNotificationTemp/GetNotificationsSentByAdmin";
    public static String url_DeleteNotification = IP + "CreateNotificationTemp/DeleteNotification";
    //    -------------------------------Editplacements -----------------------------------
    public static String url_GetPlacementSentByAdminByAdminMetaData = IP + "CreateNotificationTemp/GetPlacementsByAdminMetaData";
    public static String url_GetReadStatusOfPlacementsByAdmin = IP + "CreateNotificationTemp/GetReadStatusOfPlacementsByAdmin";
    public static String url_GetPlacementSentByAdmin = IP + "CreateNotificationTemp/GetPlacementSentByAdmin";
    public static String url_Delete_Placements = IP + "CreateNotificationTemp/DeletePlacement";
    //    -------------------------------hractivity -----------------------------------
    public static String url_GetPlacementsCreatedByHr = IP + "CreateNotificationTemp/GetPlacementsCreatedByHr";
    //    -------------------------------EditPlacementHr -----------------------------------
    public static String url_GetPlacementSentByHr = IP + "CreateNotificationTemp/GetPlacementSentByHr";
    //    -------------------------------EditPlacementMainHr -----------------------------------
    public static String url_ModifyPlacementHr = IP + "CreateNotificationTemp/ModifyPlacementHr";
    //    -------------------------------CreateNotification -----------------------------------
    public static String url_UploadAttach1 = IP + "CreateNotificationTemp/UploadAttach1";
    public static String url_ModifyNotification = IP + "CreateNotificationTemp/ModifyNotification";
    public static String url_GetForWhomeNotification = IP + "CreateNotificationTemp/GetForWhomeNotification";    //files variables
    public static String url_SavefileOnServer = IP + "CreateNotificationTemp/SavefileOnServer";
    //    -------------------------------CreatePlacement -----------------------------------
    public static String url_CreatePlacements = IP + "CreateNotificationTemp/CreatePlacements";
    //    -------------------------------CreatePlacementHr -----------------------------------
    public static String url_CreatePlacementsHr = IP + "CreateNotificationTemp/CreatePlacementsHr";
    //    -------------------------------CreateNotificationHR -----------------------------------
    public static String url_CreateNotificationHrToEach = IP + "CreateNotificationTemp/CreateNotificationHrToEach";
    //    -------------------------------EditPlacementMain -----------------------------------
    public static String url_GetForWhomePlacements = IP + "CreateNotificationTemp/GetForWhomePlacements";
    public static String url_ModifyPlacement = IP + "CreateNotificationTemp/ModifyPlacement";
    //    -------------------------------ViewPlacement -----------------------------------
    public static String url_RegisterForPlacement = IP + "AESTest/RegisterForPlacement";
    public static String url_GetStudentMarksInfo = IP + "AESTest/GetStudentMarksInfo";
    public static String url_SaveResume = IP + "GenerateResumeWithJODConverter3/SaveResume";
    //    -------------------------------EditPlacementMain -----------------------------------
    public static String url_SaveShortListedUsers = IP + "CreateNotificationTemp/SaveShortListedUsers";
    public static String url_SavePlacedUsers = IP + "CreateNotificationTemp/SavePlacedUsers";
    public static String url_SaveRegistereduserStatus = IP + "CreateNotificationTemp/SaveRegistereduserStatus";
//    ----------------------------------/sunny---------------------------------------------------------------


    public static final int USER_DATA_CHANGE_RESULT_CODE = 888;
    public static final String USERNAME_KEY = "nameKey";
    public static final String PASSWORD_KEY = "passKey";
    public static final String users_under_your_supervision = " Users under your supervision. Click to view the list.";

    //#############################   PlaceMe   ######################################

    public static final String url_create_firebase = IP + "PlaceMe/RegisterFirebaseUser";
    public static final String url_UpdateFirebaseToken = IP + "PlaceMe/UpdateFirebaseToken";
    public static final String url_ChangeUsernameFireBase = IP + "PlaceMe/ChangeUsername";
    public static String url_get_chatrooms = IP + "PlaceMe/GetChatRooms";
    public static String url_getmessagesreadstatus = IP + "PlaceMe/GetReadStatusOfMessages";
    public static String url_changepass = IP + "PlaceMe/ChangePass";
    public static String load_news = IP + "PlaceMe/GetNews";
    public static String load_videos = IP + "PlaceMe/GetVideos";

    // Messages -----------------------------------------

    public static final String url_savechat = IP + "PlaceMe/SaveChat";
    public static final String url_loadchat = IP + "PlaceMe/LoadChat";
    public static final String url_SendPushNotification = IP + "PlaceMe/SendPushNotification";
    public static final String url_markread = IP + "PlaceMe/MarkRead";
    public static final String url_GetLastPushedMessage = IP + "PlaceMe/GetLastPushedMessage";
    public static final String url_ChangeMessageReadStatus = IP + "PlaceMe/ChangeMessageReadStatus";

    //**************************************   AESTest   *****************************************

    public static final String url_Welcome = IP + "AESTest/Welcome";
    public static final String url_login = IP + "AESTest/Auth";
    public static final String url_getdigest = IP + "AESTest/GetDigest";

    public static String url_getsession = IP + "AESTest/GetSessionDetails";
    public static String load_resume_ids = IP + "AESTest/GetResumeIds";
    public static String url_editemail = IP + "AESTest/EditEmail";
    public static String url_VerifyOTPEditEmail = IP + "AESTest/VerifyOTPEditEmail";
    public static final String url_CreatePass = IP + "AESTest/CreatePass";

    public static String url_save_bug = IP + "ProfileObjects/Save_Bug";
    public static String url_resendotp = IP + "AESTest/ResendOTP";
    public static String url_verifyotp = IP + "AESTest/VerifyOTP";
    public static String url_GetMyResumeIds = IP + "AESTest/GetMyResumeIds";
    public static String url_savepreferences = IP + "AESTest/SavePreferences";
    public static String url_report = IP + "AESTest/ReportBug";
    public static String download_resume_template = IP + "AESTest/DownloadResumeTemplate";
    public static String url_savesessiondetails = IP + "AESTest/SaveSessionDetails";
    public static String load_resume_pages = IP + "AESTest/GetAvailableResumePages";
    public static String load_resume_page = IP + "AESTest/GetResumePage";


//    -----------------------------------ProfileObjects---------------------------------------------------------------------

//    --------------------------------------Load fragment data-------------------------------------

    public static final String load_HR_data = IP + "ProfileObjects/GetHrData";
    public static String url_load_alumni_data = IP + "ProfileObjects/GetAlumniData";
    public static final String load_student_data = IP + "ProfileObjects/GetStudentData";
    public static final String load_Admin_data = IP + "ProfileObjects/GetAdminData";

//    --------------------------------------Intro-------------------------------------

    public static final String url_SaveIntro = IP + "ProfileObjects/SaveIntro";
    public static final String url_SaveHrIntro = IP + "ProfileObjects/SaveHrIntro";
    public static String url_SaveAdminIntro = IP + "ProfileObjects/SaveAdminIntro";

//    --------------------------------------project-------------------------------------

    public static final String url_saveprojects = IP + "ProfileObjects/SaveProjects";

//    -----------------------------------Education details--------------------------

    public static final String url_SaveTenth = IP + "ProfileObjects/SaveTenth";
    public static final String url_savedata_twelth = IP + "ProfileObjects/SaveTwelth";
    public static final String url_savedata_diploma = IP + "ProfileObjects/SaveDiploma";
    public static String url_savedata_ug = IP + "ProfileObjects/SaveUg";
    public static String url_savedata_pg_sem = IP + "ProfileObjects/SavePgSem";
    public static String url_savedata_pg_year = IP + "ProfileObjects/SavePgYear";

//    ----------------------------------------Accomplishments-----------------------------

    public static final String url_savelanguages = IP + "ProfileObjects/SaveLanguages";
    public static final String url_savecertifications = IP + "ProfileObjects/SaveCertificates";
    public static final String url_savecourses = IP + "ProfileObjects/SaveCourses";
    public static final String url_saveskills = IP + "ProfileObjects/SaveSkills";
    public static final String url_savehonors = IP + "ProfileObjects/SaveHonors";
    public static final String url_savepatents = IP + "ProfileObjects/SavePatents";
    public static final String url_savepublications = IP + "ProfileObjects/SavePublications";

//    -----------------------------------------MyProfileCareerDetails---------------------------------

    public static final String url_savecareerobj = IP + "ProfileObjects/SaveCareerObj";
    public static final String url_savestrengths = IP + "ProfileObjects/SaveStrengths";
    public static final String url_saveweaknesses = IP + "ProfileObjects/SaveWeaknesses";
    public static final String url_savelocationpreferences = IP + "ProfileObjects/SaveLocationPreferences";

//    --------------------------------------------MyProfileContact---------------------------------

    public static final String url_SaveStdalmContact = IP + "ProfileObjects/SaveStdAlmContact";
    public static final String url_SaveAdminContact = IP + "ProfileObjects/SaveAdminContact";
    public static final String url_SaveHrContact = IP + "ProfileObjects/SaveHrContact";

//   --------------------------------------------Experience---------------------------------

    public static final String url_SaveExperiences = IP + "ProfileObjects/SaveExperiences";

//  --------------------------------------------Tabfragment---------------------------------

    public static String savepersonalinfo = IP + "ProfileObjects/SavePersonalInfo";
    public static final String url_SaveAdminPersonal = IP + "ProfileObjects/SaveAdminPersonal";

//    ------------------------------------------company details---------------------------------

    public static final String url_SaveHrCompany = IP + "ProfileObjects/SaveHrCompany";

//    ------------------------------------------Institute details---------------------------------

    public static final String url_SaveAdminInstituteData = IP + "ProfileObjects/SaveAdminInstituteData";

//    ------------------------------------------Education details---------------------------------

    public static String url_getcourses = IP + "AESTest/GetDiplomaCourses";
    public static String url_getugcourses = IP + "AESTest/GetUGCourses";
    public static String url_getstreams = IP + "AESTest/GetUGStreams";
    public static String url_getuniversities = IP + "AESTest/GetUGUniversities";
    public static String url_getpgcourses = IP + "AESTest/GetPGCourses";
    public static String url_getpgstreams = IP + "AESTest/GetPGStreams";
    public static String url_getpguniversities = IP + "AESTest/GetPGUniversities";

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

    //    public static String url = IP+"HandleMobileRequests/getimg.jsp?username=";
    public static final String url_SendActivationCode = IP + "AESTest/SendActivationCode";
    public static final String url_SaveAndGenrateInstituteCode = IP + "AESTest/SaveAndGenrateInstituteCode";
    public static final String url_SaveAndGenrateCompanyCode = IP + "AESTest/SaveAndGenrateCompanyCode";


    public static final String url_checkUcode = IP + "AESTest/checkUcode";
    public static final String url_ClearOTP = IP + "AESTest/ClearOTP";
    public static final String url_AddStudentUnderAdmin = IP + "AESTest/AddStudentUnderAdmin";
    public static final String url_GetCountOfUsersUnderAdmin = IP + "AESTest/GetCountOfUsersUnderAdmin";

//    --------------------------------- function --------------------------------------------------------------

    public static Typeface getFA(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/fa.ttf");
    }

    public static Typeface getBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/nunitobold.ttf");
    }

    public static Typeface getLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/nunitolight.ttf");
    }

    public static Typeface getItalic(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/nunitoitalic.ttf");
    }

    public static Typeface getBoldItalic(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/nunitobolditalic.ttf");
    }

    public static Typeface getRighteous(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/righteous.ttf");
    }

    public static void fade(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.fadein);
        view.startAnimation(animation1);
    }

    public static void fadeandmovedown(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.fadeinmove);
        view.startAnimation(animation1);
    }

    public static void slideoutleft2(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.slideoutleft2);
        view.startAnimation(animation1);


    }

    public static void fadeout(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.fadeout);
        view.startAnimation(animation1);


    }

    public static void slideinleft1(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.slideinleft1);
        view.startAnimation(animation1);
    }

    public static void slideinleft2(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.slideinleft2);
        view.startAnimation(animation1);
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

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.scaledown);
        view.startAnimation(animation1);

    }

    public static void bottomupbox1(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.bottom_up_box1);
        view.startAnimation(animation1);

    }

    public static void bottomupbox2(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.bottom_up_box2);
        view.startAnimation(animation1);

    }

    public static void bottomupbox3(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.bottom_up_box3);
        view.startAnimation(animation1);

    }

    public static void bottomupbox4(Activity activity, View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.bottom_up_box4);
        view.startAnimation(animation1);

    }


}
