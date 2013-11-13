package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MakeMessage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.make_message, menu);
		return true;
	}
	
	
	
	void clear(View v) {
	//Clear the screen	
	}
	
	void sendMessage(View v) {
	//in progress	
	}
	

}
