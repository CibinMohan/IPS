package com.ips.ipsactivity;

import java.io.File;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ips.services.WebService;
import com.ips.util.AppConstance;

public class Login extends Activity {
	EditText username = null;
	EditText password = null;
	Button login = null;
	SharedPreferences preferences = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		File maked = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ AppConstance.CREATED_FILE_NAME + File.separator);

		File tempFile = new File(AppConstance.TEMP_FILE_NAME);

		if (!maked.isDirectory()) {
			if (maked.mkdirs()) {
				tempFile.mkdirs();

			} else {
				Toast.makeText(getApplicationContext(),
						"Error while creating files..!", Toast.LENGTH_SHORT)
						.show();
				finish();
			}
		}
		setComponents();
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String usrName = username.getText().toString();
				String pswd = password.getText().toString();

				if (usrName.equals("")) {
					username.setError("Enter the username");
				} else if (pswd.equals("")) {
					password.setError("Enter your password");
				} else {
					new LoginTask().execute(usrName, pswd);
				}

			}
		});

	}

	private void setComponents() {
		// TODO Auto-generated method stub
		preferences = getSharedPreferences(AppConstance.SHARED_PREF, 0);
		username = (EditText) findViewById(R.id.userNameEditText);
		password = (EditText) findViewById(R.id.userpasswordEditText);
		login = (Button) findViewById(R.id.loginButton);
	}

	private class LoginTask extends AsyncTask<String, String, String> {
		ProgressDialog dialog = new ProgressDialog(Login.this);

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			String resp = new WebService(Login.this)
					.login(params[0], params[1]);
			return resp;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			dialog.dismiss();
			if (result.equals("error")) {
				Toast.makeText(getApplicationContext(), "Network error..!",
						Toast.LENGTH_SHORT).show();
			} else if (result.equals("true")) {
				Editor edit = preferences.edit();
				edit.putString("username", username.getText().toString());
				edit.putString("password", password.getText().toString());
				edit.commit();
				startActivity(new Intent(Login.this, Home.class));
				finish();
			} else {
				Toast.makeText(getApplicationContext(),
						"Invalid username or password..!", Toast.LENGTH_SHORT)
						.show();
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			dialog.show();
			dialog.setCanceledOnTouchOutside(false);
			dialog.setIndeterminate(true);
			dialog.setContentView(R.layout.progress);
		}
	}

}
