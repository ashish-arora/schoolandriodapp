package com.tresflex.schoolapp.teacherfragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tresflex.schoolapp.R;

import com.tresflex.schoolapp.activity.BaseFragment;
import com.tresflex.schoolapp.activity.MainActivity;
import com.tresflex.schoolapp.adapter.SubjectListAdapter;
import com.tresflex.schoolapp.helper.Constants;
import com.tresflex.schoolapp.teacherfragments.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class SubjectFragment extends BaseFragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<String> homeworkList = new ArrayList<String>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView SubjectListView;
    Activity thisActivity;
    View mView;
    SubjectListAdapter subjectListAdapter;
    String[] homework_list={"Complete question 1 and 2 of chapter 1", "Complete question 1 and 2 of chapter 1", "Complete question 3 and 4 of chapter 1"};


    /**
     * The fragment's ListView/GridView.
     */
    private ListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static SubjectFragment newInstance(String param1, String param2) {
        SubjectFragment fragment = new SubjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SubjectFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_work, container, false);

        //mListView = (ListView) view.findViewById(android.R.id.list);
        //((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        //mListView.setOnItemClickListener(this);

        thisActivity = getActivity();
        //thisActivity.setTitle();
        MainActivity.mTitleTextView.setText(Constants.MATHS);
        MainActivity.imgSlider.setVisibility(View.VISIBLE);
        // Set the adapter
        initialize();
        return mView;
    }

    public void initialize(){
        for(int i=0; i < homework_list.length; i++){
            homeworkList.add(homework_list[i]);
        }
        SubjectListView = (ListView) mView.findViewById(R.id.HomeWorkListView);
        subjectListAdapter = new SubjectListAdapter(thisActivity, homeworkList);
        SubjectListView.setAdapter(subjectListAdapter);
    }


}
