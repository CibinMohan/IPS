package com.ips.ipsactivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ips.adaptor.MyCustomAdapter;
import com.ips.bean.CaseBean;
import com.ips.ipsactivity.Home.PlanetFragment;
import com.ips.ipsactivity.camera.AndroidVideoCapture;
import com.ips.ipsactivity.camera.CameraImage;
import com.ips.ipsactivity.camera.ImageViewSet;
import com.ips.ipsactivity.camera.VideoViewSet;
import com.ips.services.WebService;
import com.ips.util.AppConstance;
import com.ips.util.AppVariable;
import com.ips.util.StringToArrayList;

@SuppressLint("NewApi")
public class SetView {
	EditText locationedittext = null;
	EditText adharnumberedittext = null;
	EditText vaehiclenumber = null;
	EditText nameedittext = null;
	EditText address = null;
	EditText victimedittext = null;
	Spinner caseType = null;
	EditText phoneeditetext = null;
	ArrayList<String> caseTypeArray = null;
	TextView dateandtime = null;
	EditText offenceedittext = null;
	EditText emailidedittext = null;
	Button addbutton = null;
	Button mapclick = null;
	Button captureclick = null;
	Context context = null;
	ImageView capturedImage;
	PopupMenu menu = null;
	EditText searchtext = null;
	RadioButton adharNumber = null;
	RadioButton vehicleNumber = null;
	String radioTitle = "";
	String searchContent = "";
	ArrayList<CaseBean> searchResults = null;
	PopupMenu menuHere = null;
	SharedPreferences preferences = null;
	boolean okStatus = true;
	String tempFilePath = "";
	View view = null;
	protected boolean flag_for_EMAIL_CHECKING = false;

	public SetView(PlanetFragment planetFragment, View rootView, String name,
			Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.view = rootView;
		preferences = context.getSharedPreferences(AppConstance.SHARED_PREF, 0);
		setViews(rootView, planetFragment, name, context);

	}

