package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;


public class MakeMessage extends ActionBarActivity {

	String debug = "debug";
	String msgType = null;
	MessageFragment fragment = null;
	String receiverName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Intent i = getIntent();
			msgType = i.getStringExtra("msgType");
			setContentView(R.layout.activity_make_message);	
			if (msgType.equals("draw")) {
				Button questionButton = (Button) findViewById(R.id.questionButton);
				questionButton.setVisibility(View.INVISIBLE);
			}
			receiverName = getIntent().getStringExtra("name");
			TextView toUser = (TextView) findViewById(R.id.toUser); 
			String toUserText = String.format(getResources().getString(R.string.to_user), receiverName);
			toUser.setText(toUserText);

			FragmentManager manager = getSupportFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			fragment = new MessageFragment(msgType);

			transaction.replace(R.id.fragmentContainer, fragment);
			transaction.commit();
			Log.d(debug, "added in fragment container");
		} catch (Exception e) {

		}

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
			boolean sendAnother = i.getBooleanExtra("sendAnother", false);
			Log.d("debug", "on activity result" + goHome + " " + sendAnother);
			if (goHome) {
				setResult(RESULT_OK, i);
				finish();	
			} else if (sendAnother) {
				setResult(RESULT_OK, i);
				finish();
			}
		} catch (Exception e) {

		}
	}

	public void getSuggestedQuestion(View v) {
		getSuggestedQuestion();
	}

	public void getSuggestedQuestion() {
		String[] suggestedQuestionsArray = getResources().getStringArray(R.array.suggested_questions);
		int choice = (int) (Math.random() * suggestedQuestionsArray.length) ;
		String suggestedQuestionText = suggestedQuestionsArray[choice];
		TextView suggestedQuestion = (TextView) findViewById(R.id.suggestedQuestion);
		suggestedQuestion.setText(suggestedQuestionText);
	}

	public void clear(View v) {
		//Clear the screen	
		fragment.clear();
	}

	public void sendMessage(View v) {
		//in progress
		Log.d(debug, "starting send message" + fragment);
		Intent i = fragment.getIntent();
		i.putExtra("name", receiverName);
		Log.d(debug, "got intent" + i);
		goToMessageSent(i);
	}


	void goToMessageSent(Intent i) {
		i.setClass(this, MessageSent.class);
		startActivityForResult(i, 0);
	}
}
