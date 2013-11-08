package com.example.mobilist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {
	
	//public final static String EXTRA_MESSAGE = "com.droidforhumans.mobilist.MESSAGE";
	
	private ArrayList<String> myList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Create an ArrayList and Add the message to an array
	    myList.add("a");
	    myList.add("b");
	    myList.add("c");
	    	    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
        android.R.layout.simple_list_item_1, myList);
	   
	    
	    //Create a new ListView and pass the adapter into it
	    ListView listView = new ListView(this);
	    listView.setAdapter(adapter);
	    
	    
	    //Set the listView as ContentView
	    setContentView(listView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when user touches "Add" button */
	public void addItem(View view) {
		// Creates Intent to call the MyList
		/*
		Intent intent = new Intent(this, MyList.class);
		
		EditText editText = (EditText) findViewById(R.id.message_add);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		 */
	}

}
