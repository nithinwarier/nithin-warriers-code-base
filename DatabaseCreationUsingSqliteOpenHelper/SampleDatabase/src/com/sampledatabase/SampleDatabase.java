package com.sampledatabase;

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class SampleDatabase extends ListActivity {

	DBAdapter db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			db = new DBAdapter(this);

			insertData();
//			db.updateTitle(1, "", "", "");
			displayAll();
			// retrieveSingleData(4);

		} catch (Exception e) {
			System.out.println("Exception in SampleDatabase onCreate() : e = "
					+ e);
		}
	}

	// displaying all data in database in list
	private void displayAll() {
		db.open();
		Cursor cursor = db.getAllTitles();
		ArrayList<String> results = new ArrayList<String>();
		try {
			if (cursor != null) {
				for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
						.moveToNext()) {

					results.add("id: " + cursor.getString(0) + "\n" + "ISBN: "
							+ cursor.getString(1) + "\n" + "TITLE: "
							+ cursor.getString(2) + "\n" + "PUBLISHER:  "
							+ cursor.getString(3));
					System.out.println("id: " + cursor.getString(0) + "\n"
							+ "ISBN: " + cursor.getString(1) + "\n" + "TITLE: "
							+ cursor.getString(2) + "\n" + "PUBLISHER:  "
							+ cursor.getString(3));
				}
			} else {
				Toast.makeText(this, "Cursor is null in displayAll()",
						Toast.LENGTH_LONG).show();
			}
			this.setListAdapter(new ArrayAdapter<String>(SampleDatabase.this,
					android.R.layout.simple_list_item_1, results));
			db.close();
		} catch (Exception e) {
			System.out.println("Exception in displayAll() : e = " + e);
			db.close();
		}
	}

	// inserting data to database
	private void insertData() {
		long id = -1;
		try {
			db.open();
			id = db.insertTitle("0470285818", "C# 2008 Programmer's Reference",
					"Wrox");
			id = db.insertTitle("047017661X",
					"Professional Windows Vista Gadgets Programming", "Wrox");
			db.close();
		} catch (Exception e) {
			System.out.println("Exception in insertData() : e = " + e);
		}
	}

	// retrieving single row from database using ID
	private void retrieveSingleData(int id) {
		// ---get a title---
		Cursor cursor = null;
		try {
			db.open();
			cursor = db.getTitle(id);
			if (cursor.moveToFirst())
				displayData(cursor);
			else
				Toast.makeText(this, "No Data found", Toast.LENGTH_LONG).show();
			db.close();
		} catch (Exception e) {
			System.out.println("Exception in retrieveSingleData() : e = " + e);
		}
	}

	// displaying single row from database
	private void displayData(Cursor cursor) {
		try {
			Toast.makeText(
					this,
					"id: " + cursor.getString(0) + "\n" + "ISBN: "
							+ cursor.getString(1) + "\n" + "TITLE: "
							+ cursor.getString(2) + "\n" + "PUBLISHER:  "
							+ cursor.getString(3), Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			System.out.println("Exception in displayData() : e = " + e);
		}
	}

}