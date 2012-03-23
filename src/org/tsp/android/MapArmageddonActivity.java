package org.tsp.android;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
	private MyPathItemizedOverlay pathImages;
	
	/*
	 * GPS - location
	 * 
	 */
	private String provider;
	private LocationManager myLocationManager;
	private LocationListener myLocationListener;
	private int lat;
	private int lng;
	
	//private int score = 5;
	private int score = 0;
	private boolean finalEnigma = false;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        m_mapView = (MapView) findViewById(R.id.map_view);
        m_mapView.setBuiltInZoomControls(true);
        
        mController = m_mapView.getController();
        mController.animateTo(new GeoPoint(48625002, 2442962));
        mController.setZoom(18);
        
        List<Overlay> map_overlays = m_mapView.getOverlays();
        images = new MyItemizedOverlay(this, getResources().getDrawable(R.drawable.blue_dot));
        map_overlays.add(images);
        
        OverlayItem itemMission = new OverlayItem(new GeoPoint(48625119, 2442082), "Start mission", "View your mission");
        images.addOverlay(itemMission);
        Toast.makeText(this, "Click on the blue item to discover your mission", Toast.LENGTH_LONG).show();
    
        pathImages = new MyPathItemizedOverlay(this, getResources().getDrawable(R.drawable.blue_dot));
        map_overlays.add(pathImages);
        
        myLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        myLocationListener = new myLocationListener(this, images, pathImages);
		Criteria criteria = new Criteria();
		provider = myLocationManager.getBestProvider(criteria, false);
		
		
		//add alert dialog with "waiting for location"
		Location location = myLocationManager.getLastKnownLocation(provider);
		myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, myLocationListener);
		
		// Initialize the location fields
		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			lat = (int) (location.getLatitude() * 1000000);
			lng = (int) (location.getLongitude() * 1000000);
			Toast.makeText(this, "Longitude: " + lng + " - Latitude: " + lat,
					Toast.LENGTH_SHORT).show();
	       /*mController.setCenter(new GeoPoint(lat, lng));*/

		} else {
			Toast.makeText(this, "No provider available",
					Toast.LENGTH_SHORT).show();	
		}         
		
        /*
         * Display only when all the clues have been found
         */
         
		if (this.score == 5){			
			Drawable iconFinal = getResources().getDrawable(R.drawable.stargate);
			iconFinal.setBounds(0, 0, iconFinal.getIntrinsicWidth(), iconFinal.getIntrinsicHeight());
			OverlayItem itemFinal = new OverlayItem(new GeoPoint(48627000, 2441082), "First clue", "View first clue");
			itemFinal.setMarker(iconFinal);
			images.addOverlay(itemFinal);
	    	Toast.makeText(this, "Click on the stargate!", Toast.LENGTH_SHORT).show();
	    	this.finalEnigma = true;
	    	
		}
           
    /*    File dir = new File(search_path);
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
		}*/
    }

    
    public void setLat(int lat){
    	this.lat = lat;
    }
    
    public void setLng(int lng){
    	this.lng = lng;
    }
    
    public int getLat(){
    	return this.lat;
    }
    
    public int getLng(){
    	return this.lng;
    }
    
    public int getScore(){
    	return this.score;
    }
    
    public void incrementScore(){
    	this.score++;
    }
    
    public boolean getFinalEnigma(){
    	return this.finalEnigma;
    }
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		myLocationManager.requestLocationUpdates(provider, (long) 400, (float) 1, myLocationListener);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		myLocationManager.removeUpdates(myLocationListener);
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