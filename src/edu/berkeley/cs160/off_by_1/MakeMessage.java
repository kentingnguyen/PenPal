package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class MakeMessage extends FragmentActivity {

	String debug = "debug";
	String msgType = null;
	MessageFragment fragment = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Intent i = getIntent();
			msgType = i.getStringExtra("msgType");

		setContentView(R.layout.activity_make_message);	
		
		String toUserText = String.format(getResources().getString(R.string.to_user), "Timmy");
		TextView toUser = (TextView) findViewById(R.id.toUser); 
		toUser.setText(toUserText);	
		String[] suggestedQuestionsArray = getResources().getStringArray(R.array.suggested_questions);
		int choice = (int) (Math.random() * suggestedQuestionsArray.length) ;
		String suggestedQuestionText = suggestedQuestionsArray[choice];
		TextView suggestedQuestion = (TextView) findViewById(R.id.suggestedQuestion);
		suggestedQuestion.setText(suggestedQuestionText);
		
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		fragment = new MessageFragment(msgType);
		
		transaction.replace(R.id.fragmentContainer, fragment);
		transaction.commit();
		Log.d(debug, "added in fragment container");
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

	public void clear(View v) {
	//Clear the screen	
	}
	
	public void sendMessage(View v) {
	//in progress
		Log.d(debug, "starting send message" + fragment);
		Intent i = fragment.getIntent();
		Log.d(debug, "got intent" + i);
		goToMessageSent(i);
	}
	

	void goToMessageSent(Intent i) {
		i.setClass(this, MessageSent.class);
		startActivityForResult(i, 0);
	}
}
