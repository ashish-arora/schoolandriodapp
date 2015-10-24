package com.tresflex.schoolapp.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.tresflex.schoolapp.model.TeacherAddStudent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppPreferences {

    public static final String KEY = Constants.SCHOOL_APP;
    static final String key_user_name = Constants.USER_NAME;

    public static void setUserType(Context c, String username) {
        SharedPreferences sp = c.getSharedPreferences(Constants.USER_NAME, 0);
        Editor editor = sp.edit();
        editor.putString(Constants.USER_NAME, username);
        editor.commit();
    }

    public static String getUserType(Context c) {
        SharedPreferences sp = c.getSharedPreferences(Constants.USER_NAME, 0);
        return sp.getString(Constants.USER_NAME, "");
    }

    public static void setAddStudentList(Context c, String json) {
        SharedPreferences sp = c.getSharedPreferences(Constants.STUDENT_LIST, 0);
        Editor editor = sp.edit();
        editor.putString(Constants.STUDENT_LIST, json);
        editor.commit();
    }
    public static String getAddStudentList(Context c) {
        SharedPreferences sp = c.getSharedPreferences(Constants.STUDENT_LIST, 0);
        return sp.getString(Constants.STUDENT_LIST, "");
    }
}
