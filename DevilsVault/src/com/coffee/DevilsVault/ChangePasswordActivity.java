package com.coffee.DevilsVault;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

enum ChangePasswordScreenType{
	ChangePassword,
	AcceptPassword;
	
	public static ChangePasswordScreenType fromInt(int x) {
        switch(x) {
        case 0:
            return ChangePassword;
        case 1:
            return AcceptPassword;
        }
        return null;
    }
};

public class ChangePasswordActivity extends Activity {
	
	ChangePasswordScreenType screenType;
	
	public ChangePasswordActivity() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_change_password);
		
		Intent myIntent = getIntent();
		int type = myIntent.getIntExtra("ScreenType", 0);
		this.screenType = ChangePasswordScreenType.fromInt(type);
		
		EditText etvOldPassword = (EditText)findViewById(R.id.etvOldPassword);
		EditText etvNewPassword = (EditText)findViewById(R.id.etvNewPassword);
		EditText etvConfirmPassword = (EditText)findViewById(R.id.etvConfirmPassword);
		
		if(screenType == ChangePasswordScreenType.AcceptPassword){
			etvOldPassword.setHint(R.string.title_password);
			etvOldPassword.setVisibility(View.VISIBLE);
			etvNewPassword.setVisibility(View.GONE);
			etvNewPassword.setVisibility(View.GONE);
		}else if(screenType == ChangePasswordScreenType.ChangePassword){
			etvOldPassword.setVisibility(View.VISIBLE);
			etvNewPassword.setVisibility(View.VISIBLE);
			etvConfirmPassword.setVisibility(View.VISIBLE);
		}
	}
}
