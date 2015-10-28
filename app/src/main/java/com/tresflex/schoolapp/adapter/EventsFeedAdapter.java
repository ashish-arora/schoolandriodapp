package com.tresflex.schoolapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.model.EventsFeed;

import java.util.List;

/**
 * Created by Admin on 20-10-2015.
 */
public class EventsFeedAdapter extends BaseAdapter {

    private final List<EventsFeed> eventsList;
    private final Context context;


    public EventsFeedAdapter(Context context, List<EventsFeed> eventsList) {
        // super(context, R.layout.row_reward, rewardAL);
        this.context = context;
        this.eventsList = eventsList;
    }

    @Override
    public int getCount() {
        return eventsList.size();
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
            rowView = inflater.inflate(R.layout.events_feed_list_item, parent, false);
            ImageView homeImg = (ImageView) rowView.findViewById(R.id.homeImg);
            ImageView schoolImg = (ImageView) rowView.findViewById(R.id.imgSchool);
            TextView titleTxt = (TextView) rowView.findViewById(R.id.titleTxt);
            TextView descriptionTxt = (TextView) rowView.findViewById(R.id.descriptionTxt);
            TextView dateTxt = (TextView) rowView.findViewById(R.id.dateTxt);
            TextView dateTxt1 = (TextView) rowView.findViewById(R.id.dateTxt1);

            //  Picasso.with(context).load(eventsList.get(position).getHomeUrl()).into( homeImg);
            //  Picasso.with(context).load(eventsList.get(position).getSchoolUrl()).into(schoolImg);
            homeImg.setImageResource(eventsList.get(position).getHomeImg());
            schoolImg.setImageResource(eventsList.get(position).getSchoolImg());
            titleTxt.setText(eventsList.get(position).getTitleStr());
            descriptionTxt.setText(eventsList.get(position).getDescriptionStr());
            dateTxt.setText(eventsList.get(position).getDateStr());
            dateTxt1.setText(eventsList.get(position).getDateStr1());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowView;
    }
}
