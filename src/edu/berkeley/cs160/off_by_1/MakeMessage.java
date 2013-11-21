package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MakeMessage extends TabActivity {

	String debug = "debug";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_message);
		
		
	       TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		//TabHost tabHost = getTabHost();
	       Log.d(debug, "tabhost " + tabHost);
//	       tabHost.addTab(tabHost.newTabSpec("Voice")
//	         .setIndicator("Voice");
//	         .setContent(R.layout.voice_message);
	       Log.d(debug, "set voice");
	       tabHost.addTab(tabHost.newTabSpec("Draw")
	         .setIndicator("Draw")
	         .setContent(R.id.draw));
	       Log.d(debug, "set draw");
	       tabHost.addTab(tabHost.newTabSpec("Write")
	         .setIndicator("Write")
	         .setContent(R.id.write));
	       Log.d(debug, "set write");
	      
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
