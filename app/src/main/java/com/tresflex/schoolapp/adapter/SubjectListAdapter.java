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
 * Created by ashish on 31/10/15.
 */

public class SubjectListAdapter extends BaseAdapter {

    Context context;
    List<String> subjectList;

    public SubjectListAdapter(Activity thisActivity, List<String> subjectList) {
        this.context = thisActivity;
        this.subjectList = subjectList;
    }

    @Override
    public int getCount() {
        return subjectList.size();
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
            rowView = inflater.inflate(R.layout.subject_list_item, parent, false);

            TextView subjectNameTxt = (TextView) rowView.findViewById(R.id.subjectName);
            subjectNameTxt.setText(subjectList.get(position));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowView;
    }
}

