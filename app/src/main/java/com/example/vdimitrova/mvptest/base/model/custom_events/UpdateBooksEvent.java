package com.example.vdimitrova.mvptest.base.model.custom_events;

/**
 * Created by vdimitrova on 29.06.17.
 * <p>
 * MVPTest
 */

public class UpdateBooksEvent {
    private final boolean isReadyForUpdate;

    public UpdateBooksEvent(boolean isReadyForUpdate) {
        this.isReadyForUpdate = isReadyForUpdate;
    }

    public boolean isReadyForUpdate() {
        return isReadyForUpdate;
    }
}
