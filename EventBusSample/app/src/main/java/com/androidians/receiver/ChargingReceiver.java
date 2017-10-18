package com.androidians.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.androidians.eventbus.GreenRobotChargingEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vichu on 09/10/17.
 */

public class ChargingReceiver extends BroadcastReceiver {

    public static final String THIS_DEVICE_STARTED = "this device started ";
    EventBus eventBus = EventBus.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        GreenRobotChargingEvent greenRobotChargingEvent = null;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh mm ss");
        String time = format.format(date) + " : ";

        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_CONNECTED)) {
            greenRobotChargingEvent = new GreenRobotChargingEvent(time + THIS_DEVICE_STARTED + "charging");
        } else if (intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_DISCONNECTED)) {
            greenRobotChargingEvent = new GreenRobotChargingEvent(time + THIS_DEVICE_STARTED + "discharging");
        }

        // post the event
        if (greenRobotChargingEvent != null)
            eventBus.post(greenRobotChargingEvent);
    }
}
