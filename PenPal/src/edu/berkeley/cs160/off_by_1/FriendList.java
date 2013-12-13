package edu.berkeley.cs160.off_by_1;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class FriendList extends ActionBarActivity {

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
		try {
			boolean goHome = i.getBooleanExtra("home", false);
			if (goHome) {
				setResult(RESULT_OK, i);
				finish();	
			}
		} catch (Exception e) {
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
		Intent i = new Intent(this, Profile.class);
		Button buttonV = (Button) v;
		String profileName = buttonV.getText().toString();
		byte[] compressStamp = getUserStamp(buttonV); 
		i.putExtra("name", profileName);
		i.putExtra("image", compressStamp);
		startActivityForResult(i, 0);

	}

	private byte[] getUserStamp(Button v) {
		Drawable stamp = v.getCompoundDrawables()[3];
		Bitmap stampBitmap = ((BitmapDrawable) stamp).getBitmap();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		stampBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] compressStamp = stream.toByteArray();
		return compressStamp;
	}

}
