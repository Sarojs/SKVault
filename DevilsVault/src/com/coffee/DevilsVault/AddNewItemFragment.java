package com.coffee.DevilsVault;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewItemFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	public AddNewItemFragment(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.activity_add_new_item, container, false);
		
//		TextView dummyTextView = (TextView) rootView.findViewById(R.id.tvTitle);
//		dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
		
		Button buttonSave = (Button)rootView.findViewById(R.id.addNew_ButtonSave);
		buttonSave.setOnClickListener(new saveButtonListener());
		
		return rootView;
	}
	
	private class saveButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			
//			DatabaseHelper db = DatabaseHelper.sharedInstance();
//			db.deleteAllItems();
//			return;
			
			if(v.getId() == R.id.addNew_ButtonSave){
				
				
				View view = getView();
				
				EditText title = ((EditText)view.findViewById(R.id.etvTitle));
				EditText description = ((EditText)view.findViewById(R.id.etvDescription));
				EditText email = ((EditText)view.findViewById(R.id.etvEmail));
				EditText username = ((EditText)view.findViewById(R.id.etvUsername));
				EditText password = ((EditText)view.findViewById(R.id.etvPassword));
				EditText websiteLink = ((EditText)view.findViewById(R.id.etvUrl));
				EditText note = ((EditText)view.findViewById(R.id.etvNote));
				
				DatabaseHelper db = DatabaseHelper.sharedInstance();
				DevilsEntity entity = new DevilsEntity();
				entity.setTitle(title.getText().toString());
				entity.setDescription(description.getText().toString());
				entity.setEmail(email.getText().toString());
				entity.setUsername(username.getText().toString());
				entity.setPassword(password.getText().toString());
				entity.setUrl(websiteLink.getText().toString());
				entity.setNote(note.getText().toString());
				db.createDevilsEntity(entity);
				
				Toast.makeText(getActivity(), "Item saved", Toast.LENGTH_LONG).show();
				
				// Set SHOULD_UPDATE_LIST = true, so that list gets updated on next tab selection
				AllItemsFragment.SHOULD_UPDATE_LIST = true;
				
				// Clear fields
				title.setText(""); 
				description.setText(""); 
				email.setText("");
				username.setText("");
				password.setText("");
				websiteLink.setText("");
				note.setText("");
			}
		}
	}
}

