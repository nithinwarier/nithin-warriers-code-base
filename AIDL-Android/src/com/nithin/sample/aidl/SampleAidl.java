package com.nithin.sample.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SampleAidl extends Activity {
    
	private String TAG = "SampleAidlClient";
	private MyRemoteService remoteService;
	private MyServiceConnection serviceConnection;
	EditText firstNum, secondNum;
	TextView resultView;
	Button add;
	int result, PID, UID;
	
	private void initService() {
		Log.i(TAG, " inside initService()");
		serviceConnection = new MyServiceConnection();
		Intent service = new Intent(this, RemoteService.class);
		boolean bindService = bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
		Log.i(TAG, "bindService = "+bindService);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnumber);
        Log.i(TAG, "onCreate()");
        initService();
        firstNum = (EditText) findViewById(R.id.firstNum);
        secondNum = (EditText) findViewById(R.id.secondNum);
        add = (Button) findViewById(R.id.addNum);
        resultView = (TextView) findViewById(R.id.result);
        add.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				int first = Integer.parseInt(firstNum.getText().toString());
				int second = Integer.parseInt(secondNum.getText().toString());
				try {
					result = remoteService.add(first, second);
					PID = remoteService.getPid();
					UID = remoteService.getUid();
					Log.i(TAG, "result = "+result+" PID = "+PID+" UID = "+UID);
					resultView.setText(Integer.toString(result)+" PID = "+PID+" UID = "+UID);
				} catch (Exception e) {
					Log.i(TAG, "Exception e = "+e);
					e.printStackTrace();
				}
			}
		});
    }
   
    class MyServiceConnection implements ServiceConnection {

		public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
			Log.i(TAG, "onServiceConnected()");
			remoteService = MyRemoteService.Stub.asInterface((IBinder)iBinder);
			Toast.makeText(SampleAidl.this, "Remote service connected", Toast.LENGTH_SHORT).show();
		}

		public void onServiceDisconnected(ComponentName name) {
			Toast.makeText(SampleAidl.this, "Remote service Disconnected", Toast.LENGTH_SHORT).show();
			Log.i(TAG, "onServiceDisconnected()");
			//remoteService = null;
		}
    }
}