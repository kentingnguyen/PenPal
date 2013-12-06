package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SendMessage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_message);
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

	
	public void goToTextMessage(View v) {
		goToTextMessage();
	}
	
    public void goToTextMessage() {
    	Intent i = new Intent(this, MakeMessage.class);
    	i.putExtra("msgType", "text");
    	startActivityForResult(i, 0);
    }

    public void goToDrawMessage(View v) {
        goToDrawMessage();
    }
    
    public void goToDrawMessage() {
    	Intent i = new Intent(this, MakeMessage.class);
    	i.putExtra("msgType", "draw");
    	Log.d("debug", "got to intent");
    	startActivityForResult(i, 0);
    }

    public void goToVoiceMessage(View v) {
        goToVoiceMessage();
    }
    	
    public void goToVoiceMessage() {
    	Intent i = new Intent(this, MakeMessage.class);
    	i.putExtra("msgType", "voice");
    	startActivityForResult(i, 0);
    }

	
}
