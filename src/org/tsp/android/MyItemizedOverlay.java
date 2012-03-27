package org.tsp.android;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
        
    private ArrayList<OverlayItem> myOverlays;
    private MapArmageddonActivity mContext; 
    
    private static final int START_MISSION_DIALOG = 0;
    private static final int VIEW_CLUE_1 = 1;
    private static final int VIEW_CLUE_2 = 2;
    private static final int VIEW_CLUE_3 = 3;
    private static final int FINAL = 4;



    public MyItemizedOverlay(MapArmageddonActivity context, Drawable defaultMarker) {
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
    	AlertDialog alert;    
    	
    	/*
    	 * Clues are not linked with a special place. The are displayed in the same order
    	 * whatever the path of the player is.  
    	 *  
    	 */
    	switch(index){
    	
    		case START_MISSION_DIALOG:
           			
    			builder.setPositiveButton("Let's go, O'Neil!", new DialogInterface.OnClickListener(){
    	            public void onClick(DialogInterface alert, int id) {
    	            alert.dismiss();
    	    		//hint
    	    		Toast.makeText(mContext, "Hint! Click on the map to show actualized path",
    	    				Toast.LENGTH_LONG).show();
    	            }
    	    	});
    	    	
    	    	builder.setNegativeButton("I'm afraid!", new DialogInterface.OnClickListener(){
    	            public void onClick(DialogInterface alert, int id) {
    	            alert.dismiss();
    	            Toast.makeText(mContext, "Too bad, you die under acid rains and nuclear radiations. Bye Bye!", Toast.LENGTH_LONG).show();
    	            mContext.finish();
    	            }
    	    	});
    	    	
    	    	alert = builder.create();

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
    			
    			builder.setPositiveButton("I found it, I'm even better than Carter!", new DialogInterface.OnClickListener(){
    	            public void onClick(DialogInterface alert, int id) {
    	            alert.dismiss();}
    	    	});
    			
    	    	alert = builder.create();

    			String clueStr_1 = "Do you know about Tana?";
    			alert.setTitle("First clue");
    			alert.setMessage(clueStr_1);
    			alert.show();
    			return (true);
    			
    		case VIEW_CLUE_2:
    			
    			builder.setPositiveButton("I found it, I'm even better than Carter!", new DialogInterface.OnClickListener(){
    	            public void onClick(DialogInterface alert, int id) {
    	            alert.dismiss();}
    	    	});
    			
    	    	alert = builder.create();

    			String clueStr_2 = "Seek refuge on a forest moon.";
    			alert.setTitle("Second clue");
    			alert.setMessage(clueStr_2);
    			alert.show();
    			return (true);	

    		case VIEW_CLUE_3:
    			
    			builder.setPositiveButton("I found it, I'm even better than Carter!", new DialogInterface.OnClickListener(){
    	            public void onClick(DialogInterface alert, int id) {
    	            alert.dismiss();}
    	    	});
    			
    	    	alert = builder.create();

    			//String clueStr_3 = "This is the third clue";
    			alert.setTitle("Third clue");
    			ImageView image2 = new ImageView(mContext);
    			image2.setImageResource(R.drawable.ewok);
    			alert.setView(image2);
    			//alert.setMessage(clueStr_3);
    			alert.show();
    			return (true);
    			
    		case FINAL:
    			
    			if ((mContext.getLng() > 48626950) &&
    	                (mContext.getLng() < 48627050) &&
    	                (mContext.getLat() > 2440100) &&
    	                (mContext.getLat() < 2441182) &&
    	                mContext.getFinalEnigma()){
    				
    				builder.setPositiveButton("Solve the enigma now!", new DialogInterface.OnClickListener(){
    	        		public void onClick(DialogInterface alert, int id) {
    	                    Intent intentEnigma = new Intent(mContext, EnigmaActivity.class);
    	       		        mContext.startActivityForResult(intentEnigma, 1);
    	        		}
    	        	});
    				
    				builder.setNegativeButton("Back to the map", new DialogInterface.OnClickListener(){
    	        		public void onClick(DialogInterface alert, int id) {
    	        			alert.dismiss();
    	        		}
    	        	});
    				alert = builder.create();
    				alert.show();	
    				
    	        } else {
    	        
    	        	builder.setPositiveButton("Let's go!", new DialogInterface.OnClickListener(){
    	        		public void onClick(DialogInterface alert, int id) {
    	        			alert.dismiss();}
    	        	});
    			
    	        	alert = builder.create();
    	        	String finalStr = "Congratulations, you found the 5 clues. Now you have to go at the location " +
    	        			"indicated by this item to be able to solve the enigma.";
    	        	alert.setTitle("To solve the enigma...");
    			
    	        	alert.setMessage(finalStr); 
    	        	alert.show();
    			}
    			return (true); 
    			
    	}
    	return (true);
    }

    // Returns present number of items in list
    @Override
    public int size() {
        return myOverlays.size();
    }
}
