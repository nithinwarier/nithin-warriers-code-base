package com.simpleprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

/**
 * created by Androidians 09/10/2017
 */
public class SimpleProvider extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ContentValues values = new ContentValues();
		values.put(Provider.FIRSTNAME, "Nithin");
		values.put(Provider.LASTNAME, "Warrier");
		getContentResolver().insert(Provider.CONTENT_URI, values);

		ContentValues values1 = new ContentValues();
		values1.put(Provider.FIRSTNAME, "Guna");
		values1.put(Provider.LASTNAME, "Boss");
		getContentResolver().insert(Provider.CONTENT_URI, values1);

		Cursor c = managedQuery(Provider.CONTENT_URI, null, null, null, null);
		int count = 1;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Log.i("Row " + count + " "
					+ c.getString(c.getColumnIndex(Provider.FIRSTNAME)), c
					.getString(c.getColumnIndex(Provider.LASTNAME)));
			count++;
		}
	}
}