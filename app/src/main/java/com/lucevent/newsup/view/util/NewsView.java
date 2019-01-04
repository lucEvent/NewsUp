package com.lucevent.newsup.view.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.parse.NTVParser;
import com.lucevent.newsup.view.adapter.viewholder.news.NewsWebViewViewHolder;

public class NewsView extends RelativeLayout {

	private News mCurrentNews;

	private Activity mActivityContext;
	private DrawerLayout mDrawer;
	private ActionBar mActionBar;
	private boolean mImmersiveMode;

	private NewsElementsListView mListView;
	private FloatingActionButton mBookmarkBtn;
	private NewsSideToolbar mSideToolbar;
	private boolean mDisplayActionBar;

	public NewsView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		mDisplayActionBar = false;

		((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.v_news, this, true);

		mListView = (NewsElementsListView) findViewById(R.id.news_elems_list);
		mSideToolbar = (NewsSideToolbar) findViewById(R.id.side_toolbar);

		mBookmarkBtn = (FloatingActionButton) findViewById(R.id.button_bookmark);
		findViewById(R.id.button_share).setOnClickListener(onShareAction);
		findViewById(R.id.button_night).setOnClickListener(onNightModeSelected);
		findViewById(R.id.button_font_size).setOnClickListener(onFontSizeSelected);
		findViewById(R.id.button_open_in_browser).setOnClickListener(onOpenInBrowserSelected);

		setUpNightMode();
		PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(onPreferenceChange);
	}

