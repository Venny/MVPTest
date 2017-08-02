package com.example.vdimitrova.mvptest.features.single_event.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vdimitrova.mvptest.R;
import com.example.vdimitrova.mvptest.base.fragments.BaseFragment;
import com.example.vdimitrova.mvptest.base.model.custom_events.JustASimpleTestEvent;
import com.example.vdimitrova.mvptest.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by vdimitrova on 18.05.17.
 */

public class SingleEventFragment extends BaseFragment {
    @BindView(R.id.event_name)
    TextView eventName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_event, container, false);
        ButterKnife.bind(this, view);

        init();

        return view;
    }

    private void init() {
        int positionId = getArguments().getInt(Constants.KEY_POSITION);
        eventName.setText(String.valueOf(positionId));
    }

    @Override
    public void onResume() {
        super.onResume();

        EventBus.getDefault().postSticky(new JustASimpleTestEvent("Simple event test"));
    }
}
