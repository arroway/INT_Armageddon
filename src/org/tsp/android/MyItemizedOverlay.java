package org.tsp.android;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

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
    protected boolean onTap(int index){
            
        // To complete
    	// Show image in the other activity
    	OverlayItem item = myOverlays.get(index);
    	AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
    	dialog.setTitle(item.getTitle());
    	//dialog.setMessage(item.getSnippet());
        LayoutInflater li = mContext.getLayoutInflater();
        View view_mission = li.inflate(R.layout.view_mission, null);
        dialog.setView(view_mission);
    	dialog.setPositiveButton("Back", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
            dialog.dismiss();}
    	});
    	dialog.show();    	
        return(true);
    }

    // Returns present number of items in list
    @Override
    public int size() {
        return myOverlays.size();
    }
}
