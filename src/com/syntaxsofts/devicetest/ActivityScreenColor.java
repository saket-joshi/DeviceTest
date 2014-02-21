package com.syntaxsofts.devicetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityScreenColor extends Activity {

	int CurrentColorCode;
	
	private final String TEST_COMPLETED = "Color test completed. Tick the colors you saw. . .";
	private final String TEST_CANCEL = "Sure to cancel test?";
	
	RelativeLayout mLayout;
	TextView lblInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_color);
		
		mLayout = (RelativeLayout)findViewById(R.id.layout_screen_color);
		lblInfo = (TextView)findViewById(R.id.lblInfoColorScreen);
		
		mLayout.setBackgroundResource(R.color.RED);
		CurrentColorCode=0;
		
		mLayout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View view, MotionEvent event) {

				if(CurrentColorCode!=4)
				{
					mLayout.setBackgroundResource(getNextColorCode());
					CurrentColorCode++;
				}
				else
				{
					lblInfo.setVisibility(View.INVISIBLE);
					
					AlertDialog.Builder mBuilder = new Builder(ActivityScreenColor.this);
					mBuilder.setTitle(TEST_COMPLETED)
					.setMultiChoiceItems(new CharSequence[] {"Red","Green","Blue","Black","White"}, 
							new boolean[] {false,false,false,false,false}, new OnMultiChoiceClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int position, boolean value) {
							clsValues.ScreenColors[position] = value;
						}
					})
					.setNegativeButton("Cancel Test", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							AlertDialog.Builder mBuilder2 = new Builder(ActivityScreenColor.this);
							mBuilder2.setTitle(TEST_CANCEL)
							.setNegativeButton("No", new OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
								}
							})
							.setPositiveButton("Yes", new OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {

									Intent intent = new Intent(ActivityScreenColor.this, ActivityMain.class);
									intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
									.putExtra("returnScreen", "color");
									startActivity(intent);
									ActivityScreenColor.this.finish();		
									
								}
							});
							mBuilder2.create().show();
						}
					})
					.setNeutralButton("Re-test colors", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							Intent intent = new Intent(ActivityScreenColor.this, ActivityScreenColor.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent);
							ActivityScreenColor.this.finish();
							
						}
					})
					.setPositiveButton("Proceed", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int button) {
							
							Toast.makeText(getApplicationContext(), "Enter code to store to db", Toast.LENGTH_SHORT).show();
							Intent mIntent = new Intent(ActivityScreenColor.this, ActivityTouchPanel.class);
							mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(mIntent);
							ActivityScreenColor.this.finish();
							
						}
					});
					mBuilder.create().show();
				}				
				return false;
			}
		});
		
	}
	
	int getNextColorCode()
	{
		switch(CurrentColorCode)
		{
		case 0:
			return R.color.GREEN;
		case 1:
			return R.color.BLUE;
		case 2:
			return R.color.BLACK;
		case 3:
			return R.color.WHITE;
		case 4:
			return -1;
		}
		return -1;
	}
	
}
