package com.example.vdimitrova.mvptest.features;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.example.vdimitrova.mvptest.R;
import com.example.vdimitrova.mvptest.base.activities.BaseActivity;
import com.example.vdimitrova.mvptest.features.books_list.ui.fragments.BooksListFragment;


/**
 * Created by vdimitrova on 12.05.17.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new BooksListFragment();
        replaceFragment(fragment, R.id.main_container);
    }
}
