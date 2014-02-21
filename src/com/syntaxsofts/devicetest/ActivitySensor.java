package com.syntaxsofts.devicetest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ActivitySensor extends Activity{

	Sensor mSensor;
	SensorManager mSensorManager;
	
	ListView lstSensorListView;
	List<Map<String,String>> lstSensorList = new ArrayList<Map<String,String>>();
	List<Sensor>lstSensor = null;
	
	public static ArrayList<String>lstValues = new ArrayList<String>();
	
	public static SimpleAdapter mAdapter;
	
	SensorUpdates mSensorUpdates;
	Button btnProceed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		
		SensorUpdates.myActivitySensor = ActivitySensor.this;
		
		lstSensorListView =(ListView)findViewById(R.id.lstSensors);
		btnProceed=(Button)findViewById(R.id.btnProceedSensor);
		
		ActionBar mActionBar = getActionBar();
		mActionBar.setTitle("Device Test");
		mActionBar.setSubtitle("Sensor Test");
		
		mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);	
		lstSensor=mSensorManager.getSensorList(Sensor.TYPE_ALL);
	
		for (Sensor nSensor : lstSensor) {
			Map<String, String> mMap = new HashMap<String, String>(2);
			mMap.put("sensorName", getSensorProperName(nSensor.getType()));
			lstSensorList.add(mMap);			
			
			mSensorUpdates = new SensorUpdates(nSensor, mMap);
				mSensorUpdates.execute(ActivitySensor.this);
		}
		
		mAdapter = new SimpleAdapter(ActivitySensor.this, lstSensorList, android.R.layout.simple_list_item_2,
				new String[] {"sensorName","sensorInfo"}, new int[] {android.R.id.text1,android.R.id.text2});
		
		lstSensorListView.setAdapter(mAdapter);
		
		btnProceed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent(ActivitySensor.this,ActivitySaveToFile.class);
				startActivity(mIntent);
				ActivitySensor.this.finish();
			}
		});
	}
	
	private String getSensorProperName(int SENSOR_TYPE)
	{
		switch(SENSOR_TYPE)
		{
		case Sensor.TYPE_ACCELEROMETER:
			return "Accelerometer";
			
		case Sensor.TYPE_AMBIENT_TEMPERATURE:
			return "Temperature Sensor";
			
		case Sensor.TYPE_GRAVITY:
			return "Gravity Sensor";
			
		case Sensor.TYPE_GYROSCOPE:
			return "Gyroscope";
			
		case Sensor.TYPE_LIGHT:
			return "Light Sensor";
			
		case Sensor.TYPE_LINEAR_ACCELERATION:
			return "Linear Acceleration";
			
		case Sensor.TYPE_MAGNETIC_FIELD:
			return "Magnetometer";
			
		case Sensor.TYPE_PRESSURE:
			return "Barometer";
			
		case Sensor.TYPE_PROXIMITY:
			return "Proximity Sensor";
			
		case Sensor.TYPE_RELATIVE_HUMIDITY:
			return "Humidity Sensor";
			
		case Sensor.TYPE_ROTATION_VECTOR:
		case Sensor.TYPE_GAME_ROTATION_VECTOR:
		case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
			return "Rotation Sensor";
			
			default:
				return "Unknown Sensor";
		}
	}
}
