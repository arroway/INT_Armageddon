package org.tsp.android;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapArmageddonActivity extends MapActivity {
	
	private final String search_path = "/sdcard/"; 
    
	private MapView m_mapView;
	private MapController mController;
	private MyItemizedOverlay images;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        m_mapView = (MapView) findViewById(R.id.map_view);
        m_mapView.setBuiltInZoomControls(true);
        
        mController = m_mapView.getController();
        mController.animateTo(new GeoPoint( 48625002, 2442962));
        mController.setZoom(18);
        
        List<Overlay> map_overlays = m_mapView.getOverlays();
        images = new MyItemizedOverlay(this, getResources().getDrawable(R.drawable.blue_dot));
        map_overlays.add(images);
        
        OverlayItem itemMission = new OverlayItem(new GeoPoint(48625119, 2442082), "Start mission", "View your mission");
        images.addOverlay(itemMission);
        
        /* TODO:
         * Display only when the player is in near the location
         */
        OverlayItem itemClue1 = new OverlayItem(new GeoPoint(48625500, 2442282), "First clue", "View first clue");
        images.addOverlay(itemClue1);
        
        OverlayItem itemClue2 = new OverlayItem(new GeoPoint(4862500, 2442482), "First clue", "View first clue");
        images.addOverlay(itemClue2);
        
        OverlayItem itemClue3 = new OverlayItem(new GeoPoint(48625300, 2442682), "First clue", "View first clue");
        images.addOverlay(itemClue3);
        
        OverlayItem itemClue4 = new OverlayItem(new GeoPoint(48625300, 2442882), "First clue", "View first clue");
        images.addOverlay(itemClue4);
        
        OverlayItem itemClue5 = new OverlayItem(new GeoPoint(48625900, 2442882), "First clue", "View first clue");
        images.addOverlay(itemClue5);
        
        
        /*
         * Display only when all the clues have been found
         */
         
        OverlayItem itemFinal = new OverlayItem(new GeoPoint(48625300, 2442082), "First clue", "View first clue");
        images.addOverlay(itemFinal);
       
        
        File dir = new File(search_path);
        String[] image_files = dir.list(new FilenameFilter() {
		
			@Override
			public boolean accept(File dir, String filename) {
				if (filename.endsWith(".jpg"))
					return true;
				return false;
			}
		});

       for (String name : image_files) {
            ExifInterface itf;
    		try {
    			itf = new ExifInterface(search_path+name);
    			float[] latLng = new float[2];
    			if ( !itf.getLatLong(latLng) )
    				continue;
    			OverlayItem image_item = new OverlayItem(new GeoPoint((int)(latLng[0]*1000000), (int)(latLng[1]*1000000)), name, search_path+name);
    			Bitmap bmp = BitmapFactory.decodeFile(search_path+name);
    			bmp = Bitmap.createScaledBitmap(bmp, 100, 100, true);
    			BitmapDrawable bmpd = new BitmapDrawable(bmp);
    			bmpd.setBounds(0, 0, bmpd.getIntrinsicWidth(), bmpd.getIntrinsicWidth());
    			image_item.setMarker(images.fixMarkerImage(bmpd));
    			images.addOverlay(image_item);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
		}
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	/*
	 * 
	 * Menu
	 * 
	 */
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_map, menu);
		return true;
	}
	
	@Override 
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.menu_exit:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	 
}