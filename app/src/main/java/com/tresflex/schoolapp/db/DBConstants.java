package com.tresflex.schoolapp.db;

/**
 * Created by ashish on 03/11/15.
 */

public interface DBConstants {
    // TAG

    public static final String TAG="schoolchap";

    // USER TYPE

    public static final Integer ADMIN_TYPE = 1;

    public static final Integer TEACHER_TYPE = 2;

    public static final Integer STUDENT_TYPE = 3;

    public static final Integer PARENT_TYPE = 4;

    // APP SETTINGS

    public static final String FIRST_NAME = "first_name";

    public static final String LAST_NAME = "last_name";

    public static final String USER_TYPE = "user_type";

    public static final String MSISDN = "msisdn";

    public static final String EMAIL = "email";


    // Shared Pref Settings

    public static final int PRIVATE_MODE = 0;

    // Sharedpref file name
    public static final String PREF_NAME = "Pref";

    // Tables

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "schoolchap";

    public static final String ATTENDANCE_TABLE = "attendance";

    public static final String GROUPS = "groups";

    public static final String ORGANIZATION = "organization";

    public static final String EVENT = "event";

    public static final String HOMEWORK = "homework";

    public static final String SUBJECTS = "subjects";

    public static final String STUDENTS = "students";

    public static final String TEACHERS = "teachers";

    public static final String PARENTS = "parents";

    public static final String PARENTS_ORGANIZATION = "parents_organization";

    public static final String TEACHERS_ORGANIZATION = "teachers_organization";





    // FIELDS

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String MSISDN = "msisdn";

    public static final String EMAIL = "email";

    public static final String USER_ID = "user_id";

    public static final String ADDRESS= "address";

    public static final String TIMESTAMP = "ts";

    public static final String YOUR_CHILD = "your_child";

    public static final String GROUP_ID = "group_id";

    public static final String TEACHER_ID = "teacher_id";

    public static final String SUBJECT_ID = "subject_id";

    public static final String ORGANIZATION_ID = "organization_id";

    public static final String PARENTS_STUDENTS = "parents_students";

    public static final String TEACHERS_GROUPS = "groups";

    public static final String STUDENT_ID = "student_id";

    public static final String PARENT_ID = "parent_id";

    public static final String PRESENT = "present";

    public static final String OWNER_ID = "owner_id";

    public static final String TOTAL_STUDENTS = "total_students";

    public static final String ROLL_NO = "roll_no";

    public static final String IS_OWNER = "is_owner";


}
