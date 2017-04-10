package com.ips.ipsactivity.camera;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.ips.ipsactivity.R;
import com.ips.util.AppVariable;

public class ImageViewSet extends Activity {
	ImageView setImage = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);
		setComponents();
		setImages();
	}

	private void setImages() {
		// TODO Auto-generated method stub
		if (!AppVariable.IMAGE_FILE_PATH.equals("")) {
			final RotateAnimation rotateAnim = new RotateAnimation(0.0f, 90,
		            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
		            RotateAnimation.RELATIVE_TO_SELF, 0.5f);

		    rotateAnim.setDuration(0);
		    rotateAnim.setFillAfter(true);
		   
			setImage.setImageBitmap(BitmapFactory
					.decodeFile(AppVariable.IMAGE_FILE_PATH));
			setImage.startAnimation(rotateAnim);
		} else {
			Toast.makeText(getApplicationContext(),
					"No image pls take action...!", Toast.LENGTH_SHORT).show();
		}
	}

	private void setComponents() {
		// TODO Auto-generated method stub
		setImage = (ImageView) findViewById(R.id.setImgimage);
	}

}
