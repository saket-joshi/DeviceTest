package com.syntaxsofts.devicetest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

public class ActivityVibrator extends Activity {
	
	private final String DIALOG_TITLE="Vibrator Test";
	private final String DIALOG_MESSAGE="Did the device vibrate?";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vibrator_test);
		
		ActionBar mActionBar = getActionBar();
		mActionBar.setTitle("Device Test");
		mActionBar.setSubtitle("Vibration test");
		
		Vibrator mVibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		mVibrator.vibrate(2000);
		try 
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		AlertDialog.Builder mAlertDialog = new Builder(ActivityVibrator.this);
		mAlertDialog.setTitle(DIALOG_TITLE)
		.setMessage(DIALOG_MESSAGE)
		.setIcon(android.R.drawable.ic_dialog_info)
		.setCancelable(false)
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "save to db and proceed", Toast.LENGTH_SHORT).show();
				
				Intent mIntent = new Intent(ActivityVibrator.this, ActivityButtonTest.class);
				mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(mIntent);
				ActivityVibrator.this.finish();
				
			}
		})
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "save to db and proceed", Toast.LENGTH_SHORT).show();
				
				Intent mIntent = new Intent(ActivityVibrator.this, ActivityButtonTest.class);
				mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(mIntent);
				ActivityVibrator.this.finish();
			}
		});
		
		mAlertDialog.create().show();
	}
}
