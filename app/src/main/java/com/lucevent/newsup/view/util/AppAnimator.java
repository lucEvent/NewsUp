package com.lucevent.newsup.view.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

public class AppAnimator {

    private static final long animationTime = 400;

    static void expandMoving(View view, View from, int finalHeight, Animation.AnimationListener animationListener)
    {
        TranslateAnimation movement = new TranslateAnimation(0, 0, from.getY(), 0);
        AppAnimator.ResizeAnimation resizing = new AppAnimator.ResizeAnimation(view, finalHeight, from.getHeight());
        resizing.setAnimationListener(animationListener);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(movement);
        animationSet.addAnimation(resizing);
        animationSet.setDuration(animationTime);
        animationSet.setFillAfter(false);
        animationSet.setFillBefore(true);

        view.startAnimation(animationSet);
    }

    static void collapseMoving(View view, View to, int startHeight, Animation.AnimationListener animationListener)
    {
        TranslateAnimation movement = new TranslateAnimation(0, 0, 0, to.getY());
        AppAnimator.ResizeAnimation resizing = new AppAnimator.ResizeAnimation(view, to.getHeight(), startHeight);
        resizing.setAnimationListener(animationListener);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(movement);
        animationSet.addAnimation(resizing);
        animationSet.setDuration(animationTime);
        animationSet.setFillBefore(true);

        view.startAnimation(animationSet);
    }

    static class AppAnimatorListener implements Animation.AnimationListener {

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

    static class ResizeAnimation extends Animation {
        private final int targetHeight;
        private View view;
        private int startHeight;

        ResizeAnimation(View view, int targetHeight, int startHeight)
        {
            this.view = view;
            this.targetHeight = targetHeight;
            this.startHeight = startHeight;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t)
        {
            view.getLayoutParams().height = (int) (startHeight + (targetHeight - startHeight) * interpolatedTime);
            view.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight)
        {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds()
        {
            return true;
        }
    }

}
