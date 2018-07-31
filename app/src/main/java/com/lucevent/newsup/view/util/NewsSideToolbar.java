package com.lucevent.newsup.view.util;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.lucevent.newsup.R;

public class NewsSideToolbar extends CoordinatorLayout implements View.OnClickListener, Animator.AnimatorListener {

	private static final int ANIMATION_TIME = 200;

	private View trigger;
	private boolean closed;

	private FloatingActionButton[] actions;

	public NewsSideToolbar(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.v_news_side_toolbar, this, true);

		trigger = findViewById(R.id.button_trigger);
		trigger.setOnClickListener(this);

		closed = true;

		actions = new FloatingActionButton[5];

		actions[0] = (FloatingActionButton) findViewById(R.id.button_bookmark);
		actions[1] = (FloatingActionButton) findViewById(R.id.button_share);
		actions[2] = (FloatingActionButton) findViewById(R.id.button_night);
		actions[3] = (FloatingActionButton) findViewById(R.id.button_font_size);
		actions[4] = (FloatingActionButton) findViewById(R.id.button_open_in_browser);

		actions[0].setEnabled(false);
		actions[1].setEnabled(false);
		actions[2].setEnabled(false);
		actions[3].setEnabled(false);
		actions[4].setEnabled(false);
	}

	@Override
	public void onClick(View v)
	{
		if (closed)
			open();
		else
			close();
	}

	public void close()
	{
		trigger.animate()
				.rotationBy(-45)
				.setListener(this)
				.setDuration(ANIMATION_TIME)
				.setInterpolator(new LinearOutSlowInInterpolator())
				.start();

		for (int i = 0; i < actions.length; i++) {
			actions[i].animate()
					.translationYBy(0.75f * trigger.getHeight() * (i + 1))
					.setDuration(ANIMATION_TIME)
					.setInterpolator(new LinearOutSlowInInterpolator())
					.start();
			actions[i].setEnabled(false);
		}
		closed = true;
	}

	public void open()
	{
		trigger.animate()
				.rotationBy(45)
				.setListener(this)
				.setDuration(ANIMATION_TIME)
				.setInterpolator(new LinearOutSlowInInterpolator())
				.start();

		for (int i = 0; i < actions.length; i++) {
			actions[i].animate()
					.translationYBy(-0.75f * trigger.getHeight() * (i + 1))
					.setDuration(ANIMATION_TIME)
					.setInterpolator(new LinearOutSlowInInterpolator())
					.start();
			actions[i].setEnabled(true);
		}
		closed = false;
	}

	@Override
	public void onAnimationStart(Animator animation)
	{
		trigger.setClickable(false);
		for (FloatingActionButton fab : actions)
			fab.setClickable(false);
	}

	@Override
	public void onAnimationEnd(Animator animation)
	{
		trigger.setClickable(true);
		for (FloatingActionButton fab : actions)
			fab.setClickable(true);
	}

	@Override
	public void onAnimationCancel(Animator animation)
	{
	}

	@Override
	public void onAnimationRepeat(Animator animation)
	{
	}

	public boolean isOpened()
	{
		return !closed;
	}

	public boolean isClosed()
	{
		return closed;
	}

}
