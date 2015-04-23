package com.coffee.DevilsVault;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditUpdateItemActivity extends Activity {
	
	private DevilsEntity de;
	private EditUpdateItemActivity thisActivity;;
	
	public EditUpdateItemActivity() {
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null)
		    de = b.getParcelable(SKConstants.ActivityExtras.ENTITY_IN_ACTION);
		setContentView(R.layout.activity_add_new_item);
		
		populateData(de);
		
		Button buttonSave = (Button)findViewById(R.id.addNew_ButtonSave);
		buttonSave.setOnClickListener(new saveButtonListener());
		
		Button buttonDelete = (Button)findViewById(R.id.addNew_ButtonDelete);
		buttonDelete.setOnClickListener(new deleteButtonListener());
		buttonDelete.setVisibility(View.VISIBLE);
		
		thisActivity = this;
	}

	private void populateData(DevilsEntity de){
		
		EditText edt = (EditText) findViewById(R.id.etvTitle);
		edt.setText(de.title);
		
		edt = (EditText) findViewById(R.id.etvDescription);
		edt.setText(de.description);
		
		edt = (EditText) findViewById(R.id.etvEmail);
		edt.setText(de.email);
		
		edt = (EditText) findViewById(R.id.etvUsername);
		edt.setText(de.username);
		
		edt = (EditText) findViewById(R.id.etvPassword);
		edt.setText(de.password);
		
		edt = (EditText) findViewById(R.id.etvUrl);
		edt.setText(de.url);
		
		edt = (EditText) findViewById(R.id.etvNote);
		edt.setText(de.note);
	}
	
	private class saveButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			
//			DatabaseHelper db = DatabaseHelper.sharedInstance();
//			db.deleteAllItems();
//			return;
			
			if(v.getId() == R.id.addNew_ButtonSave){
				
				EditText title = ((EditText)findViewById(R.id.etvTitle));
				EditText description = ((EditText)findViewById(R.id.etvDescription));
				EditText email = ((EditText)findViewById(R.id.etvEmail));
				EditText username = ((EditText)findViewById(R.id.etvUsername));
				EditText password = ((EditText)findViewById(R.id.etvPassword));
				EditText websiteLink = ((EditText)findViewById(R.id.etvUrl));
				EditText note = ((EditText)findViewById(R.id.etvNote));
				
				DevilsEntity entity = de;
				entity.setTitle(title.getText().toString());
				entity.setDescription(description.getText().toString());
				entity.setEmail(email.getText().toString());
				entity.setUsername(username.getText().toString());
				entity.setPassword(password.getText().toString());
				entity.setUrl(websiteLink.getText().toString());
				entity.setNote(note.getText().toString());
				
				DatabaseHelper db = DatabaseHelper.sharedInstance();
				boolean isSuccessful = db.updateDevilsEntity(entity); 
				
				if(isSuccessful){
					
					Toast.makeText(getApplicationContext(), "Item saved", Toast.LENGTH_LONG).show();
					
					// Set SHOULD_UPDATE_LIST = true, so that list gets updated on next tab selection
					AllItemsFragment.SHOULD_UPDATE_LIST = true;
				}else{
					Toast.makeText(getApplicationContext(), "Item could not be saved. Please try again!", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
	
	private class deleteButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			if(v.getId() == R.id.addNew_ButtonDelete){
				
				DatabaseHelper db = DatabaseHelper.sharedInstance();
				boolean isSuccessful = db.deleteEntity(de); 
				
				if(isSuccessful){
					
					Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_LONG).show();
					
					// Set SHOULD_UPDATE_LIST = true, so that list gets updated on next tab selection
					AllItemsFragment.SHOULD_UPDATE_LIST = true;
					
					thisActivity.finish();
					
				}else{
					Toast.makeText(getApplicationContext(), "Item could not be deleted. Please try again!", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}
