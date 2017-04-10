package com.ips.ipsactivity.camera;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.RotateAnimation;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ips.ipsactivity.R;
import com.ips.util.AppVariable;

public class VideoViewSet extends Activity {
	VideoView videoplayer = null;
	MediaController controller = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoplayer);
		setComponents();
	}

	private void setComponents() {
		// TODO Auto-generated method stub
		videoplayer = (VideoView) findViewById(R.id.videoView1);
		controller = new MediaController(this);
		controller.setAnchorView(videoplayer);
		videoplayer.setMediaController(controller);
		videoplayer.setVideoURI(Uri.parse(AppVariable.VIDEO_FILEPATH));
		videoplayer.requestFocus();
		videoplayer.start();
	}

}
