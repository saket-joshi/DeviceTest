package com.syntaxsofts.devicetest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class ActivityButtonTest extends Activity {

	CheckBox chkVolUp, chkVolDown, chkMenu, chkBack;
	Button btnProceed;
	
	private final String DIALOG_TITLE="Power and home buttons";
	private final String DIALOG_MESSAGE="It is advisable to check the power and the home buttons on your own " +
			"and see if you get the appropriate results";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button_test);
		
		btnProceed = (Button)findViewById(R.id.btnProceedBtnTest);
		chkVolUp=(CheckBox)findViewById(R.id.chkVolUp);
		chkVolDown=(CheckBox)findViewById(R.id.chkVolDown);
		chkMenu=(CheckBox)findViewById(R.id.chkMenu);
		chkBack=(CheckBox)findViewById(R.id.chkBack);
		
		ActionBar mActionBar = getActionBar();
		mActionBar.setSubtitle("Button Test");
		
		btnProceed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				AlertDialog.Builder mBuilder = new Builder(ActivityButtonTest.this);
				mBuilder.setTitle(DIALOG_TITLE)
				.setMessage(DIALOG_MESSAGE)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						Toast.makeText(getApplicationContext(), "store to db code", Toast.LENGTH_SHORT).show();
						Intent mIntent = new Intent(ActivityButtonTest.this,ActivityNotificationLights.class);
						mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mIntent);
						ActivityButtonTest.this.finish();
						
					}
				});
				
				mBuilder.create().show();
			}
		});
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	
		switch(keyCode)
		{
		case KeyEvent.KEYCODE_VOLUME_UP:
			chkVolUp.setChecked(true);
			break;
			
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			chkVolDown.setChecked(true);
			break;
			
		case KeyEvent.KEYCODE_MENU:
			chkMenu.setChecked(true);
			break;
			
		case KeyEvent.KEYCODE_BACK:
			chkBack.setChecked(true);
			break;
		}
		
		return true;
	}
}
