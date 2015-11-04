package com.tresflex.schoolapp.teacherfragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.activity.BaseFragment;
import com.tresflex.schoolapp.activity.MainActivity;
import com.tresflex.schoolapp.adapter.TakeAttendanceAdapter;
import com.tresflex.schoolapp.db.DBConstants;
import com.tresflex.schoolapp.helper.AppPreferences;
import com.tresflex.schoolapp.helper.Constants;
import com.tresflex.schoolapp.helper.HttpRequests;
import com.tresflex.schoolapp.model.TeacherAddStudent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;


public class TakeAttendanceStudentList extends BaseFragment implements View.OnClickListener {

    View mView;
    Activity thisActivity;
    Bundle savedInstanceState;
    ListView takeAttendanceListView;
    Button saveBtn;
    TakeAttendanceAdapter takeAttendanceAdapter;
    AlertDialog.Builder mAlertDialogBuilder;
    TextView alertText;
    ArrayList<TeacherAddStudent> student_list = new ArrayList<TeacherAddStudent>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    String[] studentName={"Joy", "Happy"};
    Long[] studentRollNo={12L, 14L};


    public static TakeAttendanceStudentList newInstance(String param1, String param2) {
        TakeAttendanceStudentList fragment = new TakeAttendanceStudentList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TakeAttendanceStudentList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_take_attendance_student_list, container, false);
        thisActivity = getActivity();
        MainActivity.mTitleTextView.setText(Constants.ATTENDANCE);
        MainActivity.imgSlider.setVisibility(View.VISIBLE);
        mAlertDialogBuilder = new AlertDialog.Builder(thisActivity);
        initialize();
        return mView;
    }

    private void initialize() {
        takeAttendanceListView = (ListView) mView.findViewById(R.id.takeAttendanceListView);
        saveBtn = (Button) mView.findViewById(R.id.takeAttendanceSaveBtn);
        saveBtn.setOnClickListener(this);
        saveBtn.setVisibility(View.VISIBLE);
        alertText=(TextView)mView.findViewById(R.id.alertText);

        if (AppPreferences.getAddStudentList(thisActivity).equals("")) {
            //showAlertDialogStudet("School App", "Add Student");
            alertText.setVisibility(View.VISIBLE);
            alertText.setText(Constants.ALERT_TEXT);
        } else {

            alertText.setVisibility(View.INVISIBLE);
            String json=AppPreferences.getAddStudentList(thisActivity);
            System.out.println("JSON"+json);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<TeacherAddStudent>>() {}.getType();
            ArrayList<TeacherAddStudent> studentList = gson.fromJson(json, type);
            for (int i=0; i < studentName.length; i++){
                TeacherAddStudent student = new TeacherAddStudent();
                student.setStudentName(studentName[i]);
                student.setRollNo(studentRollNo[i]);
                student_list.add(student);
            }
            takeAttendanceAdapter = new TakeAttendanceAdapter(thisActivity, student_list);
            takeAttendanceListView.setAdapter(takeAttendanceAdapter);
        }
    }

    private void showAlertDialogStudet(String title, String message) {

        mAlertDialogBuilder.setTitle(title);
        mAlertDialogBuilder.setMessage(message);
        mAlertDialogBuilder.setCancelable(false);
        mAlertDialogBuilder.setNegativeButton(Constants.OK,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ClassOneA.tabHost.setCurrentTab(1);
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = mAlertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.takeAttendanceSaveBtn:
                HashMap<String, Integer> attendanceData= new HashMap<String,Integer>();
                Strint result = HttpRequests.postAttendance(attendanceData);
                Log.d(DBConstants.TAG, "post attendance result: " + result);
                Toast.makeText(thisActivity, Constants.SAVE_TEXT, Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
