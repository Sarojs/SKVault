package com.coffee.DevilsVault;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutActivity extends Activity {

	public AboutActivity(){
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_about);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView aboutEditText = (TextView)findViewById(R.id.aboutText);
		aboutEditText.setText(R.string.action_about_text);
		aboutEditText.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
