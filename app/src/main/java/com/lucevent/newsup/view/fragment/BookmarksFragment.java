package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.NewsManager;
import com.lucevent.newsup.permission.StoragePermissionHandler;
import com.lucevent.newsup.view.adapter.NewsAdapter;
import com.lucevent.newsup.view.util.ContentLoader;
import com.lucevent.newsup.view.util.NewsView;
import com.lucevent.newsup.view.util.OnBackPressedListener;

import java.lang.ref.WeakReference;

public class BookmarksFragment extends android.app.Fragment implements View.OnClickListener,
        View.OnLongClickListener, OnBackPressedListener {

    private NewsAdapter adapter;
    private NewsMap bookmarks;

    private NewsView newsView;
    private Handler handler;
    private StoragePermissionHandler permissionHandler;
    private boolean displayingNews = false;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        handler = new Handler(this);
        permissionHandler = new StoragePermissionHandler(getActivity());

        adapter = new NewsAdapter(new NewsArray(), this, this, onBookmarkClick);
        adapter.showSiteLogo(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        menu.add(R.string.menu_delete_all)
                .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
                .setIcon(R.drawable.ic_remove_all)
                .setOnMenuItemClickListener(onDeleteAllAction);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.f_news_list, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.addOnScrollListener(new ContentLoader(layoutManager) {
            @Override
            public void onLoadMore()
            {
                adapter.loadMoreData();
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        newsView = (NewsView) view.findViewById(R.id.news_view);
        newsView.setFragmentContext(this, ((Main) getActivity()).drawer);
        newsView.setBookmarkChangeListener(onBookmarkClick);

        view.findViewById(R.id.button_sections).setVisibility(View.GONE);

        BookmarksManager.getBookmarkedNews(handler);
        return view;
    }

    @Override
    public boolean onBackPressed()
    {
        if (displayingNews) {
            newsView.hideNews();
            displayingNews = false;
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v)
    {
        News news = (News) v.getTag();
        NewsManager.readContentOf(news);

        displayingNews = true;
        NewsManager.addToHistory(news);
        newsView.displayNews(news,v);
    }

    @Override
    public boolean onLongClick(View v)
    {
        //// TODO: 14/10/2016
        return false;
    }

    private MenuItem.OnMenuItemClickListener onDeleteAllAction = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.msg_confirm_to_remove_all_saved_news)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            BookmarksManager.removeAllEntries();
                            adapter.clear();
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
            return true;
        }
    };

    private View tempBookmarkButton;

    private View.OnClickListener onBookmarkClick = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if (permissionHandler.checkAndAsk(BookmarksFragment.this)) {
                News news = (News) v.getTag();

                if (BookmarksManager.isBookmarked(news)) {
                    bookmarks.remove(news);
                    adapter.remove(news);
                } else {
                    bookmarks.add(news);
                    adapter.setNewDataSet(bookmarks);
                }
                BookmarksManager.toggleBookmark(news);
                newsView.setBookmarkButtonImage(v);
            } else
                tempBookmarkButton = v;
        }
    };

    static class Handler extends android.os.Handler {

        private final WeakReference<BookmarksFragment> context;

        Handler(BookmarksFragment context)
        {
            this.context = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg)
        {
            BookmarksFragment service = context.get();
            switch (msg.what) {
                case AppCode.NEWS_MAP_READ:
                    service.bookmarks = (NewsMap) msg.obj;
                    service.adapter.addAll(service.bookmarks);
                    break;
                case AppCode.ERROR:
                    AppSettings.printerror("[BMF] Error received by the Handler", null);
                    break;
                default:
                    AppSettings.printerror("[BMF] OPTION UNKNOWN: " + msg.what, null);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        if (permissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            BookmarksManager.toggleBookmark((News) tempBookmarkButton.getTag());
            newsView.setBookmarkButtonImage(tempBookmarkButton);
        }
    }

}
