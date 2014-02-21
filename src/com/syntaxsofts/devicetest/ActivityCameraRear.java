package com.syntaxsofts.devicetest;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ActivityCameraRear extends Activity implements SurfaceHolder.Callback{

	Camera mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
	Button btnProceed;
	
	private final String DIALOG_TITLE = "Rear camera test";
	private final String DIALOG_MESSAGE = "Did you see the camera?";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_rear);
		
		mCamera.setDisplayOrientation(90);
		btnProceed=(Button)findViewById(R.id.btnProceedCamRear);
		
		SurfaceView mSurfaceView = (SurfaceView)findViewById(R.id.surfaceCam);
		SurfaceHolder mHolder = mSurfaceView.getHolder();
		mHolder.addCallback(ActivityCameraRear.this);
		
		btnProceed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
			
				mCamera.release();
				
				AlertDialog.Builder mBuilder = new Builder(ActivityCameraRear.this);
				mBuilder.setTitle(DIALOG_TITLE)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setMessage(DIALOG_MESSAGE)
				.setCancelable(false)
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						Intent mIntent = new Intent(ActivityCameraRear.this, ActivityCameraFront.class);
						mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mIntent);
						ActivityCameraRear.this.finish();
						Toast.makeText(getApplicationContext(), "add to db", Toast.LENGTH_SHORT).show();
						
					}
				})
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						Intent mIntent = new Intent(ActivityCameraRear.this, ActivityCameraFront.class);
						mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mIntent);
						ActivityCameraRear.this.finish();
						Toast.makeText(getApplicationContext(), "add to db", Toast.LENGTH_SHORT).show();
						
					}
				});
				
				mBuilder.create().show();
			}
		});
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		try
		{
			mCamera.release();
		}
		catch(Exception ex) {}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		try
		{
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		}
		catch (IOException e) 
		{		}
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		try
		{
			mCamera.release();
		}
		catch(Exception ex)
		{}
		
	}
	
}
