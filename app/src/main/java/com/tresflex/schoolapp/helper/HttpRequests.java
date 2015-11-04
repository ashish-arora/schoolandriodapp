package com.tresflex.schoolapp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.tresflex.schoolapp.db.DBConstants;
import com.tresflex.schoolapp.db.DBManager;
import com.tresflex.schoolapp.library.HttpClient;
import com.tresflex.schoolapp.model.Attendance;
import com.tresflex.schoolapp.model.Group;
import com.tresflex.schoolapp.model.Organization;
import com.tresflex.schoolapp.model.Parent;
import com.tresflex.schoolapp.model.StudentInfo;
import com.tresflex.schoolapp.model.Teacher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ashish on 03/11/15.
 */
public class HttpRequests {

    public static String postAttendance(JSONObject attendanceData) throws IOException{
        String result = HttpClient.doPostRequest(HttpUrlConstants.PostAttendanceUrl, attendanceData.toString());
        return result;
    }

    public static String postLogin(JSONObject data) throws IOException{
        String result = HttpClient.doPostRequest(HttpUrlConstants.LOGIN_URL, data.toString());
        return result;
    }

    public String createTeacher(HashMap<String,Integer> attendanceData){
        String result= "";
        try {
            result = HttpClient.doPostRequest(HttpUrlConstants.PostAttendanceUrl, attendanceData.toString());
            return result;
        } catch (Exception ex){
            Log.d(DBConstants.TAG, "Error while posting attendance data", ex);
            return result;
        }
    }

    public String postLogin(HashMap<String,Integer> attendanceData){
        String result= "";
        try {
            result = HttpClient.doPostRequest(HttpUrlConstants.PostAttendanceUrl, attendanceData.toString());
            return result;
        } catch (Exception ex){
            Log.d(DBConstants.TAG, "Error while posting attendance data", ex);
            return result;
        }
    }

    public String createStudent(HashMap<String,Integer> attendanceData){
        String result= "";
        try {
            result = HttpClient.doPostRequest(HttpUrlConstants.PostAttendanceUrl, attendanceData.toString());
            return result;
        } catch (Exception ex){
            Log.d(DBConstants.TAG, "Error while posting attendance data", ex);
            return result;
        }
    }

    public String createParent(HashMap<String,Integer> attendanceData){
        String result= "";
        try {
            result = HttpClient.doPostRequest(HttpUrlConstants.PostAttendanceUrl, attendanceData.toString());
            return result;
        } catch (Exception ex){
            Log.d(DBConstants.TAG, "Error while posting attendance data", ex);
            return result;
        }
    }

    public String postEvent(String status, String file_path){
        String result= "";
        try {
            result = HttpClient.doPostRequestMultiPart(HttpUrlConstants.PostEventUrl, status, file_path);
            return result;
        } catch (Exception ex){
            Log.d(DBConstants.TAG, "Error while posting attendance data", ex);
            return result;
        }
    }

    public String postHomework(HashMap<String,Integer> attendanceData){
        String result= "";
        try {
            result = HttpClient.doPostRequest(HttpUrlConstants.PostAttendanceUrl, attendanceData.toString());
            return result;
        } catch (Exception ex){
            Log.d(DBConstants.TAG, "Error while posting attendance data", ex);
            return result;
        }
    }

    public void processAttendanceJson(String data) throws JSONException{
        JSONObject jsonObject = new JSONObject(data);
        jsonObject.getJSONArray("asdads");

    }

    public static void setUserSharedPref(SharedPreferences.Editor editor, Teacher teacher, Set<String> organizationIds){
        editor.putString(DBConstants.FIRST_NAME, teacher.getFirstName());
        editor.putString(DBConstants.LAST_NAME, teacher.getLastName());
        editor.putInt(DBConstants.USER_TYPE, DBConstants.TEACHER_TYPE);
        editor.putString(DBConstants.EMAIL, teacher.getEmail());
        editor.putString(DBConstants.MSISDN, teacher.getMsisdn());
        editor.putString(DBConstants.USER_ID, teacher.getId());
        editor.putStringSet(DBConstants.ORGANIZATION_ID, organizationIds);
        editor.commit();
    }

    public static void setUserSharedPref(SharedPreferences.Editor editor, Parent parent, Set<String> organizationIds){
        editor.putString(DBConstants.FIRST_NAME, parent.getFirstName());
        editor.putString(DBConstants.LAST_NAME, parent.getLastName());
        editor.putInt(DBConstants.USER_TYPE, DBConstants.PARENT_TYPE);
        editor.putString(DBConstants.EMAIL, parent.getEmail());
        editor.putString(DBConstants.MSISDN, parent.getMsisdn());
        editor.putString(DBConstants.USER_ID, parent.getId());
        editor.putStringSet(DBConstants.ORGANIZATION_ID, organizationIds);
        editor.commit();
    }

