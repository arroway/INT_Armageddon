package org.tsp.android.MapExample;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.sax.StartElementListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
        
    private ArrayList<OverlayItem> myOverlays;
    private Activity mContext; 

    public MyItemizedOverlay(Activity context, Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
        mContext = context;
        myOverlays = new ArrayList<OverlayItem>();
        populate();
    }
    
    public Drawable fixMarkerImage(Drawable img)
    {
    	return boundCenterBottom(img);
    }
        
    public void addOverlay(OverlayItem overlay){
        myOverlays.add(overlay);
        setLastFocusedIndex(-1);
        populate();
    }

    @Override
    protected OverlayItem createItem(int i) {
    	if ( i >= size() )
    		return null;
        return myOverlays.get(i);
    }
        
    // Removes overlay item i
    public void removeItem(int i){
    	if ( i >= size() )
    		return;
    	setLastFocusedIndex(-1);
        myOverlays.remove(i);
        populate();
    }
        
    // Handle tap events on overlay icons
    @Override
    protected boolean onTap(int i){
            
        // To complete
    	// Show image in the other activity
    	
        return(true);
    }

    // Returns present number of items in list
    @Override
    public int size() {
        return myOverlays.size();
    }
}
