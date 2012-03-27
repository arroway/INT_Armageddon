package org.tsp.android;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class myLocationListener implements LocationListener {

	private MapArmageddonActivity mContext;
	private MyItemizedOverlay mImages;
	private MyPathItemizedOverlay pathImages;
	
	private boolean clue_discover_1 = false;
	private boolean clue_discover_2 = false;
	private boolean clue_discover_3 = false;


	private Drawable iconPath;
	
	public myLocationListener(MapArmageddonActivity activity, MyItemizedOverlay images, MyPathItemizedOverlay pImages){
		mContext = activity;
		mImages = images;
		pathImages = pImages;
		
	}

	@Override
	public void onLocationChanged(Location location) {
		mContext.setLat( (int) (location.getLatitude() * 1000000));
		mContext.setLng( (int) (location.getLongitude() *1000000));
		Toast.makeText(mContext, "Longitude: " + mContext.getLng() + " - Latitude: " + mContext.getLat(),
	    			Toast.LENGTH_SHORT).show();
		
		
		/*
		 * Display the clues 
		 * 
		 */
		 
        if( (mContext.getLng() > 48625800) &&
        	(mContext.getLng() < 48626000) &&
        	(mContext.getLat() > 2442182) &&
        	(mContext.getLat() < 2442382) &&
        	!clue_discover_1 ){
        	
        	OverlayItem itemClue1 = new OverlayItem(new GeoPoint(48625900, 2442282), "First clue", "View first clue");
            mImages.addOverlay(itemClue1);
    		Toast.makeText(mContext, "You are now near a new clue. Click on the map to make it appear.",
    				Toast.LENGTH_SHORT).show();
    		clue_discover_1 = true;
			mContext.incrementScore();
        } else 
        
        if( (mContext.getLng() > 48625400) &&
            (mContext.getLng() < 48625600) &&
           ( mContext.getLat() > 2442382) &&
           ( mContext.getLat() < 2442582) &&
           !clue_discover_2 ){
            	
            OverlayItem itemClue2 = new OverlayItem(new GeoPoint(48625500, 2442482), "First clue", "View first clue");
            mImages.addOverlay(itemClue2);
        	Toast.makeText(mContext, "You are now near a new clue. Click on the map to make it appear.",
        			Toast.LENGTH_SHORT).show(); 
    		clue_discover_2 = true;
			mContext.incrementScore();

         } else
        
        if( (mContext.getLng() > 48625200) &&
            (mContext.getLng() < 48625400) &&
            (mContext.getLat() > 2442582) &&
            (mContext.getLat() < 2442782) &&
            !clue_discover_3 ){
            	
            OverlayItem itemClue3 = new OverlayItem(new GeoPoint(48625300, 2442682), "First clue", "View first clue");
            mImages.addOverlay(itemClue3);
        	Toast.makeText(mContext, "You are now near a new clue. Click on the map to make it appear.",
        				Toast.LENGTH_SHORT).show(); 
    		clue_discover_3 = true;
			mContext.incrementScore();

         } else {
        	    
        	iconPath = mContext.getResources().getDrawable(R.drawable.sphere);
      		iconPath.setBounds(0, 0, iconPath.getIntrinsicWidth(), iconPath.getIntrinsicHeight());  
        	OverlayItem itemPath = new OverlayItem(new GeoPoint(mContext.getLng(), mContext.getLat()), "position", "position udpated");
      		itemPath.setMarker(iconPath);
      		pathImages.addOverlay(itemPath);
          }

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(mContext, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(mContext, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
	
}
