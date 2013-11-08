package com.example.mobilist;

import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;

@SuppressLint("NewApi")
public class MyList extends Activity {

//	private ArrayList<String> myList = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    /*
	    //Receive the intent
	    Intent intent = getIntent();
	    //Save the message into a String called "message"
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    //Create an ArrayList and Add the message to an array
	    
	    myList.add((String) message);
	    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
        android.R.layout.simple_list_item_1, myList);
	   
	    //Create a new ListView and pass the adapter into it
	    ListView listView = new ListView(this);
	    listView.setAdapter(adapter);
	    //Set the listView as ContentView
	    setContentView(listView);*/
	    
	    
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}*/

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
