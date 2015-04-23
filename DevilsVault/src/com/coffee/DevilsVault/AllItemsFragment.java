package com.coffee.DevilsVault;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AllItemsFragment extends Fragment{

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	
	// Whether list should be updated when this is selected/reselected.
	public static boolean SHOULD_UPDATE_LIST = false;
	
	Activity mActivity;	// or Context
	DatabaseHelper mDBHelper;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mActivity = activity;
		this.mDBHelper = DatabaseHelper.sharedInstance();
	}

	public AllItemsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);
//		TextView dummyTextView = (TextView) rootView.findViewById(R.id.tvTitle);
//		dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
		
		ListView lvAllItems = (ListView)rootView.findViewById(R.id.lvAllItems);
		lvAllItems.setOnItemClickListener(new CustomOnItemSelectedListener());
		
		updateList(lvAllItems);
		
		return rootView;
	}
	
	public void updateList(ListView listView) {
		
		List<DevilsEntity> entities = mDBHelper.getAllDevilsEntities();
		if(listView == null)
			listView = (ListView) getView().findViewById(R.id.lvAllItems);
		
		CustomAllItemsArrayAdapdar customAdaptar = new CustomAllItemsArrayAdapdar(
				mActivity, R.layout.list_row_item, entities);
		listView.setAdapter(customAdaptar);
		customAdaptar.notifyDataSetChanged();
	}
	
	class CustomOnItemSelectedListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent,  View view, int position, long id) {
			
			DevilsEntity de = (DevilsEntity) parent.getAdapter().getItem(position);
			Intent editUpdateIntent = new Intent(getActivity(), EditUpdateItemActivity.class);
			Bundle bundle = new Bundle();
			bundle.putParcelable(SKConstants.ActivityExtras.ENTITY_IN_ACTION, de);
			editUpdateIntent.putExtras(bundle);
			startActivity(editUpdateIntent);
		}
	}
}

