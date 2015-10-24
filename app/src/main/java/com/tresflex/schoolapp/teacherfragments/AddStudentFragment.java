package com.tresflex.schoolapp.teacherfragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.activity.BaseFragment;
import com.tresflex.schoolapp.activity.MainActivity;
import com.tresflex.schoolapp.helper.AppPreferences;
import com.tresflex.schoolapp.helper.Constants;
import com.tresflex.schoolapp.model.TeacherAddStudent;

import java.util.ArrayList;

/**
 * Created by Admin on 20-10-2015.
 */
public class AddStudentFragment extends BaseFragment implements View.OnClickListener {
    View mView;
    Activity thisActivity;
    Bundle savedInstanceState;
    Button saveBtn;
    EditText name, rollNo, fatherName, motherName, fatherMobileNo, motherMobileNo;
    String nameStr, fatherNameStr, motherNameStr;
    Long rollNoStr, fatherMobileNoStr, motherMobileNoStr;
    View.OnTouchListener focusHandler;
    TeacherAddStudent teacherAddStudent = new TeacherAddStudent();
    ArrayList<TeacherAddStudent> teacherAddStudentList = new ArrayList<TeacherAddStudent>();
    AlertDialog.Builder mAlertDialogBuilder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_add_student, container, false);
        thisActivity = getActivity();
        MainActivity.mTitleTextView.setText(Constants.CLASS_1A);
        MainActivity.imgSlider.setVisibility(View.VISIBLE);
        MainActivity.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(thisActivity, "Clicked", Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStackImmediate();
            }
        });
        initialize(focusHandler);
        //AppPreferences.setAddStudentList(thisActivity, "");
        return mView;
    }

    private void initialize(View.OnTouchListener focusHandler) {

        name = (EditText) mView.findViewById(R.id.txtName);
        rollNo = (EditText) mView.findViewById(R.id.txtRollNo);
        fatherName = (EditText) mView.findViewById(R.id.txtFathersName);
        fatherMobileNo = (EditText) mView.findViewById(R.id.txtFathersNo);
        motherName = (EditText) mView.findViewById(R.id.txtMothersName);
        motherMobileNo = (EditText) mView.findViewById(R.id.txtMothersNo);
        saveBtn = (Button) mView.findViewById(R.id.saveAddStudentBtn);

       /* name.setOnTouchListener(focusHandler);
        rollNo.setOnTouchListener(focusHandler);
        fatherName.setOnTouchListener(focusHandler);
        fatherMobileNo.setOnTouchListener(focusHandler);
        motherName.setOnTouchListener(focusHandler);
        motherMobileNo.setOnTouchListener(focusHandler);*/
        saveBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.saveAddStudentBtn:
                saveStudentDetails();
                break;
        }
    }

    private void saveStudentDetails() {

        nameStr = name.getText().toString();
        fatherNameStr = fatherName.getText().toString();
        motherNameStr = motherName.getText().toString();
        try {
            rollNoStr = Long.parseLong(rollNo.getText().toString());
            fatherMobileNoStr = Long.parseLong(fatherMobileNo.getText().toString());
            motherMobileNoStr = Long.parseLong(motherMobileNo.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (TextUtils.isEmpty(nameStr)) {
            name.setError(Html.fromHtml("<font color='black'>"
                    + Constants.ENTER_NAME + "</font>"));
            name.requestFocus();
        } else if (TextUtils.isEmpty(fatherNameStr)) {
            fatherName.setError(Html.fromHtml("<font color='black'>"
                    + Constants.ENTER_FATHERS_NAME + "</font>"));
            fatherName.requestFocus();
        } else if (TextUtils.isEmpty(motherNameStr)) {
            motherName.setError(Html.fromHtml("<font color='black'>"
                    + Constants.ENTER_MOTHERS_NAME + "</font>"));
            motherName.requestFocus();
        } else if (TextUtils.isEmpty(rollNo.getText().toString())) {
            rollNo.setError(Html.fromHtml("<font color='black'>"
                    + Constants.ENTER_RollNo + "</font>"));
            rollNo.requestFocus();
        } else if (TextUtils.isEmpty(fatherMobileNo.getText().toString())) {
            fatherMobileNo.setError(Html.fromHtml("<font color='black'>"
                    + Constants.ENTER_FATHERS_No + "</font>"));
            fatherMobileNo.requestFocus();
        } else if (TextUtils.isEmpty(motherMobileNo.getText().toString())) {
            motherMobileNo.setError(Html.fromHtml("<font color='black'>"
                    + Constants.ENTER_MOTHERS_No + "</font>"));
            motherMobileNo.requestFocus();
        } else {
            teacherAddStudent.setStudentName(nameStr);
            teacherAddStudent.setRollNo(rollNoStr);
            teacherAddStudent.setFatherName(fatherNameStr);
            teacherAddStudent.setMotherName(motherNameStr);
            teacherAddStudent.setFatherMobileNo(fatherMobileNoStr);
            teacherAddStudent.setMotherMobileNo(motherMobileNoStr);
            teacherAddStudentList.add(teacherAddStudent);

            Gson gson = new Gson();
            String json = gson.toJson(teacherAddStudentList);
            System.out.println(json);
            AppPreferences.setAddStudentList(thisActivity, json);

            name.setText("");
            rollNo.setText("");
            fatherName.setText("");
            motherName.setText("");
            fatherMobileNo.setText("");
            motherMobileNo.setText("");

            System.out.println("Name : " + teacherAddStudent.getStudentName() + "  Roll Number : " + teacherAddStudent.getRollNo() + "  Father's Name : " + teacherAddStudent.getFatherName() + "  Mother's Name : " + teacherAddStudent.getMotherName() +
                    "  Father Mobile number : " + teacherAddStudent.getFatherMobileNo() + "  Mother Mobile Number : " + teacherAddStudent.getMotherMobileNo());
        }
    }
}
