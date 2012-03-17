package org.tsp.android;

import org.tsp.android.R;

import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class ImageViewMissionActivity extends Activity {
	
//	public static String FileName = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mission);
        
//        if (FileName == null)
//        	this.finish();
    
        
		ImageView image = new ImageView(this);
		image.setImageResource(R.drawable.mission);  
	}
	public void myClickHandler(View view) {
      	finish(); 
	}
		
	/*	AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Start mission");
        //dialog.setMessage(item.getSnippet());
      //  LayoutInflater li = this.getLayoutInflater();
       // View view_mission = li.inflate(R.layout.view_mission, null);
        dialog.setPositiveButton("Back", new DialogInterface.OnClickListener(){
        	public void onClick(DialogInterface dialog, int id) {
        		setResult(Activity.RESULT_OK);
            	dialog.dismiss();
   	       		finish();     	
   	      }
         });
        
   	    dialog.show();        
*/
	
}
