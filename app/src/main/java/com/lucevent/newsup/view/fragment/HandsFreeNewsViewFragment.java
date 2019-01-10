package com.lucevent.newsup.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.UserSite;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.view.util.NewsAdapterList;
import com.lucevent.newsup.view.util.NewsView;
import com.lucevent.newsup.view.util.Utils;

public class HandsFreeNewsViewFragment extends Fragment implements
		ViewPager.OnPageChangeListener,
		Runnable,
		GestureDetector.OnGestureListener,
		AppData.OnNewsListChange {

	private static final int SECONDS_BETWEEN_NEWS = 10 * 1000;
	private static final String GESTURE_EDUCATION_SHOWN_KEY = "gh_hands_free";
	private static RequestOptions glideOptions = new RequestOptions().centerCrop();
	private static int MAX_ACTION_SWIPE;
	private static int HEADLINE_IMAGE_HEIGHT;

	private Handler mHandler = new Handler();
	private GestureDetectorCompat mGDetector;
	private NewsView mNewsView;

	private HeadlinesPagerAdapter mHeadlinesPagerAdapter;
	private HeadLinesViewPager mViewPager;
	private ProgressBar mProgressBar;
	private View mCloseHelp, mOpenNewsHelp;

	private boolean mDisplayingContent = false;
	private float accumDistanceY = 0f;
	private boolean mVerticalScrolling = false, mHorizontalScrolling = false, mMustCloseOnUp = false, mMustOpenNewsOnUp = false;

	public void setNewsView(NewsView nv)
	{
		mNewsView = nv;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		FragmentActivity a = getActivity();
		final View v = inflater.inflate(R.layout.f_hands_free_news_view, container, false);

		MAX_ACTION_SWIPE = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 52, getResources().getDisplayMetrics());
		Point size = new Point();
		a.getWindowManager().getDefaultDisplay().getSize(size);
		HEADLINE_IMAGE_HEIGHT = (int) (0.45f * size.y);

		mHeadlinesPagerAdapter = new HeadlinesPagerAdapter(a.getSupportFragmentManager());
		mViewPager = (HeadLinesViewPager) v.findViewById(R.id.pager);
		mViewPager.setAdapter(mHeadlinesPagerAdapter);
		mViewPager.addOnPageChangeListener(this);
		mViewPager.mHFNVFragment = this;

		mProgressBar = (ProgressBar) v.findViewById(R.id.progress);
		mProgressBar.setMax((mHeadlinesPagerAdapter.getCount() - 3) * 100);
		mProgressBar.setProgress(0);
		mViewPager.setCurrentItem(1);

		mCloseHelp = v.findViewById(R.id.help_close);
		mOpenNewsHelp = v.findViewById(R.id.help_open_news);

		if (!AppSettings.getStatus(GESTURE_EDUCATION_SHOWN_KEY, false)) {
			((ViewStub) v.findViewById(R.id.gesture_education)).inflate();
			v.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View btn)
				{
					v.findViewById(R.id.gesture_education).setVisibility(View.GONE);

					if (((CheckBox) v.findViewById(R.id.do_not_show_again)).isChecked())
						AppSettings.setStatus(GESTURE_EDUCATION_SHOWN_KEY, true);

					startGestureDetection();
				}
			});
		} else
			startGestureDetection();

		AppData.setOnNewsListChange(this);
		hideSystemUi(a);

		return v;
	}

	private void startGestureDetection()
	{
		mGDetector = new GestureDetectorCompat(getActivity(), this);
		mGDetector.setIsLongpressEnabled(false);
		mHandler.postDelayed(this, SECONDS_BETWEEN_NEWS);
	}

	public boolean onBackPressed()
	{
		if (mDisplayingContent) {
			mNewsView.hideNews();
			mDisplayingContent = false;
			displayNext();
			return true;
		}
		finish();
		return false;
	}

	@Override
	public void onResume()
	{
		super.onResume();
		mNewsView.resume();

		Activity a = getActivity();
		if (a instanceof Main)
			((Main) a).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		mNewsView.pause();

		Activity a = getActivity();
		if (a instanceof Main)
			((Main) a).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mNewsView.destroy();
		AppData.setOnNewsListChange(null);
	}

	private void hideSystemUi(Activity a)
	{
		Window w = a.getWindow();
		w.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		w.getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LOW_PROFILE
						| View.SYSTEM_UI_FLAG_FULLSCREEN
						| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

		// Hidding ActionBar
		try {
			ActionBar mActionBar = ((AppCompatActivity) a).getSupportActionBar();
			mActionBar.setShowHideAnimationEnabled(false);
			mActionBar.hide();
		} catch (Exception ignored) {
		}
	}

	private boolean dispatchTouchEvent(MotionEvent event)
	{
		if (mVerticalScrolling && event.getAction() == MotionEvent.ACTION_UP) {
			mVerticalScrolling = false;

			if (mMustCloseOnUp) {
				finish();
				return true;
			}
			if (mMustOpenNewsOnUp) {
				mMustOpenNewsOnUp = false;
				displayCurrentContent();
			} else {
				Animation a = new TranslateAnimation(0f, 0f, mViewPager.getY(), 0f);
				a.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation)
					{
					}

					@Override
					public void onAnimationEnd(Animation animation)
					{
						mViewPager.setTranslationY(0);
					}

					@Override
					public void onAnimationRepeat(Animation animation)
					{
					}
				});
				a.setDuration(200);
				mViewPager.startAnimation(a);
				mHandler.postDelayed(HandsFreeNewsViewFragment.this, SECONDS_BETWEEN_NEWS);
			}
			mViewPager.setTranslationY(0);
			accumDistanceY = 0;
		}
		return (mGDetector != null && mGDetector.onTouchEvent(event));
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
		mHorizontalScrolling = positionOffset != 0.0;
		mProgressBar.setProgress((int) ((position - 1 + positionOffset) * 100));
	}

	@Override
	public void onPageSelected(int position)
	{
		mProgressBar.setMax((mHeadlinesPagerAdapter.getCount() - 3) * 100);
		mProgressBar.setProgress(position * 100);

		if (position == 0 || position == mHeadlinesPagerAdapter.getCount() - 1) {
			finish();
		}
	}

	boolean dragging = false;

	@Override
	public void onPageScrollStateChanged(int state)
	{
		if (state == ViewPager.SCROLL_STATE_DRAGGING) {
			if (!dragging) {
				mHandler.removeCallbacks(this);
				dragging = true;
			}
		} else {
			if (dragging) {
				mHandler.postDelayed(this, SECONDS_BETWEEN_NEWS);
				dragging = false;
			}
		}
	}

	@Override
	public boolean onDown(MotionEvent e)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		if (!mHorizontalScrolling && !mVerticalScrolling && Math.abs(distanceX) < 2.0) {
			// vertical scroll just starts in this call
			mHandler.removeCallbacks(this);
			mVerticalScrolling = true;
		}
		if (mVerticalScrolling) {
			accumDistanceY -= distanceY;

			mMustCloseOnUp = accumDistanceY > MAX_ACTION_SWIPE;
			mMustOpenNewsOnUp = accumDistanceY < -MAX_ACTION_SWIPE;
			if (accumDistanceY > 0) {
				if (accumDistanceY >= MAX_ACTION_SWIPE)
					accumDistanceY = MAX_ACTION_SWIPE;

				mCloseHelp.setTranslationY(accumDistanceY - MAX_ACTION_SWIPE);
			} else {
				if (accumDistanceY <= -MAX_ACTION_SWIPE)
					accumDistanceY = -MAX_ACTION_SWIPE;

				mOpenNewsHelp.setTranslationY(accumDistanceY + MAX_ACTION_SWIPE);
			}
			mViewPager.setTranslationY(accumDistanceY);
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		return false;
	}

	private void displayCurrentContent()
	{
		News news = mHeadlinesPagerAdapter.getNews(mViewPager.getCurrentItem() - 1);
		Site site = AppData.getSiteByCode(news.site_code);

		if (site instanceof UserSite) {
			Utils.openCustomTab(getActivity(), news);
			return;
		}

		KernelManager.readContentOf(news);
		if (news.content != null && !news.content.isEmpty()) {
			mNewsView.displayNews(news, false);
			mDisplayingContent = true;
			KernelManager.setNewsRead(news);
			return;
		}

		Utils.openCustomTab(getActivity(), news);
	}

	private void displayNext()
	{
		run();
	}

	@Override
	public void run()
	{
		// Displaying next news
		int current = mViewPager.getCurrentItem();
		if (current >= mHeadlinesPagerAdapter.getCount() - 1) {
			return;
		}
		mViewPager.setCurrentItem(current + 1);

		if (mGDetector != null)
			mHandler.postDelayed(this, SECONDS_BETWEEN_NEWS);
	}

	@Override
	public void onChange(NewsAdapterList currentNewsList)
	{
		mHeadlinesPagerAdapter.notifyDataSetChanged();
	}

	/**
	 * HeadLinesViewPager
	 */
	public static class HeadLinesViewPager extends ViewPager {

		HandsFreeNewsViewFragment mHFNVFragment;

		public HeadLinesViewPager(@NonNull Context context)
		{
			super(context);
		}

		public HeadLinesViewPager(@NonNull Context context, @Nullable AttributeSet attrs)
		{
			super(context, attrs);
		}

		@Override
		public boolean dispatchTouchEvent(MotionEvent event)
		{
			boolean r1 = false, r2 = false;
			if (!mHFNVFragment.mVerticalScrolling)
				r1 = super.dispatchTouchEvent(event);

			if (!mHFNVFragment.mDisplayingContent)
				r2 = mHFNVFragment.dispatchTouchEvent(event);

			return r1 || r2;
		}
	}

	/**
	 * HeadlinesPagerAdapter
	 */
	class HeadlinesPagerAdapter extends FragmentStatePagerAdapter {

		private NewsAdapterList mNews;

		HeadlinesPagerAdapter(FragmentManager fm)
		{
			super(fm);
			mNews = AppData.getCurrentNewsList();
		}

		@Override
		public Fragment getItem(int i)
		{
			if (i == 0 || i == getCount() - 1)  // dummy pages to exit by swiping left/right at the beginning/end
				return new DummyFragment();

			Fragment fragment = new HeadlineFragment();
			Bundle args = new Bundle();
			args.putInt(HeadlineFragment.ARG_ITEM, i - 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount()
		{
			int size = mNews.size();
			return (mNews.get(size - 1).id == News.ID_DUMMY ? size - 1 : size) + 2; // +2 -> dummy pages to exit by swiping left/right at the beginning/end
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			return "";
		}

		News getNews(int i)
		{
			return mNews.get(i);
		}

	}

	/**
	 * HeadlineFragment
	 */
	public static class HeadlineFragment extends Fragment {
		public static final String ARG_ITEM = "item";

		private TextView mTVtitle, mTVdescription;
		private ImageView mIVicon, mIVimage;

		@Override
		public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View v = inflater.inflate(R.layout.f_hands_free_headline, container, false);

			mTVtitle = (TextView) v.findViewById(R.id.title);
			mTVdescription = (TextView) v.findViewById(R.id.description);
			mIVicon = (ImageView) v.findViewById(R.id.icon);
			mIVimage = (ImageView) v.findViewById(R.id.image);

			//
			mIVimage.getLayoutParams().height = HEADLINE_IMAGE_HEIGHT;
			//

			News news = AppData.getCurrentNewsList()
					.get(getArguments().getInt(ARG_ITEM));

			mTVtitle.setText(news.title);
			mTVdescription.setText(news.description);
			mIVicon.setImageDrawable(LogoManager.getLogo(news.site_code, LogoManager.Size.ACTION_BAR));

			mIVimage.setImageResource(R.color.colorPrimary);
			if (news.imgSrc == null)
				news.forceImage();

			if (news.imgSrc != null)
				Glide.with(this)
						.applyDefaultRequestOptions(glideOptions)
						.load(news.imgSrc)
						.into(mIVimage)
						.onLoadFailed(getNoImageDrawable());
			else
				mIVimage.setImageDrawable(getNoImageDrawable());

			return v;
		}

		private static Drawable mNoImageDrawable = null;

		private Drawable getNoImageDrawable()
		{
			if (mNoImageDrawable == null) {
				Activity activity = getActivity();
				Resources res = activity.getResources();

				// Getting screen width
				Display display = activity.getWindowManager().getDefaultDisplay();
				Point size = new Point();
				display.getSize(size);
				int width = size.x;

				// Setting paint
				Paint p = new Paint();
				p.setStrokeWidth(4);
				p.setColor(res.getColor(R.color.default_text));
				p.setStyle(Paint.Style.STROKE);

				int bgColor = res.getColor(R.color.colorPrimary);
				int framePadding = 40;

				// Drawing canvas
				Bitmap bm = Bitmap.createBitmap(width, HEADLINE_IMAGE_HEIGHT, Bitmap.Config.RGB_565);
				Canvas c = new Canvas(bm);
				c.drawARGB(0xff, (bgColor >> 16) & 0xff, (bgColor >> 8) & 0xff, bgColor & 0xff);
				c.drawRect(framePadding, framePadding, width - framePadding, HEADLINE_IMAGE_HEIGHT - framePadding, p);

				// Drawing text
				p.setStyle(Paint.Style.FILL);
				p.setTextAlign(Paint.Align.CENTER);
				p.setTextSize(48);
				c.drawText(res.getText(R.string.msg_no_image).toString(), width / 2, HEADLINE_IMAGE_HEIGHT / 2, p);

				mNoImageDrawable = new BitmapDrawable(getResources(), bm);
			}
			return mNoImageDrawable;
		}
	}

	public static class DummyFragment extends Fragment {
		@Override
		public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View v = new FrameLayout(inflater.getContext());
			v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			v.setBackgroundColor(0x00000000);
			return v;
		}
	}

	private void finish()
	{
		mHandler.removeCallbacks(this);

		FragmentActivity a = getActivity();
		if (a == null)
			return;
		a.getSupportFragmentManager().beginTransaction()
				.remove(this)
				.commit();

		// Redisplaying system IU (top bar)
		Window w = a.getWindow();
		w.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

		// Redisplaying ActionBar
		try {
			ActionBar mActionBar = ((AppCompatActivity) a).getSupportActionBar();
			mActionBar.setShowHideAnimationEnabled(false);
			mActionBar.show();
		} catch (Exception ignored) {
		}
	}

}
