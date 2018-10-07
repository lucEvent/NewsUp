package com.lucevent.newsup.view.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lucevent.newsup.R;

public class IconTextView extends LinearLayout {

	public IconTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.v_icon_text_view, this, true);

		ImageView mIcon = (ImageView) findViewById(R.id.icon);
		TextView mTextView = (TextView) findViewById(R.id.text);

		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.IconTextView,
				0, 0);

		try {
			mIcon.setImageResource(
					a.getResourceId(R.styleable.IconTextView_iconSrc, R.mipmap.ic_launcher)
			);
			//// TODO: 14/08/2018 TINT
			mTextView.setText(
					a.getString(R.styleable.IconTextView_text)
			);

			int custom_text_size = a.getDimensionPixelSize(R.styleable.IconTextView_textSize, -1);
			if (custom_text_size > 0)
				mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, custom_text_size);

			int textStyle = a.getInt(R.styleable.IconTextView_textStyle, 0);
			if (textStyle == 1)
				mTextView.setTypeface(mTextView.getTypeface(), Typeface.BOLD);
			else if (textStyle == 2)
				mTextView.setTypeface(mTextView.getTypeface(), Typeface.ITALIC);

		} finally {
			a.recycle();
		}
	}

	public void setIcon(@DrawableRes int resource)
	{
		((ImageView) findViewById(R.id.icon)).setImageResource(resource);
	}

	public void setIcon(Drawable drawable)
	{
		((ImageView) findViewById(R.id.icon)).setImageDrawable(drawable);
	}

	public void setText(String text)
	{
		((TextView) findViewById(R.id.text)).setText(text);
	}

}

