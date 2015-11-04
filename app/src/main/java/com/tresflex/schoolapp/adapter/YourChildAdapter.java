package com.tresflex.schoolapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.model.YourChild;

import java.util.List;

/**
 * Created by Admin on 21-10-2015.
 */
public class YourChildAdapter extends BaseAdapter {

    Context context;
    List<YourChild> yourChildList;

    public YourChildAdapter(Activity thisActivity, List<YourChild> yourChildList) {
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
            rowView = inflater.inflate(R.layout.your_child_list_item, parent, false);

            RadioButton yourChildRadioBtn = (RadioButton) rowView.findViewById(R.id.yourChildRadioBtn);
            TextView childNameTxt = (TextView) rowView.findViewById(R.id.childNameTxt);
            TextView teacherClassTxt = (TextView) rowView.findViewById(R.id.teacherClassTxt);
            TextView schoolTxt = (TextView) rowView.findViewById(R.id.schoolTxt);
            TextView presenttodayTxt = (TextView) rowView.findViewById(R.id.presenttodayTxt);

            childNameTxt.setText(yourChildList.get(position).getChildName());
            teacherClassTxt.setText(yourChildList.get(position).getTeacherClass());
            schoolTxt.setText(yourChildList.get(position).getSchool());
            presenttodayTxt.setText(yourChildList.get(position).getPresentToday());
            /*if (yourChildList.get(position).getPresentToday().equalsIgnoreCase("Yes")) {
                yourChildRadioBtn.setChecked(true);
            } else {
                yourChildRadioBtn.setChecked(false);
            }*/
            if (position == 0) {
                yourChildRadioBtn.setChecked(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowView;
    }
}
