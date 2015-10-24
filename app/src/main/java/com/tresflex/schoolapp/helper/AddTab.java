package com.tresflex.schoolapp.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.tresflex.schoolapp.R;

public class AddTab {
    Activity thisActivity;
    TabHost tabHost;
    TextView title;

    public AddTab(Activity thisActivity, TabHost tabHost) {
        this.thisActivity = thisActivity;
        this.tabHost = tabHost;
    }

    public void addTab(String tabName, String tabTag, int imageId) {
        ViewGroup tabsView = (ViewGroup) thisActivity.findViewById(android.R.id.tabs);
        TabHost.TabSpec spec = tabHost.newTabSpec(tabTag);
        View tabIndicator = thisActivity.getLayoutInflater().inflate(R.layout.simple_tab, tabsView, false);
        title = (TextView) tabIndicator.findViewById(R.id.tabTitle);
        ImageView imageTab = (ImageView) tabIndicator.findViewById(R.id.imageViewTab);
        title.setText(tabName);
//		title.setGravity(Gravity.CENTER);
        if (imageId == -1) {
            imageTab.setVisibility(View.GONE);
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT, thisActivity.getResources().getDimensionPixelSize(R.dimen.tab_height));
//			LayoutParams params = title.getLayoutParams();
//			layoutParams.height = 70;
            title.setLayoutParams(layoutParams);
        } else {
            imageTab.setImageResource(imageId);
        }
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        tabIndicator.setLayoutParams(layoutParams);
        spec.setContent(new TabFactory(thisActivity));
        spec.setIndicator(tabIndicator);
        tabHost.addTab(spec);
    }

    public static void setColor(String aTrue) {

    }

    class TabFactory implements TabContentFactory {
        private final Context mContext;

        public TabFactory(Context context) {
            mContext = context;
        }

        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }

    }
}
