package com.tresflex.schoolapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.model.EventsFeed;
import com.tresflex.schoolapp.model.TeacherAddStudent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22-10-2015.
 */
public class StudentListAdapter extends BaseAdapter {

    private final ArrayList<TeacherAddStudent> studentList;
    private final Context context;

    public StudentListAdapter(Context context, ArrayList<TeacherAddStudent> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
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
            rowView = inflater.inflate(R.layout.student_list_item, parent, false);
            ImageView studentListImg = (ImageView) rowView.findViewById(R.id.studentListImg);
            TextView rollNo = (TextView) rowView.findViewById(R.id.rollNoList);
            TextView studentName = (TextView) rowView.findViewById(R.id.NameList);

            //  Picasso.with(context).load(eventsList.get(position).getSchoolUrl()).into(schoolImg);
            studentListImg.setImageResource(R.drawable.ic_close);
            rollNo.setText(studentList.get(position).getRollNo().toString());
            studentName.setText(studentList.get(position).getStudentName());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowView;
    }
}
