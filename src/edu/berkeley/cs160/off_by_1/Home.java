package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;

public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.header, menu);
        return true;
    }
    
    public void goToFriendList(View v) {
    goToFriendList();	
    }
    
    public void goToFriendList() {
    	Intent i = new Intent(this, FriendList.class);
    	// Use TaskStackBuilder to build the back stack and get the PendingIntent
    	
    	/*PendingIntent pendingIntent =
    	        TaskStackBuilder.create(this)
    	                        // add all of DetailsActivity's parents to the stack,
    	                        // followed by DetailsActivity itself
    	                        //.addNextIntentWithParentStack(i)
    	                        .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    	 */
    	//NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
    	//builder.setContentIntent(pendingIntent);
    	startActivity(i);
    }
    
    public void goToReceiveMessage(View v) {
    goToReceiveMessage();	
    }

    public void goToReceiveMessage() {
    	Intent i = new Intent(this, ReceiveMessage.class);
    	//this intent will hopefully be dynamically loaded in the future
    	startActivity(i);
    	
    }
}
