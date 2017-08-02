package com.example.vdimitrova.mvptest.features.steps_counter.presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.vdimitrova.mvptest.R;
import com.example.vdimitrova.mvptest.features.steps_counter.model.StepDetector;
import com.example.vdimitrova.mvptest.features.steps_counter.view.StepsCounterViewContractor;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by vdimitrova on 15.05.17.
 */

public class StepsCounterPresenter implements SensorEventListener, StepListener {
    private StepsCounterViewContractor view;

    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor sensor;
    private int numSteps;
    private boolean isCounterOff;

    public StepsCounterPresenter(Context context, StepsCounterViewContractor view) {
        this.view = view;

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        isCounterOff = true;
        registerStepDetectorListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void step(long timeNs) {
        addStep();
    }

    public int getNumSteps(){
        return numSteps;
    }

    public void onStepBtnClick(){
        if (isCounterOff) {
            isCounterOff = false;
            numSteps = 0;
            view.setStepButtonTxt(R.string.txt_stop_pedometer);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);

        } else {
            isCounterOff = true;
            view.setStepButtonTxt(R.string.txt_start_pedometer);
            sensorManager.unregisterListener(this);
        }
    }

    private void addStep(){
        numSteps++;
        view.setStepCounterText();
    }

    private void registerStepDetectorListener(StepListener listener) {
        simpleStepDetector.registerListener(listener);
    }
}
