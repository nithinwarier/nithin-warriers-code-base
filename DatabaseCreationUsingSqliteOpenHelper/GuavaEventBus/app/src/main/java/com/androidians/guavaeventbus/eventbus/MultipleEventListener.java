package com.androidians.guavaeventbus.eventbus;

import android.content.Context;
import android.widget.Toast;

import com.google.common.eventbus.Subscribe;

/**
 * Created by vichu on 09/10/17.
 */

public class MultipleEventListener {

    Context context;

    @Subscribe
    public void task0(Context context) {
        this.context = context;
    }

    @Subscribe
    public void task1(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

}
