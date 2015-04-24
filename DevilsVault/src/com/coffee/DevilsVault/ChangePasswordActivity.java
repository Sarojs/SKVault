package com.coffee.DevilsVault;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChangePasswordActivity extends Activity {
	
	public static int ScreenTypeChangePassword = 0;
	public static int ScreenTypeAcceptPassword = 1;
	public static int ScreenTypeCreatePassword = 2;
	
	private int screenType;
	
	public ChangePasswordActivity() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_change_password);
		
		Intent myIntent = getIntent();
		this.screenType = myIntent.getIntExtra("ScreenType", ScreenTypeChangePassword);
		
		EditText etvOldPassword = (EditText)findViewById(R.id.etvOldPassword);
		EditText etvNewPassword = (EditText)findViewById(R.id.etvNewPassword);
		EditText etvConfirmPassword = (EditText)findViewById(R.id.etvConfirmPassword);
		
		if(screenType == ScreenTypeAcceptPassword){
			
			setTitle(R.string.title_action_bar_set_password);
			
			etvOldPassword.setHint(R.string.title_old_password);
			etvOldPassword.setVisibility(View.VISIBLE);
			etvNewPassword.setVisibility(View.GONE);
			etvNewPassword.setVisibility(View.GONE);
			
		}else if(screenType == ScreenTypeChangePassword){
			
			setTitle(R.string.title_action_bar_change_password);
			
			etvOldPassword.setVisibility(View.VISIBLE);
			etvNewPassword.setVisibility(View.VISIBLE);
			etvConfirmPassword.setVisibility(View.VISIBLE);
			
		}else if(screenType == ScreenTypeCreatePassword){
			
			setTitle(R.string.title_action_bar_set_password);
			
			etvOldPassword.setVisibility(View.GONE);
			etvNewPassword.setVisibility(View.VISIBLE);
			etvConfirmPassword.setVisibility(View.VISIBLE);
		}
	}
}
