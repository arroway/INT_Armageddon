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
    /*	OverlayItem item = myOverlays.get(index);
    	AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
    	dialog.setTitle(item.getTitle());
        LayoutInflater li = mContext.getLayoutInflater();
        View view_mission = li.inflate(R.layout.view_mission, null);
        dialog.setView(view_mission);
    	
    	dialog.setPositiveButton("Back", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
            dialog.dismiss();}
    	});
    	
    	dialog.show();*/
    	

    	
    	/*AlertDialog.Builder builder;
    	AlertDialog alertDialog;

    	LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    	View layout = inflater.inflate(R.layout.view_mission,
    	                               null);

    	TextView text = (TextView) layout.findViewById(R.id.text_view);*/
    	String missionStr = "2013: the world was overhelmed by the Armageddon in late 2012. " +
    	"Sole survivors in a post-apocalyptic land, your mission is simple: staying alive." + 
        "But one hope remains: finding the Stargate to escape this world." +
        "Rumors say that the Stargate may lay on the campus of Telecom SudParis." +
        "You must find it so you can leave this no-man land...  ";
    	/*text.setText(mission);
    	ImageView image = (ImageView) layout.findViewById(R.id.img_view);
    	image.setImageResource(R.drawable.mission);

    	builder = new AlertDialog.Builder(mContext);
    	builder.setView(layout);
    	alertDialog = builder.create();
        alertDialog.show();*/
    	
    	AlertDialog.Builder builder;
    	builder = new AlertDialog.Builder(mContext);
    	builder.setPositiveButton("Back", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface alert, int id) {
            alert.dismiss();}
    	});
    	AlertDialog alert = builder.create();
    	alert.setTitle("Your mission,if you accept it...");
    	alert.setMessage(missionStr);
    	ImageView image = new ImageView(mContext);
    	image.setImageResource(R.drawable.mission);
    	alert.setView(image);
    	alert.show();

        return(true);
    }

    // Returns present number of items in list
    @Override
    public int size() {
        return myOverlays.size();
    }
}
