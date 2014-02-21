package com.syntaxsofts.devicetest;

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

public class ActivityCameraFront extends Activity implements SurfaceHolder.Callback{

	Button btnProceed;	
	Camera mCamera;
	
	static final String LABEL_INFO = "Front facing camera is not available for your device, press Proceed button";
	private final String DIALOG_TITLE = "Front camera test";
	private final String DIALOG_MESSAGE = "Did you see the camera?";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_front);
		
		btnProceed=(Button)findViewById(R.id.btnProceedCamFront);
		SurfaceView mSurfaceView = (SurfaceView)findViewById(R.id.surfaceCamFront);
		
		if(Camera.getNumberOfCameras()==1)
		{
			mSurfaceView.setVisibility(View.INVISIBLE);
			Toast.makeText(getApplicationContext(), LABEL_INFO, Toast.LENGTH_SHORT).show();
		}
		else
		{
			Camera nCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
			nCamera.setDisplayOrientation(90);
			
			mCamera=nCamera;
			
			SurfaceHolder mHolder = mSurfaceView.getHolder();
			mHolder.addCallback(ActivityCameraFront.this);	
		}
		
		btnProceed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
			
				if(Camera.getNumberOfCameras()!=1)
				{
					mCamera.release();
				
					AlertDialog.Builder mBuilder = new Builder(ActivityCameraFront.this);
					mBuilder.setTitle(DIALOG_TITLE)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setMessage(DIALOG_MESSAGE)
					.setCancelable(false)
					.setNegativeButton("No", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							Intent mIntent = new Intent(ActivityCameraFront.this, ActivityFlash.class);
							mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(mIntent);
							ActivityCameraFront.this.finish();
							Toast.makeText(getApplicationContext(), "add to db", Toast.LENGTH_SHORT).show();
							
						}
					})
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							Intent mIntent = new Intent(ActivityCameraFront.this, ActivityFlash.class);
							mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(mIntent);
							ActivityCameraFront.this.finish();
							Toast.makeText(getApplicationContext(), "add to db", Toast.LENGTH_SHORT).show();
							
						}
					});		
					
					mBuilder.create().show();
					
				}
				else
				{
					Intent mIntent = new Intent(ActivityCameraFront.this, ActivityFlash.class);
					mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mIntent);
					ActivityCameraFront.this.finish();
					Toast.makeText(getApplicationContext(), "add to db", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		try
		{
			if(Camera.getNumberOfCameras()!=1)
				mCamera.release();
		}
		catch(Exception ex) {}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		try 
		{
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		}
		catch (Exception e) 
		{
			Toast.makeText(getApplicationContext(), "Cannot connect to the camera service", Toast.LENGTH_SHORT).show();
		}
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

		try
		{
			mCamera.release();
		}
		catch(Exception ex) {}
		
	}
	
}
