package com.example.vdimitrova.mvptest.base.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.vdimitrova.mvptest.base.activities.BaseActivity;

/**
 * Created by vdimitrova on 12.05.17.
 */

public class BaseFragment extends Fragment {

    private BaseActivity mBaseActivity;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            this.mBaseActivity = (BaseActivity) context;
            this.context = context;
        }
    }

    public BaseActivity getBaseActivity() {
        return this.mBaseActivity;
    }

    public Context getContext() {
        return this.context;
    }

    /**
     * Method for replacing fragment and add to backstack
     *
     * @param fragment
     * @param name
     * @param resContainer
     */
    public void showFragmentAndAddToBackStack(Fragment fragment, String name, int resContainer) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(resContainer, fragment);
        transaction.addToBackStack(name);
        transaction.commit();
    }

    /**
     * Method for showing the fragment without clearing the BackStack or adding to BackStack
     *
     * @param fragment
     */
    public void showFragment(Fragment fragment, int resContainer) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(resContainer, fragment);
        transaction.commit();
    }
}
