package com.tresflex.schoolapp.teacherfragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tresflex.schoolapp.R;
import com.tresflex.schoolapp.activity.MainActivity;
import com.tresflex.schoolapp.adapter.SubjectListAdapter;
import com.tresflex.schoolapp.helper.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeWorkFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeWorkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeWorkFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View mView;
    ListView SubjectListView;
    Activity thisActivity;
    SubjectListAdapter subjectListAdapter;
    List<String> subjectList = new ArrayList<String>();
    String[] subject_list = {"Maths", "English", "Science", "Geography", "Social Studies"};

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeWorkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeWorkFragment newInstance(String param1, String param2) {
        HomeWorkFragment fragment = new HomeWorkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeWorkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home_work, container, false);
        MainActivity.mTitleTextView.setText(Constants.HOMEWORK);
        MainActivity.imgSlider.setVisibility(View.VISIBLE);
        thisActivity = getActivity();
        initialize();
        return mView;
    }

    public void initialize(){
        for(int i=0; i < subject_list.length; i++){
            subjectList.add(subject_list[i]);
        }
        SubjectListView = (ListView) mView.findViewById(R.id.HomeWorkListView);
        subjectListAdapter = new SubjectListAdapter(thisActivity, subjectList);
        SubjectListView.setAdapter(subjectListAdapter);

        SubjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        SubjectFragment subjectFragment = new SubjectFragment();
                        FragmentTransaction subjectTransaction = getFragmentManager()
                                .beginTransaction();
                        subjectTransaction.replace(R.id.container, subjectFragment, Constants.MATHS);
                        subjectTransaction.addToBackStack(Constants.MATHS);
                        subjectTransaction.commit();
                        break;
                    case 1:
                        SubjectFragment subject1Fragment = new SubjectFragment();
                        FragmentTransaction subject1Transaction = getFragmentManager()
                                .beginTransaction();
                        subject1Transaction.replace(R.id.container, subject1Fragment, Constants.MATHS);
                        subject1Transaction.addToBackStack(Constants.MATHS);
                        subject1Transaction.commit();
                        Toast.makeText(thisActivity, Constants.CLASS_1B, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        SubjectFragment subject2Fragment = new SubjectFragment();
                        FragmentTransaction subject2Transaction = getFragmentManager()
                                .beginTransaction();
                        subject2Transaction.replace(R.id.container, subject2Fragment, Constants.MATHS);
                        subject2Transaction.addToBackStack(Constants.MATHS);
                        subject2Transaction.commit();
                        Toast.makeText(thisActivity, Constants.CLASS_1C, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        SubjectFragment subject3Fragment = new SubjectFragment();
                        FragmentTransaction subject3Transaction = getFragmentManager()
                                .beginTransaction();
                        subject3Transaction.replace(R.id.container, subject3Fragment, Constants.MATHS);
                        subject3Transaction.addToBackStack(Constants.MATHS);
                        subject3Transaction.commit();
                        Toast.makeText(thisActivity, Constants.CLASS_1C, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        SubjectFragment subject4Fragment = new SubjectFragment();
                        FragmentTransaction subject4Transaction = getFragmentManager()
                                .beginTransaction();
                        subject4Transaction.replace(R.id.container, subject4Fragment, Constants.MATHS);
                        subject4Transaction.addToBackStack(Constants.MATHS);
                        subject4Transaction.commit();
                        Toast.makeText(thisActivity, Constants.CLASS_1C, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }



}
