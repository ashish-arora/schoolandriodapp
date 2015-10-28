package com.tresflex.schoolapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tresflex.schoolapp.R;

import java.util.List;

/**
 * Created by Admin on 21-10-2015.
 */
public class TeacherClassAdapter extends BaseAdapter {

    Context context;
    List<String> teacherClassesList;

    public TeacherClassAdapter(Activity thisActivity, List<String> yourChildList) {
        this.context = thisActivity;
        this.teacherClassesList = yourChildList;
    }

    @Override
    public int getCount() {
        return teacherClassesList.size();
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
            rowView = inflater.inflate(R.layout.classes_list_item, parent, false);

            TextView classNameTxt = (TextView) rowView.findViewById(R.id.teacherClassName);
            classNameTxt.setText(teacherClassesList.get(position));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowView;
    }
}
