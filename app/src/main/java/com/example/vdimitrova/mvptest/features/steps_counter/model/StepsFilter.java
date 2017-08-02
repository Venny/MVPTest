package com.example.vdimitrova.mvptest.features.steps_counter.model;

/**
 * Created by vdimitrova on 21.06.17.
 * <p>
 * MVPTest
 */

public class StepsFilter {

    public static float sum(float[] array) {
        float retval = 0;
        for (int i = 0; i < array.length; i++) {
            retval += array[i];
        }
        return retval;
    }

    public static float norm(float[] array) {
        float retval = 0;
        for (int i = 0; i < array.length; i++) {
            retval += array[i] * array[i];
        }

        return (float) Math.sqrt(retval);
    }


    public static float filterCurrentZ(float[] a, float[] b) {
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
    }
}
