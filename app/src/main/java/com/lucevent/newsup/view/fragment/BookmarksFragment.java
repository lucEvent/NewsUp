package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

import com.lucevent.newsup.Main;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.view.adapter.NewsFilterAdapter;
import com.lucevent.newsup.view.util.NUSearchBar;
import com.lucevent.newsup.view.util.NewsAdapterList;
import com.lucevent.newsup.view.util.NewsView;
import com.lucevent.newsup.view.util.OnBackPressedListener;

import java.util.Collection;

public class BookmarksFragment extends StoragePermissionFragment implements View.OnClickListener,
		OnBackPressedListener, NUSearchBar.CallBack {

	private NewsFilterAdapter adapter;

	private NewsView newsView;
	private NUSearchBar searchView;

	private boolean displayingNews = false;

	private View noContentMessage;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		adapter = new NewsFilterAdapter(this, onBookmarkClick, NewsAdapterList.SortBy.byTime);
		adapter.showSiteIcon(true);
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		View view = inflater.inflate(R.layout.f_news_list_with_search, container, false);

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

		Collection<News> bmNews = BookmarksManager.getBookmarkedNews();
		if (!bmNews.isEmpty())
			adapter.setNewDataSet(bmNews);
		else
			displayNoBookmarksMessage();

		return view;
	}

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
		KernelManager.setNewsRead(news);
		newsView.displayNews(news);
		searchView.hideKeyBoard();
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

	private MenuItem.OnMenuItemClickListener onSearchAction = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			((AppCompatActivity) getActivity()).getSupportActionBar().hide();
			searchView.start(BookmarksFragment.this);
			return true;
		}
	};

	private MenuItem.OnMenuItemClickListener onDeleteAllAction = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			if (adapter.getItemCount() > 0)
				new AlertDialog.Builder(getActivity())
						.setMessage(R.string.msg_confirm_to_remove_all_saved_news)
						.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								BookmarksManager.removeAllEntries();
								adapter.clear();
								displayNoBookmarksMessage();
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

		boolean isBookmarked = BookmarksManager.toggleBookmark(news);

		if (!isBookmarked) {
			adapter.remove(news);

			if (adapter.getItemCount() == 0)
				displayNoBookmarksMessage();
		} else
			adapter.add(news);

		btn.setSelected(isBookmarked);
	}

	private void displayNoBookmarksMessage()
	{
		if (noContentMessage instanceof ViewStub)
			noContentMessage = ((ViewStub) noContentMessage).inflate();

		((TextView) noContentMessage.findViewById(R.id.message)).setText(R.string.msg_no_bookmarks);
	}

}
