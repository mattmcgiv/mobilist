package com.example.mobilist;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.droidforhumans.mobilist.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		Intent intent = new Intent(this, MyList.class);
		EditText editText = (EditText) findViewById(R.id.message_add);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

}
