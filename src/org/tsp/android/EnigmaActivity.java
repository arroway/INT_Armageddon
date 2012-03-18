package org.tsp.android;

import org.tsp.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class EnigmaActivity extends Activity {
	
	public static String FileName = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_enigma);
	}
	
	public void myClickHandler(View view){
		EditText inputText = (EditText) findViewById(R.id.editText1);
		TextView winText = (TextView) findViewById(R.id.winText);

		if (inputText.getText().toString().matches("answer")){
			winText.setText("Congratulations !");
		} else {			
			winText.setText(":(");
		}
	}

}
