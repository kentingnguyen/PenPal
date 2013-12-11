package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	Log.d("debug", "inflating menu");
        getMenuInflater().inflate(R.menu.header, menu);
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
    	/*TaskStackBuilder.create(this)
    								.addNextIntentWithParentStack(i)
    								.startActivities();*/
    	// Use TaskStackBuilder to build the back stack and get the PendingIntent
    	
    	 
    	//NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
    	//builder.setContentIntent(pendingIntent);
    	//startActivities(builder);
    	startActivity(i);
    }
    
    public void goToReceiveMessage(View v) {
    goToReceiveMessage();	
    }

    public void goToReceiveMessage() {
    	Intent i = new Intent(this, ReceiveMessage.class);    	
    	/*TaskStackBuilder.create(this)
    								.addNextIntentWithParentStack(i)
    								.startActivities();*/
    	
    	//this intent will hopefully be dynamically loaded in the future
    	startActivity(i);
    	
    }
}
