package com.tresflex.schoolapp.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.helper.AppPreferences;
import com.tresflex.schoolapp.helper.Constants;
import com.tresflex.schoolapp.parentsfragments.AttendanceFragment;
import com.tresflex.schoolapp.parentsfragments.EventsFeedFragment;
import com.tresflex.schoolapp.parentsfragments.YourChildFragment;
import com.tresflex.schoolapp.teacherfragments.ClassesFragment;
import com.tresflex.schoolapp.teacherfragments.TakeAttendanceFragment;

public class MainActivity extends Activity {
    public static android.app.ActionBar mActionBar;
    public static TextView mTitleTextView;
    public static ImageView imgSlider;
    public static ImageView imgSearch;
    public static ImageView imgEventsFeed;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Constants.ACTION_BAR_COLOR)));

        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.fragment_custom_actionbar, null);
        mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        //mTitleTextView.setText(getResources().getString(R.string.app_name));
        mTitleTextView.setText(Constants.EVENTS_FEED);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        imgSlider = (ImageView) mCustomView.findViewById(R.id.imgSlideManu);
        imgSearch = (ImageView) mCustomView.findViewById(R.id.imgSearch);
        imgEventsFeed = (ImageView) mCustomView.findViewById(R.id.imgEventsFeed);
        imgSearch.setVisibility(View.GONE);
        imgEventsFeed.setVisibility(View.GONE);
        imgSearch.setImageResource(R.drawable.ic_search);
        imgSlider.setImageResource(R.drawable.ic_arrow);

        AppPreferences.setUserType(context, Constants.TYPE_TEACHERS);
        // AppPreferences.setUserType(context, Constants.TYPE_CHILD);
        EventsFeedFragment eventsFeedFragment = new EventsFeedFragment();
        FragmentTransaction eventsTransaction = getFragmentManager()
                .beginTransaction();
        eventsTransaction.add(R.id.container, eventsFeedFragment, Constants.EVENTS_FEED);
        eventsTransaction.addToBackStack(Constants.EVENTS_FEED);
        eventsTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (AppPreferences.getUserType(context).equalsIgnoreCase(Constants.TYPE_CHILD)) {
            getMenuInflater().inflate(R.menu.menu_child, menu);
        } else if (AppPreferences.getUserType(context).equalsIgnoreCase(Constants.TYPE_TEACHERS)) {
            getMenuInflater().inflate(R.menu.menu_teachers, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_child, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (AppPreferences.getUserType(context).equalsIgnoreCase(Constants.TYPE_CHILD)) {
            int id = item.getItemId();

            switch (id) {
                case R.id.attendance:
                    AttendanceFragment attendanceFragment = new AttendanceFragment();
                    FragmentTransaction attendanceTransaction = getFragmentManager()
                            .beginTransaction();
                    attendanceTransaction.add(R.id.container, attendanceFragment, Constants.ATTENDANCE);
                    attendanceTransaction.commit();
                    break;
                case R.id.profile:
                    YourChildFragment yourChildFragment = new YourChildFragment();
                    FragmentTransaction childTransaction = getFragmentManager()
                            .beginTransaction();
                    childTransaction.add(R.id.container, yourChildFragment, Constants.YOUR_CHILD);
                    childTransaction.commit();
                    break;
                case R.id.events_feed:
                    EventsFeedFragment eventsFeedFragment = new EventsFeedFragment();
                    FragmentTransaction eventsTransaction = getFragmentManager()
                            .beginTransaction();
                    eventsTransaction.add(R.id.container, eventsFeedFragment, Constants.EVENTS_FEED);
                    //eventsTransaction.addToBackStack(Constants.EVENTS_FEED);
                    eventsTransaction.commit();
                    break;
                default:
                    break;
            }
        } else if (AppPreferences.getUserType(context).equalsIgnoreCase(Constants.TYPE_TEACHERS)) {
            int id = item.getItemId();

            switch (id) {
                case R.id.classes:
                    ClassesFragment classesFragment = new ClassesFragment();
                    FragmentTransaction classesTransaction = getFragmentManager()
                            .beginTransaction();
                    classesTransaction.add(R.id.container, classesFragment, Constants.CLASSES);
                    classesTransaction.addToBackStack(Constants.CLASSES);
                    classesTransaction.commit();
                    break;
                case R.id.take_attendance:
                    TakeAttendanceFragment takeAttendanceFragment = new TakeAttendanceFragment();
                    FragmentTransaction takeAttendanceTransaction = getFragmentManager()
                            .beginTransaction();
                    takeAttendanceTransaction.add(R.id.container, takeAttendanceFragment, Constants.TAKE_ATTENDANCE);
                    takeAttendanceTransaction.addToBackStack(Constants.TAKE_ATTENDANCE);
                    takeAttendanceTransaction.commit();
                    break;
                case R.id.show_attendance:
                    TakeAttendanceFragment showAttendanceFragment = new TakeAttendanceFragment();
                    FragmentTransaction showAttendanceTransaction = getFragmentManager()
                            .beginTransaction();
                    showAttendanceTransaction.add(R.id.container, showAttendanceFragment, Constants.TAKE_ATTENDANCE);
                    showAttendanceTransaction.addToBackStack(Constants.TAKE_ATTENDANCE);
                    showAttendanceTransaction.commit();
                    break;
                default:
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickEvent(View v) {

        switch (v.getId()) {
            case R.id.imgSlideManu:
                back();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        // TODO Auto-generated method stub
        back();
    }

    private void back() {
        System.out.println(Constants.BACK_STACK
                + getFragmentManager().getBackStackEntryCount());
        imgSlider.setVisibility(View.GONE);
        mTitleTextView.setText(Constants.EVENTS_FEED);
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            System.out.println(Constants.BACK_STACK + getFragmentManager().getBackStackEntryCount());
            getFragmentManager().popBackStackImmediate();
            for (int i = getFragmentManager().getBackStackEntryCount() - 1; i > 0; i--) {
                FragmentManager.BackStackEntry backStackEntry = getFragmentManager().getBackStackEntryAt(i);
                // Toast.makeText(MainActivity.this, "backStackEntry.getName()" + backStackEntry.getName(), Toast.LENGTH_SHORT).show();
                mTitleTextView.setText(backStackEntry.getName());
                imgSlider.setVisibility(View.VISIBLE);
            }
        } else {
            finish();
        }
    }
}
