package com.lucevent.newsup.view.activity;

import android.graphics.Point;
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
import android.util.TypedValue;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lucevent.newsup.AppSettings;
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

public class HandsFreeNewsViewActivity extends FragmentActivity implements
		ViewPager.OnPageChangeListener,
		Runnable,
		GestureDetector.OnGestureListener,
		AppData.OnNewsListChange {

	private static final int SECONDS_BETWEEN_NEWS = 10 * 1000;
	private static final String GESTURE_EDUCATION_SHOWN_KEY = "gh_hands_free";
	private static RequestOptions glideOptions = new RequestOptions().centerCrop();
	private static int MAX_SWIPE_FOR_ACTION, MAX_MARK_TRANSLATION;
	private static final float MAX_MARK_SCALE = 1.4f;
	private static int HEADLINE_IMAGE_HEIGHT;

	private Handler mHandler = new Handler();
	private GestureDetectorCompat mGDetector;
	private NewsView mNewsView;

	private HeadlinesPagerAdapter mHeadlinesPagerAdapter;
	private ViewPager mViewPager;
	private ProgressBar mProgressBar;
	private View mCloseMark, mOpenNewsMark;

	private boolean mDisplayingContent = false;
	private float accumDistanceY = 0f;
	private boolean mVerticalScrolling = false, mHorizontalScrolling = false, mMustCloseOnUp = false, mMustOpenNewsOnUp = false;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_hands_free_news_view);

		MAX_SWIPE_FOR_ACTION = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, getResources().getDisplayMetrics());
		MAX_MARK_TRANSLATION = (int) (MAX_SWIPE_FOR_ACTION * 0.35);
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		HEADLINE_IMAGE_HEIGHT = (int) (0.45f * size.y);

		mHeadlinesPagerAdapter = new HeadlinesPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mHeadlinesPagerAdapter);
		mViewPager.addOnPageChangeListener(this);

		mProgressBar = (ProgressBar) findViewById(R.id.progress);
		mProgressBar.setMax((mHeadlinesPagerAdapter.getCount() - 1) * 100);
		mProgressBar.setProgress(0);

		mNewsView = (NewsView) findViewById(R.id.news_view);
		mNewsView.setFragmentContext(this, null);

		mCloseMark = findViewById(R.id.close_mark);
		mOpenNewsMark = findViewById(R.id.open_news_mark);

		if (!AppSettings.getStatus(GESTURE_EDUCATION_SHOWN_KEY, false)) {
			((ViewStub) findViewById(R.id.gesture_education)).inflate();
			findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					findViewById(R.id.gesture_education).setVisibility(View.GONE);

					if (((CheckBox) findViewById(R.id.do_not_show_again)).isChecked())
						AppSettings.setStatus(GESTURE_EDUCATION_SHOWN_KEY, true);

					startGestureDetection();
				}
			});
		} else
			startGestureDetection();

		AppData.setOnNewsListChange(this);
		hideSystemUi();
	}

	private void startGestureDetection()
	{
		mGDetector = new GestureDetectorCompat(this, this);
		mGDetector.setIsLongpressEnabled(false);
		mHandler.postDelayed(this, SECONDS_BETWEEN_NEWS);
	}

	@Override
	public void onBackPressed()
	{
		if (mDisplayingContent) {
			mNewsView.hideNews();
			mDisplayingContent = false;
			displayNext();
			return;
		}
		mHandler.removeCallbacks(this);
		super.onBackPressed();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		mNewsView.resume();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		mNewsView.pause();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		mNewsView.destroy();
		AppData.setOnNewsListChange(null);
	}

	private void hideSystemUi()
	{
		Window w = getWindow();
		w.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		w.getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LOW_PROFILE
						| View.SYSTEM_UI_FLAG_FULLSCREEN
						| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
		mHorizontalScrolling = positionOffset != 0.0;
		mProgressBar.setProgress((int) ((position + positionOffset) * 100));
	}

	@Override
	public void onPageSelected(int position)
	{
		mProgressBar.setMax((mHeadlinesPagerAdapter.getCount() - 1) * 100);
		mProgressBar.setProgress(position * 100);
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
	public boolean dispatchTouchEvent(MotionEvent event)
	{
		if (!mVerticalScrolling)
			super.dispatchTouchEvent(event);

		if (!mDisplayingContent) {
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
					mHandler.postDelayed(this, SECONDS_BETWEEN_NEWS);
				}
				mViewPager.setTranslationY(0);
				accumDistanceY = 0;
			}
			return (mGDetector != null && mGDetector.onTouchEvent(event));
		}
		return super.dispatchTouchEvent(event);
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
			mHandler.removeCallbacks(this);
			mVerticalScrolling = true;
		}
		if (mVerticalScrolling) {
			accumDistanceY -= distanceY;

			mMustCloseOnUp = accumDistanceY > MAX_SWIPE_FOR_ACTION;
			mMustOpenNewsOnUp = accumDistanceY < -MAX_SWIPE_FOR_ACTION;
			if (accumDistanceY > 0) {
				if (accumDistanceY >= MAX_SWIPE_FOR_ACTION)
					accumDistanceY = MAX_SWIPE_FOR_ACTION;

				float swipePercent = accumDistanceY / MAX_SWIPE_FOR_ACTION;
				float scale = MAX_MARK_SCALE * swipePercent;

				mCloseMark.setTranslationY(MAX_MARK_TRANSLATION * swipePercent);
				mCloseMark.setScaleX(scale);
				mCloseMark.setScaleY(scale);
			} else {
				if (accumDistanceY <= -MAX_SWIPE_FOR_ACTION)
					accumDistanceY = -MAX_SWIPE_FOR_ACTION;

				float swipePercent = accumDistanceY / MAX_SWIPE_FOR_ACTION;
				float scale = -(MAX_MARK_SCALE * swipePercent);

				mOpenNewsMark.setTranslationY(MAX_MARK_TRANSLATION * swipePercent);
				mOpenNewsMark.setScaleX(scale);
				mOpenNewsMark.setScaleY(scale);
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
		News news = mHeadlinesPagerAdapter.getNews(mViewPager.getCurrentItem());
		Site site = AppData.getSiteByCode(news.site_code);

		if (site instanceof UserSite) {
			Utils.openCustomTab(this, news);
			return;
		}

		KernelManager.readContentOf(news);
		if (news.content != null && !news.content.isEmpty()) {
			mNewsView.displayNews(news);
			mDisplayingContent = true;
			KernelManager.setNewsRead(news);
			return;
		}

		Utils.openCustomTab(this, news);
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
			finish();
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
			Fragment fragment = new HeadlineFragment();
			Bundle args = new Bundle();
			args.putInt(HeadlineFragment.ARG_ITEM, i);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount()
		{
			int size = mNews.size();
			return mNews.get(size - 1).id == News.ID_DUMMY ? size - 1 : size;
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
			View v = inflater.inflate(R.layout.f_hands_free_news_view, container, false);

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
						.into(mIVimage);

			return v;
		}
	}

}
