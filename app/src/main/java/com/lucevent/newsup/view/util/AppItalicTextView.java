package com.lucevent.newsup.view.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class AppItalicTextView extends TextView {

	private static final String FONT_NAME = "roboto_italic.ttf";
	private static Typeface mTypeface;

	public AppItalicTextView(Context context)
	{
		super(context);
		setTypeface();
	}

	public AppItalicTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setTypeface();
	}

	public AppItalicTextView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		setTypeface();
	}

	private void setTypeface()
	{
		if (mTypeface == null) {
			mTypeface = Typeface.createFromAsset(getContext().getAssets(), FONT_NAME);
		}
		setTypeface(mTypeface);
	}

}
