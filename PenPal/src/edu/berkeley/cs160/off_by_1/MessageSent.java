package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MessageSent extends ActionBarActivity {
	String receiverName = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_sent);

		receiverName = getIntent().getStringExtra("name");
		TextView toUser = (TextView) findViewById(R.id.toUser); 
		String toUserText = String.format(getResources().getString(R.string.to_user), receiverName);
		toUser.setText(toUserText);

		String sentUserText = String.format(getResources().getString(R.string.message_sent_text), receiverName);
		TextView sentUser = (TextView) findViewById(R.id.messageSent); 
		sentUser.setText(sentUserText);


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

	public void sendAnother(View v) {
		Intent i = new Intent();
		i.putExtra("sendAnother", true);
		setResult(RESULT_OK, i);
		Log.d("debug", "Sending another");
		finish();
	}

}
