package com.ips.adaptor;

import java.util.ArrayList;

import com.ips.ipsactivity.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCustomAdapter extends ArrayAdapter<String> {
	Context context = null;
ArrayList<String> list = null;
	public MyCustomAdapter(Context context, int textViewResourceId,
			ArrayList<String> list) {
		super(context, textViewResourceId, list);
		this.context = context;
		this.list = list;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// return super.getView(position, convertView, parent);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.row, parent, false);
		TextView label = (TextView) row.findViewById(R.id.weekofday);
		label.setTextColor(Color.RED);
		label.setText(list.get(position));

		ImageView icon = (ImageView) row.findViewById(R.id.icon);

		icon.setImageResource(R.drawable.ic_launcher);

		return row;
	}
}