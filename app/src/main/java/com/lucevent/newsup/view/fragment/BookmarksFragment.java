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

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.view.adapter.NewsAdapter;
import com.lucevent.newsup.view.util.ContentLoader;
import com.lucevent.newsup.view.util.OnNewsItemClickListener;

import java.lang.ref.WeakReference;

public class BookmarksFragment extends android.app.Fragment {

    private BookmarksManager dataManager;
    private NewsAdapter adapter;
    private NewsMap bookmarks;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        dataManager = new BookmarksManager(new Handler(this));

        adapter = new NewsAdapter(new NewsArray(), new OnNewsItemClickListener(getActivity()));
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

        view.findViewById(R.id.button_sections).setVisibility(View.GONE);

        dataManager.getBookmarkedNews();
        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (bookmarks != null && bookmarks.size() != adapter.getActualItemCount())
            adapter.setNewDataSet(bookmarks);
    }

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
                    System.out.println("[BMF] Error received by the Handler");
                    break;
                default:
                    System.out.println("[BMF] OPTION UNKNOWN: " + msg.what);
            }
        }
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
                            dataManager.removeAllEntries();
                            adapter.clear();
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
            return true;
        }
    };

}
