package com.syntaxsofts.devicetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTouchPanel extends Activity {

	RelativeLayout mLayout;
	TextView lblXCoOrd;
	TextView lblYCoOrd;
	TextView lblTouchValue;
	Button btnProceed;
	
	boolean isMultiTouchTested=false;
	
	private final String DIALOG_TOUCH = "Multitouch test";
	private final String DIALOG_TOUCH_INFO = "Did you try the multitouch feature?";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch_panel);
		
		mLayout = (RelativeLayout)findViewById(R.id.layout_touch_panel);
		lblXCoOrd=(TextView)findViewById(R.id.lblCoOrdX);
		lblYCoOrd=(TextView)findViewById(R.id.lblCoOrdY);
		lblTouchValue=(TextView)findViewById(R.id.lblTouchValue);
		btnProceed=(Button)findViewById(R.id.btnProceed);
		
		mLayout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View view, MotionEvent event) {

				lblXCoOrd.setText(String.valueOf(event.getX()));
				lblYCoOrd.setText(String.valueOf(event.getY()));
				lblTouchValue.setText(String.valueOf(event.getPointerCount()));
				
				return true;
			}
		});
		
		btnProceed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
			
				AlertDialog.Builder mBuilder = new Builder(ActivityTouchPanel.this);
				mBuilder.setTitle(DIALOG_TOUCH)
				.setMessage(DIALOG_TOUCH_INFO)
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						Toast.makeText(getApplicationContext(), "Enter code to store to db", Toast.LENGTH_SHORT).show();
						isMultiTouchTested=false;

						Intent mIntent = new Intent(ActivityTouchPanel.this, ActivityVibrator.class);
						mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mIntent);
						ActivityTouchPanel.this.finish();
					}
				})
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						Toast.makeText(getApplicationContext(), "Enter code to store to db", Toast.LENGTH_SHORT).show();
						isMultiTouchTested=true;

						Intent mIntent = new Intent(ActivityTouchPanel.this, ActivityVibrator.class);
						mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mIntent);
						ActivityTouchPanel.this.finish();
					}
				});
				mBuilder.create().show();
			}
		});
		
	}
	
}
