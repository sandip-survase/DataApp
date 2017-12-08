package placeme.octopusites.com.placeme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by admin on 9/27/2017.
 */

public class MyConstants {

    public static final int USER_DATA_CHANGE_RESULT_CODE=888;
    public static final String USERNAME_KEY = "nameKey";
    public static final String PASSWORD_KEY = "passKey";

    //-----------------------------------   PlaceMe   ---------------------------------

    public static final String url_savesessiondetails = "http://192.168.100.100/PlaceMe/SaveSessionDetails";
    public static final String url_create_firebase = "http://192.168.100.100/PlaceMe/RegisterFirebaseUser";
    public static final String url_UpdateFirebaseToken = "http://192.168.100.100/PlaceMe/UpdateFirebaseToken";
    public static final String url_ChangeUsernameFireBase = "http://192.168.100.100/PlaceMe/ChangeUsername";

    //-----------------------------------   AESTest   ---------------------------------

    public static final String url_Welcome = "http://192.168.100.100/AESTest/Welcome";
    public static final String url_login = "http://192.168.100.100/AESTest/Auth";           // changed to AEStest.auth
//    public static final String url_savesessiondetails = "http://192.168.100.100/PlaceMe/SaveSessionDetails";

//    -----------------------------------ProfileObjects---------------------------------------------------------------------


//    --------------------------------------Load fragment data-------------------------------------

    public static final String load_HR_data = "http://192.168.100.10/ProfileObjects/GetHrData";
    public static String url_load_alumni_data = "http://192.168.100.10/ProfileObjects/GetAlumniData";
    public static final String load_student_data = "http://192.168.100.10/ProfileObjects/GetStudentData";
    public static final String load_Admin_data = "http://192.168.100.10/ProfileObjects/GetAdminData";

//    --------------------------------------Intro-------------------------------------

    public static final String url_SaveIntro = "http://192.168.100.10/ProfileObjects/SaveIntro";
    public static final String url_SaveHrIntro = "http://192.168.100.10/ProfileObjects/SaveHrIntro";
    public static String url_SaveAdminIntro= "http://192.168.100.10/ProfileObjects/SaveAdminIntro";

//    --------------------------------------project-------------------------------------

    public static final String url_saveprojects = "http://192.168.100.10/ProfileObjects/SaveProjects";

//    -----------------------------------Education details--------------------------

    public static final String url_SaveTenth = "http://192.168.100.10/ProfileObjects/SaveTenth";
    public static final String url_savedata_twelth = "http://192.168.100.10/ProfileObjects/SaveTwelth";
    public static final String url_savedata_diploma = "http://192.168.100.10/ProfileObjects/SaveDiploma";
    public static String url_savedata_ug = "http://192.168.100.10/ProfileObjects/SaveUg";
    public static String url_savedata_pg_sem = "http://192.168.100.10/ProfileObjects/SavePgSem";
    public static String url_savedata_pg_year = "http://192.168.100.10/ProfileObjects/SavePgYear";

//    ----------------------------------------Accomplishments-----------------------------

    public static final String url_savelanguages = "http://192.168.100.10/ProfileObjects/SaveLanguages";
    public static final String url_savecertifications = "http://192.168.100.10/ProfileObjects/SaveCertificates";
    public static final String url_savecourses = "http://192.168.100.10/ProfileObjects/SaveCourses";
    public static final String url_saveskills = "http://192.168.100.10/ProfileObjects/SaveSkills";
    public static final String url_savehonors = "http://192.168.100.10/ProfileObjects/SaveHonors";
    public static final String url_savepatents = "http://192.168.100.10/ProfileObjects/SavePatents";
    public static final String url_savepublications = "http://192.168.100.10/ProfileObjects/SavePublications";

//    -----------------------------------------MyProfileCareerDetails---------------------------------

    public static final String url_savecareerobj = "http://192.168.100.10/ProfileObjects/SaveCareerObj";
    public static final String url_savestrengths = "http://192.168.100.10/ProfileObjects/SaveStrengths";
    public static final String url_saveweaknesses = "http://192.168.100.10/ProfileObjects/SaveWeaknesses";
    public static final String url_savelocationpreferences = "http://192.168.100.10/ProfileObjects/SaveLocationPreferences";

//    --------------------------------------------MyProfileContact---------------------------------

    public static final String url_SaveStdalmContact= "http://192.168.100.10/ProfileObjects/SaveStdAlmContact";
    public static final String url_SaveAdminContact = "http://192.168.100.10/ProfileObjects/SaveAdminContact";
    public static final String url_SaveHrContact= "http://192.168.100.10/ProfileObjects/SaveHrContact";

//   --------------------------------------------Experience---------------------------------

    public static final String url_SaveExperiences= "http://192.168.100.10/ProfileObjects/SaveExperiences";

//  --------------------------------------------Tabfragment---------------------------------

    public static String savepersonalinfo = "http://192.168.100.10/ProfileObjects/SavePersonalInfo";
    public static final String url_SaveAdminPersonal = "http://192.168.100.10/ProfileObjects/SaveAdminPersonal";

//    ------------------------------------------company details---------------------------------

