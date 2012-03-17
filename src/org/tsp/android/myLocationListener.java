package org.tsp.android;

import com.google.android.maps.MapActivity;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class myLocationListener implements LocationListener {

	private MapArmageddonActivity mContext;
	
	public myLocationListener(MapArmageddonActivity activity){
		mContext = activity;
	}

	@Override
	public void onLocationChanged(Location location) {
		mContext.setLat((int) (location.getLatitude()));
		mContext.setLng((int) (location.getLongitude()));
		Toast.makeText(mContext, "Longitude: " + mContext.getLng() + " - Latitude: " + mContext.getLat(),
				Toast.LENGTH_SHORT).show();

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
