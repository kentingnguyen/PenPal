package edu.berkeley.cs160.off_by_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Profile extends Activity  {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.header, menu);
		return true;
	}
	
    public void goToSendMessage(View v) {
       goToSendMessage(); 
    }
        
    public void goToSendMessage() {
    	Intent i = new Intent(this, SendMessage.class);
    	startActivity(i);
    }
}
