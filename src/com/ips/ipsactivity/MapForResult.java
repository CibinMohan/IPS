package com.ips.ipsactivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ips.refer.GetReverseGeoCoding;

public class MapForResult extends Activity {

	// static final LatLng THiNK = new LatLng(9.966839, 76.294845);
	// static final LatLng SOUTH = new LatLng(9.970115, 76.291991);
	// static final LatLng PANMPILLY = new LatLng(9.963771, 76.295736);
	private GoogleMap map;

	Button okButton = null;
	Button cancelButton = null;
	String location_name = null;
	int flag = 0;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		okButton = (Button) findViewById(R.id.mapFromResults);
		cancelButton = (Button) findViewById(R.id.cancelbutton);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		okButton.setVisibility(View.INVISIBLE);
		try {
			String latLang = getIntent().getStringExtra("MAP_LOCTION");

			GetReverseGeoCoding coding = new GetReverseGeoCoding();
			String[] splitLatLang = latLang.split(",");
			coding.getAddress(splitLatLang[0], splitLatLang[1]);

			Marker addMarker = map.addMarker(new MarkerOptions()
					.title(coding.getAddress1())
					.snippet(coding.getAddress2())
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.ic_launcher))
					.position(
							new LatLng(Double.parseDouble(splitLatLang[0]),
									Double.parseDouble(splitLatLang[1]))));
			addMarker.showInfoWindow();
			LatLng latLng = new LatLng(Double.parseDouble(splitLatLang[0]),
					Double.parseDouble(splitLatLang[1]));
			map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
			map.animateCamera(CameraUpdateFactory.zoomTo(20));
			

		} catch (NullPointerException e) {
			Toast.makeText(getApplicationContext(),
					"Error while loading location name", Toast.LENGTH_SHORT)
					.show();

		}
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
	}
}
