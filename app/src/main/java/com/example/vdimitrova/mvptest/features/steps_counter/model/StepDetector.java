package com.example.vdimitrova.mvptest.features.steps_counter.model;

import com.example.vdimitrova.mvptest.features.steps_counter.presenter.StepListener;

/**
 * Created by vdimitrova on 21.06.17.
 * <p>
 * MVPTest
 */

public class StepDetector {
    private static final int SENSOR_RING_SIZE = 50;
    private static final int VELOCITY_RING_SIZE = 10;

    // change this threshold according to your sensitivity preferences
    // Decrease the threshold to increase the sensitivity.
    private static final float STEP_THRESHOLD = 50f;

    private static final int STEP_DELAY_NS = 250000000;

    private int sensorRingCounter = 0;
    private float[] sensorRingX = new float[SENSOR_RING_SIZE];
    private float[] sensorRingY = new float[SENSOR_RING_SIZE];
    private float[] sensorRingZ = new float[SENSOR_RING_SIZE];

    private int velocityRingCounter = 0;
    private float[] velRing = new float[VELOCITY_RING_SIZE];
    private long lastStepTimeNs = 0;
    private float oldVelocityEstimate = 0;

    private StepListener listener;

    public void registerListener(StepListener listener) {
        this.listener = listener;
    }


    public void updateAccel(long timeNs, float x, float y, float z) {
        float[] currentSensorParams = new float[3];
        currentSensorParams[0] = x;
        currentSensorParams[1] = y;
        currentSensorParams[2] = z;

        // First step is to update our guess of where the global z vector is.
        sensorRingCounter++;
        sensorRingX[sensorRingCounter % SENSOR_RING_SIZE] = currentSensorParams[0];
        sensorRingY[sensorRingCounter % SENSOR_RING_SIZE] = currentSensorParams[1];
        sensorRingZ[sensorRingCounter % SENSOR_RING_SIZE] = currentSensorParams[2];

        // If you push the device toward the sky with an acceleration of A m/s2,
        // the z acceleration value is equal to A + 9.81, which corresponds to the acceleration of the device
        // (+A m/s2) minus the force of gravity (-9.81 m/s2).
        float[] sensorZ = new float[3];
        sensorZ[0] = StepsFilter.sum(sensorRingX) / Math.min(sensorRingCounter, SENSOR_RING_SIZE);
        sensorZ[1] = StepsFilter.sum(sensorRingY) / Math.min(sensorRingCounter, SENSOR_RING_SIZE);
        sensorZ[2] = StepsFilter.sum(sensorRingZ) / Math.min(sensorRingCounter, SENSOR_RING_SIZE);

        float normalizationFactor = StepsFilter.norm(sensorZ);

        sensorZ[0] = sensorZ[0] / normalizationFactor;
        sensorZ[1] = sensorZ[1] / normalizationFactor;
        sensorZ[2] = sensorZ[2] / normalizationFactor;

        float currentZ = StepsFilter.filterCurrentZ(sensorZ, currentSensorParams) - normalizationFactor;
        velocityRingCounter++;
        velRing[velocityRingCounter % VELOCITY_RING_SIZE] = currentZ;

        float velocityEstimate = StepsFilter.sum(velRing);

        if (velocityEstimate > STEP_THRESHOLD &&
                oldVelocityEstimate <= STEP_THRESHOLD &&
                (timeNs - lastStepTimeNs > STEP_DELAY_NS)) {

            listener.step(timeNs);
            lastStepTimeNs = timeNs;
        }

        oldVelocityEstimate = velocityEstimate;
    }
}
