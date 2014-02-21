package com.syntaxsofts.devicetest;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;
import android.widget.Toast;

public class NotificationTask extends AsyncTask<Context, String, Void> {
	
	private Context mContext;
	public static Activity myActivity;
	
	@Override
	protected Void doInBackground(Context... context) {
		
		mContext=context[0];
		
		NotificationManager mManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
		
		NotificationCompat.Builder mNotification = new NotificationCompat.Builder(mContext);
		mNotification.setSmallIcon(R.drawable.ic_launcher)
		.setContentTitle("Notification lights test")
		.setContentText("Blinking the notification light");
		
		mNotification.setLights(Color.RED, 2000, 1000);
		mManager.notify(1, mNotification.build());
		try 
		{
			publishProgress("RED");
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		mManager.cancel(1);
/*		
		mNotification.setLights(Color.BLUE, 2000, 1000);
		mManager.notify(1, mNotification.build());
		try 
		{
			publishProgress("BLUE");
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		mManager.cancel(1);

		mNotification.setLights(Color.CYAN, 2000, 1000);
		mManager.notify(1, mNotification.build());
		try 
		{
			publishProgress("CYAN");
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		mManager.cancel(1);
		
		mNotification.setLights(Color.GREEN, 2000, 1000);
		mManager.notify(1, mNotification.build());
		try 
		{
			publishProgress("GREEN");
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		mManager.cancel(1);
		
		mNotification.setLights(Color.WHITE, 2000, 1000);
		mManager.notify(1, mNotification.build());
		try 
		{
			publishProgress("WHITE");
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		mManager.cancel(1);
		
		mNotification.setLights(Color.YELLOW, 2000, 1000);
		mManager.notify(1, mNotification.build());
		try 
		{
			publishProgress("YELLOW");
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		mManager.cancel(1);
		
		mNotification.setLights(Color.GRAY, 2000, 1000);
		mManager.notify(1, mNotification.build());
		try 
		{
			publishProgress("GRAY");
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		mManager.cancel(1);
		this.cancel(true);*/
		
		return null;		
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		Toast.makeText(mContext,"LED test color: " + values[0], Toast.LENGTH_SHORT).show();
		super.onProgressUpdate(values);
	}
	
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		 
		Button btnProceed = (Button)myActivity.findViewById(R.id.btnProceedNotifications);
		btnProceed.setEnabled(true);
		
	}
}
