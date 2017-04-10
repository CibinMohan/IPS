package com.ips.ipsactivity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.ips.adaptor.MessageArrayAdaptor;
import com.ips.bean.CaseBean;
import com.ips.util.AppVariable;

public class SearchResults extends ListActivity {
	ListView lv = null;
	HashMap<Integer, String> Hashmap = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		lv = getListView();
		lv.setBackgroundResource(R.drawable.background);
		lv.setDividerHeight(5);

		try {
			addTextToMain();
		} catch (Exception e) {

		}

	}

	private void addTextToMain() {
		// TODO Auto-generated method stub
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<CaseBean> beans = null;
		Hashmap = new HashMap<Integer, String>();
		if (AppVariable.SEARCH_RESULT_ARRY_LIST != null) {
			beans = AppVariable.SEARCH_RESULT_ARRY_LIST;
		}

		for (int i = 0; i < beans.size(); i++) {

			arrayList.add(beans.get(i).getCase_type());
			Hashmap.put(i, beans.get(i).getCase_id());
			Log.d("RES", "" + i);
		}

		lv.setAdapter(new MessageArrayAdaptor(this, arrayList));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(SearchResults.this, SearchResultView.class);
		String resultPos = Hashmap.get(position);
		Log.d("RES", "from : " + position + "result:" + resultPos);
		AppVariable.POSI_ID = resultPos;
		startActivity(intent);
		finish();
		super.onListItemClick(l, v, position, id);
	}
}
