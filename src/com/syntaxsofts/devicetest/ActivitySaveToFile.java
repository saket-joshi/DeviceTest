package com.syntaxsofts.devicetest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class ActivitySaveToFile extends Activity{

	public static final String SHARED_PREFS = "DeviceTest_Prefs";
	SharedPreferences mPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final);
		
		mPreferences = getSharedPreferences(SHARED_PREFS, Activity.MODE_PRIVATE);
		
		SharedPreferences.Editor mEditor = mPreferences.edit();
		if(clsValues.ScreenColors[0])
			mEditor.putBoolean("ScreenColor_RED", true);
		else
			mEditor.putBoolean("ScreenColor_RED", false);
		
		mEditor.apply();
		
	}
	
}
