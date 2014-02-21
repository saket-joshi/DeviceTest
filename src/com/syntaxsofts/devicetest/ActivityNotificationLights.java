package com.syntaxsofts.devicetest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ActivityNotificationLights extends Activity {

	Button btnProceed;
	private static String DIALOG_TITLE = "Check the colors of the displayed lights";
	private boolean observedColors[] = {false, false, false, false, false, false, false};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_lights);
		
		NotificationTask.myActivity=this; 
		
		ActionBar mActionBar = getActionBar();
		mActionBar.setTitle("Device Test");
		mActionBar.setSubtitle("Notification Lights Test");
		
		btnProceed=(Button)findViewById(R.id.btnProceedNotifications);
		
		NotificationTask mNotificationTask = new NotificationTask();
		mNotificationTask.execute(ActivityNotificationLights.this);
		
		btnProceed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder mBuilder = new Builder(ActivityNotificationLights.this);
				mBuilder.setTitle(DIALOG_TITLE)
				.setMultiChoiceItems(new String[] {"Red", "Blue", "Cyan", "Green", "White","Yellow", "Gray"},
						new boolean[] {false, false, false, false, false, false, false}, new OnMultiChoiceClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int position, boolean isChecked) {
								observedColors[position] = true;
							}
						})
				.setNegativeButton("Re-test", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						Intent mIntent = new Intent(ActivityNotificationLights.this, ActivityNotificationLights.class);
						mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mIntent);
						ActivityNotificationLights.this.finish();
						
					}
				})
				.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						Toast.makeText(getApplicationContext(), "save to db", Toast.LENGTH_SHORT).show();
						Intent mIntent = new Intent(ActivityNotificationLights.this, ActivityCameraRear.class);
						mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mIntent);
						ActivityNotificationLights.this.finish();
						
					}
				})
				.setCancelable(false);
				
				mBuilder.create().show();
			}
		});	
	}
}
