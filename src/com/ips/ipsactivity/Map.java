package com.ips.ipsactivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ips.refer.GetReverseGeoCoding;
import com.ips.util.AppConstance;
import com.ips.util.AppVariable;

public class Map extends Activity {

	// static final LatLng THiNK = new LatLng(9.966839, 76.294845);
	// static final LatLng SOUTH = new LatLng(9.970115, 76.291991);
	// static final LatLng PANMPILLY = new LatLng(9.963771, 76.295736);
	private GoogleMap map;

	Button okButton = null;
	Button cancelButton = null;
	String location_name = null;
	int flag = 0;
	SharedPreferences preferences = null;

	protected String locatio_Values = "";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		okButton = (Button) findViewById(R.id.mapFromResults);
		cancelButton = (Button) findViewById(R.id.cancelbutton);
		preferences = getSharedPreferences(AppConstance.SHARED_PREF, 0);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.setMyLocationEnabled(true);

		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!location_name.equals("")
						&& !(AppVariable.LOCATION_NAME_ASSIGN == null)) {
					AppVariable.selected_location = location_name;
					Editor edit = preferences.edit();
					edit.putString("LOCATION_NAME", location_name);
					edit.putString("LOCATION_VALUE", locatio_Values);
					edit.commit();
					AppVariable.LOCATION_NAME_ASSIGN.setText(location_name);
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Please select atleast one area",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!location_name.equals("")
						&& !(AppVariable.LOCATION_NAME_ASSIGN == null)) {
					AppVariable.selected_location = "";
					AppVariable.curLatitude = "";
					AppVariable.curLongitude = "";
					AppVariable.LOCATION_NAME_ASSIGN.setText("");
					finish();
				}
				finish();

			}
		});

		map.setOnMyLocationChangeListener(new OnMyLocationChangeListener() {
			@Override
			public void onMyLocationChange(Location location) {
				if (flag == 0) {
					map.clear();
					double latitude = location.getLatitude();
					double longitude = location.getLongitude();
					locatio_Values = latitude + "," + longitude;
					AppVariable.curLatitude = String.valueOf(latitude);
					AppVariable.curLongitude = String.valueOf(longitude);
					GetReverseGeoCoding coding = new GetReverseGeoCoding();
					coding.getAddress(String.valueOf(latitude),
							String.valueOf(longitude));
					String address2 = coding.getCity();
					location_name = coding.getAddress2();
					MarkerOptions options = new MarkerOptions()
							.position(new LatLng(latitude, longitude))
							.title(location_name)
							.snippet(address2)
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.floatflag));

					map.addMarker(options).showInfoWindow();
					map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(
							latitude, longitude)));
					map.animateCamera(CameraUpdateFactory.zoomTo(15));
					// map.animateCamera(CameraUpdateFactory.zoomTo(15));
				}
			}
		});
		map.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng arg0) {
				// TODO Auto-generated method stub
				map.clear();
				flag = 1;
				AppVariable.curLatitude = String.valueOf(arg0.latitude);
				AppVariable.curLongitude = String.valueOf(arg0.longitude);
				GetReverseGeoCoding coding = new GetReverseGeoCoding();
				coding.getAddress(String.valueOf(arg0.latitude),
						String.valueOf(arg0.longitude));
				String address1 = coding.getAddress1();
				location_name = coding.getAddress2();
				String address2 = coding.getCity();
				Toast.makeText(
						getApplicationContext(),
						"" + address1 + " : " + location_name + " :" + address2,
						Toast.LENGTH_SHORT).show();

				MarkerOptions options = new MarkerOptions()
						.position(arg0)
						.title(location_name)
						.snippet(address2)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.floatflag));

				map.addMarker(options).showInfoWindow();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (!location_name.equals("")
				&& !(AppVariable.LOCATION_NAME_ASSIGN == null)) {
			AppVariable.selected_location = "";
			AppVariable.curLatitude = "";
			AppVariable.curLongitude = "";
			AppVariable.LOCATION_NAME_ASSIGN.setText("");
		}
		finish();
	}
}
