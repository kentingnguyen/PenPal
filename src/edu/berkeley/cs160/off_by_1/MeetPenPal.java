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

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MeetPenPal extends Activity {
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
		JSONObject penPalDetails = getPenPal();
		setDetailsTextView(penPalDetails);
	}
	
	public void setDetailsTextView(JSONObject penPalDetails) {
		TextView detailsText = (TextView) findViewById(R.id.penPalDetailsText);
		StringBuilder details = new StringBuilder();
		try {
			details.append(penPalDetails.getString("first_name")).append("\n");
			details.append(penPalDetails.getString("last_name")).append("\n");
			details.append(penPalDetails.getString("location")).append("\n");
			detailsText.setText(details.toString());
			} catch (Exception e) {
			}				
	}

	public JSONObject getPenPal() {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(api);
		StringBuilder builder = new StringBuilder();
		String line;
		JSONObject jsonObject = null; 
		try {
			HttpResponse response = client.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			Log.d("debug", "error" + e);
		}
		JSONArray jsonArray;
		
		try {
			jsonArray = new JSONArray(builder.toString());

		if (jsonArray.length() != 0) {
			jsonObject = jsonArray.getJSONObject(0);
		} else {		
			return null;
		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	public void goToSendMessage(View v) {
		goToSendMessage();
	}

	public void goToSendMessage() {
		Intent i = new Intent(this, SendMessage.class);
		//Be sure to remember the recipient
		startActivityForResult(i, 0);
	}

}
