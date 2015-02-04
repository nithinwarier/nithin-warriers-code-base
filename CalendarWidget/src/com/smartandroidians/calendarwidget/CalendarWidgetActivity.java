package com.smartandroidians.calendarwidget;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class CalendarWidgetActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CalendarUI(this));
    }
    
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calendar_widget, menu);
        return true;
    }
}
