package com.tresflex.schoolapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tresflex.schoolapp.model.Attendance;
import com.tresflex.schoolapp.model.Group;
import com.tresflex.schoolapp.model.Organization;
import com.tresflex.schoolapp.model.Parent;
import com.tresflex.schoolapp.model.StudentInfo;
import com.tresflex.schoolapp.model.Teacher;

import java.util.List;

/**
 * Created by ashish on 02/11/15.
 */
public class DBManager extends SQLiteOpenHelper{

    private static volatile SQLiteDatabase mDb;

    private static volatile DBManager dbManager;

    private static Context mContext;

    public static void init(Context context)
    {
        if (dbManager == null)
        {
            mContext = context;
            dbManager = new DBManager(context);
        }
    }

    public static DBManager getInstance()
    {
        return dbManager;
    }

    private DBManager(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
        mDb = getWritableDatabase();
    }

    public SQLiteDatabase getWriteDatabase()
    {
        if(mDb == null || !mDb.isOpen())
        {
            mDb = super.getWritableDatabase();
        }
        return mDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db == null) {
            db = mDb;
        }

        String sql = "CREATE TABLE IF NOT EXISTS " + DBConstants.ORGANIZATION
                + " ( "
                + DBConstants.ID + " TEXT, "
                + DBConstants.NAME + " TEXT, "
                + DBConstants.ADDRESS + " TEXT, "
                + DBConstants.TIMESTAMP + "LONG"
                + " ) ";

        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + DBConstants.STUDENTS
                + " ( "
                + DBConstants.ID + " TEXT PRIMARY KEY, "  // student ID
                + DBConstants.NAME + " TEXT, "
                + DBConstants.MSISDN + " TEXT, "
                + DBConstants.EMAIL + " TEXT, "
                + DBConstants.TIMESTAMP + "LONG, "
                + DBConstants.GROUP_ID + "TEXT, "
                + "FOREIGN KEY(" + DBConstants.GROUP_ID + ") REFERENCES " + DBConstants.GROUPS + "(" + DBConstants.ID  + ")"
                + " ) ";

        db.execSQL(sql);


