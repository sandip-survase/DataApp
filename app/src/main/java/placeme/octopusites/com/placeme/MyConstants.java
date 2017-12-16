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

    public static final int USER_DATA_CHANGE_RESULT_CODE=888;
    public static final String USERNAME_KEY = "nameKey";
    public static final String PASSWORD_KEY = "passKey";
    public static final String users_under_your_supervision = " Users under your supervision. Click to view the list.";

    //-----------------------------------   PlaceMe   ---------------------------------

    public static String url_get_chatrooms = "http://192.168.100.100/PlaceMe/GetChatRooms";
    public static String url_getmessagesreadstatus= "http://192.168.100.100/PlaceMe/GetReadStatusOfMessages";
    public static String url_changepass = "http://192.168.100.100/PlaceMe/ChangePass";
    public static final String url_create_firebase = "http://192.168.100.100/PlaceMe/RegisterFirebaseUser";
    public static final String url_UpdateFirebaseToken = "http://192.168.100.100/PlaceMe/UpdateFirebaseToken";
    public static final String url_ChangeUsernameFireBase = "http://192.168.100.100/PlaceMe/ChangeUsername";

    //-----------------------------------   AESTest   ---------------------------------

    public static final String url_savesessiondetails = "http://104.237.4.236/AESTest/SaveSessionDetails";
    public static final String url_Welcome = "http://104.237.4.236/AESTest/Welcome";
    public static final String url_login = "http://104.237.4.236/AESTest/Auth";           // changed to AEStest.auth
    public static final String url_getdigest = "http://104.237.4.236/AESTest/GetDigest";

//    -----------------------------------ProfileObjects---------------------------------------------------------------------


//    --------------------------------------Load fragment data-------------------------------------

    public static final String load_HR_data = "http://104.237.4.236/ProfileObjects/GetHrData";
    public static String url_load_alumni_data = "http://104.237.4.236/ProfileObjects/GetAlumniData";
    public static final String load_student_data = "http://104.237.4.236/ProfileObjects/GetStudentData";
    public static final String load_Admin_data = "http://104.237.4.236/ProfileObjects/GetAdminData";

//    --------------------------------------Intro-------------------------------------

    public static final String url_SaveIntro = "http://104.237.4.236/ProfileObjects/SaveIntro";
    public static final String url_SaveHrIntro = "http://104.237.4.236/ProfileObjects/SaveHrIntro";
    public static String url_SaveAdminIntro= "http://104.237.4.236/ProfileObjects/SaveAdminIntro";

//    --------------------------------------project-------------------------------------

    public static final String url_saveprojects = "http://104.237.4.236/ProfileObjects/SaveProjects";

//    -----------------------------------Education details--------------------------

    public static final String url_SaveTenth = "http://104.237.4.236/ProfileObjects/SaveTenth";
    public static final String url_savedata_twelth = "http://104.237.4.236/ProfileObjects/SaveTwelth";
    public static final String url_savedata_diploma = "http://104.237.4.236/ProfileObjects/SaveDiploma";
    public static String url_savedata_ug = "http://104.237.4.236/ProfileObjects/SaveUg";
    public static String url_savedata_pg_sem = "http://104.237.4.236/ProfileObjects/SavePgSem";
    public static String url_savedata_pg_year = "http://104.237.4.236/ProfileObjects/SavePgYear";

//    ----------------------------------------Accomplishments-----------------------------

    public static final String url_savelanguages = "http://104.237.4.236/ProfileObjects/SaveLanguages";
    public static final String url_savecertifications = "http://104.237.4.236/ProfileObjects/SaveCertificates";
    public static final String url_savecourses = "http://104.237.4.236/ProfileObjects/SaveCourses";
    public static final String url_saveskills = "http://104.237.4.236/ProfileObjects/SaveSkills";
    public static final String url_savehonors = "http://104.237.4.236/ProfileObjects/SaveHonors";
    public static final String url_savepatents = "http://104.237.4.236/ProfileObjects/SavePatents";
    public static final String url_savepublications = "http://104.237.4.236/ProfileObjects/SavePublications";

//    -----------------------------------------MyProfileCareerDetails---------------------------------

    public static final String url_savecareerobj = "http://104.237.4.236/ProfileObjects/SaveCareerObj";
    public static final String url_savestrengths = "http://104.237.4.236/ProfileObjects/SaveStrengths";
    public static final String url_saveweaknesses = "http://104.237.4.236/ProfileObjects/SaveWeaknesses";
    public static final String url_savelocationpreferences = "http://104.237.4.236/ProfileObjects/SaveLocationPreferences";

//    --------------------------------------------MyProfileContact---------------------------------

    public static final String url_SaveStdalmContact= "http://104.237.4.236/ProfileObjects/SaveStdAlmContact";
    public static final String url_SaveAdminContact = "http://104.237.4.236/ProfileObjects/SaveAdminContact";
    public static final String url_SaveHrContact= "http://104.237.4.236/ProfileObjects/SaveHrContact";

//   --------------------------------------------Experience---------------------------------

    public static final String url_SaveExperiences= "http://104.237.4.236/ProfileObjects/SaveExperiences";

//  --------------------------------------------Tabfragment---------------------------------

    public static String savepersonalinfo = "http://104.237.4.236/ProfileObjects/SavePersonalInfo";
    public static final String url_SaveAdminPersonal = "http://104.237.4.236/ProfileObjects/SaveAdminPersonal";

//    ------------------------------------------company details---------------------------------

    public static final String url_SaveHrCompany = "http://104.237.4.236/ProfileObjects/SaveHrCompany";

//    ------------------------------------------Institute details---------------------------------

    public static final String url_SaveAdminInstituteData= "http://104.237.4.236/ProfileObjects/SaveAdminInstituteData";

