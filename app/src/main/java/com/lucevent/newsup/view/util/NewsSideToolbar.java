package com.lucevent.newsup.view.util;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.lucevent.newsup.R;

public class NewsSideToolbar extends CoordinatorLayout implements View.OnClickListener, Animator.AnimatorListener {

    private static final int ANIMATION_TIME = 200;

    private View trigger;
    public boolean closed;

    private FloatingActionButton[] actions;

    public NewsSideToolbar(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.v_news_side_toolbar, this, true);

        trigger = (View) findViewById(R.id.button_trigger);
        trigger.setOnClickListener(this);

        closed = true;

        actions = new FloatingActionButton[3];

        actions[0] = (FloatingActionButton) findViewById(R.id.button_bookmark);
        actions[1] = (FloatingActionButton) findViewById(R.id.button_share);
        actions[2] = (FloatingActionButton) findViewById(R.id.button_night);

        actions[0].setEnabled(false);
        actions[1].setEnabled(false);
        actions[2].setEnabled(false);
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
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();

        for (int i = 0; i < actions.length; i++) {
            actions[i].animate()
                    .translationYBy(1.5f * trigger.getHeight() * (i + 1))
                    .setDuration(ANIMATION_TIME)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
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
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();

        for (int i = 0; i < actions.length; i++) {
            actions[i].animate()
                    .translationYBy(-1.5f * trigger.getHeight() * (i + 1))
                    .setDuration(ANIMATION_TIME)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
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

}
