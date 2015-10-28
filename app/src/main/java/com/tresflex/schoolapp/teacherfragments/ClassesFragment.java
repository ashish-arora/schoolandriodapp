package com.tresflex.schoolapp.teacherfragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.activity.BaseFragment;
import com.tresflex.schoolapp.activity.MainActivity;
import com.tresflex.schoolapp.adapter.TeacherClassAdapter;
import com.tresflex.schoolapp.helper.Constants;

import java.util.ArrayList;
import java.util.List;


public class ClassesFragment extends BaseFragment {

    View mView;
    Activity thisActivity;
    Bundle savedInstanceState;
    ListView teacherClassListView;
    TeacherClassAdapter teacherClassAdapter;
    //List<TeacherClasses> teacherClassList = new ArrayList<TeacherClasses>();
    List<String> teacherClassList = new ArrayList<String>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static ClassesFragment newInstance(String param1, String param2) {

        ClassesFragment fragment = new ClassesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ClassesFragment() {
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
        mView = inflater.inflate(R.layout.fragment_classes, container, false);
        thisActivity = getActivity();
        MainActivity.mTitleTextView.setText(Constants.CLASSES);
        MainActivity.imgSlider.setVisibility(View.VISIBLE);
        initialize();
        return mView;
    }

    private void initialize() {

        teacherClassList.add(Constants.CLASS_1A);
        teacherClassList.add(Constants.CLASS_1B);
        teacherClassList.add(Constants.CLASS_1C);
        teacherClassListView = (ListView) mView.findViewById(R.id.teacherClassListView);
        teacherClassAdapter = new TeacherClassAdapter(thisActivity, teacherClassList);
        teacherClassListView.setAdapter(teacherClassAdapter);

        teacherClassListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        ClassOneA classAFragment = new ClassOneA();
                        FragmentTransaction classeATransaction = getFragmentManager()
                                .beginTransaction();
                        classeATransaction.add(R.id.container, classAFragment, Constants.CLASS_1A);
                        classeATransaction.addToBackStack(Constants.CLASS_1A);
                        classeATransaction.commit();
                        break;
                    case 1:
                        ClassOneA classBFragment = new ClassOneA();
                        FragmentTransaction classeBTransaction = getFragmentManager()
                                .beginTransaction();
                        classeBTransaction.add(R.id.container, classBFragment, Constants.CLASS_1A);
                        classeBTransaction.addToBackStack(Constants.CLASS_1A);
                        classeBTransaction.commit();
                        Toast.makeText(thisActivity, Constants.CLASS_1B, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        ClassOneA classCFragment = new ClassOneA();
                        FragmentTransaction classeCTransaction = getFragmentManager()
                                .beginTransaction();
                        classeCTransaction.add(R.id.container, classCFragment, Constants.CLASS_1A);
                        classeCTransaction.addToBackStack(Constants.CLASS_1A);
                        classeCTransaction.commit();
                        Toast.makeText(thisActivity, Constants.CLASS_1C, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

}
