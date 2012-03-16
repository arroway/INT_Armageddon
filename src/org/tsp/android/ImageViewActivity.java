package org.tsp.android;

import org.tsp.android.R;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {
	
	public static String FileName = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view);
        
        if (FileName == null)
        	this.finish();
        
        // To Complete
        // Load image here
	}

}
