package com.tresflex.schoolapp.teacherfragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.activity.BaseFragment;
import com.tresflex.schoolapp.activity.MainActivity;
import com.tresflex.schoolapp.adapter.PagerAdapter;
import com.tresflex.schoolapp.helper.AddTab;
import com.tresflex.schoolapp.helper.Constants;

import java.util.List;
import java.util.Vector;


public class ClassOneA extends BaseFragment implements TabHost.OnTabChangeListener {
    View mView;
    public static TabHost tabHost;
    Activity thisActivity;
    private ViewPager viewPager;
    PagerAdapter viewPagerAdapter;
    Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_teacher_classes, container, false);
        thisActivity = getActivity();
        MainActivity.mTitleTextView.setText(Constants.CLASS_1A);
        MainActivity.imgSlider.setVisibility(View.VISIBLE);
        initialize();
        return mView;
    }

    private void initialize() {
        initialiseTabHost();
        if (savedInstanceState != null) {
            tabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));

        }
        initializeViewPager();
        setSelectedTabColor();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initializeViewPager() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(thisActivity, StudentListFragment.class.getName()));
        fragments.add(Fragment.instantiate(thisActivity, AddStudentFragment.class.getName()));
        // viewPagerAdapter = new PagerAdapter(getFragmentManager(), fragments);
        viewPagerAdapter = new PagerAdapter(getChildFragmentManager(), fragments);
        viewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(onPageChange);
    }

    private void initialiseTabHost() {
        tabHost = (TabHost) mView.findViewById(android.R.id.tabhost);
        tabHost.setup();
        AddTab addTab = new AddTab(thisActivity, tabHost);
        addTab.addTab(Constants.STUDENT_LIST, Constants.STUDENT_LIST, -1);
        addTab.addTab(Constants.ADD_STUDENT, Constants.ADD_STUDENT, -1);
        tabHost.setOnTabChangedListener(this);
    }

    ViewPager.OnPageChangeListener onPageChange = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            tabHost.setCurrentTab(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void setSelectedTabColor() {

        //not selected
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {

            tabHost.getTabWidget().getChildAt(i).
                    setBackgroundResource(R.drawable.tab_bg);
          //  tabHost.getTabWidget().setDividerDrawable(R.drawable.layer_bg_selected_tabs_green);
        }

        //selected
       // tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab())
                .setBackgroundResource(R.drawable.tab_bg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tabHost = null;
    }

    @Override
    public void onTabChanged(String tabId) {
        int pos = tabHost.getCurrentTab();
        viewPager.setCurrentItem(pos);
        setSelectedTabColor();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

}