//    ------------------------------------------Education details---------------------------------

    public static String url_getcourses = "http://104.237.4.236/AESTest/GetDiplomaCourses";
    public static String url_getugcourses = "http://104.237.4.236/AESTest/GetUGCourses";
    public static String url_getstreams = "http://104.237.4.236/AESTest/GetUGStreams";
    public static String url_getuniversities = "http://104.237.4.236/AESTest/GetUGUniversities";
    public static String url_getpgcourses = "http://104.237.4.236/AESTest/GetPGCourses";
    public static String url_getpgstreams = "http://104.237.4.236/AESTest/GetPGStreams";
    public static String url_getpguniversities = "http://104.237.4.236/AESTest/GetPGUniversities";

    public static final String load_last_updated = "http://104.237.4.236/AESTest/GetLastUpdated";
    public static final String url_remove_profile = "http://104.237.4.236/AESTest/RemoveImage";


    public static final String url_saveHrExperience = "http://104.237.4.236/AESTest/SaveHrExperiences";
    public static final String url = "http://104.237.4.236/HandleMobileRequests/getimg.jsp?username=";
    public static final String load_student_image = "http://104.237.4.236/AESTest/GetImage";
    public static final String upload_profile = "http://104.237.4.236/AESTest/UploadProfile";
    public static final String remove_profile = "http://104.237.4.236/AESTest/RemoveImage";

    public static final String url_createSingleUser_admin = "http://104.237.4.236/AESTest/CreateSingleUser";
    public static final String url_createMultipleUser_admin = "http://104.237.4.236/AESTest/CreateMultipleUser";
    public static final String url_delete_file = "http://104.237.4.236/AESTest/DeleteFile";
    public static final String url_uploadSingleFile = "http://104.237.4.236/AESTest/UploadSingleFile";
    public static final String url_GetRegisteredUsersUnderAdmin = "http://104.237.4.236/AESTest/GetRegisteredUsersUnderAdmin";
    public static final String url_getlastupdated = "http://104.237.4.236/AESTest/GetLastUpdated";
    public static final String url_SavePlacedDebarInfo = "http://104.237.4.236/AESTest/SavePlacedDebarInfo";
    public static final String url_GetPlacedDebarInfo = "http://104.237.4.236/AESTest/GetPlacedDebarInfo";
    public static final String url_DeleteNonActiveUser = "http://104.237.4.236/AESTest/DeleteNonActiveUser";
    public static final String url_SaveWelcomeIntroData = "http://104.237.4.236" +
            "/AESTest/SaveNewUserWelcomeIntroData";
    public static final String url_SaveStudentFnameLnameMobile = "http://104.237.4.236/AESTest/SaveStudentFnameLnameMobile";

    //    private static String url = "http://104.237.4.236/HandleMobileRequests/getimg.jsp?username=";
    public static final String url_SendActivationCode = "http://104.237.4.236/AESTest/SendActivationCode";
    public static final String url_SaveAndGenrateInstituteCode = "http://104.237.4.236/AESTest/SaveAndGenrateInstituteCode";
    public static final String url_SaveAndGenrateCompanyCode = "http://104.237.4.236/AESTest/SaveAndGenrateCompanyCode";


    public static final String url_checkUcode = "http://104.237.4.236/AESTest/checkUcode";
    public static final String url_ClearOTP = "http://104.237.4.236/AESTest/ClearOTP";
    public static final String url_AddStudentUnderAdmin = "http://104.237.4.236/AESTest/AddStudentUnderAdmin";
    public static final String url_GetCountOfUsersUnderAdmin = "http://104.237.4.236/AESTest/GetCountOfUsersUnderAdmin";

//    --------------------------------- function --------------------------------------------------------------

    public static Typeface getFA(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),  "fonts/fa.ttf");
    }
    public static Typeface getBold(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),  "fonts/nunitobold.ttf");
    }
    public static Typeface getLight(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),  "fonts/nunitolight.ttf");
    }
    public static Typeface getItalic(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),  "fonts/nunitoitalic.ttf");
    }
    public static Typeface getBoldItalic(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),  "fonts/nunitobolditalic.ttf");
    }
    public static Typeface getRighteous(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),  "fonts/righteous.ttf");
    }
    public static void fade(Activity activity, View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.fadein);
        view.startAnimation(animation1);
    }
    public static void fadeandmovedown(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.fadeinmove);
        view.startAnimation(animation1);
    }
    public static void slideoutleft2(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.slideoutleft2);
        view.startAnimation(animation1);


    }
    public static void fadeout(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.fadeout);
        view.startAnimation(animation1);


    }
    public static void slideinleft1(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.slideinleft1);
        view.startAnimation(animation1);
    }
    public static void slideinleft2(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.slideinleft2);
        view.startAnimation(animation1);
    }
    public static void scale1(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.scaleup1);
        view.startAnimation(animation1);
    }
    public static void scale2(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.scaleup2);
        view.startAnimation(animation1);
    }
    public static void scale3(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.scaleup3);
        view.startAnimation(animation1);
    }
    public static void scale4(Activity activity,View view){
        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.scaleup4);
        view.startAnimation(animation1);
    }
    public static void scaledown(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.scaledown);
        view.startAnimation(animation1);

    }
    public static void bottomupbox1(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.bottom_up_box1);
        view.startAnimation(animation1);

    }
    public static void bottomupbox2(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.bottom_up_box2);
        view.startAnimation(animation1);

    }
    public static void bottomupbox3(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.bottom_up_box3);
        view.startAnimation(animation1);

    }
    public static void bottomupbox4(Activity activity,View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(activity,
                        R.anim.bottom_up_box4);
        view.startAnimation(animation1);

    }





}
