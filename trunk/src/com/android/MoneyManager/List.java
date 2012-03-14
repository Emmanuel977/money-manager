package com.android.MoneyManager;

import model.DBAdapter;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class List extends ListActivity {
	private DBAdapter dbAdapter;
	private Cursor mCursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		dbAdapter = new DBAdapter(this);
		dbAdapter.open();
		displayList();
	}
	
	private void displayList(){
		mCursor = dbAdapter.getAllActivity();
		startManagingCursor(mCursor);
		String[] from = new String[]{DBAdapter.KEY_ACTIVITY, DBAdapter.KEY_ACTIVITY_DATE, DBAdapter.KEY_ACTIVITY_MONEY};
		int[] to = new int[]{R.id.text1};
		SimpleCursorAdapter activities = new SimpleCursorAdapter(this, R.layout.activity_row, mCursor, from, to);
		setListAdapter(activities);
	}
}