        sql = "CREATE TABLE IF NOT EXISTS " + DBConstants.TEACHERS
                + " ( "
                + DBConstants.ID + " TEXT PRIMARY KEY, "  // student ID
                + DBConstants.NAME + " TEXT, "
                + DBConstants.MSISDN + " TEXT, "
                + DBConstants.EMAIL + " TEXT, "
                + DBConstants.TIMESTAMP + "LONG, "
                + " ) ";

        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + DBConstants.PARENTS
                + " ( "
                + DBConstants.ID + " TEXT PRIMARY KEY, "  // parent ID
                + DBConstants.NAME + " TEXT, "
                + DBConstants.MSISDN + " TEXT, "
                + DBConstants.EMAIL + " TEXT, "
                + DBConstants.TIMESTAMP + "LONG, "
                + DBConstants.GROUP_ID + "TEXT, "
                + "FOREIGN KEY(" + DBConstants.GROUP_ID + ") REFERENCES " + DBConstants.GROUPS + "(" + DBConstants.ID  + "),"
                + " ) ";

        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + DBConstants.PARENTS_ORGANIZATION
                + " ( "
                + DBConstants.ID + " TEXT PRIMARY KEY AUTOINCREMENT, "
                + DBConstants.PARENT_ID + " TEXT, "
                + DBConstants.ORGANIZATION_ID + " TEXT, "
                + "FOREIGN KEY(" + DBConstants.PARENT_ID + ") REFERENCES " + DBConstants.PARENTS + "(" + DBConstants.ID  + "),"
                + "FOREIGN KEY(" + DBConstants.ORGANIZATION_ID + ") REFERENCES " + DBConstants.ORGANIZATION + "(" + DBConstants.ID  + ")"
                + " ) ";

        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + DBConstants.TEACHERS_ORGANIZATION
                + " ( "
                + DBConstants.ID + " TEXT PRIMARY KEY AUTOINCREMENT, "
                + DBConstants.TEACHER_ID + " TEXT, "
                + DBConstants.ORGANIZATION_ID + " TEXT, "
                + "FOREIGN KEY(" + DBConstants.TEACHER_ID + ") REFERENCES " + DBConstants.TEACHERS + "(" + DBConstants.ID  + "),"
                + "FOREIGN KEY(" + DBConstants.ORGANIZATION_ID + ") REFERENCES " + DBConstants.ORGANIZATION + "(" + DBConstants.ID  + ")"
                + " ) ";

        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + DBConstants.PARENTS_STUDENTS
                + " ( "
                + DBConstants.ID + " TEXT PRIMARY KEY AUTOINCREMENT, "
                + DBConstants.PARENT_ID + " TEXT, "
                + DBConstants.STUDENT_ID + " TEXT, "
                + "FOREIGN KEY(" + DBConstants.PARENT_ID + ") REFERENCES " + DBConstants.PARENTS + "(" + DBConstants.ID  + "),"
                + "FOREIGN KEY(" + DBConstants.STUDENT_ID + ") REFERENCES " + DBConstants.STUDENTS + "(" + DBConstants.ID  + ")"
                + " ) ";

        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + DBConstants.TEACHERS_GROUPS
                + " ( "
                + DBConstants.ID + " TEXT PRIMARY KEY AUTOINCREMENT, "
                + DBConstants.TEACHER_ID + " TEXT, "
                + DBConstants.GROUP_ID + " TEXT, "
                + DBConstants.IS_OWNER + " INTEGER DEFAULT 0, "
                + "FOREIGN KEY(" + DBConstants.TEACHER_ID + ") REFERENCES " + DBConstants.TEACHERS + "(" + DBConstants.ID  + "),"
                + "FOREIGN KEY(" + DBConstants.GROUP_ID + ") REFERENCES " + DBConstants.GROUPS + "(" + DBConstants.ID  + ")"
                + " ) ";

        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + DBConstants.ATTENDANCE_TABLE
                + " ( "
                + DBConstants.ID + " TEXT, "
                + DBConstants.PRESENT + " INTEGER, "
                + DBConstants.TIMESTAMP + "LONG, "
                + DBConstants.STUDENT_ID + "TEXT,"
                + DBConstants.GROUP_ID + "TEXT,"
                + "FOREIGN KEY(" + DBConstants.STUDENT_ID + ") REFERENCES " + DBConstants.STUDENTS + "(" + DBConstants.ID  + "),"
                + "FOREIGN KEY(" + DBConstants.GROUP_ID + ") REFERENCES " + DBConstants.GROUPS + "(" + DBConstants.ID  + ")"
                + " ) ";

        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + DBConstants.GROUPS
                + " ( "
                + DBConstants.ID + " TEXT, "
                + DBConstants.NAME + " TEXT, "
                + DBConstants.TOTAL_STUDENTS + "INTEGER DEFAULT 0, "
                + DBConstants.TIMESTAMP + "LONG"
                + " ) ";

