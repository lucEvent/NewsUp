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
import com.lucevent.newsup.view.util.Utils;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.TreeSet;

public class HistorialFragment extends StoragePermissionFragment implements View.OnClickListener,
		OnBackPressedListener, NUSearchBar.CallBack {

	private HistoryManager mDataManager;
	private NewsFilterAdapter mAdapter;
	private NewsView mNewsView;
	private NUSearchBar mSearchView;

	private View mNoContentMessage;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		Handler handler = new Handler(this);
		mDataManager = new HistoryManager(getActivity(), handler);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		View view = inflater.inflate(R.layout.f_historial, container, false);

		mAdapter = new NewsFilterAdapter(this, onBookmarkClick, NewsAdapterList.SortBy.byReadOn);
		mAdapter.showSiteIcon(true);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
		recyclerView.setNestedScrollingEnabled(false);
		recyclerView.setHasFixedSize(false);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(mAdapter);

		mNewsView = (NewsView) view.findViewById(R.id.news_view);
		mNewsView.setFragmentContext(this, ((Main) getActivity()).drawer);
		mNewsView.setBookmarkStateChangeListener(onBookmarkClick);
		mNewsView.setImageLongClickListener(onImageLongClick);

		mNoContentMessage = view.findViewById(R.id.no_content);
		mSearchView = (NUSearchBar) view.findViewById(R.id.searchView);
		view.findViewById(R.id.filter).setOnClickListener(mOnFilterClick);

		mDataManager.getReadNews();

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		menu.add(R.string.search)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
				.setIcon(R.drawable.ic_search_white)
				.setOnMenuItemClickListener(mOnSearchAction);

		menu.add(R.string.menu_delete_all)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
				.setIcon(R.drawable.ic_remove_all)
				.setOnMenuItemClickListener(mOnDeleteAllAction);

		super.onCreateOptionsMenu(menu, inflater);
	}

	private boolean mDisplayingNews = false;

	@Override
	public boolean onBackPressed()
	{
		if (mDisplayingNews) {
			mNewsView.hideNews();
			mDisplayingNews = false;
			return true;
		} else if (mSearchView.isShown()) {
			mSearchView.hide();
			return true;
		}
		return false;
	}

	@Override
	public void onDestroyView()
	{
		super.onDestroyView();
		if (mSearchView.isShown())
			mSearchView.hide();
	}

	@Override
	public void onClick(View v)
	{
		News news = (News) v.getTag();

		KernelManager.readContentOf(news);

		if (news.content == null || news.content.isEmpty()) {
			Utils.openCustomTab(getActivity(), news);
			return;
		}

		mDisplayingNews = true;
		mNewsView.displayNews(news, true);
		mSearchView.hideKeyBoard();

		mDataManager.getDataManager().setNewsRead(news.clone());
	}

	@Override
	public void onFilter(String filter)
	{
		mAdapter.filter(filter);
	}

	@Override
	public void onEnd()
	{
		((AppCompatActivity) getActivity()).getSupportActionBar().show();
	}

	static class Handler extends android.os.Handler {

		private final WeakReference<HistorialFragment> mContext;

		Handler(HistorialFragment context)
		{
			mContext = new WeakReference<>(context);
		}

		@Override
		public void handleMessage(Message msg)
		{
			HistorialFragment service = mContext.get();
			switch (msg.what) {
				case AppCode.NEWS_COLLECTION:
					Collection<News> news = (Collection<News>) msg.obj;
					if (!news.isEmpty()) {
						service.mAdapter.addAll(news);
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

	private MenuItem.OnMenuItemClickListener mOnSearchAction = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			((AppCompatActivity) getActivity()).getSupportActionBar().hide();
			mSearchView.start(HistorialFragment.this);
			return true;
		}
	};

	private MenuItem.OnMenuItemClickListener mOnDeleteAllAction = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			if (mAdapter.getItemCount() > 0)
				new AlertDialog.Builder(getActivity())
						.setMessage(R.string.msg_confirm_to_clear_history)
						.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								mDataManager.clearHistory();
								mAdapter.clear();
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
			mAdapter.update(news);
	}

	private void displayNoRecordsMessage()
	{
		if (mNoContentMessage instanceof ViewStub)
			mNoContentMessage = ((ViewStub) mNoContentMessage).inflate();

		((TextView) mNoContentMessage.findViewById(R.id.message)).setText(R.string.msg_no_history);
	}

	private FilterDialog mFilterDialog;

	private View.OnClickListener mOnFilterClick = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			if (mFilterDialog == null)
				mFilterDialog = new FilterDialog(getActivity())
						.news(mAdapter.getDataSet())
						.listener(onFilterListener);

			mFilterDialog.show();
		}

		FilterDialog.Callback onFilterListener = new FilterDialog.Callback() {
			@Override
			public void onFilter(TreeSet<Integer> f)
			{
				mAdapter.filter(f);
			}
		};
	};

}

