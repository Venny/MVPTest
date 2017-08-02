package com.example.vdimitrova.mvptest.base.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by vdimitrova on 12.05.17.
 */

public class BaseActivity extends AppCompatActivity {
    /**
     * Method for replacing fragment in container
     *
     * @param fragment
     * @param resContainer
     */
    public void replaceFragment(Fragment fragment, int resContainer) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(resContainer, fragment);
        transaction.commit();
    }

    public void addFragment(Fragment fragment, int resContainer) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(resContainer, fragment);
        transaction.commit();
    }
}