package com.coffee.DevilsVault;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import SKUtility.SKDateFormatter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.coffee.DevilsVault.DevilsEntity;

public class CustomAllItemsArrayAdapdar extends ArrayAdapter<DevilsEntity> {

	final String TAG = "CustomAllItemsArrayAdapdar.java";

	Context mContext;
	int layoutResourceId;
	List<DevilsEntity> objects;

	public CustomAllItemsArrayAdapdar(Context mContext, int layoutResourceId,
			List<DevilsEntity> entities) {

		super(mContext, layoutResourceId, entities);

		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.objects = entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		try {

			if (convertView == null) {
				// inflate the layout
				LayoutInflater inflater = ((MainActivity) mContext).getLayoutInflater();
				convertView = inflater.inflate(layoutResourceId, parent, false);
			}

			DevilsEntity objectItem = objects.get(position);
			Log.d(MainActivity.APP_TAG, objectItem.title + " at index " + position);

			TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);
			textViewItem.setText(objectItem.title);
			
			long modifiedAtTimestamp = Long.valueOf(objectItem.modified_at);
			Date modifiedTimestamp = new Date(modifiedAtTimestamp);
			
			
			String humanFormat = SKDateFormatter.humanFormat(modifiedTimestamp);
			TextView textViewItemDetails = (TextView) convertView.findViewById(R.id.textViewItemDetails);
			textViewItemDetails.setText(humanFormat);

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;
	}

}
