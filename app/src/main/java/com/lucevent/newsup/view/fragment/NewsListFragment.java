package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.OnReplaceFragmentListener;
import com.lucevent.newsup.ProSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.alert.Alert;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.UserSite;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.services.StatisticsService;
import com.lucevent.newsup.services.util.DownloadData;
import com.lucevent.newsup.services.util.DownloadNotification;
import com.lucevent.newsup.view.activity.HandsFreeNewsViewActivity;
import com.lucevent.newsup.view.adapter.NewsAdapter;
import com.lucevent.newsup.view.dialog.AppAlertDialog;
import com.lucevent.newsup.view.dialog.SectionsDialog;
import com.lucevent.newsup.view.util.NewsAdapterList;
import com.lucevent.newsup.view.util.NewsView;
import com.lucevent.newsup.view.util.OnBackPressedListener;
import com.lucevent.newsup.view.util.OnMoreSectionsClickListener;
import com.lucevent.newsup.view.util.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class NewsListFragment extends StoragePermissionFragment implements View.OnClickListener,
		OnBackPressedListener {

	private static final int MAIN_PUBLICATIONS = -1;
	private static final int NOTIFICATION = -2;
	private static final int SAVED_NOTIFICATION = -3;

	public static NewsListFragment instanceFor(int site_code)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(AppCode.SITE_CODE, site_code);

		NewsListFragment fragment = new NewsListFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	public static NewsListFragment instanceFor(Bundle extras)
	{
		extras.putInt(AppCode.SITE_CODE, NOTIFICATION);

		NewsListFragment fragment = new NewsListFragment();
		fragment.setArguments(extras);
		return fragment;
	}

	public static NewsListFragment instanceFor(DownloadData data)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(AppCode.SITE_CODE, SAVED_NOTIFICATION);
		bundle.putIntArray(AppCode.SOURCES, data.news_ids);

		NewsListFragment fragment = new NewsListFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	public int currentSiteCode, lastLoadedSiteCode;
	private boolean showSectionsButton;
	private Site currentSite;
	private int[] currentSections;
	private SectionsDialog sectionsDialog;

	public void setSite(int site_code)
	{
		currentSiteCode = site_code;
		if (site_code > 0)
			currentSite = AppData.getSiteByCode(site_code);
		else
			currentSite = null;

		if (sectionsDialog == null)
			sectionsDialog = new SectionsDialog(getActivity(), currentSite, onSectionSelected);
		else
			sectionsDialog.setSections(currentSite);
	}

	public void setUp()
	{
		if (currentSiteCode == lastLoadedSiteCode) {
			mAdapter.notifyDataSetChanged();
			return;
		}
		currentSections = null;

		mAdapter.showSiteIcon(currentSite == null);
		mAdapter.clear();
		if (mReloadBox.getVisibility() == View.VISIBLE)
			mReloadBox.setVisibility(View.GONE);
		mProgressBar.setVisibility(ProgressBar.VISIBLE);
		switch (currentSiteCode) {
			case SAVED_NOTIFICATION:
				mAdapter.setOnMoreSectionsClick(null);
				showSectionsButton = false;

				int[] news_ids = (int[]) getArguments().getIntArray(AppCode.SOURCES);
				Collection<News> c = mDataManager.getDatabaseManager().readNews(news_ids);

				mHandler.obtainMessage(AppCode.NEWS_COLLECTION, c).sendToTarget();
				mHandler.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();
				break;

			case NOTIFICATION:    // Load from notification
				mAdapter.setOnMoreSectionsClick(null);
				showSectionsButton = false;

				Event event = null;
				if (getArguments().containsKey(AppCode.STRING_FILTERS)) {
					event = new Event();
					event.keyWords = getArguments().getStringArray(AppCode.STRING_FILTERS);
				}
				ArrayList<DownloadNotification.Source> sources = (ArrayList<DownloadNotification.Source>) getArguments().getSerializable(AppCode.SOURCES);
				assert sources != null : "Arguments can't be recovered";

				for (DownloadNotification.Source s : sources) {
					c = mDataManager.getSavedNewsOf(s);
					if (event != null)
						c = event.filter(c);

					mHandler.obtainMessage(AppCode.NEWS_COLLECTION, c).sendToTarget();
				}

				mHandler.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();
				break;

			case MAIN_PUBLICATIONS:
				showSectionsButton = false;
				mAdapter.setOnMoreSectionsClick(null);
				mDataManager.getReaderManager().read(AppData.getSites(AppSettings.getMainSitesCodes()), mHandler);
				break;

			default:
				showSectionsButton = currentSite.getSections().size() > 1;
				mAdapter.setOnMoreSectionsClick(showSectionsButton ? onMoreSectionsClick : null);
				mDataManager.getReaderManager().read(currentSite, null, mHandler);
		}
		lastLoadedSiteCode = currentSiteCode;
		setUpColors();
	}

	public void onLoadImagesPreferenceChanged()
	{
		mAdapter.setUserPreferences(getActivity());
	}

	private KernelManager mDataManager;
	private NewsAdapter mAdapter;
	private Handler mHandler;

	private View mMainView;
	private NewsView mNewsView;
	private RecyclerView mRecyclerView;
	private FloatingActionButton mBtnSections;
	private View mProgressBar, mReloadBox;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		Context context = getActivity();

		mHandler = new Handler(this);
		mDataManager = new KernelManager(context);

		lastLoadedSiteCode = -9;
		setSite(getArguments().getInt(AppCode.SITE_CODE));

		if (!ProSettings.isDeveloperModeEnabled())
			context.startService(
					new Intent(context, StatisticsService.class)
							.putExtra(AppCode.REQUEST_CODE, StatisticsService.REQ_SEND)
			);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		if (mAdapter == null) {
			mMainView = inflater.inflate(R.layout.f_news_list, container, false);

			mAdapter = new NewsAdapter(this, onBookmarkClick, NewsAdapterList.SortBy.byTime);
			onLoadImagesPreferenceChanged();

			LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
			layoutManager.setAutoMeasureEnabled(true);

			mRecyclerView = (RecyclerView) mMainView.findViewById(R.id.list);
			mRecyclerView.setNestedScrollingEnabled(false);
			mRecyclerView.setHasFixedSize(false);
			mRecyclerView.setLayoutManager(layoutManager);
			mRecyclerView.setAdapter(mAdapter);

			mNewsView = (NewsView) mMainView.findViewById(R.id.news_view);
			mNewsView.setFragmentContext(this, getActivity() instanceof Main ? ((Main) getActivity()).drawer : null);
			mNewsView.setBookmarkStateChangeListener(onBookmarkClick);
			mNewsView.setImageLongClickListener(onImageLongClick);

			mBtnSections = (FloatingActionButton) mMainView.findViewById(R.id.button_sections);
			mBtnSections.setOnClickListener(onSectionsAction);

			mProgressBar = mMainView.findViewById(R.id.progress_bar);
			mReloadBox = mMainView.findViewById(R.id.try_reload);

			mMainView.findViewById(R.id.view_hands_free).setOnClickListener(onViewHandsFree);
		}
		setUp();
		return mMainView;
	}

	@Override
	public void onResume()
	{
		super.onResume();

		mNewsView.resume();

		sectionsDialog.setSections(currentSite);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		mNewsView.pause();
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mMenu = null;
		mNewsView.destroy();
	}

	private Menu mMenu;

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		mMenu = menu;
		mMenu.add(R.string.menu_favorite)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
				.setIcon(R.drawable.ic_favorite)
				.setOnMenuItemClickListener(onFavoriteToggleAction);
		mMenu.add(R.string.menu_settings)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
				.setIcon(R.drawable.ic_configuration)
				.setOnMenuItemClickListener(onSiteConfigurationAction);

		super.onCreateOptionsMenu(mMenu, inflater);

		setUpColors();
	}

	private boolean displayingNews = false;

	@Override
	public boolean onBackPressed()
	{
		if (displayingNews) {
			if (showSectionsButton)
				mBtnSections.setVisibility(View.VISIBLE);
			mNewsView.hideNews();
			displayingNews = false;
			return true;
		}
		return false;
	}

	@Override
	public void onClick(final View v)
	{
		final News news = (News) v.getTag();
		Context context = getActivity();

		if (currentSite instanceof UserSite) {
			Utils.openCustomTab(context, news);
			return;
		}

		KernelManager.readContentOf(news);

		if (news.content != null && !news.content.isEmpty()) {
			mNewsView.displayNews(news);
			mBtnSections.setVisibility(View.GONE);
			displayingNews = true;
			mDataManager.getDatabaseManager().setNewsRead(news);
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
				NewsListFragment.this.onClick(v);
				dialog.dismiss();
			}
		});
		view.findViewById(R.id.action_open_in_browser).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v2)
			{
				Utils.openCustomTab(getActivity(), news);
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

	private View.OnClickListener onViewHandsFree = new View.OnClickListener() {

		@Override
		public void onClick(View v)
		{
			mAdapter.discloseData();
			startActivity(new Intent(getActivity(), HandsFreeNewsViewActivity.class));
		}
	};

	private static class Handler extends android.os.Handler {

		private final WeakReference<NewsListFragment> context;

		Handler(NewsListFragment context)
		{
			this.context = new WeakReference<>(context);
		}

		@Override
		public void handleMessage(Message msg)
		{
			NewsListFragment service = context.get();
			switch (msg.what) {
				case AppCode.NEWS_COLLECTION:
					Collection<News> news = (Collection<News>) msg.obj;

					if (news.isEmpty())
						return;

					if (service.currentSiteCode > 0 &&
							news.iterator().next().site_code != service.currentSiteCode)
						return;

					service.mAdapter.addAll(news);
					if (service.mAdapter.getItemCount() == 0 || service.mRecyclerView.computeVerticalScrollOffset() == 0)
						service.mRecyclerView.smoothScrollToPosition(0);
					break;
				case AppCode.NEWS_LOADED:
					service.mProgressBar.setVisibility(ProgressBar.GONE);
					if (service.mAdapter.getItemCount() == 0) {
						if (service.mReloadBox instanceof ViewStub) {
							service.mReloadBox = ((ViewStub) service.mReloadBox).inflate();
							service.mReloadBox.findViewById(R.id.btn_reload).setOnClickListener(service.onReloadClickListener);
						}
						service.mReloadBox.setVisibility(View.VISIBLE);
					}
					break;
				case AppCode.NO_INTERNET:
					Snackbar snackbar = Snackbar.make(service.mMainView, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG);
					if (service.currentSite != null) {
						int color = service.currentSite.color == 0xffffffff ? 0xffcccccc : service.currentSite.color;
						snackbar.getView().setBackgroundColor(color);
					}
					snackbar.show();
					break;
				case AppCode.ALERT:
					new AppAlertDialog(service.getActivity())
							.prepare((Alert) msg.obj)
							.start();
					break;
				case AppCode.ERROR:
					AppSettings.printerror("[NLF] Error received by the Handler", null);
					break;
				default:
					AppSettings.printerror("[NLF] OPTION UNKNOWN: " + msg.what, null);
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	private void setUpColors()
	{
		if (mMenu == null || mMenu.size() < 2) return;

		Toolbar ab = (Toolbar) getActivity().findViewById(R.id.toolbar);
		if (currentSite == null) {

			switch (currentSiteCode) {
				case SAVED_NOTIFICATION:
					ab.setTitle(R.string.notification);
					break;
				case NOTIFICATION:
					ab.setTitle(R.string.app_name);
					break;
				case MAIN_PUBLICATIONS:
					ab.setTitle(R.string.my_news);
					break;
			}

			mBtnSections.setVisibility(ImageButton.GONE);
			mMenu.getItem(0).setVisible(false);
			mMenu.getItem(1).setVisible(false);

			((FloatingActionButton) mMainView.findViewById(R.id.view_hands_free))
					.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

		} else {

			ab.setTitle(currentSite.name);

			if (showSectionsButton) {
				mBtnSections.setVisibility(ImageButton.VISIBLE);
				mBtnSections.setBackgroundTintList(ColorStateList.valueOf(currentSite.color == 0xffffffff ? 0xff666666 : currentSite.color));
			} else
				mBtnSections.setVisibility(ImageButton.GONE);

			FloatingActionButton handsFreeBtn = (FloatingActionButton) mMainView.findViewById(R.id.view_hands_free);
			handsFreeBtn.setBackgroundTintList(ColorStateList.valueOf(currentSite.color == 0xffffffff ? 0xff666666 : currentSite.color));

			setFavoriteIcon();
			Drawable icon_conf = mMenu.getItem(1).setVisible(true).getIcon();

			if (currentSite.needsBrightColors()) {
				mBtnSections.clearColorFilter();
				handsFreeBtn.clearColorFilter();
				icon_conf.clearColorFilter();
			} else {
				mBtnSections.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
				handsFreeBtn.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
				icon_conf.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	private void setFavoriteIcon()
	{
		Drawable icon_fav = ContextCompat.getDrawable(getActivity(),
				AppSettings.isFavorite(currentSite) ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);

		mMenu.getItem(0).setVisible(true).setIcon(icon_fav);

		if (currentSite.needsBrightColors())
			icon_fav.clearColorFilter();
		else
			icon_fav.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
	}

	private View.OnClickListener onSectionsAction = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			sectionsDialog.show();
		}
	};

	private MenuItem.OnMenuItemClickListener onFavoriteToggleAction = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			AppSettings.toggleFavorite(currentSite, true);
			setFavoriteIcon();
			return true;
		}
	};

	private MenuItem.OnMenuItemClickListener onSiteConfigurationAction = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			((OnReplaceFragmentListener) getActivity()).onReplaceFragment(SiteSettingsFragment.instanceFor(currentSite.code), R.id.nav_settings, true);
			return true;
		}
	};

	private View.OnClickListener onSectionSelected = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			mAdapter.clear();
			mProgressBar.setVisibility(ProgressBar.VISIBLE);
			currentSections = new int[]{(int) v.getTag()};
			mDataManager.getReaderManager().read(currentSite, currentSections, mHandler);
			sectionsDialog.dismiss();
		}
	};

	private OnMoreSectionsClickListener onMoreSectionsClick = new OnMoreSectionsClickListener() {
		@Override
		public Set<Pair<Integer, Section>> sections()
		{
			Set<Pair<Integer, Section>> res;
			Sections s = currentSite.getSections();
			if (s.size() <= 9) {
				res = new HashSet<>(s.size());
				for (int i = 0; i < s.size(); i++)
					res.add(new Pair<>(i, s.get(i)));

			} else {
				res = new TreeSet<>(new Comparator<Pair<Integer, Section>>() {
					@Override
					public int compare(Pair<Integer, Section> s1, Pair<Integer, Section> s2)
					{
						return s1.second.name.compareTo(s2.second.name);
					}
				});
				Random r = new Random();
				while (res.size() < 9) {
					int pos;
					do {
						pos = r.nextInt(s.size());
					} while (s.get(pos).level == -1);
					res.add(new Pair<>(pos, s.get(pos)));
				}
			}
			return res;
		}

		@Override
		public void onClick(View v)
		{
			onSectionSelected.onClick(v);
		}
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

	private View.OnClickListener onReloadClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v)
		{
			mProgressBar.setVisibility(ProgressBar.VISIBLE);
			mReloadBox.setVisibility(View.GONE);

			if (currentSite == null)
				mDataManager.getReaderManager().read(AppData.getSites(AppSettings.getMainSitesCodes()), mHandler);

			else
				mDataManager.getReaderManager().read(currentSite, currentSections, mHandler);
		}
	};

}
