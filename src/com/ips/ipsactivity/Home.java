package com.ips.ipsactivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Home extends Activity {
	ActionBar actionBar = null;
	public Context context = null;
	DrawerLayout mDrawerLayout = null;
	RelativeLayout mDrawerList = null;
	ActionBarDrawerToggle mDrawerToggle = null;
	TextView chargeSheet = null;
	TextView search = null;
	TextView viewcases = null;
	TextView exit = null;
	LinearLayout chargeclick = null;
	LinearLayout serchclick = null;
	LinearLayout viewcasesclick = null;
	LinearLayout exitclick = null;

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		setComponents();
		setColor(Color.GREEN);
		context = Home.this;
		selectItem("addips", Home.this);
		createToast("");
		exitclick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDrawerLayout.closeDrawer(mDrawerList);
				dialog();
				getActionBar().setTitle("IPS");

			}

		});

		chargeclick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDrawerLayout.closeDrawer(mDrawerList);
				getActionBar().setTitle("Charge Sheet");
				selectItem("chargeshhet", Home.this);
			}
		});

		viewcasesclick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDrawerLayout.closeDrawer(mDrawerList);
				getActionBar().setTitle("View Cases");
				selectItem("viewcases", Home.this);
			}
		});

		serchclick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDrawerLayout.closeDrawer(mDrawerList);
				getActionBar().setTitle("Search");
				selectItem("chargesheetserach", Home.this);
			}
		});

	}

	private void createToast(String msg) {
		// TODO Auto-generated method stub
		Toast toast = new Toast(Home.this);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custome_toast, null);
		toast.setView(view);
		toast.setDuration(10000);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	}

	private void setColor(int red) {
		// TODO Auto-generated method stub
		chargeSheet.setTextColor(red);
		search.setTextColor(red);
		viewcases.setTextColor(red);
		exit.setTextColor(red);
	}

	private void setComponents() {
		// TODO Auto-generated method stub
		chargeSheet = (TextView) findViewById(R.id.dateandtimetextView);
		search = (TextView) findViewById(R.id.textView2);
		viewcases = (TextView) findViewById(R.id.textView3);
		exit = (TextView) findViewById(R.id.textView4);

		chargeclick = (LinearLayout) findViewById(R.id.chargesheetclick);
		serchclick = (LinearLayout) findViewById(R.id.serachclick);
		viewcasesclick = (LinearLayout) findViewById(R.id.viewcasesclick);
		exitclick = (LinearLayout) findViewById(R.id.exitclick);
		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (RelativeLayout) findViewById(R.id.left_drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.open, R.string.close);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		dialog();
		// super.onBackPressed();
	}

	private void dialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new Builder(Home.this);
		builder.setTitle("IPS");
		builder.setMessage("Do you want to exit..?");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// nothoing is here....!
			}
		}).create();
		builder.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.login, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		} else if (item.getItemId() == R.id.About) {
			aboutDialogue();
		} else if (item.getItemId() == R.id.Exit) {
			dialog();
		}
		return super.onOptionsItemSelected(item);
	}

	private void aboutDialogue() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new Builder(Home.this);
		builder.setTitle("About");
		builder.setMessage("Traffic on roads may consist of "
				+ "pedestrians, ridden or herded animals, vehicles, "
				+ "streetcars and other conveyances, either singly or "
				+ "together, while using the public way for purposes of "
				+ "travel. Traffic laws are the laws which govern traffic "
				+ "and regulate vehicles, while rules of the road are both"
				+ " the laws and the informal rules that may have developed"
				+ " over time to facilitate the orderly and timely flow of traffic.");
		// builder.setIcon(R.drawable.info_icon);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).create();
		builder.show();
	}

	private void selectItem(String name, Context context) {
		// TODO Auto-generated method stub

		Fragment fragment = new PlanetFragment(name, context);
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
	}

	public static class PlanetFragment extends Fragment {
		String name = null;
		View rootView = null;
		Context context = null;

		public PlanetFragment(String layoutName, Context context) {
			// Empty constructor required for fragment subclasses
			name = layoutName;
			this.context = context;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			if (name.equals("chargesheetserach")) {

				rootView = inflater.inflate(R.layout.chargesheet, container,
						false);
				new SetView(this, rootView, name, context);

			} else if (name.equals("viewcases")) {
				rootView = inflater.inflate(R.layout.viwepolicecases,
						container, false);
				new SetView(this, rootView, name, context);
			} else if (name.equals("chargeshhet")) {
				rootView = inflater.inflate(R.layout.chargeshhet, container,
						false);
				new SetView(this, rootView, name, context);
			} else {
				rootView = inflater.inflate(R.layout.addips, container, false);
				new SetView(this, rootView, name, context);
			}

			return rootView;
		}
	}
}
