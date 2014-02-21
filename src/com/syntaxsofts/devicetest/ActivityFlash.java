package com.syntaxsofts.devicetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ActivityFlash extends Activity {

	private boolean hasFlash;
	private final String FLASH_NOT_PRESENT = "Your device doesn't have a flash";
	
	Button btnProceed;
	Button btnFlash;
	
	Camera mCamera;
	Camera.Parameters mParameters;
	
	private final String DIALOG_TITLE = "Flash test";
	private final String DIALOG_MESSAGE = "Did you see the flash?";
	
	private boolean isChecked = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flash);
		
		btnProceed=(Button)findViewById(R.id.btnProceedFlash);
		btnFlash=(Button)findViewById(R.id.toggleFlash);
		
		hasFlash=getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		if(!hasFlash)
		{
			AlertDialog.Builder mBuilder = new Builder(ActivityFlash.this);
			mBuilder.setTitle(DIALOG_TITLE)
			.setMessage(FLASH_NOT_PRESENT)
			.setCancelable(false)
			.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					Intent mIntent = new Intent(ActivityFlash.this, ActivitySensor.class);
					mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mIntent);
					ActivityFlash.this.finish();
					Toast.makeText(getApplicationContext(), "add to db", Toast.LENGTH_SHORT).show();
		
				}
			});
			mBuilder.create().show();
			
			btnFlash.setEnabled(false);
		}
		else
		{
			mCamera=Camera.open();
			mParameters=mCamera.getParameters();
			
			btnFlash.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(isChecked)
					{
						mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
						mCamera.setParameters(mParameters);
						btnFlash.setText(R.string.btnFlashOFF);
						mCamera.startPreview();
						isChecked=false;
					}
					else
					{
						mParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
						mCamera.setParameters(mParameters);
						btnFlash.setText(R.string.btnFlashON);
						mCamera.stopPreview();
						isChecked=true;
					}
					
				}
			});
		}
		
		btnProceed.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View view) {
				
				mCamera.release();
				
				AlertDialog.Builder mBuilder = new Builder(ActivityFlash.this);
				mBuilder.setTitle(DIALOG_TITLE)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setMessage(DIALOG_MESSAGE)
				.setCancelable(false)
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						Intent mIntent = new Intent(ActivityFlash.this, ActivitySensor.class);
						mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mIntent);
						ActivityFlash.this.finish();
						Toast.makeText(getApplicationContext(), "add to db", Toast.LENGTH_SHORT).show();
							
					}
				})
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
							
						Intent mIntent = new Intent(ActivityFlash.this, ActivitySensor.class);
						mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mIntent);
						ActivityFlash.this.finish();
						Toast.makeText(getApplicationContext(), "add to db", Toast.LENGTH_SHORT).show();
					
					}
				});			
				
				mBuilder.create().show();
	
			}
		});
	}
}
