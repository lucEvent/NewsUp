package com.lucevent.newsup.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.view.adapter.NewsFilterAdapter;
import com.lucevent.newsup.view.dialog.EventConfigDialog;
import com.lucevent.newsup.view.fragment.HandsFreeNewsViewFragment;
import com.lucevent.newsup.view.util.NewsAdapterList;
import com.lucevent.newsup.view.util.NewsView;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.TreeSet;

public class EventActivity extends StoragePermissionActivity implements View.OnClickListener,
		SwipeRefreshLayout.OnRefreshListener {

	private Event mEvent;

	private KernelManager mManager;

	private Handler mHandler;

	private NewsFilterAdapter mAdapter;
	private NewsView mNewsView;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private RecyclerView mRecyclerView;

	private boolean mDisplayingNews = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_event);

		mHandler = new Handler(this);
		mManager = new KernelManager(this);

		// Views
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mAdapter = new NewsFilterAdapter(this, onBookmarkClick, NewsAdapterList.SortBy.byTime);
		mAdapter.setUserPreferences(this);
		mAdapter.showSiteIcon(true);

		mSwipeRefreshLayout = findViewById(R.id.refresh_trigger);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
		mSwipeRefreshLayout.setRefreshing(true);

		mRecyclerView = (RecyclerView) findViewById(R.id.list);
		mRecyclerView.setNestedScrollingEnabled(false);
		mRecyclerView.setHasFixedSize(false);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(mAdapter);

		mNewsView = (NewsView) findViewById(R.id.news_view);
		mNewsView.setFragmentContext(this, null);
		mNewsView.setBookmarkStateChangeListener(onBookmarkClick);
		mNewsView.setImageLongClickListener(onImageLongClick);

		findViewById(R.id.btn_hands_free).setOnClickListener(onViewHandsFree);

		// setup
		Bundle bundle = getIntent().getExtras();
		mEvent = AppData.getEvent(bundle.getInt(AppCode.EVENT_CODE));

		TreeSet<Integer> filterCodes = AppSettings.getEventFilter(mEvent.code);
		if (filterCodes != null)
			mAdapter.filter(filterCodes);

		mManager.getReaderManager().read(mEvent, mHandler);
		setTitle(mEvent.title);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		mNewsView.resume();
	}

	@Override
	public void onPause()
	{
		super.onPause();
		mNewsView.pause();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		mNewsView.destroy();
	}

	@Override
	public void onBackPressed()
	{
		if (mHandsFreeFragment != null) {
			if (!mHandsFreeFragment.onBackPressed())
				mHandsFreeFragment = null;
			return;
		}

		if (mDisplayingNews) {
			mNewsView.hideNews();
			mDisplayingNews = false;
		} else {
			mManager.getReaderManager().cancelAll();
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(R.string.menu_settings)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
				.setIcon(R.drawable.ic_configuration)
				.setOnMenuItemClickListener(onConfiguration);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
		}
		return true;
	}

	@Override
	public void onClick(final View v)
	{
		final News news = (News) v.getTag();
		final Context context = this;

		KernelManager.readContentOf(news);

		if (news.content != null && !news.content.isEmpty()) {
			mNewsView.displayNews(news, true);
			mDisplayingNews = true;
			KernelManager.setNewsRead(news);
			return;
		}
		KernelManager.fetchContentOf(news);

		View view = LayoutInflater.from(context).inflate(R.layout.d_news_not_found, null);

		final AlertDialog dialog = new AlertDialog.Builder(context)
				.setTitle(R.string.msg_cant_load_content)
				.setMessage(R.string.msg_news_not_found)
				.setView(view)
				.create();

		view.findViewById(R.id.action_try_again).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v2)
			{
				EventActivity.this.onClick(v);
				dialog.dismiss();
			}
		});
		view.findViewById(R.id.action_open_in_browser).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v2)
			{
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.link));
				context.startActivity(browserIntent);
				dialog.dismiss();
			}
		});
		view.findViewById(R.id.action_close).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v2)
			{
				dialog.dismiss();
			}
		});

		dialog.show();
	}

	private HandsFreeNewsViewFragment mHandsFreeFragment;

	private View.OnClickListener onViewHandsFree = new View.OnClickListener() {

		@Override
		public void onClick(View v)
		{
			mAdapter.discloseData();
			if (mAdapter.getItemCount() > 0) {
				mHandsFreeFragment = new HandsFreeNewsViewFragment();
				mHandsFreeFragment.setNewsView(mNewsView);

				getSupportFragmentManager().beginTransaction()
						.add(R.id.v_hands_free, mHandsFreeFragment)
						.commit();
			}
		}
	};

	@Override
	public void onRefresh()
	{
		mAdapter.clear();
		mManager.getReaderManager().read(mEvent, mHandler);
	}

	private static class Handler extends android.os.Handler {

		private final WeakReference<EventActivity> context;

		Handler(EventActivity context)
		{
			this.context = new WeakReference<>(context);
		}

		@Override
		public void handleMessage(Message msg)
		{
			EventActivity service = context.get();
			switch (msg.what) {
				case AppCode.NEWS_COLLECTION:
					if (service.mAdapter == null)
						return;

					Collection<News> news = (Collection<News>) msg.obj;

					if (news.isEmpty())
						return;

					service.mAdapter.addAll(news);
					if (service.mAdapter.getItemCount() == 0 || service.mRecyclerView.computeVerticalScrollOffset() == 0)
						service.mRecyclerView.smoothScrollToPosition(0);
					break;
				case AppCode.NEWS_LOADED:
					if (service.mSwipeRefreshLayout.isRefreshing())
						service.mSwipeRefreshLayout.setRefreshing(false);
					break;
				case AppCode.NO_INTERNET:
					Snackbar.make(service.mRecyclerView, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG).show();
					break;
				case AppCode.ERROR:
					AppSettings.printerror("[EA] Error received by the Handler", null);
					break;
				default:
					AppSettings.printerror("[EA] OPTION UNKNOWN: " + msg.what, null);
			}
		}

	}

	private EventConfigDialog configDialog;

	private MenuItem.OnMenuItemClickListener onConfiguration = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			if (configDialog == null)
				configDialog = new EventConfigDialog(EventActivity.this)
						.event(mEvent)
						.listener(onFilterListener);

			configDialog.show();
			return true;
		}

		EventConfigDialog.Callback onFilterListener = new EventConfigDialog.Callback() {
			@Override
			public void onFilter(TreeSet<Integer> f)
			{
				mAdapter.filter(f);
			}
		};

	};

	@Override
	protected void onBookmarkStateChanged(View btn)
	{
		News news = (News) btn.getTag();

		btn.setSelected(
				BookmarksManager.toggleBookmark(news)
		);

		if (btn instanceof FloatingActionButton)
			mAdapter.update(news);
	}

}