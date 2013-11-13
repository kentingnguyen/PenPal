package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;

public class MakeMessage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_message);
		
	       TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);  
	       tabHost.addTab(tabHost.newTabSpec("Voice")
	         .setIndicator("Voice")
	         .setContent(R.id.voice));
	       tabHost.addTab(tabHost.newTabSpec("Draw")
	         .setIndicator("Draw")
	         .setContent(R.id.draw));
	       tabHost.addTab(tabHost.newTabSpec("Write")
	         .setIndicator("Write")
	         .setContent(R.id.write));
	      
	       tabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.header, menu);
		return true;
	}
	
	
	
	public void clear(View v) {
	//Clear the screen	
	}
	
	public void sendMessage(View v) {
	//in progress	
	}
	

}
