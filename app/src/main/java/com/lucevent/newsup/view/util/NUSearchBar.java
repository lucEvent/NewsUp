package com.lucevent.newsup.view.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lucevent.newsup.R;

public class NUSearchBar extends LinearLayout implements TextWatcher {

	public interface CallBack {
		void onFilter(String filter);

		void onEnd();
	}

	private EditText mInput;

	private CallBack mCallback;

	public NUSearchBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.v_search_bar, this, true);


		findViewById(R.id.btn_clear).setOnClickListener(onClearAction);
		findViewById(R.id.btn_back).setOnClickListener(onBackAction);

		mInput = (EditText) findViewById(R.id.input);
		mInput.addTextChangedListener(this);
	}

	public final void start(CallBack callback)
	{
		mCallback = callback;

		setVisibility(VISIBLE);

		mInput.requestFocus();
		((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
				.showSoftInput(mInput, InputMethodManager.SHOW_IMPLICIT);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{
	}

	@Override
	public void onTextChanged(CharSequence cs, int start, int before, int count)
	{
		mCallback.onFilter(cs.toString().toLowerCase());
	}

	@Override
	public void afterTextChanged(Editable s)
	{
	}

	private OnClickListener onClearAction = new OnClickListener() {
		@Override
		public void onClick(View v)
		{
			mInput.setText("");
		}
	};

	private OnClickListener onBackAction = new OnClickListener() {
		@Override
		public void onClick(View v)
		{
			hideKeyBoard();
			hide();
		}
	};

	public boolean isShown()
	{
		return getVisibility() == VISIBLE;
	}

	public void hide()
	{
		mCallback.onEnd();
		setVisibility(GONE);
	}

	public void hideKeyBoard()
	{
		((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(mInput.getWindowToken(), 0);
	}

}
