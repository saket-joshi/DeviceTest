package com.syntaxsofts.devicetest;

import java.util.Map;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.Toast;

public class SensorUpdates extends AsyncTask<Context, float[], Void> implements SensorEventListener{

	Context mContext;

	SensorManager mSensorManager;
	Sensor mSensor;
	Map<String, String>mMap; 
	
	BroadcastReceiver brUnregister;
	
	public static Activity myActivitySensor;
	
	private float[] sensorValues={0,0,0};
	private boolean firstTime = true;
	
	public SensorUpdates(Sensor nSensor, Map<String,String> nMap) {
		this.mSensor = nSensor;
		this.mMap=nMap;
	}
	
	@Override
	protected Void doInBackground(Context... params) {
		mContext=params[0];

		mSensorManager=(SensorManager)mContext.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(SensorUpdates.this, mSensor, SensorManager.SENSOR_DELAY_UI);
		
		return null;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		sensorValues = event.values;
		publishProgress(sensorValues);

		if(firstTime)
		{
			brUnregister = new BroadcastReceiver() {
			
				@Override
				public void onReceive(Context context, Intent intent) {
					mSensorManager.unregisterListener(SensorUpdates.this);
					Toast.makeText(mContext, "unregistered" + mSensor.getName(), Toast.LENGTH_SHORT).show();
					mContext.unregisterReceiver(brUnregister);
				}
			};
			mContext.registerReceiver(brUnregister, new IntentFilter("unregisterListeners"));
			PendingIntent mPendingIntent = PendingIntent.getBroadcast(mContext, 0, new Intent("unregisterListeners"), 0);
			
			AlarmManager mAlarmManager = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
			mAlarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 5000, mPendingIntent);
			firstTime=false;
		}
	}
	
	@Override
	protected void onProgressUpdate(float[]... values) {
		super.onProgressUpdate(values);
	
		if(values[0][1]!=-1 && values[0][2]!=-1)
			mMap.put("sensorInfo","Vendor: " + mSensor.getVendor() + "\nPower Usage: " + String.valueOf(mSensor.getPower()) + "\n" 
					+ String.valueOf(values[0][0]) + "\n" + String.valueOf(values[0][1])
					+ "\n" + String.valueOf(values[0][2]));
		else
			mMap.put("sensorInfo","Vendor: " + mSensor.getVendor() + "\nPower Usage: " + String.valueOf(mSensor.getPower()) + "\n"
					+ String.valueOf(values[0][0]));
		
		ActivitySensor.mAdapter.notifyDataSetChanged();
		
	}
}
