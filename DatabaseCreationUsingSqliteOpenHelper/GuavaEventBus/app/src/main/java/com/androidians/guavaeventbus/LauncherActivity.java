package com.androidians.guavaeventbus;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidians.guavaeventbus.eventbus.MultipleEventListener;
import com.google.common.eventbus.EventBus;

public class LauncherActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }
}
