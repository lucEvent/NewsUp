package com.lucevent.newsup.view.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lucevent.newsup.R;

public class FilterCheckBox extends LinearLayout implements View.OnClickListener {

	public interface OnCheckedChangeListener {
		void onCheckedChanged(FilterCheckBox fcb, boolean isChecked);
	}

	private OnCheckedChangeListener mOnCheckedChangeListener;
	private CheckBox mCheckBox;
	private TextView mTextView;

	public FilterCheckBox(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.v_filter_checkbox, this, true);

		mCheckBox = (CheckBox) findViewById(R.id.checkBox);
		mTextView = (TextView) findViewById(R.id.textView);
		setOnClickListener(this);

		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.FilterCheckBox,
				0, 0);

		try {
			mCheckBox.setChecked(
					a.getBoolean(R.styleable.FilterCheckBox_checked, true)
			);
			mTextView.setText(
					a.getString(R.styleable.FilterCheckBox_text)
			);

			int textStyle = a.getInt(R.styleable.FilterCheckBox_textStyle, 0);
			if (textStyle == 1)
				mTextView.setTypeface(mTextView.getTypeface(), Typeface.BOLD);
			else if (textStyle == 2)
				mTextView.setTypeface(mTextView.getTypeface(), Typeface.ITALIC);

		} finally {
			a.recycle();
		}
	}

	@Override
	public void onClick(View v)
	{
		mCheckBox.toggle();
		if (mOnCheckedChangeListener != null)
			mOnCheckedChangeListener.onCheckedChanged(this, mCheckBox.isChecked());
	}

	public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener)
	{
		mOnCheckedChangeListener = onCheckedChangeListener;
	}

	public void setText(String text)
	{
		mTextView.setText(text);
	}

	public boolean isChecked()
	{
		return mCheckBox.isChecked();
	}

	public void setChecked(boolean checked)
	{
		mCheckBox.setChecked(checked);
	}

}