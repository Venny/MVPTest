package com.example.vdimitrova.mvptest.base.model.custom_events;

/**
 * Created by vdimitrova on 21.06.17.
 * <p>
 * MVPTest
 */

public class JustASimpleTestEvent {
    private final String message;

    public JustASimpleTestEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
