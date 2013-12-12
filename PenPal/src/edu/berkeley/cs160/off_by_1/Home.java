package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "creating1");
        setContentView(R.layout.activity_home);
        
        Log.d("debug", "creating");
        //R.id.unreadText
        int unreadMessages = 2;
        String unreadMsgString = getResources().getQuantityString(R.plurals.unread_messages, unreadMessages, unreadMessages);
        TextView unreadMessagesText = (TextView) findViewById(R.id.unreadText);
        unreadMessagesText.setText(unreadMsgString);
    }


public static String[] getUserData(String name) {
	String[] data = new String[3];

	if (name.equals("Timmy")){
		data[0] = "Timmy";
		data[1] = "English, Spanish";
		data[2] = "Spain";
	} else if (name.equals("Annie")) {
		data[0] = "Annie";
		data[1] = "English, French";
		data[2] = "France";
	} else if (name.equals("Angus")) {
		data[0] = "Angus";
		data[1] = "English, Vietnamese";
		data[2] = "CA, United States";
	} else if (name.equals("Mike")) {
			data[0] = "Mike";
			data[1] = "English";
			data[2] = "New York, United States";
	} else if (name.equals("Natalie")) {
		data[0] = "Natalie";
		data[1] = "German";
		data[2] = "Germany";
	} else {
			data[0] = "A random alien";
			data[1] = "Unknown";
			data[2] = "Unknown";
	}
	return data;
	
	
}

public Bitmap getStamp(String name) {
	Drawable stamp = null;
	if (name.equals("Timmy")) {
		stamp = getResources().getDrawable(R.drawable.stamp1);
	} else if (name.equals("Annie")) {
		stamp = getResources().getDrawable(R.drawable.stamp2);
	}
	else {
		stamp = getResources().getDrawable(R.drawable.stamp5);
	}
	return ((BitmapDrawable) stamp).getBitmap();
}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	Log.d("debug", "inflating menu");
        getMenuInflater().inflate(R.menu.home, menu);
        //MenuItem backItem = menu.findItem(R.id.actionBack);
        //MenuItem homeItem = menu.findItem(R.id.actionHome);
        // To retrieve the Action Provider
        //ShareActionProvider provider = (ShareActionProvider) MenuItemCompat.getActionProvider(backItem);
        return super.onCreateOptionsMenu(menu);
    }
    /**
    public boolean onOptionsItemSelected(MenuItem menuItem) {
    	
    }
    */
    public void goToFriendList(View v) {
    goToFriendList();	
    }
    
    public void goToFriendList() {
    	Intent i = new Intent(this, FriendList.class);    	
    	startActivity(i);
    }
    
    public void goToReceiveMessage(View v) {
    	String name = ((TextView) v).getText().toString(); 
    goToReceiveMessage(name);	
    }

    public void goToReceiveMessage(String name) {
    	Intent i = new Intent(this, ReceiveMessage.class);
    	i.putExtra("name", name);
    	startActivity(i);
    	
    }
}
