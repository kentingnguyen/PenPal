package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ReceiveMessage extends ActionBarActivity {

	String senderName = "";
	ReceiveFragment fragment = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receive_message);
		senderName = getIntent().getStringExtra("name");
		String fromUserText = String.format(getResources().getString(R.string.from_user), senderName);
		TextView fromUser = (TextView) findViewById(R.id.fromUser); 
		fromUser.setText(fromUserText);
		
		try {
		String msgType = Home.getUserMsgType(senderName);
		FragmentManager manager = getSupportFragmentManager();
		Log.d("debug", "msgType" + msgType);
		FragmentTransaction transaction = manager.beginTransaction();
		fragment = new ReceiveFragment(msgType);
		Log.d("debug", "fragment" + fragment);
		transaction.replace(R.id.receivedContainer, fragment);
		transaction.commit();
		} catch (Exception e) {
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.header, menu);
		return true;
	}

	@Override
	public void onBackPressed(){
		//super.onBackPressed();
		Intent i = new Intent();
		goBack(i);     
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i = new Intent();
		switch(item.getItemId()) {		
		case R.id.actionHome:
			/*i.putExtra("home", true);
			setResult(RESULT_OK, i);
			finish();*/
			goBack(i);
			return true;
		case R.id.actionBack:
			goBack(i);
			return true;
		}
		return false;
	}


	private void goBack(Intent i) {
		setResult(RESULT_OK, i);
		i.putExtra("name", senderName);
		finish();
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
		i.putExtra("name", senderName);
		//Be sure to remember the recipient
		startActivityForResult(i, 0);	
	}


}
