package com.android.MoneyManager;

import java.util.ArrayList;

import model.DBAdapter;
import model.ITableColumns;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

	private void displayList() {
		// mCursor = dbAdapter.getAllActivity();
		// startManagingCursor(mCursor);
		// String[] from = new String[]{ITableColumns.NAME};
		// int[] to = new int[]{R.id.text1};
		// SimpleCursorAdapter activities = new SimpleCursorAdapter(this,
		// R.layout.activity_row, mCursor, from, to);
		// setListAdapter(activities);

		ArrayList<String> list = new ArrayList<String>();
		String row = "";
		mCursor = dbAdapter.getAllActivity();
		while (mCursor.moveToNext()) {
			row = mCursor.getString(2) + " - ";
			row += mCursor.getString(1) + " - ";
			row += mCursor.getString(3);
			list.add(row);
			row = "";
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
	}
}