    public static void parseMembers(JSONArray members) throws JSONException
    {
        for(int i=0; i<members.length(); i++)
        {
            JSONObject student = members.getJSONObject(i);
            StudentInfo studentObj = StudentInfo.convertFromJson(student);
            DBManager.getInstance().insertStudent(studentObj);

        }

    }

    public static void parseOwners(JSONArray owners, String groupId) throws JSONException
    {
        List<String> teacherIds = new ArrayList<>();
        for(int i=0; i<owners.length(); i++)
        {
            JSONObject teacher = owners.getJSONObject(i);
            Teacher teacherObj = Teacher.convertFromJson(teacher);
            DBManager.getInstance().insertTeacher(teacherObj);
            teacherIds.add(teacherObj.getId());
        }

        DBManager.getInstance().insertGroupTeacherList(groupId, teacherIds, 1);
    }


    public static void parseLoginResponse(String response, Context context) throws JSONException{
        JSONObject jsonObject = new JSONObject(response);
        SharedPreferences sharedpreferences = context.getSharedPreferences(DBConstants.PREF_NAME, Context.MODE_PRIVATE);
        if (jsonObject.get("stat") == "ok"){
            JSONObject userProfile = jsonObject.getJSONObject("user_profile");

            Integer user_type = (Integer) userProfile.get("type");

            SharedPreferences.Editor editor = sharedpreferences.edit();

            if (user_type.equals(DBConstants.TEACHER_TYPE)){

                JSONArray organizations = userProfile.getJSONArray("organization");
                List<String> organizationIds = new ArrayList<>();
                for (int j=0; j < organizations.length(); j++){
                    JSONObject organization = organizations.getJSONObject(j);
                    organizationIds.add(organization.getString("id"));
                    Organization organizationObj = Organization.convertFromJson(organization);
                    DBManager.getInstance().insertOrganization(organizationObj);
                }

                Teacher teacher = Teacher.convertFromJson(userProfile);

                Set<String> organizationSet = new HashSet<String>(organizationIds);

                setUserSharedPref(editor, teacher, organizationSet);

                DBManager.getInstance().insertTeacher(teacher);

                DBManager.getInstance().insertTeacherOrganizationList(teacher.getId(), organizationIds);

                JSONArray classListData = jsonObject.getJSONObject("result").getJSONArray("class_data");

                for (int i =0; i< classListData.length(); i++)
                {
                    JSONObject class_data = classListData.getJSONObject(i);
                    Group group = Group.convertFromJson(class_data);
                    DBManager.getInstance().insertGroup(group);

                    JSONArray members = class_data.getJSONArray("members");
                    List<StudentInfo> studentList = new ArrayList<StudentInfo>();

                    for (int j=0; j< members.length(); j++)
                    {
                        JSONObject studentData = members.getJSONObject(j);
                        StudentInfo studentObj = StudentInfo.convertFromJson(studentData);
                        DBManager.getInstance().insertStudent(studentObj);
                    }

                }
            } else if(user_type.equals(DBConstants.PARENT_TYPE))
            {
                JSONArray organizations = userProfile.getJSONArray("organization");
                List<String> organizationIds = new ArrayList<>();
                for (int j=0; j < organizations.length(); j++){
                    JSONObject organization = organizations.getJSONObject(j);
                    organizationIds.add(organization.getString("id"));
                    Organization organizationObj = Organization.convertFromJson(organization);
                    DBManager.getInstance().insertOrganization(organizationObj);
                }

                Parent parent = Parent.convertFromJson(userProfile);

                Set<String> organizationSet = new HashSet<String>(organizationIds);

                setUserSharedPref(editor, parent, organizationSet);

                DBManager.getInstance().insertParent(parent);

                DBManager.getInstance().insertParentOrganizationList(parent.getId(), organizationIds);

                JSONArray studentList = jsonObject.getJSONObject("result").getJSONArray("child_data");

                for(int i =0; i< studentList.length(); i++)
                {
                    JSONObject student = studentList.getJSONObject(i);
                    JSONObject group = student.getJSONObject("group");

                    Group groupObj = Group.convertFromJson(group);

                    JSONArray groupMembers = group.getJSONArray("members");

                    parseMembers(groupMembers);

                    JSONArray groupOwners = group.getJSONArray("owner");

                    parseOwners(groupOwners, groupObj.getId());

                    JSONArray groupSubjects = group.getJSONArray("subjects");

                    // Have to work on Subjects here
                    /* To Do */

                    StudentInfo studentObj = StudentInfo.convertFromJson(student);
                    DBManager.getInstance().insertStudent(studentObj);
                }

                JSONArray attendanceList = jsonObject.getJSONObject("result").getJSONArray("attendance_data");
                List<Attendance> attendancesObjList = new ArrayList<>();
                for(int i =0; i< attendanceList.length(); i++)
                {
                    attendancesObjList.add(Attendance.convertFromJson(attendanceList.getJSONObject(i)));
                }
                DBManager.getInstance().insertAttendanceList(attendancesObjList);

            }
        }
    }

}
