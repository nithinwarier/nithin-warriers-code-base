package com.simpleprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class Provider extends ContentProvider {

	public static final String AUTHORITY = "simple.provider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/table1");
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.provider.table1";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.provider.table1";
	public static final String DATABASE_NAME = "MyDatabase";
	public static final String TABLE_NAME = "table1";
	public static final int DATABASE_VERSION = 2;
	public static final int TABLE = 1;
	public static final int ROW_ID = 2;
	public static final UriMatcher sUriMatcher;
	public static final String FIRSTNAME = "first_name";
	public static final String LASTNAME = "last_name";
	private SQLiteDatabase sqlitedb;

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(AUTHORITY, "table1", TABLE);
		sUriMatcher.addURI(AUTHORITY, "table1/#", ROW_ID);
	}

	class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db
					.execSQL("CREATE TABLE IF NOT EXISTS table1 "
							+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, first_name VARCHAR,"
							+ "last_name VARCHAR);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS table1;");
			onCreate(db);
		}
	}
	
	//creating database
	@Override
	public boolean onCreate() {
		DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
		sqlitedb = databaseHelper.getWritableDatabase();
		return (sqlitedb == null) ? false : true;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case TABLE:
			return CONTENT_TYPE;
		case ROW_ID:
			return CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI : " + uri);
		}
	}

	//inserting data into database
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long row_id = sqlitedb.insert(TABLE_NAME, "", values);
		try {
			if (row_id > 0) {
				Uri uri1 = ContentUris.withAppendedId(CONTENT_URI, row_id);
				getContext().getContentResolver().notifyChange(uri1, null);
				return uri1;
			}
		} catch (Exception e) {
			Log.e("Fail to insert the row into database",
					"Fail to insert the row into database");
		}
		return null;
	}
	
	//accessing records from database
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
		sqlBuilder.setTables(TABLE_NAME);
		if (sUriMatcher.match(uri) == ROW_ID)
			sqlBuilder.appendWhere("id" + " = " + uri.getPathSegments().get(1));
		Cursor c = sqlBuilder.query(sqlitedb, projection, selection,
				selectionArgs, null, null, sortOrder);
		// ---register to watch a content URI for changes---
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}
}
