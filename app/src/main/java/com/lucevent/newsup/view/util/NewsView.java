package com.lucevent.newsup.view.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
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

	private News currentNews;

	private Activity activityContext;
	private DrawerLayout drawer;
	private ActionBar actionBar;

	private NewsElementsListView listView;
	private FloatingActionButton button_bookmark;
	private NewsSideToolbar sideToolbar;
	private boolean displayActionBar;

	public NewsView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		displayActionBar = false;

		((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.v_news, this, true);

		listView = (NewsElementsListView) findViewById(R.id.list);
		sideToolbar = (NewsSideToolbar) findViewById(R.id.side_toolbar);

		button_bookmark = (FloatingActionButton) findViewById(R.id.button_bookmark);
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

		if (viewHeight != -2) {
			int aux = viewWidth;
			viewWidth = viewHeight;
			viewHeight = aux;
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
		this.activityContext = a;
		if (a instanceof AppCompatActivity) {
			this.actionBar = ((AppCompatActivity) a).getSupportActionBar();
			this.actionBar.setShowHideAnimationEnabled(false);
		}
		this.drawer = drawer;
	}

	public void setBookmarkStateChangeListener(View.OnClickListener bookmarkStateChangeListener)
	{
		button_bookmark.setOnClickListener(bookmarkStateChangeListener);
	}

	public void setImageLongClickListener(OnLongClickListener listener)
	{
		listView.setImageLongClickListener(listener);
	}

	public void setUpNightMode()
	{
		boolean nightMode = AppSettings.getNightModeStatus();
		((ViewGroup) listView.getParent()).setBackgroundResource(nightMode ? R.color.nv_background_dark : R.color.nv_background);
		listView.setDarkStyle(nightMode);
	}

	private int viewWidth = -2;
	private int viewHeight = -2;

	public void displayNews(News news)
	{
		currentNews = news;
		button_bookmark.setTag(news);
		button_bookmark.setSelected(BookmarksManager.isBookmarked(news));

		Site site = AppData.getSiteByCode(currentNews.site_code);

		listView.clear();
		listView.set(currentNews.title, new NTVParser().parse(currentNews.content, site), site);

		if (drawer != null)
			drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

		if (actionBar != null && actionBar.isShowing()) {
			displayActionBar = true;
			actionBar.hide();
		}

		setVisibility(View.VISIBLE);

		if (viewHeight == -2) {
			Rect r = new Rect();
			activityContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
			viewHeight = ((ViewGroup) getParent()).getHeight() + (actionBar != null ? actionBar.getHeight() : 0) + r.top;
			viewWidth = getWidth();
		}

		AppAnimator.swipeUp(this, viewHeight, new AppAnimator.AppAnimatorListener() {
			@Override
			public void onAnimationEnd(Animation animation)
			{
				//  hiding System UI
				Window w = activityContext.getWindow();
				w.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				w.getDecorView().setSystemUiVisibility(
						View.SYSTEM_UI_FLAG_LOW_PROFILE
								| View.SYSTEM_UI_FLAG_FULLSCREEN
								| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
								| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
			}
		});
	}

	public void resume()
	{
		//      listView.onResume();
	}

	public void pause()
	{
		//   listView.onPause();
	}

	public void destroy()
	{
		PreferenceManager.getDefaultSharedPreferences(getContext()).unregisterOnSharedPreferenceChangeListener(onPreferenceChange);
	}

	public void hideNews()
	{
		if (actionBar != null) {
			// showing System UI
			Window w = activityContext.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

			if (displayActionBar)
				actionBar.show();
			displayActionBar = false;
		}

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run()
			{
				listView.clear();
			}
		}, 400);
		AppAnimator.swipeDown(this, this.getY(), viewHeight, new AppAnimator.AppAnimatorListener() {

			@Override
			public void onAnimationEnd(Animation animation)
			{
				if (drawer != null)
					drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

				setVisibility(View.GONE);
			}
		});

		if (!sideToolbar.isClosed())
			sideToolbar.close();
	}

	private final View.OnClickListener onShareAction = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, currentNews.title + " " + currentNews.link);
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
	private View fontSizeBox;
	private boolean fontSizeBoxShouldHide;
	private Handler mHandler = new Handler();
	//
	private final View.OnClickListener onFontSizeSelected = new View.OnClickListener() {
		@SuppressLint("SetTextI18n")
		@Override
		public void onClick(View v)
		{
			if (fontSizeBox == null) {
				fontSizeBox = ((ViewStub) findViewById(R.id.box_font_size)).inflate();

				int current_font_size = AppSettings.getFontSize(2);
				((TextView) fontSizeBox.findViewById(R.id.label)).setText(NewsWebViewViewHolder.FONT_SIZE_VALUES[current_font_size] + "%");

				SeekBar seekBar = (SeekBar) fontSizeBox.findViewById(R.id.seekBar);
				seekBar.setProgress(current_font_size);
				seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

					@Override
					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
					{
						listView.setTextSize(progress);
						((TextView) fontSizeBox.findViewById(R.id.label)).setText(NewsWebViewViewHolder.FONT_SIZE_VALUES[progress] + "%");
						AppSettings.setFontSize(progress);
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar)
					{
						synchronized (onFontSizeSelected) {
							fontSizeBoxShouldHide = false;
						}
					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar)
					{
						mHandler.postDelayed(new Runnable() {
							@Override
							public void run()
							{
								fontSizeBox.setVisibility(View.GONE);
							}
						}, 1000);
					}
				});
				setHidingTimer();
				return;
			}
			if (fontSizeBox.getVisibility() != VISIBLE) {
				fontSizeBox.setVisibility(VISIBLE);
				setHidingTimer();
			} else
				fontSizeBox.setVisibility(GONE);
		}

		private void setHidingTimer()
		{
			fontSizeBoxShouldHide = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run()
				{
					synchronized (onFontSizeSelected) {
						if (fontSizeBoxShouldHide)
							fontSizeBox.setVisibility(View.GONE);
					}
				}
			}, 3000);
		}
	};

	private final View.OnClickListener onOpenInBrowserSelected = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			Utils.openCustomTab(v.getContext(), currentNews);
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