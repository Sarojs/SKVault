package com.coffee.DevilsVault;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

public class SplashScreenActivity extends Activity {

	private final int kSplashScreenDuration = 3000;	// Duration of splash screen in milliseconds.
	private WeakReference<SplashScreenActivity> splashActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().hide();
		showScreen();
		
		
		new Timer().schedule(new TimerTask() {          
		    @Override
		    public void run() {
		        // this code will be executed after 2 seconds
		    	setContentView(R.layout.activity_splash);
		    }
		}, 001);
		
		splashActivity = new WeakReference<SplashScreenActivity>(this);
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		  @Override
		  public void run() {
		    //Do something after 100ms
			  
			  Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			  startActivity(intent);
			  
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
