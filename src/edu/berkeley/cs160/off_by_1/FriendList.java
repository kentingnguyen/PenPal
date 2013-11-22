package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
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

	
	public void goToMeetPenPal(View v) {
	Intent i = new Intent(this, MeetPenPal.class);
	startActivity(i);
	}
	

	public void goToSendMessage(View v) {
	Intent i = new Intent(this, SendMessage.class);
	startActivity(i);
	}

	public void goToProfile(View v) {
		Intent i = new Intent(this, Profile.class);
		startActivity(i);
		
	}
	
}