	@Override
	protected void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);

		if (mHeight != -2) {
			int aux = mWidth;
			mWidth = mHeight;
			mHeight = aux;
		}

		if (getVisibility() == View.VISIBLE) {

			ViewGroup.LayoutParams layoutParams = getLayoutParams();

			layoutParams.width = -1;
			layoutParams.height = -1;

			requestLayout();
		}
	}

	public void setFragmentContext(Fragment fragmentContext, @Nullable DrawerLayout drawer)
	{
		setFragmentContext(fragmentContext.getActivity(), drawer);
	}

	public void setFragmentContext(Activity a, @Nullable DrawerLayout drawer)
	{
		mActivityContext = a;
		if (a instanceof AppCompatActivity) {
			mActionBar = ((AppCompatActivity) a).getSupportActionBar();
			mActionBar.setShowHideAnimationEnabled(false);
		}
		mDrawer = drawer;
	}

	public void setBookmarkStateChangeListener(View.OnClickListener bookmarkStateChangeListener)
	{
		mBookmarkBtn.setOnClickListener(bookmarkStateChangeListener);
	}

	private OnLongClickListener mOnImageLongClickListener = new OnLongClickListener() {
		@Override
		public boolean onLongClick(final View v)
		{
			new AlertDialog.Builder(getContext())
					.setMessage(R.string.msg_confirm_to_download_image)
					.setNegativeButton(R.string.cancel, null)
					.setPositiveButton(R.string.download, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							onImageLongClickListener.onLongClick(v);
						}
					})
					.show();
			return true;
		}
	};

	private OnLongClickListener onImageLongClickListener;

	public void setImageLongClickListener(OnLongClickListener listener)
	{
		onImageLongClickListener = listener;
		mListView.setImageLongClickListener(mOnImageLongClickListener);
	}

	public void setUpNightMode()
	{
		boolean nightMode = AppSettings.getNightModeStatus();
		((ViewGroup) mListView.getParent()).setBackgroundResource(nightMode ? R.color.nv_background_dark : R.color.nv_background);
		mListView.setDarkStyle(nightMode);
	}

	private int mWidth = -2;
	private int mHeight = -2;

	public void displayNews(News news, boolean immersiveMode)
	{
		mCurrentNews = news;
		mImmersiveMode = immersiveMode;
		mBookmarkBtn.setTag(news);
		mBookmarkBtn.setSelected(BookmarksManager.isBookmarked(news));

		Site site = AppData.getSiteByCode(mCurrentNews.site_code);

		mListView.clear();
		mListView.set(mCurrentNews.title, new NTVParser().parse(mCurrentNews.content, site), site);

		if (mDrawer != null && mImmersiveMode)
			mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

		if (mActionBar != null && mActionBar.isShowing() && mImmersiveMode) {
			mDisplayActionBar = true;
			mActionBar.hide();
		}

		setVisibility(View.VISIBLE);

		if (mHeight == -2) {
			Rect r = new Rect();
			mActivityContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
			mHeight = ((ViewGroup) getParent()).getHeight() + (mActionBar != null ? mActionBar.getHeight() : 0) + r.top;
			mWidth = getWidth();
		}

		AppAnimator.swipeUp(this, mHeight, new AppAnimator.AppAnimatorListener() {
			@Override
			public void onAnimationEnd(Animation animation)
			{
				if (mImmersiveMode) {
					//  hiding System UI
					Window w = mActivityContext.getWindow();
					w.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
					w.getDecorView().setSystemUiVisibility(
							View.SYSTEM_UI_FLAG_LOW_PROFILE
									| View.SYSTEM_UI_FLAG_FULLSCREEN
									| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
									| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
				}
			}
		});
	}

	public void resume()
	{
//      mListView.onResume();
	}

	public void pause()
	{
//   mListView.onPause();
	}

	public void destroy()
	{
		PreferenceManager.getDefaultSharedPreferences(getContext()).unregisterOnSharedPreferenceChangeListener(onPreferenceChange);
	}

	public void hideNews()
	{
		if (mActionBar != null && mImmersiveMode) {
			// showing System UI
			Window w = mActivityContext.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

			if (mDisplayActionBar)
				mActionBar.show();
			mDisplayActionBar = false;
		}

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run()
			{
				mListView.clear();
			}
		}, 400);
		AppAnimator.swipeDown(this, this.getY(), mHeight, new AppAnimator.AppAnimatorListener() {

			@Override
			public void onAnimationEnd(Animation animation)
			{
				if (mDrawer != null && mImmersiveMode)
					mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

				setVisibility(View.GONE);
			}
		});

		if (!mSideToolbar.isClosed())
			mSideToolbar.close();
	}

	private final View.OnClickListener onShareAction = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, mCurrentNews.title + " " + mCurrentNews.link);
			sendIntent.setType("text/plain");
			getContext().startActivity(Intent.createChooser(sendIntent, getContext().getString(R.string.share_with)));
		}
	};

	private final View.OnClickListener onNightModeSelected = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			AppSettings.toggleNightMode();
			setUpNightMode();
		}
	};

	//
	private View mFontSizeBox;
	private boolean mFontSizeBoxShouldHide;
	private Handler mHandler = new Handler();
	//
	private final View.OnClickListener onFontSizeSelected = new View.OnClickListener() {
		@SuppressLint("SetTextI18n")
		@Override
		public void onClick(View v)
		{
			if (mFontSizeBox == null) {
				mFontSizeBox = ((ViewStub) findViewById(R.id.box_font_size)).inflate();

				int current_font_size = AppSettings.getFontSize(2);
				((TextView) mFontSizeBox.findViewById(R.id.label)).setText(NewsWebViewViewHolder.FONT_SIZE_VALUES[current_font_size] + "%");

				SeekBar seekBar = (SeekBar) mFontSizeBox.findViewById(R.id.seekBar);
				seekBar.setProgress(current_font_size);
				seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

					@Override
					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
					{
						mListView.setTextSize(progress);
						((TextView) mFontSizeBox.findViewById(R.id.label)).setText(NewsWebViewViewHolder.FONT_SIZE_VALUES[progress] + "%");
						AppSettings.setFontSize(progress);
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar)
					{
						synchronized (onFontSizeSelected) {
							mFontSizeBoxShouldHide = false;
						}
					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar)
					{
						mHandler.postDelayed(new Runnable() {
							@Override
							public void run()
							{
								mFontSizeBox.setVisibility(View.GONE);
							}
						}, 1000);
					}
				});
				setHidingTimer();
				return;
			}
			if (mFontSizeBox.getVisibility() != VISIBLE) {
				mFontSizeBox.setVisibility(VISIBLE);
				setHidingTimer();
			} else
				mFontSizeBox.setVisibility(GONE);
		}

		private void setHidingTimer()
		{
			mFontSizeBoxShouldHide = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run()
				{
					synchronized (onFontSizeSelected) {
						if (mFontSizeBoxShouldHide)
							mFontSizeBox.setVisibility(View.GONE);
					}
				}
			}, 3000);
		}
	};

	private final View.OnClickListener onOpenInBrowserSelected = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			Utils.openCustomTab(v.getContext(), mCurrentNews);
		}
	};

	SharedPreferences.OnSharedPreferenceChangeListener onPreferenceChange = new SharedPreferences.OnSharedPreferenceChangeListener() {
		public void onSharedPreferenceChanged(SharedPreferences prefs, String key)
		{
			if (key.equals(AppSettings.PREF_NIGHT_MODE_KEY))
				setUpNightMode();
		}
	};

}