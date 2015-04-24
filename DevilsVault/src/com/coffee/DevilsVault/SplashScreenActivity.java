package com.coffee.DevilsVault;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {

	private final int kSplashScreenDuration = 3000;	// Duration of splash screen in milliseconds.
	private WeakReference<SplashScreenActivity> splashActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().hide();
		
//		# WARNING: Need to relocate this DatabseInitializer statement to a better place.
		try {
			DatabaseHelper.setupSharedInstance(this, "ManiacKey");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		showScreen();
		
		
		new Timer().schedule(new TimerTask() {          
		    @Override
		    public void run() {
		        // this code will be executed after 2 seconds
		    	setContentView(R.layout.activity_splash);
		    }
		}, 001);
		
		splashActivity = new WeakReference<SplashScreenActivity>(this);
		
		final boolean isUserPresent = DatabaseHelper.sharedInstance().isUserPresent();
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		  @Override
		  public void run() {
			  
			  Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
			  if(isUserPresent){
				  intent.putExtra("ScreenType", ChangePasswordActivity.ScreenTypeCreatePassword);
			  }else{
				  intent.putExtra("ScreenType", ChangePasswordActivity.ScreenTypeCreatePassword);
			  }
			  startActivity(intent);
			  
//			  Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//			  startActivity(intent);
			  
			  new Timer().schedule(new TimerTask() {          
				    @Override
				    public void run() {
				        // this code will be executed after kSplashScreenDuration.
				    	splashActivity.get().finish();
				    	splashActivity = null;
				    }
				}, 2000);
		  }
		}, kSplashScreenDuration);
		
	}
	
	private void showScreen(){
		setContentView(R.layout.activity_splash);
	}
}