    public static final String url_SaveHrCompany = "http://192.168.100.10/ProfileObjects/SaveHrCompany";

//    ------------------------------------------Institute details---------------------------------

    public static final String url_SaveAdminInstituteData= "http://192.168.100.10/ProfileObjects/SaveAdminInstituteData";


    public static String url_getcourses = "http://192.168.100.100/AESTest/GetDiplomaCourses";
    public static String url_getugcourses = "http://192.168.100.100/AESTest/GetUGCourses";
    public static String url_getstreams = "http://192.168.100.100/AESTest/GetUGStreams";
    public static String url_getuniversities = "http://192.168.100.100/AESTest/GetUGUniversities";
    public static String url_getpgcourses = "http://192.168.100.100/AESTest/GetPGCourses";
    public static String url_getpgstreams = "http://192.168.100.100/AESTest/GetPGStreams";
    public static String url_getpguniversities = "http://192.168.100.100/AESTest/GetPGUniversities";

    public static final String load_last_updated = "http://192.168.100.100/AESTest/GetLastUpdated";
    public static final String url_remove_profile = "http://192.168.100.100/AESTest/RemoveImage";
    public static final String url_getlanguages = "http://192.168.100.100/AESTest/GetLanguages";
    public static final String URL_SAVE_ALUMNI_CONTACT_DETAILS = "http://192.168.100.100/AESTest/SaveAlumniContact";
    public static final String LOAD_ALUMNI_DATA = "http://192.168.100.100/AESTest/GetAlumniData";
    public static final String URL_SAVE_ALUMNI_PROJECTS = "http://192.168.100.100/AESTest/SaveAlumniProjects";
    public static final String url_saveHrExperience = "http://192.168.100.100/AESTest/SaveHrExperiences";
    public static final String url = "http://192.168.100.100/HandleMobileRequests/getimg.jsp?username=";
    public static final String load_student_image = "http://192.168.100.100/AESTest/GetImage";

    public static final String url_SaveAdminInstitute = "http://192.168.100.10/ProfileObjects/SaveAdminInstitute";
    public static final String upload_profile = "http://192.168.100.100/AESTest/UploadProfile";

    public static final String remove_profile = "http://192.168.100.100/AESTest/RemoveImage";
    public static final String URL_SAVE_HR_CONTACT_DETAILS = "http://192.168.100.100/AESTest/SaveHrContact";
    public static final String url_savedata_SaveContact = "http://192.168.100.100/AESTest/SaveContact";
    public static final String url_SaveExperiences_admin = "http://192.168.100.100/AESTest/SaveExperiences";
    public static final String url_createSingleUser_admin = "http://192.168.100.100/AESTest/CreateSingleUser";
    public static final String url_createMultipleUser_admin = "http://192.168.100.100/AESTest/CreateMultipleUser";
    public static final String url_delete_file = "http://192.168.100.100/AESTest/DeleteFile";
    public static final String url_uploadSingleFile = "http://192.168.100.100/AESTest/UploadSingleFile";
    public static final String url_GetRegisteredUsersUnderAdmin = "http://192.168.100.100/AESTest/GetRegisteredUsersUnderAdmin";
    public static final String url_getlastupdated = "http://192.168.100.100/AESTest/GetLastUpdated";
    public static final String url_SavePlacedDebarInfo = "http://192.168.100.100/AESTest/SavePlacedDebarInfo";
    public static final String url_GetPlacedDebarInfo = "http://192.168.100.100/AESTest/GetPlacedDebarInfo";
    public static final String url_DeleteNonActiveUser = "http://192.168.100.100/AESTest/DeleteNonActiveUser";
    public static final String url_SaveWelcomeIntroData = "http://192.168.100.100/AESTest/SaveNewUserWelcomeIntroData";
    public static final String url_SaveStudentFnameLnameMobile = "http://192.168.100.100/AESTest/SaveStudentFnameLnameMobile";

//    private static String url = "http://192.168.100.100/HandleMobileRequests/getimg.jsp?username=";
    public static final String url_SendActivationCode = "http://192.168.100.100/AESTest/SendActivationCode";
    public static final String url_SaveAndGenrateInstituteCode = "http://192.168.100.100/AESTest/SaveAndGenrateInstituteCode";
    public static final String url_SaveAndGenrateCompanyCode = "http://192.168.100.100/AESTest/SaveAndGenrateCompanyCode";

    public static String url_getcountries = "http://192.168.100.100/AESTest/GetCountries";
    public static String url_getstates = "http://192.168.100.100/AESTest/GetStates";
    public static String url_getcities = "http://192.168.100.100/AESTest/GetCities";
    public static final String url_checkUcode = "http://192.168.100.100/AESTest/checkUcode";
    public static final String url_ClearOTP = "http://192.168.100.100/AESTest/ClearOTP";
    public static final String url_AddStudentUnderAdmin = "http://192.168.100.100/AESTest/AddStudentUnderAdmin";


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

    //--


    public static String url_save_intro_data = "http://192.168.100.100/AESTest/SaveAlumniIntro";





}
