package com.tresflex.schoolapp.parentsfragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.activity.BaseFragment;
import com.tresflex.schoolapp.activity.MainActivity;
import com.tresflex.schoolapp.adapter.EventsFeedAdapter;
import com.tresflex.schoolapp.helper.Constants;
import com.tresflex.schoolapp.model.EventsFeed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 20-10-2015.
 */
public class EventsFeedFragment extends BaseFragment {

    View mView;
    Activity thisActivity;
    Bundle savedInstanceState;
    ListView eventsFeedListView;
    ImageView imgEventsFeed;
    EventsFeedAdapter eventsFeedAdapter;
    List<EventsFeed> eventsFeedList = new ArrayList<EventsFeed>();
    EventsFeed eventsFeed = new EventsFeed();

    int[] homeImg = {R.drawable.desert, R.drawable.desert, R.drawable.desert, R.drawable.desert, R.drawable.desert};
    int[] schoolImg = {R.drawable.desert, R.drawable.hydrangeas, R.drawable.lighthouse, R.drawable.desert, R.drawable.hydrangeas};
    String[] title = {"House Swimming Carnival 20th August", "Camp bus is running late. ETA 4.45", "Kinder Room update 30 jul", "House Swimming Carnival 20th August", "Camp bus is running late. ETA 4.45"};
    String[] tiledescription = {"The House Swimming Carnival will be soon upon us ", "Due to late departure the bus from the year 3 camp is not due until 4.45pm", "The Childre have lovely day today", "The House Swimming Carnival will be soon upon us ", "House Swimming Carnival 20th August"};
    String[] date1 = {"Primary School", "Primary School", "Primary School", "Primary School", "Primary School"};
    String[] date2 = {"23-jul-2013", "23-jul-2013", "23-jul-2013", "23-jul-2013", "23-jul-2013"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.savedInstanceState = savedInstanceState;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_events_feed, container, false);
        thisActivity = getActivity();
        MainActivity.mActionBar.show();
        MainActivity.mTitleTextView.setText(Constants.EVENTS_FEED);
        MainActivity.imgSlider.setVisibility(View.GONE);
        initialize();
        return mView;
    }

    private void initialize() {
        imgEventsFeed = (ImageView) mView.findViewById(R.id.imgEventsFeed);
        eventsFeedListView = (ListView) mView.findViewById(R.id.eventsFeedList);
//        MainActivity.imgEventsFeed.setVisibility(View.VISIBLE);

        for (int i = 0; i < schoolImg.length; i++) {
            eventsFeed.setTitleStr(title[i]);
            eventsFeed.setDescriptionStr(tiledescription[i]);
            eventsFeed.setHomeImg(homeImg[i]);
            eventsFeed.setSchoolImg(schoolImg[i]);
            eventsFeed.setDateStr(date1[i]);
            eventsFeed.setDateStr1(date2[i]);
            eventsFeedList.add(eventsFeed);
        }

        eventsFeedAdapter = new EventsFeedAdapter(thisActivity, eventsFeedList);
        eventsFeedListView.setAdapter(eventsFeedAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mView != null) {
            ViewGroup parentViewGroup = (ViewGroup) mView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }
    }
}