	@SuppressLint("NewApi")
	private void setViews(View rootView, PlanetFragment planetFragment,
			String name, final Context context) {
		// TODO Auto-generated method stub
		if (name.equals("chargeshhet")) {

			locationedittext = (EditText) rootView
					.findViewById(R.id.locationeditText);
			AppVariable.LOCATION_NAME_ASSIGN = locationedittext;

			adharnumberedittext = (EditText) rootView
					.findViewById(R.id.adharnumbereditText);
			nameedittext = (EditText) rootView.findViewById(R.id.nameeditText);

			vaehiclenumber = (EditText) rootView
					.findViewById(R.id.vehiclenumbereditText);
			phoneeditetext = (EditText) rootView
					.findViewById(R.id.phoneeditText);
			caseType = (Spinner) rootView.findViewById(R.id.caseTypespinner);

			dateandtime = (TextView) rootView
					.findViewById(R.id.dateandtimetextView);
			offenceedittext = (EditText) rootView
					.findViewById(R.id.offenceeditText);
			emailidedittext = (EditText) rootView
					.findViewById(R.id.emaiideditText);
			address = (EditText) rootView
					.findViewById(R.id.addharEditFromresulteditText);
			victimedittext = (EditText) rootView
					.findViewById(R.id.victimadarnumbereditText);
			addbutton = (Button) rootView
					.findViewById(R.id.addchargesheetbutton);
			mapclick = (Button) rootView.findViewById(R.id.mapclickbutton);
			// setDefaultLocation name
			String location_name = preferences.getString("LOCATION_NAME", "");
			if (!location_name.equals("")) {
				locationedittext.setText(location_name);
			}
			//

			// ======================================
			// pls change these examples....!
			caseTypeArray = new ArrayList<String>();
			caseTypeArray.add("<Case Type>");
			caseTypeArray.add("Documents");
			caseTypeArray.add("Driving");
			caseTypeArray.add("Signal");
			caseTypeArray.add("Speed & Overtaking");
			caseTypeArray.add("Pollution");
			caseTypeArray.add("Parking");

			// ======================================
			caseType.setAdapter(new MyCustomAdapter(context, R.layout.row,
					caseTypeArray));

			capturedImage = (ImageView) rootView
					.findViewById(R.id.captureimageView);

			locationedittext.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (!AppVariable.curLatitude.equals("")
							&& !AppVariable.curLongitude.equals("")) {
						locationedittext.setError(AppVariable.curLatitude + ","
								+ AppVariable.curLongitude);
					}
					return false;
				}
			});

			capturedImage.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					if (!AppVariable.IMAGE_FILE_PATH.equals("")
							&& !AppVariable.VIDEO_FILEPATH.equals("")) {
						// context.startActivity(new Intent(context,
						// / ImageViewSet.class));
						playerDialogue();
					} else if (!AppVariable.IMAGE_FILE_PATH.equals("")) {
						SetView.this.context.startActivity(new Intent(context,
								ImageViewSet.class));
					} else if (!AppVariable.VIDEO_FILEPATH.equals("")) {
						SetView.this.context.startActivity(new Intent(context,
								VideoViewSet.class));
					} else {
						Toast.makeText(context, "No valid resources",
								Toast.LENGTH_SHORT).show();
					}

					return false;
				}

				private void playerDialogue() {
					// TODO Auto-generated method stub
					AlertDialog.Builder builder = new Builder(context);
					builder.setTitle("IPS");
					builder.setMessage("Image or Video..?");
					builder.setIcon(R.drawable.ic_launcher);
					builder.setPositiveButton("Image",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									SetView.this.context
											.startActivity(new Intent(context,
													ImageViewSet.class));
								}
							});

					builder.setNeutralButton("Video",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									SetView.this.context
											.startActivity(new Intent(context,
													VideoViewSet.class));
								}
							});
					builder.setNegativeButton("Clear",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									AppVariable.IMAGE_FILE.delete();
									AppVariable.IMAGE_FILE_PATH = "";
									AppVariable.VIDEO_FILEPATH = "";
								}
							}).create().show();
				}
			});

			captureclick = (Button) rootView.findViewById(R.id.capturebutton);
			dateandtime.setTextColor(Color.WHITE);
			dateandtime.setText(getDate());
			getValuesFromComponents();
			addbutton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					preferences = context.getSharedPreferences(
							AppConstance.SHARED_PREF, 0);
					if (locationedittext.getText().toString().equals("")) {
						locationedittext.setError("Enter the location");
						Toast.makeText(context, "Enter the location",
								Toast.LENGTH_SHORT).show();
					} else if (nameedittext.getText().toString().equals("")) {
						nameedittext.setError("Enter the name");
						Toast.makeText(context, "Enter the name",
								Toast.LENGTH_SHORT).show();
					} else if (adharnumberedittext.getText().toString()
							.equals("")
							|| adharnumberedittext.getText().toString()
									.getBytes().length < 12
							|| adharnumberedittext.getText().toString()
									.getBytes().length > 12) {
						adharnumberedittext
								.setError("Enter a valid aadhar number it should be 12 digits");
						Toast.makeText(context, "Enter a valid aadhar number",
								Toast.LENGTH_SHORT).show();

					} else if (vaehiclenumber.getText().toString().equals("")) {
						vaehiclenumber.setError("Enter the vahicle number");
						Toast.makeText(context, "Enter the vahicle number",
								Toast.LENGTH_SHORT).show();
					} else if (phoneeditetext.getText().toString().equals("")
							|| phoneeditetext.getText().toString().getBytes().length < 10
							|| phoneeditetext.getText().toString().getBytes().length > 10) {
						phoneeditetext.setError("Enter a valid phone number");
						Toast.makeText(context, "Enter a valid phone number",
								Toast.LENGTH_SHORT).show();
					} else if (caseType.getSelectedItem().toString()
							.equals("<Case Type>")) {

						Toast.makeText(context, "Case type", Toast.LENGTH_SHORT)
								.show();
					} else if (offenceedittext.getText().toString().equals("")) {
						offenceedittext.setError("Enter the reasons");
						Toast.makeText(context, "Enter the reasons",
								Toast.LENGTH_SHORT).show();
					} else if (emailidedittext.getText().toString().equals("")) {
						emailidedittext.setError("Enter the emailid");
						Toast.makeText(context, "Enter the emailid",
								Toast.LENGTH_SHORT).show();
					} else if (address.getText().toString().equals("")) {
						address.setError("Enter the address");
						Toast.makeText(context, "Enter the address",
								Toast.LENGTH_SHORT).show();
					} else if (AppVariable.IMAGE_FILE_PATH.equals("")) {
						Toast.makeText(context, "Please take image",
								Toast.LENGTH_SHORT).show();

					} //else if (AppVariable.VIDEO_FILEPATH.equals("")) {
						//Toast.makeText(context, "Please take video",
						//		Toast.LENGTH_SHORT).show();

					//} 
				else if (victimedittext.getText().toString().equals("")
							|| victimedittext.getText().toString().getBytes().length < 12
							|| victimedittext.getText().toString().getBytes().length > 12) {
						victimedittext
								.setError("Enter a valid victim's adhar number");
						Toast.makeText(context,
								"Enter a valid victim's adhar number",
								Toast.LENGTH_SHORT).show();
					} else if (!flag_for_EMAIL_CHECKING) {
						emailidedittext.setError("Enter a valid email id");
					} else {
						new AddingCaseUiThered().execute();
					}
				}

			});

			// for testing email id

			emailidedittext.addTextChangedListener(new TextWatcher() {
				String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
				Pattern p = Pattern.compile(regEx);

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub
					Matcher m = p.matcher(s);

					if (m.find()) {
						flag_for_EMAIL_CHECKING = true;
					} else {
						flag_for_EMAIL_CHECKING = false;
					}

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub

				}
			});
			// ==========================

			captureclick.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog();
				}
			});

			mapclick.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SetView.this.context.startActivity(new Intent(context,
							Map.class));
				}
			});
		} else if (name.equals("viewcases")) {
			// calling ui thered
			new HandleViewCase().execute();
		} else if (name.equals("chargesheetserach")) {

			final Button searchForVahicle = (Button) rootView
					.findViewById(R.id.searchForVahiclebutton);

			Button anim = (Button) rootView.findViewById(R.id.animationbutton);

			Animation animFadein = AnimationUtils.loadAnimation(context,
					R.anim.fade_in);

			anim.startAnimation(animFadein);

			searchtext = (EditText) rootView
					.findViewById(R.id.addharEditFromresulteditText);

			adharNumber = (RadioButton) rootView.findViewById(R.id.adaharradio);

			vehicleNumber = (RadioButton) rootView
					.findViewById(R.id.vehiclenumberradio);
			// ==================================================================

			// defualt value
			radioTitle = adharNumber.getText().toString();
			// ===========================================
			adharNumber.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					radioTitle = adharNumber.getText().toString();
					searchForVahicle.setVisibility(View.INVISIBLE);
				}
			});

			vehicleNumber.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					searchForVahicle.setVisibility(View.VISIBLE);
					radioTitle = vehicleNumber.getText().toString();

				}
			});

			// ====================================================================

			searchtext.addTextChangedListener(new TextWatcher() {

				@SuppressLint("NewApi")
				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub

					if (radioTitle.equals(adharNumber.getText().toString())) {
						if (start + 1 >= 12) {

							AsyncTask<String, String, String> execute = new SyncResultTask()
									.execute(searchtext.getText().toString(),
											"adhar");
							// manipulate the results from adhar number
							try {
								String valUfromWeb = execute.get();
								searchResults = new StringToArrayList()
										.getObject(valUfromWeb);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								searchtext.setError(Html
										.fromHtml("<font color=green>Network error..!</font>"));
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								searchtext.setError(Html
										.fromHtml("<font color=green>Network error..!</font>"));
								e.printStackTrace();
							} catch (NullPointerException e) {
								// TODO: handle exception
								searchtext.setError(Html
										.fromHtml("<font color=green>Network error..!</font>"));
							}
							if (searchResults != null) {

								if (searchResults.size() > 0) {
									AppVariable.SEARCH_RESULT_ARRY_LIST = searchResults;

									SetView.this.context
											.startActivity(new Intent(
													SetView.this.context,
													SearchResults.class));
								} else {
									searchtext.setError(Html
											.fromHtml("<font color=green>No result found..!</font>"));
								}
							} else {
								searchtext.setError(Html
										.fromHtml("<font color=green>No result found..!</font>"));
							}
						}

					} else if (radioTitle.equals(vehicleNumber.getText()
							.toString())) {

						searchForVahicle
								.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub

										if (!searchtext.getText().toString()
												.equals("")) {
											AsyncTask<String, String, String> execute = new SyncResultTask()
													.execute(searchtext
															.getText()
															.toString(),
															"vahicle");
											// manipulate the results from
											// vahicle
											// search
											try {
												String resultFromWeb = execute
														.get();
												searchResults = new StringToArrayList()
														.getObject(resultFromWeb);
												if (searchResults != null) {
													if (searchResults.size() > 0) {
														AppVariable.SEARCH_RESULT_ARRY_LIST = searchResults;
														SetView.this.context
																.startActivity(new Intent(
																		SetView.this.context,
																		SearchResults.class));
													} else {
														searchtext.setError(Html
																.fromHtml("<font color=green>No result found..!</font>"));
													}
												} else {
													searchtext.setError(Html
															.fromHtml("<font color=green>No result found..!</font>"));
												}
											} catch (InterruptedException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											} catch (ExecutionException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										} else {
											searchtext
													.setError("Pls enter the number..!");
										}
									}
								});

					}

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub

				}
			});

		} else {

		}
	}

	private CharSequence getDate() {
		// TODO Auto-generated method stub
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formattime = new SimpleDateFormat("hh:mm:ss");
		String format2 = formatDate.format(new Date());
		String format = formattime.format(new Date());

		return format2 + " : " + format;
	}

	protected void dialog() {
		// TODO Auto-generated method stub

		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("IPS");
		builder.setMessage("Take action..?");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setPositiveButton("Image",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						context.startActivity(new Intent(context,
								CameraImage.class));
						// ((Activity) context).finish();

					}
				})
				.setNeutralButton("Video",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

								context.startActivity(new Intent(context,
										AndroidVideoCapture.class));
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						}).create();
		builder.show();
	}

	private void getValuesFromComponents() {
		// TODO Auto-generated method stub

	}

	// asynch task for searching
	private class SyncResultTask extends AsyncTask<String, String, String> {
		ProgressDialog dialog = new ProgressDialog(context);

		// ArrayList<String> searchResults1 = null;

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			if ((params[1]).equals("adhar")) {
				return new WebService(context).serachResultForAdhar(params[0]);
			} else {
				return new WebService(context).serachResultVahicle(params[0]);
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

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			dialog.dismiss();
			if (result.equals("error")) {
				Toast.makeText(context, "Newtwork error...!",
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	// asynch task for view polices added cases
	private class ViewPoliceCase extends AsyncTask<String, String, String> {

		ProgressDialog dialog = new ProgressDialog(context);

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			String rsp = new WebService(context).getAllTodayCases(params[0]);
			return rsp;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			dialog.dismiss();

			if (result.equals("error")) {
				Toast.makeText(context, "Erro while loading",
						Toast.LENGTH_SHORT).show();
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

	// asynch task for case adding
	private class SenDCaseSynch extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String rsp = new WebService(context).sendCaseFile(params[0],
					params[1], params[2]);
			return rsp;
		}

	}

	// asunch for ui controll

	private class HandleViewCase extends AsyncTask<String, String, String> {
		ArrayList<CaseBean> beans = null;
		String result;
		ArrayList<String> list = new ArrayList<String>();
		List<String> spin = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		final Spinner viewCaseSpinner = (Spinner) view
				.findViewById(R.id.viewCaseSpinner);
		Button searchFromPolice = (Button) view
				.findViewById(R.id.searchFromViewpoliceCaseButton);

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			// =================================================================

			viewCaseSpinner.setOnItemSelectedListener(new CostomeListener());

			return null;
		}

		@Override
		protected void onPostExecute(String results) {
			// TODO Auto-generated method stub

			AsyncTask<String, String, String> execute = new ViewPoliceCase()
					.execute(preferences.getString("username", ""));

			try {
				result = execute.get();
				if (!result.equals("") || !result.equals(null)
						|| !result.equals("error")) {

					beans = new StringToArrayList().getObject(result);

					if (beans.size() > 0) {

						for (int i = 0; i < beans.size(); i++) {
							list.add(beans.get(i).getCase_type());
							map.put(i, beans.get(i).getCase_id());
						}

						spin = list;
						AppVariable.SEARCH_RESULT_ARRY_LIST = beans;
						AppVariable.HASH_MAP = map;
						searchFromPolice.setVisibility(View.VISIBLE);
					} else {
						Toast.makeText(context, "No cases here",
								Toast.LENGTH_SHORT).show();
						list.add("No items here");
						spin = list;
						searchFromPolice.setVisibility(View.INVISIBLE);
					}

				} else {

					list.add("No items here");
					spin = list;
					searchFromPolice.setVisibility(View.INVISIBLE);
				}

				viewCaseSpinner.setAdapter(new MyCustomAdapter(context,
						R.layout.row, list));
				searchFromPolice.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						context.startActivity(new Intent(context,
								SearchResultView.class));
					}
				});
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NullPointerException e2) {
				// TODO: handle exception
				Toast.makeText(context, "Network error", Toast.LENGTH_SHORT)
						.show();
				e2.printStackTrace();
			}

		}

	}

	// asynch task for ui thred adding cases

	private class AddingCaseUiThered extends
			AsyncTask<Boolean, Boolean, Boolean> {
		ProgressDialog dialog = new ProgressDialog(context);
		ObjectOutputStream objectOutputStream = null;
		boolean objetWritingStatus = true;
		CaseBean bean = new CaseBean();

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

			dialog.show();
			dialog.setContentView(R.layout.progressupload);
		}

		@Override
		protected Boolean doInBackground(Boolean... params) {
			// TODO Auto-generated method stub

			bean.setUsername(preferences.getString("username", ""));

			bean.setLocation(preferences.getString("LOCATION_VALUE", ""));

			bean.setSuspect_Name(nameedittext.getText().toString());

			bean.setSuspect_AddarNo(adharnumberedittext.getText().toString());

			bean.setVehicle_No(vaehiclenumber.getText().toString());

			bean.setPhone(phoneeditetext.getText().toString());

			bean.setCase_type(caseType.getSelectedItem().toString());

			bean.setCase_Comands(offenceedittext.getText().toString());

			bean.setEmail(emailidedittext.getText().toString());

			bean.setAddress(address.getText().toString());

			bean.setVictim_Adhaar_No(victimedittext.getText().toString());

			// make a temp file for writing object
			if (okStatus) {

				try {
					tempFilePath = AppConstance.TEMP_FILE_NAME + File.separator
							+ System.currentTimeMillis() + ".temp";
					objectOutputStream = new ObjectOutputStream(
							new FileOutputStream(new File(tempFilePath)));

					objectOutputStream.writeObject(bean);
					objectOutputStream.flush();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					objetWritingStatus = false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					objetWritingStatus = false;
				} finally {
					try {
						objectOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						objetWritingStatus = false;
					}
				}
			} else {
				return okStatus;
			}
			return objetWritingStatus;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			if (result) {

				AsyncTask<String, String, String> execute = new SenDCaseSynch()
						.execute(tempFilePath, AppVariable.IMAGE_FILE_PATH,
								AppVariable.VIDEO_FILEPATH);

				try {
					String rsp = execute.get();
					if (rsp.equals("error")) {
						dialog.dismiss();
						Toast.makeText(context, "Network error",
								Toast.LENGTH_SHORT).show();
						Log.d("TAG", rsp);
					} else if (rsp.equals("true")) {
						dialog.dismiss();
						Toast.makeText(context, "Successfully added..!",
								Toast.LENGTH_SHORT).show();
						Log.d("TAG", rsp);
						clearFields();
					} else if (rsp.equals("ok")) {
						dialog.dismiss();
						setNotification();
						clearFields();
						Log.d("TAG", rsp);
					} else {
						Toast.makeText(
								context,
								"Some error occure while adding cases please try again..!",
								Toast.LENGTH_SHORT).show();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(context, "Network error..!",
							Toast.LENGTH_SHORT).show();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(context, "Network error..!",
							Toast.LENGTH_SHORT).show();
				}

			} else {
				Toast.makeText(context,
						"Some error occured while creating case..!",
						Toast.LENGTH_SHORT).show();
			}

		}

		private void clearFields() {
			// TODO Auto-generated method stub

			nameedittext.setText("");
			adharnumberedittext.setText("");
			vaehiclenumber.setText("");
			phoneeditetext.setText("");
			caseType.setSelection(0);
			offenceedittext.setText("");
			emailidedittext.setText("");
			address.setText("");
			victimedittext.setText("");
			AppVariable.IMAGE_FILE_PATH = "";
			AppVariable.VIDEO_FILEPATH = "";
			AppVariable.IMAGE_FILE = null;

		}

		private void setNotification() {
			// TODO Auto-generated method stub
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
					context).setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("Please Block the vehicle")
					.setContentText("Auto Theft..!");
			Intent resultIntent = new Intent(context, NewMessageFromWeb.class);

			TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
			stackBuilder.addParentStack(NewMessageFromWeb.class);
			stackBuilder.addNextIntent(resultIntent);
			PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
					0, PendingIntent.FLAG_UPDATE_CURRENT);
			Uri notification = RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone r = RingtoneManager.getRingtone(context, notification);
			r.play();
			mBuilder.setContentIntent(resultPendingIntent);
			NotificationManager mNotificationManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			mNotificationManager.notify(1, mBuilder.build());

		}

	}

}
