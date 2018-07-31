package com.lucevent.newsup.view.util;

import android.animation.Animator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

public class AppAnimator {

	private static final long animationTime = 500;

	static void expandMoving(View view, View from, int finalHeight, Animation.AnimationListener animationListener)
	{
/*	    TranslateAnimation movement = new TranslateAnimation(0, 0, from.getY(), 0);
		AppAnimator.ResizeAnimation resizing = new AppAnimator.ResizeAnimation(view, finalHeight, from.getHeight());
		resizing.setAnimationListener(animationListener);

		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(movement);
		animationSet.addAnimation(resizing);
		animationSet.setDuration(animationTime);
		animationSet.setFillAfter(false);
		animationSet.setFillBefore(true);

		view.startAnimation(animationSet);
*/
	}

	static void swipeUp(View view, float screen_height, Animation.AnimationListener animationListener)
	{
		TranslateAnimation anim = new TranslateAnimation(0, 0, screen_height, 0);
		anim.setAnimationListener(animationListener);
		anim.setDuration(animationTime);
		anim.setFillAfter(false);
		anim.setFillBefore(true);

		view.startAnimation(anim);
	}

	public static void collapseAnimation(View view, int finalHeight, long duration, Animation.AnimationListener animationListener)
	{
		Animation anim = new AppAnimator.ResizeAnimation(view, finalHeight);
		anim.setAnimationListener(animationListener);
		anim.setDuration(duration);
		anim.setFillBefore(true);

		view.startAnimation(anim);
	}

	static void swipeDown(View view, float startHeight, float toHeight, Animation.AnimationListener animationListener)
	{
		TranslateAnimation anim = new TranslateAnimation(0, 0, startHeight, toHeight);
		anim.setAnimationListener(animationListener);
		anim.setDuration(animationTime);
		anim.setFillBefore(true);
		anim.setFillAfter(false);

		view.startAnimation(anim);
	}

	public static void crossfade(View reveal, final View hide, long duration, Animator.AnimatorListener listener)
	{
		// Set the content view to 0% opacity but visible, so that it is visible
		// (but fully transparent) during the animation.
		reveal.setAlpha(0f);
		reveal.setVisibility(View.VISIBLE);

		// Animate the content view to 100% opacity, and clear any animation
		// listener set on the view.
		reveal.animate()
				.alpha(1f)
				.setDuration(duration)
				.setListener(null);

		// Animate the loading view to 0% opacity. After the animation ends,
		// set its visibility to GONE as an optimization step (it won't
		// participate in layout passes, etc.)
		hide.animate()
				.alpha(0f)
				.setDuration(duration)
				.setListener(listener);
	}

	public static class AppAnimatorListener implements Animation.AnimationListener {

		@Override
		public void onAnimationStart(Animation animation)
		{
		}

		@Override
		public void onAnimationEnd(Animation animation)
		{
		}

		@Override
		public void onAnimationRepeat(Animation animation)
		{
		}
	}

	public static class ResizeAnimation extends Animation {

		private final int mCollapsableHeight;
		private final View mView;
		private final int mStartHeight;

		public ResizeAnimation(View view, int finalHeight)
		{
			mView = view;
			mStartHeight = view.getHeight();
			mCollapsableHeight = mStartHeight - finalHeight;

		}

		@Override
		protected void applyTransformation(float interpolatedTime, Transformation t)
		{
			mView.getLayoutParams().height = (int) (mStartHeight - mCollapsableHeight * interpolatedTime);
			mView.requestLayout();
		}

		@Override
		public boolean willChangeBounds()
		{
			return true;
		}
	}

}
