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
import android.widget.TextView;

public class MeetPenPal extends ActionBarActivity {
	String api = "http://hidden-ridge-3009.herokuapp.com/penpals/api/v1.0/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meet_pen_pal);
		search();
		
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
	
	public void research(View v) {
		search();
	}
	
	public void search() {
		setContentView(R.layout.loading_screen);
		getPenPal();
	}
	
	public void setDetailsTextView(JSONObject penPalDetails) {
		TextView detailsText = (TextView) findViewById(R.id.penPalDetailsText);
		StringBuilder details = new StringBuilder();
		try {
				details.append("First Name:").append(penPalDetails.getString("first_name")).append("\n");
				details.append("Last Name: ").append(penPalDetails.getString("last_name")).append("\n");
				details.append("From: ").append(penPalDetails.getString("location")).append("\n");
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
		//Be sure to remember the recipient
		startActivityForResult(i, 0);
	}
	
	class GetPenPal extends AsyncTask<String, Void, JSONObject> {


	    protected JSONObject doInBackground(String... urls) {
	    	HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(api);
			Log.d("debug", get.toString() +" error");
			StringBuilder builder = new StringBuilder();
			java.lang.String line;
			JSONObject jsonObject = null; 
			try {
				HttpResponse response = client.execute(get);
				Log.d("debug", response.toString() + "here");
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
			} catch (Exception e) {
				Log.d("debug", "error" + e);
			}
			JSONObject jsonArray;
			
			try {
				jsonArray = new JSONObject(builder.toString());
				Log.d("debug", "here!!!!!!");
			if (jsonArray.length() != 0) {
				jsonObject = jsonArray;
			} else {		
				return null;
			}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Log.d("debug",jsonObject.getString("first_name"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("debug","error null");
			}
			return jsonObject;
	    }

	    protected void onPostExecute(JSONObject penpaldata) {
	    	setContentView(R.layout.activity_meet_pen_pal);
	    	setDetailsTextView(penpaldata);
	        // TODO: check this.exception 
	        // TODO: do something with the feed
	    }
	}

}

