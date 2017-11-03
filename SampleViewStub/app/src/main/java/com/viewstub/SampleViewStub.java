package com.viewstub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.View.OnClickListener;

public class SampleViewStub extends Activity {
	
	private ViewStub stub;
	private boolean clicked;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViewById(R.id.openstub).setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				if (!clicked) {
					stub = findViewById(R.id.stub1);
					stub.inflate();

					clicked = true;
				}
			}
		});
	}
}