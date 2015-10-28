package com.tresflex.schoolapp.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.tresflex.schoolapp.helper.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * BaseFragment is a super class for all fragments in the application also have
 * common members and methods which can be reused across the application
 *
 * @author Durai
 */

public abstract class BaseFragment extends Fragment {
    ProgressDialog mProgressDialog;
    AlertDialog.Builder mAlertDialogBuilder;
    private Activity thisActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        thisActivity = getActivity();
        mAlertDialogBuilder = new AlertDialog.Builder(getActivity());
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
    }

    protected boolean isConnectedToNetwork() {
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public void showAlertDialog(String message, String title) {
        if (title != null) {
            mAlertDialogBuilder.setTitle(title);
        }
        mAlertDialogBuilder.setMessage(message);
        mAlertDialogBuilder.setCancelable(false);
        mAlertDialogBuilder.setNegativeButton(Constants.OK,
                new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = mAlertDialogBuilder.create();
        alertDialog.show();
    }

    public void showProgressDialog(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public String convertMilliSecondToDisplayFormat(String inputDate) {
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MMM-yy");
        long milliSeconds = Long.parseLong(inputDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return targetFormat.format(calendar.getTime());
    }

    public void setCurrentNavigationTitle() {
        ActionBar actionBar = thisActivity.getActionBar();
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void setPreviousNavigationTitle(String title) {
        ActionBar actionBar = thisActivity.getActionBar();
        actionBar.setTitle(title);
    }

}

