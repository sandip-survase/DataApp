package placeme.octopusites.com.placeme;

/**
 * Created by admin on 9/27/2017.
 */

public class MyConstants {

    public static final int USER_DATA_CHANGE_RESULT_CODE=888;
    public static final String USERNAME_KEY = "nameKey";
    public static final String PASSWORD_KEY = "passKey";


    //-----------------------------------   PlaceMe   ---------------------------------

    public static final String url_savesessiondetails = "http://192.168.100.100/PlaceMe/SaveSessionDetails";
    public static final String url_create_firebase= "http://192.168.100.100/PlaceMe/RegisterFirebaseUser";
    public static final String url_UpdateFirebaseToken= "http://192.168.100.100/PlaceMe/UpdateFirebaseToken";
    public static final String url_ChangeUsernameFireBase= "http://192.168.100.100/PlaceMe/ChangeUsername";


    //-----------------------------------   AESTest   ---------------------------------

    public static final String url_Welcome = "http://192.168.100.100/AESTest/Welcome";
    public static final String url_login = "http://192.168.100.100/AESTest/Auth";           // changed to AEStest.auth
//    public static final String url_savesessiondetails = "http://192.168.100.100/PlaceMe/SaveSessionDetails";

    public static final String load_last_updated = "http://192.168.100.100/AESTest/GetLastUpdated";
    public static final String url_remove_profile = "http://192.168.100.100/AESTest/RemoveImage";
    public static String url_getcountries = "http://192.168.100.100/AESTest/GetCountries";
    public static String url_getstates = "http://192.168.100.100/AESTest/GetStates";
    public static String url_getcities = "http://192.168.100.100/AESTest/GetCities";


    public static String url_save_intro_data = "http://192.168.100.100/AESTest/SaveAlumniIntro";
    public static String url_load_alumni_data = "http://192.168.100.10/ProfileObjects/GetAlumniData";
    public static String url_getcourses = "http://192.168.100.100/AESTest/GetDiplomaCourses";

    // not working on my ip(http://192.168.100.10) so use "http://192.168.100.100
//    public static String url_getugcourses = "http://192.168.100.100/AESTest/GetUGCourses";
//    public static String url_getstreams= "http://192.168.100.100/AESTest/GetUGStreams";
//    public static String url_getuniversities= "http://192.168.10.100/AESTest/GetUGUniversities";
//    public static String url_savedata_ug= "http://192.168.100.100/AESTest/SaveUg";

    public static String url_getugcourses = "http://192.168.100.100/ProfileObjects/GetUGCourses";
    public static String url_getstreams= "http://192.168.100.100/AESTest/GetUGStreams";
    public static String url_getuniversities= "http://192.168.100.100/AESTest/GetUGUniversities";
    public static String url_savedata_ug= "http://192.168.100.10/ProfileObjects/SaveUg";

    public static String url_getpgcourses = "http://192.168.100.100/AESTest/GetPGCourses";
    public static String url_getpgstreams= "http://192.168.100.100/AESTest/GetPGStreams";
    public static String url_getpguniversities= "http://192.168.100.100/AESTest/GetPGUniversities";
    public static String url_savedata_pg_sem= "http://192.168.100.10/ProfileObjects/SavePgSem";
    public static String url_savedata_pg_year= "http://192.168.100.10/ProfileObjects/SavePgYear";

//    public static String savepersonalinfo = "http://192.168.100.100/AESTest/SavePersonalInfo";
public static String savepersonalinfo = "http://192.168.100.10:8080/ProfileObjects/SavePersonalInfo";
    public static final String savepersonalinfoAlumni = "http://192.168.100.10/ProfileObjects/SavePersonalInfoAlumni";


    public static final String url_getlanguages = "http://192.168.100.100/AESTest/GetLanguages";


    public static final String URL_SAVE_ALUMNI_CONTACT_DETAILS = "http://192.168.100.100/AESTest/SaveAlumniContact";
    public static final String LOAD_ALUMNI_DATA = "http://192.168.100.100/AESTest/GetAlumniData";

    public static final String URL_SAVE_ALUMNI_PROJECTS= "http://192.168.100.100/AESTest/SaveAlumniProjects";
    public static final String url_saveHrExperience = "http://192.168.100.100/AESTest/SaveHrExperiences";

    public static final String url_savecareerobj= "http://192.168.100.10/ProfileObjects/SaveCareerObj";
    public static final String url_savestrengths= "http://192.168.100.10/ProfileObjects/SaveStrengths";
    public static final String url_saveweaknesses= "http://192.168.100.10/ProfileObjects/SaveWeaknesses";
    public static final String url_savelocationpreferences= "http://192.168.100.10/ProfileObjects/SaveLocationPreferences";

    public static final String url_savelanguages= "http://192.168.100.10/ProfileObjects/SaveLanguages";
    public static final String url_savecertifications= "http://192.168.100.10/ProfileObjects/SaveCertificates";
    public static final String url_savecourses= "http://192.168.100.10/ProfileObjects/SaveCourses";
    public static final String url_saveskills = "http://192.168.100.10/ProfileObjects/SaveSkills";
    public static final String url_savehonors= "http://192.168.100.10/ProfileObjects/SaveHonors";
    public static final String url_savepatents= "http://192.168.100.10/ProfileObjects/SavePatents";
    public static final String url_savepublications= "http://192.168.100.10/ProfileObjects/SavePublications";

