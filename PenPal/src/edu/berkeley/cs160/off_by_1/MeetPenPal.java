package edu.berkeley.cs160.off_by_1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MeetPenPal extends ActionBarActivity {
	String api = "http://hidden-ridge-3009.herokuapp.com/penpals/api/v1.0/";
	TextView detailsText = null;
	String name = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meet_pen_pal);
		detailsText = (TextView) findViewById(R.id.penPalDetailsText);
		try {
			search();
		} catch (Exception e) {
			internetError();
		}
	}

	private void internetError() {
		// TODO Auto-generated method stub
		try {
			setContentView(R.layout.activity_meet_pen_pal);
			Button sendMessage = (Button) findViewById(R.id.sendMessage);
			sendMessage.setVisibility(View.GONE);
			detailsText = (TextView) findViewById(R.id.penPalDetailsText);
			detailsText.setText(getString(R.string.internet_error));
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
	public void onBackPressed(){
		super.onBackPressed();
		Intent i = new Intent();
		setResult(RESULT_OK, i);
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

	public void research(View v) {
		try {
			search();
		} catch (Exception e) {
			internetError();			
		}
	}

	public void search() {
		setContentView(R.layout.loading_screen);
		try {
			getPenPal();
		} catch (Exception e) {
			internetError();
		}
	}

	public void setDetailsTextView(JSONObject penPalDetails) {
		detailsText = (TextView) findViewById(R.id.penPalDetailsText);
		
		StringBuilder details = new StringBuilder();
		try {
			name = penPalDetails.getString("first_name");
			details.append("Meet ").append(name).append(" ");;
			details.append(penPalDetails.getString("last_name")).append("\n\n");
			details.append(name).append(" is from ").append(penPalDetails.getString("location")).append("\n");
			detailsText.setText(details.toString());
		} catch (Exception e) {
		}				
	}

	public void getPenPal() {
		AsyncTask<String, Void, JSONObject> getpenpal =  new GetPenPal();
		getpenpal.execute(api);
	}

	public void goToSendMessage(View v) {
		goToSendMessage();
	}

	public void goToSendMessage() {
		Intent i = new Intent(this, SendMessage.class);
		i.putExtra("name", name);
		//Be sure to remember the recipient
		startActivityForResult(i, 0);
	}

	class GetPenPal extends AsyncTask<String, Void, JSONObject> {

		protected JSONObject doInBackground(String... urls) {
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(api);
				
				StringBuilder builder = new StringBuilder();
				java.lang.String line;
				JSONObject jsonObject = null; 

				HttpResponse response = client.execute(get);
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					Log.d("debug", "here");
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
					Log.d("debug", builder.toString());
				} else {
					return null;
				}

				JSONObject jsonArray = null;

				jsonArray = new JSONObject(builder.toString());
				Log.d("debug", "here!!!!!!");
				if (jsonArray.length() != 0) {
					jsonObject = jsonArray;
				} else {		
					return null;
				}
				return jsonObject;
			} catch (Exception e) {
				Log.d("debug", "hi2");
				return null;
			}
		}

		protected void onPostExecute(JSONObject penpaldata) {
			try {
				setContentView(R.layout.activity_meet_pen_pal);
				if (penpaldata == null) {
					internetError();
				} else {
					Button sendMessage = (Button) findViewById(R.id.sendMessage);
					sendMessage.setVisibility(View.VISIBLE);
				setDetailsTextView(penpaldata);
				}
				// TODO: check this.exception 
				// TODO: do something with the feed
			} catch (Exception e) {

			}
		}

	}
}
