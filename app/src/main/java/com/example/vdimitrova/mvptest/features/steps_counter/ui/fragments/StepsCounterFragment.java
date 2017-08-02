package com.example.vdimitrova.mvptest.features.steps_counter.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vdimitrova.mvptest.R;
import com.example.vdimitrova.mvptest.base.fragments.BaseFragment;
import com.example.vdimitrova.mvptest.features.steps_counter.presenter.StepsCounterPresenter;
import com.example.vdimitrova.mvptest.features.steps_counter.view.StepsCounterViewContractor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vdimitrova on 21.06.17.
 * <p>
 * MVPTest
 */

public class StepsCounterFragment extends BaseFragment implements StepsCounterViewContractor {
    @BindView(R.id.step_counter)
    TextView stepCounter;
    @BindView(R.id.btn_start_stop)
    Button btnManageCounter;
    StepsCounterPresenter presenter;

    private static final String TEXT_NUM_STEPS = "Number of Steps: ";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_steps_counter, container, false);
        ButterKnife.bind(this, layout);
        presenter = new StepsCounterPresenter(getContext(), this);

        return layout;
    }

    @OnClick(R.id.btn_start_stop)
    void onClick() {
        presenter.onStepBtnClick();
    }

    @Override
    public void setStepCounterText() {
        int steps = presenter.getNumSteps();
        stepCounter.setText(getResources().getString(R.string.txt_pedometer_steps, steps));
    }

    @Override
    public void setStepButtonTxt(int res) {
        btnManageCounter.setText(res);
    }
}