        db.execSQL(sql);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (db == null) {
            db = mDb;
        }
    }

    public void insertStudent(StudentInfo student){
        SQLiteDatabase db = this.getWriteDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.ID, student.getId());
        values.put(DBConstants.FIRST_NAME, student.getFirstName());
        values.put(DBConstants.LAST_NAME, student.getLastName());
        values.put(DBConstants.ROLL_NO, student.getRollNo());
        values.put(DBConstants.MSISDN, student.getLastName());
        values.put(DBConstants.EMAIL, student.getLastName());
        values.put(DBConstants.GROUP_ID, student.getGroupId());
        db.insert(DBConstants.STUDENTS, null, values);
        db.close();
    }

    public void updateStudent(StudentInfo student){
        SQLiteDatabase db = this.getWriteDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.ID, student.getId());
        values.put(DBConstants.FIRST_NAME, student.getFirstName());
        values.put(DBConstants.LAST_NAME, student.getLastName());
        values.put(DBConstants.ROLL_NO, student.getRollNo());
        values.put(DBConstants.MSISDN, student.getLastName());
        values.put(DBConstants.EMAIL, student.getLastName());
        values.put(DBConstants.GROUP_ID, student.getGroupId());
        db.update(DBConstants.STUDENTS, values, DBConstants.ID + " = ?",
                new String[]{String.valueOf(student.getId())});
        db.close();
    }

    public void deleteStudent(StudentInfo student){
        SQLiteDatabase db = this.getWriteDatabase();
        db.delete(DBConstants.STUDENTS, DBConstants.ID + " = ?",
                new String[]{String.valueOf(student.getId())});
        db.close();
    }

    public Integer getStudentCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + DBConstants.STUDENTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public Integer getStudentCountInGroup(int group_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  count(*) FROM " + DBConstants.STUDENTS + " where "+ DBConstants.GROUP_ID + "=" + group_id;
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            cursor.close();
            return cursor.getInt(0);
        }
        return 0;
    }


    public StudentInfo getStudent(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DBConstants.STUDENTS, new String[] { DBConstants.ID,
                        DBConstants.FIRST_NAME, DBConstants.LAST_NAME, DBConstants.ROLL_NO, DBConstants.MSISDN, DBConstants.EMAIL, DBConstants.GROUP_ID }, DBConstants.ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        StudentInfo student = new StudentInfo(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(5));
        return student;
    }

    public void insertGroup(Group group){
        SQLiteDatabase db = this.getWriteDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.ID, group.getId());
        values.put(DBConstants.NAME, group.getName());
        values.put(DBConstants.TIMESTAMP, group.getTimestamp());
        db.insert(DBConstants.GROUPS, null, values);
        db.close();
    }

    public void insertTeacher(Teacher teacher){
        SQLiteDatabase db = this.getWriteDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.ID, teacher.getId());
        values.put(DBConstants.FIRST_NAME, teacher.getFirstName());
        values.put(DBConstants.LAST_NAME, teacher.getLastName());
        values.put(DBConstants.TIMESTAMP, teacher.getTimestamp());
        db.insert(DBConstants.TEACHERS, null, values);
        db.close();
    }

    public void insertParent(Parent parent){
        SQLiteDatabase db = this.getWriteDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.ID, parent.getId());
        values.put(DBConstants.FIRST_NAME, parent.getFirstName());
        values.put(DBConstants.LAST_NAME, parent.getLastName());
        values.put(DBConstants.TIMESTAMP, parent.getTimestamp());
        db.insert(DBConstants.PARENTS, null, values);
        db.close();
    }

    public void insertOrganization(Organization organization){
        SQLiteDatabase db = this.getWriteDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.ID, organization.getId());
        values.put(DBConstants.NAME, organization.getName());
        values.put(DBConstants.ADDRESS, organization.getAddress());
        values.put(DBConstants.TIMESTAMP, organization.getTimestamp());
        db.insert(DBConstants.ORGANIZATION, null, values);
        db.close();
    }

    public void insertParentOrganizationList(String parentId, List<String> organizationIds){
        SQLiteDatabase db = this.getWriteDatabase();
        for(String organizationId: organizationIds){
            ContentValues values = new ContentValues();
            values.put(DBConstants.PARENT_ID, parentId);
            values.put(DBConstants.ORGANIZATION_ID, organizationId);
            db.insert(DBConstants.PARENTS_ORGANIZATION, null, values);
        }
        db.close();
    }

    public void insertTeacherOrganizationList(String teacherId, List<String> organizationIds){
        SQLiteDatabase db = this.getWriteDatabase();
        for(String organizationId: organizationIds){
            ContentValues values = new ContentValues();
            values.put(DBConstants.TEACHER_ID, parentId);
            values.put(DBConstants.ORGANIZATION_ID, organizationId);
            db.insert(DBConstants.TEACHERS_ORGANIZATION, null, values);
        }
        db.close();
    }

    public void insertGroupTeacherList(String groupId, List<String> teacherIds, Integer isOwner){
        SQLiteDatabase db = this.getWriteDatabase();
        for(String teacherId: teacherIds){
            ContentValues values = new ContentValues();
            values.put(DBConstants.GROUP_ID, groupId);
            values.put(DBConstants.TEACHER_ID, teacherId);
            values.put(DBConstants.TEACHER_ID, teacherId);
            values.put(DBConstants.IS_OWNER, isOwner);
            db.insert(DBConstants.TEACHERS_GROUPS, null, values);
        }
        db.close();
    }

    public void insertAttendanceList(List<Attendance> attendances){
        SQLiteDatabase db = this.getWriteDatabase();
        for(Attendance attendance: attendances)
        {
            ContentValues values = new ContentValues();
            values.put(DBConstants.ID, attendance.getId());
            values.put(DBConstants.TIMESTAMP, attendance.getTimestamp());
            values.put(DBConstants.STUDENT_ID, attendance.getStudentId());
            values.put(DBConstants.GROUP_ID, attendance.getGroupId());
            values.put(DBConstants.PRESENT, attendance.getPresent());
            values.put(DBConstants.GROUP_ID, student.getGroupId());
            db.insert(DBConstants.STUDENTS, null, values);
        }
        db.close();
    }
}
