package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
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
import com.lucevent.newsup.permission.StoragePermissionHandler;
import com.lucevent.newsup.view.adapter.NewsAdapter;
import com.lucevent.newsup.view.util.NUSearchBar;
import com.lucevent.newsup.view.util.NewsAdapterList;
import com.lucevent.newsup.view.util.NewsView;
import com.lucevent.newsup.view.util.OnBackPressedListener;

import java.lang.ref.WeakReference;
import java.util.Collection;

public class HistorialFragment extends android.app.Fragment implements View.OnClickListener,
        View.OnLongClickListener, OnBackPressedListener, NUSearchBar.SearchBarListener {

    private HistoryManager dataManager;
    private StoragePermissionHandler permissionHandler;
    private NewsAdapter adapter;
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
        permissionHandler = new StoragePermissionHandler(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.f_news_list_with_search, container, false);

        adapter = new NewsAdapter(this, this, onBookmarkClick, NewsAdapterList.SortBy.byReadOn);
        adapter.showSiteLogo(true);

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

        noContentMessage = view.findViewById(R.id.no_content);
        searchView = (NUSearchBar) view.findViewById(R.id.searchView);

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
        }
        return false;
    }

    @Override
    public void onClick(View v)
    {
        News news = (News) v.getTag();

        KernelManager.readContentOf(news);

        displayingNews = true;
        newsView.displayNews(news);
        searchView.hideKeyBoard();

        News clone = new News(news.id, news.title, news.link, news.description, news.date, news.tags, news.site_code, news.section_code, news.readOn);
        KernelManager.setNewsRead(clone);
    }

    @Override
    public boolean onLongClick(View v)
    {
        //// TODO: 14/10/2016
        return false;
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
            searchView.start(adapter, HistorialFragment.this);
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
                                searchView.restart();
                                displayNoRecordsMessage();
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
            if (permissionHandler.checkAndAsk(HistorialFragment.this))
                bookmarkStateChanged(v);
            else
                tempBookmarkButton = v;
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        if (permissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            bookmarkStateChanged(tempBookmarkButton);
        }
    }

    private void bookmarkStateChanged(View btn)
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

}

