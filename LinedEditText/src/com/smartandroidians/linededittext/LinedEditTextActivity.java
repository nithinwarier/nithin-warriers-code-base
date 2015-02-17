package com.smartandroidians.linededittext;

import android.app.Activity;
import android.os.Bundle;

public class LinedEditTextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new LinedEditText(this));
	}
	
}
