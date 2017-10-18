package com.androidians.eventbussample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidians.eventbus.GreenRobotChargingEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class LauncherActivity extends AppCompatActivity {

    // for Green Robot
    private EventBus eventBus = EventBus.getDefault();

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        tv = new TextView(this);
        tv.setTextSize(20f);
        tv.setTextColor(Color.BLUE);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        setContentView(tv, params);



        // for Green Robot
        // register as a subscriber
        eventBus.register(this);
    }

    @Override
    protected void onDestroy() {
        // for Green Robot
        eventBus.unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onEvent(GreenRobotChargingEvent event) {
        tv.setText(tv.getText() + "\n"+ event.getData());
    }
}
