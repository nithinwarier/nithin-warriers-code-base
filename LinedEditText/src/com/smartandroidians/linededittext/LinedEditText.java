package com.smartandroidians.linededittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

public class LinedEditText extends EditText {

	private final String TAG = LinedEditText.class.getSimpleName();
	private Rect mRect;
	private Paint mPaint;

	public LinedEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LinedEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LinedEditText(Context context) {
		super(context);
		init();
	}

	private void init() {
		Log.d(TAG, "init()");
		mRect = new Rect();
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(0x800000FF);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int count = getLineCount();
		Rect rect = mRect;
		Paint paint = mPaint;

		for (int index = 0; index < count; index++) {
			int baseline = getLineBounds(index, rect);
			canvas.drawLine(rect.left, baseline + 1, rect.right, baseline + 1,
					paint);
		}
	}

}
