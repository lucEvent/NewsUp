package com.lucevent.newsup.view.activity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
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

public class HandsFreeNewsViewActivity extends Activity implements
		Runnable,
		GestureDetector.OnGestureListener {

	private static final int SECONDS_BETWEEN_NEWS = 10 * 1000;
	private static final String GESTURE_EDUCATION_SHOWN_KEY = "gh_hands_free";
	private static RequestOptions glideOptions = new RequestOptions().centerCrop();
	private NewsAdapterList mNews;
	private Handler mHandler = new Handler();
	private GestureDetectorCompat mGDetector;
	private NewsView mNewsView;

	private int mCurrent = 0;
	private boolean mDisplayingContent = false;

	// views
	private TextView mTVtitle, mTVdescription;
	private ImageView mIVicon, mIVimage;
	private ProgressBar mProgressBar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_hands_free_news_view);

		mTVtitle = (TextView) findViewById(R.id.title);
		mTVdescription = (TextView) findViewById(R.id.description);
		mIVicon = (ImageView) findViewById(R.id.icon);
		mIVimage = (ImageView) findViewById(R.id.image);
		mProgressBar = (ProgressBar) findViewById(R.id.progress);

		//
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		mIVimage.getLayoutParams().height = (int) (0.45f * size.y);
		//

		mNews = AppData.getCurrentNewsList();

		mNewsView = (NewsView) findViewById(R.id.news_view);
		mNewsView.setFragmentContext(this, null);

		mProgressBar.setMax(mNews.size());

		if (!AppSettings.getStatus(GESTURE_EDUCATION_SHOWN_KEY, false)) {
			((ViewStub) findViewById(R.id.gesture_education)).inflate();
			findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					findViewById(R.id.gesture_education).setVisibility(View.GONE);

					if (((CheckBox) findViewById(R.id.do_not_show_again)).isChecked())
						AppSettings.setStatus(GESTURE_EDUCATION_SHOWN_KEY, true);

					startGestureDetection(true);
				}
			});
		} else
			startGestureDetection(false);

		hideSystemUi();
		displayCurrent();
	}

	private void startGestureDetection(boolean wasEducationShown)
	{
		mGDetector = new GestureDetectorCompat(this, this);
		mGDetector.setIsLongpressEnabled(false);

		if (wasEducationShown)
			mHandler.postDelayed(this, SECONDS_BETWEEN_NEWS);
	}

	private void displayNext()
	{
		mCurrent++;
		displayCurrent();
	}

	private void displayPrevious()
	{
		if (mCurrent > 0)
			mCurrent--;
		displayCurrent();
	}

	private void displayCurrent()
	{
		if (mCurrent >= mNews.size()) {
			finish();
			return;
		}
		mProgressBar.setMax(mNews.size());
		mProgressBar.setProgress(mCurrent);

		News news = mNews.get(mCurrent);
		if (news.id == News.ID_DUMMY) {
			finish();
			return;
		}

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

		if (mGDetector != null)
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
	protected void onDestroy()
	{
		super.onDestroy();

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
	public boolean onTouchEvent(MotionEvent event)
	{
		return (mGDetector != null && mGDetector.onTouchEvent(event)) || super.onTouchEvent(event);
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
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		float absVX = Math.abs(velocityX);
		float absVY = Math.abs(velocityY);
		if (absVX > absVY * 2) {

			mHandler.removeCallbacks(this);
			if (velocityX > 0)
				displayPrevious();
			else
				displayNext();

		} else if (absVX * 2 < absVY) {

			mHandler.removeCallbacks(this);
			if (velocityY > 0)
				finish();
			else
				displayCurrentContent();
		}
		return false;
	}

	private void displayCurrentContent()
	{
		News news = mNews.get(mCurrent);
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

	@Override
	public void run()
	{
		displayNext();
	}

}
