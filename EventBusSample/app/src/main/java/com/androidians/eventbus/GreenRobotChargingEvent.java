package com.androidians.eventbus;

/**
 * Created by vichu on 09/10/17.
 */

public class GreenRobotChargingEvent {

    String data;

    public GreenRobotChargingEvent(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
