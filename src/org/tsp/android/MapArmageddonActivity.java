package org.tsp.android;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.os.Bundle;

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
        
        OverlayItem itemMission = new OverlayItem(new GeoPoint(48625119, 2442082), "Start", "");
        images.addOverlay(itemMission);
        
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
}