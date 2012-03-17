package org.tsp.android;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
        
    private ArrayList<OverlayItem> myOverlays;
    private Activity mContext; 
    
    private static final int START_MISSION_DIALOG = 0;
    private static final int VIEW_CLUE_1 = 1;

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
    	
    	AlertDialog.Builder builder;
    	builder = new AlertDialog.Builder(mContext);
    	builder.setPositiveButton("Let's go, O'Neil!", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface alert, int id) {
            alert.dismiss();}
    	});
    	
    	builder.setNegativeButton("I'm afraid!", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface alert, int id) {
            alert.dismiss();
            Toast.makeText(mContext, "Too bad, you die under acid rains and nuclear radiations. Bye Bye!", Toast.LENGTH_LONG).show();
            mContext.finish();
            }
    	});
    	AlertDialog alert = builder.create();
            
    	switch(index){
    	
    		case START_MISSION_DIALOG:
       
    			String missionStr = "2013: the world was overhelmed by the Armageddon in late 2012. " +
    					"Sole survivors in a post-apocalyptic land, your mission is simple: staying alive." + 
    					"But one hope remains: finding the Stargate to escape this world." +
    					"Rumors say that the Stargate may lay on the campus of Telecom SudParis." +
    					"You must find it so you can leave this no-man land...  ";


    			alert.setTitle("Your mission,if you accept it...");
    			alert.setMessage(missionStr);
    			ImageView image = new ImageView(mContext);
    			image.setImageResource(R.drawable.mission);
    			alert.setView(image);
    			alert.show();
         		return(true);
    					
    		case VIEW_CLUE_1:
    			String clueStr_1 = "This is the first clue";
    			alert.setTitle("First clue");
    			alert.setMessage(clueStr_1);
    			alert.show();
    			return (true);
    			
    	}
    	
    	return(true);
    }

    // Returns present number of items in list
    @Override
    public int size() {
        return myOverlays.size();
    }
}
