package com.aktivo.sender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendBroadcastBtn = findViewById(R.id.sendBroadcastBtn);
        sendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sendBroadcastBtn", "sendBroadcastBtn clicked");

                Intent intent = new Intent();
                intent.setAction("com.aktivo.receiver.broadcast");
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.putExtra("syncState", 1);
                intent.setPackage("com.aktivo.receiver");
                intent.setClassName("com.aktivo.receiver", "com.aktivo.receiver.MessageReceiver");
//                intent.setComponent(new ComponentName("com.aktivo.receiver", "com.aktivo.receiver.MessageReceiver"));
                sendBroadcast(intent, "com.aktivo.receiver.permission.BROADCAST");
            }
        });
    }
}