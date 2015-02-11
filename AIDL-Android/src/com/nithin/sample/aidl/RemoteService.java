package com.nithin.sample.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.os.Process;

public class RemoteService extends Service {

	private String TAG = "RemoteService";
	
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate()");
	}
	
	private MyRemoteService.Stub remoteServiceStub = new MyRemoteService.Stub() {

		public int add(int a, int b) throws RemoteException {
			return (a+b);
		}
		
		public int getPid() throws RemoteException {
			Log.i(TAG, "getProcessID(), processID = "+Process.myPid());
			return Process.myPid();
		}
		
		public int getUid() throws RemoteException {
			Log.i(TAG, "getProcessUID(), processUID = "+Process.myUid());
			return Process.myUid();
		}
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "onBind()");
		return remoteServiceStub;
	}
}
