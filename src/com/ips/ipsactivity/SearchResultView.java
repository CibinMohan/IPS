package com.ips.ipsactivity;

import java.util.ArrayList;

import com.ips.bean.CaseBean;
import com.ips.refer.GetReverseGeoCoding;
import com.ips.util.AppVariable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchResultView extends Activity {
	EditText adharNumber = null;
	EditText vahicleNumber = null;
	EditText caseType = null;
	EditText location = null;
	Button mapClick = null;
	String locationLatLang = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewcasesforsearch);
		setComponents();
		setResults(AppVariable.POSI_ID);
		mapClick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchResultView.this, MapForResult.class);
				intent.putExtra("MAP_LOCTION", locationLatLang);
				startActivity(intent);

			}
		});
	}

	private void setResults(String positionId) {
		// TODO Auto-generated method stub
		if (AppVariable.SEARCH_RESULT_ARRY_LIST != null) {
			ArrayList<CaseBean> beans = AppVariable.SEARCH_RESULT_ARRY_LIST;

			for (int i = 0; i < beans.size(); i++) {

				if (beans.get(i).getCase_id().equals(positionId)) {

					adharNumber.setText(beans.get(i).getSuspect_AddarNo());
					vahicleNumber.setText(beans.get(i).getVehicle_No());
					caseType.setText(beans.get(i).getCase_type());
					try {
						GetReverseGeoCoding coding = new GetReverseGeoCoding();
						String locationFromResult = beans.get(i).getLocation();
						locationLatLang = beans.get(i).getLocation();
						String[] splitForResult = locationFromResult.split(",");
						coding.getAddress(splitForResult[0], splitForResult[1]);
						location.setText(coding.getAddress2());

					} catch (NullPointerException e) {
						Toast.makeText(getApplicationContext(),
								"No matching location..!", Toast.LENGTH_SHORT)
								.show();

					}
				}

			}
		}

	}

	private void setComponents() {
		// TODO Auto-generated method stub
		adharNumber = (EditText) findViewById(R.id.addharEditFromresulteditText);
		vahicleNumber = (EditText) findViewById(R.id.vahiclenumberFromresulteditText);
		caseType = (EditText) findViewById(R.id.casetypeFromResultEdittext);
		location = (EditText) findViewById(R.id.locationResultFromsearcheditText);
		mapClick = (Button) findViewById(R.id.mapFromResults);
	}

}
