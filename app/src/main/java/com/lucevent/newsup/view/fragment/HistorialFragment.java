package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
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
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.HistoryManager;
import com.lucevent.newsup.kernel.NewsManager;
import com.lucevent.newsup.view.adapter.NewsAdapter;
import com.lucevent.newsup.view.util.ContentLoader;
import com.lucevent.newsup.view.util.NewsView;
import com.lucevent.newsup.view.util.OnBackPressedListener;

import java.lang.ref.WeakReference;

public class HistorialFragment extends android.app.Fragment implements View.OnClickListener,
        OnBackPressedListener {

    private HistoryManager dataManager;
    private NewsAdapter adapter;
    private NewsView newsView;

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
        View view = inflater.inflate(R.layout.f_news_list, container, false);

        adapter = new NewsAdapter(new NewsArray(), this);
        adapter.showSiteLogo(true);

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

        view.findViewById(R.id.button_sections).setVisibility(View.GONE);

        dataManager.getReadNews();

        return view;
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

        NewsManager.getNewsContent(news);

        displayingNews = true;
        NewsManager.addToHistory(news);
        newsView.displayNews(news);
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
                case AppCode.NEWS_MAP_READ:
                case AppCode.NEWS_MAP_FRAGMENT_READ:
                    service.adapter.addAll((NewsMap) msg.obj);
                    break;
                case AppCode.ERROR:
                    AppSettings.printerror("[HF] Error received by the Handler", null);
                    break;
                default:
                    AppSettings.printerror("[HF] OPTION UNKNOWN: " + msg.what, null);
            }
        }
    }

    private MenuItem.OnMenuItemClickListener onDeleteAllAction = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.msg_confirm_to_clear_history)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dataManager.clearHistory();
                            adapter.clear();
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
            return true;
        }
    };

}

