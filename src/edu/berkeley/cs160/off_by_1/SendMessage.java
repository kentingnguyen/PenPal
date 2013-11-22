package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
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

	public void goToTextMessage(View v) {
		goToTextMessage();
	}
	
    public void goToTextMessage() {
    	Intent i = new Intent(this, MakeMessage.class);
    	startActivity(i);
    }

    public void goToDrawMessage(View v) {
        goToDrawMessage();
    }
    
    public void goToDrawMessage() {
    	Intent i = new Intent(this, MakeMessage.class);
    	startActivity(i);
    }

    public void goToVoiceMessage(View v) {
        goToVoiceMessage();
    }
    	
    public void goToVoiceMessage() {
    	Intent i = new Intent(this, MakeMessage.class);
    	startActivity(i);
    }

	
}
