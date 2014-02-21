package com.syntaxsofts.devicetest;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityMain extends Activity {
	
	Button btnStartTest;
	private final String LABEL_TITLE="Device Tester";
	private final String LABEL_SUBTITLE="Welcome to device tester";
	private final String DIALOG_BEGIN="Begin test?";
	private final String DIALOG_BEGIN_INFO="Test will take atleast 5-10 mins. Proceed?";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar mActionBar = getActionBar();
		mActionBar.setTitle(LABEL_TITLE);
		mActionBar.setSubtitle(LABEL_SUBTITLE);
		
		btnStartTest=(Button)findViewById(R.id.btnStartTest);
		
		btnStartTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder mBuilder = new Builder(ActivityMain.this);
				mBuilder.setIcon(android.R.drawable.ic_dialog_alert)				
				.setCancelable(true)
				.setTitle(DIALOG_BEGIN)
				.setMessage(DIALOG_BEGIN_INFO)
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
					}
				})
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						Intent intent = new Intent(ActivityMain.this, ActivityScreenColor.class);
						startActivity(intent);
						ActivityMain.this.finish();
					}
				});
				mBuilder.create().show();
			}
		});
	}
}
