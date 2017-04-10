package com.ips.ipsactivity.camera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.ips.ipsactivity.R;
import com.ips.util.AppVariable;

public class CameraImage extends Activity implements SurfaceHolder.Callback {

	Camera camera;
	private SurfaceView prSurfaceView;
	private SurfaceHolder prSurfaceHolder;
	Button buttonClick;
	static Boolean imageTaken;
	String path = "mnt/sdcard/IPS_FILES/Images/";
	static long time = 0;
	FileOutputStream outStream = null;

	/** Called when the activity is first created. */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_image);

		File f = new File(path);
		if (!f.isDirectory()) {
			f.mkdir();
		}

		imageTaken = false;
		prSurfaceView = (SurfaceView) findViewById(R.id.preview);
		// prSurfaceView = new SurfaceView(this);
		prSurfaceHolder = prSurfaceView.getHolder();
		prSurfaceHolder.addCallback(CameraImage.this);
		prSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		buttonClick = (Button) findViewById(R.id.buttonClick);

		Animation rotation = AnimationUtils.loadAnimation(this, R.anim.anim);
		buttonClick.setAnimation(rotation);
		// setAnimation();
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Log.d("Sapcron","Latitude : " + AppConstants.latitude +
				// " and Longitude : " + AppConstants.longitude);

				camera.takePicture(shutterCallback, rawCallback, jpegCallback);

			}
		});

	}

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {

		}
	};

	/** Handles data for raw picture */
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {

		}
	};

	/** Handles data for jpeg picture */
	PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(final byte[] data, Camera camera) {

			try {
				buttonClick.clearAnimation();
				AlertDialog.Builder builder = new Builder(CameraImage.this);
				builder.setTitle("IPS");
				builder.setMessage("Are you sure or retake...?");

				builder.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								String filepath = path
										+ String.valueOf(System
												.currentTimeMillis()) + ".jpg";
								File file = new File(filepath);
								AppVariable.IMAGE_FILE_PATH = filepath;
								AppVariable.IMAGE_FILE = file;

								try {
									outStream = new FileOutputStream(file);
									outStream.write(data);
									outStream.close();

								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								finish();
								Toast.makeText(getApplicationContext(),
										"Please tap the image view..!",
										Toast.LENGTH_LONG).show();
							}
						});

				builder.setNegativeButton("Retake",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								reload();
							}
						});
				builder.create().show();
				imageTaken = true;

			} finally {
				// reload();

			}

		}
	};

	@SuppressLint("NewApi")
	public void surfaceChanged(SurfaceHolder _holder, int _format, int _width,
			int _height) {
		try {
			if (camera != null) {
				Camera.Parameters lParam = camera.getParameters();
				camera.setDisplayOrientation(90);
				// lParam.setPictureSize(640, 480);
				camera.setParameters(lParam);
				camera.setPreviewDisplay(_holder);
				camera.startPreview();

			}
		} catch (IOException _le) {
			Toast.makeText(getApplicationContext(), "Cannot start camera",
					Toast.LENGTH_SHORT).show();
			_le.printStackTrace();
		}

	}

	public void surfaceCreated(SurfaceHolder arg0) {
		try {
			camera = Camera.open();

			if (camera == null) {
				finish();
			}
		} catch (Exception e) {
			Toast.makeText(CameraImage.this, "Cannot start camera",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		if (camera != null) {
			camera.stopPreview();

			camera.release();
			camera = null;
		}
	}

	public void reload() {
		surfaceDestroyed(prSurfaceHolder);
		Intent intent = getIntent();
		overridePendingTransition(0, 0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();

		overridePendingTransition(0, 0);
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		// super.onBackPressed();
	}
}
