package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FriendList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_list);
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
	boolean goHome = i.getBooleanExtra("home", false);
	if (goHome) {
		setResult(RESULT_OK, i);
		finish();	
	}
	}
	
	
	
	public void goToMeetPenPal(View v) {
		goToMeetPenPal();
	}
	
	public void goToMeetPenPal() {
	Intent i = new Intent(this, MeetPenPal.class);
	startActivityForResult(i, 0);
	}
	
	public void goToSendMessage(View v) {
	goToSendMessage();
	}
	
	public void goToSendMessage() {
	Intent i = new Intent(this, SendMessage.class);
	startActivityForResult(i, 0);
	}

	public void goToProfile(View v) {
	goToProfile();
	}
		
	public void goToProfile() {
		Intent i = new Intent(this, Profile.class);
		startActivityForResult(i, 0);
		
	}
	
}