    public static final String url_saveprojects= "http://192.168.100.10/ProfileObjects/SaveProjects";


    public static final String url = "http://192.168.100.100/HandleMobileRequests/getimg.jsp?username=";
    public static final String load_student_image = "http://192.168.100.100/AESTest/GetImage";
    public static final String upload_profile = "http://192.168.100.100/AESTest/UploadProfile";

    public static final String load_HR_data = "http://192.168.100.10/ProfileObjects/GetHrData";
    public static final String remove_profile = "http://192.168.100.100/AESTest/RemoveImage";

    public static final String url_savedata_SaveHrCompany = "http://192.168.100.100/AESTest/SaveHrCompany";

    public static final String url_savedata_SaveHrIntro1 = "http://192.168.100.100/AESTest/SaveHrIntro";

    public static final String URL_SAVE_HR_CONTACT_DETAILS = "http://192.168.100.100/AESTest/SaveHrContact";

//    private static String url = "http://192.168.100.100/HandleMobileRequests/getimg.jsp?username=";

    public static final String url_savedata= "http://192.168.100.10/ProfileObjects/SaveTenth";
//    public static final String savepersonalinfoAlumni = "http://192.168.100.100/AESTest/SavePersonalInfoAlumni";
    public static final String url_savedata_SaveContact= "http://192.168.100.100/AESTest/SaveContact";

    public static final String url_savedata_SaveIntro= "http://192.168.100.10:8080/ProfileObjects/SaveIntro";

    public static final String load_student_data = "http://192.168.100.10:8080/ProfileObjects/GetStudentData";

    public static final String url_SaveExperiences_admin= "http://192.168.100.100/AESTest/SaveExperiences";

    public static final String url_createSingleUser_admin= "http://192.168.100.100/AESTest/CreateSingleUser";
    public static final String url_createMultipleUser_admin= "http://192.168.100.100/AESTest/CreateMultipleUser";
    public static final String url_delete_file= "http://192.168.100.100/AESTest/DeleteFile";
    public static final String url_uploadSingleFile = "http://192.168.100.100/AESTest/UploadSingleFile";

    public static final String url_GetRegisteredUsersUnderAdmin = "http://192.168.100.100/AESTest/GetRegisteredUsersUnderAdmin";
    public static final String url_getlastupdated = "http://192.168.100.100/AESTest/GetLastUpdated";
    public static final String url_SavePlacedDebarInfo = "http://192.168.100.100/AESTest/SavePlacedDebarInfo";
    public static final String url_GetPlacedDebarInfo = "http://192.168.100.100/AESTest/GetPlacedDebarInfo";
    public static final String url_DeleteNonActiveUser = "http://192.168.100.100/AESTest/DeleteNonActiveUser";

    public static final String url_SaveWelcomeIntroData = "http://192.168.100.100/AESTest/SaveNewUserWelcomeIntroData";
    public static final String url_SaveStudentFnameLnameMobile = "http://192.168.100.100/AESTest/SaveStudentFnameLnameMobile";
    public static final String url_SendActivationCode = "http://192.168.100.100/AESTest/SendActivationCode";
    public static final String url_SaveAndGenrateInstituteCode = "http://192.168.100.20:8080/AESTest/SaveAndGenrateInstituteCode";
    public static final String url_SaveAndGenrateCompanyCode = "http://192.168.100.100/AESTest/SaveAndGenrateCompanyCode";
    public static final String url_checkUcode = "http://192.168.100.100/AESTest/checkUcode";

    //--



//    public static String url_getplacementsmetadata = "http://192.168.100.30/CreateNotificationTemp/GetPlacementsAdminMetaData";
//    public static String url_getplacementsreadstatus = "http://192.168.100.30/CreateNotificationTemp/GetReadStatusOfPlacementsForAdmin";
//    public static String url_getplacements = "http://192.168.100.30/CreateNotificationTemp/GetPlacementsAdmin";
//    public static String url_changeplacementsreadstatus = "http://192.168.100.30/CreateNotificationTemp/ChangePlacementReadStatusAdmin";
//    //notiffurl
//    public static String url_getnotificationsmetadata = "http://192.168.100.30/CreateNotificationTemp/GetNotificationsAdminMetaData";
//    public static String url_getnotificationsreadstatus = "http://192.168.100.30/CreateNotificationTemp/GetReadStatusOfNotificationsAdmin";
//    public static String url_getnotifications = "http://192.168.100.30/CreateNotificationTemp/GetNotificationsAdmin";
//    public static String url_changenotificationsreadstatus = "http://192.168.100.30/CreateNotificationTemp/ChangeNotificationReadStatusAdmin";
//    public static String url_getlastupdated = "http://192.168.100.30/CreateNotificationTemp/GetLastUpdatedAdmin";



}
