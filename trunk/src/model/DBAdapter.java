package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBAdapter {
	// Logcat
	public static String TAG = "HUNGDD";

	// Fields name
	public static final String KEY_ID = "_id";
	public static final String KEY_ACTIVITY_NAME = "activityName";
	public static final String KEY_ACTIVITY_DATE = "activityDate";
	public static final String KEY_ACTIVITY_MONEY = "activityMoney";

	// Database information
	private static final String DATABASE_NAME = "MoneyManagerDB";
	private static final String DATABASE_TABLE = "tblActivity";
	private static final int DATABASE_VERSION = 2;

	// create database statement
	private static final String DATABASE_CREATE = "create table tblActivity (_id integer primary key autoincrement, "
			+ "activityName text not null, activityDate text not null, activityMoney int);";

	private final Context mContext;
	private SQLiteDatabase mDB;
	private DatabaseHelper mDBHelper;

	/**
	 * Constructor.
	 * 
	 * @param ctx
	 */
	public DBAdapter(Context ctx) {
		this.mContext = ctx;
	}

	/***
	 * Open database
	 * 
	 * @return DBAdapter object
	 */
	public DBAdapter open() {
		mDBHelper = new DatabaseHelper(mContext, DATABASE_NAME, null,
				DATABASE_VERSION);
		mDB = mDBHelper.getWritableDatabase();
		return this;
	}

	/***
	 * Insert row
	 * 
	 * @param activityName
	 * @param activityDate
	 * @param money
	 * @return
	 */
	public long insertActivity(String activityName, String activityDate,
			int money) {
		ContentValues initialValue = new ContentValues();

		initialValue.put(KEY_ACTIVITY_NAME, activityName);
		initialValue.put(KEY_ACTIVITY_DATE, activityDate);
		initialValue.put(KEY_ACTIVITY_MONEY, money);

		long rowID = mDB.insert(DATABASE_TABLE, null, initialValue);
		// return rowID
		return rowID;
	}

	/**
	 * Get all activity from database.
	 * 
	 * @return Cursor object.
	 */
	public Cursor getAllActivity() {
		return mDB.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_ACTIVITY_NAME,
				KEY_ACTIVITY_DATE, KEY_ACTIVITY_MONEY }, null, null, null,
				null, null);
	}

	/**
	 * Close database.
	 */
	public void close() {
		mDBHelper.close();
	}

	/**
	 * Inner class DatabaseHelper.
	 * 
	 * @author HungDD
	 * 
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {

		/**
		 * Constructor.
		 * @param context
		 * @param name
		 * @param factory
		 * @param version
		 */
		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
			Log.i(TAG, "DB created");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			Log.i(TAG, "Upgrading DB");
			db.execSQL("DROP TABLE IF EXISTS tblActivity");
			onCreate(db);
		}
	}
}
