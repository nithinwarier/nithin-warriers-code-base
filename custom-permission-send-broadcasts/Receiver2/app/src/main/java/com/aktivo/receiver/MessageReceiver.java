package com.aktivo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("MessageReceiver", "message receiver invoked, getIntExtra: "
                +intent.getIntExtra("syncState", 0));
    }
}
