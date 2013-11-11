package com.example.mobilist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;


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
	   
	      
	   listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int clickedItem,
				long arg3) {
			ArrayAdapter<String> myAdapter = (ArrayAdapter<String>) adapter.getAdapter();
			String item = getListItem (myAdapter, clickedItem);
			//String tempItem = new String(item);
			myAdapter.insert("Item removed.", myAdapter.getPosition(item));
			
			ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(10);
			//ScheduledFuture futureState = new ScheduledFuture();
			ScheduledFuture<ListItemDeleter> myListItemDeleter = new ListItemDeleter(myAdapter,item);
			myListItemDeleter = (ScheduledFuture<ListItemDeleter>)threadPool.schedule((Runnable) myListItemDeleter, 3L, TimeUnit.SECONDS);
			
		}
	
	   });
		    
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
	
	public String getListItem (ArrayAdapter adapter, int index) {
		
		return (String) adapter.getItem(index);
	}
	
	private class ListItemDeleter implements Runnable, ScheduledFuture {
		//This class gets a local copy of the item.
		ArrayAdapter myAdapter;
		String item;
		boolean isCancelled;

		public ListItemDeleter (ArrayAdapter myAdapter, String item) {
			this.item=item;
			this.myAdapter = myAdapter;
			this.isCancelled = false;
			run();
		}
		
		//Implemented for Runnable
		public void run() {
			//TODO: Implement run method
			myAdapter.remove(this.item);
			updateList();
			//finish();
		}
		
		/**Implemented for Future:
		 * abstract boolean cancel(boolean mayInterruptIfRunning)
		 * 		-Attempts to cancel execution of this task
		 */
		public boolean cancel(boolean mayInterruptIfRunning) {
			return true;
		}
		
		/**Implemented for Future:
		 * abstract V get()
		 * 		-Waits if necessary for the computation to complete, and 
		 * 		then retrieves its result
		 */
		public Object get() {
			return true;
		}
		
		/**Implemented for Future:
		 * abstract V get(long timeout, TimeUnit unit)
		 * 		-Waits if necessary for at most the given time for the
		 * 		computation to complete, and then retrieves its result,
		 * 		if available.
		 */
		public Object get(long timeout, TimeUnit unit) {
			return true;
		}
		
		/**Implemented for Future
		 * abstract boolean isCancelled()
		 * 		-Returns true if this task was cancelled before it completed
		 * 		normally.
		 */
		public boolean isCancelled(){
			return true;
		}
	
		/**Implemented for Future:
		 * abstract boolean isDone()
		 * 		-Returns true if this task completed. 
		 */
		public boolean isDone() {	
			return true;
		}
		
		public long getDelay (TimeUnit unit) {
			return 3L;
		}

		@Override
		public int compareTo(Delayed comparator) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
