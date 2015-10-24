package com.tresflex.schoolapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.model.TeacherAddStudent;
import java.util.ArrayList;

/**
 * Created by Admin on 22-10-2015.
 */
public class TakeAttendanceAdapter extends BaseAdapter {

    Context context;
    ArrayList<TeacherAddStudent> yourChildList;

    public TakeAttendanceAdapter(Activity thisActivity, ArrayList<TeacherAddStudent> yourChildList) {
        this.context = thisActivity;
        this.yourChildList = yourChildList;
    }

    @Override
    public int getCount() {
        return yourChildList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = null;

        try {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.take_attendance_student_list_item, parent, false);

            TextView nameTxt = (TextView) rowView.findViewById(R.id.nameTxtT);
            TextView rollNoTxt = (TextView) rowView.findViewById(R.id.rollNotxtT);

            nameTxt.setText(yourChildList.get(position).getStudentName());
            rollNoTxt.setText(yourChildList.get(position).getRollNo().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowView;
    }
}
