package com.example.mobilist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {	
	private ArrayList<String> myList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		//Create an ArrayList and Add the message to an array
	    if (myList.isEmpty()) {
	    	myList.add(getString(R.string.empty_list));
	    }
	    	    
	    updateList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	/** Called when user touches "Add" button */
	public void addItem() {
		if (myList.contains(getString(R.string.empty_list)))
			{myList.clear();}
		Intent intent = new Intent(this, ListAddActivity.class);
		startActivityForResult(intent,1);
		updateList();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_add:
	            addItem();
	            return true;
	        case R.id.action_settings:
	            //TODO: openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void updateList() {
		//Create a new ArrayAdapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, myList);
			   
	   //Create a new ListView and pass the adapter into it
	   ListView listView = new ListView(this);
	   listView.setAdapter(adapter);
		    
	   //Set the listView as ContentView
	   setContentView(listView);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		  if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		         String result=data.getStringExtra("result");
		         myList.add(result);
		         updateList();
		     }
		     if (resultCode == RESULT_CANCELED) {    
		         myList.clear();
		         myList.add("Error retrieving list from sub-activity.");
		     }
		  }
		}//onActivityResult
	

}
