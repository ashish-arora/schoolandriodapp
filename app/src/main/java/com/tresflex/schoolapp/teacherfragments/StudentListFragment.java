package com.tresflex.schoolapp.teacherfragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.tresflex.schoolapp.adapter.EventsFeedAdapter;
import com.tresflex.schoolapp.adapter.StudentListAdapter;
import com.tresflex.schoolapp.helper.AppPreferences;
import com.tresflex.schoolapp.helper.Constants;
import com.tresflex.schoolapp.model.TeacherAddStudent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * #0099ff
 * Created by Admin on 20-10-2015.
 */
public class StudentListFragment extends BaseFragment implements View.OnClickListener {
    View mView;
    Activity thisActivity;
    Bundle savedInstanceState;
    ListView studentListView;
    Button saveBtn;
    StudentListAdapter studentListAdapter;
    AlertDialog.Builder mAlertDialogBuilder;
    TextView alertText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_student_list, container, false);
        thisActivity = getActivity();
        MainActivity.mTitleTextView.setText(Constants.CLASS_1A);
        MainActivity.imgSlider.setVisibility(View.VISIBLE);
        // MainActivity.imgSearch.setVisibility(View.VISIBLE);
        // MainActivity.imgCheckbox.setVisibility(View.VISIBLE);
        MainActivity.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(thisActivity, "Clicked", Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStackImmediate();
            }
        });

        initialize();
        return mView;
    }

    private void initialize() {
        studentListView = (ListView) mView.findViewById(R.id.studentListView);
        //saveBtn = (Button) mView.findViewById(R.id.saveSutdentListBtn);
        //saveBtn.setOnClickListener(this);
        alertText = (TextView) mView.findViewById(R.id.alertText);

        if (AppPreferences.getAddStudentList(thisActivity).equals("")) {
            alertText.setVisibility(View.VISIBLE);
            alertText.setText(Constants.ALERT_TEXT);
        } else {
            alertText.setVisibility(View.INVISIBLE);
            String json = AppPreferences.getAddStudentList(thisActivity);
            System.out.println("JSON" + json);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<TeacherAddStudent>>() {
            }.getType();
            ArrayList<TeacherAddStudent> studentList = gson.fromJson(json, type);
            studentListAdapter = new StudentListAdapter(thisActivity, studentList);
            studentListView.setAdapter(studentListAdapter);
        }
    }

    @Override
    public void onClick(View v) {

        //  switch (v.getId()) {
        // case R.id.saveSutdentListBtn:
        //  Toast.makeText(thisActivity, "Details Saved", Toast.LENGTH_SHORT).show();
        //  break;
        //}
    }
}
