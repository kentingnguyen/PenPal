package edu.berkeley.cs160.off_by_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Profile extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		String friendProfileText = String.format(getResources().getString(R.string.user_profile), "Timmy");
		TextView friendProfile = (TextView) findViewById(R.id.profileName); 
		friendProfile.setText(friendProfileText);	

	}

	 @Override
	    public void onBackPressed(){
	    super.onBackPressed();
	    Intent i = new Intent();
	    setResult(RESULT_OK, i);
	     finish();     
	    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.header, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i = new Intent();
		switch(item.getItemId()) {		
		case R.id.actionHome:
				i.putExtra("home", true);
				setResult(RESULT_OK, i);
				finish();
				return true;
		case R.id.actionBack:
				setResult(RESULT_OK, i);
				finish();
				return true;
		}
	return false;
	}

	@Override
	public void onActivityResult(int req, int result, Intent i) {
	try {
		boolean goHome = i.getBooleanExtra("home", false);
	if (goHome) {
		setResult(RESULT_OK, i);
		finish();	
	}
	} catch (Exception e) {
	}
}

	
    public void goToSendMessage(View v) {
       goToSendMessage(); 
    }
        
    public void goToSendMessage() {
    	Intent i = new Intent(this, SendMessage.class);
    	startActivityForResult(i, 0);
    }
}
