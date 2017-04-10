package com.ips.ipsactivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.ips.util.AppVariable;

public class CostomeListener implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		if (AppVariable.HASH_MAP != null) {
			AppVariable.POSI_ID = AppVariable.HASH_MAP.get(position);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
