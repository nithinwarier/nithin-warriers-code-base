package com.androidians.guavaeventbus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.androidians.guavaeventbus.eventbus.MultipleEventListener;
import com.google.common.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vichu on 09/10/17.
 */

public class ChargingEventReceiver extends BroadcastReceiver {

    public static final String THIS_DEVICE_STARTED = "this device started ";
    private EventBus eventBus = new EventBus();

    @Override
    public void onReceive(Context context, Intent intent) {

        eventBus.register(new MultipleEventListener());
        eventBus.post(context);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh mm ss");
        String time = format.format(date) + " : ";

        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_CONNECTED)) {
            eventBus.post(time+THIS_DEVICE_STARTED + "charging");
        } else if (intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_DISCONNECTED)) {
            eventBus.post(time+THIS_DEVICE_STARTED + "discharging");
        }
    }
}
