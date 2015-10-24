package com.tresflex.schoolapp.parentsfragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.activity.MainActivity;
import com.tresflex.schoolapp.adapter.YourChildAdapter;
import com.tresflex.schoolapp.helper.Constants;
import com.tresflex.schoolapp.model.YourChild;

import java.util.ArrayList;
import java.util.List;

public class YourChildFragment extends Fragment {

    View mView;
    Activity thisActivity;
    Bundle savedInstanceState;
    ListView yourChidListView;
    YourChildAdapter yourChildAdapter;
    List<YourChild> yourChildList = new ArrayList<YourChild>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static YourChildFragment newInstance(String param1, String param2) {
        YourChildFragment fragment = new YourChildFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public YourChildFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_your_child, container, false);
        thisActivity = getActivity();
        MainActivity.mTitleTextView.setText(Constants.YOUR_CHILD);
        initialize();
        return mView;
    }

    private void initialize() {
        yourChidListView = (ListView) mView.findViewById(R.id.yourChildListView);
        yourChildAdapter = new YourChildAdapter(thisActivity, yourChildList);
        yourChidListView.setAdapter(yourChildAdapter);
    }

}
