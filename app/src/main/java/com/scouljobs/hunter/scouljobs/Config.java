package com.scouljobs.hunter.scouljobs;

public class Config {
    public static final String MYPREFERENCES = "MyPrefs";
    public static int RESULT_LOAD = 1;

    //Error Messages
    public static String NO_SERVER_RESPONSE = "Please Check your Internet Connection.";
    public static String NO_DATA_ERROR = "No Data Found, try again please.";

    //Keys to map values to server
    public static String FIRSTNAME = "firstname";
    public static String LASTNAME = "lastname";
    public static String EMAIL = "email";
    public static String PASSWORD = "password";
    public static String USER_ID = "user_id";
    public static String EDUCATION = "education";
    public static String DOB = "dob";
    public static String NATIONALITY = "nationality";
    public static String ADDRESS = "address";
    public static String PHONE = "phone";

    //Display feedback, processing
    public static String PROGRESS_BAR = "Processing...";
    public static String REGISTER_PROGRESS = "Registering...";
    public static String LOGIN_PROGRESS = "Login...";

    //Urls
    public static String url = "http://10.0.2.2/scoul_jobs/";
    public static String REGISTER = url+"register.php";
    public static String LOGIN = url+"login.php";
    public static String ADD_JOBS = url+"add_jobs.php";
    public static String GET_JOBS = url+"get_jobs.php";
    public static String UPDATE_JOB = url+"update_job.php";
    public static String DELETE_JOB = url+"delete_job.php";

    public static String ORG_NAME = "org_name";
    public static String TITLE = "title";
    public static String CATEGORY = "category";
    public static String DESCRIPTION = "description";
    public static String QUALIFICATIONS = "qualifications";
    public static String REQUIREMENTS = "requirements";
    public static String JOB_ID = "job_id";

}
