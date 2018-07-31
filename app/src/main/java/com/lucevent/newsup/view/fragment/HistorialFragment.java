package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.HistoryManager;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.view.adapter.NewsFilterAdapter;
import com.lucevent.newsup.view.dialog.FilterDialog;
import com.lucevent.newsup.view.util.NUSearchBar;
import com.lucevent.newsup.view.util.NewsAdapterList;
import com.lucevent.newsup.view.util.NewsView;
import com.lucevent.newsup.view.util.OnBackPressedListener;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.TreeSet;

public class HistorialFragment extends StoragePermissionFragment implements View.OnClickListener,
		OnBackPressedListener, NUSearchBar.CallBack {

	private HistoryManager dataManager;
	private NewsFilterAdapter adapter;
	private NewsView newsView;
	private NUSearchBar searchView;

	private View noContentMessage;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		Handler handler = new Handler(this);
		dataManager = new HistoryManager(getActivity(), handler);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		View view = inflater.inflate(R.layout.f_historial, container, false);

		adapter = new NewsFilterAdapter(this, onBookmarkClick, NewsAdapterList.SortBy.byReadOn);
		adapter.showSiteIcon(true);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setAutoMeasureEnabled(true);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
		recyclerView.setNestedScrollingEnabled(false);
		recyclerView.setHasFixedSize(false);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);

		newsView = (NewsView) view.findViewById(R.id.news_view);
		newsView.setFragmentContext(this, ((Main) getActivity()).drawer);
		newsView.setBookmarkStateChangeListener(onBookmarkClick);
		newsView.setImageLongClickListener(onImageLongClick);

		noContentMessage = view.findViewById(R.id.no_content);
		searchView = (NUSearchBar) view.findViewById(R.id.searchView);
		view.findViewById(R.id.filter).setOnClickListener(onFilterClick);

		dataManager.getReadNews();

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		menu.add(R.string.search)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
				.setIcon(R.drawable.ic_search_white)
				.setOnMenuItemClickListener(onSearchAction);

		menu.add(R.string.menu_delete_all)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
				.setIcon(R.drawable.ic_remove_all)
				.setOnMenuItemClickListener(onDeleteAllAction);

		super.onCreateOptionsMenu(menu, inflater);
	}

	private boolean displayingNews = false;

	@Override
	public boolean onBackPressed()
	{
		if (displayingNews) {
			newsView.hideNews();
			displayingNews = false;
			return true;
		} else if (searchView.isShown()) {
			searchView.hide();
			return true;
		}
		return false;
	}

	@Override
	public void onDestroyView()
	{
		super.onDestroyView();
		if (searchView.isShown())
			searchView.hide();
	}

	@Override
	public void onClick(View v)
	{
		News news = (News) v.getTag();

		KernelManager.readContentOf(news);

		displayingNews = true;
		newsView.displayNews(news);
		searchView.hideKeyBoard();

		dataManager.getDataManager().setNewsRead(news.clone());
	}

	@Override
	public void onFilter(String filter)
	{
		adapter.filter(filter);
	}

	@Override
	public void onEnd()
	{
		((AppCompatActivity) getActivity()).getSupportActionBar().show();
	}

	static class Handler extends android.os.Handler {

		private final WeakReference<HistorialFragment> context;

		Handler(HistorialFragment context)
		{
			this.context = new WeakReference<>(context);
		}

		@Override
		public void handleMessage(Message msg)
		{
			HistorialFragment service = context.get();
			switch (msg.what) {
				case AppCode.NEWS_COLLECTION:
					Collection<News> news = (Collection<News>) msg.obj;
					if (!news.isEmpty()) {
						service.adapter.addAll(news);
						break;
					}
				case AppCode.ERROR:
					service.displayNoRecordsMessage();
					break;
				default:
					AppSettings.printerror("[HF] OPTION UNKNOWN: " + msg.what, null);
			}
		}
	}

	private MenuItem.OnMenuItemClickListener onSearchAction = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			((AppCompatActivity) getActivity()).getSupportActionBar().hide();
			searchView.start(HistorialFragment.this);
			return true;
		}
	};

	private MenuItem.OnMenuItemClickListener onDeleteAllAction = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			if (adapter.getItemCount() > 0)
				new AlertDialog.Builder(getActivity())
						.setMessage(R.string.msg_confirm_to_clear_history)
						.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								dataManager.clearHistory();
								adapter.clear();
								displayNoRecordsMessage();
							}
						})
						.setNegativeButton(R.string.cancel, null)
						.show();
			return true;
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
			adapter.update(news);
	}

	private void displayNoRecordsMessage()
	{
		if (noContentMessage instanceof ViewStub)
			noContentMessage = ((ViewStub) noContentMessage).inflate();

		((TextView) noContentMessage.findViewById(R.id.message)).setText(R.string.msg_no_history);
	}

	private FilterDialog filterDialog;

	private View.OnClickListener onFilterClick = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			if (filterDialog == null)
				filterDialog = new FilterDialog(getActivity())
						.news(adapter.getDataSet())
						.listener(onFilterListener);

			filterDialog.show();
		}

		FilterDialog.Callback onFilterListener = new FilterDialog.Callback() {
			@Override
			public void onFilter(TreeSet<Integer> f)
			{
				adapter.filter(f);
			}
		};
	};

}

