package com.ips.adaptor;

import java.util.ArrayList;

import com.ips.ipsactivity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageArrayAdaptor extends ArrayAdapter<String> {
	Context context = null;
	ArrayList<String> list = null;

	public MessageArrayAdaptor(Context context, ArrayList<String> list) {
		// TODO Auto-generated constructor stub
		super(context, com.ips.ipsactivity.R.layout.listsearchedcases, list);
		this.context = context;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.listsearchedcases, parent, false);
		TextView names = (TextView) view
				.findViewById(R.id.serchedNamestextView);
		ImageView imageView = (ImageView) view
				.findViewById(R.id.withsearchedimageView);
		names.setText(list.get(position));
		imageView.setImageResource(R.drawable.ic_launcher);
		return view;
	}

}
