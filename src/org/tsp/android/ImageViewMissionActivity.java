package org.tsp.android;

import org.tsp.android.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewMissionActivity extends Activity {
	
	public static String FileName = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mission);
        
        if (FileName == null)
        	this.finish();
                
        // To Complete
        // Load image here
  /*  	Bitmap bmp = BitmapFactory.decodeFile("/sdcard/mission.jpg");
		ImageView img = (ImageView) findViewById(R.id.img_view);
		img.setImageBitmap(bmp);
		*/
	}

}
